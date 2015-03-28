<%@page
	import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>


<style>
.transpa {
	background-color: transparent;
	border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">


//alert(vardocumentoID);
var idExpediente =""; 

var reloadGrid=false;

var reloadGrid2=false;

$(document).ready(function() {
	$("#tabsDetalleAudiencias").tabs();
	grid();


	var config = {toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Table','HorizontalRule'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		]
	};
	$('.jquery_ckeditor').ckeditor(config);


	 });


function tipoEslabon(){

	
	$("#nabtabgrid2").dialog("open");
	$("#nabtabgrid2").dialog({ autoOpen: true, 
		modal: true, 
	  	title: 'Resolutivo', 
	  	dialogClass: 'alert',
	  	position: [30,30],
	  	width: 237,
	  	height: 241,
	  	maxWidth: 500,
	  	buttons:{"Registrar Movimiento en Almacén":function() {	

	  					  					 
	  			$(this).dialog("close");
	  		},
	  		"Relación de Evidencias con Movimiento":function() {
	  			$(this).dialog("close");
	  		}
	  	}
	});	
	    
		 
		  
}	

function buscaCarpeta() {
	idExpediente="";
	var param ="";
	param += 'audienciaNum='+ $("#numCarpetaPJ").val();
	
	//alert(param);
	   $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/BuscaCarpetaEjecucion.do',
		 data: param,
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 idExpediente = $(xml).find('').find('').text();
			 
			
		  }
		});
	   //alert(idExpediente);
	   //if (idExpediente!=""){
		 
			 grid();
			 $("#catEslabon").css("display","block");
			 
		// }else{ 

//			 alert("no se encontro");
	//	 }
	 }



function grid(){
	
	//alertidExpediente);
	if (reloadGrid==true) {
		  jQuery("#tabgrid").jqGrid('setGridParam', {postData: {audienciaid: idExpediente}});
		  $("#tabgrid").trigger("reloadGrid"); 
	  } else {
		  reloadGrid=true;
	  jQuery("#tabgrid").jqGrid({ 
	url:'<%=request.getContextPath()%>/.do',
	
		
							data : "",
							datatype : "xml",
							colNames : ['Nombre','Calidad','Edad'],
							colModel : [ {name : 'Nombre',index : 'Nombre',	width : 200,resizable : true},
										{name : 'Calidad',index : 'Calidad',	width : 200,resizable : true},	
							             {name : 'Edad',index : 'Edad',	width : 200,resizable : true},
							             
								],
							resizable : true,
							pager : jQuery('#paginadorTabgrid'),
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							width : 'auto',
							height : 200,
							sortname : 'detalle',
							viewrecords : true,
							sortorder : "desc",
							multiselect: true,
							
							//multiselect: true,

							onresize_end : function() {
								$("#tabgrid").setGridWidth(
										$("#mainContent").width() - 5, true);
							}
						}).navGrid('#tabgrid', {
					edit : false,
					add : false,
					del : false
				});
	  }
	 
	}



	
	function tipoBusqueda(){
		//funcion en caso de confirm
			buscaCarpeta();

					
	}

	
	function lanzaRegistrarEntradasAlmacen() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:650,height:400,title:"Conciliacion Mediación", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/registrarEntradaAlmacen.do" width="650" height="400" />');
	    		
	}	
</script>

<title>Insert title here</title>
</head>
<body>

<div id="tabsDetalleAudiencias">
		<ul>
			<li id="detalleAudiencias"><a href="#tabsDetalleAudiencias-1">Involucrado</a></li>
			<li id="involucrados"><a href="#tabsDetalleAudiencias-2">Acuerdo</a></li>
			
		</ul>
		
		<div id="tabsDetalleAudiencias-1" >
		<table id="tabgrid" ></table>
		<div id="paginadorTabgrid"></div>
		</div>
		
		<div id="tabsDetalleAudiencias-2" >
		<textarea class="jquery_ckeditor" name="motivoDEATT" cols="70" rows="5" id="motivoDEATT"></textarea>
		</div>
		
		</div>




<script type="text/javascript">
	
</script>
</body>
</html>