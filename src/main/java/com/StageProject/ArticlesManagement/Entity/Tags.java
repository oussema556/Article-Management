package com.StageProject.ArticlesManagement.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="tags")
public class Tags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tagId;
	
	@NonNull
	private String name;

	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category tagsCategory;

	public int getTagId() {
		return tagId;
	}

	public Tags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tags(int tagId, String name, Category tagsCategory) {
		super();
		this.tagId = tagId;
		this.name = name;
		this.tagsCategory = tagsCategory;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getTagsCategory() {
		return tagsCategory;
	}

	public void setTagsCategory(Category tagsCategory) {
		this.tagsCategory = tagsCategory;
	}
	

}
