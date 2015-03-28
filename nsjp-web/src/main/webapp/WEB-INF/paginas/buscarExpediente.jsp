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

	var reloadGridAlias = false;
	var reloadGridNombre = false;
	var reloadGridEvidencia = false;
	var reloadGridExpediente = false;
    var validaEvidencia = false;
    var validaNombre = false;
    var validaExpediente = false;
    var validaCaso = false;
    var reloadGridCaso = false;
    var idWindowNuevaDenuncia=1;
    var opcionNoPenal="";

    var tipoOrigen= '<%=request.getParameter("tipo")!=null?request.getParameter("tipo"):""%>';

    var rolUsuario= "";
	
	$(document).ready(function() {

		controlAtributesTipoBusqueda();
				
		enableControls('0');
		deshabilitaOpciones();      //funcion que deshabilita opciones de buscar por alias
		cargaCatEvidencia();		//Funcion que carga el combo de tipo de evidencia en buscar por evidencia
		enableControlsEvidencia('0');		//funcion que habilita controles en evidencia
		opcionNoPenal='<%= request.getParameter("opcionNoPenal")%>';
	    //$("#cmbTipoBusqueda").change(onSelectChangeTipo);
	   	$("#buscarAlias").bind("click",llenaGridAlias);
		$("#alias").bind("keypress",habilitaOpciones);
		//$("#catEvidencia").change(onSelectChangeTipoEv);
		$("#buscarEvidencia").bind("click",validaCamposEvidencia);
		$("#buscarEvidencia").bind("click",llenaGridEvidencia);
		$("#buscarNombre").bind("click",validaCamposNombre);
		$("#buscarNombre").bind("click",llenaGridNombre);
		$("#buscarExpediente").bind("click",validaCamposExpediente);
		$("#buscarExpediente").bind("click",llenaGridExpediente);
		$("#buscarCaso").bind("click",validaCamposCaso);
		$("#buscarCaso").bind("click",llenaGridCaso);
		$("#buscarEvidencia").attr('disabled', 'disabled');
		$("#palabraClave").attr('disabled', 'disabled');
		$("#palabraClave2").attr('disabled', 'disabled');
		$("#palabraClave3").attr('disabled', 'disabled');
		$("#palabraClave4").attr('disabled', 'disabled');
		$("#palabraClave5").attr('disabled', 'disabled');
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

	/*
	*Funcion que obtiene el parametro del usuario que esta llamando a la pantalla, 
	*para despues mostrar u ocultar algunos campos de busqueda del combo box
	*/
	function controlAtributesTipoBusqueda(){

		//Parametro para controlar que usuario desea abrir la ventana
		rolUsuario = '<%=request.getParameter("rolUsuario")%>';
		
		if(rolUsuario == "encargadoSala"  || rolUsuario == "atencionPublico" || rolUsuario == "juezPJ" || rolUsuario == "admonPJ"){
			$("#cmbTipoBusqueda").find("option[value='2']").remove(); 
			$("#cmbTipoBusqueda").find("option[value='5']").remove();
		}		
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
		
	if (parseInt(tipo) == 1||parseInt(tipo) == 2||parseInt(tipo) == 3||parseInt(tipo) == 4||parseInt(tipo) == 5)
		{
		
		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");
		$("#porCaso").css("display", "none");

		if (parseInt(tipo) == 1){
			limpiaCampos();
			$("#porAlias").css("display", "block");
			}
		else{

		
		$("#porAlias").css("display", "none");
		$("#porEvidencia").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porExpediente").css("display", "none");
		$("#porCaso").css("display", "none");
		
				if (parseInt(tipo) == 2){
					limpiaCampos();
					$("#porEvidencia").css("display", "block");
				}
				else{

					
					$("#porAlias").css("display", "none");
					$("#porEvidencia").css("display", "none");
					$("#porNombre").css("display", "none");
					$("#porExpediente").css("display", "none");
					$("#porCaso").css("display", "none");

						if (parseInt(tipo) == 3){
							limpiaCampos();
							$("#porNombre").css("display", "block");
			   			 }

				   	else{


				   		
				   		$("#porAlias").css("display", "none");
						$("#porEvidencia").css("display", "none");
						$("#porNombre").css("display", "none");
						$("#porExpediente").css("display", "none");
						$("#porCaso").css("display", "none");
						
						    if (parseInt(tipo) == 4){

						    	limpiaCampos();
								$("#porExpediente").css("display", "block");
							}else{
								$("#porAlias").css("display", "none");
								$("#porEvidencia").css("display", "none");
								$("#porNombre").css("display", "none");
								$("#porExpediente").css("display", "none");
								$("#porCaso").css("display", "none");
								if (parseInt(tipo) == 5){
							    	limpiaCampos();
							    	$("#porCaso").css("display", "block");
								}
								

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
		$("#apellidoMat").val("");		
		$("#noExpediente").val("");

		}
	
	function tituloVentana(num){
		$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+" div.window-titleBar-content").html("Expediente: "+num);
	}
	
	function detEvi(id) 
	{
		if(rolUsuario == "encargadoSala" || rolUsuario == "juezPJ" || rolUsuario == "atencionPublico" || rolUsuario == "admonPJ"){

			var selectedOptionCbx = $("#cmbTipoBusqueda option:selected").val();
			var numeroExpediente="";
			
			if(parseInt(selectedOptionCbx) == 3 ){
				//alert("Opcion 3");
				var rowd = jQuery("#tablaBuscarExpedientePorNombreDePersona").jqGrid('getGridParam','selrow');

				if (rowd){ 
					var retd = jQuery("#tablaBuscarExpedientePorNombreDePersona").jqGrid('getRowData',rowd);
					numeroExpediente = retd.expediente;
					//alert("Numero Expediente="+numeroExpediente);
				}
				//alert("Numero Expediente="+numeroExpediente);
				ventanaAudiencias(id,numeroExpediente);
			}
			if(parseInt(selectedOptionCbx) == 4 ){
				//alert("Opcion 4");
				var rowd = jQuery("#tablaBuscarPorNumeroDeExpediente").jqGrid('getGridParam','selrow');

				if (rowd){ 
					var retd = jQuery("#tablaBuscarPorNumeroDeExpediente").jqGrid('getRowData',rowd);
					numeroExpediente = retd.expediente;
					//alert("Numero Expediente="+numeroExpediente);
				}

				//alert("Numero Expediente="+numeroExpediente);
				ventanaAudiencias(id,numeroExpediente);
			}
		}
		else{
			idWindowNuevaDenuncia++;
			var ingresoDenuncia = true;
			$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: ", type:"iframe"});
			$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'" width="1430" height="670" />');
			$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia+ " .window-maximizeButton").click();
		}
	}


	/*
	*Funcion que crea el visor Audiencias de un Expediente
	*/
	var idWindowAudienciasCausa=1; 
							
	function ventanaAudiencias(id,numeroExpediente) {
		idWindowAudienciasCausa++;
		$.newWindow({id:"iframewindowAudienciasCausa"+idWindowAudienciasCausa, statusBar: true, posx:255,posy:110,width:700,height:400,title:"Audiencias", type:"iframe"});
    	$.updateWindowContent("iframewindowAudienciasCausa"+idWindowAudienciasCausa,'<iframe src="<%= request.getContextPath() %>/visorAudienciasPorExpediente.do?numeroExpedienteId='+id+'&numeroExpediente='+numeroExpediente+'&rolUsuario='+rolUsuario+'" width="700" height="400" />');
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
        <option value="2" >Evidencia</option>
        <option value="3" >Nombre de Persona</option>
        <option value="4" >N&uacute;mero de Expediente </option>
        <option value="5" >N&uacute;mero de Caso</option>
    </select></td>
    <td width="200" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" >&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">
    	<div id="porAlias" style="display: none;"><jsp:include page="buscarExpedientePorAlias.jsp"></jsp:include></div>
    	<div id="porEvidencia" style="display: none;"><jsp:include page="buscarExpedientePorEvidencia.jsp"></jsp:include></div>
    	<div id="porNombre" style="display: none;"><jsp:include page="buscarExpedientePorNombreDePersona.jsp"></jsp:include></div>
    	<div id="porExpediente" style="display: none;"><jsp:include page="buscarExpedientePorNumeroDeExpediente.jsp"></jsp:include></div>
    	<div id="porCaso" style="display: none;"><jsp:include page="buscarCasoPorNumeroDeCaso.jsp"></jsp:include></div>
   </td>
  </tr>   
</table>




</body>
</html>