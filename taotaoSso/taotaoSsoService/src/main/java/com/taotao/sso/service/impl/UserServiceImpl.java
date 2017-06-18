package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户处理Service实现类
 * Created by 孙建荣 on 17-6-18.下午4:53
 */
@Service
public class UserServiceImpl implements UserService {
    private final TbUserMapper tbUserMapper;

    @Autowired
    public UserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    @Override
    public TaotaoResult checkDate(String param, int type) {
        // 从tb_user表中查询数据
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        // 查询条件根据参数动态生成
        // 1,2,3 分别代表 username,phone,email
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return TaotaoResult.build(400, "非法数据");
        }
        // 执行查询
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        // 判断查询出来的结果,没有结果就返回true,否则返回false
        if (list == null || list.size() == 0) {
            // 如果没有返回true
            return TaotaoResult.ok(true);
        }
        // 使用TaotaoResult包装进行返回
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        // 判断是否符合需求
        if (StringUtils.isBlank(user.getUsername())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        // 检验用户名是否可用
        TaotaoResult result = checkDate(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "此用户名已经被占用了");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        // 检测电话是否为空
        if (StringUtils.isBlank(user.getPhone())) {
            return TaotaoResult.build(400, "手机号码为空");
        }
        TaotaoResult phoneResult = checkDate(user.getPhone(), 2);
        if (!(boolean) phoneResult.getData()) {
            return TaotaoResult.build(400, "手机号码被占用");
        }
        // 检查email是否可用
        if (StringUtils.isBlank(user.getEmail())) {
            return TaotaoResult.build(400, "邮箱地址被占用");
        }
        TaotaoResult emailResult = checkDate(user.getEmail(), 3);
        if (!(boolean) emailResult.getData()) {
            return TaotaoResult.build(400, "邮箱被占用");
        }
        // 补全属性
        user.setUpdated(new Date());
        user.setCreated(new Date());
        // 对密码进行加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        // 插入到数据库
        tbUserMapper.insert(user);
        // 返回TaotaoResult
        return TaotaoResult.ok();
    }
}























