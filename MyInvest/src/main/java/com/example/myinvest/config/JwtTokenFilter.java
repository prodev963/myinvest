package com.example.myinvest.config;
import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.entity.UserRole;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtTokenFilter extends OncePerRequestFilter  {

    private final UserService userService;

    @Autowired
    public JwtTokenFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String headerToken = httpServletRequest.getHeader("Authorization");

        if (headerToken == null){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        String token = headerToken.replace("Bearer ", "");
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("rWHdVPWbqsBDDcQs0PdArBwcZLkH5yykj3l4I")
                    .parseClaimsJws(token).getBody();

            Date expirationDate = claims.getExpiration();

            if (expirationDate.getTime() <= System.currentTimeMillis()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            String username = claims.getSubject();
            UserEntity userEntity = (UserEntity) userService.loadUserByUsername(username);

            if (!userEntity.isEnabled()) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            userEntity.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }catch (ExpiredJwtException e){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new ApiResponse("token vaqti o'tib ketdi",false,401)));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}

