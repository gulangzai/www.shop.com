/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectaccept.service.PmYanShouController.java
 * 创建日期： 2016-03-28 上午 11:50:46
 * 功能：    服务控制类：工程验收
 * 所含类:   PmYanShouService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:50:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectaccept;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.projectaccept.service.PmYanShouService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmYanShouController.java </p>
 * <p> 功能：工程验收 </p>
 *
 * <p><a href="PmYanShouController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

@Controller
@RequestMapping("/projectaccept/pmYanShouController")
public class PmYanShouController {

	private static Logger logger = LoggerFactory.getLogger(PmYanShouController.class);

    @Autowired
    private PmYanShouService pmYanShouService;

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
        logger.info("<{}>执行操作【工程验收查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmYanShouService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【工程验收新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmYanShouService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【工程验收修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmYanShouService.update(json.getMsg());
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
        logger.info("<{}>执行操作【工程验收删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmYanShouService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    @RequestMapping(params = "deleteById")
    @ResponseBody
    public requestJson deleteById(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【工程验收删除】",user.getName());
    	String yanshou_uid = request.getParameter("YANSHOU_UID");
    	requestJson j = new requestJson();
    	boolean flag = this.pmYanShouService.deleteById(yanshou_uid);
    	j.setSuccess(flag);
    	return j;
    }
}
