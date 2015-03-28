<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarIndividuoServidorPublico.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">

	function recuperoDatosServidorPublico()
	{
		var datosServidorPublico="";
		var selected = $("#cmbServPublicoNivelDependencia option:selected");
		var selectedDos = $("#cmbServPublicoTipoDependencia option:selected");
		
		var selectedInt = $("#cmbServPublicoArea option:selected");
		var selectedDosInt = $("#cmbServPublicoDepto option:selected");
		var selectedTresInt = $("#cmbServPublicoNombreInt option:selected");
		
		
		var tipoServidorPublico = $(':radio[name=rdbTipoSerPublico]:checked').val();
		datosServidorPublico = datosServidorPublico +'&tipoServidorPublico='+tipoServidorPublico;
		if(tipoServidorPublico==0)//Interno
		{
			datosServidorPublico = datosServidorPublico +'&areaServPublico='+selectedInt.val(); 
			datosServidorPublico = datosServidorPublico +'&deptoServPublico='+selectedDosInt.val();
			datosServidorPublico = datosServidorPublico +'&nombreServPublicoInt=' +selectedTresInt.val();
			datosServidorPublico = datosServidorPublico +'&puestoServPublicoInt=' +$("#cmbServPublicoPuestoInt").val();
		}
		else if(tipoServidorPublico==1)//Externo
		{
			datosServidorPublico = datosServidorPublico +'&nivelDepServPublico='+selected.val(); 
			datosServidorPublico = datosServidorPublico +'&tipoDepServPublico='+selectedDos.val();
			datosServidorPublico = datosServidorPublico +'&dependenciaServPublico=' +$("#cmbServPublicoDependencia").val();
			datosServidorPublico = datosServidorPublico +'&puestoServPublico=' +$("#cmbServPublicoPuesto").val();
			datosServidorPublico = datosServidorPublico +'&numEpmleadoServPublico=' +$("#txtServPublicoNumEmpleado").val();
			datosServidorPublico = datosServidorPublico +'&nombreServPublicoExt=' +$("#txtServPublicoNombre").val();
			datosServidorPublico = datosServidorPublico +'&appaternoServPublicoExt=' +$("#txtServPublicoApPaterno").val();
			datosServidorPublico = datosServidorPublico +'&apmaternoServPublico=' +$("#txtServPublicoApMaterno").val();
		}
		else
		{
			customAlert("Debe seleccionar el tipo de Servidor Público");
		}
		return datosServidorPublico;
	}

	function pintaServidorPublico(xml){
		
		$('#rdbServPublicoExterno').attr("checked","checked");
		ocultaSeccionTipoServidor('1');
		var tipoSer=$(xml).find('servidorPublicoDTO').find('tipoDependencia').find('idCampo').text();
		var nivelSev=$(xml).find('servidorPublicoDTO').find('nivelDependencia').find('idCampo').text();
		$("#cmbServPublicoNivelDependencia").find("option[value='"+nivelSev+"']").attr("selected","selected");
		cargaCombo();
		$("#cmbServPublicoTipoDependencia").find("option[value='"+tipoSer+"']").attr("selected","selected");
		var dependencia=$(xml).find('servidorPublicoDTO').find('dependencia').text();
		var puesto=$(xml).find('servidorPublicoDTO').find('puesto').text();
		var numeroEmpleado=$(xml).find('servidorPublicoDTO').find('numeroEmpleado').text();
		$("#cmbServPublicoDependencia").val(dependencia);
		$("#cmbServPublicoPuesto").val(puesto);
		$("#txtServPublicoNumEmpleado").val(numeroEmpleado);

	}

	function cargaCombo(){
		var idnivelDepen=$("#cmbServPublicoNivelDependencia").val();
		if($("#cmbServPublicoNivelDependencia").val() == -1){
			
			$('#cmbServPublicoTipoDependencia').attr('selectedIndex',0);
			$('#cmbServPublicoTipoDependencia').attr('disabled','-1');
			$('#cmbServPublicoDependencias').attr('selectedIndex',0);
		    $('#cmbServPublicoDependencias').attr('disabled','-1');
		}else{
			$('#cmbServPublicoTipoDependencia').empty();
			$('#cmbServPublicoTipoDependencia').append('<option value="-1">-Seleccione-</option>');
			var retornaResultado = false;
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/CargarCatalogoTipoDependencia.do',
				data: 'nivelDependenciaServPublico=' +idnivelDepen,
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('catalogoDTO').each(function(){
						$('#cmbServPublicoTipoDependencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						retornaResultado = true;
					});
				}
			});
			if(retornaResultado){
				$('#cmbServPublicoTipoDependencia').removeAttr('disabled');
			}else{
				customAlert('No se encontraron resultados para esa selección');
			}
		}
	}
	
	function ocultaSeccionTipoServidor(id)
	{
		if(parseInt(id)==0)//Servidor Interno
		{
			$("#tableInterno").show();
			$("#tableExterno").hide();
		}
		else//Servidor Externo
		{
			$("#tableInterno").hide();
			$("#tableExterno").show();
		}
	}

	/*
	*Deshabilita los campos de servidor publico "Externo"
	*/
	function deshabilitaDatosServidorPublico(){

		$('#txtServPublicoNumEmpleado').attr("disabled","disabled");
		$('#cmbServPublicoPuesto').attr("disabled","disabled");
		$('#cmbServPublicoDependencia').attr("disabled","disabled");
		$('#cmbServPublicoTipoDependencia').attr("disabled","disabled");
		$('#cmbServPublicoNivelDependencia').attr("disabled","disabled");	
	}

	/*
	*Habilita los campos de servidor publico "Externo"
	*/
	function habilitaDatosServidorPublico(){

		$('#txtServPublicoNumEmpleado').attr("disabled","");
		$('#cmbServPublicoPuesto').attr("disabled","");
		$('#cmbServPublicoDependencia').attr("disabled","");
		$('#cmbServPublicoTipoDependencia').attr("disabled","");
		$('#cmbServPublicoNivelDependencia').attr("disabled","");	
	}
</script>
<table>
	<tr>
		<td>
		Tipo de Servidor Público: 
		<input type="radio" name="rdbTipoSerPublico" id="rdbServPublicoInterno" onclick="ocultaSeccionTipoServidor('0')" value="0" style="display:none;"/> Interno
		&nbsp;&nbsp;&nbsp;
		<input type="radio" name="rdbTipoSerPublico" id="rdbServPublicoExterno" onclick="ocultaSeccionTipoServidor('1')" value="1"/> Externo
		<input type="button" onclick="recuperoDatosServidorPublico();" value="prueba" style="display:none;" class="btn_Generico"/> 
		</td> 
	</tr>
</table>
<br/>
<table id="tableInterno">
    <tr>
	    <td>
		    &Aacute;rea : 
	    </td>
	    <td>
	    	<select id="cmbServPublicoArea" style="width: 200px;">						
				<option value="-1">-Seleccione-</option>
			</select>
	    </td>
	</tr>
    <tr>
	    <td>
		    Departamento : 
	    </td>
	    <td>
	    	<select id="cmbServPublicoDepto" style="width: 200px;">						
				<option value="-1">-Seleccione-</option>
			</select>
	    </td>
  	</tr>
	<tr>
	    <td>
		    Nombre : 
	    </td>
	    <td>
	    	<select id="cmbServPublicoNombreInt" style="width: 200px;">						
				<option value="-1">-Seleccione-</option>
			</select>
	    </td>
  	</tr>
  	<tr>
	    <td>
		    Cargo: 
	    </td>
	    <td>
	    	<input type="text" id="cmbServPublicoPuestoInt" size="38" >						
				
	    </td>
  	</tr>
</table>

<table id="tableExterno">
    <tr>
	    <td>
		    Ámbito de gobierno: 
	    </td>
	    <td>
	    	<select id="cmbServPublicoNivelDependencia" style="width: 200px;">					
				<option value="-1">-Seleccione-</option>
			</select>
	    </td>
	</tr>
    <tr>
	    <td>
		   Tipo de Dependencia/Institución:
	    </td>
	    <td>
	    	<select id="cmbServPublicoTipoDependencia" style="width: 200px;">						
				<option value="-1">-Seleccione-</option>
			</select>
	    </td>
  	</tr>
	<tr>
	    <td>
		    Dependencia/Institución de adscripción: 
	    </td>
	    <td>
	    	<input type="text" id="cmbServPublicoDependencia" size="38">						
				
	    </td>
  	</tr>
  	<tr>
	    <td>
		    Cargo: 
	    </td>
	    <td>
	    	<input type="text" id="cmbServPublicoPuesto" size="38" >						
				
	    </td>
  	</tr>
  	<tr>
	    <td>
		    Número de empleado: 
	    </td>
	    <td>
	    	<input type="text" id="txtServPublicoNumEmpleado" size="38"">
	    </td>
  	</tr>
  	  	<tr style="display:none;">
	    <td>
		    Nombre (s): 
	    </td>
	    <td>
	    	<input type="text" id="txtServPublicoNombre" size="38"">
	    </td>
  	</tr>
  	  	<tr style="display:none;">
	    <td>
		    Apellido Paterno: 
	    </td>
	    <td>
	    	<input type="text" id="txtServPublicoApPaterno" size="38"">
	    </td>
  	</tr>
  	  	<tr style="display:none;">
	    <td>
		    Apellido Materno: 
	    </td>
	    <td>
	    	<input type="text" id="txtServPublicoApMaterno" size="38"">
	    </td>
  	</tr>
</table>

<script type="text/javascript">
	inicializaServidorPublico('<%= request.getContextPath()%>');
	ocultaSeccionTipoServidor('0');
	/*Llenamos el combo de  area*/
	$('#cmbServPublicoArea').empty();
	$('#cmbServPublicoArea').append('<option value="-1">- Seleccione -</option>');
	$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoAreas.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
					$(xml).find('areas').each(function(){
						$('#cmbServPublicoArea').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
					});
				}
			});
	//refrescamos el combo de areas
	$("#cmbServPublicoArea").multiselect({ 
		multiple: false, 
		header: "Seleccione", 
		position: { 
			my: 'center', 
			at: 'center' 
		},
		selectedList: 1,
		height: "auto"
	});
	/*FIN Llenamos el combo de  area*/
	/* Llenamos el combo de departamentos*/
	$('#cmbServPublicoDepto').empty();
	$('#cmbServPublicoDepto').append('<option value="-1">- Seleccione -</option>');
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/consultarCatalogoDepartamentos.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
					$(xml).find('departamentos').each(function(){
						$('#cmbServPublicoDepto').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
					});
				}
			});
	//refresco el combo de departamentos
	$("#cmbServPublicoDepto").multiselect({ 
		multiple: false, 
		header: "Seleccione", 
		position: { 
			my: 'center', 
			at: 'center' 
		},
		selectedList: 1,
		height: "auto"
	});
	/*FIN  Llenamos el combo de departamentos*/
	/*$("#cmbServPublicoNivelDependencia").multiselect({ 
		multiple: false, 
		header: "Seleccione", 
		position: { 
			my: 'center', 
			at: 'center' 
		},
		selectedList: 1,
		height: "auto"
	});
	$("#cmbServPublicoTipoDependencia").multiselect({ 
		multiple: false, 
		header: "Seleccione", 
		position: { 
			my: 'center', 
			at: 'center' 
		},
		selectedList: 1,
		height: "auto"
	});*/
	$("#tableInterno,#tableExterno").hide();
</script>