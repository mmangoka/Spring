package com.ecommerce.project.com.ecommerce.project.Config;

import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SslConfig {
    @Bean
    public SslBundles sslBundles() {
        return null; // Prevents automatic SSL configuration
    }
}