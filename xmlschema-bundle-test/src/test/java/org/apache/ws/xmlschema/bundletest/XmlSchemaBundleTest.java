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

package org.apache.ws.xmlschema.bundletest;

import junit.framework.Assert;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.apache.ws.commons.schema.internal.DummyInternalClass;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;


/**
 *
 */
@RunWith(JUnit4TestRunner.class)
public class XmlSchemaBundleTest {

    @Configuration
    public static Option[] configuration()
    {
       return CoreOptions.options(CoreOptions.felix(), CoreOptions.provision(
         CoreOptions.mavenBundle().groupId("org.apache.ws.xmlschema").artifactId("xmlschema-core")
       ));
    }

    @Test(expected = java.lang.NoClassDefFoundError.class)
    public void testInternalsExcluded() {
        new DummyInternalClass();
    }

    @Test
    public void testSchemaAvailable() {
        XmlSchemaCollection collection = new XmlSchemaCollection();
        Assert.assertNotNull(collection);
    }
}
