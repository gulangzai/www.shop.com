/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianStrucController.java
 * 创建日期： 2016-03-02 下午 02:24:31
 * 功能：    服务控制类：巡检对应的项目结构
 * 所含类:   PmXunjianStrucService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:24:31  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmjiancha.service.PmXunjianStrucService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmXunjianStrucController.java </p>
 * <p> 功能：巡检对应的项目结构 </p>
 *
 * <p><a href="PmXunjianStrucController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Controller
@RequestMapping("/pmjiancha/pmXunjianStrucController")
public class PmXunjianStrucController {

	private static Logger logger = LoggerFactory.getLogger(PmXunjianStrucController.class);

    @Autowired
    private PmXunjianStrucService pmXunjianStrucService;

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
        logger.info("<{}>执行操作【巡检对应的项目结构查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmXunjianStrucService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【巡检对应的项目结构新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianStrucService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【巡检对应的项目结构修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianStrucService.update(json.getMsg());
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
        logger.info("<{}>执行操作【巡检对应的项目结构删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmXunjianStrucService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
