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

package org.joinfaces.example.view;

import jakarta.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Redirect to index.faces SPA.
 *
 * @author Marcelo Fernandes
 */
@Component
@ApplicationScope
public class RedirectToIndexConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(ServletContext t) {
		return ConfigurationBuilder.begin()
			.addRule()
			.when(Direction.isInbound().and(Path.matches("/")))
			.perform(Redirect.temporary("/starter.faces"));
	}

	@Override
	public int priority() {
		return 10;
	}

}
