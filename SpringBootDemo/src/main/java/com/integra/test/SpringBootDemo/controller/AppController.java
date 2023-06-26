package com.integra.test.SpringBootDemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.integra.test.SpringBootDemo.entity.Student;
import com.integra.test.SpringBootDemo.exception.AppError;
import com.integra.test.SpringBootDemo.exception.ResourceNotFoundException;
import com.integra.test.SpringBootDemo.service.StudentDataServices;

@RestController
public class AppController {

	@Autowired
	private StudentDataServices studentdataServices;

	@RequestMapping("/getallStudents")
	public ResponseEntity<?> getAllStudent() {
		AppError error = new AppError(605, "Data is Not Avilable in DB");
		try {
			List<Student> allStudents =  studentdataServices.getAllStudents();
			return new ResponseEntity<>(allStudents, HttpStatus.OK);
		} catch(ResourceNotFoundException e) {
			//throw new ResourceNotFoundException("Exception while Fetching Student Data"+ e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<?> getStudent(@PathVariable Integer id){
		AppError error = new AppError(604, "ID is not Present in the DB");
		try {
		Student getStudent=studentdataServices.getStudent(id);
		return new ResponseEntity<>(getStudent, HttpStatus.OK);
	} catch(ResourceNotFoundException e) {
		//throw new ResourceNotFoundException("Exception while Fetching Student Data " + e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
}

	@RequestMapping(method = RequestMethod.POST, value = "/addStudent")
	public ResponseEntity<?> addStudent(@RequestBody Student student) {
		AppError error = new AppError(606, "Please Provide Proper data");
		try {
			Student add = studentdataServices.addStudent(student);
			return new ResponseEntity<>(add, HttpStatus.OK);
		} catch(ResourceNotFoundException e) {
			//throw new ResourceNotFoundException("Exception while Inserting Student Data" + e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateStudent/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
		AppError error = new AppError(602, "ID is not present in the DB");
		try {
		studentdataServices.updateStudent(id,student);
		return new ResponseEntity<>("Updated SuccessFully", HttpStatus.OK); 
	} catch(ResourceNotFoundException e) {
		//throw new ResourceNotFoundException("Exception while Updating Student Data" + e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}  


	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteStudent/{id}") 
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) { 
		AppError error = new AppError(603, "ID is not Present");
		try {
			studentdataServices.deleteStudent(id); 
			return new ResponseEntity<>("Deleted SuccessFully", HttpStatus.OK); 
		}
		catch(ResourceNotFoundException e) {
			//throw new ResourceNotFoundException("Exception while Deleting Student Data" + e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	   } 
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteAllStudent") 
	public ResponseEntity<?> deleteAllStudent(@RequestBody Student sudent) {
		try {
			studentdataServices.deleteAllStudent(sudent); 
		return new ResponseEntity<>("All Student Data are Deleted", HttpStatus.OK);
		}
		catch(ResourceNotFoundException e) { 
			return new ResponseEntity<>(" Exception while Deleting Student Data", HttpStatus.NOT_FOUND);
			} 
		}
	}                


      














