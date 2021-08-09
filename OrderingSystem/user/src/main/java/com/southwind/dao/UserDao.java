package com.southwind.dao;

import com.southwind.entity.User;
import java.util.List;

public interface UserDao {
    /**
     * 分页查询用户
     * @param index
     * @param limit
     * @return
     */
    public List<User> findAll(int index, int limit);

    /**
     * 查询用户数量
     * @return
     */
    public int count();

    /**
     * 保存用户
     * @return
     */
    public void save(User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    public void deleteById(long id);
}