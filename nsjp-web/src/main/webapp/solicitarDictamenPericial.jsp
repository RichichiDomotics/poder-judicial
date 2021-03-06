<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar Dictamen Pericial</title>
	
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
		$("#chbRegEvidencia").click(addCadenaRegistrada);
		
		$("#btnEnviarSolicitud").click(cerrarVentana);
		cargaFechaCaptura();
	});

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
    			$('#solDeSSPFecha').val($(xml).find('fechaActual').text());
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
					<li><a href="#tabsconsultaprincipal-2">PQS</a></li>
					<li><a href="#tabsconsultaprincipal-3">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-4">Avisar Funcionario</a></li>
					
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre Servidor P�blico:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPNombre" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Cargo:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDeSSPPuesto"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								�rea Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDeSSPAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha Elaboraci�n:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDeSSPFecha" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-2">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								N&uacute;mero IPH:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solNumeroIPH"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha de Vencimiento:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solFechaVencimiento"  />
							</td>
						</tr>
						<tr>
							<td align="right">
								Especialidad:
							</td>
							<td>
								<select id="solSSPEspecialidad" style="width: 300px;">
									<option>- Selecciona -</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
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
								Nombre del Funcionario
							</td>
						</tr>
						<tr>
							<td>
								Area del Funcionario 
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