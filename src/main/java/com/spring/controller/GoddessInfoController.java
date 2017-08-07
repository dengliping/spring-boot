package com.spring.controller;

import com.spring.dto.PageResponse;
import com.spring.service.GoddessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dalp on 2017/8/7.
 */
@Controller
public class GoddessInfoController {

    @Autowired
    private GoddessInfoService goddessInfoService;

    @RequestMapping(value = "/goddess", method = RequestMethod.GET)
    public String getGoddessInfo(Model model){
        PageResponse<Object> pr = goddessInfoService.getGoddessInfoList();
        model.addAttribute("goddessList", pr.getContent());
        return "goddess";
    }
}
