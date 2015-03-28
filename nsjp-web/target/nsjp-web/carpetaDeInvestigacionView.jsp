<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud" %>
<%@ page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas" %>
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
			#iRepLegalAccordionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iRepLegalAccordionPane dl{width:1000px;height:355px}	
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
			
			.ui-jqgrid tr.jqgrow td {
			    white-space: normal;
			    height:auto;
			    vertical-align:text-middle;
			    text-align:center;
			    padding-top:2px;
			}
		</style>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
	var tur;
	var outerLayout, innerLayout;
	var idWindowNuevaDenuncia=1;
	var idWindowAMP=1;
	//Variable para controlar el id de las ventanas
	var idWindowVisorMedidasCautelaresPJENC=1;
	//variable para guardar el ID del area del usuario logueado
	var idAreaUserLogged='<%=Areas.CORRDINACION_UNIDAD_INVESTIGACION.ordinal()%>';
	idWindowDetalleSolicitud=1;
	
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
		
		$('#remisionesIPH').click(cargaGridInformePolicial);
		
		//$('#imageViewer').click(generaVisorGraficaView);
		$("#casos-propios").css({ color: "#1C94C4"});
		$("#casos_at_penal").css({ color: "#1C94C4"});
		$("#casos_propios").css({ color: "#1C94C4"});		
		$("#casos-propios-abiertos").css({ color: "#1C94C4"});
		$("#casos-propios-temporales").css({ color: "#1C94C4"});
		$("#casos-propios-definitivos").css({ color: "#1C94C4"});
		$("#casos-propios-judicializados").css({ color: "#1C94C4"});
		$("#casos-propios-cerrados").css({ color: "#1C94C4"});
		
		$("#funcionarios-area").css({ color: "#1C94C4"});
		$("#funcionarios").css({ color: "#1C94C4"});
		$("#nombre-funcionario").css({ color: "#1C94C4"});
		$("#casos-funcionario-abiertos").css({ color: "#1C94C4"});
		$("#casos-funcionario-temporales").css({ color: "#1C94C4"});
		$("#casos-funcionario-definitivos").css({ color: "#1C94C4"});
		$("#casos-funcionario-judicializados").css({ color: "#1C94C4"});
		$("#casos-funcionario-cerrados").css({ color: "#1C94C4"});

		$("#fechas").css({ color: "#1C94C4"});
		$("#anio1").css({ color: "#1C94C4"});
		$("#abiertos-anio1").css({ color: "#1C94C4"});
		$("#temporales-anio1").css({ color: "#1C94C4"});
		$("#definitivos-anio1").css({ color: "#1C94C4"});
		$("#judicializados-anio1").css({ color: "#1C94C4"});
		$("#cerrados-anio1").css({ color: "#1C94C4"});
		
		
		$("#audiencia").css({ color: "#1C94C4"});
		$("#audiencia-general").css({ color: "#1C94C4"});
		$("#audiencia-urgente").css({ color: "#1C94C4"});
		$("#defensor").css({ color: "#1C94C4"});
		$("#audio-video").css({ color: "#1C94C4"});
		$("#transcripcion-audiencia").css({ color: "#1C94C4"});
		$("#atencion-victimas").css({ color: "#1C94C4"});
		$("#solicitud-dictamen-pericial").css({ color: "#1C94C4"});
		

		$("#cadena").css({ color: "#1C94C4"});
		$("#evidencia-cadena").css({ color: "#1C94C4"});
		$("#eslabon-cadena").css({ color: "#1C94C4"});
		$("#reporte-evidencia").css({ color: "#1C94C4"});
		$("#objeto1").css({ color: "#1C94C4"});
		$("#destino-evidencia-obj1").css({ color: "#1C94C4"});
		$("#almacen-obj1").css({ color: "#1C94C4"});
		
		$("#objeto2").css({ color: "#1C94C4"});
		$("#destino-evidencia-obj2").css({ color: "#1C94C4"});
		$("#almacen-obj2").css({ color: "#1C94C4"});
		
		$("#asentar-registro").css({ color: "#1C94C4"});
		$("#admin-almacen-virtual").css({ color: "#1C94C4"});
		
		
		$( "#tabsAtencionTempranaPenal" ).tabs();
		
		
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
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		$("#seccion1tree").treeview();
		$("#seccion11tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();
		
		$("#controlAgenda").click(creaAgenda);
		
		$("#exp0001").click(seleccionFila);	
		$("#exp0002").click(seleccionFila2);
		$("#expI0001").click(seleccionFila);	
		$("#expI0002").click(seleccionFila2);
		$("#expD0001").click(seleccionFila);	
		$("#expD0002").click(seleccionFila2);
		$("#expS0001").click(seleccionFila);	
		$("#expS0002").click(seleccionFila2);	
		$("#expC0001").click(seleccionFila);	
		$("#expC0002").click(seleccionFila2);
		//$("#tbarBtnGenerar").click(nuevaDenuncia);
		$("#tbarBtnAsignar").click(asigarPermisos);
		$("#tbarBtnDenunciaExt").click(denunciaExterna);
		$("#divGridSolsXAtndr").hide();
		
		//Mandamos consultar los tipos de solicitud dependiendo del Area del Funcionario
		consultarTiposSolicitudPorAreaDelFuncionarioGen('tableSolsGeneradas',"0");
		consultarTiposSolicitudPorAreaDelFuncionario('tableSolsXAtender',idAreaUserLogged);
		
		$('#iRepLegalAccordionPane').easyAccordion({ 
			autoStart: false, 
			slideInterval: 3000
		}); 
		muestraGadgets();
			
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=RECIBIR_CANALIZACION', 
			datatype: "xml", 
			autowidth: true,
			colNames:['Caso','Tipo','Fecha', 'Denunciante', 'Delito','Origen','Estatus'], 
			colModel:[ 	{name:'Detalle',index:'1',width:100},
			           	{name:'Tipo',index:'7', width:120, align:'center', hidden:true}, 
						{name:'Fecha',index:'2',width:55}, 
						{name:'Nombre',index:'3'}, 
						{name:'Resumen',index:'4'},
						{name:'Origen',index:'5',width:50},
						{name:'Estatus',index:'6',width:50}
					],
			pager: jQuery('#divGridDetalleFrmPrincipal'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: '1',
			//id: 'pager1',
			viewrecords: true,
			onSelectRow: function(id){
//				muestraAcordeon();
				},
			ondblClickRow: function(id) {
				nuevoNumeroExpediente(id);
				},
			sortorder: "desc"
		})
		.navGrid('#divGridDetalleFrmPrincipal',{edit:false,add:false,del:false});	 

//Grid de Solicitudes por atender
		jQuery("#gridSolsXAtndr").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			autowidth: true,
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creaci\u00f3n','Fecha Limite','Instituci\u00f3n','Remitente'],
			colModel:[ 	{name:'caso',index:'caso', width:180,hidden:true},
			           	{name:'expediente',index:'expediente', width:180}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:90},
						{name:'fechaLimite',index:'fechaLimite', width:80,hidden:true},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsXAtndr'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
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
			autowidth: true,
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creaci\u00f3n','Fecha Limite','Instituci\u00f3n','Destinatario'],
			colModel:[ 	{name:'caso',index:'caso', width:180,hidden:true},
			           	{name:'expediente',index:'expediente', width:180}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:90},
						{name:'fechaLimite',index:'fechaLimite', width:80,hidden:true},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsGeneradas'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudesGen(rowid);
					}
		}).navGrid('#pagerGridSolsGeneradas',{edit:false,add:false,del:false});
		
		//Grid de Mandamientos Judiciales
		jQuery("#gridMandamientosJudiciales").jqGrid({ 
			url:'local',  
			datatype: "xml", 
			colNames:['Caso','Tipo', 'Persona (s)','Fecha'], 
			colModel:[ 	{name:'caso',index:'caso', width:250},
			           	{name:'tipo',index:'tipo', width:200}, 
						{name:'persona',index:'persona', width:200}, 
						{name:'fecha',index:'fecha', width:150}
					],
			pager: jQuery('#pagerGridMandamientosJudiciales'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerGridMandamientosJudiciales',{edit:false,add:false,del:false});

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
		
//Ejemplo3.xml
		jQuery("#gridDetalleFrmUno").jqGrid({ 
			url:'<%=request.getContextPath()%>/.do', 
			datatype: "xml", 
		//	   |       | Denunciante     |          |            |         

			
			colNames:['Expediente','Fecha Remitido', 'Denunciante', 'Delito principal','Origen','Estatus'], 
			colModel:[ 	{name:'Caso',index:'detalle', width:125},
			           	{name:'Fecha',index:'fecha', width:125}, 
						{name:'Denunciante',index:'nombre', width:125}, 
						{name:'Fecha',index:'fecha', width:125}, 
						{name:'Denunciante',index:'nombre', width:125}, 
						{name:'Delito',index:'Resumen', width:125}
			
					],
			pager: jQuery('#pager3'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			onSelectRow: function(id){
				nuevaDenuncia(id);
				},
			sortorder: "desc"
		}).navGrid('#pager3',{edit:false,add:false,del:false});
//Ejemplo4.xml
		jQuery("#gridDetalleFrmDos").jqGrid({ 
			url:'<%=request.getContextPath()%>/.do', 
			datatype: "xml", 
			colNames:['Caso','Fecha Remitido', 'Denunciante', 'Delito'], 
			colModel:[ 	{name:'Caso',index:'detalle', width:125},
			           	{name:'Fecha',index:'fecha', width:125}, 
						{name:'Denunciante',index:'nombre', width:425}, 
						{name:'Delito',index:'Resumen', width:225}
			
					],
			pager: jQuery('#pager4'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			onSelectRow: function(id){
				nuevaDenuncia(id);
				},
			sortorder: "desc"
		}).navGrid('#pager4',{edit:false,add:false,del:false});	
		
		//cargo los datos del grid desde la BD
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
				{url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=RECIBIR_CANALIZACION', 
				datatype: "xml" });
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
			 
		

		// BIND events to hard-coded buttons in the NORTH toolbar
		outerLayout.addToggleBtn( "#tbarBtnHeaderZise", "north" );
		var eastSelector = "body > .ui-layout-east"; // outer-east pane
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		outerLayout.addCloseBtn("#east-closer", "east");
		createInnerLayout () ;
		$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '410px');
		$("#gview_gridDetalleFrmUno .ui-jqgrid-bdiv").css('height', '410px');
		$("#gview_gridDetalleFrmDos .ui-jqgrid-bdiv").css('height', '410px');
		$("#gview_gridSolsXAtndr .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridSolsGeneradas .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridMandamientosJudiciales .ui-jqgrid-bdiv").css('height', '400px');
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
		
		$('#test').weatherfeed(['MXDF0132']);
		//construimos el menu de las carpetas de auditoria
		generaMenuExpedientesDelArea();
		});
		//termina funcion onready


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
										$("#gridDetalleFrmUno").setGridWidth($("#mainContent").width() - 5, true);
										$("#gridDetalleFrmDos").setGridWidth($("#mainContent").width() - 5, true);
									}		
		}
	};
	

	
	function nuevaVentanita(numeroCasoNuevo,idNuevaDenuncia,ingresoDenuncia,numeroExpediente,pantallaSolicitada,numeroExpedienteId,idExpediente){
		
		//idWindowNuevaDenuncia++;
		//$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente:", type:"iframe"});
		//$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?detenido=1&numeroGeneralCaso=&abreenPenal=1&idNuevaDenuncia=&ingresoDenuncia=&numeroExpediente=&pantallaSolicitada=&idNumeroExpedienteop=&idExpedienteop=" width="1430" height="670" />');
		var pantallaSolicitada=3;
		idWindowNuevaDenuncia++;
		 var ingresoDenuncia = true;
		//alert('<%= request.getSession().getAttribute("numExpConsul")%>');
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Carpeta de investigaci\u00f3n: ", type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+numeroExpedienteId+'&pantallaSolicitada='+pantallaSolicitada+'" width="1430" height="670" />');
		$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia + " .window-maximizeButton").click();
		
		
		//$.closeWindow(miid);
	}
	
	function cierraVentanaAdjuntarDenuncia(){
		var pantalla ="iframewindowRDE";
		
		$.closeWindow(pantalla);
	}
	
	/*
	*Funcion para realizar la consulta del grid de visitadores
	*/
	function cargaGridVisitadores(idDepartamento,idEstatus)
	{ 	//alert(idDepartamento+" -- "+idEstatus);
		//$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		gIdDepartamento=idDepartamento;
		gIdEstatus=idEstatus;
		var activaExpedienteId = true;
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/busquedaCanalizadosRestaurativaStatus.do?estatus='+idEstatus+'&activaExpedienteId='+activaExpedienteId+'',datatype: "xml" });
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
		
		//limpiamos el grid de las carpetas de auditoria
		//ocultaMuestraGrids("gridVisitadores");
		//$("#gview_gridVisitadores .ui-jqgrid-bdiv").css('height', '200px');
	}
	
	function regresaGrid(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=RECIBIR_CANALIZACION',datatype: "xml" });
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
		ocultaMuestraGrids("gridDetalleFrmPrincipal");
	}


	
	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
	}

    function seleccionFila(){

		$("#1").css({ color: "#FFFFFF", background: "#FF0000" }); 
		$("#2").css({ background: "#dafafc", color: "#008000" });
	}

	function seleccionFila2(){

		$("#2").css({ color: "#FFFFFF", background: "#FF0000" }); 
		$("#1").css({ background: "#dafafc", color: "#FFA500" });
	}

	//Funcion ke genera un nuevo numero de expediente para la ui en el mismo expediente
	function nuevoNumeroExpediente(id){
			
		var idExpediente="0";
		var numeroExpediente="0";
		var numeroExpedienteId="0";
		
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/nuevoNumeroExpediente.do?idArea='+<%=Areas.UNIDAD_INVESTIGACION.ordinal()%>+'&idExpediente='+id+'',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			//alert("jhgdf"+idExpediente);
    			numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			//alert(numeroExpediente);
    			numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').text();
    			//alert(numeroExpedienteId);
    		}
    		
    	});
		
    	if(numeroExpedienteId != "0"){
    		
    		nuevaDenuncia(numeroExpedienteId);
        }
		
	}
	
    function nuevaDenuncia(id) {
    	
    	var pantallaSolicitada=3;
		idWindowNuevaDenuncia++;
		 var ingresoDenuncia = true;
		//alert('<%= request.getSession().getAttribute("numExpConsul")%>');
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Carpeta de investigaci\u00f3n: ", type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'" width="1430" height="670" />');
		$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia + " .window-maximizeButton").click();
	}
    var iframewindowAPSE = 0;
    function asigarPermisos(){
		$.newWindow({id:"iframewindowAPSE"+iframewindowAPSE, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Asignar permisos sobre Expediente: ", type:"iframe"});
		$.updateWindowContent("iframewindowAPSE"+iframewindowAPSE,'<iframe src="<%= request.getContextPath() %>/asigarPermisosExpediente.do" width="1430" height="670" />');
		$("#" +"iframewindowAPSE"+iframewindowAPSE + " .window-maximizeButton").click();
		iframewindowAPSE++;
    }
    var iframewindowRDE = 0;
    function denunciaExterna(){
		$.newWindow({id:"iframewindowRDE", statusBar: true, posx:300,posy:200,width:550,height:250,title:"Registrar denuncia externa", type:"iframe"});
		$.updateWindowContent("iframewindowRDE",'<iframe src="<%= request.getContextPath() %>/registrarDenunciaExterna.jsp " width="550" height="250" />');
		//$("#" +"iframewindowRDE"+iframewindowRDE + " .window-maximizeButton").click();
		//iframewindowRDE++;
    }
    
    function cerrarVentanaAsignarPermisos(){
    	$.closeWindow("#iframewindowAsignarPermisosSubordinados");
    }

    function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Carpeta de investigaci\u00f3n: "+num);
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

	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}
	
	function activaPrincipal() {
		$("#divGridSolicitudes").css("display", "block");
		$("#divGridSolicitudesUno").css("display", "none");
		$("#divGridSolicitudesDos").css("display", "none");
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
	}

	function activaUno() {
		$("#divGridSolicitudes").css("display", "none");
		$("#divGridSolicitudesUno").css("display", "block");
		$("#divGridSolicitudesDos").css("display", "none");
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
	}
	function activaDos() {
		$("#divGridSolicitudes").css("display", "none");
		$("#divGridSolicitudesUno").css("display", "none");
		$("#divGridSolicitudesDos").css("display", "block");
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
	}
	
	function asignarCancelarAMP()
	{
		idWindowAMP++;
		$.newWindow({id:"iframewindowAsignaCancelaAMP" + idWindowAMP, statusBar: true, posx:250,posy:150,width:350,height:320,title:"Asignar / Cancelar AMP", type:"iframe"});
		$.updateWindowContent("iframewindowAsignaCancelaAMP" + idWindowAMP,'<iframe src="<%= request.getContextPath() %>/asignarCancelarAMP.do" width="350" height="320" />');
	}
	function actualizaGrid(){$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
	activaPrincipal();

	}
	
	/*
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsXAtndr(tipoSolicitud,idArea)
	{
		jQuery("#gridSolsXAtndr").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsXAtnder.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>,<%=EstatusSolicitud.CERRADA.getValorId()%>',datatype: "xml" });
		$("#gridSolsXAtndr").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsXAtndr");
	}
	
	/*
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsGeneradas(tipoSolicitud,idArea)
	{
		jQuery("#gridSolsGeneradas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsGeneradas.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>,<%=EstatusSolicitud.CERRADA.getValorId()%>',datatype: "xml" });
		$("#gridSolsGeneradas").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsGeneradas");
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
	
	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudes(rowID){
		idWindowDetalleSolicitud++;
		$.newWindow({id:"iframewindowDetalleSolicitud"+idWindowDetalleSolicitud, statusBar: true, posx:200,posy:50,width:1100,height:450,title:"Detalle Solicitud", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud,'<iframe src="<%=request.getContextPath()%>/consultarDetalleSolicitudBandeja.do?idSolicitud=' +rowID +'&tipoUsuario=0 " width="1140" height="450" />'); 
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
	*Funcion que oculta o muestra los grids, recibe como parametro
	*el nombre del grid que va a mostrar, y todos los demas, se 
	*ocultaran
	*/ 
	function ocultaMuestraGrids(nombreGrid){
		if(nombreGrid == "gridDetalleFrmPrincipal"){
			$("#divGridSolicitudes").show();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridInformePolicial").hide();
		}
		if(nombreGrid == "gridSolsXAtndr"){
			$("#divGridSolicitudes").hide();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide();
			$("#divGridSolsXAtndr").show();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridInformePolicial").hide();
		}
		if(nombreGrid == "gridSolsGeneradas"){
			$("#divGridSolicitudes").hide();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").show();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridInformePolicial").hide();
		}
		if(nombreGrid == "gridMandamientosJudiciales"){
			$("#divGridSolicitudes").hide();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").show();
			$("#divGridAudiencias").hide();
			$("#divGridInformePolicial").hide();
		}
		if(nombreGrid == "gridAudiencias"){
			$("#divGridSolicitudes").hide();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").show();
			$("#divGridInformePolicial").hide();
		}
		
		if(nombreGrid == "informePolicial"){
			$("#divGridSolicitudes").hide();
			$("#divGridSolicitudesUno").hide();
			$("#divGridSolicitudesDos").hide;
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridInformePolicial").show();
		}
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

	//**************************************************************FUNCIONALIDAD ACORDEON ADMINISTRAR MEDIDAS CAUTELARES*********************************************/
	/**
	*Funcion que abre la ventana modal para introducir el numero de causa
	*/
	function abreModalCausa(){
		
		$("#datoCausa").val("");
		$("#divCausa").dialog("open");
	  	$("#divCausa").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Administrar medidas cautelares por n&uacute;mero de causa', 
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

	/*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarVentanaInvolucradosCausa(numeroCausa){

		idWindowVisorMedidasCautelaresPJENC++;
		$.newWindow({id:"iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC, statusBar: true, posx:251,posy:113,width:970,height:480,title:"Visor de Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC,'<iframe src="<%=request.getContextPath()%>/visorMedidaCautelar.do?numeroCausa=' + numeroCausa +'" width="970" height="480" />'); 
	}

function visorLeyesCodigos() {
		
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:253,posy:111,width:809,height:468,title:"Leyes y C&oacute;digos", type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/detalleLeyesyCodigos.do" width="800" height="500" />');
	    		
	}

	/*
	*Funcion que llama a la funcionalidad para generar un visualizador de imagen  $('#imageViewer').click(generaVisorGraficaView);
	*/
	function generaVisorGraficaView() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:66,posy:111,width:1140,height:400,title:"Visor de imagenes", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="400" />');
	    		
	}	
	
	/*
	*Funcion para generar dinamicamente el menu izquierdo para la opcion de 
	*expedientes del area
	*/
	function generaMenuExpedientesDelArea()
	{
		//limpiamos el menu de los tipos de solicitud
		$("#seccion1tree").empty();
		//$("#seccion1tree").append("<li class='closed' id='li_dptos_10'><span class='folder'>"+$(this).find('nombre').text()+"</span>"+"<ul id='li_dptosTree_"+$(this).find('jerarquiaOrganizacionalId').text()+"' class='filetree'></ul></li>");
		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/consultarEstadosPorDepartamentoDinamico.do',
    		data: 'area='+<%= Areas.UNIDAD_INVESTIGACION.ordinal()%>,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
				$(xml).find('estatus').each(function(){
					//barremos el grid para generar la cadena de IDs de los delitos normales
					$("#seccion1tree").append("<li><span class='file'><a onclick='cargaGridVisitadores("+<%= Areas.UNIDAD_INVESTIGACION.ordinal()%>+","+$(this).find('idCampo').text()+")'>"+$(this).find('valor').text()+"</a></span></li>");
    			});
    		}
    		
    	});
		
		$("#seccion1tree").treeview();
		//alert(cadenaIDs);
	}
	
	
	 function gridAudiencias()
	 {
			///Grid de Audiencias
		 jQuery("#gridAudiencias").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarAudienciasDefensor.do', 
				datatype: "xml", 
				colNames:['Caso','Car\u00e1cter','Tipo de Audiencia','Fecha de Audiencia','Sala','Defensor de Oficio'],
				colModel:[{name:'caso',	 	index:'2002', 		width:200, align:"let"},
				          {name:'caracter',	index:'2037', 	width:100, align:"center"},
				          {name:'tipo',	 	index:'2017', 	    width:120, align:"center"},
				          {name:'fechaHora',index:'2018',	width:200, align:"center"},
				          {name:'sala' ,	index:'2029', 		width:110, align:"center"},
				          {name:'defensor' ,index:'2030', 	width:150, align:"center"}
						],
				
				pager: jQuery('#pagerGridAudiencias'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					var ret = jQuery("#gridAudiencias").jqGrid('getRowData',rowid);
					caso = ret.caso;
					detalleAudiencia(rowid, caso);
				}
				
			}).navGrid('#pagerGridAudiencias',{edit:false,add:false,del:false});
				ocultaMuestraGrids('divGridAudiencias');
				jQuery("#gridAudiencias").trigger('reloadGrid');
				$("#gview_gridAudiencias .ui-jqgrid-bdiv").css('height', '450px');
	 }
	 
	 //Funcion para consultar el detalle de una Audiencia
	 function detalleAudiencia(rowId, caso) {
			$.newWindow({id:"iframewindowDetalleAudiencia", statusBar: true, posx:200,posy:50,width:1000,height:400,title:" Caso:"+caso, type:"iframe"});
		    $.updateWindowContent("iframewindowDetalleAudiencia",'<iframe src="<%= request.getContextPath() %>/detalleAudienciaDefensoria.do?idAudiencia='+rowId+'" width="1000" height="400" />');
		}
	 
	 function muestraGridAudiencias()
	 {
		 gridAudiencias();
		 ocultaMuestraGrids('gridAudiencias');
	 }
	//$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia + " .window-maximizeButton").click();		ajusta al tama�o de la pantalla 
	
	
	/******************************************************FUNCIONES PARA REMISIONES DE IPH***********************************************************************************/
	
	//variable para controlar la carga del grid de informe policial
	var primeraVezGridInformePolicial=true;
	
	/*
	*Funcion que carga el grid de consulta por fechas
	*/
	function cargaGridInformePolicial(){
		//alert("cargaGridInformePolicial");
		
		
		if(primeraVezGridInformePolicial == true){
			
			jQuery("#gridInformePolicial").jqGrid({ 
				url:'<%=request.getContextPath()%>/remisionesIPH.do', 
				datatype: "xml", 
				autowidth: true,
				colNames:['Caso','Fecha', 'Denunciante', 'Delito','Origen','Estatus'], 
				colModel:[ 	{name:'Detalle',index:'1', width:140}, 
							{name:'Fecha',index:'2', width:55}, 
							{name:'Nombre',index:'3'}, 
							{name:'Resumen',index:'4', width:80},
							{name:'Origen',index:'5', width:50},
							{name:'Estatus',index:'6'}
						],
				pager: jQuery('#pagergridInformePolicial'),
				rowNum:10,
				rowList:[10,20,30,40,50,60,70,80,90,100],
				height:420,
				sortname: 'detalle',
				viewrecords: true,
				onSelectRow: function(id){
					//muestraAcordeon();
					},
				ondblClickRow: function(id) {
					
					 nuevaDenuncia(id);
					},
				sortorder: "desc"
			}).navGrid('#pagergridInformePolicial',{edit:false,add:false,del:false});	 
				//Resize del grid
				$("#gridInformePolicial").setGridWidth($("#mainContent").width() - 5, true);
				 ocultaMuestraGrids("informePolicial");
				 
				 primeraVezGridInformePolicial=false;
		}
		else{
			jQuery("#gridInformePolicial").jqGrid('setGridParam', {url:'local',datatype: "xml" });
			$("#gridInformePolicial").trigger("reloadGrid");
			ocultaMuestraGrids("informePolicial");				  
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
<div class="ui-layout-west" >

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
		<h3><a href="#" onclick="regresaGrid()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes del &Aacute;rea</a></h3>
			<div>
				<ul id="seccion1tree" class="filetree">

				</ul>	
			</div>
		<h3 style="display: none;"><a href="#" onclick="actualizaGrid()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes Remitidos</a></h3>
			<div>
				<ul id="seccion11tree" class="filetree">
					<li class="closed" ><span id="casos-propios" class="folder" onclick="activaUno()">Por Justicia Alternativa Restaurativa</span>
						<ul>
						
						</ul>
					</li>
					<li class="closed" ><span id="casos_at_penal" class="folder" onclick="activaUno()">Por Atenci&oacute;n Temprana Penal</span>
						<ul>
						
						</ul>
					</li>
				</ul>
			</div>
			<h3 style="display: none;"><a href="#" ><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Administrar medidas cautelares</a></h3>
				<div>			
					<ul id="seccion4treePJENC" class="filetree">
						<li>
							<span class="fileCausas">
								<a id="causaMedidasCautelares" style="cursor: pointer;" onclick="abreModalCausa();">Por Causa</a>
							</span>
						</li>
					</ul>		
				</div>
			<h3 style="display: none;"><a href="#" onclick="activaUno()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes Propios</a></h3>
			<div>			
				<!-- <ul id="seccion1tree" class="filetree">
					<li class="closed" ><span id="casos_propios" class="folder">Propios</span>
						<ul>
							<li class="closed" id="casos-propios-abiertos"><span class="folder">Abiertos</span>
								<ul>
									
								</ul>
							</li>
							<li class="closed" id="casos-propios-temporales"><span class="folder">Archivo Temporal</span>
								<ul>
									
								</ul>
							</li>
							<li class="closed" id="casos-propios-definitivos"><span class="folder">Archivo Definitivo</span>
								<ul>
									
								</ul>
							</li>
							<li class="closed" id="casos-propios-judicializados"><span class="folder">Judicializados</span>
								<ul>
									
								</ul>
							</li>
							<li class="closed" id="casos-propios-cerrados"><span class="folder">Cerrados</span>
								<ul>
									
								</ul>
							</li>
						</ul>
					</li>
					<li class="closed" id="funcionarios-area"><span class="folder">Del �rea</span>
						<ul>
							<li class="closed" id="funcionarios"><span class="folder">Funcionarios</span>
								<ul>
									<li class="closed" ><span id="nombre-funcionario" class="folder">AMP (Nombre)</span>
										<ul>
											<li class="closed" id="casos-funcionario-abiertos"><span
												class="folder">Abiertos</span>
											<ul>
							
											</ul>
											</li>
											<li class="closed" id="casos-funcionario-temporales"><span
												class="folder">Archivo Temporal</span>
											<ul>
							
											</ul>
											</li>
											<li class="closed" id="casos-funcionario-definitivos"><span
												class="folder">Archivo Definitivo</span>
											<ul>
							
											</ul>
											</li>
											<li class="closed" id="casos-funcionario-judicializados"><span
												class="folder">Judicializados</span>
											<ul>
							
											</ul>
											</li>
											<li class="closed" id="casos-funcionario-cerrados"><span
												class="folder">Cerrados</span>
											<ul>
							
											</ul>
											</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="closed" id="fechas"><span class="folder">Hist&oacute;rico</span>
						<ul>
							<li class="closed" id="anio1"><span class="folder">2010</span>
								<ul>
									<li class="closed" id="abiertos-anio1"><span class="folder">Abiertos</span>
										<ul>
											
										</ul>
									</li>
									<li class="closed" id="temporales-anio1"><span class="folder">Archivo Temporal</span>
										<ul>
											
										</ul>
									</li>
									<li class="closed" id="definitivos-anio1"><span class="folder">Archivo Definitivo</span>
										<ul>
											
										</ul>
									</li>
									<li class="closed" id="judicializados-anio1"><span class="folder">Judicializados</span>
										<ul>
											
										</ul>
									</li>
									<li class="closed" id="cerrados-anio1"><span class="folder">Cerrados</span>
										<ul>
											
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>	-->	
			</div>
			<h3 style="display: none;"><a href="#" onclick="activaUno()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Expedientes por Asignar</a></h3>
			<div>
			</div>
			<h3><a href="#" onclick="muestraGridAudiencias();"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Audiencias</a></h3>
			<div>
			</div>
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes por Atender</a></h3>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer" id="tableSolsXAtender">
				</table>
			</div>
			<h3><a href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Solicitudes Generadas</a></h3>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer" id="tableSolsGeneradas">
				</table>
			</div>
			<h3 style="display: none;"><a href="#" onclick="ocultaMuestraGrids('gridMandamientosJudiciales');"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Mandamientos Judiciales</a></h3>
			<div>
				
			</div>
			<h3>
				<a id="remisionesIPH" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="" width="15" height="15">&nbsp;Remisiones con IPH</a>
			</h3>
			<div>
				<!--<ul id="seccion5tree" class="filetree"> -->
				<!--<li class="closed" id=""><span class="folder">Avisos de Auxilio</span></li> -->
				<!--</ul> -->
			</div>	
			
			<h3 >
				<a id="" href="#" onclick="generaVisorGraficaView()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png"  width="15" height="15">Graficas y Reportes</a>
			</h3>
			<div>		
				<!--	<input type="button" value="Ver Grafica" id="imageViewer" name="imageViewer"/>	
				<ul id="seccion3treePJENC" class="filetree">
					<li class="closed" id="casosPJENC"><span class="folder">Casos</span>
						 Aqui se agregan los casos y expedientes dinamicamente 
					</li>
				</ul>	-->	
			</div>
			<!--  <h3><a href="#">Solicitudes</a></h3>
			<div>			
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
						<tr>
						   <td width="100%" id="audiencia"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Audiencia</td>
						</tr>
						<tr>
						   <td width="100%" id="audiencia-general">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Audiencia General</td>
						</tr>
						<tr>
						   <td width="100%" id="audiencia-urgente">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Audiencia Urgente</td>
						</tr>
						<tr>
						   <td width="100%" id="defensor"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Defensor</td>
						</tr>
						<tr>
						   <td width="100%" id="audio-video"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Copia de Audio y Video</td>
						</tr>
						<tr>
						   <td width="100%" id="transcripcion-audiencia"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Transcripci&oacute;n de Audiencia</td>
						</tr>
						<tr>
						   <td width="100%" id="atencion-victimas"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Atenci&oacute;n a V�ctimas del Delito</td>
						</tr>
						<tr>
						   <td width="100%" id="solicitud-dictamen-pericial"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Solicitar Dictamen Pericial</td>
						</tr>
					</table>		
			</div>
			<h3><a href="#">Testimonio</a></h3>
			<div>
					
			</div>
			<h3><a href="#">Declaraciones</a></h3>
			<div>

			</div>
			<h3><a href="#">Cadena de Custodia</a></h3>
			<div>
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
						<tr>
						   <td width="100%" id="cadena"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Cadena</td>
						</tr>
						<tr>
						   <td width="100%" id="evidencia-cadena">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Ingresar Evidencia de Cadena</td>
						</tr>
						<tr>
						   <td width="100%" id="eslabon-cadena">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Registrar Eslab�n de Cadena</td>
						</tr>
						<tr>
						   <td width="100%" id="reporte-evidencia">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Reporte de Evidencia</td>
						</tr>
						<tr>
						   <td width="100%" id="objeto1">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Objeto 1</td>
						</tr>
						<tr>
						   <td width="100%" id="destino-evidencia-obj1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />>Destino Legal de Evidencia</td>
						</tr>
						<tr>
						   <td width="100%" id="almacen-obj1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Almac&eacute;n</td>
						</tr>
						<tr>
						   <td width="100%" id="objeto2">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Objeto 2</td>
						</tr>
						<tr>
						   <td width="100%" id="destino-evidencia-obj2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />>Destino Legal de Evidencia</td>
						</tr>
						<tr>
						   <td width="100%" id="almacen-obj2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Almac&eacute;n</td>
						</tr>
						<tr>
						   <td width="100%" id="asentar-registro">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Asentar Registro</td>
						</tr>
						<tr>
						   <td width="100%" id="admin-almacen-virtual">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Administrar Almac&eacute;n Virtual</td>
						</tr>
				</table>
			</div>
			<h3><a href="#">Asignar Permisos a Expediente</a></h3>
			<div>
				  <ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="folder">Solicitudes de Permiso</span>
						<ul>
							<li class="closed"><span class="file">Uno</span>
								<ul>
									<li><span class="file">&nbsp;</span></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>		
				<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
						<tr>
						</tr>
				</table>
			</div>-->
			<!-- <h3><a href="#">Presentar Recursos</a></h3>
			<div>
	
			</div>-->
			
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
			<h6><a href="#">Agenda</a></h6>
			<div>
				<center>
					<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
				</center>
					
			</div>
			<!--h3><a href="#">Clima</a></h3>
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
			<!--h3><a href="#">Chat</a></h3>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>

			</div-->
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
					    <TD width=301 align=left valign="middle" style="color:#51a651;">COORDINADOR AMP UNIDAD DE INVESTIGACION</TD>
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
		 	<!--  <li id="tbarBtnGenerar" class="first"><span></span>Generar Denuncia&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="10" height="16"></li>  --> 
			<li id="tbarBtnAsignar" class="first"><span></span>Asignar Permisos a Subordinados&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="10" height="16"--></li>
			<li id="tbarBtnDenunciaExt" class="first"><span></span>Recibir denuncia externa&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctoadjun.png" width="10" height="16"--></li>
			<!-- <li id="tbarBtnNuevo" class="first"><span></span>Enviar Mensaje de Presencia de Detenido/Aprehendido</li> -->
			
		</div>
		<div id="menu_config">
			<li onclick="buscarCaso();">Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
			<li onclick="buscarExpediente();">Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
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
			<div class=ui-layout-north>
		<!-- <div class="ui-layout-content">-->
			<div id="divGridSolicitudes">
				<table id="gridDetalleFrmPrincipal"></table>
				<div id="divGridDetalleFrmPrincipal"></div>
			</div>
				<div id="divGridSolicitudesUno" style="display: none;">
					<table id="gridDetalleFrmUno"></table>
					<div id="pager3"></div>
				</div>
				<div id="divGridSolicitudesDos" style="display: none;">
					<table id="gridDetalleFrmDos"></table>
					<div id="pager4"></div>
				</div>
				<div id="divGridSolsXAtndr" style="display: none;">
					 	<table id="gridSolsXAtndr" width="100%" height="100%"></table>
						<div id="pagerGridSolsXAtndr"></div>
					</div>
				<div id="divGridSolsGeneradas" style="display: none;">
					 	<table id="gridSolsGeneradas" width="100%" height="100%"></table>
						<div id="pagerGridSolsGeneradas"></div>
				</div>	
				
				<div id="divGridMandamientosJudiciales" style="display: none;">
					 	<table id="gridMandamientosJudiciales" width="100%" height="100%"></table>
						<div id="pagerGridMandamientosJudiciales"></div>
				</div>
				<div id="divGridAudiencias">
					<table id="gridAudiencias"></table>
					<div id="pagerAudiencias"></div>
				</div>
				<div id="divGridInformePolicial">
					<table id="gridInformePolicial"></table>
					<div id="pagergridInformePolicial"></div>
				</div>
			
		<!-- </div>-->
	</div>
		</div>
	</div>
</div>

<div id="dialog-logout" title="Logout">
	<p align="center">
		<span id="logout">&iquest;Desea cerrar su sesi&oacute;n?</span>
	</p>
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
				<td colspan="3" align="justify"><strong>Ingrese el n&uacute;mero de causa: </strong></td>
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
<script type="text/javascript">
$( "#dialog-alarm" ).dialog();
$( "#dialog-alarmPos" ).dialog();
$( "#dialog-alarm" ).dialog( "destroy" );
$( "#dialog-alarmPos" ).dialog( "destroy" );	
</script>
</html>