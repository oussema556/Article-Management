package com.StageProject.ArticlesManagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "wess";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);//$2a$10$9wAU/MN2P/lidE55aXd14u98CxHT.sEVfDxH0aVXfpcGhA/5zDImC
	 }

}