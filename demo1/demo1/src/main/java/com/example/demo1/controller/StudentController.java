package com.example.demo1.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo1.Dto.ResponseModel;
import com.example.demo1.Dto.StudentDao;
import com.example.demo1.config.CourseFeign;
import com.example.demo1.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseFeign courseFeign;
	
	@PostMapping(value = "/addStudent", consumes = "multipart/form-data")
	public ResponseModel addStudent(@RequestParam("data") String json,
			@RequestParam(value = "studentImage", required = false) MultipartFile studentImage,
			HttpServletRequest request) {
		
		ResponseModel st = studentService.addStudent(json, studentImage, request);
		logger.info("Response => " + st);
		return st;
	}
	
	@PostMapping(value = "/updateStudent", consumes = "multipart/form-data")
	public ResponseModel updateStudent(@RequestParam("data") String json,
			@RequestParam(value = "studentImage", required = false) MultipartFile studentImage,
			HttpServletRequest request) {
		
		ResponseModel st = studentService.updateStudent(json, studentImage, request);
		
		return st;
	}
	
	
	@GetMapping(value = "/getStudentList")
	public ResponseModel getStudentList(HttpServletRequest request) {
		ResponseModel st = studentService.getStudentList(request);
				return st;
	}
	
	@GetMapping(value = "/getcourceList")
	public String getcourceList(HttpServletRequest request) {
//		ResponseModel IngredientrList = courseFeign.getCourceList();
		return courseFeign.getCourceList();
	}
	
	
	@GetMapping(value = "/getStudentListById/{id}")
	public ResponseModel getStudentListById(HttpServletRequest request,@PathVariable("id") Long id) {
		ResponseModel st = studentService.getStudentListById(request,id);
		return st;
	}
	
	
}
