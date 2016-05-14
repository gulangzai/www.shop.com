package com.ccthanking.framework;

public class Constants extends ConfigurableConstants {

	static {
		init("application.xml");
	}

	// ---------------从其它地方copy过来的放这个里面 ----------begin---

	// 是否启用dwr消息推送 true启用 false不启用
	public static String MSG_DWR_FLAG = "MSG_DWR_FLAG";
	// 是否创建jmemcache server true建 false不建
	public static String MEMCACHE_SERVER_CREATE_FLAG = "MEMCACHE_SERVER_CREATE_FLAG";

	// ---------------从其它地方copy过来的放这个里面 ----------end-----

	// 是否开发状态
	public static String APP_DEV_FLAG = "DEV_FLAG";

	// cookie name 登录名
	public static final String APP_COOKIE_NAME_REMBERUSERNAME = "jsremusname";

	// 上下文存放的 web文件上下文件路径 需要与--> web.xml 中保持一致
	public static String webAppRootKey = "webAppRootKey";

	// 应用程序名称 对应于菜单 fs_eap_menu.APP_NAME
	public static final String APP_NAME = "cpmi";

	// 加密方式
	public static String ENCODER_TYPE = "MD5";
	// 登录方式
	public static String LOGIN_TYPE = "springsecurity";

	// 客户端信息
	public static final String WEB_CLIENT = "WEB_CLIENT";

	// 来宾账号
	public static String GUEST_ID = "guest";

	// 注意，该文件为保持兼容性而保留，新添加的常量定义须写在 Globals.java 中，避免直接
	// 使用 Constants 类。
	public static final String FILE_KEY = "GLOBAL.TMP_FILES";

	// 资源路径，用于修改资源文件，如JSP,FTL,HTML等类型资源文件
	public static final String RESOURCE_PATH = "resourcePath";
	// 控制文件从哪个地方读取 本地文件file 数据库db
	public static final String RESOURCE_CORL = "resource_corl";

	// 模板文件
	public static final String PATH_TEMPLATE_WORD = "PATH_TEMPLATE_WORD";

	// 转换时生成的xml、pdf
	public static final String PATH_TEMPLATE_XML = "PATH_TEMPLATE_XML";

	// 附件显示
	public static final String DATA_FUJIAN_SHOW = "DATA_FUJIAN_SHOW";

	
	// 附件上传地址别名
	public static final String FILEUPLOADOLD_ROOT = "FILEUPLOADROOT";

	// 应用程序上传附件存放根目录key fs_para_sys_configure表中parakey
	public static final String APP_FILEUPLOADROOT_PARAKEY = "FILEUPLOADROOT";
	
	//微信 投诉 或 资讯 附件类型
	public static final String FS_FILEUPLOAD_FJLB_TSORZX_FJLX="9811";

}
