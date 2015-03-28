<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" >
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />  	
      
	<style type="text/css">
	.texto{
		width: 225px; 
		border: 0; 
		background: #DDD;
	}
	.transpa {
		background-color: transparent;
		border: 0;
	}
			DD P {
				LINE-HEIGHT: 120%
			}
			#cedulaIdentificacion {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 2px;
				WIDTH: 1030px;
				PADDING-RIGHT: 0px;
				HEIGHT: 380px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#cedulaIdentificacion DL {
				WIDTH: 1030px; HEIGHT: 390px
			}
			/*acordeon editar*/
			#cedulaIdentificacion DT {
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
			#cedulaIdentificacion DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#cedulaIdentificacion DT.hover {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DT.hover.active {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DD {
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
			#cedulaIdentificacion .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#cedulaIdentificacion .active .slide-number {
				COLOR: #fff
			}
			#cedulaIdentificacion A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#cedulaIdentificacion DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#cedulaIdentificacion H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#cedulaIdentificacion .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
			
				<!--ESTILOS PARA LAS TABS-->
	
	
		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 600px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
		
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
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu Defensor</title>
	
	<script type="text/javascript">
	     
		//variables globales
		
		//id del involulcrado para cuando viene de carpeta de investigacion en etapa tecnica
	 	//var idInvolucrado=parent.involucradoID;
	 	//var para cambiar la calidad del individuo a guardar por invitado
	 	
	 	//habilita los campos de datos generales cuando se llama la funcion de pinta datos
	 	var deshabilitaDatos = "habilita";
	 	//variable que se utiliza en medios de contacto
	 	var idindi;
	 	var idNumeroExpedienteOp = 0;
	 	var verAlias = 0;
	 	var invitado=0;
	 	var vardelitos =""; 
	 	var numeroExpedienteCadena;
	 	var varNombre;		
		var banderaDenuncia=0;
		var banderaDenuncianteQuerellante=0;
		var banderaNarrativa=0;
		var idInvolucrado=0;
		var numeroExpediente="";
		var idOrganizacion=0;
		var idExpediente = parent.numExpediente;
		var idUsuario="";
		var tipoTiempoHecho=1;//1 para definido, 2 para lapso y 3 para descripcion hecho en el tiempo
		var calidad="";
		var idHecho="";
		var banderaConsultaHecho=0;
		//controla los tabs segun la etapa del expediente
		var numEtapaMenu=parent.numEtapa;
		//pinta el valor en el tab de actuaciones
		var valorEtapaMenu=parent.valorEtapa;

		var nombreFuncionario = "";
		var apaterFuncionario = "";
		var amaterFuncionario = "";
		
		//para el manejo de formas
		var forma = "";
		//almacena el id de la audiencia
		var idAudiencia="";

		//identificador ventana solicitante de justiciarestaurativa
		var idWindowSolicitanteJusticiaRestaurativa = 1;
		 /*
		* Identificadores de los frames ingresar calidades
		*/
		var idWindowIngresarVictima = 1;	
		var idWindowIngresarTraductor = 1;	
		var idWindowIngresarQuienDetuvo = 1;	
		var idWindowIngresarRepresentanteLegal = 1;
		var idWindowIngresarTestigo = 1;
		var idWindowIngresarTutor = 1;
		var idWindowIngresarProbResponsable = 1;
		var idWindowIngresarContactoDeUnaOrganizacion = 1;
		var idWindowIngresarSentenciadoReinsertar = 1;
		var idWindowIngresarDenunciante = 1;
		var idWindowIngresarSolicitanteJusticiaRestaurativa = 1;

		 /*
		* Identificadores de los frames ingresar Objetos y evidencias
		*/
		var idWindowIngresarEquipoDeComputo = 1;
		var idWindowIngresarEquipoTelefonico = 1;
		var idWindowIngresarArma = 1;
		var idWindowIngresarExplosivo = 1;
		var idWindowIngresarSustancia = 1;
		var idWindowIngresarAnimal = 1;
		var idWindowIngresarVehiculo = 1;
		var idWindowIngresarAeronave = 1;
		var idWindowIngresarEmbarcacion = 1;
		var idWindowIngresarInmueble = 1;
		var idWindowIngresarNumerario = 1;
		var idWindowIngresarVegetal = 1;
		var idWindowIngresarDocumentoOficial = 1;
		var idWindowIngresarJoya = 1;
		var idWindowIngresarObraDeArte = 1;

		var idWindowGenerarNotas=1;
      	     	
		$(document).ready(function() {
		
		//oculta boton de teoria del caso y se habilita solo para tecnica
		$('#teoriaDelCaso').hide();
		//Guarda el solicitante
		$("#btnGuardar").click(guardaSolicitante);
		//oculta leyenda de seleccionar delito
		$('#leyendaUnDelitoGrave').hide();
		$('#leyendaNingunDelitoGrave').hide();
		$('#actividadesSugeridas').hide();

		//oculta boton de guardar delitos
		$('#btnGuardarDelitosAg').hide();

		//activa la funcionalidad del acordeon
		$('#cedulaIdentificacion').easyAccordion({ 
			  autoStart: false, 
			  slideInterval: 3000
		});
	
		//Se agrega el evento click para crear nuevos objetos
		$("#nuevoEquipoDeComputo").click(creaNuevoEquipoDeComputo);
		$("#nuevoEquipoTelefonico").click(creaNuevoEquipoTelefonico);
		$("#nuevaArma").click(creaNuevaArma);
		$("#nuevoExplosivo").click(creaNuevoExplosivo);
		$("#nuevaSustancia").click(creaNuevaSustancia);
		$("#nuevoAnimal").click(creaNuevoAnimal);
		$("#nuevoVehiculo").click(creaNuevoVehiculo);
		$("#nuevaAeronave").click(creaNuevaAeronave);
		$("#nuevaEmbarcacion").click(creaNuevaEmbarcacion);
		$("#nuevoInmueble").click(creaNuevoInmueble);
		$("#nuevoNumerario").click(creaNuevoNumerario);
		$("#nuevoVegetal").click(creaNuevoVegetal);
		$("#nuevoDocumentoOficial").click(creaNuevoDocumentoOficial);
		$("#nuevaJoya").click(creaNuevaJoya);
		$("#nuevaObraDeArte").click(creaNuevaObraDeArte);

		//Se agrega el evento click para crear nuevos 
		$("#nuevaVictima").click(creaNuevaVictima);
		$("#nuevoTraductor").click(creaNuevoTraductor);
		$("#quienDetuvo").click(creaQuienDetuvo);
		$("#nuevoRepresentanteLegal").click(creaNuevoRepresentanteLegal);
		$("#nuevoProbResponsable").click(creaNuevoProbResponsable);
		$("#crearDenunciante").click(crearDenunciante);
		$("#nuevoTestigo").click(creaNuevoTestigo);
		$("#nuevoContactoDeUnaOrganizacion").click(creaNuevoContactoDeUnaOrganizacion);
		$("#ingresarSentenciadoReinsertar").click(crearSentenciadoReinsertar);
		$("#nuevoTutor").click(creaNuevoTutor);
		//$("#nuevoSolicitanteJusticiaRestaurativa").click(crearSolicitanteJusticiaRestaurativa);
		
		//$("#asociarIndividuo").click(asociarIndividuo);
		//seteamos los listener de los radios para la relacion de Delitos por Person o por Delito
		$("#rdbMenuInterRelDelXPersona").bind("click",ocultaMuestraTblsRelacionarDelitos);
		$("#rdbMenuInterRelDelXDelito").bind("click",ocultaMuestraTblsRelacionarDelitos);
		//tabs principales
		$("#tabsprincipalconsulta" ).tabs();
		$('#tabsprincipalconsulta').tabs('selected', 1) ;
		//tabs hijos para pestaña de hechos
		$("#tabsHechos").tabs();
		//tabs hijos de cedula de identificacion
		$("#tabsconsulta7").tabs();
		//tabs hijos de la pestaña involucrados
		$("#tabschild").tabs();
		//tabs hijos de la pestaña delito
		$("#tabschild2").tabs();	
		//tabs hijos de la pestaña objetos y evidencias
		$("#tabschild4").tabs();
		//asocia la funcionalidad al combo de acciones
		$("#cbxAcciones").change(accionesExpediente);
		//oculta el botono de guardar de la jsp incluida de delitos
		$("#btnGuardarDelitosAg").css("display", "none");

		consultaCedula();
		consultaAcuerdo();
			
		//carga el grid de las audiencias
		gridAudiencia();

		//asigna el valor del expediente
		numeroExpediente=idExpediente;
		//consulta el detalle del expediente
		consultaDetalleExp();	

        //recupera el id para pintar los datos si viene de carpeta de investigacion
		//var idProbableResponsable='<%= request.getParameter("idProbableResponsable")%>';
		
		//if(idProbableResponsable != "null"){
			//consulta(idProbableResponsable);
		//}

		//carga el editor de texto
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
					]
			};		
	
		$('.jquery_ckeditor').ckeditor(config);
			
		calidad='PROBABLE_RESPONSABLE_PERSONA';
		idHecho=0;
		idUsuario="0000";
		$("#idFechaDate,#idFechaDateLapso,#idFechaDateLapso2").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		//asocia funcion a boton
		$("#btnGuardarDatos").click(obtenerParametrosDelDetenido);
		//$("#btnGuardarHechos").click(guardaHecho);
		//$("#btnModificarHechos").click(modificarHecho);
		//$("#btnGuardarHechos").show();
		//$("#btnModificarHechos").hide();
		
		//asginamos los listener de los botones de fecha y hora del hecho
		$("#ingresarTiempoLapsoPResponsable").click(cambiaLapso);
		$("#ingresarTiempoEspecificamentePResponsable").click(cambiaEspecifico);
		$("#ingresarTiempoOtroPResponsable").click(creaNuevaNarrativa);
				
		if(idHecho!=null && parseInt(idHecho)!=0){			
			$("#btnGuardarHechos").hide();
			$("#btnModificarHechos").show();
			consultaHecho();
		}
		
		cargaActuaciones();

		$("#datosGeneralesCmpOcupacion").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1
			
		}); 	
		
	});
		
	function cargarComboCambiarEtapa(etapa){
		var tip;
		$.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/consultarCatalogoEtapaExpediente.do',
	    	  data: '',
	    	  async: false,
	    	  dataType: 'xml',
	    	  success: function(xml){
		    	  $('#cbxEtapaExpediente').empty();
		    	  $('#cbxEtapaExpediente').append('<option value="-1">- Seleccione -</option>');
		    	  $(xml).find('etapaExpediente').each(function(){
		    	  	tip = $(this).find('clave').text();
		    	  switch(etapa){
		    	  	case <%=EtapasExpediente.INTEGRACION.getValorId()%>:
		    	  		if(tip == <%=EtapasExpediente.TECNICA.getValorId()%> || tip == <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%> ){
		    	  			$('#cbxEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		    	  		}
		    	  	break;
		    	  	case <%=EtapasExpediente.TECNICA.getValorId()%>:
		    	  		if(tip == <%=EtapasExpediente.EJECUCION.getValorId()%>){
		    	  			$('#cbxEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		    	  		}
		    	  	break;
		    	  	case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>:
		    	  		if(tip == <%=EtapasExpediente.TECNICA.getValorId()%>){
		    	  			$('#cbxEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		    	  		}
		    	  	break;
		    	  }
		    	  	
				});
		    	  
	    	  }
		});	
	}
		
	function manejoDePestañasPorEtapa(etapa,estatus){
		
		switch(parseInt(etapa)){
		case <%=EtapasExpediente.INTEGRACION.getValorId()%>:
			loadEtapaIntegracion();
			cargarComboCambiarEtapa(<%=EtapasExpediente.INTEGRACION.getValorId()%>);
			break;
		case <%=EtapasExpediente.TECNICA.getValorId()%>:
			loadEtapaTecnica(estatus);	
			cargarComboCambiarEtapa(<%=EtapasExpediente.TECNICA.getValorId()%>);
			break;
		case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>:
			loadEtapaRestaurativa();
			cargarComboCambiarEtapa(<%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>);
		
			break;
		case <%=EtapasExpediente.EJECUCION.getValorId()%>:
			loadEtapaEjecucion();
			break;
		case <%=EtapasExpediente.ASESORIA.getValorId()%>:
			loadEtapaAsesoria();
			break;
		}
	}
	function loadEtapaAsesoria(){

		$("#tabResumen").css("display", "block");
		$("#tabDocumentos").css("display",	"block");
		$('#tabsconsultaprincipal-1').css("display", "block");
		$('#resumen1').hide();
		$('#detalleAsesoria').show();	
		$("#tabMotivoAsesoria").css("display", "block");
		$("#tabDatosInteresado").css("display", "block");
		$("#tabBitacora").css("display", "block");		

		}
	
	function loadEtapaEjecucion(){
		$("#tabResumen").css("display", "block");
		$("#tabActuaciones").css("display",	"block");
		$("#tabNotas").css("display",	"block");
		$("#tabDocumentos").css("display",	"block");
		$("#tabAudiencias").css("display",	"block");
		$("#tabDocumentos").css("display", "block");
		$('#tabsconsultaprincipal-11').show();
		$('#tabsconsultaprincipal-1').show();
		$("#tabsprincipalconsulta").tabs("option", "selected", 0);
		$("#cbxAcciones").show();
		$("#etiquetaFechaHoraMensaje").hide();
		$("#campoFechaHoraMensaje").hide();
		$("#etiquetaAgenciaMP").hide();
		$("#campoAgenciaMP").hide();						
		$("#etiquetaQuienSolicita").hide();
		$("#campoQuienSolicita").hide();
		$("#etiquetaFechaHoraAtencion").hide();
		$("#campoFechaHoraAtencion").hide();
		$("#etiquetaFechaHoraSolicitud").hide();
		$("#campoFechaHoraSolicitud").hide();
		$("#tabBitacora").css("display", "block");		
	}
	
	function loadEtapaRestaurativa(){
		//restaurativa o conciliacion y mediacion
		$("#tabResumen").css("display", "block");
		$("#tabInvolucrados").css("display", "block");
		//$("#tabAcuerdos").css("display", "block");
		$("#tabActuaciones").css("display",	"block");
		$("#tabDocumentos").css("display", "block");
		$("#tabBitacora").css("display", "block");		
		$('#tabsconsultaprincipal-11').show();
		$('#tabsconsultaprincipal-1').show();
		$('#tabsconsultaprincipal-13').show();
		$('#tabsconsultaprincipal-9').show();
		$("#tabsprincipalconsulta").tabs("option", "selected", 0);			
		//se ocultan tabs hijos de involucrado
		$('#tabDenunciante').hide();
		$('#tabVictima').hide();
		$('#tabProbableResponsable').hide();
		$('#tabTestigo').hide();
		$('#tabTraductor').hide();
		$('#tabQuienDetuvo').hide();	
		$('#tabschild-1').hide();
		$('#tabschild-2').hide();
		$('#tabschild-3').hide();
		$('#tabschild-4').hide();
		$('#tabschild-5').hide();
		$('#tabschild-6').hide();
	  	$("#etiquetaTipoAudiencia").hide();
		$("#campoTipoAudiencia").hide();
		$("#etiquetaSalaDesignada").hide();
		$("#campoSalaDesignada").hide();
		$("#etiquetaFechaHoraAudiencia").hide();
		$("#campoFechaHoraAudiencia").hide();
		$("#etiquetaDetenido").hide();
		$("#campoDetenido").hide();
		$("#etiquetaAgenciaMP").hide();
		$("#campoAgenciaMP").hide();
		$("#etiquetaCausa").hide();
		$("#campoCausa").hide();	
		$("#etiquetaNombreImputado").hide();
		$("#campoNombreImputado").hide();
		$("#etiquetaFechaHoraMensaje").hide();
		$("#campoFechaHoraMensaje").hide();	
		//$("#etiquetaFechaRegistro").hide();	
		//$("#datosGeneralesCmpFechaIngreso").hide();		
		//quita el domicilio de notificaciones de la jsp ingresar domicilio
		//killDomicilioNotificaciones();		
		$('#liDom').hide();	
		
		idInvolucrado=0;
		invitado=1;

	}
		
	function loadEtapaTecnica(estatus){
		
		$("#tabResumen").css("display", "block");
		$("#tabTeoriaCaso").css("display",	"block");
		$("#tabActuaciones").css("display",	"block");
		$("#tabNotas").css("display",	"block");
		$("#tabAudiencias").css("display",	"block");
		$("#tabDocumentos").css("display", "block");
		$("#tabBitacora").css("display", "block");		
		$('#tabsconsultaprincipal-11').show();
		$('#tabsconsultaprincipal-1').show();
		//$("#tabsprincipalconsulta").tabs("option", "selected", 0);
		$('#teoriaDelCaso').show();

		if(parseInt(estatus)== <%=EstatusExpediente.ABIERTO_TECNICA_CON_CARPETA.getValorId()%>){

			$("#tabInvolucrados").css("display", "block");
			$("#tabDelito").css("display", "block");
			$("#tabObjetosEvidencias").css("display", "block");
			$("#tabCadenaCustodia").css("display", "block");
			$("#tabPericiales").css("display", "block");
			$('#tabschild-7').hide();
			$('#resumen1').hide();
			$('#resumen2').show();
			$("#tabschild").tabs("option", "selected", 2);
			datosGenerales();
			cargarInvolucradosExpediente(idExpediente);
			consultarNotas();

		}
		
		$('#tabInvitado').hide();
		$('#tabSolicitante').hide();
		$("#etiquetaFechaHoraMensaje").hide();
		$("#campoFechaHoraMensaje").hide();
		$("#etiquetaAgenciaMP").hide();
		$("#campoAgenciaMP").hide();						
		$("#etiquetaQuienSolicita").hide();
		$("#campoQuienSolicita").hide();
		$("#etiquetaFechaHoraAtencion").hide();
		$("#campoFechaHoraAtencion").hide();
		$("#etiquetaFechaHoraSolicitud").hide();
		$("#campoFechaHoraSolicitud").hide();
	}
		
	function loadEtapaIntegracion(){
		$("#tabResumen").css("display", "block");
		$("#tabCedulaIdentificacion").css("display", "block");
		$("#tabBitacora").css("display", "block");
		$("#tabActuaciones").css("display",	"block");
		$("#tabDocumentos").css("display", "block");
		//$("#tabBitacora").css("display", "block");		
		$('#tabsconsultaprincipal-11').show();
		$('#tabsconsultaprincipal-1').show();
		$('#tabsconsultaprincipal-2').show();
		//$('#tabsconsultaprincipal-4').show();
		$('#tabsconsultaprincipal-9').show();
		$("#tabsprincipalconsulta").tabs("option", "selected", 0);
		$("#etiquetaTipoAudiencia").hide();
		$("#campoTipoAudiencia").hide();
		$("#etiquetaSalaDesignada").hide();
		$("#campoSalaDesignada").hide();
		$("#etiquetaFechaHoraAudiencia").hide();
		$("#campoFechaHoraAudiencia").hide();
		$("#etiquetaDetenido").hide();
		$("#campoDetenido").hide();
		$("#etiquetaQuienSolicita").hide();
		$("#campoQuienSolicita").hide();
		$("#etiquetaFechaHoraAtencion").hide();
		$("#campoFechaHoraAtencion").hide();
		$("#etiquetaFechaHoraSolicitud").hide();
		$("#campoFechaHoraSolicitud").hide();
		$("#etiquetaAgenciaMP").hide();
		$("#campoAgenciaMP").hide();
		$("#etiquetaCausa").hide();
		$("#campoCausa").hide();
		//$("#etiquetaFechaRegistro").hide();	
		//$("#datosGeneralesCmpFechaIngreso").hide();		
	}
		
	//Abre pantalla con el documento generado con la plantilla
	function generarDoctoPrueba(){
		//pasar tipo de forma del documento de pruebas en lugar del documentoId
		forma = <%=Formas.ESCRITO.getValorId()%>;
		abreVentanaDocumento(forma);
	}

	
	//Abre pantalla con el documento generado con la plantilla
	function generarDoctoAMP(){
		//pasar tipo de forma del documento de acuerdo con amp en lugar del documentoId
		forma = <%=Formas.ACUERDO_AMP.getValorId()%>;
		abreVentanaDocumento(forma);
	}
	function abreVentanaDocumento(forma){
		var params = 'numExpediente=' + idExpediente;
		if(forma != ""){
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/colocarExpedienteParaDefensaTecnica.do',
	    		data: params,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var errorCode;
					errorCode=$(xml).find('response').find('code').text();				
					if(parseInt(errorCode)==0){	
						var exp = $(xml).find('numeroExpediente').first().text();
	    			}
					else{
						//Mostrar mensaje de error
					}
	    		}
	    	});
			
			$.newWindow({id:"iframewindowGenerarDocumentoDefensaTecnica", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"", type:"iframe"});
	        $.updateWindowContent("iframewindowGenerarDocumentoDefensaTecnica",
	        		"<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId="+forma+"&numeroUnicoExpediente="+numeroExpedienteCadena+"' width='1140' height='400' />");
		}
	}
	
	//consulta la cedula
	function consultaCedula(){			
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarDocumentosPorExpediente.do?tipo=0',
			data: 'idExpediente='+ idExpediente, 
			async: false,
			dataType: 'xml',
			success: function(xml){
					$("#cedulaIdentificacion").val($(xml).find('lDocumentoDTO').find('nombre').first().text());
					cedula=$(xml).find('archivoDigitalId').first().text();				
			}
		});
	}

	//consulta acuerdo
	function consultaAcuerdo(){
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/consultarDocumentosPorExpediente.do?tipo=1',
		data: 'idExpediente='+ idExpediente, 
		async: false,
		dataType: 'xml',
		success: function(xml){
			  
				$("#acuerdosDefensa").val($(xml).find('lDocumentoDTO').find('nombre').first().text());
				acuerdos=$(xml).find('archivoDigitalId').first().text();
		}
	});
	}
	
	/*
	*Funcion que dispara el Action para consultar el Estado Civil
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
    				$('#estadoCivilDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#estadoCivilDEATT1').multiselect('refresh');
        			
    			});
    		}
    	});
    }
		
	
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
    			$('#ocupacionDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			$('#ocupacionDEATT1').multiselect('refresh');
    			});
    		}
    	});
    }

	/*
	*Funcion que dispara el Action para consultar el Idioma
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
    				$('#idiomaDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#idiomaDEATT1').multiselect('refresh');
        			
    			});
    		}
    	});
    }

    /*
	*Funcion que dispara el Action para consultar Nacionalidad
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
    					
    				$('#nacionalidadDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#nacionalidadDEATT1').multiselect('refresh');
    				
        			
    			});
    		}
    	});
    }
    /*
	*Funcion que dispara el Action para consultar Escolaridad
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
    				$('#escoralidadDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#escoralidadDEATT1').multiselect('refresh');
        			
    			});
    		}
    	});
    } 
	  
    /*
	*Funcion que dispara el Action para consultar Religion
	*/    
    function cargaReligion(){
    	
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoReligion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catReligion').each(function(){
    				$('#ReligionDEATT1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#ReligionDEATT1').multiselect('refresh');
        			
    			});
    		}
    	});
    }

	
	//obtiene parametros del detenido
	function obtenerParametrosDelDetenido(){		
		
       var parametros = 'idExpediente='+idExpediente;
        //Datos Generales
        parametros += obtenerParametrosDatosGenerales();
    	//Domicilio
		parametros += obtenerParametrosDomicilio();
		//Medios de contacto
		parametros += obtenerMedios();
		//modificar Individuo
		parametros += '&idIndividuo='+ idInvolucrado;
		//ingresarInvitado
		parametros += '&Invitado='+ invitado;
      
		 $.ajax({
		     type: 'POST',
		     url: '<%= request.getContextPath()%>/GuardaInvolucradoIntegracion.do',
			 data: parametros,
			 dataType: 'xml',
			 success: function(xml){
				 customAlert("Se guardó con éxito");
				//idInvolucrado=$(xml).find('involucrado').find('elementoId').text();
				idInvolucrado = $(xml).find("long").text();
				guardarDelitosAgraviadosExp();
			  }
			});

		 if(invitado == 0){
			 obtenerParametrosMediaFiliacion();
			 guardaHecho();
			 recuperaDelitos();
			 }
		
		 return parametros;
	}
		
	function recuperaDelitos(){

		//var rowd = jQuery("#gridDelitosAgraviados").jqGrid('getGridParam','selrow');
		//var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',rowd);
		var ids = jQuery('#gridDelitosAgraviados').getDataIDs();
		
		//var listaDelitos="";
		//for(i=0; i<= ids.length(); i++){
			
		//var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',ids[i]);
		//var delito = retd.Delito;
		//listaDelitos += delito;-->
			//
		//}

		vardelitos=ids;
		if(vardelitos!=""){
			var paramDet="";
		       paramDet += '&numDelitos='+ vardelitos;
		       paramDet += '&idInvolucrado='+idInvolucrado;
		       paramDet +='&idExpediente='+idExpediente;
				}
		 $.ajax({
		     type: 'POST',
		     url: '<%= request.getContextPath()%>/GuardaDelitosIntegracion.do',
			 data: paramDet,
			 dataType: 'xml'
		});

		 return paramDet;

	}

	function consultaHecho(){
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
    		data: 'idNumeroExpediente='+numeroExpediente,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
   				//seteamos la informacion del hecho
   					  $(xml).find('hechoDTO').find('tiempo').find('fechaInicio').each(function(){
	    	    	 	 $("#ingresarTiempoEspecificamentePResponsable").click();
	    	  	    	 seteaDatosTiempoEspecifico(xml);
	    	  	    	 bloqueaCamposTiempoEspecifico(0);
	    	  	    	 tipoTiempoHecho=1;
		    	      });
    				  
		    	      $(xml).find('hechoDTO').find('tiempo').find('fechaFin').each(function(){
		    	    	  $("#ingresarTiempoLapsoPResponsable").click();
	    	  	    	  seteaDatosTiempoLapso(xml);
	    	  	    	  bloqueaCamposTiempoLapso(0);
	    	  	    	  tipoTiempoHecho=2;
	    	  	    	  //ingresarTiempoOtroPResponsable
		    	      });
		    	      habilitaDeshabilitaBotonesTiempo(0);
		    	      
		    	      $(xml).find('hechoDTO').each(function(){
		    	    	  //seteamos la informacion del lugar
				    	  seteaLugar(xml);
			    	      $("#btnGuardarHechos").hide();
			    	      $("#btnModificarHechos").show();
		    	    	  //seteamos el valor de la descripcion del hecho
		    	    	  $('.jquery_ckeditor').val($(this).find('descNarrativa').text());
		    	    	  CKEDITOR.on("instanceReady", function (ev) {
		    	  	      var bodyelement = ev.editor.document.$.body;
		    	  	      bodyelement.setAttribute("contenteditable", false);
		    	  	    });
		    	  	    CKEDITOR.replace('editor1');
		    	    	  banderaConsultaHecho=1;
		    	      });
	    		}
    		}	
    	});
	}
	
	function habilitaDeshabilitaBotonesTiempo(bandera){
		if(parseInt(bandera)==1)
		{
			$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","");
			$("#ingresarTiempoLapsoPResponsable").attr("disabled","");
			$("#ingresarTiempoOtroPResponsable").attr("disabled","");
		}
		else
		{
			$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","disabled");
			$("#ingresarTiempoLapsoPResponsable").attr("disabled","disabled");
			$("#ingresarTiempoOtroPResponsable").attr("disabled","disabled");
		}
	}
			
	function cargaDatosDomicilio(xml){
		pintaDatosDomicilio(xml);
	}
			
	function cambiaEspecifico(){
		  if(!$("#divEspecifico").is(':visible')){
			$("#divLapso").css("display", "none");
			$("#divEspecifico").css("display", "block");  
			$("#divOtro").css("display", "none"); 
			tipoTiempoHecho=1;
		  }
		}
		
			
		function cambiaLapso(){
		  if(!$("#divLapso").is(':visible')){
			$("#divLapso").css("display", "block");
			$("#divEspecifico").css("display", "none");
			$("#divOtro").css("display", "none"); 
			tipoTiempoHecho=2;
		  }
		}
		
				
		function cambiaOtro(){
		  if(!$("#divLapso").is(':visible')||!("#divEspecifico").is(':visible')){
			$("#divLapso").css("display", "none");
			$("#divEspecifico").css("display", "none"); 
			$("#divOtro").css("display", "block");
			tipoTiempoHecho=3;
		  }
		}
		
		
		function creaNuevaNarrativa() {
			if(!$("#divEspecifico").is(':visible')){
				$("#divEspecifico").css("display", "none"); 
				$("#divLapso").css("display", "none");  	 
			}
			if(!$("#divLapso").is(':visible')){
				$("#divLapso").css("display", "none");
				$("#divEspecifico").css("display", "none");  
			}
			cambiaOtro();
		}
	/*
	*Funcion para mandar guardar el nuevo hecho en la BD
	*/
	function guardaHecho()
	{
		if(validaDatosIngresoHecho())
		{
			//guardare el hecho
			var parametrosHechos=extraeDatosIngresarHechos();//obtengo la información a almacenar
			//llamamos al action que guardara el nuevo Hecho
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ingresarHecho.do',
				data: parametrosHechos,
				dataType: 'xml',
				success: function(xml){
					if(parseInt(idHecho)==0)
					{
						//Venismo de una insercion
						if(parseInt($(xml).find('code').text())==0)
						{
							$("#btnGuardarHechos").attr("disabled","disabled");
							window.parent.cargaIngresoHecho("Hecho",''+$(xml).find('hechoId').text());
							customAlert("Se guardó exitosamente el hecho");
						}
						else
						{
							customAlert("Ocurrió un error al guardar el hecho");
						}
					}
					else
					{
						//venimos de un update y para setear la pantalla mandaremos llamar a la consulta del hecho
						consultaHecho();
					}
				}
			});
		}
		else
		{
			customAlert("Existe un dato inválido");
		}
	}
			
	function modificarHecho(){
		//habilitamos los campos de tiempo especifico 
		bloqueaCamposTiempoEspecifico(1);
		//habilitamos los campos de tiempo por lapso
		bloqueaCamposTiempoLapso(1);
		//habilitamos los botones para seleccionar el tipo de tiempo
		habilitaDeshabilitaBotonesTiempo(1);
		//habilitamos todos los campos de lugarx|
		bloqueaAllCamposLugaresHecho(1);
		
	}
	
	
	/*
	*Funcion que validara los datos del nuevo hecho para poder ingresar el hecho a la BD
	*/
	function validaDatosIngresoHecho()
	{
		var banderaVal=true;
		//lamaremos a cada uno de los metodos que validan las secciones de la vista
		return banderaVal;	
	}
		
	/*
	*extrae datos para guardar el hecho
	*/
	function extraeDatosIngresarHechos()
	{
		var parametros="";
		//llmaremos a cada uno de los metodos que estraen la informacion de cada una de 
		//las secciones de la vista para poder ingresar un nuevo Hecho
		
		//agregamos el Id del hecho
		parametros="idHecho="+idHecho;
		//extraemos la descripcion del hecho
		parametros +="&gcDescripcionHecho="+escape($('.jquery_ckeditor').val());
		//recuperamos los datos de lugar, ya trae el & para la union
		parametros += obtenerDatos();
		if(parseInt(tipoTiempoHecho)>0)
		{
			//seteamos el tiempo seleccionado
			parametros += "&tipoTiempoHecho="+tipoTiempoHecho;
		}
		//recuperamos los datos de fecha y tiempo
		if(parseInt(tipoTiempoHecho)==1)//especifico
		{
			parametros += "&"+recuperaDatosTiempoEspecifico(calidad);	
		}
		else if(parseInt(tipoTiempoHecho)==2)//lapso
		{
			parametros += "&"+recuperaDatosTiempoLapso(calidad);	
		}
		else if(parseInt(tipoTiempoHecho)==3)//hecho en el tiempo
		{
			parametros += "&gsNarrativa="+$("#textNarrativa").val();	
		}
		
		//regresamos la cadena con los datos del Hecho
		parametros += "&numExpediente=" + numeroExpediente;
		parametros += "&idUsuario=" + idUsuario;
		parametros += "&origenExpediente=" + $(':radio[name=rdTipoExpediente]:checked').val();
		return parametros;
	}
	
	/*
	 *Imprime los datos que vienen de la funcion espejoDatosNarrativa de texto narrativa
	 *en la pantalla ingresar probable responsable
	 */
	function imprimeDatosPadreNarrativa(textoNarrativa){
	  $("#textNarrativa").val(textoNarrativa);
	}
	function imprimeDatosPadre(nombre, apPat, apMat){
		document.getElementById('iSolicitudCmpNombre1').value=nombre;
		document.getElementById('iSolicitudCmpApellidoPaterno1').value=apPat;
		document.getElementById('iSolicitudCmpApellidoMaterno1').value=apMat;
		//document.getElementById('iSolicitudCmpNombreSJR').value=nombreSJR;
		//document.getElementById('iSolicitudCmpApellidoPaternoSJR').value=apPatSJR;
		//document.getElementById('iSolicitudCmpApellidoMaternoSJR').value=apMatSJR;

	}
	
	//Funcion abre una ventana modal para cambiar la etapa al expediente
	  function cambiarEtapaExpediente(){

		  
        $('#etapaActual').val(valorEtapaMenu);  
		$("#divCambiarEtapa").dialog("open");
	  	$("#divCambiarEtapa").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Cambiar etapa del expediente', 
		  	dialogClass: 'alert',
		  	position: [312,40],
		  	width: 400,
		  	height: 250,
		  	maxWidth: 1000,
		  	buttons:{"Cambiar":function() {
			  		confirmar=confirm("¿Se cambiará la etapa actual del expediente, ¿está seguro?");
			  		if (confirmar){
				  		var nuevaEtapa =  $('#cbxEtapaExpediente').val();
				   		$.ajax({
							type: 'POST',
							url: '<%= request.getContextPath()%>/cambiarEtapaDelExpediente.do?numeroExpedienteId='+numeroExpediente+'&etapaId='+nuevaEtapa+'',
							async: false,
							dataType: 'xml',
							success: function(xml){
								    					    			
							}
						});
				   		$(this).dialog("close");
				   		parent.cerrarEtapa();
				  	}
				},
		  		"Cancelar":function() {
			  		confirmar=confirm("¿Realmente desea salir?");
			  		if (confirmar){
				  		  		$(this).dialog("close");
				  	}else{}
			  	}
		  	}
		});	  	
	}	

	  $(':radio[name=rbtSexoDatosGenerales]:checked').val();

	 function gridAudiencia() {
			jQuery("#gridAudiencia").jqGrid({
						url:'<%= request.getContextPath()%>/consultarAudienciasDefensor.do', 
						datatype: "xml", 						
						colNames:['Caso','Carácter','Tipo de Audiencia','Fecha de Audiencia','Sala','Defensor de Oficio'], 
						colModel:[{name:'caso',	 	index:'2002', 		width:200, align:"let"},
						          {name:'caracter',	index:'2037', 	width:100, align:"center"},
						          {name:'tipo',	 	index:'2017', 	    width:120, align:"center"},
						          {name:'fechaHora',index:'2018',	width:200, align:"center"},
						          {name:'sala' ,	index:'2029', 		width:110, align:"center"},
						          {name:'defensor' ,index:'2030', 	width:150, align:"center"}
								],				
						pager: jQuery('#pagerAudiencia'),
						rowNum:10,
						rowList:[10,20,30],
						autowidth: true,
						autoheight:true,
						sortname: 'detalle',
						viewrecords: true,
						sortorder: "desc",
						onSelectRow: function(rowID) {
							idAudiencia=rowID;				
							}
		}).navGrid('#pagerAudiencia',{edit:false,add:false,del:false});
			jQuery("#gridAudiencia").trigger('reloadGrid');
		}
			 
	function cargaGridBitacora() {
		jQuery("#gridBitacora").jqGrid({
					url : '<%= request.getContextPath()%>/consultarBitacoraPorExpediente.do?numeroExpedienteId='+numeroExpediente+'', 
					datatype: "xml", 						
					colNames:['Fecha','Hora','Movimiento','Descripción'], 
					colModel:[{name:'Fecha',	 	index:'1',  width:200, align:"center"},
					          {name:'Hora',	        index:'4', 	width:200, align:"center"},
					          {name:'Movimiento',	index:'2', 	width:200, align:"center"},
					          {name:'Descripcion',	index:'3',  width:400, align:"center"}
							],				
					pager: jQuery('#pagerAudiencia'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					sortname: '1',
					viewrecords: true,
					sortorder: "desc",
					onSelectRow: function(rowID) {
						//idAudiencia=rowID;				
					}
		}).navGrid('#pagerGridBitacora',{edit:false,add:false,del:false});
			jQuery("#gridBitacora").trigger('reloadGrid');
	}

	 function solicitarAudiencia() {
	
		var param = 'idExpediente='+idExpediente;
		param+='tipoSolicitud='+ $(':radio[name=tipoAudiencia]:checked').val();;
	      $.ajax({
		      type: 'POST',
	    	  url: '<%=request.getContextPath()%>/ConsultarIndividuoDatos.do',
	    	  data: param,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
		    	    	 
	    	  }
	    	});
	}	

	function camposSeleccionados(){
		var id = jQuery("#gridAudiencia").jqGrid('getGridParam','selrow');
		if(idAudiencia!=""){
			//botonGeneraSolicitud
			$('#botonGeneraSolicitud').removeAttr("disabled");

			}else
				{ 
				$('#botonGeneraSolicitud').css("disabled","disabled");
				}
		}	
	
	/*
	* Funcion para sacar el valor del select de acciones
	*/
	function accionesExpediente() {
	  	var selected = $("#cbxAcciones option:selected");	
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+selected.val(),
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
	  	
	  	switch (parseInt(actividad)) {
	  		case <%=Actividades.GENERAR_ACUERDO_CON_AMP.getValorId()%>:
			case <%=Actividades.GENERAR_CEDULA_DE_IDENTIFICACION.getValorId()%>:
			case <%=Actividades.GENERAR_ACUERDO_DE_DEFENSA.getValorId()%>:
			case <%=Actividades.GENERAR_LIBRO_DE_GOBIERNO.getValorId()%>:
				asociarActividadExpediente(actividad);
				generarDocumentoSinCaso(formaID, titulo, usaeditor);
				break;

			case <%=Actividades.ADJUNTAR_ACUERDO_RESTAURATIVO.getValorId()%>:
				abreVentanaAdjuntarDocumento('0');
				break;
		  	
			case <%=Actividades.SOLICITAR_CARPETA_DE_INVESTIGACION.getValorId()%>:
				solicitarCarpetaInvestigacion();
				break;
			case <%=Actividades.SOLICITAR_SERVICIO_PERICIAL.getValorId()%>:
			case <%=Actividades.SOLICITAR_DICTAMENES_PERICIALES.getValorId()%>:
				solicitarServicioPericial();
				break;

			case <%= Actividades.SOLICITAR_AUDIENCIA.getValorId() %>:
				enviarSolicitudAudiencia();
	            break;
			case <%= Actividades.SOLICITAR_TRANSCRIPCION_DE_AUDIENCIA.getValorId() %>:
			case <%= Actividades.SOLICITAR_COPIA_DE_AUDIO_Y_VIDEO_DE_AUDIENCIA.getValorId() %>:
				enviarSolicitudTranscripcionAudioyVideoAudienca();
	            break;
	  	}
	}
	function enviarSolicitudTranscripcionAudioyVideoAudienca(){
		var params ="?idNumeroExpediente="+ numeroExpediente;
		params +="&nombreSolicitante="+nombreFuncionario;
		params +="&apaterSolicitante="+apaterFuncionario;
		params +="&amaterSolicitante="+amaterFuncionario;
		$.newWindow({id:"iframewindowSolicitudTranscripcion", statusBar: true, posx:253,posy:113,width:812,height:454,title:"Solicitud de Transcripción", type:"iframe"});
		$.updateWindowContent("iframewindowSolicitudTranscripcion",'<iframe src="/nsjp-web/solicitarTranscripcionEnPG.do'+params+'" width="812" height="454" />');
	}
	
	function enviarSolicitudAudiencia(){
		var params ="?";
		params += "numeroExpediente=" + numeroExpedienteCadena;
		params += "&idExpedienteSoli="+ idExpediente;
		params += "&idNumeroExpediente="+ numeroExpediente;
        $.newWindow({id:"iframewindowSolicitarAudiencia", statusBar: true, posx:20,posy:20,width:740,height:520,title:"Solicitar Audiencia", type:"iframe"});
        $.updateWindowContent("iframewindowSolicitarAudiencia",'<iframe src="<%= request.getContextPath() %>/solicitarAudiencia.jsp'+params+'"    width="750" height="570" />');		
	}

	function asociarActividadExpediente(actividad){
				$.ajax({type : 'POST',
					url : '<%= request.getContextPath()%>/asociarActividadExpediente.do',
					data : 'numeroExpedienteId=' + idExpediente + '&numeroExpediente=' + numeroExpedienteCadena + '&actividad=' + actividad,
					async : false,
					dataType : 'xml',
					success : function(xml) { }
					});
		}
	
	 function generarDocumentoSinCaso(forma, titulo, usaeditor) {
	 	if(usaeditor){
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title: titulo, type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?esconderArbol=0&numeroUnicoExpediente="+numeroExpedienteCadena+"&formaId="+forma+"' width='1140' height='400' />");
	 	}else{
			generarDocumentoDirecto(forma, '', '', numeroExpedienteCadena);
	 	}
	 }
	 
		function generarDocumentoDirecto(idForma, idDocumento, tipoDocumento, numeroExpediente){
			document.formaDocumento.formaId.value = idForma;
			document.formaDocumento.documentoId.value = idDocumento;
			document.formaDocumento.tipoDocumento.value = tipoDocumento;
			document.formaDocumento.numeroUnicoExpediente.value = numeroExpediente;
			document.formaDocumento.submit();
		}
	
	function solicitarServicioPericial(){
		var params = "numeroExpediente="+numeroExpedienteCadena;
		params += "&numeroExpedienteId="+numeroExpediente;
		$.newWindow({id:"iframewindowVisorSolicitarServicioPericial", statusBar: true, posx:200,posy:50,width:850,height:380,title:"Solicitar Servicio Pericial", type:"iframe"});
	    $.updateWindowContent("iframewindowVisorSolicitarServicioPericial",'<iframe src="<%=request.getContextPath()%>/solicitarServicioPericial.do?'+params+'" width="850" height="380" />');
	}

	function cerrarVentanaSolicitarServicioPericial(){
		var pantalla ="iframewindowVisorSolicitarServicioPericial";
		$.closeWindow(pantalla);
	}

	function cerrarVentanaAdjuntarDocumento(){
		var pantalla ="iframewindowAdjuntarDocumento";
		$.closeWindow(pantalla);
	}

	function abreVentanaAdjuntarDocumento(tipo){
		$.newWindow({id:"iframewindowAdjuntarDocumento", statusBar: true, posx:650,posy:50,width:308,height:165,title:"Adjuntar documento", type:"iframe"});
	    $.updateWindowContent("iframewindowAdjuntarDocumento",'<iframe src="<%=request.getContextPath()%>/adjuntarDocumento.do?idExpediente='+ idExpediente +'&numeroExpedienteCadena='+numeroExpedienteCadena+'&tipo='+tipo+'" width="308" height="165" />');
	}
	
	function solicitarCarpetaInvestigacion(){
		var cofirmacion = confirm("¿Confirma la solicitud de carpeta de investigación para el expediente ?");
		if(cofirmacion){
			parametros = "numeroExpedienteId="+numeroExpediente;
			$.ajax({
		    	type: 'POST', data: parametros, async: false, dataType: 'xml',
		    	url: '<%= request.getContextPath()%>/solictarCarpetaInvestigacion.do',
		    	success: function(xml){
					var docum = $(xml).find("body").find("solicitud").find("documentoId").text();
					var folio = $(xml).find("body").find("solicitud").find("folioSolicitud").text();
		    		var msg = $(xml).find("body").find("string").text();
		    		if(docum != undefined && docum != null && docum != ""){
		    			cofirmacion = confirm("Se envio la Solicitud de Carpeta de Investigación con Folio "+folio+"\n ¿Desea mostrar el documento?");
						if(confirmacion){		    			
		    				var forma = "<%= Formas.SOLICITUD.getValorId() %>" ;
		    				var tipoD = "<%= TipoDocumento.SOLICITUD.getValorId() %>" ;
		    				generarDocumentoDirecto(forma, docum, tipoD);
		    			}
		    		}else{if(msg == "Error"){
    					customAlert("Se produjó un error en el envio favor de comunicar a su administrador...");
    				}else{
	    				customAlert(msg);
    					}
		    		}
		    	}
		    });
		}
	}
	
	
	function mostrarCampos(valor){
		hiddeAll();
		switch (parseInt(valor)) {
			case <%=EtapasExpediente.INTEGRACION.getValorId()%>: // EXPEDIENTE EN ETAPA INTEGRACION
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
			case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>: // EXPEDIENTE EN ETAPA CONCILIACION Y MEDIACION [RESTAURATIVA]
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
			case <%=EtapasExpediente.TECNICA.getValorId()%>:// EXPEDIENTE EN ETAPA TECNICA
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
			case <%=EtapasExpediente.ASESORIA.getValorId()%>:// EXPEDIENTE EN ASESORIA LEGAL
				$("#trExpediente").show();
				$("#trEtapaExpediente").show();
				$("#trInteresado").show();
				$("#trFechaSolicitud").show();
				$("#trHoraSolicitud").show();
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

		numeroExpediente = $(expediente).find('numeroExpedienteId').first().text();
		idExpediente = $(expediente).find('expedienteId').first().text();
		numeroExpedienteCadena = $(expediente).find('numeroExpediente').first().text();
		numeroCaso = $(expediente).find('casoDTO').find('numeroGeneralCaso').first().text();
		
		if(!existe(expediente)){
			expediente = $(detalle).find('expedienteDTO').first();
		}
		if(existe($(detalle).find('numeroGeneralCaso'))){
			$("#caso").val($(detalle).find('numeroGeneralCaso').first().text());
		}else{
			$("#caso").val("----");
		}
		if(existe($(expediente).find('numeroExpediente').first())){
			$("#expediente").val($(expediente).find('numeroExpediente').first().text());
		}else{
			$("#expediente").val("----");
		}
		
		etapa = $(expediente).find('etapa').first();
		var idEtapa = $(etapa).find('idCampo').text();
		var estatus = $(expediente).find('estatus').find('idCampo').first().text();
		manejoDePestañasPorEtapa(idEtapa ,estatus);
		
		if(existe($(etapa).find('valor'))){
			$("#etapaExpediente").val($(etapa).find('valor').text());
			mostrarCampos(idEtapa);
		}else{
			$("#etapaExpediente").val("----");
		}
		aviso = $(detalle).find("avisoDesignacionDTO").first();
		if(existe(aviso)){
			if(existe($(aviso).find('fechaCreacion'))){
				var fecha = $(detalle).find('fechaCreacion').first().text();
				var strfecha = obtenerFecha(fecha);
				var strhora = obtenerHora(fecha);
				$("#fechaSolicitud").val(strfecha);
				$("#horaSolicitud").val(strhora);
				$("#fechaMensaje").val(strfecha);
				$("#horaMensaje").val(strhora);
				$("#fechaDesignacion").val(strfecha);
				$("#horaDesignacion").val(strhora);
			}
		}else{
			$("#fechaSolicitud").val("----");
			$("#horaSolicitud").val("----");
			$("#fechaMensaje").val("----");
			$("#horaMensaje").val("----");
			$("#fechaDesignacion").val("----");
			$("#horaDesignacion").val("----");
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
			nombreFuncionario = $(funcionario).find("nombreFuncionario").text();
			apaterFuncionario = $(funcionario).find("apellidoPaternoFuncionario").text();
			amaterFuncionario = $(funcionario).find("apellidoMaternoFuncionario").text();
			
			$("#defensor").val(nombreFuncionario+" "+apaterFuncionario+" "+amaterFuncionario);
		}else{
			$("#defensor").val("----");
			$("#fechaDesignacion").val("----");
			$("#horaDesignacion").val("----");
		}
		
		if(existe($(detalle).find('etapasPasadas'))){
			$(detalle).find('etapasPasadas').find('etapasExpediente').each(function(){
				if($(this).text() == 'INTEGRACION'){
					loadEtapaIntegracion();
				}else if($(this).text() == 'TECNICA'){
					loadEtapaTecnica(estatus);	
				}else if($(this).text() == 'CONCILIACION_Y_MEDIACION'){
					loadEtapaRestaurativa();
				}else if($(this).text() == 'EJECUCION'){
					loadEtapaEjecucion();
				}else if($(this).text() == 'ASESORIA'){
					loadEtapaAsesoria();
				}
			});
		}

		$(detalle).find('involucradoDTO').each(function(){
			calidad = $(this).find('calidadDTO').first().find('valorIdCalidad').find('idCampo').text();
			id = $(this).find('elementoId').first().text();
			consultaDetalle(id);
			disparaConsultaGridsMediosDeContacto(id);
			if(calidad == <%=Calidades.DEFENDIDO.getValorId()%>){
				var nombre = obtenNombre($(this).find('nombresDemograficoDTO'));
				$("#imputado").val(nombre);
				$("#paraQuien").val(nombre);
				var esDetenido = $(this).find('esDetenido').first().text();

				if(existe($(this).find('delitoDTO').find('nombreDelito').first())){
					$("#delito").val($(this).find('delitoDTO').find('nombreDelito').first().test());
				}else{
					$("#delito").val("----");
				}

				if(esDetenido == "true"){		
					$("#detenido").val("Si");
					var fechaDetencion = $(this).find('detenciones').find('detencionDTO').find('fechaInicioDetencion').first().text();
					var fecha = obtenerFecha(fechaDetencion);
					var hora = obtenerHora(fechaDetencion);
				    $("#fechaDetencion").val(fecha);
				    $("#horaDetencion").val(hora);
					$("#lugarDetencion").val($(this).find('detenciones').find('detencionDTO').find('lugarDetencionProvicional').first().text());
				}else{
					$("#detenido").val("No");
				}
			}else if(calidad == <%=Calidades.SOLICITANTE.getValorId()%>){
				var nombre = obtenNombre($(this).find('nombresDemograficoDTO'));
				$("#interesado").val(nombre);
			}

			if($(this).find('motivoComparecencia')){
				$("#editor2").val($(this).find('motivoComparecencia').text());
			}
		});

	}
	
	function obtenNombre(nombreDemografico){
		var nombre = $(nombreDemografico).find('nombre').first().text();
		nombre += " " + $(nombreDemografico).find('apellidoPaterno').first().text();
		nombre += " " + $(nombreDemografico).find('apellidoMaterno').first().text();
		return nombre;
	}

	function consultaDetalleExp() {
	$.ajax({type : 'POST',
		url : '<%= request.getContextPath()%>/consultaDetalleExpedienteDefensoria.do',
		data : 'numeroExpedienteId=' + idExpediente+'&numeroExpediente='+numeroExpedienteCadena,
		async : false, dataType : 'xml',
		success : function(xml) {
					llenarDetalle(xml);
				 }
		});
	}

	//consulta el detalle del involucrado
	function consultaDetalle(){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
	    	  data: 'idInvolucrado='+id,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  datosXML=xml;
	    		  pintaDatosDomicilio(xml);
	    		  pintaDatosGenerales(xml);
			  }
	    });		
	}	

	//guarda el solicitante
	function guardaSolicitante(){
		//id del expediente
	    var parametros = 'idExpediente='+idExpediente;
		//extraemos la descripcion del hecho
		parametros +="&motivoAsesoria="+escape($('.jquery_ckeditor').val());
		//Datos generales
		parametros += obtenerParametrosDatosGenerales();
		//recuperamos los datos de lugar
		parametros += obtenerParametrosDomicilio();
		//Datos nacimiento
		parametros += obtenerParametrosDatosNacimiento();
		//Medios de contacto
		parametros += obtenerMedios();
		//modificar Individuo
		parametros += '&idIndividuo='+ id;
		$.ajax({								
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/guardaSolicitanteAsesoria.do',
	    	  data: parametros,				
	    	  dataType: 'xml',
	    	  success: function(xml){
	    			 customAlert("Se guardó con éxito");						 
	 				//idIndividuo = $(xml).find("long").text();
	    	  }
	    	});
	}

	function documentos(){
		//carga grid de documentos	
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarDocumentosDefensoria.do?tipo=3&idExpedienteop='+numeroExpediente, 
			datatype: "xml", 
			colNames:['Tipo de documento','Fecha','Nombre de Documento','Nombre de la actividad','Fecha de la actividad','Área del responsable'], 
			colModel:[ 	{name:'Tipo',index:'tipo', width:155, align:"center"}, 
			           	{name:'Fecha',index:'fecha', width:90, align:"center"},
						{name:'Nombre',index:'nombre', width:255, align:"center"},
						{name:'nombreActividad',index:'nombreActividad', width:400, align:"center"},
						{name:'FechaActividad',index:'fechaActividad', width:170, align:"center"},							
						{name:'area',index:'area', width:200, align:"center"}
						],
			pager: jQuery('#pager1'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: false,
			width:1100,
			sortname: 'turno',
			viewrecords: true,
			id: 'divgrid',
			onSelectRow: function(id){
				consultaPDF(id);
			},
			sortorder: "desc"
		}).navGrid('#pager1',{edit:false,add:false,del:false});
		$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '500px');
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	}

	function consultaPDF(id){
		document.formaArchivoD.documentoId.value = id;
		document.formaArchivoD.submit();
	}


	function gridRelacionarDelitosTabDelito(){
		idNumeroExpedienteOp=numeroExpediente;
		cargaComboProbableResponsableRDPPV();
		cargaComboDelitosAAsociarRDPD();
		jQuery("#gridDetalleTabDelitoRelDel").jqGrid({ 
			url:'<%= request.getContextPath()%>', 
			datatype: "xml", 
			colNames:['','', '','', '','','', '','','' ], 
			colModel:[ 	{name:'caso',index:'caso', width:20}, 
						{name:'expediente',index:'expediente', width:20}, 
						{name:'defensor',index:'defensor', width:20}, 
						{name:'detenido',index:'detenido', width:20},
						{name:'delito',index:'delito', width:20},
						{name:'institucion',index:'institucion', width:20},
						{name:'fecha',index:'fecha', width:20},
						{name:'hora',index:'hora', width:20},
						{name:'abogado',index:'abogado', width:20},
						{name:'enterado',index:'enterado', width:20}
					],
			pager: jQuery('#pagerGridDetalleTabDelitoRelDel'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
				
		}).navGrid('#pagerGridDetalleTabDelitoRelDel',{edit:false,add:false,del:false});

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
	
	/*
	*Funcion para asociar los delitos del grid de agraviados con el 
	*expediente
	*/
	function guardarDelitosAgraviadosExp()
	{
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
		var params="delitos="+nombreDelPrincipal+"-"+delitosNormales+"&numExp="+numeroExpediente;
		$.ajax({
       	  url: '<%= request.getContextPath()%>/guardarDelitosAgraviadosATP.do',
    	  dataType: 'xml',
    	  Type: 'POST',
    	  data:params,
    	  async: false,
    	  success: function(xml){
    		  if(parseInt($(xml).find('code').text())==0)
    		  {
    			  isDelitoSaved=true;
    			//mostramos las leyendas de canalizacion debajo del grid
    			  if(existeDelitoGraveEnGrid())
    			  {
    				  $("#leyendaUnDelitoGrave").show();
    				  $("#leyendaNingunDelitoGrave").hide();
    			  }
    			  else
    			  {
    				  $("#leyendaUnDelitoGrave").hide();
    				  $("#leyendaNingunDelitoGrave").show();
    			  }
    			  customAlert("Se guardaron exitosamente los delitos seleccionados");
    		  }	 
    		  else
    		  {
    			  isDelitoSaved=false;
    			  customAlert("Ocurrió un error al tratar de guardar los delitos agraviados");
    		  }
    		   //$(xml).find('delitoDTO').each(function(){
				//});    			    		
		  }
    	});
	}

	function ocultaMuestraTblsRelacionarDelitos(){
		var relacionDelitoPorPErsonaDelito = $(':radio[name=relacionaDelitos]:checked').val();
		if(parseInt(relacionDelitoPorPErsonaDelito)==0)
		{
			//Relacion por persona
			$("#tblRelacionaDelXPersona").show();
			$("#tblRelacionaDelXDelito").hide();
		}
		else
		{
			//Relacion por delito
			$("#tblRelacionaDelXDelito").show();
			$("#tblRelacionaDelXPersona").hide();
		}	
	}

	/*
	*Funcion que consulta los datos generales del resumen con carpeta de investigacion
	*/
	var loadedDatosGenerales = false;
	function datosGenerales(){
		if(!loadedDatosGenerales){
			loadedDatosGenerales =true;
			 $.ajax({
			      type: 'POST',
		    	  url: '<%= request.getContextPath()%>/cargarDatosGenerales.do?idNumeroExpedienteOp='+idExpediente,
		    	  data: '',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
						$('#Vehiculos').html($(xml).find('totalVehiculos').text());
						$('#EquiposDeComputo').html($(xml).find('totalEquiposComputo').text());
						$('#EquiposTelefonicos').html($(xml).find('totalEquiposTelefonicos').text());
						$('#Armas').html($(xml).find('totalArmas').text());
						$('#Explosivos').html($(xml).find('totalExplosivos').text());
						$('#Sustancias').html($(xml).find('totalSustancias').text());
						$('#Animales').html($(xml).find('totalAnimales').text());
						$('#Aeronaves').html($(xml).find('totalAeronaves').text());
						$('#Embarcacion').html($(xml).find('totalEmbarcaciones').text());
						$('#Inmueble').html($(xml).find('totalInmuelbes').text());
						$('#Numerario').html($(xml).find('totalNumerarios').text());
						$('#Vegetal').html($(xml).find('totalVegetales').text());
						$('#DocumentoOficial').html($(xml).find('totalDocumentosOficiales').text());
						$('#Joya').html($(xml).find('totalJoyas').text());
						$('#ObraDeArte').html($(xml).find('totalObrasDeArte').text());
						$('#Otros').html($(xml).find('totalOtrosObjestos').text());
						$('#Denunciantes').html($(xml).find('totalDenunciantes').text());
						$('#Victimas').html($(xml).find('totalVictimas').text());
						$('#ProbablesResponsables').html($(xml).find('totalProbablesResposables').text());
						$('#Testigos').html($(xml).find('totalTestigos').text());
						$('#Traductores').html($(xml).find('totalTraductores').text());
						$('#QuienDetuvo').html($(xml).find('quienDetuvo').text());
						$('#estatusExpe').html($(xml).find('estatusNumeroExpediente').text());
						$('#origenExpe').html($(xml).find('origenExpediente').text());	
						$('#anonimoDenun').html($(xml).find('esDesconocido').text());	
						$('#idTotalDocumentos').html($(xml).find('totalDocumentosDelExpediente').text());
				    	
		    	  }
		    	});
		}
	}

	/*
	*Funcion que realiza el cambio de lengua o dialecta en cedula de identificacion en la pestaña de datos generales
	*/	
	
	function cambiaLenguaDialecto(){

    	if($('#nacionalidadDEATT1').val()!=2056){

	    		$('#idiomaNacional').hide();	
		    	$('#idiomaExtranjero').show();		    						

	    	}else{
	    		$('#idiomaNacional').show();	
		    	$('#idiomaExtranjero').hide();	
    		
	    	}	    	
    	

	    }

	/*
	*Funcion que realiza la carga del combo de Actuaciones
	*/
	function cargaActuaciones() {

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?numeroExpediente='+numeroExpedienteCadena+'',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAcciones').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});

                            
			}
		});
	}
	

	/*Comienzan funciones para crear ventanas de la pestaña Objetos*/
	
	function relacionarExpedienteACaso(){
	 	$.newWindow({id:"iframeRelacionarExpedienteACaso", statusBar: true, posx:200,posy:50,width:500,height:350,title:"Relacionar expediente "+numeroExpedienteCadena+"a caso", type:"iframe"});
	  	$.updateWindowContent("iframeRelacionarExpedienteACaso",'<iframe src="<%= request.getContextPath() %>/RelacionarExpedienteACaso.do?numeroExpedienteId='+numeroExpediente+'" width="1050" height="600" />');
	}
	
	function creaNuevoEquipoDeComputo(){
		idWindowIngresarEquipoDeComputo++;
		$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Ingresar equipo de c&oacute;mputo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpedienteCadena +'&idEquipoComputo=0&tipoObjeto=EQUIPO_COMPUTO" width="830" height="340" />');
	}
	
	function consultarEquipoComputo(idEquipoComputo){
		idWindowIngresarEquipoDeComputo++;
		$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Consultar equipo de c&oacute;mputo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpedienteCadena+'&idEquipoComputo='+idEquipoComputo+'&tipoObjeto=EQUIPO_COMPUTO " width="830" height="340" />');
	}
	
	function cargaEquipoComputo(id,tipo){
		$('#tblEquipoComputo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarEquipoComputo_'+id+'" onclick="consultarEquipoComputo('+id+')">'+tipo+'</a></td></tr>');
		cerrarVentanaEquipoComputo();
	} 
	
	function cerrarVentanaEquipoComputo(){
		var pantalla ="iframewindowIngresarEquipoDeComputo";
		pantalla += idWindowIngresarEquipoDeComputo;
		$.closeWindow(pantalla);
	}
		
	function creaNuevoEquipoTelefonico(){
		 idWindowIngresarEquipoTelefonico++;
		$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpedienteCadena+'&idEquipoTelefonico=0&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
	}
	
	function consultarEquipoTelefonico(idEquipoTelefonico){
		 idWindowIngresarEquipoTelefonico++;
		$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Consultar equipo telef&oacute;nico", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpedienteCadena+'&idEquipoTelefonico='+idEquipoTelefonico+'&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
	}

	function cargaEquipoTelefonico(id,modelo){
		$('#tblEquipoTelefonico').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarEquipoTelefonico_'+id+'" onclick="consultarEquipoTelefonico('+id+')">'+modelo+'</a></td></tr>');
		cerrarVentanaEquipoTelefonico();
	} 
	
	function cerrarVentanaEquipoTelefonico(){
		var pantalla ="iframewindowIngresarEquipoTelefonico";
		pantalla += idWindowIngresarEquipoTelefonico;
		$.closeWindow(pantalla);
	}
	 
	function creaNuevaArma(){
		 idWindowIngresarArma++;
		$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpedienteCadena+'&idArma=0&tipoObjeto=ARMA " width="800" height="380" />');
	}

	function consultarArma(idArma){
		 idWindowIngresarArma++;
		$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Consultar arma", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpedienteCadena+'&idArma='+idArma+'&tipoObjeto=ARMA" width="800" height="380" />');
	}
	
	function cargaArma(id,tipo){
		$('#tblArma').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarArma_'+id+'" onclick="consultarArma('+id+')">'+tipo+'</a></td></tr>');
		cerrarVentanaArma();
	} 
	
	function cerrarVentanaArma(){
		var pantalla ="iframewindowIngresarArma";
		pantalla += idWindowIngresarArma;
		$.closeWindow(pantalla);
	}
	
	function creaNuevoExplosivo(){
		 idWindowIngresarExplosivo++;
		$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpedienteCadena +'" width="880" height="330" />');
	}

	
	function creaNuevaSustancia(){
		 idWindowIngresarSustancia++;
		$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpedienteCadena +'" width="900" height="330" />');
	}

	
	function creaNuevoAnimal(){
		 idWindowIngresarAnimal++;
		$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpedienteCadena +'" width="900" height="330" />');
	}

	
	function creaNuevoVehiculo(){
		 idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpedienteCadena +'&tipoObjeto=VEHICULO&idVehiculo=0 " width="570" height="600" />');
	}
	
	function consultarVehiculo(idVehiculo){
		 idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpedienteCadena +'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO " width="570" height="600" />');
	}

	function cargaVehiculo(id,tipo,placas){
		$('#tblVehiculos').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarVehiculo_'+id+'" onclick="consultarVehiculo('+id+')">'+tipo+' '+ placas+'</a></td></tr>');
		cerrarVentanaVehiculo();
	} 
	
	function cerrarVentanaVehiculo(){
		var pantalla ="iframewindowIngresarVehiculo";
		pantalla += idWindowIngresarVehiculo;
		$.closeWindow(pantalla);
	}

	 
	function creaNuevaAeronave(){
		 idWindowIngresarAeronave++;
		$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpedienteCadena +'" width="600" height="530" />');
	}

	
	function creaNuevaEmbarcacion(){
		 idWindowIngresarEmbarcacion++;
		$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpedienteCadena +'" width="600" height="530" />');
	}

	 
	function creaNuevoInmueble(){
		 idWindowIngresarInmueble++;
		$.newWindow({id:"iframewindowIngresarInmueble" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:0,width:768,height:540,title:"Ingresar inmueble", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarInmueble" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do?numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="600" />');
	}
	 

	function creaNuevoNumerario(){
		 idWindowIngresarNumerario++;
		$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpedienteCadena +'" width="800" height="350" />');
	}

	 
	function creaNuevoVegetal(){
		 idWindowIngresarVegetal++;
		$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpedienteCadena +'" width="800" height="350" />');
	}
	 

	function creaNuevoDocumentoOficial(){
		 idWindowIngresarDocumentoOficial++;
		$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Ingresar documento oficial", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpedienteCadena +'" width="800" height="350" />');
	}
	 

	function creaNuevaJoya(){
		 idWindowIngresarJoya++;
		$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpedienteCadena +'" width="850" height="380" />');
	}
	 

	function creaNuevaObraDeArte(){
		 idWindowIngresarObraDeArte++;
		$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe"});
	    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpedienteCadena +'" width="850" height="380" />');
	}

	/*Comienzan funciones para crear ventanas de la pestaña Involucrado*/

	/*  inicia ingresar consultar y modificar victima*/
	
	//Abre una nueva ventana de crear una nueva victima
	function creaNuevaVictima() {
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="600" />');		
	}
	//carga al denunciante para su consulta
	function cargaVictima(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaVictima('+id+')">'+nombre+'</a></td></tr>');
		//$('#nuevaVictima').hide();
	} 

	//carga al denunciante para su consulta como victima y denunciante
	function cargaVictimaDenunciante(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
		//$('#nuevaVictima').hide();
	} 

	//funcion de rehuso
	function modificaVictima(id){
		modificarVictima(id);
	}

	//Abre una nueva ventana de crear una nueva victima
	function modificarVictima(id) {
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?idVictima='+id +'&numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="600" />');		
	}

	/*  termina ingresar consultar y modificar victima*/

	/*  inicia ingresar consultar y modificar traductor*/
															
	//Abre una nueva ventana de ingresar traductor
	function creaNuevoTraductor() {
		idWindowIngresarTraductor++;
	$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Traductor", type:"iframe"});
    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroExpedienteCadena+'" width="1050" height="600" />');		
	}	
	//carga al denunciante para su consulta como victima y denunciante	
	function cargaTraductor(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblTraductor').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarTraductor" onclick="modificaTraductor('+id+')">'+nombre+'</a></td></tr>');
		
	} 
	//funcion de rehuso
	function modificaTraductor(id){
		modificarTraductor(id);
	}
	//Abre una nueva ventana de ingresar traductor
	function modificarTraductor(id) {
		idWindowIngresarTraductor++;
	$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Traductor", type:"iframe"});
    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?idTraductor='+id+'&numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="600" />');		
	}	

	/*  termina ingresar consultar y modificar traductor*/
	
	/*  inicia ingresar consultar y modificar quien detuvo*/															
		
	//Abre una nueva ventana de ingresar quien detuvo
	function creaQuienDetuvo() {
		idWindowIngresarQuienDetuvo++;
	$.newWindow({id:"iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Quién detuvo", type:"iframe"});
    $.updateWindowContent("iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo,'<iframe src="<%= request.getContextPath() %>/IngresarQuienDetuvo.do?elemento='+0+'&numeroExpediente='+numeroExpedienteCadena+'" width="1050" height="600" />');
	}	

	function cargaQuienDetuvo(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblQuienDetuvo').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarQuienDetuvo" onclick="">'+nombre+'</a></td></tr>');
		cerrarVentanaQuienDetuvo();
	} 

	function cerrarVentanaQuienDetuvo(){
	    var pantalla ="iframewindowQuienDetuvo";
		pantalla += idWindowIngresarQuienDetuvo;
		$.closeWindow(pantalla);
	}

	/*  termina ingresar consultar y modificar quien detuvo*/
	
	/*  inicia ingresar consultar y modificar representante legal*/
	
	//Abre una nueva ventana de ingresar representante legal
	function creaNuevoRepresentanteLegal() {
		idWindowIngresarRepresentanteLegal++;
	$.newWindow({id:"iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Representante Legal", type:"iframe"});
    $.updateWindowContent("iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do" width="1050" height="600" />');		
	}	

	/*  termina ingresar consultar y modificar representante legal*/
	
	/*  inicia ingresar consultar y modificar probable responsable*/	
	
	//Abre una nueva ventana de probable responsable
	function creaNuevoProbResponsable() {
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?numeroExpediente='+numeroExpedienteCadena +'&calidadInv=PROBABLE_RESPONSABLE" width="1050" height="620" />');		
	}
	//carga al probable responsable para su consulta
	function cargaProbableResponsable(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		nombre=nombre+"- ";
		$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificaProbableResponsable('+id+')">'+nombre+'<bean:message key="indiciado" /></a></td></tr>');
		
	} 
	//funcion de rehuso
	function modificaProbableResponsable(id){
		modificarProbResponsable(id);
	}

	//Abre una nueva ventana de probable responsable
	function modificarProbResponsable(id) {
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="620" />');			
	}


	/*  termina ingresar consultar y modificar probable responsable*/	

	/*  inicia ingresar consultar y modificar denunciante*/
		
	//Abre una nueva ventana de Denunciante
	function crearDenunciante(){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroExpedienteCadena +'&calidadInv=DENUNCIANTE" width="1040" height="570" />');		
	}
	//carga al denunciante para su consulta
	function cargaDenunciante(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblDenunciante').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarDenunciante" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
		$('#crearDenunciante').hide();
	} 
	//funcion que modifica el denunciante rehuso
	function modificaDenunciante(id){
		modificarDenuncianteDatos(id);
	}
	
	//Abre una nueva ventana de Denunciante para su modificacion
	function modificarDenuncianteDatos(id){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpedienteCadena +'" width="1040" height="570" />');		
	}
													/*  termina ingresar consultar y modificar denunciante*/
	
	//Crea una nueva ventana de testigo
	function creaNuevoTestigo() {
		idWindowIngresarTestigo++;
		$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Testigo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?numeroExpediente='+numeroExpedienteCadena +'" width="1050" height="600" />');		
	}
	//Crea una ventana de un nuevo contacto de una organizacion		
	function creaNuevoContactoDeUnaOrganizacion() {
		
		idWindowIngresarContactoDeUnaOrganizacion++;
		$.newWindow({id:"iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Contacto de una organización", type:"iframe"});
		$.updateWindowContent("iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do" width="1050" height="620" />');		
	}
	//Crea una ventata de un nuevo sentenciado a reinsertar
	function crearSentenciadoReinsertar() {
		idWindowIngresarSentenciadoReinsertar++;
		$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar nuevo sentenciado a reinsertar", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/IngresarSentenciadoReinsertar.do" width="1050" height="600" />');		
	}
	//crea una nueva ventana de ingresar tutor
	function creaNuevoTutor() {
		idWindowIngresarTutor++;
		$.newWindow({id:"iframewindowTutor" + idWindowIngresarTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Datos Generales", type:"iframe"});
	    $.updateWindowContent("iframewindowTutor" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
	}
	
	function generaLigaInvolucrado(involucrado, funcion,  anonimo){
		var id =  $(involucrado).find('elementoId').text();
		var nombresDemograficos = $(involucrado).find('nombresDemograficoDTO');
		var liga = '<tr id="' +id+ '">';
		liga += '<td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="'+funcion+'(' + id + ');">';
		try{
			nombre = $(nombresDemograficos).find('nombreDemografico').first();
		}catch(e){
		}
		$(nombresDemograficos).find('nombreDemografico').each(function(){
			if($(this).find('esVerdadero')){
				nombre = $(this);
			}
		});
		
		if($(nombre).find('nombre').text()=='null'){
			liga += amonimo;
		}else{
			liga += $(nombre).find('nombre').text()+" "+$(nombre).find('apellidoPaterno').text()+" "+$(nombre).find('apellidoMaterno').text();
	  	}		
	  	liga += '</a></td></tr>';
	  	return liga;
	}

	//consulta y carga los involucrados 
	function cargarInvolucradosExpediente(idNumeroExpediente){
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultaDetalleExpedienteDefensoria.do',
    		data : 'numeroExpedienteId=' + idExpediente+'&numeroExpediente='+numeroExpedienteCadena,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    	      $(xml).find('involucradoDTO').each(function(){
    	    	  var calidad = parseInt($(this).find('calidadDTO').find('valorIdCalidad').find('idCampo').text());

    	    	  switch(calidad){
    	    	  	case <%= Calidades.DENUNCIANTE.getValorId() %>:
    	    	  		var victima = $(this).find('esVictima').text();
	    	    	  	$('#tblDenunciante').append(generaLigaInvolucrado($(this), 'modificaDenunciante', 'Anónimo'));
    	    	  		$('#crearDenunciante').css('display','none'); 
    	    	  		if(victima == 'true'){
    	    	  			$('#tblVictima').append(generaLigaInvolucrado($(this), 'modificaDenunciante', 'Desconocido'));
    	    	  		}
	    	    	  	$('#crearDenunciante').css('display','none');
    	    	  		break;
    	    	  	case <%= Calidades.DENUNCIANTE_ORGANIZACION.getValorId() %>:
	    	    	  	$('#tblDenunciante').append(generaLigaInvolucrado($(this), 'modificaDenunciante', 'Anónimo'));
	    	    	  	$('#crearDenunciante').css('display','none');
	    	    	  	break;
    	    	  	case <%= Calidades.VICTIMA_PERSONA.getValorId() %>:
	    	    	  	$('#tblVictima').append(generaLigaInvolucrado($(this), 'modificaVictima', 'Desconocido'));
	    	    	  	break;
    	    	  	case <%= Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId() %>:
    	    	  	case <%= Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() %>:
	    	    	  	$('#tblProbableResponsable').append(generaLigaInvolucrado($(this), 'modificaProbableResponsable', 'Desconocido'));
	    	    	  	break;
    	    	  	case <%= Calidades.TESTIGO.getValorId() %>:
	    	    	  	$('#tblTestigo').append(generaLigaInvolucrado($(this), 'modificaTestigo', 'Desconocido'));
	    	    	  	break;
    	    	  	case <%= Calidades.TESTIGO.getValorId() %>:
	    	    	  	$('#tblTraductor').append(generaLigaInvolucrado($(this), 'modificaTraductor', 'Desconocido'));
	    	    	  	break;

    	    	  }
	    	      
    	      });
    		}	
    	});
	}	


	/*
	*Funcion para asociar los delitos del grid de agraviados con el 
	*expediente
	*/
	function guardarDelitosAgraviadosExp()
	{
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
    			  customAlert("Se guardaron exitosamente los delitos seleccionados");
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

	/*Comienza funcionalidad para el tab de notas*/
	//carga nota
	function cargaNota(id){
		var row=$('#rowNota_'+id);
		$(row).remove();
		$('#tablaNotasExpediente').append('<tr id="rowNota_'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarNota_'+id+'" onclick="notaExpediente('+id+')">Nota</a></td></tr>');
		//cerrarVentanaNota();
	} 

	function notaExpediente(idNota){
		idWindowGenerarNotas++;
		$.newWindow({id:"iframewindowGenerarNotas" + idWindowGenerarNotas, statusBar: true, posx:200,posy:50,width:700, height:450,title:"Nota de Expediente", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarNotas" + idWindowGenerarNotas,'<iframe src="<%= request.getContextPath() %>/capturarNotaExpediente.do?idNumeroExpedienteOp='+numeroExpediente +'&idNota='+idNota+' " width="700" height="450" />');
	}

	function cerrarVentanaNota(){
		var pantalla ="iframewindowGenerarNotas";
		pantalla += idWindowGenerarNotas;
		$.closeWindow(pantalla);
	}

	/*
	*Consultar las notas del expediente
	*POR EL MOMENTO SOLO SE CONSULTA UNA NOTA
	*/
	function consultarNotas(){
		
		var notas=$('#editor1').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarNotasExpediente.do?idNumeroExpediente='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('notaExpedienteDTO').each(function(){
					cargaNota($(this).find('idNota').text());
				});
			}
		});
	}

	function abreTeoria() {
		$.newWindow({id:"iframewindowAbrirteoria", statusBar: true, posx:20,posy:20,width:1390,height:600,title:"Teoria del caso", type:"iframe"});
	    $.updateWindowContent("iframewindowAbrirteoria",'<iframe src="<%= request.getContextPath() %>/teoriaDelCasoView.jsp?idExpediente='+idExpediente+'&numeroExpediente='+numeroExpedienteCadena+'" width="1390" height="600" />');
	      		
	}

	/*Termina funcionalidad del tab de notas*/	

	</script>
	
	<style type="text/css">
	
	<!--ESTILOS PARA LAS TABS-->
		
		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 600px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
		
	</style>
			
</head>
<body>
<!-- Comienza Tabs Principal del menu -->

<div id="tabsprincipalconsulta">
	<ul>
		<li id="tabResumen" style="display: none"><a href="#tabsconsultaprincipal-1" onclick="datosGenerales()">Resumen</a></li>
		<li id="tabDatosInteresado" style="display: none"><a href="#tabsconsultaprincipal-21">Datos del interesado</a></li>
		<li id="tabMotivoAsesoria"  style="display: none"><a href="#tabsconsultaprincipal-20">Motivo de asesoría</a></li>
		<li id="tabInvolucrados" style="display: none"><a href="#tabsconsultaprincipal-13">Involucrados</a></li>
		<li id="tabObjetosEvidencias" style="display: none"><a href="#tabsconsultaprincipal-15">Objetos y evidencias</a></li>		
		<li id="tabCadenaCustodia" style="display: none"><a href="#tabsconsultaprincipal-16">Cadena de Custodia</a></li>	
		<li id="tabDiligencias" style="display: none"><a href="#tabsconsultaprincipal-14">Diligencias</a></li>
		<li id="tabAudiencias" style="display: none"><a href="#tabsconsultaprincipal-12" onclick="gridAudiencia()">Audiencias</a></li>
		<li id="tabPericiales" style="display: none"><a href="#tabsconsultaprincipal-22">Periciales</a></li> <!--onclick : gridPericiales() -->
		<li id="tabActuaciones" style="display: none"><a href="#tabsconsultaprincipal-9">Actuaciones</a></li> 
		<li id="tabNotas" style="display: none"><a href="#tabsconsultaprincipal-10">Notas</a></li>
		<li id="tabDocumentos" style="display: none"><a href="#tabsconsultaprincipal-11" onclick="documentos()">Documentos</a></li>
		<li id="tabBitacora" style="display: none"><a href="#tabsconsultaprincipal-4" onclick="cargaGridBitacora()">Bitácora</a></li>
	</ul>
	
	<!--Inicia Pestaña de Resumen del Expediente-->
	<div id="tabsconsultaprincipal-1" style="display: none;">
		<!--Inicia Resumen Expediente -->
		<div id="resumen1">
			<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
			<tr>
				<td style="vertical-align:top;">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
					<tr id = "trCaso">
						<td align="right" width="50%">
							<strong>Número de caso:</strong>
						</td>
						<td width="50%">
							<input class="texto" type="text" readonly="readonly" id="caso"/>
						</td>
					</tr>
					<tr id = "trExpediente">
						<td align="right" width="50%">
							<strong>Número de expediente:</strong>
						</td>
						<td width="50%">
							<input class="texto" type="text" readonly="readonly" id="expediente"/>
						</td>
					</tr>
					<tr id = "trEtapaExpediente">
						<td align="right">
							<strong>Etapa del Expediente:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="etapaExpediente"/>
						</td>
					</tr>
					<tr id = "trImputado">
						<td align="right">
							<strong>Nombre del imputado:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="imputado"/>
						</td>
					</tr>
					<tr id = "trParaQuien">
						<td align="right">
							<strong>Para quien se solicita:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="paraQuien"/>
						</td>
					</tr>
					<tr id = "trInteresado">
						<td align="right">
							<strong>Interesado:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="interesado"/>
						</td>
					</tr>
					<tr id = "trDelito">
						<td align="right">
							<strong>Delito:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="delito"/>
						</td>
					</tr>
					<tr id = "trDefensor">
						<td align="right">
							<strong>Nombre del defensor:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="defensor"/>
						</td>
					</tr>
					<tr id = "trFechaDesignacion">
						<td align="right">
							<strong>Fecha Designación:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaDesignacion"/>
						</td>
					</tr>
					<tr id = "trHoraDesignacion">
						<td align="right">
							<strong>Hora Designación:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaDesignacion"/>
						</td>
					</tr>
					</table>
				</td>
				<td style="vertical-align:top;">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
					<tr id = "trTipoAudiencia">
						<td align="right">
							<strong>Tipo de Audiencia:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="tipoAudiencia"/>
						</td>
					</tr>
					<tr id = "trSalaAudiencia">
						<td align="right">
							<strong>Sala Asignada:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="salaAudiencia"/>
						</td>
					</tr>
					<tr id = "trFechaAudiencia">
						<td align="right">
							<strong>Fecha de Audiencia:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaAudiencia"/>
						</td>
					</tr>
					<tr id = "trHoraAudiencia">
						<td align="right">
							<strong>Hora de Audiencia:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaAudiencia"/>
						</td>
					</tr>
					<tr id = "trDetenido">
						<td align="right">
							<strong>Detenido:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="detenido"/>
						</td>
					</tr>
					<tr id = "trLugarDetencion">
						<td align="right">
							<strong>Lugar donde se encuentra detenido:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="lugarDetencion"/>
						</td>
					</tr>
					<tr id = "trFechaDetencion">
						<td align="right">
							<strong>Fecha de Detención:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaDetencion"/>
						</td>
					</tr>
					<tr id = "trHoraDetencion">
						<td align="right">
							<strong>Hora de Detención:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaDetencion"/>
						</td>
					</tr>
					<tr id = "trFechaMensaje">
						<td align="right">
							<strong>Fecha envio del aviso:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaMensaje"/>
						</td>
					</tr>
					<tr id = "trHoraMensaje">
						<td align="right">
							<strong>Hora envio del aviso:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaMensaje"/>
						</td>
					</tr>
					<tr id = "trFechaVencimiento">
						<td align="right">
							<strong>Fecha vencimiento de atencion:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaVencimiento"/>
						</td>
					</tr>
					<tr id = "trHoraVencimiento">
						<td align="right">
							<strong>Hora vencimiento de atencion</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaVencimiento"/>
						</td>
					</tr>
					<tr id = "trFechaSolicitud">
						<td align="right">
							<strong>Fecha Solicitud:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaSolicitud"/>
						</td>
					</tr>
					<tr id = "trHoraSolicitud">
						<td align="right">
							<strong>Hora Solicitud</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaSolicitud"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
		<!--Termina Resumen Expediente -->
				
		<!-- Inicia Resumen Expediente con Carpeta de Investigación -->
		<div id="resumen2" style="display: none">
			<table width="1042px"  height="565px" border="0" cellspacing="0" cellpadding="0" class="back_generales">
				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>			
	  			<tr style="border-bottom-style: solid; border: 1;background-image:">
	   				 <td width="248" style="font-size:14px; background-color:" align="right"><strong>Estatus del Expediente:</strong></td>
	   				 <td width="100" style="font-size:14px; background-color:" >&nbsp;</td>
				     <td width="203" align="right" style="font-size:14px; background-color:"><strong>Resumen de objetos:</strong></td>
				     <td width="100" style="font-size:14px; background-color:">&nbsp;</td>
				     <td width="203" align="right" style="font-size:14px; background-color:"><strong>Resumen de involucrados<em>:</em></strong></td>	
				     <td width="46" style="font-size:14px; background-color:">&nbsp;</td>
			   </tr>
	  		   <tr>
	  				 <td  colspan="6" height="20px">
	    				<table width="978" border="0" cellpadding="0" cellspacing="0" class="linea_down_gral" align="center">
			  				<tr>
			   					 <td>&nbsp;</td>
			 				</tr>
						</table>			    	
	   				 </td>
	 			</tr>
				<tr valign="top">
				    <td id="estatusExpe" align="center">&nbsp;</td>
				    <td>&nbsp;</td>
				    <td rowspan="16" align="right" style="background-color:" valign="top">
						<table border="0" cellpadding="0" cellspacing="0"  style="background-color: #DCDDDE">
     					     <tr>
					            <td nowrap align="right" style="background-color:">Vehículos:</td>
					            <td id="Vehiculos">&nbsp;</td>
         					 </tr>
         					 <tr>
					            <td align="right" style="background-color:">Equipos de cómputo:</td>
					            <td id="EquiposDeComputo">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Equipos Telefónicos:</td>
					            <td id="EquiposTelefonicos">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Armas:</td>
					            <td id="Armas">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Explosivos:</td>
					            <td id="Explosivos">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Sustancias:</td>
					            <td id="Sustancias">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Animales:</td>
					            <td id="Animales">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Aeronaves:</td>
					            <td id="Aeronaves">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Embarcación:</td>
					            <td id="Embarcacion">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Inmueble:</td>
					            <td id="Inmueble">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Numerario:</td>
					            <td id="Numerario">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Vegetal:</td>
					            <td id="Vegetal">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Documento oficial:</td>
					            <td id="DocumentoOficial">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Joya:</td>
					            <td id="Joya">&nbsp;</td>
					          </tr>
					          <tr>
					            <td align="right" style="background-color:">Obra de arte:</td>
					            <td id="ObraDeArte">&nbsp;</td>
					          </tr>
					          <tr>
					            <td height="19" align="right" style="background-color:">Otros:</td>
					            <td id="Otros">&nbsp;</td>
					          </tr>
						</table>
					&nbsp;
					</td>
					<td>&nbsp;</td>
	  			    <td rowspan="6" align="right" style="background-color:">
						<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="background-color: #DCDDDE">
						  <tr>
						    <td align="right" style="background-color:">Denunciantes:</td>
						    <td id="Denunciantes">&nbsp;</td>
						  </tr>
						  <tr>
						    <td align="right" style="background-color:">Victimas:</td>
						    <td id="Victimas">&nbsp;</td>
						  </tr>
						  <tr>
						    <td align="right" style="background-color:">Probables Responsables:</td>
						    <td id="ProbablesResponsables">&nbsp;</td>
						  </tr>
						  <tr>
						    <td align="right" style="background-color:">Testigos:</td>
						    <td id="Testigos">&nbsp;</td>
						  </tr>
						  <tr>
						    <td align="right" style="background-color:">Traductores/Intérpretes:</td>
						    <td id="Traductores">&nbsp;</td>
						  </tr>
						  <tr>
						    <td align="right" style="background-color:">Quién detuvo:</td>
						    <td id="QuienDetuvo">&nbsp;</td>
						  </tr>
						</table>
					</td>
	              </tr>
			      <tr>
	 				   <td  style="background-color:"align="right">
	    					<span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:">
	    						<img src="<%=request.getContextPath()%>/resources/images/icn_doc_chek.png"><strong>Tipo:</strong>
	    					</span>
	   				  </td>
	   				   <td >&nbsp;</td>
	    
	    
	 			 </tr>
	 			 <tr>
	 			    <td id="origenExpe" align="right"></td>
	  				<td ></td>
	             </tr>
				 <tr>
				   <td></td>
				   <td></td>			    
				    
				 </tr>
				 <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				 </tr>
				 <tr>
				    <td  id="anonimoDenun" align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				 </tr>
				  <tr>
				    <td align="right"><span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:; display:none;"><strong>Canalizado a:</strong></span> </td>
				    <td><!-- <input type="radio" name="radio" id="rbtnRestaurativa" value="R" />--></td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td></td>
				    <td><!-- <input type="radio" name="radio" id="rbtnUnidadDeInvestigacion" value="rbtnUnidadDeInvestigacion" />--></td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td></td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td align="right"><span id="spanGralJAR">Justicia Alternativa Restaurativa</span></td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td align="right"><span id="spanGralUI">Unidad de Investigación: </span><span id="spanInfoGralUI"></span></td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td align="right"><span id="spanGralIE">Instituci&oacute;n Externa: </span><span id="spanInfoGralIE"></span></td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
			</table>
			<table width="1042px"  height="22px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><img src="<%=request.getContextPath()%>/resources/images/pleca_down_grales.jpg"></td>
				</tr>
			</table>
		</div>
		<!--Termina Resumen Expediente con Carpeta de Investigación -->
		
	</div>
	<!--Termina Pestaña de Resumen del Expediente-->
		
		<!--Inicia Pestaña de Cedula de identificacion -->
		<div id="tabsconsultaprincipal-2" style="display: none;" align="center">		
		     <% Long idEtapa =  Long.parseLong(request.getParameter("etapa")); %>
		     <% if(idEtapa.longValue() == EtapasExpediente.INTEGRACION.getValorId().longValue()){ %>
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
						                                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpNombre1 title="Escribir nombre" readOnly maxLength=40 size=30 type=text>
						                </TD>
						              </TR>
						              <TR>
						                <TD width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
						                <TD height=28 width="64%">
						                	<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
						                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoPaterno1 readOnly maxLength=40 size=30 type=text>
						                </TD>
						              </TR>
             						  <TR>
						                <TD width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
							            <TD height=29>
							            	<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
							                  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoMaterno1 readOnly maxLength=40 size=30 type=text>
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
					   <!-- <TR vAlign=top> -->
				       <TR vAlign=top>
				          <TD colSpan=3>&nbsp;</TD>
				        </TR>
       				 </TBODY>
   				 </TABLE> 		
   				 <!--Inicia Acordeon de Cedula de identificacion -->
						<div id="cedulaIdentificacion" >
						    <dl>
			                    <dt id="cejaDatosGenerales">Datos Generales</dt>
			                      <dd>	
			                      <jsp:include page="datosGeneralesView.jsp"></jsp:include>
								
			                      </dd>
			                    <dt id="cejaDomicilio">Domicilio</dt>
			                      <dd>
			                    	<jsp:include page="ingresarDomicilioView.jsp"></jsp:include>
			                      </dd>
			                    <dt id="cejaMediosContacto">Medios de Contacto</dt>
			                       <dd>
			                         <jsp:include page="ingresarMediosContactoView.jsp"/>
			                       </dd>
			                    <dt id="cejaMediaFiliacion">Media Filiación</dt>
			                       <dd>
			                       	<jsp:include page="mediaFiliacionDefensoria.jsp"></jsp:include>
								  </dd>
			                    <dt id="cejaDetenido">Delitos</dt>
			                      <dd>
			                      	<jsp:include page="seleccionarDelitoView.jsp"></jsp:include>
			                      </dd>
			                    <dt id="cejaDetenido">Hechos</dt>
			                      <dd>
			                      <!--Inicia pestañas de hechos dentro de acordeon hechos -->
									<div id="tabsHechos" class="tabEstilo"> 
									    <ul>
									        <li id="hechosIngHechosTab"><a href="#tabsHechos-1">Hechos</a></li>
									        <li id="fechaHoraTab"><a href="#tabsHechos-3">Fecha y hora de los hechos</a></li>
									    </ul>
									    	<!--Inicia pestaña Hechos -->
								   			 <div id="tabsHechos-1">
								    		   	<textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea>
								    		</div>
								    		<!--Termina pestaña Hechos -->
								    		
								    		<!--Inicia pestaña Fecha y hora de los hechos -->
								    		<div id="tabsHechos-3">
								    			<table id="datosDetenido" width="400" height="150" border="0" cellspacing="0" cellpadding="10">
					                    		    <tr>
					                          			<td colspan="2" height="20" valign="middle" class="seccion">Ingresar datos de detenci&oacute;n</td>
					                        		</tr>
					                        		<tr>
					                          			<td align="left">
					                              			<input type="submit" id="ingresarTiempoEspecificamentePResponsable" value="Espec&iacute;ficamente" />
					                          			</td>
					                          			<td width="55%" rowspan="3">
					                            			<div id="divEspecifico" style="display: block;">
					                                			<jsp:include page="/WEB-INF/paginas/ingresarTiempoEspecificamente.jsp" flush="true"></jsp:include>
					                            			</div>
					                            			<div id="divLapso" style="display: none;">
					                                			<jsp:include page="/WEB-INF/paginas/ingresarTiempoLapso.jsp" flush="true"></jsp:include>
					                            			</div>
					                            			<div id="divOtro" style="display: none;">
					                            					Evento en el tiempo: <br/>
					                                				&nbsp;&nbsp;&nbsp;<textarea rows="5" cols="100" id="textNarrativa" ></textarea>
					                            			</div>						                		
					                          			</td>
					                        		</tr>
					                   			     <tr>
					                         			 <td align="left">
					                              			<input style="width:112px" type="submit" id="ingresarTiempoLapsoPResponsable" value="Lapso" />
					                          			</td>
					                        		</tr>
					                        		<tr>
					                          			<td align="left">
					                              			<input style="width:112px" type="submit" id="ingresarTiempoOtroPResponsable" value="Otro" />
					                          			</td>
					                        		</tr>
					                      		</table>
								    		</div>
								    		<!--Termina pestaña Fecha y hora de los hechos -->
								    	</div>
								    	 <!--Termina pestañas de hechos dentro de acordeon hechos -->
						             </dd>
				                  </dl>
				        		</div>
				        		 <!--Termina Acordeon de Cedula de identificacion -->
			        	  <%} %>
			        	 </div>
			        	<!--Termina Pestaña de Cedula de identificacion -->
        			
        	<!--Inicia Pestaña de Bitacora -->
			<div id="tabsconsultaprincipal-4">			
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			            <td><br></td>
			        </tr>
			        <tr>
			            <td>
			            	<div id="divBitacora">
				            	<table id="gridBitacora"></table>
				            	<div id="pagerGridBitacora"></div>
			            	</div>
			            </td>
			        </tr>
			    </table>	
			</div>
			<!--Termina Pestaña de Bitacora -->
			
			<!--Inicia Pestaña de Actuaciones -->
			<div id="tabsconsultaprincipal-9">
					
				 	 <table width="100%" cellspacing="0" cellpadding="0">
				  <tr>
				    <td width="1%" >&nbsp;</td>
				    <td width="15%" >&nbsp;</td>
				    <td width="6%" >&nbsp;</td>
				    <td width="78%">&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td ><strong>Actuaciones:</strong></td>
				    <td ><select name="acciones" id="cbxAcciones">
				    	<option>- Seleccione</option>				      				     
				      	</select></td>
				    <td><input type="button" value="Cambiar etapa del expediente" id="cambiarEtapaDelExpediente" onClick="cambiarEtapaExpediente();" class="btn_Generico"/></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td><input type="button" value="Teoría del caso/Escritos de prueba" id="teoriaDelCaso" onClick="abreTeoria();" class="btn_Generico"/></td>
				  </tr>
				  <tr id="btnRelacionExpedienteCaso">
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td><input type="button" value="Relacionar expediente a caso" id="relacionarExpediente" onClick="relacionarExpedienteACaso();" class="btn_Generico"/></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td><input type="button" value="Adjuntar documento" id="btnAdjuntarDocumento" onClick="abreVentanaAdjuntarDocumento('1');" class="btn_Generico"/></td>
				  </tr>
				</table>  
			
			</div> 
			<!--Termina Pestaña de Actuaciones -->			
			
			<!--Inicia Pestaña de Notas -->
			<div id="tabsconsultaprincipal-10">
				<table width="25%" cellpadding="0" cellspacing="0" id="tablaNotasExpediente">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" class="btn_modificar" value="Generar Nota"  id="botonGuardarNotas" onclick="notaExpediente(0);" class="btn_Generico"/></td>
						</tr>
				</table>
			</div>
			<!--Inicia Pestaña de Notas -->
			
			<!--Inicia Pestaña de Documentos -->
			<div id="tabsconsultaprincipal-11">
				<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1"></div>
						<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
							<input type="hidden" id="documentoId" name="documentoId" />
						</form>
						<form name="frmDoc2" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
							<input type="hidden" name="formaId" />
							<input type="hidden" name="numeroUnicoExpediente" />
						</form>
				<table id="tableDocs">
					<tr>
						<td>Total de documentos:</td>
						<td id="idTotalDocumentos">&nbsp;</td>
					</tr>
				</table>
			</div>
			<!--Termina Pestaña de Documentos -->
			
			<!--Inicia Pestaña de audiencia -->
				<div id="tabsconsultaprincipal-12" align="center">
						
							<table width="100%" id="gridAudiencia" onmouseup="camposSeleccionados()"></table>
							<div id="pagerAudiencia"></div>
						
							<!-- <table>
								<tr>
									<td>
									Audio y Video 
									</td>
									<td>
									Transcripción 
									</td>
								</tr>
								<tr>
									<td>
									<input type="radio" name="tipoAudiencia" value="0"/>
									</td>
									<td>
									<input type="radio" name="tipoAudiencia" value="1"/>
									</td>
								</tr>
							</table>
						<input type="button" disabled="disabled" value="Elaborar Solicitud" id="botonGeneraSolicitud" class="btn_Generico"/>  -->
				</div>
				<!--Termina Pestaña de audiencia -->
				
				<!--Inicia Pestaña de Delito -->
				<div id="tabsconsultaprincipal-14">
						<!--Inicia Pestaña de hijos de Delito -->
					<div id="tabschild2" class="tabs-bottom">
						<ul>
							<li><a href="#tabschild2-1">Seleccionar delitos</a></li>
							<li><a href="#tabschild2-2" onclick="gridRelacionarDelitosTabDelito()">Establecer Delito Persona</a></li>					
						</ul>
								<!--Inicia Pestaña de Seleccionar Delito -->
								<div id="tabschild2-1">
									<table width="25%" cellpadding="0" cellspacing="0">
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a id="nuevaSentenciado"><jsp:include page="seleccionarDelitoView.jsp"></jsp:include></a>
											</td>
										</tr>
									</table>
								</div>
								<!--Termina Pestaña de Seleccionar Delito -->
								
								<!--Inicia Pestaña de Establecer Delito -->
								<div id="tabschild2-2">
									<table>
										<tr>
											<td>
												Relacionar Delito por :  
												Persona <input type="radio" value="0" checked="checked" id="rdbMenuInterRelDelXPersona" name="relacionaDelitos"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												Delito <input type="radio" value="1" id="rdbMenuInterRelDelXDelito" name="relacionaDelitos"/>
											</td>
										</tr>
									</table>
									<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXPersona">
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<jsp:include page="relacionaDelitoPorPersonaView.jsp"></jsp:include>
											</td>
										</tr>
									</table>
									<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXDelito" style="display: none;">
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<jsp:include page="relacionaDelitoPorDelitoView.jsp"></jsp:include>
											</td>
										</tr>
									</table>
								</div>
								<!--Termina Pestaña de Establecer Delito -->
						</div>
				<!--Termina Pestaña de hijos de Delito -->
				</div>
			<!--Termina Pestaña de Delito -->
			
			<!--Inicia Pestaña de Objetos -->
			<div id="tabsconsultaprincipal-15">
					<!--Inicia Pestaña de hijos de objetos -->
					<div id="tabschild4" class="tabs-bottom">
						<ul>
							<li><a href="#tabschild4-7">Veh&iacute;culo</a></li>
							<li><a href="#tabschild4-1">Equipo de c&oacute;mputo</a></li>
							<li><a href="#tabschild4-2">Equipo telef&oacute;nico</a></li>
							<li><a href="#tabschild4-3">Arma</a></li>
							<li><a href="#tabschild4-4">Explosivo</a></li>
							<li><a href="#tabschild4-5">Sustancia</a></li>
							<li><a href="#tabschild4-6">Animal</a></li>					
							<li><a href="#tabschild4-8">Aeronave</a></li>
							<li><a href="#tabschild4-9">Embarcaci&oacute;n</a></li>
							<!-- <li><a href="#tabschild4-10">Inmueble</a></li>-->
							<li><a href="#tabschild4-11">Numerario</a></li>
							<li><a href="#tabschild4-12">Vegetal</a></li>
							<li><a href="#tabschild4-13">Documento oficial</a></li>
							<li><a href="#tabschild4-14">Joya</a></li>
							<li><a href="#tabschild4-15">Obra de arte</a></li>	
							<li><a href="#tabschild4-16">Otros</a></li>		
						</ul>
							
							<!--EQUIPO DE COMPUTO-->
							<div id="tabschild4-1">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEquipoComputo">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoDeComputo" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</div>
							<!--CIERRA EQUIPO DE COMPUTO-->
							
							<!--EQUIPO TELEFONICO-->
							<div id="tabschild4-2">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEquipoTelefonico">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoTelefonico" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</div>
							<!--CIERRA EQUIPO TELEFONICO-->
							
							<!--ARMA-->
							<div id="tabschild4-3">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblArma">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</div>
							<!--CIERRA ARMA-->
							
							<!--EXPLOSIVO-->
							<div id="tabschild4-4">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblExplosivo">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoExplosivo" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Explosivos 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Explosivos 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA EXPLOSIVO-->
							
							<!--SUSTANCIA-->
							<div id="tabschild4-5">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblSustancia">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Sustancia 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Sustancia 2</a></td>
									</tr>
								</table>
							</div>
								<!--CIERRA SUSTANCIA-->
							
							<!--ANIMAL-->
							<div id="tabschild4-6">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblAnimal">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoAnimal" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Animal 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Animal 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA ANIMAL-->
							
							<!--VEHICULO-->
							<div id="tabschild4-7">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVehiculos">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVehiculo" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</div>
							<!--CIERRA VEHICULO-->
							
							<!--AERONAVE-->
							<div id="tabschild4-8">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblAeronave">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaAeronave" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Aeronave 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Aeronave 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA AERONAVE-->
							
							<!--EMBARCACION-->
							<div id="tabschild4-9">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEmbarcacion">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Embarcaci&oacute;n 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;<a>Embarcaci&oacute;n 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA EMBARCACION-->
							
							<!--INMUEBLE-->
							<!-- <div id="tabschild4-10">
								<table width="25%" cellpadding="0" cellspacing="0">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoInmueble" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 2</a></td>
									</tr>
								</table>
							</div>-->
							<!--CIERRA INMUEBLE-->
							
							<!--NUMERARIO-->
							<div id="tabschild4-11">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblNumerario">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Numerario 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Numerario 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA NUMERARIO-->
							
							<!--VEGETAL-->
							<div id="tabschild4-12">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVegetal">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVegetal" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Vegetal 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Vegetal 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA VEGETAL-->
							
							<!--DOCUMENTO OFICIAL-->
							<div id="tabschild4-13">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblDocOficial">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoDocumentoOficial" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Documento oficial 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Documento oficial 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA DOCUMENTO OFICIAL-->
							
							<!--JOYA-->
							<div id="tabschild4-14">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblJoya">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaJoya" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Joya 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Joya 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA JOYA-->
							
							<!--OBRA DE ARTE-->
							<div id="tabschild4-15">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblObraArte">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaObraDeArte" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Obra de arte 1</a></td>
									</tr>
									<tr>
										<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Obra de arte 2</a></td>
									</tr>
								</table>
							</div>
							<!--CIERRA OBRA DE ARTE-->
					</div>
					<!--Cierra pestaña de hijos de objetos-->
				</div>
				<!--Termina Pestaña de Objetos -->
			
				<!--Inicia Pestaña de Cadena de custodia -->
				<div id="tabsconsultaprincipal-16">
					
				</div>
				<!--Termina Pestaña de Cadena de custodia -->
				  <% if(idEtapa.longValue() == EtapasExpediente.ASESORIA.getValorId().longValue()){ %>
				<!--Comienza Div Principal 20-->
				<div id="tabsconsultaprincipal-20" >
					<textarea class="jquery_ckeditor" cols="150" id="editor2" name="editor2" rows="10"></textarea>
				</div>
				<!--Termina Div Principal 20-->
				<!--Comienza Div Principal 21-->
				<div id="tabsconsultaprincipal-21">
				<table width="100%" >
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">
								 <input type="button" class="btn_guardar" value="Guardar" id="btnGuardar" />
							</td>
						</tr>
				</table>
					  <!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
		                <div id="cedulaIdentificacion">
		                  <dl>
		                    <dt id="cejaDatosGenerales">Datos Generales</dt>
		                      <dd>	
		                        <jsp:include page="datosGeneralesView.jsp"/>
		                      </dd>
		                    <dt id="cejaDomicilio">Domicilio</dt>
		                      <dd>
		                        <jsp:include page="ingresarDomicilioView.jsp"/>
		                      </dd>
		                      <dt id="cejaMediosContacto">Medios de Contacto</dt>
		                        <dd>
		                          <jsp:include page="ingresarMediosContactoView.jsp"/>
		                        </dd>
		                      </dl>
		                    </div>	
				</div>
				<!--Termina Div Principal 21-->
						<%} %>
						
					<!--Inicia Pestaña de Perciales -->
					<div id="tabsconsultaprincipal-22">	
						<!-- <center>
							<table id="gridDetalleFrmPrincipalDos" align="center"></table>
							<div id="pagerDos"></div>
							
						</center> -->
						<jsp:include page="solicitudesTabPericialesView.jsp"></jsp:include>
					
					</div>
				<!--Inicia Pestaña de Involucrados -->
				<div id="tabsconsultaprincipal-13">
						<!--Inicia Pestaña de hijos Involucrados -->
					<div id="tabschild" class="tabs-bottom">
						<ul>
							<li id="tabInvitado"><a href="#tabschild-7">Invitado</a></li>
							<li id="tabSolicitante"><a href="#tabschild-8">Solicitante Justicia Restaurativa</a></li>
							<li id="tabDenunciante"><a href="#tabschild-1">Denunciante</a></li>
							<li id="tabVictima"><a href="#tabschild-2">Víctima</a></li>
							<li id="tabProbableResponsable"><a href="#tabschild-3">Probables Responsable</a></li>
							<li id="tabTestigo"><a href="#tabschild-4">Testigo</a></li>
							<li id="tabTraductor"><a href="#tabschild-5">Traductor-Intérprete</a></li>
							<li id="tabQuienDetuvo"><a href="#tabschild-6">Quién Detuvo</a></li>
							<li id="tabCedulaIdentificacion" ><a href="#tabschild-9">Cedula de Identificación</a></li>
						</ul>
							<!--Inicia Pestaña de Denunciante -->
							<div id="tabschild-1">
							 <% if(idEtapa.longValue() == EtapasExpediente.TECNICA.getValorId().longValue()){ %>
								<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante"><input type="button" value="Ingresar Denunciante" class="btn_Generico"/></a></td>
									</tr>
								</table>
								<%} %>
							</div>
							<!--Termina Pestaña de Denunciante -->
					
							<!--Inicia Pestaña de Victima -->
							<div id="tabschild-2">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVictima">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima"><input type="button" value="Ingresar Víctima" class="btn_Generico"/></a></td>
									</tr>
								</table>
							</div>
							<!--Termina Pestaña de Victima -->
						
							<!--Inicia Pestaña de Probable Responsable -->
							<div id="tabschild-3">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblProbableResponsable">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable"><input type="button" value='<bean:message key="ingProbaleResponsable"/>' class="btn_Generico"/></a></td>
									</tr>
								</table>
							</div>
							<!--Termina Pestaña de Probable Responsable -->
						
							<!--Inicia Pestaña de Testigo -->
							<div id="tabschild-4">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblTestigo">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo"><input type="button" value="Ingresar Testigo" class="btn_Generico"/></a></td>
									</tr>
								</table>
							</div>
							<!--Termina Pestaña de Testigo -->
						
							<!--Inicia Pestaña de  Traductor -->
							<div id="tabschild-5">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblTraductor">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor"><input type="button" value="Ingresar Traductor - Interprete" class="btn_Generico"/></a></td>
									</tr>
								</table>
							</div>
							<!--Termina Pestaña de  Traductor -->
					
							<!--Inicia Pestaña de  Quien detuvo -->
							<div id="tabschild-6">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblQuienDetuvo">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="quienDetuvo"><input type="button" value="Ingresar Quien Detuvo" class="btn_Generico"/></a></td>
									</tr>
								</table>
							</div>
							<!--Termina Pestaña de  Quien detuvo -->
							<!--Inicia Pestaña de  Invitado -->
							<div id="tabschild-7" align="center">		
							 <% if(idEtapa.longValue() == EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId().longValue()){ %>
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
							                                  			BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpNombre1 title="Escribir nombre" readOnly maxLength=40 size=30 type=text>
							                					</TD>
							              					</TR>
							              					<TR>
							                					<TD width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
							                					<TD height=28 width="64%">
							                						<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
							                							  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoPaterno1 readOnly maxLength=40 size=30 type=text>
							                					</TD>
							              					</TR>
							              					<TR>
							                					<TD width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
								            					<TD height=29>
								            						<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
								                  						BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoMaterno1 readOnly maxLength=40 size=30 type=text>
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
								   			<!-- <TR vAlign=top> -->
							       			<TR vAlign=top>
							          		<TD colSpan=3>&nbsp;</TD>
							        		</TR>
							       		 </TBODY>
							    	</TABLE> 		
					    			  <!--Inicia Div de acordeon invitado -->
								      <div id="cedulaIdentificacion" >
									    <dl>
						                    <dt id="cejaDatosGenerales">Datos Generales</dt>
						                      <dd>	
						                      <jsp:include page="datosGeneralesView.jsp"></jsp:include>
											
						                      </dd>
						                    <dt id="cejaDomicilio">Domicilio</dt>
						                      <dd>
						                    	<jsp:include page="ingresarDomicilioView.jsp"></jsp:include>
						                      </dd>
						                    <dt id="cejaMediosContacto">Medios de Contacto</dt>
						                       <dd>
						                         <jsp:include page="ingresarMediosContactoView.jsp"/>
						                       </dd>
						                  </dl>
						               </div>	
						               <% } %>
						                 <!--Termina Div de acordeon invitado -->					
									</div>
							<!--Termina Pestaña de  Invitado -->
								
							<!--Inicia Pestaña de  Solicitante de Justicia -->							
							<div id="tabschild-8" >		
							 <% if(idEtapa.longValue() == EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId().longValue()){ %>	
								<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante"><input type="button" value="Ingresar Solicitante de Justicia Restaurativa" class="btn_Generico"/></a></td>
								</tr>
								</table>	
							<% } %>	
							<div>	
								<!--Termina Pestaña de  Solicitante de Justicia -->	
					
				</div>
				<!--Termina Pestaña de Involucrados -->
				
				</div>
		<!--Termina Pestaña de hijos Involucrados -->
		
		</div>
		<!-- Termina Tabs Principal del menu -->
		
		<form name="formaDocumento" action="<%= request.getContextPath() %>GenerarDocumentoDirecto.do" method="post">
			<input type="hidden" value="" id="formaId" >
			<input type="hidden" value="" id="documentoId">
			<input type="hidden" value="" id="tipoDocumento">
			<input type="hidden" name="numeroUnicoExpediente" />
		</form>
		
		<form name="formaArchivoD" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
			<input type="hidden" name="documentoId" />
		</form>
	
		<div id="divCambiarEtapa" style="display: none">
			<table align="center" cellpadding="0" cellspacing="0">
				  <tr>
				    <td>&nbsp;</td>
				    <td align="center">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td align="center"><strong>La etapa actual del expediente es:
				        <input type="text" id="etapaActual" disabled size="40" style="text-align: center;"/>
				    </strong></td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td valign="top">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td align="center"><strong>Nueva etapa:
				      <select name="cbxEtapaExpediente" id="cbxEtapaExpediente">
				      </select>
				    </strong></td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td align="center">&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
			</table>
		</div>		
	</div>	
</body>

<script type="text/javascript">

//quita el domicilio de notificaciones de la jsp ingresar domicilio
killDomicilioNotificaciones();		
$('#liDom').hide();	

</script>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" >
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />  	
		
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
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>

</html>	
