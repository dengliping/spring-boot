package com.spring.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by dalp on 2016/7/7.
 */
@Data
public class BaseModel {

    protected Long id;

    protected Timestamp createTime; //创建时间

    protected Timestamp modifyTime; //修改时间

}
