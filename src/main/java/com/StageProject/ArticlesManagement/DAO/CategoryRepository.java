package com.StageProject.ArticlesManagement.DAO;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.StageProject.ArticlesManagement.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
