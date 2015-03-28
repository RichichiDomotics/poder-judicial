<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.EstatusMandamiento"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.EstatusMandamiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--COMIENZA CSS DEL DOCUMENTO-->
	<!--css para ventanas-->
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
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
/**Variables para la ceja de Bandeja de Solicitudes en Audiencia******/
	
	//variable para controlar el grid de audiencias del dia
	var primeraVezGridSolProcesoHistorico = true;
	//Variable para controlar el id de las ventanas
	var idWindowVisorAudienciaPJENC=1;

	var primeraGridExpedientesDocumentoPJATP = true;
	
	//variable para controlar el cargado del gird de accion penal privada
	var cargaGridSolAccPenalPrivada = true;

/**Variables para la ceja de Mandamientos Judiciales******/
	
	//variable para controlar el grid de audiencias por fecha
	var primeraVezGridMandamientosJudiciales = true;	
	//Variable para controlar el id de las ventanas
	var idWindowVisorMedidasCautelaresPJENC=1;

/**Variables para la ceja de Estado Expediente******/
	var primeraVezGridEstadoExpediente = true;	
	var numCausa; 
	var numCaso;
	
	//variables para setear las fechas y horas maximas
	var fechaServidor="";
	var fechaMax="";
	
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


/********************************************************COMIENZA ON READY**************************************************************************************/
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
		//seteamos la fecha del servidor
		fechaServidor= consultaFechaHoraMaximaServer();
		fechaMax=getFechaMaximaServerHechos(fechaServidor);
		
		outerLayout = $("body").layout( layoutSettings_Outer );

		//Plug in date
		$("#buscaporfechaIni").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});


		//Plug in date
		$("#buscaporfechaFin").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		
		//generamos los calendarios
		$("#fechaInicio").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '-111:+0',
			maxDate: fechaMax,
			onSelect: function(date) {
				//setter fecha minima al segunda calendario
				$( "#fechaFin" ).datepicker( "option", "minDate", date );
			},
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		
		$("#fechaFin").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '-111:+0',
			maxDate: fechaMax, 
			minDate: fechaMax,
			onSelect: function(date) {
				//revisaLongitudFechas();
			},
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		$("#fechaInicio,#fechaFin").attr("disabled","disabled");

		$("#seccion34treePJEA").treeview();
//**Funcionalidad Comun*****/	
		//crea el acordeon
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		//crea el arbol de audiencias
		$("#seccion1treePJENC").treeview();
		//crea el arbol de mandamientos judiciales
		$("#seccion2treePJENC").treeview();
		//crea el sub arbol de audiencias
		$("#seccion3treePJENC").treeview();
		//crea el arbol de medidas cautelares
		$("#seccion4treePJENC").treeview();
		//crea el arbol de estado del expediente
		$("#seccion5treePJENC").treeview();
		//crea el arbol de años para sentencia
		$("#seccion6treePJENC,#seccion34treePJEA").treeview();
		$("#seccionAccPenaltreePJENC").treeview();
		//agreaga el evento para crear la agenda
		$("#controlAgenda").click(creaAgenda);
		
//**Funcionalidad para ceja de Bandeja de sol por audiencia****/
		//Carga el grid de solicitudes en proceso, por default
		cargaGridSolProcesoHistoricoPJENC('<%=EstatusSolicitud.EN_PROCESO.getValorId()%>,<%=EstatusSolicitud.ABIERTA.getValorId()%>');

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

//**Funcionalidad para ceja de Estado del Expediente*****/
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
		createInnerLayout ();
		
		//Carga el reloj
		muestraGadgets();
					
	});
/********************************************************TERMINA ON READY**************************************************************************************/
	
	

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
		,	minWidth:				200
		,	minHeight:				200
		,	onresize_end:			function () { 

				$("#gridAudienciasPJENC").setGridWidth($("#mainContent").width() - 5, true);
				$("#gridMandamientosJudPJENC").setGridWidth($("#mainContent").width() - 5, true);
				$("#gridEstadoExpedientePJENC").setGridWidth($("#mainContent").width() - 5, true);
				$("#gridSolicitudesAccPenalPrivadaPJENC").setGridWidth($("#mainContent").width() - 5, true);
			}		
		}
	};
		
//**************************************************************FUNCIONALIDAD ACORDEON BANDEJA DE SOLICITUDES POR AUDIENCIA****************************************/
	/*
	*Funcion que carga el grid, por default con las audiencias del dia
	*/
	function cargaGridSolProcesoHistoricoPJENC(estados){

		if(primeraVezGridSolProcesoHistorico == true){

			jQuery("#gridAudienciasPJENC").jqGrid({
				url:'<%= request.getContextPath()%>/consultarAudienciasBandejaInicialEncargadoCausa.do?estadoSolicitud='+estados,
				datatype: "xml", 
				colNames:['N&uacute;mero de Caso','N&uacute;mero de Causa','Carácter','Tipo de Audiencia','Fecha de Audiencia','Hora de Audiencia','Sala'], 
				colModel:[ 	{name:'numeroCaso',index:'numeroCaso', width:130, align:'center'},
							{name:'expediente',index:'expediente', width:110, align:'center'}, 
							{name:'caracter',index:'caracter', width:50, align:'center'}, 
							{name:'tipoAudiencia',index:'tipoAudiencia', width:60, align:'center'}, 
							{name:'fechaAudiencia',index:'fechaAudiencia', width:80, align:'center'},	
							{name:'horaAudiencia',index:'horaAudiencia', width:80, align:'center'},											
							{name:'sala',index:'sala', width:80, align:'center'}
																		
						],
				pager: jQuery('#pagerAudienciasPJENC'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: false,
				width:767,
				sortname: 'expediente',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					mostrarVisorAudienciaPJENC(rowid);
						}
			}).navGrid('#pagerAudienciasPJENC',{edit:false,add:false,del:false});
			$("#gview_gridAudienciasPJENC .ui-jqgrid-bdiv").css('height', '450px');
			primeraVezGridSolProcesoHistorico=false;
			muestraOcultaGrids("procesoHistorico");
		}
		else{
			jQuery("#gridAudienciasPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarAudienciasBandejaInicialEncargadoCausa.do?estadoSolicitud='+estados,datatype: "xml" });
			$("#gridAudienciasPJENC").trigger("reloadGrid");
			muestraOcultaGrids("procesoHistorico");
		}
	}

	/*
	*Funcion que abre el visor de audiencias
	*/
	function mostrarVisorAudienciaPJENC(rowID){

		idWindowVisorAudienciaPJENC++;
		$.newWindow({id:"iframewindowVisorAudiencia"+idWindowVisorAudienciaPJENC, statusBar: true, posx:255,posy:111,width:1140,height:450,title:"Atencion de solicitudes por audiencia", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorAudiencia"+idWindowVisorAudienciaPJENC,'<iframe src="<%=request.getContextPath()%>/visorAudienciaPJENC.do?idAudiencia=' + rowID +'" width="1140" height="450" />'); 
    	 $("#" +"iframewindowVisorAudiencia"+idWindowVisorAudienciaPJENC+ " .window-maximizeButton").click();
    	}

//**************************************************FUNCIONALIDAD ACORDEON MANDAMIENTOS JUDICIALES**************************************************************/

	/*
	*Funcion que carga el grid de consulta por fechas
	*/
	numeroCausaGlobalGrid  = "";
	function cargaGridMandamientosJudiciales(numeroCausa,estatusParam){
		numeroCausaGlobalGrid = numeroCausa;							
		
		if(primeraVezGridMandamientosJudiciales == true){
			//alert(numeroCausa+","+estatus+","+fechaMax);				
			  jQuery("#gridMandamientosJudPJENC").jqGrid({ 
					url:'<%= request.getContextPath() %>/consultarMandamientosJudicialesPorCausa.do?numeroExpediente='+numeroCausa+'&estatusMandamiento='+estatusParam+'&fecha='+fechaMax+'', 					
					datatype: "xml",
					colNames:['Fecha Creacíon','Tipo de Mandamiento Judicial','Estado'], 
					colModel:[ 	{name:'fechaCreacion',index:'fechaCreacion',width:130, align:'center'},
								{name:'tipoMandamiento',index:'tipoMandamiento',width:110, align:'center'}, 
								{name:'estado',index:'estado', width:50, align:'center'}, 
							],
					autowidth: false,
					width:767, 
					pager: jQuery('#pagerGridMandamientosJudPJENC'),
					rowNum:10,
					rowList:[25,50,100],
					sortname: 'fechaCreacion',
					sortorder: "desc", 
					viewrecords: true,
					//caption:"NUMERO DE CAUSA"+numeroCausaGlobalGrid,
					ondblClickRow: function(rowid) {
						if(rowid.split(",")[1].toLowerCase() == "en proceso"){
							abreModalPdfMandamientoJudicial(rowid.split(",")[0],rowid.split(",")[1]);
						}
						
					} 
				}).navGrid('#pagerGridMandamientosJudPJENC',{edit:false,add:false,del:false}); 
				$("#gview_gridMandamientosJudPJENC .ui-jqgrid-bdiv").css('height', '450px');
				primeraVezGridMandamientosJudiciales = false;
				//Resize del grid
				$("#cargaGridMandamientosJudiciales").setGridWidth($("#mainContent").width() - 5, true);
				muestraOcultaGrids("mandamientosJudiciales");
		}
		else{
			jQuery("#gridMandamientosJudPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/consultarMandamientosJudicialesPorCausa.do?numeroExpediente='+numeroCausa+'&estatusMandamiento='+estatusParam+'&fecha='+fechaMax+'',datatype: "xml" });
			$("#gridMandamientosJudPJENC").trigger("reloadGrid");
			muestraOcultaGrids("mandamientosJudiciales");				  
		}
	}
	
	
	function cargaGridMandamientosJudicialesPorFecha(fechaInicio,fechaFin,estatusParam){
		//alert(estatusParam +"_"+fechaInicio+"_"+fechaFin);
		if(primeraVezGridMandamientosJudiciales == true){
			  jQuery("#gridMandamientosJudPJENC").jqGrid({ 
					url:'<%=request.getContextPath()%>/consultarMandamientosJudicialesPorPeriodo.do?estatusMandamiento='+estatusParam+'&fechaInicio='+fechaInicio+'&fechaFin='+fechaFin+'',
					datatype: "xml",
					colNames:['Fecha Creacíon','Tipo de Mandamiento Judicial','Estado'], 
					colModel:[ 	{name:'fechaCreacion',index:'fechaCreacion',width:130, align:'center'},
								{name:'tipoMandamiento',index:'tipoMandamiento',width:110, align:'center'}, 
								{name:'estado',index:'estado', width:50, align:'center'}, 
							],
					autowidth: false,
					width:767, 
					pager: jQuery('#pagerGridMandamientosJudPJENC'),
					rowNum:10,
					rowList:[25,50,100],
					sortname: 'fechaCreacion',
					sortorder: "desc", 
					viewrecords: true,
					//caption:"NUMERO DE CAUSA"+numeroCausaGlobalGrid,
					ondblClickRow: function(rowid) {
						if(rowid.split(",")[1].toLowerCase() == "en proceso"){
							abreModalPdfMandamientoJudicial(rowid.split(",")[0],rowid.split(",")[1]);
						}
						
					} 
				}).navGrid('#pagerGridMandamientosJudPJENC',{edit:false,add:false,del:false}); 
				$("#gview_gridMandamientosJudPJENC .ui-jqgrid-bdiv").css('height', '450px');
				primeraVezGridMandamientosJudiciales = false;
				//Resize del grid
				$("#cargaGridMandamientosJudiciales").setGridWidth($("#mainContent").width() - 5, true);
				muestraOcultaGrids("mandamientosJudiciales");
		}
		else{
			jQuery("#gridMandamientosJudPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/consultarMandamientosJudicialesPorPeriodo.do?fechaInicio='+fechaInicio+'&fechaFin='+fechaFin+'&estatusMandamiento='+estatusParam+'',datatype: "xml" });
			$("#gridMandamientosJudPJENC").trigger("reloadGrid");
			muestraOcultaGrids("mandamientosJudiciales");				  
		}
	}


	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*/
	function abreModalCausaMandamientosJudiciales(estatus){
		//alert("estatus_por_dia:: "+estatus);
		$("#datoCausaMandamientosJud").val("");
		$("#divCausaMandamientosJud").dialog("open");
	  	$("#divCausaMandamientosJud").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Mandamientos judiciales por n&uacute;mero de causa', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 350,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Realizar Busqueda":function() {
		  		var numeroCausa = $("#datoCausaMandamientosJud").val();
		  		cargaGridMandamientosJudiciales(numeroCausa,estatus);
		  		$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});
	}
	
	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*/
	function abreModalCausaMandamientosJudicialesPorFecha(estatus){
		//alert("estatus_por_rango:: "+estatus);
		$("#busquedaFecha").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Mandamientos judiciales por fecha', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 350,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Realizar Búsqueda":function() {
		  		if(($("#fechaInicio").val().length!=0) & ($("#fechaFin").val().length!=0) )
		  		{
		  			//llamamos la busqueda de los mandamientos por rango
		  			cargaGridMandamientosJudicialesPorFecha($("#fechaInicio").val(),$("#fechaFin").val(),estatus);	
		  			$(this).dialog("close");	
		  			$("#fechaInicio,#fechaFin").val("");
		  		}
		  		else
		  		{
		  			alertDinamico("Debe colocar un periodo de búsqueda válido.");
		  		}
		  		
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});
	}

	/*
	*Funcion que abre la ventana modal para cambier el estado del mandamiento judicial
	*/
	idMandamientoGlobal = "";
	function abreModalPdfMandamientoJudicial(idMandamiento,estatusActual){
		//colocar el estado actual
		idMandamientoGlobal = idMandamiento;
		$("#estadoActualMandamientoJud").val(estatusActual);
		$("#divPdfMandamientoJudicial").dialog("open");
	  	$("#divPdfMandamientoJudicial").dialog({ autoOpen: true, 
			modal: true, 
		  	title: '¿Cambiar el estado del mandamiento judicial?', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 450,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Aceptar":function() {
				var estado = $('#estadoMandamientoJudPJENC option:selected').val();
				actualizarEstadoMandamiento(idMandamientoGlobal,estado);		  		
		  		$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});	  	
	}	
	/**
	* Manda a actualizar el estado de un mandamiento judicial y actualiza el grid de mandamientos
	*
	*/
	function actualizarEstadoMandamiento(idMandamiento,estadoNuevo){
		
		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/actualizarMandamientoJudicial.do?estatusMandamiento='+estadoNuevo+'&mandamientoId='+idMandamiento,
    		data: '',
    		dataType: 'xml',
    		async: true,
    		success: function(xml){
    			
    			cargaGridMandamientosJudiciales(numeroCausaGlobalGrid,estadoNuevo); //FIXME - revisar que siga funcionando
    		}
    		
    	});
		
		
	}
	
//**************************************************************FUNCIONALIDAD ACORDEON ADMINISTRAR MEDIDAS CAUTELARES*********************************************/
	
	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*/
	function abreModalCausa(){
		
		$("#datoCausa").val("");
		$("#divCausa").dialog("open");
	  	$("#divCausa").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Administrar medidas cautelares por n&uacute;mero de causa', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 350,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Realizar Busqueda":function() {
		  		var numeroCausa = $("#datoCausa").val();
		  		mostrarVentanaInvolucradosCausa(numeroCausa);
		  		$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});
	}

	/*
	*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarVentanaInvolucradosCausa(numeroCausa){

		idWindowVisorMedidasCautelaresPJENC++;
		$.newWindow({id:"iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC, statusBar: true, posx:255,posy:111,width:970,height:480,title:"Visor de Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC,'<iframe src="<%=request.getContextPath()%>/visorMedidaCautelar.do?numeroCausa=' + numeroCausa +'" width="970" height="480" />'); 
	}

//**************************************************************FUNCIONALIDAD ACORDEON ESTADO DEL EXPEDIENTE*******************************************************/
	
	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*para consultar el estado de un expediente 
	*/
	function abreModalCausaEstadoExpediente(){
		
		$("#datoCausaExpediente").val("");
		$("#divCausaExpediente").dialog("open");
	  	$("#divCausaExpediente").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Estado del Expediente', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 350,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Realizar Busqueda":function() {
		  		var numeroCausa = $("#datoCausaExpediente").val();
		  		cargaGridEstadoExpediente(numeroCausa);
		  		
		  		$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});
	}


	/*
	*Funcion que carga el grid con el estado del expediente
	*/
	function cargaGridEstadoExpediente(numeroCausa){
													  
		if(primeraVezGridEstadoExpediente == true){
			  jQuery("#gridEstadoExpedientePJENC").jqGrid({ 
					url:'<%= request.getContextPath() %>/buscarInvolucradosAudienciaPorCausa.do?numCausa='+numeroCausa,datatype: "xml",
								
					datatype: "xml",

					colNames:['Nombre Probable Responsable','Nombre Víctima','Delito','Calidad Actual','Nueva Calidad'], 
						colModel:[ 	{name:'nombreProbResp',index:'nombreProbResp',width:250, align:'center'},
									{name:'victima',index:'victima', width:250, align:'center'}, 
									{name:'delito',index:'delito',width:150, align:'center'},								
									{name:'CalidadActual',index:'calidadActual', width:150, align:'center'},
					           		{name:'NuevaCalidad',index:'NuevaCalidad', width:200, align:'center'},
							],
						autowidth: false,
						width:767, 
						pager: jQuery('#pagerGridEstadoExpedientePJENC'),
						rowNum:10,
						rowList:[25,50,100],
						sortname: 'nombreProResp',
						sortorder: "desc", 
						viewrecords: true,
						//caption:"NUMERO DE CAUSA:"+numeroCausa,
						toolbar: [true,"top"],
						
						ondblClickRow: function(rowid) {
							mostrarbuscarInvolucradosAudienciaPorCausa(rowid);
						} 
				
				}).navGrid('#pagerGridEstadoExpedientePJENC',{edit:false,add:false,del:false}); 
			  jQuery("#gridEstadoExpedientePJENC").jqGrid('navGrid','#pagerGridEstadoExpedientePJENC',{edit:false,add:false,del:false});
			  $("#gview_gridEstadoExpedientePJENC .ui-jqgrid-bdiv").css('height', '450px');
			  $("#t_gridEstadoExpedientePJENC").append("<input type='button' value='Guardar' style='height:20px;font-size:-3'/>");
			  $("input","#t_gridEstadoExpedientePJENC").click(function(){
		
			  	actualizarSituacionJuridica();
			  });
			  				
				$("#gview_gridEstadoExpedientePJENC .ui-jqgrid-bdiv").css('height', '480px');
				primeraVezGridEstadoExpediente = false;
				//Resize del grid de estado expediente
				$("#gridEstadoExpedientePJENC").setGridWidth($("#mainContent").width() - 5, true);
				muestraOcultaGrids("estadoExpediente");				
		}
		else{
			
			jQuery("#gridEstadoExpedientePJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/buscarInvolucradosAudienciaPorCausa.do?numCausa='+numeroCausa,datatype: "xml" });
			$("#gridEstadoExpedientePJENC").trigger("reloadGrid");
			muestraOcultaGrids("estadoExpediente");				  
		}
	}

	/*
	*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarbuscarInvolucradosAudienciaPorCausa(numeroCausa){

		idWindowVisorMedidasCautelaresPJENC++;
		$.newWindow({id:"iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC, statusBar: true, posx:255,posy:111,width:970,height:480,title:"Visor de Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC,'<iframe src="<%=request.getContextPath()%>/visorbuscarInvolucradosAudienciaPorCausa.do?numeroCausa=' + numeroCausa +'" width="970" height="480" />'); 
	}
//**************************************************************FUNCIONALIDAD ACORDEON SENTENCIAS*******************************************************/

	/*
	*Funcion que agrega los anios dinamicamente 
	*/
	function cargaHistoricoSentenciasPJENC(){
    	
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/BusquedaInicialCaso.do',
    		data: '',
    		dataType: 'xml',
    		async: true,
    		success: function(xml){
    			var branches = "";
				$(xml).find('anio').each(function(){
					var anioId = $(this).find('anioId').text();
					var anio = $(this).find('anio').text();
    				branches ="<ul><li class='closed' id='" + anioId+ "' onclick='agregaMesesPJENC(" + anioId + ")'><span class='folder'>" + anio + "</span><ul></ul></li></ul>";
					var sentenciasPJENC = $(branches).appendTo("#historicoSentencias");
					$("#seccion6treePJEA").treeview({
    					add: sentenciasPJENC
    				});
    			});
    		}
    		
    	});
    }


	var primeraVezGridSentencias = true 
	
	/*
	*Funcion que carga el grid con el historico del estado del expediente
	*/
	function cargaGridSentencias(estados){
													  
		if(primeraVezGridSentencias == true){
						
			  jQuery("#gridSentenciasEnProcesoPJENC").jqGrid({ 
					url:'<%= request.getContextPath()%>/consultarSentenciasPorEstatus.do?estadoSentencia='+estados+'',
					data:'',
					datatype: "xml",
					colNames:['Imputado','Número de Caso','Número de Causa','Carpeta de Ejecución','Fecha Creación'], 
					colModel:[ 	{name:'imputado',index:'imputado',width:250, align:'center'},
								{name:'numeroCaso',index:'numeroCaso',width:150, align:'center'}, 
								{name:'numeroCausa',index:'numeroCausa', width:150, align:'center'}, 
								{name:'carpetaEjecucion',index:'audiencia',width:50, align:'center'},
								{name:'fechaCreacion',index:'fecha',width:100, align:'center'},
							],
						autowidth: false,
						width:767, 
						pager: jQuery('#pagerGridSentenciasEnProcesoPJENC'),
						rowNum:10,
						rowList:[25,50,100],
						sortname: 'fechaDocumento',
						sortorder: "desc", 
						viewrecords: true
				}).navGrid('#pagerGridSentenciasEnProcesoPJENC',{edit:false,add:false,del:false}); 
				$("#gview_gridSentenciasEnProcesoPJENC .ui-jqgrid-bdiv").css('height', '450px');

				primeraVezGridSentencias = false;

				//Resize del grid
				$("#gridSentenciasEnProcesoPJENC").setGridWidth($("#mainContent").width() - 5, true);
				muestraOcultaGrids("sentencias");
		}
		else{
			jQuery("#gridSentenciasEnProcesoPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSentenciasPorEstatus.do?estadoSentencia='+estados+'',datatype: "xml" });
			$("#gridSentenciasEnProcesoPJENC").trigger("reloadGrid");
			muestraOcultaGrids("sentencias");				  
		}
			   
	}

//**************************************************************************FUNCIONALIDAD COMUN ****************************************************************//

	function muestraOcultaGrids(grid){

		
		if( grid == "accPenalPrivada"){
			$('#divGridAudienciasProcesoHistorico').hide();
			$('#divGridMandamientosJudPJENC').hide();
			$('#divGridEstadoExpedientePJENC').hide();
			$('#divGridSentenciasEnProcesoPJENC').hide();
			$('#divGridExpedientesDocumentoPJATP').hide();
			$('#divGridSolicitudesAccPenalPrivadaPJENC').show();
		}
		

		if( grid == "procesoHistorico"){
			$('#divGridSolicitudesAccPenalPrivadaPJENC').hide();
			$('#divGridMandamientosJudPJENC').hide();
			$('#divGridEstadoExpedientePJENC').hide();
			$('#divGridSentenciasEnProcesoPJENC').hide();
			$('#divGridAudienciasProcesoHistorico').show();
			$('#divGridExpedientesDocumentoPJATP').hide();			
		}
		
		if( grid == "mandamientosJudiciales"){
			$('#divGridSolicitudesAccPenalPrivadaPJENC').hide();
			$('#divGridAudienciasProcesoHistorico').hide();
			$('#divGridEstadoExpedientePJENC').hide();
			$('#divGridSentenciasEnProcesoPJENC').hide();
			$('#divGridMandamientosJudPJENC').show();
			$('#divGridExpedientesDocumentoPJATP').hide();
		}

		if(grid == "estadoExpediente"){
			$('#divGridSolicitudesAccPenalPrivadaPJENC').hide();
			$('#divGridMandamientosJudPJENC').hide();
			$('#divGridAudienciasProcesoHistorico').hide();
			$('#divGridSentenciasEnProcesoPJENC').hide();
			$('#divGridEstadoExpedientePJENC').show();
			$('#divGridExpedientesDocumentoPJATP').hide();
		}

		if(grid == "sentencias"){
			$('#divGridSolicitudesAccPenalPrivadaPJENC').hide();
			$('#divGridMandamientosJudPJENC').hide();
			$('#divGridEstadoExpedientePJENC').hide();
			$('#divGridAudienciasProcesoHistorico').hide();
			$('#divGridExpedientesDocumentoPJATP').hide();
			$('#divGridSentenciasEnProcesoPJENC').show();
		}
		
		if(grid == "expedientesDocumentoPJATP"){
			$('#divGridSolicitudesAccPenalPrivadaPJENC').hide();
			$('#divGridMandamientosJudPJENC').hide();
			$('#divGridEstadoExpedientePJENC').hide();
			$('#divGridAudienciasProcesoHistorico').hide();
			$('#divGridSentenciasEnProcesoPJENC').hide();
			$('#divGridExpedientesDocumentoPJATP').show();
		}
		
	}


	/*
	*Funcion que llama a la funcionalidad para crear la agenda 
	*/
	
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();
		}


function visorLeyesCodigos() {
		
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:255,posy:111,width:809,height:468,title:"Leyes y C&oacute;digos", type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/detalleLeyesyCodigos.do" width="809" height="468" />');
	    		
	}

	/*
	*Funcion que llama a la funcionalidad para generar un visualizador de imagen  $('#imageViewer').click(generaVisorGraficaView);
	*/
	function generaVisorGraficaView() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:63,posy:111,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="400" />');
	    		
	}	


	function actualizarSituacionJuridica(){

		var parametros = delitosPersonaAOrdenar();

		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/actualizarSituacionJuridica.do',
			data: 'parametros='+parametros,
			dataType: 'xml',
			success: function(xml){			
			}
		});
	}

	/*
	*Funcion para obtener los ids del 
	*DELITO
	*INVOLUCRADO
	*NUEVA CALIDAD
	*/
	function delitosPersonaAOrdenar() {
		var solicitudesAOrdenar = "";
		var i=0;
		var arrayIds = new Array();
		
		arrayIds = jQuery("#gridEstadoExpedientePJENC").getDataIDs();
		$('select[id^="delito_"]').each(function(){
			id = $(this).attr("id");
			solicitudesAOrdenar += arrayIds[i]+"_"+id.split("_")[1]+ "_" + $(this).val()+ ",";
			i++;
		});
		return solicitudesAOrdenar; 
	}


	/*
	*Funcion que realiza las validaciones para la carga el grid de consulta por fechas y expedientes
	*/
	function controlCargaGridExpedientes(){

		var fechaIni = $("#buscaporfechaIni").val();
		var fechaFin = $("#buscaporfechaFin").val();
		var numeroExpedienteId =$("#buscapornumexp").val();

		if(numeroExpedienteId == null || numeroExpedienteId == "" || numeroExpedienteId == "undefined"){

			var validacion = validaCamposFecha(fechaIni,fechaFin);
			
			if(validacion == true){
				cargaGridExpedientes(fechaIni, fechaFin, numeroExpedienteId);
			}			
		}else{
			
			cargaGridExpedientes(fechaIni, fechaFin, numeroExpedienteId);
		}
	}

	
	/*
	*Funcion que carga el grid de consulta por fechas y numero de expediente
	*/
	function cargaGridExpedientes(fechaIni, fechaFin, numeroExpedienteId){
												  
		if(primeraGridExpedientesDocumentoPJATP == true){

			jQuery("#gridExpedientesDocumentoPJATP").jqGrid({ 
				url:'<%= request.getContextPath() %>/buscarExpedientePorNumeroDeExpedienteParaDocumentos.do?fechaIni='+fechaIni+'&fechaFin='+fechaFin+'&numeroExpedienteId='+numeroExpedienteId+'', 
				datatype: "xml",
				mtype: 'POST', 
				colNames:['<bean:message key="numeroDeCausa" />',
							'<bean:message key="numeroDeCaso" />',
							'<bean:message key="fechaDeCreacion" />',
							'<bean:message key="fechaDeModificacion" />',
							'<bean:message key="documentos" />',
				], 
				colModel:[ 	
							{name:'expediente',index:'numeroCaso',width:70,align:'center'},
							{name:'numeroCaso',index:'expediente',width:70,align:'center'}, 
							{name:'caracter',index:'caracter',width:25,align:'center'},
							//se oculta porque no se muestran datos debido a la bitacora 
							{name:'tipoAudiencia',index:'tipoAudiencia', width:40,hidden:true},
							{name:'documento',index:'documento', width:40,hidden:true},
						],
				pager: jQuery('#paginadorExpedientesDocumentoPJATP'),
				rowNum:10,
				rowList:[10,20,30],
				width:830,
				height:410,
				viewrecords: true,
				ondblClickRow: function(rowid) {
					var ret2 = jQuery("#gridExpedientesDocumentoPJATP").jqGrid('getRowData',rowid);
					numCausa= ret2.expediente;
					numCaso = ret2.numeroCaso;
					dblClickRowvisorDocumentosExpediente(rowid);
				},
				sortorder: "desc"
			});
			$("#gview_GridExpedientesDocumentoPJATP .ui-jqgrid-bdiv").css('height', '450px');
			//cambia la bandera a false para que solo ejecute el reload
		  	primeraGridExpedientesDocumentoPJATP = false;
		}
		else{
			jQuery("#gridExpedientesDocumentoPJATP").jqGrid('setGridParam',{url:'<%= request.getContextPath() %>/buscarExpedientePorNumeroDeExpedienteParaDocumentos.do?fechaIni='+fechaIni+'&fechaFin='+fechaFin+'&numeroExpedienteId='+numeroExpedienteId+'',datatype:"xml" });
			$("#gridExpedientesDocumentoPJATP").trigger("reloadGrid");				  
		}
		
		muestraOcultaGrids("expedientesDocumentoPJATP");
	}

	
	/*
	*Funcion para desplegar el poppoup de tipo de busqueda
	*/
	function  poppopTipoBusqueda(tipo){
	
		var titulo="";
		
		$("#buscapornumexp").val("");  
		$("#buscaporfechaIni").val("");
		$("#buscaporfechaFin").val("");
		
		if(tipo=="expediente"){
			$("#tiposBusquedaExpediente").css("display","block");
			$("#tiposBusquedafecha").css("display","none");
			titulo="Buscar por número de causa";
		}else{
			$("#tiposBusquedaExpediente").css("display","none");
			$("#tiposBusquedafecha").css("display","block");
			titulo="Buscar causa por fecha";
		}
		  
		$( "#tiposBusquedaExpedienteid" ).dialog({
			title:titulo, 
			autoOpen: true,
			resizable: false,
			modal: true,
			height:'auto',
			width:'auto',
			buttons: {
				"Aceptar": function() {
					controlCargaGridExpedientes();
					$( this ).dialog( "close" );
				},
				"Cancelar": function() {
					$( this ).dialog( "close" );
					
				}
			}
		});	
	}

	/*
	*Funcion que crea el visor de  audiencia
	*/		
	function dblClickRowvisorDocumentosExpediente(idRow){ 
		$.newWindow({id:"iframewindowVisorEncargadoAdmonAudiencias", statusBar: true, posx:255,posy:111,width:1024,height:400,title:"Numero de Causa:"+numCausa+" "+"Numero de Caso:"+numCaso, type:"iframe"});
		$.updateWindowContent("iframewindowVisorEncargadoAdmonAudiencias",'<iframe src="<%=request.getContextPath()%>/visorDocumentos.do?numExpedienteId=' + idRow +'" width="1024" height="400" />'); 
	}


//********************************************************************COMIENZA SECCI0N PARA SENTENCIA************************************************************/


	//TODO Se tiene que agregar este visor para que se abra cuando se de click como el grid 
	/*
	* Función que genera un Mandamiento y después abre la pantalla de mandamiento judicial
	*
	*/
	function generarMandamiento(){

		resolutivo = jQuery("#gridSentenciasEnProcesoPJENC").jqGrid('getGridParam','selrow');
		
		//limpiaDivTipoMandamiento();
		//agregarCalendarios();

		$("#divTipoMandamiento").dialog("open");
	  	$("#divTipoMandamiento").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Tipo de Mandamiento', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 450,
		  	height: 350,
		  	maxWidth: 450,
		  	maxHeigth:350,
		  	buttons:{"Aceptar":function() {
			  	
		  		//if(validarDatosMandamiento() == false || validaCamposFechaSentencia() == false){
				//	return;
			  	//}else{
			  	//	enviarMandamiento();	
			  	//	$(this).dialog("close");
				//}
		  	  },
				"Cancelar" : function() {	
					$(this).dialog("close");
				}
		  	}
		});	 
	}

//******************************************************************ACCION PENAL PRIVADA***********************************************************************************/

	
 	//variable para verificar que grid fue el seleccionado
 	var seleccion; 
 	
 	/*
 	* Carga el grid con las solicitudes de accion penal privada
 	*
 	*/
	function cargaGirdSolicitudesAccPenalPrivadaPJENC(estado){
		
 		var status;
 		
 		if(estado == "porAtender"){
 			status='<%=EstatusTurno.ESPERA.getValorId()%>';
 			seleccion="porAtender";
 		}
 			
 		else if(estado =="atendidas"){
 			status='<%=EstatusTurno.ATENDIDO.getValorId()%>';
 			seleccion="atendidas";
 		}
 		
		if(cargaGridSolAccPenalPrivada == true){
			
			jQuery("#gridSolicitudesAccPenalPrivadaPJENC").jqGrid({
				url:'<%=request.getContextPath()%>/consultarTurnosAccPenalPrivada.do?estado='+status+'', 
				datatype: "xml", 
				colNames:['Fecha de la Solicitud','Hora de la Solicitud','Nombre del Solicitante'], 
				colModel:[ 
							{name:'FechaSolicitud',index:'fechaSolicitud',align:"center", width:100},
							{name:'HoraSolicitud',index:'horaSolicitud',align:"center", width:100}, 
							{name:'NombreSolicitante',index:'nombreSolicitante',align:"center", width:100}, 																	
						],
				pager: jQuery('#pagerGridSolicitudesAccPenalPrivadaPJENC'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: false,
				width:760,
				sortname: 'fechaSolicitud',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					
					var turnoId = rowid.split("+")[0];
					var numeroExpRelacion = rowid.split("+")[1];
					nuevaDenunciaUI(turnoId,numeroExpRelacion);
					}
				}).navGrid('#pagerGridSolicitudesAccPenalPrivadaPJENC',{edit:false,add:false,del:false});
			
			$("#gview_gridSolicitudesAccPenalPrivadaPJENC .ui-jqgrid-bdiv").css('height', '355px');
			cargaGridSolAccPenalPrivada = false;
			muestraOcultaGrids("accPenalPrivada");
		}
		else{
			jQuery("#gridSolicitudesAccPenalPrivadaPJENC").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarTurnosAccPenalPrivada.do?estado='+status+'',datatype: "xml" });
			$("#gridSolicitudesAccPenalPrivadaPJENC").trigger("reloadGrid");
			muestraOcultaGrids("accPenalPrivada");
		}
	}
	

	//variable para controlar el id del visor
	var idWindowNuevaDenuncia=1; 

	/*
	*Funcion que crea una nueva denuncia
	*/
	function nuevaDenunciaUI(turnoId,numeroExpRelacion) {
		
	    var idExpediente="";
	    var numeroExpediente="";
	    var numeroExpedienteId="";
	    var numeroCasoNuevo="";
		//ya estaba
	    var idNuevaDenuncia = 1;
	 	//variable que indica si es un ingreso o una consulta
	    var ingresoDenuncia = false;
	 	
	 	if(numeroExpRelacion == "null"){
	 		
	 		$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/nuevoExpedienteUI.do',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var option;
					idExpediente=$(xml).find('expedienteDTO').find('expedienteId').first().text();
					
					numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').first().text();
					
					numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').first().text();
					
					numeroCasoNuevo=$(xml).find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').first().text();
					
				}
				
			});	 		
	 	}
	 	else{
	 		
	 		//alert("numeroExpRelacion="+numeroExpRelacion);
	 		
	 		$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/consultarExpedienteByIdPJENC.do',
				data: 'expedienteId='+numeroExpRelacion,
				dataType: 'xml',
				async: false,
				success: function(xml){
				
					idExpediente=$(xml).find('expedienteDTO').find('expedienteId').first().text();
					
					numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').first().text();
					
					numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').first().text();
					
					numeroCasoNuevo=$(xml).find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').first().text();
					
				}
				
			});
	 	}
	   	
	   	
	   	var pantallaSolicitada=3;
		idWindowNuevaDenuncia++;
		isWindowOpen = true;
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente+" - No. Caso: "+numeroCasoNuevo, type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedioPJENC.do?numeroGeneralCaso='+numeroCasoNuevo+'&abreenPenal=abrPenal&idNuevaDenuncia='+idNuevaDenuncia +'&ingresoDenuncia='+ingresoDenuncia +'&numeroExpediente='+numeroExpediente+'&pantallaSolicitada='+pantallaSolicitada+'&idNumeroExpedienteop='+numeroExpedienteId+'&idNumeroExpediente='+numeroExpedienteId+'&idExpedienteop='+idExpediente+'&idNumeroExpedienteConsul='+numeroExpedienteId+'&idExpediente='+idExpediente+'&turnoId='+turnoId+'" width="1430" height="670" />');
		
		if(seleccion=="porAtender"){
			cargaGirdSolicitudesAccPenalPrivadaPJENC('porAtender');	
		}
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

	/************************************************* FUNCIONES PARA LAS FECHAS DE LA BUSQUEDA DE MADNAMIENTOS *****************/
	/*
			*Funcion para traer la fecha y hora del servidor en el formato : YYYY-MM-DD HH:MI:SS
			*/
			function consultaFechaHoraMaximaServer()
			{
				var fecha="";
				   $.ajax({
					     type: 'POST',
					     url: '<%=request.getContextPath()%>/regresaFechaYHoraDelServidor.do',
						 dataType: 'xml',
						 async: false,
						 success: function(xml){
							fecha= $(xml).find('fecha').text();
						  }
						});
				return fecha;
			}
			
			/*
			 * Funcion para regresar la fecha maxima obtenida desde el servidor
			 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
			 * regresa una cadena con la fecha en el formato : DD/MM/YYYY
			 */
			function getFechaMaximaServerHechos(fechaCompleta)
			{
				var arrFechaHora=fechaCompleta.split(" ");
				var digitosFecha=arrFechaHora[0].split("-");
				return digitosFecha[2]+'/'+digitosFecha[1]+'/'+digitosFecha[0];
			}
			/****************************************** FIN  FUNCIONES PARA LAS FECHAS DE LA BUSQUEDA DE MADNAMIENTOS *****************/
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

	<!--Comienza ui-layout-west-->
	<div class="ui-layout-west">
		<div class="header">&nbsp;</div>
		<div class="content">
			<div id="accordionmenuprincipal">
			
				<h3 ><a id="evento" href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes Generadas<br/>en Audiencia</a></h3>
				<div>			
					<ul id="seccion1treePJENC" class="filetree">
						<li>
							<span class="file">
								<a onclick="javascript:cargaGridSolProcesoHistoricoPJENC('<%=EstatusSolicitud.EN_PROCESO.getValorId()%>,<%=EstatusSolicitud.ABIERTA.getValorId()%>')" style="cursor: pointer;">En proceso</a>
							</span>
						</li>
						<li>
							<span class="file" style="cursor: pointer;" onclick="javascript:cargaGridSolProcesoHistoricoPJENC('<%=EstatusSolicitud.CERRADA.getValorId()%>')" >Hist&oacute;rico</span>
						</li>
					</ul>		
				</div>
				
				<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Mandamientos Judiciales</a></h3>
				<div>
					<ul id="seccion2treePJENC" class="filetree">
					<li class="closed" id=""><span id="" class="folder" onmousedown="">En Proceso</span>
								<ul id=""><span class="file">
								<a id="causaMandamientosJudiciales" style="cursor: pointer;" onclick="abreModalCausaMandamientosJudiciales(<%=EstatusMandamiento.EN_PROCESO.getValorId()%>);">del Dia</a>
							</span>
								</ul>
								<ul id=""><span class="file">
								<a id="" style="cursor: pointer;" onclick="abreModalCausaMandamientosJudicialesPorFecha(<%=EstatusMandamiento.EN_PROCESO.getValorId()%>);">por Fecha</a>
							</span>
								</ul>
							</li>	
							<li class="closed" id=""><span id="" class="folder" onmousedown="">Ejecutados</span>
								<ul id=""><span class="file">
								<a id="causaMandamientosJudiciales" style="cursor: pointer;" onclick="abreModalCausaMandamientosJudiciales(<%=EstatusMandamiento.EJECUTADO.getValorId()%>);">del Dia</a>
							</span>
								</ul>
								<ul id=""><span class="file">
								<a id="" style="cursor: pointer;" onclick="abreModalCausaMandamientosJudicialesPorFecha(<%=EstatusMandamiento.EJECUTADO.getValorId()%>);">por Fecha</a>
							</span>
								</ul>
								
							</li>	
							
							
					</ul>	
				</div>
				
				<h3 ><a href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Administrar medidas cautelares</a></h3>
				<div>			
					<ul id="seccion4treePJENC" class="filetree">
						<li>
							<span class="file">
								<a id="causaMedidasCautelares" style="cursor: pointer;" onclick="abreModalCausa();">Por Causa</a>
							</span>
						</li>
					</ul>		
				</div>
				
				<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Estado del Expediente</a></h3>
				<div>
					<ul id="seccion5treePJENC" class="filetree">
						<li>
							<span class="file">
								<a id="estadoExpediente" style="cursor: pointer;" onclick="abreModalCausaEstadoExpediente();">Por Causa</a>
							</span>
						</li>
					</ul>	
				</div>
				
				<h3><a href="#"><img src="<%=request.getContextPath()%>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Sentencia</a></h3>
				<div>
					<ul id="seccion6treePJENC" class="filetree">
						<li>
							<span class="file">
								<a id="sentenciaEnProceso" style="cursor: pointer;" onclick="javascript:cargaGridSentencias('<%=EstatusExpediente.ABIERTO.getValorId()%>')">En proceso</a>
							</span>
							<span class="file">
								<a id="sentenciaFueraDeProceso" style="cursor: pointer;" onclick="javascript:cargaGridSentencias('<%=EstatusExpediente.CERRADO.getValorId()%>,<%=EstatusExpediente.NO_ATENDIDO.getValorId()%>')">Fuera de Proceso</a>
							</span>
						</li>				
					</ul>	
				</div>
				
				<h3 ><a id="even" href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes</a></h3>
				<div>			
					<ul id="seccion34treePJEA" class="filetree">
						<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="poppopTipoBusqueda('expediente')">N&uacute;mero Expediente</a></span></li>
						<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="poppopTipoBusqueda('fecha')">Por Fecha</a></span></li>
					</ul>		
				</div>
				<h3 ><a id="evem" href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="imgAccPenal" width="15" height="15">Solicitud de acción <br\>penal privada </a></h3>
				<div>			
					<ul id="seccionAccPenaltreePJENC" class="filetree">
						<li><span class="file"><a id="accPenalPrivadaPorAtender" style="cursor: pointer;" onclick="cargaGirdSolicitudesAccPenalPrivadaPJENC('porAtender');">Por atender</a></span></li>
						<li><span class="file"><a id="accPenalPrivadaAtendida" style="cursor: pointer;" onclick="cargaGirdSolicitudesAccPenalPrivadaPJENC('atendidas');">Atendidas</a></span></li>
					</ul>		
				</div>
				<h3 ><a id="" href="#" onclick="generaVisorGraficaView()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png"  width="15" height="15">Graficas y Reportes</a></h3>
				<div>		
					<!--	<input type="button" value="Ver Grafica" id="imageViewer" name="imageViewer"/>	
					<ul id="seccion3treePJENC" class="filetree">
						<li class="closed" id="casosPJENC"><span class="folder">Casos</span>
							 Aqui se agregan los casos y expedientes dinamicamente 
						</li>
					</ul>	-->	
				</div>
			</div>
		</div>
	</div>
	<!--Fin ui-layout-west-->

	<!--Comienza ui-layout-east-->
	<div class="ui-layout-east">
		<div class="header">Bienvenido</div>
		<div class="content">
			<div id="accordionmenuderprincipal">
				<h4><a href="#">Datos Personales</a></h4>
				<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
					</center>
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
					<br />
				</div>
				<h6><a href="#" id="" onclick="visorLeyesCodigos()">Consultar Leyes y C&oacute;digos</a></h6>
				<div>
					<!--  <table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" bordercolorlight="#FFFFFF" style="cursor:pointer">
						<tr>
							<td width="100%" id="leyes"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Leyes</td>
						</tr>
						<tr>
							<td id="codigos">&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Codigos</td>
						</tr>
						<tr>
							<td id="manuales">&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Manuales</td>
						</tr>
					</table>-->
				</div>
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
	</div>
	<!--Termina ui-layout-east-->

	<!--Comienza ui-layout-north-->
	<div class="ui-layout-north">
		<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ENCARGADO DE CAUSA</TD>
					    <TD width=126 align=left valign="top"><!--SPAN></SPAN--><img src="<%=request.getContextPath()%>/resources/images/ejecutivo.png" width="100px"></TD>
					    <TD width=272 align=center valign="top"><img id="logoPagina" src="<%=request.getContextPath()%>/resources/images/sis_penal.jpg" width="300px"></TD>
					    <TD width=28 align=middle>&nbsp;</TD><td width="150" align="center"><img src="<%=request.getContextPath()%>/resources/images/escudo.png" width="100px"></td>
					    <TD width="150" align="right"  valign="middle">
										<br>
										<br>
										<br>
										<DIV id=liveclock style="visibility:hidden;"></DIV>
										<a href="#" title="Salir" onclick='$("#dialog-logout").dialog( "open" );'><img src="<%=request.getContextPath()%>/resources/images/cerrar.jpg" width="50" height="50" border="0" style="box-shadow: 2px 2px 5px #999;"/></a>
										<!--a href="#" title="Ayuda"><img src="<%=request.getContextPath()%>/resources/images/Help.png" width="26" height="26" border="0"></a>
										<IMG alt="Icono reloj" src="<%=request.getContextPath()%>/resources/images/clock.png" width=26 height=25-->
									</TD>
					  </TR>
				  </TBODY>
			  </TABLE>
		</div>
	
		<!--comienza barra de herramientas-->
		<ul class="toolbar">
			<div id="menu_head">
				<li id="tbarBtnHeaderZise" class="first"><span></span></li>
				<!--<li id="accionPenalPrivada" ><span></span>Accion Penal Privada</li>-->
			</div>
			<div id="menu_config">
				<li id="generarDocumento"><span></span>Generar Documento&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="15" height="16"--></li>
				<li><span></span>Adjuntar documento&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctoadjun.png" width="10" height="16"--></li>
			</div>
		</ul>
		<!--termina barra de herramientas-->
	</div>
	<!--Termina ui-layout-north-->

	<!--Comienza ui-layout-south-->
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
	<!--Termina ui-layout-south-->

	<!--Comienza main content-->
	<div id="mainContent">
		<div class="ui-layout-center">
			<div class="ui-layout-content">
				<div class="ui-layout-north">
					
					<!--Comienzan los divs para mostrar los diferentes Grids-->
					
						<!--Por el momento no entra en requerimientos-->
					<div id="divGridSolicitudesAccPenalPrivadaPJENC">
						<table id="gridSolicitudesAccPenalPrivadaPJENC" ></table>
						<div id="pagerGridSolicitudesAccPenalPrivadaPJENC"></div>
					</div>
					
					
					<div id="divGridAudienciasProcesoHistorico">
						<table id="gridAudienciasPJENC" ></table>
						<div id="pagerAudienciasPJENC"></div>
					</div>
					<div id="divGridMandamientosJudPJENC">
						<table id="gridMandamientosJudPJENC" ></table>
						<div id="pagerGridMandamientosJudPJENC"></div>
					</div>
					
					<div id="divGridEstadoExpedientePJENC">
						<table id="gridEstadoExpedientePJENC" ></table>
						<div id="pagerGridEstadoExpedientePJENC"></div>
					</div>
					
					<div id="divGridSentenciasEnProcesoPJENC">
						<table id="gridSentenciasEnProcesoPJENC" ></table>
						<div id="pagerGridSentenciasEnProcesoPJENC"></div>
					</div>
					
					<div id="divGridExpedientesDocumentoPJATP">
						<table id="gridExpedientesDocumentoPJATP"></table>
						<div id="paginadorExpedientesDocumentoPJATP"></div>
					</div>
					<!--Terminan los divs para mostrar los diferentes Grids-->					
				</div>	
			</div>
		</div>
	</div>
	<!--Termina main content-->

	<!--Comienza div para mostrar la ventana para ingresar las fechas-->
	<div id="busquedaFecha" style="display: none">

		<table cellspacing="0" cellpadding="0">
			<tr>
				<td width="153">&nbsp;</td>
				<td width="153">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><strong>Fecha Inicio:</strong> <input
					type="text" id="fechaInicio" size="10"/></td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td align="center">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><strong>Fecha
						Fin:&nbsp;&nbsp;</strong>&nbsp; <input type="text" id="fechaFin" size="10" />
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td align="center">&nbsp;</td>
			</tr>
		</table>
	</div>
	<!--Termina div para mostrar la ventana para ingresar las fechas-->

	<!--Comienza div para mostrar la ventana para ingresar el numero de causa en mandamientos judiciales-->	
	<div id="divCausaMandamientosJud" style="display: none">
		<table width="300" cellspacing="0" cellpadding="0">
			<tr>
				<td width="45">&nbsp;</td>
				<td width="308">&nbsp;</td>
				<td width="45">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="justify"><strong>Ingrese el n&uacute;mero de causa: </strong></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input type="text" class="" size="30" maxlength="30" id="datoCausaMandamientosJud"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<!--Termina div para mostrar la ventana para ingresar el numero de causa en mandamientos judiciales-->
	
	<!--Comienza div para mostrar la ventana para ingresar el numero de causa-->	
	<div id="divCausa" style="display: none">
		<table width="300" cellspacing="0" cellpadding="0">
			<tr>
				<td width="45">&nbsp;</td>
				<td width="308">&nbsp;</td>
				<td width="45">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="justify"><strong>Ingrese el n&uacute;mero de causa: </strong></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input type="text" class="" size="30" maxlength="30" id="datoCausa"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<!--Termina div para mostrar la ventana para ingresar el numero de causa-->
	
	<!--Comienza div para mostrar la ventana modal para ver el pdf de mandamiento judicial-->	
	<div id="divPdfMandamientoJudicial" style="display: none">
		<table width="400" cellspacing="0" cellpadding="0">
			<tr>
				<td width="45">&nbsp;</td>
				<td width="308" align="center"><strong>Mandamiento Judicial</strong></td>
				<td width="45">&nbsp;</td>
			</tr>
            <tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><strong>Estado Actual:</strong></td>
				<td>&nbsp;</td>
			</tr>
            <tr>
			  <td>&nbsp;</td>
				<td align="center">
                	<input type="text" id="estadoActualMandamientoJud" style="width: 200px; border: 0; 
                	
                	background: #DDD;" readonly="readonly" />
                </td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><strong> Nuevo  estado:</strong></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><select name="estadoMandamientoJudPJENC" id="estadoMandamientoJudPJENC" style="width:200px;">
				  <option value="0">-Seleccione-</option>
				  <option value="<%=EstatusMandamiento.EJECUTADO.getValorId() %>">Ejecutado</option>
				  <option value="<%=EstatusMandamiento.CANCELADO.getValorId() %>">Cancelado</option>
		      </select></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<!--Termina div para mostrar la ventana para ingresar el numero de causa en mandamientos judiciales-->
	
	<!--Comienza div para mostrar la ventana para ingresar el numero de causa-->	
	<div id="divCausaExpediente" style="display: none">
		<table width="300" cellspacing="0" cellpadding="0">
			<tr>
				<td width="45">&nbsp;</td>
				<td width="308">&nbsp;</td>
				<td width="45">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="justify"><strong>Ingrese el n&uacute;mero de causa: </strong></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input type="text" class="" size="30" maxlength="30" id="datoCausaExpediente"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<!--Termina div para mostrar la ventana para ingresar el numero de causa-->
	<div id="dialog-logout" title="Logout">
		<p align="center">
			<span id="logout">¿Desea cerrar su sesi&oacute;n?</span>
		</p>
	</div>
	
	<!-- Cuadros de dialogo para buscar expedientes por numero de expediente y por fecha -->
	<div id="tiposBusquedaExpedienteid"  style="display: none;"> 
	
		<div id="tiposBusquedaExpediente"  style="display: none;"> 
			<table cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td width="120">
						<bean:message key="numeroDeCausa"/>
					</td>
					<td width="153">
						<input type="text" id="buscapornumexp" size="28" maxlength="32"/>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="tiposBusquedafecha"  style="display: none;">
		
			<table cellspacing="0" cellpadding="0" >
				<tr>
					<td width="153">&nbsp;</td>
					<td width="153">&nbsp;</td>
				</tr>
				<tr>
				  <td colspan="2" align="center">
				  	<strong>Fecha Inicio:</strong>
				  	<input type="text" id="buscaporfechaIni" size="15" readonly="readonly"/>
				  </td>
			    </tr>
				<tr>
				  <td align="center">&nbsp;</td>
				  <td align="center">&nbsp;</td>
		  		</tr>
				<tr>
				  <td colspan="2" align="center">
				  	<strong>Fecha Fin:&nbsp;&nbsp;</strong>&nbsp;
			      	<input type="text" id="buscaporfechaFin" size="15" readonly="readonly"/>
			      </td>
		  		</tr>
				<tr>
				  <td align="center">&nbsp;</td>
				  <td align="center">&nbsp;</td>
		  		</tr>
			</table>
			
		</div>
		
	</div>
		    
	<!--Div para ventana modal del tipo de mandamieto-->
	<div id="divTipoMandamiento" style="display: none">
	
		<table width="400" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="20">&nbsp;</td>
	        <td colspan="2" align="center">
	        	<strong>Tipo de Mandamiento Judicial</strong>
	       	</td>
	        <td width="20">&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td width="160" align="right">&nbsp;</td>
	        <td width="200">&nbsp;</td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Tipo de Mandamiento:</strong>
	       	</td>
	        <td>
	        	<select id="tipoMandamiento" style="width: 200px;">
	              <option value="0">-Seleccione-</option>
	            </select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Nombre del Imputado:</strong>
	       	</td>
	        <td>
	        	<select id="nombreDelImputado" style="width: 200px;">
	          		<option value="0">-Seleccione-</option>
	        	</select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiTipoSentencia">
		        	<strong>Tipo de Sentencia:</strong>
	            </div>
	        </td>
	        <td>
	        	<div id="divCbxTipoSentencia">
	                <select id="tipoSentencia" style="width: 200px;">
	                    <option value="0">-Seleccione-</option>
	                </select>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiFechaInicioSentencia">
	        		<strong>Fecha Inicio:</strong>
	        	</div>
	        </td>
	        <td>
	        	<div id="divFechaInicioSentencia">
	        		<input type="text" id="fechaInicioSentencia" style="width: 100px;"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiFechaFinSentencia">
	        		<strong>Fecha Fin:</strong>
	        	</div>
	       	</td>
	        <td>
	        	<div id="divFechaFinSentencia">
	        		<input type="text" id="fechaFinSentencia" style="width: 100px;"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">&nbsp;</td>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	      </tr>
	    </table>
	    
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