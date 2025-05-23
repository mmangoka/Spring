package com.example.securityDemo.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.debug("AuthTokenFiler called for URI:{}",request.getRequestURI());

        try{

            //extract a token
            String jwt = parseJwt(request);

            if(jwt != null && jwtUtils.validateToken(jwt)){
                String username = jwtUtils.getUserNameFromJWTToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //create an authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());//set roles and permissions required

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.debug("Roles from JWT:{}",userDetails.getAuthorities());
            }

        }catch(Exception e){
                logger.error("Cannot set user authentication:{}",e);
        }

        filterChain.doFilter(request,response);

    }

    private String parseJwt(HttpServletRequest request) {

        String jwt = jwtUtils.JWTfRomHeader(request);
        logger.debug("AuthTokenFilter.java:{}",jwt);

        return jwt;
    }
}
