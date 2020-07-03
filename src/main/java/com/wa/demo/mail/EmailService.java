package com.wa.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * EmailService
 * 2020-07-03 10:25
 *
 * @author wuao
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendMail(String sender, String title, String context, String... receiver) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(title);
        message.setText(context);

        sendMail(message);
    }

    public void sendMail(SimpleMailMessage message) {
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
