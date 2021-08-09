package com.southwind.dao;

import com.southwind.entity.Type;

import java.util.List;

public interface TypeDao {
    /**
     * 根据ID查类型
     */
    public Type findById();

    /**
     * 查找所有菜品
     */
    public List<Type> findAll();
}