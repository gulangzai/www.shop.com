package com.lanbao.cn.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
   
	public Template getTemplate(String name){
		Configuration cfg  = new Configuration(); 
		String projectPath = System.getProperty("user.dir");
		System.out.println(System.getProperty("user.dir")); 
		// 设定去哪里读取相应的ftl模板文件
		//cfg.setClassForTemplateLoading(this.getClass(), "/ftl.createCode/");
	 
		Template temp = null;
		try {
			cfg.setDirectoryForTemplateLoading(new File(projectPath+"/src/main/resources/ftl.createCode"));
			 
			temp = cfg.getTemplate(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public void htmlOut(String name,Map<String,Object> rootMap,String outFile){
		FileWriter out = null;
		try {
			out = new FileWriter(new File("E:/code/" + outFile));
			Template temp = this.getTemplate(name);
			temp.process(rootMap, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{ 
				try {
				 if(out!=null)
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
