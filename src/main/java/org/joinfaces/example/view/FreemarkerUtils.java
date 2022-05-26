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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Freemarker utility class to merge map and templates.
 *
 * @author Marcelo Fernandes
 */
@Component
@ApplicationScope
public class FreemarkerUtils {

	@Autowired
	private Configuration configuration;

	/**
	 * Generates the document as string using the map and templateName.
	 * @param map map of objects
	 * @param templateName name of template
	 * @return merged string
	 * @throws IOException io exception
	 * @throws TemplateException template exception
	 */
	public String mergeTemplate(Map<String, Object> map, String templateName) throws TemplateException, IOException {
		Template template = this.configuration.getTemplate(templateName, "UTF-8");

		return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
	}

}
