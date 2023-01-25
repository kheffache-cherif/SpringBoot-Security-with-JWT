package com.kheffache.usersjwt.security;

import com.kheffache.usersjwt.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //User user;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //CSRF / CROSS-SITE- REQUEST FORGERY / ON A PAS BESOIN CAR C4EST DES TOKENS
        // SpringBoot de ne pas enregistrer les sessions.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // premiere connection permition à tous
        System.out.println("je suis la avant !!!!!!!!!");
        http.authorizeRequests().antMatchers("/login").permitAll();

        System.out.println("je suis la  apres !!!!!!!!!" );
        //pour les autres connection ----> authentification
     //   http.authorizeRequests().anyRequest().authenticated();
            //  a   jout filtre
        http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
    }
}
