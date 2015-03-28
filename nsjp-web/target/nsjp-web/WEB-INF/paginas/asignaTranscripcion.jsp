<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<style>
.transpa {
background-color: transparent;
border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

var idAudienciaTranscripcion = parent.idAudiencia;
var varResolutivo ="";
var documentoID="";

$(document).ready(function() {
grid();

});

	function marcaAudiencia() {
		
		parent.actualizaGridResolutivaAudiencia();
	var param ="";
	param += 'idAudiencia='+idAudienciaTranscripcion;
	
	   $.ajax({
	     type: 'POST',
	     url: '<%= request.getContextPath()%>/MarcarAudienciaResolutivos.do',
		 data: param,
		 dataType: 'xml',
		 success: function(xml){
		  }
		});

	   $windowCloseButton.click();
	 }

function agregarEtapa(){

	
	$("#nabtabgrid2").dialog("open");
	$("#nabtabgrid2").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Resolutivo', 
	  	dialogClass: 'alert',
	  	position: [30,30],
	  	width: 237,
	  	height: 241,
	  	maxWidth: 500,
	  	buttons:{"Agregar":function() {	

	  					  					 
	  			$(this).dialog("close");
	  		},
	  		"Cancelar":function() {
	  			$(this).dialog("close");
	  		}
	  	}
	});	
	    
		 
		  
}	

function grid(){

	
	  jQuery("#tabgrid").jqGrid({ 
	url:'<%= request.getContextPath()%>/CargaResolutivos.do?idAudiencia='+idAudienciaTranscripcion,
	data:"",
	datatype: "xml", 
	colNames:['Resolutivo'], 
	colModel:[ 	{name:'Resolutivo',index:'resolutivo', width:200, resizable:true}
				
			],
	resizable:true,
	pager: jQuery('#tabgrid'),
	rowNum:10,
	rowList:[10,20,30],
	width: 'auto',
	height: 'auto',
	sortname: 'detalle',
	viewrecords: true,
	sortorder: "desc",
	//multiselect: true,
	
	
		onresize_end:			function () { $("#tabgrid").setGridWidth($("#mainContent").width() - 5, true); 
	 },
	 

	
	ondblClickRow: function(rowID) {
		varResolutivo=rowID;
		grid2();
		agregarEtapa();
		

		
			
	
			}
}).navGrid('#tabgrid',{edit:false,add:false,del:false});	


}

function grid2(){
	
	
	  jQuery("#tabgrid2").jqGrid({ 
			url:'<%= request.getContextPath()%>/CargaPlantillas.do?idResolutivo='+varResolutivo, 
			data:"",
			datatype: "xml", 
			colNames:['Nombre'], 
			colModel:[ 	{name:'Nombre',index:'nombre', width:200, resizable:true}
			           	
						
					],
			resizable:true,
			pager: jQuery('#tabgrid2'),
			rowNum:10,
			rowList:[10,20,30],
			width: 'auto',
			height: 'auto',
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			//multiselect: true,
			
			
				onresize_end:			function () { $("#tabgrid2").setGridWidth($("#mainContent").width() - 5, true); 
			 },
			 

			
			ondblClickRow: function(rowID) {
				documentoID=rowID;
				parent.temVarResolutivo=varResolutivo;
				parent.temDocumentoID=documentoID;
				parent.agarraGenerarDocumento();
				
				$("#nabtabgrid2").dialog("close");		
			
					}
		}).navGrid('#tabgrid2',{edit:false,add:false,del:false});	
		
}

</script>

<title>Insert title here</title>
</head>
<body>
<table  style="border-left: 1;border-right: 1;border-bottom: 1;border-top: 1;" >
<tr class="fondoFuerteAP">

    <td >&emsp;
</td>
  </tr>
  <tr align="center" >

    <td><div id="nabtabgrid"> <table id="tabgrid" align="center" ></table></div>
</td>
  </tr>
</table>

<input type="button" value="Marcar Audiencia" onclick="marcaAudiencia()" class="btn_Generico">


<div id="nabtabgrid2" style="display: none;"> <table id="tabgrid2" align="center" ></table></div>
<script type="text/javascript">

</script>
</body>
</html>