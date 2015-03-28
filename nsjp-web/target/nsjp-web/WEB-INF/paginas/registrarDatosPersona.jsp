<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Datos Persona</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>


	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
			
	<!--para controlar las ventanas-->

<!--script para multi select-->
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>

<!--Scrip para el idioma del calendario-->
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<!--css ultrasist-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />

<!--css para widgets -->
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!-- css multiselect-->
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />

	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/autocomplete/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/autocomplete/jquery.ui.button.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/autocomplete/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/autocomplete/jquery.ui.position.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/autocomplete/jquery.ui.widget.js"></script>
	


</head>

<body>
<form id="profileForm" class="actionForm"  method="get">

    <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>



	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Datos del compareciente</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Domicilio</a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-1">
		    <table border="0" width="100%">
				<tr>
					<td  align="right">*Nombre(s):</td>
					<td  class="field">
						<input type="text" class="" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNombres" name="datosGeneralesCmpNombres"  value="" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
					</td>
					<td align="right">*Apellido Paterno:</td>
					<td>
						<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpApaterno" name="datosGeneralesCmpApaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
					</td>
					<td align="right">*Apellido Materno:</td>
					<td>
						<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpMaterno"  name="datosGeneralesCmpMaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
					</td>
				</tr>
				<tr>
					<td align="right">CURP:</td>
					<td>
						<input type="text" style="width: 180px;" maxlength="18" class="" id="datosGeneralesCmpCurp"  name="datosGeneralesCmpCurp"/>
					</td>
					<td align="right">RFC:</td>
					<td>
						<input type="text" class="" style="width: 180px;" maxlength="13" id="datosGeneralesCmpRfc" name="datosGeneralesCmpRfc"/>
					</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td  valign="top" align="right">*Nacionalidad:</td>
					<td >
						<select id="datosGeneralesCmpNacionalidad" name="datosGeneralesCmpNacionalidad" style="width: 182px;">
							<option value="">- Seleccione -</option>
						</select>
				    </td>
			        <td  align="right" valign="top">*Estado Civil:
					</td>
			        <td   valign="top"><select id="datosGeneralesCmpEstadoCivil" name="datosGeneralesCmpEstadoCivil"  style="width: 180px;">
			        		<option value="">- Seleccione -</option>
			        	</select>
					</td>
					<td width="11%" align="right">*Ocupaci&oacute;n:</td>
					<td width="45%">
						<select id="datosGeneralesCmpOcupacion"  name="datosGeneralesCmpOcupacion" style="width: 182px;">
							<option value="">- Seleccione -</option>
						</select>
				    </td>
				</tr>
				<tr>
					<td align="right">*Fecha Nacimiento:</td>
					<td>
						<input type="text" class="" style="width: 180px;" maxlength="3" id="idFechaDate" width="50px" name="idFechaDate">
					</td>
					<td align="right">*Edad:</td>
			        <td><input type="text" class="" style="width: 180px;" maxlength="3" id="datosGeneralesCmpEdad" name="datosGeneralesCmpEdad"/></td>
					<td align="right">*Sexo:   Masculino</td>
					<td><input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" checked="checked" />&nbsp;
						&nbsp;
						Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F"/>
					</td>
			  </tr>
				<tr>
					 <td   valign="top" align="left" colspan="6">Motivo de la solicitud de atención
					</td>
				</tr>
				<tr>
					<td valign="top" align="left" colspan="6">
			        
			          <textarea name="textMotivo" id="textMotivo" cols="165" rows="7"></textarea>
			       </td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						<div id="boton" ><input type="button" value="Generar Acta"  id="botonGenerarActa" onclick="generaOficioActa();"	class="btn_Generico"/> </div>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="tabsconsultaprincipal-2">
			<table width="762px" height="313px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td width="142" height="25" align="right">Pa&iacute;s:</td>
					<td width="200" height="25"><select id="cbxPais"
						style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></td>
					<td width="105" height="25" align="right">Calle o Avenida:</td>
					<td colspan="3" height="25"><input type="text" id="areaCalle"
						size="40" /></td>
				</tr>
				<tr>
					<td align="right" height="25">C&oacute;digo Postal:</td>
					<td height="25"><input type="text" id="codigoPostal" size="8"
						maxlength="5" /> <input type="button" value="Enviar"
						id="codigoPostalButton" class="btn_Generico"/></td>
					<td align="right" height="25">N&uacute;mero Ext.:</td>
					<td width="53" height="25" align="left"><input type="text"
						id="areaNumeroExterior" size="8" maxlength="8" /></td>
					<td width="120" height="25" align="right">N&uacute;mero Int.:</td>
					<td width="142" height="25" align="left"><input type="text"
						id="areaNumeroInterior" size="8" maxlength="8" /></td>
				</tr>
				<tr>
					<td height="25" align="right">Entidad Federativa:</td>
					<td height="25">
					<div id="divCbxEntFederativa"><select id="cbxEntFederativa"
						style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input value="" size="20" maxlength="15" type="text"
						id="entidadFederativa" /></td>
					<td height="25" align="right">Entre calle:</td>
					<td height="25" colspan="3"><input type="text"
						id="areaEntreCalle" size="40" /></td>
				</tr>
				<tr>
					<td height="25" align="right">Ciudad:</td>
					<td height="25">
					<div id="divCbxCiudad"><select id="cbxCiudad"
						style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input type="text" id="areaCiudad" /></td>
					<td height="25" align="right">y calle:</td>
					<td height="25" colspan="3"><input type="text" id="areaYCalle"
						size="40" /></td>
				</tr>
				<tr>
					<td height="25" align="right">Delegaci&oacute;n/Municipio:</td>
					<td height="25">
					<div id="divCbxDelegacionMunicipio"><select
						id="cbxDelegacionMunicipio" style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input type="text" id="areaDelegacionMunicipio" /></td>
					<td height="25" align="right">Calle conocida como:</td>
					<td height="25" colspan="3"><input type="text" id="areaAlias"
						size="40" /></td>
				</tr>
				<tr>
					<td height="25" align="right">Asentamiento o Colonia:</td>
					<td height="25">
					<div id="divCbxAsentamientoColonia"><select
						id="cbxAsentamientoColonia" style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input type="text" id="areaColonia" /></td>
					<td height="25" align="right">Edificio:</td>
					<td height="25" colspan="3"><input type="text" id="areaEdificio"
						size="40" /></td>
				</tr>
				<tr>
					<td height="25" align="right">Tipo de Asentamiento:</td>
					<td height="25">
					<div id="divCbxTipoAsentamiento"><select
						id="cbxTipoAsentamiento" style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input type="text" id="areaAsentamiento" readonly="readonly" /></td>
					<td height="25" align="right">Referencias:</td>
					<td height="25" colspan="3"><textarea id="areaReferencias"
						cols="45" rows="5" style="width: 240px; height: 50px;"></textarea></td>
				</tr>
				<tr>
					<td height="25" align="right">Tipo de Calle:</td>
					<td height="25">
					<div id="divCbxTipoCalle"><select id="cbxTipoCalle"
						style="width: 200px;">
						<option value="-1">-Seleccione-</option>
					</select></div>
					<input type="text" id="areaTipoCalle" readonly="readonly" /></td>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						<div id="boton2"><input type="button" value="Guardar"  id="botonGuarda" onclick="guardarDatosCiudadano();"	class="btn_Generico"/></div>
					</td>
				</tr>
			</table>
		</div>
	</div>

<input class="formButton" type="submit" value="Next"  tabindex="14" id="botonvalida" style="display: none;"/>
</form>

<form name="formaDocDirecto" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
	<input type="hidden" name="formaId" value="<%=request.getParameter("formaId")!=null?request.getParameter("formaId"):"" %>"/>
</form>
				
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
	

	$("#idFechaDate").datepicker({
		dateFormat: 'dd/mm/yy',
		yearRange: '1900:2100',
		changeMonth: true,
		changeYear: true,
		showOn: "button",
		buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
		buttonImageOnly: true			
	});
	cargaEstadoCivil();
	cargaOcupacion();
	cargaNacionalidad();
	//$("#botonGenerarActa").click(generarDocumentoSinCaso);
	//$("#botonGenerarActa").click(generaOficioActa());
	$( "#datosGeneralesCmpNacionalidad" ).combobox();
	$( "#datosGeneralesCmpOcupacion" ).combobox();
	$( "#datosGeneralesCmpEstadoCivil" ).combobox();

	//Se crean las tabs principales
	$("#tabsprincipalconsulta" ).tabs();

	
	
});

function generaOficioActa(){
	document.formaDocDirecto.submit();
}

/*
*Funcion que dispara el Action para consultar el Estado Civil
*/	
function cargaEstadoCivil(){
	//alert("cargarDefensor");
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/ConsultarListaEstadoCivil.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catEstadoCivil').each(function(){
				$('#datosGeneralesCmpEstadoCivil').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				//$('#datosGeneralesCmpEstadoCivil').multiselect('refresh');
			});
		}
	});
}

function cargaOcupacion(){
	//alert("cargaOcupacion");
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catOcupacion').each(function(){
			$('#datosGeneralesCmpOcupacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
			});
		}
	});
}



/*
*Funcion que dispara el Action para consultar Nacionalidad
*/		
function cargaNacionalidad(){
	  	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/ConsultarCatalogoNacionalidad.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catNacionalidad').each(function(){
				$('#datosGeneralesCmpNacionalidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
			});
		}
	});
}

/**
 *Limpia todos los campos de esta página
 */
function cleanDatosGenerales(){

	$('#datosGeneralesCmpApaterno').val("");
	$('#datosGeneralesCmpMaterno').val("");
	$('#datosGeneralesCmpNombres').val("");
	$('#datosGeneralesCmpCurp').val("");
	$('#datosGeneralesCmpRfc').val("");
	$('#newItem').val("");

	
	//limpia los datos de la ventana padre
	imprimeDatosPadre("","","");
	//Limpia todos los combo box
	
	
	$('#datosGeneralesCmpEstadoCivil').attr('selectedIndex',0);

	//combos  multi select
	$('#datosGeneralesCmpOcupacion').attr('selectedIndex',0);
	$('#datosGeneralesCmpNacionalidad').attr('selectedIndex',0);		
	
}

function recuperaDatosDatosGenerales(idCalidad)
{
	   var lsDatosGenerales="";
	   lsDatosGenerales="&nombre="+$("#datosGeneralesCmpNombres").val();
	   lsDatosGenerales+="&aPaterno="+$("#datosGeneralesCmpApaterno").val();
	   lsDatosGenerales+="&aMaterno="+$("#datosGeneralesCmpMaterno").val();
	   lsDatosGenerales+="&curp="+$("#datosGeneralesCmpCurp").val();
	   lsDatosGenerales+="&rfc="+$("#datosGeneralesCmpRfc").val();
	   lsDatosGenerales+="&ocupacion="+$("#datosGeneralesCmpOcupacion option:selected").val();
	   lsDatosGenerales+="&nacionalidad="+$("#datosGeneralesCmpNacionalidad option:selected").val();
	   lsDatosGenerales+="&estadoCivil="+$("#datosGeneralesCmpEstadoCivil option:selected").val();

	   var sexoT = $(':radio[name=rbtSexoDatosGenerales]:checked').val();
       lsDatosGenerales += '&sexo=' + sexoT;

       lsDatosGenerales+="&fechaNacimiento="+$("#idFechaDate").val();
	   lsDatosGenerales+="&edadAproximada="+$("#datosGeneralesCmpEdad").val();
	   lsDatosGenerales+="&motivo="+$("#textMotivo").val();
	   
	   return lsDatosGenerales;
}

//function pintaDatosGenerales(xml){
	// $('#datosGeneralesCmpNombres').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').text());
	// $('#datosGeneralesCmpApaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').text());
	// $('#datosGeneralesCmpMaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').text());
	// $('#datosGeneralesCmpCurp').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('curp').text());
	// $('#datosGeneralesCmpRfc').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('rfc').text());
	// if ($(xml).find('nombresDemograficoDTO').find('sexo').text() == "masculino"){
	//	$('#datosGeneralesCmpSexoM').attr('checked','checked');
	// }else{
	//	$('#datosGeneralesCmpSexoF').attr('checked','checked');
	// } 
//}



	 /**
	  * Muestra u oculta los combo box's o cajas de texto dependiendo
	  * de el país seleccionado tiene o no, entidades federativas.
	  * Esto para el domicilio.
	  */	
	  function hideControls(existenEntidades) {
		  
		if(existenEntidades == "si" ){
	      $('#divCbxEntFederativa').show();	
		  $('#divCbxCiudad').show();
		  $('#divCbxDelegacionMunicipio').show();
		  $('#divCbxAsentamientoColonia').show();
		  $('#divCbxTipoAsentamiento').show();
		  $('#divCbxTipoCalle').show();
		  $('#codigoPostalButton').show();
		  $('#entidadFederativa').hide();
		  $('#areaDelegacionMunicipio').hide();
		  $('#areaCiudad').hide();
		  $('#areaColonia').hide();
		  $('#areaAsentamiento').hide();
		  $('#areaTipoCalle').hide();
		}
		else{
	      $('#divCbxEntFederativa').hide();
		  $('#divCbxCiudad').hide();
		  $('#divCbxDelegacionMunicipio').hide();
		  $('#divCbxAsentamientoColonia').hide();
		  $('#divCbxTipoAsentamiento').hide();
		  $('#divCbxTipoCalle').hide();
		  $('#codigoPostalButton').hide();
		  $('#entidadFederativa').show();
		  $('#areaDelegacionMunicipio').show();
		  $('#areaCiudad').show();
		  $('#areaColonia').show();
		  $('#areaAsentamiento').show();
		  $('#areaTipoCalle').show();
		}			
	  }


	 /**
	  * Limpia todo el formulario
	  *	(AUN SIN FUNCIONALIDAD)
	  */	
	  function limpiarFormulario(){
		  
		$('#ingresarDomicilioForm').each (function() { this.reset(); });
		cleanAllCombos();
		hideControls("no"); 
		cleanAllCombosNotif();
		hideControlsNotif("no");  	
	  }


	 /**
	  * Limpia los combos:Ent Federativa, Ciudad, DelegacionMunicipio, AsentamientoColonia
	  * Tipo de Asentamiento y Tipo de Calle, para el Domicilio
	  */  
	  function cleanAllCombos(){
		  
		$('#cbxEntFederativa').multiselect().empty();
		$('#cbxEntFederativa').multiselect().append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
		$('#cbxEntFederativa').multiselect('refresh');
		$('#cbxCiudad').multiselect().empty();
		$('#cbxCiudad').multiselect().append('<option value="-1">-Seleccione-</option>');	
		$('#cbxCiudad').multiselect('refresh');
		$('#cbxDelegacionMunicipio').multiselect().empty();
		$('#cbxDelegacionMunicipio').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxDelegacionMunicipio').multiselect('refresh');
		$('#cbxAsentamientoColonia').multiselect().empty();
		$('#cbxAsentamientoColonia').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').multiselect('refresh');
		$('#cbxTipoAsentamiento').multiselect().empty();
		$('#cbxTipoAsentamiento').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxTipoAsentamiento').multiselect('refresh');
		$('#cbxTipoCalle').multiselect().empty();
		$('#cbxTipoCalle').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxTipoCalle').multiselect('refresh');
	  }
	  

	 /**
	  * Limpia los combos que dependen del combo entidad federativa, para 
	  * el domicilio
	  */  
	  function cleanCombosDependsEntidadFed(){
		$('#cbxCiudad').multiselect().empty();
		$('#cbxCiudad').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxCiudad').multiselect('refresh');
		$('#cbxDelegacionMunicipio').multiselect().empty();
		$('#cbxDelegacionMunicipio').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxDelegacionMunicipio').multiselect('refresh');
		$('#cbxAsentamientoColonia').multiselect().empty();
		$('#cbxAsentamientoColonia').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').multiselect('refresh');
	  }
		

	 /**
	  * Limpia los combos que dependen del combo Ciudad, para 
	  * el domicilio
	  */  	
	  function cleanCombosDependsCiudad(){
		$('#cbxDelegacionMunicipio').multiselect().empty();
		$('#cbxDelegacionMunicipio').multiselect().append('<option value="-1">-Seleccione-</option>');	
		$('#cbxDelegacionMunicipio').multiselect('refresh');
		$('#cbxAsentamientoColonia').multiselect().empty();
		$('#cbxAsentamientoColonia').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').multiselect('refresh');
		  }


	 /**
	  * Limpia los combos que dependen del combo Delegacion/Municipio, para 
	  * el domicilio
	  */ 	
	  function cleanCombosDependsDelegMun(){
		$('#cbxAsentamientoColonia').multiselect().empty();
		$('#cbxAsentamientoColonia').multiselect().append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').multiselect('refresh');
		  }


	 /**
	  * Limpia los combos que dependen de la consulta por codigo postal
	  * para el domicilio
	  */ 	
	  function cleanAllCombosCodigoPostal(){
		$('#cbxEntFederativa').empty();
		$('#cbxCiudad').empty();
		$('#cbxDelegacionMunicipio').empty();
		$('#cbxAsentamientoColonia').empty();
	  }


	/**
	*Funcion que limpia el combo box de asentamiento colonia
	*/
	function cleanComboAsentColonia(){
		$('#cbxAsentamientoColonia').multiselect().empty();
		$('#cbxAsentamientoColonia').multiselect().append('<option value="-1">-Seleccione-</option>');
	}

	/*
	*Funcion que realiza la carga del combo de Paises
	*/
	function cargaPaises() {
		 
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarPaises.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catPaises').each(function(){
					$('#cbxPais').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxPais').multiselect('refresh');
					});
			}
		});
	}

	/**
	* Si existe un cambio en el combo de paises se realiza la consulta de 
	* entidades federativas, y si la consulta es NO vacía se leventa la 
	* bandera para mostrar los combo box. Esto para el domicilio
	*/ 	
	function onSelectChangePais() {
		  
		var selected = $("#cbxPais option:selected");
		var existenEntidades = "no";
			
				
		cleanAllCombos();							//Limpia todos los combo box´s		
		hideControls(existenEntidades);				//Si la opcion seleccionada no contiene entidades federativas se esconden los cbx's
		$.ajax({
			async: false,									// la accion cargar estados y llena el combo con la consulta
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarEntFederativas.do',
			data: 'glCatPaisId=' + selected.val(),	//Parametro para hacer la consulta de Entidades por Id del País
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catEntidadesFed').each(function(){
					$('#cbxEntFederativa').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxEntFederativa').multiselect('refresh');
					existenEntidades = "si";	
				});
				hideControls(existenEntidades);	
				cargaTipoAsent();
				cargaTipoCalle();
			}
		});
	}


	/**
	* Si existe un cambio en el combo de Entidades federativas se realiza la consulta de 
	* entidades Ciudades. Esto para el domicilio
	*/ 	
	function onSelectChangeEntFed() {
			  
		var selected = $("#cbxEntFederativa option:selected");
						
		cleanCombosDependsEntidadFed();
		$.ajax({											// la accion cargar cidades
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarCiudades.do',
			data: 'glCatEntidadFederativaId=' + selected.val(), 	//hace la consulta por el id de la Entidad Federativa
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catCiudades').each(function(){											//LLena el comboBox con la consulta
					$('#cbxCiudad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxCiudad').multiselect('refresh');
						});
			}
		});
		onSelectChangeCiudad();				
	}


	/**
	* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
	* Delegaciones/Municipios.
	*/ 	
	function onSelectChangeCiudad() {
			    
		var selected = $("#cbxEntFederativa option:selected");
		  
		//cleanCombosDependsCiudad();
		$.ajax({
			async: false,														// Ejecuta la accion cargar Delegacion/Municipio
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarDelgMun.do',
			data: 'glCatEntidadFederativaId=' + selected.val(), 				//hace la consulta por el id de la Entidad Federativa
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catDelegMun').each(function(){				//LLena el comboBox con la consulta
					$('#cbxDelegacionMunicipio').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					$('#cbxDelegacionMunicipio').multiselect('refresh');
					});
			}	
		});
	}	  

	/**
	* Si existe un cambio en el combo de Ciudade, delegacion, o tipo de asentamiento
	* se realiza la consulta de por medio de esos tres id´s 
	*/ 	
	function onSelectChangeCiudadMunicipioTipoAsentamiento() {

		var parametrosConsulta ='';
			  
		parametrosConsulta += 'glDelgMunId='+ $("#cbxDelegacionMunicipio option:selected").val();
		parametrosConsulta += '&glCatCiudadId=' + $("#cbxCiudad option:selected").val();
		parametrosConsulta += '&glCatTipoAsentamientoId=' + $("#cbxTipoAsentamiento option:selected").val();

		//alert(parametrosConsulta);
		cleanComboAsentColonia();
		$.ajax({
			async: false,														// Ejecuta la accion cargar Delegacion/Municipio
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
			data: parametrosConsulta,
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
					$('#cbxAsentamientoColonia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					$('#cbxAsentamientoColonia').multiselect('refresh');
					});
			}
		});
	}


	/**
	* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
	* Delegaciones/Municipios.
	*/ 	
	function onSelectChangeDelgMun() {
		  
		var selected = $("#cbxDelegacionMunicipio option:selected");
		    
		cleanCombosDependsDelegMun();
		$.ajax({														// Ejecuta la accion cargar Delegacion/Municipio
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
			data: 'glDelgMunId=' + selected.val(),						//Realiza la consulta de acuerdo al id de la delegacion o municipio
			dataType: 'xml',
			success: function(xml){
			  $(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
				$('#cbxAsentamientoColonia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
				$('#cbxAsentamientoColonia').multiselect('refresh');
				   });
			}
		});
	}


	/*
	*Funcion que realiza la carga del combo de tipo de asentamiento	
	*/
	function cargaTipoAsent() {	
	  
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarTipoAsent.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTipoAsentamiento').each(function(){
					$('#cbxTipoAsentamiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxTipoAsentamiento').multiselect('refresh');
					});  
		  	}
		});
	  }


	 /*
	  *Funcion que realiza la carga del combo de tipo de calle,
	  *para el domicilio 
	  */
	  function cargaTipoCalle() {
		
		$.ajax({
		  type: 'POST',
		  url: '<%= request.getContextPath()%>/cargarTiposDeCalle.do',
		  data: '',
		  dataType: 'xml',
		  success: function(xml){
			var option;
			$(xml).find('catTipoCalle').each(function(){
				$('#cbxTipoCalle').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				$('#cbxTipoCalle').multiselect('refresh');
					 });			
		  }
		});
	  }
	  
	  /*
	   *Funcion que realiza la consulta de los datos
	   *por código postal
	   *(SIN FUNCIONALIDAD POR EL MOMENTO)
	   */
	  function cosultaPorCodigoPostal(){
	  
		var codigoPostal = $("#codigoPostal");
		
		$.ajax({
		  type: 'POST',
		  url: '<%= request.getContextPath()%>/ConsultaDatosAsent.do',
		  data: 'codigoPostal='+ codigoPostal.val(),
		  dataType: 'xml',
		  success: function(xml){
			cleanAllCombosCodigoPostal();
			var oAsentamiento = $(xml);
			$('#cbxEntFederativa').append('<option value="' + oAsentamiento.find('entidadFederativaDTO').find('glCatEntidadFederativaId').text() + '">' + oAsentamiento.find('entidadFederativaDTO').find('gcAbreviacion').text() + '</option>');
			$('#cbxCiudad').append('<option value="' + oAsentamiento.find('ciudadDTO').find('glCiudadId').text() + '">' + oAsentamiento.find('ciudadDTO').find('gccbxCiudad').text() + '</option>');
			$('#cbxDelegacionMunicipio').append('<option value="' + oAsentamiento.find('municipioDTO').find('glMunicipioId').text() + '">' + oAsentamiento.find('municipioDTO').find('gccbxMunicipio').text() + '</option>');
			$('#cbxAsentamientoColonia').append('<option value="' + oAsentamiento.find('asentamientoDTO').find('glAsentamientoId').text() + '">' + oAsentamiento.find('asentamientoDTO').find('gccbxAsentamiento').text() + '</option>');
		  }
		});
	  }

	  function obtenerParametrosDomicilio(){
	        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
	        var IDPAIS_MEXICO = 10;
	        var idPais = $('#cbxPais option:selected').val();
	        //alert("idPais" + idPais);
	        var parametros = '&pais=' + idPais;
	        parametros += '&codigoPostal=' +  $('#codigoPostal').val();
	        //Cambiar por el id de Mexico
	        if(idPais==IDPAIS_MEXICO){        	
	            parametros += '&entidadFederativa=' + $('#cbxEntFederativa option:selected').val();
	            parametros += '&ciudad=' + $('#cbxCiudad option:selected').val();
	            parametros += '&delegacionMunicipio=' + $('#cbxDelegacionMunicipio option:selected').val();
	            parametros += '&asentamientoColonia=' + $('#cbxAsentamientoColonia option:selected').val();
	            parametros += '&tipoAsentamiento=' + $('#cbxTipoAsentamiento option:selected').val();
	            parametros += '&tipoCalle=' + $('#cbxTipoCalle option:selected').val();
	            //alert("tipo asentamiento:: "+$('#cbxTipoAsentamiento option:selected').val());
	        }else{
	            parametros += '&entidadFederativa=' + $('#entidadFederativa').val();
	            parametros += '&ciudad=' + $('#areaCiudad').val();
	            parametros += '&delegacionMunicipio=' + $('#areaDelegacionMunicipio').val();
	            parametros += '&asentamientoColonia=' + $('#areaColonia').val();
	            parametros += '&tipoAsentamiento=' + $('#areaAsentamiento').val();        
	            parametros += '&tipoCalle=' + $('#areaTipoCalle').val();
	        }

	        parametros += '&calle=' + $('#areaCalle').val();
	        parametros += '&numExterior=' + $('#areaNumeroExterior').val();
	        parametros += '&numInterior=' + $('#areaNumeroInterior').val();
	        parametros += '&referencias=' + $('#areaReferencias').val();
	        parametros += '&entreCalle=' + $('#areaEntreCalle').val();
	        parametros += '&ycalle=' + $('#areaYCalle').val();
	        parametros += '&aliasDomicilio=' + $('#areaAlias').val(); //ALIAS DE DOMICILIO?
	        parametros += '&edificio=' + $('#areaEdificio').val();

	        //var mismoDomicilioNotificaciones = $(':radio[name=rbtMismoDomicilioNotificaciones]:checked').val();
	        //parametros += '&mismoDomicilioNotificaciones=' + mismoDomicilioNotificaciones;

	        //alert('parametros en domicilio normal:' + parametros);
	        //alert('parametros en domicilio para notificaciones:' + parametros);
	        /*Faltan datos pendientes de esta pantalla como pais de nacimiento, estado y mnicipio, faltan loa alias ocupacion y nacionalidad*/
	      	return parametros;
		}

function generarDocumentoSinCaso() {
	$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Oficio/Acta", type:"iframe"});
    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId=<%=request.getParameter("formaId") %>" width="1140" height="400" />');
   		
}
		   
--></script>

<script>
	(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var self = this,
					select = this.element.hide(),
					selected = select.children( ":selected" ),
					value = selected.val() ? selected.text() : "";
				var input = this.input = $( "<input>" )
					.insertAfter( select )
					.val( value )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
							response( select.children( "option" ).map(function() {
								var text = $( this ).text();
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "<strong>$1</strong>" ),
										value: text,
										option: this
									};
							}) );
						},
						select: function( event, ui ) {
							ui.item.option.selected = true;
							self._trigger( "selected", event, {
								item: ui.item.option
							});
						},
						change: function( event, ui ) {
							if ( !ui.item ) {
								var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
									valid = false;
								select.children( "option" ).each(function() {
									if ( $( this ).text().match( matcher ) ) {
										this.selected = valid = true;
										return false;
									}
								});
								if ( !valid ) {
									// remove invalid value, as it didn't match anything
									$( this ).val( "" );
									select.val( "" );
									input.data( "autocomplete" ).term = "";
									return false;
								}
							}
						}
					})
					.addClass( "ui-widget ui-widget-content ui-corner-left" );

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};

				this.button = $( "<button type='button'>&nbsp;</button>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Show All Items" )
					.insertAfter( input )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "ui-corner-right ui-button-icon" )
					.click(function() {
						// close if already visible
						if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
							input.autocomplete( "close" );
							return;
						}

						// work around a bug (likely same cause as #5265)
						$( this ).blur();

						// pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
						input.focus();
					});
			},

			destroy: function() {
				this.input.remove();
				this.button.remove();
				this.element.show();
				$.Widget.prototype.destroy.call( this );
			}
		});
	})( jQuery );



	/**
	* Función que guarda los datos de la pantalla Registrar datos ciudadano
	*/
	function guardarDatosCiudadano(){
		var params = '';
		params += 'calidadDelIndividuo=4';

		//Datos generales, media filiacion, medios de contacto, documentos de identificacion
		var datosPersona = recuperaDatosDatosGenerales();
		var datosDomicilio = obtenerParametrosDomicilio();
		params += datosPersona;
		params += datosDomicilio;

		alert(params);
		
		$.ajax({								
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/guardarCiudadano.do',
	    	  data: params,				
	    	  dataType: 'xml',
	    	  success: function(xml){
	    		  cerrarVentana();
	    	  }
	    	});
	}

	function cerrarVentana(){
		parent.cerrarVentanaRegistrarDatosPersona();
	}

	
		/**
		*Carga los escuchadores de eventos
		*/
		cargaPaises();					//Carga el combo de paises

		/**
		*Carga los Combos con multiselect
		*/	 
		$("#cbxTipoAsentamiento, #cbxTipoCalle, #cbxAsentamientoColonia").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1 
		});
		$("#cbxPais").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangePais();}
		});
		$("#cbxEntFederativa").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'bottomr', 
				at: 'bottom' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeEntFed();}
		});
		
		$("#cbxCiudad").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeCiudadMunicipioTipoAsentamiento();}
		});

		$("#cbxDelegacionMunicipio").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeCiudadMunicipioTipoAsentamiento();}
		});

		$("#codigoPostalButton").bind("click",cosultaPorCodigoPostal);					//Escuchador para consultar pos código postal
		$("#limpiarButton").bind("click",limpiarFormulario);							//Funcion para limpiar el formulario

	 	hideControls("no");			//Funciones para esconder los controles 
		hideControlsNotif("no");	//Funciones para esconder los controles del domicilio de notificaciones
		
	</script>





