/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
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

public class MenuOfPage extends AbstractPageComponent {

	@FindBy(id = "starterMenuItem")
	private WebElement starterMenuItem;

	@FindBy(id = "fileUploadMenuItem")
	private WebElement fileUploadMenuItem;

	@FindBy(id = "customInputMenuItem")
	private WebElement customInputMenuItem;

	@FindBy(id = "myTagMenuItem")
	private WebElement myTagMenuItem;

	@FindBy(id = "hiCCMenuItem")
	private WebElement hiCCMenuItem;

	@FindBy(id = "welcomeConverterMenuItem")
	private WebElement welcomeConverterMenuItem;

	public MenuOfPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String getLocation() {
		return "starter.faces";
	}

	public StarterPage clickStarter() {
		return click(this.starterMenuItem, StarterPage.class);
	}

	public FileUploadPage clickFileUpload() {
		return click(this.fileUploadMenuItem, FileUploadPage.class);
	}

	public CustomInputPage clickCustomInput() {
		return click(this.customInputMenuItem, CustomInputPage.class);
	}

	public HelloTagPage clickHelloTag() {
		return click(this.myTagMenuItem, HelloTagPage.class);
	}

	public HiCCPage clickHiCC() {
		return click(this.hiCCMenuItem, HiCCPage.class);
	}

	public WelcomeConverterPage clickWelcomeConverter() {
		return click(this.welcomeConverterMenuItem, WelcomeConverterPage.class);
	}

	private <T extends AbstractPageComponent> T click(WebElement menuItem, Class<T> classx) {
		menuItem.click();

		T page = initElements(classx);

		page.waitLoad();

		return page;
	}

	@Override
	public void waitLoad() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
