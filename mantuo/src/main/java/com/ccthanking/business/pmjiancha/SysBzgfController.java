/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.SysBzgfController.java
 * 创建日期： 2016-02-25 下午 02:46:15
 * 功能：    服务控制类：标准和规范
 * 所含类:   SysBzgfService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-25 下午 02:46:15  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmjiancha.service.SysBzgfService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SysBzgfController.java </p>
 * <p> 功能：标准和规范 </p>
 *
 * <p><a href="SysBzgfController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-25
 * 
 */

@Controller
@RequestMapping("/pmjiancha/sysBzgfController")
public class SysBzgfController {

	private static Logger logger = LoggerFactory.getLogger(SysBzgfController.class);

    @Autowired
    private SysBzgfService sysBzgfService;

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
        logger.info("<{}>执行操作【标准和规范查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sysBzgfService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【标准和规范新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzgfService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【标准和规范修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzgfService.update(json.getMsg());
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
        logger.info("<{}>执行操作【标准和规范删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzgfService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    //生成树状结构
    @RequestMapping(params = "creatTree")
    @ResponseBody
    public void creatCLTree(final HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String NODE_TYPE = request.getParameter("NODE_TYPE");
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("NODE_TYPE", NODE_TYPE);
    	String menuJson = this.sysBzgfService.creatTree(map);
        response.setCharacterEncoding("UTF-8");
        try{
            response.getWriter().print(menuJson);
        }
        catch(IOException e){
           e.printStackTrace();
        }		
	}

}
