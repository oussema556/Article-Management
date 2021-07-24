package com.StageProject.ArticlesManagement.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StageProject.ArticlesManagement.Entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer>{

}
