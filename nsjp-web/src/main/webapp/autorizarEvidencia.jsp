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
	$(document).ready(function() {
		var solicitudId = '<%= request.getParameter("rowid")%>';
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
		$("#solServPericialFechaInicioPrestamo").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#solServPericialFechaFinPrestamo").datepicker({
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
		cargaDatosSolicitud(solicitudId);
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
    			$('#solDePericialFecha').val($(xml).find('fechaActual').text());
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

        if($(xml).find('solicitudDTO').find('fechaLimite') != null){
 		   $('#solDePericialAreaAdmin').val($(xml).find('solicitudDTO').find('fechaLimite').text());
        }

        //if($(xml).find('solicitudDTO').find('expedienteDTO') != null){
 		//   $('#solDePericialAreaAdmin').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());
        //}
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
					<li><a href="#tabsconsultaprincipal-3">Motivo</a></li>
					<li><a href="#tabsconsultaprincipal-4">Dar Aviso A</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
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
								Fecha inicio de préstamo:
							</td>
							<td>
								<input type="text" id="solServPericialFechaInicioPrestamo" width="50px" />
							</td>
						</tr>
						<tr>
							<td>
								Fecha fin de préstamo:
							</td>
							<td>
								<input type="text" id="solServPericialFechaFinPrestamo" width="50px" />
							</td>
						</tr>
						<tr>
							<td>
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solServPericialFechaVencimiento" width="50px" />
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-3">
					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
					<input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
				</div>
				<div id="tabsconsultaprincipal-4">
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