<script type="text/javascript">

  	/*
   *Funcion para recuperar los datos de Coordenadas GPS
   */
   function recuperaDatosCoordenadasGPS(idCalidad)
   {
	   var lsDatosCoordenadasGPS="";
	   		lsDatosCoordenadasGPS="longitudE="+$("#txtFldLongitudEste").val();
	   		lsDatosCoordenadasGPS+="&longitudGrados="+$("#txtFldLongitudGrados").val();
	   		lsDatosCoordenadasGPS+="&longitudMinutos="+$("#txtFldLongitudMinutos").val();
	   		lsDatosCoordenadasGPS+="&longitudSegundos="+$("#txtFldLongitudSegundos").val();
	   		lsDatosCoordenadasGPS+="&latitudN="+$("#txtFldLatitudNorte").val();
	   		lsDatosCoordenadasGPS+="&latitudGrados="+$("#txtFldLatitudGrados").val();
	   		lsDatosCoordenadasGPS+="&latitudMinutos="+$("#txtFldLatitudMinutos").val();
	   		lsDatosCoordenadasGPS+="&latitudSegundos="+$("#txtFldLatitudSegundos").val();
	   return lsDatosCoordenadasGPS;
   }

   /*
    *Funcion para pintar los datos mediante la recuperacion del xml del tipo de datos coordenadas GPS
    */
   function pintaDatosCoordenadasGPS(xml){
	   $('#txtFldLongitudEste').val($(xml).find('domicilio').find('longitudE').text());
	   $('#txtFldLongitudGrados').val($(xml).find('domicilio').find('longitudGrados').text());
	   $('#txtFldLongitudMinutos').val($(xml).find('domicilio').find('longitudMinutos').text());
	   $('#txtFldLongitudSegundos').val($(xml).find('domicilio').find('longitudSegundos').text());
	   $('#txtFldLatitudNorte').val($(xml).find('domicilio').find('latitudN').text());
	   $('#txtFldLatitudGrados').val($(xml).find('domicilio').find('latitudGrados').text());
	   $('#txtFldLatitudMinutos').val($(xml).find('domicilio').find('latitudMinutos').text());
	   $('#txtFldLatitudSegundos').val($(xml).find('domicilio').find('latitudSegundos').text());
	}
   
   function seteaDatosCoordenadasGPSHecho(xml)
   {
	   //$('#txtFldLongitud').val($(xml).find('hechoDTO').find('lugar').find('longitud').text());
	   //$('#txtFldLatitud2').val($(xml).find('hechoDTO').find('lugar').find('latitud').text());
	   $('#txtFldLongitudEste').val($(xml).find('lugar').find('longitudE').text());
	   $('#txtFldLongitudGrados').val($(xml).find('lugar').find('longitudGrados').text());
	   $('#txtFldLongitudMinutos').val($(xml).find('lugar').find('longitudMinutos').text());
	   $('#txtFldLongitudSegundos').val($(xml).find('lugar').find('longitudSegundos').text());
	   $('#txtFldLatitudNorte').val($(xml).find('lugar').find('latitudN').text());
	   $('#txtFldLatitudGrados').val($(xml).find('lugar').find('latitudGrados').text());
	   $('#txtFldLatitudMinutos').val($(xml).find('lugar').find('latitudMinutos').text());
	   $('#txtFldLatitudSegundos').val($(xml).find('lugar').find('latitudSegundos').text());
   }
   
   function bloqueaCamposCoordenadasGPSHecho(bandera)
   {
	   if(parseInt(bandera)==0)
	   {
		   $('#txtFldLongitudEste').attr("disabled","disabled");
		   $('#txtFldLongitudGrados').attr("disabled","disabled");
		   $('#txtFldLongitudMinutos').attr("disabled","disabled");
		   $('#txtFldLongitudSegundos').attr("disabled","disabled");
		   $('#txtFldLatitudNorte').attr("disabled","disabled");
		   $('#txtFldLatitudGrados').attr("disabled","disabled");
		   $('#txtFldLatitudMinutos').attr("disabled","disabled");
		   $('#txtFldLatitudSegundos').attr("disabled","disabled");
	   }
	   else
	   {
		   $('#txtFldLongitudEste').attr("disabled","");
		   $('#txtFldLongitudGrados').attr("disabled","");
		   $('#txtFldLongitudMinutos').attr("disabled","");
		   $('#txtFldLongitudSegundos').attr("disabled","");
		   $('#txtFldLatitudNorte').attr("disabled","");
		   $('#txtFldLatitudGrados').attr("disabled","");
		   $('#txtFldLatitudMinutos').attr("disabled","");
		   $('#txtFldLatitudSegundos').attr("disabled","");
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
	 function limpiarDtsCrdndsGPS()
	   {
	   	   $('#txtFldLongitudEste').val("");
		   $('#txtFldLongitudGrados').val("");
		   $('#txtFldLongitudMinutos').val("");
		   $('#txtFldLongitudSegundos').val("");
		   $('#txtFldLatitudNorte').val("");
		   $('#txtFldLatitudGrados').val("");
		   $('#txtFldLatitudMinutos').val("");
		   $('#txtFldLatitudSegundos').val("");
		  }
</script>

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    	<td>&nbsp;&nbsp;&nbsp;Longitud:E/O:</td>
  		<td valign="top"><input type="text" name="txtFldLatitudNorte" id="txtFldLongitudEste" size="1" maxlength="1" onKeyPress="return soloLetras(event);" onKeyUp="this.value = this.value.toUpperCase();" tabindex="18"/></td>
  		<td valign="top"><input type="text" name="txtFldLatitudGrados" id="txtFldLongitudGrados" size="1" maxlength="2" onKeyPress="return solonumeros(event);"  tabindex="20"/>&nbsp;°&nbsp;</td>
  		<td valign="top"><input type="text" name="txtFldLatitudMinutos" id="txtFldLongitudMinutos" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorMinLong()" tabindex="22"/>&nbsp;&nbsp;'&nbsp;&nbsp;</td>
  		<td valign="top"><input type="text" name="txtFldLatitudSegundos" id="txtFldLongitudSegundos" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorSegLong()" tabindex="24"/>&nbsp;"</td>
  </tr>
  <tr>
  		<td>&nbsp;&nbsp;&nbsp;Latitud:N/S:</td>
  		<td valign="top"><input type="text" name="txtFldLatitudNorte" id="txtFldLatitudNorte" size="1" maxlength="1" onKeyPress="return soloLetrasNorte(event);" onKeyUp="this.value = this.value.toUpperCase();" tabindex="19" /></td>
  		<td valign="top"><input type="text" name="txtFldLatitudGrados" id="txtFldLatitudGrados" size="1" maxlength="2"  onKeyPress="return solonumeros(event);" tabindex="21"/>&nbsp;°&nbsp;</td>
  		<td valign="top"><input type="text" name="txtFldLatitudMinutos" id="txtFldLatitudMinutos" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorMinLat()" tabindex="23"/>&nbsp;&nbsp;'&nbsp;&nbsp;</td>
  		<td valign="top"><input type="text" name="txtFldLatitudSegundos" id="txtFldLatitudSegundos" size="1" maxlength="2" onKeyPress="return solonumeros(event);" onKeyUp="mayorSegLat()" tabindex="25"/>&nbsp;"</td>
  </tr>
</table>
