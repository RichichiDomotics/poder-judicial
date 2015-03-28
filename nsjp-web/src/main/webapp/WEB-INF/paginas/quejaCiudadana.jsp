<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Queja Ciudadana</title>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
	
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	
	<script type="text/javascript">
	var solicitudId = '<%= request.getParameter("rowid")%>';
	var idqueja;
	var idindi;
	
	
	var idExpediente;
	var idExpediente2;
	var idWindowIngresarDenunciante=0;
	var respuestaQueja;
	var nombreQuejoso;
	var idQuejoso;
	
	$(document).ready(function() {
		idqueja ='<%= request.getParameter("idQueja")%>';

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
		$("#chkAfectado").click(espejoDatos);
		$("#chkAnonima").click(bloqueaDatos);

		cargaTipoQueja();
		//cargaCalidades();
	});
	
	  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	function espejoDatos(){
		if($("#chkAfectado").is(':checked')){
			$("#solDeAfectadoNombre").val($("#solDeQuejosoNombre").val());
			$("#solDeAfectadoAPaterno").val($("#solDeQuejosoAPaterno").val());
			$("#solDeAfectadoAMaterno").val($("#solDeQuejosoAMaterno").val());

			$("#solDeAfectadoNombre").attr("disabled","disabled");
			$("#solDeAfectadoAPaterno").attr("disabled","disabled");
			$("#solDeAfectadoAMaterno").attr("disabled","disabled");
		}else{
			$("#solDeAfectadoNombre").val("");
			$("#solDeAfectadoAPaterno").val("");
			$("#solDeAfectadoAMaterno").val("");

			$("#solDeAfectadoNombre").attr("disabled","");
			$("#solDeAfectadoAPaterno").attr("disabled","");
			$("#solDeAfectadoAMaterno").attr("disabled","");
		}
	}
   
	  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	function bloqueaDatos(){
		if($("#chkAnonima").is(':checked')){
			$("#solDeQuejosoNombre").val("Anónimo");
			$("#solDeQuejosoAPaterno").val(" ");
			$("#solDeQuejosoAMaterno").val(" ");
			
			$("#solDeAfectadoNombre").val("");
			$("#solDeAfectadoAPaterno").val("");
			$("#solDeAfectadoAMaterno").val("");

			$("#solDeQuejosoNombre").attr("disabled","disabled");
			$("#solDeQuejosoAPaterno").attr("disabled","disabled");
			$("#solDeQuejosoAMaterno").attr("disabled","disabled");

			$("#chkAfectado").attr("disabled","disabled");
		}else{
			$("#solDeQuejosoNombre").val("");
			$("#solDeQuejosoAPaterno").val("");
			$("#solDeQuejosoAMaterno").val("");

			$("#solDeQuejosoNombre").attr("disabled","");
			$("#solDeQuejosoAPaterno").attr("disabled","");
			$("#solDeQuejosoAMaterno").attr("disabled","");

			$("#chkAfectado").attr("disabled","");
		}
	}

	function guardaQejaCiudadana(){
		var calidad   = $("#catCalidad option:selected").val();
		var tipoQueja = $("#catTipoQueja option:selected").val();

		if(calidad != "0" && tipoQueja != "0"){
	  		var	param="numExpediente="+$('#solServPericialNumExpediente').val();

	  		param+="&quejaAnonima="+$("#chkAnonima").is(':checked');

		    param+="&nombreQuejoso="+$('#solDeQuejosoNombre').val();
	  		param+="&aPQuejoso="+$('#solDeQuejosoAPaterno').val();
	  		param+="&aMQuejoso="+$('#solDeQuejosoAMaterno').val();
	  		
		    param+="&nombreFuncionario="+$('#solDePericialNombre').val();
		    param+="&aPFuncionario="+$('#solDePericialAPaterno').val();
		    param+="&aMFuncionario="+$('#solDePericialAMaterno').val();

		    param+="&calidadAfectado="+$("#catCalidad option:selected").val();
		    param+="&nombreAfectado="+$('#solDeAfectadoNombre').val();
		    param+="&aPAfectado="+$('#solDeAfectadoAPaterno').val();
		    param+="&aMAfectado="+$('#solDeAfectadoAMaterno').val();
		    
		    param+="&tipoQueja="+$("#catTipoQueja option:selected").val();
		    param+="&descripcion="+$("#editor1").val();
		   
		    datosPestania = obtenerMedios();
			param += datosPestania;
		    	    
	    	$.ajax({
				type: 'POST',
	    	  	url:  '<%= request.getContextPath()%>/guardaQejaCiudadana.do',
	    	  	data:param,
	    	  	dataType: 'xml',
	    	  	async: false,
	    	  	success: function(xml){
			    	respuestaQueja=  $(xml).find('long').text();
		    		if(respuestaQueja!=""){
						customAlert("La Queja Ciudadana se Realizó con éxito");
						window.parent.cerrarVentanaQueja();
			    	}else{
			    			customAlert("No se logró guardar la queja ciudadana");
				    }
			  	}
		    });
		}else{
			customAlert("Los datos Tipo de Queja y Calidad del Afectado");
		}	
		
   	}
        
	/*
	*Funcion que dispara el Action para consultar los tipos de quejas
	*/		
	function cargaTipoQueja(){
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoTipoQueja.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				var option;
				$(xml).find('catTipoQueja').each(function(){
					$('#catTipoQueja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	</script>
</head>
<body>
	<table width="100%">
	<tr>
	<td align="right""><input class="back_button" type="button" onclick="guardaQejaCiudadana()" value="Guardar"> </td>
	</tr>
		<tr>
			<td>
				<div id="tabsPrincipal">
					<ul>
						<li><a href="#tabsconsultaprincipal-1">Datos Generales</a></li>
						<li><a href="#tabsconsultaprincipal-2">Datos de la Queja</a></li>
					</ul>
					<div id="tabsconsultaprincipal-1">
						<fieldset>
					    <legend>Datos del Quejoso</legend>
						<table width="100%">
							<tr align="center">
								<td align="center" colspan="2">
									¿La queja es An&oacute;nima?
								
								
		                            <input type="checkbox" id="chkAnonima"/>
								</td>
                                <td width="100%" rowspan="6">                           
									<jsp:include page="ingresarMediosContactoView.jsp"/>
								
                                </td>
							</tr>
							<tr>
								<td width="117" align="right"> 
									Nombre:
								</td>
								<td width="307">
									<input type="text" class="" size="50" maxlength="50" id="solDeQuejosoNombre" onKeyPress="return soloLetrasN(event);" />
								</td>
                            </tr>
							<tr>
								<td align="right">
									Apellido Paterno:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="solDeQuejosoAPaterno" onKeyPress="return soloLetrasN(event);" />
								</td>
                            </tr>
							<tr>
								<td align="right">
									Apellido Materno:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="solDeQuejosoAMaterno" onKeyPress="return soloLetrasN(event);" />
								</td>
                            </tr>
							<tr>
								<td align="center" colspan="2">
									¿Es el Afectado?
								
		                            <input type="checkbox" id="chkAfectado"/>
								</td>
                            </tr>
							
						</table>
						</fieldset>
				    	<table>
				    		<tr>
					    		<td align="center">&ensp;&ensp;&ensp; <textarea class="jquery_ckeditor" cols="140" id="editor1" name="editor1" rows="10" style="width:887px"></textarea></td>
					    	</tr>
					    </table>
					</div>
					<div id="tabsconsultaprincipal-2">
						<fieldset style="width: 700px;">
						<legend>Datos para Queja</legend>
						<table width="100%" border="0" height="90%">
							<tr>
								<td align="right">
									Tipo Queja:
								</td>
								<td align="left">
									<select id="catTipoQueja" style="width: 180px;">
									<option value="0">- Seleccione -</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									Número de Expediente:
								</td>
								<td align="left">
									<input type="text" class="" size="50" maxlength="50" id="solServPericialNumExpediente"  />
								</td>
							</tr>
							<tr>
								<td align="right">
									Nombre de Funcionario:
								</td>
								<td align="left">
									<input type="text" class="" size="50" maxlength="50" id="solDePericialNombre" onKeyPress="return soloLetrasN(event);" />
								</td>
							</tr>
	                        
	                        <tr>
								<td align="right">
									Apellido Paterno
								</td>
								<td align="left">
									<input type="text" class="" size="50" maxlength="50" id="solDePericialAPaterno" onKeyPress="return soloLetrasN(event);" /> 
								</td>
							</tr>
	                        
	                        <tr>
								<td align="right">
									Apellido Materno:
								</td>
								<td align="left">
									<input type="text" class="" size="50" maxlength="50" id="solDePericialAMaterno" onKeyPress="return soloLetrasN(event);" />
								</td>
							</tr>
							
						</table>
						</fieldset>
						
						<fieldset style="width: 700px;">
	                    
						<legend>Datos del Afectado</legend>
						<table>
							<tr>
								<td align="right">
									Calidad del Afectado:
								</td>
								<td align="left">
									<select id="catCalidad" style="width: 180px;">
									<option value="0">- Seleccione -</option>
									<option value="215">Denunciante</option>
									<option value="213"><bean:message key="probableResponsable"/></option>
									<option value="216">Testigo</option>
									<option value="214">V&iacute;ctima</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="232" align="right">
									Nombre:
								</td>
								<td width="463">
									<input type="text" class="" size="50" maxlength="50" id="solDeAfectadoNombre" onKeyPress="return soloLetrasN(event);" /> 
								</td>
							</tr>
							<tr>
								<td align="right">
									Apellido Paterno:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="solDeAfectadoAPaterno" onKeyPress="return soloLetrasN(event);" />
								</td>
							</tr>
							<tr>
								<td align="right">
									Apellido Materno:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="solDeAfectadoAMaterno" onKeyPress="return soloLetrasN(event);" />
								</td>
							</tr>
						</table>
						</fieldset>
					</div>
				</div>
	</table>
</body>
</html>