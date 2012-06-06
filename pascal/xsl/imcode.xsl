<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="imcode">
  <html>
    <body>
    <table cellspacing="0">
      <xsl:apply-templates/>
    </table>
    </body>
  </html>
</xsl:template>

<xsl:template match="datachunk">
  <xsl:variable name="label" select="@label"/>
  <xsl:variable name="size" select="@size"/>
  <tr>
    <td>
      <table>
        <tr>
          <td align="center" bgcolor="#FFAE0F" colspan="0">
            <nobr>DATA CHUNK: </nobr>
            <br/>
            label=<xsl:value-of select="$label"/>
            <br/>
            size=<xsl:value-of select="$size"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
    </td>
  </tr>
</xsl:template>

<xsl:template match="constchunk">
  <xsl:variable name="label" select="@label"/>
  <xsl:variable name="value" select="@value"/>
  <tr>
    <td>
      <table>
        <tr>
          <td align="center" bgcolor="#FFAE0F" colspan="0">
            <nobr>CONST CHUNK: </nobr>
            <br/>
            label=<xsl:value-of select="$label"/>
            <br/>
            value="<xsl:value-of select="$value"/>"
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
    </td>
  </tr>
</xsl:template>

<xsl:template match="codechunk">
  <xsl:variable name="value" select="@value"/>
  <tr>
    <td>
      <table colspan="0">
        <tr>
          <td align="center" bgcolor="#FFAE0F" colspan="0">
            <nobr>CODE CHUNK: <xsl:value-of select="$value"/></nobr>
            <br/>
            <xsl:apply-templates select="frmnode"/>
          </td>
          <td>
            <table>
              <xsl:apply-templates select="imcnode"/>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
    </td>
  </tr>
</xsl:template>

<xsl:template match="frmnode">
  <table cellspacing="0">
    <xsl:apply-templates select="frm"/>
  </table>
</xsl:template>

<xsl:template match="frm">
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <tr>
    <td align="center" bgcolor="#52C461" colspan="0">
      <xsl:value-of select="$kind"/>
        <xsl:if test="$value != ''">
          <xsl:text> </xsl:text>
          <xsl:value-of select="$value"/>
        </xsl:if>
    </td>
  </tr>
</xsl:template>

<xsl:template match="imcnode">
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <td valign="top">
    <table cellspacing="0">
      <tr>
        <td align="center" bgcolor="#91B8D2" colspan="10000">
          <nobr>
          <xsl:value-of select="$kind"/>
          <xsl:if test="$value != ''">
            <xsl:text> </xsl:text>
            <xsl:value-of select="$value"/>
          </xsl:if>
          </nobr>
        </td>
      </tr>
      <tr>
        <xsl:apply-templates/>
      </tr>
    </table>
  </td>
</xsl:template>

</xsl:stylesheet>
