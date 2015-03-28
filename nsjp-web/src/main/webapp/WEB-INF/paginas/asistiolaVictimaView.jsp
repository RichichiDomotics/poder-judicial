<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Entrevista Inicial</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<!--  <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery-ui.css" />-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>


</head>
<script type="text/javascript">
var tipoVictima;
var idSolicitud;
var asistencia;
$(document).ready(function() {
	tipoVictima=parent.resRad;
	idSolicitud='<%= request.getParameter("rowid")%>';
	if(parent.resRad == "1"){
		asistencia = "true";
	}else{
		asistencia = "false";
	}
	consultaNotasEvaluacion(idSolicitud);
	$("#dtnGuardarNotaEvaluacion").click(guardaNotaEvaluacion);
	$("#GuardarAgenda").hide();
	
	if(tipoVictima==1){
		$('#observacionFaltaInteres').css("display","none");
	}else{ 
		$('#tipoVictimaSegimiento,#objetotextarea,#tipoVictimaObjeto,#tipoVictimaAnalisis,#textareaAnalisis,#tipoVictimaPlan,#seguimientoText').css("display","none");
	}
//$("#tabsPrincipal").tabs();

	var config = {toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Table','HorizontalRule'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		],
	height:'350px'
	};

	$('.jquery_ckeditor').ckeditor(config);

	/*
	* Propiedades de las cajas de texto de Numero de sesion, de Numero de Expediente y Fecha
	*  para que su contenido no se modifique y que el expediente se muestre correctamente
	*/
	$('#nSesionEI').attr('readonly','readonly');
	$('#nExpedienteEI').attr('readonly','readonly');
	$('#nExpedienteEI').attr('size','25');
	$('#FechaEI').attr('readonly','readonly');

});


/*
 *Funcion para hacer la consulta de una entrevista inicial por ID
 */
 function consultaNotasEvaluacion(idSesion)
 {
	//mandamos a consultar
	 $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/consultarNotaEvaluacionPorId.do?idSesion='+idSesion+'',
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 
			 	$('#nSesionEI').val($(xml).find('NotaEvolucionDTO').find('numeroSesion').text());
			 	$('#nExpedienteEI').val($(xml).find('NotaEvolucionDTO').find('numeroExpediente').find('numeroExpediente').text());
			 	$('#FechaEI').val($(xml).find('NotaEvolucionDTO').find('stringFecha').text());
			 	$('#seguimientoText').val($(xml).find('NotaEvolucionDTO').find('seguimiento').text());
			 	$('#objetotextarea').val($(xml).find('NotaEvolucionDTO').find('objetivo').text());
			 	$('#textareaAnalisis').val($(xml).find('NotaEvolucionDTO').find('analisisSesion').text());
			 	$('#planTerap').val($(xml).find('NotaEvolucionDTO').find('planteamientoTerap').text());
			 	var nombrecompletoVictima=$(xml).find('NotaEvolucionDTO').find('victima').find('nombre').text();
			 	nombrecompletoVictima=nombrecompletoVictima+" "+$(xml).find('NotaEvolucionDTO').find('victima').find('apellidoPaterno').text();
			 	nombrecompletoVictima=nombrecompletoVictima+" "+$(xml).find('NotaEvolucionDTO').find('victima').find('apellidoMaterno').text();
			 	$('#nombreVictima').val(nombrecompletoVictima);
		  }
	});
 }
 
 /*
  *Funcion para guardar una Nota de evaluacion
  */
  function guardaNotaEvaluacion()
  {
 	var idSesion=idSolicitud;
 	var seguimiento=escape($("#seguimientoText").val());
 	var fecha=escape($("#FechaEI").val());
 	var objetivo=escape($("#objetotextarea").val());
 	var analisis=escape($("#textareaAnalisis").val());
 	var plan=escape($("#planTerap").val());
 	var observaciones=escape($("#planTerap").val());

	if(validaCamposInsercion()){
 	 $.ajax({
 	     type: 'POST',
	 	     url: '<%=request.getContextPath()%>/guardarNotaEvaluacion.do?idSesion='+idSesion+'&seguimiento='+seguimiento+'&fecha='+fecha+'&objetivo='+objetivo+'&analisis='+analisis+'&plan='+plan+'&asistencia='+asistencia+'&observaciones='+observaciones+'',
 		 dataType: 'xml',
 		 async: false,
 		 success: function(xml){
	 			 customAlert("La nota de evaluación se guardó correctamente");
 		  }
 	});
		 window.parent.cerrarVentana("iframewindowVisorNotasEjecucion");
  }
  }

  /*
  *Funcion que simula la funcion TRIM de otros lenguajes 
  *
  */
  function trim (myString)
  {
  	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
  }

  function validaCamposInsercion()
  {
	if(asistencia == "true"){
		//validacion de Seguimiento
	  	if(trim($("#seguimientoText").val()).length==0)
	  	{
	  		customAlert("El campo seguimiento debe ser ingresado");
	  		return false;
	  	}
	    //validacion de Objetivo
	  	if(trim($("#objetotextarea").val()).length==0)
	  	{
	  		customAlert("El campo objetivo debe ser ingresado");
	  		return false;
	  	}
	    //validacion de Análisis de Sesión y Estrategias Empleadas
	  	if(trim($("#textareaAnalisis").val()).length==0)
	  	{
	  		customAlert("El campo análisis de sesión y estrategias empleadas debe ser ingresado");
	  		return false;
	  	}
	    //validacion de Plan Terapéutico
	  	if(trim($("#planTerap").val()).length==0)
	  	{
	  		customAlert("El campo plan terapéutico debe ser ingresado");
	  		return false;
	  	}
	}else{
		//validacion de observaciones
	  	if(trim($("#planTerap").val()).length==0)
	  	{
	  		customAlert("El campo observaciones de la falta de interés debe ser ingresado");
	  		return false;
	  	}
	}
  	return true;  
  }

</script>
<body>
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
<table width="100%" border="0">
  <tr>
    <td width="13%">Numero de Sesi&oacute;n:</td>
    <td width="15%">
      <input type="text" name="nSesionEI" id="nSesionEI" disabled="disabled" style="width:50px"/>
   </td>
    <td width="11%">Fecha de Sesi&oacute;n:</td>
    <td width="15%"><input type="text" name="FechaEI" id="FechaEI" disabled="disabled"/></td>
    <td width="13%">Número Expediente:</td>
    <td width="15%">
    	<input type="text" name="nExpedienteEI" id="nExpedienteEI" disabled="disabled" style="width:200px"/>
    </td>
    <td width="1%">&nbsp;</td>
    <td width="17%" align="right"><input name="" type="button" value="Agendar Siguiente Sesión" class="btn_guardar" id="GuardarAgenda" /></td>
  </tr>
   <tr>
    <td width="13%"><span id="tipoVictimaSegimiento">Seguimiento:</span></td> 
    <td width="15%">
      <input type="text" name="nSesionEI" id="seguimientoText"  />
   </td>
    <td colspan="4" align="right">Nombre de la Víctima:
      <input type="text" name="FechaEI" id="nombreVictima" disabled="disabled" style="width:200px"/></td>
    <td>&nbsp;</td>
    <td width="17%" align="right"><input name="" type="button" value="Guardar" class="btn_guardar" id="dtnGuardarNotaEvaluacion"/></td>
  </tr>
   <tr align="left">
    <td colspan="8"><table width="100%" border="0">
  <tr>
    <td width="8%"><span id="tipoVictimaObjeto">Objetivo:</span> </td>
    <td width="35%"><textarea name="textarea" id="objetotextarea" cols="45" rows="5"></textarea></td>
    <td width="28%"><span id="tipoVictimaAnalisis">Análisis de Sesión y Estrategias Empleadas: </span></td>
    <td width="29%"><textarea name="textarea2" id="textareaAnalisis" cols="45" rows="5"></textarea></td>
  </tr>
</table></td>
  </tr>
  <tr>
    <td colspan="3"><span id="tipoVictimaPlan">Plan Terapéutico u Observaciones para Siguiente  Sesión: </span> <span id="observacionFaltaInteres">Observaciones de la Falta de Interés: </span></td>
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8"><textarea class="jquery_ckeditor" name="planTerap" cols="70" rows="20" id="planTerap" ></textarea>
   
  </tr>
 
</table>
	            
			

</body>
</html>