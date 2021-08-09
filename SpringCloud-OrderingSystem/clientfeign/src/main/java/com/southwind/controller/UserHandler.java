package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.feign.OrderFeign;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserFeign userFeign;

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
     * 分页查询用户
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return userFeign.findAll(index, limit);
    }

    /**
     * 新增用户--页面
     */
    @GetMapping("/add")
    public String add(){
        return "redirect:/user/redirect/user_add";
    }

    /**
     * 新增用户--逻辑
     * @param user
     * @return
     */
    @PostMapping("/save")
    public String save(User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/user/redirect/user_manage";
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        //1.首先删除此用户下的所有订单
        orderFeign.deleteByUid(id);

        //2.删除用户
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manage";
    }
}