package com.StageProject.ArticlesManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        try {
            authProvider.setPasswordEncoder(passwordEncoder());
            authProvider.setUserDetailsService(userDetailsService());
        }catch(Exception e) {
            System.out.println("die :"+e.getMessage());
        }
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        try {
            auth.authenticationProvider(authenticationProvider());
        }catch(Exception e) {
            System.out.println("die :"+e.getMessage());
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.authorizeRequests()
            .antMatchers("/createArticle").hasAuthority("USER")
            .antMatchers("/updateAccount").hasAuthority("USER")
            .antMatchers("/userArticles").hasAuthority("USER")
            .antMatchers("/").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll();
        }catch(Exception e) {
            System.out.println("die :"+e.getMessage());
        }
    }
}
