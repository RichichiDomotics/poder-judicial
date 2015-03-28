<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
	<title>Crear o Editar Plantilla</title>
	
		<!--CSS DE LA PAGINA-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
		<!--SCRIPTS DE LA PAGINA-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		//Crea las tabs del visor
		$("#tabsCrearOEditarPlantilla").tabs();
		//Ocultamos el boton de guardar del jsp include de ingresar narrativa
		$("#guardarNarraTiva").hide();
		//Carga el combo de plantillas
		cargaTipoDePlantillas();
		//Carga las etiquetas que se pueden agregar a la plantilla
		cargaValoresPlantilla();
	});

	//Funcion para cerrar la ventana
	function cerrarVentana(){
		
		parent.cerrarVisorCrearOEditarPlantilla();
	}
	 
	/*
	*Funcion que realiza la carga del combo de plantillas
	*/
	function cargaTipoDePlantillas() {

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTiposFormas.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catDocumentos').each(function(){			
					$('#cbxTipoForma').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
		tipoPlantilla='<%= request.getAttribute("tipoPlantilla")%>';
		
		//Si el id es diferente de null, se trata de una consulta de la plantilla,
		if(tipoPlantilla != "null"){
			$('#cbxTipoForma').val(tipoPlantilla);
			$('#nombrePlantilla').val('<%= request.getAttribute("nombrePlantilla")%>');
			$('.jquery_ckeditor').val($("#contenido").html());
		}
		
	}

	/*
	*Funcion que realiza la carga de los valores
	*/
	function cargaValoresPlantilla(){

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCamposForma.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('camposFormaDTO').each(function(){
					$('#listaCampos').append('<tr><td>&lt;' + $(this).find('nombreNegocio').text() + '&gt;</td></tr>');
				});
			}
		});
	}
	
	function guardar(){
		
		nombrePlantilla = $("#nombrePlantilla").val();
		cuerpo = escape($('.jquery_ckeditor').val());
		tipoForma = $("#cbxTipoForma").val(); ;
		formaId = '<%= request.getParameter("plantillaId")%>';
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/guardarPlantilla.do',
			data: 'nombre='+nombrePlantilla+'&cuerpo='+cuerpo+'&tipoForma='+tipoForma+'&formaId='+formaId,
			dataType: 'xml',
			async: false,
			success: function(xml){
				
				if($(xml).find('long').text() == '1'){
					alert("El nombre de la plantilla ya existe en el sistema");
				}else{
					alert("La información se guardó con éxito");
					cerrarVentana();
				}
			}
		});
	}
   
	</script>
</head>
<body>
<div style="display: none;" id="contenido">
<%= request.getAttribute("cuerpoPlantilla")%>
</div>
<div id="tabsCrearOEditarPlantilla">
	
	<ul>
		<li><a href="#tabsCrearOEditarPlantilla-0">Datos de la plantilla</a></li>
		<li><a href="#tabsCrearOEditarPlantilla-1">Forma de la plantilla</a></li>
	</ul>
	
	<!--	Comienza tab datos de la plantilla-->
	<div id="tabsCrearOEditarPlantilla-0">
		<table width="800" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="150">&nbsp;</td>
		    <td width="200">&nbsp;</td>
		    <td width="450" align="right"></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><strong>Nombre:</strong></td>
		    <td><input type="text" id="nombrePlantilla" style="width: 180px;"/></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right">
				<!--<strong>Fecha de creación:</strong>-->
				<strong>Tipo de Forma:</strong>
		    </td>
		    <td>
				<!--<input type="text" id="fechaCreacionDocumento" style="width: 180px; border: 0; background: #DDD;" readonly="readonly" />-->
		    	<select id="cbxTipoForma" style="width:200px;">						
				<option value="0">-Todos-</option>
				</select>
		    </td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><input type="button" id="guardarPlantilla1" value="Guardar" class="btn_Generico" onclick="javascript:guardar();" /></td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>
	</div>
	<!--	Termina tab datos de la pantilla-->
	<!--	Comienza tab Forma de la plantilla-->
	<div id="tabsCrearOEditarPlantilla-1">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		  	<td align="right"></td>
            <td align="right">
		  		<input type="button" id="guardarPlantilla2" value="Guardar" class="btn_Generico" onclick="javascript:guardar();"/>
		  	</td>
		  </tr>
		  <tr>
		    <td width="200"><strong>Seleccione y arrastre los campos que desee, hacia el documento</strong>
		    	<table id="listaCampos">
		    		
		    	</table>
		    	
		    </td>
		    <td width="250"><jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include></td>
		    
		  </tr>
		</table>
	</div>
	
	<!--	Termina tab Forma de la plantilla-->
</div>
<!--	Termina tab principal-->
</body>
</html>