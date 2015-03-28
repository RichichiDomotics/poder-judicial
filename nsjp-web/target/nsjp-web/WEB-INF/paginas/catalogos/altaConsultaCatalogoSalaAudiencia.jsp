<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO"%>
<%@page import="mx.gob.segob.nsjp.web.catalogo.form.CatalogosForm"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.alerts.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>    
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
			
	<script type="text/javascript">
	<%	
		boolean esNuevo = false;
	
		Long claveSala = 0L;
		Long claveSalaJAVS = 0L;

		String ubicacion = "";
		String domicilio = "";
		String sala = "";
	
		String dirIP = "";
		String user = "";
		String ps = "";
	
		Long idTribunal = 0L;		

		Long idDistrito = 0L;
		
		Long claveFuncionario = 0L;	
		
		if(request.getAttribute("esNuevo") != null){
			esNuevo = (Boolean) request.getAttribute("esNuevo");
		}
		
		if (!esNuevo) {
			
			SalaAudienciaDTO salaAudiencia = new SalaAudienciaDTO();
			salaAudiencia = (SalaAudienciaDTO) request.getAttribute("SalaAudienciaDTO");
						
			if(salaAudiencia != null){
				
				if(salaAudiencia.getUbicacionSala() != null){
					ubicacion = salaAudiencia.getUbicacionSala();
				}
				
				if(salaAudiencia.getDomicilioSala() != null){
					domicilio = salaAudiencia.getDomicilioSala();
				}
				
				if(salaAudiencia.getNombreSala() != null){
					sala = salaAudiencia.getNombreSala();
				}
				
				if(salaAudiencia.getSalaAudienciaId() != null){
					claveSala = salaAudiencia.getSalaAudienciaId();
				}
				
				if(salaAudiencia.getSalaJAVSDTO()!=null){
					
					if(salaAudiencia.getSalaJAVSDTO().getSalaJAVSId() != null){
					 	claveSalaJAVS = salaAudiencia.getSalaJAVSDTO().getSalaJAVSId();
					}
					
					if(salaAudiencia.getSalaJAVSDTO().getDireccionIP() != null){
						dirIP = salaAudiencia.getSalaJAVSDTO().getDireccionIP();	
					}
					
					if(salaAudiencia.getSalaJAVSDTO().getPassword() != null){
						ps = salaAudiencia.getSalaJAVSDTO().getPassword();
					}
					
					if(salaAudiencia.getSalaJAVSDTO().getUsuarioJAVS() != null){
						user = salaAudiencia.getSalaJAVSDTO().getUsuarioJAVS();
					}
				}
				
				if(salaAudiencia.getCatDiscriminanteDTO() != null && salaAudiencia.getCatDiscriminanteDTO().getCatDiscriminanteId() != null){
					idTribunal = salaAudiencia.getCatDiscriminanteDTO().getCatDiscriminanteId();
				}
				
				if(salaAudiencia.getCatDiscriminanteDTO() != null && salaAudiencia.getCatDiscriminanteDTO().getDistrito() != null && salaAudiencia.getCatDiscriminanteDTO().getDistrito().getCatDistritoId() != null){
					idDistrito = salaAudiencia.getCatDiscriminanteDTO().getDistrito().getCatDistritoId();
				}
								
				
				if(salaAudiencia.getFuncionarioDTO()!=null && salaAudiencia.getFuncionarioDTO().getClaveFuncionario() != null){
					claveFuncionario = salaAudiencia.getFuncionarioDTO().getClaveFuncionario();
				}								
			}
			
		}

	%>
		
	$(document).ready(function() {
				
	<% if (esNuevo == false) { %>
		
		$("#btnModificar").show();
		$("#btnEliminar").show();
		$("#btnGuardar").hide();									
		
		ocultaCampos();
		cargaDistritos();
		cargaDatos();
		
	//Nuevo
	<% } else { %>
	
		$("#btnModificar").hide();
		$("#btnEliminar").hide();
		$("#btnGuardar").show();

		ocultaCampos();
		cargaDistritos();		
	
	<% } %>
	
	});	
	
	var estatusElimina = 0;	
	
	function modificaValor(){
		habilitaCampos();
		$("#btnModificar").hide();
		$("#btnEliminar").hide();
		$("#btnGuardar").show();
	}
	
	function eliminaValor(){
		estatusElimina = 1;
		alertEliminacion("Desea eliminar la sala de audiencia " + $("#nombreSala").val());		
	}
	
	function cargaDatos(){	
		
		$("#nombreSala").val("<%=sala%>");
		$("#domicilioSala").val("<%=domicilio%>");
		$("#ubicacionSala").val("<%=ubicacion%>");
				
		$('#distrito').find("option[value='"+'<%=idDistrito%>'+"']").attr("selected","selected");
		cargaTribunales();		
		$('#tribunal').find("option[value='"+'<%=idTribunal%>'+"']").attr("selected","selected");
		cargaFuncionarios();	
		$('#funcionario').find("option[value='"+'<%=claveFuncionario%>'+"']").attr("selected","selected");
		
		$("#dirJAVS").val("<%=dirIP%>");
		
		var existeSalaJAVS = "<%=claveSalaJAVS%>";
				
		if(existeSalaJAVS != 0 ){
	
			$("#userJAVS").val("<%=user%>"); 
			$("#psJAVS").val("<%=ps%>");
			$("#psJAVS1").val("<%=ps%>");
			
			$("#salaJAVS").attr('checked','checked');
	
			$("#labelDirJAVS").show();
			$("#labelUserJAVS").show();
			$("#labelPsJAVS").show();
			$("#labelPsJAVS1").show();
	
			$("#dirJAVS").show();
			$("#userJAVS").show();
			$("#psJAVS").show();
			$("#psJAVS1").show();
		}		
		
		deshabilitaCampos();
	}
	
	function deshabilitaCampos(){
		
		$("#nombreSala").attr("disabled","disabled");
		$("#domicilioSala").attr("disabled","disabled");
		$("#ubicacionSala").attr("disabled","disabled");
	
		$('#distrito').attr("disabled","disabled");
		$('#tribunal').attr("disabled","disabled");
		$('#funcionario').attr("disabled","disabled");
		
		$("#dirJAVS").attr("disabled","disabled");
		$("#userJAVS").attr("disabled","disabled");
		$("#psJAVS").attr("disabled","disabled");
		$("#psJAVS1").attr("disabled","disabled");		
		
		$("#salaJAVS").attr("disabled","disabled");
	}
	
	function habilitaCampos(){
		
		$("#nombreSala").attr("disabled","");
		$("#domicilioSala").attr("disabled","");
		$("#ubicacionSala").attr("disabled","");
	
		$('#distrito').attr("disabled","");
		$('#tribunal').attr("disabled","");
		$('#funcionario').attr("disabled","");
		
		$("#dirJAVS").attr("disabled","");
		$("#userJAVS").attr("disabled","");
		$("#psJAVS").attr("disabled","");
		$("#psJAVS1").attr("disabled","");
		
		$("#salaJAVS").attr("disabled","");
	}
	
	function ocultaCampos(){
		
		if($("#salaJAVS").is(':checked')){
		
			$("#labelDirJAVS").val("");
			$("#labelUserJAVS").val("");
			$("#labelPsJAVS").val("");
			$("#labelPsJAVS1").val("");
			
			$("#dirJAVS").val("");
			$("#userJAVS").val("");
			$("#psJAVS").val("");
			$("#psJAVS1").val("");
			
			$("#labelDirJAVS").show();
			$("#labelUserJAVS").show();
			$("#labelPsJAVS").show();
			$("#labelPsJAVS1").show();
			
			$("#dirJAVS").show();
			$("#userJAVS").show();
			$("#psJAVS").show();			
			$("#psJAVS1").show();
		}
		else{
			$("#labelDirJAVS").hide();
			$("#labelUserJAVS").hide();
			$("#labelPsJAVS").hide();
			$("#labelPsJAVS1").hide();
			
			$("#dirJAVS").val("");
			$("#userJAVS").val("");
			$("#psJAVS").val("");
			$("#psJAVS1").val("");
			$("#dirJAVS").hide();
			$("#userJAVS").hide();
			$("#psJAVS").hide();			
			$("#psJAVS1").hide();
		}
	}
	
	function cargaDistritos(){
			$.ajax({
				type: 'POST',
		    	url: '<%=request.getContextPath()%>/consultarDistritos.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
			    		$(xml).find('listaCatalogo').find('catDistritoDTO').each(function(){
							$('#distrito').append('<option value="' + $(this).find('catDistritoId').text() + '">' + $(this).find('claveDistrito').text()+" - "+ $(this).find('nombreDist').text() + '</option>');
						});					
				}
			});
	}
	
	function cargaTribunales(){
		var distrito=$("#distrito").val();
		var contTribunales=0;		
		
		limpiaComboTribunales();
	
		if(distrito!=-1){						
			$.ajax({
				type: 'POST',
		    	url: '<%=request.getContextPath()%>/consultarTribunalesXDistritoIdSinWS.do?distritoId='+distrito+'',
			    data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){		    		
			    	$(xml).find('CatDiscriminanteDTO').find('catDiscriminanteDTO').each(function(){
						$('#tribunal').append('<option value="' + $(this).find('catDiscriminanteId').text() + '">' + $(this).find('clave').text()+" - "+ $(this).find('nombre').text() + '</option>');
						contTribunales++;
					});
					if(contTribunales == 0){												
						alertDinamico("No existen tribunales asociados a este distrito");
					}
				}
			});
		}
		
	}
	
	function limpiaComboTribunales(){
		$('#tribunal').empty();
		$('#tribunal').append('<option value="-1">-Seleccione-</option>');
		limpiaComboFuncionarios();
	}
	
	function cargaFuncionarios(){
		var tribunal=$("#tribunal").val();
		var contFuncionarios=0;
		
		limpiaComboFuncionarios();
			
		if(tribunal!=-1){
			$.ajax({
				type: 'POST',
			    url: '<%=request.getContextPath()%>/consultarDestinararioXTribunalSinWS.do?tribunal='+tribunal+'',
			    data: '',
			    dataType: 'xml',
			    async: false,
			    success: function(xml){			    	
			    	$(xml).find('linked-list').find('funcionarioDTO').each(function(){			    		
			    		var nombre = $(this).find('nombreFuncionario').text();
			    		var apPaterno = $(this).find('apellidoPaternoFuncionario').text();
			    		var apMaterno = $(this).find('apellidoMaternoFuncionario').text();
			    		$('#funcionario').append('<option value="' + $(this).find('claveFuncionario').text() + '">' + nombre + " " + apPaterno + " " + apMaterno + '</option>');
						contFuncionarios++;
					});
					if(contFuncionarios == 0){												
						alertDinamico("No existen funcionarios asociados a este tribunal");
					}
				}
			});
		}		
	}
	
	function limpiaComboFuncionarios(){
		$('#funcionario').empty();
		$('#funcionario').append('<option value="-1">-Seleccione-</option>');	
	}

	function validaCampos(){
			
		if($("#nombreSala").val() == "" || $("#domicilioSala").val() == "" || $("#ubicacionSala").val() == ""){
			alertDinamico("Es indispensable capturar los campos que están marcados con asterisco");
			return 0;
		}
		else{
			var distrito = $("#distrito").val();
			var tribunal = $("#tribunal").val();
			var funcionario = $("#funcionario").val();
			
			if(distrito == -1 || tribunal == -1 || funcionario == -1){
				var campos = "";
				
				if(distrito == -1){
					campos = "Distrito"
				}
				if(tribunal == -1){
					if(campos == ""){
						campos = "Tribunal";
					}
					else{
						campos = campos + ", Tribunal";
					}
				}
				if(funcionario == -1){
					if(campos == ""){
						campos = "Funcionario";
					}
					else{
						campos = campos +", Funcionario";
					}
				}
				alertDinamico("Favor de verificar que tenga una selección valida los siguientes campos: " + campos);
				return 0;
			}				
		}	
		
		if($("#salaJAVS").is(':checked')){					
			if($("#dirJAVS").val() == "" || $("#userJAVS").val() == "" || $("#psJAVS").val() == "" || $("#psJAVS1").val() == ""){
				alertDinamico("Es indispensable capturar los campos que están marcados con asterisco");
				return 0;
			}
			else{
				
				if($("#psJAVS").val() != $("#psJAVS1").val()){
					alertDinamico("Favor de verificar la contraseña proporcionada");
					return 0;					
				}
				
				var ipCorrecta = validaDireccionIP($("#dirJAVS").val());
				if(ipCorrecta==0){
					alertDinamico("Favor de verificar el formato de la dirección IP (XXX.XXX.XXX.XXX)");
				}
				return ipCorrecta;
			}
		}				
		
		return 1;
	}	
	
	function validaDireccionIP(inputString) {
		var re = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;

		if (re.test(inputString)) {
		 
		    var parts = inputString.split(".");
		    if(parts.length!=4){
		    	return 0;
		    }
		    else{
		    	for (var i=0; i<parts.length; i++) {
		    		if (parseInt(parseFloat(parts[i])) == 0) {
				    	  return 0;
					}
		    		if (parseInt(parseFloat(parts[i])) > 255 || parseInt(parseFloat(parts[i])) < 0) {
				        return 0;
				    }
		    	}
		    }
		}		
		else{
			return 0;
		}
		
		return 1;
	}
		 
	function guardaValor(){
		
		var permisible=1;
		
		if(estatusElimina != 1){
			permisible = validaCampos();
		}
		
		if(permisible != 0){
			var params = '';
			
			params += "&claveSala=" 	+ "<%=claveSala%>";
			
			params += "&nombreSala="    + $("#nombreSala").val();
			params += "&domicilioSala=" + $("#domicilioSala").val();
			params += "&ubicacionSala=" + $("#ubicacionSala").val(); 
			params += "&tribunal="      + $("#tribunal").val();
			params += "&funcionario="   + $("#funcionario").val();				
			params += "&claveSalaJAVS=" + "<%=claveSalaJAVS%>";

			if($("#salaJAVS").is(':checked')){
				params += "&direccionJAVS=" + $("#dirJAVS").val();
				params += "&usuarioJAVS="   + $("#userJAVS").val();
				params += "&passwordJAVS="  + $("#psJAVS").val();								
			}

			if(estatusElimina != 1){
				params += "&eliminaSala=" + "NO";
			}
			else{
				params += "&eliminaSala=" + "SI";
			}
									
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/GuardarValorEnCatalogoSalaAudiencia.do',
				data: 'params='+params,
				dataType: 'xml',
				async: false,
				success: function(xml){
					var valorRetorno=$(xml).find("long").text();
					if(valorRetorno != 0){
						if(estatusElimina == 1){
							alertSincronoGuardado("La sala de audiencia ha sido eliminada correctamente");
						}
						else{
							<% if (esNuevo == false) { %>
								alertSincronoGuardado("La sala de audiencia ha sido modificada correctamente");							
							<% } else { %>
								alertSincronoGuardado("La sala de audiencia ha sido agregada correctamente");
							<% }  %>
						}
					}
					else{
						if(estatusElimina == 1){
							alertSincronoGuardado("La sala de audiencia no ha sido eliminada, ocurrió un error");
						}
						else{
							<% if (esNuevo == false) { %>
								alertSincronoGuardado("La sala de audiencia no ha sido registrada, ocurrió un error");
							<% } else { %>
								alertSincronoGuardado("La sala de audiencia no ha sido modificada, ocurrió un error");
							<% }  %>
						}
					}
				}
			});
		}
	}

  		
	/*
	*Funcion que despliega una ventana modal para indicar 
	*que el delito fue guardado exitosamente, e invocar a la 
	*funcion del padre(administrarCatalogos.jsp) que cierra la
	*ventana y recarga el grid de delitos 
	*/
	function alertSincronoGuardado(mensaje){

		$('#spanAlert').html(mensaje);
		
		$("#alertSincro").dialog("open");
		$("#alertSincro").dialog({
			autoOpen: true, 
			modal: true, 
			title: '', 
			dialogClass: 'alert',
			position: [250,100],
		  	height: 120,
		  	width: 400,
		  	maxWidth: 400,
		  	maxHeigth:120,
			buttons: {"Aceptar":function() {$( this ).dialog( "close" );$("#spanAlert").html("");parent.cerrarVentanaVerValor();}} 
		});
	}

	function alertEliminacion(mensaje){

		$('#spanAlert').html(mensaje);
		
		$("#alertSincro").dialog("open");
		$("#alertSincro").dialog({
			autoOpen: true, 
			modal: true, 
			title: '', 
			dialogClass: 'alert',
			position: [250,100],
		  	height: 120,
		  	width: 400,
		  	maxWidth: 400,
		  	maxHeigth:120,
			buttons: {"Eliminar":function() {$( this ).dialog( "close" );$("#spanAlert").html("");guardaValor();},
					  "Cancelar":function() {$( this ).dialog( "close" );$("#spanAlert").html("");}} 
		});
	}
	
</script>
</head>
<body>
	<table width="900" border="0">
		<tr><td>&nbsp;</td></tr>
		<tr>
	    	<td colspan="4" align="center">
	    		<font FACE="arial" SIZE=2>
					<b>Datos de la sala de audiencia</b>
				</font>
	    	</td>	    		    	
	  	</tr>	  		
	  	<tr><td>&nbsp;</td></tr>
	  	<tr>
	    	<td>
	    		<div align="center">* Nombre de la sala:</div>
	    	</td>
	    	<td>
	    		<input type="text" id="nombreSala" style="width:260px" tabindex="1" maxlength="100"/>
	    	</td>
	    	<td align="right">
	  			<div align="center">Distrito:</div> 
		 	</td>		 		
		 	<td>
				<select id="distrito" onchange="cargaTribunales();" style="width: 250px;">
					<option value="-1">- Seleccione -</option>					
				</select>
			</td>	    
	  	</tr>
	  	<tr>
	    	<td>
	    		<div align="center">* Domicilio de la sala:</div>
	    	</td>
	    	<td>
	    		<input type="text" id="domicilioSala" style="width:260px" tabindex="1" maxlength="150"/>
	    	</td>
			<td align="right">
	  			<div align="center">Tribunal:</div> 
		 	</td>		 		
		 	<td>
				<select id="tribunal" onchange="cargaFuncionarios();"  style="width: 250px;">
					<option value="-1">- Seleccione -</option>
				</select>
			</td>	    	    	
	  	</tr>
	  	<tr>
	    	<td>
	    		<div align="center">* Ubicación de la sala:</div>
	    	</td>
	    	<td>
	    		<input type="text" id="ubicacionSala" style="width:260px" tabindex="1" maxlength="100"/>
	    	</td>
	    	<td align="right">
	  			<div align="center">Encargado:</div> 
		 	</td>		 		
		 	<td>
				<select id="funcionario" style="width: 250px;">
					<option value="-1">- Seleccione -</option>
				</select>
			</td>	    	    		    	
	  	</tr>	  	
	  	<tr>
	  		<td></td>
	  		<td>
		    	<div align="right">Sala JAVS
		    		<input id="salaJAVS" type="checkbox" onchange="ocultaCampos();">
		    	</div>
	    	</td>	    	    
	  	</tr>
	  	<tr><td>&nbsp;</td></tr>
		<tr>
	    	<td>
		    	<div align="center" id="labelDirJAVS">* Dirección IP - Servidor JAVS:</div>
	    	</td>
	    	<td>
	    		<input type="text" id="dirJAVS" style="width:150px" tabindex="1" maxlength="20"/>
	    	</td>
	    	<td>
		    	<div align="center" id="labelPsJAVS">* Contraseña del usuario:</div>
	    	</td>
	    	<td>
	    		<input type="password" id="psJAVS" style="width:150px" tabindex="1" maxlength="20"/>
	    	</td>	    	    	   	    		    		        	    	    	    	    		    	    	   
	  	</tr>
	  	<tr>
	  		<td>
		    	<div align="center" id="labelUserJAVS">* Usuario JAVS:</div>
	    	</td>
	    	<td>
	    		<input type="text" id="userJAVS" style="width:150px" tabindex="1" maxlength="20"/>
	    	</td>
	  		<td>
		    	<div align="center" id="labelPsJAVS1">* Repetir Contraseña:</div>
	    	</td>
	    	<td>
	    		<input type="password" id="psJAVS1" style="width:150px" tabindex="1" maxlength="20"/>
	    	</td>	    	    	   
	  	</tr>  		  	  
	  	<tr><td>&nbsp;</td></tr>
	  	<tr>	  		
			<td align="center" colspan="4">
				<input type="button" onclick="modificaValor()" id="btnModificar" value="Modificar" class="btn_Generico"/>
				<input type="button" onclick="guardaValor()" 	id="btnGuardar" value="Guardar" class="btn_Generico"/>
				<input type="button" onclick="eliminaValor()" id="btnEliminar" value="Eliminar" class="btn_Generico"/>
			</td>
		</tr>	  	
	</table>
	
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
	
	<!-- div para el alert sincrono -->
	<div id="alertSincro" style="display: none">
		<table>
			<tr>
				<td>
					<label id=spanAlert></label>
				</td>
			</tr>
		</table>
	</div>   

</body>
</html>