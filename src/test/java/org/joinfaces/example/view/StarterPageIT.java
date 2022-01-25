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
	public void clickBootsfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickBootsFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("BootsFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("bootsfaces-spring-boot-starter");
	}

	@Test
	public void clickButterfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickButterFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("ButterFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("butterfaces-spring-boot-starter");
	}

	@Test
	public void clickAngularfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickAngularFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("AngularFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("angularfaces-spring-boot-starter");
	}

	@Test
	public void clickRichfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickRichFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("RichFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("richfaces-spring-boot-starter");
	}

	@Test
	public void clickRichfacesAyg() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickRichFacesAyg();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("RichFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("richfaces-ayg-spring-boot-starter");
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
	public void clickIcefaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickIceFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("IceFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("icefaces-spring-boot-starter");
	}

	@Test
	public void clickAdminfaces() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickAdminFaces();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("AdminFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("adminfaces-spring-boot-starter");
	}

	@Test
	public void clickOmnifaces1() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickOmnifaces1();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("OmniFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("omnifaces1-spring-boot-starter");
	}

	@Test
	public void clickOmnifaces3() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesComponentsTab();
		starterPage.clickOmnifaces3();
		assertThat(starterPage.getJoinFacesComponentsTabHeaderText())
			.contains("OmniFaces");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("omnifaces3-spring-boot-starter");
	}

	@Test
	public void clickRewrite() {
		StarterPage starterPage = initElements(StarterPage.class);
		starterPage.navegateTo();

		starterPage.clickJoinFacesAddonsTab();
		starterPage.clickRewrite();
		assertThat(starterPage.getJoinFacesAddonsTabHeaderText())
			.contains("Rewrite");

		starterPage.clickPomTab();
		assertThat(starterPage.getPomContent())
			.contains("rewrite-spring-boot-starter");
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
