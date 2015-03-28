/**
 * 
 */

function estaSesionActiva(){
	if(sesionActiva=="false"){
		document.location.href = contextoPagina + "/Logout.do";
	}
}

function  reiniciar(){
	$.idleTimeout.options.onResume.call($.idleTimeout('#dialogBlok', 'div.ui-dialog-buttonpane button:first'));
	$('#password').val("");
	$('#scaptcha').val("");
	$('#imgcaptcha').hide().attr("src", contextoPagina + "/kaptcha.jpg?" + (new Date().getTime()) ).fadeIn(); 
}

function validaContra(){
	var pass=$('#password').val();
	var capcha=$('#scaptcha').val();
	$.ajax({
   		type: 'POST',
    		url: contextoPagina +  '/consultarAutenticidad.do',
    		data: {password:pass, captcha:capcha, noCache: new Date().getTime()},
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var op=$(xml).find('usuarioDTO').find('datosIncorrectos').text();
    			if(op=="false"){
    				customAlert("Los datos son incorrectos", "Error");
    			}else{
    				$("#dialog-bloqueo").dialog( "close" );
    				reiniciar();
    			}
			}
   	});
}

function bloqueoSesion(){
	$( "#dialog-bloqueo" ).dialog({
		resizable: false,
		height:400,
		width:400,
		modal: true,
		closeOnEscape: false,
		buttons: {
			"Aceptar": function() {
				validaContra();
			},
			"Cancelar": function() {
				document.location.href = contextoPagina + "/Logout.do";
				$( this ).dialog( "close" );
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}


function iniciarBloqueoSesion(){
	$("#dialogBlok").dialog({
		autoOpen: false,
		modal: true,
		width: 400,
		height: 200,
		closeOnEscape: false,
		draggable: false,
		resizable: false,
		buttons: {
			'¡Autenticarse!': function(){
				// fire whatever the configured onTimeout callback is.
				// using .call(this) keeps the default behavior of "this" being the warning
				// element (the dialog in this case) inside the callback.
				$(this).dialog('close');
				bloqueoSesion();

			}
		}
		
	});				
	
	var $countdown = $("#dialog-countdown");
	// start the idle timer plugin
	$.idleTimeout('#dialogBlok', 'div.ui-dialog-buttonpane button:first', {
		idleAfter: tiempoActiva,
		pollingInterval: 2,
		serverResponseEquals: 'OK',
		onTimeout: function(){
			$(this).dialog('close');
			bloqueoSesion();
		},
		onIdle: function(){
				$(this).dialog("open");
		},
		onCountdown: function(counter){
			$countdown.html(counter); // update the counter
		},
		onResume: function(){
			
		}
	});	
	//Fin
}

function customLogout(){
	
	if (jQuery('#dialog-logout').length == 0){
		jQuery("body").append("<div id=\"dialog-logout\" title=\"Cerrar Sesi&oacute;n\"><p align=\"center\"><span id=\"logout\">¿Desea cerrar su sesi&oacute;n?</span></p></div>");
	}

	jQuery( "#dialog:ui-dialog" ).dialog( "destroy" );

	$( "#dialog-logout" ).dialog({
		autoOpen: false,
		resizable: false,
		//height:290,
		//width:300,
		modal: true,
		buttons: {
			"Aceptar": function() {
				//$( this ).dialog( "close" );
				//$( "#dialog:ui-dialog" ).dialog( "destroy" );
				document.location.href= contextoPagina + "/Logout.do";
			},
			"Cancelar": function() {
				$( this ).dialog( "close" );
				$( "#dialog:ui-dialog" ).dialog( "destroy" );
			}
		}
	});											
}



$(document).ready(function() {
	iniciarBloqueoSesion();
	customLogout();
});	
