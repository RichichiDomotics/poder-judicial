<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccionar AMP</title>
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
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-es.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		buscarPersonal();
		$('#a1').click(muestraDatos);
	});	

	
	  //Llena el grid de Agentes del Ministerio Publico	  
	  function buscarPersonal(){
				jQuery("#gridDeAMP").jqGrid({
					url:'<%=request.getContextPath()%>/consultarAgentesDelMinisterioPublico.do', 
					datatype: "xml",  							
					colNames:['ID','Nombre del AMP'], 
					colModel:[  {name:'id',index:'id', width:50},
								{name:'nombre',index:'nombre', width:450}								
							],
							autowidth: false, 
							rowNum:10,
							rowList:[10,20,30],
							pager: jQuery('#pager1'), 
							sortname: 'id', 
							rownumbers: true,
							gridview: true, 
							viewrecords: true, 
							sortorder: "desc", 
							height: "100%",							
							caption:"Agente(s) del Ministerio publico encontrado(s)" 												
							});
	        	//$("#gridDeAMP").trigger("reloadGrid");
				validaSiHayRegistros();
	}//Cierra buscarPersonal
	
	function muestraDatos(){
		var id = jQuery("#gridDeAMP").jqGrid('getGridParam','selrow');
		if (id)	{
			var ret = jQuery("#gridDeAMP").jqGrid('getRowData',id);
			alert("ID="+ret.id+" Nombre="+ret.nombre);
		} else { alert("Por favor selecciona un Funcionario");}
	}
	
	
	function validaSiHayRegistros(){
		//if(parseInt(jQuery('#gridDeAMP').jqGrid('getGridParam','records')) == 0)
			//alert("No hay agentes del Ministerio p\u00FAblico registrados");	
	}
</script>	
	<table align="center" id="gridDeAMP" width="800px"></table>
	<div id="pager1"></div>	
	<br /><br />
	<a href="#" id="a1">Obt&eacute;n datos del Funcionario Seleccionado</a> <br />	
