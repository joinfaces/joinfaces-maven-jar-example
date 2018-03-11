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

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageComponent {

	protected final WebDriver webDriver;

	@Setter
	private String preffix;

	public AbstractPageComponent(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	protected abstract String url();

	public void navegateTo() {
		this.webDriver.navigate().to(this.preffix + url());
	}

	public <T extends AbstractPageComponent> T initElements(Class<T> classx) {
		T result = PageFactory.initElements(this.webDriver, classx);
		result.setPreffix(this.preffix);
		return result;
	}

}
