package com.StageProject.ArticlesManagement.Service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StageProject.ArticlesManagement.DAO.ArticleRepository;
import com.StageProject.ArticlesManagement.Entity.Article;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	public Article insertArticle(Article article) {	
		return articleRepository.save(article);
	}
	
	public Article UpdateArticle(Article article) {	
		return articleRepository.save(article);
	}
	public void deleteArticle(Article article) {	
		articleRepository.delete(article);
	}
	
	public List<Article> findAllArticles(Article article) {	
		return articleRepository.findAll();
	}
	
	public Collection<Article> findbyNameAndDescription(String keyword){
		return articleRepository.findByNameAndDescription(keyword);
	}
}
