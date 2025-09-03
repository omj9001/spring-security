package com.study.springsecurity.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        //정적 리소스에 대한 접근을 허용함.
        http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        );

        //메인 화면, 로그인 화면을 제외한 접근에 대해 권한 요구
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/login/**").permitAll()
                        .anyRequest().authenticated()
                );

        //Form Login에 대한 설정
        http
                .formLogin(form -> form
                        .loginPage("/login/page")
                        .loginProcessingUrl("/login/loginProc") // 로그인 처리 URL 명시적 지정
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
