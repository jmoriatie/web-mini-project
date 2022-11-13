package com.api.miniproject.config;

import com.api.miniproject.jwt.JwtAccessDeniedHandler;
import com.api.miniproject.jwt.JwtAuthenticationEntryPoint;
import com.api.miniproject.jwt.JwtSecurityConfig;
import com.api.miniproject.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(TokenProvider tokenProvider, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        , "/error"
                        , "/css/**"
                        , "/*.ico");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                // enable h2-console
                //TODO: 동일도메인에서는 접근이 가능하도록하는 설정 필수인지 확인 필요
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .authorizeRequests()// httpServletRequest 를 사용하는 요청들에 대한 접근 제한을 설정하겠다
                .antMatchers("/v1/item-api/items").permitAll()
                .antMatchers("/v1/account-api/join").permitAll()
                .antMatchers("/v1/auth/authenticate").permitAll()

                .anyRequest().authenticated() // 나머지는 인증을 받아야 한다

                // jwt
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .apply(new JwtSecurityConfig(tokenProvider))

                // TODO: View session 사용하고, api는 session 사용 안하는거 가능?
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER); // jtw사용

        return httpSecurity.build();
    }

}
