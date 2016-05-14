package com.xue.controller; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bokecc.util.XmlUtil;
import com.xue.dto.AllStudent;
import com.xue.dto.BatchInputStudentResult;
import com.xue.dto.PageBean;
import com.xue.dto.StudentPlanLearnResult;
import com.xue.model.BsCallbackInfo;
import com.xue.model.BsUserInfo;
import com.xue.model.BsUserStuFavorable;
import com.xue.model.CourseMajorShop;
import com.xue.model.CourseMajorSubject;
import com.xue.model.StudentHighScore;
import com.xue.model.StudentInfo;
import com.xue.model.StudentInfoDetail;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanLog;
import com.xue.model.StudentScore;
import com.xue.service.BsCallbackInfoService;
import com.xue.service.BsUserStuFavorableService;
import com.xue.service.CourseMajorService;
import com.xue.service.CourseMajorSubjectService;
import com.xue.service.StudentExamInfoService;
import com.xue.service.StudentInfoDetailService;
import com.xue.service.StudentInfoService;
import com.xue.service.StudentPlanLearnService;
import com.xue.service.StudentPlanService;
import com.xue.vo.query.AllStudentQuery;
import com.xue.vo.query.BsCallbackInfoQuery;
import com.xue.vo.query.BsNoticeQuery;
import com.xue.vo.query.StudentPlanQuery;

import javacommon.base.CommonController;
import javacommon.base.ResultAction;
import javacommon.util.DateUtils;
import javacommon.util.DbUtil;
import javacommon.util.SafeUtils;

@Controller
@RequestMapping("/myStudent")
public class MyStudentController extends CommonController{ 
	
	@Autowired
	public StudentInfoService studentInfoService;
	@Autowired
	public StudentInfoDetailService studentInfoDetailService;
	@Autowired
	public StudentPlanService studentPlanService;
	@Autowired
	public StudentPlanLearnService studentPlanLearnService;
	@Autowired
	public StudentExamInfoService studentExamInfoService;
	@Autowired
	public CourseMajorService courseMajorService;
	@Autowired
	public BsCallbackInfoService bsCallbackInfoService;
	
	@Autowired
	public CourseMajorSubjectService courseMajorSubjectService;
	
	@Autowired
	public BsUserStuFavorableService bsUserStuFavorableService;
	
	@RequestMapping("/myStudentMain")
	public String myStudentMain(){
		return "/modules/mystudent/myStudentMain";
	}
	
	@RequestMapping("/myStudentList")
	public String myStudentList(){
		return "/modules/mystudent/myStudentList";
	}
	
	@RequestMapping("/studentList")
	public String studentList(){
		return "/modules/mystudent/studentList";
	}
	
	/*接收参数： marjorId
	 * 当前页：currentPage
	 * 关键词：keyword
	 * 颜色寻找 headColor
	 * */
	@RequestMapping("/allStudent")
	@ResponseBody
	public ResultAction allStudent(String marjorId,int currentPage,String keyword,String headColor,String intention,String mobile,String studentId,String studentName){
		System.out.println("marjorId:"+marjorId+"currentPage:"+currentPage+"keyword:"+keyword+"headColor:"+headColor+"intention:"+intention); 
		com.xue.vo.query.AllStudentQuery allStudentQuery = new com.xue.vo.query.AllStudentQuery(marjorId,headColor,keyword,currentPage,intention,mobile,studentId,studentName);
		PageBean pageBean = new PageBean(); 
		int size = 0;
		//无关键词
		List<AllStudent> allStudentInfos = studentInfoService.queryAllStudents(allStudentQuery);
		size=studentInfoService.queryCount(allStudentQuery); 
		//有关键词
		/*if(keyword!=null){
			   allStudentInfos = studentInfoService.queryStudentByShopKeyword(allStudentQuery);
			   size = studentInfoService.queryStudentByShopKeywordCount(allStudentQuery);
		} */
		pageBean.setAllRecodeNum(size);
		pageBean.setObj(allStudentInfos);
		ResultAction sResult = new ResultAction(); 
		sResult.setData(pageBean);
		return sResult;
	}
	
	@RequestMapping("/searchStudent")
	@ResponseBody
	public ResultAction searchStudent(String keyword){
		ResultAction sResult = new ResultAction(); 
		if(keyword.equals("李益")){  
		} 
		return sResult;
	}
	
	/*
	 * 导入学生
	 * */
	@RequestMapping("/inputStudent")
	@ResponseBody
	public ResultAction inputStudent(String mobile,String studentName,String applyIntention,String applyClasses,String intentionRamark,String intention){
		ResultAction sResult = new ResultAction();
		StudentInfo studentInfo = studentInfoService.findByStudentMobile(mobile);
		if(studentInfo==null){  
			try {
				studentInfo = new StudentInfo();
				studentInfo.setMobile(mobile);
				studentInfo.setStudentName(studentName);
				studentInfo.setIsDel("0");
				studentInfo.setCreateTime(DateUtils.getTodayTime());
				StudentInfoDetail studentInfoDetail = new StudentInfoDetail();  
				studentInfoDetail.setStudentId(DbUtil.getKey());  
				studentInfoDetail.setApplyIntention(applyIntention);
				studentInfoDetail.setApplyClasses(applyClasses);
				studentInfoDetail.setMobilePhone(mobile);
				studentInfoDetail.setIntentionRamark(intentionRamark);
				studentInfoDetail.setIntention(Integer.parseInt(intention));
				studentInfoService.inputStudent(studentInfo,studentInfoDetail);
				sResult.setMessage("SUCCESS");
			} catch (Exception e) {
				// TODO Auto-generated catch block
			    logger.error(e.getMessage());
				e.printStackTrace();
				sResult.setMessage("ERROR");
			}
		}else{
			sResult.setMessage("YETEXIST");
		} 
		return sResult;
	}
	
	/*
	 * 是否存在模板文件
	 * */ 
	@RequestMapping("/templateIsExist")
	@ResponseBody
	public ResultAction templateIsExist(HttpServletResponse response,HttpServletRequest request){
		ResultAction resultAction = new ResultAction();
		File file = new File("E:/downFile/studentTemplate.xls");  
		if(file.exists()){
			resultAction.setMessage("EXIST");
		}else{
			resultAction.setMessage("NO");
		}
	    return resultAction;
	}
	 
	/*
	 * 下载导入名单表格(下载模板文件)
	 * */
	@RequestMapping("/downloadTemplate")
	@ResponseBody
	public void downloadTemplate(HttpServletResponse response,HttpServletRequest request){
		 try { 
			response.setContentType("multipart/form-data");  
			response.setHeader("Content-Disposition", "attachment;fileName=student.xls");
		    String path = request.getContextPath();
			File file = new File("E:/downFile/studentTemplate.xls"); 
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			OutputStream out = response.getOutputStream();
			int len=0;
			while((len=fis.read(buf))!=-1){
				out.write(buf,0,len);
			}
			fis.close();
			out.flush();
			out.close();
		} catch(Exception e)  {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
	}
	 
	/*
	 * 批量导入学生
	 * */
	@RequestMapping(value="/batchInputStudent",method=RequestMethod.POST) 
	@ResponseBody
	public ResultAction batchInputStudent(HttpServletRequest request){
		ResultAction sResult = new ResultAction();
	    // 转型为MultipartHttpRequest：   
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        // 获得文件：   
        MultipartFile file = multipartRequest.getFile("file");   
        // 获得文件名：   
        String filename = file.getOriginalFilename();   
   System.out.println("filename:"+filename);
        // 获得输入流：   
        try {
			InputStream is = file.getInputStream();
			  // 写入文件   
	        File newFile = new File("E:/downFile/"+filename);
	        if(!newFile.exists()){
	        	newFile.createNewFile();
	        }
	        OutputStream os = new FileOutputStream(newFile);
	        byte[] buf = new byte[1024];
	        int len=-1;
	        while((len=is.read(buf))!=-1){
	        	os.write(buf, 0, len);
	        	os.flush();
	        }
	        os.close();
	        BatchInputStudentResult batchInputStudentResult = new BatchInputStudentResult();
	        batchInputStudentResult.setFile(newFile.getAbsolutePath());
	        sResult.setData(batchInputStudentResult);
	        sResult.setMessage("SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sResult.setMessage("FAIL");
			logger.error(e.getMessage());
		}     
        return sResult;
	}
	
	/*
	 * 批量解析excel
	 * */
	@RequestMapping(value="parseExcelInputStudent")
	@ResponseBody
	public ResultAction parseExcelInputStudent(String fileName){
		ResultAction result = new ResultAction();
		File file = new File(fileName);
		try { 
			POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wk = new HSSFWorkbook(pfs);
			Sheet sheet = wk.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			Row row = null;
			for(int rowNum=1;rowNum<rows;rowNum++){
				  row = sheet.getRow(rowNum);
				  int cols = row.getPhysicalNumberOfCells();
				  String studentId = DbUtil.getKey();
				  String studentName = XmlUtil.getCellFormatValue((HSSFCell) row.getCell((short) 2)).trim();
				  String mobile =   XmlUtil.getCellFormatValue((HSSFCell)row.getCell((short) 3)).trim();
				  String lastLoginTime = XmlUtil.getCellFormatValue((HSSFCell) row.getCell((short) 4)).trim();
				  String studentSource = XmlUtil.getCellFormatValue((HSSFCell) row.getCell((short) 8)).trim();
				  String intention = XmlUtil.getCellFormatValue((HSSFCell) row.getCell((short) 9)).trim();
				  String intentionRamark =  XmlUtil.getCellFormatValue((HSSFCell) row.getCell((short) 10)).trim();
System.out.println("studentName:"+studentName+"mobile:"+mobile+"lastLoginTime"+lastLoginTime+"studentSource:"+studentSource+"intention:"+intention+"intentionRamark:"+intentionRamark);				  
				  StudentInfo studentInfo = new StudentInfo();  
				      studentInfo.setStudentId(studentId);
					  studentInfo.setStudentName(studentName);
				      studentInfo.setMobile(mobile);
				      studentInfo.setLastLoginTime(lastLoginTime);
				      studentInfoService.save(studentInfo); 
				 StudentInfoDetail studentInfoDetail = new StudentInfoDetail();
				 studentInfoDetail.setStudentId(studentId);
				 studentSource = studentSource.substring(0,studentSource.indexOf("."));
				 intention = intention.substring(0,intention.indexOf("."));
				 studentInfoDetail.setStudentSource(Integer.parseInt(studentSource));
				 studentInfoDetail.setIntention(Integer.parseInt(intention));
				 studentInfoDetail.setIntentionRamark(intentionRamark);
				 studentInfoDetail.setMobilePhone(mobile);
				 studentInfoDetailService.save(studentInfoDetail);
			} 
			result.setMessage("SUCCESS");
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setMessage("FAIL"); 
			logger.error(e.getMessage());
		}  
		return result;
	}
	 
	/*
	 * 通过电话号码删掉用户
	 * */
	@RequestMapping("/deleteStudentByPhone")
	@ResponseBody
	public ResultAction deleteStudentByPhone(String phone){
		ResultAction sResult = new ResultAction();
		int i = studentInfoService.deleteStudentByPhone(phone); 
		System.out.println("deleteI:"+i);
		if(i==1){
			sResult.setMessage("DELETE_YES");
		}else{
			sResult.setMessage("DELETE_NO");
		} 
		return sResult;
	}
	
	//重置密码
	@RequestMapping("/resetPassword")
	@ResponseBody
	public ResultAction resetPassword(String phone){
		ResultAction sResult = new ResultAction();
		System.out.println("phone:"+phone);
		int flag = studentInfoService.resetPassword(phone); 
		if(flag==1)
		   sResult.setMessage("RESET_SUCCESS");
		else
		   sResult.setMessage("RESET_FAIL");
		return sResult;
	}
	
	/*
	 * 添加优惠券记录，
	 * 给学生余额充钱
	 * */
	@RequestMapping("/addCoupon")
	@ResponseBody
	public ResultAction addCoupon(String studentId,int favorableBalance,HttpSession session){
		ResultAction result = new ResultAction();
		try{ 
			BsUserInfo teacher = getCurrentStudentInfo(session);
			BsUserStuFavorable bsUserStuFavorable = new BsUserStuFavorable();
			bsUserStuFavorable.setCreateTime(new Date());
			bsUserStuFavorable.setFavorableBalance(favorableBalance);
			bsUserStuFavorable.setStudentId(studentId);
			bsUserStuFavorable.setUserId(teacher.getUserId());
			bsUserStuFavorable.setStatu(0);
			bsUserStuFavorableService.addCoupon(bsUserStuFavorable); 
			result.setIserror(false);
			result.setMessage("添加优惠券成功");
		}catch(Exception e){
			result.setMessage("ERROR:系统错误"+e.getMessage());
			result.setIserror(true);
		} 
		return result;
	} 
	
	
	//导出excel
	@RequestMapping("/exportExcel")   //String majorId,String headColor,String intention,String mobile,String studentName,String keyword,
	@ResponseBody
	public ResultAction exportExcel(@RequestBody String[] choiceStudents){ 
		ResultAction sResult = new ResultAction();
		System.out.println("choiceStudents:"+choiceStudents);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("学生表1");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ceateCell(row,style,0,"序号");
		ceateCell(row,style,1,"姓名");
		ceateCell(row,style,2,"用户名");
		ceateCell(row,style,3,"手机号码");
		ceateCell(row,style,4,"最后登录时间");
		ceateCell(row,style,5,"第一轮学习时长");
		ceateCell(row,style,6,"答题正确率");
		ceateCell(row,style,7,"课程属性");
		ceateCell(row,style,8,"学员来源"); 
		ceateCell(row,style,9,"意向程度");
		ceateCell(row,style,10,"意向备注");
		AllStudentQuery allStudentQuery = new AllStudentQuery(null,null,null);
		allStudentQuery.setKeyword(null); 
		allStudentQuery.setChoiceStudents(choiceStudents);
		List<AllStudent> studentInfos =  studentInfoService.downExcel(allStudentQuery);
		int i=0;
		int sheetNum = 1;
		int cursor=0;
		if(studentInfos!=null){
			while(i<studentInfos.size()){
				AllStudent allStudent = studentInfos.get(i);
				row = sheet.createRow(cursor+1);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(allStudent.getStudentId());
				row.createCell(2).setCellValue(allStudent.getStudentName());
				row.createCell(3).setCellValue(allStudent.getMobile());
				row.createCell(4).setCellValue(allStudent.getLastTime());
				row.createCell(5).setCellValue(allStudent.getFirstDuration());
				row.createCell(6).setCellValue(allStudent.getRightRate());
				row.createCell(7).setCellValue(allStudent.getStudentAttr()); 
				row.createCell(7).setCellValue(allStudent.getStudentSource()); 
				i++;
				cursor++;
				if(cursor/65535!=0){
					sheetNum++;
					cursor=0;
					sheet = wb.createSheet("学生表"+sheetNum);
				}
			} 
		} 
		FileOutputStream fos;
		try {
			File file = new File("E:/downFile/student.xls");
			if(!file.exists()){
				file.createNewFile();
			} 
			fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
		} catch (Exception e) { 
			e.printStackTrace();
			sResult.setIserror(true);
			logger.error(e.getMessage());
		} 
		sResult.setMessage("SUCCESS");
		return sResult;
	}
	
	public void ceateCell(HSSFRow row,HSSFCellStyle style,int index,String label){
		HSSFCell cell = row.createCell(index);
		cell.setCellValue(label);
		cell.setCellStyle(style);
	}
	
	//downExcel
	@RequestMapping("/downloadExcel")
	@ResponseBody
	public void downloadExcel(HttpServletResponse response){
		 try {
			response.setContentType("multipart/form-data");  
			response.setHeader("Content-Disposition", "attachment;fileName=student.xls");
			FileInputStream fis = new FileInputStream("E:/downFile/student.xls");
			byte[] buf = new byte[1024];
			OutputStream out = response.getOutputStream();
			int len=0;
			while((len=fis.read(buf))!=-1){
				out.write(buf,0,len);
			}
			fis.close();
			out.flush();
			out.close();
		} catch (Exception e) { 
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
	}
	
	//学员学习情况详情
	@RequestMapping(value="/myStudentDetail",method=RequestMethod.GET)
	public String myStudentDetail(String majorId,String studentId,HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response){
		//根据studentId获取他报考的专业科目。
		List<CourseMajorSubject> courseMajorSubjects = courseMajorSubjectService.findCourseMajorSubjectListByStudentId(studentId);
		System.out.println("-----------studentId:"+studentId);
		try { 
			int isFree=1; 
			StudentInfo studentInfo = studentInfoService.findByUserId(studentId); 
			StudentInfoDetail studentDetailInfo = studentInfoDetailService.findByStudentId(studentId);
			StudentPlanQuery studentPlanQuery = new StudentPlanQuery();
			session.removeAttribute("isFree"); 
		    studentPlanQuery.setStudentId(studentId); 
		    List<StudentPlanLearnResult> studentPlanLearnResults = new ArrayList<StudentPlanLearnResult>();
		    List<StudentPlan> studentPlans = studentPlanService.findByStudentId(studentPlanQuery);
		     for (StudentPlan studentPlan : studentPlans) { 
			    if(studentPlan!=null){ 
			    	StudentPlanLearnResult studentPlanLearnResult = new StudentPlanLearnResult();
			    	List<StudentPlanLog> studentPlanLogs=studentPlanService.findByPlanId(studentPlan.getPlanId(),studentInfo);
				    StudentPlanLearn studentPlanLearn = studentPlanLearnService.queryStudentPlanLearnsByPlan(studentPlan);
				    if(studentPlanLearn!=null){
					    int isGrapKnowLedgeCount=studentPlanLearnService.findByIsGrasp(studentPlan.getPlanId());
						studentPlanService.calculateSuccessRate(studentPlan,studentPlanLearn,model); 
						studentPlanLearn.setOneLearnTime(Float.valueOf(String.format("%.2f",studentPlanLearn.getOneLearnTime()/3600))); 
						studentPlanLearn.setTwoLearnTime(Float.valueOf(String.format("%.2f",studentPlanLearn.getTwoLearnTime()/3600)));
						studentPlanLearn.setTotalLearnTime(Float.valueOf(String.format("%.2f",studentPlanLearn.getTotalLearnTime()/3600)));
						studentPlanLearn.setReviewRate((float)Math.round((studentPlanLearn.getReviewRate()/studentPlanLearn.getKnowledgePointCoverage())*100));
						studentPlanLearn.setTwoPracticeRate((float)Math.round((studentPlanLearn.getTwoPracticeRate())*100));
						studentPlanLearn.setTempo(Math.round((isGrapKnowLedgeCount/studentPlanLearn.getKnowledgePointCoverage()*100))/100);
						float pie = (studentPlanLearn.getOneLearnTime()+studentPlanLearn.getTwoLearnTime())/studentPlanLearn.getTotalLearnTime(); 
						studentPlanLearn.setPie((1-Float.valueOf(String.format("%.4f",pie))));
						float knowledgePointCoverage = studentPlanLearn.getKnowledgePointCoverage();
						if(knowledgePointCoverage<1){
						  studentPlanLearn.setCoverage(0);
						}else{
					      studentPlanLearn.setCoverage(isGrapKnowLedgeCount/studentPlanLearn.getKnowledgePointCoverage());
						}
						int totalDay = studentPlan.getTotalDay()*studentPlan.getDayTime();
						studentPlan.setTotalVideoTime(totalDay);  
						studentPlanLearnResult.setStudentPlanLearn(studentPlanLearn);
				    } 
					studentPlanLearnResult.setStudentPlan(studentPlan); 
					studentPlanLearnResult.setStudentPlanLogs(studentPlanLogs);
					studentPlanLearnResults.add(studentPlanLearnResult);
			   } 
			} 
	 
		    //回访记录 
		    BsUserInfo teacher = getCurrentStudentInfo(session);
		    BsCallbackInfoQuery bsCallbackInfoQuery = new BsCallbackInfoQuery(teacher.getUserId(),studentId);   
		    int bsNotices = bsCallbackInfoService.findNoticeCount(bsCallbackInfoQuery);
		    model.addAttribute("bsNotices",bsNotices);
		    
		    model.addAttribute("courseMajorSubjects", courseMajorSubjects); 
		    model.addAttribute("studentId",studentId);
		    model.addAttribute("studentDetailInfo",studentDetailInfo); 
		    model.addAttribute("studentPlanLearnResults", studentPlanLearnResults);
		  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return COMMON_ERROR_JSP;
		}
		return "/modules/mystudent/myStudentDetail";
	}
	
	/**
	 * @Description:获取我的考试变化
	 * @param model
	 * @param session
	 * @return
	 * ResultAction<List<StudentExamInfo>>
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年9月23日 下午3:28:34
	 */
	@RequestMapping("/getStudentExamInfo")
	@ResponseBody
	public ResultAction<List<StudentScore<List<Float>>>> getStudentExamInfo(String studentId,Model model,HttpSession session,String majorId,String subjectId){
		System.out.println("studentId:"+studentId+"majorId:"+majorId+"subjectId:"+subjectId);
		ResultAction<List<StudentScore<List<Float>>>> resultAction = new ResultAction<List<StudentScore<List<Float>>>>();
		try {
			List<StudentScore<List<Float>>> dataList = new ArrayList<StudentScore<List<Float>>>(); 
			StudentInfo studentInfo = studentInfoService.findByUserId(studentId);
			List<StudentHighScore> studentExamInfos=studentExamInfoService.getStudentExamInfo(studentInfo,majorId,subjectId);
			List<StudentHighScore> monthStudentHighScore = new ArrayList<StudentHighScore>();//月月考	
			List<StudentHighScore> examStudentHighScore = new ArrayList<StudentHighScore>();//真题
			List<StudentHighScore> simulateStudentHighScore = new ArrayList<StudentHighScore>();//模拟
			for (int i = 0; i < studentExamInfos.size(); i++) {
				StudentHighScore studentHighScore = studentExamInfos.get(i);
				if(studentHighScore.getSourceType()==0){
					monthStudentHighScore.add(studentHighScore);
				}else if(studentHighScore.getSourceType()==1){
					examStudentHighScore.add(studentHighScore);
				}else if(studentHighScore.getSourceType()==2){
					simulateStudentHighScore.add(studentHighScore);
				}
			}
			StudentScore<List<Float>> stu1 = new StudentScore<List<Float>>();
			StudentScore<List<Float>> stu2 = new StudentScore<List<Float>>();
			StudentScore<List<Float>> stu3 = new StudentScore<List<Float>>();
			stu1.setName("月月考");
			stu2.setName("模考");
			stu3.setName("真题");
			List<Float> allgrade1 = new ArrayList<Float>();
			List<Float> allgrade2 = new ArrayList<Float>();
			List<Float> allgrade3 = new ArrayList<Float>();
			if(monthStudentHighScore.size()>0){
				for (int i = 1; i <= 12; i++) {
					boolean isTrue = false;
					for (int j = 0; j < monthStudentHighScore.size(); j++) {
						if(SafeUtils.getInt(monthStudentHighScore.get(j).getMonths())==i){
							//System.out.println(SafeUtils.getInt(monthStudentHighScore.get(j).getMonths()));
							allgrade1.add(monthStudentHighScore.get(j).getScore());
							isTrue =true;
							break;
						}
					}
					if(!isTrue)
						allgrade1.add(0f);
				}
				stu1.setData(allgrade1);
				dataList.add(stu1);
			}
			if(examStudentHighScore.size()>0){
				for (int i = 1; i <= 12; i++) {
					boolean isTrue = false;
					for (int j = 0; j < examStudentHighScore.size(); j++) {
						if(SafeUtils.getInt(examStudentHighScore.get(j).getMonths())==i){
							//System.out.println(SafeUtils.getInt(monthStudentHighScore.get(j).getMonths()));
							allgrade2.add(examStudentHighScore.get(j).getScore());
							isTrue =true;
							break;
						}
					}
					if(!isTrue)
						allgrade2.add(0f);
				}
				stu2.setData(allgrade2);
				dataList.add(stu2);
			}
			if(simulateStudentHighScore.size()>0){
				for (int i = 1; i <= 12; i++) {
					boolean isTrue = false;
					for (int j = 0; j < simulateStudentHighScore.size(); j++) {
						if(SafeUtils.getInt(simulateStudentHighScore.get(j).getMonths())==i){
							//System.out.println(SafeUtils.getInt(monthStudentHighScore.get(j).getMonths()));
							allgrade3.add(simulateStudentHighScore.get(j).getScore());
							isTrue =true;
							break;
						}
					}
					if(!isTrue)
						allgrade3.add(0f);
				}
				stu3.setData(allgrade3);
				dataList.add(stu3);
			}
			resultAction.setData(dataList);
			resultAction.setIserror(false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			resultAction.setIserror(true);
		}
		return resultAction;
	}
	
	/*
	 * 学员学习情况饼图
	 * */ 
	@RequestMapping(value="/myStudentStatusPie")
	public String myStudentStatusPie(String pie,Model model){
		model.addAttribute("pie", pie);
		return "/modules/mystudent/myStudentStatusPie";
	}
	
	/*
	 * 学员成绩变化图
	 * */
	@RequestMapping(value="/myStudentScoreLine")
	public String myStudentScoreLine(String studentId,String majorId,String subjectId,Model model){
		model.addAttribute("studentId", studentId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("subjectId", subjectId); 
		return "/modules/mystudent/myStudentScoreLine";
	}
	
	
	/*
	 * 添加回访记录
	 * */
	@RequestMapping(value="/addCallbackInfo")
	@ResponseBody
	public ResultAction addCallbackInfo(String callbackContent,String studentId,Model model,HttpSession session){
		ResultAction result = new ResultAction();
		BsUserInfo teacher = getCurrentStudentInfo(session);
		BsCallbackInfo bsCallbackInfo= new BsCallbackInfo();
		bsCallbackInfo.setCallbackContent(callbackContent);
		bsCallbackInfo.setTeacherId(teacher.getUserId());
		bsCallbackInfo.setCreateTime(new Date(System.currentTimeMillis()));
		bsCallbackInfo.setStudentId(studentId);
		bsCallbackInfo.setStatu(1);  
		bsCallbackInfoService.addCallbackInfo(bsCallbackInfo);   
	    BsCallbackInfoQuery bsCallbackInfoQuery = new BsCallbackInfoQuery(teacher.getUserId(),studentId);   
	    int bsNotices = bsCallbackInfoService.findNoticeCount(bsCallbackInfoQuery);
	    result.setData(bsNotices);
		return result;
	}
	
	/*查询回访记录*/
	@RequestMapping(value="/queryCallbackInfo")
	@ResponseBody
	public ResultAction queryCallbackInfo(String callbackContent,String studentId,Model model,HttpSession session,int pageIndex,int pageSize){
		ResultAction result = new ResultAction();
		BsUserInfo teacher = getCurrentStudentInfo(session);  
		BsCallbackInfoQuery bsCallbackInfoQuery = new BsCallbackInfoQuery(teacher.getUserId(),studentId);
		bsCallbackInfoQuery.setPageNumber(pageIndex);
		bsCallbackInfoQuery.setPageSize(pageSize); 
		List<BsCallbackInfo> bsCallbackInfos = bsCallbackInfoService.selectByTeacherAndStudent(bsCallbackInfoQuery);
		result.setData(bsCallbackInfos); 
		return result;
	} 
	
	
	/**
	 * Private serial version unique ID to ensure serialization
	 * compatibility.
	 */
	 static final long serialVersionUID = 8397324403548013681L;
}