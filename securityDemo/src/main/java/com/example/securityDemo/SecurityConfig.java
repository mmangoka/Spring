package com.example.securityDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity //enable web security features in an application -helps customize
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
        //http.formLogin(Customizer.withDefaults());
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());//basic authentication use http basic authentication with basic default settings
        return (SecurityFilterChain)http.build();
    }


    //configure in memory authentication
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1").
                password("{noop}password1").
                roles("USER").
                build();


        UserDetails admin = User.withUsername("admin").
                password("{noop}adminPass").
                roles("ADMIN").
                build();

        return new InMemoryUserDetailsManager(user1,admin);//implementation of userdetailsmanager
    }
}
