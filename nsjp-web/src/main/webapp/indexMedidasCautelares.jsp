<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
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
        <link rel="stylesheet" type="text/css" 
        	  href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
        	  
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

        <!--COMIENZAN SCRIPTS-->

        <!--jquery-->
        <script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
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
        <script type="text/javascript" 	
        src="<%=request.getContextPath()%>/js/comun.js"></script>
        <script type="text/javascript" 
        src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>

		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
        <script type="text/javascript">
        var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
		if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
		}

        var audienciaID;
        var  expedienteID;
        var documentoID;
        var numeroCarpeta;
        var CONTEXT_ROOT = '<%= request.getContextPath()%>'; 

        var firstGrid = true;
        var firstGridAlternas=true;
		var idWindowMedidaCautelar = 1;	
		var idWindowMedidaAlterna = 1;	
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
        outerLayout = $("body").layout( layoutSettings_Outer );

        $("#accordionmenuprincipal").accordion({  fillSpace: true });
        $("#accordionmenuderprincipal").accordion({ fillSpace: true});
        $("#seccion1tree").treeview();
		$("#seccion2tree").treeview();
		$("#seccion3tree").treeview();
		$("#seccion4tree").treeview();
		$("#seccion5tree").treeview();
        $("#controlAgenda").click(creaAgenda);
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
      //obtenemos el tiempo de las alarmas y ponemos en marcha el timer		
        var tiempo='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoRevisionAlarmas()%>';
        setInterval(muestraAlerta, tiempo);

		muestraGadgets();
		$( "#tabs" ).tabs();	

		$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Menor de Edad', 
		dialogClass: 'alert', 
		width: 500 ,
		maxWidth: 600,
		buttons: {"Aceptar":function() {
						$(this).dialog("close");
					}
			  } 
		});		

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

        //cargaGrid(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.NO_ATENDIDA.getValorId()%>);

        $('#test').weatherfeed(['MXDF0132']);
        
      //llenamos el grid de incumplidas del dia anterior
			jQuery("#gridCautelaresIncumplidas").jqGrid({
                url:'<%=request.getContextPath()%>/consultaGridMedidasCautelaresIncumplidas.do',
                datatype: "xml",
                colNames:["Nombre del Imputado","Expediente SSP","Numero Caso", "Causa","Tipo de medida"],
                colModel:[
					{name:'imputado',index:'3', sortable:false, viewable:false, key:false, hidden:false},
					{name:'expedienteSSP',index:'4', sortable:false, viewable:false, key:false, hidden:false},
                    {name:'numeroCaso',index:'1', sortable:false, viewable:false, key:false, hidden:false},
                    {name:'causa',index:'2',  sortable:false},
                    {name:'TipoMedida',index:'5',  sortable:false, viewable:false, key:false, hidden:false}
                ],
                pager: jQuery('#paginadorGridCautelaresIncumplidas'),
                rowNum:10,
                rowList:[10,20,30],
                autowidth: true,
                autoheight:true,
                height:450,
                sortname: '1',
                viewrecords: true,
                sortorder: "desc"
            }).navGrid('#paginadorGridCautelaresIncumplidas',{edit:false,add:false,del:false});
			$("#divGridCautelaresIncumplidas").hide();
			
			//llenamos el grid de incumplidas Alternas del dia anterior
			jQuery("#gridAlternasIncumplidas").jqGrid({
	           url:'<%=request.getContextPath()%>/consultaGridMedidasAlternasIncumplidas.do',
	           datatype: "xml",
	           colNames:["Nombre del Imputado","Expediente SSP","Numero Caso", "Causa","Tipo de medida"],
	           colModel:[
					{name:'imputado',index:'3', sortable:false, viewable:false, key:false, hidden:false},
					{name:'expedienteSSP',index:'4', sortable:false, viewable:false, key:false, hidden:false},
	               {name:'numeroCaso',index:'1', sortable:false, viewable:false, key:false, hidden:false},
	               {name:'causa',index:'2',  sortable:false},
	               {name:'TipoMedida',index:'5',  sortable:false, viewable:false, key:false, hidden:false}
	           ],
	           pager: jQuery('#paginadorGridAlternasIncumplidas'),
	           rowNum:10,
	           rowList:[10,20,30],
	           autowidth: true,
	           autoheight:true,
	           height:450,
	           sortname: '1',
	           viewrecords: true,
	           sortorder: "desc"
	       }).navGrid('#paginadorGridAlternasIncumplidas',{edit:false,add:false,del:false});
			$("#divGridAlternasIncumplidas").hide();
			
      });
            //FIN ON READY

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

		//Carga grid para medidas cautelares segun su estatus
		function cargaGrid(estatusMedida){
			ocultaMuestraGrids("grid");
			if(firstGrid == true){
	            jQuery("#grid").jqGrid({
	                url:'<%=request.getContextPath()%>/consultaGridMedidasCautelares.do?estatus='+estatusMedida+'',
	                datatype: "xml",
	                colNames:["Nombre del Imputado","Expediente SSP","Numero Caso", "Causa"],
	                colModel:[
						{name:'imputado',index:'3', sortable:false, viewable:false, key:false, hidden:false},
						{name:'expedienteSSP',index:'4', sortable:false, viewable:false, key:false, hidden:false},
	                    {name:'numeroCaso',index:'1', sortable:false, viewable:false, key:false, hidden:false},
	                    {name:'causa',index:'2',  sortable:false}
	                ],
	                pager: jQuery('#paginadorDetalle'),
	                rowNum:10,
	                rowList:[10,20,30],
	                autowidth: true,
	                autoheight:true,
	                height:450,
	                sortname: '1',
	                viewrecords: true,
	                sortorder: "desc",
	                ondblClickRow: function(rowid) {
	                	lanzaMenuMedidasCautelares(rowid,estatusMedida);
	                }
	            }).navGrid('#paginadorDetalle',{edit:false,add:false,del:false});

	            firstGrid=false;
			}else{
				jQuery("#grid").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaGridMedidasCautelares.do?estatus='+estatusMedida+'',datatype: "xml",
                ondblClickRow: function(rowid) {
                	lanzaMenuMedidasCautelares(rowid,estatusMedida);
                }});
				$("#grid").trigger("reloadGrid");		
			}
		}	

        //Carga grid para medidas alternas segun su estatus
		function cargaGridAlternas(estatusSolicitud){
			ocultaMuestraGrids("gridAlternas");
			if(firstGridAlternas == true){
	            jQuery("#gridAlternas").jqGrid({
	                url:'<%=request.getContextPath()%>/consultaGridMedidasAlternas.do?estatus='+estatusSolicitud+'',
	                datatype: "xml",
	                colNames:['NUS','Nombre del sentenciado','Expediente SSP','Numero Caso','Causa','Carpeta de Ejecucion'],
	                colModel:[
						{name:'nus',index:'5', sortable:false},
						{name:'sentenciado',index:'6', sortable:false},
  	                    {name:'expedienteSSP',index:'4', sortable:false},
	                    {name:'numeroCaso',index:'1', sortable:false},
	                    {name:'causa',index:'2',  sortable:false},
	                    {name:'carpetaEjecucion',index:'3',  sortable:false},
	                   
	                ],
	                pager: jQuery('#paginadorDetalleAlternas'),
	                rowNum:10,
	                rowList:[10,20,30],
	                autowidth: true,
	                autoheight:true,
	                height:450,
	                sortname: '1',
	                viewrecords: true,
	                sortorder: "desc",
	                ondblClickRow: function(rowid) {
	                	lanzaMenuMedidasAlternas(rowid,estatusSolicitud);
	                }
	            }).navGrid('#paginadorDetalleAlternas',{edit:false,add:false,del:false});

	            firstGridAlternas=false;
			}else{
				jQuery("#gridAlternas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaGridMedidasAlternas.do?estatus='+estatusSolicitud+'',datatype: "xml",
	                ondblClickRow: function(rowid) {
	                	lanzaMenuMedidasAlternas(rowid,estatusSolicitud);
	                }});
				$("#gridAlternas").trigger("reloadGrid");		
			}
		}
		
       /*
		*Funcion que acarrea el id del expediente, para devolverlo
		*a la pantalla de detalle 
		*/
		function dblClickRowBandejaAudiencias(rowID){
			idWindowDetalleNotificacion++;
			$.newWindow({id:"iframewindowDetalleNotificacion"+idWindowDetalleNotificacion, statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Atender Notificación", type:"iframe"});
	    	$.updateWindowContent("iframewindowDetalleNotificacion"+idWindowDetalleNotificacion,'<iframe src="<%=request.getContextPath()%>/acarrearIdEvento.do?idEvento=' +rowID +'" width="1140" height="400" />'); 
	    	$("#" +"iframewindowDetalleNotificacion"+idWindowDetalleNotificacion+ " .window-maximizeButton").click();
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
                $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();
                }


            /*
             *Funcion que llama a la funcionalidad para generar un documento
             */
	
            function lanzaMenuSalidasAlmacen() {
                abreNuevoFrame("iframewindowGenerarDocumento", "/salidaAlmacen.do", 10, 10, 1024, 900, "Salida de Almacén");
            }

          
	
            function lanzaMenuMedidasCautelares(rowid, estatus) {
            	idWindowMedidaCautelar++;
				$.newWindow({id:"iframewindowMedidasCautelares"+idWindowMedidaCautelar, statusBar: true, posx:255,posy:111,width:650,height:300,title:"Medidas Cautelares", type:"iframe"});
                $.updateWindowContent("iframewindowMedidasCautelares"+idWindowMedidaCautelar,'<iframe src="<%=request.getContextPath()%>/visorMedidasCautelares.do?rowid='+rowid+'&estatus='+estatus+'" width="650" height="300" />');
            }

            function lanzaMenuMedidasAlternas(rowid, estatus) {
            	idWindowMedidaAlterna++;
				$.newWindow({id:"iframewindowMedidasAlternas"+idWindowMedidaAlterna, statusBar: true, posx:255,posy:111,width:650,height:300,title:"Medidas Alternas", type:"iframe"});
                $.updateWindowContent("iframewindowMedidasAlternas"+idWindowMedidaAlterna,'<iframe src="<%=request.getContextPath()%>/visorMedidasAlternas.do?rowid='+rowid+'&estatus='+estatus+'" width="650" height="300" />');
	    		
            }

            function paraGridGenerarReporte() {
            // Se llena del osd Seleccionar almacén de evidencias
	
            }

        	function consultaNotificaciones()
        	{
        		ocultaMuestraGrids('gridNotificaciones');
        		//Grid de Notificaciones
        		jQuery("#gridNotificacionEventos").jqGrid({ 
        			url:'<%= request.getContextPath()%>/ConsultaNuevasNotificaciones.do', 
        			datatype: "xml", 
        			colNames:['Expediente','Tipo Evento', 'Evento','Fecha-Hora Solicitud','Fecha-Hora Evento'], 
        			colModel:[ 	{name:'expediente',index:'1', width:250}, 
        						{name:'tipoEvento',index:'2', width:260}, 
        						{name:'evento',index:'3', width:260}, 
        						{name:'fechaHoraSolicitud',index:'4', width:270},
        						{name:'fechaHoraEvento',index:'5', width:270}
        																	
        					],
        			pager: jQuery('#pagerGridNotificacionEventos'),
        			rowNum:10,
        			rowList:[10,20,30],
        			autowidth: true,
        			sortname: '1',
        			viewrecords: true,
        			sortorder: "desc",
        			ondblClickRow: function(rowid) {
        					dblClickRowBandejaAudiencias(rowid);
        					}
        		}).navGrid('#pagerGridNotificacionEventos',{edit:false,add:false,del:false});	
        		
        	}
        	
            function funcionMuestraGrids(){
                ocultaMuestraGrids("grid");
            }
            
            function ejecutaChat() {
        		$("#dialogoChat").dialog( "open" );
        	}

        	/*
        	*Funcion que oculta o muestra los grids, recibe como parametro
        	*el nombre del grid que va a mostrar, y todos los demas, se 
        	*ocultaran
        	*/ 
        	function ocultaMuestraGrids(nombreGrid){

        		if(nombreGrid == "gridNotificaciones"){
        			$("#divGridNotificaciones").show();
        			$("#divGridDetalle").hide();
					$("#divGridDetalleAlternas").hide();
        			$('#divGridPreliberacion').hide();
        			$('#divGridCautelaresIncumplidas').hide();
        		}
        		if(nombreGrid == "grid"){
        			$("#divGridNotificaciones").hide();
        			$("#divGridDetalle").show();
					$("#divGridDetalleAlternas").hide();
        			$('#divGridPreliberacion').hide();
        			$('#divGridCautelaresIncumplidas').hide();
        		}
				if(nombreGrid == "gridAlternas"){
        			$("#divGridNotificaciones").hide();
        			$("#divGridDetalle").hide();
        			$("#divGridDetalleAlternas").show();
					$('#divGridPreliberacion').hide();
					$('#divGridCautelaresIncumplidas').hide();
        		}
				if(nombreGrid == "gridCautelaresIncumplidas"){
        			$("#divGridNotificaciones").hide();
        			$("#divGridDetalle").hide();
        			$("#divGridDetalleAlternas").hide();
					$('#divGridPreliberacion').hide();
					$('#divGridCautelaresIncumplidas').show();
        		}
        	}


        	function cargaCarpetadePreliberacion() {
        		
        		$('#divGridPreliberacion').show();
        		$('#divGridNotificaciones').hide();
        		$('#divGridDetalle').hide();
        		
        		
        	//Número de causa
        	//- Número de carpeta de ejecución
        	//- Solicitante
        	//- Institución solicitante
        	//- Sentenciado
        	//- Fecha - hora de solicitud (Nota 1)
        	
        jQuery("#gridPreliberacion").jqGrid({ 
        						url:'<%= request.getContextPath()%>/consultarSolicitudesPorTipoYEstatus.do',
        						datatype: "xml", 
        						colNames:['Número de Causa','Carpeta de Ejecución','Solicitante','Institución Solicitante','Sentenciado','Fecha - Hora de Solicitud'], 
        						colModel:[ 	{name:'numeroCausa',index:'1', width:127}, 
        									{name:'carpetaEjecucion',index:'2', width:127}, 
        									{name:'solicitante',index:'3', width:127}, 
        									{name:'institucionSolicitante',index:'4', width:127},	
        									{name:'sentenciado',index:'5', width:127},											
        									{name:'fechaSolicitud',index:'6', width:127}
        																				
        								],
        						pager: jQuery('#paginadorGridPreliberacion'),
        						rowNum:10,
        						rowList:[10,20,30],
        						height: 445,
        						width:767 ,
        						sortname: '1',
        						viewrecords: true,
        						sortorder: "desc",
        						ondblClickRow: function(rowid) {								
        							audienciaID=rowid.split("*-*")[2];
        							expedienteID=rowid.split("*-*")[1];	
        							documentoID=rowid.split("*-*")[0];	

        							var id2 = jQuery("#gridPreliberacion").jqGrid('getGridParam','selrow');
        			                var ret2 = jQuery("#gridPreliberacion").jqGrid('getRowData',id2);
        			                
        			                numeroCarpeta=ret2.expediente;						
        							visorACE(documentoID, expedienteID, audienciaID);
        							
        			                    
        							
        						}
        					}).navGrid('#paginadorGridPreliberacion',{edit:false,add:false,del:false});	

        	
        	}
        	function visorACE() {
        		
        		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:255,posy:111,width:400,height:400,title:"Numero de Carpeta: "+numeroCarpeta, type:"iframe"});
        	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/visorAdministrarCarpetaDeEjecucionPJOAE.do" width="400" height="400" />');
        	    		
        	}

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

        <!--Comienza ui-layout-west-->
        <div class="ui-layout-west">
            <div class="header">&nbsp;</div>
            <div class="content">
                <div id="accordionmenuprincipal">
                    <h3><a id="menuEntradaAlmacen" href="#"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Cumplimiento de pena</a></h3>
                    <div>
                       <ul id="seccion2treePJENC" class="filetree">
							<li><span class="check" ><a id="recurso" style="cursor: pointer;" onclick="cargaGridAlternas(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.NO_ATENDIDA.getValorId()%>)">Nuevas</a></span>
							</li>
							<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGridAlternas(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.EN_PROCESO.getValorId()%>)">En proceso<br></a></span>
							</li>
							<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGridAlternas(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.ATENDIDA.getValorId()%>)">Concluidas<br></a></span>
							</li>
							<!-- <li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGridIncumplimientosAlternas()">Incumplimientos<br></a></span> -->
							</li>							
						</ul>	
                    </div>
                    <h3><a id="menuSalidaAlmacen" href="#" onclick=""><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Cautelares</a></h3>
                    <div>
                     <ul id="seccion2treePJENC" class="filetree">
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGrid(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.NO_ATENDIDA.getValorId()%>)">Nuevas</a></span></li>
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGrid(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.EN_PROCESO.getValorId()%>)">En proceso<br></a></span></li>
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGrid(<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.ATENDIDA.getValorId()%>)">Concluidas<br></a></span></li>
						<li><span class="check"><a id="anclaGridCautelaresIncumplidas" style="cursor: pointer;" onclick="ocultaMuestraGrids('gridCautelaresIncumplidas');">Incumplidas<br></a></span></li>
						<!--  <li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="cargaGridIncumplimientos()">Incumplimientos<br></a></span></li> -->
					</ul>	
                    </div>
                    <!--<h3><a id="menuExpediente" href="#" onclick="lanzaMenuGestionarAlmacen()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Incumplimientos</a></h3>
                    <div>
                     <ul id="seccion2treePJENC" class="filetree">
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="">Del D&iacute;a</a></span></li>
						<li><span class="check"><a id="recurso" style="cursor: pointer;" onclick="">Terminada<br></a></span></li>
						
					</ul>	
                    </div>-->
               <h3 ><a id="evento" href="#" onclick="cargaCarpetadePreliberacion()"><img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">&nbsp;Beneficios de Preliberación</a></h3>
				<div>			
					<!-- <ul id="seccion1treePJENC" class="filetree">
						<li><span class="file"><a id="audienciaDelDia" style="cursor: pointer;" onclick="cargaGridDiaPJENC();">Audiencia de Ejecución</a></span></li>
						<li><span class="file" id="porFecha" style="cursor: pointer;" onclick="modalFecha()">Carpeta de Ejecución</span></li>
						<li><span class="file" id="porFecha" style="cursor: pointer;" onclick="modalFecha()">Hist&oacute;rico</span></li>
					</ul>	-->	
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

        <!--Fin ui-layout-west-->

        <!--Comienza ui-layout-east-->
    <div class="ui-layout-east">
		<div class="header">Bienvenido</div>
		<div class="content">
			<div id="accordionmenuderprincipal">
				<h4>
					<a href="#" >Datos Personales</a>
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
				<h4>
					<a href="#">Agenda</a>
				</h4>
				<div>
					<center>
						<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
					</center>
					<br />
					
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
				<!--h4>
					<a href="#">Clima</a>
				</h4>
				<div align="left">
					<div align="left" id="test"></div>
				</div>
				<h4>
					<a href="#">Chat</a>
				</h4>
				<div align="center">
				
					<div id="dialogoChat" title="Chat" align="center">
						<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
					</div>
					<center>
						<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
					</center>
						<!--iframe src="http://gaby1:5280/web/jwchat/index.html" frameborder="0" width="200" height="200" scrolling="no"></iframe  -->
				<!--/div-->
				<h4><a href="#" onclick="consultarTiposRol();">Facultades</a></h4>
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
        <!--Termina ui-layout-east-->

        <!--Comienza ui-layout-north-->
        <div class="ui-layout-north">
		<div class="content">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"  height="100%">
				  <TBODY style="background: #fff;">
					  <TR>
					    <TD width=100 align=left valign="middle"><img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg"></TD>
					    <TD width=301 align=left valign="middle" style="color:#51a651;">ENCARGADO DE PENAS Y REINSERCION SOCIAL</TD>
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
            <!--comienza barra de herramientas-->
            <ul class="toolbar">
                <div id="menu_head">
                    <li id="tbarBtnHeaderZise" class="first"><span></span></li>
                </div>
                <div id="menu_config">
                    <li id="generarDocumento"><span></span>Generar Documento&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="15" height="16"--></li>
                    <li><span></span>Adjuntar documento&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctoadjun.png" width="10" height="16"--></li>
                </div>
            </ul>
            <!--termina barra de herramientas--></div>
        <!--Termina ui-layout-north-->

        <!--Comienza ui-layout-south-->
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
        <!--Termina ui-layout-south-->

        <!--Comienza main content-->
        <div id="mainContent">
            <div class="ui-layout-center">
                <div class="ui-layout-content">
                    <div class="ui-layout-north">

                        <div id="divGridDetalle">
                            <table id="grid"></table>
                            <div id="paginadorDetalle"></div>
                        </div>
						<div id="divGridDetalleAlternas">
                            <table id="gridAlternas"></table>
                            <div id="paginadorDetalleAlternas"></div>
                        </div>

						<div id="divGridNotificaciones" style="display: none;">
						 	<table id="gridNotificacionEventos" width="100%" height="100%"></table>
							<div id="pagerGridNotificacionEventos"></div>
						</div>


						<div id="divGridPreliberacion" >
						<table id="gridPreliberacion" ></table>
						<div id="paginadorGridPreliberacion"></div>
						</div>
						<!-- GRID MEDIDAS CAUTELARES INCUMPLIDAS -->
						<div id="divGridCautelaresIncumplidas">
                            <table id="gridCautelaresIncumplidas"></table>
                            <div id="paginadorGridCautelaresIncumplidas"></div>
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

