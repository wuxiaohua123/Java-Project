package com.southwind.controller;

import com.southwind.dao.OrderDao;
import com.southwind.entity.OrderVO;
import com.southwind.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/index")
    public String index(){
        return "order的端口："+this.port;
    }

    /**
     * 1.新增订单
     * @param order
     */
    @PostMapping("/save")
    public void save(@RequestBody Order order){
        orderDao.save(order);
    }

    /**
     * 2.分页查询用户订单
     * @param uid
     * @param index
     * @param limit
     * @return
     */
    @GetMapping("/findAllByUid/{uid}/{index}/{limit}")
    public OrderVO findAllByUid(@PathVariable("uid") long uid, @PathVariable("index") int index, @PathVariable("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderDao.countByUid(uid));
        orderVO.setData(orderDao.findAllByUid(uid,index,limit));
        return orderVO;
    }

    /**
     * 4.删除某菜单下所有订单
     * @param mid
     */
    @DeleteMapping("/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable("mid") long mid){
        orderDao.deleteByMid(mid);
    }

    /**
     * 5.删除某用户下所有订单
     * @param uid
     */
    @DeleteMapping("/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable("uid") long uid){
        orderDao.deleteByUid(uid);
    }

    /**
     * 6.根据订单状态查询订单
     * @param state
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAllByState/{state}/{page}/{limit}")
    public OrderVO findAllByState(@PathVariable("state") int state, @PathVariable("page") int page, @PathVariable("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderDao.countByState(0));
        orderVO.setData(orderDao.findAllByState(0,(page-1)*limit,limit));
        return orderVO;
    }

    /**
     * 8.修改订单状态
     * @param id
     * @param state
     * @param aid
     */
    @PutMapping("/updateState/{id}/{state}/{aid}")
    public void updateState(@PathVariable("id") long id, @PathVariable("state") int state, @PathVariable("aid") long aid){
        orderDao.updateState(id,aid,state);
    }
}