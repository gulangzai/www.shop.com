package com.ccthanking.framework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;

public interface SjMenuService extends BaseService<MenuVo> {

    /**
     * @author wangzh
     * @memo生成菜单数html代码
     * @return string
     * @throws Exception
     */
    // String getMenuTreeHtml(final User user) throws Exception;
    public String getAllMenu();

    // 查询所有可用菜单
    public List<Map<String, String>> getAll();

    // 根据菜单编号查询所 有权限的角色
    public String[][] loadRoleByMid(String menuid);

    public String executeMenu(String json, User user, String operatorSign) throws Exception;
    
    public String getByMenuCodeAndUserId(String userId, String code) throws Exception;
    
    public String queryUnique(String menuName, User user) throws Exception;

    public String loadAllMenu(HashMap<String, String> map);
    //给角色分配系统权限
    public void awardMenuToRole(String roleId, String[] menuName,User user) throws Exception;
    
    //给用户分配系统权限
    public void awardMenuToUser(String roleId, String[] menuName,String[] codes,User user) throws Exception;
}
