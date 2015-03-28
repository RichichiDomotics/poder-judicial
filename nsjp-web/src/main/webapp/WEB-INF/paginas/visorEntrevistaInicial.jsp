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


</head>
<script type="text/javascript">
var idSolicitud=0;
$(document).ready(function() {
//$("#tabsPrincipal").tabs();
	idSolicitud='<%= request.getParameter("rowid")%>';
	//alert(idSolicitudCompuesto);
	consultaEntrevistaInicial(idSolicitud);
	$("#btnGuardaEntrvistaInicial").click(guardaEntrevistaInicial);
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
 function consultaEntrevistaInicial(idSesion)
 {
	//mandamos a consultar
	 $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/consultarEntrevistaInicialPorId.do?idSesion='+idSesion+'',
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 //alert($(xml).find('EntrevistaInicialDTO').find('numeroSesion').text());
			 	 $('#nSesionEI').val($(xml).find('EntrevistaInicialDTO').find('numeroSesion').text());
			 	
			 	 $('#nExpedienteEI').val($(xml).find('EntrevistaInicialDTO').find('numeroExpediente').find('numeroExpediente').text());
			 	$('#FechaEI').val($(xml).find('EntrevistaInicialDTO').find('stringFecha').text());
				 if($(xml).find('EntrevistaInicialDTO').find('esVictimaDirecta').text()== 'true'){
					 $('#radio').attr('checked','checked');
				 }else{
					 $('#radio2').attr('checked','checked');
				 }
				 
				 $('#motivoDEATT').val($(xml).find('EntrevistaInicialDTO').find('motivoAtencion').text());
		  }
	});
 }

 /*
 *Funcion para guardar una entrevista inicial
 */
 function guardaEntrevistaInicial()
 {
	 //alert("sdfsdf");
	 var idSesion=idSolicitud;
	 var victimaDirecta="true";
	 var radio=$("#radio").val();
	 if(radio==0){
		 victimaDirecta="true";
	 }else{
		 victimaDirecta="false"; 
	 }
		 
	 var fecha=$("#FechaEI").val();
	 var motivo=escape($("#motivoDEATT").val());

	 if( motivo != ""){
	 $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/guardarEntrevistaInicial.do?idSesion='+idSesion+'&victimaDirecta='+victimaDirecta+'&fecha='+fecha+'&motivo='+motivo+'',
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 alert("La Entrevista se guardó exitosamente");
		  }
	});
		 window.parent.cerrarVentana("iframewindowVisorEntrevistaInicial");
 }
	 else{
		 alert("El campo Motivo de la Atención debe ser ingresado");
	}

 }

</script>
<body>

<table width="100%" border="0">
  <tr>
    <td width="13%">Numero de Sesi&oacute;n:</td>
    <td width="16%">
      <input type="text" name="nSesionEI" id="nSesionEI" disabled="disabled" style="width:50px"/>
   </td>
    <td width="13%">Fecha de Sesi&oacute;n:</td>
    <td width="16%"><input type="text" name="FechaEI" id="FechaEI" disabled="disabled"/></td>
    <td width="10%">Número Expediente:</td>
    <td width="19%">
    	<input type="text" name="nExpedienteEI" id="nExpedienteEI" disabled="disabled" style="width:200px"/>
    </td>
    <td>&nbsp;</td>
    <td width="9%" align="right"><input name="btnGuardaEntrvistaInicial" type="button" value="Guardar" class="btn_guardar" id="btnGuardaEntrvistaInicial" /></td>
  </tr>
   <tr align="center">
    <td colspan="5">Tipo de Víctima&nbsp;&nbsp;&nbsp;&nbsp; Directa: 
      <input type="radio" name="radioTipoVictimaEI" id="radio" value="0" /> 
      Indirecta: <input type="radio" name="radioTipoVictimaEI" id="radio2" value="1" /></td>
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">Motivo de la Atenci&oacute;n:</td>
    
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8"><textarea class="jquery_ckeditor" name="motivoDEATT" cols="70" rows="20" id="motivoDEATT" ></textarea>
   
  </tr>
 
</table>
	            
			

</body>
</html>