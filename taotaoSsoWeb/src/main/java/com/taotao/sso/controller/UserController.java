package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 用户注册的请求
     *
     * @param user 注册的用户实体数据
     * @return 注册的结果
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user) {
        return userService.createUser(user);
    }

    /**
     * 效验数据
     *
     * @param param 效验的数据
     * @param type  验证的类型
     * @return 验证的结果
     */
    @RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult checkData(@PathVariable(value = "param") String param, @PathVariable(value = "type") Integer type) {
        return userService.checkDate(param, type);
    }
}














