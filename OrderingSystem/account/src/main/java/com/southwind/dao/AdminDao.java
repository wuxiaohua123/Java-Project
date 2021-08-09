package com.southwind.dao;

import com.southwind.entity.Admin;

public interface AdminDao {
    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    public Admin login(String username, String password);
}