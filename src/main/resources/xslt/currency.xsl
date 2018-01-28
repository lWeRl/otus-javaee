<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <div class="currency-chart">
            <xsl:for-each select="ValCurs/Valute">
                <div class="currency">
                    <div class="name">
                        <xsl:value-of select="Name"/>
                    </div>
                    <div class="rate">
                        <xsl:value-of select="format-number(number(translate(string(Value), ',', '.')) div number(Nominal), '####.#####')"/>
                    </div>
                </div>
            </xsl:for-each>
            
        </div>
    </xsl:template>

</xsl:stylesheet>