package com.cyt.dao;

import com.cyt.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IAccountDao_annotation {
    /**
     * 查询所有用户，使用注解实现 多表连接中 一对一的映射关系
     * @return
     */

    @Results(id="accountMap",
            value = {
           @Result(id=true,property = "id",column = "id"),
           @Result(property = "uid",column = "uid"),
           @Result(property = "money",column = "money"),
           @Result(property = "user",column = "uid",one = @One(select = "com.cyt.dao.IUserDao_annotation.findUserById",fetchType = FetchType.EAGER))
           //one 表示一对一的映射关系，fetchType.eager 是及时加载
            })
    @Select("select * from account")
    List<Account> findAll();

    /**
     * 查询所有用户(延迟缓存)
     * @return
     */
    List<Account> findAll_testLazy();

    /**
     * 查询指定用户 id 下的所有账户
     * @return
     */
    @Select("select * from account where uid=#{uid}")
    @Results(value = {
                    @Result(id=true,property = "id",column = "id"),
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "money",column = "money")})
    List<Account> findAccountById(Integer uid);

}
