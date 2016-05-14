/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZhengGaiContentController.java
 * 创建日期： 2016-03-30 上午 10:35:43
 * 功能：    服务控制类：工程整改内容
 * 所含类:   PmZhengGaiContentService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:35:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.zhenggai.service.PmZhengGaiContentService;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmZhengGaiContentController.java </p>
 * <p> 功能：工程整改内容 </p>
 *
 * <p><a href="PmZhengGaiContentController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Controller
@RequestMapping("/zhenggai/pmZhengGaiContentController")
public class PmZhengGaiContentController {

	private static Logger logger = LoggerFactory.getLogger(PmZhengGaiContentController.class);

    @Autowired
    private PmZhengGaiContentService pmZhengGaiContentService;

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
        logger.info("<{}>执行操作【工程整改内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmZhengGaiContentService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j; 
    }
    
    @RequestMapping(params = "queryNewContent")
    @ResponseBody
    public requestJson queryNewContent(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【工程整改内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String zhenggai_uid = request.getParameter("ZHENGGAI_UID");
        String project_uid = request.getParameter("PROJECT_UID");
        PmZhengGaiVO vo = new PmZhengGaiVO();
        vo.setZhenggai_uid(zhenggai_uid);
        vo.setProject_uid(project_uid);
        domresult = this.pmZhengGaiContentService.queryNewContent(zhenggai_uid,vo);
        j.setMsg(domresult);
        return j; 
    }
    
    @RequestMapping(params = "queryContent")
    @ResponseBody
    public requestJson queryConditionContent(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【工程整改内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String ZHENGGAI_UID = request.getParameter("ZHENGGAI_UID");
        domresult = this.pmZhengGaiContentService.queryConditionContent(ZHENGGAI_UID);
        j.setMsg(domresult);
        return j; 
    }
    
    @RequestMapping(params = "queryContentList")
    @ResponseBody
    public requestJson queryContentList(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【工程整改内容查询列表】",user.getName());
        requestJson j = new requestJson();
        List obj = null;
        String ZHENGGAI_UID = request.getParameter("ZHENGGAI_UID");
        obj = this.pmZhengGaiContentService.queryContentList(ZHENGGAI_UID);
        j.setObj(obj);
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
        logger.info("<{}>执行操作【工程整改内容新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZhengGaiContentService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改内容修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZhengGaiContentService.update(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改内容删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZhengGaiContentService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
