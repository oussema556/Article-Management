package com.StageProject.ArticlesManagement.Controller;

import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Service.ArticleService;
import com.StageProject.ArticlesManagement.Service.CategoryService;
import com.StageProject.ArticlesManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class UserController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    //CREATE ARTICLE
    @RequestMapping("User/createArticle")
    public String CreateArticle(Model model) {
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("Article",new Article());
        return "User/createArticle";
    }
    @RequestMapping(value="User/insertArticleIntoDB", method= RequestMethod.POST)
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


        return "redirect:/User/userArticles";
    }

    // GET USER ARTICLES
    @RequestMapping("User/userArticles")
    public String userArticles(Model model) {
        try {
            User currentLogedInUser=userService.getLoggedUser();
            model.addAttribute("articles",currentLogedInUser.getArticles());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "User/myArticles";
    }


    //SEARCH FOR ARTICLES
    @RequestMapping("User/searchMyArticles")
    public String searchMyArticlesByKeyword(Model model, @ModelAttribute(name="keyword") String keyword){
        Collection<Article> articlesList= null;
        try {
            //Search with java code for articles by keyword
            User currentLogedInUser=userService.getLoggedUser();
            articlesList = articleService.findbyNameAndDescription(keyword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("/////////"+articlesList.size());
        //System.out.println(keyword);
        model.addAttribute("articles",articlesList);
        return  "User/myArticles";
    }

    @RequestMapping("/User/UpdateArticle/{articleId}")
    public String updateArticle(Model model, @PathVariable(name="articleId") int id){
        User currentLogedInUser=userService.getLoggedUser();
        Article article= articleService.findById(id);

    if(currentLogedInUser.getUserId()==article.getUser().getUserId()){
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("Article",article);
        return "/User/updateArticle";
    }
    return "403";
    }

    @RequestMapping(value="/User/updateArticleIntoDB/{articleId}", method= RequestMethod.POST)
    public String updateArticleIntoDB(Model model, @PathVariable(name="articleId") int id, @ModelAttribute(name = "Article") Article article){
        User currentLogedInUser=userService.getLoggedUser();
        Article testarticle= articleService.findById(id);
        if(currentLogedInUser.getUserId()==testarticle.getUser().getUserId()){
            article.setArticleId(id);
            article.setUser(currentLogedInUser);
            articleService.updateArticle(article);
            return "redirect:/User/userArticles";
        }
    return "403";
    }

}
