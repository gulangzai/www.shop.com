package com.ccthanking.framework.util;

/**
 * 邮箱用户基本设置
 * @author WinVer
 *
 */
public class MailUserInfo {
	private String userName;// 验证邮箱名
	private String password; // 邮箱的密码
	private String[] toUser; // 发送者邮箱数组
	private String[] ccToUser; // 抄送邮箱数组
	private String fromUser;

	public MailUserInfo() {

	}
	/**
	 * 构造函数
	 * @param userName
	 * @param password
	 * @param toUser
	 * @param ccToUser
	 * @param fromUser
	 */
	public MailUserInfo(String userName, String password, String[] toUser,
			String[] ccToUser, String fromUser) {
		this.userName = userName;
		this.password = password;
		this.toUser = toUser;
		this.ccToUser = ccToUser;
		this.fromUser = fromUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getToUser() {
		return toUser;
	}

	public void setToUser(String[] toUser) {
		this.toUser = toUser;
	}

	public String[] getCcToUser() {
		return ccToUser;
	}

	public void setCcToUser(String[] ccToUser) {
		this.ccToUser = ccToUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
}
