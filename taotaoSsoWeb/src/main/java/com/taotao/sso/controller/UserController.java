package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户处理控制器
 * Created by 孙建荣 on 17-6-18.下午5:00
 */
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET)
    public TaotaoResult checkData(@PathVariable(value = "param") String param, @PathVariable(value = "type") Integer type) {
        return userService.checkDate(param, type);
    }
}














