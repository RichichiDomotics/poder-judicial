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
	//alert("accesa tab");
	buscarExpediente();
	$('#btnSeleccionarAlmacen').hide();
	$('#btnCambiarAlmacen').hide();
	$('#btnGuardar').hide();
});

var idNumeroExpediente;
var identificadorAlmacen;
var descAlmacen;

function buscarExpediente(){
   $.ajax({
	     type: 'POST',
	     url: '<%= request.getContextPath()%>/buscarExpedienteAlmacenPorExpedienteId.do',
		 data: 'idNumeroExpediente=' + '<%=request.getParameter("idNumeroExpediente")%>',
		 dataType: 'xml',
		 success: function(xml){
    	    	idNumeroExpediente = $(xml).find('expediente').find('numeroExpedienteId').text();
    	    	identificadorAlmacen = $(xml).find('expediente').find('almacenDTO').find('identificadorAlmacen').text();
    	    	
				if(identificadorAlmacen == null || identificadorAlmacen == undefined || identificadorAlmacen == ""){
					$('#btnSeleccionarAlmacen').show();
				}else{
					var nombreAlmacen = $(xml).find('expediente').find('almacenDTO').find('nombreAlmacen').text();
					var domicilio = $(xml).find('expediente').find('almacenDTO').find('domicilio').find('descripcion').text()
								   + ' ' + $(xml).find('expediente').find('almacenDTO').find('domicilio').find('calle').text()
								   + ' No. Ext. ' + $(xml).find('expediente').find('almacenDTO').find('domicilio').find('numeroExterior').text();
					var descripcion = $(xml).find('expediente').find('almacenDTO').find('descripcion').first().text();
					descAlmacen = nombreAlmacen + ", " + domicilio + ", " + descripcion;
					$('#tblAlmacen').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;'+descAlmacen+'</td></tr>');				
    	    		$('#btnCambiarAlmacen').show();					
				} 	
		  }
		});
}

function seleccionarAlmacen(){
	$.newWindow({id:"iframewindowSeleccionarAlmacen", statusBar: true, posx:255,posy:110,width:800,height:380,title:"Seleccionar Almacen de Expedientes", type:"iframe"});
   	$.updateWindowContent("iframewindowSeleccionarAlmacen",'<iframe src="<%= request.getContextPath() %>/seleccionarAlmacenExpediente.do" width="815" height="380" />');		
}

function cargaAlmacen(id, desc){	
	var rowCount = $('#tblAlmacen tr').length;
	if(rowCount > 1){
		$('#tblAlmacen tr:last').remove();
	}
	$('#tblAlmacen').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;'+desc+'</td></tr>');
	cerrarVentanaAlmacen();
	identificadorAlmacen = id;
	$('#btnGuardar').show();
} 
	
function cerrarVentanaAlmacen(){
	$.closeWindow("iframewindowSeleccionarAlmacen");
}

function guardar(){
   $.ajax({
	     type: 'POST',
	     url: '<%= request.getContextPath()%>/asociarExpedienteAlmacen.do',
		 data: 'idNumeroExpediente=' + idNumeroExpediente + '&identificadorAlmacen=' + identificadorAlmacen,
		 dataType: 'xml',
		 success: function(xml){
			var estatus = $(xml).find('estatus').text();
			if(estatus == "success"){
				alert("Almacen asociado de manera exitosa");
			}else{
				alert("Error: la asociación del almacen no se realizó.");
			}			
		  }
		});
   $('#btnGuardar').hide();
   $('#btnSeleccionarAlmacen').hide();
   $('#btnCambiarAlmacen').show();
}

</script>

<table id="tblAlmacen" width="650" border="0" cellspacing="0" cellpadding="0">    
	<tr >
    	<td height="25" colspan="2" align="left">
    		<input type="button" name="btnSeleccionarAlmacen" value="Seleccionar almacen de expedientes" id="btnSeleccionarAlmacen" onclick="seleccionarAlmacen();" class="btn_Generico"/>
    		<input type="button" name="btnCambiarAlmacen" value="Cambiar expediente de almacen" id="btnCambiarAlmacen" onclick="seleccionarAlmacen()" class="btn_Generico"/>
    		<input type="button" name="btnGuardar" value="Guardar" id="btnGuardar" onclick="guardar()" class="btn_Generico"/>
    	</td>
    </tr>
</table>

</body>
</html>