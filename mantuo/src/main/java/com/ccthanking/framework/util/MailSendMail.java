package com.ccthanking.framework.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件发送
 * 
 * @author WinVer
 * 
 */
public class MailSendMail {
	/**
	 * 发送邮件
	 * 
	 * @param userinfo
	 *            发送的用户信息
	 * @param basciinfo
	 *            邮件的基本信息
	 */
	public boolean sendMail(final MailUserInfo userinfo, MailBasicInfo basciinfo) {
		boolean flag = false;
		// 邮件会话属性设置
		Properties props = System.getProperties();
		props.put("mail.smtp.host", basciinfo.getHost()); // 设置邮件主机配置
		props.put("mail.smtp.auth", "true"); // 是否验证用户邮箱
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {

					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userinfo
								.getUserName(), userinfo.getPassword());
					}
				});
		try {
			// 创建邮件信息
			MimeMessage message = new MimeMessage(session);
			// 设置发送邮件人
			message.setFrom(new InternetAddress(userinfo.getFromUser()));

			// 设置发件人信息
			String toUser[] = userinfo.getToUser();
			if (toUser != null && toUser.length > 0) {
				InternetAddress[] toaddress = new InternetAddress[toUser.length];
				for (int i = 0; toUser.length > i; i++) {
					toaddress[i] = new InternetAddress(toUser[i]);
				}
				message.setRecipients(Message.RecipientType.TO, toaddress);
			}
			// 设置抄送邮件人信息
			String[] ccToUser = userinfo.getCcToUser();
			if (ccToUser != null && ccToUser.length > 0) {
				InternetAddress[] cctoaddress = new InternetAddress[ccToUser.length];
				for (int i = 0; ccToUser.length > i; i++) {
					cctoaddress[i] = new InternetAddress(ccToUser[i]);
				}
				message.setRecipients(Message.RecipientType.CC, cctoaddress);
			}

			// 设置邮件主题格式
			message.setSubject(MimeUtility.encodeText(basciinfo.getSubject(),
					"gb2312", "B"));

			// 构造邮件的主体
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbpContent = new MimeBodyPart(); // 邮件内容主题
			mbpContent.setText(basciinfo.getContent());
			mp.addBodyPart(mbpContent);

			// 添加附件信息
			Vector<?> file = basciinfo.getFile();
			if (file != null && file.size() > 0) {
				Enumeration<?> efile = file.elements();
				while (efile.hasMoreElements()) {

					MimeBodyPart mbpFile = new MimeBodyPart();
					String filename = efile.nextElement().toString();
					FileDataSource fds = new FileDataSource(filename);
					mbpFile.setDataHandler(new DataHandler(fds));
					mbpFile.setFileName(fds.getName());
					mp.addBodyPart(mbpFile);

				}
				file.removeAllElements();
				basciinfo.getFile().removeAllElements();
			}
			
			message.setContent(mp);
			message.setSentDate(new Date());
			// 发送邮件
			Transport.send(message);
			flag = true;

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 验证邮箱地址
	 * 
	 * @param mail
	 * @return
	 */
	private boolean valMail(String mail) {
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(mail);
		return m.find();
	}

	/**
	 * 邮箱数组中得到正确的邮箱数组
	 * 
	 * @param mails
	 * @return
	 */
	private String[] getTrueMail(String[] mails) {
		if (mails != null && mails.length > 0) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < mails.length; i++) {
				boolean falg = valMail(mails[i]);
				if (falg) {
					list.add(mails[i]);
				}
			}
			String[] mas = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				mas[j] = (String) list.get(j);
			}
			return mas;
		}
		return null;
	}

	/**
	 * 主函数， 获取配置文件中信息，发送邮件
	 * 
	 * @param args
	 */
	public static boolean sendMail(String subject,String content,String toUser) {
		
		boolean reflag = false;
		MailSendMail msm = new MailSendMail();
		MailUserInfo userinfo = new MailUserInfo();
		MailBasicInfo basicinfo = new MailBasicInfo();
		boolean flag = true;
		
		//收件人
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(toUser);
		userinfo.setToUser(list.toArray(new String[1]));
		
//		String to = ProperConfigUtil.getParaValue("mailToUser");
//		if (to != null && !"".equals(to)) {
//			String[] tos = msm.getTrueMail(to.split(";"));
//			if (tos != null && tos.length > 0) {
//				userinfo.setToUser(tos);
//			} else {
//				flag = false;
//			}
//		} else {
//			flag = false;
//		}

		//抄送人
//		String cc = ProperConfigUtil.getParaValue("mailccTouser");
//		if (cc != null && !"".equals(cc)) {
//			String[] ccs = msm.getTrueMail(cc.split(";"));
//			if (ccs != null && ccs.length > 0) {
//				userinfo.setCcToUser(ccs);
//			} else {
//				flag = false;
//			}
//		} else {
//			flag = false;
//		}

		//发件人
		String user = ProperConfigUtil.getParaValue("mailUserName");
		if (msm.valMail(user)) {
			userinfo.setUserName(user);
		} else {
			flag = false;
		}

		//密码
		String pwd = ProperConfigUtil.getParaValue("mailPassword");

		if (pwd != null && !"".equals(pwd)) {
			userinfo.setPassword(pwd);
		} else {
			flag = false;
		}

		//发件人邮箱地址
		String from = ProperConfigUtil.getParaValue("mailFromUser");
		if (msm.valMail(from)) {
			userinfo.setFromUser(from);
		} else {
			flag = false;
		}
		//邮箱服务器
		String host = ProperConfigUtil.getParaValue("mailSmtpHost");
		if (host != null && !"".equals(host)) {
			basicinfo.setHost(host);
		} else {
			flag = false;
		}
		
		//标题信息
		basicinfo.setSubject(subject);
		//内容信息
		basicinfo.setContent(content);
		//附件信息
		// basicinfo.setFile("E:\\test.txt");
		if (flag) {
			try {
				reflag = msm.sendMail(userinfo, basicinfo);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("获取数据邮箱等有问题");
		}
		return reflag;

	}

	public static void main(String[] args) {
		sendMail("邀请邮箱测试","此邮件请勿回复","429391259@qq.com");
	}
}
