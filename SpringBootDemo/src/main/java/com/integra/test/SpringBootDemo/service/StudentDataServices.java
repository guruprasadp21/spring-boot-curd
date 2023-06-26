package com.integra.test.SpringBootDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.integra.test.SpringBootDemo.entity.Student;
import com.integra.test.SpringBootDemo.exception.ResourceNotFoundException;
import com.integra.test.SpringBootDemo.repository.StudentRepository;

@Service
public class StudentDataServices {

	@Autowired
	private StudentRepository studentrepository;

	public List<Student> getAllStudents() {
		List<Student> findAll = (List<Student>) studentrepository.findAll();
		if(findAll.isEmpty()) {
			throw new ResourceNotFoundException("Data Not Found");
		}
		try {
		return findAll;
	}catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException( "Exception While fetching the Student list " + e.getMessage());
		}     
	}
	public Student getStudent(Integer id) {
		try {
			Optional<Student> optional = studentrepository.findById(id);
			if(optional.isPresent()) {
             Student student = optional.get();
             return student;
		}else {
			throw new ResourceNotFoundException("No data is found in this ID");
		}
	} catch(ResourceNotFoundException e) {
		throw new ResourceNotFoundException("Exception While fetching the Student" + e.getMessage());
	}
}	

	public Student addStudent(Student student) {
		try {
			if(student.getBranch().isEmpty()) {
				throw new ResourceNotFoundException("Please Send Proper data");
			}
			
			return studentrepository.save(student);
		} catch(ResourceNotFoundException  e) {
			throw new ResourceNotFoundException("Exception While Inserting the Student data" + e.getMessage());
		}
	}

	public Student updateStudent(Integer id, Student student) {
        try {
		Optional<Student> optional = studentrepository.findById(id);
        if(optional.isPresent()) {
        	Student student2 = optional.get();
    		student2.setName(student.getName()); 
    		student2.setBranch(student.getBranch());
    		optional.get();
    		Student save = studentrepository.save(student);
    		return save;
    	} else {
    		throw new ResourceNotFoundException("No data is found in this ID");
    	}
      } catch(ResourceNotFoundException e) 
        {
    	  throw new ResourceNotFoundException("Exception While Updating the Student data" + e.getMessage());
        }
	}
		/*
		 * if (optional.isPresent()) { Student student1 = new Student();
		 * student1.setName(student.getName()); student1.setBranch(student.getBranch());
		 * studentrepository.save(student1); }
		 */

		/*
		 * Optional<Student> optional = studentrepository.findById(id2); Student stu =
		 * optional.get(); System.out.println(stu); studentrepository.save(stu);
		 */

	public void deleteStudent(Integer id) {
		try {
			Optional<Student> idValue = studentrepository.findById(id);
			if(idValue.isPresent())
			{
				studentrepository.deleteById(id);
			}
			else {
				throw new ResourceNotFoundException( "ID is not present in the DB");
			}
		   
		} catch(ResourceNotFoundException  e) {
			throw new ResourceNotFoundException("ID is not Found in the DB " + e.getMessage());
		}
		
	}

	public void deleteAllStudent(Student student) {
		try {
		studentrepository.deleteAll();
		} catch(ResourceNotFoundException  e) {
			throw new ResourceNotFoundException("No Data Found to Delete " + e.getMessage());
		}
	}

}  
