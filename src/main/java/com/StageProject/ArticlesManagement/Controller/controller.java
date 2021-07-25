package com.StageProject.ArticlesManagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.Category;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Service.ArticleService;
import com.StageProject.ArticlesManagement.Service.CategoryService;
import com.StageProject.ArticlesManagement.Service.UserService;

@Controller
public class controller {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
 	

	@RequestMapping("/")
	public String homepage() {
		return "home";
	}
	
	
	//LOGIN
	@RequestMapping("/login")
	public String getLoginForm() {
		return "login";
	}
	
	
	//REGISTER
	@RequestMapping("/register")
	public String getRegisterForm(Model model) {
		model.addAttribute("User",new User());
		return "register";
	}
	@RequestMapping(value="/insertUserIntoDB", method=RequestMethod.POST)
	public String insertUserIntoDB(Model model, @ModelAttribute("User") User user) {
		try {
			userService.insertUser(user);
			model.addAttribute("user",user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/login";
	}
	

	//CREATE ARTICLE
	@RequestMapping("/createArticle")
	public String CreateArticle(Model model) {
		model.addAttribute("categories",categoryService.findAllCategories());
		model.addAttribute("Article",new Article());
		return "createArticle";
	}
	
	@RequestMapping(value="/insertArticleIntoDB", method=RequestMethod.POST)
	public String insertArticleIntoDB(Model model, @ModelAttribute("Article") Article article) {
		try {
			User currentLogedInUser=userService.getLoggedUser();
			article.setUser(currentLogedInUser);
			articleService.insertArticle(article);
			model.addAttribute("article",article);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
	// ADD CATEGORY
	@RequestMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("Category", new Category());
		return "addCategory";
	}
	
	@RequestMapping(value="/addCateogryIntoDB", method=RequestMethod.POST)
	public String addCateogryIntoDB(Model model, @ModelAttribute("Category") Category category) {
		try {
			categoryService.insertCategory(category);
			model.addAttribute("category",category);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminDashboard";
	}
	
	
	
	//  All Articles
	@RequestMapping("/adminDashboardArticles")
	public String adminDashboardArticles(Model model) {
		model.addAttribute("articles",articleService.findAllArticles());
		return "adminDashboardArticles";
	}
	
	// All Users
	@RequestMapping("/adminDashboardUsers")
	public String adminDashboardUsers(Model model) {
		model.addAttribute("users",userService.findAllUsers());
		return "adminDashboardUsers";
	}
	
	
	// GET USER ARTICLES
	@RequestMapping("/userArticles")
	public String userArticles(Model model) {
		try {
			User currentLogedInUser=userService.getLoggedUser();
			model.addAttribute("articles",currentLogedInUser.getArticles());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "userAllArticles";
	}
	
	
	
	
	
	/*@RequestMapping("/updateAccount")
	public String getUpdateAccountForm(Model model) {
		model.addAttribute("User",new User());
		return "updateUser";
	}
	@RequestMapping(value="/updateUserIntoDB", method=RequestMethod.POST)
	public String	updateUserIntoDB(Model model, @ModelAttribute("User") User user) {
		try {
			int currentLogedInUserId=userService.getLoggedUser().getUserId();
			System.out.println(currentLogedInUserId);
			userService.UpdateUser(user,currentLogedInUserId);
			model.addAttribute("article",user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/home";
	}*/
	
	
	
}
