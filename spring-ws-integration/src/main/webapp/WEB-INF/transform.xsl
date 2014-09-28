<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:echo="http://www.springframework.org/spring-ws/samples/echo">
	<xsl:output method="xml" indent="no" omit-xml-declaration="yes" />
	<xsl:template match="/echo:echoResponse">
		<echo:echoResponse>
		<xsl:value-of select="translate(. , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
		</echo:echoResponse> 
	</xsl:template>
	<xsl:variable name="vLowercaseChars_CONST" select="'abcdefghijklmnopqrstuvwxyz'" />
	<xsl:variable name="vUppercaseChars_CONST" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
</xsl:stylesheet> 