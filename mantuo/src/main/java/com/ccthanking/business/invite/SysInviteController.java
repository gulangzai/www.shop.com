/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    invite.service.SysInviteController.java
 * 创建日期： 2016-04-25 下午 01:12:45
 * 功能：    服务控制类：系统邀请
 * 所含类:   SysInviteService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 01:12:45  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.invite;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.invite.service.SysInviteService;
import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SysInviteController.java </p>
 * <p> 功能：系统邀请 </p>
 *
 * <p><a href="SysInviteController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

@Controller
@RequestMapping("/invite/sysInviteController")
public class SysInviteController {

	private static Logger logger = LoggerFactory.getLogger(SysInviteController.class);

    @Autowired
    private SysInviteService sysInviteService;

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
        logger.info("<{}>执行操作【系统邀请查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sysInviteService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "querySingle")
    @ResponseBody
    public requestJson querySingle(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【系统邀请查询】",user.getName());
        requestJson j = new requestJson(); 
        String invite_uid = request.getParameter("invite_uid");
        SysInviteVO sysInviteVo  = this.sysInviteService.get(invite_uid);
        j.setObj(sysInviteVo);
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
        logger.info("<{}>执行操作【系统邀请新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysInviteService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【系统邀请修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysInviteService.update(json.getMsg());
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
        logger.info("<{}>执行操作【系统邀请删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysInviteService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
