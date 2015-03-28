<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registrar Datos Persona</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<!--  <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery-ui.css" />-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />

<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		

</head>

<body>
<form id="profileForm" class="actionForm"  method="get">

 <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>
   
	<table border="0" width="600" cellspacing="5">
	<tr>
		<td align="center">Datos de la v&iacute;ctima</td>
		<td ></td>
		<td align="center">Datos de la causa</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td  align="right">Nombre(s):</td>
		<td  class="field">
			<input type="text" class="" style="width: 180px;background-color: #EEEEEE; border: 0;" maxlength="30" id="datosGeneralesCmpNombres" name="datosGeneralesCmpNombres"  value="" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
		</td>
		<td  align="right">Delito:</td>
		<td  class="field">
			<input type="text" class="" style="width: 80px;background-color: #EEEEEE; border: 0;" maxlength="30" id="txtDelito" name="txtDelito"  value="" />
		</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td align="right">Apellido Paterno:</td>
		<td>
			<input type="text" style="width: 180px;background-color: #EEEEEE; border: 0;" maxlength="30" class="" id="datosGeneralesCmpApaterno" name="datosGeneralesCmpApaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
		</td>
		<td align="right">Remitido por:</td>
		<td >
			<input type="text" style="width: 100px;background-color: #EEEEEE; border: 0;" maxlength="30" class="" id="txtRemitido" name="txtRemitido" />
		</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td align="right">Apellido Materno:</td>
		<td>
			<input type="text" style="width: 180px;background-color: #EEEEEE; border: 0;;" maxlength="30" class="" id="datosGeneralesCmpMaterno"  name="datosGeneralesCmpMaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
		</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td align="right">Delito:</td>
		<td>
			<input type="text" style="width: 180px;background-color: #EEEEEE; border: 0;" maxlength="18" class="" id="datosGeneralesCmpCurp"  name="datosGeneralesCmpCurp"/>
		</td>
		<td align="right"></td>
		<td>&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		 <td   valign="top" align="left" colspan="6">&Aacute;rea para redactar dictamen
		</td>
	</tr>
	<tr>
		<td valign="top" align="left" colspan="6">
        
          <textarea name="textMotivo" id="textMotivo" cols="101" rows="10" style="border: 0;background-color: #FFFFFF;" disabled="disabled"></textarea>
       </td>
	</tr>
	<tr>
		<td align="right" colspan="2">
			<div id="boton2"><input type="button" value="Agendar Cita"  id="botonGuarda"	class="btn_Generico"/></div>
		</td>
		<td align="center">
			<div id="boton" ><input type="button" value="Elaborar Evaluación"  id="botonGenerarActa" class="btn_Generico"/> </div>
		</td>
		<td align="left" colspan="3">
			<div id="boton3"><input type="button" value="Solicitar Apoyo de &#10; Instituci&oacute;n Externa"  id="btnSolApoyoIE"	class="btn_Generico"/></div>
		</td>
	</tr>
</table>
<input class="formButton" type="submit" value="Next"  tabindex="14" id="botonvalida" style="display: none;"/>
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
	$("#botonGenerarActa").click(generarDocumentoSinCaso);	

	
});

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
		success: function(xml){
			var option;
			$(xml).find('catEstadoCivil').each(function(){
				$('#datosGeneralesCmpEstadoCivil').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
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
	
	
	$('#datosGeneralesCmpIdioma').attr('selectedIndex',0);
	$('#datosGeneralesCmpReligion').attr('selectedIndex',0);
	$('#datosGeneralesCmpEscoralidad').attr('selectedIndex',0);
	$('#datosGeneralesCmpEstadoCivil').attr('selectedIndex',0);

	//combos  multi select
	$('#datosGeneralesCmpAlias').attr('selectedIndex',0);
	$('#datosGeneralesCmpOcupacion').attr('selectedIndex',0);
	$('#datosGeneralesCmpNacionalidad').attr('selectedIndex',0);		
	
	//$('#datosGeneralesCmpIdioma').empty();
	//$('#datosGeneralesCmpEstadoCivil').append('<option value="seleccione">-Seleccione-</option>');
	//$('#datosGeneralesCmpSexo').attr('checked', false);
}

function recuperaDatosDatosGenerales(idCalidad)
{
	   var lsDatosGenerales="";
	   lsDatosGenerales="nombre="+$("#datosGeneralesCmpNombres").val();
	   lsDatosGenerales+="&alias="+$("#datosGeneralesCmpAlias option:selected").val();
	   lsDatosGenerales+="&aPaterno="+$("#datosGeneralesCmpApaterno").val();
	   lsDatosGenerales+="&ocupacion="+$("#datosGeneralesCmpOcupacion option:selected").val();
	   lsDatosGenerales+="&aMaterno="+$("#datosGeneralesCmpMaterno").val();
	   lsDatosGenerales+="&nacionalidad="+$("#datosGeneralesCmpNacionalidad option:selected").val();
	   lsDatosGenerales+="&curp="+$("#datosGeneralesCmpCurp").val();
	   lsDatosGenerales+="&rfc="+$("#datosGeneralesCmpRfc").val();
	   lsDatosGenerales+="&fIngreso="+$("#datosGeneralesCmpFechaIngreso").val();
	   lsDatosGenerales+="&idioma="+$("#datosGeneralesCmpIdioma option:selected").val();
	   lsDatosGenerales+="&religion="+$("#datosGeneralesCmpReligion option:selected").val();
	   lsDatosGenerales+="&escolaridad="+$("#datosGeneralesCmpIdioma option:selected").val();
	   //lsDatosGenerales+="&estadocivil="+$("#datosGeneralesCmpReligion option:selected").val();
	   return lsDatosGenerales;
}

function pintaDatosGenerales(xml){
	 $('#datosGeneralesCmpNombres').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').text());
	 $('#datosGeneralesCmpApaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').text());
	 $('#datosGeneralesCmpMaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').text());
	 $('#datosGeneralesCmpCurp').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('curp').text());
	 $('#datosGeneralesCmpRfc').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('rfc').text());
	 if ($(xml).find('nombresDemograficoDTO').find('sexo').text() == "masculino"){
		$('#datosGeneralesCmpSexoM').attr('checked','checked');
	 }else{
		$('#datosGeneralesCmpSexoF').attr('checked','checked');
	 } 
	 $(xml).find('aliasInvolucradoDTO').each(function(){
	 $('#datosGeneralesCmpAlias').append('<option selected="selected" value="' + $(this).find('aliasInvolucradoId').text() + '">'+ $(this).find('alias').text() + '</option>');
	 }); 
}

function generarDocumentoSinCaso() {
	$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Acta", type:"iframe"});
    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do" width="1140" height="400" />');
   		
}
		   
</script>








