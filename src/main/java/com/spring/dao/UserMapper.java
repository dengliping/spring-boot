package com.spring.dao;

import com.spring.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by dalp on 2017/8/10.
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 根据用户名，获取其用户信息和角色list
     * @param userName
     * @return
     */
    SysUser findByUserName(@Param("userName") String userName);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insertSysUser(SysUser sysUser);

    /**
     * 新增用户角色关系
     * @param roleUserMap
     * @return
     */
    int insertSysRoleUser(Map<String, Object> roleUserMap);

//    /**
//     * 获取用户列表
//     * @return
//     */
//    List<SysUser> getSysUser();
}
