/**
 * 
 */

function inicilizarTabDocumentos(idNumeroExpediente){
	documentos(idNumeroExpediente);
}

function documentos(idNumeroExpediente){
	var parametros = "tipo=3";
	parametros += "&idExpedienteop="+idNumeroExpediente;
	var gridName = "documentosExpediente"; 
	var idGridTable = "gridDocumentosExpediente";
	var idPagerDiv = "pagerDocumentosExpediente";
	var liga = contextPath+'/consultarDocumentosDefensoria.do?'+parametros; 
	
	var headers = ['Tipo de documento', 'Fecha', 'Nombre de Documento', 
	               'Nombre de la actividad', 'Fecha de la actividad', 'Area del responsable']; 
	
	var columnas =[ 
	                {name:'tipo',		index:'2101', width:155, align:"center"}, 
		           	{name:'fecha',		index:'2102', width: 90, align:"center"},
					{name:'nDocumento',	index:'2103', width:255, align:"center"},
					{name:'nActividad',	index:'2104', width:400, align:"center"},
					{name:'fActividad',	index:'2105', width:170, align:"center"},							
					{name:'area',		index:'2106', width:200, align:"center"}
				  ];
	crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 15, undefined, abrirDocumento);
}	

function actualizarTotalDocumentos(){
	var size = jQuery("#gridDelitosAgraviados").jqGrid('getGridParam','selrow');
	$("#idTotalDocumentos").val();
}
