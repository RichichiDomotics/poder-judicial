<%@page import="mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Prueba Visor de elementos</title>
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/jquery-ui.css" media="screen" />-->
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" media="screen" />-->
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.multiselect.css" media="screen" />-->

<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.js"></script>-->
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery-ui-1.8.1.custom.min.js"></script>-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.contextmenu.js"></script>-->
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/ui.multiselect.js"></script>-->
<!--    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.layout-1.3.0.js"></script>-->

	<script type="text/javascript">
            $.jgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;
	</script>
        
    <script type="text/javascript">

    	var contDelitosGraves=0;
		var idExpedienteop="";
    	//var numeroExpedienteId=0;
    	
		$(document).ready(function(){
			//idExpedienteop='<%= request.getParameter("idExpedienteop")%>';
			//numeroExpedienteId='<%= request.getParameter("idNumeroExpediente")%>';
			//if(idExpedienteop=='null')
			//{
			//	idExpedienteop='<%= request.getSession().getAttribute("idExpedienteConsulop")%>';
			//}
			cargaGridSolicitidesPericialesEnviadasPorExpediente();
			cargaGridSolicitidesPericialesRespondidasPorExpediente();
		});
		

		/*
		*Funcion que consulta y carga el grid con las solicitudes periciales enviadas
		*/
		function cargaGridSolicitidesPericialesEnviadasPorExpediente(){

			var abierta= <%=EstatusSolicitud.ABIERTA.getValorId()%>;
			jQuery("#gridSolicitudesPeri1").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesPorExpediente.do?numeroExpedienteId='+idNumeroExpedienteOp+'&estatus='+abierta+'', 
				datatype: "xml", 
				colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Destinatario'], 
				colModel:[ 	{name:'caso',index:'caso', width:150},
				           	{name:'expediente',index:'expediente', width:130}, 
							{name:'folio',index:'folio', width:100}, 
							{name:'estatus',index:'estatus', width:100}, 
							{name:'fechaCreacion',index:'fechaCreacion', width:100},
							{name:'fechaLimite',index:'fechaLimite', width:100},
							{name:'institucion',index:'institucion', width:100},
							{name:'remitente',index:'remitente', width:200}
						],
				pager: jQuery('#pagerGridSolicitudesPeri1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"SOLICITUDES ENVIADAS",
				sortname: 'caso',
				viewrecords: true,
				//id: 'divgridIzq',
				onSelectRow: function(id){
					//detEvi(id);
					},
				sortorder: "desc"
			});
		}
		

		/*
		*Funcion que consulta y carga el grid con las solicitudes periciales que ya han sido respondidas
		*y estan concluidas
		*/
		function cargaGridSolicitidesPericialesRespondidasPorExpediente(){

			var concluida= <%=EstatusSolicitud.CERRADA.getValorId()%>;
			jQuery("#gridSolicitudesPeri2").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesPorExpediente.do?numeroExpedienteId='+idNumeroExpedienteOp+'&estatus='+concluida+'', 
				datatype: "xml", 
				colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente'], 
				colModel:[ 	{name:'caso',index:'caso', width:150},
				           	{name:'expediente',index:'expediente', width:130}, 
							{name:'folio',index:'folio', width:100}, 
							{name:'estatus',index:'estatus', width:100}, 
							{name:'fechaCreacion',index:'fechaCreacion', width:100},
							{name:'fechaLimite',index:'fechaLimite', width:100},
							{name:'institucion',index:'institucion', width:100},
							{name:'remitente',index:'remitente', width:200}
						],
				pager: jQuery('#pagerGridSolicitudesPeri2'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"RESPUESTA A SOLICITUDES",
				sortname: 'caso',
				viewrecords: true,
				//id: 'divgridDer',
				ondblClickRow: function(rowid){
					//alert("Doble click");
					abrirDocsDigAsociadosASol();
				},
				sortorder: "desc"
			});
		}


		/*
		*Funcion que abre el PDF para ver los documentos asociados al numero de causa
		*/
		function abrirDocsDigAsociadosASol(documentoAsocId){
			if(documentoAsocId != null && documentoAsocId != ""){
				$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?archivoDigitalId="+documentoAsocId+"&inFrame=true");
			}
			else{
				alert("El documento no puede ser mostrado");
			}
		}

				
	</script>
</head>
<body>
	<table border="0"  width="1000px">
		<tr>
			<td height="20" colspan="4" align="center" >
				<table id="gridSolicitudesPeri1" width="670px"></table>
				<div id="pagerGridSolicitudesPeri1"></div>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4" align="center" >
				<table id="gridSolicitudesPeri2" width="670px"></table>
				<div id="pagerGridSolicitudesPeri2"></div>
			</td>
		</tr>
	</table>
</body>
</html>