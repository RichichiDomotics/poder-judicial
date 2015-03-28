<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Testigo</title>
		
<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<!--    Hoja de estilo para easyaccordion-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<!--    Estilo para el acordeon de ingresar probable responsable-->	
		<!-- <style type="text/css">
			dd p{line-height:120%}
			#iTestigoAccordionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iTestigoAccordionPane dl{width:1000px;height:355px}	
			#iTestigoAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iTestigoAccordionPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#68889b}
			#iTestigoAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iTestigoAccordionPane dt.hover{color:#E78F08;}
			#iTestigoAccordionPane dt.active.hover{color:#1C94C4}
			#iTestigoAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iTestigoAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iTestigoAccordionPane .active .slide-number{color:#fff;}
			#iTestigoAccordionPane a{color:#68889b}
			#iTestigoAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iTestigoAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iTestigoAccordionPane .more{padding-top:10px;display:block}
		</style> -->
		
		<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#iTestigoAccordionPane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 6px;
				WIDTH: 1000px;
				PADDING-RIGHT: 0px;
				HEIGHT: 362px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#iTestigoAccordionPane DL {
				WIDTH: 1000px; HEIGHT: 355px
			}
			/*acordeon editar*/
			#iTestigoAccordionPane DT {
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
			#iTestigoAccordionPane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#iTestigoAccordionPane DT.hover {
				COLOR: #f5f5f5
			}
			#iTestigoAccordionPane DT.hover.active {
				COLOR: #f5f5f5
			}
			#iTestigoAccordionPane DD {
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
			#iTestigoAccordionPane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#iTestigoAccordionPane .active .slide-number {
				COLOR: #fff
			}
			#iTestigoAccordionPane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#iTestigoAccordionPane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#iTestigoAccordionPane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#iTestigoAccordionPane .more {
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

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" /><script type="text/javascript">
		var verAlias=1;
		var numeroExpediente="";
			var idWindowIngresarTutor = 1;
			var idindi=0;
			var elemntoNuevo="no";
			var deshabilitarCampos = window.parent.deshabilitarCamposPM;
			var modificaGrid=true;

			/**
			*Variable para reusar la pantalla en modo de solicitante, y es usada por el 
			*visor de expediente defensoria, se usa para guardar, consultar y anular
			*/
			var solicitante;
			
			jQuery().ready(			
				function () {
					var id=<%= request.getAttribute("idInvolucrado")%>;
					numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
					elemntoNuevo='<%= request.getParameter("elemento")%>';
					solicitante = '<%= request.getParameter("solicitante")%>';
					//se crean los tabs y el acordeon
					//$("#tabsTestigo").tabs();
					$("#tabs").tabs();
					$('#iTestigoBtnModificarDatos').hide();	
					$('#iTestigoAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});
					$("#iTestigoCmpMenorEdad").click(formaCapturaMenorEdad);
					$("#iTestigoCmpServidorPublico").click(formaCapturaServidorPublico);
					//deshabilitamos los campos de nombre y apellidos
					$("#iTestigoCmpNombre").attr("disabled","disabled");
					$("#iTestigoCmpApellidoPaterno").attr("disabled","disabled");
					$("#iTestigoCmpApellidoMaterno").attr("disabled","disabled");	
					//deshabilito los tabs
					habilitaDeshabilitaTabAcordeon("servidorPublicoTab",0);
					//agregamos el listener del evento click para el boton guardar
					$("#iTestigoBtnGuardar").click(guardarTestigo);
					$("#iTestigoBtnModificarDatos").click(avilitaDatos);
					
					//codigo para anular
					$('#anularInvolucrado').hide();
					$("#anularInvolucrado").click(eliminarTestigo);
					
					//Revisamos si es una consulta para llenar los campos
					if(id!=null){
						seteaDatosConsultaTestigo(id);
					}
					var num=parent.num;
					if(num!=null && num!="0"){
						$("#anularInvolucrado").hide();
						$("#anularInv").hide();
					}
					ocultaDomicilioNotificaciones();

					var idTestigo='<%= request.getParameter("idTestigo")%>';
					if(idTestigo != "null"){
						$('#anularInvolucrado').show();
						consulta(idTestigo);
						if(solicitante=="true"){
							pantallaSolicitante();
					    }
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

			//Funcion para ocultar los campos que sobran de testigo, para el solicitante
			function pantallaSolicitante(){
				$("#divCheckTestigoProtegido").hide();
				$("#laberTestigoProtegido").hide();
				$("#anularInvolucrado").hide();
			}


			function consulta(id){
				$.ajax({
			    	  type: 'POST',
			    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
			    	  data: 'idInvolucrado='+id,
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
			    		  datosXML=xml;
			    		  var servidorPubli=$(xml).find('esServidor').text();
					      if(servidorPubli=='true'){
					    	  $('#iTestigoCmpServidorPublico').attr("checked","checked");
					    	  formaCapturaServidorPublico();
					    	  pintaServidorPublico(xml);
							}
			    		  pintaDatosGenerales(xml);
			    		  pintaDatosDomicilio(xml);
			    		  pintaDatosDomicilioNotif(xml);
			    		  pintaDatosTipoDocIdentificacion(xml);
			    		  var esProtegido=$(datosXML).find('esProtegido').text();
			    		  if(esProtegido!=null){
						   	  	if(esProtegido=='true'){
						    	  $('#cbxTestigoProtegido').attr('checked','checked');					    	  					    		
						    	}else{
						    	  $('#cbxTestigoProtegido').attr('checked','');
								}
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
				$('#iTestigoBtnModificarDatos').show();
				$('#iTestigoBtnGuardar').hide();
				$("#cbxTestigoProtegido").attr('disabled', 'disabled');

			}

			function avilitaDatos(){
				avilitarDatosGenerales();
				avilitarDatosDomicilio();
				avilitarDatosIdentificacion();
				desbloqueaCamposMediosDeContactoGrid();
				if (solicitante=="true"){
					$('#anularInvolucrado').hide();
				}else{
					$('#anularInvolucrado').show();
				}
				$('#iTestigoBtnGuardar').show();
				$('#iTestigoBtnModificarDatos').hide();
				$('#cbxTestigoProtegido').removeAttr('disabled');				
				modificaGrid=true;
			}
				
			/************  SECCION INGRESA TESTIGO **********/
				//asociamos la funcion que atiende el evento click del check de menor de edad
				function formaCapturaMenorEdad() {
					if ($("#iTestigoCmpMenorEdad").is(':checked')) 
					{
						creaNuevoTutor();
						$("#tutortab").attr('disabled', '').toggle();
					}else
					{
						$("#tutortab").attr('disabled', 'disabled').toggle();
					}
				}
				
				function creaNuevoTutor() {
					idWindowIngresarTutor++;
					$.newWindow({id:"iframewindow" + idWindowIngresarTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Tutor", type:"iframe"});
				    $.updateWindowContent("iframewindow" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
				}
				
				/*
				* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
				* y un 0 para deshabilitar o un 1 para habilitar
				*/
				function habilitaDeshabilitaTabAcordeon(idTabAcordeon,bandera)
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
				
				//asociamos la funcion que atiende el evento click del check de servidor publico
				function formaCapturaServidorPublico() {
					if ($("#iTestigoCmpServidorPublico").is(':checked')) {
						habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",1);
					}else{
						habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);
					}				
				}
				
				var datosIngresarTestigo="";
				
				/*
				*Recuperamos la informacion de los hijos
				*/
				function recuperaInformacionTipoDoc()
				{
					var informacionTipoDoc="";
					informacionTipoDoc=recuperaDatosTipoDocIdentificacion(1);
				}
				/************ FIN  SECCION INGRESA TESTIGO **********/
				
				/************  SECCION CONSULTA TESTIGO **********/
				
				/*
				*Funcion que llama al action que recuperara toda la informacion relacionada con el 
				* CU de consultar testigo
				*/
 				function seteaDatosConsultaTestigo(id) {
			          $.ajax({
					      type: 'POST',
				    	  url: '<%= request.getContextPath()%>/ConsultarIndividuoDatos.do',
				    	  data: 'idInvolucrado='+id,
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  //seteamos los datos en el CU concentrador
				    		  seteaDatosTestigoConcentrador(xml);
				    		  //seteamos los datos en el CU Datos Generales
				    		  pintaDatosGenerales(xml);
				    		  //setemos los datos en el CU Domicilio
				    		  
				    		  //setemoa los datos en el CU Medios de contacto
				    		  
				    		  //seteamos los datos en el CU Tipo de documento
							  pintaDatosTipoDocIdentificacion(xml);
				    		  //seteamos los datos en el CU Servidor Publico
				    	  }
				    	});
					}
				
				/*
				* Metodo para setear los valores en los campos de informacion en el jsp concentradora 
				*/
				function seteaDatosTestigoConcentrador(xml)
				{
					//Seteamos el nombre del testigo
		    		$("#iTestigoCmpNombre").val($(xml).find('nombreDemograficoDTO').find('nombre').text());
					$("#iTestigoCmpApellidoPaterno").val($(xml).find('nombreDemograficoDTO').find('apellidoPaterno').text());
					$("#iTestigoCmpApellidoMaterno").val($(xml).find('nombreDemograficoDTO').find('apellidoMaterno').text());
					//Revisamos si es un servidor publico
					var esServidorPublico=$(xml).find('esServidor').text();
					if(esServidorPublico=="true")
					{
						$('#iTestigoCmpServidorPublico').attr("checked", "checked");
						habilitaDeshabilitaTabAcordeon("servidorPublicoTab",1);
					}
					$('#iTestigoCmpServidorPublico').attr("disabled", true);
					$('#iTestigoCmpMenorEdad').attr("disabled", true);
				}
				
				/************ FIN SECCION CONSULTA TESTIGO **********/
				
				function guardarTestigo(){

					var validaRFC_CURP;
					validaRFC_CURP=camposGeneralesValidos();
					if(validaRFC_CURP==1){

					//inicio testigo protegido
					var esProtegido;
					if($('#cbxTestigoProtegido').is(':checked')==true)
						esProtegido=true;
					else 
						esProtegido=false;
					//fin testigo protegido
						
					var nombreGeneralOP=$('#datosGeneralesCmpNombres').val();
					if(nombreGeneralOP!=""){
						var params = '';
						params += 'idIndividuo='+idindi;
						if(solicitante=="true"){
							params += '&calidadDelIndividuo=11';
						}
						else{
							params += '&calidadDelIndividuo=5';
						}

						
						params += '&expediente=1';
						params += '&esProtegido='+esProtegido;
						params += '&esMenorDeEdad=' + $('#iTestigoCmpMenorEdad').is(':checked');
						params += '&esServidorPublico=' + $('#iTestigoCmpServidorPublico').is(':checked');
						
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
	
						//Documento de identificacion
						datosPestania = '&';
						datosPestania += recuperaDatosTipoDocIdentificacion();
						params += datosPestania;
	
						//Servidor publico
						if($('#iDenuncianteCmpServidorPublico').is(':checked')==true)
						{
						datosPestania = recuperoDatosServidorPublico();
						params += '&';
						params += datosPestania;
						}
						$.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do?numeroExpediente='+numeroExpediente+'',
					    	  data: params,				
					    	  dataType: 'xml',
					    	  success: function(xml){
					    		if(elemntoNuevo=="si"){
					    			window.parent.refresca();
							    }else{
							    	if(solicitante=="true"){
										//Bloquea Vista
							    		consulta($(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								    }
							    	else{
							    		window.parent.cargaTestigo($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
								    }
							    }
					    		  idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
					    		  if(solicitante=="true"){
					    			  alertDinamico('Solicitante guardado');
								  }
					    		  else{
					    			  alertDinamico('Testigo guardado');
						    		}
					    		  
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

				function eliminarTestigo(){					
					guardarTestigoEliminar();
				}
				
				function guardarTestigoEliminar(){
					if(idindi!='null' && idindi!=0)
					{
						//debemos mostrar un confirm
						customConfirm ("¿Está seguro que desea anular al testigo?", "", anularInvolucrado);
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
										window.parent.eliminarVictima(idindi);
										window.parent.customAlert("Sé logró anular al testigo  con éxito");
										cerrarCustomVentana();
										//window.parent.cerrarVentanaVictima();
									}
									else if(parseInt($(xml).find('banderaOp').text())==0)
									{
										//se puede eliminar el objeto sin problemas
										window.parent.customAlert("No se logró anular al testigo");
									}
									else if(parseInt($(xml).find('banderaOp').text())==-1)
									{
										window.parent.customAlert("Ocurrión un error al tratar de anular al testigo,<br/>consulte a su administrador ");
									}
					    	  }
					    });
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

				//Imprime los datos que vienen de la funcion espejoDatos de datos generales
				//en la pantalla ingresar contacto de una organizacion
				function imprimeDatosPadre(nombre, apPat, apMat){
					document.getElementById('iTestigoCmpNombre').innerHTML=nombre;
					document.getElementById('iTestigoCmpApellidoPaterno').innerHTML=apPat;
					document.getElementById('iTestigoCmpApellidoMaterno').innerHTML=apMat;
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
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iTestigoViewHeader" width="100%" border="0">
					<tr>
						<td width="13%">DENUNCIA</td>
						<td><input type="button" class="btn_Generico" id="anularInvolucrado" value="Anular Involucrado"></td>
						<!-- td width="30%" align="right">CALIDAD: Testigo</td-->
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iTestigoWorkSheet" width="100%"  border="0" >
					<tr valign="top">
						<td width="60%">
							<table width="100%" border="0">
								<tr>
									<td width="22%" rowspan="2">
										<img alt="foto" src="<%= request.getContextPath() %>/resources/images/foto.png" id="iTestigoCmpFoto">
									</td>
									<td width="20%" align="left" valign="top">
										<span id="laberTestigoProtegido">
											Testigo Protegido
										</span>									    
									</td>
									<td width="58%" align="left" valign="top">
										<div id="divCheckTestigoProtegido">
											<input type="checkbox" value="false" id="cbxTestigoProtegido"/>
										</div>
									</td>
                                    
								</tr>
								<tr>
                                <td align="left" style="display: none;">
								  Servidor P&uacute;blico&nbsp;&nbsp; </td>
									<td align="left" style="display: none;"><input type="checkbox"  id="iTestigoCmpServidorPublico"/></td>
								</tr>
							</table>
		        </td>
						
						<td width="40%">
							<table>
								<tr>
									<td align="right">
										Nombre:
									</td>
									<td>
										<div id="iTestigoCmpNombre" style="border: 0; background: #DDD;width: 250px;">&nbsp;</div>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Paterno:
									</td>
									<td>
										<div id="iTestigoCmpApellidoPaterno" style="border: 0; background: #DDD;width: 250px;">&nbsp;</div>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Materno:
									</td>
									<td>
										<div id="iTestigoCmpApellidoMaterno" style="border: 0; background: #DDD;width: 250px;">&nbsp;</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr valign="top">
						<td colspan="3">
							<table width="100%" >
								<tr valign="top">
									<td align="center">
										<input type="button" value="Modificar Datos" id="iTestigoBtnModificarDatos" class="btn_Generico"/>
										<input type="button" value="Guardar" id="iTestigoBtnGuardar" class="btn_Generico"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iTestigoAccordionPane" style="width: 100%" >
					            <dl>
					                <dt id="datosGeneralesIngTesTab">Datos Generales</dt>
					                <dd>
					                	<jsp:include page="datosGeneralesView.jsp"/>
									</dd>
					                <dt>Domicilio</dt>
					                <dd>
					                	<jsp:include page="ingresarDomicilioView.jsp"/>
                                    </dd>
					                <dt>Medios de Contacto</dt>
					                <dd>
					                	<jsp:include page="ingresarMediosContactoView.jsp"/>
					                </dd>
					                <dt>Documentos de Identificación </dt>
					                <dd>
					                	<jsp:include page="ingresarDocumentoIdentificacionView.jsp"/>

					                </dd>
<!--					                <dt id="servidorPublicoTab">Servidor Público</dt>-->
<!--					                <dd id="servidorPublicoTabCont">-->
<!--					                	<jsp:include page="ingresarIndividuoServidorPublicoView.jsp"/>-->
<!--					                </dd>-->
					            </dl>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>