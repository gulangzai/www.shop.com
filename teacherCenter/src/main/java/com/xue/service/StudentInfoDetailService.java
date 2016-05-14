package com.xue.service;

import com.xue.model.StudentInfoDetail;

public interface StudentInfoDetailService {

	/*
	 * 通过studentId获取studentInfoDetail详情
	 * */
	StudentInfoDetail findByStudentId(String studentId);

	void save(StudentInfoDetail studentInfoDetail);

}
