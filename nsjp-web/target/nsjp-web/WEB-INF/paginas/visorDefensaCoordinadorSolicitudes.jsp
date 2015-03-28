<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>

<!--ESTILOS PARA LAS TABS HACE CHAMACOS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" media="screen" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>

       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" rel="stylesheet">
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

<script type="text/javascript">
$(function(){
	var el = $("#datosGeneralesCmpAlias").multiselect(),
		selected = $('#selected'),
		newItem = $('#newItem');
	
	$("#add").click(function(){
		var v = newItem.val(), opt = $('<option />', {
			value: v,
			text: v
		});
		
		opt.attr('selected','selected');
		opt.appendTo( el );
		el.multiselect('refresh');
		$("#newItem").val('');
	});
});




	$(document).ready(function() {

		$( "#tabsprincipalconsulta,#tabschild" ).tabs();
		
		var config = {					
				toolbar:
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
		


		
		$("#datosGeneralesCmpIdioma,#datosGeneralesCmpReligion,#datosGeneralesCmpEscoralidad,#datosGeneralesCmpEstadoCivil,#datosGeneralesCmpIdioma1,#datosGeneralesCmpReligion1,#datosGeneralesCmpEscoralidad1,#datosGeneralesCmpEstadoCivil1").multiselect({ 
			   multiple: false, 
			   header: "Seleccione", 
			   position: { 
				      my: 'center', 
				      at: 'center' 
				       
				     // only include the "of" property if you want to position 
				     // the menu against an element other than the button. 
				     // multiselect automatically sets "of" unless you explictly 
				     // pass in a value. 
				   } ,
			   //noneSelectedText: "1 Opcion", 
			   selectedList: 1 
			  
			   //#cbxPaisNacimiento,#cbxEntFederativaNacimiento,#cbxCiudadNacimiento,
			  

			});
		

	/*	$("#datosGeneralesCmpOcupacion").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1
			
		}); */

		

		
		
	$("#datosGeneralesCmpOcupacion").multiselect();
	$("#datosGeneralesCmpOcupacion1").multiselect();
	$("#datosGeneralesCmpNacionalidad,#datosGeneralesCmpNacionalidad1").multiselect(
			{ 
				header: "Selecciona de 1 a 3 opciones",
				click: function(e){
				
					if( $(this).multiselect("widget").find("input:checked").length > 3 ){
						
						return false;
					} 
					
				}		
	
			});

});




var id=<%= request.getAttribute("idInvolucrado")%>;
if(id!=null){
	//muestraDatosGenerales(id);
} 
	
	
	</script>

<script type="text/javascript">
  
      function muestraDatosGenerales(id) {
		  
	   $.ajax({
	     type: 'POST',
	     url: '<%= request.getContextPath()%>/ConsultarIndividuoDatos.do',
		 data: 'idInvolucrado='+id,
		 dataType: 'xml',
		 success: function(xml){
			 pintaDatosGenerales(xml);
			 
		  }
		});
	 }
   
  
			
			
			
	/*
	*Funcion que dispara el Action para consultar el Estado Civil
	*/		
    function cargaEstadoCivil(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarListaEstadoCivil.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('catEstadoCivil').each(function(){
    				$('#datosGeneralesCmpEstadoCivil').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#datosGeneralesCmpEstadoCivil1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }
		
		
    function cargaOcupacion(){
    	//alert("cargaOcupacion");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catOcupacion').each(function(){
    			$('#datosGeneralesCmpOcupacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			$('#datosGeneralesCmpOcupacion1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			
    			});
    		}
    	});
    }

	/*
	*Funcion que dispara el Action para consultar el Idioma
	*/		
    function cargaIdioma(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoIdioma.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catIdioma').each(function(){
    				$('#datosGeneralesCmpIdioma').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#datosGeneralesCmpIdioma1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }

    /*
	*Funcion que dispara el Action para consultar Nacionalidad
	*/		
    function cargaNacionalidad(){
    	  	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoNacionalidad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catNacionalidad').each(function(){
    				$('#datosGeneralesCmpNacionalidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#datosGeneralesCmpNacionalidad1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }
    
    function cargaEscolaridad(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoEscolaridad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catEscolaridad').each(function(){
    				$('#datosGeneralesCmpEscoralidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#datosGeneralesCmpEscoralidad1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    } 
	  
   
    
    function cargaReligion(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoReligion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('catReligion').each(function(){
    				$('#datosGeneralesCmpReligion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    				$('#datosGeneralesCmpReligion1').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
        			
    			});
    		}
    	});
    }

    
	


  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	
	function imprimeDatosPadre(nombre, apPat, apMat,nombre1, apPat1, apMat1 ){
		document.getElementById('iSolicitudCmpNombre').value=nombre;
		document.getElementById('iSolicitudCmpApellidoPaterno').value=apPat;
		document.getElementById('iSolicitudCmpApellidoMaterno').value=apMat;
		document.getElementById('iSolicitudCmpNombre1').value=nombre1;
		document.getElementById('iSolicitudCmpApellidoPaterno1').value=apPat1;
		document.getElementById('iSolicitudCmpApellidoMaterno1').value=apMat1;
	}

	/**
	 *Limpia todos los campos de esta página
	 */
	function cleanDatosGenerales(){

		$('#datosGeneralesCmpApaterno').val("");
		$('#datosGeneralesCmpMaterno').val("");
		$('#datosGeneralesCmpNombres').val("");
		$('#datosGeneralesCmpCurp').val("");
		$('#datosGeneralesCmpRfc').val("");
		$('#newItem').val("");

		
		//limpia los datos de la ventana padre
		imprimeDatosPadre("","","","","","");
		//Limpia todos los combo box
		
		
		$('#datosGeneralesCmpIdioma').attr('selectedIndex',0);
		$('#datosGeneralesCmpReligion').attr('selectedIndex',0);
		$('#datosGeneralesCmpEscoralidad').attr('selectedIndex',0);
		$('#datosGeneralesCmpEstadoCivil').attr('selectedIndex',0);

		//combos  multi select
		$('#datosGeneralesCmpAlias').attr('selectedIndex',0);
		$('#datosGeneralesCmpOcupacion').attr('selectedIndex',0);
		$('#datosGeneralesCmpNacionalidad').attr('selectedIndex',0);		
		
		//$('#datosGeneralesCmpIdioma').empty();
		//$('#datosGeneralesCmpEstadoCivil').append('<option value="seleccione">-Seleccione-</option>');
		//$('#datosGeneralesCmpSexo').attr('checked', false);
	}

	function mandaDatosPadre(){
        alert('llego a funcion hijo');
    	var valorCampoNombre=$('#datosGeneralesCmpNombres').val();
		var valorCampoApPat=$('#datosGeneralesCmpApaterno').val();
		var valorCampoApMat=$('#datosGeneralesCmpMaterno').val();
		var valorCampoCurp=$('#datosGeneralesCmpCurp').val();
		var valorCampoRfc=$('#datosGeneralesCmpRfc').val();
		var valorCampoFechaNac=$('#datosNacimientoCmpFechaNacimiento').val();
		var valorCampoEdadAproximada=$('#datosNacimientoCmpEdadAproximada').val();
        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
        alert('antes de regresar al padre');
		validaDatosFormatoTipo(valorCampoNombre, valorCampoApPat,valorCampoApMat,valorCampoCurp,valorCampoRfc,valorCampoFechaNac,valorCampoEdadAproximada);
	}

	function obtenerParametrosDatosGenerales(){
        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
        var parametros = '&nombre=' + $('#datosGeneralesCmpNombres').val();
        parametros += '&apellidoPaterno=' +  $('#datosGeneralesCmpApaterno').val();
        parametros += '&apellidoMaterno=' + $('#datosGeneralesCmpMaterno').val();
        parametros += '&curp=' + $('#datosGeneralesCmpCurp').val();
        parametros += '&rfc=' + $('#datosGeneralesCmpRfc').val();
        parametros += '&fechaIngreso=' + $('#datosGeneralesCmpFechaIngreso').val();
        parametros += '&idioma=' + $('#datosGeneralesCmpIdioma option:selected').val();        
        parametros += '&religion=' + $('#datosGeneralesCmpReligion option:selected').val();
        parametros += '&escolaridad=' + $('#datosGeneralesCmpEscoralidad option:selected').val();
        parametros += '&estadoCivil=' + $('#datosGeneralesCmpEstadoCivil option:selected').val();
        parametros += '&sexo=' + $(':radio[name=rbtSexoDatosGenerales]:checked').val();
        parametros += '&alias=' + $('#datosGeneralesCmpAlias').val();
        parametros += '&ocupacion=' + $('#datosGeneralesCmpOcupacion').val();
        parametros += '&nacionalidad=' + $('#datosGeneralesCmpNacionalidad').val();
        /*Faltan datos pendientes de esta pantalla como pais de nacimiento, estado y mnicipio, faltan loa alias ocupacion y nacionalidad*/
        //alert('parametros en datos generales:' + parametros);
		return parametros;
	}

			
		 function recuperaDatosDatosGenerales(idCalidad)
		   {
			   var lsDatosGenerales="";
			   lsDatosGenerales="nombre="+$("#datosGeneralesCmpNombres").val();
			   lsDatosGenerales+="&alias="+$("#datosGeneralesCmpAlias option:selected").val();
			   lsDatosGenerales+="&aPaterno="+$("#datosGeneralesCmpApaterno").val();
			   lsDatosGenerales+="&ocupacion="+$("#datosGeneralesCmpOcupacion option:selected").val();
			   lsDatosGenerales+="&aMaterno="+$("#datosGeneralesCmpMaterno").val();
			   lsDatosGenerales+="&nacionalidad="+$("#datosGeneralesCmpNacionalidad option:selected").val();
			   lsDatosGenerales+="&curp="+$("#datosGeneralesCmpCurp").val();
			   lsDatosGenerales+="&rfc="+$("#datosGeneralesCmpRfc").val();
			   lsDatosGenerales+="&fIngreso="+$("#datosGeneralesCmpFechaIngreso").val();
			   lsDatosGenerales+="&idioma="+$("#datosGeneralesCmpIdioma option:selected").val();
			   lsDatosGenerales+="&religion="+$("#datosGeneralesCmpReligion option:selected").val();
			   lsDatosGenerales+="&escolaridad="+$("#datosGeneralesCmpIdioma option:selected").val();
			   //lsDatosGenerales+="&estadocivil="+$("#datosGeneralesCmpReligion option:selected").val();
			   return lsDatosGenerales;
		   }

		   function pintaDatosGenerales(xml){
			   
			   $('#datosGeneralesCmpNombres').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').text());
			   $('#datosGeneralesCmpApaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').text());
			   $('#datosGeneralesCmpMaterno').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').text());
			   $('#datosGeneralesCmpCurp').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('curp').text());
			   $('#datosGeneralesCmpRfc').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('rfc').text());
			   if ($(xml).find('nombresDemograficoDTO').find('sexo').text() == "masculino"){
		    		 $('#datosGeneralesCmpSexoM').attr('checked','checked');
		    		
		    	 }else{
		    		 $('#datosGeneralesCmpSexoF').attr('checked','checked');
		    	 } 
			   
			   $(xml).find('aliasInvolucradoDTO').each(function(){
   				$('#datosGeneralesCmpAlias').append('<option selected="selected" value="' + $(this).find('aliasInvolucradoId').text() + '">'+ $(this).find('alias').text() + '</option>');

   				
   			});

			   
			   //$('#folioDocumentoIdentificacion').val($(xml).find('valorIdIdentificaion').find('nombreCampo').text());
			   //var id=$(xml).find('valorIdIdentificaion').find('valor').text();
			   //$('#datosGeneralesCmpAlias').find("option[value='"+id+"']").attr("selected","selected");
			   }

		   /*
		    *Funcion para pintar los datos mediante la recuperacion del xml de datos de nacimiento
		    */
		   function pintaDatosNacimiento(xml){
		   				

			   $('#datosNacimientoCmpFechaNacimiento').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('fechaNacimiento').text());
			   $('#datosNacimientoCmpEdadAproximada').val($(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('edadAproximada').text());
			   
		   	   var id0=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('paisValorDTO').find('idCampo').text();
		   	   $('#cbxPaisNacimiento').find("option[value='"+id0+"']").attr("selected","selected");

		   		onSelectChangePaisNacimiento();
		   	   
		   	   var id1=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('entidadFederativaDTO').find('entidadFederativaId').text();
		   	   $('#cbxEntFederativaNacimiento').find("option[value='"+id1+"']").attr("selected","selected");

		   		onSelectChangeEntFedNacimiento();

		   		var id2=$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('municipioDTO').find('municipioId').text();
			   	   $('#cbxCiudadNacimiento').find("option[value='"+id2+"']").attr("selected","selected");

		   	   }		 
		   
		  
		   
		   function selectTipoExpediente(){
				if($("#tipoExpediente").val()=="tecnico"){
					$("#divrestaurativa").css("display", "none");
					$("#divtecnico").css("display", "block");
					$("#divadscrito").css("display", "none");
				}
				
				if($("#tipoExpediente").val()=="adscrito"){
					$("#divrestaurativa").css("display", "none");
					$("#divtecnico").css("display", "none");
					$("#divadscrito").css("display", "block");
				}
				
				if($("#tipoExpediente").val()=="restaurativa"){
					$("#divrestaurativa").css("display", "block");
					$("#divtecnico").css("display", "none");
					$("#divnormal").css("display", "none");
					
				}
				
			}


		   function espejoDatos(){
				var valorCampoNombre;
				var valorCampoApPat;
				var valorCampoApMat;
				var valorCampoNombre1;
				var valorCampoApPat1;
				var valorCampoApMat1;
				
				valorCampoNombre=document.getElementById('datosGeneralesCmpNombres').value;
				valorCampoApPat=document.getElementById('datosGeneralesCmpApaterno').value;
				valorCampoApMat=document.getElementById('datosGeneralesCmpMaterno').value;
				valorCampoNombre1=document.getElementById('datosGeneralesCmpNombres1').value;
				valorCampoApPat1=document.getElementById('datosGeneralesCmpApaterno1').value;
				valorCampoApMat1=document.getElementById('datosGeneralesCmpMaterno1').value;
				
				//llama a la funcion que escribe los datos en la ventana padre
				imprimeDatosPadre(valorCampoNombre, valorCampoApPat,valorCampoApMat, valorCampoNombre1, valorCampoApPat1,valorCampoApMat1);
			}

		    function guardado(){
		    	
		    	$("#btnGenera").removeAttr('disabled');



			    }
		    
			
</script>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Generales</a></li>
		<li><a href="#tabsconsultaprincipal-2">Informaci&oacute;n del Caso</a></li>
		<li><a href="#tabsconsultaprincipal-3">Acuse de Atenci&oacute;n</a></li>
		<li><a href="#tabsconsultaprincipal-4">Designar Abogado</a></li>
	</ul>
	<div id="tabsconsultaprincipal-1">
		<!--<table width="25%" cellpadding="0" cellspacing="0" id="tblsolicitud">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="datosCaso">Datos del caso</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="">Acuse de atención</a></td>
						</tr>
						
					</table>					
	--></div>
	<div id="tabsconsultaprincipal-2">
		<table width="25%" cellpadding="0" cellspacing="0" id="tblInformacionCaso" bgcolor="#DDD">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id=""><b>Número de Caso:</b></a></td><td>&nbsp;&nbsp;&nbsp; 001 </td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="">Detenido:</a></td><td>&nbsp;&nbsp;&nbsp; Jasper Hayton </td>
						</tr>
							<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id=""><b>Delito Principal:</b></a></td><td>&nbsp;&nbsp;&nbsp; Robo </td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="">Fecha de Detenci&oacute;n  :</a></td><td>&nbsp;&nbsp;&nbsp; 24/05/2011 </td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="">Hora de Detenci&oacute;n  :</a></td><td>&nbsp;&nbsp;&nbsp; 14:30 </td>
						</tr>
						
					</table>		
	</div>
	<div id="tabsconsultaprincipal-3">
		
	<div id="tabschild" class="tabs-bottom">
	<ul>
		<li><a href="#tabschild-1">Solicitante</a></li>
		<li><a href="#tabschild-2">Para Quien Solicita</a></li>
		<li><a href="#tabschild-3">Motivo</a></li>
		<!-- <li><a href="#tabsconsultaprincipal-4">Enviar Notificación</a></li> -->
	</ul>
	<div id="tabschild-1">
		<jsp:include page="datosSolicitud.jsp"></jsp:include>					
	</div>
	<div id="tabschild-2">
		<jsp:include page="datosSolicitud2.jsp"></jsp:include>
	</div>
	<div id="tabschild-3">
	<center>
		<textarea class="jquery_ckeditor" name="Motivo" cols="70" rows="5" id="Motivo"></textarea>
		<br/>
		<input type="button" name="button" id="btnGuardar" value="Guardar" class="btn_Generico"/> &nbsp;
		<input type="button" name="button" id="btnGenera" value="Generar Acuse de Atención" class="btn_Generico"/>
		</center>
	</div>
	<div id="tabsconsultaprincipal-4">
		&nbsp;
	</div>
</div>
	
	 <!--<div id="tabschild-4">
		&nbsp;
	</div>
--></div>
</div>
<script type="text/javascript">
cargaEstadoCivil();
cargaOcupacion();
cargaNacionalidad();
cargaIdioma();	
cargaEscolaridad();
cargaReligion();
//cargaFechaCaptura();
/* cargaAlias(); */

</script>

</body>
</html>