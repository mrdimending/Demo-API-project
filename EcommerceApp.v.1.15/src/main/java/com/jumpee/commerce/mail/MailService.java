package com.jumpee.commerce.mail;

public interface MailService 
{
    void sendEmail(String to, String subject, String message);
}
