<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="synanal">
  <html>
    <body>
      <xsl:for-each select="stack">
        <nobr>
        <xsl:for-each select="state">
          <xsl:apply-templates select="."/>
        </xsl:for-each>
        </nobr>
        <br/>
      </xsl:for-each>
    </body>
  </html>
</xsl:template>

<xsl:template match="state">
  <xsl:variable name="num" select="@num"/>
  <text>
    (<xsl:value-of select="$num"/>,<xsl:apply-templates/>)
  </text>
</xsl:template>

<xsl:template match="nonterminal">
  <xsl:variable name="value" select="@value"/>
  <text style="background-color:#FFAE0F">
    <xsl:value-of select="$value"/>
  </text>
  
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
    <xsl:comment>
      (<xsl:value-of select="$line"/>,<xsl:value-of select="$column"/>)
    </xsl:comment>
  </text>
</xsl:template>

</xsl:stylesheet>
