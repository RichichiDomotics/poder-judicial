<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Descarcelacion No Atendida</title>
	
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
		$("#tabsPrincipal").tabs();
		$("#solServPericialConsCadenaCustodia").click(gridCadenaCustodia);
		$("#solServPericialAddCadenaCustodia").click(addCadenaRegistrada);
		$("#chbRegEvidencia").click(addCadenaRegistrada);
		
		$("#solServPericialFechaVencimiento").datepicker({
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
	});

	function cerrarVentana(){
		parent.cerrarVentanaEvidencia();
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

	</script>
</head>
<body>
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Solicitante</a></li>
					<li><a href="#tabsconsultaprincipal-2">A Quién Solicita</a></li>
					<li><a href="#tabsconsultaprincipal-3">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-4">Dar Aviso A</a></li>
					
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre Servidor Público:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Cargo:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  />
							</td>
						</tr>
						<tr>
							<td align="right">
								Área Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha Elaboración:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDePericialFecha" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-2">
				<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Número IPH:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Nombre Imputado:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  />
							</td>
						</tr>
						<tr>
							<td align="right">
								Tipo de Audiencia:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Delito:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Hora:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDePericialFecha" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="btnGuardar" value="Enviar Solicitud" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				
				
				</div>
				<div id="tabsconsultaprincipal-3">
				
					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
					<input type="button" id="btnGuardar" value="Guardar" align="top" class="btn_Generico"/>
					
				</div>
				<div id="tabsconsultaprincipal-4">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								<input type="checkbox" id="chbDirCentroDet"/>Director de la Policía Procesal
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="chbDirCentroDet"/>Médico de la CEDEPRO
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="chbDirCentroDet"/>Entregar Notificación Físicamente
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
								<input type="button" id="btnGuardar" value="Enviar Solicitud" class="btn_Generico"/>
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