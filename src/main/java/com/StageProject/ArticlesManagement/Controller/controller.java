package com.StageProject.ArticlesManagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StageProject.ArticlesManagement.Entity.Article;
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
 	
	@RequestMapping("/login")
	public String getLoginForm() {
		return "login";
	}
	
	@RequestMapping("/")
	public String homepage() {
		return "home";
	}
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
			System.out.println(article.getArticleCategory()+"$$$$$$$");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
	
	
}
