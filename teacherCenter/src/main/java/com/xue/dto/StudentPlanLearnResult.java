package com.xue.dto;

import java.util.List;

import com.xue.model.StudentPlan;
import com.xue.model.StudentPlanLearn;
import com.xue.model.StudentPlanLog;

public class StudentPlanLearnResult {
	
	private StudentPlan studentPlan = null;
	
	private StudentPlanLearn studentPlanLearn = null;
	
	private List<StudentPlanLog> studentPlanLogs = null;

	public StudentPlan getStudentPlan() {
		return studentPlan;
	}

	public void setStudentPlan(StudentPlan studentPlan) {
		this.studentPlan = studentPlan;
	}

	public StudentPlanLearn getStudentPlanLearn() {
		return studentPlanLearn;
	}

	public void setStudentPlanLearn(StudentPlanLearn studentPlanLearn) {
		this.studentPlanLearn = studentPlanLearn;
	}

	public List<StudentPlanLog> getStudentPlanLogs() {
		return studentPlanLogs;
	}

	public void setStudentPlanLogs(List<StudentPlanLog> studentPlanLogs) {
		this.studentPlanLogs = studentPlanLogs;
	}
	
}
