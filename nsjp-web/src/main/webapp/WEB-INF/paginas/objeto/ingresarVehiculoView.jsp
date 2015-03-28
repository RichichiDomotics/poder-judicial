<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar veh&iacute;culo</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>	
	<!-- JS para la validacion de solo numeros -->
	<script src="<%=request.getContextPath()%>/js/comun.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/thickbox/thickbox.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/thickBox/thickbox.css"  />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	
	
	
	<script type="text/javascript">
	var idVehiculo="";
	var tipoObjeto="";
	var numeroExpediente="";
	var cadenaCustodia="";
	var fechaLevCadCus="";
	var origenEvdCadCus="";
	var deshabilitarCampos = window.parent.deshabilitarCamposPM;
	var ocultaAnularObjetoCadCus=null;
	
	var contextoPagina = "${pageContext.request.contextPath}";
	var sistemaTrad=false; 
	
	
	jQuery().ready(			
		function () {
			//Instruccion pensada solo para el caso de policia ministerial
			if(deshabilitarCampos == true){
				$(":enabled").attr('disabled','disabled');
				$("#btnBuscarReincidencia").removeAttr('disabled');
				$("#btnConsultarExpedientes").removeAttr('disabled');	
			}
			 
					
			cargaTiposVehiculo();
			cargaRelacionesHecho();
			$('#cbxTipoVehiculo').change(cargaMarcasVehiculo);
			$('#cbxMarcaVehiculo').change(cargaSubMarcasVehiculo);

			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idVehiculo=parseInt('<%= request.getParameter("idVehiculo")%>');
			
			if (idVehiculo != null && idVehiculo != 0){
				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idVehiculo")%>');
			}
			else{
				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
			}
			
			cadenaCustodia='<%= request.getParameter("cadenaCustodia")%>';
			fechaLevCadCus='<%= request.getParameter("fechaLevCadCus")%>';
			origenEvdCadCus='<%= request.getParameter("origenEvdCadCus")%>';
			sistemaTrad='<%= request.getParameter("sistemaTrad")%>';
			
			//lineas para ocultar la opcion de anular objeto alconsultar desde una cadena de custodia
			ocultaAnularObjetoCadCus='<%= request.getParameter("anularConsultaCadCus")%>';
			if(ocultaAnularObjetoCadCus!=null && ocultaAnularObjetoCadCus!=0)
			{
				$("#anularElemento").hide("");
				$("#anularInv").hide("");
			}
			
			if(idVehiculo=='null' || idVehiculo==null)
			{
				idVehiculo=0;
				
			}
			
			//Ocultar reincidencia
			if (sistemaTrad=='true') {
				$("#btnBuscarReincidencia").hide("");
				$("#btnConsultarExpedientes").hide("");
			}
			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
			cargaColores();
			cargaPaises();
			cargaCondicion();
			var num=window.parent.num;
			//revisamos si es una consulta
			if(idVehiculo!=null && idVehiculo!=0)
			{//esto se ejecuta cuando llegamos desde cadena de custodia
				consultaVehiculo();
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
			else{
				if(num!=null && num!="0")
				{
					$("#anularElemento").hide();
					$("#anularInv").hide();
				}
			}
	});
	
	function enviaImagenDeElemento(idElemento){
		document.frmImagenElemento.elementoID.value = idElemento;
    	document.frmImagenElemento.submit();
	}
	
	//Llena el grid con los resultados de la busqueda
	var banderaCargarORecargar=0;
	function cargaGridReincidenciaElemento() {
		var noPlacas = $('#txtPlacasVehiculo').val();	
		var noSerie = $('#txtSerieVehiculo').val();
		if(noPlacas == "")
			noPlacas = null
		if(noSerie == "")
			noSerie = null
			
		if(noSerie == null && noPlacas == null)	
			window.parent.alertDinamico("Es necesario escribir el No. de Placas y el No. de Serie para consultar las reincidencias");
		else{
			muestraPopupReincidenciaElemento();
			//Inicia grid
			if(banderaCargarORecargar==0){
				jQuery("#gridReincidenciaElemento").jqGrid({
					url:'<%=request.getContextPath()%>/buscarReincidenciaDeVehiculo.do?noPlacas='+noPlacas +'&noSerie='+noSerie+'&numeroExpediente='+ numeroExpediente+'&idVehiculo='+idVehiculo+'',
								datatype: "xml",
								colNames:['Numero General del caso','Fecha de Apertura','Estatus'],
								colModel:[ 	{name:'NumeroGeneralCaso',index:'numeroGeneralCaso', width:200},
											{name:'FechaApertura',index:'fechaApertura', width:120},							
											{name:'Estatus',index:'estatus', width:100}
											],
					pager: jQuery('#paginadorReincidenciaElemento'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: false,
					autoheight:false,
					sortname: 'FechaApertura',
					viewrecords: true,
					multiselect: true,
					sortorder: "desc",
					onSelectRow: function(id) {
						}
				}).navGrid('#paginadorReincidenciaElemento',{edit:false,add:false,del:false});
	        	banderaCargarORecargar=1;
			}
			else{
		    	jQuery("#gridReincidenciaElemento").jqGrid('setGridParam',{
		    		url:'<%=request.getContextPath()%>/buscarReincidenciaDeVehiculo.do?noPlacas='+noPlacas +'&noSerie='+noSerie+'&numeroExpediente='+ numeroExpediente+'&idVehiculo='+idVehiculo+'',
		    				datatype: "xml" });
				$("#gridReincidenciaElemento").trigger("reloadGrid");
		    }//Fin grid
		}
	}
	
	
	//Llena el grid con los resultados de la busqueda de las relaciones entre un elemento y los casos registrados en BD
	var banderaCargarORecargarGrid2=0;
	function cargaGridReincidenciasXElemento() {
			var idElemento = idVehiculo;
			muestraPopupReincidenciaElementoCaso();
			//Inicia grid
			if(banderaCargarORecargarGrid2==0){
				jQuery("#gridReincidenciaElementoCaso").jqGrid({
					url:'<%=request.getContextPath()%>/consultarReincidenciaXElemento.do?numeroExpediente='+numeroExpediente+'&idElemento='+idElemento +'',
								datatype: "xml",
								colNames:['Numero General del caso','Fecha de Apertura','Estatus'],
								colModel:[ 	{name:'NumeroGeneralCaso',index:'numeroGeneralCaso', width:200},
											{name:'FechaApertura',index:'fechaApertura', width:120},							
											{name:'Estatus',index:'estatus', width:100}
											],
					pager: jQuery('#paginadorReincidenciaElementoCaso'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: false,
					autoheight:false,
					sortname: 'FechaApertura',
					viewrecords: true,
					multiselect: false,
					sortorder: "desc",
					onSelectRow: function(id) {
						}
				}).navGrid('#paginadorReincidenciaElementoCaso',{edit:false,add:false,del:false});
				banderaCargarORecargarGrid2=1;
			}
			else{
		    	jQuery("#gridReincidenciaElementoCaso").jqGrid('setGridParam',{
		    		url:'<%=request.getContextPath()%>/consultarReincidenciaXElemento.do?numeroExpediente='+numeroExpediente+'&idElemento='+idElemento + 
					'',datatype: "xml" });
				$("#gridReincidenciaElementoCaso").trigger("reloadGrid");
		    }//Fin grid		
	}

	

		function registrarReincidenciasXElemento(){
			var params = "";
		    params = '&idFuncionario=' + 1;
		    params += '&idElemento=' + idVehiculo;
		    params += '&idCasos=' + jQuery("#gridReincidenciaElemento").jqGrid('getGridParam','selarrrow');		    
		    if(idVehiculo != 0){
		    	$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/registrarReincidenciasXElemento.do',
					data: params, 
					async: false,
					dataType: 'xml',
					success: function(xml){	
						window.parent.alertDinamico("La(s) relaci\u00F3n(es) se registraron correctamente");
						window.parent.cargaVehiculo(idVehiculo,null,null);
						cargaGridReincidenciaElemento(); 
					}
				});
		    }
		    else
		    	window.parent.alertDinamico("Debe de guardar primero el elemento")
		    	
	}

	//Inician cambios de RGG
	function muestraPopupReincidenciaElemento()
	{	
			$( "#dialog-Reincidencia").dialog({
				resizable: true,
				width:500,
				height:270,					
				modal: true,
				buttons: {
					"Relacionar": function() {	
						if(deshabilitarCampos == false){
							var longitudTabla = jQuery("#gridReincidenciaElemento").getDataIDs();
							var numeroExpedientes = longitudTabla.length;
							
				       		if(numeroExpedientes>0){
				       			if(jQuery("#gridReincidenciaElemento").jqGrid('getGridParam','selarrrow') != ""){
									registrarReincidenciasXElemento();
				       			}else{
				       				window.parent.alertDinamico("Debe de seleccionar al menos un registro");
				       			}
							}
							else{
								window.parent.alertDinamico("No hay expedientes por relacionar");
							}
						}
						
					},

					"Cerrar": function() {
						$( "#dialog:ui-dialog" ).hide();
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();	
	}	
	
	//Inician cambios de RGG
	function muestraPopupReincidenciaElementoCaso()
	{	
			$( "#dialog-ReincidenciaCaso").dialog({
				resizable: true,
				width:500,
				height:270,					
				modal: true,
				buttons: {
					"Cerrar": function() {
						$( "#dialog:ui-dialog" ).hide();
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();	
	}	


	/*
	*Funcion para mandar consultar el vehiculo
	*/
	function consultaVehiculo()
	{
		//$('#uploadArchivo').hide();
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
    		data: 'idObjeto='+idVehiculo,
    		dataType: 'xml',
    		async: false,
    		cache: false,
    		success: function(xml){
    			if(parseInt($(xml).find('code').text())==0)
	    		{
    				var contenido = $(xml).find('contenido').text();
    				
    				if(contenido != null && contenido == ""){
    					$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
    				}
    				//seteamos la informacion del hecho
    				$(xml).find('VehiculoDTO').each(function(){
    						//if($(this).find('elementoId').text()==idVehiculo)
    						//{
    							seteaDatosVechiculo($(this));
    						//}
		    	      });
	    		}
    		}	
    	});

	}
	
	function seteaDatosVechiculo(xml)
	{
		$('#txtModeloVehiculo').val($(xml).find('modelo').text());
		$('#txtPlacasVehiculo').val($(xml).find('placa').text());
		$('#txtSerieVehiculo').val($(xml).find('noSerie').text());
		$('#txtMotorVehiculo').val($(xml).find('noMotor').text());
		$('#txtRegFedVehiculo').val($(xml).find('nrfv').text());
		if($(xml).find('esBlindado').text()=="true")
		{
        	$('#chkBoxBlindadoVehiculo').attr('checked','checked');
		}
		else
		{
			$('#chkBoxBlindadoVehiculo').attr('checked','');
		}
		$('#txtPuertas').val($(xml).find('noPuertas').text());
		$('#txtCilindrosVehiculo').val($(xml).find('noCilindros').text());
		if($(xml).find('almacen'))
			$(xml).find('almacen').remove();
		$("#txtBoxDescVehiculo").val($(xml).find('descripcion').text());
		//seteamos los combos
		$('#cbxTipoVehiculo').find("option[value='"+$(xml).find('valorByTipoVehiculo').find('idCampo').text()+"']").attr("selected","selected");
		cargaMarcasVehiculo();
		$('#cbxMarcaVehiculo').find("option[value='"+$(xml).find('valorByMarcaVal').find('idCampo').text()+"']").attr("selected","selected");
		cargaSubMarcasVehiculo();
		$('#cbxSubMarcaVehiculo').find("option[value='"+$(xml).find('valorBySubmarcaVal').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxPaisOrigenVehiculo').find("option[value='"+$(xml).find('valorByPaisOrigenVal').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxColorVehiculo').find("option[value='"+$(xml).find('valorByColorVal').find('idCampo').text()+"']").attr("selected","selected");
		$('#cbxCondicionVehiculo').find("option[value='"+$(xml).find('valorByCondicionFisicaVal').find('idCampo').text()+"']").attr("selected","selected");
		
		$("#txtPropietario").val($(xml).find('propietario').text());
		$('#cbxRelacionHecho').find("option[value='"+$(xml).find('relacionHechoVal').find('idCampo').text()+"']").attr("selected","selected");
	}
	
	/**
	*Funcion que limpia los combos que dependen del combo tipo de vehiculo
	*/
	function limpiaCombosDependsTipo(){
		$('#cbxMarcaVehiculo').empty();
		$('#cbxMarcaVehiculo').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
		$('#cbxSubMarcaVehiculo').empty();
		$('#cbxSubMarcaVehiculo').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
	}


	/**
	*Funcion que limpia los combos que dependen del combo tipo de la marca del vehiculo
	*/
	function limpiaCombosDependsMarca(){
		$('#cbxSubMarcaVehiculo').empty();
		$('#cbxSubMarcaVehiculo').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
	}
	
	/*
	*Funcion que realiza la carga del combo de tipos de vehiculo
	*/
	function cargaTiposVehiculo() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposVehiculo.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catTiposVehiculo').each(function(){
					$('#cbxTipoVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de marcas del vehiculo
	*/
	function cargaMarcasVehiculo() {

		var selected = $("#cbxTipoVehiculo option:selected");

		limpiaCombosDependsTipo();
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarMarcasVehiculo.do',
			data: 'glTipoVehiculoId=' + selected.val(),
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catMarcasVehiculo').each(function(){
					$('#cbxMarcaVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$("#cbxMarcaVehiculo").multiselect('refresh');
					});
			}
		});
		
	}

	/*
	*Funcion que realiza la carga de sub marcas del vehiculo
	*/
	function cargaSubMarcasVehiculo() {

		var selected = $("#cbxMarcaVehiculo option:selected");

		limpiaCombosDependsMarca();
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarSubMarcasVehiculo.do',
			data: 'glMarcaVehiculoId=' + selected.val(),
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catSubMarcasVehiculo').each(function(){
					$('#cbxSubMarcaVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#cbxSubMarcaVehiculo').multiselect('refresh');
					});
			}
		});
	}

	/*
	*Funcion que realiza la carga de colores del vehiculo
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
					$('#cbxColorVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	/*
	*Funcion que realiza la carga de colores del vehiculo
	*/
	function cargaRelacionesHecho() {
		  
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarRelacionesHecho.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catRelacionesHechos').each(function(){
					$('#cbxRelacionHecho').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
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
					$('#cbxPaisOrigenVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que realiza la carga de la condicion del vehiculo
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
					$('#cbxCondicionVehiculo').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	

	//Funcionalidad agregada a peticion de lupita (esta pendiente de aceptacion) 16/05/2011
	//function buscarExpedienteVehiculo() {
	//	$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
	//   	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do" width="653" height="400" />');		
	//}



	
	//function buscarCasoVehiculo(){
	//	$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
    //	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
	//}
	

//	COMIENZA FUNCIONES PARA EL GUARDADO DE LOS DATOS		
	function obtenerValoresVehiculo(){		
		var paramVehiculo = 'idVehiculo='+idVehiculo;
		paramVehiculo += '&glIdPaisOrigenId=' + $("#cbxPaisOrigenVehiculo option:selected").val();
		paramVehiculo += '&glColorVehiculoId=' + $("#cbxColorVehiculo option:selected").val();
		paramVehiculo += '&glMarcaVehiculoId=' +  $("#cbxMarcaVehiculo option:selected").val();
		paramVehiculo += '&glSubMarcaVehiculoId=' + $("#cbxSubMarcaVehiculo option:selected").val();
		paramVehiculo += '&glTipoVehiculoId=' + $("#cbxTipoVehiculo option:selected").val();
		paramVehiculo += '&gsModelo=' + $('#txtModeloVehiculo').val();
		paramVehiculo += '&gcNoPlaca=' + $('#txtPlacasVehiculo').val();
		paramVehiculo += '&gcNoSerie=' + $('#txtSerieVehiculo').val();
		paramVehiculo += '&gcNoMotor=' + $('#txtMotorVehiculo').val();
		paramVehiculo += '&gcNoRegFed=' + $('#txtRegFedVehiculo').val();
        paramVehiculo += '&gbEsBlindado=' + $('#chkBoxBlindadoVehiculo').is(':checked');		
		paramVehiculo += '&gsNoPuertas=' + $('#txtPuertas').val();
		paramVehiculo += '&gsNoCilindros=' + $('#txtCilindrosVehiculo').val();
		paramVehiculo += '&glCondicionFisica=' + $("#cbxCondicionVehiculo option:selected").val();
		paramVehiculo += '&gsDescripcion=' + $("#txtBoxDescVehiculo").val();
		if(cadenaCustodia!='null')
		{
			paramVehiculo += '&cadenaCustodia=' + cadenaCustodia;
			paramVehiculo += '&origenEvdCadCus=' + origenEvdCadCus;
			paramVehiculo += '&fechaLevCadCus=' + fechaLevCadCus;
		}
		paramVehiculo += '&propietario=' + $('#txtPropietario').val();
		paramVehiculo += '&relacionHechoId=' + $("#cbxRelacionHecho option:selected").val();
				
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/ingresarVehiculo.do?numeroExpediente='+numeroExpediente +'',
			data: paramVehiculo,
			dataType: 'xml',
			success: function(xml){
				  var tipoVehiculo = $("#cbxTipoVehiculo option:selected").text();
				  var placas = $(xml).find('VehiculoForm').find('gcNoPlaca').text();
				  var id = parseInt($(xml).find('VehiculoForm').find('glTipoVehiculoId').text());				  
				  if(idVehiculo==0 && id==0)
				  {
					  window.parent.alertDinamico("Favor de revisar la información capturada");
				  }
				  else if(idVehiculo==0 && id>0)
				  {
					  window.parent.alertDinamico("Se guardó correctamente la información");
					  enviaImagenDeElemento(id);					
					  window.parent.cargaVehiculo(id,tipoVehiculo,placas);					
				  }
				  else
				  {
					  enviaImagenDeElemento(id);
					  //window.parent.consultaGridVehiculosVisor();
					  //window.parent.cerrarVentanaVehiculo();
					  window.parent.cargaVehiculo(id,tipoVehiculo,placas);
					  window.parent.alertDinamico("La información se actualizó correctamente");
				  }
			}
		});
	}
	
	function validaCamposObligatorios(){
		var tipoVehiculo = $("#cbxTipoVehiculo option:selected").val();
		var marcaVehiculo = $("#cbxMarcaVehiculo option:selected").val();
		var subMarcaVehiculo = $("#cbxSubMarcaVehiculo option:selected").val();
		var colorVehiculo = $("#cbxColorVehiculo option:selected").val();
		var paisOrigenVehiculo = $("#cbxPaisOrigenVehiculo option:selected").val();
		var modeloVehiculo = $('#txtModeloVehiculo').val();		
		var condicionVehiculo = $("#cbxCondicionVehiculo option:selected").val();
		var relacionHechoVehiculo = $("#cbxRelacionHecho option:selected").val();	
		var mensaje = "";
		//Primera validacion por cada campo obligatorio		
		if(parseInt(tipoVehiculo) == -1){
			mensaje += "<br />- Tipo del vehículo";			
		}
		if(parseInt(marcaVehiculo) == -1){
			mensaje += "<br />- Marca del vehículo";			
		}
		if(parseInt(subMarcaVehiculo) == -1){
			mensaje += "<br />- Submarca del vehículo";			
		}
		if(parseInt(colorVehiculo) == -1){
			mensaje += "<br />- Color del vehículo";			
		}
		//if(parseInt(paisOrigenVehiculo) == -1){
		//	mensaje += "<br />- País de origen del vehículo";			
		//}
		if(modeloVehiculo == ""){
			mensaje += "<br />- Modelo del vehículo";			
		}
		if(parseInt(condicionVehiculo) == -1){
			mensaje += "<br />- Condición del vehículo";			
		}
		if(parseInt(relacionHechoVehiculo) == -1){
			mensaje += "<br />- Relación del hecho del vehículo";			
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
			obtenerValoresVehiculo();
		}
	}
	
	/*
	*Funcion que manda a eliminar logicamente el objeto en la BD
	*/
	function anularObjeto(){
		var paramVehiculo="idObjeto="+idVehiculo;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
			data: paramVehiculo,
			dataType: 'xml',
			success: function(xml){
				//procederemos a tratar de eliminar la evidencia
				if(parseInt($(xml).find('bandera').text())==1)
				{
					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
					window.parent.cargaVehiculo(idVehiculo,"","");
					window.parent.cerrarVentanaVehiculo();
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
		if(idVehiculo!=null && idVehiculo!=0)
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
		var paramVehiculo="idObjeto="+idVehiculo;
		$.ajax({		
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
			data: paramVehiculo,
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
	
<form id="profileForm" class="actionForm"  method="get">
	    		<table width="550px" height="448" border="0" cellspacing="0" cellpadding="0">
        			<tr>
        				<td>&nbsp;</td>
        			</tr>
            		<tr height="6.25%">
             			<td width="15%" height="21" class="txt_gral_victima" id="anularInv"></td>
						<td width="40%"></td>
					</tr>			
					<tr>
						<td colspan="3" align="center" width="80%">
							<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/>
							<input name="Button" type="button" id="btnGuardarVehiculo" class="btn_Generico" onclick="validaCamposObligatorios();" value="Guardar" />
               				<input type="button" id="btnBuscarReincidencia" value="Buscar reincidencia" class="btn_Generico" onclick="cargaGridReincidenciaElemento()"/>
                			<input type="button" id="btnConsultarExpedientes" value="Consultar expedientes relacionados " class="btn_Generico" onclick="cargaGridReincidenciasXElemento()"/>
                		</td>                
            		</tr>
              		<tr height="6.25%">
                		<td width="15%" height="21" align="right">&nbsp;</td>
                		<td width="40%">&nbsp;</td>                
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Tipo: </td>
                		<td width="40%"><select id="cbxTipoVehiculo" style="width:180px" onchange="cargaMarcasVehiculo();">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
                		<td width="45%" align="center">Descripci&oacute;n: </td>                
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Marca: </td>
                		<td width="40%"><select id="cbxMarcaVehiculo" style="width:180px" onchange="cargaSubMarcasVehiculo();">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
                		<td width="45%" rowspan="7" align="center"><textarea cols="25" rows="9" maxlength="199" id="txtBoxDescVehiculo" ></textarea></td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Submarca: </td>
                		<td width="40%"><select id="cbxSubMarcaVehiculo" style="width:180px">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Color: </td>
                		<td width="40%"><select id="cbxColorVehiculo" style="width:180px">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Pa&iacute;s de Origen: </td>
                		<td width="40%"><select id="cbxPaisOrigenVehiculo" style="width:180px">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">Modelo (a&ntilde;o): </td>
                		<td width="40%"><input type="text" id="txtModeloVehiculo" onKeyPress="return solonumeros(event);" maxlength="4" style="width:175px"/></td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">No. de Placas: </td>
                		<td width="40%"><input type="text" id="txtPlacasVehiculo" maxlength="10" style="width:175px"/></td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">No. de Motor: </td>
                		<td width="40%"><input type="text" id="txtMotorVehiculo" maxlength="20" style="width:175px"/></td>                
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" height="24" align="right">No. de Serie: </td>
                		<td width="40%"><input type="text" id="txtSerieVehiculo" maxlength="20" style="width:175px"/></td>
					</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">No. de Registro P&uacute;blico Vehicular: </td>
                		<td width="40%"><input type="text" id="txtRegFedVehiculo" maxlength="20" style="width:175px"/></td>
                		<td width="45%" align="center">Fotograf&iacute;a: </td>                              
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">No. de Puertas: </td>
                		<td width="40%"><input type="text" id="txtPuertas" style="width:175px" onKeyPress="return solonumeros(event);" maxlength="1"/></td>
                		<td width="45%" rowspan="6" align="center">
               				<img src="" alt="" width="100%" height="165" id="imgConFoto"/>
                		</td>               
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" align="right">No. de Cilindros: </td>
                		<td width="40%"><input type="text" id="txtCilindrosVehiculo" style="width:175px" onKeyPress="return solonumeros(event);" maxlength="1"/></td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" height="25" align="right">Blindado</td>
                		<td width="40%"><input type="checkbox" id="chkBoxBlindadoVehiculo"/></td>
              		</tr>
              		<tr height="6.25%">
                		<td width="15%" height="36" align="right">Condici&oacute;n del objeto: </td>
                		<td width="40%"><select name="cbxTipoJoya6" id="cbxCondicionVehiculo" style="width:180px">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
              		</tr>
               		<tr height="6.25%">
                		<td width="15%" height="36" align="right">Propietario: </td>
                		<td width="40%"><input type="text" id="txtPropietario" maxlength="100" style="width:175px"/></td>
              		</tr>
               		<tr height="6.25%">
                		<td width="15%" height="36" align="right">Relaci&oacute;n del hecho: </td>
                		<td width="40%"><select name="cbxRelacionHecho" id="cbxRelacionHecho" style="width:180px">
                  							<option value="-1">-Seleccione-</option>
                						</select>
                		</td>
              		</tr>              
              		<tr height="6.25%">
                		<td width="15%" align="right">&nbsp;</td>
                		<td width="40%">&nbsp;</td>
                		<td width="45%">&nbsp;</td>
              		</tr>
				</table>
</form>

<table width="550px" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" colspan="3" height="6.25%">
			<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoVehiculo.do" method="post" enctype="multipart/form-data" align="left">
				<input type="hidden" name="elementoID"/>
				<input type="file" name="archivo" id="uploadArchivo">
			</form>
		</td>
	</tr>			
</table>
         
         
<!-- ETIQUETAS PARA LA REINCIDENCIA DE UN OBJETO -->
<div id="dialog-Reincidencia" title="Reincidencia(s) asociada(s) al elemento">
	<table id="gridReincidenciaElemento"></table>
	<div id="paginadorReincidenciaElemento"></div>
</div>

<div id="dialog-ReincidenciaCaso" title="Reincidencia(s) registrada(s) al elemento">
	<table id="gridReincidenciaElementoCaso"></table>
	<div id="paginadorReincidenciaElementoCaso"></div>
</div>


</body>
</html>