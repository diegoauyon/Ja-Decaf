<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:output method="text" encoding="UTF-8"/>

<!--
********************************************************************
** XSLT Principal, revisa los nodos y los convierte a JASMIN
   tab 						<xsl:text>&#9;</xsl:text>
   cr 						<xsl:text>&#10;&#13;</xsl:text>
   cr+lf					&#10;&#13;
   sp 						<xsl:text> </xsl:text>
   mayor					&gt;
   menor					&lt;
*****************************************************************-->
	
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<!-- Generacion Encabezado-->
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<xsl:template match="/class">
		<xsl:text>.class </xsl:text> <xsl:value-of select="/class/@access"/> <xsl:text> </xsl:text> <xsl:value-of select="/class/@type"/> <xsl:text>&#10;&#13;</xsl:text>
		<xsl:text>.super java/lang/Object</xsl:text><xsl:text>&#10;&#13;</xsl:text>
		<!--#######################################-->
		<!-- Generacion Fields-->
		<!--#######################################-->
		<xsl:text>
		&#10;&#13;
;========================
; fields
;========================</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
		<xsl:for-each select="/class/fields/field">
			<xsl:text>.field </xsl:text><xsl:value-of select="@access"/><xsl:text> </xsl:text><xsl:value-of select="@name"/><xsl:text> </xsl:text>
				<xsl:if test="@array = 'true'">
					<xsl:text>[</xsl:text>
				</xsl:if>
				<xsl:if test="@estructure = 'true'">
					<xsl:text>L</xsl:text>
				</xsl:if>
				<xsl:value-of select="@type"/><xsl:text>&#9;&#9;</xsl:text>
				<xsl:if test="@estructure = 'false'">
					<xsl:text>&#9;&#9;&#9;</xsl:text>
				</xsl:if>
				<xsl:text>; </xsl:text><xsl:value-of select="@annotation"/>
			<xsl:text>&#10;&#13;</xsl:text>
		</xsl:for-each>
		<!--####################################### -->
		<!-- Generacion Constructor-->
		<!--####################################### -->
		<xsl:text>
		&#10;&#13;
;========================
; constructores
;========================</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
		<xsl:call-template name="construccionConstructor"></xsl:call-template>
		<xsl:text>
		&#10;&#13;
;========================
; métodos
;========================</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
<xsl:text>&#10;&#13;</xsl:text>
		<xsl:call-template name="consMetodo"></xsl:call-template>

	</xsl:template>
	
	
	
	
	<!-- ********************************************************************************-->
	<!-- *                           Template CONSTRUCTOR                               *-->
	<!-- ********************************************************************************-->
	<xsl:template name="construccionConstructor">
		<xsl:text>.method public static &lt;init&gt;()V</xsl:text>
		<xsl:text>&#10;&#13;&#9;  .limit locals 1</xsl:text>
		<xsl:text>&#10;&#13;&#9;  .limit stack 99&#10;&#13;&#10;&#13;</xsl:text>
		<xsl:text>&#9;  aload_0&#10;&#13;
	  invokenonvirtual   java/lang/Object/&lt;init&gt;()V        ;push this&#10;&#13;	</xsl:text>
		<xsl:call-template name="inicializadorNormales"></xsl:call-template>
		<xsl:text>&#9;  return&#10;&#13;
.end method&#10;&#13;&#10;&#13;</xsl:text>
		<xsl:call-template name="inicializadorClases"></xsl:call-template>
	</xsl:template>
	
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<!-- Generacion Arrays-->
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<xsl:template name="inicializadorNormales">
		<xsl:for-each select="/class/fields/field">
			<xsl:if test="@estructure = 'false'">
				<xsl:if test="@array = 'true'">
					<xsl:text>  aload_0&#10;&#13;</xsl:text>
					<xsl:text>&#9;  bipush    </xsl:text><xsl:value-of select="@size"/><xsl:text>&#10;&#13;</xsl:text>
					<xsl:text>&#9;  newarray  </xsl:text>
					<xsl:if test="@type = 'I'">
						<xsl:text>int&#10;&#13;</xsl:text>
					</xsl:if>
					<xsl:if test="@type = 'Z'">
						<xsl:text>boolean&#10;&#13;</xsl:text>
					</xsl:if>
					<xsl:if test="@type = 'C'">
						<xsl:text>char&#10;&#13;</xsl:text>
					</xsl:if>
					<xsl:text>&#9;  putfield  </xsl:text><xsl:value-of select="/class/@type"/><xsl:text>/</xsl:text><xsl:value-of select="@name"/><xsl:text> [</xsl:text><xsl:value-of select="@type"/><xsl:text>&#10;&#13;</xsl:text>
				</xsl:if>
				
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<!-- Generacion arrays de clases numArrayEstruct-->
	<!-- °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° -->
	<xsl:template name="inicializadorClases">
		<xsl:if test="/class/fields/@numArrayEstruct != '0'">
			<xsl:text>.method static &lt;clinit&gt;()V&#10;&#13;</xsl:text>
			<xsl:text>&#9;  .limit locals 0&#10;&#13;</xsl:text>
			<xsl:text>&#9;  .limit stack 1 &#10;&#13; &#10;&#13;</xsl:text>
			<xsl:for-each select="/class/fields/field">
				<xsl:if test="@estructure = 'true'">
					<xsl:if test="@array = 'true'">
						<xsl:text>&#9;  bipush    </xsl:text><xsl:value-of select="@size"/><xsl:text>&#10;&#13;</xsl:text>
						<xsl:text>&#9;  anewarray </xsl:text><xsl:value-of select="@type"/><xsl:text> ; Crea array del objeto&#10;&#13;</xsl:text>
						<xsl:text>&#9;  putstatic </xsl:text><xsl:value-of select="/class/@type"/><xsl:text>/</xsl:text><xsl:value-of select="@name"/><xsl:text> [L</xsl:text><xsl:value-of select="@type"/><xsl:text>;&#10;&#13;</xsl:text>
					</xsl:if>
				</xsl:if>
			</xsl:for-each>
			<xsl:text>&#9;  return&#10;&#13;
.end method&#10;&#13;&#10;&#13;</xsl:text>
		</xsl:if>
	</xsl:template>
	
   
   <!-- ********************************************************************************-->
   <!-- *                           Template Metodo                               *-->
   <!-- ********************************************************************************-->
   
   <xsl:template name="consMetodo">
	   <xsl:for-each select="/class/methods/method">
		   <xsl:text>.method public </xsl:text><xsl:value-of select="@name"/><xsl:text>(</xsl:text>
		   <xsl:call-template name="escribirParametros"><xsl:with-param name="parametros" select="properties/parameters"/> </xsl:call-template> <xsl:text>)</xsl:text>
		   <xsl:call-template name="escribirTipoMetodo"><xsl:with-param name="retorno" select="properties/return"/> </xsl:call-template> <xsl:text>&#10;&#13;</xsl:text>
		   <xsl:text>&#9;  .limit locals </xsl:text><xsl:value-of select="properties/locals/@numTotThis"/><xsl:text>&#10;&#13;</xsl:text>
		   <xsl:text>&#9;  .limit stack 99 &#10;&#13; &#10;&#13;</xsl:text>
		   <xsl:call-template name="crearInstrucciones"><xsl:with-param name="instrucciones" select="instructions"/></xsl:call-template>
		   <xsl:call-template name="tipoRetorno"><xsl:with-param name="ret" select="properties/return" /></xsl:call-template>
			<xsl:text>.end method&#10;&#13;&#10;&#13;</xsl:text>
	   </xsl:for-each> <!-- TERMINA EACH -->
   </xsl:template>
   
    <!-- ********************************************************************************-->
   <xsl:template name="escribirParametros">
	   <xsl:param name="parametros"/>
	   <xsl:for-each select="$parametros/parameter">
		   <xsl:if test="@array = 'true'">
			   <xsl:text>[</xsl:text>
		   </xsl:if>
		   <xsl:if test="@estructure = 'true'">
			   <xsl:text>L</xsl:text><xsl:value-of select="@type"></xsl:value-of><xsl:text>;</xsl:text>
		   </xsl:if>
		   <xsl:if test="@estructure = 'false'">
			   <xsl:value-of select="@type"></xsl:value-of>
		   </xsl:if>
	   </xsl:for-each>
   </xsl:template>
   
    <!-- ********************************************************************************-->
   <xsl:template name="escribirTipoMetodo">
	   <xsl:param name="retorno"/>
	   <xsl:if test="$retorno/@array = 'true'">
		   <xsl:text>[</xsl:text>
	   </xsl:if>
	   <xsl:if test="$retorno/@estructure = 'true'">
		   <xsl:text>L</xsl:text><xsl:value-of select="$retorno/@type"></xsl:value-of><xsl:text>;</xsl:text>
	   </xsl:if>
	   <xsl:if test="$retorno/@estructure = 'false'">
		   <xsl:value-of select="$retorno/@type"></xsl:value-of>
	   </xsl:if>
	   <xsl:text>&#10;&#13;</xsl:text>
   </xsl:template>
   
    <!-- ********************************************************************************-->
   <xsl:template name="tipoRetorno">
	   <xsl:param name="ret"/>
	   <xsl:choose>
		   <xsl:when test="$ret/@array = 'true'"> <xsl:text>&#9;  areturn&#10;&#13;</xsl:text></xsl:when>
		   <xsl:otherwise>
			   <xsl:choose>
				   <xsl:when test="$ret/@estructure = 'true'"><xsl:text>&#9;  areturn&#10;&#13;</xsl:text></xsl:when>
				   <xsl:otherwise>
					   <xsl:choose>
						   <xsl:when test="$ret/@type = 'V'"> <xsl:text>&#9;  return&#10;&#13;</xsl:text></xsl:when>
						   <xsl:otherwise> <xsl:text>&#9;  ireturn&#10;&#13;</xsl:text> </xsl:otherwise>
					   </xsl:choose>
				   </xsl:otherwise>
			   </xsl:choose>
		   </xsl:otherwise>
	   </xsl:choose>
   </xsl:template>
	   
    <!-- ********************************************************************************-->
   <xsl:template name = "crearInstrucciones">
	   <xsl:param name = "instrucciones"/>
	   <xsl:for-each select="$instrucciones/ins">
		   <xsl:text>haoa</xsl:text>
	   </xsl:for-each>   
   </xsl:template>
</xsl:stylesheet>
