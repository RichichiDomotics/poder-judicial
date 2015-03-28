<script type="text/javascript">

function seleccionaMexico(){
	$('#cbxPaisNacimiento').val(10);
	onSelectChangePaisNacimiento();
 }


 /**
  * Limpia los combos:Ent Federativa, Ciudad, DelegacionMunicipio, AsentamientoColonia
  * Tipo de Asentamiento y Tipo de Calle, para el Domicilio
  */  
  function cleanAllCombosNacimiento(){
	  
	$('#cbxEntFederativaNacimiento').empty();
	$('#cbxEntFederativaNacimiento').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
	$('#cbxCiudadNacimiento').empty();
	$('#cbxCiudadNacimiento').append('<option value="-1">-Seleccione-</option>');	
	$("#datosNacimientoCmpFechaNacimiento,#datosNacimientoCmpEdadAproximada").val("");
  }
  
  /**
	  * Limpia los combos que dependen del combo entidad federativa, para 
	  * el domicilio
	  */  
	  function cleanCombosDependsEntidadFedNacimiento(){
		$('#cbxCiudadNacimiento').empty();
		$('#cbxCiudadNacimiento').append('<option value="-1">-Seleccione-</option>');
		
	  }
				
								  
	/*
	*Funcion que realiza la carga del combo de Paises de nacimiento
	*/
	function cargaPaisesNacimiento() {
	  
	$.ajax({
		async: false,
		type: 'POST',
		url: '<%= request.getContextPath()%>/cargarPaises.do',
		data: '',
		dataType: 'xml',
		success: function(xml){
			$(xml).find('catPaises').each(function(){
				$('#cbxPaisNacimiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
//				$('#cbxPaisNacimiento').multiselect('refresh');
			});
		}
	});

	
}

/**
* Si existe un cambio en el combo de paises se realiza la consulta de 
* entidades federativas, y si la consulta es NO vacía se leventa la 
* bandera para mostrar los combo box. Esto para el nacimiento
*/ 	
function onSelectChangePaisNacimiento() {
	  
	var selected = $("#cbxPaisNacimiento option:selected");
		
	cleanAllCombosNacimiento();							//Limpia todos los combo box´s		
	
	$.ajax({
		async: false,									// la accion cargar estados y llena el combo con la consulta
		type: 'POST',
		url: '<%= request.getContextPath()%>/cargarEntFederativas.do',
		data: 'glCatPaisId=' + selected.val(),	//Parametro para hacer la consulta de Entidades por Id del País
		dataType: 'xml',
		success: function(xml){
			$(xml).find('catEntidadesFed').each(function(){
				$('#cbxEntFederativaNacimiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
//				$('#cbxEntFederativaNacimiento').multiselect('refresh');
			});
			
						
		}
	});
}


/**
* Si existe un cambio en el combo de Entidades federativas se realiza la consulta de 
* entidades Ciudades. Esto para el domicilio
*/ 	
function onSelectChangeEntFedNacimiento() {
		  
	var selected = $("#cbxEntFederativaNacimiento option:selected");
					
	cleanCombosDependsEntidadFedNacimiento();
	$.ajax({											// la accion cargar cidades  -  url: '<%= request.getContextPath()%>/cargarCiudades.do',
		async: false,
		type: 'POST',
		url: '<%= request.getContextPath()%>/cargarDelgMun.do', 
		data: 'glCatEntidadFederativaId=' + selected.val(), 	//hace la consulta por el id de la Entidad Federativa
		dataType: 'xml',
		success: function(xml){
			//$(xml).find('catCiudades').each(function(){	
			$(xml).find('catDelegMun').each(function(){		//LLena el comboBox con la consulta									
				$('#cbxCiudadNacimiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
//				$('#cbxCiudadNacimiento').multiselect('refresh');
			});
		}
	});
					
}


function ocultaEdadAprox(){
	if ( $('#datosNacimientoCmpFechaNacimiento').val()!=""){
		$('#datosNacimientoCmpEdadAproximada').attr('disabled', 'disabled');
		
		}
	
	else {
		$('#datosNacimientoCmpEdadAproximada').attr('disabled', '');
		}
	
		
}

function obtenerParametrosDatosNacimiento(){
   
    var parametros = '&fechaNacimiento=' + $('#datosNacimientoCmpFechaNacimiento').val();
    parametros += '&edadAproximada=' +  $('#datosNacimientoCmpEdadAproximada').val();
    parametros += '&paisNacimiento=' + $('#cbxPaisNacimiento option:selected').val();
    parametros += '&entFederativaNacimiento=' + $('#cbxEntFederativaNacimiento option:selected').val();
    parametros += '&municipioNacimiento=' + $('#cbxCiudadNacimiento option:selected').val();
    
	return parametros;
}

	function calculaEdadAproximada(fecha){
		var edad = calculaEdad(fecha);
		if(parseInt(edad) >= 0)
			$("#datosNacimientoCmpEdadAproximada").val(edad);
		else
			$("#datosNacimientoCmpEdadAproximada").val("0");
	}
	 
	function calculaEdad(fecha){
		//calculo la fecha de hoy
		hoy=new Date()
		//calculo la fecha que recibo
		//La descompongo en un array
		var array_fecha = fecha.split("/")
		//si el array no tiene tres partes, la fecha es incorrecta
		if (array_fecha.length!=3)
		   return false
	
		//compruebo que los ano, mes, dia son correctos
		var ano
		ano = parseInt(array_fecha[2]);
		if (isNaN(ano))
		   return false
	
		var mes
		mes = parseInt(array_fecha[1]);
		if (isNaN(mes))
		   return false
		   
		var dia
		dia = parseInt(array_fecha[0]);
		if (isNaN(dia))
		   return false		
	
		//si el año de la fecha que recibo solo tiene 2 cifras hay que cambiarlo a 4
		if (ano<=99)
		   ano +=1900
	
		//resto los años de las dos fechas
		edad=hoy.getFullYear()- ano - 1; //-1 porque no se si ha cumplido años ya este año
	
		//si resto los meses y me da menor que 0 entonces no ha cumplido años. Si da mayor si ha cumplido
		if (hoy.getMonth() + 1 - mes < 0) //+ 1 porque los meses empiezan en 0
		   return edad
		if (hoy.getMonth() + 1 - mes > 0)
		   return edad+1
	
		//entonces es que eran iguales. miro los dias
		//si resto los dias y me da menor que 0 entonces no ha cumplido años. Si da mayor o igual si ha cumplido
		if (hoy.getUTCDate() - dia >= 0)
		   return edad + 1
	
		return edad
	} 


</script>

<table>
	<tr>
		<td align="center" colspan="2" class="seccion">Datos de Nacimiento</td>
	</tr>
	<tr>
		<td align="right">Fecha de Nacimiento:</td>		
		<td><input type="text" id="datosNacimientoCmpFechaNacimiento" onKeypress="return bloqueaEnter(event);" name="datosNacimientoCmpFechaNacimiento" maxlength="10" readonly="readonly" style="width: 180px;" onchange="calculaRFC_CURP_UNO_UNO();calculaEdadAproximada($('#datosNacimientoCmpFechaNacimiento').val())"/></td>
	</tr>
	<tr>
		<td align="right">Edad Aproximada:</td>
		<td><input type="text" id="datosNacimientoCmpEdadAproximada" onKeypress="return bloqueaEnter(event);" name="datosNacimientoCmpEdadAproximada" style="width: 180px;"/></td>
	</tr>
	<tr>
		<td align="right">País de Nacimiento:</td>
		<td>
			<select id="cbxPaisNacimiento" name="cbxPaisNacimiento"  style="width: 180px;">
				<option value="-1">-Seleccione-</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">Estado:</td>
		<td>
			<select id="cbxEntFederativaNacimiento" name="cbxEntFederativaNacimiento" style="width: 180px;" onchange="calculaRFC_CURP_UNO_UNO()">
			<option value="-1">-Seleccione-</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">Municipio:</td>
		<td>
			<select id="cbxCiudadNacimiento" name="cbxCiudadNacimientos" style="width: 180px;">
			<option value="-1">-Seleccione-</option>
			</select>
		</td>
	</tr>
</table>

<script type="text/javascript">
		//variables para setear las fechas y horas maximas
		var fechaServidor="";
		var fechaMax="";
		
		/*
		*Funcion para traer la fecha y hora del servidor en el formato : YYYY-MM-DD HH:MI:SS
		*/
		function consultaFechaHoraMaximaServer()
		{
			var fecha="";
			   $.ajax({
				     type: 'POST',
				     url: '<%=request.getContextPath()%>/regresaFechaYHoraDelServidor.do',
					 dataType: 'xml',
					 async: false,
					 success: function(xml){
						fecha= $(xml).find('fecha').text();
					  }
					});
			return fecha;
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

		
		fechaServidor= consultaFechaHoraMaximaServer();
		fechaMax=getFechaMaximaServerHechos(fechaServidor);
		
		/**
		*Carga los escuchadores de eventos
		*/
		cargaPaisesNacimiento();					//Carga el combo de paises
		seleccionaMexico();
					 
		/**
		* Carga los escuchadores de eventos para los combo box's para 
		* el domicilio 
		*/
		$("#datosNacimientoCmpFechaNacimiento").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '-111:+0',
			maxDate: fechaMax, 
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		//$("#cbxPaisNacimiento").change(onSelectChangePaisNacimiento());
		//$("#cbxEntFederativaNacimiento").change(onSelectChangeEntFedNacimiento());
		//$('#cbxPaisNacimiento').attr('selectedIndex',10);
		//onSelectChangePaisNacimiento();
		//$('#cbxEntFederativaNacimiento').attr('selectedIndex',31);
		//onSelectChangeEntFedNacimiento();
		
		//Codigo para habilitar el calendario en el campo fecha de datos generales
		//$("#datosNacimientoCmpFechaNacimiento").bind("click", ocultaEdadAprox);			
		$("#datosNacimientoCmpFechaNacimiento").bind("change", ocultaEdadAprox);

//		$("#cbxEntFederativaNacimiento").multiselect({ 
//			multiple: false, 
//			header: "Seleccione", 
//			position: { 
//				my: 'top', 
//				at: 'top' 
//			},
//			selectedList: 1, 
//			close: function (event,ui){
//				onSelectChangeEntFedNacimiento();}
//		});

		$("#cbxEntFederativaNacimiento").change(function(e){
			onSelectChangeEntFedNacimiento();
		});

//		$("#cbxPaisNacimiento").multiselect({ 
//			multiple: false, 
//			header: "Seleccione", 
//			position: { 
//				my: 'top', 
//				at: 'top' 
//			},
//			selectedList: 1, 
//			close: function (event,ui){
//				onSelectChangePaisNacimiento();}
//		});

$("#cbxPaisNacimiento").change(function(e){
	onSelectChangePaisNacimiento();
}); 



//		$("#cbxCiudadNacimiento").multiselect({ 
//			multiple: false, 
//			header: "Seleccione", 
//			position: { 
//				my: 'top', 
//				at: 'top' 
//			},
//			selectedList: 1,
//		});

		
</script>