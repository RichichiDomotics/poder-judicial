<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
var idRoles=0;
var rolSelect=0;
$(document).ready(function() {
hideTable();
idRoles=10;
gridRoles();
gridInitModulos();
hideSubRol();
});
function gridRoles(){
jQuery("#list1").jqGrid({ 
		url:'<%=request.getContextPath()%>/consultarCatalogoRoles.do', 
		datatype: "xml", 
		colNames:['Id No','Nombre', 'Descripción'], 
		colModel:[ {name:'id',index:'id', width:15}, 
		           {name:'nombre',index:'nombre', width:30}, 
		           {name:'desc',index:'desc', width:50}], 
		rowNum:10, 
		autowidth: true,
		width: 100,
		rowList:[10,20,30], 
		pager: jQuery('#pager1'), 
		sortname: 'id', 
		viewrecords: true, 
		onSelectRow: function(id){
			rolSelect=id;
			showSubRol();
		},
		ondblClickRow: function(id) {
			gridModulos(id);
			},
		sortorder: "desc", 
		caption:"Roles en Sistema" 
}).navGrid('#pager1',{edit:false,add:false,del:false}); 
}
function gridInitModulos(){
	jQuery("#list2").jqGrid({ 
		url:'local', 
		datatype: "xml", 
		colNames:['Id No','Nombre', 'Descripción','esSeleccionado'], 
		colModel:[ {name:'id',index:'id', width:15}, 
		           {name:'nombre',index:'nombre', width:30}, 
		           {name:'desc',index:'desc', width:50},
		           {name:'esSel',index:'esSel', width:50, hidden:true}],
		loadComplete: function(){
		 	var ids=jQuery("#list2").jqGrid('getDataIDs');
		 	for (var i=0;i < ids.length;i++){
		 		var cl = ids[i]; 
		 		var ret = jQuery("#list2").jqGrid('getRowData',cl);
		 		if(ret.esSel=='true'){
		 			jQuery("#list2").jqGrid('setSelection',cl);
		 		}
		 	}			
		},
		rowNum:10, 
		autowidth: true,
		width: 100, 
		rowList:[10,20,30], 
		pager: jQuery('#pager2'), 
		sortname: 'id', 
		viewrecords: true, 
		sortorder: "desc",
		multiselect: true,
		caption:"Modulos"
}).navGrid('#pager2',{edit:false,add:false,del:false}); 
	jQuery("#m1").click( function() { var s; s = jQuery("#list2").jqGrid('getGridParam','selarrrow'); alert(s); });
}

function saveValues(){
	var s; 
	s = jQuery("#list2").jqGrid('getGridParam','selarrrow'); 
	var params = 'idsMod=' +s;
	params+= '&idRol='+idRoles
	
	
	//---
	$.ajax({
   		type: 'POST',
    		url: '<%=request.getContextPath()%>/guardarModulosRol.do',
    		data: params,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    		op=$(xml).find('boolean').text();
    		if(op='true'){
    			alert('Cambios guardados correctamente');
    		}
    			//alert($(xml).find('alertaDTO').find('nombre').text());
    			//alert('la primera op:'+op);
    			
    			//alert('la xml op:'+$(xml).find('alertaDTO').find('esAplaza').text());
    			//alert('la segunda op:'+op);
   		}
   	});
	
	
	//---
}


function gridModulos(id){
		idRoles=id;
	 	jQuery("#list2").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarCatalogoModulos.do?idRol=' + id,datatype: "xml" });
	 	$("#list2").trigger("reloadGrid");	
	 	showTable();
	 	
}

function showTable(){
	document.getElementById('btnGuardar').style.visibility = "visible";		
}

function hideTable(){
	document.getElementById('btnGuardar').style.visibility = "hidden";
}
function showSubRol(){
	$.ajax({
   		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultaRolPapa.do',
    		data: 'rolSelect='+rolSelect,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var op=$(xml).find('boolean').text();
    			if(op=="true"){
    				document.getElementById('btnSubRol').style.visibility = "visible";
    			}else{
    				document.getElementById('btnSubRol').style.visibility = "hidden";
    			}
			}
  	});   	
}

function hideSubRol(){
    document.getElementById('btnSubRol').style.visibility = "hidden";
}

function agregaNuevoRol(){
	//alert(rolSelect);
	popupSubRol();
}	
	/*
	 * Genera ventana para modificar a un funcionario 
	 */

	function guardarSubRol(){
		var nameSubRol=$('#nameSubRol').val();
		var decSubRol=$('#decSubRol').val();
		
	$.ajax({
   		type: 'POST',
    		url: '<%=request.getContextPath()%>/guardarSubRol.do',
    		data: 'nameSubRol='+nameSubRol+'&decSubRol='+decSubRol+'&rolSelect='+rolSelect,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var op=$(xml).find('boolean').text();
    			alert("termina:"+op);
    			//if(op=="false"){
    			//	alert("Los datos son incorrectos","Error");
    		//	}else{
    		//		$("#dialog-bloqueo").dialog( "close" );
    		//		reiniciar();
    		//	}
			}
  	});
}


function popupSubRol(){
	$( "#dialog-subRol" ).dialog({
		resizable: false,
		height:200,
		width:400,
		modal: true,
		closeOnEscape: false,
		buttons: {
			"Guardar": function() {
				$( this ).dialog( "close" );
				guardarSubRol();
				
			},
			"Cancelar": function() {
				$( this ).dialog( "close" );
				
				
			}
		}
	});
	$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
}

</script>
</head>
<body>
	<table id="tblButtons1" align="center">
		<tr>
			<td align="center" width="50%">
				<input id="btnSubRol" class="btn_Generico" type="button" onclick="agregaNuevoRol()" value="Agregar Sub Rol">
			</td>
		</tr>	
	</table>
	<table id="list1"></table>
	<div id="pager1"></div>
<br>
	<table id="list2"></table>
	<div id="pager2"></div>
	<table id="tblButtons" align="center">
		<tr>
			<td align="center" width="50%">
				<input id="btnGuardar" class="btn_Generico" type="button" onclick="saveValues()" value="Guardar">
			</td>
		</tr>	
	</table>

</body>
<!-- dialogos para Sub Rol-->
	<div id="dialog-subRol" title="Agregar Sub Rol"  style="display: none;">
		<p align="center">
			<table border="0">
				<tr>
					<td>Nombre del Rol</td>
					<td>Rol</td>
				</tr>
				<tr>
					<td align="right"><label style="color:#4A5C68">Nombre del Sub Rol:</label></td>
					<td><input type="text" name="name" id="nameSubRol" value="" maxlength="50" size="20"></td>
				</tr>
				<tr>
					<td align="right"><label style="color:#4A5C68">Descripción del sub Rol:</label></td>
					<td><input type="text" name="dec" id="decSubRol" value="" maxlength="50" size="20"></td>
				</tr>
			</table>
		</p>
	</div>
</html>