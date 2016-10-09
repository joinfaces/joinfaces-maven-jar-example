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

import org.joinfaces.example.JoinFacesExampleApplication;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JoinFacesExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarterPageIT extends AbstractPageIT {

	@Test
	public void checkServletContainerElement() throws IOException {
		WebDriver page = page("/");

		assertThat(page.findElement(By.name("servletContainer")))
			.isNotNull();
	}

	@Test
	public void clickJettyMyfacesButterfaces() throws IOException {
		WebDriver page = page("/");

		By panelHeaderSpanBy = By.xpath("//span[@class='ui-panel-title']");

		WebElement jettyRadioButtonInput = page.findElement(By.xpath("//input[@value='Jetty']/.."));
		jettyRadioButtonInput.click();

		String starter = "jsf-jetty-spring-boot-starter";
		new WebDriverWait(page, 10000).until(ExpectedConditions.textToBePresentInElementLocated(panelHeaderSpanBy, starter));

		assertThat(page.findElement(panelHeaderSpanBy).getText())
			.contains(starter);

		WebElement myfacesRadioButtonInput = page.findElement(By.xpath("//input[@value='MyFaces']/.."));
		myfacesRadioButtonInput.click();

		starter = "jsf-jetty-myfaces-spring-boot-starter";
		new WebDriverWait(page, 10000).until(ExpectedConditions.textToBePresentInElementLocated(panelHeaderSpanBy, starter));

		assertThat(page.findElement(panelHeaderSpanBy).getText())
			.contains(starter);

		WebElement butterfacesRadioButtonInput = page.findElement(By.xpath("//input[@value='ButterFaces']/.."));
		butterfacesRadioButtonInput.click();

		starter = "jsf-jetty-myfaces-butterfaces-spring-boot-starter";
		new WebDriverWait(page, 10000).until(ExpectedConditions.textToBePresentInElementLocated(panelHeaderSpanBy, starter));

		assertThat(page.findElement(panelHeaderSpanBy).getText())
			.contains(starter);
	}

}
