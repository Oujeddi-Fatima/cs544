package edu.mum.cs.cs544.timesheet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs.cs544.timesheet.dao.StudentDAO;
import edu.mum.cs.cs544.timesheet.entity.Student;

@Transactional
@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO{

	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
