package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 孙建荣 on 17-6-11.下午3:26
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
