package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.feign.MenuFeign;
import com.southwind.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能：客户端对应的controller层
 * 说明：浏览器发送的URL请求会转到这里
 */
@Controller
@RequestMapping("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuFeign menuFeign;

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        return "client的端口："+this.port;
    }

    /**
     * 功能：查找所有菜单
     * @param page 从？页开始显示
     * @param limit 一页显示？条数据
     */
    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page,@RequestParam("limit") int limit){ //此处用RequestParam取参是因为前端用的是原始的传参方法,而不是RESTful风格
        int index = (page-1)*limit; //从哪个索引开始显示
        return menuFeign.findAll(index,limit);
    }

    /**
     * 功能：负责所有的向页面跳转（重定向）
     */
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    /**
     * 根据ID删除菜单
     * @param id
     * @return 重定向到主页面
     */
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        //1.首先删除此菜单下的所有订单
        orderFeign.deleteByMid(id);

        //2.删除菜单
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage";
    }

    /**
     * 新增菜单--页面
     * 1.首先获得所有菜品
     */
    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list",menuFeign.findTypes());
        return modelAndView;
    }

    /**
     * 新增菜单--逻辑
     * @param menu
     */
    @PostMapping("/save")
    public String save(Menu menu){
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    /**
     * 更新菜单--页面
     * 1.先根据ID查出菜单
     * 2.获得所有菜品
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu",menuFeign.findById(id));
        modelAndView.addObject("list",menuFeign.findTypes());
        return modelAndView;
    }

    /**
     * 更新菜单--逻辑
     * @param menu
     * @return
     */
    @PostMapping("/update")
    public String update(Menu menu){
        menuFeign.update(menu);
        return "redirect:/menu/redirect/menu_manage";
    }
}