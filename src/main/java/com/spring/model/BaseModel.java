package com.spring.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by dalp on 2016/7/7.
 */
@Data
public class BaseModel {

    protected  long id;

    protected Timestamp createTime;

    protected Timestamp modifyTime;

}
