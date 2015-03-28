
 <script type="text/javascript">
	 /*
	*Funcion para obtener los datos de la organizacion No formal
	*/
	function recuperaInformacionOrganizacionNoFormal()
	{
		var datosOrgNoFormal="";
		datosOrgNoFormal = datosOrgNoFormal +'&areaOrgNoFormal=' +$("#txtIngOrgNoFormalArea").val();
		datosOrgNoFormal = datosOrgNoFormal +'&giroOrgNoFormal=' +$("#txtIngOrgNoFormalGiro").val();
		return datosOrgNoFormal;
	}
	
	/*
	*Funcion para pintar los datos de la organizacion No formal
	*/
	function seteaInformacionOrganizacionNoFormal(xml)
	{
		//seteamos la informacion
		

 		$("#txtIngOrgNoFormalArea").val($(xml).find('organizacionDTO').find('areaDeInfluencia').text());
 		$("#txtIngOrgNoFormalGiro").val($(xml).find('organizacionDTO').find('giro').text());
		//bloqueo los campos
		habilitaDeshabilitaCamposOrganizacionNoFormal(0);
	}
	
	/*
 	*Metodo para limpiar los campos de Organizacion No Formal
 	*/
 	function limpiaCamposOrganizacionNoFormal()
 	{
 		$("#txtIngOrgNoFormalArea").val('');
		$("#txtIngOrgNoFormalGiro").val('');
 	}
	
 	/*
 	*Metodo para bloquear o desbloquear los campos de Organizacion Formal
 	*bandera = 0  para bloquear
 	*bandera = 1 para desbloquear
 	*/
 	function habilitaDeshabilitaCamposOrganizacionNoFormal(bandera)
 	{
 		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura
			$("#txtIngOrgNoFormalArea").attr("disabled","disabled");
			$("#txtIngOrgNoFormalGiro").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$("#txtIngOrgNoFormalArea").attr("disabled","");
			$("#txtIngOrgNoFormalGiro").attr("disabled","");
		}
 	}
</script>

<!-- Tabla INGRESAR ORGANIZACION NO FORMAL-->
   <table id="iIngresarOrgNoFormalWorkSheet" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="30">Área de influencia: </td>
        <td >
        	<input id="txtIngOrgNoFormalArea" title="Escribir area de influencia" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
      <tr>
        <td width="30%" height="30">Giro: </td>
        <td >
        	<input id="txtIngOrgNoFormalGiro" title="Escribir giro de la organizacion" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
   </table>
<!-- FIN Tabla INGRESAR ORGANIZACION NO FORMAL-->
