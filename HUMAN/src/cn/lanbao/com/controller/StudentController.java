package cn.lanbao.com.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import cn.lanbao.com.jopo.Student;
import cn.lanbao.com.service.StudentService;

public class StudentController  {
	
	private StudentService studentService;
	
	
	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public ModelAndView save(Student student){
		studentService.save(student);
		ModelAndView modelAndView = new ModelAndView("success");
		return modelAndView;
	}
	 
}
