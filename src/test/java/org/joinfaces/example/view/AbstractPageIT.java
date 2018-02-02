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

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import org.springframework.boot.web.server.LocalServerPort;

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

	@BeforeClass
	public static void init() {
		if (webDriver == null) {
			String webDriverType = System.getProperty("webDriverType", "firefox");
			if (webDriverType.equals("htmlunit")) {
				webDriver = getHtmlUnitDriver();
			}
			else if (webDriverType.equals("chrome")) {
				webDriver = getChromeDriver();
			}
			else {
				webDriver = getFirefoxDriver();
			}
		}
	}

	private static WebDriver getHtmlUnitDriver() {
		return new HtmlUnitDriver(true);
	}

	private static WebDriver getChromeDriver() {
		ChromeDriverManager.getInstance().setup();
		return new ChromeDriver();
	}

	private static WebDriver getFirefoxDriver() {
		FirefoxDriverManager.getInstance().setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		// setting preference because of https://github.com/mozilla/geckodriver/issues/659
		firefoxProfile.setPreference("dom.file.createInChild", true);
		firefoxOptions.setProfile(firefoxProfile);
		return new FirefoxDriver(firefoxOptions);
	}

	@AfterClass
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
