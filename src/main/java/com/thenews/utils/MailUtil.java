package com.thenews.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailUtil {

    public static void sending(String emailTo, String title, String subject) throws MessagingException, MessagingException {
        final String username = "haithach.01202@gmail.com";
        final String password = "npudmppgdsqfladk";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailTo));
        message.setSubject(title);
        message.setText(subject);
        try {
            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException e) {
            e.printStackTrace(); // In ra thông tin lỗi
            throw new RuntimeException("Error sending email", e); // Ném ngoại lệ nếu có lỗi
        }
    }


}
