<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar Evidencia</title>
	
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
	var documentoId="";
	var solicitudId="";
	var envio="";
	var puestoId;
	var numeroExpedienteId = "";
	
	$(document).ready(function() {
		solicitudId = '<%= request.getParameter("rowid")%>';
		//alert(solicitudId);
		$("#tabsPrincipal").tabs();
		$("#btnEnviarSolicitud").click(enviarSolicitudEvidencia);
		$("#solServPericialAddCadenaCustodia").click(agregarEvidencias);
		$("#solServPericialDelCadenaCustodia").click(eliminarEvidencias);
		
		consultaFuncionario();
		cargaFechaCaptura();
		cargaDatosDelUsuario();
		cargaDatosSolicitud(solicitudId);
		cargaGridEvidencias(solicitudId);
		cargaGridEvidenciasAsignadas();
	});

	function cerrarVentana(){
		parent.cerrarVentanaEvidencia();
	}

	/*Funcion que dispara el Action para consultar los datos de usuario logeado*/
    function cargaDatosDelUsuario(){
    	
    	$('#solDePericialAreaAdmin').val('');
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
					$('#solDePericialAreaAdmin').val( $(xml).find('usuarioDTO').find('areaActual').find('nombre').first().text()); 		
					$('#solDePericialNombre').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text());
					$('#solDePericialPuesto').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
    }

	function cargaDatosSolicitud(solicitudId){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultaDetalleSolicitudPericial.do',
	    	  data: 'solicitudPericialId='+solicitudId,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  pintaDatosSolicitud(xml);
			  }
	    });
    }

    function pintaDatosSolicitud(xml){
		 
        if($(xml).find('usuarioSolicitante') != null){
 		   $('#solDePericialQuienSolicita').val($(xml).find('usuarioSolicitante').find('nombreFuncionario').first().text() + " " +
					$(xml).find('usuarioSolicitante').find('apellidoPaternoFuncionario').first().text() + " "+
					$(xml).find('usuarioSolicitante').find('apellidoMaternoFuncionario').first().text());
        }
        if($(xml).find('usuarioSolicitante') != null){
  		   $('#solDePericialEspecialidad').val($(xml).find('usuarioSolicitante').find('especialidad').find('valor').first().text());
        }
        if($(xml).find('fechaLimite') != null){
 		   $('#solServPericialFechaLimite').val($(xml).find('fechaLimiteStr').text());
        }
        if($(xml).find('expedienteDTO') != null){
 		   $('#solServPericialNumExpediente').val($(xml).find('expedienteDTO').find('numeroExpediente').first().text());
 		  numeroExpedienteId = $(xml).find('expedienteDTO').find('numeroExpedienteId').first().text();
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

	function cargaGridEvidencias(solicitudId){
		jQuery("#gridDetalleCadenaCustodia").jqGrid({ 
			url:'<%= request.getContextPath()%>/consultarEvidenciasSolicitud.do?solicitudId='+solicitudId+'',
			data:'',
			datatype: "xml", 
			colNames:['Número de Evidencia','Cadena de Custodia','Objeto','Código de Barras'], 
			colModel:[ 	{name:'NumeroEvidencia',index:'numeroEvidencia', width:150},
			           	{name:'CadenaCustodia',index:'cadenaCustodia', width:150},
			           	{name:'Objeto',index:'objeto', width:150},
			           	{name:'CodigoBarras',index:'codigoBarras', width:150}
					],
			pager: jQuery('#pagerCadenaCustodia'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'CadenaCustodia',
			viewrecords: true,
			sortorder: "desc",
			multiselect: true
			}).navGrid('#pagerCadenaCustodia',{edit:false,add:false,del:false});
	}

	function cargaGridEvidenciasAsignadas(){
		jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid({ 
			url:'local', 
			data:'',
			datatype: "xml", 
			colNames:['Número de Evidencia','Cadena de Custodia','Objeto','Código de Barras'],
			colModel:[ {name:'NumeroEvidencia',index:'numeroEvidencia', width:150},
						{name:'CadenaCustodia',index:'cadenaCustodia', width:150},
						{name:'Objeto',index:'objeto', width:150},
						{name:'CodigoBarras',index:'codigoBarras', width:150}
			],
			pager: jQuery('#pagerCadenaCustodiaAsignados'),
			rowNum:50,
			rowList:[50],
			autowidth: true,
			sortname: 'CadenaCustodia',
			viewrecords: true,
			sortorder: "asc",
			multiselect: true
			}).navGrid('#pagerCadenaCustodiaAsignados',{edit:false,add:false,del:false});
	}

	/*
	*Funcion que agrega una evidencia del grid de evidencias al grid de evidencias asignadas
	*/
	function agregarEvidencias(){
		var arraySelRows = jQuery("#gridDetalleCadenaCustodia").getGridParam('selarrrow');

		if (arraySelRows.length > 0) { 
			for(i=0; i<arraySelRows.length; i++){
				var ret = jQuery("#gridDetalleCadenaCustodia").jqGrid('getRowData',arraySelRows[i]);
				var arrayIds = jQuery("#gridDetalleCadenaCustodiaAsignados").getDataIDs();
				var cont=0;
				while(cont<arrayIds.length){
					if(arraySelRows[i] == arrayIds[cont]){
						alert("La evidencia ya se encuentra asignada");
						break;
					}
					cont++;		
				}
				if(cont==arrayIds.length){
					jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid('addRowData',arraySelRows[i],ret);
				}				
			}
		} 
		else{
			 alert("Por favor seleccione un renglon");
		} 		
	} 

	/*
	*Funcion que elimina una evidencia del grid de evidencias seleccionadas
	*/
	function eliminarEvidencias(){
		var arraySelRows = new Array();
		arraySelRows  = jQuery("#gridDetalleCadenaCustodiaAsignados").getGridParam('selarrrow');

		if (arraySelRows.length > 0) {
			for(var i=arraySelRows.length-1; i>=0; i--){
				jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid('delRowData',arraySelRows[i]);
			}
		} 
		else{
			 alert("Por favor seleccione un renglon");
		} 	
	} 
		
	//Envio de la solicitud a en proceso
	function enviarSolicitudEvidencia(){
		tipoServicio = <%=TiposSolicitudes.EVIDENCIA.getValorId()%>;
		arrayIds = jQuery("#gridDetalleCadenaCustodiaAsignados").getDataIDs();
		observaciones = $("#areaDescripcion").val();		
		
		if(arrayIds.length < 1){
			alert("Debe asignar Evidencias a la solicitud.");
		}else{
			//Datos de la ceja Solicitante
			var parametros = '&solicitudId=' + solicitudId;
			parametros += '&tipoServicio=' + tipoServicio;
			parametros += '&arrayIds=' +  arrayIds;
			parametros += '&observaciones=' + observaciones;
			parametros += '&destinatarioId=' + puestoId;
			parametros += '&numeroExpedienteId=' + numeroExpedienteId;
			
			$.ajax({
	    		type: 'POST',
	    	    url:'<%=request.getContextPath()%>/guardarSolicitudEvidencia.do',
	    	    data:parametros,
	    	    dataType: 'xml',
	    	    async: false,
	    	    success: function(xml){
	    	    	alert('La solicitud se envió correctamente');
	        	    parent.cerrarVentanaEvidencia();
	    		}
			});
		}
	}
	
	/**
	* Carga el funcionario a mostrar en la tab de Avisar a Funcionario según puesto del destinatario
	*/
	function consultaFuncionario(){

		puestoId = <%= Puestos.FISCAL.getValorId() %>;
		 	
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarFuncionarioANotificar.do',
    		data: 'puestoId='+ puestoId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					$('#solServPericialNombreFuncionario').val($(xml).find('nombreFuncionario').first().text() + " " +
							$(xml).find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('apellidoMaternoFuncionario').first().text());
    			}
				else{
					//Mostrar mensaje de error
				}
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
					<li><a href="#tabsconsultaprincipal-1">Solicitud</a></li>
					<li><a href="#tabsconsultaprincipal-3">Evidencia</a></li>
					<li><a href="#tabsconsultaprincipal-5">Dar Aviso A</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
				<fieldset style="width: 700px;">
				<legend>Responsable</legend>
					<table width="100%" border="0">
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
					<fieldset style="width: 700px;">
					<legend>Datos de la Solicitud</legend>
					<table width="100%" border="0">
						<tr>
							<td>
								Quien Solicita:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDePericialQuienSolicita" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Especialidad:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDePericialEspecialidad"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								N&uacute;mero de Expediente:
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
								<input type="text" id="solServPericialFechaLimite" width="50px" disabled="disabled"/>
							</td>
						</tr>						
					</table>					
					</fieldset>
				</div>
				<div id="tabsconsultaprincipal-3">
					<table width="100%" border="0">
						<tr>
							<td align="right">&nbsp;</td>
							<td colspan="2">&nbsp;</td>
				          <td>Recomendaciones:</td>						
						</tr>
						<tr>
							<td colspan="3">
								<table id="gridDetalleCadenaCustodia"></table>
								<div id="pagerCadenaCustodia"></div>
							</td>
				          	<td>
				            	<textarea id="areaDescripcion" cols="45" rows="5" style="width: 500px; height:200px;" ></textarea>
			              	</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" id="solServPericialAddCadenaCustodia" value="Agregar Evidencia" class="btn_Generico"/>
								<input type="button" id="solServPericialDelCadenaCustodia" value="Eliminar Evidencia" class="btn_Generico"/>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<table id="gridDetalleCadenaCustodiaAsignados"></table>
								<div id="pagerCadenaCustodiaAsignados"></div>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-5" style="height: 400">
				  	<table width="100%">
				        <tr>
				            <td>
				                Nombre Funcionario:
				                <input type="text" class="" size="50" maxlength="50" id="solServPericialNombreFuncionario" disabled="disabled"/>
				            </td>
				        </tr>
				        <tr>
				        	<td>&nbsp;</td>
				        </tr>
						<tr>
							<td align="left">
								<input type="button" id="btnEnviarSolicitud" value="Enviar Solicitud" class="btn_Generico"/>
							</td>
						</tr>
				  	</table>
				  </div>
			</div>
		</td>
	</tr>
</table>
</body>
</html>