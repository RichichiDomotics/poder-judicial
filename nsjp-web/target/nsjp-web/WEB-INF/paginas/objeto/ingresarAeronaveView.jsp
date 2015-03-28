<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Aeronave</title>
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
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
	
	var idAeronave="";
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
			//seteamos las variables globales
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idAeronave='<%= request.getParameter("idAeronave")%>';
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
			
			if (idAeronave != null && idAeronave != 0)
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idAeronave")%>');
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			if(idAeronave=='null')
			{
				idAeronave=0;	
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			
			cargaTiposAeronave();
			$('#cbxTipoAeronave').change(cargaMarcasAeronave);
			$('#cbxMarcaAeronave').change(cargaSubMarcasAeronave);
			cargaColores();
			cargaPaises();
			cargaCondicion();
			var num=parent.num;
			//revisamos si es una consulta
			if(idAeronave!=null && idAeronave!=0)
			{
				consultaAeronave();
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
	*Funcion para mandar consultar el aeronave
	*/
	function consultaAeronave()
	{
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idAeronave,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				//seteamos la informacion del hecho
    				$(xml).find('AeronaveDTO').each(function(){
    						seteaDatosAeronave($(this));
		    	      });
	    		}
    		}	
    	});
	}
	
	function seteaDatosAeronave(xml)
	{
		$('#cbxTipoAeronave').find("option[value='"+$(xml).find('tipoAeroNave').find('idCampo').text()+"']").attr("selected","selected");
		cargaMarcasAeronave();
		$('#cbxMarcaAeronave').find("option[value='"+$(xml).find('marca').find('idCampo').text()+"']").attr("selected","selected");
		cargaSubMarcasAeronave();
		$('#cbxSubMarcaAeronave').find("option[value='"+$(xml).find('subMarca').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxPaisOrigenAeronave').find("option[value='"+$(xml).find('paisProcedencia').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxColorAeronave').find("option[value='"+$(xml).find('color').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxCondicionAeronave').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");
		$('#txtModeloAeronave').val($(xml).find('modelo').text());
		$('#txtMatAeronave').val($(xml).find('matricula').text());
		$('#txtNoSerieAeronave').val($(xml).find('noSerie').text());
		$('#txtNoMotorAeronave').val($(xml).find('noMotor').text());
		if($(xml).find('almacen')){
			$(xml).find('almacen').remove();
		}
		$('#txtBoxDescAeronave').val($(xml).find('descripcion').text());
	}
	
   /**
	*Funcion que limpia los combos que dependen del combo tipo de aeronave
	*/
	function limpiaCombosDependsTipo(){
		$('#cbxMarcaAeronave').empty();
		$('#cbxMarcaAeronave').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
		$('#cbxSubMarcaAeronave').empty();
		$('#cbxSubMarcaAeronave').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
	}


	/**
	*Funcion que limpia los combos que dependen del combo tipo de la marca de la aeronave
	*/
	function limpiaCombosDependsMarca(){
		$('#cbxSubMarcaAeronave').empty();
		$('#cbxSubMarcaAeronave').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
	}
		

	/*
	*Funcion que realiza la carga del combo de tipos de aeronave
	*/
	function cargaTiposAeronave() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposAeronave.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposAeronave').each(function(){
					$('#cbxTipoAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/*
	*Funcion que realiza la carga del combo de marcas de aeronave
	*/
	function cargaMarcasAeronave() {

		var selected = $("#cbxTipoAeronave option:selected");
		
		limpiaCombosDependsTipo();
		
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarMarcasAeronave.do',
			data: 'glTipoAeronaveId='+selected.val(),
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catMarcasAeronave').each(function(){
					$('#cbxMarcaAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxMarcaAeronave').multiselect('refresh');
						});
			}
		});
	}

	/*
	*Funcion que realiza la carga de  sub marcas de aeronave 
	*/
	function cargaSubMarcasAeronave() {

		var selected = $("#cbxMarcaAeronave option:selected");

		limpiaCombosDependsMarca();
		
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarSubMarcasAeronave.do',
			data: 'glMarcaAeronaveId=' + selected.val(),
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catSubMarcasAeronave').each(function(){
					$('#cbxSubMarcaAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxSubMarcaAeronave').multiselect('refresh');
						});
			}
		});
	}



	
	/*
	*Funcion que realiza la carga de colores de la aeronave
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
					$('#cbxColorAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/*
	*Funcion que realiza la carga del combo de Paises
	*/
	function cargaPaises() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarPaises.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catPaises').each(function(){
					$('#cbxPaisOrigenAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/*
	*Funcion que realiza la carga de la condicion de la aeronave
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
					$('#cbxCondicionAeronave').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}	

//	COMIENZA FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresAeronave(){
				
		var paramAeronave = "idAeronave="+idAeronave; 
		paramAeronave += '&glTipoAeronaveId=' + $("#cbxTipoAeronave option:selected").val();
		paramAeronave += '&glMarcaAeronaveId=' +  $("#cbxMarcaAeronave option:selected").val();
		paramAeronave += '&glSubMarcaAeronaveId=' + $("#cbxSubMarcaAeronave option:selected").val(); 
		paramAeronave += '&gcModeloAeronave=' + $('#txtModeloAeronave').val();
		paramAeronave += '&glPaisProcedAeronaveId=' + $("#cbxPaisOrigenAeronave option:selected").val();
		paramAeronave += '&glColorAeronaveId=' + $("#cbxColorAeronave option:selected").val();
		paramAeronave += '&gcMatriculaAeronave=' + $('#txtMatAeronave').val();
		paramAeronave += '&gcNoSerieAeronave=' + $('#txtNoSerieAeronave').val();
		paramAeronave += '&gcNoMotorAeronave=' + $('#txtNoMotorAeronave').val();
		paramAeronave += '&glCondicionAeronaveId=' + $("#cbxCondicionAeronave option:selected").val();
		paramAeronave += '&gcDescripcionAeronave=' + $("#txtBoxDescAeronave").val();
		
		if(cadenaCustodia!='null')
		{
			paramAeronave += '&cadenaCustodia=' + cadenaCustodia;
			paramAeronave += '&origenEvdCadCus=' + origenEvdCadCus;
			paramAeronave += '&fechaLevCadCus=' + fechaLevCadCus;
		}
		
		$.ajax({
		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/ingresarAeronave.do?numeroExpediente='+numeroExpediente +'',
			data: paramAeronave,
			dataType: 'xml',
			success: function(xml){
				 var tipoAeronave = $("#cbxTipoAeronave option:selected").text();
				  var id = $(xml).find('AeronaveForm').find('idAeronave').text();
				  if(idAeronave==0 && id>0)
				  {	
					  window.parent.alertDinamico("Se guardó correctamente la información");					  
					  //Para guardar la imagen
					  enviaImagenDeElemento(id);
				  }
				  else if(idAeronave==0 && id==0)
				  {
					  window.parent.alertDinamico("Favor de revisar la información capturada");
				  }
				  else
				  {
					  enviaImagenDeElemento(id);
					  window.parent.alertDinamico("La información se actualizó correctamente");
				  }
				  window.parent.cargaAeronave(id,tipoAeronave);
			}
		});
	}
	
	function validaCamposObligatorios(){
		var tipoAeronave = $("#cbxTipoAeronave option:selected").val();
		var marcaAeronave = $("#cbxMarcaAeronave option:selected").val();
		var subMarcaAeronave = $("#cbxSubMarcaAeronave option:selected").val();
		var colorAeronave = $("#cbxColorAeronave option:selected").val();
		var paisOrigenAeronave = $("#cbxPaisOrigenAeronave option:selected").val();
		var condicionAeronave = $("#cbxCondicionAeronave option:selected").val();
		var mensaje = "";
		//Primera validacion por cada campo obligatorio		
		if(parseInt(tipoAeronave) == -1){
			mensaje += "<br />- Tipo de la aeronave";			
		}
		if(parseInt(marcaAeronave) == -1){
			mensaje += "<br />- Marca de la aeronave";			
		}
		if(parseInt(subMarcaAeronave) == -1){
			mensaje += "<br />- Submarca de la aeronave";			
		}
		if(parseInt(colorAeronave) == -1){
			mensaje += "<br />- Color de la aeronave";			
		}
		if(parseInt(paisOrigenAeronave) == -1){
			mensaje += "<br />- País de origen de la aeronave";			
		}
		if(parseInt(condicionAeronave) == -1){
			mensaje += "<br />- Condición de la aeronave";			
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
			obtenerValoresAeronave();
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
		var paramAeronave="idObjeto="+idAeronave;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramAeronave,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaAeronave(idAeronave,"");
					window.parent.cerrarVentanaAeronave();
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
		if(idAeronave!=null && idAeronave!=0)
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
		var paramAeronave="idObjeto="+idAeronave;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramAeronave,
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
        	<table width="560px" height="448" border="0" cellspacing="0" cellpadding="0">
		  	 <tr><td>&nbsp;</td></tr>				   
             <tr height="6.25%">
                <td width="28%" height="21" align="right" class="txt_gral_victima" id="anularInv"></td>
              <td width="36%">&nbsp;</td>
                <td width="36%" align="right">
                	<input type="button" value="Anular objeto" class="btn_Generico" id="anularElemento" onclick="solicitarAnlrObjeto()"/>&nbsp;&nbsp;
                	<input type="button" id="btnGuardarAeronave" onclick="validaCamposObligatorios();" value="Guardar" class="btn_Generico" />
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>				   
              <tr height="6.25%">
                <td width="28%" height="21" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
                <td width="36%" align="center">Descripci&oacute;n:</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Tipo:</td>
                <td width="36%"><select id="cbxTipoAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
                <td width="36%" rowspan="7" align="center"><textarea cols="25" rows="9" id="txtBoxDescAeronave" maxlength="200"></textarea></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Marca:</td>
                <td width="36%"><select id="cbxMarcaAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Submarca:</td>
                <td width="36%"><select id="cbxSubMarcaAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Color:</td>
                <td width="36%"><select id="cbxColorAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Pa&iacute;s de Origen:</td>
                <td width="36%"><select id="cbxPaisOrigenAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Modelo: </td>
                <td width="36%"><input type="text" id="txtModeloAeronave" maxlength="25" style="width:175px"/></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">Matrícula:</td>
                <td width="36%"><input type="text" id="txtMatAeronave" maxlength="15" style="width:175px"/></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">No. de Motor:</td>
                <td width="36%"><input type="text" id="txtNoMotorAeronave" maxlength="20" style="width:175px"/></td>
                <td width="36%" align="center">Fotograf&iacute;a:</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" height="24" align="right">No. de Serie:</td>
                <td width="36%"><input type="text" id="txtNoSerieAeronave" maxlength="20" style="width:175px"/></td>
                <td rowspan="6" align="center"><img id="imgConFoto" src="<%=request.getContextPath()%>/resources/images/Foto.JPG" alt="foto del objeto" width="185" height="165" /></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" height="25" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
              </tr>
              <tr height="6.25%">
                <td width="28%" height="36" align="right">Condici&oacute;n:</td>
                <td width="36%"><select id="cbxCondicionAeronave" style="width:180px">
                  <option value="-1">-Seleccione-</option>
                </select></td>
              </tr>
              <tr height="6.25%">
                <td width="28%" align="right">&nbsp;</td>
                <td width="36%">&nbsp;</td>
                <td width="36%">&nbsp;</td>
              </tr>
              
            </table>
    <table width="450px" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoAeronave.do" method="post" enctype="multipart/form-data" align="left">
				         		<input type="hidden" name="elementoID"/>
				                <input type="file" name="archivo" id="uploadArchivo">
	        	</form>
			</td>
		</tr>			
		</table>
</body>
</html>