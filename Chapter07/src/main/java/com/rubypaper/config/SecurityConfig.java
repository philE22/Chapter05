package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BoardUserDetailsService boardUserDetailsService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
//        security.authorizeRequests().antMatchers("/").permitAll();
//        security.authorizeRequests().antMatchers("/member/**").authenticated();
//        security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
//        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        security.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN");

        security.csrf().disable();//RESTfull 사용하기 위해서는 csrf(크로스 사이트 위조 요청)기능을 비활성화해야 함
        security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
        security.exceptionHandling().accessDeniedPage("/accessDenied");
        security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");

        security.userDetailsService(boardUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
