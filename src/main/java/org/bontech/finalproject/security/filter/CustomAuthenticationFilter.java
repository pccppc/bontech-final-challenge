package org.bontech.finalproject.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bontech.finalproject.exception.AuthException;
import org.bontech.finalproject.model.dto.LoginForm;
import org.bontech.finalproject.model.response.SuccessBody;
import org.bontech.finalproject.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public CustomAuthenticationFilter(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            var form = new ObjectMapper().readValue(request.getInputStream().readAllBytes(),
                    LoginForm.class);
            var authToken =
                    new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
            return authManager.authenticate(authToken);
        } catch (IOException bodyException) {
            throw new AuthException("authentication failed");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new SuccessBody<>(Map.of("access_token",token)));
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authManager);
    }
}