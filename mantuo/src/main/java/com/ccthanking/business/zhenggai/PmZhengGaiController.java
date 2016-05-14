/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZhengGaiController.java
 * 创建日期： 2016-03-30 上午 10:31:40
 * 功能：    服务控制类：工程整改
 * 所含类:   PmZhengGaiService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:31:40  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.zhenggai.service.PmZhengGaiService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmZhengGaiController.java </p>
 * <p> 功能：工程整改 </p>
 *
 * <p><a href="PmZhengGaiController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Controller
@RequestMapping("/zhenggai/pmZhengGaiController")
public class PmZhengGaiController {

	private static Logger logger = LoggerFactory.getLogger(PmZhengGaiController.class);

    @Autowired
    private PmZhengGaiService pmZhengGaiService;

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
        logger.info("<{}>执行操作【工程整改查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmZhengGaiService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmZhengGaiService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改修改】",user.getName());
        requestJson j = new requestJson(); 
        String resultVO = "";
        resultVO = this.pmZhengGaiService.update(json.getMsg());
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
        logger.info("<{}>执行操作【工程整改删除】",user.getName());
        requestJson j = new requestJson();
        String PROJECT_UID = request.getParameter("PROJECT_UID");
        String ZHENGGAI_UID = request.getParameter("ZHENGGAI_UID");
        String resultVO = "";
        resultVO = this.pmZhengGaiService.delete(PROJECT_UID,ZHENGGAI_UID);
        j.setMsg(resultVO);
        return j;
    }

}
