<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud" %>
<%@ page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas" %>
<html>
<head>
<!--Hojas de estilos-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/layout_complex.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" 
	href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />


<!--Scripts-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
	//URG - 005 
	var habilitarTurno='<%= (request.getSession().getAttribute("KEY_SESSION_HABILITAR_TURNO"))%>';
	var abreenPenal;
	var tur;
	var outerLayout, innerLayout;
	var idWindowNuevaDenuncia=1;
	var grid=0;
	var idSiguienteTurno=0;
	var idTurno;
	var numerocaso;
	var idWindowDetalleSolicitud=1;

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
		$("#idExpedientes").css({ color: "#1C94C4"});
		$("#anio1").css({ color: "#1C94C4"});
		$("#A1").css({ color: "#1C94C4"});
		$("#A2").css({ color: "#1C94C4"});
		$("#A3").css({ color: "#1C94C4"});
		$("#anio2").css({ color: "#1C94C4"});
		$("#B1").css({ color: "#1C94C4"});
		$("#B2").css({ color: "#1C94C4"});
		$("#B3").css({ color: "#1C94C4"});
		
			
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
		//ocultamos el boton de turno o no URG - 005
		if(habilitarTurno==null || habilitarTurno=='null' || habilitarTurno==1)
		{
			$("#tbarBtnConsultarTurnoAtencion").show();
			$("#tbarBtnConsultarTurnoAtencionDesh").hide();
		}
		else
		{
			$("#tbarBtnConsultarTurnoAtencion").hide();
			$("#tbarBtnConsultarTurnoAtencionDesh").show();
		}
		//funciones para el popup interno del turno
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$("#tbarBtnConsultarTurnoAtencion").click(obtenTurnoSinPopup);
		$("#tbarBtnConsultarTurnoAtencionDesh").click(obtenTurnoSinPopup);//URG - 005
		
		$("#tbarBtnAsignar").click(asigarPermisos);
				
		$("#justiciaAlterna-restaurativa").css({ color: "#1C94C4"});
		
		$("#unidad-imvestigacion").css({ color: "#1C94C4"});
		
		$("#controlAgenda").click(creaAgenda);
		
		$( "#tabsAtencionTempranaPenal" ).tabs();
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
	
		$("#exp0001").click(seleccionFila);	
		$("#exp0002").click(seleccionFila2);
		$("#restaura").click(gridRestaurativa);
		$("#unidadInvestiga").click(gridUnidadInvestigacion);
		
		outerLayout = $("body").layout( layoutSettings_Outer );
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		muestraGadgets();

		//$("#tbarBtnConsultarTurnoAtencion").click(nuevaDenuncia);
		$("#botonGuarda").click(guardaNombre);
		$("#cancelTurno").click(cancelar);
		$("#idTurnos").click(activaTurno);
		$("#delDia").click(activaExpediente);
		$("#delTodos").click(activaExpedienteSinFecha);
		$("#expCompartidos").click(activaExpCompartidos);
		
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
		
		$("#divDatos").css("display", "none");
		
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
										url:'<%=request.getContextPath()%>/BusquedaInicialTurnosGrid.do', 
										datatype: "xml", 
										colNames:['Expediente','Tipo','Fecha', 'Denunciante','Delito'], 
										colModel:[ 	{name:'Expediente',index:'1', width:200, align:'center'}, 
										           	{name:'Tipo',index:'5', width:120, align:'center'},
													{name:'Fecha',index:'2', width:100, align:'center'},
													{name:'Denunciante',index:'3', width:250, align:'center'}, 
													{name:'Delito',index:'4', width:205, align:'center'}
												],
										pager: jQuery('#pager1'),
										rowNum:10,
										rowList:[10,20,30,40,50,60,70,80,90,100],
										//height:50,
										//width:20,
										autoheight: true,
										//autowidth: true,
										sortname: '1',
										viewrecords: true,
										id: 'pager1',
										onSelectRow: function(id){
											detEvi(id);
										},
										sortorder: "desc"
									}).navGrid('#pager1',{edit:false,add:false,del:false});
		
		//GRID Expedientes Compartidos
		jQuery("#gridExpCompartidos").jqGrid({ 
			url:'<%=request.getContextPath()%>/BusquedaExpCompartidosFuncionarioLog.do', 
			datatype: "xml", 
			colNames:['Expediente','Tipo','Fecha', 'Denunciante','Delito'], 
			colModel:[ 	{name:'Expediente',index:'1', width:30},
			           	{name:'Tipo',index:'5', width:120, align:'center'}, 
						{name:'Fecha',index:'2', width:25},
						{name:'Denunciante',index:'3', width:90}, 
						{name:'Delito',index:'4', width:45}
					],
			pager: jQuery('#pagerExpCompartidos'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			//height:50,
			//width:20,
			autoheight: true,
			autowidth: true,
			sortname: '1',
			viewrecords: true,
			//id: 'pager1',
			onSelectRow: function(id){
				detEvi(id);
			},
			sortorder: "desc"
		}).navGrid('#pagerExpCompartidos',{edit:false,add:false,del:false});
		//FIN GRID Expedientes Compartidos
		
		jQuery("#gridDetalleExpediente").jqGrid({ 
			url:'<%=request.getContextPath()%>/BusquedaInicialExpedientesGrid.do', 
			datatype: "xml", 
			colNames:['Expediente','Fecha','Denunciante','Delito'], 
			colModel:[ 	{name:'Expediente',index:'expediente', width:200},
			           	{name:'Fecha',index:'fecha', width:100},
			           	{name:'Denunciante',index:'denunciante', width:290}, 
						{name:'Delito',index:'delito', width:190}
			
					],
			pager: jQuery('#pagerExpediente'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerExpediente',{edit:false,add:false,del:false});

		//Grid de Solicitudes por atender
		jQuery("#gridSolsXAtndr").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['No. Caso','Expediente', 'Folio','Estatus','Fecha Creaci�n','Fecha Limite','Instituci�n','Remitente'], 
			colModel:[ 	{name:'caso',index:'caso', width:150,hidden:true},
			           	{name:'expediente',index:'expediente', width:180,align:'center'}, 
						{name:'folio',index:'folio', width:100,align:'center'}, 
						{name:'estatus',index:'estatus', width:100,align:'center'}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100,align:'center'},
						{name:'fechaLimite',index:'fechaLimite', width:100,align:'center',hidden:true},
						{name:'institucion',index:'institucion', width:100,align:'center'},
						{name:'remitente',index:'remitente', width:200,align:'center'}
					],
			pager: jQuery('#pagerGridSolsXAtndr'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autoheight: true,
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudes(rowid);
					}
		}).navGrid('#pagerGridSolsXAtndr',{edit:false,add:false,del:false});

		//Grid de Solicitudes generadas
		jQuery("#gridSolsGeneradas").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['No. Caso','Expediente', 'Folio','Estatus','Fecha Creaci�n','Fecha Limite','Instituci�n','Destinatario'], 
			colModel:[ 	{name:'caso',index:'caso', width:150,hidden:true},
			           	{name:'expediente',index:'expediente', width:180,align:'center'}, 
						{name:'folio',index:'folio', width:100,align:'center'}, 
						{name:'estatus',index:'estatus', width:100,align:'center'}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100,align:'center'},
						{name:'fechaLimite',index:'fechaLimite', width:100,align:'center',hidden:true},
						{name:'institucion',index:'institucion', width:100,align:'center'},
						{name:'remitente',index:'remitente', width:200,align:'center'}
					],
			pager: jQuery('#pagerGridSolsGeneradas'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autoheight: true,
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudesGen(rowid);
					}
		}).navGrid('#pagerGridSolsGeneradas',{edit:false,add:false,del:false});

		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		
		//Mandamos consultar los tipos de solicitud dependiendo del Area del Funcionario
		consultarTiposSolicitudPorAreaDelFuncionario('tableSolsXAtender',"<%=Areas.ATENCION_TEMPRANA_PG_PENAL.parseLong()%>");
		consultarTiposSolicitudPorAreaDelFuncionarioGen('tableSolsGeneradas',"0");

		$("#tableSolsXAtender").treeview();
		$("#tableSolsGeneradas").treeview();
		
		// BIND events to hard-coded buttons in the NORTH toolbar
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
		 $("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '400px');
		 $("#gview_gridDetalleExpediente .ui-jqgrid-bdiv").css('height', '410px');
		 $("#gview_gridSolsGeneradas .ui-jqgrid-bdiv").css('height', '400px');
		 $("#gview_gridSolsXAtndr .ui-jqgrid-bdiv").css('height', '400px');
		 $("#gview_gridExpCompartidos .ui-jqgrid-bdiv").css('height', '400px');
		 //$("#opcAgenda").click();
		 $('#test').weatherfeed(['MXDF0132']);
		});
		
	

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
		,	onresize_end:			function () { $("#gridDetalleFrmPrincipal").setGridWidth($("#mainContent").width() - 5, true); 
		  										  $("#gridDetalleExpediente").setGridWidth($("#mainContent").width() - 5, true);
		  										  $("#gridSolsXAtndr").setGridWidth($("#mainContent").width() - 5, true);
		  										  $("#gridSolsGeneradas").setGridWidth($("#mainContent").width() - 5, true);
		}
		}
	};
	

    var iframewindowAPSE = 0;
    
    function asigarPermisos(){
		$.newWindow({id:"iframewindowAPSE"+iframewindowAPSE, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Asignar permisos sobre Expediente: ", type:"iframe"});
		$.updateWindowContent("iframewindowAPSE"+iframewindowAPSE,'<iframe src="<%= request.getContextPath() %>/asigarPermisosExpediente.do" width="1430" height="670" />');
		$("#" +"iframewindowAPSE"+iframewindowAPSE + " .window-maximizeButton").click();
		iframewindowAPSE++;
    }
	
	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
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
	    			if(estatus=="posponer")
	    			{
	    				customAlert("Alarma pospuesta");
	    			}
	    			else if(estatus=="cancelar")
	    			{
	    				customAlert("Alarma cancelada");
	    			}
	    			else
	    			{
	    				customAlert("Alarma aceptada");
	    			}
	   		}
		});
	}

	function llamaraCambia(op,idAlerta){
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
	
	/************************************************    FUNCION PARA REGISTRAR DENUNCIA SIN TURNO        **************************************/
	
	function obtenTurnoSinPopup()
	{
		//generamos el turno
		//var params ='tipoTurno=0&esUrgente=false';
        var params ='tipoTurno=0&esUrgente=true';
		 $.ajax({
		      url: '<%= request.getContextPath()%>/generarConsultarTurnoAtencion.do',
	    	  dataType: 'xml',
	    	  Type: 'POST',
	    	  data:params,
	    	  async: false,
	    	  success: function(xml){ 
	        	  //revisamos si se genero el turno
	    		  if($(xml).find('turnoDTO').find('numeroTurno'))
	    		  {
	    			  idSiguienteTurno=$(xml).find('numeroTurno').text();
	    			  idTurno=$(xml).find('turnoId').text();
	    			  if(idSiguienteTurno!=null && idSiguienteTurno!="")
	    			  {
	    				  nuevaDenuncia();
	    			  }
	    		  }
	    		  else
	    		  {
	    			  customAlert("Ocurri� un error al intentar registrar la denuncia");
	    		  }
			  }
	    	});
	}
	/************************************************   FIN FUNCION PARA REGISTRAR DENUNCIA SIN TURNO        **************************************/
	
	function obtenTurnoPopup()
	{
		//Vamos a BD por el turno penal siguiente
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/BusquedaSiguienteTurno.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var errorCode;
    			errorCode=$(xml).find('response').find('code').text();
    			//Si errorCode=0 entonces continuamos con el flujo
    			if(parseInt(errorCode)==0)
    			{
    				//asignamos el numero de turno al span del popup
    				$("#spanNumTurno").html(''+$(xml).find('numeroTurno').text());
    				var tipoTurno="";
    				if($(xml).find('tipoTurno').text()=='PENAL')
    				{
    					tipoTurno="TURNO  PENAL  ";
    				}
    				else
    				{
    					tipoTurno="TURNO  NO PENAL";
    				}
    				if($(xml).find('esUrgente').text()=='true')
    				{
    					tipoTurno=tipoTurno+"URGENTE";
    				}
    				$("#spanTipoTurno").html(tipoTurno);
    				idSiguienteTurno=$(xml).find('numeroTurno').text();
    				idTurno=$(xml).find('turnoId').text();
    				//Generamos el popup
    				muestraPopupSiguienteTurno();
    			}
    		}
    	});
	}
	
	var isWindowOpen = false;
	
	function muestraPopupSiguienteTurno(){

				if(idSiguienteTurno==null||idSiguienteTurno==""){
					customAlert("No hay turnos por atender");
				}else{		
		//if (!isWindowOpen) {
			$( "#dialog-confirm" ).dialog({
				resizable: false,
				height:290,
				width:300,
				modal: true,
				buttons: {
					"Atender": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						nuevaDenuncia();
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
						cancelarTurnoPRCAN();
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();

		}
	}

	function cancelarTurnoPRCAN(){
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/CancelarTurno.do?turno='+idTurno,
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			//var option;
    			//idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			customAlert("Turno Cancelado");
    			//numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    		}
    		
    	});
	}
	
    function nuevaDenuncia() {
   
        var idExpediente;
        var numeroExpediente;
        var numeroExpedienteId;
        var numeroCasoNuevo;
		////ya estaba
        var idNuevaDenuncia = 1;
      //variable que indica si es un ingreso o una consulta
        var ingresoDenuncia = false;
       	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/nuevoExpedienteDenuncia.do?turno='+idTurno,
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').text();
    			numeroCasoNuevo=$(xml).find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text();
    		}
    		
    	});
       	var pantallaSolicitada=1;
    	idWindowNuevaDenuncia++;
		//if (idWindowNuevaDenuncia>3){
		//}
		//else{
			if(parseInt(idSiguienteTurno)>0){
				if(habilitarTurno==null || habilitarTurno=='null' || habilitarTurno==1)
				{
					$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente+" - Turno: "+idSiguienteTurno+" - No. Caso: "+numeroCasoNuevo, type:"iframe"});		
				}
				else
				{
					$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente+" - No. Caso: "+numeroCasoNuevo, type:"iframe"});
				}
			}
			else{
				isWindowOpen = true;
				$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente+" - No. Caso: "+numeroCasoNuevo, type:"iframe"});
			}
			var idIframe = "iframewindowCarpInvNuevaDenuncia" + idWindowNuevaDenuncia;
	    	$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?numeroGeneralCaso='+numeroCasoNuevo+'&abreenPenal=abrPenal&idNuevaDenuncia='+idNuevaDenuncia +'&ingresoDenuncia='+ingresoDenuncia +'&numeroExpediente='+numeroExpediente+'&pantallaSolicitada='+pantallaSolicitada+'&idNumeroExpedienteop='+numeroExpedienteId+'&idExpedienteop='+idExpediente+'&idIframe='+idIframe+'" width="1430" height="670" />');
		//}																																			
    }    																																				

	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudesGen(rowID){
		idWindowDetalleSolicitud++;
		$.newWindow({id:"iframewindowDetalleSolicitud"+idWindowDetalleSolicitud, statusBar: true, posx:200,posy:50,width:1100,height:450,title:"Detalle Solicitud", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud,'<iframe src="<%=request.getContextPath()%>/consultarDetalleSolicitudBandejaGen.do?idSolicitud=' +rowID +'&tipoUsuario=0 " width="1140" height="450" />'); 
	}
    
    function generarDocumento() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/generarDocumento.do" width="1140" height="400" />');
	    		
	}

    function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

    function seleccionFila(){

		$("#1").css({ color: "#FFFFFF", background: "#FBCB09" }); 
		$("#2").css({ background: "#dafafc", color: "#008000" });
			  
	}

	function seleccionFila2(){

		$("#2").css({ color: "#FFFFFF", background: "#FBCB09" }); 
		$("#1").css({ background: "#dafafc", color: "#FFA500" });
		   
	}

	//este acction se tiene ke colocar dentro del ke obtiene el expediente
	function generarTurno(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			{url:'<%=request.getContextPath()%>/BusquedaGenerarTurnosGrid.do', 
			datatype: "xml" });
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	}

		function guardaNombre(){
			jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
					{url:'<%=request.getContextPath()%>/EjemploAtencionTempranaNuevolleno.xml', 
					datatype: "xml" });
			  $("#gridDetalleFrmPrincipal").trigger("reloadGrid");
		}
		function cancelar(){
			jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
					{url:'<%=request.getContextPath()%>/CancelarTurnosGrid.do',
	datatype : "xml"
		});
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
	}

	function detEvi(id) {
	 	var pantallaSolicitada=1;
		idWindowNuevaDenuncia++;
		 var ingresoDenuncia = true;
		 var idIframe = "iframewindowCarpInvNuevaDenuncia" + idWindowNuevaDenuncia;
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: ", type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'&idIframe='+idIframe+'" width="1430" height="670" />');
		//var numExpConsul='<%= request.getSession().getAttribute("numExpConsul")%>';
		//$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Expediente: "+numExpConsul);
		 $("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+ " .window-maximizeButton").click();
		}

	function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Expediente: "+num);
	}

	function activaExpediente() {
		//$("#expedientePrincipal").css("display", "block");
		//$("#turnoPrincipal").css("display", "none");
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			{url:'<%=request.getContextPath()%>/BusquedaInicialTurnosGrid.do', 
			datatype: "xml" });
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
		 ocultaMuestraGrids("turnoPrincipal");
	}
	
	/*
	*Funcion para recargar el grid de expedientes compartidos desde el arbol izquierdo
	*/
	function activaExpCompartidos()
	{
		jQuery("#gridExpCompartidos").jqGrid('setGridParam',  
				{url:'<%=request.getContextPath()%>/BusquedaExpCompartidosFuncionarioLog.do', 
				datatype: "xml" });
			 $("#gridExpCompartidos").trigger("reloadGrid"); 
			 ocultaMuestraGrids("expCompartidos");
			$("#gridExpCompartidos").setGridWidth($("#mainContent").width() - 5, true);
			$("#gview_gridExpCompartidos .ui-jqgrid-bdiv").css('width', '900px');
	}
	
	function activaExpedienteSinFecha() {
		//$("#expedientePrincipal").css("display", "block");
		//$("#turnoPrincipal").css("display", "none");
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			{url:'<%=request.getContextPath()%>/BusquedaInicialTurnosGridSinFecha.do', 
			datatype: "xml" });
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
		 ocultaMuestraGrids("turnoPrincipal");
	}

	function activaTurno() {
		$("#expedientePrincipal").css("display", "none");
		$("#turnoPrincipal").css("display", "block");
		$("#divGridSolsGeneradas").hide();
		$("#divGridSolsXAtndr").hide();
	}

	function buscaExpedientes(num){
		if(num=='A1'){
			$("#A1").css({ color: "#E78F08"});
			$("#A2").css({ color: "#1C94C4"});
			$("#A3").css({ color: "#1C94C4"});
			
			jQuery("#gridDetalleExpediente").jqGrid('setGridParam',  
					{url:'<%=request.getContextPath()%>/BusquedaExpedientesMesGrid.do', 
					datatype: "xml" });
				 $("#gridDetalleExpediente").trigger("reloadGrid"); 
		}else if(num=='A2'){
			$("#A1").css({ color: "#1C94C4"});
			$("#A2").css({ color: "#E78F08"});
			$("#A3").css({ color: "#1C94C4"});
		}else  if(num=='A3'){
			$("#A1").css({ color: "#1C94C4"});
			$("#A2").css({ color: "#1C94C4"});
			$("#A3").css({ color: "#E78F08"});
		}

		if(num=='B1'){
			$("#B1").css({ color: "#E78F08"});
			$("#B2").css({ color: "#1C94C4"});
			$("#B3").css({ color: "#1C94C4"});
			
			jQuery("#gridDetalleExpediente").jqGrid('setGridParam',  
					{url:'<%=request.getContextPath()%>/BusquedaExpedientesMesGrid.do', 
					datatype: "xml" });
				 $("#gridDetalleExpediente").trigger("reloadGrid"); 
		}else if(num=='B2'){
			$("#B1").css({ color: "#1C94C4"});
			$("#B2").css({ color: "#E78F08"});
			$("#B3").css({ color: "#1C94C4"});
		}else  if(num=='B3'){
			$("#B1").css({ color: "#1C94C4"});
			$("#B2").css({ color: "#1C94C4"});
			$("#B3").css({ color: "#E78F08"});
		}				
		
	}

	//Funcion ke rellena los datos del grid con los expedientes ke se encuentran en restaurativa
	function gridRestaurativa(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			{url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=JAR&actividad=ATENDER_', 
			datatype: "xml" });
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	}

	//Funcion ke rellena los datos del grid con los expedientes ke se encuentran en unidad de investigacion
	function gridUnidadInvestigacion(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			{url:'<%=request.getContextPath()%>/BusquedaCanalizadosUnidadInvestigacion.do', 
			datatype: "xml" });
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	}
	
	function ocultaMuestraGrids(idDivGrid) 
	{
		if(idDivGrid == "turnoPrincipal"){
			$("#turnoPrincipal").show();
			$("#expedientePrincipal").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridSolsXAtndr").hide();
		}
		if(idDivGrid == "expedientePrincipal"){
			$("#turnoPrincipal").hide();
			$("#expedientePrincipal").show();
			$("#divGridSolsGeneradas").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridSolsXAtndr").hide();
		}
		if(idDivGrid == "gridSolsXAtndr"){
			$("#divGridSolsXAtndr").show();
			$("#turnoPrincipal").hide();
			$("#expedientePrincipal").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridExpCompartidos").hide();
		}
		if(idDivGrid == "gridSolsGeneradas"){
			$("#turnoPrincipal").hide();
			$("#expedientePrincipal").hide();
			$("#divGridSolsGeneradas").show();
			$("#divGridExpCompartidos").hide();
			$("#divGridSolsXAtndr").hide();
		}
		if(idDivGrid == "expCompartidos"){
			$("#turnoPrincipal").hide();
			$("#expedientePrincipal").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridExpCompartidos").show();
			$("#divGridSolsXAtndr").hide();
		}
		
	}

	/*
	 *Funcion para consultar los tipos de solicitud y generar 
	 * el arbol dinamico de los tipos de solicitud en el menu izquierdo
	 * param - nombre del elemento en el que se construira de manera dinamica
	 * los tipos de solicutd
	 */
	function consultarTiposSolicitudPorAreaDelFuncionario(idDivArbol,idArea)
	{
		//limpiamos el menu de los tipos de solicitud
		$("#"+idDivArbol).empty();
		//lanzamos la consulta del tipo de solicitudes
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultaTiposSolsXArea.do',
			data: 'idArea='+idArea,
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('ValorDTO').each(function(){
					var idCampo = $(this).find("idCampo").text();
					var trTabla = '<li class="closed">';
					trTabla = trTabla + '<span class="folder">'+$(this).find("valor").text()+'</a></span>';
					trTabla = trTabla + '<ul>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsXAtndr('+idCampo+','+idArea+','+<%=EstatusSolicitud.ABIERTA.getValorId()%>+')">Abierta</a></span></li>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsXAtndr('+idCampo+','+idArea+','+<%=EstatusSolicitud.CERRADA.getValorId()%>+')">Cerrada</a></span></li>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsXAtndr('+idCampo+','+idArea+','+<%=EstatusSolicitud.EN_PROCESO.getValorId()%>+')">En proceso</a></span></li>';
					trTabla = trTabla + '</ul>';
					trTabla = trTabla + '</li>';
					$('#'+idDivArbol).append(trTabla);
				});
			}
			
		});
	}
	
	/*
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsXAtndr(tipoSolicitud,idArea,estatus)
	{
		jQuery("#gridSolsXAtndr").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsXAtnder.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus='+estatus+'',datatype: "xml" });
		$("#gridSolsXAtndr").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsXAtndr");
	}
	
	/*
	 *Funcion para consultar los tipos de solicitud y generar 
	 * el arbol dinamico de los tipos de solicitud en el menu izquierdo
	 * param - nombre del elemento en el que se construira de manera dinamica
	 * los tipos de solicutd
	 */
	function consultarTiposSolicitudPorAreaDelFuncionarioGen(idDivArbol,idArea)
	{
		//limpiamos el menu de los tipos de solicitud
		$("#"+idDivArbol).empty();
		//lanzamos la consulta del tipo de solicitudes
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultaTiposSolsXArea.do',
			data: 'idArea='+idArea,
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('ValorDTO').each(function(){
					var idCampo = $(this).find("idCampo").text();
					var trTabla = '<li class="closed">';
					trTabla = trTabla + '<span class="folder">'+$(this).find("valor").text()+'</a></span>';
					trTabla = trTabla + '<ul>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsGeneradas('+idCampo+','+idArea+','+<%=EstatusSolicitud.ABIERTA.getValorId()%>+')">Abierta</a></span></li>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsGeneradas('+idCampo+','+idArea+','+<%=EstatusSolicitud.CERRADA.getValorId()%>+')">Cerrada</a></span></li>';
					trTabla = trTabla + '<li><span class="file"><a onclick="cargaGridSolsGeneradas('+idCampo+','+idArea+','+<%=EstatusSolicitud.EN_PROCESO.getValorId()%>+')">En proceso</a></span></li>';
					trTabla = trTabla + '</ul>';
					trTabla = trTabla + '</li>';
					$('#'+idDivArbol).append(trTabla);
				});
		}
			
		});
		}

	var idWindowDetalleSolicitud=1;
	 
	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudes(rowID){
		idWindowDetalleSolicitud++;
		$.newWindow({id:"iframewindowDetalleSolicitud"+idWindowDetalleSolicitud, statusBar: true, posx:200,posy:50,width:1100,height:450,title:"Detalle Solicitud", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud,'<iframe src="<%=request.getContextPath()%>/consultarDetalleSolicitudBandeja.do?idSolicitud=' +rowID +'&tipoUsuario=1 " width="1140" height="450" />'); 
		}
	
	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudesGen(rowID){
		idWindowDetalleSolicitud++;
		$.newWindow({id:"iframewindowDetalleSolicitud"+idWindowDetalleSolicitud, statusBar: true, posx:200,posy:50,width:1100,height:450,title:"Detalle Solicitud", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud,'<iframe src="<%=request.getContextPath()%>/consultarDetalleSolicitudBandejaGen.do?idSolicitud=' +rowID +'&tipoUsuario=0 " width="1140" height="450" />'); 
		}
		
	
	/*
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsGeneradas(tipoSolicitud,idArea,estatus)
	{
		jQuery("#gridSolsGeneradas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsGeneradas.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus='+estatus+'',datatype: "xml" });
		$("#gridSolsGeneradas").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsGeneradas");
	}

	function solonumeros(e) {
		var unicode = e.charCode ? e.charCode : e.keyCode;

		// if the key is backspace, tab, or numeric
		if (unicode == 8 || unicode == 9 || (unicode >= 48 && unicode <= 57)) {
		// we allow the key press
		return true;
		}
		else {
		// otherwise we don't
		return false;
		}
		}
	
	/*************  FUNCION PARA ABRIR LA VENTANA DE CAMBIO DE PASSWORD *************/
	function abreVentanaModificarContrasena()
	{
		$.newWindow({id:"iframewindowModificarPwdUsuario", statusBar: true, posx:400,posy:90,width:380,height:280,title:"Modificar Contrase�a", type:"iframe"});
	    $.updateWindowContent("iframewindowModificarPwdUsuario",'<iframe src="<%=request.getContextPath()%>/cambiarContrasena.do?administrador=4" width="380" height="280" />');
	}
	
	function cerrarVentanaModificarContrasena()
	{
		var pantalla ="iframewindowModificarPwdUsuario";
		$.closeWindow(pantalla);
	}
	/************* fin  FUNCION PARA ABRIR LA VENTANA DE CAMBIO DE PASSWORD *************/
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
					//alert(rolnuevo);
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

<body background="#393939">

   <div class="ui-layout-west">
		
	<div class="header">&nbsp;</div>
		
	   <div class="content" style="width: 200px;">
		  <div id="accordionmenuprincipal">
		     <h3><a href="#" id="idExpedientes"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">Expedientes</a></h3>
		     <div>
		         <ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="folder" id="delDia" >Del d&iacute;a</span>
					<ul></ul>
					</li>
					<li class="closed"><span class="folder" id="restaura" >Canalizados a Conciliaci&oacute;n/Mediaci&oacute;n</span>
					<ul></ul>
					</li>
					<li class="closed"><span class="folder" id="unidadInvestiga" >Canalizados a Unidad de Investigaci&oacute;n</span>
					<ul></ul>
					</li>
					<li class="closed"><span class="folder" id="delTodos" >Todos</span>
					<ul></ul>
					<li class="closed"><span class="folder" id="expCompartidos" >Expedientes compartidos</span>
					<ul></ul>
				</ul>
			 </div>
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes por Atender</a></h3>
			<div>
				<ul id="tableSolsXAtender" style="cursor:pointer" class="filetree">
				</ul>
			</div>
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes Generadas</a></h3>
			<div>
				<ul id="tableSolsGeneradas" style="cursor:pointer" class="filetree">
				</ul>
		  </div>
		</div>
	</div>
	</div>
		
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
		<!-- <h4><a href="#">Calendario</a></h4>
		<div>
			<center>
				<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
			</center>
		</div>-->
		<h6><a href="#" id="opcAgenda">Agenda</a></h6>
		<div>
		<center>
			<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
		</center>
		<br />
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
		<!-- div class="footer">&nbsp;</div --></div>
		
		
		<div class="ui-layout-north">
		<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ATENCION TEMPRANA</TD>
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
			<li id="tbarBtnConsultarTurnoAtencion"><span></span>Atenci&oacute;n&nbsp;<!--img src="<%=request.getContextPath() %>/resources/images/icn_turno.png" id="botpenal" width="16" height="14"--></li>
			<li id="tbarBtnConsultarTurnoAtencionDesh"><span></span>Registrar Denuncia&nbsp;<!--img src="<%=request.getContextPath() %>/resources/images/icn_turno.png" id="botpenal" width="16" height="14"--></li>
<li id="tbarBtnAsignar" class="first"><span></span>Asignar Permisos a Subordinados&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="10" height="16"--></li>
			</div>
			<div id="menu_config">
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
		
			<div id="turnoPrincipal">
				<table id="gridDetalleFrmPrincipal"></table>
				<div id="pager1"></div>
			</div>
			<div id="expedientePrincipal" style="display: none;" >
				<table id="gridDetalleExpediente" ></table>
				<div id="pagerExpediente"></div>
			</div>
			<div id="divGridSolsXAtndr" style="display: none;">
			 	<table id="gridSolsXAtndr"></table>
				<div id="pagerGridSolsXAtndr"></div>
			</div>
			<div id="divGridSolsGeneradas" style="display: none;">
			 	<table id="gridSolsGeneradas"></table>
				<div id="pagerGridSolsGeneradas"></div>
			</div>
			<!-- GRID Expedientes compartidos -->
			<div id="divGridExpCompartidos" style="display: none;">
			 	<table id="gridExpCompartidos"></table>
				<div id="pagerExpCompartidos"></div>
			</div>
			<div>
				
			</div>
		</div>
		
		</div>
		</div>

	<div id="dialog-confirm" title="Turno: ">
		<p align="center"><span style="font-size: 25px;" id="spanTipoTurno">.</span><br/><span style="font-size: 115px;" id="spanNumTurno">15</span></p>
	</div>

	<div id="dialog-logout" title="Logout">
		<p align="center">
			<span id="logout">&iquest;Desea cerrar su sesi&oacute;n?</span>
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
</html>