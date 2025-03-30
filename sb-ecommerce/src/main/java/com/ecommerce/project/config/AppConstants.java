package com.ecommerce.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConstants {

    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "50";
    public static final String SORT_CATEGORIES_BY = "categoryID";
    public static final String SORT_DIR = "asc";
}
