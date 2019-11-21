package com.cyt.mybatis.sqlSession.defaults;

import com.cyt.mybatis.cfg.Configuration;
import com.cyt.mybatis.sqlSession.SqlSession;
import com.cyt.mybatis.sqlSession.SqlSessionFactory;

/**
 * SqlSessionFactory 接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }
    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
