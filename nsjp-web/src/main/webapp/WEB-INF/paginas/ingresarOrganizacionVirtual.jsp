
 <script type="text/javascript">
 
 	/*
 	*Funcion para cargar los tipos de comunidad virtual existentes
 	*/
 	function cargaTiposComunidadVirtual(xml)
 	{
 		$('#cbxIngOrgVirtualTiposOrganizacion').append('<option value="-1">- Seleccione -</option>');
		$(xml).find('catTipoOrganizacionVirtual').each(function(){
			$('#cbxIngOrgVirtualTiposOrganizacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		});
 	}
 	
	/*
	*Funcion para obtener los datos de la organizacion No formal
	*/
	function recuperaInformacionOrganizacionVirtual()
	{
		var datosOrgVirtual="";
		var selected = $("#cbxIngOrgVirtualTiposOrganizacion option:selected");
		datosOrgVirtual = datosOrgVirtual +'&tipoOrgVirtual='+selected.val(); 
		datosOrgVirtual = datosOrgVirtual +'&dirElectronicaOrgVirtual=' +$("#txtIngOrgVirtualDirElectronica").val();
		datosOrgVirtual = datosOrgVirtual +'&propositoOrgVirtual=' +$("#txtIngOrgVirtualProposito").val();
		return datosOrgVirtual;
	}
	
	/*
	*Funcion para pintar los datos de la organizacion No formal
	*/
	function seteaInformacionOrganizacionVirtual(xml)
	{
		//seteamo la informacion
		$('#cbxIngOrgVirtualTiposOrganizacion').find("option[value='"+$(xml).find('organizacionDTO').find('valorByComunidadVirtualVal').first().find('idCampo').text()+"']").attr("selected","selected");
		$("#txtIngOrgVirtualDirElectronica").val($(xml).find('direccionInternet').text());
		$("#txtIngOrgVirtualProposito").val($(xml).find('propositoCiberespacio').text());
		//bloquemos los campos
		habilitaDeshabilitaCamposOrganizacionVirtual(0);
	}
	
	/*
 	*Metodo para limpiar los campos de Organizacion Virtual
 	*/
 	function limpiaCamposOrganizacionVirtual()
 	{
 		$('#cbxIngOrgVirtualTiposOrganizacion').find("option[value='-1']").attr("selected","selected");
		$("#txtIngOrgVirtualDirElectronica").val('');
		$("#txtIngOrgVirtualProposito").val('');
 	}
	
 	/*
 	*Metodo para bloquear o desbloquear los campos de Organizacion Formal
 	*bandera = 0  para bloquear
 	*bandera = 1 para desbloquear
 	*/
 	function habilitaDeshabilitaCamposOrganizacionVirtual(bandera)
 	{
 		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura			
			$('#cbxIngOrgVirtualTiposOrganizacion').attr("disabled","disabled");
			$("#txtIngOrgVirtualDirElectronica").attr("disabled","disabled");
			$("#txtIngOrgVirtualProposito").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$('#cbxIngOrgVirtualTiposOrganizacion').attr("disabled","");
			$("#txtIngOrgVirtualDirElectronica").attr("disabled","");
			$("#txtIngOrgVirtualProposito").attr("disabled","");
		}
 	}
</script>

<!-- Tabla INGRESAR ORGANIZACION COMUNIDAD VIRTUAL-->
   <table id="iIngresarOrgVirtualWorkSheet" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="30">Tipo  de comunidad virtual: </td>
        <td >
        	<select id="cbxIngOrgVirtualTiposOrganizacion">
          </select>
        </td>
      </tr>
      <tr>
        <td width="30%" height="30">Direcci&oacute;n de correo electr&oacute;nico: </td>
        <td >
        	<input id="txtIngOrgVirtualDirElectronica" title="Escribir direccion electronica" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
      <tr>
        <td width="30%" height="30">Prop&oacute;sito: </td>
        <td >
        	<input id="txtIngOrgVirtualProposito" title="Escribir proposito de la organizacion" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
   </table>
<!-- FIN Tabla INGRESAR ORGANIZACION COMUNIDAD VIRTUAL-->
<script>

</script>