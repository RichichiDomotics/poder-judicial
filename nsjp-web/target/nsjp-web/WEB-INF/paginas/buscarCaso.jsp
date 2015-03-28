<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Caso</title>
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
	var reloadGridDelito = false;
	var reloadGridCaso = false;
	var reloadGridFecha = false;
    var validaDelito = false;
    var validaNombre = false;
    var validaCaso = false;
    var validaFecha = false;
	$(document).ready(function() {
		enableControls('0');
		deshabilitaOpciones();      //funcion que deshabilita opciones de buscar por alias
		cargaComboDelito();		//Funcion que carga el combo de tipo de delito
		enableControlsDelito('0');		//funcion que habilita controles en delito
		
	    //$("#cmbTipoBusqueda").change(onSelectChangeTipo);
	   	$("#buscarAlias").bind("click",llenaGridAlias);
		$("#alias").bind("keypress",habilitaOpciones);
		//$("#catDelito").change(onSelectChangeTipoDel);
		$("#buscarDelito").bind("click",validaCamposDelito);
		$("#buscarDelito").attr('disabled', 'disabled');
		$("#buscarDelito").bind("click",llenaGridDelito);
		$("#buscarNombre").bind("click",validaCamposNombre);
		$("#buscarNombre").bind("click",llenaGridNombre);
		$("#buscarFecha").bind("click",validaCamposFecha);
		$("#buscarFecha").bind("click",llenaGridFecha);
		$("#buscarCaso").bind("click",validaCamposCaso);
		$("#buscarCaso").bind("click",llenaGridCaso);
		$("#idFechaDateLapso").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#idFechaDateLapso2").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		$("#cmbTipoBusqueda").multiselect({ 
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
		$("#catDelito").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			minWidth: 400,
			selectedList: 1, 
			close: function (event,ui){
				onSelectChangeTipoDel();}
		});
		
				
});
	
/*
* Funcion para llamar la funcion de habilitar los elementos de la pantalla
*/
function onSelectChangeTipo() {
  	var selected = $("#cmbTipoBusqueda option:selected");		
	enableControls(selected.val());
}
    
/*
*Funcion que habilita o deshabilita los elementos de la pagina dependiento del tipo 
* de busqueda
*/
function enableControls(tipo){
		
	if (parseInt(tipo) == 1||parseInt(tipo) == 2||parseInt(tipo) == 3||parseInt(tipo) == 4||parseInt(tipo) == 5)
		{
		
		$("#porAlias").css("display", "none");
		$("#porDelito").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porCaso").css("display", "none");
		$("#porFecha").css("display", "none");

		if (parseInt(tipo) == 1){
			limpiaCampos();
			$("#porAlias").css("display", "block");
			
			}
		else{

			$("#porAlias").css("display", "none");
			$("#porDelito").css("display", "none");
			$("#porNombre").css("display", "none");
			$("#porCaso").css("display", "none");
			$("#porFecha").css("display", "none");
		
				if (parseInt(tipo) == 2){
					limpiaCampos();
					$("#porDelito").css("display", "block");
					
				}
				else{

					$("#porAlias").css("display", "none");
					$("#porDelito").css("display", "none");
					$("#porNombre").css("display", "none");
					$("#porCaso").css("display", "none");
					$("#porFecha").css("display", "none");
					
						if (parseInt(tipo) == 3){
							limpiaCampos();
							$("#porFecha").css("display", "block");
			   			 }

				   	else{

				   		$("#porAlias").css("display", "none");
						$("#porDelito").css("display", "none");
						$("#porNombre").css("display", "none");
						$("#porCaso").css("display", "none");
						$("#porFecha").css("display", "none");
						
						    if (parseInt(tipo) == 4){
						    	limpiaCampos();
						    	$("#porNombre").css("display", "block");
						}
							else{

						   		$("#porAlias").css("display", "none");
								$("#porDelito").css("display", "none");
								$("#porNombre").css("display", "none");
								$("#porCaso").css("display", "none");
								$("#porFecha").css("display", "none");
								
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
		$("#porDelito").css("display", "none");
		$("#porNombre").css("display", "none");
		$("#porCaso").css("display", "none");
		$("#porFecha").css("display", "none");
	}
}

	function limpiaCampos(){
		
		$("#alias").val("");
		$("#nombre").val("");
		$("#apellido").val("");
		$("#noCaso").val("");
		$("#idFechaDateLapso").val("");
		$("#idFechaDateLapso2").val("");
		
		}
			
</script>

<body>
<table width="700" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td colspan="3"></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="200">&nbsp;</td>
    <td width="298">Tipo de b&uacute;squeda:
      <select name="cmbTipoBusqueda" id="cmbTipoBusqueda" >
        <option value="-1" >-Seleccione-</option>
        <option value="1" >Alias</option>
        <option value="2" >Delito</option>
        <option value="3" >Fecha de creaci&oacute;n</option>
        <option value="4" >Nombre de persona</option>
        <option value="5" >N&uacute;mero de caso</option>
    </select></td>
    <td width="200">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" ></td>
  </tr>
  <tr>
    <td colspan="3">
    	<div id="porAlias" style="display: none;"><jsp:include page="buscarCasoPorAlias.jsp"></jsp:include></div>
    	<div id="porDelito" style="display: none;"><jsp:include page="buscarCasoPorDelito.jsp"></jsp:include></div>
    	<div id="porNombre" style="display: none;"><jsp:include page="buscarCasoPorNombreDePersona.jsp"></jsp:include></div>
    	<div id="porCaso" style="display: none;"><jsp:include page="buscarCasoPorNumeroDeCaso.jsp"></jsp:include></div>
    	<div id="porFecha" style="display: none;"><jsp:include page="buscarCasoPorFechaDeCreacion.jsp"></jsp:include></div>
   </td>
  </tr>   
</table>

</body>
</html>