package com.southwind.dao;

import com.southwind.entity.Order;
import java.util.List;

public interface OrderDao {
    /**
     * 1.新增订单
     */
    public void save(Order order);

    /**
     * 2.分页查询用户订单
     */
    public List<Order> findAllByUid(long uid,int index,int limit);

    /**
     * 3.查询用户订单总数
     */
    public int countByUid(long uid);

    /**
     * 4.删除某菜单下所有订单
     */
    public void deleteByMid(long mid);

    /**
     * 5.删除某用户下所有订单
     */
    public void deleteByUid(long uid);

    /**
     * 6.根据订单状态查询订单
     */
    public List<Order> findAllByState(int state,int index,int limit);

    /**
     * 7.查询某状态的订单总数
     */
    public int countByState(int state);

    /**
     * 8.修改订单状态
     */
    public void updateState(long id,long aid,int state);
}
