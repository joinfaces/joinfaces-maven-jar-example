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

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

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
	public void checkServletContainerElement() throws IOException {
		HtmlPage page = page("/");

		assertThat(page.getElementByName("servletContainer"))
			.isNotNull();
	}

	@Test
	public void clickJettyMyfacesButterfaces() throws IOException {
		HtmlPage page = page("/");

		HtmlRadioButtonInput jettyRadioButtonInput = (HtmlRadioButtonInput) page.getFirstByXPath("//input[@value='Jetty']");
		jettyRadioButtonInput.setChecked(true);
		HtmlPage jettyClickPage = jettyRadioButtonInput.click();
		waitJavascript("jettyClick");

		HtmlSpan panelHeaderSpan = jettyClickPage.getFirstByXPath("//span[@class='ui-panel-title']");
		assertThat(panelHeaderSpan.getTextContent())
			.contains("jsf-jetty-spring-boot-starter");

		HtmlRadioButtonInput myfacesRadioButtonInput = (HtmlRadioButtonInput) jettyClickPage.getFirstByXPath("//input[@value='MyFaces']");
		myfacesRadioButtonInput.setChecked(true);
		HtmlPage myfacesClickPage = myfacesRadioButtonInput.click();
		waitJavascript("myfacesClick");

		panelHeaderSpan = myfacesClickPage.getFirstByXPath("//span[@class='ui-panel-title']");
		assertThat(panelHeaderSpan.getTextContent())
			.contains("jsf-jetty-myfaces-spring-boot-starter");

		HtmlRadioButtonInput butterfacesRadioButtonInput = (HtmlRadioButtonInput) myfacesClickPage.getFirstByXPath("//input[@value='ButterFaces']");
		butterfacesRadioButtonInput.setChecked(true);
		HtmlPage butterfacesClickPage = butterfacesRadioButtonInput.click();
		waitJavascript("butterfacesClick");

		panelHeaderSpan = butterfacesClickPage.getFirstByXPath("//span[@class='ui-panel-title']");
		assertThat(panelHeaderSpan.getTextContent())
			.contains("jsf-jetty-myfaces-butterfaces-spring-boot-starter");
	}

}
