package cn.lanbao.com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserControllerTest {

	@Test
	public void testHandleRequest() {
		MyUserController uc = new MyUserController();
		try {  
			//uc.handleRequest(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
