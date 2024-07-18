/*
 * Reference: https://github.com/iamtatsuyamori/jjebank/blob/main/src/main/java/com/example/test/
 */

package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**", "/register","/account/","/account/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )

                .logout((logout) -> logout.permitAll());

        return http.build();
    }

//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 =
//                User.withDefaultPasswordEncoder()
//                        .username("m1")
//                        .password("m1")
//                        .roles("USER")
//                        .build();
//
//        UserDetails user2 =
//                User.withDefaultPasswordEncoder()
//                        .username("m2")
//                        .password("m2")
//                        .roles("USER")
//                        .build();
//
//        UserDetails user3 =
//                User.withDefaultPasswordEncoder()
//                        .username("p1")
//                        .password("p1")
//                        .roles("USER")
//                        .build();
//
//        UserDetails user4 =
//                User.withDefaultPasswordEncoder()
//                        .username("p2")
//                        .password("p2")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
//    }
}
