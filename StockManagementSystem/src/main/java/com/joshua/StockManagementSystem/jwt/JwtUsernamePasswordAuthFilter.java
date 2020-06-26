package com.joshua.StockManagementSystem.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UsernamePasswordAuthRequestPayload authRequest = new ObjectMapper()
      .readValue(request.getInputStream(), UsernamePasswordAuthRequestPayload.class);

      Authentication authentication = new UsernamePasswordAuthenticationToken(
              authRequest.getUsername(), // principal
              authRequest.getPassword() // credential
      );

      // cek user exists? username , password correct?
      Authentication auth = authenticationManager.authenticate(authentication);
      return auth;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return super.attemptAuthentication(request, response);
  }

  /**
   * invoked when succesfuly authenticated, create token
   * @param request
   * @param response
   * @param chain
   * @param authResult
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    // generate token
    String secretKey = "123456789012345678901234567890123456789012345678901234567890";
    String token = Jwts.builder()
            .setSubject(authResult.getName()) // get username
            .claim("authorities",authResult.getAuthorities())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1))) // exp each 1 week
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();

    // send token to client
    response.addHeader("Authorization","Bearer "+token);
  }
}
