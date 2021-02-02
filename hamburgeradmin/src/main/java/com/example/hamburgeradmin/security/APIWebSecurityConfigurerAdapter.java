package com.example.hamburgeradmin.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yash Dubey
 * <p>
 * This class handles the Authorization and Authentication of Users using HTTP Basic Auth.
 */
@Configuration
@EnableWebSecurity
public class APIWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @ConfigurationProperties("spring.datasource")
    public DataSource ds() {
        return DataSourceBuilder.create().build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/api/*").permitAll()
                .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select USERNAME, ROLE from USERS where USERNAME=?")
                .usersByUsernameQuery("select USERNAME, PASSWORD, 1 as enabled  from USERS where USERNAME=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}