package com.cyt.mybatis.sqlSession;

import com.cyt.mybatis.cfg.Configuration;
import com.cyt.mybatis.sqlSession.defaults.DefaultSqlSessionFactory;
import com.cyt.mybatis.utils.XMLConfigBuilder;
import java.io.InputStream;


/**
 *  用于创建一个 SqlSessionFactory 对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来创建一个 SqlSessionFactory 工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
