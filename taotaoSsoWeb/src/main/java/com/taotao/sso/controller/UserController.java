package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户处理控制器
 * Created by 孙建荣 on 17-6-18.下午5:00
 */
@Controller
public class UserController {
    private final UserService userService;
    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据token查询用户信息
     *
     * @param token 令牌值
     * @return 用户信息
     */
    @RequestMapping(value = "/user/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getUserByToken(@PathVariable String token) {
        return userService.getUserByToken(token);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 接收两个参数
        TaotaoResult result = userService.login(username, password);
        // 调用service进行登录
        String token = result.getData().toString();
        CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        // 从返回的结果中取出token,写入cookie,Cookie要跨
        //响应json数据,包含token
        return result;
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














