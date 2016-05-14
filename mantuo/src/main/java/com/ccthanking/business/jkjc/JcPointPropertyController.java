/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPointPropertyController.java
 * 创建日期： 2015-10-30 上午 09:38:07
 * 功能：    服务控制类：监测点属性值
 * 所含类:   JcPointPropertyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:38:07  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.jkjc.service.JcPointPropertyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcPointPropertyController.java </p>
 * <p> 功能：监测点属性值 </p>
 *
 * <p><a href="JcPointPropertyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Controller
@RequestMapping("/jkjc/jcPointPropertyController")
public class JcPointPropertyController {

	private static Logger logger = LoggerFactory.getLogger(JcPointPropertyController.class);

    @Autowired
    private JcPointPropertyService jcPointPropertyService;

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
        logger.info("<{}>执行操作【监测点属性值查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcPointPropertyService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【监测点属性值新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPointPropertyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监测点属性值修改】",user.getName());
        requestJson j = new requestJson();
    	HashMap<String, String> map = new HashMap<String, String>();
    	String pointitemid  = request.getParameter("PRJ_POINT_ITEM_UID");
    	map.put("PRJ_POINT_ITEM_UID", pointitemid);
    	String singlewarn  = request.getParameter("SINGLE_WARN");
    	map.put("SINGLE_WARN", singlewarn);
    	String totalwarn1  = request.getParameter("TOTAL_WARN1");
    	map.put("TOTAL_WARN1", totalwarn1);
    	String totalwarn2  = request.getParameter("TOTAL_WARN2");
    	map.put("TOTAL_WARN2", totalwarn2);
    	String zyfw = request.getParameter("zyfw");
    	map.put("zyfw", zyfw);
    	String jcitemUid = request.getParameter("jcitemUid");
    	map.put("jcitemUid", jcitemUid);
    	String project_uid = request.getParameter("project_uid");
    	map.put("project_uid", project_uid);
    	String result = this.jcPointPropertyService.update(map);
    	j.setObj(result);
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
        logger.info("<{}>执行操作【监测点属性值删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPointPropertyService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "querybyTypeId")
    @ResponseBody
    public requestJson querybyTypeId(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监测点属性值查询】",user.getName());
        requestJson j = new requestJson();
        String itemId = request.getParameter("ITEMID");
        String typeId = request.getParameter("TYPEID");
        String domresult = "";
        domresult = this.jcPointPropertyService.querybyTypeId(itemId,typeId);
       
        j.setMsg(domresult);
        return j;

    }

    /**
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "querybyPoint_Item_Id")
    @ResponseBody
    public requestJson querybyPoint_Item_Id(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【预警值查询】",user.getName());
        requestJson j = new requestJson();
        String pointitemId = request.getParameter("POINT_ITEM_ID");
        String domresult = "";
        domresult = this.jcPointPropertyService.querybyPoint_Item_Id(pointitemId);
       
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryTypenameByTypeid")
    @ResponseBody
    public requestJson queryTypenameByTypeid(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【类型名称查询】",user.getName());
        requestJson j = new requestJson();
        String typeId = request.getParameter("TYPE_UID");
        String domresult = "";
        domresult = this.jcPointPropertyService.queryTypenameByTypeid(typeId);
       
        j.setMsg(domresult);
        return j;

    }
    
}
