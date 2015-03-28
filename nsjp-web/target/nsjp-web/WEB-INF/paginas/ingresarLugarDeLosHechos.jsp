<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Lugar de los Hechos</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<script type="text/javascript">
	var idindi;
	var unidadInvestigacion='';
	var numeroExpediente = "";
	$(document).ready(function() {
		//generarExpedienteSSP();
		cargaDelitos();

		//Se crean las tabs principales
		$( "#tabs" ).tabs();
		$("#tabsprincipalconsulta" ).tabs();

		$("#chkAnonima").click(bloqueaDatos);
		deshabilitaFuncionalidadDomicilio();
	
	});

	  //Funcion que refleja los datos de nombre, apellido paterno, apellido materno a la ventana padre
	function bloqueaDatos(){
		if($("#chkAnonima").is(':checked')){
			$("#solicitanteNombre").val("Anónimo");
			$("#solicitanteAPaterno").val("");
			$("#solicitanteAMaterno").val("");
			
			$("#solicitanteNombre").attr("disabled","disabled");
			$("#solicitanteAPaterno").attr("disabled","disabled");
			$("#solicitanteAMaterno").attr("disabled","disabled");

		}else{
			$("#solicitanteNombre").val("");
			$("#solicitanteAPaterno").val("");
			$("#solicitanteAMaterno").val("");

			$("#solicitanteNombre").attr("disabled","");
			$("#solicitanteAPaterno").attr("disabled","");
			$("#solicitanteAMaterno").attr("disabled","");
		}
	}

	function generarExpedienteSSP(){
		$.ajax({								
		  	  type: 'POST',
		  	  url: '<%= request.getContextPath()%>/generarExpedienteSSP.do',
		  	  data:'',				
		  	  dataType: 'xml',
		  	  success: function(xml){
		 		$(xml).find('expedienteDTO').each(function(){
		 			 numeroExpediente = $(xml).find('numeroExpediente').text();
				});
		  	  }
	  	});
	}

	/*
	*Funcion que dispara ajusta funcionalidad de domicilio para este caso
	*/	
	function deshabilitaFuncionalidadDomicilio(){
		killDomicilioNotificaciones();
		killCoordenadasGeograficas();
	}

	/*
	*Funcion que dispara el Action para consultar Detalle de delito
	*/	
	function consultarDatosCoordinador() {
		var selected = $("#chkDelito option:selected").index();
		if(unidadInvestigacion != ''){
			var unidad = unidadInvestigacion.split(",");
			if(unidad[selected -1] != "" && unidad[selected -1] !=null){
				$( "#datosGeneralesCmpUnidad" ).val(unidad[selected -1]);
				$( "#datosGeneralesCmpResponsable" ).val("Coordinador de la unidad");
			}else{
				$( "#datosGeneralesCmpUnidad" ).val("");
				$( "#datosGeneralesCmpResponsable" ).val("");
			}
		}
	}
	
	/*
	*Funcion que dispara el Action para consultar Delitos
	*/	
	function cargaDelitos(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoDelitos.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('delitos').each(function(){
					$('#chkDelito').append('<option value="' + $(this).find('claveDelito').text() + '">'+ $(this).find('nombre').last().text() + '</option>');
					
					if($(this).find('departamento').text()){
						unidadInvestigacion+=$(this).find('departamento').find('nombreDepto').text()+',';
					}else{
						unidadInvestigacion+=$(this).find('institucion').find('nombre').last().text()+',';
					}
				});
			}
		});
	}
	
	function recuperaDatosGenerales()
	{
		   var lsDatosGenerales="";
		   
		   lsDatosGenerales+="delito="+$("#chkDelito option:selected").val();
		   lsDatosGenerales+="&unidadDelito="+$('#datosGeneralesCmpUnidad').val();
		   lsDatosGenerales+="&responsableUnidad="+$('#datosGeneralesCmpResponsable').val();

		   lsDatosGenerales+="&llamadaAnonima="+$("#chkAnonima").is(':checked');

		   lsDatosGenerales+="&calidadImplicado="+$("#chkIngresarLugarCmpCalidad option:selected").val();
		   lsDatosGenerales+="&implicadoNombre="+$('#solicitanteNombre').val();
		   lsDatosGenerales+="&implicadoAPaterno="+$('#solicitanteAPaterno').val();
		   lsDatosGenerales+="&implicadoAMaterno="+$('#solicitanteAMaterno').val();
		   
		   var datosMedios = obtenerMedios();
		   lsDatosGenerales += datosMedios;
		   
		   var domicilio = obtenerParametrosDomicilio();
		   lsDatosGenerales+=domicilio;
		   
		   return lsDatosGenerales;
	}

	function validaDatosGenerales()
	{
		   if(!$("#chkAnonima").is(':checked')){
				if(($('#solicitanteNombre').val()=="") && ($('#solicitanteAPaterno').val()=="") && ($('#solicitanteAMaterno').val()=="")){
					customAlert("Favor de capturar la información del solicitante");
					return false;
				}
		   }
		   
		   if($("#chkIngresarLugarCmpCalidad option:selected").val() == -1){
				customAlert("Favor de capturar la calidad del solicitante");
				return false;
		   }
		   
		   if($("#chkDelito option:selected").val() == -1){
				customAlert("Favor de capturar el delito");
				return false;
		   }
		   
		   return true;
	}

	function guardarDatosLugar(){
		if(validaDatosGenerales()==true){
			var params = recuperaDatosGenerales();
			$.ajax({
		  		  type: 'POST',
		  	  	  url: '<%= request.getContextPath()%>/guardarLugarDeLosHechos.do',
		  	  	  data: params,				
		  	      dataType: 'xml',
		  	   	  success: function(xml){
            		$(xml).find('avisoHechoDTO').each(function(){
	            			if($(this).find('documentoId').text() != 0){
	            				customAlert('Solicitud de apoyo guardada',"",window.parent.cerrarVentanaLugarDeHechos);	            				
	            			}
	            			else{
	        		  		  	customAlert('Error al registrar la solicitud de apoyo');	        		  		  		            				
	            			}
            		});
		  	  	  }
		   });
		}	
	}
	
</script>

</head>

<body>
    <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>



	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Reporte</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Ingresar lugar de los hechos</a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-1">
			<fieldset>
		    <legend>Solicitante</legend>
			<table width="100%">
				<tr align="center">
					<td align="center" colspan="2">
						¿La llamada es an&oacute;nima?
	                    <input type="checkbox" id="chkAnonima"/>
					</td>
                    <td width="100%" rowspan="8">                           
						<jsp:include page="ingresarMediosContactoView.jsp"/>
                    </td>
				</tr>
			    <tr>
				    <td align="right">Calidad del solicitante:</td>
				    <td align="left">
					    <select id="chkIngresarLugarCmpCalidad" style="width: 290px;">
						<option value="-1">- Seleccione -</option>
						<option value="215">Denunciante</option>
						<option value="216">Testigo</option>
						<option value="214">V&iacute;ctima</option>
					    </select>
			        </td>
			    </tr>
				<tr>
					<td align="right">Delito:</td>
					<td align="left">
						<select id="chkDelito"  style="width: 290px;" onchange="consultarDatosCoordinador();">
							<option value="-1">- Seleccione -</option>
						</select>
	  			    </td>
				</tr>
				<tr>
					<td  align="right">Unidad de investigación:</td>
					<td>
						<input type="text" size="50" maxlength="30" id="datosGeneralesCmpUnidad" name="datosGeneralesCmpUnidad" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="right">Responsable de la Unidad:</td>
					<td>
						<input type="text" size="50" maxlength="30" id="datosGeneralesCmpResponsable" name="datosGeneralesCmpResponsable" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="right"> 
						Nombre(s):
					</td>
					<td>
						<input type="text" size="50" maxlength="50" id="solicitanteNombre" onkeypress="return soloLetrasDos(event);" />
					</td>
                         </tr>
				<tr>
					<td align="right">
						Apellido paterno:
					</td>
					<td>
						<input type="text" class="" size="50" maxlength="50" id="solicitanteAPaterno" onkeypress="return soloLetrasDos(event);" />
					</td>
                         </tr>
				<tr>
					<td align="right">
						Apellido materno:
					</td>
					<td>
						<input type="text" class="" size="50" maxlength="50" id="solicitanteAMaterno"  onkeypress="return soloLetrasDos(event);"/>
					</td>
                </tr>
				<tr>
				    <td colspan="8" align="center">
					    <div id="boton2"><input type="button" value="Enviar"  id="botonGuarda" onclick="guardarDatosLugar();" class="btn_Generico"/></div>
					</td>
				</tr>
			</table>
			</fieldset>
		</div>
		
		<div id="tabsconsultaprincipal-2">
			<table width="762px" height="313px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td>
						<jsp:include page="ingresarDomicilioView.jsp"/>
					</td>
				</tr>
			</table>
		</div>

	</div>

</body>
</html>
