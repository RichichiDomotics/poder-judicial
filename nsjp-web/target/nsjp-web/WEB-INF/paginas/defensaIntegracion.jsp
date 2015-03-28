<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>



	
	
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#tabsprincipalconsulta" ).tabs();
	});
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
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarListaEstadoCivil.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('catEstadoCivil').each(function(){
    				$('#datosGeneralesCmpEstadoCivil').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			});
    		}
    	});
    }
		
		
    function cargaOcupacion(){
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
    			});
    		}
    	});
    }

	/*
	*Funcion que dispara el Action para consultar el Idioma
	*/		
    function cargaIdioma(){
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
    			});
    		}
    	});
    }
    
    function cargaEscolaridad(){
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
    			});
    		}
    	});
    } 
	  
   
    
    function cargaReligion(){
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
    			});
    		}
    	});
    }

    
	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%= request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#datosGeneralesCmpFechaIngreso').val($(xml).find('fechaActual').text());
    		}
		});
    }
    


  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	function espejoDatos(){
		var valorCampoNombre;
		var valorCampoApPat;
		var valorCampoApMat;
		
		valorCampoNombre=document.getElementById('datosGeneralesCmpNombres').value;
		valorCampoApPat=document.getElementById('datosGeneralesCmpApaterno').value;
		valorCampoApMat=document.getElementById('datosGeneralesCmpMaterno').value;
		
		//llama a la funcion que escribe los datos en la ventana padre
		imprimeDatosPadre(valorCampoNombre, valorCampoApPat,valorCampoApMat);
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
		imprimeDatosPadre("","","");
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
    	var valorCampoNombre=$('#datosGeneralesCmpNombres').val();
		var valorCampoApPat=$('#datosGeneralesCmpApaterno').val();
		var valorCampoApMat=$('#datosGeneralesCmpMaterno').val();
		var valorCampoCurp=$('#datosGeneralesCmpCurp').val();
		var valorCampoRfc=$('#datosGeneralesCmpRfc').val();
		var valorCampoFechaNac=$('#datosNacimientoCmpFechaNacimiento').val();
		var valorCampoEdadAproximada=$('#datosNacimientoCmpEdadAproximada').val();
        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
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
		   
		   
		   /*
		   *Funcion para revisar la calidad del indiviudo cuando se ingresara un representante legal
		   */
		   function ocultaCamposPorCalidadIndividuo()
		   {
			   if((gingRepLegCalidadDelIndividuo!=null) && (gingRepLegCalidadDelIndividuo!=""))
			   {
					if(gingRepLegCalidadDelIndividuo="REPRESENTANTE_LEGAL")
					{
						//ocultamos los campos que no se deben mostrar cuando es un representante legal
						$("#trAliasTxt").hide();
						$(".tdAliasCmp").hide();
					}
			   }
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
</script>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Ingresar C&eacute;dula de Investigaci&oacute;n </a></li>
		<li><a href="#tabsconsultaprincipal-2">Asentar en Bit&aacute;cora</a></li>
		<li><a href="#tabsconsultaprincipal-3">Generar Acuerdos de Defensa</a></li>
		<!-- <li><a href="#tabsconsultaprincipal-4">Enviar Notificación</a></li> -->
	</ul>
	<div id="tabsconsultaprincipal-1">
							
	</div>
	<div id="tabsconsultaprincipal-2">
		
	</div>
	<div id="tabsconsultaprincipal-3">
	
	</div>
	<!-- <div id="tabsconsultaprincipal-4">
		&nbsp;
	</div> -->
</div>


</body>
</html>