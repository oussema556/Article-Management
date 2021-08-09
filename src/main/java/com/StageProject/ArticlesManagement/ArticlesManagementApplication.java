package com.StageProject.ArticlesManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.StageProject.ArticlesManagement.DAO.CategoryRepository;
import com.StageProject.ArticlesManagement.DAO.UserRepository;
import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.Category;
import com.StageProject.ArticlesManagement.Entity.Role;
import com.StageProject.ArticlesManagement.Entity.Tags;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Service.ArticleService;
import com.StageProject.ArticlesManagement.Service.CategoryService;
import com.StageProject.ArticlesManagement.Service.RoleService;
import com.StageProject.ArticlesManagement.Service.TagsService;
import com.StageProject.ArticlesManagement.Service.UserService;

@SpringBootApplication
public class ArticlesManagementApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ArticlesManagementApplication.class, args);	
	}
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TagsService tagsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryRepository categoryRepository ;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder;

		User AdminUser = new User();
		AdminUser.setUserId(1);
		AdminUser.setEmail("gg@gmail.com");
		AdminUser.setUserName("wess");
		encoder = new BCryptPasswordEncoder();
		AdminUser.setPassword(encoder.encode("wess"));
		Role adminRole = roleService.newRole(new Role(1,"ADMIN"));
		AdminUser.getRoles().add(adminRole);
		userService.insertUser(AdminUser);
		roleService.newRole(new Role(2,"USER"));

		/*User NormalUser = new User();
		NormalUser.setUserId(2);
		NormalUser.setEmail("firas@gmail.com");
		NormalUser.setUserName("firas");
		encoder = new BCryptPasswordEncoder();
		NormalUser.setPassword(encoder.encode("wess"));
		Role userRole = roleService.newRole(new Role(2,"USER"));
		NormalUser.getRoles().add(userRole);
		userService.insertUser(NormalUser);


		Article article1 = new Article();
		article1.setName("last article");
		article1.setDescription("this is number 1");
		article1.setModelNumber("114523");
		article1.setUser(NormalUser);
			List<String> Categories = new ArrayList();
			Categories.add("AI");
			Categories.add("Java");
			Categories.add("Spring");
			Categories.add("Angular");
			Categories.add("Js");
			Category category1= new Category();
			int rndnb = (int) ((Math.random() * (5 - 1)) + 1);
			category1.setCategoryName(Categories.get(rndnb));
			categoryService.insertCategory(category1);
				Tags tag1 = new Tags();
				tag1.setName("AI medecine");
				tag1.setTagsCategory(category1);
				tagsService.insertTags(tag1);
				Tags tag2 = new Tags();
				tag2.setName("AI haja o5ra");
				tag2.setTagsCategory(category1);
				tagsService.insertTags(tag2);
			category1.getTags().add(tag1);
			category1.getTags().add(tag2);
		article1.setArticleCategory(category1);
		articleService.insertArticle(article1);
		category1.getArticles().add(article1);
		categoryService.UpdateCategory(category1);
		article1.setUser(NormalUser);

		NormalUser.getArticles().add(article1);
		userService.UpdateHardUser(NormalUser);


		System.out.println(articleService.findbyNameAndDescription("rt"));*/
	
		
		/*Category c = categoryRepository.findById(1).get();
		System.out.println(c.getArticles().size()+"**********");*/
		
	}

}
