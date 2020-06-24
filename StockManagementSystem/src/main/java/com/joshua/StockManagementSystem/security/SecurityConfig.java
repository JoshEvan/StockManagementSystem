package com.joshua.StockManagementSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * handle all security
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * where to secure things
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /**
     * every request must be authenticated dengan menggunakan Basic Auth dengan http
     */
    http
            .csrf().disable()// TODO: IMPL THIS
            .authorizeRequests()
            .antMatchers("/","/css/*","/js/*")/*2 line ini antMatchers dan permitAll untuk whitelist url yg ga hrs login untuk akses*/
            .permitAll()
            .antMatchers("/api/v1/joseph/production/**").hasRole(UserRole.MEMBER.name()) // onluy member can access api url
            .antMatchers("/api/**").hasRole(UserRole.ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
  }

  /**
   * how to retrieve users from database
   * @return
   */
  @Override
  @Bean
  protected UserDetailsService userDetailsService() {

    UserDetails stranger1 = User.builder()
            .username("stranger1")
            .password(passwordEncoder.encode("stranger1"))
            .roles(UserRole.STRANGER.name())
            .build();

    UserDetails user1 = User.builder()
            .username("member1")
            .password(passwordEncoder.encode("member1"))
            .roles(UserRole.MEMBER.name()) // ini jadi ROLE_MEMBER secara auto
            .build();

    UserDetails admin = User.builder()
            .username("admin1")
            .password(passwordEncoder.encode("admin1"))
            .roles(UserRole.ADMIN.name())
            .build();

    return new InMemoryUserDetailsManager(
            user1,admin
    );
  }
}
