/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    message.service.BuUserMessageController.java
 * 创建日期： 2016-04-21 上午 11:18:28
 * 功能：    服务控制类：个人消息
 * 所含类:   BuUserMessageService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-21 上午 11:18:28  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.message;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.message.service.BuUserMessageService;
import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuUserMessageController.java </p>
 * <p> 功能：个人消息 </p>
 *
 * <p><a href="BuUserMessageController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-21
 * 
 */

@Controller
@RequestMapping("/message/buUserMessageController")
public class BuUserMessageController {

	private static Logger logger = LoggerFactory.getLogger(BuUserMessageController.class);

    @Autowired
    private BuUserMessageService buUserMessageService;

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
        logger.info("<{}>执行操作【个人消息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String user_uid = request.getParameter("user_uid");
        domresult = this.buUserMessageService.queryCondition(json.getMsg(),user_uid);
        j.setMsg(domresult);
        return j; 
    }
    
    @RequestMapping(params = "queryDetail")
    @ResponseBody
    public requestJson queryDetail(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【个人消息查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        String  user_message_uid = request.getParameter("USER_MESSAGE_UID"); 
        String status = request.getParameter("STATUS");
        buUserMessageService.updateById(user_message_uid,status);
        domresult = this.buUserMessageService.queryByUserMessageId(json.getMsg(),user_message_uid);
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
        logger.info("<{}>执行操作【个人消息新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buUserMessageService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【个人消息修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buUserMessageService.update(json.getMsg());
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
        logger.info("<{}>执行操作【个人消息删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.buUserMessageService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
