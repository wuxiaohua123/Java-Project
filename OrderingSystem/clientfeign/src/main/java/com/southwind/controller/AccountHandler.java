package com.southwind.controller;

import com.southwind.entity.Account;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import com.southwind.uitls.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountHandler {
    @Autowired
    private AccountFeign accountFeign;

    /**
     * 登录
     * 1.后台创建Session再给前端
     * @param username
     * @param password
     * @param type 角色类型
     * @param session 会话信息
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        //此处拿到的是HashMap类型，并不是Account对象
        Account account = accountFeign.login(username,password,type);

        String result = null;
        if(account == null){    //登录失败
            result = "login";
        } else {
            switch (type){
                case "user":    //用户角色
                    User user = convertUser(account);
                    session.setAttribute("user",user);
                    result = "redirect:/account/redirect/home_user";
                    break;
                case "admin":   //管理员角色
                    Admin admin = convertAdmin(account);
                    session.setAttribute("admin",admin);
                    result = "redirect:/account/redirect/index";
                    break;
            }
        }
        return result;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 页面跳转
     * @param result
     * @return
     */
    @RequestMapping("/redirect/{result}")
    public String redirect(@PathVariable("result") String result){
        return result;
    }

    /**
     * 将HashMap类型的account变量   转为  User对象
     * @param account
     * @return
     */
    private User convertUser(Account account){
        User user = new User();
        user.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
        user.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
        user.setGender(ReflectUtils.getFieldValue(account,"gender")+"");
        user.setId((long)(ReflectUtils.getFieldValue(account,"id")));
        user.setNickname(ReflectUtils.getFieldValue(account,"nickname")+"");
        user.setRegisterdate((Date)(ReflectUtils.getFieldValue(account,"registerdate")));
        user.setTelephone(ReflectUtils.getFieldValue(account,"telephone")+"");
        return user;
    }

    /**
     * 将HashMap类型的account变量   转为  Admin对象
     * @param account
     * @return
     */
    private Admin convertAdmin(Account account){
        Admin admin = new Admin();
        admin.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
        admin.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
        admin.setId((long)(ReflectUtils.getFieldValue(account,"id")));
        return admin;
    }
}