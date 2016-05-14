package com.ccthanking.framework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可用Properties文件配置的Constants基类.
 * 本类既保持了Constants的static和final(静态与不可修改)特性,又拥有了可用Properties文件配置的特征,
 * 主要是应用了Java语言中静态初始化代码的特性.
 * <p/>
 * 子类可如下编写
 * 
 * <pre>
 * public class Constants extends ConfigurableConstants {
 *  static {
 *    init("application.properties");
 * }
 * <p/>
 * public final static String ERROR_BUNDLE_KEY = getProperty("constant.error_bundle_key", "errors"); }
 * </pre>
 * 
 * @author calvin
 * @see org.springside.core.Constants
 */
public class ConfigurableConstants extends Globals {

	protected static Logger logger = LoggerFactory.getLogger(ConfigurableConstants.class.getName());
	protected static Properties p = new Properties();

	protected static XMLConfiguration pc = null;

	/**
	 * 静态读入属性文件到Properties p变量中.
	 */
	protected static void init(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigurableConstants.class.getClassLoader().getResourceAsStream(propertyFileName);
			if (in != null) {
				p.load(in);
				String path = p.getProperty("config_file");
				if ("".equals(StringUtils.trimToEmpty(path))) {
					// 引用当前文件
					URL url = ConfigurableConstants.class.getClassLoader().getResource(propertyFileName);
					path = url.getPath();
				}

				File f = new File(path);
				pc = new XMLConfiguration();
				pc.setDelimiterParsingDisabled(true);
				
				

				// 注册 修改生新加载
				pc.setReloadingStrategy(new FileChangedReloadingStrategy());
				
				pc.load(f);
			}
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
	}

	/**
	 * 封装了PropertiesConfiguration类的getString函数,使p变量对子类透明.
	 * 
	 * @param key
	 *            property key.
	 * @param defaultValue
	 *            当使用property key在properties中取不到值时的默认值.
	 */
	public static String getString(String key, String defaultValue) {
		return pc.getString(key, defaultValue);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return pc.getBoolean(key, defaultValue);
	}

	public static int getInt(String key, int defaultValue) {
		return pc.getInt(key, defaultValue);
	}
}
