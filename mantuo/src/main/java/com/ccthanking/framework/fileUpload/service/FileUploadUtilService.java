package com.ccthanking.framework.fileUpload.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccthanking.business.commons.vo.BuAttachmentVO;
import com.ccthanking.business.commons.vo.BuFileVO;
import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.PropertyUtil;
import com.ccthanking.framework.util.Pub;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class FileUploadUtilService {

	private static Logger logger = LoggerFactory.getLogger(FileUploadUtilService.class);

	public FileUploadUtilService() {
		SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEROOT");
		fileRoot = syspara.PARAVALUE1;
	}

	public static String fileRoot = "";

	/**
	 * 
	 * @param request
	 * @param fileRoot
	 *            这个参数不再使用了
	 * @return
	 * @throws Exception
	 */
	public JSONArray doInsert(HttpServletRequest request) throws Exception {
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		uploadHandler.setHeaderEncoding("UTF-8");
		String root = request.getContextPath();
		JSONArray json = new JSONArray();
		JSONArray rjson = new JSONArray();
		
		String target_uid = request.getParameter("target_uid");
		String target_table = request.getParameter("target_table");
		String target_col = request.getParameter("target_col");
		String file_type = request.getParameter("file_type");
		String create_user = request.getParameter("create_user");
		String file_type_name = PropertyUtil.getProperty(file_type);

		try {
			String fileDir = "";// 文件路径
			String fileName = "";// 文件名称
			List<FileItem> items = uploadHandler.parseRequest(request);
			if (Pub.empty(target_uid)) {
				target_uid = String.valueOf(System.nanoTime());
				
			} 
			for (FileItem item : items) {
				if (!item.isFormField()) {
					// 生成附件ID
					//fileID = DBUtil.getSequenceValue("FS_FILE_UID");
					// 文件保存路径：根路径/项目ID/附件ID
					fileDir = fileRoot + "/"+target_uid;
					
					// 保存到系统的文件名，默认使用上传文件的名字
					fileName = item.getName();
					if (file_type == "0038" || "0038".equals(file_type)) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
								"SYSTEM_USER_TX");
						fileRoot = syspara.PARAVALUE1;
						fileDir = fileRoot;
						//fileName = fileID + "." + this.getSuffix(fileName);
					} else if (file_type == "0039" || "0039".equals(file_type)) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
								"SYSTEM_USER_QM");
						fileRoot = syspara.PARAVALUE1;
						fileDir = fileRoot;
						//fileName = fileID + "." + this.getSuffix(fileName);
					}
					// 文件夹不存在的话，需要创建文件夹，否则直接写入文件
					File file = new File(fileDir);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
						file = new File(fileDir);
						file.mkdir();
					} else {
						// do nothing
					}
					String newFileName = System.nanoTime()+"."+fileName.substring(fileName.lastIndexOf(".") + 1);
					file = new File(fileDir, newFileName);
					item.write(file);
					//String size = this.resizeImage(file);// 调整图片类文件的大小
					
					JSONObject jsono = new JSONObject();
	
					jsono.put("FILE_NAME", fileName);
					jsono.put("FILE_EXT_NAME", fileName.substring(fileName.lastIndexOf(".") + 1));
					jsono.put("FILE_PATH", "/"+target_uid+"/"+newFileName);
					jsono.put("MIME_TYPE", this.getMimeType(file));
					jsono.put("FILE_SIZE", String.valueOf(file.length()));
					
					jsono.put("CREATE_USER", create_user);
					
					jsono.put("TARGET_TABLE", target_table);
					jsono.put("TARGET_UID", target_uid);
					jsono.put("TARGET_COL", target_col);
					jsono.put("FILE_TYPE", file_type);
					jsono.put("FILE_TYPE_NAME", file_type_name);
					
					jsono.put(
							"url",
							root + "/UploadUtilServlet?getfile="+ "/"+target_uid+"/"+newFileName+ "&fileDir="
									+ fileRoot);
					jsono.put(
							"url_look",
							root + "/UploadUtilServlet?getfile=" + "/"+target_uid+"/"+newFileName+ "&fileDir="
									+ fileRoot + "&getLook=1");
					jsono.put(
							"thumbnail_url",
							root + "/UploadUtilServlet?getthumb=" + "/"+target_uid+"/"+newFileName + "&fileDir="
									+ fileRoot + "/");
					jsono.put(
							"delete_url",
							root + "/UploadUtilServlet?delfile=" + "/"+target_uid+"/"+newFileName + "&fileDir="
									+ fileRoot  );
					jsono.put("modifyflag_url", root + "/UploadUtilServlet?modifyflag=" + "/"+target_uid+"/"+newFileName + "&fileDir="
							+ fileRoot);
					jsono.put("delete_type", "GET");
					

					json.put(jsono);
					String file_uid = insertTable(json, request);
					jsono.put("FILE_UID", file_uid);
					rjson.put(jsono);
					
				}
			}
			
			
		
		} catch (Exception e) {
			throw e;
		}
		return rjson;
	}
	
	/**
	 * 
	 * @param request
	 * @param fileRoot
	 *            这个参数不再使用了
	 * @return
	 * @throws Exception
	 */
	public JSONArray doInsert2(HttpServletRequest request) throws Exception {
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		uploadHandler.setHeaderEncoding("UTF-8");
		String root = request.getContextPath();
		JSONArray json = new JSONArray();
		JSONArray rjson = new JSONArray();
		Connection conn = DBUtil.getConnection();
		
		String p_doc_uid = request.getParameter("p_doc_uid");
		String target_table = request.getParameter("target_table");
		String target_col = request.getParameter("target_col");
		String file_type = request.getParameter("file_type");
		String create_user = request.getParameter("create_user");
		String file_type_name = PropertyUtil.getProperty(file_type);
		String project_uid = request.getParameter("project_uid");
		
		try {
			String fileDir = "";// 文件路径
			String fileName = "";// 文件名称
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					PmProjectDocsVO vo = new PmProjectDocsVO();
			    	vo.setProject_uid(project_uid);
			    	vo.setP_docs_uid(p_doc_uid);
			    	vo.setNode_name(item.getName());
			    	vo.setNode_type("DOC");
			    	vo.setCreate_user(create_user);
			    	vo.setCreate_date(new Date());
			    	vo.setUpdate_user(create_user);
			    	vo.setUpdate_date(new Date());
					String uid = String.valueOf(BaseDAO.insert(conn, vo));
					// 文件保存路径：根路径/项目ID/附件ID
					fileDir = fileRoot + "/"+uid;
					
					// 保存到系统的文件名，默认使用上传文件的名字
					fileName = item.getName();

					// 文件夹不存在的话，需要创建文件夹，否则直接写入文件
					File file = new File(fileDir);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
						file = new File(fileDir);
						file.mkdir();
					} 
					
					String newFileName = System.nanoTime()+"."+fileName.substring(fileName.lastIndexOf(".") + 1);
					file = new File(fileDir, newFileName);
					item.write(file);
					//String size = this.resizeImage(file);// 调整图片类文件的大小

					JSONObject jsono = new JSONObject();
					
					jsono.put("FILE_NAME", fileName);
					jsono.put("FILE_EXT_NAME", fileName.substring(fileName.lastIndexOf(".") + 1));
					jsono.put("FILE_PATH", "/"+uid+"/"+newFileName);
					jsono.put("MIME_TYPE", this.getMimeType(file));
					jsono.put("FILE_SIZE", String.valueOf(file.length()));
					
					jsono.put("CREATE_USER", create_user);
					
					jsono.put("TARGET_TABLE", target_table);
					jsono.put("TARGET_UID", uid);
					jsono.put("TARGET_COL", target_col);
					jsono.put("FILE_TYPE", file_type);
					jsono.put("FILE_TYPE_NAME", file_type_name);
					
					jsono.put(
							"url",
							root + "/UploadUtilServlet?getfile="+ "/"+uid+"/"+newFileName+ "&fileDir="
									+ fileRoot);

					jsono.put("delete_type", "GET");
					
					
					json.put(jsono);
					String file_uid = insertTable2(json, request);
					jsono.put("FILE_UID", file_uid);
					rjson.put(jsono);
					
				}
			}
			
			
			
		} catch (Exception e) {
			throw e;
		}
		return rjson;
	}

	public String resizeImage(File file) throws Exception {
		String size = "";
		try {
			String mimetype = this.getMimeType(file);
			File newFile = new File(file.getAbsolutePath());
			if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("jpg")) {

				String imgType = mimetype.substring(6);
				//把jpg类型文件重写(java 不支持 某些图片的操作（放大，缩小）)
				if(mimetype.endsWith("jpeg") || mimetype.endsWith("jpg")){
					JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(newFile));  
			        BufferedImage image = decoder.decodeAsBufferedImage();  
			        ImageIO.write(image, "JPEG", newFile); 
			        
				}
				// 读入图片文件
				BufferedImage im = ImageIO.read(newFile);
				// 图片高度超出尺寸时，重置文件大小
				if (im != null && im.getHeight() > 1280) {
					BufferedImage thumb = Scalr.resize(im, 1280);// 重新定义图片的高度
					FileOutputStream op = new FileOutputStream(newFile);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(thumb, getSuffix(file.getName()), out);// 将图片写入输出流
					byte[] b = out.toByteArray();
					op.write(b);
					op.close();
				} else {
					ImageIO.write(im, imgType, newFile);
				}
			}
			Double d = new Double(2);
			d = Double.parseDouble(Long.toString(newFile.length()));
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			size = df.format(d / 1000) ;//+ "KB";
			if (size.startsWith(".")) {
				size = "0" + size;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return size;
	}

	/**
	 * 获得VO集合
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> doInitVO(JSONArray json, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
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
				
				//fvo.setFile_uid(jsono.getString("FILE_UID"));
				fvo.setTarget_table(jsono.getString("TARGET_TABLE"));
				fvo.setTarget_col(jsono.getString("TARGET_COL"));
				fvo.setTarget_uid(jsono.getString("TARGET_UID"));
				fvo.setFile_type(jsono.getString("FILE_TYPE"));
				fvo.setFile_type_name(jsono.getString("FILE_TYPE_NAME"));
				fvo.setCreate_date(new Date());
				fvo.setCreate_user(jsono.getString("CREATE_USER"));
				fvo.setEnabled("0");

				map.put("vo", vo);
				map.put("fvo", fvo);
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}
	/**
	 * 获得VO集合
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> doInitVO2(JSONArray json, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
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
				
				//fvo.setFile_uid(jsono.getString("FILE_UID"));
				fvo.setTarget_table(jsono.getString("TARGET_TABLE"));
				fvo.setTarget_col(jsono.getString("TARGET_COL"));
				fvo.setTarget_uid(jsono.getString("TARGET_UID"));
				fvo.setFile_type(jsono.getString("FILE_TYPE"));
				fvo.setFile_type_name(jsono.getString("FILE_TYPE_NAME"));
				fvo.setCreate_date(new Date());
				fvo.setCreate_user(jsono.getString("CREATE_USER"));
				fvo.setEnabled("1");
				
				map.put("vo", vo);
				map.put("fvo", fvo);
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	/**
	 * 将VO信息存入数据库表
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String insertTable(JSONArray json, HttpServletRequest request) throws Exception {
		Connection conn = DBUtil.getConnection();
		Long file_uid = null;
		try {
			conn.setAutoCommit(false);
			Map<String,Object> map = this.doInitVO(json, request);
			BuAttachmentVO vo = (BuAttachmentVO) map.get("vo");
			BuFileVO fvo = (BuFileVO) map.get("fvo");
			Long uid = BaseDAO.insert(conn, vo);
			
			fvo.setAttachment_uid(String.valueOf(uid));
			file_uid =  BaseDAO.insert(conn, fvo);
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
	 * 将VO信息存入数据库表
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String insertTable2(JSONArray json, HttpServletRequest request) throws Exception {
		Connection conn = DBUtil.getConnection();
		Long file_uid = null;
		try {
			conn.setAutoCommit(false);
			Map<String,Object> map = this.doInitVO2(json, request);
			BuAttachmentVO vo = (BuAttachmentVO) map.get("vo");
			BuFileVO fvo = (BuFileVO) map.get("fvo");
			Long uid = BaseDAO.insert(conn, vo);
			
			fvo.setAttachment_uid(String.valueOf(uid));
			file_uid =  BaseDAO.insert(conn, fvo);
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
	 * 根据业务tragetUid更新附件状态和tragetUid 把其原String换成number
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateBytragetUid(String targetUid, String newtargetUid) throws Exception {
		boolean flag = true;
		Connection conn = null;
		try {
			
			conn = DBUtil.getConnection();
			if (!Pub.empty(targetUid)) {
				String sql = "update bu_file set enabled=1,target_uid='" + newtargetUid + "' " + " where target_uid ='" + targetUid
						+ "' and enabled=0 ";

				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return flag;
	}





	/**
	 * 根据新增的业务编号更新附件状态
	 * 
	 * @param conn
	 * @param newywid
	 *            ,oldywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByTargetUid(Connection conn, String newtragetUid, String oldtragetUid) throws Exception {
		boolean flag = true;
		try {
			if (!Pub.empty(newtragetUid)) {
				String sql = "update bu_file set enabled=1," + "target_uid='" + newtragetUid + "'" +
						" where target_uid='" + oldtragetUid + "'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
		return flag;
	}


	public static boolean deleteByConditionVO(Connection conn, FileUploadVO condVO) throws Exception {
		boolean b = false;
		int createFlag = 0;
		try {
			BaseVO[] bv = null;
			if (conn == null) {
				createFlag = 1;
				conn = DBUtil.getConnection();
			}
			if (condVO != null && !condVO.isEmpty()) {
				bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			}
			if (bv != null) {
				for (int i = 0; i < bv.length; i++) {
					FileUploadVO vo = (FileUploadVO) bv[i];
					if ("0".equals(vo.getFjzt())) {
						// 如果附件状态为0，那么删除附件
						BaseDAO.delete(conn, vo);
						// 1.先删除文件
						File file = new File(fileRoot + "/" + vo.getUrl());
						if (file.exists()) {
							file.delete(); // TODO:check and report success
						}
					} else {
						// 如果附件状态不为0，那么把附件状态置为2（被删除）,文件和记录都保留
						vo.setFjzt("2");
						BaseDAO.update(conn, vo);
					}
				}
			}
			b = true;
			if (createFlag == 1) {
				// 本方法创建的conn由本方法提交
				conn.commit();
			}
		} catch (Exception e) {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法回撤
				DBUtil.rollbackConnetion(conn);
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法关闭
				DBUtil.closeConnetion(conn);
			}
		}
		return b;
	}

	/**
	 * 附件删除方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete(BuFileVO vo) throws Exception {
		Connection conn = null;
		String resultVO = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			vo = (BuFileVO) BaseDAO.getVOByPrimaryKey(conn, vo);
			BuAttachmentVO tvo =  new BuAttachmentVO();
			tvo.setAttachment_uid(vo.getAttachment_uid());
			BaseDAO.delete(conn, tvo);
			BaseDAO.delete(conn, vo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVO;
	}

	/**
	 * 附件修改方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update(BuFileVO vo) throws Exception {
		Connection conn = null;
		String resultVO = null;
		try {
			conn = DBUtil.getConnection();
			BaseDAO.update(conn, vo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVO;
	}

	/**
	 * 附件修改方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public FileUploadVO getLastFileVO(FileUploadVO condVO) throws Exception {
		Connection conn = null;
		BaseVO[] bv = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select fileid from bu_attachment where ywid='" + condVO.getYwid() + "' and fjlb='"
					+ condVO.getFjlb() + "' order by lrsj desc";
			String arr[][] = DBUtil.query(conn, sql);
			if (arr != null) {
				condVO.setFileid(arr[0][0]);
				bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		if (bv == null) {
			return new FileUploadVO();
		} else {
			return (FileUploadVO) bv[0];
		}
	}

	/**
	 * 根据项目ID查询附件方法
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String queryFile(HttpServletRequest request, requestJson js) throws Exception {
		JSONArray json = new JSONArray();
		String root = request.getContextPath();
		Connection conn = null;
		String domresult = "";
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			BuFileVO vo = new BuFileVO();
			net.sf.json.JSONArray tlist = vo.doInitJson(js.getMsg());
			vo.setValueFromJson((net.sf.json.JSONObject)tlist.get(0));
			StringBuffer sql = new StringBuffer();
			sql.append(" select file_uid, target_table, target_col,  ");
			sql.append("        target_uid, file_type, file_type_name,  ");
			sql.append("        t.attachment_uid, enabled, file_name,  ");
			sql.append("        file_ext_name, file_path, mime_type, ");
			sql.append("        file_size,create_date,create_user ");
			sql.append(" from bu_file  f ");
			sql.append(" left join bu_attachment t ");
			sql.append(" on f.attachment_uid = t.attachment_uid ");
			sql.append(" where f.enabled = 1 ");
			sql.append(" and f.target_table = '"+vo.getTarget_table()+"'");
			sql.append(" and f.target_uid = '"+vo.getTarget_uid()+"'");
			//sql.append(" and f.file_type = '"+vo.getFile_type()+"'");
			//System.out.println("sql==="+sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			md = rs.getMetaData();
			while(rs.next()){
				JSONObject jsono = new JSONObject();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i+1).toUpperCase();
					String sTemp  = rs.getString(columnName);	
					
					if(sTemp==null ||sTemp == ""){
						sTemp = " ";
					}
					jsono.put(columnName, sTemp);
				}
					
				jsono.put("url", root + "/UploadUtilServlet?getfile=" +rs.getString("File_PATH") 
						+ "&fileDir="+ fileRoot );
				jsono.put("thumbnail_url", root + "/UploadUtilServlet?getthumb="+rs.getString("File_PATH")
						+ "&fileDir="+ fileRoot );
				jsono.put(
						"delete_url",
						root + "/UploadUtilServlet?delfile="+rs.getString("File_PATH")+ "&fileDir="+ fileRoot
						);//FS_FILE_UID  // +"&FILE_UID="+rs.getString("FILE_UID") 页面上添加了FILE_UID 所以这里不用加
				jsono.put("delete_type", "GET");
				json.put(jsono);
			}

			domresult = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return domresult;
	}
	/**
	 * 根据项目ID查询附件方法
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryFileByType(HttpServletRequest request) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String root = request.getContextPath();
		Connection conn = null;
		String domresult = "";
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String targetUid = request.getParameter("targetUid");
			String file_type = request.getParameter("file_type");
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.file_path,a.file_name from bu_file f ");
			sql.append(" left join bu_attachment a ");
			sql.append(" on f.attachment_uid = a.attachment_uid ");
			sql.append(" where f.target_uid = '"+targetUid+"' and f.file_type = '"+file_type+"' ");// ORDER BY f.FILE_UID DESC LIMIT 1
			ps = conn.prepareStatement(sql.toString());
			//System.out.println(sql+"显示 sql语句");
			
			rs = ps.executeQuery();
			md = rs.getMetaData();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i+1).toUpperCase();
					String sTemp  = rs.getString(columnName);	
					
					if(sTemp==null ||sTemp == ""){
						sTemp = " ";
					}
					map.put(columnName, sTemp);
				}
				
				map.put("url", root + "/UploadUtilServlet?getfile=" +rs.getString("File_PATH") 
						+ "&fileDir="+ fileRoot );
			
				list.add(map);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return list;
	}

	/**
	 * 这个是临时使用的方法，由于BaseDAO.getVOByCondition方法不能使用，只能先自己查询了
	 * 
	 * @param conn
	 *            数据库连接
	 * @param js
	 *            前台传入条件
	 * @return
	 */
	private List<BuFileVO> getFileUploadVOByJson(Connection conn, requestJson js) throws Exception {
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		List<BuFileVO> list = new ArrayList<BuFileVO>();
		try {
			String sql = "select * from bu_file where ENABLED=1 ";
			BuFileVO condVO = new BuFileVO();
			net.sf.json.JSONArray tlist = condVO.doInitJson(js.getMsg());
			condVO.setValueFromJson((net.sf.json.JSONObject) tlist.get(0));
			String cond = "";
			// 保证至少存在一个条件，否则不允许查询
			if (!Pub.empty(condVO.getTarget_uid())) {
				// 处理业务ID条件
				if (condVO.getTarget_uid().indexOf(",") != -1) {
					String[] tempArr = condVO.getTarget_uid().split(",");
					for (int i = 0; i < tempArr.length; i++) {
						cond += "  target_uid ='" + tempArr[i] + "' or ";
					}
					cond = " and (" + cond.substring(0, cond.length() - 3) + ")";
				} else {
					cond += " and target_uid='" + condVO.getTarget_uid() + "'";
				}
				if (StringUtils.isNotBlank(condVO.getTarget_table())) {
					cond += " and business_sub_type ='" + condVO.getTarget_table() + "' ";
				}
				sql = sql + cond;
				logger.debug(sql);

				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				md = rs.getMetaData();
				while (rs.next()) {
					BuFileVO res = new BuFileVO();
					for (int i = 1; i <= md.getColumnCount(); i++) {
						String colname = md.getColumnName(i).toUpperCase();
						if (!res.isValidField(colname))
							continue;
						int coltype = md.getColumnType(i);
						if (coltype == java.sql.Types.DATE || coltype == java.sql.Types.TIMESTAMP) {
							//if (rs.getDate(i) != null)
								//res.put(colname, Utils.formatToDate(rs.getDate(i) + " " + rs.getTime(i)));
						} else if (coltype == java.sql.Types.BLOB) {
							Blob dbBlob;
							dbBlob = rs.getBlob(i);
							if (dbBlob == null)
								continue;
							int length = (int) dbBlob.length();
							byte[] buffer = dbBlob.getBytes(1, length);
							res.put(colname, buffer);
						} else if (coltype == java.sql.Types.NULL) {
							// res.put(colname,null);
						} else {
							if (rs.getString(i) != null) {
								res.put(colname, rs.getString(i));
							}
						}
					}
					list.add(res);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		}
		return list;
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
