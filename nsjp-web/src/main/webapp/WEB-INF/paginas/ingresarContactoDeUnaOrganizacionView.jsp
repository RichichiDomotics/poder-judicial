<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar contacto de una organización</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen"href="<%= request.getContextPath()%>/resources/css/estilos.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
						
		<style type="text/css">
			dd p{line-height:120%}
			#iContacOrganizacionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iContacOrganizacionPane dl{width:1000px;height:355px}	
			#iContacOrganizacionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iContacOrganizacionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iContacOrganizacionPane dt.hover{color:#E78F08;}
			#iContacOrganizacionPane dt.active.hover{color:#1C94C4}
			#iContacOrganizacionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iContacOrganizacionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iContacOrganizacionPane .active .slide-number{color:#fff;}
			#iContacOrganizacionPane a{color:#68889b}
			#iContacOrganizacionPane dd img{float:right;margin:0 0 0 0px;}
			#iContacOrganizacionPane h2{font-size:2.5em;margin-top:10px}
			#iContacOrganizacionPane .more{padding-top:10px;display:block}
		</style>
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>		
		<script type="text/javascript">
		var verAlias=1;
		
		var numeroExpediente="";
		var idOrganizacion="";
		var idUsuario="";
		var idindi; 
			jQuery().ready(			
				function () {
					$( "#tabs" ).tabs();
					$( "#tabs2" ).tabs();
					$('#iContacOrganizacionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});

					//Codigo para obtener los datos de la pantalla
					$("#iVictimaBtnGuardar").click(guardarContacto);
					ocultaDomicilioNotificaciones();
					
					numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
					idOrganizacion='<%= request.getParameter("idOrganizacion")%>';
					idUsuario='<%= request.getParameter("idUsuario")%>';
	
					/*
					*Funcion que se utiliza para llamar al CU Consultar contacto de una organizacion
					*si el id del contacto ,es null, no se ejecuta la consulta
					*/				
					var id=<%= request.getAttribute("idInvolucrado")%>;					
					if(id!=null){
						muestraDatosContactoDeUnaOrganizacion(id);	
					}
				});

			//Imprime los datos que vienen de la funcion espejoDatos de datos generales
			//en la pantalla ingresar contacto de una organizacion
			function imprimeDatosPadre(nombre, apPat, apMat){
				document.getElementById('nombContactoOrg').value=nombre;
				document.getElementById('apPatContactoOrg').value=apPat;
				document.getElementById('apMatContactoOrg').value=apMat;
			}

			function guardarContacto(){
				var params = '';
				if(parseInt(idOrganizacion)!=0)
				{
					params += 'idIndividuo=0';
					params += '&calidadDelIndividuo=0';
					params += '&numExpediente='+numeroExpediente;
					params += '&idOrganizacion='+idOrganizacion;
					params += '&idUsuario='+idUsuario;
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
				    	  url: '<%= request.getContextPath()%>/guardarContactoOrg.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('code').text())==0)
				    		  {
				    			  //deshabilitamos el boton de guardado 
					    		  $("#iVictimaBtnGuardar").attr("disabled","disabled");
					    		  window.parent.deshabilitaBotonContacto();
				    			  customAlert("El contacto de la organización se guardó exitosamente");
				    		  }
				    		  else
				    		  {
				    			  customAlert("Ocurrió un error al guardar el contacto de la organización");
				    		  }
				    	  }
				    	});
				}
			}

/*
 *COMIENZAN FUNCIONES PARA EL CU CONSULTAR CONTACTO DE UNA ORGANIZACION
 */  
			/*
			 *Funcion que realiza la consulta de los datos del probable responsable
			 */
			function muestraDatosContactoDeUnaOrganizacion(id) {
			 
			  $.ajax({
			     type: 'POST',
			     url: '<%= request.getContextPath()%>/ConsultarIndividuoDatos.do',
				 data: 'idInvolucrado='+id,
				 dataType: 'xml',
				 success: function(xml){
					 //muestraNombres(xml);
					 pintaDatosGenerales(xml);
					 idindi=id;
						mediosContactoTelefonoActualiza();
						mediosContactoCorreoActualiza();
			    	 espejoDatos();
				  }
			   });
			 }

			/*
			 *Funcion que muestra los nombres del probable responsable, cuando es persona fisica
			 */	
			//function muestraNombres(xml){
				//$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').each(function(){
				//	$('#nombContactoOrg').val($(this).find('nombre').text());
				//	$('#apPatContactoOrg').val($(this).find('apellidoPaterno').text());
				//	$('#apMatContactoOrg').val($(this).find('apellidoMaterno').text());
			//	});
		//	}




				
		</script>
	</head>
<body>
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iContacOrganizacionHeader" width="100%" border="0">
					<tr>
						<td width="10%">DENUNCIA</td>
						<td width="70%">&nbsp;</td>
						<td width="30%" align="right">EXPEDIENTE</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iContacOrganizacionWorkSheet" width="100%"  border="1">
					<tr valign="top">
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
							        <td width="12%" height="130" align="center">
							            <img src="<%= request.getContextPath() %>/resources/images/foto.png" width="100" height="100" />
							        </td>
									<td width="12%" height="130" align="center" valign="middle">
										<!--<img src="<%= request.getContextPath() %>/resources/images/fingerPrint.JPG" width="100" height="100" />-->
							        </td>
								  <td width="12%" height="130" align="center" valign="top">
								  
								  </td>
									<td width="32%" align="center">&nbsp;</td>
									<td width="32%" height="130" align="center" valign="top" >
									  <table  style="background:#DDD;border: 0;" width="30%" height="143" cellpadding="0"  cellspacing="0" class="celda2">
							                <!-- tr>
							                  <td width="32%" height="25" align="center">CALIDAD</td>
							                  <td width="29%" height="25" align="center">CONTACTO DE ORGANIZACION</td>
							                </tr-->
							                <tr>
							                  <td width="32%" height="30">Nombre:</td>
							                  <td width="29%"><input name="nombre" title="Escribir nombre" type="text" size="40" id="nombContactoOrg" style="background:#DDD;border: 0;" readonly="readonly"/></td>
							                </tr>
							                <tr>
							                  <td width="32%" height="28">Apellido Paterno:</td>
							                  <td width="29%" height="28"><input name="aPaterno" title="Escribir apellido paterno" type="text" size="40" style="background:#DDD;border: 0;" id="apPatContactoOrg" readonly="readonly"/></td>
							                </tr>
							                <tr>
							                  <td width="32%"  height="35">Apellido Materno:</td>
							                <td height="35"><input name="aMaterno" title="Escribir apellido materno" type="text" size="40" id="apMatContactoOrg" style="background:#DDD;border: 0;" readonly="readonly"/></td>
							                </tr>
							                <tr>
							                  <td height="25" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							                </tr>
									  </table>
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
										<input type="button" value="Modificar Datos" id="iVictimaBtnModificarDatos" class="btn_Generico"/>
										<input type="button" value="Guardar" id="iVictimaBtnGuardar" class="btn_Generico"/>
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
							<div id="iContacOrganizacionPane">
					            <dl>
					                <dt>Datos Generales</dt>
					                <dd>
					                	<jsp:include page="datosGeneralesView.jsp"/>
									</dd>
					                <dt>Domicilio</dt>
					                <dd>
					                	<jsp:include page="ingresarDomicilioView.jsp"/>
						            </dd>
					                <!-- dt id="mediafiliacionTab">Media Filiación</dt>
					                <dd>
					                	<p> Media filiación</p>
					                </dd-->
					                <dt>Medios de Contacto</dt>
					                <dd>
					                	<jsp:include page="ingresarMediosContactoView.jsp"/>
					                </dd>
					                <dt>Documentos de Identificación </dt>
					                <dd>
										<jsp:include page="ingresarDocumentoIdentificacionView.jsp"/>
									</dd>
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