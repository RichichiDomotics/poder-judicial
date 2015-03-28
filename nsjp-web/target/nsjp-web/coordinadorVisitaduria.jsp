<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas" %>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud" %>
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
			#iRepLegalAccordionPane{width:900px;height:255px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iRepLegalAccordionPane dl{width:900px;height:255px}	
			#iRepLegalAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iRepLegalAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iRepLegalAccordionPane dt.hover{color:#E78F08;}
			#iRepLegalAccordionPane dt.active.hover{color:#1C94C4}
			#iRepLegalAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iRepLegalAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iRepLegalAccordionPane .active .slide-number{color:#fff;}
			#iRepLegalAccordionPane a{color:#68889b}
			#iRepLegalAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iRepLegalAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iRepLegalAccordionPane .more{padding-top:10px;display:block}
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
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
	var outerLayout, innerLayout;

	var idWindowPermisos = 1;	
	var idWindowNuevaDenuncia = 1;
	var ingresoDenuncia="false";
	
	var idWindowDetalleSolicitud=1;
	
	var gIdDepartamento=0;
	var gIdEstatus=0;
	
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

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();
		
		$("#divGridSolsXAtndr").hide();
		
		//Mandamos consultar los tipos de solicitud dependiendo del Area del Funcionario
		consultarTiposSolicitudPorAreaDelFuncionarioGen('tableSolsGeneradas',"0");
		consultarTiposSolicitudPorAreaDelFuncionario('tableSolsXAtender',"0");
		
		muestraGadgets();
		
		
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
		
		
		jQuery("#gridDetalleFrmSecundario").jqGrid({ 
			url:'<%= request.getContextPath()%>/EjemploVisitaduriaSolicitud.xml', 
			datatype: "xml", 
			colNames:['Expediente','Nombre Solicitante','Fecha Limite','Fecha de Solicitud', 'Tipo de Solicitud'], 
			colModel:[ 	{name:'Expediente',index:'expediente', width:180},
						{name:'Nombre',index:'nombre', width:250},
						{name:'Limite',index:'limite', width:95}, 
						{name:'Solicitud',index:'solicitud', width:95}, 
						{name:'Tipo',index:'tipo', width:180} 
						
					],
			pager: jQuery('#pager2'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pager2',{edit:false,add:false,del:false});				

		//Grid de Solicitudes por atender
		jQuery("#gridSolsXAtndr").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente'], 
			colModel:[ 	{name:'caso',index:'caso', width:150},
			           	{name:'expediente',index:'expediente', width:130}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100},
						{name:'fechaLimite',index:'fechaLimite', width:100},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsXAtndr'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
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
			url:'<%= request.getContextPath()%>/consultaSolsGeneradas.do?tipoSoliciutd=0&idArea=0&estatus=0', 
			datatype: "xml", 
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente'], 
			colModel:[ 	{name:'caso',index:'caso', width:150},
			           	{name:'expediente',index:'expediente', width:130}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100},
						{name:'fechaLimite',index:'fechaLimite', width:100},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsGeneradas'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudesGen(rowid);
					}
		}).navGrid('#pagerGridSolsGeneradas',{edit:false,add:false,del:false});
		
		//Grid de Visitadores
		jQuery("#gridVisitadores").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Visitador'], 
			colModel:[ 	{name:'Visitador',index:'visitador', width:840,align:'center'}
					],
			pager: jQuery('#pagerGridVisitadores'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					//funcion para atendar el doble click en cada visitador
					cargaGridCarpetasAuditoria(rowid);
					}
		}).navGrid('#pagerGridVisitadores',{edit:false,add:false,del:false});
		
		//Grid de Carpetas de Auditoria
		jQuery("#gridCarpetasAuditoria").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Carpeta de Auditoría','Expediente Auditado','AMP Auditado','Delito Principal', 'Tipo Expediente Auditado','Estatus Expediente Auditado'], 
			colModel:[ 	{name:'Carpeta',index:'estado', width:150},
						{name:'Expediente',index:'expediente', width:150},
						{name:'AMP',index:'dueno', width:160}, 
						{name:'Delito',index:'fechai', width:150}, 
						{name:'Tipo',index:'fechaf', width:130},
						{name:'Estatus',index:'fechai', width:90}
					],
			pager: jQuery('#pagerGridCarpetasAuditoria'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			caption: "Carpetas de Auditoría del Visitador",
			viewrecords: true,
			onSelectRow: function(id){
					selectr(id);
				},
			sortorder: "desc"
		}).navGrid('#pagerGridCarpetasAuditoria',{edit:false,add:false,del:false});
		
		$( "#tabs" ).tabs();	
		$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Menor de Edad', 
		dialogClass: 'alert', 
		width: 400 ,
		maxWidth: 400,
		buttons: {"Aceptar":function() {
							$(this).dialog("close");
						}
					} 
	});		
	//////////////fin - funcion onready
	
	
												
	$('#iRepLegalAccordionPane').easyAccordion({ 
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
		//cargaCasos();
		$( "#tabsprincipalconsulta" ).tabs();
			
			$("#exp01").click(seleccionFila);
			//$("#exp02").click(creaExpediente02);			
			$("#controlAgenda").click(creaAgenda);
				
			$("#buscarExpediente").click(buscarExpediente);
			$('#casos li').click(agregaExpediente);		
			$("#buscarCaso").click(buscarCaso);	
			$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);	
			//$('#casos1 li').click(justiciaRestaurativa);	
			//$("#generarDocumento").click(generarDocumentoViwe);
			//$("#entrevista").click(generaCapturaEntrevista);
				
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);
		$('#test').weatherfeed(['MXDF0132']);	
		
		//construimos el menu de las carpetas de auditoria
		generaMenuCarpetasAuditoria();

		//Carga por default de la pagina
		cargaGridVisitadores(gIdDepartamento,"<%=EstatusExpediente.ABIERTO.getValorId()%>");
		
		});
		//termina funcion onready

		/*
		*Funcion para generar dinamicamente el menu izquierdo para la opcion de 
		*carpeta de auditoria
		*/
		function generaMenuCarpetasAuditoria()
		{
			//limpiamos el menu de los tipos de solicitud
			$("#seccion1tree").empty();
			var cadenaIDs="";
			//lanzamos la consulta del tipo de solicitudes
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultaDepartamentosCarpetaDeAuditoria.do',
				//data: 'idArea='+idArea,
				dataType: 'xml',
				async: false,
				success: function(xml){
					//lleno el arbol del menu izquierdo
					$(xml).find('departamentos').each(function(){
						$("#seccion1tree").append("<li class='closed' id='li_dptos_"+$(this).find('jerarquiaOrganizacionalId').text()+"'><span class='folder'>"+$(this).find('nombre').text()+"</span>"+"<ul id='li_dptosTree_"+$(this).find('jerarquiaOrganizacionalId').text()+"' class='filetree'></ul></li>");
						//barro los ids para generar la funcionaldiad de arbol  a los LI
						if(cadenaIDs.length==0)
						{
							cadenaIDs += ""+$(this).find('jerarquiaOrganizacionalId').text();
						}
						else
						{
							cadenaIDs += ","+$(this).find('jerarquiaOrganizacionalId').text();
						}
					});
				}
			});	
			var arrayIDs=cadenaIDs.split(",");
			
			$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/consultarEstadosPorDepartamento.do',
	    		data: 'idDepartamento='+0,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
					$(xml).find('estatus').each(function(){
						//barremos el grid para generar la cadena de IDs de los delitos normales
						for (i=0;i<arrayIDs.length;i++)
						{
							$("#li_dptosTree_"+arrayIDs[i]).append("<li><span class='file'><a onclick='cargaGridVisitadores("+arrayIDs[i]+","+$(this).find('idCampo').text()+")'>"+$(this).find('valor').text()+"</a></span></li>");
						}
	    			});
	    		}
	    		
	    	});
			
			$("#seccion1tree").treeview();
			//alert(cadenaIDs);
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
		
		function creaExpediente01() {
			$.newWindow({id:"iframewindowExp01", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"EXP 00000001", type:"iframe"});
		    $.updateWindowContent("iframewindowExp01",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');		
		}
		
		function creaExpediente02() {
			$.newWindow({id:"iframewindowExp02", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"EXP 00000002", type:"iframe"});
		    $.updateWindowContent("iframewindowExp02",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');		
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

		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
		}

		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
		}

		function generarDocumento() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');
		    		
		}
		
		function seleccionFila(){
			$("#1").css({ color: "#FFFFFF", background: "#FF0000" });   
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
		,	onresize_end:			function () { 
										$("#gridDetalleFrmSecundario").setGridWidth($("#mainContent").width() - 5, true);
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

	function activaAuditoria() {
		$("#auditoria").css("display", "block");
		$("#divSolicitudes").css("display", "none");
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
	}

	function activaSolicitud() {
		$("#auditoria").css("display", "none");
		$("#divSolicitudes").css("display", "block");
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		
	}

	function solicitudPermisos(){
		idWindowPermisos++;

		$.newWindow({id:"iframewindowDetalleDeOrden"+idWindowPermisos, statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Nueva Auditor&iacute;a", type:"iframe"});
		$.maximizeWindow("iframewindowDetalleDeOrden"+idWindowPermisos);
		$.updateWindowContent("iframewindowDetalleDeOrden"+idWindowPermisos,'<iframe src="<%= request.getContextPath() %>/DetallesolicitudPermisos.do" width="100%" height="100%" />');
		
		//$.newWindow({id:"iframewindowDetalleDeOrden" + idWindowPermisos, statusBar: true, posx:150,posy:20,width:1040,height:450,title:"Nueva Auditor&iacute;a", type:"iframe"});
	    //$.updateWindowContent("iframewindowDetalleDeOrden" + idWindowPermisos,'<iframe src="<%= request.getContextPath() %>/DetallesolicitudPermisos.do" width="1050" height="450" />');		
	}

	/*
	*Funcion que oculta o muestra los grids, recibe como parametro
	*el nombre del grid que va a mostrar, y todos los demas, se 
	*ocultaran
	*/ 
	function ocultaMuestraGrids(nombreGrid){
		if(nombreGrid == "gridSolsXAtndr"){
			$("#auditoria,#divSolicitudes").hide();
			$("#divGridSolsXAtndr").show();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		}
		if(nombreGrid == "gridSolsGeneradas"){
			$("#auditoria,#divSolicitudes").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").show();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		}
		if(nombreGrid == "gridMandamientosJudiciales"){
			$("#auditoria,#divSolicitudes").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").show();
			$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		}
		if(nombreGrid == "gridVisitadores"){
			$("#auditoria,#divSolicitudes").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridVisitadores,#divGridCarpetasAuditoria").show();
		}
	}
	
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
	function cargaGridSolsXAtndr(tipoSolicitud,idArea)
	{
		jQuery("#gridSolsXAtndr").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsXAtnder.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>',datatype: "xml" });
		$("#gridSolsXAtndr").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsXAtndr");
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
					var trTabla = '<tr>';
					trTabla = trTabla + '<td><span><img src="<%=request.getContextPath()%>/resources/css/treeview/images/folder-closed.gif" width="16" height="16"/><a onclick="cargaGridSolsXAtndr('+$(this).find("idCampo").text()+','+idArea+')">'+$(this).find("valor").text()+'</a></span></td>';
					trTabla = trTabla + '</tr>';
					$('#'+idDivArbol).append(trTabla);
				});
			}
			
		});
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
					var trTabla = '<tr>';
					trTabla = trTabla + '<td><span><img src="<%=request.getContextPath()%>/resources/css/treeview/images/folder-closed.gif" width="16" height="16"/><a onclick="cargaGridSolsGeneradas('+$(this).find("idCampo").text()+',0)">'+$(this).find("valor").text()+'</a></span></td>';
					trTabla = trTabla + '</tr>';
					$('#'+idDivArbol).append(trTabla);
				});
			}
			
		});
	}
	
	/*
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsGeneradas(tipoSolicitud,idArea)
	{
		jQuery("#gridSolsGeneradas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsGeneradas.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>',datatype: "xml" });
		$("#gridSolsGeneradas").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsGeneradas");
	}
	
	/*
	*Funcion para realizar la consulta del grid de visitadores
	*/
	function cargaGridVisitadores(idDepartamento,idEstatus){
			
		//alert("idDepartamento="+idDepartamento+" -- "+"idEstatus="+idEstatus);
		$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		gIdDepartamento=idDepartamento;
		gIdEstatus=idEstatus;
		jQuery("#gridVisitadores").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaVisitadores.do?idDepartamento='+idDepartamento+'&idEstatus='+idEstatus+'',datatype: "xml" });
		$("#gridVisitadores").trigger("reloadGrid");
		//limpiamos el grid de las carpetas de auditoria
		ocultaMuestraGrids("gridVisitadores");
		$("#gview_gridVisitadores .ui-jqgrid-bdiv").css('height', '200px');
	}
	
	/*
	*Funcion para realizar la consulta del grid de visitadores
	*/
	function cargaGridCarpetasAuditoria(idFuncionario)
	{	
		jQuery("#gridCarpetasAuditoria").hide();
		jQuery("#gridCarpetasAuditoria").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaexpedientesVisitadores.do?idDepartamento='+gIdDepartamento+'&idEstatus='+gIdEstatus+'&idFuncionario='+idFuncionario+'',datatype: "xml" });
		$("#gridCarpetasAuditoria").trigger("reloadGrid");
		$("#gview_gridCarpetasAuditoria .ui-jqgrid-bdiv").css('height', '200px');
		jQuery("#gridCarpetasAuditoria").show();
	}
	
	function selectr(id){
		
		var pantallaSolicitada=7;
		idWindowNuevaDenuncia++;
		var ingresoDenuncia = true;

		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Carpeta de Auditoría: ", type:"iframe"});
		$.maximizeWindow("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia);
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpedienteSistTrad.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'&idArea='+gIdDepartamento+'" width="100%" height="100%"/>');
		
		//$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: ", type:"iframe"});
		//$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'&idArea='+gIdDepartamento+'" width="1430" height="670" />');
	}
	
	function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Carpeta de Auditoría: "+num);
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
<div class="ui-layout-west">

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
			<h3><a href="#" onclick="activaAuditoria()">Carpetas de Auditoría</a></h3>
			<div>			
				<ul id="seccion1tree" class="filetree">
					<li class="closed" id="casos"><span class="folder">Atenci&oacute;n Temprana</span>
						<ul id="seccion1tree11" class="filetree">
						</ul>
					</li>
					<li class="closed"><span class="folder">Justicia alternativa</span>
						<ul id="" class="filetree">
						</ul>
					</li>
					<li class="closed"><span class="folder">Unidad de Investigaci&oacute;n</span>
						<ul id="" class="filetree">
						</ul>
					</li>
				</ul>		
			</div>
			<h3><a href="#">Solicitudes por Atender</a></h3>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer" id="tableSolsXAtender">
				</table>
			</div>
			<h3><a href="#">Solicitudes Generadas</a></h3>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer" id="tableSolsGeneradas">
				</table>
			</div>
			<h3><a href="#" onclick="ocultaMuestraGrids('gridMandamientosJudiciales');">Mandamientos Judiciales</a></h3>
			<div>

			</div>
			<h3><a href="#" id="hrefReportesEstadisticos">Reportes Estad&iacute;sticos</a></h3>
			<div>
				
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
						
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
			<!--h4>
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
	<!-- div class="footer">&nbsp;</div -->
</div>


<div class="ui-layout-north">
	<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">COORDINADOR DE VISITADURIA</TD>
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
			<li id="tbarBtnNuevaAuditoria" class="first" onclick="solicitudPermisos()"><span></span>Nueva Auditor&iacute;a</li>
		</div> 
		<div id="menu_config">
			<li onclick="buscarExpediente();">Buscar expediente<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
			<li onclick="buscarCaso();">Buscar caso<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
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
	<div id="pie" align="center"
		style="BACKGROUND-COLOR: #e7eaeb; BACKGROUND-POSITION: center top; COLOR: #58595b">
	<div id="footer"
		style="PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; WIDTH: 720px; PADDING-RIGHT: 5px; PADDING-TOP: 5px">
			Direcci&oacute;n de la Instituci&oacute;n</div>
	</div>
</div>


<div id="mainContent">
	<div class="ui-layout-center">
		<div class="ui-layout-content">
		<div class="ui-layout-north" >
			<div id="divSolicitudes"  style="display: none;">
				<table id="gridDetalleFrmSecundario"></table>
				<div id="pager2"></div>
			</div>
			<div id="divGridSolsXAtndr" style="display: none;">
			 	<table id="gridSolsXAtndr" width="100%" height="100%"></table>
				<div id="pagerGridSolsXAtndr"></div>
			</div>
			<div id="divGridSolsGeneradas" style="display: none;">
			 	<table id="gridSolsGeneradas" width="100%" height="100%"></table>
				<div id="pagerGridSolsGeneradas"></div>
			</div>	
			<div id="divGridVisitadores" style="display: none;">
			 	<table id="gridVisitadores" width="100%" height="100%"></table>
				<div id="pagerGridVisitadores"></div>
			</div>
			<div id="divGridCarpetasAuditoria" style="display: none;">
			 	<table id="gridCarpetasAuditoria" width="100%" height="100%"></table>
				<div id="pagerGridCarpetasAuditoria"></div>
			</div>
			<div id="divAcordion"  style="display: none;">
				<table width="60%" border="0">
					<tr valign="top">
						<td width="60%" valign="top">
							<div id="iRepLegalAccordionPane" style="width: 60%" >
					            <dl>
					                 <dt>Reportes de Auditoria</dt>
					                <dd></dd>
					                <dt>Sanciones</dt>
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
