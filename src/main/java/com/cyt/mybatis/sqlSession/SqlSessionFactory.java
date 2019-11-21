package com.cyt.mybatis.sqlSession;

import com.cyt.mybatis.sqlSession.SqlSession;

public interface SqlSessionFactory {
    /**
     *  用于打开一个新的 session 对象
     * @return
     */
    SqlSession openSession();
}
