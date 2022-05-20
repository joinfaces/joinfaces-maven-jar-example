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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomeConverterPage extends AbstractPageComponent {

	@FindBy(name = "welcomeInput")
	private WebElement welcomeInput;

	@FindBy(name = "welcomeButton")
	private WebElement welcomeButton;

	public WelcomeConverterPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/welcomeConverter.jsf";
	}

	public void submit(String message) {
		this.welcomeInput.sendKeys(message);

		this.welcomeButton.submit();

		By outputTextBy = getOutputTextBy();
		String expectedValue = message + " welcome!";

		new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBe(outputTextBy, expectedValue));
	}

	private By getOutputTextBy() {
		return By.id("welcomeOutput");
	}

	public String getOutputText() {
		return webDriver.findElement(getOutputTextBy()).getText();
	}

	public WelcomeConverterPage waitLoad() {
		new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(
			getOutputTextBy()));

		return this;
	}

}
