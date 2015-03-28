<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--COMIENZA CSS DEL DOCUMENTO-->
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--css para el estilos de jquery-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	
	<!--css para estilo de los arboles-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<!--estilo ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
	<!--estilo del grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
<!--COMIENZAN SCRIPTS-->
	
	<!--jquery-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	
	<!--para controlar las ventanas-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	
	<!--para creacion de arboles-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}


	//Variable para controlar los id´s de las ventanas
	//de solicitud de transcripcion de audiencia
	var idWindowTranscripcionAdudiencia  = 1;
	var idWindowImageViewer              = 1;
	
	//por default es la primera vez que entra
	var primeraTranscripcion="true";
	
	var outerLayout, innerLayout;
	
	var reloadGridFecha = false;
	
	var validaFecha = false;

	var audienciaID;

	var expedienteID;

	var numeroCarpeta;
	var nombreSentenciado;
	var global=0;
	
	var varparaEncargadoSala=false;
	
	var documentoID="";
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
		$("#seccion1treePJENC").treeview();
		//crea el arbol de eventos
		$("#seccion2treePJENC").treeview();
		//crea el arbol de casos
		$("#seccion3treePJENC").treeview();
		//Acordeon Sentencias
		$("#seccion6treePJENC").treeview();
		//Acordeon medidas alternativas
		$("#seccion7treePJENC").treeview();
		//agrega el evento generar documento 
		$("#generarDocumento").click(generarDocumentoView);
		//agreaga el evento para crear la agenda
		$("#controlAgenda").click(creaAgenda);
		//Carga el arbol de casos
		//cargaCasosPJENC();
		$('#casosPJENC').click(agregaExpediente);

		$('#resolutivos').click(gridResolutivaAudiencia);

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
		
		
		
		cargaCarpetadePreliberacion();	
		//Carga el grid  con las audiencias del dia
		//cargaGridDiaPJENC();
	
			//oculta el grid de expedientes
			$('#divGridOtrosPJENC').hide();

			//Oculta grid solicitudes
			$("#divGridSolicitudesRecursoPJENC").hide();

		
	
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


			//cargaGridCarpetaEjecucion();
			cargaGridSentencias('<%=EstatusExpediente.ABIERTO.getValorId()%>');
			
			//Carga el reloj
			muestraGadgets();
						
	});
	//FIN ON READY
	
	

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
		,	onresize_end:			function () { $("#gridAudienciasPJJU").setGridWidth($("#mainContent").width() - 5, true); }
		}
	};
	/////////////////////////////////////////////// COMIENZA FUNCIONALIDAD COMUN //////////////////////////////////////////////////////////////////
	/*
	*Funcion que abre el visor de audiencias
	*(Por el momento no acarrea el ID solo abre el visor) 
	*/
	function mostrarVisorAudienciaPJENC(rowID){
		var newVentana = "iframewindowVisorAudiencia" + global;
		
		$.newWindow({id:newVentana, statusBar: true, posx:255,posy:111,width:1140,height:450,title:"Visor de Audiencia", type:"iframe"});
    	$.updateWindowContent(newVentana,'<iframe src="<%=request.getContextPath()%>/visorAudienciaPJENC.do?idEvento=' + rowID +'" width="1140" height="450" />');
    	
    	global = global + 1;		
	}

	/*
	*Funcion que abre el visor de recurso
	*(Por el momento no acarrea el ID solo abre el visor) 
	*/
	function mostrarVisorRecursoPJENC(rowID){
		$.newWindow({id:"iframewindowVisorRecurso", statusBar: true, posx:255,posy:111,width:1000,height:350,title:"Visor de Recurso", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorRecurso",'<iframe src="<%=request.getContextPath()%>/visorRecursoPJENC.do?idEvento=' + rowID +'" width="1000" height="350" />');
	}
	/*
	*Funcion que llama a la funcionalidad para crear la agenda 
	*/
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();
		}


	/*
	*Funcion que llama a la funcionalidad para generar un documento 
	*/
	function generarDocumentoView() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/generarDocumento.do" width="1140" height="400" />');
	    $("#" +"iframewindowGenerarDocumento"+ " .window-maximizeButton").click();		
	}	
	
	
	/*
	*Funcion que llama a la funcionalidad para generar un visualizador de imagen 
	*/
	function generaVisorGraficaView() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="400" />');
	    		
	}	
	
	/*
	*Funcion que agrega los casos dinamicamente 
	*/

//	function cargaCasosPJENC(){
//    	var branches = "";
//    	$.ajax({
//    		type: 'POST',
 //   		url: '<%= request.getContextPath()%>/BusquedaInicialCaso.do',
//    		data: '',
 //   		dataType: 'xml',
 //   		async: true,
  //  		success: function(xml){
  //  			var branches = "";
	//			$(xml).find('caso').each(function(){
	//				var casoId = $(this).find('casoId').text();
	//				var numeroGralCaso = $(this).find('numeroGeneralCaso').text();
					// 				branches ="<ul><li class='closed' id='" + casoId + "CASO' onclick='agregaExpediente(" + casoId + ")'><span class='folder'>" + numeroGralCaso + "</span><ul></ul></li></ul>";
	//				var casosPJENC = $(branches).appendTo("#casosPJENC");
	//				$("#seccion3treePJENC").treeview({
    //					add: casosPJENC
    //				});
   // 			});
   // 		}
   // 		
   // 	});
 //   }

	//variable que indica si el caso esta abierto
	var casoAbierto = Array();

	/*
	*Funcion que agrega los expedientes dinamicamente 
	*al arbol de casos, de acuerdo al caso seleccionado
	*/
	function agregaExpediente (idCaso) {	
				
		if (casoAbierto[idCaso ] != true) {
			$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/BusquedaCaso.do',
	    		data: 'idCaso=' + idCaso,
	    		dataType: 'xml',
	    		async: true,
	    		success: function(xml){
	    			$(xml).find('expediente').each(function(){
	    				//var branches = $("<ul><li id='" + $(this).find('expedienteId').text() + "EXP' onclick='verEventosPorExpediente(" + $(this).find('expedienteId').text() + ",\"\")'><span class='file'>" + $(this).find('numeroExpediente').text() + "</span><ul></ul></li></ul>").appendTo("#" + idCaso + "CASO");
	    				var branches = $("<ul><li id='" + $(this).find('expedienteId').text() + "EXP' onclick=''><span class='file'>" + $(this).find('numeroExpediente').text() + "</span><ul></ul></li></ul>").appendTo("#" + idCaso + "CASO");
	    				$("#" + idCaso + "CASO").treeview({
	    					add: branches
	    				});
		    		});
	    		}		    		
	    	});
		}
		casoAbierto[idCaso] = true;
	}
	
	/*
	*Funcion que recarga el grid de acuerdo a la consulta de un expediente
	*es decir, consulta el historico de eventos del expedienta seleccionado
	*/
	function verEventosPorExpediente(idExpediente){
		jQuery("#gridOtrosPJENC").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultaEventosPorExpediente.do?expedienteId='+idExpediente +'',datatype: "xml" });
		  $("#gridOtrosPJENC").trigger("reloadGrid");
		  $("#divGridAudienciasPJENC").hide();
		  $('#gridTranscripcionAudienciasPJENC').hide();
		  $("#divGridOtrosPJENC").show();
		  $('#divDetalleResolutivaAudiencia').hide();
		  $('#divGridPreliberacion').hide();

			////////
			$('#divGridSentenciasEnProcesoPJENC').hide();
		  
	}
	/*
	*Funcion que recarga el grid de acuerdo a la consulta al
	*seguimiento a eventos de tipo audiencia o tipo Recuso
	*/
	function recargarGridAudiencia(tipoBusqueda){
		jQuery("#gridAudienciasPJENC").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/darSeguimientoEventos.do?eventoTipo='+ tipoBusqueda +'',datatype: "xml" });
		$("#gridAudienciasPJENC").trigger("reloadGrid");
		$("#divGridOtrosPJENC").hide();
		$('#gridTranscripcionAudienciasPJENC').hide();
		$("#divGridAudienciasPJENC").show();
		$('#divDetalleResolutivaAudiencia').hide();
		$('#divGridPreliberacion').hide();

		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
	}
	/*
	*Funcion que recarga el grid de acuerdo a la consulta al
	*seguimiento a eventos de tipo audiencia o tipo Recuso
	*/
	function recargarGridOtras(tipoBusqueda){
		jQuery("#gridOtrosPJENC").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/darSeguimientoEventos.do?eventoTipo='+ tipoBusqueda +'',datatype: "xml" });
		$("#gridOtrosPJENC").trigger("reloadGrid");
		$("#divGridAudienciasPJENC").hide();
		$('#gridTranscripcionAudienciasPJENC').hide();
	    $("#divGridOtrosPJENC").show();
	    $('#divDetalleResolutivaAudiencia').hide();
	    $('#divGridPreliberacion').hide();

		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
	}

	/*
	*Funcion que carga el grid, por default con las audiencias del dia
	*/
	function cargaGridDiaPJENC(){
		
	jQuery("#gridAudienciasPJENC").jqGrid({ 
							url:'<%= request.getContextPath()%>/consultarAudienciasByTipoYEstatus.do',
							datatype: "xml", 
							colNames:['Número de Causa','Carpeta de Ejecución','Nombre Sentenciado',' Fecha de Audiencia','Sala'], 
							colModel:[ 	{name:'Causa',index:'expediente', width:228}, 
										{name:'Ejecucion',index:'Ejecucion', width:228}, 
										{name:'Sentenciado',index:'Sentenciado', width:228}, 
										{name:'horaAudiencia',index:'horaAudiencia', width:228},											
										{name:'sala',index:'sala', width:228}
																					
									],
							pager: jQuery('#paginadorAudienciasPJENC'),
							rowNum:10,
							rowList:[25,50,100],
							autowidth: true,
							height:450,
							sortname: 'expediente',
							viewrecords: true,
							sortorder: "desc",
							ondblClickRow: function(rowid) {								
								expedienteID=rowid.split("*-*")[1];	
								audienciaID=rowid.split("*-*")[0];
								
								var id2 = jQuery("#gridAudienciasPJENC").jqGrid('getGridParam','selrow');
								var ret2 = jQuery("#gridAudienciasPJENC").jqGrid('getRowData',id2);
				      
				                	
				                    numeroCarpeta= ret2.Ejecucion;
								visorACE(audienciaID);
								
																
									}
						}).navGrid('#paginadorAudienciasPJENC',{edit:false,add:false,del:false});	

	 					//$("#gview_gridAudienciasPJENC .ui-jqgrid-bdiv").css('height', '480px');

	 					$('#divGridAudienciasPJENC').show();
	 					$('#divGridSolicitudesRecursoPJENC').hide();
	 					$('#divGridOtrosPJENC').hide();
	 					$('#divGridAudienciasFechaPJENC').hide();
	 					$('#gridTranscripcionAudienciasPJENC').hide();
	 					$('#divDetalleResolutivaAudiencia').hide();
	 					$('#divGridPreliberacion').hide();

	 					////////
	 					$('#divGridSentenciasEnProcesoPJENC').hide();
	 					
	}	

	function cargaGridCarpetaEjecucion(){
	 
	  jQuery("#gridFechaPJENC").jqGrid({
		   url:'<%= request.getContextPath() %>/gridCarpetaEjecucionCarpetaEjecucion.do', 						
			datatype: "xml", 
			mtype: 'POST',						
			postData: {
				//fechaInicio: function()  {return fechaInicio;},
			      //     fechaFin: function()		{return fechaFin;}																										
			},
			colNames:['N&uacute;mero Caso','N&uacute;mero Causa','Carpeta Ejecución','Sentenciado','Fecha Creaci&oacute;n'], 
			colModel:[ {name:'Caso',index:'index1', width:380},
			           {name:'Causa',index:'index2', width:380},
			           {name:'Ejecución',index:'index3', width:380},
			           {name:'Sentenciado',index:'index4', width:380},
			           {name:'Fecha',index:'index5', width:380}
	            																											
					],
				autowidth: false,
				width:767, 
				pager: jQuery('#paginadorFechaPJENC'), 
				sortname: 'id', 
				rownumbers: false,	
				gridview: false, 
				viewrecords: true, 
				sortorder: "desc", 
				height: "60%",
				//caption:"Resultado de la B&uacute;squeda",
				ondblClickRow: function(rowid) {
				

					expedienteID=rowid.split("*-*")[0];	
					audienciaID=rowid.split("*-*")[1];
					var id2 = jQuery("#gridFechaPJENC").jqGrid('getGridParam','selrow');
					  
					
	                var ret2 = jQuery("#gridFechaPJENC").jqGrid('getRowData',id2);
	      			
	                
	                    numeroCarpeta=ret2.Causa;
						nombreSentenciado=ret2.Sentenciado;
	                   
						visorACEcarpetaEjecucion(expedienteID,audienciaID);
											} 
				}).navGrid('#paginadorFechaPJENC',{edit:false,add:false,del:false});
 				//$("#gridFechaPJENC").trigger("reloadGrid"); 
			  	$("#gview_gridFechaPJENC .ui-jqgrid-bdiv").css('height', '480px');
			  	$('#divGridAudienciasPJENC').hide();
				$('#divGridSolicitudesRecursoPJENC').hide();
				$('#divGridOtrosPJENC').hide();
				$('#gridTranscripcionAudienciasPJENC').hide();
				$('#divGridAudienciasFechaPJENC').show();
				$('#divDetalleResolutivaAudiencia').hide();
				$('#divGridPreliberacion').hide();

				////////
				$('#divGridSentenciasEnProcesoPJENC').hide();  
			  }			

			
		
	//Funcion que valida si los campos estan llenos al enviar 
	function validaCamposFecha() {

		if ($('#fechaInicio').val() == '' || $('#fechaFin').val() == '') {
			alert("Debes ingresar la fecha de inicio y fin de la consulta");
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
				alert("La fecha final debe de ser mayor a la fecha inicial");
			}
		}	
	}

	function modalFecha() {

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
		$("#busquedaFecha").dialog({
			autoOpen : true,
			modal : true,
			title : 'Buscar por Fecha',
			dialogClass : 'alert',
			position : [ 255, 140 ],
			width : 380,
			height : 260,
			maxWidth : 1000,
			buttons : {
				"Aceptar" : function() {

					validaCamposFecha();
					cargaGridFechaPJENC();

					if (validaFecha == false) {

					} else {
						$(this).dialog("close");
					}

				},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
			}
		});

	}

	/*
	 *Funcion que carga el grid,  de solicitudes --------VER. DUMMIE
	 */
	function cargaGridHistorico() {
		jQuery("#gridSolicitudesRecursoPJENC").jqGrid(
				{
					url : '<%= request.getContextPath()%>/gridHistoricoCarpetaEjecucion.do',
					datatype : "xml",
					colNames:['N&uacute;mero Carpeta'], 
					colModel:[ {name:'expediente',index:'expediente', width:380}, 
								],
					pager : jQuery('#pager2'),
					rowNum : 10,
					rowList : [25,50,100],
					autowidth : false,
					width : 767,
					sortname : 'expediente',
					viewrecords : true,
					sortorder : "desc",
					ondblClickRow : function(rowid) {
						expedienteID=rowid;

						var id2 = jQuery("#gridSolicitudesRecursoPJENC").jqGrid('getGridParam','selrow');
						  
						
		                var ret2 = jQuery("#gridSolicitudesRecursoPJENC").jqGrid('getRowData',id2);
		      			
		                
		                    numeroCarpeta=ret2.expediente;
						visorACE(rowid);
					}
				}).navGrid('#pager2', {edit : false,add : false,del : false
		});

		$("#gview_gridSolicitudesRecursoPJENC .ui-jqgrid-bdiv").css('height','480px');
		
		$('#divGridAudienciasPJENC').hide();
		$('#divGridOtrosPJENC').hide();
		$('#divGridAudienciasFechaPJENC').hide();
		$('#gridTranscripcionAudienciasPJENC').hide();
		$('#divGridSolicitudesRecursoPJENC').show();
		$('#divDetalleResolutivaAudiencia').hide();
		$('#divGridPreliberacion').hide();

		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
	}

	/**
	*Funcion que consulta las solicitudes de transcripciones de audiencia sin atender
	*/
	function cargaGridTranscripcionAudienciaPJENC(){

		//Si es la primera ves que se consulta, se crea el gird
		if(primeraTranscripcion == "true"){
			
			jQuery("#gridTranscripcionAudienciasPJENC").jqGrid(
					{ url:'<%= request.getContextPath() %>/consultarSolicitudesDeTranscripcionDeAudienciaPJENC.do', 						
						datatype: "xml",
						colNames:['No.de Causa','Fecha-Hora Solicitud', 'Institucion','Id. de Audiencia','No. de TOCA'], 
						colModel:[ 	{name:'numCausa',index:'numCausa', width:150}, 
									{name:'fechSol',index:'fechSol', width:100}, 
									{name:'institucion',index:'institucion', width:150},
									{name:'idAudiencia',index:'idAudiencia', width:150}, 
									{name:'numToca',index:'numToca', width:150},
								],
							autowidth: false,
							width:767,
							height: 450,
							pager: jQuery('#paginadorTranscripcionAudiencias'), 
							sortname: 'fechSol', 
							rownumbers: false,
							gridview: false, 
							viewrecords: true, 
							sortorder: "desc",
							loadComplete: function(){
								
								primeraTranscripcion="false";
							}, 
							caption:"Administrar solicitudes de transcripcion de Audiencia",
							ondblClickRow: function(rowid) {
								
								mostrarVentanaTranscripcionAdudiencia(rowid);
							} 
					}).navGrid('#paginadorTranscripcionAudiencias',{edit:false,add:false,del:false});
		}
		//Si no es la primera vez que se consulta, solo se recarga el grid		
		else{
			jQuery("#gridTranscripcionAudienciasPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/consultarSolicitudesDeTranscripcionDeAudienciaPJENC.do',datatype: "xml" });
			$("#gridTranscripcionAudienciasPJENC").trigger("reloadGrid");
		}

		$('#gridTranscripcionAudienciasPJENC').show();
		$('#divGridAudienciasPJENC').hide();
		$('#divGridOtrosPJENC').hide();
		$('#divGridAudienciasFechaPJENC').hide();
		$('#divGridSolicitudesRecursoPJENC').hide();
		$('#divDetalleResolutivaAudiencia').hide();
		$('#divGridPreliberacion').hide();

		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
	}

	//Funcion 
	function mostrarVentanaTranscripcionAdudiencia(rowid){

		idWindowTranscripcionAdudiencia++;
		$.newWindow({id:"iframewindowTranscripcionAdudiencia" + idWindowTranscripcionAdudiencia, statusBar: true, posx:255,posy:111,width:1000,height:600,title:"Solicitud de Transcripci&oacute;n de Audiencia", type:"iframe"});
		$.updateWindowContent("iframewindowTranscripcionAdudiencia" + idWindowTranscripcionAdudiencia,'<iframe src="<%= request.getContextPath() %>/transcripcionAdudiencia.do?idSolicitudTranscripcion='+ rowid+'" width="1000" height="620" />');
	}


	function gridResolutivaAudiencia(){
		
		$('#divDetalleResolutivaAudiencia').show();
		$('#gridTranscripcionAudienciasPJENC').hide();
		$('#divGridAudienciasPJENC').hide();
		$('#divGridOtrosPJENC').hide();
		$('#divGridAudienciasFechaPJENC').hide();
		$('#divGridSolicitudesRecursoPJENC').hide();
		$('#divGridPreliberacion').hide();
		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
		
		
		jQuery("#gridDetalleResolutivaAudiencia").jqGrid({ 
			url:'<%= request.getContextPath()%>/CargaTranscripcionResolutivos.do', 
			datatype: "xml", 
			colNames:['Identificador de la Audiencia','Fecha - Hora del Fin de la Audiencia','Número de Causa','Número de Toca' ], 
			colModel:[ 	{name:'Audiencia',index:'audiencia', width:25},
			           	{name:'FinAudiencia',index:'finAudiencia', width:15},
			           	{name:'nCausa',index:'nCausa', width:20},
			           	{name:'nToca',index:'nToca', width:20},
			           	
					],
			pager: jQuery('#paginadorDetalleResolutivaAudiencia'),
			rowNum:10,
			rowList:[25,50,100],
			width:767,
			height: 450,
			sortname: 'numeroExpediente',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {

				idAudiencia=rowid
				//alert(rowid);
				asignaTranscripcion();
				
				
			}
			
		}).navGrid('#paginadorDetalleResolutivaAudiencia',{edit:false,add:false,del:false});

		
		  }
	

	function asignaTranscripcion() {
		
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:25500,posy:111,width:400,height:400,title:" ", type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/asignaTranscripcion.do" width="400" height="400" />');
	    		
	}

	
	function visorACEcarpetaEjecucion() {
			
			$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:200,posy:50,width:400,height:400,title:"Numero de Carpeta: "+numeroCarpeta+" "+"Nombre Sentenciado"+nombreSentenciado, type:"iframe"});
		    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/visorAdministrarCarpetaDeEjecucionPJOAE.do" width="400" height="400" />');
		    $("#" +"iframewindowRestaurativa"+ " .window-maximizeButton").click();		
		}
	
function visorACE() {
		
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:200,posy:50,width:400,height:400,title:"Numero de Carpeta: "+numeroCarpeta, type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/visorAdministrarCarpetaDeEjecucionPJOAE.do" width="400" height="400" />');
	    $("#" +"iframewindowRestaurativa"+ " .window-maximizeButton").click();		
	}
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

	function actualizaGridResolutivaAudiencia() {
		
		 $("#gridDetalleResolutivaAudiencia").trigger("reloadGrid");
		}

	function cargaCarpetadePreliberacion() {
		
		$('#divGridPreliberacion').show();
		$('#divDetalleResolutivaAudiencia').hide();
		$('#gridTranscripcionAudienciasPJENC').hide();
		$('#divGridAudienciasPJENC').hide();
		$('#divGridOtrosPJENC').hide();
		$('#divGridAudienciasFechaPJENC').hide();
		$('#divGridSolicitudesRecursoPJENC').hide();
		////////
		$('#divGridSentenciasEnProcesoPJENC').hide();
		
		
	//Número de causa
	//- Número de carpeta de ejecución
	//- Solicitante
	//- Institución solicitante
	//- Sentenciado
	//- Fecha - hora de solicitud (Nota 1)
	
	jQuery("#gridPreliberacion").jqGrid({
		
		url:'<%= request.getContextPath()%>/consultarSolicitudesPorTipoYEstatus.do',
		datatype: "xml", 
		colNames:['Número de Causa','Carpeta de Ejecución','Solicitante','Institución Solicitante','Sentenciado','Fecha - Hora de Solicitud'], 
		colModel:[ 	{name:'expediente',index:'expediente', width:190}, 
					{name:'caracter',index:'caracter', width:190}, 
					{name:'tipoAudiencia',index:'tipoAudiencia', width:190}, 
					{name:'fechaAudiencia',index:'fechaAudiencia', width:190},	
					{name:'horaAudiencia',index:'horaAudiencia', width:190},											
					{name:'Solicitud',index:'Solicitud', width:190}
																
				],
		pager: jQuery('#paginadorGridPreliberacion'),
		rowNum:10,
		rowList:[25,50,100],
		height: 490,
		sortname: 'expediente',
		viewrecords: true,
		sortorder: "desc",
		ondblClickRow: function(rowid) {								
			audienciaID=rowid.split("*-*")[2];
			expedienteID=rowid.split("*-*")[1];	
			documentoID=rowid.split("*-*")[0];	
			var id2 = jQuery("#gridPreliberacion").jqGrid('getGridParam','selrow');				
			var ret2 = jQuery("#gridPreliberacion").jqGrid('getRowData',id2);
			numeroCarpeta=ret2.expediente;		
			visorACE(documentoID, expedienteID, audienciaID);
		}
		}).navGrid('#paginadorGridPreliberacion',{edit:false,add:false,del:false});	
	}

function actualizaGridsPadre(){
	$("#gridPreliberacion").trigger("reloadGrid");
	$("#gridAudienciasPJENC").trigger("reloadGrid");
	$("#gridFechaPJENC").trigger("reloadGrid");
	$("#gridSolicitudesRecursoPJENC").trigger("reloadGrid");
	

	
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

//*************************************************************FUNCIONALIDAD PARA SENTENCIAS*******************************************************************/

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
				//muestraOcultaGrids("sentencias");
		}
		else{
			jQuery("#gridSentenciasEnProcesoPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSentenciasPorEstatus.do?estadoSentencia='+estados+'',datatype: "xml" });
			$("#gridSentenciasEnProcesoPJENC").trigger("reloadGrid");
			//muestraOcultaGrids("sentencias");				  
		}

		$('#divGridAudienciasPJENC').hide();
		$('#divGridSolicitudesRecursoPJENC').hide();
		$('#divGridOtrosPJENC').hide();
		$('#gridTranscripcionAudienciasPJENC').hide();
		$('#divGridAudienciasFechaPJENC').show();
		$('#divDetalleResolutivaAudiencia').hide();
		$('#divGridPreliberacion').hide();
		////////
		$('#divGridSentenciasEnProcesoPJENC').show();  
			   
	}

function ocultarGrids(){

	$('#divGridSentenciasEnProcesoPJENC').show();

	$('#divDetalleResolutivaAudiencia').hide();
	$('#gridTranscripcionAudienciasPJENC').hide();
	$('#divGridAudienciasPJENC').hide();
	$('#divGridOtrosPJENC').hide();
	$('#divGridAudienciasFechaPJENC').hide();
	$('#divGridSolicitudesRecursoPJENC').hide();
	$('#divGridPreliberacion').hide();
	
}



//*********************************************************************SECCION PARA MEDIDAS ALTERNATIVAS*******************************************************/

	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*/
	function abreModalCausa(){
		
		$("#datoCausa").val("");
		$("#divCausa").dialog("open");
	  	$("#divCausa").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Administrar medidas alternativas por n&uacute;mero de carpeta de ejecución', 
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

	//Variable para controlar el id, de las ventanas
	var idWindowVisorMedidasAlternativasPJADE=1;

	/*
	*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarVentanaInvolucradosCausa(numeroCausa){

		idWindowVisorMedidasAlternativasPJADE++;
		
		idVentana="iframewindowVisorMedidasAlternativas"+idWindowVisorMedidasAlternativasPJADE;
		$.newWindow({id:"iframewindowVisorMedidasAlternativas"+idWindowVisorMedidasAlternativasPJADE, statusBar: true, posx:255,posy:111,width:970,height:365,title:"Medidas Alternativas de la Carpeta de Ejecución:"+numeroCausa, type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasAlternativas"+idWindowVisorMedidasAlternativasPJADE,'<iframe src="<%=request.getContextPath()%>/visorMedidasAlternativasPJADE.do?numeroCausa=' + numeroCausa +'&idVentana='+idVentana+'" width="970" height="365" />'); 
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

	<!--Comienza ui-layout-west-->
	<div class="ui-layout-west">
		<div class="header">&nbsp;</div>
		<div class="content">
			<div id="accordionmenuprincipal">
			
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
				
				<h3 ><a href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Administrar Medidas Alternativas</a></h3>
				<div>			
					<ul id="seccion7treePJENC" class="filetree">
						<li>
							<span class="file">
								<a id="causaMedidasCautelares" style="cursor: pointer;" onclick="abreModalCausa();">Por N&uacute;mero de Carpeta de Ejecuci&oacute;n</a>
							</span>
						</li>
					</ul>		
				</div>
				
				<h3 ><a id="evento" href="#" onclick="cargaCarpetadePreliberacion()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Beneficios de Preliberación</a></h3>
				<div>			
					<!-- <ul id="seccion1treePJENC" class="filetree">
						<li><span class="file"><a id="audienciaDelDia" style="cursor: pointer;" onclick="cargaGridDiaPJENC();">Audiencia de Ejecución</a></span></li>
						<li><span class="file" id="porFecha" style="cursor: pointer;" onclick="modalFecha()">Carpeta de Ejecución</span></li>
						<li><span class="file" id="porFecha" style="cursor: pointer;" onclick="modalFecha()">Hist&oacute;rico</span></li>
					</ul>	-->	
				</div>
				<h3 ><a id="evento" href="#"  onclick="cargaGridDiaPJENC();"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Audiencia de Ejecución</a></h3>
				<div>			
				<!--	<ul id="seccion2treePJENC" class="filetree">
						<li><span class="file"><a id="recurso" style="cursor: pointer;" onclick="cargaGridRecursoPJENC();">Recurso</a></span></li>
						<li><span class="file"><a id="recurso" style="cursor: pointer;" onclick="cargaGridTranscripcionAudienciaPJENC();">Transcripci&oacute;n <br></br>de Audiencia</a></span></li>
					</ul>	-->	
				</div>
			
				
					<h3 ><a id="" href="#" onclick="cargaGridHistorico()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Hist&oacute;rico</a></h3>
				<div>		
					<!--	<input type="button" value="Ver Grafica" id="imageViewer" name="imageViewer"/>	
					<ul id="seccion3treePJENC" class="filetree">
						<li class="closed" id="casosPJENC"><span class="folder">Casos</span>
							 Aqui se agregan los casos y expedientes dinamicamente 
						</li>
					</ul>	-->	
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
						<img src="<%= request.getContextPath()%>/resources/images/mono.png" />
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
						<a href="#" id="controlAgenda"><img	src="<%=request.getContextPath()%>/resources/images/img_agenda.png"  width="201" height="246"></a>
					</center>
					<br/>
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
				<!--h1><a href="#">Clima</a></h1>
				<div align="left">
					<div align="left" id="test"></div>
				</div>
				<h1><a href="#">Chat</a></h1>
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
	<!--Termina ui-layout-east-->

	<!--Comienza ui-layout-north-->
	<div class="ui-layout-north">
		<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ENCARGADO ADMINISTRATIVO DE EJECUCION</TD>
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
				
				
				<div id="divGridPreliberacion" >
						<table id="gridPreliberacion" ></table>
						<div id="paginadorGridPreliberacion"></div>
					</div>
					
					<div id="divGridAudienciasPJENC">
						<table id="gridAudienciasPJENC" ></table>
						<div id="paginadorAudienciasPJENC"></div>
					</div>
					
					<div id="divGridAudienciasFechaPJENC">
						<table id="gridFechaPJENC" ></table>
						<div id="paginadorFechaPJENC"></div>
					</div>
					
					<div id="divGridSolicitudesRecursoPJENC">
						<table id="gridSolicitudesRecursoPJENC" ></table>
						<div id="pager2"></div>
					</div>
					
					
					
					<div id="divGridOtrosPJENC">
						<table id="gridOtrosPJENC" ></table>
						<div id="paginadorOtrosPJENC"></div>
					</div>
					
					<div id="divGridTranscripcionAudienciasPJENC">
						<table id="gridTranscripcionAudienciasPJENC" ></table>
						<div id="paginadorTranscripcionAudiencias"></div>
					</div>
					
					<div id="divDetalleResolutivaAudiencia">
						<table id="gridDetalleResolutivaAudiencia" ></table>
						<div id="paginadorDetalleResolutivaAudiencia"></div>
					</div>
					
					<div id="divGridSentenciasEnProcesoPJENC">
						<table id="gridSentenciasEnProcesoPJENC" ></table>
						<div id="pagerGridSentenciasEnProcesoPJENC"></div>
					</div>
										
				</div>	
			</div>
		</div>
	</div>
	<!--Termina main content-->

	<div id="busquedaFecha" style="display: none">

		<table cellspacing="0" cellpadding="0">
			<tr>
				<td width="153">&nbsp;</td>
				<td width="153">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><strong>Fecha Inicio:</strong> <input
					type="text" id="fechaInicio" size="20" /></td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td align="center">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><strong>Fecha
						Fin:&nbsp;&nbsp;</strong>&nbsp; <input type="text" id="fechaFin" size="20" />
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
				<td align="center">&nbsp;</td>
			</tr>
		</table>
	<div id="dialog-logout" title="Logout">
		<p align="center">
			<span id="logout">¿Desea cerrar su sesi&oacute;n?</span>
		</p>
	</div>

	</div>
	
	
	<!--Comienza div para mostrar la ventana para ingresar el numero de causa-->	
	<div id="divCausa" style="display: none">
		<table width="300" cellspacing="0" cellpadding="0">
			<tr>
				<td width="45">&nbsp;</td>
				<td width="308">&nbsp;</td>
				<td width="45">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="justify"><strong>Ingrese el n&uacute;mero de carpeta de ejecuci&oacute;n: </strong></td>
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

