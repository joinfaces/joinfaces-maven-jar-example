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

package org.joinfaces.example;

import java.io.IOException;

import javax.faces.application.ResourceHandler;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JoinFacesExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JsfPagesIT {

	@LocalServerPort
	private long port;

	private <P extends Page> P page(String url) throws IOException {
		WebClient webClient = new WebClient();
		webClient.getOptions().setTimeout(0);
		return webClient.getPage("http://localhost:" + port + url);
	}

	@Test
	public void starterJSF() throws IOException {
		HtmlPage page = page("/starter.jsf");
		assertThat(page.getTitleText()).startsWith(".:: Choose your Jsf Spring Boot Starter ::.");
	}

	private String resource(String url) {
		return ResourceHandler.RESOURCE_IDENTIFIER + url + ".jsf";
	}

	@Test
	public void faviconIcoResource() throws IOException {
		Page page = page(resource("/images/favicon.ico"));
		assertThat(page.getWebResponse().getContentLength())
			.isEqualTo(1150);
	}

	@Test
	public void starterCSSResource() throws IOException {
		Page page = page(resource("/starter.css"));
		assertThat(page.getWebResponse().getContentLength())
			.isGreaterThan(0);
	}

}
