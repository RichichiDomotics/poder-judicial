<%@page import="org.apache.commons.lang.BooleanUtils"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page
	import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@page
	import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="org.apache.commons.lang.math.NumberUtils"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resumen</title>

<style type="text/css">
.texto {
	width: 225px;
	border: 0;
	background: #DDD;
}

.transpa {
	background-color: transparent;
	border: 0;
}

.textoBarra {
	color: #06F;
}

DD P {
	LINE-HEIGHT: 120%
}

#iProbResponsablePane {
	PADDING-BOTTOM: 0px;
	PADDING-LEFT: 6px;
	WIDTH: 1000px;
	PADDING-RIGHT: 0px;
	HEIGHT: 462px;
	PADDING-TOP: 10px;
	background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
	background-repeat: no-repeat;
	border: 0px solid #000;
}

#iProbResponsablePane DL {
	WIDTH: 1000px;
	HEIGHT: 400px
}

/*acordeon editar*/
#iProbResponsablePane DT {
	TEXT-ALIGN: right;
	PADDING-BOTTOM: 16px;
	PADDING-TOP: 2px;
	PADDING-LEFT: 0px;
	LINE-HEIGHT: 35px;
	TEXT-TRANSFORM: none;
	/*acomodo texto*/
	PADDING-RIGHT: 40px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	LETTER-SPACING: 1px;
	/*distancia persianas*/
	HEIGHT: 25px;
	COLOR: #f5f5f5;
	FONT-SIZE: 12px;
	FONT-WEIGHT: normal;
	background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
	background-repeat: no-repeat;
	background-position: 28px;
}

#iProbResponsablePane DT.active {
	BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
	background-repeat: no-repeat;
	COLOR: #f5f5f5;
	CURSOR: pointer;
	background-position: 30px;
}

#iProbResponsablePane DT.hover {
	COLOR: #f5f5f5
}

#iProbResponsablePane DT.hover.active {
	COLOR: #f5f5f5
}

#iProbResponsablePane DD {
	BORDER-BOTTOM: #dbe9ea 0px solid;
	BORDER-LEFT: 0px;
	PADDING-BOTTOM: 1px;
	PADDING-LEFT: 1px;
	PADDING-RIGHT: 1px;
	/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/
	BORDER-TOP: #dbe9ea 0px solid;
	MARGIN-RIGHT: 1px;
	BORDER-RIGHT: #dbe9ea 0px solid;
	PADDING-TOP: 1px
}

/*distancia y color de numero*/
#iProbResponsablePane .slide-number {
	COLOR: #68889b;
	FONT-WEIGHT: bold;
	LEFT: 30px
}

#iProbResponsablePane .active .slide-number {
	COLOR: #fff
}

#iProbResponsablePane A {
	COLOR: #58595b;
	font-family: Arial, Helvetica, sans-serif;
}

#iProbResponsablePane DD IMG {
	MARGIN: 0px;
	FLOAT: right
}

#iProbResponsablePane H2 {
	MARGIN-TOP: 10px;
	FONT-SIZE: 2.5em
}

#iProbResponsablePane .more {
	DISPLAY: block;
	PADDING-TOP: 10px
}
</style>

<!-- Hojas de estilo -->

<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<!--    Hoja de estilo para easyaccordion-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--Hoja de estilos para el grid-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<!--Hoja de estilo para el texto dentro de los acordeones-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/layout_complex.css" />
<!--Hoja de estilo para los popups-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--scripts -->

<!--Scripts necesarios para el funcionamiento de la JSP-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<!--Scrip para el idioma del calendario-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<!--Scripts necesarios para la ejecucion del editor-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<!--scripts del gird-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<!--script de jquery UI-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<%
	int tipoSolicitud = NumberUtils.toInt(
			request.getParameter("tipoSolicitud"), 0);
	boolean asignarDefensor = BooleanUtils.toBoolean(
				request.getParameter("asignarDefensor"), "1", "0");
%>
<script type="text/javascript">
	var idindi; 
	var id; 	//almacena el id del individuo solicitante
	var verAlias;
	var idDefensor ="";
	var defensorNombre ="";
	var apDefensor = "";
	var amDefensor = "";
	var etapa = "";
	var tipoDefensaDefensor;
	var lstTelefono = new Array();
	var lstCorreos = new Array();
	var i=0;
	var e=0;
	$(document).ready(function() {

		 
<%if (tipoSolicitud == 5 || tipoSolicitud == 3) {%>
		interfazAsesoria();		
<%}%>
		//genera el detalle segun el tipo de detalle
		hiddeAll();
		mostrarCampos(<%=request.getParameter("tipoSolicitud")%>);
		consultaDetalleGeneral();
		$("#tabsprincipalconsulta").tabs();	
<%if (asignarDefensor) {%>
var idEtapaExp = $(etapa).find('idCampo').first().text();
var tipoDefensaDefensor = 0;
switch(parseInt(idEtapaExp)){
	case <%=EtapasExpediente.ASESORIA.getValorId()%>:
		tipoDefensaDefensor = <%=TipoDefensoria.ASESORIA.getValorId()%>;
		$('#cbxTipoDefensa').val('<%=TipoDefensoria.ASESORIA.getValorId()%>');
		break;
	case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION
						.getValorId()%>:
		tipoDefensaDefensor = <%=TipoDefensoria.RESTAURATIVA.getValorId()%>;
		$('#cbxTipoDefensa').val('<%=TipoDefensoria.RESTAURATIVA.getValorId()%>');
		break;
	case <%=EtapasExpediente.INTEGRACION.getValorId()%>:
		tipoDefensaDefensor = <%=TipoDefensoria.INTEGRACION.getValorId()%>;
		$('#cbxTipoDefensa').val('<%=TipoDefensoria.INTEGRACION.getValorId()%>');
		break;
	case <%=EtapasExpediente.TECNICA.getValorId()%>:
		tipoDefensaDefensor = <%=TipoDefensoria.TECNICA.getValorId()%>;
		$('#cbxTipoDefensa').val('<%=TipoDefensoria.TECNICA.getValorId()%>');
		break;
}
grid2(tipoDefensaDefensor, false);
tipoDefensaDefensor = idEtapaExp;
tablaDefensor();

		$('#cbxTipoDefensa').change(seleccionEtapa);
		$("#asignarDefensor").show();
<%} else {%>		
		$("#asignarDefensor").hide();
<%}%>		
	});
	
	function interfazAsesoria(){
		//ocultamos el domicilio de notificaciones
		killDomicilioNotificaciones();
		$('#liDom').hide();	
		$('#tabs-2').hide();	
		
		//se genera el acordeon
		$('#iProbResponsablePane').easyAccordion({ 
		  autoStart: false, 
		  slideInterval: 3000
		});
		
		//arranca el editor de texto
		var config = {					
				toolbar:
				[
					['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
					['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
					['NumberedList','BulletedList','-','Outdent','Indent'],
					['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
					['Table','HorizontalRule'],
					'/',
					['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
				],
				
				height:'250px',
				width:'900px'
			};			
			$('.jquery_ckeditor').ckeditor(config);
	}
	
	function mostrarCampos(valor){
		
		switch (parseInt(valor)) {
		case 1: // AVISO DE PERSONA DETENIDA
			
			$("#trCaso").show();
			$("#trExpediente").show();
			$("#trEtapaExpediente").show();
			$("#trImputado").show();
			$("#trDelito").show();
			$("#trLugarDetencion").show();
			$("#trFechaDetencion").show();
			$("#trHoraDetencion").show();
			$("#trFechaMensaje").show();
			$("#trHoraMensaje").show();
			break;
		case 2: // SOLICITUD DEFENSOR ATENCICON TEMPRANA
			$("#trCaso").show();
			$("#trExpediente").show();
			$("#trEtapaExpediente").show();
			$("#trImputado").show();
			$("#trDelito").show();
			$("#trDetenido").show();
			$("#trLugarDetencion").show();
			$("#trFechaDetencion").show();
			$("#trHoraDetencion").show();
			$("#trFechaVencimiento").show();
			$("#trHoraVencimiento").show();
			$("#trFechaSolicitud").show();
			$("#trHoraSolicitud").show();
			break;
		case 4:// SOLICITUD DE DEFENSOR PODER JUDICIAL
			$("#trCaso").show();
			$("#trExpediente").show();
			$("#trEtapaExpediente").show();
			$("#trParaQuien").show();
			$("#trDelito").show();
			$("#trTipoAudiencia").show();
			$("#trSalaAudiencia").show();
			$("#trFechaAudiencia").show();
			$("#trHoraAudiencia").show();				
			$("#trLugarDetencion").show();
			$("#trFechaDetencion").show();
			$("#trHoraDetencion").show();
			$("#trFechaSolicitud").show();
			$("#trHoraSolicitud").show();
			break;
		case 5:// ASESORIA LEGAL
			$("#trExpediente").show();
			$("#trEtapaExpediente").show();
			$("#trInteresado").show();
			$("#trTipoAsesoria").show();			
			$("#trFechaSolicitud").show();
			$("#trHoraSolicitud").show();
			
			$("#datosInteresado").show();
			$("#motivoAsesoria").show();
			$("#documentos").show();
			break;			
	}		
	}
	
	function hiddeAll(){
		$("#trCaso").hide();
		$("#trExpediente").hide();
		$("#trEtapaExpediente").hide();
		$("#trImputado").hide();
		$("#trParaQuien").hide();
		$("#trInteresado").hide();
		$("#trTipoAsesoria").hide();	
		$("#trDelito").hide();
		$("#trDefensor").hide();
		$("#trFechaDesignacion").hide();
		$("#trHoraDesignacion").hide();
		
		$("#trTipoAudiencia").hide();
		$("#trSalaAudiencia").hide();
		$("#trFechaAudiencia").hide();
		$("#trHoraAudiencia").hide();
		$("#trDetenido").hide();
		$("#trLugarDetencion").hide();
		$("#trFechaDetencion").hide();
		$("#trHoraDetencion").hide();
		$("#trFechaMensaje").hide();
		$("#trHoraMensaje").hide();
		$("#trFechaVencimiento").hide();
		$("#trHoraVencimiento").hide();
		$("#trFechaSolicitud").hide();
		$("#trHoraSolicitud").hide();
	}

	function consultaDetalleGeneral() {
		var tipo = '<%=request.getParameter("tipoSolicitud")%>';
		
		var documentoId = '<%=request.getParameter("idDocumento")%>';
		$.ajax({type : 'POST',
			url : '<%=request.getContextPath()%>/obtenerDetalleDefensoria.do',
			data : "idDocumento="+documentoId+"&tipoDocumento="+tipo,
			async : false,
			dataType : 'xml',
			
					
			success : function(xml) {
				switch (parseInt(tipo)) {
				case 1:	// AVISO DE DETENCION
					aviso = $(xml).find("avisoDetencion").first();
					llenarDetalle(aviso);
					break;
				case 2: // SOLICITUD DEFENSOR ATENCION TEMPRANA
					solicitud = $(xml).find("solicitud").first();
					llenarDetalle(solicitud);
					break;
				case 3: // AVISO DESIGNACION
					designacion = $(xml).find("designacion").first();
					//alert("consultadetalle designacion-- " +designacion);
					llenarDetalle(designacion);
					break;
				case 4: // SOLICITUD DEFENSOR PODER JUDICIAL
					solicitud = $(xml).find("solicitud").first();
					llenarDetalle(solicitud);
					break;
				case 5: // SOLICITUD ASESORIA LEGAL
					asesoria = $(xml).find("solicitud").first();
					llenarDetalle(asesoria);
					consultaDetalle();
					break;
				}
			}
		});
	}

	function obtenerFecha(fecha){
		var fh = fecha.split(" ");
		return fh[0].split("-")[2]+"/"+fh[0].split("-")[1]+"/"+fh[0].split("-")[0];	
	}
	
	function obtenerHora(fecha){
		var fh = fecha.split(" ");
		return fh[1].substring(0,5);
	}
	
	function existe(val){
		if(val != undefined && val != null && val != "" && val.length > 0){
			return true;
		}
		return false;
	}
	
	function llenarDetalle(detalle){
		expediente = $(detalle).find('expediente').first();
		if(!existe(expediente)){
			expediente = $(detalle).find('expedienteDTO').first();
		}
		if(existe($(detalle).find('numeroCasoAsociado'))){
			$("#caso").val($(detalle).find('numeroCasoAsociado').first().text());
		}else{
			$("#caso").val("----");
		}
		if(existe($(expediente).find('numeroExpediente').first())){
			$("#expediente").val($(expediente).find('numeroExpediente').first().text());
		}else{
			$("#expediente").val("----");
		}
		
		if($('solicitud').find('tipoAsesoria').find('valor').first().text() == ""){
			$('#tipoAsesoria').val($(detalle).find('tipoAsesoria').find('valor').first().text());
			}else{
			$('#tipoAsesoria').val($(detalle).find('solicitudDefensor').find('tipoAsesoria').find('valor').first().text());
				}
		
		etapa = $(expediente).find('etapa');
		
		if(existe($(etapa).find('valor').first())){
			if(<%=request.getParameter("tipoSolicitud")%> == 3){
				var idEtapaExp = $(etapa).find('idCampo').first().text();
				tipoDefensaDefensor = idEtapaExp;
				switch(parseInt(idEtapaExp)){
					case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>:
						mostrarCampos(1);
						break;
					case <%=EtapasExpediente.INTEGRACION.getValorId()%>:
						mostrarCampos(2);
						break;
					case <%=EtapasExpediente.TECNICA.getValorId()%>:
						mostrarCampos(4);
						break;
					case <%=EtapasExpediente.ASESORIA.getValorId()%>:
						//detalle = $(detalle).find("solicitudDefensor");
						mostrarCampos(5);
						break;
				}
			}
			$("#etapaExpediente").val($(etapa).find('valor').first().text());
		}else{
			$("#etapaExpediente").val("----");
		}
		
		if(existe($(detalle).find('fechaCreacion'))){
			var fecha = $(detalle).find('fechaCreacion').first().text();
			var strfecha = obtenerFecha(fecha);
			var strhora = obtenerHora(fecha);
			$("#fechaSolicitud").val(strfecha);
			$("#horaSolicitud").val(strhora);
			$("#fechaMensaje").val(strfecha);
			$("#horaMensaje").val(strhora);
			$("#fechaDesignacion").val(strfecha);
			$("#horaDesignacion").val(strhora);
		}else{
			$("#fechaSolicitud").val("----");
			$("#horaSolicitud").val("----");
			$("#fechaMensaje").val("----");
			$("#horaMensaje").val("----");			
		}
		
		if(existe($(detalle).find('fechaDetencion'))){
			var fecha = $(detalle).find('fechaDetencion').first().text();
			var strfecha = obtenerFecha(fecha);
			var strhora = obtenerHora(fecha);
			$("#fechaDetencion").val(strfecha);
			$("#horaDetencion").val(strhora);
		}else{
			$("#fechaDetencion").val("----");
			$("#horaDetencion").val("----");
		}
		
		if(existe($(detalle).find('lugarDetencionProvicional').first())){
			$("#lugarDetencion").val($(detalle).find('lugarDetencionProvicional').first().text());
		}else{
			$("#lugarDetencion").val("----");
		}
		
		if(existe($(detalle).find('detenido').first())){
			$("#paraQuien").val($(detalle).find('detenido').first().text());
		}else{
			$("#paraQuien").val("----");
		}
		
		if(existe($(detalle).find('audiencia').first())){
			audiencia = $(detalle).find('audiencia').first();
			var fecha = $(audiencia).find('fechaEvento').first().text();
			var strfecha = obtenerFecha(fecha);
			var strhora = obtenerHora(fecha);
			$("#fechaAudiencia").val(strfecha);
			$("#horaAudiencia").val(strhora);
			$("#tipoAudiencia").val($(audiencia).find('tipoAudiencia').first().find("valor").text());
			$("#salaAudiencia").val($(audiencia).find('sala').find("nombreSala").text());
		}else{
			$("#fechaAudiencia").val("----");
			$("#horaAudiencia").val("----");
			$("#tipoAudiencia").val("----");
			$("#salaAudiencia").val("----");
		}		
		
		if(existe($(detalle).find('delitoDTO').find('nombreDelito').first())){
			$("#delito").val($(detalle).find('delitoDTO').find('nombreDelito').first().text());
		}else{
			$("#delito").val("----");
		}
		
		
		if(existe($(detalle).find('funcionario').first())){
			funcionario = $(detalle).find('funcionario').first();
			nombreFuncionario  = $(funcionario).find("nombreFuncionario").text();
			nombreFuncionario += " "+$(funcionario).find("apellidoPaternoFuncionario").text();
			nombreFuncionario += " "+$(funcionario).find("apellidoMaternoFuncionario").text();
			$("#defensor").val(nombreFuncionario);
		}else{
			$("#defensor").val("----");
			$("#fechaDesignacion").val("----");
			$("#horaDesignacion").val("----");
		}
		
		var defendido = $(detalle).find('inputado');
		if(defendido != undefined && defendido != null && defendido != "" && defendido.length > 0){
			detalleInvolucrado(defendido);
		}else{
			$(detalle).find('involucradoDTO').each(function(){
				detalleInvolucrado(this);
			});
		}
		var idEtapaExp = $(etapa).find('idCampo').first().text();
		if (parseInt(idEtapaExp) == <%=EtapasExpediente.ASESORIA.getValorId()%>){
			interfazAsesoria();
			consultaDetalle();
		}
		
		//Permite mostrar el delito principal
		if(existe($(detalle).find('avisoDetencion').find('delitos').find('catDelitoDTO').first().find('nombre').text())){
			$("#delito").val($(detalle).find('avisoDetencion').find('delitos').find('catDelitoDTO').first().find('nombre').text());
		}else{
			$("#delito").val("---");
		}
		
	}
	
	function detalleInvolucrado(involucrado){
		calidad = $(involucrado).find('calidadDTO').first().find('valorIdCalidad').find('idCampo').text();
		if(calidad == <%=Calidades.DEFENDIDO.getValorId()%> || calidad == <%=Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()%>){
			id = idindi = $(involucrado).find('elementoId').first().text();
			var nombre = obtenNombre($(involucrado).find('nombresDemograficoDTO'));
			$("#imputado").val(nombre);
			$("#paraQuien").val(nombre);
			$("#interesado").val(nombre);
			var esDetenido = $(involucrado).find('esDetenido').first().text();
		
			if(esDetenido == "true"){		
				$("#detenido").val("Si");
				var fechaDetencion = $(involucrado).find('detenciones').find('detencionDTO').find('fechaInicioDetencion').first().text();
				var fecha = obtenerFecha(fechaDetencion);
				var hora = obtenerHora(fechaDetencion);
			    $("#fechaDetencion").val(fecha);
			    $("#horaDetencion").val(hora);
				$("#lugarDetencion").val($(involucrado).find('detenciones').find('detencionDTO').find('lugarDetencionProvicional').first().text());
			}else{
				$("#detenido").val("No");
			}
		}else if(calidad == <%=Calidades.SOLICITANTE.getValorId()%>){
			idindi = $(involucrado).find('elementoId').first().text();
			id = $(involucrado).find('elementoId').first().text();
			var nombre = obtenNombre($(involucrado).find('nombresDemograficoDTO'));
			$("#interesado").val(nombre);
		}
	}
	
	function obtenNombre(nombreDemografico){
		var nombre = $(nombreDemografico).find('nombre').first().text();
		nombre += " " + $(nombreDemografico).find('apellidoPaterno').first().text();
		nombre += " " + $(nombreDemografico).find('apellidoMaterno').first().text();
		return nombre;
	}
	
	//consulta el detalle del involucrado
	function consultaDetalle(){
		//alert(id);
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%=request.getContextPath()%>/consultarInvolucrado.do',
	    	  data: 'idInvolucrado='+id,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){

		 		  $('.jquery_ckeditor').val($(xml).find('involucradoDTO').find('motivoComparecencia').text());
	    		  pintaDatosDomicilio(xml);
	    		  pintaDatosContacto(xml);
	    		  pintaDatosGenerales(xml);
	    		  pintaDatosNacimiento(xml);
	    			  				    	     
			  }
	    });
		desavilitarDatosGenerales();
		desavilitarDatosDomicilio();
		deshabilitaMediosContacto();
		
   	 	 CKEDITOR.on("instanceReady", function (ev){
 	        var bodyelement = ev.editor.document.$.body;
 	        bodyelement.setAttribute("contenteditable", false);
 	    });
 	     CKEDITOR.replace('editor1');
		
	}

	//se ocupa para consultar los documentos asociados
	function documentos(){
		var liga  = '<%=request.getContextPath()%>';
		liga += '/consultarDocumentosDefensoria.do?tipo=1&idExpedienteop=';
		liga += '<%=request.getParameter("numeroExpedienteId")%>';
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:liga, 
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'Tipo',index:'2022', width:155, align:"center"}, 
				           	{name:'Fecha',index:'2024', width:90, align:"center"},
							{name:'Nombre',index:'2023', width:255, align:"center"},
							{name:'nombreActividad',index:'2021', width:400, align:"center"},
							{name:'FechaActividad',index:'2020', width:170, align:"center"},							
							{name:'area',index:'2019', width:200, align:"center"}
							],
				pager: jQuery('#pager1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: false,
				width:800,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					consultaPDF(id);
					},
				sortorder: "desc"
			});

			jQuery("#gridDetalleFrmPrincipal").trigger('reloadGrid');
				
		}

	function consultaPDF(id){
		document.formaArchivoD.documentoId.value = id;
		document.formaArchivoD.submit();
	}
	
<%if (asignarDefensor) {%>
/**
 * INICAN FUNCIONES PARA ASIGNAR ABOGADO
 */
	
	function grid2(tipo, reloadGrid){
		if (reloadGrid) {			 
			  jQuery("#tabgrid").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/SolicitudesDefensorGrid.do?tipo='+tipo,datatype: "xml" });
			  $("#tabgrid").trigger("reloadGrid"); 
		} else{
			jQuery("#tabgrid").jqGrid({ 
				url:'<%=request.getContextPath()%>/SolicitudesDefensorGrid.do?tipo='+tipo, 
				datatype: "xml", 
				colNames:['Carga','Nombre','Propuesta'], 
				colModel:[ {name:'Carga',index:'carga', width:100, resizable:true},
						   {name:'Nombre',index:'nombre', width:200, resizable:true},		           	
				           {name:'Marca',index:'marca', width:100, resizable:true}
														
						],
				resizable:true,
				pager: jQuery('#tabgrid'),
				rowNum:10,
				rowList:[10,20,30],
				width: 'auto',
				height: 'auto',
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				onresize_end: function() { $("#tabgrid").setGridWidth($("#mainContent").width() - 5, true);},
				onSelectRow: function(rowID) {
						tablaDefensor(rowID);
				},
				ondblClickRow: function(rowID) {
						tablaDefensor(rowID);
				}
			});	
			$(this).find('table').width('100%');
		  }
	}

	function tablaDefensor(defensor){
		idDefensor = defensor;
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/SolicitudesDefensor.do',
			data: 'idDefensor='+idDefensor,
			dataType: 'xml',
			async: false,
			success: function(xml){
				
				$(xml).find('funcionarioDTO').each(function(){
					
					var tipoDefensa = $(this).find("especialidad").find("idCampo").text();
					switch(parseInt(tipoDefensa)){
						case <%=TipoDefensoria.INTEGRACION.getValorId()%>:   
							$("#tipoDefensa").val('Integración');
							break;
						case <%=TipoDefensoria.TECNICA.getValorId()%>:   
							$("#tipoDefensa").val('Tecnica');
							break;
						case <%=TipoDefensoria.RESTAURATIVA.getValorId()%>:   
							$("#tipoDefensa").val('Restaurativa');
							break;
						case <%=TipoDefensoria.EXTERNA.getValorId()%>:   
							$("#tipoDefensa").val('Externa');
							break;
						case <%=TipoDefensoria.ASESORIA.getValorId()%>:   
							$("#tipoDefensa").val('Asesoria');
							break;
					}				

						
					$(xml).find('funcionarioDTO').find('idCampo').each(function(){
						especialidadesDefensor=$(xml).find('valor').text();
					});
				});
					pintaMedios(xml);	
			}
		});
	}
	
	function pintaMedios(xml){
		
		$("#campoEspecialidadDefensor").val($(xml).find('especialidad').find('valor').first().text());
		$("#numeroTelefono").empty();
				
		lstTelefonos = new Array();
		lstCorreos = new Array();
		
		i=0;
		e=0;				
			
		$(xml).find('mediosContacto').find('TelefonoDTO').each(function(){
				
				var codigoPais = $(this).find('codigoPais').text();
				var codigoArea = $(this).find('codigoArea').text();
				var numeroTelefonico = $(this).find('numeroTelefonico').first().text();

				var telefonos = new tel(codigoPais,codigoArea,numeroTelefonico);
				lstTelefonos[i] = telefonos;
				if(i==0){
				$("#numeroTelefono").append(lstTelefonos[i].codigoPais+"-"+lstTelefonos[i].codigoArea+"-"+lstTelefonos[i].numeroTelefonico);
						}else{
							$("#numeroTelefono").append(", "+lstTelefonos[i].codigoPais+"-"+lstTelefonos[i].codigoArea+"-"+lstTelefonos[i].numeroTelefonico+"");
							}
				i++;

			});		

		$("#correo").empty();

		$(xml).find('mediosContacto').find('CorreoElectronicoDTO').each(function(){

				var correoElectronico = $(this).find('direccionElectronica').first().text();
				lstCorreos[e] = correoElectronico;
			
				if(e==0){
					$("#correo").append(lstCorreos[e]);
					}else{
						$("#correo").append(", " + lstCorreos[e]);
						}
				e++;
			});			
		
		}

	function tel(codigoPais,codigoArea,numeroTelefonico){
			this.codigoPais = codigoPais;
			this.codigoArea = codigoArea;
			this.numeroTelefonico = numeroTelefonico;

		}

	function designarAbogadoDefensorCoordinador(){
		var param = '';
		param += 'numSolicitud='+ '<%=request.getParameter("idDocumento")%>';
		param += '&idDefensor='+ idDefensor;
		$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/DesignarAbogadoDefensorExpediente.do',
				data: param,
				dataType: 'xml',
				async: true,
				success: function(xml){	}
			});
		parent.cerrarVentana();
	}

	function solicitarAbogadoDefensorExterno(){
		var forma=<%=Formas.SOLICITUD_DEFENSOR_EXTERNO.getValorId()%>;
		var params = "?esconderArbol=0&formaId="+forma;
			params+= "&numeroUnicoExpediente=<%=request.getParameter("numeroExpedienteSt")%>";
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitar abogado externo", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%=request.getContextPath()%>/generarDocumentoSinCaso.do"+params+"' width='1140' height='400' />");
	}
	
	function seleccionEtapa(){
		var seleccion = $("#cbxTipoDefensa option:selected");					
			grid2(seleccion.val(),true);

		}
 
/**
 * TERMINAN FUNCIONESE PARA ASIGNAR ABOGADO
 */
 <%}%> 
</script>
</head>
<body>
<!--Comienza tabprincipal-->
<div id="tabsprincipalconsulta">
<ul>
	<li id="detalle"><a href="#tabsconsultaprincipal-1">Resumen</a></li>
	<li id="asignarDefensor" style="display: none"><a
		href="#tabsconsultaprincipal-2">Asignar Defensor</a></li>
	<li id="datosInteresado" style="display: none"><a
		href="#tabsconsultaprincipal-3">Datos del interesado</a></li>
	<li id="motivoAsesoria" style="display: none"><a
		href="#tabsconsultaprincipal-4">Motivo de asesoría</a></li>
	<li id="documentos" style="display: none"><a
		href="#tabsconsultaprincipal-5" onclick="documentos()">Documentos</a></li>

</ul>

<!--Comienza tabprincipal 1-->
<div id="tabsconsultaprincipal-1">
<div id="detalleGeneral">

<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="5">
	<tr>
		<td style="vertical-align: top;">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="5">
			<tr id="trCaso">
				<td align="right" width="50%"><strong>Número de caso:</strong>
				</td>
				<td width="50%"><input class="texto" type="text"
					readonly="readonly" id="caso" /></td>
			</tr>
			<tr id="trExpediente">
				<td align="right" width="50%"><strong>Número de
				expediente:</strong></td>
				<td width="50%"><input class="texto" type="text"
					readonly="readonly" id="expediente" /></td>
			</tr>
			<tr id="trEtapaExpediente">
				<td align="right"><strong>Etapa del Expediente:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="etapaExpediente" /></td>
			</tr>
			<tr id="trImputado">
				<td align="right"><strong>Nombre del imputado:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="imputado" /></td>
			</tr>
			<tr id="trParaQuien">
				<td align="right"><strong>Para quien se solicita:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="paraQuien" /></td>
			</tr>
			<tr id="trInteresado">
				<td align="right"><strong>Interesado:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="interesado" /></td>
			</tr>
			<tr id="trTipoAsesoria">
				<td align="right"><strong>Tipo de asesoría:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="tipoAsesoria" /></td>
			</tr>
			<tr id="trDelito">
				<td align="right"><strong>Delito:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="delito" /></td>
			</tr>
			<tr id="trDefensor">
				<td align="right"><strong>Nombre del defensor:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="defensor" /></td>
			</tr>
			<tr id="trFechaDesignacion">
				<td align="right"><strong>Fecha Designación:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaDesignacion" /></td>
			</tr>
			<tr id="trHoraDesignacion">
				<td align="right"><strong>Hora Designación:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaDesignacion" /></td>
			</tr>
		</table>
		</td>
		<td style="vertical-align: top;">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="5">
			<tr id="trTipoAudiencia">
				<td align="right"><strong>Tipo de Audiencia:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="tipoAudiencia" /></td>
			</tr>
			<tr id="trSalaAudiencia">
				<td align="right"><strong>Sala Asignada:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="salaAudiencia" /></td>
			</tr>
			<tr id="trFechaAudiencia">
				<td align="right"><strong>Fecha de Audiencia:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaAudiencia" /></td>
			</tr>
			<tr id="trHoraAudiencia">
				<td align="right"><strong>Hora de Audiencia:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaAudiencia" /></td>
			</tr>
			<tr id="trDetenido">
				<td align="right"><strong>Detenido:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="detenido" /></td>
			</tr>
			<tr id="trLugarDetencion">
				<td align="right"><strong>Lugar donde se encuentra
				detenido:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="lugarDetencion" /></td>
			</tr>
			<tr id="trFechaDetencion">
				<td align="right"><strong>Fecha de Detención:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaDetencion" /></td>
			</tr>
			<tr id="trHoraDetencion">
				<td align="right"><strong>Hora de Detención:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaDetencion" /></td>
			</tr>
			<tr id="trFechaMensaje">
				<td align="right"><strong>Fecha envio del aviso:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaMensaje" /></td>
			</tr>
			<tr id="trHoraMensaje">
				<td align="right"><strong>Hora envio del aviso:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaMensaje" /></td>
			</tr>
			<tr id="trFechaVencimiento">
				<td align="right"><strong>Fecha vencimiento de
				atencion:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaVencimiento" /></td>
			</tr>
			<tr id="trHoraVencimiento">
				<td align="right"><strong>Hora vencimiento de atencion</strong>
				</td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaVencimiento" /></td>
			</tr>
			<tr id="trFechaSolicitud">
				<td align="right"><strong>Fecha Solicitud:</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="fechaSolicitud" /></td>
			</tr>
			<tr id="trHoraSolicitud">
				<td align="right"><strong>Hora Solicitud</strong></td>
				<td><input class="texto" type="text" readonly="readonly"
					id="horaSolicitud" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</div>
</div>
<!--Termina Div Principal 1-->

<div id="tabsconsultaprincipal-2">
<%
	if (asignarDefensor) {
%>
<table width="80%" align="center"
	style="border-left: 1; border-right: 1; border-bottom: 1; border-top: 1;">
	<tr>
		<td align="center">&nbsp;</td>
		<td>&nbsp;</td>
		<td></td>
	</tr>
	<tr>
		<td align="center"><strong>Tipo de defensa: </strong> <select
			id="cbxTipoDefensa">
			<option value="<%=TipoDefensoria.ASESORIA.getValorId()%>">Asesoría</option>
			<option value="<%=TipoDefensoria.RESTAURATIVA.getValorId()%>">Conciliación
			y Mediación</option>
			<option value="<%=TipoDefensoria.INTEGRACION.getValorId()%>">Integración</option>
			<option value="<%=TipoDefensoria.TECNICA.getValorId()%>">Técnica</option>
			<option value="<%=TipoDefensoria.EXTERNA.getValorId()%>">Externo</option>
		</select></td>
		<td colspan="2">&nbsp;
		</td>
	</tr>
	<tr align="center">
		<td width="37%" align="center">&nbsp;</td>
		<td width="23%" align="center">&nbsp;</td>
		<td width="40%" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" valign="top">
		<table width="100%">
			<tr>
				<td width="100%" height="100%">
				<div id="nabtabgrid">
				<center>
				<table id="tabgrid" align="center" ></table>
				</center>
				</div>
				</td>
			</tr>
		</table>
		</td>
		<td>
		<table>
			<tr class="toolbar">
				<td colspan="2" align="center" class="textoBarra">Información
				del Defensor</td>
			</tr>
			<!--<tr >
				  <td>Tipo de defensa:</td>
				  <td><input type="text" class="transpa" disabled="disabled" id="tipoDefensa"></td>
				  </tr>
				<tr>
					<td>Nombre:</td>
					<td>
						<input id="camponombreDefensor" type="text" border="0" class="transpa" disabled="disabled">
					</td>
				</tr>
				<tr>
					<td>Apellido Paterno:</td>
					<td>
						<input id="campoapDefensor" type="text" border="0" class="transpa" disabled="disabled">
					</td>
				</tr>
				<tr>
					<td>Apellido Materno:</td>
					<td>
						<input id="campoamDefensor" type="text" border="0" class="transpa" disabled="disabled">
					</td>
				</tr>-->
			<tr>
				<td>Correo Electrónico:</td>
				<td id="correo"></td>
			</tr>
			<tr>
				<td>Numero de Teléfono:</td>
				<td id="numeroTelefono"></td>
			</tr>
			<tr>
				<td>Especialidad(es):</td>
				<td><input type="text" id="campoEspecialidadDefensor"
					class="transpa" onClick="" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr align="center">
		<td width="37%" align="center">&nbsp;</td>
		<td width="23%" align="center">&nbsp;</td>
		<td width="40%" align="center">&nbsp;</td>
	</tr>
	<tr align="center">
		<td align="center"><input type="button"
			value="Seleccionar Abogado Para Defensa"
			onClick="designarAbogadoDefensorCoordinador()" class="btn_Generico"/></td>
		<td align="center"><input type="button"
			value="Solicitar Abogado Externo" id="solicitarAbogadoExterno"
			onClick="solicitarAbogadoDefensorExterno()" class="btn_Generico"/></td>
		<td width="37%" align="center">&nbsp;</td>
	</tr>
</table>
<center></center>
<%
	}
%>
</div>
<!--Comienza Div Principal 3-->
<div id="tabsconsultaprincipal-3"><!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
<%
	if (tipoSolicitud == 5 || tipoSolicitud == 3) {
%>
<div id="iProbResponsablePane">
<dl>
	<dt id="cejaDatosGenerales">Datos Generales</dt>
	<dd><jsp:include page="datosGeneralesView.jsp" /></dd>
	<dt id="cejaDomicilio">Domicilio</dt>
	<dd><jsp:include page="ingresarDomicilioView.jsp" /></dd>
	<dt id="cejaMediosContacto">Medios de Contacto</dt>
	<dd><jsp:include page="ingresarMediosContactoView.jsp" /></dd>
</dl>
</div>
<%
	}
%>
</div>
<!--Termina Div Principal 3--> <!--Comienza Div Principal 4-->
<div id="tabsconsultaprincipal-4">
<%
	if (tipoSolicitud == 5 || tipoSolicitud == 3) {
%> <textarea
	class="jquery_ckeditor" cols="150" id="editor1" name="editor1"
	rows="10"></textarea> <%
 	}
 %>
</div>
<!--Termina Div Principal 4--> <!--Comienza Div Principal 5-->
<div id="tabsconsultaprincipal-5">
<table id="gridDetalleFrmPrincipal"></table>
<div id="pager1"></div>
<form name="frmDoc"
	action="<%=request.getContextPath()%>/ConsultarContenidoArchivoDigital.do"
	method="post"><input type="hidden" name="documentoId" /></form>
<form name="frmDoc2"
	action="<%=request.getContextPath()%>/GenerarDocumentoDirecto.do"
	method="post"><input type="hidden" name="documentoId" /> <input
	type="hidden" name="numeroUnicoExpediente" /></form>

<form name="formaArchivoD"
	action="<%=request.getContextPath()%>/ConsultarContenidoArchivoDigital.do"
	method="post"><input type="hidden" name="documentoId" /></form>

</div>
<!--Termina Div Principal 5--></div>
<!--Termina Div Principal-->
</body>
</html>
