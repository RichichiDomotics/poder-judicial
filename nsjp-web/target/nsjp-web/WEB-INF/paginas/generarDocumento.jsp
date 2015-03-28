<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Generar Documento</title>
			
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript">

		var idWindowTurnarDocumento = 1;	
			
			jQuery().ready(function () {
				
				$('#guardarNarraTiva').hide();
				cargaFechaCaptura();
				cargaHoraCaptura();
				$("#guardarDoc").bind("click",muestraTurImp);
				$("#turnarDoc").hide();
				$("#turnarDoc").click(seleccionarDestinatario);
				$("#imprimirDoc").hide();
								
				});

			/*
			*Funcion que abre ventana de turnar documento
			*/
			
			function seleccionarDestinatario(){
				
				idWindowTurnarDocumento++;
				$.newWindow({id:"iframewindow" + idWindowTurnarDocumento, statusBar: true, posx:150,posy:20,width:930,height:600,title:"Turnar Documento", type:"iframe"});
			    $.updateWindowContent("iframewindow" + idWindowTurnarDocumento,'<iframe src="<%= request.getContextPath() %>/turnarDocumento.do" width="930" height="600" />');		
			}
			/*
			*Funcion que muestra botones de turnar e imprimir
			*/
			function muestraTurImp(){
				$("#turnarDoc").toggle();
				$("#imprimirDoc").toggle();
				$("#guardarDoc").attr('disabled','disabled');
				}
			
			  /*
				*Funcion que dispara el Action para consultar catalogos
				*/
			   function cargaCatalogos() {
			         $('#idDelitosCaso').empty();
				      $.ajax({
					      type: 'POST',
				    	  url: '<%= request.getContextPath()%>/consultaCatalogosCaso.do',
				    	  data: '',
				    	  dataType: 'xml',
				    	  success: function(xml){
					    	  $('#idDelitosCaso').empty();
					    	  $('#idDelitosCaso').append('<option value="-1">- Seleccione -</option>');
				    		  $(xml).find('catCatalogo').each(function(){
								$('#idDelitosCaso').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							   });
				    	  }
				    	});
					}

			   /*
				*Funcion que dispara el Action para consultar catalogos
				*/
			   function cargaCatalogos2() {
			         $('#idFormasParticipacion').empty();
				      $.ajax({
					      type: 'POST',
				    	  url: '<%= request.getContextPath()%>/consultaCatalogosCaso.do',
				    	  data: '',
				    	  dataType: 'xml',
				    	  success: function(xml){
					    	  $('#idFormasParticipacionv').empty();
					    	  $('#idFormasParticipacion').append('<option value="-1">- Seleccione -</option>');
				    		  $(xml).find('catCatalogo').each(function(){
								$('#idFormasParticipacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							   });
				    	  }
				    	});
					}

			   /*
				*Funcion que carga la fecha actual
				*/

			  function cargaFechaCaptura(){
				    	$.ajax({
				    		type: 'POST',
				    	    url: '<%= request.getContextPath()%>/ConsultarFechaCaptura.do',
				    	    data: '',
				    	    dataType: 'xml',
				    	    success: function(xml){
				    			$('#generarDocumentoCmpFechaIngreso').val($(xml).find('fechaActual').text());
				    		}
						});
				    }

			  /*
				*Funcion que carga la hora actual
				*/

			  function cargaHoraCaptura(){
			    	$.ajax({
			    		type: 'POST',
			    	    url: '<%= request.getContextPath()%>/ConsultarHoraCaptura.do',
			    	    data: '',
			    	    dataType: 'xml',
			    	    success: function(xml){
			    			$('#idHoraDate').val($(xml).find('horaActual').text());
			    		}
					});
			    }
				
		</script>
	</head>
<body>
	
				<table align="center" border="0" width="820px" height="50%">
					<tr>
					  <td colspan="4">
					  		<ul class="toolbar">
								<div id="menu_head">
									<li id="guardarParcial" class="first"><span></span>Guardado Parcial</li>
									<li id="guardarDoc" class="first"><span></span>Guardado Definitivo</li>
									<li id="imprimirDoc" class="first"><span></span>Imprimir</li>
									<li id="turnarDoc" class="first"><span></span>Turnar</li>
									<li id="firma" class="first"><span></span>Salir</li>
								</div>
							</ul>
					  </td>
				  </tr>
					<tr>
					  <td>N&uacute;mero de caso: </td>
					  <td><input type="text" title="Numero caso" size="40" id="iNumeroCaso2" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
					  <td>No. Expediente: </td>
					  <td><input type="text" title="Numero expediente" size="30" id="iNumeroExpediente" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
				  </tr>
					<tr>
						<td width="20%">&nbsp;</td>
						<td width="">&nbsp;</td>
						<td width="">&nbsp;</td>
						<td width="">&nbsp;</td>
					</tr>
					<tr>
						<td width="20%">Nombre Servidor P&uacute;blico:</td>
						<td width=""><input type="text" title="Nombre del Servidor Publico" size="40" id="iNombreServidorPublico" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
						<td width="">Hora de Elaboraci&oacute;n:</td>
						<td width=""><input type="text" id="idHoraDate" disabled="disabled" size="30" style=" border:0; background-color:#EEEEEE;"/></td>
					</tr>
					<tr>
						<td width="20%">Cargo:</td>
						<td width=""><input type="text" title="Cargo" size="40" id="iPuesto" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
						<td width="">Fecha de Elaboraci&oacute;n:</td>
						<td width=""><input type="text" id="generarDocumentoCmpFechaIngreso" name="generarDocumentoCmpFechaIngreso" disabled="disabled" size="30" style=" border:0; background-color:#EEEEEE;"/></td>
					</tr>
					<tr>
						<td>&Aacute;rea Administrativa:</td>
						<td><input type="text" title="Area Administrativa" size="40" id="iAreaAdministrativa" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
						<td>Ciudad:</td>
						<td><input type="text" title="Ciudad" size="30" id="iCiudad" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
					</tr>
					<tr>
						<td width="20%">N&uacute;mero de Oficio:</td>
						<td width=""><input type="text" title="Numero de Oficio" size="40" id="iNumeroOficio" /></td>
						<td>Estado:</td>
						<td><input type="text" title="Estado" size="30" id="iEstado" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
					</tr>
				</table>
		
<table align="center" width="800px">
				<tr>
					<td>
					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
					</td>
				</tr>
				</table>
		


</body>
</html>