<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Expediente</title>
</head>

<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

<script type="text/javascript">

	var reloadGridAlias = false;
	var reloadGridNombre = false;
	var reloadGridEvidencia = false;
	var reloadGridExpediente = false;
    var validaEvidencia = false;
    var validaNombre = false;
    var validaExpediente = false;
	$(document).ready(function() {
		enableControls('0');
		deshabilitaOpciones();      //funcion que deshabilita opciones de buscar por alias
		cargaCatEvidencia();		//Funcion que carga el combo de tipo de evidencia en buscar por evidencia
		enableControlsEvidencia('0');		//funcion que habilita controles en evidencia
	    $("#cmbTipoBusqueda").change(onSelectChangeTipo);
	   	$("#buscarAlias").bind("click",llenaGridAlias);
		$("#alias").bind("keypress",habilitaOpciones);
		$("#catEvidencia").change(onSelectChangeTipoEv);
		$("#buscarEvidencia").bind("click",validaCamposEvidencia);
		$("#buscarEvidencia").bind("click",llenaGridEvidencia);
		$("#buscarNombre").bind("click",validaCamposNombre);
		$("#buscarNombre").bind("click",llenaGridNombre);
		$("#buscarExpediente").bind("click",validaCamposExpediente);
		$("#buscarExpediente").bind("click",llenaGridExpediente);
});

	
	/**
	*Funcion que obtiene el texto seleccionado del textarea de descripcion
	*/    
	function getTextSub() 
	{ 
	   var textoSeleccionado="";
	   //IE support
		if (document.selection)
		{
		   $("#txtDescripcion").focus();
		   sel = document.selection.createRange();
		   textoSeleccionado=sel.text;
	    }
	    //MOZILLA/NETSCAPE support
	    else if ($("#txtDescripcion")[0].selectionStart || $("#txtDescripcion")[0].selectionStart == "0")
	    {
			    var startPos = $("#txtDescripcion")[0].selectionStart;
				var endPos = $("#txtDescripcion")[0].selectionEnd;
			textoSeleccionado=$("#txtDescripcion").val().substr(startPos, endPos - startPos);
	    }
	}  
		
	
/*
* Funcion para llamar la funcion de habilitar los elementos de la pantalla
*/
function onSelectChangeTipo() {
  	var selected = $("#cmbTipoBusqueda option:selected");		
	enableControls(selected.val());
}
    
/*
*Funcion que habilita o deshabilita los elementos de la pagina dependiento del tipo 
* de narrativa seleccionada
*/
function enableControls(tipo){
		
	if (parseInt(tipo) == 1||parseInt(tipo) == 2||parseInt(tipo) == 3||parseInt(tipo) == 4)
		{
		
		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");

		if (parseInt(tipo) == 1){
			limpiaCampos();
			$("#porAlias").css("display", "block");
			}
		else{

		
		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");
		
				if (parseInt(tipo) == 2){
					limpiaCampos();
					$("#porEvidencia").css("display", "block");
				}
				else{

					
					$("#porAlias").css("display", "none");
					$("#porEvidencia").css("display", "none");
					$("#porNombre").css("display", "none");
					$("#porExpediente").css("display", "none");

						if (parseInt(tipo) == 3){
							limpiaCampos();
							$("#porNombre").css("display", "block");
			   			 }

				   	else{


				   		
				   		$("#porAlias").css("display", "none");
						$("#porEvidencia").css("display", "none");
						$("#porNombre").css("display", "none");
						$("#porExpediente").css("display", "none");
						
						    if (parseInt(tipo) == 4){

						    	limpiaCampos();
								$("#porExpediente").css("display", "block");
							}
						}
					}
		
				}
		
			}
	
	else{
		
   		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");
	}
}


	function limpiaCampos(){
		
		$("#alias").val("");
		$("#palabraClave").val("");
		$("#palabraClave2").val("");
		$("#palabraClave3").val("");
		$("#palabraClave4").val("");
		$("#palabraClave5").val("");
		$("#nombre").val("");
		$("#apellido").val("");
		$("#noExpediente").val("");

		}
			
</script>

<body>
<table width="700" cellspacing="0" cellpadding="0">
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"> 
      Tipo de busqueda:
        <select name="cmbTipoBusqueda" id="cmbTipoBusqueda">
        <option value="-1" >-Seleccione-</option>
        <option value="1" >Por Especialidad</option>
        <option value="2" >Como Funcionario Publico</option>
	</select></td>
  </tr>
  <tr>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td width="120"><div id="porAlias" style="display: none;"><jsp:include page="buscarExpedientePorAlias.jsp"></jsp:include></div>
    	<div id="porEvidencia" style="display: none;">uno</div>
    	<div id="porNombre" style="display: none;">dos</div>
    	<div id="porExpediente" style="display: none;">tres</div>
   </td>
  </tr>   
</table>




</body>
</html>