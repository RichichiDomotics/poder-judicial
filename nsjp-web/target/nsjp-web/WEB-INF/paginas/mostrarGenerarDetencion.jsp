<%@page import="mx.gob.segob.nsjp.comun.enums.detencion.TipoCentroDetencion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Mostrar Generar Detención</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>

	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	</style>
    
<script type="text/javascript">

	var numeroExpediente = '<%=request.getParameter("numeroExpediente")%>';

	//Variable que controla si el grid se carga de peritos por primera vez
	var firstGridLugarDeDetencion = true;

	var idWindowLugarDeDetencion = 1;

	$(document).ready(function() {
		$('#liDom').hide();
		$('#liDom').addClass("tabEstilo");
		
		$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
			.removeClass( "ui-corner-all ui-corner-top" )
			.addClass( "ui-corner-bottom" );
		
		//Se crean las tabs principales
		$("#tabsconsultaprincipaldetencion" ).tabs();

		//Para seleccionar tab por default
		$('#tabsconsultaprincipaldetencion').tabs({ selected: 0 });

		$('#btnAgregarDetencion').click(mostrarLugarDeDetencion);
		$('#btnCancelarDetencion').click(cancelarDetencion);

		mostrarDatos();

	});

	/**
	** Llena de manera dinámica los subtipos de eventos asociados al tipo de evento seleccionado.
	**/
	function buscaSubTipoEvento(){
		var selected = $("#detencionCbxTipoEvento option:selected").val();
		$( "#detencionCbxSubtipoEvento" ).attr('selectedIndex',0);
		$('#detencionCbxSubtipoEvento').empty();
		$('#detencionCbxSubtipoEvento').append('<option value="-1">-Seleccione-</option>');
		
		$.ajax({
			async: false,									// la accion cargar las especialidades
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarSubtipoEvento.do?tipoEvento='+selected+'',
			dataType: 'xml',
			success: function(xml){
				if(selected == "1"){
					$(xml).find('delito').each(function(){
						$('#detencionCbxSubtipoEvento').append('<option value="' + $(this).find('claveDelito').text() + '">' + $(this).find('nombre').text() + '</option>');
					});
				}else if(selected == "2"){
					$(xml).find('falta').each(function(){
						$('#detencionCbxSubtipoEvento').append('<option value="' + $(this).find('catFaltaAdministrativaId').text() + '">' + $(this).find('nombreFalta').text() + '</option>');
					});
				}
			}
		});
	}

	/**
	**Funcion que carga el grid con los probables responsables y su lugar de detencion
	**/
	function cargaGridLugarDeDetencion(){

		if(firstGridLugarDeDetencion == true){
			
			jQuery("#gridLugarDeDetencion").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarLugarDeDetencionPorNumeroExpediente.do?numeroExpediente='+numeroExpediente+'',
				datatype: "xml", 
				colNames:['Nombre','Descripcion'], 
				colModel:[ 	{name:'Nombre',index:'nombre', width:300},
				           	{name:'Descripcion',index:'descripcion', width:300}
						 ],
				pager: jQuery('#pagerGridLugarDeDetencion'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
				//autoheight:true,
				width:600,
				height:115,
				sortname: 'nombre',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					mostrarLugarDeDetencion(rowid);
				}
			}).navGrid('#pagerGridLugarDeDetencion',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridLugarDeDetencion=false;
		}
		else{
			jQuery("#gridLugarDeDetencion").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarProbableResponsable.do?',datatype: "xml" });
			$("#gridLugarDeDetencion").trigger("reloadGrid");			
		}
	}

	function mostrarDatos(){
		$('#detencionCbxTipoEvento').val(parent.$("#detencionCbxTipoEvento option:selected").val());
		buscaSubTipoEvento();
		$('#detencionCbxSubtipoEvento').val(parent.$("#detencionCbxSubtipoEvento option:selected").val());
		armaComboProbableResponsable();
		cargaGridLugarDeDetencion();

		$("#detencionTxtNombreSP").val(parent.detencionTxtNombreSP);
		$("#detencionTxtCargo").val(parent.detencionTxtCargo);
		$("#detencionTxtAreaAdm").val(parent.detencionTxtAreaAdm);
		var fechaElaboracion = parent.detencionTxtFechaElaboracion;
		var fechaElaboracionFmt = fechaElaboracion.substring(8,10) + '/' + fechaElaboracion.substring(5,7) 
								+ '/' + fechaElaboracion.substring(0,4) + ' ' + fechaElaboracion.substring(11,16); 
		$("#detencionTxtFechaElaboracion").val(fechaElaboracionFmt);
		$("#detencionTxtCoordinadorAMP").val(parent.detencionTxtCoordinadorAMP);
		$("#detencionTxtCoordinadorDP").val(parent.detencionTxtCoordinadorDP);
	}

	function armaComboProbableResponsable(){
		$('#detencionCbxProbableResponsable').attr('selectedIndex',0);
		$('#detencionCbxProbableResponsable').empty();
		$('#detencionCbxProbableResponsable').append('<option value="-1">-Seleccione-</option>');
		
		var arrayIds = parent.jQuery("#gridProbableResponsable").getDataIDs();
		for(var i=0; i<arrayIds.length; i++){
			var ret = parent.jQuery("#gridProbableResponsable").jqGrid('getRowData',arrayIds[i]);
			if(ret.Detencion == 'Si'){
				$('#detencionCbxProbableResponsable').append('<option value="' + arrayIds[i] + '">' + (ret.Nombre + ' ' + ret.ApellidoPaterno + ' ' + ret.ApellidoMaterno) + '</option>');
			}			
		}
	}
	
	/**
	** Llena de manera dinámica las actuaciones asociadas al tipo de evento seleccionado.
	**/
	function buscaActuacion(){
		var arrayActuaciones = new Array();
		arrayActuaciones[0] = new Array('Oficio de detención','Ingreso a CEDEPRO','Mediación y conciliación','Resguardo de pertenencias','Examen médico'); 
		arrayActuaciones[1] = new Array('Oficio de detención','Ingreso a CEDEPRO','Mediación y conciliación','Resguardo de pertenencias','Examen médico');
		arrayActuaciones[2] = new Array('Resguardo de pertenencias','Oficio de localización de padres','Unidad de tratamiento para menores','Examen médico');
		
		var selected = $("#detencionCbxTipoEventoAct option:selected").val();
		$('#detencionCbxActuacion').attr('selectedIndex',0);
		$('#detencionCbxActuacion').empty();
		$('#detencionCbxActuacion').append('<option value="-1">-Seleccione-</option>');
		
		if(selected == '1'){
			for(i=0; i<arrayActuaciones[0].length; i++){
				$('#detencionCbxActuacion').append('<option value="' + (i+1) + '">' + arrayActuaciones[0][i] + '</option>');
			}
		}else if(selected == '2'){
			for(i=0; i<arrayActuaciones[1].length; i++){
				$('#detencionCbxActuacion').append('<option value="' + (i+1) + '">' + arrayActuaciones[1][i] + '</option>');
			}			
		}else if(selected == '3'){
			for(i=0; i<arrayActuaciones[2].length; i++){
				$('#detencionCbxActuacion').append('<option value="' + (i+1) + '">' + arrayActuaciones[2][i] + '</option>');
			}			
		}
	}

	function mostrarLugarDeDetencion(){
		if($('#detencionCbxProbableResponsable option:selected').val() == -1){
			customAlert('Debe seleccionar un probable responsable');
		}else{
		  	var involucradoId = $('#detencionCbxProbableResponsable option:selected').val();

	      	$('#detencionCbxProbableResponsable').val('-1');

	      	idWindowLugarDeDetencion++;
	   		$.newWindow({id:"iframewindowLugarDeDetencion" + idWindowLugarDeDetencion, statusBar: true, posx:30,posy:10,width:850,height:400,title:"Ingresar Lugar de Detención", type:"iframe"});
	   		$.updateWindowContent("iframewindowLugarDeDetencion" + idWindowLugarDeDetencion,'<iframe src="<%= request.getContextPath() %>/mostrarLugarDeDetencion.do?numeroExpediente='+numeroExpediente+'&involucradoId='+involucradoId+'" width="850" height="400" />');
		}
	}

	function cerrarVentanaMostrarLugarDeDetencion(){
		var pantalla ="iframewindowLugarDeDetencion";
		pantalla += idWindowLugarDeDetencion;
		$.closeWindow(pantalla);
		cargaGridLugarDeDetencion();
	}
	
	function cancelarDetencion(){
		var row = jQuery("#gridLugarDeDetencion").jqGrid('getGridParam','selrow');		

	    if (row) { 
			jQuery("#gridLugarDeDetencion").jqGrid('delRowData',row);
			var rowCount = $(".gridLugarDeDetencion tr").length;
		}else{
			customAlert("Por favor seleccione un probable responsable");
		}	
	}

</script>

</head>

<body>
	<div id="tabsconsultaprincipaldetencion">
		<ul>
			<li id="tabDetencion"><a href="#tabsconsultadetencion-1">Detenci&oacute;n</a></li>
			<li id="tabAvisos"><a href="#tabsconsultadetencion-2">Aviso de Detenci&oacute;n</a></li>
			<li id="tabActuaciones"><a href="#tabsconsultadetencion-3">Actuaciones</a></li>
		</ul>
		
		<!--COMIENZA TAB DE DETENCION-->
		<div id="tabsconsultadetencion-1" class="tabDetencion">
			<fieldset style="width: 650px;">
				<legend>Descripci&oacute;n</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">Tipo de Evento:</td>
							<td>
								<select id="detencionCbxTipoEvento" onchange="buscaSubTipoEvento();" disabled="disabled">
									<option value="-1">- Seleccione -</option>
									<option value="1">Delito</option>
									<option value="2">Falta Administrativa</option>
								</select>
							</td>
							<td align="right" >Subtipo de Evento:</td>
							<td>
								<select id="detencionCbxSubtipoEvento" style="width: 180px;" disabled="disabled">
									<option value="-1">- Seleccione -</option>
								</select>
							</td>
						</tr>
					</table>
			</fieldset>
			<fieldset style="width: 650px;">
				<legend>Probables Responsables</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td><bean:message key="probableResponsable"/>:</td>
						<td>
							<select id="detencionCbxProbableResponsable" tabindex="1">
								<option value="-1">- Seleccione -</option>
							</select>
						</td>
						<td>
							<input type="button" value="Agregar a Detención" id="btnAgregarDetencion" class="back_button" tabindex="4"/>
						</td>
						<td>
							<input type="button" value="Cancelar Detención" id="btnCancelarDetencion" class="back_button" tabindex="5"/>
						</td>						
					</tr>
					<tr>
						<td colspan="4"><br></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
		            		<table id="gridLugarDeDetencion"></table>
		            		<div id="pagerGridLugarDeDetencion"></div>
		            	</td>
					</tr>
				</table>
			</fieldset>
		</div>
		<!--TERMINA TAB DE DETENCION-->
		
		<!--COMIENZA TAB DE AVISOS-->
		<div id="tabsconsultadetencion-2" class="tabAvisos">
			<fieldset style="width: 650px;">
				<legend>Responsable</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td align="right" style="width: 250px;">Nombre Servidor P&uacute;blico:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtNombreSP" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="width: 250px;">Cargo:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtCargo" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="width: 250px;">&Aacute;rea Administrativa:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtAreaAdm" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="width: 250px;">Fecha Elaboraci&oacute;n:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtFechaElaboracion" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>					
				</table>
			</fieldset>
			
			<br>

			<fieldset style="width: 650px;">
				<legend>Avisos</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td align="right" style="width: 250px;">Coordinador Agente del Ministerio P&uacute;blico:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtCoordinadorAMP" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="width: 250px;">Coordinador Defensor&iacute;a P&uacute;blica:</td>
						<td><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtCoordinadorDP" disabled="disabled"/></td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</fieldset>
					
		</div>
		<!--TERMINA TAB DE AVISOS-->
		
		<!--COMIENZA TAB DE ACTUACIONES-->
		<div id="tabsconsultadetencion-3" class="tabActuaciones">
			<fieldset style="width: 650px;">
				<legend>Actuaciones</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right" style="width: 200px;">Tipo de Evento:</td>
							<td>
								<select id="detencionCbxTipoEventoAct" style="width: 180px;" onchange="buscaActuacion();">
									<option value="-1">- Seleccione -</option>
									<option value="1">Flagrancia</option>
									<option value="2">Falta Administrativa</option>
									<option value="3">Menor de edad</option>
								</select>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" style="width: 200px;">Actuaci&oacute;n:</td>
							<td>
								<select id="detencionCbxActuacion" style="width: 180px;">
									<option value="">- Seleccione -</option>
								</select>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
			</fieldset>
		</div>
		<!--TERMINA TAB DE ACTUACIONES-->		
				
	</div>
</body>
</html>
