/*
 * FreemarkerHelper.java  v1.00  2013-4-24
 * Peoject	copj-commons-utils
 * Copyright (c) 2013 Wisdragon
 *
 * Filename	:	FreemarkerHelper.java  v1.00 2013-4-24
 * Project	: 	copj-commons-utils
 * Copyight	:	Copyright (c) 2013 Wisdragon
 */

package com.ccthanking.common.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Freemarker工具类.<br>
 * 用于获取jar包下的ftl模版,生成文件等.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2013-4-24
 * 
 */
public class FreemarkerHelper {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerHelper.class);

    /**
     * 获取Configuration配置.<br/>
     * 
     * @parm type 获取类型 jar, classpath
     * @param tempPath
     *            模版路径 如： "/temp/code"
     * @return Configuration
     * @since v1.00
     */
    public static Configuration getConfig(String type, String tempPath) {
        try {

            Configuration cfg = new Configuration();
            cfg.setObjectWrapper(new DefaultObjectWrapper());

            if ("classpath".equals(type)) {
                // ftl 在本工程目录中
                 cfg.setDirectoryForTemplateLoading(new File(tempPath));
            } else if ("jar".equals(type)) {
                // ftl 在jar包里面的情况
                ClassTemplateLoader ctl = new ClassTemplateLoader(FreemarkerHelper.class, tempPath);
                cfg.setTemplateLoader(ctl);
            }

            cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
            cfg.setDefaultEncoding("utf-8");

            return cfg;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据模版及数据生成文件.<br/>
     * 
     * @param temp
     *            模版
     * @param root
     *            数据
     * @param path
     *            生成文件存放路径
     * @param fileName
     *            文件名
     * @return boolean
     * @since v1.00
     */
    public static boolean mackFile(Template temp, Map root, String path, String fileName) {

        // 创建目录路径
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File isAlerdyFile = new File(path + "\\" + fileName);
        if (isAlerdyFile.exists()) {
            logger.info("--!!!!!---文件已存在---!!!" + path + "\\" + fileName + " ---\n如需功能有修改，请先删除--");
            return false;
        }

        // 创建文件
        // File file = new File(dir.getPath(), fileName);
        // if (!file.exists()) {
        // file.createNewFile();
        // }

        try {
            java.io.BufferedWriter writer = null;
            java.io.FileOutputStream writerStream;

            writerStream = new java.io.FileOutputStream(path + "\\" + fileName);

            writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(writerStream, "UTF-8"));
            temp.process(root, writer);

            writer.flush();
            writer.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    /**
    * 生成word文件
    * dataMap word中需要展示的动态数据，用map集合来保存
    * templatePath word模板所在路径， 例如：D:/wordFile/
    * templateName word模板名称，例如：test.ftl
    * filePath 文件生成的目标路径，例如：D:/wordFile/
    * fileName 生成的文件名称，例如：test.doc   test.xml
    */
    public static boolean createWord(Map dataMap,String templatePath,String templateName,String filePath,String fileName){
        boolean isResult=false;   
    	try {
    			//添加后缀
    		     templateName= templateName+".ftl";
    		     fileName=fileName+".xml";
    		     
            	 Configuration configuration = new Configuration();
            	 configuration.setObjectWrapper(new DefaultObjectWrapper());
            	 configuration.setDirectoryForTemplateLoading(new File(templatePath));
            	 configuration.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
            	 configuration.setDefaultEncoding("utf-8");
               
                Template template=configuration.getTemplate(templateName);                
                //输出文件
                File outFile = new File(filePath+File.separator+fileName);          
                //如果输出目标文件夹不存在，则创建
                if (!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdirs();
                }           
                //将模板和数据模型合并生成文件 
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

                //生成文件
                template.process(dataMap, out);         
                //关闭流
                out.flush();
                out.close();
                isResult=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isResult;
        }
    
    
    /**
     * 生成word文件
     * dataMap word中需要展示的动态数据，用map集合来保存
     * templatePath word模板所在路径， 例如：D:/wordFile/
     * templateName word模板名称，例如：test.ftl
     * filePath 文件生成的目标路径，例如：D:/wordFile/
     * fileName 生成的文件名称，例如：test.doc   test.xml
     */
    public static String createWord(Map dataMap,String templatePath,String templateName,String fileName){
    	String path =  "";
    	try {
    		//添加后缀
    		templateName = templateName+".ftl";
    		fileName = fileName+".doc";
    		
    		Configuration configuration = new Configuration();
    		configuration.setObjectWrapper(new DefaultObjectWrapper());
    		configuration.setDirectoryForTemplateLoading(new File(templatePath));
    		configuration.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
    		configuration.setDefaultEncoding("utf-8");
    		
    		Template template = configuration.getTemplate(templateName);                
    		//输出文件
    		File outFile = new File(templatePath+File.separator+fileName);          
    		//如果输出目标文件夹不存在，则创建
    		if (!outFile.getParentFile().exists()){
    			outFile.getParentFile().mkdirs();
    		}           
    		//将模板和数据模型合并生成文件 
    		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
    		
    		//生成文件
    		template.process(dataMap, out);         
    		//关闭流
    		out.flush();
    		out.close();
    		path = fileName;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return path;
    }
    
    /**
     * freemarker 生成work 中 图片处理方法
     * imagePath  图片路径  如：D:/imageFile/
     * imageName  图片名称   如：image.jpg
     * */
	 public static String getImageStr(String imagePath,String imageName) {
         String imgFile = imagePath+File.separator+imageName;
         InputStream in = null;
         byte[] data = null;
         try {
             in = new FileInputStream(imgFile);
             data = new byte[in.available()];
             in.read(data);
             in.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         BASE64Encoder encoder = new BASE64Encoder();
         return encoder.encode(data);
     }
	 /**
	     * freemarker 生成work 中 图片处理方法
	     * imagePath  图片路径  如：D:/imageFile/
	     * imageName  图片名称   如：image.jpg
	     * */
	 public static String getImageAllPath(String imagePathName) {
	         String imgFile = imagePathName;
	         InputStream in = null;
	         byte[] data = null;
	         try {
	             in = new FileInputStream(imgFile);
	             data = new byte[in.available()];
	             in.read(data);
	             in.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	         BASE64Encoder encoder = new BASE64Encoder();
	         return encoder.encode(data);
	     }
	 
	 public static void main(String[] args) {
		 
// 		String templatePath="d:\\Users\\Administrator\\Desktop\\wmdjs-vo\\src\\main\\resources\\template\\word";
//		String templateName="《新区建设项目扩初评审申请表》";
//		String filePath="d:\\Users\\Administrator\\Desktop\\wmdjs-vo\\src\\main\\resources\\template\\word";
//		String fileName="《新区建设项目扩初评审申请表》.ftl";//后缀 .xml .doc 都可以
		
		Map temMap=new HashMap();
		//图片处理方式
//		temMap.put("BMSH_NAME", FreemarkerHelper.getImageStr(templatePath,"cjn1.jpg"));
		
//		if(FreemarkerHelper.createWord(temMap, templatePath, templateName, filePath, fileName)){
//			//生成成功之后调用转成PDF文件
////			Word2PDF.toPdf(filePath+File.separator+fileName);
//		}

	}

}
