<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<html>
<head>

	<!--	se importan CSS necesarias	-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
	
	<!--	se importan los scripts necesarios	-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
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
	var outerLayout, innerLayout;

	//Variable que controla que se cargue por primera vez el grid de solicitudes de dictamen no atendidas
	var firstGridSolicitudesDeDictamenNoAtendidas = true;

	//Variable que controla que se cargue por primera vez el grid de solicitudes de dictamen en proceso
	var firstGridSolicitudesDeDictamenEnProceso = true;

	//Variable que controla que se cargue por primera vez el grid de solicitudes de dictamen en proceso
	var firstGridSolicitudesDeDictamenTerminadas = true;

	//Variable que controla que se cargue por primera vez el grid de consulta de plantillas
	var firstGridAdmonPlantillaConsulta = true;

	//Variable para controlar el identificador de la ventana designar perito que se abre
	var idWindowDesignarPerito = 1;
	//Variable para controlar el identificador de la ventana asignar evidencias
	var idWindowAsignarEvidencia = 1;
	
	var firstGridSolicitudesDeAsesoriaNoAtendidas = true;
	var firstGridSolicitudesDeAsesoriaEnProceso = true;
	var firstGridSolicitudesDeAsesoriaTerminadas = true;

	//Variable para controlar el id de la ventana de crear o editar plantilla
	var idWindowCrearEditarPlantilla = 1;

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
				'�Autenticarse!': function(){
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
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});

		//Arboles del menu derecho
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		//Muestra el reloj
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
		//Agrera el evento a la agenda
		$("#controlAgenda").click(creaAgenda);
		//cargamos del grid de Solicitides->Periciales->Nuevas, por default
		cargaGridSolicitudesDeDictamenNoAtendidas();
											
		outerLayout.addToggleBtn( "#tbarBtnHeaderZise", "north" );
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		var eastSelector = "body > .ui-layout-east"; // outer-east pane
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		outerLayout.addCloseBtn("#west-closer", "west");
		outerLayout.addCloseBtn("#east-closer", "east");
		createInnerLayout () ;
		$('#test').weatherfeed(['MXDF0132']);

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
		,	onresize_end:			function () { $("#gridDetalleEvidencias").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeDictamenNoAtendidas").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeDictamenEnProceso").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeDictamenTerminadas").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeAsesoriaNoAtendidas").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeAsesoriaEnProceso").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridSolicitudesDeAsesoriaTerminadas").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridAdmonPlantillaConsulta").setGridWidth($("#mainContent").width() - 5, true);
									}
		}
	};
	

	/*
	*Verificar esta funciion ya que no se llama a�n en ningun lado
	*/
	function gridEvidencias(){
		
		jQuery("#gridDetalleEvidencias").jqGrid({ 
			url:'<%= request.getContextPath()%>/EjemploCoordinadorPeritajeEvidencia.xml', 
			datatype: "xml", 
			colNames:['N�mero de Expediente','N�mero de Caso','Tipo de solicitud','Nombre del Solicitante','Cadena de Custodia','Perito Responsable','Fecha L�mite','Acuse' ], 
			colModel:[ 	{name:'NumeroExpediente',index:'1', width:40},
			           	{name:'NumeroCaso',index:'2', width:25},
			           	{name:'TipoSolicitud',index:'3', width:40},
			           	{name:'NombreSolicitante',index:'4', width:35},
			           	{name:'CadenaCustodia',index:'5', width:30},
			           	{name:'PeritoResponsable',index:'6', width:35},
			           	{name:'FechaVencimiento',index:'7', width:35},
			           	{name:'Acuse',index:'acuse', width:10}
					],
			pager: jQuery('#pagerEvidencias'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: '1',
			viewrecords: true,
			sortorder: "desc",
			multiselect: true,
			ondblClickRow: function(rowid) {
				asignarEvidencia(rowid);
			}
			}).navGrid('#pagerEvidencias',{edit:false,add:false,del:false});
		}

	//Funcion que muestra el visor de solicitud de evidencia
	function asignarEvidencia(rowid){
		idWindowAsignarEvidencia++;
		$.newWindow({id:"iframewindowAsignacionEvidencia"+idWindowAsignarEvidencia, statusBar: true, posx:200,posy:50,width:700,height:350,title:"Asignaci�n de Evidencia", type:"iframe"});
	    $.updateWindowContent("iframewindowAsignacionEvidencia"+idWindowAsignarEvidencia,'<iframe src="<%=request.getContextPath()%>/asignacionDeEvidencia.do" width="700" height="350" />');
	}

	//Funcion para cerrar los visores
	function cerrarVentana(){
		$.closeWindow('iframewindowCoordPeriDefensoriaBandjSolicitudes');
	}

	//Funcion que muestra el visor de designacion de perito
	function dblClickRowBandejaSolicitudes(rowID){
		$.newWindow({id:"iframewindowCoordPeriDefensoriaBandjSolicitudes", statusBar: true, posx:200,posy:50,width:840,height:450,title:"Designaci�n de Perito", type:"iframe"});
	    $.updateWindowContent("iframewindowCoordPeriDefensoriaBandjSolicitudes",'<iframe src="<%=request.getContextPath()%>/visorCoorPeriDefensoriaBandSolicitudes.do" width="840" height="450" />'); 
	}
	
	
	//Funcion que crea la agenda
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');		
	}

	//Funcion que ejecuta el chat
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

  	//Abre una nueva ventana para designar un perito 		
	function designarPerito(solicitudPericialId, estatus, area){
		idWindowDesignarPerito++;
		$.newWindow({id:"iframewindowConsultarDenunciante" + idWindowDesignarPerito, statusBar: true, posx:50,posy:50,width:1150,height:400,title:"Designar Perito", type:"iframe"});
		$.updateWindowContent("iframewindowConsultarDenunciante" + idWindowDesignarPerito,'<iframe src="<%= request.getContextPath() %>/designarPerito.do?solicitudPericialId=' +solicitudPericialId +'&estatus='+estatus+'&area='+area+'" width="1350" height="400" />');
	}

	//Funcion que cierra la ventana hijo
	function cerrarVisorDesignarPerito(){
		$.closeWindow('iframewindowConsultarDenunciante'+ idWindowDesignarPerito);
	}

	////////////////////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA SOLICITUDES /////////////////////////////////////////////////////

	/*
	*Funcion que oculta o muestra los grids, recibe como parametro
	*el nombre del grid que va a mostrar, y todos los demas, se 
	*ocultaran
	*/
	function ocultaMuestraGrids(nombreGrid){

		if(nombreGrid == "gridSolicitudesDeDictamenNoAtendidas"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenEnProceso").hide();			
			$("#divGridSolicitudesDeDictamenTerminadas").hide();
			$("#divGridSolicitudesDeDictamenNoAtendidas").show();
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").hide();
		}

		if(nombreGrid == "gridSolicitudesDeDictamenEnProceso"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenTerminadas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").show();
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").hide();
		}
		
		if(nombreGrid == "gridSolicitudesDeDictamenTerminadas"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").hide();	
			$("#divGridSolicitudesDeDictamenTerminadas").show();			
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").hide();
		}
		
		if(nombreGrid == "gridSolicitudesDeAsesoriaEnProceso"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").hide();	
			$("#divGridSolicitudesDeDictamenTerminadas").hide();			
			$("#divGridSolicitudesDeAsesoriaEnProceso").show();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").hide();
		}

		if(nombreGrid == "gridSolicitudesDeAsesoriaTerminadas"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").hide();	
			$("#divGridSolicitudesDeDictamenTerminadas").hide();			
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").show();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").hide();
		}

		if(nombreGrid == "gridSolicitudesDeAsesoriaNoAtendidas"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").hide();	
			$("#divGridSolicitudesDeDictamenTerminadas").hide();			
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").show();
			$("#divGridAdmonPlantillaConsulta").hide();
		}

		if(nombreGrid == "gridAdmonPlantillaConsulta"){

			//Este grid ya se encontraba CHECARLO
			$("#divGridSolicitudes").hide();
			//Este grid ya se encontraba
			$("#divGridSolicitudesDeDictamenNoAtendidas").hide();
			$("#divGridSolicitudesDeDictamenEnProceso").hide();	
			$("#divGridSolicitudesDeDictamenTerminadas").hide();			
			$("#divGridSolicitudesDeAsesoriaEnProceso").hide();			
			$("#divGridSolicitudesDeAsesoriaTerminadas").hide();
			$("#divGridSolicitudesDeAsesoriaNoAtendidas").hide();
			$("#divGridAdmonPlantillaConsulta").show();
		}
	}
	
	/*
	*Funcion que carga el grid con las solicitudes periciales no atendidas
	*/
	function cargaGridSolicitudesDeDictamenNoAtendidas(){
		
		if(firstGridSolicitudesDeDictamenNoAtendidas == true){
			
			jQuery("#gridSolicitudesDeDictamenNoAtendidas").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesNoAtendidas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1', 
				datatype: "xml", 
				colNames:['N\u00famero de Folio','N\u00famero de Caso','N\u00famero de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha L\u00edmite' ],
				colModel:[ 	{name:'NumeroFolio',index:'1', width:200},
				           	{name:'NumeroCaso',index:'2', width:200},
							{name:'NumeroExpediente',index:'3', width:200},
				           	{name:'NombreSolicitante',index:'4', width:230},
				           	{name:'EspecialidadPerito',index:'5', width:200}, 
							{name:'FechaLimite',index:'6', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesDeDictamenNoAtendidas'),
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
					designarPerito(rowid,"NA",1);
				}
			}).navGrid('#pagerGridSolicitudesDeDictamenNoAtendidas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeDictamenNoAtendidas=false;
		}
		else{
			jQuery("#gridSolicitudesDeDictamenNoAtendidas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesNoAtendidas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1',datatype: "xml" });
			$("#gridSolicitudesDeDictamenNoAtendidas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeDictamenNoAtendidas');
	}

	/*
	*Funcion que carga el grid de solicitudes periciales no atendidas
	*/
	function cargaGridSolicitudesDeDictamenProceso(){

		if(firstGridSolicitudesDeDictamenEnProceso == true){
			
			jQuery("#gridSolicitudesDeDictamenEnProceso").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesEnProceso.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1', 
				datatype: "xml", 
				colNames:['N�mero de Folio','N�mero de Caso','N�mero de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha L�mite','Fecha �ltima Modificaci�n' ], 
				colModel:[ 	{name:'NumeroFolio',index:'1', width:200},
							{name:'NumeroCaso',index:'2', width:200},
				           	{name:'NumeroExpediente',index:'3', width:200},
				           	{name:'NombreSolicitante',index:'4', width:230},
				           	{name:'EspecialidadPerito',index:'5', width:200}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaUltModif',index:'7', width:150,hidden:true}
						],
				pager: jQuery('#pagerGridSolicitudesDeDictamenEnProceso'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesDeDictamenEnProceso',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeDictamenEnProceso=false;
		}
		else{
			jQuery("#gridSolicitudesDeDictamenEnProceso").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesEnProceso.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1',datatype: "xml" });
			$("#gridSolicitudesDeDictamenEnProceso").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeDictamenEnProceso');
	}

	
	/*
	*Funcion que carga el grid de solicitudes periciales terminadas
	*/
	function cargaGridSolicitudesDeDictamenTerminadas(){

		if(firstGridSolicitudesDeDictamenTerminadas == true){
			
			jQuery("#gridSolicitudesDeDictamenTerminadas").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesTerminadas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1', 
				datatype: "xml", 
				colNames:['N�mero de Folio','N�mero de Caso','N�mero de Expediente','Nombre del Solicitante','Especialidad de Perito','Fecha L�mite','Fecha de Cierre' ], 
				colModel:[ 	{name:'NumeroFolio',index:'1', width:200},
				           	{name:'NumeroCaso',index:'2', width:200},
				           	{name:'NumeroExpediente',index:'3', width:200},				           	
				           	{name:'NombreSolicitante',index:'4', width:230},
				           	{name:'EspecialidadPerito',index:'5', width:200}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaCierre',index:'7', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesDeDictamenTerminadas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesDeDictamenTerminadas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeDictamenTerminadas=false;
		}
		else{
			jQuery("#gridSolicitudesDeDictamenTerminadas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarSolicitudesPericialesTerminadas.do?tipoSolicitud=<%=TiposSolicitudes.DICTAMEN.getValorId()%>&area=1&multiRol=1',datatype: "xml" });
			$("#gridSolicitudesDeDictamenTerminadas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeDictamenTerminadas');
	}
	
	/*
	*Funcion que carga el grid de solicitud asesorias terminadas
	*/
	function cargaGridSolicitudesDeAsesoriaNoAtendidas(){

		if(firstGridSolicitudesDeAsesoriaNoAtendidas == true){
			
			jQuery("#gridSolicitudesDeAsesoriaNoAtendidas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=1', 
				datatype: "xml", 
				colNames:['N�mero de Folio','N�mero de Caso','N�mero de Expediente','Nombre del Solicitante','Especialidad del Asesor','Fecha L�mite' ], 
				colModel:[ 	{name:'NumeroFolio',index:'1', width:150},
				           	{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
							{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'EspecialidadPerito',index:'5', width:150}, 
							{name:'FechaLimite',index:'6', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesDeAsesoriaNoAtendidas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesDeAsesoriaNoAtendidas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeAsesoriaNoAtendidas=false;
		}
		else{
			jQuery("#gridSolicitudesDeasesoriaNoAtendidas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=1',datatype: "xml" });
			$("#gridSolicitudesDeAsesoriaNoAtendidas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeAsesoriaNoAtendidas');
	}

	/*
	*Funcion que carga el grid de solicitud asesorias en proceso
	*/
	function cargaGridSolicitudesDeAsesoriaProceso(){

		if(firstGridSolicitudesDeAsesoriaEnProceso == true){
			
			jQuery("#gridSolicitudesDeAsesoriaEnProceso").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=2', 
				datatype: "xml", 
				colNames:['N�mero de folio','N�mero de caso','N�mero de expediente','Nombre del solicitante','Especialidad del asesor','Fecha l�mite','Fecha ultima de modificaci�n' ], 
				colModel:[ 	{name:'NumeroFolio',index:'1', width:150},				           	
				           	{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
				           	{name:'NombreSolicitante',index:'4', width:200},
				           	{name:'EspecialidadPerito',index:'5', width:150}, 
							{name:'FechaLimite',index:'6', width:150},
							{name:'FechaModificacion',index:'7', width:150,hidden:true}
						],
				pager: jQuery('#pagerGridSolicitudesDeAsesoriaEnProceso'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesDeAsesoriaEnProceso',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeAsesoriaEnProceso=false;
		}
		else{
			jQuery("#gridSolicitudesDeAsesoriaEnProceso").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=2',datatype: "xml" });
			$("#gridSolicitudesDeAsesoriaEnProceso").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeAsesoriaEnProceso');
	}

	
	/*
	*Funcion que carga el grid de solicitud asesorias terminadas
	*/
	function cargaGridSolicitudesDeAsesoriaTerminadas(){

		if(firstGridSolicitudesDeAsesoriaTerminadas == true){
			
			jQuery("#gridSolicitudesDeAsesoriaTerminadas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=3', 
				datatype: "xml", 
				colNames:['N�mero de Folio','N�mero de Caso','N�mero de Expediente','Nombre del Solicitante','Perito Designado','Especialidad','Fecha L�mite','Fecha Cierre' ], 
				colModel:[ 	{name:'NumeroFolio',index:'1', width:150},
				           	{name:'NumeroCaso',index:'2', width:150},
				           	{name:'NumeroExpediente',index:'3', width:150},
				           	{name:'NombreSolicitante',index:'4', width:200},			           	
				           	{name:'PeritoDesignado',index:'5', width:200}, 
				           	{name:'EspecialidadPerito',index:'6', width:150}, 
							{name:'FechaLimite',index:'7', width:150},
							{name:'FechaCierre',index:'8', width:150}
						],
				pager: jQuery('#pagerGridSolicitudesDeAsesoriaTerminadas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridSolicitudesDeAsesoriaTerminadas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridSolicitudesDeAsesoriaTerminadas = false;
		}
		else{
			jQuery("#gridSolicitudesDeAsesoriaTerminadas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudAsesoriasPorEstatus.do?estatus=3',datatype: "xml" });
			$("#gridSolicitudesDeAsesoriaTerminadas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridSolicitudesDeAsesoriaTerminadas');
	}

	
	/**
	*Abre la ventana modal para elegir el tipo de platilla a consultar 
	*/
	function abreModalPlantilla(){
		
		cargaTipoDocumentos();
		$("#divTipoDocto").dialog("open");
	  	$("#divTipoDocto").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Tipo de Documento', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 350,
		  	height: 200,
		  	maxWidth: 1000,
		  	buttons:{"Realizar Consulta":function() {
		  		cargaGridAdmonPlantillaConsulta();
		  			$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});	  	
		
	}

	/*
	*Carga el grid de las plantillas dependiendo del valor que se seleccione en la
	*el combo box de tipo de documento
	*/
	function cargaGridAdmonPlantillaConsulta(){
		
		var tipoDocumento = $('#cbxTipoDocto option:selected').val();

		if(firstGridAdmonPlantillaConsulta == true){
			
			jQuery("#gridAdmonPlantillaConsulta").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarPlantillasPorTipo.do?tipoDocumento='+tipoDocumento+'', 
				datatype: "xml", 
				colNames:['Nombre de la Plantilla','Tipo de Plantilla','Fecha de Creacion de la Plantilla'], 
				colModel:[ 	{name:'NombrePlantilla',index:'1', width:350},
				           	{name:'TipoPlantilla',index:'2', width:350},
				           	{name:'FechaCreacionPlantilla',index:'3', width:210} 
						],
				pager: jQuery('#pagerGridAdmonPlantillaConsulta'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				height:440,
				width:"100%",
				height:440,
				sortname: '1',
				viewrecords: true,
				ondblClickRow: function(rowid) {
					crearOEditarPlantilla(rowid);
				},
				sortorder: "desc"
			}).navGrid('#pagerGridAdmonPlantillaConsulta',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridAdmonPlantillaConsulta=false;
		}
		else{
			jQuery("#gridAdmonPlantillaConsulta").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarPlantillasPorTipo.do?tipoDocumento='+tipoDocumento+'',datatype: "xml" });
			$("#gridAdmonPlantillaConsulta").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridAdmonPlantillaConsulta');
	}
	
	/*
	*Funcion que realiza la carga del combo de tipos de documento
	*/
	function cargaTipoDocumentos() {
		 
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTiposFormas.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catDocumentos').each(function(){
					$('#cbxTipoDocto').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
	}

	//Abre una ventana para crear o editar un documento		
	function crearOEditarPlantilla(plantillaId){
		idWindowCrearEditarPlantilla++;
		$.newWindow({id:"iframewindowCrearOEditarPlantilla" + idWindowCrearEditarPlantilla, statusBar: true, posx:50,posy:50,width:1250,height:400,title:"Crear o Editar Plantilla", type:"iframe"});
		$.updateWindowContent("iframewindowCrearOEditarPlantilla" + idWindowCrearEditarPlantilla,'<iframe src="<%= request.getContextPath() %>/crearOEditarPlantilla.do?plantillaId=' +plantillaId +'" width="1250" height="400" />');
	}

	//Funcion para cerrar la ventana
	function cerrarVisorCrearOEditarPlantilla(){		
		$.closeWindow('iframewindowCrearOEditarPlantilla'+ idWindowCrearEditarPlantilla);
		idWindowCrearEditarPlantilla--;
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

<!--	comienza ui-layout-west -->
<div class="ui-layout-west">	
	<div class="header">&nbsp;</div>
	<div class="content">
	
		<!--	comienza acordeon del menu derecho-->
		<div id="accordionmenuprincipal">
		
			<!--	comienza tab solicitudes -->
			<h3><a id="solicitudes" href="#">Solicitudes</a></h3>
				<div>
					<ul id="seccion1tree" class="filetree">
						<li class="closed" ><span class="folder">Periciales</span>
							<ul>
							   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="pericialesNoAtendida" onclick="cargaGridSolicitudesDeDictamenNoAtendidas();">Nuevas</a></span></li>
							   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="pericialesEnProceso" onclick="cargaGridSolicitudesDeDictamenProceso();">Pendientes</a></span></li>
							   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="pericialesTerminada" onclick="cargaGridSolicitudesDeDictamenTerminadas();">Concluidas</a></span></li>
							</ul>			
						</li>
					</ul>
				</div>
			<!--	termina tab solicitudes -->
			
			<!--	comienza tab Administrar Plantillas
			<h3><a id="solicitudes" href="#" >Administraci&oacute;n</a></h3>
				<div>
					<ul id="seccion2tree" class="filetree">
						<li class="closed" ><span class="folder">Plantillas</span>
							<ul>
							   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="admonPlantillaConsulta" onclick="abreModalPlantilla();">Consulta</a></span></li>
							   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="admonPlantillaNueva" onclick="crearOEditarPlantilla();">Nueva</a></span></li>
							</ul>			
						</li>
					</ul>
				</div> -->
			<!--	termina tab Administrar Plantillas -->

			<!--	comienza tab evidencias -->
			<h3><a id="tabEvidencias" href="#">Evidencias</a></h3>
				<div></div>
			<!--	termina tab evidencias -->
			
			<!--	comienza tab dictamen -->
			<!--<h3><a id="tabDictamen" href="#" >Dictamen</a></h3>
				<div></div>-->
			<!--	termina tab evidencias -->
		</div>
		<!--	termina acordeon del menu derecho-->
		
	</div>
</div>
<!--	Termina ui-layout-west -->

<!--	Comienza ui-layout-east -->
<div class="ui-layout-east">

	<div class="header">Bienvenido</div>
	<div class="content">
	
	<!--	Acordeon menu izquierdo -->	
	<div id="accordionmenuderprincipal">
	
		<!--	Comienza tab datos personales -->
		<h4><a href="#">Datos Personales</a></h4>
			<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
					</center>
			</div>
		<!--	Termina tab datos personales -->
		
		<!--	Comienza tab calendario -->
		<!-- <h4><a href="#">Calendario</a></h4>
			<div>
					<center>
						<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
					</center>
			</div>-->
		<!--	Termina tab calendario-->
		
		<!--	Comienza tab agenda -->
		<h6><a href="#">Agenda</a></h6>
			<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
					</center>
					<br/>
			</div>
		<!--	Termina tab datos personales -->
			
		<!--	Comienza tab leyes y codigos -->
		<h6><a href="#" id="">Consultar Leyes y C&oacute;digos</a></h6>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" bordercolorlight="#FFFFFF" style="cursor:pointer">
			      <tr>
			        <td id="leyes" width="100%"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Leyes</td>
			      </tr>
			      <tr>
			        <td id="codigos" >&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />C�digos</td>
			      </tr>
			      <tr>
			        <td id="manuales">&nbsp;<img  src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Manuales</td>
			      </tr>
			      </table>
			</div>
		<!--	Termina tab leyes y codigos -->
		
		<!--	Comienza tab Chat -->	
		<!--h1><a href="#">Chat</a></h1>
			<div>
					<div id="dialogoChat" title="Chat" align="center">
						<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
					</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>
			</div>
		<!--	Termina tab Chat -->
		
		<!--	Comienza tab Clima -->
		<!--h1><a href="#">Clima</a></h1>
		<div align="left">
			<div align="left" id="test"></div>
		</div>
		<!--	Termina tab Clima -->
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
<!--	Comienza ui-layout-east -->

<!--	Comienza layout-north -->
<div class="ui-layout-north">

	<!--	Comienza content-->
	<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">DIRECTOR (COORDINADOR) DE SERVICIOS PERICIALES</TD>
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
	<!--	Termina content-->

	<!--	Comienza barra de herramientas-->
	<ul class="toolbar">
		<div id="menu_head">
		<li id="tbarBtnHeaderZise" class="first"><span></span></li>
		</div>
		<div id="menu_config">
		</div>
	</ul>
	<!--	Termina barra de herramientas-->

</div>
<!--	Termina layout-north -->

<!--	Comienza layout-south-->
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
<!--	Termina layout-south-->

<!--	Comienza mainContent-->
<div id="mainContent">
	<div class="ui-layout-center">
		<div class="ui-layout-content">
			<div class="ui-layout-north">
			
				<!-- Espacio para el grid con las solicitudes de dictamen no atendidas-->
				<div id="divGridSolicitudesDeDictamenNoAtendidas">
					<table id="gridSolicitudesDeDictamenNoAtendidas"></table>
					<div id="pagerGridSolicitudesDeDictamenNoAtendidas"></div>
				</div>
				
				<!--Espacio para el grid con las solicitudes periciales en proceso-->
				<div id="divGridSolicitudesDeDictamenEnProceso">
					<table id="gridSolicitudesDeDictamenEnProceso"></table>
					<div id="pagerGridSolicitudesDeDictamenEnProceso"></div>
				</div>
				
				<!--Espacio para el grid con las solicitudes periciales terminadas o cerradas-->
					<div id="divGridSolicitudesDeDictamenTerminadas">
						<table id="gridSolicitudesDeDictamenTerminadas"></table>
						<div id="pagerGridSolicitudesDeDictamenTerminadas"></div>
					</div>			
				
				<!--Espacio para los grid de solicitudes de asesorias-->
				<div id="divGridSolicitudesDeAsesoriaNoAtendidas">
					<table id="gridSolicitudesDeAsesoriaNoAtendidas"></table>
					<div id="pagerGridSolicitudesDeAsesoriaNoAtendidas"></div>
				</div>
				<div id="divGridSolicitudesDeAsesoriaEnProceso">
					<table id="gridSolicitudesDeAsesoriaEnProceso"></table>
					<div id="pagerGridSolicitudesDeAsesoriaEnProceso"></div>
				</div>
				<div id="divGridSolicitudesDeAsesoriaTerminadas">
					<table id="gridSolicitudesDeAsesoriaTerminadas"></table>
					<div id="pagerGridSolicitudesDeAsesoriaTerminadas"></div>
				</div>			
				<!--FIN de Espacio para los grid de solicitudes de asesorias-->
				
				<!--Espacio para el grid con la consulta de plantillas-->
					<div id="divGridAdmonPlantillaConsulta">
						<table id="gridAdmonPlantillaConsulta"></table>
						<div id="pagerGridAdmonPlantillaConsulta"></div>
					</div>			

					<div id="divTipoDocto" style="display: none">
						<table width="300" cellspacing="0" cellpadding="0">
							<tr>
								<td width="45">&nbsp;</td>
								<td width="308">&nbsp;</td>
								<td width="45">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3" align="justify"><strong>Seleccione Tipo de Documento: </strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="center">
						            <select id="cbxTipoDocto" style="width:200px;">						
						                <option value="0">-Todos-</option>
						            </select>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
					
				<!-- YA SE ENCONTRABAN ESTOS GRIDS -->
				<div id="divGridEvidencias">
					<table id="gridDetalleEvidencias"></table>
					<div id="pagerEvidencias"></div>
				</div>
				
			</div>
		</div>
	</div>
</div>
<!--	Termina mainContent-->
	<div id="dialog-logout" title="Logout">
		<p align="center">
			<span id="logout">�Desea cerrar su sesi&oacute;n?</span>
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
					<td colspan="2">La sesi&oacute;n se a bloqueado introduce tu contrase�a para desbloquear.</td>
					
				</tr>
				<tr>
					<td align="right"><label style="color:#4A5C68">Contrase�a:</label></td>
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

			<p>�Desea continuar con la sesi&oacute;n?</p>
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
