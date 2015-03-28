<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Prueba Visor de elementos</title>
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/jquery-ui.css" media="screen" />-->
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" media="screen" />-->
<!--	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.multiselect.css" media="screen" />-->

	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery-ui-1.8.1.custom.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.contextmenu.js"></script>-->
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/ui.multiselect.js"></script>-->
<!--    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.layout-1.3.0.js"></script>-->

	<script type="text/javascript">
            $.jqgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;
	</script>
        
    <script type="text/javascript">
		$(document).ready(function(){
			
			jQuery("#gridCatDelitos").jqGrid({ 
				url:'<%= request.getContextPath()%>/CargarDelito.do', 
				datatype: "xml", 
				colNames:['Clave','Delito', 'Grave'], 
				colModel:[ 	{name:'Clave',index:'clave', width:60}, 
							{name:'Delito',index:'delito', width:250},
							{name:'Gravedad',index:'gravedad',width:80,align:'center',formatter:'checkbox'},
						],
				//pager: jQuery('#pager1'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE DELITOS",
				sortname: 'Clave',
				recordpos: 'left', 
				viewrecords: true,
				id: 'divgridIzq',
				onSelectRow: function(id){
					//detEvi(id);
					},
				sortorder: "desc"
			});
			
			
			jQuery("#gridDelitosAgraviados").jqGrid({ 
				url:'local',
				datatype: "local",
				colNames:['Clave','Delito', 'Grave'], 
				colModel:[ 	{name:'Clave',index:'clave', width:60}, 
							{name:'Delito',index:'delito', width:250},
							{name:'Gravedad',index:'gravedad',width:80,align:'center',formatter:'checkbox'},
						],
				//pager: jQuery('#pager2'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE DELITOS AGRAVIADOS",
				sortname: 'Clave',
				recordpos: 'left', 
				viewrecords: true,
				id: 'divgridDer',
				onSelectRow: function(){
					delitoPrincipal();
					},
				sortorder: "desc"
			});
			
			
			jQuery("#gridCatDelitos").jqGrid('gridDnD',{connectWith:'#gridDelitosAgraviados'}); 
			jQuery("#gridDelitosAgraviados").jqGrid('gridDnD',{connectWith:'#gridCatDelitos'});


			function delitoPrincipal(id){
				
				var rowd = jQuery("#gridDelitosAgraviados").jqGrid('getGridParam','selrow');
				
				if (rowd) { 
					var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',rowd);
					 document.getElementById('cveDelitoPrincipal').value=retd.Clave;
					 document.getElementById('delitoPrincipal').value=retd.Delito;
					 document.getElementById('graveDelitoPrincipal3').value=retd.Gravedad;				
				}
				else { 
					customAlert("Por favor seleccione un renglón");
				} 
			}
			
		});
	</script>
</head>
<body>
	<table border="1"  width="800px">
		<tr>
			<td colspan="3" >
				PRUEBA CON LOS GRIDS
			</td>
		</tr>
		<tr>
			<td>
				<table id="gridCatDelitos" width="400px"></table>
				<div id="pgrid1"></div>
			</td>
			<td>
				<table id="gridDelitosAgraviados" width="400px"></table>
				<div id="pgrid2"></div>
			</td>
			<td valign="top">
				<table width="200" border="1" cellspacing="0" cellpadding="0">
			    	<tr>
			        	<td colspan="3" align="center"></td>
			        </tr>
			      	<tr>
			        	<td align="center">Unidad de Investigaci&oacute;n</td>
			        	<td align="center">Justicia Alternativa/Restaurativa</td>
			        	<td align="center">Institucion Externa</td>
			      	</tr>
			      	<tr>
			        	<td></td>
			        	<td><input type="text" id="delitoPrincipal" /></td>
			        	<td><input type="text" id="graveDelitoPrincipal3" /></td>
			      	</tr>
			    </table>
			</td>
		</tr>
	</table>
</body>
</html>
