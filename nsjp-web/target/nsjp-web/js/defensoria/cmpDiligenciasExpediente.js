/**
 * Este archivo contiene las funciones que le otorgan su funcionalidad al contenido
 * del archivo actuaciones.jsp, el mismo que puede ser reutilizado sin mayor parametrizacion
 * que pasar a la funcion de inicializacion el numero del expediente y definir la variable contextPath
 */

var dilExpedienteId = 0;

function inicilizarTabDiligencias(expedienteId){
	
	dilExpedienteId = expedienteId;
	
	cargaDiligencias();
	$("#cbxTipoDiligencias").change(seleccionDiligencia);
	$('#btnRegistrarDiligencia').click(registraDiligencia);
	$("#fechaDiligencia").datepicker({
		dateFormat: 'dd/mm/yy',
		yearRange: '1900:2100',
		changeMonth: true,
		changeYear: true,
		showOn: "button",
		buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
		buttonImageOnly: true			
	});
	consultarDiligencias();
	
}

/**
 * Funcion que carga el catalogo de Diligencias en el combo cbxDiligencias, 
 * 
 */
function cargaDiligencias() {
	var accion = contextPath+'/consultarCatalogoTipoDiligencia.do';
	peticionSincronaAjaxXML(accion,"", parseDiligenciasXMLResponse);
}

/**
 * Funcion que transforma el xml para pintar los datos en el combo de diligencias
 * @param xml
 */

function parseDiligenciasXMLResponse(xml){
	$(xml).find('tipoDiligencia').each(function(){
		$('#cbxTipoDiligencias').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
	});
}

/**
 * Al seleccionar una diligencia habilita el boton de registrar
 * 
 */
function seleccionDiligencia(){
	
	var selected = $("#cbxTipoDiligencias option:selected");
		
	if(selected.val()!=0){
		$('#btnRegistrarDiligencia').removeAttr('disabled','disabled');
	}		
}

/**
 * registra una diligencia y abre la ventana para ingresar narrativa 
 */

function registraDiligencia(){
	
	var selected = $("#cbxTipoDiligencias option:selected");
	var params = 'tipoDiligencia='+ selected.val();
		params += 'fechaDiligencia=' + $('#fechaDiligencia').val();
		
	var accion = contextPath+'/registrarDiligencia.do';
		
	peticionSincronaAjaxXML(accion,params,abreNarrativaDiligencia);
	abreNarrativaDiligencia();
	
}

/**
 * si se registra la diligencia con exito se abre una ventana para ingresar narrativa
 */

function abreNarrativaDiligencia(){
	
	var selected = $("#cbxTipoDiligencias option:selected");
	var action = contextPath+'/generarDocumentoSinCaso.do?numeroUnicoExpediente='+numeroExpediente;	
			
	openNewIframeWindow("idWindowDiligencia", action,"Diligencia: "+selected.text());	
	
}

/**
 * se consultan las diligencias asociadas al expediente
 */

function consultarDiligencias(){
	//FALTA PASAR LA VARIABLE AL NUMERO DE EXPEXPEDIENTE
	var parametros = "idNumeroExpediente=";
	var gridName = "diligenciasExpediente"; 
	var idGridTable = "gridDiligenciasExpediente";
	var idPagerDiv = "pagerDiligenciasExpediente";
	var liga = contextPath+'/consultarDiligenciasExpediente.do?'+parametros; 
	
	var headers = ['Tipo de diligencia', 'Descripcion', 'Fecha de diligencia' , 'Fecha de registro']; 
	
	var columnas =[ 
	                {name:'tipoDiligencia',		index:'2101', width:180, align:"center"}, 
		           	{name:'descripcion',		index:'2102', width: 180, align:"center"},
					{name:'fechaDiligencia',	index:'2103', width:180, align:"center"},
					{name:'fechaRegistro',	index:'2104', width:180, align:"center"},
					
				  ];
	
	crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 10, undefined, undefined);
	
}

