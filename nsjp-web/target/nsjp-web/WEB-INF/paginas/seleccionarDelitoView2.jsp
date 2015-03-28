<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Seleccionar Delito</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
            $.jgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;
	</script>
        
    <script type="text/javascript">

    	var contDelitosGraves=0;
		var idExpediente2="";
    	var numeroExpedienteId2=0;
    	
		$(document).ready(function(){
			
			idExpediente2='<%= request.getParameter("idExpedienteop")%>';
			numeroExpedienteId2='<%= request.getParameter("idNumeroExpediente")%>';
			
			if(idExpediente2=='null')
			{
				idExpediente2='<%= request.getSession().getAttribute("idExpedienteConsulop")%>';
			}
			
			jQuery("#gridCatDelitos2").jqGrid({ 
				url:'<%= request.getContextPath()%>/CargarDelito.do', 
				datatype: "xml", 
				colNames:['Clave','Delito', '¿Es grave?','¿Es grave?','Delito Principal','Tipo'], 
				colModel:[ 	{name:'Clave',index:'clave', width:40}, 
							{name:'Delito',index:'delito', width:150}, 
							//{name:'Gravedad',index:'gravedad', width:100}
							{name:'Gravedad',index:'gravedad',width:45,align:'center',formatter:'checkbox',hidden:true},
							{name:'GravedadFormateada',index:'GravedadFormateada',width:45,align:'center'},
							{name:'Principal',index:'principal',width:50,hidden: true,align:'center'},
							{name:'Tipo',index:'tipo',width:50,hidden: true,align:'center'},
						],
				//pager: jQuery('#pager1'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE DELITOS",
				sortname: 'Clave',
				viewrecords: true,
				id: 'divgridIzq2',
				onSelectRow: function(id){
					//detEvi(id);
					},
				sortorder: "desc"
			});
			
			
			jQuery("#gridDelitosAgraviados2").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultaDelitoPorExpedienteGrid.do?idNumeroExpediente='+idExpediente2+'&numeroExpedienteId='+numeroExpedienteId2+'',
				datatype: "xml",
				colNames:['Clave','Delito', '¿Es grave?','¿Es grave?','Delito Principal','Tipo'], 
				colModel:[ 	{name:'Clave',index:'clave', width:40}, 
							{name:'Delito',index:'delito', width:130}, 
							{name:'Gravedad',index:'gravedad',width:45,align:'center',formatter:'checkbox',hidden:true},
							{name:'GravedadFormateada',index:'gravedadFormateada',width:50,align:'center'},
							{name:'Principal',index:'principal',width:95,align:'center'},
							{name:'Tipo',index:'tipo',width:50,hidden: true,align:'center'},
						],
				//pager: jQuery('#pager2'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE DELITOS AGRAVIADOS",
				sortname: 'Clave',
				viewrecords: true,
				id: 'divgridDer2',
				onSelectRow: function(){
					//detEvi(id);
					},
				sortorder: "desc"
			});
		});

		function addRowRigthToLeft2(){
			
			var row = jQuery("#gridCatDelitos2").jqGrid('getGridParam','selrow');
			
			if (row) { 
				var ret = jQuery("#gridCatDelitos2").jqGrid('getRowData',row);
				 
				jQuery("#gridDelitosAgraviados2").jqGrid('addRowData',ret.Clave,ret);
				jQuery("#gridCatDelitos2").jqGrid('delRowData',ret.Clave); 
				
			} else { alert("Por favor seleccione un renglon");} 	 				
		} 

		function addRowLeftToRigth2(){
			
			var rowd = jQuery("#gridDelitosAgraviados2").jqGrid('getGridParam','selrow');

			
			if (rowd) { 
				
				var retd = jQuery("#gridDelitosAgraviados2").jqGrid('getRowData',rowd);

				//if(retd.Clave == document.getElementById('cveDelitoPrincipal').value)
				//{
					//alert("El delito fue clasificado como principal");
					//limpiar();
				//}
					
					
				jQuery("#gridCatDelitos2").jqGrid('addRowData',retd.Clave,retd);
				jQuery("#gridDelitosAgraviados2").jqGrid('delRowData',retd.Clave);
			
			} else { alert("Por favor seleccione un renglon");} 	 				
		} 


		function delitoPrincipal2(){
			
			var rowd = jQuery("#gridDelitosAgraviados2").jqGrid('getGridParam','selrow');
			
			if (rowd) { 
				var retd = jQuery("#gridDelitosAgraviados2").jqGrid('getRowData',rowd);
				 document.getElementById('cveDelitoPrincipal').value=retd.Clave;
				 document.getElementById('delitoPrincipal').value=retd.Delito;
				 if(retd.Gravedad == "Yes")
				 	document.getElementById('graveDelitoPrincipal').value="Si";
				 else
					 document.getElementById('graveDelitoPrincipal').value=retd.Gravedad;				
			}	
			else { 
				alert("Por favor seleccione un renglon");
			} 
		}

		function limpiar2(){
			document.getElementById('cveDelitoPrincipal').value="";
			document.getElementById('delitoPrincipal').value="";
		 	document.getElementById('graveDelitoPrincipal').value="";	
		}
		
	</script>
</head>
<body>
	<table border="0"  width="900px">
		<tr>
			<td height="20" colspan="4" align="right" ><input type="button" id="btnGuardarDelitosAg2" value="Guardar" onclick="guardarDelitosAgraviadosExp2();" class="btn_Generico"/>
				
			</td>
		</tr>
		<tr>
			<td>
				<table id="gridCatDelitos2" width="370px"></table>
				<div id="pager1"></div>
			</td>
			<td>
				<input type="button" id="pasar" value=">>" onclick="addRowRigthToLeft2();" class="btn_Generico">
				<input type="button" id="pasarD" value="<<" onclick="addRowLeftToRigth2();" class="btn_Generico">
			</td>
			<td>
				<table id="gridDelitosAgraviados2" width="380px"></table>
				<div id="pgrid2"></div>
				
				<span id="leyendaUnDelitoGrave2">Se debe canalizar a la Unidad de Fiscales Investigadores</span>
				<span id="leyendaNingunDelitoGrave2">Si no hay reincidencia por parte del probable responsable,<br/>
				se debe canalizar a la Unidad de Solución de Controversias</span>
				
			</td>
			<td width="100" valign="top" align="center">&nbsp;</td>
		</tr>
	</table>
</body>
</html>