<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar Perito Externo</title>
	
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
	<script type="text/javascript">
	$(document).ready(function() {
		var solicitudId=<%= request.getParameter("solicitudPericialId")%>;
		
		//var solicitudId = '<%= request.getParameter("rowid")%>';
		$("#tabsPrincipal").tabs();
		$("#solServPericialConsCadenaCustodia").click(gridCadenaCustodia);
		$("#solServPericialAddCadenaCustodia").click(addCadenaRegistrada);
		$("#solServPericialFechaLimite").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#guardarNarraTiva").css("display", "none");
		$("#btnEnviarSolicitud").click(cerrarVentana);
		cargaFechaCaptura();
		cargaDatosDelUsuario();
		consultaDetalleSolicitudPericial(solicitudId);
	});

	function cerrarVentana(){
		parent.cerrarVentanaEvidencia();
	}

	/*
	 * Funcion que consulta el detalle de la solicitud pericial, para 
	 *obtener la fecha de solicitud y el estatus de la solicitud
	 */
	function consultaDetalleSolicitudPericial(solicitudPericialId){
		
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultaDetalleSolicitudPericial.do?solicitudPericialId='+solicitudPericialId+'',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
							
				if(parseInt(errorCode)==0){	
					 fechaSol = $(xml).find('fechaCreacion ').first().text();
					 var fechaPos1=fechaSol.indexOf(":", 0);
					 fechaReal=fechaSol.substring(0, fechaPos1-2);
					//Fecha de la solicitud
					 $('#fechaSolicitudDesignarPerito').val(fechaReal);
					
    			}
    		}
    	});
	
	
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

					//Nombre del funcionario
					$('#nombreFuncionarioDesignarPerito').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text()
						);
					//Puesto del funcionario
					$('#puestoFuncionarioDesignarPerito').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					//Area administrativa
					$('#areaFuncionarioDesignarPerito').val( $(xml).find('usuarioDTO').find('area').find('nombre').first().text()); 		
					//Fecha de la solicitud
					$('#fechaSolicitudDesignarPerito').val();
					
					
    			}
    		}
    	});
    }
	
	function cargaDatosSolicitud(solicitudId){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultaSolicitudesEvidenciasPorId.do',
	    	  data: 'solicitudId='+solicitudId,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  pintaDatosSolicitud(xml);
			  }
	    });
    }

    function pintaDatosSolicitud(xml){
		 
        if($(xml).find('solicitudDTO').find('usuarioSolicitante') != null){
 		   $('#solDePericialNombre').val($(xml).find('solicitudDTO').find('usuarioSolicitante').find('nombreCompleto').text());
        }else{
  		   $('#solDePericialNombre').val($(xml).find('solicitudDTO').find('nombreSolicitante').text());
        }

        if($(xml).find('solicitudDTO').find('usuarioSolicitante') != null){
  		   $('#solDePericialPuesto').val($(xml).find('solicitudDTO').find('usuarioSolicitante').find('puesto').text());
         }

        if($(xml).find('solicitudDTO').find('usuarioDTO') != null){
   		   $('#solDePericialAreaAdmin').val($(xml).find('solicitudDTO').find('usuarioDTO').find('areaActual').text());
          }

        if($(xml).find('solicitudDTO').find('expedienteDTO') != null){
    		   $('#solDePericialAreaAdmin').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());
           }

        //if($(xml).find('solicitudDTO').find('expedienteDTO') != null){
 		//   $('#solDePericialAreaAdmin').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());
        //}
    }

    function addCadenaRegistrada(){
		var selecciones = jQuery("#gridDetalleCadenaCustodia").jqGrid('getGridParam','selarrrow');
		if (selecciones) {
			selecciones = selecciones +"";
			var id = selecciones.split(",");
			var registrosSeleccionados = "(";
			for(var a= 0; a<id.length;a++){
				var ret = jQuery("#gridDetalleCadenaCustodia").jqGrid('getRowData',id[a]);
				var numEvidencia =  ret.NumeroEvidencia;
				numEvidencia = numEvidencia.substring(numEvidencia.length-9,numEvidencia.length-6);
				registrosSeleccionados = registrosSeleccionados + numEvidencia + ",";
			}
			registrosSeleccionados  = registrosSeleccionados.substring(0, registrosSeleccionados.length - 1);
			registrosSeleccionados = registrosSeleccionados + ')';
			$('#solServPericialCadenasRegistradas').append('<option>'+$('#solServPericialCadenaCustodia').val() + registrosSeleccionados +'</option>');
		} else {
			alert("Seleccione un registro");
		} 
		
		
	}

	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solDePericialFecha').val($(xml).find('fechaActual').text());
    		}
		});
    }

	function gridCadenaCustodia(){
		jQuery("#gridDetalleCadenaCustodia").jqGrid({ 
			url:'<%= request.getContextPath()%>/EjemploCadenaCustodia.xml', 
			datatype: "xml", 
			colNames:['Número de Evidencia','Tipo de Objeto','Objeto','Descripción','Código de Barras','Acuse'], 
			colModel:[ 	{name:'NumeroEvidencia',index:'numeroEvidencia', width:40},
			           	{name:'TipoObjeto',index:'tipoObjeto', width:40},
			           	{name:'Objeto',index:'objeto', width:40},
			           	{name:'Descripcion',index:'descripcion', width:30},
			           	{name:'CodigoBarras',index:'codigoBarras', width:40},
			           	{name:'Acuse',index:'acuse', width:10}
					],
			pager: jQuery('#pagerCadenaCustodia'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'NumeroEvidencia',
			viewrecords: true,
			sortorder: "desc",
			multiselect: true
			}).navGrid('#pagerCadenaCustodia',{edit:false,add:false,del:false});
		}

	function guardarSolicitud(){
		fechaLimite =  $("#solServPericialFechaLimite").val();
		motivo = escape($(".jquery_ckeditor").val());
		envio = "false";
		
		$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/guardarSolicitudServicioPericial.do',
    	    data: 'documentoId='+documentoId+'&tipoSolicitud='+tipoServicio+'&especialidad='+especialidad+
    	    '&fechaLimite='+fechaLimite+'&motivo='+motivo+'&envio='+envio,
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			documentoId = $(xml).find('documentoId').text();
    		}
		});
		
		
	}

	//Envio de la solicitud a en proceso
	function enviarSolicitud(){
		fechaLimite =  $("#solServPericialFechaLimite").val();
		motivo = escape($(".jquery_ckeditor").val());
		envio = "true";
		
		$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/guardarSolicitudServicioPericial.do',
    	    data: 'documentoId='+documentoId+'&tipoSolicitud='+tipoServicio+'&especialidad='+especialidad+
    	    '&fechaLimite='+fechaLimite+'&motivo='+motivo+'&envio='+envio,
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			documentoId = $(xml).find('documentoId').text();
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
					<!--<li><a href="#tabsconsultaprincipal-2">Para Quien Solicita</a></li>-->
					<li><a href="#tabsconsultaprincipal-3">Perito</a></li>
					<li><a href="#tabsconsultaprincipal-4">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-5">Dar Aviso A</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
				<fieldset style="width:700px">
				<legend>Solicitante</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Nombre Servidor Público:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Cargo:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Área Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha Elaboración:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDePericialFecha" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<br>
				<fieldset style="width:700px">
				<legend>Solicitante</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Número de Expediente:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solServPericialFechaLimite" width="50px" />
							</td>
						</tr>
					</table>				
				</fieldset>				
				</div>
				<!--<div id="tabsconsultaprincipal-2">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Número de Expediente:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solServPericialFechaLimite" width="50px" />
							</td>
						</tr>
					</table>
				</div>-->
				<div id="tabsconsultaprincipal-3">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Cadena de custodia:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialCadenaCustodia"/>
							</td>
							<td>
								<input type="button" id="solServPericialConsCadenaCustodia" value="Consultar" class="btn_Generico"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table id="gridDetalleCadenaCustodia"></table>
								<div id="pagerCadenaCustodia"></div>
							</td>
							<td>
								Cadenas Registradas:<br/>
								<select id="solServPericialCadenasRegistradas" size="3" style="width: 180px;"></select>
							</td>						
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								<input type="button" id="solServPericialAddCadenaCustodia" value="Agregar Cadena" class="btn_Generico"/>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="right">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-4">
					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
					<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
				</div>
				<div id="tabsconsultaprincipal-5" style="height: 400">
				  	<table width="600">
				  		<tr>
				  			<td width="10">&nbsp;</td>
				  			<td width="590">
				  				Lic. Hugo S&aacute;nchez <br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Coordinador de Servicios Periciales Externos</b>
				  			</td>
				  		</tr>
				  		<tr>
				  			<td>&nbsp;</td>
				  			<td>
				  				<input type="checkbox" disabled="disabled" id="chkNotificacioFisica"/>Entregar Notificaci&oacute;n F&iacute;sicamente
				  			</td>
				  		</tr>
				  		<tr>
							<td colspan="2" align="right">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
							</td>
						</tr>
				  	</table>
				  </div>
			</div>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" id="btnEnviarSolicitud" value="Enviar Solicitud" class="btn_Generico"/>
		</td>
	</tr>
</table>
</body>
</html>