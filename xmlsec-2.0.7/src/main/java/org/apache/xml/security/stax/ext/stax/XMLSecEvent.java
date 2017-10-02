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
package org.apache.xml.security.stax.ext.stax;

import javax.xml.namespace.QName;
import javax.xml.stream.events.XMLEvent;
import java.util.List;

/**
 * @author $Author: giger $
 * @version $Revision: 1399606 $ $Date: 2012-10-18 13:17:41 +0100 (Thu, 18 Oct 2012) $
 */
public interface XMLSecEvent extends XMLEvent {

    void setParentXMLSecStartElement(XMLSecStartElement xmlSecStartElement);

    XMLSecStartElement getParentXMLSecStartElement();

    int getDocumentLevel();

    void getElementPath(List<QName> list);

    List<QName> getElementPath();

    XMLSecStartElement getStartElementAtLevel(int level);

    @Override
    XMLSecStartElement asStartElement();

    @Override
    XMLSecEndElement asEndElement();

    @Override
    XMLSecCharacters asCharacters();
}
