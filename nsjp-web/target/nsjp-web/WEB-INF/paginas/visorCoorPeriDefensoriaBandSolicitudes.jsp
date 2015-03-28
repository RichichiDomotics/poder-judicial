<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administracion de Audiencia Informatica</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
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
		//$("#generarDocumento").click(generarDocumentoViwe);
		$("#divGridConsulta").css("display", "none");
		ocultaDivsFiltroBusqueda();
		$("#btnNuevoPerito").click(nuevoPerito);
		$("#cbxTipoBusqueda").change(ocultaDivsFiltroBusqueda);
		$("#btnPeritoExterno").click(muestraPeritoExterno);
		$("#btnEnviarNotificacion").click(cerrarVentana);
		$("#btnConsultar").click(gridConsultaPerito);
		creaMultiselect();
		cargaFechaCaptura();
	});

	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solDePericialFecha').val($(xml).find('fechaActual').text());
    		}
		});
    }

	function cerrarVentana(){
		parent.cerrarVentana();
	}

	function creaMultiselect(){
		$('#cbxTipoBusqueda').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1,
			close: function (event,ui){
				ocultaDivsFiltroBusqueda();}
		});

		$('#cbxInstitucion,#cbxEspecialidad').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1 
		});
	}
	
	function cerrarVentanaPeritoExterno(){
		$.closeWindow('iframewindowPeritoExterno');
	}

	function cerrarVentanaNvoPerito(){
		$.closeWindow('iframewindowNuevoPerito');
	}
	

	function muestraPeritoExterno()
	{
		$.newWindow({id:"iframewindowPeritoExterno", statusBar: true, posx:200,posy:50,width:900,height:400,title:"Solicitud de Perito Externo ", type:"iframe"});
	    $.updateWindowContent("iframewindowPeritoExterno",'<iframe src="<%=request.getContextPath()%>/solicitudDePeritoExterno.jsp" width="900" height="400" />'); 
	}

	function nuevoPerito() {
		$.newWindow({id:"iframewindowNuevoPerito", statusBar: true, posx:200,posy:50,width:540,height:290,title:"Nuevo Perito", type:"iframe"});
	    $.updateWindowContent("iframewindowNuevoPerito",'<iframe src="<%=request.getContextPath()%>/nuevoPerito.do" width="540" height="290" />');
	    		
	}
	
	function ocultaDivsFiltroBusqueda()
	{
		var selected = $("#cbxTipoBusqueda option:selected");
		//oculto todas
		$("#divNombre").hide();
		$("#divInstitucion").hide();
		$("#divEspecialidad").hide();
		$("#divServiciosPericiales").hide();
		$("#trPeritoExterno").hide();
		//muestro los campos dependiendo de la opcion seleccionada
		if(selected.text()=="Nombre")
		{
			limpiaCamposNombre();
			$("#divNombre").show();
		}
		else if(selected.text()=="Institución")
		{
			limpiaCamposInstitucion();
			$("#divInstitucion").show();
		}
		else if(selected.text()=="Especialidad")
		{
			limpiaCamposEspecialidad();
			$("#divEspecialidad").show();
		}
		else if(selected.text()=="Servicios Periciales")
		{
			$("#trPeritoExterno").show();
			$("#divServiciosPericiales").show();
		}
		else
		{
			$("#divNombre").hide();
			$("#divInstitucion").hide();
			$("#divEspecialidad").hide();
			$("#divServiciosPericiales").hide();
		}
	}
	
	function limpiaCamposNombre(){
		$("#txtNombre,#txtApPaterno,#txtApMaterno").val('');
	}
	
	function limpiaCamposInstitucion(){
		$('#cbxInstitucion').find("option[value='-1']").attr("selected","selected");	
	}
	
	function limpiaCamposEspecialidad(){
		$('#cbxEspecialidad').find("option[value='-1']").attr("selected","selected");
	}

	function gridConsultaPerito(){
		$("#divGridConsulta").css("display", "block");
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploCoorPeriDefensoriaVisorSolicitudes.xml', 
			datatype: "xml", 
			colNames:['','Nombre','Instituci&oacute;n','Especialidad'], 
			colModel:[ 	{name:'Check',index:'check', width:15},
			           	{name:'Nombre',index:'nombre', width:125}, 
						{name:'Institucion',index:'institucion', width:100},
						{name:'Especialidad',index:'especialidad', width:100}
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
	}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
    <ul>
		<li><a href="#tabsconsultaprincipal-0">Solicitante</a></li>
		<li><a href="#tabsconsultaprincipal-1">Perito</a></li>
		<li><a href="#tabsconsultaprincipal-2">Avisar a Funcionario</a></li>		
  	</ul>
  	<div id="tabsconsultaprincipal-0" style="height: 400">
		<table width="100%" border="0" height="90%">
			<tr>
				<td>
					Nombre Servidor Público:
				</td>
				<td>
					<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre"/>
				</td>
			</tr>
			<tr>
				<td>
					Cargo:
				</td>
				<td>
					<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  />
				</td>
			</tr>
			<tr>
				<td>
					Área Administrativa:
				</td>
				<td>
					<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
				</td>
			</tr>
			<tr>
				<td>
					Fecha Elaboración:
				</td>
				<td>
					<input type="text" size="50" maxlength="13"	id="solDePericialFecha" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
				</td>
			</tr>
		</table>
	</div>
    <div id="tabsconsultaprincipal-1" style="height: 400">
	  	<div id="divOpcion">Buscar por : &nbsp;
	  		<select id="cbxTipoBusqueda">
	  			<option selected="selected">-Seleccione-</option>
	  			<option>Nombre</option>
	  			<option>Instituci&oacute;n</option>
	  			<option>Especialidad</option>
	  			<option>Servicios Periciales</option>
	  		</select>
	  	</div>
	  	<div id="divNombre">
	  		<table>
	  			<tr>
	  				<td>Nombre(s): </td>
	  				<td><input type="text" id="txtNombre" style="width:200px;"></td>
	  			</tr>
	  			<tr>
	  				<td>Apellido Paterno: </td>
	  				<td><input type="text" id="txtApPaterno" style="width:200px;"></td>
	  			</tr>
	  			<tr>
	  				<td>Apellido Materno: </td>
	  				<td><input type="text" id="txtApMaterno" style="width:200px;"></td>
	  			</tr>
	  		</table>
	  	</div>
		<div id="divInstitucion">
			<table>
				<tr>
					<td>Instituci&oacute;n: </td>
					<td>
						<select id="cbxInstitucion">
							<option value="-1">-Seleccione-</option>
							<option>Poder Judicial</option>
							<option>Defensor&iacute;a</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div id="divEspecialidad">
			<table>
				<tr>
					<td>Especialidad: </td>
					<td>
						<select id="cbxEspecialidad">
							<option value="-1">-Seleccione-</option>
							<option>Discapacidad Auditiva</option>
							<option>Impacto Ambiental</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div id="divServiciosPericiales">
			<table>
				<tr>
					<td>Servicios Periciales: </td>
					<td>
						<input type="button" id="btnPeritoExterno" value="Solicitar Perito Externo" class="btn_Generico"/>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<div id="divBotones">
			<table width="600">
				<tr>
					<td width="25%">&nbsp;</td>
					<td width="25%">
						<input type="button" id="btnConsultar" value="Consultar" class="btn_Generico"/>
					</td>
					<td width="25%"><input type="button" id="btnNuevoPerito" value="Nuevo Perito" class="btn_Generico"/></td>
					<td width="25%">&nbsp;</td>
				</tr>
			</table>
		</div>
		<div id="divGridConsulta">
		 <table width="600">
		 	<tr>
		 		<td>
		 			<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1"></div>
		 		</td>
		 	</tr>
		 </table>
		</div>
		<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
  </div>
  <div id="tabsconsultaprincipal-2" style="height: 400">
  	<table width="600">
  		<tr>
  			<td width="10">&nbsp;</td>
  			<td width="590">
  				<input type="checkbox" id="chkNotificacion_1"/>Lic. Hugo S&aacute;nchez <br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Coordinaci&oacute;n Periciales</b>
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>
  				<div style="width: 400px; height: 100px;">
  					<input type="checkbox" id="chkNotificacion_2"/>Lic. Elesban Ruiz<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Perito - Inform&aacute;tica</b><br/>
  					<input type="checkbox" id="chkNotificacion_3"/>Lic. Fernando Galindo<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Perito Ambiental</b><br/>
  				</div>
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>
  				<input type="checkbox" id="chkNotificacion_4"/>Lic. Juan Cruz<br/>&nbsp;&nbsp;&nbsp;<b>Coordinaci&oacute;n Defensores</b>
  			</td>
  		</tr>
  		<tr id="trPeritoExterno">
  			<td>&nbsp;</td>
  			<td>
  				<input type="checkbox" id="chkNotificacion_5"/>Lic. Luisa Flores<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Servicio Periciales Externo</b>
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>
  				<input type="checkbox" id="chkNotificacioFisica"/>Entregar Notificaci&oacute;n F&iacute;sicamente
  			</td>
  		</tr>
  		<tr>
			<td colspan="2" align="right">
				<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
			</td>
		</tr>
  	</table>
  </div>
</div>
<div>
	<table width="600">
		<tr>
			<td width="25%">&nbsp;</td>
			<td width="25%">
				&nbsp;
			</td>
			<td width="25%"><input type="button" id="btnEnviarNotificacion" value="Enviar Notificaci&oacute;n" class="btn_Generico"/></td>
			<td width="25%">&nbsp;</td>
		</tr>
	</table>
</div>

</body>
</html>