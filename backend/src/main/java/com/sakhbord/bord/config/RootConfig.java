package com.sakhbord.bord.config;

import com.sakhbord.bord.security.filter.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableScheduling
public class RootConfig implements WebMvcConfigurer {

    private static final String HTTP_HEADERS = "Access-Control-Allow-Origin";

    @Value("${sakhbord.app.origins.host}")
    private String[] originHost;

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setMaxAge(3600L);

//        corsConfiguration.setAllowedOrigins(Collections.singletonList(originHost))
        corsConfiguration.setAllowedOrigins(List.of(originHost));

        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", HTTP_HEADERS, "Content-Type",
                "Accept", "Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));

        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Token", "Authorization",
                HTTP_HEADERS, "Access-Control-Allow-Credentials"));

        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE"));

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }


}
