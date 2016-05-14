package com.xue.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.StudentInfoDetailMapper;
import com.xue.dao.StudentInfoMapper;
import com.xue.dao.StudentPlanLearnMapper;
import com.xue.dao.StudentPlanMapper;
import com.xue.dto.AllStudent;
import com.xue.dto.StudentAndDetailsResult;
import com.xue.model.CourseMajorSubject;
import com.xue.model.StudentInfo;
import com.xue.model.StudentInfoDetail;
import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.service.StudentInfoService;
import com.xue.vo.query.AllStudentQuery;
import com.xue.vo.query.ResetPasswordQuery;
import com.xue.vo.query.StudentInfoQuery;
import com.xue.vo.query.StudentPlanQuery;

import javacommon.util.DateUtils;
import javacommon.util.DbUtil;
import javacommon.util.MD5Utils;
import javacommon.util.SafeUtils;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	
	@Autowired
	StudentInfoMapper studentInfoMapper;
	
	@Autowired
	StudentInfoDetailMapper studentInfoDetailMapper;
	
	@Autowired
	StudentPlanLearnMapper studentPlanLearnMapper;
	
	@Autowired
	StudentPlanMapper studentPlanMapper;
	
	@Override
	public List<AllStudent> queryAllStudents(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub
		if(allStudentQuery.getKeyword()!=null&&!allStudentQuery.getKeyword().equals("")){
			if (Pattern
					.compile("^((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
					.matcher(allStudentQuery.getKeyword()).matches()) {
				allStudentQuery.setMobile(allStudentQuery.getKeyword()); 
			} else{
				allStudentQuery.setStudentName(allStudentQuery.getKeyword());
			}
		} 
		List<StudentAndDetailsResult> studentInfos = studentInfoMapper.queryAllStudentsForPage(allStudentQuery);
		List<AllStudent> allStudents = getAllStudent(studentInfos,allStudentQuery);
		return allStudents; 
	}

	@Override
	public int queryCount(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub
		return studentInfoMapper.queryCount(allStudentQuery);
	}
	
	/*无关键词，
	 * currentPage:当前页
	 * headColor:条件  red yellow orange
	 * */
	@Override
	public List<AllStudent> queryStudentByShop(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub  
		List<StudentInfo> studentInfos = studentInfoMapper.queryStudentByShop(allStudentQuery);
		//List<AllStudent> allStudents = getAllStudent(studentInfos,allStudentQuery);
		return null;//allStudents;
	}
	
	@Override
	public int queryStudentByShopCount(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub 
		return studentInfoMapper.queryStudentByShopCount(allStudentQuery); 
	}
	
	@Override
	public List<AllStudent> queryStudentByShopKeyword(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub 
		List<StudentInfo> studentInfos = studentInfoMapper.queryStudentByShopKeyword(allStudentQuery);  
		//List<AllStudent> allStudents =  getAllStudent(studentInfos,allStudentQuery);
		return null;//allStudents;
	}

	@Override
	public int queryStudentByShopKeywordCount(AllStudentQuery allStudentQuery) {
		// TODO Auto-generated method stub
		return studentInfoMapper.queryStudentByShopKeywordCount(allStudentQuery);
	}
	
	//下载导出
	@Override
	public List<AllStudent> downExcel(AllStudentQuery allStudentQuery){ 
		 List<StudentAndDetailsResult> studentInfos = studentInfoMapper.downExcel(allStudentQuery);  
		 List<AllStudent> allStudents =  getAllStudent(studentInfos,allStudentQuery); 
		return  allStudents;//allStudents;//allStudents;
	}

	
	public List<AllStudent> getAllStudent(List<StudentAndDetailsResult> studentInfos, AllStudentQuery allStudentQuery){
		List<AllStudent> allStudents=new ArrayList();
		for (StudentAndDetailsResult studentAndDetailsResult : studentInfos) {
			AllStudent allStudent = new AllStudent();
			allStudent.setRealName(studentAndDetailsResult.getRealName());
			allStudent.setStudentName(studentAndDetailsResult.getStudentName());
			allStudent.setMobile(studentAndDetailsResult.getMobile());
			allStudent.setEmail(studentAndDetailsResult.getEmail());
		    allStudent.setLastTime(studentAndDetailsResult.getLastLoginTime()); 
		    allStudent.setLastLoginTime(studentAndDetailsResult.getLastLoginTime());
		    allStudent.setStudentId(studentAndDetailsResult.getStudentId());
		    if(allStudent.getRealName()==null){
		    	allStudent.setRealName("未填");
		    }
		    if(studentAndDetailsResult.getCourseProperty()==1){
		    	allStudent.setCoursePropertyLabel("纯网课");
		    }else{
		    	allStudent.setCoursePropertyLabel("未知");
		    }
		    if(studentAndDetailsResult.getStudentSource()==1){
		    	allStudent.setStudentSourceLabel("网盟");
		    }else{
		    	allStudent.setStudentSourceLabel("未知");
		    }
		    String intentLabel="未知";
		    switch(studentAndDetailsResult.getIntention()){
		    case 1:intentLabel = "低";
		    case 2:intentLabel = "中";
		    case 3:intentLabel = "高";
		    }
		    allStudent.setIntentionLabel(intentLabel);
		    
		    allStudent.setIntentionRamark(studentAndDetailsResult.getIntentionRamark()==null?"未知":studentAndDetailsResult.getIntentionRamark());
		    allStudent.setFirstDuration("0小时");
		    allStudent.setRightRate("0%");
		    //第一轮学习时长
		    StudentPlanLearn studentPlanLearn = (StudentPlanLearn) studentPlanLearnMapper.queryStudentPlanLearnsByStudentId(studentAndDetailsResult.getStudentId());
		    StudentPlanQuery studentPlanQuery = new StudentPlanQuery();
		    studentPlanQuery.setStudentId(studentAndDetailsResult.getStudentId());
            StudentPlan studentPlan = studentPlanMapper.findByStudent(studentPlanQuery);
		    if(studentPlanLearn!=null){
		     	  allStudent.setFirstDuration(String.format("%.2f",studentPlanLearn.getOneLearnTime()/3600)+"小时"); 
		     	  String correntRate =  "0%";
		     	  Integer alwaysaTopicQuantity = studentPlanLearn.getAlwaysATopicQuantity();
		     	  if((alwaysaTopicQuantity!=null&&alwaysaTopicQuantity!=0)){
		     		 correntRate = (studentPlanLearn.getCorrect()/studentPlanLearn.getAlwaysATopicQuantity()*100)+"%";
		     	  } 
		    	  allStudent.setRightRate(correntRate);
		    }   
		    //连续七天未登陆学员
		    if(allStudentQuery.getHeadColor()!=null&&allStudentQuery.getHeadColor().equals("red")){ 
				 long lastLoginTimeDatenum =0;
				 if(allStudent.getLastLoginTime()!=null&&!allStudent.getLastLoginTime().equals("")){
					 lastLoginTimeDatenum = DateUtils.getTime(allStudent.getLastLoginTime());
				 } 
			     long lastNum = System.currentTimeMillis()-1000*60*60*24*7;
			     if(lastLoginTimeDatenum<lastNum){ 
			    		allStudents.add(allStudent); 
			     } 
			 //每天学习时长达标率<30%的学员
		    }else if(allStudentQuery.getHeadColor()!=null&&allStudentQuery.getHeadColor().equals("yellow")){
		    	float learningTimeRate = 0;
		    	if(studentPlan!=null){ 
					//从计划开始到现在用了几天
			    	long needLearnDay=SafeUtils.getDaySub(studentPlan.getStartDate(),new Date());
			    	//一二轮总用时
					float learnDay=(studentPlanLearn.getOneLearnTime()+studentPlanLearn.getTwoLearnTime())/3600;
					//每日计划学习时长
					int studentDayTime=studentPlan.getDayTime();
					if(needLearnDay>0){
						learningTimeRate = (learnDay/needLearnDay);
						learningTimeRate = learningTimeRate/studentDayTime*100;
					}else{
						//total_day 总天数
						int totalDay = studentPlan.getTotalDay();
						learningTimeRate = learnDay/totalDay/studentDayTime*100;
					}
		    	} 
				if(learningTimeRate<30){ 
			    		allStudents.add(allStudent); 
				} 
				//100题<总答题量且答题正确率<50%的学员
		    }else if(allStudentQuery.getHeadColor()!=null&&allStudentQuery.getHeadColor().equals("orange")){
		    	float rightRate = Float.valueOf(allStudent.getRightRate().substring(0, allStudent.getRightRate().indexOf("%")));
		        if(rightRate<50&&studentPlanLearn.getAlwaysATopicQuantity()>100){ 
			    	allStudents.add(allStudent); 
		        }
		    }else{ 
		    		allStudents.add(allStudent); 
		    } 
		}
		return allStudents;
	}
 
	@Override
	public StudentPlanLearn queryStudentPlanLearnsByStudentId(String studentId){
	   return studentPlanLearnMapper.queryStudentPlanLearnsByStudentId(studentId);
	}

	@Override
	public int resetPassword(String mobile) {
		// TODO Auto-generated method stub
		ResetPasswordQuery resetPasswordQuery = new ResetPasswordQuery();
		resetPasswordQuery.setMobile(mobile);
		return studentInfoMapper.resetPassword(resetPasswordQuery);
	}

	@Override
	public int deleteStudentByPhone(String mobile) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mobile", mobile);
		return studentInfoMapper.deleteStudentByPhone(map); 
	}
	
	@Override
	public StudentInfo login(String studentName, String passWord) {
		// 加密密码
		// 判断用户输入的用户名是什么，用户名 or Email or 电话
		
		StudentInfoQuery studentInfoQuery = new StudentInfoQuery();
		studentInfoQuery.setPassword(passWord);
		if (Pattern
				.compile("^((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
				.matcher(studentName).matches()) {
			studentInfoQuery.setMobile(studentName);
			return studentInfoMapper.login(studentInfoQuery);
		} else if (Pattern
				.compile(
						"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
				.matcher(studentName).matches()) {
			studentInfoQuery.setEmail(studentName);
			return studentInfoMapper.login(studentInfoQuery);
		}
		studentInfoQuery.setStudentName(studentName);
		return studentInfoMapper.login(studentInfoQuery);
	}

	/*@Override
	public List<AllStudent> queryAllStudents() {
		// TODO Auto-generated method stub
		List<StudentInfo> studentInfos = studentInfoMapper.queryAllStudents();
		List<AllStudent> allStudents=new ArrayList();
		for (StudentInfo studentInfo : studentInfos) {
			AllStudent allStudent = new AllStudent();
			allStudent.setStudentName(studentInfo.getStudentName());
			allStudent.setMobile(studentInfo.getMobile());
			allStudent.setEmail(studentInfo.getEmail());
		    allStudent.setLastTime(studentInfo.getLastLoginTime()); 
		    allStudent.setLastLoginTime(studentInfo.getLastLoginTime());
		    allStudent.setStudentId(studentInfo.getStudentId());
		    StudentPlanLearn studentPlanLearn = (StudentPlanLearn) studentPlanLearnMapper.queryStudentPlanLearnsByStudentId(studentInfo.getStudentId());
		    String correntRate =  "0%";
			allStudent.setFirstDuration("0小时"); 
		    allStudent.setRightRate(correntRate); 
		    if(studentPlanLearn!=null){
		     	  allStudent.setFirstDuration(String.valueOf(studentPlanLearn.getOneLearnTime())+"小时");  
		     	  Integer alwaysaTopicQuantity = studentPlanLearn.getAlwaysATopicQuantity();
		     	  if((alwaysaTopicQuantity!=null&&alwaysaTopicQuantity!=0)){
		     		 correntRate = (studentPlanLearn.getCorrect()/studentPlanLearn.getAlwaysATopicQuantity()*100)+"%";
		     	  } 
		    	  allStudent.setRightRate(correntRate);
		    } 
		    allStudent.setStudentAttr("");
		    allStudent.setStudentSource("");
			allStudents.add(allStudent);
		}
		return allStudents;
	}*/
	
	@Override
	public StudentInfo findByUserId(String userId) {
		return studentInfoMapper.selectByPrimaryKey(userId);
	}

  
	@Override
	public List<StudentInfo> findByStudentName(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(StudentInfo studentInfo) {
		// TODO Auto-generated method stub 
		return studentInfoMapper.insert(studentInfo);
	}

	@Override
	public int updateByPrimaryKeySelective(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		return studentInfoMapper.updateByPrimaryKeySelective(studentInfo);
	}

	@Override
	public StudentInfo findByStudentMobile(String mobile) {
		// TODO Auto-generated method stub
		return studentInfoMapper.findByStudentMobile(mobile);
	}
    
	/*
	 * 向student_info 和 student_info_detail插入信息
	 * */
	@Override
	public void inputStudent(StudentInfo studentInfo,StudentInfoDetail studentInfoDetail)throws Exception {
		// TODO Auto-generated method stub  
			studentInfo.setIsDel(String.valueOf(0));
			studentInfo.setStudentId(studentInfoDetail.getStudentId());
			studentInfo.setMobile(studentInfoDetail.getMobilePhone()); 
			//默认密码123456
			studentInfo.setPassword(MD5Utils.createMD5("123456"));
			studentInfoMapper.insert(studentInfo);
			studentInfoDetailMapper.insert(studentInfoDetail);  
	} 
}
