package com.example.gateway.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.gateway.util.JwtTokenUtils;
import com.example.gateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
        	ServerHttpRequest request = null;
        	
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
            	System.out.println("in filter");
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                	String username = jwtTokenUtils.getUsername(authHeader);
                	List<String> roles = jwtTokenUtils.getRoles(authHeader);
                	request = exchange.getRequest()
                			.mutate()
                			.header("username",username)
                			.header("role", roles.get(0))
                			.build();
                } catch (ExpiredJwtException e) {
                	System.out.println("Token expired");
                } catch (SignatureException e) {
                	System.out.println("Incorrent sign");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}