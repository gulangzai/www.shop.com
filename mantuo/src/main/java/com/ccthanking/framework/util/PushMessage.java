package com.ccthanking.framework.util;

import java.util.Date;
import java.util.HashMap;

import com.aliyuncs.push.model.v20150827.PushRequest;
import com.aliyuncs.push.model.v20150827.PushResponse;
import com.aliyuncs.utils.ParameterHelper;

public class PushMessage {
	
	public static void messagePush(HashMap<String, String> map) throws Exception{
		PushRequest pushRequest = new PushRequest();

		// 推送目标
		pushRequest.setAppKey(PushBase.appKey);
		pushRequest.setTarget("account"); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
		pushRequest.setTargetValue(map.get("account")); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
		pushRequest.setDeviceType(1); // 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.


		// 推送配置
		pushRequest.setType(1); // 0:表示消息(默认为0), 1:表示通知
		pushRequest.setTitle(map.get("title")); // 消息的标题
		pushRequest.setBody(map.get("content")); // 消息的内容
		//pushRequest.setSummary(map.get("summary")); // 通知的摘要
		// 推送配置: iOS
		pushRequest.setiOSBadge("5"); // iOS应用图标右上角角标
		pushRequest.setiOSMusic("default"); // iOS通知声音
		pushRequest.setiOSExtParameters("{\"k1\":\"ios\",\"k2\":\"v2\"}"); //自定义的kv结构,开发者扩展用 针对iOS设备
		pushRequest.setApnsEnv("DEV");
		pushRequest.setRemind(true); // 当APP不在线时候，是否通过通知提醒
		// 推送配置: Android
		pushRequest.setAndroidOpenType("2"); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url
		pushRequest.setAndroidActivity(map.get("className")); 
		
		//pushRequest.setAndroidOpenType("3");
		//pushRequest.setAndroidOpenUrl("http://www.baidu.com"); // Android收到推送后打开对应的url,仅仅当androidOpenType=3有效
		pushRequest.setAndroidExtParameters("{\"id\":\""+map.get("id")+"\",\"k2\":\"v2\"}"); // 设定android类型设备通知的扩展属性


		// 推送控制
		//final Date pushDate = new Date(System.currentTimeMillis() + 3600 * 1000); // 一小时后发送, 也可以设置成你指定固定时间
		//final String pushTime = ParameterHelper.getISO8601Time(pushDate);
		//pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
		pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
		final String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
		pushRequest.setExpireTime(expireTime);
		pushRequest.setBatchNumber("100010"); // 批次编号,用于活动效果统计. 设置成业务可以记录的字符串

		PushResponse pushResponse = PushBase.client.getAcsResponse(pushRequest);
		
		System.out.printf("RequestId: %s, ResponseId: %s, message: %s\n",
		        pushResponse.getRequestId(), pushResponse.getResponseId(), pushResponse.getMessage());
	}
	
}
