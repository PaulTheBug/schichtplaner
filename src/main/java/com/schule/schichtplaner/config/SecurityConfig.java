package com.schule.schichtplaner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.schule.schichtplaner.repositories.MitarbeiterRepository;
import com.schule.schichtplaner.views.LoginView;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.with(VaadinSecurityConfigurer.vaadin(), configurer -> {
             configurer.loginView(LoginView.class);
        });
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(MitarbeiterRepository mitarbeiterRepository) {
        return username -> mitarbeiterRepository.findByBenutzername(username)
                .map(mitarbeiter -> User.withUsername(mitarbeiter.getBenutzername())
                        .password(mitarbeiter.getPasswort())
                        .roles("USER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Mitarbeiter nicht gefunden: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
