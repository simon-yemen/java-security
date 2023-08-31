package com.juejin.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * AuthenticationManager 认证管理器，管理认证流程，这里获取默认的认证管理器
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * UserDetailsService 用于加载用户详情
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    /*
        这段代码的作用是创建一个基于内存的用户认证系统，
        其中包含两个用户："user"和"admin"，分别具有不同的用户名、密码和角色。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().
                withUser("user").password("{noop}password1").roles("USER")
                .and()
                .withUser("admin").password("{noop}password2").roles("USER", "ADMIN");
    }
}
