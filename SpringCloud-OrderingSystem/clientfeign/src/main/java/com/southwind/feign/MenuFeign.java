package com.southwind.feign;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

/**
 * 功能：接口
 * 说明：此处接口映射到服务提供者
 */
@FeignClient(value = "menu")
public interface MenuFeign {
    /**
     * 功能：分页查询所有菜单
     * @param index 从？索引开始显示
     * @param limit 一页显示？条数据
     */
    @GetMapping("/menu/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    /**
     * 功能：根据ID删除菜单
     * @param id
     */
    @DeleteMapping("/menu/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    /**
     * 查找所有菜品
     */
    @GetMapping("/menu/findTypes")
    public List<Type> findTypes();

    /**
     * 更新菜单
     * 1.RequestBody注解将JSON对象转换为Java对象，否则Java对象中没数据
     * 2.RequestBody注解用于微服务之间的JSON数据传递
     * @param menu
     */
    @PostMapping("/menu/save")
    public void save(@RequestBody Menu menu);

    /**
     * 根据ID查找菜单
     * @param id
     * @return
     */
    @GetMapping("/menu/findById/{id}")
    public Menu findById(@PathVariable("id") long id);

    /**
     * 更新菜单
     * @param menu
     */
    @PutMapping("/menu/update")
    public void update(@RequestBody Menu menu);
}