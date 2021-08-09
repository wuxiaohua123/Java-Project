package com.southwind.feign;

import com.southwind.entity.UserVO;
import com.southwind.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user")
public interface UserFeign {
    /**
     * 分页查询用户
     * @param index
     * @param limit
     * @return
     */
    @GetMapping("/user/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    /**
     * 保存用户
     * @param user
     */
    @PostMapping("/user/save")
    public void save(@RequestBody User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    @DeleteMapping("/user/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);
}
