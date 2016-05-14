/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmJianchaBzController.java
 * 创建日期： 2016-02-23 上午 09:54:56
 * 功能：    服务控制类：项目检查内容
 * 所含类:   PmJianchaBzService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 09:54:56  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmjiancha.service.PmJianchaBzService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmJianchaBzController.java </p>
 * <p> 功能：项目检查内容 </p>
 *
 * <p><a href="PmJianchaBzController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

@Controller
@RequestMapping("/pmjiancha/pmJianchaBzController")
public class PmJianchaBzController {

	private static Logger logger = LoggerFactory.getLogger(PmJianchaBzController.class);

    @Autowired
    private PmJianchaBzService pmJianchaBzService;

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
        logger.info("<{}>执行操作【项目检查内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmJianchaBzService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryBz")
    @ResponseBody
    public requestJson queryBz(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目检查内容查询】",user.getName());
    	requestJson j = new requestJson();
    	String domresult = "";
    	domresult = this.pmJianchaBzService.queryBz(json.getMsg());
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
    @RequestMapping(params = "queryWgnr")
    @ResponseBody
    public requestJson queryWgnr(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目检查内容查询】",user.getName());
    	requestJson j = new requestJson();
    	String guifan_uid = request.getParameter("guifan_uid");
    	List<Map<String, String>> list = this.pmJianchaBzService.queryWgnr(guifan_uid);
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
        logger.info("<{}>执行操作【项目检查内容新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmJianchaBzService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目检查内容修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmJianchaBzService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目检查内容删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmJianchaBzService.delete(json.getMsg());
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
    @RequestMapping(params = "deleteById")
    @ResponseBody
    public requestJson deleteById(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目标准规范删除】",user.getName());
    	requestJson j = new requestJson();
    	String uid = request.getParameter("JIANCHA_BZ_UID");
    	boolean flag = this.pmJianchaBzService.deleteById(uid);
    	j.setSuccess(flag);
    	return j;
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryJcjb")
    @ResponseBody
    public requestJson queryJcjb(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目检查内容查询】",user.getName());
        requestJson j = new requestJson();
        List<Map<String, String>> list = this.pmJianchaBzService.queryJcjb();
        j.setObj(list);
        return j;

    }

}
