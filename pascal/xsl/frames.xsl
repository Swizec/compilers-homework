<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="frames">
  <html>
    <body>
    <table cellspacing="0">
      <xsl:apply-templates mode="abs"/>
    </table>
    </body>
  </html>
</xsl:template>

<xsl:template match="absnode" mode="abs">
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
        <td align="center" bgcolor="#FFAE0F" colspan="0">
          <table>
            <xsl:apply-templates select="seminfo" mode="sem"/>
          </table>
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFAE0F" colspan="0">
          <table cellspacing="0">
            <td align="center" bgcolor="#FFAE0F" colspan="0">
              <xsl:apply-templates select="semtype"/>
            </td>
          </table>
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFAE0F" colspan="0">
          <table cellspacing="0">
            <td align="center" bgcolor="#FFAE0F" colspan="0">
              <xsl:apply-templates select="frmnode"/>
            </td>
          </table>
        </td>
      </tr>
      <tr>
        <td>
          <table cellspacing="0">
            <xsl:apply-templates mode="abs"/>
          </table>
        </td>
      </tr>
    </table>
  </td>
</xsl:template>

<xsl:template match="abserror" mode="abs">
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

<xsl:template match="seminfo" mode="sem">
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <tr>
    <td align="center" bgcolor="#55A2D9" colspan="0">
      <tt><xsl:value-of select="$kind"/>=<xsl:value-of select="$value"/></tt>
    </td>
  </tr>
</xsl:template>

<xsl:template match="semtype">
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <td valign="top" align="center" bgcolor="#FFAE0F" colspan="0">
    <table cellspacing="0">
      <tr>
        <td align="center" bgcolor="#75C2F9" colspan="0">
          <nobr>
          <xsl:value-of select="$kind"/><xsl:value-of select="$value"/>
          </nobr>
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFAE0F" colspan="0">
          <table cellspacing="0">
            <tr>
              <xsl:apply-templates select="semtype"/>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </td>
</xsl:template>

<xsl:template match="frmnode">
  <td valign="top" align="center" bgcolor="#FFAE0F" colspan="0">
    <table cellspacing="0">
      <tr>
        <td align="center" bgcolor="#02BCE3" colspan="0">
          <nobr>
          <xsl:apply-templates select="frm"/>
          </nobr>
        </td>
      </tr>
    </table>
  </td>
</xsl:template>

<xsl:template match="frm">
      <tr>
        <td align="center" bgcolor="#02BCE3" colspan="0"> 
  <xsl:variable name="kind" select="@kind"/>
  <xsl:variable name="value" select="@value"/>
  <xsl:value-of select="$kind"/>=<xsl:value-of select="$value"/>
        </td>
      </tr>
</xsl:template>

</xsl:stylesheet>
