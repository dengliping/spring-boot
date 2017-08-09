package com.spring.dao;

import com.spring.model.GoddessInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dalp on 2017/8/7.
 */
@Mapper
@Component
public interface GoddessInfoMapper {

    int insertGoddessInfo(GoddessInfo goddessInfo);

    List<GoddessInfo> getGoddessInfoList();

    int deleteGoddess(@Param("id") Long id);
}
