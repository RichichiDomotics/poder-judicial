<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Tutor</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
		<style type="text/css">
			dd p{line-height:120%}
			#iTutorAccordionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iTutorAccordionPane dl{width:1000px;height:355px}	
			#iTutorAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iTutorAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iTutorAccordionPane dt.hover{color:#E78F08;}
			#iTutorAccordionPane dt.active.hover{color:#1C94C4}
			#iTutorAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iTutorAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iTutorAccordionPane .active .slide-number{color:#fff;}
			#iTutorAccordionPane a{color:#68889b}
			#iTutorAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iTutorAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iTutorAccordionPane .more{padding-top:10px;display:block}
		</style>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		<script type="text/javascript">
		var idindi=0;
			jQuery().ready(			
				function () {
					$( "#tabs" ).tabs();
					$( "#tabs2" ).tabs();
					
					$("#iTutorAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
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
					$('#iTutorAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});
					$("#iTutorCmpMenorEdad").click(formaCapturaMenorEdad);
					$("#iTutorCmpServidorPublico").click(formaCapturaServidorPublico);
					habilitaDeshabilitaTabAcordeon("servidorPublicoTab",0);
					//Codigo para obtener los datos de la pantalla
					$("#iTutorBtnGuardar").click(guardarTutor);
					ocultaDomicilioNotificaciones();
					
					//si el id del prob responsable ,es null, no se ejecuta la consulta			
					var id=<%= request.getAttribute("idInvolucrado")%>;
					id="1";
					if(id!=null){
						muestraDatosTutor(id);
						
					} 
				});
			
				/***************************   SECCION CU  INGRESAR  TUTOR******************************/
				
				//asociamos la funcion que atiende el evento click del check de servidor publico
				function formaCapturaServidorPublico() {
					if ($("#iTutorCmpServidorPublico").is(':checked')) {
						habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",1);
					}else{
						habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);
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
				
				function formaCapturaMenorEdad() {
					if ($("#iTutorCmpMenorEdad").is(':checked')) {
						$("#iTutorAccordionDialogoMenorEdad").dialog("open");
					}					
				}	


				function cambiar(){
					if($("#domicilioNotificacion").is(':disabled')==false){
						$("#domicilioNotificacion").attr('disabled','disabled');
					}else{
						$("#domicilioNotificacion").removeAttr('disabled','disabled');
					}
				}

				function guardarTutor(){
					var params = '';
					params += 'idIndividuo='+idindi;
					params += '&calidadDelIndividuo=3';
					params += '&expediente=1';
					params += '&idCondicion=' + $(':radio[name=iTutorCmp]:checked').val();
					params += '&seCuentaConTutor=' + $(':radio[name=iTutorCmp]:checked').val();
					params += '&esServidorPublico=' + $('#iTutorCmpServidorPublico').is(':checked');

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
					/* datosPestania = obtenerDatosServidorPublico();
					params += '&';
					params += datosPestania; */
					
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  window.parent.cargaTestigo($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
				    		  idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
				    		  customAlert('Tutor guardado');
				    	  }
				    	});
				}

				//Imprime los datos que vienen de la funcion espejoDatos de datos generales
				//en la pantalla ingresar contacto de una organizacion
				function imprimeDatosPadre(nombre, apPat, apMat){
					document.getElementById('nombContactoOrg').value=nombre;
					document.getElementById('apPatContactoOrg').value=apPat;
					document.getElementById('apMatContactoOrg').value=apMat;
				}
				/***************************   FIN SECCION CU  INGRESAR  TUTOR******************************/
				/***************************   SECCION FUNCIONES GRALES CU  INGRESAR  TUTOR******************************/
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
				
				/*
				*Metodo para habilitar o deshabilitar los campos del maestro de Organizacion
				*bandera = 0 para deshabilitar, bandera=1 para habilitar los campos
				*/
				function habilitaDeshabilitaCamposIngTutor(bandera)
				{
					if(parseInt(bandera)==0)
					{
						//bloqueo los campos de solo lectura
						$("#iTutorCmpVivo").attr("disabled","disabled");
						$("#iTutorCmpDesconocido").attr("disabled","disabled");
						$("#iTutorCmpTutorSi").attr("disabled","disabled");
						$("#iTutorCmpTutorNo").attr("disabled","disabled");
						$('#iTutorCmpServidorPublico').attr("disabled","disabled");
						$('#divIdExpediente').attr("disabled","disabled");
						$('#iTutorCmpCalidad').attr("disabled","disabled");
						$('#iTutorCmpNombre').attr("disabled","disabled");
						$('#iTutorCmpApellidoPaterno').attr("disabled","disabled");
						$('#iTutorCmpApellidoMaterno').attr("disabled","disabled");
					}
					else
					{
						//habilito los campos 
						$("#iTutorCmpVivo").attr("disabled","");
						$("#iTutorCmpDesconocido").attr("disabled","");
						$("#iTutorCmpTutorSi").attr("disabled","");
						$("#iTutorCmpTutorNo").attr("disabled","");
						$('#iTutorCmpServidorPublico').attr("disabled","");
						$('#divIdExpediente').attr("disabled","");
						$('#iTutorCmpCalidad').attr("disabled","");
						$('#iTutorCmpNombre').attr("disabled","");
						$('#iTutorCmpApellidoPaterno').attr("disabled","");
						$('#iTutorCmpApellidoMaterno').attr("disabled","");
					}
				}
				/***************************   FIN SECCION FUNCIONES GRALES CU  INGRESAR  TUTOR******************************/
				/***************************   SECCION CU  CONSULTAR  TUTOR******************************/
				
				/*
			  	 *Funcion que realiza la consulta de los datos del tutor
			     */
			  	function muestraDatosTutor(id) {
				  
				   $.ajax({
				     type: 'POST',
				     url: '<%= request.getContextPath()%>/ConsultarIndividuoDatos.do',
					 data: 'idInvolucrado='+id,
					 dataType: 'xml',
					 success: function(xml){
						  //seteamos los datos en el CU concentrador
			    		  seteaDatosConsultaTutor(xml);
			    		  //seteamos los datos en el CU Datos Generales
			    		  pintaDatosGenerales(xml);
			    		  //setemos los datos en el CU Domicilio
			    		  
			    		  //setemoa los datos en el CU Medios de contacto
			    		  
			    		  //seteamos los datos en el CU Tipo de documento
						  pintaDatosTipoDocIdentificacion(xml);
			    		  //seteamos los datos en el CU Servidor Publico
			    		  
			    		  //mandamos bloquear los campos
						  habilitaDeshabilitaCamposIngTutor(0);
					  }
					});
				 }
				
			  	/*
				*Metodo para pintar los datos de un Tutor despues de hacer la consulta
				*/
				function seteaDatosConsultaTutor(xml)
				{
					
					//seteamos los radio buttons
					if($(xml).find('esVivo').text() == "1"){
						 $("#iTutorCmpVivo").attr('checked','checked');
					}
					if($(xml).find('esDesconocido').text() == "true"){	
						$("#iTutorCmpDesconocido").attr('checked','checked');
						//$("#iTutorCmpDesconocido").click();
					}
					if($(xml).find('condicion').text() == "1"){//el campo del find es experimental
						 $("#iTutorCmpTutorSi").attr('checked','checked');
					}
					else{
						 $("#iTutorCmpTutorNo").attr('checked','checked');
					}
					//Revisamos si es un servidor publico
					var esServidorPublico=$(xml).find('esServidor').text();
					if(esServidorPublico=="true")
					{
						$('#iTutorCmpServidorPublico').attr("checked", "checked");
						habilitaDeshabilitaTabAcordeon("servidorPublicoTab",1);
					}
					else
					{
						habilitaDeshabilitaTabAcordeon("servidorPublicoTab",0);
					}
					//seteo el numero del expediente
					$('#divIdExpediente').html($(xml).find('expedienteDTO').find('numeroExpediente').text());
					
					//seteo los campos de la informacion maestra
					$('#iTutorCmpCalidad').val($(xml).find('calidadDTO').find('descripcionEstadoFisico').text());
					$('#iTutorCmpNombre').val($(xml).find('nombreDemograficoDTO').find('nombre').text());
					$('#iTutorCmpApellidoPaterno').val($(xml).find('nombreDemograficoDTO').find('apellidoPaterno').text());
					$('#iTutorCmpApellidoMaterno').val($(xml).find('nombreDemograficoDTO').find('apellidoMaterno').text());
				}
				
				/***************************   FIN SECCION CU  CONSULTAR  TUTOR******************************/
				
				/*
		 *Imprime los datos que vienen de la funcion espejoDatos de datos generales, 
		 *en la pantalla ingresar probable responsable
		 */
		function imprimeDatosPadre(nombre, apPat, apMat){
		  document.getElementById('nombProResponsable').value=nombre;
		  document.getElementById('apPatProbResponsable').value=apPat;
		  document.getElementById('apMatProbResponsable').value=apMat;
		}
		</script>
	</head>
	
<body >
	
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iTutorViewHeader" width="100%" border="0">
					<tr>
						<td width="10%" >Denuncia</td>
						<td width="70%" >&nbsp;</td>
						<td width="30%" align="right">Expediente: <div id="divIdExpediente">XXXXXXXXX</div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iTutorWorkSheet" width="100%"  border="0">
					<tr valign="top">
						<td>
							<table>
								<tr>
									<td>
										<img alt="foto" src="<%= request.getContextPath() %>/resources/images/foto.png" id="iTutorCmpFoto">
									</td>
									<td>
										
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										Servidor P&uacute;blico&nbsp;&nbsp;<input type="checkbox" value="false" id="iTutorCmpServidorPublico"/>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table width="100%" border="0">
								<tr>
									<td>
										<input type="radio" name="iTutorCmpVivo" id="iTutorCmpVivo" value="V" checked="checked" >
									</td>
									<td align="left">Vivo</td>
									<td align="right">
									</td>
								</tr>
								<tr>
									<td>
										<input type="radio" name="iTutorCmpVivo" id="iTutorCmpDesconocido" value="D">
									</td>
									<td align="left">Desconocido</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
									</td>
									<td align="left">Se cuenta con tutor 
										<input type="radio" name="iTutorCmp" id="iTutorCmpTutorSi" value="1" checked="checked">Si
										<input type="radio" name="iTutorCmp" id="iTutorCmpTutorNo" value="0">No
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
						<td>
							<table  bgcolor="#FFFFFF" width="30%" height="143" border="0" cellpadding="0"  cellspacing="0" class="celda2">
		                        <tr>
		                          <td width="32%" height="25" align="center">CALIDAD</td>
		                          <td width="29%" height="25" align="center">TUTOR</td>
		                        </tr>
		                        <tr>
		                          <td width="32%" height="30">Nombre:</td>
		                          <td width="29%"><input name="nombre" title="Escribir nombre" type="text" size="40" id="nombProResponsable"  style="background:#DDD" readonly="readonly"/></td>
		                        </tr>
		                        <tr>
		                          <td width="32%" height="28">Apellido Paterno:</td>
		                          <td width="29%" height="28"><input name="aPaterno" title="Escribir apellido paterno" type="text" size="40" id="apPatProbResponsable" style="background:#DDD" readonly="readonly"/></td>
		                        </tr>
		                        <tr>
		                          <td width="32%"  height="35">Apellido Materno:</td>
		                        <td height="35"><input name="aMaterno" title="Escribir apellido materno" type="text" size="40" id="apMatProbResponsable" style="background:#DDD" readonly="readonly"/></td>
		                        </tr>
		                        <tr>
		                          <td height="25" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                        </tr>
		                      </table>
						</td>
					</tr>
					<tr valign="top">
						<td colspan="3">
							<table width="100%" >
								<tr valign="top">
									<td align="center">
										<input type="button" value="Guardar" id="iTutorBtnGuardar" class="btn_Generico"/>
										<input type="button" value="Modificar Datos" id="iTutorBtnModificarDatos" class="btn_Generico"/>
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
							<div id="iTutorAccordionPane" style="width: 100%" >
					            <dl>
					                <dt>Datos Generales</dt>
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
					                <dt id="servidorPublicoTab">Servidor Público</dt>
					                <dd id="servidorPublicoTabCont">
					                	<jsp:include page="ingresarIndividuoServidorPublicoView.jsp"/>
					                </dd>
					            </dl>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<div id="iTutorAccordionDialogoMenorEdad" title="Menor de edad">
	<table>
		<tr>
			<td>Denuncia</td>
			<td>&nbsp;</td>
			<td>Expediente</td>
		</tr>
		<tr>
			<td>Calidad:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iTutorDialogoCmpCalidad"/></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iTutorDialogoCmpNombre"/></td>
		</tr>
	</table>
</div>
</body>
</html>