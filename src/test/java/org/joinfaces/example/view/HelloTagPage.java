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

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelloTagPage extends AbstractPageComponent {

	public HelloTagPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/helloTag.jsf";
	}

	private By getHelloWorldDivBy() {
		return By.id("outputTextTagId");
	}

	public String getHelloWorldText() {
		return webDriver.findElement(getHelloWorldDivBy()).getText();
	}

	public HelloTagPage waitLoad() {
		new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(
			getHelloWorldDivBy()));

		return this;
	}

}
