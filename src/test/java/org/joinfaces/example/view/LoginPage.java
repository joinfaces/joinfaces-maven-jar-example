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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPageComponent {

	@FindBy(name = "username")
	private WebElement usernameInput;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(name = "submit")
	private WebElement buttonSubmit;

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public String getLocation() {
		return "login.faces";
	}

	public StarterPage login(String username, String password) {
		this.usernameInput.sendKeys(username);
		this.passwordInput.sendKeys(password);

		this.buttonSubmit.click();

		StarterPage starterPage = initElements(StarterPage.class);

		return starterPage.waitLoad();
	}

	public String getTitle() {
		return webDriver.getTitle();
	}
}
