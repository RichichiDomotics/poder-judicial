<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitud de Perito Exterior</title>
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$("#tabsPrincipal").tabs();
		$("#solServPericialFechaVencimiento").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#btnEnviarSolicitud").click(cerrarVentana);
		cargaFechaCaptura();
		creaMultiselect();
		muestraCriterioSolicitud();
	});

	function cerrarVentanaAsignacionEvidencia(){
		$.closeWindow('iframewindowAsignacionEvidencia');
	}
	function cerrarVentana(){
		parent.cerrarVentanaPeritoExterno();
	}

	function creaMultiselect(){
		$('#cbxTipoSolicitud').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1,
			close: function (event,ui){
				muestraCriterioSolicitud();}
		});

		$('#cbxCriterioTipoSolicitudPerito').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1,
			close: function (event,ui){
				muestraCriterioPerito();}
		});
		$('#cbxCriterioTipoSolicitudPeritoEspecialidad').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1 
		});
	}

	function muestraCriterioSolicitud(){
		var idGrid = $("#cbxTipoSolicitud option:selected").val();
		if(idGrid==0){
			$("#divTipoSolicitudPerito").css("display", "block");
		}else if(idGrid==1){
			$("#divTipoSolicitudPerito").css("display", "none");
			muestraSolEvidencia();
			limpiaCriterioPerito();
		}else{
			$("#divTipoSolicitudPerito").css("display", "none");
			limpiaCriterioPerito();
		}
	}

	function limpiaCriterioPerito(){
		$('#cbxCriterioTipoSolicitudPerito').find("option[value='-1']").attr("selected","selected");
		$('#nombreCriterioPerito').val('');
		$('#apPaternoCriterioPerito').val('');
		$('#apMaternoCriterioPerito').val('');
		$('#cbxCriterioTipoSolicitudPeritoEspecialidad').find("option[value='-1']").attr("selected","selected");
		muestraCriterioPerito();
	}

	function muestraSolEvidencia(){
		$.newWindow({id:"iframewindowAsignacionEvidencia", statusBar: true, posx:200,posy:50,width:700,height:350,title:"Asignación de Evidencia", type:"iframe"});
	    $.updateWindowContent("iframewindowAsignacionEvidencia",'<iframe src="<%=request.getContextPath()%>/asignacionDeEvidencia.jsp" width="700" height="350" />');
	}

	function muestraCriterioPerito(){
		var idGrid = $("#cbxCriterioTipoSolicitudPerito option:selected").val();
		if(idGrid==0){
			$("#divCriterioTipoSolicitudPeritoNombre").css("display", "block");
			$("#divCriterioTipoSolicitudPeritoEspecialidad").css("display", "none");
		}else if(idGrid==1){
			$("#divCriterioTipoSolicitudPeritoNombre").css("display", "none");
			$("#divCriterioTipoSolicitudPeritoEspecialidad").css("display", "block");
		}else{
			$("#divCriterioTipoSolicitudPeritoNombre").css("display", "none");
			$("#divCriterioTipoSolicitudPeritoEspecialidad").css("display", "none");
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
					<li><a href="#tabsconsultaprincipal-3">Tipo de Solicitud</a></li>
					<li><a href="#tabsconsultaprincipal-4">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-5">Dar Aviso A</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td>
								Nombre Servidor Público:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre"/>
							</td>
						</tr>
						<tr>
							<td>
								Cargo:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  />
							</td>
						</tr>
						<tr>
							<td>
								Área Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" />
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
							<td>
								Número de Expediente:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente"/>
							</td>
						</tr>
						<tr>
							<td>
								Fecha de Vencimiento:
							</td>
							<td>
								<input type="text" id="solServPericialFechaVencimiento" width="50px" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-3">
					<table width="100%" height="90%" border="0">
						<tr>
							<td width="20%">
								Solicitud: 
							</td>
							<td width="20%">
								<select id="cbxTipoSolicitud" style="width: 100px;">
									<option value="-1">-Seleccione-</option>
									<option value="0">Perito</option>
									<option value="1">Evidencia</option>
								</select>
							</td>
							<td width="60%">
								<select id="cbxSolicitud" size="3" style="width: 180px;">
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="divTipoSolicitudPerito">
									Criterio: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<select id="cbxCriterioTipoSolicitudPerito" style="width: 100px;">
										<option value="-1">-Seleccione-</option>
										<option value="0">Nombre</option>
										<option value="1">Especialidad</option>
									</select>
									<div id="divCriterioTipoSolicitudPeritoNombre">
										<table>
											<tr>
												<td>
													Nombre(s):
												</td>
												<td>
													<input type="text" class="" size="50" maxlength="50" id="nombreCriterioPerito"/>
												</td>
											</tr>
											<tr>
												<td>
													Apellido Paterno:
												</td>
												<td>
													<input type="text" size="50" maxlength="50"	id="apPaternoCriterioPerito"  />
												</td>
											</tr>
											<tr>
												<td>
													Apellido Materno:
												</td>
												<td>
													<input type="text" size="50" maxlength="50" id="apMaternoCriterioPerito" />
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<input type="button" maxlength="50" id="btnAddPerito" value="Agregar Perito" class="btn_Generico"/>
												</td>
											</tr>
										</table>
									</div>
									<div id="divCriterioTipoSolicitudPeritoEspecialidad">
										<table>
											<tr>
												<td>
													Especialidad:
												</td>
												<td>
													<select id="cbxCriterioTipoSolicitudPeritoEspecialidad" style="width: 100px;">
														<option value="-1">-Seleccione-</option>
														<option value="0">Especialidad 0</option>
														<option value="1">Especialidad 1</option>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<input type="button" maxlength="50" id="btnAddPerito" value="Agregar Perito" class="btn_Generico"/>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabsconsultaprincipal-5" style="height: 400">
				  	<table width="600">
				  		<tr>
				  			<td width="10">&nbsp;</td>
				  			<td width="590">
				  				Lic. Hugo S&aacute;nchez <br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Coordinaci&oacute;n Periciales</b>
				  			</td>
				  		</tr>
				  		<tr>
				  			<td>&nbsp;</td>
				  			<td>
				  				Lic. Elesban Ruiz<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Perito - Inform&aacute;tica</b><br/>
				  				Lic. Fernando Galindo<br/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Perito Ambiental</b><br/>				  				
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