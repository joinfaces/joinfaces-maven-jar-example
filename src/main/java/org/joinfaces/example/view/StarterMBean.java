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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * StarterMBean to show joinfaces starters.
 *
 * @author Marcelo Fernandes
 */
@Component
@ViewScoped
public class StarterMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String TOMCAT = "Tomcat";

	private static final String MOJARRA = "Mojarra";

	@Getter
	@Setter
	private String servletContainer = TOMCAT;

	@Getter
	@Setter
	private String jsfImplementation = MOJARRA;

	@Getter
	private List<JsfComponent> jsfComponents;

	@Getter
	@Setter
	private List<JsfComponent> selectedJsfComponents;

	@Autowired
	private transient JsfComponentService jsfComponentService;

	@Autowired
	private transient Environment environment;

	@Autowired
	private transient FreemarkerUtils freemarkerUtils;

	@Getter
	private List<JsfComponent> jsfAddons;

	@Getter
	@Setter
	private List<JsfComponent> selectedJsfAddons;

	@PostConstruct
	public void init() {
		this.jsfComponents = this.jsfComponentService.getJsfComponents();
		this.selectedJsfComponents = new ArrayList<>();
		this.jsfAddons = this.jsfComponentService.getJsfAddons();
		this.selectedJsfAddons = new ArrayList<>();
	}

	public List<String> getComponentArtifactIds() {
		List<String> result = new ArrayList<>();

		for (JsfComponent jsfComponent : this.jsfComponents) {
			if (this.jsfComponentService.containsByName(jsfComponent.getName(), this.selectedJsfComponents)) {
				result.add(jsfComponent.getName());
			}
		}
		if (result.isEmpty()) {
			result.add("jsf");
		}

		return result;
	}

	public String getJsfComponentsHeader() {
		StringBuilder result = new StringBuilder();

		for (JsfComponent jsfComponent : this.jsfComponents) {
			if (this.jsfComponentService.containsByName(jsfComponent.getName(), this.selectedJsfComponents)) {
				result.append(' ').append(jsfComponent.getName());
			}
		}

		return result.toString();
	}

	public List<String> getAddonArtifactIds() {
		List<String> result = new ArrayList<>();

		for (JsfComponent jsfComponent : this.jsfAddons) {
			if (this.jsfComponentService.containsByName(jsfComponent.getName(), this.selectedJsfAddons)) {
				result.add(jsfComponent.getName());
			}
		}

		return result;
	}

	public String getJsfAddonsHeader() {
		StringBuilder result = new StringBuilder();

		for (JsfComponent jsfComponent : this.jsfAddons) {
			if (this.jsfComponentService.containsByName(jsfComponent.getName(), this.selectedJsfAddons)) {
				result.append(' ').append(jsfComponent.getName());
			}
		}

		return result.toString();
	}

	public boolean isTomcatSelected() {
		return this.servletContainer.equals(TOMCAT);
	}

	public boolean isMojarraSelected() {
		return this.jsfImplementation.equals(MOJARRA);
	}

	public String getPom() throws IOException, TemplateException {
		Map<String, Object> map = new HashMap<>();
		map.put("environment", this.environment);
		map.put("starterMBean", this);
		return this.freemarkerUtils.mergeTemplate(map, "pom.ftl");
	}

	public void setPom(String pom) {
	}
}
