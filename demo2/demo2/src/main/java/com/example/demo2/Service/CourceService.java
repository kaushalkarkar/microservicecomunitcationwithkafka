package com.example.demo2.Service;

import java.io.File;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.CourceDto;
import com.example.Dto.ResponseModel;
import com.example.Utils.CustomMessages;
import com.example.Utils.FileUtils;
import com.example.demo2.Repository.CourceRepository;
import com.example.demo2.entity.Cource;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CourceService {
	
//	@Autowired
//	private FileUtils fileUtils;
	
	@Autowired
	private CourceRepository courceRepository;

	public ResponseModel addCource(String json, MultipartFile courceImage, HttpServletRequest request) {
		try {
			JSONObject requestObj = new JSONObject(json);
			CourceDto dao = new Gson().fromJson(json, CourceDto.class);
			Cource cource = new Cource();
//			if (courceImage != null) {
//				File file = fileUtils.uploadFile(courceImage, "courceImage");
//
//				if (file != null) {
//					requestObj.put("courceImage", file.getName());
//					cource.setImage(file.getName());
//				}
//			}
		cource.setName(dao.getName());
		cource.setPrice(dao.getPrice());
		cource.setDescription(dao.getDescription());
		
			courceRepository.save(cource);
			return new ResponseModel(cource, CustomMessages.SUCCESS, CustomMessages.ADD_SUCCESSFULLY,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}

	public ResponseModel getCourceList(HttpServletRequest request) {
		try {
			List<Cource> courceList = courceRepository.findAll();

			return new ResponseModel(courceList, CustomMessages.SUCCESS, CustomMessages.GET_DATA_SUCCESS,
					CustomMessages.SUCCESS, CustomMessages.METHOD_POST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(null, CustomMessages.FAILURE, CustomMessages.GET_DATA_ERROR,
					CustomMessages.FAILURE, CustomMessages.METHOD_GET);
		}
	}
}
