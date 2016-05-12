package com.github.persapiens.example;

import com.github.persapiens.jsfboot.CdiScopeResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan(scopeResolver = CdiScopeResolver.class)
@Configuration
public class JsfSpringBootStarterExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsfSpringBootStarterExampleApplication.class, args);
	}
}
