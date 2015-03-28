<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	

<STYLE type=text/css>
DD P {
	LINE-HEIGHT: 120%
}
#iRepLegalAccordionPane {
	BORDER-BOTTOM: #b5c9e8 0px solid; BORDER-LEFT: #b5c9e8 0px solid; PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; WIDTH: 1000px; PADDING-RIGHT: 1px; BACKGROUND: #fff; HEIGHT: 355px; BORDER-TOP: #b5c9e8 0px solid; BORDER-RIGHT: #b5c9e8 0px solid; PADDING-TOP: 1px
}
#iRepLegalAccordionPane DL {
	WIDTH: 1000px; HEIGHT: 355px
}
#iRepLegalAccordionPane DT {
	TEXT-ALIGN: right;
	PADDING-BOTTOM: 0px;
	LINE-HEIGHT: 44px;
	TEXT-TRANSFORM: uppercase;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 15px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) #fff no-repeat 0px 0px;
	LETTER-SPACING: 1px;
	HEIGHT: 46px;
	COLOR: #1c94c4;
	FONT-SIZE: 1.1em;
	FONT-WEIGHT: bold;
	PADDING-TOP: 0px
}
#iRepLegalAccordionPane DT.active {
	BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) #fff no-repeat 0px 0px; COLOR: #e78f08; CURSOR: pointer
}
#iRepLegalAccordionPane DT.hover {
	COLOR: #e78f08
}
#iRepLegalAccordionPane DT.hover.active {
	COLOR: #1c94c4
}
#iRepLegalAccordionPane DD {
	BORDER-BOTTOM: #dbe9ea 1px solid; BORDER-LEFT: 0px; PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom; BORDER-TOP: #dbe9ea 1px solid; MARGIN-RIGHT: 1px; BORDER-RIGHT: #dbe9ea 1px solid; PADDING-TOP: 1px
}
#iRepLegalAccordionPane .slide-number {
	COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 10px
}
#iRepLegalAccordionPane .active .slide-number {
	COLOR: #fff
}
#iRepLegalAccordionPane A {
	COLOR: #68889b
}
#iRepLegalAccordionPane DD IMG {
	MARGIN: 0px; FLOAT: right
}
#iRepLegalAccordionPane H2 {
	MARGIN-TOP: 10px; FONT-SIZE: 2.5em
}
#iRepLegalAccordionPane .more {
	DISPLAY: block; PADDING-TOP: 10px
}
body {
	background-image: url(<%= request.getContextPath()%>/images/back_gral.jpg);
	background-repeat: repeat-x;
}
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
}
</STYLE>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	
	var outerLayout, innerLayout;
	var numeroCausa =0;
	var numeroGeneralDeCaso =0;
	var idNumExpSeleccionado=0;
	
	/*
	*variable para controlar si las fechas introducidas por el usuario son validas
	*/
	validaFecha = false;

	/*
	*variable para controlar si el grid de consultar audiencias agendadas por dia
	*se carga por primera vez
	*/ 
	primeraVezCargaGridDia = true;

	/*
	*variable para controlar si el grid de consultar audiencias agendadas por fecha
	*se carga por primera vez
	*/ 
	primeraVezCargaGridFecha = true;	
	/*
	*variable para controlar los id´s del visor de detalle de audiencia
	*cuando se da click el audiencias -> del dia o audiencias -> por fecha
	*/		
	var idWindowVisorAudiencia = 1;

	/*
	*variable para controlar los id´s del visor de nueva solicitud
	*/
	var idWindowVisorAtnPublicoSol = 1;	
	
	function  reiniciar(){
		$.idleTimeout.options.onResume.call($.idleTimeout('#dialogBlok', 'div.ui-dialog-buttonpane button:first'));
		$('#password').val("");
		$('#scaptcha').val("");
		$('#imgcaptcha').hide().attr("src", "<%=request.getContextPath()%>/kaptcha.jpg?<%=new Date().getTime()%>").fadeIn(); 
		
	}
	
	function validaContra(){
		//alert("ejecuta");
		var pass=$('#password').val();
		var capcha=$('#scaptcha').val();
		$.ajax({
	   		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarAutenticidad.do',
	    		data: 'password='+pass+'&captcha='+capcha,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var op=$(xml).find('usuarioDTO').find('datosIncorrectos').text();
	    			if(op=="false"){
	    				alert("Los datos son incorrectos","Error");
	    			}else{
	    				$("#dialog-bloqueo").dialog( "close" );
	    				reiniciar();
	    			}
				}
	   	});
	}
	 function bloqueoSesion(){
	    	//crearTimer();
			$( "#dialog-bloqueo" ).dialog({
				resizable: false,
				height:400,
				width:400,
				modal: true,
				closeOnEscape: false,
				buttons: {
					"Aceptar": function() {
						//$( this ).dialog( "close" );
						//$( "#dialog:ui-dialog" ).dialog( "destroy" );
						//cambiarEstausAlarma("aceptar","0",idAlerta,"0");
						validaContra();
						
						
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						//$( "#dialog:ui-dialog" ).dialog( "destroy" );
						//cambiarEstausAlarma("cancelar","0",idAlerta,"0");
						document.location.href="<%= request.getContextPath()%>/Logout.do";
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}
	
	$(document).ready(function() {
		$("#dialogBlok").dialog({
			autoOpen: false,
			modal: true,
			width: 400,
			height: 200,
			closeOnEscape: false,
			draggable: false,
			resizable: false,
			buttons: {
				'¡Autenticarse!': function(){
					// fire whatever the configured onTimeout callback is.
					// using .call(this) keeps the default behavior of "this" being the warning
					// element (the dialog in this case) inside the callback.
					$(this).dialog('close');
					bloqueoSesion();

				}
			}
			
		});			
		//Codigo timer
		var $countdown = $("#dialog-countdown");

		// start the idle timer plugin
		$.idleTimeout('#dialogBlok', 'div.ui-dialog-buttonpane button:first', {
			idleAfter:'<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoBloqueoSesion()%>',
			pollingInterval: 2,
			//keepAliveURL: 'keepalive.php',
			serverResponseEquals: 'OK',
			onTimeout: function(){
				//window.location = "timeout.htm";
				$(this).dialog('close');
				//$(this).dialog( "destroy" ); .click();
				//buttonOpts.click();
				bloqueoSesion();
				
			},
			onIdle: function(){
					$(this).dialog("open");
				//bloqueoSesion();
			},
			onCountdown: function(counter){
				$countdown.html(counter); // update the counter
			},
			onResume: function(){
				
			}
		});	
//Fin	
		//obtenemos el tiempo de las alarmas y ponemos en marcha el timer		
		var tiempo='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoRevisionAlarmas()%>';
		setInterval(muestraAlerta, tiempo);

		outerLayout = $("body").layout( layoutSettings_Outer );

		//crea el acordeon
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});	
		//crea el arbol de audiencias	
		$("#seccion1treePJATP").treeview();
		//crea el arbol de solicitudes
		//$("#seccion2treePJATP").treeview();
		//recarga Grid con expediente seleccionado
		//$('#casosSolicitudesPJATP').click(recargaGridSolicitudesPJATP);
		//llama la funcion que crea la ventana de agenda
		$("#controlAgenda").click(creaAgenda);	
		//llama la funcion que crea la ventana de buscar expediente	
		$("#buscarExpediente").click(buscarExpediente);
		//llama la funcion que crea la ventana de buscar caso	
		$("#buscarCaso").click(buscarCaso);	
		//llama la funcion que crea la ventana de generar documento	
		$("#generarDocumento").click(generarDocumentoViwe);
		//boton que llama la funcion que crea la ventana para una nueva solicitud		
		$("#nuevaSolicitudPJATP").click(nuevaSolicitudPJATP);	
		//boton que llama la funcion que crea la ventana modal para una nueva solicitud de accion penal privada
		$("#btnNuevaSolAccPenalPrivadaPJATP").click(nuevaSolAccPenalPrivadaPJATP);
		
		$("#dialogoChat").dialog({ autoOpen: false, 
			modal: true, 
			title: 'Chat', 
			dialogClass: 'alert',
			modal: true,
			width: 500 ,
			maxWidth: 600,
			buttons: {"Cancelar":function() {
								$(this).dialog("close");
							}
						} 
		});	
		
		$( "#dialog-logout" ).dialog({
			autoOpen: false,
			resizable: false,
			//height:290,
			//width:300,
			modal: true,
			buttons: {
				"Aceptar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					document.location.href="<%= request.getContextPath()%>/Logout.do";
				},
				"Cancelar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
				}
			}
		});	
		
		//Carga el reloj
		muestraGadgets();
		//carga el arbol de casos
		//cargaCasosSolicitudesPJATP();
		cargaGridDiaPJATP();	
				
		/*******************************
		 ***  CUSTOM LAYOUT BUTTONS  ***
		 *******************************
		 *
		 * Add SPANs to the east/west panes for customer "close" and "pin" buttons
		 *
		 * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
		 * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
		 *
		 * CSS will size and position the spans, as well as set the background-images
		 */

		// BIND events to hard-coded buttons in the NORTH toolbar
		outerLayout.addToggleBtn( "#tbarBtnHeaderZise", "north" );
		// save selector strings to vars so we don't have to repeat it
		// must prefix paneClass with "body > " to target ONLY the outerLayout panes
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		var eastSelector = "body > .ui-layout-east"; // outer-east pane

		 // CREATE SPANs for pin-buttons - using a generic class as identifiers
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		// BIND events to pin-buttons to make them functional
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		outerLayout.addCloseBtn("#east-closer", "east");
		$('#test').weatherfeed(['MXDF0132']);
		createInnerLayout () ;
						
		}); // FIN onready

		
		/*
			*#######################
			* INNER LAYOUT SETTINGS
			*#######################
			*
			* These settings are set in 'list format' - no nested data-structures
			* Default settings are specified with just their name, like: fxName:"slide"
			* Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
			*/
			layoutSettings_Inner = {
				applyDefaultStyles:				false // basic styling for testing & demo purposes
			,	minSize:						50 // TESTING ONLY
			,	spacing_closed:					14
			,	north__spacing_closed:			8
			,	south__spacing_closed:			8
			,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
			,	south__togglerLength_closed:	-1
			,	fxName:							"slide" // do not confuse with "slidable" option!
			,	fxSpeed_open:					1000
			,	fxSpeed_close:					2500
			,	fxSettings_open:				{ easing: "easeInQuint" }
			,	fxSettings_close:				{ easing: "easeOutQuint" }
			,	north__fxName:					"none"
			,	south__fxName:					"drop"
			,	south__fxSpeed_open:			500
			,	south__fxSpeed_close:			1000
			//,	initClosed:						true
			,	center__minWidth:				200
			,	center__minHeight:				200
			};
			/*
			*#######################
			* OUTER LAYOUT SETTINGS
			*#######################
			*
			* This configuration illustrates how extensively the layout can be customized
			* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
			*
			* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
			* All default settings (applied to all panes) go inside the defaults:{} key
			* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
			*/
			var layoutSettings_Outer = {
				name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
				// options.defaults apply to ALL PANES - but overridden by pane-specific settings
			,	defaults: {
					size:					"auto"
				,	minSize:				50
				,	paneClass:				"pane" 		// default = 'ui-layout-pane'
				,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
				,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
				,	buttonClass:			"button"	// default = 'ui-layout-button'
				,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
				,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
				,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
				,	togglerLength_closed:	35			// "100%" OR -1 = full height
				,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
				,	togglerTip_open:		"Close This Pane"
				,	togglerTip_closed:		"Open This Pane"
				,	resizerTip:				"Resize This Pane"
				//	effect defaults - overridden on some panes
				,	fxName:					"slide"		// none, slide, drop, scale
				,	fxSpeed_open:			750
				,	fxSpeed_close:			1500
				,	fxSettings_open:		{ easing: "easeInQuint" }
				,	fxSettings_close:		{ easing: "easeOutQuint" }
			}
			,	north: {
					spacing_open:			1			// cosmetic spacing
				,	togglerLength_open:		0			// HIDE the toggler button
				,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
				,	resizable: 				false
				,	slidable:				false
				//	override default effect
				,	fxName:					"none"
				}
			,	south: {
					maxSize:				200
				,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
				,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
				,	initClosed:				false
				}
			,	west: {
					size:					250
				,	spacing_closed:			21			// wider space when closed
				,	togglerLength_closed:	21			// make toggler 'square' - 21x21
				,	togglerAlign_closed:	"top"		// align to top of resizer
				,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
				,	togglerTip_open:		"Close West Pane"
				,	togglerTip_closed:		"Open West Pane"
				,	resizerTip_open:		"Resize West Pane"
				,	slideTrigger_open:		"click" 	// default
				,	initClosed:				false
				//	add 'bounce' option to default 'slide' effect
				,	fxSettings_open:		{ easing: "" }
				,	west__onresize:		function () { $("#accordionmenuprincipal").accordion("resize"); }
				}
			,	east: {
					size:					250
				,	spacing_closed:			21			// wider space when closed
				,	togglerLength_closed:	21			// make toggler 'square' - 21x21
				,	togglerAlign_closed:	"top"		// align to top of resizer
				,	togglerLength_open:		0 			// NONE - using custom togglers INSIDE east-pane
				,	togglerTip_open:		"Close East Pane"
				,	togglerTip_closed:		"Open East Pane"
				,	resizerTip_open:		"Resize East Pane"
				,	slideTrigger_open:		"mouseover"
				,	initClosed:				false
				//	override default effect, speed, and settings
				,	fxName:					"drop"
				,	fxSpeed:				"normal"
				,	fxSettings:				{ easing: "" } // nullify default easing
				,	est__onresize:		function () { $("#accordionmenuderprincipal").accordion("resize"); }		
				}
			,	center: {
					paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
				,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes
				,	onresize_end:			function () { $("#gridAudienciaDelDiaPJATP").setGridWidth($("#mainContent").width() - 5, true);
														  $("#gridFechaPJATP").setGridWidth($("#mainContent").width() - 5, true);
														  $("#gridSolicitudesPJATP").setGridWidth($("#mainContent").width() - 5, true);
														}		
			}
		};

		/*
		*Funcion que obtiene el numero de causa del grid seleccionado si está visible
		*/	
		function obtenerNumeroDeCausaDeGrids(){

			var numeroDeCausa="";
			//Si el grid audiencias del día esta visible
			if( $('#gridAudienciaDelDiaPJATP').is(':visible') ){
				var row = jQuery("#gridAudienciaDelDiaPJATP").jqGrid('getGridParam','selrow');

				if (row) { 
					var ret = jQuery("#gridAudienciaDelDiaPJATP").jqGrid('getRowData',row);
					numeroDeCausa = ret.expediente;
				}
			}else{
				//Si el grid de busquedas por fecha esta visible
				if( $('#gridFechaPJATP').is(':visible') ){
					var row = jQuery("#gridFechaPJATP").jqGrid('getGridParam','selrow');

					if (row) { 
						var ret = jQuery("#gridFechaPJATP").jqGrid('getRowData',row);
						numeroDeCausa = ret.expediente;
					}
				}
			}
			return numeroDeCausa; 			
		}
			
		/*
		*Funcion que crea el visor de solicitudes para una nueva solicitud
		*/		
		function nuevaSolicitudPJATP() {

			var numeroDeCausa="";
			
			numeroDeCausa=obtenerNumeroDeCausaDeGrids();			
			idWindowVisorAtnPublicoSol++;
			$.newWindow({id:"iframewindowVisorAtnPublicoSolicitudes"+idWindowVisorAtnPublicoSol, statusBar: true, posx:255,posy:111,width:1000,height:400,title:"Atenci&oacute;n de Solicitudes", type:"iframe"});
	    	$.updateWindowContent("iframewindowVisorAtnPublicoSolicitudes"+idWindowVisorAtnPublicoSol,'<iframe src="<%= request.getContextPath() %>/crearNuevaSolicitud.do?numeroDeCausa='+numeroDeCausa+'" width="1000" height="400" />');
	    	$("#" +"iframewindowVisorAtnPublicoSolicitudes"+idWindowVisorAtnPublicoSol+ " .window-maximizeButton").click();
		}

		function cerrarVentanaNuevaSolicitud(){
			$.closeWindow('iframewindowVisorAtnPublicoSolicitudes'+idWindowVisorAtnPublicoSol);
		}

		/*
		*Funcion que carga la ventana modal para generar una nueva solicitid de accion penal privada
		*/
		function nuevaSolAccPenalPrivadaPJATP(){
			//Limpia los campos de la ventana modal
			limpiaCamposSolAccPenalPrivada();
			//Carga la fecha y hora actuales
			cargaFechaHoraActual();
			$("#divModalSolAccPenalPrivada").dialog("open");
			$("#divModalSolAccPenalPrivada").dialog({ autoOpen: true, 
		  		modal: true, 
		  		title: 'Solicitar Acción Penal Privada', 
		  		dialogClass: 'alert',
		  		position: [500,140],
		  		width: 380,
		  		height: 300,
		  		maxWidth: 1000,
		  		buttons:{"Aceptar":
			  				function() {
				  				if(validaDatosSolAccPenalPrivada()==true){
				  					enviarSolAccPenalPrivada();
				  					$(this).dialog("close");
						  		}
		  					},
		  				"Cancelar":function() {
			  				$(this).dialog("close");
		  					}
		  				}
		  	});			
		}

		/*
		*Valida que todos los campos esten llenos
		*/		
		function validaDatosSolAccPenalPrivada(){

			if($('#nombreSolAccPenalPrivada').val() == ""){
				alertDinamico("Ingrese el nombre del solicitante");
				return false;
			}
			if($('#apPatSolAccPenalPrivada').val() == ""){
				alertDinamico("Ingrese el apellido paterno del solicitante");
				return false;
			}
			//Puede que no tenga ap materno
			return true;
		}

		/*
		*Envia los datos de la solicitud de accion penal privada
		*/		
		function enviarSolAccPenalPrivada(){

			//obtenemos los parametros
			var parametrosSolAccPenalPrivada="";
 
			parametrosSolAccPenalPrivada += 'nombreSolAccPenalPrivada=' +  $('#nombreSolAccPenalPrivada').val();
			parametrosSolAccPenalPrivada += '&apPatSolAccPenalPrivada=' + $('#apPatSolAccPenalPrivada').val();
			parametrosSolAccPenalPrivada += '&apMatSolAccPenalPrivada=' + $('#apMatSolAccPenalPrivada').val();
			
			$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/generarTurnoAtPublicoPJ.do',
	    		data: parametrosSolAccPenalPrivada,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			alertDinamico("Solicitud enviada");	    				
	    		}
	    	});
		}

		/*
		*Limpia los campos de la ventana modal
		*/
		function limpiaCamposSolAccPenalPrivada(){
			
			$('#nombreSolAccPenalPrivada').val('');
			$('#apPatSolAccPenalPrivada').val('');
			$('#apMatSolAccPenalPrivada').val('');
			$('#fechaSolAccPenalPrivada').val('');
			$('#horaSolAccPenalPrivada').val('');	
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
				$('#fechaSolAccPenalPrivada').val(+"0"+diaActual+"/"+"0"+ mesActual+"/"+anioActual);
			}
			if(diaActual.length < 2 && mesActual.length >= 2 ){
				$('#fechaSolAccPenalPrivada').val(+"0"+diaActual+"/"+ mesActual+"/"+anioActual);
			}
			if(diaActual.length >= 2 && mesActual.length < 2 ){
				$('#fechaSolAccPenalPrivada').val(diaActual+"/"+"0"+ mesActual+"/"+anioActual);
			}
			if(diaActual.length >= 2 && mesActual.length >= 2 ){
				$('#fechaSolAccPenalPrivada').val(diaActual+"/"+mesActual+"/"+anioActual);
			}

			var horaActual='<%=(java.util.Calendar.getInstance().get(Calendar.HOUR_OF_DAY))%>';
			var minutoActual='<%=(java.util.Calendar.getInstance().get(Calendar.MINUTE))%>';
			if(minutoActual.length < 2){
				$('#horaSolAccPenalPrivada').val(horaActual+":0"+minutoActual);
			}
			else{
				$('#horaSolAccPenalPrivada').val(horaActual+":"+minutoActual);
			}
		}

		
		/*
		*Funcion que crea el visor de solicitudes para ver una solicitud
		*/
		function dblClickRowBandejaSolicitudes(rowID){
			
			$.newWindow({id:"iframewindowVisorAtnPublicoSolicitudes", statusBar: true, posx:255,posy:111,width:640,height:400,title:"Atenci&oacute;n de Solicitudes", type:"iframe"});
		    $.updateWindowContent("iframewindowVisorAtnPublicoSolicitudes",'<iframe src="<%=request.getContextPath()%>/visorAtnAlPublicoSolicitudes.do?idEvento=' + rowID +'" width="1140" height="400" />'); 
		}
		/*
		*Funcion que crea el visor de  audiencia
		*/		
		function dblClickRowBandejaAudiencias(idRow){
		
			idWindowVisorAudiencia++;
			$.newWindow({id:"iframewindowVisorAtnPublicoAudiencias"+ idWindowVisorAudiencia, statusBar: true, posx:255,posy:111,width:1024,height:400,title:"Atenci&oacute;n de Audiencias", type:"iframe"});
		    $.updateWindowContent("iframewindowVisorAtnPublicoAudiencias"+ idWindowVisorAudiencia,'<iframe src="<%=request.getContextPath()%>/visorAtnAlPublicoAudiencias.do?idEvento=' + idRow +'" width="1024" height="400" />'); 
		}
	
		
		/*
		*Funcion que crea el visor buscar Expediente
		*/	
		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do?rolUsuario=atencionPublico" width="653" height="400" />');		
		}
		/*
		*Funcion que crea el visor buscar Caso
		*/
		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
		}
		/*
		*Funcion que crea el visor agenda de barra de gadgets
		*/		
		function creaAgenda() {
			$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
		    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
		    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();
			}
		/*
		*Funcion que crea el visor de generar documento
		*/	
		
		function generarDocumentoViwe() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
		    $("#" +"iframewindowGenerarDocumento"+ " .window-maximizeButton").click();  		
		}
		/*
		*Funcion que agrega los casos dinamicamente 
		*/
		function cargaCasosSolicitudesPJATP(){
	    	var branches = "";
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/busquedaInicialCausasPJ.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: true,
	    		success: function(xml){
	    			
	    			branchesCasos="<ul>";
					$(xml).find('expedienteDTO').each(function(){
						var numeroExpedienteId = $(this).find('numeroExpedienteId').text();
						var numeroExpediente = $(this).find('numeroExpediente').text();
						branchesCasos +="<li class='opened' id='" + numeroExpedienteId +"CASO' ><span class='folder' id='span_" + 
						numeroExpedienteId + "' onclick='recargaGridSolicitudesPJATP(" + numeroExpedienteId + ")'>" + numeroExpediente + "</span></li>";
						
	    			});
					branchesCasos+="</ul>"
					$("#casosSolicitudesPJATP").append(branchesCasos);
					
					$("#seccion2treePJATP").treeview();
					
	    		}
	    		
	    	});
	    }

		//variable que indica si el caso esta abierto
		var casoAbierto = Array();

		/*
		*Funcion que agrega los expedientes dinamicamente 
		*al arbol de casos, de acuerdo al caso seleccionado
		*/
		function recargaGridSolicitudesPJATP (numeroExpedienteId) {
										
			if (casoAbierto[numeroExpedienteId ] != true) {
				$.ajax({
		    		type: 'POST',
		    		url: '<%= request.getContextPath()%>/busquedaNumeroExpedientesPorCausaPJ.do',
		    		data: 'numeroExpedienteId=' + numeroExpedienteId,
		    		dataType: 'xml',
		    		async: true,
		    		success: function(xml){
		    			
		    			branches="<ul>";
		    			$(xml).find('expedienteDTO').each(function(){
		    				
		    				branches += "<li  id='" + $(this).find('numeroExpedienteId').text() + 
		    						"EXP' onclick='verEventosPorExpedientePJATP(" + 
		    								$(this).find('numeroExpedienteId').text() +
		    								")'><span class='file' id='span_" + 
		    								$(this).find('numeroExpedienteId').text() + "'>" + 
		    								$(this).find('numeroExpediente').text() + "</span></li>";
		    				
			    		});
		    			branches+="</ul>";
		    			$("#" + numeroExpedienteId + "CASO").append(branches);
		    			$("#seccion2treePJATP").treeview();
		    		}		    
		    		
		    	});
				casoAbierto[numeroExpedienteId] = true;
			}
			
			
		}
		
		/*
		*Funcion que recarga el grid de acuerdo a la consulta de un expediente
		*es decir, consulta el historico de eventos del expedienta seleccionado
		*/
		function verEventosPorExpedientePJATP(numeroExpedienteId){

			idNumExpSeleccionado = numeroExpedienteId;
			var numExpediente = $('#span_'+ numeroExpedienteId).html();
			numeroCausa = numExpediente;
			
								
					jQuery("#gridSolicitudesPJATP").jqGrid({ 
					url:'<%=request.getContextPath()%>/consultaEventosPorExpedientePJATP.do?numeroExpedienteId='+numeroExpedienteId +'', 
					datatype: "xml", 
					colNames:['N&uacute;mero de Causa', 'Solicitud','Fecha Solicitud','Hora Solicitud','Institución Solicitante','Solicitante','Estado'], 
					colModel:[ 	{name:'expediente',index:'expediente', width:45, align:"center"}, 
								{name:'solicitud',index:'solicitud', width:35, align:"center"}, 
								{name:'fechaSolicitud',index:'fechaSolicitud', width:30, align:"center"},
								{name:'horaSolicitud',index:'horaSolicitud', width:30, align:"center"}, 
								{name:'institucionSolicitante',index:'institucionSolicitante', width:45, align:"center"},
								{name:'solicitante',index:'solicitante', width:45, align:"center"},
								{name:'estado',index:'estado', width:20, align:"center"}
							],
					pager: jQuery('#paginadorSolicitudesPJATP'),
					rowNum:20,
					rowList:[25,50,100],
					width:924,
					autowidth: false,
					sortname: 'detalle',
					viewrecords: true,
					sortorder: "desc",
					ondblClickRow: function(rowid) {
						dblClickRowBandejaSolicitudes(rowid);
					}
				}).navGrid('#paginadorSolicitudesPJATP',{edit:false,add:false,del:false});

				$("#gview_gridSolicitudesPJATP .ui-jqgrid-bdiv").css('height', '450px');

								  $("#divGridSolicitudesPJATP").show();
								  $("#divGridAudienciasPJATP").hide();
								  $("#divGridFechasPJATP").hide();
							     jQuery("#gridSolicitudesPJATP").jqGrid('updateGrid');
			  
		}		    

		/*
		*Funcion que carga el grid para consultar las audiencias del dia
		*/
		function cargaGridDiaPJATP(){

			if(primeraVezCargaGridDia == true){
				
				jQuery("#gridAudienciaDelDiaPJATP").jqGrid({ 
					url:'<%= request.getContextPath()%>/consultarAudienciaDelDiaPJENS.do',
					datatype: "xml", 
					colNames:['N&uacute;mero de Caso','N&uacute;mero de Causa','Car&aacute;cter', 'Tipo de Audiencia','Fecha de Audiencia','Hora de Audiencia','Sala'], 
					colModel:[ 	{name:'numeroCaso',index:'numeroCaso', width:70},
								{name:'expediente',index:'expediente', width:70}, 
								{name:'caracter',index:'caracter', width:25}, 
								{name:'tipoAudiencia',index:'tipoAudiencia', width:40},
								{name:'fechaAudiencia',index:'fechaAudiencia', width:50}, 
								{name:'horaAudiencia',index:'horaAudiencia', width:30},
								{name:'sala',index:'sala', width:50}
							],
					pager: jQuery('#paginadorAudienciaDelDiaPJATP'),
					rowNum:10,
					rowList:[10,20,30],
					width:924,
					autowidth: false,
					sortname: 'detalle',
					viewrecords: true,
					sortorder: "desc",
					ondblClickRow: function(idRow) {
						dblClickRowBandejaAudiencias(idRow);
										}
				}).navGrid('#paginadorAudienciaDelDiaPJATP',{edit:false,add:false,del:false});	

				$("#gview_gridAudienciaDelDiaPJATP .ui-jqgrid-bdiv").css('height', '480px');
				//Cambiamos la bandera
				primeraVezCargaGridDia = false;
					
			}
			else{
				
				jQuery("#gridAudienciaDelDiaPJATP").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarAudienciaDelDiaPJENS.do',datatype: "xml" });
				$("#gridAudienciaDelDiaPJATP").trigger("reloadGrid");
			}
			
			$('#divGridSolicitudesPJATP').hide();
			$('#divGridFechasPJATP').hide();
			$('#divGridAudienciasPJATP').show();

		}			

		/*
		*Funcion que carga el grid de audiencias usando como filtro la fecha de la audiencia
		*/
		function cargaGridFechaPJATP(){
		 
		var fechaInicio = $('#fechaInicio').val();
		var fechaFin = $("#fechaFin").val();				  
		  
		if (validaFecha==true){
		
			if(primeraVezCargaGridFecha == true){
		
				jQuery("#gridFechaPJATP").jqGrid(
						{ url:'<%= request.getContextPath() %>/buscarAudienciasPorFecha.do', 						
							datatype: "xml", 
							mtype: 'POST',						
							postData: {fechaInicio: function()  {return fechaInicio;},
							           fechaFin: function()		{return fechaFin;}																										
							},
							colNames:['N&uacute;mero de Caso','N&uacute;mero de Causa','Carácter', 'Tipo de Audiencia','Fecha de Audiencia','Hora de Audiencia','Sala'], 
							colModel:[ 	{name:'numeroCaso',index:'numeroCaso', width:70},
										{name:'expediente',index:'expediente', width:70}, 
										{name:'caracter',index:'caracter', width:25}, 
										{name:'tipoAudiencia',index:'tipoAudiencia', width:40},
										{name:'fechaAudiencia',index:'fechaAudiencia', width:50}, 
										{name:'horaAudiencia',index:'horaAudiencia', width:30},
										{name:'sala',index:'sala', width:50}
									],
								autowidth: false,
								width:924, 
								pager: jQuery('#paginadorFechaPJATP'), 
								sortname: 'id', 
								rownumbers: false,
								gridview: false, 
								viewrecords: true, 
								sortorder: "desc", 
								height: "60%",
								//caption:"Resultado de la B&uacute;squeda",
								ondblClickRow: function(rowid) {
									dblClickRowBandejaAudiencias(rowid);
													} 
						}).navGrid('#paginadorFechaPJATP',{edit:false,add:false,del:false});
		
						$("#gview_gridFechaPJATP .ui-jqgrid-bdiv").css('height', '480px');
						//Cambiamos la bandera
						primeraVezCargaGridFecha = false;			
			}
			else{
				jQuery("#gridFechaPJATP").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/buscarAudienciasPorFecha.do?fechaInicio='+fechaInicio+'&fechaFin='+fechaFin+'',datatype: "xml" });
					$("#gridFechaPJATP").trigger("reloadGrid");			
				}												  
				 
				 
				$('#divGridAudienciasPJATP').hide();
				$('#divGridSolicitudesPJATP').hide();
				$('#busquedaFecha').hide();			
				$('#divGridFechasPJATP').show();			 			 
			}			  
					   
		}			

		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposFecha() {

			if ($('#fechaInicio').val() == '' || $('#fechaFin').val() == '') {
				alertDinamico("Debes ingresar la fecha de inicio y fin de la consulta");
				validaFecha = false;
			} else {

				var fechaIniVal = $('#fechaInicio').val();
				var fechaFinVal = $('#fechaFin').val();

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
					alertDinamico("La fecha final debe de ser mayor a la fecha inicial");
				}
				
			}	
		}

		/*
		*Funcion que abre la ventana modal para mostrar los campos para ingresar
		*la fecha de inicio y la fecha de fin 
		*/
		function modalFecha(){
			
			$('#fechaInicio').val('');
			$('#fechaFin').val('');
		/**
		* Carga los escuchadores de eventos para los combo box's para 
		* el domicilio 
		*/
		$("#fechaInicio").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		$("#fechaFin").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
			
			//abre la ventana de detalle de la persona
			$("#busquedaFecha").dialog("open");
			$("#busquedaFecha").dialog({ autoOpen: true, 
		  		modal: true, 
		  		title: 'Buscar por Fecha', 
		  		dialogClass: 'alert',
		  		position: [255,140],
		  		width: 380,
		  		height: 260,
		  		maxWidth: 1000,
		  		buttons:{"Aceptar":function() {
		  			
		  			validaCamposFecha();
		  			cargaGridFechaPJATP();

		  			if(validaFecha==true){
		  				$(this).dialog("close");
			  			}		  			
		  			},
		  				"Cancelar":function() {
		  				$(this).dialog("close");
		  			}
		  		}
		  	});
					
		}

		//Funcion para abrir la ventana de chat
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}
	
	/******************************************************    FUNCIONES PARA LAS ALARMAS      ***************************************************/
	function muestraAlerta(){
		var op="";
		var idAlerta="";

		$.ajax({
   		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarAlarmas.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			$(xml).find('alertaDTO').each(function(){
    				op=$(this).find('esAplaza').text();
    				idAlerta=$(this).find('alertaId').text();
    				var nombre=$(this).find('nombre').text();
    				$("#mensajeAlarma").html(nombre);
    				
        			llamaraCambia(op,idAlerta);
				});
    			//alert($(xml).find('alertaDTO').find('nombre').text());
    			//alert('la primera op:'+op);
    			
    			//alert('la xml op:'+$(xml).find('alertaDTO').find('esAplaza').text());
    			//alert('la segunda op:'+op);
   		}
   	});
		
		

	}

	function cambiarEstausAlarma(estatus,tiempo,id,unidad){
		$.ajax({
	   		type: 'POST',
	    		url: '<%=request.getContextPath()%>/actualizarAlarma.do?idAlerta='+id+'&estatus='+estatus+'&tiempo='+tiempo+'&unidad='+unidad,
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			//alert($(xml).find('alertaDTO').find('nombre').text());
	    			if(estatus=="posponer")
	    			{
	    				alertDinamico("Alarma pospuesta.");
	    			}
	    			else if(estatus=="cancelar")
	    			{
	    				alertDinamico("Alarma cancelada");
	    			}
	    			else
	    			{
	    				alertDinamico("Alarma aceptada.");
	    			}
	   		}
		});
	}

	function llamaraCambia(op,idAlerta){
		//alert('la segunda op:'+op);
		if(op!="false"){		
			$( "#dialog-alarm" ).dialog({
				resizable: false,
				height:150,
				width:300,
				modal: true,
				buttons: {
					"Aceptar": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						cambiarEstausAlarma("aceptar","0",idAlerta,"0");
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						cambiarEstausAlarma("cancelar","0",idAlerta,"0");
					},
					"Posponer": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						nuevoPoppupAlarma(idAlerta);
						
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}else if(op=="false"){
			$( "#dialog-alarm" ).dialog({
				resizable: false,
				height:150,
				width:300,
				modal: true,
				buttons: {
					"Aceptar": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						cambiarEstausAlarma("aceptar","0",idAlerta,"0");
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						cambiarEstausAlarma("cancelar","0",idAlerta,"0");
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}
	}


	function nuevoPoppupAlarma(idAlerta)
	{
		$( "#dialog-alarmPos" ).dialog({
			resizable: false,
			height:200,
			width:500,
			modal: true,
			buttons: {
				"Aceptar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					var unidadTiempo=$( "#cbxTiempo" ).val();
					var tiempoAplazar=$( "#idTiempotex" ).val();
					cambiarEstausAlarma("posponer",tiempoAplazar,idAlerta,unidadTiempo);
				},
				"Cancelar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					//cambiarEstausAlarma("cancelar","0",idAlerta);
				}
			}
		});
		$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
			
	}
	/******************************************************  FIN  FUNCIONES PARA LAS ALARMAS      ***************************************************/
		/*
	 *Funcion para consultar los roles extras de cada usuario y
	 * construlle el arbol dinamico de los tipos de rol en el menu derecho
	 */
	function consultarTiposRol()
	{
		//limpiamos el menu de los tipos de solicitud
		$("#tableRolMenu").empty();
		//lanzamos la consulta del tipo de solicitudes
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultaMenuRol.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('RolDTO').each(function(){
					var rolnuevo=$(this).find("nombreRol").text();
					var rolDesc=$(this).find("descripcionRol").text();
					var trTabla = "<tr>";
					trTabla = trTabla + "<td><span><img src='<%=request.getContextPath()%>/resources/css/check.png' width='16' height='16' />"+
					 					"<a  onclick=\"cargaRolNuevo('"+rolnuevo+"');\">" + rolDesc +
					 					"</a></span></td>";
					trTabla = trTabla + "</tr>";
					
					
					$('#tableRolMenu').append(trTabla);
				});
			}
			
		});
	}

	function cargaRolNuevo(rolNuevo){
		///rolRedirec
		//alert(rolNuevo);
		document.frmRol2.rolname.value = rolNuevo;
		document.frmRol2.submit();

	}
	</script>	
</head>

<body>
<div class="ui-layout-west">

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
			<h3><a href="#" id="opcBandejaAudiencias"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Audiencias</a></h3>
			<div>
				<ul id="seccion1treePJATP" class="filetree">
					<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="cargaGridDiaPJATP()">Del Dia</a></span></li>
					<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="modalFecha()">Por Fecha</a></span></li>
					
				</ul>	
			</div>
<!--			<h3><a href="#" id="opcBandejaSolicitudes"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes</a></h3>-->
<!--			<div>-->
<!--				<ul id="seccion2treePJATP" class="filetree">-->
<!--					<li class="opened" id="casosSolicitudesPJATP"><span class="folder">Causas</span>-->
<!--						 Aqui se agregan los casos y expedientes dinamicamente -->
<!--					</li>-->
<!--				</ul>	-->
<!--			</div>			-->
		</div>
	</div>
	
</div>

<div class="ui-layout-east">
	<div class="header">Bienvenido</div>
	<div class="content">
		<div id="accordionmenuderprincipal">
			<h4><a href="#">Datos Personales</a></h4>
			<div align="center">			
				<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
			</div>
			<!-- 
			<h4><a href="#">Calendario</a></h4>
			<div>
				<center>
				<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
				</center>
			</div>
			 -->
			<h6><a href="#">Agenda</a></h6>
			<div>
				<center>
					<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
				</center>
			</div>
			<!--  <h6><a href="#" id="">Consultar Leyes y C&oacute;digos</a></h6>
			<div>
			<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" bordercolorlight="#FFFFFF" style="cursor:pointer">
		      <tr>
		        <td width="100%"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Leyes</td>
		      </tr>
		      <tr>
		        <td>&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Codigos</td>
		      </tr>
		      <tr>
		        <td id="">&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Manuales</td>
		      </tr>
		      </table>
			</div>-->
			<!--h4>
				<a href="#">Clima</a>
			</h4>
			<div align="left">
				<div align="left" id="test"></div>
			</div>
			<h4>
				<a href="#">Chat</a>
			</h4>
			<div align="center">
				
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>
					<!--iframe src="http://gaby1:5280/web/jwchat/index.html" frameborder="0" width="200" height="200" scrolling="no"></iframe  -->
			<!--/div-->
			<h4><a href="#" onclick="consultarTiposRol();">Facultades</a></h4>
				<div>
					<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0"  style="cursor:pointer" id="tableRolMenu">
					</table>
					<form name="frmRol2" action="<%= request.getContextPath() %>/rolRedirec.do" method="post">
						<input type="hidden" name="rolname" />
					</form>
				</div>
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div -->
</div>


	<div class="ui-layout-north">
		<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ATENCION AL PUBLICO</TD>
					    <TD width=126 align=left valign="top"><!--SPAN></SPAN--><img id="logoPagina" src="<%=request.getContextPath()%>/resources/images/sis_penal.jpg" width="300px"></TD>
					    <TD width=272 align=center valign="top">&nbsp;</TD>
					    <TD width=28 align=middle>&nbsp;</TD><td width="150" align="center"><img src="<%=request.getContextPath()%>/resources/images/ejecutivo.png" width="100px"></td>
					    <TD width=380 align="right" valign="middle">
					      <table width="362" border="0" cellspacing="0" cellpadding="0">
					        <tr>
					          <td width="165"><table width="141" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					              <td width="53" align="right" class="txt_menu_top">&nbsp;</td>
					              <td width="157">&nbsp;</td>
					            </tr>
					          </table></td>
					          <td width="103"><table width="89" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					            	<td width="107" class="txt_menu_top"><!--Cerrar sesi&oacute;n--></td>
					            	<td width="42" class="txt_menu_top"></td>
					              </tr>
					          </table>
					            <label for="textfield"></label></td>
					          <td width="204"><table width="89" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					              <td width="47"><!--Ayuda--></td>
					              <td width="42"></td>
					            </tr>
					          </table></td>
					        </tr>
					      </table>
					      <table width="363" border="0" cellspacing="0" cellpadding="0">
					        <tr>
					          <td width="363" align="right" valign="middle"><TABLE border=0 cellSpacing=0 cellPadding=0 width="300" height="100%">
					            <TBODY>
					              <TR>          
					              <TR vAlign=top>
					                <TD width=128 align=right valign="middle">&nbsp;</TD>					           
					                <TD width="150" align="right" valign="middle">
										<a href="#" title="Salir" onclick='$("#dialog-logout").dialog( "open" );'><img src="<%=request.getContextPath()%>/resources/images/cerrar.jpg" width="26" height="26" border="0"></a>
										<a href="#" title="Ayuda"><img src="<%=request.getContextPath()%>/resources/images/Help.png" width="26" height="26" border="0"></a>
										<IMG alt="Icono reloj" src="<%=request.getContextPath()%>/resources/images/clock.png" width=26 height=25>
									</TD>
									<TD width=90 align="right" valign="middle"><DIV id=liveclock style="visibility:hidden;"></DIV></TD>
					              </TR>
					            </TBODY>
					          </TABLE></td>
					        </tr>
					  </table>
					  </TD>
					  </TR>
				  </TBODY>
			  </TABLE>
		</div>
	<ul class="toolbar">
		<div id="menu_head">
			<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			<!-- <li id="tbarBtnAgendaAudiencias" onmousedown="ocultaMuestraGridsAlertas('2');"><span></span>Agenda de Audiencias</li>
			<li id="tbarBtnSolicitudes" onmousedown="ocultaMuestraGridsAlertas('1');"><span></span>Bandeja de Solicitudes</li>-->
			<li id="nuevaSolicitudPJATP"><span></span>Nueva Solicitud&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="15" height="16"--></li>
			<li id="btnNuevaSolAccPenalPrivadaPJATP"><span></span>Solicitar Acción Penal Privada&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_errorinfo.png" width="15" height="16"--></li>
			<!--<li id="tbarBtnGenerarCausa"><span></span>Generar Causa</li>-->
			
		</div>
		
		<div id="menu_config">
			<li id="buscarCaso"><span></span>Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
			<li id="buscarExpediente"><span></span>Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
			<li id="generarDocumento"><span></span>Generar Documento&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="15" height="16"--></li>
			<!--<li><span></span>Adjuntar Documento</li>-->
			<!--<li><span></span>Turnar Documento</li>-->
			<li id="verde">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
		</div>
	</ul>
</div>


<div class="ui-layout-south">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pleca_bottom">
	    <tr>
	    <td height="15">&nbsp;</td>
	  </tr>
	</table>
	<div id="pie" align="center" style="BACKGROUND-COLOR: #e7eaeb; BACKGROUND-POSITION: center top; COLOR: #58595b">
		<div id="footer" style="PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; WIDTH: 720px; PADDING-RIGHT: 5px; PADDING-TOP: 5px">
			Direcci&oacute;n de la Instituci&oacute;n
		</div>
	</div>

</div>


<div id="mainContent">
	<div class="ui-layout-center">
		 <div class="ui-layout-content">
				<div class="ui-layout-north">
					
					<div id="divGridAudienciasPJATP">
						<table id="gridAudienciaDelDiaPJATP"></table>
						<div id="paginadorAudienciaDelDiaPJATP"></div>
					</div>
					
					<div id="divGridFechasPJATP">
						<table id="gridFechaPJATP"></table>
						<div id="paginadorFechaPJATP"></div>
					</div>
					<div id="divGridSolicitudesPJATP">
						<table id="gridSolicitudesPJATP"></table>
						<div id="paginadorSolicitudesPJATP"></div>
												
					</div>
					
				</div>
		</div>
	</div>
</div>

<!--Div para mostrar la ventana modal para elegir la fecha de consulta para audiencias-->
<div id="busquedaFecha" style="display: none">
	<table cellspacing="0" cellpadding="0" >
		<tr>
			<td width="153">&nbsp;</td>
			<td width="153">&nbsp;</td>
		</tr>
		<tr>
		  <td colspan="2" align="center"><strong>Fecha Inicio:</strong><input type="text" id="fechaInicio" size="20" />		  
		  </td>
	    </tr>
		<tr>
		  <td align="center">&nbsp;</td>
		  <td align="center">&nbsp;</td>
  		</tr>
		<tr>
		  <td colspan="2" align="center"><strong>Fecha Fin:&nbsp;&nbsp;</strong>&nbsp;
	      <input type="text" id="fechaFin" size="20" /></td>
  		</tr>
		<tr>
		  <td align="center">&nbsp;</td>
		  <td align="center">&nbsp;</td>
  		</tr>
	</table>
</div>
<!--Termina div fechas-->

<!--Comienza div para la ventana modal de solicitar accion penal privada-->
<div id="divModalSolAccPenalPrivada" style="display: none">
	<table width="350" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td colspan="2" align="center"><strong>Datos del Solicitante</strong></td>
	  </tr>
	  <tr>
	    <td width="161" align="right"><strong>Nombre:</strong></td>
	    <td width="189">
	    	<input type="text" id="nombreSolAccPenalPrivada" style="width:150px;" onkeypress="return soloLetrasDos(event,this.id);"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right"><strong>Apellido paterno:</strong></td>
	    <td>
	    	<input type="text" id="apPatSolAccPenalPrivada" style="width:150px;" onkeypress="return soloLetrasDos(event,this.id);"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right"><strong>Apellido Materno:</strong></td>
	    <td>
	    	<input type="text" id="apMatSolAccPenalPrivada" style="width:150px;" onkeypress="return soloLetrasDos(event,this.id);"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td align="right"><strong>Fecha de Solicitud:</strong></td>
	    <td>
	    	<input type="text" id="fechaSolAccPenalPrivada"	style="width: 150px; border: 0; background: #DDD;" readonly="readonly" />
	    </td>
	  </tr>
	  <tr>
	    <td align="right"><strong>Hora de Solicitud:</strong></td>
	    <td>
	    	<input type="text" id="horaSolAccPenalPrivada"	style="width: 150px; border: 0; background: #DDD;" readonly="readonly" />
	    </td>
	  </tr>
	</table>
</div>
<!--Termina div fechas-->

	<div id="dialog-logout" title="Logout">
		<p align="center">
			<span id="logout">¿Desea cerrar su sesi&oacute;n?</span>
		</p>
	</div>

	<!-- dialogos para las alarmas -->
	<div id="dialog-alarm" title="Alarma ">
		<p align="center">
			<span id="mensajeAlarma">mensajeConfigurable</span>
		</p>
	</div>
			<!-- dialogos para Bloqueo de pantalla-->
	<div id="dialog-bloqueo" title="Bloqueo Sesi&oacute;n"  style="display: none;">
		<p align="center">
			<table border="0">
				<tr>
					<td colspan="2">La sesi&oacute;n se a bloqueado introduce tu contraseña para desbloquear.</td>
					
				</tr>
				<tr>
					<td align="right"><label style="color:#4A5C68">Contraseña:</label></td>
					<td><input type="password" name="password" id="password" value="" maxlength="15" size="20"></td>
				</tr>
				<tr id="captchaJPG" >
	            	<td align="right">
	                	<label style="color:#4A5C68">Captcha:</label>
                    </td>
	                <td>
	                	<img id="imgcaptcha" src="<%=request.getContextPath()%>/kaptcha.jpg">
	                </td>
	            </tr>
	            <tr id="captchaTXT" >
	            	<td align="right">
	                	<label style="color:#4A5C68">Captcha:</label>
	             	</td>
	                <td>
	                   	<input type="text" id="scaptcha" name="scaptcha" value="" maxlength="15" size="20">
	                   	<input type="hidden" name="captcha" value='<%= request.getAttribute("captcha")%>'>
	                </td>
	            </tr>
			</table>
		</p>
	</div>
	<div id="dialogBlok" title="Su sesi&oacute;n est&aacute; a punto de caducar!">
			<p>
				<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
				La sesi&oacute;n se cerrara en <span id="dialog-countdown" style="font-weight:bold"></span> segundos.
			</p>

			<p>¿Desea continuar con la sesi&oacute;n?</p>
	</div>
	<div id="dialog-alarmPos" title="Alarma ">
		<p align="center">
			<span id="mensaje">Tiempo deseado para aplazar la alerta</span><br/>
			<span id="tiempo"><input type="text" size="5" maxlength="2" id="idTiempotex" onKeyPress="return solonumeros(event);"/></span>
			<span id="cbxTiempoSpan">
				<select id="cbxTiempo">
					<option value="1">Minutos</option>
					<option value="2">Horas</option>
				</select>
			</span>
		</p>
	</div>
	<!-- FIN dialogos para las alarmas -->

	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center"><span id="divAlertTexto"></span></td>
			</tr>
		</table>
	</div>

</body>
<script type="text/javascript">
	$( "#dialog-alarm" ).dialog();
	$( "#dialog-alarmPos" ).dialog();
	$( "#dialog-alarm" ).dialog( "destroy" );
	$( "#dialog-alarmPos" ).dialog( "destroy" );	
</script>
</html>