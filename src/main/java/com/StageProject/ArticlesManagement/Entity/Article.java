package com.StageProject.ArticlesManagement.Entity;




import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;


import com.sun.istack.NotNull;

@Entity
@Table(name="Articles")
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int articleId;
	@NotNull
	private String name;
	@NotNull
	private String modelNumber;
	@NotNull
	private String description;
	@NotNull
	private int pageNumber;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category articleCategory;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(Category articleCategory) {
		this.articleCategory = articleCategory;
	}

	public Article() {}
	

	
	
	public Article(int articleId, String name, String modelNumber, String description, int pageNumber, User user,
			Category articleCategory) {
		super();
		this.articleId = articleId;
		this.name = name;
		this.modelNumber = modelNumber;
		this.description = description;
		this.pageNumber = pageNumber;
		this.user = user;
		this.articleCategory = articleCategory;
	}

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}
