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
public class MenuOfPageIT extends AbstractPageIT {

	@Test
	public void clickStarter() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		StarterPage page = menu.clickStarter();

		assertThat(page.getServletContainerTabHeaderText())
			.contains("Tomcat : Servlet Container");
	}

	@Test
	public void clickFileUpload() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		FileUploadPage page = menu.clickFileUpload();

		assertThat(page.isDownloadButtonEnabled())
			.isFalse();
	}

	@Test
	public void clickCustomInput() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		CustomInputPage page = menu.clickCustomInput();

		assertThat(page.getOutputText())
			.isEqualTo("You entered: null");
	}

	@Test
	public void clickMyTag() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		HelloTagPage page = menu.clickHelloTag();

		assertThat(page.getHelloWorldText())
			.isEqualTo("Hello Tag File");
	}

	@Test
	public void clickHiCC() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		HiCCPage page = menu.clickHiCC();

		assertThat(page.getHiCCText())
			.isEqualTo("Hi Composite Component");
	}

	@Test
	public void clickWelcomeConverter() {
		MenuOfPage menu = initElements(MenuOfPage.class);
		menu.navegateTo();

		WelcomeConverterPage page = menu.clickWelcomeConverter();
		page.submit("Nick");

		assertThat(page.getOutputText())
			.isEqualTo("Nick welcome!");
	}
}
