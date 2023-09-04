package com.example.demo.service.Impl;

import com.example.demo.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    String mail;

    @Override
    public void sendVerifyCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("[xx网站] 您的验证码");
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        redisTemplate.opsForValue().set("verify:code:" + email, code + "", 3, TimeUnit.MINUTES);
        message.setText("您的验证码为：" + code + ",三分钟内有效，请及时完成注册!如果不是本人操作,请忽略");
        message.setTo(email);
        message.setFrom(mail);
        sender.send(message);
    }

    @Override
    public boolean doVerify(String email, String code) {
        String string = redisTemplate.opsForValue().get("verify:code:" + mail);
        if (string == null) return false;
        return string.equals(code);
    }
}
