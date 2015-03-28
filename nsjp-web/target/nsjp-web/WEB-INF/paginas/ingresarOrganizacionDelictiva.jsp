
 <script type="text/javascript">
 /*
	*Funcion para obtener los datos de la organizacion No formal
	*/
	function recuperaInformacionOrganizacionDelictiva()
	{
		var datosOrgDelictiva="";
		datosOrgDelictiva = datosOrgDelictiva +'&descOrgDelictiva=' +$("#txtIngOrgDelictivaDescOrg").val();
		return datosOrgDelictiva;
	}
	
	/*
	*Funcion para pintar los datos de la organizacion No formal
	*/
	function seteaInformacionOrganizacionDelictiva(xml)
	{
		//seteamos la informacion
 		$('#txtIngOrgDelictivaDescOrg').val($(xml).find('descripcionDelictiva').text());
		//bloquemos los campos
		habilitaDeshabilitaCamposOrganizacionDelictiva(0);
	}
	
	/*
 	*Metodo para limpiar los campos de Organizacion Delictiva
 	*/
 	function limpiaCamposOrganizacionDelictiva()
 	{
 		$("#txtIngOrgDelictivaDescOrg").val('');
 	}
	
 	/*
 	*Metodo para bloquear o desbloquear los campos de Organizacion Formal
 	*bandera = 0  para bloquear
 	*bandera = 1 para desbloquear
 	*/
 	function habilitaDeshabilitaCamposOrganizacionDelictiva(bandera)
 	{
 		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura
			$("#txtIngOrgDelictivaDescOrg").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$("#txtIngOrgDelictivaDescOrg").attr("disabled","");
		}
 	}
</script>

<!-- Tabla INGRESAR ORGANIZACION DELICTIVA-->
   <table id="iIngresarOrgDelictivaWorkSheet" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="30">Descripci&oacute;n Organizaci&oacute;n: </td>
        <td >
        	<input id="txtIngOrgDelictivaDescOrg" title="Escribir descripcion organizacion" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
   </table>
<!-- FIN Tabla INGRESAR ORGANIZACION DELICTIVA-->
