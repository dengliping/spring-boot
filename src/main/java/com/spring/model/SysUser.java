package com.spring.model;

import lombok.Data;
import java.util.List;

/**
 * Created by dalp on 2017/8/10.
 */
@Data
public class SysUser extends BaseModel{
    private String userName; //用户名
    private String userPsd; //用户密码

    private List<SysRole> roles; //用户角色list
}
