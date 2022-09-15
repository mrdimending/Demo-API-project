package com.jumpee.commerce.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) 
    {
    	MimeMessage mailer = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mailer, "utf-8");
    	
    	try 
    	{
			helper.setFrom("mrdandimen@gmail.com");
			helper.setTo(to);
	    	helper.setSubject(subject);
	    	helper.setText(message, true);
	    	
	    	this.mailSender.send(mailer);
		}
    	catch (MessagingException e) 
    	{
			e.printStackTrace();
		}
    	

        
    }
}
