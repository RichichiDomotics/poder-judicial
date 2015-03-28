<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingreso CEDEPRO</title>
	
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
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
	//alert(sesionActiva);
	document.location.href="<%= request.getContextPath()%>/Logout.do";
	}
	$(document).ready(function() {
		$("#tabsPrincipal").tabs();
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


	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solDeSSPFecha').val($(xml).find('fechaActual').text());
    			$('#solDeSSPFecha2').val($(xml).find('fechaActual').text());
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
					<li><a href="#tabsconsultaprincipal-2">Ingreso</a></li>
					<li><a href="#tabsconsultaprincipal-3">Pertenencias</a></li>
					<li><a href="#tabsconsultaprincipal-4">Avisar Funcionario</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre Servidor Público:
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
								Área Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDeSSPAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha Elaboración:
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
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPIPH" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Nombre del imputado:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPNombreImputado" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Hora de ingreso:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDeSSPHoraIngreso" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha Elaboración:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDeSSPFecha2" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-3">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Categor&iacute;a de la pertenencia:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPCategoPertenencia"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Descripci&oacute;n de la pertenencia:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPDescPertenencia"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Condici&oacute;n F&iacute;sica de la pertenencia:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDeSSPCondicionPertenencia"/>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								 Pertenencias Registradas
							</td>
						</tr>
						<tr>
							<td align="right" valign="top" >
								<input type="button" id="btnIngresarPertenencias" value="Ingresar Pertenencia" class="btn_Generico"/>
							</td>
							<td>
				            	<textarea rows="4" cols="30"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-4">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								<input type="checkbox" checked="checked"/>Enviar Aviso de Acceso al CEDEPRO
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
</table>
</body>
</html>