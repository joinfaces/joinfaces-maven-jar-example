/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.example;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationUsers.class)
public class SecurityConfig {

	/**
	 * Setups a security filter chain that will be applied to all the requests.
	 * Since JSF 2.2 there is already a CSRF protection, so the Spring CSRF protection must be disabled,
	 * because it blocks AJAX requests.
	 * @param http - autowired HttpSecurity object
	 * @return SecurityFilterChain that contains all the security filters
	 * @throws BeanCreationException if something in the configuration is wrong
	 */
	@SuppressFBWarnings("SPRING_CSRF_PROTECTION_DISABLED")
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) {
		try {
			http.csrf().disable();
			http
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/**.jsf").permitAll()
				.antMatchers("/javax.faces.resource/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login.jsf")
				.permitAll()
				.failureUrl("/login.jsf?error=true")
				.defaultSuccessUrl("/starter.jsf")
				.and()
				.logout()
				.logoutSuccessUrl("/login.jsf")
				.deleteCookies("JSESSIONID");
			return http.build();
		}
		catch (Exception ex) {
			throw new BeanCreationException("Wrong spring security configuration", ex);
		}
	}

	/**
	 * UserDetailsService that configures an in-memory users store.
	 * @param applicationUsers - autowired users from the application.yml file
	 * @return InMemoryUserDetailsManager - a manager that keeps all the users' info in the memory
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService(ApplicationUsers applicationUsers) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		InMemoryUserDetailsManager result = new InMemoryUserDetailsManager();
		for (UserCredentials userCredentials : applicationUsers.getUsersCredentials()) {
			result.createUser(User.builder()
				.username(userCredentials.getUsername())
				.password(encoder.encode(userCredentials.getPassword()))
				.authorities(userCredentials.getAuthorities().toArray(new String[0])).build());
		}
		return result;
	}
}
