package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.Order;
import com.southwind.entity.OrderVO;
import com.southwind.entity.User;
import com.southwind.entity.Admin;
import com.southwind.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private OrderFeign orderFeign;

    /**
     * 功能：向页面跳转
     */
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    /**
     * 1.新增订单
     * @param mid
     * @param session
     * @return
     */
    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") long mid, HttpSession session){
        Order order = new Order();
        //User
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        //Menu
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        //Date
        order.setDate(new Date());

        orderFeign.save(order);
        return "redirect:/order/redirect/order";
    }

    /**
     * 2.分页查询用户订单
     * @param session
     * @return
     */
    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        User user = (User) session.getAttribute("user");
        int index = (page-1)*limit;
        return orderFeign.findAllByUid(user.getId(), index, limit);
    }

    /**
     * 6.根据订单状态查询订单
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return orderFeign.findAllByState(0, page, limit);
    }

    /**
     * 8.修改订单状态
     * @param id
     * @param state
     * @param session
     * @return
     */
    @GetMapping("/updateState/{id}/{state}")
    public String updateState(@PathVariable("id") long id,@PathVariable("state") int state,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        orderFeign.updateState(id,state,admin.getId());
        return "redirect:/menu/redirect/order_handler";
    }
}