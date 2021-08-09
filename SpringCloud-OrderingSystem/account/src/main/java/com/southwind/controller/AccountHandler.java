package com.southwind.controller;

import com.southwind.dao.AdminDao;
import com.southwind.dao.UserDao;
import com.southwind.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountHandler {
    @Value("${server.port}")
    private String port;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @GetMapping("/index")
    public String index(){
        return "account的端口："+this.port;
    }

    /**
     * 登录
     * 1.使用多态方式解决用户和管理员不同的角色登录
     * @param username
     * @param password
     * @param type 登录身份
     * @return
     */
    @GetMapping("/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type){
        Account account = null;
        switch (type){
            case "user":
                account = userDao.login(username, password);
                break;
            case "admin":
                account = adminDao.login(username, password);
                break;
        }
        return account;
    }
}