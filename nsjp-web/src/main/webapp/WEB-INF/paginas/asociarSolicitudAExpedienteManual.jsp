<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
	<title>Asociar solicitud a expedientes de forma manual</title>
	
		<!--CSS DE LA PAGINA-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
		<!--SCRIPTS DE LA PAGINA-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">

	var solicitudId;	
	$(document).ready(function() {

		solicitudId='<%= request.getParameter("idEvento")%>';

		//Crea las tabs del visor
		$("#tabsPrincipalAsociarSolAExpedientes").tabs();
		//Carga el grid con las evidencias
		cargaGridAsociarSolicitudAExpedienteManual(solicitudId);
		
		
	});
	
	function cerrarVentana(){
		parent.cerrarVentanaAsignacionEvidencia();
	}
	 
////////////////////////////////////COMIENZA FUNCIONALIDAD////////////////////////////////////////////

	/*
	*Funcion que carga el grid con los numeros de expedientes con los cuales puede asociarse la solicitud
	*/
	function cargaGridAsociarSolicitudAExpedienteManual(solicitudId){
			
			jQuery("#gridAsociarSolAExpediente").jqGrid({
				
				url:'<%= request.getContextPath()%>/consultarExpedientesPorSolicitudId.do?solicitudId='+solicitudId+'', 
				datatype: "xml", 
				colNames:['Número de Expediente','Fecha de Creacion'], 
				colModel:[ 	
							{name:'NumeroDeExpediente',index:'numeroDeExpediente', width:250},
							{name:'FechaDeCreacion',index:'fechaDeCreacion', width:250},
						],
				pager: jQuery('#pagerGridAsociarSolAExpediente'),
				rowNum:5,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:150,
				sortname: 'numeroDeExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridAsociarSolAExpediente',{edit:false,add:false,del:false});
	}



	/*
	*Funcion para tomar el(los) numeros de expedientes para relacionarlos con la solicitud
	*/
	function asociarSolicitudAExpediente(){

		var expedienteId = jQuery("#gridAsociarSolAExpediente").jqGrid('getGridParam','selrow');
		
		if (expedienteId) {

			if(confirm("¿Realmente desea asignar la solicitud al expediente seleccionado?")) {
				
		    	$.ajax({
		    		type: 'POST',
		    	    url: '<%=request.getContextPath()%>/asociarSolicitudAExpediente.do?solicitudId='+solicitudId.split(',')[1]+'&numeroExpedienteId='+expedienteId+'',
		    	    data: '',
		    	    dataType: 'xml',
		    	    async: false,
		    	    success: function(xml){
		    	    	var errorCode;
		    	    	
						errorCode=$(xml).find('response').find('code').text();				
						if(parseInt(errorCode)==0){	
														
		    			}
						this.close();
		    		}
				});
					
			 }
		} else { alertDinamico("Por favor seleccione un renglon");}
    }
////////////////////////////////////TERMINA FUNCIONALIDAD DE LA CEJA AVISAR///////////////////////////////////////////////////////////////////////////////////
    
	</script>
</head>
<body>
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
<div id="tabsPrincipalAsociarSolAExpedientes">
	
	<ul>
		<li><a href="#tabsPrincipalAsociarSolAExpedientes-0">Asociar solicitud a expedientes de forma manual</a></li>
	</ul>
	
	<!--	Comienza tab Asignacion de evidencia-->
	<div id="tabsAsignarEvidencia-0">
	
		<table width="600" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="450"><strong>Seleccione el expediente al que desea asociar la solicitud</strong></td>
		    <td width="150" align="right"><input type="submit" id="asociarSolicitud"  onclick="asociarSolicitudAExpediente();" value="Asociar Solicitud"/></td>
		  </tr>
		  <tr>
		    <td colspan="2">
		    	<!--Espacio para el grid con las solicitudes periciales terminadas o cerradas-->
				<div id="divGridAsociarSolAExpediente">
					<table id="gridAsociarSolAExpediente"></table>
					<div id="pagerGridAsociarSolAExpediente"></div>
				</div>		    
		    </td>
		  </tr>
		</table>
	</div>
	<!--	Termina tab asignacion de evidencia-->
</div>
<!--	Termina tab principal-->
</body>
</html>