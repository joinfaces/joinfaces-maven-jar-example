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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationUsers.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApplicationUsers applicationUsers;

	@SuppressFBWarnings({"SPRING_CSRF_PROTECTION_DISABLED", "THROWS_METHOD_THROWS_RUNTIMEEXCEPTION"})
	@Override
	protected void configure(HttpSecurity http) {
		try {
			http.csrf().disable();
			http
				.userDetailsService(userDetailsService())
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
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected UserDetailsService userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		InMemoryUserDetailsManager result = new InMemoryUserDetailsManager();
		for (UserCredentials userCredentials : this.applicationUsers.getUsersCredentials()) {
			result.createUser(User.builder()
				.username(userCredentials.getUsername())
				.password(encoder.encode(userCredentials.getPassword()))
				.authorities(userCredentials.getAuthorities().toArray(new String[0])).build());
		}
		return result;
	}
}
