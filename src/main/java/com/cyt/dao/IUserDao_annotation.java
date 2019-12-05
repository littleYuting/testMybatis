package com.cyt.dao;

import com.cyt.domain.QueryVo;
import com.cyt.domain.User;
import com.cyt.domain.User1;
import com.cyt.domain.User2;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;


/**
 *  mybatis 面向注解开发中有一个小细节：当使用注解时，同 Resource下 com.cyt.dao 路径下不能建有与其同名的 xml，否则会报错无法识别
 */
public interface IUserDao_annotation {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id="userMap",
            value = {
            @Result(id=true, column = "id", property = "userId"),
            @Result(id=true, column = "username", property = "userName"),
            @Result(id=true, column = "address", property = "userAddress"),
            @Result(id=true, column = "sex", property = "userSex"),
            @Result(id=true, column = "birthday", property = "userBirthday")
    })
    List<User1> findAll();
    /**
     * 查询所有用户，一对多的注解查询
     * @return
     */
    @Select("select * from user")
    @Results(value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "address", property = "address"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
            @Result(property = "accounts", column = "id", many = @Many(select = "com.cyt.dao.IAccountDao_annotation.findAccountById",fetchType = FetchType.LAZY))
            })
    List<User2> findAllUserAccount();
    /**
     * 查询单个用户
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findUserById(Integer id);

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username, address, sex, birthday) values (#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 根据 id 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    /**
     * 根据名称查找用户
     * @param name
     * @return
     */
    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = "userMap")
    List<User1> findByName(String name);

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
}
