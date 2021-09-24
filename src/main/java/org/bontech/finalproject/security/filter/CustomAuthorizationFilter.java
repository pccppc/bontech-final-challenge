package org.bontech.finalproject.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.bontech.finalproject.model.response.FailureBody;
import org.bontech.finalproject.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class CustomAuthorizationFilter extends OncePerRequestFilter {


    private final Logger logger = Logger.getLogger("authorization-filter");
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        try {
            if (!httpServletRequest.getServletPath().equals("/login")) {
                String accessToken = httpServletRequest.getHeader("Authentication");
                if (accessToken != null) {
                    String username = jwtUtil.extractUserName(accessToken);
                    Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, authorities));
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }else throw new RuntimeException("access_token not found");
            }else filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            logger.warning("Error while authorizing");
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            Map<String, Object> map = Map.of(
                    "code", 403,
                    "message", "authorization failure",
                    "cause", e.getMessage()
            );
            var body = new FailureBody<>(map);
            httpServletResponse.setContentType("application/json");
            try {
                new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), body);
            } catch (IOException ignored) {}

        }
    }
}