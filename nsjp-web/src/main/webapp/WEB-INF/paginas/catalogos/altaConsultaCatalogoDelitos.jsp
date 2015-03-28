<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO"%>
<%@page import="mx.gob.segob.nsjp.web.catalogo.form.CatalogosForm"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.alerts.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>    
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
			
	<script type="text/javascript">
	<%	
		boolean esNuevo = true;
	
		if(request.getAttribute("esNuevo") != null){
			esNuevo = (Boolean) request.getAttribute("esNuevo");
		}
		
		CatDelitoDTO catDelito = new CatDelitoDTO();
		
		Long  delitoId = 0L;
		String cveDelito = "";
		String nombDelito = "";
		Boolean esGrave = false;
		Boolean esAccPenPriv = false;
		Long jerarquiaId = 0L;
		Long jerarquiaUIE = 0L;
		
		Long penaMinDiaDel=0L;
		Long penaMinMesDel=0L;
		Long penaMinAnioDel=0L;
		
		Long penaMaxDiaDel=0L;
		Long penaMaxMesDel=0L;
		Long penaMaxAnioDel=0L;
		
		//Si es la consulta de un delito
		if (!esNuevo) {
			
			catDelito = (CatDelitoDTO) request.getAttribute("catDelito");
			
			if(catDelito != null){
				
				if(catDelito.getCatDelitoId() != null){
					delitoId=catDelito.getCatDelitoId();
				}
					
				if(catDelito.getClaveDelito() != null){
					cveDelito = catDelito.getClaveDelito();	
				}
				if(catDelito.getNombre() != null){
					nombDelito = catDelito.getNombre();	
				}
				if(catDelito.getEsGrave() != null){
					esGrave = catDelito.getEsGrave();	
				}
				if(catDelito.getEsAccionPenPriv()){
					esAccPenPriv = catDelito.getEsAccionPenPriv();
				}
				
				//Agregar la consulta de UIE
				if(catDelito.getUnidadIEspecializada() != null && catDelito.getUnidadIEspecializada().getCatUIEId() != null){
					jerarquiaUIE = catDelito.getUnidadIEspecializada().getCatUIEId();	
				}
				
				if(catDelito.getPenaMinimaDias() != null){
					penaMinDiaDel=catDelito.getPenaMinimaDias();
				}
				if(catDelito.getPenaMinimaMeses() != null){
					penaMinMesDel=catDelito.getPenaMinimaMeses();
				}
				if(catDelito.getPenaMinimaAnios() != null){
					penaMinAnioDel=catDelito.getPenaMinimaAnios();
				}
				if(catDelito.getPenaMaximaDias() != null){
					penaMaxDiaDel=catDelito.getPenaMaximaDias();
				}
				if(catDelito.getPenaMaximaMeses() != null){
					penaMaxMesDel=catDelito.getPenaMaximaMeses();
				}
				if(catDelito.getPenaMaximaAnios() != null){
					penaMaxAnioDel=catDelito.getPenaMaximaAnios();
				}
				
			}
	%>
			$(document).ready(function() {
				bloquearTodosLosElementos();
				moduloGenericoDeValidaciones();
				cargaMeses();
				cargaDias();
				cargaAnios();
				//consultarCatalogoAreas();
				consultarCatalogoUIE();
				consultaYMuestraCatalogo();
				
			});
	<%
		//Si es un delito nuevo
		}else{%>		
			$(document).ready(function() {
				moduloGenericoDeValidaciones();
				cargaMeses();
				cargaDias();
				cargaAnios();
				//consultarCatalogoAreas();
				consultarCatalogoUIE();
				botonesEsNuevo();
			});
	<%}%>
  
	var banderaValidacion = 0;
	
 /******************************************************************FUNCIONES PARA CONSULTAS*********************************************************/
 
 	function consultaYMuestraCatalogo(){

 		$('#claveDelito').val('<%=cveDelito%>');
 		$('#nombreDelito').val('<%=nombDelito%>');

 		//Checamos si el delito es grave
 		<%if (esGrave) {%>
 			$('input[name=esGraveDelito]:eq(0)').attr('checked','checked');
 		<%} else {%>
 			$('input[name=esGraveDelito]:eq(1)').attr('checked','checked');
 	 	<%}%>

 	 	//Checamos si de accion Penal Privada
 	 	<%if (esAccPenPriv) {%>
			$('input[name=esAccPenalPriv]:eq(0)').attr('checked','checked');
		<%} else {%>
			$('input[name=esAccPenalPriv]:eq(1)').attr('checked','checked');
	 	<%}%>

	 	//SELECCIONAMOS EL AREA DE LA UIE
	 	$("#jerarquiaUIEdelito").val('<%=jerarquiaUIE%>');
	 	

	 	//Checamos la pena minima
	 	$('#penaMinAnios').val('<%=penaMinAnioDel%>');
	 	$('select#penaMinMeses')[0].selectedIndex = '<%=penaMinMesDel%>';
	 	$('select#penaMinDias')[0].selectedIndex = '<%=penaMinDiaDel%>';

	 	//Checamos la pena maxima
	 	$('#penaMaxAnios').val('<%=penaMaxAnioDel%>');
	 	$('select#penaMaxMeses')[0].selectedIndex = '<%=penaMaxMesDel%>';
	 	$('select#penaMaxDias')[0].selectedIndex = '<%=penaMaxDiaDel%>';
 	 }

 	/*
 	*Funcion que deshabilita todos los campos de la ventana para el modo consulta
 	*/
	 function bloquearTodosLosElementos(){

		 $("#claveDelito").attr('disabled','disabled');
		 $("#nombreDelito").attr('disabled','disabled');
		 $("#esGraveDelitoSiRbtn").attr('disabled','disabled');
		 $("#esGraveDelitoNoRbtn").attr('disabled','disabled');
		 $("#esAccPenalPrivSiRbtn").attr('disabled','disabled');
		 $("#esAccPenalPrivNoRbtn").attr('disabled','disabled'); 
		 $("#jerarquiaUIEdelito").attr('disabled','disabled');
		 $("#penaMinAnios").attr('disabled','disabled');
		 $("#penaMinMeses").attr('disabled','disabled');
		 $("#penaMinDias").attr('disabled','disabled');
		 $("#penaMaxAnios").attr('disabled','disabled');
		 $("#penaMaxMeses").attr('disabled','disabled');
		 $("#penaMaxDias").attr('disabled','disabled');

		 $("#guardarDelitoBtn").hide();
		 $("#modificarDelitoBtn").show();
	}


	/*
	*Funcion que deshabilita todos los campos de la ventana para el modo consulta
	*/
	function desbloquearTodosLosElementos(){
	
		 $("#claveDelito").attr('disabled',false);
		 $("#nombreDelito").attr('disabled',false);
		 $("#esGraveDelitoSiRbtn").attr('disabled',false);
		 $("#esGraveDelitoNoRbtn").attr('disabled',false);
		 $("#esAccPenalPrivSiRbtn").attr('disabled',false);
		 $("#esAccPenalPrivNoRbtn").attr('disabled',false); 
		 $("#jerarquiaUIEdelito").attr('disabled',false);
		 $("#penaMinAnios").attr('disabled',false);
		 $("#penaMinMeses").attr('disabled',false);
		 $("#penaMinDias").attr('disabled',false);
		 $("#penaMaxAnios").attr('disabled',false);
		 $("#penaMaxMeses").attr('disabled',false);
		 $("#penaMaxDias").attr('disabled',false);

		 $("#guardarDelitoBtn").show();
		 $("#modificarDelitoBtn").hide();
		 $("#eliminarDelitoBtn").hide();
	}
 
 
 /******************************************************************FUNCIONES PARA CONSULTAS*********************************************************/
	/*
	*Funcion que oculta el boto de modificar
	*/
	function botonesEsNuevo(){
		 $("#modificarDelitoBtn").hide();
		 $("#guardarDelitoBtn").show();
		 $("#eliminarDelitoBtn").hide();
	}
 
	/*
	*Funcion generica para que se escriban solo numeros en un text field
	*Parametro:id del textbox
	*/
	function validarClavesNumericas(idTextBox){

		$("#"+idTextBox).keypress(function(event) {
			 var unicode = event.charCode ? event.charCode : event.keyCode;
			    // if the key is backspace, tab, or numeric
			    if (unicode == 8 || unicode == 9 || (unicode >= 48 && unicode <= 57)) {
			        // we allow the key press
			        return true;
			    }
			    else {
			        // otherwise we don't
			        return false;
			    }
		});
	}

	/*
	*Funcion generica para validar campos dependiendo del catalogo que se este
	*editando
	*/
	function moduloGenericoDeValidaciones(){
				
		validarClavesNumericas("claveDelito");
		validarClavesNumericas("penaMinAnios");
		validarClavesNumericas("penaMaxAnios");	
	}

	/*
	*Funcion que setea el valor a cero en los campos anio
	*/
	function cargaAnios(){

		$('#penaMinAnios').val('0');
		$('#penaMaxAnios').val('0');
	}

	/**
	*Funcion que carga el numero de mases
	*/
	function cargaMeses(){

		var NUMERO_MESES=11;
		var indice=0;
		
		for(indice=0;indice<=NUMERO_MESES;indice++){
			$('#penaMinMeses').append('<option value="'+indice+'">' +indice+ '</option>');
			$('#penaMaxMeses').append('<option value="'+indice+'">' +indice+ '</option>');
		}
	}

	/**
	*Funcion que carga el numero de dias
	*/
	function cargaDias(){

		var NUMERO_DIAS=29;
		var indice=0;
				
		for(indice=0;indice<=NUMERO_DIAS;indice++){
			$('#penaMinDias').append('<option value="'+indice+'">' +indice+ '</option>');
			$('#penaMaxDias').append('<option value="'+indice+'">' +indice+ '</option>');
		}
	}

	
	/*
	*Funcion que realiza la carga del combo de unidad de investigacion especializada
	*/
	function consultarCatalogoUIE() {

		$('#jerarquiaUIEdelito').empty();
		$('#jerarquiaUIEdelito').append('<option value="nop">- Seleccione -</option>');
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoUIE.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('CatUIEspecializadaDTO').each(function(){
					$('#jerarquiaUIEdelito').append('<option value="'+$(this).find('catUIEId').text() + '">'+$(this).find('claveUIE').text()+" - "+$(this).find('nombreUIE').text()+'</option>');
				});
			}
		});
	}
	

	
	/*
	*Funcion para guardar el delito despues de las validaciones
	*/
	function guardarDelito(){

		if(validaDatosDelito()){
			
			if(validaPenaMinPenaMaxDelito()){
				
				var params = '';
				params += "&delitoId="+'<%=delitoId%>';
				params += "&cveDel="+$('#claveDelito').val();
				params += "&nombDel="+$('#nombreDelito').val();
				params += "&esGraveDel="+$("input[name='esGraveDelito']:checked").val();
				params += "&esAccPenalPrivDel="+$("input[name='esAccPenalPriv']:checked").val();
				params += "&jerarquiaUIEdelito="+$("#jerarquiaUIEdelito option:selected").val();
				params += "&penaMinA="+$('#penaMinAnios').val();
				params += "&penaMinM="+$('#penaMinMeses option:selected').val();
				params += "&penaMinD="+$('#penaMinDias option:selected').val();
				params += "&penaMaxA="+$('#penaMaxAnios').val();
				params += "&penaMaxM="+$('#penaMaxMeses option:selected').val(); 
				params += "&penaMaxD="+$('#penaMaxDias option:selected').val();
				
				$.ajax({
					type: 'POST',
					url: '<%=request.getContextPath()%>/GuardarValorEnCatalogoDelito.do',
					data: 'params='+params,
					dataType: 'xml',
					async: false,
					success: function(xml){
						respuestaIntentarGuardar(xml);
					}
				});
			}
		}
	}

	/*
	*Funcion que se invocara despues de intentar guardar 
	*/
	function respuestaIntentarGuardar(xml){
		var codigo = $(xml).find('code').text();
		if(codigo == 0){
			var cuerpo = $(xml).find('body').text();
			if(cuerpo == "fallo"){
				alertSincronoClave("La clave del delito ya existe");
			}
			if(cuerpo == "exito"){
				alertSincronoGuardado("El delito fue guardado correctamente");
			}
		}
		else{
			//Error inesperado
		}
	}

	/*
	*Funcion que despliega una ventana modal para indicar 
	*que el delito fue guardado exitosamente, e invocar a la 
	*funcion del padre(administrarCatalogos.jsp) que cierra la
	*ventana y recarga el grid de delitos 
	*/
	function alertSincronoGuardado(mensaje){

		$('#spanAlert').html(mensaje);
		
		$("#alertSincro").dialog("open");
		$("#alertSincro").dialog({
			autoOpen: true, 
			modal: true, 
			title: '', 
			dialogClass: 'alert',
			position: [250,100],
		  	width: 300,
		  	height: 120,
		  	maxWidth: 300,
		  	maxHeigth:120,
			buttons: {"Aceptar":function() {
							parent.cerrarVentanaVerValor();
							//$(this).dialog("close");
						}
				},
			close:function() {
					parent.cerrarVentanaVerValor();
					//$(this).dialog("close");
				} 
		});
	}

	/*
	*Funcion que despliega una ventana modal para indicar que la clave existe
	*/
	function alertSincronoClave(mensaje){

		$('#spanAlert').html(mensaje);
		
		$("#alertSincro").dialog("open");
		$("#alertSincro").dialog({
			autoOpen: true, 
			modal: true, 
			title: '', 
			dialogClass: 'alert',
			position: [250,100],
		  	width: 300,
		  	height: 120,
		  	maxWidth: 300,
		  	maxHeigth:120,
			buttons: {"Aceptar":function() {
						$(this).dialog("close");
				     }
				}
		});
	}
	
	/*
	*Funcion para validar que los datos ingresados en pena max y pena min
	*/
	function validaPenaMinPenaMaxDelito(){

		//Validacion para ceros pena minima
		var penaMinA = $('#penaMinAnios').val();
		var penaMaxA = $('#penaMaxAnios').val();
		var penaMinM = $('#penaMinMeses option:selected').val();
		var penaMaxM = $('#penaMaxMeses option:selected').val(); 
		var penaMinD = $('#penaMinDias option:selected').val();
		var penaMaxD = $('#penaMaxDias option:selected').val();


		var totalPenaMin = 0;
		
		totalPenaMin = parseInt(penaMinA)*365;
		totalPenaMin += parseInt(penaMinM)*30;
		totalPenaMin += parseInt(penaMinD);
		
		if(parseInt(totalPenaMin) == 0){	
			alertDinamico("Ingrese una pena mínima");
			return false;	 
		}
		
		var totalPenaMax = 0;

		totalPenaMax = parseInt(penaMaxA)*365;
		totalPenaMax += parseInt(penaMaxM)*30;
		totalPenaMax += parseInt(penaMaxD);
		
		if(parseInt(totalPenaMax) == 0){
			alertDinamico("Ingrese una pena máxima");
			return false;
		}
		if(parseInt(totalPenaMin) > parseInt(totalPenaMax)){
			alertDinamico("La pena mínima debe ser menor o igual a la pena máxima");
			return false;
		}

	
		return true;
	}

	/*
	*Funcion para validar los datos generales del delito
	*/
	function validaDatosDelito(){

		var cveDel = $('#claveDelito').val();
		var nombDel = $('#nombreDelito').val();
		
		var nombDelArray = new Array(); 
		nombDelArray = $('#nombreDelito').val();

		

		if (cveDel == " " || cveDel == ""){
			
			alertDinamico("Ingrese una clave para el delito");
			return false;
		}
		else if(nombDel == ''){	
			alertDinamico("Ingrese un nombre para el delito");
			return false;
		}
		else if(nombDelArray[0] == ' '){
			
			alertDinamico("El nombre del delito no puede empezar con espacios");
			return false;
		}
		else if($("input[name='esGraveDelito']:checked").val() === undefined){
			
			alertDinamico("Seleccione si el delito es grave o no");
			return false;
		}
		else if($("input[name='esAccPenalPriv']:checked").val() === undefined){
			
			alertDinamico("Seleccione si el delito es acción penal privada");
			return false;
		}
		else if($("#jerarquiaUIEdelito option:selected").val() == "nop"){
			alertDinamico("Seleccione una Unidad de Investigación Especializada");
			return false;
		}
		else{
			return true;
		}
	}

//*****************************************************************FUNVCIONES PARA ELIMINAR UN DELITO*****************************************/

	/*
	*Funcion que elimina el delito que se consultó
	*/
	function eliminarDelito(){

		var params = '';
		params += "&delitoId="+'<%=delitoId%>';
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/EliminarValorEnCatalogoDelito.do',
			data: 'params='+params,
			dataType: 'xml',
			async: false,
			success: function(xml){
				var banderaValidacion = 1;
				var valorRetorno=$(xml).find("long").text();
				respuestaIntentarEliminar(valorRetorno);
			}
		});

		// En este if entran las excepciones generadas por Hibernate (cuando truena a nivel negocio)
		// por el momento se agrupan en un solo bloque ya que esto cumple con la funcionalidad requerida.
		if(banderaValidacion != 2  &&  banderaValidacion!=1){
			alertSincronoNoExisteOusado("El delito no se puede eliminar porque tiene relación en denuncias existentes");
		}
		
		banderaValidacion = 0;
	}

	/*
	*Funcion que se invocara despues de intentar eliminar el objeto
	*/
	function respuestaIntentarEliminar(resultado){
		
		if(resultado == -1){
			alertSincronoNoExisteOusado("El delito no se puede eliminar porque tiene relación en denuncias existentes");
		}
		else if(resultado == 2){
			alertSincronoNoExisteOusado("El delito no se logró eliminar porque no se encontró en la base de datos");
		}
		else if(resultado == 1){
			alertSincronoNoExisteOusado("El delito fue eliminado correctamente");				
		}
		
		banderaValidacion = 2;
 	}

	/*
	*Funcion que despliega una ventana modal para indicar que el delito no existe 
	*o esta siendo usado 
	*/
	function alertSincronoNoExisteOusado(mensaje){

		$('#spanAlert').html(mensaje);
		
		$("#alertSincro").dialog("open");
		$("#alertSincro").dialog({
			autoOpen: true, 
			modal: true, 
			title: '', 
			dialogClass: 'alert',
			position: [250,100],
		  	width: 300,
		  	height: 150,
		  	maxWidth: 300,
		  	maxHeigth:150,
			buttons: {"Aceptar":function() {
						$( this ).dialog( "close" );$("#spanAlert").html("");parent.cerrarVentanaVerValor();
				     }
				}
		});
	}

	</script>
</head>
<body>
	<table width="755" border="0">
	  <tr>
	    <td width="30">&nbsp;</td>
	    <td colspan="7">
	    	<div align="center"><strong>DATOS DEL DELITO</strong></div>
	    </td>
	    <td width="30">&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td width="180">&nbsp;</td>
	    <td colspan="4">&nbsp;</td>
	    <td width="100">
	    	<div align="center">Pena M&iacute;nima </div>
	    </td>
	    <td width="70">&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
	    	<div align="right">Clave:</div>
	    </td>
	    <td colspan="4"><input type="text" id="claveDelito" style="width:50px" tabindex="1" maxlength="3"/></td>
	    <td>
	    	<div align="right">A&ntilde;os:</div>
	    </td>
	    <td>
	    	<input type="text" id="penaMinAnios" style="width:50px" tabindex="8" maxlength="3"/>
	    </td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
	    	<div align="right">Nombre: </div>
	    </td>
	    <td colspan="4">
	    	<input type="text" id="nombreDelito" style="width:300px" tabindex="2" maxlength="80"/>
	    </td>
	    <td>
	    	<div align="right">Meses:</div>
	    </td>
	    <td>
			<select id="penaMinMeses" style="width:50px" tabindex="9"></select>
	    </td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
	    	<div align="right">Es grave:</div>
	    </td>
	    <td width="72">
	    	<div align="right">Si:</div>
	    </td>
	    <td width="71">
	    	<input name="esGraveDelito" id="esGraveDelitoSiRbtn" type="radio" value="true" tabindex="3"/>
		</td>
	    <td width="72">
	    	<div align="right">No:</div>
	    </td>
	    <td width="72">
			<input name="esGraveDelito"  id="esGraveDelitoNoRbtn" type="radio" value="false" tabindex="4" />
		</td>
	    <td>
	    	<div align="right">D&iacute;as:</div>
	    </td>
	    <td>
			<select id="penaMinDias" style="width:50px" tabindex="10"></select>
		</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
	    	<div align="right">Es acci&oacute;n penal privada:</div>
	    </td>
	    <td>
	    	<div align="right">Si:</div>
	    </td>
	    <td>
			<input name="esAccPenalPriv" id="esAccPenalPrivSiRbtn" type="radio" value="true" tabindex="5"/>
		</td>
	    <td>
	    	<div align="right">No:</div>
	    </td>
	    <td>
			<input name="esAccPenalPriv" id="esAccPenalPrivNoRbtn" type="radio" value="false" tabindex="6"/>
		</td>
	    <td>
	    	<div align="center">Pena M&aacute;xima </div>
	    </td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
			<!--<div align="right">Jerarqu&iacute;a Org.: </div>-->
	    	<div align="right">Unidad investigaci&oacute;n especializada: </div>
	    </td>
	    <td colspan="4">
			<select id="jerarquiaUIEdelito" style="width:300px" tabindex="7"></select>
		</td>
	    <td>
	    	<div align="right">A&ntilde;os:</div>
	    </td>
	    <td>
	    	<input type="text" id="penaMaxAnios" style="width:50px" tabindex="11" maxlength="3"/>
	    </td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td colspan="4">&nbsp;</td>
	    <td>
	    	<div align="right">Meses:</div>
	    </td>
	    <td>
			<select id="penaMaxMeses" style="width:50px" tabindex="12"></select>
		</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td colspan="4">&nbsp;</td>
	    <td>
	    	<div align="right">D&iacute;as:</div>
	    </td>
	    <td>
			<select id="penaMaxDias" style="width:50px" tabindex="13"></select>
		</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>
	    	<input type="submit" id="guardarDelitoBtn" value="Guardar" onclick="guardarDelito();" class="btn_Generico"/>
	    	<input type="submit" id="modificarDelitoBtn" value="Modificar" onclick="desbloquearTodosLosElementos();" class="btn_Generico"/>
	    	<input type="submit" id="eliminarDelitoBtn" value="Eliminar" onclick="eliminarDelito();" class="btn_Generico"/>
	    </td>
	    <td colspan="4">&nbsp;</td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
	
	<!-- div para el alert sincrono -->
	<div id="alertSincro" style="display: none">
		<table>
			<tr>
				<td>
					<label id=spanAlert></label>
				</td>
			</tr>
		</table>
	</div>   
</body>
</html>