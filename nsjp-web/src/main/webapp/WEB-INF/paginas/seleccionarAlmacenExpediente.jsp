<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccionar almacen de expedientes</title>
</head>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	

<script type="text/javascript">
$(document).ready(function() {
	cargaGrid();
	$('#btnAsignar').hide();
});

var idAlmacen;
var descAlmacen;

function cargaGrid(){
	jQuery("#gridAlmacenes").jqGrid({ 
		url:'<%= request.getContextPath()%>/buscarAlmacenes.do', 
		datatype: "xml", 
		colNames:['Nombre','Domicilio','Descripción'], 
		colModel:[ 	{name:'NombreAlmacen',index:'nombreAlmacen', width:100},
		           	{name:'Domicilio',index:'domicilio', width:150},
		           	{name:'Descripcion',index:'descripcion', width:150}
				],
		pager: jQuery('#pagerAlmacenes'),
		rowNum:10,
		rowList:[10,20,30],
		autowidth: true,
		width:"100%",
		height:300,
		sortname: 'nombreAlmacen',
		viewrecords: true,
		sortorder: "desc",
		/*ondblClickRow: function(rowid) {
			seleccionarAlmacen(rowid);
		},*/
		onSelectRow: function(rowid){			
			seleccionarAlmacen(rowid);
		}
	})//.navGrid('#pagerAlmacenes',{edit:false,add:false,del:false});
}

function seleccionarAlmacen(id){
	idAlmacen = id;
	var grid = jQuery('#gridAlmacenes');
	var sel_id = grid.jqGrid('getGridParam', 'selrow');
	var myCellData_NombreAlmacen = grid.jqGrid('getCell',sel_id,'NombreAlmacen');
	var myCellData_Domicilio = grid.jqGrid('getCell',sel_id,'Domicilio');
	var myCellData_Descripcion = grid.jqGrid('getCell',sel_id,'Descripcion');
	descAlmacen = myCellData_NombreAlmacen + ", " + myCellData_Domicilio + ", " + myCellData_Descripcion;
	//descAlmacen = myCellData_NombreAlmacen;
	$('#btnAsignar').show();
}

function asignar(){
	window.parent.cargaAlmacen(idAlmacen, descAlmacen);	
}

function almacenTemporal(){
	alert("CU Registrar almacén de expedientes");
}
</script>

<table width="650" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td height="25" colspan="2" align="left">
    		<input type="button" name="btnAsignar" value="Asignar" id="btnAsignar" onclick="asignar()" class="btn_Generico"/>
    		<input type="button" name="btnAlmacenTemporal" value="Almacén Temporal" id="btnAlmacenTemporal" onclick="almacenTemporal()" class="btn_Generico"/>
    	</td>
    </tr>
</table>

<table id="gridAlmacenes"></table>
<div id="pagerAlmacenes"></div>

</body>
</html>