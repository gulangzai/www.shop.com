/*
 * StudentinfoMapper.java
 * Copyright(C) 2015-2018 ѧ��ɭ
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.dao;

import java.util.HashMap;
import java.util.List;

import com.xue.dto.AllStudent;
import com.xue.dto.StudentAndDetailsResult;
import com.xue.model.CourseMajorSubject;
import com.xue.model.StudentInfo;
import com.xue.vo.query.AllStudentQuery;
import com.xue.vo.query.ResetPasswordQuery;
import com.xue.vo.query.StudentInfoQuery;;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    List<StudentAndDetailsResult> queryAllStudentsForPage(AllStudentQuery allStudentQuery);

	int queryCount(AllStudentQuery allStudentQuery);
 
	int queryCountByKeyword(String keyword);
  
	List<StudentInfo> queryByKeyword(HashMap map);

	int resetPassword(ResetPasswordQuery resetPasswordQuery);

	int deleteStudentByPhone(HashMap<String,String> map);

	StudentInfo login(StudentInfoQuery studentInfoQuery);

	List<StudentInfo> queryAllStudents();

	//通过student_id marger_id 
	List<StudentInfo> queryStudentByShop(AllStudentQuery allStudentQuery);
	
	int queryStudentByShopCount(AllStudentQuery allStudentQuery);	
	
	//通过student_id marger_id  keyword模糊查询
	List<StudentInfo> queryStudentByShopKeyword(AllStudentQuery allStudentQuery);
	
	int queryStudentByShopKeywordCount(AllStudentQuery allStudentQuery);
	
	
 
	List<StudentAndDetailsResult> downExcel(AllStudentQuery allStudentQuery);

	StudentInfo findByStudentMobile(String mobile);
     
	/*
	 * 通过studentId获取报考的专业和科目
	 * */
	List<CourseMajorSubject> findCourseMajorSubjectListByStudentId(String studentId);
  
}