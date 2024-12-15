package io.github.igorcastelo.spring_security_jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ConfigSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider) throws Exception{
        return http
                .authorizeHttpRequests(customizer-> {
                    customizer.requestMatchers("/public").permitAll();
                    customizer.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(senhaMasterAuthenticationProvider)
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails commonUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
