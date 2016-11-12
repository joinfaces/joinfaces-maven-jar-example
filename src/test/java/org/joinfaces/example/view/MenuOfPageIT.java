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
public class MenuOfPageIT extends AbstractPageIT {

	@Test
	public void clickStarter() {
		MenuOfPage menuOfPage = initElements(MenuOfPage.class);
		menuOfPage.navegateTo();

		StarterPage starterPage = menuOfPage.clickStarter();

		assertThat(starterPage.getPanelHeaderText())
			.contains("jsf-spring-boot-starter");
	}

	@Test
	public void clickFileUpload() {
		MenuOfPage menuOfPage = initElements(MenuOfPage.class);
		menuOfPage.navegateTo();

		FileUploadPage fileUploadPage = menuOfPage.clickFileUpload();

		assertThat(fileUploadPage.isDownloadButtonEnabled())
			.isFalse();
	}

	@Test
	public void clickCustomInput() {
		MenuOfPage menuOfPage = initElements(MenuOfPage.class);
		menuOfPage.navegateTo();

		CustomInputPage customInputPage = menuOfPage.clickCustomInput();

		assertThat(customInputPage.getOutputText())
			.isEqualTo("You entered: null");
	}

	@Test
	public void clickMyTag() {
		MenuOfPage menuOfPage = initElements(MenuOfPage.class);
		menuOfPage.navegateTo();

		HelloTagPage helloTagPage = menuOfPage.clickHelloTag();

		assertThat(helloTagPage.getHelloWorldText())
			.isEqualTo("Hello Tag File");
	}
}
