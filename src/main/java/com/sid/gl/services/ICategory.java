package com.sid.gl.services;

import com.sid.gl.models.Category;

public interface ICategory extends CookingService<Category>{
	Category findCategoryByName(String name);
}
