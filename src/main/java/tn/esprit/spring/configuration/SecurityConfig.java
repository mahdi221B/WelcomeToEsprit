package tn.esprit.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;/*
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;*/

import java.security.Security;

@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public class SecurityConfig  {
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests().antMatchers("/","/classroom/**","/ws/**","/block/**","/equipement/**","/faq/**","/profil/**")
                .permitAll().anyRequest().authenticated().and()
                .formLogin().and()
                .logout(logout->logout.logoutSuccessUrl("/"));
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests()
                .antMatchers("/classroom/**","/","/ws/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout(logout->logout.logoutSuccessUrl("/"));
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails teacher= User.withDefaultPasswordEncoder()
                .username("teacher")
                .password("teacher")
                .roles("TEACHER")
                .build();
        UserDetails student= User.withDefaultPasswordEncoder()
                .username("student")
                .password("student")
                .roles("STUDENT")
                .build();
        return new InMemoryUserDetailsManager(teacher,student);
    }*/

}
