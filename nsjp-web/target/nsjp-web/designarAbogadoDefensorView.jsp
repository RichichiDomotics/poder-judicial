<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var outerLayout, innerLayout;
	
	$(document).ready(function() {
		outerLayout = $("body").layout( layoutSettings_Outer );

		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});		
		$("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();
		muestraGadgets();
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
								url:'<%= request.getContextPath()%>/EjemploListaAbogados.xml',
								datatype: "xml", 
								colNames:['Nombre','A. Paterno', 'A. Materno'], 
								colModel:[ 	{name:'nombre',index:'nombre', width:150}, 
											{name:'apaterno',index:'apaterno', width:150}, 
											{name:'amaterno',index:'amaterno', width:150} 
											
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
			
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);

		$("#divGridAudiencia").css("display", "none");
		$("#buscarExpediente").click(buscarExpediente);	

		});

		function verExpediente(idExpediente, numeroExpediente) {
			$.newWindow({id:"iframewindowExp" + idExpediente, statusBar: true, posx:200,posy:50,width:1140,height:400,title:NúmeroExpediente, type:"iframe"});
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
		function seleccionFila(){

			$("#1").css({ color: "#FFFFFF", background: "#FF0000" }); 
			$("#2").css({ background: "#dafafc", color: "#008000" });
		}

		function seleccionFila2(){

			$("#2").css({ color: "#FFFFFF", background: "#FF0000" }); 
			$("#1").css({ background: "#dafafc", color: "#FFA500" });
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
			$("#divGridAudiencia").css("display", "none");
		}
		else
		{
			$("#divGridSolicitudes").css("display", "none");
			$("#divGridAudiencia").css("display", "block");

			grid2 ();
		}
	}


	function grid2(){

	jQuery("#gridDetalleFrmPrincipalDos").jqGrid({ 
		url:'<%= request.getContextPath()%>/EjemploEncargadoCausas.xml', 
		datatype: "xml", 
		colNames:['Caso','Fecha de Audiencia', 'Hora de Audiencia', 'Sala','Tipo de Audiencia','Probable Responsable','Delito'], 
		colModel:[ 	{name:'Detalle',index:'detalle', width:15}, 
					{name:'Fecha',index:'fecha', width:20}, 
					{name:'Hora',index:'Hora', width:50},
					{name:'Sala',index:'Sala', width:50}, 
					{name:'Audiencia',index:'Audiencia', width:50},
					{name:'Responzable',index:'Responzable', width:50},
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
	</script>	
</head>

<body>
<div class="ui-layout-west">

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
			<h3><a href="#">Casos</a></h3>
			<div>			
				<ul id="seccion1tree" class="filetree">
					<li class="closed" ><span class="folder">Casos</span>
						<ul>
							<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="exp0001">EXP 00000001</a></span></li>
									<li><span class="file"><a id="exp0002">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="expI0001">EXP 00000001</a></span></li>
									<li><span class="file"><a id="expI0002">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="expD0001">EXP 00000001</a></span></li>
									<li><span class="file"><a id="expD0002">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="expS0001">EXP 00000001</a></span></li>
									<li><span class="file"><a id="expS0002">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="expC0001">EXP 00000001</a></span></li>
									<li><span class="file"><a id="expC0002">EXP 00000002</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="closed" id="funcionarios"><span class="folder">Funcionarios</span>
						<ul>
							<li class="closed" id="caso1"><span class="folder">Defensor</span>
								<ul>
									<li class="closed" ><span class="folder">Casos</span>
						<ul>
							<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
						</ul>
					</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="closed" id="fechas"><span class="folder">Fechas</span>
						<ul>
							<li class="closed" id="caso1"><span class="folder">2000</span>
								<ul>
									<li class="closed" id="caso1"><span class="folder">Enero</span>
								<ul>
									<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Febrero</span>
								<ul>
									<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
								</ul>
							</li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">2001</span>
								<ul>
									<ul>
									<li class="closed" id="caso1"><span class="folder">Enero</span>
								<ul>
									<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Febrero</span>
								<ul>
									<li class="closed" id="caso1"><span class="folder">Asesoria Legal</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Integración</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Defensoria Técnica</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Seguimiento de Ejecución</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
							<li class="closed" id="caso1"><span class="folder">Cerrada</span>
								<ul>
									<li><span class="file"><a id="">EXP 00000001</a></span></li>
									<li><span class="file"><a id="">EXP 00000002</a></span></li>
								</ul>
							</li>
								</ul>
							</li>
								</ul>
								</ul>
							</li>
						</ul>
					</li>
					
					
				</ul>		
			</div>
			<h3><a href="#">Recibir Solicitud de Defensor</a></h3>
			<div>
				<ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="file" id="exp01">Designar Abogado Defensor</span>
						
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
					<img src="<%= request.getContextPath()%>/resources/images/usuarioVertical.JPG" />
				</center>
			</div>
			<!-- <h4><a href="#">Calendario</a></h4>
			<div>
				<center>
					<a href="#"><img src="<%= request.getContextPath()%>/resources/images/calendario.JPG" width="130" height="104"></a>
				</center>
			</div>-->
			<h6><a href="#">Agenda</a></h6>
			<div>
				<center>
					<a href="#" id="controlAgenda"><img src="<%= request.getContextPath()%>/resources/images/agenda.jpg" width="130" height="104"></a>
				</center>
				<br/>
				<ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="file" id="exp01">Consultar Eventos</span>
						
					</li>
					<li class="closed"><span class="file" id="exp01">Agendar Solicitudes</span>
						
					</li>
					<li class="closed"><span class="file" id="exp01">Enviar Respuesta Agendada</span>
						
					</li>
				</ul>	
			</div>
			<h3><a href="#">Solicitudes</a></h3>
			<div>
			<!--<ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="file" id="exp01">Agendar</span>
						
					</li>
				</ul>	
			--></div>
			<h3><a href="#">Designaciones</a></h3>
			<div>
			<ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="file" id="exp02">Abogado Defensor</span>
						
					</li>
				</ul>	
			</div>
			<h1><a href="#">Chat</a></h1>
			<div></div>
			<h1><a href="#">Clima</a></h1>
			<div></div>
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div -->
</div>


<div class="ui-layout-north">
	<div class="content">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/resources/images/title/title13	.png">
			<tr>
            	
				<td width="150" align="center"><img src="<%=request.getContextPath()%>/resources/images/title/sistemaDeJusticiaPenal.png" width="100" height="100" alt="Logo sistema de justicia" /></td>        
	            <td width="150" align="center"></td>
	            <td width="150" align="center"></td>
	            <td width="296" align="center">&nbsp;</td>
				<!--<td width="150" align="center"></td>-->
	            <td width="180" >
					<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	                	<tr>
	                 		<td width="168" height="40" colspan="2" align="center">
								<table align="center" cellpadding="2" cellspacing="2">
									<tr>
										<td><a href="#"><img src="<%=request.getContextPath()%>/resources/images/btn_head_buscar.png" width="35" height="35" alt="Botón Buscar General" title="Buscar" /></a></td>
										<td><a href="#"><img src="<%=request.getContextPath()%>/resources/images/btn_head_cerrarSesion.png" width="35" height="35" alt="Botón cerrar sesión" title="Cerrar Sesión"/></a></td>
										<td><a href="#"><img src="<%=request.getContextPath()%>/resources/images/btn_head_ayuda.png" width="35" height="35" alt="Botón ayuda" title="Ayuda"/></a></td>
									</tr>
								</table>
							</td>
	                  	</tr>
						<tr valign="top">
							<td  width="128" align="center">
								<div id="liveclock" >
							</td>
							<td width="40">
								<img src="<%=request.getContextPath()%>/resources/images/ico_reloj_grisOx.jpg" width="35" height="35" alt="Icono reloj" />
							</td>
						</tr>
					</table>            
				</td>
			</tr>
		</table>
	</div>
	<ul class="toolbar">
		<div id="menu_head">
			<li id="tbarBtnHeaderZise" class="first"><span></span></li>
			<li id="tbarBtnSolicitudes" class="first" onmousedown="ocultaMuestraGridsAlertas('1');">Bandeja de Casos</li>
			<li id="tbarBtnAgendaAudiencias" onmousedown="ocultaMuestraGridsAlertas('2');">Bandeja de Audiencias</li>
			<li id="tbarBtnImpresoras">Número de Expediente</li>	
			<li id="tbarBtnImpresoras">Información del Caso</li>			
		</div>
		<div id="menu_config">
			<li id="buscarCaso">Buscar Caso</li>
			<li id="buscarExpediente">Buscar Expediente</li>
			<li id="generarDocumento">Generar Documento</li>
			<li>Imprimir</li>
			<li>Guardar</li>
			<li>Digitalizar Documento</li>
			<li>Turnar Documento</li>
			<li class="last">config02</li>
		</div>
	</ul>
</div>

<div id="mainContent">
	<div class="ui-layout-center">
		 <div class="ui-layout-content">
		
		</div>
	</div>
	<div class="ui-layout-north">
		<div id="divGridSolicitudes">
			<table id="gridDetalleFrmPrincipal"></table>
			<div id="pager1"></div>
		</div>
		<div id="divGridAudiencia">
			<table id="gridDetalleFrmPrincipalDos"></table>
			<div id="pagerDos1"></div>
		</div>
	</div>
</div>
<div class="ui-layout-south">
	<div id="pie" align="center" style="background-color ;background-position:center top; color: #FFFFFF; background-color: #4A5C68">
		<div id="footer" style="width: 720px;  padding: 5px;" >
			Direcci&oacute;n de la Instituci&oacute;n
		</div> 
	</div>
</div>

</body>
</html>