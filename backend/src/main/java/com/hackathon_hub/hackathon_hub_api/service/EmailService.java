package com.hackathon_hub.hackathon_hub_api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {


    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String to, String subject, String username, String password) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("no-reply@hackathonhub.com");

            Context context = new Context();
            context.setVariable("username", username);
            context.setVariable("email", to);
            context.setVariable("password", password);

            String htmlContent = templateEngine.process("adduser", context);
            helper.setText(htmlContent, true); // send HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Email send failed", e);
        }
    }
}
