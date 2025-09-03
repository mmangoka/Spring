package com.example.springsection1.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       /* http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount","/myBalance",
                "myCards").authenticated()
                .requestMatchers("/notices","/contact","/error").permitAll());
        http.formLogin(withDefaults());
        /*http.formLogin((flc)->flc.disable());*/
        http.httpBasic(withDefaults());
        /*http.httpBasic((hbc)->hbc.disable());*/
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailService() {
        UserDetails user = User.withUsername("user").
                password("{bcrypt}$2a$12$y1Hy2T4k8t/8TYKW.3RE6uHCU8JVvTm3BsrYJF/outgTFof2cLaru").
                authorities("read").build();
        UserDetails admin = User.withUsername("admin").
                password("{noop}Admin@54321").authorities("admin").build();

        return new InMemoryUserDetailsManager(user,admin);


    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public CompromisedPasswordChecker  compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
