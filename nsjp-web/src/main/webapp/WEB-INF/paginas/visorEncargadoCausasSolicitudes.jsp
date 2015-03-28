<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar juez manualmente</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
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
	    
	$(document).ready(function() {
		
		jQuery("#gridDetalleProbablesResponsables").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploProbableResponsable.xml', 
			datatype: "xml", 
			colNames:['Probable Responsable','Delito'], 
			colModel:[ 	{name:'Responsable',index:'responsable', width:200}, 
						{name:'Delito',index:'delito', width:100}
					],
			pager: jQuery('#pager2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pager2',{edit:false,add:false,del:false});
		
		jQuery("#gridSalas").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploEncargadoCausasSolSalas.xml', 
			datatype: "xml", 
			colNames:['Sala','Tipo','Disponibilidad'], 
			colModel:[ 	{name:'Sala',index:'sala', width:100}, 
						{name:'Tipo',index:'tipo', width:120},
						{name:'Disponibilidad',index:'disponibilidad', width:220}
					],
			pager: jQuery('#pagerSalas'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerSalas',{edit:false,add:false,del:false});
		
		jQuery("#gridJuecesCargaTrabajo").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploEncargadoCausasJuezCargaTrabajo.xml', 
			datatype: "xml", 
			colNames:['Juez','Carga de TRabajo'], 
			colModel:[ 	{name:'Juez',index:'juez', width:200}, 
						{name:'Carga',index:'carga', width:120}
					],
			pager: jQuery('#pagerJuecesCargaTrabajo'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerJuecesCargaTrabajo',{edit:false,add:false,del:false});
		
		
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
		$("#juez1").click(checkboxListener);
		$('#juez2').click(checkboxListener);
		$('#juez3').click(checkboxListener);
		$('#juez4').click(checkboxListener);
		
		$("#sala1").click(checkboxListenerSala);
		$('#sala2').click(checkboxListenerSala);
		$('#sala3').click(checkboxListenerSala);
		$('#sala4').click(checkboxListenerSala);
		
		$("#responsable1").click(checkboxListenerReponsable);
		$('#responsable2').click(checkboxListenerReponsable);
		$('#responsable3').click(checkboxListenerReponsable);
		$('#responsable4').click(checkboxListenerReponsable);
		
		
		
	});

	function checkboxListener(){
		
		if ($("#juez1").is(':checked')){
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez2").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez3").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez4").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
		}		
	}
	
function checkboxListenerSala(){
		
		if ($("#sala1").is(':checked')){
			$('#sala2').attr('checked', false);
			$('#sala3').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala2").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala3').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala3").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala2').attr('checked', false);
			$('#sala4').attr('checked', false);
		}
		if ($("#sala4").is(':checked')){
			$('#sala1').attr('checked', false);
			$('#sala2').attr('checked', false);
			$('#sala3').attr('checked', false);
		}		
	}
	
function checkboxListenerResponsable(){
	
	if ($("#responsable1").is(':checked')){
		$('#responsable2').attr('checked', false);
		$('#responsable3').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable2").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable3').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable3").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable2').attr('checked', false);
		$('#responsable4').attr('checked', false);
	}
	if ($("#responsable4").is(':checked')){
		$('#responsable1').attr('checked', false);
		$('#responsable2').attr('checked', false);
		$('#responsable3').attr('checked', false);
	}		
}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-0">Detalle</a></li>
		<li><a href="#tabsconsultaprincipal-1">Datos de Audiencia</a></li>
		<li><a href="#tabsconsultaprincipal-2">Jueces</a></li>
		<li><a href="#tabsconsultaprincipal-3">Salas</a></li>
		<li><a href="#tabsconsultaprincipal-4">Sala Temporal</a></li>
		<li><a href="#tabsconsultaprincipal-5">Notificaciones</a></li>
	</ul>
	<div id="tabsconsultaprincipal-0">
		<table width="650" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="300" >
					<table id="gridDetalleProbablesResponsables"></table>
					<div id="pager2" width="300"></div>
				</td>
				<td width="50" >
				</td>
				<td width="300"  valign="top">
					<table>
						<tr>
							<td> Motivo:
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="5" cols="35"></textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table><br/><br/>
		<table width="650" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right"><b>Tipo de Audiencia: </b>
				</td>
				<td align="left">&nbsp;Privada
				</td>
				<td align="right"><b>Quien solicita: </b>
				</td>
				<td align="left">&nbsp;Arturo Soto Cruz
				</td>
			</tr>
			<tr>
				<td align="right"><b>Fecha L&iacute;mite: </b>
				</td>
				<td align="left">&nbsp;14/06/2011
				</td>
				<td align="right"><b>Instituci&oacute;n:</b>
				</td>
				<td align="left">&nbsp;Poder Judicial
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-1">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="552" border="0" cellspacing="5" cellpadding="0">
				      <tr>
				      	<td width="8%" >Fecha:</td>
				        <td width="27%"><input type="text" id="txtFecha" /></td>
				        <td>Duración Estimada:</td>
				        <td><input type="text" id="txtDuracionAprox" /></td>
			          </tr>
				      <tr>
				      	<td>Hora: </td>
				        <td><input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM">
				        </td>
				        <td colspan="2">¿Medios de Comunicaci&oacute;n presentes?: 
				        	Si
                          <input type="radio" name="medios" id="rdbMediosSi" />
No
<input type="radio" name="medios" id="rdbMediosNo" /></td>
			          </tr>
				      <tr>
				        <td>&nbsp;</td>
				        <td colspan="3">&nbsp;</td>
				      </tr>
				      <tr>
				        <td colspan="2" align="center">&nbsp;</td>
				        <td align="center"><input id="btnGuardar" type="button" value="Guardar" class="btn_Generico"/></td>
				        <td>&nbsp;</td>
				      </tr>
				      <tr>
				      	<td></td>
				      	<td colspan="3" align="left">&nbsp;</td>
				      </tr>
					</table>	
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-2">
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="300" >
					<table id="gridJuecesCargaTrabajo"></table>
					<div id="pagerJuecesCargaTrabajo" width="300"></div>
				</td>
				<td width="300" >
				</td>
				<td width="300"  valign="top">
				</td>
			</tr>
			<tr>
			  <td ><input type="button" value="Designar Autom&aacute;ticamente" class="btn_Generico"/></td>
			  <td >&nbsp;</td>
			  <td  valign="top"></td>
  </tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-3">
		<table width="650" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="300" >
					<table id="gridSalas"></table>
					<div id="pagerSalas" width="300"></div>
				</td>
				<td width="50" >
				</td>
				<td width="300"  valign="top">&nbsp;</td>
			</tr>
			<tr>
			  <td >&nbsp;</td>
			  <td ></td>
			  <td  valign="top">&nbsp;</td>
  </tr>
			<tr>
			  <td  align="center"><input type="button" value="Designar Autom&aacute;ticamente" class="btn_Generico"/></td>
			  <td ></td>
			  <td  valign="top">&nbsp;</td>
  </tr>
		</table>

	</div>
	<div id="tabsconsultaprincipal-4">
		<table width="355" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td colspan="2" align="center" width="250" ><h3>Direcci&oacute;n de la Sala</h3>
			</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="354" border="0" cellspacing="4" cellpadding="0">
				      <tr>
				        <td width="25%">Calle o Avenida:</td>
				        <td width="75%"><input type="text" style="width:90%;"></td>
				      </tr>
				      <tr>
				        <td>No. Exterior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>No. Interior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>Delegaci&oacute;n o <br/> Municipio :</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
				      <tr>
				        <td>Entidad Federativa</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
					</table>	
				</td>
				<td>
					<table width="100%">
						<tr>
							<td width="25%">Motivo : 
							</td>
							<td width="75%"> <textarea rows="5" cols="45"></textarea>
							</td>
						</tr>
						<tr>
							<td>Descripci&oacute;n: 
							</td>
							<td> <textarea rows="5" cols="45"></textarea>
							</td>
						</tr>
						<tr>
						<td colspan="2" align="right">
						</td>
						</tr>	
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-5">
		<table width="530px" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10">&nbsp;</td>
    <td width="247">&nbsp;</td>
    <td width="203">&nbsp;</td>
    <td width="68">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><b>Involucrados:</b></td>
    <td><b>Jueces: </b></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="checkbox" id="chkInvNot1" />
      Pastor Delgado - Homicida<br/>
      <input type="checkbox" id="chkInvNot2" />
      Luis Cabrera - Violador <br/>
      <input type="checkbox" id="chkInvNot3" />
      Alejandro Galaviz - Ladr&oacute;n<br/>
      <input type="checkbox" id="chkInvNot4" />
      Jorge Rostrillo - Estafador<br/>
      <input type="checkbox" id="chkInvNot5" />
    Fernanda Herrera - Secuestrador</td>
    <td><input type="checkbox" id="chkJuezNot1" />
      Armando Castaneda <br/>
      <input type="checkbox" id="chkJuezNot2" />
      Luis Mendoza <br/>
      <input type="checkbox" id="chkJuezNot3" />
      Alejandro Galaviz <br/>
      <input type="checkbox" id="chkJuezNot4" />
      Jorge Rostrillo <br/>
      <input type="checkbox" id="chkJuezNot5" />
    Fernanda Herrera</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><b>Defensor: </b></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" id="chkDefNot1" />
      Lic. Armando Castaneda <br/>
      <input type="checkbox" id="chkDefNot2" />
      Lic. Luis Mendoza <br/>
      <input type="checkbox" id="chkDefNot3" />
      Lic. Alejandro Galaviz <br/>
      <input type="checkbox" id="chkDefNot4" />
      Lic. Jorge Rostrillo <br/>
      <input type="checkbox" id="chkDefNot5" />
    Lic. Fernanda Herrera</td>
    <td>&nbsp;</td>
  </tr>
</table>

	</div>
</div>
</body>
</html>