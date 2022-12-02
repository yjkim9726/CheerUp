package com.codepresso.cheerup;

import com.codepresso.cheerup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //Security 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    // 로그인 실패 Handler
    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public HttpFirewall defaultHttpFirewall(){
        return new DefaultHttpFirewall();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Security 인증 무시 경로(front)
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/js/**","/vendor/**","/error","/favicon.ico");
        web.httpFirewall(defaultHttpFirewall());

    }

    // HTTP 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests() // 6
                .antMatchers("/","/login","/findId","/findPw","/gofindId", "/gofindPw", "/modifyPw","/signup", "/user","/register","/loginCheck","/home","/interview","/interview-home","/interview-result").permitAll() // 누구나 접근 허용
                .antMatchers("/interview-home" ,"/interview", "/interviewing_f", "/interview-result", "interview_result_f" ,"/interview").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 7
                .loginPage("/login") // 로그인 페이지 링크
                .loginProcessingUrl("/dologin") // modify - login process  page
                .defaultSuccessUrl("/home") // 로그인 성공 후 리다이렉트 주소
                .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                .and()
                .logout() // 8
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;

    }



}