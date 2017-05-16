package com.zlxtk.housePrice.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailBase {

	// 发件人电子邮箱
	public static String from;
	// 密码
	private static String password;

	// 指定发送邮件的主机为 smtp.qq.com
	public static String smtp;
	// 指定发送邮件的主机为 smtp.qq.com
	public static String[] to;

	static {
		InputStream in = null;
		try {
			Properties prop = new Properties();
			// 获取项目路径
			File directory = new File("");// 参数为空
			String courseFile = directory.getCanonicalPath() + File.separator + "email.properties";
			// 读取属性文件
			FileInputStream fi = new FileInputStream(courseFile);
			in = new BufferedInputStream(fi);
			prop.load(in); /// 加载属性列表
			from = prop.getProperty("from");
			password = prop.getProperty("password");
			smtp = prop.getProperty("smtp");
			String str = prop.getProperty("to");
			to = str.split(",");
		} catch (Exception e) {
			System.out.println("邮箱配置文件加载失败！！！");
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				// nothing
			}
		}
	}
	/**
	 * 邮箱服务器连接并认证
	 * @return
	 * @throws Exception
	 */
	public Session getSession() throws Exception{
		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", smtp);

		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password); // 发件人邮件用户名、密码
			}
		});
		return session;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmailBase ds = new EmailBase();
			System.out.println(ds.from);
			System.out.println(ds.to);
			System.out.println(ds.smtp);
			System.out.println(ds.password);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
