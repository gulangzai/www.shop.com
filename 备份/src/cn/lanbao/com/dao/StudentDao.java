package cn.lanbao.com.dao;

import java.util.List;
import cn.lanbao.com.jopo.Student;

public interface StudentDao {

	void save(Student student);

	List<Student> queryAllStudent();

}
