<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	
   	<!-- Carga la css para el plugin de adjuntar documento -->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/plupload/jquery.ui.plupload.css" />  
   	    
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		
	<script type="text/javascript" src="http://bp.yahooapis.com/2.4.21/browserplus-min.js"></script>
	
	<!-- Carga el script para el funcionamiento de adjuntar documento -->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/plupload/plupload.full.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/plupload/jquery.ui.plupload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
	<script type="text/javascript">
	
	var idExpediente = '<%=request.getParameter("idExpediente")!=null?request.getParameter("idExpediente"):""%>';
	//var numeroExpedienteCadena = '<%=request.getParameter("numeroExpedienteCadena")!=null?request.getParameter("numeroExpedienteCadena"):""%>';
	var numeroExpedienteCadena = '<%=request.getParameter("numeroExpediente")!=null?request.getParameter("numeroExpediente"):""%>';
	var tipo = '<%=request.getParameter("tipo")!=null?request.getParameter("tipo"):""%>';
	
	$(document).ready(function() {

		$(function() {
				    $("#uploader").plupload({
				        // Opciones generales
				        runtimes : 'html5',
				        url : '<%= request.getContextPath() %>/adjuntarDocumentoAsociadoAExpedientePenal.do',
				        max_file_size : '10mb',
				        chunk_size : '1mb',
				        unique_names : true,
				 
				        // Resize images on clientside if we can
				        resize : {width : 320, height : 240, quality : 90},
				 
				        // Tipos de archivos que se pueden adjuntar
				        filters : [
			            {title : "Image files", extensions : "jpg,gif,png"},
			            {title : "Zip files", extensions : "zip"},
			            {title : "Pdf files", extensions : "pdf"}
			            	]				 
				      
				    });
				 
				    // Client side form validation
				    $('form').submit(function(e) {
				        var uploader = $('#uploader').plupload();
				 
				        // Files in queue upload them first
				        if (uploader.files.length > 0) {
				            // When all files are uploaded submit form
				            uploader.bind('StateChanged', function() {
				                if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
				                    $('form')[0].submit();
				                }
				            });
				                 
				            uploader.start();
				        } else {
				        	alertDinamico('Se debe subir al menos un archivo');
				        }
				 
				        return false;
				    });
				});   

	//Fin onready	
	});
	

		//Funcion confirma el adjuntar archivo
		function adjuntarArchivo() {
				confirmar=confirm("¿El archivo seleccionado se adjuntara al expediente, ¿Desea continuar?");
		  		if (confirmar){
		  			adjuntarArchivoActuaciones();
			   		parent.cerrarVentanaAdjuntarDocumento();			   		

		  		}else{}
		  		
		}


		//Funcion que cancela el adjuntar un archivo
		function cancelarArchivo() {
		  		confirmar=confirm("¿Realmente desea salir?");
		  		if (confirmar){
		  			parent.cerrarVentanaAdjuntarDocumento();		   		

		  		}else{}
		  		
		}				

		//manda archivo al accion para asociarlo al expediente
		function adjuntarArchivoActuaciones(){
				alert(tipo+" "+idExpediente+" "+numeroExpedienteCadena);
				document.adjuntarDocForm.tipo.value = tipo;
				document.adjuntarDocForm.expedienteId.value = idExpediente;
				document.adjuntarDocForm.numeroExpedienteCadena.value = numeroExpedienteCadena;
		forma = document.adjuntarDocForm;
		forma.submit();
		
		}
		
	</script>
	<title>Adjuntar documento</title>
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
			<table cellpadding="0" cellspacing="0" >
					<tr>
						<td colspan="2">&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" width="16px">&nbsp;
						</td>
						<td colspan="2">
						<form>
								<!-- <div id="uploader"  style="width: 700px; height: 400px;" align="center">
							        <p>tu navegador no soporta el complemento</p>
							    </div>-->
						</form>
						</td>
					</tr>				
					<tr id="adjuntarDocumentoStruts" >
						<td colspan="2" align="center">
							 <form id="adjuntarDocForm" name="adjuntarDocForm" action="<%= request.getContextPath() %>/adjuntarDocumentoAsociadoAExpediente.do" method="post" enctype="multipart/form-data" >
								<input id="btnAdjuntarArchivo" type="file" name="archivoAdjunto" value="" > 
								<input type="hidden" name="numeroExpedienteCadena" />
								<input type="hidden" name="expedienteId" />
								<input type="hidden" name="tipo" />
							</form>
								
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;
						</td>
					</tr>
					<tr id="botonesAdjuntar" >
						<td width="140" align="center"><input type="button" id="adjuntar" class="btn_modificar" value="Adjuntar" onclick="adjuntarArchivo();" />
						</td>
						<td width="140" align="center"><input type="button" id="cancelar" class="btn_modificar" value="Cancelar" onclick="cancelarArchivo();"/></td>
					</tr>			
				</table>		
	</body>
	</html>
