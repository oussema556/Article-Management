package com.StageProject.ArticlesManagement.Controller;


import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.Role;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Model.Search;
import com.StageProject.ArticlesManagement.Service.ArticleService;
import com.StageProject.ArticlesManagement.Service.CategoryService;
import com.StageProject.ArticlesManagement.Service.RoleService;
import com.StageProject.ArticlesManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Controller
public class GeneralController {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;




    //LOGIN
    @RequestMapping("/login")
    public String getLoginForm() {
        return "/login";
    }


    //REGISTER
    @RequestMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("User",new User());
        return "/General/register";
    }
    @RequestMapping(value="/insertUserIntoDB", method= RequestMethod.POST)
    public String insertUserIntoDB(Model model, @ModelAttribute("User") User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            Role userRole= roleService.findByRoleType("USER");
            user.setRoles(null);
            Set<Role> roles= new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            userService.insertUser(user);
            model.addAttribute("user",user);

        }
        catch(Exception e) {
            e.printStackTrace();
        }



        return "redirect:/login";
    }


    //  All Articles
    @RequestMapping("/")
    public String getAllArticles(Model model) {
        User user = userService.getLoggedUser();
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("user",user);
        model.addAttribute("articles",articleService.findAllArticles());
        model.addAttribute("search",new Search());
        return "/General/home";
    }

    //SEARCH FOR ARTICLES
    @RequestMapping("/searchArticles")
    public String searchArticlesByKeyword(Model model, @ModelAttribute(name="search") Search search){

        Collection<Article> articlesList= null;

        System.out.println("+++++++++++++++++++"+search.getCategory()+search.getKeyword());

        try {
            if(search.getCategory()==-1){
                articlesList = articleService.findbyNameAndDescription(search.getKeyword());
            }
            else{
                articlesList = articleService.findByCategoryAndKeyword(search.getCategory(), search.getKeyword());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("/////////"+articlesList.size());
        //System.out.println(keyword);
        User user = userService.getLoggedUser();
        model.addAttribute("user",user);
        model.addAttribute("articles",articlesList);
        model.addAttribute("categories",categoryService.findAllCategories());

        return  "/General/home";
    }


    //UPDATE USER
        @RequestMapping("/updateUser")
        public String getUpdateUserForm(Model model){
        User user = userService.getLoggedUser();
        model.addAttribute("user",user);
        model.addAttribute("User",new User());
        return "/General/updateUser";
    }
    @RequestMapping(value="/updateUserIntoDB", method= RequestMethod.POST)
    public String updateUserIntoDB(Model model, @ModelAttribute("User") User user) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            User currentLogedInUser=userService.getLoggedUser();
            user.setUserName(currentLogedInUser.getUserName());
            user.setUserId(currentLogedInUser.getUserId());
            user.setArticles(null);
            user.setArticles(currentLogedInUser.getArticles());
            user.setRoles(null);
            user.setRoles(currentLogedInUser.getRoles());
            user.setEnabled(true);
            userService.updateUser(user);
            model.addAttribute("user",user);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return "redirect:/login";
    }

}
