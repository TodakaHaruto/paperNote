package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

//springの設定クラス
@Configuration
public class SecurityConfig {

    // セキュリティの対象外を設定
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity web) -> web.ignoring()
                .requestMatchers("/webjars/**", "/css/**", "/js/**");
    }
    
    //セキュリティの設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //http:セキュリティ設定を書くためのオブジェクト
    	http
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/login").permitAll()
    				.requestMatchers("/user/signup").permitAll()
    				.anyRequest().authenticated() //anyRequest:それ以外のリクエストには authenticated:ログイン済みであることを要求
    				)
    		.formLogin(form -> form
    				.loginProcessingUrl("/login")
    		        .loginPage("/login")
    		        .failureUrl("/login?error")
    		        .usernameParameter("userId")
    		        .passwordParameter("password")
    		        .defaultSuccessUrl("/user/success", true)
    		        .permitAll()
    		    );
    	http.csrf(csrf -> csrf.disable());
    	return http.build();
    }
    
}