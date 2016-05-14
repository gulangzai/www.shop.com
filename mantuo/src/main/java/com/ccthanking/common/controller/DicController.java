package com.ccthanking.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.framework.dic.Dics;
import com.ccthanking.framework.dic.TreeNode;
import com.ccthanking.framework.model.requestJson;

@Controller
@RequestMapping("/controller/dicController/")
public class DicController {

	// 客户端下载文件调用
	@SuppressWarnings("static-access")
	@RequestMapping(value = "getDic")
	@ResponseBody
	public requestJson getDic(final HttpServletRequest request, final HttpServletResponse response, requestJson json)
			throws Exception {
		TreeNode[] nodes = Dics.getInstance().getAllDics();
		requestJson j = new requestJson();
		TreeNode[] cnodes = null;
		for (int i = 0; i < nodes.length; i++) {
			if ("PROJECTS_TYPE".equals(nodes[i].getDicCode())) {
				cnodes = nodes[i].getChilds();
				break;
			}
		}
		JSONArray arr = new JSONArray();
		if (cnodes!=null) {
			JSONObject obj = null;
			for (int i = 0; i < cnodes.length; i++) {
				obj = new JSONObject();
				obj.put("PRO_TYPE", cnodes[i].getDicCode());
				obj.put("PRO_TYPE_NAME", cnodes[i].getDicValue());
				
				arr.add(obj);
			}
		}
		//System.out.println(arr.toString());
		j.setMsg(arr.toString());
		return j;
	}

}
