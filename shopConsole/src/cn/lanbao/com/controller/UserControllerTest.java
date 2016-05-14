package cn.lanbao.com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserControllerTest {

	@Test
	public void testHandleRequest() {
		UserController uc = new UserController();
		try {  
			uc.handleRequest(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
