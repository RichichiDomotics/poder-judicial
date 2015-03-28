var idWPR = 0;
var idWTS = 0;
var idWVC = 0;
var invExpedienteId = 0;
var invEtapaExpediente = 0;
var invNumeroExpedienteId = 0;
var invNumeroExpedienteSt = "";
var	invInvolucradosIds = [];
var muestraDetenido=0;

var firstClickSolicitante = true;
var firstClickQuienDetuvo = true;


function inicilizarTabInvolucrados(idExpediente, idNumeroExpediente, numeroExpediente, etapaId, estatusExpedienteId, involucrados){
	invExpedienteId = idExpediente;
	invEtapaExpediente = etapaId;
	invNumeroExpedienteId = idNumeroExpediente;
	invNumeroExpedienteSt = numeroExpediente;
	invInvolucradosIds = involucrados;
	invEstatusExpedienteId = estatusExpedienteId;
	
	$("#tabsInvolucrados").tabs();
	$("#tabsInvolucrados").tabs('selected', 1);
	
	//$("#nuevoProbResponsable").click(manejarProbableResponsable);
	//$("#nuevoTestigo").click(creaNuevoTestigo);
	bloqueaTabsInvolucrados();
	cargaTabEtapaActual(invEtapaExpediente);

	cargarInvolucradosExpediente();
}

function cargaTabEtapaActual(etapaId){
	if(etapaId == 2048){
		loadEtapaInvolucradoIntegracion();
	}else if(etapaId == 2049){
		loadEtapaInvolucradoTecnica();	
	}else if(etapaId == 2050){
		loadEtapaInvolucradoRestaurativa();
	}else if(etapaId == 2051){
		loadEtapaInvolucradoEjecucion();
	}
}

function cargarTabInvolucradosOnClick(){
	cargarParaQuienSolicita();
}

function cargarParaQuienSolicita(){
	var accion=	contextPath+"/IngresarProbResponsable.do?" +
	"idProbableResponsable=" + invInvolucradosIds["DEFENDIDO"]+
	"&calidadInv=DEFENDIDO" +
	"&numeroExpediente="+invNumeroExpedienteSt;
	$("#iframe_involucradoDefendido").attr("src",accion);
}

function cargarSolicitante(){
	
	if(firstClickSolicitante == true){
		var accion=	contextPath+"/IngresarTestigo.do?" +
		"idTestigo=" + invInvolucradosIds["SOLICITANTE"]+
		"&calidadInv=SOLICITANTE" +
		"&numeroExpediente="+invNumeroExpedienteSt+
		"&solicitante=true";
		$("#iframe_InvolucrdSolicitante").attr("src",accion);
		
		firstClickSolicitante =false;
	}
}

function cargarCedulaDeIdentificacion(){
	var accion=	contextPath+"/IngresarProbResponsable.do?" +
	"idProbableResponsable=" + invInvolucradosIds["DEFENDIDO"]+
	"&calidadInv=DEFENDIDO" +
	"&numeroExpediente="+invNumeroExpedienteSt;
	$("#iframe_CedulaIdentificacion").attr("src",accion);
}

function cargarInvitado(){
	var accion=	contextPath+"/IngresarProbResponsable.do?" +
	"idProbableResponsable=" + invInvolucradosIds["DEFENDIDO"]+
	"&calidadInv=DEFENDIDO" +
	"&numeroExpediente="+invNumeroExpedienteSt;
	$("#iframe_InvDefendidoInvitado").attr("src",accion);
}

function cargarSolicitanteJusticiaRestaurativa(){
	var accion=	contextPath+"/IngresarProbResponsable.do?" +
	"idProbableResponsable=" + invInvolucradosIds["DEFENDIDO"]+
	"&calidadInv=DEFENDIDO" +
	"&numeroExpediente="+invNumeroExpedienteSt;
	$("#iframe_SolicitanteJusticiaR").attr("src",accion);
}

function cargarQuienDetuvo(){
	
	if(firstClickQuienDetuvo == true){
		var accion=	contextPath+"/IngresarQuienDetuvo.do?" +
		"elemento=" + (involucrados["QUIENDETUVO"] == undefined ?  0: involucrados["QUIENDETUVO"])+
		"&numeroExpediente="+invNumeroExpedienteSt;
		$("#iframeInvolucrdQuienDetuvo").attr("src",accion);
		firstClickQuienDetuvo = false;
	}
}


//Crea una nueva ventana de testigo
function creaNuevoTestigo() {
	
	var idWindow = "iframewindowIngresarTestigo"+(idWTS++);
	var action = contextPath+"/IngresarTestigo.do?";
	action += "numeroExpediente="+invNumeroExpedienteSt;
	if(!isEmpty(id)){
		action += "&idProbableResponsable="+id;
	}
	openNewIframeWindow(idWindow, action, "Ingresar Testigo", 1050, 620, 250, 150);
}

function creaNuevaVictima(){
	
	var idWindow = "iframewindowIngresarVictima"+(idWVC++);
	var action = contextPath+"/IngresarVictima.do?";
	action += "numeroExpediente="+invNumeroExpedienteSt;
	if(!isEmpty(id)){
		action += "&idProbableResponsable="+id;
	}
	openNewIframeWindow(idWindow, action, "Ingresar Victima", 1050, 620, 250, 150);
}

function creaNuevoProbResponsable() {
	
	var idWindow = "iframewindowModificarProbResponsable"+(idWPR++);
	var action = contextPath+"/IngresarProbResponsable.do?";
	action += "numeroExpediente="+invNumeroExpedienteSt;
	openNewIframeWindow(idWindow, action, "Ingresar Probable Responsable", 1050, 620, 250, 150);
	
	//accion += '&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+invNumeroExpedienteSt +'&detenido='+muestraDetenido;		
}

function generaLigaInvolucrado(involucrado, funcion,  anonimo){
	var id =  $(involucrado).find('elementoId').first().text();
	var nombresDemograficos = $(involucrado).find('nombresDemograficoDTO');
	var liga = '<tr id="' +id+ '">';
	liga += '<td class="noSub" style="cursor: pointer;">&nbsp;&nbsp;&nbsp;<a onclick="'+funcion+'(' + id + ');">';
	try{
		nombre = $(nombresDemograficos).find('nombreDemografico').first();
	}catch(e){
		alert(e);
	}
	$(nombresDemograficos).find('nombreDemografico').each(function(){
		if($(this).find('esVerdadero')){
			nombre = $(this);
		}
	});
	
	if($(nombre).find('nombre').text()=='null'){
		liga += amonimo;
	}else{
		liga += $(nombre).find('nombre').text()+" "+$(nombre).find('apellidoPaterno').text()+" "+$(nombre).find('apellidoMaterno').text();
  	}		
  	liga += '</a></td></tr>';
  	return liga;
}

//consulta y carga los involucrados 
function cargarInvolucradosExpediente(){
	accion = contextPath+'/consultaDetalleExpedienteDefensoria.do',
	parametros = 'numeroExpedienteId=' + invNumeroExpedienteId,
	parametros+= '&numeroExpediente='+invNumeroExpedienteSt,
	peticionSincronaAjaxXML(accion, parametros, parserCargarInvolucradosExpediente);
}

function parserCargarInvolucradosExpediente(xml){
    $(xml).find('involucradosDTO').find('involucradoDTO').each(function(){
  	  var calidad = parseInt($(this).find('calidadDTO').first().find('valorIdCalidad').find('idCampo').text());

  	  switch(calidad){
  	  	case 215:
	  			$('#tblDenunciante').append(generaLigaInvolucrado($(this), 'modificaDenunciante', 'Desconocido'));
  	  		var victima = $(this).find('esVictima').text();
  	  		if(victima == 'true'){
  	  			$('#tblVictima').append(generaLigaInvolucrado($(this), 'modificaDenunciante', 'Desconocido'));
  	  		}
  	  		break;
  	  	case 214:
	    	  	$('#tblVictima').append(generaLigaInvolucrado($(this), 'modificaVictima', 'Desconocido'));
	    	  	break;
  	  	case 213://<%= Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId() %>:
  	  	case 222://<%= Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() %>:
	    	  	$('#tblProbableResponsable').append(generaLigaInvolucrado($(this), 'modificaProbResponsable', 'Desconocido'));
	    	  	break;
  	  	case 216://<%= Calidades.TESTIGO.getValorId() %>:
	    	  	$('#tblTestigo').append(generaLigaInvolucrado($(this), 'modificaTestigo', 'Desconocido'));
	    	  	break;
  	  	case 220://<%= Calidades.TESTIGO.getValorId() %>:
	    	  	$('#tblTraductor').append(generaLigaInvolucrado($(this), 'modificaTraductor', 'Desconocido'));
	    	  	break;
	    	  	
  	  }
	      
    });
    $(xml).find('expediente').find('etapasPasadas').each(function(){
    	if($(this).find('etapasExpediente').first().text() != null && $(this).find('etapasExpediente').first().text() != ""){
    		var etapa = $(this).find('etapasExpediente').first().text();
        	if(etapa == 'INTEGRACION'){
    			loadEtapaInvolucradoIntegracion();
    		}else if(etapa == 'TECNICA'){
    			loadEtapaInvolucradoTecnica();	
    		}else if(etapa == 'CONCILIACION_Y_MEDIACION'){
    			loadEtapaInvolucradoRestaurativa();
    		}else if(etapa == 'EJECUCION'){
    			loadEtapaInvolucradoEjecucion();
    		}
    	}
    });
}


function loadEtapaInvolucradoEjecucion(){
	$("#tabInvolucradoDefendido").show();
	$("#tabInvolucrdSolicitante").show();
	$("#tabCedulaIdentificacion").show();
	$("#tabInvDefendidoInvitado").show();
	$("#tabSolicitanteJusticiaR").show();
	$("#tabInvolucrdDenunciante").show();
	$("#tab_Involucrado_Victima").show();
	$("#tab_ProbableResponsable").show();
	$("#tab_Involucrado_Testigo").show();
	$("#tabInvolucradoTraductor").show();
	$("#tabInvolucrdQuienDetuvo").show();
}

function loadEtapaInvolucradoRestaurativa(){
	$("#tabInvolucradoDefendido").show();
	$("#tabInvolucrdSolicitante").show();
	$("#tabCedulaIdentificacion").show();
	$("#tabInvDefendidoInvitado").show();
	$("#tabSolicitanteJusticiaR").show();
}
	
function loadEtapaInvolucradoTecnica(){
	$("#tabInvolucradoDefendido").show();
	$("#tabInvolucrdSolicitante").show();
	$("#tabCedulaIdentificacion").show();
	$("#tabInvDefendidoInvitado").show();
	$("#tabSolicitanteJusticiaR").show();
	//tabs de tecnica con carpeta
	if(invEstatusExpedienteId == 2487){
		$("#tabInvolucrdDenunciante").show();
		$("#tab_Involucrado_Victima").show();
		$("#tab_ProbableResponsable").show();
		$("#tab_Involucrado_Testigo").show();
		$("#tabInvolucradoTraductor").show();
		$("#tabInvolucrdQuienDetuvo").show();
	}
}
	
function loadEtapaInvolucradoIntegracion(){
	$("#tabInvolucradoDefendido").show();
	$("#tabInvolucrdSolicitante").show();
	$("#tabCedulaIdentificacion").show();
	$("#tabInvDefendidoInvitado").show();
	$("#tabSolicitanteJusticiaR").show();
	$("#tabInvolucrdDenunciante").hide();
	$("#tab_Involucrado_Victima").hide();
	$("#tab_ProbableResponsable").hide();
	$("#tab_Involucrado_Testigo").hide();
	$("#tabInvolucradoTraductor").hide();
	$("#tabInvolucrdQuienDetuvo").hide();
}

function bloqueaTabsInvolucrados(){
	$("#tabInvolucradoDefendido").hide();
	$("#tabInvolucrdSolicitante").hide();
	$("#tabCedulaIdentificacion").hide();
	$("#tabInvDefendidoInvitado").hide();
	$("#tabSolicitanteJusticiaR").hide();
	$("#tabInvolucrdDenunciante").hide();
	$("#tab_Involucrado_Victima").hide();
	$("#tab_ProbableResponsable").hide();
	$("#tab_Involucrado_Testigo").hide();
	$("#tabInvolucradoTraductor").hide();
	$("#tabInvolucrdQuienDetuvo").hide();
}

//Abre una nueva ventana de probable responsable
function modificaProbResponsable(id) {
	var idWindow = "iframewindowModificarProbResponsable"+(idWPR++);
	var accion = contextPath+'/IngresarProbResponsable.do?';
	accion += 'idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+invNumeroExpedienteSt +'&detenido='+muestraDetenido;
	openNewIframeWindow(idWindow, accion, "Probable Responsable", 1100, 530, 75, 30);	
}

//Abre una nueva ventana de Denunciante
function modificaDenunciante(id){
	var accion = contextPath+'/IngresarDenunciante.do?';
	accion += 'idDenunciante='+id +'&numeroExpediente='+invNumeroExpedienteSt;
	openNewIframeWindow("iframewindowIngresarDenunciante", accion, "Denunciante", 1100, 530, 75, 30);	
}

//Abre una nueva ventana de crear una nuev victima
function modificaVictima(id) {
	var idWindow = "iframewindowIngresarVictima"+(idWVC++);
	var accion = contextPath+'/IngresarVictima.do?';
	accion += 'idVictima='+id +'&numeroExpediente='+invNumeroExpedienteSt;
	openNewIframeWindow(idWindow, accion, "Víctima", 1100, 530, 75, 30);	
}

//Crea una nueva ventana de testigo
function modificaTestigo(id) {
	var idWindow = "iframewindowIngresarTestigo"+(idWTS++);
	var accion = contextPath+'/IngresarTestigo.do?';
	accion += 'idTestigo='+id+'&numeroExpediente='+invNumeroExpedienteSt;
	openNewIframeWindow(idWindow, accion, "Testigo", 1100, 530, 75, 30);	
}

//Abre una nueva ventana de ingresar traductor
function modificaTraductor(id) {
	var accion = contextPath+'/IngresarTraductor.do?';
	accion += 'idTraductor='+id;
	openNewIframeWindow("iframewindowTraductor", accion, "Traductor", 1100, 530, 75, 30);	
}	
