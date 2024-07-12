package com.globallogic.service.impl;

import com.globallogic.model.EmailRequest;
import com.globallogic.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest emailRequest) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailRequest.getTo());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getBody());
        mailSender.send(mailMessage);
    }
}
