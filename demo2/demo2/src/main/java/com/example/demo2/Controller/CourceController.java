package com.example.demo2.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.ResponseModel;
import com.example.demo2.Service.CourceService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/mainCourse")
public class CourceController {

	
	
	
	@Autowired
	private CourceService courceService;
	
	
	@PostMapping(value = "/addCource", consumes = "multipart/form-data")
	public ResponseModel addCource(@RequestParam("data") String json,
			@RequestParam(value = "courceImage", required = false) MultipartFile courceImage,
			HttpServletRequest request) {
		
		ResponseModel ngredient = courceService.addCource(json, courceImage, request);
		
		return ngredient;
	}
	
	
	@GetMapping(value = "/getCourceList")
	public ResponseModel getCourceList(HttpServletRequest request) {
		ResponseModel cource = courceService.getCourceList(request);
		return cource;
	}


	
}
