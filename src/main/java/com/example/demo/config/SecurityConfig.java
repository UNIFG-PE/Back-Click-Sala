package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .cors(Customizer->corsConfigurationSource())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)-> request
                        .requestMatchers(HttpMethod.POST,"/api/v1/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/register").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("SDMUnifgOdaback8gs2").password("{noop}SDM7Unifg9DJEwh").build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
