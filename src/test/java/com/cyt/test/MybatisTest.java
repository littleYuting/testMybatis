package com.cyt.test;

import com.cyt.dao.IUserDao;
import com.cyt.dao.impl.UserDaoImpl;
import com.cyt.domain.QueryVo;
import com.cyt.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import com.cyt.mybatis.sqlSession.SqlSession;
//import com.cyt.mybatis.io.Resources;
//import com.cyt.mybatis.sqlSession.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig_CURD.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);//此处的true是设置自动提交
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After//用于在测试方法之后执行
    public void desTroy() throws Exception{
//        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
    public void testFindAll() throws Exception{
        List<User> users = userDao.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
    @Test
    public void testSaveUser() throws Exception{

        User user = new User();
        user.setUsername("陈玉婷_last_insert_id");
        user.setAddress("天津财经大学");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);
//        sqlSession.commit();//在没加这句话之前，由于已经生成好的新的id，但是都回滚啦，所以最终保存的那条记录的 id 跟之前不是增1关系；
    }
    @Test
    public void testUpdateUser() throws Exception{
        User user = userDao.findUserById(54);
        user.setUsername(user.getUsername()+"_update");
        userDao.updateUser(user);
    }
    @Test
    public void testDeleteUser() throws Exception{
        userDao.deleteUser(43);
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
//    @Test
//    public void testDaoMapper() throws Exception{
//        //1. 读取配置文件
//        // 注意：读取文件的时候，不要采用绝对或相对路径，可以使用类加载器或ServletContext对象的getRealPath()
//        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        //2. 创建 SqlSessionFactory 工厂【创建者模式】
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        //3. 使用工厂生产 SqlSession 对象【工厂模式】
//        SqlSession session = (SqlSession) factory.openSession();
//        //4. 使用 SqlSession 创建的 Dao 接口的代理对象，代理 dao 实现类通过 mapper 映射到具体数据操作 dao【代理模式】
//        IUserDao userDao = session.getMapper(IUserDao.class);
//        //5. 使用代理对象执行方法
//        List<User> users = userDao.findAll();
//        for (User user : users) {
//            System.out.println(user);
//        }
//        //6. 释放资源
//        session.close();
//        in.close();
//        /** 使用 sqlSession 创建 Dao 接口的代理对象 IUserDao userDao = session.getMapper(IUserDao.class);
//         * 根据 dao 接口的字节码创建 dao 的代理对象
//         * public <T> T getMapper(Class<T> daoInterfaceClass) {
//         *      // 类加载器：它使用的和被代理对象是相同的类加载器；
//         *      // 代理对象要实现的接口：和被代理对象实现相同的接口；
//         *      // 如何代理：它就是增强的方法，需人为提供，此处是一个 InvocationHandler 的接口，需写一个该接口的实现类，在实现类中调用 selectList 方法
//         *     Proxy.newProxyInstance(类加载器，代理对象要实现的接口字节码数组，如何代理)
//         * }
//         */
//        /** 自定义 Mybatis 的分析：
//         * 1. Mybatis 在使用代理 dao 的方式实现增删改查时的步骤：
//         *      1） 创建代理对象
//         *      2） 在代理对象中调用 selectList
//         * 2. Mybatis 中的类分析
//         *      1) Class Resources
//         *      2）Class SqlSessionFactoryBuilder
//         *      3) Interface SqlSessionFactory
//         *      4) Interface SqlSession
//         */
//    }
    @Test
    public void testDaoImpl(){
        // mybatis 支持 dao 接口的实现，实际开发中不采用，而是用代理+mapper的方式
        try {
            //1. 读取配置文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2. 创建 SqlSessionFactory 工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            //3. 使用工厂创建 Dao 对象
            IUserDao userDao = new UserDaoImpl(factory);
            //5. 使用 dao 的实现对象执行方法
            List<User> users = userDao.findAll();
            for (User user : users) {
                System.out.println(user);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
