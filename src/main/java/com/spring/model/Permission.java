package com.spring.model;

import lombok.Data;

/**
 * Created by dalp on 2017/8/9.
 * 权限
 */
@Data
public class Permission {
    private Integer id;
    //权限名称
    private String name;

    //权限描述
    private String description;

    //授权链接
    private String url;

    //父节点id
    private Integer pid;
}
