<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configurar Complejidad de Audiencia</title>


<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/estilos.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet"
	href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />

<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
<script type="text/javascript">
						    
	$(document).ready(function() {

		jQuery("#gridTipoAudienciaPJENA").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarCatalogoTipoAudienciaGrid.do', 
			datatype: "xml", 
			colNames:['Tipo de Audiencia'], 
			colModel:[ 	{name:'tipoAudiencia',index:'tipoAudiencia', width:100, align:"center"} 
													
					],
			pager: jQuery('#pagTipoAudienciaPJENA'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			onSelectRow: function(rowid) {
				abreModalComplejidad(rowid);
			}
			}).navGrid('#pagTipoAudienciaPJENA',{edit:false,add:false,del:false});		
				
	});
		   

		function abreModalComplejidad(rowid){

			$("#divComplejidad").dialog("open");
		  	$("#divComplejidad").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Introducir Complejidad de Audiencia', 
			  	dialogClass: 'alert',
			  	position: [20,20],
			  	width: 350,
			  	height: 200,
			  	maxWidth: 1000,
			  	buttons:{"Guardar":function() {

			  		var complejidad = 'complejidad='+ $("#nivelComplejidad option:selected").val();
			  			complejidad += '&clave='+ rowid ;

			  		 $.ajax({
							type: 'POST',
							url: '<%= request.getContextPath()%>/guardaComplejidadTipoAudiencia.do',
							data: complejidad, 
							async: false,
							dataType: 'xml',
							success: function(xml){
				   								
							}
						});
						

			   		customAlert("Se guarda complejidad");
			   		$(this).dialog("close");
					
			  		},
			  		"Cancelar":function() {
				  		confirmar=confirm("¿Realmente desea salir de la ventana sin guardar la complejidad?");
				  		if (confirmar){

					  		  		$(this).dialog("close");
					  		}
				  		
				  		else{
					  		
					  		}
				  		
			  		}
			  	}
			});	  	
			
		}
	
	</script>
</head>
<body>
<table width="380" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3" width="318" align="justify"><strong>"Seleccione
		una audiencia del listado para configurar su complejidad y con base en
		ello determinar la carga de trabajo al momento de programar una
		audiencia."</strong></td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="29">&nbsp;</td>
		<td colspan="2" rowspan="2">
		<table id="gridTipoAudienciaPJENA" cellspacing="0" cellpadding="0"></table>
		<div id="pagTipoAudienciaPJENA"></div>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>

<div id="divComplejidad" style="display: none">

<table width="300" cellspacing="0" cellpadding="0">
	<tr>
		<td width="45">&nbsp;</td>
		<td width="308">&nbsp;</td>
		<td width="45">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" align="justify"><strong>Selecciona una
		complejidad para la audiencia seleccionada, el valor debe estar entre
		1 y 5, siendo el número uno una complejidad muy baja y el 5 una
		complejidad muy alta. </strong></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><select id="nivelComplejidad">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>

</table>



</div>
</body>
</html>

