<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar juez manualmente</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	
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
		
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploEncargadoCausasBandejaSolc.xml', 
			datatype: "xml", 
			colNames:['Caso','Fecha <br>de Solicitud', 'Hora', 'Tipo de Audiencia','Probable Responsable','Delito','Quien Solicita','Fecha L&iacute;mite de Audiencia'], 
			colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
						{name:'Fecha',index:'fecha', width:20}, 
						{name:'Hora',index:'Hora', width:50}, 
						{name:'Audiencia',index:'Audiencia', width:50},
						{name:'Responzable',index:'Responzable', width:50},
						{name:'Delito',index:'Delito', width:50},
						{name:'Solicita',index:'Solicita', width:50},
						{name:'Limite',index:'Limite', width:50}
					],
			pager: jQuery('#pager1'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
				dblClickRowBandejaSolicitudes(rowid);
				}
		}).navGrid('#pager1',{edit:false,add:false,del:false});	
		
		$("#txtFecha").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$( "#tabsprincipalconsulta" ).tabs();
		$("#juez1").click(checkboxListener);
		$('#juez2').click(checkboxListener);
		$('#juez3').click(checkboxListener);
		$('#juez4').click(checkboxListener);
		
		$("#sala1").click(checkboxListenerSala);
		$('#sala2').click(checkboxListenerSala);
		$('#sala3').click(checkboxListenerSala);
		$('#sala4').click(checkboxListenerSala);
		
		$("#responsable1").click(checkboxListenerReponsable);
		$('#responsable2').click(checkboxListenerReponsable);
		$('#responsable3').click(checkboxListenerReponsable);
		$('#responsable4').click(checkboxListenerReponsable);
		
		
		
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
	
function checkboxListenerSala(){
		
		if ($("#sala1").is(':checked')){
			$('#sala2').attr('checked', false);
			$('#sala3').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala2").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala3').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala3").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala2').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala4").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala2').attr('checked', false);
			$('#sala3').attr('checked', false);
		}		
	}
	
function checkboxListenerResponsable(){
	
	if ($("#responsable1").is(':checked')){
		$('#responsable2').attr('checked', false);
		$('#responsable3').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable2").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable3').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable3").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable2').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable4").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable2').attr('checked', false);
		$('#responsable3').attr('checked', false);
	}		
}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-0">Detalle</a></li>
		<li><a href="#tabsconsultaprincipal-1">Registrar Fecha y Hora</a></li>
		<li><a href="#tabsconsultaprincipal-2">Jueces</a></li>
		<li><a href="#tabsconsultaprincipal-3">Salas</a></li>
		<li><a href="#tabsconsultaprincipal-4">Sala Temporal</a></li>
		<li><a href="#tabsconsultaprincipal-5">Notificaciones</a></li>
	</ul>
	<div id="tabsconsultaprincipal-0">
		<table width="600" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1"></div>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-1">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="5" cellpadding="0">
				      <tr>
				      	<td width="11%" >Fecha:</td>
				        <td width="89%"><input type="text" id="txtFecha" /></td>
				      </tr>
				      <tr>
				        <td></td>
				        <td></td>
				      </tr>
				      <tr>
				      	<td>Hora: </td>
				        <td><input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM">
				        </td>
				      </tr>
				      <tr>
				      	<td></td>
				      	<td align="left"><input id="btnGuardar" type="button" value="Guardar" class="btn_Generico"/></td>
				      </tr>
					</table>	
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-2">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="11%"><input type="checkbox" id="juez1" /></td>
				        <td width="89%">Juez. Armando Castañeda Tenango</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez2" /></td>
				        <td>Juez. Cuauhtémoc Paredes Serrano</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez3" /></td>
				        <td>Juez. Jorge Ignacio Fernández Ortíz</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez4" /></td>
				        <td>Juez. Erick Arturo de la Peña Soto</td>
				      </tr>
					</table>
				</td>
				<td width="200" >
					<input type="button" value="Designar Autom&aacute;ticamente" class="btn_Generico"/>
				</td> 
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-3">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="11%"><input type="checkbox" id="sala1" /></td>
				        <td width="89%">Sala 1</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="sala2" /></td>
				        <td>Sala 2</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="sala3" /></td>
				        <td>Sala 3</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="sala4" /></td>
				        <td>Sala 4</td>
				      </tr>
					</table>	
				</td>
				<td width="200" >
					<input type="button" value="Designar Autom&aacute;ticamente" class="btn_Generico"/>
				</td> 
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-4">
		<table width="355" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td colspan="2" align="center" width="250" ><h3>Direcci&oacute;n de la Sala</h3>
			</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="354" border="0" cellspacing="4" cellpadding="0">
				      <tr>
				        <td width="25%">Calle o Avenida:</td>
				        <td width="75%"><input type="text" style="width:90%;"></td>
				      </tr>
				      <tr>
				        <td>No. Exterior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>No. Interior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>Delegaci&oacute;n o <br/> Municipio :</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
				      <tr>
				        <td>Entidad Federativa</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
					</table>	
				</td>
				<td>
					<table width="100%">
						<tr>
							<td width="25%">Motivo : 
							</td>
							<td width="75%"> <textarea rows="5" cols="45"></textarea>
							</td>
						</tr>
						<tr>
							<td>Descripci&oacute;n: 
							</td>
							<td> <textarea rows="5" cols="45"></textarea>
							</td>
						</tr>
						<tr>
						<td colspan="2" align="right"><input type="button" value="Asignar Sala" class="btn_Generico"/>
						</td>
						</tr>	
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-5">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="11%"><input type="checkbox" id="responsable1" /></td>
				        <td width="89%">Responsable: Erick Arturo de la Peña Soto</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="responsable2" /></td>
				        <td>Responsable: Jorge Ignacio Fernández Ortíz</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="responsable3" /></td>
				        <td>Responsable: Cuauhtémoc Paredes Serrano</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="responsable4" /></td>
				        <td>Responsable: Armando Castañeda Tenango</td>
				      </tr>
					</table>	
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>