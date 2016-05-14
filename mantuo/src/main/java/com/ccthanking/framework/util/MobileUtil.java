package com.ccthanking.framework.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class MobileUtil {

	public static String TOKEN = "704DBY3HR8VJDEZM6RA";

	public static String WXCTOKEN = "704DBY3HR8VJDEZM6RA";

	/**
	 * 返回查询token错误信息
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONObject getErrorToken(JSONObject obj) {

		obj.put("state", false);
		obj.put("message", "token错误...");

		return obj;
	}

	/**
	 * 返回查询错误信息
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONObject getErrorMessage(JSONObject obj) {

		obj.put("state", false);
		obj.put("message", "查询结果失败");

		return obj;
	}

	/**
	 * 返回查询正确信息
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONObject getRightMessage(JSONObject obj) {

		obj.put("state", true);
		obj.put("message", "查询结果成功");

		return obj;
	}

	/**
	 * 返回密码修改的信息
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONObject getUpdateMessage(JSONObject obj, boolean flag) {
		obj.put("state", flag);
		if (flag) {
			obj.put("message", "修改密码成功");
		} else {
			obj.put("message", "修改密码失败,请重新输入");
		}

		return obj;
	}

	public static JSONObject getPwdInputError(JSONObject obj) {
		obj.put("state", false);
		obj.put("message", "您输入的原始密码有误,请重新输入。");
		return obj;

	}

	public static JSONObject getUserXinXi(JSONObject obj, boolean flag) {
		obj.put("state", flag);
		if (flag) {
			obj.put("message", "获取用户个人信息成功");
		} else {
			obj.put("message", "获取用户个人信息失败,请联系管理员处理。");
		}
		return obj;
	}

	public static JSONObject getMessageInfo(JSONObject obj) {
		obj.put("state", true);
		obj.put("message", "操作成功！");
		return obj;
	}

	public static JSONObject getMessageError(JSONObject obj) {
		obj.put("state", false);
		obj.put("message", "操作失败！");
		return obj;
	}

	/**
	 * 判断是否是查询某人发布的
	 * 
	 * @param sql
	 * @return
	 */
	public static StringBuffer isMyPublish(StringBuffer sql, HashMap<String, String> map) {
		if (map.get("type").equals("my")) {
			sql.append(" and CREATE_USER = '" + map.get("userId") + "'");
		}
		return sql;
	}

}
