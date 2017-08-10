package com.spring.dao;

import com.spring.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by dalp on 2017/8/10.
 */
@Mapper
@Component
public interface UserMapper {

    SysUser findByUserName(@Param("userName") String userName);
}
