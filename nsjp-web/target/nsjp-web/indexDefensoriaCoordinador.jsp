<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
	
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
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/mktSignup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}

	

	var idDocumento;
	var tieneDefensor;
	var varIdNumExp="";
	var varexp="";
	var varSol="";
	var numeroCaso="";
	var gridToReload = "";
	var reloadGrid=false;
	var consultaTarea="";
	var idFuncionario="";
	var expTodos="";
	var outerLayout, innerLayout;
	
	var contextoPagina = "${pageContext.request.contextPath}";
	
	var firstGridFuncionarios = true;

	//variable para verificar si se cargo el grid de sol. periciales no atendidas por primera vez
	var firstGridSolicitudesPericialesNoAtendidas = true;
	var firstGridEvidenciasNuevas = true;
	var firstGridEvidenciasPendientes = true;
	var firstGridEvidenciasConcluidas = true;

	var idWindowConsultarExpedientesAsociadosADefensores = 1;
	var idWindowSolicitarEvidencia = 1;
	var idWindowSolicitarPericial = 1;
	var idWindowMultasySanciones = 1;
	var idWindowInspecciones = 1;
	//variable para verificar si se cargo el grid de sol. periciales en proceso por primera vez
	var firstGridSolicitudesPericialesEnProceso = true;
	//variable para verificar si se cargo el grid de sol. periciales terminadas por primera vez
	var firstGridSolicitudesPericialesTerminadas = true;
	
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
		
		//obtenemos el tiempo de las alarmas y ponemos en marcha el timer		
		var tiempo='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoRevisionAlarmas()%>';
		setInterval(muestraAlerta, tiempo);

		//$('#imageViewer').click(generaVisorGraficaView);
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		
		//Arboles del menu derecho
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion5tree").treeview();
		//menuDependienteEtapa();
		
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
		
		muestraGadgets();

		//Crea las tabs del acordeon
		$( "#tabs" ).tabs();
		
		$("#tabsprincipalconsulta" ).tabs();
		//se agrega el evento para crear la agenda
		$("#controlAgenda").click(creaAgenda);	
		//se agrega al evento para vacaciones e incapacidades
		$("#vacacionesIncapacidades").click(vacacionesIncapacidad);
		//se agrega al evento para buscar el expediente
		$("#buscarExpediente").click(buscarExpediente);
		//se agrega el evento para buscar casos		
		$("#buscarCaso").click(buscarCaso);
		//se agrega la funcionalidad para crear una nueva denuncia
		$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);
		//se agrega la funcionalidad para crear un nuevo documento
		$("#generarDocumento").click(generarDocumentoView);
		//se agrega la funcionalidad para cargar grid de designaciones
		$("#adesignaciones").click(gridRecibirDesignaciones);
		//se agrega la funcionalidad para cargar grid de Avisos de personas detenidas
		$("#avisosDetencion").click(gridSolAvisosDetencion);
		//click dinamico a pestaña
		//$("#avisosDetencion").click();
		//Consulta audiencias programadas
		$("#ligAudiencia").click(gridAudiencias);
		//asocia a carpeta de asesorias funcion para recargar el grid
		$("#asesorias").click(gridAsesoria);
		//asocia a carpeta de atencion temprana funcion para recargar el grid
		$("#solicitudAtencionTemprana").click(gridSolAtencionTemprana);
		//Registra los eventos de click para el menu de Avisos de Personas Detenidas - Poder Judicical
		$("#solicitudPoderJudicial").click(gridSolPoderJudicial);
		
		//se agrega al evento para consulta de funcionarios
		$("#consultaFuncionario").click(cargaGridFuncionarios);
		//se agrega al evento para ingresar un funcionario
		$("#nuevoFuncionario").click(ventanaNuevoFuncionario);
		$("#reasignarDefensor").click(reasignarDefensor);
		$("#cobroAreancceles").click(abrirVentanaAranceles);
		$("#multasYSanciones").click(abrirVentanaMultasSanciones);
		$("#inspecciones").click(abrirVentanaInspecciones);
		$("#funcionarios").click(consultarExpedientesAsociadosADefensores);

		$("#tbarBtnQuejaCiudadana").click(muestraQuejaCiudadana);
		
		
	
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
		createInnerLayout();

		$('#avisosDetencion').click();
		$('#test').weatherfeed(['MXDF0132']);

				
	});//FIN DE ONREADY
				
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
										var ancho = $("#mainContent").width() - 5;
										$("#gridSolicitudesPericialesNoAtendidas").setGridWidth(ancho, true);
										$("#gridEvidenciasNuevas").setGridWidth(ancho, true);
										$("#gridEvidenciasPendientes").setGridWidth(ancho, true);
										$("#gridEvidenciasConcluidas").setGridWidth(ancho, true);
										$("#gridSolicitudesPericialesEnProceso").setGridWidth(ancho, true);
										$("#gridSolicitudesPericialesTerminadas").setGridWidth(ancho, true);
										$("#gridRecicbirDesignaciones").setGridWidth(ancho, true);
										$("#gridAudiencias").setGridWidth(ancho, true);
										$("#gridDetalleSolAvisosDetencion").setGridWidth(ancho, true);
										$("#gridDefensores").setGridWidth(ancho, true);
										$("#divGridSolicitudAtencionTempranaGrid").setGridWidth(ancho, true);
										$("#gridSolicitudPoderJudicial").setGridWidth(ancho, true);
										$("#gridSubordinados").setGridWidth(ancho, true);
										$("#gridAsesorias").setGridWidth(ancho, true);
										$("#gridExpedientesSubordinados").setGridWidth(ancho, true);
										
									}
	}
	};

	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
	}
	
	function abrirVentanaAranceles(){
		 var clave = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
		 if(clave != undefined && clave!= null){
			 var def = jQuery("#gridSubordinados").jqGrid('getRowData',clave);
			 var idDefensor = clave;
			 var nombreDef = def.nombre;
			 var idExp = jQuery("#gridExpedientesSubordinados").jqGrid('getGridParam','selrow');
			 if(idExp != undefined && idExp != null){
				 var exp = jQuery("#gridExpedientesSubordinados").jqGrid('getRowData',idExp);			 
				 var numeroExpedienteSt = exp.Expediente;
				 var numeroExpedienteId = idExp;
				 var etapaExpediente = exp.Etapa;
				 arancelesExpediente(numeroExpedienteId,numeroExpedienteSt, etapaExpediente, idDefensor, nombreDef);
			 }else{
				alert("Primero debe de seleccionar un expediente."); 
			 }
		 }else{
			 alert("Primero debe de seleccionar un defensor para ver su lista de expedientes.");
		 }
	 }
	
	function abrirVentanaMultasSanciones(){
		var clave = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
		if(clave != undefined && clave != null){
			var params = "";
			var def = jQuery("#gridSubordinados").jqGrid('getRowData',clave);
			params += "?idDefensor="+clave; 
			params += "&nombreDef="+def.nombre;
			var nombreDef = def.nombre;
			idWindowMultasySanciones++;
			$.newWindow({id:"iframeMultasySanciones" + idWindowMultasySanciones, statusBar: true, posx:256,posy:115,width:850,height:600,title:"Multas y Sanciones", type:"iframe"});
			$.updateWindowContent("iframeMultasySanciones" + idWindowMultasySanciones,'<iframe src="<%= request.getContextPath() %>/multasYSanciones.do'+params+'" width="850" height="600" />');		
		}else{
			alert("Primero debe de seleccionar un defensor.");
		}
	}

	function cerrarVentanaMultas(){
	    var pantalla ="iframeMultasySanciones"+idWindowMultasySanciones;
		$.closeWindow(pantalla);
	}
	
	function abrirVentanaInspecciones(){
		 var clave = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
		 if(clave != undefined && clave!= null){
			var params = "";
			var def = jQuery("#gridSubordinados").jqGrid('getRowData',clave);
			params += "?idDefensor="+clave; 
			params += "&nombreDef="+def.nombre;
			var idExp = jQuery("#gridExpedientesSubordinados").jqGrid('getGridParam','selrow');
			if(idExp != undefined && idExp != null){
				var exp = jQuery("#gridExpedientesSubordinados").jqGrid('getRowData',idExp);			 
				params += "&numeroExpediente="+exp.Expediente;
				params += "&numeroExpedienteId="+idExp;
				params += "&etapaExpediente="+exp.Etapa;
				idWindowInspecciones++;
				$.newWindow({id:"iframeInspecciones" + idWindowInspecciones, statusBar: true, posx:256,posy:115,width:950,height:600,title:"Inspecciones:"+def.nombre, type:"iframe"});
				$.updateWindowContent("iframeInspecciones" + idWindowInspecciones,'<iframe src="<%= request.getContextPath() %>/inspeccionesDefensoria.do'+params+'" width="950" height="600" />');		
			}else{
				alert("Debe de seleccionar un expediente."); 
			}
		 }else{
			 alert("Debe seleccionar un defensor.");
		 }
	}

	function cerrarVentanaInspeccion(){
	    var pantalla ="iframeInspecciones"+idWindowInspecciones;
		$.closeWindow(pantalla);
	}

	//Ventana de captura de queja ciudadana
	function muestraQuejaCiudadana(){
		
		$.newWindow({id:"iframewindowQuejaCiudadana", statusBar: true, posx:255,posy:111,width:1023,height:473,title:"Queja Ciudadana", type:"iframe"});
	    $.updateWindowContent("iframewindowQuejaCiudadana",'<iframe src="<%=request.getContextPath()%>/quejaCiudadana.do" width="1023" height="473" />'); 
	}

	//Ventana de captura de queja ciudadana
	function consultaQuejaCiudadana(rowid){
		$.newWindow({id:"iframewindowConsultaQuejaCiudadana", statusBar: true, posx:200,posy:50,width:850,height:350,title:"Consulta de Queja Ciudadana", type:"iframe"});
	    $.updateWindowContent("iframewindowConsultaQuejaCiudadana",'<iframe src="<%=request.getContextPath()%>/consultaQuejaCiudadana.do?idQueja='+rowid+'" width="850" height="350" />'); 
	}

	function cerrarVentanaConsultaQueja(){
		var pantalla ="iframewindowConsultaQuejaCiudadana";
		$.closeWindow(pantalla);
	}
	
	//Ventana de queja ciudadana concluida
	function quejaCiudadanaConcluida(rowid){
		$.newWindow({id:"iframewindowQuejaCiudadanaConcluida", statusBar: true, posx:200,posy:50,width:850,height:350,title:"Queja Ciudadana Concluida", type:"iframe"});
	    $.updateWindowContent("iframewindowQuejaCiudadanaConcluida",'<iframe src="<%=request.getContextPath()%>/quejaCiudadanaConcluida.do?idQueja='+rowid+'" width="850" height="350" />'); 
	}

	function cerrarVentanaQuejaConcluida(){
		var pantalla ="iframewindowQuejaCiudadanaConcluida";
		$.closeWindow(pantalla);
	}
	
	function cerrarVentanaQueja(){
		var pantalla ="iframewindowQuejaCiudadana";
		$.closeWindow(pantalla);
	}
	
	function muestraGridQuejaPendiente(){

		jQuery("#gridQuejaPendiente").jqGrid({
			url : '<%= request.getContextPath()%>/consultaGridQuejasPEndientes.do', 
			datatype: "xml", 
			
			colNames:['Folio de Queja','Nombre de Quejoso','Calidad del Afectado','Nombre del Funcionario','Tipo de Queja'], 
			colModel:[ 	
			        	{name:'FolioQueja',index:'1', sortable:false,width: 150},
			           	{name:'NombreQuejoso',index:'2', sortable:false, width: 250},
			           	{name:'CalidadQuejoso',index:'3', sortable:false, width: 150}, 
			           	{name:'NombreFuncionario',index:'4', sortable:false, width: 250}, 
			           	{name:'TipoQueja',index:'5', sortable:false, width: 200}, 							
			
		],
		autowidth: false,
		width:924, 
		pager: jQuery('#paginadorgridQuejaPendiente'),
		rowNum:10,
		rowList:[10,20,30],
		sortname: '1',
		viewrecords: true,
		sortorder: "desc",
		ondblClickRow: function(rowid) {
			
			consultaQuejaCiudadana(rowid);
			
		} 
		}).navGrid('#paginadorgridQuejaPendiente',{edit:false,add:false,del:false});
		ocultaMuestraGrids("QuejaPendiente");
	}

	function muestraGridQuejaConcluida(){

		jQuery("#gridQuejaConcluida").jqGrid({
			url : '<%= request.getContextPath()%>/consultaGridQuejasConcluidas.do', 
			datatype: "xml", 
			
			colNames:['Folio de Queja','Nombre de Quejoso','Calidad del Afectado','Nombre del Funcionario','Motivo de Rechazo'], 
			colModel:[ 	
			        	{name:'FolioQueja',index:'1', sortable:false,width: 150},
			           	{name:'NombreQuejoso',index:'2', sortable:false, width: 250},
			           	{name:'CalidadQuejoso',index:'3', sortable:false, width: 150}, 
			           	{name:'NombreFuncionario',index:'4', sortable:false, width: 250}, 
			           	{name:'MotivoRechazo',index:'5', sortable:false, width: 200}, 							
			
		],
		autowidth: false,
		width:924, 
		pager: jQuery('#paginadorgridQuejaConcluida'),
		rowNum:10,
		rowList:[10,20,30],
		sortname: '1',
		viewrecords: true,
		sortorder: "desc",
		ondblClickRow: function(rowid) {
			quejaCiudadanaConcluida(rowid);
		} 
		}).navGrid('#paginadorgridQuejaConcluida',{edit:false,add:false,del:false});
		ocultaMuestraGrids("QuejaConcluida");
	}
	
	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
	}
	
	//Abre una nueva ventana para consultar los expedientes asociados a defensores
	function arancelesExpediente(numeroExpedienteId, numeroExpedienteSt, etapaExpediente, idDefensor, nombreDef) {
		var params = "";
		params += "?numeroExpedienteId="+numeroExpedienteId;
		params += "&numeroExpedienteSt="+numeroExpedienteSt;
		params += "&etapaExpediente="+etapaExpediente;
		params += "&idDefensor="+idDefensor;
		params += "&nombreDef="+nombreDef;
		params += "&mostrarCaptura=true";
		var idWinAran = idWindowConsultarExpedientesAsociadosADefensores++;
		$.newWindow({id:"iframeArancelExpediente" + idWinAran, statusBar: true, posx:256,posy:115,width:850,height:440,title:"Aranceles del Expediente "+numeroExpedienteSt, type:"iframe"});
	    $.updateWindowContent("iframeArancelExpediente" + idWinAran,'<iframe src="<%= request.getContextPath() %>/manejoDeAranceles.do'+params+'" width="850" height="440" />');		
	}
	
	 function consultarExpedientesAsociadosADefensores(){		 
		 var ancho = $("#mainContent").width() - 15;
			jQuery("#gridSubordinados").jqGrid({
				 url: '<%= request.getContextPath()%>/consultarDefensoresActivos.do?index=1',
				datatype: "xml", 
				colNames:['Tipo de Defensa','Nombre','Especialidad'], 
				colModel:[ {name:'TipoDefensa',index:'2025', width:100, align:"center"},
						   {name:'nombre',index:'2026', width:150, align:"center"},
						   {name:'especialidad',index:'2027', width:150, align:"center"}			     
						  ],
				pager: jQuery('#pagGridSubordinados'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:ancho,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				onSelectRow: function(rowid){
					gridExpedientesDeEtapas(rowid);
				}
			}).navGrid('#pagGridSubordinados',{edit:false,add:false,del:false});
			gridExpedientesDeEtapas(0);
			ocultaMuestraGrids('gridSubordinados');
			$("#gridSubordinados").trigger("reloadGrid"); 
	    }

	 gridExpedientesEtapaCargado = false;
	 function gridExpedientesDeEtapas(rowid){	
		var ancho = $("#mainContent").width() - 15;
		var param ='expTodos=true&idFuncionario='+rowid;
		if(!gridExpedientesEtapaCargado){
			jQuery("#gridExpedientesSubordinados").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultarExpedientesPorUsuarioYEtapa.do', 
				datatype: "xml",
				data: param,					
				colNames:['Caso','Expediente','Etapa','Defendido','Delito(s)'], 
				colModel:[ 	{name:'Caso',index:'2002', width:250},
				           	{name:'Expediente',index:'2003', width:200, align:"center"},
				           	{name:'Etapa',index:'2028', width:200, align:"center"},
				           	{name:'Defendido',index:'2009', width:350, align:"center"},
					        {name:'delit',index:'delito', width:250, hidden:true}
						  ],
				pager: jQuery('#pagGridExpedientesSubordinados'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:ancho,
				autoheight:true,
				sortname: '2003',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagGridExpedientesSubordinados',{edit:false,add:false,del:false});
			gridExpedientesEtapaCargado = true;
		}else{
			jQuery("#gridExpedientesSubordinados").jqGrid('setGridParam',
				{url:'<%= request.getContextPath()%>/ConsultarExpedientesPorUsuarioYEtapa.do?'+param});
			$("#gridExpedientesSubordinados").trigger("reloadGrid");
		}
	}
	 
	 function reasignarDefensor(){
		 var selrow = jQuery("#gridExpedientesSubordinados").jqGrid('getGridParam','selrow');
		 if(selrow != undefined && selrow != null){
			 var confir = confirm("Desea reasignar defensor?");
			 var param = "idExpediente="+selrow;	
			 if(confir){
			   	$.ajax({
			   		type: 'POST', data: param, dataType: 'xml',
			   		url: '<%= request.getContextPath()%>/cambiarDefensorAsignado.do',
			   		async: false,
			   		success: function(xml){
			   			varsol = $(xml).find('documentoId').first().text();
			   			varexp = $(xml).find('expediente').first().find('numeroExpediente').first().text();
			   			numeroCaso = $(xml).find('expediente').find("casoDTO").find("numeroGeneralCaso").text();
					 	gridToReload = "#gridExpedientesSubordinados";
					 	mostrarDetalleAsignacion(3, numeroCaso, varexp, $(xml).find('documentoId').first().text(), 0 ,1);
			   		}
			   	});
			 }
		 }else{
			alert("Primero debe de seleccionar un expediente asociado a un defensor."); 
		 }
	 }
 
		function asignaAtencionAbogado(tipo, exp, sol) {
			var params = "?tipo="+tipo;
			params += "&stNumEx="+exp;
			params += "&idAviso="+sol;
			$.newWindow({id:"iframewindowAsignaAtencionAbogado", statusBar: true, posx:200,posy:50,width:1000,height:400,title:" Expediente: "+exp, type:"iframe"});
		    $.updateWindowContent("iframewindowAsignaAtencionAbogado",'<iframe src="<%= request.getContextPath() %>/asignaAtencionAbogado.do'+params+'" width="1000" height="400" />');
		    		
		}	
	
	//Abre una nueva ventana para solicitar evidencia
	function solicitaEvidencia(rowid) {
		idWindowSolicitarEvidencia++;
		$.newWindow({id:"iframewindowSolicitarEvidencia" + idWindowSolicitarEvidencia, statusBar: true, posx:200,posy:50,width:1050,height:400,title:"Solicitud de Evidencia", type:"iframe"});
	    $.updateWindowContent("iframewindowSolicitarEvidencia" + idWindowSolicitarEvidencia,'<iframe src="<%= request.getContextPath() %>/solicitudDeEvidencia.do?rowid='+rowid+'" width="1050" height="400" />');		
	}		
	
	function cerrarVentanaEvidencia(){
	    var pantalla ="iframewindowSolicitarEvidencia"+idWindowSolicitarEvidencia;
		$.closeWindow(pantalla);
	}

	//Abre una nueva ventana para solicitar pericial
	function mostrarSolPericial(rowid) {
		idWindowSolicitarPericial++;
		$.newWindow({id:"iframewindowSolicitarPericial" + idWindowSolicitarPericial, statusBar: true, posx:200,posy:50,width:1050,height:400,title:"Solicitud de Pericial", type:"iframe"});
	    $.updateWindowContent("iframewindowSolicitarPericial" + idWindowSolicitarPericial,'<iframe src="<%= request.getContextPath() %>/solicitudDePericial.do?rowid='+rowid+'" width="1050" height="400" />');		
	}	
	function cerrarVentanaPericial(){
	    var pantalla ="iframewindowSolicitarPericial"+idWindowSolicitarPericial;
		$.closeWindow(pantalla);
	}

	//Abre una nueva ventana de chat 
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}
    
  //Crea una nueva ventana para vacacionesIncapacidades
	function vacacionesIncapacidad() {
		$.newWindow({id:"iframewindowVacacionesIncapacidades", statusBar: true, posx:255,posy:111,width:824,height:400,title:"registrar Vacaciones e Incapacidades", type:"iframe"});
    	$.updateWindowContent("iframewindowVacacionesIncapacidades",'<iframe src="<%= request.getContextPath() %>/vacacionesIncapacidad.do" width="824" height="400" />');		
	}
	//Crea una nueva ventana para buscar expedientes
	function buscarExpediente() {
		$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
	}

	//Crea una nueva ventana con la funcionalidad para buscar un caso
	function buscarCaso() {
		$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
	}
	
	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    //$("#iframewindowagenda.window-maximizeButton").click();	
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
		}

	//Crea una nueva ventana con la funcion de crear una nueva denuncia
	function nuevaDenuncia() {
		$.newWindow({id:"iframewindowNuevaDenuncia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Nueva Denuncia", type:"iframe"});
	    $.updateWindowContent("iframewindowNuevaDenuncia",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?idNuevaDenuncia=1" width="1140" height="400" />');		
	}

	//Crea una nueva ventana para crear un nuevo documento
	function generarDocumentoView() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
	}

	/*
	 * Genera ventana para registrar a un nuevo funcionario 
	 */
	function ventanaNuevoFuncionario()
	{
	    customVentana("iframewindowRegistrarPerito","Registrar Nuevo Defensor","/registrarFuncionario.do","?administrador=0", cargaGridFuncionarios);
	}

	/*
	 * Genera ventana para consultar un nuevo funcionario defensor
	 */
	function muestraConsultaFuncionario(rowid)
	{
	    customVentana("iframewindowConsultaFuncionario", "Consulta De Defensor", "/consultarFuncionario.do", "?rowid="+rowid, cargaGridFuncionarios);    
	}

	function cerrarVentanaConsultaFuncionario(){
	    var pantalla ="iframewindowConsultaFuncionario";
		$.closeWindow(pantalla);
	}
	
	/*
	*Funcion que oculta o muestra los grids, recibe como parametro
	*el nombre del grid que va a mostrar, y todos los demas, se 
	*ocultaran
	*/
	function ocultaMuestraGrids(nombreGrid){

		$("#divEtapaExpediente").hide();
		$("#divGridEvidenciasPendientes").hide();
		$("#divGridEvidenciasConcluidas").hide();
		$("#divGridEvidenciasNuevas").hide();
		$("#divDetalleEvidencia").hide();
		$("#divGridSolicitudesPericialesEnProceso").hide();
		$("#divGridSolicitudesPericialesTerminadas").hide();
		$("#divGridSolicitudesPericialesNoAtendidas").hide();	
		$("#divGridSolicitudAtencionTempranaDiv").hide();
		$("#divGridSolicitudPoderJudicial").hide();
		$("#divGridAvisosDetencion").hide();	
		$("#divGridRecicbirDesignaciones").hide();
		$("#divGridDefensores").hide();
		$("#divGridAudiencias").hide();
		$("#divGridAsesorias").hide();
		$("#reasignarDefensor").hide();
		$("#cobroAreancceles").hide();
		$("#multasYSanciones").hide();
		$("#inspecciones").hide();
		$('#divGridQuejaPendiente').hide();
		$('#divGridQuejaConcluida').hide();

		if(nombreGrid == "gridSubordinados"){
			$("#reasignarDefensor").show();
			$("#cobroAreancceles").show();			
			$("#multasYSanciones").show();
			$("#inspecciones").show();
			$("#divEtapaExpediente").show();
			$('#divGridQuejaPendiente').hide();
			$('#divGridQuejaConcluida').hide();
		}
		if(nombreGrid == "gridSolicitudesPericialesNoAtendidas")
			$("#divGridSolicitudesPericialesNoAtendidas").show();	
		if(nombreGrid == "gridEvidenciasNuevas")
			$("#divGridEvidenciasNuevas").show();
		if(nombreGrid == "gridEvidenciasPendientes")
			$("#divGridEvidenciasPendientes").show();
		if(nombreGrid == "gridEvidenciasConcluidas")
			$("#divGridEvidenciasConcluidas").show();
		if(nombreGrid == "gridDetalleEvidencia")
			$("#divDetalleEvidencia").show();
		if(nombreGrid == "gridSolicitudesPericialesEnProceso")
			$("#divGridSolicitudesPericialesEnProceso").show();
		if(nombreGrid == "divGridRecicbirDesignaciones")
			$("#divGridRecicbirDesignaciones").show();
		if(nombreGrid == "gridSolicitudesPericialesTerminadas")
			$("#divGridSolicitudesPericialesTerminadas").show();
		if(nombreGrid == "divGridAvisosDetencion")
			$("#divGridAvisosDetencion").show();
		if(nombreGrid == "divGridSolicitudAtencionTempranaDiv")
			$("#divGridSolicitudAtencionTempranaDiv").show();
		if(nombreGrid == "divGridSolicitudPoderJudicial")
			$("#divGridSolicitudPoderJudicial").show();
		if(nombreGrid == "gridDefensores")
			$("#divGridDefensores").show();
		if(nombreGrid == "divGridAudiencias")
			$("#divGridAudiencias").show();	
		if(nombreGrid == "divGridAsesorias")
			$("#divGridAsesorias").show();	
		if(nombreGrid == "QuejaPendiente")
			$("#divGridQuejaPendiente").show();
		if(nombreGrid == "QuejaConcluida")
			$('#divGridQuejaConcluida').show();
		
	}

	/*
	*Funcion que carga el grid con las solicitudes periciales no atendidas
	*/
	function cargaGridSolicitudesPericialesNoAtendidas(){

		if(firstGridSolicitudesPericialesNoAtendidas == true){
			
			jQuery("#gridSolicitudesPericialesNoAtendidas").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesNoAtendidas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2', 
				datatype: "xml", 
				colNames:['Folio','Número de Caso','Número de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha Límite','Fecha Solicitud' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:200},
				           	{name:'NumeroExpediente',index:'3', width:200},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'EspecialidadPerito',index:'5', width:150}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaSolicitud',index:'7', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesPericialesNoAtendidas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					mostrarSolPericial(rowid);
				}
			}).navGrid('#pagerGridSolicitudesPericialesNoAtendidas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesPericialesNoAtendidas=false;
		}
		else{
			jQuery("#gridSolicitudesPericialesNoAtendidas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesNoAtendidas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2',datatype: "xml" });
			$("#gridSolicitudesPericialesNoAtendidas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesPericialesNoAtendidas');
	}

	/*
	*Funcion que carga el grid de evidencias nuevas
	*/
	function cargaGridEvidenciasNuevas(){
		if(firstGridEvidenciasNuevas == true){
			
			jQuery("#gridEvidenciasNuevas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=1&areaSolicitante=1', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Cadena de custodia','Número de evidencia','Nombre del solicitante','Fecha límite','Perito responsable','Acuse' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'CadenaCustodia',index:'5', width:200},
				           	{name:'NumeroEvidencia',index:'6', width:150},
							{name:'FechaLimite',index:'7', width:100},
				           	{name:'PeritoResponsable',index:'8', width:150}, 
							{name:'Acuse',index:'9', width:30}
						],
				pager: jQuery('#pagerEvidenciasNuevas'),
				rowNum:10,
				rowList:[10,20,30],
				width:'100%',
				autowidth: true,
				//autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					solicitaEvidencia(rowid);
				}
			}).navGrid('#pagerGridEvidenciasNuevas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasNuevas=false;
		}
		else{
			jQuery("#gridEvidenciasNuevas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=1&areaSolicitante=1',datatype: "xml" });
			$("#gridEvidenciasNuevas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasNuevas');
	}

	/*
	*Funcion que carga el grid de evidencias pendientes
	*/
	function cargaGridEvidenciasPendientes(){
		if(firstGridEvidenciasPendientes == true){
			
			jQuery("#gridEvidenciasPendientes").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=2&areaSolicitante=1', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Cadena de custodia','Número de evidencia','Nombre del solicitante','Fecha límite','Fecha ultima de modificación' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'CadenaCustodia',index:'5', width:150},
				           	{name:'NumeroEvidencia',index:'6', width:150},
							{name:'FechaLimite',index:'7', width:100},
				           	{name:'FechaUltimaModificacion',index:'8', width:100, hidden:true}
						],
				pager: jQuery('#pagerEvidenciasPendientes'),
				rowNum:10,
				rowList:[10,20,30],
				width:'100%',
				autowidth: true,
				//autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					solicitaEvidencia(rowid);
				}
			}).navGrid('#pagerGridEvidenciasPendientes',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasPendientes=false;
		}
		else{
			jQuery("#gridEvidenciasPendientes").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=2&areaSolicitante=1',datatype: "xml" });
			$("#gridEvidenciasPendientes").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasPendientes');
	}
	
	/*
	*Funcion que carga el grid de evidencias concluidas
	*/
	function cargaGridEvidenciasConcluidas(){
		if(firstGridEvidenciasConcluidas == true){
			
			jQuery("#gridEvidenciasConcluidas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=3&areaSolicitante=1', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Cadena de custodia','Número de evidencia','Nombre del solicitante','Perito responsable','Fecha inicio de préstamo','Fecha fin de préstamo','Fecha de cierre' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'CadenaCustodia',index:'5', width:150},
				           	{name:'NumeroEvidencia',index:'6', width:150},
							{name:'PeritoResponsable',index:'7', width:150},
				           	{name:'FechaInicioPrestamo',index:'8', width:40}, 
				           	{name:'FechaFinPrestamo',index:'9', width:40}, 
							{name:'FechaCierre',index:'10', width:40}
						],
				pager: jQuery('#pagerEvidenciasConcluidas'),
				rowNum:10,
				rowList:[10,20,30],
				width:'100%',
				autowidth: true,
				//autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridEvidenciasConcluidas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasConcluidas=false;
		}
		else{
			jQuery("#gridEvidenciasConcluidas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=3&areaSolicitante=1',datatype: "xml" });
			$("#gridEvidenciasConcluidas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasConcluidas');
	}


	/*
	*Funcion que carga el grid de solicitudes periciales en proceso
	*/
	function cargaGridSolicitudesPericialesEnProceso(){

		if(firstGridSolicitudesPericialesEnProceso == true){
			
			jQuery("#gridSolicitudesPericialesEnProceso").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesEnProceso.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2', 
				datatype: "xml", 
				colNames:['Folio','Número de Caso','Número de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha Límite','Fecha Última Modificación','Fecha Solicitud' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:200},
				           	{name:'NumeroExpediente',index:'3', width:200},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'EspecialidadPerito',index:'5', width:150}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaUltModif',index:'7', width:150, hidden:true},
							{name:'FechaSolicitud',index:'8', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesPericialesEnProceso'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					mostrarSolPericial(rowid);
				}
			}).navGrid('#pagerGridSolicitudesPericialesEnProceso',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesPericialesEnProceso=false;
		}
		else{
			jQuery("#gridSolicitudesPericialesEnProceso").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesEnProceso.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2',datatype: "xml" });
			$("#gridSolicitudesPericialesEnProceso").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesPericialesEnProceso');
	}


	/*
	*Funcion que carga el grid de solicitudes periciales terminadas
	*/
	function cargaGridSolicitudesPericialesTerminadas(){

		if(firstGridSolicitudesPericialesTerminadas == true){
			
			jQuery("#gridSolicitudesPericialesTerminadas").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesTerminadas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2', 
				datatype: "xml", 
				colNames:['Folio','Número de Caso','Número de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha Límite','Fecha Cierre','Fecha Solicitud' ], 
				colModel:[ 	{name:'Folio',index:'1', width:130},
							{name:'NumeroCaso',index:'2', width:200},
				           	{name:'NumeroExpediente',index:'3', width:200},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'EspecialidadPerito',index:'5', width:150}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaCierre',index:'7', width:150},
							{name:'FechaSolicitud',index:'8', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesPericialesTerminadas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesPericialesTerminadas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesPericialesTerminadas=false;
		}
		else{
			jQuery("#gridSolicitudesPericialesTerminadas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesTerminadas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=2',datatype: "xml" });
			$("#gridSolicitudesPericialesTerminadas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesPericialesTerminadas');
	}
		
		
		function gridRecibirDesignaciones(){
				jQuery("#gridRecicbirDesignaciones").jqGrid({ 
				url:'<%= request.getContextPath()%>/recibirDesignaciones.do', 				 
				datatype: "xml", 
				colNames:['Área origen','Caso','Expediente','Delito(s)','Detenido','Defensor','Fecha - Hora de Designación','Numero Expediente Id'], 
				colModel:[ 
							{name:'origen',index:'2001', width:150,align:"center"},
				           	{name:'caso',index:'2002', width:150,align:"center"},
				           	{name:'expediente',index:'2003', width:150,align:"center"},
				        	{name:'delitos',index:'2004', width:130,align:"center"},
							{name:'Detenido',index:'2005', width:120,align:"center"},
							{name:'defensor',index:'2006', width:190,align:"center"},
							{name:'FHDesignacion',index:'2007', width:220,align:"center"},
							{name:'numeroExpedienteId',index:'8', width:220,align:"center", hidden:true}
						],
				pager: jQuery('#pagerGridRecicbirDesignaciones'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				//width:500,
				autoheight:true,
				height:600,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				onSelectRow: function(rowid){
						var ret = jQuery("#gridRecicbirDesignaciones").jqGrid('getRowData',rowid); 
						idDocumento = rowid;
						varIdNumExp = ret.numeroExpedienteId;
						var asignarDefensor = 0;
						var tipoSolicitud = 4;
						if(ret.defensor == " Sin Defensor "){
							asignarDefensor = 1;
						}
						gridToReload = "#gridRecicbirDesignaciones";
						mostrarDetalleAsignacion(3, ret.caso, ret.expediente, rowid, varIdNumExp, asignarDefensor);
				}
			}).navGrid('#pagerGridRecicbirDesignaciones',{edit:false,add:false,del:false});
	
			$("#gridRecicbirDesignaciones").trigger("reloadGrid");			
			//muestra este grid y oculta los demas
			ocultaMuestraGrids('divGridRecicbirDesignaciones');
			$("#gview_gridRecicbirDesignaciones .ui-jqgrid-bdiv").css('height', '500px');
	
	}

		function mostrarDetalle(rowid){							
				$.newWindow({id:"iframewindowDetalleDesignaciones", statusBar: true, posx:200,posy:50,width:1000,height:400,title:"Detalle", type:"iframe"});
			    $.updateWindowContent("iframewindowDetalleDesignaciones",'<iframe src="<%= request.getContextPath() %>/detalleDesignaciones.do" width="1000" height="400" />');
		}
		
		function gridAudiencias(){
			///SolicitudesNoAtendidas.do
			jQuery("#gridAudiencias").jqGrid({ 
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
				
				pager: jQuery('#pagerGridAudiencias'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					var ret = jQuery("#gridAudiencias").jqGrid('getRowData',rowid);
					caso = ret.caso;
					detalleAudiencia(rowid, caso);
				}
				
			}).navGrid('#pagerGridAudiencias',{edit:false,add:false,del:false});
				ocultaMuestraGrids('divGridAudiencias');
				jQuery("#gridAudiencias").trigger('reloadGrid');
				$("#gview_gridAudiencias .ui-jqgrid-bdiv").css('height', '450px');
			  }
		  

		function gridSolAvisosDetencion(){
			///SolicitudesNoAtendidas.do
			jQuery("#gridDetalleSolAvisosDetencion").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesNoAtendidas.do', 
				datatype: "xml", 
				colNames:['Folio','Caso','Imputado:','Delito(s)','Agencia del MP','Fecha-Hora de detención','Fecha-Hora de aviso'], 
				colModel:[ 	{name:'folio',index:'2008', width:150},
				           	{name:'caso',index:'2002', width:180},
				           	{name:'imputado',index:'2009', width:150},
				           	{name:'delito',index:'2004', width:200},
				           	{name:'mp',index:'2010', width:150},
				           	{name:'fechaHoraDetencion',index:'2011', width:150},
				           	{name:'fechaHoraAviso',index:'2012', width:150}
				           
				           	
						],
				pager: jQuery('#pagerDetalleSolAvisosDetencion'),
				rowNum:30,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					idDocumento = rowid;
					var ret = $("#gridDetalleSolAvisosDetencion").jqGrid('getRowData',rowid); 
					//cargaIdSolicitud(rowid);
					//varIdNumExp=ret.numeroExpedienteId;
					numeroCaso=ret.caso;
					alertDinamicoDosBotones("¿Dar seguimiento?", "avisosDetencion");	
					//activaConfirm("avisosDetencion");
				}
				
			}).navGrid('#pagerDetalleSolAvisosDetencion',{edit:false,add:false,del:false});
			ocultaMuestraGrids('divGridAvisosDetencion');
			$('#gridDetalleSolAvisosDetencion').trigger('reloadGrid');
			$("#gview_gridDetalleSolAvisosDetencion .ui-jqgrid-bdiv").css('height', '450px');
			  }
		

		function cargaGridFuncionarios(){
		
			if(firstGridFuncionarios == true){
				//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
				firstGridFuncionarios = false;	
			
			  	jQuery("#gridDefensores").jqGrid({ 
				    url: '<%= request.getContextPath()%>/consultarDefensoresActivos.do?index=1',
					datatype: "xml", 
					colNames:['Tipo de Defensa','Nombre','Especialidad'], 
					colModel:[ {name:'TipoDefensa',index:'1', width:350, align:"center"},
							   {name:'nombre',index:'2', width:450, align:"center"},
							   {name:'especialidad',index:'3', width:350, align:"center"}
							  					     
							    ],
				     rowNum:20, 
				     rowList:[10,20,30],
				     autowidth: true,
					 width:"100%",
					 height:440,
				     pager: '#pagerGridDefensores',
				     sortname: '2',
				     viewrecords: true,
				     gridview: true, 
				     caption: "Lista de Defensores", 
				     sortorder: "desc"	,
				     ondblClickRow: function (rowid){
					     muestraConsultaFuncionario(rowid);
				     }		     			     
				    
				  }).navGrid('#pagerGridDefensores',{edit:false,add:false,del:false});
		
			} else {
				jQuery("#gridEvidenciasNuevas").jqGrid('setGridParam', {url: '<%= request.getContextPath()%>/consultarDefensoresActivos.do?index=1', datatype: "xml"});
				$("#gridEvidenciasNuevas").trigger("reloadGrid");			
			}

			$("#gview_gridDefensores .ui-jqgrid-bdiv").css('height', '400px');
			ocultaMuestraGrids('gridDefensores');
			   
		}

		function cargaExpediente(numExp){
			varexp = numExp;
		}
		
		
		function cargaIdSolicitud(rowid){
			varSol = rowid;
		}	
		
		function activaConfirm(id) {					
				switch(id){
					case "avisosDetencion":
						//actualizaEstatusAvisoDetencion();
						generarDesignacionDefensor(3);
						gridToReload = "#gridDetalleSolAvisosDetencion";
						mostrarDetalleAsignacion(3, numeroCaso, varexp, varSol, varIdNumExp, 1);
						$('#gridDetalleSolAvisosDetencion').trigger('reloadGrid');
						break;						
					case "solAtencionTemprana":
						//actualizaEstatusSolicitud();
						generarDesignacionDefensor(1);
						gridToReload = "#divGridSolicitudAtencionTempranaGrid";
						mostrarDetalleAsignacion(3, numeroCaso, varexp, varSol, varIdNumExp, 1);
						$("#divGridSolicitudAtencionTempranaGrid").trigger("reloadGrid"); 
						break;
					case "solAsesorias":
						//actualizaEstatusSolicitud();
						generarDesignacionDefensor(1);
						gridToReload = "#gridAsesorias";
						mostrarDetalleAsignacion(3, numeroCaso, varexp, varSol, varIdNumExp, 1,"Asesoria Legal: ");
						 $("#gridAsesorias").trigger("reloadGrid"); 
						 break;
					case "solicitudPoderJudicial":
						//actualizaEstatusSolicitud();
						generarDesignacionDefensor(2);
						gridToReload = "#gridSolicitudPoderJudicial";
						mostrarDetalleAsignacion(3, numeroCaso, varexp, varSol, varIdNumExp, 1);
						 $("#gridSolicitudPoderJudicial").trigger("reloadGrid");
						break;
						
				}
			}
		
		
		function generarDesignacionDefensor(caso){
			
			var param = 'caso='+caso+"&";
			if(caso != 3){
				param += 'numSolicitud='+ varSol;
			}else{
				param += 'idDocumento='+ idDocumento;
			}
		   	$.ajax({
		   		type: 'POST', data: param, dataType: 'xml',
		   		url: '<%= request.getContextPath()%>/registrarAvisoDesignacion.do',
		   		async: false,
		   		success: function(xml){
	   				varSol = $(xml).find('documentoId').text();
		   			varexp = $(xml).find('expediente').find('numeroExpediente').text();
		   			varIdNumExp = $(xml).find('expediente').find('numeroExpedienteId').text();
		   		}
		   	});			
			
		}
		
	function actualizaEstatusSolicitud(){
		var param = 'numSolicitud='+ varSol;
	   	$.ajax({
	   		type: 'POST',
	   		url: '<%= request.getContextPath()%>/ActualizaEstatusSolicitud.do',
	   		data: param,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var option;
	   			$(xml).find('ocupacion').each(function(){
	   			$('#consultaVictima').append('<li value="' + $(this).find('gcNombre').text() +  '" title="'+ $(this).find('gcDescripcion').text() + '"  style="background:#99C"  >'+ $(this).find('gcDescripcion').text() + '</li>');
	   			});
	   		}
	   	});
	}	

	function actualizaEstatusAvisoDetencion(){
		var param = 'idDocumento='+ idDocumento;
	   	$.ajax({
	   		type: 'POST',
	   		url: '<%= request.getContextPath()%>/actualizaEstatusAvisoDetencion.do',
	   		data: param,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			cargaExpediente($(xml).find('avisoDetencionDTO').find('expedienteDTO').find('numeroExpediente').text());
	   		}
	   	});
	}	

	function menuDependienteEtapa(){
		var param = '';
	   	$.ajax({
	   		type: 'POST',
	   		url: '<%= request.getContextPath()%>/ConsultarExpedientesUsuarioArea.do',
	   		data: param,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var etapa;
	   			var i=0;
	   			$(xml).find('lista').find('expedienteDTOs').find('etapa').each(function(){
	   				etapa = $(this).find('idCampo').text(); 
		 			if(etapa==<%= EtapasExpediente.INTEGRACION.getValorId() %>){
		 			}
		 			if(etapa==<%= EtapasExpediente.TECNICA.getValorId() %>){
		 			}
		 			if(etapa==<%= EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId() %>){
		 			}                    
		 			if(etapa==<%= EtapasExpediente.EJECUCION.getValorId() %>){
	  		 			}
	   			});
	   		}
	   		
	   	});
	}	

		function asignaAtencionAbogado(tipo, exp, sol, numExpSol) {
			var params = "?tipo="+tipo;
			params += "&stNumEx="+exp;
			params += "&idAviso="+sol;
			params += "&numExpId="+numExpSol;
			var titulo="Exp: ";
			
			if(tipo==8){
				titulo="Asesoría: ";
				}
			$.newWindow({id:"iframewindowAsignaAtencionAbogado", statusBar: true, posx:200,posy:50,width:1000,height:400,title:titulo+exp, type:"iframe"});
		    $.updateWindowContent("iframewindowAsignaAtencionAbogado",'<iframe src="<%= request.getContextPath() %>/asignaAtencionAbogado.do'+params+'" width="1000" height="400" />');
		    		
		}		
		
	function mostrarDetalleAsignacion(tipoSolicitud, numeroCaso, numeroExpedienteSt, idDocumento, idNumeroExpediente, asignarDefensor, prefijoVentana) {
		var params = "?tipoSolicitud="+tipoSolicitud;
		params += "&idDocumento="+idDocumento;
		params += "&numeroExpedienteId="+idNumeroExpediente;
		params += "&numeroExpedienteSt="+numeroExpedienteSt;
		params += "&asignarDefensor="+asignarDefensor;
		var tituloVentana="";
		if(numeroCaso != null && numeroCaso != "" && numeroCaso != "-"){
			tituloVentana += "Caso: "+numeroCaso+" ";
		}
		if(prefijoVentana == undefined || prefijoVentana == null || prefijoVentana == ""){
			tituloVentana += "Expediente: ";
		}else{
			tituloVentana += prefijoVentana;
		}
		tituloVentana += numeroExpedienteSt;
		$.newWindow({id:"iframewindowAsignaAtencionAbogado", statusBar: true, posx:255,posy:111,width:1000,height:400,title:tituloVentana, type:"iframe"});
	    $.updateWindowContent("iframewindowAsignaAtencionAbogado",'<iframe src="<%= request.getContextPath() %>/mostrarDetalleATDEF.do'+params+'" width="1000" height="400" />');
	    		
	}	
		
		

	function detalleAudiencia(rowId, caso) {
		$.newWindow({id:"iframewindowDetalleAudiencia", statusBar: true, posx:200,posy:50,width:1000,height:400,title:" Caso:"+caso, type:"iframe"});
	    $.updateWindowContent("iframewindowDetalleAudiencia",'<iframe src="<%= request.getContextPath() %>/detalleAudienciaDefensoria.do?idAudiencia='+rowId+'" width="1000" height="400" />');
	}


	//Grid de asesoria
	function gridAsesoria(){
		jQuery("#gridAsesorias").jqGrid({ 
			url:'<%= request.getContextPath()%>/solicitudesNoAtendidasAsesoria.do', 
			datatype: "xml", 
			colNames:['Folio de Solicitud','Expediente','Interesado','Fecha de Solicitud'], 
			colModel:[ 	{name:'Folio de Solicitud',index:'2013', width:230,align:"center"},
			         	{name:'Expediente',index:'2003', width:230,align:"center"},
			           	{name:'interesado',index:'2016', width:230,align:"center"},
			          	{name:'FHSolicitud',index:'2014', width:230,align:"center"}
			           	
					],
			pager: jQuery('#pagGridAsesorias'),
			rowNum:25,
			rowList:[10,20,30,40,50,60],
			autowidth: true,
			//autoheight:true,
			sortname: '1',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
				cargaIdSolicitud(rowid.split("-")[0]);
				cargaExpediente(rowid.split("-")[1]);
				alertDinamicoDosBotones("¿Dar seguimiento?", "solAsesorias");	
				//activaConfirm("solAsesorias");				
			}			
		}).navGrid('#pagGridAsesorias',{edit:false,add:false,del:false});
		ocultaMuestraGrids('divGridAsesorias');
		$("#gview_gridAsesorias .ui-jqgrid-bdiv").css('height', '525px');
		jQuery("#gridAsesorias").trigger("reloadGrid");
	}
			
		function gridSolAtencionTemprana(){
			jQuery("#divGridSolicitudAtencionTempranaGrid").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesNoAtendidasAtencionTemprana.do', 
				datatype: "xml", 
				colNames:['Folio de Solicitud','Caso','Expediente','Para Quien se Sólicita: ','Detenido','Fecha y Hora de Solicitud'], 
				colModel:[ 	{name:'Folio de Solicitud',index:'2013', width:185,align:"center"},
				           	{name:'Caso',index:'2002', width:165,align:"center"},
				           	{name:'Expediente',index:'2003', width:185,align:"center"},
				           	{name:'ParaQuienseSolicita',index:'2016', width:215,align:"center"},
				           	{name:'Detenido',index:'2004', width:185,align:"center"},
				           	{name:'FHSolicitud',index:'2014', width:185,align:"center"}
				           	
						],
				pager: jQuery('#pagGridSolicitudAtencionTemprana'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				sortname: '2013',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					cargaIdSolicitud(rowid.split("-")[0]);
					cargaExpediente(rowid.split("-")[1]);
					var ret = $("#divGridSolicitudAtencionTempranaGrid").jqGrid('getRowData',rowid);
					numeroCaso = ret.Caso;
					alertDinamicoDosBotones("¿Dar seguimiento?", "solAtencionTemprana");	
					//activaConfirm("solAtencionTemprana");
				}
				
			}).navGrid('#pagGridSolicitudAtencionTemprana',{edit:false,add:false,del:false});
			ocultaMuestraGrids('divGridSolicitudAtencionTempranaDiv');
			jQuery("#divGridSolicitudAtencionTempranaGrid").trigger("reloadGrid");
			$("#gview_divGridSolicitudAtencionTempranaGrid .ui-jqgrid-bdiv").css('height', '450px');
			
		}
		
		function gridSolPoderJudicial(){
			
			jQuery("#gridSolicitudPoderJudicial").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesNoAtendidasPoderJudicial.do', 
				datatype: "xml", 
				colNames:['Folio','Caso','Imputado ','Delito(s)','Tipo de Audiencia','Fecha-Hora de audiencia','Detenido','Fecha-Hora solicitud'], 
				colModel:[ 	{name:'folio',index:'2013', width:130,align:"center"},
				           	{name:'caso',index:'2002', width:120,align:"center"},
				           	{name:'imputado',index:'2009', width:150,align:"center"},
				           	{name:'Delitos',index:'2004', width:130,align:"center"},
				           	{name:'Tipo de Audiencia',index:'2017', width:150,align:"center"},
				           	{name:'fechaHoraAudiencia',index:'2018', width:150,align:"center"},
				           	{name:'detenido',index:'2005', width:130,align:"center"},
				         	{name:'fechaHoraSolicitud',index:'2014', width:160,align:"center"}
				           	
						],
				pager: jQuery('#pagGridSolicitudPoderJudicial'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				viewrecords: true,
				onresize_end: function () { $("#pagGridSolicitudPoderJudicial").setGridWidth($("#mainContent").width() - 5, true); 
				},
				ondblClickRow: function(rowid) {					
					cargaIdSolicitud(rowid);
					//cargaIdSolicitud(rowid.split("-")[0]);
					//cargaExpediente(rowid.split("-")[1]);
					//alert(varexp+varSol);
					var ret = $("#gridSolicitudPoderJudicial").jqGrid('getRowData',rowid);
					numeroCaso = ret.caso;
					alertDinamicoDosBotones("¿Dar seguimiento?", "solicitudPoderJudicial");	
					//activaConfirm("solicitudPoderJudicial");
				}
				
			}).navGrid('#pagGridSolicitudPoderJudicial',{edit:false,add:false,del:false});
			ocultaMuestraGrids('divGridSolicitudPoderJudicial');
			jQuery("#gridSolicitudPoderJudicial").trigger("reloadGrid");
			$("#gview_gridSolicitudPoderJudicial .ui-jqgrid-bdiv").css('height', '450px');
		}

		function cerrarEtapa(documentoId,numExpediente){

			$.closeWindow('iframewindowReasignarDefensor');
			varSol=documentoId;
			varexp=numExpediente;
			//varIdNumExp=;
			asignaAtencionAbogado();
			
             }
		
		 function cargaEstadoExpediente(){
		    	//alert("cargarDefensor");
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/ConsultarListaEstadoExpediente.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		    			$(xml).find('catEstadoExpediente').each(function(){
		    				$('#etapaOptionDEATT').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		    			});
		    		}
		    	});
		    }
		 
	    function boqueaEtapa(){
	    	var checkado=$('input[name=checkTodosExpedientes]').is(':checked');
	    	if(checkado==true){
	    		$('#etapaOptionDEATT').attr('disabled', 'disabled');

		    }else{
		    	$('#etapaOptionDEATT').removeAttr('disabled'); 
				}
	    }


	   function cerrarVentana(){
		   $windowCloseButton.click();
		   $(gridToReload).trigger("reloadGrid");
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


<body link="#ffffcc" vlink="#ffffcc" alink="#ffffcc">

	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div>

	<!--Comienza layout west-->
	<div class="ui-layout-west">
		<div class="header">&nbsp;</div>	
		<div class="content">
			<!--Comienza acordeon Principal-->
			<div id="accordionmenuprincipal">
				<!--Tab de expedientes-->
				<h3><a id="funcionarios" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes por Defensor</a></h3>
					<div>
						<ul id="seccion1tree" class="filetree">
						</ul>	
					</div>
				<!--Tab de Avisos de detencion-->
				<h3><a href="#" id="avisosDetencion"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Avisos de personas detenidas</a></h3>
					<div>
					</div>
				<!--Tab de Solicitudes de defensor-->
				<h3><a href="#" id="ligSolicitudesDefensor"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes de Defensor Registradas</a></h3>
					<div>
						<ul id="seccion3tree" class="filetree">	
							<li class="closed" id="ateTemprana"><span id="solicitudPoderJudicial" class="folder" onmousedown="">Poder Judicial</span>
								<ul id="podJudicial">
								</ul>
							</li>		
							<li class="closed" id="ateTemprana"><span id="solicitudAtencionTemprana" class="folder">Atenci&oacute;n Temprana</span>
								<ul id="atTemprana">
								</ul>
							</li>	
							<li class="closed" ><span id="asesorias" class="folder" >Asesorías</span>							
								<ul>
								</ul>
							</li>					
						</ul>
					</div>
					
				<!--Tab de designaciones-->
				<h3><a href="#" id="adesignaciones"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes sin Atender</a></h3>
					<div>
						<!--<ul id="seccion3tree" class="filetree">								
							<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
								<tr>
								   <td width="100%" id=""><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><span id="consultaFuncionario">Consultar Funcionario</span></td>
								</tr>
								<tr>
								   <td width="100%" id=""><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><span id="nuevoFuncionario">Ingresar Funcionario</span></td>
								</tr>
							</table>							
						</ul>
					--></div>
					
				<!--Tab de audiencia-->
				<h3><a href="#" id="ligAudiencia"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Audiencias</a></h3>
					<div>
					</div>
					
				<!--Tab de servicios periciales	-->
				<h3><a href="#" id="ligServiciosPericiales0"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Servicios Periciales</a></h3>
					<div>
				
					<ul id="seccion2tree" class="filetree">
							<li class="closed" ><span class="folder">Solicitud</span>
								<ul>
									<li class="closed" ><span class="folder"><a id="ligServiciosPericiales">Pericial</a></span>
										<ul>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="pericialesNoAtendida" onclick="cargaGridSolicitudesPericialesNoAtendidas();">Nuevas</a></span></li>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><a id="pericialesEnProceso" onclick="cargaGridSolicitudesPericialesEnProceso();">Pendientes</a></span></li>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><a id="pericialesTerminada" onclick="cargaGridSolicitudesPericialesTerminadas();">Concluidas</a></span></li>
										</ul>
									</li>
									<li class="closed" ><span class="folder"><a id="ligServiciosPericialesEvidencia">Evidencia</a></span>
										<ul>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><a id="evidenciaNueva" onclick="cargaGridEvidenciasNuevas();">Nuevas</a></span></li>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><a id="evidenciaPendiente" onclick="cargaGridEvidenciasPendientes();">Pendientes</a></span></li>
										   <li><span><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><a id="evidenciaConcluida" onclick="cargaGridEvidenciasConcluidas();">Concluidas</a></span></li>
										</ul>
									</li>
								</ul>	
							</li>
					</ul>
				</div>
				<h3>
						<a id="conciliacionMedicion" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" width="15" height="15">&nbsp;Queja Ciudadanas</a>
					</h3>
					<div>
						<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer">
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="Queja" onclick="muestraGridQuejaPendiente();">Pendientes</a></td>
							</tr>
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="QuejaConcluida" onclick="muestraGridQuejaConcluida();">Concluidas</a></td>
							</tr>
						</table>	
					</div>
			<!--Tab de reportes y graficas-->
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
		<!--Termina acordeon principal-->
	</div>
	<!--Termina div class="content"-->
</div>
<!--Termina layout west-->

<!--Comienza layout east-->
<div class="ui-layout-east">

	<div class="header">Bienvenido</div>
	
	<!--Comienza div class="content"-->
	<div class="content">
	
		<!--Comienza acordeon principal derecho-->
		<div id="accordionmenuderprincipal">
		
			<!--Tab datos personales-->
			<h4><a href="#">Datos Personales</a></h4>
				<div>			
					<center>
						<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
					</center>
				</div>
			
			<!--Tab Calendario-->
			<!-- <h4><a href="#">Calendario</a></h4>
				<div>
					<center>
						<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
					</center>
				</div>
			-->
			<!--Tab Agenda-->
			<h6><a href="#">Agenda</a></h6>
				<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
					</center>
					<br/>
					<!--  <ul id="seccion1tree" class="filetree">
						<li class="closed"><span class="file" id="consultar-eventos">Consultar Eventos</span>
							
						</li>
						
						
					</ul>-->	
				</div>
			
			<!--Tab Consultar leyes y códigos-->
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
			
			<!--Tab Chat-->
			<!--h1><a href="#">Chat</a></h1>
				<div>
					<div id="dialogoChat" title="Chat" align="center">
						<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
					</div>
					<center>
						<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
					</center>
				</div>
			
			<!--Tab Clima-->
			<!--h1><a href="#">Clima</a></h1>
				<div align="left">
					<div align="left" id="test"></div>
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
		<!--Termina acordeon principal derecho-->
		
	</div>
	<!--Termina div class="content"-->
	
</div>
<!--Termina layout east-->

<!--Comienza layout north-->
<div class="ui-layout-north">
	
	<!--Comienza div class="content"-->
	<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">COORDINACION DE DEFENSORIA</TD>
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
	<!--Termina div class="content"-->
	
	<!--Comienza barra de herramientas superior	-->
	<ul class="toolbar">
		<div id="menu_head">
			<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			<li id="vacacionesIncapacidades">Vacaciones e Incapacidades&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_agendawri.png" width="20" height="15"--></li>
			<li id="tbarBtnQuejaCiudadana">Recibir queja&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_errorinfo.png" width="15" height="16"--></li>
			<li id=consultaFuncionario>Consultar funcionario&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca1.png" width="15" height="16"--></li>
			<li id="nuevoFuncionario">Nuevo funcionario&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_basemas.png" width="15" height="16"--></li>
			<li id="reasignarDefensor">Reasignar Defensor&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_basemas.png" width="15" height="16"--></li>
			<li id="cobroAreancceles">Cobro Aranceles&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_basemas.png" width="15" height="16"--></li>
			<li id="multasYSanciones">Multas y Sanciones&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_basemas.png" width="15" height="16"--></li>
			<li id="inspecciones">Inspecciones&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_basemas.png" width="15" height="16"--></li>
		</div>
		<div id="menu_config">
			<li id="buscarCaso">Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
			<li id="buscarExpediente">Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
			<li id="verde">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
		</div>
	</ul>
	<!--Termina barra de herramientas superior	-->
	
</div>

	<!--Comienza main -->
	<div id="mainContent">
		
		<!--Comienza layout-center-->
		<div class="ui-layout-center">
			
			<div class="ui-layout-content">

				<div class="ui-layout-north">
		
					<div id="divEtapaExpediente">
						<table border="0" cellspacing="0" cellpadding="0" align="center">
					    <tr>
					    	<td>
					      		<div id="divGridSubordinados">
									<table id="gridSubordinados"></table>
									<div id="pagGridSubordinados"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="divGridExpedientesSubordinados">
									<table id="gridExpedientesSubordinados"></table>
									<div id="pagGridExpedientesSubordinados"></div>
								</div>
								
								
							</td>
						</tr>
					    </table>
					</div>
				
					<div id="divGridQuejaPendiente" >
						<table id="gridQuejaPendiente" ></table>
						<div id="paginadorgridQuejaPendiente"></div>
					</div>
					
					<div id="divGridQuejaConcluida" >
						<table id="gridQuejaConcluida" ></table>
						<div id="paginadorgridQuejaConcluida"></div>
					</div>

					<!-- Espacio para el grid con las solicitudes pericialies no atendidas-->
					<div id="divGridSolicitudesPericialesNoAtendidas">
						<table id="gridSolicitudesPericialesNoAtendidas"></table>
						<div id="pagerGridSolicitudesPericialesNoAtendidas"></div>
					</div>
					
					<!--Espacio para el grid con las solicitudes periciales en proceso-->
					<div id="divGridSolicitudesPericialesEnProceso">
						<table id="gridSolicitudesPericialesEnProceso"></table>
						<div id="pagerGridSolicitudesPericialesEnProceso"></div>
					</div>
					<!--Espacio para el grid con las solicitudes periciales terminadas o cerradas-->
					<div id="divGridSolicitudesPericialesTerminadas">
						<table id="gridSolicitudesPericialesTerminadas"></table>
						<div id="pagerGridSolicitudesPericialesTerminadas"></div>
					</div>					
					
					<div id="divGridEvidenciasNuevas">
						<table id="gridEvidenciasNuevas"></table>
						<div id="pagerGridEvidenciasNuevas"></div>
					</div>					
					<div id="divGridEvidenciasPendientes">
						<table id="gridEvidenciasPendientes"></table>
						<div id="pagerGridEvidenciasPendientes"></div>
					</div>					
					<div id="divGridEvidenciasConcluidas">
						<table id="gridEvidenciasConcluidas"></table>
						<div id="pagerGridEvidenciasConcluidas"></div>
					</div>					
					
					
						<div id="divGridRecicbirDesignaciones">
						<table id="gridRecicbirDesignaciones"></table>
						<div id="pagerGridRecicbirDesignaciones"></div>
					</div>				
					
					<div id="divGridDefensores">
						<table id="gridDefensores"></table>
						<div id="pagerGridDefensores"></div>
					</div>					
					
					
					<div id="divGridAvisosDetencion">
						<table id="gridDetalleSolAvisosDetencion" align="center" ></table>
						<div id="pagerDetalleSolAvisosDetencion"></div>
					</div>
	
					<div id="divGridSolicitudAtencionTempranaDiv">
						<table id="divGridSolicitudAtencionTempranaGrid"></table>
						<div id="pagGridSolicitudAtencionTemprana"></div>
						
					</div>
					
					<div id="divGridSolicitudPoderJudicial">
						<table id="gridSolicitudPoderJudicial"></table>
						<div id="pagGridSolicitudPoderJudicial"></div>
						
					</div>
						
					<div id="divDetalleEvidencia">
						<table id="gridDetalleEvidenciasNuevas"></table>
						<div id="pagerGridDetalleEvidenciasNuevas"></div>
					</div>
						<!--Espacio para el grid de audiencias-->
					<div id="divGridAudiencias">
						<table id="gridAudiencias"></table>
						<div id="pagerGridAudiencias"></div>
					</div>	
					<!--Espacio para el grid de asesorias-->
					<div id="divGridAsesorias">
					<table id="gridAsesorias"></table>
					<div id="pagGridAsesorias"></div>
					
				</div>
				
			</div>
			
		</div>
		<!--Termina layout-center-->
	</div>
	<!--Comienza main -->	
	
	
	<!--Comienza layout-south-->
	
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
	<!--Termina layout-south-->
	
<script type="text/javascript">
	
	//$("#accordionmenuderprincipal").accordion({ fillSpace: true});	
</script>
	
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