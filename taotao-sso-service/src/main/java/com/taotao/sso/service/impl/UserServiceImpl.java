package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.jedis.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户处理Service实现类
 * Created by 孙建荣 on 17-6-18.下午4:53
 */
@org.apache.dubbo.config.annotation.Service(version = "${sso.service.version}",timeout = 300000)
public class UserServiceImpl implements UserService {
    private final JedisClient jedisClient;
    private final TbUserMapper tbUserMapper;

    @Value("${USER_SESSION}")
    private String USER_SESSION;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    public UserServiceImpl(TbUserMapper tbUserMapper, JedisClient jedisClient) {
        this.tbUserMapper = tbUserMapper;
        this.jedisClient = jedisClient;
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

    @Override
    public TaotaoResult login(String username, String password) {
        // 首先判断用户密码是否正确
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        // 查询用户信息
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if (list == null || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或者面膜错误");
        }
        TbUser user = list.get(0);
        // 效验密码
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return TaotaoResult.build(400, "用户名或者密码错误");
        }
        // 登录成功后把生成token,Token相当于jsessionid,字符串，可以使用uuid
        String token = UUID.randomUUID().toString();
        // 把用户信息保存到redis,Key就是token,value就是Tbuser对象转成json
        // 使用String类型保存session信息,可以使用 "前缀:token"为key
        user.setPassword(null);
        jedisClient.set(USER_SESSION + ":" + token, JsonUtils.objectToJson(user));
        // 设置key的过期时间,模拟Session的过期时间,一般为半个小时
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        // 返回TaotaoResult包装token
        return TaotaoResult.ok(token);
    }

    /**
     * 通过token查询用户信息
     * @param token  token令牌值
     * @return   用户信息
     */
    @Override
    public TaotaoResult getUserByToken(String token) {
        // 根据token查询redis
        String json = jedisClient.get(USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            // 如果查询不到数据,就说明用户已经过期
            return TaotaoResult.build(400, "用户登录已经过期,请重新登录");
        }
        // 如果查询到数据,说明用户已经登录了
        // 如要需要重置key的过期时间
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        // 把json数据转为TbUser对象,然后使用TaotaoResult包装返回
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return TaotaoResult.ok(user);
    }
}























