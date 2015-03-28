<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
		var outerLayout, innerLayout;
		var tipoResolutivo;
		var formaId;
		var idWindowNuevaDenuncia=1;

		var idWindowMostrarRegistroDetencion=1;
		
		var primeraVezGridAvisos=true;

		//Variable para controlar el grid que se carga en el onready de la pagina
		var primeraVezGridInformePolicial=true;

		/**Variables para la ceja solicitudes**/
		var primeraVezGridSolicitudesDeTraslado=true;
		
		var primeraVezGridRegistroDeDetencion=true;
		
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
			estilosMenu('procuracionJusticia');
			
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
			//cargamos la imagen dependiendo del usuario que llama la ppagina
			var idTipoUsuario='<%= request.getParameter("idUSer")%>';
			if(parseInt(idTipoUsuario)==1)
			{
				//imagen de sspPolicia
				$('#logoPagina').attr('src','<%=request.getContextPath()%>/resources/images/sis_penal.jpg');
				$("#mandamientosJudicialesSSPPM").css("display","none");
						}
			
			else
			{
				//policiaMinister
				$('#logoPagina').attr('src','<%=request.getContextPath()%>/resources/images/sis_penal.jpg');
				$("#apoyoFiscaliaSSPPolicia,#ConsyMediacionSSPPolicia,#quejaCiudadanaSSPPolicia").css("display","none");
				
				
				}
			
			//obtenemos el tiempo de las alarmas y ponemos en marcha el timer		
			var tiempo='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoRevisionAlarmas()%>';
			setInterval(muestraAlerta, tiempo);
			
			outerLayout = $("body").layout( layoutSettings_Outer );
			//llenamos el arbol de las solicitudes por atender del menu ixquierdo
			consultarTiposSolicitudPorAreaDelFuncionario('tableSolsXAtender',"0");
			
			$("#accordionmenuprincipal").accordion({  fillSpace: true });
			$("#accordionmenuderprincipal").accordion({ fillSpace: true});
			$("#detencion").click(cargaGridInformePolicial);
			$("#ingresoCedepro").click(muestraIngresoCedepro);
			$("#solicitarDefensor").click(muestraSolicitarDefensor);
			//$("#dictamenPericial").click(muestraDictamenPericial);			
			$("#controlAgenda").click(creaAgenda);
			$("#solicitudes").click(cargaGridSolicitud);
			//$("#procuracionJusticia").click(ocultaGridSolicitud);
			$('#divGridSolicitudes').hide();
			$("#divGridSolsXAtndr").hide();
			//Ocultanos la ceja del acordeon derecho Teporalmente
			//$('#conciliacionMedicion').hide();
			$("#tbarBtnQuejaCiudadana").click(muestraQuejaCiudadana);
			$("#tbarBtnVerGrafica").click(muestraVerGrafica);
			$('#imageViewer').click(generaVisorGraficaView);
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
			
			//Grid de Solicitudes por atender
			jQuery("#gridSolsXAtndr").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente','IdExpediente'], 
				colModel:[ 	{name:'caso',index:'caso', width:150},
				           	{name:'expediente',index:'expediente', width:130}, 
							{name:'folio',index:'folio', width:100}, 
							{name:'estatus',index:'estatus', width:100}, 
							{name:'fechaCreacion',index:'fechaCreacion', width:100},
							{name:'fechaLimite',index:'fechaLimite', width:100},
							{name:'institucion',index:'institucion', width:100},
							{name:'remitente',index:'remitente', width:200},
							{name:'idExpediente',index:'idexpediente', width:200,hidden:true}
						],
				pager: jQuery('#pagerGridSolsXAtndr'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					nuevoNumeroExpediente(rowid);
						}
			}).navGrid('#pagerGridSolsXAtndr',{edit:false,add:false,del:false});	
			$("#gview_gridSolsXAtndr .ui-jqgrid-bdiv").css('height', '400px');
			
			//carga del grid por default
			//cargaGridInformePolicial();	
			cargaGridAvisosAuxilio(<%=EstatusNotificacion.NO_ATENDIDA.getValorId()%>);
			
			$('#test').weatherfeed(['MXDF0132']);

			//crea el arbol para traslados
			$("#seccion6treePJENC,#apoyoFiscaliaSSPPolicia,#seccion8treePJENC,#seccion9treePJENC").treeview();
			$("#seccion5tree").treeview();

		});
		
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
		*Funcion para realizar la consulta del grid de solicitudes por Atender
		*/
		function cargaGridSolsXAtndr(tipoSolicitud,idArea)
		{
			jQuery("#gridSolsXAtndr").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsXAtnder.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>',datatype: "xml" });
			$("#gridSolsXAtndr").trigger("reloadGrid");
			muestraOcultaGrids("gridSolsXAtndr");
		}

		function creaAgenda() {
			$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
		    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');		
		}

		function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
		}
		
		function administrarResolutivosImputado(){
			
			if(tipoResolutivo == "Prisión Preventiva"){

				$.newWindow({id:"iframewindowAdministrarResolutivosImputado", statusBar: true, posx:200,posy:50,width:840,height:450,title:"Administrar Resolutivos Imputado", type:"iframe"});
			    $.updateWindowContent("iframewindowAdministrarResolutivosImputado",'<iframe src="<%=request.getContextPath()%>/visorAdministrarResolutivosImputado.do" width="840" height="450" />'); 
					  	  			  	  		
	  	  		}else{				
	  	  		
		  	  	$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Resolutivo", type:"iframe"});
			    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/enviaResolutivo.do?esconderArbol=1&formaId=18' width='1140' height='400' />");
	  	  		
  	  	  		}  		
			
			}

		function muestraDetencion(folioIPH){
			$.newWindow({id:"iframewindowDetencion", statusBar: true, posx:258,posy:139,width:927,height:530,title:"Detención", type:"iframe"});
		    $.updateWindowContent("iframewindowDetencion",'<iframe src="<%=request.getContextPath()%>/administrarDetencionDePersona.jsp?folioIPH='+folioIPH+'" width="927" height="530" />'); 
		}

		function muestraIngresoCedepro(){
			$.newWindow({id:"iframewindowIngresoCedepro", statusBar: true, posx:255,posy:112,width:850,height:350,title:"Ingreso a CEDEPRO", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresoCedepro",'<iframe src="<%=request.getContextPath()%>/ingresoCedepro.jsp" width="850" height="350" />'); 
		}
	
		function muestraSolicitarDefensor(){
			$.newWindow({id:"iframewindowSolicitarDefensor", statusBar: true, posx:200,posy:50,width:850,height:350,title:"Solicitar Defensor", type:"iframe"});
		    $.updateWindowContent("iframewindowSolicitarDefensor",'<iframe src="<%=request.getContextPath()%>/solicitarDefensor.jsp" width="850" height="350" />'); 
		}

		//Temporalmente no se utiliza esta funcion
		function muestraDictamenPericial(){
			$.newWindow({id:"iframewindowDictamenPericial", statusBar: true, posx:200,posy:50,width:850,height:350,title:"Solicitar Dictamen Pericial", type:"iframe"});
		    $.updateWindowContent("iframewindowDictamenPericial",'<iframe src="<%=request.getContextPath()%>/solicitarDictamenPericial.jsp" width="850" height="350" />'); 
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
		
		//Ventana consulta de llamadas de auxilio
		function mostrarVentanaLlamada(rowid, estatus){
			$.newWindow({id:"iframewindowLlamadaAuxilio", statusBar: true, posx:50,posy:80,width:1200,height:440,title:"Aviso de Auxilio", type:"iframe"});
		    $.updateWindowContent("iframewindowLlamadaAuxilio",'<iframe src="<%=request.getContextPath()%>/avisosAuxilio.do?avisoId='+rowid+'&estatus='+estatus+'" width="1200" height="440" />'); 
		}

		function cerrarVentanaAviso(){
			var pantalla ="iframewindowLlamadaAuxilio";
			$.closeWindow(pantalla);
		}
		
		function muestraVerGrafica(){
			$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
		    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/detallevisorGrafica.do" width="1140" height="400" />');
		}

		function cargaGridSolicitud (){

		jQuery("#gridElaborarOficioResolutivoJuez").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarSolicitudesMandatoJudicial.do', 
			datatype: "xml", 
			colNames:['Número de IPH','Nombre del Inculpado','Delito','Tipo de Resolutivo','Resolución Emitida Por','Fecha de Resolución' ], 
			colModel:[ 	{name:'iph',index:'1', width:40, align:"center"}, 
						{name:'nombreInculpado',index:'2', width:45, align:"center"}, 
						{name:'delito',index:'3', width:30, align:"center"}, 
						{name:'tipoResolutivo',index:'4', width:35, align:"center"},
						{name:'emitidoPor',index:'5', width:50, align:"center"}, 
						{name:'fechaResolucion',index:'6', width:45, align:"center"}
					],
			pager: jQuery('#paginadorSolicitudes'),
			rowNum:20,
			rowList:[10,20,30],
			width:765,
			height:440,
			sortname: '1',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
				
				var ret = jQuery("#gridElaborarOficioResolutivoJuez").jqGrid('getRowData',rowid);

				tipoResolutivo = ret.tipoResolutivo;
				
				administrarResolutivosImputado();
			}
		}).navGrid('#paginadorSolicitudesPJATP',{edit:false,add:false,del:false});
		$("#gview_gridElaborarOficioResolutivoJuez .ui-jqgrid-bdiv").css('height', '440px');

		muestraOcultaGrids("solicitudes");				  
		
		}

		function ocultaGridSolicitud(){

			$('#divGridSolicitudes').hide();

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
			,	onresize_end:			function () { $("#gridElaborarOficioResolutivoJuez").setGridWidth($("#mainContent").width() - 10, true); }
			}
		};
						
		function estilosMenu(opc){
			if(opc=='procuracionJusticia'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp1'){
				$("#sp1").css({ color: "#E78F08"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp2'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#E78F08"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp3'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#E78F08"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp4'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#E78F08"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp5'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#E78F08"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp6'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#E78F08"});
				$("#sp7").css({ color: "#1C94C4"});
			}else if(opc=='sp7'){
				$("#sp1").css({ color: "#1C94C4"});
				$("#sp2").css({ color: "#1C94C4"});
				$("#sp3").css({ color: "#1C94C4"});
				$("#sp4").css({ color: "#1C94C4"});
				$("#sp5").css({ color: "#1C94C4"});
				$("#sp6").css({ color: "#1C94C4"});
				$("#sp7").css({ color: "#E78F08"});
			}
		}


		//Abre una nueva ventana para informar lugar de los hechos
		function abreVentanaLugarDeHechos() {
			$.newWindow({id:"iframewindowLugarDeHechos", statusBar: true, posx:50,posy:80,width:1200,height:430,title:"Llamadas de auxilio", type:"iframe"});
		    $.updateWindowContent("iframewindowLugarDeHechos",'<iframe src="<%= request.getContextPath() %>/lugarDeLosHechos.do" width="1200" height="430" />');		
		}

		function cerrarVentanaLugarDeHechos(){
			var pantalla ="iframewindowLugarDeHechos";
			$.closeWindow(pantalla);
		}
		

/****************************************************************FUNCIONALIDAD PARA EL ON READY/*******************************************************************/
	 	/*
		*Funcion que carga el grid de consulta por fechas
		*/
		function cargaGridInformePolicial(){
			if(primeraVezGridInformePolicial == true){
							
				  jQuery("#gridInformePolicialSPUCA").jqGrid({ 
						url:'<%= request.getContextPath() %>/consultarIPHs.do?involucrado=false',					
						datatype: "xml",
						colNames:['Folio de control','Tipo de Evento','Subtipo de Evento','Detencion','Fecha Informe','Hora Informe','Atendida'], 
						colModel:[ 	{name:'folioControl',index:'1',width:100, align:'center'},
									{name:'tipoEvento',index:'2',width:100, align:'center'}, 
									{name:'subTipoEvento',index:'3', width:100, align:'center'}, 
									{name:'detencion',index:'4', width:50, align:'center', formatter: "checkbox",
										 formatoptions: {disabled : false}},
									{name:'fechaInforme',index:'5', width:50, align:'center'},
									{name:'horaInforme',index:'6', width:50, align:'center'},
									{name:'atendida',index:'7', width:50, align:'center',formatter:'checkbox'},
								],
						autowidth: false,
						width:924, 
						pager: jQuery('#pagerGridInformePolicialSPUCA'),
						rowNum:10,
						rowList:[10,20,30],
						sortname: '1',
						sortorder: "desc", 
						viewrecords: true,
						//caption:"Resultado de la B&uacute;squeda",
						ondblClickRow: function(rowid) {
							//validaMuestraDetencion(rowid);
							mostrarIPH(rowid);
						} 
					}).navGrid('#pagerGridInformePolicialSPUCA',{edit:false,add:false,del:false}); 
					$("#gview_gridInformePolicialSPUCA .ui-jqgrid-bdiv").css('height', '440px');
					primeraVezGridInformePolicial = false;
					//Resize del grid
					$("#gridInformePolicialSPUCA").setGridWidth($("#mainContent").width() - 5, true);
					muestraOcultaGrids("informePolicial");
			}
			else{
				jQuery("#gridInformePolicialSPUCA").jqGrid('setGridParam', {url:'local',datatype: "xml" });
				$("#gridInformePolicialSPUCA").trigger("reloadGrid");
				muestraOcultaGrids("informePolicial");				  
			}
		}

		//Muestra el visor con la información del IPH
		function mostrarIPH(rowid){
			var ret = jQuery("#gridInformePolicialSPUCA").jqGrid('getRowData',rowid);
			var folioIPH = ret.folioControl;
			$.newWindow({id:"iframewindowIPH", statusBar: true, posx:20,posy:30,width:1400,height:600,title:"Ingresar Informe Policial Homologado:" +folioIPH, type:"iframe"});
		    $.updateWindowContent("iframewindowIPH",'<iframe src="<%=request.getContextPath()%>/mostrarIPH.do?folioIPH='+folioIPH+'&rowid='+rowid+'" width="1400" height="600" />'); 
		}

		//Muestra el visor de detencion solo para aquellas solicitudes que no han sido atendidas
		function validaMuestraDetencion(idInforme){
			if(idInforme != null){
				var rowSelectedData = jQuery("#gridInformePolicialSPUCA").jqGrid('getRowData',idInforme);
				var folioIPH = rowSelectedData.folioControl;
				//if(rowSelectedData.atendida == true){
					muestraDetencion(folioIPH);
			}
		}


		/*
		*Muestra u oculta los grids dependiendo del grid que fue seleccionado
		*/
		function muestraOcultaGrids(accion){
			if(accion == "informePolicial"){
				$('#gridAcuerdos').hide();
				$('#gridMediar').hide();
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').show();
				$('#divGridSolicitudes').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "solicitudes"){
				$('#gridAcuerdos').hide();
				$('#gridMediar').hide();
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridSolicitudes').show();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "gridAvisosAuxilio"){
				$('#divGridTrasladosSSPP').hide();
				$('#divGridAvisosAuxilio').show();
				$('#divGridSolicitudes').hide();
				$('#gridAcuerdos').hide();
				$('#gridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridInformePolicialSPUCA').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "Mediar"){
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').show();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "Acuerdos"){
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').show();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "QuejaPendiente"){
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').show();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "QuejaConcluida"){
				$('#divGridTrasladosSSPP').hide();
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridQuejaConcluida').show();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "traslados"){
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridTrasladosSSPP').show();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}	
			if(accion == "gridSolsXAtndr"){
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridTrasladosSSPP').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").show();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").hide();
			}
			if(accion == "registroDeDetencion"){
				$('#divGridInformePolicialSPUCA').hide();
				$('#divGridAcuerdos').hide();
				$('#divGridSolicitudes').hide();
				$('#divGridMediar').hide();
				$('#divGridQuejaPendiente').hide();
				$('#divGridTrasladosSSPP').hide();
				$('#divGridQuejaConcluida').hide();
				$("#divGridSolsXAtndr").hide();
				$('#divGridAvisosAuxilio').hide();
				$("#divGridRegistroDeDetencion").show();
			}	
				
	 	}		
/****************************************************************FUNCIONALIDAD PARA EL ON READY/*******************************************************************/
	 function muestraGridMediar(){
		 jQuery("#gridMediar").jqGrid({
				url : '<%= request.getContextPath()%>/.do', 
				datatype: "xml", 
				
				colNames:['Folio Aviso Auxilio','Folio de Control IPH', 'Presunto Responsable','Víctima'], 
				colModel:[ 	
				        	{name:'Auxilio',index:'1', sortable:false,width:191},
				           	{name:'Control',index:'2', sortable:false,width:191},
				           	{name:'Presunto',index:'3', sortable:false,width:191},
				           	{name:'Victima',index:'4', sortable:false,width:191}, 
				],
				pager: jQuery('#paginadorMediar'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				height:450,
				width:767,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				onSelectRow: function(rowID) {
					visorConciliacionMediacion();
				}
		}).navGrid('#paginadorMediar',{edit:false,add:false,del:false});
			 muestraOcultaGrids("Mediar");
	 }

	function muestraGridAcuerdos(){
		 jQuery("#gridAcuerdos").jqGrid({
				url : '<%= request.getContextPath()%>/.do', 
				datatype: "xml", 
				colNames:['Folio Acuerdo','Folio de cadena de custodia','Folio de control IPH','Presunto responsable'], 
				colModel:[ 	
				        	{name:'Acuerdo',index:'1', sortable:false},
				           	{name:'Cadena',index:'2', sortable:false},
				           	{name:'Control',index:'3', sortable:false}, 
				           	{name:'Responsable',index:'4', sortable:false}, 
				],
				pager: jQuery('#paginadorAcuerdos'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
			    height:450,
				width:767,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				onSelectRow: function(rowID) {
					visorConciliacionMediacion();
				}
		}).navGrid('#paginadorAcuerdos',{edit:false,add:false,del:false});
			 muestraOcultaGrids("Acuerdos");
	}

	//Abre una nueva ventana para informar lugar de los hechos
	function visorConciliacionMediacion() {
		$.newWindow({id:"iframewindowConciliacionMediacion", statusBar: true, posx:255,posy:111,width:838,height:399,title:"Por Medida y Acuerdos", type:"iframe"});
	    $.updateWindowContent("iframewindowConciliacionMediacion",'<iframe src="<%= request.getContextPath() %>/visorConciliacionMediacion.do" width="838" height="399" />');		
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
		 muestraOcultaGrids("QuejaPendiente");
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
		 muestraOcultaGrids("QuejaConcluida");
	}
/****************************************************FUNCIONALIDAD PARA LA CEJA DE SOLICIYUDES*********************************************************************/
	
	/**************************************************FUNCIONALIDAD PARA SOLICIYUDES DE TRASLADOS*****************************************************************/
	/*
	*Funcion que carga el grid con el historico del estado del expediente
	*/
	function cargaGridSolicitudesDeTraslado(estatus){
		if(primeraVezGridSolicitudesDeTraslado == true){
						
			  jQuery("#gridTrasladosSSPP").jqGrid({ 
					//url:'<%= request.getContextPath() %>/consultarSolicitudesTrasladoPorEstatus.do?estado='+estatus+'',					
					datatype: "xml",
					colNames:['Número de Caso','Funcionario que Autoriza','Persona a Trasladar','Fecha Traslado','Hora Traslado','Destino'], 
					colModel:[ 	{name:'numCaso',index:'1',width:250, align:'center'},
								{name:'funcionarioAutoriza',index:'2',width:200, align:'center'}, 
								{name:'personaTrasladar',index:'3', width:200, align:'center'}, 
								{name:'fechaTraslado',index:'4', width:100, align:'center'},
								{name:'horaTraslado',index:'5', width:100, align:'center'},
								{name:'destino',index:'6', width:200, align:'center'},
							],
						autowidth: false,
						width:924, 
						pager: jQuery('#pagerGridTrasladosSSPP'),
						rowNum:10,
						rowList:[10,20,30],
						sortname: '1',
						sortorder: "desc", 
						viewrecords: true,
						//caption:"Resultado de la B&uacute;squeda",
						ondblClickRow: function(rowid) {
							consultaSolicitudTraslados(rowid,estatus);
						} 
				}).navGrid('#pagerGridTrasladosSSPP',{edit:false,add:false,del:false}); 
				$("#gview_gridTrasladosSSPP .ui-jqgrid-bdiv").css('height', '440px');
				primeraVezGridSolicitudesDeTraslado = false;
				//Resize del grid de estado expediente
				$("#gridTrasladosSSPP").setGridWidth($("#mainContent").width() - 5, true);
		}
		else{
			jQuery("#gridTrasladosSSPP").jqGrid('setGridParam', {url:'local',datatype: "xml" });
			$("#gridTrasladosSSPP").trigger("reloadGrid");
		}
		muestraOcultaGrids("traslados");
	}

	function visorLeyesCodigos() {
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:254,posy:111,width:809,height:468,title:"Leyes y C&oacute;digos", type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/detalleLeyesyCodigos.do" width="809" height="468" />');
	}

	/*
	*Funcion que llama a la funcionalidad para generar un visualizador de imagen  $('#imageViewer').click(generaVisorGraficaView);
	*/
	function generaVisorGraficaView() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:63,posy:111,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="400" />');
	}	

	function consultaSolicitudTraslados() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Visor Traslados", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/detallevisorTraslados.do" width="1140" height="400" />');
	}	

	
	/**FUNCIONES PARA GENERAR EL NUEVO NUMERO DE EXPEDIENTE**/
	//Funcion ke genera un nuevo numero de expediente para la ui en el mismo expediente
	function nuevoNumeroExpediente(id){
		
		var rowd = jQuery("#gridSolsXAtndr").jqGrid('getGridParam','selrow');
		var retd = jQuery("#gridSolsXAtndr").jqGrid('getRowData',rowd);
		id=retd.idExpediente.substring(retd.idExpediente.indexOf(">")+1,retd.idExpediente.indexOf("<",1));

		var idExpediente="0";
		var numeroExpediente="0";
		var numeroExpedienteId="0";
		var numeroGeneralCaso="0";
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/nuevoNumeroExpediente.do?idArea='+<%=Areas.POLICIA_MINISTERIAL.ordinal()%>+'&idExpediente='+id,
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').text();
    			numeroGeneralCaso=$(xml).find('expedienteDTO').find('numeroGeneralCaso').text();
    		}
    		
    	});
    	if(numeroExpedienteId!="0"){
        	var pantallaSolicitada=6;
    		idWindowNuevaDenuncia++;
    		 var ingresoDenuncia = true;
    		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente, type:"iframe"});
    		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idExpedienteop='+idExpediente+'&pantallaSolicitada='+pantallaSolicitada+'&numeroExpediente='+numeroExpediente+'&idNumeroExpedienteop='+numeroExpedienteId+'&idNumeroExpediente='+numeroExpedienteId+'&numeroGeneralCaso='+numeroGeneralCaso+'" width="1430" height="670" />');
    		 //nuevaDenunciaFaci(idExpediente);
        }
	}
	
	function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Expediente: "+num);
	}
    /**FIN - FUNCIONES PARA GENERAR EL NUEVO NUMERO DE EXPEDIENTE**/
    
	/*
	*Funcion que carga el grid con los expediente de registro de detencion según su estatus
	*/
	function cargaGridRegistroDeDetencion(estatus){

		//confirmarRegistroDeDetencion(1);
		if(primeraVezGridRegistroDeDetencion == true){
						
			  jQuery("#gridRegistroDeDetencion").jqGrid({ 
					url:'<%= request.getContextPath() %>/consultarRegistroDeDetencionPorEstatus.do?estatus='+estatus+'',					
					datatype: "xml",
					colNames:['Número de Expediente','Folio Aviso Auxilio','Fecha del Aviso Auxilio','Probable Delito'], 
					colModel:[ 	{name:'NumeroExpediente',index:'1',width:250, align:'center'},
								{name:'FolioAviso',index:'2',width:200, align:'center'}, 
								{name:'FechaAviso',index:'3', width:200, align:'center'}, 
								{name:'ProbableDelito',index:'4', width:100, align:'center'}
							],
						autowidth: false,
						width:924, 
						pager: jQuery('#pagerGridRegistroDeDetencion'),
						rowNum:10,
						rowList:[10,20,30,40,50,60,70,80,90,100],
						sortname: '1',
						sortorder: "desc", 
						viewrecords: true,
						ondblClickRow: function(rowid) {
							confirmarRegistroDeDetencion(rowid);
						} 
				}).navGrid('#pagerGridRegistroDeDetencion',{edit:false,add:false,del:false}); 
				$("#gview_gridRegistroDeDetencion .ui-jqgrid-bdiv").css('height', '440px');
				primeraVezGridSolicitudesDeTraslado = false;
				//Resize del grid de estado expediente
				$("#gridRegistroDeDetencion").setGridWidth($("#mainContent").width() - 5, true);
		}
		else{
			jQuery("#gridRegistroDeDetencion").jqGrid('setGridParam', {url:'local',datatype: "xml" });
			$("#gridRegistroDeDetencion").trigger("reloadGrid");
		}
		muestraOcultaGrids("registroDeDetencion");
	}

	function consultaRegistroDeDetencionPorExpedienteId(rowid){
		var ret = jQuery("#gridRegistroDeDetencion").jqGrid('getRowData',rowid);
   		idWindowMostrarRegistroDetencion++;
   		$.newWindow({id:"iframewindowMostrarRegistroDetencion" + idWindowMostrarRegistroDetencion, statusBar: true, posx:150,posy:10,width:1000,height:580,title:"Registro de detención. Expediente:"+ret.NumeroExpediente+". No. Folio:"+ret.FolioAviso, type:"iframe"});
   		$.updateWindowContent("iframewindowMostrarRegistroDetencion" + idWindowMostrarRegistroDetencion,'<iframe src="<%= request.getContextPath() %>/mostrarRegistroDetencion.do?expedienteId='+rowid+'&numeroExpediente='+ret.NumeroExpediente+'" width="1000" height="580" />');		
	}

	function cargaGridAvisosAuxilio(estatus){
		if(primeraVezGridAvisos == true){
			jQuery("#gridAvisosAuxilio").jqGrid({
				url : '<%= request.getContextPath()%>/consultaGridAvisosAuxilio.do?estatus='+estatus+'', 
				datatype: "xml", 
				
				colNames:['Folio de Aviso','Tipo de Delito','Solicitante de Auxilio','Calidad','Domicilio','Fecha Aviso','Hora','Estatus'], 
				colModel:[ 	
				        	{name:'FolioAviso',index:'1', sortable:false,width: 200},
				           	{name:'TipoDelito',index:'2', sortable:false, width: 250},
				           	{name:'Solicitante',index:'3', sortable:false, width: 250}, 
				           	{name:'Calidad',index:'4', sortable:false, width: 150}, 							
				           	{name:'Domicilio',index:'5', sortable:false, width: 200}, 							
				           	{name:'Fecha',index:'6', sortable:false, width: 150}, 							
				           	{name:'Hora',index:'7', sortable:false, width: 150}, 							
				           	{name:'UnidadInvestigacion',index:'8', sortable:false, width: 250}, 
			],
			autowidth: false,
			width:924, 
			pager: jQuery('#paginadorgridAvisosAuxilio'),
			rowNum:10,
			rowList:[10,20,30],
			sortname: '1',
			viewrecords: true,
			sortorder: "asc",
			ondblClickRow: function(rowid) {
				mostrarVentanaLlamada(rowid, estatus);
			} 
			}).navGrid('#paginadorgridAvisosAuxilio',{edit:false,add:false,del:false});
			primeraVezGridAvisos=false;
		}else{
			jQuery("#gridAvisosAuxilio").jqGrid('setGridParam', 
					{url:'<%= request.getContextPath()%>/consultaGridAvisosAuxilio.do?estatus='+estatus+'',datatype: "xml" });
			$("#gridAvisosAuxilio").trigger("reloadGrid");
		}
		muestraOcultaGrids("gridAvisosAuxilio");
	}
    
	function cargaGridAvisosAuxilioConcluidos(estatusNotificacion){
		if(primeraVezGridAvisos == true){
			jQuery("#gridAvisosAuxilio").jqGrid({
				url : '<%= request.getContextPath()%>/consultaGridAvisosAuxilio.do?estatus='+estatusNotificacion+'', 
				datatype: "xml", 
				
				colNames:['Folio de Aviso','Tipo de Delito','Solicitante de Auxilio','Calidad','Domicilio','Fecha Aviso','Hora','Estatus'], 
				colModel:[ 	
				        	{name:'FolioAviso',index:'1', sortable:false,width: 200},
				           	{name:'TipoDelito',index:'2', sortable:false, width: 250},
				           	{name:'Solicitante',index:'3', sortable:false, width: 250}, 
				           	{name:'Calidad',index:'4', sortable:false, width: 150}, 							
				           	{name:'Domicilio',index:'5', sortable:false, width: 200}, 							
				           	{name:'Fecha',index:'6', sortable:false, width: 150}, 							
				           	{name:'Hora',index:'7', sortable:false, width: 150}, 							
				           	{name:'UnidadInvestigacion',index:'8', sortable:false, width: 250}, 
			],
			autowidth: false,
			width:924, 
			pager: jQuery('#paginadorgridAvisosAuxilio'),
			rowNum:10,
			rowList:[10,20,30],
			sortname: '1',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
				mostrarVentanaLlamada(rowid, estatusNotificacion);
			} 
			}).navGrid('#paginadorgridAvisosAuxilio',{edit:false,add:false,del:false});
			primeraVezGridAvisos=false;
		}else{
			jQuery("#gridAvisosAuxilio").jqGrid('setGridParam', 
					{url:'<%= request.getContextPath()%>/consultaGridAvisosAuxilio.do?estatus='+estatusNotificacion+'',datatype: "xml" });
			$("#gridAvisosAuxilio").trigger("reloadGrid");
		}
		muestraOcultaGrids("gridAvisosAuxilio");
	}
    
	function confirmarRegistroDeDetencion(rowid){
		consultaRegistroDeDetencionPorExpedienteId(rowid);		  		
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
					<h3>
						<a id="procuracionJusticia" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Detenciones</a>
					</h3>
					<div>
						<ul id="seccion5tree" class="filetree">
							<li class="closed" id=""><span class="folder">Avisos de Auxilio</span>
								<ul>
									<li>
										<span class="file" onclick="cargaGridAvisosAuxilio(<%=EstatusNotificacion.NO_ATENDIDA.getValorId()%>);">
											<a style="cursor: pointer;">Nuevas</a>
										</span>
									</li>
									<li>
										<span class="file" onclick="cargaGridAvisosAuxilioConcluidos(<%=EstatusNotificacion.ATENDIDA.getValorId()%>);">
											<a style="cursor: pointer;">Atendidas</a>
										</span>
									</li>
								</ul>
							</li>					
							<li class="closed" id=""><span class="folder">Registro de Detenci&oacute;n</span>
								<ul>
									<li>
										<span class="file" onclick="cargaGridRegistroDeDetencion(<%=EstatusExpediente.NO_ATENDIDO.getValorId()%>);">
											<a style="cursor: pointer;">Nuevas</a>
										</span>
									</li>
									<li>
										<span class="file" onclick="cargaGridRegistroDeDetencion(<%=EstatusExpediente.ABIERTO.getValorId()%>);">
											<a style="cursor: pointer;">En Proceso</a>
										</span>
									</li>
									<li>
										<span class="file" onclick="cargaGridRegistroDeDetencion(<%=EstatusExpediente.CANALIZADO.getValorId()%>);">
											<a style="cursor: pointer;">Atendidas</a>
										</span>
									</li>
								</ul>
							</li>					
						</ul>
					</div>
<!--					<h3>-->
<!--						<a id="solicitudes" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes</a>-->
<!--					</h3>-->
<!--					<div>-->
<!--						<ul id="seccion6treePJENC" class="filetree">-->
<!--							<li class="closed" id=""><span class="folder">Traslados</span>-->
<!--								<ul>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.ABIERTA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Nuevas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.CERRADA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Atendidas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--								</ul>-->
<!--							</li>	-->
<!--							</ul>-->
<!--							<ul  class="filetree" id="apoyoFiscaliaSSPPolicia" >-->
<!--							<li class="closed" ><span class="folder">Apoyo a Fiscalía</span>-->
<!--								<ul>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.ABIERTA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Nuevas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.CERRADA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Atendidas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--								</ul>-->
<!--							</li>					-->
<!--						</ul>-->
<!--					</div>-->
<!--					<h3>-->
<!--						<a id="Mandamientos" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Mandamientos</a>-->
<!--					</h3>-->
<!--					<div>-->
<!--						<ul id="seccion8treePJENC" class="filetree">-->
<!--							<li class="closed" id=""><span class="folder">Judiciales</span>-->
<!--								<ul>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.ABIERTA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Nuevas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.CERRADA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Atendidas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--								</ul>-->
<!--							</li>-->
<!--							</ul>	-->
<!--							<ul id="seccion9treePJENC" class="filetree">-->
<!--							<li class="closed" id="mandamientosJudicialesSSPPM" ><span class="folder">Apoyo a Fiscalia</span>-->
<!--								<ul>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.ABIERTA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Nuevas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--									<li>-->
<!--										<span class="file" onclick="cargaGridSolicitudesDeTraslado(<%=EstatusSolicitud.CERRADA.getValorId()%>);">-->
<!--											<a style="cursor: pointer;">Atendidas</a>-->
<!--										</span>-->
<!--									</li>-->
<!--								</ul>-->
<!--							</li>					-->
<!--						</ul>-->
<!--					</div>-->
<!--					-->
<!--					<h3 id="solicitudesPorAtenderSSPPolicia"><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes por Atender</a></h3>-->
<!--					<div>-->
<!--						<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0"  style="cursor:pointer" id="tableSolsXAtender">-->
<!--						</table>-->
<!--					</div>		-->
					<h3  id=""><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;L&iacute;neas de Investigaci&oacute;n</a></h3>
					<div>
						<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0"  style="cursor:pointer" id="tableSolsXAtender">
						</table>
					</div>
<!-- 					<h3> -->
<%-- 						<a id="avisos" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Avisos</a> --%>
<!-- 					</h3> -->
<!-- 					<div> -->
<!-- 						<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer"> -->
<!-- 							<tr> -->
<%-- 							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="lugarHechos" onclick="abreVentanaLugarDeHechos();">Llamadas de auxilio</a></td> --%>
<!-- 							</tr> -->
<!-- 						</table>	 -->
<!-- 					</div> -->

					<h3  id="ConsyMediacionSSPPolicia">
						<a id="conciliacionMedicion" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Conciliación y Mediación</a>
					</h3>
					<div>
					<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer">
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="Mediar" onclick="muestraGridMediar();">Por Mediar</a></td>
							</tr>
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="Acuerdo" onclick="muestraGridAcuerdos();">Acuerdo</a></td>
							</tr>
						</table>	
					</div>
					
					<h3  id="quejaCiudadanaSSPPolicia">
						<a id="" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Queja Ciudadana</a>
					</h3>
					<div>
					<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer">
							<!--  <tr>
							   <td><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" /><a id="Mediar" onclick="muestraGridQuejaNueva();">Nuevas</a></td>
							</tr>-->
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="Acuerdo" onclick="muestraGridQuejaPendiente();">Pendientes</a></td>
							</tr>
							<tr>
							   <td><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16" /><a id="Acuerdo" onclick="muestraGridQuejaConcluida();">Concluidas</a></td>
							</tr>
						</table>	
					</div>
					
					<h3 ><a id="imageViewer" href="#" onclick=""><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png"  width="15" height="15">Graficas y Reportes</a></h3>
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
			<!--h1>
				<a href="#">Chat</a>
			</h1>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>
			</div>
			<h1>
				<a href="#">Clima</a>
			</h1>
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
		</div>
	</div>


<div class="ui-layout-north">
	<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">Policia Ministerial</TD>
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
		<ul class="toolbar">
			<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			<li id="tbarBtnQuejaCiudadana" class="first"><span></span>Recibir queja&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_errorinfo.png" width="15" height="16"--></li>			
			<li id="lugarHechos" class="first" onclick="abreVentanaLugarDeHechos();"><span></span>Llamadas de auxilio <!--img src="<%= request.getContextPath() %>/resources/images/icn_folderchek.png" width="15" height="16"--></li>
		</ul>
	</div>
	<div id="menu_config">
		<ul class="toolbar">
			<li id="verde">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
		</ul>
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
						<table id="gridElaborarOficioResolutivoJuez"></table>
						<div id="paginadorSolicitudes"></div>
					</div>
					
					<!--Comienzan los grids los divs para los grids-->
					<div id="divGridInformePolicialSPUCA">
						<table id="gridInformePolicialSPUCA" ></table>
						<div id="pagerGridInformePolicialSPUCA"></div>
					</div>
					<!--Terminan los grids los divs para los grids-->
					
					<div id="divGridMediar" onclick="visorConciliacionMediacion()">
						<table id="gridMediar"></table>
						<div id="paginadorMediar"></div>
					</div>
					
					<!--Comienzan los grids los divs para los grids-->
					<div id="divGridAcuerdos" onclick="visorConciliacionMediacion()">
						<table id="gridAcuerdos" ></table>
						<div id="paginadorAcuerdos"></div>
					</div>
					
					
					
					<!--Comienzan los grids los divs para los grids-->
					<div id="divGridQuejaPendiente" >
						<table id="gridQuejaPendiente" ></table>
						<div id="paginadorgridQuejaPendiente"></div>
					</div>
					
					<div id="divGridQuejaConcluida" >
						<table id="gridQuejaConcluida" ></table>
						<div id="paginadorgridQuejaConcluida"></div>
					</div>

					<div id="divGridAvisosAuxilio" >
						<table id="gridAvisosAuxilio" ></table>
						<div id="paginadorgridAvisosAuxilio"></div>
					</div>

					<!-- Comienza grid traslados-->
					<div id="divGridTrasladosSSPP" onclick="consultaSolicitudTraslados();">
						<table id="gridTrasladosSSPP" ></table>
						<div id="pagerGridTrasladosSSPP"></div>
					</div>
					<!-- Termina grid traslados-->
					
					<div id="divGridSolsXAtndr" style="display: none;">
					 	<table id="gridSolsXAtndr" width="100%" height="100%"></table>
						<div id="pagerGridSolsXAtndr"></div>
					</div>
					
					<!-- Comienza grid Registro de Detencion-->
					<div id="divGridRegistroDeDetencion">
						<table id="gridRegistroDeDetencion" ></table>
						<div id="pagerGridRegistroDeDetencion"></div>
					</div>
					<!-- Termina grid Registro de Detencion-->
					
					<!--div para la ventana modal en donde se muestra la confirmacion del registro de detencion-->
					<div id="divConfirmarRegistroDeDetencion" style="display: none"></div>
					
					
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
