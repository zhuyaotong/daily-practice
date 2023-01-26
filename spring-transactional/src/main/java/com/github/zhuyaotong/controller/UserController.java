package com.github.zhuyaotong.controller;

import com.github.zhuyaotong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("wrong1")
    public int wrong1(@RequestParam("name") String name) {
        try {
            return userService.createUserRight1(name);
        } catch (IOException e) {
            System.out.println("创建用户失败：" + e.getMessage());
        }
        return -1;
    }

}
