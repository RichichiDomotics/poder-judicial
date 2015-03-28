<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Funcionario</title>

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
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>


	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>


<script type="text/javascript">

var contextoPagina = "${pageContext.request.contextPath}";

var idindi;
	var id="";
	$(document).ready(function() {
		id='<%=request.getParameter("rowid")%>';
	 	jQuery(document).ajaxStop(jQuery.unblockUI); 

		//Se crean las tabs principales
		$("#tabsprincipalconsulta" ).tabs();
		$("#datosDomicilioDefensorExterno").hide();
		cargaTipoEspecialidad();
		
		consultarDatosFuncionario(id);
	});
	
	/**
	 *Limpia todos los campos de esta página
	 */
	function consultarDatosFuncionario(id){
		bloquearPantalla();
	   	$.ajax({
		     type: 'POST',
		     url: '<%= request.getContextPath()%>/buscarFuncionarioPorId.do',
			 data: 'funcionarioId='+id,
			 dataType: 'xml',
			 success: function(xml){
				$('#datosGeneralesCmpApaterno').val($(xml).find('apellidoPaternoFuncionario').first().text());
				$('#datosGeneralesCmpMaterno').val($(xml).find('apellidoMaternoFuncionario').first().text());
				$('#datosGeneralesCmpNombres').val($(xml).find('nombreFuncionario').first().text());
			 
				var tipoEspecialidad=$(xml).find('tipoEspecialidad').find('idCampo').first().text();
				$('#datosGeneralesCmpTipoEspecialidad').find("option[value='"+tipoEspecialidad+"']").attr("selected","selected");
				onSelectChangeTipoEspecialidad();

				var especialidad=$(xml).find('especialidad').find('idCampo').first().text();
				$('#datosGeneralesCmpEspecialidad').find("option[value='"+especialidad+"']").attr("selected","selected");

				if($(xml).find('especialidad').find('valor').first().text() == "Externa"){
					//$('#datosDomicilioCmpNombreCalle').val($(xml).find('apellidoPaternoFuncionario').text());
					//$('#datosDomicilioCmpNumero').val($(xml).find('apellidoMaternoFuncionario').text());
					//$('#datosDomicilioCmpColonia').val($(xml).find('nombreFuncionario').text());
					//muestraInformacionDomicilio();
				}

				$('#tblCorreos').append('<tr><td>'+$(xml).find('email').first().text()+'</td></tr>');
				 pintaDatosContacto(xml);
				 idindi=id;
				 //mediosContactoTelefonoActualiza();
				 //mediosContactoCorreoActualiza();
				 disparaConsultaGridsMediosDeContactoFuncionario(id);
				 bloqueaCampos();
			  }
			});
	}

	function bloqueaCampos(){
		$('#btnAgregarCorreo').attr("disabled", "disabled");
		$('#btnEliminarCorreo').attr("disabled", "disabled");
		$('#btnAgregarTelefono').attr("disabled", "disabled");
		$('#btnEliminarTelefono').attr("disabled", "disabled");

		$('#datosGeneralesCmpTipoEspecialidad').attr("disabled", "disabled");
		$('#datosGeneralesCmpEspecialidad').attr("disabled", "disabled");
		$('#botonGuardar').attr("disabled", "disabled");
		bloqueaCamposMediosDeContactoGrid();
	}

	function muestraInformacionDomicilio(){
		$('#datosDomicilioDefensorExterno').show();
	}

	function recuperaDatosFuncionario()
	{
		   var lsDatosGenerales="";
		   var mediosContacto = obtenerMedios();
		   
		   lsDatosGenerales+="tipoEspecialidad="+$("#datosGeneralesCmpTipoEspecialidad option:selected").val();
		   lsDatosGenerales+="&especialidad="+$("#datosGeneralesCmpEspecialidad option:selected").val();
		   lsDatosGenerales+=mediosContacto;
	
		   return lsDatosGenerales;
	}

	function modificarDatosFuncionario(){
		$('#datosGeneralesCmpTipoEspecialidad').attr("disabled", "");
		$('#datosGeneralesCmpEspecialidad').attr("disabled", "");
		$('#botonGuardar').attr("disabled", "");
		 desbloqueaCamposMediosDeContactoGrid();
		cambiarTab('#tabsconsultaprincipal-1');
	}

	function guardarModificarDatosFuncionario(){
		var params = recuperaDatosFuncionario();
		bloquearPantalla();
		$.ajax({								
		  	  type: 'POST',
		  	  url: '<%= request.getContextPath()%>/modificarFuncionario.do?funcionarioId='+id+'',
		  	  data: params,				
		  	  dataType: 'xml',
		  	  success: function(xml){
			  	  window.parent.cargaGridFuncionarios();
		  		  customAlert('Funcionario actualizado');
		  	  }
		  	});
	}

	function cerrarVentanaConsulta(){
		window.parent.cerrarVentanaConsultaFuncionario();
	}

	/*
	*Funcion que dispara el Action para consultar Tipo Especialidad
	*/	
	function cargaTipoEspecialidad(){
		bloquearPantalla();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTipoEspecialidad.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var option;
				$(xml).find('tipoEspecialidad').each(function(){
					$('#datosGeneralesCmpTipoEspecialidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	/*
	*Funcion que dispara el Action para consultar Especialidad
	*/	
	function onSelectChangeTipoEspecialidad() {
		  
		var selected = $("#datosGeneralesCmpTipoEspecialidad option:selected").val();

		$.ajax({
			async: false,									// la accion cargar las especialidades
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoEspecialidad.do?tipoEspecialidadId='+selected+'',
			dataType: 'xml',
			success: function(xml){
				$("#datosGeneralesCmpEspecialidad").empty();
				$("#datosGeneralesCmpEspecialidad").append('<option value="-1">- Seleccione -</option>');
				$(xml).find('especialidad').each(function(){
					$("#datosGeneralesCmpEspecialidad").append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	function cambiarTab(tabId){
		jQuery("#tabsprincipalconsulta").tabs("select", tabId);
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
			<li><a href="#tabsconsultaprincipal-1">Datos Generales</a></li>
			<li><a href="#tabsconsultaprincipal-2">Medios de Contacto</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
		    <table border="0" width="100%">
				<tr>
					<td  align="right">*Nombre(s):</td>
					<td>
						<input type="text" class="" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNombres" disabled="disabled"/>
					</td>
					<td align="right">*Apellido Paterno:</td>
					<td>
						<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpApaterno" disabled="disabled"/>
					</td>
					<td align="right">*Apellido Materno:</td>
					<td>
						<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpMaterno" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td  align="right">Tipo Defensa:</td>
					<td>
						<select id="datosGeneralesCmpTipoEspecialidad" onchange="onSelectChangeTipoEspecialidad();" style="width: 182px;">
							<option value="">- Seleccione -</option>
						</select>
				    </td>
					<td align="right">Especialidad:</td>
					<td>
						<select id="datosGeneralesCmpEspecialidad" style="width: 182px;">
							<option value="">- Seleccione -</option>
						</select>
				    </td>
					<td align="right" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td align="right" colspan="6">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="datosDomicilioDefensorExterno">
							<table width="100%">
								<tr>
									<td align="right">Calle:</td>
									<td>
										<input type="text" class="" style="width: 180px;" maxlength="30" id="datosDomicilioCmpNombreCalle" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td align="right">N&uacute;mero:</td>
									<td>
										<input type="text" style="width: 180px;" maxlength="30" class="" id="datosDomicilioCmpNumero" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td align="right">Colonia:</td>
									<td>
										<input type="text" style="width: 180px;" maxlength="30" class="" id="datosDomicilioCmpColonia" disabled="disabled"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<button id="btnContinuar"  type="button" class="btn_Generico" onclick="cambiarTab('#tabsconsultaprincipal-2');">Continuar</button>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="tabsconsultaprincipal-2">
			<table width="762px" height="313px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td>
						<jsp:include page="ingresarMediosContactoView.jsp"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div id="boton2">
							<input type="button" value="Modificar"  id="botonModifica" class="btn_Generico" onclick="modificarDatosFuncionario();"/>
							<!--<input type="button" value="Cerrar"  id="botonCerrar" class="btn_Generico" onclick="cerrarVentanaConsulta();"/>
							--><input type="button" value="Guardar"  id="botonGuardar" class="btn_Generico" onclick="guardarModificarDatosFuncionario();"/>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>
