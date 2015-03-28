/**
 * Este archivo contiene las funciones que le otorgan su funcionalidad al contenido
 * del archivo actuaciones.jsp, el mismo que puede ser reutilizado sin mayor parametrizacion
 * que pasar a la funcion de inicializacion el numero del expediente y definir la variable contextPath
 */

var actExpedienteId = 0;
var actEtapaExpediente = "";
var actNumeroExpedienteId = 0;
var actNumeroExpedienteSt = "";
var actInvolucradosIds = [];

function inicilizarTabActuaciones(expedienteId, numeroExpedienteId, numeroExpedienteSt, etapaExpediente, involucrados, situacionActualDefendido){
	actExpedienteId = expedienteId;
	actNumeroExpedienteId = parseInt(numeroExpedienteId);
	actNumeroExpedienteSt = numeroExpedienteSt;
	actEtapaExpediente = etapaExpediente;
	actInvolucradosIds = involucrados;
	actSituacionDefendido = situacionActualDefendido;
	
	cargaActuaciones(actNumeroExpedienteId, actNumeroExpedienteSt);
	$("#cbxAcciones").change(accionesExpediente);
	$("#teoriaDelCaso").hide();
	cargarComboCambiarEtapa();
	cargarComboSituacionDefendido();
}

/**
 * Funcion que carga el catalogo de Actuaciones en el combo cbxAcciones, recicbe
 * como parametros el identificador del numero de expediente y/o el numero del expediente,
 * en caso de omitire el parametro <code>numeroExpedienteId</code> o no disponer del mismo,
 * se puede enviar solo la cadena numeroExpedientSt que requerira que previamente se encuente
 * cargado el expediente en sesión. 
 * @param numeroExpedienteId Identificador del Expediente de trabajo (relacionado a la entidad NumeroExpediente)
 * @param numeroExpedienteSt Cadena con el numero de expediente que identifica al expediente de trabajo
 */
function cargaActuaciones(numeroExpedienteId, numeroExpedienteSt) {
	var param = "";
	param += "numeroExpedienteId="+numeroExpedienteId;
	param += "&numeroExpediente="+numeroExpedienteSt;
	var accion = contextPath+'/cargarActuaciones.do';
	peticionSincronaAjaxXML(accion, param, parseActuacionesXMLResponse);
}

function parseActuacionesXMLResponse(xml){
	$(xml).find('catActuaciones').each(function(){
		$('#cbxAcciones').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
	});
}

/*
* Funcion para sacar el valor del select de acciones
*/
function accionesExpediente() {
  	var selected = $("#cbxAcciones option:selected");
	var param = "idConf="+selected.val();
	var accion = contextPath+'/obtenerConfActividadDocumento.do';
	peticionSincronaAjaxXML(accion, param, ejecutarActuacionXMLResponse);	
}

function ejecutarActuacionXMLResponse(xml){
	var actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
	var formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
	var titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
	var usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
	var estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
	
  	switch (parseInt(actividad)) {
		case 1656: //Actividades.GENERAR_ACUERDO_CON_AMP
		case 2193: //Actividades.GENERAR_ACUERDO_DE_DEFENSA
		case 2481: //Actividades.GENERAR_LIBRO_DE_GOBIERNO
			asociarActividadExpediente(actividad);
			generarDocumentoSinCaso(formaID, titulo, usaeditor);
		break;
		case 2192: //Actividades.GENERAR_CEDULA_DE_IDENTIFICACION
			asociarActividadExpediente(actividad);
			generarDocumentoSinCasoConArbol(formaID, titulo, usaeditor);
		break;
		case 2522: //Actividades.ADJUNTAR_ACUERDO_RESTAURATIVO
			actuacionAdjuntarDocumento('0', formaID);
		break;
		case 2483: //Actividades.SOLICITAR_CARPETA_DE_INVESTIGACION
			actuacionEnviarSolicitudCarpetaInvestigacion();
		break;
		case 2408: //Actividades.SOLICITAR_SERVICIO_PERICIAL
		case 2291: //Actividades.SOLICITAR_DICTAMENES_PERICIALES
			actuacionEnviarSolicitudServicioPericial();
		break;

		case 2255: //Actividades.SOLICITAR_AUDIENCIA
			actuacionEnviarSolicitudAudiencia();
        break;
		case 2261: //Actividades.SOLICITAR_TRANSCRIPCION_DE_AUDIENCIA
		case 2256: //Actividades.SOLICITAR_COPIA_DE_AUDIO_Y_VIDEO_DE_AUDIENCIA
			enviarSolicitudTranscripcionAudioyVideoAudienca();
        break;
	}
}

function asociarActividadExpediente(actividad){
	var param = 'numeroExpedienteId=' + actNumeroExpedienteId; 
	param += '&actividad=' + actividad;
	param += '&numeroExpediente=' + actNumeroExpedienteSt;
	var accion = contextPath+'/asociarActividadExpediente.do';
	peticionSincronaAjaxXML(accion, param);
}

function generarDocumentoSinCaso(forma, titulo, usaeditor) {
 	if(usaeditor){
 		var accion = contextPath+"/generarDocumentoSinCaso.do?";
 		accion += "esconderArbol=0";
 		accion += "&formaId="+ forma;
 		accion += "&numeroUnicoExpediente="+ numeroExpediente;
 		openNewIframeWindow("iframewindowGenerarDocumento", accion, titulo, 1140, 400, 200, 50);
 	}else{
		generarDocumentoDirecto(forma, '', '', actNumeroExpedienteSt);
 	}
}

function generarDocumentoSinCasoConArbol(forma, titulo, usaeditor) {
 	if(usaeditor){
 		var accion = contextPath+"/generarDocumentoSinCaso.do?";
 		accion += "&formaId="+ forma;
 		accion += "&numeroUnicoExpediente="+ numeroExpediente;
 		openNewIframeWindow("iframewindowGenerarDocumento", accion, titulo, 1140, 400, 200, 50);
 	}else{
		generarDocumentoDirecto(forma, '', '', actNumeroExpedienteSt);
 	}
}

function actuacionAdjuntarDocumento(tipo, forma){
	var accion = contextPath+"/adjuntarDocumento.do?";
	accion += "tipo="+tipo;
	accion += "&esconderArbol=0";
	if(!isEmpty(forma)){
		accion += "&formaId="+ forma;
	}
	accion += "&idExpediente="+actExpedienteId;
	accion += "&numeroExpedienteCadena="+ actNumeroExpedienteSt;
	openNewIframeWindow("iframewindowAdjuntarDocumento", accion, "Adjuntar documento", 300, 200, 650, 50);
}

function cerrarVentanaAdjuntarDocumento(){
	var pantalla ="iframewindowAdjuntarDocumento";
	$.closeWindow(pantalla);
}

function actuacionEnviarSolicitudCarpetaInvestigacion(){
	var cofirmacion = confirm("¿Confirma la solicitud de carpeta de investigación para el expediente ?");
	if(cofirmacion){
		var accion = contextPath+"/solictarCarpetaInvestigacion.do";
		var  params = "numeroExpedienteId="+actNumeroExpedienteId;
		peticionSincronaAjaxXML(accion, params, parseSolicitudCarpetaInvestigacionXMLResponse);
	}
}

function parseSolicitudCarpetaInvestigacionXMLResponse(xml){
	var docum = $(xml).find("body").find("solicitud").find("documentoId").text();
	var folio = $(xml).find("body").find("solicitud").find("folioSolicitud").text();
	var msg = $(xml).find("body").find("string").text();
	if(docum != undefined && docum != null && docum != ""){
		cofirmacion = confirm("Se envio la Solicitud de Carpeta de Investigación con Folio "+folio+"\n ¿Desea mostrar el documento?");
		if(confirmacion){
			var forma = 13; //Formas.SOLICITUD ;
			var tipoD = 82; //TipoDocumento.SOLICITUD;
			generarDocumentoDirecto(forma, docum, tipoD);
		}
	}else{
		if(msg == "Error"){
			alert("Se produjo un error en el envio favor de comunicar a su administrador...");
		}else{
			alert(msg);
		}
	}	
}

function actuacionEnviarSolicitudServicioPericial(){
	var accion = contextPath+"/solicitarServicioPericial.do";
	accion += "?numeroExpedienteId="+actNumeroExpedienteId;
	accion += "&numeroExpediente="+ actNumeroExpedienteSt;
	openNewIframeWindow("iframewindowVisorSolicitarServicioPericial", accion, "Solicitar Servicio Pericial", 850, 380, 200, 50);
}

function actuacionEnviarSolicitudAudiencia(){
	var accion = contextPath+"/solicitarAudiencia.jsp?";
	accion += "numeroExpediente=" + actNumeroExpedienteSt;
	accion += "&idExpedienteSoli="+ actExpedienteId;
	accion += "&idNumeroExpediente="+ actNumeroExpedienteId;
	openNewIframeWindow("iframewindowSolicitarAudiencia", accion, "Solicitud de Audiencia", 750, 570, 20, 20);
}

function enviarSolicitudTranscripcionAudioyVideoAudienca(){
	var accion =contextPath+"/solicitarTranscripcionEnPG.do?";
	accion += "idNumeroExpediente="+actNumeroExpedienteId;
	accion +="&nombreSolicitante="+nombreFuncionario;
	accion +="&apaterSolicitante="+apaterFuncionario;
	accion +="&amaterSolicitante="+amaterFuncionario;
	openNewIframeWindow("iframewindowSolicitudTranscripcion", accion, "Solicitud de Transcripción", 812, 454, 253, 113);
}


function cerrarVentanaSolicitarServicioPericial(){
//	cerrarVentana("iframewindowVisorSolicitarServicioPericial")
}

function cargarComboCambiarEtapa(){
	var accion = contextPath+'/consultarCatalogoEtapaExpediente.do';
	peticionSincronaAjaxXML(accion, "", parseEtapasXMLResponse);
}

function parseEtapasXMLResponse(xml){
	$('#cmbEtapaExpediente').empty();
	$('#cmbEtapaExpediente').append('<option value="-1">- Seleccione -</option>');
	$(xml).find('etapaExpediente').each(function(){
		var tip = parseInt($(this).find('clave').text());
		switch(actEtapaExpediente){
			case 2048: //EtapasExpediente.INTEGRACION
				if(tip == 2049 || tip == 2050 ){
					$('#cmbEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				}
				break;
			case 2049://EtapasExpediente.TECNICA
				$("#teoriaDelCaso").show();
				if(tip == 2051){
					$('#cmbEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				}
				break;
			case 2050://EtapasExpediente.CONCILIACION_Y_MEDIACION
				if(tip == 2049){
					$('#cmbEtapaExpediente').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		  		}
				break;
		}
	});
}

function cambiarEtapaExpediente(){
	$('#etapaActual').val($("#etapaExpediente").val());  
	$("#divCambiarEtapa").dialog("open");
	$("#divCambiarEtapa").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Cambiar etapa del expediente', 
	  	dialogClass: 'alert',
	  	position: [312,40],
	  	width: 400,
	  	height: 250,
	  	maxWidth: 1000,
	  	buttons:{"Cambiar":function() {
		  		confirmar=confirm("¿Se cambiará la etapa actual del expediente, ¿está seguro?");
		  		if (confirmar){
			   		var param = "numeroExpedienteId="+actNumeroExpedienteId;
			   		param += "&etapaId="+$('#cmbEtapaExpediente').val();
			   		var accion = contextPath+'/cambiarEtapaDelExpediente.do';
			   		peticionSincronaAjaxXML(accion, param, parseActuacionesXMLResponse);
			   		$(this).dialog("close");
			   		parent.cerrarEtapa();
			  	}
			},
	  		"Cancelar":function() {
		  		confirmar=confirm("¿Realmente desea salir?");
		  		if (confirmar){
			  		  		$(this).dialog("close");
			  	}else{}
		  	}
	  	}
	});	  	
}	

//Abre una nueva ventana de abre Teoria
function abreTeoria(){
	var accion = contextPath+'/abreTeoriaDelCaso.do?';
	accion += 'idExpediente='+actExpedienteId+'&numeroExpediente='+actNumeroExpedienteSt;
	openNewIframeWindow("iframewindowAbrirteoria", accion, "Teoria del caso", 1100, 530, 75, 30);	
}


function generarDoctoPrueba(){
	abreVentanaDocumento(29);
}

function abreVentanaDocumento(forma){
	if(forma != ""){		
		var accion = contextPath+'/colocarExpedienteParaDefensaTecnica.do';
		var params = 'numExpediente=' + actNumeroExpedienteSt;
		peticionSincronaAjaxXML(accion, params, parseVentanDocumentoXMLResponse);
		
		accion = contextPath+'/generarDocumentoSinCaso.do?';
		accion += "formaId="+forma;
		accion += "&numeroUnicoExpediente="+actNumeroExpedienteSt;
		openNewIframeWindow("iframewindowGenerarDocumentoDefensaTecnica", accion, "Generar Documento", 1140, 400, 200, 50);	
	}
}

function parseVentanDocumentoXMLResponse(xml){
	var errorCode;
	errorCode=$(xml).find('response').find('code').text();				
	if(parseInt(errorCode)==0){	
		var exp = $(xml).find('numeroExpediente').first().text();
	}
	else{
		//Mostrar mensaje de error
	}
}

function relacionarExpedienteACaso(){
	accion = contextPath+'/RelacionarExpedienteACaso.do?';
	accion += "numeroExpedienteId="+actNumeroExpedienteId;
	openNewIframeWindow("iframeRelacionarExpedienteACaso", accion, "Relacionar expediente "+actNumeroExpedienteSt+" a caso", 1050, 600, 200, 50);	
}

function cargarComboSituacionDefendido(){
	var accion = contextPath+'/consultarCatalogoSituacionJuridica.do';
	peticionSincronaAjaxXML(accion, "", parseSituacionDefendidoXMLResponse);
}

function parseSituacionDefendidoXMLResponse(xml){
	 $('#cmbSituacionDefendido').empty();
	 $('#cmbSituacionDefendido').append('<option value="-1">- Seleccione -</option>');
	 
	 var situacionJuridicaId = 0;
	 
	 $(xml).find('situacionJuridica').each(function(){
		 situacionJuridicaId = $(this).find('clave').text();

		 if(situacionJuridicaId == idImputado || situacionJuridicaId == idSentenciado ||
				 situacionJuridicaId == idDetenido || situacionJuridicaId == idInvestigado ||
				 situacionJuridicaId == idAcusado){
			 $('#cmbSituacionDefendido').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		 }
	 });	

	
		
	
}

function cambiarSituacionDefendido(){
	$('#situacionActual').val(actSituacionDefendido);  
	$("#divCambiarSituacionDefendido").dialog("open");
	$("#divCambiarSituacionDefendido").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Cambiar situación jurídica del defendido', 
	  	dialogClass: 'alert',
	  	position: [312,40],
	  	width: 400,
	  	height: 250,
	  	maxWidth: 1000,
	  	buttons:{"Cambiar":function() {
		  		confirmar=confirm("¿Se cambiará la situación actual del defendido, ¿está seguro?");
		  		if (confirmar){
			   		var param = "involucradoId="+actInvolucradosIds["DEFENDIDO"];
			   		param += "&situacionJuridicaId="+$('#cmbSituacionDefendido option:selected').val();
			   		var accion = contextPath+'/cambiarSituacionDelDefendido.do';
			   		peticionSincronaAjaxXML(accion, param, parseActuacionesXMLResponse);
			   		var nuevaSituacionJuridica = $("#cmbSituacionDefendido option:selected").html();		   		
			   		$("#situacionActual").val(nuevaSituacionJuridica);
			   		actSituacionDefendido = nuevaSituacionJuridica;
			   		$("#cmbSituacionDefendido").val("-1");
			   		
			   		$(this).dialog("close");
			  	}
			},
	  		"Cancelar":function() {
		  		confirmar=confirm("¿Realmente desea cancelar la operación?");
		  		if (confirmar){
			  		  		$(this).dialog("close");
			  	}else{}
		  	}
	  	}
	});	  	
}	

/*
*Funcion que abre el visor de medidas cautelares
*(Por el momento solo paso el numero de causa)
*ocultarAdd  "true" para ocultar u otro valor para no ocultar 
*/
function mostrarVentanaInvolucradosCausa(numeroCausa, ocultarAdd){
	customVentana("iframewindowVisorMedidasCautelares", "Visor de Medidas Cautelares", "/visorMedidaCautelar.do", "?numeroCausa=" + numeroCausa +"&ocultarAdd="+ocultarAdd);
}