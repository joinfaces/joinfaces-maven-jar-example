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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
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

	@SuppressFBWarnings("EI_EXPOSE_REP")
	@Getter
	private List<JoinFacesStarter> joinFacesStarterComponents;

	@SuppressFBWarnings("EI_EXPOSE_REP")
	@Getter
	@Setter
	private List<JoinFacesStarter> selectedJoinFacesStarterComponents;

	@Autowired
	private transient JoinFacesStarterService joinFacesStarterService;

	@Autowired
	private transient FreemarkerUtils freemarkerUtils;

	@SuppressFBWarnings("EI_EXPOSE_REP")
	@Getter
	private List<JoinFacesStarter> joinFacesStarterAddons;

	@SuppressFBWarnings("EI_EXPOSE_REP")
	@Getter
	@Setter
	private List<JoinFacesStarter> selectedJoinFacesStarterAddons;

	/**
	* Initialize default attributes.
	*/
	@PostConstruct
	public void init() {
		this.joinFacesStarterComponents = this.joinFacesStarterService.getJoinFacesStartersComponents();
		this.selectedJoinFacesStarterComponents = new ArrayList<>();
		this.joinFacesStarterAddons = this.joinFacesStarterService.getJoinFacesStartersAddons();
		this.selectedJoinFacesStarterAddons = new ArrayList<>();
	}

	/**
	* Calculate component artifact ids.
	*/
	public List<String> getComponentArtifactIds() {
		List<String> result = new ArrayList<>();

		for (JoinFacesStarter joinFacesStarter : this.joinFacesStarterComponents) {
			if (this.joinFacesStarterService.containsByName(joinFacesStarter.getName(), this.selectedJoinFacesStarterComponents)) {
				result.add(joinFacesStarter.getName());
			}
		}
		if (result.isEmpty()) {
			result.add("jsf");
		}

		return result;
	}

	/**
	* Calculate joinfaces starter components header.
	*/
	public String getJoinFacesStarterComponentsHeader() {
		StringBuilder result = new StringBuilder();

		for (JoinFacesStarter joinFacesStarter : this.joinFacesStarterComponents) {
			if (this.joinFacesStarterService.containsByName(joinFacesStarter.getName(), this.selectedJoinFacesStarterComponents)) {
				result.append(' ').append(joinFacesStarter.getName());
			}
		}

		return result.toString();
	}

	/**
	* Calculate addon artifact ids.
	*/
	public List<String> getAddonArtifactIds() {
		List<String> result = new ArrayList<>();

		for (JoinFacesStarter joinFacesStarter : this.joinFacesStarterAddons) {
			if (this.joinFacesStarterService.containsByName(joinFacesStarter.getName(), this.selectedJoinFacesStarterAddons)) {
				result.add(joinFacesStarter.getName());
			}
		}

		return result;
	}

	/**
	* Calculate joinfaces starter addons header.
	*/
	public String getJoinFacesStarterAddonsHeader() {
		StringBuilder result = new StringBuilder();

		for (JoinFacesStarter joinFacesStarter : this.joinFacesStarterAddons) {
			if (this.joinFacesStarterService.containsByName(joinFacesStarter.getName(), this.selectedJoinFacesStarterAddons)) {
				result.append(' ').append(joinFacesStarter.getName());
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

	/**
	* Create pom map.
	*/
	public String getPom() throws IOException, TemplateException {
		Map<String, Object> map = new HashMap<>();
		map.put("starterService", this.joinFacesStarterService);
		map.put("starterMBean", this);
		return this.freemarkerUtils.mergeTemplate(map, "pom.ftl");
	}
}
