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

package org.joinfaces.example;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.BeanCreationException;
@SuppressFBWarnings("THROWS_METHOD_THROWS_CLAUSE_THROWABLE")
public class SecurityConfigIT {

	@Test
	public void exceptionOnConfigureNull() {
		SecurityConfig securityConfig = new SecurityConfig();
		Assertions.assertThrows(BeanCreationException.class, () -> securityConfig.configure(null));
	}

}
