package com.StageProject.ArticlesManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StageProject.ArticlesManagement.DAO.CategoryRepository;
import com.StageProject.ArticlesManagement.Entity.Category;

@Service
public class CategoryService {

	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category insertCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> findAllCategories(){
		
		return  categoryRepository.findAll();
	}

	public Category UpdateCategory(Category category) {	
		return categoryRepository.save(category);
	}
	
	public void deleteCategory(Category category) {	
		categoryRepository.delete(category);
	}
	
}
