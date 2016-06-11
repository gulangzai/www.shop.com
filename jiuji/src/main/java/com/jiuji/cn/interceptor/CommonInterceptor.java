package com.jiuji.cn.interceptor;  
  
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  
import com.jiuji.cn.listener.SessionListener; 
  
  
  
/** 
 * @author tfj 
 * 2014-8-1 
 */  
public class CommonInterceptor extends HandlerInterceptorAdapter{  
    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);   
    //public static final String LAST_PAGE = "com.alibaba.lastPage";  
    /* 
     * 利用正则映射到需要拦截的路径      
    private String mappingURL; 
     
    public void setMappingURL(String mappingURL) {     
               this.mappingURL = mappingURL;     
    }    
  */  
    /**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
        log.info("==============执行顺序: 1、preHandle================");    
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        log.info("requestUri:"+requestUri);    
        log.info("contextPath:"+contextPath);    
        log.info("url:"+url);  
        /*HttpSession session=request.getSession();
        Cookie[] cookies=request.getCookies();
        String[] cooks = null;  
        String username = null;  
        String password = null;  
		if(cookies!=null&&cookies.length>0){
			 for (Cookie coo : cookies) {  
	                String aa = coo.getValue();  
	                cooks = aa.split("==");  
	                if (cooks.length == 2) {  
	                    username = cooks[0];  
	                    password = cooks[1];  
	                }  
            }  
		}
		StudentInfo user = studentInfoService.login(username, password);
        if(user!=null){
        	session.setAttribute("user", user);
			session.setAttribute("loginname", user.getStudentName());
			request.getRequestDispatcher("redirect:/homePage/toIndex.do").forward(request, response); 
			return true;   
        }*/
        HttpSession session=request.getSession();
        return true;
        /*String uri = request.getHeader("referer");
        BsUserInfo teacher =  (BsUserInfo)request.getSession().getAttribute("teacher");
         System.out.println("teacher:----------------------------|"+teacher);
        if(teacher == null){  
        	Cookie[] cookies=request.getCookies();
            if(cookies!=null&&cookies.length>0){
    			 for (Cookie coo : cookies) {  
    				 if ("userId".equals(coo.getName())){
    					 String userId = coo.getValue();
					     teacher =bsUserInfoService.findByUserId(userId);
					     if(teacher!=null){
			            	session.setAttribute("teacher", teacher);
			    			session.setAttribute("loginname", teacher.getUserName());
							SessionListener.replaceUserSession(session, teacher.getUserId());
			    			return true; 
			             }
    				 }
                }  
    		}
            if(url.indexOf("toIndex")>1){
            	System.out.println("indexOf:....");
            	return true; 
            }
            if(url.indexOf("toLogin")>1){
            	return true;
            } 
            log.info("Interceptor：跳转到login页面！");
            request.setAttribute("fromUrl", uri);
          //request.getRequestDispatcher("/WEB-INF/view/modules/homepage/login.jsp").forward(request, response);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return false;  
        }else{ 
                 if(!SessionListener.isOnline(request.getSession())){//当前用户不在线,即session已经被剔除
                	 session.removeAttribute("teacher");
                     //RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/modules/homepage/login.jsp");
                     RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
                     request.setAttribute("fromUrl", uri);
                     dispatcher.forward(request,response);
                     log.info("用户不在线剔除,要求重新登录");
                     return false;  
                 } 
            return true;   
        }*/
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
        log.info("==============执行顺序: 2、postHandle================");    
        /*if(modelAndView != null){  //加入当前时间    
            modelAndView.addObject("var", "测试postHandle");    
        } */   
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等     
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
       // log.info("==============执行顺序: 3、afterCompletion================");    
    }    
  
} 