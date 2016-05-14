/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmbzjc.service.PmBzjcController.java
 * 创建日期： 2016-05-11 上午 09:12:48
 * 功能：    服务控制类：项目标准检查
 * 所含类:   PmBzjcService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 09:12:48  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmbzjc;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmbzjc.service.PmBzjcService;
import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.copj.common.page.Page;


/**
 * <p> PmBzjcController.java </p>
 * <p> 功能：项目标准检查 </p>
 *
 * <p><a href="PmBzjcController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Controller
@RequestMapping("/pmbzjc/pmBzjcController")
public class PmBzjcController {

	private static Logger logger = LoggerFactory.getLogger(PmBzjcController.class);

    @Autowired
    private PmBzjcService pmBzjcService;

    @RequestMapping(value = "queryTree")
    @ResponseBody
    public requestJson queryTreeCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        String domresult = "";
		Map xmmap=new HashMap();
		String number= request.getParameter("number");
		xmmap.put("NUMBER",( number!=null&&!"".equals(number))?number:"6");
		xmmap.put("JC_TYPE", request.getParameter("JC_TYPE"));
         
        domresult = this.pmBzjcService.queryTreeCondition(json.getMsg(),xmmap);
       
		
        j.setMsg(domresult); 
        
        System.out.println("j"+j.toString());
        return j;

    }
    
    @RequestMapping(params = "queryNewContent")
    @ResponseBody
    public requestJson queryNewContent(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【飞行检查内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String bzjc_uid = request.getParameter("BZJC_UID");
        String project_uid = request.getParameter("PROJECT_UID");
        PmBzjcVO vo = new PmBzjcVO();
        vo.setBzjc_uid(bzjc_uid);
        vo.setProject_uid(project_uid);
        domresult = this.pmBzjcService.queryNewContent(bzjc_uid,vo);
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
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目标准检查查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmBzjcService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目标准检查新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmBzjcService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【飞行检查修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmBzjcService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    @RequestMapping(params = "fankui")
    @ResponseBody
    protected requestJson fankui(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【飞行检查反馈】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmBzjcService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目标准检查删除】",user.getName());
        requestJson j = new requestJson(); 
        String PROJECT_UID = request.getParameter("PROJECT_UID");
        String BZJC_UID = request.getParameter("BZJC_UID"); 
        String resultVO = "";
        resultVO = this.pmBzjcService.delete(PROJECT_UID,BZJC_UID);
        j.setMsg(resultVO);
        return j;
    }

}
