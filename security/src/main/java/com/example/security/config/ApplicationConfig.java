package com.example.security.config;

import com.example.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUserEmail(username).
                orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

        }

        @Bean
      public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authProvider =  new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
      }

      @Bean
      public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
      }

      @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception{
        return config.getAuthenticationManager();
    }
}

