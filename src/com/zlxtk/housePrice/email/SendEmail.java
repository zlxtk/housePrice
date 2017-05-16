package com.zlxtk.housePrice.email;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends EmailBase {

	public void sendEmail(String title, String text) throws Exception {
		System.out.println("准备发送邮件到：");
		// 创建默认的 MimeMessage 对象
		MimeMessage message = new MimeMessage(getSession());

		// Set From: 头部头字段
		message.setFrom(new InternetAddress(from));
		for (String toEmail : to) {
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			System.out.println("    "+toEmail);
		}
		// Set Subject: 头部头字段
		message.setSubject(title);

		// 设置消息体
		message.setText(text);

		System.out.println("开始发送...");
		// 发送消息
		Transport.send(message);
		System.out.println("Sent email successfully.");

	}
}
