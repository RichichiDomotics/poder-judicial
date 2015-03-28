<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Audiencia - Caso</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	
	<script type="text/javascript">
	function customRange(input) {
		  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
			$('#idHoraDate').timeEntry('getTime') : null),
			maxTime: (input.id == 'idHoraDate' ?
			$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
	    }
	    $(function(){
	      $('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
	    });
	    
	$(document).ready(function() {
	
		jQuery("#gridDetalleProbablesResponsablesDos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploProbableResponsable.xml', 
			datatype: "xml", 
			colNames:['Probable Responsable','Delito'], 
			colModel:[ 	{name:'Responsable',index:'responsable', width:200}, 
						{name:'Delito',index:'delito', width:100}
					],
			pager: jQuery('#pagerDos2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerDos2',{edit:false,add:false,del:false});

		jQuery("#gridJuecesCargaTrabajo").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploEncargadoCausasJuezCargaTrabajo.xml', 
			datatype: "xml", 
			colNames:['Juez','Carga de TRabajo'], 
			colModel:[ 	{name:'Juez',index:'juez', width:200}, 
						{name:'Carga',index:'carga', width:120}
					],
			pager: jQuery('#pagerJuecesCargaTrabajo'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerJuecesCargaTrabajo',{edit:false,add:false,del:false});

		jQuery("#gridSalas").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploEncargadoCausasSolSalas.xml', 
			datatype: "xml", 
			colNames:['Sala','Tipo','Disponibilidad'], 
			colModel:[ 	{name:'Sala',index:'sala', width:100}, 
						{name:'Tipo',index:'tipo', width:120},
						{name:'Disponibilidad',index:'disponibilidad', width:220}
					],
			pager: jQuery('#pagerSalas'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerSalas',{edit:false,add:false,del:false});
		
		
		$( "#tabsDetalleAudiencias" ).tabs();
		
		$("#juez1").click(checkboxListener);
		$('#juez2').click(checkboxListener);
		$('#juez3').click(checkboxListener);
		$('#juez4').click(checkboxListener);
				
	});

	function checkboxListener(){
		
		if ($("#juez1").is(':checked')){
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez2").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez3").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez4").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
		}		
	}
	</script>
</head>
<body>

<div id="tabsDetalleAudiencias">
		<ul>
			<li><a href="#tabsDetalleAudiencias-1">Detalle</a></li>
			<li><a href="#tabsDetalleAudiencias-2">Datos de Audiencia</a></li>
		</ul>
		<div id="tabsDetalleAudiencias-1">
			<table width="905">
				<tr>
					<td width="305">
						<table id="gridDetalleProbablesResponsablesDos"></table>
						<div id="pagerDos2" width="300"></div>
					</td>
					<td width="300" valign="top">
						<table>
							<tr>
								<td align="center" colspan="2">
									<b>Jueces</b>
								</td>
							</tr>
							<tr>
								<td width="40"></td>
								<td width="304">Juez. Armando Castañeda Tenango</td>
							</tr>
							<tr>
								<td width="40"></td>
								<td width="304">Juez. Cuauhtémoc Paredes Serrano</td>
							</tr>
							<tr>
								<td width="40"></td>
								<td width="304">Juez. Jorge Ignacio Fernández Ortíz</td>
							</tr>
							<tr>
							  <td></td>
							  <td>&nbsp;</td>
						  </tr>
							<tr>
							  <td colspan="2"></td>
						  </tr>
							<tr>
							  <td colspan="2"></td>
						  </tr>
							<tr>
							  <td></td>
							  <td><table>
							    <tr>
							      <td align="center" width="299"><b>Defensor</b></td>
						        </tr>
							    <tr>
							      <td>Defensor. Armando Castañeda Tenango</td>
						        </tr>
						      </table></td>
						  </tr>
							<tr>
							  <td></td>
							  <td>&nbsp;</td>
						  </tr>
						</table>
					</td>
					<td width="300" valign="top">&nbsp;</td>
				</tr>
			</table>
		</div>
		<div id="tabsDetalleAudiencias-2">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="552" border="0" cellspacing="5" cellpadding="0">
				      <tr>
				      	<td width="8%" >Fecha:</td>
				        <td width="27%"><input type="text" id="txtFecha" value="10/05/2011" readonly="readonly"/></td>
				        <td>Duración Estimada:</td>
				        <td><input type="text" id="txtDuracionAprox" value="30 min" readonly="readonly"/></td>
			          </tr>
				      <tr>
				      	<td>Hora: </td>
				        <td>
				        	<input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM" readonly="readonly" style="display: none;">
				        	<input type="text" value="8:00 AM" readonly="readonly">
				        </td>
				        <td colspan="2"></td>
			          </tr>
				      <tr>
				        <td>&nbsp;</td>
				        <td colspan="3">&nbsp;</td>
				      </tr>
				      <tr>
				        <td colspan="2" align="center">&nbsp;</td>
				        <td align="center"></td>
				        <td>&nbsp;</td>
				      </tr>
				      <tr>
				      	<td></td>
				      	<td colspan="3" align="left">&nbsp;</td>
				      </tr>
					</table>	
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>