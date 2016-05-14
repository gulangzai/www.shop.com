/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectdoc.service.PmProjectDocsService.java
 * 创建日期： 2016-03-21 上午 09:32:52
 * 功能：    接口：项目工程文档
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-21 上午 09:32:52  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectdoc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmProjectDocsService.java </p>
 * <p> 功能：项目工程文档 </p>
 *
 * <p><a href="PmProjectDocsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-21
 * 
 */
public interface PmProjectDocsService extends IBaseService<PmProjectDocsVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;
    
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json) throws Exception;

    List<Map<String, String>> queryFileList(HashMap<String, String> map);

	boolean saveDir(PmProjectDocsVO vo);

	List<Map<String, String>> queryFiles(HashMap<String, String> map);

	boolean deleteFiles(String doc_uids);

	boolean updateDir(PmProjectDocsVO vo);

}
