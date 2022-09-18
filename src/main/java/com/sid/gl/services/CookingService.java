package com.sid.gl.services;

import java.util.List;
import java.util.Optional;

public interface CookingService<T> {
	T save(T entity);
	List<T> findAll();
	Optional<T> findById(Long id);
	

}
