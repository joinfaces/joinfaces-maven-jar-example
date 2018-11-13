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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.Getter;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Jsf Component service.
 *
 * @author Marcelo Fernandes
 */
@Component
@ApplicationScope
@Getter
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
	 * AdminFaces constant.
	 */
	public static final String ADMINFACES = "AdminFaces";

	/**
	 * IceFaces constant.
	 */
	public static final String ICEFACES = "IceFaces";

	/**
	 * AngularFaces constant.
	 */
	public static final String ANGULARFACES = "AngularFaces";

	/**
	 * RichFaces constant.
	 */
	public static final String RICHFACES = "RichFaces";

	private List<JsfComponent> jsfComponents;

	private List<JsfComponent> jsfAddons;

	/**
	 * AngularFaces constant.
	 */
	public static final String REWRITE = "Rewrite";

	/**
	 * RichFaces constant.
	 */
	public static final String OMNIFACES3 = "OmniFaces3";

	/**
	 * RichFaces constant.
	 */
	public static final String WELD = "Weld";

	@Value("${joinfaces.version}")
	private String joinfacesVersion;

	private String bootsfacesVersion;
	private String cdiVersion;
	private String mojarraVersion;
	private String myfacesVersion;
	private String omnifaces1Version;
	private String primefacesVersion;
	private String primefacesExtensionsVersion;
	private String butterfacesVersion;
	private String adminfacesVersion;
	private String icefacesVersion;
	private String angularfacesVersion;
	private String richfacesVersion;

	private String rewriteVersion;
	private String omnifaces3Version;
	private String weldVersion;

	@PostConstruct
	public void init() throws IOException, MalformedURLException, XmlPullParserException {
		Model model = createModel();
		Map<String, String> versionMap = versionMap(model);
		findVersions(versionMap);

		this.jsfComponents = new ArrayList<>();
		JsfComponent primefaces = jsfComponent(PRIMEFACES, "http://primefaces.org", getPrimefacesVersion());
		primefaces.getLinks().add(jsfComponentLink(PRIMEFACES_EXTENSIONS, "http://primefaces-extensions.github.io", getPrimefacesExtensionsVersion()));
		this.jsfComponents.add(primefaces);
		this.jsfComponents.add(jsfComponent(BOOTSFACES, "http://bootsfaces.net", getBootsfacesVersion()));
		this.jsfComponents.add(jsfComponent(BUTTERFACES, "http://butterfaces.org", getButterfacesVersion()));
		this.jsfComponents.add(jsfComponent(ADMINFACES, "https://adminfaces.github.io/site/", getAdminfacesVersion()));
		this.jsfComponents.add(jsfComponent(ICEFACES, "http://www.icesoft.org/java/projects/ICEfaces/overview.jsf", getIcefacesVersion()));
		this.jsfComponents.add(jsfComponent(ANGULARFACES, "http://angularfaces.com", getAngularfacesVersion()));
		this.jsfComponents.add(jsfComponent(RICHFACES, "https://github.com/richfaces/richfaces", getRichfacesVersion()));

		this.jsfAddons = new ArrayList<>();
		this.jsfAddons.add(jsfComponent(REWRITE, "https://www.ocpsoft.org/rewrite/", getRewriteVersion()));
		this.jsfAddons.add(jsfComponent(OMNIFACES3, "http://omnifaces.org/", getOmnifaces3Version()));
		this.jsfAddons.add(jsfComponent(WELD, "http://weld.cdi-spec.org/", getWeldVersion()));
	}

	private Model createModel() throws MalformedURLException, IOException, XmlPullParserException {
		MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
		return mavenXpp3Reader.read(new URL("http://repo1.maven.org/maven2/org/joinfaces/joinfaces-dependencies/"
				+ joinfacesVersion + "/joinfaces-dependencies-" + joinfacesVersion + ".pom").openStream());
	}

	private Map<String, String> versionMap(Model pom) {
		Map<String, String> result = new HashMap<>();
		pom.getDependencyManagement().getDependencies().forEach((dependency) -> {
			result.put(dependency.getGroupId() + ":" + dependency.getArtifactId(), dependency.getVersion());
		});
		return result;
	}

	private void findVersions(Map<String, String> versionMap) {
		this.cdiVersion = versionMap.get("javax.enterprise:cdi-api");
		this.mojarraVersion = versionMap.get("org.glassfish:javax.faces");
		this.myfacesVersion = versionMap.get("org.apache.myfaces.core:myfaces-api");
		this.omnifaces1Version = "1.14.1";
		this.primefacesVersion = versionMap.get("org.primefaces:primefaces");
		this.primefacesExtensionsVersion = versionMap.get("org.primefaces.extensions:primefaces-extensions");
		this.bootsfacesVersion = versionMap.get("net.bootsfaces:bootsfaces");
		this.butterfacesVersion = versionMap.get("org.butterfaces:components");
		this.adminfacesVersion = versionMap.get("com.github.adminfaces:admin-template");
		this.icefacesVersion = versionMap.get("org.icefaces:icefaces");
		this.angularfacesVersion = versionMap.get("de.beyondjava:angularFaces-core");
		this.richfacesVersion = versionMap.get("org.richfaces:richfaces");
		this.rewriteVersion = versionMap.get("org.ocpsoft.rewrite:rewrite-servlet");
		this.omnifaces3Version = "3.2";
		this.weldVersion = versionMap.get("org.jboss.weld.servlet:weld-servlet-core");
	}

	private JsfComponent jsfComponent(String name, String siteLink, String version) {
		JsfComponent result = JsfComponent.builder()
				.name(name)
				.links(new ArrayList<>())
				.build();

		result.getLinks().add(jsfComponentLink(name, siteLink, version));

		return result;
	}

	private JsfComponentLink jsfComponentLink(String name, String siteLink, String version) {
		return JsfComponentLink.builder()
				.name(name)
				.site(siteLink)
				.image("images/" + name.toLowerCase() + ".png")
				.version(version)
				.build();
	}

	public JsfComponent findByName(String name) {
		JsfComponent result = findByName(name, this.jsfComponents);
		if (result == null) {
			result = findByName(name, this.jsfAddons);
		}
		return result;
	}

	private JsfComponent findByName(String name, List<JsfComponent> jsfComponentsList) {
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
