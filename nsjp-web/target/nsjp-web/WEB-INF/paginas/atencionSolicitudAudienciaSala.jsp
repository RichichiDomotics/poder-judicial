<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administracion de Audiencia</title>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>


<script type="text/javascript">
	function customRange(input) {
		  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
			$('#idHoraDate').timeEntry('getTime') : null),
			maxTime: (input.id == 'idHoraDate' ?
			$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
	    }
	    $(function(){
	      $('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
	    });

		var idWindowIngresarProbResponsable = 1;
	    
	$(document).ready(function() {
		$("#txtFecha").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$( "#tabsprincipalconsulta" ).tabs();
		
		jQuery("#gridDetalleProbablesResponsablesDos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploAtnSolAudSalaCheck.xml', 
			datatype: "xml", 
			colNames:['Involucrado','Presente'], 
			colModel:[ 	{name:'Involucrado',index:'involucrado', width:200}, 
						{name:'Presente',index:'presente', width:100}
					],
			pager: jQuery('#pagerDos2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			onSelectRow: function(id){
				$("#gridDetalleAsuntos").hide();		
			}
		}).navGrid('#pagerDos2',{edit:false,add:false,del:false});
		
		jQuery("#gridDetalleAsuntos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploAtnSolAudSalaEntregado.xml', 
			datatype: "xml", 
			colNames:['Fecha y Hora','Entregado'], 
			colModel:[ 	{name:'fechaHora',index:'fechahora', width:200}, 
						{name:'Entregado',index:'entregado', width:100}
					],
			pager: jQuery('#pagerTres2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerTres2',{edit:false,add:false,del:false});

		jQuery("#gridDetalleImputados").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploAtnSolAudSalaCheck.xml', 
			datatype: "xml", 
			colNames:['Imputados',' '], 
			colModel:[ 	{name:'imputados',index:'imputados', width:200},
			           	{name:'check',index:'check', width:50}
					],
			pager: jQuery('#pagerCuatro'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerCuatro',{edit:false,add:false,del:false});
		
		jQuery("#gridDetalleDelitos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploAtnSolAudSalaAsunto.xml', 
			datatype: "xml", 
			colNames:['Delitos'], 
			colModel:[ 	{name:'delitos',index:'delitos', width:200}
						
					],
			pager: jQuery('#pagerCinco'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerCinco',{edit:false,add:false,del:false});
	
		jQuery("#gridDetalleInvolucradoCalidad").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploAtnSolAudSalaAsunto2.xml', 
			datatype: "xml", 
			colNames:['Involucrados','Calidad'], 
			colModel:[ 	{name:'involucrados',index:'involucrados', width:100},
			           	{name:'calidad',index:'calidad', width:100}						
					],
			pager: jQuery('#pagerSeis'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerSeis',{edit:false,add:false,del:false});

		$("#btnAdminDatosInd").click(creaNuevoProbResponsable);
		$("#gridDetalleAsuntos").hide();
	});

	function creaNuevoProbResponsable() {
		
		idWindowIngresarProbResponsable++;
		$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
		$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do" width="1050" height="620" />');		
	}
	
	function mostrarGridEntregados()
	{
		$("#gridDetalleAsuntos").show();
	}

	</script>
</head>
<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-4">Detalle</a>
			</li>
			<li><a href="#tabsconsultaprincipal-1">Inicio de audiencia</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Administrar medidas cautelares</a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-1" style="height: 400">
			<table width="800" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="195" rowspan="2">
						<table width="192" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td width="45%">No.Audiencia: 0956</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="left"><input id="btnAdminDatosInd" type="button"
									value="Administrar Datos Individuo" class="btn_Generico"/>
								</td>
							</tr>
							<tr>
								<td align="left"><input id="btnSolDefensor" type="button"
									value="Solicitar Defensor" class="btn_Generico"/>
								</td>
							</tr>
						</table>
					</td>
					<td width="20" colspan="2" align="center"></td>
					<!-- <td width="323" align="center">Notificaciones</td>-->
				</tr>
				<tr>
					<td width="282"><table
							id="gridDetalleProbablesResponsablesDos"></table>
						<!-- <div id="pagerDos2" width="300"></div>-->
					</td>
					<td >&nbsp;</td>
					<td align="center"><input type="button" value="Ver Notificaciones" onclick="mostrarGridEntregados();" class="btn_Generico"></td>
					<td>
						<table width="282"><tr><td align="center">Notificaciones</td></tr></table>
						<table id="gridDetalleAsuntos"></table>
						<!-- <div id="pagerTres2" width="299"></div>-->
					</td>
				</tr>
			</table>
		</div>
		<div id="tabsconsultaprincipal-2" style="height: 400">
			<table width="1000">
			<tr>
			<td width="500" style="border-right-style: solid; border-width:2px;">
				<table width="500" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" colspan="3"><b>Particulares</b></td>
					</tr>
					<tr></tr>
					<tr>
						<td width="300" align="center">
							<table id="gridDetalleImputados"></table>
							<!-- <div id="pagerCuatro" width="300"></div>-->
						</td>
						<td width="100" align="center">
							<select>
								<option>-Seleccione-</option>
								<option>Traslado</option>
								<option>Garant&iacute;a Econ&oacute;mica</option>
							</select>
							<!-- <table id="gridDetalleDelitos"></table>
							<div id="pagerCinco" width="299"></div>-->
						</td>
						<td width="100"></td>
					</tr>
					<tr></tr>
					<tr>
						<td align="center" colspan="3"><input type="button" value="Solicitar" style="width: 200px;" class="btn_Generico"/></td>
					</tr>
				</table>
			</td>
			<td width="500" valign="top">
				<table width="500" border="0" cellspacing="0" cellpadding="0">
					<tr >
						<td align="center" colspan="3"><b>Generales</b></td>
					</tr>
					<tr></tr>
					<tr height="200px">
						<td width="100">Solicitud de :</td>
						<td width="300" align="center" valign="middle">
							<select>
								<option>-Seleccione-</option>
								<option>Audiencia</option>
								<option>Copia de Audio y Video</option>
							</select>
						</td>
						<td width="100" align="center">
						</td>
					</tr>
					<tr></tr>
					<tr>
						<td align="center" colspan="3"><input type="button" value="Solicitar" style="width: 200px;" class="btn_Generico"/></td>
					</tr>
				</table>
			</td>
			</tr>
			</table>
		</div>
		<div id="tabsconsultaprincipal-4" style="height: 400">
			<table width="800" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="215" rowspan="2" valign="top">
						<table width="184" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td width="45%">Caso: 002</td>
							</tr>
							<tr>
								<td align="left">Expediente: 0091290</td>
							</tr>
							<tr>
								<td>Fecha : 12/04/03</td>
							</tr>
							<tr>
								<td>Hora : 12:23 pm</td>
							</tr>
							
						</table></td>
					<td width="215" valign="top">
						<table width="184" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td>Tipo Audiencia: Publica</td>
							</tr>
							<tr>
								<td>Audiencia:</td>
							</tr>
							<tr>
								<td align="left">Juez(ces):</td>
							</tr>
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;Guillermo Ochoa</td>
							</tr>
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;Felix Salgado</td>
							</tr>
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;Marco Murillo</td>
							</tr>
							<tr>
								<td align="left">Defensor: Gabriel Ochoa</td>
							</tr>
						</table>
					</td>
					<td width="370"><table id="gridDetalleInvolucradoCalidad"></table>
						<!-- <div id="pagerSeis" width="300"></div>-->
					</td>
				</tr>
			</table>

		</div>
		<div id="divEditor">
			<table width="100%" border="0" cellspacing="5" cellpadding="0"
				bgcolor="#EEEEEE">
				<tr>

					<td width="85%" align="center"><hr></hr>
					</td>
				</tr>
				<tr>
					<td align="center">Audiencia:</td>
				</tr>
				<tr>
					<td align="center"><textarea name="textarea" cols="150"
							rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center"><input type="button" value="Generar Documento" class="btn_Generico"/>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>