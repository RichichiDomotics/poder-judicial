<!-- 
CASO DE USO
Solicitar Investigación pericial

El sistema muestra el visor de elementos con las siguientes secciones de la pantalla: 
   - Solicitante  - Datos del usuario
   - Para quien solicita - Datos de la solicitud
   - Motivo  - Motivo de la solicitud
   - Avisar a Funcionario - Coordinador de Periciales

 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar Investigación Pericial</title>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>	
	
	<script type="text/javascript">
	
	numeroExpedienteId = <%=request.getParameter("numeroExpedienteId")%>;
	numeroExpediente = '<%=request.getParameter("numeroExpediente")%>';
	numeroCaso = '<%=request.getParameter("numeroCaso")%>';
	tipoServicio = '<%=TiposSolicitudes.ASESORIA.getValorId()%>';
	documentoId = 0;
	$(document).ready(function() {

		
		//verifica si el numero de expediente es nulo
		verificaExpediente(numeroExpedienteId);
		
		$("#tabsPrincipal").tabs();
		$("#guardarNarraTiva").css("display", "none");
		
		$("#solInvPericialNumExpediente").val(numeroExpediente);
		$("#solInvPericialNumeroCaso").val(numeroCaso);
		$("#btnGuardar").click(guardarSolicitud);
		$("#btnEnviarSolicitud").click(enviarSolicitud);
		
		
		
		//Agregar estilo del date picker para seleccionar anio y mes
		$("#solInvPericialFechaLimite").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		//Carga los datos del usuario firmado
		cargaDatosDelUsuario();
		cargaPruebasPericial();
		cargaFechaCaptura();
		consultaFuncionario();
	});
	
	/**
	* Carga el funcionario a mostrar en la tab de Avisar a Funcionario según el tipo de servicio solicitado
	*/
	function consultaFuncionario(){
		
		
		
		
			
				
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarFuncionarioANotificar.do',
	    		data: 'tipoSolicitud='+tipoServicio,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){

	    			var errorCode;
					errorCode=$(xml).find('response').find('code').text();				
					if(parseInt(errorCode)==0){	
						$('#solInvPericialNombreFuncionario').val($(xml).find('nombreFuncionario').first().text() + " " +
								$(xml).find('apellidoPaternoFuncionario').first().text() + " "+
								$(xml).find('apellidoMaternoFuncionario').first().text());
	    			}
					else{
						//Mostrar mensaje de error
					}
	    		}
	    	});
		
		
	}

	/*
	*Funcion que verifica si ya se cuenta con un numero de expediente o sera ingresado
	*por el usuario
	*/
	function verificaExpediente(idExpediente){

		if(idExpediente==null || idExpediente==""){
			$('#solInvPericialNumExpediente').hide();
			$('#solInvPericialNumExpedienteIngresado').show();
		}
		else{
			$('#solInvPericialNumExpediente').show();
			$('#solInvPericialNumExpediente').val(idExpediente);
			$('#solInvPericialNumExpedienteIngresado').hide();

		}

	}
	
	/*Funcion que dispara el Action para consultar los datos de usuario*/
    function cargaDatosDelUsuario(){
    	
    	
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDatosUsuario.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					$('#solInvPericialAreaAdmin').val( $(xml).find('usuarioDTO').find('area').find('nombre').first().text()); 		
					$('#solInvPericialNombre').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text());
					$('#solInvPericialPuesto').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
    }

    /*Funcion que dispara el Action para consultar Las especialidades Pericialess*/		
    function cargaPruebasPericial(){
    	
    	  	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarCatalogoEspecialidadPericial.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			
    			$(xml).find('catEspecialidadPericial').each(function(){
    				
    				$('#solPruebaPericialTipoServ').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			});
    		}
    	});
    }

	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solInvPericialFecha').val($(xml).find('fechaActual').text());
    		}
		});
    }

	/*Funcion que cierra la ventana*/
	function cerrarVentana(){
		parent.cerrarVentana();
	}
    
	function guardarSolicitud(){
		
		especialidad = $("#solPruebaPericialTipoServ option:selected").val();
		fechaLimite =  $("#solInvPericialFechaLimite").val();
		motivo = escape($(".jquery_ckeditor").val());
		envio = "false";
		
		$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/guardarSolicitudServicioPericial.do',
    	    data: 'numeroExpedienteId='+numeroExpedienteId+'&documentoId='+documentoId+'&tipoSolicitud='+tipoServicio+'&especialidad='+especialidad+
    	    '&fechaLimite='+fechaLimite+'&motivo='+escape(motivo)+'&envio='+envio,
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			documentoId = $(xml).find('documentoId').text();
    			
    		}
		});
		
		
	}
	
	function enviarSolicitud(){
		
		especialidad = $("#solPruebaPericialTipoServ option:selected").val();
		fechaLimite =  $("#solInvPericialFechaLimite").val();
		motivo = escape($(".jquery_ckeditor").val());
		envio = "true";
		
		$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/guardarSolicitudServicioPericial.do',
    	    data: 'numeroExpedienteId='+numeroExpedienteId+'&documentoId='+documentoId+'&tipoSolicitud='+tipoServicio+'&especialidad='+especialidad+
    	    '&fechaLimite='+fechaLimite+'&motivo='+escape(motivo)+'&envio='+envio,
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			documentoId = $(xml).find('documentoId').text();
    			this.close();
    		}
		});
		
		
	}
	
    </script>
</head>
<body>
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Solicitante</a></li>
					<li><a href="#tabsconsultaprincipal-2">Para Quien Solicita</a></li>
					<li><a href="#tabsconsultaprincipal-3">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-4">Avisar a Funcionario</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre del servidor P&uacute;blico:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialNombre" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right" >
								Cargo:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialPuesto" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								&Aacute;rea Administrativa:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha de Solicitud:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialFecha" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solInvPericialFechaLimite" width="50px" />
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-2">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Número de Expediente:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialNumExpediente" disabled="disabled"/>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialNumExpedienteIngresado"/>
							</td>
						</tr>
						<tr>
							<td>
								Número de Caso:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solInvPericialNumeroCaso" disabled="disabled"/>
								
							</td>
						</tr>
						<tr>
							<td>
								Prueba Pericial:
							</td>
							<td>
								<select id="solPruebaPericialTipoServ" style="width: 180px;">
									<option value="seleccione">- Seleccione -</option>
									
								</select>
							</td>
						</tr>
						
						
						
					</table>
				</div>
				<div id="tabsconsultaprincipal-3">
					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
					
				</div>
				<div id="tabsconsultaprincipal-4">
					<table width="100%" border="0" height="90%">
				        <tr>
				            <td>
				                Nombre Funcionario:<input type="text" class="" size="50" maxlength="50" id="solInvPericialNombreFuncionario" disabled="disabled"/>
				            </td>
				            <td align="left">
				            	
				            </td>
				        </tr>
				        <tr>
				            <td>
				                
				            </td>
				        </tr>
				        <tr>
				            <td colspan="2" align="right">
				                
				            </td>
				        </tr>
				    </table>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="5">
			<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="btnEnviarSolicitud" value="Enviar Solicitud" class="btn_Generico"/>
		</td>
	</tr>
</table>
</body>
</html>