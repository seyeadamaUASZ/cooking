package com.sid.gl.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sid.gl.exceptions.FileStorageException;
import com.sid.gl.models.FileCooking;
import com.sid.gl.models.Recipe;
import com.sid.gl.properties.FileStorageProperties;
import com.sid.gl.respositories.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageService {
	private final Path fileStorageLocation;
	
	@Autowired
	private  RecipeRepository recipeRepository;
	
    
	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
		 try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }

	}
	
	public FileCooking saveFile(MultipartFile file,Long id) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileDownloadUri="";
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/recipe/downloadImageUri/")
					.path(fileName)
					.toUriString();
			FileCooking fileCooking = new FileCooking(fileName, file.getContentType(), fileDownloadUri);
			
			//check Recipe with id
			Optional<Recipe> recipe = this.recipeRepository.findById(id);
			if(recipe.isPresent()) {
				Recipe recip = recipe.get();
				recip.setFileCooking(fileCooking);
				//update
				this.recipeRepository.save(recip);
				return fileCooking;
			}
		}catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);

	}
    
	return null;
	
 
}
	
	
}