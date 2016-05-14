package com.ccthanking.framework.util;


import java.util.Vector;
/**
 * 邮件的基本信息 
 * @author WinVer
 *
 */
public class MailBasicInfo {
	private String host = "";    //smtp主机
	private String subject = ""; // 邮件主题
	private String content = ""; // 邮件内容
	private Vector<Object> file=new Vector<Object>();

	public Vector<Object> getFile() {
		return file;
	}

	public MailBasicInfo() {

	}

	/**
	 * 构造时 将主题 内容设置
	 * 
	 * @param subject
	 * @param content
	 */
	public MailBasicInfo(String subject, String content,String host) {
		this.subject = subject;
		this.content = content;
		this.host=host;
	}

	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 添加附件 filepath 是本地服務器上的路徑及文件名
	 * 
	 * @param filepath
	 */
	public void setFile(String filepath) {
		file.addElement(filepath);
	}

}
