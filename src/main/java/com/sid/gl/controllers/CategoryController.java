package com.sid.gl.controllers;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.gl.models.Category;
import com.sid.gl.services.ICategory;
import com.sid.gl.tools.BasicResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/category")
@AllArgsConstructor
public class CategoryController {
	
	private ICategory iCategory;
	
	@PostMapping
	public ResponseEntity<BasicResponse> addCategory(@RequestBody Category category){
		BasicResponse basicResponse = new BasicResponse();
		Category category2 = iCategory.save(category);
		if(Objects.isNull(category2)) {
			basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			basicResponse.setMessageDebug("category non saved");
			basicResponse.setObject(null);
		}else {
			basicResponse.setHttpStatus(HttpStatus.OK);
			basicResponse.setMessageDebug("category saved successfully");
			basicResponse.setObject(category2);
		}
		return ResponseEntity.ok(basicResponse);
	}
	
	

}
