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
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
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
		<li><a href="#tabsconsultaprincipal-1">Recibir Pruebas</a></li>
		<li><a href="#tabsconsultaprincipal-2">Recibir Recursos</a></li>
		<li><a href="#tabsconsultaprincipal-3">Solicitudes</a></li>
		<!-- <li><a href="#tabsconsultaprincipal-4">Sala Temporal</a></li>
		<li><a href="#tabsconsultaprincipal-5">Notificaciones</a></li>-->
	</ul>
	<div id="tabsconsultaprincipal-1">
		<table width="350" border="0" cellspacing="5" cellpadding="0" bgcolor="#EEEEEE">
			<tr>
				<td width="100%" align="left">
					<input type="button" id="btnSolTRaslado" value="Digitalizar documento" class="btn_Generico"/>
				</td>
			</tr>
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolCopiaAudioVideo" value="Verificar formato de audio y video" class="btn_Generico"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-2">
		<table width="350" border="0" cellspacing="5" cellpadding="0" bgcolor="#EEEEEE">
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolTRaslado" value="Registrar recurso" class="btn_Generico"/>
				</td>
			</tr>
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolCopiaAudioVideo" value="Dar seguimiento a recurso" class="btn_Generico"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-3">
		<table width="350" border="0" cellspacing="5" cellpadding="0" bgcolor="#EEEEEE">
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolTRaslado" value="Audiencia" class="btn_Generico"/>
				</td>
			</tr>
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolCopiaAudioVideo" value="Audio y Video" class="btn_Generico"/>
				</td>
			</tr>
			<tr>
				<td width="100%" align=""left"">
					<input type="button" id="btnSolCopiaAudioVideo" value="Transcripci&oacute;n de Audiencia" class="btn_Generico"/>
				</td>
			</tr>
		</table>
	</div>
	<!-- <div id="tabsconsultaprincipal-4">
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
	</div>-->
</div>
</body>
</html>