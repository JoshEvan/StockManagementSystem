package com.joshua.StockManagementSystem.security;

import com.joshua.StockManagementSystem.auth.UserService;
import com.joshua.StockManagementSystem.jwt.JwtConfig;
import com.joshua.StockManagementSystem.jwt.JwtTokenVerifier;
import com.joshua.StockManagementSystem.jwt.JwtUsernamePasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

/**
 * handle all security
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, SecretKey secretKey, JwtConfig jwtConfig) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
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
     * cara validasi / filter request nya dari atas ke bawah, jadi order urutan nya km hrus sesuai
     */
    // authenticationManager()dari parent class
    http
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
            .csrf().disable()
            .cors().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt itu stateless
            .and()
            .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
            .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernamePasswordAuthFilter.class)
            .authorizeRequests()
            .antMatchers("/","/login","/css/*","/js/*")/*2 line ini antMatchers dan permitAll untuk whitelist url yg ga hrs login untuk akses*/
            .permitAll()
            .antMatchers("/api/v1/joseph/login").permitAll()
            .antMatchers("/api/v1/joseph/production/**").hasAnyRole(UserRole.MEMBER.name(), UserRole.ADMIN.name()) // onluy member can access api url
            .antMatchers("/api/**").hasRole(UserRole.ADMIN.name())
            .anyRequest()
            .authenticated();
//            .and()
//           .httpBasic();
//            .formLogin()
//            .loginPage("/login").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(josephDaoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider josephDaoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Bean
  protected CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(Arrays.asList("*"));// TODO: impl this to front end server
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization"));

    // This allow us to expose the headers
    configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));


    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  //  /**
//   * how to retrieve users from database
//   * @return
//   */
//  @Override
//  @Bean
//  protected UserDetailsService userDetailsService() {
//
//    UserDetails stranger1 = User.builder()
//            .username("stranger1")
//            .password(passwordEncoder.encode("stranger1"))
//            .roles(UserRole.STRANGER.name())
//            .build();
//
//    UserDetails user1 = User.builder()
//            .username("member1")
//            .password(passwordEncoder.encode("member1"))
//            .roles(UserRole.MEMBER.name()) // ini jadi ROLE_MEMBER secara auto
//            .build();
//
//    UserDetails admin = User.builder()
//            .username("admin1")
//            .password(passwordEncoder.encode("admin1"))
//            .roles(UserRole.ADMIN.name())
//            .build();
//
//    return new InMemoryUserDetailsManager(
//            user1,admin
//    );
//  }
}
