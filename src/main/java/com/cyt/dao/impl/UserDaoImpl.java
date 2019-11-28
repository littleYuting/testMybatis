package com.cyt.dao.impl;

import com.cyt.dao.IUserDao;
import com.cyt.domain.QueryVo;
import com.cyt.domain.User;
import com.cyt.domain.User1;
import com.cyt.domain.User2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        // 1. 使用工厂创建 SqlSession 对象
        SqlSession session = factory.openSession();
        // 2. 使用 session
        List<User> users = session.selectList("com.cyt.dao.IUserDao.findAll");// 必须具体到包路径和方法名
        session.close();
        // 3. 返回查询结果
        return users;
        /** selectList 方法
         * 1. 根据配置文件的信息创建 Connection 对象：注册驱动，获取连接；（读取配置文件所用的技术就是解析 xml 文件的技术，此处为 dom4j）
         * 2. 获取预处理对象 PreparedStatement； conn.prepareStatement(sql)
         * 3. 执行查询：ResultSet resultSet = preparedStatement.executeQuery();
         * 4. 遍历结果集用于封装
         *      List<E> list = new ArrayList();
         *      while(resultSet.hasNext()){
         *          E element = (E)Class.forName(配置的全限定类名).newInstance()
         *          进行封装，把每个 rs 的内容都添加到 element 中，由于实体类属性和表中的列名
         *          一致，所以可以把表的列名看成是实体类的属性名称，使用反射的方式来根据名称获取
         *          每个属性，并把值赋进去
         *          list.add(element);
         *      }
         * 5. 返回 list；
         */
        /** 实现 selectList 方法需提供的信息：
         * 1. 连接信息
         * 2. 映射信息：
         *      1） 执行的 SQL 语句；
         *      2）封装结果的实体类全限定类名；
         * 把这两个信息组合起来定义成一个对象 Mapper, 最终返回一个结果 map ，key:com.cyt.dao.IUserDao.findAll; value(Mapper): String domainClassPath,String sql
         */
    }

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public Integer findTotal() {
        return null;
    }

    @Override
    public List<User> findUserByVo(QueryVo vo) {
        return null;
    }

    @Override
    public List<User1> findUserByCondition(User1 user) {
        return null;
    }

    @Override
    public List<User> findUserInIds(QueryVo vo) {
        return null;
    }

    @Override
    public List<User> testChooseLabel(User user) {
        return null;
    }

    @Override
    public void testSetLabel(User user) {
    }

    @Override
    public void testMapLabel(@Param(value = "userMaps") Map<String, Object> userMap) {

    }

    @Override
    public List<User2> findUserAccounts() {
        return null;
    }

}
