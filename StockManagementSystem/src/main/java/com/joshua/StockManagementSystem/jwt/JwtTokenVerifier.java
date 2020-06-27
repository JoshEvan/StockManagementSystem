package com.joshua.StockManagementSystem.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

  private Logger logger = LoggerFactory.getLogger(JwtTokenVerifier.class);
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    String authHeader = httpServletRequest.getHeader(jwtConfig.getAuthorizationHeader());
    if (Strings.isNullOrEmpty(authHeader) ||
        !authHeader.startsWith( jwtConfig.getTokenPrefix() )
    ) {

      logger.error("invalid JWT Token");
      filterChain.doFilter(httpServletRequest,httpServletResponse);
      return;
    }

    String token = authHeader.replace("Bearer ","");
    try{

      Jws<Claims> claimsJws = Jwts.parser()
              .setSigningKey(secretKey)
              .parseClaimsJws(token);
      // jws -> hasil compact jwt, kita mau balikin jws jadi jwt lagi

      Claims body = claimsJws.getBody();
      String username = body.getSubject();
      List<Map<String, String>> authorities =
              (List<Map<String,String>>)body.get("authorities");

      Authentication auth = new UsernamePasswordAuthenticationToken(
              username,
              null,
              authorities.stream().map(
                  m -> new SimpleGrantedAuthority(m.get("authority"))
              ).collect(Collectors.toSet())
      );
      SecurityContextHolder.getContext().setAuthentication(auth);
    }catch(JwtException e){
      logger.error("Token "+token+" cannot be trusted (modified or expired)");
      e.printStackTrace();
    }

    // sambungin ke filter selanjutnya, ini penting unutk di tulis, kalo ga ga muncul json nya
    filterChain.doFilter(httpServletRequest,httpServletResponse);
  }
}
