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

public class StarterPage extends AbstractPageComponent {

	@FindBy(id = "tabView:jetty")
	private WebElement jettyRadioButtonInput;

	@FindBy(xpath = "//a[text()='Servlet Container']")
	private WebElement servletContainerTab;

	@FindBy(id = "tabView:myfaces")
	private WebElement myfacesRadioButtonInput;

	@FindBy(xpath = "//a[text()='JSF Implementation']")
	private WebElement jsfImplementationTab;

	@FindBy(xpath = "//a[text()='JoinFaces Components']")
	private WebElement joinFacesComponentsTab;

	@FindBy(xpath = "//a[text()='JoinFaces Addons']")
	private WebElement joinFacesAddonsTab;

	@FindBy(xpath = "//a[text()='Add to your pom.xml']")
	private WebElement pomTab;

	public StarterPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	protected String url() {
		return "/starter.jsf";
	}

	private void click(WebElement webElement, String technology, By tabHeaderBy)  {
		webElement.click();

		new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
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

	public void clickJoinFacesComponentsTab() {
		this.joinFacesComponentsTab.click();
	}

	private WebElement joinFacesComponentRadioButtonInput(int index) {
		return webDriver.findElement(By.xpath("//*[@id='tabView:joinFacesComponents']/div[2]/table/tbody/tr[" + index + "]/td[1]/div/div"));
	}

	public void clickSecurity() {
		click(joinFacesComponentRadioButtonInput(1), "Security", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickPrimeFaces() {
		click(joinFacesComponentRadioButtonInput(2), "PrimeFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickBootsFaces() {
		click(joinFacesComponentRadioButtonInput(3), "BootsFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickButterFaces() {
		click(joinFacesComponentRadioButtonInput(4), "ButterFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickAdminFaces() {
		click(joinFacesComponentRadioButtonInput(5), "AdminFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickOmnifaces1() {
		click(joinFacesComponentRadioButtonInput(6), "OmniFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickOmnifaces3() {
		click(joinFacesComponentRadioButtonInput(7), "OmniFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickIceFaces() {
		click(joinFacesComponentRadioButtonInput(8), "IceFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickAngularFaces() {
		click(joinFacesComponentRadioButtonInput(9), "AngularFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickRichFaces() {
		click(joinFacesComponentRadioButtonInput(10), "RichFaces", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickRichFacesAyg() {
		click(joinFacesComponentRadioButtonInput(11), "RichFaces-Ayg", getJoinFacesComponentsTabHeaderBy());
	}

	public void clickTobago() {
		click(joinFacesComponentRadioButtonInput(12), "Tobago", getJoinFacesComponentsTabHeaderBy());
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

	private By getJoinFacesComponentsTabHeaderBy() {
		return getTabHeaderBy("JoinFacesComponents");
	}

	private By getJoinFacesAddonsTabHeaderBy() {
		return getTabHeaderBy("JoinFacesAddons");
	}

	public String getServletContainerTabHeaderText() {
		return webDriver.findElement(getServletContainerTabHeaderBy()).getText();
	}

	public String getJsfImplementationTabHeaderText() {
		return webDriver.findElement(getJsfImplementationTabHeaderBy()).getText();
	}

	public String getJoinFacesComponentsTabHeaderText() {
		return webDriver.findElement(getJoinFacesComponentsTabHeaderBy()).getText();
	}

	public String getAdminRoleLabelText() {
		return webDriver.findElement(By.id("labelRoleAdmin")).getText();
	}

	public String getPomContent() {
		return webDriver.findElement(By.xpath("//*[@id='tabView:panelPom_content']/div/div[6]/div[1]/div/div")).getText();
	}

	public StarterPage waitLoad() {
		new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(
			getServletContainerTabHeaderBy()));

		return this;
	}

	public void clickJoinFacesAddonsTab() {
		this.joinFacesAddonsTab.click();
	}

	public void clickRewrite() {
		click(joinFacesAddonsRadioButtonInput(1), "Rewrite", getJoinFacesAddonsTabHeaderBy());
	}

	public void clickWeld() {
		click(joinFacesAddonsRadioButtonInput(2), "Weld", getJoinFacesAddonsTabHeaderBy());
	}

	private WebElement joinFacesAddonsRadioButtonInput(int index) {
		return webDriver.findElement(By.xpath("//*[@id='tabView:joinFacesAddons']/div[2]/table/tbody/tr[" + index + "]/td[1]/div/div"));
	}

	public String getJoinFacesAddonsTabHeaderText() {
		return webDriver.findElement(getJoinFacesAddonsTabHeaderBy()).getText();
	}
}
