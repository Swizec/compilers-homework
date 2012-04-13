<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="abstree">
  <html>
    <body>
    <table cellspacing="0">
      <xsl:apply-templates/>
    </table>
    </body>
  </html>
</xsl:template>

<xsl:template match="absnode">
  <xsl:variable name="pos" select="@pos"/>
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <td valign="top">
    <table cellspacing="0">
      <tr>
        <td align="center" bgcolor="#FFAE0F" colspan="0">
          <nobr>
          <b><xsl:value-of select="$kind"/></b>
          <xsl:if test="$value != ''">
            <xsl:text> </xsl:text>
            <tt><xsl:value-of select="$value"/></tt>
          </xsl:if>
          <xsl:if test="$pos != ''">
            <xsl:text> </xsl:text>
            <xsl:value-of select="$pos"/>
          </xsl:if>
          </nobr>
        </td>
      </tr>
      <tr>
        <td>
          <table cellspacing="0">
            <xsl:apply-templates/>
          </table>
        </td>
      </tr>
    </table>
  </td>
</xsl:template>

<xsl:template match="abserror">
  <xsl:variable name="kind" select="@kind"/>
  <td valign="top">
    <table cellspacing="0">
      <tr>
        <td align="center" bgcolor="#FF0000" colspan="0">
          <nobr>
          <b>ERROR:</b><tt><xsl:value-of select="$kind"/></tt>
          </nobr>
        </td>
      </tr>
      <tr>
        <td>
          <table cellspacing="0">
            <xsl:apply-templates select="absnode"/>
          </table>
        </td>
      </tr>
    </table>
  </td>
</xsl:template>

</xsl:stylesheet>
