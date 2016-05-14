/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    xmgk.service.PmGongkuangController.java
 * 创建日期： 2015-11-25 下午 01:16:52
 * 功能：    服务控制类：项目工况
 * 所含类:   PmGongkuangService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-11-25 下午 01:16:52  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.xmgk;


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

import com.ccthanking.business.xmgk.service.PmGongkuangService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmGongkuangController.java </p>
 * <p> 功能：项目工况 </p>
 *
 * <p><a href="PmGongkuangController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-11-25
 * 
 */

@Controller
@RequestMapping("/xmgk/pmGongkuangController")
public class PmGongkuangController {

	private static Logger logger = LoggerFactory.getLogger(PmGongkuangController.class);

    @Autowired
    private PmGongkuangService pmGongkuangService;

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
        logger.info("<{}>执行操作【项目工况查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmGongkuangService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询 工况模型文件列表
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryFileList")
    @ResponseBody
    public requestJson queryFileList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工况查询】",user.getName());
    	requestJson j = new requestJson();
    	String gongkuang_uid = request.getParameter("gongkuang_uid");
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("gongkuang_uid", gongkuang_uid);
    	List<Map<String, String>> list = this.pmGongkuangService.queryFileList(map);
    	j.setObj(list);
    	return j;
    	
    }
    
	/**
	 * 查询文件列表
	 * 
	 * @param request
	 * @param js
	 */
	@RequestMapping(params = "queryFileByType")
	@ResponseBody
	public requestJson queryFileByType(HttpServletRequest request) throws Exception {
		requestJson j = new requestJson();
		String targetUid = request.getParameter("targetUid");
		String file_type = request.getParameter("file_type");
		String root = request.getContextPath();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("targetUid", targetUid);
		map.put("file_type", file_type);
		map.put("root", root);
		List<Map<String, Object>> list = this.pmGongkuangService.queryFileByType(map);
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
        logger.info("<{}>执行操作【项目工况新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmGongkuangService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目工况修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmGongkuangService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    /**
     * 设置工况
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "pmGongkuangSet")
    @ResponseBody
    protected requestJson pmGongkuangSet(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工况设置】",user.getName());
    	requestJson j = new requestJson();
    	
    	String gongkuang_uid = request.getParameter("gongkuang_uid");
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("gongkuang_uid", gongkuang_uid);
    	
    	boolean flag = this.pmGongkuangService.pmGongkuangSet(map);
    	j.setSuccess(flag);
    	
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
        logger.info("<{}>执行操作【项目工况删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmGongkuangService.delete(json.getMsg());
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
    @RequestMapping(params = "deleteByid")
    @ResponseBody
    public requestJson deleteByid(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工况删除】",user.getName());
    	requestJson j = new requestJson();
    	String gongkuang_uid = request.getParameter("GONGKUANG_UID");
    	boolean flag = this.pmGongkuangService.deleteByid(gongkuang_uid);
    	j.setSuccess(flag);
    	return j;
    }

}
