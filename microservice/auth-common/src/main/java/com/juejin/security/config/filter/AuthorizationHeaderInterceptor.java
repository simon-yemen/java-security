package com.juejin.security.config.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * template的拦截器，需要设置template.setInterceptors(interceptors);
 */
public class AuthorizationHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(AuthorizationHeader.AUTHORIZATION_HEADER, AuthorizationHeaderHolder.getAuthorizationHeader().getAuthorizationHeader());

        return execution.execute(request, body);
    }
}