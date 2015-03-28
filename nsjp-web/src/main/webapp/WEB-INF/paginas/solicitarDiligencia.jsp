<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" media="screen" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>

       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" rel="stylesheet">
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	<style>
.transpa {
background-color: transparent;
border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	var reloadGridcarga=false;
	var reloadGrid=false;
	var idDefensor="";
	var carga = "";
	var idCarga = "";
	 var ditable=0;
	 var cargaMinutos=0;
	
	
	$(document).ready(function() {
		cargaEstadoExpediente();
		

		$("#fechaSol").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true
			
		});

		$('#horaSol').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
	});

	

	function generarSolicitud(){}



	
	
	 function cargaEstadoExpediente(){
	    	//alert("cargarDefensor");
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarTipoDiligencias.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	    			$(xml).find('catDiligencia').each(function(){
	    				$('#catTipoDiligencias').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }


	 function recuperaDatosSolicitudDiligencia() {
			var param ="";
			param += 'fechaSol='+$("#fechaSol").val();
			param += '&horaSol='+$("#horaSol").val();
			param += '&motivoSoli='+$("#motivoSoli").val();
			param += '&TipoDilidencia='+$('#catTipoDiligencias option:selected').val();
			alert(param);
			   $.ajax({
			     type: 'POST',
			     url: '<%= request.getContextPath()%>/GenerarSolicitudDiligencia.do',
				 data: param,
				 dataType: 'xml',
				 success: function(xml){
					//alert(param);
				  }
				});
			 }
</script>

<title>Insert title here</title>
</head>
<body>
<table align="center"">
	<tr>
	
		<td width="73" align="right" >Fecha de Detenci&oacute;n:</td>
		<td width="334">
			<input type="text" size="50" maxlength="50" id="fechaSol"  name=""/>
		</td>
		<td width="66" align="right" >Hora de Detenci&oacute;n:</td>
		<td width="301">
			<input type="text" size="50" maxlength="50" id="horaSol" name=""/>
		</td>
		
	</tr>
	
	<tr>
	
		<td width="73" align="right" >Tipo de diligencia</td>
		<td width="334">
			<select id="catTipoDiligencias">
			<option></option>
			</select>
		</td>
		<td width="66" align="right" >Motivo</td>
		<td width="301">
			<input type="text" size="50" maxlength="50" id="motivoSoli" name=""/>
		</td>
		
	</tr>
	</table>

<center>
	
	<input type="button" value="Generar solicitud de diligencia" onclick="recuperaDatosSolicitudDiligencia()" class="btn_Generico">
</center>
<script type="text/javascript">

</script>
</body>
</html>