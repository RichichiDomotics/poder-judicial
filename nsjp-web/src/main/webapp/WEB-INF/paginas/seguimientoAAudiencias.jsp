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
		
		$( "#tabsprincipalconsulta" ).tabs();
		$("#juez1").click(checkboxListener);
		$('#juez2').click(checkboxListener);
		$('#juez3').click(checkboxListener);
		$('#juez4').click(checkboxListener);
		
		$("#txtFecha").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
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

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Fecha y Hora</a></li>
		<li><a href="#tabsconsultaprincipal-2">Juez</a></li>
		<li><a href="#tabsconsultaprincipal-3">Sala</a></li>
		<li><a href="#tabsconsultaprincipal-4">Notificaciones</a></li>
	</ul>
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
					<!-- <input type="button" value="Designar Autom&aacute;ticamente"/>-->
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
					<!-- <input type="button" value="Designar Autom&aacute;ticamente"/>-->
				</td> 
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-4">
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