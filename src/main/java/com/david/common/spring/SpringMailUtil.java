package com.david.common.spring;

import com.david.common.config.JConfig;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author David
 */
public class SpringMailUtil {

    private static JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

    static {
        senderImpl.setHost(JConfig.getConfig("mail.smtp"));
        senderImpl.setPort(465);
        senderImpl.setUsername(JConfig.getConfig("mail.username"));
        senderImpl.setPassword(JConfig.getConfig("mail.password"));
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        properties.setProperty("mail.smtp.starttls.required", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        senderImpl.setJavaMailProperties(properties);
    }

    public static void sendTextMail(String email, String Subject, String text) {
        // Create a mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(Subject);
        mailMessage.setText(text);
        senderImpl.send(mailMessage);
    }
    private  SpringMailUtil(){};
}
