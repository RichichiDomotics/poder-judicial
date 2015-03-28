<%@page import="mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atender Notificación</title>
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<!--Hojas de Estilo	-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<!--Scripts-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<!--para controlar las ventanas-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	<script type="text/javascript">
	var x=0;
	var eventoDTOID=parent.eventoDTOID;
	//variable para almacenar el id del evento
	var idEvento;
	//Id del expediente que es consultado en el metodo consultaDetalleEvento(idEvento)
	//que posteriormente es usado para agregar notificacion
	var idExpediente;
	//Bandera para recargar el grid
	var primeraConsulta="true";
	var idnotificacion;
	var idinvolucrado;
	var docID;
	var archivoDigitalId;
	var documentoId;
	var idDocumentoGlobal;

	var numeroExpediente;
	var numeroExpedienteId;
	/**
	* Variables globales para controlar los datos del destinatario
	*/
	var lstDestinatarios = new Array();
	var idDestinatario = 0;
	
	$(document).ready(function() {

		//Se crean las tabs
		$("#tabsprincipalconsulta" ).tabs();
		//Se agrega la funcion agregarDestinatario al dar click en el botn agregar destinatario
		$('#agregarDestinatarioBoton').bind('click', agregarDestinatario);
		//Se oculta el domicilio notificaciones de la JSP de ingresarDomicilioView
		$("#liDomNotif").hide();
		//Oculta la ceja de la tab Ingresar Domicilio, para que no se vea el JSP include 
		$("#liDom").hide();
		//Oculta completamente la tab de domicilio notificaciones
		$("#tabs-2").hide();
		//Oculta el renglon donde se encuentra la ceja de domicilio notificaciones
		$("#rowDomicilioNotif").hide();		
		
		//Id del evento que viene desde el gird de la bandeja de notificaciones
		idEvento=<%= request.getAttribute("idEvento")%>;
		//numeroExpediente="<%= request.getParameter("numeroExpediente")%>";
		//LLamada aconsultar el detalle del evento para llenar los campos de la Tab Detalle evento
		consultaDetalleEvento(idEvento);
		
		/*
		*Funcion que carga el grid, con el detalle de las notificaciones asociadas
		*/
		jQuery("#gridDetalleNotificaciones").jqGrid({ 
			url:'<%= request.getContextPath()%>/atenderSolicitudAudienciaNotificador.do?tipoDeRespuesta=0&idEvento='+idEvento+'', 
			datatype: "xml", 
			colNames:['Interveniente','idInstitucion','Institución','Notificación Enviada','Notificación Recibida','No. de Notificaciones','esFuncionario'], 
			colModel:[ 	{name:'involucrado',index:'involucrado', width:150},
			           	{name:'idInstitucion',index:'idInstitucion', width:150, hidden:true},
						{name:'institucion',index:'institucion', width:150}, 
						{name:'notificacionEnviada',index:'notificacionEnviada', width:150},
						{name:'notificacionRecibida',index:'notificacionRecibida', width:150},
						{name:'noNotificaciones',index:'noNotificaciones', width:150},
						{name:'esFuncionario',index:'esFuncionario', width:150, hidden:true}
																	
					],
			pager: jQuery('#pager1'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			onSelectRow: function(rowid) {
				mostrarDetalleNotificacionesPersona(rowid,idEvento);
				idinvolucrado=rowid;
					}
			
		}).navGrid('#pager1',{edit:false,add:false,del:false});	

	});//FIN ON READY

	/*
	*Funcion que limpia las cajas de texto
	*antes de llenarlas con los datos de la
	*nueva consulta
	*/	
	function limpiaDatosDetalleEvento(){
		
		$("#numCasoNotifDetalle").val("");
		$("#numExpedienteNotifDetalle").val("");
		$("#tipoEventoNotifDetalle").val("");
		$("#eventoNotifDetalle").val("");
		$("#fechaEventoNotifDetalle").val("");
		$("#horaEventoNotifDetalle").val("");
		$("#lugarEventoNotifDetalle").val("");
		$("#direccionEventoNotifDetalle").val("");
	}

	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultaDetalleEvento(idEvento){

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/atenderSolicitudAudienciaNotificador.do?tipoDeRespuesta=1',
			data: 'idEvento='+ idEvento, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
    				limpiaDatosDetalleEvento();
					$("#numCasoNotifDetalle").val($(xml).find('numeroGeneralCaso').first().text());
					$("#numExpedienteNotifDetalle").val($(xml).find('numeroExpediente').first().text());
					numeroExpediente = $(xml).find('numeroExpediente').first().text();
					numeroExpedienteId = $(xml).find('numeroExpedienteId').first().text();
					
					$("#tipoEventoNotifDetalle").val($(xml).find('tipoEvento').first().text());
					$("#eventoNotifDetalle").val($(xml).find('tipo').find('valor').first().text());

					//Se le da formato a la fecha del nuevo evento
					var fechEvento =  $(xml).find('fechaEvento').first().text();
					var fechEventoPos1 = fechEvento.indexOf(":",0); 

					$("#fechaEventoNotifDetalle").val(fechEvento.substring(0,fechEventoPos1-2));

					//Se le da formato a la hora del nuevo evento
					var horaEvento =  $(xml).find('fechaEvento').first().text();
					var horaEventoPos1 = horaEvento.indexOf(":",0); 

					$("#horaEventoNotifDetalle").val(horaEvento.substring(horaEventoPos1-2,horaEventoPos1+3));
					$("#lugarEventoNotifDetalle").val($(xml).find('lugarEvento').first().text());
					$("#direccionEventoNotifDetalle").val($(xml).find('ubicacionEvento').first().text());
					idExpediente=$(xml).find('expedienteId').first().text();
    			}
				else{
					//Mostrar mensaje de error
				}
			}
		});
	}

	/*
	*Funcion que recibe el id de la notificacion
	*y consulta el detalle de notificaciones asociadas a una persona
	*/
	function mostrarDetalleNotificacionesPersona(rowID,idEvento){

		//abre la ventana modal en la que aparecen los detalles de las notificaciones por persona
		ventanaModal();
		//Consulta y muestra los datos de la persona
		mostrarDatosPersonaNotificacion(rowID,idEvento);
		
		var ret = jQuery("#gridDetalleNotificaciones").jqGrid('getRowData',rowID);
		var params ='idEvento='+idEvento;
			params+='&rowID='+rowID;
			params+='&esFuncionario='+ret.esFuncionario;
			params+='&tipoDeRespuesta=0';
		
		if(primeraConsulta == "true"){
			//Llena el grid con los datos de la persona seleccionada
			  jQuery("#gridDetalleNotificacionesPersona").jqGrid({
				  			url:'<%= request.getContextPath() %>/consultaDetalleNotificacionesPersona.do?'+params, 						
							datatype: "xml", 					
							colNames:['No. Notificación','Notificador','Forma notificación','Notificación enviada','Notificación recibida','Documento'], 
							
							colModel:[{name:'noNotificacion',index:'noNotificacion', width:100, align:"center", sortable:false},
							          {name:'notificador',index:'notificador', width:100, align:"center", sortable:false},
							          {name:'formaNotificacion',index:'formaNotificacion', width:130, align:"center", sortable:false},
							          {name:'notificacionEnviada',index:'notificacionEnviada', width:130, align:"center", sortable:false},
							          {name:'notificacionRecibida',index:'notificacionRecibida', width:130, align:"center", sortable:false},
							          {name:'documento',index:'documento', width:130,align:"center", sortable:false}
		
							],
							rowNum:10,
							rowList:[25,50,100],
							pager: jQuery('#pager2'), 
							sortname: 'notificador', 
							viewrecords: true,
							sortorder: "desc", 
							width: 800,
							height: 150,
							loadComplete: function(rowid){
								primeraConsulta="false";
								
							},
							onSelectRow: function(rowid) {
								idnotificacion=rowid;
								//archivoDigitalId=rowid;
								documentoId=rowid;

								$("#botonAdjuntarDocumento").attr('disabled', false);
								$("#botonGeneraActa").attr('disabled', false);
								
									},
							caption:"Lista de Documentos Relacionados al Expediente" 
						}).navGrid('#pager2',{edit:false,add:false,del:false});				
		}
		else{
			jQuery("#gridDetalleNotificacionesPersona").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/consultaDetalleNotificacionesPersona.do?'+params,datatype: "xml" });
			$("#gridDetalleNotificacionesPersona").trigger("reloadGrid");
		}
			
		
	}
	
	/*
	*Funcion que abre la ventana modal para mostrar el detalle de las
	*notificaciones asociadas a una persona
	*/
	function ventanaModal (){

		//abre la ventana de detalle de la persona
		$("#ventanaDetallePersona").dialog("open");
		$("#ventanaDetallePersona").dialog({ autoOpen: true, 
	  		modal: true, 
	  		title: 'Detalle de Notificaciones de Persona', 
	  		dialogClass: 'alert',
	  		position: [312,40],
	  		width: 890,
	  		height: 510,
	  		maxWidth: 1000,
	  		buttons:{"Agregar Notificación":function() {
		  			$(this).dialog("close");
		  			generarNotificacion();
		  			//volver a subir el objeto a sesión
		  			consultaDetalleEvento(idEvento);
		  			//Recarga el grid
		  			$("#gridDetalleNotificaciones").trigger("reloadGrid");
		  			$("#gridDetalleNotificacionesPersona").trigger("reloadGrid");
	  			}
	  		}
	  	});
	}

	
	/*
	*Funcion consulta los datos de la persona (nombre, institucion, direccion fisica y direccion electronica)
	* y los muestra en la ventana modal de detalle de notificaciones  
	*/
	function mostrarDatosPersonaNotificacion(rowID,idEvento){

		limpiaDatosPersonaNotificacion();
		
		var ret = jQuery("#gridDetalleNotificaciones").jqGrid('getRowData',rowID);
		var params ='idEvento='+idEvento;
			params+='&rowID='+rowID;
			params+='&esFuncionario='+ret.esFuncionario;
			params+='&tipoDeRespuesta=1';
		
		$.ajax({
			type: 'POST',
			url:'<%= request.getContextPath() %>/consultaDetalleNotificacionesPersona.do?'+params,
			data: '', 
			async: false,
			dataType: 'xml',
			success: function(xml){
				
				var errorCode;
				
				errorCode=$(xml).find('response').find('code').text();
				
				//SI ES FUNCIONARIO
				if(parseInt(errorCode)==0){
					
					//Nombre Completo de la persona
					var nombre="";
					var apPat="";
					var apMat="";
					if(ret.esFuncionario == 1){
						nombre = $(xml).find('funcionario').find('nombreFuncionario').first().text();
						apPat = $(xml).find('funcionario').find('apellidoPaternoFuncionario').first().text();
						apMat = $(xml).find('funcionario').find('apellidoMaternoFuncionario').first().text();
						$("#nombreDetallePersona").val(nombre+" "+apPat+" "+apMat);
						$("#institucionDetallePersona").val($(xml).find('institucion').find('nombreInst').first().text());
						
						$("#trDirecionFisica").attr('hidden', true);
						$("#trDirecionElectronica").attr('hidden', true);
						
						$("#botonAdjuntarDocumento").attr('hidden', true);
						$("#botonGeneraActa").attr('hidden', true);
						
					}
					
					//SI NO ES FUNCIONARIO
					else{
						$("#trDirecionFisica").attr('hidden', false);
						$("#trDirecionElectronica").attr('hidden', false);
						
						$("#botonAdjuntarDocumento").attr('hidden', false);
						$("#botonGeneraActa").attr('hidden', false);
						
						
						nombre = $(xml).find('nombresDemograficoDTO').find('nombre').first().text();
						apPat = $(xml).find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
						apMat = $(xml).find('nombresDemograficoDTO').find('apellidoMaterno').first().text();

						$("#nombreDetallePersona").val(nombre+" "+apPat+" "+apMat);

						//Institucion
						$("#etiInstitucion").hide();
						$("#institucionDetallePersona").hide();
						$("#institucionDetallePersona").val($(xml).find('institucionPresenta').find('nombreInst').first().text());
													
			 			//Direccion Fisica
						var calle="", numExt="", numInt="", colonia="", codPostal="", municipio="", entFed="", pais="";
						
						calle = $(xml).find('involucrado').find('domicilioNotificacion').find('calle').first().text();
						
						numExt = $(xml).find('involucrado').find('domicilioNotificacion').find('numeroExterior').first().text();
						numInt = $(xml).find('involucrado').find('domicilioNotificacion').find('numeroInterior').first().text();
						colonia = $(xml).find('involucrado').find('domicilioNotificacion').find('asentamientoDTO').find('nombreAsentamiento').first().text();
						codPostal = $(xml).find('involucrado').find('domicilioNotificacion').find('asentamientoDTO').find('codigoPostal').first().text();
						municipio = $(xml).find('involucrado').find('domicilioNotificacion').find('asentamientoDTO').find('municipioDTO').find('nombreMunicipio').first().text();
						entFed = $(xml).find('involucrado').find('domicilioNotificacion').find('asentamientoDTO').find('municipioDTO').find('entidadFederativaDTO').find('abreviacion').first().text();
						pais = $(xml).find('involucrado').find('domicilioNotificacion').find('asentamientoDTO').find('municipioDTO').find('entidadFederativaDTO').find('valorIdPais').find('valor').first().text();

						$("#direccionFisicaDetallePersona").val(calle+" No.Ext: "+numExt+" No.Int: "+numInt+" Col. "+colonia+" CP. "+codPostal+"  "+municipio+". "+pais+" ,"+entFed);
						
						//Direccion Electrónica
	    				$("#direccionElectronicaDetallePersona").val($(xml).find('correosDTO').find('direccionElectronica').first().text());

					}
    			}
				else{
					//Mostrar mensaje de error
				}
			}
		});
	}	

	function limpiaDatosPersonaNotificacion(){
		
		$("#nombreDetallePersona").val("");
		$("#institucionDetallePersona").val("");
		$("#direccionFisicaDetallePersona").val("");
		$("#direccionElectronicaDetallePersona").val("");
	}
	
	/*
	*Funcion que abre el frame de detalle de notificaciones por persona 
	*/
	function dblClickRowDetalleNotificacionesPersona(rowID){
		$.newWindow({id:"iframewindowDetalleNotificacionesPersona", statusBar: true, posx:200,posy:50,width:700,height:400,title:"Detalle de Notificaciones de Persona", type:"iframe"});
    	$.updateWindowContent("iframewindowDetalleNotificacionesPersona",'<iframe src="<%=request.getContextPath()%>/atenderSolicitudAudienciaNotificador.do" width="700" height="400" />'); 
	}
		
	//Crea una nueva ventana para crear un nuevo documento
	function generarDocumentoView() {
	    $.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitud de Estudio", type:"iframe"});
        //$.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+docID+"&esconderArbol=1&formaId=88&numeroUnicoExpediente="+numeroExpediente+"' width='1140' height='400' />");
	    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+docID+"&esconderArbol=1&formaId="+formaId+"&numeroUnicoExpediente="+numeroExpediente+"' width='1140' height='400' />");
	}
	//Funcion que agrega un destinatario
	  function agregarDestinatario(){
		  
		$("#agregarDestinatario").dialog("open");
	  	$("#agregarDestinatario").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Agregar Destinatario', 
		  	dialogClass: 'alert',
		  	position: [312,40],
		  	width: 810,
		  	height: 540,
		  	maxWidth: 1000,
		  	buttons:{"Agregar":function() {		  			

		  		//Agrega el nuevo destinatario al grid
		  		nombreCompleto = ($('#nombre').val()+" "+$('#apPat').val()+" "+$('#apMat').val());
		  		institucion = $('#cmbInstitucion option:selected').val();
		  		
		  		
		  		var destinatario = new Destinatario(nombreCompleto,institucion);
		  		lstDestinatarios[idDestinatario] = destinatario;
		  		idDestinatario++;
		  		
		  		jQuery("#gridDetalleNotificaciones").jqGrid('addRowData',idDestinatario,destinatario);
		  		jQuery("#gridDetalleNotificaciones").trigger("updateGrid");
		  		
		  		//agregamos todos los parametros que vamos a enviar al action para el guardado
		  		var parametrosDomicilio = obtenerParametrosDomicilio();
				var nuevoDestinatario="";
				
				nuevoDestinatario +='idExpediente='+idExpediente;
				nuevoDestinatario +='&nombreDest='+ $('#nombre').val();
				nuevoDestinatario +='&apPatDest='+ $('#apPat').val();
				nuevoDestinatario +='&apMatDest='+ $('#apMat').val();
				nuevoDestinatario +='&institucionDest=' + $("#cmbInstitucion option:selected").val();
				nuevoDestinatario +='&dirElectDest=' + $('#correo').val();
				nuevoDestinatario +='&domicilioDest='+parametrosDomicilio;
				//Agregamos el nuevo destinatario
				 agregarDestinatarioNotificacion(nuevoDestinatario);				 
		  		$(this).dialog("close");
		  		limpiaAgregarNuevoDestinatario();		  	
		  	},
		  	"Cancelar":function() {	
		  			$(this).dialog("close");
		  			limpiaAgregarNuevoDestinatario();
		  		}
		  	}
		});	  	
	}	

	/**
	*Funcion que funciona como constructor para la estrucutra destinatario
	*/
	function Destinatario(involucrado, institucion){
		this.involucrado = involucrado;
		this.institucion = institucion;			
	} 

	/**
	*Funcion que realiza el paso de parametros para el guardado de la
	*informacion del destinatario. 
	*/
	function agregarDestinatarioNotificacion(nuevoDestinatario) {

		
		$.ajax({
  			type: 'POST',
  			url: '<%= request.getContextPath()%>/agregarDestinatario.do?idEvento='+idEvento+'',
  			data: nuevoDestinatario,
  			dataType: 'xml',
  			async: false,
  			success: function(xml){
  	  			
  				consultaDetalleEvento(idEvento);
  				jQuery("#gridDetalleNotificaciones").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/atenderSolicitudAudienciaNotificador.do?tipoDeRespuesta=0&idEvento='+idEvento+'',datatype: "xml" });
  				$("#gridDetalleNotificaciones").trigger("reloadGrid");
  			}
		});
 	}

	/**
	*Funcion que limpia todos los datos de la ventana de agregar nuevo destinatario 
	*/
	function limpiaAgregarNuevoDestinatario() {
		//Limpia los campos del destinatario
		$("#nombre").val("");
		$("#apPat").val("");
		$("#apMat").val("");
		$("#correo").val("");
		$("#cmbInstitucion").attr('selectedIndex', 0);
		//Limpia la direccion fisica
		seleccionaMexico();
		$('#codigoPostal').val("");
		 $('#entidadFederativa').val("");
		 $('#areaCiudad').val("");
		 $('#areaDelegacionMunicipio').val("");
		 $('#areaColonia').val("");
		 $('#areaAsentamiento').val("");
		 $('#areaTipoCalle').val("");
		 $('#areaCalle').val("");
		 $('#areaNumeroExterior').val("");
		 $('#areaNumeroInterior').val("");
		 $('#areaReferencias').val("");
		 $('#areaEntreCalle').val("");
		 $('#areaYCalle').val("");
		 $('#areaAlias').val("");
		 $('#areaEdificio').val("");
		//Limpia los datos de coordenadas
		$('#txtFldLongitudEste').val("");
		$('#txtFldLongitudGrados').val("");
		$('#txtFldLongitudMinutos').val("");
		$('#txtFldLongitudSegundos').val("");
		$('#txtFldLatitudNorte').val("");
		$('#txtFldLatitudGrados').val("");
		$('#txtFldLatitudMinutos').val("");
		$('#txtFldLatitudSegundos').val("");
	}
	
	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function generarNotificacion(idEvento){
		var rowID = jQuery("#gridDetalleNotificaciones").jqGrid('getGridParam','selrow');		
		var ret = jQuery("#gridDetalleNotificaciones").jqGrid('getRowData',rowID);

        var param ='id='+eventoDTOID;
        	param += '&numeroExpedienteId='+numeroExpedienteId;
			param += '&elementoId='+rowID;
			param += '&esFuncionario='+ret.esFuncionario;
		
		if(ret.esFuncionario == 1 || ret.esFuncionario == '1'){
			param+='&nombre='+ret.involucrado;
			param+='&institucion='+ret.idInstitucion;
		}
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/generarNotificacion.do',
			data: param, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				documentoId=$(xml).find('long').text();
				if(documentoId != undefined && documentoId != null && documentoId != ""){
					generarDocumentoDirecto();
					if(ret.esFuncionario == 1 || ret.esFuncionario == '1'){
						enviarNotificacionWS();
					}
					abrirPDF();
				}else{
					alertDinamico("No se logró generar la notificación");
				}

			}
		});

	}
	
	function generarDocumentoDirecto(){
		var param ="";
		//param += "formaId=<%=Formas.ACTA.getValorId()%>";
		param += "formaId=<%=Formas.NOTIFICACION_DE_AUDIENCIA.getValorId()%>";
		param += "&documentoId="+documentoId;
		param += "&tipoDocumento=<%=TipoDocumento.NOTIFICACION.getValorId()%>";
		param += "&numeroUnicoExpediente="+numeroExpediente;
		param += "&returnDocument=0";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/GenerarDocumentoDirecto.do',
			data: param, 
			async: false,
			dataType: 'xml',
			success: function(xml){}
		});
		
	}

	function enviarNotificacionWS(){
		var rowID = jQuery("#gridDetalleNotificaciones").jqGrid('getGridParam','selrow');		
		var ret = jQuery("#gridDetalleNotificaciones").jqGrid('getRowData',rowID);
		var param ="";
		param += "id="+eventoDTOID;
		param += "&documentoId="+documentoId;
		param += "&nombre="+ret.involucrado;
		param += "&institucion="+ret.idInstitucion;
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/enviarNotificacionByWebService.do',
			data: param, 
			async: false,
			dataType: 'xml',
			success: function(xml){}
		});
	}
	
	function abrirPDF(){
		document.frmDoc.archivoDigitalId.value = archivoDigitalId;
		document.frmDoc.documentoId.value = documentoId;
		document.frmDoc.submit();
		
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

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsprincipalconsulta-1">Detalle Evento</a></li>
		<li><a href="#tabsprincipalconsulta-2">Notificaciones</a></li>
  	</ul>
  	<div id="tabsprincipalconsulta-1" style="height: 500px">
		<table width="720" border="0" align="center" cellpadding="0" cellspacing="5">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td width="30%" align="right"><strong>Numero de Caso:</strong></td>
				<td width="70%"><input type="text" id="numCasoNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Numero de Expediente:</strong></td>
				<td><input type="text" id="numExpedienteNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Tipo de Evento: </strong></td>
				<td><input type="text" id="tipoEventoNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Evento:</strong></td>
				<td><input type="text" id="eventoNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Fecha de Evento:</strong></td>
				<td><input type="text" id="fechaEventoNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Hora de Evento:</strong></td>
				<td><input type="text" id="horaEventoNotifDetalle" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Lugar de Evento:</strong></td>
				<td><input type="text" id="lugarEventoNotifDetalle" style="width:500px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right"><strong>Dirección de Evento:</strong></td>
				<td><input type="text" id="direccionEventoNotifDetalle" style="width:500px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</div>
	
	<div id="tabsprincipalconsulta-2" style="height: 500px">
		<table align="center" cellspacing="0" cellpadding="0" >
			<tr>
				<td colspan="2">&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<table id="gridDetalleNotificaciones" >
	                	<tr>
	                		<td>&nbsp;</td>
	                	</tr>
					</table>
					<div id="pager1"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td width="150" align="center">&nbsp;</td>
				<td width="149" align="center">
					<input type="button" value="Agregar Destinatario" id="agregarDestinatarioBoton" class="btn_Generico"/>
				</td>
				<td width="150" align="center">
					<!--<input type="button" value="Guardar" id="guardar" />-->
				</td>
				<td width="149" align="center">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</div>
</div>
	
<div id="ventanaDetallePersona" style="display: none">
	
	<table cellspacing="0" cellpadding="0" >
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	             <tr>
			<td width="153" align="right"><strong>Nombre:</strong></td>
			<td colspan="2"><input type="text" size="80" id="nombreDetallePersona" style="border: 0; background:#DDD;" readonly="readonly"/></td>
		</tr>
	             <tr>
			<td align="right"><span id="etiInstitucion"><strong>Institución:</strong></span></td>
			<td colspan="2"><input type="text" size="80" id="institucionDetallePersona" style="border: 0; background:#DDD;" readonly="readonly"/></td>
		</tr>
	    <tr id="trDirecionFisica">
			<td align="right"><strong>Dirección Fisica:</strong></td>
			<td colspan="2"><input type="text" size="80" id="direccionFisicaDetallePersona" style="border: 0; background:#DDD;" readonly="readonly"/></td>
		</tr>
	    <tr id="trDirecionElectronica">
			<td align="right"><strong>Direccion Electronica:</strong></td>
			<td colspan="2"><input type="text" size="80" id="direccionElectronicaDetallePersona" style="border: 0; background:#DDD;" readonly="readonly"/></td>
		</tr>           
	         	<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr><td colspan="3" align="center">
		
			</td>
		</tr>
	</table>	
    
    <table id="gridDetalleNotificacionesPersona" align="center" >                
	</table>
	<div id="pager2"></div>
	<table>
		<tr>
			<td><input type="button" value="Generar Acta"
				onclick="abrirPDF()" id="botonGeneraActa" disabled="disabled" class="btn_Generico">
			</td>
			<td><input type="button" value="Adjuntar Documento"
				id="botonAdjuntarDocumento" style="display: none;"
				disabled="disabled" class="btn_Generico"></td>
		</tr>
	</table>

</div>
	
<div id="agregarDestinatario" style="display: none">
	
	<table width="780" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="196">&nbsp;</td>
        <td width="263">&nbsp;</td>
        <td width="164" align="right">&nbsp;</td>
        <td width="247">&nbsp;</td>
        
      </tr>
      <tr>
        <td align="right">Nombre:</td>
        <td><input type="text" size="30" id="nombre"/></td>
        <td align="right">Institución:</td>
        <td>
            <select name="select" id="cmbInstitucion" style="width:165px;">
            <option value="Seleccione">-Seleccione-</option>
            <option value="Defensoria">Defensoria</option>
            <option value="Procuraduria">Procuraduria</option>
            </select>
        </td>
      </tr>
      <tr>
        <td align="right">Apellido Paterno:</td>
        <td><input type="text" size="30" id="apPat"/></td>
        <td align="right">Dirección Electronica:</td>
        <td><input type="text" size="30" id="correo"/></td>
      </tr>
      <tr>
        <td align="right">Apellido Materno:</td>
        <td><input type="text" size="30" id="apMat"/></td>
        <td align="right">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="4" align="center" valign="top">Direcci&oacute;n Fis&iacute;ca:</td>
      </tr>
      <tr>
        <td colspan="4"><jsp:include page="ingresarDomicilioView.jsp"></jsp:include></td>
      </tr>
    </table>
	

</div>
<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="archivoDigitalId" value=""/>
	<input type="hidden" name="documentoId" value="">
</form>

<form name="formaDocumento" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
	<input type="hidden" value="" id="formaId" >
	<input type="hidden" value="" id="documentoId">
	<input type="hidden" value="" id="tipoDocumento">
	<input type="hidden" name="numeroUnicoExpediente" />
</form>

</body>
</html>