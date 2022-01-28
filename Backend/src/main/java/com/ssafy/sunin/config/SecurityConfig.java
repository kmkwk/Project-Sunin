package com.ssafy.sunin.config;


import com.ssafy.sunin.user.CustomOAuth2UserService;
=========
import com.ssafy.sunin.security.service.CustomOAuth2UserService;
>>>>>>>>> Temporary merge branch 2
import com.ssafy.sunin.user.JwtAuthenticationFilter;
import com.ssafy.sunin.user.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService; //만들어둔 customOAuth2UserService을 연결


    private final JwtTokenProvider jwtTokenProvider;


//    @Bean
//    public PasswordEncoder noOpPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }


=========
    private final JwtTokenProvider jwtTokenProvider;

>>>>>>>>> Temporary merge branch 2
    // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

<<<<<<<<< Temporary merge branch 1

=========
>>>>>>>>> Temporary merge branch 2
    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
<<<<<<<<< Temporary merge branch 1
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .headers().frameOptions().disable().and()
//                //cross site를 disable처리
//                //authorizeRequests -> URL별 권한 관리 설정 옵션 시작점
//                //permitAll()은 antMatchers에 지정된 url들은 모두 열람할 수 있도록 권한 부여
//                .authorizeRequests().antMatchers("/", "/css/**", "/img/**", "/js/**", "/h2-console/**", "/api/v2/**","/swagger-ui.html").permitAll()
//                //anyRequest 나머지 url들을 나타냄, authenticated 인증된 경우만 허용
//                .and()
//                .logout().logoutSuccessUrl("/");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable().and()
                //cross site를 disable처리
                //authorizeRequests -> URL별 권한 관리 설정 옵션 시작점
                //permitAll()은 antMatchers에 지정된 url들은 모두 열람할 수 있도록 권한 부여
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                //user라는 곳은 USER라는 권한이 있어야함
                //anyRequest 나머지 url들을 나타냄, authenticated 인증된 경우만 허용
                .logout().logoutSuccessUrl("/").and() //로그아웃 기능 설정 진입점, 로그아웃 성공 시 /로 이동
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService); //로그인 기능 설정 진입점, 소셜 로그인 성공 시 후속 조치 진행 Service 인터페이스 구현체 등록
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().mvcMatchers("/user/**");
    }
}

