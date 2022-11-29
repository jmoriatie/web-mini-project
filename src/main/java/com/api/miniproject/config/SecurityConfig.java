package com.api.miniproject.config;

import com.api.miniproject.service.login.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
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
                .antMatchers("/join").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/v1/account-api/join").permitAll()
                .antMatchers("/v1/account-api/login").permitAll()
                .antMatchers("/v1/account-api/createAccount").hasRole("ADMIN")
                .anyRequest().authenticated() // 나머지는 인증을 받아야 한다

                .and()
                .formLogin()
                    .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("accountId")
                        .passwordParameter("accountPw")
                        .permitAll()

                .and()
                .logout().permitAll()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true);

        return httpSecurity.build();
    }

}
