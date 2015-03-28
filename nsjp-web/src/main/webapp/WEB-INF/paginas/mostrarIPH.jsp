<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades" %>
	 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar IPH</title>

	<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--Hojas de estilos de los combos multiselect-->
	<!-- <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" /> -->
	
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
	
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>

   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<!-- JS para la validacion de solo numeros -->
	<script src="<%=request.getContextPath()%>/js/comun.js"></script>	

	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	</style>
    

<script type="text/javascript">

	//variable en donde se almacena el id del funcionario para poder registrar el usuario
    //var idFuncionario;
	//variable global para manipular la pestaña de dar de alta un usuario segun de donde se mande a llamar
    //var administrador= '<%=request.getParameter("administrador")!=null?request.getParameter("administrador"):""%>';
    
	var numeroExpediente;
	var numeroExpedienteId;
	var operativoId;
	var primeraVezGridDocumentosDigitalesPropios = true;
	//var lsDatosMotivo="";
    
    var idWindowIngresarVictima           = 1;	
	//var idWindowIngresarTraductor         = 1;	
	var idWindowIngresarTestigo           = 1;
	var idWindowIngresarProbResponsable   = 1;
	var idWindowIngresarDenunciante       = 1;
	var idWindowIngresarHechos            = 1;
	var idWindowIngresarVehiculo          = 1;
	var idWindowIngresarAeronave          = 1;
	var idWindowIngresarEmbarcacion       = 1;	
	var idWindowIngresarArma              = 1;
	var idWindowIngresarExplosivo         = 1;
	var idWindowIngresarSustancia         = 1;	
	var idWindowIngresarNumerario         = 1;
	var isDetenidoExist                   = false;	

	var idWindowAsntarRegCadCus = 1;

	var folioIPH='<%= request.getParameter("folioIPH")%>';
	var rowid = <%=request.getParameter("rowid")%>;
	
	var contextoPagina = "${pageContext.request.contextPath}";
	
	$(document).ready(function() {
		
		$('#liDom').hide();
		$('#liDom').addClass("tabEstilo");
		
		$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
			.removeClass( "ui-corner-all ui-corner-top" )
			.addClass( "ui-corner-bottom" );
		
		//Se crean las tabs principales
		$("#tabsconsultaprincipal" ).tabs();
		$("#tabs" ).tabs();

		//SE CREAN LAS TABS INFERIORES
		//personas involucradas
		$( "#tabschild2" ).tabs();
		//medios de transporte
		//$( "#tabschild3" ).tabs();
		//estupefacientes
		//$( "#tabschild4" ).tabs();
		//armas y explosivos
		//$( "#tabschild5" ).tabs();
		//aseguramientos
		$( "#tabschild15" ).tabs();
		
		//Se agrega el evento click para crear nuevos involucrados
		$("#nuevaVictima").click(creaNuevaVictima);
		$("#nuevoProbResponsable").click(creaNuevoProbResponsable);
		$("#crearDenunciante").click(crearDenunciante);
		$("#nuevoTestigo").click(creaNuevoTestigo);
		$("#ingresarHechos").click(ingresarHechos);
		$("#nuevoVehiculo").click(creaNuevoVehiculo);
		$("#nuevaAeronave").click(creaNuevaAeronave);
		$("#nuevaEmbarcacion").click(creaNuevaEmbarcacion);
		$("#nuevaArma").click(creaNuevaArma);
		$("#nuevoExplosivo").click(creaNuevoExplosivo);
		$("#nuevaSustancia").click(creaNuevaSustancia);
		$("#nuevoNumerario").click(creaNuevoNumerario);
		
		cargaTurnos();
		cargaTipoParticipacion();
		
		$("#chkOperativo").click(habilitaDivOperativo);	
		ocultaDivOperativo();			
		
		/*$( "#dialog-detenido-informe" ).dialog({
			autoOpen: true,
			resizable: false,
			//height:290,
			//width:300,
			modal: true,
			buttons: {
				"Si": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					//dlgDetenidoInforme(true);
				},
				"No": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					//dlgDetenidoInforme(false);
				}
			}
		});	*/								
		
		//Seteo listener cadena de custodia
		$("#btnCadCusNuevaCadCus").click(asentarRegCadenaCustodia);
		$("#btnCadCusConsultaCadCus").click(consultarRegCadenaCustodia);

		consultarInformePolicialHomologadoPorFolio(folioIPH);

		resumen();
		$('#tabsconsultaprincipal').tabs({ selected: 2 });		
		
	});

	/**
	** Consulta el IPH de acuerdo a un folio determinado.
	**/
	function consultarInformePolicialHomologadoPorFolio(folioIPH){
		$.ajax({
	  		type: 'POST',
	  	  	url: '<%= request.getContextPath()%>/consultarInformePolicialHomologadoPorFolio.do?folioIPH='+folioIPH+'',			
	  	  	dataType: 'xml',
	  	  	success: function(xml){
	  		  	mostrarDatosGenerales(xml);
	  		  	mostrarHechos(xml, 'hecho');
	  		  	mostrarInvolucrados(xml);
	  		  	mostrarMediosDeTransporte(xml);
	  		  	mostrarSustancias(xml);
	  		  	mostrarArmas(xml);
	  		  	mostrarNumerario(xml);
	  			cargaGridDocumentosDigitalesPropios();
	  				  		  	
	  	  	}
		});
	}

	/**
	** Obtiene a partir de un xml los valores a mostrar en las pestañas de Datos Generales y Observaciones Generales
	**/
	function mostrarDatosGenerales(xml){
		$("#datosGeneralesCmpNumeroTransporteOf").val($(xml).find('numEcoTransporte').text());
		$("#datosGeneralesCmpAsunto").val($(xml).find('asunto').text());

		if($(xml).find('faltaIph').find('catFaltaAdministrativa').find('nombreFalta') == null ||
			$(xml).find('faltaIph').find('catFaltaAdministrativa').find('nombreFalta').text() == ""){
			$("#motivoCmpTipoEvento").val('1'); //combo
			buscaSubTipoEvento();
			$("#motivoCmpSubtipoEvento").val($(xml).find('delitoIph').find('catDelito').find('catDelitoId').text()); //combo
		}else{
			$("#motivoCmpTipoEvento").val('2'); //combo
			buscaSubTipoEvento();
			$("#motivoCmpSubtipoEvento").val($(xml).find('faltaIph').find('catFaltaAdministrativa').find('catFaltaAdministrativaId').text()); //combo
		}
		
		$("#datosGeneralesCmpNumeroEmpleado").val($(xml).find('funcionarioDestinatario').find('numeroEmpleado').text());
		if($("#datosGeneralesCmpNumeroEmpleado").val() != null && $("#datosGeneralesCmpNumeroEmpleado").val() != ""){
  			buscarFuncionario();	  		  	
		}
		
		$("#datosGeneralesCmpOficial").val($(xml).find('funcionarioDestinatario').find('nombreFuncionario').text() + " " + 
										   $(xml).find('funcionarioDestinatario').find('apellidoPaternoFuncionario').text() + " " +
										   $(xml).find('funcionarioDestinatario').find('apellidoMaternoFuncionario').text());
		$("#datosGeneralesCmpSector").val($(xml).find('funcionarioDestinatario').find('departamento').find('area').find('nombre').first().text());
		$("#datosGeneralesCmpDirigidoA").val();
		
		$("#datosGeneralesCmpTurno").val($(xml).find('turnoLaboralIphs').find('turnoLaboral').find('turnoLaboralId').text()); //combo
		$("#datosGeneralesCmpTipoParticipacion").val($(xml).find('tipoParticipacion').find('idCampo').text());

		$("#editor1").val($(xml).find('objetivosGenerales').text());

		if ($(xml).find('operativo').find('operativoId') != null &&	$(xml).find('operativo').find('operativoId').text() != ""){
			$("#divOperativo").show();
			$('#chkOperativo').attr("checked","checked");
			$("#datosGeneralesCmpNombreOperativo").val($(xml).find('operativo').find('nombre').last().text());
			$("#datosGeneralesCmpComandanteAgrupamiento").val($(xml).find('operativo').find('nombreComte').text());
			$("#datosGeneralesCmpComandanteOperativo").val($(xml).find('operativo').find('nombreComteAgrupto').text());				
		}else{
			$("#divOperativo").hide();
		}

		numeroExpedienteId = $(xml).find('expediente').find('numeroExpedienteId').first().text(); 
		numeroExpediente = $(xml).find('expediente').find('numeroExpediente').first().text();
		if($(xml).find('operativo').find('operativoId').first().text() != null && $(xml).find('operativo').find('operativoId').first().text() != ""){
			operativoId = $(xml).find('operativo').find('operativoId').first().text();
		}else{
			operativoId = null;
		}
	}
	
	/**
	** Muestra el listado de Hechos asignados al IPH.
	**/
	function mostrarHechos(xml, nombre){
		$(xml).find('InformePolicialHomologadoDTO').find('hechoDTO').each(function(){
			$('#tablaHecho').append('<tr id="'+$(this).find('hechoId').text()+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarHecho('+$(this).find('hechoId').text()+', '+numeroExpedienteId+');">'+nombre+ '_' + $(this).find('hechoId').text() + '</a></td></tr>');			
		});		
	}

	/**
	** Muestra los involucrados en las pestañas correspondientes según su tipo.
	**/
	function mostrarInvolucrados(xml){
		$(xml).find('InformePolicialHomologadoDTO').find('involucradosDTO').find('InvolucradoDTO').each(function(){

	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.DENUNCIANTE.getValorId() %>') {
		      	var idInvolucrado = $(this).find('elementoId').first().text(); 
	      		var nombreDenunciante = $(this).find('nombre').first().text() + ' ' + $(this).find('apellidoPaterno').first().text() + ' ' + $(this).find('apellidoMaterno').first().text();
	      		nombreDenunciante = nombreDenunciante == "" ? 'Anónimo' : nombreDenunciante;
				$('#tblDenunciante').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarDenuncianteDatos('+idInvolucrado+');">'+ nombreDenunciante + '</a></td></tr>');
				if($(this).find('condicion').first().text() == 1){
					$('#tblVictima').append('<tr id="v' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarDenuncianteDatos('+idInvolucrado+');">'+ nombreDenunciante + '</a></td></tr>');
				}
				$("#crearDenunciante").hide();
	      	}
	      	
	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.DENUNCIANTE_ORGANIZACION.getValorId() %>'){
	      		var idInvolucrado = $(this).find('elementoId').first().text();
	      		var nombreDenunciante = $(this).find('nombreOrganizacion').first().text();
	      		nombreDenunciante = nombreDenunciante == "" ? 'Anónimo' : nombreDenunciante;
				$('#tblDenunciante').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarDenuncianteDatos('+idInvolucrado+');">'+ nombreDenunciante + '</a></td></tr>');
				if($(this).find('condicion').first().text() == 1){
					$('#tblVictima').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarVictima('+idInvolucrado+');">'+ nombreDenunciante + '</a></td></tr>');
				}				
				$("#crearDenunciante").hide();
	      	}

	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.VICTIMA_PERSONA.getValorId() %>'){
	      		var idInvolucrado = $(this).find('elementoId').first().text();
	      		var nombreVictima = $(this).find('nombre').first().text() + ' ' + $(this).find('apellidoPaterno').first().text() + ' ' + $(this).find('apellidoMaterno').first().text();
      			$('#tblVictima').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarVictima('+idInvolucrado+');">'+ nombreVictima + '</a></td></tr>');	
	      	}
	      
	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId() %>'){
	      		var idInvolucrado = $(this).find('elementoId').first().text(); 
	      		var nombreResponsable = $(this).find('nombre').first().text() + ' ' + $(this).find('apellidoPaterno').first().text() + ' ' + $(this).find('apellidoMaterno').first().text();
				$('#tblProbableResponsable').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarProbableResponsable('+idInvolucrado+');">'+ nombreResponsable + '</a></td></tr>');
	      	}

	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() %>'){
	      		var idInvolucrado = $(this).find('elementoId').first().text();
	      		var nombreResponsable = $(this).find('nombreOrganizacion').first().text();
				$('#tblProbableResponsable').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarProbableResponsable('+idInvolucrado+');">'+ nombreResponsable + '</a></td></tr>');
	      	}
	      	
	      	if($(this).find('valorIdCalidad').find('idCampo').first().text() == '<%= Calidades.TESTIGO.getValorId() %>'){
	      		var idInvolucrado = $(this).find('elementoId').first().text();
		      	var nombreTestigo = $(this).find('nombre').first().text() + ' ' + $(this).find('apellidoPaterno').first().text() + ' ' + $(this).find('apellidoMaterno').first().text();
				$('#tblTestigo').append('<tr id="' + idInvolucrado + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificarTestigo('+idInvolucrado+');">'+ nombreTestigo + '</a></td></tr>');
	      	}
	      	
		});		
	}

	/**
	** Muestra los medios de transporte en las pestañas correspondientes según su tipo.
	**/
	function mostrarMediosDeTransporte(xml){
		$(xml).find('VehiculoDTO').each(function(){
			var idVehiculo = $(this).find('elementoId').first().text();
      		var nombreVehiculo = $(this).find('valorByTipoVehiculo').find('valor').first().text() + ' ' + $(this).find('placa').first().text();
			$('#tblVehiculo').append('<tr id="' + idVehiculo + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarVehiculo('+idVehiculo+');">'+ nombreVehiculo + '</a></td></tr>');
		});
		$(xml).find('AeronaveDTO').each(function(){
			var idAeronave = $(this).find('elementoId').first().text();
      		var nombreAeronave = $(this).find('tipoAeroNave').find('valor').first().text();
			$('#tblAeronave').append('<tr id="' + idAeronave + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarAeronave('+idAeronave+');">'+ nombreAeronave + '</a></td></tr>');
		});		
		$(xml).find('EmbarcacionDTO').each(function(){
			var idEmbarcacion = $(this).find('elementoId').first().text();
      		var nombreEmbarcacion = $(this).find('tipoEmbarcacion').find('valor').first().text();
			$('#tblEmbarcacion').append('<tr id="' + idEmbarcacion + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarEmbarcacion('+idEmbarcacion+');">'+ nombreEmbarcacion + '</a></td></tr>');
		});		
	}
	/**
	** Muestra los estupefacientes en las pestañas correspondientes según su tipo.
	**/
	function mostrarSustancias(xml){
		$(xml).find('SustanciaDTO').each(function(){
			var idSustancia = $(this).find('elementoId').first().text();
      		var nombreSustancia = $(this).find('tipoSustancia').find('valor').first().text();
			$('#tblEstupefacientes').append('<tr id="' + idSustancia + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarSustancia('+idSustancia+');">'+ nombreSustancia + '</a></td></tr>');
		});
	}
	/**
	** Muestra las armas en las pestañas correspondientes según su tipo.
	**/
	function mostrarArmas(xml){
		$(xml).find('ArmaDTO').each(function(){
			var idArma = $(this).find('elementoId').first().text();
      		var nombreArma = $(this).find('tipoArma').find('valor').first().text();
			$('#tblArma').append('<tr id="' + idArma + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarArma('+idArma+');">'+ nombreArma + '</a></td></tr>');
		});
		$(xml).find('ExplosivoDTO').each(function(){
			var idExplosivo = $(this).find('elementoId').first().text();
      		var nombreExplosivo = $(this).find('tipoExplosivo').find('valor').first().text();
			$('#tblExplosivo').append('<tr id="' + idExplosivo + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarExplosivo('+idExplosivo+');">'+ nombreExplosivo + '</a></td></tr>');
		});		
	}
	/**
	** Muestra los objetos asegurados.
	**/
	function mostrarNumerario(xml){
		$(xml).find('NumerarioDTO').each(function(){
			var idNumerario = $(this).find('elementoId').first().text();
      		var nombreNumerario = $(this).find('moneda').first().text();
			$('#tblNumerario').append('<tr id="'+idNumerario+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarNumerario('+idNumerario+');">'+ nombreNumerario + '</a></td></tr>');
		});
	}
		
	/****** listener cadena de Custodia  ****/
	function asentarRegCadenaCustodia()
	{
		idWindowAsntarRegCadCus++;
		$.newWindow({id:"iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Asentar registro de cadena de custodia", type:"iframe"});
	    $.updateWindowContent("iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=0&numeroExpediente='+numeroExpediente +'&IPH=1 " width="1000" height="600" />');
	}
	
	function consultarRegCadenaCustodia()
	{
		idWindowAsntarRegCadCus++;
		$.newWindow({id:"iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Cadena de custodia", type:"iframe"});
	    $.updateWindowContent("iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=1&numeroExpediente='+numeroExpediente +'&IPH=1 " width="1000" height="600" />');
	}
	/****** FIN listener cadena de Custodia  ****/

	function dlgDetenidoInforme(detenidoExist) {
		//isDetenidoExist = detenidoExist;
		//if (detenidoExist) {
			//$("#tabsconsultaprincipal" ).tabs({ enabled: [] });
		//} else {
			//$("#tabsconsultaprincipal" ).tabs({ disabled: [2] });
		//}		
	}
	
	function habilitaDivOperativo(){
		if($("#chkOperativo").is(':checked')){
			$("#divOperativo").show();
		}else{
			ocultaDivOperativo();
		}
	}

	function ocultaDivOperativo(){
		$("#divOperativo").hide();
	}

	function recuperaDatosMotivos(){
			lsDatosMotivo="";
			lsDatosMotivo+="&tipoEvento="+$("#motivoCmpTipoEvento option:selected").val();
			lsDatosMotivo+="&subtipoEvento="+$("#motivoCmpSubtipoEvento option:selected").val();
			lsDatosMotivo+="&turnoLaboralId="+$("#datosGeneralesCmpTurno option:selected").val();
			lsDatosMotivo+="&tipoParticipacionId="+$("#datosGeneralesCmpTipoParticipacion option:selected").val(); 
		return	lsDatosMotivo;
	}
	
	function recuperaDatosGenerales()
	{
	    var lsDatosGenerales="";
	    lsDatosGenerales+="transporteOficialNum="+$("#datosGeneralesCmpNumeroTransporteOf").val();
	    lsDatosGenerales+="&asunto="+$("#datosGeneralesCmpAsunto").val();
	    lsDatosGenerales+="&numeroEmpleado="+$("#datosGeneralesCmpNumeroEmpleado").val();
	    lsDatosGenerales+="&numeroExpediente="+numeroExpediente;

		if($("#chkOperativo").is(':checked')){
			   lsDatosGenerales+="&nombreOperativo="+$("#datosGeneralesCmpNombreOperativo").val();
			   lsDatosGenerales+="&comandanteAgrupamiento="+$("#datosGeneralesCmpComandanteAgrupamiento").val();
			   lsDatosGenerales+="&comandanteOperativo="+$("#datosGeneralesCmpComandanteOperativo").val();
		}	   
	   
	   var motivo = recuperaDatosMotivos();
	   lsDatosGenerales+=motivo;

	    var txtArea = $('.jquery_ckeditor').val();
		lsDatosGenerales+='&observaciones=' + escape(txtArea);
	   
	   return lsDatosGenerales;
	}

	function guardarDatosGeneralesIPH(){
		var params = recuperaDatosGenerales();
		params += "&operativoId=" + operativoId;
		
		if($("#datosGeneralesCmpNumeroTransporteOf").val() == "" || $("#datosGeneralesCmpAsunto").val() == "" ||
		   $("#motivoCmpTipoEvento option:selected").val() == 0 || $("#motivoCmpSubtipoEvento option:selected").val() == "" ||
		   $("#datosGeneralesCmpNumeroEmpleado").val() == "" || $("#datosGeneralesCmpTurno option:selected").val() == "" ||
		   $("#datosGeneralesCmpTipoParticipacion option:selected").val() == "" || 
		   ($("#chkOperativo").is(':checked') && ($("#datosGeneralesCmpNombreOperativo").val() == "" || 
		    $("#datosGeneralesCmpComandanteAgrupamiento").val() == "" || $("#datosGeneralesCmpComandanteOperativo").val() == ""))){
			alert("Debe ingresar valores a los campos obligatorios (*)");
		}else{			
			$.ajax({								
			  	  type: 'POST',
			  	  url: '<%= request.getContextPath()%>/guardarDatosGeneralesIPH.do?folioIPH='+folioIPH+'',
			  	  data: params,				
			  	  dataType: 'xml',
			  	  success: function(xml){
			  		  alert('Datos Generales del IPH guardados de manera correcta');
			  	  }
			 });
		}
	}

	<%-- function generarInformeIPH(){
		$.ajax({								
		  	  type: 'POST',
		  	  url: '<%= request.getContextPath()%>/generarInformeIPH.do?folioIPH='+folioIPH+'',			
		  	  dataType: 'xml',
		  	  success: function(xml){			  	  
		  		  alert('Informe Policial Homologado generado de manera correcta');
		  		  document.frmDocumento.documentoId.value = $(xml).find('documentoId').text();
				  document.frmDocumento.submit();
		  	  }
		});
	} --%>
	
	function generarInformeIPH(){
		 
		
		var regreso = guardarDatosGeneralesIPH();
		
		if(regreso == "ok"){
			var idAgencia = parseInt($("#cbxAgencia option:selected").val());
			$.ajax({								
			  	  type: 'POST',
			  	  url: '<%= request.getContextPath()%>/generarInformeIPH.do?folioIPH='+folioIPH+ '&idAgencia=' + idAgencia + '',			
			  	  dataType: 'xml',
			  	  async:false,
			  	  success: function(xml){			  	  
			  		  alertDinamico('Informe Policial Homologado generado de manera correcta');
			  		  document.frmDocumento.documentoId.value = $(xml).find('documentoId').text();
					  document.frmDocumento.submit();
					  //window.parent.cerrarVentanaIPH(idVentana);
			  	  }
					
			});
		}
		else if(regreso == "fail"){
			alertDinamico("Ocurrió un problema durante el guardado, intente de nuevo");	
		}
	}
	
	function buscarFuncionario(){
		var numeroEmpleado = $('#datosGeneralesCmpNumeroEmpleado').val();

		if(numeroEmpleado != ""){
	    	$.ajax({
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/consultarPersonalOperativoIPH.do?numeroEmpleado='+numeroEmpleado+'',
	    	    data: '',
	    	    dataType: 'xml',
	    	    async: false,
	    	    success: function(xml){
				   var nombre =  $(xml).find('funcionarioDTO').find('nombreFuncionario').first().text()+' '+$(xml).find('funcionarioDTO').find('apellidoPaternoFuncionario').first().text();
				   var sector =  $(xml).find('funcionarioDTO').find('departamento').find('area').find('nombre').first().text();
				   if(nombre != "" && nombre != null){
					   $('#datosGeneralesCmpOficial').val(nombre);
					   $('#datosGeneralesCmpSector').val(sector);
					   var claveFuncionario =  $(xml).find('funcionarioDTO').find('claveFuncionario').first().text();
					   obtenerSuperior(claveFuncionario);
				   }else{
						alert('No existe Funcionario con ese Número de Empleado');
				   }
	    		}
			});
		}else{
			alert("Debe ingresar un número de Empleado");
		}
	}

	function obtenerSuperior(claveFuncionario){
		if(claveFuncionario != "" && claveFuncionario != null){
	    	$.ajax({
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/consultaFuncionarioSuperior.do?claveFuncionario='+claveFuncionario+'',
	    	    data: '',
	    	    dataType: 'xml',
	    	    async: false,
	    	    success: function(xml){
				   var nombre =  $(xml).find('funcionarioDTO').find('nombreFuncionario').first().text()+' '+$(xml).find('funcionarioDTO').find('apellidoPaternoFuncionario').first().text();

				   $('#datosGeneralesCmpDirigidoA').val(nombre);
	    		}
			});
		}
	}

	/*
	*Funcion que dispara el Action para consultar Turnos
	*/	
	function cargaTurnos(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTurnoLaboral.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var option;
				$(xml).find('turnoLaboralDTO').each(function(){
					$('#datosGeneralesCmpTurno').append('<option value="' + $(this).find('turnoLaboralId').text() + '">'+ $(this).find('nombreTurno').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que dispara el Action para consultar Tipo Participacion
	*/	
	function cargaTipoParticipacion(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTipoParticipacion.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var option;
				$(xml).find('tipoParticipacion').each(function(){
					$('#datosGeneralesCmpTipoParticipacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	function buscaSubTipoEvento(){
		var selected = $("#motivoCmpTipoEvento option:selected").val();
		$( "#motivoCmpSubtipoEvento" ).attr('selectedIndex',0);
		$('#motivoCmpSubtipoEvento').empty();
		$('#motivoCmpSubtipoEvento').append('<option value="0">-Seleccione-</option>');
		
		$.ajax({
			async: false,									// la accion cargar las especialidades
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarSubtipoEvento.do?tipoEvento='+selected+'',
			dataType: 'xml',
			success: function(xml){
				if(selected == "1"){
					$(xml).find('delito').each(function(){
						$('#motivoCmpSubtipoEvento').append('<option value="' + $(this).find('claveDelito').text() + '">' + $(this).find('nombre').text() + '</option>');
					});
				}else if(selected == "2"){
					$(xml).find('falta').each(function(){
						$('#motivoCmpSubtipoEvento').append('<option value="' + $(this).find('catFaltaAdministrativaId').text() + '">' + $(this).find('nombreFalta').text() + '</option>');
					});
				}
			}
		});
	}
	
	/*	FUNCIONES PARA CREAR PERSONAS INVOLUCRADAS EN IPH	*/
	
	//Abre una nueva ventana de crear una nueva victima
	function creaNuevaVictima() {
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
	}
	function cargaVictima(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificarVictima('+id+')">'+nombre+'</a></td></tr>');
		cerrarVentanaVictima();
	}
		
	function cerrarVentanaVictima(){
		var pantalla ="iframewindowIngresarVictima";
		pantalla += idWindowIngresarVictima;
		$.closeWindow(pantalla);
	}
	//Abre una nueva ventana para modificar una victima
	function modificarVictima(id) {	
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Modificar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?idVictima='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
	}
	
	function cargaVictimaDenunciante(nombre,id){
		var row=$('#tblVictima tr:#v'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="v'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificarDenuncianteDatos('+id+')">'+nombre+'</a></td></tr>');
	} 
	
	//Funcion para quitar la victima del visor de elementos
	function eliminarVictima(id)
	{
		var row=$('#tblVictima tr:#'+id);
		$(row).remove();
	}
	
	//Funcion para quitar la victima del visor de elementos
	function eliminarVictimaDenunciante(id)
	{
		var row=$('#tblVictima tr:#v'+id);
		$(row).remove();
	}
	
	//Abre una nueva ventana de probable responsable
	function creaNuevoProbResponsable() {
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?numeroExpediente='+numeroExpediente+'&calidadInv=PROBABLE_RESPONSABLE&isDetenidoExist='+isDetenidoExist+'" width="1050" height="620" />');		
	}

	function cargaProbableResponsable(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		nombre += "- ";
		$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificarProbableResponsable('+id+')">'+nombre+'<bean:message key="indiciado" /></a></td></tr>');
		cerrarVentanaProbableResponsable();		
	} 
	function cerrarVentanaProbableResponsable(){
		var pantalla ="iframewindowIngresarProbResponsable";
		pantalla += idWindowIngresarProbResponsable;
		$.closeWindow(pantalla);
	}

	//Abre una nueva ventana de probable responsable
	function modificarProbableResponsable(id) {
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpediente +'" width="1050" height="620" />');			
	}
	
	//Funcion para quitar la victima del visor de elementos
	function eliminarProbableResponsable(id)
	{
		var row=$('#'+id);
		$(row).remove();
	}
	
	//Abre una nueva ventana de Denunciante
	function crearDenunciante(){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroExpediente+'&calidadInv=DENUNCIANTE" width="1040" height="570" />');		
	}
	function cargaDenunciante(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblDenunciante').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarDenunciante" onclick="modificarDenuncianteDatos('+id+')">'+nombre+'</a></td></tr>');
		$('#crearDenunciante').hide();
	} 
	function cerrarVentanaDenunciante(){
		var pantalla ="iframewindowIngresarDenunciante";
		pantalla += idWindowIngresarDenunciante;
		$.closeWindow(pantalla);
	}

	//Abre una nueva ventana de Denunciante
	function modificarDenuncianteDatos(id){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpediente +'" width="1040" height="570" />');		
	}
		
	//Funcion para quitar la victima del visor de elementos
	function eliminarDenunciante(id)
	{
		var row=$('#tblDenunciante tr:#'+id);
		$(row).remove();
		$('#crearDenunciante').show();
	}
	
	//Crea una nueva ventana de testigo
	function creaNuevoTestigo() {
		idWindowIngresarTestigo++;
		$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Testigo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
	}
	function cargaTestigo(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblTestigo').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarTestigo" onclick="modificarTestigo('+id+')">'+nombre+'</a></td></tr>');
		cerrarVentanaTestigo();		
	}
	function cerrarVentanaTestigo(){
		var pantalla ="iframewindowIngresarTestigo";
		pantalla += idWindowIngresarTestigo;
		$.closeWindow(pantalla);
	}
	function modificaTestigo(id){
		modificarTestigo(id);
	}
	function modificarTestigo(id) {
		idWindowIngresarTestigo++;
		$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Modificar Testigo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?idTestigo='+id+'&numeroExpediente='+numeroExpedienteId +'" width="1100" height="530" />');		
	}
	
	//Funcion para quitar la victima del visor de elementos
	function eliminarTestigo(id)
	{
		var row=$('#tblTestigo tr:#'+id);
		$(row).remove();
		$('#crearDenunciante').show();
	}

	//Abre una nueva ventana de ingresar traductor
	function creaNuevoTraductor() {
		//idWindowIngresarTraductor++;
	//$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Traductor", type:"iframe"});
    //$.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
	}	
	
	//Elimina un registro del traductor 
	function eliminaTraductorDeMenuIntermedio(id){
		var row =$('#tblTraductor tr:#'+id);
		$(row).remove();
		alertDinamico("Se anuló exitosamente el traductor");
	}
	
	function cargaTraductor(nombre,id){
		//var row=$('#'+id);
		//$(row).remove(); 
		//$('#tblTraductor').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarTraductor" onclick="modificaTraductor('+id+')">'+nombre+'</a></td></tr>');
	} 

	function ingresarHechos() {
		idWindowIngresarHechos++;
		$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Hechos", type:"iframe"});
	    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?iphFuncionalidadHidden=true&numeroExpediente='+numeroExpediente+'&idCalidad=DENUNCIANTE&idHecho=0 " width="1050" height="600" />');		
	}
	function cargaIngresoHecho(nombre,id){
		$("#ingresarHechos").hide();
		$('#tablaHecho').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="hechoId" onclick="consultarHecho('+id+','+numeroExpedienteId+');">'+nombre+'</a></td></tr>');
		cerrarVentanaHecho();
	}
	function cerrarVentanaHecho(){
		var pantalla ="iframewindowHecho";
		pantalla += idWindowIngresarHechos;
		$.closeWindow(pantalla);
	}
	function consultarHecho(idHecho, numeroExpedienteId) {	
		idWindowIngresarHechos++;
		$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Hechos", type:"iframe"});
	    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/ingresarHechos.do?iphFuncionalidadHidden=true&numeroExpediente='+numeroExpedienteId +'&idCalidad=DENUNCIANTE&idLugar=1&idHecho='+idHecho +' " width="1050" height="600" />');		
	}

	function creaNuevoVehiculo(){
		idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=VEHICULO&idVehiculo=0" width="570" height="600" />');
	}

	function consultarVehiculo(idVehiculo){
		 idWindowIngresarVehiculo++;
		$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO " width="570" height="600" />');
	}
	function cargaVehiculo(id,tipo,placas){
		$('#tblVehiculo tr:#'+id).remove();
		if(tipo!="" && placas!="")
		{
			$('#tblVehiculo').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarVehiculo_'+id+'" onclick="consultarVehiculo('+id+')">'+tipo+' '+ placas+'</a></td></tr>');			
		}
		cerrarVentanaVehiculo();
	} 

	function muestraMenuQuienDetuvo(){
	}
	
	function cerrarVentanaVehiculo(){
		var pantalla ="iframewindowIngresarVehiculo";
		pantalla += idWindowIngresarVehiculo;
		$.closeWindow(pantalla);
	}

	function creaNuevaAeronave(){
		idWindowIngresarAeronave++;
		$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente+'" width="600" height="530" />');
	}
	function consultarAeronave(idAeronave){
		idWindowIngresarAeronave++;
		$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Consultar Aeronave", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'&idAeronave='+idAeronave+'&tipoObjeto=AERONAVE " width="600" height="530" />');
	}
	function cargaAeronave(id,tipo){
		$('#tblAeronave tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblAeronave').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="'+id+'" onclick="consultarAeronave('+id+');">'+ tipo + '</a></td></tr>');		
		}
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
	    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente+'" width="600" height="530" />');
	}
	function consultarEmbarcacion(idEmbarcacion){
		idWindowIngresarEmbarcacion++;
		$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Consultar embarcación", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'&idEmbarcacion='+idEmbarcacion+'&tipoObjeto=EMBARCACION " width="600" height="530" />');
	}	
	function cargaEmbarcacion(id,tipo){
		$('#tblEmbarcacion tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblEmbarcacion').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="'+id+'" onclick="consultarEmbarcacion('+id+');">'+ tipo + '</a></td></tr>');		
		}
		cerrarVentanaEmbarcacion();
	} 
	function cerrarVentanaEmbarcacion(){
		var pantalla ="iframewindowIngresarEmbarcacion";
		pantalla += idWindowIngresarEmbarcacion;
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
		$('#tblArma tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblArma').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarArma_'+id+'" onclick="consultarArma('+id+')">'+tipo+'</a></td></tr>');
		}
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
	    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente+'" width="880" height="330" />');
	}
	function consultarExplosivo(idExplosivo){
		idWindowIngresarExplosivo++;
		$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Consultar explosivo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente+'&idExplosivo='+idExplosivo+'&tipoObjeto=EXPLOSIVO" width="880" height="330" />');
	}
	function cargaExplosivo(id,tipo){
		$('#tblExplosivo tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblExplosivo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="'+id+'" onclick="consultarExplosivo('+id+');">'+ tipo + '</a></td></tr>');
		}
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
	    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'" width="900" height="330" />');
	}
	function consultarSustancia(idSustancia){
		idWindowIngresarSustancia++;
		$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900, height:330,title:"Consultar sustancia", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente +'&idSustancia='+idSustancia+'&tipoObjeto=SUSTANCIA " width="900" height="330" />');
	}
	function cargaSustancia(id, tipo){
		$('#tblEstupefacientes tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblEstupefacientes').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="'+id+'" onclick="consultarSustancia('+id+');">'+ tipo + '</a></td></tr>');
		}
		cerrarVentanaSustancia();
	} 
	function cerrarVentanaSustancia(){
		var pantalla ="iframewindowIngresarSustancia";
		pantalla += idWindowIngresarSustancia;
		$.closeWindow(pantalla);
	}
	
	function creaNuevoNumerario(){
		idWindowIngresarNumerario++;
		$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente+'" width="800" height="350" />');
	}
	function consultarNumerario(idNumerario){
		idWindowIngresarNumerario++;
		$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar Numerario", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario='+idNumerario+'&tipoObjeto=NUMERARIO " width="800" height="350" />');
	}
	function cargaNumerario(id, tipo){
		$('#tblNumerario tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblNumerario').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="'+id+'" onclick="consultarNumerario('+id+');">'+ tipo + '</a></td></tr>');
		}
		cerrarVentanaNumerario();
	} 
	function cerrarVentanaNumerario(){
		var pantalla ="iframewindowIngresarNumerario";
		pantalla += idWindowIngresarNumerario;
		$.closeWindow(pantalla);
	}			

	function resumen(){
		 $.ajax({
		      type: 'POST',
	    	  url: '<%= request.getContextPath()%>/cargarDatosGenerales.do?idNumeroExpedienteOp='+numeroExpedienteId,
	    	  data: '',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
					$('#Vehiculos').html($(xml).find('totalVehiculos').text()+': '+$(xml).find('vehiculos').text());
					$('#Aeronaves').html($(xml).find('totalAeronaves').text()+': '+$(xml).find('aeronaves').text());
					$('#Embarcacion').html($(xml).find('totalEmbarcaciones').text()+': '+$(xml).find('embarcaciones').text());
					$('#Sustancias').html($(xml).find('totalSustancias').text()+': '+$(xml).find('sustancias').text());
					$('#Armas').html($(xml).find('totalArmas').text()+': '+$(xml).find('armas').text());
					$('#Explosivos').html($(xml).find('totalExplosivos').text()+': '+$(xml).find('explosivos').text());
					$('#Numerario').html($(xml).find('totalNumerarios').text()+': '+$(xml).find('numerarios').text());
					$('#Denunciantes').html($(xml).find('totalDenunciantes').text() == "" ? 'Anónimo' : $(xml).find('totalDenunciantes').text()+': '+$(xml).find('denunciantes').text());
					$('#Victimas').html($(xml).find('totalVictimas').text()+': '+$(xml).find('victimas').text());
					$('#ProbablesResponsables').html($(xml).find('totalProbablesResposables').text()+': '+$(xml).find('probablesResposables').text());
					$('#Testigos').html($(xml).find('totalTestigos').text()+': '+$(xml).find('testigos').text());

					$('#estatusExpe').html($(xml).find('estatusNumeroExpediente').text());
					$('#origenExpe').html($(xml).find('origenExpediente').text());	
					$('#anonimoDenun').html($(xml).find('esDesconocido').text());
					num=$(xml).find('totalDocumentosDelExpediente').text();
					$('#fehcaApertura').html("Fecha Apertura:"+$(xml).find('fechaApertura').text());

					ocultaImgResumen(xml);				
	    	  }
	    	});
	}

	function ocultaImgResumen(xml){
		if($(xml).find('ve').text()!="1"){
			$('#imgVehiculo').hide();
		}else{
			$('#imgVehiculo').show();
		}
		if($(xml).find('aero').text()!="1"){
			$('#imgAeronaves').hide();
		}else{
			$('#imgAeronaves').show();
		}
		if($(xml).find('embar').text()!="1"){
			$('#imgEmbarcacion').hide();
		}else{
			$('#imgEmbarcacion').show();
		}					
		if($(xml).find('sus').text()!="1"){
			$('#imgSustancias').hide();
		}else{
			$('#imgSustancias').show();
		}
		if($(xml).find('arm').text()!="1"){
			$('#imgArmas').hide();
		}else{
			$('#imgArmas').show();
		}
		if($(xml).find('expl').text()!="1"){
			$('#imgExplosivos').hide();
		}else{
			$('#imgExplosivos').show();
		}
		if($(xml).find('nume').text()!="1"){
			$('#imgNumerario').hide();
		}else{
			$('#imgNumerario').show();
		}
		if($(xml).find('denun').text()!="1"){
			$('#imgDenunciantes').hide();
		}else{
			$('#imgDenunciantes').show();
		}
		if($(xml).find('vic').text()!="1"){
			$('#imgVictimas').hide();
		}else{
			$('#imgVictimas').show();	
		}
		if($(xml).find('proba').text()!="1"){
			$('#imgProbablesResponsables').hide();
		}else{
			$('#imgProbablesResponsables').show();
		}
		if($(xml).find('test').text()!="1"){
			$('#imgTestigos').hide();
		}else{
			$('#imgTestigos').show();
		}
	}

	function cargaGridDocumentosDigitalesPropios(){ 

		if(primeraVezGridDocumentosDigitalesPropios == true){
			jQuery("#gridDocumentosDigitalesPropios").jqGrid({
				url:'<%=request.getContextPath()%>/ConsultaExpedientesDocumento.do?numeroExpedienteId='+numeroExpedienteId+'',
				datatype: "xml", 
				colNames:['Nombre de Documento'],
				colModel:[	{name:'nombre',index:'nombre', width:255},
				       
							],
				pager: jQuery('#pagerGridDocumentosDigitalesPropios'),
				rowNum:20,
				rowList:[10,20,30],
				width:250,
				height:455,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				//multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						//abrirDocsDigAsociadosASolPropios(rowid);	
						abrirDocsDigAsociadosASol(rowid);
						//alert(rowid);						
					}
				}
				
			}).navGrid('#pagerGridDocumentosDigitalesPropios',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitalesPropios .ui-jqgrid-bdiv").css('height', '455px');
			primeraVezGridDocumentosDigitalesPropios= false;
		}
		else{
			jQuery("#gridDocumentosDigitalesPropios").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/ConsultaExpedientesDocumento?numeroExpedienteId='+numExpedienteId+'',datatype: "xml" });
			$("#gridDocumentosDigitalesPropios").trigger("reloadGrid");
		}
	}	

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
		$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId+"&inFrame=true");
			
		}
		else{
			alert("El documento no puede ser mostrado");
		}
	}
</script>

</head>

<body>

	<table width="100%" class="back_pleca_h">
		<tr>			
			<td align="right">
					 <input type="button" value="Guardar" id="btnIPHGuardadoParcial" class="back_button" onclick="guardarDatosGeneralesIPH()"/>
					 <input type="button" value="Generar Informe" class="back_button" onclick="generarInformeIPH()"/>
			</td>
		</tr>
	</table>

    <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>

	<form name="frmDocumento" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
		<input type="hidden" name="documentoId" />
	</form>

	<div id="tabsconsultaprincipal">
		<ul>
			<li id="tabResumen"><a href="#tabsconsultaprincipal-12" onclick="resumen()">Resumen</a></li>
			<li id="tabRegistroPertenencias"><a href="#tabsconsultaprincipal-13">Registro de Pertenencias</a></li>
			<li id="tabDetencion"><a href="#tabsconsultaprincipal-14">Detenci&oacute;n</a></li>
			<li id="tabDatosGenerales"><a href="#tabsconsultaprincipal-1">Datos Generales</a></li>
			<li id="tabHechos"><a href="#tabsconsultaprincipal-11">Hechos</a></li>
			<li id="tabInvolucrados"><a href="#tabsconsultaprincipal-2">Personas Involucradas</a></li>
			<li id="tabAseguramientos"><a href="#tabsconsultaprincipal-15">Aseguramientos</a></li>
			<li class="tabTabsCadCus"><a href="#tabsconsultaprincipal-9">Cadena de Custodia</a></li>
			<li id="tabInformacion"><a href="#tabsconsultaprincipal-7">Documentos de Apoyo</a></li>
			<li id="tabObservaciones"><a href="#tabsconsultaprincipal-8">Observaciones Generales</a></li>
		</ul>
		
		<!--COMIENZA TAB DE RESUMEN-->
		<div id="tabsconsultaprincipal-12" class="tabResumen" >
			<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
				<tr><td colspan="6">&nbsp;</td></tr>			
			  	<tr style="border-bottom-style: solid; border: 1;background-image:">
				    <td width="238" style="font-size:14px; background-color:" align="right"><strong>Estatus del Expediente:</strong></td>
				    <td width="19" style="font-size:14px; background-color:" >&nbsp;</td>
				    <td width="507" align="center" style="font-size:14px; background-color:"><strong>Resumen de objetos:</strong></td>
				    <td width="4" style="font-size:14px; background-color:">&nbsp;</td>
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
			    	<td id="estatusExpe" align="right"></td>
			    	<td></td>
			    	<td rowspan="16" align="center" width="507" style="background-color:" valign="top">
			    		<table border="0" cellpadding="0" cellspacing="0" width="99%">
		          			<tr>
		            			<td width="145" align="right" nowrap style="background-color:">Vehículos:</td>
					            <td width="332" id="Vehiculos">&nbsp;</td>
					            <td id="imgVehiculo" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>			            
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
				            	<td align="right" style="background-color:">Sustancias:</td>
				            	<td id="Sustancias">&nbsp;</td>
				            	<td id="imgSustancias" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
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
					            <td align="right" style="background-color:">Numerario:</td>
					            <td id="Numerario">&nbsp;</td>
					            <td id="imgNumerario" width="25"><img title="Contiene mas registros" width="10px" src='<%= request.getContextPath() %>/resources/images/add.png'></td>
				          	</tr>
				          	<tr>
				          		<td>&nbsp;</td>
				          	</tr>
				          	<tr style="border-bottom-style: solid; border: 1;background-image:">
				          		<td width="670" align="center" style="font-size:14px; background-color:" colspan="2">
				          		<strong>Resumen de involucrados<em>:</em></strong></td>	
				    			<!--<td width="4" style="font-size:14px; background-color:">&nbsp;</td>-->
				    		</tr>
				    		<tr>
								<td height="20px" colspan="6">
									<table class="linea_down_gral" width="578" cellspacing="0" cellpadding="0" border="0" align="center">
										<tbody>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
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
				    		
						</table>
			
					</td>
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
			  	<!--<tr>
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
			  	</tr>-->
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
		
		</div>
		<!--TERMINA TAB DE RESUMEN-->

		<!--COMIENZA TAB DE REGISTRO DE PERTENENCIAS-->
		<div id="tabsconsultaprincipal-13" class="tabRegistroPertenencias">
		</div>
		<!--TERMINA TAB DE REGISTRO DE PERTENENCIAS-->
		
		<!--COMIENZA TAB DE DETENCION-->
		<div id="tabsconsultaprincipal-14" class="tabDetencion">
		</div>	
		<!--TERMINA TAB DE DETENCION-->
		
		<!--COMIENZAN TABS INFERIORES DE DATOS GENERALES-->
		<div id="tabsconsultaprincipal-1" class="tabDatosGenerales">
			<div id="tabschild" class="tabs-bottom">
				<div id="tabschild-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table>
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* N&uacute;mero Ec&oacute;nomico del Transporte Oficial:</td>
										<td><input type="text" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNumeroTransporteOf"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* Asunto:</td>
										<td><input type="text" style="width: 460px;" maxlength="150" id="datosGeneralesCmpAsunto"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* Tipo de Evento:</td>
										<td>
											<select id="motivoCmpTipoEvento" style="width: 180px;" onchange="buscaSubTipoEvento();" disabled="disabled">
												<option value="0">- Seleccione -</option>
												<option value="1">Delito</option>
												<option value="2">Falta Administrativa</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* Subtipo de Evento:</td>
										<td>
											<select id="motivoCmpSubtipoEvento" style="width: 180px;">
												<option value="">- Seleccione -</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* N&uacute;mero de Empleado:</td>
										<td><input type="text" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNumeroEmpleado" disabled="disabled"/></td>
										<!--<td><input type="button" id="btnFuncionario" value="Validar Funcionario" onclick="buscarFuncionario();" class="back_button" ></td>-->
									</tr>
									<tr>
										<td align="right">Oficial:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpOficial" disabled="disabled"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Sector:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpSector" disabled="disabled"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Dirigido a:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpDirigidoA" disabled="disabled"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* Turno:</td>
										<td>
											<select id="datosGeneralesCmpTurno" style="width: 180px;">
												<option value="">- Seleccione -</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">* Participaci&oacute;n:</td>
										<td>
											<select id="datosGeneralesCmpTipoParticipacion" style="width: 180px;">
												<option value="">- Seleccione -</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
			                        <tr>
			                            <td align="right">Se realiz&oacute; Operativo</td>
			                            <td>
			                              <input type="checkbox" id="chkOperativo"/>
			                            </td>                 
										<td>&nbsp;</td>
			                        </tr>  
			                        <tr>
			                        	<td colspan="3">
				                        	<div id="divOperativo">
				                        		<table>
							                        <tr>
							                            <td align="right" width="52%">* Nombre del Operativo</td>
														<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpNombreOperativo"/></td>
														<td>&nbsp;</td>
							                        </tr>  
							                        <tr>
							                            <td align="right">* Comandante Agrupamiento</td>
														<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpComandanteAgrupamiento"/></td>
														<td>&nbsp;</td>
							                        </tr>  
							                        <tr>
							                            <td align="right">* Comandante Operativo</td>
														<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpComandanteOperativo"/></td>
														<td>&nbsp;</td>
							                        </tr>  
				                        		</table>
				                        	</div>
			                        	</td>
			                        </tr>          
								</table>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
			
		</div>
		<!--TERMINAN TABS INFERIORES DE DATOS GENERALES-->
		
		<!--COMIENZAN TABS INFERIORES DE HECHOS-->
		<div id="tabsconsultaprincipal-11" class="tabHechos">
			<div style="width: 1042px; height: 490px;" class="back_hechos">
			<table border="0" cellspacing="0" cellpadding="0" id="tablaHecho" class="back_hechos" style="padding: .5cm; " >
				<tr valign="top">						
					<td valign="top"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="ingresarHechos" value="Ingreso Hecho" class="btn_estilo"/></td>
				</tr>
			</table>
			</div>
		</div>
		<!--TERMINAN TABS INFERIORES DE HECHOS-->
		
		<!--COMIENZAN TABS INFERIORES DE PERSONAS INVOLUCRADAS-->
		<div id="tabsconsultaprincipal-2" class="tabInvolucrados">
			<div id="tabschild2" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild2-1">Denunciante</a></li>
					<li><a href="#tabschild2-2">Víctima</a></li>
					<li><a href="#tabschild2-3"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild2-4">Testigo</a></li>
				</ul>
				
				<div id="tabschild2-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante"><input type="button" value="Ingresar Denunciante" class="back_button"/></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild2-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">	
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVictima">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima"><input type="button" value="Ingresar Víctima" class="back_button"/></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild2-3">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblProbableResponsable">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable"><input type="button" value='<bean:message key="ingProbaleResponsable"/>' class="back_button"/></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild2-4">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblTestigo">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo"><input type="button" value="Ingresar Testigo" class="back_button"/></a></td>
									</tr>
									
								</table>
							</td>
						</tr>
					</table>
				</div>
					
			</div>

		</div>
		
		<!--COMIENZAN TABS INFERIORES DE ASEGURAMIENTO-->
		<div id="tabsconsultaprincipal-15" class="tabAseguramientos">
			<div id="tabschild15" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild15-1">Veh&iacute;culo</a></li>
					<li><a href="#tabschild15-2">Aeronave</a></li>
					<li><a href="#tabschild15-3">Embarcaci&oacute;n</a></li>
					<li><a href="#tabschild15-4">Sustancia</a></li>
					<li><a href="#tabschild15-5">Arma</a></li>
					<li><a href="#tabschild15-6">Explosivo</a></li>
					<li><a href="#tabschild15-7">Numerario</a></li>
				</ul>
				
			<!--COMIENZAN TABS INFERIORES DE MEDIOS DE TRANSPORTE-->
			<div id="tabschild15-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVehiculo">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoVehiculo"><input type="button" value="Ingresar Vehículo" class="back_button"/></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild15-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblAeronave">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaAeronave" value="Ingreso nuevo" class="back_button"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild15-3">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEmbarcacion">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" value="Ingreso nuevo" class="back_button"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!--TERMINAN TABS INFERIORES DE MEDIOS DE TRANSPORTE-->
				
				<!--COMIENZAN TABS INFERIORES DE ESTUPEFACIENTES-->
				<div id="tabschild15-4">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEstupefacientes">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" value="Ingreso nuevo" class="back_button"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!--TERMINAN TABS INFERIORES DE ESTUPEFACIENTES-->

				<!--COMIENZAN TABS INFERIORES DE ARMAS-->
				<div id="tabschild15-5">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblArma">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" value="Ingreso nuevo" class="back_button"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild15-6">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblExplosivo">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoExplosivo" value="Ingreso nuevo" class="back_button"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!--TERMINAN TABS INFERIORES DE ARMAS-->
				
				<!--COMIENZAN TABS INFERIORES DE NUMERARIO-->
				<div id="tabschild15-7">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblNumerario">
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" value="Ingreso nuevo" class="back_button"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
				<!--TERMINAN TABS INFERIORES DE NUMERARIO-->
		
			</div>			
		</div>
		<!--TERMINAN TABS INFERIORES DE ASEGURAMIENTO-->
		
		<!--COMIENZAN TABS INFERIORES DE DOCUMENTOS DE APOYO-->
		<div id="tabsconsultaprincipal-7">
		<table width="1150"  height="530" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="250" align="center" valign="top">
	                        <table id="gridDocumentosDigitalesPropios"></table>
	                        <div id="pagerGridDocumentosDigitalesPropios"></div>
		                </td>
		                <td width="900" align="center" valign="top">
		               
		                
		               	  <iframe id='visorDocsPropiosFrame' width="900" height="500" src="" scrolling="auto" style="display: ;">		               	  
		               	  </iframe>  
		                </td>
		              </tr>
		            </table>
		</div>
		<!--TERMINAN TABS INFERIORES DE DOCUMENTOS DE APOYO-->
		
		<!--COMIENZAN TABS INFERIORES DE OBSERVACIONES GENERALES-->
		<div id="tabsconsultaprincipal-8">
	    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
	    		<tr>
		    		<td align="top"><textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea></td>
		    	</tr>
		    </table>
		</div>
		<!--TERMINAN TABS INFERIORES DE OBSERVACIONES GENERALES-->
		
		<!--COMIENZAN TABS INFERIORES DE CADENAS DE CUSTODIA-->
		<div id="tabsconsultaprincipal-9" class="tabTabsCadCus">
				 <input type="button" class="btn_grande" id="btnCadCusNuevaCadCus" style="width: 250px;" value="Crear nueva cadena de custodia"/><br/><br/>
				 <input type="button" class="btn_grande" id="btnCadCusConsultaCadCus" style="width: 250px;" value="Consultar cadena de custodia"/><br/><br/>    
  				 <input type="button" class="btn_grande" id="btnCadCusRegEslabones" style="width: 250px;" value="Registrar eslabones"/> <br/><br/>
  				 <input type="button" class="btn_grande" id="btnCadCusRepEvidencias" style="width: 250px;" style="width: 250px;" value="Reporte de evidencias"/> <br/><br/>
  				 <input type="button" class="btn_grande" id="btnCadCusElabOficio" style="width: 250px;" value="Elaborar oficio para fijación y preservación"/><br/><br/>  
   				 <input type="button" class="btn_grande" id="btnCadCusAdmDestino" style="width: 250px;" value="Administrar destino legal de evidencia"/>
		</div>	
		<!--TERMINAN TABS INFERIORES DE CADENAS DE CUSTODIA-->
				
	</div>
	<!--<div id="dialog-detenido-informe" title="Aviso">
		<p align="center">
			<span id="logout">¿Existe alg&uacuten detenido en el informe?</span>
		</p>
	</div>-->

</body>
<script type="text/javascript">
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
		],
		height:'300px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);

</script>
</html>
