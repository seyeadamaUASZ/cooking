package com.sid.gl.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sid.gl.exceptions.UploadException;
import com.sid.gl.models.FileCooking;
import com.sid.gl.models.Recipe;
import com.sid.gl.services.IRecipe;
import com.sid.gl.tools.BasicResponse;
import com.sid.gl.tools.FileStorageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/recipe")
@AllArgsConstructor
public class RecipeController {
	private IRecipe iRecipe;
	private FileStorageService fileStorageService;
	
	@PostMapping
	public ResponseEntity<BasicResponse> saveRecipe(@RequestBody Recipe recipe){
		BasicResponse basicResponse = new BasicResponse();
		Recipe recipe2 = this.iRecipe.save(recipe);
		if(Objects.isNull(recipe2)) {
			basicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			basicResponse.setMessageDebug("Recipe non saved");
			basicResponse.setObject(null);
		}else {
			basicResponse.setHttpStatus(HttpStatus.OK);
			basicResponse.setMessageDebug("Recipe saved !!");
			basicResponse.setObject(recipe2);
		}
		return ResponseEntity.ok(basicResponse);
	}
	
	@PostMapping("/upload/{id}")
	@ResponseBody
	public FileSystemResource uploadFileRecipe(@RequestParam("file") MultipartFile file,@PathVariable("id")Long id){
		
		FileCooking fileCooking = this.fileStorageService.saveFile(file, id);
		if(Objects.isNull(fileCooking)) {
			throw new UploadException("Cannot upload image file");
		}else {
			return new FileSystemResource(new File("./uploads/"+fileCooking.getFileName()));	
		}
		
	}
	
	@GetMapping(value="/downloadImageUri/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public FileSystemResource getimfileimage(@PathVariable("fileName") String fileName) throws IOException{
	     //Produits produits=iProduit.findProduitByFileName(fileName);
	     //System.out.println("produit "+produits.getFileName());
	     return new FileSystemResource(new File("./uploads/"+fileName));
	}
	
	@GetMapping
	public ResponseEntity<BasicResponse> listRecipe(){
		BasicResponse basicResponse = new BasicResponse();
		List<Recipe> recipes = this.iRecipe.findAll();
		if(recipes.isEmpty()) {
			basicResponse.setHttpStatus(HttpStatus.OK);
			basicResponse.setMessageDebug("recipe is empty");
			basicResponse.setObject(null);
		}else {
			basicResponse.setHttpStatus(HttpStatus.OK);
			basicResponse.setMessageDebug("recipe loaded !!");
			basicResponse.setObject(recipes);
		}
		return ResponseEntity.ok(basicResponse);
		
		
	}


}
