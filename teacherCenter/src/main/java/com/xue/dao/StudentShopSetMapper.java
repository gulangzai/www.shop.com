package com.xue.dao;

import java.util.List;

import com.xue.dto.OpenCourseQuery;
import com.xue.model.StudentShopSet;

public interface StudentShopSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentShopSet record);

    int insertSelective(StudentShopSet record);

    StudentShopSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentShopSet record);

    int updateByPrimaryKey(StudentShopSet record);
    
    List<StudentShopSet> queryStudentShopSetList(StudentShopSet studentShopSet);
    
}