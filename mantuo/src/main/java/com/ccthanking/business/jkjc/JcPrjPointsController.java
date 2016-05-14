/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPrjPointsController.java
 * 创建日期： 2015-10-30 上午 09:48:59
 * 功能：    服务控制类：项目监测点位
 * 所含类:   JcPrjPointsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:48:59  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.service.JcPrjPointsService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcPrjPointsController.java </p>
 * <p> 功能：项目监测点位 </p>
 *
 * <p><a href="JcPrjPointsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Controller
@RequestMapping("/jkjc/jcPrjPointsController")
public class JcPrjPointsController {

	private static Logger logger = LoggerFactory.getLogger(JcPrjPointsController.class);

    @Autowired
    private JcPrjPointsService jcPrjPointsService;

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
        logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcPrjPointsService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryById")
    @ResponseBody
    public requestJson queryById(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
    	requestJson j = new requestJson();
    	String PRJ_POINTS_UID = request.getParameter("PRJ_POINTS_UID");
    	String domresult = "";
    	domresult = this.jcPrjPointsService.queryById(PRJ_POINTS_UID);
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
    @RequestMapping(params = "queryU3DElementUids")
    @ResponseBody
    public requestJson queryU3DElementUids(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	String jpiuid = request.getParameter("jpiuid");
    	String type = request.getParameter("type");
    	map.put("puid", projectUid);
    	map.put("jpiuid", jpiuid);
    	map.put("type", type);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryU3DElementUids(map);
    	String elements[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			elements [i] = (String) m.get("U3D_ELEMENT_ID");
		}
    	j.setObj(JSONArray.toJSONString(elements));
    	return j;
    	
    }
    /**
     * 通过 项目监测点位中 element_uid 查找主键 PRJ_POINTS_UID查询出测点的图标
     * 显示在 U3D模型上
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    
    @RequestMapping(params = "queryIconById")
    @ResponseBody
    public requestJson queryIconById(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
       String eleId = request.getParameter("ELEID");
        requestJson j = new requestJson();
        String domresult = "";
    	List<Map<String, String>> list = this.jcPrjPointsService.queryIconById(eleId);
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
    @RequestMapping(params = "queryList")
    @ResponseBody
    public requestJson queryList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
    	requestJson j = new requestJson();
    	Map<String, String> map = new HashMap<String, String>();
    	String type = request.getParameter("type");
    	String lx  = request.getParameter("lx");
    	String project_uid = request.getParameter("project_uid");
    	String wdgz = request.getParameter("wdgz");
    	map.put("type", type);
    	map.put("lx", lx);
    	map.put("project_uid", project_uid);
    	map.put("wdgz", wdgz);
    	List<?> list = this.jcPrjPointsService.queryList(map);
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
    @RequestMapping(params = "queryPointForType")
    @ResponseBody
    public requestJson queryPointForType(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目监测点位查询】",user.getName());
        requestJson j = new requestJson();
        String type = request.getParameter("type");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        String domresult = "";
        domresult = this.jcPrjPointsService.queryPointForType(json.getMsg(),map);
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【项目监测点位新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPrjPointsService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    
    
    /**
     * 用户关注的点
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insertFocus")
    @ResponseBody
    protected requestJson insertFocus(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【用户关注点新增】",user.getName());
        requestJson j = new requestJson();
        String pri_points_uid = request.getParameter("PRJ_POINTS_UID");
        String resultVO = "";
        resultVO = this.jcPrjPointsService.insertFocus(pri_points_uid);
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 删除关注的 点
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "deleteFocus")
    @ResponseBody
    public requestJson deleteFocus(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【用户关注的项目监测点位删除】",user.getName());
        requestJson j = new requestJson();
        String pri_points_uid = request.getParameter("PRJ_POINTS_UID");
        boolean bs =this.jcPrjPointsService.deleteFocus(pri_points_uid);
        j.setSuccess(bs);
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
        logger.info("<{}>执行操作【项目监测点位修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPrjPointsService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目监测点位删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPrjPointsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryPoints")
    @ResponseBody
    public requestJson queryPoints(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryPoints(map);
    	String pointids[] = new String[list.size()];
    	String pointelementids[] = new String[list.size()];
    	String pointcodes[] = new String[list.size()];
    	String pointinitheight[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointelementids[i] = (String) m.get("U3D_ELEMENT_ID");
			pointcodes[i] = (String) m.get("POINT_CODE");
			pointinitheight[i] = (String) m.get("INIT_HEIGHT");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("U3D_ELEMENT_ID", pointelementids);
    	returnmap.put("POINT_CODE", pointcodes);
    	returnmap.put("INIT_HEIGHT", pointinitheight);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryItems")
    @ResponseBody
    public requestJson queryItems(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测项查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryItems(map);
    	String itemids[] = new String[list.size()];
    	String itemobjectids[] = new String[list.size()];
    	String itemtypeids[] = new String[list.size()];
    	String itemnames[] = new String[list.size()];
    	String itemshortnames[] = new String[list.size()];
    	String itemtypes[] = new String[list.size()];
    	String itemunits[] = new String[list.size()];
    	String itemprecodes[] = new String[list.size()];
    	String itemiconfiles[] = new String[list.size()];
    	String itemsinglewarns[] = new String[list.size()];
    	String itemtotalwarn1s[] = new String[list.size()];
    	String itemtotalwarn2s[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			itemids[i] = (String) m.get("JC_PRJ_ITEM_UID");
			itemobjectids[i] = (String) m.get("JC_OBJECT_UID");
			itemtypeids[i] = (String) m.get("JC_TYPE_UID");
			itemnames[i] = (String) m.get("JC_NAME");
			itemshortnames[i] = (String) m.get("SHORT_NAME");
			itemtypes[i] = (String) m.get("ITEM_TYPE");
			itemunits[i] = (String) m.get("UNIT");
			itemprecodes[i] = (String) m.get("PRE_CODE");
			itemiconfiles[i] = (String) m.get("ICON_FILE");
			itemsinglewarns[i] = (String) m.get("SINGLE_WARN");
			itemtotalwarn1s[i] = (String) m.get("TOTAL_WARN1");
			itemtotalwarn2s[i] = (String) m.get("TOTAL_WARN2");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("JC_PRJ_ITEM_UID", itemids);
    	returnmap.put("JC_OBJECT_UID", itemobjectids);
    	returnmap.put("JC_TYPE_UID", itemtypeids);
    	returnmap.put("JC_NAME", itemnames);
    	returnmap.put("SHORT_NAME", itemshortnames);
    	returnmap.put("ITEM_TYPE", itemtypes);
    	returnmap.put("UNIT", itemunits);
    	returnmap.put("PRE_CODE", itemprecodes);
    	returnmap.put("ICON_FILE", itemiconfiles);
    	returnmap.put("SINGLE_WARN", itemsinglewarns);
    	returnmap.put("TOTAL_WARN1", itemtotalwarn1s);
    	returnmap.put("TOTAL_WARN2", itemtotalwarn2s);
    	j.setObj(returnmap);
    	return j;
    	
    }

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryItemTypes")
    @ResponseBody
    public requestJson queryItemTypes(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测项监测类型查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	List<Map<String,String>> list = this.jcPrjPointsService.queryItemTypes(map);
    	String itemtypeids[] = new String[list.size()];
    	String itemtypenames[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			itemtypeids[i] = (String) m.get("JC_TYPE_UID");
			itemtypenames[i] = (String) m.get("JC_TYPE_NAME");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("JC_TYPE_UID", itemtypeids);
    	returnmap.put("JC_TYPE_NAME", itemtypenames);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryPoint_Item")
    @ResponseBody
    public requestJson queryPoint_Item(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点监测项查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryPoint_Item(map);
    	String point_item_ids[] = new String[list.size()];
    	String pointids[] = new String[list.size()];
    	String pointitemids[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			point_item_ids[i] = (String) m.get("PRJ_POINT_ITEM_UID");
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointitemids[i] = (String) m.get("JC_PRJ_ITEM_UID");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINT_ITEM_UID", point_item_ids);
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("JC_PRJ_ITEM_UID", pointitemids);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryPointProperty")
    @ResponseBody
    public requestJson queryPointProperty(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目监测点预警值查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryPointProperty(map);
    	String point_item_ids[] = new String[list.size()];
    	String point_single_warns[] = new String[list.size()];
    	String point_total_warn1s[] = new String[list.size()];
    	String point_total_warn2s[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			point_item_ids[i] = (String) m.get("PRJ_POINT_ITEM_UID");
			point_single_warns[i] = (String) m.get("SINGLE_WARN");
			point_total_warn1s[i] = (String) m.get("TOTAL_WARN1");
			point_total_warn2s[i] = (String) m.get("TOTAL_WARN2");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINT_ITEM_UID", point_item_ids);
    	returnmap.put("SINGLE_WARN", point_single_warns);
    	returnmap.put("TOTAL_WARN1", point_total_warn1s);
    	returnmap.put("TOTAL_WARN2", point_total_warn2s);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryPointData_jc")
    @ResponseBody
    public requestJson queryPointData_jc(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目沉降监测点最新数据查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryPointData_jc(map);
    	String pointids[] = new String[list.size()];
    	String pointreportdates[] = new String[list.size()];
    	String pointvvalues[] = new String[list.size()];
    	String pointvvaluediffs[] = new String[list.size()];
    	String pointhvalues[] = new String[list.size()];
    	String pointhvaluediffs[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointreportdates[i] = (String) m.get("REPORT_DATE");
			pointvvalues[i] = (String) m.get("VERTICAL_VALUE");
			pointvvaluediffs[i] = (String) m.get("VERTICAL_VALUE_DIFF");
			pointhvalues[i] = (String) m.get("HORIZONTAL_VALUE");
			pointhvaluediffs[i] = (String) m.get("HORIZONTAL_VALUE_DIFF");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("REPORT_DATE", pointreportdates);
    	returnmap.put("VERTICAL_VALUE", pointvvalues);
    	returnmap.put("VERTICAL_VALUE_DIFF", pointvvaluediffs);
    	returnmap.put("HORIZONTAL_VALUE", pointhvalues);
    	returnmap.put("HORIZONTAL_VALUE_DIFF", pointhvaluediffs);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryPointData_cx")
    @ResponseBody
    public requestJson queryPointData_cx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目测斜监测点最新数据查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String projectUid  = request.getParameter("project_uid");
    	map.put("puid", projectUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryPointData_cx(map);
    	String pointids[] = new String[list.size()];
    	String pointdeeps[] = new String[list.size()];
    	String pointreportdates[] = new String[list.size()];
    	String pointhvalues[] = new String[list.size()];
    	String pointhvaluediffs[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointreportdates[i] = (String) m.get("REPORT_DATE");
			pointdeeps[i] = (String) m.get("DEEP");
			pointhvalues[i] = (String) m.get("HORIZONTAL_VALUE");
			pointhvaluediffs[i] = (String) m.get("HORIZONTAL_VALUE_DIFF");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("REPORT_DATE", pointreportdates);
    	returnmap.put("DEEP", pointdeeps);
    	returnmap.put("HORIZONTAL_VALUE", pointhvalues);
    	returnmap.put("HORIZONTAL_VALUE_DIFF", pointhvaluediffs);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryUser_Focus_Point")
    @ResponseBody
    public requestJson queryUser_Focus_Point(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【用户关注点数据查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("USER_UID", user.getIdCard());
    	List<Map<String,String>> list = this.jcPrjPointsService.queryUser_Focus_Point(map);
    	String pointids[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryNewestDataByPointId_jc")
    @ResponseBody
    public requestJson queryNewestDataByPointId_jc(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目沉降监测点最新数据查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String pointUid  = request.getParameter("PRJ_POINTS_UID");
    	map.put("PRJ_POINTS_UID", pointUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryNewestDataByPointId_jc(map);
    	String pointids[] = new String[list.size()];
    	String pointreportdates[] = new String[list.size()];
    	String pointvvalues[] = new String[list.size()];
    	String pointvvaluediffs[] = new String[list.size()];
    	String pointhvalues[] = new String[list.size()];
    	String pointhvaluediffs[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointreportdates[i] = (String) m.get("REPORT_DATE");
			pointvvalues[i] = (String) m.get("VERTICAL_VALUE");
			pointvvaluediffs[i] = (String) m.get("VERTICAL_VALUE_DIFF");
			pointhvalues[i] = (String) m.get("HORIZONTAL_VALUE");
			pointhvaluediffs[i] = (String) m.get("HORIZONTAL_VALUE_DIFF");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("REPORT_DATE", pointreportdates);
    	returnmap.put("VERTICAL_VALUE", pointvvalues);
    	returnmap.put("VERTICAL_VALUE_DIFF", pointvvaluediffs);
    	returnmap.put("HORIZONTAL_VALUE", pointhvalues);
    	returnmap.put("HORIZONTAL_VALUE_DIFF", pointhvaluediffs);
    	j.setObj(returnmap);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryNewestDataByPointId_cx")
    @ResponseBody
    public requestJson queryNewestDataByPointId_cx(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目测斜监测点最新数据查询】",user.getName());
    	requestJson j = new requestJson();
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	String pointUid  = request.getParameter("PRJ_POINTS_UID");
    	map.put("PRJ_POINTS_UID", pointUid);
    	List<Map<String,String>> list = this.jcPrjPointsService.queryNewestDataByPointId_cx(map);
    	String pointids[] = new String[list.size()];
    	String pointdeeps[] = new String[list.size()];
    	String pointreportdates[] = new String[list.size()];
    	String pointhvalues[] = new String[list.size()];
    	String pointhvaluediffs[] = new String[list.size()];
    	
    	for (int i = 0; i < list.size(); i++) {
			Map<String,String> m = list.get(i);
			pointids[i] = (String) m.get("PRJ_POINTS_UID");
			pointreportdates[i] = (String) m.get("REPORT_DATE");
			pointdeeps[i] = (String) m.get("DEEP");
			pointhvalues[i] = (String) m.get("HORIZONTAL_VALUE");
			pointhvaluediffs[i] = (String) m.get("HORIZONTAL_VALUE_DIFF");
		}
    	
    	HashMap<String, String[]> returnmap = new HashMap<String, String[]>();
    	returnmap.put("PRJ_POINTS_UID", pointids);
    	returnmap.put("REPORT_DATE", pointreportdates);
    	returnmap.put("DEEP", pointdeeps);
    	returnmap.put("HORIZONTAL_VALUE", pointhvalues);
    	returnmap.put("HORIZONTAL_VALUE_DIFF", pointhvaluediffs);
    	j.setObj(returnmap);
    	return j;
    	
    }

}
