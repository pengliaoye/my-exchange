package com.exchange.utils;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {
	private JavaMailSender sender;
	private SimpleMailMessage mailMessage;
	private MimeMessage mimeMessage;
	private MimeMessageHelper helper;
	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void sendSimpleMessage(String address,String message){
		mailMessage.setTo(address);
		mailMessage.setCc(new String[]{""});
		mailMessage.setText(message);
		sender.send(mailMessage);
	}
	public void sendMimeMessage(String address,String message){
		FileSystemResource resource=new FileSystemResource(new File("c:/Sample.jpg"));
		try {
			helper.addAttachment("附件名称", resource);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
