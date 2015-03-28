<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Expediente</title>
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
		var numeroExpedienteId= '<%=request.getParameter("numeroExpedienteId")!=null?request.getParameter("numeroExpedienteId"):""%>';
		var numeroExpediente = '<%=request.getParameter("numeroExpediente")!=null?request.getParameter("numeroExpediente"):""%>';
		var rolUsuario = '<%=request.getParameter("rolUsuario")!=null?request.getParameter("rolUsuario"):""%>';
		$("#numExpediente").val(numeroExpediente);
		cargarGridAudienciasDeCausa(numeroExpedienteId,rolUsuario);
	});

	/*
	 *Funcion que carga el grid de las audoiencias asociadas al numero de expediente
	 *que están en estatus de terminada
	 */
	function cargarGridAudienciasDeCausa(numeroExpedienteId,rolUsuario) {

		//Se llena el gird de audiencias
		jQuery("#gridAudiencias").jqGrid({
			url : '<%= request.getContextPath()%>/consultarAudienciasPorNumeroCausaYEstatus.do?numeroExpedienteId='+numeroExpedienteId+'', 
			datatype: "xml", 
			colNames:['Fecha','Hora','Tipo','Sala'], 
			colModel:[	{name:'fecha',index:'fecha', width:150, align:"center"},
						{name:'hora',index:'hora', width:100, align:"center"},  
						{name:'tipo',index:'tipo', width:150, align:"center"},
						{name:'sala',index:'sala', width:200, align:"center"}
					],
			pager: jQuery('#pagerGridAudiencias'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth:false,
			height:250,
			sortname:'fecha',
			viewrecords: true,
			sortorder:"desc",
			ondblClickRow: function(rowid) {
					audienciasid=rowid.split("*")[0];
					numExpediente=rowid.split("*")[1];
					if(rolUsuario == "encargadoSala"){
						//Abre el visor de audiencias del encargado sala
						top.visorAudienciaPJENS(audienciasid,numExpediente);
					}
					if(rolUsuario == "juezPJ"){
						top.mostrarVisorAudienciaJuezPJJU(audienciasid);
					}
					if(rolUsuario == "admonPJ"){
						top.dblClickRowBandejaAudiencias(audienciasid);
					}
				}
			});
		}

	
</script>

</body>
	<table width="500" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="306" >&nbsp;
	    </td>
	  </tr>
	  <tr>
	    <td height="25" colspan="2" align="center">Audiencias del Expediente:&nbsp;
			<input type="text" id="numExpediente" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />&nbsp;&nbsp;</td>
	    </tr>
	
	  <tr >
	    <td height="25" colspan="2" align="center">&nbsp;</td>
	    </tr>
	    <tr>
	     <td align="center">
	       <table align="center" id="gridAudiencias"></table>
	       <div id="pagerGridAudiencias"></div>
		 </td>
	
	</table>
</body>
</html>