package com.cyt.dao;

import com.cyt.domain.User;
//import org.apache.ibatis.annotations.Select;
import com.cyt.mybatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
