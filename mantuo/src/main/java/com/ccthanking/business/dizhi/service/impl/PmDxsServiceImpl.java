/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dizhi.service.PmDxsService.java
 * 创建日期： 2015-12-17 下午 02:19:59
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-17 下午 02:19:59  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dizhi.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dizhi.vo.PmDxsVO;
import com.ccthanking.business.dizhi.dao.PmDxsDao;
import com.ccthanking.business.dizhi.service.PmDxsService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmDxsService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="PmDxsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggnag@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-12-17
 * 
 */

@Service
public class PmDxsServiceImpl extends Base1ServiceImpl<PmDxsVO,String> implements PmDxsService {

	private static Logger logger = LoggerFactory.getLogger(PmDxsServiceImpl.class);
	
    private PmDxsDao pmDxsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmDxsDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmDxsVO vo = new PmDxsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            
            //查询 出最大的序号值加1
            String xuhao= queryMaxSort();
            vo.setXuhao(xuhao);
            
            // 插入
			pmDxsDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            SystemException.handleMessageException("地下水情况 新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    /**将查询出 的最大序号中 加了1 即为当前最大的 序号值 **/
    private String queryMaxSort() {
    	Connection conn = DBUtil.getConnection();
        List<?> list = new ArrayList();
        String xuhao = "";
        try {
            String sql  = "SELECT MAX(XUHAO) as XUHAO FROM  pm_dxs ";
            String[][] bs = DBUtil.querySql(conn, sql);
            if(bs != null && bs.length !=0 ){//序号不为空
            	xuhao = bs[0][0];
            	int maxxuhao = Integer.parseInt(xuhao);
            	maxxuhao +=1;
            	xuhao =String.valueOf(maxxuhao);
            	
            }else{
            	 xuhao = "1";
            }

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询地下水最大的序号值出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return xuhao;
    }
    
    /**查询出 最小的序号值 **/
    private String queryMinSort() {
    	Connection conn = DBUtil.getConnection();
        String xuhao = "";
        try {
            String sql  = "SELECT MIN(XUHAO) as XUHAO FROM  pm_dxs ";
            String[][] bs = DBUtil.querySql(conn, sql);
            if(bs != null && bs.length !=0 ){//序号不为空
            	xuhao = bs[0][0];
            	int minxuhao = Integer.parseInt(xuhao);
            	xuhao =String.valueOf(minxuhao);
            	
            }else{//表示没有序号值
            	 xuhao = "0";
            }

        } catch (Exception e) {
            DaoException.handleMessageException("*********查询地下水最小的序号值出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        
        return xuhao;
    }
	public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmDxsVO vo = new PmDxsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            pmDxsDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            SystemException.handleMessageException("项目修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String dxsUid) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmDxsVO vo = new PmDxsVO();
		try {
		
			pmDxsDao.delete(PmDxsVO.class, dxsUid);

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("项目{}", e.getMessage());
            SystemException.handleMessageException("项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmDxsDaoImpl")
	public void setPmDxsDao(PmDxsDao pmDxsDao) {
		this.pmDxsDao = pmDxsDao;
	}

	public List<?> queryDxsById(String dxsUid) {
	
		return this.pmDxsDao.queryById(dxsUid);
	}

//更新序号值 
	public String updateForSort(String dxsUid, String xuhao,String move) {
	    Connection conn = DBUtil.getConnection();
        String reSort = "";
        String needSort = "";
        String needDuid = "";
        boolean bs = false;
        String message = "移动成功！";
        try {
        	if("up".equalsIgnoreCase(move) && xuhao != ""){//上移 和前一个节点交换位置
        		int maxXH = Integer.parseInt(xuhao) - 1;//减去1取前面的序号
        		int minXH = Integer.parseInt(queryMinSort());//获取到最小的序号值 

        		if(minXH != Integer.parseInt(xuhao)){
        		for(int i=maxXH;i>=1;i--){
        			reSort = querySort(String.valueOf(i));
        			if(reSort != null  && reSort != ""){//只要不等于 空或null 说明存在 返回前面的 序号
        				JSONObject obj = JSONObject.fromObject(reSort);
        				 needSort = obj.getString("xuhao");
        				 needDuid = obj.getString("dxs_uid");
        				 bs = replaceSort(needDuid,xuhao);
        				
        				break;
        			}
        			
        		}
        		/**将 前一个 序号值 替换为 当前的序号值 **/
        		if(bs){
        			bs = replaceSort(dxsUid,needSort);
        			
        		}
        		
        		}else{//表示 当前的序号值 等于是最小的序号追值，说明是 第一个
        			message = "当前行是第一行！不可向上移动！";
        		}
               
        	}else if("down".equalsIgnoreCase(move) && xuhao !=""){//下移
        		int maxXH = Integer.parseInt(queryMaxSort()) - 1;//查询出最大的序号值 然后逐一查找
                int downxh = Integer.parseInt(xuhao) + 1;//加上 1 逐一判断是否存在下一条数据
                if(maxXH != Integer.parseInt(xuhao)){//说明当前的 序号值不是 最大的 说明后面存在序号值
        		for(int i=downxh;i<=maxXH;i++){
        			reSort = querySort(String.valueOf(i));
        			if(reSort != null  && reSort != ""){//只要不等于 空或null 说明存在 返回后面的 序号
        				JSONObject obj = JSONObject.fromObject(reSort);
        				 needSort = obj.getString("xuhao");
        				 needDuid = obj.getString("dxs_uid");
        				 bs = replaceSort(needDuid,xuhao);//替换 当前下一条数据的 序号值 
        				break;
        			}
        			
        			
        		}
        		
        		/**将 前一个 序号值 替换为 当前的序号值 **/
        		if(bs){
        			bs = replaceSort(dxsUid,needSort);
        			
        		}
                }else{
                	message="当前行是最后一行，不可向下移动！";
                	
                }
        	}
        } catch (DaoException e) {
        	e.printStackTrace();
            SystemException.handleMessageException("地下水情况序号更改失败,请联系相关人员处理");
        }finally{
        	DBUtil.closeConnetion(conn);
        }
        
        return  message;

}

	//查询 序号值是否 存在   存在 就返回序号值 及其主键id 不存在 返回null
private String querySort(String sort) {
	    	Connection conn = DBUtil.getConnection();
	        String xuhao = "";
	        String dxs_uid = "";
	        String result = "";
	        try {
	            String sql  = "SELECT XUHAO,DXS_UID  FROM  pm_dxs WHERE XUHAO="+sort;
	            String[][] bs = DBUtil.querySql(conn, sql);
	            if(bs != null && bs.length !=0 ){//序号不为空说明存在序号
	            	xuhao = bs[0][0];
	            	dxs_uid = bs[0][1];
	            }else{
	            	xuhao = null;
	            }
	            
	            JSONObject obj = new JSONObject();
	            obj.put("xuhao", xuhao);
	            obj.put("dxs_uid", dxs_uid);
	            result = obj.toString();
                
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.closeConnetion(conn);
	        }
	        return result;
	    }
	 
	 //将 当前的序号 替换
public boolean replaceSort(String id,String sort){
		Connection conn = DBUtil.getConnection();
	    boolean bs= false;
		try {
			 conn.setAutoCommit(false);
		     //设置 被替换的 原始序号 
			 String sql="update pm_dxs s set s.XUHAO = "+sort+" where s.DXS_UID="+id;
			 bs = DBUtil.execUpdateSql(conn, sql);
			 conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
		     SystemException.handleMessageException("序号值替换失败,请联系相关人员处理");
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return bs;
		
		}
		    
}
