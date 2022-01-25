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

import org.joinfaces.example.JoinFacesExampleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JoinFacesExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarterPageIT extends AbstractPageIT {

	@Test
	public void checkPanelHeaderText() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		assertThat(starterPage.getServletContainerTabHeaderText())
			.contains("Servlet Container");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("<joinfaces.version>");
	}

	@Test
	public void clickJetty() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickServletContainerTab();
		starterPage.clickJetty();
		assertThat(starterPage.getServletContainerTabHeaderText())
			.contains("Jetty");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("jetty-spring-boot-starter");
	}

	@Test
	public void clickMyfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfImplementationTab();
		starterPage.clickMyFaces();
		assertThat(starterPage.getJsfImplementationTabHeaderText())
			.contains("MyFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("myfaces-spring-boot-starter");
	}

	@Test
	public void clickSecurity() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickSecurity();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("Security");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("security-spring-boot-starter");
	}

	@Test
	public void clickPrimefaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickPrimeFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("PrimeFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("primefaces-spring-boot-starter");
	}

	@Test
	public void clickTobago() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickTobago();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("Tobago");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("tobago-spring-boot-starter");
	}

	@Test
	public void clickOmnifaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickOmnifaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("OmniFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("omnifaces-spring-boot-starter");
	}

	@Test
	public void clickWeld() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesAddonsTab();
		starterPage.clickWeld();
		assertThat(starterPage.getJoinFacesAddonsTabHeaderText())
			.contains("Weld");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("weld-spring-boot-starter");
	}

}
