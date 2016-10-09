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
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuOfPage {

	private WebDriver webDriver;

	@FindBy(xpath = "//a[@class='ui-menuitem-link ui-corner-all']")
	private	List<WebElement> menuAnchors;
	//= page.findElements(By.xpath()).get(1);

	public MenuOfPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void clickFileUpload() throws IOException {
		menuAnchors.get(1).click();

		new WebDriverWait(webDriver, 10000).until(ExpectedConditions.presenceOfElementLocated(By.id("fileUpload")));
	}

}
