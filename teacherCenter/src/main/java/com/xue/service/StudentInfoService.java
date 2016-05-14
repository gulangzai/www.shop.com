package com.xue.service;

import java.util.List;

import com.xue.dto.AllStudent;
import com.xue.model.CourseMajorSubject;
import com.xue.model.StudentInfo;
import com.xue.model.StudentInfoDetail;
import com.xue.model.StudentPlanLearn;
import com.xue.vo.query.AllStudentQuery;


public interface StudentInfoService {
	  
	List<AllStudent> queryAllStudents(AllStudentQuery allStudentQuery);
 
	int queryCount(AllStudentQuery allStudentQuery);
	
	List<AllStudent> queryStudentByShop(AllStudentQuery allStudentQuery);

	int queryStudentByShopCount(AllStudentQuery allStudentQuery);

	List<AllStudent> queryStudentByShopKeyword(AllStudentQuery allStudentQuery);

	int queryStudentByShopKeywordCount(AllStudentQuery allStudentQuery);
	
	List<AllStudent> downExcel(AllStudentQuery allStudentQuery);

	int resetPassword(String phone);

	int deleteStudentByPhone(String mobile);

	StudentInfo login(String username, String newpassword);
  
	public int updateByPrimaryKeySelective(StudentInfo studentInfo);

	/**
	 * @Description:判断用户名是否唯一
	 * @param studentInfo
	 * @return
	 * StudentInfo
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月9日 下午5:38:58
	 */
	public List<StudentInfo> findByStudentName(StudentInfo studentInfo);

	public int save(StudentInfo studentInfo); 

	/**
	 * @Description:根据ID寻找用户
	 * @param userId
	 * @return
	 * StudentInfo
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年11月4日 下午1:51:54
	 */
	public StudentInfo findByUserId(String userId);

	StudentPlanLearn queryStudentPlanLearnsByStudentId(String studentId);

	StudentInfo findByStudentMobile(String tel);

	void inputStudent(StudentInfo studentInfo, StudentInfoDetail studentInfoDetail) throws Exception;
 
}
