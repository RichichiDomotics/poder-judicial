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
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
	
	<style type="text/css">
			dd p{line-height:120%}
			#iPoliciaMinAccordionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iPoliciaMinAccordionPane dl{width:1000px;height:355px}	
			#iPoliciaMinAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iPoliciaMinAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iPoliciaMinAccordionPane dt.hover{color:#E78F08;}
			#iPoliciaMinAccordionPane dt.active.hover{color:#1C94C4}
			#iPoliciaMinAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iPoliciaMinAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iPoliciaMinAccordionPane .active .slide-number{color:#fff;}
			#iPoliciaMinAccordionPane a{color:#68889b}
			#iPoliciaMinAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iPoliciaMinAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iPoliciaMinAccordionPane .more{padding-top:10px;display:block}
		</style>	
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	var outerLayout, innerLayout;
	
	/*
	* Identificadores de los frames
	*/
	var idWindowDetalleDeOrden = 1;	
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
		
		$("#atender").css({ color: "#1C94C4"});
		$("#proceso").css({ color: "#1C94C4"});
		$("#atendidos").css({ color: "#1C94C4"});
		
		$("#mandamientos-judiciales").css({ color: "#1C94C4"});
		$("#programacion-diligencia").css({ color: "#1C94C4"});
				
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();
		$("#seccion6tree").treeview();
		$("#seccion7tree").treeview();
		$("#seccion8tree").treeview();
		$("#seccion9tree").treeview();
		
		$("#opcDiligencia").click(muestraDiligencias);
		
		
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
		
		
		
		
		//oculto el div de domicilio
		$("#tabsprincipalconsulta").css("display", "none");
		
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
									url:'<%= request.getContextPath()%>/EjemploIndexPoliciaMinisterial.xml', 
									datatype: "xml", 
									colNames:['Orden','Fecha', 'Probable Responsable', 'Asunto'], 
									colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
												{name:'Fecha',index:'fecha', width:20}, 
												{name:'Nombre',index:'nombre', width:50}, 
												{name:'Resumen',index:'Resumen', width:150}
											],
									pager: jQuery('#pager1'),
									rowNum:10,
									onSelectRow: function(){
										detalleDeOrden();
									},
									rowList:[10,20,30],
									autowidth: true,
									sortname: 'detalle',
									viewrecords: true,
									sortorder: "desc"
								}).navGrid('#pager1',{edit:false,add:false,del:false});		
		
		 $("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '410px');
		$( "#tabs" ).tabs();	
		$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Menor de Edad', 
		dialogClass: 'alert', 
		width: 500 ,
		maxWidth: 600,
		buttons: {"Aceptar":function() {
							$(this).dialog("close");
						}
					} 
		});	
		
		$('#iPoliciaMinAccordionPane').easyAccordion({ 
			autoStart: false, 
			slideInterval: 3000
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
		createInnerLayout () ;
		cargaOcupacion();
		cargaCasos();
		$( "#tabsprincipalconsulta" ).tabs();
			
			$("#exp01").click(designaAbogadoDefensor);
			$("#exp02").click(designaAbogadoDefensor2);			
			$("#controlAgenda").click(creaAgenda);
				
			$("#buscarExpediente").click(buscarExpediente);
			$('#casos li').click(agregaExpediente);		
			$("#buscarCaso").click(buscarCaso);	
			$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);	
			//$('#casos1 li').click(justiciaRestaurativa);	
			$("#generarDocumento").click(generarDocumentoViwe);
			//$("#entrevista").click(generaCapturaEntrevista);
				
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);	
		
		$("#divGridAudiencias").css("display", "none");
		//seleccionamos el renglon del grid
		$("#expediente001").click(seleccionFila);
		$('#test').weatherfeed(['MXDF0132']);
		
		});

		function detalleDeOrden(){
			$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '150px');
			 $("#divAcordion").css("display", "block");
			idWindowDetalleDeOrden++;
			$.newWindow({id:"iframewindowDetalleDeOrden" + idWindowDetalleDeOrden, statusBar: true, posx:150,posy:20,width:1040,height:450,title:"Detalle de Orden", type:"iframe"});
		    $.updateWindowContent("iframewindowDetalleDeOrden" + idWindowDetalleDeOrden,'<iframe src="<%= request.getContextPath() %>/DetalleDeOrden.do" width="1050" height="450" />');		
		}

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
		
		function designaAbogadoDefensor() {
			$.newWindow({id:"iframewindowAsignaAbogado", statusBar: true, posx:200,posy:50,width:500,height:400,title:"Designar Abogado Defensor", type:"iframe"});
		    $.updateWindowContent("iframewindowAsignaAbogado",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="500" height="400" />');		
		}
		
		function designaAbogadoDefensor2() {
			$.newWindow({id:"iframewindowAsignaAbogado2", statusBar: true, posx:200,posy:50,width:500,height:400,title:"Designar Abogado Defensor", type:"iframe"});
		    $.updateWindowContent("iframewindowAsignaAbogado2",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="500" height="400" />');		
		}
		
		function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
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
				
		function muestraDiligencias()
		{
			$.newWindow({id:"iframewindowDiligencias", statusBar: true, posx:200,posy:50,width:440,height:100,title:"Diligencias", type:"iframe"});
		    $.updateWindowContent("iframewindowDiligencias",'<iframe src="<%= request.getContextPath() %>/MostrarDiligencias.do" width="1140" height="400" />');
		    		
		}
		
		function cargaOcupacion(){
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
		,	onresize_end:			function () { $("#gridDetalleFrmPrincipal").setGridWidth($("#mainContent").width() - 5, true);
											$("#gridDetalleFrmPrincipalDos").setGridWidth($("#mainContent").width() - 5, true);
									}		
		}
	};
	
	/*
	*Listener del click para la redireccion a la valoracion de hechos
	*/
	function realizarValoracionHechos()
	{
		location.href='<%= request.getContextPath()%>/RealizarValoracionHechos.do';
	}
	
	
	function ocultaMuestraGridsAlertas(idGrid)
	{
		if(parseInt(idGrid)==1)
		{
			$("#divGridSolicitudes").css("display", "block");
			$("#divGridAudiencias").css("display", "none");
			$("#gridDetalleFrmPrincipal").find("tr:nth-child(2)").css({ color: "#FFFFFF", background: "#FFFFFF" });
		}
		else
		{
			$("#divGridSolicitudes").css("display", "none");
			$("#divGridAudiencias").css("display", "block");
			gridDos();
			$("#gridDetalleFrmPrincipalDos").find("tr:nth-child(2)").css({ color: "#FFFFFF", background: "#FFFFFF" });
		}
	}
	
	function gridDos()
	{
		jQuery("#gridDetalleFrmPrincipalDos").jqGrid({ 
			url:'<%= request.getContextPath()%>/EjemploEncargadoCausas.xml', 
			datatype: "xml", 
			colNames:['Caso','Fecha de Audiencia', 'Hora de Audiencia', 'Sala','Tipo de Audiencia','Probable Responsable','Delito'], 
			colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
						{name:'Fecha',index:'fecha', width:20}, 
						{name:'Hora',index:'Hora', width:50},
						{name:'Sala',index:'Sala', width:50}, 
						{name:'Audiencia',index:'Audiencia', width:50},
						{name:'Responsable',index:'Responsable', width:50},
						{name:'Delito',index:'Delito', width:50}
					],
			pager: jQuery('#pagerDos1'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerDos1',{edit:false,add:false,del:false});
	}
	
	function seleccionFila(){
		$("#gridDetalleFrmPrincipal").find("tr:nth-child(2)").css({ color: "#FFFFFF", background: "#FF0000" });
		$("#gridDetalleFrmPrincipalDos").find("tr:nth-child(2)").css({ color: "#FFFFFF", background: "#FF0000" });
	}
	
	//Abre una nueva ventana para informar lugar de los hechos
	function abreVentanaLugarDeHechos() {
		$.newWindow({id:"iframewindowLugarDeHechos", statusBar: true, posx:50,posy:80,width:1200,height:430,title:"Informar Lugar de los Hechos", type:"iframe"});
	    $.updateWindowContent("iframewindowLugarDeHechos",'<iframe src="<%= request.getContextPath() %>/lugarDeLosHechos.do" width="1200" height="430" />');		
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
			<h3><a href="#">Ordenes</a></h3>
			<div>			
				<ul id="seccion1tree" class="filetree">
					<li class="closed" id="atender"><span class="folder">Por Atender</span></li>
					<li class="closed" id="proceso"><span class="folder">En Proceso</span></li>
					<li class="closed" id="atendidos"><span class="folder">Atendidas</span></li>
				</ul>	
			</div>
			<h3><a href="#">Mandamientos <br/>Judiciales</a></h3>
			<div>
				<!--  <ul id="seccion8tree" class="filetree">
					<li class="closed"><span class="file" id="">Seguir Mandamiento Judicial</span>
					</li>
				</ul>-->
<!--				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">-->
<!--					<tr>-->
<!--					   <td width="100%" id=""><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Seguir Mandamiento Judicial</td>-->
<!--					</tr>-->
<!--				</table>		-->
			</div>
		<!--<h3><a href="#">Registrar Informe de <br/>Actividad Policial</a></h3>-->
			<!--<div>-->
				<!--<ul id="seccion6tree" class="filetree">
					  <li class="closed"><span class="file" id="">Digitalizar documento</span>
					</li>
					<li class="closed"><span class="file" id="">Verificar formato de audio y video</span>
					</li>
				</ul>-->		
			<!--</div>-->
<!--			<h3><a href="#">Registrar Informe de<br/> Investigaci&oacute;n Policial</a></h3>-->
<!--			<div>-->
				<!--<ul id="seccion2tree" class="filetree">
					 <li class="closed"><span class="file" id="">Registrar recursos o contestaci&oacute;n</span>
					</li>
					<li class="closed"><span class="file" id="">Rechazar recurso(por tiempo)</span>
					</li> 
				</ul>-->		
<!--			</div>-->
			<h3><a>Diligencias</a></h3>
			<div>
				<!--  ul id="seccion7tree" class="filetree">
					<li class="closed"><span class="file" id="opcDiligencia">Diligencias</span>
					</li>
				</ul>-->
<!--				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">-->
<!--					<tr>-->
<!--					   <td width="100%" id="opcDiligencia"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Diligencias</td>-->
<!--					</tr>-->
<!--				</table>		-->
<!--				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">-->
<!--					<tr>-->
<!--					   <td><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" /><a id="lugarHechos" onclick="abreVentanaLugarDeHechos();">Informar Lugar de los Hechos</a></td>-->
<!--					</tr>-->
<!--				</table>		-->
			</div>
			<h3><a id="bPeritoEvidencia" href="#">Solicitudes</a></h3>
			<div>
				<ul id="seccion2tree" class="filetree">	
					<li class="closed"><span class="folder">Dict&aacute;men Pericial</span>
						<ul>
						   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="solPericialNueva" onclick="cargaGridSolPericialesNuevas();">Nuevas</a></span></li>
						   <li><span><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16"  /><a id="solPericialPendiente" onclick="cargaGridSolPericialesPendientes();">En Proceso</a></span></li>
						</ul>
					</li>
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
				<h4>
					<a href="#">Datos Personales</a>
				</h4>
				<div>				
					<center>
						 <img src="<%=request.getContextPath()%>/resources/images/mono.png"></td>
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
				<h4>
					<a href="#">Agenda</a>
				</h4>
				<div>
					<center>
						<a href="#" id="controlAgenda"><img	src="<%=request.getContextPath()%>/resources/images/img_agenda.png"  width="201" height="246"></a>
					</center>
					<br />
					
				</div>
				<h4>
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
					    <TD width=401 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_polminis.png"></TD>
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
					            </tr>					          </table>
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
					                <TD width=128 align=right valign="middle">&nbsp;</TD>
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
			<!-- <li id="tbarBtnSolicitudes" class="first" onmousedown="ocultaMuestraGridsAlertas('1');"><span></span>Solicitudes</li>
			<li id="tbarBtnAgendaAudiencias" onmousedown="ocultaMuestraGridsAlertas('2');"><span></span>Agenda de Audiencias</li> -->
		</div>
		
		<div id="menu_config">
			<!--  <li id="buscarCaso"><a href="#">Buscar Caso</a></li>
			<li id="buscarExpediente"><a href="#">Buscar Expediente</a></li>
			<li id="generarDocumento"><a href="#">Generar Documento</a></li>
			<li><a href="#">Imprimir</a></li>
			<li><a href="#">Guardar</a></li>
			<li><a href="#">Digitalizar Documento</a></li>
			<li><a href="#">Turnar Documento</a></li>
			<li><a href="#">Enviar Notificaciones</a></li>-->
			<!--<li>config01</li>
			<li>config02</li>
			<li>config03</li>
			<li class="last">config04</li>-->
			<li id="verde">Configuraci&oacute;n&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"></li>
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
		<div id="divGridSolicitudes">
			<table id="gridDetalleFrmPrincipal"></table>
			<div id="pager1"></div>
		</div>
		<div id="divGridAudiencias">
			<table id="gridDetalleFrmPrincipalDos"></table>
			<div id="pagerDos1"></div>
		</div>
		<div id="divAcordion"  style="display: none;">
			<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iPoliciaMinAccordionPane" style="width: 100%" >
					            <dl>
					                 <dt>Mandamientos Judiciales</dt>
					                <dd></dd>
					                <dt>Reportes de Investigación</dt>
					                <dd></dd>
					                <dt>IPH'S</dt>
					                <dd></dd>
					                <dt>Oficios</dt>
					                <dd></dd>
					            </dl>
							</div>
						</td>
					</tr>
				</table>
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
