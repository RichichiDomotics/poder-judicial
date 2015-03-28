
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Detalle de la designación</title>

	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript">

	/**
	* Variables globales para controlar los datos del testigo
	*/

	var direccionGlob;
	var ubicacionGlob;
	var tipoAudGlob;
	var idEventoGlobal;
	var iGlob=1;
	var pasa;
	var reta;
	var fechaGlob;
		    
	$(document).ready(function() {

		var idAudiencia= '<%=request.getParameter("idAudiencia")!=null?request.getParameter("idAudiencia"):""%>';
		//alert(idAudiencia);	
		consultaDetalleEvento(idAudiencia);
		
		var idEvento = <%= request.getParameter("idEvento")%>;
		idEventoGlobal=idEvento;

		$("#tabsprincipalconsulta").tabs();
		consultaDetalleDesignaciones(ids);
			  
	});					
	
	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultaDetalleEvento(idAudiencia){

		alert(idAudiencia);
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/detalleAudienciasDelDiaPJENS.do',
			data: 'idEvento='+ idAudiencia, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				
	  				alert("entra a succes")
    				limpiaDatosDetalleEvento();
    			    tipoAudGlob	= $("#tipoAudienciaPJENS").val($(xml).find('audienciaDTO').find('tipoAudiencia').find('valor').first().text());
    				$("#numCasoPJENS").val($(xml).find('audienciaDTO').find('expediente').find('casoDTO').find('numeroGeneralCaso').first().text());
    				$("#numExpPJENS").val($(xml).find('numeroExpediente').first().text());
    				$("#audienciaPJENS").val($(xml).find('id').first().text());
    				$("#caracterPJENS").val($(xml).find('caracter').first().text());

    				var fecha= $(xml).find('fechaEvento').text();
    				fechaGlob=fecha;
					formateaFechaHora(fecha);
    				
    				$("#salaPJENS").val($(xml).find('sala').find('nombreSala').first().text());
    				direccionGlob = $("#direccionSalaPJENS").val($(xml).find('sala').find('domicilioSala').first().text());
    				ubicacionGlob = $("#ubicacionPJENS").val($(xml).find('sala').find('ubicacionSala').first().text());
    				$("#juezPJENS").val($(xml).find('juez').first().text());
    				
    		
			}
		});
	}

	/*
	*Funcion que limpia las cajas de texto
	*antes de llenarlas con los datos de la
	*nueva consulta
	*/	
	function limpiaDatosDetalleEvento(){
		
		$("#tipoAudienciaPJENS").val("");
		$("#numCasoPJENS").val("");
		$("#numExpPJENS").val("");
		$("#audienciaPJENS").val("");
		$("#caracterPJENS").val("");
		$("#fechaAudienciaPJENS").val("");
		$("#horaAudienciaPJENS").val("");
		$("#salaPJENS").val("");
		$("#direccionSalaPJENS").val("");
		$("#ubicacionPJENS").val("");
		$("#juezPJENS").val("");
		$("#audienciaInvPJENS").val("");
		$("#tipoAudienciaInvPJENS").val("");
		$("#numCasoInvPJENS").val("");
		$("#numExpInvPJENS").val("");
		$("#audienciaObjPJENS").val("");
		$("#tipoAudienciaObjPJENS").val("");
		$("#numCasoObjPJENS").val("");
		$("#numExpObjPJENS").val("");
	}

	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function formateaFechaHora(fechaHora){
		
		var fechaFrac = fechaHora.split(" ")[0];
		var horaFrac = fechaHora.split(" ")[1];

		horaFracPos = horaFrac.indexOf(":", 0);
		hora=horaFrac.substring(0,horaFracPos+3);
		    				
		$("#fechaAudienciaPJENS").val(fechaFrac);
		$("#horaAudienciaPJENS").val(hora);
	}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
<ul>
	<li id="detalleAudiencias"><a href="#tabsconsultaprincipal-1">Detalle de designación</a></li>

</ul>

<!--Comienza tabprincipal 1-->

<div id="tabsconsultaprincipal-1">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td width="22%" align="right">&nbsp;</td>
			<td width="17%">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td>&nbsp;</td>
		  </tr>
		<tr>
		  <td width="22%" align="right"><strong>Número de Caso:</strong></td>
		  <td width="17%"><input type="text"
				id="numCasoPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  <td align="right"><strong>Sala designada:</strong></td>
		  <td><input type="text" id="ubicacionPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>Número de Causa:</strong></td>
		  <td><input type="text" id="numExpPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  <td width="22%" align="right"><strong>Fecha y hora de audiencia:</strong></td>
		  <td width="17%"><input type="text"
				id="numCasoPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>N&uacute;mero de expediente: </strong></td>
		  <td><input type="text" id="caracterPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  <td align="right"><strong>Detenido:</strong></td>
		  <td><input type="text" id="numExpPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>Etapa del expediente:</strong></td>
		  <td><input type="text" id="fechaAudienciaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  <td align="right"><strong>Fecha y hora de la detenci&oacute;n: </strong></td>
		  <td><input type="text" id="caracterPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>Nombre del imputado:</strong></td>
		  <td><input type="text" id="horaAudienciaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  <td align="right"><strong>Lugar donde esta detenido:</strong></td>
		  <td><input type="text" id="fechaAudienciaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>Delito:</strong></td>
		  <td><input type="text" id="salaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  <td align="right"><strong>Defensor designado:</strong></td>
		  <td><input type="text" id="horaAudienciaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  </tr>
		<tr>
		  <td align="right"><strong>Tipop de audiencia:</strong></td>
		  <td><input type="text" id="direccionSalaPJENS"
				style="width: 150px; border: 0; background: #DDD; "
				readonly="readonly" 
				 /></td>
		  <td align="right"><strong>Fecha y hora de designaci&oacute;n:</strong></td>
		  <td><input type="text" id="salaPJENS"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  </tr>
		<tr>
		  <td align="right">&nbsp;</td>
		  <td>&nbsp;</td>
		  <td align="right">&nbsp;</td>
		  <td>&nbsp;</td>
		  </tr>
	</table>
		</div>
</div>
<!--Termina Div Principal 1-->


</body>
</html>