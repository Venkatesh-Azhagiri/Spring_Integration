/*
 * Copyright 2006 the original author or authors.
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

package org.springframework.ws.samples.echo.service.impl;

import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.ws.samples.echo.service.EchoService;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class EchoServiceImpl implements EchoService, InitializingBean {

	private static final String NAMESPACE_URI = "http://www.springframework.org/spring-ws/samples/echo";
	private Properties namespaces;
	private DocumentBuilder documentBuilder;

	@ServiceActivator
	public Document echo(Source payload) {
		Jaxp13XPathTemplate xpathTemplate = new Jaxp13XPathTemplate();
		xpathTemplate.setNamespaces(namespaces);
		String message = xpathTemplate.evaluateAsString("//echo:echoRequest/text()",
				payload);
		Document response = documentBuilder.newDocument();
		Element responseElement = response.createElementNS(NAMESPACE_URI,
				"echoResponse");
		Text responseText = response.createTextNode(message);
		responseElement.appendChild(responseText);
		response.appendChild(responseElement);
		return response;
	}

	public void afterPropertiesSet() throws Exception {
		namespaces = new Properties();
		namespaces.setProperty("echo", NAMESPACE_URI);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}
}
