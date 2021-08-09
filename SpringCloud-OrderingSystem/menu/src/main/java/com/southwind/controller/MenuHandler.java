package com.southwind.controller;

import com.southwind.dao.MenuDao;
import com.southwind.dao.TypeDao;
import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 功能：服务提供者menu的controller
 * 说明：对外开放RESTful接口,只做menu业务,不做跳转
 */
@RestController
@RequestMapping("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private TypeDao typeDao;

    @GetMapping("/index")
    public String index(){
        return "menu的端口："+this.port;
    }

    /**
     * 分页查询所有菜单
     * @param index 从？索引开始显示
     * @param limit 一页显示？条数据
     * @return
     */
    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Menu> list = menuDao.findAll(index,limit);

        MenuVO menuVO = new MenuVO();
        menuVO.setCode(0);
        menuVO.setMsg("");
        menuVO.setCount(menuDao.count());
        menuVO.setData(list);
        return menuVO;
    }

    /**
     * 根据ID删除菜单
     * @param id
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuDao.deleteById(id);
    }

    /**
     * 查找所有菜品
     * @return
     */
    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeDao.findAll();
    }

    /**
     * 新增菜单
     * @param menu
     */
    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menuDao.save(menu);
    }

    /**
     * 根据ID查找菜单
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
       return menuDao.findById(id);
    }

    /**
     * 更新菜单
     * @param menu
     */
    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuDao.update(menu);
    }
}