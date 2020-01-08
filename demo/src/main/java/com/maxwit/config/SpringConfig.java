package com.maxwit.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration

public class SpringConfig extends WebSecurityConfigurerAdapter {

    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                
                .withUser("admin").password("{noop}adim").roles("admin", "user")
                .and()

                .withUser("kalin").password("{noop}kalin").roles("user");
                
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                
                .antMatchers("/css/**", "/img/**", "/js/**", "index.html", "favicon.ico")

                .permitAll().and().formLogin().loginPage("/index.html").loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                            Authentication auth) throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }

                }).failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                            AuthenticationException e) throws IOException, ServletException {
                                resp.setContentType("application/json;charset=utf-8");
                                PrintWriter out = resp.getWriter();
                                resp.setStatus(401);
                                Map<String, Object> map = new HashMap<>();
                                map.put("status",401);
                                if (e instanceof LockedException) {
                                    map.put("msg","账户被锁定， 登录失败 ！");
                                }else if (e instanceof BadCredentialsException) {
                                    map.put("msg","账户名或密码输入错误， 登录失败!");
                                } else if(e instanceof DisabledException) {
                                    map.put("msg","账户被禁用，登录失败");
                                }else if(e instanceof CredentialsExpiredException) {
                                    map.put("msg","密码已过期，登录失败");
                                }
                                ObjectMapper om = new ObjectMapper();
                                out.write(om.writeValueAsString(map));
                                out.flush();
                                out.close();
                        

                    }
            
        })
        .permitAll()
        .and()
        .csrf()
        .disable();
    }
}
