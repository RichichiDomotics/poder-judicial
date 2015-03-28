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
	abreCodigosLeyes();

		 });


	

function abreCodigosLeyes(tipo) {

	 tipoLey = tipo;

	 jQuery("#gridLeyesCodigosPJENS").jqGrid('setGridParam', {postData: {tipoLey: tipoLey}});
	  $("#gridLeyesCodigosPJENS").trigger("reloadGrid"); 

	jQuery("#gridLeyesCodigosPJENS").jqGrid({ 
		url:'<%= request.getContextPath()%>/consultarLeyesCodigos.do', 
		datatype: "xml", 
		colNames:['Consulta de Leyes y C&oacute;digos'], 
		colModel:[ 	{name:'ley',index:'ley', width:500,align:"center"} ],
		pager: jQuery('#pager10'),
		rowNum:10,
		rowList:[10,20,30],
		autowidth: true,
		height: 220,
		sortname: 'detalle',
		viewrecords: true,
		sortorder: "desc",
		onCellSelect: function(id,fila,contenido){
			abrirPDF(id,fila,contenido);
			}
		}).navGrid('#pager10',{edit:false,add:false,del:false});
	
}

function abrirPDF(idLey,fila,contenido){
	

	document.frmDoc.idLey.value = idLey;
	document.frmDoc.nombreArchivo.value = contenido;
	
	document.frmDoc.submit();

}

</script>

<title>Insert title here</title>
</head>
<body>

<table width="100%" border="0" cellpadding="0" cellspacing="5">
		
		<tr>
		  
		  <td width="25%"><ul class="botonLeyes">
					<div>
						<li id="consPolitica"  onclick="abreCodigosLeyes(2070)"><span></span>Constitución Política</li>
				</div>
				</ul></td >
		  <td width="75%" rowspan="8" valign="top" ><table id="gridLeyesCodigosPJENS" width="100%"></table>
	      <div id="pager10"></div></td>
  </tr>
		<tr>
			
			<td width="25%"><ul class="botonLeyes">
					<div>
						<li id="leyesGenerales" onClick="abreCodigosLeyes(2071)" ><span>Leyes Generales</span></li>
				  </div>
				</ul></td>
		</tr>
		<tr>
			
			
			<td height="21" ><ul class="botonLeyes">
					<div>
					  <li id="tratados" onClick="abreCodigosLeyes(2074)">Tratados Internacionales</li>
					</div>
				</ul></td>
		</tr>
		<tr>
		  <td ><ul class="botonLeyes">
					<div>
						<li id="codigos" onClick="abreCodigosLeyes(2072)"><span></span>Códigos</li>
					</div>
				</ul></td>
  </tr>
		<tr>
		  <td><ul class="botonLeyes">
					<div>
						<li id="acuerdos" onClick="abreCodigosLeyes(2075)"><span></span>Acuerdos</li>
					</div>
				</ul></td>
  </tr>
		<tr>
		  <td><ul class="botonLeyes">
					<div>
						<li id="circulares" onClick="abreCodigosLeyes(2076)" ><span></span>Circulares</li>
					</div>
				</ul></td>
  </tr>
		<tr>
		  <td ><ul class="botonLeyes">
					<div>
						<li id="manuales" onClick="abreCodigosLeyes(2073)"><span></span>Manuales</li>
					</div>
				</ul></td>
  </tr>
		<tr>
		  <td ><ul class="botonLeyes">
					<div>
						<li id="instructivos" onClick="abreCodigosLeyes(2077)"><span>Instructivos</span></li>
					</div>
				</ul></td>
  </tr>
		
		</table>		



<form name="frmDoc" action="<%= request.getContextPath() %>/abrirPDF.do" method="post">
					<input type="hidden" name="idLey" value=""/>
					<input type="hidden" name="nombreArchivo" value=""/>					
<script type="text/javascript">
	
</script>
</body>
</html>