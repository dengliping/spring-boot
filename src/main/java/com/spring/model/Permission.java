package com.spring.model;

import lombok.Data;

/**
 * Created by dalp on 2017/8/9.
 * 权限
 */
@Data
public class Permission extends BaseModel{

    private String permissionName; //权限名称

    private String description; //权限描述

    private String url; //授权链接

    private Integer pid; //父节点id
}
