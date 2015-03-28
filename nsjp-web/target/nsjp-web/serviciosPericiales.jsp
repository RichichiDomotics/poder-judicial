<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<html>
<head>
	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

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
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	var outerLayout, innerLayout;
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
		
		$("#casos").css({ color: "#1C94C4"});
		$("#idEspecialidad").css({ color: "#1C94C4"});
		$("#dictamen01").css({ color: "#1C94C4"});
		$("#informe01").css({ color: "#1C94C4"});
		
		$("#idEspecialidadPrimaria").css({ color: "#1C94C4"});
		$("#idPerito").css({ color: "#1C94C4"});
		$("#dictamen02").css({ color: "#1C94C4"});
		$("#informe02").css({ color: "#1C94C4"});
		
		$("#solictud1").css({ color: "#1C94C4"});
		$("#solictud2").css({ color: "#1C94C4"});
		$("#A4").css({ color: "#1C94C4"});
		$("#A5").css({ color: "#1C94C4"});
		$("#B1").css({ color: "#1C94C4"});
		$("#B2").css({ color: "#1C94C4"});
		$("#B3").css({ color: "#1C94C4"});
		$("#B4").css({ color: "#1C94C4"});
		$("#B5").css({ color: "#1C94C4"});
		
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
				
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
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
		
		muestraGadgets();
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
								url:'<%= request.getContextPath()%>/EjemploPericiales.xml', 
								datatype: "xml", 
								colNames:['No.Caso','Fecha', 'Tipo de Especialidad', 'Prueba Pericial','Fecha Vencimiento'], 
								colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
											{name:'Fecha',index:'fecha', width:20}, 
											{name:'Nombre',index:'nombre', width:50}, 
											{name:'Resumen',index:'Resumen', width:100},
											{name:'Vencimiento',index:'vencimiento', width:20}
										],
								pager: jQuery('#pager1'),
								rowNum:10,
								rowList:[10,20,30],
								autowidth: true,
								sortname: 'detalle',
								viewrecords: true,
								sortorder: "desc"
							}).navGrid('#pager1',{edit:false,add:false,del:false});		

							
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
		cargaOcupacion();
		//cargaCasos();
		$( "#tabsprincipalconsulta" ).tabs();
			
			//$("#exp01").click(creaExpediente01);
			$("#exp02").click(creaExpediente02);			
			$("#controlAgenda").click(creaAgenda);
				
			$("#buscarExpediente").click(buscarExpediente);
			$('#casos li').click(agregaExpediente);		
			$("#buscarCaso").click(buscarCaso);	
			$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);	
			//$('#casos1 li').click(justiciaRestaurativa);	
			$("#generarDocumento").click(generarDocumentoViwe);
			//$("#entrevista").click(generaCapturaEntrevista);
			$("#idTipoBusqueda").click(buscarPerito);
			$("#dictamen01").click(seleccionFila);
			$("#idSolicitud2").click(idSolicitud2);
			$("#idSolicitud").click(idSolicitud2);
			
				
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);

		$("#textoSolicitud").hide();
		$("#textosPericiales").show();
		$('#test').weatherfeed(['MXDF0132']);

		});

		function verExpediente(idExpediente, numeroExpediente) {
			$.newWindow({id:"iframewindowExp" + idExpediente, statusBar: true, posx:200,posy:50,width:1140,height:400,title:numeroExpediente, type:"iframe"});
		    $.updateWindowContent("iframewindowExp" + idExpediente,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');			
		}
				
		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
		}

		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
		}
		
		function creaExpediente01() {
			$.newWindow({id:"iframewindowExp01", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"EXP 00000001", type:"iframe"});
		    $.updateWindowContent("iframewindowExp01",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');		
		}
		
		function creaExpediente02() {
			$.newWindow({id:"iframewindowExp02", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"EXP 00000002", type:"iframe"});
		    $.updateWindowContent("iframewindowExp02",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');		
		}
	
		function creaAgenda() {
			$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
		    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
		}

		function nuevaDenuncia() {
			$.newWindow({id:"iframewindowNuevaDenuncia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Nueva Denuncia", type:"iframe"});
		    $.updateWindowContent("iframewindowNuevaDenuncia",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?idNuevaDenuncia=1" width="1140" height="400" />');		
		}
		
		 
		function justiciaRestaurativa() {
			$.newWindow({id:"iframewindowjusticia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Justicia Restaurativa", type:"iframe"});
		    $.updateWindowContent("iframewindowjusticia",'<iframe src="<%= request.getContextPath() %>/JusticiaRestaurativa.do" width="1140" height="400" />');		
		}

		function generarDocumentoViwe() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
		    		
		}

		function buscarPerito() {
			$.newWindow({id:"iframewindowBuscarPerito", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Buscar Perito", type:"iframe"});
		    $.updateWindowContent("iframewindowBuscarPerito",'<iframe src="<%= request.getContextPath() %>/buscarPerito.do" width="1140" height="400" />');
		    		
		}

		function seleccionFila(){
				$("#1").css({ color: "#FFFFFF", background: "#FF0000" });   
		}
		
		function generarDocumento() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
		    		
		}
		function idSolicitud2(){
			$("#textoSolicitud").show();
			$("#textosPericiales").hide(); 
	}
		
		
		function cargaOcupacion(){
		    	//alert("cargaOcupacion");
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
		    		data: '',
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

		var casoAbierto = Array();
		
		function agregaExpediente (idCaso) {	
			//alert("agregarExpediente:" + idCaso)	
			if (casoAbierto[idCaso ] != true) {
				$.ajax({
		    		type: 'POST',
		    		url: '<%= request.getContextPath()%>/BusquedaCaso.do',
		    		data: 'idCaso=' + idCaso,
		    		dataType: 'xml',
		    		async: true,
		    		success: function(xml){
		    			$(xml).find('expediente').each(function(){
		    				var branches = $("<ul><li id='" + $(this).find('expedienteId').text() + "EXP' onclick='verExpediente(" + $(this).find('expedienteId').text() + ",\"" + $(this).find('numeroExpediente').text() + "\")'><span class='file'>" + $(this).find('numeroExpediente').text() + "</span><ul></ul></li></ul>").appendTo("#" + idCaso + "CASO");
		    				$("#" + idCaso + "CASO").treeview({
		    					add: branches
		    				});
			    		});
		    		}		    		
		    	});
			}
			casoAbierto[idCaso] = true;
		}

		function cargaCasos(){
	    	var branches = "";
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/BusquedaInicialCaso.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: true,
	    		success: function(xml){
	    			var branches = "";
					$(xml).find('caso').each(function(){
						var casoId = $(this).find('casoId').text();
						var numeroGralCaso = $(this).find('numeroGeneralCaso').text();
	    				branches ="<ul><li class='closed' id='" + casoId + "CASO' onclick='agregaExpediente(" + casoId + ")'><span class='folder'>" + numeroGralCaso + "</span><ul></ul></li></ul>";
						var casos = $(branches).appendTo("#casos");
						$("#seccion1tree").treeview({
	    					add: casos
	    				});
	    			});
	    		}
	    		
	    	});
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
		,	onresize_end:			function () { $("#gridDetalleFrmPrincipal").setGridWidth($("#mainContent").width() - 5, true); }		
		}
	};
	
	/*
	*Listener del click para la redireccion a la valoracion de hechos
	*/
	function realizarValoracionHechos()
	{
		location.href='<%= request.getContextPath()%>/RealizarValoracionHechos.do';
	}
	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}	
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
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Casos</a></h3>
			<div>			
				<ul id="seccion1tree" class="filetree">
					<li class="closed" id="casos"><span class="folder">Casos</span>
						<ul>
							<li class="closed" id="idEspecialidad"><span class="folder">Especialidad</span>
								<ul>
									<li><span class="file"><a id="dictamen01">Dictámenes</a></span></li>
									<li><span class="file"><a id="informe01">Informes</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="closed" id="idEspecialidadPrimaria"><span class="folder">Especialidad</span>
						<ul>
							<li class="closed" id="idPerito"><span class="folder">Perito</span>
								<ul>
									<li><span class="file"><a id="dictamen02">Dictámenes</a></span></li>
									<li><span class="file"><a id="informe02">Informes</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>		
			</div>
			<h3><a href="#" id="hrefDesignarPerito"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Revisar Solicitudes</a></h3>
			<div>
				<!-- <ul id="seccion6tree" class="filetree">
					<li class="closed" id="idSolicitud"><span class="file">Solicitud 1</span></li>
					<li class="closed" id="idSolicitud2"><span class="file">Solicitud 2</span></li>
				</ul>-->
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer">
						<tr>
						   <td width="100%" id="solictud1"><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" />Solictud 1</td>
						</tr>
						<tr>
						   <td width="100%" id="solictud2"><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" />Solicitud 2</td>
						</tr>
					</table>
			</div>
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div-->
</div>

<div class="ui-layout-east">
		<div class="header">Bienvenido</div>
		<div class="content">
			<div id="accordionmenuderprincipal">
				<h4>
					<a href="#" class="txt_acordeon">Datos Personales</a>
				</h4>
				<div>				
					<center>
						 <jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
					</center>					
				</div>
				<!-- <h4>
					<a href="#" class="txt_acordeon">Calendario</a>
				</h4>
				<div>
					<center>
						<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
					</center>
				</div>-->
				<h4>
					<a href="#" class="txt_acordeon">Agenda</a>
				</h4>
				<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
					</center>
					<br />
					
				</div>
				<h4>
					<a href="#" class="txt_acordeon">Clima</a>
				</h4>
				<div align="left">
					<div align="left" id="test"></div>
				</div>
				<h4>
					<a href="#" class="txt_acordeon">Chat</a>
				</h4>
				<div align="center">
				
					<div id="dialogoChat" title="Chat" align="center">
						<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
					</div>
					<center>
						<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
					</center>
						<!--iframe src="http://gaby1:5280/web/jwchat/index.html" frameborder="0" width="200" height="200" scrolling="no"></iframe  -->
				</div>
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
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" background=<%=request.getContextPath()%>/resources/images/top_gral.jpg height="100%">
				  <TBODY>
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_gral.png"></TD>
                        <TD width=301 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_coorpericial.png" ></TD>
					    <TD width=126 align=left valign="top"><SPAN></SPAN></TD>
					    <TD width=272 align=center valign="top">&nbsp;</TD>
					    <TD width=28 align=middle>&nbsp;</TD><!--<td width="150" align="center"></td>-->
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
					            	<td width="107" class="txt_menu_top">Cerrar sesi&oacute;n</td>
					            	<td width="42" class="txt_menu_top"><a href="#" onclick='$("#dialog-logout").dialog( "open" );'><img src="<%=request.getContextPath()%>/resources/images/btn_cerrar.png" width="29" height="26" border="0"></a></td>
					            	<!-- 
					              <td width="53" class="txt_menu_top">Buscar</td>
					              <td width="147"><img src="<%=request.getContextPath()%>/resources/images/icn_buscar.png" width="22" height="23" border="0"></td>
					               -->
					            </tr>
					          </table>
					            <label for="textfield"></label></td>
					          <td width="204"><table width="89" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					              <td width="47">Ayuda</td>
					              <td width="42"><a href="#"><img src="<%=request.getContextPath()%>/resources/images/btn_ayuda.png" width="29" height="26" border="0"></a></td>
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
					                <TD width=128 align=right valign="middle"><table width="141" border="0" cellspacing="0" cellpadding="0">
					                  <tr>
					                    <td width="107" align="right" valign="middle" class="txt_menu_top">&nbsp;</td>
					                  </tr>
					                </table></TD>
					                <TD width=150 align="right" valign="middle"><DIV id=liveclock style="visibility:hidden;"></DIV></TD>
					                <TD width=10 align="right" valign="middle"><IMG alt="Icono reloj" src="<%=request.getContextPath()%>/resources/images/icn_reloj.png" width=26 height=25></TD>
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
			
		</div>
		<div id="menu_config">
			<li onclick="generarDocumento();">Generar Documento&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="15" height="15"></li>
			<li>Imprimir&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_print.png" width="15" height="15"></li>
			<li>Guardar&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_save_ch.png" width="15" height="15"></li>
			<li>Digitalizar Documento&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_scan.png" width="15" height="15"></li>
			<!--<li>config00</li>
			<li>config00</li>
			<li>config00</li>
			<li class="last">config00</li>-->
			<li id="verde">Configuraci&oacute;n&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"></li>
		</div>
	</ul>
</div>


<div class="ui-layout-south">
	<div id="pie" align="center" style="background-color ;background-position:center top; color: #FFFFFF; background-color: #4A5C68">
		<div id="footer" style="width: 720px;  padding: 5px;" >
			Direcci&oacute;n de la Instituci&oacute;n
		</div> 
	</div>
</div>


<div id="mainContent">
	<div class="ui-layout-center">
		<div class="ui-layout-content">
			<div id="textosPericiales">
			<table width="100%">
				<tr>
					<td>Resumen de pericial</td>
					<td><textarea rows="4" cols="80"></textarea></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Dictamen o informe</td>
					<td><textarea rows="4" cols="80"></textarea></td>
				</tr>
			</table>
			</div>
			<div id="textoSolicitud">
			<table width="100%">
				<tr>
					<td>Solicitud</td>
					<td align="right"  ><input type="button"  id="idTipoBusqueda" value="Designar Perito" class="btn_Generico"/></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="7" cols="100"></textarea></td>
				</tr>
			</table>
			</div>
		</div>
		
	</div>
	<div class="ui-layout-north">
		<table id="gridDetalleFrmPrincipal"></table>
		<div id="pager1"></div>
	</div>
</div>

<div id="dialog-logout" title="Logout">
	<p align="center">
		<span id="logout">¿Desea cerrar su sesi&oacute;n?</span>
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

</body>
</html>
