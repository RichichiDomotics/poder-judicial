<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/reloj.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 		   src="<%= request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">

	var numeroExpedienteId = '<%=request.getParameter("numeroExpedienteId")%>';
	
	$(document).ready(function() {
		jQuery("#gridCasos").jqGrid({
			url:'<%= request.getContextPath()%>/consultarCasosPorFiltro.do', 
			datatype: "xml", 
			colNames:['casoId','Numero General de Caso','Apertura','Imputado', 'involucradoId'], 
			colModel:[{name:'casoId',	index:'casoId', 	width:300, align:"center", hidden:true},
			          {name:'caso',	 	index:'caso', 		width:300, align:"center"},
			          {name:'fecha',	index:'fecha', 		width:150, align:"center"},
			          {name:'imputado',	index:'imputado', 	width:300, align:"center"},
			          {name:'involucradoId',	index:'involucradoId', 	width:300, align:"center", hidden:true}
					],
			pager: jQuery('#paginadorCasos'),
			rowNum:10,
			rowList:[10,20,30],
			width:"100%",
			height:250,
			//width: 500,
			//autoheight:true,
			viewrecords: true,
			sortname: 'caso',
			sortorder: "desc"
		}).navGrid('#paginadorCasos',{edit:false,add:false,del:false});
		$("#gridCasos .ui-jqgrid-bdiv").css('height', '450px');
	});
	
	
	function buscarCasosParaVincular(){
		var numeroCaso = document.formaBusqueda.numeroCaso.value;
		jQuery("#gridCasos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarCasosPorFiltro.do?numeroCaso='+numeroCaso});
		jQuery("#gridCasos").trigger('reloadGrid');
	}
	
	function vincularCaso(){
		var rowid = jQuery("#gridCasos").jqGrid('getGridParam','selrow');
		var ret = jQuery("#gridCasos").jqGrid('getRowData',rowid);
		var param = "numeroExpedienteId="+numeroExpedienteId+"&casoId="+ret.casoId+"&involucradoId="+ret.involucradoId;
	   	$.ajax({
	   		type: 'POST', data: param, dataType: 'xml',
	   		url: '<%= request.getContextPath()%>/vincularNumeroExpedienteConCaso.do',
	   		async: false,
	   		success: function(xml){
	   			if($(xml).find('respuesta').text() == 'exito'){
					alert("El expediente se asocio exitosamente");
	   			}
	   		}
	   	});			
	}
	
</script>
</head>

<body bgcolor="#DDDDDD">
	<div>
		<table>
			<tr>
				<td>
				<form id="formaBusqueda" name="formaBusqueda">
					<input type="text"   value="" id="numeroCaso" name="numeroCaso" style="width:350px"/>
					<input type="button" value="Buscar Caso" onclick="buscarCasosParaVincular();" class="btn_Generico"/>
				</form>
				</td>
			</tr>
			<tr id="resultadoBusquedaCaso">
				<td>
					<div id="contenedorGridCasos">
						<table id="gridCasos"></table>
						<div id="paginadorCasos"></div>
					</div>
					<input type="button" value="Vincular" onclick="vincularCaso()" class="btn_Generico"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>