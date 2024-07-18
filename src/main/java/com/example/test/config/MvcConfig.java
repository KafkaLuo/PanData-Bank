/*
 * Add the register function
 * Modified by Luo Jing
 * 
 * Reference: https://github.com/iamtatsuyamori/jjebank/blob/main/src/main/java/com/example/test/MainRunner.java 
 */

package com.example.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");

    }

}
