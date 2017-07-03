package com.tatvacoconet.config;

/**
 * Created by pca48 on 6/29/2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bhavin").password("bhavin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("admin").password("bhavin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers( "/","/Welcome/**").access("hasRole('ROLE_ADMIN')")

                /*.antMatchers("/index*//**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")*/
                .and().formLogin().loginPage("/Login")
               /* .usernameParameter("bhavin").passwordParameter("bhavin")*/
                .and().csrf().disable();

    }
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }*/
}