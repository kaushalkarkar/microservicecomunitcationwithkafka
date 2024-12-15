package com.example.demo1.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "course", url = "${app.feign.course.service.url}")
public interface CourseFeign {

	
	@GetMapping(path = "/mainCourse/getCourceList")
	public String getCourceList();
	
}
