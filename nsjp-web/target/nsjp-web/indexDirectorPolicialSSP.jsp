<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
 
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

<STYLE type=text/css>

DD P {
       LINE-HEIGHT: 120%
}

#iRepLegalAccordionPane {
       BORDER-BOTTOM: #b5c9e8 0px solid; BORDER-LEFT: #b5c9e8 0px solid; PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; WIDTH: 1000px; PADDING-RIGHT: 1px; BACKGROUND: #fff; HEIGHT: 355px; BORDER-TOP: #b5c9e8 0px solid; BORDER-RIGHT: #b5c9e8 0px solid; PADDING-TOP: 1px
}

#iRepLegalAccordionPane DL {
       WIDTH: 1000px; HEIGHT: 355px
}

#iRepLegalAccordionPane DT {
       TEXT-ALIGN: right;
       PADDING-BOTTOM: 0px;
       LINE-HEIGHT: 44px;
       TEXT-TRANSFORM: uppercase;
       PADDING-LEFT: 0px;
       PADDING-RIGHT: 15px;
       FONT-FAMILY: Arial, Helvetica, sans-serif;
       BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) #fff no-repeat 0px 0px;
       LETTER-SPACING: 1px;
       HEIGHT: 46px;
       COLOR: #1c94c4;
       FONT-SIZE: 1.1em;
       FONT-WEIGHT: bold;
       PADDING-TOP: 0px
}

#iRepLegalAccordionPane DT.active {
       BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) #fff no-repeat 0px 0px; COLOR: #e78f08; CURSOR: pointer
}

#iRepLegalAccordionPane DT.hover {
       COLOR: #e78f08
}

#iRepLegalAccordionPane DT.hover.active {
       COLOR: #1c94c4
}

#iRepLegalAccordionPane DD {
       BORDER-BOTTOM: #dbe9ea 1px solid; BORDER-LEFT: 0px; PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: url(<%= request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom; BORDER-TOP: #dbe9ea 1px solid; MARGIN-RIGHT: 1px; BORDER-RIGHT: #dbe9ea 1px solid; PADDING-TOP: 1px
}

#iRepLegalAccordionPane .slide-number {
       COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 10px
}

#iRepLegalAccordionPane .active .slide-number {
       COLOR: #fff
}

#iRepLegalAccordionPane A {
       COLOR: #68889b
}

#iRepLegalAccordionPane DD IMG {
       MARGIN: 0px; FLOAT: right
}

#iRepLegalAccordionPane H2 {
       MARGIN-TOP: 10px; FONT-SIZE: 2.5em
}

#iRepLegalAccordionPane .more {
       DISPLAY: block; PADDING-TOP: 10px
}

body {
       background-image: url(<%= request.getContextPath()%>/images/back_gral.jpg);
       background-repeat: repeat-x;
}

body,td,th {
       font-family: Arial, Helvetica, sans-serif;
}

</STYLE>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/reloj.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
       <script src="<%=request.getContextPath()%>/js/prettify.js"></script>
       <script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
       <script type="text/javascript">

       var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';

       if(sesionActiva=="false"){
       //alert(sesionActiva);
       document.location.href="<%= request.getContextPath()%>/Logout.do";
       }

             var outerLayout, innerLayout;
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
                    outerLayout = $("body").layout( layoutSettings_Outer );
                    $("#solicitarDetencion").click(muestraSolicitarDetencion);
                    $("#solicitudes").click(muestraSolicitudes);
                    $("#descarcelacionNA").click(muestraDescarcelacionNA);
                    $("#descarcelacionProceso").click(muestraDescarcelacionProceso);
                    $("#descarcelacionTerminada").click(muestraDescarcelacionTerminada);
                    $("#dialogBlok").dialog({
        				autoOpen: false,
        				modal: true,
        				width: 400,
        				height: 200,
        				closeOnEscape: false,
        				draggable: false,
        				resizable: false,
        				buttons: {
        					'Autenticarme!': function(){
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
 
                    $("#accordionmenuprincipal").accordion({  fillSpace: true });
                    $("#accordionmenuderprincipal").accordion({ fillSpace: true});
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

                    //muestraGadgets();
                    /*jQuery("#gridDetalleFrmPrincipal").jqGrid({

                           url:'<%=request.getContextPath()%>/EjemploCoorPeriDefensoriaCheck.xml',

                           datatype: "xml",

                           colNames:['Asignar','Nombre del Solicitante','Número del Expediente','Número de Caso','Tipo de solicitud','Especialidad Perito','Fecha Límite','Enterado' ,'Acuse'],

                           colModel:[   {name:'Asignar',index:'asignar', width:25},

                                               {name:'Solicitante',index:'solicitante', width:50},

                                               {name:'Noexpediente',index:'noexpediente', width:50},

                                               {name:'NumeroCaso',index:'numeroCaso', width:40},

                                               {name:'TipoSolicitud',index:'tipoSolicitud', width:40},

                                               {name:'Especialidad',index:'especialidad', width:50},

                                               {name:'Fechav',index:'fechav', width:30},

                                               {name:'Enterado',index:'enterado', width:20},

                                               {name:'Acuse',index:'acuse', width:10}

                                        ],

                           pager: jQuery('#pager1'),

                           rowNum:10,

                           rowList:[10,20,30],

                           autowidth: true,

                           sortname: 'detalle',

                           viewrecords: true,

                           sortorder: "desc",

                           ondblClickRow: function(rowid) {

                                        dblClickRowBandejaSolicitudes(rowid);

                                        }

                    }).navGrid('#pager1',{edit:false,add:false,del:false});*/         

      

                                                                  

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

                          

                    $("#controlAgenda").click(creaAgenda);

                    $("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '337px');

                    $("#ligaEvidencias").click(muestraGridEvidencia);

                    $('#test').weatherfeed(['MXDF0132']);

                   

             });

 

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

 

             function muestraGridEvidencia(){

                    ocultaMuestraGridsAlertas('2');

             }

      

             function cerrarVentana(){

                    $.closeWindow('iframewindowCoordPeriDefensoriaBandjSolicitudes');

             }

            

             function dblClickRowBandejaSolicitudes(rowID){

                    $.newWindow({id:"iframewindowCoordPeriDefensoriaBandjSolicitudes", statusBar: true, posx:255,posy:111,width:840,height:450,title:"Designación de Perito", type:"iframe"});

                 $.updateWindowContent("iframewindowCoordPeriDefensoriaBandjSolicitudes",'<iframe src="<%=request.getContextPath()%>/visorCoorPeriDefensoriaBandSolicitudes.do" width="840" height="450" />');

             }

 

             function muestraSolicitarDetencion(){

                    $.newWindow({id:"iframewindowSolicitarDetencion", statusBar: true, posx:255,posy:111,width:850,height:350,title:"Solicitud Detención ", type:"iframe"});

                 $.updateWindowContent("iframewindowSolicitarDetencion",'<iframe src="<%=request.getContextPath()%>/solicitarDetencion.jsp" width="850" height="350" />');

             }

 

             function muestraSolicitudes(){

                    $.newWindow({id:"iframewindowSolicitudes", statusBar: true, posx:255,posy:111,width:850,height:350,title:"Solicitudes", type:"iframe"});

                 $.updateWindowContent("iframewindowSolicitudes",'<iframe src="<%=request.getContextPath()%>/solicitudesSSPAMP.jsp" width="850" height="350" />');

             }

 

             function muestraDescarcelacionNA(){

                    $.newWindow({id:"iframewindowDescarcelacionNA", statusBar: true, posx:255,posy:111,width:850,height:350,title:"Descarcelación No Atendida", type:"iframe"});

                 $.updateWindowContent("iframewindowDescarcelacionNA",'<iframe src="<%=request.getContextPath()%>/descarcelacionNAPoliciaSSP.jsp" width="850" height="350" />');

             }

 

             function muestraDescarcelacionProceso(){

                    $.newWindow({id:"iframewindowDescarcelacionProceso", statusBar: true, posx:255,posy:111,width:850,height:350,title:"Descarcelación En Proceso", type:"iframe"});

                 $.updateWindowContent("iframewindowDescarcelacionProceso",'<iframe src="<%=request.getContextPath()%>/descarcelacionProceso.jsp" width="850" height="350" />');

             }

 

             function muestraDescarcelacionTerminada(){

                    $.newWindow({id:"iframewindowDescarcelacionTerminada", statusBar: true, posx:255,posy:111,width:850,height:350,title:"Descarcelación Terminada", type:"iframe"});

                 $.updateWindowContent("iframewindowDescarcelacionTerminada",'<iframe src="<%=request.getContextPath()%>/descarcelacionTerminada.jsp" width="850" height="350" />');

             }

            

            

      

             function creaAgenda() {

                    $.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});

                 $.updateWindowContent("iframewindowagenda",'<iframe src="<%=request.getContextPath()%>/InicioAgenda.do" width="1150" height="600" />');         

                 $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();

                    }

      

             function muestraGridAl2(){

                    ocultaMuestraGridsAlertas('1');

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

                    applyDefaultStyles:                     false // basic styling for testing & demo purposes

             ,      minSize:                                       50 // TESTING ONLY

             ,      spacing_closed:                                14

             ,      north__spacing_closed:                  8

             ,      south__spacing_closed:                  8

             ,      north__togglerLength_closed:      -1 // = 100% - so cannot 'slide open'

             ,      south__togglerLength_closed:      -1

             ,      fxName:                                               "slide" // do not confuse with "slidable" option!

             ,      fxSpeed_open:                           1000

             ,      fxSpeed_close:                                 2500

             ,      fxSettings_open:                        { easing: "easeInQuint" }

             ,      fxSettings_close:                       { easing: "easeOutQuint" }

             ,      north__fxName:                                 "none"

             ,      south__fxName:                                 "drop"

             ,      south__fxSpeed_open:                    500

             ,      south__fxSpeed_close:                   1000

             //,    initClosed:                                    true

             ,      center__minWidth:                       200

             ,      center__minHeight:                      200

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

             ,      defaults: {

                           size:                             "auto"

                    ,      minSize:                          50

                    ,      paneClass:                        "pane"              // default = 'ui-layout-pane'

                    ,      resizerClass:              "resizer"    // default = 'ui-layout-resizer'

                    ,      togglerClass:              "toggler"    // default = 'ui-layout-toggler'

                    ,      buttonClass:               "button"     // default = 'ui-layout-button'

                    ,      contentSelector:           ".content"   // inner div to auto-size so only it scrolls, not the entire pane!

                    ,      contentIgnoreSelector:     "span"       // 'paneSelector' for content to 'ignore' when measuring room for content

                    ,      togglerLength_open:        35                  // WIDTH of toggler on north/south edges - HEIGHT on east/west edges

                    ,      togglerLength_closed:      35                  // "100%" OR -1 = full height

                    ,      hideTogglerOnSlide:        true         // hide the toggler when pane is 'slid open'

                    ,      togglerTip_open:           "Close This Pane"

                    ,      togglerTip_closed:         "Open This Pane"

                    ,      resizerTip:                       "Resize This Pane"

                    //     effect defaults - overridden on some panes

                    ,      fxName:                                 "slide"             // none, slide, drop, scale

                    ,      fxSpeed_open:              750

                    ,      fxSpeed_close:                    1500

                    ,      fxSettings_open:           { easing: "easeInQuint" }

                    ,      fxSettings_close:          { easing: "easeOutQuint" }

              }

             ,      north: {

                           spacing_open:              1                   // cosmetic spacing

                    ,      togglerLength_open:        0                   // HIDE the toggler button

                    ,      togglerLength_closed:      -1                  // "100%" OR -1 = full width of pane

                    ,      resizable:                        false

                    ,      slidable:                         false

                    //     override default effect

                    ,      fxName:                                 "none"

                    }

             ,      south: {

                           maxSize:                          200

                    ,      togglerLength_closed:      -1                  // "100%" OR -1 = full width of pane

                    ,      slidable:                         false        // REFERENCE - cannot slide if spacing_closed = 0

                    ,      initClosed:                       false

                    }

             ,      west: {

                           size:                             250

                    ,      spacing_closed:                   21                  // wider space when closed

                    ,      togglerLength_closed:      21                  // make toggler 'square' - 21x21

                    ,      togglerAlign_closed:       "top"        // align to top of resizer

                    ,      togglerLength_open:        0                   // NONE - using custom togglers INSIDE west-pane

                    ,      togglerTip_open:           "Close West Pane"

                    ,      togglerTip_closed:         "Open West Pane"

                    ,      resizerTip_open:           "Resize West Pane"

                    ,      slideTrigger_open:         "click"      // default

                    ,      initClosed:                       false

                    //     add 'bounce' option to default 'slide' effect

                    ,      fxSettings_open:           { easing: "" }

                    ,      west__onresize:            function () { $("#accordionmenuprincipal").accordion("resize"); }

                    }

             ,      east: {

                           size:                             250

                    ,      spacing_closed:                   21                  // wider space when closed

                    ,      togglerLength_closed:      21                  // make toggler 'square' - 21x21

                    ,      togglerAlign_closed:       "top"        // align to top of resizer

                    ,      togglerLength_open:        0                   // NONE - using custom togglers INSIDE east-pane

                    ,      togglerTip_open:           "Close East Pane"

                    ,      togglerTip_closed:         "Open East Pane"

                    ,      resizerTip_open:           "Resize East Pane"

                    ,      slideTrigger_open:         "mouseover"

                    ,      initClosed:                       false

                    //     override default effect, speed, and settings

                    ,      fxName:                                 "drop"

                    ,      fxSpeed:                          "normal"

                    ,      fxSettings:                       { easing: "" } // nullify default easing

                    ,      est__onresize:             function () { $("#accordionmenuderprincipal").accordion("resize"); }            

                    }

             ,      center: {

                           paneSelector:              "#mainContent"                    // sample: use an ID to select pane instead of a class

                    ,      onresize:                         "innerLayout.resizeAll"    // resize INNER LAYOUT when center pane resizes  

                    ,      minWidth:                         200

                    ,      minHeight:                        200

                    }

             };

            

             function ocultaMuestraGridsAlertas(idGrid){

                    if(parseInt(idGrid)==1){

                           $("#divGridSolicitudes").css("display", "block");

                           $("#gridDetalleFrmPrincipal").find("tr:nth-child(2)").css({ color: "#FFFFFF", background: "#FFFFFF" });

                           $("#divGridEvidencias").css("display", "none");

                    }else if(parseInt(idGrid)==2){

                           $("#divGridSolicitudes").css("display", "none");

                           $("#divGridEvidencias").css("display", "block");

                           gridEvidencias();

                    }     

             }

      

             function gridEvidencias(){

                    jQuery("#gridDetalleEvidencias").jqGrid({

                           url:'<%= request.getContextPath()%>/EjemploCoordinadorPeritajeEvidencia.xml',

                           datatype: "xml",

                           colNames:['Número de Expediente','Número de Caso','Tipo de solicitud','Nombre del Solicitante','Cadena de Custodia','Perito Responsable','Fecha Límite','Acuse' ],

                           colModel:[   {name:'NumeroExpediente',index:'numeroExpediente', width:40},

                                        {name:'NumeroCaso',index:'numeroCaso', width:25},

                                        {name:'TipoSolicitud',index:'tipoSolicitud', width:40},

                                        {name:'NombreSolicitante',index:'nombreSolicitante', width:35},

                                        {name:'CadenaCustodia',index:'cadenaCustodia', width:30},

                                        {name:'PeritoResponsable',index:'peritoResponsable', width:35},

                                        {name:'FechaVencimiento',index:'fechaVencimiento', width:35},

                                        {name:'Acuse',index:'acuse', width:10}

                                        ],

                           pager: jQuery('#pagerEvidencias'),

                           rowNum:10,

                           rowList:[10,20,30],

                           autowidth: true,

                           sortname: 'numeroExpediente',

                           viewrecords: true,

                           sortorder: "desc",

                           multiselect: true,

                           ondblClickRow: function(rowid) {

                                  muestraSolEvidencia(rowid);

                           }

                           }).navGrid('#pagerEvidencias',{edit:false,add:false,del:false});

                    }

      

             function muestraSolEvidencia(rowid){

                    $.newWindow({id:"iframewindowAsignacionEvidencia", statusBar: true, posx:255,posy:111,width:700,height:350,title:"Asignación de Evidencia", type:"iframe"});

                 $.updateWindowContent("iframewindowAsignacionEvidencia",'<iframe src="<%=request.getContextPath()%>/asignacionDeEvidencia.jsp" width="700" height="350" />');

             }

            

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

             function ejecutaChat() {

                    $("#dialogoChat").dialog( "open" );

             }

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

                                        <a id="solicitudesAudio" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Director Policía Procesal</a>

                                  </h3>

                                  <div>

                                  <table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" style="cursor:pointer">

                                 

                                               <tr>

                                                  <td width="100%" id="solicitarDetencion"><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><span id="sp1" onclick="estilosMenu('sp1')">Solicitudes</span></td>

                                               </tr>

                                               <tr>

                                                  <td width="100%" id="solicitudes"><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><span id="sp2" onclick="estilosMenu('sp2')">Descarcelación</span></td>

                                                     

                                               </tr>

                                               <tr>

                                              

                                                            <td width="100%" id="descarcelacionNA" ><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><span id="sp3" onclick="estilosMenu('sp3')">No Atendida</span></td>

                                               </tr>

                                               <tr>

                                                            <td width="100%" id=""><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><span id="sp4" onclick="estilosMenu('sp4')">En Proceso </span></td>

                                                     

                                               </tr>

                                               <tr>

                                                            <td width="100%" id=""><img src="<%=request.getContextPath()%>/resources/images/icn_folderchek.png" width="20" height="16"  /><span id="sp5" onclick="estilosMenu('sp5')">Terminada</span></td>                                                  

                                                     

                                               </tr>

                                        </table>

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

                    <h6>

                           <a href="#" id="">Consultar Leyes y C&oacute;digos</a>

                    </h6>

                    <div>

                           <table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" bordercolorlight="#FFFFFF" style="cursor:pointer">

                                  <tr>

                                  <td id="leyes" width="100%"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Leyes</td>

                               </tr>

                               <tr>

                                   <td id="codigos" >&nbsp;<img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Códigos</td>

                               </tr>

                               <tr>

                                   <td id="manuales">&nbsp;<img  src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Manuales</td>

                               </tr>

                           </table>

                    </div>

                    <!--h1>

                           <a href="#">Clima</a>

                    </h1>

                    <div align="left">

                           <div align="left" id="test"></div>

                    </div>

                    <h1>

                           <a href="#">Chat</a>

                    </h1>

                    <div>

                           <div id="dialogoChat" title="Chat" align="center">

                                  <iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>

                           </div>

                           <center>

                                  <a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>

                           </center>

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
					    <TD width=301 align=left valign="middle" style="color:#51a651;">DIRECTOR POLICIAL PROCESAL</TD>
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

       </div>

       <div id="menu_config">

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

                           <div class="ui-layout-north">

                                  <div id="divGridSolicitudes">

                                        <table id="gridDetalleFrmPrincipal"></table>

                                        <div id="pager1"></div>

                                  </div>

                                  <div id="divGridEvidencias">

                                        <table id="gridDetalleEvidencias"></table>

                                        <div id="pagerEvidencias"></div>

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

 

 
