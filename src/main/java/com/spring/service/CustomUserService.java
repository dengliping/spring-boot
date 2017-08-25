package com.spring.service;

import com.spring.dao.PermissionMapper;
import com.spring.dao.RoleMapper;
import com.spring.dao.UserMapper;
import com.spring.dto.ManaGlobal;
import com.spring.dto.MessageResponse;
import com.spring.dto.PageResponse;
import com.spring.model.Permission;
import com.spring.model.SysRole;
import com.spring.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dalp on 2017/8/10.
 * 自定义UserDetailsService 接口
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserMapper userDao;
    @Autowired
    PermissionMapper permissionDao;
    @Autowired
    RoleMapper roleDao;

    @Override
    public UserDetails loadUserByUsername(String username){
        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getPermissionName()!= null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUserName(), user.getUserPsd(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }

//        SysUser user = userDao.findByUserName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(SysRole role : user.getRoles())
//        {
//            authorities.add(new SimpleGrantedAuthority(role.getName())); //遍历获得该用户的角色，添加到 authorities 中。
//            System.out.println(role.getName());
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), authorities);
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    public MessageResponse<Object> insertSysUser(SysUser sysUser){
        MessageResponse<Object> mr = new MessageResponse<>();
        //新增用户信息到用户表
        userDao.insertSysUser(sysUser);
        //根据用户名获取新增后的id
        SysUser newSysUser = userDao.findByUserName(sysUser.getUserName());
        Long newId = newSysUser.getId();
        //获取新增用户的角色
        List<SysRole> roles = sysUser.getRoles();
        //遍历新增用户角色关系
        roles.forEach(item -> {
            Map<String, Object> map = null;
            map.put("userId", newId);
            map.put("roleId", item.getId());
            userDao.insertSysRoleUser(map);
        });
        mr.setStatus(ManaGlobal.SUCCESS);
        return mr;
    }

//    /**
//     * 获取用户列表
//     * @return
//     */
//    public PageResponse<Object> getSysUser(){
//        PageResponse<Object> pr = new PageResponse<>();
//        pr.setContent(userDao.getSysUser());
//        return pr;
//    }
}
