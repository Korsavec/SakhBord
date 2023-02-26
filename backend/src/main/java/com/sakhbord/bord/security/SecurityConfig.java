package com.sakhbord.bord.security;

import com.sakhbord.bord.security.handler.JwtAccessDeniedHandler;
import com.sakhbord.bord.security.handler.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.sakhbord.bord.security.HttpPath.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        // prePostEnabled = true
)
public class SecurityConfig {

  private final
  UserDetailsServiceImpl userDetailsService;

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private final
  JwtAccessDeniedHandler jwtAccessDeniedHandler;

  private final AuthenticationConfiguration authConfiguration;

  private final
  PasswordEncoder passwordEncoder;

  public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler, AuthenticationConfiguration authConfiguration, PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    this.authConfiguration = authConfiguration;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    return daoAuthenticationProvider;
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests()



            .requestMatchers(API_USER).permitAll()



            .requestMatchers(API_ADMIN).permitAll()
            .requestMatchers(GUARD_ADMIN).permitAll()



            .requestMatchers(GUARD_ADD_ANNOUNCEMENT).permitAll()

            .requestMatchers(GET_ANNOUNCEMENTS).permitAll()

            .requestMatchers(GET_RULES).permitAll()

            .requestMatchers(RESET_PASS_USER).permitAll()



            .anyRequest().authenticated();

    return http.build();

  }


  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

}
