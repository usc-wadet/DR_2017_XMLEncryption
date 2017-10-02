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
package org.apache.xml.security.stax.ext;

import org.apache.xml.security.exceptions.XMLSecurityException;

/**
 * Exception when configuration errors are detected
 *
 * @author $Author: coheigea $
 * @version $Revision: 1742958 $ $Date: 2016-05-09 15:35:36 +0100 (Mon, 09 May 2016) $
 */
public class XMLSecurityConfigurationException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = -214211575738383300L;

    public XMLSecurityConfigurationException(Exception originalException) {
        super(originalException);
    }

    public XMLSecurityConfigurationException(String msgID) {
        super(msgID);
    }

    public XMLSecurityConfigurationException(String msgID, Object... exArgs) {
        super(msgID, exArgs);
    }

    public XMLSecurityConfigurationException(Exception originalException, String msgID) {
        super(originalException, msgID);
    }
    
    @Deprecated
    public XMLSecurityConfigurationException(String msgID, Exception originalException) {
        this(originalException, msgID);
    }
}
