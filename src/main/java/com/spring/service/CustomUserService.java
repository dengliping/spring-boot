package com.spring.service;

import com.spring.dao.PermissionMapper;
import com.spring.dao.UserMapper;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dalp on 2017/8/10.
 * 自定义UserDetailsService 接口
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserMapper userDao;
//    @Autowired
//    PermissionMapper permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username){
//        SysUser user = userDao.findByUserName(username);
//        if (user != null) {
//            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (Permission permission : permissions) {
//                if (permission != null && permission.getName()!= null) {
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            }
//            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//        } else {
//            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
//        }

        SysUser user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(SysRole role : user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName())); //遍历获得该用户的角色，添加到 authorities 中。
            System.out.println(role.getName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}
