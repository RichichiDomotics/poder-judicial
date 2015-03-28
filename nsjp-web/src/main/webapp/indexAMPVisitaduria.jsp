<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--COMIENZA CSS DEL DOCUMENTO-->
<!--css para ventanas-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/layout_complex.css"
	media="screen" />

<!--css para el estilos de jquery-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />

<!--css para estilo de los arboles-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />

<!--estilo ultrasist-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />

<!--estilo del grid-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--COMIENZAN SCRIPTS-->

<!--jquery-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<!--para controlar las ventanas-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>

<!--para creacion de arboles-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
<script type="text/javascript">


	
	$(document).ready(function() {
		var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
		if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
		}
				
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
		//crea el arbol de audiencias
		//$("#seccion1treePJENC").treeview();
		//crea el arbol de eventos
		//$("#seccion2treePJENC").treeview();
		//crea el arbol de casos
		//$("#seccion3treePJENC").treeview();
		//agrega el evento generar documento 
		//$("#generarDocumento").click(generarDocumentoView);
		//agreaga el evento para crear la agenda
		$("#controlAgenda").click(creaAgenda);
		//Carga el arbol de casos
		
		 /*
		*Funcion que carga el grid, por default con las nuevas notificaciones 
		*/

		//div contenedor divGridDetalleResolutivaAudiencia
		

			

		 	

			

		
	
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
		createInnerLayout ();
					
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
		}
	};

	/////////////////////////////////////////////// COMIENZA FUNCIONALIDAD COMUN //////////////////////////////////////////////////////////////////
	/*
	*Funcion que abre el visor de audiencias
	*(Por el momento no acarrea el ID solo abre el visor) 
	*/
	
	/*
	*Funcion que llama a la funcionalidad para crear la agenda 
	*/
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');		
	}


	/*
	*Funcion que llama a la funcionalidad para generar un documento 
	*/
	
	function lanzaMenuAuditarExpedientes() {
			$("#divGridAuditarExpedientes").css("display","block");
			$("#divGridDetalle").css("display","none");
			$("#divGridExpedientesFuncionario").css("display","none");
			
		  jQuery("#gridAuditarExpedientes").jqGrid({ 
				url:'<%=request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['Número de Caso','Número de Expediente','Nombre Completo Imputado','Delito(s)' ], 
				colModel:[ 	{name:'Caso',index:'Caso', width:25},
				           	{name:'Expediente',index:'Expediente', width:15},
				           	{name:'Imputado',index:'Imputado', width:15},
				           	{name:'Delito',index:'Delito', width:20},
				        	
				           	
						],
				pager: jQuery('#paginadorAuditarExpedientes'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				height:600,
				sortname: 'Solicitante',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#paginadorAuditarExpedientes',{edit:false,add:false,del:false});
	}	

	

	function lanzaMenuSolicitudPermisos() {
		$.newWindow({id:"iframewindowEntradasAlmacen", statusBar: true, posx:200,posy:50,width:650,height:400,title:"Generar Solicitud de Permisos", type:"iframe"});
	    $.updateWindowContent("iframewindowEntradasAlmacen",'<iframe src="<%=request.getContextPath()%>/solicitudPermisosVisitaduria.do" width="650" height="400" />');
	    		
	}	

	
	
	function lanzaMenuSeguimientosAuditoria() {
		
		  $("#divGridAuditarExpedientes").css("display","none");
			$("#divGridDetalle").css("display","none");
			$("#divGridExpedientesFuncionario").css("display","block");
			
		  jQuery("#gridExpedientesFuncionario").jqGrid({ 
				url:'<%=request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['Número de Caso','Número de Expediente','Nombre Completo Imputado','Delito(s)' ], 
				colModel:[ 	{name:'Caso',index:'Caso', width:25},
				           	{name:'Expediente',index:'Expediente', width:15},
				           	{name:'Imputado',index:'Imputado', width:15},
				           	{name:'Delito',index:'Delito', width:20},
				        	
				           	
						],
				pager: jQuery('#paginadorAuditarExpedientes'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				height:600,
				sortname: 'Solicitante',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#paginadorAuditarExpedientes',{edit:false,add:false,del:false});
	    		
	}	
	
function paraGridGenerarReporte() {

	jQuery("#grid").jqGrid({ 
		url:'<%=request.getContextPath()%>', 
		datatype: "xml", 
		colNames:['Tipo de Almacén','Numero de Almacén','Dirección','Descripcion' ], 
		colModel:[ 	{name:'Fecha/HoraSolicitud',index:'Fecha/HoraSolicitud', width:25},
		           	{name:'TipoSolicitud',index:'TipoSolicitud', width:15},
		           	{name:'NumCaso',index:'NumCaso', width:15},
		           	{name:'Victima',index:'Victima', width:20},
		        	
		           	
				],
		pager: jQuery('#paginadorDetalle'),
		rowNum:10,
		rowList:[10,20,30],
		autowidth: true,
		autoheight:true,
		height:600,
		sortname: 'Solicitante',
		viewrecords: true,
		sortorder: "desc"
	}).navGrid('#paginadorDetalle',{edit:false,add:false,del:false});
}

	
function funcionMuestraGrids(){
	//if(){
	$("#divGridDetalle").css("display","block");
	paraGridGenerarReporte();
	//}
	}

	
</script>
</head>

<body>

<!--Comienza ui-layout-west-->
<div class="ui-layout-west">
<div class="header">&nbsp;</div>
<div class="content">
<div id="accordionmenuprincipal">
<h3><a id="menuEntradaAlmacen" href="#" onclick="lanzaMenuSolicitudPermisos()">Solicitud de Permisos</a></h3>
<div></div>
<h3><a id="menuSalidaAlmacen" href="#" onclick="lanzaMenuAuditarExpedientes()">Auditar Expediente</a></h3>
<div></div>
<h3><a id="menuExpediente" href="#" onclick="lanzaMenuSeguimientosAuditoria()">Seguimiento de Auditoria</a></h3>
<div></div>

<!--<h3 ><a id="menuSolicitud" href="#" >Solicitudes</a></h3>
				<div>			
					<ul id="seccion2treePJENC" class="filetree">
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="">No Atendidas</a></span></li>
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="">En Preceso<br></a></span></li>
					</ul>		
				</div>
				-->
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
<center><img
	src="<%=request.getContextPath()%>/resources/images/usuarioVertical.JPG" />
</center>
</div>
<!-- <h4><a href="#">Calendario</a></h4>
<div>
<center><a href="#"> <img
	src="<%=request.getContextPath()%>/resources/images/calendario.JPG"
	width="130" height="104"> </a></center>
</div>-->
<h6><a href="#">Agenda</a></h6>
<div>
<center><a href="#" id="controlAgenda"> <img
	src="<%=request.getContextPath()%>/resources/images/agenda.jpg"
	width="130" height="104"> </a></center>
<br />
</div>
<h6><a href="#" id="">Consultar Leyes y C&oacute;digos</a></h6>
<div>
<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0"
	cellpadding="0" bgcolor="#EEEEEE" bordercolorlight="#FFFFFF"
	style="cursor: pointer">
	<tr>
		<td width="100%" id="leyes"><img
			src="<%=request.getContextPath()%>/resources/css/check.png"
			width="16" height="16" />Leyes</td>
	</tr>
	<tr>
		<td id="codigos">&nbsp;<img
			src="<%=request.getContextPath()%>/resources/css/check.png"
			width="16" height="16" />Codigos</td>
	</tr>
	<tr>
		<td id="manuales">&nbsp;<img
			src="<%=request.getContextPath()%>/resources/css/check.png"
			width="16" height="16" />Manuales</td>
	</tr>
</table>
</div>
<h1><a href="#">Chat</a></h1>
<div></div>
<h1><a href="#">Clima</a></h1>
<div></div>
</div>
</div>
</div>
<!--Termina ui-layout-east-->

<!--Comienza ui-layout-north-->
<div class="ui-layout-north">
<div class="content">
<table width="100%" height="100%" border="0" cellspacing="0"
	cellpadding="0"
	background="<%=request.getContextPath()%>/resources/images/title/title13.png">
	<tr>
		<!--		          	<td width="250" >-->
		<!--		          		<img src="<%=request.getContextPath()%>/resources/images/title/poderJudicialYucatan.jpg" width="450" height="90" alt="Logo procuraduría" />-->
		<!--		          	</td>-->
		<td width="150" align="center"><img
			src="<%=request.getContextPath()%>/resources/images/title/sistemaDeJusticiaPenal.png"
			width="100" height="100" alt="Logo sistema de justicia" /></td>
		<td width="296" align="center" style="font-size: 15px;"><span></span>Almacen</td>
		<td width="296" align="center"></td>
		<td width="150" align="center"><img
			src="<%=request.getContextPath()%>/resources/images/title/sistemaDeJusticiaPenal.png"
			width="100" height="100" alt="Logo sistema de justicia" /></td>
		<td width="180">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="168" height="40" colspan="2" align="center">
				<table align="center" cellpadding="2" cellspacing="2">
					<tr>
						<td><a href="#"><img
							src="<%=request.getContextPath()%>/resources/images/btn_head_buscar.png"
							width="35" height="35" alt="Botón Buscar General" title="Buscar" /></a>
						</td>
						<td><a href="#"><img
							src="<%=request.getContextPath()%>/resources/images/btn_head_cerrarSesion.png"
							width="35" height="35" alt="Botón cerrar sesión"
							title="Cerrar Sesión" /></a></td>
						<td><a href="#"><img
							src="<%=request.getContextPath()%>/resources/images/btn_head_ayuda.png"
							width="35" height="35" alt="Botón ayuda" title="Ayuda" /></a></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr valign="top">
				<td width="128" align="center">
				<div id="liveclock"></div>
				</td>
				<td width="40"><img
					src="<%=request.getContextPath()%>/resources/images/ico_reloj_grisOx.jpg"
					width="35" height="35" alt="Icono reloj" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>

<!--comienza barra de herramientas-->
<ul class="toolbar">
	<div id="menu_head">
	<li id="tbarBtnHeaderZise" class="first"><span></span></li>
	</div>
	<div id="menu_config">
	<li id="generarDocumento"><span></span>Generar Documento</li>
	<li><span></span>Adjuntar documento</li>
	</div>
</ul>
<!--termina barra de herramientas--></div>
<!--Termina ui-layout-north-->

<!--Comienza ui-layout-south-->
<div class="ui-layout-south">
<div id="pie" align="center"
	style="background-position: center top; color: #FFFFFF; background-color: #4A5C68">
<div id="footer" style="width: 720px; padding: 5px;">
Direcci&oacute;n de la Instituci&oacute;n</div>
</div>
</div>
<!--Termina ui-layout-south-->

<!--Comienza main content-->
<div id="mainContent">
<div class="ui-layout-center">
<div class="ui-layout-content">
<div class="ui-layout-north">

<div id="divGridDetalle"  onclick="">
<table id="grid"></table>
<div id="paginadorDetalle"></div>
</div>

<div id="divGridAuditarExpedientes"  onclick="">
<table id="gridAuditarExpedientes"></table>
<div id="paginadorAuditarExpedientes"></div>
</div>

<div id="divGridExpedientesFuncionario"  onclick="">
<table id="gridExpedientesFuncionario"></table>
<div id="paginadorGridExpedientesFuncionario"></div>
</div>

</div>
</div>
</div>
</div>
<!--Termina main content-->

<div id="cambiaEstatusConfirm" style="display: none">

		<table cellspacing="0" cellpadding="0">
			<tr>
				<td >Estas Seguro de Actualizar el Estatus</td>
				
			</tr>
		</table>

	</div>
</body>
</html>

