package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVO;
import com.southwind.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private UserDao userDao;

    @GetMapping("/index")
    public String index(){
        return "user的端口："+this.port;
    }

    /**
     * 分页查询用户
     * @param index
     * @param limit
     * @return
     */
    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userDao.count());
        userVO.setData(userDao.findAll(index,limit));
        return userVO;
    }

    /**
     * 保存用户
     * @param user
     */
    @PostMapping("/save")
    public void save(@RequestBody User user){
        user.setRegisterdate(new Date());
        userDao.save(user);
    }

    /**
     * 根据ID删除用户
     * @param id
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userDao.deleteById(id);
    }
}