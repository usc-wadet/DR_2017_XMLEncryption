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
package org.apache.xml.security.test.dom.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * This package is responsible for retrieving test vectors for our unit tests.
 *
 * @author Christian Geuer-Pollmann
 * $todo$ Currently, the test vectors are in the file system under the data/ directory.
 * It is planned to put them all into a single jar/zip which is deployed with the library.
 */
public class TestVectorResolver implements EntityResolver {

    static org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(TestVectorResolver.class);

    /** Field alreadyInitialized */
    static boolean alreadyInitialized = false;

    /** Field zis */
    static java.util.zip.ZipInputStream zis = null;

    /** Field vectors */
    static java.util.Map<String, byte[]> vectors = null;

    static {
        org.apache.xml.security.Init.init();
        TestVectorResolver.init();
    }

    /** Field _firstEntityResolved */
    boolean _firstEntityResolved = false;

    /** Field _firstEntitySystemId */
    String _firstEntitySystemIdDirectory = null;

    /**
     * Method init
     *
     */
    public static void init() {
        String thisClass =
            "org.apache.xml.security.test.resource.TestVectorResolver";
        String testVectorFile = "testvectors.zip";

        if (!TestVectorResolver.alreadyInitialized) {
            TestVectorResolver.alreadyInitialized = true;
            TestVectorResolver.vectors = new java.util.HashMap<String, byte[]>(30);

            try {
                zis =
                    new java.util.zip.ZipInputStream(
                         Class.forName(thisClass).getResourceAsStream(testVectorFile)
                     );

                java.util.zip.ZipEntry ze = null;
                while ((ze = zis.getNextEntry()) != null) {
                    if (!ze.isDirectory()) {
                        byte data[] =
                            org.apache.xml.security.utils.JavaUtils.getBytesFromStream(zis);

                        TestVectorResolver.vectors.put(ze.getName(), data);
                        log.debug("Contents of " + thisClass + "/" + testVectorFile
                                  + "#" + ze.getName() + " " + data.length + " bytes");
                    }
                }
            } catch (ClassNotFoundException e) {
                //
            }
            catch (IOException e) {
                //
            }
        }
    }

    /**
     * Method getCurrentDir
     *
     *
     * @throws IOException
     */
    private String getCurrentDir() throws IOException {

        String currentDir = new File(".").getCanonicalPath();

        currentDir = currentDir.replace(File.separatorChar, '/');
        currentDir = "file:///" + currentDir + "/";

        return currentDir;
    }

    /**
     * Method getFileName
     *
     * @param systemId
     *
     * @throws IOException
     */
    private String getFileName(String systemId) throws IOException {

        // clean up file name
        String currentDir = getCurrentDir();

        if (systemId.startsWith(currentDir)) {
            return systemId.substring(currentDir.length());
        } else {
            return systemId;
        }
    }

    /**
     * Method getFilePath
     *
     * @param systemId
     *
     * @throws IOException
     */
    private String getFilePath(String systemId) throws IOException {

        String t = new File(systemId).getCanonicalPath();

        t = t.replace(File.separatorChar, '/');
        t = "file:///" + t;

        String currentDir = getCurrentDir();

        if (t.startsWith(currentDir)) {
            t = t.substring(currentDir.length());
        }

        t = t.substring(0, t.lastIndexOf("/"));

        return t;
    }

    /**
     * Method set1stSystemId
     *
     * @param systemId
     * @throws IOException
     */
    private void set1stSystemId(String systemId) throws IOException {

        this._firstEntitySystemIdDirectory = getFilePath(systemId);

        log.debug("this._firstEntitySystemIdDirectory = "
                  + this._firstEntitySystemIdDirectory);
    }

    /**
     * Method resolveEntity
     *
     * @param publicId
     * @param systemId
     *
     */
    public InputSource resolveEntity(String publicId, String systemId) {
        try {
            if (!this._firstEntityResolved) {
                this.set1stSystemId(systemId);
            }

            systemId = this.getFileName(systemId);

            log.debug("publicId=\"" + publicId + "\" systemId=\"" + systemId
                      + "\"");

            // InputStream result = this.getInputStream(systemId);
            // return new InputSource(result);
            return new InputSource(new FileInputStream(systemId));
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

}
