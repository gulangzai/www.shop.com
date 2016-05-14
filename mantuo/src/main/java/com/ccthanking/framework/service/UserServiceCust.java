package com.ccthanking.framework.service;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.framework.common.User;

public interface UserServiceCust{


	public void awardRoleToPerson(String roleid, String rolename, String[] accountNameAndId, User user)
			throws Exception;

	public User getUserByUsernameAndPasswordm (String username,String password) throws Exception;

	public User getUserByUsernameAndPassword(String username, String password) throws Exception;
	
	public String updatePassWord(String userId, String oldPwd, String newPwd) throws Exception;
	
	public JSONObject getUserXinXi(String userId) throws Exception;
	
	public String[][] queryRole(HashMap<String, String> map);
	
	public String[][] queryPersonRole(HashMap<String, String> map) ;
}
