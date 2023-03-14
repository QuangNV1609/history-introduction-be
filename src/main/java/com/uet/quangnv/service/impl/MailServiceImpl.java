package com.uet.quangnv.service.impl;

import com.uet.quangnv.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Integer sendCode(String email) {
        Integer code = new Random().nextInt(100000) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Code");
        message.setText("Mã xác thực của bạn là: " + code);
        emailSender.send(message);
        return null;
    }
}
