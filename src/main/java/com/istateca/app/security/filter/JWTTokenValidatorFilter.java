package com.istateca.app.security.filter;

import com.istateca.app.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;


public class JWTTokenValidatorFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(SecurityConstants.JWT_HEADER);

        /*String URL = request.getRequestURL().toString();
        System.out.println("URL Request JWT:" + URL);

        String URLAdr = request.getRemoteAddr();
        System.out.println("URL Remote JWT Addr:" + URLAdr);
        String URLHost = request.getRemoteAddr();
        System.out.println("URL Remote JWT Host:" + URLHost);
        String var = request.getHeader("origin");
        System.out.println("URL JWT Origen:" + var);

        Enumeration<String> headerNames = request.getHeaderNames();
        String headName="";

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                headName = headerNames.nextElement();
                System.out.println( headName + ": " + request.getHeader(headName));
            }
        }*/

            if (null != jwt) {
                try {
                    SecretKey key = Keys.hmacShaKeyFor(
                            SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = (String) claims.get("authorities");
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
                    throw new BadCredentialsException("Invalid Token received!");
                }

            }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/ingresar");
    }

}
