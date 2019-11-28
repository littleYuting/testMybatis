package com.cyt.dao;

import com.cyt.domain.Account;
import com.cyt.domain.AccountUser;

import java.util.List;


public interface IAccountDao {
    /**
     * 查询所有用户
     * @return
     */
    List<Account> findAll();

    /**
     * 插叙所有用户，包括user表和account表信息
     * @return
     */
    List<AccountUser> findAllUsers();


}
