<%@page import="mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica"%>

<%@page import="mx.gob.segob.nsjp.comun.enums.objeto.Objetos"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO"%>

<%@page import=" mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="java.util.List"%>

<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpActuacionesExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpDocumentosExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpInvolucradosExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpDiligenciasExpediente.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
      
	<style type="text/css">
	.texto{
		width: 225px; 
		border: 0; 
		background: #DDD;
	}
	.transpa {
		background-color: transparent;
		border: 0;
	}
			DD P {
				LINE-HEIGHT: 120%
			}
			#cedulaIdentificacion {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 2px;
				WIDTH: 1030px;
				PADDING-RIGHT: 0px;
				HEIGHT: 380px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#cedulaIdentificacion DL {
				WIDTH: 1030px; HEIGHT: 390px
			}
			/*acordeon editar*/
			#cedulaIdentificacion DT {
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
			#cedulaIdentificacion DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#cedulaIdentificacion DT.hover {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DT.hover.active {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DD {
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
			#cedulaIdentificacion .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#cedulaIdentificacion .active .slide-number {
				COLOR: #fff
			}
			#cedulaIdentificacion A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#cedulaIdentificacion DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#cedulaIdentificacion H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#cedulaIdentificacion .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
			
				<!--ESTILOS PARA LAS TABS-->
	
	
		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 600px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
		
	</style>

    <script>
<% 
	ExpedienteDTO exp = (ExpedienteDTO)request.getAttribute("expediente");
	long etapaId = exp.getEtapa().getIdCampo();
	long idExpediente = (Long)(request.getSession().getAttribute("expedienteId"));
	long idExpedienteOK = (Long)(request.getSession().getAttribute("expedienteId"));
	long numeroExpedienteIdOK = exp.getNumeroExpedienteId();
	
	long idNumeroExpediente = exp.getNumeroExpedienteId();
	

	long estatusExp = exp.getEstatus().getIdCampo();	
	String numeroExpediente = exp.getNumeroExpediente();
	UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("KEY_SESSION_USUARIO_FIRMADO");
	FuncionarioDTO funcionario = usuario.getFuncionario();
%>

	//Inician variables para filtrar el catalogo de situacion juridica
	var idImputado = <%=SituacionJuridica.IMPUTADO.getValorId()%>;
	var idSentenciado = <%=SituacionJuridica.SENTENCIADO.getValorId()%>;
	var idDetenido = <%=SituacionJuridica.DETENIDO.getValorId()%>;
	var idInvestigado = <%=SituacionJuridica.INVESTIGADO.getValorId()%>;
	var idAcusado = <%=SituacionJuridica.ACUSADO.getValorId()%>;
	//Finalizan variables para filtrar el catalogo de situacion juridica

	var contextPath = '<%=request.getContextPath()%>';
	var nombreFuncionario = "<%= funcionario.getNombreFuncionario()%>";
	var apaterFuncionario = "<%= funcionario.getApellidoPaternoFuncionario()%>";
	var amaterFuncionario = "<%= funcionario.getApellidoMaternoFuncionario()%>";
	var involucrados=new Array();
	var idWindowGenerarNotas=1;
	var idWindowAsntarRegCadCus=1;
	var situacionActualDefendido="";
	var idExpedienteop = '<%=idExpedienteOK%>';
	var idNumeroExpedienteOp = '<%=numeroExpedienteIdOK%>';
	var numeroExpediente= '<%=numeroExpediente%>';
	var numeroExpedienteId= idNumeroExpedienteOp;
	
	var idExpedienteOK = '<%=idExpedienteOK%>';
	var idNumeroExpedienteOK = '<%=numeroExpedienteIdOK%>';
		
	
	var nombreDefendido="";
<%
	if(exp.getEtapasPasadas() != null && !exp.getEtapasPasadas().isEmpty()){
		List<EtapasExpediente> etapasExpediente = null;
		etapasExpediente = exp.getEtapasPasadas();
		
		for(EtapasExpediente etapa: etapasExpediente){
		%>
			if('<%=etapa%>' == 'INTEGRACION'){
				loadEtapaIntegracion();
			}else if('<%=etapa%>' == 'TECNICA'){
				loadEtapaTecnica();	
			}else if('<%=etapa%>' == 'CONCILIACION_Y_MEDIACION'){
				loadEtapaRestaurativa();
			}else if('<%=etapa%>' == 'EJECUCION'){
				loadEtapaEjecucion();
			}else if('<%=etapa%>' == 'ASESORIA'){
				loadEtapaAsesoria();
			}
		<%
		}
	}
%>

<%
	List<InvolucradoDTO> involucrados = null;
	involucrados = exp.getInvolucradoByCalidad(Calidades.DEFENDIDO);
	if(involucrados != null && !involucrados.isEmpty()){
%>		involucrados["DEFENDIDO"]=<%=involucrados.get(0).getElementoId()%>;<%
	}
	involucrados = exp.getInvolucradoByCalidad(Calidades.DENUNCIANTE);
	if(involucrados != null && !involucrados.isEmpty()){
%>	involucrados["DENUNCIANTE"]=<%=involucrados.get(0).getElementoId()%>;<%
	}
	involucrados = exp.getInvolucradoByCalidad(Calidades.SOLICITANTE);
	if(involucrados != null && !involucrados.isEmpty()){
%>	involucrados["SOLICITANTE"]=<%=involucrados.get(0).getElementoId()%>;<%
	}
	involucrados = exp.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
	if(involucrados != null && !involucrados.isEmpty()){
%>	involucrados["PROBABLE_RESPONSABLE_PERSONA"]=<%=involucrados.get(0).getElementoId()%>;<%
	}
	involucrados = exp.getInvolucradoByCalidad(Calidades.QUIEN_DETUVO);
	if(involucrados != null && !involucrados.isEmpty()){
%>	involucrados["QUIENDETUVO"]=<%=involucrados.get(0).getElementoId()%>;
<%
	}
%>

	/*
	* Identificadores de los frames ingresar Objetos y evidencias
	*/
	var idWindowIngresarVehiculo = 1;
	var idWindowIngresarEquipoDeComputo = 1;
	var idWindowIngresarEquipoTelefonico = 1;
	var idWindowIngresarArma = 1;
	var idWindowIngresarExplosivo = 1;
	var idWindowIngresarSustancia = 1;
	var idWindowIngresarAnimal = 1;	
	var idWindowIngresarAeronave = 1;
	var idWindowIngresarEmbarcacion = 1;
	var idWindowIngresarInmueble = 1;
	var idWindowIngresarNumerario = 1;
	var idWindowIngresarVegetal = 1;
	var idWindowIngresarDocumentoOficial = 1;
	var idWindowIngresarJoya = 1;
	var idWindowIngresarObraDeArte = 1;

	var contextoPagina = "${pageContext.request.contextPath}";

	$(document).ready(function() {		
	
		consultaDetalleExp();
		$("#tabedMenuExpedienteDefensoria").tabs();
		$("#tabs").tabs();
		$("#tabschild4").tabs();
		$("#tabschild14").tabs();
		
		
		$("#tabedMenuExpedienteDefensoria").tabs('selected', 4);
		$("#btnCadCusConsultaCadCus").click(consultarRegCadenaCustodia);
		$("#rdbMenuInterRelDelXPersona").bind("click",ocultaMuestraTblsRelacionarDelitos);
		$("#rdbMenuInterRelDelXDelito").bind("click",ocultaMuestraTblsRelacionarDelitos);
		$("#rdbMenuInterRelDelXTodos").bind("click",ocultaMuestraTblsRelacionarDelitos);
	

		manageTabs();
		killDomicilioNotificaciones();
		killCoordenadasGeograficas();

		//Se agrega el evento click para crear nuevos objetos
		$("#nuevoVehiculo").click(creaNuevoVehiculo);
		$("#nuevoEquipoDeComputo").click(creaNuevoEquipoDeComputo);
		$("#nuevoEquipoTelefonico").click(creaNuevoEquipoTelefonico);
		$("#nuevaArma").click(creaNuevaArma);
		$("#nuevoExplosivo").click(creaNuevoExplosivo);
		$("#nuevaSustancia").click(creaNuevaSustancia);
		$("#nuevoAnimal").click(creaNuevoAnimal);		
		$("#nuevaAeronave").click(creaNuevaAeronave);
		$("#nuevaEmbarcacion").click(creaNuevaEmbarcacion);
		$("#nuevoInmueble").click(creaNuevoInmueble);
		$("#nuevoNumerario").click(creaNuevoNumerario);
		$("#nuevoVegetal").click(creaNuevoVegetal);
		$("#nuevoDocumentoOficial").click(creaNuevoDocumentoOficial);
		$("#nuevaJoya").click(creaNuevaJoya);
		$("#nuevaObraDeArte").click(creaNuevaObraDeArte);

		//ocultamos las leyendas en la carga de la pagina
		$("#leyendaUnDelitoGrave,#leyendaNingunDelitoGrave").hide();
		$("#btnGuardarDelitosAg").hide();
		
		/*SECCION PARA DECLARAR LOS GRIDS DE LOS OBJETOS EN EL VISOR INTERMEDIO*/
		jQuery("#gridObjsVehiculo").jqGrid({ 
			url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>', 
			datatype: "xml", 
			colNames:['Vehículo','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Vehiculo',index:'vehiculo', width:100}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:false}, 
					],
			pager: jQuery('#pagerGridObjsVehiculo'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
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
			colModel:[ 	{name:'Arma',index:'arma', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsArma'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
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
			colNames:['Equipo de Cómputo','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'EquipoComputo',index:'equipoComputon', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsEquipoComputo'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"EQUIPO DE COMPUTO",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsVehiculo',
			ondblClickRow: function(id){
				consultarEquipoComputo(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Equipo Telefonico
		jQuery("#gridObjsEquipoTelefonico").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Equipo Telefonico','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'EquipoTelefonico',index:'equipoTelefonicon', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsEquipoTelefonico'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"EQUIPO TELEFONICO",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsEquipoTelefonico',
			ondblClickRow: function(id){
				consultarEquipoTelefonico(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Explosivo
		jQuery("#gridObjsExplosivo").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Explosivo','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Explosivo',index:'explosivon', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsExplosivo'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"EXPLOSIVO",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsExplosivo',
			ondblClickRow: function(id){
				consultarExplosivo(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Sustancia
		jQuery("#gridObjsSustancia").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Sustancia','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Sustancia',index:'sustancian', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsSustancia'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"SUSTANCIA",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsSustancia',
			ondblClickRow: function(id){
				consultarSustancia(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Animal
		jQuery("#gridObjsAnimal").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Animal','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Animal',index:'animaln', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsAnimal'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"ANIMAL",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsAnimal',
			ondblClickRow: function(id){
				consultarAnimal(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Aeronave
		jQuery("#gridObjsAeronave").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Aeronave','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Aeronave',index:'aeronaven', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsAeronave'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"AERONAVE",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsAeronave',
			ondblClickRow: function(id){
				consultarAeronave(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Embarcacion
		jQuery("#gridObjsEmbarcacion").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Embarcacion','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Embarcacion',index:'embarcacionn', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsEmbarcacion'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"EMBARCACION",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsEmbarcacion',
			ondblClickRow: function(id){
				consultarEmbarcacion(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Inmueble
			jQuery("#gridObjsInmueble").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Inmueble','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Inmueble',index:'inmueblen', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsInmueble'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"INMBUEBLE",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsInmueble',
				onSelectRow: function(id){
					consultarInmueble(id);
					},
				sortorder: "desc"
			});
		
		//Grid de Numerario
		jQuery("#gridObjsNumerario").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Numerario','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Numerario',index:'numerarion', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsNumerario'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"NUMERARIO",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsNumerario',
			ondblClickRow: function(id){
				consultarNumerario(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Vegetal
		jQuery("#gridObjsVegetal").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Vegetal','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Vegetal',index:'vegetaln', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsVegetal'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"VEGETAL",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsVegetal',
			onSelectRow: function(id){
				consultarVegetal(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Documento Oficial
		jQuery("#gridObjsDocumentoOficial").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Documento oficial','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'DocumentoOficial',index:'documentoOficialn', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsDocumentoOficial'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"DOCUMENTO OFICIAL",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsDocumentoOficial',
			ondblClickRow: function(id){
				consultarDocumentoOficial(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Joya
		jQuery("#gridObjsJoya").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Joya','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'Joya',index:'joyan', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsJoya'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"JOYA",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsJoya',
			ondblClickRow: function(id){
				consultarJoya(id);
				},
			sortorder: "desc"
		});
		
		//Grid de Obra de Arte
		jQuery("#gridObjsObraDeArte").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Obra de arte','Folio de cadena de custodia', 'No. de casos asociados'], 
			colModel:[ 	{name:'ObraDeArte',index:'obraDeArten', width:150}, 
						{name:'FolioCadCus',index:'folioCadCus', width:150}, 
						{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
					],
			pager: jQuery('#pagerGridObjsObraDeArte'),
			rowNum:10,
			rowList:[10,20,30,40,50,60],
			width:600,
			caption:"OBRA DE ARTE",
			sortname: 'Clave',
			viewrecords: true,
			id: 'gridObjsObraDeArte',
			ondblClickRow: function(id){
				consultarObraDeArte(id);
				},
			sortorder: "desc"
		});		
		/*FIN - SECCION PARA DECLARAR LOS GRIDS DE LOS OBJETOS EN EL VISOR INTERMEDIO*/
		
		//carga el editor de texto
		var config = {					
				toolbar:
					[
						['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
						['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
						['NumberedList','BulletedList','-','Outdent','Indent'],
						['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
						['Table','HorizontalRule'],
						'/',
						['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
					]
			};		
	
		$('.jquery_ckeditor').ckeditor(config);

		//activa la funcionalidad del acordeon
		$('#cedulaIdentificacion').easyAccordion({ 
			  autoStart: false, 
			  slideInterval: 3000
		});
		
	});
	
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

	//SECCION para llenar los grids de los objetos en el visor intermedio
	function consultaGridVehiculosVisor()
	{	
		jQuery("#gridObjsVehiculo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>',datatype: "xml" });
		$("#gridObjsVehiculo").trigger("reloadGrid");
	}
	
	function consultaGridEquiposComputoVisor()
	{	
		jQuery("#gridObjsEquipoComputo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>',datatype: "xml" });
		$("#gridObjsEquipoComputo").trigger("reloadGrid");
	}
	
	function consultaGridEquipoTelefonicoVisor()
	{	
		jQuery("#gridObjsEquipoTelefonico").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EQUIPO_TELEFONICO.getValorId() %>',datatype: "xml" });
		$("#gridObjsEquipoTelefonico").trigger("reloadGrid");
	}
	
	function consultaGridArmasVisor()
	{	
		jQuery("#gridObjsArma").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.ARMA.getValorId() %>',datatype: "xml" });
		$("#gridObjsArma").trigger("reloadGrid");
	}
	
	
	function consultaGridExplosivoVisor()
	{	
		jQuery("#gridObjsExplosivo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EXPLOSIVO.getValorId() %>',datatype: "xml" });
		$("#gridObjsExplosivo").trigger("reloadGrid");
	}
	
	function consultaGridSustanciaVisor()
	{	
		jQuery("#gridObjsSustancia").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.SUSTANCIA.getValorId() %>',datatype: "xml" });
		$("#gridObjsSustancia").trigger("reloadGrid");
	}
	
	function consultaGridAnimalVisor()
	{	
		jQuery("#gridObjsAnimal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.ANIMAL.getValorId() %>',datatype: "xml" });
		$("#gridObjsAnimal").trigger("reloadGrid");
	}
	
	function consultaGridAeronaveVisor()
	{	
		jQuery("#gridObjsAeronave").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.AERONAVE.getValorId() %>',datatype: "xml" });
		$("#gridObjsAeronave").trigger("reloadGrid");
	}
	
	function consultaGridEmbarcacionVisor()
	{	
		jQuery("#gridObjsEmbarcacion").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EMBARCACION.getValorId() %>',datatype: "xml" });
		$("#gridObjsEmbarcacion").trigger("reloadGrid");
	}
	
	function consultaGridInmuebleVisor()
	{	
		jQuery("#gridObjsInmueble").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.INMUEBLE.getValorId() %>',datatype: "xml" });
		$("#gridObjsInmueble").trigger("reloadGrid");
	}
	
	function consultaGridNumerarioVisor()
	{	
		jQuery("#gridObjsNumerario").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.NUMERARIO.getValorId() %>',datatype: "xml" });
		$("#gridObjsNumerario").trigger("reloadGrid");
	}
	
	function consultaGridVegetalVisor()
	{	
		jQuery("#gridObjsVegetal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEGETAL.getValorId() %>',datatype: "xml" });
		$("#gridObjsVegetal").trigger("reloadGrid");
	}
	
	function consultaGridDocumentoOficialVisor()
	{	
		jQuery("#gridObjsDocumentoOficial").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>',datatype: "xml" });
		$("#gridObjsDocumentoOficial").trigger("reloadGrid");
	}
	
	function consultaGridJoyaVisor()
	{	
		jQuery("#gridObjsJoya").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.JOYA.getValorId() %>',datatype: "xml" });
		$("#gridObjsJoya").trigger("reloadGrid");
	}
	
	function consultaGridObraDeArteVisor()
	{	
		jQuery("#gridObjsObraDeArte").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.OBRA_DE_ARTE.getValorId() %>',datatype: "xml" });
		$("#gridObjsObraDeArte").trigger("reloadGrid");
	}
	//FIN SECCION para llenar los grids de los objetos en el visor intermedio
	
	function creaNuevoVehiculo(){
		 idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=VEHICULO&idVehiculo=0 " width="570" height="600" />');
	    consultaGridVehiculosVisor();
	}
	
	
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
	

	function creaNuevoExplosivo(){
		 idWindowIngresarExplosivo++;
		$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente+'" width="880" height="330" />');
	}

	
	function creaNuevaSustancia(){
		 idWindowIngresarSustancia++;
		$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'" width="900" height="330" />');
	}

	
	function creaNuevoAnimal(){
		 idWindowIngresarAnimal++;
		$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'" width="900" height="330" />');
	}

	

	
	//RGG Inicia seccion de consultas 	
	function consultarVehiculo(idVehiculo){
		 idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente+'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO " width="570" height="600" />');
	}


	function creaNuevaAeronave(){
		 idWindowIngresarAeronave++;
		$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'" width="600" height="530" />');
	}

	function creaNuevaEmbarcacion(){
		 idWindowIngresarEmbarcacion++;
		$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'" width="600" height="530" />');
	}

	function creaNuevoInmueble(){
		 idWindowIngresarInmueble++;
		$.newWindow({id:"iframewindowIngresarInmueble" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:0,width:768,height:540,title:"Ingresar inmueble", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarInmueble" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');
	}
	 
	function creaNuevoNumerario(){
		 idWindowIngresarNumerario++;
		$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente+'" width="800" height="350" />');
	}

	function creaNuevoVegetal(){
		 idWindowIngresarVegetal++;
		$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente +'" width="800" height="350" />');
	}
	 
	function creaNuevoDocumentoOficial(){
		 idWindowIngresarDocumentoOficial++;
		$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente +'" width="800" height="350" />');
	}
	 
	function creaNuevaJoya(){
		 idWindowIngresarJoya++;
		$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850, height:380,title:"Ingresar joya", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente +'" width="850" height="380" />');
	}
	 
	function creaNuevaObraDeArte(){
		 idWindowIngresarObraDeArte++;
		$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850, height:380,title:"Ingresar obra de arte", type:"iframe"});
	    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'" width="850" height="380" />');
	}

	function consultarRegCadenaCustodia()
	{
		idWindowAsntarRegCadCus++;
		$.newWindow({id:"iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Cadena de custodia", type:"iframe"});
	    $.updateWindowContent("iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=1&numeroExpediente='+numeroExpediente+'" width="1000" height="600" />');
	}
	


	
	//Inicia sección para consultar objetos
	
			
		function consultarVehiculo(idVehiculo){
			 idWindowIngresarVehiculo++;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO " width="570" height="600" />');
		}
	
		function consultarExplosivo(idExplosivo){
			idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880, height:330,title:"Consultar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&idExplosivo='+idExplosivo+'&tipoObjeto=EXPLOSIVO " width="880" height="330" />');
		}
	
		function consultarSustancia(idSustancia){
			idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia='+idSustancia+'&tipoObjeto=SUSTANCIA" width="900" height="330" />');
		}
		
		function consultarAnimal(idAnimal){
			idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'&idAnimal='+idAnimal+'&tipoObjeto=ANIMAL" width="900" height="330" />');
		}
		
		function consultarAeronave(idAeronave){
			idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente+'&idAeronave='+idAeronave+'&tipoObjeto=AERONAVE" width="600" height="530" />');
		}
		
		function consultarEmbarcacion(idEmbarcacion){
			idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente+'&idEmbarcacion='+idEmbarcacion+'&tipoObjeto=EMBARCACION" width="600" height="530" />');
		}
		
		function consultarNumerario(idNumerario){
			idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario='+idNumerario+'&tipoObjeto=NUMERARIO " width="800" height="350" />');
		}
		
		
		function consultarVegetal(idVegetal){
			idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente+'&idVegetal='+idVegetal+'&tipoObjeto=VEGETAL" width="800" height="350" />');
		}
		
		
		function consultarDocumentoOficial(idDocumentoOficial){
			idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente+'&idDocOfic='+idDocumentoOficial+'&tipoObjeto=DOCUMENTO_OFICIAL" width="800" height="350" />');
		}
		
	
		function consultarJoya(idJoya){
			idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente+'&idJoya='+idJoya+'&tipoObjeto=JOYA" width="850" height="380" />');
		}
		
		function consultarObraDeArte(idObraDeArte){
			idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'&idObraArte='+idObraDeArte+'&tipoObjeto=OBRA_DE_ARTE" width="850" height="380" />');
		}
	//Finaliza sección para consultar objetos
	
	function cargaVehiculo(id,tipo,placas){
		consultaGridVehiculosVisor();
		cerrarVentanaVehiculo();
	} 
	
	function cerrarVentanaVehiculo(){
		var pantalla ="iframewindowIngresarVehiculo";
		pantalla += idWindowIngresarVehiculo;
		$.closeWindow(pantalla);
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
	
	function cargaEquipoTelefonico(id,modelo){
		consultaGridEquipoTelefonicoVisor();
		cerrarVentanaEquipoTelefonico();
	} 
	
	function cerrarVentanaEquipoTelefonico(){
		var pantalla ="iframewindowIngresarEquipoTelefonico";
		pantalla += idWindowIngresarEquipoTelefonico;
		$.closeWindow(pantalla);
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
	
	
	function cargaExplosivo(id,tipo){
			 consultaGridExplosivoVisor();
			 cerrarVentanaExplosivo();
		} 
		
		function cerrarVentanaExplosivo(){
			var pantalla ="iframewindowIngresarExplosivo";
			pantalla += idWindowIngresarExplosivo;
			$.closeWindow(pantalla);
		}
	
	
	function cargaSustancia(id,tipo){
			consultaGridSustanciaVisor();
			cerrarVentanaSustancia();
	} 
		
	function cerrarVentanaSustancia(){
			var pantalla ="iframewindowIngresarSustancia";
			pantalla += idWindowIngresarSustancia;
			$.closeWindow(pantalla);
	}
	
	function cargaAnimal(id,tipo){
		 consultaGridAnimalVisor();
		 cerrarVentanaAnimal();
	} 
	
	function cerrarVentanaAnimal(){
		var pantalla ="iframewindowIngresarAnimal";
		pantalla += idWindowIngresarAnimal;
		$.closeWindow(pantalla);
	}
	
	function cargaAeronave(id,tipo){
		 consultaGridAeronaveVisor();
		 cerrarVentanaAeronave();
	} 
	
	function cerrarVentanaAeronave(){
		var pantalla ="iframewindowIngresarAeronave";
		pantalla += idWindowIngresarAeronave;
		$.closeWindow(pantalla);
	}
	
	function cargaEmbarcacion(id,tipo){
		 consultaGridEmbarcacionVisor();
		 cerrarVentanaEmbarcacion();
	} 
	
	function cerrarVentanaEmbarcacion(){
		var pantalla ="iframewindowIngresarEmbarcacion";
		pantalla += idWindowIngresarEmbarcacion;
		$.closeWindow(pantalla);
	}
	
	function cargaNumerario(id,tipo){
		 consultaGridNumerarioVisor();
		 cerrarVentanaNumerario();
	} 
	
	function cerrarVentanaNumerario(){
		var pantalla ="iframewindowIngresarNumerario";
		pantalla += idWindowIngresarNumerario;
		$.closeWindow(pantalla);
	}
	
	function cargaVegetal(id,tipo){
		 consultaGridVegetalVisor();
		 cerrarVentanaVegetal();
	} 
	
	function cerrarVentanaVegetal(){
		var pantalla ="iframewindowIngresarVegetal";
		pantalla += idWindowIngresarVegetal;
		$.closeWindow(pantalla);
	}
	
	function cargaDocOfic(id,tipo){
		 consultaGridDocumentoOficialVisor();
		 cerrarVentanaDocumentoOficial();
	} 
	
	function cerrarVentanaDocumentoOficial(){
		var pantalla ="iframewindowIngresarDocumentoOficial";
		pantalla += idWindowIngresarDocumentoOficial;
		$.closeWindow(pantalla);
	}
	
	function cargaJoya(id,tipo){
		 consultaGridJoyaVisor();
		 cerrarVentanaJoya();
	} 
	
	function cerrarVentanaJoya(){
		var pantalla ="iframewindowIngresarJoya";
		pantalla += idWindowIngresarJoya;
		$.closeWindow(pantalla);
	}
	
	
	function cargaObraDeArte(id,tipo){
		 consultaGridObraDeArteVisor();
		 cerrarVentanaObraDeArte();
	} 
	
	function cerrarVentanaObraDeArte(){
		var pantalla ="iframewindowObraDeArte";
		pantalla += idWindowIngresarObraDeArte;
		$.closeWindow(pantalla);
	}
	

	function gridRelacionarDelitosTabDelito(){
		cargaComboProbableResponsableRDPPV();
		cargaComboDelitosAAsociarRDPD();
		jQuery("#gridDetalleTabDelitoRelDel").jqGrid({ 
			url:'local', 
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
	
	function consultaDetalleExp() {
		$.ajax({type : 'POST',
			url : '<%= request.getContextPath()%>/consultaDetalleExpedienteDefensoria.do',
			data : 'numeroExpedienteId=' +idNumeroExpedienteOK+'&numeroExpediente='+'<%=numeroExpediente%>',
			async : false, dataType : 'xml',
			success : function(xml) {
				$(xml).find('involucradoDTO').each(function(){
					if($(this).find('motivoComparecencia')){
						$("#editor2").val($(this).find('motivoComparecencia').text());
					}
				});

				$(xml).find('involucradoDTO').each(function(){
					calidad = $(this).find('calidadDTO').first().find('valorIdCalidad').find('idCampo').text();
					if(calidad == <%=Calidades.DEFENDIDO.getValorId()%>){
						situacionActualDefendido = $(this).find('valorSituacionJuridica').find('valor').text();
						nombre =$(this).find('nombresDemograficoDTO').find('nombreDemografico').first();
						nombreDefendido = $(nombre).find('nombre').text()+" "+$(nombre).find('apellidoPaterno').text()+" "+$(nombre).find('apellidoMaterno').text();
						$("#involucradoDefendido").val(nombreDefendido);
					}
				});
				
				pintaDatosAudiencia(xml);
				pintaDatosResumen(xml);
			    pintaDatosGenerales(xml);
			    pintaDatosDomicilio(xml);
	    		pintaDatosContacto(xml);
	    		  
			 }
		});
	}
	
	function manageTabs(){
		$("#tabDatosInteres").hide();
		$("#tabMotivoAsesor").hide();
		$("#tabInvolucrados").hide();
		$("#tabObjetsyEvids").hide();
		$("#tabCadebaCustod").hide();
		$("#tab_Diligencias").hide();
		$("#tabAudienciasEx").hide();
		$("#tabPericialesEx").hide();
		$("#tab_Actuaciones").hide();
		$("#tab_Notas_Exped").hide();
		$("#tabTabsDelito").hide();

		switch(parseInt(<%=etapaId%>)){
			case <%=EtapasExpediente.ASESORIA.getValorId()%>:
				$("#tabDatosInteres").show();
				$("#tabMotivoAsesor").show();
				$("#resumen1").show();
				$("#resumen2").hide();
				break;
			case <%=EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>:
			case <%=EtapasExpediente.INTEGRACION.getValorId()%>:
				$("#tabInvolucrados").show();
				inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);
				$("#tab_Actuaciones").show();
				inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
				$("#tab_Notas_Exped").show();
				$("#resumen1").show();
				$("#resumen2").hide();
				//inicilizarTabNotas(<%=numeroExpedienteIdOK%>);
				break;
			case <%=EtapasExpediente.TECNICA.getValorId()%>:
			case <%=EtapasExpediente.EJECUCION.getValorId()%>:
				$("#tabInvolucrados").show();
				inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);	
				$("#tab_Actuaciones").show();
				inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
				$("#tab_Notas_Exped").show();
				$("#resumen1").show();
				$("#resumen2").hide();
				//inicilizarTabNotas(<%=numeroExpedienteIdOK%>);
				if(<%=estatusExp%> == <%=EstatusExpediente.ABIERTO_TECNICA_CON_CARPETA.getValorId()%>
					|| <%=etapaId%> == <%=EtapasExpediente.EJECUCION.getValorId()%>){
					$("#tabObjetsyEvids").show();
					$("#tabCadebaCustod").show();
					$("#tab_Diligencias").show();
					inicilizarTabDiligencias(<%=numeroExpedienteIdOK%>);
					$("#tabAudienciasEx").show();
					$("#tabPericialesEx").show();
					$("#resumen1").hide();
					$("#resumen2").show();
					$("#tabTabsDelito").show();
				}
				break;
		}
		//inicilizarTabDocumentos(<%=numeroExpedienteIdOK%>);
		//inicilizarTabBitacora(<%=numeroExpedienteIdOK%>);
		
	}
	
	function cargaGridBitacora() {
		jQuery("#gridBitacora").jqGrid({
					url : '<%= request.getContextPath()%>/consultarBitacoraPorExpediente.do?numeroExpedienteId='+<%=numeroExpedienteIdOK%>+'', 
					datatype: "xml", 						
					colNames:['Fecha','Hora','Movimiento','Descripción'], 
					colModel:[{name:'Fecha',	 	index:'1',  width:200, align:"center"},
					          {name:'Hora',	        index:'4', 	width:200, align:"center"},
					          {name:'Movimiento',	index:'2', 	width:200, align:"center"},
					          {name:'Descripcion',	index:'3',  width:400, align:"center"}
							],				
					pager: jQuery('#pagerAudiencia'),
					rowNum:20,
					rowList:[20,40,60],
					autowidth: true,
					//autoheight:true,
					sortname: '1',
					viewrecords: true,
					sortorder: "desc",
					onSelectRow: function(rowID) {
						//idAudiencia=rowID;				
					}
		}).navGrid('#pagerGridBitacora',{edit:false,add:false,del:false});
			jQuery("#gridBitacora").trigger('reloadGrid');
	}

	/*Comienza funcionalidad para el tab de notas*/
	//carga nota
	function cargaNota(id){
		var row=$('#rowNota_'+id);
		$(row).remove();
		$('#tablaNotasExpediente').append('<tr id="rowNota_'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarNota_'+id+'" onclick="notaExpediente('+id+')">Nota</a></td></tr>');
		//cerrarVentanaNota();
	} 

	function notaExpediente(idNota){
		idWindowGenerarNotas++;
		$.newWindow({id:"iframewindowGenerarNotas" + idWindowGenerarNotas, statusBar: true, posx:200,posy:50,width:700, height:450,title:"Nota de Expediente", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarNotas" + idWindowGenerarNotas,'<iframe src="<%= request.getContextPath() %>/capturarNotaExpediente.do?idNumeroExpedienteOp='+<%=numeroExpedienteIdOK%>+'&idNota='+idNota+' " width="700" height="450" />');
	}

	function cerrarVentanaNota(){
		var pantalla ="iframewindowGenerarNotas";
		pantalla += idWindowGenerarNotas;
		$.closeWindow(pantalla);
	}

	/*
	*Consultar las notas del expediente
	*POR EL MOMENTO SOLO SE CONSULTA UNA NOTA
	*/
	function consultarNotas(){
		
		var notas=$('#editor1').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarNotasExpediente.do?idNumeroExpediente='+<%=numeroExpedienteIdOK%>,
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

	function loadEtapaAsesoria(){
		$("#tabResumenExped").show();
		$("#tabDatosInteres").show();
		$("#tabMotivoAsesor").show();
		$("#tabDocumentosEx").show();
		$("#tab_BitacoraExp").show();
	}
	
	function loadEtapaEjecucion(){
		$("#tabResumenExped").show();
		$("#tabInvolucrados").show();
		$("#tabObjetsyEvids").show();
		$("#tabCadebaCustod").show();
		$("#tab_Diligencias").show();
		$("#tabAudienciasEx").show();
		$("#tabPericialesEx").show();
		$("#tab_Actuaciones").show();
		$("#tab_Notas_Exped").show();
		$("#tabDocumentosEx").show();
		$("#tab_BitacoraExp").show();
		inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);	
		inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
	}
	
	function loadEtapaRestaurativa(){
		$("#tabResumenExped").show();
		$("#tabInvolucrados").show();
		$("#tab_Actuaciones").show();
		$("#tab_Notas_Exped").show();
		$("#tabDocumentosEx").show();
		$("#tab_BitacoraExp").show();
		$("#tabInvolucrados").show();
		inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);
		inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
	}
		
	function loadEtapaTecnica(){
		$("#tabResumenExped").show();
		$("#tabInvolucrados").show();
		if(<%=estatusExp%> == <%=EstatusExpediente.ABIERTO_TECNICA_CON_CARPETA.getValorId()%>){
			$("#tabObjetsyEvids").show();
			$("#tabCadebaCustod").show();
			$("#tab_Diligencias").show();
			$("#tabAudienciasEx").show();
			$("#tabPericialesEx").show();
		}
		$("#tab_Actuaciones").show();
		$("#tab_Notas_Exped").show();
		$("#tabDocumentosEx").show();
		$("#tab_BitacoraExp").show();
		inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);	
		inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
	}
		
	function loadEtapaIntegracion(){
		$("#tabResumenExped").show();
		$("#tabInvolucrados").show();
		$("#tab_Actuaciones").show();
		$("#tab_Notas_Exped").show();
		$("#tabDocumentosEx").show();
		$("#tab_BitacoraExp").show();
		inicilizarTabInvolucrados(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, <%=estatusExp%>, involucrados);
		inicilizarTabActuaciones(<%=idExpedienteOK%>, <%=numeroExpedienteIdOK%>, '<%=numeroExpediente%>', <%=etapaId%>, involucrados, situacionActualDefendido);
	}

	function documentos(){
		//carga grid de documentos	
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarDocumentosDefensoria.do?tipo=3&idExpedienteop='+<%=numeroExpedienteIdOK%>, 
			datatype: "xml", 
			colNames:['Tipo de documento','Fecha','Nombre de Documento','Nombre de la actividad','Fecha de la actividad','Área del responsable'], 
			colModel:[ 	{name:'Tipo',index:'tipo', width:155, align:"center"}, 
			           	{name:'Fecha',index:'fecha', width:90, align:"center"},
						{name:'Nombre',index:'nombre', width:255, align:"center"},
						{name:'nombreActividad',index:'nombreActividad', width:400, align:"center"},
						{name:'FechaActividad',index:'fechaActividad', width:170, align:"center"},							
						{name:'area',index:'area', width:200, align:"center"}
						],
			pager: jQuery('#pager1'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: false,
			width:1100,
			sortname: 'turno',
			viewrecords: true,
			id: 'divgrid',
			onSelectRow: function(id){
				consultaPDF(id);
			},
			sortorder: "desc"
		}).navGrid('#pager1',{edit:false,add:false,del:false});
		$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '500px');
		$("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
	}

	function consultaPDF(id){
		document.formaArchivoD.documentoId.value = id;
		document.formaArchivoD.submit();
	}

	 function gridAudiencia() {
			jQuery("#gridAudiencia").jqGrid({
						url:'<%= request.getContextPath()%>/consultarAudienciasDefensor.do', 
						datatype: "xml", 						
						colNames:['Caso','Carácter','Tipo de Audiencia','Fecha de Audiencia','Sala','Defensor de Oficio'], 
						colModel:[{name:'caso',	 	index:'2002', 		width:200, align:"let"},
						          {name:'caracter',	index:'2037', 	width:100, align:"center"},
						          {name:'tipo',	 	index:'2017', 	    width:120, align:"center"},
						          {name:'fechaHora',index:'2018',	width:200, align:"center"},
						          {name:'sala' ,	index:'2029', 		width:110, align:"center"},
						          {name:'defensor' ,index:'2030', 	width:150, align:"center"}
								],				
						pager: jQuery('#pagerAudiencia'),
						rowNum:10,
						rowList:[10,20,30],
						autowidth: true,
						autoheight:true,
						sortname: 'detalle',
						viewrecords: true,
						sortorder: "desc",
						onSelectRow: function(rowID) {
							idAudiencia=rowID;				
							}
		}).navGrid('#pagerAudiencia',{edit:false,add:false,del:false});
			jQuery("#gridAudiencia").trigger('reloadGrid');
	}

	function cargaTestigo(nombre,id){		 
		var row=$('#'+id);
		
		$(row).remove(); 
		$('#tblTestigo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTestigo" onclick="modificaTestigo('+id+')">'+nombre+'</a></td></tr>');
	} 

	function cargaVictima(nombre,id){		 
		var row=$('#'+id);
		
		$(row).remove(); 
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaVictima('+id+')">'+nombre+'</a></td></tr>');
	} 

	function cargaProbableResponsable(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		//nombre=nombre+"- Indiciado";
		$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificaProbResponsable('+id+')">'+nombre+'</a></td></tr>');
	}

	//Funcion para quitar la victima del visor de elementos
	function eliminarTestigo(id){
		var row=$('#'+id);
		$(row).remove();
	}
	
	//Funcion para quitar la victima del visor de elementos
	function eliminarVictima(id){
		var row=$('#'+id);
		$(row).remove();
	}

	//Funcion para quitar la victima del visor de elementos
	function eliminarVictima(id){
		var row=$('#'+id);
		$(row).remove();
	}

	//Funcion para quitar el probable responsable del visor de elementos
	function eliminarProbableResponsable(id)
	{
		var row=$('#'+id);
		$(row).remove();
	}
	
	//Funcion que permite mostrar el valor para los campos correspondientes a la audiencia
	function pintaDatosAudiencia(xml){
		
		if (existe($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('audiencia').find('tipoAudiencia').find('valor').text())){
			$("#tipoAudiencia").val($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('audiencia').find('tipoAudiencia').find('valor').text());
		}else{
			$("#tipoAudiencia").val("---");
		}
		
		if (existe($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('audiencia').find('sala').find('nombreSala').text())){
			$("#salaAudiencia").val($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('audiencia').find('sala').find('nombreSala').text());
		}else{
			$("#salaAudiencia").val("---");
		}
		
		var fechaAudiencia = $(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('audiencia').find('fechaEvento').text();
		if (existe(fechaAudiencia)){
			$("#fechaAudiencia").val(obtenerFecha(fechaAudiencia));
			$("#horaAudiencia").val(obtenerHora(fechaAudiencia));
		}else{
			$("#fechaAudiencia").val("---");
			$("#horaAudiencia").val("---");
		}
		
		if (existe($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('strFechaCreacion').text())){
			$("#fechaSolicitud").val($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('strFechaCreacion').text());
		}else{
			$("#fechaSolicitud").val("---");
		}
		
		if (existe($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('strHoraCreacion').text())){
			$("#horaSolicitud").val($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('strHoraCreacion').text());
		}else{
			$("#horaSolicitud").val("---");
		}
		
		if ($("#involucradoDefendido").val() == ""){
			if (existe($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('detenido').text())){
				$("#involucradoDefendido").val($(xml).find('avisoDesignacionDTO').first().find('solicitudDefensor').find('detenido').text());			
			}else{
				$("#involucradoDefendido").val("---");
			}
		}
		$("#fechaVencimiento").val("---");
		$("#horaVencimiento").val("---");
	}
	
	//Funcion que permite mostrar el valor para los campos del resumen
	function pintaDatosResumen(detalle){
		
		//Numero de caso
		if(existe($(detalle).find('avisoDetencion').first().find('numeroCasoAsociado').text())){
			$("#caso").val($(detalle).find('avisoDetencion').first().find('numeroCasoAsociado').text());
		}else{
			$("#caso").val("---");
		}	
		
		
		//Permite mostrar el delito principal
		if(existe($(detalle).find('avisoDetencion').find('delitos').find('catDelitoDTO').first().find('nombre').text())){
			$("#delito").val($(detalle).find('avisoDetencion').find('delitos').find('catDelitoDTO').first().find('nombre').text());
		}else{
			$("#delito").val("---");
		}	
		
		//Existe Detenido		
		var esDetenido = $(detalle).find('inputado').find('esDetenido').first().text();
		if(esDetenido == "true"){
			$("#detenido").val("Si");			
		}else{
			if(esDetenido == ""){
				if($(detalle).find('strFechaInicioDetencion').first().text() != "")
					$("#detenido").val("SI");
				else
					$("#detenido").val("----");
			}
				
			else
				$("#detenido").val("No");
			
		}
		
		//Lugar de detencion
		if(existe($(detalle).find('inputado').find('detenciones').find('detencionDTO').find('lugarDetencionProvicional').first().text()))
			$("#lugarDetencion").val($(detalle).find('inputado').find('detenciones').find('detencionDTO').find('lugarDetencionProvicional').first().text());
		else
			$("#lugarDetencion").val("---");
		
		//Fecha y hora de detencion
		if(existe($(detalle).find('strFechaInicioDetencion'))){
			var fecha = $(detalle).find('strFechaInicioDetencion').first().text();
			var strfecha = obtenerFecha(fecha);
			var strhora = obtenerHora(fecha);
			$("#fechaDetencion").val(strfecha);
			$("#horaDetencion").val(strhora);
		}else{
			$("#fechaDetencion").val("----");
			$("#horaDetencion").val("----");
		}		
		
		//Fecha y hora de solicitud
		if(existe($(detalle).find('fechaCreacion'))){
			var fecha = $(detalle).find('fechaCreacion').first().text();
			var strfecha = obtenerFecha(fecha);
			var strhora = obtenerHora(fecha);
			$("#fechaSolicitud").val(strfecha);
			$("#horaSolicitud").val(strhora);
		}else{
			$("#fechaSolicitud").val("----");
			$("#horaSolicitud").val("----");
		}

		
		
	}
	
	function existe(val){
		if(val != undefined && val != null && val != "" && val.length > 0){
			return true;
		}
		return false;
	}
	
	function obtenerFecha(fecha){
		var fh = fecha.split(" ");
		return fh[0].split("-")[2]+"/"+fh[0].split("-")[1]+"/"+fh[0].split("-")[0];	
	}
	
	function obtenerHora(fecha){
		var fh = fecha.split(" ");
		return fh[1].substring(0,5);
	}
	



    </script>
    
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu Defensor</title>			
</head>
<body>
<!-- Comienza Tabs Principal del menu -->

<div id="tabedMenuExpedienteDefensoria">
	<ul>
		<li id="tabResumenExped"><a href="#tabsconsultaprincipal-1" >Resumen</a></li>
		<li id="tabDatosInteres"><a href="#tabsconsultaprincipal-2" >Interesado</a></li>
		<li id="tabMotivoAsesor"><a href="#tabsconsultaprincipal-3" >Motivo Asesoría</a></li>
		<li id="tabInvolucrados"><a href="#tabsconsultaprincipal-4" onclick="cargarTabInvolucradosOnClick()">Involucrados</a></li>
		<li id="tabTabsDelito"><a href="#tabsconsultaprincipal-14" onclick="cargaComboProbableResponsableRDPPVDelito()">Delito</a></li>
		<li id="tabObjetsyEvids"><a href="#tabsconsultaprincipal-5" >Objetos y evidencias</a></li>
		<li id="tabCadebaCustod"><a href="#tabsconsultaprincipal-6" >Cadena de custodia</a></li>
		<li id="tab_Diligencias"><a href="#tabsconsultaprincipal-7" >Diligencias</a></li>
		<li id="tabAudienciasEx"><a href="#tabsconsultaprincipal-8"  onclick="gridAudiencia()">Audiencias</a></li>
		<li id="tabPericialesEx"><a href="#tabsconsultaprincipal-9" >Periciales</a></li>
		<li id="tab_Actuaciones"><a href="#tabsconsultaprincipal-10">Actuaciones</a></li>
		<li id="tab_Notas_Exped"><a href="#tabsconsultaprincipal-11">Notas</a></li>
		<li id="tabDocumentosEx"><a href="#tabsconsultaprincipal-12"  onclick="documentos()">Documentos</a></li>
		<li id="tab_BitacoraExp"><a href="#tabsconsultaprincipal-13" onclick="cargaGridBitacora()">Bitácora</a></li>
	</ul>
	
	<div id="tabsconsultaprincipal-1" style="height: 600px !important;">			
		<!--Inicia Resumen Expediente -->
		<div id="resumen1">
			<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
			<tr>
				<td style="vertical-align:top;">
				<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
				<logic:notEqual name="expediente" property="etapa.idCampo" value="2533">
				<tr id = "trCaso">
					<td align="right" width="50%">
						<strong>Número de caso:</strong>
					</td>
					<td width="50%">
					<logic:present name="expediente" property="casoDTO">
						<html:text name="expediente" property="casoDTO.numeroGeneralCaso" styleId="caso" styleClass="texto" readonly="readonly"></html:text>
					</logic:present>
					<logic:notPresent name="expediente" property="casoDTO">
						<input class="texto" type="text" readonly="readonly" id="caso" value="----"/>
					</logic:notPresent>
					</td>
				</tr>
				</logic:notEqual>
				<tr id = "trExpediente">
					<td align="right" width="50%">
						<strong>Número de expediente:</strong>
					</td>
					<td width="50%">
					<logic:present name="expediente" property="numeroExpediente">
						<html:text name="expediente" property="numeroExpediente" styleId="expediente" styleClass="texto" readonly="readonly"></html:text>
					</logic:present>
					<logic:notPresent name="expediente" property="numeroExpediente">
						<input class="texto" type="text" readonly="readonly" id="expediente" value="----" readonly="readonly"/>
					</logic:notPresent>
					</td>
				</tr>
				<tr id = "trEtapaExpediente">
					<td align="right">
						<strong>Etapa del Expediente:</strong>
					</td>
					<td>
					<logic:present name="expediente" property="etapa">
						<html:text name="expediente" property="etapa.valor" styleId="etapaExpediente" styleClass="texto" readonly="readonly"></html:text>
					</logic:present>
					<logic:notPresent name="expediente" property="etapa">
						<input class="texto" type="text" readonly="readonly" id="etapaExpediente" value="----" readonly="readonly"/>
					</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>
							Nombre del Defendido:
						</strong>
					</td>
					<td>
						<input class="texto" type="text" readonly="readonly" id="involucradoDefendido" readonly="readonly"/>
					</td>
				</tr>
<!--			<logic:iterate id="involucrado" name="expediente" property="involucradosDTO">-->
<!--				<logic:equal name="involucrado" property="calidadDTO.valorIdCalidad.idCampo" value="213">-->
<!--					<tr id = "trImputado">-->
<!--						<td align="right">-->
<!--							<strong>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2048">-->
<!--									Nombre del imputado:-->
<!--								</logic:equal>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2050">-->
<!--									Nombre del imputado:-->
<!--								</logic:equal>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2049">-->
<!--									Para quien se solicita:-->
<!--								</logic:equal>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2533">-->
<!--									Interesado:-->
<!--								</logic:equal>-->
<!--							</strong>-->
<!--						</td>-->
<!--						<td>-->
<!--						<logic:present name="involucrado" property="nombreCompleto">-->
<!--							<html:text name="involucrado" property="nombreCompleto" styleId="imputado" styleClass="texto" readonly="readonly"></html:text>-->
<!--						</logic:present>-->
<!--						<logic:notPresent name="involucrado" property="nombreCompleto">-->
<!--							<input class="texto" type="text" readonly="readonly" id="imputado" value="----" readonly="readonly"/>-->
<!--						</logic:notPresent>-->
<!--						</td>-->
<!--					</tr>-->
<!--				</logic:equal>-->
<!--				<logic:equal name="involucrado" property="calidadDTO.valorIdCalidad.idCampo" value="1584">-->
<!--					<tr id = "trInteresado">-->
<!--						<td align="right">-->
<!--							<strong>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2050">-->
<!--									Quien solicita:-->
<!--								</logic:equal>-->
<!--								<logic:equal name="expediente" property="etapa.idCampo" value="2533">-->
<!--									Interesado:-->
<!--								</logic:equal>-->
<!--							</strong>-->
<!--						</td>-->
<!--						<td>-->
<!--						<logic:present name="involucrado" property="nombreCompleto">-->
<!--							<html:text name="involucrado" property="nombreCompleto" styleId="interesado" styleClass="texto" readonly="readonly"></html:text>-->
<!--						</logic:present>-->
<!--						<logic:notPresent name="involucrado" property="nombreCompleto">-->
<!--							<input class="texto" type="text" readonly="readonly" id="interesado" value="----" readonly="readonly"/>-->
<!--						</logic:notPresent>-->
<!--						</td>-->
<!--					</tr>-->
<!--				</logic:equal>-->
<!--			</logic:iterate>-->
				<tr id = "trDelito">
					<td align="right">
						<strong>Delito:</strong>
					</td>
					<td>
						<input class="texto" type="text" readonly="readonly" id="delito"/>
					</td>
				</tr>
<!--				<tr id = "trDefensor">-->
<!--					<td align="right">-->
<!--						<strong>Nombre del defensor:</strong>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input class="texto" type="text" readonly="readonly" id="defensor"/>-->
<!--					</td>-->
<!--				</tr>-->
<!--				<tr id = "trFechaDesignacion">-->
<!--					<td align="right">-->
<!--						<strong>Fecha Designación:</strong>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input class="texto" type="text" readonly="readonly" id="fechaDesignacion"/>-->
<!--					</td>-->
<!--				</tr>-->
<!--				<tr id = "trHoraDesignacion">-->
<!--					<td align="right">-->
<!--						<strong>Hora Designación:</strong>-->
<!--					</td>-->
<!--					<td>-->
<!--						<input class="texto" type="text" readonly="readonly" id="horaDesignacion"/>-->
<!--					</td>-->
<!--				</tr>-->
				</table>
			</td>
			<td style="vertical-align:top;">
				<table width="100%" border="0" align="center" cellpadding="0"cellspacing="5">
					<logic:notEqual name="expediente" property="etapa.idCampo" value="2533">
						<logic:notEqual name="expediente" property="etapa.idCampo" value="2049">
							<tr id = "trDetenido">
								<td align="right">
									<strong>Detenido:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="detenido"/>
								</td>
							</tr>
							<tr id = "trFechaVencimiento">
								<td align="right">
									<strong>Fecha vencimiento de atencion:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="fechaVencimiento"/>
								</td>
							</tr>
							<tr id = "trHoraVencimiento">
								<td align="right">
									<strong>Hora vencimiento de atencion</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="horaVencimiento"/>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal name="expediente" property="etapa.idCampo" value="2049">
							<tr id = "trTipoAudiencia">
								<td align="right">
									<strong>Tipo de Audiencia:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="tipoAudiencia"/>
								</td>
							</tr>
							<tr id = "trSalaAudiencia">
								<td align="right">
									<strong>Sala Asignada:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="salaAudiencia"/>
								</td>
							</tr>
							<tr id = "trFechaAudiencia">
								<td align="right">
									<strong>Fecha de Audiencia:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="fechaAudiencia"/>
								</td>
							</tr>
							<tr id = "trHoraAudiencia">
								<td align="right">
									<strong>Hora de Audiencia:</strong>
								</td>
								<td>
									<input class="texto" type="text" readonly="readonly" id="horaAudiencia"/>
								</td>
							</tr>
						</logic:equal>
						<tr id = "trLugarDetencion">
							<td align="right">
								<strong>Lugar donde se encuentra detenido:</strong>
							</td>
							<td>
								<input class="texto" type="text" readonly="readonly" id="lugarDetencion"/>
							</td>
						</tr>
						<tr id = "trFechaDetencion">
							<td align="right">
								<strong>Fecha de Detención:</strong>
							</td>
							<td>
								<input class="texto" type="text" readonly="readonly" id="fechaDetencion"/>
							</td>
						</tr>
						<tr id = "trHoraDetencion">
							<td align="right">
								<strong>Hora de Detención:</strong>
							</td>
							<td>
								<input class="texto" type="text" readonly="readonly" id="horaDetencion"/>
							</td>
						</tr>
					</logic:notEqual>
					<tr id = "trFechaSolicitud">
						<td align="right">
							<strong>Fecha Solicitud:</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="fechaSolicitud"/>
						</td>
					</tr>
					<tr id = "trHoraSolicitud">
						<td align="right">
							<strong>Hora Solicitud</strong>
						</td>
						<td>
							<input class="texto" type="text" readonly="readonly" id="horaSolicitud"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</div>
		<!--Termina Resumen Expediente -->
	
		<!-- Inicia Resumen Expediente con Carpeta de Investigación -->
		<div id="resumen2" style="display: none">
			<table width="1042px"  height="565px" border="0" cellspacing="0" cellpadding="0" class="back_generales">
			<tr>
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr style="border-bottom-style: solid; border: 1;background-image:">
				<td width="248" style="font-size:14px; background-color:" align="right"><strong>Estatus del Expediente:</strong></td>
				<td width="100" style="font-size:14px; background-color:" >&nbsp;</td>
			    <td width="203" align="right" style="font-size:14px; background-color:"><strong>Resumen de objetos:</strong></td>
			    <td width="100" style="font-size:14px; background-color:">&nbsp;</td>
			    <td width="203" align="right" style="font-size:14px; background-color:"><strong>Resumen de involucrados<em>:</em></strong></td>	
			    <td width="46" style="font-size:14px; background-color:">&nbsp;</td>
			</tr>
			<tr>
				<td  colspan="6" height="20px">
					<table width="978" border="0" cellpadding="0" cellspacing="0" class="linea_down_gral" align="center">
					<tr>
				   		<td>&nbsp;</td>
				 	</tr>
					</table>			    	
			 	</td>
			</tr>
			<tr valign="top">
				<td id="estatusExpe" align="center">&nbsp;</td>
				<td>&nbsp;</td>
				<td rowspan="16" align="right" style="background-color:" valign="top">
					<table border="0" cellpadding="0" cellspacing="0"  style="background-color: #DCDDDE">
				  	<tr>
						<td nowrap align="right" style="background-color:">Vehículos:</td>
						<td id="Vehiculos">&nbsp;</td>
				    </tr>
				    <tr>
						<td align="right" style="background-color:">Equipos de cómputo:</td>
						<td id="EquiposDeComputo">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Equipos Telefónicos:</td>
						<td id="EquiposTelefonicos">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Armas:</td>
						<td id="Armas">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Explosivos:</td>
						<td id="Explosivos">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Sustancias:</td>
						<td id="Sustancias">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Animales:</td>
						<td id="Animales">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Aeronaves:</td>
						<td id="Aeronaves">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Embarcación:</td>
						<td id="Embarcacion">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Inmueble:</td>
						<td id="Inmueble">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Numerario:</td>
						<td id="Numerario">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Vegetal:</td>
						<td id="Vegetal">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Documento oficial:</td>
						<td id="DocumentoOficial">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Joya:</td>
						<td id="Joya">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Obra de arte:</td>
						<td id="ObraDeArte">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" align="right" style="background-color:">Otros:</td>
						<td id="Otros">&nbsp;</td>
					</tr>
					</table>
				</td>
				<td>&nbsp;</td>
				<td rowspan="6" align="right" style="background-color:">
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="background-color: #DCDDDE">
					<tr>
						<td align="right" style="background-color:">Denunciantes:</td>
						<td id="Denunciantes">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Víctimas:</td>
						<td id="Victimas">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Probables Responsables:</td>
						<td id="ProbablesResponsables">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Testigos:</td>
						<td id="Testigos">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Traductores/Intérpretes:</td>
						<td id="Traductores">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" style="background-color:">Quién detuvo:</td>
						<td id="QuienDetuvo">&nbsp;</td>
					</tr>
					</table>
				</td>
		    </tr>
		    <tr>
				<td  style="background-color:"align="right">
				<span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:">
					<img src="<%=request.getContextPath()%>/resources/images/icn_doc_chek.png"><strong>Tipo:</strong>
				</span>
				</td>
				<td >&nbsp;</td>
			</tr>
		 	<tr>
		    	<td id="origenExpe" align="right"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr>
			   <td  id="anonimoDenun" align="right">&nbsp;</td>
			   <td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right"><span style="border-left:#000000; border-top:#000000; border-bottom-width:4; font-size:14px; background-color:; display:none;"><strong>Canalizado a:</strong></span> </td>
				<td><!-- <input type="radio" name="radio" id="rbtnRestaurativa" value="R" />--></td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
			  <td></td>
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
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;</td>
			</tr>
			</table>
			<table width="1042px"  height="22px" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<%=request.getContextPath()%>/resources/images/pleca_down_grales.jpg"></td>
			</tr>
			</table>
		</div>
		<!--Termina Resumen Expediente con Carpeta de Investigación -->
	</div>
	<div id="tabsconsultaprincipal-2" style="height: 600px !important;">
		<table width="100%" >
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td align="right">
						 <input type="button" class="btn_guardar" value="Guardar" id="btnGuardar" />
					</td>
				</tr>
		</table>
	  <!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
           <div id="cedulaIdentificacion">
             <dl>
               <dt id="cejaDatosGenerales">Datos Generales</dt>
                 <dd>	
                   <jsp:include page="/WEB-INF/paginas/datosGeneralesView.jsp"/>
                 </dd>
               <dt id="cejaDomicilio">Domicilio</dt>
                 <dd>
                   <jsp:include page="/WEB-INF/paginas/ingresarDomicilioView.jsp"/>
                 </dd>
               <dt id="cejaMediosContacto">Medios de Contacto</dt>
                   <dd>
                     <jsp:include page="/WEB-INF/paginas/ingresarMediosContactoView.jsp"/>
                   </dd>
                 </dl>
               </div>	
	</div>
	<div id="tabsconsultaprincipal-3" style="height: 600px !important;">
		<textarea class="jquery_ckeditor" cols="150" id="editor2" name="editor2" rows="10"></textarea>
	</div>
	<div id="tabsconsultaprincipal-4" style="height: 600px !important;">
		<jsp:include page="involucra2.jsp"></jsp:include>
	</div>
	<div id="tabsconsultaprincipal-5" style="height: 600px !important;">
		<!--Inicia Pestaña de hijos de objetos -->
		<div id="tabschild4" class="tabs-bottom">
			<ul>
				<li><a onclick="consultaGridVehiculosVisor()" href="#tabschild4-7">Veh&iacute;culo</a></li>
				<li><a onclick="consultaGridEquiposComputoVisor()" href="#tabschild4-1">Equipo de c&oacute;mputo</a></li>
				<li><a onclick="consultaGridEquipoTelefonicoVisor()"href="#tabschild4-2">Equipo telef&oacute;nico</a></li>
				<li><a onclick="consultaGridArmasVisor()" href="#tabschild4-3">Arma</a></li>
				<li><a onclick="consultaGridExplosivoVisor()" href="#tabschild4-4">Explosivo</a></li>
				<li><a onclick="consultaGridSustanciaVisor()" href="#tabschild4-5">Sustancia</a></li>
				<li><a onclick="consultaGridAnimalVisor()" href="#tabschild4-6">Animal</a></li>					
				<li><a onclick="consultaGridAeronaveVisor()" href="#tabschild4-8">Aeronave</a></li>
				<li><a onclick="consultaGridEmbarcacionVisor()" href="#tabschild4-9">Embarcaci&oacute;n</a></li>
				<li><a onclick="consultaGridNumerarioVisor()" href="#tabschild4-11">Numerario</a></li>
				<li><a onclick="consultaGridVegetalVisor()" href="#tabschild4-12">Vegetal</a></li>
				<li><a onclick="consultaGridDocumentoOficialVisor()" href="#tabschild4-13">Documento oficial</a></li>
				<li><a onclick="consultaGridJoyaVisor()" href="#tabschild4-14">Joya</a></li>
				<li><a onclick="consultaGridObraDeArteVisor()" href="#tabschild4-15">Obra de arte</a></li>	
			</ul>
			
			<!--EQUIPO DE COMPUTO-->
			<div id="tabschild4-1">
			
				<table width="25%" cellpadding="0" cellspacing="0" id="tblEquipoComputo">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoDeComputo" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
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
				<br>
				<table id="gridObjsEquipoTelefonico" width="600px"></table>
				<div id="pagerGridObjsEquipoTelefonico"></div>
			</div>
			
			<!--ARMA-->
			<div id="tabschild4-3">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblArma">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
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
				<br>
				<table id="gridObjsExplosivo" width="600px"></table>
				<div id="pagerGridObjsExplosivo"></div>
			</div>
			
			<!--SUSTANCIA-->
			<div id="tabschild4-5">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblSustancia">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsSustancia" width="600px"></table>
				<div id="pagerGridObjsSustancia"></div>
			</div>
			
			<!--ANIMAL-->
			<div id="tabschild4-6">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblAnimal">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoAnimal" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsAnimal" width="600px"></table>
				<div id="pagerGridObjsAnimal"></div>
			</div>
			
			<!--VEHICULO-->
			<div id="tabschild4-7">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblVehiculos">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVehiculo" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
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
				<br>
				<table id="gridObjsAeronave" width="600px"></table>
				<div id="pagerGridObjsAeronave"></div>
			</div>
			
			<!--EMBARCACION-->
			<div id="tabschild4-9">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblEmbarcacion">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsEmbarcacion" width="600px"></table>
				<div id="pagerGridObjsEmbarcacion"></div>
			</div>
			
			<!--NUMERARIO-->
			<div id="tabschild4-11">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblNumerario">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsNumerario" width="600px"></table>
				<div id="pagerGridObjsNumerario"></div>
			</div>
			
			<!--VEGETAL-->
			<div id="tabschild4-12">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblVegetal">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVegetal" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsVegetal" width="600px"></table>
				<div id="pagerGridObjsVegetal"></div>
			</div>
			
			<!--DOCUMENTO OFICIAL-->
			<div id="tabschild4-13">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblDocOficial">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoDocumentoOficial" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsDocumentoOficial" width="600px"></table>
				<div id="pagerGridObjsDocumentoOficial"></div>
			</div>
			
			<!--JOYA-->
			<div id="tabschild4-14">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblJoya">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaJoya" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsJoya" width="600px"></table>
				<div id="pagerGridObjsJoya"></div>
			</div>
			
			<!--OBRA DE ARTE-->
			<div id="tabschild4-15">
				<table width="25%" cellpadding="0" cellspacing="0" id="tblObraArte">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaObraDeArte" class="btn_modificar" value="Ingreso nuevo"/></td>
					</tr>
				</table>
				<br>
				<table id="gridObjsObraDeArte" width="600px"></table>
				<div id="pagerGridObjsObraDeArte"></div>
			</div>
		</div>
		<!--Cierra pestaña de hijos de objetos-->
	</div>
	<div id="tabsconsultaprincipal-6" style="height: 600px !important;">
		 <input type="button" class="btn_grande" id="btnCadCusConsultaCadCus" style="width: 250px;" value="Consultar cadena de custodia"/><br/><br/>    
	</div>
	<div id="tabsconsultaprincipal-7" style="height: 600px !important;">
		<jsp:include page="diligencias.jsp"/>
	</div>
	<div id="tabsconsultaprincipal-8" style="height: 600px !important;">
		<table width="100%" id="gridAudiencia" onmouseup="camposSeleccionados()"></table>
		<div id="pagerAudiencia"></div>
	</div>
	<div id="tabsconsultaprincipal-9" style="height: 600px !important;">
		<jsp:include page="/WEB-INF/paginas/solicitudesTabPericialesView.jsp"></jsp:include>
	</div>
	<div id="tabsconsultaprincipal-10" style="height: 600px !important;">
		<jsp:include page="actuaciones.jsp"/>
	</div>
	<div id="tabsconsultaprincipal-11" style="height: 600px !important;">
		<table width="25%" cellpadding="0" cellspacing="0" id="tablaNotasExpediente">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;<input type="button" class="btn_modificar" value="Generar Nota"  id="botonGuardarNotas" onclick="notaExpediente(0);" /></td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-12" style="height: 600px !important;">
		<table id="gridDetalleFrmPrincipal"></table>
			<div id="pager1"></div>
				<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" id="documentoId" name="documentoId" />
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
	<div id="tabsconsultaprincipal-13">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	            <td><br></td>
	        </tr>
	        <tr>
	            <td>
	            	<div id="divBitacora">
		            	<table id="gridBitacora"></table>
		            	<div id="pagerGridBitacora"></div>
	            	</div>
	            </td>
	        </tr>
	    </table>	
	</div>
	<!--COMIENZAN TABS INFERIORES DE DELITO-->
	<div id="tabsconsultaprincipal-14" class="tabTabsDelito">
	
		<div id="tabschild14" class="tabs-bottom">
			
			<ul>
				<li><a href="#tabschild2-1">Seleccionar delitos</a></li>
				<li><a href="#tabschild2-2" onclick="gridRelacionarDelitosTabDelito();cargaGridConsultaDelitosTodos()">Establecer Delito Persona</a></li>					
			</ul>
			
			<div id="tabschild2-1">

				<table width="25%" cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaSentenciado">
							<jsp:include page="/WEB-INF/paginas/seleccionarDelitoViewDefensoria.jsp"></jsp:include>
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
							<jsp:include page="/WEB-INF/paginas/relacionaDelitoPorPersonaView.jsp"></jsp:include>
						</td>
					</tr>
				</table>
				<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXDelito" style="display: none;">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<jsp:include page="/WEB-INF/paginas/relacionaDelitoPorDelitoView.jsp"></jsp:include>
						</td>
					</tr>
				</table>
				<table width="25%" cellpadding="0" cellspacing="0" id="tblRelacionaDelXTodos" >
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<jsp:include page="/WEB-INF/paginas/relacionaDelitoPorTodosView.jsp"></jsp:include>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!--TERMINAN TABS INFERIORES DE DELITO-->
	
		<form name="formaDocumento" action="<%= request.getContextPath() %>GenerarDocumentoDirecto.do" method="post">
			<input type="hidden" value="" id="formaId" >
			<input type="hidden" value="" id="documentoId">
			<input type="hidden" value="" id="tipoDocumento">
			<input type="hidden" name="numeroUnicoExpediente" />
		</form>
		
		<form name="formaArchivoD" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
			<input type="hidden" name="documentoId" />
		</form>
	
	
</div>
</body>
</html>	