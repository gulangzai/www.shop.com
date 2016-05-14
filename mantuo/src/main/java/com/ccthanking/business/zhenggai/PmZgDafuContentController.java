/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZgDafuContentController.java
 * 创建日期： 2016-03-30 上午 10:43:36
 * 功能：    服务控制类：工程整改答复内容
 * 所含类:   PmZgDafuContentService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:43:36  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.zhenggai.service.PmZgDafuContentService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmZgDafuContentController.java </p>
 * <p> 功能：工程整改答复内容 </p>
 *
 * <p><a href="PmZgDafuContentController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Controller
@RequestMapping("/zhenggai/pmZgDafuContentController")
public class PmZgDafuContentController {

	private static Logger logger = LoggerFactory.getLogger(PmZgDafuContentController.class);

    @Autowired
    private PmZgDafuContentService pmZgDafuContentService;

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
        logger.info("<{}>执行操作【工程整改答复内容查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmZgDafuContentService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j; 
    }
    
    @RequestMapping(params = "querySon")
    @ResponseBody
    public requestJson  querySon(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【工程整改字表查询】querySon",user.getName());
        requestJson j = new requestJson(); 
        List domresult = null;
        String ZG_DAFU_UID = request.getParameter("ZG_DAFU_UID");
          domresult = this.pmZgDafuContentService.querySon(json.getMsg(),ZG_DAFU_UID);
        //j.setMsg(domresult);
        j.setObj(domresult);
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
        logger.info("<{}>执行操作【工程整改答复内容新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZgDafuContentService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改答复内容修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZgDafuContentService.update(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改答复内容删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZgDafuContentService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
