package com.gams.forum.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Config. de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    //Config. de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/topicos").permitAll() //free only get requests
        .antMatchers(HttpMethod.GET, "/topicos/*").permitAll();
    }

    //config. de recursos estasticos(js, css, img, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
