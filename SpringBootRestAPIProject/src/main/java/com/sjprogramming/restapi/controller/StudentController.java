package com.sjprogramming.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sjprogramming.restapi.repository.StudentRepository;

import com.sjprogramming.restapi.entity.Student;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;

	//get all the students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student> students = repo.findAll();
		return students;
	}

}
