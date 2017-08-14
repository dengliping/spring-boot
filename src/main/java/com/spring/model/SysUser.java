package com.spring.model;

import lombok.Data;
import java.util.List;

/**
 * Created by dalp on 2017/8/10.
 */
@Data
public class SysUser{
    private Integer id;
    private String username;
    private String password;

    private List<SysRole> roles;
}
