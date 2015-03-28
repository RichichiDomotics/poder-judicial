<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Autorizar Evidencia</title>
	
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
	<script type="text/javascript">
	var solicitudId = '<%= request.getParameter("rowid")%>';
	var area = <%=request.getParameter("area")%>;
	var puestoId;
	
	$(document).ready(function() {
		$("#tabsPrincipal").tabs();
		$('#solServPericialFechaInicioPrestamo, #solServPericialFechaFinPrestamo').datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true,
	        firstDay: 1, 
	        changeFirstDay: false,
	        beforeShow: customRange
		});
		//$('#solServPericialFechaInicioPrestamo,#solServPericialFechaFinPrestamo').datepick({showTrigger: '#calImg'});
		$("#guardarNarraTiva").css("display", "none");
		$("#btnEnviarSolicitud").click(enviarAutorizacion);
		cargaFechaCaptura();
		cargaDatosDelUsuario();
		cargaDatosSolicitud(solicitudId);
		cargaGridEvidencias(solicitudId);	
		consultaFuncionario();
	});

	function customRange(input){ 
	    var min = new Date(1900, 11 - 1, 1); //Set this to your absolute minimum date
	    var max = new Date(2100, 11 - 1, 1); //Set this to your absolute minimum date
        var dateMin = min;
        var dateMax = max;
        var dayRange = 100000;  // Set this to the range of days you want to restrict to
	    
        if (input.id == "solServPericialFechaInicioPrestamo") 
        {
            if ($("#solServPericialFechaFinPrestamo").datepicker("getDate") != null)
            {
                dateMax = $("#solServPericialFechaFinPrestamo").datepicker("getDate");
                dateMin = $("#solServPericialFechaFinPrestamo").datepicker("getDate");
                dateMin.setDate(dateMin.getDate() - dayRange);
                if (dateMin < min)
                {
                        dateMin = min;
                }
             }
             else
             {
                //dateMax = new Date(); //Set this to your absolute maximum date
             }                      
        }
        else if (input.id == "solServPericialFechaFinPrestamo")
        {
               //dateMax = new Date(); //Set this to your absolute maximum date
               if ($("#solServPericialFechaInicioPrestamo").datepicker("getDate") != null) 
               {
                       dateMin = $("#solServPericialFechaInicioPrestamo").datepicker("getDate");
                       var rangeMax = new Date(dateMin.getFullYear(), dateMin.getMonth(), dateMin.getDate() + dayRange);

                       if(rangeMax < dateMax)
                       {
                           dateMax = rangeMax; 
                       }
               }
        }
    	return {
               minDate: dateMin, 
               maxDate: dateMax
        }; 
	}
	
	function cerrarVentana(){
		parent.cerrarVentanaEvidencia();
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
		 
        if($(xml).find('solicitudDTO').find('expedienteDTO') != null){
    		   $('#solServPericialNumExpediente').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());
           }

        if($(xml).find('solicitudDTO').find('fechaLimite') != null){
 		   $('#solServPericialFechaLimite').val($(xml).find('solicitudDTO').find('fechaLimiteStr').text());
        }

        if($(xml).find('solicitudDTO').find('observaciones') != null){
  		   $('#areaObservaciones').val($(xml).find('solicitudDTO').find('observaciones').text());
        }

        if($(xml).find('solicitudDTO').find('usuarioSolicitante') != null){
   		   puestoId = $(xml).find('solicitudDTO').find('usuarioSolicitante').find('puesto').find('idCampo').text();
        }

        /*if($(xml).find('solicitudDTO').find('fechaLimite') != null){
  		   $('#solServPericialFechaInicioPrestamo').val($(xml).find('solicitudDTO').find('fechaLimite').text());
        }
        
        if($(xml).find('solicitudDTO').find('fechaLimite') != null){
  		   $('#solServPericialFechaFinPrestamo').val($(xml).find('solicitudDTO').find('fechaLimite').text());
        }*/
    }

	function recuperaDatosAutorizaEvidencia(){
		var lsDatosEvidencia="";
		
		lsDatosEvidencia += "&fechaInicioPrestamo="+$("#solServPericialFechaInicioPrestamo").val();  
		lsDatosEvidencia += "&fechaFinPrestamo="+$("#solServPericialFechaFinPrestamo").val();
		lsDatosEvidencia += "&nombreAutorizado="+$("#solServPericialPersonaAutorizadas").val();  
		lsDatosEvidencia += "&observaciones="+$('#areaDescripcion').val();
		lsDatosEvidencia += "&solicitudId="+solicitudId;
		lsDatosEvidencia += "&puestoId="+puestoId;
		lsDatosEvidencia += "&area="+area;

		lsDatosEvidencia += recuperaDatosTipoDocIdentificacion( );

		/*lsDatosTipoDocIdent="&docIdentificacion="+$("#documentoIdentificacion option:selected").val();
		lsDatosTipoDocIdent+="&folioDoc="+$("#folioDocumentoIdentificacion").val();*/
		
		return lsDatosEvidencia;
	}
	
	//Envio de la solicitud a en proceso
	function enviarAutorizacion(){
		var params = recuperaDatosAutorizaEvidencia();

		if($("#solServPericialFechaInicioPrestamo").val() == "" || $("#solServPericialFechaFinPrestamo").val() == ""
			|| $("#solServPericialPersonaAutorizadas").val() == "" || $("#folioDocumentoIdentificacion").val() == ""
			|| $("#documentoIdentificacion option:selected").val() == 0){
			alertDinamico("Debe capturar los siguientes campos:\n*Fecha Inicio de Prestamo\n*Fecha Fin de Prestamo\n*Persona Autorizada\n*Tipo de documento de identificación\n*Folio de documento de identificación");
		}else{
			$.ajax({
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/guardaAutorizarEvidencia.do',
	    	    data: params,
	    	    dataType: 'xml',
	    	    async: false,
	    	    success: function(xml){
	        	    alertDinamico('La solicitud se envió correctamente');
	        	    parent.cerrarVentanaEvidencia();
	    			//documentoId = $(xml).find('documentoId').text();
	    		}
			});
		}
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
			multiselect: false
			}).navGrid('#pagerCadenaCustodia',{edit:false,add:false,del:false});
	}

	/**
	* Carga el funcionario a mostrar en la tab de Avisar a Funcionario según puesto del destinatario
	*/
	function consultaFuncionario(){
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
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Solicitud</a></li>
					<li><a href="#tabsconsultaprincipal-2">Detalle Solicitud</a></li>
					<li><a href="#tabsconsultaprincipal-3">Autorizar Evidencia</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<fieldset style="width: 700px;">
					<legend>Responsable</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Nombre Servidor P&uacute;blico:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialNombre" disabled="disabled"/>
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
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Número de Expediente:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha inicio de préstamo:
							</td>
							<td>
								<input type="text" id="solServPericialFechaInicioPrestamo" width="50px"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha fin de préstamo:
							</td>
							<td>
								<input type="text" id="solServPericialFechaFinPrestamo" width="50px"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solServPericialFechaLimite" width="50px"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
				          <td>Observaciones:</td>
				          <td>
				            <textarea id="areaObservaciones" cols="45" rows="5" style="width: 500px; height:200px;" disabled="disabled"></textarea>
			              </td>
				        </tr>
					</table>
					</fieldset>
				</div>
				<div id="tabsconsultaprincipal-2">
					<fieldset style="width: 700px;">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Persona Autorizada:
							</td>
							<td align="left">
								<input type="text" size="50" maxlength="50"  id="solServPericialPersonaAutorizadas"/>
							</td>
						</tr>
						<tr>
				          <td>Recomendaciones:</td>
				          <td>
				            <textarea id="areaDescripcion" cols="45" rows="5" style="width: 500px; height:200px;" ></textarea>
			              </td>
				        </tr>
					</table>
					<table>
						<tr>
							<td>
								<jsp:include page="ingresarDocumentoIdentificacionView.jsp"/>
							</td>
						</tr>
					</table>
					</fieldset>
					<fieldset style="width: 700px;">
					<legend>Evidencias</legend>
					<table>
						<tr>
							<td width="100%">
								<table id="gridDetalleCadenaCustodia"></table>
								<div id="pagerCadenaCustodia"></div>
							</td>
						</tr>
					</table>
					</fieldset>
				</div>
				<div id="tabsconsultaprincipal-3">
				  	<table width="600">
				  		<tr>
				  			<td>&nbsp;</td>
				  			<td>
				                Nombre Funcionario:
				                <input type="text" class="" size="50" maxlength="50" id="solServPericialNombreFuncionario" disabled="disabled"/>
				            </td>
				  		</tr>
				  		<tr>
							<td  colspan="2" align="center">
								<input type="button" id="btnEnviarSolicitud" value="Enviar Autorizacion" class="btn_Generico">
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