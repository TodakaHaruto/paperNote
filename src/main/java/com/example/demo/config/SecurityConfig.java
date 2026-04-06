package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




//springの設定クラス
@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	return provider;
    }
	
	
    
    
    //セキュリティの設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //http:セキュリティ設定を書くためのオブジェクト
    	http
    		.authenticationProvider(authenticationProvider())
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/login", "/user/signup", "/webjars/**", "/css/**", "/js/**").permitAll() //セキュリティの対象外を設定
    				.anyRequest().authenticated()
    		)
    		.formLogin(form -> form
    				.loginProcessingUrl("/login")
    		        .loginPage("/login")
    		        .failureUrl("/login?error")
    		        .usernameParameter("userId")
    		        .passwordParameter("password")
    		        .defaultSuccessUrl("/user/success", true)
    		        .permitAll()
    		    )
    		.logout(logout -> logout
    				.logoutUrl("/logout")
    				.logoutSuccessUrl("/login?logout")
    				.permitAll()
    				);
    	http.csrf(csrf -> csrf.disable());
    	return http.build();
    }
    
}


