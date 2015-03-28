<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
						
		<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#iVictimaAccordionPane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 6px;
				WIDTH: 1000px;
				PADDING-RIGHT: 0px;
				HEIGHT: 362px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#iVictimaAccordionPane DL {
				WIDTH: 1000px; HEIGHT: 355px
			}
			/*acordeon editar*/
			#iVictimaAccordionPane DT {
				TEXT-ALIGN: right;
				PADDING-BOTTOM: 16px;
				PADDING-TOP: 2px;
				PADDING-LEFT: 0px;
				LINE-HEIGHT: 35px;
				TEXT-TRANSFORM: none;	
				/*acomodo texto*/PADDING-RIGHT: 40px;
				FONT-FAMILY: Arial, Helvetica, sans-serif;
				LETTER-SPACING: 1px;
				/*distancia persianas*/HEIGHT: 25px;
				COLOR: #f5f5f5;
				FONT-SIZE: 12px;
				FONT-WEIGHT: normal;	
				background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
				background-repeat: no-repeat;
				background-position: 28px;
			}
			#iVictimaAccordionPane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#iVictimaAccordionPane DT.hover {
				COLOR: #f5f5f5
			}
			#iVictimaAccordionPane DT.hover.active {
				COLOR: #f5f5f5
			}
			#iVictimaAccordionPane DD {
				BORDER-BOTTOM: #dbe9ea 0px solid; 
				BORDER-LEFT: 0px; 
				PADDING-BOTTOM: 1px; 
				PADDING-LEFT: 1px; 
				PADDING-RIGHT: 1px; 
				/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/ 
				BORDER-TOP: #dbe9ea 0px solid; 
				MARGIN-RIGHT: 1px; 
				BORDER-RIGHT: #dbe9ea 0px solid; 
				PADDING-TOP: 1px
			}
			/*distancia y color de numero*/
			#iVictimaAccordionPane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#iVictimaAccordionPane .active .slide-number {
				COLOR: #fff
			}
			#iVictimaAccordionPane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#iVictimaAccordionPane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#iVictimaAccordionPane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#iVictimaAccordionPane .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
		</style>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
		<!--Hoja de estilo -->
      	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
      	
      	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/css/layout_complex.css"
			media="screen" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" 
			href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
      	<!--Scripts-->
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
		<script type="text/javascript" 
			src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
      	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
      	
      	
		<script type="text/javascript">
		
			jQuery().ready(function () {
				jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
					url:'local', 
					datatype: "xml", 
					colNames:['No. Expediente','Fecha','Hora','Usuario','Nombre del funcionario','Permitida'], 
					colModel:[ 	{name:'Expediente',index:'1', width:70},
								{name:'Fecha',index:'2', width:30}, 
								{name:'Hora',index:'3', width:20},
								{name:'Usuario',index:'4', width:35}, 
								{name:'Nombre',index:'5', width:120},
								{name:'Permitida',index:'6', width:20}
								
							],
					pager: jQuery('#pager1'),
					rowNum:60,
					rowList:[10,20,30,40,50,60],
					autowidth: true,
					sortname: 'turno',
					viewrecords: true,
					id: 'pager1',
					onSelectRow: function(id){
					
						},
					sortorder: "desc"
				}).navGrid('#pager1',{edit:false,add:false,del:false});


				
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
					  										  $("#gridDetalleExpediente").setGridWidth($("#mainContent").width() - 5, true);}
					}
				};
								
				$("#idFechaDate").datepicker({
					dateFormat: 'dd/mm/yy',
					yearRange: '1900:2100',
					changeMonth: true,
					changeYear: true,
					showOn: "button",
					buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
					buttonImageOnly: true			
				});
						
		});

			
			function customRange(input) {
				  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
					$('#idHoraDate').timeEntry('getTime') : null),
					maxTime: (input.id == 'idHoraDate' ?
					$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
			    }
			    $(function(){
			      $('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
			    });

		//Funcion para obtener los Datos generales de eslabon
				function busca(){
					var numeroExpe=$('#numeroExpedienteText').val();
					var fecha=$('#idFechaDate').val();
					var hora=$('#idHoraDate').val();
					var nombre=$('#nombreFuncionario').val();
					if(numeroExpe!=''){
						jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
								{url:'<%=request.getContextPath()%>/consultaBitacoraINQ.do?numeroExpediente='+numeroExpe+'&fecha='+fecha+'&hora='+hora+'&nombre='+nombre, 
								datatype: "xml" });
							 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
					}
					
				}						
				
		</script>
	</head>
<body>
	<input type="hidden" name="xml" id="xml" />
<TABLE border=0>
  <TBODY>
  <TR vAlign=top>
    <TD width="1060" align="center">
	    <table width="196" align="center" border="0" cellspacing="0" cellpadding="2">
			<tr>
				<td align="center" class="txt_gral_victima">Pantalla consultar bitácora INQ</td>
			</tr>
     	</table>
	</TD>
  </TR>
  <TR vAlign=top>
    <TD>
      <TABLE width="100%" height="176" border=0 class="back_denuncia" id=iDenuncianteWorkSheet>
        <TBODY>
        <TR vAlign=top>
        </TR>	
        <TR vAlign=top>
          <TD width="100%" align="left" valign="middle">
          	<table width="960" height="116" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
           	 <tr>
                <td class="txt_gral_victima">No. de expediente</td>
                <td><input type="text" size="25" maxlength="40" id="numeroExpedienteText"/></td>
               	<td class="txt_gral_victima">Fecha de inicio</td>
                <td><div id="idFechaDateDiv">
						<input type="text" id="idFechaDate" width="50px">
					</div>
				</td>
               <td  class="txt_gral_victima">Hora de inicio</td>
                <td><div id="idHora">
					<input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM">
				</td>
                <td  class="txt_gral_victima">Permitidas:</td>
                <td class="txt_gral_victima">0</td>
			</tr>
			<tr>
				<td class="txt_gral_victima">Nombre de Funcionario</td>
                <td colspan="5"><input type="text" size="75" maxlength="40" id="nombreFuncionario"/></td>
                <td class="txt_gral_victima">No permitidas:</td>
                <td class="txt_gral_victima">0</td>
			</tr>
			<tr>
			<td colspan="8" align="right">
				<INPUT type=button class="btn_guardar" id=buscarBitacora value=Buscar onclick="busca()"></td>
			</tr>
          </table>
         </TD>
       </TR>
	   <TR vAlign=top>
       <TR vAlign=top>
          <TD colSpan=3>&nbsp;</TD>
        </TR>
        </TBODY>
    </TABLE>                		
				

		<tr  align="left" valign="top">
			<td>
			<div id="turnoPrincipal">
				<table id="gridDetalleFrmPrincipal"></table>
				<div id="pager1"></div>
			</div>
			</td>
		</tr>
	</table>
	
</body>
</html>