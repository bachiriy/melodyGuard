package com.melodyguard.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class AppConfig {

    
	// @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// 	http
	// 		.authorizeHttpRequests((authorize) -> authorize
	// 			.anyRequest().authenticated()
	// 		)
	// 		.httpBasic(Customizer.withDefaults())
	// 		.formLogin(Customizer.withDefaults());

	// 	return http.build();
	// }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails userDetails = User.withDefaultPasswordEncoder()
	// 		.username("user")
	// 		.password("password")
	// 		.roles("ADMIN")
	// 		.build();

	// 	return new InMemoryUserDetailsManager(userDetails);
	// }
}
