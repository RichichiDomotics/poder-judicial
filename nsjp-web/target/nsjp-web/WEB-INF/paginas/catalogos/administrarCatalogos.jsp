<%@page import="mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>
<%@page import=" mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>    
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
		var contextPath = '<%=request.getContextPath()%>';
		var idFramePadreConsultaCatVal="iframeConsultarValorCatalogo";
		var agregar = false;
		function iframeConsultaCatalogoLoad(){
			$("#iframeConsultaCatalogo").show();
			if(document.CatalogosForm.idCatalogo.value == "-1"){
				//$("#iframeConsultaCatalogo").hide();
			} else if (document.CatalogosForm.idCatalogo.value == '<%=Catalogos.AREA.ordinal()%>') {
				$("#agregarValorAlCatalogo").hide();				
				document.CatalogosForm.submit();
			} else if (document.CatalogosForm.idCatalogo.value == '<%=Catalogos.DEPARTAMENTO.ordinal()%>') {
				$("#agregarValorAlCatalogo").hide();
				document.CatalogosForm.submit();
			} else if (document.CatalogosForm.idCatalogo.value == '<%=Catalogos.INSTITUCION.ordinal()%>') {
				$("#agregarValorAlCatalogo").hide();
				document.CatalogosForm.submit();
			} else{
				document.CatalogosForm.submit();
			}
		}
		
		function toggleAgregarValorAlCatalogo(desactivar){
			if(!desactivar){
				$("#agregarValorAlCatalogo").show();
			}else{
				$("#agregarValorAlCatalogo").hide();
			}						
		}
		
		function agregarValorCatalogo(){
			agregar = true;
			if($("#idCatalogo").val()!=-1){
				if($("#idCatalogo").val() == '<%=Catalogos.DELITO.ordinal()%>'){
					var accion = contextPath+"/ConsultaValorDeCatalogoDelito.do?";
		 			accion += "idCatalogo="+ $("#idCatalogo").val();
					accion += "&esNuevo="+ true;
		 			openNewIframeWindow(idFramePadreConsultaCatVal, accion, "Nuevo Delito", 800, 320, 50, 50);
				}
				else if($("#idCatalogo").val() == '<%=Catalogos.SALA_AUDIENCIA.ordinal()%>'){
			 		var accion = contextPath+"/ConsultaValorDeCatalogoSalaAudiencia.do";
		 			openNewIframeWindow(idFramePadreConsultaCatVal, accion, "Nuevo valor", 900, 400, 50, 50);
				}
				else {
			 		var accion = contextPath+"/ConsultaValorDeCatalogo.do?";
		 			accion += "idCatalogo="+ $("#idCatalogo").val();
		 			openNewIframeWindow(idFramePadreConsultaCatVal, accion, "Nuevo valor", 600, 400, 50, 50);
				}
			}
		}
		
		/*
		*Funcion que se invoca unicamente cuando se agrega un valor al catalogo
		*esta ventana es el padre y recarga el grid del iframe incrustado en ella
		*que se llama consultaCatalogo.jsp
		*/
		function cerrarVentanaVerValor(){
				$.closeWindow(idFramePadreConsultaCatVal);
				window.frames[0].recargarGrid();				
		}
		
	</script>
</head>
<body>
	<table>
	<tr>
		<td>
		<html:form action="/ConstruirGridCatalogos" method="post" styleId="CatalogosForm" target="iframeConsultaCatalogo">
			<html:select name="CatalogosForm" property="idCatalogo" styleId="idCatalogo" onchange="iframeConsultaCatalogoLoad()">
				<option value="-1">Seleccione una opci&oacute;n</option>
				<logic:iterate id="catalogo" name="CatalogosForm" property="catalogos">
					<bean:define id="idValor" name="catalogo" property="clave"></bean:define>
					<option value="<%=idValor%>" 
					onkeypress="toggleAgregarValorAlCatalogo(<bean:write name="catalogo" property="esSistema"/>)"
					onkeyup="toggleAgregarValorAlCatalogo(<bean:write name="catalogo" property="esSistema"/>)"
					onclick="toggleAgregarValorAlCatalogo(<bean:write name="catalogo" property="esSistema"/>)" >
						<bean:write name="catalogo" property="valor"/>
					</option>
				</logic:iterate>
			</html:select>
		</html:form>
		</td>
		<td>
			<input type="button" id="agregarValorAlCatalogo" value="Agregar nuevo valor al Catálogo" onclick="agregarValorCatalogo()" class="btn_Generico"/>
		</td>
	</tr>
	</table>
	<div>
		<iframe id="iframeConsultaCatalogo" name="iframeConsultaCatalogo" width="95%" height="600px"
		style="border:none; display:none;"
		src="<%=request.getContextPath()%>/ConstruirGridCatalogos.do"></iframe>
	</div>
</body>
</html>