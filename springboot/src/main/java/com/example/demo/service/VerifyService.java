package com.example.demo.service;

import com.example.demo.entity.resp.RestBean;

public interface VerifyService {
    public void sendVerifyCode(String email);

    public boolean doVerify(String email, String code);
}
