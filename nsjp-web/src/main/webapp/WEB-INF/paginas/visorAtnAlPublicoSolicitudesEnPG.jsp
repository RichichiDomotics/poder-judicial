<%@page import="org.apache.cxf.common.util.StringUtils"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atencion a Sollicitudes</title>

<!--Se importan las css necesarias-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<script type="text/javascript">
		
	$(document).ready(function() {
		//Ocultamos todos los campos
		ocultaTodosLosCampos();
		//Crea Tabs
		$("#tabsprincipalconsulta" ).tabs();
		//carga el combo box de tipo de solicitudes
		cargaComboTipoSolicitud();
		//Escuchador de cambio en el combo instituciones
		 $("#solicitudPJATP").change(onSelectChangeTipo);
		 //deshabilita todos los botones
		 deshabilitaBotones();
		//carga Date picker para la fecha limite de la audiencia
		cargaDatePickerFechaLimite();

		 $('#horaLimiteAudiencia').timepicker({
	            onSelect: function(time, inst) {
	                $('#floating_selected_time').html('You selected ' + time);
	            }
	        });
	});
	//Fin On Ready	
	var idNumeroExpediente = '<%=request.getParameter("idNumeroExpediente")%>';

	/*
	*Funcion que carga los combos de tipo de solicitudes que se pueden realizar
	*para esta institucion
	*/
	function cargaComboTipoSolicitud() {
	
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTipoSolicitud.do',
			data: '',
			async: false,
			dataType: 'xml',
			success: function(xml){
				$('#solicitudPJATP').empty();
				$('#solicitudPJATP').append('<option value="-1">- Seleccione -</option>');
				var tipoSol = "";
				$(xml).find('tipoSolicitud').each(function(){
					var tipoSol = $(this).find('clave').text();
					if( parseInt(tipoSol) ==  1774 ||
						tipoSol == <%=TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId()%>){
							$('#solicitudPJATP').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						}
				});
			}
		});
	}

	/*
	*Funcion que carga los combos de tipo de solicitudes de audiencia que se pueden realizar
	*/
	function cargaComboTipoAudiencia() {
	
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTipoAudiencia.do',
			data: '',
			async: false,
			dataType: 'xml',
			success: function(xml){
				$('#tipoDeAudiencia').empty();
				$('#tipoDeAudiencia').append('<option value="-1">- Seleccione -</option>');
				var tipoAud = "";
				$(xml).find('institucion').each(function(){
					var tipoAud = $(this).find('clave').text();
					
					if((tipoAud != <%=TipoAudiencia.JUICIO_ORAL.getValorId()%>) &&(tipoAud != <%=TipoAudiencia.VINCULACION.getValorId()%>)){
						$('#tipoDeAudiencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					}
				});
			}
		});
	}
	
	
	/*
	* Funcion que muestra u oculta los campos dependiendo de la opciion seleccionadas
	*/	
	function muestraControles(tipoSolicitud){
		
		//Limpia todos los campos cuando se cambia de el tipo de audiencia
		limpiaTodosLosCampos();
		//Oculta todos los campos
		ocultaTodosLosCampos();

		//Si es una no ha seleccionado una opcion
		if(tipoSolicitud == "-1"){
			deshabilitaBotones();
		}

		//Si es una sol de audio y video
		if(parseInt(tipoSolicitud) ==  1774){
			habilitaBotones();
			muestraCamposAudioVideoTranscripcion();
		}
		
		//Si es una sol de transcripcion de audiencia
		if(tipoSolicitud ==  <%=TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId()%>){
			habilitaBotones();
			muestraCamposAudioVideoTranscripcion();			
		}
<%
		String nombreSolicitante = request.getParameter("nombreSolicitante");
		String apaterSolicitante = request.getParameter("apaterSolicitante");
		String amaterSolicitante = request.getParameter("amaterSolicitante");
		if(!StringUtils.isEmpty(nombreSolicitante) && !StringUtils.isEmpty(apaterSolicitante) && !StringUtils.isEmpty(amaterSolicitante)){%>
		
		$("#nombreSolicitantePJATP").val("<%=nombreSolicitante%>");
		$("#apPatSolicitantePJATP").val("<%=apaterSolicitante%>");
		$("#apMatSolicitantePJATP").val("<%=amaterSolicitante%>");
		
		<%}
%>
		
	}
	
	/*
	*Funcion que oculta todos los campos, comboBox y areas de texto
	*/	
	function ocultaTodosLosCampos(){
		
		$(".au").hide();
		$(".av").hide();
		$(".ra").hide();
		$(".rc").hide();
		$(".ta").hide();
				
		$("#divEtiquetaInvolucrados").hide();
		$("#gridInvolucradosAudienciaContenedor").hide();
		
		$("#divFundamentoSol").hide();
		$("#divFundamentoSolCampo").hide();
		$("#divSituacionesEspeciales").hide();
		$("#divSituacionesEspecialesCampo").hide();
		
		
		$("#divNumeroCaso").hide();
		$("#divNumeroCausa").hide();
		$("#numCasoPJATP").hide();
		$("#numCausaPJATP").hide();
		$("#divFechaSolicitud").hide();
		$("#fechaSolicitudPJATP").hide();
		$("#divHoraSolicitud").hide();
		$("#horaSolicitudPJATP").hide();
		$("#divInsSolicitante").hide();
		$("#institucionSolicitantePJATP").hide();
		$("#divDatosSolicitante").hide();
		$("#divNombreSol").hide();
		$("#nombreSolicitantePJATP").hide();
		$("#divApellidoPat").hide();
		$("#apPatSolicitantePJATP").hide();
		$("#divApellidoMat").hide();
		$("#apMatSolicitantePJATP").hide();
		$("#divFechaLimite").hide();
		$("#fechaLimiteAudiencia").hide();
		$("#divHoraLimite").hide();
		$("#horaLimiteAudiencia").hide();
		$("#divTipoAudiencia").hide();
		$("#tipoDeAudiencia").hide();
		$("#divTipoRecurso").hide();
		$("#tipoDeRecurso").hide();
		$("#divAdjuntarDoc").hide();
		$("#btnSolTranscripcion,#divIdAudiencia,#txtIdAudiencia").hide();
	}

	
	/*
	*Funcion que muestra los campos para realizar una solicitud
	*de Audio y video y de Transcripcion de audiencia
	*/	
	function muestraCamposAudioVideoTranscripcion(){
		
		$(".av").show();
		$(".ta").show();
		
		$("#divNumeroCausa").show();
		$("#divNumeroCaso").show();
		$("#numCausaPJATP").show();
		$("#numCasoPJATP").show();
		$("#divFechaSolicitud").show();
		$("#fechaSolicitudPJATP").show();
		$("#divHoraSolicitud").show();
		$("#horaSolicitudPJATP").show();
		$("#divInsSolicitante").show();
		$("#institucionSolicitantePJATP").show();
		$("#divDatosSolicitante").show();
		$("#divNombreSol").show();
		$("#nombreSolicitantePJATP").show();
		$("#divApellidoPat").show();
		$("#apPatSolicitantePJATP").show();
		$("#divApellidoMat").show();
		$("#apMatSolicitantePJATP").show();
		$("#btnSolTranscripcion,#divIdAudiencia,#txtIdAudiencia").show();
		//carga la fecha y la hora actual
		cargaFechaHoraActual();
		//carga el combo de instituciones solicitantes
		cargaComboInstitucionSolicitante();
	} 

	

	/*
	*Funcion que deshabilita todos los botones
	*/
	function deshabilitaBotones(){
		
		$('#buscarCausa').attr("disabled", "true");
		$('#limpiar').attr("disabled", "true");
		$('#validarCausa').attr("disabled", "true");
		$('#numCasoPJATP').attr("disabled", "true");
	}
	
	/*
	*Funcion que deshabilita todos los botones
	*/
	function habilitaBotones(){
		
		$('#buscarCausa').removeAttr('disabled');
		$('#limpiar').removeAttr('disabled');
		$('#validarCausa').removeAttr('disabled');
		$('#numCasoPJATP').removeAttr('disabled');
	}

	
	/*
	*Funcion que muestra la fecha y hora del sistema
	*/
	function cargaFechaHoraActual(){
		
		var anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';
		var mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
		var diaActual='<%=(java.util.Calendar.getInstance().get(Calendar.DAY_OF_MONTH))%>';

		mesActual=(parseInt(mesActual)+1)+"";
				
		if(diaActual.length < 2 && mesActual.length < 2 ){
			$('#fechaSolicitudPJATP').val(+"0"+diaActual+"/"+"0"+ mesActual+"/"+anioActual);
		}
		if(diaActual.length < 2 && mesActual.length >= 2 ){
			$('#fechaSolicitudPJATP').val(+"0"+diaActual+"/"+ mesActual+"/"+anioActual);
		}
		if(diaActual.length >= 2 && mesActual.length < 2 ){
			$('#fechaSolicitudPJATP').val(diaActual+"/"+"0"+ mesActual+"/"+anioActual);
		}
		if(diaActual.length >= 2 && mesActual.length >= 2 ){
			$('#fechaSolicitudPJATP').val(diaActual+"/"+mesActual+"/"+anioActual);
		}

		var horaActual='<%=(java.util.Calendar.getInstance().get(Calendar.HOUR_OF_DAY))%>';
		var minutoActual='<%=(java.util.Calendar.getInstance().get(Calendar.MINUTE))%>';
		if(minutoActual.length < 2){
			$('#horaSolicitudPJATP').val(horaActual+":0"+minutoActual);
		}
		else{
			$('#horaSolicitudPJATP').val(horaActual+":"+minutoActual);
		}
	}

	//Funcion que carga los combos de las instituciones solicitantes
	function cargaComboInstitucionSolicitante() {
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarInstitucion.do',
			data: '',
			async: false,
			dataType: 'xml',
			success: function(xml){
				$('#institucionSolicitantePJATP').empty();
				$('#institucionSolicitantePJATP').append('<option value="-1">- Seleccione -</option>');
				var tipoIns = "";
				$(xml).find('institucion').each(function(){
					tipoIns = $(this).find('clave').text();
					if( tipoIns == "1" || tipoIns == "2"){
						$('#institucionSolicitantePJATP').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					}
				});
			}
		});
	}
	
	/*
	*Limpia el campo de causa
	*/
	function limpiarCampoCausa(){
		$('#numCasoABuscar').val("");
		$('#numCasoPJATP').val("");
		$('#numCausaPJATP').val("");
	}

	/*
	*Funcion que limpia todos los campos
	*/	
	function limpiaTodosLosCampos(){
		
		$("#numCausaPJATP").val("");
		$("#numCasoPJATP").val("");
		$("#fechaSolicitudPJATP").val("");
		$("#horaSolicitudPJATP").val("");
		$("#institucionSolicitantePJATP").attr('selectedIndex', 0);
		$("#nombreSolicitantePJATP").val("");
		$("#apPatSolicitantePJATP").val("");
		$("#apMatSolicitantePJATP").val("");
		$("#fechaLimiteAudiencia").val("");
		$("#horaLimiteAudiencia").val("");
		$("#tipoDeAudiencia").attr('selectedIndex', 0);
		$("#tipoDeRecurso").attr('selectedIndex', 0);
		$("#txtIdAudiencia").val("");
	}

	/*
	* Funcion que al cambiar el combo de instituciones oculta o muestra algunos elementos de la 
	* pantalla
	*/
	function onSelectChangeTipo() {
	
		var seleccion = $("#solicitudPJATP option:selected");
		muestraControles(seleccion.val());
	}

	function terminarGuardadoSolicitud(documentoId,numExp){
		alert("Solicitud enviada");
		
		document.formaDocDirecto.documentoId.value = documentoId;
		document.formaDocDirecto.numeroUnicoExpediente.value = numExp;
		document.formaDocDirecto.submit();
		limpiarCampoCausa();
	}
	

	//Funcion que valida si los campos estan llenos al enviar 
	function validaCamposFecha() {

		var validaFecha = false;
		
		if ($('#fechaSolicitudPJATP').val() == '' || $('#fechaLimiteAudiencia').val() == ''
				|| $('#horaLimiteAudiencia').val() == '') {
			alert("Se debe ingresar una fecha y hora límite");
		} else {

			var fechaIniVal = $('#fechaSolicitudPJATP').val();
			var fechaFinVal = $('#fechaLimiteAudiencia').val();

			var inicio = fechaIniVal.split("/");
			var fin = fechaFinVal.split("/");

			if(fin[2]>inicio[2]){
				validaFecha=true;
			}
			else{
				if(fin[2]<inicio[2]){
					validaFecha=false;
				}
				else{
					if(fin[1]>inicio[1]){
						validaFecha=true;
					}	
					else{
						if(fin[1]<inicio[1]){
							validaFecha=false;
						}
						else{
							if(fin[0]>=inicio[0]){
								validaFecha=true;
							}
							else{
								validaFecha=false;
							}
						}
					}
				}
			}
				
			if(validaFecha==false){	
				alert("La fecha final debe de ser mayor a la fecha inicial");
			}
		}
		
		return validaFecha;
	}
	
	function enviaTranscripcion()
	{
		if(validaDatosObligatorios())
		{
			guardarSolicitudAccion();
		}
		else
		{
			alert("Ocurrió un error al tratar de enviar la transcripción.");
		}
	}

	//Funcion para validar que todos los datos se hayan proporcionados
	function validaDatosObligatorios()
	{
		var idTipoSol=$("#solicitudPJATP option:selected").val();
		if(parseInt(idTipoSol)==-1)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		
		if($("#numCasoPJATP").val()=="" || $("#numCasoPJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		if($("#txtIdAudiencia").val()=="" || $("#txtIdAudiencia").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		if($("#numCausaPJATP").val()=="" || $("#numCausaPJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		if($("#fechaSolicitudPJATP").val()=="" || $("#fechaSolicitudPJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		if($("#horaSolicitudPJATP").val()=="" || $("#horaSolicitudPJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}

		
		var idInsSol=$("#institucionSolicitantePJATP option:selected").val();
		if(parseInt(idInsSol)==-1)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}

		if($("#nombreSolicitantePJATP").val()=="" || $("#nombreSolicitantePJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		
		if($("#apPatSolicitantePJATP").val()=="" || $("#apPatSolicitantePJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		if($("#apMatSolicitantePJATP").val()=="" || $("#apMatSolicitantePJATP").val().length==0)
		{
			alert("Todos los datos son obligatorios");
			return false;
		}
		return true;
	}
	
	//funcion que guarda las solicitudes de audiencia, de transcripcion de audiencia y  de audio/video
	function guardarSolicitud()
	{

		forma = document.solicitudPJForm;
			
		forma.fechaSolicitud.value = $('#fechaSolicitudPJATP').val();
		forma.horaSolicitud.value = $('#horaSolicitudPJATP').val();
		forma.institucionSolicitante.value = $('#institucionSolicitantePJATP option:selected').val();
		forma.nombreSolicitante.value = ($('#nombreSolicitantePJATP').val()+" "+$('#apPatSolicitantePJATP').val()+" "+$('#apMatSolicitantePJATP').val());
		forma.tipoSolicitud.value = $('#solicitudPJATP option:selected').val();
		forma.numeroExpedienteId.value = "1";
		forma.tipoDeRecurso.value = "1";//$('#tipoDeRecurso').val();
		forma.formaId.value = <%=Formas.ACUSE_RECIBO.getValorId()%>
		forma.numeroCausa.value = $('#numCausaPJATP').val();
		forma.numeroCaso.value = $('#numCasoPJATP').val();
		forma.fechaLimite.value = $('#fechaSolicitudPJATP').val();
		forma.horaLimite.value = $('#horaSolicitudPJATP').val();
		forma.tipoAudiencia.value = "1";//$('#tipoDeAudiencia').val();
		forma.motivoSolicitud.value = $('#motivoSolicitud').val();
		forma.situacionesEspeciales.value = $('#situacionesEspeciales').val();
		forma.involucradosAudiencia.value = "";//jQuery("#gridInvolucradosAudiencia").jqGrid('getGridParam','selarrrow');
		forma.audienciaId.value = $("#txtIdAudiencia").val();//jQuery("#gridAudiencias").jqGrid('getGridParam','selrow');
		forma.submit();		 
	}
	
	//funcion que guarda las solicitudes de audiencia, de transcripcion de audiencia y  de audio/video
	function guardarSolicitudAccion()
	{
		parametros="";			
		parametros += "fechaSol="+$('#fechaSolicitudPJATP').val();
		parametros += "&horaSol="+$('#horaSolicitudPJATP').val();
		parametros += "&instSol="+$('#institucionSolicitantePJATP option:selected').val();
		parametros += "&nombreSol="+($('#nombreSolicitantePJATP').val()+" "+$('#apPatSolicitantePJATP').val()+" "+$('#apMatSolicitantePJATP').val());
		parametros += "&tipoSol="+$('#solicitudPJATP option:selected').val();
		parametros += "&idAudienciaSol="+$("#txtIdAudiencia").val();//jQuery("#gridAudiencias").jqGrid('getGridParam','selrow');
		parametros += "&numCausaSol="+$('#numCausaPJATP').val();
		parametros += "&numCasoSol="+$('#numCasoPJATP').val();
		parametros += "&idNumeroExpediente="+idNumeroExpediente;
				
		//llamamos al action que guardara la transcripcion
		//alert(parametrosHechos);
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/solicitarTranscripcionPG.do',
			data: parametros,
			dataType: 'xml',
			success: function(xml){
				//Venismo de una insercion
				if(parseInt($(xml).find('bandera').text())==1)
				{
					alert("La solicitud de la transcripción fue exitosa.");
				}
				else
				{
					alert("Ocurrió un error al solicitar la transcripción.");
				}
			}
		});
	}

</script>
</head>

<!--Comienza cuerpo del documento-->
<body>
	<!--Comienza tab principal-->
	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Datos de la Solicitud</a></li>
		</ul>
		<!--Comienza tab detalle de solicitud-->
		<div id="tabsconsultaprincipal-1" align="left">
			
			<!--Comienza tabla con datos de la solicitud-->
				 <table width="650" border="0" cellspacing="5" cellpadding="0">
				  <tr>
			      	<td colspan="2">
			      	</td>
			      	<td align="right">
			      	</td>
			      	<td align="right">
			      		<input type="button" id="btnSolTranscripcion" value="Solicitar Transcripción" onclick="enviaTranscripcion();" class="btn_Generico"/>
			      	</td>
			      </tr>
				 <tr>
			      	<td colspan="2">&nbsp;
			      	</td>
			      	<td align="right">&nbsp;
			      	</td>
			      	<td align="right">
			      		&nbsp;
			      	</td>
			      </tr>
			      <tr>
			      	<td colspan="2">
			      	</td>
			      	<td align="right"><div id="divNumeroCaso"><strong>N&uacute;mero de Caso:</strong></div>
			      	</td>
			      	<td>
			      		<input type="text" id="numCasoPJATP" style="width: 200px;"/>
			      	</td>
			      </tr>
			      <tr>
			        <td colspan="2"></td>
			        <td align="right">
			            <div id="divIdAudiencia">
			                <strong>Id Audiencia : </strong>
			            </div>
			        </td>
			        <td align="left">
			        	<input type="text" id="txtIdAudiencia" style="width: 200px;"/>
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2"><strong>Tipo de Solicitud:</strong></td>
			        <td width="138" align="right">
			            <div id="divNumeroCausa"><strong>N&uacute;mero de Causa:</strong></div>
			        </td>
			        <td width="203">
			            <input type="text" id="numCausaPJATP" style="width: 200px;"/>
			        </td>
			        </tr>
			        <tr>
			        <td width="125" align="right">
			            <div id="divFechaLimite">
			                <strong>Fecha límite: </strong>
			            </div>
			            <div id="divTipoRecurso">
			                <strong>Tipo de recurso:</strong>
			            </div>
			        </td>
			        <td width="209">
			            <input type="text" id="fechaLimiteAudiencia" style="width:78px;"/>
			            <select id="tipoDeRecurso" style="width: 200px;" disabled="disabled">
			                <option>-Seleccione-</option>
			                <option value="0">Recurso casaci&oacute;n</option>
			                <option value="1">Recurso apelaci&oacute;n</option>
			            </select>
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2"><select id="solicitudPJATP" style="width:200px;">
			        </select></td>
			        <td align="right">
			            <div id="divFechaSolicitud"><strong>Fecha de Solicitud:</strong></div>
			        </td>
			        <td>
			            <input type="text" id="fechaSolicitudPJATP" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
			        </td>
			      </tr>
			      <tr>
			        <td width="137">&nbsp;</td>
			        <td width="77">&nbsp;</td>
			        <td align="right">
			            <div id="divHoraSolicitud"><strong>Hora deSolicitud:</strong></div>
			        </td>
			        <td>
			            <input type="text" id="horaSolicitudPJATP" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" />
			        </td>
			        </tr>
			        <tr>
			        <td align="right">
			            <div id="divTipoAudiencia">
			                <strong>Tipo de audiencia:</strong>
			            </div>
			        </td>
			        <td>
			            <select id="tipoDeAudiencia" style="width: 200px;"></select>
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2"></td>
			        <td align="right">
			            <div id="divInsSolicitante"><strong>Institución Solicitante:</strong></div>
			        </td>
			        <td>
			            <select id="institucionSolicitantePJATP" style="width:200px;"></select>
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2"></td>
			        <td colspan="2" align="center">
			            <div id="divDatosSolicitante">
			                <strong>Datos del solicitante</strong>
			            </div>
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2"></td>
			        <td align="right">
			            <div id="divNombreSol"><strong>Nombre(s):</strong></div>
			        </td>
			        <td>
			            <input type="text" id="nombreSolicitantePJATP" style="width: 200px;" />
			        </td>
			       
			      </tr>
			      <tr>
			        <td></td>
			        <td></td>
			        <td align="right">
			            <div id="divApellidoPat"><strong>Apellido Paterno:</strong></div>
			        </td>
			        <td>
			            <input type="text" id="apPatSolicitantePJATP" style="width: 200px;" />
			        </td>
			      </tr>
			      <tr>
			        <td colspan="2">&nbsp;</td>
			        <td align="right">
			            <div id="divApellidoMat"><strong>Apellido Materno:</strong></div>
			        </td>
			        <td>
			            <input type="text" id="apMatSolicitantePJATP" style="width: 200px;" />
			        </td>
			      </tr>
			    </table>
			<!--Termina tabla con datos de la solicitud-->
			
		</div>
		<!--Termina tab detalle de solicitud-->
	</div>
	<div id="divAdjuntarDoc" style="display: none;">
				        	<form id="solicitudPJForm" name="solicitudPJForm" 
				        	action="<%= request.getContextPath() %>/registrarSolicitudPGATP.do" method="post" enctype="multipart/form-data" >
								<input type="file" name="archivoAdjunto" > 
								<input type="hidden" name="fechaSolicitud" />
								<input type="hidden" name="horaSolicitud" />
								<input type="hidden" name="institucionSolicitante" />
								<input type="hidden" name="nombreSolicitante" />
								<input type="hidden" name="tipoSolicitud" />
								<input type="hidden" name="numeroExpedienteId" />
								<input type="hidden" name="esconderArbol" />
								<input type="hidden" name="formaId" />
								<input type="hidden" name="tipoDeRecurso" />
								<input type="hidden" name="numeroCausa" />
								<input type="hidden" name="numeroCaso" />
								<input type="hidden" name="fechaLimite" />
								<input type="hidden" name="horaLimite" />
								<input type="hidden" name="tipoAudiencia" />
								<input type="hidden" name="motivoSolicitud" />
								<input type="hidden" name="situacionesEspeciales" />
								<input type="hidden" name="involucradosAudiencia" />
								<input type="hidden" name="audienciaId"/>
								
								
							</form>
						</div>
	<!--Termina Tab principal-->
	<form name="formaDocDirecto" id="formaDocDirecto" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
		<input type="hidden" name="documentoId" />
		<input type="hidden" name="numeroUnicoExpediente" />
	</form>	
		
	<script type="text/javascript">

		<logic:present name="solicitudNueva">
			terminarGuardadoSolicitud("<%=request.getAttribute("solicitudNueva").toString()%>","<%=request.getAttribute("numeroExpediente").toString()%>");
		</logic:present>
	</script>
	
</body>
</html>

