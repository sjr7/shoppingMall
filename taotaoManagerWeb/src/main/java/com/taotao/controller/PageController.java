package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 孙建荣 on 17-6-1.下午5:52
 */
@Controller
public class PageController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
