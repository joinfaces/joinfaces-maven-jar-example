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

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * Abstract class to create utility methods to access WebClient and Page.
 * @author Marcelo Fernandes
 */
public class AbstractPageIT {

	@LocalServerPort
	private long port;

	private static WebDriver webDriver;

	private static int countFinish = 0;

	private static final int NUMBER_OF_SUBCLASSES = 8;

	@BeforeAll
	public static void init() {
		if (webDriver == null) {
			String webDriverType = System.getProperty("webDriverType", "chrome");
			if (webDriverType.equals("htmlunit")) {
				webDriver = getHtmlUnitDriver();
			}
			else if (webDriverType.equals("firefox")) {
				webDriver = getFirefoxDriver();
			}
			else {
				webDriver = getChromeDriver();
			}
		}
	}

	private static WebDriver getHtmlUnitDriver() {
		return new HtmlUnitDriver(true);
	}

	private static WebDriver getChromeDriver() {
		WebDriverManager.getInstance(ChromeDriver.class).setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless", "--window-size=1920,1080", "--ignore-certificate-errors", "--remote-allow-origins=*");
		return new ChromeDriver(chromeOptions);
	}

	private static WebDriver getFirefoxDriver() {
		WebDriverManager.getInstance(FirefoxDriver.class).setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("-headless");
		firefoxOptions.addArguments("--width=1920");
		firefoxOptions.addArguments("--height=1080");
		return new FirefoxDriver(firefoxOptions);
	}

	@AfterAll
	public static void finish() {
		countFinish++;
		if (countFinish == NUMBER_OF_SUBCLASSES) {
			webDriver.close();
		}
	}

	protected <T extends AbstractPageComponent> T initElements(Class<T> classx) {
		T result = PageFactory.initElements(webDriver, classx);
		result.setPreffix(getPreffix());
		return result;
	}

	private String getPreffix() {
		return "http://localhost:" + this.port;
	}

}
