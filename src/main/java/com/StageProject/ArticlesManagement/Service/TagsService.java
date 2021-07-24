package com.StageProject.ArticlesManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.StageProject.ArticlesManagement.DAO.TagsRepository;
import com.StageProject.ArticlesManagement.Entity.Tags;

@Service
public class TagsService {
	
	@Autowired
	TagsRepository tagsRepository;
	
	public Tags insertTags(Tags tag) {
		return tagsRepository.save(tag);
	}
	
	public List<Tags> findAllTags(){
		
		return  tagsRepository.findAll();
	}

	public Tags UpdateTags(Tags tag) {	
		return tagsRepository.save(tag);
	}
	
	public void deleteTags(Tags tag) {	
		tagsRepository.delete(tag);
	}
	

}
