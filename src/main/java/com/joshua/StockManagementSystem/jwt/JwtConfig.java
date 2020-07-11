package com.joshua.StockManagementSystem.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * ini baca dari application.yml
 */
@ConfigurationProperties(prefix = "application.jwt")
@Component
public class JwtConfig {
  private String secretKey, tokenPrefix;
  private Integer tokenExpirationAfterDays;

  public JwtConfig() {
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getTokenPrefix() {
    return tokenPrefix+" ";
  }

  public void setTokenPrefix(String tokenPrefix) {
    this.tokenPrefix = tokenPrefix;
  }

  public Integer getTokenExpirationAfterDays() {
    return tokenExpirationAfterDays;
  }

  public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
    this.tokenExpirationAfterDays = tokenExpirationAfterDays;
  }

  @Bean
  public SecretKey getSecretKeyForLogIn(){
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String getAuthorizationHeader(){
    return HttpHeaders.AUTHORIZATION;
  }
}
