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

import java.io.File;

import org.joinfaces.example.JoinFacesExampleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JoinFacesExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadPageIT extends AbstractPageIT {

	@Test
	public void checkFileUploadElement() {
		FileUploadPage fileUploadPage = initElements(FileUploadPage.class);
		fileUploadPage.navegateTo();

		assertThat(fileUploadPage.isDownloadButtonEnabled())
			.isFalse();
	}

	@Test
	public void submitApplicationYml() {
		FileUploadPage fileUploadPage = initElements(FileUploadPage.class);
		fileUploadPage.navegateTo();

		fileUploadPage.upload(new File("target/classes/application.yml").getAbsolutePath());

		assertThat(fileUploadPage.isDownloadButtonEnabled())
			.isTrue();
	}
}
