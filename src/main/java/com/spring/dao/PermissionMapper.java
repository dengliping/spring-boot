package com.spring.dao;

import com.spring.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dalp on 2017/8/9.
 */
@Mapper
@Component
public interface PermissionMapper {
    List<Permission> findAll();
    List<Permission> findByAdminUserId(@Param("userId") Long userId);
}
