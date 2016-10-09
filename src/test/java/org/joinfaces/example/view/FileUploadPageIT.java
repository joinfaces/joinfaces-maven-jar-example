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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JoinFacesExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadPageIT extends AbstractPageIT {

	@Test
	public void checkFileUploadElement() {
		WebDriver page = navegateTo("/index.jsf?content=fileUpload");

		assertThat(page.findElement(By.id("fileUpload")))
			.isNotNull();
	}

	@Test
	public void goToFileUploadPage() {
		WebDriver page = navegateTo("/");

		MenuOfPage menuOfPage = PageFactory.initElements(page, MenuOfPage.class);

		menuOfPage.clickFileUpload();

		assertThat(page.findElement(By.id("fileUpload")))
			.isNotNull();
	}

}
