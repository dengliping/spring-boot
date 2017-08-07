package com.spring.dao;

import com.spring.model.GoddessInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by dalp on 2017/8/7.
 */
@Mapper
@Component
public interface GoddessInfoMapper {

    int insertGoddessInfo(GoddessInfo goddessInfo);

    GoddessInfo getGoddessInfoList();
}
