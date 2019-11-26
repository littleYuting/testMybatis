package com.cyt.test;

import com.cyt.dao.IUserDao;
import com.cyt.dao.impl.UserDaoImpl;
import com.cyt.domain.QueryVo;
import com.cyt.domain.User;
import com.cyt.domain.User1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSQLTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("DynamicSqlMapConfig_CURD.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void desTroy() throws Exception{
        sqlSession.close();
        in.close();
    }
    @Test
    public void testMapLabel(){
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("username","陈玉婷1_mapTest");
        userMap.put("sex","女");
        userMap.put("address","杭州1_mapTest");
        userDao.testMapLabel(userMap);
    }
    @Test
    public void testTrimLabel(){
        // trim : 格式化标签，可以完成 <set> 或 <where> 标记的功能，通常用于去掉第一个and或or， 最后一个逗号或其他标记
        // where or set 标签本身支持一定的容错处理
    }
    @Test
    public void testSetLabel(){
        User user = new User();
        user.setId(42);
        user.setAddress("Beijing");
        user.setUsername("cyt");
        userDao.testSetLabel(user);
    }
    @Test
    public void testChooseLabel(){
        // choose when otherwise 搭配使用，使用同 switch case，条件判断的顺序与sql语句有关，而与赋值先后无关
        User user = new User();
//        user.setUsername("陈玉婷");
        user.setAddress("北京");
        user.setSex("男");
        List<User> users = userDao.testChooseLabel(user);
        for (User u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindUserInIds(){
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(42);
        ids.add(46);
        ids.add(54);
        ids.add(59);
        QueryVo queryVo = new QueryVo();
        queryVo.setIds(ids);
        List<User> users = userDao.findUserInIds(queryVo);
        for (User u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindByCondition(){
        User1 user1 = new User1();
        user1.setUserName("老王");
        user1.setUserSex("男");
        List<User1> user1s = userDao.findUserByCondition(user1);
        for (User1 u : user1s) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindAll() throws Exception{
        List<User> users = userDao.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindById() throws Exception{
        User user = userDao.findUserById(48);
        System.out.println(user);
    }
    @Test
    public void tesFindUsersByName() throws Exception{
//        List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println(userDao.findTotal());
    }
    @Test
    public void testFindByVo(){
        User user = userDao.findUserById(41);
        user.setUsername("%王%");
        QueryVo queryVo = new QueryVo();
        queryVo.setUser(user);
        List<User> users = userDao.findUserByVo(queryVo);
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println(userDao.findTotal());
    }
}
