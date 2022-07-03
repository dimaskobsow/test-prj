package ru.tst.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import ru.tst.rest.service.security.AuthenticationFailureHandler;
import ru.tst.rest.service.security.AuthenticationSuccessHandler;

@EnableWebSecurity
@RequiredArgsConstructor
@Import(SecurityProblemSupport.class)
public class SecurityConfig {

    private final SecurityProblemSupport problemSupport;
    private final AuthenticationFailureHandler failureHandler;
    private final AuthenticationSuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/authentication")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/user/check/**").permitAll()
                .antMatchers("/api/**").authenticated();

        return http.build();
    }
}
