package com.StageProject.ArticlesManagement.Controller;

import com.StageProject.ArticlesManagement.Entity.Category;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Model.Search;
import com.StageProject.ArticlesManagement.Service.ArticleService;
import com.StageProject.ArticlesManagement.Service.CategoryService;
import com.StageProject.ArticlesManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    // All Users
    @RequestMapping("Admin/adminDashboardallUsers")
    public String adminDashboardUsers(Model model) {
        User user = userService.getLoggedUser();
        model.addAttribute("user",user);
        model.addAttribute("users",userService.findAllUsers());
        return "Admin/adminDashboardUsers";
    }

    // All Articles
    @RequestMapping("Admin/adminDashboardArticles")
    public String adminDashboardArticles(Model model){
        User user = userService.getLoggedUser();
        model.addAttribute("user",user);
        model.addAttribute("articles",articleService.findAllArticles());
        model.addAttribute("search", new Search());
        return "Admin/adminDashboardArticles";
    }

    @RequestMapping("Admin/categories")
    public String allCategories(Model model){
        User user = userService.getLoggedUser();
        model.addAttribute("user",user);
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("Category",new Category());
        return "Admin/categories";
    }
    @RequestMapping(value="/Admin/addCateogryIntoDB", method=RequestMethod.POST)
    public String addCateogryIntoDB(Model model, @ModelAttribute("Category") Category category) {


        try {

            categoryService.insertCategory(category);

            model.addAttribute("categories",categoryService.findAllCategories());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/Admin/categories";
    }
}
