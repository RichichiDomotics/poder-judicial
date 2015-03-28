<%@page
	import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/layout_complex.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<style>
.transpa {
	background-color: transparent;
	border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">


//alert(vardocumentoID);
var idExpediente =""; 

var reloadGrid=false;



$(document).ready(function() {
	gridSolicitud();

		 });


	

function confirmaSolicitud(){

	
	$("#confirmaSolicitud").dialog("open");
	$("#confirmaSolicitud").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Motivo de la Solicitud', 
	  	dialogClass: 'alert',
	  	position: [200,30],
	  	width: 412,
	  	height: 332,
	  	maxWidth: 500,
	  	buttons:{"Generar Entrada":function() {	

	  					  					 
	  			$(this).dialog("close");
	  		},
	  		"Cancelar":function() {
	  			$(this).dialog("close");
	  		}
	  	}
	});	
	    
		 
		  
}	





function gridSolicitud(){
	
	//alertidExpediente);
	if (reloadGrid==true) {
		  jQuery("#tabgrid").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
		  $("#tabgrid").trigger("reloadGrid"); 
	  } else {
		  reloadGrid=true;
	  jQuery("#tabgrid").jqGrid({ 

	url:'<%=request.getContextPath()%>/.do',
							data : "",
							datatype : "xml",
							colNames : [ 'Folio de Cadena de Custodia','Autorizado por','Fecha de Inicio de Préstamo','Fecha de Fin de Préstamo','Nombre de persona autorizada','Tipo de Identificación'],
							colModel:[ {name : 'CadenaCustodia',index : 'CadenaCustodia',	width : 200,resizable : true},
							 {name : 'Autorizado',index : 'Autorizado',	width : 200,resizable : true},
							 {name : 'InicioPréstamo',index : 'InicioPréstamo',	width : 200,resizable : true},
							 {name : 'FinPrestamo',index : 'FinPrestamo',	width : 200,resizable : true},
							 {name : 'Nombre',index : 'Nombre',	width : 200,resizable : true},
							 {name : 'Identificacion',index : 'Identificacion',	width : 200,resizable : true}],
							resizable : true,
							pager : jQuery('#tabgrid'),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							width : 'auto',
							height : 'auto',
							sortname : 'detalle',
							viewrecords : true,
							sortorder : "desc",
							//multiselect: true,

							onresize_end : function() {
								$("#tabgrid").setGridWidth(
										$("#mainContent").width() - 5, true);
							},

							ondblClickRow : function(rowID) {
								confirmaSolicitud();
							}
						}).navGrid('#tabgrid', {
					edit : false,
					add : false,
					del : false
				});
	  }
	 
	}


</script>

<title>Insert title here</title>
</head>
<body>

<table>
	<tr class="fondoFuerteAP">

		<td style="color: white" colspan="2" align="center">Solicitudes
		</td>
	</tr>
	
	
	<tr>
		<td align="left">Folio de cadena de custodia</td>
		<td align="right"><input type="text" id="folioCadenaCustodiaRSA"></td>
	</tr>
	<tr>
		<td align="left">Total de Evidencias</td>
		<td align="right"><input type="text" id="totalEvidenciasRSA"></td>
	</tr>
	<tr>
		<td align="left">Evidencias</td>
		<td align="right"><input type="text" id="evidenciasRSA"></td>
	</tr>
	<!-- Lista de las evidencias -->
	<tr>
		<td align="left"></td>
		
		<td align="right"><input type="text" id=""></td>
	</tr>
</table>

<div id="nabtabgrid" >
<table id="tabgrid" align="center"></table>
<div id="pagerGrid"></div>
<input type="button" value="Confir que sale del grid"
			onclick="confirmaSolicitud()" class="btn_Generico"/>
</div>

<div id="confirmaSolicitud" style="display: none">
<table><tr><td> <textarea rows="12" cols="56" ></textarea>  </td></tr> </table>
</div>







<script type="text/javascript">
	
</script>
</body>
</html>