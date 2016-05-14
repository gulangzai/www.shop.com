package javacommon.util;

import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  
public class CookieUtil {   
  
    public boolean addCookie(HttpServletResponse response,String name,String value,int expiry) {  
        boolean tmpret = true;
        try
        {
	    	Cookie cookie = new Cookie(name, value);
	    	cookie.setPath("/");
	        if(expiry>=0)
	        	cookie.setMaxAge(expiry);
	        else
	        	cookie.setMaxAge(60 * 60 * 1);

        	response.addCookie(cookie);
        }
        catch(Exception ex)
        {
        	tmpret = false;
        	System.out.println(ex.getMessage());
        }
        
        return tmpret;  
    }  
  
    public String getCookie(HttpServletRequest request,String name) { 
    	String value=null;
        Cookie[] cookies = request.getCookies();  
        //System.out.println("cookies: " + cookies);  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {   
                if (name.equals(cookie.getName())) {  
                    value = cookie.getValue();                      
                }  
            }  
        }  
        return value;
    }  
  
    public boolean delCookie(HttpServletRequest request,String name,HttpServletResponse response) { 
    	boolean tmpret = true;
    	try
    	{
	        Cookie[] cookies = request.getCookies();  
	        if (cookies != null) {  
	            for (Cookie cookie : cookies) {  
	                if (name.equals(cookie.getName())) {  
	                	 //Cookie cookie1 = new Cookie(cookie.getName(), null);  
	                	cookie.setMaxAge(1);  
	                	cookie.setPath("/");
	                     response.addCookie(cookie);  
	                }  
	            }  
	        }
    	}
    	catch(Exception ex)
        {
        	tmpret = false;
        	System.out.println(ex.getMessage());
        }
        return tmpret;  
    }  
}  