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


var idExpediente =""; 

var reloadGrid=false;

var reloadGrid2=false;

$(document).ready(function() {
	grid();

		 });




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
							colNames : ['Numer Convenio', 'Fecha Inicio','Fecha Termino','Periodicidad','Cantidad Total a Pagar',' Cantidad Total Por Pago','Fecha Último Cumplimiento','Convenio'  ],
							colModel : [ {name : 'NConvenio',index : 'NConvenio',	width : 200,resizable : true},
										{name : 'Inicio',index : 'Inicio',	width : 200,resizable : true},	
							             {name : 'Termino',index : 'Termino',	width : 200,resizable : true},
							             {name : 'Periodicidad',index : 'Periodicidad',	width : 200,resizable : true},
							             {name : 'CantidadTotal',index : 'CantidadTotal',	width : 200,resizable : true},
							             {name : 'CantidadPago',index : 'CantidadPago',	width : 200,resizable : true},
							             {name : 'UCumplimiento',index : 'UCumplimiento',	width : 200,resizable : true},	
							             {name : 'Convenio',index : 'Convenio',	width : 200,resizable : true},	
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

	
	function lanzaDetalleConvenio() {
		if (reloadGrid2==true) {
			  jQuery("#gridConvenio").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
			  $("#gridConvenio").trigger("reloadGrid"); 
		  } else {
			  reloadGrid2=true;
		  jQuery("#gridConvenio").jqGrid({ 
		url:'<%=request.getContextPath()%>/.do',
		
			
								data : "",
								datatype : "xml",
								colNames : ['Fecha Programada Cumplimiento', 'Descripción de Cumplimiento','Cumplió','Fecha Cumplimiento','Hora Cumplimiento','Observaciones' ],
								colModel : [ {name : 'NumEvidencia',index : 'NumEvidencia',	width : 200,resizable : true},
											{name : 'NEvidencia',index : 'NEvidencia',	width : 200,resizable : true},	
								             {name : 'IEvidencia',index : 'IEvidencia',	width : 200,resizable : true},
								             {name : 'NEslabon',index : 'NEslabon',	width : 200,resizable : true},
								             {name : 'TEslabon',index : 'TEslabon',	width : 200,resizable : true},
								             {name : 'Almacen',index : 'Almacen',	width : 200,resizable : true},		
									],
								resizable : true,
								pager : jQuery('#gridConvenio'),
								rowNum : 10,
								rowList : [ 10, 20, 30 ],
								width : 'auto',
								height : 'auto',
								sortname : 'detalle',
								viewrecords : true,
								sortorder : "desc",
								//multiselect: true,
								
								//multiselect: true,

								onresize_end : function() {
									$("#gridConvenio").setGridWidth(
											$("#mainContent").width() - 5, true);
								}							}).navGrid('#gridConvenio', {
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

<div id="nabtabgrid" onclick="lanzaDetalleConvenio()" >
<table id="tabgrid" align="center"></table>
<div id="pagerGrid"></div>

</div>
</br>

<div id="divGridConvenio" >
<table id="gridConvenio" align="center"></table>
<div id="pagerGrid"></div>
<input type="button" value="Guardar" align="right" class="btn_Generico"/> 
</div>



<div id="nabtabgrid2" style="display: none;">



</div>





<script type="text/javascript">
	
</script>
</body>
</html>