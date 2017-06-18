package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 用户处理接口
 * Created by 孙建荣 on 17-6-18.下午4:52
 */
public interface UserService {
    TaotaoResult checkDate(String param,int type);
}
