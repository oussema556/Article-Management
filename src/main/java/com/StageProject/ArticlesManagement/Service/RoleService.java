package com.StageProject.ArticlesManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StageProject.ArticlesManagement.DAO.RoleRepository;
import com.StageProject.ArticlesManagement.Entity.Role;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	
	public Role newRole(Role r) {
        return roleRepository.save(r);
    }

    public Role findByRoleType(String roleType) {
	    return roleRepository.findByRoleType(roleType);
    }


}
