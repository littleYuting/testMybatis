package com.cyt.test;

import com.cyt.dao.IAccountDao;
import com.cyt.dao.IUserDao;
import com.cyt.dao.IRoleDao;
import com.cyt.domain.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.List;


public class MultiTableRelationTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;
    private IUserDao userDao;
    private IRoleDao roleDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("multiTableConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(IAccountDao.class);
        userDao = sqlSession.getMapper(IUserDao.class);
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }
    @After
    public void desTroy() throws Exception{
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAllRoles(){
        List<Role> roles = roleDao.findAllRoles();
        for (Role r:roles) {
            System.out.println(r);
        }
    }
    @Test
    public void testFindRoleUsers(){
        List<Role> roles = roleDao.findRoleUsers();
        for (Role r:roles) {
            System.out.println(r);
        }
    }
    @Test
    public void testFindUserRoles(){
        List<Role> roles = roleDao.findUserRoles();
        for (Role r:roles) {
            System.out.println(r);
        }
    }
    @Test
    public void testFindUserAccounts(){
        List<User2> users = userDao.findUserAccounts();
        for (User2 u : users) {
            System.out.println(u);
            System.out.println(u.getAccounts());
        }
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.findAll();
        for (Account a : accounts) {
            System.out.println("每个 account 的信息：");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
    @Test
    public void testFindAllUsers(){
        List<AccountUser> accounts = accountDao.findAllUsers();
        for (AccountUser a : accounts) {
            System.out.println(a);
        }
    }

}
