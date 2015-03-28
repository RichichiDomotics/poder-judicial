function crearGrid(gridName, idGridTable, liga, headers, columnas, idPagerDiv, filasVisibles, onSelectRowFunction, ondblClickRowFunction, loadCompleteFunction){
	var filas1 = filasVisibles * 2;
	var filas2 = filasVisibles * 3;
	jQuery('#'+idGridTable).jqGrid({
		id: gridName,
		url:liga, 
		datatype: "xml", 
		colNames:headers, 
		colModel:columnas,
		rowNum:filasVisibles,
		rowList:[filasVisibles,filas1,filas2],
		autowidth: true,
		height:'100%',
		viewrecords: true,
		onSelectRow: onSelectRowFunction,
		loadComplete: loadCompleteFunction,
		ondblClickRow: ondblClickRowFunction,
		pager: jQuery('#'+idPagerDiv)
	}).navGrid('#'+idPagerDiv,{edit:false,add:false,del:false});
	$('#'+idGridTable).trigger("reloadGrid"); 
}

function generarDocumentoDirecto(idForma, idDocumento, tipoDocumento, numeroExpediente){
	var nombre = "generarDocumentoDirectoForm";
	var accion = contextPath+"/GenerarDocumentoDirecto.do";
	var generarDocumentoDirectoForm = crearNuevaForma(nombre,accion);
	createNuevoParametroEnForma(generarDocumentoDirectoForm, "formaId", idForma);
	createNuevoParametroEnForma(generarDocumentoDirectoForm, "documentoId", idDocumento);
	createNuevoParametroEnForma(generarDocumentoDirectoForm, "tipoDocumento", tipoDocumento);
	createNuevoParametroEnForma(generarDocumentoDirectoForm, "numeroUnicoExpediente", numeroExpediente);
	generarDocumentoDirectoForm.submit();
	
}

function abrirArchivoDigital(idArchvoDigital){
	var nombre = "abrirArchivoDigitalForm";
	var accion = contextPath+"/ConsultarContenidoArchivoDigital.do";
	var abrirArchivoDigitalForm = crearNuevaForma(nombre,accion);
	createNuevoParametroEnForma(abrirArchivoDigitalForm, "archivoDigitalId", idArchvoDigital);
	abrirArchivoDigitalForm.submit();
}

function abrirDocumento(idDocumento){
	var nombre = "abrirDocumentoForm";
	var accion = contextPath+"/ConsultarContenidoArchivoDigital.do";
	var abrirDocumentoForm = crearNuevaForma(nombre,accion);
	createNuevoParametroEnForma(abrirDocumentoForm, "documentoId", idDocumento);
	abrirDocumentoForm.submit();
}

function createNuevoParametroEnForma(forma, nombre, value){
	var input = document.getElementsByName("documentoId");
	var newElement;
	if(input.length > 0){
		newElement = input[0];
	}else{
		newElement = document.createElement("INPUT");
		newElement.type = "hidden";
		newElement.name = nombre;
		forma.appendChild(newElement);
	}
	newElement.value = value;
}

//helper function to create the form
function crearNuevaForma(nombre, action, target){
	var formas = document.getElementsByName(nombre);
	var newForm;
	if(formas.length > 0){
		newForm = formas[0];
	}else{
		newForm = document.createElement("FORM");
		document.body.appendChild(newForm);
		newForm.method = "POST";
		newForm.action = action;
		newForm.action = action;
		newForm.name = nombre;
	}
	return newForm;
}

function isEmpty(variable){
	if(variable == undefined || variable == null 
	|| variable == "" || variable.length == 0 ){
		return true;
	}
	return false;
}

function openNewIframeWindow(idWindow, action, titulo, ancho, alto, posX, posY){
	if(isEmpty(ancho)){ ancho = 600;}
	if(isEmpty(alto)) { alto = 400; }
	if(isEmpty(posX)) { posX = 250; }
	if(isEmpty(posY)) { posY = 100; }
	
	$.newWindow({id: idWindow, posx: posX, posy: posY, width: ancho,
				height: alto, statusBar: true, type:"iframe", title:titulo,modal:false
				});
	var iframeString = "<iframe src='"+action+"' width='"+ancho+"' height='"+alto+"' />";
	$.updateWindowContent(idWindow, iframeString);
}

function cerrarIframeWindow(idPantalla){
	$.closeWindow("#"+idPantalla);
}

function crearIframe(idPadre, idIframe, action, callback)
{
	var padre = document.getElementById(idPadre);
    var iframe = document.createElement('iframe');
    iframe.id = idIframe;
    iframe.src = action;
    iframe.width="100%";
    iframe.height="100%";
    $("#"+idIframe).load(callback);
    iframe.className="tabbedFrame";
    padre.appendChild(iframe); 
    
}

/**
 * Ejecuta una peticion ajax sincrona con método post que tendrá como respuesta un xml
 * @param accion	La url qu se va a ejecutar
 * @param parametros	Los datos que se enviarán via post
 * @param successXmlFuncion	La función que se invocará para transformar los datos que se recivan
 * @param errorFuncion La función que se ejecutará en caso de que la petición sea erronea
 */
function peticionSincronaAjaxXML(accion, parametros, successXmlFuncion, errorFuncion){
	$.ajax({
		type: 'POST',
		url: accion,
		data: parametros,
		dataType: 'xml',
		async: false,
		success: successXmlFuncion,
		error: errorFuncion
	});
}
