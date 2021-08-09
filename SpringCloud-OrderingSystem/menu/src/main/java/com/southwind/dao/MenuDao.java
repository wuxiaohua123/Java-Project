package com.southwind.dao;

import com.southwind.entity.Menu;
import java.util.List;

public interface MenuDao {
    /**
     * 分页查询所有菜单
     */
    public List<Menu> findAll(int index,int limit);

    /**
     * 查询菜单总数
     */
    public int count();

    /**
     * 根据ID查询菜单
     */
    public Menu findById(long id);

    /**
     * 新增菜单
     */
    public void save(Menu menu);

    /**
     * 更新菜单
     */
    public void update(Menu menu);

    /**
     * 根据ID删除菜单
     */
    public void deleteById(long id);
}