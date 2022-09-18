package com.sid.gl.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.gl.models.Category;
import com.sid.gl.respositories.CategoryRepository;
import com.sid.gl.services.ICategory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryImpl implements ICategory{
	private CategoryRepository repository;
	

	@Override
	public Category save(Category entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Category findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByName(name);
	}

}
