<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Denunciante</title>
		<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<!--    Hoja de estilo para easyaccordion-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#iVictimaAccordionPane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 6px;
				WIDTH: 1000px;
				PADDING-RIGHT: 0px;
				HEIGHT: 350px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#iVictimaAccordionPane DL {
				WIDTH: 1000px; HEIGHT: 345px
			}
			/*acordeon editar*/
			#iVictimaAccordionPane DT {
				TEXT-ALIGN: right;
				PADDING-BOTTOM: 16px;
				PADDING-TOP: 2px;
				PADDING-LEFT: 0px;
				LINE-HEIGHT: 35px;
				TEXT-TRANSFORM: none;	
				/*acomodo texto*/PADDING-RIGHT: 40px;
				FONT-FAMILY: Arial, Helvetica, sans-serif;
				LETTER-SPACING: 1px;
				/*distancia persianas*/HEIGHT: 25px;
				COLOR: #f5f5f5;
				FONT-SIZE: 12px;
				FONT-WEIGHT: normal;	
				background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
				background-repeat: no-repeat;
				background-position: 28px;
			}
			#iVictimaAccordionPane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#iVictimaAccordionPane DT.hover {
				COLOR: #f5f5f5
			}
			#iVictimaAccordionPane DT.hover.active {
				COLOR: #f5f5f5
			}
			#iVictimaAccordionPane DD {
				BORDER-BOTTOM: #dbe9ea 0px solid; 
				BORDER-LEFT: 0px; 
				PADDING-BOTTOM: 1px; 
				PADDING-LEFT: 1px; 
				PADDING-RIGHT: 1px; 
				/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/ 
				BORDER-TOP: #dbe9ea 0px solid; 
				MARGIN-RIGHT: 1px; 
				BORDER-RIGHT: #dbe9ea 0px solid; 
				PADDING-TOP: 1px
			}
			/*distancia y color de numero*/
			#iVictimaAccordionPane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#iVictimaAccordionPane .active .slide-number {
				COLOR: #fff
			}
			#iVictimaAccordionPane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#iVictimaAccordionPane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#iVictimaAccordionPane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#iVictimaAccordionPane .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
		</style>
		<!--Hoka de estilo para el texto dentro de los acordeones-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<!--Hoja de estilo para los popups-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--Scripts necesarios para el funcionamiento de la JSP-->

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<!--Scrip para el idioma del calendario-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" />
		<script type="text/javascript">
		var verAlias=1;
			var idWindowIngTutor = 1;

			var numeroExpediente="";
			var xml;
			var idindi=0;
			var idOrganizacion=0;
			var calidadInv="";
			var elemntoNuevo="no";
			var deshabilitarCampos = window.parent.deshabilitarCamposPM;
			var modificaGrid=true;
			jQuery().ready(function () {
				idindi=0;
					//agregamos el listener del evento onchange del combo de tipo de persona
					//$("#cbxDenuncianteTipoPerosona").change(changeTipoPersonaMoral);
					//$("#cbxDenuncianteTipoPerosona").multiselect({ 
					//	multiple: false, 
					//	header: "Seleccione", 
					//	selectedList: 1, 
					//	height: "auto",
						//click: function (event,ui){
						//	changeTipoPersonaMoral();},
					//	close: function (event,ui){
					//			changeTipoPersonaMoral();}
					//});

					$("#cbxDenuncianteTipoPerosona").change(function(e){
							changeTipoPersonaMoral();
						}); 
					
					var id=<%= request.getAttribute("idInvolucrado")%>;
					numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
					calidadInv='<%= request.getParameter("calidadInv")%>';
					var idDenunciante='<%= request.getParameter("idDenunciante")%>';
					elemntoNuevo='<%= request.getParameter("elemento")%>';
					var num=parent.num;
					if(num!=null && num!="0"){
						$("#anularInvolucrado").hide();
						$("#anularInv").hide();
					}
					$( "#tabs" ).tabs();
					$("#iVictimaCmpMenorEdad").click(creaNuevoTutor);
					$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
						modal: true, 
						title: 'Menor de Edad', 
						dialogClass: 'alert', 
						width: 500 ,
						maxWidth: 600,
						buttons: {"Aceptar":function() {
											$(this).dialog("close");
										}
									} 
					});
					$('#iVictimaAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});
					//$("#iVictimaCmpMenorEdad").click(formaCapturaMenorEdad);
					$("#iAnonimo").click(anonimo);
					
					//Se agrega Multiselect al combo tipo persona
					//$("#cbxDenuncianteTipoPerosona").multiselect({ 
					//	multiple: false, 
					//	header: "Seleccione", 
					//	selectedList: 1, 
					//	height: "auto",
					//	close: function (event,ui){
					//		changeTipoPersonaMoral();}
					//});		

					//Codigo para obtener los datos de la pantalla
					$("#guardarDenuncia").click(guardarDenunciante);
					//Codigo para obtener los datos de la pantalla
					$("#iIngOrgBtnGuardar").click(guardarOrgDenunciante);
					
					ocultaDomicilioNotificaciones();
					$('#iIngresarOrgWorkSheet').hide();
					$('#iDenuncianteWorkSheet').show();
					$('#modificarDatos').hide();
					$("#modificarDatos").click(avilitaDatos);
					cargaOrganizacion();
					
					//codigo para anular 
					$('#anularInvolucrado').hide();
					$("#anularInvolucrado").click(eliminarDenunciante);

					$("#iDenuncianteCmpServidorPublico").click(formaCapturaServidorPublico);
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);
					if(idDenunciante != "null"){
						consulta(idDenunciante);
						$('#anularInvolucrado').show();
					}
					else{
						inicializaDatosGenerales();
					}
					
					//Instruccion pensada solo para el caso de policia ministerial
					if(deshabilitarCampos == true){
						$(":enabled").attr('disabled','disabled');
					}
					//ocultamos los campos referente a Alias
					$("#trAliasTxt").hide();
					$(".tdAliasCmp").hide();
				});

			function avilitaDatos(){
				avilitarDatosGenerales();
				avilitarDatosDomicilio();
				avilitarDatosIdentificacion();
				desbloqueaCamposMediosDeContactoGrid();
				$('#anularInvolucrado').show();
				$('#guardarDenuncia').show();
				$('#modificarDatos').hide();
				modificaGrid=true;
			}
			
			//asociamos la funcion que atiende el evento click del check de servidor publico
			function formaCapturaServidorPublico() {
				if ($("#iDenuncianteCmpServidorPublico").is(':checked')) {
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",1);
				}else{
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);
				}				
			}

			function consulta(id){
				$.ajax({
			    	  type: 'POST',
			    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
			    	  data: 'idInvolucrado='+id,
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
			    		  var tipoPer=$(xml).find('tipoPersona').text();
			    		  var condicion=$(xml).find('condicion').text();
			    		  var desconocidoAnonimo=$(xml).find('desconocido').text();
			    		  if(desconocidoAnonimo=="true"){
			    			  $('#iAnonimo').attr("checked","checked");
			    		  }
			    		  if(condicion!="0"){
			    			  $('#chbDenuncianteVictima').attr("checked","checked");
				    		}
			    		  if(tipoPer=="0"){
			    			  $('#cbxDenuncianteTipoPerosona').find("option[value='"+tipoPer+"']").attr("selected","selected");
			    			  changeTipoPersonaMoral();
			    			  seteaDatosPersonaMoralConsOrg(xml);
			    			  pintaDatosDomicilioOrganizacion(xml);
					      }else{
						      var servidorPubli=$(xml).find('esServidor').text();
						      if(servidorPubli=='true'){
						    	  $('#iDenuncianteCmpServidorPublico').attr("checked","checked");
						    	  formaCapturaServidorPublico();
						    	  pintaServidorPublico(xml);
								}
						      pintaDatosGenerales(xml);
						      //seteamos la informacion del domicilio
						      pintaDatosDomicilio(xml);
		    				  //seteamos la informacion del domicilioNotificacion
		    				  pintaDatosDomicilioNotif(xml);
				    		  pintaDatosTipoDocIdentificacion(xml);
				    		  pintaDatosContacto(xml);
				    		  
						   }
					  }
			    });
				idindi=id;
				
				desavilitarDatosGenerales();
				
				desavilitarDatosDomicilio();
				desavilitarDatosIdentificacion();
				bloqueaCamposMediosDeContactoGrid();
				mediosContactoCorreoActualiza();
				mediosContactoTelefonoActualiza();
				
				$('#modificarDatos').show();				
				$('#guardarDenuncia').hide();

			}

			/*
			* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
			* y un 0 para deshabilitar o un 1 para habilitar
			*/
			function habilitaDeshabilitaTabAcordeon1(idTabAcordeon,bandera)
			{
				if(parseInt(bandera)==0)//Deshabilita el tab del acordeon
				{
					$("#"+idTabAcordeon).unbind('click');
					if($("#"+idTabAcordeon).hasClass('active'))
					{
						$("#"+idTabAcordeon).removeClass('active').addClass('inactive');
						$("#"+idTabAcordeon).parent().find('dt.no-more-active:first').click();
					}
					else
					{
						$("#"+idTabAcordeon).removeClass('no-more-active').addClass('inactive');
					}
				}
				else//habilita los tabs del acordeon
				{
					$("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
					$("#"+idTabAcordeon).click(function(){		
						jQuery($("#"+idTabAcordeon)).activateSlide();
						//clearTimeout(timerInstance.value);
					});
				}
			}

//			function datosDenunciante(id){
//		        $.ajax({
//				      type: 'POST',
//			    	  url: '<%= request.getContextPath()%>/ConsultarIndividuoDatos.do',
//			    	  data: 'idInvolucrado='+id,
//			    	  dataType: 'xml',
//			    	  async: false,
//			    	  success: function(xml){
			    		  //llamar(xml);
//			    		  $('#xml').val(xml);
//			    		  	var tipoPersona=$(xml).find('tipoPersona').text();
//			    		  	if(tipoPersona==1){
//			    		  		$('#iDenuncianteCmpNombre').val($(xml).find('nombreDemograficoDTO').find('nombre').text());
//					    		$('#iDenuncianteCmpApellidoPaterno').val($(xml).find('nombreDemograficoDTO').find('apellidoPaterno').text());
//					    		$('#iDenuncianteCmpApellidoMaterno').val($(xml).find('nombreDemograficoDTO').find('apellidoMaterno').text());
//					    		pintaDatosTipoDocIdentificacion(xml);
//					    		pintaDatosGenerales(xml);
//				    		}else{
//				    			$('#documentoIdentificacion').find("option[value='"+tipoPersona+"']").attr("selected","selected");
//				    			changeTipoPersonaMoral();
//				    			seteaDatosPersonaMoralConsOrg(xml);
//					    	}
				    		
				    		
				    		
//					  }
//			    	});
			      
//				}


				

				
				function formaCapturaMenorEdad() {
					if ($("#iVictimaCmpMenorEdad").is(':checked')) {
						$("#iVictimaAccordionDialogoMenorEdad").dialog("open");
					}
				}

				function anonimo(){
					var selected = $("#cbxDenuncianteTipoPerosona option:selected");
					if (parseInt(selected.val()) == 1 ){
						if ($("#iAnonimo").is(':checked')) {
							habilitaDeshabilitaTabAcordeon("datosGeneralesTab","mediosContactos",0);
							habilitaDeshabilitaTabAcordeon("domicilioTab","mediosContactos",0);
							habilitaDeshabilitaTabAcordeon("documentosIdentificacion","mediosContactos",0);
							//llamamos al metodo para limpiar los datos capturados
							limpiarDatosAnonimo();
							$("#mediosContactos").click();
						}else{
							habilitaDeshabilitaTabAcordeon("datosGeneralesTab","mediafiliacionTab",1);
							habilitaDeshabilitaTabAcordeon("domicilioTab","mediafiliacionTab",1);
							habilitaDeshabilitaTabAcordeon("documentosIdentificacion","mediosContactos",1);
						}
					}else{
						if ($("#iAnonimo").is(':checked')) {
							habilitaDeshabilitaTabAcordeon("datosGeneralesTab","mediosContactos",0);
							habilitaDeshabilitaTabAcordeon("domicilioTab","mediosContactos",0);
							habilitaDeshabilitaCamposIngOrganizacion(0);
							//llamamos al metodo para limpiar los datos capturados
							limpiarDatosAnonimo();
							$("#mediosContactos").click();
							
						}else{
							
							habilitaDeshabilitaTabAcordeon("domicilioTab","mediosContactos",1);	
							habilitaDeshabilitaCamposIngOrganizacion(1);		
						}
					}
				}
					
				//Funcion para limpiar los campos cuando seleccionamos Anonimo
				function limpiarDatosAnonimo()
				{
					//limpiamos los datos Generales
					cleanDatosGenerales();
					//limpiamos los datos de Nacimiento
					cleanAllCombosNacimiento();
					//limpia datos domicilio
					limpiarFormulario();
					//limpiamos los campos de coordenadas GPS
					limpiarDtsCrdndsGPS();
					//limpiamos los campos de coordenadas GPS de notificacion
					limpiarDtsCrdndsGPSNotif();
					//limpiamos los medios de contacto
					limpiarDtsMdsDCntct();
					//limpiamos los campos de doc de identificacion
					limpiarCmpsCptrDocId();
				}

				/*
				 *Imprime los datos que vienen de la funcion espejoDatos de datos generales, 
				 *en la pantalla ingresar probable responsable
				 */
				function imprimeDatosPadre(nombre, apPat, apMat){
					document.getElementById('iDenuncianteCmpNombre').innerHTML=nombre;
					document.getElementById('iDenuncianteCmpApellidoPaterno').innerHTML=apPat;
					document.getElementById('iDenuncianteCmpApellidoMaterno').innerHTML=apMat;
				}
				/*
				* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
				* y un 0 para deshabilitar o un 1 para habilitar
				*/
				function habilitaDeshabilitaTabAcordeon(idTabAcordeon,idTabAcordeonActive,bandera)
				{
					if(parseInt(bandera)==0)//Deshabilita el tab del acordeon
					{
						$("#"+idTabAcordeon).unbind('click');
						$("#"+idTabAcordeonActive).click();
						if($("#"+idTabAcordeon).hasClass('active'))
						{
							$("#"+idTabAcordeon).removeClass('active').addClass('inactive');
						}
						else
						{
							$("#"+idTabAcordeon).removeClass('no-more-active').addClass('inactive');
						}
					}
					else//habilita los tabs del acordeon
					{
						$("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
						$("#"+idTabAcordeon).click(function(){		
							jQuery($("#"+idTabAcordeon)).activateSlide();
							//clearTimeout(timerInstance.value);
						});
					}
				}

				function guardarDenunciante(){
					var validaRFC_CURP;
					validaRFC_CURP=camposGeneralesValidos();
					if(validaRFC_CURP==1){
						var nombreGeneralOP=$('#datosGeneralesCmpNombres').val();
						var anonimoOp=$('#iAnonimo').is(':checked');
					if(nombreGeneralOP!="" && anonimoOp!="true"){
						$("#guardarDenuncia").hide();				
						
						var params = '';
						params += 'idIndividuo='+idindi;
						params += '&calidadDelIndividuo=4';
						params += '&expediente=1';
						params += '&esMenorDeEdad=' + $('#iVictimaCmpMenorEdad').is(':checked');
						params += '&esPersonaMoral=' + $('#personaMoral').is(':checked');
						params += '&esVictimayDenunciante=' + $('#chbDenuncianteVictima').is(':checked');
						params += '&esServidorPublico=' + $('#iDenuncianteCmpServidorPublico').is(':checked');
						params += '&esAnonimo=' + $('#iAnonimo').is(':checked');
												
						//Datos generales, media filiacion, medios de contacto, documentos de identificacion
						var datosPestania = obtenerParametrosDatosGenerales();//Frame de datos generales
						params += datosPestania;
	
						//Datos nacimiento
						datosPestania = obtenerParametrosDatosNacimiento();
						params += datosPestania;	
	
						//Domicilio
						datosPestania = obtenerParametrosDomicilio();
						params += datosPestania;
	
						//Medios de contacto
						datosPestania = obtenerMedios();
						params += datosPestania;
	
						//Servidor publico
						if($('#iDenuncianteCmpServidorPublico').is(':checked')==true)
						{
							datosPestania = recuperoDatosServidorPublico();
							params += '&';
							params += datosPestania;
						}
	
						datosPestania=recuperaDatosTipoDocIdentificacion("Denunciante");
						params += '&';
						params += datosPestania;

						$.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do?numeroExpediente='+numeroExpediente +'',
					    	  data: params,				
					    	  dataType: 'xml',
					    	  async: false,
					    	  success: function(xml){
								if(elemntoNuevo=="si"){
									window.parent.refresca();
							    }else{
								    if($('#chbDenuncianteVictima').is(':checked')){
									    //cargaVictima
								    	window.parent.cargaVictimaDenunciante($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								    	//window.parent.cargaVictima($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
									}
							    	window.parent.cargaDenunciante($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								}
								  idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
					    		  alertDinamico('Denunciante guardado');
					    		  $('#guardarDenuncia').show();
					    	  }
					    	});
					}else if(anonimoOp==true){
						$("#guardarDenuncia").hide();				
						var params = '';
						params += 'idIndividuo='+idindi;
						params += '&calidadDelIndividuo=4';
						params += '&expediente=1';
						params += '&esMenorDeEdad=' + $('#iVictimaCmpMenorEdad').is(':checked');
						params += '&esPersonaMoral=' + $('#personaMoral').is(':checked');
						params += '&esVictimayDenunciante=' + $('#chbDenuncianteVictima').is(':checked');
						params += '&esServidorPublico=' + $('#iDenuncianteCmpServidorPublico').is(':checked');
						params += '&esAnonimo=' + $('#iAnonimo').is(':checked');
						
						//Datos generales, media filiacion, medios de contacto, documentos de identificacion
						var datosPestania = obtenerParametrosDatosGenerales();//Frame de datos generales
						params += datosPestania;
						//Datos nacimiento
						datosPestania = obtenerParametrosDatosNacimiento();
						params += datosPestania;	
						//Domicilio
						datosPestania = obtenerParametrosDomicilio();
						params += datosPestania;
						//Medios de contacto
						datosPestania = obtenerMedios();
						params += datosPestania;
	
						//Servidor publico
						if($('#iDenuncianteCmpServidorPublico').is(':checked')==true)
						{
							datosPestania = recuperoDatosServidorPublico();
							params += '&';
							params += datosPestania;
						}
	
						datosPestania=recuperaDatosTipoDocIdentificacion("Denunciante");
						params += '&';
						params += datosPestania;
						
						$.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do?numeroExpediente='+numeroExpediente +'',
					    	  data: params,				
					    	  dataType: 'xml',
					    	  async: false,
					    	  success: function(xml){
								if(elemntoNuevo=="si"){
									window.parent.refresca();
							    }else{
								    if($('#chbDenuncianteVictima').is(':checked')){
									    //cargaVictima
								    	window.parent.cargaVictimaDenunciante($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								    	//window.parent.cargaVictima($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
									}
							    	window.parent.cargaDenunciante($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								}
						    		
					    		  
					    		  idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
					    		  alertDinamico('Denunciante guardado');
					    		  $('#guardarDenuncia').show();
					    	  }
					    	});
					}else{
						alertDinamico('Favor de capturar el nombre del involucrado');
					}
				}
				else if(validaRFC_CURP==0){
					alertDinamico("Favor de verificar que el CURP ingresado sea correcto");
				}
				else{
					alertDinamico("Favor de verificar que el RFC ingresado sea correcto");
				}
			}				
				
 				function eliminarDenunciante(){					
					guardarDenuncianteEliminar();
					//window.parent.eliminaDenuncianteDeMenuIntermedio(idindi);
					//window.parent.cerrarVentanaDenunciante();					
				}
 				
 				function guardarDenuncianteEliminar(){
					if(idindi!='null' && idindi!=0)
					{
						//debemos mostrar un confirm
						customConfirm ("¿Está seguro que desea anular al denunciante?", "", anularInvolucrado);
					}
				}
 				
				//Funcion que manda a anular al involucrado en la BD
				function anularInvolucrado(){
					//primero revisaremos si el involucrado cuenta con relaciones
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%= request.getContextPath()%>/consultarRelacionesElementoXId.do',
				    	  data: 'idElemento='+idindi,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  	   //revisamo si hubo relaciones o no
				    		  	   if(parseInt($(xml).find('numRel').text())>-1)
								   {
				    		    	   if(parseInt($(xml).find('numRel').text())==0)
				    		    	   {
				    		    		   //no hay reaciones
				    		    		   anularInvolucradoCnRelaciones();
				    		    	   }
				    		    	   else{
				    		    		   //hay relaciones, preguntamos si desea eliminar
					    		    	   var mensaje = "El involucrado tiene relaciones con: <br/>";
					    		    	   //barremos la lista de relaciones
					    		    	   $(xml).find('cadena').each(function(){
					    		    		   mensaje+= $(this).text()+ "<br/>";
				            			   });
					    		    	   mensaje+= "<br/>¿Está seguro de querer eliminarlo?";
					    		    	   customConfirm (mensaje, "", anularInvolucradoCnRelaciones);
				    		    	   }
								   }
				    		       else
				    		      {
				    		    	   //casos de error
				    		    	   if(parseInt($(xml).find('numRel').text())>-1)
								   		{
				    		    		   //Lista nula
								   			customAlert("No se logró revisar si el involucrado tiene relaciones, intente más tarde");
								   		}
				    		    	   else if(parseInt($(xml).find('numRel').text())>-2)
									   {
				    		    		   //ID no llego
				    		    		   customAlert("Ocurrió un problema de conexión, intente más tarde");
									   }
				    		    	   else if(parseInt($(xml).find('numRel').text())>-3)
									   {
				    		    		   //excepcion
				    		    		   customAlert("Ocurrió un problema al tratar de eliminar el involucrado, intente más tarde");
									   }
				    		      }
				    	  }
				    });
				}
				
				//Funcion que manda a borrar un elemento con sus relaciones
				function anularInvolucradoCnRelaciones()
				{
					//el elemento no cuenta con relaciones por lo tanto se puede anular
 		    	   $.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/anularElemento.do',
					    	  data: 'idIndividuo='+idindi,				
					    	  dataType: 'xml',
					    	  success: function(xml){
					    		//procederemos a tratar de eliminar la evidencia
									if(parseInt($(xml).find('banderaOp').text())==1)
									{
										window.parent.eliminarDenunciante(idindi);
										window.parent.eliminarVictimaDenunciante(idindi);
										window.parent.customAlert("Sé logró anular al denunciante con éxito");
										cerrarCustomVentana();
										window.parent.$("#crearDenunciante").show();
										//window.parent.cerrarVentanaVictima();
									}
									else if(parseInt($(xml).find('banderaOp').text())==0)
									{
										//se puede eliminar el objeto sin problemas
										window.parent.customAlert("No se logró anular al denunciante");
									}
									else if(parseInt($(xml).find('banderaOp').text())==-1)
									{
										window.parent.customAlert("Ocurrión un error al tratar de anular al denunciante,<br/>consulte a su administrador ");
									}
					    	  }
					    });
				}
 				
				/**
				* Función que guarda los datos de la pantalla
				*/
				function guardarOrgDenunciante(){
					var params = '';
					params += 'idIndividuo='+idindi;
					params += '&calidadDelIndividuo=0';
					params += '&numeroExpediente='+numeroExpediente;
						params += '&esPersonaMoral=true';
						params += '&idUsuario=0';
						params += '&calidadPersonaMoral='+calidadInv;
						params += '&'+extraeDatosPersonaMoralIngOrg();//datos generales de organizacion
						params += obtenerParametrosDomicilio();//datos del domicilio de la organizacion
						params += obtenerMedios();//datos de los medios de contacto de la organizacion
						
						//llamamos al ajax que guardara la informacion de la organizacion
						$.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/guardarOrganizacion.do',
					    	  data: params,				
					    	  dataType: 'xml',
					    	  success: function(xml){
					    		  if(parseInt($(xml).find('code').text())==0)
					    		  {  
				    			    //el guardado fue exitoso
				    			    idOrganizacion=$(xml).find('organizacionId').text();//seteamos el id de la nueva organizacion
						    		//revisamos si se elimino el objeto durante la actualizacion  
									if($('#anularInvolucrado').is(':checked')=='false' || $('#anularInvolucrado').is(':checked')==false)
									{
									  var nombre =$(xml).find('organizacionDTO').find('nombreOrganizacion').text();
		    			 			  window.parent.cargaDenunciante(nombre,idOrganizacion);
					    			  //habilitamos el boton para poder guardar el representante legal y el contacto organizacional
						    		  //$("#iVictimaBtnGuardar").attr("disabled","disabled");
					    			  $("#iIngOrgBtnGuardar").attr("disabled","disabled");
					    			  $("#iIngOrgBtnIngresarContacto,#btnIngOrgFormalDatosRep").attr("disabled","");
						    		  alertDinamico("La organización se guardó exitosamente");
									}
									else
									{
										//mandamos eliminar visualmente el elemento anulado logicamente y luego cerraremos la ventana
										window.parent.eliminaDenuncianteDeMenuIntermedio(idOrganizacion);
										window.parent.cerrarVentanaDenunciante();
									}  
					    		  }
					    		  else
					    		  {
					    			  idOrganizacion=0;
					    			  alertDinamico("Ocurrió un error al guardar la organización");
					    		  }
					    	  }
					    	});
				}


				function changeTipoPersonaMoral(){
					var selected = $("#cbxDenuncianteTipoPerosona option:selected");
					if (parseInt(selected.val()) == 1 ){
						  anonimo();
						  //habilitaDeshabilitaTabAcordeon("mediafiliacionTab","mediafiliacionTab",1);
						  //habilitaDeshabilitaTabAcordeon("documentosIdentificacion","mediafiliacionTab",1);
						  
						  habilitaDeshabilitaTabAcordeon("datosGeneralesTab","domicilioTab",1);
						  habilitaDeshabilitaTabAcordeon("mediafiliacionTab","domicilioTab",1);
						  habilitaDeshabilitaTabAcordeon("documentosIdentificacion","domicilioTab",1);
							
						  $('#iIngresarOrgWorkSheet').hide();
						  $('#iDenuncianteWorkSheet').show();
						  liveDomicilioNotificaciones();
					}else{
						anonimo();
						//habilitaDeshabilitaTabAcordeon("datosGeneralesTab","mediafiliacionTab",0);
						//habilitaDeshabilitaTabAcordeon("mediafiliacionTab","mediosContactos",0);
						//habilitaDeshabilitaTabAcordeon("documentosIdentificacion","mediosContactos",0);
						
						habilitaDeshabilitaTabAcordeon("datosGeneralesTab","domicilioTab",0);
						habilitaDeshabilitaTabAcordeon("mediafiliacionTab","domicilioTab",0);
						habilitaDeshabilitaTabAcordeon("documentosIdentificacion","domicilioTab",0);
						
						$('#iIngresarOrgWorkSheet').show();
						$('#iDenuncianteWorkSheet').hide();
						killDomicilioNotificaciones();
						//$("#domicilioTab").click();
					}
				}
				/*
			  	*Funcion que crea una nueva ventana de un nuevo tutor
				*/	
				function creaNuevoTutor() {
				  idWindowIngTutor++;
				  if ($("#iVictimaCmpMenorEdad").is(':checked')) {
					  $.newWindow({id:"iframewindow" + idWindowIngTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Tutor", type:"iframe"});
					  $.updateWindowContent("iframewindow" + idWindowIngTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');
				  }
				}

						
		</script>
	</head>
<body>
<div id="dialog-Alert" style="display: none">
	<table align="center">
	<tr>
	<td align="center">
	<span id="divAlertTexto"></span>
	</td>
	</tr>
	</table>	
	</div>
	<input type="hidden" name="xml" id="xml" />
<TABLE border=0>
  <TBODY>
  <TR vAlign=top>
    <TD width="1060">
    <table width="196" border="0" cellspacing="0" cellpadding="2">
						<tr>
							<td  width="185" valign="top" width="111" align="left" valign="middle" class="txt_gral_victima">Tipo de Persona</td>
							
							<td width="85">
								<select id="cbxDenuncianteTipoPerosona">
						              <option value="1">F&iacute;sica</option>
						              <option value="0">Moral</option>
						          </select>
						    <td>
						    <td><input type="button" class="btn_Generico" id="anularInvolucrado" value="Anular Involucrado"></td>
						</tr>
	                </table>
	</TD>
  </TR>
  <TR vAlign=top>
    <TD>
      <TABLE width="100%" height="176" border=0 class="back_denuncia" id=iDenuncianteWorkSheet>
        <TBODY>
        <TR vAlign=top>
        </TR>	
        <TR vAlign=top>
          <TD width="37%" align="center" valign="middle">
          <table width="360" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
            <tr>
                <td width="10" height="109">&nbsp;</td>
                <td width="4">&nbsp;</td>
                <td width="123"><IMG id=iVictimaCmpFoto alt=foto src="<%= request.getContextPath() %>/resources/images/img_denunciante.png"></td>
                <td width="223">	
					
    	            <table width="195" border="0" cellspacing="0" cellpadding="0">
	                    <tr >
			 				<td width="111" align="left" valign="middle" class="txt_gral_victima" style="display: none;"> Servidor P&uacute;blico&nbsp;&nbsp;</td>
							<td width="84"><span class="txt_gral_victima" style="display: none;"><input type="checkbox" value="false" id="iDenuncianteCmpServidorPublico"/></span></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td>&nbsp;</td>
	                    </tr>
                  </table>
               </td>
           </tr>
          </table>
         </TD>
         <TD width="18%" align="left" valign="middle">
            <table width="177" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
              <tr>
                <td width="175" height="109" align="left"><table width="129" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="129" class="txt_gral_victima">Denunciante es: </td>
                  </tr>
                </table>
                  <table width="159" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                      <td width="159" class="txt_gral_victima">V&iacute;ctima</td>
                      <td width="159"><INPUT id=chbDenuncianteVictima value=false type=checkbox></td>
                      <td width="159" class="txt_gral_victima">An&oacute;nimo</td>
                      <td width="159"><INPUT id=iAnonimo value=false type=checkbox></td>
                    </tr>
                </table></td>
              </tr>
            </table>
		 </TD>         
         <TD width="45%" align="left" valign="top">
            <table width="92%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="23">&nbsp;</td>
              </tr>
            </table>
            <TABLE style="BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px" class=celda2 cellSpacing=0 cellPadding=0 width="73%" height=91>
              <TBODY>
              <TR>
                <TD width="30%" height=25 align=left class="txt_gral_victima">Nombre:</TD>
                <TD width="82%">
                	<div id="iDenuncianteCmpNombre" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div>
                </TD>
              </TR>
              <TR>
                <TD width="30%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
                <TD height=28 width="82%">
                	<div id="iDenuncianteCmpApellidoPaterno" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div>                	
                </TD>
              </TR>
              <TR>
                <TD width="30%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
	            <TD height=29>
	            	<div id="iDenuncianteCmpApellidoMaterno" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div>
	             </TD>
          	  </TR>
         	  </TBODY>
            </TABLE>
            <table width="92%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
                <td width="26%" align="left" valign="bottom"><INPUT type=button class="btn_modificar" id=modificarDatos value="Modificar Datos"></td>
                <td width="31%" align="left" valign="bottom"><INPUT type=button class="btn_guardar" id=guardarDenuncia value=Guardar></td>
              </tr>
            </table>
            <table width="92%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="43%" height="23">&nbsp;</td>
                
              </tr>
          	</table>
          </TD>
       </TR>
	   <TR vAlign=top>
       <TR vAlign=top>
          <TD colSpan=3>&nbsp;</TD>
        </TR>
        </TBODY>
    </TABLE>                		
	<!-- Tabla INGRESAR ORGANIZACION -->
      	<div id="iIngresarOrgWorkSheet"> <jsp:include page="ingresarOrganizacion.jsp"/></div>
    <!-- FIN Tabla INGRESAR ORGANIZACION -->
				

		<tr valign="top">
			<td>
				<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iVictimaAccordionPane" style="width: 100%" >
					            <dl>
					                <dt id="datosGeneralesTab">Datos Generales</dt>
					                <dd >
					                	<jsp:include page="datosGeneralesView.jsp"/>
									</dd>
					                <dt id="domicilioTab" style="width: 100%">Domicilio</dt>
					                <dd>
					                	<jsp:include page="ingresarDomicilioView.jsp"/>
						            </dd>
					                <!-- dt id="mediafiliacionTab">Media Filiación</dt>
					                <dd>
					                	<p> Media filiación</p>
					                </dd-->
					                <dt id="mediosContactos">Medios de Contacto</dt>
					                <dd>
					                	<jsp:include page="ingresarMediosContactoView.jsp"/>
					                </dd>
					                <dt id="documentosIdentificacion">Documentos de Identificación </dt>
					                <dd>
										<jsp:include page="ingresarDocumentoIdentificacionView.jsp"/>
									</dd>
<!-- 					                <dt id="servidorPublicoTab">Servidor Público</dt> -->
<!-- 					                <dd id="servidorPublicoTabCont"> -->
<%-- 					                	<jsp:include page="ingresarIndividuoServidorPublicoView.jsp"/> --%>
<!-- 					                </dd> -->
					            </dl>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<div id="iVictimaAccordionDialogoMenorEdad" title="Menor de edad">
	<table>
		<tr>
			<td>Denuncia</td>
			<td>&nbsp;</td>
			<td>Expediente</td>
		</tr>
		<tr>
			<td>Calidad:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iVictimaDialogoCmpCalidad"/></td>
		</tr>
		<tr>
			<td>Nombre Tutor:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iVictimaDialogoCmpNombre"/></td>
		</tr>
	</table>
</div>

</body>
</html>