<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar días inhábiles</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estiloBoton.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript">

	var lstDiaInhabil = new Array();
	var idDiaInhabil = 0;
	var reta;	
	var pasa;

		$(document).ready(function(){
				
			$("#fecha").datepicker({
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true
			});			

			$("#fecha").attr("disabled","disabled");
			
			  jQuery("#gridDiasInhabiles").jqGrid({ 
					url: '<%= request.getContextPath()%>/consultarCatalogoDiasInhabiles.do',
					datatype: "xml", 
					colNames:['Día Inhábil','Descripción'], 
					colModel:[ {name:'diaInhabil',index:'diaInhabil', width:100,editable:true, align:"center"},
							   {name:'descripcion',index:'descripcion', width:200, align:"right",editable:true,align:"center"}						    
							     
							    ],
				     rowNum:10, 
				     rowList:[10,20,30],
				     autowidth: true,
				     pager: '#pagered',
				     sortname: 'id',
				     //onSelectRow: function(){
							
					 //		},
				     viewrecords: true,
				     gridview: true, 
				     caption: "Administracion de días inhábiles", 
				     sortorder: "desc", 
				     editurl: "http://localhost:8080/nsjp-web/administrarDiasInhabiles.jsp"
				    
					  }); 

			 $('#agregarDiaInhabil').click(registraDiaInhabil);
			 $('#eliminarDiaInhabil').click(eliminarDiaInhabil);
			});	

		
		function registraDiaInhabil(){

			$("#divAgregarDiaInhabil").dialog("open");
			$("#divAgregarDiaInhabil").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Agregar Día Inhábil', 
			  	dialogClass: 'alert',
			  	position: [312,40],
			  	width: 440,
			  	height: 240,
			  	buttons:{"Agregar":function() {	
	  					var params = 'fecha=' + $("#fecha").val();
	  					params += '&tipoMovimiento=' + $("#tipoMovimiento").val();
	  					params += '&motivoDiaInhabil=' + $("#diaInhabil").val();
	  						
	  					$.ajax({
	  			    		type: 'POST',
	  			    	    url: '<%=request.getContextPath()%>/ingresarDiaInhabil.do',
	  			    	    data: params,
	  			    	    dataType: 'xml',
	  			    	    success: function(xml){
	  			    	    	jQuery("#gridDiasInhabiles").trigger("reloadGrid");
	  			    		}
	  					});			  			  			  					                                                         
	  					$(this).dialog("close");
	  					jQuery("#gridDiasInhabiles").trigger("reloadGrid");
				  	},
			  		"Cancelar":function() {
			  			$(this).dialog("close");
			  		}
				}
			});	  	
		}

	function eliminarDiaInhabil(){
		
  		var id = jQuery("#gridDiasInhabiles").jqGrid('getGridParam','selrow');
		if( id != null ){

		$("#divEliminarDiaInhabil").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Eliminar Día Inhábil', 
		  	dialogClass: 'alert',
		  	position: [312,40],
		  	width: 440,
		  	height: 240,
		  	buttons:{
		  		"Eliminar":function() {			  		
					var params = 'idDiaInhabil='+id;
					$.ajax({
		  				type: 'POST',
		  				url: '<%=request.getContextPath()%>/eliminarDiaInhabil.do',
		  				data: params,
			  			dataType: 'xml',
			  			success: function(xml){				  				
			  			}
					});							
					jQuery("#gridDiasInhabiles").jqGrid('delRowData',id);
					alertDinamico("El día inhábil seleccionado ha sido eliminado correctamente");				  			  	
  					$(this).dialog("close");
  					jQuery("#gridDiasInhabiles").trigger("reloadGrid");
				},
		  		"Cancelar":function() {
		  			$(this).dialog("close");
		  		}
			}
		});		
		}else {
			alertDinamico("Por favor seleccione un día a eliminar"); 
		} 
	}
				
</script>
</head>

	<body>	
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
	
	<table width="600" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="40">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="36">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center"><table id="gridDiasInhabiles"></table>
			<div id="pager4" width="300"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="201" align="center">&nbsp;</td>
    <td width="221" align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><input type="button" value="Agregar" id="agregarDiaInhabil" class="btn_Generico"/></td>
    <td align="center"><input type="button" value="Eliminar"id="eliminarDiaInhabil" class="btn_Generico"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<div id="divAgregarDiaInhabil" style="display:none">
	<table width="408" cellspacing="0" cellpadding="0" align="center">
  		<tr>
    		<td width="35">&nbsp;</td>
    		<td width="151">&nbsp;</td>
    		<td width="183">&nbsp;</td>
    		<td width="29">&nbsp;</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
		    <td>&nbsp;</td>
    		<td align="right"><strong>Fecha:</strong></td>
    		<td><input type="text" id="fecha"/></td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td align="right"><strong>Motivo de d&iacute;a inh&aacute;bil:</strong></td>
    		<td align="left"><input type="text" id="diaInhabil"/></td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td colspan="2" align="center">&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
  		<tr>
    		<td>&nbsp;</td>
    		<td colspan="2" align="center">&nbsp;</td>
    		<td>&nbsp;</td>
  		</tr>
	</table>
</div>	

<div id="divEliminarDiaInhabil" style="display:none">
	<table width="300" cellspacing="0" cellpadding="0" align="center">
  		<tr>
  			</br></br></br></br>
  		    <td align="center"><b>¿Está seguro que desea eliminar el día inhábil?</b></td>	
  		</tr>
  	</table>
</div>
	
</body>
	
</html>