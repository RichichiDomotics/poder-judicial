/**
 * Realiza una solicitud port al path indicado como parametro, la respuesta
 * la procesa con la "funcionSucces" que debe recibir como parametro un objeto
 * que sera procesado como xml.
 */
// /consultarCasoPorExpediente.do
function ejecutaAction(pathAction, funcionSucces, parametros){
    $.ajax({
        type: 'POST',
        url: CONTEXT_ROOT + pathAction + ".do",
        data: parametros == null ? '': parametros,
        dataType: 'xml',
        async: false,
        success: function(respuesta){
            funcionSucces(respuesta);
        }
    });

}

/**
 * pathAction = "/consultarCatalogoTipoBajaEvidencia"
 * idElemento = "tipoDeBaja"
 * aliasDto = "evidencia" En el action, "evidencia" corresponde a:
 *             converter.alias("evidencia", CatalogoDTO.class);
 */
function cargaCatalogo(pathAction, idElemento, aliasDto){
    ejecutaAction(pathAction, function(respuesta){
        var catalogo = $(respuesta);
        catalogo.find(aliasDto).each(function(){
            var elementoCatalogo = $(this);
            var option = $('<option value="' + elementoCatalogo.find('clave').text() + '">' + elementoCatalogo.find('valor').text() + '</option>');
            $('#' + idElemento).append(option);
        });
    });
}

/**
 * Obtiene el valor de los campos cuya clase sea igual al parametro clase
 * y los acomoda como una cadena campo1=valor1&campo2=valor2... etc.
 */
function obtenParametros(clase){
    var parametrosValor = "";
    var parametros = $("." + clase);
    var distintosDeNull = 0;
    for(var i = 0; i < parametros.length; ++i){
        var texto = parametros[i];
        var value = $(texto).val();
        if (value != null && value != "") {
            if (distintosDeNull > 0) {
                parametrosValor += "&" + texto.id + "=" + $(texto).val();
            }else{
                parametrosValor += "" + texto.id + "=" + $(texto).val();
            }
            distintosDeNull++;
        }
    }
    return parametrosValor;
}

function muestraAlert(idElemento, width, height){
    $("#" + idElemento).dialog({ autoOpen: true,
        modal: true,
        title: 'Atenci\u00f3n',
        dialogClass: 'alert',
        position: [200,30],
        width: width == null ? 412: width,
        height: height == null? 120:height,
        maxWidth: 500,
        buttons:{"Ok":function() {
                $(this).dialog("close");
            }
        }
    });
}

function validaParametrosRequeridos(parametros, requeridos){
    var elementosParametros = parametros.split("&");
    var llaves = new Array();
    var valores = new Array();
    var faltantes = new Array();
    // separamos la cadena de parametros en llaves y valores
    for(var i = 0; i < elementosParametros.length; ++i){
        var elementoParametro = elementosParametros[i];
        var llave = elementoParametro.split("=")[0];

        var valor = elementoParametro.split("=")[1];
        llaves[i] = llave;
        valores[i] = valor;
    }
    for(i in requeridos){
        var requerido = i + "";
        var requeridoEncontrado = false;
        for(var j = 0; j < llaves.length; ++j){
//            alert("requerido = " + requerido + " llave = " + llaves[j] + " no esperado = " + requeridos[i] + " encontrado = " + valores[j]);
//            alert("(requerido == llaves[j]) = " + (requerido == (""+llaves[j])));
//            alert("(valores[j] != requeridos[i]) = " + (valores[j] != requeridos[i]));
            if ((requerido == (""+llaves[j])) && (valores[j]) && (valores[j] != "" + requeridos[i])) {
//                alert("encontrado");
                requeridoEncontrado = true;
                break;
            }
        }
        if (!requeridoEncontrado) {
            faltantes[faltantes.length] = requerido;
        }
    }
    return faltantes;
}

function obtenParametroDeUrl(nombre){
    nombre = nombre.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regexS = "[\\?&]"+nombre+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec( window.location.href );
    if( results == null ){
        return "";
    }else{
        return results[1];
    }
}

function obtenParametrosDeUrlComoCadena(nombreParametro){
    var parametros = obtenParametrosDeUrlComoArreglo(nombreParametro);
    var resultado = "";
    for(var i = 0; i < parametros.length; ++i){
        var parametro = parametros[i];
        if(i == 0){
            resultado += nombreParametro + "=" + parametro;
        }else{
            resultado += "&" + nombreParametro + "=" + parametro;
        }
    }
    return resultado;
}

/**
 * Regresa un arreglo con los valores de los parametros en la url que son
 * igual a nombreParametro.
 * EG.
 * si la url = htt://...?id=1&id=2&nombre=usuario
 * este metodo regresa el arreglo = [1,2]
 */
function obtenParametrosDeUrlComoArreglo(nombreParametro) {
    var url = window.location.href;
    var request = new Array();
    var agregados = 0;
    var pairs = url.substring(url.indexOf('?') + 1).split('&');
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split('=');
        if (pair[0] == nombreParametro) {
            request[agregados++] = decodeURIComponent(pair[1]);
        }
    }
    return request;
}

function llenaCamposEnJspConObjetoXML(camposEnJsp, objetoXml){
    var objetoJquery = $(objetoXml);
    for(var i = 0; i < camposEnJsp.length; ++i){
        var campo = $(camposEnJsp[i]);
        var idCampo = campo.attr("id");
        campo.html(objetoJquery.find("" + idCampo));
    }
}

/**
 * idFrame = "iframewindowSolicitarAudiencia"
 * jsp = "/solicitarAudiencia.jsp"
 * x = 20
 * y = 20
 * width = 740
 * height = 520
 * titulo = "Solicitar Audiencia"
 */
function abreNuevoFrame(idFrame, jsp, x, y, width, height, titulo){
    $.newWindow({
        id:idFrame,
        statusBar: true,
        posx:x,
        posy:y,
        width:width,
        //        height:height,
        title:titulo,
        type:"iframe"
    });
    // height="' + (height) + '"
    $.updateWindowContent(idFrame,'<iframe src="' + CONTEXT_ROOT + jsp + ' "width="'+ (width) +'" height="' + (height) + '" />');
}

function joinComoParametros(parametros, separador){
    var join = "";
    for(var i = 0; i< parametros.length; ++i){
        if(i == 0){
            join += separador + "=" + parametros[i];
        }else{
            join += "&" + separador + "=" + parametros[i];
        }
    }
    return join;
}

function getHoraMinutoActual(fechaActual){
	minutosInc =fechaActual.getMinutes();
	
//	alert(minutosInc);
	if(minutosInc=="0"||minutosInc=="1"||minutosInc=="2"||minutosInc=="3"||minutosInc=="4"||minutosInc=="5"||minutosInc=="6"||minutosInc=="7"||minutosInc=="8"||minutosInc=="9"){
		 return  fechaActual.getHours()+ ":" + "0" +
		    fechaActual.getMinutes()+ " hrs ";
		
	}else{
    return  fechaActual.getHours()+ ":" +
    fechaActual.getMinutes()+ " hrs ";
	}
}

function getMesLetra(date){
    var mes = date.getMonth();
    var mesLetra = "";
    switch (mes) {
        case 0:
            mesLetra = "enero";
            break;
        case 1:
            mesLetra = "febrero";
            break;
        case 2:
            mesLetra = "marzo";
            break;
        case 3:
            mesLetra = "abril";
            break;
        case 4:
            mesLetra = "mayo";
            break;
        case 5:
            mesLetra = "junio";
            break;
        case 6:
            mesLetra = "julio";
            break;
        case 7:
            mesLetra = "agosto";
            break;
        case 8:
            mesLetra = "septiembre";
            break;
        case 9:
            mesLetra = "octubre";
            break;
        case 10:
            mesLetra = "noviembre";
            break;
        case 11:
            mesLetra = "diciembre";
            break;

        default:
            break;
    }
    return mesLetra;

}

/*
 * Funcion que permite solo capturar numeros en los campos de texto
 * Se debe colocar dentro de la accion onkeypressdown del textbox
 * */
function solonumeros(e) {
    var unicode = e.charCode ? e.charCode : e.keyCode;

    // if the key is backspace, tab, or numeric
    if (unicode == 8 || unicode == 9 || (unicode >= 48 && unicode <= 57)) {
        // we allow the key press
        return true;
    }
    else {
        // otherwise we don't
        return false;
    }
}

/*
 *Funcion que recibe una hora en el formato HH:MM
 *y le da formato para setear un campo TimeEntry con el 
 *formato HH:MMAM o HH:MMPM 
 */
function formateaHoraTimeEntryTextBox(horaSinFormato)
{
	var horas=horaSinFormato.substring(0,2);
	var amPM="AM";
	var horaConFormato="";
	if(parseInt(horas)<12)
	{
		horaConFormato=horaSinFormato+"AM";
	}
	else
	{
		horaConFormato=horaSinFormato+"PM";
	}
	return horaConFormato;
}

/*
 * Funcion para setear los campos de fecha y hora que se generan a partir de los plugins de Jquery de DatePicker y TimeEntry
 * fechaHora - cadena que contiene la fecha y hora regresada de la BD en el formato : 2011-09-08 08:00:00.0
 * idCampoFecha - id del campo fecha donde se seteara la fecha una vez que sea formateada correctamente
 * idCampoHora - id del campo hora donde se seteara la fecha una vez que sea formateada correctamente
 */
function seteaFechaHoraEnCamposPlugin(fechaHora,idCampoFecha,idCampoHora)
{
	var datos=fechaHora.split(' ');
	var fechaBien=datos[0].split('-');
	var hora=datos[1].split(".");
	var horaBien=hora[0].split(":");
	$("#"+idCampoFecha).val(fechaBien[2]+"/"+fechaBien[1]+"/"+fechaBien[0]);
	//destruimos el plugin para el campo de hora
	$("#"+idCampoHora).timeEntry('destroy');
	//seteamos la configuracion del campo hora
	$("#"+idCampoHora).timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
	//generamos de nuevo el campo con el plugin de hora
	$("#"+idCampoHora).timeEntry('setTime', formateaHoraTimeEntryTextBox(datos[1].substring(0,5)));
}

/*
*Funcion que simula la funcion TRIM de otros lenguajes 
*
*/
function trim (myString)
{
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}


/*
 * Funcion para regresar la fecha maxima obtenida desde el servidor
 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
 * regresa una cadena con la fecha en el formato : DD/MM/YYYY
 */
function getFechaMaximaServer(fechaCompleta)
{
	var arrFechaHora=fechaCompleta.split(" ");
	var digitosFecha=arrFechaHora[0].split("-");
	return digitosFecha[2]+'/'+digitosFecha[1]+'/'+digitosFecha[0];
}

/*
 * Funcion para regresar la hora maxima obtenida desde el servidor
 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
 * regresa una cadena con la hora en el formato : HH:MMAM o HH:MMPM
 */
function getHoraMaximaServer(fechaCompleta)
{
	var arrFechaHora=fechaCompleta;
	var horaC=arrFechaHora[1].substring(0,5);
	var horaD=horaC.substring(0,2);
	var horaCorrecta="";
	if(parseInt(horaD)<13)
	{	
		horaCorrecta=horaC+'AM';
	}
	else
	{
		horaCorrecta=horaC+'PM';
	}
	return horaCorrecta;
}

function soloLetrasDos(e) 
{
	var ExpReg_cad=/^[aA-zZ]*$/;
	var unicode = e.charCode ? e.charCode : e.keyCode;
	//alert(unicode);
	//alert("charcode:: "+e.charCode);
	//alert("keycode:: "+e.keyCode);
	if (unicode == 241 || unicode == 46 || unicode == 39 || unicode == 32 || unicode == 8 || unicode == 9 || (unicode >= 65 && unicode <= 90) || (unicode >= 97 && unicode <= 122) || unicode == 165) { //todas las letras en mayuscula
       // we allow the key press
       if(unicode == 39 || unicode == 46)
       {
    		if(e.keyCode==0)
    		{
    			return false;
    		}
    		else
    		{
    			return true;
    		}
       }
       else
       {
       return true;
       }
	}
	else 
	{
       // otherwise we don't
       return false;
	}
	return true;
}
    
//validaRangoFechasyHoras(Fecha Inicial,Fecha Final,Hora Inicial, Hora Final)
function validaRangoFechasyHoras(fechaini,fechafin,horaini,horafin){
	
	var fechaIniVal = fechaini;
	var fechaFinVal = fechafin;
	var hIniVal = horaini; 
	var hFinVal= horafin;
	var inicio = fechaIniVal.split("/");
	var fin = fechaFinVal.split("/");
	var hInicio=hIniVal.split(":");
	var hFin=hFinVal.split(":");


//si el anio fin es mayor termina

 if (fin[2] > inicio[2] )   
{   
	 return(true);
}   
else  
{   
//si el anio fin es = al inicial compara los meses

if (fin[2] == inicio[2])   
{    
//comara que el mes final sea mayor al inicial termina
if (fin[1]> inicio[1])   
{   
	return(true);
}   
else  
{    
	//si el mes fin es = al inicial compara los dias
  if (fin[1] == inicio[1])   
  {   
    if (fin[0]> inicio[0])  
        {
    	return(true);
    	}
	 else  {
		 if (fin[0] ==  inicio[0])   
	      {    
	        //comara que el mes final sea mayor al inicial termina
	        if (hFin[0]> hInicio[0])   
	        {   
	        	return(true);
	        }   
		 else  {
   		alert("La hora l\u00edmite debe de ser mayor a la hora de solicitud");
   		return(false);
 	  		 } 	 
	 }
	 }
  }   
  else  {
	  return(false); 


	  alert("La fecha l\u00edmite debe de ser mayor a la fecha de solicitud");
	  }
	    
}   
}   
else  {

 alert("La fecha l\u00edmite debe de ser mayor a la fecha de solicitud");

      return(false);

 }
  
}   
	
	
	
}

//Funcion que valida si los campos estan llenos al enviar y si es valido el rango de fechas 
function validaCamposFecha(fechaini,fechafin) {

	if (fechaini == '' || fechafin == '') {
		customAlert("Debes ingresar tanto la fecha de inicio como la de fin");
		validaFecha = false;
	} else {

		var fechaIniVal = fechaini;
		var fechaFinVal = fechafin;

		var inicio = fechaIniVal.split("/");
		var fin = fechaFinVal.split("/");

		if ((fin[2]) >= (inicio[2])) {
			if((fin[2]) == (inicio[2])){
				if((fin[1]) >= (inicio[1])){
					if((fin[1]) == (inicio[1])){
						if((fin[0]) < (inicio[0])){
							customAlert("La Fecha de fin debe de ser mayor a la fecha de inicio");
							validaFecha = false;
						}else {
							validaFecha = true;
						}	
					}else{
						validaFecha = true;
					}
				}else{
					customAlert("La Fecha de fin debe de ser mayor a la fecha de inicio");
					validaFecha = false;
				}
			}else{
				validaFecha = true;
			}
		}else{
			customAlert("La Fecha de fin debe de ser mayor a la fecha de inicio");
			validaFecha = false;
		} 		
	}	
	return validaFecha;
}

//Funcion para alertDinamico
function alertDinamicoEvCadCus(textoAlert, id){
	$("#divAlertTexto").html(textoAlert);
    $( "#dialog-Alert" ).dialog({
		autoOpen: true,
		resizable: false,
		zIndex:19500,
		modal: true,
		buttons: {				
			
			"Aceptar": function() {
				$( this ).dialog( "close" );
				$("#divAlertTexto").html("");					
			}				
		}
	});    
 }

//Funcion para alertDinamico
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

//Funcion para alertDinamico
function alertDinamicoDosBotones(textoAlert, id){
    $("#divAlertTexto").html(textoAlert);
    $( "#dialog-Alert" ).dialog({
		autoOpen: true,
		resizable: false,
		//height:290,
		//width:300,
		modal: true,
		buttons: {				
			"Aceptar": function() {	
				activaConfirm(id);
				$( this ).dialog( "close" );
				$("#divAlertTexto").html("");					
			},
			"Cancelar": function() {
				$( this ).dialog( "close" );
				$("#divAlertTexto").html("");					
			}				
		}
	});    
 }


function customAlert(texto, titulo, funcion){
	var tituloTmp;
	if ( titulo === undefined ) {
		tituloTmp = '';
	}else{
		tituloTmp = titulo;
	}
	
	if (jQuery('#customAlert').length){
		jQuery('#customAlertText').empty();
		jQuery('#customAlertText').html(texto);
	}else{
		jQuery("body").append("<div id='customAlert'>" +
				"<p><span id=\"customAlertText\">"+ texto+"</span></p></div>");
	}
	
	jQuery( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	jQuery( "#customAlert" ).dialog({
		resizable: false,
		title: tituloTmp,
		height:'auto',
		width:'auto',
		modal: true,
		autoOpen: false,
		closeOnEscape: false,
		buttons: {
			'Aceptar': function() {
				jQuery( this ).dialog( "close" );
				if (!(funcion === undefined) ) {
					funcion();
				}
			}
		}
	});	
	
	return jQuery( "#customAlert" ).dialog( "open" );
	
}

function customConfirm(texto, titulo, fAceptar, fCancelar){
	var tituloTmp;
	if ( titulo === undefined ) {
		tituloTmp = '';
	}else{
		tituloTmp = titulo;
	}
	
	if (jQuery('#customConfirm').length){
		jQuery('#customConfirmText').empty();
		jQuery('#customConfirmText').html(texto);
	}else{
		jQuery("body").append("<div id='customConfirm'>" +
				"<p><span id=\"customConfirmText\">"+ texto+"</span></p></div>");
	}
	
	jQuery( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	jQuery( "#customConfirm" ).dialog({
		resizable: false,
		title: tituloTmp,
		height:'auto',
		width:'auto',
		modal: true,
		autoOpen: false,
		closeOnEscape: false,
		buttons: {
			'Aceptar': function() {
				jQuery( this ).dialog( "close" );
				if (!(fAceptar === undefined) ) {
					fAceptar();
				}
			},
			"Cancelar": function() {
				$( this ).dialog( "close" );
				if (!(fCancelar === undefined) ) {
					fCancelar();
				}
			}
		}
	});	
	
	return jQuery( "#customConfirm" ).dialog( "open" );
	
}


/** mï¿½todo que crea una ventana:
 * @param id del la ventana
 * @param titulo de la ventana
 * @param url relativa direcciï¿½n a mostrar eg: /path.do
 * @param parametros que se le enviaran a la ventana debe empezar con ?, eg: ?expediente=1&caso=1
 */

function customVentana(id, titulo, url, parametros, fCerrar) {
	var urlCompleta;
	if ( parametros === undefined ) {
		urlCompleta = contextoPagina + url;
	}else{
		urlCompleta = contextoPagina + url + parametros;
	}
	$.newWindow( {
					id:id, 
					statusBar: true, 
					posx:0,
					posy:0,
					width:$(document).width(),
					height:$(document).height(),
					title:titulo, 
					type:"iframe",
					onWindowClose: function(id){
						if ( !(fCerrar === undefined) ) {
							fCerrar();
						}
					}
	} );
	
	$.maximizeWindow(id);
    $.updateWindowContent(
    		id,
    		'<iframe src="' + urlCompleta + '" width="100%" height="100%" />'
    );
}

//Funcion para validar numeros decimales
function isnumeroDecimal(numero) {	
	var num = numero;
	var filtro=/^([0-9])*[.]?[0-9]*$/;
			
	if(filtro.test(num) == false) {
		alertDinamico('El n\u00famero es inv\u00e1lido');
		return false;
	}
	if (filtro.test(num)){
		return true;
	}
	return false;
}

/*
 * Funcion que permite solo capturar numeros decimales en los campos de texto
 * Se debe colocar dentro de la accion onkeypressdown del textbox
 * */
function numerosDecimales(evt){
	var nav = window.Event ? true : false;
	// 'Backspace' = 8, 'Enter' = 13, '0' = 48, '9' = 57, '.' = 46
	var key = nav ? evt.which : evt.keyCode;
	return (key <= 13 || (key >= 48 && key <= 57) || key == 46);
}

/*
 * Funcion que permite solo capturar letras con 'Ã‘' y 'Ã±'
 * Se debe colocar dentro de la accion onkeypressdown del textbox
 * */
function soloLetrasN(e) { 
    tecla = e.charCode ? e.charCode : e.keyCode;
    // 'Backspace' = 8, 'Tab' = 9
    if (tecla==8 || tecla==9) return true;	
    patron =/[A-Za-zÑñ\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

/*
 * Funcion que permite solo capturar letras con 'ï¿½' , 'ï¿½' y '.'
 * Se debe colocar dentro de la accion onkeypressdown del textbox
 * */
function soloLetrasNPunto(e) { 
    tecla = e.charCode ? e.charCode : e.keyCode;
    // 'Backspace' = 8, 'Tab' = 9, '.' = 46 
    if (tecla==8 || tecla==9 || tecla==46) return true;	
    patron =/[A-Za-zÑñ\s]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}
function getFrameElement() {
    var iframes = parent.document.getElementsByTagName('iframe');
    for (var i= iframes.length; i-->0;) {
        var iframe = iframes[i];
        try {
            var idoc= 'contentDocument' in iframe? iframe.contentDocument : iframe.contentWindow.document;
        } catch (e) {
            continue;
        }
        if (idoc === document)
            return iframe;
    }
    return null;
}


function cerrarCustomVentana(){
	var iframe = getFrameElement();
	if (iframe != null){
		var idAbuelo = iframe.parentNode.parentNode.id;
		try{
		window.parent.cerrarCustomVentanaDesdeHijo(idAbuelo);
		}catch(e){}
	}
}

function cerrarCustomVentanaDesdeHijo(iframe){
	$.closeWindow(iframe);
}

function bloquearPantalla (conImagen, titulo, mensaje){

	var tituloTmp;
	var mensajeTmp;
	var imagen = "<p><center><img src='" + contextoPagina +"/resources/images/loading6.gif' /></center></p>" ;
	if ( conImagen === undefined ) {
		conImagen = true;
	}
	
	if ( titulo === undefined ) {
		tituloTmp = 'Cargando el contenido';
	}else{
		tituloTmp = titulo;
	}

	if ( mensaje === undefined ) {
		mensajeTmp = '<p><center>Espere un momento por favor.</center></p>';
	}else{
		mensajeTmp = mensaje;
	}
	
	if (conImagen){
		mensajeTmp = imagen + mensajeTmp; 
	}
	
	jQuery.blockUI({ 
		theme:     true, 
		title:    tituloTmp, 
		message:  mensajeTmp 
	});
}

function esFecha(value) {
    try {
        //Change the below values to determine which format of date you wish to check. It is set to dd/mm/yyyy by default.
        var DayIndex = 0;
        var MonthIndex = 1;
        var YearIndex = 2;
 
        value = value.replace(/-/g, "/").replace(/\./g, "/"); 
        var SplitValue = value.split("/");
        var OK = true;
        if (!(SplitValue[DayIndex].length == 1 || SplitValue[DayIndex].length == 2)) {
            OK = false;
        }
        if (OK && !(SplitValue[MonthIndex].length == 1 || SplitValue[MonthIndex].length == 2)) {
            OK = false;
        }
        if (OK && SplitValue[YearIndex].length != 4) {
            OK = false;
        }
        if (OK) {
            var Day = parseInt(SplitValue[DayIndex], 10);
            var Month = parseInt(SplitValue[MonthIndex], 10);
            var Year = parseInt(SplitValue[YearIndex], 10);
 
            if (OK = ((Year > 1900) && (Year <= new Date().getFullYear()))) {
                if (OK = (Month <= 12 && Month > 0)) {
                    var LeapYear = (((Year % 4) == 0) && ((Year % 100) != 0) || ((Year % 400) == 0));
 
                    if (Month == 2) {
                        OK = LeapYear ? Day <= 29 : Day <= 28;
                    }
                    else {
                        if ((Month == 4) || (Month == 6) || (Month == 9) || (Month == 11)) {
                            OK = (Day > 0 && Day <= 30);
                        }
                        else {
                            OK = (Day > 0 && Day <= 31);
                        }
                    }
                }
            }
        }
        return OK;
    }
    catch (e) {
        return false;
    }
}

function cambiarTituloVisor(idFrame, texto){
	var idFrameModificado = "#" + idFrame + " div.window-titleBar-content";
	$(idFrameModificado).html(texto);
}


function recuperaTituloVisor(idFrame){
	var idFrameModificado = "#" + idFrame + " div.window-titleBar-content";
	return $(idFrameModificado).text();
}