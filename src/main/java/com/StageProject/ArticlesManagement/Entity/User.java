package com.StageProject.ArticlesManagement.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@NotNull
	@Column(length=12)
	private String userName;
	@NotNull
	@Column(length=50)
	private String email;
	@NotNull
	@Column(length=64)
	private String 	password;
	
	@NotNull
	private boolean enabled=true;
	
	@OneToMany(mappedBy="user")
	private List<Article> Articles = new ArrayList<Article>();
	
    @ManyToMany(cascade = CascadeType.ALL , fetch =FetchType.EAGER)
    @JoinTable(name="users_roles" , joinColumns = @JoinColumn(name="user_id") , inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
	
	public Set<Role> getRoles() {
		return roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(int userId, String userName, String email, String password, List<Article> articles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		
		Articles = articles;
	}

	public User() {
		
	}
	

	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
