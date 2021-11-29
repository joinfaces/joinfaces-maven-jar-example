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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * jsf component converter.
 *
 * @author Marcelo Fernandes
 */
@Component
public class JoinFacesStarterConverter implements Converter<JoinFacesStarter> {

	@Autowired
	private JoinFacesStarterService joinFacesStarterService;

	@Override
	public JoinFacesStarter getAsObject(FacesContext fc, UIComponent uic, String value) {
		return this.joinFacesStarterService.findByName(value);
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, JoinFacesStarter object) {
		if (object != null) {
			return object.getName();
		}
		return null;
	}

}
