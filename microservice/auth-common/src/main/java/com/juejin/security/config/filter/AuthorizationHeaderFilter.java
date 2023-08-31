package com.juejin.security.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorizationHeaderFilter implements Filter {
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // 先从请求中获取AuthorizationHeader放入上下文中，微服务之间调用时获取使用
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        AuthorizationHeaderHolder.getAuthorizationHeader().setAuthorizationHeader(httpServletRequest.getHeader(AuthorizationHeader.AUTHORIZATION_HEADER));
        
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
