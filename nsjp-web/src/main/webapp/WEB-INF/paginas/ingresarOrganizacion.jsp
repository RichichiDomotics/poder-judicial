
 <script type="text/javascript">

 	 var idWindowIngContOrganizacion = 1;
 	 var tipoOrganizacion=0;
 	 var idWindowIngresarRepLegal=1; 
 	/*
	*Funcion que dispara el Action para consultar el Tipo de Organizacion
	*/		
	function cargaOrganizacion(){ 
		$("#cbxIngOrgTiposOrganizacion").change(ocultaTrsTipoOrganizacion);
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/ConsultarCatalogosIngOrganizacion.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var option;
				$('#cbxIngOrgTiposOrganizacion').append('<option value="-1">- Seleccione -</option>');
				$(xml).find('catTipoOrganizacion').each(function(){
					$('#cbxIngOrgTiposOrganizacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
				//mandamos llenar los combos de cado de los tipos de organizacion
				cargaTiposOrganizacionFormal(xml);
				cargaNivelDependencias(xml);
				cargaTiposComunidadVirtual(xml);
			}
		});
//		$("#cbxIngOrgTiposOrganizacion").multiselect({ 
//			multiple: false, 
//			header: "Seleccione", 
//			position: { 
//				my: 'bottom', 
//				at: 'bottom' 
//			},
//			selectedList: 1,
//			height: "auto",
//			close: function (event,ui){
//				ocultaTrsTipoOrganizacion();} 
//		});
		$("#cbxIngOrgTiposOrganizacion").change(function(e){
				ocultaTrsTipoOrganizacion();
			});
		$("#iIngOrgBtnIngresarContacto").click(creaNuevoContactoOrganizacion);
		$("#iIngOrgBtnModificarDatos").click(validaDatosGeneralesOrganizacion);
		//ocultamos los TRs de los tipos de organizacion
		$("#trOrganizacionFormal").css("display", "none");
		$("#trOrganizacionNoFormal").css("display", "none");
		$("#trOrganizacionDelictiva").css("display", "none");
		$("#trOrganizacionSectorPublico").css("display", "none");
		$("#trOrganizacionVirtual").css("display", "none");
	}

	/*
  	*Funcion que crea una nueva ventana de un contacto de una organizacion
	*/	
	function creaNuevoContactoOrganizacion() {
	  //idWindowIngContOrganizacion++;
	  $.newWindow({id:"iframewindowIngContOrganizacion", statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Contacto De Una Organizacion", type:"iframe"});
	  $.updateWindowContent("iframewindowIngContOrganizacion",'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do?numeroExpediente='+numeroExpediente +'&idOrganizacion='+idOrganizacion+'&idUsuario=0 " width="1050" height="600" />');		
	}	
    
	/*
	*Funcion para validar los datos generales de una organizacion
	*/
	function validaDatosGeneralesOrganizacion(){
		$('#anularInvolucrado').show();
	  var bandera=false;
	  var idTipoOrganizacion=-1;
	  idTipoOrganizacion=	$("#cbxIngOrgTiposOrganizacion option:selected").val();
	  //if(idTipoOrganizacion!=-1){
		  //if(banderaDenuncianteQuerellante==0){
			  //es probable responsable como Denunciante
			  //if(banderaDenunciaQuerella==1){
				  //es denuncia
				  
			  //}
			  //else{
				  //es querella
			  //}
		//  }
		 // else{
			//revisamos la obligatoriedad de nombre de la organizacion
		//	if(txtIngOrgNombreOrg.html().length>0){
		//		bandera=true;
		//	}	
		 // }
	  //}
	  avilitarDatosDomicilio();
	  switch(tipoOrganizacion)
		{
			case 230://Formal
				habilitaDeshabilitaCamposOrganizacionFormal(1);
		  		break;
			case 231://No Formal
				habilitaDeshabilitaCamposOrganizacionNoFormal(1);
				break;
			case 232://Comunidad Virtual
				habilitaDeshabilitaCamposOrganizacionVirtual(1);
			  	break;
			case 233://Sector Publico
				habilitaDeshabilitaCamposOrganizacionSectorPublico(1);
			  	break;
			case 234://Delictiva
				habilitaDeshabilitaCamposOrganizacionDelictiva(1);
			  	break;
		}
	  habilitaDeshabilitaCamposIngOrganizacion(1);
	  return bandera;
	}

	/*
	*Metodo para habilitar o deshabilitar los campos del maestro de Organizacion
	*bandera = 0 para deshabilitar, bandera=1 para habilitar los campos
	*/
	function habilitaDeshabilitaCamposIngOrganizacion(bandera)
	{
		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura
			$("#txtIngOrgNombreOrg").attr("disabled","disabled");
			$("#txtIngOrgNombreCortoOrg").attr("disabled","disabled");
			$("#txtIngOrgDirInternet").attr("disabled","disabled");
			$("#cbxIngOrgTiposOrganizacion").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$("#txtIngOrgNombreOrg").attr("disabled","");
			$("#txtIngOrgNombreCortoOrg").attr("disabled","");
			$("#txtIngOrgDirInternet").attr("disabled","");
			//$("#cbxIngOrgTiposOrganizacion").attr("disabled","");
		}
	}

	/*
	*Funcion para pintar los datos provenientes de la consulta de una organizacion
	*/
	function seteaDatosPersonaMoralConsOrg(xml)
	{
		
		//seteo los valores en los campos
		$("#txtIngOrgNombreOrg").val($(xml).find('organizacionDTO').find('nombreOrganizacion').text());
		$("#txtIngOrgNombreCortoOrg").val($(xml).find('organizacionDTO').find('nombreCorto').text());
		$("#txtIngOrgDirInternet").val($(xml).find('organizacionDTO').find('direccionInternet').text());
		var tipoOrga=$(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text();
		$('#cbxIngOrgTiposOrganizacion').find("option[value='"+tipoOrga+"']").attr("selected","selected");
		ocultaTrsTipoOrganizacion();
		//mandamos a setear la informacion dependiendo del tipo de organizacion
		switch(parseInt($(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text()))
		{
			case 230://Formal
				seteaInformacionOrganizacionFormal(xml);
		  		break;
			case 231://No Formal
				seteaInformacionOrganizacionNoFormal(xml);
				break;
			case 232://Comunidad Virtual
				seteaInformacionOrganizacionVirtual(xml);
			  	break;
			case 233://Sector Publico
				seteaInformacionOrganizacionSectorPublico(xml);
			  	break;
			case 234://Delictiva
				seteaInformacionOrganizacionDelictiva(xml);
			  	break;
		}
		tipoOrganizacion=parseInt($(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text());
		//deshabilitamos los campos de la informacion maestra
		habilitaDeshabilitaCamposIngOrganizacion(0)
	}
	
	/*
	*Funcion listener del combobox de Tipos de Organizacion
	*/
	function ocultaTrsTipoOrganizacion()
	{
		var selected = $("#cbxIngOrgTiposOrganizacion option:selected");
		//oculto todas
		$("#trOrganizacionFormal").css("display", "none");
		$("#trOrganizacionNoFormal").css("display", "none");
		$("#trOrganizacionDelictiva").css("display", "none");
		$("#trOrganizacionSectorPublico").css("display", "none");
		$("#trOrganizacionVirtual").css("display", "none");
		switch(parseInt(selected.val()))
		{
			case 230://Formal
				limpiaCamposOrganizacionFormal();
				$("#trOrganizacionFormal").css("display", "block");
		  		break;
			case 231://No Formal
				limpiaCamposOrganizacionNoFormal();
				$("#trOrganizacionNoFormal").css("display", "block");
				break;
			case 232://Comunidad Virtual
				limpiaCamposOrganizacionVirtual();
				$("#trOrganizacionVirtual").css("display", "block");
			  	break;
			case 233://Sector Publico
				limpiaCamposOrganizacionSectorPublico();
				$("#trOrganizacionSectorPublico").css("display", "block");
			  	break;
			case 234://Delictiva
				limpiaCamposOrganizacionDelictiva();
				$("#trOrganizacionDelictiva").css("display", "block");
				break;
		}
	}
	  
	
	/*
	*Funcion para extraer los datos provenientes del ingreso de una organizacion
	*/
	function extraeDatosPersonaMoralIngOrg()
	{
		var datosIngresarOrganizacion="";
		
		//obtenemos los valores de la seccion maestra
		datosIngresarOrganizacion = datosIngresarOrganizacion +'nombreOrg=' +$("#txtIngOrgNombreOrg").val();
		datosIngresarOrganizacion = datosIngresarOrganizacion +'&nombreCortoOrg=' +$("#txtIngOrgNombreCortoOrg").val();
		datosIngresarOrganizacion = datosIngresarOrganizacion +'&orgDirInternet=' +$("#txtIngOrgDirInternet").val();
				
		var selected = $("#cbxIngOrgTiposOrganizacion option:selected");
		datosIngresarOrganizacion = datosIngresarOrganizacion +'&tipoOrg='+selected.val(); 
		
		//extraemos los datos dependiento del tipo de orgnizacion
		switch(parseInt(selected.val()))
		{
			case 230://Formal
				datosIngresarOrganizacion = datosIngresarOrganizacion + recuperaInformacionOrganizacionFormal();
		  		break;
			case 231://No Formal
				datosIngresarOrganizacion = datosIngresarOrganizacion + recuperaInformacionOrganizacionNoFormal();
				break;
			case 232://Comunidad Virtual
				datosIngresarOrganizacion = datosIngresarOrganizacion + recuperaInformacionOrganizacionVirtual();
				break;
			case 233://Sector Publico
				datosIngresarOrganizacion = datosIngresarOrganizacion + recuperaInformacionOrganizacionSectorPublico();
			  	break;
			case 234://Delictiva
				datosIngresarOrganizacion = datosIngresarOrganizacion + recuperaInformacionOrganizacionDelictiva();
			  	break;
		}
		return datosIngresarOrganizacion;
	}
	
	function deshabilitaBotonContacto()
	{
		$("#iIngOrgBtnIngresarContacto").attr("disabled","disabled");
	}
	
	function deshabilitaBotonRepresentante()
	{
		$("#btnIngOrgFormalDatosRep").attr("disabled","disabled");
	}
	//Función que permite la carga y moficacion de un representante legal
	function cargaRepLegal(nombre,id){
		var row=$('#'+id);		
		$(row).remove();
		$("#etiquetaRepLegal").remove();
		$('#idRepLegal').append('<tr id="etiquetaRepLegal"><td>Representante Legal:</td></tr><tr id="'+id+'"><td class="noSub"><a id="consultarRepLegal" onclick="modificaRepLegal('+id+')">&nbsp;&nbsp;&nbsp;&nbsp;'+nombre+'</a></td></tr>');
	} 
	function modificaRepLegal(id) {
			idWindowIngresarRepLegal++;
			$.newWindow({id:"iframewindowIngresarRepLegal" + idWindowIngresarRepLegal, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Representante Legal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarRepLegal" + idWindowIngresarRepLegal,'<iframe src="<%=request.getContextPath()%>/IngresarRepresentanteLegal.do?numeroExpediente='+numeroExpediente+ '&idDenunciante='+ id+ '&idOrganizacion='+idOrganizacion+'&idUsuario=0&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');		
	}
	
</script>

<!-- Tabla INGRESAR ORGANIZACION -->
          <table id="iIngresarOrgWorkSheet"  height="365" class="back_obj" width="100%"  border="0">
            <tr valign="top">
              <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="72%" height="130" align="left" valign="top" >
                      <table   width="80%" align="center" height="100" border="0" cellpadding="0"  cellspacing="0" >
                        <tr>
                          <td width="36%"  height="35">Tipo de Organización: </td>
                          <td height="14%">
                            <select id="cbxIngOrgTiposOrganizacion">
                            </select>
                          </td>
                          <td width="36%" height="28">Nombre corto:</td>
                          <td width="14%" height="28"><input id="txtIngOrgNombreCortoOrg" title="Escribir nombre corto organización" type="text" size="40" maxlength="30"/></td>
                        </tr>
                        <tr>
                          <td width="36%" height="30">Nombre de la Organización: </td>
                          <td width="14%"><input id="txtIngOrgNombreOrg" title="Escribir nombre" type="text" size="40" maxlength="60"/></td>
                          <td width="36%"  height="35">Dirección de Internet: </td>
                          <td height="14%"><input id="txtIngOrgDirInternet" title="Escribir dirección de internet" type="text" size="40" maxlength="150"/></td>
                        </tr>
                      </table>
                    </td>
                    <!-- td width="28%"  height="135">CALIDAD: Denunciante</td-->
                  </tr>
                </table>
              </td>
            </tr>	
            <tr valign="top">
              <td colspan="3">
                <table width="100%" >
                  <tr valign="top">
                    <td align="center">
                        <input style="display: none;" type="button" class="btn_mediano" value="Ingresar Contacto" id="iIngOrgBtnIngresarContacto"/>
                    </td>
                    <td align="center">
                        <input type="button" class="btn_modificar" value="Modificar Datos" id="iIngOrgBtnModificarDatos"/>
                        <input type="button" class="btn_guardar" value="Guardar" id="iIngOrgBtnGuardar"/>
                    </td>
                    
                    <td width="20%" align="left" id="idRepLegalTdDad">
						<table width="100%" cellpadding="0" cellspacing="0"	id="idRepLegal"></table>
	  				</td>
                    
                  </tr>
                </table>
              </td>
            </tr>
            <tr align="center" valign="top" id="trOrganizacionFormal">
            	<td width="100%">
            		<jsp:include page="ingresarOrganizacionFormal.jsp"/>
            	</td>
            </tr>
            <tr align="center" valign="top" id="trOrganizacionNoFormal">
            	<td width="100%">
            		<jsp:include page="ingresarOrganizacionNoFormal.jsp"/>
            	</td>
            </tr>				
            <tr align="center" valign="top" id="trOrganizacionDelictiva">
            	<td width="100%">
            		<jsp:include page="ingresarOrganizacionDelictiva.jsp"/>
            	</td>
            </tr>				
            <tr align="center" valign="top" id="trOrganizacionSectorPublico" style="display: none;">
            	<td>
            		<jsp:include page="ingresarOrganizacionSectorPublico.jsp"/>
            	</td>
            </tr>				
            <tr align="center" valign="top" id="trOrganizacionVirtual">
            	<td>
            		<jsp:include page="ingresarOrganizacionVirtual.jsp"/>
            	</td>
            </tr>								
            
          </table>
          <script type="text/javascript">
          	$("#iIngOrgBtnIngresarContacto,#btnIngOrgFormalDatosRep").attr("disabled","disabled");
          </script>
          <!-- FIN Tabla INGRESAR ORGANIZACION -->