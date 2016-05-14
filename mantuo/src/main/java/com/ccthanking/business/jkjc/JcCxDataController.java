/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcCxDataController.java
 * 创建日期： 2015-12-01 上午 10:44:12
 * 功能：    服务控制类：测斜数据
 * 所含类:   JcCxDataService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-01 上午 10:44:12  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.service.JcCxDataService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.github.abel533.echarts.Option;


/**
 * <p> JcCxDataController.java </p>
 * <p> 功能：测斜数据 </p>
 *
 * <p><a href="JcCxDataController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-01
 * 
 */

@Controller
@RequestMapping("/jkjc/jcCxDataController")
public class JcCxDataController {

	private static Logger logger = LoggerFactory.getLogger(JcCxDataController.class);

    @Autowired
    private JcCxDataService jcCxDataService;

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
        logger.info("<{}>执行操作【测斜数据查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcCxDataService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryTab")
    @ResponseBody
    public requestJson queryTab(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
    	requestJson j = new requestJson();
    	//List<Map<String,String>>  domresult = new ArrayList<Map<String,String>>();
    	
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");

    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);
    	map.put("type", type);
    	Map<String,Object> domresult = this.jcCxDataService.queryTab(map);
    	j.setObj(domresult);
    	System.out.println(domresult);
    	
    	return j;
    	
    }
    
    /**
     * 查询 折线图
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryLineChart")
    @ResponseBody
    public requestJson queryLineChart(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【测斜数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);
    	map.put("type", type);
    	Option option = this.jcCxDataService.queryLineChart(map);
    	System.out.println(JSONObject.toJSONString(option) );
    	j.setObj(JSONObject.toJSONString(option));
    	return j;
    	
    }
    
    /**
     * 查询 折线图
     * 初始值与截至日期的对比
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryLineChart2")
    @ResponseBody
    public requestJson queryLineChart2(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【测斜数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);
    	map.put("type", type);
    	Option option = this.jcCxDataService.queryLineChart2(map);
    	System.out.println(JSONObject.toJSONString(option) );
    	j.setObj(JSONObject.toJSONString(option));
    	return j;
    	
    }
    /**
     * 查询 柱状图
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryBarChart")
    @ResponseBody
    public requestJson queryBarChart(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【测斜数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);    	
    	map.put("type", type);    	
    	Option option = this.jcCxDataService.queryBarChart(map);
    	
    	System.out.println(JSONObject.toJSONString(option) );
    	j.setObj(JSONObject.toJSONString(option) );
    	return j;
    	
    }
    /**
     * 查询 柱状图
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryBarChart2")
    @ResponseBody
    public requestJson queryBarChart2(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【测斜数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);    	
    	map.put("type", type);    	
    	Option option = this.jcCxDataService.queryBarChart2(map);
    	
    	System.out.println(JSONObject.toJSONString(option) );
    	j.setObj(JSONObject.toJSONString(option) );
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
        logger.info("<{}>执行操作【测斜数据新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcCxDataService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【测斜数据修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcCxDataService.update(json.getMsg());
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
        logger.info("<{}>执行操作【测斜数据删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcCxDataService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
