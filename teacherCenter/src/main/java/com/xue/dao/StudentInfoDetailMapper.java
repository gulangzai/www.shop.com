package com.xue.dao;

import com.xue.model.StudentInfoDetail;

public interface StudentInfoDetailMapper {

	void insert(StudentInfoDetail studentInfoDetail);

	StudentInfoDetail findByStudentId(String studentId);

}
