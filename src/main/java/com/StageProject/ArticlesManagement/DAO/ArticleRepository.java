package com.StageProject.ArticlesManagement.DAO;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StageProject.ArticlesManagement.Entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	
	@Query(value = "SELECT * FROM Articles a WHERE a.name like %:keyWord% or a.description like %:keyWord%", nativeQuery = true)
	Collection<Article> findByNameAndDescription(String keyWord);
}
