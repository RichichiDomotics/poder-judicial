<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar Inmueble</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript">
	var guardarCombos = "no";
	
	jQuery().ready(			
			function () {
				cargaTiposInmueble();
				cargaCondicion();
				cargaTiposConstruccion();
				cargaUnidadesDeMedidaTerreno();
				cargaUnidadesDeMedidaConstruccion();
				///para ingresar la ubicacion del inmueble////
				cargaPaises();
				/**
				* Carga los escuchadores de eventos para los combo box's para 
				* el domicilio 
				*/
				//$("#cbxPais").change(onSelectChangePais);
				//$("#cbxEntFederativa").change(onSelectChangeEntFed);
				//$("#cbxCiudad").change(onSelectChangeCiudadMunicipioTipoAsentamiento);
				//$("#cbxDelegacionMunicipio").change(onSelectChangeCiudadMunicipioTipoAsentamiento);
				//$("#cbxTipoAsentamiento").change(onSelectChangeCiudadMunicipioTipoAsentamiento);
				hideControls("no");			//Funciones para esconder los controles
				
			 	$("#cbxTipoInmueble, #cbxTipoConstruccion, #cbxUnidadSuperficieTerreno, #cbxUnidadSuperficieConstruccion, #cbxCondicionInmueble, #cbxTipoAsentamiento, #cbxTipoCalle, #cbxAsentamientoColonia").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1 
				});
				$("#cbxPais").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1, 
					close: function (event,ui){
						onSelectChangePais();}
				});
				$("#cbxEntFederativa").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1, 
					close: function (event,ui){
						onSelectChangeEntFed();}
				});
				
				$("#cbxCiudad").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1, 
					close: function (event,ui){
						onSelectChangeCiudadMunicipioTipoAsentamiento();}
				});

				$("#cbxDelegacionMunicipio").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1, 
					close: function (event,ui){
						onSelectChangeCiudadMunicipioTipoAsentamiento();}
				});

				$("#cbxTipoAsentamiento").multiselect({ 
					multiple: false, 
					header: "Seleccione", 
					position: { 
						my: 'top', 
						at: 'top' 
					},
					selectedList: 1, 
					close: function (event,ui){
						onSelectChangeCiudadMunicipioTipoAsentamiento();}
				});
				
				
			});

	/*
	*Funcion que realiza la carga del combo de tipos de inmuebles
	*/
	function cargaTiposInmueble() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposInmueble.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposInmueble').each(function(){
					$('#cbxTipoInmueble').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga del combo de tipos de inmuebles
	*/
	function cargaTiposConstruccion() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposConstruccion.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposConstruccion').each(function(){
					$('#cbxTipoConstruccion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}


	/*
	*Funcion que realiza la carga del combo de tipos de unidades de medida para el terreno
	*/
	function cargaUnidadesDeMedidaTerreno() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarUnidadesMedida.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catUnidadMedida').each(function(){
					$('#cbxUnidadSuperficieTerreno').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga del combo de tipos de unidades de medida para la construccion
	*/
	function cargaUnidadesDeMedidaConstruccion() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarUnidadesMedida.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catUnidadMedida').each(function(){
					$('#cbxUnidadSuperficieConstruccion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}


	/*
	*Funcion que realiza la carga de la condicion del inmueble
	*/
	function cargaCondicion() {
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCondicion.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catCondicion').each(function(){
					$('#cbxCondicionInmueble').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

//FUNCIONES PARA EL GUARDADO DE LOS DATOS DE LA UBICACION DEL INMUEBLE///




 	/**
	  * Muestra u oculta los combo box's o cajas de texto dependiendo
	  * de el país seleccionado tiene o no, entidades federativas.
	  * Esto para el domicilio.
	  */	
	  function hideControls(existenEntidades) {

		if(existenEntidades == "si" ){

	      $('#divCbxEntFederativa').show();	
		  $('#divCbxCiudad').show();
		  $('#divCbxDelegacionMunicipio').show();
		  $('#divCbxAsentamientoColonia').show();
		  $('#divCbxTipoAsentamiento').show();
		  $('#divCbxTipoCalle').show();
		  
		  $('#entidadFederativa').hide();
		  $('#areaDelegacionMunicipio').hide();
		  $('#areaCiudad').hide();
		  $('#areaColonia').hide();
		  $('#areaAsentamiento').hide();
		  $('#areaTipoCalle').hide();
		}
		else{
		  $('#divCbxEntFederativa').hide();
		  $('#divCbxCiudad').hide();
		  $('#divCbxDelegacionMunicipio').hide();
		  $('#divCbxAsentamientoColonia').hide();
		  $('#divCbxTipoAsentamiento').hide();
		  $('#divCbxTipoCalle').hide();
		  
		  $('#entidadFederativa').show();
		  $('#areaDelegacionMunicipio').show();
		  $('#areaCiudad').show();
		  $('#areaColonia').show();
		  $('#areaAsentamiento').show();
		  $('#areaTipoCalle').show();
		}			
	  }



	  /**
	  * Limpia los combos:Ent Federativa, Ciudad, DelegacionMunicipio, AsentamientoColonia
	  * Tipo de Asentamiento y Tipo de Calle, para el Domicilio
	  */  
	  function cleanAllCombos(){
		  
		$('#cbxEntFederativa').empty();
		$('#cbxEntFederativa').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
		$('#cbxCiudad').empty();
		$('#cbxCiudad').append('<option value="-1">-Seleccione-</option>');	
		$('#cbxDelegacionMunicipio').empty();
		$('#cbxDelegacionMunicipio').append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').empty();
		$('#cbxAsentamientoColonia').append('<option value="-1">-Seleccione-</option>');
		$('#cbxTipoAsentamiento').empty();
		$('#cbxTipoAsentamiento').append('<option value="-1">-Seleccione-</option>');
		$('#cbxTipoCalle').empty();
		$('#cbxTipoCalle').append('<option value="-1">-Seleccione-</option>');
	  }


	  /**
	  * Limpia los combos que dependen del combo entidad federativa, para 
	  * el domicilio
	  */  
	  function cleanCombosDependsEntidadFed(){
		$('#cbxCiudad').empty();
		$('#cbxCiudad').append('<option value="-1">-Seleccione-</option>');
		$('#cbxDelegacionMunicipio').empty();
		$('#cbxDelegacionMunicipio').append('<option value="-1">-Seleccione-</option>');
		$('#cbxAsentamientoColonia').empty();
		$('#cbxAsentamientoColonia').append('<option value="-1">-Seleccione-</option>');
	  }


	  /**
	  * Limpia los combos que dependen del combo Ciudad, para 
	  * el domicilio
	  */  	
	  function cleanCombosDependsCiudad(){
		$('#cbxDelegacionMunicipio').empty();
		$('#cbxDelegacionMunicipio').append('<option value="-1">-Seleccione-</option>');	
		$('#cbxAsentamientoColonia').empty();
		$('#cbxAsentamientoColonia').append('<option value="-1">-Seleccione-</option>');
	  }


	  /**
	  * Limpia los combos que dependen del combo Delegacion/Municipio, para 
	  * el domicilio
	  */ 	
	  function cleanCombosDependsDelegMun(){
		$('#cbxAsentamientoColonia').empty();
		$('#cbxAsentamientoColonia').append('<option value="-1">-Seleccione-</option>');
	  }


	/**
	*Funcion que limpia el combo box de asentamiento colonia
	*/
	function cleanComboAsentColonia(){
		$('#cbxAsentamientoColonia').empty();
		$('#cbxAsentamientoColonia').append('<option value="-1">-Seleccione-</option>');
	}


	/*
	*Funcion que realiza la carga del combo de Paises
	*/
	function cargaPaises() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarPaises.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catPaises').each(function(){
					$('#cbxPais').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxPais').multiselect('refresh');
					});
			}
		});
	}


	/**
	* Si existe un cambio en el combo de paises se realiza la consulta de 
	* entidades federativas, y si la consulta es NO vacía se leventa la 
	* bandera para mostrar los combo box. Esto para el domicilio
	*/ 	
	function onSelectChangePais() {
		  
		var selected = $("#cbxPais option:selected");
		var existenEntidades = "no";
		guardarCombos = "no";
				
		cleanAllCombos();							//Limpia todos los combo box´s		
		//hideControls(existenEntidades);				//Si la opcion seleccionada no contiene entidades federativas se esconden los cbx's
		$.ajax({
			async: false,									// la accion cargar estados y llena el combo con la consulta
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarEntFederativas.do',
			data: 'glCatPaisId=' + selected.val(),	//Parametro para hacer la consulta de Entidades por Id del País
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catEntidadesFed').each(function(){
					$('#cbxEntFederativa').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxEntFederativa').multiselect('refresh');
					existenEntidades = "si";
					guardarCombos = "si";	
				});
				hideControls(existenEntidades);	
				cargaTipoAsent();
				cargaTipoCalle();
			}
		});
	}


	/**
	* Si existe un cambio en el combo de Entidades federativas se realiza la consulta de 
	* entidades Ciudades. Esto para el domicilio
	*/ 	
	function onSelectChangeEntFed() {
			  
		var selected = $("#cbxEntFederativa option:selected");
						
		cleanCombosDependsEntidadFed();
		$.ajax({											// la accion cargar cidades
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarCiudades.do',
			data: 'glCatEntidadFederativaId=' + selected.val(), 	//hace la consulta por el id de la Entidad Federativa
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catCiudades').each(function(){											//LLena el comboBox con la consulta
					$('#cbxCiudad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxCiudad').multiselect('refresh');
						});
			}
		});
		onSelectChangeCiudad();				
	}


	/**
	* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
	* Delegaciones/Municipios.
	*/ 	
	function onSelectChangeCiudad() {
			    
		var selected = $("#cbxEntFederativa option:selected");
		  
		//cleanCombosDependsCiudad();
		$.ajax({
			async: false,														// Ejecuta la accion cargar Delegacion/Municipio
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarDelgMun.do',
			data: 'glCatEntidadFederativaId=' + selected.val(), 				//hace la consulta por el id de la Entidad Federativa
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catDelegMun').each(function(){				//LLena el comboBox con la consulta
					$('#cbxDelegacionMunicipio').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					$('#cbxDelegacionMunicipio').multiselect('refresh');
					});
			}	
		});
	}


	/**
	* Si existe un cambio en el combo de Ciudade, delegacion, o tipo de asentamiento
	* se realiza la consulta de por medio de esos tres id´s 
	*/ 	
	function onSelectChangeCiudadMunicipioTipoAsentamiento() {

		var parametrosConsulta ='';
			  
		parametrosConsulta += 'glDelgMunId='+ $("#cbxDelegacionMunicipio option:selected").val();
		parametrosConsulta += '&glCatCiudadId=' + $("#cbxCiudad option:selected").val();
		parametrosConsulta += '&glCatTipoAsentamientoId=' + $("#cbxTipoAsentamiento option:selected").val();

		cleanComboAsentColonia();
		$.ajax({
			async: false,														// Ejecuta la accion cargar Delegacion/Municipio
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
			data: parametrosConsulta,
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
					$('#cbxAsentamientoColonia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					$('#cbxAsentamientoColonia').multiselect('refresh');
					});
			}
		});
	}

	/**
	* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
	* Delegaciones/Municipios.
	*/ 	
	function onSelectChangeDelgMun() {
		  
		var selected = $("#cbxDelegacionMunicipio option:selected");
		    
		cleanCombosDependsDelegMun();
		$.ajax({														// Ejecuta la accion cargar Delegacion/Municipio
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
			data: 'glDelgMunId=' + selected.val(),						//Realiza la consulta de acuerdo al id de la delegacion o municipio
			dataType: 'xml',
			success: function(xml){
			  $(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
				$('#cbxAsentamientoColonia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
			   });
			}
		});
	}

	/*
	*Funcion que realiza la carga del combo de tipo de asentamiento	
	*/
	function cargaTipoAsent() {	
	  
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarTipoAsent.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTipoAsentamiento').each(function(){
					$('#cbxTipoAsentamiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxTipoAsentamiento').multiselect('refresh');
					});  
		  	}
		});
	  }


	/*
	  *Funcion que realiza la carga del combo de tipo de calle,
	  *para el domicilio 
	  */
	  function cargaTipoCalle() {
		
		$.ajax({
		  type: 'POST',
		  url: '<%= request.getContextPath()%>/cargarTiposDeCalle.do',
		  data: '',
		  dataType: 'xml',
		  success: function(xml){
			var option;
			$(xml).find('catTipoCalle').each(function(){
				$('#cbxTipoCalle').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				$('#cbxTipoCalle').multiselect('refresh');
				 });			
		  }
		});
	  }	  

	function obtenerValoresInmueble(){

		var paramInmueble = 'glCantidad=' + $('#txtCantidadNumerario').val();
		paramInmueble += '&gcMoneda=' + $('#txtMonedaNumerario').val();
		paramInmueble += '&gcCtaTesoreria=' + $('#txtCuentaTesoreriaNumerario').val();
		paramInmueble += '&gcFechaDeposito='+ $('#idFechaDate').val();
		paramInmueble += '&gcHoraDeposito=' + $('#idHoraDate').val();
		paramInmueble += '&glCondicionFisica=' + $("#cbxCondicionNumerario option:selected").val();
		paramInmueble += '&gcDescripcion=' + $("#txtBoxDescNumerario").val();
						
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ingresarNumerario.do',
				data: paramNumerario,
				dataType: 'xml',
				success: function(xml){
				}
			});		
	}
	  function obtenerParametrosDomicilio(){
	        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
	        var IDPAIS_MEXICO = 10;
	        var idPais = $('#cbxPais option:selected').val();
	        var parametros = '&pais=' + idPais;
	        parametros += '&codigoPostal=' +  $('#codigoPostal').val();
	        //Cambiar por el id de Mexico
	        if(idPais==IDPAIS_MEXICO){        	
	            parametros += '&entidadFederativa=' + $('#cbxEntFederativa option:selected').val();
	            parametros += '&ciudad=' + $('#cbxCiudad option:selected').val();
	            parametros += '&delegacionMunicipio=' + $('#cbxDelegacionMunicipio option:selected').val();
	            parametros += '&asentamientoColonia=' + $('#cbxAsentamientoColonia option:selected').val();
	            parametros += '&tipoAsentamiento=' + $('#cbxTipoAsentamiento option:selected').val();
	            parametros += '&tipoCalle=' + $('#cbxTipoCalle option:selected').val();
	            
	        }else{
	            parametros += '&entidadFederativa=' + $('#entidadFederativa').val();
	            parametros += '&ciudad=' + $('#areaCiudad').val();
	            parametros += '&delegacionMunicipio=' + $('#areaDelegacionMunicipio').val();
	            parametros += '&asentamientoColonia=' + $('#areaColonia').val();
	            parametros += '&tipoAsentamiento=' + $('#areaAsentamiento').val();        
	            parametros += '&tipoCalle=' + $('#areaTipoCalle').val();
	        }

	        parametros += '&calle=' + $('#areaCalle').val();
	        parametros += '&numExterior=' + $('#areaNumeroExterior').val();
	        parametros += '&numInterior=' + $('#areaNumeroInterior').val();
	        parametros += '&referencias=' + $('#areaReferencias').val();
	        parametros += '&entreCalle=' + $('#areaEntreCalle').val();
	        parametros += '&ycalle=' + $('#areaYCalle').val();
	        parametros += '&aliasDomicilio=' + $('#areaAlias').val(); //ALIAS DE DOMICILIO?
	        parametros += '&edificio=' + $('#areaEdificio').val();
	        parametros += '&longitud=' + $('#txtFldLongitud').val();
	        parametros += '&latitud=' + $('#txtFldLatitud2').val();

	        var mismoDomicilioNotificaciones = $(':radio[name=rbtMismoDomicilioNotificaciones]:checked').val();
	        parametros += '&mismoDomicilioNotificaciones=' + mismoDomicilioNotificaciones;

	        if(mismoDomicilioNotificaciones == 0){
	        	idPais = $('#cbxPaisNotif option:selected').val();
	            parametros += '&paisNotif=' + idPais;
	            parametros += '&codigoPostalNotif=' +  $('#codigoPostalNotif').val();
	            //Cambiar por el id de Mexico
	            if(idPais==IDPAIS_MEXICO){        	
	                parametros += '&entidadFederativaNotif=' + $('#cbxEntFederativaNotif option:selected').val();
	                parametros += '&ciudadNotif=' + $('#cbxCiudadNotif option:selected').val();
	                parametros += '&delegacionMunicipioNotif=' + $('#cbxDelegacionMunicipioNotif option:selected').val();
	                parametros += '&asentamientoColoniaNotif=' + $('#cbxAsentamientoColoniaNotif option:selected').val();
	                parametros += '&tipoAsentamientoNotif=' + $('#cbxTipoAsentamientoNotif option:selected').val();
	                parametros += '&tipoCalleNotif=' + $('#cbxTipoCalleNotif option:selected').val();
	                
	            }else{
	                parametros += '&entidadFederativaNotif=' + $('#entidadFederativaNotif').val();
	                parametros += '&ciudadNotif=' + $('#areaCiudadNotif').val();
	                parametros += '&delegacionMunicipioNotif=' + $('#areaDelegacionMunicipioNotif').val();
	                parametros += '&asentamientoColoniaNotif=' + $('#areaColoniaNotif').val();
	                parametros += '&tipoAsentamientoNotif=' + $('#areaAsentamientoNotif').val();        
	                parametros += '&tipoCalleNotif=' + $('#areaTipoCalleNotif').val();
	            }

	            parametros += '&calleNotif=' + $('#areaCalleNotif').val();
	            parametros += '&numExteriorNotif=' + $('#areaNumeroExteriorNotif').val();
	            parametros += '&numInteriorNotif=' + $('#areaNumeroInteriorNotif').val();
	            parametros += '&referenciasNotif=' + $('#areaReferenciasNotif').val();
	            parametros += '&entreCalleNotif=' + $('#areaEntreCalleNotif').val();
	            parametros += '&ycalleNotif=' + $('#areaYCalleNotif').val();
	            parametros += '&aliasDomicilioNotif=' + $('#areaAliasNotif').val(); //ALIAS DE DOMICILIO?
	            parametros += '&edificioNotif=' + $('#areaEdificioNotif').val();
	            parametros += '&longitudNotif=' + $('#txtFldLongitud').val();
		        parametros += '&latitudNotif=' + $('#txtFldLatitud2').val();
	        }else{
	        	idPais = $('#cbxPais option:selected').val();
	            parametros += '&paisNotif=' + idPais;
	            parametros += '&codigoPostalNotif=' +  $('#codigoPostal').val();
	            //Cambiar por el id de Mexico
	            if(idPais==IDPAIS_MEXICO){        	
	                parametros += '&entidadFederativaNotif=' + $('#cbxEntFederativa option:selected').val();
	                parametros += '&ciudadNotif=' + $('#cbxCiudad option:selected').val();
	                parametros += '&delegacionMunicipioNotif=' + $('#cbxDelegacionMunicipio option:selected').val();
	                parametros += '&asentamientoColoniaNotif=' + $('#cbxAsentamientoColonia option:selected').val();
	                parametros += '&tipoAsentamientoNotif=' + $('#cbxTipoAsentamiento option:selected').val();
	                parametros += '&tipoCalleNotif=' + $('#cbxTipoCalle option:selected').val();
	                
	            }else{
	                parametros += '&entidadFederativaNotif=' + $('#entidadFederativa').val();
	                parametros += '&ciudadNotif=' + $('#areaCiudad').val();
	                parametros += '&delegacionMunicipioNotif=' + $('#areaDelegacionMunicipio').val();
	                parametros += '&asentamientoColoniaNotif=' + $('#areaColonia').val();
	                parametros += '&tipoAsentamientoNotif=' + $('#areaAsentamiento').val();        
	                parametros += '&tipoCalleNotif=' + $('#areaTipoCalle').val();
	            }

	            parametros += '&calleNotif=' + $('#areaCalle').val();
	            parametros += '&numExteriorNotif=' + $('#areaNumeroExterior').val();
	            parametros += '&numInteriorNotif=' + $('#areaNumeroInterior').val();
	            parametros += '&referenciasNotif=' + $('#areaReferencias').val();
	            parametros += '&entreCalleNotif=' + $('#areaEntreCalle').val();
	            parametros += '&ycalleNotif=' + $('#areaYCalle').val();
	            parametros += '&aliasDomicilioNotif=' + $('#areaAlias').val(); //ALIAS DE DOMICILIO?
	            parametros += '&edificioNotif=' + $('#areaEdificio').val();
	            parametros += '&longitudNotif=' + $('#txtFldLongitud').val();
		        parametros += '&latitudNotif=' + $('#txtFldLatitud2').val();
		    }
	      	return parametros;
		}
	</script>
</head>
 
<body>
	<table width="762px"   height="533px" border="0" cellpadding="0" cellspacing="0">
        <tr height="220px">
            <td width="762px">
                <table width="762px"   height="220px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <table width="762px"  height="220px" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr height="12.5%">
                                    <td width="19%" align="right">&nbsp;</td>
                                    <td width="29%">&nbsp;</td>
                                    <td width="26%">Descripci&oacute;n:</td>
                                    <td width="26%">Fotografía:</td>
                                </tr >
                                <tr height="12.5%">
                                    <td width="19%" align="right">Tipo de inmueble:</td>
                                    <td width="29%"><select name="cbxTipoInmueble" id="cbxTipoInmueble" style="width:200px">
                                      <option value="-1">-Seleccione-</option>
                                  </select></td>
                                    <td width="26%" rowspan="6" align="center" valign="top">
                                        <textarea cols="25" rows="9" id="txtAreaDescripcionInmueble"></textarea>
                                    </td>
                                    <td width="26%" rowspan="6" align="center" valign="middle">
                                        <img src="<%=request.getContextPath()%>/resources/images/Foto.JPG" alt="foto del objeto" width="185" height="165" />
                                    </td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Tipo de construcci&oacute;n:</td>
                                    <td width="29%"><select name="cbxTipoConstruccion" id="cbxTipoConstruccion" style="width:200px">
                                      <option value="-1">-Seleccione-</option>
                                    </select></td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Superficie del terreno:</td>
                                    <td width="29%"><input type="text" id="txtSuperficieTerreno" style="width:195px"/></td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Unidad de medida</td>
                                    <td width="29%"><select name="cbxUnidadSuperficieTerreno" id="cbxUnidadSuperficieTerreno" style="width:200px">
                                      <option value="-1">-Seleccione-</option>
                                    </select></td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Superficie constru&iacute;da:</td>
                                    <td width="29%"><input type="text" id="txtSuperficieConstruida" style="width:195px"/></td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Unidad de medida:</td>
                                    <td width="29%"><select name="cbxUnidadSuperficieConstruccion" id="cbxUnidadSuperficieConstruccion" style="width:200px">
                                      <option value="-1">-Seleccione-</option>
                                    </select></td>
                                </tr>
                                <tr height="12.5%">
                                    <td width="19%" align="right">Condici&oacute;n:</td>
                                    <td width="29%"><select name="cbxCondicionInmueble" id="cbxCondicionInmueble" style="width:200px">
                                      <option value="-1">-Seleccione-</option>
                                  </select></td>
                                    <td width="26%">&nbsp;</td>
                                    <td width="26%">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr height="313px">
            <td width="762px">
                <table width="762px"  height="313px" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="150" height="25" align="right" >Pa&iacute;s:</td>
                        <td width="225" height="25">
                            <select id="cbxPais" style="width:200px;">						
                                <option value="-1">-Seleccione-</option>
                            </select>
                        </td>
                        <td width="130" height="25" align="right">Calle o Avenida:</td>
                        <td colspan="3" height="25" >
                            <input type="text" id="areaCalle" style="width:195px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" height="25">C&oacute;digo Postal:</td>
                        <td height="25">
                            <input type="text" id="codigoPostal" size="8" maxlength="5" />
							
                        </td>
                        <td align="right" height="25">N&uacute;mero Ext.:</td>
                        <td width="95" height="25" align="left">
                            <input type="text" id="areaNumeroExterior" size="8" maxlength="8"/>
                        </td>
                        <td width="90" height="25" align="right">N&uacute;mero Int.:</td>
                        <td width="58"  height="25" align="left">
                            <input type="text" id="areaNumeroInterior" size="8" maxlength="8"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Entidad Federativa:</td>
                        <td height="25"><div id="divCbxEntFederativa">
                            <select id="cbxEntFederativa" style="width:200px;">
                                <option value="-1">-Seleccione-</option>
                            </select></div>
                            <input style="width:195px" type="text" id="entidadFederativa" />
                        </td>
                        <td height="25" align="right">Entre calle:</td>
                        <td height="25" colspan="3"><input type="text" id="areaEntreCalle" style="width:195px"/></td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Ciudad:</td>
                        <td height="25"><div id="divCbxCiudad">
                            <select id="cbxCiudad"  style="width:200px;">
                                <option value="-1">-Seleccione-</option>
                            </select></div>
                            <input type="text" style="width:195px" id="areaCiudad"/>
                        </td>
                        <td height="25" align="right">y calle:</td>
                        <td height="25" colspan="3"><input type="text" id="areaYCalle" style="width:195px"/></td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Delegaci&oacute;n/Municipio:</td>
                        <td height="25"><div id="divCbxDelegacionMunicipio">
                            <select id="cbxDelegacionMunicipio" style="width:200px;">
                                <option value="-1">-Seleccione-</option>
                            </select></div>
                            <input type="text" style="width:195px" id="areaDelegacionMunicipio"/>					
                        </td>
                        <td height="25" align="right">Alias:</td>
                        <td height="25" colspan="3" ><input type="text" id="areaAlias" style="width:195px"/></td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Asentamiento o Colonia:</td>
                        <td height="25"><div id="divCbxAsentamientoColonia">
                            <select id="cbxAsentamientoColonia" style="width:200px;">
                                <option value="-1">-Seleccione-</option>
                            </select></div>
                            <input type="text" style="width:195px" id="areaColonia" />
                        </td>
                        <td height="25" align="right">Edificio:</td>
                        <td height="25" colspan="3" ><input type="text" id="areaEdificio" style="width:195px"></td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Tipo de Asentamiento:</td>
                        <td height="25"><div id="divCbxTipoAsentamiento">
                            <select id="cbxTipoAsentamiento" style="width:200px;">
                              <option value="-1">-Seleccione-</option>						
                            </select></div>
                            <input type="text" style="width:195px" id="areaAsentamiento" />
                        </td>
                        <td height="25" align="right">Referencias:</td>
                        <td height="25" colspan="3" >
                            <textarea id="areaReferencias" cols="45" rows="5" style="width: 195px; height:50px;" ></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td height="25" align="right">Tipo de Calle:</td>
                        <td height="25"><div id="divCbxTipoCalle">
                            <select id="cbxTipoCalle" style="width:200px;">
                                <option value="-1">-Seleccione-</option>
                            </select></div>
                            <input type="text" style="width:195px" id="areaTipoCalle" />
                        </td>  
                        <td height="25" align="right">Coord. Georeferenciales:</td>
                        <td height="25" colspan="3">
                            <jsp:include page="/WEB-INF/paginas/datosCoordenadasGPSView.jsp" flush="true"></jsp:include>
                        </td>
                    </tr>
                    <tr>
                        <td height="25" align="center" id="rowDomicilioNotif"><input type="button" id="btnGuardarInmueble" value="Guardar" onclick="obtenerValoresInmueble();" class="btn_Generico"/></td>
                        <td height="25" align="center" id="rowDomicilioNotif">&nbsp;</td>
                      <td height="25" align="right">&nbsp;</td>
                        <td colspan="3" >&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>