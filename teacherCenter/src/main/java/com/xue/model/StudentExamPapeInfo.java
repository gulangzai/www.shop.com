package com.xue.model;

public class StudentExamPapeInfo {
	/**
	 * 学员考试信息
	 */
	private StudentExamInfo studentExamInfo;
	/**
	 * 试卷信息
	 */
	private PaperInfo paperInfo;

	public PaperInfo getPaperInfo() {
		return paperInfo;
	}

	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}

	public StudentExamInfo getStudentExamInfo() {
		return studentExamInfo;
	}

	public void setStudentExamInfo(StudentExamInfo studentExamInfo) {
		this.studentExamInfo = studentExamInfo;
	}

	
	
}
