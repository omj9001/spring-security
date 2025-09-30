package com.study.springsecurity.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain restAPIFilterChain (HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/**").permitAll()
        );
        return http.build();
    }

    @Bean
    @Order(2)
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
                        .requestMatchers("/","/login/**","/join").permitAll()
                        .anyRequest().authenticated()
                );

        //Form Login에 대한 설정
        http
                .formLogin(form -> form
                        .loginPage("/login/page")
                        .loginProcessingUrl("/login/loginProc") // 로그인 처리 URL 명시적 지정
                        .defaultSuccessUrl("/login/userProfile", true)
                        .permitAll()
                )
        ;

        http.logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login/page")
                                .addLogoutHandler((request, response, authentication) -> {
                                                        request.getSession().invalidate();
                                })
                                .logoutSuccessHandler(((request, response, authentication) -> {
                                                        response.sendRedirect("/login/page");
                                }))
                                .deleteCookies("JSESSIONID")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
