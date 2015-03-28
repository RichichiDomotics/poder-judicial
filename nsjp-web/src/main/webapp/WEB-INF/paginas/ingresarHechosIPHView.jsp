<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Hechos</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />	
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />			
		<style type="text/css">
			dd p{line-height:120%}
			#iHechosAccordionPane {width:1000px;height:385px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iHechosAccordionPane dl{width:1000px;height:385px}	
			#iHechosAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iHechosAccordionPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#68889b}
			#iHechosAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iHechosAccordionPane dt.hover{color:#E78F08;}
			#iHechosAccordionPane dt.active.hover{color:#1C94C4}
			#iHechosAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iHechosAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iHechosAccordionPane .active .slide-number{color:#fff;}
			#iHechosAccordionPane a{color:#68889b}
			#iHechosAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iHechosAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iHechosAccordionPane .more{padding-top:10px;display:block}
		</style>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		
									<!--Scripts necesarios para la ejecucion del editor-->
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	   
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	   
		<script type="text/javascript">
			var idWindowIngresarNarrativa = 1;
			var numeroExpediente="";
			var idUsuario="";
			var tipoTiempoHecho=1;//1 para definido,2 para lapso y 3 para descripcion hecho en el tiempo
			var calidad="";
			var idHecho=";"
			var banderaConsultaHecho=0;
			var idLugar;
			
			jQuery().ready(			
				function () {
					
					$("#tabsHechos").tabs();
					numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
					calidad='<%= request.getParameter("idCalidad")%>';
					idHecho='<%= request.getParameter("idHecho")%>';
					idLugar='<%= request.getParameter("idLugar")%>';
					idUsuario="0000";
					$("#idFechaDate").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '1900:2100',
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#idFechaDateLapso").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '1900:2100',
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#idFechaDateLapso2").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '1900:2100',
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#btnGuardarHechos").click(guardaHecho);
					$("#btnModificarHechos").click(modificarHecho);
					$("#btnGuardarHechos").show();
					$("#btnModificarHechos").hide();
					//se crean los tabs y el acordeon
					/*$('#iHechosAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});*/
					//asginamos los listener de los botones de fecha y hora del hecho
					$("#ingresarTiempoLapsoPResponsable").click(cambiaLapso);
					$("#ingresarTiempoEspecificamentePResponsable").click(cambiaEspecifico);
					$("#ingresarTiempoOtroPResponsable").click(creaNuevaNarrativa);
					
					killDomicilioNotificaciones();
					
					
					if(idHecho!=null && parseInt(idHecho)!=0)
					{
						$("#btnGuardarHechos").hide();
						$("#btnModificarHechos").show();
						//consultamos el Hecho
						consultaHecho();
					}

					$('#idFechaDateLapso').change(validaCamposFecha);
					$('#idFechaDateLapso2').change(validaCamposFecha);
					
					//var isIPH = ${param.iphFuncionalidadHidden};
					var isIPH = '<%=request.getParameter("iphFuncionalidadHidden")%>' == '' ? false : <%=request.getParameter("iphFuncionalidadHidden")%>;
					if (isIPH) {
						$("#IPHFuncionalidadOcultaTipoExpediente").hide();					
					}
				});
			
			function consultaHecho()
			{
				$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
		    		data: 'idNumeroExpediente='+numeroExpediente,
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			if(parseInt($(xml).find('code').text())==0)
			    		{
		    				//seteamos la informacion del hecho
		    				 $(xml).find('hechoDTO').find('tiempo').find('fechaInicio').each(function(){
				    	    	  $("#ingresarTiempoEspecificamentePResponsable").click();
				    	  	    	seteaDatosTiempoEspecifico(xml);
				    	  	    	bloqueaCamposTiempoEspecifico(0);
				    	  	    	tipoTiempoHecho=1;
				    	      });
		    				  
				    	      $(xml).find('hechoDTO').find('tiempo').find('fechaFin').each(function(){
				    	    	  $("#ingresarTiempoLapsoPResponsable").click();
			    	  	    	  seteaDatosTiempoLapso(xml);
			    	  	    	  bloqueaCamposTiempoLapso(0);
			    	  	    	  tipoTiempoHecho=2;
				    	      });
				    	      
				    	      $(xml).find('hechoDTO').find('tiempo').find('descripcion').each(function(){
				    	    	  $("#ingresarTiempoOtroPResponsable").click();
				    	    	  $("#textNarrativa").val($(xml).find('hechoDTO').find('tiempo').find('descripcion').text());
				    	    	  bloqueaCamposTiempoOtro(0);
			    	  	    	  tipoTiempoHecho=3;
				    	      });
				    	      
				    	      habilitaDeshabilitaBotonesTiempo(0);
				    	      
				    	      $(xml).find('hechoDTO').each(function(){
				    	    	  //seteamos la informacion del lugar
						    	      seteaLugar(xml);
					    	      $("#btnGuardarHechos").hide();
					    	      $("#btnModificarHechos").show();
				    	    	  //seteamos el valor de la descripcion del hecho
				    	    	  $('.jquery_ckeditor').val($(this).find('descNarrativa').text());
				    	    	  CKEDITOR.on("instanceReady", function (ev) {
				    	  	        var bodyelement = ev.editor.document.$.body;
				    	  	        bodyelement.setAttribute("contenteditable", false);
				    	  	    });
				    	  	    CKEDITOR.replace('editor1');
				    	    	  banderaConsultaHecho=1;
				    	      });
			    		}
		    		}	
		    	});
			}
			
			function bloqueaCamposTiempoOtro(bandera)
		     {
		    	if(parseInt(bandera)==0)
		    	{
		    		$("#textNarrativa").attr("readonly","readonly");
		    	}
		    	else
		    	{
		    		$("#textNarrativa").attr("readonly","");
		    	}
		     }
			
			function habilitaDeshabilitaBotonesTiempo(bandera)
			{
				if(parseInt(bandera)==1)
				{
					$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","");
					$("#ingresarTiempoLapsoPResponsable").attr("disabled","");
					$("#ingresarTiempoOtroPResponsable").attr("disabled","");
				}
				else
				{
					$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","disabled");
					$("#ingresarTiempoLapsoPResponsable").attr("disabled","disabled");
					$("#ingresarTiempoOtroPResponsable").attr("disabled","disabled");
				}
			}
			
			function seteaDatosLugar(xml)
			{
				
				/*
				<calle>av de pachuca</calle>
			    <numeroExterior>12</numeroExterior>
			    <numeroInterior>23</numeroInterior>
			    <entreCalle1>el viento</entreCalle1>
			    <entreCalle2>el huracan</entreCalle2>
			    <alias>La bella airosa alias</alias>
			    <edificio>Estadio Pachuca</edificio>
			    <valorCalleId>
			      <idCampo>76</idCampo>
			      <valor>Carretera</valor>
			    </valorCalleId>
			    
			    
			    
			    <hechoId>18</hechoId>
			    <tiempo>
			      <tipoRegistro>
			        <idCampo>1770</idCampo>
			      </tipoRegistro>
			      <fechaInicio class="sql-timestamp">2011-07-01 07:00:00.0</fechaInicio>
			      <fechaFin class="sql-timestamp">2011-07-02 08:00:00.0</fechaFin>
			    </tiempo>
			    <expediente>
			      <expedienteId>617</expedienteId>
			      <elementosDTO/>
			      <involucradosDTO/>
			      <objetosDTO/>
			      <delitos/>
			      <documentosDTO/>
			      <consulta>false</consulta>
			    </expediente>
			    <lugar class="mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO">
			      <elementoId>1091</elementoId>
			      <fechaCreacionElemento class="sql-timestamp">2011-07-04 20:57:41.443</fechaCreacionElemento>
			      <inmediaciones/>
			      <calle>asdadas</calle>
			      <numeroExterior>434</numeroExterior>
			      <numeroInterior>23</numeroInterior>
			      <entreCalle1>asdasds</entreCalle1>
			      <entreCalle2>asdasdas</entreCalle2>
			      <alias>asdas</alias>
			      <edificio>asdasdas</edificio>
			      <valorCalleId>
			        <idCampo>76</idCampo>
			        <valor>Carretera</valor>
			      </valorCalleId>
			      <asentamientoDTO>
			        <asentamientoId>136</asentamientoId>
			        <codigoPostal>20135</codigoPostal>
			        <nombreAsentamiento>Agropecuario</nombreAsentamiento>
			        <ciudadDTO>
			          <ciudadId>1</ciudadId>
			          <nombreCiudad>Aguascalientes</nombreCiudad>
			          <entidadFederativaDTO>
			            <entidadFederativaId>1</entidadFederativaId>
			            <abreviacion>AGS </abreviacion>
			            <nombreEntidad>Aguascalientes</nombreEntidad>
			            <valorIdPais>
			              <idCampo>10</idCampo>
			              <valor>México</valor>
			            </valorIdPais>
			          </entidadFederativaDTO>
			        </ciudadDTO>
			        <municipioDTO>
			          <municipioId>1</municipioId>
			          <nombreMunicipio>Aguascalientes</nombreMunicipio>
			          <entidadFederativaDTO>
			            <entidadFederativaId>1</entidadFederativaId>
			            <abreviacion>AGS </abreviacion>
			            <nombreEntidad>Aguascalientes</nombreEntidad>
			            <valorIdPais>
			              <idCampo>10</idCampo>
			              <valor>México</valor>
			            </valorIdPais>
			          </entidadFederativaDTO>
			        </municipioDTO>
			      </asentamientoDTO>
			    </lugar>
			    <descNarrativa>&lt;p&gt;
			  	prueba para el partido 2 de mexico&lt;/p&gt;
			  </descNarrativa> 
			  </hechoDTO>*/
			}
			
			function cambiaEspecifico(){
				  if(!$("#divEspecifico").is(':visible')){
					$("#divLapso").css("display", "none");
					$("#divEspecifico").css("display", "block");  
					$("#divOtro").css("display", "none"); 
					tipoTiempoHecho=1;
				  }
				}
				
					
				function cambiaLapso(){
				  if(!$("#divLapso").is(':visible')){
					$("#divLapso").css("display", "block");
					$("#divEspecifico").css("display", "none");
					$("#divOtro").css("display", "none"); 
					tipoTiempoHecho=2;
				  }
				}
				
						
				function cambiaOtro(){
				  if(!$("#divLapso").is(':visible')||!("#divEspecifico").is(':visible')){
					$("#divLapso").css("display", "none");
					$("#divEspecifico").css("display", "none"); 
					$("#divOtro").css("display", "block");
					tipoTiempoHecho=3;
				  }
				}
				
				
				function creaNuevaNarrativa() {
					if(!$("#divEspecifico").is(':visible')){
						$("#divEspecifico").css("display", "none"); 
						$("#divLapso").css("display", "none");  	 
					}
					if(!$("#divLapso").is(':visible')){
						$("#divLapso").css("display", "none");
						$("#divEspecifico").css("display", "none");  
					}
					cambiaOtro();
				}
			/*
			*Funcion para mandar guardar el nuevo hecho en la BD
			*/
			function guardaHecho()
			{
				if(validaDatosIngresoHecho())
				{
					//guardare el hecho
					var parametrosHechos=extraeDatosIngresarHechos();//obtengo la información a almacenar
					//llamamos al action que guardara el nuevo Hecho
					$.ajax({
						async: false,
						type: 'POST',
						url: '<%= request.getContextPath()%>/ingresarHecho.do',
						data: parametrosHechos,
						dataType: 'xml',
						success: function(xml){
							if(parseInt(idHecho)==0)
							{
								//Venismo de una insercion
								if(parseInt($(xml).find('code').text())==0)
								{
									customAlert("Se guardó exitosamente el hecho");
									$("#btnGuardarHechos").attr("disabled","disabled");
									window.parent.cargaIngresoHecho("Hecho",''+$(xml).find('hechoDTO').find('hechoId').text());
								}
								else
								{
									customAlert("Ocurrió un error al guardar el hecho");
								}
							}
							else
							{
								//venimos de un update y para setear la pantalla mandaremos llamar a la consulta del hecho
								consultaHecho();
							}
						}
					});
				}
				else
				{
					customAlert("Hay un dato inválido, favor de verificar");
				}
			}
			
			function modificarHecho()
			{
				//habilitamos los campos de tiempo especifico 
				bloqueaCamposTiempoEspecifico(1);
				//habilitamos los campos de tiempo por lapso
				bloqueaCamposTiempoLapso(1);
				//habilita los campos de tiempo Otro
				bloqueaCamposTiempoOtro(1);
				//habilitamos los botones para seleccionar el tipo de tiempo
				habilitaDeshabilitaBotonesTiempo(1);
				//habilitamos todos los campos de lugar
				bloqueaAllCamposLugaresHecho(1);
				
			}
			
			
			/*
			*Funcion que validara los datos del nuevo hecho para poder ingresar el hecho a la BD
			*/
			function validaDatosIngresoHecho()
			{
				var banderaVal=true;
				//lamaremos a cada uno de los metodos que validan las secciones de la vista
				return banderaVal;	
			}
				
			/*
			*
			*/
			function extraeDatosIngresarHechos()
			{
				var parametros="";
				//llmaremos a cada uno de los metodos que estraen la informacion de cada una de 
				//las secciones de la vista para poder ingresar un nuevo Hecho
				
				//agregamos el Id del hecho
				parametros="idHecho="+idHecho;
				//extraemos la descripcion del hecho
				parametros +="&gcDescripcionHecho="+escape($('.jquery_ckeditor').val());
				//recuperamos los datos de lugar, ya trae el & para la union
				parametros += obtenerDatos();
				if(parseInt(tipoTiempoHecho)>0)
				{
					//seteamos el tiempo seleccionado
					parametros += "&tipoTiempoHecho="+tipoTiempoHecho;
				}
				//recuperamos los datos de fecha y tiempo
				if(parseInt(tipoTiempoHecho)==1)//especifico
				{
					parametros += "&"+recuperaDatosTiempoEspecifico(calidad);	
				}
				else if(parseInt(tipoTiempoHecho)==2)//lapso
				{
					parametros += "&"+recuperaDatosTiempoLapso(calidad);	
				}
				else if(parseInt(tipoTiempoHecho)==3)//hecho en el tiempo
				{
					parametros += "&gsNarrativa="+$("#textNarrativa").val();	
				}
				
				//regresamos la cadena con los datos del Hecho
				parametros += "&numExpediente=" + numeroExpediente;
				parametros += "&idUsuario=" + idUsuario;
				parametros += "&origenExpediente=" + $(':radio[name=rdTipoExpediente]:checked').val();
				return parametros;
			}
			
			/*
			 *Imprime los datos que vienen de la funcion espejoDatosNarrativa de texto narrativa
			 *en la pantalla ingresar probable responsable
			 */
			function imprimeDatosPadreNarrativa(textoNarrativa){
			  $("#textNarrativa").val(textoNarrativa);
			}
		</script>
	</head>
<body>
	<table width="100%" class="back_pleca_h">
		<tr>
			<td>&nbsp;
				<span id="IPHFuncionalidadOcultaTipoExpediente">
					Tipo de Expediente: 
					<input type="radio" id="rdbTipoDenuncia" value="0" name="rdTipoExpediente" checked="checked"/> Denuncia
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="rdbTipoQuerella" value="1" name="rdTipoExpediente"/> Querella
				</span>
			</td>
			<td align="right">
					 <input type="button" value="Modificar" id="btnModificarHechos" class="back_button"/>
					 <input type="button" value="Guardar" id="btnGuardarHechos" class="back_button"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<div id="tabsHechos"> 
					    <ul>
					        <li id="hechosIngHechosTab"><a href="#tabsHechos-1">Hechos</a></li>
					        <li id="lugarTab"><a href="#tabsHechos-2">Lugar donde ocurrieron los Hechos</a></li>
					        <li id="fechaHoraTab"><a href="#tabsHechos-3">Fecha y Hora de los Hechos</a></li>
					    </ul>
					    <div id="tabsHechos-1">
					    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
					    		<tr>
						    		<td align="center"><textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea></td>
						    	</tr>
						    </table>
					    </div>
					    <div id="tabsHechos-2">
					    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
			    				<tr valign="top">
			    					<td width="20px">&nbsp;</td>
			    					<td><br>
			    					<jsp:include page="/WEB-INF/paginas/ingresarLugarIPHView.jsp" flush="true">
			    						<jsp:param name="idLugar" value="<%=request.getParameter("idLugar")%>" />
			    					</jsp:include>			    					
			    					</td>			    					
			    				</tr>
			    			</table>
					    </div>
					    <div id="tabsHechos-3">
					    	<table id="datosDetenido" width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
		                        <tr valign="top">
		                          <td width="20px">&nbsp;</td>
		                          <td colspan="2" height="20" valign="middle" class="seccion"><br>Ingresar datos de detenci&oacute;n</td>
		                        </tr>
		                        <tr valign="top">
		                          <td width="20px">&nbsp;</td>
		                          <td align="left" height="18px">
		                              <input type="submit" id="ingresarTiempoEspecificamentePResponsable" value="Espec&iacute;ficamente" class="back_button"/>
		                          </td>
		                          <td width="55%" rowspan="3">
		                            <div id="divEspecifico" style="display: block;">
		                                <jsp:include page="/WEB-INF/paginas/ingresarTiempoEspecificamente.jsp" flush="true"></jsp:include>
		                            </div>
		                            <div id="divLapso" style="display: none;">
		                                <jsp:include page="/WEB-INF/paginas/ingresarTiempoLapso.jsp" flush="true"></jsp:include>
		                            </div>
		                            <div id="divOtro" style="display: none;">
		                            	Evento en el tiempo: <br/>
		                                &nbsp;&nbsp;&nbsp;<textarea rows="7" cols="70" id="textNarrativa" ></textarea>
		                            </div>						                		
		                          </td>
		                        </tr>
		                        <tr valign="top">
		                        <td width="20px">&nbsp;</td>
		                          <td align="left" height="18px">
		                              <input style="width:112px" type="submit" id="ingresarTiempoLapsoPResponsable" value="Lapso" class="back_button"/>
		                          </td>
		                        </tr>
		                        <tr valign="top">
		                        <td width="20px">&nbsp;</td>
		                          <td align="left" height="18px">
		                              <input style="width:112px" type="submit" id="ingresarTiempoOtroPResponsable" value="Otro" class="back_button"/>
		                          </td>
		                        </tr>
		                      </table>
					    </div>
					 </div>
</body>
<script type="text/javascript">
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
		],
		height:'300px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);
</script>
</html>