<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Visor Medida Cautelar</title>

	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<!--para controlar las ventanas-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>

	<script type="text/javascript">
	numeroCausa = "";
	$(document).ready(function() {

		numeroCausa = '<%= request.getParameter("numeroCausa")%>';

		//Carga el grid  de Involucrados por Causa
		cargaGridInvolucradosCausaPJENC(numeroCausa);

		$("#tabsprincipalconsulta" ).tabs();
	});

	/**
	*Funcion que consulta los involucrados por causa
	*/
	function cargaGridInvolucradosCausaPJENC(numeroCausa){
			
		jQuery("#gridInvolucradosCausaPJENC").jqGrid(
				{ url:'<%= request.getContextPath() %>/consultarMedidasCautelaresPorNumeroExpediente.do?numeroExpedieteId='+numeroCausa+'', 						
					datatype: "xml",
					mtype: 'POST',						
					colNames:['Nombre','Medida Cautelar', 'Descripcion','Periodo de Imposici&oacute;n','Periodicidad','Encargado Seguimiento'], 
					colModel:[ 	{name:'nombre',index:'nombre', width:200}, 
								{name:'medidaCautelar',index:'medidaCautelar', width:100}, 
								{name:'descripcionMedida',index:'descripcionMedida', width:150},
								{name:'periodoAplicacion',index:'periodoAplicacion', width:150}, 
								{name:'periodicidad',index:'periodicidad', width:150},
								{name:'encargadoSeguimiento',index:'encargadoSeguimiento', width:150},
							],
						autowidth: false,
						width:924,
						height: 200,
						pager: jQuery('#paginadorInvolucradosCausa'), 
						sortname: 'nombre', 
						rownumbers: false,
						gridview: false, 
						viewrecords: true, 
						sortorder: "desc",
						caption:"Administrar medidas cautelares",
						ondblClickRow: function(rowid) {
							mostrarVentanaMedidasCautelares(rowid);
						} 
				}).navGrid('#paginadorInvolucradosCausa',{edit:false,add:false,del:false});
		$('#divGridInvolucradosCausaPJENC').show();
	}

	/*
	*Funcion que abre la ventana de medidas cautelares
	* 
	*/
	function mostrarVentanaMedidasCautelares(rowid){
		$.newWindow({id:"iframewindowMedidasCautelares", statusBar: true, posx:70,posy:20,width:880,height:360,title:"Ingresar/Modificar Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowMedidasCautelares",'<iframe src="<%=request.getContextPath()%>/ingresarMedidaCautelarPJENC.do?numeroExpedienteId='+numeroCausa+'&rowid=' + rowid +'" width="880" height="360" />'); 
	}

	function nuevaVentanaMedidasCautelares(rowid){
		var idInvolucrado =   rowid.split(",")[0];
		rowid= idInvolucrado+',0';
		$.newWindow({id:"iframewindowMedidasCautelares", statusBar: true, posx:70,posy:20,width:880,height:360,title:"Ingresar/Modificar Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowMedidasCautelares",'<iframe src="<%=request.getContextPath()%>/ingresarMedidaCautelarPJENC.do?numeroExpedienteId='+numeroCausa+'&rowid=' + rowid +'" width="880" height="360" />'); 
	}
	
	function validaSeleccion(){
		var rowid = jQuery("#gridInvolucradosCausaPJENC").jqGrid('getGridParam','selrow');
		if (rowid) { 
			nuevaVentanaMedidasCautelares(rowid);
		}else{
			alert("Por favor seleccione un renglon");
		} 
	}
	</script>
</head>
<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Traslado</a></li>
			<li><a href="#tabsconsultaprincipal-2">Actuaciones</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
		<table width="697" border="0">
  <tr align="right">
    <td width="140">Caso</td>
    <td width="167"><input name="" type="text" id="idTrasladosCaso"/></td>
    <td width="201">Solicitante</td>
    <td width="171"><input name="" type="text" id="idTrasladosSolicitante"/></td>
  </tr>
  <tr align="right">
    <td>Personal a Trasladar</td>
    <td><input name="" type="text" id="idTrasladosPersona"/></td>
    <td>Lugar Origen</td>
    <td><input name="" type="text" id="idTrasladosOrigen"/></td>
  </tr>
  <tr align="right">
    <td>Lugar Destino</td>
    <td><input name="" type="text" id="idTrasladosDestino"/></td>
    <td>Sala</td>
    <td><input name="" type="text" id="idTrasladosSala"/></td>
  </tr>
  <tr align="right">
   <td>Fecha y Hora Traslado</td>
    <td><input name="" type="text" id="idTrasladosFechaHora"/></td>
    <td>Duracion de la Audiencia</td>
    <td><input name="" type="text" id="idTrasladosDuracion"/></td>
  </tr>
</table>

		</div>
		<div id="tabsconsultaprincipal-2">
	<table width="274" border="0">
  <tr>
    <td width="79">Actuaciones</td>
    <td width="185"><select name="">
    <option value="0">- Seleccione -</option>
    <option value="1">Oficio de Descarcelación </option>
    <option value="2">Oficio de Cumplimiento</option>
    <option value="3">Oficio de incumplimiento</option>
    </select></td>
  </tr>
</table>
		</div>
	</div>
</body>
</html>