<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades" %>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar IPH</title>

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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<!--script de los combos multiselect-->
	<!-- script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script-->
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>

   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
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
    var idFuncionario;
	//variable global para manipular la pestaña de dar de alta un usuario segun de donde se mande a llamar
    var administrador= '<%=request.getParameter("administrador")!=null?request.getParameter("administrador"):""%>';
    
	var numeroExpediente="";
	var numeroExpedienteId = '<%= request.getParameter("numeroExpedienteId")%>';
	var numeroExpedienteIdConsulta = <%=request.getSession().getAttribute("numeroExpedienteId")%>;
	
	var lsDatosMotivo="";
    
    var idWindowIngresarVictima           = 1;	
	var idWindowIngresarTraductor         = 1;	
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
	numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
	
	var idVentana = '<%=request.getParameter("idVentana")%>;'
	
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
		//datos generales
		$( "#tabschild" ).tabs();
		//personas involucradas
		$( "#tabschild2" ).tabs();
		//medios de transporte
		$( "#tabschild3" ).tabs();
		//estupeacientes
		$( "#tabschild4" ).tabs();
		//estupeacientes
		$( "#tabschild5" ).tabs();
		//numerario y otros
		//estupeacientes
		$( "#tabschild6" ).tabs();
		
		
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
		consultarDistritos();
		
		//Seteo listener cadena de custodia
		$("#btnCadCusNuevaCadCus").click(asentarRegCadenaCustodia);
		$("#btnCadCusConsultaCadCus").click(consultarRegCadenaCustodia);	
	});

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
		isDetenidoExist = detenidoExist;
		if (detenidoExist) {
			$("#tabsconsultaprincipal" ).tabs({ enabled: [] });
		} else {
			$("#tabsconsultaprincipal" ).tabs({ disabled: [2] });
		}		
	}
	
//	function deshabilitaCtrlsFaltaAdministrativa(isFaltaAdministrativa) {
//		if (isFaltaAdministrativa) {
//			if (isDetenidoExist) {
//				$("#tabsconsultaprincipal" ).tabs("option", "disabled", []);
//			} else {
//				$("#tabsconsultaprincipal" ).tabs("option", "disabled", [2]);
//			}
//		} else {
//			if (isDetenidoExist) {
//				$("#tabsconsultaprincipal" ).tabs("option", "disabled", [3,4,5,6]);
//			} else {
//				$("#tabsconsultaprincipal" ).tabs("option", "disabled", [2,3,4,5,6]);
//			}
//		}
//	}
	
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

	//function habilitaDivPuntoCarretero(){
	//	if($("#chkPuntoCarretero").is(':checked')){
	//		$("#divPuntoCarretero").show();
	//	}else{
	//		ocultaDivPuntoCarretero();
	//	}
	//}

	//function ocultaDivPuntoCarretero(){
	//	$("#divPuntoCarretero").hide();
	//}

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
	    //lsDatosGenerales+="fechaEvento="+$("#datosGeneralesCmpFechaEvento").val();
	    //lsDatosGenerales+="&horaEvento="+$("#datosGeneralesCmpHoraEvento").val();
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

	   /*var domicilio = obtenerParametrosDomicilio();
	   lsDatosGenerales+=domicilio;*/

	   //if($("#chkPuntoCarretero").is(':checked')){
			//var puntoCarretero = obtenerDatosPuntoCarretero();
		    //lsDatosGenerales+=puntoCarretero;
		//}		   

	   //lsDatosGenerales += '&descripcionEvento=' + $('#areaDescripcion').val();
	   //Permite enviar los datos asociados al distrito y la agencia al cual sera enviado el IPH
	   return lsDatosGenerales;
	}

	function guardarDatosGeneralesIPH(){

		var respuesta="fail";
		var params = recuperaDatosGenerales();
		
		if($("#datosGeneralesCmpNumeroTransporteOf").val() == "" || $("#datosGeneralesCmpAsunto").val() == "" ||
		   $("#motivoCmpTipoEvento option:selected").val() == 0 || $("#motivoCmpSubtipoEvento option:selected").val() == "" ||
		   $("#datosGeneralesCmpNumeroEmpleado").val() == "" || $("#datosGeneralesCmpTurno option:selected").val() == "" ||
		   $("#datosGeneralesCmpTipoParticipacion option:selected").val() == "" || 
		   $("#cbxAgencia option:selected").val() == 0 || $("#cbxDistrito option:selected").val() == "" ||
		   ($("#chkOperativo").is(':checked') && ($("#datosGeneralesCmpNombreOperativo").val() == "" || 
		    $("#datosGeneralesCmpComandanteAgrupamiento").val() == "" || $("#datosGeneralesCmpComandanteOperativo").val() == ""))){
			alertDinamico("Debe ingresar valores a los campos obligatorios (*)");
			respuesta = "paramInsuficientes";
		}else{			
			$.ajax({								
			  	  type: 'POST',
			  	  url: '<%= request.getContextPath()%>/guardarDatosGeneralesIPH.do?folioIPH='+folioIPH+'',
			  	  data: params,				
			  	  dataType: 'xml',
			  	  async:false,
			  	  success: function(xml){
			  		  respuesta = "ok";
			  	  }
			 });
			return respuesta;
		}
	}

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
	
	/*function customRange(input) {
		$.timeEntry.setDefaults({show24Hours: true});
		return {minTime: (input.id == 'idHoraDateLapsoFin' ?
			$('#datosGeneralesCmpHoraEvento').timeEntry('getTime') : null),
			maxTime: (input.id == 'datosGeneralesCmpHoraEvento' ?
			$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
	}*/
	
	/*$(function(){
		$('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,5,0],ampmPrefix: ' '});
	});*/

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
						alertDinamico('No existe funcionario con ese número de empleado');
				   }
	    		}
			});
		}else{
			alertDinamico("Debe ingresar un número de empleado");
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
		//deshabilitaCtrlsFaltaAdministrativa(selected != 2 );
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
	
	//Abre una nueva ventana de crear una nuev victima
	function creaNuevaVictima() {
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
	}
	function cargaVictima(nombre,id){
		var row=$('#'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaVictima('+id+')">'+nombre+'</a></td></tr>');
		//$('#nuevaVictima').hide();
	} 
	
	function cargaVictimaDenunciante(nombre,id){
		var row=$('#tblVictima tr:#'+id);
		$(row).remove();
		$('#tblVictima').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificarDenunciante('+id+')">'+nombre+'</a></td></tr>');
	} 
	
	function modificaVictima(id){
		modificarVictima(id);
	}
	
	function cerrarVentanaVictima(){
	    var pantalla ="iframewindowIngresarVictima";
		pantalla += idWindowIngresarVictima;
		$.closeWindow(pantalla);
	}
	//Abre una nueva ventana de crear una nuev victima
	function modificarVictima(id) {
		idWindowIngresarVictima++;
		$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Víctima", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?idVictima='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
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
		var row=$('#tblVictima tr:#'+id);
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
		nombre=nombre+"- "
		$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificaProbableResponsable('+id+')">'+nombre+'<bean:message key="indiciado" /></a></td></tr>');
		
	} 

	function modificaProbableResponsable(id){
		modificarProbResponsable(id);
	}
	
	//Abre una nueva ventana de probable responsable
	function modificarProbResponsable(id) {
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpediente +'" width="1050" height="620" />');			
	}

	//Abre una nueva ventana de Denunciante
	function crearDenunciante(){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroExpediente+'&calidadInv=DENUNCIANTE" width="1040" height="570" />');		
	}
	function cargaDenunciante(nombre,id){
		var row=$('#tblDenunciante tr:#'+id);
		$(row).remove(); 
		$('#tblDenunciante').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarDenunciante" onclick="modificarDenunciante('+id+')">'+nombre+'</a></td></tr>');
		$('#crearDenunciante').hide();
	} 
	
	function eliminarDenunciante(id){
		var row=$('#tblDenunciante tr:#'+id);
		$(row).remove(); 
	} 

	//Abre una nueva ventana de Denunciante
	function modificarDenunciante(id){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Modificar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpediente +'" width="1040" height="570" />');		
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
		
	} 
	function modificarTestigo(id) {
		idWindowIngresarTestigo++;
		$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Testigo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?idTestigo='+id+'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
	}

	//Abre una nueva ventana de ingresar traductor
	function creaNuevoTraductor() {
		idWindowIngresarTraductor++;
	$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Traductor", type:"iframe"});
    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
	}	
	function cargaTraductor(nombre,id){
		var row=$('#'+id);
		$(row).remove(); 
		$('#tblTraductor').append('<tr id="'+id+'"><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarTraductor" onclick="modificaTraductor('+id+')">'+nombre+'</a></td></tr>');
	} 

	function ingresarHechos() {
		idWindowIngresarHechos++;
		$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Hechos", type:"iframe"});
	    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?iphFuncionalidadHidden=true&numeroExpedienteId='+numeroExpedienteId+'&numeroExpediente='+numeroExpediente+'&idCalidad=DENUNCIANTE&idHecho=0 " width="1050" height="600" />');		
	}
	function cargaIngresoHecho(nombre,id){
		$("#ingresarHechos").hide();
		$('#tablaHecho').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="hecho_'+id+'" onclick="consultarHecho('+id+','+numeroExpedienteId+');">'  +nombre+ '</a></td></tr>');
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
	    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?iphFuncionalidadHidden=true&numeroExpedienteId='+numeroExpedienteId+'&numeroExpediente='+numeroExpediente+'&idCalidad=DENUNCIANTE&idHecho='+idHecho +' " width="1050" height="600" />');		
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
		//ocultaMuestraTabVisor("tabTabsQuienDetuvo",1);
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
		$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar aeronave", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente+'&idAeronave='+idAeronave+'&tipoObjeto=AERONAVE" width="600" height="530" />');
	}
	function cargaAeronave(id,tipo){
		$('#tblAeronave tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblAeronave').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarAeronave_'+id+'" onclick="consultarAeronave('+id+')">'+tipo+'</a></td></tr>');
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
		$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar embarcaci&oacute;n", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente+'&idEmbarcacion='+idEmbarcacion+'&tipoObjeto=EMBARCACION" width="600" height="530" />');
	}
	function cargaEmbarcacion(id,tipo){
		$('#tblEmbarcacion tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblEmbarcacion').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarEmbarcacion_'+id+'" onclick="consultarEmbarcacion('+id+')">'+tipo+'</a></td></tr>');
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
		$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880, height:330,title:"Consultar explosivo", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&idExplosivo='+idExplosivo+'&tipoObjeto=EXPLOSIVO " width="880" height="330" />');
	}
	function cargaExplosivo(id,tipo){
		$('#tblExplosivos tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblExplosivos').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarExplosivo_'+id+'" onclick="consultarExplosivo('+id+')">'+tipo+'</a></td></tr>');
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
		$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar sustancia", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia='+idSustancia+'&tipoObjeto=SUSTANCIA" width="900" height="330" />');
	}
	function cargaSustancia(id,tipo){
		$('#tblSustancia tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblSustancia').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarSustancia_'+id+'" onclick="consultarSustancia('+id+')">'+tipo+'</a></td></tr>');
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
		$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar numerario", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario='+idNumerario+'&tipoObjeto=NUMERARIO " width="800" height="350" />');
	}
	function cargaNumerario(id,tipo){
		$('#tblNumerario tr:#'+id).remove();
		if(tipo!="")
		{
			$('#tblNumerario').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarNumerario_'+id+'" onclick="consultarNumerario('+id+')">'+tipo+'</a></td></tr>');
		}
		cerrarVentanaNumerario();
	} 
	function cerrarVentanaNumerario(){
		var pantalla ="iframewindowIngresarNumerario";
		pantalla += idWindowIngresarNumerario;
		$.closeWindow(pantalla);
	}
			
	 /*
	*Funcion que consulta los tribunales asignadas a un Distrito
	*/
	
	 /*
	*Funcion que consulta Distritos
	*/
	function consultarDistritos(){		
		$.ajax({
			type: 'POST',
		    url: '<%=request.getContextPath()%>/consultarDistritos.do',
		    data: '',
		    dataType: 'xml',
		    async: false,
		    success: function(xml){
			    	$(xml).find('listaCatalogo').find('catDistritoDTO').each(function(){
						$('#cbxDistrito').append('<option value="' + $(this).find('catDistritoId').text() + '">' + $(this).find('claveDistrito').text()+"-"+ $(this).find('nombreDist').text() + '</option>');
					});					
			}
		});
	}
	function consultarAgenciasXDistrito(distritoId){
		$('#cbxAgencia').empty();
		$('#cbxAgencia').append('<option value="0">-Seleccione-</option>');
		$.ajax({
			type: 'POST',
		    url: '<%=request.getContextPath()%>/consultarAgenciasDePGJxIdDistrito.do?distritoId='+distritoId+'',
		    data: '',
		    dataType: 'xml',
		    async: false,
		    success: function(xml){
			    	var contAgencias=0;
			    	$(xml).find('listaCatalogo').find('catDiscriminanteDTO').each(function(){
						$('#cbxAgencia').append('<option value="' + $(this).find('catDiscriminanteId').text() + '">' + $(this).find('clave').text()+"-"+ $(this).find('nombre').text() + '</option>');
						contAgencias++;
					});
					if(contAgencias == 0){
						alertDinamico("No existen agencias asignadas a este distrito");
					}
			}
		});
	}
	
	function actualizaComboAgencias(){
		distritoId = parseInt($("#cbxDistrito option:selected").val());
		if(distritoId > 0)
			consultarAgenciasXDistrito(distritoId);
		else{
			$('#cbxAgencia').empty();
			$('#cbxAgencia').append('<option value="0">-Seleccione-</option>');
		}
	}
</script>

</head>

<body>
<div id="dialog-Alert" style="display: none">
	<table>
		<tr>
			<td>
				<span id="divAlertTexto"></span>
			</td>
		</tr>
	</table>	
</div>
	<table width="100%" class="back_pleca_h">
		<tr>			
			<td align="right">
					<!--<input type="button" value="Guardado Parcial" id="btnIPHGuardadoParcial" class="back_button" onclick="guardarDatosGeneralesIPH()"/> -->
					 <input type="button" value="Generar Informe" class="btn_Generico" onclick="generarInformeIPH()"/>
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
			<li id="tabDatosGenerales"><a href="#tabsconsultaprincipal-1">Datos Generales</a></li>
			<li id="tabHechos"><a href="#tabsconsultaprincipal-11">Hechos</a></li>
			<li id="tabInvolucrados"><a href="#tabsconsultaprincipal-2">Personas Involucradas</a></li>
			<li id="tabTransporte"><a href="#tabsconsultaprincipal-3">Medios de Transporte Involucrados</a></li>
			<li id="tabEstupefacientes"><a href="#tabsconsultaprincipal-4">Estupefacientes Involucrados</a></li>
			<li id="tabArmas"><a href="#tabsconsultaprincipal-5">Armas Involucradas</a></li>
			<li id="tabObjectos"><a href="#tabsconsultaprincipal-6">Numerario</a></li>
			<li class="tabTabsCadCus"><a href="#tabsconsultaprincipal-9">Cadena de Custodia</a></li>
			<li id="tabInformacion"><a href="#tabsconsultaprincipal-7">Documentos de Apoyo</a></li>
			<li id="tabObservaciones"><a href="#tabsconsultaprincipal-8">Observaciones Generales</a></li>
		</ul>
		<!--COMIENZAN TABS INFERIORES DE INDIVIDUO-->
		<div id="tabsconsultaprincipal-1" class="tabDatosGenerales">
			<div id="tabschild" class="tabs-bottom">
				<!--<ul>
					<li><a href="#tabschild-1">Generales</a></li>
					<li><a href="#tabschild-2">Motivo</a></li>
				</ul>-->
				
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
											<select id="motivoCmpTipoEvento" style="width: 180px;" onchange="buscaSubTipoEvento();">
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
										<td><input type="text" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNumeroEmpleado"/></td>
										<td><!--input type="button" id="btnFuncionario" value="Validar Funcionario" onclick="buscarFuncionario();" class="btn_Generico"--></td>
									</tr>
									<tr>
										<td align="right">Oficial:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpOficial" readonly="readonly"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Sector:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpSector" readonly="readonly"/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Dirigido a:</td>
										<td><input type="text" style="width: 180px;" maxlength="50" id="datosGeneralesCmpDirigidoA" readonly="readonly"/></td>
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
									  <td align="right">* Distrito:</td>
									  <td><select name="cbxDistrito" id="cbxDistrito" style="width: 180px;" onchange="actualizaComboAgencias()">
									    <option value="">- Seleccione -</option>
								      </select></td>
									  <td>&nbsp;</td>
								  </tr>
									<tr>
									  <td align="right">* Agencia:</td>
									  <td><select name="cbxAgencia" id="cbxAgencia" style="width: 180px;">
									    <option value="">- Seleccione -</option>
								      </select></td>
									   <td>&nbsp;</td>
								  </tr>
			                        <tr>
			                            <td align="right">Se realiz&oacute; Operativo</td>
			                            <td>
			                              <input type="checkbox" id="chkOperativo"/>
			                            </td>                 
										<td><input type="button" id="btnFuncionario" value="Validar Funcionario" onclick="buscarFuncionario();" class="btn_Generico"></td>
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
				
				<!--<div id="tabschild-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table>
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Tipo de Evento:</td>
										<td>
											<select id="motivoCmpTipoEvento" style="width: 180px;" onchange="buscaSubTipoEvento();">
												<option value="0">- Seleccione -</option>
												<option value="1">Delito</option>
												<option value="2">Falta Administrativa</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="right">Subtipo de Evento:</td>
										<td>
											<select id="motivoCmpSubtipoEvento" style="width: 180px;">
												<option value="">- Seleccione -</option>
											</select>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>-->
			</div>
			
		</div>
		<!--TERMINAN TABS INFERIORES DE INDIVIDUO-->
		
		<div id="tabsconsultaprincipal-11" class="tabHechos">
			<div style="width: 1042px; height: 490px;" class="back_hechos">
				<table border="0" cellspacing="0" cellpadding="0" id="tablaHecho" class="back_hechos" style="padding: .5cm; " >
					<tr valign="top">						
						<td valign="top"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="ingresarHechos" value="Ingreso Hecho" class="btn_Generico"/></td>
					</tr>
				</table>
			</div>
		</div>
		
		<!--COMIENZAN TABS INFERIORES DE PERSONAS INVOLUCRADAS-->
		<div id="tabsconsultaprincipal-2" class="tabInvolucrados">
			<div id="tabschild2" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild2-1">Denunciante</a></li>
					<li><a href="#tabschild2-2">Víctima</a></li>
					<li><a href="#tabschild2-3"><bean:message key="probableResponsable" /></a></li>
					<li><a href="#tabschild2-4">Testigo</a></li>
				</ul>
				
				<div id="tabschild2-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante"><input type="button" value="Ingresar Denunciante" class="btn_Generico"/></a></td>
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
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima"><input type="button" value="Ingresar Víctima" class="btn_Generico"/></a></td>
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
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable"><input type="button" value='<bean:message key="ingProbaleResponsable" />' class="btn_Generico"/></a></td>
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
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo"><input type="button" value="Ingresar Testigo" class="btn_Generico"/></a></td>
									</tr>
									
								</table>
							</td>
						</tr>
					</table>
				</div>
					
			</div>

		</div>
		<!--COMIENZAN TABS INFERIORES DE MEDIOS DE TRANSPORTE-->
		<div id="tabsconsultaprincipal-3" class="tabTransporte">
			<div id="tabschild3" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild3-1">Veh&iacute;culo</a></li>
					<li><a href="#tabschild3-2">Aeronave</a></li>
					<li><a href="#tabschild3-3">Embarcaci&oacute;n</a></li>
				</ul>
				
				<div id="tabschild3-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblVehiculo">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoVehiculo"><input type="button" value="Ingresar Vehículo" class="btn_Generico"/></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild3-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblAeronave">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaAeronave" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild3-3">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblEmbarcacion">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>

		</div>
		<!--COMIENZAN TABS INFERIORES DE ESTUPEFACIENTES-->
		<div id="tabsconsultaprincipal-4" class="tabEstupefacientes">
			<div id="tabschild4" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild4-1">Sustancia</a></li>
				</ul>
				
				<div id="tabschild4-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblSustancia">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
								
			</div>

		</div>
		<!--COMIENZAN TABS INFERIORES DE ARMAS-->
		<div id="tabsconsultaprincipal-5" class="tabArmas">
			<div id="tabschild5" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild5-1">Arma</a></li>
					<li><a href="#tabschild5-2">Explosivo</a></li>
				</ul>
				
				<div id="tabschild5-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblArma">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild5-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblExplosivos">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoExplosivo" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>

		</div>
		<div id="tabsconsultaprincipal-6">
			<div id="tabschild6" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild6-1">Numerario</a></li>
					<!-- <li><a href="#tabschild6-2">Otros</a></li>-->
				</ul>
				
				<div id="tabschild6-1">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblNumerario">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>
				
				<!-- <div id="tabschild6-2">
					<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
						<tr valign="top">
							<td valign="top">
								<table width="25%" cellpadding="0" cellspacing="0" id="tblOtros">
									<tr>
										<td>&nbsp;
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoOtro" value="Ingreso nuevo" class="btn_Generico"/></td>
									</tr>						
								</table>
							</td>
						</tr>
					</table>
				</div>-->
			</div>
					
		</div>
			
		<div id="tabsconsultaprincipal-7">
		</div>
		<div id="tabsconsultaprincipal-8">
	    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
	    		<tr>
		    		<td align="center"><textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea></td>
		    	</tr>
		    </table>
		</div>
		<div id="tabsconsultaprincipal-9" class="tabTabsCadCus">
				 <input type="button" class="btn_Generico" id="btnCadCusNuevaCadCus" style="width: 200px;" value="Crear nueva cadena de custodia"/>
				 <input type="button" class="btn_Generico" id="btnCadCusConsultaCadCus" style="width: 200px;" value="Consultar cadena de custodia"/>    
  				 <input type="button" class="btn_Generico" id="btnCadCusRegEslabones" style="width: 200px;" value="Registrar eslabones"/> 
  				 <input type="button" class="btn_Generico" id="btnCadCusRepEvidencias" style="width: 200px;" style="width: 250px;" value="Reporte de evidencias"/>
  				 <input type="button" class="btn_Generico" id="btnCadCusElabOficio" style="width: 250px;" value="Elaborar oficio para fijación y preservación"/> 
   				 <input type="button" class="btn_Generico" id="btnCadCusAdmDestino" style="width: 250px;" value="Administrar destino legal de evidencia"/>
		</div>		
		
	</div>
	<div id="dialog-detenido-informe" title="Aviso">
		<p align="center">
			<!-- <span id="logout">¿Existe alg&uacute;n detenido en el informe?</span> -->
		</p>
	</div>

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
