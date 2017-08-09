package com.spring.service;

import com.spring.dao.GoddessInfoMapper;
import com.spring.dto.ManaGlobal;
import com.spring.dto.MessageResponse;
import com.spring.dto.PageResponse;
import com.spring.model.GoddessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by dalp on 2017/8/7.
 */
@Service
public class GoddessInfoService {

    @Autowired
    private GoddessInfoMapper goddessInfoMapper;

    /**
     * 新增
     * @param goddessInfo
     * @return
     */
    public MessageResponse<Object> insertGoddessInfo(GoddessInfo goddessInfo){
        MessageResponse<Object> mr = new MessageResponse<>();
        if (goddessInfoMapper.insertGoddessInfo(goddessInfo) > 0){
            mr.setMessage("报名成功");
            mr.setStatus(ManaGlobal.SUCCESS);
        }
        return mr;
    }

    /**
     * 查询
     * @return
     */
    public PageResponse<Object> getGoddessInfoList(){
        PageResponse<Object> pr = new PageResponse<>();
        pr.setContent(goddessInfoMapper.getGoddessInfoList());
        return pr;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public MessageResponse<Object> deleteGoddess(Long id){
        MessageResponse<Object> mr = new MessageResponse<>();
        if (!StringUtils.isEmpty(goddessInfoMapper.deleteGoddess(id))){
            mr.setStatus(ManaGlobal.SUCCESS);
            mr.setMessage("删除成功");
        }
        return mr;
    }
}
