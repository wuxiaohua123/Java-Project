package com.southwind.feign;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order")
public interface OrderFeign {
    /**
     * 1.新增订单
     * @param order
     */
    @PostMapping("/order/save")
    public void save(@RequestBody Order order);

    /**
     * 2.分页查询用户订单
     * @param uid
     * @param index
     * @param limit
     * @return
     */
    @GetMapping("/order/findAllByUid/{uid}/{index}/{limit}")
    public OrderVO findAllByUid(@PathVariable("uid") long uid, @PathVariable("index") int index, @PathVariable("limit") int limit);

    /**
     * 4.删除某菜单下所有订单
     * @param mid
     */
    @DeleteMapping("/order/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable("mid") long mid);

    /**
     * 5.删除某用户下所有订单
     * @param uid
     */
    @DeleteMapping("/order/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable("uid") long uid);

    /**
     * 6.根据订单状态查询订单
     * @param state
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/order/findAllByState/{state}/{page}/{limit}")
    public OrderVO findAllByState(@PathVariable("state") int state, @PathVariable("page") int page, @PathVariable("limit") int limit);

    /**
     * 8.修改订单状态
     * @param id
     * @param state
     * @param aid
     */
    @PutMapping("/order/updateState/{id}/{state}/{aid}")
    public void updateState(@PathVariable("id") long id, @PathVariable("state") long state,@PathVariable("aid") long aid);
}