
 <script type="text/javascript">
 
 	/*
 	*Funcion para cargar los distintos niveles de dependencias del sector publico que existen
 	*/
 	function cargaNivelDependencias(xml)
 	{
 		$("#cbxIngOrgSecPublicoNivelDependencias").change(onSelectChangeNivelDependencia);
 		$("#cbxIngOrgSecPublicoTiposDependencias").change(onSelectChangeTipoDependencia);
 		lipiarComboPrincipalNivelDependencia();
		$(xml).find('catNivelesDependencias').each(function(){
			$('#cbxIngOrgSecPublicoNivelDependencias').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		});

		$("#cbxIngOrgSecPublicoNivelDependencias").change(onSelectChangeNivelDependencia);
		
		/*$("#cbxIngOrgSecPublicoTiposDependencias").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'center', 
				at: 'center' 
			},
			selectedList: 1,
			height: "auto",
			close: function (event,ui){
				onSelectChangeTipoDependencia();} 
		});

		$("#cbxIngOrgSecPublicoOrgsSecPublico").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'center', 
				at: 'center' 
			},
			selectedList: 1,
			height: "auto"
		});*/
 	}

 	/*
	*Funcion para obtener los datos de la organizacion No formal
	*/
	function recuperaInformacionOrganizacionSectorPublico()
	{
		var datosOrgSectorPublico="";
		var selectedNivelDep = $("#cbxIngOrgSecPublicoNivelDependencias option:selected");
		var selectedTipoDep = $("#cbxIngOrgSecPublicoTiposDependencias option:selected");
		var selectedOrgsSecPublico = $("#cbxIngOrgSecPublicoOrgsSecPublico option:selected");
		
		datosOrgSectorPublico = datosOrgSectorPublico +'&nivelDepOrgSectorPublico='+selectedNivelDep.val(); 
		datosOrgSectorPublico = datosOrgSectorPublico +'&tipoDepOrgSectorPublico='+selectedTipoDep.val();
		datosOrgSectorPublico = datosOrgSectorPublico +'&orgsSecPubOrgSectorPublico='+selectedOrgsSecPublico.val();
		datosOrgSectorPublico = datosOrgSectorPublico +'&areaOrgSectorPublico=' +$("#txtIngOrgSecPublicoNombreArea").val();
		
		return datosOrgSectorPublico;
	}
	
	/*
	*Funcion para pintar los datos de la organizacion No formal
	*/
	function seteaInformacionOrganizacionSectorPublico(xml)
	{
		//seteamos la informacion
		$('#cbxIngOrgSecPublicoNivelDependencias').find("option[value='"+$(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text()+"']").attr("selected","selected");
 		$('#cbxIngOrgSecPublicoTiposDependencias').find("option[value='"+$(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text()+"']").attr("selected","selected");
 		$('#cbxIngOrgSecPublicoOrgsSecPublico').find("option[value='"+$(xml).find('organizacionDTO').find('valorBySectorGubernamentalVal').find('idCampo').text()+"']").attr("selected","selected");
 		$("#txtIngOrgSecPublicoNombreArea").val($(xml).find('organizacionDTO').find('areaDeInfluencia').text());
 		
 		//Bloqueamos los campos
		habilitaDeshabilitaCamposOrganizacionSectorPublico(0);
	}
	
	/*
 	*Metodo para limpiar los campos de Organizacion Sector Publico
 	*/
 	function limpiaCamposOrganizacionSectorPublico()
 	{
 		$('#cbxIngOrgSecPublicoNivelDependencias').find("option[value='-1']").attr("selected","selected");
 		lipiarCombosDepNivelDependencia();
 		$("#txtIngOrgSecPublicoNombreArea").val('');
 	}
	
 	/*
 	*Metodo para bloquear o desbloquear los campos de Organizacion Formal
 	*bandera = 0  para bloquear
 	*bandera = 1 para desbloquear
 	*/
 	function habilitaDeshabilitaCamposOrganizacionSectorPublico(bandera)
 	{
 		if(parseInt(bandera)==0)
		{
			//bloqueo los campos de solo lectura			
			$('#cbxIngOrgSecPublicoNivelDependencias').attr("disabled","disabled");
	 		$('#cbxIngOrgSecPublicoTiposDependencias').attr("disabled","disabled");
	 		$('#cbxIngOrgSecPublicoOrgsSecPublico').attr("disabled","disabled");
	 		$("#txtIngOrgSecPublicoNombreArea").attr("disabled","disabled");
		}
		else
		{
			//habilito los campos 
			$('#cbxIngOrgSecPublicoNivelDependencias').attr("disabled","");
	 		$('#cbxIngOrgSecPublicoTiposDependencias').attr("disabled","");
	 		$('#cbxIngOrgSecPublicoOrgsSecPublico').attr("disabled","");
	 		$("#txtIngOrgSecPublicoNombreArea").attr("disabled","");
		}
 	}
 	
	/**
	* Si existe un cambio en el combo de Nivel de Dependencias 
	* Se cargaran los tipos de dependencias
	*/ 	
	function onSelectChangeNivelDependencia() {
		var selected = $("#cbxIngOrgSecPublicoNivelDependencias option:selected");
		//lipiarCombosDepNivelDependencia();
		if(parseInt(selected.val())!=-1)
		{	
			$.ajax({														// Ejecuta la accion cargar tipo de dependencia
				async: false,														
				type: 'POST', 
				url: '<%= request.getContextPath()%>/CargarTipoDependencia.do',		
				data: 'glNivelDependencia=' + selected.val(),			//Realiza la consulta de acuerdo al id del nivel de dependencia
				dataType: 'xml',
				success: function(xml){
				  $(xml).find('catTipoDependencias').each(function(){				//LLena el comboBox con la consulta
					$('#cbxIngOrgSecPublicoTiposDependencias').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					$("#cbxIngOrgSecPublicoTiposDependencias").multiselect('refresh');
					  });
				}
			});
		}

		/*$("#cbxIngOrgSecPublicoTiposDependencias").multiselect({ 
			multiple: false, 
			header: "Seleccione", o
			position: { 
				my: 'center', 
				at: 'center' 
			},
			selectedList: 1,
			height: "auto",
			close: function (event,ui){
				onSelectChangeTipoDependencia();} 
		});*/
		//$("#cbxIngOrgSecPublicoTiposDependencias").multiselect('refresh');
		
				
	}
	
	/** 
	* Si existe un cambio en el combo de Nivel de Tipo de dependencia 
	* Se cargaran los tipos de dependencias
	*/ 	
	function onSelectChangeTipoDependencia() {
		
		var selected = $("#cbxIngOrgSecPublicoTiposDependencias option:selected");
		lipiarCombosDepTipoDependencia();
		if(parseInt(selected.val())!=-1)
		{	
			$.ajax({														// Ejecuta la accion cargar tipo de dependencia
				async: false,														
				type: 'POST',
				url: '<%= request.getContextPath()%>/CargarOrgsSectorPublico.do',		
				data: 'glTipoDependencia=' + selected.val(),			//Realiza la consulta de acuerdo al id del nivel de dependencia
				dataType: 'xml',
				success: function(xml){
				  $(xml).find('catOrgsSectorPublico').each(function(){				//LLena el comboBox con la consulta
					$('#cbxIngOrgSecPublicoOrgsSecPublico').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
				   });
				}
			});
		}
		
	}
	
	/*
	*Metodo para limpiar los combos dependientes de Nivel de Dependencia
	*/
	function lipiarComboPrincipalNivelDependencia()
	{
		$('#cbxIngOrgSecPublicoNivelDependencias').empty();
		$('#cbxIngOrgSecPublicoNivelDependencias').append('<option value="-1">-Seleccione-</option>');
		lipiarCombosDepNivelDependencia();
	}
	
	/*
	*Metodo para limpiar los combos dependientes de Nivel de Dependencia
	*/
	function lipiarCombosDepNivelDependencia()
	{
		$('#cbxIngOrgSecPublicoTiposDependencias').empty();
		$('#cbxIngOrgSecPublicoTiposDependencias').append('<option value="-1">-Seleccione-</option>');
 		$('#cbxIngOrgSecPublicoOrgsSecPublico').empty();
 		$('#cbxIngOrgSecPublicoOrgsSecPublico').append('<option value="-1">-Seleccione-</option>');
	}
	
	/*
	*Metodo para limpiar los combos dependientes de Tipo de Dependencia
	*/
	function lipiarCombosDepTipoDependencia()
	{
 		$('#cbxIngOrgSecPublicoOrgsSecPublico').empty();
 		$('#cbxIngOrgSecPublicoOrgsSecPublico').append('<option value="-1">-Seleccione-</option>');
	}
</script>

<!-- Tabla INGRESAR ORGANIZACION SECTOR PUBLICO-->
   <table id="iIngresarOrgSecPublicoWorkSheet" width="100%" border="0" cellspacing="0" cellpadding="0">
   	  <tr>
        <td width="30%" height="30">Nivel de dependencia: </td>
        <td >
        	<select id="cbxIngOrgSecPublicoNivelDependencias">
          </select>
        </td>
        
      </tr>
      <tr>
        <td width="30%" height="30">Tipo de dependencia: </td>
        <td >
        	<select id="cbxIngOrgSecPublicoTiposDependencias">
          </select>
        </td>
      </tr>
       
 		<tr>
        <td width="30%" height="30">Organizaci&oacute;n Sector P&uacute;blico: </td>
        <td >
        	<select id="cbxIngOrgSecPublicoOrgsSecPublico">
          </select>
        </td>
      </tr>
      <tr>
        <td width="30%" height="30">Nombre &aacute;rea: </td>
        <td >
        	<input id="txtIngOrgSecPublicoNombreArea" title="Escribir area de la organizacion" type="text" size="90" maxlength="150"/>
        </td>
      </tr>
   </table>
<!-- FIN Tabla INGRESAR ORGANIZACION SECTOR PUBLICO-->