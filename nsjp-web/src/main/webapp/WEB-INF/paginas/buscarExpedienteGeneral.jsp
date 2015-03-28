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

	
	
	
	
    var validaEvidencia = false;
    var validaNombre = false;
    var validaExpediente = false;
    
    tipoExpedienteBuscado = <%=request.getParameter("tipoExpedienteBuscado")%>;

   
	$(document).ready(function() {

				
		enableControls('0');
		deshabilitaOpciones();      //funcion que deshabilita opciones de buscar por alias
		cargaCatEvidencia();		//Funcion que carga el combo de tipo de evidencia en buscar por evidencia
		enableControlsEvidencia('0');		//funcion que habilita controles en evidencia
	    //$("#cmbTipoBusqueda").change(onSelectChangeTipo);
	   	
		$("#alias").bind("keypress",habilitaOpciones);
		//$("#catEvidencia").change(onSelectChangeTipoEv);
		
		
		
		
			
		$("#etiquetaNumExp").html("Número de Causa");
		$('#cmbTipoBusqueda').multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeTipo();}
		});

		$('#catEvidencia').multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeTipoEv();}
		});
	
});

	function seleccionarNumeroExpediente(id,numero){
		
		parent.seleccionarExpediente(id,numero);
	}
	
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
	intTipo = 	parseInt(tipo);
	if (intTipo > 0)
	{
		
		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");
		$("#porCaso").css("display", "none");
		limpiaCampos();
		if (intTipo == 1){
			$("#porAlias").css("display", "block");
		}
		if (parseInt(tipo) == 2){
			$("#porEvidencia").css("display", "block");
		}
		if (parseInt(tipo) == 3){
			$("#porNombre").css("display", "block");
  		}

	    if (parseInt(tipo) == 4){
			$("#porExpediente").css("display", "block");
		}
	    if (parseInt(tipo) == 5){
			$("#porCaso").css("display", "block");
		}
						
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
<table width="700" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="200">&nbsp;</td>
    <td width="298">Tipo de b&uacute;squeda:
      <select name="cmbTipoBusqueda" id="cmbTipoBusqueda">
        <option value="-1" >-Seleccione-</option>
        <option value="1" >Alias</option>
        <option value="2" >Evidencia</option>
        <option value="3" >Nombre de Persona</option>
        <option value="4" >N&uacute;mero de Causa</option>
        <option value="5" >N&uacute;mero de Caso</option>
    </select></td>
    <td width="200" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" >&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3"><div id="porAlias" style="display: none;"><jsp:include page="buscarExpedientePorAliasGeneral.jsp"></jsp:include></div>
    	<div id="porEvidencia" style="display: none;"><jsp:include page="buscarExpedientePorEvidenciaGeneral.jsp"></jsp:include></div>
    	<div id="porNombre" style="display: none;"><jsp:include page="buscarExpedientePorNombreDePersonaGeneral.jsp"></jsp:include></div>
    	<div id="porExpediente" style="display: none;"><jsp:include page="buscarExpedientePorNumeroDeExpedienteGeneral.jsp"></jsp:include></div>
    	<div id="porCaso" style="display: none;">
    		<jsp:include page="buscarCasoPorNumeroDeCasoGeneral.jsp"/>
    	</div>
   </td>
  </tr>   
</table>




</body>
</html>