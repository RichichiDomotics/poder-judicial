<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Arma</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<!-- JS para la validacion de solo numeros -->
	<script type="text/javascript">
	var idArma="";
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
			idArma='<%= request.getParameter("idArma")%>';
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
			
			if (idArma != null && idArma != 0)
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idArma")%>');
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			if(idArma=='null')
			{
				idArma=0;	
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			
			cargaTiposArma();
			cargaMarcasArma();
			cargaCondicion();
			/*$("#cbxTipoArma, #cbxMarcaArma, #cbxCondicionArma").multiselect({ 
				multiple: false, 
				header: "Seleccione", 
				position: { 
					my: 'top', 
					at: 'top' 
				},
				selectedList: 1 
			});*/
			var num=parent.num;
			//revisamos si es una consulta
			if(idArma!=null && idArma!=0)
			{
				consultaArma();
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
	*Funcion para mandar consultar el arma
	*/
	function consultaArma()
	{
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idArma,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				//seteamos la informacion del hecho
    				$(xml).find('ArmaDTO').each(function(){
    						//if($(this).find('elementoId').text()==idArma)
    						//{
    							seteaDatosArma($(this));
    						//}
		    	      });
	    		}
    		}	
    	});
	}
	
	function seteaDatosArma(xml)
	{
		$('#txtModeloArma').val($(xml).find('modelo').text());
		$('#txtMatriculaArma').val($(xml).find('matricula').text());
		$('#txtCalibreArma').val($(xml).find('calibre').text());
		if($(xml).find('almacen'))
			$(xml).find('almacen').remove();
		$("#txtBoxDescArma").val($(xml).find('descripcion').text());
		
		$('#cbxTipoArma').find("option[value='"+$(xml).find('tipoArma').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxMarcaArma').find("option[value='"+$(xml).find('marca').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxCondicionArma').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");

	}

	/*
	*Funcion que realiza la carga del combo de tipos de arma
	*/
	function cargaTiposArma() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposArma.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposArma').each(function(){
					$('#cbxTipoArma').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de marcas del arma
	*/
	function cargaMarcasArma() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarMarcasArma.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catMarcasArma').each(function(){
					$('#cbxMarcaArma').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de la condicion del arma
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
					$('#cbxCondicionArma').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}


//	COMIENZA FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresArma(){		
		var paramArma = "idArma="+idArma; 
		paramArma += '&glTipoArmaId=' + $("#cbxTipoArma option:selected").val();
		paramArma += '&glMarcaArmaId=' + $("#cbxMarcaArma option:selected").val();
		paramArma += '&gcModeloArma=' + $('#txtModeloArma').val();
		paramArma += '&gcMatriculaArma=' + $('#txtMatriculaArma').val();
		paramArma += '&gcCalibreArma=' + $('#txtCalibreArma').val();
		paramArma += '&glCondicionFisicaArmaId=' + $("#cbxCondicionArma option:selected").val();
		paramArma += '&gcDescripcionArma=' + $("#txtBoxDescArma").val();
		
		if(cadenaCustodia!='null')
		{
			paramArma += '&cadenaCustodia=' + cadenaCustodia;
			paramArma += '&origenEvdCadCus=' + origenEvdCadCus;
			paramArma += '&fechaLevCadCus=' + fechaLevCadCus;
		}

		$.ajax({
		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/ingresarArma.do?numeroExpediente='+numeroExpediente +'',
			data: paramArma,
			dataType: 'xml',
			success: function(xml){
				  var tipoArma = $("#cbxTipoArma option:selected").text();
				  var id = $(xml).find('ArmaForm').find('glTipoArmaId').text();
				  if(idArma==0 && id>0)
				  {	
					  window.parent.alertDinamico("Se guardó correctamente la información");					  
					  //Para guardar la imagen
					  enviaImagenDeElemento(id);
					  //window.parent.cargaArma(id,tipoArma);
				  }
				  else if(idArma==0 && id==0)
				  {
					  window.parent.alertDinamico("Favor de revisar la información capturada");
				  }
				  else
				  {
					  enviaImagenDeElemento(id);
					  //window.parent.consultaGridArmasVisor();
					  //window.parent.cerrarVentanaArma();
					  window.parent.alertDinamico("La información se actualizó correctamente");
				  }
				  window.parent.cargaArma(id,tipoArma);
			}
		});
	}
	
	function validaCamposObligatorios(){
		var tipoArma = $("#cbxTipoArma option:selected").val();
		var marcaArma = $("#cbxMarcaArma option:selected").val();
		var condicionArma = $("#cbxCondicionArma option:selected").val();		
		var mensaje = "";
		//Primera validacion por cada campo obligatorio		
		if(parseInt(tipoArma) == -1){
			mensaje += "<br />- Tipo del arma";			
		}
		if(parseInt(marcaArma) == -1){
			mensaje += "<br />- Marca del arma";			
		}
		if(parseInt(condicionArma) == -1){
			mensaje += "<br />- Condición del arma";			
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
			obtenerValoresArma();
		}
	}
	
	/*
	*Funcion que manda a eliminar logicamente el objeto en la BD
	*/
	function anularObjeto(){
		var paramArma="idObjeto="+idArma;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramArma,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaArma(idArma,"");
					window.parent.cerrarVentanaArma();
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
		if(idArma!=null && idArma!=0)
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
		var paramArma="idObjeto="+idArma;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramArma,
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
				         <td width="20%" height="21" class="txt_gral_victima" id="anularInv"></td>
						 <td width="28%"></td>
				         <td width="26%">&nbsp;</td>
				         <td width="26%" align="right">
				         	<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/>&nbsp;&nbsp;
				         	<input type="button" id="btnGuardarArma" value="Guardar" class="btn_Generico" onclick="validaCamposObligatorios();" />
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
				         <td width="28%"><select id="cbxTipoArma" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				        <td width="26%" rowspan="6" align="center" valign="top">
				            <textarea cols="29" rows="9" id="txtBoxDescArma" maxlength="200"></textarea>
				        </td>
				        <td width="26%" rowspan="6" align="center" valign="middle">
				             <img src="" alt="" width="185" height="185" id="imgConFoto"/>
				        </td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Marca:</td>
				        <td width="28%"><select 
                         id="cbxMarcaArma" style="width:180px">
				          <option value="-1">-Seleccione-</option>
				        </select></td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Modelo:</td>
				        <td width="28%"><input type="text" id="txtModeloArma" maxlength="25" style="width:175px"/></td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Calibre:</td>
				        <td width="28%"><input type="text" id="txtCalibreArma" onKeyPress="return numerosDecimales(event);" maxlength="5" style="width:135px"/> mm.</td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Matr&iacute;cula:</td>
				        <td width="28%"><input type="text" id="txtMatriculaArma" maxlength="20" style="width:175px"/></td>
				    </tr>
				    <tr height="12.5%">
				        <td width="20%" align="right">Condici&oacute;n:</td>
				        <td width="28%"><select id="cbxCondicionArma" style="width:180px">
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
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoArma.do" method="post" enctype="multipart/form-data" align="left">
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
