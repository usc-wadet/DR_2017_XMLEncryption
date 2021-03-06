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
package org.apache.xml.security.stax.securityToken;

import org.apache.xml.security.binding.xmldsig.KeyInfoType;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.stax.config.ConfigurationProperties;
import org.apache.xml.security.stax.ext.InboundSecurityContext;
import org.apache.xml.security.stax.ext.XMLSecurityProperties;
import org.apache.xml.security.utils.ClassLoaderUtils;

/**
 * Factory to create SecurityToken Objects from keys in XML
 *
 * @author $Author: coheigea $
 * @version $Revision: 1720201 $ $Date: 2015-12-15 17:13:17 +0000 (Tue, 15 Dec 2015) $
 */
public abstract class SecurityTokenFactory {

    private static SecurityTokenFactory securityTokenFactory = null;

    public static synchronized SecurityTokenFactory getInstance() throws XMLSecurityException {
        if (securityTokenFactory == null) {
            String stf = ConfigurationProperties.getProperty("securityTokenFactory");
            if (stf == null) {
                throw new XMLSecurityException("algorithm.ClassDoesNotExist",
                                               new Object[] {"null"});
            }
            Class<?> callingClass = ConfigurationProperties.getCallingClass();
            if (callingClass == null) {
                callingClass = SecurityTokenFactory.class;
            }

            try {
                @SuppressWarnings("unchecked")
                Class<SecurityTokenFactory> securityTokenFactoryClass =
                        (Class<SecurityTokenFactory>) ClassLoaderUtils.loadClass(stf, callingClass);
                securityTokenFactory = securityTokenFactoryClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new XMLSecurityException(e, "algorithm.ClassDoesNotExist", new Object[]{stf});
            } catch (InstantiationException e) {
                throw new XMLSecurityException(e, "algorithm.ClassDoesNotExist", new Object[]{stf});
            } catch (IllegalAccessException e) {
                throw new XMLSecurityException(e, "algorithm.ClassDoesNotExist", new Object[]{stf});
            }
        }
        return securityTokenFactory;
    }

    public abstract InboundSecurityToken getSecurityToken(
            KeyInfoType keyInfoType, SecurityTokenConstants.KeyUsage keyUsage,
            XMLSecurityProperties securityProperties, InboundSecurityContext inboundSecurityContext)
            throws XMLSecurityException;
}
