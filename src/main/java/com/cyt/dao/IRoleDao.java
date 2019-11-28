package com.cyt.dao;

import com.cyt.domain.Role;
import com.cyt.domain.User;

import java.util.List;


public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 查询所有角色及角色所对应的用户
     * @return
     */
    List<Role> findRoleUsers();

    /**
     * 查询所有用户及用户对应的角色
     * @return
     */
    List<Role> findUserRoles();

}
