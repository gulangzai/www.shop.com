package com.jiuji.cn.utils.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
 

public class SendMsg_webchinese {
	
	public static void main(String[] args){
		SendMsg_webchinese  msg = new SendMsg_webchinese();
	    try {
	    	 String checkcode = IDGenertor.randomCode();
	    	 
			msg.sendMessage("wazll1314", "616d9b0637a69e44b5b9","15345886314","������֤��Ϊ:"+checkcode);
	    Liyi.p("............������Ϣ�ɹ�"+checkcode);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*	public void sendMessage(String uid,String password,String tele,String text)throws MsmException{
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
		HttpMethod method  = new GetMethod("http://utf8.sms.webchinese.cn?Uid="+uid+"&Key="+password+
				"&smsMob="+tele+"&smsText="+text);
		try {
			client.executeMethod(method);
			int result = Integer.parseInt(new String(method.getResponseBodyAsString().getBytes("gbk")));
		    if(result==-1){
		    	throw new MsmException("û�и��û��˻�,�û����ƣ�"+uid);
		    }else if(result==-2){
		       	throw new MsmException("�ӿ����벻��ȷ��"+password);
		    }else if(result==-3){
		    	throw new MsmException("�����������㣺"+password);
		    }else if(result==-14){
		    	throw new MsmException("�������ݳ��ַǷ��ַ���"+password);
		    }else if(result==-41){
		    	throw new MsmException("�ֻ���Ϊ��"+tele);
		    } 
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	 public  String getEncoding(String str) {
		    
		 String encode = "ISO-8859-1";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s1 = encode;
	                return s1;
	            }
	        } catch (Exception exception1) {
	        }
	        
	         encode = "GB2312";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s = encode;
	                return s;
	            }
	        } catch (Exception exception) {
	        }
	     
	        encode = "UTF-8";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s2 = encode;
	                return s2;
	            }
	        } catch (Exception exception2) {
	        }
	        encode = "GBK";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s3 = encode;
	                return s3;
	            }
	        } catch (Exception exception3) {
	        }
	        return "";
	    }
	 
	
 public void sendMessage(String uid,String password,String tele,String text)throws MsmException, UnsupportedEncodingException{
		HttpClient client = new HttpClient();
		PostMethod post  = new PostMethod("http://gbk.sms.webchinese.cn");
		//��ͷ�ļ�������ת��
		post.addRequestHeader("Content_Type", "application/x-www-form-urlencoded;charset=gbk");
		Liyi.p("����:"+getEncoding(text));
		NameValuePair[] data={
				new NameValuePair("Uid",uid),
				new NameValuePair("Key",password),
				new NameValuePair("smsMob",tele),
				new NameValuePair("smsText",new String(text.getBytes("gbk"),"ISO-8859-1"))};
		post.setRequestBody(data);
		System.out.println("data:"+data+"text:"+text);
		try {
			  client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:"+statusCode);
			for(Header h:headers){
				Liyi.p(h.toString()+"-----");
			}
			//��ȡ����״̬��
			int result = Integer.parseInt(new String(post.getResponseBodyAsString().getBytes("gbk")));
		    if(result==-1){
		    	throw new MsmException("û�и��û��˻�,�û����ƣ�"+uid);
		    }else if(result==-2){
		       	throw new MsmException("�ӿ����벻��ȷ��"+password);
		    }else if(result==-3){
		    	throw new MsmException("�����������㣺"+password);
		    }else if(result==-14){
		    	throw new MsmException("�������ݳ��ַǷ��ַ���"+password);
		    }else if(result==-41){
		    	throw new MsmException("�ֻ���Ϊ��"+tele);
		    }
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		post.releaseConnection();
	} 
}
