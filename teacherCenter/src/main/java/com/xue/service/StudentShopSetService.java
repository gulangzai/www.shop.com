package com.xue.service;

import java.util.List;

import com.xue.dto.OpenCourseQuery;
import com.xue.model.StudentShopSet;

public interface StudentShopSetService {

	List<StudentShopSet> queryStudentShopSetList(StudentShopSet studentShopSet);

	void openCourse(OpenCourseQuery openCourseQuery);

}
