package com.StageProject.ArticlesManagement.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.StageProject.ArticlesManagement.DAO.UserRepository;
import com.StageProject.ArticlesManagement.Entity.User;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUserName(username);

        if(user == null ) {
            throw new UsernameNotFoundException("Could not find user : " +username);
        }
        return user.map(MyUserDetails::new).get();
    }
	
}
