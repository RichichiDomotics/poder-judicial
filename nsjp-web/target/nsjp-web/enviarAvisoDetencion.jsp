<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enviar Aviso de Detención</title>
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
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">

	var imputadoId = null;
	var noGralCaso = null;
		$(document).ready(function(){

			var expedienteId=42;
			
			  jQuery("#gridProbablesResponsablesDetenidos").jqGrid({ 
				    postData: {expedienteId: expedienteId},
					url: '<%= request.getContextPath()%>/consultarProbablesResponsablesDetenidos.do',
					datatype: "xml", 
					colNames:['Nombre','Delito(s)','Fecha-Hora de Detención','Lugar del Detenido','No. Caso'], 
					colModel:[ {name:'nombre',index:'nombre', width:200, align:"center"},
							   {name:'delitos',index:'delitos', width:200, align:"center"},
							   {name:'fechaHoraDetencion',index:'fechaHoraDetencion', width:300, align:"center"},
							   {name:'lugarDetenido',index:'lugarDetenido', width:300, align:"center"},
							   {name:'noCaso',index:'noCaso', width:300, align:"center", hidden:true}							   					    
							     
							    ],
				     rowNum:10, 
				     rowList:[10,20,30],
				     autowidth: true,
				     pager: '#pagered',
				     sortname: 'id',
				     multiselect:true,
				     viewrecords: true,
				     gridview: true, 
				     caption: "Probables Responsables detenidos", 
				     sortorder: "desc"				     
				    
					  }); 


			 $('#enviarAviso').click(enviarAvisoDetencion);
			});	


		function enviarAvisoDetencion(){
			 
	    	 noGralCaso = 123;

			var imputadosId;
			imputadosId = jQuery("#gridProbablesResponsablesDetenidos").jqGrid('getGridParam','selarrrow');
			alert(imputadosId); 

			imputadoId=imputadosId;

			$.ajax({
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/registrarAvisoDetencion.do?noCaso='+ noGralCaso +'&imputadoId='+ imputadoId +'',
	    	    data: '',
	    	    dataType: 'xml',
	    	    success: function(xml){	
	    	    
	    		}		
			});	

			}
					
</script>
</head>

	<body>
	<table width="80%" cellspacing="0" cellpadding="0" align="center">
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
    <td colspan="2" align="center"><table id="gridProbablesResponsablesDetenidos"></table>
			<div id="pagered" width="300"></div></td>
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
    <td colspan="2" align="center"><input type="button" value="Enviar Aviso" id="enviarAviso" class="btn_Generico"/></td>
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
	
</body>
	
</html>
