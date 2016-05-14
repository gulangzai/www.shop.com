/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectController.java
 * 创建日期： 2015-10-17 上午 08:22:57
 * 功能：    服务控制类：项目
 * 所含类:   BuProjectService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-17 上午 08:22:57  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.message.service.BuUserMessageService;
import com.ccthanking.business.project.service.BuProjectService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.util.DesUtil;


/**
 * <p> BuProjectController.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="BuProjectController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-17
 * 
 */

@Controller
@RequestMapping("/project/buProjectController")
public class BuProjectController {

	private static Logger logger = LoggerFactory.getLogger(BuProjectController.class);

    @Autowired
    private BuProjectService buProjectService;
    
    @Autowired
    private BuUserMessageService buUserMessageService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buProjectService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryProject")
    @ResponseBody
    public requestJson queryProject(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	String project_uid =  request.getParameter("project_uid");
    	domresult = this.buProjectService.queryProject(project_uid);
    	j.setObj(domresult);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryProjectList")
    @ResponseBody
    public requestJson queryProjectList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	String userUid =  request.getParameter("userUid");
    	String projectName = request.getParameter("projectName");
    	List<?> list = this.buProjectService.queryProjectList(userUid,projectName);
    	j.setObj(list);
    	return j;
    	
    }
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryProjectByid")
    @ResponseBody
    public requestJson queryProjectByid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	 String jsonstr = request.getParameter("uuid");
    	String project_uid = "";
    	 if(null!=jsonstr&&!"".equals(jsonstr)){
         	JSONObject obj = JSONObject.fromObject(DesUtil.decrypt(jsonstr));
         	project_uid = obj.getString("project_uid");
         }
    	List<?> list = this.buProjectService.queryProjectList(project_uid);
    	j.setObj(list);
    	return j; 
    }

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 修改记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 删除记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buProjectService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
   
    /**
     * 删除文件
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    
    @RequestMapping(params = "deleteFile")
    @ResponseBody
    public requestJson deleteFile(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目删除】",user.getName());
        requestJson j = new requestJson();
        String target_uid = request.getParameter("target_uid");
        String target_table = request.getParameter("target_table");
        String file_type = request.getParameter("file_type");
        String resultVO = "";
        resultVO = this.buProjectService.deleteFile(target_uid,target_table,file_type);
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 项目详细信息查询
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDetailById")
    @ResponseBody
    public requestJson queryDetailById(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	 String project_uid = request.getParameter("PROJECT_UID");
    	
    	List<?> list = this.buProjectService.queryDetailById(project_uid);
    	j.setObj(list);
    	return j;
    	
    }
	  /**
	   * 查询新增项目时选择的省市区（县）
	   * @param request
	   * @param json
	   * @return
	   * @throws Exception
	   */
    @RequestMapping(params = "queryCity")
    @ResponseBody
    public requestJson queryCity(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	List<?> list  = new ArrayList();
    	 String code = request.getParameter("REGION_CODE");
    	 if(null!=code && !"".equals(code)){
          	//JSONObject obj = JSONObject.fromObject(DesUtil.decrypt(code));
          	//project_uid = obj.getString("project_uid");
    		list = this.buProjectService.queryCityorControyBycode(code);
          }else{
	    	list = this.buProjectService.queryCity(json.getMsg());
          }
	    	j.setObj(list);
	    	return j;
    	
    }
    
    /**
	   * 查询市县的名称
	   * @param request
	   * @param json
	   * @return
	   * @throws Exception
	   */
  @RequestMapping(params = "queryCityName")
  @ResponseBody
  public requestJson queryCityorName(final HttpServletRequest request, requestJson json) throws Exception {
  	User user = RestContext.getCurrentUser();
  	logger.info("<{}>执行操作【项目查询】",user.getName());
  	requestJson j = new requestJson();
  	List<?> list  = new ArrayList();
  	    String code = request.getParameter("REGION_CODE"); 	 
  		list = this.buProjectService.queryCityName(code);
	    j.setObj(list);
	    return j;
  	
  }
    
    /**
     * 查询天气情况
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryWeather")
    @ResponseBody
    public requestJson queryWeather(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	List<?> domresult = new ArrayList();//返回的是多个数据
    	String projectUid =  request.getParameter("project_uid");
    	domresult = this.buProjectService.queryWeather(projectUid);
    	j.setObj(domresult);
    	return j;
    	
    }
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryGKdetail")
    @ResponseBody
    public requestJson queryGKdetail(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	String project_uid =  request.getParameter("project_uid");
    	domresult = this.buProjectService.queryGKdetail(project_uid);
    	j.setObj(domresult);
    	return j;
    	
    }
    /**
     * 查询 详细信息
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDataJSON")
    @ResponseBody
    public requestJson queryDataJSON(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.buProjectService.queryDataJSON(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    
    /**
     * 饼图 我关注的 点
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryMyCare")
    @ResponseBody
    public requestJson queryMyCare(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String project_uid  = request.getParameter("PROJECT_UID");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("puid", project_uid);
    	Map<String,Object> map1 = this.buProjectService.queryMyCare(map);
    	j.setObj(com.alibaba.fastjson.JSONObject.toJSONString(map1));
    	return j;
    	
    }

    /**
     * 饼图 我关注的点报警的和正常的对比
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryWarning")
    @ResponseBody
    public requestJson queryWarning(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String project_uid  = request.getParameter("PROJECT_UID");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("puid", project_uid);
    	Map<String,Object> map1 = this.buProjectService.queryWarning(map);
    	j.setObj(com.alibaba.fastjson.JSONObject.toJSONString(map1));
    	return j;
    	
    }

    /**
     * 饼图 所有点报警的和正常的对比
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryAllPoint")
    @ResponseBody
    public requestJson queryAllPoint(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String project_uid  = request.getParameter("PROJECT_UID");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("puid", project_uid);
    	Map<String,Object> map1 = this.buProjectService.queryAllPoint(map);
    	j.setObj(com.alibaba.fastjson.JSONObject.toJSONString(map1));
    	return j; 
    }

    @RequestMapping(params = "queryMessageCount")
    @ResponseBody
    public requestJson queryMessageCount(final HttpServletRequest request,requestJson json)throws Exception{
    	User user = RestContext.getCurrentUser();
    	System.out.println("当前用户："+user.getAccount());
    	requestJson j = new requestJson();
    	int count = buUserMessageService.queryMessageCount(user.getIdCard());
    	j.setObj(count);
    	return j;
    }
    
    @RequestMapping(params = "queryid")
    @ResponseBody
    public requestJson queryid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目查询】",user.getName());
    	String projectuid =  request.getParameter("projectuid");
    	requestJson j = new requestJson();
    	String domresult = "";
    	
    	domresult = this.buProjectService.queryid(projectuid);
    	j.setObj(domresult);
    	return j;
    	
    }
}



