package com.example.testpath.config;

import com.example.testpath.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
import java.security.MessageDigest;
import java.math.BigInteger;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(rawPassword.toString().getBytes());
                    byte[] digest = md.digest();
                    String hash = String.format("%032x", new BigInteger(1, digest));
                    return hash;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to encode password", e);
                }
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String rawHash = encode(rawPassword);
                return rawHash.equals(encodedPassword);
            }
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/static/**", "/error", "/login", "/css/**", "/js/**", "/benchmark01/**", "/.well-known/**").permitAll()
                .antMatchers("/benchmark02/**").authenticated()
                .anyRequest().authenticated()
                .and()
            .formLogin().disable()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                });
    }
}
