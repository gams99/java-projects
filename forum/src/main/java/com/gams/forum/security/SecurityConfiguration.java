package com.gams.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenServices tokenService;

    @Override //manual authentication
    @Bean //to spring use
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Config. de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Config. de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/topicos").permitAll() //free only get requests
        .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFIlter(tokenService), UsernamePasswordAuthenticationFilter.class); // filter register
    }

    //config. de recursos estasticos(js, css, img, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    // encode password
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//    }
}
