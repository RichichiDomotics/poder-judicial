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
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
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
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	
		var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
		if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
		}
	<% ( (mx.gob.segob.nsjp.dto.usuario.UsuarioDTO)request.getSession().getAttribute("KEY_SESSION_USUARIO_FIRMADO") ).setAreaActual(new mx.gob.segob.nsjp.dto.institucion.AreaDTO(16L));%>

	/*variables globales*/
		
	var turnoEnCurso;
	var outerLayout, innerLayout;
	var acuse = "";
	var numExpediente = "";
	var idExpediente = "";
	var numeroExpedienteId="";
	var documentoId="";
	var idSolicitud="";
	var idFuncionario="";
	var defensoria=1;
	var tipoBusqueda = "";
	
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

		outerLayout = $("body").layout( layoutSettings_Outer );
		//pinta turnos por default
		ocultaMuestraGrids('6');
		//asocia funcion al boton de queja ciudadana
		$("#tbarBtnQuejaCiudadana").click(muestraQuejaCiudadana);
		//ajusta el tamaño de el acordeon izquierdo	
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		//ajusta el tamaño de el acordeon izquierdo				
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		//vista de arbol para las solicitudes
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		//asocia agenda
		$("#controlAgenda").click(creaAgenda);
		//asocia funcion a boton de buscar expediente
		$("#buscarExpediente").click(buscarExpediente);
		//asocia funcion a boton de buscar caso		
		$("#buscarCaso").click(buscarCaso);	
		//abre pantalla para la recepcion de una solicitud
		$("#tbarBtnLlamarTurnoSolicitud").click(cambiaTipoBusquedaSolicitud);
		//abre pantalla para la recepcion de una solicitud
		$("#tbarBtnLlamarTurnoAsesoria").click(cambiaTipoBusquedaAsesoria);
		
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

		//abre ventana de chat						
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

		//abre ventana modal para finalizar sesion
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
		$('#test').weatherfeed(['MXDF0132']);
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		outerLayout.addCloseBtn("#east-closer", "east");
		createInnerLayout();
		
		});

		//abre la ventana para registrar una asesoria legal
		function asesoriaLegal() {
			$.newWindow({id:"iframewindowAsesoriaLegal", statusBar: true, posx:255,posy:110,width:1090,height:570,title:"Asesoría Legal: "+ numExpediente+" - Turno: "+turnoEnCurso, type:"iframe"});
	    	$.updateWindowContent("iframewindowAsesoriaLegal",'<iframe src="<%=request.getContextPath()%>/registrarAsesoriaLegal.do?numeroExpedienteCadena='+numExpediente+'&numeroExpedienteId='+numeroExpedienteId+'&expedienteId='+idExpediente+'" width="1090" height="570" />');		
		} 
		//abre la ventana para registrar una asesoria legal
		function detalleEnvioAsesoriaLegal(idSolicitud) {
			$.newWindow({id:"iframewindowAsesoriaLegal", statusBar: true, posx:255,posy:110,width:1090,height:570,title:"Detalle Solicitud Asesoría Legal", type:"iframe"});
	    	$.updateWindowContent("iframewindowAsesoriaLegal",'<iframe src="<%=request.getContextPath()%>/registrarAsesoriaLegal.do?solicitudId='+idSolicitud+'" width="1090" height="570" />');		
		} 
		//abre la ventana para buscar un expediente
		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%=request.getContextPath()%>/buscarExpediente.do" width="653" height="400" />');		
		}
		//abre la ventana para buscar un caso
		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%=request.getContextPath()%>/buscarCaso.do" width="653" height="400" />');		
		}
		//abre la ventana para mostrar el detalle de designaciones
		function mostrarDetalleDesignaciones(rowid, tipoSolicitud, idNumeroExpediente){
			var idDocumento = rowid.split("-")[0];
			var params = "?tipoSolicitud="+tipoSolicitud;
			var titulo = "Expediente: " + numExpediente;
			switch(tipoSolicitud){
				case 1:
					titulo="Aviso de Persona Detenida ";
					break;
				case 2:
					titulo="Expediente: " + numExpediente;
					break;
				case 3:
					titulo="Expediente: " + numExpediente;
					break;
				case 4:
					titulo="Solicitud de Defensor ";
					break;
				case 5:
					titulo="Asesoría: " + numExpediente;
					break;
			}
			
			params += "&idDocumento="+idDocumento;
			params += "&numeroExpedienteId="+idNumeroExpediente;
			params += "&asignarDefensor=0";
			$.newWindow({id:"iframewindowDetalleDesignaciones", statusBar: true, posx:255,posy:111,width:925,height:514,title:titulo, type:"iframe"});
		    $.updateWindowContent("iframewindowDetalleDesignaciones",'<iframe src="<%=request.getContextPath()%>/mostrarDetalleATDEF.do'+params+'" width="925" height="514" />');		
		    $("#" +"iframewindowDetalleDesignaciones"+ " .window-maximizeButton").click();
			}
		//abre la ventana para la agenda
		function creaAgenda() {
			$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
		    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');	
		    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();		
		}
		//abre la ventana para llamar el turno de solicitudes
		function busquedaExpedienteCaso(){
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:255,posy:111,width:900,height:350,title:"Solicitud Ciudadana Defensor", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/BusquedaExpedienteCaso.do" width="898" height="350" />');	
			
		}
		//abre la ventana para ejecutar el chat
		function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
		}
		//genera numero de acuse
		function cargaAcuse(tipoGenera, idNumeroExpediente){
			var params = "";
			params += "?tipo="+tipoGenera;
			if(idNumeroExpediente != undefined && idNumeroExpediente != null){
				params += "&idExpediente="+idNumeroExpediente;
			}
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/GeneraNumeroAcuse.do'+params+'',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					 $(xml).find('solicitudDefensorDTO').each(function(){
						 acuse =$(xml).find('folioDocumento').text();
						 numExpediente =$(xml).find('numeroExpediente').text();
						 idExpediente =$(xml).find('expedienteId').text();
						 numeroExpedienteId=$(xml).find('numeroExpedienteId').text();
						 documentoId =$(xml).find('documentoId').text();					
					});
				}
			});
		}
		//abre pantalla para introducir los datos de la solicitud
		function generaNumeroAcuse(exp){
			if(exp != null && exp != undefined){
				params = "?idExpediente="+exp;
			}
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1080,height:640,title:"Acuse de Atencion: "+acuse+" para el Exp: "+ numExpediente, type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/DatosGeneralesSolicitud.do'+params +'" width="898" height="498" />');	
		}
		//abre pantalla detalle de los datos de la solicitud
		function detalleEnvioSolicitud(rowid){
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1080,height:640,title:"Detalle de la Solicitud de Defensor", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/DatosGeneralesSolicitud.do?solicitudId='+rowid+'" width="898" height="498" />');	
		}
		//cierra pantalla de solicitud
		function resBuscarCaso(){
			$windowCloseButton.click();
			cargaAcuse("solicitud");
			generaNumeroAcuse(idExpediente);		
		}	
		//registra una solicitud asociada a un numero de expediente
		function registrarSolicitudAsociada(idNumeroExpediente,numExpediente){
			$windowCloseButton.click();
			cargaAcuse("solicitud", idNumeroExpediente);
			generaNumeroAcuse(idExpediente);		
		}	
		/*Habilita o deshabilita el elemento principal de la pagina segun la opcion seleccionada en el acordeon izquierdo*/
		function ocultaMuestraGrids(idGrid) {
			$("#divGenerarTurno").css("display", "none");
			$("#divGridPoderJudicial").css("display", "none");
			$("#divGridDesignaciones").css("display", "none");
			$("#divGridAvisosDeDetencion").css("display", "none");
			$("#divGridExpedientesSubordinados").css("display", "none");
			$("#divGridSolicitudAtencionTemprana").css("display", "none");
			$("#divGridAsesorias").css("display", "none");
			$("#divGridNoEnvioSolicitud").css("display", "none");
			$("#divGridNoEnvioAsesoria").css("display", "none");
			
			//Manipula el grid de designaciones
			if (parseInt(idGrid) == 1) {
				$("#divGridDesignaciones").css("display", "block");
				gridDesignaciones();
			}
			//Manipula el grid de avisos de  detencion
			if (parseInt(idGrid) == 2) {
				$("#divGridAvisosDeDetencion").css("display", "block");
				gridAvisosDeDetencion();
			}
			//Manipula el grid de solicitudes de defensor de atencion temprana
			if (parseInt(idGrid) == 3) {
				$("#divGridSolicitudAtencionTemprana").css("display", "block");
				gridSolAtencionTemprana();
			}
			//Manipula el grid de solicitudes de defensor de poder judicial
			if (parseInt(idGrid) == 4) {
				$("#divGridPoderJudicial").css("display", "block");
				gridSolPoderJudicial();
			}
			//Manipula el grid de expedientes
			if (parseInt(idGrid) == 5) {
				$("#divGridExpedientesSubordinados").css("display", "block");
			}
			//Manipula grid de asesorias
			if (parseInt(idGrid) == 7) {
				$("#divGridAsesorias").css("display", "block");
				gridAsesoria();
			}
			//Manipula div de turnos
			if (parseInt(idGrid) == 6) {
				$("#divGenerarTurno").css("display", "block");
			}
			//Manipula grid de no envio solicitud
			if (parseInt(idGrid) == 10) {
				$("#divGridNoEnvioSolicitud").css("display", "block");
				gridNoEnvioSolicitud();
			}
			//Manipula grid de no envio asesoria
			if (parseInt(idGrid) == 9) {
				$("#divGridNoEnvioAsesoria").css("display", "block");
				gridNoEnvioAsesoria();
			}
		}
		
		/* Grid designaciones */
		function gridDesignaciones() {
	
			jQuery("#gridDesignaciones").jqGrid({
				url:'<%= request.getContextPath()%>/recibirDesignaciones.do', 				 
				datatype: "xml", 
				colNames:['Área origen','Caso','Expediente','Delito(s)','Detenido','Defensor','Fecha - Hora de Designación'], 
				colModel:[ 
							{name:'origen',index:'2001', width:100},
				           	{name:'caso',index:'2002', width:100},
				           	{name:'expediente',index:'2003', width:100},
				        	{name:'delitos',index:'2004', width:80},
							{name:'Detenido',index:'2005', width:70},
							{name:'defensor',index:'2006', width:120},
							{name:'FHDesignacion',index:'2007', width:170}
						],
			pager: jQuery('#pagerGridDesignaciones'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			autowidth: true,
			autoheight:true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			
			ondblClickRow: function(rowid) {	
				var ret =jQuery("#gridDesignaciones").jqGrid('getRowData',rowid); 
			    numExpediente = ret.expediente;	
				
				mostrarDetalleDesignaciones(rowid,3,"");
			}
		}).navGrid('#pagerGridDesignaciones',{edit:false,add:false,del:false});
		$("#gview_gridDesignaciones .ui-jqgrid-bdiv").css('height', '525px');
		$("#gridDesignaciones").trigger("reloadGrid"); 
		
		}
		
		/* Grid Avisos de detencion */
		function gridAvisosDeDetencion() {	
			jQuery("#gridAvisosDeDetencion").jqGrid({
						url : '<%= request.getContextPath()%>/SolicitudesNoAtendidas.do', 
						datatype: "xml", 
						colNames:['Folio','Caso','Imputado:','Delito(s)','Agencia del MP','Fecha-Hora de detención','Fecha-Hora de aviso' ], 
						colModel:[ 	{name:'folio',index:'2008', width:150},
						           	{name:'caso',index:'2002', width:180},
						           	{name:'imputado',index:'2009', width:150},
						           	{name:'delito',index:'2004', width:200},
						           	{name:'mp',index:'2010', width:150},
						           	{name:'fechaHoraDetencion',index:'2011', width:150},
						           	{name:'fechaHoraAviso',index:'2012', width:150},
						           	
								],			
			pager: jQuery('#pagerAvisosDeDetencion'),
			rowNum:25,
			rowList:[10,20,30,40,50,60],
			autowidth: true,
			autoheight:true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {	
								
				mostrarDetalleDesignaciones(rowid,1,"");
			}
		}).navGrid('#pagerAvisosDeDetencion',{edit:false,add:false,del:false});
		$("#gview_gridAvisosDeDetencion .ui-jqgrid-bdiv").css('height', '450px');
		$("#gridAvisosDeDetencion").trigger("reloadGrid"); 
		
		}
		/* Grid Solcitudes de Atencion temprana */
		function gridSolAtencionTemprana(){
			jQuery("#gridSolicitudAtencionTemprana").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesNoAtendidasAtencionTemprana.do', 
				datatype: "xml", 
				colNames:['Folio de Solicitud','Caso','Expediente','Para Quien se Sólicita: ','Detenido','Fecha y Hora de Solicitud','Numero Expediente Id'], 
				colModel:[ 	{name:'Folio de Solicitud',index:'2013', width:185,align:"center"},
				           	{name:'Caso',index:'2002', width:165,align:"center"},
				           	{name:'Expediente',index:'2003', width:185,align:"center"},
				           	{name:'ParaQuienseSolicita',index:'2009', width:215,align:"center"},
				           	{name:'Detenido',index:'2005', width:185,align:"center"},
				           	{name:'FHSolicitud',index:'2013', width:185,align:"center"},
				        	{name:'numeroExpedienteId',index:'2015', width:185,align:"center", hidden:true}
				           					           	
						],
				pager: jQuery('#pagGridSolicitudAtencionTemprana'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				//autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					var ret =jQuery("#gridSolicitudAtencionTemprana").jqGrid('getRowData',rowid); 
					var numeroExpedienteId = ret.numeroExpedienteId;
					    numExpediente = ret.Expediente;									
					mostrarDetalleDesignaciones(rowid,2,numeroExpedienteId);
				}			
			}).navGrid('#pagGridSolicitudAtencionTemprana',{edit:false,add:false,del:false});
			$("#gview_gridSolicitudAtencionTemprana .ui-jqgrid-bdiv").css('height', '525px');
			jQuery("#gridSolicitudAtencionTemprana").trigger("reloadGrid");
		}

		/* Grid Solcitudes de Asesoria */
		function gridAsesoria(){
			jQuery("#gridAsesorias").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudesNoAtendidasAsesoria.do', 
				datatype: "xml", 
				colNames:['Folio de Solicitud','Expediente','Interesado','Fecha de Solicitud','Numero Expediente Id'], 
				colModel:[ 	{name:'Folio de Solicitud',index:'2013', width:185,align:"center"},
				         	{name:'Expediente',index:'2003', width:185,align:"center"},
				           	{name:'interesado',index:'2016', width:215,align:"center"},
				          	{name:'FHSolicitud',index:'2014', width:185,align:"center"},
				        	{name:'numeroExpedienteId',index:'2015', width:185,align:"center",hidden:true}
				           	
						],
				pager: jQuery('#pagGridAsesorias'),
				rowNum:25,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				//autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {

				var ret =jQuery("#gridAsesorias").jqGrid('getRowData',rowid); 
				var numeroExpedienteId = ret.numeroExpedienteId;
				    numExpediente = ret.Expediente;	
													
					mostrarDetalleDesignaciones(rowid,5,numeroExpedienteId);
				}			
			}).navGrid('#pagGridAsesorias',{edit:false,add:false,del:false});
			$("#gview_gridAsesorias .ui-jqgrid-bdiv").css('height', '525px');
			jQuery("#gridAsesorias").trigger("reloadGrid");
		}
		
		/* Grid Solcitudes No Enviadas */
		function gridNoEnvioSolicitud(){
			jQuery("#gridNoEnvioSolicitud").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudesNoEnviadasAtencionTemprana.do', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Para Quien Solicita','Etapa'], 
				colModel:[ 	{name:'caso',index:'1', width:185,align:"center"},
				         	{name:'expediente',index:'2', width:185,align:"center"},
				           	{name:'interesado',index:'3', width:215,align:"center"},
				          	{name:'etapa',index:'4', width:185,align:"center"}
						],
				pager: jQuery('#pagGridNoEnvioSolicitud'),
				rowNum:20,
				rowList:[20,40,60,80,100],
				autowidth: true,
				//autoheight:true,
				sortname: '2',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					detalleEnvioSolicitud(rowid);
				}			
			}).navGrid('#pagGridNoEnvioSolicitud',{edit:false,add:false,del:false});
			$("#gview_gridNoEnvioSolicitud .ui-jqgrid-bdiv").css('height', '525px');
			jQuery("#gridNoEnvioSolicitud").trigger("reloadGrid");
		}
		
		/* Grid Asesoria No Enviadas */
		function gridNoEnvioAsesoria(){
			jQuery("#gridNoEnvioAsesoria").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudesNoEnviadasAsesoria.do', 
				datatype: "xml", 
				colNames:['Caso','Expediente','Interesado','Etapa'], 
				colModel:[ 	{name:'caso',index:'1', width:185,align:"center"},
				         	{name:'expediente',index:'2', width:185,align:"center"},
				           	{name:'interesado',index:'3', width:215,align:"center"},
				          	{name:'etapa',index:'4', width:185,align:"center"}
						],
				pager: jQuery('#pagGridNoEnvioAsesoria'),
				rowNum:20,
				rowList:[20,40,60,80,100],
				autowidth: true,
				//autoheight:true,
				sortname: '2',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					detalleEnvioAsesoriaLegal(rowid);
				}			
			}).navGrid('#pagGridNoEnvioAsesoria',{edit:false,add:false,del:false});
			$("#gview_gridNoEnvioAsesoria .ui-jqgrid-bdiv").css('height', '525px');
			jQuery("#gridNoEnvioAsesoria").trigger("reloadGrid");
		}
		
		//grid de solicitudes de poder judicial
		function gridSolPoderJudicial(){			
			jQuery("#gridPoderJudicial").jqGrid({ 
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
				pager: jQuery('#pagerPoderJudicial'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				autoheight:true,
				viewrecords: true,
				ondblClickRow: function(rowid) {

					mostrarDetalleDesignaciones(rowid,4,"");

				}			
			}).navGrid('#pagerPoderJudicial',{edit:false,add:false,del:false});
			$("#gview_gridPoderJudicial .ui-jqgrid-bdiv").css('height', '450px');
			jQuery("#gridPoderJudicial").trigger("reloadGrid");
		}
	
	
		//Ventana de captura de queja ciudadana
		function muestraQuejaCiudadana(){
			
			$.newWindow({id:"iframewindowQuejaCiudadana", statusBar: true, posx:255,posy:111,width:1023,height:473,title:"Queja Ciudadana", type:"iframe"});
		    $.updateWindowContent("iframewindowQuejaCiudadana",'<iframe src="<%=request.getContextPath()%>/quejaCiudadana.do" width="1023" height="473" />'); 
		}

		function cambiaTipoBusquedaAsesoria(){

			tipoBusqueda ="asesoriaLegal";
			obtenTurnoPopup();

			}

		function cambiaTipoBusquedaSolicitud(){

			tipoBusqueda = "solicitud";
			obtenTurnoPopup();

			}
		
		//Vamos a BD por el turno penal siguiente
		function obtenTurnoPopup(){
			
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/busquedaSiguienteTurnoDefensoria.do?tipoBusqueda='+tipoBusqueda+'',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var errorCode;
	    			errorCode=$(xml).find('response').find('code').text();
	    			//Si errorCode=0 entonces continuamos con el flujo
	    			if(parseInt(errorCode)==0)
	    			{
	    				turnoEnCurso = $(xml).find('numeroTurno').text();
	    				//asignamos el numero de turno al span del popup
	    				$("#spanNumTurno").html(''+$(xml).find('numeroTurno').text());
	    				var tipoTurno="";
	    				if($(xml).find('tipoTurno').text()=='ASESORIA_LEGAL')
	    				{
	    					tipoTurno="ASESORIA LEGAL";
	    				}
	    				else
	    				{
	    					tipoTurno="SOLICITUD CIUDADANA DE DEFENSOR";
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
							if(	tipoBusqueda=="asesoriaLegal"){
								cargaAcuse('asesoria');
								asesoriaLegal();
								
								}else{
									busquedaExpedienteCaso();
									}
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
			//} else {
				//alert("Usted no puede abrir mas de una ventana");
			//}
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
	    			alert("Turno Cancelado");
	    			//numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
	    			//alert(numeroExpediente);
	    		}
	    		
	    	});
		}

		function actualizaEtapaTemp() {
			var param ="";
			
			param += 'etapaExpediente='+ $('#etapaOptionDEATT option:selected').val();
			param+= '&documentoId='+documentoId1;
			//alert(param);
			   $.ajax({
			     type: 'POST',
			     url: '<%= request.getContextPath()%>/ActualizarEtapaExpedienteSolicitudDefensoria.do',
				 data: param,
				 dataType: 'xml',
				 success: function(xml){
					//alert(param);
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
		,	center__minWidth:				10
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
			,	initClosed:				true
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
			,	minWidth:				0
			,	minHeight:				0
			//,	initClosed:				false
			,	onresize_end:			function () { $("#gridDesignaciones").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridAvisosDeDetencion").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridSolicitudAtencionTemprana").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridPoderJudicial").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridSubordinados").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridExpedientesSubordinados").setGridWidth($("#mainContent").width() - 5, true);
													$("#gridAsesorias").setGridWidth($("#mainContent").width() - 5, true);
										}
		}
		};


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

		function mostrarDetalleSolicitudNoEnviada(rowid){
			alert(rowid);
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
				
				<!--Tab de Turnos-->
				<h3><a href="#" onmousedown="ocultaMuestraGrids('6');">Turnos </a></h3>
					<div >
					<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" class="fondoClaroAp" style="cursor:pointer">
						<tr >
						   <td width="100%" id="administrativa" ><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><span class="txt_tit_turno">Asesoria Legal</span></td>
						</tr>
						<tr>
						   <td width="100%" id="admin-TurnoA"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">4</span></td>
						   </tr>
						   <tr>
						   <td width="100%" id="admin-TurnoB"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">5</span></td>
						</tr>
						<tr>
						   <td width="100%" id="admin-TurnoC"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">6</span></td>
						   </tr>
						   <tr>
						   <td width="100%" id="admin-TurnoD"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">7</span></td>
						</tr>
							<tr>
						   <td width="100%" id="penal"><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><span class="txt_tit_turno">Solicitud ciudadana de defensor</span></td>
						</tr>
						<tr>
						   <td width="100%" id="penal-TurnoA"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">1</span></td>
						   </tr>
						   <tr>
						   <td width="100%" id="penal-TurnoB"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">2</span></td>
						</tr>
						<tr>
						   <td width="100%" id="penal-TurnoC"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">3</span></td>
						   </tr>
						   <tr>
						   <td width="100%" id="penal-TurnoD"><img src="<%= request.getContextPath() %>/resources/images/icn_fleup.png" width=16 height=14>&#8194;&#8194;&#8194;<span class="txt_num_turno">4</span></td>
						</tr>
											
				</table>
				</div>

				<h3><a><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes sin enviar</a></h3>
				<div>
					<ul id="seccion2tree" class="filetree">								
						<li class="closed"><span id="noEnvioSolicitud" class="folder" onclick="ocultaMuestraGrids('10');">Solicitudes de Defensor</span>							
							<ul>
							</ul>
						</li>
						<li class="closed"><span id="noEnvioAsesoria" class="folder" onclick="ocultaMuestraGrids('9');">Solicitudes de Asesoria</span>
							<ul>
							</ul>
						</li>
					</ul>
				</div>

				<h3><a href="#" id="designaciones" onmousedown="ocultaMuestraGrids('1');" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes sin atender</a></h3>
				<div>
					
				</div>
				
				<h3><a href="#" id="avisosDetencion" onmousedown="ocultaMuestraGrids('2');" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Avisos de personas detenidas</a></h3>
				<div>
					
				</div>
				<!--Tab de Solicitudes de defensor-->
				<h3><a href="#" id="ligSolicitudesDefensor" onmousedown="ocultaMuestraGrids('4');"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes de Defensor</a></h3>
					<div>
						<ul id="seccion1tree" class="filetree">								
							<li class="closed"><span id="solicitudPoderJudicial" class="folder" onmousedown="ocultaMuestraGrids('4');">Poder Judicial</span>							
								<ul>
								</ul>
							</li>
							<li class="closed"><span id="solicitudAtencionTemprana" class="folder" onmousedown="ocultaMuestraGrids('3');">Atenci&oacute;n Temprana</span>
								<ul>
								</ul>
							</li>
							<li class="closed"><span id="asesorias" class="folder" onmousedown="ocultaMuestraGrids('7');">Asesorias</span>							
								<ul>
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
				
				<!--h3>
					<a href="#">Clima</a>
				</h3>
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
				<!--h3>
					<a href="#">Chat</a>
				</h3>
				<div align="center">
				
					<div id="dialogoChat" title="Chat" align="center">
						<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
					</div>
					<center>
						<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
					</center>
						<!--iframe src="http://gaby1:5280/web/jwchat/index.html" frameborder="0" width="200" height="200" scrolling="no"></iframe  -->
				<!--/div-->
				<h3><a href="#" onclick="consultarTiposRol();">Facultades</a></h3>
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
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ATENCION TEMPRANA DE DEFENSORIA</TD>
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
				<li id="tbarBtnHeaderZise" class="first"><span></span>
				<li id="tbarBtnQuejaCiudadana" class="first"><span></span>Recibir queja<!--img src="<%= request.getContextPath() %>/resources/images/icn_errorinfo.png" width="15" height="16"--></li>
				<li id="tbarBtnLlamarTurnoSolicitud" class="first"><span></span>LLamar turno para solicitud de defensor<!--img src="<%= request.getContextPath() %>/resources/images/icn_turno.png" width="15" height="16"--></li>
				<li id="tbarBtnLlamarTurnoAsesoria" class="first"><span></span>LLamar turno para asesoria<!--img src="<%= request.getContextPath() %>/resources/images/icn_turno.png" width="15" height="16"--></li>
				
				</li>
				
			</div>
			
			<div id="menu_config">
				<li id="buscarCaso"><span></span>Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
				<li id="buscarExpediente"><span></span>Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
				<li id="verde">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
				<!-- <li id="buscarCaso"><a href="#">Buscar Caso</a></li>
				<li id="buscarExpediente"><a href="#">Buscar Expediente</a></li> 
				<li id="generarDocumento">Generar Documento</li>
				 <li>Imprimir</li> 
				<li>Guardar</li>
				<li><a href="#">Digitalizar Documento</li>
				<li><a href="#">Turnar Documento</a></li>
				<li>Enviar Notificaciones</li>
				<li>config01</li>
				<li>Config02</li>
			    <li>Config03</li>
				<li class="last">config04</li>-->
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
				<div id="divGridDesignaciones">
				<table id="gridDesignaciones"></table>
				<div id="pagerGridDesignaciones"></div>
				</div>
				<div id="divGridAvisosDeDetencion">
				<table id="gridAvisosDeDetencion"></table>
				<div id="pagerAvisosDeDetencion"></div>
				</div>
				<div id="divGridSolicitudAtencionTemprana">
				<table id="gridSolicitudAtencionTemprana"></table>
				<div id="pagGridSolicitudAtencionTemprana"></div>
				</div>
				<div id="divGridPoderJudicial">
				<table id="gridPoderJudicial"></table>
				<div id="pagerPoderJudicial"></div>
				</div>
				<div id="divGridExpedientesSubordinados">
				<table id="gridExpedientesSubordinados"></table>
				<div id="pagGridExpedientesSubordinados"></div>
				</div>
				<div id="divGridAsesorias">
				<table id="gridAsesorias"></table>
				<div id="pagGridAsesorias"></div>
				</div>				
				<div id="divGridNoEnvioSolicitud">
				<table id="gridNoEnvioSolicitud"></table>
				<div id="pagGridNoEnvioSolicitud"></div>
				</div>				
				<div id="divGridNoEnvioAsesoria">
				<table id="gridNoEnvioAsesoria"></table>
				<div id="pagGridNoEnvioAsesoria"></div>
				</div>				
				<div id="divGenerarTurno">
				<table align="center">
					<tr>
						<td>
							<jsp:include page="vAdmonRecepcion.jsp"><jsp:param value="defensoria" name="defensoria"/></jsp:include>
						</td>
					</tr>
				</table>
				<table height="200px"></table>
				</div>
								
			</div>
		</div>
	</div>
	

	<div id="dialog-confirm" title="Turno: " style="display: none">
		<p align="center"><span style="font-size: 25px;" id="spanTipoTurno">.</span><br/><span style="font-size: 115px;" id="spanNumTurno">15</span></p>
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
