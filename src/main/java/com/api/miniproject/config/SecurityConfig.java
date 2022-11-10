package com.api.miniproject.config;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        , "/favicon.ico"
                        , "/error");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                //TODO: 동일도메인에서는 접근이 가능하도록 하는...? 좀 더 자세히 알아보기
//                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                // TODO: View session 사용하고, api는 session 사용 안하는거 가능?
//                .and()
//                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.NEVER) // 스프링 시큐리티가 생성하지는 않지만 기존에 있으면 사용
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jtw사용

                .and()
                .authorizeRequests()// httpServletRequest 를 사용하는 요청들에 대한 접근 제한을 설정하겠다
                .antMatchers("/v1/item-api/items").permitAll()
                .antMatchers("/v1/item-api/authenticate").permitAll()
                .antMatchers("/v1/item-api/signup").permitAll()

                .anyRequest().authenticated(); // 나머지는 인증을 받아야 한다

//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
        return httpSecurity.build();
    }

}
