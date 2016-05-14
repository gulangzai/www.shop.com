/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmXianchangDafuController.java
 * 创建日期： 2016-01-27 下午 06:45:28
 * 功能：    服务控制类：现场状况答复
 * 所含类:   PmXianchangDafuService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-27 下午 06:45:28  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmxianchang.service.PmXianchangDafuService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmXianchangDafuController.java </p>
 * <p> 功能：现场状况答复 </p>
 *
 * <p><a href="PmXianchangDafuController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-27
 * 
 */

@Controller
@RequestMapping("/pmxianchang/pmXianchangDafuController")
public class PmXianchangDafuController {

	private static Logger logger = LoggerFactory.getLogger(PmXianchangDafuController.class);

    @Autowired
    private PmXianchangDafuService pmXianchangDafuService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDetails")
    @ResponseBody
    public requestJson queryDetails(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【现场状况答复查询】",user.getName());
    	requestJson j = new requestJson();
    	String xc_uid =request.getParameter("XC_UID");
    	String fileType = request.getParameter("FILE_TYPE");
    	Map<String, List<?>> map = this.pmXianchangDafuService.queryDetails(xc_uid,fileType);
    	
    	/** 发布者的头像**/
    	List<?> allAnswer = (List<?>) map.get("allAnswer");
    	System.out.println(allAnswer);
    	for (int i = 0; i < allAnswer.size(); i++) {
    		Map rmap = (Map) allAnswer.get(i);
    		List<Map<String,String>> allFileList = (List<Map<String, String>>) rmap.get("allFileList");
        	for (Map<String, String> vmap : allFileList) {
        		vmap.put("FILE_ROOT_PATH", vmap.get("FILE_PATH"));
    			vmap.put("FILE_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("FILE_PATH"));
    			
    			
    		}
    		
		}
    	
    	   List<?>  userxx = (List<Map<String,String>>) map.get("allAnswer");
    		for (int i=0;i<userxx.size();i++) {
    			Map rmap = (Map)userxx.get(i);
    			rmap.put("IMG_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+rmap.get("IMG_PATH"));
    			
    		}
			
			
		
    	
    	
    	j.setObj(map);
    	return j;

    }
    
    /**
     * 查询最新的回复数据
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryNewAnswer")
    @ResponseBody
    public requestJson queryNewAnswer(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【现场状况答复查询】",user.getName());
    	requestJson j = new requestJson();
    	String xc_uid =request.getParameter("XC_UID");
    	String fileType = request.getParameter("FILE_TYPE");
    	Map<String, List<?>> map = this.pmXianchangDafuService.queryNewAnswer(xc_uid,fileType);
    	List<Map<String,String>> queryFileList = (List<Map<String, String>>) map.get("queryFileList");
    	for (Map<String, String> vmap : queryFileList) {
    		vmap.put("FILE_ROOT_PATH", vmap.get("FILE_PATH"));
			vmap.put("FILE_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("FILE_PATH"));
			
		}
    	
    	/** 发布者的头像**/
    	List<Map<String,String>> userxx = (List<Map<String,String>>) map.get("userxx");
    	for (Map<String, String> vmap : userxx) {
			vmap.put("IMG_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("IMG_PATH"));
			System.out.println(vmap.get("IMG_PATH"));
		}
    	
    	j.setObj(map);
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
        logger.info("<{}>执行操作【现场状况答复新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXianchangDafuService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【现场状况答复修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXianchangDafuService.update(json.getMsg());
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
        logger.info("<{}>执行操作【现场状况答复删除】",user.getName());
        requestJson j = new requestJson();
        String xcdfId = request.getParameter("XCDF_UID");
        String resultVO = "";
        resultVO = this.pmXianchangDafuService.delete(xcdfId);
        j.setMsg(resultVO);
        return j;
    }

}
