<!-- 
CASO DE USO
Elaborar Documento - Pericial

El sistema muestra las solicitudes de pericial asignadas al perito
que estén en proceso para elaborar un documento o un informe y poder cerrar 
la solicitud

 -->

<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Documentos Elaborados</title>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	
	<!--para controlar las ventanas-->
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>	
	<script type="text/javascript">
		tipo = '<%=request.getParameter("tipo")%>';
	</script>
	<script type="text/javascript">
	
	solicitudIdActual = 0;
	
	$(document).ready(function() {
		$("#tabsPrincipal").tabs();
		if(tipo == 'Dictamen'){
			window.title = 'Dictámenes Elaborados';
			
		}else if(tipo == 'Informe'){
			window.title = 'Informes Elaborados';
		}
		$("#tituloSeccion").html(window.title);
		
		
		jQuery("#gridSolicitud").jqGrid({ 

			url:'<%=request.getContextPath()%>/consultarSolicitudesPericialesPeritoEnProceso.do', 
			datatype: "xml", 
			colNames:['Número Expediente','Número de Caso','Fecha Límite','Fecha Última Modificación','Documento creado'], 
			colModel:[ 	{name:'numeroExpediente',index:'numeroExpediente', width:18,align:'center'}, 
			           	{name:'numeroCaso',index:'numeroCaso', width:18,align:'center'}, 
						{name:'fechaLimite',index:'fechaLimite', width:10,align:'center'},
						//se oculta porque no se muestran datos debido a la bitacora
						{name:'fechaUltimaModificacion',index:'fechaUltimaModificacion', width:12,align:'center',hidden:true},
						{name:'documentoCreado',index:'documentoCreado', width:10,align:'center'}
						
					],
			pager: jQuery('#pagerGridSolicitud'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'numeroExpediente',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid, iRow, iCol,e){
				params = rowid.split(",");
				forma = tipo == 'Dictamen'?<%=Formas.DICTAMEN_PERICIAL.getValorId()%>:<%=Formas.INFORME_PERICIAL.getValorId()%>;
				documentoId = params[1];
				solicitudIdActual = params[0];

				if(documentoId == ''){
					//si no tiene documento creado, mandar a crearlo primero antes de editarlo
					$.ajax({
			    		type: 'POST',
			    		url: '<%=request.getContextPath()%>/crearDictamenParaSolicitudPericial.do',
			    		data: 'solicitudPericialId='+params[0]+'&formaId='+forma,
			    		dataType: 'xml',
			    		async: false,
			    		success: function(xml){

			    			var errorCode;
							errorCode=$(xml).find('response').find('code').text();				
							if(parseInt(errorCode)==0){	
								documentoId = $(xml).find('documentoId').first().text();
								
								$("#gridSolicitud").trigger("reloadGrid");
			    			}
							else{
								//Mostrar mensaje de error
							}
			    		}
			    	});
				}
				//colocar el expediente en sesion
				exp = "";
				$.ajax({
			    		type: 'POST',
			    		url: '<%=request.getContextPath()%>/colocarExpedienteParaSolicitudPericial.do',
			    		data: 'solicitudPericialId='+params[0]+'&formaId='+forma,
			    		dataType: 'xml',
			    		async: false,
			    		success: function(xml){
			    			var errorCode;
							errorCode=$(xml).find('response').find('code').text();				
							if(parseInt(errorCode)==0){	
								exp = $(xml).find('numeroExpediente').first().text();
								
								
			    			}
							else{
								//Mostrar mensaje de error
							}
			    		}
			    	});
				//abrir el documento
				$.newWindow({id:"iframewindowGenerarDocumentoPericial", statusBar: true, posx:200,posy:50,width:1140,height:400,title:tipo, type:"iframe"});
		        $.updateWindowContent("iframewindowGenerarDocumentoPericial",
		        		"<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+documentoId+"&numeroUnicoExpediente="+exp+"' width='1140' height='400' />");
		       
				
		        
				
			}
		}).navGrid('#pagerGridSolicitud',{edit:false,add:false,del:false});
		
	});
	
	function documentoGenerado(){
		if(solicitudIdActual > 0){
			$.ajax({			
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/terminarSolicitudPericialPerito.do',
	    		data: 'solicitudPericialId='+solicitudIdActual,
	    		dataType: 'xml',
	    		async: true,
	    		success: function(xml){
	    			$("#gridSolicitud").trigger("reloadGrid");
	    			
	    		}
	    	});
			
		}
	}
    </script>
</head>
<body>
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1" id="tituloSeccion">Solicitante</a></li>
					
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="left">
								Solicitudes en Proceso
							</td>
						</tr>
						<tr>
							<td align="right" >
								<table
										id="gridSolicitud"></table>
								<div id="pagerGridSolicitud" style="width: 300"></div>
							</td>
						</tr>
						
						
					</table>
				</div>
				
		</td>
	</tr>
	
</table>
</body>
</html>