package com.ccthanking.framework.util;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ccthanking.business.commons.vo.BuAttachmentVO;
import com.ccthanking.business.commons.vo.BuFileVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;

public class MobileFileUpload {

	public static String fileRoot = "";

	public MobileFileUpload() {
		SysParaConfigureVO syspara = ParaManager.getInstance().getSysParameter("FILEROOT");
		fileRoot = syspara.PARAVALUE1;
	}

	public JSONArray packFile(MultipartHttpServletRequest request)throws Exception {
		JSONArray json = new JSONArray();
		Map<String, MultipartFile> map = request.getFileMap();
		int num = 0;
		for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
			MultipartFile myfile = entry.getValue();
			String fileDir = "";// 文件路径
			String fileName = "";// 文件名称
			//String fileDescribe = "";//文件描述
			String target_uid = String.valueOf(System.nanoTime());
			String target_table = request.getParameter("target_table");
			String target_col = request.getParameter("target_col");
			String file_type = request.getParameter("file_type");
			String create_user = request.getParameter("create_user");
			String file_type_name = PropertyUtil.getProperty(file_type);
			

			// 文件保存路径：根路径/项目ID/附件ID
			fileDir = fileRoot + "/" + target_uid;

			// 保存到系统的文件名，getOriginalFilename()默认使用上传文件的名字
/*			String[] str = myfile.getOriginalFilename().split("#");
			if(str.length>1){
				fileName = str[0];
				fileDescribe = str[1];
			}else{
				fileName = str[0];
			}*/

			fileName = myfile.getOriginalFilename();
			// 文件夹不存在的话，需要创建文件夹，否则直接写入文件
			File file = new File(fileDir);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
				file = new File(fileDir);
				file.mkdir();
			}

			String newFileName = System.nanoTime() + "."
					+ fileName.substring(fileName.lastIndexOf(".") + 1);
			file = new File(fileDir, newFileName);
			// item.write(file);
			myfile.transferTo(file);
			org.json.JSONObject jsono = new org.json.JSONObject();

			jsono.put("FILE_NAME", fileName);
			jsono.put("FILE_EXT_NAME", fileName.substring(fileName
					.lastIndexOf(".") + 1));
			jsono.put("FILE_PATH", "/" + target_uid + "/" + newFileName);
			jsono.put("MIME_TYPE", this.getMimeType(file));
			jsono.put("FILE_SIZE", String.valueOf(file.length()));

			jsono.put("CREATE_USER", create_user);

			jsono.put("TARGET_TABLE", target_table);
			jsono.put("TARGET_UID", target_uid);
			jsono.put("TARGET_COL", target_col);
			jsono.put("FILE_TYPE", file_type);
			jsono.put("FILE_TYPE_NAME", file_type_name);
			if(null!=request.getParameter("describe"+num)){
				jsono.put("DESCRIPTION", request.getParameter("describe"+num));
			}else{
				jsono.put("DESCRIPTION", "");
			}
			
			json.put(jsono);
			num ++;
		}
		return json;
	}

	/**
	 * 将VO信息存入数据库表
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String insertTable(JSONArray json, String target_uid)
			throws Exception {
		Connection conn = DBUtil.getConnection();
		Long file_uid = null;
		try {
			conn.setAutoCommit(false);
			for (int i = 0; i < json.length(); i++) {
				BuAttachmentVO vo = new BuAttachmentVO();
				BuFileVO fvo = new BuFileVO();
				JSONObject jsono = (JSONObject) json.get(i);

				vo.setFile_name(jsono.getString("FILE_NAME"));
				vo.setFile_ext_name(jsono.getString("FILE_EXT_NAME"));
				vo.setFile_path(jsono.getString("FILE_PATH"));
				vo.setFile_size(jsono.getString("FILE_SIZE"));
				vo.setMime_type(jsono.getString("MIME_TYPE"));
				vo.setCreated_date(new Date());
				vo.setCreated_user(jsono.getString("CREATE_USER"));

				// fvo.setFile_uid(jsono.getString("FILE_UID"));
				fvo.setTarget_table(jsono.getString("TARGET_TABLE"));
				fvo.setTarget_col(jsono.getString("TARGET_COL"));
				fvo.setTarget_uid(jsono.getString("TARGET_UID"));
				fvo.setFile_type(jsono.getString("FILE_TYPE"));
				fvo.setFile_type_name(jsono.getString("FILE_TYPE_NAME"));
				fvo.setCreate_date(new Date());
				fvo.setCreate_user(jsono.getString("CREATE_USER"));
				fvo.setDescription(jsono.getString("DESCRIPTION"));
				fvo.setEnabled("1");
				Long uid = BaseDAO.insert(conn, vo);
				fvo.setAttachment_uid(String.valueOf(uid));
				fvo.setTarget_uid(target_uid);
				BaseDAO.insert(conn, fvo);
			}
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return String.valueOf(file_uid);
	}

	
	/**
	 * 获取文件扩展类型
	 * 
	 * @param file
	 * @return
	 */
	public String getMimeType(File file) {
		String mimetype = "";
		if (file.exists()) {
			if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
				mimetype = "image/png";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpg")) {
				mimetype = "image/jpg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpeg")) {
				mimetype = "image/jpeg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("gif")) {
				mimetype = "image/gif";
			} else {
				javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
				mimetype = mtMap.getContentType(file);
			}
		}
		return mimetype;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		return suffix;
	}
	
}
