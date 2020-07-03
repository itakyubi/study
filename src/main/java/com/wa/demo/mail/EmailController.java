package com.wa.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmailController
 * 2020-07-03 10:52
 *
 * @author wuao
 */
@RestController
@RequestMapping("/v1")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public void sendMail(@RequestBody SimpleMailMessage message) {
        emailService.sendMail(message);
    }
}
