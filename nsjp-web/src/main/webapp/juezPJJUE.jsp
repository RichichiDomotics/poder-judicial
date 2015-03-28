<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html>
<head>

<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--css para el estilos de jquery-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	
	<!--css para estilo de los arboles-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<!--estilo ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--estilo del grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
	
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
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
<!--para creacion de arboles-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	var outerLayout, innerLayout;
	var primeraGridExpedientesDocumentoPJATP = true;
	var validaFecha = false;
	var audienciasid;
	var global=0;
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

		//llama la funcion que crea la ventana de buscar expediente	
		$("#buscarExpediente").click(buscarExpediente);
		
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
		
		outerLayout = $("body").layout( layoutSettings_Outer );

		//crea el acordeon
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		//crea el arbol de audiencias
		$("#seccion1treePJENC,#seccion34treePJEA").treeview();
		//agrega el evento generar documento 
		$("#generarDocumento").click(generarDocumentoView);
		//agreaga el evento para crear la agenda
		$("#controlAgenda").click(creaAgenda);						

		//agreaga el evento busqueda de audiencias
		$("#bAudienciaPJRMJ").click(buscaAudienciaNo);
		
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

		/*
		*Funcion que carga el grid, por default con las audiencias del dia
		*/
		jQuery("#gridAudienciasPJJU").jqGrid({ 
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
								pager: jQuery('#pager1'),
								rowNum:10,
								rowList:[10,20,30],
								autowidth: true,
								sortname: 'expediente',
								viewrecords: true,
								sortorder: "desc",
								ondblClickRow: function(rowid) {
											mostrarVisorAudienciaJuezPJJU(rowid);
										}
							}).navGrid('#pager1',{edit:false,add:false,del:false});	

		 $("#gview_gridAudienciasPJJU .ui-jqgrid-bdiv").css('height', '400px');	



					
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
		
	});
	//Fin OnReady

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

	/*
	*Funcion que crea el visor buscar Expediente
	*/						
	function buscarExpediente() {
		$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do?rolUsuario=juezPJ" width="653" height="400" />');		
	}

	function recargaGridAudienciasDelDia(){

		jQuery("#gridAudienciasPJJU").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarAudienciaDelDiaPJENS.do',datatype: "xml" });
		$("#gridAudienciasPJJU").trigger("reloadGrid");
	}
	
	/*
	*Funcion que abre el visor de audiencias
	*(Por el momento no acarrea el ID solo abre el visor) 
	*/
	function mostrarVisorAudienciaJuezPJJU(rowID){
		
		var newVentana = "iframewindowVisorAudienciaJuez" + global;
		
		$.newWindow({id:newVentana, statusBar: true, posx:200,posy:50,width:1140,height:450,title:"Visor de Audiencia", type:"iframe"});
		$.updateWindowContent(newVentana,'<iframe src="<%=request.getContextPath()%>/visorAudienciaJuezPJJUE.do?idEvento='+rowID+'"width="1140" height="450" />');
    	$("#" + newVentana + " .window-maximizeButton").click();
		
    	global = global + 1;
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
	*Funcion que llama a la funcionalidad para el chat 
	*/
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

    /*
	*Funcion que llama a la funcionalidad para recargar el grid 
	*/
	function recargaGrid(){
		
		$("#gridAudienciasPJJU").trigger("reloadGrid");

	}	

	/*
	*Funcion que llama a la funcionalidad para buscar audiencias 
	*/
	
	function buscaAudienciaNo() {
		$.newWindow({id:"iframewindowBuscarAudiencias", statusBar: true, posx:255,posy:111,width:650,height:400,title:"Busqueda de Audiencia", type:"iframe"});
	    $.updateWindowContent("iframewindowBuscarAudiencias",'<iframe src="<%=request.getContextPath()%>/buscaCarpetaEjecucionJSP.do" width="650" height="400" />');
	    		
	}	

	function muestraAudiencias(){
		 $("#divGridAudienciasPJJU").css("display","block");
	      $("#divGridControvercias").css("display","none");
	      $("#divGridAudienciasPorFechaPJENS").css("display","none");
	      $("#divGridExpedientesDocumentoPJATP").css("display","none"); 
	      
	      recargaGrid();

		}

	

	function consultaGridControvercias(){
      $("#divGridAudienciasPJJU").css("display","none");
      $("#divGridControvercias").css("display","block");
      $("#divGridAudienciasPorFechaPJENS").css("display","none");
      $("#divGridExpedientesDocumentoPJATP").css("display","none"); 
		jQuery("#gridControvercias").jqGrid({ 
			url:'<%= request.getContextPath()%>/condultaGridControvercias.do',
			datatype: "xml", 
			colNames:['N&uacute;mero de Caso','Nombre del Fiscal','Nombre del Documento', 'Fecha Inicio'], 
			colModel:[ 	{name:'numeroCaso',index:'numeroCaso', width:285},
						{name:'expediente',index:'expediente', width:285}, 
						{name:'caracter',index:'caracter', width:285}, 
						{name:'tipoAudiencia',index:'tipoAudiencia', width:285},
						
					],
			pager: jQuery('#pagerGridControvercias'),
			rowNum:10,
			rowList:[25,50,100],
			width: 767,
			autowidth: true,
			sortname: 'expediente',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerGridControvercias',{edit:false,add:false,del:false});	

$("#gview_gridControvercias .ui-jqgrid-bdiv").css('height', '450px');	



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
		
		$('#divGridAudienciasPJJU').hide();
		$('#divGridExpedientesDocumentoPJATP').show();
		$('#divGridControvercias').hide();
		$("#divGridAudienciasPorFechaPJENS").css("display","none");
	}
	

 	// function dblClickRowvisorDocumentosExpediente(idRow){	
	//	$.newWindow({id:"iframewindowVisorEncargadoAdmonAudiencias", statusBar: true, posx:255,posy:111,width:1024,height:400,title:"Documentos", type:"iframe"});
	//	$.updateWindowContent("iframewindowVisorEncargadoAdmonAudiencias",'<iframe src="<%=request.getContextPath()%>/visorDocumentos.do?numExpedienteId=' + idRow +'" width="1024" height="400" />'); 
	//}
	

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



/*********************************************FUNCIONALIDAD PARA CEJA DE AUDIENCIAS POR FECHA*************************************************************/
	
	/*
	*Funcion que carga la ventana modal para selccionar el intervalo de fechas entre las cuales
	*se desea consultar las audiencias agendadas
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
		  				validaCamposFechas();
		  				cargaGridPorFechaPJENS();
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

	//Funcion que valida si los campos estan llenos al enviar 
	function validaCamposFechas() {

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

	//variable para controlar el guardado de las audiencias por fecha
	var gridPorFecha = true;
	
	/**
	*Funcion que carga el grid de audiencias por fecha
	*/
	function cargaGridPorFechaPJENS(){

	  var fechaInicio = $('#fechaInicio').val();
	  var fechaFin = $("#fechaFin").val();				  

	  if (validaFecha==true){

		  if(gridPorFecha == true){

				jQuery("#gridAudienciaPorFechaPJENS").jqGrid({ 
					url:'<%= request.getContextPath() %>/buscarAudienciasPorFecha.do',
					datatype: "xml", 
					mtype: 'POST',						
					postData: {fechaInicio: function()  {return fechaInicio;},
					           fechaFin: function()		{return fechaFin;}																										
					},
					colNames:['N&uacute;mero de Caso','N&uacute;mero de Causa','Carácter', 'Tipo de Audiencia','Fecha de Audiencia','Hora de Audiencia','Sala'], 
					colModel:[ 	{name:'numeroCaso',index:'numeroCaso', width:200},
								{name:'expediente',index:'expediente', width:180},
								{name:'caracter',index:'caracter', width:80},
								{name:'tipoAudiencia',index:'tipoAudiencia', width:120},
								{name:'fechaAudiencia',index:'fechaAudiencia', width:120}, 
								{name:'horaAudiencia',index:'horaAudiencia', width:120},
								{name:'sala',index:'sala', width:80}
							],
					pager: jQuery('#paginadorGridAudienciaPorFechaPJENS'),
					rowNum:10,
					rowList:[25,50,100],
					autowidth: false,
					sortname: 'detalle',
					viewrecords: true,
					sortorder: "desc",
					ondblClickRow: function(rowid) {
						
						audienciasid=rowid.split("*")[0];
						mostrarVisorAudienciaJuezPJJU(audienciasid);
					}
				}).navGrid('#paginadorGridAudienciaPorFechaPJENS',{edit:false,add:false,del:false});
				//Cambiamos la variable para que la proxima vez se refresque el grid
				gridPorFecha= false;
			}
			else{
				jQuery("#gridAudienciaPorFechaPJENS").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/buscarAudienciasPorFecha.do?fechaInicio='+fechaInicio+'&fechaFin='+fechaFin+'',datatype: "xml" });
				$("#gridAudienciaPorFechaPJENS").trigger("reloadGrid");			
			}


		  
		
		  $("#divGridAudienciasPJJU").css("display","none");
	      $("#divGridControvercias").css("display","none");
	      $("#divGridAudienciasPorFechaPJENS").css("display","block");
	      $("#divGridExpedientesDocumentoPJATP").css("display","none"); 
			$("#gview_gridAudienciaPorFechaPJENS .ui-jqgrid-bdiv").css('height', '450px');
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
				<h3 ><a id="evento" href="#" onclick="muestraAudiencias()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Audiencias</a></h3>
				<div>			
					<ul id="seccion1treePJENC" class="filetree">
						<li><span class="file"><a id="audienciaDelDia" style="cursor: pointer;" onclick="muestraAudiencias();">Del día</a></span></li>
						<li><span class="file"><a id="audienciaFecha" style="cursor: pointer;" onclick="modalFecha()">Por fecha</a></span></li>
					</ul>		
				</div>
				<!-- <h3><a id="bAudienciaPJRMJ" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Buscar Audiencia</a></h3>
				<div></div> 
				<h3><a onclick="consultaGridControvercias()" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Controversias</a></h3>
				<div></div>-->
				<h3 ><a id="even" href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes</a></h3>
				<div>			
					<ul id="seccion34treePJEA" class="filetree">
						<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="poppopTipoBusqueda('expediente')">N&uacute;mero Expediente</a></span></li>
						<li><span class="file"><a id="audiencia" style="cursor: pointer;" onclick="poppopTipoBusqueda('fecha')">Por Fecha</a></span></li>
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
				</div>
				
				<!--h4>
					<a href="#">Clima</a>
				</h4>
				<div align="left">
					<div align="left" id="test"></div>
				</div-->
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
					    <TD width=301 align=left valign="middle" style="color:#51a651;">JUEZ</TD>
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
		<!--comienza barra de herramientas-->
		<ul class="toolbar">
			<div id="menu_head">
				<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			</div>
			<div id="menu_config">
				<li id="buscarExpediente"><span></span>Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
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
					<div id="divGridAudienciasPJJU">
						<table id="gridAudienciasPJJU" ></table> 
						<div id="pager1"></div>
					</div>	
					
					<div id="divGridAudienciasPorFechaPJENS">
						<table id="gridAudienciaPorFechaPJENS"></table>
						<div id="paginadorGridAudienciaPorFechaPJENS"></div>
					</div>
					
					<div id="divGridControvercias">
						<table id="gridControvercias" ></table>
						<div id="pagerGridControvercias"></div>
					</div>	
					
					<div id="divGridExpedientesDocumentoPJATP">
						<table id="gridExpedientesDocumentoPJATP"></table>
						<div id="paginadorExpedientesDocumentoPJATP"></div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	
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
	
	<!--Termina main content-->
	
		<!--div para la ventana modal de buscar audiencias agendadas por fecha-->
	<div id="busquedaFecha" style="display: none">
		<table cellspacing="0" cellpadding="0" >
			<tr>
				<td width="153">&nbsp;</td>
				<td width="153">&nbsp;</td>
			</tr>
			<tr>
			  <td colspan="2" align="center">
			  	<strong>Fecha Inicio:</strong>
			  	<input type="text" id="fechaInicio" size="20" />		  
			  </td>
		    </tr>
			<tr>
			  <td align="center">&nbsp;</td>
			  <td align="center">&nbsp;</td>
	  		</tr>
			<tr>
			  <td colspan="2" align="center">
			  	<strong>Fecha Fin:&nbsp;&nbsp;</strong>&nbsp;
		      	<input type="text" id="fechaFin" size="20" /></td>
	  		</tr>
			<tr>
			  <td align="center">&nbsp;</td>
			  <td align="center">&nbsp;</td>
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

