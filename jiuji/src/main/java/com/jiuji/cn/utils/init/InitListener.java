package cn.lanbao.com.utils.init;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSHttpPost;
import cn.com.webxml.WeatherWSSoap; 
import cn.lanbao.com.utils.log.Liyi; 

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// ��ȡ��������ص�Service����
		/* 	 Liyi.p("----------------��������ʼ����Ŀ¼--------------"); 
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()); 
		Liyi.p("ApplicationContext:"+ac);
		IGoodParentTypeDao goodParentTypeDao = (IGoodParentTypeDao) ac.getBean("goodParentTypeDaoImpl");
		IGoodBaseDao igoodBaseDaoImpl = (IGoodBaseDao) ac.getBean("goodBaseDaoImpl"); 
		List<GoodParentType> goodParentTypes =  goodParentTypeDao.queryGoodParentTypeAll();
		List<GoodBase> goodBases = igoodBaseDaoImpl.queryFourGood();
        sce.getServletContext().setAttribute("goodParentTypes", goodParentTypes);
        sce.getServletContext().setAttribute("hotGoodBases", goodBases);
       Liyi.p("��ʼ������webService");
       WeatherWS weatherWS = new WeatherWS();
       WeatherWSSoap  wwss = weatherWS.getWeatherWSSoap();
       ArrayOfString aos = wwss.getSupportCityString("�Ϻ�");
      aos = wwss.getWeather("�Ϻ�", null);
       List<String> weather = aos.getString();
       for (String string : weather) {
		System.out.println("---"+string);
		 sce.getServletContext().setAttribute("weather", weather);
	    }*/
	}

}
