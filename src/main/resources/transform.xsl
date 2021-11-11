<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="TRANSFORMATION_DATETIME"/>
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="root">
        <order>
            <orderId>
                <xsl:value-of select="concat(id,'_', batch)"/>
            </orderId>
            <documentDateTime>
                <xsl:value-of select="$TRANSFORMATION_DATETIME"/>
            </documentDateTime>
            <orderRows>
                <xsl:for-each select="rows/row">
                    <orderRow>
                        <rowNumber>
                            <xsl:value-of select="position()"/>
                        </rowNumber>
                        <description>
                            <xsl:value-of select="description"/>
                        </description>
                    </orderRow>
                </xsl:for-each>
            </orderRows>
        </order>
    </xsl:template>
</xsl:stylesheet>