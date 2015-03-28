<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Numerario</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>  

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
	  
	var idNumerario="";
	var tipoObjeto="";
	var numeroExpediente="";
	var cadenaCustodia="";
	var fechaLevCadCus="";
	var origenEvdCadCus="";
	var deshabilitarCampos = window.parent.deshabilitarCamposPM;
	var ocultaAnularObjetoCadCus=null;
	
	var contextoPagina = "${pageContext.request.contextPath}";
	
	jQuery().ready(
			
		function () {
			//Instruccion pensada solo para el caso de policia ministerial
			if(deshabilitarCampos == true){
				$(":enabled").attr('disabled','disabled');
			}	
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idNumerario='<%= request.getParameter("idNumerario")%>';
			cadenaCustodia='<%= request.getParameter("cadenaCustodia")%>';
			fechaLevCadCus='<%= request.getParameter("fechaLevCadCus")%>';
			origenEvdCadCus='<%= request.getParameter("origenEvdCadCus")%>';
			
			//lineas para ocultar la opcion de anular objeto alconsultar desde una cadena de custodia
			ocultaAnularObjetoCadCus='<%= request.getParameter("anularConsultaCadCus")%>';
			if(ocultaAnularObjetoCadCus!=null && ocultaAnularObjetoCadCus!=0)
			{
				$("#anularElemento").hide("");
				$("#anularInv").hide("");
			}
			
			if (idNumerario != null && idNumerario != 0)
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idNumerario")%>');
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			if(idNumerario=='null')
			{
				idNumerario=0;	
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			
			$("#idFechaDate").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			cargaCondicion();
			var num=parent.num;
			//revisamos si es una consulta
			if(idNumerario!=null && idNumerario!=0)
			{
				consultaNumerario();
				//condicional para no mostrar el boton de anular objeto cuando entramos desde cadena de custodia
				if(ocultaAnularObjetoCadCus!='null' && ocultaAnularObjetoCadCus!=0)
				{
					//alert("1");
					$("#anularElemento").hide("");
					$("#anularInv").hide("");
				}
				else
				{
					//alert("2");
					$("#anularElemento").show("");
					$("#anularInv").show("");
				}
			}
			else
			{
				if(num!=null && num!="0"){
					$("#anularElemento").hide();
					$("#anularInv").hide();
				}
			}
	});	
	
	/*
	*Funcion para mandar consultar el numerario
	*/
	function consultaNumerario()
	{
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idNumerario,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				//seteamos la informacion del hecho
    				$(xml).find('NumerarioDTO').each(function(){
    							seteaDatosNumerario($(this));
		    	      });
	    		}
    		}	
    	});
	}
	
	function seteaDatosNumerario(xml)
	{
		$('#txtCantidadNumerario').val($(xml).find('cantidad').text());
		$('#txtMonedaNumerario').val($(xml).find('moneda').text());
		$('#txtCuentaTesoreriaNumerario').val($(xml).find('ctaTesoreria').text());
		
		//obtenemos la fecha y hora y mandamos setear los campos con sus respectivos valores
		var fechaBD=$(xml).find('fechaDeposito').text();
		seteaFechaHoraEnCamposPlugin(fechaBD,'idFechaDate','idHoraDate');
		//$('#idFechaDate').val($(xml).find('fechaDeposito').text());
		//$('#idHoraDate').val($(xml).find('fechaDeposito').text());
		$('#cbxCondicionNumerario').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");
		if($(xml).find('almacen'))
			$(xml).find('almacen').remove();
		$('#txtBoxDescNumerario').val($(xml).find('descripcion').text());
	}

		
	function customRange(input) {
		return {minTime: (input.id == 'idHoraDateLapsoFin' ?
			$('#idHoraDate').timeEntry('getTime') : null),
			maxTime: (input.id == 'idHoraDate' ?
			$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
	}

	
	$(function(){
		$('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
	});

	     
	/*
	*Funcion que realiza la carga de la condicion del numerario
	*/
	function cargaCondicion() {
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCondicion.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catCondicion').each(function(){
					$('#cbxCondicionNumerario').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}	

	//COMIENZA FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresNumerario()
	{
		var paramNumerario = "idNumerario="+idNumerario;  
		paramNumerario += '&glCantidad=' + $('#txtCantidadNumerario').val();
		paramNumerario += '&gcMoneda=' + $('#txtMonedaNumerario').val();
		paramNumerario += '&gcCtaTesoreria=' + $('#txtCuentaTesoreriaNumerario').val();
		paramNumerario += '&gcFechaDeposito='+ $('#idFechaDate').val();
		paramNumerario += '&gcHoraDeposito=' + $('#idHoraDate').val();
		paramNumerario += '&glCondicionFisica=' + $("#cbxCondicionNumerario option:selected").val();
		paramNumerario += '&gcDescripcion=' + $("#txtBoxDescNumerario").val();
		
		if(cadenaCustodia!='null')
		{
			paramNumerario += '&cadenaCustodia=' + cadenaCustodia;
			paramNumerario += '&origenEvdCadCus=' + origenEvdCadCus;
			paramNumerario += '&fechaLevCadCus=' + fechaLevCadCus;
		}
		
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ingresarNumerario.do?numeroExpediente='+numeroExpediente +'',
				data: paramNumerario,
				dataType: 'xml',
				success: function(xml){
					var tipoNumerario = $("#txtMonedaNumerario").val();
					  var id = $(xml).find('NumerarioForm').find('idNumerario').text();
					  if(idNumerario==0 && id>0)
					  {	
						  window.parent.alertDinamico("Se guardó correctamente la información");					  
						  //Para guardar la imagen
						  enviaImagenDeElemento(id);
					  }
					  else if(idNumerario==0 && id==0)
					  {
						  window.parent.alertDinamico("Favor de revisar la información capturada");
					  }
					  else
					  {
						  enviaImagenDeElemento(id);
						  window.parent.alertDinamico("La información se actualizó correctamente");
					  }
					  window.parent.cargaNumerario(id,tipoNumerario);
				}
			});
	}
	
	function validaCamposObligatorios(){
		var condicionNumerario = $("#cbxCondicionNumerario option:selected").val();
		var mensaje = "";
		if(parseInt(condicionNumerario) == -1){
			mensaje += "<br />- Condición del numerario</li>";			
		}
		//Comienza segunda validacion para validacion de consistencia de expresiones regulares
		if(mensaje != ""){
			//mensaje += "\n\nEs necesario...."; FUTURAS VALIDACIONES
			if(ocultaAnularObjetoCadCus!=null && ocultaAnularObjetoCadCus!=0)
			{// alert especial cuando agregamos el objeto como evidencia en la cadena de custodia
				window.parent.alertDinamicoEvCadCus("<p align='left'>Favor de revisar los siguientes campos obligatorios: <br />"+mensaje+"</p>");
			}
			else
			{
				window.parent.alertDinamico("<p align='left'>Favor de revisar los siguientes campos obligatorios: <br />"+mensaje+"</p>");
			}
		}else{			
			obtenerValoresNumerario();
		}		
	}
	
	/*Funcion que permite guardar una imagen y la asocia a un elemento*/
	function enviaImagenDeElemento(idElemento){
			document.frmImagenElemento.elementoID.value = idElemento;
	    	document.frmImagenElemento.submit();
	}	
	
	/*
	*Funcion que manda a eliminar logicamente el objeto en la BD
	*/
	function anularObjeto(){
		var paramNumerario="idObjeto="+idNumerario;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramNumerario,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaNumerario(idNumerario,"");
					window.parent.cerrarVentanaNumerario();
				}
				if(cadenaCustodia=='null'){
					//alert("cadCus::"+cadenaCustodia);
					window.parent.alertDinamico($(xml).find('mensajeOp').text());
				}
			}
		});

	}
	
	/*
	*Funcion para anular el objeto
	*/
	function solicitarAnlrObjeto(){
		//revisamos si es una consulta
		if(idNumerario!=null && idNumerario!=0)
		{
			//procederemos a tratar de eliminar la evidencia
			customConfirm ("¿Está seguro que desea anular el objeto?", "", validarObjEvdncNoEslbns);
		}
	}
	
	/*
	*Funcion que validara si el objeto es evidencia y NO tiene eslabones, de ser asi
	*se debe confirmar que se desea eliminar dado que el objeto ya esta en una cadena de custdia
	*/
	function validarObjEvdncNoEslbns()
	{
		var paramNumerario="idObjeto="+idNumerario;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramNumerario,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//debemos mostrar un confirm
					customConfirm ("El objeto es ya una evidencia en alguna cadena de custodia <br/> ¿Está seguro que desea anular el objeto?", "", anularObjeto);
				}
				else if(parseInt($(xml).find('bandera').text())==2)
				{
					//se puede eliminar el objeto sin problemas
					anularObjeto();
				}
				else
				{
					window.parent.alertDinamico($(xml).find('mensajeOp').text());
				}
			}
		});
	}
	
	window.onload = function(){
		var selects = document.getElementsByTagName("textarea");
		for (var i = 0; i < selects.length; i++) { 
			if(selects[i].getAttribute("maxlength") > 0){
				selects[i].onkeydown = function(){
	                            if (this.value.length > this.getAttribute("maxlength")) 
	                                this.value = this.value.substring(0, this.getAttribute("maxlength"));
	                        }
	                        selects[i].onblur = function(){
	                            if (this.value.length > this.getAttribute("maxlength")) 
	                                this.value = this.value.substring(0, this.getAttribute("maxlength"));
	                        }
			}
		}
	}
	</script>  
</head>
 
<body>
				<table width="750px"  height="220px" border="0" align="center" cellpadding="0" cellspacing="0">
				    <tr><td>&nbsp;</td></tr>
					<tr height="12.5%">
					    <td width="20%" class="txt_gral_victima" align="right" id="anularInv"></td>
					    <td width="28%"></td>
					    <td width="26%">&nbsp;</td>
					    <td width="26%" align="right">
					    	<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/>&nbsp;&nbsp;
					    	<input type="button" id="btnGuardarNumerario" value="Guardar" onclick="validaCamposObligatorios();" class="btn_Generico" />
					    </td>
					</tr>
					<tr><td>&nbsp;</td></tr>
                    <tr height="12.5%">
					    <td width="20%">&nbsp;</td>
					    <td width="28%">&nbsp;</td>
					    <td width="26%" align="center">Descripci&oacute;n:</td>
					    <td width="26%" align="center">Fotografía:</td>
					</tr >
					<tr height="12.5%">
					    <td width="20%" align="right">Fecha de Dep&oacute;sito:</td>
					    <td width="28%"><input type="text" id="idFechaDate" maxlength="10" readonly="readonly" width="50px" /></td>
					   <td width="26%" rowspan="6" align="center" valign="top">
					        <textarea cols="29" rows="9" id="txtBoxDescNumerario" maxlength="200"></textarea>
					   </td>
					   <td width="26%" rowspan="6" align="center" valign="middle">
					   	<img id="imgConFoto" src="<%=request.getContextPath()%>/resources/images/Foto.JPG" alt="foto del objeto" width="185" height="185" />
					    </td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">Hora de Dep&oacute;sito:</td>
					    <td width="28%"><input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM" /></td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">Cantidad:</td>
					    <td width="28%"><input type="text" id="txtCantidadNumerario" maxlength="8" style="width:140px"/> Piezas</td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">Moneda:</td>
					    <td width="28%"><input type="text" id="txtMonedaNumerario" maxlength="25" style="width:175px"/></td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">Cuenta Tesorer&iacute;a:</td>
					    <td width="28%"><input type="text" id="txtCuentaTesoreriaNumerario" maxlength="30" style="width:175px"/></td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">Condici&oacute;n:</td>
					    <td width="28%"><select id="cbxCondicionNumerario" style="width:180px">
					      <option value="-1">-Seleccione-</option>
					    </select></td>
					</tr>
					<tr height="12.5%">
					    <td width="20%" align="right">&nbsp;</td>
					    <td width="28%">&nbsp;</td>
					    <td width="26%">&nbsp;</td>
					    <td width="26%">&nbsp;</td>
					</tr>
				</table>
	<table width="750px" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoNumerario.do" method="post" enctype="multipart/form-data" align="left">
				         		<input type="hidden" name="elementoID"/>
				                <input type="file" name="archivo" id="uploadArchivo">
	        	</form>
			</td>
		</tr>			
	</table>
</body>
</html>