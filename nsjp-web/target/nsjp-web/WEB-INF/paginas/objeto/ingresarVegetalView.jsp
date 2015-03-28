<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Vegetal</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	
	<script src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
	
	var idVegetal="";
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
			cargaTiposVegetal();
			cargaUnidadesDeMedida();
			cargaCondicion();
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idVegetal='<%= request.getParameter("idVegetal")%>';
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
			
			if (idVegetal != null && idVegetal != 0)
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idVegetal")%>');
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			if(idVegetal=='null')
			{
				idVegetal=0;	
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			var num=parent.num;
			//revisamos si es una consulta
			if(idVegetal!=null && idVegetal!=0)
			{
				consultaVegetal();
				//condicional para no mostrar el boton de anular objeto cuando entramos desde cadena de custodia
				if(ocultaAnularObjetoCadCus!='null' && ocultaAnularObjetoCadCus!=0)
				{
					$("#anularElemento").hide("");
					$("#anularInv").hide("");
				}
				else
				{
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
	*Funcion para mandar consultar el vegetal
	*/
	function consultaVegetal()
	{
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idVegetal,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				//seteamos la informacion del hecho
    				$(xml).find('VegetalDTO').each(function(){
    							seteaDatosVegetal($(this));
		    	      });
	    		}
    		}	
    	});
	}
	
	function seteaDatosVegetal(xml)
	{
		$('#cbxTipoVegetal').find("option[value='"+$(xml).find('tipoVegetal').find('idCampo').text()+"']").attr("selected","selected");
		$('#txtCantidadVegetal').val($(xml).find('cantidad').text());
		if($(xml).find('almacen'))
			$(xml).find('almacen').remove();
		$('#txtBoxDescVegetal').val($(xml).find('descripcion').text());
		$('#cbxUnidadMedidaVegetal').find("option[value='"+$(xml).find('unidadMedida').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxCondicionVegetal').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");
	}

	/*
	*Funcion que realiza la carga del combo de tipos de Vegetal
	*/
	function cargaTiposVegetal() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposVegetal.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposVegetal').each(function(){
					$('#cbxTipoVegetal').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/*
	*Funcion que realiza la carga del combo de tipos de de unidad para el vegetal
	*/
	function cargaUnidadesDeMedida() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarUnidadesMedida.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catUnidadMedida').each(function(){
					$('#cbxUnidadMedidaVegetal').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/*
	*Funcion que realiza la carga de la condicion del vegetal
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
					$('#cbxCondicionVegetal').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}


//	COMIENZAN FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresVegetal(){		
		var paramVegetal = "idVegetal="+idVegetal;
		paramVegetal += '&glTipoVegetalId=' + $("#cbxTipoVegetal option:selected").val();
		paramVegetal += '&gsCantidadVegetal=' + $('#txtCantidadVegetal').val();
		paramVegetal += '&glUMedidaVegetalId=' + $('#cbxUnidadMedidaVegetal').val();
		paramVegetal += '&glCondicionFisica=' + $("#cbxCondicionVegetal option:selected").val();
		paramVegetal += '&gcDescripcion=' + $("#txtBoxDescVegetal").val();
		
		if(cadenaCustodia!='null')
		{
			paramVegetal += '&cadenaCustodia=' + cadenaCustodia;
			paramVegetal += '&origenEvdCadCus=' + origenEvdCadCus;
			paramVegetal += '&fechaLevCadCus=' + fechaLevCadCus;
		}
		
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/ingresarVegetal.do?numeroExpediente='+numeroExpediente +'',
			data: paramVegetal,
			dataType: 'xml',
			success: function(xml){
				var tipoVegetal = $("#cbxTipoVegetal option:selected").text();
				  var id = $(xml).find('VegetalForm').find('glTipoVegetalId').text();
				  if(idVegetal==0 && id>0)
				  {	
					  window.parent.alertDinamico("Se guardó correctamente la información");					  
					  //Para guardar la imagen
					  enviaImagenDeElemento(id);
				  }
				  else if(idVegetal==0 && id==0)
				  {
					  window.parent.alertDinamico("Favor de revisar la información capturada");
				  }
				  else
				  {
					  enviaImagenDeElemento(id);
					  window.parent.alertDinamico("La información se actualizó correctamente");
				  }
				  window.parent.cargaVegetal(id,tipoVegetal);
			}
		});
	}
	
	function validaCamposObligatorios(){
		var tipoVegetal = $("#cbxTipoVegetal option:selected").val();
		var unidadMedidaVegetal = $('#cbxUnidadMedidaVegetal').val();
		var condicionVegetal = $("#cbxCondicionVegetal option:selected").val();
		var mensaje = "";
		//Primera validacion por cada campo obligatorio		
		if(parseInt(tipoVegetal) == -1){
			mensaje += "<br />- Tipo del vegetal";		
		}
		if(parseInt(unidadMedidaVegetal) == -1){
			mensaje += "<br />- Unidad de medida del vegetal";			
		}
		if(parseInt(condicionVegetal) == -1){
			mensaje += "<br />- Condición del vegetal";			
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
			obtenerValoresVegetal();
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
		var paramVegetal="idObjeto="+idVegetal;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramVegetal,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaVegetal("","");
					window.parent.cerrarVentanaVegetal();
				}
				if(cadenaCustodia=='null'){
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
		if(idVegetal!=null && idVegetal!=0)
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
		var paramVegetal="idObjeto="+idVegetal;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramVegetal,
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
				         <td width="20%" class="txt_gral_victima" id="anularInv"></td>
				         <td width="28%"></td>
				         <td width="26%">&nbsp;</td>
				         <td width="26%" align="right">
				         	<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/>
				         	<input type="button" id="btnGuardarVegetal" value="Guardar" onclick="validaCamposObligatorios();" class="btn_Generico" />
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
				         <td width="20%" align="right">Tipo:</td>
				         <td width="28%"><select id="cbxTipoVegetal" style="width:180px">
				           <option value="-1">-Seleccione-</option>
			             </select></td>
				        <td width="26%" rowspan="6" align="center" valign="top">
			              <textarea cols="29" rows="9" id="txtBoxDescVegetal" maxlength="200"></textarea>
				        </td>
				        <td width="26%" rowspan="6" align="center" valign="middle">
				        	<img id="imgConFoto" src="<%=request.getContextPath()%>/resources/images/Foto.JPG" alt="foto del objeto" width="185" height="185" />
				        </td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Cantidad:</td>
				        <td width="28%"><input type="text" id="txtCantidadVegetal" maxlength="8" style="width:140px"/> Piezas</td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Unidad de Medida:</td>
				        <td width="28%"><select  id="cbxUnidadMedidaVegetal" style="width:180px">
				          <option value="-1">-Seleccione-</option>
			            </select></td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">&nbsp;</td>
				        <td width="28%">&nbsp;</td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">&nbsp;</td>
				        <td width="28%">&nbsp;</td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Condici&oacute;n:</td>
				        <td width="28%"><select id="cbxCondicionVegetal" style="width:180px">
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
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoVegetal.do" method="post" enctype="multipart/form-data" align="left">
				         		<input type="hidden" name="elementoID"/>
				                <input type="file" name="archivo" id="uploadArchivo">
	        	</form>
			</td>
		</tr>			
	</table>
  </body>
</html>