package com.juejin.security.mfa.config;

import com.juejin.security.mfa.authentication.filter.MfaFilter;
import com.juejin.security.mfa.authentication.provider.SecurityCodeAuthenticationProvider;
import com.juejin.security.mfa.authentication.provider.UserCredentialAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MfaFilter mfaFilter;

    @Autowired
    private SecurityCodeAuthenticationProvider securityCodeAuthenticationProvider;

    @Autowired
    private UserCredentialAuthenticationProvider userCredentialAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(securityCodeAuthenticationProvider)
            .authenticationProvider(userCredentialAuthenticationProvider);
    }


    /*
        这段代码是使用Spring Security框架配置HTTP安全认证的示例。它包含以下步骤：
        1. 禁用CSRF（跨站请求伪造）保护，即在HTTP请求中添加一个随机值作为参数以防止攻击者伪造请求。
        2. 将自定义的MFA（多因素身份验证）过滤器添加到HTTP安全认证链中，该过滤器用于处理用户的身份验证和授权。
        3. 启用所有请求都需要经过身份验证的安全策略
     */

    /*
        在Spring Security中，如果在同一个位置注册多个过滤器，它们的顺序是不确定的。具体来说，在同一个位置注册多个过滤器并不会覆盖现有的过滤器。
        相反，如果您不希望使用某个过滤器，则不要将其注册到Spring Security配置中 。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.addFilterAt(
        		mfaFilter,
                BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
