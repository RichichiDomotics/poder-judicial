<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atencion a Sollicitudes de Recurso</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript">

		var idDocumentoGlobal;
		var archivoDigitalId;
		var expedienteId;
		var numeroExpedienteId;
	
	$(document).ready(function() {

		var idDocumento = <%= request.getParameter("idDocumento")%>;
		
		idDocumentoGlobal=idDocumento;
		consultaDetalleEvento();
	
		$("#tabsprincipalconsulta" ).tabs();
		$("#verDocumento").click(abrirPDF);
		$("#dictarAcuerdoBoton").click(dictarAcuerdo);

		asociaTOCA();		
		
	});

	function abrirPDF(){
		//alert(archivoDigitalId);
		document.frmDoc.archivoDigitalId.value = archivoDigitalId;
		document.frmDoc.submit();
		
	}
	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultaDetalleEvento(){

	//	alert("consultaDetalleEvento" + idDocumentoGlobal);
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/detalleSolicitudRecurso.do',
			data: 'idDocumento='+ idDocumentoGlobal, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				  
    				$("#numCausa").val($(xml).find('solicitudDTO').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').first().text());
    				$("#fechaSolicitud").val($(xml).find('solicitudDTO').find('strFechaCreacion').first().text());
    				$("#horaSolicitud").val($(xml).find('solicitudDTO').find('strHoraCreacion').first().text());
    				$("#solicitante").val($(xml).find('solicitudDTO').find('nombreSolicitanteExternoInterno').first().text());
    				$("#insSolicitante").val($(xml).find('solicitudDTO').find('institucion').find('nombreInst').first().text());
    				$("#documento").val($(xml).find('nombreArchivo').first().text());

    				archivoDigitalId=$(xml).find('archivoDigitalId').first().text();
    				expedienteId=$(xml).find('solicitudDTO').find('expedienteDTO').find('expedienteId').first().text();
    				numeroExpedienteId=$(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpedienteId').first().text();
			}
		});
	}

	function dictarAcuerdo() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Dictar Acuerdo", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId=<%=Formas.ACUERDO_RECURSO_RECIBIDO.getValorId()%>&esconderArbol=1" width="1140" height="400" />');
	   		
	}

	function asociaTOCA(){

		//alert("NumeroExpedienteId"+numeroExpedienteId);
		//alert("expedienteId"+expedienteId);
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/asignarNumeroExpedienteTOCA.do?numeroExpedienteId='+numeroExpedienteId+'',
			data: 'expedienteId='+ expedienteId, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				
			}
		});

		}
		
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
<ul>
	<li><a href="#tabsconsultaprincipal-1">Detalle de Recurso</a></li>
</ul>

<!--Comienza tabprincipal 1-->
<div id="tabsconsultaprincipal-1">

	<table width="800" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td width="24%" align="right">&nbsp;</td>
			<td width="22%">&nbsp;</td>
			<td width="21%">&nbsp;</td>
			<td width="33%">&nbsp;</td>
		</tr>
		<tr>
		  <td align="right"><strong>Número de Causa:</strong></td>
		  <td><input type="text"
				id="numCausa" 
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly" /></td>
		  <td align="right"><strong>Fecha de Solicitud:</strong></td>
		  <td><input type="text" id="fechaSolicitud"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
  </tr>
		<tr>
		  <td align="right"><strong>Solicitante:</strong></td>
		  <td><input type="text" id="solicitante"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  <td align="right"><strong>Hora de Solicitud:</strong>
		  </td>
		  <td><input type="text" id="horaSolicitud"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
  </tr>
		<tr>
		  <td align="right"><strong>Institución Solicitante:</strong></td>
		  <td><input type="text" id="insSolicitante"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  /></td>
		  <td align="right"><strong>Documento:</strong></td>
		  <td><input type="text" id="documento"
				style="width: 150px; border: 0; background: #DDD;"
				readonly="readonly"  />
		    <input type="button" value="Ver" id="verDocumento" class="btn_Generico"/></td>
  </tr>
		<tr>
		  <td align="right">&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
  </tr>
		<tr>
		  <td align="right">&nbsp;</td>
		  <td colspan="2" align="center"><input type="button" value="Dictar Acuerdo" id="dictarAcuerdoBoton" class="btn_Generico"/></td>
		  <td>&nbsp;</td>
  </tr>
		<tr>
		  <td align="right">&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
  </tr>
	</table>


</div>
<!--Termina Div Principal 1-->
</div>
<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" name="archivoDigitalId" value=""/>
					
										
</form>
</body>
</html>