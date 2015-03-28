<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
								url:'<%= request.getContextPath()%>/BusquedaInicialCasoGrid.do', 
								datatype: "xml", 
								colNames:['Caso','Fecha', 'Presunto responsable', 'Delito'], 
								colModel:[ 	{name:'Detalle',index:'detalle', width:50}, 
											{name:'Fecha',index:'fecha', width:20}, 
											{name:'Nombre',index:'nombre', width:60}, 
											{name:'Resumen',index:'Resumen', width:100}
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
			$("#buscarCaso").click(buscarCaso);	
			$("#buscarExpediente").click(buscarExpediente);
			$('#casos li').click(agregaExpediente);		
			$("#tbarBtnNuvaDenuncia").click(nuevaDenuncia);	
			//$('#casos1 li').click(justiciaRestaurativa);	
			$("#generarDocumento").click(generarDocumentoViwe);
			//$("#entrevista").click(generaCapturaEntrevista);
				
		
		//agregamos el click para redireccionar a la valoracion de hechos
		$("#hrefHechos").click(realizarValoracionHechos);	

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
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:900,height:620,title:"Generar Documento", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumento.do" width="900" height="620" />');
		    		
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
					<li class="closed" id="casos"><span class="folder">Casos</span>
						<!--ul>
							<li class="closed" id="caso1"><span class="folder">CASO 1</span>
								< ul>
									<li><span class="file"><a id="exp01">EXP 00000001</a></span></li>
									<li><span class="file"><a id="exp02">EXP 00000002</a></span></li>
								</ul>
							</li>
						</ul -->
					</li>
				</ul>		
			</div>
			<h3><a href="#">Denuncia</a></h3>
			<div>
				<ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="folder">&nbsp;</span>
						<ul>
							<li class="closed"><span class="folder">&nbsp;</span>
								<ul>
									<li><span class="file">&nbsp;</span></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>		
			</div>
			<h3><a href="#">Actividades</a></h3>
			<div>
				<ul id="seccion3tree" class="filetree">
					<li class="closed"><span class="folder">&nbsp;</span>
						<ul>
							<li class="closed"><span class="folder">&nbsp;</span>
								<ul>
									<li><span class="file">&nbsp;</span></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>		
			</div>
			<h3><a href="#">Busqueda Legal</a></h3>
			<div>
				<ul id="seccion4tree" class="filetree">
					<li class="closed"><span class="folder">&nbsp;</span>
						<ul>
							<li class="closed"><span class="folder">&nbsp;</span>
								<ul>
									<li><span class="file">&nbsp;</span></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>		
			</div>
			<h3><a href="#">Buscar Personas</a></h3>
			<div>
				&nbsp;
			</div>
			<h3><a href="#">Solicitudes Periciales</a></h3>
			<div>
				&nbsp;
			</div>
			<!-- <h3><a href="#" id="jRestaura">Justicia restaurativa </a></h3>
			<div>
				<ul id="seccion5tree" class="filetree">
					<li class="closed" id="jRcaso"><span class="folder">Alternativa</span>
						<ul>
							<li class="closed" id="alte1"><span class="folder">Preliminares</span>
								ul>
									<li><span class="file"><a id="exp01">EXP 00000001</a></span></li>
									<li><span class="file"><a id="exp02">EXP 00000002</a></span></li>
								</ul
							</li>
						</ul>
					</li>
					
					<li class="closed" id="entrevista"><span class="folder">Aplicar Justicia</span></li>
					
				</ul>		
			</div>
			 -->
			<h3><a href="#" id="hrefHechos">Hechos</a></h3>
			<div>
				&nbsp;
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
			</div>
			<h3><a href="#">Solicitudes</a></h3>
			<div></div>
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
			<li id="tbarBtnNuevo" class="first"><span></span>Nuevo</li>
			<li id="tbarBtnLectura"><span></span>Lectura</li>
			<li id="tbarBtnImpresoras"><span></span>Impresoras</li>
			<li id="tbarBtnNuvaDenuncia"><span></span>Nueva Denuncia</li>
			<li id="buscarExpediente" ><span></span>Buscar Expediente</li>
			<li id="buscarCaso" ><span></span>Buscar Caso</li>
			<li id="generarDocumento">Generar Documento</li>
		</div>
		<div id="menu_config">
			<li><a href="#">config01</a></li>
			<li><a href="#">config02</a></li>
			<li><a href="#">config03</a></li>
			<li class="last"><a href="#">config04</a></li>
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
			<div id="tabsprincipalconsulta">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Datos Generales</a></li>
					<li><a href="#tabsconsultaprincipal-2">Domicilio</a></li>
					<li><a href="#tabsconsultaprincipal-3">Medios de Contacto</a></li>
					<li><a href="#tabsconsultaprincipal-4">Documento</a></li>
					<li><a href="#tabsconsultaprincipal-5">Servidor Publico</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table border="0" width="100%">
						<tr>
							<td align="right">Apellido Paterno:</td>
							<td>
								<input type="text" size="50" maxlength="50" id="datosGeneralesCmpApaterno" />
							</td>
							<td align="right" rowspan="2">Alias:</td>
							<td rowspan="2">
								<select id="datosGeneralesCmpAlias"  multiple="multiple" ></select>
							</td>
						</tr>
						<tr>
							<td align="right">Apellido Materno:</td>
							<td>
								<input type="text" size="50" maxlength="50" id="datosGeneralesCmpMaterno" />
							</td>
						</tr>
						<tr>
							<td align="right">Nombre(s):</td>
							<td>
								<input type="text" size="50" maxlength="50" id="datosGeneralesCmpNombres" />
							</td>
						</tr>
						<tr>
							<td align="right">CURP:</td>
							<td>
								<input type="text" size="50" maxlength="18" id="datosGeneralesCmpCurp"/>
							</td>
							<td rowspan="2" valign="top" align="right">Ocupaci&oacute;n:</td>
							<td rowspan="2">
								<select id="datosGeneralesCmpOcupacion" size="3"></select>
							</td>
						</tr>
						<tr>
							<td align="right">RFC:</td>
							<td>
								<input type="text" size="50" maxlength="13" id="datosGeneralesCmpRfc""/>
							</td>
						</tr>
						<tr>
							<td align="right">Fecha de Registro:</td>
							<td>
								<input type="text" size="50" maxlength="50" id="datosGeneralesCmpFechaIngreso" disabled="disabled"/>
							</td>
							<td rowspan="2" align="right" valign="top">Nacionalidad:</td>
							<td rowspan="2">
								<select id="datosGeneralesCmpNacionalidad" size="3"></select>
							</td>
						</tr>
						<tr>
							<td align="right">Alias:</td>
							<td>
								<input type="text" size="20" maxlength="50" id="newItem"/>
								<input type="button" id="add" value="Agrega Alias" class="btn_Generico"/>
							</td>
						</tr>
						<tr>
							<td align="right">Idioma o Lengua:</td>
							<td>
								<select id="datosGeneralesCmpIdioma">
									<option value="Seleccione">- Seleccione -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">Religion:</td>
							<td>
								<select id="datosGeneralesCmpReligion">
									<option value="">- Seleccione -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">Escolaridad:</td>
							<td>
								<select id="datosGeneralesCmpEscoralidad">
									<option value="">- Seleccione -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">Estado Civil:</td>
							<td>
								<select id="datosGeneralesCmpEstadoCivil">
									<option value="">- Seleccione -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">Sexo:</td>
							<td>
								Masculino <input type="radio" id="datosGeneralesCmpSexo">
								&nbsp;
								&nbsp;
								&nbsp;
								Femenino <input type="radio" id="datosGeneralesCmpSexo">
							</td>
						</tr>
					</table>					
				</div>
				<div id="tabsconsultaprincipal-2">
					<table width="100%" border="0" cellspacing="1" cellpadding="1" id=ingresarDomicilio>
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="4" align="center">
											<basefont size="3">INGRESAR DOMICILIO
										</td>
									</tr>
									<tr>
										<td>Pais</td>
										<td>
											<select name="cbxPais" id="cbxPais">						
												<option value="-1">-Seleccione-</option>
											</select>
										</td>

										<td>Calle o Avenida</td>
										<td><label for="areaCalle"></label> <input
											type="text" name="areaCalle" id="areaCalle" /></td>
									</tr>
									<tr>
										<td>Código Postal</td>
										<td>
											<input type="text" name="codigoPostal" id="codigoPostal" size="5" maxlength="5" />
											<input type="button" value="Enviar" id="codigoPostalButton" class="btn_Generico"/>
										</td>
										<td>Numero exterior</td>
										<td>
											<label for="areaNumeroExterior"></label> 
											<input type="text" name="areaNumeroExterior" id="areaNumeroExterior" />
										</td>
									</tr>
									<tr>
										<td>Entidad Federativa</td>
										<td>
											<select name="cbxEntFederativa" id="cbxEntFederativa">
												<option value="-1">-Seleccione-</option>
											</select>
											<input value="" size="20" maxlength="10" type="text" name="entidadFederativa" id="entidadFederativa" />
										</td>

										<td>Numero Interior</td>
										<td><input type="text" name="areaNumeroInterior" id="textfield" /></td>
									</tr>
									<tr>
										<td>Ciudad</td>
										<td>
											<select name="cbxCiudad" id="cbxCiudad">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaCiudad" />
										</td>
										<td>Referencias</td>
										<td>
											<label for="areaReferencias"></label> 
											<input type="text" name="areaReferencias" id="areaReferencias" /></td>
									</tr>
									<tr>
										<td>Delegación/Municipio</td>
										<td>
											<select	name="cbxDelegacionMunicipio" id="cbxDelegacionMunicipio">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaDelegacionMunicipio" name="areaDelegacionMunicipio"/>					
										</td>
										<td>Entre calle</td>
										<td>
											<label for="areaEntreCalle"></label> 
											<input type="text" name="areaEntreCalle" id="areaEntreCalle" />
										</td>
									</tr>
									<tr>
										<td>Asentamiento o Colonia</td>
										<td>
											<select name="cbxAsentamientoColonia" id="cbxAsentamientoColonia">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaColonia" />
										</td>
										<td>y calle</td>
										<td>
											<label for="areaYCalle"></label> 
											<input type="text" name="areaYCalle" id="areaYCalle" />
										</td>
									</tr>
									<tr>
										<td>Tipo de Asentamiento</td>
										<td>
											<select name="cbxTipoAsentamiento" id="cbxTipoAsentamiento">
											<option value="-1">-Seleccione-</option>						
											</select>
											<input type="text" id="areaAsentamiento" />
										</td>
										<td>Alias</td>
										<td>
											<label for="areaAlias"></label> 
											<input type="text" name="areaAlias" id="areaAlias" />
										</td>
									</tr>
									<tr>
										<td>Tipo de Calle</td>
										<td>
											<select name="cbxTipoCalle" id="cbxTipoCalle">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaTipoCalle" />
										</td>

										<td>Edificio</td>
										<td>
											<label for="areaEdificio"></label> 
											<input type="text" name="areaEdificio" id="areaEdificio" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>Coordenadas Geográficas</td>
										<td>
											<label for="areaCoordenadas"></label> 
											<input type="text" name="areaCoordenadas" id="areaCoordenadas" />
										</td>
									</tr>
									<tr>
										<td>Mismo domicilio para notificaciones?</td>
										<td>
											<input type="radio" name="radio" id="DomicilioNotificacionesSi" value="DomicilioNotificacionesSi" checked="checked"/>
											<label for="DomicilioNotificacionesSi">Si</label>
											<input type="radio" name="radio" id="DomicilioNotificacionesNo" value="domicilioNotificacionesNo"/> 
											<label for="domicilioNotificacionesNo">No</label>
										</td>
										<td>Limpiar Formulario</td>
										<td><input type="button" value="Limpiar" id="limpiarButton" class="btn_Generico"/></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>

						<!-- TABLA PARA DOMICILIO DE NOTIFICACIONES -->
						<table width="100%" border="0" cellspacing="1" cellpadding="1" id="tablaNotificaciones">
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="4" align="center">
											<basefont size="3">DOMICILIO PARA NOTIFICACIONES
										</td>
									</tr>
									<tr>
										<td>Pais</td>
										<td>
											<select name="cbxPaisNotif" id="cbxPaisNotif">						
												<option value="-1">-Seleccione-</option>
											</select>
										</td>

										<td>Calle o Avenida</td>
										<td>
											<input type="text" name="areaCalleNotif" id="areaCalleNotif" />
										</td>
									</tr>
									<tr>
										<td>Código Postal</td>
										<td> 
											<input type="text" name="codigoPostalNotif" id="codigoPostalNotif" maxlength="5" size="5"/>
											<input type="button" value="Enviar" id="codigoPostalButtonNotif" class="btn_Generico"/>
										</td>
										<td>Numero exterior</td>
										<td> 
											<input type="text" name="areaNumeroExteriorNotif" id="areaNumeroExteriorNotif" />
										</td>
									</tr>
									<tr>
										<td>Entidad Federativa</td>
										<td>
											<select name="cbxEntFederativaNotif" id="cbxEntFederativaNotif">
												<option value="-1">-Seleccione-</option>
											</select>
											<input value="" size="20" maxlength="10" type="text" name="entidadFederativaNotif" id="entidadFederativaNotif" />
										</td>

										<td>Numero Interior</td>
										<td>
											<input type="text" name="areaNumeroInteriorNotif" id="areaNumeroInteriorNotif" />
										</td>
									</tr>
									<tr>
										<td>Ciudad</td>
										<td>
											<select name="cbxCiudadNotif" id="cbxCiudadNotif">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaCiudadNotif" />
										</td>
										<td>Referencias</td>
										<td> 
											<input type="text" name="areaReferenciasNotif" id="areaReferenciasNotif" />
										</td>
									</tr>
									<tr>
										<td>Delegación/Municipio</td>
										<td>
											<select	name="cbxDelegacionMunicipioNotif" id="cbxDelegacionMunicipioNotif">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaDelegacionMunicipioNotif" name="areaDelegacionMunicipioNotif"/>					
										</td>
										<td>Entre calle</td>
										<td> 
											<input type="text" name="areaEntreCalleNotif" id="areaEntreCalleNotif" />
										</td>
									</tr>
									<tr>
										<td>Asentamiento o Colonia</td>
										<td>
											<select name="cbxAsentamientoColoniaNotif" id="cbxAsentamientoColoniaNotif">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaColoniaNotif" />
										</td>
										<td>y calle</td>
										<td> 
											<input type="text" name="areaYCalleNotif" id="areaYCalleNotif" />
										</td>
									</tr>
									<tr>
										<td>Tipo de Asentamiento</td>
										<td>
											<select name="cbxTipoAsentamientoNotif" id="cbxTipoAsentamientoNotif">
											<option value="-1">-Seleccione-</option>						
											</select>
											<input type="text" id="areaAsentamientoNotif" />
										</td>
										<td>Alias</td>
										<td> 
											<input type="text" name="areaAliasNotif" id="areaAliasNotif" />
										</td>
									</tr>
									<tr>
										<td>Tipo de Calle</td>
										<td>
											<select name="cbxTipoCalleNotif" id="cbxTipoCalleNotif">
												<option value="-1">-Seleccione-</option>
											</select>
											<input type="text" id="areaTipoCalleNotif" />
										</td>

										<td>Edificio</td>
										<td>
											<input type="text" name="areaEdificioNotif" id="areaEdificioNotif" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>Coordenadas Geográficas</td>
										<td> 
											<input type="text" name="areaCoordenadasNotif" id="areaCoordenadasNotif" />
										</td>
									</tr>
									<tr>
										
									</tr>
								</table>
								</td>
							</tr>
						</table>
				</div>
				<div id="tabsconsultaprincipal-3">
					&nbsp;
				</div>
				<div id="tabsconsultaprincipal-4">
					&nbsp;
				</div>
				<div id="tabsconsultaprincipal-5">
					&nbsp;
				</div>
			</div>
		</div>
	</div>
	<div class="ui-layout-north">
		<table id="gridDetalleFrmPrincipal"></table>
		<div id="pager1"></div>
	</div>
</div>

</body>
</html>
