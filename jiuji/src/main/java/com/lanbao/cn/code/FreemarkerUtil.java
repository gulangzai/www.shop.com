package com.lanbao.cn.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.lanbao.cn.db.single.Table;

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
			
			Table table = (Table) rootMap.get("table");
			File fileDir = new File("E:/code/"+ table.getName());
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			File file = new File("E:/code/"+ table.getName()+"/"+ outFile);
			if(!file.exists()){
				file.createNewFile();
			} 
			out = new FileWriter(file);
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
