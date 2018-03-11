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
import javax.inject.Inject;
import javax.inject.Named;

import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;

import org.springframework.core.env.Environment;

/**
 * StarterMBean to show joinfaces starters.
 *
 * @author Marcelo Fernandes
 */
@Named
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

	@Inject
	private transient JsfComponentService jsfComponentService;

	@Inject
	private transient Environment environment;

	@Inject
	private transient FreemarkerUtils freemarkerUtils;

	@PostConstruct
	public void init() {
		this.jsfComponents = this.jsfComponentService.getJsfComponents();
		this.selectedJsfComponents = new ArrayList<>();
	}

	public List<String> getArtifactIds() {
		List<String> result = new ArrayList<>();

		if (isPrimeFacesSelected()) {
			result.add(JsfComponentService.PRIMEFACES);
		}
		if (isBootsFacesSelected()) {
			result.add(JsfComponentService.BOOTSFACES);
		}
		if (isButterFacesSelected()) {
			result.add(JsfComponentService.BUTTERFACES);
		}
		if (isAngularFacesSelected()) {
			result.add(JsfComponentService.ANGULARFACES);
		}
		if (isRichFacesSelected()) {
			result.add(JsfComponentService.RICHFACES);
		}
		if (result.isEmpty()) {
			result.add("jsf");
		}

		return result;
	}

	public String getJsfComponentsHeader() {
		StringBuilder result = new StringBuilder();

		if (isPrimeFacesSelected()) {
			result.append(' ').append(JsfComponentService.PRIMEFACES);
		}
		if (isBootsFacesSelected()) {
			result.append(' ').append(JsfComponentService.BOOTSFACES);
		}
		if (isButterFacesSelected()) {
			result.append(' ').append(JsfComponentService.BUTTERFACES);
		}
		if (isAngularFacesSelected()) {
			result.append(' ').append(JsfComponentService.ANGULARFACES);
		}
		if (isRichFacesSelected()) {
			result.append(' ').append(JsfComponentService.RICHFACES);
		}

		return result.toString();
	}

	public boolean isTomcatSelected() {
		return this.servletContainer.equals(TOMCAT);
	}

	public boolean isMojarraSelected() {
		return this.jsfImplementation.equals(MOJARRA);
	}

	public boolean isPrimeFacesSelected() {
		return this.jsfComponentService.containsByName(JsfComponentService.PRIMEFACES, this.selectedJsfComponents);
	}

	public boolean isBootsFacesSelected() {
		return this.jsfComponentService.containsByName(JsfComponentService.BOOTSFACES, this.selectedJsfComponents);
	}

	public boolean isButterFacesSelected() {
		return this.jsfComponentService.containsByName(JsfComponentService.BUTTERFACES, this.selectedJsfComponents);
	}

	public boolean isAngularFacesSelected() {
		return this.jsfComponentService.containsByName(JsfComponentService.ANGULARFACES, this.selectedJsfComponents);
	}

	public boolean isRichFacesSelected() {
		return this.jsfComponentService.containsByName(JsfComponentService.RICHFACES, this.selectedJsfComponents);
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
