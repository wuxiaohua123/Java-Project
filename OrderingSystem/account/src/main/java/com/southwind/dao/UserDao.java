package com.southwind.dao;

import com.southwind.entity.User;

public interface UserDao {
    /**
     * 普通用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);
}