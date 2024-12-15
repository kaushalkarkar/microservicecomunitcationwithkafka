package com.example.Dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


@Data
@JsonInclude(value = Include.NON_NULL)
public class CourceDto {

	
	private Long id;
	private String name;
	private String image;
	private String price;
	private String description;
}
