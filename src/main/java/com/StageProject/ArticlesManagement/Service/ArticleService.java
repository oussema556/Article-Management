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
	
	public List<Article> findAllArticles() {	
		return articleRepository.findAll();
	}
	
	public Collection<Article> findbyNameAndDescription(String keyword) throws Exception{
		Collection<Article> articles= articleRepository.findByNameAndDescription(keyword);
		if (articles==null){
			throw new Exception();
		}
		return articles;
	}

    public Article findById(int id) {
		return articleRepository.getById(id);
    }

	public Article updateArticle(Article article) {
		return articleRepository.save(article);
	}

	public Collection<Article> findByCategoryAndKeyword(int category, String keyword){
		return articleRepository.findByCategoryAndKeyword(category,keyword);
	}

	public Collection<Article> findByCategory(int category) {
		return articleRepository.findByCategory(category);
	}
}
