<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Quien Detuvo</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen"href="<%= request.getContextPath()%>/resources/css/estilos.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
		<!--<style type="text/css">
			dd p{line-height:120%}
			#iQuienDetuvoAccordionPane {width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iQuienDetuvoAccordionPane dl{width:1000px;height:355px}	
			#iQuienDetuvoAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iQuienDetuvoAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#333333 url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iQuienDetuvoAccordionPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%=request.getContextPath()%>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#E78F08}
			#iQuienDetuvoAccordionPane dt.hover{color:#E78F08;}
			#iQuienDetuvoAccordionPane dt.active.hover{color:#1C94C4}
			#iQuienDetuvoAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iQuienDetuvoAccordionPane .slide-number{color:#E78F08;left:10px;font-weight:bold}
			#iQuienDetuvoAccordionPane .active .slide-number{color:#fff;}
			#iQuienDetuvoAccordionPane a{color:#E78F08}
			#iQuienDetuvoAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iQuienDetuvoAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iQuienDetuvoAccordionPane .more{padding-top:10px;display:block}
		</style>-->
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

		<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>

	<style type="text/css">
DD P {
	LINE-HEIGHT: 120%
}

#iQuienDetuvoAccordionPane {
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

#iQuienDetuvoAccordionPane DL {
	WIDTH: 1000px;
	HEIGHT: 355px
}

/*acordeon editar*/
#iQuienDetuvoAccordionPane DT {
	TEXT-ALIGN: right;
	PADDING-BOTTOM: 16px;
	PADDING-TOP: 2px;
	PADDING-LEFT: 0px;
	LINE-HEIGHT: 35px;
	TEXT-TRANSFORM: none;
	/*acomodo texto*/
	PADDING-RIGHT: 40px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	LETTER-SPACING: 1px;
	/*distancia persianas*/
	HEIGHT: 25px;
	COLOR: #f5f5f5;
	FONT-SIZE: 12px;
	FONT-WEIGHT: normal;
	background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
	background-repeat: no-repeat;
	background-position: 28px;
}

#iQuienDetuvoAccordionPane DT.active {
	BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
	background-repeat: no-repeat;
	COLOR: #f5f5f5;
	CURSOR: pointer;
	background-position: 30px;
}

#iQuienDetuvoAccordionPane DT.hover {
	COLOR: #f5f5f5
}

#iQuienDetuvoAccordionPane DT.hover.active {
	COLOR: #f5f5f5
}

#iQuienDetuvoAccordionPane DD {
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
#iQuienDetuvoAccordionPane .slide-number {
	COLOR: #68889b;
	FONT-WEIGHT: bold;
	LEFT: 30px
}

#iQuienDetuvoAccordionPane .active .slide-number {
	COLOR: #fff
}

#iQuienDetuvoAccordionPane A {
	COLOR: #58595b;
	font-family: Arial, Helvetica, sans-serif;
}

#iQuienDetuvoAccordionPane DD IMG {
	MARGIN: 0px;
	FLOAT: right
}

#iQuienDetuvoAccordionPane H2 {
	MARGIN-TOP: 10px;
	FONT-SIZE: 2.5em
}

#iQuienDetuvoAccordionPane .more {
	DISPLAY: block;
	PADDING-TOP: 10px
}
</style>
	
	
		<script type="text/javascript">
		var idindi='<%= request.getParameter("elemento")%>';
		var deshabilitarCampos = window.parent.deshabilitarCamposPM;
		var numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
		var numDetenidosAsociados=0;
		var numDetenidosTotal=0;
		var ArrayIndice = new Array();
		
			jQuery().ready(
				function () {
					if(numeroExpediente!=null && idindi==0){
						datosDetenidos();
					}
										
					$( "#tabs" ).tabs();
					
					$('#iQuienDetuvoAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});

					$("#iDetieneBtnGuardar").click(guardarQuienDetuvo);

					$("#iVictimaCmpServidorPublico").click(formaCapturaServidorPublico);
					
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);

					if(idindi!=0){
		                $("#iDetieneBtnModificar").show();
		                $("#cmbProbablesResponsablesDetenidos").attr("disabled","disabled");
		                $("#iDetieneBtnGuardar").hide();
						consulta();
					}
					else{
						$("#iDetieneBtnGuardar").show();
						$("#cmbProbablesResponsablesDetenidos").attr("disabled","");
		                $("#iDetieneBtnModificar").hide();
						inicializaDatosGenerales();
					}
					
					$("#cmbProbablesResponsablesDetenidos").multiselect();

					ocultaDomicilioNotificaciones();
					
					//Instrucción pensada solo para el caso de policía ministerial
					if(deshabilitarCampos == true){
						$(":enabled").attr('disabled','disabled');
					}					
			});

			//Asociamos la función que atiende el evento click del check de servidor público
			function formaCapturaServidorPublico() {
				if ($("#iVictimaCmpServidorPublico").is(':checked')) {
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",1);
				}else{
					habilitaDeshabilitaTabAcordeon1("servidorPublicoTab",0);
				}				
			}

			function consulta(){
				var i=0;
				$.ajax({
			    	  type: 'POST',
			    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
			    	  data: 'idInvolucrado='+idindi,
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
						  $(xml).find('involucradoDTO').find('relsDetencion').find('relacionDetencionDTO').each(function(){							  
			    					var numDetenido = $(this).find('idCmplemento').text();
			    					ArrayIndice[i]=numDetenido;
			    					i=i+1;
			    					numDetenidosAsociados++;			    					
			    		  });
						  
						  datosDetenidosCheck();
						  
			    		  pintaDatosGenerales(xml);
			    		  pintaDatosDomicilio(xml);
			    		  pintaDatosDomicilioNotif(xml);
			    		  pintaDatosTipoDocIdentificacion(xml);
			    		  pintaDatosMediaFiliacion(xml);
					  }
			    });

				desavilitarDatosGenerales();
				desavilitarDatosDomicilio();
				desavilitarDatosIdentificacion();
				mediosContactoCorreoActualiza();
				mediosContactoTelefonoActualiza();
				$('#iTestigoBtnModificarDatos').show();
				$('#iTestigoBtnGuardar').hide();
				$("#cbxTestigoProtegido").attr('disabled', 'disabled');							

			}

			/*
			* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
			* y un 0 para deshabilitar o un 1 para habilitar
			*/
			function habilitaDeshabilitaTabAcordeon1(idTabAcordeon,bandera)
			{
				if(parseInt(bandera)==0)
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
				else
				{
					$("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
					$("#"+idTabAcordeon).click(function(){		
						jQuery($("#"+idTabAcordeon)).activateSlide();
					});
				}
			}
			
				/*
				 *Imprime los datos que vienen de la funcion espejoDatos de datos generales, 
				 *en la pantalla ingresar representante legal
				 */
				function imprimeDatosPadre(nombre, apPat, apMat){
					document.getElementById('nombQuienDetuvo').value=nombre;
					document.getElementById('apPatQuienDetuvo').value=apPat;
					document.getElementById('apMatQuienDetuvo').value=apMat;
				}				

				function datosDetenidos() {
				       				   	  
			    	$.ajax({
			    		type: 'POST',
			    		url: '<%=request.getContextPath()%>/ConsultarIndividuosDetenidos.do',
			    		data: 'numeroExpediente='+numeroExpediente,
			    		dataType: 'xml',
			    		async: false,
			    		success: function(xml){
			    			$(xml).find('lstInvolucradosDetenidos').find('involucrado').each(function(){
			    				var numElemento =       $(this).find('elementoId').text();
			    				var nombreElemento =    $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').text();
			    				var apPaternoElemento = $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').text();
			    				var apMaternoElemento = $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').text() 
			    				$('#cmbProbablesResponsablesDetenidos').append('<option value="' + numElemento + '">'+ nombreElemento + " " + apPaternoElemento + " " + apMaternoElemento + '</option>');			    				
			    				numDetenidosTotal++;
			    			});
			    		}
			    	});

				}

				function datosDetenidosCheck() {
 				   	  
			    	$.ajax({
			    		type: 'POST',
			    		url: '<%=request.getContextPath()%>/ConsultarIndividuosDetenidos.do',
			    		data: 'numeroExpediente='+numeroExpediente,
			    		dataType: 'xml',
			    		async: false,
			    		success: function(xml){
			    			$(xml).find('lstInvolucradosDetenidos').find('involucrado').each(function(){

			    				var numElemento =       $(this).find('elementoId').text();
			    				var nombreElemento =    $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('nombre').text();
			    				var apPaternoElemento = $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoPaterno').text();
			    				var apMaternoElemento = $(this).find('nombresDemograficoDTO').find('nombreDemograficoDTO').find('apellidoMaterno').text() 
			    				var bandera=false;
			    				var i=0;
			    				while(i<numDetenidosAsociados && bandera==false){
			    					if(ArrayIndice[i]==numElemento){
			    						bandera=true;
			    					}
			    					else{
			    						i++;
			    					}
			    				}
			    				
			    				if(bandera==true){
				    				$('#cmbProbablesResponsablesDetenidos').append('<option selected="selected" value="' + numElemento + '">'+ nombreElemento + " " + apPaternoElemento + " " + apMaternoElemento + '</option>');
			    				}
			    				else{
			    					$('#cmbProbablesResponsablesDetenidos').append('<option value="' + numElemento + '">'+ nombreElemento + " " + apPaternoElemento + " " + apMaternoElemento + '</option>');
			    				}
			    				numDetenidosTotal++;
			    			});
			    		}
			    	});

				}
				// Función que guarda los datos de la pantalla
				function guardarQuienDetuvo(){
					
					var detenidos="";
					
					for(i=0;i<numDetenidosTotal;i++){
						if($("#ui-multiselect-cmbProbablesResponsablesDetenidos-option-"+i).is(':checked')==true){
							if(detenidos==""){
								detenidos+=$("#ui-multiselect-cmbProbablesResponsablesDetenidos-option-"+i).val();
							}
							else{
								detenidos+=","+$("#ui-multiselect-cmbProbablesResponsablesDetenidos-option-"+i).val();
							}
						}
					}
					
					var verificar = $('#cmbProbablesResponsablesDetenidos option:selected').val();
					var validaRFC_CURP = camposGeneralesValidos();
										
					if(validaRFC_CURP==1){
						var nombreGeneralOP=$('#datosGeneralesCmpNombres').val();
						if(verificar>=0){
							if(nombreGeneralOP!=""){
								var params = '';
								params += '&calidadDelIndividuo=8';
								params += '&idIndividuo='+ idindi;
								params += '&numeroExpediente='+ numeroExpediente;
								params += '&strDetenidos=' + detenidos;
								params += '&esServidorPublico=' + $('#iVictimaCmpServidorPublico').is(':checked');
						
								//Datos generales, media filiacion, medios de contacto, documentos de identificacion
								var datosPestania = obtenerParametrosDatosGenerales();
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
							
								//Servidor publico:  obtenerDatosServidorPublico();
						
								$.ajax({								
							    	type: 'POST',
					    	  		url: '<%= request.getContextPath()%>/guardarIndividuo.do',
					    	  		data: params,				
					    	  		dataType: 'xml',
					    	  		success: function(xml){
					    		  		idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
					    		  		if(idindi!=0){
								  			alertDinamicoCerrar("Se almacenó la información de forma correcta",xml);
					    		  		}
					    		  		else{
					    		  			alertDinamico('Ocurrió un error al almacenar la información, involucrado no guardado');
					    		  		}
					    	  		}
					    		});
							}else{
								alertDinamico('Favor de capturar el nombre del involucrado');
							}
						}else{
							alertDinamico("No se ha seleccionado un detenido");
						}
					}
					else if(validaRFC_CURP==0){
						alertDinamico("Favor de verificar que el CURP ingresado sea correcto");
					}
					else{
						alertDinamico("Favor de verificar que el RFC ingresado sea correcto");
					}
				}
								
				//Función para alertDinamicoCerrar
				function alertDinamicoCerrar(textoAlert,xml){
					$("#divAlertTextoCerrar").html(textoAlert);
				    $( "#dialog-AlertCerrar" ).dialog({
						autoOpen: true,
						resizable: false,
						modal: true,
						buttons: {											
							"Aceptar": function() {
								$( this ).dialog( "close" );
								$("#divAlertTextoCerrar").html("");
			    		  		window.parent.cargaQuienDetuvo($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
							}				
						}
					});    
				 }

				function habilitaDatos(){
					$('#cmbProbablesResponsablesDetenidos').attr("disabled","");
					avilitarDatosGenerales();
					avilitarDatosDomicilio();
					avilitarDatosIdentificacion();
					$('#iDetieneBtnGuardar').show();
					$('#iDetieneBtnModificar').hide();
					
					var $widget = $("#cmbProbablesResponsablesDetenidos").multiselect(),state = true;
					state = !state;
					$widget.multiselect(state ? 'disable' : 'enable');
				}
				
		</script>
	</head>
<body>

	<!-- div para el alert dinamico antes de cerrar ventana -->
	<div id="dialog-AlertCerrar" style="display: none">
		<table align="center">
			<tr>
        		<td align="center">
            		<span id="divAlertTextoCerrar"></span>
            	</td>
        	</tr>
    	 </table>              
	</div>    

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
				<table id="iVictimaWorkSheet" width="100%"  border="0">
					<tr valign="top">
						<td width="50%">
							<table>
								<tr>
									<td>
										<img alt="foto" src="<%= request.getContextPath() %>/resources/images/foto.png" id="iVictimaCmpFoto">
									</td>
									<td>
										
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										Servidor P&uacute;blico&nbsp;&nbsp;<input type="checkbox" value="false" id="iVictimaCmpServidorPublico"/>
									</td>
								</tr>
							</table>
						</td>
						<td width="30%">
							<table width="100%" border="0">
								<tr>
									<td rowspan="2" valign="top" align="right">Detenidos:</td>
									<td rowspan="2"><select id="cmbProbablesResponsablesDetenidos" 
										name="cmbProbablesResponsablesDetenidos" multiple="multiple" style="width: 180px;">
										
										</select></td>
								</tr>
							</table>
						</td>
						<td width="20%">
							<table width="100%" style="background:#DDD;border: 0;">
								<!-- tr>
									<td width="32%" height="25" align="right">CALIDAD</td>
		                            <td width="29%" height="25" align="center">TRADUCTOR-INTÉRPRETE</td>
								</tr-->
								<tr>
									<td align="right">
										Nombre:
									</td>
									<td align="left">
										<input type="text" size="40" maxlength="40" id="nombQuienDetuvo" style="background:#DDD;border: 0;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Paterno:
									</td>
									<td align="left">
										<input type="text" size="40" maxlength="40" id="apPatQuienDetuvo" style="background:#DDD;border: 0;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										Apellido Materno:
									</td>
									<td align="left">
										<input type="text" size="40" maxlength="40" id="apMatQuienDetuvo" style="background:#DDD;border: 0;" readonly="readonly"/>
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
										<input type="button" value="Modificar Datos" id="iDetieneBtnModificar" class="btn_Generico"  onclick="habilitaDatos()"/>
										<input type="button" value="Guardar" id="iDetieneBtnGuardar" class="btn_Generico"/>
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
							<div id="iQuienDetuvoAccordionPane" style="width: 100%" >
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
					                <dt>Documentos de Identificación</dt>
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