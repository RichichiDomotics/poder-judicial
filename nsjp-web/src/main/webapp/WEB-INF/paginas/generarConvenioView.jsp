<%@page import="mx.gob.segob.nsjp.comun.enums.convenios.Convenios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Generar convenio</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/currencyFormatMask.js"></script>
	<!-- Lanza nuevas ventanas -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>  

	<script type="text/javascript">
	
	var idNumeroExpedienteOp;
	var numeroExpediente;
	var area;
	var idRenglon=1;

		$(document).ready(function() {
			
			//guardamos las variables del paso de parametros
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			idNumeroExpedienteOp='<%= request.getParameter("numeroExpedienteId")%>';
			area='<%= request.getParameter("area")%>';
			
			$("#fechaInicio").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
		
			$("#fechaFin").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			$("#fechaPago").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			jQuery("#gridPagos").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['','Cantidad por pago','Fecha pago'], 
				colModel:[	{name:'act',index:'act', width:15,sortable:false,hidden:false},
				           	{name:'Cantidad',index:'cantidad', width:100}, 
							{name:'Fecha',index:'fecha', width:100}
						],
				pager: jQuery('#pagerGridPagos'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE PAGOS",
				sortname: 'fecha',
				viewrecords: true,
				gridComplete: function(){
					  var ids = jQuery("#gridPagos").jqGrid('getDataIDs');
					  for(var i=0;i < ids.length;i++){
						var cl = ids[i];
					 
						el = 
							"<img alt='Eliminar Pago' width='15' height='15' src='<%= request.getContextPath() %>/resources/css/wdCalendar/images/icons/delete.png' onclick=\"jQuery('#gridPagos').jqGrid('delRowData',"+cl+");\">"; 
						jQuery("#gridPagos").jqGrid('setRowData',ids[i],{act:el});
					  }	
				    },
				sortorder: "desc"
			});
			$("#tableInformacionGeneral").hide();
			//cargamos el combo ocn los responsables
			cargaComboProbableResp();
			cargaComboVictimas();
			
			$("#btnGenerarConvenio").click(enviarDatosDeConvenio);
		});
		//termina funcion onready

		/*
			*#######################
			* INNER LAYOUT SETTINGS
			*#######################
			*
			* These settings are set in 'list format' - no nested data-structures
			* Default settings are specified with just their name, like: fxName:"slide"
			* Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
			*/
			layoutSettings_Inner = {
				applyDefaultStyles:				false // basic styling for testing & demo purposes
			,	minSize:						50 // TESTING ONLY
			,	spacing_closed:					14
			,	north__spacing_closed:			8
			,	south__spacing_closed:			8
			,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
			,	south__togglerLength_closed:	-1
			,	fxName:							"slide" // do not confuse with "slidable" option!
			,	fxSpeed_open:					1000
			,	fxSpeed_close:					2500
			,	fxSettings_open:				{ easing: "easeInQuint" }
			,	fxSettings_close:				{ easing: "easeOutQuint" }
			,	north__fxName:					"none"
			,	south__fxName:					"drop"
			,	south__fxSpeed_open:			500
			,	south__fxSpeed_close:			1000
			//,	initClosed:						true
			,	center__minWidth:				200
			,	center__minHeight:				200
			};
			/*
			*#######################
			* OUTER LAYOUT SETTINGS
			*#######################
			*
			* This configuration illustrates how extensively the layout can be customized
			* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
			*
			* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
			* All default settings (applied to all panes) go inside the defaults:{} key
			* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
			*/
			var layoutSettings_Outer = {
				name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
				// options.defaults apply to ALL PANES - but overridden by pane-specific settings
			,	defaults: {
					size:					"auto"
				,	minSize:				50
				,	paneClass:				"pane" 		// default = 'ui-layout-pane'
				,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
				,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
				,	buttonClass:			"button"	// default = 'ui-layout-button'
				,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
				,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
				,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
				,	togglerLength_closed:	35			// "100%" OR -1 = full height
				,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
				,	togglerTip_open:		"Close This Pane"
				,	togglerTip_closed:		"Open This Pane"
				,	resizerTip:				"Resize This Pane"
				//	effect defaults - overridden on some panes
				,	fxName:					"slide"		// none, slide, drop, scale
				,	fxSpeed_open:			750
				,	fxSpeed_close:			1500
				,	fxSettings_open:		{ easing: "easeInQuint" }
				,	fxSettings_close:		{ easing: "easeOutQuint" }
			}
			,	north: {
					spacing_open:			1			// cosmetic spacing
				,	togglerLength_open:		0			// HIDE the toggler button
				,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
				,	resizable: 				false
				,	slidable:				false
				//	override default effect
				,	fxName:					"none"
				}
			,	south: {
					maxSize:				200
				,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
				,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
				,	initClosed:				false
				}
			,	west: {
					size:					250
				,	spacing_closed:			21			// wider space when closed
				,	togglerLength_closed:	21			// make toggler 'square' - 21x21
				,	togglerAlign_closed:	"top"		// align to top of resizer
				,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
				,	togglerTip_open:		"Close West Pane"
				,	togglerTip_closed:		"Open West Pane"
				,	resizerTip_open:		"Resize West Pane"
				,	slideTrigger_open:		"click" 	// default
				,	initClosed:				false
				//	add 'bounce' option to default 'slide' effect
				,	fxSettings_open:		{ easing: "" }
				,	west__onresize:		function () { $("#accordionmenuprincipal").accordion("resize"); }
				}
			,	east: {
					size:					250
				,	spacing_closed:			21			// wider space when closed
				,	togglerLength_closed:	21			// make toggler 'square' - 21x21
				,	togglerAlign_closed:	"top"		// align to top of resizer
				,	togglerLength_open:		0 			// NONE - using custom togglers INSIDE east-pane
				,	togglerTip_open:		"Close East Pane"
				,	togglerTip_closed:		"Open East Pane"
				,	resizerTip_open:		"Resize East Pane"
				,	slideTrigger_open:		"mouseover"
				,	initClosed:				false
				//	override default effect, speed, and settings
				,	fxName:					"drop"
				,	fxSpeed:				"normal"
				,	fxSettings:				{ easing: "" } // nullify default easing
				,	est__onresize:		function () { $("#accordionmenuderprincipal").accordion("resize"); }		
				}
			,	center: {
					paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
				,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes	
				,	minWidth:				200
				,	minHeight:				200
				,	onresize_end:			function () { $("#gridDetalleFrmPrincipal").setGridWidth($("#mainContent").width() - 5, true); }
				,	onresize_end:			function () { $("#gridDetalleFrmSecundario").setGridWidth($("#mainContent").width() - 5, true); }
				}
			};

		
		/*
		*Funcion para llenar los combobox de Probable Responsable y Victimas del expediente
		*/
		function cargaComboProbableResp()
		{
			$('#cbxProbResponsable').empty();
			$('#cbxProbResponsable').append('<option value="-1" selected="selected">-Seleccione-</option>');

			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=PROBABLE_RESPONSABLE',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaProbResps=0;
					$(xml).find('listaInvolucrados').find('involucradoDTO').each(function(){
			              var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
						  nombreCompleto+=$(this).find('apellidoPaterno').text()+" ";
						  nombreCompleto+=$(this).find('apellidoMaterno').text()+" ";
						  
			              if($(this).find('valorIdCalidad').find('idCampo').text()=="213")//PR
			              {
							$('#cbxProbResponsable').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');	  
			              }
					});
				}
			});
		}
		
		function cargaComboVictimas()
		{
			$('#cbxVictima').empty();
			$('#cbxVictima').append('<option value="-1" selected="selected">-Seleccione-</option>');

			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=VICTIMA',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaProbResps=0;
					$(xml).find('listaInvolucrados').find('involucradoDTO').each(function(){
			              var nombreCompleto="";
						  if($(this).find('valorIdCalidad').find('idCampo').text()=="214")//victima
			              {
							  nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
							  nombreCompleto+=$(this).find('apellidoPaterno').text()+" ";
							  nombreCompleto+=$(this).find('apellidoMaterno').text()+" ";
			            	  $('#cbxVictima').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
			              }
						  else if($(this).find('valorIdCalidad').find('idCampo').text()=="215")//denunciante-victima
			              {
							  nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombreDemografico').find('nombre').text()+" ";
							  nombreCompleto+=$(this).find('nombresDemograficoDTO').find('nombreDemografico').find('apellidoPaterno').text()+" ";
							  nombreCompleto+=$(this).find('nombresDemograficoDTO').find('nombreDemografico').find('apellidoMaterno').text()+" ";
			            	  $('#cbxVictima').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
			              }
					});
				}
			});
		}

	
	/*
	*Funcion que valida la informacion requerida para realizar la captura de la informacion 
	*general del convenio
	*/
	function capturarConvenio()
	{
		var idPR=$("#cbxProbResponsable option:selected").val();
		var idVictima=$("#cbxVictima option:selected").val();
		
		if(parseInt(idPR)!=-1 && parseInt(idVictima)!=-1)
		{
			$("#tableInformacionGeneral").show();	
		}
		else
		{
			customAlert("Debe seleccionar el nombre del probable responsable y el nombre de la víctima, para los cuales se generará el convenio");
		}
	}
	
	/*
	*Funcion que valida la inforamcion requerida para ingresar un pago
	*/
	function  validaIngresarPago()
	{
		if($("#fechaInicio").val()=="" || $("#fechaInicio").val().length==0)
		{
			return false;
		}
		if($("#fechaFin").val()=="" || $("#fechaFin").val().length==0)
		{
			return false;
		}
		if($("#txtBox").val()=="" || $("#txtBox").val().length<2)
		{
			return false;
		}
		return true;
	}
	
	/*
	*Funcion que lanza el popup para capturar los datos de un pago y posteriormente
	*agregarlos al grid del convenio, para ser persistidos en un paso posterior en la BD
	*/
	function ingresarPago()
	{
		if(validaIngresarPago())
		{
			//generamos el Dialogo
			$( "#dialogDos-confirm" ).dialog({
				resizable: false,
				height:150,
				width:700,
				modal: true,
				buttons: {
					"Guardar": function() {
						agregaPagoAGrid($("#txtBoxPagoPoppup").val(),$("#fechaPago").val());
						$("#txtBoxPagoPoppup,#fechaPago").val("");
						$( this ).dialog( "close" );
						$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}
		else
		{
			customAlert("Debe seleccionar la fecha de inicio, la fecha de fin y la cantidad total a pagar del convenio");
		}
	}
	
	/*
	*Funcion que agrega los datos de un pago al grid correspondiente
	*/
	function agregaPagoAGrid(pago,fecha)
	{
		if($("#fechaPago").val()=="" || $("#fechaPago").val().length==0)
		{
			customAlert("Debe proporcionar la Fecha de Pago y el monto");
			return;
		}
		if($("#txtBoxPagoPoppup").val()=="" || $("#txtBoxPagoPoppup").val().length<2)
		{
			customAlert("Debe proporcionar la Fecha de Pago y el monto");
			return;
		}
		var mydata = [
					  {id:idRenglon,Cantidad:pago,Fecha:fecha}						  
	              ];
		for(var i=0;i<=mydata.length;i++)
			jQuery("#gridPagos").jqGrid('addRowData',idRenglon,mydata[i]);
		idRenglon++;
	}
	
	function generarConvenioDocumento(idConvenio)
	{
		$.newWindow({id:"iframewindowElaborarDocumentoConvenio", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Generar Convenio", type:"iframe"});
        $.updateWindowContent("iframewindowElaborarDocumentoConvenio","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+idConvenio+"&numeroUnicoExpediente="+numeroExpediente+"' width='1140' height='550' />");
	}
	
	function eliminaMascaraMonedaNacional(monto){
		monto = monto.replace("$","");
		monto = monto.replace(",","");
		return monto;		
	}
	
	
	function obtenIdsInformacionDePagos(){		
		var datos = "";
		var lista = jQuery("#gridPagos").getDataIDs();
		for(i=0;i<lista.length;i++){
				rowData= jQuery("#gridPagos").jqGrid('getRowData',lista[i]);
				datos = datos + eliminaMascaraMonedaNacional(rowData.Cantidad) + "," + rowData.Fecha + "-"; 
		 }
		return datos;			
	}		
	
	
	function validaPagos(){
		var montoTotalDePagos = 0;
		var lista = jQuery("#gridPagos").getDataIDs();
		for(i=0;i<lista.length;i++){
				rowData= jQuery("#gridPagos").jqGrid('getRowData',lista[i]);
				montoTotalDePagos = montoTotalDePagos + parseInt(eliminaMascaraMonedaNacional(rowData.Cantidad));
		 }
		if(montoTotalDePagos == parseInt(eliminaMascaraMonedaNacional($('#txtBox').val()))){
			return true;
		}
		else			
			return false;
		
	}
	
    function enviarDatosDeConvenio(){
    	if(validaPagos()){

    		var params = '&cbxProbResponsable='  + parseInt($("#cbxProbResponsable option:selected").val());
    	    params += '&cbxVictima=' + parseInt($("#cbxVictima option:selected").val());
    	    params += '&fechaInicio=' + $('#fechaInicio').val();
    	    params += '&fechaFin=' + $('#fechaFin').val();
    	    params += '&txtCantidadTotal=' + eliminaMascaraMonedaNacional($('#txtBox').val());
    	    params += '&tipoConvenio=' + $(':radio[name=tipoConvenio]:checked').val()//Usuarios Externos;
    	    params += '&pagos=' + obtenIdsInformacionDePagos();    
    	    params += '&numeroExpediente=' + numeroExpediente;    
    	    
    	    $.ajax({
    			type: 'POST',
    			url: '<%= request.getContextPath()%>/registrarConvenio.do',
    			data: params, 
    			async: false,
    			dataType: 'xml',
    			success: function(xml){										
    				 if(parseInt($(xml).find('ConvenioDTO').find('convenioID').text())>0){
    					 customAlert("El convenio se registró correctamente");
    					 $("#btnGenerarConvenio").attr("disabled","disabled");
    					 generarConvenioDocumento(parseInt($(xml).find('ConvenioDTO').find('convenioID').text()));
    				 }
    					
    				 
    				 else
    					  customAlert('Error al intentar guardar el convenio, inténtelo mas tarde');
    			}
    		});    
    	}else{
    		customAlert("El monto total de los pagos no coincide con la cantidad total a pagar")
    	}
    }
	</script>

</head>
<body bgcolor="#CCCCCC">   
	<br/>
	<table border="0" width="100%">
		<tr>
			<td align="right"><bean:message key="selProbableResponsable"/> : 
			</td>
			<td>
				<select id="cbxProbResponsable">
					<option value="-1" selected="selected">-Seleccione-</option>
				</select>
			</td>
			<td>Seleccione V&iacute;ctima : 
			</td>
			<td>
				<select id="cbxVictima">
					<option value="-1" selected="selected">-Seleccione-</option>
				</select>
			</td>
			<td>
				<input type="button" value="Capturar Convenio" onclick="capturarConvenio();" class="btn_Generico"/>
			</td>
		</tr>
	</table>
	<br/>
	<table border="0" width="90%" id="tableInformacionGeneral">
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left"><table width="200" border="0" align="center">
  <tr>
    <td align="center">Conciliación</td>
  </tr>
  <tr>
    <td align="center"><input type="radio" name="tipoConvenio" value="<%=Convenios.CONCILIACION.getValorId()%>" id="radConvenio" checked="checked"/></td>
  </tr>
</table>
</td>
			<td align="right">&nbsp;</td>
			<td align="left"><table width="200" border="0"  align="center">
  <tr>
    <td align="center">Mediación</td>
  </tr>
  <tr>
    <td align="center"><input type="radio" name="tipoConvenio" value="<%=Convenios.MEDIACION.getValorId()%>" id="radMediacion"/></td>
  </tr>
</table></td>
			<td align="right">Cantidad total a pagar :</td>
			<td align="left">
				 <input type="textbox" style="text-align:right" id="txtBox"  
         onkeyup="javascript:return mask(this.value,this);"  
         onblur="javascript:return mask(this.value,this);"/>  
			</td>
		</tr>
		<tr>
			<td>Fecha de inicio :</td>
			<td><input type="text" id="fechaInicio" name="fechaInicio" maxlength="10" readonly="readonly" style="width: 180px;" /></td>
			<td>Fecha de fin : </td>
			<td><input type="text" id="fechaFin" name="fechaFin" maxlength="10" readonly="readonly" style="width: 180px;" /></td>
			<td></td>
			<td align="left">
				<input type="button" id="btnIngresaPago" onclick="ingresarPago();" value="Ingresar pago" class="btn_Generico"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
			<td colspan="2">
				<table id="gridPagos" width="370px"></table>
				<div id="pagerGridPagos"></div>
			</td>
			<td colspan="2" align="right">
				<input type="button" id="btnGenerarConvenio" value="Generar convenio" class="btn_Generico"/>
			</td>
		</tr>
	</table>
	<br/>
	<!-- DIV para el dialogo de asociacion de un delito por persona -->
	<div id="dialogDos-confirm" title="Ingresar Pago" >
		<p align="left">
			Fecha de pago : <input type="text" id="fechaPago" name="fechaPago" maxlength="10" readonly="readonly" style="width: 180px;" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Cantidad a pagar :  <input type="textbox" style="text-align:right" id="txtBoxPagoPoppup"  
              onkeyup="javascript:return mask(this.value,this);"  
              onblur="javascript:return mask(this.value,this);"/> 
		</p>
	</div>
	<!-- FIN DIV para el dialogo de asociacion de un delito por persona -->
</body>
<script type="text/javascript">
$( "#dialogDos-confirm" ).dialog();
$( "#dialogDos-confirm" ).dialog( "destroy" );
</script>
</html>