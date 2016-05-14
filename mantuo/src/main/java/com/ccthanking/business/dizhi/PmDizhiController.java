/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dizhi.service.PmDizhiVOController.java
 * 创建日期： 2015-12-17 下午 02:12:38
 * 功能：    服务控制类：项目
 * 所含类:   PmDizhiVOService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-17 下午 02:12:38  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dizhi;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dizhi.service.PmDizhiService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmDizhiVOController.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="PmDizhiVOController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggnag@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-12-17
 * 
 */

@Controller
@RequestMapping("/dizhi/pmDizhiController")
public class PmDizhiController {

	private static Logger logger = LoggerFactory.getLogger(PmDizhiController.class);

    @Autowired
    private PmDizhiService pmDizhiService;

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
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmDizhiService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmDizhiService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmDizhiService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目删除】",user.getName());
        requestJson j = new requestJson();
        String dzUid = request.getParameter("DIZHI_UID");
        String resultVO = "";
        resultVO = this.pmDizhiService.delete(dzUid);
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryDzById")
    @ResponseBody
    public requestJson  queryDzById(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String dzUid = request.getParameter("DIZHI_UID");
      
    	List<?> list = this.pmDizhiService.queryDxsById(dzUid);
    	j.setObj(list);
    	return j;
    }

    /**
     * 更新地质情况的序号值 
     * @param request
     * @param json
     * @return
     * @throws Exception
     */
      @RequestMapping(params = "updateForSort")
      @ResponseBody
      public requestJson updateForSort(HttpServletRequest request, requestJson json) throws Exception {
          User user = RestContext.getCurrentUser();
          logger.info("<{}>执行操作【项目删除】",user.getName());
          requestJson j = new requestJson();
          String dizhiUid = request.getParameter("DIZHI_UID");
          String xuhao = request.getParameter("XUHAO");
          String move = request.getParameter("MOVE");
          String resultVO = "";
          resultVO = this.pmDizhiService.updateForSort(dizhiUid,xuhao,move);
          j.setMsg(resultVO);
          return j;
      }

   
}
