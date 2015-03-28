<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="java.util.Date"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas" %>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<html>
<head>
	<script type="text/javascript">
		var contextoPagina = "${pageContext.request.contextPath}";
		var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
		var tiempoActiva = '<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoBloqueoSesion()%>';
	</script>
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
	
		
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimeout.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/timer/jquery.idletimer.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.async.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/sesion.js"></script>

	<script type="text/javascript">
	
	estaSesionActiva();
	

	
	var tur;
	var gridAud=0;
	var outerLayout, innerLayout;
	var idWindowNuevaDenuncia=1;
	var idWindowSolicitarEvidencia = 1;
	var selector;
	var validaFecha;
	idWindowDetalleSolicitud=1;
	
	var numerocaso;
	var firstGridEvidenciasNuevas = true;
	var firstGridEvidenciasPendientes = true;
	var firstGridEvidenciasConcluidas = true;

	//variable que controla si se carga el grid de evaluacion de documentos por primera vez
	var firstGridEvaluarDocumentos = true;
	
	var idVentanaAvisoPosHechoDel=1;
	
	//Variable para los identificadores de las ventanas
	var idWindowDetalleNotificacion = 1;
	
	//variable para guardar el ID del area del usuario logueado
	var idAreaUserLogged='<%=Areas.UNIDAD_INVESTIGACION.ordinal()%>';
	
	var idWindowSolicitudTranscripcion=1;		
		
	$(document).ready(function() {
		
		$("#tbarBtnGenerar").click(nuevaDenunciaUI);
		$("#casos-propios").css({ color: "#1C94C4"});
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
		
		$("#permiso-expediente").css({ color: "#1C94C4"});
		
		$("#agenda-funcionario").css({ color: "#1C94C4"});
		$("#funcionario1").css({ color: "#1C94C4"});
		$("#funcionario2").css({ color: "#1C94C4"});
		$("#admin-agendas").css({ color: "#1C94C4"});
		
		$("#ligSolServPericial").css({ color: "#1C94C4"});
		$("#consulta_dictamen").css({ color: "#1C94C4"});
		$("#divGridNotificaciones").hide();
		$("#divGridSolsXAtndr").hide();
		$("#tabsAtencionTempranaPenal" ).tabs();
		$("#tabs" ).tabs();	
		$("#tbarBtnAsignar").click(asigarPermisos);
				
		//obtenemos el tiempo de las alarmas y ponemos en marcha el timer		
		var tiempo='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getTiempoRevisionAlarmas()%>';
		setInterval(muestraAlerta, tiempo);
		
		//Mandamos consultar los tipos de solicitud dependiendo del Area del Funcionario
		consultarTiposSolicitudPorAreaDelFuncionarioGen('tableSolsGeneradas',"0");
		consultarTiposSolicitudPorAreaDelFuncionario('tableSolsXAtender',idAreaUserLogged);
		$("#controlAgenda").click(creaAgenda);	
		$("#iVictimaAccordionDialogoMenorEdad").dialog(
			{ 	autoOpen: false, 
				modal: true, 
				title: 'Menor de Edad', 
				dialogClass: 'alert', 
				width: 400 ,
				maxWidth: 400,
				buttons:	{"Aceptar":function() {
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
				
		outerLayout = $("body").layout( layoutSettings_Outer );
		$("#accordionmenuprincipal").accordion({  fillSpace: true });
		$("#accordionmenuderprincipal").accordion({ fillSpace: true});
//		$("#seccion2tree").treeview();
//		$("#seccion3tree").treeview();
//		$("#seccion4tree").treeview();
//		$("#seccion5tree").treeview();
//		$("#seccion6tree").treeview();
//		$("#exp0001").click(seleccionFila);	
//		$("#exp0002").click(seleccionFila2);
//		$("#expI0001").click(seleccionFila);	
//		$("#expI0002").click(seleccionFila2);
//		$("#expD0001").click(seleccionFila);	
//		$("#expD0002").click(seleccionFila2);
//		$("#expS0001").click(seleccionFila);	
//		$("#expS0002").click(seleccionFila2);	
//		$("#expC0001").click(seleccionFila);	
//		$("#expC0002").click(seleccionFila2);	
		
		muestraGadgets();
		 
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=ATENDER_CANALIZACION', 
			datatype: "xml", 
			colNames:['Caso','Tipo','Fecha', 'Denunciante', 'Delito','Origen','Estatus'], 
			colModel:[ 	{name:'Detalle',index:'1', width:70}, 
			           	{name:'Tipo',index:'7', width:120, align:'center', hidden:true},
						{name:'Fecha',index:'2', width:70}, 
						{name:'Nombre',index:'3', width:70}, 
						{name:'Resumen',index:'4', width:110},
						{name:'Origen',index:'5', width:110},
						{name:'Estatus',index:'6', width:110}
					],
			pager: jQuery('#pager1'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			autoheight:true,
			height:400,
			sortname: 'detalle',
			viewrecords: true,
			ondblClickRow: function(id) {
				consultaDenuncia(id);
			},
			sortorder: "desc"
		}).navGrid('#pager1',{edit:false,add:false,del:false});
		
		//GRID Expedientes Compartidos
		jQuery("#gridExpCompartidos").jqGrid({ 
			url:'<%=request.getContextPath()%>/BusquedaExpCompartidosFuncionarioLogAMP.do', 
			datatype: "xml", 
			colNames:['Caso','Fecha', 'Denunciante', 'Delito','Origen','Estatus'], 
			colModel:[ 	{name:'Detalle',index:'1', width:70}, 
						{name:'Fecha',index:'2', width:70}, 
						{name:'Nombre',index:'3', width:70}, 
						{name:'Resumen',index:'4', width:110},
						{name:'Origen',index:'5', width:110},
						{name:'Estatus',index:'6', width:110}
					],
			pager: jQuery('#pagerExpCompartidos'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],

			autoheight: true,
			autowidth: true,
			sortname: '1',
			viewrecords: true,
			onSelectRow: function(id){
//				muestraAcordeon();
				},
			ondblClickRow: function(id) {
				
				//alert(numerocaso);
				consultaDenuncia(id);
				},
			sortorder: "desc"
		}).navGrid('#pagerExpCompartidos',{edit:false,add:false,del:false});
		//FIN GRID Expedientes Compartidos
		
		
		//cargo los datos del grid desde la BD
	jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
				{url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=ATENDER_CANALIZACION', 
				datatype: "xml" });
			 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 

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
		
		$("#bandeja_casos").click(mostrarBandejaCasos);
		$("#ligServiciosPericiales").click(mostrarServiciosPericiales);
		$("#ligSolServPericial").click(solServicioPericial);

		//cargo los datos del grid desde la BD
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
				{
					url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=ATENDER_CANALIZACION', 
					datatype: "xml" });
			 $("#gridDetalleFrmPrincipal").trigger("reloadGrid");
	
	//	$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '410px');	 
		 
		//Grid de Notificaciones
		jQuery("#gridNotificacionEventos").jqGrid({ 
			url:'<%= request.getContextPath()%>/ConsultaNuevasNotificaciones.do', 
			datatype: "xml", 
			colNames:['Expediente','Tipo Evento', 'Evento','Fecha-Hora Solicitud','Fecha-Hora Evento'], 
			colModel:[ 	{name:'expediente',index:'expediente', width:250}, 
						{name:'tipoEvento',index:'tipoEvento', width:260}, 
						{name:'evento',index:'evento', width:260}, 
						{name:'fechaHoraSolicitud',index:'fechaHoraSolicitud', width:270},
						{name:'fechaHoraEvento',index:'fechaHoraEvento', width:270}
																	
					],
			pager: jQuery('#pagerGridNotificacionEventos'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaAudiencias(rowid);
					}
		}).navGrid('#pagerGridNotificacionEventos',{edit:false,add:false,del:false});	
		
		//Grid de Solicitudes por atender
		jQuery("#gridSolsXAtndr").jqGrid({ 
			//url:'local', 
			datatype: "xml", 
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente'], 
			colModel:[ 	{name:'caso',index:'caso', width:180,hidden:true},
			           	{name:'expediente',index:'expediente', width:180}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100},
						{name:'fechaLimite',index:'fechaLimite', width:100,hidden:true},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsXAtndr'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudes(rowid);
					}
		}).navGrid('#pagerGridSolsXAtndr',{edit:false,add:false,del:false});	
		
		//Grid de Solicitudes generadas
		jQuery("#gridSolsGeneradas").jqGrid({ 
			//url:'local', 
			datatype: "xml", 
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Destinatario'], 
			colModel:[ 	{name:'caso',index:'caso', width:150,hidden:true},
			           	{name:'expediente',index:'expediente', width:180}, 
						{name:'folio',index:'folio', width:100}, 
						{name:'estatus',index:'estatus', width:100}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100},
						{name:'fechaLimite',index:'fechaLimite', width:100,hidden:true},
						{name:'institucion',index:'institucion', width:100},
						{name:'remitente',index:'remitente', width:200}
					],
			pager: jQuery('#pagerGridSolsGeneradas'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
					dblClickRowBandejaSolicitudesGen(rowid);
					}
		}).navGrid('#pagerGridSolsGeneradas',{edit:false,add:false,del:false});

		//Grid de Mandamientos Judiciales
		jQuery("#gridMandamientosJudiciales").jqGrid({ 
			//url:'local', 
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
		
		$("#gview_gridNotificacionEventos .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridSolsXAtndr .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridSolsGeneradas .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridMandamientosJudiciales .ui-jqgrid-bdiv").css('height', '400px');
		$("#gview_gridExpCompartidos .ui-jqgrid-bdiv").css('height', '400px');
		$("#divGridNotificaciones").hide();
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
		
		$('#test').weatherfeed(['MXDF0132']);
		//construimos el menu de las carpetas de auditoria
//		generaMenuExpedientesDelArea();
		
		<logic:notEmpty name="KEY_SESSION_MENU_DINAMICO">
			<logic:iterate name="KEY_SESSION_MENU_DINAMICO" id="elementoMenuAcordeon" >
			$("#<bean:write name="elementoMenuAcordeon" property="cIdHTML" />").treeview(
				{
					url:contextoPagina + "/obtenerSubMenus.do"
/*					,
					ajax: {
						data: {
							"additional": function() {
								return "yeah: " + new Date;
							}
					},
						type: "post"
					}
*/				}			
			);
			</logic:iterate>	
		</logic:notEmpty>
		
	});
	//fin funcion onready

    var iframewindowAPSE = 0;
    function asigarPermisos(){
		$.newWindow({id:"iframewindowAPSE"+iframewindowAPSE, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Asignar permisos sobre Expediente: ", type:"iframe"});
		$.updateWindowContent("iframewindowAPSE"+iframewindowAPSE,'<iframe src="<%= request.getContextPath() %>/asigarPermisosExpediente.do" width="1430" height="670" />');
		$("#" +"iframewindowAPSE"+iframewindowAPSE + " .window-maximizeButton").click();
		iframewindowAPSE++;
    }

	
	//crea una nueva ventana para la agenda	
	function creaAgenda() {
		$.newWindow({id:"iframewindowagenda", statusBar: true, posx:10,posy:10,width:1150,height:600,title:"Agenda", type:"iframe"});
	    $.updateWindowContent("iframewindowagenda",'<iframe src="<%= request.getContextPath() %>/InicioAgenda.do" width="1150" height="600" />');		
	    $("#" +"iframewindowagenda"+ " .window-maximizeButton").click();	
	}
	
	function muestraAcordeonIndexProcuraduria(){
		//alert("entra muestra acordeon");
        
    	$("#gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '150px');
    	$("#divCasos .ui-jqgrid-bdiv").css('height', '150px');
		$("#iRepLegalAccordionPane").css("display", "block");
   	}

	//Abre una nueva ventana para solicitar evidencia
	function autorizaEvidencia(rowid) {
		idWindowSolicitarEvidencia++;
		$.newWindow({id:"iframewindowSolicitarEvidencia" + idWindowSolicitarEvidencia, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Autorizar Solicitud de Evidencia", type:"iframe"});
	    $.updateWindowContent("iframewindowSolicitarEvidencia" + idWindowSolicitarEvidencia,'<iframe src="<%= request.getContextPath() %>/autorizarEvidencia.do?rowid='+rowid+'" width="1050" height="600" />');		
	}	
   	
	
	function mostrarBandejaCasos(){
		ocultaMuestraGridsAlertas('1');
	}

	function ejecutaChat() {
		$("#dialogoChat").dialog( "open" );
	}

	function mostrarServiciosPericiales(){
		ocultaMuestraGridsAlertas('2');
	}
	function solServicioPericial(){
		$.newWindow({id:"iframewindowVisorSolicitarServicioPericial", statusBar: true, posx:200,posy:50,width:850,height:380,title:"Solicitar Servicio Pericial", type:"iframe"});
	    $.updateWindowContent("iframewindowVisorSolicitarServicioPericial",'<iframe src="<%=request.getContextPath()%>/solicitarServicioPericial.do" width="850" height="380" />');
	}


  function seleccionFila(){

		$("#1").css({ color: "#FFFFFF", background: "#FF0000" }); 
		$("#2").css({ background: "#dafafc", color: "#008000" });
	}

	function seleccionFila2(){

		$("#2").css({ color: "#FFFFFF", background: "#FF0000" }); 
		$("#1").css({ background: "#dafafc", color: "#FFA500" });
	}

    function consultaDenuncia(id) {

    	var ingresoDenuncia = true;
        var pantallaSolicitada=4;
		idWindowNuevaDenuncia++;
		 		
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Carpeta de investigación: ", type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'&flagIndexProcView=1" width="1430" height="670" />');
		$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia + " .window-maximizeButton").click();		
	}

    /*
	*Funcion para realizar la consulta del grid de visitadores
	*/
	function cargaGridVisitadores(idDepartamento,idEstatus)
	{  //alert(idDepartamento+" -- "+idEstatus);
		//$("#divGridVisitadores,#divGridCarpetasAuditoria").hide();
		gIdDepartamento=idDepartamento;
		gIdEstatus=idEstatus;
		
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/busquedaCanalizadosRestaurativaStatus.do?estatus='+idEstatus+'',datatype: "xml" });
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
		
		//limpiamos el grid de las carpetas de auditoria
		//ocultaMuestraGrids("gridVisitadores");
		//$("#gview_gridVisitadores .ui-jqgrid-bdiv").css('height', '200px');
	}
	
	function regresaGrid(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/BusquedaCanalizadosRestaurativa.do?area=UI&actividad=ATENDER_CANALIZACION',datatype: "xml" });
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
		ocultaMuestraGrids("gridDetalleFrmPrincipal");
	}

    
    function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Carpeta de investigación: "+num);
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
												$("#gridDetalleSolServPericiales").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridEvidenciasNuevas").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridEvidenciasPendientes").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridEvidenciasConcluidas").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridNuevoAvisoPosHechoDel").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridAvisoAtnddPosHechoDel").setGridWidth($("#mainContent").width() - 5, true);
												$("#gridEvaluarDocumentos").setGridWidth($("#mainContent").width() - 5, true);	
												$("#gridDetalleFrmUno").setGridWidth($("#mainContent").width() - 5, true);	
																					 
									}
		}
	};
	

	function ocultaMuestraGridsAlertas(idGrid)
	{
		if(parseInt(idGrid)==1)
		{
			$("#divCasos").css("display", "block");
			$("#divGridSolServPericiales").css("display", "none");
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
		}
		//Agregado Grid para servicios periciales
		if(parseInt(idGrid)==2){
			$("#divCasos").css("display", "none");
			$("#divGridSolServPericiales").css("display", "block");
			gridSolServPericiales();
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
		}
	}

	/*
	*Funcion que oculta o muestra los grids, recibe como parametro
	*el nombre del grid que va a mostrar, y todos los demas, se 
	*ocultaran
	*/ 
	function ocultaMuestraGrids(nombreGrid){
		
		if(nombreGrid == "divGridAvisosDetencion"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").show();
		}

				
		if(nombreGrid == "gridDetalleFrmPrincipal"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").show();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridEvidenciasNuevas"){
			$("#divGridEvidenciasNuevas").show();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridEvidenciasPendientes"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").show();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridEvidenciasConcluidas"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").show();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridDetalleEvidencia"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").show();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridNuevoAvisoPosHechoDel"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").show();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridAvisoAtnddPosHechoDel"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").show();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridEvaluarDocumentos"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").show();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridNotificaciones"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").show();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridSolsXAtndr"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").show();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridSolsGeneradas"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").show();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridMandamientosJudiciales"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").show();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "gridAudiencias"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").show();
			$("#divGridExpCompartidos").hide();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
		if(nombreGrid == "expCompartidos"){
			$("#divGridEvidenciasNuevas").hide();
			$("#divGridEvidenciasPendientes").hide();
			$("#divGridEvidenciasConcluidas").hide();
			$("#divDetalleEvidencia").hide();
			$("#divCasos").hide();
			$("#divGridSolServPericiales").hide();
			$("#divNuevoAvisoPosHechoDel").hide();
			$("#divAvisoAtnddPosHechoDel").hide();
			$("#divGridEvaluarDocumentos").hide();
			$("#divGridNotificaciones").hide();
			$("#divGridSolsXAtndr").hide();
			$("#divGridSolsGeneradas").hide();
			$("#divGridMandamientosJudiciales").hide();
			$("#divGridAudiencias").hide();
			$("#divGridExpCompartidos").show();
			$("#divGridDetalleSolAvisosDetencion").hide();
		}
	}
	
	function gridSolServPericiales(){
				jQuery("#gridDetalleSolServPericiales").jqGrid({ 
					url:'<%= request.getContextPath()%>/.do', 
					datatype: "xml", 
					//Expediente   |   Fecha remitido    | Denunciante     |     Delito principal     |      Origen      |     Estatus 
					colNames:['Expediente',' Fecha Remitido','Denunciante', 'Delito Principal', 'Origen','Estatus'], 
					colModel:[ 	{name:'NumeroExpediente',index:'numeroExpediente', width:40},
					           	{name:'NombreSolicitante',index:'nombreSolicitante', width:40}, 
								{name:'FechaElaboracion',index:'fechaElaboracion', width:30}, 
								{name:'FechaVencimiento',index:'fechaVencimiento', width:30},
								{name:'Especialidad',index:'Especialidad', width:40},
								{name:'Especialidad',index:'Especialidad', width:40}, 
							],
					pager: jQuery('#pagerSolServPericiales'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					height:400,
					sortname: 'numeroExpediente',
					viewrecords: true,
					sortorder: "desc"
			}).navGrid('#pagerSolServPericiales',{edit:false,add:false,del:false});
		}
	
	function detEvi(){
		//jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
			//	{url:'<%= request.getContextPath()%>/EjemploUno.xml', 
				//datatype: "xml",
				//height: "30%" });
		  $("#gridDetalleFrmPrincipal").trigger("reloadGrid");

		 //alert("div:"+$("#gview_gridDetalleFrmPrincipal").get(1));
		  //$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '25px');
	}

	function nuevaDenuncia(id) {

    	var idExpediente;
        var numeroExpediente;
        var pantallaSolicitada=3;
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/nuevoExpedienteDenuncia.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			
    		}
    		
    	});
		
    	idWindowNuevaDenuncia++;
		if (idWindowNuevaDenuncia>=3){
			alert("Ya se abrio una ventana");
		}
		else{
			$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Carpeta de investigación: "+numeroExpediente, type:"iframe"});
	    	$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?idNuevaDenuncia=1&pantallaSolicitada='+pantallaSolicitada+'" width="1140" height="400" />');
		}	
	}

	/*
	*Funcion que carga el grid de evidencias nuevas
	*/
	function cargaGridEvidenciasNuevas(){
		if(firstGridEvidenciasNuevas == true){
			
			jQuery("#gridEvidenciasNuevas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=1&areaSolicitante=3', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Nombre del solicitante','Cadena de custodia','Objetos solicitados','Inicio de préstamo','Fin de préstamo','Fecha límite'], 
				colModel:[ 	{name:'Folio',index:'folio', width:100},
				           	{name:'NumeroCaso',index:'numeroCaso', width:100},
				           	{name:'NumeroExpediente',index:'numeroExpediente', width:100},
				           	{name:'NombreSolicitante',index:'nombreSolicitante', width:100},
				           	{name:'CadenaCustodia',index:'cadenaCustodia', width:100},
				           	{name:'NumeroObjetosSolicitados',index:'numeroObjetosSolicitados', width:100},
							{name:'FechaInicioPrestamo',index:'fechaInicioPrestamo', width:100},
							{name:'FechaFinPrestamo',index:'fechaFinPrestamo', width:100},
							{name:'FechaLimite',index:'fechaLimite', width:100}
						],
				pager: jQuery('#pagerEvidenciasNuevas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					autorizaEvidencia(rowid);
				}
			}).navGrid('#pagerGridEvidenciasNuevas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasNuevas=false;
		}
		else{
			jQuery("#gridEvidenciasNuevas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=1&areaSolicitante=3',datatype: "xml" });
			$("#gridEvidenciasNuevas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasNuevas');
	}

	/*
	*Funcion que carga el grid de evidencias pendientes
	*/
	function cargaGridEvidenciasPendientes(){
		if(firstGridEvidenciasPendientes == true){
			
			jQuery("#gridEvidenciasPendientes").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=2&areaSolicitante=3', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Nombre del solicitante','Cadena de custodia','Objetos solicitados','Inicio de préstamo','Fin de préstamo','Fecha límite','Ultima modificación'], 
				colModel:[ 	{name:'Folio',index:'folio', width:100},
				           	{name:'NumeroCaso',index:'numeroCaso', width:100},
							{name:'NumeroExpediente',index:'numeroExpediente', width:100},
				           	{name:'NombreSolicitante',index:'nombreSolicitante', width:100},
				           	{name:'CadenaCustodia',index:'cadenaCustodia', width:100},
				           	{name:'NumeroObjetosSolicitados',index:'numeroObjetosSolicitados', width:100},
							{name:'FechaInicioPrestamo',index:'fechaInicioPrestamo', width:100},
							{name:'FechaFinPrestamo',index:'fechaFinPrestamo', width:100},
							{name:'FechaLimite',index:'fechaLimite', width:100},
				           	{name:'FechaUltimaModificacion',index:'fechaUltimaModificacion', width:100, hidden:true} 
						],
				pager: jQuery('#pagerEvidenciasPendientes'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridEvidenciasPendientes',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasPendientes=false;
		}
		else{
			jQuery("#gridEvidenciasPendientes").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=2&areaSolicitante=3',datatype: "xml" });
			$("#gridEvidenciasPendientes").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasPendientes');
	}
	
	/*
	*Funcion que carga el grid de evidencias concluidas
	*/
	function cargaGridEvidenciasConcluidas(){
		if(firstGridEvidenciasConcluidas == true){
			
			jQuery("#gridEvidenciasConcluidas").jqGrid({ 
				url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=3&areaSolicitante=3', 
				datatype: "xml", 
				colNames:['Folio','Número de caso','Número de expediente','Nombre del solicitante','Cadena de custodia','Objetos solicitados','Inicio de préstamo','Fin de préstamo','Fecha límite','Fecha de cierre' ], 
				colModel:[ 	{name:'Folio',index:'folio', width:100},
				           	{name:'NumeroCaso',index:'numeroCaso', width:100},
							{name:'NumeroExpediente',index:'numeroExpediente', width:100},
				           	{name:'NombreSolicitante',index:'nombreSolicitante', width:100},
				           	{name:'CadenaCustodia',index:'cadenaCustodia', width:100},
				           	{name:'NumeroObjetosSolicitados',index:'numeroObjetosSolicitados', width:100},
							{name:'FechaInicioPrestamo',index:'fechaInicioPrestamo', width:100},
							{name:'FechaFinPrestamo',index:'fechaFinPrestamo', width:100},
							{name:'FechaLimite',index:'fechaLimite', width:100},
				           	{name:'FechaCierre',index:'fechaCierre', width:100} 
						],
				pager: jQuery('#pagerEvidenciasConcluidas'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridEvidenciasConcluidas',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvidenciasConcluidas=false;
		}
		else{
			jQuery("#gridEvidenciasConcluidas").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/solicitudEvidenciasPorEstatus.do?estatus=3&areaSolicitante=3',datatype: "xml" });
			$("#gridEvidenciasConcluidas").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvidenciasConcluidas');
	}
	
	function activaNuevoAviso()
	{
		jQuery("#gridNuevoAvisoPosHechoDel").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarAvisosNuevosPosiblesHechosDel.do?estatus=NO_ATENDIDA', 
			datatype: "xml", 
			colNames:['Lugar de los hechos','Tipo de delito', 'Fecha y Hora envio'], 
			colModel:[ 	{name:'Lugar',index:'lugar', width:425},
			           	{name:'Tipodelito',index:'tipodelito', width:125}, 
						{name:'Fecha',index:'fecha', width:125}
					],
			pager: jQuery('#pagerGridNuevoAvisoPosHechoDel'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			onSelectRow: function(id){
				consultaAvisoPosHechoDel(id,0);
				},
			sortorder: "desc"
		}).navGrid('#pagerGridNuevoAvisoPosHechoDel',{edit:false,add:false,del:false});
		$("#gridNuevoAvisoPosHechoDel").trigger("reloadGrid"); 
		 
		$("#gview_gridNuevoAvisoPosHechoDel .ui-jqgrid-bdiv").css('height', '410px');	
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridNuevoAvisoPosHechoDel');
	}
	
	function activaAvisoAtendido()
	{
		jQuery("#gridAvisoAtnddPosHechoDel").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarAvisosNuevosPosiblesHechosDel.do?estatus=ATENDIDA', 
			datatype: "xml", 
			colNames:['Lugar de los hechos','Tipo de delito', 'Fecha y Hora envio'], 
			colModel:[ 		{name:'Lugar',index:'lugar', width:425},
				           	{name:'Tipodelito',index:'tipodelito', width:205}, 
							{name:'Fecha',index:'fecha', width:195}
					],
			pager: jQuery('#pagerGridAvisoAtnddPosHechoDel'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			onSelectRow: function(id){
				consultaAvisoPosHechoDel(id,1);
				},
			sortorder: "desc"
		}).navGrid('#pagerGridAvisoAtnddPosHechoDel',{edit:false,add:false,del:false});
		$("#gridAvisoAtnddPosHechoDel").trigger("reloadGrid"); 
		$("#gview_gridAvisoAtnddPosHechoDel .ui-jqgrid-bdiv").css('height', '410px');	
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridAvisoAtnddPosHechoDel');
	}

	
	/*
	*Funcion que carga el grid de evaluar documentos
	*/
	function cargaGridEvaluarDocumentos(tipoDocumento){
		tipo = tipoDocumento=='Dictamen'?<%=Formas.DICTAMEN_PERICIAL.getValorId()%>:<%=Formas.INFORME_PERICIAL.getValorId()%>;
		
		if(firstGridEvaluarDocumentos == true){
			
			jQuery("#gridEvaluarDocumentos").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarDocumentosRecibidos.do?formaId='+tipo, 
				datatype: "xml", 
				colNames:['Número de Expediente','Número de Caso','Nombre del Perito','Especialidad','Fecha de Recepci&oacute;n'], 
				colModel:[ 	{name:'NumeroExpediente',index:'numeroExpediente', width:200},
				           	{name:'NumeroCaso',index:'numeroCaso', width:200},
				           	{name:'NombrePerito',index:'nombrePerito', width:200},
				           	{name:'EspecialidadPerito',index:'especialidadPerito', width:200},
				           	{name:'fechaRecepcion',index:'fechaRecepcion', width:150}
						],
				pager: jQuery('#pagerGridEvaluarDocumentos'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				height:440,
				sortname: 'fechaRecepcion',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridEvaluarDocumentos',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridEvaluarDocumentos=false;
		}
		else{
			jQuery("#gridEvaluarDocumentos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarDocumentosRecibidos.do?formaId='+tipo,datatype: "xml" });
			$("#gridEvaluarDocumentos").trigger("reloadGrid");			
		}
		
		//muestra este grid y oculta los demas
		ocultaMuestraGrids('gridEvaluarDocumentos');
	}
	
	function consultaAvisoPosHechoDel(idAviso,bandEstatus)
	{
		idVentanaAvisoPosHechoDel++;
		$.newWindow({id:"iframewindowAviPosHechoDel_"+idVentanaAvisoPosHechoDel, statusBar: true, posx:200,posy:50,width:850,height:450,title:"Aviso Posible Hecho Delictivo", type:"iframe"});
		var rowd;
		var retd;
		
		if(parseInt(bandEstatus)==0)
		{
			rowd = jQuery("#gridNuevoAvisoPosHechoDel").jqGrid('getGridParam','selrow');
			retd = jQuery("#gridNuevoAvisoPosHechoDel").jqGrid('getRowData',rowd);
		}
		else
		{
			rowd = jQuery("#gridAvisoAtnddPosHechoDel").jqGrid('getGridParam','selrow');
			retd = jQuery("#gridAvisoAtnddPosHechoDel").jqGrid('getRowData',rowd);
		}
		var aux=retd.Tipodelito;
		var posicion1=aux.indexOf('>',0);
		var posicion2=aux.indexOf('<',1);
		var tipoDelito=aux.substring(posicion1+1,posicion2);
		
		aux=retd.Fecha;
		posicion1=aux.indexOf('>',0);
		posicion2=aux.indexOf('<',1);
		var fecha=aux.substring(posicion1+1,posicion2);
		
		if(parseInt(bandEstatus)==0)//No atendidas
		{
			$.updateWindowContent("iframewindowAviPosHechoDel_"+idVentanaAvisoPosHechoDel,'<iframe src="<%= request.getContextPath() %>/consultaPosAvisoHechoDel.do?idAviso='+idAviso+'&estatus=NO_ATENDIDA&tipo='+tipoDelito+'&fecha='+fecha+' " width="850" height="450" />');			
		}
		else //atendidas
		{
			$.updateWindowContent("iframewindowAviPosHechoDel_"+idVentanaAvisoPosHechoDel,'<iframe src="<%= request.getContextPath() %>/consultaPosAvisoHechoDel.do?idAviso='+idAviso+'&estatus=ATENDIDA&tipo='+tipoDelito+'&fecha='+fecha+' " width="850" height="450" />');			
		}	    
	}
	
	function recargaGridAvisosNoAtendidos()
	{
		jQuery("#gridNuevoAvisoPosHechoDel").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarAvisosNuevosPosiblesHechosDel.do?estatus=NO_ATENDIDA',datatype: "xml" });
		$("#gridNuevoAvisoPosHechoDel").trigger("reloadGrid");
	}
	function activaPrincipal() {
		$("#divCasos").css("display", "block");
		$("#divGridSolicitudesUno").css("display", "none");
		$("#divGridSolicitudesDos").css("display", "none");

		$("#divGridAudiencias").hide();
		$("#divGridSolServPericiales").css("display", "none");
		$("#divGridEvidenciasNuevas").hide();
		$("#divGridEvidenciasPendientes").hide();
		$("#divGridEvidenciasConcluidas").hide();
		$("#divDetalleEvidencia").hide();
		$("#divGridEvaluarDocumentos").hide();
		$("#divGridNotificaciones").hide();
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
	}

	function activaUno() {
		//$("#divGridSolicitudes").css("display", "none");
		$("#divGridSolServPericiales").css("display", "none");
		$("#divGridSolicitudesUno").css("display", "block");
		$("#divCasos").css("display", "none");
		jQuery("#gridDetalleFrmUno").jqGrid({ 
			url:'<%=request.getContextPath()%>', 
			datatype: "xml", 
		//	 Expediente   |   Fecha remitido    | Denunciante     |     Delito principal     |      Origen      |     Estatus       

			
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
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
	}
	function activaDos() {
		$("#divCasos").css("display", "none");
		$("#divGridSolicitudes").css("display", "none");
		$("#divGridSolicitudesUno").css("display", "none");
		$("#divGridSolicitudesDos").css("display", "none");
		$("#divGridSolServPericiales").css("display", "block");
		$("#divGridNotificaciones").hide();
		$("#divGridSolsXAtndr").hide();
		$("#divGridSolsGeneradas").hide();
		$("#divGridMandamientosJudiciales").hide();
		$("#divGridAudiencias").hide();
		
		$("#divGridEvidenciasNuevas").hide();
		$("#divGridEvidenciasPendientes").hide();
		$("#divGridEvidenciasConcluidas").hide();
		$("#divDetalleEvidencia").hide();
		$("#divGridEvaluarDocumentos").hide();
		$("#divGridNotificaciones").hide();
		$("#divGridSolsXAtndr").hide();
		gridSolServPericiales();
	}
	
	function consultaNotificaciones()
	{
		ocultaMuestraGrids('gridNotificaciones');
		jQuery("#gridNotificacionEventos").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultaEventosPorExpediente.do?numeroExpedienteId='+numeroExpedienteId +'',datatype: "xml" });
		  $("#gridNotificacionEventos").trigger("reloadGrid");
	}
	
	/*
	*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaAudiencias(rowID){
		idWindowDetalleNotificacion++;
		$.newWindow({id:"iframewindowDetalleNotificacion"+idWindowDetalleNotificacion, statusBar: true, posx:251,posy:111,width:838,height:360,title:"Atender Notificación", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleNotificacion"+idWindowDetalleNotificacion,'<iframe src="<%=request.getContextPath()%>/acarrearIdEvento.do?idEvento=' +rowID +'" width="838" height="360" />'); 
	}
	
	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudes(rowID){
		idWindowDetalleSolicitud++;
		$.newWindow({id:"iframewindowDetalleSolicitud"+idWindowDetalleSolicitud, statusBar: true, posx:200,posy:50,width:1100,height:450,title:"Detalle Solicitud", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud,'<iframe src="<%=request.getContextPath()%>/consultarDetalleSolicitudBandeja.do?idSolicitud=' +rowID +'&tipoUsuario=1 " width="1140" height="450" />'); 
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
	*Funcion para realizar la consulta del grid de solicitudes por Atender
	*/
	function cargaGridSolsXAtndr(tipoSolicitud,idArea)
	{
		jQuery("#gridSolsXAtndr").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaSolsXAtnder.do?tipoSoliciutd='+tipoSolicitud+'&idArea='+idArea+'&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>,<%=EstatusSolicitud.EN_PROCESO.getValorId()%>,<%=EstatusSolicitud.CERRADA.getValorId()%>',datatype: "xml" });
		$("#gridSolsXAtndr").trigger("reloadGrid");
		ocultaMuestraGrids("gridSolsXAtndr");
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

					var tipoSol = $(this).find("idCampo").text();
					
					//Aplica filtro de solicitides
					if(tipoSol == '<%=TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId()%>' || 
							tipoSol == '<%=TiposSolicitudes.TRABAJO_SOCIAL.getValorId()%>' || 
								tipoSol == '<%=TiposSolicitudes.ATENCION_PSICOLOGICA.getValorId()%>' ||
									tipoSol == '<%=TiposSolicitudes.ATENCION_JURIDICA.getValorId()%>'){

						var trTabla = '<tr>';
						trTabla = trTabla + '<td><span><img src="<%=request.getContextPath()%>/resources/css/treeview/images/folder-closed.gif" width="16" height="16"/><a onclick="cargaGridSolsXAtndr('+$(this).find("idCampo").text()+','+idArea+')">'+$(this).find("valor").text()+'</a></span></td>';
						trTabla = trTabla + '</tr>';
						$('#'+idDivArbol).append(trTabla);
					}
				});
			}
			
		});
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

				var trTabla = '<tr>';
				trTabla = trTabla + '<td><span><img src="<%=request.getContextPath()%>/resources/css/treeview/images/folder-closed.gif" width="16" height="16"/><a onclick="gridSolAvisosDetencion()">Avisos de Detenido</a></span></td>';
				trTabla = trTabla + '</tr>';
				$('#'+idDivArbol).append(trTabla);
				
				$(xml).find('ValorDTO').each(function(){
					
					var tipoSol = $(this).find("idCampo").text();
					
					//Aplica filtro de solicitides
					if(tipoSol == '<%=TiposSolicitudes.AUDIENCIA.getValorId()%>' || 
							tipoSol == '<%=TiposSolicitudes.TRABAJO_SOCIAL.getValorId()%>' || 
								tipoSol == '<%=TiposSolicitudes.ATENCION_PSICOLOGICA.getValorId()%>' ||
									tipoSol == '<%=TiposSolicitudes.ATENCION_JURIDICA.getValorId()%>'){
						
						var trTabla = '<tr>';
						trTabla = trTabla + '<td><span><img src="<%=request.getContextPath()%>/resources/css/treeview/images/folder-closed.gif" width="16" height="16"/><a onclick="cargaGridSolsGeneradas('+$(this).find("idCampo").text()+',0)">'+$(this).find("valor").text()+'</a></span></td>';
						trTabla = trTabla + '</tr>';
						$('#'+idDivArbol).append(trTabla);
					}
				});
			}
			
		});
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


	var firstAvisoDetencion = true;
	/**
	*Funcion que llena el grid de Avisos de detencion 
	*/
	function gridSolAvisosDetencion(){

		if(firstAvisoDetencion == true){

			jQuery("#gridDetalleSolAvisosDetencion").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesNoAtendidas.do', 
				datatype: "xml", 
				colNames:['Folio','Caso','Imputado:','Delito(s)','Agencia del MP','Fecha-Hora de detención','Fecha-Hora de aviso'], 
				colModel:[ 	{name:'folio',index:'2008', width:150, align:"center"},
				           	{name:'caso',index:'2002', width:180, align:"center"},
				           	{name:'imputado',index:'2009', width:200, align:"center"},
				           	{name:'delito',index:'2004', width:200, align:"center"},
				           	{name:'mp',index:'2010', width:150, align:"center"},
				           	{name:'fechaHoraDetencion',index:'2011', width:150, align:"center"},
				           	{name:'fechaHoraAviso',index:'2012', width:150, align:"center"}
				           
				           	
						],
				pager: jQuery('#pagerDetalleSolAvisosDetencion'),
				rowNum:30,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				autoheight:true,
				sortname: '1',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					//idDocumento = rowid;
					//var ret = $("#gridDetalleSolAvisosDetencion").jqGrid('getRowData',rowid); 
					//cargaIdSolicitud(rowid);
					//varIdNumExp=ret.numeroExpedienteId;
					//numeroCaso=ret.caso;
					//alertDinamicoDosBotones("¿Dar seguimiento?", "avisosDetencion");	
					//activaConfirm("avisosDetencion");
				}
				
			}).navGrid('#pagerDetalleSolAvisosDetencion',{edit:false,add:false,del:false});
			$("#gview_gridDetalleSolAvisosDetencion .ui-jqgrid-bdiv").css('height', '450px');

			firstAvisoDetencion = false;
		}
		else{
			$('#gridDetalleSolAvisosDetencion').trigger('reloadGrid');
		}
		
		ocultaMuestraGrids('divGridAvisosDetencion');
	}
	

function visorLeyesCodigos() {
		
		$.newWindow({id:"iframewindowRestaurativa", statusBar: true, posx:200,posy:50,width:800,height:500,title:"Leyes y C&oacute;digos", type:"iframe"});
	    $.updateWindowContent("iframewindowRestaurativa",'<iframe src="<%= request.getContextPath() %>/detalleLeyesyCodigos.do" width="800" height="500" />');
	    		
	}

	/*
	*Funcion que llama a la funcionalidad para generar un visualizador de imagen  $('#imageViewer').click(generaVisorGraficaView);
	*/
	function generaVisorGraficaView() {
		$.newWindow({id:"iframewindowWindowImageViewer", statusBar: true, posx:85,posy:86,width:1120,height:499,title:"Visor de imagenes", type:"iframe"});
	    $.updateWindowContent("iframewindowWindowImageViewer",'<iframe src="<%=request.getContextPath()%>/VisorGraficas.do" width="1140" height="499" />');
	    		
	}	
	
		/*
	*Funcion que lanza la ventana par asoliciutar una transcripcion de audiencia y de audio y video
	*/
	function muestraSolicitudTranscripcion()
	{
		idWindowSolicitudTranscripcion++;
		$.newWindow({id:"iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion, statusBar: true, posx:253,posy:113,width:812,height:454,title:"Solicitud de Transcripción", type:"iframe"});
    	$.updateWindowContent("iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion,'<iframe src="<%=request.getContextPath()%>/solicitarTranscripcionEnPG.do" width="812" height="454" />');

    	}
		
//	/*
//	*Funcion para generar dinamicamente el menu izquierdo para la opcion de 
//	*expedientes del area
//	*/
//	function generaMenuExpedientesDelArea()
//	{
//		//limpiamos el menu de los tipos de solicitud
//		$("#seccion2tree").empty();
//		//$("#seccion1tree").append("<li class='closed' id='li_dptos_10'><span class='folder'>"+$(this).find('nombre').text()+"</span>"+"<ul id='li_dptosTree_"+$(this).find('jerarquiaOrganizacionalId').text()+"' class='filetree'></ul></li>");
//		$.ajax({
//    		type: 'POST',
//    		url: '<%= request.getContextPath()%>/consultarEstadosPorDepartamentoDinamico.do',
//    		data: 'area='+<%= Areas.UNIDAD_INVESTIGACION.ordinal()%>,
//    		dataType: 'xml',
//    		async: false,
//    		success: function(xml){
//				$(xml).find('estatus').each(function(){
//					//barremos el grid para generar la cadena de IDs de los delitos normales
//					$("#seccion2tree").append("<li><span class='file'><a onclick='cargaGridVisitadores("+<%= Areas.UNIDAD_INVESTIGACION.ordinal()%>+","+$(this).find('idCampo').text()+")'>"+$(this).find('valor').text()+"</a></span></li>");
//    			});
//    		}
//    		
//    	});
//		$("#seccion2tree").append("<li><span class='file'><a onclick='activaExpCompartidos()'>Expedientes compartidos</a></span></li>");
//		$("#seccion2tree").treeview();
//		//alert(cadenaIDs);
//	}
	
	
	function nuevaDenunciaUI() {
		var idExpediente;
        var numeroExpediente;
        var numeroExpedienteId;
        var numeroCasoNuevo;
		////ya estaba
        var idNuevaDenuncia = 1;
      //variable que indica si es un ingreso o una consulta
        var ingresoDenuncia = false;
       	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/nuevoExpedienteUI.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			idExpediente=$(xml).find('expedienteDTO').find('expedienteId').text();
    			//alert("idExpediente"+idExpediente);
    			numeroExpediente=$(xml).find('expedienteDTO').find('numeroExpediente').text();
    			//alert("numeroExpediente"+numeroExpediente);
    			numeroExpedienteId=$(xml).find('expedienteDTO').find('numeroExpedienteId').text();
    			//alert("numeroExpedienteId"+numeroExpedienteId);
    			numeroCasoNuevo=$(xml).find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text();
    		}
    		
    	});
       	var pantallaSolicitada=3;
    	idWindowNuevaDenuncia++;
		isWindowOpen = true;
		$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: "+numeroExpediente+" - No. Caso: "+numeroCasoNuevo, type:"iframe"});
		$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/IngresarMenuIntermedio.do?detenido=1&numeroGeneralCaso='+numeroCasoNuevo+'&abreenPenal=abrPenal&idNuevaDenuncia='+idNuevaDenuncia +'&ingresoDenuncia='+ingresoDenuncia +'&numeroExpediente='+numeroExpediente+'&pantallaSolicitada='+pantallaSolicitada+'&idNumeroExpedienteop='+numeroExpedienteId+'&idExpedienteop='+idExpediente+'" width="1430" height="670" />');
		//}																																			
    }   

	//Funcion que valida si los campos estan llenos al enviar 
	function validaCamposFecha() {

		if ($('#fechaInicio').val() == '' || $('#fechaFin').val() == '') {
			alertDinamico("Debes ingresar la fecha de inicio y fin de la consulta");
			validaFecha = false;
		} else {

			var fechaIniVal = $('#fechaInicio').val();
			var fechaFinVal = $('#fechaFin').val();

			var inicio = fechaIniVal.split("/");
			var fin = fechaFinVal.split("/");

			if(fin[2]>inicio[2]){
				validaFecha=true;
			}
			else{
				if(fin[2]<inicio[2]){
					validaFecha=false;
				}
				else{
					if(fin[1]>inicio[1]){
						validaFecha=true;
					}	
					else{
						if(fin[1]<inicio[1]){
							validaFecha=false;
						}
						else{
							if(fin[0]>=inicio[0]){
								validaFecha=true;
							}
							else{
								validaFecha=false;
							}
						}
					}
				}
			}
				
			if(validaFecha==false){	
				alertDinamico("La fecha final debe de ser mayor a la fecha inicial");
			}
			
		}	
	}
	
	function gridAudiencias(){
		
		var fechaInicio=$('#fechaInicio').val();
		var fechaFin=$('#fechaFin').val();
		
		if(selector == 1){
			fechaInicio="";
			fechaFin="";
		}

		if(gridAud==0){
    		jQuery("#gridAudiencias").jqGrid({ 
    			url:'<%= request.getContextPath()%>/consultarAudienciasDefensor.do?inicio='+fechaInicio+'&fin='+fechaFin+'', 	   		
    			datatype: "xml", 
				colNames:['Caso','Carácter','Tipo de Audiencia','Fecha de Audiencia','Sala','Defensor de Oficio'], 
				colModel:[{name:'caso',	 index:'caso', 		width:180},
			          {name:'caracter',	 index:'caracter',		width:110},
			          {name:'tipo',index:'tipo', 	width:115},
			          {name:'fechaHora',	 index:'fechaHora', 	    width:120},
			          {name:'sala',index:'sala',width:120},
			          {name:'defensor' ,index:'defensor', 		width:110}				         
					],			
				pager: jQuery('#pagerAudiencias'),
				rowNum:30,
				rowList:[10,20,30],
				autowidth: true,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"			
			}).navGrid('#pagerAudiencias',{edit:false,add:false,del:false});
    		
    		//jQuery("#gridAudiencias").trigger('reloadGrid');
			//$("#gview_gridAudiencias .ui-jqgrid-bdiv").css('height', '450px');
			gridAud=1;
		}
		else{
			
			jQuery("#gridAudiencias").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarAudienciasDefensor.do?inicio='+fechaInicio+'&fin='+fechaFin+'',
					datatype: "xml" });
			$("#gridAudiencias").trigger("reloadGrid");
			$("#gview_gridAudiencias .ui-jqgrid-bdiv").css('height', '450px');
		}
	}
    
    function muestraGridAudiencias()
	 {
		 selector=1;
		 gridAudiencias();
		 ocultaMuestraGrids('gridAudiencias');
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
	
	function modalFecha(){

		$('#fechaInicio').val('');
		$('#fechaFin').val('');
		$("#fechaInicio").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#fechaFin").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		//abre la ventana de detalle de la persona
		$("#busquedaFecha").dialog("open");
		$("#busquedaFecha").dialog({ autoOpen: true, 
	  		modal: true, 
	  		title: 'Buscar por Fecha', 
	  		dialogClass: 'alert',
	  		position: [255,140],
	  		width: 380,
	  		height: 260,
	  		maxWidth: 1000,
	  		buttons:{"Aceptar":function() {
	  				
	  					validaCamposFecha();
	  					
	  					if(validaFecha==true){ 
		  					selector=2;
			  				gridAudiencias();
			  				ocultaMuestraGrids('gridAudiencias');
		  					$(this).dialog("close");
				  		}
	  				},
	  				"Cancelar":function() {
		  				$(this).dialog("close");
	  				}
	  		}
	  	});
				
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
	
	/**************************************   FUNCIONES PARA EL GRID DE EXPEDIENTES COMPARTIDOS ********************************************/
	/*
	*Funcion para recargar el grid de expedientes compartidos desde el arbol izquierdo
	*/
	function activaExpCompartidos()
	{
		jQuery("#gridExpCompartidos").jqGrid('setGridParam',  
				{url:'<%=request.getContextPath()%>/BusquedaExpCompartidosFuncionarioLogAMP.do', 
				datatype: "xml" });
			 $("#gridExpCompartidos").trigger("reloadGrid"); 
			 ocultaMuestraGrids("expCompartidos");
			$("#gridExpCompartidos").setGridWidth($("#mainContent").width() - 5, true);
			//$("#gview_gridExpCompartidos .ui-jqgrid-bdiv").css('width', '900px');
	}
	/************************************** FIN  FUNCIONES PARA EL GRID DE EXPEDIENTES COMPARTIDOS ********************************************/
	//$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia + " .window-maximizeButton").click();		ajusta al tamaño de la pantalla
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
<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 

<!--Div para mostrar la ventana modal para elegir la fecha de consulta para audiencias-->
<div id="busquedaFecha" style="display: none">
	<table cellspacing="0" cellpadding="0" >
		<tr>
			<td width="153">&nbsp;</td>
			<td width="153">&nbsp;</td>
		</tr>
		<tr>
		  <td colspan="2" align="center"><strong>Fecha Inicio:</strong><input type="text" id="fechaInicio" size="20" />		  
		  </td>
	    </tr>
		<tr>
		  <td align="center">&nbsp;</td>
		  <td align="center">&nbsp;</td>
  		</tr>
		<tr>
		  <td colspan="2" align="center"><strong>Fecha Fin:&nbsp;&nbsp;</strong>&nbsp;
	      <input type="text" id="fechaFin" size="20" /></td>
  		</tr>
		<tr>
		  <td align="center">&nbsp;</td>
		  <td align="center">&nbsp;</td>
  		</tr>
	</table>
</div>
<!--Termina div fechas-->

<div class="ui-layout-west">

	<div class="header">&nbsp;</div>

	<div class="content">
		<div id="accordionmenuprincipal">
			<logic:notEmpty name="KEY_SESSION_MENU_DINAMICO">
				<logic:iterate name="KEY_SESSION_MENU_DINAMICO" id="elementoMenuAcordeon" >
					<h3>
						<a href="javascript:void(0);" onclick='<bean:write name="elementoMenuAcordeon" property="cComando" />'>						
							<img src="<%=request.getContextPath() %>/resources/images/icn_carpprincipal.png" id="botpenal" width="15" height="15">
							&nbsp;<bean:write name="elementoMenuAcordeon" property="cNombre" />
						</a>
					</h3>
					<div>
						<logic:notEmpty name="elementoMenuAcordeon" property="elementoMenuHijosDTO">
							<ul id="<bean:write name="elementoMenuAcordeon" property="cIdHTML" />" class="filetree treeview-famfamfam treeview">
								<logic:iterate name="elementoMenuAcordeon" property="elementoMenuHijosDTO" id="elementoMenuPrimerNivel" >
									<li id="<bean:write name="elementoMenuPrimerNivel" property="elementoMenuId" />" class="hasChildren expandable" onclick="<bean:write name="elementoMenuPrimerNivel" property="cComando"  />">
										<div class="hitarea hasChildren-hitarea expandable-hitarea"></div>
										<span class='<bean:write name="elementoMenuPrimerNivel" property="cClassHTML" />'>
												<bean:write name="elementoMenuPrimerNivel" property="cNombre" />
										</span>
										<ul style="display: none;">
											<li class="last">
												<span class="placeholder">&nbsp;</span>
											</li>
										</ul>
									</li>
								</logic:iterate>
							</ul>
						</logic:notEmpty>
					</div>
				</logic:iterate>					
			</logic:notEmpty>
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
				<br/>
				<!-- <ul id="seccion2tree" class="filetree">
					<li class="closed"><span class="folder" id="agenda1">Agenda de Funcionario</span>
						<ul>
							<li class="closed"><span class="file">Mitzi</span>
							<li class="closed"><span class="file">Rodrigo</span>
						</ul>
					</li>
					<li class="closed"><span class="folder" id="agenda2">Administrar Agendas</span></li>
				</ul>-->
				<!-- <table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0" bgcolor="#EEEEEE" style="cursor:pointer">
						<tr>
						   <td width="100%" id="agenda-funcionario"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Agenda de Funcionario</td>
						</tr>
						<tr>
						   <td width="100%" id="funcionario1"><img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Mitzi</td>
						</tr>
						<tr>
						   <td width="100%" id="funcionario2"><img src="<%=request.getContextPath()%>/resources/css/flecha.png" width="16" height="16" />Rodrigo</td>
						</tr>
						<tr>
						   <td width="100%" id="admin-agendas"><img src="<%=request.getContextPath()%>/resources/css/check.png" width="16" height="16" />Administrar Agendas</td>
						</tr>
				</table>	-->
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
			<!--h1><a href="#">Chat</a></h1>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe src="<%=((ConfiguracionDTO)session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280"></iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%= request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"></a>
				</center>
			</div>
			<h1><a href="#">Clima</a></h1>
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
			<div></div>
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
					    <TD width=301 align=left valign="middle" style="color:#51a651;">AMP UNIDAD DE INVESTIGACION</TD>
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
			<li id="tbarBtnGenerar" class="first"><span></span>Generar Denuncia&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="10" height="16"--></li>
		</div>
		<div id="menu_config">
			<li onclick="buscarCaso();">Buscar Caso&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca2.png" width="15" height="16"--></li>
			<li onclick="buscarExpediente();">Buscar Expediente&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_busca3.png" width="15" height="16"--></li>
			<!--<li onclick="generarDocumento();">Config01</li>
			<li>Config02</li>
			<li>Config03</li>
			<li class="last">Config04</li>-->
			<li id="verde" onclick="generarDocumento();">Configuraci&oacute;n&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_config.png" width="15" height="16"--></li>
			<li id="tbarBtnAsignar" class="first"><span></span>Asignar Permisos a Subordinados&nbsp;<!--img src="<%= request.getContextPath() %>/resources/images/icn_dctowri.png" width="10" height="16"--></li>
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
					 <div id="divCasos">
						<table id="gridDetalleFrmPrincipal"></table>
						<div id="pager1" style=" vertical-align: top;"></div>
					</div>
					<div id="divGridSolServPericiales" style="display: none; ">
						<table id="gridDetalleSolServPericiales"></table>
						<div id="pagerSolServPericiales"></div>
					</div>
				

					<div id="divGridEvidenciasNuevas" style="display: none; ">
						<table id="gridEvidenciasNuevas"></table>
						<div id="pagerGridEvidenciasNuevas"></div>
					</div>					
					<div id="divGridEvidenciasPendientes" style="display: none; ">
						<table id="gridEvidenciasPendientes"></table>
						<div id="pagerGridEvidenciasPendientes"></div>
					</div>					
					<div id="divGridEvidenciasConcluidas" style="display: none; ">
						<table id="gridEvidenciasConcluidas"></table>
						<div id="pagerGridEvidenciasConcluidas"></div>
					</div>					
					<div id="divDetalleEvidencia" style="display: none; ">
						<table id="gridDetalleEvidenciasNuevas"></table>
						<div id="pagerGridDetalleEvidenciasNuevas"></div>
					</div>
					
					<div id="divNuevoAvisoPosHechoDel" style="display: none; ">
					 	<table id="gridNuevoAvisoPosHechoDel"></table>
						<div id="pagerGridNuevoAvisoPosHechoDel"></div>
					</div>
					
					<div id="divAvisoAtnddPosHechoDel" style="display: none; ">
					 	<table id="gridAvisoAtnddPosHechoDel" width="100%" height="100%"></table>
						<div id="pagerGridAvisoAtnddPosHechoDel"></div>
					</div>
					
					<div id="divGridEvaluarDocumentos" style="display: none; ">
					 	<table id="gridEvaluarDocumentos" width="100%" height="100%"></table>
						<div id="pagerGridEvaluarDocumentos"></div>
					</div>
					<div id="divGridSolicitudesUno" style="display: none;">
					<table id="gridDetalleFrmUno"></table>
					<div id="pager3"></div>
				</div>
					<div id="divGridNotificaciones" style="display: none;">
					 	<table id="gridNotificacionEventos" width="100%" height="100%"></table>
						<div id="pagerGridNotificacionEventos"></div>
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
					
					<!-- GRID Expedientes compartidos -->
					<div id="divGridExpCompartidos" style="display: none;">
					 	<table id="gridExpCompartidos" width="100%" height="100%"></table>
						<div id="pagerExpCompartidos"></div>
					</div>
					
					<div id="divGridAudiencias">
						<table id="gridAudiencias"></table>
						<div id="pagerAudiencias"></div>
					</div>
				
					<div id="divGridDetalleSolAvisosDetencion">
						<table id="gridDetalleSolAvisosDetencion"></table>
						<div id="pagerDetalleSolAvisosDetencion"></div>
					</div>
					
					<table width="100%">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iRepLegalAccordionPane" style="display: none; width: 70%">
					            <dl>
					                <dt>Denuncia</dt>
					                <dd></dd>
					                <dt>Oficios</dt>
					                <dd></dd>
					                <dt>Solicitudes</dt>
					                <dd></dd>
					                <dt>Notificaciones</dt>
					                <dd></dd>
					                <dt>Documentos</dt>
					                <dd></dd>
					            </dl>
							</div>
						</td>
					</tr>
				</table>
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