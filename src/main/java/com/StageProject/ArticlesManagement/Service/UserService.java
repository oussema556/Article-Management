package com.StageProject.ArticlesManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StageProject.ArticlesManagement.DAO.ArticleRepository;
import com.StageProject.ArticlesManagement.DAO.UserRepository;
import com.StageProject.ArticlesManagement.Entity.Article;
import com.StageProject.ArticlesManagement.Entity.User;
import com.StageProject.ArticlesManagement.Security.MyUserDetails;

@Service
public class UserService{
	
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		
//		Optional<User> user = userRepository.findByUserName(userName);
//		return new MyUserDetails(user);
//	}
//	
	@Autowired
	UserRepository userRepository;
	
	
	//CURRENT LOGED IN USER
    public User getLoggedUser() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName(); 

        System.out.println("$$ user name = " + username);
        User user = new User() ;
        try {
            user = userRepository.findByUserName(username).get();
        }catch(Exception e) {
            e.printStackTrace();
        }

       return user;

    }
	
	public User insertUser(User user) {	
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers() {	
		return userRepository.findAll();
	}
	
	public User UpdateHardUser(User user) {	
		return userRepository.save(user);
	}

	public void deleteUser(User user) {	
		userRepository.delete(user);
	}


    public User updateUser(User user) {
		return userRepository.save(user);
    }
}
