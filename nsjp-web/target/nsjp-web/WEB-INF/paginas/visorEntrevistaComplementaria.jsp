<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registrar Datos Persona</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<!--  <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery-ui.css" />-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	
<script type="text/javascript" src="<%= request.getContextPath()%>/js/comun.js"></script>

</head>
<script type="text/javascript">

var idSesion;
var idFamiliar="0";
var fechaActual;

$(document).ready(function() {
	//asignamos 
	$("#btnGuardarMotivoConsulta").click(onClickBtnGuardarMotivoConsulta);
	$("#btnGuardaConciencia").click(onClickBtnGuardaConciencia);
	$("#btnGuardarImpresionFamiliar").click(onClickBtnGuardarImpresionFamiliar);
	$("#btnGuardarHipotesisFamiliar").click(onClickBtnGuardarHipotesisFamiliar);
	
	//leemos los parametros 
	idSesion = '<%= request.getParameter("rowid")%>';
	cargaFechaActual();

	$("#tabsPrincipal").tabs();

	//cargamos a los familiares
	jQuery("#gridFamiliares").jqGrid({
		url:'<%= request.getContextPath()%>/consultarFamiliaresEntrevistaComplementariaPorId.do?idSesion='+idSesion+'',
		datatype: "xml", 
		colNames:['Nombre','Apellido Paterno','Apellido Materno','Edad','Parentesco','Escolaridad','Estado Civil','Ocupación'],
		colModel:[	{name:'Nombre',index:'index0', width:100},
		          	{name:'Paterno',index:'index1', width:100},
		          	{name:'Materno',index:'index2', width:110},
		          	{name:'Edad',index:'index3', width:90},
		          	{name:'Parentesco',index:'index4', width:100},
		          	{name:'Escolaridad',index:'index5', width:100},
		          	{name:'Estado Civil',index:'index6', width:100},
		          	{name:'Ocupación',index:'index7', width:100},
					],
		pager: jQuery('#pagerGridFamiliares'),
		rowNum:20,
		rowList:[10,20,30],
		width:800,
		sortname: 'nombreDocumento',
		viewrecords: true,
		sortorder: "desc"
	}).navGrid('#pagerGridFamiliares',{edit:false,add:false,del:false});
	$("#gview_gridFamiliares .ui-jqgrid-bdiv").css('height', '400px');
	
	var config = {toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Table','HorizontalRule'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		],
	height:'320px'
	//width:'640px'
	};
	$('.jquery_ckeditor').ckeditor(config);
	
	if(idSesion!=null && parseInt(idSesion)>0)
	{
		consultaEntrevistaCOmplementaria();
	}
	//cargamos los catalogos
	cargaEstadoCivil();
	cargaOcupacion();
	cargaEscolaridad();
	cargaParentesco();
	
	//ocultamos las pestañas
	killPestanas();
});

function killPestanas()
{
	$('#liPropuestaPsico').hide();
	$('#liExamenMental').hide();
	$('#liDiagnostico').hide();
}

/*
*Funcion que consulta la fecha actual en el calendario
*/
function cargaFechaActual(){
	$.ajax({
		type: 'POST',
	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
	    data: '',
	    dataType: 'xml',
	    async: false,
	    success: function(xml){
    	    fechaActual = $(xml).find('fechaActual').text();
		}
	});
}


/*
*Funcion que dispara el Action para consultar el Estado Civil
*/		
function cargaEstadoCivil(){
	$('#select3').empty();
	$('#select3').append('<option value="-1" selected="selected">-Seleccione-</option>');
	$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/ConsultarListaEstadoCivil.do',
		data: '',
		dataType: 'xml',
		success: function(xml){
			var option;
			$(xml).find('catEstadoCivil').each(function(){
				$('#select3').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
			});
		}
	});
}
	
	
function cargaOcupacion(){
	$('#select4').empty();
	$('#select4').append('<option value="-1" selected="selected">-Seleccione-</option>');
	$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catOcupacion').each(function(){
			$('#select4').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
			});
		}
	});
}

function cargaEscolaridad(){
	$('#select2').empty();
	$('#select2').append('<option value="-1" selected="selected">-Seleccione-</option>');
	$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/ConsultarCatalogoEscolaridad.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catEscolaridad').each(function(){
				$('#select2').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
			});
		}
	});
} 

function cargaParentesco(){
	$('#select').empty();
	$('#select').append('<option value="-1" selected="selected">-Seleccione-</option>');
	$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/ConsultarCatalogoParentesco.do',
		data: '',
		dataType: 'xml',
		async: false,
		success: function(xml){
			var option;
			$(xml).find('catParentesco').each(function(){
				$('#select').append('<option value="' + $(this).find('catRelacionId').text() + '">'+ $(this).find('descripcionRelacion').text() + '</option>');
			});
		}
	});
	
} 

/****** listeners para los botones de guardado de Atencion Psicologica*******/
function onClickBtnGuardarMotivoConsulta()
{
	var narrativa = escape($('#motivoConsultaUAVD').val());
	var opcionGuardado="MOTIVO_CONSULTA";
	if( narrativa != ""){
	guardaActualizaNarrativaEntrevistaComplementaria(narrativa,opcionGuardado);	
}
	else{
		 alert("El campo Motivo de Consulta debe ser ingresado");
	}
}

function onClickBtnGuardaConciencia()
{
	var narrativa = escape($('#motivoProblematicaUAVD').val());
	var opcionGuardado="CONCIENCIA_PROBLEMATICA";
	if( narrativa != ""){
	guardaActualizaNarrativaEntrevistaComplementaria(narrativa,opcionGuardado);
}
	else{
		 alert("El campo Conciencia de la Problemática debe ser ingresado");
	}
}

function onClickBtnGuardarImpresionFamiliar()
{
	var narrativa = escape($('#motivoDiagnosticaUAVD').val());
	var opcionGuardado="DIAGNOSTICO_FAMILIAR";
	if( narrativa != ""){
	guardaActualizaNarrativaEntrevistaComplementaria(narrativa,opcionGuardado);
}
	else{
		 alert("El campo Impresión Diagnóstica de la Familia debe ser ingresado");
	}

}

function onClickBtnGuardarHipotesisFamiliar()
{
	var narrativa = escape($('#motivoFamiliarUAVD').val());
	var opcionGuardado="HIPOTESIS_FAMILIAR";
	if( narrativa != ""){
	guardaActualizaNarrativaEntrevistaComplementaria(narrativa,opcionGuardado);
}
	else{
		 alert("El campo Hipótesis Familiar debe ser ingresado");
	}
}

/*
 * Funcion que guarda algunas de las narrativas de la entrevista complementaria en la BD
 * narrativa - cadeana con el texto de la narrativa correspondietne a la opcion que se desea guardar
 * opcionGuardado - cadena con los valores :MOTIVO_CONSULTA,CONCIENCIA_PROBLEMATICA,DIAGNOSTICO_FAMILIAR,HIPOTESIS_FAMILIAR
 * cada cadena representa alguna de las distintas narrativas de la entrevista complementaria
 */
function guardaActualizaNarrativaEntrevistaComplementaria(narrativa,opcionGuardado)
{
	var bandera=false;
	//alert("guarda_actualiza narrativa entrevista complementaria :: \n"+narrativa+" - "+opcionGuardado);
	//mandamos a guardar
	 $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/guardaActualizaNarrativaEntrevistaComplementariaPorIdUAVD.do?idSesion='+idSesion+'&narrativa='+narrativa+'&opcionGuardado='+opcionGuardado+'&fecha='+fechaActual+'',
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 $(xml).find('EntrevistaComplementariaDTO').each(function(){
			  bandera=true;
			  //alert("motivo_de_comparacion guardado con exito");
  	      	});
		  }
	});
	if(bandera==true)
	{
		alert("Se guardó o actualizó la entrevista complementaria exitosamente.");
	}
	else
	{
		alert("Ocurrió un error al guardar o actualizar la entrevista complementaria.");
	}
}
/****** FIN listeners para los botones de guardado *******/
 
 /*
 *Funcion para hacer la consulta de una entrevista complementaria por ID
 */
 function consultaEntrevistaCOmplementaria()
 {
	//mandamos a consultar
	 $.ajax({
	     type: 'POST',
	     url: '<%=request.getContextPath()%>/consultarEntrevistaComplementariaPorIdUAVD.do?idSesion='+idSesion+'',
		 dataType: 'xml',
		 async: false,
		 success: function(xml){
			 $(xml).find('EntrevistaComplementariaDTO').each(function(){
				 pintaDatosNarrativasEntrevistaComplementaria($(this));
  	      	});
		  }
	});
 }
 
 /*
 *Funcion para pintar los datos de la entrevista complementaria
 *xml - cadena con formato xml que contiene los datos de la entrevista
 */
 function pintaDatosNarrativasEntrevistaComplementaria(xml)
 {
	 $('#motivoConsultaUAVD').val($(xml).find('motivoConsulta').text());
	 $('#motivoProblematicaUAVD').val($(xml).find('concienciaProblema').text());
	 $('#motivoDiagnosticaUAVD').val($(xml).find('impresionDiagnostico').text());
	 $('#motivoFamiliarUAVD').val($(xml).find('hipotesisFamilia').text());
 }

function cargaGridFamiliares(){ 	
	jQuery("#gridFamiliares").jqGrid('setGridParam',{
		url:'<%= request.getContextPath()%>/consultarFamiliaresEntrevistaComplementariaPorId.do?idSesion='+idSesion+'',datatype: "xml" });
	$("#gridFamiliares").trigger("reloadGrid");
}	



function abrirEntrevistaInicial(id){

	$.newWindow({id:"iframewindowVisorEntrevistaInicial", statusBar: true, posx:151,posy:69,width:939,height:403,title:"Entrevista Inicial", type:"iframe"});
    $.updateWindowContent("iframewindowVisorEntrevistaInicial","<iframe src='<%= request.getContextPath() %>/visorEntrevistaInicial.do?rowid="+id+"' width='939' height='403' />");
}
function abrirEntrevistaComplementaria(id){

	$.newWindow({id:"iframewindowVisorTipoSesion", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Entrevista Complementaria", type:"iframe"});
    $.updateWindowContent("iframewindowVisorTipoSesion","<iframe src='<%= request.getContextPath() %>/visorEntrevistaComplementaria.do?rowid="+id+"' width='1140' height='400' />");
}
function abrirNotasEvolucion(id){

	$.newWindow({id:"iframewindowVisorNotasEjecucion", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Nota de Evolución", type:"iframe"});
    $.updateWindowContent("iframewindowVisorNotasEjecucion","<iframe src='<%= request.getContextPath() %>/visorNotaEjecucion.do?rowid="+id+"' width='1140' height='400' />");
}

function poppopIngresaFamilia(){

	$( "#dialog-Familia" ).dialog({
		autoOpen: true,
		resizable: true,
		height:300,
		width:630,
		modal: true,
		buttons: {
			"Aceptar": function() {
				if(validaCamposInsercion())
				{
					agregarFamiliar();
					limpiaCamposIngresoFamiliar();
					$( this ).dialog( "close" );	
				}
			},
			"Cancelar": function() {
				limpiaCamposIngresoFamiliar();
				$( this ).dialog( "close" );
			}
		}
	});
}

function agregarFamiliar()
{
	var paramFamiliar = recuperaInformacionFamiliar();
	paramFamiliar += "&idSesion="+idSesion+"&idFamiliar="+idFamiliar;
	//alert(paramFamiliar);
	$.ajax({
		async: false,
		type: 'POST',
		url: '<%= request.getContextPath()%>/ingresarFamiliarAyudaPsicologicaUAVD.do',
		data: paramFamiliar,
		dataType: 'xml',
		success: function(xml){
			//Venimos de una insercion
			if(parseInt($(xml).find('code').text())==0)
			{
				alert("Se guardó exitosamente el familiar.");
				limpiaCamposIngresoFamiliar();
				cargaGridFamiliares();
	    	}
			else
			{
				alert("Se debe ingresar toda la información del familiar.");
			}
		}
	});
}

function recuperaInformacionFamiliar()
{
	var params= "";
	//obtenemos los datos
	params +=  "nombre=" + $("#textfield").val();
	params +=  "&apPaterno=" + $("#textfield2").val();
	params +=  "&apMaterno=" + $("#textfield3").val();
	params +=  "&edoCivil=" + $("#select3 option:selected").val();
	params +=  "&escolaridad=" + $("#select2 option:selected").val();
	params +=  "&edadAprox=" + $("#textfield4").val();
	params +=  "&ocupacion=" + $("#select4 option:selected").val();
	params +=  "&parentesco=" + $("#select option:selected").val();
	return params;
}
	
/*
*Funcion que simula la funcion TRIM de otros lenguajes 
*
*/
function trim (myString)
{
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

function validaCamposInsercion()
{
	//validacion de nombre
	if(trim($("#textfield").val()).length==0)
	{
		alert("El campo Nombre debe ser ingresado");
		return false;
	}
	
	//validacion de apellido paterno
	if(trim($("#textfield2").val()).length==0)
	{
		alert("El campo Apellido Paterno debe ser ingresado");
		return false;
	}
	
	//validacion de apellido materno
	if(trim($("#textfield3").val()).length==0)
	{
		alert("El campo Apellido Materno debe ser ingresado");
		return false;
	}
	
	//validacion de edad aproximada
	if(trim($("#textfield4").val()).length==0)
	{
		alert("El campo Edad Aproximada debe ser ingresado");
		return false;
	}
	
	//validacion de parentesco
	if(parseInt($("#select option:selected").val())==-1)
	{
		alert("El campo Parentesco debe ser ingresado");
		return false;
	}
	
	//validacion de escolaridad
	if(parseInt($("#select2 option:selected").val())==-1)
	{
		alert("El campo Escolaridad debe ser ingresado");
		return false;
	}

	//validacion de estado civil
	if(parseInt($("#select3 option:selected").val())==-1)
	{
		alert("El campo Estado Civil debe ser ingresado");
		return false;
	}
	
	//validacion de ocupacion
	if(parseInt($("#select4 option:selected").val())==-1)
	{
		alert("El campo Ocupación debe ser ingresado");
		return false;
	}
	return true;  
}

function limpiaCamposIngresoFamiliar()
{
	  $("#textfield").val("");
	  $("#textfield2").val("");
	  $("#textfield3").val("");
	  $("#select3").find("option[value='-1']").attr("selected","selected");
	  $("#select2").find("option[value='-1']").attr("selected","selected");
	  $("#textfield4").val("");
	  $("#select4").find("option[value='-1']").attr("selected","selected");
	  $("#select").find("option[value='-1']").attr("selected","selected");
}

</script>
<body>
<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Familiares</a></li>
					<li><a href="#tabsconsultaprincipal-2">Motivo de Consulta</a></li>
					<li><a href="#tabsconsultaprincipal-3">Conciencia de la Problemática</a></li>
					<li><a href="#tabsconsultaprincipal-4">Dinámica Familiar</a></li>
					<li id="liPropuestaPsico"><a href="#tabsconsultaprincipal-5">Propuesta Psicológica</a></li>
					<li id="liExamenMental"><a href="#tabsconsultaprincipal-6">Examen Mental</a></li>
					<li id="liDiagnostico"><a href="#tabsconsultaprincipal-7">Diagnóstico</a></li>
					
			
					
				</ul>
				
		<!--Comienza div para ver los documentos propios del perito-->
				<div id="tabsconsultaprincipal-1">
				<input name="" type="button" value="Ingresar Familiar" class="btn_mediano"  onclick="poppopIngresaFamilia();"/>
				<table id="gridFamiliares"></table>
	            <div id="pagerGridFamiliares"></div>
				</div>
						
				<div id="tabsconsultaprincipal-2">
				<table width="100%" height="100%">
				<tr>
				 <td colspan="6">Motivo de Consulta (Explícito e Implícito)</td>
    <td colspan="2"><input name="" type="button" value="Guardar" class="btn_guardar"  id="btnGuardarMotivoConsulta"/></td>
   
    
  </tr>
  <tr>
    <td colspan="8" ><textarea class="jquery_ckeditor" name="motivoConsultaUAVD" cols="70" rows="10" id="motivoConsultaUAVD"></textarea>
   
  </tr>
 
</table>
				</div>
				
				
				<div id="tabsconsultaprincipal-3">
				
				<table width="100%" height="100%">
				<tr>
				 <td colspan="6">Conciencia de la Problemática (Herramientas con las que cuenta y afronta el problema que demanda)</td>
    <td colspan="2"><input name="" type="button" value="Guardar" class="btn_guardar"  id="btnGuardaConciencia"/></td>
   
    
  </tr>
  <tr>
    <td colspan="8"><textarea class="jquery_ckeditor" name="motivoProblematicaUAVD" cols="70" rows="5" id="motivoProblematicaUAVD"></textarea></td>
   
  </tr>
 
</table>
				</div>
				
				
				<div id="tabsconsultaprincipal-4">
				 <table width="100%" height="100%">
				  <tr>
				 <td colspan="8" align="center"><h1>BREVE HISTORIA FAMILIAR Y PERSONAL</h1></td>
			
  </tr>
				 <tr>
				 <td colspan="3">Impresi&oacute;n Diagn&oacute;stica de la Familia</td>
				    <td colspan="1" align="right"><input name="" type="button" value="Guardar" class="btn_guardar" id="btnGuardarImpresionFamiliar" /></td>
     <td colspan="3">Hip&oacute;tesis Familiar:</td>
    <td colspan="1" align="right"><input name="" type="button" value="Guardar" class="btn_guardar" id="btnGuardarHipotesisFamiliar" /></td>
   
    
  </tr>
  <tr>
    <td colspan="4"><textarea class="jquery_ckeditor" name="motivoProblematicaUAVD" cols="70" rows="5" id="motivoDiagnosticaUAVD"></textarea></td> 
    <td colspan="4"><textarea class="jquery_ckeditor" name="motivoProblematicaUAVD" cols="70" rows="5" id="motivoFamiliarUAVD"></textarea></td>
   
  </tr>
 
</table>
	            
				</div>
				
				
				<div id="tabsconsultaprincipal-5">
				</div>
				<div id="tabsconsultaprincipal-6">
				</div>
				
				
				<div id="tabsconsultaprincipal-7">
				</div>
				
				</div>		


<div id="dialog-Familia" style="display: none;" title="Ingresar Familiar">
<table width="100%" border="0">
  <tr>
    <td width="25%">*Nombre(s):</td>
    <td width="30%">
      <input type="text" name="textfield" id="textfield" />
    </td>
    <td width="17%">*Parentesco:</td>
    <td width="28%"><label>
      <select name="select" id="select">
      </select>
    </label></td>
  </tr>
  <tr>
    <td>*Apellido Paterno:</td>
    <td><input type="text" name="textfield2" id="textfield2" /></td>
    <td>*Escolaridad:</td>
    <td><select name="select2" id="select2">
    </select></td>
  </tr>
  <tr>
    <td>*Apellido Materno:</td>
    <td><input type="text" name="textfield3" id="textfield3" /></td>
    <td>*Estado Civil:</td>
    <td><select name="select3" id="select3">
    </select></td>
  </tr>
  <tr>
    <td>*Edad Aproximada:</td>
    <td><input type="text" name="textfield4" id="textfield4" onKeyPress="return solonumeros(event);" maxlength="3"/></td>
    <td>*Ocupación:</td>
    <td><select name="select4" id="select4">
    </select></td>
  </tr>
</table></div>
</body>
</html>