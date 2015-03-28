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
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>

<style>
.transpa {
	background-color: transparent;
	border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

var idExpediente =""; 

var reloadGrid=false;

var reloadGrid2=false;

$(document).ready(function() {
	

		 });


	

function confirmaDestino(){
	gridConfirmarDestinoLegalEvidencia();
	
	$("#confirmarDestinoLegal").dialog("open");
	$("#confirmarDestinoLegal").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Resolutivo', 
	  	dialogClass: 'alert',
	  	position: [30,30],
	  	width: 500,
	  	height: 185,
	  	maxWidth: 700,
	  	buttons:{"Aceptar":function() {	

	  					  					 
	  			$(this).dialog("close");
	  		},
	  		"Rechazar":function() {
	  			$(this).dialog("close");
	  		}
	  	}
	});	
	    
		 
		  
}	

function buscaCarpeta() {
	idExpediente="";
	var param ="";
	param += 'audienciaNum='+ $("#numCarpetaPJ").val();
	
	   $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/BuscaCarpetaEjecucion.do',
		 data: param,
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 idExpediente = $(xml).find('').find('').text();
			 
			
		  }
		});
		 
			 grid();
	 }



function grid(){
	
	if (reloadGrid==true) {
		  jQuery("#tabgrid").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
		  $("#tabgrid").trigger("reloadGrid"); 
	  } else {
		  reloadGrid=true;
	  jQuery("#tabgrid").jqGrid({ 
	url:'<%=request.getContextPath()%>/.do',
															   
															
							data : "",
							datatype : "xml",
							colNames : ['Folio de la Cadena de Custodia', 'Número de Evidencia','Descripción de la Evidencia','Número de Eslabón','Tipo de Eslabón','Almacén','Destino Legal' ],
							colModel : [ {name : 'NumEvidencia',index : 'NumEvidencia',	width : 200,resizable : true},
										{name : 'NEvidencia',index : 'NEvidencia',	width : 200,resizable : true},	
							             {name : 'IEvidencia',index : 'IEvidencia',	width : 200,resizable : true},
							             {name : 'NEslabon',index : 'NEslabon',	width : 200,resizable : true},
							             {name : 'TEslabon',index : 'TEslabon',	width : 200,resizable : true},
							             {name : 'Almacen',index : 'Almacen',	width : 200,resizable : true},	
							             {name : 'Destino',index : 'Destino',	width : 200,resizable : true},		
								],
							resizable : true,
							pager : jQuery('#tabgrid'),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							width : 'auto',
							height : 'auto',
							sortname : 'detalle',
							viewrecords : true,
							sortorder : "desc",
							multiselect: true,
							
							//multiselect: true,

							onresize_end : function() {
								$("#tabgrid").setGridWidth(
										$("#mainContent").width() - 5, true);
							}
						}).navGrid('#tabgrid', {
					edit : false,
					add : false,
					del : false
				});
	  }
	 
	}



	
	function tipoBusqueda(){
		//funcion en caso de confirm
			buscaCarpeta();

					
	}

	
	function lanzaConfirmarDestinoLegal() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:650,height:400,title:"Entradas de Almacen", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/registrarEntradaAlmacen.do" width="650" height="400" />');
	    		
	}	


	function gridConfirmarDestinoLegalEvidencia(){
		
		if (reloadGrid2==true) {
			  jQuery("#gridConfirmarDestinoLegal").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
			  $("#gridConfirmarDestinoLegal").trigger("reloadGrid"); 
		  } else {
			  reloadGrid2=true;
		  jQuery("#gridConfirmarDestinoLegal").jqGrid({ 
		url:'<%=request.getContextPath()%>/.do',
		
			
								data : "",
								datatype : "xml",
								colNames : ['Número de la Evidencia', 'Destino de la Evidencia'],
								colModel : [ {name : 'NumEvidencia',index : 'NumEvidencia',	width : 200,resizable : true},
											{name : 'DesEvidencia',index : 'DesEvidencia',	width : 200,resizable : true},	
								             	
									],
								resizable : true,
								pager : jQuery('#gridConfirmarDestinoLegal'),
								rowNum : 10,
								rowList : [ 10, 20, 30 ],
								width : 'auto',
								height : 'auto',
								sortname : 'detalle',
								viewrecords : true,
								sortorder : "desc",
								multiselect: true,
								
								//multiselect: true,

								onresize_end : function() {
									$("#gridConfirmarDestinoLegal").setGridWidth(
											$("#mainContent").width() - 5, true);
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

		<td style="color: white" colspan="2" align="center">Busqueda
		</td>
	</tr>
	
	<tr>
		<td colspan="2">&nbsp;
		</td>
	</tr>
	<tr>
		<td align="left"><input type="text" id="numCarpetaPJ"></td>
		<td align="right"><input type="button" value="Buscar" onclick="tipoBusqueda();" class="btn_Generico"></td>
	</tr>
</table>

<table id="catEslabon" style="display: none;">
<tr>
<td>Destino Legal</td>
<td><select >
<option  >- Seleccionar -</option>
</select> </td>
 </tr>
</table>
<div id="nabtabgrid" >
<table id="tabgrid" align="center"></table>
<div id="pagerGrid"></div>
<input type="button" value="Generar Oficio" onclick="confirmaDestino()" class="btn_Generico">
</div>



<div id="confirmarDestinoLegal" style="display: none;">

<div id="divConfirmarDestinoLegal" >
<table id="gridConfirmarDestinoLegal" align="center"></table>
<div id="pagerGridConfirmarDestinoLegal"></div>

</div>





<script type="text/javascript">
	
</script>
</body>
</html>