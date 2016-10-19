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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StarterPage extends AbstractPageComponent {

	@FindBy(xpath = "//input[@value='Jetty']/..")
	private WebElement jettyRadioButtonInput;

	@FindBy(xpath = "//input[@value='MyFaces']/..")
	private WebElement myfacesRadioButtonInput;

	@FindBy(xpath = "//input[@value='ButterFaces']/..")
	private WebElement butterfacesRadioButtonInput;

	public StarterPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/index.jsf?content=starter";
	}

	private void click(WebElement webElement, String technology)  {
		By panelHeaderSpanBy = By.xpath("//span[@class='ui-panel-title']");

		webElement.click();

		new WebDriverWait(webDriver, 10000).until(
			ExpectedConditions.textToBePresentInElementLocated(panelHeaderSpanBy, technology));
	}

	public void clickJetty() {
		click(jettyRadioButtonInput, "jetty");
	}

	public void clickMyFaces() {
		click(myfacesRadioButtonInput, "myfaces");
	}

	public void clickButterFaces() {
		click(butterfacesRadioButtonInput, "butterfaces");
	}

	public String getPanelHeaderText() {
		By panelHeaderSpanBy = By.xpath("//span[@class='ui-panel-title']");
		return webDriver.findElement(panelHeaderSpanBy).getText();
	}

	public String getAdminRoleLabelText() {
		return webDriver.findElement(By.id("labelRoleAdmin")).getText();
	}

	private By getJsfUtilityLibrariesPanelBy() {
		return By.id("panelJsfUtilityLibraries");
	}

	public StarterPage waitLoad() {
		new WebDriverWait(webDriver, 10000).until(ExpectedConditions.visibilityOfElementLocated(
			getJsfUtilityLibrariesPanelBy()));

		return this;
	}
}
