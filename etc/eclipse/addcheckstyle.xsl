<?xml version="1.0"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements. See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership. The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License. You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied. See the License for the
  specific language governing permissions and limitations
  under the License.
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    <xsl:strip-space elements="*"/>
    
    <xsl:param name="checkstyleconfig"/>

    <xsl:template match="*">
        <xsl:copy>
            <xsl:copy-of select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="checkstyle-configurations">
        <xsl:copy>
            <xsl:copy-of select="@*"/>
            <xsl:apply-templates/>

            <xsl:choose>
                <xsl:when test="not(check-configuration/@name='XmlSchema Checks')">
                    <check-configuration name="XmlSchema Checks" type="external" description="">
                        <xsl:attribute name="location"><xsl:value-of select="$checkstyleconfig"/></xsl:attribute>
                    </check-configuration>
                </xsl:when>
            </xsl:choose>
        </xsl:copy>
    </xsl:template>

</xsl:stylesheet>

