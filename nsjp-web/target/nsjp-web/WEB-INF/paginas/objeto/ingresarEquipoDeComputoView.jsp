<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar equipo de c&oacute;mputo</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<script src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript">
	
	var idEquipoComputo="";
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
			cargaTiposEquipoComputo();
			cargaMarcasEquipoComputo();
			cargaColores();
			cargaCondicion();
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idEquipoComputo='<%= request.getParameter("idEquipoComputo")%>';
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
			
			if (idEquipoComputo != null && idEquipoComputo != 0)
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idEquipoComputo")%>');
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			if(idEquipoComputo=='null')
			{
				idEquipoComputo=0;
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			var num=parent.num;
			//revisamos si es una consulta
			if(idEquipoComputo!=null && idEquipoComputo!=0)
			{
				consultaEquipoComputo();
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
			else{
				if(num!=null && num!="0"){
					$("#anularElemento").hide();
					$("#anularInv").hide();
				}
			}
	});

	/*
	*Funcion para mandar consultar el vehiculo
	*/
	function consultaEquipoComputo()
	{
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idEquipoComputo,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				//seteamos la informacion del hecho
    				$(xml).find('EquipoComputoDTO').each(function(){
    						//if($(this).find('elementoId').text()==idEquipoComputo)
    						//{
    							seteaDatosEquipoComputo($(this));
    						//}
		    	      });
	    		}
    		}	
    	});
	}

	function seteaDatosEquipoComputo(xml)
	{
		$('#txtModeloEquipoComputo').val($(xml).find('modelo').text());
		$('#txtNoSerieEquipoComputo').val($(xml).find('noSerie').text());
		if($(xml).find('almacen'))
			$(xml).find('almacen').remove();
		$("#txtBoxDescEquipoComputo").val($(xml).find('descripcion').text());
		$('#cbxTipoEquipoComputo').find("option[value='"+$(xml).find('tipoEquipo').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxMarcaEquipoComputo').find("option[value='"+$(xml).find('Marca').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxColorEquipoComputo').find("option[value='"+$(xml).find('color').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxCondicionEquipoComputo').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");

	}
	
	/*
	*Funcion que realiza la carga del combo de tipos de eqipo de computo
	*/
	function cargaTiposEquipoComputo() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposEquipoComputo.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposEquipoComputo').each(function(){
					$('#cbxTipoEquipoComputo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de marcas del equipo de computo
	*/
	function cargaMarcasEquipoComputo() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarMarcasEquipoComputo.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catMarcasEquipoComputo').each(function(){
					$('#cbxMarcaEquipoComputo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de colores del equipo de computo
	*/
	function cargaColores() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarColores.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catColores').each(function(){
					$('#cbxColorEquipoComputo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de la condicion fisica del equipo de computo
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
					$('#cbxCondicionEquipoComputo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

//	COMIENZA FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresEquipoComputo(){
		
		var paramEquipComputo="idEquipoComputo="+idEquipoComputo; 
		paramEquipComputo += '&glTipoEquipoComputoId=' + $("#cbxTipoEquipoComputo option:selected").val();
		paramEquipComputo += '&glMarcaEquipoComputoId=' + $("#cbxMarcaEquipoComputo option:selected").val();
		paramEquipComputo += '&gsModeloEquipoComputo=' + $('#txtModeloEquipoComputo').val();
		paramEquipComputo += '&glColorEquipoComputoId=' +  $("#cbxColorEquipoComputo option:selected").val();
		paramEquipComputo += '&gsNoSerieEquipoComputo=' + $('#txtNoSerieEquipoComputo').val();
		paramEquipComputo += '&glCondicionFisicaEquipoComputoId=' + $("#cbxCondicionEquipoComputo option:selected").val();
		paramEquipComputo += '&gsDescripcionEquipoComputo=' + $("#txtBoxDescEquipoComputo").val();
		if(cadenaCustodia!='null')
		{
			paramEquipComputo += '&cadenaCustodia=' + cadenaCustodia;
			paramEquipComputo += '&origenEvdCadCus=' + origenEvdCadCus;
			paramEquipComputo += '&fechaLevCadCus=' + fechaLevCadCus;
		}		
		
		$.ajax({
		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/ingresarEquipoComputo.do?numeroExpediente='+numeroExpediente +'',
			data: paramEquipComputo,
			dataType: 'xml',
			success: function(xml){
				  var tipoEquipo = $("#cbxTipoEquipoComputo option:selected").text();
				  var id = $(xml).find('EquipoDeComputoForm').find('glTipoEquipoComputoId').text();  	
				  if(idEquipoComputo==0 && id>0)
				  {	  
					  window.parent.alertDinamico("Se guardó correctamente la información");
					//Para guardar la imagen
					enviaImagenDeElemento(id);
	    		  	window.parent.cargaEquipoComputo(id,tipoEquipo);
				  }
				  else if(idEquipoComputo==0 && id==0)
				  {
					  window.parent.alertDinamico("Favor de revisar la información capturada");
				  }
				  else
				  {
					  enviaImagenDeElemento(id);
					  window.parent.consultaGridEquiposComputoVisor();
					  window.parent.cerrarVentanaEquipoComputo();
					  window.parent.alertDinamico("La información se actualizó correctamente");
				  }
			}
		});
	}
	
	function validaCamposObligatorios(){
		var tipoEquipComputo = $("#cbxTipoEquipoComputo option:selected").val();
		var marcaEquipComputo = $("#cbxMarcaEquipoComputo option:selected").val();
		var colorEquipComputo = $("#cbxColorEquipoComputo option:selected").val();
		var condicionEquipComputo = $("#cbxCondicionEquipoComputo option:selected").val();
		var mensaje = "";
		//Primera validacion por cada campo obligatorio		
		if(parseInt(tipoEquipComputo) == -1){
			mensaje += "<br />- Tipo del equipo de cómputo";			
		}
		if(parseInt(marcaEquipComputo) == -1){
			mensaje += "<br />- Marca del equipo de cómputo";			
		}
		if(parseInt(colorEquipComputo) == -1){
			mensaje += "<br />- Color del equipo de cómputo";			
		}
		if(parseInt(condicionEquipComputo) == -1){
			mensaje += "<br />- Condición del equipo de cómputo";			
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
			obtenerValoresEquipoComputo();
		}
	}
	
	/*
	*Funcion que manda a eliminar logicamente el objeto en la BD
	*/
	function anularObjeto(){
		var paramEquipComputo="idObjeto="+idEquipoComputo;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramEquipComputo,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaEquipoComputo("","");
					window.parent.cerrarVentanaEquipoComputo();
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
		if(idEquipoComputo!=null && idEquipoComputo!=0)
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
		var paramEquipComputo="idObjeto="+idEquipoComputo;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramEquipComputo,
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
				         <td height="21" class="txt_gral_victima" id="anularInv"></td>
						 <td></td>
				         <td>&nbsp;</td>
				         <td align="right">
				         	<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/> &nbsp;&nbsp;
				         	<input type="button" id="btnGuardarEquipoComputo" value="Guardar" class="btn_Generico" onclick="validaCamposObligatorios();" />
				         </td>
				     </tr>
				     <tr><td>&nbsp;</td></tr>				    
                     <tr height="12.5%">
				         <td>&nbsp;</td>
				         <td>&nbsp;</td>
				         <td align="center">Descripci&oacute;n:</td>
				         <td align="center" width="30%">Fotografía:</td>
				     </tr>
				     <tr height="12.5%">
				         <td align="right">Tipo:</td>
				         <td><select name="cbxTipoEquipoComputo" id="cbxTipoEquipoComputo" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				        <td rowspan="6" align="center" valign="top">
				            <textarea cols="29" rows="9" id="txtBoxDescEquipoComputo" maxlength="200"></textarea>
				        </td>
				        <td rowspan="6" align="center" valign="middle" width="125px" height="150px">
				        	<img src="" alt="" width="100%" height="100%" id="imgConFoto"/>
				        </td>
				    </tr>
				    <tr height="12.5%">
				        <td align="right">Marca:</td>
				        <td><select id="cbxMarcaEquipoComputo" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				    </tr>
				    <tr height="12.5%">
				        <td align="right">Color:</td>
				        <td><select id="cbxColorEquipoComputo" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				    </tr>
				    <tr height="12.5%">
				        <td align="right">Modelo:</td>
				        <td><input type="text" id="txtModeloEquipoComputo" maxlength="25" style="width:175px"/></td>
				    </tr>
				    <tr height="12.5%">
				        <td align="right">No. de Serie:</td>
				        <td><input type="text" id="txtNoSerieEquipoComputo" maxlength="20" style="width:175px"/></td>
				    </tr>
				    <tr height="12.5%">
				        <td align="right">Condici&oacute;n:</td>
				        <td><select name="cbxCondicionEquipoComputo" id="cbxCondicionEquipoComputo" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				    </tr>
				    <tr><td>&nbsp;</td></tr>				    
				</table>
	
	<table width="750px" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td align="center" colspan="2">
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoEquipComputo.do" method="post" enctype="multipart/form-data" align="left">
				         		<input type="hidden" name="elementoID"/>
				                <input type="file" name="archivo" id="uploadArchivo">
	        	</form>
			</td>
		</tr>			
		</table>
		<script type="text/javascript">
			/*Funcion que permite guardar una imagen y la asocia a un elemento*/
			function enviaImagenDeElemento(idElemento){
					document.frmImagenElemento.elementoID.value = idElemento;
			    	document.frmImagenElemento.submit();
			}		
		</script>
</body>
</html>
