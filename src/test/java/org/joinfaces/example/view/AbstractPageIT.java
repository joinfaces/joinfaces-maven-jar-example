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

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.springframework.boot.context.embedded.LocalServerPort;

/**
 * Abstract class to create utility methods to access WebClient and Page.
 * @author Marcelo Fernandes
 */
public class AbstractPageIT {

	@LocalServerPort
	private long port;

	private static WebClient webClient;

	@BeforeClass
	public static void init() {
		webClient = new WebClient();
	}

	@AfterClass
	public static void finish() {
		webClient.close();
	}

	protected <P extends Page> P page(String url) throws IOException {
		return webClient.getPage("http://localhost:" + port + url);
	}

	protected void waitJavascript(String action) {
		long time = System.currentTimeMillis();
		int stillRunning = webClient.waitForBackgroundJavaScript(60000);
		time = System.currentTimeMillis() - time;
		System.out.println(action + " waited: " + time + " ms. running: " + stillRunning);
	}
}
