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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<style>
.transpa {
	background-color: transparent;
	border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

var vardocumentoID=parent.documentoID;
var idExpediente =""; 
var reloadGrid=false;
var reloadGrid2=false;

$(document).ready(function() {
});

function agregarEtapa(){

	
	$("#nabtabgrid2").dialog("open");
	$("#nabtabgrid2").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Resolutivo', 
	  	dialogClass: 'alert',
	  	position: [30,30],
	  	width: 237,
	  	height: 241,
	  	maxWidth: 500,
	  	buttons:{"Agregar":function() {	

	  					  					 
	  			$(this).dialog("close");
	  		},
	  		"Cancelar":function() {
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
			 idExpediente = $(xml).find('audienciaDTO').find('id').text();
			 
			
		  }
		});
	   if (idExpediente!=""){
		   $("#nabtabgrid").css("display", "block");
			 grid();
			 
		 }else{ 

			 $("#nabtabgrid").css("display", "none");
		 }
	 }



function grid(){	
	if (reloadGrid==true) {
		  jQuery("#tabgrid").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
		  $("#tabgrid").trigger("reloadGrid"); 
	  } else {
		  reloadGrid=true;
	  jQuery("#tabgrid").jqGrid({ 
	url:'<%=request.getContextPath()%>/ConsultarDocumentosAudiencia.do?audienciaid='+idExpediente,
							data : "",
							datatype : "xml",
							colNames : [ 'Nombre Documento' ],
							colModel : [ {name : 'Ndocumento',index : 'ndocumento',	width : 200,resizable : true
							}

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

function grid2(){
	$("#nabtabgrid2").css("display", "block");
	var numeroEXP = $("#numCarpetaPJ").val();
	if (reloadGrid2==true) {
		  jQuery("#tabgrid2").jqGrid('setGridParam', {postData: {numCarpetaEjecucion: numeroEXP}});
		  $("#tabgrid2").trigger("reloadGrid"); 
	  } else {
		  reloadGrid2=true;
	  jQuery("#tabgrid2").jqGrid({ 
	url:'<%=request.getContextPath()%>/BuscaCarpetaEjecucionPorCausa.do?numCarpetaEjecucion='+ numeroEXP,
							data : "",
							datatype : "xml",
							colNames : [ 'Numero Audiencia' ],
							colModel : [ {name : 'Ndocumento',index : 'ndocumento',	width : 200,resizable : true
							}

							],
							resizable : true,
							pager : jQuery('#tabgrid2'),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							width : 'auto',
							height : 'auto',
							sortname : 'detalle',
							viewrecords : true,
							sortorder : "desc",
							//multiselect: true,

							onresize_end : function() {
								$("#tabgrid2").setGridWidth(
										$("#mainContent").width() - 5, true);
							},

							ondblClickRow : function(rowID) {
								idExpediente=rowID;
								grid();
								 $("#nabtabgrid").css("display", "block");
							}
						}).navGrid('#tabgrid2', {
					edit : false,
					add : false,
					del : false
				});
	  }
	}

	
	function tipoBusqueda(){
		if($(':radio[name=tipoBusquedaSudiencia]:checked').val()==0){
			buscaCarpeta();
		}else{   
			grid2();
		}
	}
</script>

<title>Insert title here</title>
</head>
<body>

<table>
	<tr class="fondoFuerteAP">

		<td style="color: white" colspan="2" align="center">Busqueda Por
		</td>
	</tr>
	<tr >

		<td>N&uacute;mero de Audiencia <input type="radio" name="tipoBusquedaSudiencia" value="0">
		</td>
		<td>N&uacute;mero de Causa <input type="radio" name="tipoBusquedaSudiencia" value="1">
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;
		</td>
	</tr>
	<tr>
		<td align="left"><input type="text" id="numCarpetaPJ"></td>
		<td align="right"><input type="button" value="Buscar"
			onclick="tipoBusqueda();" class="btn_Generico"></td>
	</tr>
</table>

<div id="nabtabgrid2" style="display: none">
<table id="tabgrid2" align="center"></table>
</div>

<div id="nabtabgrid"  style="display: none">
<table id="tabgrid" align="center"></table>
</div>







<script type="text/javascript">
	
</script>
</body>
</html>