package com.taotao.sso.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis config
 * @author sunjianrong
 * @date 19-2-23 下午2:23
 */
@Configuration
@MapperScan({"com.taotao.mapper"})
public class MybatisConfig {

}
