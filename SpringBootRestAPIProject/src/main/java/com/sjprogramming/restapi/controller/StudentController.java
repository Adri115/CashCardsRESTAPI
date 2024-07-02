package com.sjprogramming.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	//Get a student by Id
	@GetMapping("/student/{requestedId}")
	public ResponseEntity<Student> getStudent(@PathVariable int requestedId) {
		Optional<Student> searched = repo.findById(requestedId);
		if(searched.isPresent()) {
		Student student = searched.get();
		return ResponseEntity.ok(student);
		}
		return ResponseEntity.noContent().build();
	}
	
	//Create a student
	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void postStudent(@RequestBody Student student) {
		repo.save(student);
	}
	
	//Update Student
	@PutMapping("/student/update/{requestedId}")
	public ResponseEntity<Student> updateStudent(@PathVariable int requestedId, @RequestBody Student student) {
	    Optional<Student> optionalStudent = repo.findById(requestedId);
	    if (optionalStudent.isPresent()) {
	        Student studentToUpdate = optionalStudent.get();
	        studentToUpdate.setAll(student);
	        repo.save(studentToUpdate);
	        return ResponseEntity.ok(studentToUpdate);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	//Delete Student
	@DeleteMapping("/student/delete/{requestedId}")
	public ResponseEntity<Student> deleteStudent(@PathVariable int requestedId) {
		Optional<Student> optionalStudent = repo.findById(requestedId);
		if(optionalStudent.isPresent()) {
			repo.delete(optionalStudent.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
