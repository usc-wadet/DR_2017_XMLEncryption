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

/**
 * Unchecked Exception to mark uncaught-exceptions in threads
 *
 * @author $Author: coheigea $
 * @version $Revision: 1720201 $ $Date: 2015-12-15 17:13:17 +0000 (Tue, 15 Dec 2015) $
 */
public class UncheckedXMLSecurityException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3440022764012033141L;

    public UncheckedXMLSecurityException(String message) {
        super(message);
    }

    public UncheckedXMLSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedXMLSecurityException(Throwable cause) {
        super(cause);
    }
}
