package com.StageProject.ArticlesManagement.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StageProject.ArticlesManagement.Entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{


    Role findByRoleType(String roleType);
}
