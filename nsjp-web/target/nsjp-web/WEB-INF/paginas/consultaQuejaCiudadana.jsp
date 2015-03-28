<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Queja Ciudadana</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/estilos.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/layout_complex.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<script type="text/javascript">
	
	
	var idExpediente;
	var idExpediente2;
	var idWindowIngresarDenunciante=0;
	var respuestaQueja;
	var nombreQuejoso;
	var idQuejoso;
	var idQueja;
	
	$(document).ready(function() {
		idQueja ='<%= request.getParameter("idQueja")%>';
		$("#tabsPrincipal").tabs();
		
		var config = {toolbar:
			[
				['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
				['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
				['NumberedList','BulletedList','-','Outdent','Indent'],
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				['Table','HorizontalRule'],
				'/',
				['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
			]
		};

		$('.jquery_ckeditor').ckeditor(config);

		cargaMotivoQueja();
		consultadeQueja(idQueja);
		$('input:text').attr("disabled", true);

		$("#quejaProsperaSi").bind("click",muestraCausas);
		$("#quejaProsperaNo").bind("click",ocultaCausas);

		$('#divRechazoQueja').hide();
		$('#tabsPrincipal').tabs("option", "disabled", [2]);
	});
	
	function  consultadeQueja(idQueja){

	  $.ajax({
		type: 'POST',
		url:  '<%= request.getContextPath()%>/consultarQuejaCiudadanaXIdSSPPolicia.do?idQueja='+idQueja+'',
		dataType: 'xml',
		async: false,
		success: function(xml){
		
			$('#cQuejaNumero').val($(xml).find('quejaCiudadanaDTO').find('folioQueja').text());			   
			$("#cQNomQuejoso").val($(xml).find('quejaCiudadanaDTO').find('nombreQuejoso').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoPatQuejoso').text()+" "+$(xml).find('quejaCiudadanaDTO').find('getApellidoMatQuejoso').text());
			$("#cQTipoQueja").val($(xml).find('quejaCiudadanaDTO').find("tipoQuejaDTO").find('valor').text());
			$("#cQCalidadQuejoso").val($(xml).find('quejaCiudadanaDTO').find('calidadAfectado').find('valor').text());
			$('#cQNomInvolucradoCompara').val($(xml).find('quejaCiudadanaDTO').find('nombreDenunciado').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoPatDenunciado').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoMatDenunciado').text());
			$("#cQNumExpedienteCompara").val($(xml).find('quejaCiudadanaDTO').find('numeroExpediente').first().text());
			$("#cQCampoQueja").val($(xml).find('quejaCiudadanaDTO').find('descripcionQueja').text()); 

			var numExpediente = $(xml).find('quejaCiudadanaDTO').find('numeroExpediente').first().text();
			validaExpediente(numExpediente);
			
			var fecha = $(xml).find('quejaCiudadanaDTO').find('fechaRegistro').first().text();
			var hora = fecha.split(" ")[1];
			hora = hora.substring(0,5);
			fecha = fecha.split(" ")[0];						
									
			$("#cQFechaQueja").val(fecha.split("-")[2]+"/"+ fecha.split("-")[1]+"/"+fecha.split("-")[0]);
			  }
	    });
	
	}

	function validaExpediente(numExpediente){
		if(numExpediente.length < 7){
			$("#cQNumExpedienteComparaBoton").attr("disabled",true);	
			$("#cQNumExpedienteExiste").val("Información insuficiente para validar");
		}
	}

	/*
	*Funcion que dispara el Action para consultar el tipo de queja
	*/		
	function cargaMotivoQueja(){
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoRechazoQueja.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				var option;
				$(xml).find('catRechazoQueja').each(function(){
					$('#catRechazoQueja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	
	//Abre una nueva ventana de Denunciante
	function modificarDenuncianteDatos(id){
		idWindowIngresarDenunciante++;
		$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpediente +'" width="1040" height="570" />');		
	}
	
	function modificaDenunciante(id){
		modificarDenuncianteDatos(id);
	}
	
	function  consultadeExpedienteExistente(){
		var param="noExpediente="+$("#cQNumExpedienteCompara").val();
	
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/buscarExpedientePorNumeroDeExpedienteComparaExpediente.do',
			data:param,
			dataType: 'xml',
			async: false,
			success: function(xml){
				if($(xml).find('expedienteDTO').find('numeroExpediente').text() != ''){
						$('#cQNumExpedienteExiste').val("El expediente existe");		
				}else{
					$('#cQNumExpedienteExiste').val("El expediente no existe");		
				}
			}
		});
	}

	function  consultaFuncionarioExistente(){
		var param="nomFuncionario="+$("#cQNomInvolucradoCompara").val();
	
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/buscarFuncionarioPorNombre.do',
			data:param,
			dataType: 'xml',
			async: false,
			success: function(xml){
				if($(xml).find('funcionarioDTO').find('claveFuncionario').text() != ''){	
						$('#cQNomInvolucradoExiste').val("El funcionario es valido");		
				}else{			
					$('#cQNomInvolucradoExiste').val("El funcionario no es valido");		
				}
			}
		});
	}

	function ocultaCausas(){
		$('#tabsPrincipal').tabs("option", "disabled", [2]);
		$('#divRechazoQueja').show();
	}

	function muestraCausas(){
		$('#tabsPrincipal').tabs('enable', 2);
		$('#divRechazoQueja').hide();
	}

	function cerrarQueja(){
		var param="idQueja="+idQueja;
		
	    param+="&rechazoQueja="+$("#catRechazoQueja option:selected").val();
		
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/asignarMotivoRechazoQuejaCiudadana.do',
			data:param,
			dataType: 'xml',
			async: false,
			success: function(xml){
				alertDinamicoCerrar("La Queja Ciudadana fue cerrada correctamente");							
			}
		});
	}

	//Función para alertDinamicoCerrar
	function alertDinamicoCerrar(textoAlert){						
		$("#divAlertTextoCerrar").html(textoAlert);
	    $( "#dialog-AlertCerrar" ).dialog({
			autoOpen: true,
			resizable: false,
			modal: true,
			buttons: {				
				
				"Aceptar": function() {						
					window.parent.cerrarVentanaConsultaQueja();
					$( this ).dialog( "close" );
					$("#divAlertTextoCerrar").html("");					
				}				
			}
		});    
	 }

	function actuacionesQueja(){
		var formaId="";
		var opcion = $("#cQTipoRespuesta option:selected").val();
		if(opcion == "1"){
			formaId=<%=Formas.RESPUESTA_CIUDADANA.getValorId()%>;
		}else if(opcion == "2"){
			formaId=<%=Formas.OFICIO_SANCION_ADMINISTRATIVA.getValorId()%>;
		}
		if(formaId != ""){
			generarDocumentoSinCaso(formaId);
			formaId="";	
		}
	}

	 function generarDocumentoSinCaso(formaId) {
		 var titulo = "";
		 if(formaId == <%=Formas.RESPUESTA_CIUDADANA.getValorId()%>){
			 titulo = "Generar Respuesta al Ciudadano";
		 }else if(formaId == <%=Formas.OFICIO_SANCION_ADMINISTRATIVA.getValorId()%>){
			 titulo = "Sansión Administrativa";
		 }
		 var numExpediente="";
		 $.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:titulo, type:"iframe"});
	     $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?esconderArbol=0&formaId="+formaId+"&numeroUnicoExpediente="+numExpediente+"' width='1140' height='400' />");
	 }
	</script>
</head>
<body>
<!-- div para el alert dinamico antes de cerrar ventana -->
	<div id="dialog-AlertCerrar" style="display: none">
		<table align="center">
			<tr>
        		<td align="center">
            		<span id="divAlertTextoCerrar"></span>
            	</td>
        	</tr>
    	 </table>              
	</div>    

<table width="100%">
	<tr>
		<td align="right""><input class="back_button" type="button"
			onclick="guardaQejaCiudadana()" value="Guardar"
			style="display: none;"></td>
	</tr>
	<tr>
		<td>
		<div id="tabsPrincipal">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Datos de la Queja</a></li>
			<li><a href="#tabsconsultaprincipal-3">Confirmación de Datos</a></li>
			<li><a href="#tabsconsultaprincipal-4">Actuaciones</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
		<fieldset>
		<legend>Datos del Quejoso</legend>
		<table width="600" border="0">
			<tr align="right">
				<td width="165">Folio de la Queja:</td>
				<td width="160"><input name="" type="text" id="cQuejaNumero" /></td>
				<td width="153">Nombre del Quejoso</td>
				<td width="158"><input name="" type="text" id="cQNomQuejoso" /></td>
			</tr>
			<tr align="right">
				<td>Calidad del Afectado</td>
				<td><input name="" type="text" id="cQCalidadQuejoso" /></td>
				<td>Tipo de Queja</td>
				<td><input name="" type="text" id="cQTipoQueja" /></td>
			</tr>
			<tr align="right">
				<td>Fecha de Queja</td>
				<td><input name="" type="text" id="cQFechaQueja" /></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</fieldset>
			<fieldset>
			<legend>Queja</legend>
			<table>
				<tr>
					<td>
						<textarea cols="100" rows="15" id="cQCampoQueja" disabled="disabled">
						</textarea>
					</td>
				</tr>
			</table>
			</fieldset>
		</div>
		<div id="tabsconsultaprincipal-3">
		<table width="580" border="0">
			<tr align="right">
				<td width="183">Nombre del involucrado:</td>
				<td width="156"><input name="" type="text"
					id="cQNomInvolucradoCompara" /></td>

				<td width="133"><input name="" type="button"
					id="cQNumInvolucradoComparaBoton" value="Validar Funcionario"
					onclick="consultaFuncionarioExistente()" class="btn_Generico"/></td>
				<td><input type="text" id="cQNomInvolucradoExiste" size="30"/></td>
			</tr>
			<tr align="right">
				<td>Número de Expediente:</td>
				<td><input type="text" id="cQNumExpedienteCompara" /></td>
				<td><input type="button"
					id="cQNumExpedienteComparaBoton" value="Validar Expediente"
					onclick="consultadeExpedienteExistente()" class="btn_Generico"/></td>
				<td><input type="text" id="cQNumExpedienteExiste" size="30"/></td>
			</tr>
	        <tr>
	          <td  height="25" colspan="3" align="right">&iquest;La Queja Ciudadana prospera? 
	            <input type="radio" name="rbtQueja" id="quejaProsperaSi" value="1"/>
	            <label for="quejaProsperaSi2">Si</label>
	            <input type="radio" name="rbtQueja" id="quejaProsperaNo" value="0"/>
	            <label for="quejaProsperaNo2">No</label>
	          </td>
	        </tr>
	   </table>
	   <div id="divRechazoQueja">
			<table width="486" border="0">
				<tr>
					<td align="right">
						Motivo:
					</td>
					<td align="left">
						<select id="catRechazoQueja" style="width: 180px;">
						<option value="-1">- Seleccione -</option>
						</select>
					</td>
					<td>
						<input name="" type="button" id="btnCerrarQueja" value="Cerrar Queja" onclick="cerrarQueja(); " class="btn_Generico"/>
					</td>
				</tr>
			</table>
		</div>
		</div>

		<div id="tabsconsultaprincipal-4">
			<table width="200" border="0">
				<tr>
					<td>Actuaciones</td>
					<td><select id="cQTipoRespuesta" onchange="actuacionesQueja();">
						<option value="-1">- Seleccionar -</option>
						<option value="1">Respuesta a Ciudadano</option>
						<option value="2">Sansi&oacute;n Administrativa</option>
					</select></td>
				</tr>
			</table>
		</div>
		</div>
		</td>
	</tr>
</table>
</body>
</html>