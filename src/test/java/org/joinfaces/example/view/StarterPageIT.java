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
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
	public void clickPrimefaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfComponentsTab();
		starterPage.clickPrimeFaces();
		assertThat(starterPage.getJsfComponentsTabHeaderText())
			.contains("PrimeFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("primefaces-spring-boot-starter");
	}

	@Test
	public void clickBootsfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfComponentsTab();
		starterPage.clickBootsFaces();
		assertThat(starterPage.getJsfComponentsTabHeaderText())
			.contains("BootsFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("bootsfaces-spring-boot-starter");
	}

	@Test
	public void clickButterfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfComponentsTab();
		starterPage.clickButterFaces();
		assertThat(starterPage.getJsfComponentsTabHeaderText())
			.contains("ButterFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("butterfaces-spring-boot-starter");
	}

	@Test
	public void clickAngularfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfComponentsTab();
		starterPage.clickAngularFaces();
		assertThat(starterPage.getJsfComponentsTabHeaderText())
			.contains("AngularFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("angularfaces-spring-boot-starter");
	}

	@Test
	public void clickRichfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJsfComponentsTab();
		starterPage.clickRichFaces();
		assertThat(starterPage.getJsfComponentsTabHeaderText())
			.contains("RichFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("richfaces-spring-boot-starter");
	}

}
