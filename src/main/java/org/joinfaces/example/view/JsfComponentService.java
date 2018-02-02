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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

import org.springframework.core.env.Environment;

/**
 * Jsf Component service.
 *
 * @author Marcelo Fernandes
 */
@Named
@ApplicationScoped
public class JsfComponentService {

	/**
	 * PrimeFaces constant.
	 */
	public static final String PRIMEFACES = "PrimeFaces";

	/**
	 * PrimeFaces Extensions constant.
	 */
	public static final String PRIMEFACES_EXTENSIONS = "PrimeFaces-Extensions";

	/**
	 * BootsFaces constant.
	 */
	public static final String BOOTSFACES = "BootsFaces";

	/**
	 * ButterFaces constant.
	 */
	public static final String BUTTERFACES = "ButterFaces";

	/**
	 * AngularFaces constant.
	 */
	public static final String ANGULARFACES = "AngularFaces";

	/**
	 * RichFaces constant.
	 */
	public static final String RICHFACES = "RichFaces";

	@Getter
	private List<JsfComponent> jsfComponents;

	@Inject
	private transient Environment environment;

	@PostConstruct
	public void init() {
		this.jsfComponents = new ArrayList<>();
		JsfComponent primefaces = jsfComponent(PRIMEFACES, "http://primefaces.org");
		primefaces.getLinks().add(jsfComponentLink(PRIMEFACES_EXTENSIONS, "http://primefaces-extensions.github.io"));
		this.jsfComponents.add(primefaces);
		this.jsfComponents.add(jsfComponent(BOOTSFACES, "http://bootsfaces.net"));
		this.jsfComponents.add(jsfComponent(BUTTERFACES, "http://butterfaces.org"));
		this.jsfComponents.add(jsfComponent(ANGULARFACES, "http://angularfaces.com"));
		this.jsfComponents.add(jsfComponent(RICHFACES, "https://github.com/richfaces/richfaces"));
	}

	private JsfComponent jsfComponent(String name, String siteLink) {
		JsfComponent result = JsfComponent.builder()
				.name(name)
				.links(new ArrayList<>())
				.build();

		result.getLinks().add(jsfComponentLink(name, siteLink));

		return result;
	}

	private JsfComponentLink jsfComponentLink(String name, String siteLink) {
		return JsfComponentLink.builder()
				.name(name)
				.site(siteLink)
				.image("images/" + name.toLowerCase() + ".png")
				.version(this.environment.getProperty(name.toLowerCase() + ".version"))
				.build();
	}

	public JsfComponent findByName(String name) {
		return findByName(name, this.jsfComponents);
	}

	public JsfComponent findByName(String name, List<JsfComponent> jsfComponentsList) {
		JsfComponent result = null;

		for (JsfComponent jsfComponent : jsfComponentsList) {
			if (jsfComponent.getName().equals(name)) {
				result = jsfComponent;
				break;
			}
		}

		return result;
	}

	public boolean containsByName(String name, List<JsfComponent> jsfComponentsList) {
		return findByName(name, jsfComponentsList) != null;
	}
}
