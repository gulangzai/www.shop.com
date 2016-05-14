/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmcailiao.service.PmCailiaoController.java
 * 创建日期： 2016-03-24 上午 11:19:37
 * 功能：    服务控制类：监测项目
 * 所含类:   PmCailiaoService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-24 上午 11:19:37     创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmcailiao;


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

import com.ccthanking.business.pmcailiao.service.PmCailiaoService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmCailiaoController.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmCailiaoController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com"></a>
 * @version 0.1
 * @since 2016-03-24
 * 
 */

@Controller
@RequestMapping("/pmcailiao/pmCailiaoController")
public class PmCailiaoController {

	private static Logger logger = LoggerFactory.getLogger(PmCailiaoController.class);

    @Autowired
    private PmCailiaoService pmCailiaoService;

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
        logger.info("<{}>执行操作【监测项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmCailiaoService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    @RequestMapping(params = "queryFileList")
    @ResponseBody
    public requestJson queryFileList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工况查询】",user.getName());
    	requestJson j = new requestJson();
    	String cailiao_uid = request.getParameter("cailiao_uid");
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("cailiao_uid", cailiao_uid);
    	List<Map<String, String>> list = this.pmCailiaoService.queryFileList(map);
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
        logger.info("<{}>执行操作【监测项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmCailiaoService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【监测项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmCailiaoService.update(json.getMsg());
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
        logger.info("<{}>执行操作【监测项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmCailiaoService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(params = "deleteId")
    @ResponseBody
    public requestJson deleteId(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监测项目删除】",user.getName());
        String cailiaoUid = request.getParameter("CAILIAO_UID");
        requestJson j = new requestJson();
        
        boolean flag =this.pmCailiaoService.deleteId(cailiaoUid);
        j.setSuccess(flag);
        return j;
    }
}
