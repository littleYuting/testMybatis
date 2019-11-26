package com.cyt.dao;

import com.cyt.domain.QueryVo;
import com.cyt.domain.User;
import com.cyt.domain.User1;
//import org.apache.ibatis.annotations.Select;
import com.cyt.mybatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 查询单个用户
     * @return
     */
    User findUserById(Integer id);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据 id 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据名称查找用户
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 查询记录总数
     * @return
     */
    Integer findTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件
     * @return
     */
    List<User1> findUserByCondition(User1 user);

    /**
     * 根据 queryVo 中提供的 id 集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

    /**
     * test choose label
     * @param user
     * @return
     */
    List<User> testChooseLabel(User user);

    /**
     * test set label
     * @param user
     * @return
     */
    void testSetLabel(User user);

    /**
     * test trim label
     * @param userMap
     * @return
     */
    void testMapLabel(@Param(value = "userMaps") Map<String, Object> userMap);
}
