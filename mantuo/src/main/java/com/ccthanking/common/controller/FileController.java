package com.ccthanking.common.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.common.util.UserAgentUtil;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.model.requestJson;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@Controller
@RequestMapping("/controller/fileController/")
public class FileController {

	// 客户端下载文件调用
	@RequestMapping(value = "download")
	@ResponseBody
	public void download2(final HttpServletRequest request, final HttpServletResponse response, requestJson json)
			throws Exception {
		FileUploadUtilService ser = new FileUploadUtilService();
		String encoding = request.getCharacterEncoding();
		response.setCharacterEncoding(encoding);
		String path = request.getParameter("path");
		File file = new File(path);
		if (file.exists()) {
			int bytes = 0;
			ServletOutputStream op = response.getOutputStream();

			response.setContentType(ser.getMimeType(file));
			response.setContentLength((int) file.length());
			// response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; "+UserAgentUtil.encodeFileName(request, file.getName()) + "");
			byte[] bbuf = new byte[1024];
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, bytes);
			}

			in.close();
			op.flush();
			op.close();
		}
	}

}
