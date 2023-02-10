package com.hukaichao.webdisk.controller;

import com.alibaba.fastjson.JSONObject;
import com.hukaichao.webdisk.model.User;
import com.hukaichao.webdisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (userService.checkUser(user)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "登录成功");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "用户名或密码错误");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/register")
    public String register(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (userService.addUser(user)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "注册成功");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "用户名已存在");
        }
        return jsonObject.toJSONString();
    }
}
