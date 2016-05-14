/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianContentController.java
 * 创建日期： 2016-03-02 下午 02:21:23
 * 功能：    服务控制类：巡检检查内容
 * 所含类:   PmXunjianContentService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:21:23  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.pmjiancha.service.PmXunjianContentService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmXunjianContentController.java </p>
 * <p> 功能：巡检检查内容 </p>
 *
 * <p><a href="PmXunjianContentController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Controller
@RequestMapping("/pmjiancha/pmXunjianContentController")
public class PmXunjianContentController {

	private static Logger logger = LoggerFactory.getLogger(PmXunjianContentController.class);

    @Autowired
    private PmXunjianContentService pmXunjianContentService;

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
        logger.info("<{}>执行操作【巡检检查内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmXunjianContentService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryByXunjianUid")
    @ResponseBody
    public requestJson queryByXunjianUid(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【巡检检查内容查询】",user.getName());
    	requestJson j = new requestJson();
    	String xunjianUid = request.getParameter("xunjianUid");
    	List<Map<String,String>> list = this.pmXunjianContentService.queryByXunjianUid(xunjianUid);
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
        logger.info("<{}>执行操作【巡检检查内容新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianContentService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【巡检检查内容修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianContentService.update(json.getMsg());
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
        logger.info("<{}>执行操作【巡检检查内容删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianContentService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
