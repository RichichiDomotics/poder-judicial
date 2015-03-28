/**
* Nombre del Programa : ingresarDestinatario.js
* Autor               : Cuauhtemoc Paredes
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 18/05/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para la vista de seleccionar destinatario
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/

/**
 * Objeto de tipo Destinatario
 */
function Destinatario(nombre, apPaterno, apMaterno, puesto, correo, tipoDestinatario){
	this.nombre = nombre;
	this.apPaterno = apPaterno;
	this.apMaterno = apMaterno;
	this.puesto = puesto;
	this.correo = correo;
	this.tipoDestinatario = tipoDestinatario;
}
/**
 * Variables globales para controlar los datos del destinatario
 */
var lstDestinatarios = new Array();
var idDestinatario = 0;

/**
 * Función que crea inicializa los controles asignados en el boton de SeleccionarDestinatario
 * @param contexto Contexto de la aplicación
 */
function inicializaSeleccionarDestinatario(contexto){
	$('#para').click(function () {
		limpiarPopUpDestinatario();
		$("#seleccionUsu").dialog("open");
	});
	
	$('#adjuntarOficio').click(function () {		
	$("#seleccionarOficio").dialog("open");
	});
				
	$("#seleccionUsu").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Seleccionar Destinatario', 
		dialogClass: 'alert',
		position: [100,40],
		width: 800,
		height: 450,
		maxWidth: 900,
		buttons:{"Agregar Otro":function() {
			$(this).dialog("close");
			},
			"Terminar":function() {
			if(parseInt(idDestinatario) <5){
				var destinatario = new Destinatario($('#nombreDestinatarioExt').val(),$('#apPatDestinatarioExt').val(),$('#apMatDestinatarioExt').val(),$('#correoDestinatarioExt').val(),$('#puestoDestinatarioExt').val(),$(':radio[name=tipoDestinatario]:checked').val());
				lstDestinatarios[idDestinatario] = destinatario;
				idDestinatario++;
				$('#destinatariosAgregados').empty();
				for(var i=0;i<lstDestinatarios.length;i++){
					var des = lstDestinatarios[i];                                                           
					$('#destinatariosAgregados').append('<tr><td>'+ des.nombre +'</td>&nbsp;<td>'+des.apPaterno+'</td>&nbsp;<td>'+des.apMaterno+'</td>&nbsp;<td>'+  des.puesto+ '</td>&nbsp;<td>'+  des.correo+'</td>&nbsp;<td>'+  des.tipoDestinatario+'</td></tr>');
				}
			}else{
				alert('ya se capturaron 5 telefonos');
			}
			$(this).dialog("close");
			},
			"Cancelar":function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("#seleccionarOficio").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Seleccionar Oficio', 
		dialogClass: 'alert',
		position: [100,40],
		width: 800,
		height: 450,
		maxWidth: 900,
		buttons:{"Terminar":function() {
			if(parseInt(idDestinatario) <5){
				var destinatario = new Destinatario($('#nombreDestinatarioExt').val(),$('#apPatDestinatarioExt').val(),$('#apMatDestinatarioExt').val(),$('#correoDestinatarioExt').val(),$('#puestoDestinatarioExt').val(),$(':radio[name=tipoDestinatario]:checked').val());
				lstDestinatarios[idDestinatario] = destinatario;
				idDestinatario++;
				$('#destinatariosAgregados').empty();
				for(var i=0;i<lstDestinatarios.length;i++){
					var des = lstDestinatarios[i];                                                           
					$('#destinatariosAgregados').append('<tr><td>'+ des.nombre +'</td>&nbsp;<td>'+des.apPaterno+'</td>&nbsp;<td>'+des.apMaterno+'</td>&nbsp;<td>'+  des.puesto+ '</td>&nbsp;<td>'+  des.correo+'</td>&nbsp;<td>'+  des.tipoDestinatario+'</td></tr>');
				}
			}else{
				alert('ya se capturaron 5 telefonos');
			}
			$(this).dialog("close");
			},
			"Cancelar":function() {
				$(this).dialog("close");
			}
		}
	});	
	llenaGridDocDeExpediente();
}	
				
	
/**
 * Limpia los campos de captura del popUp de destinatario
 */
function limpiarPopUpDestinatario(){
	$('#instituciones').val("1");
	$('#responsableInstitucion').attr('checked',false);
	$('#areas').val("1");
	$('#responsableArea').attr('checked',false);
	$('#departamentos').val("1");
	$('#responsableDepartamento').attr('checked',false);
	$('#nombreDestinatarioExt').val(" ");
	$('#puestoDestinatarioExt').val(" ");
	$('#apPatDestinatarioExt').val(" ");
	$('#correoDestinatarioExt').val(" ");
	$('#apMatDestinatarioExt').val(" ");
}

/**
 * Función encargada de construir un String con los datos de los arreglos de destinatario
 */
function obtenerMedios(){
	var parametros = '&medioContactoTelefono=';
	
	for(var i=0;i<lstTelefonos.length;i++){
		var tel = lstTelefonos[i];
		parametros +=tel.idTipoTelefono + ',';
		parametros +=tel.cvePais + ',';
		parametros +=tel.lada + ',';
		parametros +=tel.telefono + '|';
	}
	//alert("Medios de contacto 1:" + parametros);
	parametros += '&medioContactoCorreo=';
	for(var i=0;i<lstCorreos.length;i++){
		parametros += lstCorreos[i] + ',' ;
	}
	
	//alert("Medios de contacto:" + parametros);
	return parametros;
}