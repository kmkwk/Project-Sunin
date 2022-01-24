package com.ssafy.sunin.config;

import com.ssafy.sunin.domain.user.Role;
import com.ssafy.sunin.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService; //만들어둔 customOAuth2UserService을 연결

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable().and()
                //cross site를 disable처리
                //authorizeRequests -> URL별 권한 관리 설정 옵션 시작점
                //permitAll()은 antMatchers에 지정된 url들은 모두 열람할 수 있도록 권한 부여
                .authorizeRequests().antMatchers("/", "/css/**", "/img/**", "/js/**", "/h2-console/**", "/api/v2/**", "/swagger-ui.html").permitAll()
                .antMatchers("/user.html").hasRole(Role.USER.name())
                .anyRequest().authenticated().and()
                //user라는 곳은 USER라는 권한이 있어야함
                //anyRequest 나머지 url들을 나타냄, authenticated 인증된 경우만 허용
                .logout().logoutSuccessUrl("/").and() //로그아웃 기능 설정 진입점, 로그아웃 성공 시 /로 이동
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService); //로그인 기능 설정 진입점, 소셜 로그인 성공 시 후속 조치 진행 Service 인터페이스 구현체 등록

    }

    @Override
    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().mvcMatchers("/feed/**");
    }
}