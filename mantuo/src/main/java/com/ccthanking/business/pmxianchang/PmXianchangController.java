/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmXianchangController.java
 * 创建日期： 2016-01-22 上午 10:45:08
 * 功能：    服务控制类：进度质量安全
 * 所含类:   PmXianchangService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-22 上午 10:45:08  卢红冈   创建文件，实现基本功能
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

import com.ccthanking.business.pmxianchang.service.PmXianchangService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmXianchangController.java </p>
 * <p> 功能：进度质量安全 </p>
 *
 * <p><a href="PmXianchangController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-22
 * 
 */

@Controller
@RequestMapping("/pmxianchang/pmXianchangController")
public class PmXianchangController {

	private static Logger logger = LoggerFactory.getLogger(PmXianchangController.class);

    @Autowired
    private PmXianchangService pmXianchangService;

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
        logger.info("<{}>执行操作【进度质量安全查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmXianchangService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    /**
     * 查询json
     * 
     * @param json
     * @return queryXcXx
     * @throws Exception
     */
    @RequestMapping(params= "queryXcQK")
    @ResponseBody
    public requestJson queryXcQK(final HttpServletRequest request, requestJson json) throws Exception {
    	logger.info("<{}>执行操作【现场状况查询】");
    	requestJson j = new requestJson();
    	String xc_uid =request.getParameter("XC_UID");
    	String fileType = request.getParameter("FILE_TYPE");
    	Map<String, List<?>> map = this.pmXianchangService.queryXcQK(xc_uid,fileType);
    	/** 发布者的头像**/
    	List<Map<String,String>> queryFileList = (List<Map<String, String>>) map.get("userPic");
    	for (Map<String, String> vmap : queryFileList) {
			vmap.put("IMG_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("IMG_PATH"));
		}
    	
    	/** 发布现场状况的 附件信息*/
    	List<Map<String,String>> allFile = (List<Map<String, String>>) map.get("allFile");
    	for (Map<String, String> vmap : allFile) {
    		vmap.put("FILE_ROOT_PATH", vmap.get("FILE_PATH"));
			vmap.put("FILE_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("FILE_PATH"));
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
        logger.info("<{}>执行操作【进度质量安全新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXianchangService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【现场进度状况修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXianchangService.update(json.getMsg());
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
        logger.info("<{}>执行操作【进度质量安全删除】",user.getName());
        requestJson j = new requestJson();
        String xc_uid = request.getParameter("XC_UID");
        String xs_uid = request.getParameter("XS_UID");
        String resultVO = "";
        resultVO = this.pmXianchangService.delete(xc_uid,xs_uid);
        j.setMsg(resultVO);
        return j;
    }

}
