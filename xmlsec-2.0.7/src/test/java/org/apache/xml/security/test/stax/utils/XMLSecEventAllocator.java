/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.xml.security.test.stax.utils;

import org.apache.xml.security.stax.ext.stax.XMLSecEvent;
import org.apache.xml.security.stax.ext.stax.XMLSecEventFactory;
import org.apache.xml.security.stax.ext.stax.XMLSecStartElement;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

/**
 * <p/>
 * An extended XMLEventAllocator to collect namespaces and C14N relevant attributes
 *
 * @author $Author: coheigea $
 * @version $Revision: 1686457 $ $Date: 2015-06-19 18:04:52 +0100 (Fri, 19 Jun 2015) $
 */
public class XMLSecEventAllocator implements XMLEventAllocator {

    private XMLEventAllocator xmlEventAllocator;
    private XMLSecStartElement parentXmlSecStartElement;

    public XMLSecEventAllocator() throws Exception {
        xmlEventAllocator = com.ctc.wstx.evt.DefaultEventAllocator.getDefaultInstance();
    }

    @Override
    public XMLEventAllocator newInstance() {
        try {
            return new XMLSecEventAllocator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XMLEvent allocate(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        XMLSecEvent xmlSecEvent = XMLSecEventFactory.allocate(xmlStreamReader, parentXmlSecStartElement);
        switch (xmlSecEvent.getEventType()) {
            case XMLStreamConstants.START_ELEMENT:
                parentXmlSecStartElement = (XMLSecStartElement) xmlSecEvent;
                break;
            case XMLStreamConstants.END_ELEMENT:
                if (parentXmlSecStartElement != null) {
                    parentXmlSecStartElement = parentXmlSecStartElement.getParentXMLSecStartElement();
                }
                break;
        }
        return xmlSecEvent;
    }

    @Override
    public void allocate(XMLStreamReader reader, XMLEventConsumer consumer) throws XMLStreamException {
        xmlEventAllocator.allocate(reader, consumer);
    }
}
