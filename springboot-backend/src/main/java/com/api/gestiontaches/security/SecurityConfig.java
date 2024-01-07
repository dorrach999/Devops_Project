package com.api.gestiontaches.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.httpBasic(Customizer.withDefaults());
        //security.formLogin(form ->form.permitAll());
        security.authorizeHttpRequests(authorize -> authorize.requestMatchers("/user/**").hasAuthority("USER"));
        security.authorizeHttpRequests(authorize -> authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        //security.exceptionHandling(authorize->authorize.accessDeniedPage("/errorPage"));
        //security.authorizeHttpRequests(authorise->authorise.anyRequest().authenticated());
        security.authorizeHttpRequests(authorise->authorise.anyRequest().permitAll());
        security.userDetailsService(userDetailsService);
        security.csrf(c->c.disable());

        return security.build();
    }
}
