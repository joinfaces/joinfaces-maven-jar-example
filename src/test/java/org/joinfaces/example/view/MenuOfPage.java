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

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuOfPage extends AbstractPageComponent {

	@FindBy(xpath = "//a[@class='ui-menuitem-link ui-corner-all']")
	private	List<WebElement> menuAnchors;

	public MenuOfPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/";
	}

	public StarterPage clickStarter() {
		this.menuAnchors.get(0).click();

		StarterPage page = initElements(StarterPage.class);

		return page.waitLoad();
	}

	public FileUploadPage clickFileUpload() {
		this.menuAnchors.get(1).click();

		FileUploadPage page = initElements(FileUploadPage.class);

		return page.waitLoad();
	}

	public CustomInputPage clickCustomInput() {
		this.menuAnchors.get(2).click();

		CustomInputPage page = initElements(CustomInputPage.class);

		return page.waitLoad();
	}

	public HelloTagPage clickHelloTag() {
		this.menuAnchors.get(3).click();

		HelloTagPage page = initElements(HelloTagPage.class);

		return page.waitLoad();
	}

	public HiCCPage clickHiCC() {
		this.menuAnchors.get(4).click();

		HiCCPage page = initElements(HiCCPage.class);

		return page.waitLoad();
	}

	public WelcomeConverterPage clickWelcomeConverter() {
		this.menuAnchors.get(5).click();

		WelcomeConverterPage page = initElements(WelcomeConverterPage.class);

		return page.waitLoad();
	}

}
