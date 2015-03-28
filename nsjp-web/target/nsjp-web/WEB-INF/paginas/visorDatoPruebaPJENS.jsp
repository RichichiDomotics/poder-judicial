<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Visor Dato Prueba</title>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript">

	//Id del dato de prueba
	//var idDatoPrueba=0;
	var idDatoPrueba;
	var numeroExpediente;
	var archivoDigitalId="";
	var documentoId="";
	var idInvolucrado="";
	
	$(document).ready(function() {

		//Recuperamos el id de la audiencia ACTUAL
		idDatoPrueba=<%= request.getParameter("idDatoPrueba")%>;
		numeroExpediente="<%=request.getParameter("numeroExpediente")%>";
		//crea todas la tabs
		$("#tabsDatoPruebaPJENS").tabs();

		//Carga el detalle del dato de prueba
		consultarDatoPrueba(idDatoPrueba);
		//Carga el grid medios de prueba el id del datoDePrueba
		mediosPruebaAsociados(idDatoPrueba,numeroExpediente);
		
	});//FIN ON READY


	function consultarDatoPrueba(){
		
		$.ajax({
	   		type: 'POST',
	   		url: '<%= request.getContextPath()%>/consultarDatoPrueba.do',
	   		data:'datoPruebaId='+idDatoPrueba,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
		   		
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$("#nombreDatoPruebaDetallePJENS").val($(xml).find('nombreDato').first().text());
					$("#numeroIdDatoPruebaDetallePJENS").val($(xml).find('numeroIdentificacion').first().text());
					$("#rccDatoPruebaDetallePJENS").val($(xml).find('folioCadenaCustodia').first().text());
					var esAceptado = $(xml).find('esAceptado').first().text();
					
					if (esAceptado == "true"){
						//alert(esAceptado);
						$("#esAceptado").val("Si");
						
					}else{
						$("#esAceptado").val("No");
						$("#fechaCancelacionDatoPruebaDetallePJENS2").val($(xml).find('tiempoCancelacion').first().text());
						//cbx cbxMotivoCancelacionDetalleDatoPruebaPJENS
					}
					$("#descripcionDatoPruebaPJENS").val($(xml).find('descripcion').first().text());
				}
	   		}
		});
	}


	function consultarMedioPrueba(medioPruebaId){
		
		$.ajax({
	   		type: 'POST',
	   		url: '<%=request.getContextPath()%>/consultarMedioPrueba.do',
	   		data:'medioPruebaId='+medioPruebaId,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
		   		
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$("#nombreMedioPruebaDetallePJENS").val($(xml).find('nombreMedio').first().text());
					$("#numeroMedioPruebaDetallePJENS").val($(xml).find('numeroIdentificacion').first().text());
					$("#refUbicacionMedioPruebaDetallePJENS").val($(xml).find('ubicacionFisica').first().text());
					$("#descripcionMedioPruebaPJENS").val($(xml).find('descripcion').first().text());

					documentoId = $(xml).find('documentoMedioPrueba').find('documentoId').first().text();
					archivoDigitalId = $(xml).find('archivoDigital').find('archivoDigitalId').first().text();
					idInvolucrado = $(xml).find('elementoMedioPrueba').find('elementoId').first().text();
					
				}
	   		}
		});
	}
	

	/*
	*Carga el grid con los datos de prueba asociados al id del dato de prueba seleccionado por el usuario
	*/
	function mediosPruebaAsociados(){

		
		jQuery("#gridMedioPruebaAsociadoAlDatoPJENS").jqGrid({ 
			
			url:'<%=request.getContextPath()%>/consultarMediosPruebaAsociadosXDatoPrueba.do?idDatoPrueba='+idDatoPrueba+'&numeroExpediente='+numeroExpediente+'',
			datatype: "xml", 
			colNames:['Nombre del Medio','No. Identificación','Aceptado','Desechado'], 
			colModel:[ 					
			           	{name:'nombreMedio',index:'nombreMedio', width:230, align:'left'}, 
			           	{name:'numIdentificacion',index:'numIdentificacion', width:200, align:'left'},
			           	{name:'aceptado',index:'aceptado', width:100, align:'center'},
			           	{name:'desechado',index:'desechado', width:110, align:'center'},
					],
			pager: jQuery('#pagerGridMedioPruebaAsociadoAlDatoPJENS'),
			rowNum:10,
			rowList:[25,50,100],
			//autowidth: true,
			width:400,
			height:250,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			caption:"Medios Asociados",
			toolbar: [true,"top"],
			onSelectRow: function(rowid) {
				consultarMedioPrueba(rowid);				
			},
			toolbar: [true,"top"],
			loadComplete: function(){
				
				var medioPruebaIds=new Array();
				
				medioPruebaIds = jQuery("#gridMedioPruebaAsociadoAlDatoPJENS").getDataIDs();
				if(medioPruebaIds != null){
					if(medioPruebaIds.length > 0)
					consultarMedioPrueba(medioPruebaIds[0]);
				}
			}
		}).navGrid('#pagerGridMedioPruebaAsociadoAlDatoPJENS',{edit:false,add:false,del:false});
		
		$("#t_gridMedioPruebaAsociadoAlDatoPJENS").append("<input type='button' id='butVerMedio' value='Ver medio prueba' class='btn_Generico'/>");
		$("#butVerMedio","#t_gridMedioPruebaAsociadoAlDatoPJENS").click(function(){
				
				//var rowid = jQuery("#gridMedioPruebaAsociadoAlDatoPJENS").jqGrid('getGridParam','selrow');
				abrirPDF();					
		});

	}
	

	/*
	*Funcion para aceptar la ralacion entre dato prueba y el medio prueba
	*/
	function aceptarRelacion(relacionId){

		//alert("aceptar relacion="+relacionId);
		
		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/aceptarCancelarRelacion.do?esAceptado=true',
    		data:'relacionId='+relacionId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			alert("La relación ha sido aceptada");	
    			mediosPruebaAsociados();
    			window.parent.cargaGridPrueba();
    		}
		});

	}

	/*
	*Funcion para cancelar la ralacion entre dato prueba y el medio prueba
	*/
	function cancelarRelacion(relacionId){

		//alert("cancelar relacion="+relacionId);

		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/aceptarCancelarRelacion.do?esAceptado=false',
    		data:'relacionId='+relacionId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			alert("La relación ha sido desechada");	
    			mediosPruebaAsociados();
    			window.parent.cargaGridPrueba();
    		}
		});

	}

	function abrirPDF(){
		//alert("archivoDigitalId"+archivoDigitalId);
		//alert("documentoId"+documentoId);
		if(documentoId != "null" && documentoId != ""){
			if(archivoDigitalId != null && archivoDigitalId != ""){
				document.frmDoc.archivoDigitalId.value = archivoDigitalId;
				document.frmDoc.documentoId.value = documentoId;
				document.frmDoc.submit();
			}
		}
		else if(idInvolucrado !="null" && idInvolucrado !=""){
			consultarInvolucradoMedioPrueba();
		}
	}

	
	var idWindowConsultarInvolucradoMedioPrueba=1;

	/*
	*Funcion para abrir la ventana de consultar involucrado 
	*/
	function consultarInvolucradoMedioPrueba() {

		idWindowConsultarInvolucradoMedioPrueba++;
		
		var idWindow = "iframewindowConsultaInvolucrado" + idWindowConsultarInvolucradoMedioPrueba;
		
		testigo="testigo";
		$.newWindow({id:"iframewindowConsultaInvolucrado" + idWindowConsultarInvolucradoMedioPrueba, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar medio de prueba testigo", type:"iframe"});
	    $.updateWindowContent("iframewindowConsultaInvolucrado" + idWindowConsultarInvolucradoMedioPrueba,'<iframe src="<%= request.getContextPath() %>/IngresarTestigoPJENS.do?calidad='+testigo+'&idInvolucrado='+idInvolucrado+'&idWindow='+idWindow+'&bloquearModificacion='+true+'" width="1050" height="600" />');		
	}
	
	</script>
</head>
<body>

	<!--Tab Principal-->
	<div id="tabsDatoPruebaPJENS">
		<ul>
			<li id="pruebas">
				<a href="#tabsDatoPruebaPJENS-1">Dato de Prueba VS Medio de Prueba</a>
			</li>
		</ul>
		
		<!--Comienza Tab Dato de Prueba VS Medio de prueba-->
		<div id="tabsDatoPruebaPJENS-1">

			<table width="1200" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="200"><strong>Dato de prueba</strong></td>
					<td width="200">&nbsp;</td>
					<td width="200"><strong>Medio de prueba</strong></td>
					<td width="200">&nbsp;</td>
					<td width="400"><strong>Medios de prueba asociados</strong></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>Nombre Dato:</strong></td>
					<td><input type="text" id="nombreDatoPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right"><strong>Nombre Medio:</strong></td>
					<td><input type="text" id="nombreMedioPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td rowspan="9" align="left" valign="middle">
					<table id="gridMedioPruebaAsociadoAlDatoPJENS"></table>
					<div id="pagerGridMedioPruebaAsociadoAlDatoPJENS"></div>
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Número identificación:</strong></td>
					<td><input type="text" id="numeroIdDatoPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right"><strong>Número de identificación:</strong></td>
					<td><input type="text" id="numeroMedioPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"><strong>Reg. Cadena de custodia:</strong></td>
					<td><input type="text" id="rccDatoPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right"><strong>Ref. a ubicación física:</strong></td>
					<td><input type="text" id="refUbicacionMedioPruebaDetallePJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"><strong>Aceptado:</strong></td>
					<td>
						<!--<input type="radio" name="radio" id="esAceptado"-->
						<!--value="esAceptado" disabled="disabled"/>-->
						<input type="text" id="esAceptado"
						style="width: 20px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">
						<!--<strong>Fecha Cancelación:</strong>-->
					</td>
					<td>
						<!--<input type="text" id="fechaCancelacionDatoPruebaDetallePJENS2"	style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />-->
					</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">
<!--						<strong>Motivo Cancelacion:</strong></td>-->
					<td>
<!--						<select id="cbxMotivoCancelacionDetalleDatoPruebaPJENS"	style="width: 200px; border: 0; background: #DDD;" disabled="disabled"></select>-->
					</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>Descripción:</strong></td>
					<td>&nbsp;</td>
					<td align="right"><strong>Descripción:</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><textarea
						cols="45" rows="5"
						readonly="readonly" id="descripcionDatoPruebaPJENS"
						style="border: 0; background: #DDD;"></textarea></td>
					<td colspan="2" align="center"><textarea
						cols="45" rows="5"
						readonly="readonly" id="descripcionMedioPruebaPJENS"
						style="border: 0; background: #DDD;"></textarea></td>
				</tr>
				<tr>
					<!--<td align="right">&nbsp;</td>-->
					<!--<td>&nbsp;</td>-->
					<!--<td align="right">&nbsp;</td>-->
					<!--<td>&nbsp;</td>-->
					<!--<td>&nbsp;</td>-->
				</tr>
			</table>

		</div>
		<!--Termina Tab Dato de Prueba VS Medio de prueba-->
		
	</div>
	<!--Tab Principal-->
	
	<!--forma para consultar el archivo digital-->
	<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="archivoDigitalId" value=""/>
	<input type="hidden" name="documentoId" value="">
	</form>
	
</body>
</html>