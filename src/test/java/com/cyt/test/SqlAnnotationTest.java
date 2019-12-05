package com.cyt.test;

import com.cyt.dao.IAccountDao_annotation;
import com.cyt.dao.IUserDao_annotation;
import com.cyt.domain.Account;
import com.cyt.domain.User;
import com.cyt.domain.User1;
import com.cyt.domain.User2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class SqlAnnotationTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao_annotation userDao;
    private IAccountDao_annotation accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig_annotation.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao_annotation.class);
        accountDao = sqlSession.getMapper(IAccountDao_annotation.class);
    }
    @After
    public void desTroy() throws Exception{
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{
        List<User1> users = userDao.findAll();
        for (User1 u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void tesFindUsersByName() throws Exception{
        List<User1> users = userDao.findByName("陈玉婷");
        for (User1 u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindAllUserAccount(){
        List<User2> users = userDao.findAllUserAccount();
        for (User2 u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testSaveUser() throws Exception{
        User user = new User();
        user.setUsername("陈玉婷_annotationInsert");
        user.setAddress("天津财经大学~~~.....................");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
    }
    @Test
    public void testUpdateUser() throws Exception{
        int id = 64;
        User user = userDao.findUserById(id);
        user.setAddress(user.getAddress() + "update");
        userDao.updateUser(user);
    }
    @Test
    public void testDeleteUser() throws Exception{
        userDao.deleteUser(65);
    }

    @Test
    public void testFindAllUsers(){
        List<Account> accounts = accountDao.findAll();
        for (Account a : accounts) {
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }

}
