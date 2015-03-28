<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Testigo</title>
		
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />
		
		
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
				
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
			
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/jquery.validate.min.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/jquery.maskedinput.js"></script>
			<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/valida/mktSignup.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
		
		<script type="text/javascript">
		
		var verAlias=1;
		var numeroExpediente="";
		var idWindowIngresarTutor = 1;
		var idindi=0;
		var elemntoNuevo="no";
		var audienciaId;
		var valorCalidad="";
		var idDatoPrueba="";
		//variable para recibir el identificador de la ventana
		var idWindow;
		//variable para recargar el grid de audiencia siguiente
		var esSiguiente;

		var idInvolucrado;

		var bloquearModificacion;

		var idMedioPruebaActuliza = 0;
			
		jQuery().ready(			
			function () {
				
				audienciaId = "<%= request.getParameter("audienciaId")%>";

				idWindow ='<%=request.getParameter("idWindow")%>';

				esSiguiente ='<%=request.getParameter("esSiguiente")%>';
				
				numeroExpediente='<%=request.getParameter("numeroExpediente")%>';
				
				idInvolucrado='<%=request.getParameter("idInvolucrado")%>';
				
				valorCalidad ='<%=request.getParameter("calidad")%>';

				idDatoPrueba ='<%=request.getParameter("idDatoPrueba")%>';

				//Usado para la consulta de medios de prueba
				bloquearModificacion ='<%=request.getParameter("bloquearModificacion")%>';
				

				//elemntoNuevo='<%=request.getParameter("elemento")%>';
				
				//se crean los tabs y el acordeon
				$("#tabs").tabs();
				$('#iTestigoBtnModificarDatos').hide();	
				$('#iTestigoAccordionPane').easyAccordion({ 
					autoStart: false, 
					slideInterval: 3000
				});

				$("#iTestigoCmpMenorEdad").click(formaCapturaMenorEdad);
				
				//deshabilitamos los campos de nombre y apellidos
				$("#iTestigoCmpNombre").attr("disabled","disabled");
				$("#iTestigoCmpApellidoPaterno").attr("disabled","disabled");
				$("#iTestigoCmpApellidoMaterno").attr("disabled","disabled");
					
				//agregamos el listener del evento click para el boton guardar
				$("#iTestigoBtnGuardar").click(guardarTestigo);
				$("#iTestigoBtnModificarDatos").click(avilitaDatos);
				
				//Oculta el domicilio de notificaciones	
				ocultaDomicilioNotificaciones();
				
				//Ocultalos los campos para el medio de prueba
				ocultarMedioPrueba();
			
				if(idInvolucrado != "null"){
					consulta(idInvolucrado);
				}

				if(idDatoPrueba != "null"){
					muestraMedioPrueba();
				}

				
			});
			
			
			/*
			*Funcion que oculta los datos para el medio de prueba
			*/
			function ocultarMedioPrueba(){
				$("#divEtiNumIdeMedioPrueba").hide();
				$("#divInputNumIdeMedioPrueba").hide();
			}


			/*
			*Funcion que muestra los datos para el medio de prueba
			*/
			function muestraMedioPrueba(){
				$("#divEtiNumIdeMedioPrueba").show();
				$("#divInputNumIdeMedioPrueba").show();
			}
			
			
			/*
			*Funcion que consulta los datos del involucrado y llama a las respectivas
			*funciones de las JSP includes para mostrar los datos
			*/
			function consulta(idInvolucrado){

				$.ajax({
			    	  type: 'POST',
			    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
			    	  data: 'idInvolucrado='+idInvolucrado,
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
			    		  datosXML=xml;
			    		  
					      idindi=idInvolucrado;
			    		  pintaDatosGenerales(xml);
			    		  pintaDatosDomicilio(xml);
			    		  pintaDatosDomicilioNotif(xml);
			    		  pintaDatosTipoDocIdentificacion(xml);
			    		  pintaDatosContacto(xml);
			    		  mediosContactoCorreoActualiza();
			    		  mediosContactoTelefonoActualiza();
					  }
			    });
				desavilitarDatosGenerales();
				desavilitarDatosDomicilio();
				desavilitarDatosIdentificacion();
				deshabilitaDatosContacto();
				$('#iTestigoBtnModificarDatos').show();
				$('#iTestigoBtnGuardar').hide();

				//Usado para la consulta de medios de prueba
				if(bloquearModificacion == "true"){
					$('#iTestigoBtnModificarDatos').hide();
					$('#iTestigoBtnGuardar').hide();
				}
			}

			function avilitaDatos(){
				avilitarDatosGenerales();
				avilitarDatosDomicilio();
				avilitarDatosIdentificacion();
				habilitaDatosContacto();
				$('#iTestigoBtnGuardar').show();
				$('#iTestigoBtnModificarDatos').hide();
				
			}
				
			/***********COMIENZA SECCION INGRESA TESTIGO MENOR DE EDAD**********/
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
			/************TERMINA SECCION INGRESA TESTIGO MENOR DE EDAD**********/
				
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
						});
					}
				}

				
				var idInvolucradoMedioPrueba = 0;
				/*
				*Funcion para guardar los datos del Testigo
				*/		
				function guardarTestigo(){

					if(validarGuardadoDatoPrueba() == true){

						var params;
						
						params = 'idIndividuo='+idindi;
						params += '&calidadDelIndividuo=5';
						params += '&expediente=1';
						params += '&esMenorDeEdad=' + $('#iTestigoCmpMenorEdad').is(':checked');
						//params += '&anular=' + $('#anularInvolucrado').is(':checked');
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

						
						$.ajax({	
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do?numeroExpediente='+numeroExpediente+'',
					    	  data: params,				
					    	  dataType: 'xml',
					    	  success: function(xml){
						    	  
					    		 //Si se logró guardar el involucrado 
								if(parseInt($(xml).find('code').text())!=0){
									
									//Si el id dato prueba es != null, significa que se guardara como medio de prueba
									if(idDatoPrueba != "null"){

										
										if(idindi==0){
											idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
											relacionarIndividuoMedioPrueba(idindi,idDatoPrueba);
										}
										else{
											idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
											relacionarIndividuoMedioPrueba(idindi,idDatoPrueba);
											consulta(idindi);
											window.parent.cargaGridMediosDePrueba();
										}
										//Cierra la ventana de ingresar Testigo
										//window.parent.cerrarVentanaIngresarTestigo(idWindow);
										
									}else{
										//Si el idDatoPrueba == null, entonces se guarda relacion con audiencia							
										if(idindi==0){
							    			idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();    
							    			//Se Asocia el individuo a la audiencia actual
							    			asociarIndividuoAAudiencia(idindi,audienciaId);
							    			//Mensaje de testigo guardado
											alertDinamico('Testigo guardado');
											consulta(idindi);
											
											//Cierra la ventana de ingresar Testigo
											//window.parent.cerrarVentanaIngresarTestigo(idWindow);
								    							    			
										}else{
											//window.parent.cargaTestigo($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
											alertDinamico('Modificación exitosa');
											var respInvolucradoId= $(xml).find('idIndividuo').text();
											consulta(respInvolucradoId);
										}

								    	//Si se llamó la ventana desde el grid de audiencia siguiente
										if(esSiguiente == "true"){
											//Recarga el grid de audiencia siguiente
											window.parent.cargaGridTestigo1();
										}else{
											//Recarga el grid de audiencia actual
										  	window.parent.cargaGridTestigo();
										}
									}
								}else{//Si NO se logró guardar el involucrado
									alertDinamico("Ocurrió un problema durante el guardado")
								}
					    	  }
					    	});
					}
					
				}

				/*
				*Valida que se hayan llenado los campos obligatorios los campos obligatorios 
				*/
				function validarGuardadoDatoPrueba(){
					
					if(idDatoPrueba != "null"){
						
						var numIde = $("#numIdePersona").val();
						var nombreMedio = $('#datosGeneralesCmpNombres').val();
						
						if((numIde != "") && (nombreMedio != "")){
							return true;
						}
						else{
							alertDinamico("Ingrese el nombre de la persona y el numero de identificación");
							return false;
						}
					}
					else{
						return true;
					}
				}
				
				/*
				* Asocia un involucrado recién creado a la audiencia con el id enviado como parámetro
				*
				*/
				function asociarIndividuoAAudiencia(individuo,audiencia){

					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%= request.getContextPath()%>/asociarInvolucradoAAudiencia.do',
				    	  data: 'involucradoId='+individuo+'&audienciaId='+audiencia,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		 window.parent.refresca();
				    	  }
				    	});
				}
				

				/*
				* Relaciona un  individuo como medio de prueba
				*
				*/
				function relacionarIndividuoMedioPrueba(idInvolucradoMedioPrueba,idDatoPrueba){

					var numIde = $("#numIdePersona").val();
					var nombreMedio = $('#datosGeneralesCmpNombres').val();
					var apPat = $('#datosGeneralesCmpApaterno').val();
					var apMat = $('#datosGeneralesCmpMaterno').val();

					if((numIde != "") && (nombreMedio != "")){
						
						$.ajax({								
					    	  type: 'POST',
					    	  url: '<%= request.getContextPath()%>/relacionarIndividuoMedioPrueba.do',
					    	  data: 'involucradoId='+idInvolucradoMedioPrueba+'&idDatoPrueba='+idDatoPrueba+'&nombreMedio='+nombreMedio+'&apPat='+apPat+'&apMat='+apMat+'&numIde='+numIde+'&idMedioPruebaActuliza='+idMedioPruebaActuliza,				
					    	  dataType: 'xml',
					    	  success: function(xml){
					    		  //Si se logró guardar el involucrado 
									if(parseInt($(xml).find('code').text())==0){

										idMedioPruebaActuliza = $(xml).find('body').text();
										alertDinamico("El medio de prueba ha sido guardado y relacionado al dato");
										window.parent.cargaGridMediosDePrueba();
										consulta(idInvolucradoMedioPrueba);
									}
					    	  }
					    	});
					}
					else{
						alertDinamico("Ingrese el nombre de la persona y el numero de identificación");
					}
					
				}
				
				/*
				*Imprime los datos que vienen de la funcion espejoDatos de datos generales
				*en la pantalla ingresar contacto de una organizacion
				*/
				function imprimeDatosPadre(nombre, apPat, apMat){
					document.getElementById('iTestigoCmpNombre').value=nombre;
					document.getElementById('iTestigoCmpApellidoPaterno').value=apPat;
					document.getElementById('iTestigoCmpApellidoMaterno').value=apMat;
				}
				
		</script>
	</head>
<body>
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iTestigoViewHeader" width="100%" border="0">
					<tr>
						<!--<td width="10%">DENUNCIA</td>-->
						<!--<td width="15%" class="txt_gral_victima" id="anularInv">Anular Involucrado</td>-->
						<!--<td width="55%" align="left"><input type="checkbox" value="false" id="anularInvolucrado"/></td>-->
						<!-- td width="30%" align="right">CALIDAD: Testigo</td-->
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iTestigoWorkSheet" width="100%"  border="0">
					<tr valign="top">
						<td width="50%">
							<table width="100%" border="0">
								<tr>
									<td>
										<img alt="foto" src="<%= request.getContextPath() %>/resources/images/foto.png" id="iTestigoCmpFoto">
									</td>
									<!--<td align="left">-->
									<!--Menor de Edad <input type="checkbox"  id="iTestigoCmpMenorEdad"/> -->
									<!--</td>-->
								</tr>
							</table>
					  </td>
						
						<td width="50%">
							<table width="100%" border="0">
								<tr>
									<td align="right">
										Nombre:
									</td>
									<td>
										<input type="text" size="50" maxlength="50" id="iTestigoCmpNombre" style="background:#DDD;border: 0;"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Paterno:
									</td>
									<td>
										<input type="text" size="50" maxlength="50" id="iTestigoCmpApellidoPaterno" style="background:#DDD;border: 0;"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Materno:
									</td>
									<td>
										<input type="text" size="50" maxlength="50" id="iTestigoCmpApellidoMaterno" style="background:#DDD;border: 0;"/>
									</td>
								</tr>
                                <tr>
									<td align="right">
										<div id="divEtiNumIdeMedioPrueba">
											Núm. identificación:
										</div>
									</td>
									<td>
										<div id="divInputNumIdeMedioPrueba">
                                        	<input id="numIdePersona" type="text" size="50" maxlength="20"/>
                                        </div>
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
</body>
</html>