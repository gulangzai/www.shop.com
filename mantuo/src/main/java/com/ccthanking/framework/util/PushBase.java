package com.ccthanking.framework.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.InputStream;
import java.util.Properties;


/**
 * 推送的OpenAPI文档 https://help.aliyun.com/document_detail/mobilepush/api-reference/openapi.html
 */
public class PushBase {
    private static final String REGION_HANGZHOU = "cn-hangzhou";

    protected static long appKey;
    protected static String deviceIds;
    protected static String accounts;

    protected static DefaultAcsClient client;
    
	static{
        InputStream inputStream = PushBase.class.getClassLoader().getResourceAsStream("push.properties");
        Properties properties = new Properties();
        try {
        	properties.load(inputStream);
            String accessKeyId = properties.getProperty("accessKeyId");
            System.out.println("先在 push.properties 配置文件中配置 accessKeyId"+ accessKeyId);

            String accessKeySecret = properties.getProperty("accessKeySecret");
            System.out.println("先在 push.properties 配置文件中配置 accessKeySecret"+ accessKeySecret);

            String key = properties.getProperty("appKey");
            System.out.println("先在 push.properties 配置文件中配置 appKey"+ key);
            PushBase.appKey = Long.valueOf(key);

            //deviceIds = properties.getProperty("deviceIds");
            //accounts = properties.getProperty("accounts");

            IClientProfile profile = DefaultProfile.getProfile(REGION_HANGZHOU, accessKeyId, accessKeySecret);
            client = new DefaultAcsClient(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
