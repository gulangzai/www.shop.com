package javacommon.util;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtil
{
	public static Properties getProperty()
	{
		try
		{
			FileInputStream stream=new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"application.properties");
			Properties properties=new Properties();
			properties.load(stream);
	        stream.close();
	        return properties;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
