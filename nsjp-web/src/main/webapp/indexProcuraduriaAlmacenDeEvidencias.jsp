<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	var outerLayout, innerLayout;
	
	$(document).ready(function() {
		
		$("#atender").css({ color: "#1C94C4"});
		$("#atendidos").css({ color: "#1C94C4"});
		
		$("#registrar-almacen").css({ color: "#1C94C4"});
		$("#seleccion-almacen").css({ color: "#1C94C4"});
		$("#registro-almacen").css({ color: "#1C94C4"});
		$("#modif-estado-almacen").css({ color: "#1C94C4"});
		$("#estado-almacen").css({ color: "#1C94C4"});
		$("#ubicarEvidencia").css({ color: "#1C94C4"});
		
		$("#resguardo_evidencia").css({ color: "#1C94C4"});
		$("#atender_resguardo_evidencia").css({ color: "#1C94C4"});
		$("#atendidos_resguardo_evidencia").css({ color: "#1C94C4"});
		$("#entrega_evidencia").css({ color: "#1C94C4"});
		$("#atender_entrega_evidencia").css({ color: "#1C94C4"});
		$("#atendidos_entrega_evidencia").css({ color: "#1C94C4"});
		
		
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		
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
		
		muestraGadgets();
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%= request.getContextPath()%>/AlmacenEvidenciasOrdenes.xml', 
			datatype: "xml", 
			colNames:['Fecha de Solicitud','Evidencia', 'Nombre del Solicitante'], 
			colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
						{name:'Fecha',index:'fecha', width:20}, 
						{name:'Nombre',index:'nombre', width:50}
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
		cargaCasos();
		$( "#tabsprincipalconsulta" ).tabs();
			
			$("#exp01").click(creaExpediente01);
			$("#exp02").click(creaExpediente02);			
			$("#controlAgenda").click(creaAgenda);
				
			//$("#buscarExpediente").click(buscarExpediente);
			$('#casos li').click(agregaExpediente);		
			//$("#buscarCaso").click(buscarCaso);	
			//$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);	
			//$('#casos1 li').click(justiciaRestaurativa);	
			$("#generarDocumento").click(generarDocumentoViwe);
			//$("#entrevista").click(generaCapturaEntrevista);
			$("#ubicarEvidencia").click(cadenaDeCustodiaEvidencia);
				
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);	
		
		$("#resguardo_temporal").click(mostrarResguardoTemporal);
		$("#ordenes").click(mostrarOrdenes);
		});
		function mostrarOrdenes(){
		muestraGrid('1');
		}


		function mostrarResguardoTemporal(){
				muestraGrid('2');
		}
		
		function verExpediente(idExpediente, numeroExpediente) {
			$.newWindow({id:"iframewindowExp" + idExpediente, statusBar: true, posx:200,posy:50,width:1140,height:400,title:numeroExpediente, type:"iframe"});
		    $.updateWindowContent("iframewindowExp" + idExpediente,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do" width="1140" height="400" />');			
		}
				
//		function buscarExpediente() {
//			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
//	    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
//		}

//		function buscarCaso() {
//			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
//	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
//		}
		
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

//		function nuevaDenuncia() {
//			$.newWindow({id:"iframewindowNuevaDenuncia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Nueva Denuncia", type:"iframe"});
//		    $.updateWindowContent("iframewindowNuevaDenuncia",'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?idNuevaDenuncia=1" width="1140" height="400" />');		
//		}
		
		 
		function justiciaRestaurativa() {
			$.newWindow({id:"iframewindowjusticia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Justicia Restaurativa", type:"iframe"});
		    $.updateWindowContent("iframewindowjusticia",'<iframe src="<%= request.getContextPath() %>/JusticiaRestaurativa.do" width="1140" height="400" />');		
		}

		function generarDocumentoViwe() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="1140" height="400" />');	    		
		}

		function cadenaDeCustodiaEvidencia() {
			$.newWindow({id:"iframewindowCadenaDeCustodiaEvidencia", statusBar: true, posx:200,posy:50,width:420,height:270,title:"Cadena de custodia evidencia", type:"iframe"});
		    $.updateWindowContent("iframewindowCadenaDeCustodiaEvidencia",'<iframe src="<%= request.getContextPath() %>/generarCadenaCustodiaEvidencia.do" width="420" height="270" />');	    		
		}
		
		
		function ejecutaChat() {
			$("#dialogoChat").dialog( "open" );
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
	
	function muestraGrid(idGrid)
	{
		if(parseInt(idGrid)==1)
		{
			$("#divOrden").css("display", "block");
			$("#divResguardoTemp").css("display", "none");
		}
		else{
			
			if(parseInt(idGrid)==2)
			{
				$("#divOrden").css("display", "none");
				$("#divResguardoTemp").css("display", "block");
				resguardoTemp ();
			}
		}		
	}


	function resguardoTemp(){

		jQuery("#gridDetalleFrmPrincipalDos").jqGrid({ 
			url:'<%= request.getContextPath()%>/AlmacenEvidencias.xml', 
			datatype: "xml", 
			colNames:['Fecha', 'Tipo de Evidencia','Nombre del Responsable'], 
			colModel:[	{name:'Fecha',index:'fecha', width:50},
						{name:'Evidencia',index:'Evidencia', width:50},
						{name:'Responsable',index:'Responsable', width:50}
						
					],
			pager: jQuery('#pagerDos1'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
				ondblClickRow: function(rowid) {
					dblClickRowBandejaResguardoTemporal(rowid);
					}

		}).navGrid('#pagerDos1',{edit:false,add:false,del:false});	
		$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '410px');
		 $("#gview_gridDetalleFrmPrincipalDos .ui-jqgrid-bdiv").css('height', '410px');
	
	
		function dblClickRowBandejaResguardoTemporal(rowID)
		{
			$.newWindow({id:"iframewindowVisorAlmacen", statusBar: true, posx:200,posy:50,width:900,height:400,title:"Almacén ", type:"iframe"});
		    $.updateWindowContent("iframewindowVisorAlmacen",'<iframe src="<%=request.getContextPath()%>/visorAlmacen.do" width="1140" height="400" />'); 
		}
	}
	</script>	
</head>

<body>
<div class="ui-layout-west">

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
		
			<h3><a href="#" id="ordenes">Ordenes</a></h3>
			<div>			
				<ul id="seccion1tree" class="filetree">
				<li class="closed" ><span class="folder" id="resguardo_evidencia" onclick="">Resguardo de Evidencia</span>
						<ul>
							<li class="closed" id="atender_resguardo_evidencia"><span class="folder">Por Atender</span></li>
					
							<li class="closed" id="atendidos_resguardo_evidencia"><span class="folder">Atendidas</span></li>
						</ul>
				</li>
				<li class="closed" ><span class="folder" id="entrega_evidencia" onclick="">Entrega de Evidencia</span>
						<ul>
							<li class="closed" id="atender_entrega_evidencia"><span class="folder">Por Atender</span></li>
					
							<li class="closed" id="atendidos_entrega_evidencia"><span class="folder">Atendidas</span></li>
						</ul>
				</li>
				</ul>	
			</div>
			
			<h3><a href="#" id="resguardo_temporal">Resguardo Temporal</a></h3>
				<div>			
					<ul id="seccion2tree" class="filetree">
						<li class="closed" id="atender"><span class="folder">Por Atender</span></li>
				
						<li class="closed" id="atendidos"><span class="folder">Atendidas</span></li>
					</ul>	
				</div>
				
			<h3><a href="#">Reporte de almac&eacute;n</a></h3>
			<div>
				&nbsp;
			</div>
			<h3><a href="#">Gestionar almac&eacute;n de evidencias</a></h3>
			<div>
				<ul id="seccion3tree" class="filetree">
					<li class="closed"><span id="registrar-almacen" class="folder">Registrar almac&eacute;n</span>
						<ul>
							<li><span id="registro-almacen" class="file">Seleccionar almac&eacute;n</span></li>
							<li><span id="modif-estado-almacen" class="file">Cambiar estado de almac&eacute;n</span></li>
							<li><span id="estado-almacen" class="file">Modificar almac&eacute;n</span></li>
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
			<h4><a href="#">Datos Personales</a></h4>
			<div>			
					<center>
						<img src="<%= request.getContextPath()%>/resources/images/mono.png" />
					</center>
			</div>
			<!-- <h4><a href="#">Calendario</a></h4>
			<div>
					<center>
						<a href="#"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"></a>
					</center>
			</div>-->
			<h1><a href="#">Clima</a></h1>
			<div>
					<center>
						<a href="#" id="controlAgenda"><img	src="<%=request.getContextPath()%>/resources/images/img_clima.png"  width="201" height="178"></a>
					</center>
			</div>
			<h3><a href="#">Solicitudes</a></h3>
			<div></div>
			<h1><a href="#">Chat</a></h1>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>

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
					    <TD width=401 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_almaeviden.png" width="377" height="79"></TD>
					    <TD width=126 align=left valign="top"><SPAN></SPAN></TD>
					    <TD width=272 align=center valign="top">&nbsp;</TD>
					    <TD width=28 align=middle>&nbsp;</TD><!--<td width="150" align="center"></td>-->
					    <TD width=380 align="right" valign="middle">
					      <table width="362" border="0" cellspacing="0" cellpadding="0">
					        <tr>
					          <td width="165"><table width="141" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					              <td width="107" align="right" class="txt_menu_top">Cerrar sesi&oacute;n</td>
					              <td width="42"><a href="#"><img src="<%=request.getContextPath()%>/resources/images/btn_cerrar.png" width="29" height="26" border="0"></a></td>
					            </tr>
					          </table></td>
					          <td width="103"><table width="89" border="0" cellspacing="0" cellpadding="0">
					            <tr>
					              <td width="53" class="txt_menu_top">Buscar</td>
					              <td width="147"><img src="<%=request.getContextPath()%>/resources/images/icn_buscar.png" width="22" height="23" border="0"></td>
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
<!--			<li id="tbarBtnNuevo" class="first"><span></span>Nuevo</li>-->
<!--			<li id="tbarBtnLectura"><span></span>Lectura</li>-->
<!--			<li id="tbarBtnImpresoras"><span></span>Impresoras</li>-->
			<!-- <li id="tbarBtnNuvaDenuncia"><span></span>Nueva Denuncia</li>-->
<!--			<li id="tbarBtnScanner"><span></span>Scanner</li>-->
			<!--<li id="buscarExpediente" ><span></span>Buscar Expediente</li>-->
<!--			<li id="tbarBtnConfigurar" ><span></span>Configurar</li>-->
			<!--<li id="buscarCaso" ><span></span>Buscar Caso</li>-->
			<!--<li id="generarDocumento">Generar Documento</li>-->
			<li id="tbarBtnAyuda">Ayuda</li>
		</div>
		<div id="menu_config">
			<li>Agregar Evidencia</li>
			<!--<li>config01</li>
			<li>config02</li>
			<li>config03</li>
			<li>config04</li>-->
			<li id="verde">Configuraci&oacute;n&nbsp;<img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"></li>
<!--			<li class="last"><a href="#">config04</a></li>-->
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
				 <div id="divOrden">
					<table id="gridDetalleFrmPrincipal"></table>
					<div id="pager1"></div>
				</div>
				<div id="divResguardoTemp">
					<table id="gridDetalleFrmPrincipalDos"></table>
					<div id="pagerDos1"></div>
				</div>
			</div>
		</div>
		<div class="ui-layout-north">
			
		</div>
	</div>
</body>
</html>
