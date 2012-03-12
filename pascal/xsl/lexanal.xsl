<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="lexanal">
  <html>
    <body>
      <xsl:for-each select="terminal">
        <nobr>
        <xsl:apply-templates select="."/>
        <xsl:text>&#160;</xsl:text>
        </nobr>
        <xsl:text> </xsl:text>
      </xsl:for-each>
    </body>
  </html>
</xsl:template>

<xsl:template match="terminal">
  <xsl:variable name="token" select="@token"/>
  <xsl:variable name="lexeme" select="@lexeme"/>
  <xsl:variable name="line" select="@line"/>
  <xsl:variable name="column" select="@column"/>
  <text style="background-color:#F5D02B">
    <xsl:value-of select="$token"/>
    <xsl:if test="$lexeme">
      <text>&#160;</text>
      <code>
        <xsl:value-of select="$lexeme"/>
      </code>
    </xsl:if>
    (<xsl:value-of select="$line"/>,<xsl:value-of select="$column"/>)
  </text>
</xsl:template>

</xsl:stylesheet>
