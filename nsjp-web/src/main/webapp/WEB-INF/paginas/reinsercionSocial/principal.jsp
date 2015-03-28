<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script>
	$(document).ready(
			function() {
				//Variable para controlar la carga del grid de expedientes
				var cargaPrimeraExpPorEstatus = true;

				/*
				 *Funcion que consulta los expedientes de acuerdo a su estatus y la fecha inicial y final
				 */

				function cargagridRS() {
					if (cargaPrimeraExpPorEstatus == true) {

						jQuery("#gridRS").jqGrid(
								{
									url : 'local',
									datatype : "xml",
									colNames : [ 'NUC', 'Nombre', 'Apellido Paterno', 'Apellido Materno', 'CERESO' ],
									colModel : [ {name : 'NUC',	index : '1',width : 70}, 
												 {name : 'Nombre', index : '2',	width : 110}, 
												 {name : 'Apellido Paterno', index : '3', width : 110}, 
												 {name : 'Apellido Materno', index : '4', width : 110},
												 {name : 'CERESO', index : '5', width : 110}, ],
									pager : jQuery('#pagerGridRS'),
									rowNum : 10,
									rowList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
									autowidth : true,
									height : 360,
									sortname : 'detalle',
									caption : "Resultados de la Búsqueda",
									viewrecords : true,
									onSelectRow : function(id) {
									},
									ondblClickRow : function(id) {
										//creaVentanaExpediente(id);
										limpiarDetalles();
										consultarCasos(id);
									},
									sortorder : "desc"
								}).navGrid('#pagergridRS', {
							edit : false,
							add : false,
							del : false
						});
					} else {
						jQuery("#gridRS").jqGrid('setGridParam', {
							url : 'local',
							datatype : "xml"
						});
						$("#gridRS").trigger("reloadGrid");
					}
				}
				
				outerLayout = $("body").layout( layoutSettings_Outer );
				
				outerLayout.addToggleBtn( "#tbarBtnHeaderZise", "north" );
				outerLayout.addPinBtn("#east-pin", "east" );
				outerLayout.addCloseBtn("#east-closer", "east");
				createInnerLayout ();		
				
				
				
				cargagridRS();
				
				$("#accordionDetalles").accordion(
					{  
						autoHeight: false  
					}
				);
				
			}
	);
		
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
/*	,	south: {
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
		,	west__onresize:		function () { 
												// FUNCTION 
												$("#gridRS").setGridWidth($("#mainContent").width() - 5, true);
											}
		}
*/
	,	east: {
			size:					($(document).width()/3)
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
		,	est__onresize:		function () { 
												//function 
												$("#gridRS").setGridWidth($("#mainContent").width() - 5, true);
												$("#accordionDetalles").accordion("resize"); 
											}		
		}
	,	center: {
			paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
		,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes	
		,	minWidth:				200
		,	minHeight:				200
		,	onresize_end:			function () { 
										$("#gridRS").setGridWidth($("#mainContent").width() - 5, true);
										$("#accordionDetalles").accordion("resize"); 
									}
		}
	};





/*************************************************************************************************/

function buscar(){
		//Solo para pruebas
		
		limpiarDetalles();
		$("#gridRS").empty();
		
		var mydataCERESO = [ {
			id : "PXNDX1",
			NUC : "PXNDX1",
			Nombre : "Eduardo",
			'Apellido Paterno' : "Alvarado",
			'Apellido Materno' : "Díaz",
			CERESO : "CERESO 1"
		} ];
		for ( var i = 0; i <= mydataCERESO.length; i++) {
			$("#gridRS").jqGrid('addRowData', i + 1,
					mydataCERESO[i]);
		}
		//Termina solo para pruebas
}

function limpiarDetalles(){
	$("#accordionDetalles").accordion("destroy");
	$("#accordionDetalles").html("&nbsp;");
	$("#detalles").html("Detalles");
}

function consultarCasos(id){
	for(i = 1; i<=5; i++){
		$("#accordionDetalles").append("<h3><a href=\"javascript:void(0);\">Caso " + i +"</a></h3><div><p>Datos del caso " + i + "</p></div>");
	}
    $("#accordionDetalles").accordion('destroy').accordion();

	$("#detalles").html("NUC: PXDX1 - Eduardo Alvarado Díaz<br /> CERESO 1");
}

</script>

<html:form action="/reinsercionSocial.do">

<div class="ui-layout-east">
	<div class="header">
		<span id="east-pin" class="pin-button"></span>
		<span id="east-closer" class="pin-button"></span>
		<div id="detalles">
			Detalles 
		</div>
	</div>
	<div class="content">
		<div id="accordionDetalles">
			
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div -->
</div>


<div class="ui-layout-north">
	<div class="content">
		<tiles:insert attribute="detalles" />
	</div>
	<ul class="toolbar">
		<li id="tbarBtnHeaderZise" class="first"><span></span></li>
	</ul>
</div>
	
<div id="mainContent">
	<div class="ui-layout-center">
		<!-- div class="header">Resultado de la Búsqueda</div-->
		<div class="ui-layout-content">
			<div class="ui-layout-north">
				<div id="divGridRS">
					<table id="gridRS"></table>
					<div id="pagerGridRS" style="vertical-align: top;"></div>
				</div>									
			</div>
		</div>
	</div>
</div>
	
</html:form>