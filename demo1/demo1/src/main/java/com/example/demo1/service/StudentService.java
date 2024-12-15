package com.example.demo1.service;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo1.Dto.ResponseModel;
import com.example.demo1.Dto.StudentDao;
import com.example.demo1.entity.Student;
import com.example.demo1.repository.StudentRepository;
import com.example.demo1.utils.CustomMessages;
import com.example.demo1.utils.FileUtils;
import com.google.gson.Gson;

@Service
public class StudentService {
		
	@Autowired
	private FileUtils fileUtils;
	@Autowired
    private StudentRepository repo;  
	
	 @Autowired
	    private KafkaService kafkaService;
    
	public ResponseModel addStudent(StudentDao dao, HttpServletRequest request) {
		return null;
		
	}

	public ResponseModel addStudent(String json, MultipartFile studentImage, HttpServletRequest request) {
		try {
			JSONObject requestObj = new JSONObject(json);
			StudentDao dao = new Gson().fromJson(json, StudentDao.class);
			Student st = new Student();
			if (studentImage != null) {
				File file = fileUtils.uploadFile(studentImage, "studentImage");

				if (file != null) {
					requestObj.put("studentImage", file.getName());
					st.setImage(file.getName());
				}
			}
			st.setAddress(dao.getAddress());
			st.setName(dao.getName());
			st.setIsActive(true);
			repo.save(st);
			return new ResponseModel(st, CustomMessages.SUCCESS, CustomMessages.ADD_SUCCESSFULLY,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}

	public ResponseModel getStudentList(HttpServletRequest request) {
		try {
			List<Student> studentList = repo.findAll();
			this.kafkaService.updateLocation(studentList.toString());
			return new ResponseModel(studentList, CustomMessages.SUCCESS, CustomMessages.GET_DATA_SUCCESS,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}

	public ResponseModel updateStudent(String json, MultipartFile studentImage, HttpServletRequest request) {
		try {
			JSONObject requestObj = new JSONObject(json);
			StudentDao dao = new Gson().fromJson(json, StudentDao.class);
			Student st =repo.findByStudentId(dao.getId());
			if (studentImage != null) {
				File file = fileUtils.uploadFile(studentImage, "studentImage");

				if (file != null) {
					requestObj.put("studentImage", file.getName());
					st.setImage(file.getName());
				}
			}
			st.setAddress(dao.getAddress());
			st.setName(dao.getName());
			st.setIsActive(true);
			repo.save(st);
			return new ResponseModel(st, CustomMessages.SUCCESS, CustomMessages.ADD_SUCCESSFULLY,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}

	public ResponseModel getStudentListById(HttpServletRequest request, Long id) {
		try {
			Optional<Student> studentList = repo.findById(id);

			return new ResponseModel(studentList, CustomMessages.SUCCESS, CustomMessages.GET_DATA_SUCCESS,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}

}
