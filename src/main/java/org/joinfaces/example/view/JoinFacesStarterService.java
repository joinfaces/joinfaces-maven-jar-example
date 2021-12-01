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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import org.joinfaces.autoconfigure.JoinfacesAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.security.web.FilterChainProxy;
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
public class JoinFacesStarterService {

	/**
	 * Security constant.
	 */
	public static final String SECURITY = "Security";

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
	 * OmniFaces 1 constant.
	 */
	public static final String OMNIFACES1 = "OmniFaces1";

	/**
	 * OmniFaces 3 constant.
	 */
	public static final String OMNIFACES3 = "OmniFaces3";

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

	@SuppressFBWarnings("EI_EXPOSE_REP")
	private List<JoinFacesStarter> joinFacesStartersComponents;

	@SuppressFBWarnings("EI_EXPOSE_REP")
	private List<JoinFacesStarter> joinFacesStartersAddons;

	/**
	 * Rewrite constant.
	 */
	public static final String REWRITE = "Rewrite";

	/**
	 * Weld constant.
	 */
	public static final String WELD = "Weld";

	private final String joinfacesVersion = JoinfacesAutoConfiguration.class.getPackage().getImplementationVersion();
	private final String springBootVersion = SpringApplication.class.getPackage().getImplementationVersion();
	private final String securityVersion = FilterChainProxy.class.getPackage().getImplementationVersion();

	private String bootsfacesVersion;
	private String cdiVersion;
	private String mojarraVersion;
	private String myfacesVersion;
	private String primefacesVersion;
	private String primefacesExtensionsVersion;
	private String butterfacesVersion;
	private String adminfacesVersion;
	private String omnifaces1Version;
	private String omnifaces3Version;
	private String icefacesVersion;
	private String angularfacesVersion;
	private String richfacesVersion;

	private String rewriteVersion;
	private String weldVersion;

	/**
	* Initialize default properties.
	*/
	@PostConstruct
	public void init() throws IOException, XmlPullParserException {
		Model model = createModel();
		Map<String, String> versionMap = versionMap(model);
		findVersions(versionMap);

		this.joinFacesStartersComponents = new ArrayList<>();
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(SECURITY)
			.library(joinFacesStarterLibrary("Spring Security", "https://spring.io/projects/spring-security", getSecurityVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(PRIMEFACES)
			.library(joinFacesStarterLibrary(PRIMEFACES, "https://primefaces.org", getPrimefacesVersion()))
			.library(joinFacesStarterLibrary(PRIMEFACES_EXTENSIONS, "https://primefaces-extensions.github.io", getPrimefacesExtensionsVersion()))
			.build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(BOOTSFACES)
			.library(joinFacesStarterLibrary(BOOTSFACES, "https://bootsfaces.net", getBootsfacesVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(BUTTERFACES)
			.library(joinFacesStarterLibrary(BUTTERFACES, "http://butterfaces.org", getButterfacesVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(ADMINFACES)
			.library(joinFacesStarterLibrary(ADMINFACES, "https://adminfaces.github.io/site/", getAdminfacesVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(OMNIFACES1)
			.library(joinFacesStarterLibrary("OmniFaces", "https://omnifaces.org/", getOmnifaces1Version())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(OMNIFACES3)
			.library(joinFacesStarterLibrary("OmniFaces", "https://omnifaces.org/", getOmnifaces3Version())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(ICEFACES)
			.library(joinFacesStarterLibrary(ICEFACES, "https://www.icesoft.org/java/projects/ICEfaces/overview.jsf", getIcefacesVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(ANGULARFACES)
			.library(joinFacesStarterLibrary(ANGULARFACES, "https://angularfaces.net", getAngularfacesVersion())).build());
		this.joinFacesStartersComponents.add(JoinFacesStarter.builder().name(RICHFACES)
			.library(joinFacesStarterLibrary(RICHFACES, "https://github.com/richfaces/richfaces", getRichfacesVersion())).build());

		this.joinFacesStartersAddons = new ArrayList<>();
		this.joinFacesStartersAddons.add(JoinFacesStarter.builder().name(REWRITE)
			.library(joinFacesStarterLibrary(REWRITE, "https://www.ocpsoft.org/rewrite/", getRewriteVersion())).build());
		this.joinFacesStartersAddons.add(JoinFacesStarter.builder().name(WELD)
			.library(joinFacesStarterLibrary(WELD, "https://weld.cdi-spec.org/", getWeldVersion())).build());
	}

	@SuppressFBWarnings("URLCONNECTION_SSRF_FD")
	private Model createModel() throws IOException, XmlPullParserException {
		MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
		return mavenXpp3Reader.read(new URL("https://repo1.maven.org/maven2/org/joinfaces/joinfaces-dependencies/"
				+ this.joinfacesVersion + "/joinfaces-dependencies-" + this.joinfacesVersion + ".pom").openStream());
	}

	private Map<String, String> versionMap(Model pom) {
		return pom.getDependencyManagement()
				.getDependencies()
				.stream()
				.collect(Collectors.toMap(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId(), Dependency::getVersion));
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

	private JoinFacesStarterLibrary joinFacesStarterLibrary(String name, String siteLink, String version) {
		return JoinFacesStarterLibrary.builder()
				.name(name)
				.site(siteLink)
				.image("images/" + name.toLowerCase().trim().replace(' ', '-') + ".png")
				.version(version)
				.build();
	}

	/**
	* Find joinfaces starter by name.
	*/
	public JoinFacesStarter findByName(String name) {
		JoinFacesStarter result = findByName(name, this.joinFacesStartersComponents);
		if (result == null) {
			result = findByName(name, this.joinFacesStartersAddons);
		}
		return result;
	}

	private JoinFacesStarter findByName(String name, List<JoinFacesStarter> joinFacesStarterList) {
		JoinFacesStarter result = null;

		for (JoinFacesStarter joinFacesStarter : joinFacesStarterList) {
			if (joinFacesStarter.getName().equals(name)) {
				result = joinFacesStarter;
				break;
			}
		}

		return result;
	}

	/**
	* Calculate contains by name using joinfaces starter list.
	*/
	public boolean containsByName(String name, List<JoinFacesStarter> joinFacesStarterList) {
		return findByName(name, joinFacesStarterList) != null;
	}
}
