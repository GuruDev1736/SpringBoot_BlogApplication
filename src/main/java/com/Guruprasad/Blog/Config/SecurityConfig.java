package com.Guruprasad.Blog.Config;


import com.Guruprasad.Blog.Security.JwtAuthenticationEntryPoint;
import com.Guruprasad.Blog.Security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    private UserDetailsService userDetailsService ;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuth, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuth;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->
                        authorize
                                .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/api/register").permitAll()
                                .anyRequest().authenticated())
                                .httpBasic(Customizer.withDefaults())
                                .exceptionHandling(exception
                        -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)).sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails guru = User.builder()
//                .username("guru")
//                .password(passwordEncoder().encode("guru"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails manali = User.builder()
//                .username("manali")
//                .password(passwordEncoder().encode("meow"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(guru,manali);
//
//    }


}
