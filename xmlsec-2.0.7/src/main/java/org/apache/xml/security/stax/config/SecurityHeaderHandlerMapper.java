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
package org.apache.xml.security.stax.config;

import org.apache.xml.security.utils.ClassLoaderUtils;
import org.apache.xml.security.configuration.HandlerType;
import org.apache.xml.security.configuration.SecurityHeaderHandlersType;

import javax.xml.namespace.QName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Security-header handler mapper
 *
 * @author $Author: coheigea $
 * @version $Revision: 1720201 $ $Date: 2015-12-15 17:13:17 +0000 (Tue, 15 Dec 2015) $
 */
public class SecurityHeaderHandlerMapper {

    private static Map<QName, Class<?>> handlerClassMap;

    private SecurityHeaderHandlerMapper() {
    }

    protected static synchronized void init(SecurityHeaderHandlersType securityHeaderHandlersType,
            Class<?> callingClass) throws Exception {
        List<HandlerType> handlerList = securityHeaderHandlersType.getHandler();
        handlerClassMap = new HashMap<QName, Class<?>>(handlerList.size() + 1);
        for (int i = 0; i < handlerList.size(); i++) {
            HandlerType handlerType = handlerList.get(i);
            QName qName = new QName(handlerType.getURI(), handlerType.getNAME());
            handlerClassMap.put(qName,
                    ClassLoaderUtils.loadClass(handlerType.getJAVACLASS(), callingClass));
        }
    }

    public static Class<?> getSecurityHeaderHandler(QName name) {
        return handlerClassMap.get(name);
    }
}
