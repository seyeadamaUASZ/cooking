package com.sid.gl.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.gl.models.Recipe;
import com.sid.gl.respositories.RecipeRepository;
import com.sid.gl.services.IRecipe;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeImpl implements IRecipe {
	private RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe entity) {
		// TODO Auto-generated method stub
		return this.recipeRepository.save(entity);
	}

	@Override
	public List<Recipe> findAll() {
		// TODO Auto-generated method stub
		return this.recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		// TODO Auto-generated method stub
		return this.recipeRepository.findById(id);
	}

}
