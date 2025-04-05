package com.softwaretask.Booking_app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        http.authorizeHttpRequests(authorizeRequests ->authorizeRequests.anyRequest().authenticated())
                .httpBasic(httpBasic->httpBasic.init(http))
                .csrf(csrf->csrf.disable());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("Configuring user details service");
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")

                .build();
        logger.info("User details service created");
        return new InMemoryUserDetailsManager(user);
    }
}
