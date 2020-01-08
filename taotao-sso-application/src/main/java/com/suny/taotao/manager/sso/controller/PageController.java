package com.suny.taotao.manager.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 孙建荣 on 17-6-18.下午10:25
 */
@Controller
public class PageController {

    @RequestMapping(value = "/page/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping(value = "/page/login")
    public String showLogin(){
        return "login";
    }

}
