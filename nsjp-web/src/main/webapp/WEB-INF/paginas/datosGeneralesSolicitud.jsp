<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.OperacionDocumento"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />

<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#paraQuienSolicitaPane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 2px;
				WIDTH: 1030px;
				PADDING-RIGHT: 0px;
				HEIGHT: 362px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#paraQuienSolicitaPane DL {
				WIDTH: 1030px; HEIGHT: 390px
			}
			/*acordeon editar*/
			#paraQuienSolicitaPane DT {
				TEXT-ALIGN: right;
				PADDING-BOTTOM: 16px;
				PADDING-TOP: 2px;
				PADDING-LEFT: 0px;
				LINE-HEIGHT: 35px;
				TEXT-TRANSFORM: none;	
				/*acomodo texto*/PADDING-RIGHT: 40px;
				FONT-FAMILY: Arial, Helvetica, sans-serif;
				LETTER-SPACING: 1px;
				/*distancia persianas*/HEIGHT: 25px;
				COLOR: #f5f5f5;
				FONT-SIZE: 12px;
				FONT-WEIGHT: normal;	
				background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
				background-repeat: no-repeat;
				background-position: 28px;
			}
			#paraQuienSolicitaPane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#paraQuienSolicitaPane DT.hover {
				COLOR: #f5f5f5
			}
			#paraQuienSolicitaPane DT.hover.active {
				COLOR: #f5f5f5
			}
			#paraQuienSolicitaPane DD {
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
			#paraQuienSolicitaPane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#paraQuienSolicitaPane .active .slide-number {
				COLOR: #fff
			}
			#paraQuienSolicitaPane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#paraQuienSolicitaPane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#paraQuienSolicitaPane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#paraQuienSolicitaPane .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
		</style>
		
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<script type="text/javascript"><!--

	//Variables Globales
	
	//Se utilizan para manipular los campos de medios de contacto en el acordeon	
	var idindi;
	var deshabilitarCampos = false;

	//almacena el id del individuo	
	var idIndividuo=0;

	var revisaEsDelitoGraveUno;
	var numExpediente1 = parent.numExpediente;
	var idExpediente = '<%=request.getParameter("idExpediente")%>';
	var id=<%= request.getAttribute("idInvolucrado")%>;
	var idExpediente1 = parent.idExpediente;
	var numeroExpedienteId1= parent.numeroExpedienteId;
	var documentoId1= parent.documentoId;
	//Srive para asociar la etapa al expediente segun de donde venga
	var etapaExpediente= "";
	var lugarDelDetenido="";
	var vardelitos =""; 
	var vParaQuienDEATT = 1;
    var vSolicitanteDEATT = 1;
 	var vMotivoDEATT = 1;
 	var numeroExpediente = 0;
 	var solicitudEnvioId = '<%=request.getParameter("solicitudId")%>';

 	//carga combo alias
	$(function(){		
		
		var el = $("#aliasDEATT").multiselect(),
		selected = $('#selected'),
		newItem = $('#newItem');
		$("#add").click(function(){
			var v = newItem.val(), opt = $('<option />', {
				value: v,
				text: v
			});
			opt.attr('selected','selected');
			opt.appendTo( el );
			el.multiselect('refresh');
			$("#newItem").val('');
		});
	});
 		
	$(document).ready(function() {	
		numeroExpediente = numeroExpedienteId1;
		
		//Inicializa el acordeon
		$('#paraQuienSolicitaPane').easyAccordion({ 
			  autoStart: false, 
			  slideInterval: 3000
			});
		
		//activa la opcion no detenido por default
		$('#RadioDetenido_2').attr('checked', true);
		activaCaso();

		//oculta leyenda de seleccionar delito
		$('#leyendaUnDelitoGrave').hide();
		$('#leyendaNingunDelitoGrave').hide();

		//inicializa la parte visual del editor de texto
		var config = {toolbar:
			[
				['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
				['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
				['NumberedList','BulletedList','-','Outdent','Indent'],
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				['Table','HorizontalRule'],
				'/',
				['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
			]
		};

		$('.jquery_ckeditor').ckeditor(config);

		//Asociamos las funciones a sus respectivos botones	
		
		//Guarda datos del solicitante
		$("#btnGuardarDatos").click(guardaDatosSolicitante);
		//Copia los datos a la pestaña para quien solicita a la de solicitante
		$("#mismaPersona").change(mismoParaQuienSolicita);
		$("#btnGuardarDelitosAg").css("display", "none");		
		//cambia bandera si hay cambio en el solicitante
		$("#nombreDEATT,#aPaternoDEATT,#aMaternoDEATT,#curpDEATT,#rfcDEATT").change(cambioSolicitante);
		//cambia bandera si hay cambio en para quien solicita
		$("#nombreDEATT1,#aPaternoDEATT1,#aMaternoDEATT1,#curpDEATT1,#rfcDEATT1").change(cambioParaQuien);
		//cambia bandera si hay cambio en el motivo
		$("#Motivo").change(cambioSolicitante);
		
		//Asocia date picker al campo de fecha de detencion
		$("#datosGeneralesCmpFechaIngreso1").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		//Asocia time picker al campo de hora de detencion
		$('#datosGeneralesCmpHoraIngreso1').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });			
		
		//Asocia estilo al select de Etapa
//		$("#etapaOptionDEATT").multiselect({ 
//			   multiple: false, 
//			   header: "Seleccione", 
//			   position: { my: 'center', at: 'center'} ,
//			   selectedList: 1 
//			});
		
		//Da estilo a las pestañas principales
		$( "#tabsprincipalconsulta" ).tabs();

		//Asocia estilo al select de Ocupacion
		$("#ocupacionDEATT,#ocupacionDEATT1").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1
			
		}); 
		//Asocia estilo al select de Nacionalidad
		$("#nacionalidadDEATT,#nacionalidadDEATT1").multiselect({ 
			header: "Selecciona de 1 a 3 opciones",
			click: function(e){				
				if( $(this).multiselect("widget").find("input:checked").length > 3 ){
					return false;
				} 
			},
			close: function(){cambiaLenguaDialecto();}		
		});

		//Establece por default los valores al select de nacionalidad e idioma
		$('#nacionalidadDEATT').multiselect().val(2056);
		$('#nacionalidadDEATT').multiselect('refresh');
		$('#nacionalidadDEATT1').multiselect().val(2056);
		$('#nacionalidadDEATT1').multiselect('refresh');
		$('#idiomaDEATT').val(53);		
		$('#idiomaDEATT1').val(53);
		
		//Consulta si el expediente ya habia sido generado lo consulta para pintar sus datos
		if(idExpediente != null){
			$.ajax({
			    type: 'POST', data: "idExpediente="+idExpediente, dataType: 'xml',
			    url: '<%= request.getContextPath()%>'+"/consultarExpedienteDefensoria.do",
				success: function(xml){
					
					imputado = $(xml).find('imputado');
					nombre = $(imputado).find('nombredemografico').first();
					
					$("#nombreDEATT1").val($(nombre).find('nombre').text());
					$("#aPaternoDEATT1").val($(nombre).find('apellidoPaterno').text());
					$("#aMaternoDEATT1").val($(nombre).find('apellidoMaterno').text());
					$("#curpDEATT1").val($(nombre).find('curp').text());
					$("#rfcDEATT1").val($(nombre).find('rfc').text());
					var escol=$(imputado).find('valorIdEscolaridad').find('idCampo').text();
					$('#escoralidadDEATT1').find("option[value='"+escol+"']").attr("selected","selected");
					$('#escoralidadDEATT1').multiselect('refresh');
					var estadoCiv=$(imputado).find('valorIdEstadoCivil').find('idCampo').text();
					$('#estadoCivilDEATT1').find("option[value='"+estadoCiv+"']").attr("selected","selected");
					$('#estadoCivilDEATT1').multiselect('refresh');
					var ocupacion=$(imputado).find('valorIdOcupacion').first().find('idCampo').text();
					$('#ocupacionDEATT1').find("option[value='"+ocupacion+"']").attr("selected","selected");
					$('#ocupacionDEATT1').multiselect('refresh');
					var nacionalidad=$(imputado).find('valorIdNacionalidad').first().find('idCampo').text();
					$('#nacionalidadDEATT1').find("option[value='"+nacionalidad+"']").attr("selected","selected");
					$('#estadoCivilDEATT1').multiselect('refresh');
					var idioma=$(imputado).find('valorIdIdioma').first().find('idCampo').text();
					$('#idiomaDEATT1').find("option[value='"+idioma+"']").attr("selected","selected");
				}
			});
		}

		if(solicitudEnvioId != null){
			$.ajax({
			    type: 'POST', 
			    data: "", 
			    dataType: 'xml',
			    url: '<%= request.getContextPath()%>/obtenerDetalleDefensoria.do?idDocumento='+solicitudEnvioId+'&tipoDocumento=2',
				success: function(xml){
					$(xml).find("solicitud").find("expedienteDTO").each(function(){
							numeroExpedienteId1 = $(this).find("numeroExpedienteId").text();
							idExpediente1 = $(this).find("expedienteId").text();
						});
					$(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO").each(function(){
						var calidad =$(this).find("calidadDTO").find("valorIdCalidad").find("idCampo").text();
						if(calidad == <%=Calidades.DEFENDIDO.getValorId()%>){
							 pintaDatosDomicilio(xml);
							 pintaDatosDefendido(xml);
							 idindi = $(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO").find("elementoId").text();
							 if (typeof idIndividuo === 'undefined' || idIndividuo == 0 || !idIndividuo ){
								 idIndividuo = $(this).find("elementoId").text(); 
							 }
							 mediosContactoTelefonoActualiza();
							 mediosContactoCorreoActualiza();
							 espejoDatos();
						}else if(calidad == <%=Calidades.SOLICITANTE.getValorId()%>){
							//var involucrado = $(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO");
							 //idindi = $(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO").find("elementoId").text();
							 //muestraDatosGenerales(idindi);
							pintaDatosGenerales($(this));
							espejoDatos();
						}
						 $('#motivoDEATT').val($(xml).find("solicitud").find("motivo").text());
					});
				}
			});
		}
		
		//Mata el tab de el domicilio de notificaciones
		killDomicilioNotificaciones();
		$('#liDom').hide();

		//deshabilita ceja detenido
		$("#cejaDetenido").unbind('click');
		$("#datosDetencion").unbind('click');
		
	});

	//recupera los delitos cuando esta detenido
	function recuperaDelitos(){
	
		var ids = jQuery('#gridDelitosAgraviados').getDataIDs();
		
		vardelitos=ids;
		
		var listaDelitos="";
		var delitoPrincipal="";
		
		for(i=0; i < ids.length; i++){
			
		var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',ids[i]);
		var delito = retd.Clave;
		listaDelitos += delito;
		var principal = retd.Principal;
	
		if(principal=="true"){	
			delitoPrincipal = delito +"="+ principal ;			
			}		
		}
		return listaDelitos;
	}
	
	//Asocia la etapa a un expediente dependiendo si esta detenido o no
//	function actualizaEtapaTemp() {
			
//		var param = "";	
//		if(etapaExpediente == <%= EtapasExpediente.INTEGRACION.getValorId() %>){
//				param = 'etapaExpediente=' + etapaExpediente;
//			}else{
//				param = 'etapaExpediente=' + $('#etapaOptionDEATT option:selected').val();
//			 	}
		
//		param += '&documentoId='+documentoId1;
		
//		   $.ajax({
//		     type: 'POST',
//		     url: '<%= request.getContextPath()%>/ActualizarEtapaExpedienteSolicitudDefensoria.do',
//			 data: param,
//			 dataType: 'xml',
//			 success: function(xml){
				
//			  }
//			});
//		 }

      function muestraDatosGenerales(id) {
		  
	   $.ajax({
	     type: 'POST',
	     url: '<%= request.getContextPath()%>/ConsultarListaEstadoExpediente.do',
		 data: 'idInvolucrado='+id,
		 dataType: 'xml',
		 success: function(xml){
			 pintaDatosGenerales(xml);
			 idindi=id;
			 mediosContactoTelefonoActualiza();
			 mediosContactoCorreoActualiza();
			// GuardaSolicitud
		  }
		});
	 }			
			
	/*
	*Funcion que carga el catalogo de estado civil
	*/		
    function cargaEstadoCivil(){
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarListaEstadoCivil.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('catEstadoCivil').each(function(){
    				$('#estadoCivilDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#estadoCivilDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }
		
    /*
	*Funcion que carga el catalago de la ocupacion
	*/	
    function cargaOcupacion(){
       	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catOcupacion').each(function(){
    			
    			$('#ocupacionDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				
    			$('#ocupacionDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			
    			});
    		}
    	});
    }

	/*
	*Funcion que carga el catalago del idioma
	*/		
    function cargaIdioma(){
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoIdioma.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catIdioma').each(function(){
    				$('#idiomaDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#idiomaDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }

    /*
	*Funcion que carga el catalago de la nacionalidad
	*/		
    function cargaNacionalidad(){
    	  	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoNacionalidad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catNacionalidad').each(function(){
    					
    				$('#nacionalidadDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    					
    				$('#nacionalidadDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }

    /*
	*Funcion que carga el catalago de la escolaridad
	*/
    function cargaEscolaridad(){
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoEscolaridad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catEscolaridad').each(function(){
    				$('#escoralidadDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#escoralidadDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    } 
    
  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	function imprimeDatosPadre(nombre, apPat, apMat,nombre1, apPat1, apMat1 ){
		document.getElementById('iSolicitudCmpNombre').value=nombre;
		document.getElementById('iSolicitudCmpApellidoPaterno').value=apPat;
		document.getElementById('iSolicitudCmpApellidoMaterno').value=apMat;
		document.getElementById('iSolicitudCmpNombre1').value=nombre1;
		document.getElementById('iSolicitudCmpApellidoPaterno1').value=apPat1;
		document.getElementById('iSolicitudCmpApellidoMaterno1').value=apMat1;
	}

	/**
	 *Limpia todos los campos de esta página
	 */
	function cleanDatosGenerales(){		

		$('#iSolicitudCmpNombre').val("");
		$('#iSolicitudCmpApellidoPaterno').val("");
		$('#iSolicitudCmpApellidoMaterno').val("");
		$('#nombreDEATT').val("");
		$('#aPaternoDEATT').val("");
		$('#aMaternoDEATT').val("");
		$('#curpDEATT').val("");
		$('#rfcDEATT').val("");
				
		//Limpia todos los combo box
		$('#estadoCivilDEATT option:selected').val(""); 
		$('#estadoCivilDEATT').multiselect('refresh');
		$('#escoralidadDEATT option:selected').val("");
		$('#escoralidadDEATT').multiselect('refresh');
		
		//combos  multi select
		$('#aliasDEATT option:selected').val(""); 
		$('#aliasDEATT').multiselect('refresh');
		$('#ocupacionDEATT option:selected').val("");
		$('#ocupacionDEATT').multiselect('refresh');
					
	}

	function mandaDatosPadre(){
       
    	var valorCampoNombre=$('#nombreDEATT').val();
		var valorCampoApPat=$('#aPaternoDEATT').val();
		var valorCampoApMat=$('#aMaternoDEATT').val();
		var valorCampoCurp=$('#curpDEATT').val();
		var valorCampoRfc=$('#rfcDEATT').val();
		var valorCampoFechaNac=$('#datosNacimientoCmpFechaNacimiento').val();
		var valorCampoEdadAproximada=$('#datosNacimientoCmpEdadAproximada').val();
        validaDatosFormatoTipo(valorCampoNombre, valorCampoApPat,valorCampoApMat,valorCampoCurp,valorCampoRfc,valorCampoFechaNac,valorCampoEdadAproximada);
	}
	
	//Guarda los datos de la persona solicitante
	function guardaDatosSolicitante(){
	
		if(!isEmpty($('#nombreDEATT').val()) && !isEmpty($('#aPaternoDEATT').val())){
	        var parametros = 'nombre=' + $('#nombreDEATT').val();
	        parametros += '&documentoId='+documentoId1;
	        parametros += '&idExpediente='+idExpediente1;
	        parametros += '&numExpediente='+numExpediente1;
	        parametros += '&apellidoPaterno=' +  $('#aPaternoDEATT').val();
	        parametros += '&apellidoMaterno=' + $('#aMaternoDEATT').val();
	        parametros += '&curp=' + $('#curpDEATT').val();
	        parametros += '&rfc=' + $('#rfcDEATT').val();
	        parametros += '&idioma=' + $('#idiomaDEATT option:selected').val();        
	        parametros += '&escolaridad=' + $('#escoralidadDEATT option:selected').val();
	        parametros += '&estadoCivil=' + $('#estadoCivilDEATT option:selected').val();
	        parametros += '&sexo=' + $(':radio[name=rbtSexoDatosGenerales]:checked').val();
	       
	       if($('#ocupacionDEATT').val()== null){
		    	   parametros += '&ocupacion=-1';
		           }else{
	        			parametros += '&ocupacion=' + $('#ocupacionDEATT').val();
	        }

	       if($('#ocupacionDEATT').val()== null){
	    	  		 parametros += '&nacionalidad=-1';
	           		}else{
	           			parametros += '&nacionalidad=' + $('#nacionalidadDEATT').val();
	        }

	       $.ajax({
			     type: 'POST',
			     url: '<%= request.getContextPath()%>/GuardaSolicitud.do',
				 data: parametros,
				 dataType: 'xml',
				 success: function(xml){				 
					 if($(xml).find('involucradoDTO').first().find('elementoId')){
							customAlert("Se guardó con éxito el solicitante");
							vSolicitanteDEATT = 0;		 
					 }else{
						 customAlert("No se registró la información");	
					 }	
				  }
				});
			 return parametros;
		}else{
			customAlert("Los campos nombre y apellido paterno son obligatorios");
		}		
	}

	//Guarda los datos de la persona para la que se solicita (Involucrado)
	function obtenerParametrosParaQuien(){

		if(!isEmpty($('#nombreDEATT1').val()) && !isEmpty($('#aPaternoDEATT1').val())){
			if($('#cmbEtapaExpediente option:selected').val() != -1){

				vParaQuienDEATT = 0;				 
				
		        var parametros = 'nombre=' + $('#nombreDEATT1').val();
		        parametros += '&apellidoPaterno=' +  $('#aPaternoDEATT1').val();
		        parametros += '&apellidoMaterno=' + $('#aMaternoDEATT1').val();
				parametros += '&detenido=' +  $(':radio[name=RadioDetenido2]:checked').val(); 
		        parametros += '&fechaIngreso=' + $('#datosGeneralesCmpFechaIngreso1').val();
		        parametros += '&horaIngreso=' + $('#datosGeneralesCmpHoraIngreso1').val();
		        parametros += '&numExpediente='+numExpediente1;
		        parametros += '&idExpediente='+idExpediente1;
		        parametros += '&idNumeroExpediente='+numeroExpedienteId1;
		        parametros += '&lugarDelDetenido=' + $('#lugarDetenido').val();
		        parametros += '&documentoId='+documentoId1;
		        parametros += '&curp=' + $('#curpDEATT1').val();
		        parametros += '&rfc=' + $('#rfcDEATT1').val();
		        parametros += '&idioma=' + $('#idiomaDEATT1 option:selected').val();        
		        parametros += '&religion=' + $('#ReligionDEATT1 option:selected').val();
		        parametros += '&escolaridad=' + $('#escoralidadDEATT1 option:selected').val();
		        parametros += '&estadoCivil=' + $('#estadoCivilDEATT1 option:selected').val();
		        parametros += '&sexo=' + $(':radio[name=rbtSexoDatosGenerales1]:checked').val();
		        parametros += '&alias=' + $('#aliasDEATT1').val();
		        parametros += '&etapaExpediente='+$('#cmbEtapaExpediente option:selected').val();

		        //manda 0 si es un nuevo involucrado o el Id si se modifica el involucrado ya guardado
				parametros += '&idIndividuo='+ idIndividuo; 
		        
		        if($('#ocupacionDEATT1').val()== null){
		     	   parametros += '&ocupacion=-1';
		            }else{
		         parametros += '&ocupacion=' + $('#ocupacionDEATT1').val();
		         }

		        if($('#ocupacionDEATT1').val()== null){
		     	   parametros += '&nacionalidad=-1';
		            }else{
		            parametros += '&nacionalidad=' + $('#nacionalidadDEATT1').val();
		         }
		        // Obtiene el domicilio
				parametros += obtenerParametrosDomicilio(); 
				//Obtiene los medios de contacto
				parametros += obtenerMedios(); 

				 $.ajax({
				     type: 'POST',
				     url: '<%= request.getContextPath()%>/GuardaSolicitante.do',
					 data: parametros,
					 dataType: 'xml',
					 success: function(xml){
						 if($(xml).find('involucradoDTO').first().find('elementoId')){
							 guardarDelitosAgraviadosExp();
							 idIndividuo=0;
							 idIndividuo=$(xml).find('involucradoDTO').first().find('elementoId').text();
							 customAlert("Se guardó con éxito la persona para quien solicita");	
						 }else{
							 customAlert("No se registró la información");	
							 vParaQuienDEATT = 1;				 
						 }	
					 }
				});
				 return parametros;
			}else{
				customAlert("Debe seleccionar una etapa para el expediente");
			}
		}else{
			customAlert("Los campos nombre y apellido paterno son obligatorios");
		}		
	}	

	//Guarda el motivo
    function guardaMotivo(){
    	
    	var numExpediente2 ='&numExpediente='+numExpediente1;
    	var idExpediente2 ='&idExpediente='+idExpediente1;
    	var numeroExpedienteId2='&numeroExpedienteId='+numeroExpedienteId1;
    	var documentoId2 ='&documentoId='+documentoId1;
    	var params ='motivo=' + $('#motivoDEATT').val();
        $.ajax({		    	      
        	  url: '<%= request.getContextPath()%>/GuardaMotivo.do',
        	  dataType: 'xml',
        	  Type: 'POST',
        	  data: params+numExpediente2+idExpediente2+numeroExpedienteId2+documentoId2,
        	  async: false,
        	  success: function(xml) {
		        customAlert("Se guardó con éxito");
		        vMotivoDEATT = 0;
		        $("#btnGenera").removeAttr('disabled');        	     		
        	  }	    		
    		  });
          
    	}

  //cambia la bandera del solicitante para poder guardar otra vez	
	 function cambioSolicitante(){               
		 vSolicitanteDEATT = 1;					
				}
	//cambia la bandera del para quien solicita para poder guardar otra vez	
	 function cambioParaQuien(){		 		 
		vParaQuienDEATT = 1;					
		}
	//cambia la bandera del solicitante para poder guardar otra vez	
	function cambioMotivo(){
		vMotivoDEATT = 1;				
	}

	//Valida por pestaña al momento de guardar
   function guardado(){
	    
		if(vSolicitanteDEATT != 0){		
				customAlert("No se han guardado los datos para el solicitante");
		}
		if(vParaQuienDEATT != 0){
				customAlert("No se han guardado los datos para quien solicita");
		}
		if(vMotivoDEATT != 0){
				customAlert("No se ha guardado el motivo");
		}				    	
	   	if((vSolicitanteDEATT==0)&&(vParaQuienDEATT==0)&&(vMotivoDEATT==0)){ 

	   		actualizaEstatusSolicitudDefensoria();	 
			//if( etapaExpediente == '<%= EtapasExpediente.INTEGRACION.getValorId() %>'){
				//actualizaEtapaTemp();
	    		//generarDocumentoSinCaso();
			//}else{
				//agregarEtapa();
				//}
    	}	
   	}

	function actualizaEstatusSolicitudDefensoria(){
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/atualizarEstatusSolicitudDefensoria.do?solicitudId='+documentoId1+'',
			dataType: 'xml',
			async: false,
			success: function(xml){
			}
		});
		customAlert("La solicitud se actualizó con éxito");
		generarDocumentoSinCaso();
	}
	
	 function recuperaDatosDatosGenerales(idCalidad){
		   var lsDatosGenerales="";
		   lsDatosGenerales="nombre="+$("#nombreDEATT").val();
		   lsDatosGenerales+="&alias="+$("#aliasDEATT option:selected").val();
		   lsDatosGenerales+="&aPaterno="+$("#aPaternoDEATT").val();
		   lsDatosGenerales+="&ocupacion="+$("#ocupacionDEATT option:selected").val();
		   lsDatosGenerales+="&aMaterno="+$("#aMaternoDEATT").val();
		   lsDatosGenerales+="&nacionalidad="+$("#nacionalidadDEATT option:selected").val();
		   lsDatosGenerales+="&curp="+$("#curpDEATT").val();
		   lsDatosGenerales+="&rfc="+$("#rfcDEATT").val();
		   lsDatosGenerales+="&fIngreso="+$("#datosGeneralesCmpFechaIngreso").val();
		   lsDatosGenerales+="&idioma="+$("#idiomaDEATT option:selected").val();
		   lsDatosGenerales+="&religion="+$("#ReligionDEATT option:selected").val();
		   lsDatosGenerales+="&escolaridad="+$("#idiomaDEATT option:selected").val();
		   return lsDatosGenerales;
	   }

	   function pintaDatosGenerales(xml){
		   
		   $('#nombreDEATT').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').first().text());
		   $('#aPaternoDEATT').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').first().text());
		   $('#aMaternoDEATT').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').first().text());
		   $('#curpDEATT').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('curp').text());
		   $('#rfcDEATT').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('rfc').text());
		   if ($(xml).find('nombresDemograficoDTO').find('sexo').text() == "masculino"){
	    		 $('#datosGeneralesCmpSexoM').attr('checked','checked');
	    		
	    	 }else{
	    		 $('#datosGeneralesCmpSexoF').attr('checked','checked');
	    	 } 
	   }
		 			 
	   function pintaDatosDefendido(xml){
		   
		   $('#nombreDEATT1').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').first().text());
		   $('#aPaternoDEATT1').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').first().text());
		   $('#aMaternoDEATT1').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').first().text());
		   $('#curpDEATT1').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('curp').first().text());
		   $('#rfcDEATT1').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('rfc').first().text());
		   if ($(xml).find('nombresDemograficoDTO').find('sexo').text() == "M"){
		 		$('#datosGeneralesCmpSexoM1').attr('checked','checked');
		 		
           }else{
		 		$('#datosGeneralesCmpSexoF1').attr('checked','checked');
		   } 

		   var escol=$(xml).find('valorIdEscolaridad').find('idCampo').first().text();
		   $('#escoralidadDEATT1').find("option[value='"+escol+"']").attr("selected","selected");
		   var estadoCiv=$(xml).find('valorIdEstadoCivil').find('idCampo').first().text();
		   $('#estadoCivilDEATT1').find("option[value='"+estadoCiv+"']").attr("selected","selected");
		   var ocupacion=$(xml).find('valorIdOcupacion').find('idCampo').first().text();
		   $('#ocupacionDEATT1').find("option[value='"+ocupacion+"']").attr("selected","selected");
		   var nacionalidad=$(xml).find('valorIdNacionalidad').find('idCampo').first().text();
		   $('#nacionalidadDEATT1').find("option[value='"+nacionalidad+"']").attr("selected","selected");
		   var etapaExpediente=$(xml).find("expedienteDTO").find('etapa').find('idCampo').first().text();
		   $('#cmbEtapaExpediente').find("option[value='"+etapaExpediente+"']").attr("selected","selected");
	   }

	   /*
	    *Funcion para pintar los datos mediante la recuperacion del xml de datos de nacimiento
	    */
	   function pintaDatosNacimiento(xml){		   				

		   $('#datosNacimientoCmpFechaNacimiento').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('fechaNacimiento').text());
		   $('#datosNacimientoCmpEdadAproximada').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('edadAproximada').text());
		   
	   	   var id0=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('paisValorDTO').find('idCampo').text();
	   	   $('#cbxPaisNacimiento').find("option[value='"+id0+"']").attr("selected","selected");

	   		onSelectChangePaisNacimiento();
	   	   
	   	   var id1=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('entidadFederativaDTO').find('entidadFederativaId').text();
	   	   $('#cbxEntFederativaNacimiento').find("option[value='"+id1+"']").attr("selected","selected");

	   		onSelectChangeEntFedNacimiento();

	   		var id2=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('municipioDTO').find('municipioId').text();
		   	   $('#cbxCiudadNacimiento').find("option[value='"+id2+"']").attr("selected","selected");

	   	   }		 
	   
	   
	   /*
	   *Funcion para revisar la calidad del indiviudo cuando se ingresara un representante legal
	   */
	   function ocultaCamposPorCalidadIndividuo(){
		   if((gingRepLegCalidadDelIndividuo!=null) && (gingRepLegCalidadDelIndividuo!=""))
		   {
				if(gingRepLegCalidadDelIndividuo="REPRESENTANTE_LEGAL")
				{
					//ocultamos los campos que no se deben mostrar cuando es un representante legal
					$("#trAliasTxt").hide();
					$(".tdAliasCmp").hide();
				}
		   }
	   }
	   
	   function selectTipoExpediente(){
			if($("#tipoExpediente").val()=="tecnico"){
				$("#divrestaurativa").css("display", "none");
				$("#divtecnico").css("display", "block");
				$("#divadscrito").css("display", "none");
			}
			
			if($("#tipoExpediente").val()=="adscrito"){
				$("#divrestaurativa").css("display", "none");
				$("#divtecnico").css("display", "none");
				$("#divadscrito").css("display", "block");
			}
			
			if($("#tipoExpediente").val()=="restaurativa"){
				$("#divrestaurativa").css("display", "block");
				$("#divtecnico").css("display", "none");
				$("#divnormal").css("display", "none");
				
			}
			
		}


	   function espejoDatos(){
		   
			var valorCampoNombre;
			var valorCampoApPat;
			var valorCampoApMat;
			var valorCampoNombre1;
			var valorCampoApPat1;
			var valorCampoApMat1;
			
			valorCampoNombre=document.getElementById('nombreDEATT').value;
			valorCampoApPat=document.getElementById('aPaternoDEATT').value;
			valorCampoApMat=document.getElementById('aMaternoDEATT').value;
			valorCampoNombre1=document.getElementById('nombreDEATT1').value;
			valorCampoApPat1=document.getElementById('aPaternoDEATT1').value;
			valorCampoApMat1=document.getElementById('aMaternoDEATT1').value;				
			//llama a la funcion que escribe los datos en la ventana padre
			imprimeDatosPadre(valorCampoNombre, valorCampoApPat,valorCampoApMat, valorCampoNombre1, valorCampoApPat1,valorCampoApMat1);
		}
	 
   	//genera un documento con forma 7
    function generarDocumentoSinCaso() {
    	formaID=7;
		document.frmDoc2.numeroUnicoExpediente.value = numExpediente1;
		document.frmDoc2.formaId.value = formaID;
		document.frmDoc2.texto.value = $('#motivoDEATT').val();
		document.frmDoc2.submit();		    		
    }
    
	//Carga el catalogo de etapas del expediente
//    function cargaEstadoExpediente(){
    	
//    	$.ajax({
//    		type: 'POST',
//    		url: '<%=request.getContextPath()%>/ConsultarListaEstadoExpediente.do',
//    		data: '',
//    		dataType: 'xml',
//    		async: false,
//    		success: function(xml){
//    			var option;
//    			$(xml).find('catEstadoExpediente').each(function(){ 
//        			     option = $(this).find('clave').text(); 			
//    				if( option == <%=EtapasExpediente.TECNICA.getValorId()%> || option == <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%> ){
//	    	  			$('#etapaOptionDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
//	    	  		}
//    			});
//    		}
//    	});
//    }

	//Cambia la etiqueta de idioma
    function cambiaLenguaDialecto(){

    	if($('#nacionalidadDEATT').val()!=2056){
	    		$('#idiomaNacional').hide();	
		    	$('#idiomaExtranjero').show();		   
	    	}else{			    	
	    		$('#idiomaNacional').show();	
		    	$('#idiomaExtranjero').hide();			    		
	    	}
	    }
	//Copia los datos de la pestaña para quien solicita a solicitante
	function mismoParaQuienSolicita(){
		
		if ($("#mismaPersona").is(':checked')) {
			$("#nombreDEATT").val($("#nombreDEATT1").val());
			$("#aPaternoDEATT").val($("#aPaternoDEATT1").val());
			$("#aMaternoDEATT").val($("#aMaternoDEATT1").val());
			$("#curpDEATT").val($("#curpDEATT1").val());
			$("#rfcDEATT").val($("#rfcDEATT1").val());
			$("#ocupacionDEATT").multiselect().val($("#ocupacionDEATT1 option:selected").val());
			$('#ocupacionDEATT').multiselect('refresh');
			$("#nacionalidadDEATT").multiselect().val($("#nacionalidadDEATT1 option:selected").val());
			$('#nacionalidadDEATT').multiselect('refresh');
			$("#idiomaDEATT").val($("#idiomaDEATT1 option:selected").val());
			$("#escoralidadDEATT").val($("#escoralidadDEATT1 option:selected").val());
			$("#estadoCivilDEATT").val($("#estadoCivilDEATT1 option:selected").val());
			espejoDatos();
		}else{
			cleanDatosGenerales();
		}
	}
	//Habilita las pestañas de delitos y centro de detencion
	function activaCaso(){
		
		if($('#RadioDetenido_2').is(':checked')){
			$("#cejaDetenido").unbind('click');
			$("#datosDetencion").unbind('click');

			$('#cmbEtapaExpediente').empty();
			$('#cmbEtapaExpediente').append('<option value="-1">-Seleccione-</option>');
			$('#cmbEtapaExpediente').append('<option value="'+ <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>+ '">Conciliación y Mediación</option>');
			$('#cmbEtapaExpediente').append('<option value="' + <%=EtapasExpediente.TECNICA.getValorId()%> + '">Técnica</option>');
			$('#cmbEtapaExpediente').attr('disabled','');
			
			if($("#cejaDetenido").hasClass('active')){
				$("#cejaDetenido").removeClass('active').addClass('inactive');
				$("#cejaDetenido").parent().find('dt.no-more-active:first').click();
				$("#datosDetencion").removeClass('active').addClass('inactive');
				$("#datosDetencion").parent().find('dt.no-more-active:first').click();
			}else{
				$("#cejaDetenido").removeClass('no-more-active').addClass('inactive');
				$("#datosDetencion").removeClass('no-more-active').addClass('inactive');
			}
				
		}else{

			$('#cmbEtapaExpediente').empty();
			$('#cmbEtapaExpediente').append('<option value="' + <%=EtapasExpediente.INTEGRACION.getValorId()%> + '">Integración</option>');
		    $('#cmbEtapaExpediente').find("option[value='"+<%=EtapasExpediente.INTEGRACION.getValorId()%>+"']").attr("selected","selected");
			$('#cmbEtapaExpediente').attr('disabled','disabled');

			$("#cejaDetenido").removeClass('inactive').addClass('no-more-active');
			$("#cejaDetenido").click(function(){	
					
			jQuery($("#cejaDetenido")).activateSlide();
	
			});			
			$("#datosDetencion").removeClass('inactive').addClass('no-more-active');
			$("#datosDetencion").click(function(){	
				
				jQuery($("#datosDetencion")).activateSlide();
		
				});	

			$("#cejaDetenido").click();
				}
			
		}

	/*
	*Funcion para asociar los delitos del grid de agraviados con el expediente
	*/
	function guardarDelitosAgraviadosExp(){
		//obtenemos los ID's de los renglones del Grid de Agraviados
		var arrayIDs = new Array() ;
		var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
		//validamos que haya delitos en el grid de agraviados
		if(arrayIDs.length<1)
		{
			return;
		}
		//revisamos que si hay un delito grave se haya seleccionado
		if(existeDelitoGraveEnGrid())
		{
			if(parseInt(existeUnDelitoPrincipalGraveSeleccionado())==0)
			{
				customAlert("Debe seleccionar un delito grave como principal");
				return;
			}	
		}
		//obtenemos el ID del delito principal
		var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
		var nombreDelPrincipal=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal).Tipo;
		var delitosNormales="";			
		//barremos el grid para generar la cadena de IDs de los delitos normales
		for (i=0;i<arrayIDs.length;i++)
		{
			if(arrayIDs[i]!=idDelPrincipal)
			{
				var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
				if(delitosNormales.length>0)
				{
					delitosNormales=delitosNormales+","+retd.Tipo;//arrayIDs[i];
				}
				else
				{
					delitosNormales=""+retd.Tipo;//arrayIDs[i];
				}
			}
		} 
		
		//ahora mandamos los delitos al back-end
		var params="delitos="+nombreDelPrincipal+"-"+delitosNormales+"&numExp="+numExpediente1;

		$.ajax({
       	  url: '<%= request.getContextPath()%>/guardarDelitosAgraviadosATP.do',
    	  dataType: 'xml',
    	  Type: 'POST',
    	  data:params,
    	  async: false,
    	  success: function(xml){
    		  if(parseInt($(xml).find('code').text())==0)
    		  {
    			  customAlert("Se guardaron correctamente los delitos seleccionados");
    		  }	 
    		  else
    		  {
    			  isDelitoSaved=false;
    			  customAlert("Ocurrió un error al tratar de guardar los delitos agraviados");
    		  }   			    		
		  }
    	});
	}

	/*
	* Funcion que recorre el grid de delitos agraviados para revisar si existe 
	*un delito grave que no fue seleccionado como principal, de existir regresa true en 
	*caso contrario regresa false
	*/
	function existeDelitoGraveEnGrid()
	{
		var bandera=false;
		//obtenemos los ID's de los renglones del Grid
		var arrayIDs = new Array() ;
		var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
		//barremos el grid para revisar si hay por lo menos un delito marcado como grave
		for (i=0;i<arrayIDs.length;i++)
		{
			//revisamos el checkbox del renglon i-esimo para ver si es grave
			var isGrave=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
			if(isGrave.Gravedad=="Yes")
			{
				bandera=true;
			}
		} 
		return bandera;
	}


	/*
	*Funcion para saber si se selecciono un delito como principal
	*/
	function existeUnDelitoPrincipalGraveSeleccionado()
	{
		var bandera=0;
		//obtengo el ID del rdb del delito seleccionado
		var idRdb="";
		idRdb=$('input[name=gridDelitos]:checked').attr('id');
		if(idRdb!=null)
		{
			idRdb=idRdb.split('_')[1];
			if(idRdb!="")
			{
				//reviso si el delito seleccionado es grave o no
				var resp=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRdb);
				if(resp.Gravedad=="Yes")
				{
					//prendemos la badnera al encontrar un radio seleccionado
					bandera=1;	
				}
			}	
		}
		else
		{
			customAlert("Debe seleccionar un delito principal para poder guardar");	
			bandera=2;
		}
		return bandera;
	}

	/**
	 * Permite saber si una cadena es nula o esta vacia
	 * @param {Object} str Cadena
	 * @return {Boolean} True si es nula la cadena o vacia, false en caso contrario
	 */		
	  function isEmpty(cadena){ 
		if ( null == cadena || "" == cadena ) 
			return true; 
		else
			return false; 	   
	  }

	 </script>

</head>
	<body>
		<!-- Inicia Tab principal -->
		<div id="tabsprincipalconsulta">
		<ul>
		  <li id="tabParaQuienSolicita"><a href="#tabsconsultaprincipal-1">Para Quien Solicita</a></li>
		  <li><a href="#tabsconsultaprincipal-2">Solicitante</a></li>
		  <li><a href="#tabsconsultaprincipal-3">Motivo de solicitud</a></li>
		</ul>
			<!-- Inicia Tab 1 -->
			<div id="tabsconsultaprincipal-1" >
				<TABLE width="100%" height="176" border=0 class="back_denuncia"	id=iDenuncianteWorkSheet align="center">
				  <TBODY>
				    <TR vAlign=top>
				    </TR>
				    <TR vAlign=top>
				      <TD width="37%" align="center" valign="middle">
				      	<table width="400px" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
				          <tr>
				            <td width="21" height="109">&nbsp;</td>
				            <td width="60"><img id=iVictimaCmpFoto alt=foto	src="<%=request.getContextPath()%>/resources/images/img_denunciante.png"></td>
				            <td width="276">
				            	<table align="center" width="200px">
					                <tr>
					                  <td align="right">Detenido</td>
					                  <td align="right">No<input type="radio" name="RadioDetenido2" value="false" id="RadioDetenido_2" onclick="activaCaso()" /></td>
					                  <td>Si<input type="radio" name="RadioDetenido2" value="true" id="RadioDetenido_3" onclick="activaCaso()"/></td>
					                </tr>
					                <tr>
					                  <td align="center" colspan="3">Etapa del Expediente</td>
					                </tr>
					                <tr>
									  <td colspan="3">
									  	<select id="cmbEtapaExpediente" style="width: 180px;" tabindex="10"></select>
								  	  </td>
					                </tr>
				             	 </table>
				            </td>
				          </tr>
				        </table>
				      </TD>
				      <TD width="45%" align="left" valign="top">
				      	<table width="92%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td height="23">&nbsp;</td>
				          </tr>
				        </table>
				        <TABLE style="BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px" width="50%" class=celda2 cellSpacing=0 cellPadding=0 height=91>
				          <TBODY>
				            <TR>
				              <TD width="36%" height=25 align=left class="txt_gral_victima">Nombre:</TD>
				              <TD width="64%"><INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; BORDER-RIGHT: 1px solid #c0c2c4"
				          			id=iSolicitudCmpNombre1 title="Escribir nombre" readOnly maxLength=40 size=30 type=text></TD>
				            </TR>
				            <TR>
				              <TD width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
				              <TD height=28 width="64%"><INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; BORDER-RIGHT: 1px solid #c0c2c4"
				                  id=iSolicitudCmpApellidoPaterno1 readOnly maxLength=40 size=30 type=text></TD>
				            </TR>
				            <TR>
				              <TD width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
				              <TD height=29><INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; BORDER-RIGHT: 1px solid #c0c2c4"
				                 id=iSolicitudCmpApellidoMaterno1 readOnly maxLength=40 size=30 type=text></TD>
				            </TR>
				          </TBODY>
				        </TABLE>
				        <table width="64%%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td>&nbsp;</td>
				          </tr>
				        </table>
				        <table width="64%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td height="23" align="left"><INPUT type=button	class="btn_guardar" id=btnGuardaParaQuien value=Guardar onclick="obtenerParametrosParaQuien()"></td>
				          </tr>
				        </table></TD>
				    </TR>
				    <TR vAlign=top>
				      <TD colSpan=3>&nbsp;</TD>
				    </TR>
				  </TBODY>
				</TABLE>
				<!-- Inicia Acordeon -->
				 <div id="paraQuienSolicitaPane">
		                  <dl>
		                    <dt id="cejaDatosGenerales">Datos Generales</dt>
		                      <dd>	
		                       <jsp:include page="datosSolicitud2.jsp"></jsp:include>
		                      </dd>
		                    <dt id="cejaDomicilio">Domicilio</dt>
		                      <dd>
		                    	<jsp:include page="ingresarDomicilioView.jsp"></jsp:include>
		                      </dd>
		                    <dt id="cejaMediosContacto">Medios de Contacto</dt>
		                       <dd>
		                         <jsp:include page="ingresarMediosContactoView.jsp"/>
		                       </dd>
		                    <dt id="cejaDetenido">Delitos</dt>
		                       <dd>
								<jsp:include page="seleccionarDelitoView.jsp"></jsp:include>							
		                       </dd>
		                     <dt id="datosDetencion">Datos de detención</dt>
		                       <dd>
		                       	<jsp:include page="datosDetencion.jsp"/>
		                        </dd>
		                 </dl>
		         </div>
		         <!-- Termina Acordeon -->
		</div>
		<!-- Termina Tab 1 -->
		
		<!-- Inicia Tab 2 -->
		<div id="tabsconsultaprincipal-2">
			 <TABLE  align="center" width="1025px" height="176" border=0 class="back_denuncia">
		        <TBODY>
		        <TR vAlign=top>
		        </TR>	
		        <TR vAlign=top>
		          <TD width="37%" align="center" valign="middle">
		          <table width="360" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
		            <tr>
		                <td width="10" height="109">&nbsp;</td>
		                <td width="4">&nbsp;</td>
		                <td width="123"><IMG id=iVictimaCmpFoto alt=foto src="<%= request.getContextPath() %>/resources/images/img_denunciante.png"></td>
		                
		           </tr>
		          </table>
		         </TD>
		             
		         <TD width="45%" align="left" valign="top">
		           <table width="92%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td height="23">&nbsp;</td>
		              </tr>
		            </table>
		            <TABLE style="BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px" class=celda2 cellSpacing=0 cellPadding=0 width="63%" height=91>
		              <TBODY>
		              <TR>
		                <TD width="36%" height=25 align=left class="txt_gral_victima">Nombre:</TD>
		                <TD width="64%">
		                	<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
		                                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpNombre title="Escribir nombre" readOnly maxLength=40 size=30 type=text>
		                </TD>
		              </TR>
		              <TR>
		                <TD width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
		                <TD height=28 width="64%">
		                	<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
		                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoPaterno readOnly maxLength=40 size=30 type=text>
		                </TD>
		              </TR>
		              <TR>
		                <TD width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
			            <TD height=29>
			            	<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
			                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoMaterno readOnly maxLength=40 size=30 type=text>
		                </TD>
		          	  </TR>
		         	  </TBODY>
		            </TABLE>
		           <table width="64%%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td>&nbsp;</td>
		              </tr>
		            </table>
		            <table width="64%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td height="23" align="left"><INPUT type=button class="btn_guardar"  id="btnGuardarDatos" value="Guardar" ></td>
		              </tr>
		          	</table>
		          </TD>
		       </TR>
			   <TR vAlign=top>
		       <TR vAlign=top>
		          <TD colSpan=3>&nbsp;</TD>
		        </TR>
		        </TBODY>
		    </TABLE> 
				<jsp:include page="datosSolicitud.jsp"></jsp:include>
						
		</div>
		<!-- Termina Tab 2 -->
			
		<!-- Inicia Tab 3 -->
		<div id="tabsconsultaprincipal-3">
			<center>	
				<table width="100%">
					<tr>
						<td align="right">
							<input type="button" id="btnGuardar" value="Guardar" onclick="guardaMotivo()" class="btn_guardar" />
							<input type="button" id="btnGenera" value="Generar Acuse de Atención" onclick="guardado()" disabled="disabled" class="btn_Generico"/>
						</td>	
					</tr>
				</table>				
			<textarea class="jquery_ckeditor" name="motivoDEATT" cols="70" rows="5" id="motivoDEATT"></textarea>
			<br/>
			</center>
		</div>
		<!-- Termina Tab 3 -->
	</div>
	<!-- Termina Tab principal -->

	<!-- Forma para generar el documento asociado a la solicitud -->
	<form name="frmDoc2" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
		<input type="hidden" name="formaId" />
		<input type="hidden" name="texto" />
		<input type="hidden" name="numeroUnicoExpediente" />
	</form>
</body>
<script>
	cargaEstadoCivil();
	cargaOcupacion();
	cargaNacionalidad();
	cargaEscolaridad();	
	cargaIdioma();	
</script>
</html>