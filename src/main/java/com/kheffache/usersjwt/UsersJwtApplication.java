package com.kheffache.usersjwt;

import com.kheffache.usersjwt.entities.Role;
import com.kheffache.usersjwt.entities.User;
import com.kheffache.usersjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UsersJwtApplication {
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UsersJwtApplication.class, args);
	}
	/*
	@PostConstruct
	void init_users() {
//ajouter les rôles
		userService.addRole(new Role(null,"ADMIN"));
		userService.addRole(new Role(null,"USER"));
//ajouter les users
		userService.saveUser(new User(null,"admin","123",true,null));
		userService.saveUser(new User(null,"Ronaldo","123",true,null));
		userService.saveUser(new User(null,"Messi","123",true,null));
//ajouter les rôles aux users
		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("admin", "USER");
		userService.addRoleToUser("Ronaldo", "USER");
		userService.addRoleToUser("Messi", "USER");
	}*/
	@Bean // creation d'un Bean singlTon un objet pour tout le projet
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}

}
