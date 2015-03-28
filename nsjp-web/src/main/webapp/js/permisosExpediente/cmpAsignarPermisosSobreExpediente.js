/**
 * 
 */

var apseFuncionarioId = 0;
var gridAPSEGenerado = false;

//Función para alertDinamico
function alertDinamico(textoAlert, id){
    $("#divAlertTexto").html(textoAlert);
    $( "#dialog-Alert" ).dialog({
		autoOpen: true,
		resizable: false,
		//height:290,
		//width:300,
		modal: true,
		buttons: {				
			
			"Aceptar": function() {
				$( this ).dialog( "close" );
				$("#divAlertTexto").html("");					
			}				
		}
	});    
 }

function initAsignarPermisosSobreExpediente(idFuncionario){
	apseFuncionarioId = idFuncionario;
	crearGridSubordinados();
	crearGridExpedientesPropios();
	toggleFormaAsignarPermisos();
}

function crearGridExpedientesPropios(){
	var gridName = "expedientesPropios";
	var parametros = "claveFuncioanrio="+apseFuncionarioId; 
	var liga = contextPath+'/CargarGridExpedientesPropios.do?'+parametros; 

	var idGridTable = "gridExpedientesPropios";
	var idPagerDiv = "pagerExpedientesPropios";
	
	var headers = ['Expediente', 'Fecha Apertura', 'Denunciante', 
	               'Delito Principal', 'Origen', 'Estatus']; 
	
	var columnas =[ 
	                {name:'expediente',	index:'2101', width:155, align:"center"}, 
		           	{name:'fechaApert',	index:'2102', width: 90, align:"center"},
					{name:'denunciate',	index:'2103', width:255, align:"center"},
					{name:'delitoPrin',	index:'2104', width:400, align:"center"},
					{name:'cmp_origen',	index:'2105', width:170, align:"center"},							
					{name:'cmpEstatus',	index:'2106', width:200, align:"center"}
				  ];
	crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 6, toggleFormaAsignarPermisos);
}

function toggleFormaAsignarPermisos(){
	$("#filaFormaAsignarPermisos").show();
	$("#filaFormaModificarPermisos").hide();
	document.formaAgregar.reset();
	jQuery("#gridExpedientesFuncionario").jqGrid('resetSelection');
	
}

function crearGridSubordinados(){
	var gridName = "subordinados"; 
	var parametros = "claveFuncionario="+apseFuncionarioId;
	var liga = contextPath+'/CargarGridSubordinados.do?'+parametros; 
	var idGridTable = "gridSubordinados";
	var idPagerDiv = "pagerSubordinados";
	var headers = ['Nombre del Funcionario', 'Area']; 
	
	var columnas =[ 
	                {name:'nombre',		index:'2101', width:155, align:"center"}, 
					{name:'area',		index:'2106', width:200, align:"center"}
				  ];
	crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 6, crearGridExpedientesFuncionarioClon);
}

function crearGridExpedientesFuncionarioClon()
{
	var rowd = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	if (rowd) {
		//alert(rowd);
		var gridName = "expedientesFuncionario";
		var parametros = "claveFuncionario="+rowd;
		var liga = contextPath+'/CargarGridExpedientesFuncionario.do?'+parametros;
		var idGridTable = "gridExpedientesFuncionario";
		var idPagerDiv = "pagerExpedientesFuncionario"; 

		if(gridAPSEGenerado){
			jQuery("#"+idGridTable).jqGrid('setGridParam', {url:liga, datatype: "xml" });
			$("#"+idGridTable).trigger("reloadGrid");
		}else{		
			var headers = ['Expediente', 'Fecha de vencimiento', 'Lectura', 'Escritua']; 
			
			var columnas = [ 
			                {name:'nExpediente',	index:'2102', width:150, align:"center"}, 
				           	{name:'vencimiento',	index:'2103', width:150, align:"center"},
							{name:'permiso_Lec',	index:'2104', width: 75, align:"center"},
							{name:'permiso_Esc',	index:'2105', width: 75, align:"center"}
						   ];
			crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 6, toggleFormaModificarPermisos);
			gridAPSEGenerado = true;
		}
	}
	else
	{
		alertDinamico("Favor de seleccionar un renglón.");
	}
}

function crearGridExpedientesFuncionario(idRow){
	var gridName = "expedientesFuncionario";
	var parametros = "claveFuncionario="+idRow;
	var liga = contextPath+'/CargarGridExpedientesFuncionario.do?'+parametros;
	var idGridTable = "gridExpedientesFuncionario";
	var idPagerDiv = "pagerExpedientesFuncionario"; 

	if(gridAPSEGenerado){
		jQuery("#"+idGridTable).jqGrid('setGridParam', {url:liga, datatype: "xml" });
		$("#"+idGridTable).trigger("reloadGrid");
	}else{		
		var headers = ['Expediente', 'Fecha de vencimiento', 'Lectura', 'Escritua']; 
		
		var columnas = [ 
		                {name:'nExpediente',	index:'2102', width:150, align:"center"}, 
			           	{name:'vencimiento',	index:'2103', width:150, align:"center"},
						{name:'permiso_Lec',	index:'2104', width: 75, align:"center"},
						{name:'permiso_Esc',	index:'2105', width: 75, align:"center"}
					   ];
		crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, 6, toggleFormaModificarPermisos);
		gridAPSEGenerado = true;
	}
}

function toggleFormaModificarPermisos(){
	$("#filaFormaAsignarPermisos").hide();
	$("#filaFormaModificarPermisos").show();
	document.formaModificar.reset();
	jQuery("#gridExpedientesPropios").jqGrid('resetSelection');
	
	var idSubordinado = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	var idNExpediente = jQuery("#gridExpedientesFuncionario").jqGrid('getGridParam','selrow');
	if(!isEmpty(idSubordinado) && !isEmpty(idNExpediente)){
		var expediente = jQuery("#gridExpedientesFuncionario").jqGrid('getRowData',idNExpediente);
		if(expediente.permiso_Esc=="SI"){
			$("#modEscritura").attr("checked","true");
		}else{
			$("#modEscritura").attr("checked","false");
		}		
		$("#modFechaVencimiento").val(expediente.vencimiento);
		
	}
	
}

function asignarPermisoSobreExpedientes(){
	var idSubordinado = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	var idNExpediente = jQuery("#gridExpedientesPropios").jqGrid('getGridParam','selrow');
	if(!isEmpty(idSubordinado) && !isEmpty(idNExpediente)){
		var param = "";
		param += "claveFuncionario="+idSubordinado;
		param += "&numeroExpediente="+idNExpediente;
		param += "&escritura="+$("#escritura").attr("checked");
		if(!isEmpty($("#fechaVencimiento").val())){
			param += '&fechaVencimiento=' + $("#fechaVencimiento").val();
			var accion = contextPath+'/AsignarPermisosSobreExpedinte.do';
			peticionSincronaAjaxXML(accion, param, apseSUCCESS);
		}else{
			alertDinamico("Debe de seleccionar una fecha de vencimiento para el permiso.");
		}
	}else{
		alertDinamico("Se debe seleccionar tanto un funcionario como un expediente propio para esta operación.");
	}
}

function modificarPermisoSobreExpedientes(){
	var idSubordinado = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	var idNExpediente = jQuery("#gridExpedientesFuncionario").jqGrid('getGridParam','selrow');
	if(!isEmpty(idSubordinado) && !isEmpty(idNExpediente)){
		var param = "";
		param += "claveFuncionario="+idSubordinado;
		param += "&numeroExpediente="+idNExpediente;
		param += "&escritura="+$("#modEscritura").attr("checked");
		if(!isEmpty($("#modFechaVencimiento").val())){
			param += '&fechaVencimiento=' + $("#modFechaVencimiento").val();
			var accion = contextPath+'/AsignarPermisosSobreExpedinte.do';
			peticionSincronaAjaxXML(accion, param, apseSUCCESS);
		}else{
			alertDinamico("Debe de seleccionar una fecha de vencimiento para el permiso.");
		}
	}else{
		alertDinamico("Se debe seleccionar tanto un funcionario como un expediente asignado para esta operación.");
	}
}

function eliminarPermisoSobreExpedientes(){
	var idSubordinado = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	var idNExpediente = jQuery("#gridExpedientesFuncionario").jqGrid('getGridParam','selrow');
	if(!isEmpty(idSubordinado) && !isEmpty(idNExpediente)){
		var param = "";
		param += "claveFuncionario="+idSubordinado;
		param += "&numeroExpediente="+idNExpediente;
		var accion = contextPath+'/EliminarPermisosSobreExpedinte.do';
		peticionSincronaAjaxXML(accion, param, apseSUCCESS);
	}else{
		alertDinamico("Se debe seleccionar tanto un funcionario como un expediente asignado para esta operación.");
	}
}

function apseSUCCESS(xml){
	alert($(xml).find("body").text());
	document.formaAgregar.reset();
	document.formaModificar.reset();
	var idSubordinado = jQuery("#gridSubordinados").jqGrid('getGridParam','selrow');
	crearGridExpedientesFuncionario(idSubordinado);
}