<script type="text/javascript">

  	/*
   *Funcion para recuperar los datos de Coordenadas GPS
   */
   function recuperaDatosCoordenadasGPSNotif()
   {
	   var lsDatosCoordenadasGPS="";
	   		lsDatosCoordenadasGPS="&longitudENotif="+$("#txtFldLongitudEsteNotif").val();
	   		lsDatosCoordenadasGPS+="&longitudGradosNotif="+$("#txtFldLongitudGradosNotif").val();
	   		lsDatosCoordenadasGPS+="&longitudMinutosNotif="+$("#txtFldLongitudMinutosNotif").val();
	   		lsDatosCoordenadasGPS+="&longitudSegundosNotif="+$("#txtFldLongitudSegundosNotif").val();
	   		lsDatosCoordenadasGPS+="&latitudNNotif="+$("#txtFldLatitudNorteNotif").val();
	   		lsDatosCoordenadasGPS+="&latitudGradosNotif="+$("#txtFldLatitudGradosNotif").val();
	   		lsDatosCoordenadasGPS+="&latitudMinutosNotif="+$("#txtFldLatitudMinutosNotif").val();
	   		lsDatosCoordenadasGPS+="&latitudSegundosNotif="+$("#txtFldLatitudSegundosNotif").val();
	   return lsDatosCoordenadasGPS;
   }

  
   function pintaDatosCoordenadasGPSNotif(xml){
	   $('#txtFldLongitudEsteNotif').val($(xml).find('domicilioNotificacion').find('longitudE').text());
	   $('#txtFldLongitudGradosNotif').val($(xml).find('domicilioNotificacion').find('longitudGrados').text());
	   $('#txtFldLongitudMinutosNotif').val($(xml).find('domicilioNotificacion').find('longitudMinutos').text());
	   $('#txtFldLongitudSegundosNotif').val($(xml).find('domicilioNotificacion').find('longitudSegundos').text());
	   $('#txtFldLatitudNorteNotif').val($(xml).find('domicilioNotificacion').find('latitudN').text());
	   $('#txtFldLatitudGradosNotif').val($(xml).find('domicilioNotificacion').find('latitudGrados').text());
	   $('#txtFldLatitudMinutosNotif').val($(xml).find('domicilioNotificacion').find('latitudMinutos').text());
	   $('#txtFldLatitudSegundosNotif').val($(xml).find('domicilioNotificacion').find('latitudSegundos').text());
	}
   
   
   function bloqueaCamposCoordenadasGPSHechoNotif(bandera)
   {
	   if(parseInt(bandera)==0)
	   {
		   $('#txtFldLongitudEsteNotif').attr("disabled","disabled");
		   $('#txtFldLongitudGradosNotif').attr("disabled","disabled");
		   $('#txtFldLongitudMinutosNotif').attr("disabled","disabled");
		   $('#txtFldLongitudSegundosNotif').attr("disabled","disabled");
		   $('#txtFldLatitudNorteNotif').attr("disabled","disabled");
		   $('#txtFldLatitudGradosNotif').attr("disabled","disabled");
		   $('#txtFldLatitudMinutosNotif').attr("disabled","disabled");
		   $('#txtFldLatitudSegundosNotif').attr("disabled","disabled");
	   }
	   else
	   {
		   $('#txtFldLongitudEsteNotif').attr("disabled","");
		   $('#txtFldLongitudGradosNotif').attr("disabled","");
		   $('#txtFldLongitudMinutosNotif').attr("disabled","");
		   $('#txtFldLongitudSegundosNotif').attr("disabled","");
		   $('#txtFldLatitudNorteNotif').attr("disabled","");
		   $('#txtFldLatitudGradosNotif').attr("disabled","");
		   $('#txtFldLatitudMinutosNotif').attr("disabled","");
		   $('#txtFldLatitudSegundosNotif').attr("disabled","");
	   }
   }
	var patron = new Array(2,2,4)
	var patron2 = new Array(1,3,3,3,3)
	function mascara(d,sep,pat,nums){
		if(d.valant != d.value){
		val = d.value
   		largo = val.length
   		val = val.split(sep)
   		val2 = ''
   		for(r=0;r<val.length;r++){
   			val2 += val[r]	
   		}
   		if(nums){
   			for(z=0;z<val2.length;z++){
   				if(isNaN(val2.charAt(z))){
   					letra = new RegExp(val2.charAt(z),"g")
   					val2 = val2.replace(letra,"")
   				}
   			}
   		}
   		val = ''
   		val3 = new Array()
   		for(s=0; s<pat.length; s++){
   			val3[s] = val2.substring(0,pat[s])
   			val2 = val2.substr(pat[s])
   		}
   		for(q=0;q<val3.length; q++){
   			if(q ==0){
   				val = val3[q]
   			}
   			else{
   				if(val3[q] != ""){
   					val += sep + val3[q]
   				}
   			}
   		}
   		d.value = val
   		d.valant = val
   	}
  }


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

	function soloLetras(e) {
		var unicode = e.charCode ? e.charCode : e.keyCode;

		// if the key is backspace, tab, or numeric
		//if (unicode == 8 || unicode == 9 || (unicode >= 65 && unicode <= 90)) { todas las letras en mayuscula
		if (unicode == 8 || unicode == 9 || unicode == 79 || unicode == 69 || unicode == 101 || unicode == 111 ) {// solo las letras utilizadas
		// we allow the key press
		return true;
		}
		else {
		// otherwise we don't
		return false;
		}
		}

	function soloLetrasNorte(e) {
		var unicode = e.charCode ? e.charCode : e.keyCode;

		// if the key is backspace, tab, or numeric
		if (unicode == 8 || unicode == 9 || unicode == 78 || unicode == 83 || unicode == 110 || unicode == 115  ) {
			
		// we allow the key press
		return true;
		}
		else {
		// otherwise we don't
		return false;
		}
		}
	function mayorMinLong() {
		var min=$("#txtFldLongitudMinutos").val();
		if(min>59 || min==0){
			$("#txtFldLongitudMinutos").val("");
		}
	}
	function mayorMinLat() {
		var min=$("#txtFldLatitudMinutos").val();
		if(min>59 || min==0){
			$("#txtFldLatitudMinutos").val("");
		}
	}
	function mayorSegLong() {
		var min=$("#txtFldLongitudSegundos").val();
		if(min>59 || min==0){
			$("#txtFldLongitudSegundos").val("");
		}
	}
	function mayorSegLat() {
		var min=$("#txtFldLatitudSegundos").val();
		if(min>59 || min==0){
			$("#txtFldLatitudSegundos").val("");
		}
	}
	
	//funcion para limpiar los campos de las corrdenadas GPS
	 function limpiarDtsCrdndsGPSNotif()
	   {
	   	   $('#txtFldLongitudEsteNotif').val("");
		   $('#txtFldLongitudGradosNotif').val("");
		   $('#txtFldLongitudMinutosNotif').val("");
		   $('#txtFldLongitudSegundosNotif').val("");
		   $('#txtFldLatitudNorteNotif').val("");
		   $('#txtFldLatitudGradosNotif').val("");
		   $('#txtFldLatitudMinutosNotif').val("");
		   $('#txtFldLatitudSegundosNotif').val("");
		  }
</script>

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    	<td>&nbsp;&nbsp;&nbsp;Longitud:E/O:</td>
  		<td><input type="text" name="txtFldLatitudNorte" id="txtFldLongitudEsteNotif" size="1" maxlength="1" onKeyPress="return soloLetras(event);" onKeyUp="this.value = this.value.toUpperCase();" tabindex="18"/></td>
  		<td ><input type="text" name="txtFldLatitudGrados" id="txtFldLongitudGradosNotif" size="1" maxlength="2" onKeyPress="return solonumeros(event);" tabindex="20"/>&nbsp;°&nbsp;</td>
  		<td><input type="text" name="txtFldLatitudMinutos" id="txtFldLongitudMinutosNotif" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorMinLong()" tabindex="22"/>&nbsp;&nbsp;'&nbsp;&nbsp;</td>
  		<td><input type="text" name="txtFldLatitudSegundos" id="txtFldLongitudSegundosNotif" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorSegLong()" tabindex="24"/>&nbsp;"</td>
  </tr>
  <tr>
  		<td>&nbsp;&nbsp;&nbsp;Latitud:N/S:</td>
  		<td><input type="text" name="txtFldLatitudNorte" id="txtFldLatitudNorteNotif" size="1" maxlength="1" onKeyPress="return soloLetrasNorte(event);" onKeyUp="this.value = this.value.toUpperCase();" tabindex="19" /></td>
  		<td><input type="text" name="txtFldLatitudGrados" id="txtFldLatitudGradosNotif" size="1" maxlength="2"  onKeyPress="return solonumeros(event);" tabindex="21"/>&nbsp;°&nbsp;</td>
  		<td><input type="text" name="txtFldLatitudMinutos" id="txtFldLatitudMinutosNotif" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorMinLat()" tabindex="23"/>&nbsp;&nbsp;'&nbsp;&nbsp;</td>
  		<td><input type="text" name="txtFldLatitudSegundos" id="txtFldLatitudSegundosNotif" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorSegLat()" tabindex="25"/>&nbsp;"</td>
  </tr>
</table>
