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

	@FindBy(id = "tabView:jetty")
	private WebElement jettyRadioButtonInput;

	@FindBy(xpath = "//a[text()='Servlet Container']")
	private WebElement servletContainerTab;

	@FindBy(id = "tabView:myfaces")
	private WebElement myfacesRadioButtonInput;

	@FindBy(xpath = "//a[text()='JSF Implementation']")
	private WebElement jsfImplementationTab;

	@FindBy(xpath = "//a[text()='JSF Components']")
	private WebElement jsfComponentsTab;

	@FindBy(xpath = "//a[text()='Add to your pom.xml']")
	private WebElement pomTab;

	public StarterPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/index.jsf?content=starter";
	}

	private void click(WebElement webElement, String technology, By tabHeaderBy)  {
		webElement.click();

		new WebDriverWait(webDriver, 10000).until(
			ExpectedConditions.textToBePresentInElementLocated(tabHeaderBy, technology));
	}

	public void clickServletContainerTab() {
		this.servletContainerTab.click();
	}

	public void clickJetty() {
		click(this.jettyRadioButtonInput, "Jetty", getServletContainerTabHeaderBy());
	}

	public void clickJsfImplementationTab() {
		this.jsfImplementationTab.click();
	}

	public void clickMyFaces() {
		click(this.myfacesRadioButtonInput, "MyFaces", getJsfImplementationTabHeaderBy());
	}

	public void clickJsfComponentsTab() {
		this.jsfComponentsTab.click();
	}

	private WebElement jsfComponentRadioButtonInput(int index) {
		return webDriver.findElement(By.xpath("//*[@id='tabView:jsfComponents']/div[2]/table/tbody/tr[" + index + "]/td[1]/div/div/span"));
	}

	public void clickPrimeFaces() {
		click(jsfComponentRadioButtonInput(1), "PrimeFaces", getJsfComponentsTabHeaderBy());
	}

	public void clickBootsFaces() {
		click(jsfComponentRadioButtonInput(2), "BootsFaces", getJsfComponentsTabHeaderBy());
	}

	public void clickButterFaces() {
		click(jsfComponentRadioButtonInput(3), "ButterFaces", getJsfComponentsTabHeaderBy());
	}

	public void clickAngularFaces() {
		click(jsfComponentRadioButtonInput(4), "AngularFaces", getJsfComponentsTabHeaderBy());
	}

	public void clickRichFaces() {
		click(jsfComponentRadioButtonInput(5), "RichFaces", getJsfComponentsTabHeaderBy());
	}

	public void clickPomTab() {
		this.pomTab.click();
	}

	private By getTabHeaderBy(String tab) {
		return By.xpath("//*[@id='tabView:panel" + tab + "_header']/span");
	}

	private By getServletContainerTabHeaderBy() {
		return getTabHeaderBy("ServletContainer");
	}

	private By getJsfImplementationTabHeaderBy() {
		return getTabHeaderBy("JsfImplementation");
	}

	private By getJsfComponentsTabHeaderBy() {
		return getTabHeaderBy("JsfComponents");
	}

	public String getServletContainerTabHeaderText() {
		return webDriver.findElement(getServletContainerTabHeaderBy()).getText();
	}

	public String getJsfImplementationTabHeaderText() {
		return webDriver.findElement(getJsfImplementationTabHeaderBy()).getText();
	}

	public String getJsfComponentsTabHeaderText() {
		return webDriver.findElement(getJsfComponentsTabHeaderBy()).getText();
	}

	public String getAdminRoleLabelText() {
		return webDriver.findElement(By.id("labelRoleAdmin")).getText();
	}

	public String getPomContent() {
		return webDriver.findElement(By.xpath("//*[@id='tabView:panelPom_content']/div/div[6]/div[1]/div/div")).getText();
	}

	public StarterPage waitLoad() {
		new WebDriverWait(webDriver, 10000).until(ExpectedConditions.visibilityOfElementLocated(
			getServletContainerTabHeaderBy()));

		return this;
	}
}
