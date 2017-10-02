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
package org.apache.xml.security.stax.impl.util;

import java.util.UUID;

/**
 * @author $Author: coheigea $
 * @version $Revision: 1354896 $ $Date: 2012-06-28 11:11:05 +0100 (Thu, 28 Jun 2012) $
 */
public class IDGenerator {

    public static String generateID(String prefix) {
        String id = UUID.randomUUID().toString();
        if (prefix != null) {
            return prefix + id;
        } else {
            //always prepend a constant character to get a schema-valid id!:
            return "G" + id;
        }
    }
}
