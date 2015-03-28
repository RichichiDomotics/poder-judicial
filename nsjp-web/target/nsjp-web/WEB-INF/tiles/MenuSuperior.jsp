<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<ul class="toolbar">
	<div id="menu_head">
		<li id="tbarBtnHeaderZise" class="first"><span></span>
		</li>
		<li id="toolGenerarDenunciaBtn" class="first"><span></span>Generar
			Denuncia&nbsp;<img
			src="<%=request.getContextPath()%>/resources/images/icn_dctowri.png"
			width="10" height="16">
		</li>
	</div>

	<div id="menu_config">
		<li onclick="buscarCaso();">Buscar Caso&nbsp;
			<img src="<%=request.getContextPath()%>/resources/images/icn_busca2.png" width="15" height="16">
		</li>
		<li onclick="buscarExpediente();">Buscar Expediente&nbsp;
			<img src="<%=request.getContextPath()%>/resources/images/icn_busca3.png" width="15" height="16">
		</li>
		<!--<li id="verde" onclick="generarDocumento();">Configuraci&oacute;n&nbsp;<img src="<%=request.getContextPath()%>/resources/images/icn_config.png" width="15" height="16"></li>-->
		<!--<li id="tbarBtnAsignar" class="first"><span></span>Asignar Permisos a Subordinados&nbsp;<img src="<%=request.getContextPath()%>/resources/images/icn_dctowri.png" width="10" height="16"></li>-->
	</div>
</ul>


<!-- NO BORRAR, CIERRA EL DIV DEL HEADER -->
	</div>
<!-- NO BORRAR, CIERRA EL DIV DEL HEADER -->