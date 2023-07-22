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
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings("SPRING_CSRF_PROTECTION_DISABLED")
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationUsers.class)
public class SecurityConfig {

	/**
	 * Configure security.
	 **/
	@Bean
	public SecurityFilterChain configure(HttpSecurity http, MvcRequestMatcher.Builder mvc) {
		try {
			http.csrf((csrf) -> csrf.disable());
			http
				.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers(mvc.pattern("/")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/**.faces")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/jakarta.faces.resource/**")).permitAll()
				.anyRequest().authenticated())
				.formLogin((formLogin) ->
					formLogin.loginPage("/login.faces")
					.permitAll()
					.failureUrl("/login.faces?error=true")
					.defaultSuccessUrl("/starter.faces"))
				.logout((logout) ->
					logout.logoutSuccessUrl("/login.faces")
					.deleteCookies("JSESSIONID"));
			return http.build();
		}
		catch (Exception ex) {
			throw new BeanCreationException("Wrong spring security configuration", ex);
		}
	}

	@Scope("prototype")
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
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
