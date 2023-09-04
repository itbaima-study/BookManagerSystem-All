package com.example.demo.controller;

import com.example.demo.entity.resp.RestBean;
import com.example.demo.service.AccountService;
import com.example.demo.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/verify-code")
    public RestBean<Void> verifyCode(@RequestParam("email") String email) {
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200, "邮箱发送成功!");
        } catch (Exception e) {
            return new RestBean<>(500, "邮箱发送失败!");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestBean<Void> register(String username, String password, String email, String verify) {
        if (verifyService.doVerify(email, verify)) {
            accountService.createAccount(username, password);
            return new RestBean<>(200, "注册成功!");
        } else {
            return new RestBean<>(403, "注册失败,验证码填写错误！");
        }
    }

    @PostMapping("/login-success")
    public RestBean<Void> loginSuccess() {
        return new RestBean<>(200, "退出成功");
    }

    @GetMapping("/logout-success")
    public RestBean<Void> logoutSuccess() {
        return new RestBean<>(200, "登录成功");
    }

    @PostMapping("/login-failure")
    public RestBean<Void> loginFailure() {
        return new RestBean<>(403, "登录失败,用户名或密码错误!");
    }

    @GetMapping("/access-deny")
    public RestBean<Void> accessDeny() {
        return new RestBean<>(401, "未验证,请先进行登录");
    }


}
