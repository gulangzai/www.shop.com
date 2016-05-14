/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcJcDataController.java
 * 创建日期： 2015-10-30 上午 09:30:58
 * 功能：    服务控制类：监测数据
 * 所含类:   JcJcDataService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:30:58  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.service.JcJcDataService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcJcDataController.java </p>
 * <p> 功能：监测数据 </p>
 *
 * <p><a href="JcJcDataController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Controller
@RequestMapping("/jkjc/jcJcDataController")
public class JcJcDataController {

	private static Logger logger = LoggerFactory.getLogger(JcJcDataController.class);

    @Autowired
    private JcJcDataService jcJcDataService;


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
        logger.info("<{}>执行操作【监测数据查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcJcDataService.queryCondition(json.getMsg());
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
    	//List<Map<String,obj>>  domresult = new ArrayList<Map<String,String>>();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type  = request.getParameter("type");
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);
    	map.put("type", type);
    	Map<String,Object> domresult = this.jcJcDataService.queryTab(map);
    	j.setObj(domresult);
    	
    	return j;
    	
    }
    
    /**
     * 折线图
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryLineChart")
    @ResponseBody
    public requestJson queryLineChart(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
    	requestJson j = new requestJson();
    	String code  = request.getParameter("code");
    	String time1 = request.getParameter("time1");
    	String time2 = request.getParameter("time2");
    	String type = request.getParameter("type");
    	String projectUid = request.getParameter("projectUid");
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("code", code);
    	map.put("time1", time1);
    	map.put("time2", time2);
    	map.put("type", type);
    	map.put("projectUid", projectUid);
    	Map<String,Object> map1 = this.jcJcDataService.queryLineChart(map);
    	
    	//String jsonstr = "{title:{text:'未来一周气温变化',subtext:'纯属虚构'},tooltip:{trigger:'axis'},legend:{data:['最高气温','最低气温']},toolbox:{show:true,feature:{mark:{show:true},dataView:{show:true,readOnly:false},magicType:{show:true,type:['line','bar']},restore:{show:true},saveAsImage:{show:true}}},calculable:true,xAxis:[{type:'category',boundaryGap:false,data:['周一','周二','周三','周四','周五','周六','周日']}],yAxis:[{type:'value',axisLabel:{formatter:'{value} °C'}}],series:[{name:'最高气温',type:'line',data:[11,11,15,13,12,13,10],markPoint:{data:[{type:'max',name:'最大值'},{type:'min',name:'最小值'}]},markLine:{data:[{type:'average',name:'平均值'}]}},{name:'最低气温',type:'line',data:[1,-2,2,5,3,2,0],markPoint:{data:[{name:'周最低',value:-2,xAxis:1,yAxis:-1.5}]},markLine:{data:[{type:'average',name:'平均值'}]}}]}";
    	
    	j.setObj(JSONObject.toJSONString(map1));
    	return j;
    	
    }
    
    /**
     * 柱状图
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryBarChart")
    @ResponseBody
    public requestJson queryBarChart(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测数据查询】",user.getName());
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
    	Map<String,Object> map1 = this.jcJcDataService.queryBarChart(map);
    	
    	
    	j.setObj(JSONObject.toJSONString(map1) );
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
        logger.info("<{}>执行操作【监测数据新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJcDataService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监测数据修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJcDataService.update(json.getMsg());
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
        logger.info("<{}>执行操作【监测数据删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJcDataService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
