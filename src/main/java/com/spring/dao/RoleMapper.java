package com.spring.dao;

import com.spring.model.SysRole;

/**
 * Created by dalp on 2017/8/14.
 */
public interface RoleMapper {

    /**
     * 新增系统角色
     * @param sysRole
     * @return
     */
    int insertSysRole(SysRole sysRole);
}
