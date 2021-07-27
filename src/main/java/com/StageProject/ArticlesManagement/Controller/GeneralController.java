package com.StageProject.ArticlesManagement.Controller;


import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.Role;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Service.ArticleService;
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


    @RequestMapping("/")
    public String homepage() {
        return "/General/home";
    }


    //LOGIN
    @RequestMapping("/login")
    public String getLoginForm() {
        return "/General/login";
    }


    //REGISTER
    @RequestMapping("/General/register")
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
    @RequestMapping("/getAllArticles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles",articleService.findAllArticles());
        return "/General/allArticles";
    }


    //SEARCH FOR ARTICLES
    @RequestMapping("/searchArticles")
    public String searchArticlesByKeyword(Model model, @ModelAttribute(name="keyword") String keyword){
        Collection<Article> articlesList= null;
        try {
            articlesList = articleService.findbyNameAndDescription(keyword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("/////////"+articlesList.size());
        //System.out.println(keyword);
        model.addAttribute("articles",articlesList);
        return  "/General/allArticles";
    }


    //UPDATE USER
        @RequestMapping("/updateUser")
    public String getUpdateUserForm(Model model){
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
