// Cuando el documento este listo ejecutamos las siguientes funciones.
    //variables para setear las fechas
	var fechaServidor="";
	var fechaMax="";
	var timeMax="";
	
	
	var fechaSel="";
	
$(document).ready(function() {
    $("#tabprincipal").tabs();
    var fechaActual = new Date();
    
    $("#fechaSolicitud").html(getFechaActual(fechaActual));
    $("#horaSolicitud").html(getHoraMinutoActual(fechaActual));
    // iniciamos la carga de catalogos.
    // llamada a consultarCatalogoTipoAudiencia
    ejecutaAction("/consultarCatalogoTipoAudiencia", function(tiposDeAudiencia){
        $(tiposDeAudiencia).find('institucion').each(function(){
            $('#tipoDeAudiencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        });
    });
    var perteneceAPoderJudicial = false;
    
    if (!perteneceAPoderJudicial) {
        // llamada a consultarCasoPorExpediente
        ejecutaAction("/consultarCasoPorExpediente",
            function(caso){
                $("#numeroDeCaso").val($(caso).find("numeroGeneralCaso").text());
                $("#numeroDeCaso").attr("readonly", "true");
            },
            "numeroExpediente=" + obtenParametroDeUrl("numeroExpediente"));
        // nombre del solicitante
        ejecutaAction("/consultarDatosUsuario", function(usuario){
            var nombre = $(usuario).find("nombreFuncionario").first().text();
            var apellidoPaterno = $(usuario).find("apellidoPaternoFuncionario").first().text();
            var apellidoMaterno = $(usuario).find("apellidoMaternoFuncionario").first().text();
            $("#nombreDelSolicitante").val(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
            $("#nombreDelSolicitante").attr("readonly", "true");
            $("#institucionSolicitante").text($(usuario).find("funcionario").find("departamento").find("area").find("nombre").text());
        });
        // llamada a consultarInvolucradoPorCalidadCaso
        ejecutaAction("/consultarInvolucradoPorCalidadCaso", function(involucrados){
            $(involucrados).find('involucrado').each(function(){
                var calidadInvolucrado = $(this).find("calidadDTO").find("valorIdCalidad").find("idCampo").text();
                var nombreImputado = "";
                     $(this).find('nombreDemografico').each(function(){
                         nombreImputado =   $(this).find('apellidoPaterno').text() + " " +
                                    $(this).find('apellidoMaterno').text() + " " +
                                    $(this).find('nombre').text();
                     });
                if (calidadInvolucrado == PROBABLE_RESPONSABLE_PERSONA ) {
                    $('#imputados').append('<option value="' + $(this).find('elementoId').text() + '">'+ nombreImputado + '</option>');
                }
                // Victima 214
                if (calidadInvolucrado == VICTIMA_PERSONA) {
                    $('#victimas').append('<option value="' + $(this).find('elementoId').text() + '">'+ nombreImputado + '</option>');
                }
                // Denunciante 215
                if (calidadInvolucrado == DENUNCIANTE) {
                    $('#victimas').append('<option value="' + $(this).find('elementoId').text() + '">'+ nombreImputado + '</option>');
                }
            });
        },
        "numeroExpediente=" + obtenParametroDeUrl("numeroExpediente"));
        
        fechaServidor= consultaFechaHoraMaximaServer();
    	fechaMax=getFechaMaximaServerHechos(fechaServidor);
    	timeMax=getHoraMaximaServer(fechaServidor);
    	
        // ponemos un datepicker a la fecha
        $("#fechaLimiteAudiencia").datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange: '-111:+300',
            minDate: fechaMax,
            onSelect: function(date) {
            	fechaSel=date;
            	seteaHoraMinima(date);
				},
            changeMonth: true,
            changeYear: true,
            showOn: "button",
            buttonImage: CONTEXT_ROOT + "/resources/images/date.png",
            buttonImageOnly: true
        });
        
        $('#horaLimiteAudiencia').timeEntry({
            timeSteps:[1,1,0],
            minTime: timeMax,
            show24Hours: true
        });
    // Fin si no pertenece a poder judicial
    }else{

        $("#numeroDeCaso").change(function(){
            var numeroDeCaso = $("#numeroDeCaso").val();
            ejecutaAction("/validarExisteCaso", function(respuesta){
                var existe = $(respuesta).find("boolean").text();
                if(existe == "false"){
                    alertDinamico("El número de caso no existe");
                }
            },
            "numeroDeCaso="+numeroDeCaso);
        });
    }

}); // Fin onready de jquery.

function seteaHoraMinima(date)
{
	
	if(date==fechaMax)
	{
		$('#horaLimiteAudiencia').timeEntry('destroy');
		$('#horaLimiteAudiencia').timeEntry({show24Hours: true, minTime: timeMax});
	}
	else
	{
		$('#horaLimiteAudiencia').timeEntry('destroy');
		$('#horaLimiteAudiencia').timeEntry({show24Hours: true, minTime: null,maxTime: null});
	}
	
}


function enviarSolicitudAudiencia(){
	
    var parametros = obtenParametros("parametro");
       
    if(parametros.indexOf("tipoDeAudiencia=-1") > 0) {
        alertDinamico("Debe seleccionar un tipo de audiencia");
        return ;
    }
    
    if(parametros.indexOf("tribunal=-1") > 0){
    	alertDinamico("Debe seleccionar un tribunal para la audiencia");
    	return;
    }
    if(parametros.indexOf("funcionarioDestinatario=-1") > 0){
    	alertDinamico("Debe seleccionar un destinatario para la audiencia");
    	return;
    }
   
	parametros += "&idExpedienteSoli=" + idEspedienteSoli;
	if (idNumeroExpediente != undefined && idNumeroExpediente != null
			&& idNumeroExpediente != "") {
		parametros += "&idNumeroExpediente=" + idNumeroExpediente;
	}
	
	if ($("#imputados").length==1 && $("#imputados")[0].length==0			
			&& $("#victimas").length==1 && $("#victimas")[0].length==0){
		customConfirm("La solicitud generada se enviar&aacute; sin imputados ni v&iacute;ctimas asociadas.<br/>" +
				"Se recomienda por lo menos asociar un interviniente antes de enviar la solicitud.<br/> &#191;Desea enviar la solicitud?", 
				"Solicitud sin intervinientes", 
				function (){
					ejecutaAction("/enviarSolicitudAudiencia",function(respuesta) {
						alertDinamico($(respuesta).find('body').text());
					}, parametros);
				},
				function(){
					$( this ).dialog( "close" );
				}
		)
	}else{
		ejecutaAction("/enviarSolicitudAudiencia",function(respuesta) {
			alertDinamico($(respuesta).find('body').text());
		}, parametros);		
	}
}

function getFechaActual(fechaActual){
    var month = fechaActual.getMonth() + 1;
    var mes = month < 10 ? "0" + month: month;
    return fechaActual.getDate() + "/" + mes + "/" +
    fechaActual.getFullYear();
}

/*
 * Funcion para regresar la fecha maxima obtenida desde el servidor
 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
 * regresa una cadena con la fecha en el formato : DD/MM/YYYY
 */
function getFechaMaximaServerHechos(fechaCompleta)
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

function alertDinamico(textoAlert){
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



