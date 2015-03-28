<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Cancelar AMP</title>
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%= request.getContextPath()%>/EjemploListaAMPs.xml', 
			datatype: "xml", 
			colNames:['AMP'], 
			colModel:[ 	{name:'Amp',index:'amp', width:190}
					],
			pager: jQuery('#pager1'),
			rowNum:0,
			rowList:[0,0,0],
			autowidth: true,
			sortname: 'turno',
			viewrecords: true,
			id: 'divgrid',
			sortorder: "desc"
		}).navGrid('#pager1',{edit:false,add:false,del:false});
	});
	</script>
</head>
<body>
	<br/>
	<br/>
	<table width="340">
		<tr>
			<td width="20">
			</td>
			<td width="300">
			
				<table border="0" cellspacing="0" cellpadding="0" width="295">
					<tr>
						<td width="250" align="center">
							<table id="gridDetalleFrmPrincipal"></table>
							<!-- <div id="pager1"></div>-->
						</td>
					</tr>
				</table>
			
			</td>
			<td width="20">
			</td>
		</tr>
		<tr>
			<td width="20">
			</td>
			<td width="300">
			
				<table border="0" cellspacing="0" cellpadding="0" width="295">
					<tr>
						<td align="center">
							<input type="button" value="Asignar" id="btnAsignar" class="btn_Generico"/>
						</td>
						<td align="center">
							<input type="button" value="Cancelar" id="btnCancelar" class="btn_Generico"/>
						</td>
					</tr>
				</table>
			
			</td>
			<td width="20">
			</td>
		</tr>
	</table>
</body>
</html>