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

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

/**
 * StarterMBean to show joinfaces starters.
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@Named
@ViewScoped
public class StarterMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String servletContainer = "Tomcat";

	private String jsfImplementation = "Mojarra";

	private String jsfComponents = "PrimeFaces";

	public String getStarter() {
		String result = "";

		if (!servletContainer.equals("Tomcat")) {
			result += "-" + servletContainer;
		}

		if (!jsfImplementation.equals("Mojarra")) {
			result += "-" + jsfImplementation;
		}

		if (!jsfComponents.equals("PrimeFaces")) {
			result += "-" + jsfComponents;
		}

		return "jsf" + result.toLowerCase() + "-spring-boot-starter";
	}
}
