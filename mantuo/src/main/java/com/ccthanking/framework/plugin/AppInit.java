package com.ccthanking.framework.plugin;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.ccthanking.common.cache.Cache;
import com.ccthanking.common.cache.CacheHolder;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.datasource.DBConnectionManager;
import com.ccthanking.framework.dic.Dics;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.http.listener.SessionCacheManager;
import com.ccthanking.framework.log.AsynLog;
import com.ccthanking.framework.log.log;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.copj.modules.utils.cache.memcached.JmemcachedServer;

/* 
 * 应用系统初始化类
 * 该类负责字典的初始化
 * 及组织机构的初始化工作
 */
public class AppInit {

	public static String appPath = "";

	public void init()// ActionServlet arg0, ModuleConfig arg1
			throws ServletException {

		Logger lg = log.getLogger(AppInit.class);
		try {
			String path = System.getProperty(Constants.getString(Constants.webAppRootKey, "cpmi.root"));
			this.appPath = path;
			String connpath = path + "/WEB-INF/conf/";
			ActionContext.setIsTranscation(false);

			DBConnectionManager.setConnectionPath(connpath);

			lg.info("初始化session cache信息...");
			// 设置session缓存信息
			Cache sessonCache = CacheHolder.newGuavaCache("10000");
			SessionCacheManager.getInstance().setSessionCache(sessonCache);
			Cache onlineUserCache = CacheHolder.newGuavaCache("5000");
			SessionCacheManager.getInstance().setOnLineUsreCache(onlineUserCache);

			lg.info("初始化异步日志...");
			// 异步日志
			AsynLog.getInstance();
			lg.info("初始化jmemcacheserver...");
			 if (Constants.getBoolean(Constants.MEMCACHE_SERVER_CREATE_FLAG,false)) {
			 // 缓存
			 JmemcachedServer.getInstance().afterPropertiesSet();
			 }

			lg.info("初始化字典信息...");
			//Dics.getInstance();

			lg.info("初始化系统参数...");                                                                   
			SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("LOADINGDIC");

			lg.info("生成字典xml");
			// 此处报错 未修改 
			if (syspara != null && syspara.getPARAVALUE1().equals("1")) {
				Dics.printDicToXml(path + "/dic");
			}
			Dics.createDataSourceDic(DBConnectionManager.getInstance().getDataSource(), path + "/dic");

			lg.info("系统信息初始化完毕.......");
		} catch (Exception E) {
			lg.error(E);
			E.printStackTrace();
			throw new ServletException(E);
		} finally {
			ActionContext.clearAcContext();
		}
	}

	public void destroy() {
		try {
			AsynLog.stop();
			 if (Constants.getBoolean(Constants.MEMCACHE_SERVER_CREATE_FLAG,false)) {
			 JmemcachedServer.getInstance().destroy();
			 }
		} catch (Exception e) {
		}
	}
}