
 <script type="text/javascript">
	var idWindowModificarRepresentanteLegal=1;
	
 	/*
 	*Funcion para cargar los tipos de organizacion formal
 	*/
 	function cargaTiposOrganizacionFormal(xml)
 	{
 		$("#btnIngOrgFormalDatosRep").click(onClickIngOrgFormalDatosRepresentante);
 		$('#cbxIngOrgFormalTiposOrganizacion').append('<option value="-1">- Seleccione -</option>');
		$(xml).find('catTipoOrganizacionFormal').each(function(){
			$('#cbxIngOrgFormalTiposOrganizacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		});
 	}
 	
 	/*
 	*Funcion para obtener los datos de la organizacion No formal
 	*/
 	function recuperaInformacionOrganizacionFormal()
	{
		var datosOrgFormal="";
		var selected = $("#cbxIngOrgFormalTiposOrganizacion option:selected");
		datosOrgFormal = datosOrgFormal +'&tipoOrgFormal='+selected.val(); 
		datosOrgFormal = datosOrgFormal +'&noActaOrgFormal=' +$("#txtIngOrgFormalNoActa").val();
		datosOrgFormal = datosOrgFormal +'&rfcOrgFormal=' +$("#txtIngOrgFormalRFC").val();
		datosOrgFormal = datosOrgFormal +'&giroOrgFormal=' +$("#txtIngOrgFormalGiro").val();
		//datosOrgFormal = datosOrgFormal +'&datosRepOrgFormal=' +$("#txtIngOrgFormalDatosRep").val();
		return datosOrgFormal;
	}
	
 	/*
 	*Funcion para pintar los datos de la organizacion No formal
 	*/
	function seteaInformacionOrganizacionFormal(xml)
	{
		//seteamos la infromacion
 		$('#cbxIngOrgFormalTiposOrganizacion').find("option[value='"+$(xml).find('organizacionDTO').find('valorByOrganizacionFormalVal').find('idCampo').text()+"']").attr("selected","selected");
 		$("#txtIngOrgFormalNoActa").val($(xml).find('organizacionDTO').find('numeroActaConstitutiva').text());
		$("#txtIngOrgFormalRFC").val($(xml).find('organizacionDTO').find('rfc').text());
		$("#txtIngOrgFormalGiro").val($(xml).find('organizacionDTO').find('giro').text());
		//$("#txtIngOrgFormalDatosRep").val('');
		//bloqueamos los campos
		habilitaDeshabilitaCamposOrganizacionFormal(0);
	}
 	
 	/*
 	*Metodo para limpiar los campos de Organizacion Formal
 	*/
 	function limpiaCamposOrganizacionFormal()
 	{
 		$('#cbxIngOrgFormalTiposOrganizacion').find("option[value='-1']").attr("selected","selected");
		$("#txtIngOrgFormalNoActa").val('');
		$("#txtIngOrgFormalRFC").val('');
		$("#txtIngOrgFormalGiro").val('');
		//$("#txtIngOrgFormalDatosRep").val('');
 	}
 	
 	/*
 	*Metodo para bloquear o desbloquear los campos de Organizacion Formal
 	*bandera = 0  para bloquear
 	*bandera = 1 para desbloquear
 	*/
 	function habilitaDeshabilitaCamposOrganizacionFormal(bandera)
 	{
 		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura
			$('#cbxIngOrgFormalTiposOrganizacion').attr("disabled","disabled");
			$("#txtIngOrgFormalNoActa").attr("disabled","disabled");
			$("#txtIngOrgFormalRFC").attr("disabled","disabled");
			$("#txtIngOrgFormalGiro").attr("disabled","disabled");
			//$("#txtIngOrgFormalDatosRep").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$('#cbxIngOrgFormalTiposOrganizacion').attr("disabled","");
			$("#txtIngOrgFormalNoActa").attr("disabled","");
			$("#txtIngOrgFormalRFC").attr("disabled","");
			$("#txtIngOrgFormalGiro").attr("disabled","");
			//$("#txtIngOrgFormalDatosRep").attr("disabled","");
		}
 	}
 	
 	/*
 	* Evento listener para el click del boton para lanzar 
 	* el CU Representante Legal
 	*/
 	function onClickIngOrgFormalDatosRepresentante(id)
 	{
 		idWindowModificarRepresentanteLegal++;
		$.newWindow({id:"iframewindow" + idWindowModificarRepresentanteLegal, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Representante Legal", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowModificarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do?numeroExpediente='+numeroExpediente +'&idOrganizacion='+idOrganizacion+'&idUsuario=0&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');	
 	}
 	
 	
</script>

<!-- Tabla INGRESAR ORGANIZACION FORMAL-->
   <table id="iIngresarOrgFormalWorkSheet" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>            
        <td height="30">Tipo de la Organización: </td>
        <td >
        	<select id="cbxIngOrgFormalTiposOrganizacion">
          </select>
        </td>
        <td>&nbsp;
        </td>
        <td>&nbsp;
        </td>
      </tr>
      <tr>
        <td height="28">No. Acta Constitutiva: </td>
        <td height="28"><input id="txtIngOrgFormalNoActa" title="Escribir n&uacute;mero acta" type="text" size="25" maxlength="20"/></td>
        <td>RFC
        </td>
        <td><input id="txtIngOrgFormalRFC" title="Escribir RFC" type="text" size="12" maxlength="13"/>
        </td>
      </tr>
      <tr>
        <td height="35">Giro: </td>
        <td height="35"><input id="txtIngOrgFormalGiro" title="Escribir Giro" type="text" size="25" maxlength="60"/></td>
        <td>&nbsp;
        </td>
        <td>&nbsp;
        </td>
      </tr>
      <tr>
        <td height="35"><input id="btnIngOrgFormalDatosRep" type="button" value="Ingresar Representante Legal" class="btn_Generico"/></td>
        <td height="35" colspan="3">
        	<!-- <input id="txtIngOrgFormalDatosRep" title="Escribir Datos del Representante" type="text" size="55" maxlength="150"/> -->
        	&nbsp;
        </td>        
      </tr>
   </table>
<!-- FIN Tabla INGRESAR ORGANIZACION FORMAL-->
