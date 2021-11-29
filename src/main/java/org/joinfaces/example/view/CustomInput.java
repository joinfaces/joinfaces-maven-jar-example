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
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Custom input component.
 * @author Marcelo Fernandes
 */
@FacesComponent(createTag = true, namespace = "http://joinfaces.org/example",
	tagName = "custom-input", value = "org.joinfaces.example.view.CustomInput")
public class CustomInput extends UIInput {

	private static final String INPUT = "input";
	private static final String TYPE = "type";
	private static final String VALUE = "value";

	@Override
	public void decode(FacesContext context) {
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String clientId = getClientId(context);
		char sep = UINamingContainer.getSeparatorChar(context);
		String submitted_hello_msg = requestMap.get(clientId + sep + "inputfield");
		setSubmittedValue(submitted_hello_msg);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		String clientId = getClientId(context);
		char sep = UINamingContainer.getSeparatorChar(context);
		encodeInputField(context, clientId + sep + "inputfield");
		encodeSubmitButton(context, clientId + sep + "submit");
		encodeOutputField(context);
	}

	private void encodeInputField(FacesContext context, String clientId) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement(INPUT, this);
		writer.writeAttribute(TYPE, "text", null);
		writer.writeAttribute("name", clientId, "clientId");
		writer.writeAttribute("size", "30", null);
		Object value = getValue();
		if (value != null) {
			writer.writeAttribute(VALUE, value.toString(), VALUE);
		}
		writer.writeAttribute("size", "6", null);
		writer.endElement(INPUT);
	}

	private void encodeSubmitButton(FacesContext context, String clientId) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement(INPUT, this);
		writer.writeAttribute(TYPE, "Submit", null);
		writer.writeAttribute("name", clientId, "clientId");
		writer.writeAttribute(VALUE, "Click Me!", null);
		writer.endElement(INPUT);
	}

	private void encodeOutputField(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String hellomsg = (String) getAttributes().get(VALUE);
		writer.startElement("p", this);
		writer.writeText("You entered: " + hellomsg, null);
		writer.endElement("p");
	}

}
