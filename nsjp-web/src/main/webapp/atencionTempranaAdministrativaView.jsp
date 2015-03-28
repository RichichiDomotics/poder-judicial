<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<html>
<head> 
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
	var tur;
	var outerLayout, innerLayout;
	var grid=0;
	var idWindowNuevaDenunciaTempAdmin=1;
	var idSiguienteTurno=0;
	var idTurnoTempAdmin;
	
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

		$("#tbarBtnRegistrarDenuncia").hide();
		var habilitarTurno = '<%=request.getSession().getAttribute("KEY_SESSION_HABILITAR_TURNO")%>';

		if(habilitarTurno == 0 || habilitarTurno == "0"){
			$("#tbarBtnRegistrarDenuncia").show();
			$("#tbarBtnConsultarTurnoAtencion").hide();
		}

	
		$("#dialogBlok").dialog({
			autoOpen: false,
			modal: true,
			width: 400,
			height: 200,
			closeOnEscape: false,
			draggable: false,
			resizable: false,
			buttons: {
				'\u00a1Autenticarse!': function(){
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

		$("#tbarBtnNuevo").click(muestradatospersona);
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();	
		$("#controlAgenda").click(creaAgenda);
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		$("#tbarBtnConsultarTurnoAtencion").click(obtenTurnoSinPopup);
		$("#tbarBtnRegistrarDenuncia").click(obtenTurnoSinPopup);
		//$("#tbarBtnRegistrarDatosDePersona").click(datosPersona);
		$("#buscarCaso").click(buscarCaso);	
		$("#buscarExpediente").click(buscarExpediente);
		
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
		$("#divDatos").css("display", "none");
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
										url:'<%= request.getContextPath()%>/BusquedaInicialTurnosGridAdministrativa.do', 
										datatype: "xml", 
										colNames:['Expediente','Fecha', 'Solicitante del servicio','idInvo'], 
										colModel:[ 	{name:'Expediente',index:'1', width:20}, 
													{name:'Fecha',index:'2', width:20}, 
													{name:'Denunciante',index:'3', width:100},
													{name:'idInvo',index:'4', width:100, hidden:true}
													
												],
										pager: jQuery('#pager1'),
										rowNum:10,
										rowList:[10,20,30],
										autowidth: true,
										sortname: 'turno',
										viewrecords: true,
										onSelectRow: function(id){
											
											//var ret = jQuery("#gridDetalleFrmPrincipal").jqGrid('getRowData',id); 

											//var posicion1 = ret.indexOf(">",0); 

										//	ret.substring(0,posicion1);
										
											//alert("IDiNVO "+ret.idInvo +"IDeXP "+id);
											consultaDatosPersona(id);
											},
										sortorder: "desc"
									}).navGrid('#pager1',{edit:false,add:false,del:false});	 

		  $("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '410px');
							
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
			
			$("#opcAgenda").click();			
			
			/* $("#exp01").click(creaExpediente01);
			$("#exp02").click(creaExpediente02);	 */		
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
		,	onresize_end:			function () { $("#gridDetalleFrmPrincipal").setGridWidth($("#mainContent").width() - 5, true); }
		}
	};
	 
    function muestradatospersona(){
		$("#divDatos").css("display", "block");
		$("#divTurno").css("display", "none");
		
		
	}

	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
	}

    function buscarExpediente() {
		$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do?opcionNoPenal=true" width="653" height="400" />');		
	}

	function buscarCaso() {
		$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
	}
	
    function generarDocumentoSinCaso() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Acta", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do" width="1140" height="400" />');
	   		
	}

   	
	 function nuevaDenuncia() {
		 
        var idExpedienteTempAdmin;
        var numeroExpedienteTempAdmin;
        var numeroExpedienteIdTempAdmin;
		////ya estaba
        var idNuevaDenunciaTempAdmin = 1;
      //variable que indica si es un ingreso o una consulta
        var ingresoDenunciaTempAdmin = false;
       	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/nuevoExpedienteDenunciaAtTempAdmin.do?turnoTempAdmin='+idTurnoTempAdmin+'',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			idExpedienteTempAdmin=$(xml).find('expedienteDTO').find('expedienteId').text();
    			numeroExpedienteTempAdmin=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			numeroExpedienteIdTempAdmin=$(xml).find('expedienteDTO').find('numeroExpedienteId').text();
    		}
    	});
       	//var pantallaSolicitada=1;
    	idWindowNuevaDenunciaTempAdmin++;
    	
		//if (idWindowNuevaDenuncia>3){
		//	alert("Ya se abrio una ventana");
		//}
		//else{
			if(parseInt(idSiguienteTurno)>0){
				if(<%=request.getSession().getAttribute("KEY_SESSION_HABILITAR_TURNO")%> == 0 || <%=request.getSession().getAttribute("KEY_SESSION_HABILITAR_TURNO")%> == "0"){
					$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaTempAdmin, statusBar: true, posx:0,posy:0,width:1100,height:450,title:"Expediente: "+numeroExpedienteTempAdmin, type:"iframe"});
				}else{
					$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaTempAdmin, statusBar: true, posx:0,posy:0,width:1100,height:450,title:"Expediente: "+numeroExpedienteTempAdmin+" - Turno: "+idSiguienteTurno, type:"iframe"});
				}
			}else{
				isWindowOpen = false;
				$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaTempAdmin, statusBar: true, posx:0,posy:0,width:1100,height:450,title:"Expediente: "+numeroExpedienteTempAdmin, type:"iframe"});
			}
	    	$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaTempAdmin,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedioTempAdmin.do?abreenPenalTempAdmin=abrPenalTempAdmin&idNuevaDenunciaTempAdmin='+idNuevaDenunciaTempAdmin +'&ingresoDenunciaTempAdmin='+ingresoDenunciaTempAdmin +'&numeroExpedienteTempAdmin='+numeroExpedienteTempAdmin+'&idNumeroExpedienteopTempAdmin='+numeroExpedienteIdTempAdmin+'&idExpedienteTempAdmin='+numeroExpedienteIdTempAdmin+'&idExpedienteopTempAdmin='+idExpedienteTempAdmin+'" width="1100" height="450" />');
		//}		
	    	 $("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaTempAdmin+ " .window-maximizeButton").click();																																	
    }  

	 function consultaDatosPersona(id) {
	 
	 		var id2 = jQuery("#gridDetalleFrmPrincipal").jqGrid('getGridParam','selrow');
	 		var ret2 = jQuery("#gridDetalleFrmPrincipal").jqGrid('getRowData',id2);
	 		
	 		//obtenemos el numero unido del expediente
			var pos1=ret2.Expediente.indexOf(">",0);
			var pos2=ret2.Expediente.indexOf("<",1);
			var numeroExpedienteAtTemp=ret2.Expediente.substring(pos1+1,pos2);
			$.newWindow({id:"iframewindowRegistraDatosPersona", statusBar: true, posx:200,posy:50,width:1140,height:450,title:"Acta Circunstanciada Exp::  "+numeroExpedienteAtTemp, type:"iframe"});
		    $.updateWindowContent("iframewindowRegistraDatosPersona",'<iframe src="<%= request.getContextPath() %>/BusquedaExpedienteAdminAt.do?formaId=2&numeroExpedienteTempAdmin='+ numeroExpedienteAtTemp +'&idExpedienteTempAdmin='+id+'&operacion=CONSULTA " width="1140" height="450" />');
		    $("#" +"iframewindowRegistraDatosPersona .window-maximizeButton").click();	
	}

	 function cerrarVentanaRegistrarDatosPersona(){
		 $.closeWindow('iframewindowRegistraDatosPersona');
		 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	 }

	 function obtenTurnoPopup()
		{
			//Vamos a BD por el turno penal siguiente
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/BusquedaSiguienteTurnoAdmin.do',
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
	    				idTurnoTempAdmin=$(xml).find('turnoId').text();
	    				//Generamos el popup
	    				muestraPopupSiguienteTurno();
	    			}
	    		}
	    	});
		}

	 function muestraPopupSiguienteTurno(){

		 if(idSiguienteTurno==null||idSiguienteTurno==""){

				alert("No hay turnos por atender");


				}else{	
					
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

			}
		}

	 function cancelarTurnoPRCAN(){
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/CancelarTurno.do?turno='+idTurnoTempAdmin+'',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			//var option;
	    			//idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
	    			alert("Turno Cancelado");
	    			//numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
	    			//alert(numeroExpediente);
	    		}
	    		
	    	});
		}
	
	    function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
		}

	    function consultaDelDia()
	    {
	    	jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/BusquedaInicialTurnosGridAdministrativa.do',datatype: "xml" });
			$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
	    }
	    function consultaTodos()
	    {
	    	jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/BusquedaInicialTurnosGridAdministrativaSinFecha.do',datatype: "xml" });
			$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
	    }
	    // $("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+ " .window-maximizeButton").click();
	    
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

/************************************************ FUNCION PARA REGISTRAR DENUNCIA SIN TURNO **************************************/

	function obtenTurnoSinPopup(){
		//generamos el turno
		var params ='tipoTurno=1&esUrgente=false';
		$.ajax({
			url: '<%= request.getContextPath()%>/generarConsultarTurnoAtencion.do',
			dataType: 'xml',
			Type: 'POST',
			data:params,
			async: false,
			success: function(xml){
				//revisamos si se genero el turno
				if($(xml).find('turnoDTO').find('numeroTurno')){
					idSiguienteTurno=$(xml).find('numeroTurno').text();
					idTurnoTempAdmin=$(xml).find('turnoId').text();
					if(idSiguienteTurno!=null && idSiguienteTurno!=""){
						nuevaDenuncia();
					}
				}else{
					alert("Ocurri\u00f3 un error al intentar registrar la denuncia.");
				}
			}
		});
	}
/************************************************ FIN FUNCION PARA REGISTRAR DENUNCIA SIN TURNO **************************************/
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

	<div class="content" style="width: 200px;">
		<div id="accordionmenuprincipal" >
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes</a></h3>
			<div>			
				<ul id="seccion2tree" class="filetree">
					<li class="closed" onclick="consultaTodos()"><span id="anio1" class="folder">Todos</span>
						<ul>
							
						</ul>
					</li>
					<li class="closed" onclick="consultaDelDia()"><span id="anio2" class="folder">Del d&iacute;a</span>
						<ul>
							
						</ul>
					</li>
				</ul>		
			</div>	
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Reportes Estad&iacute;sticos</a></h3>
			<div>			
				<ul>
				</ul>
			</div>			
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div-->
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
					<br/>
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
	<!-- div class="footer">&nbsp;</div -->
</div>


<div class="ui-layout-north">
	<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ATENCION TEMPRANA NO PENAL</TD>
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
			<li id="tbarBtnRegistrarDenuncia"><span></span>Registrar Denuncia&nbsp;<!--img src="<%=request.getContextPath() %>/resources/images/icn_turno.png" id="botpenalReg" width="16" height="14"--></li>
		</div>
		<div id="menu_config">
			<li id="buscarExpediente">Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
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
					<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1" style=" vertical-align: top;"></div>
				</div>
				<div  id="includeRegistrarDatos" style="display:none;"  class="ui-layout-north">
					
					
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
					<td colspan="2">La sesi&oacute;n se a bloqueado introduce tu contrase&ntilde;a para desbloquear.</td>
					
				</tr>
				<tr>
					<td align="right"><label style="color:#4A5C68">Contrase&ntilde;a:</label></td>
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

			<p>&iquest;Desea continuar con la sesi&oacute;n?</p>
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