package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户处理接口
 * Created by 孙建荣 on 17-6-18.下午4:52
 */
public interface UserService {
    TaotaoResult checkDate(String param, int type);

    TaotaoResult createUser(TbUser user);

    TaotaoResult login(String username, String password);
}
