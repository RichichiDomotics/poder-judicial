<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
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
	
	<script type="text/javascript">

	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	//variables globales
	var outerLayout, innerLayout;
	var numExpediente ="";
	var idExpediente;
	var numEtapa="";
	var valorEtapa="";
	var numeroCaso="";
	//var involucradoID="";
	var numeroExpediente;
	
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

		$("#funcionarios").click(agregarEtapa);
						
		outerLayout = $("body").layout( layoutSettings_Outer );
		
		//genera menu izquierdo
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		//menu de arbol de expediente	
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		//menu del arbol de actividades
		$("#seccion5tree").treeview();
		
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
		
		//llama funcion para mostrar gadgets
		muestraGadgets();
		//llama funcion para habilitar elementos del menu izquierdo
		menuDependienteEtapa();
		//asocia funcion al boton de acceso al expediente
		$("#accesoExpediente").click(asigarPermisos);
		//crea agenda
		$("#controlAgenda").click(creaAgenda);
		//crea buscar expediente
		$("#buscarExpediente").click(buscarExpediente);
		//crea generar documento
		$("#generarDocumento").click(generarDocumentoViwe);	
		
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
		createInnerLayout () ;

		$('#avisosAsignacion').click(ocultaMuestraGridsAlertas(8));
		$('#avisosAsignacion').click();
		$('#test').weatherfeed(['MXDF0132']);
		
		//gridPrincipal();		
				
		});
		//Fin onready
	
	
		//Funcion que cierra la etapa
		function cerrarEtapa(){
			$.closeWindow('iframewindowVisorDefensor');
		}
	
		//cierra ventana visor pericial
		function cerrarVentana(){
			$.closeWindow('iframewindowVisorSolicitarServicioPericial');
		}
		
		//crea div servicio pericial
		function solServicioPericial(idExpediente){
			$.newWindow({id:"iframewindowVisorSolicitarServicioPericial", statusBar: true, posx:255,posy:111,width:850,height:380,title:"Solicitar Servicio Pericial", type:"iframe"});
		    $.updateWindowContent("iframewindowVisorSolicitarServicioPericial",'<iframe src="<%=request.getContextPath()%>/solicitarServicioPericial.do?idExpediente='+idExpediente+'" width="850" height="380" />');
		}

		//ejecuta la pantalla de chat
		function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
		}

		//busca expediente
		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
		}

		//busca caso
		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:111,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
		}

		//crea agenda
		function creaAgenda() {
			$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
		    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
		    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
			}

		//genera documento
		function generarDocumentoViwe() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
		    $("#" +"iframewindowGenerarDocumento"+ " .window-maximizeButton").click();	
		}
		
		function defensaIntegracion() {
			var titulo="";
			if(numeroCaso != null && numeroCaso != ""){
				titulo += "Caso: "+numeroCaso+" ";
			}
			
			if(valorEtapa=="Asesoría"){
				titulo += "Asesoría: ";
			}else{
				titulo += "Expediente: ";
			}
			$.newWindow({id:"iframewindowVisorDefensor", statusBar: true, posx:200,posy:50,width:1140,height:400,title:titulo + numeroExpediente, type:"iframe"});
		    //$.updateWindowContent("iframewindowVisorDefensor",'<iframe src="<%= request.getContextPath() %>/MenuDefensaIntegracion.do?etapa='+numEtapa+'" width="1140" height="400" />');
		    $.updateWindowContent("iframewindowVisorDefensor",'<iframe src="<%= request.getContextPath() %>/visorExpedienteDefensoria.do?idNumeroExpediente='+numExpediente+'" width="1140" height="400" />');
		    $("#" +"iframewindowVisorDefensor"+ " .window-maximizeButton").click();	
			}
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
		,	onresize_end:			function () { $("#gridAvisosAsignacion").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridDefensaRestaurativa").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridDetalleFrmPrincipalTres").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridDetalleAsesoriasPericiales").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridDetalleDictamenPericial").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridDefensaTecnica").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridCoordinarCarpeta").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridEjecucion").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridSubordinados").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridAsesorias").setGridWidth($("#mainContent").width() - 5, true);
									}
	}
	};

	
		function ocultaMuestraGridsAlertas(idGrid) {
			$("#divGridDefensor").css("display", "none");
			$("#divGridEjecucion").css("display", "none");
			$("#divGridSolicitudes").css("display", "none");
			$("#divGridDefensaTecnica").css("display", "none");
			$("#divGridAsesoriasPericiales").css("display", "none");
			$("#divGridDictamenPericial").css("display", "none");
			$("#divGridAvisosAsignacion").css("display", "none");
			$("#divGridCoordinarCarpeta").css("display", "none");
			$("#divGridDefensaRestaurativa").css("display", "none");
			$("#divGridCarpetaInvestigacion").css("display", "none");
			$("#divGridAudioVideo").css("display", "none");
			$("#divGridTranscripcion").css("display", "none");
			$("#divGridAudiencias").css("display", "none");	
			$("#divGridAudienciasGeneradas").css("display", "none");	
			$("#divGridAsesorias").css("display", "none");	

			switch(parseInt(idGrid)){
				case 1:
					$("#divGridSolicitudes").css("display", "block");
					break;
				case 2:
					$("#divGridDefensaRestaurativa").css("display", "block");
					gridDefensaRestaurativa();
					break;
				case 3:
					$("#divGridDefensor").css("display", "block");
					gridDefensaIntegracionDefensoria();
					break;
				case 4:
				case 41:
					$("#divGridAsesoriasPericiales").css("display", "block");
					gridAsesoriasPericiales();
					break;
				case 42:
					$("#divGridDictamenPericial").css("display", "block");
					gridDictamenPericial();
					break;
				case 5:
					$("#divGridDefensaTecnica").css("display", "block");
					gridDefensaTecnicaDefensoria();
					break;
				case 6:
					$("#divGridCoordinarCarpeta").css("display", "block");
					gridCoordinarCarpetaDefensoria();
					break;
				case 7:
					$("#divGridEjecucion").css("display", "block");
					gridEjecucion();
					break;
				case 8:
					$("#divGridAvisosAsignacion").css("display", "block");				
					gridAvisosAsignacion();				
					break;	
				case 9:
					$("#divGridCarpetaInvestigacion").css("display", "block");				
					gridCarpetaInvestigacion();				
					break;
				case 10:
					$("#divGridAudioVideo").css("display", "block");
					gridAudioVideo();				
					break;
				case 11:
					$("#divGridTranscripcion").css("display", "block");				
					gridTranscripcion();				
					break;	
				case 12:
					$("#divGridAudiencias").css("display", "block");				
					gridAudiencias();				
					break;	
				case 13:
					$("#divGridAsesorias").css("display", "block");				
					gridAsesoria();				
					break;	
				case 14:
					$("#divGridAudienciasGeneradas").css("display", "block");				
					gridAudienciasGeneradas();				
					break;				
			}
		}
		
		function gridAvisosAsignacion() {
			jQuery("#gridAvisosAsignacion").jqGrid({
				url : '<%= request.getContextPath()%>/consultarSolicitudesDefensorAsignadas.do', 
				datatype: "xml", 
				colNames:['Folio','Caso','Expediente','Imputado', 'Etapa', 'Audiencia','Detenido','Fecha Asignación','idNumeroExpediente'], 
				colModel:[ 	{name:'folio',		index:'2041', align:'center', width:85},
							{name:'caso',		index:'2002', align:'center', width:180},
				           	{name:'expediente', index:'2003', align:'center', width:145},
				           	{name:'imputado',	index:'2009', align:'center', width:150}, 
							{name:'etapa',  	index:'2028', align:'center', width:100}, 								 
							{name:'audiencia',	index:'2042', align:'center', width:60, hidden: true},
							{name:'detenido',	index:'2005', align:'center', width:50, hidden: true},
							{name:'fecha',		index:'2007', align:'center', width:100},
							{name:'idNumeroExpediente',	index:'idNumeroExpediente',	align:'center', width:40, hidden: true}
				
						],
				pager: jQuery('#pagerAvisosAsignacion'),
				rowList:[10,20,30],
				autowidth: true,
				autoheight: true,
				sortname: 'folio',
				viewrecords: true,
				sortorder: "desc",
				scrollOffset:0,
				ondblClickRow: function(rowid) {		
					activaConfirm("avisosAsignacion", rowid);
				}
			}).navGrid('#pagerAvisosAsignacion',{edit:false,add:false,del:false});
			$("#gridAvisosAsignacion").trigger("reloadGrid");
			$("#gview_gridAvisosAsignacion .ui-jqgrid-bdiv").css('height', '450px');	
		}
		 
		
		function activaConfirm(funcionalidad, rowid) {
			var confir = confirm("¿Dar seguimiento?");
			if (confir == true && funcionalidad == "avisosAsignacion") {
				var fila = jQuery("#gridAvisosAsignacion").jqGrid('getRowData',rowid); 
				numExpediente = fila.idNumeroExpediente;
				numeroExpediente = fila.expediente;
				numEtapa=fila.etapa;
				idExpediente=fila.idNumeroExpediente;
				//alert(numEtapa);
				switch (numEtapa) {

				case "Integración":
					numEtapa=<%=EtapasExpediente.INTEGRACION.getValorId()%>;
					valorEtapa="Integración";
					break;
				case "Conciliación y Mediación":
					numEtapa=<%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>;
					valorEtapa="Conciliación y Mediación";
					break;
				case "Técnica":
					numEtapa=<%=EtapasExpediente.TECNICA.getValorId()%>;
					valorEtapa="Técnica";
					break;
				case "Ejecucion":
					numEtapa=<%=EtapasExpediente.EJECUCION.getValorId()%>;
					valorEtapa="Ejecución";
					break;
				case "Asesoría":
					numEtapa=<%=EtapasExpediente.ASESORIA.getValorId()%>;
					valorEtapa="Asesoría";
					break;
				}
				actualizaEstatusAvisoDesignacion(rowid);
				$("#gridAvisosAsignacion").trigger("reloadGrid");
				defensaIntegracion();
			}
		}
		
		function actualizaEstatusAvisoDesignacion(idAvisoDesignacion){			
			var param = 'idDocumento='+ idAvisoDesignacion;
		   	$.ajax({
		   		type: 'POST',
		   		url: "<%= request.getContextPath()%>"+"/actualizaEstatusAvisoDesignacion.do",
		   		data: param,
		   		dataType: 'xml',
		   		async: false,
		   		success: function(xml){}
		   	});
	   }

		//Grid de asesoria
		function gridAsesoria(){
			jQuery("#gridAsesorias").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarExpedientesEtapa.do?etapa=<%=EtapasExpediente.ASESORIA.getValorId()%>',
				datatype: "xml", 
				colNames:['Expediente','Interesado','Fecha de Solicitud'], 
				colModel:[ 	{name:'expediente',index:'expediente', width:230,align:"center"},
				           	{name:'interesado',index:'interesado', width:230,align:"center"},
				          	{name:'FHSolicitud',index:'fHSolicitud', width:230,align:"center"}
				           	
						],
				pager: jQuery('#pagGridAsesorias'),
				rowNum:25,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					numExpediente=rowid;
					idExpediente=rowid;
					numEtapa=<%=EtapasExpediente.ASESORIA.getValorId()%>;
					valorEtapa="Asesoría";
					var ret = jQuery("#gridAsesorias").jqGrid('getRowData',rowid);
					numeroExpediente=ret.expediente;
					defensaIntegracion();			
				}			
			}).navGrid('#pagGridAsesorias',{edit:false,add:false,del:false});
			$("#gview_gridAsesorias .ui-jqgrid-bdiv").css('height', '525px');
			jQuery("#gridAsesorias").trigger("reloadGrid");
		}

		//gridDefensaRestaurativa
		function gridDefensaRestaurativa() {
			jQuery("#gridDefensaRestaurativa").jqGrid({
				url : '<%= request.getContextPath()%>/consultarExpedientesEtapa.do?etapa=<%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Para quien se solicita:', 'Detenido' , 'Fecha-Hora limite de atención','Fecha-Hora de designación'], 
				colModel:[ 	{name:'caso',index:'2002', width:15},
							{name:'expediente',index:'2003', width:15},
							{name:'imputado',index:'2009', width:30}, 								 
							{name:'detenido',index:'2005', width:30},
							{name:'fechaLimite',index:'2038', width:30},
							{name:'fechaDesignacion',index:'2039', width:30}
				],
				pager: jQuery('#pagerDefensaRestaurativa'), 
				rowNum:25, 
				rowList:[10,20,30],
				autowidth: true,
				autoheight: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				scrollOffset:0,
				ondblClickRow: function(rowid) {
					numExpediente=rowid;
					idExpediente=rowid;
					numEtapa=<%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>;
					valorEtapa="Conciliación y Mediación";
					var ret = jQuery("#gridDefensaRestaurativa").jqGrid('getRowData',rowid);
					numeroExpediente=ret.expediente;
					numeroCaso=ret.caso;
					defensaIntegracion();
				}
			}).navGrid('#pagerDefensaRestaurativa',{edit:false,add:false,del:false});
			$("#gridDefensaRestaurativa").trigger("reloadGrid");
			$("#gview_gridDefensaRestaurativa .ui-jqgrid-bdiv").css('height', '450px');	
		}
		
		//grid defensa e integracion
		function gridDefensaIntegracionDefensoria() {
			jQuery("#gridDetalleFrmPrincipalTres").jqGrid({
				url : '<%= request.getContextPath()%>/consultarExpedientesEtapa.do?etapa=<%=EtapasExpediente.INTEGRACION.getValorId()%>', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Imputado', 'Institución Origen', 'Fecha de Detención', 'Fecha de Designación'], 
				colModel:[ 	{name:'caso',index:'2002', width:15},
							{name:'expediente',index:'2003', width:15},
							{name:'imputado',index:'2009', width:30}, 								
							{name:'institucion',index:'2040', width:30},
							{name:'fechaDetencion',index:'2011', width:30},
							{name:'fechaDesignacion',index:'2039', width:30}
						
					],
				pager: jQuery('#pagerTres1'), rowNum:25, rowList:[10,20,30],
				autowidth: true, autoheight:true, sortname: 'detalle',
				viewrecords: true, sortorder: "desc",
				ondblClickRow: function(rowid) {
					idExpediente=rowid;
					numExpediente=rowid;
					numEtapa=<%=EtapasExpediente.INTEGRACION.getValorId()%>;
					valorEtapa="Integración";
					var ret = jQuery("#gridDetalleFrmPrincipalTres").jqGrid('getRowData',rowid);
					numeroExpediente=ret.expediente;
					numeroCaso=ret.caso;
					defensaIntegracion();
				}
			}).navGrid('#pagerTres1',{edit:false,add:false,del:false});
			$("#gridDetalleFrmPrincipalTres").trigger("reloadGrid");
			$("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '450px');	
		}
		

		function gridDefensaTecnicaDefensoria() {		
			jQuery("#gridDefensaTecnica").jqGrid({
				url : '<%= request.getContextPath()%>/consultarExpedientesEtapa.do?etapa=<%=EtapasExpediente.TECNICA.getValorId()%>', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Imputado', 'Tipo Audiencia', 'Sala', 'Fecha Audiencia'], 
				colModel:[ 	{name:'caso',index:'2002', width:15},
							{name:'expediente',index:'2003', width:15},
							{name:'imputado',index:'2009', width:30}, 								
							{name:'tipoAudiencia',index:'2017', width:30},
							{name:'sala',index:'2029', width:30},
							{name:'fechaAudiencia',index:'2018', width:30}
						
					],
				pager: jQuery('#pagerTres1'), rowNum:25, rowList:[10,20,30],
				autowidth: true, autoheight:true, sortname: 'detalle',
				viewrecords: true, sortorder: "desc",
				ondblClickRow: function(rowid) {
					numExpediente=rowid;
					numEtapa=<%=EtapasExpediente.TECNICA.getValorId()%>;
					valorEtapa="Técnica";
					var ret = jQuery("#gridDefensaTecnica").jqGrid('getRowData',rowid);
					numeroExpediente=ret.expediente;
					numeroCaso=ret.caso;
					defensaIntegracion();
				}
			}).navGrid('#pager1',{edit:false,add:false,del:false});
			$("#gridDefensaTecnica").trigger("reloadGrid");
			$("#gview_gridDefensaTecnica .ui-jqgrid-bdiv").css('height', '450px');	
		}

		function gridEjecucion() {
			jQuery("#gridEjecucion").jqGrid({
				url : '<%= request.getContextPath()%>/consultarExpedientesEtapa.do?etapa=<%=EtapasExpediente.EJECUCION.getValorId()%>', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Imputado', 'Tipo Audiencia', 'Sala', 'Fecha Audiencai'], 
				colModel:[ 	{name:'caso',index:'2002', width:15},
							{name:'expediente',index:'2003', width:15},
							{name:'imputado',index:'2009', width:30}, 								
							{name:'tipoAudiencia',index:'2017', width:30},
							{name:'sala',index:'2029', width:30},
							{name:'fechaAudiencia',index:'2018', width:30}
						
					],
				pager: jQuery('#pagerEjecucion'), rowNum:25, rowList:[10,20,30],
				autowidth: true, autoheight:true, sortname: 'detalle',
				viewrecords: true, sortorder: "desc",
				ondblClickRow: function(rowid) {
					numExpediente=rowid;
					numEtapa=<%=EtapasExpediente.EJECUCION.getValorId()%>;
					var ret = jQuery("#gridEjecucion").jqGrid('getRowData',rowid);
					numeroExpediente=ret.expediente;
					numeroCaso=ret.caso;
					defensaIntegracion();
				}
			}).navGrid('#pagerEjecucion',{edit:false,add:false,del:false});
			$("#gridEjecucion").trigger("reloadGrid");
			$("#gview_gridEjecucion .ui-jqgrid-bdiv").css('height', '450px');
		}

	//grid para carpeta de investigacion
	function gridCarpetaInvestigacion(){
		params="tipoSolicitud="+<%=TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId()%>
		jQuery("#gridCarpetaInvestigacion").jqGrid({ 
			url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params, 
			datatype: "xml", 
			colNames:['Caso','Expediente','Folio','Estatus','Fecha Creación', 'Fecha Límite', 'Institucion','Destinatario'], 
					colModel:[ 	{name:'caso',			index:'caso', width:40},
					           	{name:'expediente',		index:'expediente', width:40},
					           	{name:'folio',			index:'folio', width:40},
					           	{name:'estatus',		index:'estatus', width:40}, 
								{name:'fechaCreacion',	index:'fechaCreacion', width:30}, 
								{name:'fechaLimite',	index:'fechaLimite', width:30},
								{name:'institucion',	index:'institucion', width:40},
								{name:'destinatario',	index:'destinatario', width:10}
							],
				pager: jQuery('#pagerCarpetaInvestigacion'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
		}).navGrid('#pagerCarpetaInvestigacion',{edit:false,add:false,del:false});
		$("#gridCarpetaInvestigacion").trigger("reloadGrid");
		$("#gview_gridCarpetaInvestigacion .ui-jqgrid-bdiv").css('height', '450px');	
	}

	//grid para asesorias periciales
	function gridAsesoriasPericiales(){
		params="tipoSolicitud="+<%=TiposSolicitudes.ASESORIA.getValorId()%>
			jQuery("#gridDetalleAsesoriasPericiales").jqGrid({ 
				url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params, 
				datatype: "xml", 
				colNames:['Caso','Expediente','Folio','Estatus','Fecha Creación', 'Fecha Límite', 'Institucion','Destinatario'], 
				colModel:[ 	{name:'caso',			index:'caso', width:40},
				           	{name:'expediente',		index:'expediente', width:40},
				           	{name:'folio',			index:'folio', width:40},
				           	{name:'estatus',		index:'estatus', width:40}, 
							{name:'fechaCreacion',	index:'fechaCreacion', width:30}, 
							{name:'fechaLimite',	index:'fechaLimite', width:30},
							{name:'institucion',	index:'institucion', width:40},
							{name:'destinatario',	index:'destinatario', width:10}
						],
				pager: jQuery('#pagerAsesoriasPericiales'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'expediente',
				viewrecords: true,
				ondblClickRow: function(rowid) {
					solServicioPericial(rowid);
				},
				sortorder: "desc"
		}).navGrid('#pagerAsesoriasPericiales',{edit:false,add:false,del:false});
			$("#gridDetalleAsesoriasPericiales").trigger("reloadGrid");
			$("#gview_gridDetalleAsesoriasPericiales .ui-jqgrid-bdiv").css('height', '450px');	
	}
	
	//grid para dictamenes periciales
	function gridDictamenPericial(){
		params="tipoSolicitud="+<%=TiposSolicitudes.DICTAMEN.getValorId()%>
			jQuery("#gridDetalleDictamenPericial").jqGrid({ 
				url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params,
				datatype: "xml", 
				colNames:['Caso','Expediente','Folio','Estatus','Fecha Creación', 'Fecha Límite', 'Institucion','Destinatario'], 
				colModel:[ 	{name:'caso',index:'caso', width:40},
				           	{name:'expediente',index:'expediente', width:40},
				           	{name:'folio',index:'folio', width:40},
				           	{name:'estatus',index:'estatus', width:40}, 
							{name:'fechaCreacion',index:'fechaCreacion', width:30}, 
							{name:'fechaLimite',index:'fechaLimite', width:30},
							{name:'institucion',index:'institucion', width:40},
							{name:'destinatario',index:'destinatario', width:10}
						],
				pager: jQuery('#pagerDictamenPericial'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'expediente',
				viewrecords: true,
				ondblClickRow: function(rowid) {
					solServicioPericial(rowid);
				},
				sortorder: "desc"
		}).navGrid('#pagerDictamenPericial',{edit:false,add:false,del:false});
		$("#gridDetalleDictamenPericial").trigger("reloadGrid");
		$("#gview_gridDetalleDictamenPericial .ui-jqgrid-bdiv").css('height', '450px');	
	}
	
	//grid para Audio y video
	function gridAudioVideo(){
		params="tipoSolicitud="+<%=TiposSolicitudes.AUDIO_VIDEO.getValorId()%>
		jQuery("#gridAudioVideo").jqGrid({ 
			url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params, 
			datatype: "xml", 
			colNames:['Caso','Expediente','Folio','Estatus','Fecha Creación', 'Fecha Límite', 'Institucion','Destinatario'], 
			colModel:[ 	{name:'caso',index:'caso', width:40},
			           	{name:'expediente',index:'expediente', width:40},
			           	{name:'folio',index:'folio', width:40},
			           	{name:'estatus',index:'estatus', width:40}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:30}, 
						{name:'fechaLimite',index:'fechaLimite', width:30},
						{name:'institucion',index:'institucion', width:40},
						{name:'destinatario',index:'destinatario', width:10}
					],
			pager: jQuery('#pagerAudioVideo'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			autoheight:true,
			sortname: 'numeroExpediente',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerAudioVideo',{edit:false,add:false,del:false});
		$("#gridAudioVideo").trigger("reloadGrid");
		$("#gview_gridAudioVideo .ui-jqgrid-bdiv").css('height', '450px');	
	}

	//grid para Transcripcion
	function gridTranscripcion(){
		params="tipoSolicitud="+<%=TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId()%>
		jQuery("#gridTranscripcion").jqGrid({ 
			url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params, 
			datatype: "xml", 
			colNames:['Caso','Expediente','Folio','Estatus','Fecha Creación', 'Fecha Límite', 'Institucion','Destinatario'], 
			colModel:[ 	{name:'caso',index:'caso', width:40},
			           	{name:'expediente',index:'expediente', width:40},
			           	{name:'folio',index:'folio', width:40},
			           	{name:'estatus',index:'estatus', width:40}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:30}, 
						{name:'fechaLimite',index:'fechaLimite', width:30},
						{name:'institucion',index:'institucion', width:40},
						{name:'destinatario',index:'destinatario', width:10}
					],
			pager: jQuery('#pagerTranscripcion'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			autoheight:true,
			sortname: 'numeroExpediente',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerTranscripcion',{edit:false,add:false,del:false});
		$("#gridTranscripcion").trigger("reloadGrid");
		$("#gview_gridTranscripcion .ui-jqgrid-bdiv").css('height', '450px');	
	}
	
	function gridCoordinarCarpetaDefensoria() {

		jQuery("#gridCoordinarCarpeta").jqGrid({
					url : '<%= request.getContextPath()%>/CoordinarCarpetaDefensoria.do', 
					datatype: "xml", 
				
					colNames:['Involucrados','Delito','Hechos','Objetos y evidencias','Periciales','Audiencias','Documentos'], 
					colModel:[ 	{name:'Involucrados',index:'Involucrados', width:15},
					        	{name:'Delito',index:'Delito', width:15},
					           	{name:'Hechos',index:'Hechos', width:15}, 
								{name:'Objetos',index:'Objetos', width:30}, 
								
								{name:'Periciales',index:'Periciales', width:30}, 
								{name:'Audiencias',index:'Audiencias', width:30},
								{name:'Documentos',index:'Documentos', width:30}
								
					
				],
		pager: jQuery('#pagerCoordinarCarpeta'),
		rowNum:10,
		rowList:[10,20,30],
		autowidth: true,
		autoheight:true,
		sortname: 'detalle',
		viewrecords: true,
		sortorder: "desc",
		ondblClickRow: function(rowid) {
		numExpediente=rowid.split("-")[0];
		//activaConfirm("DefensaIntegracionDefensoria");		
		numEtapa=rowid.split("-")[1];
		defensaIntegracion();
		
		}
	}).navGrid('#pager1',{edit:false,add:false,del:false});
		$("#gridCoordinarCarpeta").trigger("reloadGrid");
		$("#gview_gridCoordinarCarpeta .ui-jqgrid-bdiv").css('height', '450px');
	}

	

	function menuDependienteEtapa(){
		var param = '';	
	  	$.ajax({
	  		type: 'POST',
	  		url: '<%= request.getContextPath()%>/ConsultarExpedientesUsuarioArea.do',
			data : param,
			dataType : 'xml',
			async : false,
			success : function(xml) {
				
				var etapa;
				var i = 0;
				$(xml).find('lista').find('expedienteDTOs').find('etapa').each(
				function() {
					agregarExpedientesArbol($(this).find('idCampo').text());
				});
			}
		});
	}
	
	function agregarExpedientesArbol(etapa){
		switch(parseInt(etapa)){
			case <%= EtapasExpediente.INTEGRACION.getValorId() %>:
				$("#menuDefIntegracion").css("display", "block");
				break;
			case <%= EtapasExpediente.TECNICA.getValorId() %>:
				$("#menuDefTecnica").css("display",	"block");
				break;
			case <%= EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId() %>:
				$("#menuDefRestaurativa").css("display", "block");
				break;
			case <%= EtapasExpediente.EJECUCION.getValorId() %>:
				$("#menuDefEjecucion").css("display", "block");
				break;
			case <%= EtapasExpediente.ASESORIA.getValorId() %>:
				$("#menuDefAsesoria").css("display", "block");
				break;
		}
	}
	
		function agregarEtapa(){
			Subordinados();
			
			$("#divEtapaExpediente").dialog("open");
			$("#divEtapaExpediente").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Favor de Seleccionar la Etapa del Expediente', 
			  	dialogClass: 'alert',
			  	position: [312,40],
			  	width: 900,
			  	height: 477,
			  	maxWidth: 1000,
			  	buttons:{"Buscar":function() {	
				//$("#checkTodosExpedientes").val();
			  				  		
			  		gridExpedientesDeEtapas();			 
			  			$(this).dialog("close");
			  		},
			  		"Cancelar":function() {
			  			$(this).dialog("close");
			  		}
			  	}
			});	
			    
				 
				  
		}	

		 function Subordinados(){
				jQuery("#gridSubordinados").jqGrid({ 
					url:'<%= request.getContextPath()%>/ConsultarFuncionariosSubordinadosEtapa.do', 
					datatype: "xml", 
					colNames:['Nombre Funcionario'], 
					colModel:[ 	{name:'Funcionario',index:'Funcionario', width:400},
					           	
					           	
							],
					pager: jQuery('#pagGridSubordinados'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					height:300,
					sortname: 'numeroExpediente',
					viewrecords: true,
					sortorder: "desc",

					onresize_end:			function () { $("#pagGridSubordinados").setGridWidth($("#mainContent").width() - 5, true); 
					},
					onSelectRow: function(rowid){
						idFuncionario=rowid;
						
						//agregarEtapa();
						
					}
					
				}).navGrid('#pagGridSubordinados',{edit:false,add:false,del:false});
								
				$("#gridSubordinados").trigger("reloadGrid"); 
		    }


	 function gridAudiencias(){
			///SolicitudesNoAtendidas.do
			jQuery("#gridAudiencias").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarAudienciasDefensor.do', 
				datatype: "xml", 
				colNames:['Caso','Causa','Carácter','Tipo de Audiencia','Fecha de Audiencia','Sala'], 
				colModel:[{name:'caso',	 index:'caso', 		width:130},
				          {name:'causa',	 index:'causa',		width:130},
				          {name:'caracter',index:'caracter', 	width:100},
				          {name:'tipo',	 index:'tipo', 	    width:120},
				          {name:'fechaHora',index:'fechaHora',width:155},
				          {name:'sala' ,index:'sala', 		width:110}				         
						],
				
				pager: jQuery('#pagerAudiencias'),
				rowNum:30,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerAudiencias',{edit:false,add:false,del:false});
				jQuery("#gridAudiencias").trigger('reloadGrid');
				$("#gview_gridAudiencias .ui-jqgrid-bdiv").css('height', '450px');
			  }


	 function gridAudienciasGeneradas(){
			params="tipoSolicitud="+<%=TiposSolicitudes.AUDIENCIA.getValorId()%>
			jQuery("#gridAudienciasGeneradas").jqGrid({ 
				url:'<%= request.getContextPath()%>'+"/consultarSolicitudesPorAtender.do?"+params, 
				datatype: "xml", 
				colNames:['Caso','Expediente','Folio','Estatus','Fecha creación','Fecha limite','Institucion','Destinatario'], 
				colModel:[{name:'caso',	 		index:'2002', 	width:130},
				          {name:'expediente',	index:'2003',	width:130},
				          {name:'folio',	 	index:'2003',	width:130},
				          {name:'estatus',		index:'', 		width:100},
				          {name:'fechaCreacion',index:'', 	    width:120},
				          {name:'fechaLimite',	index:'',		width:155},
				          {name:'institucion' ,	index:'2040', 	width:110},
				          {name:'destinatario' ,index:'', 		width:110}					         
						],
				
				pager: jQuery('#pagerAudienciasGeneradas'),
				rowNum:30,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerAudienciasGeneradas',{edit:false,add:false,del:false});
				jQuery("#gridAudienciasGeneradas").trigger('reloadGrid');
				$("#gview_gridAudienciasGeneradas .ui-jqgrid-bdiv").css('height', '450px');
			  }


	 function visorLeyesCodigos() {
			
			$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:255,posy:111,width:809,height:468,title:"Leyes y C&oacute;digos", type:"iframe"});
		    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/detalleLeyesyCodigos.do" width="800" height="500" />');
		    		
		}

		/*
		*Funcion que llama a la funcionalidad para generar un visualizador de imagen  $('#imageViewer').click(generaVisorGraficaView);
		*/
		function generaVisorGraficaView() {
			$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:63,posy:111,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
		    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="400" />');
		    		
		}	

	  var iframewindowAPSE = 0;
	    function asigarPermisos(){
			$.newWindow({id:"iframewindowAPSE"+iframewindowAPSE, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Asignar permisos sobre Expediente: ", type:"iframe"});
			$.updateWindowContent("iframewindowAPSE"+iframewindowAPSE,'<iframe src="<%= request.getContextPath() %>/asigarPermisosExpediente.do" width="1430" height="670" />');
			$("#" +"iframewindowAPSE"+iframewindowAPSE + " .window-maximizeButton").click();
			iframewindowAPSE++;
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
	    				alert("Alarma pospuesta.");
	    			}
	    			else if(estatus=="cancelar")
	    			{
	    				alert("Alarma cancelada");
	    			}
	    			else
	    			{
	    				alert("Alarma aceptada.");
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
<div class="ui-layout-west" >
	<div class="header">&nbsp;</div>
	<div class="content">
		<div id="accordionmenuprincipal">
			<h3><a href="#"  onclick="menuDependienteEtapa();"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes </a></h3>
			<div>
				<ul id="seccion1tree" class="filetree">
					<li class="closed"  id="menuDefRestaurativa" style="display: none" onmousedown="ocultaMuestraGridsAlertas('2');" ><span class="folder"  >Conciliación y mediación</span>
						<ul></ul>
					</li>	
					<li class="closed"  id="menuDefIntegracion" style="display: none" onmousedown="ocultaMuestraGridsAlertas('3');" ><span class="folder"  >Defensa en integración</span>
						<ul></ul>
					</li>
					<li class="closed" id="menuDefTecnica" style="display: none" onmousedown="ocultaMuestraGridsAlertas('5')" ><span class="folder"  >Defensa tecnica</span>
						<ul></ul>
					</li>	
					<li class="closed" id="menuDefEjecucion" style="display: none" onmousedown="ocultaMuestraGridsAlertas('7')"><span class="folder"  >Defensa en ejecución</span>
						<ul></ul>
					</li>	
					<li class="closed" id="menuDefAsesoria" style="display: none" onmousedown="ocultaMuestraGridsAlertas('13')"><span class="folder"  >Asesoría Legal</span>
						<ul></ul>
					</li>	
					 	
				</ul>	
			</div>
			
			<h3><a href="#" id="avisosAsignacion"  onmousedown="ocultaMuestraGridsAlertas('8');"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes sin atender</a></h3>
			<div></div>
				
			<h3><a href="#" id="Audiencia" onmousedown="ocultaMuestraGridsAlertas('12');"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;AudienciaPP</a></h3>
			<div></div>
				
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes generadas</a></h3>
			<div>
				<ul id="seccion2tree" class="filetree">
					<li class="closed" id="carpetaInvestigacion"><span class="folder" id="PA2" onmousedown="ocultaMuestraGridsAlertas('9')">Carpeta de investigación</span>
						<ul></ul>
					</li>
					<li class="closed"><span class="folder" id="ligServiciosPericiales" onmousedown="ocultaMuestraGridsAlertas('4');">Servicios periciales</span>
					<ul>
						<li class="closed">
							<span class="folder" id="asesoriasPericiales" onmousedown="ocultaMuestraGridsAlertas('41');">Asesorias Periciales</span>
							<ul></ul>
						</li>
						<li class="closed">
							<span class="folder" id="dictamenPericial" onmousedown="ocultaMuestraGridsAlertas('42');">Dictamenes Periciales</span>
							<ul></ul>
						</li>
					</ul>
					</li>
					<li class="closed" id="audioVideo"><span class="folder" onmousedown="ocultaMuestraGridsAlertas('10')">Audio y video</span>
					<ul></ul>
					</li>		
					<li class="closed" id="fechas"><span class="folder"  onmousedown="ocultaMuestraGridsAlertas('11')">Transcripciones</span>
					<ul></ul>
					</li>
					<li class="closed" id="audiencia"><span class="folder"  onmousedown="ocultaMuestraGridsAlertas('14')">Audiencia</span>
					<ul></ul>
					</li>
				</ul>				
			</div>
	
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Histórico de expedientes </a></h3>
			<div>
				<ul id="seccion1tree" class="filetree">
					<li class="closed" id="fechas"><span class="folder"  onclick="">Hist&oacute;rico</span>
						<ul>
							<li class="closed" id="caso1"><span class="folder" id="C1" onclick="">2010</span>
								<ul></ul>
							</li>
							<li class="closed" id="caso1"><span class="folder" id="C2" onclick="">2011</span>
								<ul></ul>
							</li>
						</ul>
					</li>
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

<div class="ui-layout-east">
	<div class="header">Bienvenido</div>
	<div class="content">
		<div id="accordionmenuderprincipal">
			<h4>
				<a href="#">Datos Personales</a>
			</h4>
			<div>
				<center>
					<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
				</center>
			</div>
			<!-- <h4>
				<a href="#">Calendario</a>
			</h4>
			<div>
				<center>
					<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
				</center>
			</div>-->
			<h6>
				<a href="#">Agenda</a>
			</h6>
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
			
			<!--h1>
				<a href="#">Clima</a>
			</h1>
			<div align="left">
				<div align="left" id="test"></div>
			</div>
			<h1>
				<a href="#">Chat</a>
			</h1>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>
			</div-->
			<h1><a href="#" onclick="consultarTiposRol();">Facultades</a></h1>
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


	<div class="ui-layout-north">
		<<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">DEFENSORIA</TD>
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
	<ul class="toolbar">
		<div id="menu_head">
			<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			
						
		</div>
		<div id="menu_config">
			<li id="buscarCaso">Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
			<li id="buscarExpediente" >Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
			<li id="accesoExpediente">Acceso a Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctosearch1.png" width="15" height="16"--></li>
			<!--<li id="generarDocumento">Generar Documento</li>
			<li>Turnar Documento</li>
			<li>Digitalizar Documento</li>
			--><li id="verde">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
		</div>
	</ul>
</div>



	<div id="mainContent">
		<div class="ui-layout-center">
			<div class="ui-layout-content">
				<div id="divGridDefensaRestaurativa">
					<table id="gridDefensaRestaurativa"></table>
					<div id="pagerDefensaRestaurativa"></div>
				</div>
				<div id="divGridSolicitudes">
					<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1"></div>
				</div>
				<div id="divGridDefensor">
					<table id="gridDetalleFrmPrincipalTres"></table>
					<div id="pagerTres1"></div>
				</div>
				<div id="divGridAsesoriasPericiales">
					<table id="gridDetalleAsesoriasPericiales"></table>
					<div id="pagerAsesoriasPericiales"></div>
				</div>
				
				<div id="divGridDictamenPericial">
					<table id="gridDetalleDictamenPericial"></table>
					<div id="pagerDictamenPericial"></div>
				</div>
				
				<div id="divGridDefensaTecnica">
					<table id="gridDefensaTecnica"></table>
					<div id="pagerDefensaTecnica"></div>
				</div>
				
				<div id="divGridCoordinarCarpeta">
					<table id="gridCoordinarCarpeta"></table>
					<div id="pagerCoordinarCarpeta"></div>
				</div>
				
				
				<div id="divGridEjecucion">
					<table id="gridEjecucion"></table>
					<div id="pagerEjecucion"></div>
				</div>
				
				<div id="divGridAvisosAsignacion">
					<table id="gridAvisosAsignacion"></table>
					<div id="pagerAvisosAsignacion"></div>
				</div>
				
				<div id="divGridCarpetaInvestigacion">
					<table id="gridCarpetaInvestigacion"></table>
					<div id="pagerCarpetaInvestigacion"></div>
				</div>
				
				<div id="divGridAudioVideo">
					<table id="gridAudioVideo"></table>
					<div id="pagerAudioVideo"></div>
				</div>
				
				<div id="divGridTranscripcion">
					<table id="gridTranscripcion"></table>
					<div id="pagerTranscripcion"></div>
				</div>
				
				<div id="divGridAudiencias">
					<table id="gridAudiencias"></table>
					<div id="pagerAudiencias"></div>
				</div>
				<div id="divGridAudienciasGeneradas">
					<table id="gridAudienciasGeneradas"></table>
					<div id="pagerAudienciasGeneradas"></div>
				</div>
				<!--Espacio para el grid de asesorias-->
					<div id="divGridAsesorias">
					<table id="gridAsesorias"></table>
					<div id="pagGridAsesorias"></div>
					
			</div>
		</div>
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
</div>

<div id="divEtapaExpediente" style="display: none">

</br>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
      <td><div id="divGridSubordinados">
						<table id="gridSubordinados"></table>
						<div id="pagGridSubordinados"></div>
					</div></td>
					<td>
       <table> 
       
       
        <tr >
      <td rowspan="2">Todas las Etapas</td><td rowspan="2"><input type="checkbox" name="checkTodosExpedientes" id="checkTodosExpedientes" onclick="boqueaEtapa()"> </td>
      </tr>
      <td rowspan="2"></td><td rowspan="2"> </td>
       <tr >
      
      </tr>
       <tr><td>Etapa del Expediente &emsp; </td>
        <td>
        <select id="etapaOptionDEATT">
        
        </select>
        
         </td>
       
      </tr>
      
      <tr >
      
      </tr>
      </table>
      </td>
       
      </tr>
    </table>
	
    </div>
    
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
</body>
<script type="text/javascript">
	$( "#dialog-alarm" ).dialog();
	$( "#dialog-alarmPos" ).dialog();
	$( "#dialog-alarm" ).dialog( "destroy" );
	$( "#dialog-alarmPos" ).dialog( "destroy" );	
</script>
</html>
