<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.objeto.Objetos" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.actividad.CategoriasActividad" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
	 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
	 
	<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--Hojas de estilos de los combos multiselect-->
	<!-- link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" /-->
	
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--script de los combos multiselect-->
	<!-- script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script-->
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	
		$.ajaxSetup({ cache: false });
	   /*
		* Identificadores de los frames ingresar calidades
		*/
		var idWindowIngresarVictima = 1;	
		var idWindowIngresarTraductor = 1;	
		var idWindowIngresarQuienDetuvo = 1;	
		var idWindowIngresarRepresentanteLegal = 1;
		var idWindowIngresarTestigo = 1;
		var idWindowIngresarTutor = 1;
		var idWindowIngresarProbResponsable = 1;
		var idWindowIngresarContactoDeUnaOrganizacion = 1;
		var idWindowIngresarSentenciadoReinsertar = 1;
		var idWindowIngresarDenunciante = 1;
		var idWindowConsultarProbResponsable = 1;
		var idWindowConsultarDenunciante = 1;
		var idWindowConsultarTestigo = 1;
		var idWindowConsultarTraductor = 1;		
		var idWindowConsultarContactoDeUnaOrganizacion = 1;
		var idWindowModificarDenunciante = 1;
		var idWindowSolicitudTranscripcion =1;
		//var idWindowModificarRepresentanteLegal=1;
		var idWindowIngresarHechos = 1;
		
	   /*
		* Identificadores de los frames ingresar Objetos y evidencias
		*/
		var idWindowIngresarEquipoDeComputo = 1;
		var idWindowIngresarEquipoTelefonico = 1;
		var idWindowIngresarArma = 1;
		var idWindowIngresarExplosivo = 1;
		var idWindowIngresarSustancia = 1;
		var idWindowIngresarAnimal = 1;
		var idWindowIngresarVehiculo = 1;
		var idWindowIngresarAeronave = 1;
		var idWindowIngresarEmbarcacion = 1;
		var idWindowIngresarInmueble = 1;
		var idWindowIngresarNumerario = 1;
		var idWindowIngresarVegetal = 1;
		var idWindowIngresarDocumentoOficial = 1;
		var idWindowIngresarJoya = 1;
		var idWindowIngresarObraDeArte = 1;
		var idWindowAsociarIndividuo = 1;
		var iframewindowAbrirteoria = 1;
		
		//Variable para los grids
		var reloadGridDelito=false;
		var validaDelito=false;

		var idExpediente;
		var idInvolucrado = 100;
		//manda 0 por default al ingresar un probable responsable si viene de atpenal 
		//cambia a 1 si se ingresa desde coordinador AMP
		var muestraDetenido=0;
		var numeroExpediente="";
		var idNumeroExpedienteOp="";
		var idNumeroExpedienteConsul="";
		var idExpedienteop="";
		var isDelitoSaved=false;
		var pantallaSolicitada=0;
		var datosXML;
		var numerogeneralCaso= parent.numerocaso;
		var ingresoDenuncia="false";
		//id para las ventanas relacionadas a cadena de custodia
		var idWindowAsntarRegCadCus=1;

		var criterioOportinidadOp=0;
		var actuacion=0;
		
		var idWindowGenerarNotas=1;
		var idDefensor="";
	    var nombreDefensor="";
	    var num="";
	    var idWindowVisorMedidasCautelaresPJENC=1;
	    
	    var idWindowGenConvenio=1;
		var deshabilitarCampos = false;

		//bandera para deshabilitar los campos en la consutla cuando el usuario es policia ministerial
		var deshabilitarCamposPM=false;
		var idExpedienteDeli="";
		//variable para la carga de documentos de visitaduria
		var primeraVezGridDocumentosDigitales = true;
		
		var numeroCaso="";
		var idRelacionDelito="";
		
		//Comienza funcion on ready del documento
		$(document).ready(function() {
			
			numeroExpediente='<%= request.getAttribute("numeroExpediente")%>';
			if(numeroExpediente == 'null'){
				numeroExpediente='<%= request.getSession().getAttribute("numExpConsul")%>';
			}
			if(numeroExpediente == 'null'){
				numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			}
			pantallaSolicitada='<%= request.getParameter("pantallaSolicitada")%>';
			if(pantallaSolicitada == 'null'){
				pantallaSolicitada='<%= request.getSession().getAttribute("pantallaSolicitada")%>';
			}
			ingresoDenuncia='<%= request.getParameter("ingresoDenuncia")%>';

			
			

			//var numExp = '<%= request.getSession().getAttribute("numExpConsul")%>';
			idNumeroExpedienteOp='<%= request.getParameter("idNumeroExpedienteop")%>';
			idExpedienteop='<%= request.getParameter("idExpedienteop")%>';
			if(idExpedienteop=='null')
			{
				idExpedienteop='<%= request.getSession().getAttribute("idExpedienteConsulop")%>';
			}
			idExpedienteDeli=idExpedienteop;
			if(idNumeroExpedienteOp == 'null'){
				idNumeroExpedienteOp='<%= request.getSession().getAttribute("idExpedienteConsul")%>';
			}
			
			idNumeroExpedienteConsul='<%= request.getParameter("idNumeroExpedienteop")%>';
			if(idNumeroExpedienteConsul == 'null'){
				idNumeroExpedienteConsul='<%= request.getParameter("idNumeroExpediente")%>';
			}
			cargarInvolucradosExpediente('<%= request.getParameter("idNumeroExpediente")%>');
			cargarHechoExpediente('<%= request.getParameter("idNumeroExpediente")%>');
			
			$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul, 
				datatype: "xml",
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'da', width:200},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pager1Documentos'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:1100,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					consultaPDF(id);
					},
				sortorder: "desc"
			}).navGrid('#pager1Documentos',{edit:false,add:false,del:false});
			$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '500px');


			jQuery("#gridDetalleFrmPrincipalAudiencias").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarAudiencias.do?numeroExpediente='+numeroExpediente, 
				datatype: "xml",
				colNames:['Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Destinatario'],
				colModel:[ 	{name:'folio',index:'folio', width:200},
				           	{name:'estatus',index:'estatus', width:200},
							{name:'fecha',index:'fecha', width:170},							
							{name:'limite',index:'limite', width:400},
				           	{name:'institucion',index:'institucion', width:155}, 
							{name:'destinatario',index:'destinatario', width:255}
				           ],
				pager: jQuery('#pagerAudiencias'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:1100,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					//consultaPDF(id);
					},
				sortorder: "desc"
			}).navGrid('#pagerAudiencias',{edit:false,add:false,del:false});
			$("#gview_gridDetalleFrmPrincipalAudiencias .ui-jqgrid-bdiv").css('height', '500px');
			
			//grid de las alertas
			jQuery("#gridDetalleAlertas").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarAlertas.do?idExpedienteop='+idNumeroExpedienteConsul, 
				datatype: "xml", 
				colNames:['Fecha y Hora','Descripción'], 
				colModel:[ 	{name:'FechaHora',index:'fechaHora', width:160}, 
				           	{name:'Descripcion',index:'descripcion', width:450}
						],
				pager: jQuery('#pagerGridDetalleAlertas'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:800,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					//consultaPDF(id);
					},
				sortorder: "desc"
			}).navGrid('#pagerGridDetalleAlertas',{edit:false,add:false,del:false});
			
			
			cargarObjetosExpediente('<%= request.getParameter("idNumeroExpediente")%>');
			ocultaMuestraTabVisor("tabTabsCriterio",0);

			 $( "#tabs" ).tabs();
			 $( "#tabschild" ).tabs();
			 $( "#tabschild2" ).tabs();
			 $( "#tabschild3" ).tabs();
			 $( "#tabschild4" ).tabs();
			 $( "#tabschild5" ).tabs();
			 $( "#tabschild7" ).tabs();
			 $( "#tabschild9" ).tabs();
			 $( "#tabschild16" ).tabs();
			 $('#idTeoriaCaso').hide();
			 $('#idbotoncarpeta').hide();
			 
			 $('#tdCbxAjentesCoorUI').hide();
			 $('#tdCbxAjentesCoorUI1').hide();
			 
			 $('#tdCbxAjentesCoorJAR').hide();
			 $('#tdCbxAjentesCoorJAR1').hide();
			 
			 $('#idAsignarAgenteMp').hide();
			 $("#idAsignarFacilitador").hide();
			 $("#idRadiosBUt").hide();
			 			
			 ocultaMuestraTabVisor("tabTabsVisitaduria",0);
			 
			// opcion uno es para la pantalla de Atencion temprana penal
			if(pantallaSolicitada==1){
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsQuienDetuvo",0);
				
				$('#btnAccAudioVideo').hide();
				$('#btnAccAudiencia').hide();
				$('#btnAccTranscripcionAudiencia').hide();
				$('#btnAccServiciosPericiales').hide();
				$('#btnAccInvestigacionMinisterial').hide();
				$('#btnAccApoyoPericial').hide();
				$('#btnAccGenerarConvenio').hide();
				cargaActuaciones("1");
			}else if(pantallaSolicitada==2){//coordinador JAR
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				cargaActuaciones("2");
				 $('#tdCbxAjentesCoorJAR').show();
				 $('#tdCbxAjentesCoorJAR1').show();
				$("#idAsignarFacilitador").show();
				cargaAgenteJAR();
			}else if(pantallaSolicitada==3){//agentemp
				muestraDetenido=1;				
				ocultaMuestraTabVisor("tabTabsPeri",1);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",1);
				ocultaMuestraTabVisor("tabTabsCriterio",0);
				cargaActuaciones("3");
				$("#tabs").tabs("option", "selected", 2);
				 $('#tdCbxAjentesCoorUI').show();
				 $('#tdCbxAjentesCoorUI1').show();
				 $('#idAsignarAgenteMp').show();
				 $('#btnAudManJud').hide();
				 $('#btnCadCusRegEslabones').hide();
				 $('#btnCadCusRepEvidencias').hide();
				 $('#btnCadCusElabOficio').hide();
				 $('#btnCadCusAdmDestino').hide();
				 cargaAgenteMp();
				 cargaActuacionesPolMinisterial();
				 
			}else if(pantallaSolicitada==4){
				ocultaMuestraTabVisor("tabTabsPeri",1);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				ocultaMuestraTabVisor("tabTabsAudiencias",1);
				ocultaMuestraTabVisor("tabTabsCriterio",0);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				cargaActuaciones("3");
				//cargaAgenteMp();
				$("#tabs").tabs("option", "selected", 2);
				 $('#idTeoriaCaso').show();
				 $('#idbotoncarpeta').show();
				 $('#btnAudManJud').hide();
				 $('#btnCadCusRegEslabones').hide();
				 $('#btnCadCusRepEvidencias').hide();
				 $('#btnCadCusElabOficio').hide();
				 $('#btnCadCusAdmDestino').hide();
				//cargamos las actuaciones para la tab de policia ministerial
				 cargaActuacionesPolMinisterial();
			}
			else if(pantallaSolicitada==5){//facilitador
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				// $("#idRadiosBUt").show();
				cargaActuaciones("2");
			}
			else if(pantallaSolicitada==6){//policia ministerial
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsDocs",1);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				$(".btn_modificar").hide();
				$(".btn_grande").hide();
				$("#cbxAccionesTab9").attr("disabled","");
				$("#idInvestiga").attr("disabled","");
				cargaActuaciones("2");
				 cargaActuacionesPolMinisterial();
			}else if(pantallaSolicitada==7){//Coordinador Visitador
				
				var Area='<%= request.getParameter("idArea")%>';
				
				if(Area=="55" || Area=="44" || Area=="11"){
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",0);
					ocultaMuestraTabVisor("tabTabsPolMin",0);
					ocultaMuestraTabVisor("tabTabsAudiencias",0);
					ocultaMuestraTabVisor("tabTabsCadCus",0);
					ocultaMuestraTabVisor("tabTabsDocs",0);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
				}else{
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",1);
					ocultaMuestraTabVisor("tabTabsPolMin",1);
					ocultaMuestraTabVisor("tabTabsAudiencias",1);
					ocultaMuestraTabVisor("tabTabsCadCus",1);
					ocultaMuestraTabVisor("tabTabsDocs",1);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
					//ocultaMuestraTabVisor("tabschild9-2",0);
				}
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				cargaActuaciones("2");
				cargaInformacionDeResumenVisitaduira();
			}else if(pantallaSolicitada==8){//Visitador
				//Carga los documentos adjuntos que se adjuntaran a la solicitud de periciales
				cargaGridDocumentosDigitales();
				var Area='<%= request.getParameter("idArea")%>';
				var estatusVisitador='<%= EstatusExpediente.AUDITANDO.getValorId() %>';
				regsitraEstatus(estatusVisitador);
				if(Area=="55" || Area=="44" || Area=="11"){
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",0);
					ocultaMuestraTabVisor("tabTabsPolMin",0);
					ocultaMuestraTabVisor("tabTabsAudiencias",0);
					ocultaMuestraTabVisor("tabTabsCadCus",0);
					ocultaMuestraTabVisor("tabTabsDocs",0);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
				}else{
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",1);
					ocultaMuestraTabVisor("tabTabsPolMin",1);
					ocultaMuestraTabVisor("tabTabsAudiencias",1);
					ocultaMuestraTabVisor("tabTabsCadCus",1);
					ocultaMuestraTabVisor("tabTabsDocs",1);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
					//ocultaMuestraTabVisor("tabschild9-2",0);
				}
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				cargaActuaciones("2");
				cargaInformacionDeResumenVisitaduira();
				//Carga los documentos adjuntos que se adjuntaran a la solicitud de periciales
				cargaGridDocumentosDigitales();
				
			}
			if(ingresoDenuncia=='true'){
				//consulta
				//$('#btnAccAudioVideo').hide();
				consultarNotas();
				var titulo='<%= request.getSession().getAttribute("numExpConsul")%>';
				if(titulo == undefined || titulo == null || titulo == "null"){
					titulo=numeroExpediente;
					alertDinamico('entra'+titulo);
				}
				numeroCaso='<%= request.getSession().getAttribute("numeroCasoConsul")%>';
				titulo=titulo+" No. Caso: "+numeroCaso;
				window.parent.tituloVentana(titulo);
			}
			else
			{
				//insercion
				numeroCaso='<%= request.getParameter("numeroGeneralCaso")%>';
			}
			//cargarMenu();
			//cargarMenuVictima();
			//cargarMenuDenunciante();
			//cargarMenuTestigo();
			//cargarMenuTraductor();
			//cargarMenuQuienDetuvo();
						
			 $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			cargaOcupacion();

			//Se agrega el evento click para crear nuevos involucrados
			$("#nuevaVictima").click(creaNuevaVictima);
			$("#nuevoTraductor").click(creaNuevoTraductor);
			$("#quienDetuvo").click(creaQuienDetuvo);
			$("#nuevoRepresentanteLegal").click(creaNuevoRepresentanteLegal);
			$("#nuevoProbResponsable").click(creaNuevoProbResponsable);
			$("#crearDenunciante").click(crearDenunciante);
			$("#nuevoTestigo").click(creaNuevoTestigo);
			$("#nuevoContactoDeUnaOrganizacion").click(creaNuevoContactoDeUnaOrganizacion);
			$("#ingresarSentenciadoReinsertar").click(crearSentenciadoReinsertar);
			$("#nuevoTutor").click(creaNuevoTutor);

			//Se agrega el evento click para consultar involucrados		
			$("#consultarDenuncianteUno").click(modificarDenunciante);
			$("#consultarRepresentanteLegalUno").click(modificarRepresentanteLegal);
			$("#consultarTraductorUno").click(modificarTraductor);
			$("#consultarProbResponsableUno").click(consultarProbResponsable);
			$("#consultarContactoDeUnaOrganizacionUno").click(consultarContactoDeUnaOrganizacion);
			$("#consultaVictimaUno").click(ConsultarVictimaUno);
			$("#consultaVictimaDos").click(ConsultarVictimaDos);


			//se agrega el evento click para ingresar hechos
			$("#ingresarHechos").click(ingresarHechos);

			//Se agrega el evento click para crear nuevos objetos
			$("#nuevoEquipoDeComputo").click(creaNuevoEquipoDeComputo);
			$("#nuevoEquipoTelefonico").click(creaNuevoEquipoTelefonico);
			$("#nuevaArma").click(creaNuevaArma);
			$("#nuevoExplosivo").click(creaNuevoExplosivo);
			$("#nuevaSustancia").click(creaNuevaSustancia);
			$("#nuevoAnimal").click(creaNuevoAnimal);
			$("#nuevoVehiculo").click(creaNuevoVehiculo);
			$("#nuevaAeronave").click(creaNuevaAeronave);
			$("#nuevaEmbarcacion").click(creaNuevaEmbarcacion);
			$("#nuevoInmueble").click(creaNuevoInmueble);
			$("#nuevoNumerario").click(creaNuevoNumerario);
			$("#nuevoVegetal").click(creaNuevoVegetal);
			$("#nuevoDocumentoOficial").click(creaNuevoDocumentoOficial);
			$("#nuevaJoya").click(creaNuevaJoya);
			$("#nuevaObraDeArte").click(creaNuevaObraDeArte);
			$("#asociarIndividuo").click(asociarIndividuo);
			$("#btnAdminMediCaute").click(mostrarVentanaInvolucradosCausa);
			
			$("#btnTranscripcionAudiencia").click(muestraSolicitudTranscripcion);
			
		
			//deshabilitamos el boton de Denuncia del tab Acciones
			$("#btnAccDenuncia").hide();//.attr("disabled", "disabled");
			$('a[name*="padre"]').click(function(event){
				var elem = $(this).next();

				if(elem.is('ul')){
					event.preventDefault();
					$('#menu ul:visible').not(elem).slideUp();
					elem.slideToggle();
					}
			});
			
			$("#consultaTestigo").click(consultarTestigo);

			var opNuevaDenuncia=<%=request.getAttribute("idNuevaDenuncia")%>;

			if(opNuevaDenuncia==1){
				$('#tabschild-op').show();
			}else{
				$('#tabschild-op').hide();
			}
			
//			$("#cbxEstatusExpediente").multiselect({ 
//				multiple: false, 
//				header: "Seleccione", 
//				position: { 
//					my: 'top', 
//					at: 'top' 
//				},
//				selectedList: 1 
//			});
			
//			$("#cbxAccionesTab").multiselect({ 
//				multiple: false, 
//				header: "Seleccione", 
//				position: { 
//					my: 'top', 
//					at: 'top' 
//				},
//				selectedList: 1,
//				close: function (event,ui){
//					seleccionaActuacion();} 
//			});

$("#cbxAccionesTab").change(function(e){
	seleccionaActuacion();
}); 
			//cargamos el combo para las actuaciones de policia ministerial
			$("#cbxAccionesTab9").change(function(e){
				seleccionaActuacionPolMin();
			}); 
			

			//$("#tdCbxAccionesTab").hide();
			//llenamos los combos de UI e IE de la pestaña de Acciones
			cargaInstitucionesExternas();
			cargaUnidadesInvestigacion();
			mostraDivGenerarOficioCanalizacion(0);
			$("#btnGenerarAcciones").click(muestraDivInformativoCanalizacion);
			$("#btnCanalizaAJR").click(muestraDivInformativoCanalizacion);
			$("#spanGralJAR,#spanGralUI,#spanGralIE,#btnGenerarAcciones").hide();

			var abrePenal='<%= request.getAttribute("respuestaPenal")%>';
			//prueba, 
			var abrePenal="";
			if(abrePenal!=null){
				//muestraMenuIntermedio(abrePenal);					
			}
			$("#btnGardadoDefini").click(validaGuardadoDefinitivo);
			$("#btnAccQuerella").hide();
			//seteamos los listener de los radios para la relacion de Delitos por Person o por Delito
			$("#rdbMenuInterRelDelXPersona").bind("click",ocultaMuestraTblsRelacionarDelitos);
			$("#rdbMenuInterRelDelXDelito").bind("click",ocultaMuestraTblsRelacionarDelitos);
			$("#rdbMenuInterRelDelXTodos").bind("click",ocultaMuestraTblsRelacionarDelitos);
			datosGenerales();
			
			//Seteo listener cadena de custodia
			$("#btnCadCusNuevaCadCus").click(asentarRegCadenaCustodia);
			$("#btnCadCusConsultaCadCus").click(consultarRegCadenaCustodia);
			//FIN Seteo listener cadena de custodia
			//consultamos las notas del expediente
			if(idNumeroExpedienteOp != '')
			{
				consultarNotas();
			}
			/*SECCION PARA DECLARAR LOS GRIDS DE LOS OBJETOS EN EL VISOR INTERMEDIO*/
			jQuery("#gridObjsVehiculo").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>', 
				datatype: "xml", 
				colNames:['Vehículo','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Vehiculo',index:'vehiculon', width:100}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200}, 
						],
				pager: jQuery('#pagerGridObjsVehiculo'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				caption:"VEHÍCULOS",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsVehiculo',
				ondblClickRow: function(id){
					consultarVehiculo(id);
					},
				sortorder: "desc"
			});
			//$("#gridObjsVehiculo").trigger("reloadGrid");
			
			//grid de arma
			jQuery("#gridObjsArma").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Arma','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Arma',index:'arman', width:100}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200}, 
						],
				pager: jQuery('#pagerGridObjsArma'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				caption:"ARMA",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsArma',
				ondblClickRow: function(id){
					consultarArma(id);
					},
				sortorder: "desc"
			});
			
			//Grid de equipo de computo
			jQuery("#gridObjsEquipoComputo").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Equipo de cómputo','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'EquipoComputo',index:'equipoComputon', width:100}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200}, 
						],
				pager: jQuery('#pagerGridObjsEquipoComputo'),
				rowNum:1,
				rowList:[10,20,30,40,50,60],
				autowidth: true,
				caption:"EQUIPO DE CÓMPUTO",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsVehiculo',
				ondblClickRow: function(id){
					consultarEquipoComputo(id);
					},
				sortorder: "desc"
			});
						
			//Instruccion pensada solo para el caso de policia ministerial
			if(deshabilitarCampos == true){
				$("#rdbMenuInterRelDelXTodos").attr("disabled","");
				$("#rdbMenuInterRelDelXPersona").attr("disabled","");
				$("#rdbMenuInterRelDelXDelito").attr("disabled","");
				$(":enabled").attr('disabled','disabled');
				$("#cbxAccionesTab").attr('disabled','');
				$("#botonGuardarNotas").show();
				$("#botonGuardarNotas").attr('disabled','');
				$("#idRadiosBUt").hide();
				$('#cbxAccionesTab').empty();
				$('#cbxAccionesTab').append('<option value="-1">-Seleccione-</option>');
				cargaActuaciones("2");
			}
			$("#btnTemporal").attr("disabled","");
			
			if(pantallaSolicitada==6){//policia ministerial
				$("#cbxAccionesTab9").attr("disabled","");
				$("#idInvestiga").attr("disabled","");
				cargaActuacionesPolMinisterial();
			}
			
			//Accion penal privada
			ocultaTabsAccPenalPrivada();
		});
		//Termina funcion on ready del documento
		
//***********************************************************ACCION PENAL PRIVADA****************************************************************************************/
		/*
		*Funcion que cierra la ventana visor de documentos
		*/
		function cerrarVentanaDocumento(){
			var pantalla ="iframewindowGenerarDocumento";
			$.closeWindow(pantalla);
		}
	
		function documentoGenerado(){
			//funcion para incluir alguna accion despues de haber sido generado el archivo digital (guardado definitivo)
		}
		
		/**
	  	* Oculta el tab para el dimicilio de notificaciones
	  	*/
	  	function ocultaTabsAccPenalPrivada(){
			//$("#tabs").tabs("option","disabled",[0]);
			
			$('#tabTabsVisitaduria').hide();
			$('#tabTabsCriterio').hide();
			$('#tabTabsDocs').hide();
			$('#tabTabsPeri').hide();
			$('#tabTabsPolMin').hide();
			$('#tabTabsCadCus').hide();
			$('#tabTabsAudiencias').hide();
			$('#tabTabsAlertas').hide();
			$('#tdCbxAjentesCoorUI1').hide();
			$('#tdCbxAjentesCoorUI').hide();
			$('#cbxAjentesCoorUI').hide();
			$('#idAsignarAgenteMp').hide();
			$('#idAsignarFacilitador').hide();			
	  	}
/*********************************************************************************************************************************************************************/
		
		//SECCION para llenar los grids de los objetos en el visor intermedio
		function consultaGridVehiculosVisor()
		{	
			jQuery("#gridObjsVehiculo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>',datatype: "xml" });
			$("#gridObjsVehiculo").trigger("reloadGrid");
		}
		
		function consultaGridArmasVisor()
		{	
			jQuery("#gridObjsArma").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.ARMA.getValorId() %>',datatype: "xml" });
			$("#gridObjsArma").trigger("reloadGrid");
		}
		
		function consultaGridEquiposComputoVisor()
		{	
			jQuery("#gridObjsEquipoComputo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>',datatype: "xml" });
			$("#gridObjsEquipoComputo").trigger("reloadGrid");
		}
		//FIN SECCION para llenar los grids de los objetos en el visor intermedio
		
		/*
		*Funcion para madar consultar la informacion de resumen de visitaduria
		*/
		function cargaInformacionDeResumenVisitaduira()
		{
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarInfoResumenVisitaduria.do',
	    		data: 'idExpediente='+idNumeroExpedienteConsul,
	    		dataType: 'xml',
	    		success: function(xml){
	    			$(xml).find('AuditoriaDTO').each(function(){
	    				$("#spanNombrDuenoExpAud").text($(this).find('nombreCompleto').text());
	    				$("#spanNumExpAud").text($(this).find('numeroExpediente').find('numeroExpediente').text());
	    				$("#spanAreaExpAud").text($(this).find('area').find('nombre').text());
	    				$("#spanFechaExpAud").text($(this).find('fechaAperturaNumeroExp').text());
	    				$("#spanEstatusExpAud").text($(this).find('estatus').find('valor').text());
	    			});
	    		}
	    	});
		}
		
		/*
		*Funcion que dispara el Action para consultar el los agentes mp
		*/		
	    function cargaAgenteMp(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarAgentesUI.do',
	    		data: '',
	    		dataType: 'xml',
	    		success: function(xml){
	    			var option;
	    			$(xml).find('funcionarioDTO').each(function(){
	    				$('#cbxAjentesCoorUI').append('<option value="' + $(this).find('claveFuncionario').text() + '">'+ $(this).find('nombreFuncionario').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
	    /*
		*Funcion que dispara el Action para consultar el los agentes mp
		*/		
	    function cargaAgenteJAR(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarAgentesJAR.do',
	    		data: '',
	    		dataType: 'xml',
	    		success: function(xml){
	    			var option;
	    			$(xml).find('funcionarioDTO').each(function(){
	    				$('#cbxAjentesCoorJAR').append('<option value="' + $(this).find('claveFuncionario').text() + '">'+ $(this).find('nombreFuncionario').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
		/****** listener cadena de Custodia  ****/
		function asentarRegCadenaCustodia()
		{
			idWindowAsntarRegCadCus++;
			$.newWindow({id:"iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Asentar registro de cadena de custodia", type:"iframe"});
		    $.updateWindowContent("iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=0&numeroExpediente='+numeroExpediente +' " width="1000" height="600" />');
		}
		
		function consultarRegCadenaCustodia()
		{
			idWindowAsntarRegCadCus++;
			$.newWindow({id:"iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Cadena de custodia", type:"iframe"});
		    $.updateWindowContent("iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=1&numeroExpediente='+numeroExpediente +' " width="1000" height="600" />');
		}
		/****** FIN listener cadena de Custodia  ****/
		
	function notaExpediente(idNota)
	{
		idWindowGenerarNotas++;
		$.newWindow({id:"iframewindowGenerarNotas" + idWindowGenerarNotas, statusBar: true, posx:200,posy:50,width:700, height:450,title:"Nota de Expediente", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarNotas" + idWindowGenerarNotas,'<iframe src="<%= request.getContextPath() %>/capturarNotaExpediente.do?idNumeroExpedienteOp='+idNumeroExpedienteOp +'&idNota='+idNota+' " width="700" height="450" />');
	}
	
	function cerrarVentanaNota(){
		var pantalla ="iframewindowGenerarNotas";
		pantalla += idWindowGenerarNotas;
		$.closeWindow(pantalla);
	}
	
	function cargaNota(id){
		var row=$('#rowNota_'+id);
		$(row).remove();
		$('#tablaNotasExpediente').append('<tr id="rowNota_'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarNota_'+id+'" onclick="notaExpediente('+id+')">Nota</a></td></tr>');
		//cerrarVentanaNota();
	} 

	/*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarVentanaInvolucradosCausa(){

		idWindowVisorMedidasCautelaresPJENC++;
		$.newWindow({id:"iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC, statusBar: true, posx:200,posy:50,width:970,height:480,title:"Visor de Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC,'<iframe src="<%=request.getContextPath()%>/visorMedidaCautelar.do?numeroCausa=' + numeroExpediente +'&op=1" width="970" height="480" />'); 
	}
	
	/*
	*Consultar las notas del expediente
	*POR EL MOMENTO SOLO SE CONSULTA UNA NOTA
	*/
	function consultarNotas(){
		var notas=$('#editor1').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarNotasExpediente.do?idNumeroExpediente='+idNumeroExpedienteOp,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('notaExpedienteDTO').each(function(){
					cargaNota($(this).find('idNota').text());
				});
			}
		});
	}
		
					
	/*
	*Funcion que realiza la carga del combo de Actuaciones
	*/
	function cargaActuaciones(id) {
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?id='+id+'&numeroExpediente='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAccionesTab').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
	//				$('#cbxAccionesTab').multiselect('refresh');
					});

                                
                               // $('#cbxAccionesTab').append('<option value="' + 67 + '">' + 'Solicitar Audiencia' + '</option>');
                                
			}
		});
	}
	
	/*
	*Funcion que realiza la carga del combo de Actuaciones de policia ministerial
	*/
	function cargaActuacionesPolMinisterial() {
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?idCategoria=<%= CategoriasActividad.POLICIAL.getValorId() %>&numeroExpediente='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAccionesTab9').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
	}

	function registrarActividadExpediente(actuacionID,estatusId,banderaMensaje){
		//Cambia la actividad del expediente
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registraActividadExpediente.do?idExpediente='+idExpedienteop+'&idNumeroExpediente='+idNumeroExpedienteOp+'&actuacion='+actuacionID+'&estatus='+estatusId+'&numExpe='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				if(parseInt(banderaMensaje)==1)
				{
					alertDinamico("Actividad nueva registrada");	
				}
				//$('#idNotasExpediente').val($(xml).find('notaExpedienteDTO').find('descripcion').text());
				//$(xml).find('notaExpedienteDTO').each(function(){
					//$('#idNotasExpediente').val($(this).find('descripcion').text());
				//});
			}
		});
	}

	function asignarAgenteMP(){
		//Sele colocara la funcion para signar agente ke aun no esta realizada
		var funcio=$('#cbxAjentesCoorUI option:selected').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registrafuncionarioNumeroExpediente.do?funcionario='+funcio+'&idNumeroExpediente='+idNumeroExpedienteOp,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				
			}
		});
		
		
		registrarActividadExpediente(151,1712,0);
		alertDinamico("Se asigno correctamente el expediente");
	}

	function asignarFacilitador()
	{
		var funcio=$('#cbxAjentesCoorJAR option:selected').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registrafuncionarioNumeroExpediente.do?funcionario='+funcio+'&idNumeroExpediente='+idNumeroExpedienteOp,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				
			}
		});
		
		//Sele colocara la funcion para signar agente ke aun no esta realizada
		registrarActividadExpediente(<%= Actividades.ATENDER_CANALIZACION_JAR.getValorId() %>,2542,0);
	alertDinamico("Se asigno correctamente el expediente");
	}
	
//******************************************************************************************************************************************************************************	
	var idWindowVisorAtnPublicoSol = 1;
	/*
	*Funcion que crea el visor de solicitudes para una nueva solicitud
	*/		
	function nuevaSolicitudPJATP() {

		var numeroDeCausa=numeroExpediente;
		var accPenalPrivada="true";
		
		idWindowVisorAtnPublicoSol++;
		$.newWindow({id:"iframewindowVisorAtnPublicoSolicitudes"+idWindowVisorAtnPublicoSol, statusBar: true, posx:255,posy:111,width:1000,height:400,title:"Atenci&oacute;n de Solicitudes", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorAtnPublicoSolicitudes"+idWindowVisorAtnPublicoSol,'<iframe src="<%= request.getContextPath() %>/crearNuevaSolicitud.do?numeroDeCausa='+numeroDeCausa+'&accPenal='+accPenalPrivada+'" width="1000" height="400" />');		
	}

	function cerrarVentanaNuevaSolicitud(){
		$.closeWindow('iframewindowVisorAtnPublicoSolicitudes'+idWindowVisorAtnPublicoSol);
	}
	//******************************************************************************************************************************************************************************
	
	function seleccionaActuacion(){
		var selected = $("#cbxAccionesTab option:selected");
		var confActividadId=selected.val();
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+confActividadId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
		actuacion=actividad;
		if( actividad=='<%= Actividades.GENERAR_QUERELLA.getValorId() %>' || actividad=='<%= Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId() %>'){
			//$.ajax({
			//	type: 'POST',
			//	url: 'url: '<%= request.getContextPath()%>/enviarReplicaCaso.do?idExpediente='+idExpedienteop,',
			//	data: '',
			//	dataType: 'xml',
			//	async: true,
			//	success: function(xml){
			//	}
			//});
		}
		
		if(usaeditor== "true"){
			//if(actividad=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS.getValorId() %>' || actividad =='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES.getValorId() %>' || actividad=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES.getValorId() %>'){
				registrarActividadExpediente(actividad,estatusId,0);
			//}
			if (actividad == '<%= Actividades.SOLICITAR_AUDIENCIA.getValorId() %>') {
				nuevaSolicitudPJATP();
               	// $.newWindow({id:"iframewindowSolicitarAudiencia", statusBar: true, posx:20,posy:20,width:740,height:520,title:"Solicitar Audiencia", type:"iframe"});
                //$.updateWindowContent("iframewindowSolicitarAudiencia",'<iframe src="<%= request.getContextPath() %>/solicitarAudiencia.jsp?idNumeroExpediente=' + idNumeroExpedienteOp + '&idExpedienteSoli='+ idExpedienteop+'&numeroExpediente='+numeroExpediente+'"    width="750" height="570" />');
			}
			else if(actividad=='<%= Actividades.SOLICITAR_ATENCION_PSICOLOGICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>')
			{
				//verificamos si se tienen relaciones de delito-persona o delito-delito
				if(consultaTotalRelacionesDelitoPorTodos()>0)
				{
					registrarActividadExpediente(actividad,estatusId,0);
					$.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:940,height:350,title:"Solicitar Audiencia", type:"iframe"});
		            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitarAyudaPsicologicaUAVD.do?formaId='+formaID+'&idExpedienteop='+idExpedienteop+'&numeroCaso='+numeroCaso+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
				}
				else
				{
					alertDinamico("Debe tener registrada la relación de la víctima \n con el probable responsable y el delito");
				}
			}
			else if(actividad=='<%= Actividades.SOLICITAR_ATENCION_MEDICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>' || actividad=='<%= Actividades.SOLICITAR_ORIENTACION_LEGAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>' || actividad=='<%= Actividades.SOLICITAR_SEGURIDAD_POLICIAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>')
			{
				registrarActividadExpediente(actividad,estatusId,0);
				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
                $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
			}
			else if(actividad=='<%= Actividades.SOLICITAR_DILIGENCIAS_MINISTERIALES.getValorId() %>'){
	            $.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar Diligencia", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitudDiligenciaJSP.do"    width="1140" height="550" />');
	     	}
			else if(actividad=='<%= Actividades.SOLICITAR_SERVICIO_PERICIAL.getValorId() %>'){
				 // Variable para definir el área de donde proviene la solicitud.
				 // Para Procuraduria el valor es 1
				 // Para Defensoria el valor es 2
				var area = 1;			
	            $.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar servicio pericial", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitarServicioPericial.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
	     	}else if(actividad=='<%= Actividades.ELABORAR_OFICIO_INVESTIGACION_POLICIACA.getValorId() %>'){
				 var area = 1;			
	            $.newWindow({id:"iframewindowSolicitudPolicia", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar servicio policial", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitudPolicia",'<iframe src="<%= request.getContextPath() %>/solicitarServicioPolocial.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
	     	}
			else if(actividad=='<%= Actividades.GENERAR_CONVENIO_DE_CONCILIACION_MEDIACION.getValorId() %>')
			{
				idWindowGenConvenio++;
				$.newWindow({id:"iframewindowGenConvenio"+idWindowGenConvenio, statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Generar Convenio", type:"iframe"});
	            $.updateWindowContent("iframewindowGenConvenio"+idWindowGenConvenio,'<iframe src="<%= request.getContextPath() %>/generarConvenio.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
			}
			else if(actividad=='<%= Actividades.GENERAR_CONSTANCIA_DE_SEGUIMIENTO_A_CONVENIO.getValorId() %>')
 			{
 				idWindowGenConvenio++;
 				$.newWindow({id:"iframewindowGenConvenio"+idWindowGenConvenio, statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Seguimiento Convenio", type:"iframe"});
 	            $.updateWindowContent("iframewindowGenConvenio"+idWindowGenConvenio,'<iframe src="<%= request.getContextPath() %>/generarSeguimientoConvenio.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
 			}
			else{
     			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
    		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
             }
		}else{
			document.frmDoc2.numeroUnicoExpediente.value = numeroExpediente;
			document.frmDoc2.formaId.value = formaID;
			document.frmDoc2.submit();
		}
		$('#cbxAccionesTab').empty();
		$('#cbxAccionesTab').append('<option value="-1">-Seleccione-</option>');
		if(pantallaSolicitada=="4"){
			cargaActuaciones("3");
		}else if(pantallaSolicitada=="5"){
			cargaActuaciones("2");
		}else{
			cargaActuaciones(pantallaSolicitada);
		}
		

//Se empezara acomentar ya que este código sera remplazado por el de arriba

		//14
//		if(selected.val()=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS.getValorId() %>'){
//			titulo="Oficio de canalización a Justicia Alternativa Restaurativa";
//			formaID=6;
//			registrarActividadExpediente(selected.val());
//		}else if(selected.val()=='<%= Actividades.CAPTURAR_DECLARACION.getValorId() %>'){
//			titulo="Declaración";
//		}else if(selected.val()=="13"){//13
//			titulo="Acta de no inicio de investigación";
//		}else if(selected.val()=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES.getValorId() %>'){
//			titulo="Ficha de canalización a Unidad de Investigación";
//			formaID=6;
//			registrarActividadExpediente(selected.val());
//		}else if(selected.val()=='<%= Actividades.DIRIGIR_A_INSTANCIA_EXTERNA.getValorId() %>'){
//			titulo="Oficio de canalización a institución externa";
//			formaID=6;
//		}else if(selected.val()=='<%= Actividades.GENERAR_OFICIO_DE_SOLICITUD_DE_ATENCION_PSICOLOGICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//			titulo="Oficio de solicitud de atención psicológica";
//		}else if(selected.val()=='<%= Actividades.GENERAR_OFICIO_DE_SOLICITUD_DE_ATENCION_MEDICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//			titulo="Oficio de solicitud de atención médica";
//		}
//		else if(selected.val()=='<%= Actividades.GENERAR_OFICIO_DE_SOLICITUD_DE_ASESORIA_JURIDICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//			titulo="Oficio de solicitud de orientación legal";
//		}
//		else if(selected.val()=='<%= Actividades.GENERAR_OFICIO_DE_SOLICITUD_DE_SEGURIDAD_POLICIAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//			titulo="Oficio de solicitud de seguridad policial";
//		}else if(selected.val()=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES.getValorId() %>'){
//			titulo="Ficha de canalización a Unidad de Investigación";
//			formaID=6;
//			registrarActividadExpediente(selected.val());
////		}else if(selected.val()=="18"){
////			titulo="Entrevista inicial";
//		}else if(selected.val()=="19"){
//			titulo="Entrevista complementaria";
//		}else if(selected.val()=='<%= Actividades.GENERAR_CONSTANCIA_DE_FALTA_DE_INTERES.getValorId() %>'){
//			titulo="Constancia de falta de interés";
//		}
//		else if(selected.val()=='<%= Actividades.ACEPTACION_DEL_SERVICIO_POR_PARTE_DE_LA_VICTIMA.getValorId() %>'){
//			titulo="Carta de aceptación del servicio por parte de la víctima";
//		}
//		else if(selected.val()=='<%= Actividades.ACEPTACION_DEL_SERVICIO_POR_PARTE_DEL_INVITADO.getValorId() %>'){
//			titulo="Carta de aceptación del servicio por parte del invitado";
//		}
//		else if(selected.val()=='<%= Actividades.LLEVAR_A_CABO_LA_AUDIENCIA_DE_CONCILIACION_MEDIACION.getValorId() %>'){
//			titulo="Convenio de confidencialidad";
//		}
//		else if(selected.val()=='<%= Actividades.GENERAR_CONVENIO_DE_CONCILIACION_MEDIACION.getValorId() %>'){
//			titulo="Convenio de Justicia alternativa restaurativa";
////		}
////		else if(selected.val()=="27"){
////			titulo="Convenio de Justicia alternativa restaurativa para Unidad de Investigación";
//		}
//		else if(selected.val()=='<%= Actividades.ELABORAR_CARTA_DE_CUMPLIMIENTO_DE_ACUERDO.getValorId() %>'){
//			titulo="Carta de cumplimiento de acuerdo";
//		}
//		else if(selected.val()=='<%= Actividades.ELABORAR_CARTA_DE_FELICITACION.getValorId() %>'){
//			titulo="Carta de felicitación";
//		}
//		else if(selected.val()=='<%= Actividades.DIRIGIR_A_LA_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS.getValorId() %>'){
//			titulo="Oficio de canalización a Justicia Alternativa Restaurativa";
//			formaID=6;
//		}
//		else if(selected.val()=='<%= Actividades.DIRIGIR_A_INSTANCIA_EXTERNA.getValorId() %>'){
//			titulo="Oficio de canalización a institución externa";
//			formaID=6;
//		}
//		else if(selected.val()=='<%= Actividades.REGRESAR_A_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS.getValorId() %>'){
//			titulo="Oficio de canalización a Justicia Alternativa Restaurativa";
//			formaID=6;
//		}else if(selected.val()=="41"){
//			titulo="Acuerdo de retención";
//			formaID=20;
//		}
	

//		if(selected.val()=='<%= Actividades.GENERAR_QUERELLA.getValorId() %>' || selected.val()=='<%= Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId() %>' || selected.val()=="32" || selected.val()=="33" 
 //                   || selected.val() == '<%= Actividades.SOLICITAR_AUDIENCIA.getValorId() %>'){
  //                  if (selected.val() == '<%= Actividades.SOLICITAR_AUDIENCIA.getValorId() %>') {
  //                      $.newWindow({id:"iframewindowSolicitarAudiencia", statusBar: true, posx:20,posy:20,width:740,height:520,title:"Solicitar Audiencia", type:"iframe"});
   //                     $.updateWindowContent("iframewindowSolicitarAudiencia",'<iframe src="<%= request.getContextPath() %>/solicitarAudiencia.jsp?numeroExpediente=' + numeroExpediente + '"    width="750" height="570" />');
    //                }else{
                    	
//							                        
//						formaID=1;
//						document.frmDoc2.numeroUnicoExpediente.value = numeroExpediente;
//						document.frmDoc2.formaId.value = formaID;
//						document.frmDoc2.submit();

//                    }
//		}else{
//			if(selected.val()=='<%= Actividades.SOLICITAR_ATENCION_PSICOLOGICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//				formaID=31;
//				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
 //               $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
//			}else if(selected.val()=='<%= Actividades.SOLICITAR_ATENCION_MEDICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//				formaID=32;
//				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
//                $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
//			}else if(selected.val()=='<%= Actividades.SOLICITAR_ORIENTACION_LEGAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>' ){
//				formaID=33;
//				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
//                $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
//			}else if(selected.val()=='<%= Actividades.SOLICITAR_SEGURIDAD_POLICIAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>'){
//				formaID=34;
//				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
//                $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');	
//             }else if(selected.val()!='<%= Actividades.SOLICITAR_SERVICIO_PERICIAL.getValorId() %>' && selected.val()!='<%= Actividades.SOLICITAR_DILIGENCIAS_MINISTERIALES.getValorId() %>'){
//     			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
//    		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
//             }			
//		}	

		
	}

	/** selecciona actuacion para policia ministerial*/
	function seleccionaActuacionPolMin(){
		var selected = $("#cbxAccionesTab9 option:selected");
		var confActividadId=selected.val();
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+confActividadId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
		actuacion=actividad;
		
		if(actividad=='<%= Actividades.ELABORAR_OFICIO_INVESTIGACION_POLICIAL.getValorId() %>'){
			 registrarActividadExpediente(actividad,estatusId,0);
			 var tipoSolicitud=<%= TiposSolicitudes.POLICIA_MINISTERIAL.getValorId() %>;
			 var area = 1;
			 $.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
             $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'&idTipoSolicitud='+tipoSolicitud+' "    width="1140" height="550" />');
		}

	}
	/**FIN seleccion de actuacion de policia ministerial**/
	
	
		function muestraMenuQuienDetuvo(){
			ocultaMuestraTabVisor("tabTabsQuienDetuvo",1);
		}

		function documentoGenerado(){
			//funcion para incluir alguna accion despues de haber sido generado el archivo digital (guardado definitivo)
		}
		
		/*
		*Seteamos la bandera cuando el usuario seleccione el tipo de denuncia o querella
		* en la pestaña de Generales
		*/
		function seteaBanderaTipoSelected()
		{
			isTipoSelected=true;
		}
		//Carga el menu de probable responsable con la consulta y un boton para agregar 
		function cargarMenu(){
			$('#tblProbableResponsable').empty();
			//$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable">Ingreso nuevo</a></td></tr>');
			$('#tblProbableResponsable').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoProbResponsable" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblProbableResponsable').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>' + '/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=0',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarProbableResponsable(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblProbableResponsable').append(liga);
					  });
		    	  }
		    });
			/*for(var i=0;i<10;i++){
				$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno" onclick="consultarProbableResponsable();">Probable responsable '+ i +'</a></td></tr>');
			}*/
		}

		function cargaProbableResponsable(nombre,id){
			var row=$('#'+id);
			$(row).remove();
			nombre=nombre+"- ";
			$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificaProbableResponsable('+id+')">'+nombre+'<bean:message key="indiciado" /></a></td></tr>');
			
		} 

		function modificaProbableResponsable(id){
			modificarProbResponsable(id);
		}

		//Carga el menu de victima con la consulta y un boton para agregar 
		function cargarMenuVictima(){
			$('#tblVictima').empty();
			//$('#tblVictima').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima">Ingreso nuevo</a></td></tr>');
			$('#tblVictima').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaVictima" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblVictima').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=2',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarVictima(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblVictima').append(liga);
					  });
		    	  }
		    });
		}

		function cargaVictima(nombre,id){
			var row=$('#'+id);
			$(row).remove();
			$('#tblVictima').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaVictima('+id+')">'+nombre+'</a></td></tr>');
			//$('#nuevaVictima').hide();
		} 
		function cargaVictimaDenunciante(nombre,id){
			var row=$('#v'+id);
			$(row).remove();
			$('#tblVictima').append('<tr id="v'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
			//$('#nuevaVictima').hide();  modificaDenunciante
		} 

		function modificaVictima(id){
			modificarVictima(id);
		}
		
		function regsitraEstatus(estatus){
			//Cambia la actividad del expediente
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/registraStatusExpediente.do?idExpediente='+idExpedienteop+'&idNumeroExpediente='+idNumeroExpedienteOp+'&estatus='+estatus,
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
						
				}
			});
		}

		
		//Carga el menu de denunciante con la consulta y un boton para agregar 
//		function cargarMenuDenunciante(){
//			$('#tblDenunciante').empty();
//			//$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante">Ingreso nuevo</a></td></tr>');
//			$('#tblDenunciante').append('<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="crearDenunciante" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
//			$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarDenuncianteUno">Denunciante uno</a></td></tr>');
//			$.ajax({
//		    	  type: 'POST',
//		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
//		    	  data: 'calidadDelIndividuo=4',
//		    	  dataType: 'xml',
//		    	  async: false,
//		    	  success: function(xml){
//		    	      $(xml).find('involucradoViewDTO').each(function(){
//			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="consultarDenunciante(' + $(this).find('involucradoId').text() + ');">';
//						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
//						  liga += '</a></td></tr>';
//		    	    	  $('#tblDenunciante').append(liga);
//					  });
//		    	  }
//		    });
//		}
		
		function cargarInvolucradosExpediente(idNumeroExpediente){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaInvolucradosExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    	      $(xml).find('involucradoViewDTO').each(function(){
		    	      if($(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE_ORGANIZACION.getValorId() %>'){
							var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
		    	      	  if($(this).find('nombre').text()=='null'){
							  liga += 'Anónimo';
						  }else if($(this).find('nombre').text()==''){
							  liga += 'Anónimo';
						  }	else{
							  liga += $(this).find('nombre').text();
							}	
						  liga += '</a></td></tr>';
		    	    	  $('#tblDenunciante').append(liga);
		    	    	  $('#crearDenunciante').css('display','none'); //.attr("disabled", "disabled");
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.VICTIMA_PERSONA.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE.getValorId() %>'){
		    	    	  if($(this).find('esVictima').text()=="true"){
								var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
								if($(this).find('nombre').text()=='null'){
									  liga += 'Desconocido';
								  }else{
									  liga += $(this).find('nombre').text();
								  }
								  liga += '</a></td></tr>';
				    	    	  $('#tblVictima').append(liga);
						}else if($(this).find('calidad').text() == '<%= Calidades.VICTIMA_PERSONA.getValorId() %>'){
				    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaVictima(' + $(this).find('involucradoId').text() + ');">';
				    	      if($(this).find('nombre').text()=='null'){
								  liga += 'Desconocido';
							  }else{
								  liga += $(this).find('nombre').text();
							  }
							  liga += '</a></td></tr>';
			    	    	  $('#tblVictima').append(liga);
						}
				    	  
		    	      }
		    	      
		    	      if($(this).find('calidad').text() == '<%= Calidades.DEFENSOR_PUBLICO.getValorId() %>' ||	$(this).find('calidad').text() == '<%= Calidades.DEFENSOR_PRIVADO.getValorId() %>'){
		    	    	  idDefensor=$(this).find('involucradoId').text();
			    	    	  nombreDefensor=$(this).find('nombre').text();
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() %>'){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaProbableResponsable(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblProbableResponsable').append(liga);
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.TESTIGO.getValorId() %>'){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaTestigo(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblTestigo').append(liga);
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.TRADUCTOR.getValorId() %>'){
		    	    	  var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaTraductor(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblTraductor').append(liga);
		    	      }
		    	      
	    	      });
	    		}	
	    	});
		}	
		
		/*
		*Funcion para pintar los objetos expediente en su tab correspondiente
		*/
		function cargarObjetosExpediente(idNumeroExpediente){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaObjetosExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		cache: false,
	    		success: function(xml){
	    			if(parseInt($(xml).find('code').text())==0)
		    		{
	    					//$('#tblVehiculo').empty();
	    					//$('#tblEquipoComputo').empty();
	    					//$('#tblEquipoTelefonico').empty();
	    					//$('#tblArma').empty();
			    	    
	    				  //$(xml).find('VehiculoDTO').each(function(){
			    	      //	  cargaVehiculo($(this).find('elementoId').text(),$(this).find('valorByTipoVehiculo').find('valor').text(),$(this).find('placa').text());
			    	      //});
			    	      
			    	      //$(xml).find('EquipoComputoDTO').each(function(){
			    	      //	  cargaEquipoComputo($(this).find('elementoId').text(),$(this).find('tipoEquipo').find('valor').text());
			    	      //});
			    	      
			    	      $(xml).find('TelefoniaDTO').each(function(){
			    	    	  cargaEquipoTelefonico($(this).find('elementoId').text(),$(this).find('tipoTelefono').find('valor').text());
			    	      });
			    	      
			    	      //$(xml).find('ArmaDTO').each(function(){
			    	      //	  cargaArma($(this).find('elementoId').text(),$(this).find('tipoArma').find('valor').text());
			    	      //});
			    	      
			    	      $(xml).find('ExplosivoDTO').each(function(){
			    	    	  cargaExplosivo($(this).find('elementoId').text(),$(this).find('tipoExplosivo').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('SustanciaDTO').each(function(){
			    	    	  cargaSustancia($(this).find('elementoId').text(),$(this).find('tipoSustancia').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('AnimalDTO').each(function(){
			    	    	  cargaAnimal($(this).find('elementoId').text(),$(this).find('tipoAnimal').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('AeronaveDTO').each(function(){
			    	    	  cargaAeronave($(this).find('elementoId').text(),$(this).find('tipoAeroNave').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('EmbarcacionDTO').each(function(){
			    	    	  cargaEmbarcacion($(this).find('elementoId').text(),$(this).find('tipoEmbarcacion').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('NumerarioDTO').each(function(){
			    	    	  cargaNumerario($(this).find('elementoId').text(),$(this).find('moneda').text());
			    	      });
			    	      
			    	      $(xml).find('VegetalDTO').each(function(){
			    	    	  cargaVegetal($(this).find('elementoId').text(),$(this).find('tipoVegetal').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('DocumentoOficialDTO').each(function(){
			    	    	  cargaDocOfic($(this).find('elementoId').text(),$(this).find('tipoDocumento').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('JoyaDTO').each(function(){
			    	    	  cargaJoya($(this).find('elementoId').text(),$(this).find('tipoJoya').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('ObraArteDTO').each(function(){
			    	    	  cargaObraDeArte($(this).find('elementoId').text(),$(this).find('tipoObraArte').find('valor').text());
			    	      });
			    	      
			    	      
			    	    //deshabilitamos los botones de guardado
			    	    
			    	    /*if(ingresoDenuncia=='true')
			    	    {
			    	      $('#nuevoEquipoDeComputo').hide();
		    	    	  $('#nuevoEquipoTelefonico').hide();
		    	    	  $('#nuevaArma').hide();
		    	    	  $('#nuevoExplosivo').hide();
		    	    	  $('#nuevaSustancia').hide();
		    	    	  $('#nuevoAnimal').hide();
		    	    	  $('#nuevoVehiculo').hide();
		    	    	  $('#nuevaAeronave').hide();
		    	    	  $('#nuevaEmbarcacion').hide();
		    	    	  $('#nuevoNumerario').hide();
		    	    	  $('#nuevoVegetal').hide();
		    	    	  $('#nuevoDocumentoOficial').hide();
		    	    	  $('#nuevaJoya').hide();
		    	    	  $('#nuevaObraDeArte').hide();
			    	    }
			    	    else
			    	    {
			    	    	$('#nuevoEquipoDeComputo').show();
			    	    	  $('#nuevoEquipoTelefonico').show();
			    	    	  $('#nuevaArma').show();
			    	    	  $('#nuevoExplosivo').show();
			    	    	  $('#nuevaSustancia').show();
			    	    	  $('#nuevoAnimal').show();
			    	    	  $('#nuevoVehiculo').show();
			    	    	  $('#nuevaAeronave').show();
			    	    	  $('#nuevaEmbarcacion').show();
			    	    	  $('#nuevoNumerario').show();
			    	    	  $('#nuevoVegetal').show();
			    	    	  $('#nuevoDocumentoOficial').show();
			    	    	  $('#nuevaJoya').show();
			    	    	  $('#nuevaObraDeArte').show();
			    	    }*/
		    		}  
	    		}	
	    	});
		}
		
		/*
		*Funcion para pintar el hecho del expediente en su tab correspondiente
		*/
		function cargarHechoExpediente(idNumeroExpediente){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			if(parseInt($(xml).find('code').text())==0)
		    		{
			    	      $(xml).find('hechoDTO').each(function(){
			    	    	  var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarHecho(' + $(this).find('hechoId').text() + ','+idNumeroExpediente+');">';
							  liga += "Hecho";
							  liga += '</a></td></tr>';
			    	    	  $('#tableHecho').append(liga);
			    	    	//deshabilitamos el boton de guardado
			    	    	  $('#ingresarHechos').css('display','none');
			    	      });
		    		}  
	    		}	
	    	});
		}		

		//Carga el menu de denunciante con la consulta y un boton para agregar 
		function cargarMenuDenunciante(){
			$('#tblDenunciante').empty();
			$('#tblDenunciante').append('<tr><td ><input type="button" id="crearDenunciante" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=4',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var idIn=$(this).find('involucradoId').text();
			    	      var liga = '<tr id="' + idIn + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblDenunciante').append(liga);
					  });
		    	  }
		    });
		}

		function cargaDenunciante(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblDenunciante').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarDenunciante" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
			$('#crearDenunciante').hide();
		} 

		function modificaDenunciante(id){
			modificarDenuncianteDatos(id);
		}

		//Carga el menu de testigo con la consulta y un boton para agregar 
		function cargarMenuTestigo(){
			$('#tblTestigo').empty();
			//$('#tblTestigo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo">Ingreso nuevo</a></td></tr>');
			$('#tblTestigo').append('<tr><td>&nbsp;&nbsp;<input type="button" id="nuevoTestigo" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTestigo').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultaTestigo">Testigo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=5',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarTestigo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTestigo').append(liga);
					  });
		    	  }
		    });
		}

		function cargaTestigo(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblTestigo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTestigo" onclick="modificaTestigo('+id+')">'+nombre+'</a></td></tr>');
			
		} 

		function modificaTestigo(id){
			modificarTestigo(id);
		}
		
		//Carga el menu de traductor con la consulta y un boton para agregar 
		function cargarMenuTraductor(){
			$('#tblTraductor').empty();
			//$('#tblTraductor').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor">Ingreso nuevo</a></td></tr>');
			$('#tblTraductor').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTraductor').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTraductorUno">Traductor uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=7',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarTraductor(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTraductor').append(liga);
					  });
		    	  }
		    });
		}

		function cargaTraductor(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblTraductor').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTraductor" onclick="modificaTraductor('+id+')">'+nombre+'</a></td></tr>');
			
		} 

		function modificaTraductor(id){
			modificarTraductor(id);
		}
		

		//Carga el menu de Quien detuvo con la consulta y un boton para agregar 
		function cargarMenuQuienDetuvo(){
			$('#tblQuienDetuvo').empty();
			//$('#tblQuienDetuvo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Ingreso nuevo</a></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a>Quien detuvo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=8',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarQuienDetuvo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblQuienDetuvo').append(liga);
					  });
		    	  }
		    });
		}

		function cargaQuienDetuvo(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblQuienDetuvo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarQuienDetuvo" onclick="">'+nombre+'</a></td></tr>');
			cerrarVentanaQuienDetuvo();
		} 
		
		function cargaIngresoHecho(nombre,id){
			$("#ingresarHechos").attr("disabled","disabled");
			$('#tableHecho').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="hecho_'+id+'" onclick="consultarHecho('+id+','+idNumeroExpedienteOp+');">'+nombre+'</a></td></tr>');
			//cerrarVentanaQuienDetuvo();
		} 

		function cerrarVentanaQuienDetuvo(){
		    var pantalla ="iframewindowQuienDetuvo";
			pantalla += idWindowIngresarQuienDetuvo;
			$.closeWindow(pantalla);
		}
		
		//Abre una nueva ventana de consulta probable responsable  
		function consultarProbResponsable(){
			idWindowConsultarProbResponsable++;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Probable Responsable", type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1000&idCalidad=PROBABLE_RESPONSABLE" width="1100" height="530" />');		
		}


		//Abre una ventana de problable responsable
		function consultarProbableResponsable(idInvolucrado){
			idWindowConsultarProbResponsable++;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=PROBABLE_RESPONSABLE" width="1100" height="530" />');
		}

		//Abre una nueva ventana para consultar una víctima		
		function consultarVictima(idInvolucrado){
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Victima", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=VICTIMA" width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de denunciante		
		function consultarDenunciante(idInvolucrado){
			idWindowConsultarDenunciante++;
			$.newWindow({id:"iframewindowConsultarDenunciante" + idWindowConsultarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Denunciante", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarDenunciante" + idWindowConsultarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=DENUNCIANTE" width="1100" height="530"/>');
		}

		//Abre una nueva ventana de consulta de testigo		
		function consultarTestigo() {
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TESTIGO" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de consulta de testigo
		function consultarTestigo(idInvolucrado){
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Testigo", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TESTIGO" width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de traductor
		function consultarTraductor(idInvolucrado){
			idWindowConsultarTraductor++;
			$.newWindow({id:"iframewindowConsultarTraductor" + idWindowConsultarTraductor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Traductor", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTraductor" + idWindowConsultarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TRADUCTOR"width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de organizacion		
		function consultarContactoDeUnaOrganizacion() {
			
			idWindowConsultarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Consultar contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1001&idCalidad=CONTACTO_ORGANIZACION" width="1100" height="530" />');		
		}

		//No existe la pantalla para consulta de quien detuvo
		function consultarQuienDetuvo(idInvolucrado){
			alertDinamico('Aún no hay pantalla para el involucrado QUIEN DETUVO ');
		}

		//Abre una nueva ventana de crear una nuev victima
		function creaNuevaVictima() {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Víctima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de crear una nuev victima
		function modificarVictima(id) {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Víctima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?idVictima='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de probable responsable
		function creaNuevoProbResponsable() {
			//numeroExpediente='<%= request.getAttribute("numeroExpediente")%>';
			idWindowIngresarProbResponsable++;
			$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?numeroExpediente='+numeroExpediente +'&calidadInv=PROBABLE_RESPONSABLE&idDefensor='+idDefensor+'&detenido='+muestraDetenido+'" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de probable responsable
		function modificarProbResponsable(id) {
			idWindowIngresarProbResponsable++;
			$.newWindow({id:"iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpediente +'&detenido='+muestraDetenido+'" width="1100" height="530" />');			
		}
		
		//Abre una nueva ventana de Denunciante
		function crearDenunciante(){
			$('#crearDenunciante').hide();
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroExpediente +'&calidadInv=DENUNCIANTE" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de Denunciante
		function modificarDenuncianteDatos(id){
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}
			

		//Crea una nueva ventana de testigo
		function creaNuevoTestigo() {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}

		//Crea una nueva ventana de testigo
		function modificarTestigo(id) {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?idTestigo='+id+'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}
		
		//Crea una ventana de un nuevo contacti de una organizacion		
		function creaNuevoContactoDeUnaOrganizacion() {
			
			idWindowIngresarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do" width="1100" height="530"  />');		
		}

		//crea una nueva ventana de ingresar tutor
		function creaNuevoTutor() {
			idWindowIngresarTutor++;
			$.newWindow({id:"iframewindowTutor" + idWindowIngresarTutor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Datos Generales", type:"iframe"});
		    $.updateWindowContent("iframewindowTutor" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
		}

		//Abre una nueva ventana de ingresar traductor
		function creaNuevoTraductor() {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Traductor - Int&eacute;rprete", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
		}	

		//Abre una nueva ventana de ingresar traductor
		function modificarTraductor(id) {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Modificar Traductor - Int&eacute;rprete", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?idTraductor='+id+'" width="1050" height="600" />');		
		}	
		
		//Abre una nueva ventana de ingresar quien detuvo
		function creaQuienDetuvo() {
			idWindowIngresarQuienDetuvo++;
		$.newWindow({id:"iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Quién detuvo", type:"iframe"});
	    $.updateWindowContent("iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo,'<iframe src="<%= request.getContextPath() %>/IngresarQuienDetuvo.do?elemento='+0+'&numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');
		}	

		//Abre una nueva ventana de modificar denunciante		
		function modificarDenunciante(){
			
			idWindowModificarDenunciante++;
			$.newWindow({id:"iframewindowModificarDenunciante" + idWindowModificarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Modificar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarDenunciante" + idWindowModificarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=DENUNCIANTE" width="1040" height="620" />');		
		}

		//Abre una nueva ventana de modificar traductor
		//function modificarTraductor(){
		//	idWindowModificarTraductor++;
		//	$.newWindow({id:"iframewindowModificarTraductor" + idWindowModificarTraductor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Traductor", type:"iframe"});
		//    $.updateWindowContent("iframewindowModificarTraductor" + idWindowModificarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TRADUCTOR" width="1040" height="570" />');		
		//}

		
		function creaNuevoRepresentanteLegal() {
			idWindowIngresarRepresentanteLegal++;
		$.newWindow({id:"iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal, statusBar: true,posx:75,posy:30,width:1100,height:530,title:"Ingresar Representante Legal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do" width="1050" height="600" />');		
		}	

		
		function modificarRepresentanteLegal(){
			
			idWindowModificarRepresentanteLegal++;
			$.newWindow({id:"iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Representante Legal", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');		
		}

		
		function crearSentenciadoReinsertar() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar nuevo sentenciado a reinsertar", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/IngresarSentenciadoReinsertar.do" width="1050" height="600" />');		
		}
		

		function ConsultarVictimaUno() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=VICTIMA_PERSONA" width="1050" height="600" />');		
		}

		
		function ConsultarVictimaDos() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1300&idCalidad=VICTIMA" width="1050" height="600" />');		
		}
			//  Inician funciones para crear ventanas de Objetos
			
		function creaNuevoEquipoDeComputo(){
			idWindowIngresarEquipoDeComputo++;
			$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Ingresar equipo de c&oacute;mputo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente +'&idEquipoComputo=0&tipoObjeto=EQUIPO_COMPUTO" width="830" height="340" />');
		}
		
		function consultarEquipoComputo(idEquipoComputo){
			idWindowIngresarEquipoDeComputo++;
			$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Consultar equipo de c&oacute;mputo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente+'&idEquipoComputo='+idEquipoComputo+'&tipoObjeto=EQUIPO_COMPUTO " width="830" height="340" />');
		}
		
		function cargaEquipoComputo(id,tipo){
			consultaGridEquiposComputoVisor();
			cerrarVentanaEquipoComputo();
		} 
		
		function cerrarVentanaEquipoComputo(){
			var pantalla ="iframewindowIngresarEquipoDeComputo";
			pantalla += idWindowIngresarEquipoDeComputo;
			$.closeWindow(pantalla);
		}
			
		function creaNuevoEquipoTelefonico(){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico=0&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
		}
		
		function consultarEquipoTelefonico(idEquipoTelefonico){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Consultar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico='+idEquipoTelefonico+'&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
		}

		function cargaEquipoTelefonico(id,modelo){
			$('#tblEquipoTelefonico').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarEquipoTelefonico_'+id+'" onclick="consultarEquipoTelefonico('+id+')">'+modelo+'</a></td></tr>');
			cerrarVentanaEquipoTelefonico();
		} 
		
		function cerrarVentanaEquipoTelefonico(){
			var pantalla ="iframewindowIngresarEquipoTelefonico";
			pantalla += idWindowIngresarEquipoTelefonico;
			$.closeWindow(pantalla);
		}
		 
		function creaNuevaArma(){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma=0&tipoObjeto=ARMA " width="800" height="380" />');
		}

		function consultarArma(idArma){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Consultar arma", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma='+idArma+'&tipoObjeto=ARMA" width="800" height="380" />');
		}
		
		function cargaArma(id,tipo){
			consultaGridArmasVisor();
			cerrarVentanaArma();
		} 
		
		function cerrarVentanaArma(){
			var pantalla ="iframewindowIngresarArma";
			pantalla += idWindowIngresarArma;
			$.closeWindow(pantalla);
		}
		
		function creaNuevoExplosivo(){
			 idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=EXPLOSIVO&idExplosivo=0" width="880" height="330" />');
		}
		
		function consultarExplosivo(idExplosivo){
			idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880, height:330,title:"Consultar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&idExplosivo='+idExplosivo+'&tipoObjeto=EXPLOSIVO " width="880" height="330" />');
		}

		function cargaExplosivo(id,tipo){
			var row=$('#consultarExplosivo_'+id);
			$(row).remove();
			$('#tblExplosivos').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarExplosivo_'+id+'" onclick="consultarExplosivo('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaExplosivo();
		} 
		
		function cerrarVentanaExplosivo(){
			var pantalla ="iframewindowIngresarExplosivo";
			pantalla += idWindowIngresarExplosivo;
			$.closeWindow(pantalla);
		}

		
		function creaNuevaSustancia(){
			 idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente +'&idSustancia=0&tipoObjeto=SUSTANCIA" width="900" height="330" />');
		}
		
		function consultarSustancia(idSustancia){
			idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia='+idSustancia+'&tipoObjeto=SUSTANCIA" width="900" height="330" />');
		}
		
		function cargaSustancia(id,tipo){
			var row=$('#consultarSustancia_'+id);
			$(row).remove();
			$('#tblSustancia').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarSustancia_'+id+'" onclick="consultarSustancia('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaSustancia();
		} 
		
		function cerrarVentanaSustancia(){
			var pantalla ="iframewindowIngresarSustancia";
			pantalla += idWindowIngresarSustancia;
			$.closeWindow(pantalla);
		}

		
		function creaNuevoAnimal(){
			 idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente +'&idAnimal=0&tipoObjeto=ANIMAL" width="900" height="330" />');
		}
		function consultarAnimal(idAnimal){
			idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'&idAnimal='+idAnimal+'&tipoObjeto=ANIMAL" width="900" height="330" />');
		}
		
		function cargaAnimal(id,tipo){
			var row=$('#consultarAnimal_'+id);
			$(row).remove();
			$('#tblAnimal').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarAnimal_'+id+'" onclick="consultarAnimal('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaAnimal();
		} 
		
		function cerrarVentanaAnimal(){
			var pantalla ="iframewindowIngresarAnimal";
			pantalla += idWindowIngresarAnimal;
			$.closeWindow(pantalla);
		}
		
		
		
		function creaNuevoVehiculo(){
			 idWindowIngresarVehiculo++;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=VEHICULO&idVehiculo=0 " width="570" height="600" />');
		}
		
		function consultarVehiculo(idVehiculo){
			 idWindowIngresarVehiculo++;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO " width="570" height="600" />');
		}

		function cargaVehiculo(id,tipo,placas){
			consultaGridVehiculosVisor();
			cerrarVentanaVehiculo();
		} 
		
		function cerrarVentanaVehiculo(){
			var pantalla ="iframewindowIngresarVehiculo";
			pantalla += idWindowIngresarVehiculo;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevaAeronave(){
			 idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'&idAeronave=0&tipoObjeto=AERONAVE" width="600" height="530" />');
		}
		function consultarAeronave(idAeronave){
			idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente+'&idAeronave='+idAeronave+'&tipoObjeto=AERONAVE" width="600" height="530" />');
		}
		
		function cargaAeronave(id,tipo){
			var row=$('#consultarAeronave_'+id);
			$(row).remove();
			$('#tblAeronave').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarAeronave_'+id+'" onclick="consultarAeronave('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaAeronave();
		} 
		
		function cerrarVentanaAeronave(){
			var pantalla ="iframewindowIngresarAeronave";
			pantalla += idWindowIngresarAeronave;
			$.closeWindow(pantalla);
		}

		
		function creaNuevaEmbarcacion(){
			 idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'&idEmbarcacion=0&tipoObjeto=EMBARCACION" width="600" height="530" />');
		}
		function consultarEmbarcacion(idEmbarcacion){
			idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente+'&idEmbarcacion='+idEmbarcacion+'&tipoObjeto=EMBARCACION" width="600" height="530" />');
		}
		
		function cargaEmbarcacion(id,tipo){
			var row=$('#consultarEmbarcacion_'+id);
			$(row).remove();
			$('#tblEmbarcacion').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarEmbarcacion_'+id+'" onclick="consultarEmbarcacion('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaEmbarcacion();
		} 
		
		function cerrarVentanaEmbarcacion(){
			var pantalla ="iframewindowIngresarEmbarcacion";
			pantalla += idWindowIngresarEmbarcacion;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevoInmueble(){
			 idWindowIngresarInmueble++;
			$.newWindow({id:"iframewindowIngresarInmueble" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:0,width:768,height:540,title:"Ingresar inmueble", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarInmueble" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do?numeroExpediente='+numeroExpediente +'" width="1050" height="600" />');
		}
		 

		function creaNuevoNumerario(){
			 idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario=0&tipoObjeto=NUMERARIO" width="800" height="350" />');
		}
		function consultarNumerario(idNumerario){
			idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario='+idNumerario+'&tipoObjeto=NUMERARIO " width="800" height="350" />');
		}
		function cargaNumerario(id,tipo){
			var row=$('#consultarNumerario_'+id);
			$(row).remove();
			$('#tblNumerario').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarNumerario_'+id+'" onclick="consultarNumerario('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaNumerario();
		} 
		
		function cerrarVentanaNumerario(){
			var pantalla ="iframewindowIngresarNumerario";
			pantalla += idWindowIngresarNumerario;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevoVegetal(){
			 idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente +'&idVegetal=0&tipoObjeto=VEGETAL" width="800" height="350" />');
		}
		function consultarVegetal(idVegetal){
			idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente+'&idVegetal='+idVegetal+'&tipoObjeto=VEGETAL" width="800" height="350" />');
		}
		
		function cargaVegetal(id,tipo){
			var row=$('#consultarVegetal_'+id);
			$(row).remove();
			$('#tblVegetal').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVegetal_'+id+'" onclick="consultarVegetal('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaVegetal();
		} 
		
		function cerrarVentanaVegetal(){
			var pantalla ="iframewindowIngresarVegetal";
			pantalla += idWindowIngresarVegetal;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevoDocumentoOficial(){
			 idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente +'&idDocOfic=0&tipoObjeto=DOCUMENTO_OFICIAL" width="800" height="350" />');
		}
		function consultarDocumentoOficial(idDocumentoOficial){
			idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente+'&idDocOfic='+idDocumentoOficial+'&tipoObjeto=DOCUMENTO_OFICIAL" width="800" height="350" />');
		}
		
		function cargaDocOfic(id,tipo){
			var row=$('#consultarDocumentoOficial_'+id);
			$(row).remove();
			$('#tblDocOficial').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarDocumentoOficial_'+id+'" onclick="consultarDocumentoOficial('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaDocumentoOficial();
		} 
		
		function cerrarVentanaDocumentoOficial(){
			var pantalla ="iframewindowIngresarDocumentoOficial";
			pantalla += idWindowIngresarDocumentoOficial;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevaJoya(){
			 idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente +'&idJoya=0&tipoObjeto=JOYA" width="850" height="380" />');
		}
		function consultarJoya(idJoya){
			idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente+'&idJoya='+idJoya+'&tipoObjeto=JOYA" width="850" height="380" />');
		}
		
		function cargaJoya(id,tipo){
			var row=$('#consultarJoya_'+id);
			$(row).remove();
			$('#tblJoya').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarJoya_'+id+'" onclick="consultarJoya('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaJoya();
		} 
		
		function cerrarVentanaJoya(){
			var pantalla ="iframewindowIngresarJoya";
			pantalla += idWindowIngresarJoya;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevaObraDeArte(){
			 idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente +'&idObraArte=0&tipoObjeto=OBRA_DE_ARTE" width="850" height="380" />');
		}
		
		
		function consultarObraDeArte(idObraDeArte){
			idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'&idObraArte='+idObraDeArte+'&tipoObjeto=OBRA_DE_ARTE" width="850" height="380" />');
		}
		
		function cargaObraDeArte(id,tipo){
			var row=$('#consultarObraDeArte_'+id);
			$(row).remove();
			$('#tblObraArte').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarObraDeArte_'+id+'" onclick="consultarObraDeArte('+id+')">'+tipo+'</a></td></tr>');
			cerrarVentanaObraDeArte();
		} 
		
		function cerrarVentanaObraDeArte(){
			var pantalla ="iframewindowObraDeArte";
			pantalla += idWindowIngresarObraDeArte;
			$.closeWindow(pantalla);
		}
					
			
		function ingresarHechos() {
			idWindowIngresarHechos++;
			$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?numeroExpediente='+numeroExpediente +'&idNumeroExpedienteOp='+idNumeroExpedienteOp+'&idCalidad=DENUNCIANTE&idHecho=0 " width="1100" height="530" />');		
		}
		
		function consultarHecho(idHecho,idNumeroExpediente) {
			idWindowIngresarHechos++;
			$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?numeroExpediente='+numeroExpediente +'&idNumeroExpedienteOp='+idNumeroExpedienteOp+'&idCalidad=DENUNCIANTE&idHecho='+idHecho +' " width="1100" height="530" />');		
		}

		
		function asociarIndividuo() {
			idWindowAsociarIndividuo++;
			$.newWindow({id:"iframewindow" + idWindowAsociarIndividuo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowAsociarIndividuo,'<iframe src="<%= request.getContextPath() %>/AsociarIndividuo.do" width="1100" height="530" />');		
		}
	function muestraMenuIntermedio(abrePenal){
		
if(abrePenal=="abrPenal"){
	$("#tablaAcusePenal").css("display", "block");
	$("#tablaAcuseNoPenal").css("display", "none");

}
		
			}
		
		function cargaOcupacion(){
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

		function generarDocumentoSinCaso() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Denuncia", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do" width="1140" height="400" />');
		   		
		}
		
		function cargaUnidadesInvestigacion(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%= request.getContextPath()%>/consultarUnidadesEspecializadas.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAUI').empty();
		    		$('#cbxCanalizaAUI').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('departamentos').each(function(){
						$('#cbxCanalizaAUI').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
		    		
					$("#cbxCanalizaAUI").multiselect({ 
						multiple: false, 
						header: "Seleccione", 
						position: { 
							my: 'top', 
							at: 'top' 
						},
						selectedList: 1 
					});
					
		    	}
		    	});
		}
		
		function cargaInstitucionesExternas(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%= request.getContextPath()%>/consultarInstitucionesExternas.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAIE').empty();
		    		$('#cbxCanalizaAIE').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('departamentos').each(function(){
						$('#cbxCanalizaAIE').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
		    		
					$("#cbxCanalizaAIE").multiselect({ 
						multiple: false, 
						header: "Seleccione", 
						position: { 
							my: 'top', 
							at: 'top' 
						},
						selectedList: 1 
					});
		    	}	
		    	});
		}
		
		
		function revisaEsDelitoGraveUno(idRadio)
		{
			//recuperamos el valor de la columna gravedad del delito
			var ret = jQuery("#gridCatDelitos").jqGrid('getRowData',idRadio);
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRadio);
			var isGrave="No";
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//mostramos los botones correspondientes dependiento del tipo de delito
			if(ret.Gravedad!=null)
			{
				isGrave=ret.Gravedad;
			}
			else
			{
				isGrave=retDos.Gravedad;
			}
			if(isGrave=="No")
			{
				//revisamos que no exista un delito grave NO seleccionado
				if(existeDelitoGraveEnGrid())
				{
					//se le indica al usuario que seleccione un delito grave como principal
					alertDinamico("El delito principal debe ser un delito grave,\n por favor revise su selección");
				}
			}
		}
		
		function revisaEsDelitoGrave(idRadio)
		{
			//recuperamos el valor de la columna gravedad del delito
			var ret = jQuery("#gridCatDelitos").jqGrid('getRowData',idRadio);
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRadio);
			var isGrave="No";
			
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//mostramos los botones correspondientes dependiento del tipo de delito
			if(ret.Gravedad!=null)
			{
				isGrave=ret.Gravedad;
			}
			else
			{
				isGrave=retDos.Gravedad;
			}
			if(isGrave=="No")
			{
				//revisamos que no exista un delito grave NO seleccionado
				if(existeDelitoGraveEnGrid())
				{
					//se le indica al usuario que seleccione un delito grave como principal
					alertDinamico("El delito principal debe ser un delito grave,\n por favor revise su selección");
				}
				else
				{
					mostraDivGenerarOficioCanalizacion(1);	
				}
			}
			else{
				//barro el pseudo-XML de delitos	
				delitosXML.find('catDelitoDTO').each(function(){
					if($(this).find('claveDelito').text()==idRadio)
					{
						if($(this).find('departamento').text()!="")
						{
							//seteamos el combo de la pestaña de Acciones dependiendo del departamento
							$('#cbxCanalizaAUI').val(parseInt($(this).find('departamento').find('departamentoId').text()));
							$('#cbxCanalizaAUI').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(2);
						}
						else
						{
							//seteamos el combo de la pestaña de Acciones dependiendo de la institucion
							$('#cbxCanalizaAIE').val(parseInt($(this).find('institucion').find('institucionId').text()));
							$('#cbxCanalizaAIE').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(3);
						}
					}
				});
			}
		}
		
		/*
		* Funcion que recorre el grid de delitos agraviados para revisar si existe 
		*un delito grave que no fue seleccionado como principal, de existir regresa true en 
		*caso contrario regresa false
		*/
		function existeDelitoGraveEnGrid()
		{
			var bandera=false;
			//obtenemos los ID's de los renglones del Grid
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//barremos el grid para revisar si hay por lo menos un delito marcado como grave
			for (i=0;i<arrayIDs.length;i++)
			{
				//revisamos el checkbox del renglon i-esimo para ver si es grave
				var isGrave=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
				if(isGrave.Gravedad=="Yes")
				{
					bandera=true;
				}
			} 
			return bandera;
		}
		
		
		/*
		*Funcion para saber si se selecciono un delito como principal
		*/
		function existeUnDelitoPrincipalGraveSeleccionado()
		{
			var bandera=0;
			//obtengo el ID del rdb del delito seleccionado
			var idRdb="";
			idRdb=$('input[name=gridDelitos]:checked').attr('id');
			if(idRdb!=null)
			{
				idRdb=idRdb.split('_')[1];
				if(idRdb!="")
				{
					//reviso si el delito seleccionado es grave o no
					/*var IDS=jQuery("#gridDelitosAgraviados").getDataIDs();
					for(j=0;j<IDS.length;j++)
					{
					}
					IDS=jQuery("#gridCatDelitos").getDataIDs();
					for(j=0;j<IDS.length;j++)
					{
					}*/
					var resp=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRdb);
					if(resp.Gravedad=="Yes")
					{
						//prendemos la badnera al encontrar un radio seleccionado
						bandera=1;	
					}
				}	
			}
			else
			{
				alertDinamico("Debe seleccionar un delito principal para poder guardar");	
				bandera=2;
			}
			return bandera;
		}
		
		function mostraDivGenerarOficioCanalizacion(idDiv)
		{
			$("#divCanalizaAUI,#divCanalizaAIE,#btnCanalizaAJR").hide();
			if(parseInt(idDiv)==1)
			{
				$("#btnCanalizaAJR").show();
				$("#btnGenerarAcciones").hide();
			}
			else if(parseInt(idDiv)==2)
			{
				$("#divCanalizaAUI").show();
				$("#btnGenerarAcciones").show();
			}
			else if(parseInt(idDiv)==3)
			{
				$("#divCanalizaAIE").show();
				$("#btnGenerarAcciones").show();
			}
		}
		
		function muestraDivInformativoCanalizacion()
		{
			$("#spanGralUI,#spanGralIE,#spanGralJAR,#spanInfoGralUI,#spanInfoGralIE").hide();
			if($("#divCanalizaAUI").is(':visible'))
			{
				$("#spanInfoGralUI").html($("#cbxCanalizaAUI option:selected").text());
				$("#spanInfoGralUI").show();
				$("#spanGralUI").show();
			}
			else if($("#divCanalizaAIE").is(':visible'))
			{
				$("#spanInfoGralIE").html($("#cbxCanalizaAIE option:selected").text());
				$("#spanInfoGralIE").show();
				$("#spanGralIE").show();
			}
			else if($("#btnCanalizaAJR").is(':visible'))
			{
				$("#spanGralJAR").show();
			}
		}
		
		function muestraDIVSCanalizacion()
		{
			//obtenemos los ID's de los renglones del Grid de Agraviados
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//validamos que haya delitos en el grid de agraviados
			if(arrayIDs.length<1)
			{
				return;
			}
			//revisamos que si hay un delito grave se haya seleccionado
			if(existeDelitoGraveEnGrid())
			{
				if(parseInt(existeUnDelitoPrincipalGraveSeleccionado())==0)
				{
					alertDinamico("Debe seleccionar un delito grave como principal");
					return;
				}	
			}
			//obtenemos el ID del delito principal
			var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
			//recuperamos el valor de la columna gravedad del delito
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal);
			var isGrave="No";
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//seteamos la gravedad
			isGrave=retDos.Gravedad;
			if(isGrave=="No")
			{
				mostraDivGenerarOficioCanalizacion(1);	
			}
			else
			{
				//barro el pseudo-XML de delitos
				delitosXML.find('catDelitoDTO').each(function()
				{
					if($(this).find('claveDelito').text()==idDelPrincipal)
					{
						if($(this).find('departamento').text()!="")
						{
							//seteamos el combo de la pestaña de Acciones dependiendo del departamento
							$('#cbxCanalizaAUI').val(parseInt($(this).find('departamento').find('departamentoId').text()));
							$('#cbxCanalizaAUI').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(2);
						}
						else
						{
							//seteamos el combo de la pestaña de Acciones dependiendo de la institucion
							$('#cbxCanalizaAIE').val(parseInt($(this).find('institucion').find('institucionId').text()));
							$('#cbxCanalizaAIE').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(3);
						}
					}
				});
			}
		}
		
		/*
		*Funcion para asociar los delitos del grid de agraviados con el 
		*expediente
		*/
		function guardarDelitosAgraviadosExp()
		{
			//obtenemos los ID's de los renglones del Grid de Agraviados
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//validamos que haya delitos en el grid de agraviados
			if(arrayIDs.length<1)
			{
				alertDinamico("Debe agregar al menos un delito agraviado");
				return;
			}
			//revisamos que si hay un delito grave se haya seleccionado
			if(existeDelitoGraveEnGrid())
			{
				if(parseInt(existeUnDelitoPrincipalGraveSeleccionado())==0)
				{
					alertDinamico("Debe seleccionar un delito grave como principal");
					return;
				}	
			}
			//obtenemos el ID del delito principal
			var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
			var nombreDelPrincipal=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal).Tipo;
			var delitosNormales="";			
			//barremos el grid para generar la cadena de IDs de los delitos normales
			for (i=0;i<arrayIDs.length;i++)
			{
				if(arrayIDs[i]!=idDelPrincipal)
				{
					var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
					if(delitosNormales.length>0)
					{
						delitosNormales=delitosNormales+","+retd.Tipo;//arrayIDs[i];
					}
					else
					{
						delitosNormales=""+retd.Tipo;//arrayIDs[i];
					}
				}
			} 
			//ahora mandamos los delitos al back-end
			var params="delitos="+nombreDelPrincipal+"-"+delitosNormales+"&numExp="+numeroExpediente;
			$.ajax({
	       	  url: '<%= request.getContextPath()%>/guardarDelitosAgraviadosATP.do',
	    	  dataType: 'xml',
	    	  Type: 'POST',
	    	  data:params,
	    	  async: false,
	    	  success: function(xml){
	    		  if(parseInt($(xml).find('code').text())==0)
	    		  {
	    			  isDelitoSaved=true;
	    			//mostramos las leyendas de canalizacion debajo del grid
	    			  if(existeDelitoGraveEnGrid())
	    			  {
	    				  $("#leyendaUnDelitoGrave").show();
	    				  $("#leyendaNingunDelitoGrave").hide();
	    			  }
	    			  else
	    			  {
	    				  $("#leyendaUnDelitoGrave").hide();
	    				  $("#leyendaNingunDelitoGrave").show();
	    			  }
	    			  //lanzamos la consulta de actividades que depende de los delitos almacenados
	    			  	$.ajax({
				       	  url: '<%= request.getContextPath()%>/consultarActividadesPorDelitosDelExpediente.do',
				    	  dataType: 'xml',
				    	  Type: 'POST',
				    	  data:params,
				    	  async: false,
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('code').text())==0)
				    		  {
				    			  $("#actividadesXDelitosDelExpediente").empty();
				    			  var actividades="";
				    			  //barremos las activiades y generamos el html para ser pintado
				    			  //debajo del anuncio de canalizacion
				    			  $(xml).find('ValorDTO').each(function(){
				    				  actividades = actividades + $(this).find('valor').text()+"<br/>";
					    	      });
				    			  $("#actividadesXDelitosDelExpediente").html(actividades);
				    		  }  			    		
						  }
	    				});
	    			  //fin de la consulta de actividades que depende de los delitos almacenados
	    			  alertDinamico("Se guardaron exitosamente los delitos seleccionados");
	    		  }	 
	    		  else
	    		  {
	    			  isDelitoSaved=false;
	    			  alertDinamico("Ocurrió un error al tratar de guardar los delitos agraviados");
	    		  }   			    		
			  }
	    	});
		}
		
		function validaGuardadoDefinitivo()
		{
			//revisamos que selecciono el tipo: Denuncia o Querella en generales
			//obtengo el ID del rdb del delito seleccionado
			var idRdbTipo="";
			var banderaTipo=false;
			idRdbTipo=$('input[name=generales]:checked').attr('id');
			if(idRdbTipo!=null)
			{
				if(idRdbTipo!="")
				{
					//reviso si el delito seleccionado es grave o no
					if(idRdbTipo=="rbtnDenuncia")//Denuncia
					{
						$("#btnAccDenuncia").show();
						$("#tdCbxAccionesTab").show();
						$("#btnAccQuerella").hide();
						//revisamos si ya guardo el delito
						if(isDelitoSaved)
						{
							//$("#btnAccDenuncia").attr("disabled", "");
						}
						else
						{
							alertDinamico("Debe de seleccionar guardar en la pestaña Delito");
							return;
						}
					}
					else//rbtnQuerella querella
					{
						$("#btnAccQuerella").show();
						$("#btnAccDenuncia").hide();
						$("#tdCbxAccionesTab").hide();
					}
					banderaTipo=true;
				}
			}
			if(!banderaTipo)
			{
				alertDinamico("Debe seleccionar el tipo en la pestaña Generales");
				return;
			}
		}
		
		
		
		function gridRelacionarDelitosTabDelito(){
			cargaComboProbableResponsableRDPPV();
			cargaComboDelitosAAsociarRDPD();
			jQuery("#gridDetalleTabDelitoRelDel").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pagerGridDetalleTabDelitoRelDel'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerGridDetalleTabDelitoRelDel',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}
		
		function gridMinisterial(){

			jQuery("#gridDetalleFrmPrincipalTres").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pagerTres1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerTres12',{edit:false,add:false,del:false});

				

			}
		
		function gridCustodia(){

			jQuery("#gridDetalleFrmPrincipalUno").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pageruno'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerpageruno',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}
		
		function gridPericiales(){

			jQuery("#gridDetalleFrmPrincipalDos").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pagerDos'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerDos1',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}

		function abrirDenuncia() {
			$.newWindow({id:"iframewindowAbrirDenuncia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Denuncia", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirDenuncia",'<iframe src="<%= request.getContextPath() %>/resources/images/Denuncia en Atención Temprana _JAS.pdf" width="1140" height="400" />');
		   		
		}
		
		function abreTeoria() {
			$.newWindow({id:"iframewindowAbrirteoria", statusBar: true, posx:20,posy:20,width:1390,height:600,title:"Teoria del caso", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirteoria",'<iframe src="<%= request.getContextPath() %>/teoriaDelCasoView.jsp?idExpediente='+idExpedienteop+'&numeroExpediente='+numeroExpediente+'" width="1390" height="600" />');
		      		
		}

		
		function lanzaCarpetaInvestigacionDefensoria() {
			$.newWindow({id:"iframewindowAbrirteoria", statusBar: true, posx:20,posy:20,width:310,height:231,title:"Enviar Carpeta Investigación", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirteoria",'<iframe src="<%= request.getContextPath() %>/enviarCarpetaInvestigacionDefensoria.do" width="310" height="231" />');
		      		
		}


		function datosGenerales(){
			 $.ajax({
			      type: 'POST',
		    	  url: '<%= request.getContextPath()%>/cargarDatosGenerales.do?idNumeroExpedienteOp='+idNumeroExpedienteOp,
		    	  data: '',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
						$('#Vehiculos').html($(xml).find('totalVehiculos').text()+': '+$(xml).find('vehiculos').text());
						if($(xml).find('ve').text()!="1"){
							$('#imgVehiculo').hide();
						}
						$('#EquiposDeComputo').html($(xml).find('totalEquiposComputo').text()+': '+$(xml).find('equiposComputo').text());
						if($(xml).find('equiCom').text()!="1"){
							$('#imgEquiposDeComputo').hide();
						}
						$('#EquiposTelefonicos').html($(xml).find('totalEquiposTelefonicos').text()+': '+$(xml).find('equiposTelefonicos').text());
						if($(xml).find('equiTel').text()!="1"){
							$('#imgEquiposTelefonicos').hide();
						}
						$('#Armas').html($(xml).find('totalArmas').text()+': '+$(xml).find('armas').text());
						if($(xml).find('arm').text()!="1"){
							$('#imgArmas').hide();
						}
						$('#Explosivos').html($(xml).find('totalExplosivos').text()+': '+$(xml).find('explosivos').text());
						if($(xml).find('expl').text()!="1"){
							$('#imgExplosivos').hide();
						}
						$('#Sustancias').html($(xml).find('totalSustancias').text()+': '+$(xml).find('sustancias').text());
						if($(xml).find('sus').text()!="1"){
							$('#imgSustancias').hide();
						}
						$('#Animales').html($(xml).find('totalAnimales').text()+': '+$(xml).find('animales').text());
						if($(xml).find('anim').text()!="1"){
							$('#imgAnimales').hide();
						}
						$('#Aeronaves').html($(xml).find('totalAeronaves').text()+': '+$(xml).find('aeronaves').text());
						if($(xml).find('aero').text()!="1"){
							$('#imgAeronaves').hide();
						}
						$('#Embarcacion').html($(xml).find('totalEmbarcaciones').text()+': '+$(xml).find('embarcaciones').text());
						if($(xml).find('embar').text()!="1"){
							$('#imgEmbarcacion').hide();
						}
						$('#Inmueble').html($(xml).find('totalInmuelbes').text()+': '+$(xml).find('inmuelbes').text());
						if($(xml).find('inmu').text()!="1"){
							$('#imgInmueble').hide();
						}
						$('#Numerario').html($(xml).find('totalNumerarios').text()+': '+$(xml).find('numerarios').text());
						if($(xml).find('nume').text()!="1"){
							$('#imgNumerario').hide();
						}
						$('#Vegetal').html($(xml).find('totalVegetales').text()+': '+$(xml).find('vegetales').text());
						if($(xml).find('vege').text()!="1"){
							$('#imgVegetal').hide();
						}
						$('#DocumentoOficial').html($(xml).find('totalDocumentosOficiales').text()+': '+$(xml).find('documentosOficiales').text());
						if($(xml).find('docuOfi').text()!="1"){
							$('#imgDocumentoOficial').hide();
						}
						$('#Joya').html($(xml).find('totalJoyas').text()+': '+$(xml).find('joyas').text());
						if($(xml).find('joy').text()!="1"){
							$('#imgJoya').hide();
						}
						$('#ObraDeArte').html($(xml).find('totalObrasDeArte').text()+': '+$(xml).find('obrasDeArte').text());
						if($(xml).find('obrArte').text()!="1"){
							$('#imgObraDeArte').hide();
						}
						$('#Otros').html($(xml).find('totalOtrosObjestos').text()+': '+$(xml).find('otrosObjestos').text());
						if($(xml).find('otrObj').text()!="1"){
							$('#imgOtros').hide();
						}
						$('#Denunciantes').html($(xml).find('totalDenunciantes').text()+': '+$(xml).find('denunciantes').text());
						if($(xml).find('denun').text()!="1"){
							$('#imgDenunciantes').hide();
						}
						$('#Victimas').html($(xml).find('totalVictimas').text()+': '+$(xml).find('victimas').text());
						if($(xml).find('vic').text()!="1"){
							$('#imgVictimas').hide();
						}
						$('#ProbablesResponsables').html($(xml).find('totalProbablesResposables').text()+': '+$(xml).find('probablesResposables').text());
						if($(xml).find('proba').text()!="1"){
							$('#imgProbablesResponsables').hide();
						}
						$('#Testigos').html($(xml).find('totalTestigos').text()+': '+$(xml).find('testigos').text());
						if($(xml).find('test').text()!="1"){
							$('#imgTestigos').hide();
						}
						$('#Traductores').html($(xml).find('totalTraductores').text()+': '+$(xml).find('traductores').text());
						if($(xml).find('tradu').text()!="1"){
							$('#imgTraductores').hide();
						}
						$('#QuienDetuvo').html($(xml).find('quienDetuvo').text()+': '+$(xml).find('quienDetuvoNombre').text());
						if($(xml).find('quienDetu').text()!="1"){
							$('#imgQuienDetuvo').hide();
						}
						$('#estatusExpe').html($(xml).find('estatusNumeroExpediente').text());
						$('#origenExpe').html($(xml).find('origenExpediente').text());	
						$('#anonimoDenun').html($(xml).find('esDesconocido').text());
						num=$(xml).find('totalDocumentosDelExpediente').text();
						$('#idTotalDocumentos').html($(xml).find('totalDocumentosDelExpediente').text());
						$('#fehcaApertura').html("Fecha Apertura:"+$(xml).find('fechaApertura').text());
						
		    	  }
		    	});
		}


		function documentos(){
//			 $.ajax({
//			      type: 'POST',
//		    	  url: '<%= request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul,
//		    	  data: '',
//		    	  dataType: 'xml',
//		    	  async: false,
//		    	  success: function(xml){
//			    	  $('#tablePestanaDocumentos').empty();
//			    	  $(xml).find('documentoDTO').each(function(){
//			    		  var nombres=$(this).find('nombreDocumento').text();
//			    		  $('#tablePestanaDocumentos').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="abrirDenuncia()" id="Documento">'+nombres+'</a></td></tr>');
//				     });
//		       	  }
//		    	});

			 jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
						{url:'<%= request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul, 
						datatype: "xml" 
						});
					 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
					
					 datosGenerales();
					
		}
		
		
		function alertas(){
			 jQuery("#gridDetalleAlertas").jqGrid('setGridParam',  
						{url:'<%= request.getContextPath()%>/consultarAlertas.do?idExpedienteop='+idNumeroExpedienteConsul, 
						datatype: "xml" });
					 $("#gridDetalleAlertas").trigger("reloadGrid"); 
		}
		
		/************ FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		function ocultaMuestraTabVisor(claseTab,bandera)
		{
			if(parseInt(bandera)==0)//oculta
			{
				$("."+claseTab).hide();
			}
			else///muestra
			{
				$("."+claseTab).show();
			}
		}

		function consultaPDF(id){
			document.frmDoc.documentoId.value = id;
			document.frmDoc.submit();
		}
		
		function cerrarVentanaProableResponsable(){
			$.closeWindow('iframewindowIngresarProbResponsable');	
		}
		
		
		/************ FIN FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		
		
		function ocultaMuestraTblsRelacionarDelitos()
		{
			var relacionDelitoPorPErsonaDelito = $(':radio[name=relacionaDelitos]:checked').val();
			if(parseInt(relacionDelitoPorPErsonaDelito)==0)
			{
				//Relacion por persona
				$("#tblRelacionaDelXPersona").show();
				$("#tblRelacionaDelXDelito").hide();
				$("#tblRelacionaDelXTodos").hide();
			}
			else if(parseInt(relacionDelitoPorPErsonaDelito)==1)
			{
				//Relacion por delito
				$("#tblRelacionaDelXDelito").show();
				$("#tblRelacionaDelXPersona").hide();
				$("#tblRelacionaDelXTodos").hide();
			}
			else
			{
				//Relacion por todos
				$("#tblRelacionaDelXDelito").hide();
				$("#tblRelacionaDelXPersona").hide();
				$("#tblRelacionaDelXTodos").show();
				cargaGridConsultaDelitosTodos();
			}	
		}

		function cerrarVentanaEnvio(){
			$windowCloseButton.click();
				
			}
		
		function abreLineasInvestiga(){		
			$.newWindow({id:"iframewindowLineaInvestigacion", statusBar: true, posx:20,posy:20,width:1300,height:550,title:"Investigacón", type:"iframe"});
	        $.updateWindowContent("iframewindowLineaInvestigacion",'<iframe src="<%= request.getContextPath() %>/lineasInvestigacion.do?numeroUnicoExpediente='+numeroExpediente+'&idNumeroUnicoExpediente='+idNumeroExpedienteConsul+'&pantalla='+pantallaSolicitada+'"    width="1300" height="550" />');
		}
		
		/*
		*Funcion que carga el grid con los nombre de los documentos digitales asociados 
		*al id de la solicitud de visitaduria
		*/
		function cargaGridDocumentosDigitales(){
			if(primeraVezGridDocumentosDigitales == true){
				jQuery("#gridDocumentosDigitales").jqGrid({
					url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul+'',
					datatype: "xml", 
					colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
					colModel:[ 	{name:'area',index:'area', width:200},
								{name:'fechaActividad',index:'fechaActividad', width:170},							
								{name:'nombreActividad',index:'nombreActividad', width:400},
					           	{name:'tipo',index:'tipo', width:155}, 
								{name:'nombre',index:'nombre', width:255},
					           	{name:'fecha',index:'fecha', width:170}
								],
					pager: jQuery('#pagerGridDocumentosDigitales'),
					rowNum:20,
					rowList:[10,20,30],
					width:250,
					sortname: 'nombreDocumento',
					viewrecords: true,
					sortorder: "desc",
					multiselect:true,
					ondblClickRow: function(rowid) {
						if (rowid) {
							abrirDocsDigAsociadosASol(rowid);							
						}
					},
					loadComplete: function(){
						jQuery("#gridDocumentosDigitales").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
					}
				}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
				$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
				primeraVezGridDocumentosDigitales= false;
			}
			else{
				jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numeroExpedienteId+'',datatype: "xml" });
				$("#gridDocumentosDigitales").trigger("reloadGrid");
			}
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '430px');
		}
		
		/*
		*Funcion que abre el PDF para ver los documentos asociados al numero de causa
		*/
		function abrirDocsDigAsociadosASol(documentoId){
			if(documentoId != null && documentoId != ""){
				$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoId+"&inFrame=true");
			}
			else{
				alertDinamico("El documento no puede ser mostrado");
			}
		}
		
		function elaboraNotificacionAuditoria()
		{	
			var actividad=0;
			var formaID=4;
			var titulo="op";
			var usaeditor="";
			var estatusId="";
			//consultamos la forma para la notificacion de auditoria
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf=135',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
					formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
					titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
					usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
					estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
				}
			});
			$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Notificación Auditoría", type:"iframe"});
            $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarNotificacionAuditoria.do?formaId='+formaID+'&numeroUnicoExpediente='+idNumeroExpedienteOp+'"    width="1140" height="550" />');
		}
		/*
		*Funcion que carga el grid con los nombre de los documentos digitales asociados 
		*al id de la solicitud de visitaduria
		*/
		function cargaGridDocumentosDigitales(){
			if(primeraVezGridDocumentosDigitales == true){
				jQuery("#gridDocumentosDigitales").jqGrid({
					url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul+'',
					datatype: "xml", 
					colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
					colModel:[ 	{name:'area',index:'area', width:200},
								{name:'fechaActividad',index:'fechaActividad', width:170},							
								{name:'nombreActividad',index:'nombreActividad', width:400},
					           	{name:'tipo',index:'tipo', width:155}, 
								{name:'nombre',index:'nombre', width:255},
					           	{name:'fecha',index:'fecha', width:170}
								],
					pager: jQuery('#pagerGridDocumentosDigitales'),
					rowNum:20,
					rowList:[10,20,30],
					width:250,
					sortname: 'nombreDocumento',
					viewrecords: true,
					sortorder: "desc",
					multiselect:true,
					ondblClickRow: function(rowid) {
						if (rowid) {
							abrirDocsDigAsociadosASol(rowid);							
						}
					},
					loadComplete: function(){
						jQuery("#gridDocumentosDigitales").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
					}
				}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
				$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
				primeraVezGridDocumentosDigitales= false;
			}
			else{
				jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numeroExpedienteId+'',datatype: "xml" });
				$("#gridDocumentosDigitales").trigger("reloadGrid");
			}
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '430px');
		}
		
		/*
		*Funcion que abre el PDF para ver los documentos asociados al numero de causa
		*/
		function abrirDocsDigAsociadosASol(documentoId){
			if(documentoId != null && documentoId != ""){
				$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoId+"&inFrame=true");
			}
			else{
				alertDinamico("El documento no puede ser mostrado");
			}
		}
		
		/*
		*Funcion para regresar el numero de relacionde delito-persona o delito-delito con 
		*las que cuenta el expediente
		*/
		function consultaTotalRelacionesDelitoPorTodos()
		{
			var numeroRelaciones = 0;
			//consultamos la forma para la notificacion de auditoria
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarTamanoDelitosAsociadosPorTodos.do?idExpediente='+idExpedienteop+'',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					numeroRelaciones=$(xml).find('relacionTodosLosDelitos').find('tamanoLista').text();
				}
			});
			return numeroRelaciones;
		}

		/*
		*Funcion que lanza la ventana par asoliciutar una transcripcion de audiencia y de audio y video
		*/
		function muestraSolicitudTranscripcion()
		{
			idWindowSolicitudTranscripcion++;
			$.newWindow({id:"iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion, statusBar: true, posx:253,posy:100,width:812,height:454,title:"Solicitud de Transcripción", type:"iframe"});
	    	$.updateWindowContent("iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion,'<iframe src="<%=request.getContextPath()%>/solicitarTranscripcionEnPG.do" width="812" height="454" />');

	    	}
	</script>
	
	<!--ARCHIVOS PARA MENU DE ARBOL-->        
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/simpletreemenu.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/simpletree.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/menu.css"  type="text/css">
	
	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	
	<script type="text/javascript">
		$(function(){
			// Tabs
			$('#tabs').tabs();
				
			//hover states on the static widgets
			$('#dialog_link, ul#icons li').hover(
				function() { $(this).addClass('ui-state-hover'); }, 
				function() { $(this).removeClass('ui-state-hover'); }
			);
				
		});
	</script>
	<!--TERMINA MENU ARBOL-->
	
</head>

<body >

	<div class="ui-layout-north">
	
		<!--BARRA DE HERRAMIENTAS-->
		
		<!--	<ul class="toolbar">-->
		<!--		<div id="menu_head">-->
		<!--			<li id="tbarBtnNuevo" class="first"><span></span>Estatus</li>-->
		<!--			<li id="tbarBtnLectura"><span></span>Canalizar</li>-->
		<!--			<li id="tbarBtnImpresoras"><span></span>Archivo Fisico</li>-->
		<!--			<li id="tbarBtnImpresoras"><span></span>Denuncia o Querella</li>-->
		<!--			-->
		<!--			-->
		<!--		</div>-->
		<!--		<div id="menu_config">-->
		<!--			<li><a href="#">config01</a></li>-->
		<!--			<li><a href="#">config02</a></li>-->
		<!--			<li><a href="#">config03</a></li>-->
		<!--			<li class="last"><a href="#">config04</a></li>-->
		<!--		</div>-->
		<!--	</ul>-->
	
	</div>

	<!--COMIENZAN TABS SUPERIORES (PRINCIPALES)-->
	<div id="tabs">
	
		<ul>
			<li class="tabTabsGrals" id="tabTabsGrals"><a href="#tabs-6" onclick="datosGenerales()">Resumen</a></li>
			<li class="tabTabsVisitaduria" id="tabTabsVisitaduria"><a href="#tabs-16">Visitaduría</a></li>
			<li class="tabTabsHechos" id="tabTabsHechos"><a href="#tabs-3">Hechos</a></li>
			<li class="tabTabsInv" id="tabTabsInv"><a href="#tabs-1">Involucrado</a></li>
			<li class="tabTabsDelito" id="tabTabsDelito"><a href="#tabs-2" onclick="cargaComboProbableResponsableRDPPVDelito()">Delito</a></li>
			<li class="tabTabsObjs" id="tabTabsObjs"><a href="#tabs-4">Objetos y evidencias</a></li>
			<li class="tabTabsCriterio" id="tabTabsCriterio"><a href="#tabs-13">Criterio de oportunidad</a></li>
			<li class="tabTabsDocs" id="tabTabsDocs"><a href="#tabs-11" onclick="documentos()">Documentos</a></li>
			<li class="tabTabsAcciones" id="tabTabsAcciones"><a href="#tabs-7">Actuaciones</a></li>
			<li class="tabTabsPeri" id="tabTabsPeri"><a href="#tabs-8">Periciales</a></li> <!--onclick : gridPericiales() -->
			<li class="tabTabsPolMin" id="tabTabsPolMin"><a href="#tabs-9" onclick="">Policía ministerial</a></li>
			<li class="tabTabsCadCus" id="tabTabsCadCus"><a href="#tabs-10" onclick="gridCustodia()">Cadena de custodia</a></li>
			<li class="tabTabsAudiencias" id="tabTabsAudiencias"><a href="#tabs-12">Audiencias</a></li>
			<li class="tabTabsNotas" id="tabTabsNotas"><a href="#tabs-5">Notas</a></li>
			<li class="tabTabsAlertas" id="tabTabsAlertas"><a href="#tabs-14" onclick="alertas()">Bitácora de alarmas</a></li>
		</ul>
		
		<!--COMIENZAN TABS INFERIORES DE INDIVIDUO-->
		<div id="tabs-1" class="tabTabsInv">		
			<div id="tabschild" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild-1">Denunciante</a></li>
					<li><a href="#tabschild-2">Víctima</a></li>
					<li><a href="#tabschild-3"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild-4">Testigo</a></li>
					<li><a href="#tabschild-5">Traductor-Intérprete</a></li>
					<li class="tabTabsQuienDetuvo"><a href="#tabschild-6">Quién Detuvo</a></li>
				</ul>
				
				
				
				<div id="tabschild-1">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante" style=" padding: .5cm;">
						
						<tr  vAlign="Top" >
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante"><input type="button" class="btn_Generico" value="Ingresar Denunciante" class="btn_Generico"/></a></td>
						</tr>
						<!--  <tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarDenuncianteUno">Denunciante uno</a></td>
						</tr>-->
					</table>
					</div>
				</div>
				
				<div id="tabschild-2">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblVictima" style="padding: .5cm;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima"><input type="button" class="btn_Generico" value="Ingresar Víctima" class="btn_Generico"/></a></td>
						</tr>
						
					</table>
				</div>
				</div>
				<div id="tabschild-3">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblProbableResponsable" style="padding: .5cm;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable"><input type="button" class="btn_Generico" value='<bean:message key="ingProbaleResponsable"/>'/></a></td>
						</tr>
					</table>
				</div>
				</div>
				
				<div id="tabschild-4">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblTestigo" style="padding: .5cm;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo"><input type="button" class="btn_Generico" value="Ingresar Testigo" class="btn_Generico"/></a></td>
						</tr>
						
					</table>
				</div>
				</div>
				<div id="tabschild-5">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblTraductor" style="padding: .5cm;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor"><input type="button" class="btn_Generico" value="Ingresar Traductor - Int&eacute;rprete" class="btn_Generico"/></a></td>
						</tr>
					</table>
				</div>
				</div>
				<div id="tabschild-6">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblQuienDetuvo" style="padding: .5cm;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="quienDetuvo"><input type="button" class="btn_Generico" value="Ingresar Quien Detuvo" class="btn_Generico"/></a></td>
						</tr>
					</table>
				</div>	
				</div>	
			</div>
			
		</div>
		<!--TERMINAN TABS INFERIORES DE INDIVIDUO-->
		
		<!--COMIENZAN TABS INFERIORES DE DELITO-->
		<div id="tabs-2" class="tabTabsDelito">
		
			<div id="tabschild2" class="tabs-bottom">
				
				<ul>
					<li><a href="#tabschild2-1">Seleccionar delitos</a></li>
					<li><a href="#tabschild2-2" onclick="gridRelacionarDelitosTabDelito();cargaGridConsultaDelitosTodos()">Establecer Delito Persona</a></li>					
				</ul>
				
				<div id="tabschild2-1">

					<table width="25%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaSentenciado">
								<jsp:include page="seleccionarDelitoViewPJENC.jsp"></jsp:include>
							</a></td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild2-2">
				
					<table>
						<tr>
							<td>
								Relacionar Delito por :  
								Todos <input type="radio" value="2" checked="checked" id="rdbMenuInterRelDelXTodos" name="relacionaDelitos"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Persona <input type="radio" value="0" id="rdbMenuInterRelDelXPersona" name="relacionaDelitos"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Delito <input type="radio" value="1" id="rdbMenuInterRelDelXDelito" name="relacionaDelitos"/>
							</td>
						</tr>
					</table>
					<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXPersona" style="display: none;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<jsp:include page="relacionaDelitoPorPersonaView.jsp"></jsp:include>
							</td>
						</tr>
					</table>
					<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXDelito" style="display: none;">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<jsp:include page="relacionaDelitoPorDelitoView.jsp"></jsp:include>
							</td>
						</tr>
					</table>
					<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXTodos" >
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<jsp:include page="relacionaDelitoPorTodosView.jsp"></jsp:include>
							</td>
						</tr>
					</table>
				
			</div>
					</div>
		</div>
		<!--TERMINAN TABS INFERIORES DE DELITO-->
		
		<!--COMIENZAN TAB Visitaduria-->
		<div id="tabs-16" class="tabTabsVisitaduria">
		
			<div id="tabschild16" class="tabs-bottom">
				
				<ul>
					<li><a href="#tabschild16-1">Resumen</a></li>
					<li><a href="#tabschild16-2">Documentos</a></li>				
				</ul>
				
				<div id="tabschild16-1">
					<div style="width: 1042px; height: 490px;" class="back_hechos" ><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<table width="50%">
							<tr>
								<td width="10%"></td>
								<td><b>Nombre del MP dueño del expediente auditado : </b>
								</td>
								<td id="spanNombrDuenoExpAud">
								</td>
							</tr>
							<tr>
								<td width="10%"></td>
								<td><b>N&uacute;mero del expediente auditado : </b>
								</td>
								<td id="spanNumExpAud">
								</td>
							</tr>
							<tr>
								<td width="10%"></td>
								<td><b>&Aacute;rea del expediente auditado : </b>
								</td>
								<td id="spanAreaExpAud">
								</td>
							</tr>
							<tr>
								<td width="10%"></td>
								<td><b>Fecha de creaci&oacute;n de la carpeta de auditor&iacute;a : </b>
								</td>
								<td id="spanFechaExpAud">
								</td>
							</tr>
							<tr>
								<td width="10%"></td>
								<td><b>Estatus del expediente de visitadur&iacute;a : </b>
								</td>
								<td id="spanEstatusExpAud">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="tabschild16-2">
					<table width="1042"  height="490" border="0" cellspacing="0" cellpadding="0">
				              <tr>
				                <td width="250" height="450px;" align="center" valign="top">
			                        <table id="gridDocumentosDigitales"></table>
			                        <div id="pagerGridDocumentosDigitales"></div>
				                </td>
				                <td width="900" align="center" valign="top">
				               	  <iframe id='visorDocsFrame' width="900" height="450" src="">		               	  
				               	  </iframe>
				                </td>
				              </tr>
			        </table>
			     </div>
			</div>
					
		</div>
		<!--TERMINA TAB Visitaduria-->	
				
		<!--COMIENZAN TAB HECHOS-->
		<div id="tabs-3" class="tabTabsHechos">
		
			<div id="tabschild3" class="tabs-bottom">
				
				<ul>
					<li><a href="#tabschild3-1">Hechos</a></li>				
				</ul>
				
				<div id="tabschild3-1">
				<div style="width: 1042px; height: 490px;" class="back_hechos">
					<table    border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos" style="padding: .5cm; " >
						<tr valign="top">						
							<td valign="top"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="ingresarHechos" value="Ingreso Hecho" class="btn_modificar"/></td>
						</tr>
					</table>
				</div>
				</div>
			</div>
					
		</div>
		<!--TERMINA TAB HECHOS-->
		
		<!--COMIENZA TABS OBJETOS Y EVIDENCIAS-->
		<div id="tabs-4" class="tabTabsObjs">
		
			<div id="tabschild4" class="tabs-bottom">
				
				<ul>
					<li><a onclick="consultaGridVehiculosVisor()" href="#tabschild4-7">Veh&iacute;culo</a></li>
					<li><a onclick="consultaGridEquiposComputoVisor()" href="#tabschild4-1">Equipo de c&oacute;mputo</a></li>
					<li><a href="#tabschild4-2">Equipo telef&oacute;nico</a></li>
					<li><a onclick="consultaGridArmasVisor()" href="#tabschild4-3">Arma</a></li>
					<li><a href="#tabschild4-4">Explosivo</a></li>
					<li><a href="#tabschild4-5">Sustancia</a></li>
					<li><a href="#tabschild4-6">Animal</a></li>					
					<li><a href="#tabschild4-8">Aeronave</a></li>
					<li><a href="#tabschild4-9">Embarcaci&oacute;n</a></li>
					<!-- <li><a href="#tabschild4-10">Inmueble</a></li>-->
					<li><a href="#tabschild4-11">Numerario</a></li>
					<li><a href="#tabschild4-12">Vegetal</a></li>
					<li><a href="#tabschild4-13">Documento oficial</a></li>
					<li><a href="#tabschild4-14">Joya</a></li>
					<li><a href="#tabschild4-15">Obra de arte</a></li>	
					<li><a href="#tabschild4-16">Otros</a></li>		
				</ul>
				
				<!--EQUIPO DE COMPUTO-->
				<div id="tabschild4-1">
				
					<table width="25%" cellpadding="0" cellspacing="0" id="tblEquipoComputo">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoDeComputo" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
					<table id="gridObjsEquipoComputo" width="470px"></table>
					<div id="pagerGridObjsEquipoComputo"></div>
				</div>
				
				<!--EQUIPO TELEFONICO-->
				<div id="tabschild4-2">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblEquipoTelefonico">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoTelefonico" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--ARMA-->
				<div id="tabschild4-3">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblArma">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
					<table id="gridObjsArma" width="470px"></table>
					<div id="pagerGridObjsArma"></div>
				</div>
				
				<!--EXPLOSIVO-->
				<div id="tabschild4-4">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblExplosivos">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoExplosivo" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--SUSTANCIA-->
				<div id="tabschild4-5">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblSustancia">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--ANIMAL-->
				<div id="tabschild4-6">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblAnimal">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoAnimal" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--VEHICULO-->
				<div id="tabschild4-7">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblVehiculos">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVehiculo" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					<!--
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Veh&iacute;culo 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Veh&iacute;culo 2</a></td>
						</tr>
					-->
					</table>
					<table id="gridObjsVehiculo" width="470px"></table>
					<div id="pagerGridObjsVehiculo"></div>
				</div>
				
				<!--AERONAVE-->
				<div id="tabschild4-8">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblAeronave">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaAeronave" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--EMBARCACION-->
				<div id="tabschild4-9">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblEmbarcacion">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--INMUEBLE-->
				<!-- <div id="tabschild4-10">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoInmueble" value="Ingreso nuevo"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 2</a></td>
						</tr>
					</table>
				</div>-->
				
				<!--NUMERARIO-->
				<div id="tabschild4-11">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblNumerario">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--VEGETAL-->
				<div id="tabschild4-12">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblVegetal">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVegetal" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--DOCUMENTO OFICIAL-->
				<div id="tabschild4-13">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblDocOficial">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoDocumentoOficial" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--JOYA-->
				<div id="tabschild4-14">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblJoya">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaJoya" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
				
				<!--OBRA DE ARTE-->
				<div id="tabschild4-15">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblObraArte">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaObraDeArte" class="btn_modificar" value="Ingreso nuevo"/></td>
						</tr>
					</table>
				</div>
						
			</div>
					
		</div>
		<!--TERMINAN TABS OBJETOS Y EVIDENCIAS-->
		
		
		<!--COMIENZA TAB NOTAS-->
		<div id="tabs-5" class="tabTabsNotas">
			<table width="25%" cellpadding="0" cellspacing="0" id="tablaNotasExpediente">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" class="btn_modificar" value="Generar Nota"  id="botonGuardarNotas" onclick="notaExpediente(0);" /></td>
						</tr>
				</table>
		</div>
		<!--TERMINA TAB NOTAS-->
		
		<!--COMIENZAN TAB GENERALES     "-->
		<!--  <div id="tabs-6" class="tabTabsGrals"    id="">-->
		<div id="tabs-6" class="fondoClaroAp">
		
			<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
				<tr><td colspan="6">&nbsp;</td></tr>			
			  <tr style="border-bottom-style: solid; border: 1;background-image:">
			    <td width="238" style="font-size:14px; background-color:" align="right"><strong>Estatus del Expediente:</strong></td>
			    <td width="19" style="font-size:14px; background-color:" >&nbsp;</td>
			    <td width="507" align="right" style="font-size:14px; background-color:"><strong>Resumen de objetos:</strong></td>
			    <td width="4" style="font-size:14px; background-color:">&nbsp;</td>
			    <td width="270" align="right" style="font-size:14px; background-color:"><strong>Resumen de involucrados<em>:</em></strong></td>	
			    <td width="4" style="font-size:14px; background-color:">&nbsp;</td>
			  </tr>
			  <tr >
			    <td  colspan="6" height="20px">
			    	<table width="978" border="0" cellpadding="0" cellspacing="0" class="linea_down_gral" align="center">
					  <tr>
					    <td>&nbsp;</td>
					  </tr>
					</table>			    	
			    </td>
			  </tr>
			  <tr valign="top">
			    <td id="estatusExpe" align="right"></td>
			    <td></td>
			    <td rowspan="16" align="center" width="507" style="background-color:" valign="top"><table border="0" cellpadding="0" cellspacing="0" width="99%">
		          <tr>
		            <td width="145" align="right" nowrap style="background-color:">Vehículos:</td>
		            <td width="332" id="Vehiculos">&nbsp;</td>
		            <td id="imgVehiculo" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		            
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Equipos de cómputo:</td>
		            <td id="EquiposDeComputo" >&nbsp;</td>
		            <td id="imgEquiposDeComputo" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Equipos Telefónicos:</td>
		            <td id="EquiposTelefonicos">&nbsp;</td>
		            <td id="imgEquiposTelefonicos" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Armas:</td>
		            <td id="Armas">&nbsp;</td>
		            <td id="imgArmas" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Explosivos:</td>
		            <td id="Explosivos">&nbsp;</td>
		            <td id="imgExplosivos" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Sustancias:</td>
		            <td id="Sustancias">&nbsp;</td>
		            <td id="imgSustancias" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Animales:</td>
		            <td id="Animales">&nbsp;</td>
		            <td id="imgAnimales" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Aeronaves:</td>
		            <td id="Aeronaves">&nbsp;</td>
		            <td id="imgAeronaves" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Embarcación:</td>
		            <td id="Embarcacion">&nbsp;</td>
		            <td id="imgEmbarcacion" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Inmueble:</td>
		            <td id="Inmueble">&nbsp;</td>
		            <td id="imgInmueble" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Numerario:</td>
		            <td id="Numerario">&nbsp;</td>
		            <td id="imgNumerario" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Vegetal:</td>
		            <td id="Vegetal">&nbsp;</td>
		            <td id="imgVegetal" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Documento oficial:</td>
		            <td id="DocumentoOficial">&nbsp;</td>
		            <td id="imgDocumentoOficial" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Joya:</td>
		            <td id="Joya">&nbsp;</td>
		            <td id="imgJoya" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td align="right" style="background-color:">Obra de arte:</td>
		            <td id="ObraDeArte">&nbsp;</td>
		            <td id="imgObraDeArte" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		          <tr>
		            <td height="19" align="right" style="background-color:">Otros:</td>
		            <td id="Otros">&nbsp;</td>
		            <td id="imgOtros" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
		          </tr>
		</table>
			
</td>
<td></td>
			    
			    <td rowspan="6" align="right" style="background-color:">
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="158" align="right" style="background-color:">Denunciantes:</td>
    <td width="97" id="Denunciantes">&nbsp;</td>
    <td id="imgDenunciantes" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
  <tr>
    <td align="right" style="background-color:">Victimas:</td>
    <td id="Victimas">&nbsp;</td>
    <td id="imgVictimas" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
  <tr>
    <td align="right" style="background-color:">Probables Responsables:</td>
    <td id="ProbablesResponsables">&nbsp;</td>
    <td id="imgProbablesResponsables" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
  <tr>
    <td align="right" style="background-color:">Testigos:</td>
    <td id="Testigos">&nbsp;</td>
    <td id="imgTestigos" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
  <tr>
    <td align="right" style="background-color:">Traductores/Intérpretes:</td>
    <td id="Traductores">&nbsp;</td>
    <td id="imgTraductores" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
  <tr>
    <td align="right" style="background-color:">Quién detuvo:</td>
    <td id="QuienDetuvo">&nbsp;</td>
    <td id="imgQuienDetuvo" width="15"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
  </tr>
</table></td>
			    
			  </tr>
			  <tr>
			    <td  style="background-color:"align="right">
			    	<span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:">
			    		<img src="<%=request.getContextPath()%>/resources/images/icn_doc_chek.png"><strong>Tipo:</strong>
			    	</span>
			    </td>
			    <td ></td>
			    
			    
			  </tr>
			  <tr>
			    <td id="origenExpe" align="right"></td>
			    <td ></td>
			    
			    
			  </tr>
			  <tr>
			    <td ></td>
			    <td></td>			    
			    
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td></td>
			    
			    
			  </tr>
			  <tr>
			    <td  id="anonimoDenun" align="right">&nbsp;</td>
			    <td></td>
			    
			    
			  </tr>
			  <tr>
			    <td align="right"><span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:; display:none;"><strong>Canalizado a:</strong></span> </td>
			    <td><!-- <input type="radio" name="radio" id="rbtnRestaurativa" value="R" />--></td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td id="fehcaApertura" align="right">&nbsp;</td>
			    <td><!-- <input type="radio" name="radio" id="rbtnUnidadDeInvestigacion" value="rbtnUnidadDeInvestigacion" />--></td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td></td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralJAR">Justicia Alternativa Restaurativa</span></td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralUI">Unidad de Investigación: </span><span id="spanInfoGralUI"></span></td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralIE">Instituci&oacute;n Externa: </span><span id="spanInfoGralIE"></span></td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			 
			</table>
		<!--	<table width="1042px"  height="22px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><img src="<%=request.getContextPath()%>/resources/images/pleca_down_grales.jpg"></td>
				</tr>
			</table>     -->					
		</div>
		<!--TERMINA TAB GENERALES-->
	<!--COMIENZAN TABS INFERIORES DE ACTUACIONES-->
		<div id="tabs-7" class="tabTabsAcciones">		
			<div id="tabschild7" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild7-1">Actuaciones</a></li>
					<li><a href="#tabschild7-2">Relacionar Información del expediente</a></li>					
				</ul>				
				<div id="tabschild7-1">					
					<!--<table width="600" border="0" cellspacing="0" cellpadding="0" id="tablaAcusePenal" style="display: none;">
					  <tr>
						<td width="250">&nbsp;</td>
						<td width="350">&nbsp;</td>
					  </tr>
					  <tr>
						<td  style="background-color:#91B8E8"align="right">
							<span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8">
								<strong>Generar Documento:</strong>
							</span>
						</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td align="left">
							<input type="button" name="btnAccDenuncia" id="btnAccDenuncia" value="Denuncia" onclick="generarDocumentoSinCaso();"/>
						</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td align="left">
							<input type="button" name="btnAccCapturarEntrevista" id="CapturarEntrevista" value="CapturarEntrevista" />
						</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Solicitud:</strong></span></td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Oficio de Canalizaci&oacute;n:</strong></span></td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td><input type="button" value="Canalizar a Justicia &#10; restaurativa" id="btnCanalizaAJR"/></td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td>
							<table>
								<tr>
									<td>
										<div id="divCanalizaAUI">
											Canalizar a Unidad de Investigaci&oacute;n<br/>
											<select id="cbxCanalizaAUI" name="cbxCanalizaAUI">
												<option selected="selected" style="height: 20px;">-Seleccione-</option>
											</select>
										</div>
										<div id="divCanalizaAIE">
											Canalizar a Instituci&oacute;n Externa:<br/>
											<select id="cbxCanalizaAIE">
												<option selected="selected"  style="height: 20px;">-Seleccione-</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2"><input type="button" id="btnGenerarAcciones" value="Generar"/></td>
								</tr>
							</table>
						</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					</table>
				
				-->
				<table width="820" border="0" cellspacing="0" cellpadding="0" id="tablaAcuseNoPenal">
			<!-- <tr>
				<td width="250">&nbsp;</td>
				<td width="350">&nbsp;</td>
			</tr>
			<tr>
				<td style="background-color: #91B8E8" align="right"><span
					style="border-left: #000000; border-top: #000000 border-bottom-width : 4; font-size: 14px; background-color: #91B8E8">
				<strong>Generar Documento:</strong> </span></td>
				<td>&nbsp;</td>
			</tr> -->
			<tr>
				<td id="tdCbxAccionesTab1">Actuaciones:</td>
				<td id="tdCbxAccionesTab"><select id="cbxAccionesTab" style="width:470px">
					<option value="-1">-Seleccione-</option>
				</select></td>
				<td>
				<button value="Elaborar teoria del caso" id="idTeoriaCaso" class="btn_Generico" onclick="abreTeoria()">Elaborar teoria del caso</button>
				</td>
				<td>
					<div id="idRadiosBUt" style="display: none;">
					<table>
						<tr>
						<td>
						Mediación
						</td>
						<td>
						<input type="radio" name="rbConci" id="raio1" checked="checked" />
						</td>
						</tr>
						<tr>
						<td>
						Conciliación
						</td>
						<td>
						<input type="radio" name="rbConci" id="raio2" />
						</td>
						</tr>
						
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td id="tdCbxAjentesCoorJAR1">Facilitadores:</td>
				<td id="tdCbxAjentesCoorJAR"><select id="cbxAjentesCoorJAR" style="width:470px">
					<option value="-1">-Seleccione-</option>
					
				</select></td>
				<td id="tdCbxAjentesCoorUI1">Agentes:</td>
				<td id="tdCbxAjentesCoorUI"><select id="cbxAjentesCoorUI" style="width:470px">
					<option value="-1">-Seleccione-</option>
					
				</select></td>
				<td>
				<button value="Asignar a Agente MP" id="idAsignarAgenteMp" class="btn_Generico" onclick="asignarAgenteMP()">Asignar a Agente MP</button>
				<button value="Asignar a Facilitador" id="idAsignarFacilitador" class="btn_Generico" onclick="asignarFacilitador()">Asignar a Facilitador</button>
				</td>
			</tr>
			<tr id="idbotoncarpeta" style="display: none;">
			<td > 
					
				</td>
				<td > 
					
				</td>
				<td>
				<button value="Enviar de Investigacion" class="btn_Generico" onclick="lanzaCarpetaInvestigacionDefensoria()">Enviar Carpeta de Investigacion</button>
				</td>
			</tr>
		<!-- <tr>
				<td align="left"><input type="button" name="btnAccDenuncia"
					id="btnAccDenuncia" value="Denuncia"
					onclick="generarDocumentoSinCaso();" /> <input type="button"
					name="btnAccQuerella" id="btnAccQuerella" value="Querella" /></td>
				<td></td>
			</tr>
			<tr>
				<td align="left"><input type="button" id="btnAccTestimonio"
					value="Testimonio" onclick="" /><br />
				<input type="button" id="btnAccDeclaraciones" value="Declaraciones" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="left"><input type="button" id="btnAccAdjDocDig"
					value="Adjuntar Documento digital" onclick="" /></td>
				<td></td>
			</tr>
			<tr>
				<td align="left">
				<div id="oficioCumplimiento" style="display: none;"><input
					type="button" name="btnAccDenuncia" id=""
					value="Oficio de Cumplimiento de Acuerdo" /></div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="left">
				<div id="convenioJusticia" style="display: none;"><input
					type="button" name="btnAccDenuncia" id=""
					value="Convenio de Justicia Alternativa" /></div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="left">
				<div id="testimonio" style="display: none;"><br>
				<input type="button" name="btnAccDenuncia" id="btnTestigo"
					value="Testimonio" /></div>
				</td>
				<td></td>
			</tr>

			<tr>
				<td align="left">
				<div id="declaraciones" style="display: none;"><br>
				<input type="button" name="btnAccDenuncia" id="btnDeclaraciones"
					value="Declaraciones" /></div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="left">
				<div id="recursos" style="display: none;"><br>
				<input type="button" name="btnAccDenuncia" id="btnRecurso"
					value="Recursos" /></div>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="left">-->
				<!--<input type="button" name="btnAccCapturarEntrevista" id="CapturarEntrevista" value="CapturarEntrevista" />-->
				<!--</td>
				<td>&nbsp;</td>
			</tr>-->
			<!--
					  <tr>
						<td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Solicitud:</strong></span></td>
						<td>&nbsp;</td>
					  </tr>
					  -->
			<!-- <tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="background-color: #91B8E8" align="right"><span
					style="border-left: #000000; border-top: #000000 border-bottom-width : 4; font-size: 14px; background-color: #91B8E8"><strong>Generar
				Oficio de Canalizaci&oacute;n:</strong></span></td>
				<td align="right"> -->
				<!-- <input type="button" name="btnGuardadoDefinitivo" id="btnGardadoDefini"	value="Guardado Definitivo" /> -->
		<!--		</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="button"
					value="Canalizar a Justicia &#10; restaurativa" id="btnCanalizaAJR" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
				<table>
					<tr>
						<td>
						<div id="divCanalizaAUI">Canalizar a Unidad de
						Investigaci&oacute;n<br />
						<select id="cbxCanalizaAUI" name="cbxCanalizaAUI">
							<option selected="selected" style="height: 20px;">-Seleccione-</option>
						</select></div>
						<div id="divCanalizaAIE">Canalizar a Instituci&oacute;n
						Externa:<br />
						<select id="cbxCanalizaAIE">
							<option selected="selected" style="height: 20px;">-Seleccione-</option>
						</select></div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="btnGenerarAcciones"
							value="Generar" /></td>
					</tr>
				</table>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr> -->
		</table>
				</div>
				<div id="tabschild7-2">					
					<table width="80%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="idRelacionarElementos">
								<jsp:include page="relacionarElementosView.jsp"></jsp:include>
							</a></td>
						</tr>
					</table>
				</div>
			</div>
					
		</div>
<!--TERMINAN TABS INFERIORES DE ACTUACIONES-->
		<div id="tabs-8" class="tabTabsPeri">
		<br>
		<br>
			<!-- <center>
				<table id="gridDetalleFrmPrincipalDos" align="center"></table>
				<div id="pagerDos"></div>
				
			</center> -->
			<jsp:include page="solicitudesTabPericialesView.jsp"></jsp:include>
		</div>
		<div id="tabs-9" class="tabTabsPolMin">
			<div id="tabschild9" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild9-1">Actuaciones</a></li>
					<!-- li><a href="#tabschild9-2">Documentos</a></li-->					
				</ul>				
				<div id="tabschild9-1">					
					<table width="800" border="0" cellspacing="0" cellpadding="0" id="">
						<tr>
							<td id="tdCbxAccionesTab9">Actuaciones:</td>
							<td id="tdCbxAccionesTab9"><select id="cbxAccionesTab9" style="width:470px">
								<option value="-1">-Seleccione-</option>
							</select></td>
							<td>
								<input type="button" id="idInvestiga" onclick="abreLineasInvestiga()" value="Investigación" class="btn_Generico"/>
							</td>
							<td>
							</td>
						</tr>
					</table>
				</div>
				<!-- 
				<div id="tabschild9-2">					
					<table width="80%" cellpadding="0" cellspacing="0">
						<tr>
							<td></td>
						</tr>
					</table>
				</div-->
			</div>
					
		</div>
		<div id="tabs-10" class="tabTabsCadCus">
				 <input type="button" class="btn_grande" id="btnCadCusNuevaCadCus" style="width: 220px;" value="Crear nueva cadena de custodia"/>
				 <input type="button" class="btn_grande" id="btnCadCusConsultaCadCus" style="width: 200px;" value="Consultar cadena de custodia"/>   
  				 <input type="button" class="btn_grande" id="btnCadCusRegEslabones" style="width: 200px;" value="Registrar eslabones"/>
  				 <input type="button" class="btn_grande" id="btnCadCusRepEvidencias" style="width: 200px;" style="width: 250px;" value="Reporte de evidencias"/> 
  				 <input type="button" class="btn_grande" id="btnCadCusElabOficio" style="width: 250px;" value="Elaborar oficio para fijación y preservación"/>  
   				 <input type="button" class="btn_grande" id="btnCadCusAdmDestino" style="width: 250px;" value="Administrar destino legal de evidencia"/>
		</div>
		<div id="tabs-11" class="tabTabsDocs">
		<br>
			<table id="gridDetalleFrmPrincipal"></table>
			<div id="pager1Documentos"></div>
			<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" name="documentoId" />
				</form>
				<form name="frmDoc2" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
					<input type="hidden" name="formaId" />
					<input type="hidden" name="numeroUnicoExpediente" />
				</form>
			<table id="tableDocs">
				<tr>
					<td>Total de documentos:</td>
					<td id="idTotalDocumentos">&nbsp;</td>
				</tr>
			</table>
		</div>
		<div id="tabs-12" class="tabTabsAudiencias">
		<br>
			<table id="tablePestanaAudiencias" align="center" width="100%" height="100%">
			<tr>
				<td>
				<table>
				<tr><td>
					<input type="button" id="btnAdminMediCaute" value="Administrar medidas cautelares" class="btn_Generico">
					</td>
					<td>
					<input type="button" id="btnTranscripcionAudiencia" value="Transcripción de audiencia" class="btn_Generico">
					</td>
					</tr>
					</table>
				</td>
				<td>
				
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="btnAudManJud" value="Mandamientos judiciales" class="btn_Generico">
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table id="gridDetalleFrmPrincipalAudiencias"></table>
					<div id="pagerAudiencias"></div>
				</td>
			<tr>
			</table>
		</div>
		<div id="tabs-13" class="tabTabsCriterio">
		<br>
			<table id="tablePestanaCriterio" align="center" width="100%" height="100%">
			<tr>
				<td>
					Si
				</td>
				<td align="left">
				No
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="noJuridico" id="juridicoSi" onclick="validaCriterios()">
				</td>
				<td>
					<input type="radio" name="noJuridico" id="juridicoNo" onclick="validaCriterios()"> No se afecta bien jurídico.
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="noImputado" id="imputadoSi" onclick="validaCriterios()">
				</td>
				<td>
					<input type="radio" name="noImputado" id="imputadoNo" onclick="validaCriterios()"> El Imputado sufrió daños graves.
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="noPena" id="penaSi" onclick="validaCriterios()">
				</td>
				<td>
					<input type="radio" name="noPena" id="penaNo" onclick="validaCriterios()"> La pena por el hecho carece de importancia.
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="btnDocumentoCriterio" onclick="" value="Generar razones para criterio de oportunidad" class="btn_Generico">
				</td>
				<td>
					<input type="button"  id="btnDocumentoDictamen" onclick="abilitaDoc()" value="Generar Dictamen motivado de criterio de oportunidad" class="btn_Generico">
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="btnInformeCriterio" onclick="dialigoDictamenOprtunidad()" value="Informar criterio de oportunidad" class="btn_Generico">
				</td>
				<td>
					<input type="button"  id="btnTurnarInpugna" onclick="dialigoImpugnacion()" value="Turnar inpugnación de criterio de oportunidad" class="btn_Generico">
				</td>
			</tr>
			</table>
		</div>
		<div id="tabs-14" class="tabTabsAlertas">
		<br>
			<table id="gridDetalleAlertas"></table>
			<div id="pagerGridDetalleAlertas"></div>
		</div>
	</div>
	<!--TERMINAN TABS SUPERIORES (PRINCIPALES)-->
	<!-- DIV para el dialogo de asociacion de un delito por persona -->
		<div id="dialogDos-confirm" title="Establecer Delito Por Persona" >
		<p align="left">
			Delitos del Expediente : 
			<select id="cbxDelitosExpRDPPV">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<br/>
			Grado de participaci&oacute;n : 
			<select id="cbxFormasParticipacionRDPPV">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<br/>
			V&iacute;ctimas : 
			<select id="cbxVictimasExpRDPPV">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<table width="650" border="0" cellspacing="0">
			  <tr>
			    <td width="100"><div align="right">Clasificaci&oacute;n :</div></td>
			    <td><select id="cbxClasificacionRDPPV">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Lugar :</div></td>
			    <td><select id="cbxLugarRDPPV">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Modalidad :</div></td>
			    <td><select id="cbxModalidadRDPPV">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Modus :</div></td>
			    <td><select id="cbxModusRDPPV">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Causa :</div></td>
			    <td><select id="cbxCausaRDPPV">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			</table>
		</p>
	</div>
	<!-- FIN DIV para el dialogo de asociacion de un delito por persona -->
	
	<!-- DIV para el dialogo de asociacion de un delito por delito -->
		<div id="dialogTres-confirm" title="Establecer Delito Por Delito" >
		<p align="left">
			Probables Responsables : 
			<select id="cbxProbableResponsableExpRDPD">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<br/>
			Formas de participaci&oacute;n : 
			<select id="cbxFormasParticipacionRDPD">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<br/>
			V&iacute;ctimas : 
			<select id="cbxVictimasExpRDPD">
				<option selected="selected" value="-1">-Seleccione-</option>
			</select>
			<table width="650" border="0" cellspacing="0">
			  <tr>
			    <td width="100"><div align="right">Clasificaci&oacute;n :</div></td>
			    <td><select id="cbxClasificacionRDPD">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Lugar :</div></td>
			    <td><select id="cbxLugarRDPD">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Modalidad :</div></td>
			    <td><select id="cbxModalidadRDPD">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Modus :</div></td>
			    <td><select id="cbxModusRDPD">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			  <tr>
			    <td><div align="right">Causa :</div></td>
			    <td><select id="cbxCausaRDPD">
			      <option selected="selected" value="-1">-Seleccione-</option>
			    </select></td>
			  </tr>
			</table>
		</p>
	</div>
	<!-- FIN DIV para el dialogo de asociacion de un delito por delito -->
	<!-- DIV para el dialogo de criterios de oportunidad -->
	<div id="dialogCriterios-confirm" title="Criterio de Opotunidad" >
		<p align="left">
			¿Desea ejercer el criterio de oportunidad? 
		</p>
	</div>
	<!-- DIV para el dialogo de envio de dictamen -->
	<div id="dialogDictamen-confirm" title="Criterio de Opotunidad Envío de Dictamen " >
		<p align="left">
			¿Desea enviar el dictamen de criterio de oportunidad? 
		</p>
	</div>
	<!-- DIV para el dialogo de inpugnacion de criterio -->
	<div id="dialogImpugnacion-confirm" title="Criterio de Opotunidad Impugnación " >
		<p align="left">
			¿Desea impugnar el criterio de oportunidad? 
		</p>
	</div>
	<!-- DIV para el dialogo de inpugnacion de criterio para archivo -->
	<div id="dialogImpugnacionARchivo-confirm" title="Criterio de Opotunidad Impugnación " >
		<p align="left">
			Seleccionar Archivo a adjuntar:
			<input type="file">
		</p>
	</div>
	
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center"><span id="divAlertTexto"></span></td>
			</tr>
		</table>
	</div>
	
	
</body>
<script type="text/javascript">
$( "#dialogDos-confirm" ).dialog();
$( "#dialogTres-confirm" ).dialog();
$( "#dialogDos-confirm" ).dialog( "destroy" );
$( "#dialogTres-confirm" ).dialog( "destroy" );

$( "#dialogCriterios-confirm" ).dialog();
$( "#dialogCriterios-confirm" ).dialog( "destroy" );
$( "#dialogDictamen-confirm" ).dialog();
$( "#dialogDictamen-confirm" ).dialog( "destroy" );
$( "#dialogImpugnacion-confirm" ).dialog();
$( "#dialogImpugnacion-confirm" ).dialog( "destroy" );
$( "#dialogImpugnacionARchivo-confirm" ).dialog();
$( "#dialogImpugnacionARchivo-confirm" ).dialog( "destroy" );
$('#btnInformeCriterio').hide();
$('#btnTurnarInpugna').hide();
$('#btnDocumentoCriterio').hide();
$('#btnDocumentoDictamen').hide();
function validaCriterios(){
	
	if($('#juridicoSi').is(':checked') &&$('#imputadoSi').is(':checked') &&$('#penaSi').is(':checked') ){
		dialigoCriterios();
	}else{
		criterioOportinidadOp=0;
	}
}

function abilitaDoc(){
	$('#btnInformeCriterio').attr("disabled","");
	$('#btnTurnarInpugna').attr("disabled","");
	$('#btnDocumentoDictamen').attr("disabled","disabled");
	
}


function dialigoCriterios(){

	//generamos el Dialogo
	$( "#dialogCriterios-confirm" ).dialog({
		resizable: false,
		height:150,
		width:300,
		modal: true,
		buttons: {
			"Si": function() {
				$( this ).dialog( "close" );
				$( "#dialogCriterios:ui-dialog" ).dialog( "destroy" );
				//RelacionarDelitoRDPPV();
				criterioOportinidadOp=1;
				$('#btnInformeCriterio').show();
				$('#btnTurnarInpugna').show();
				$('#btnDocumentoCriterio').show();
				$('#btnDocumentoDictamen').show();
				$('#btnInformeCriterio').attr("disabled","disabled");
				$('#btnTurnarInpugna').attr("disabled","disabled");
			},
			"No": function() {
				$( this ).dialog( "close" );
				$( "#dialogCriterios:ui-dialog" ).dialog( "destroy" );
				criterioOportinidadOp=0;
				 $('#juridicoNo').attr('checked','checked');
				 $('#imputadoNo').attr('checked','checked');
				 $('#penaNo').attr('checked','checked');
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}

function dialigoDictamenOprtunidad(){

	//generamos el Dialogo
	$( "#dialogDictamen-confirm" ).dialog({
		resizable: false,
		height:150,
		width:300,
		modal: true,
		buttons: {
			"Si": function() {
				$( this ).dialog( "close" );
				$( "#dialogDictamen:ui-dialog" ).dialog( "destroy" );
				$('#juridicoNo').attr("disabled","disabled");
				$('#juridicoSi').attr("disabled","disabled");
				$('#imputadoSi').attr("disabled","disabled");
				$('#imputadoNo').attr("disabled","disabled");
				$('#penaNo').attr("disabled","disabled");
				$('#penaSi').attr("disabled","disabled");
				$('#btnInformeCriterio').attr("disabled","disabled");
			},
			"No": function() {
				$( this ).dialog( "close" );
				$( "#dialogDictamen:ui-dialog" ).dialog( "destroy" );
				
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}

function dialigoImpugnacion(){

	//generamos el Dialogo
	$( "#dialogImpugnacion-confirm" ).dialog({
		resizable: false,
		height:150,
		width:300,
		modal: true,
		buttons: {
			"Si": function() {
				$( this ).dialog( "close" );
				$( "#dialogImpugnacion:ui-dialog" ).dialog( "destroy" );
				dialigoImpugnacionArchivo();
			},
			"No": function() {
				$( this ).dialog( "close" );
				$( "#dialogImpugnacion:ui-dialog" ).dialog( "destroy" );
				
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}

function dialigoImpugnacionArchivo(){

	//generamos el Dialogo
	$( "#dialogImpugnacionARchivo-confirm" ).dialog({
		resizable: false,
		height:150,
		width:300,
		modal: true,
		buttons: {
			"Aceptar": function() {
				$( this ).dialog( "close" );
				$( "#dialogImpugnacionARchivo:ui-dialog" ).dialog( "destroy" );
				$.newWindow({id:"iframewindowSolicitarAudiencia", statusBar: true, posx:20,posy:20,width:750,height:530,title:"Solicitar Audiencia", type:"iframe"});
                $.updateWindowContent("iframewindowSolicitarAudiencia",'<iframe src="<%= request.getContextPath() %>/solicitarAudiencia.jsp"    width="750" height="530" />');
			},
			"Cancelar": function() {
				$( this ).dialog( "close" );
				$( "#dialogImpugnacionARchivo:ui-dialog" ).dialog( "destroy" );
				
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}
//ocultamos las leyendas en la carga de la pagina
$("#leyendaUnDelitoGrave,#leyendaNingunDelitoGrave").hide();
</script>
</html>