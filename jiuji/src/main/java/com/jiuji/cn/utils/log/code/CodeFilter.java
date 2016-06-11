package cn.lanbao.com.utils.log.code;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lanbao.com.utils.log.Liyi;

public class CodeFilter implements Filter {

	private FilterConfig config = null;  
	  
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hres = (HttpServletResponse) res;
		String code = config.getInitParameter("charset");
		String contentType = config.getInitParameter("contentType");
		hreq.setCharacterEncoding(code);
		hres.setCharacterEncoding(code);
		hres.setContentType(contentType);
		/*String url = hreq.getRequestURL().toString(); 
		String servername = hreq.getServerName();
		String port = String.valueOf(hreq.getServerPort());
		String local = hreq.getLocalAddr();
		String project = hreq.getContextPath();
		String projectPath ="http://"+ servername+":"+port+project;
		String jsp = hreq.getServletPath() ;
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session = request.getSession();
		 Liyi.p("projectPath|"+projectPath+jsp); 
		 if(session.getAttribute("username")==null||"".equals(session.getAttribute("username"))){
			  if(jsp.endsWith("login.jsp")||!jsp.endsWith("jsp")){
				  chain.doFilter(hreq,hres);
			  }else{
				  hres.sendRedirect(request.getContextPath()+"/login/login.jsp"); 
				 // Liyi.p("....."+projectPath+"/login/login.jsp"); 
				  return;  
			  } 
		 } else{
			 chain.doFilter(hreq, hres); 
		 } */
		 chain.doFilter(hreq, hres); 
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
        this.config  = config;
        Liyi.p("----------------初始化代码过滤器------------");
	}

}
