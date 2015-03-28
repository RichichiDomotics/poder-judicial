<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ingresar interesado</title>

		<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#iProbResponsablePane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 6px;
				WIDTH: 1000px;
				PADDING-RIGHT: 0px;
				HEIGHT: 462px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#iProbResponsablePane DL {
				WIDTH: 1000px; HEIGHT: 400px
			}
			/*acordeon editar*/
			#iProbResponsablePane DT {
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
			#iProbResponsablePane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#iProbResponsablePane DT.hover {
				COLOR: #f5f5f5
			}
			#iProbResponsablePane DT.hover.active {
				COLOR: #f5f5f5
			}
			#iProbResponsablePane DD {
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
			#iProbResponsablePane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#iProbResponsablePane .active .slide-number {
				COLOR: #fff
			}
			#iProbResponsablePane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#iProbResponsablePane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#iProbResponsablePane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#iProbResponsablePane .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
		</style>
		
	<!-- Hojas de estilo -->	
    <!--	Hoja de estilo para los gadgets-->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
    
	<!--    Hoja de estilo para easyaccordion-->
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
 	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
 	<!--Hoja de estilo para el texto dentro de los acordeones-->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
    <!--Hoja de estilo para los popups-->
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
        
	<!--scripts -->
	
	<!--Scripts necesarios para el funcionamiento de la JSP-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
    <!--Scrip para el idioma del calendario-->
    <script type="text/javascript"  src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
    
    <!--Scripts necesarios para la ejecucion del editor-->
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
    <!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
    <script type="text/javascript">

		/*Variables globales de la pagina*/
		//guarda el numero unico del expediente
		var numeroExpedienteCadena="";
		//guarda el numero de expediente Id
		var numeroExpedienteId="";
		//utilizada en la pagina de ingresar datos generales para especificar si se muestra el alias o no
		var verAlias;
		//Utilizado para al inicio de la pagina guardar un nuevo individuo y despues modificar ese mismo individuo
		var idIndividuo=null;

		var idindi; 

		var solicitudEnvioId=null;
		//inicia onready
		jQuery().ready(	function () {
			//oculta boton de generar acta
			$('#generarActa').hide();
			//asocia funcion a boton de generar acta
			$('#generarActa').click(generaOficioActa);
			
			//captura el parametro enviado de la jsp padre asignandolo a su variable 
			numeroExpedienteId='<%= request.getParameter("numeroExpedienteId")%>';
			//captura el parametro enviado de la jsp padre asignandolo a su variable
			numeroExpedienteCadena='<%= request.getParameter("numeroExpedienteCadena")%>';	
			//captura el parametro enviado de la jsp padre asignandolo a su variable
			expedienteId='<%= request.getParameter("expedienteId")%>';	

			solicitudEnvioId = '<%= request.getParameter("solicitudId")%>';
			//se generan las tabs del domicilio
			$("#tabs").tabs();
			//Se crean las tabs principales
			$("#tabsprincipalconsulta").tabs();
			//ocultamos el domicilio de notificaciones
			killDomicilioNotificaciones();
			killCoordenadasGeograficas();
			//se genera el acordeon
			$('#iProbResponsablePane').easyAccordion({ 
			  autoStart: false, 
			  slideInterval: 3000
			});
			//Guarda el solicitante
			$("#btnGuardar").click(confirmGuardado);
			//carga catalogo de asesoria
			cargaCatAsesoria();

			if(solicitudEnvioId != null && solicitudEnvioId != ""){
				$.ajax({
				    type: 'POST', 
				    data: "", 
				    dataType: 'xml',
				    url: '<%= request.getContextPath()%>/obtenerDetalleDefensoria.do?idDocumento='+solicitudEnvioId+'&tipoDocumento=5',
					success: function(xml){
						$(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO").each(function(){
							var tipoAsesoria=$(xml).find("solicitud").find('tipoAsesoria').find('idCampo').text();
							$('#tipoAsesoria').find("option[value='"+tipoAsesoria+"']").attr("selected","selected");

							pintaDatosGenerales(xml);
							pintaDatosDomicilio(xml);
							idindi = $(xml).find("solicitud").find("expedienteDTO").find("involucradosDTO").find("involucradoDTO").find("elementoId").text();
							mediosContactoTelefonoActualiza();
							mediosContactoCorreoActualiza();
							$('#editor1').val($(xml).find("solicitud").find("motivo").text());
						});
					}
				});
			}
				
			//TERMINA function On Ready
  		});

		function confirmGuardado(){
			$("#divAlertTexto").html("¿Desea guardar la información capturada?");
			$( "#dialog-Alert" ).dialog({
				autoOpen: true,
				resizable: false,
				modal: true,
				buttons: {
					"Aceptar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					$("#divAlertTexto").html("");
					guardaSolicitante();
					},
					"Cancelar": function() {
					$( this ).dialog( "close" );
					$("#dialog:ui-dialog").dialog( "destroy" );
					$("#divAlertTexto").html("");
		
					}
				}
			});

		}
		
		//guarda el solicitante
		function guardaSolicitante(){
			//id del expediente
		    var parametros = 'idExpediente='+expedienteId;
		    parametros += '&idNumeroExpediente='+numeroExpedienteId;
			//extraemos la descripcion del hecho
			parametros +="&motivoAsesoria="+escape($('#editor1').val());
			//Datos generales
			parametros += obtenerParametrosDatosGenerales();
			//recuperamos los datos de lugar
			parametros += obtenerParametrosDomicilio();
			//Datos nacimiento
			parametros += obtenerParametrosDatosNacimiento();
			//Medios de contacto
			parametros += obtenerMedios();
			//modificar Individuo
			parametros += '&idIndividuo='+ idIndividuo;
			//tipoAsesoria
			var tipoAsesoria = $("#tipoAsesoria option:selected");
			parametros += '&idTipoAsesoria='+ tipoAsesoria.val();
			
			if(tipoAsesoria.val()==-1){
				alert("Por favor Selecciona un tipo de asesoría");
				}else{
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%= request.getContextPath()%>/guardaSolicitanteAsesoria.do',
				    	  data: parametros,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    			 alert("Se guardó con éxito");						 
				 				idIndividuo = $(xml).find("long").text();
				 				$("#generarActa").show();
			 					    		
				    	  }
				    	});
					}			
				}

		//consulta los documentos asociados a la solicitud
		function documentos(){
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDocumentosDefensoria.do?tipo=1&idExpedienteop='+numeroExpedienteId, 
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pager1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: false,
				width:800,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					consultaPDF(id);
					},
				sortorder: "desc"
			});

			jQuery("#gridDetalleFrmPrincipal").trigger('reloadGrid');
				
		}

		function consultaPDF(id){
			document.formaArchivoD.documentoId.value = id;
			document.formaArchivoD.submit();
		}

		function generaOficioActa(){
			actualizarEstatusSolicitudAsesoria();
			$("#generarActa").attr("disabled","disabled");
			document.formaDocDirecto.numeroUnicoExpediente.value = numeroExpedienteCadena;
			document.formaDocDirecto.submit();
		}

		function actualizarEstatusSolicitudAsesoria(){
			var documentoId1 = parent.documentoId;
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/atualizarEstatusSolicitudDefensoria.do?solicitudId='+documentoId1+'',
				dataType: 'xml',
				async: false,
				success: function(xml){
				}
			});
			alert("La solicitud de asesoria se actualizó con éxito.");
		}
		
		function imprimeDatosPadre(nombre,apellidoP,apellidoM){

			}

		//Funcion que carga los combos de tipo de asesoria
		function cargaCatAsesoria() {
		  $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/cargarCatalogoTipoAsesoria.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  success: function(xml){
	    		  $('#tipoAsesoria').empty();
		    	  $('#tipoAsesoria').append('<option value="-1">- Seleccione -</option>');
	    		  $(xml).find('tipoAsesoria').each(function(){
					$('#tipoAsesoria').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					
				   });
	    	  }
	    	});
		}
		
    </script>
  </head>
<body>
	<table width="100%" >
		<tr>
			<td>
				Tipo de Asesoría:<select id="tipoAsesoria"><option></option></select>
			</td>
			<td align="right">
				 <input type="button" class="btn_Generico" value="Generar Acta" id="generarActa" /> 
				 <input type="button" class="btn_guardar" value="Guardar" id="btnGuardar" />
			</td>
		</tr>
	</table>
	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Datos del interesado</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Motivo de la asesoría</a>
			</li>
			<li class="tabTabsDocs"><a href="#tabsconsultaprincipal-3" onclick="documentos()">Documentos</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
              <!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
                <div id="iProbResponsablePane">
                  <dl>
                    <dt id="cejaDatosGenerales">Datos Generales</dt>
                      <dd>	
                        <jsp:include page="datosGeneralesView.jsp"/>
                      </dd>
                    <dt id="cejaDomicilio">Domicilio</dt>
                      <dd>
                        <jsp:include page="ingresarDomicilioView.jsp"/>
                      </dd>
                      <dt id="cejaMediosContacto">Medios de Contacto</dt>
                        <dd>
                          <jsp:include page="ingresarMediosContactoView.jsp"/>
                        </dd>
                      </dl>
                    </div>
		</div>
		<div id="tabsconsultaprincipal-2">
			<textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea>
		</div>
		<div id="tabsconsultaprincipal-3" class="tabTabsDocs">
			<table id="gridDetalleFrmPrincipal"></table>
			<div id="pager1"></div>
			<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" name="documentoId" />
				</form>
			<form name="frmDoc2" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
						<input type="hidden" name="documentoId" />
						<input type="hidden" name="numeroUnicoExpediente" />
				</form>
		</div>
    </div>
	<div id="dialog-Alert" style="display: none">
		<table>
			<tr>
				<td>
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>
	</div>
</body>
<form name="formaDocDirecto" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
	<input type="hidden" name="formaId" value="33" %>
	<input type="hidden" name="numeroUnicoExpediente" %>
</form>
  	
<form name="formaArchivoD" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="documentoId" />
</form>
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
		
		height:'250px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);
</script>
</html>