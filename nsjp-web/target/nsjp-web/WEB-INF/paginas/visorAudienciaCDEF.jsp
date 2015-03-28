
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Detalle de audiencia</title>

	
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
	var idAudiencia= '<%=request.getParameter("idAudiencia")!=null?request.getParameter("idAudiencia"):""%>';
	var direccionGlob;
	var ubicacionGlob;
	var tipoAudGlob;
	var idEventoGlobal;
	var iGlob=1;
	var pasa;
	var reta;
	var fechaGlob;
		    
	$(document).ready(function() {

		jQuery("#gridIntervinientes").jqGrid({
			dataType:'local',
			datatype: "xml", 
			colNames:['Nombre','Calidad','Delito','Detenido'], 
			colModel:[ 	{name:'nombre',index:'nombre', width:150}, 
			           	{name:'calidad',index:'calidad', width:150}, 
			           	{name:'delito',index:'delito', width:200},
			           	{name:'detenido',index:'detenido', width:100}
						
					],
			pager: jQuery('#pagerIntervinientes'),
			autowidth: true,
			autoheight:true,
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerIntervinientes',{edit:false,add:false,del:false,refresh:false,search:false});
		$("#tabsprincipalconsulta").tabs();
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarDetalleAudienciaDefensoria.do',
			data: 'idAudiencia='+ idAudiencia, 
			async: false,
			dataType: 'xml',
			success: function(xml){
    			limpiaDatosDetalleEvento();
    			$("#tipoAudienciaPJENS").val($(xml).find('tipoAudiencia').find('valor').text());
    			$("#numCasoPJENS").val($(xml).find('numeroGeneralCaso').text());
    			$("#audienciaPJENS").val($(xml).find('audiencia').find('id').text());
    			$("#caracterPJENS").val($(xml).find('audiencia').find('caracter').text());
    			$("#fechaAudienciaPJENS").val($(xml).find('audiencia').find('fechaEvento').text());
    			$("#horaAudienciaPJENS").val($(xml).find('audiencia').find('fechaEvento').text());
    			sala = $(xml).find('sala');
    			$("#salaPJENS").val($(sala).find('nombreSala').text());
    			$("#direccionSalaPJENS").val($(sala).find('domicilioSala').text());
    			$("#ubicacionPJENS").val($(sala).find('ubicacionSala').text());
    			var count = 1;
    			$(xml).find('involucrado').each(function(){
    				var row = { nombre:$(this).find("nombre").text(), 
    							calidad:$(this).find("calidad").text(), 
    							delito:$(this).find("delitos").text(), 
    							detenido:$(this).find("detenido").text()};
    				jQuery("#gridIntervinientes").jqGrid("addRowData", count++, row);
    			});
				jQuery("#gridIntervinientes").jqGrid("update");
			}
		});
	});


	/*
	*Funcion que limpia las cajas de texto
	*antes de llenarlas con los datos de la
	*nueva consulta
	*/	
	function limpiaDatosDetalleEvento(){
		
		$("#tipoAudienciaPJENS").val("");
		$("#numCasoPJENS").val("");
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
		<li id="detalleAudiencia"><a href="#tabsconsultaprincipal-1">Detalle de Audiencias</a></li>
		<li id="involuradoAudiencia"><a href="#tabsconsultaprincipal-2">Involucrados</a></li>
	
	</ul>

<!--Comienza tabprincipal 1-->

			<div id="tabsconsultaprincipal-1">
				<table width="1000" border="0" align="center" cellpadding="0" cellspacing="5">
				<tr>
					<td colspan="4" align="center"><strong>Audiencia:
					  <input type="text"
						id="audienciaPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</strong></td>
		            
				</tr>
		        <tr>
		        	<td colspan="4" align="center"><strong>Tipo de Audiencia:
		        	  <input type="text"
						id="tipoAudienciaPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
		        	</strong></td>
		        </tr>
				<tr>
					<td width="22%" align="right">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="27%">&nbsp;</td>
					<td width="34%">&nbsp;</td>
				</tr>
				<tr>
				  <td width="22%" align="right"><strong>Número de Caso:</strong></td>
				  <td width="17%"><input type="text"
						id="numCasoPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td colspan="2" rowspan="8">
		
					</td>
				</tr>
				<tr>
				  <td align="right"><strong>Carácter: </strong></td>
				  <td><input type="text" id="caracterPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
				  <td align="right"><strong>Fecha:</strong></td>
				  <td><input type="text" id="fechaAudienciaPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly"  /></td>
				</tr>
				<tr>
				  <td align="right"><strong>Hora:</strong></td>
				  <td><input type="text" id="horaAudienciaPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
				  <td align="right"><strong>Sala:</strong></td>
				  <td><input type="text" id="salaPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly"  /></td>
				</tr>
				<tr>
				  <td align="right"><strong>Dirección de Sala:</strong></td>
				  <td><input type="text" id="direccionSalaPJENS"
						style="width: 200px; border: 0; background: #DDD; "
						readonly="readonly" 
						 /></td>
				</tr>
				<tr>
				  <td align="right"><strong>Ubicación:</strong></td>
				  <td><input type="text" id="ubicacionPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
		        <tr>
		        	<td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td align="right">&nbsp;</td>
		            <td>&nbsp;</td>
		        </tr>
		        <tr>
		        	<td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td align="left"><table cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="158" align="right">&nbsp;</td>
		              </tr>
		            </table></td>
		            <td><table cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="151">&nbsp;</td>
		              </tr>
		            </table></td>
		        </tr>
		        <tr>
		        	<td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td align="left"><table cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="158" align="right">&nbsp;</td>
		              </tr>
		            </table></td>
		            <td><table cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="151">&nbsp;</td>
		              </tr>
		            </table></td>
		        </tr>
			</table>
			</div>
<!--Termina Div Principal 1-->
<!--Comienza Div Principal 2-->
			<div id="tabsconsultaprincipal-2">
					<table width="90%">
						<tr>
							<td>
								<table id="gridIntervinientes"></table>
								<div id="pagerIntervinientes"></div>
							</td>
						</tr>
				        <tr>
				        	<td>
				            <strong>Juez(ces):</strong>
				            <input type="text" id="juezPJENS"
								style="width: 180px; border: 0; background: #DDD;"
								readonly="readonly"  />
							</td>
				        </tr>
				    </table>
			</div>
</div>

</body>
</html>