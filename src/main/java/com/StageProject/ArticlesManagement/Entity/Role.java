package com.StageProject.ArticlesManagement.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String roleType;

	@ManyToMany(cascade = CascadeType.ALL , fetch =FetchType.LAZY , mappedBy = "roles" )
    private Set<User> users = new HashSet<>();
	
	public Role() {
		super();
	}
	
	
	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Role(int id, String roleType) {
		super();
		this.id = id;
		this.roleType = roleType;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRoleType() {
		return roleType;
	}


	public void setRoleType(String roleType) {
		roleType = roleType;
	}
	

}
