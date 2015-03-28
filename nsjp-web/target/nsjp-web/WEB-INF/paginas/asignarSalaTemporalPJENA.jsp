<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Designar Sala Temporal</title>
	
		
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
		<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	
	<!--para controlar las ventanas-->

		<script type="text/javascript">
		
		function cerrarVentana(){
			parent.cerrarVentanaAsignacionTemporal();
		}

		function asignarSalaTemporal(){
			parent.asignarSalaTemporal(recuperaDatosAsignacionTemporal());
		}

		function customRange(input) {
			$.timeEntry.setDefaults({show24Hours: true});
			return {minTime: (input.id == 'idHoraDateLapsoFin' ?
				$('#idHoraDate').timeEntry('getTime') : null),
				maxTime: (input.id == 'idHoraDate' ?
				$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
		}

		
		$(function(){
			$('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,30,0],ampmPrefix: ' '});
		});
		
		
		
		 function recuperaDatosAsignacionTemporal()
		   {
			   var lsDatosAsignacionTemp="";
			   
			   lsDatosAsignacionTemp="direccion="+$("#direccionSalaTemp").val();
			   lsDatosAsignacionTemp+="&ubicacion="+$("#ubicacionSalaTemp").val();
			   lsDatosAsignacionTemp+="&motivoAsignacion="+$("#motivoAsignaSalaTemp").val();
			   var horaMin = $("#idHoraDate").val();
			   hrSelecTemp = horaMin.split(":")[0];
				minSelecTemp = horaMin.split(":")[1];
			   lsDatosAsignacionTemp+="&horaTemp="+ hrSelecTemp;
			   lsDatosAsignacionTemp+="&minutoTemp="+ minSelecTemp;
				  	
			   return lsDatosAsignacionTemp;
		   }

		 function confirmaCancelar() {
			 if(confirm("¿Desea salir de la asignación de la sala temporal?")) {
				 cerrarVentana();
			 } 

		 } 


		 
		</script>
	</head>
	<body>
		<table width="100%" border="0" height="90%">
			<tr>
				<td align="right">
					Direcci&oacute;n:
				</td>
				<td>
					<input type="text" class="" size="80" maxlength="80" id="direccionSalaTemp"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					Ubicaci&oacute;n:
				</td>
				<td>
					<input type="text" size="80" maxlength="80"	id="ubicacionSalaTemp"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					Motivo de Asignaci&oacute;n:
				</td>
				<td>
					<input type="text" size="80" maxlength="80" id="motivoAsignaSalaTemp"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					Hora Programada:
				</td>
				<td>
					<input type="text" id="idHoraDate" size="10" class="timeRange" value="01:00" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" id="btnCancelar" value="Cancelar" onclick="confirmaCancelar();" class="btn_Generico">
				</td>
				<td align="center">
					<input type="button" id="btnAsignar" value="Asignar" onclick="asignarSalaTemporal();" class="btn_Generico">
				</td>
			</tr>
		</table>
	</body>
</html>