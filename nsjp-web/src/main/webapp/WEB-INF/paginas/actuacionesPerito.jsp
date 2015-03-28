<!-- 
Visor atencion a solicitudes periciales
Funcionalidad para:
	-Ver los datos de la solicitud
	-Ver los documentos enviados por al AMP
	-Realizar actuaciones
	-Ver los documentos propios
 -->
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atención a solicitudes periciales</title>
	
	<!--Se importan las css necesarias-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	
	
<script type="text/javascript">
	
	/*
	*Variables Generales
	*/
	var documentoId; 
	var medidaId;
	
	/*
	*Variables para la ceja Documentos
	*/
	var primeraVezGridDocumentosDigitales = true;

	/*
	*Variables para la ceja Actuaciones
	*/
	var idWindowGenerarDictamenInforme=1;
	var idWindowSolicitudDeEvidencia=1;
	
	/*
	*On ready del documento
	*/	
	$(document).ready(function() {

		//Obtenemos parametros
		//documentoId = <%=request.getParameter("documentoId")%>;
		medidaId = <%=request.getParameter("medidaId")%>;
		//crear tabs		
		$("#tabsPrincipal").tabs();
		
		/*
		*LLamadas para la ceja documentos
		*/
		cargaGridDocumentosDigitales();

		/*
		*LLamadas para la ceja actuaciones
		*/		
		//Escuchador de cambio en el combo box
		$("#lstActuaciones").change(mostrarOcultarElementos);
		//Oculta inicialmente los elementos
		ocultaActuaciones();
		
	});//FIN ONREADY

/**************************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS************************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitales(){

		if(primeraVezGridDocumentosDigitales == true){
			jQuery("#gridDocumentosDigitales").jqGrid({
				url:'<%=request.getContextPath()%>/consultarDocumentosAsociadosAMedidaCautelar.do?medidaId='+medidaId+'',
				datatype: "xml", 
				colNames:['Nombre de Documento'],
				colModel:[ {name:'nombre',index:'nombre', width:255},
							],
				pager: jQuery('#pagerGridDocumentosDigitales'),
				rowNum:20,
				rowList:[10,20,30],
				width:250,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				//multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						abrirDocsDigAsociadosASol(rowid);							
					}
				}
			}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
			
			primeraVezGridDocumentosDigitales= false;
		}
		else{
			jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentosAsociadosAMedidaCautelar.do?medidaId='+medidaId+'',datatype: "xml" });
			$("#gridDocumentosDigitales").trigger("reloadGrid");
		}
	}

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
			$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?archivoDigitalId="+documentoAsocId+"&inFrame=true");
		}
		else{
			alertDinamico("El documento no puede ser mostrado");
		}
	}
	
/*******************************************************COMIENZA FUNCIONALIDAD PARA ACTUACIONES PERICIALES ***************************************************/
	
	/*************************************************************FUNCIONALIDAD COMUN*********************************************************************/
	
	/*
	*Funcion que oculta o muestra elementos en la 
	*pantalla dependiendo de la opcion seleccionada
	*/
	function mostrarOcultarElementos(){
		var selected = $("#lstActuaciones option:selected").val();

		//Oculta todo
		if(selected == "0"){
			ocultaActuaciones();
		}
		
		//Agregar documentos
		if(selected == "1"){
			$("#agregarDocs").show();
		}
		
		//Realizar dictamen
		if(selected == "2"){
			$("#agregarDocs").hide();
			controlGenerarDocumento('colaboracionCumplimiento');
		}
		//Realizar dictamen
		if(selected == "3"){
			$("#agregarDocs").hide();
			controlGenerarDocumento('incumplimientoMedida');
		}

		//solicitud de evidencia
		if(selected == "4"){
			$("#agregarDocs").hide();
			controlGenerarDocumento('cumplimientoMedida');
		}
	}


	/*
	*Funcion que oculta todos los elementos de la pantalla
	*/
	function ocultaActuaciones(){
		
		$("#agregarDocs").hide();
	}

	/********************************************************FUNCIONALIDAD PARA ANEXAR DOCUMENTOS*******************************************************/
	
	/*
	*Funcion para anexar un documento a la solicitud pericial
	*/
	function anexarDocumento(){

		
		//forma = document.anexarDocumentoForm; 
		//forma.documentoId.value = documentoId;
		//forma.submit();
			
	}


	/********************************************************FUNCIONALIDAD PARA REALIZAR DICTAMEN E INFORME***********************************************/
	
	/*
	*Funcion que crea el documento recibe el tipo de documento
	*que se desea crear
	*/
	function crearDocumento(forma){

		var documentoId = "";
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/crearDocumentoParaMedidaCautelar.do',
			data: 'formaId='+forma,
			dataType: 'xml',
			async: false,
			success: function(xml){

				var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){
					documentoId = $(xml).find('documentoId').first().text();					
					//$("#gridSolicitud").trigger("reloadGrid");
				}
				else{
					//Mostrar mensaje de error
				}
			}
		});
		return documentoId;
	}

	
	/*
	*Funcion que sube el expediente a sesion
	*param:Id de la forma que se desea crear
	*return;numero de expediente
	*/
	function subirExpedienteSesion(){
		
		//colocar el expediente en sesion
		exp = "";
		$.ajax({
	   		type: 'POST',
	   		url: '<%=request.getContextPath()%>/colocarDocumentoParaMedidaCautelar.do',
	   		data: 'medidaId='+medidaId,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					exp = $(xml).find('numeroExpediente').first().text();
					
					
	   			}
				else{
					//Mostrar mensaje de error
				}
	   		}
	    }); 
		return exp;
	}

	
	/*
	*Funcion que abre el visor para generar documentos 
	*/
	
	function abrirDocumento(documentoId,exp){

		idWindowGenerarDictamenInforme++;
		$.newWindow({id:"iframewindowGenerarDocumentoPericial"+idWindowGenerarDictamenInforme, statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Realizar Documento", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumentoPericial"+idWindowGenerarDictamenInforme,"<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+documentoId+"&numeroUnicoExpediente="+exp+"' width='1140' height='400' />");
	    
	    
	}
	/**
	* Función que es llamada por el generador de documentos cuando se realiza un guardado definitivo del documento
	* Esta función actualiza el estado de la solicitud pericial y adjunta el archivo digital recién generado a las solicitudes padre
	*/
	function documentoGenerado(){
		
		$.ajax({
	   		type: 'POST',
	   		url: '<%=request.getContextPath()%>/asociarMedidaCautelarConArchivoDigital.do',
	   		data: 'medidaId='+medidaId+'&documentoId='+documentoId,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					
					
					
	   			}
				else{
					//Mostrar mensaje de error
				}
	   		}
	    }); 
		
		
	}

	
	/*
	*Funcion que controla la creacion de un documento
	*param:String tipo de documento
	*/
	function controlGenerarDocumento(tipo){	
		
		if(tipo == 'colaboracionCumplimiento'){
			forma = <%=Formas.DICTAMEN_PERICIAL.getValorId()%>;
		}
		if(tipo == 'incumplimientoMedida'){
			forma = <%=Formas.INCUMPLIMIETO_DE_MEDIDA_CAUTELAR.getValorId()%>;
		}
		if(tipo == 'cumplimientoMedida'){
			forma = <%=Formas.CUMPLIMIENTO_DE_MEDIDAS_CAUTELARES.getValorId()%>;
		}
		
		var documentoId =crearDocumento(forma);
		var exp = subirExpedienteSesion(forma);
		abrirDocumento(documentoId,exp);
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
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-2">Documentos</a></li>
					<li><a href="#tabsconsultaprincipal-3">Actuaciones</a></li>
				</ul>
				
				<!--Comienza div ver los documentos relacionados a la solicitud-->
				<div id="tabsconsultaprincipal-2">
				
					<table width="1150"  height="530" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="250" align="center" valign="top">
	                        <table id="gridDocumentosDigitales"></table>
	                        <div id="pagerGridDocumentosDigitales"></div>
		                </td>
		                <td width="900" align="center" valign="top">
		               	  <iframe id='visorDocsFrame' width="900" height="500" src="">		               	  
		               	  </iframe>
		                </td>
		              </tr>
		            </table>
		            
				</div>
				<!--Termina div para adjuntar documentos al enviar la solicitud-->
				
				<!--Comienza div para las actuaciones del perito-->
				<div id="tabsconsultaprincipal-3">
				
					<table width="1150" height="550" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td height="20" align="center">
					    	<fieldset style="width: 1150px;">
								<legend>Actuaciones:</legend>
									<table width="100%" border="0" height="90%">
										<tr>
											<td>
												<select id="lstActuaciones" style="width:200px;">
													<option value="0">-Seleccione-</option>
													<option value="1">Adjuntar archivo digital</option>
													<option value="2">Oficio de colaboraci&oacute;n para el cumplimiento de medida cautelar </option>
													<option value="3">Oficio de incumplimiento de medida cautelar</option>
													<option value="4">Oficio de cumplimiento de medida cautelar</option>
										        </select>
											</td>
										</tr>
									</table>
							</fieldset>
					    </td>
					  </tr>
					  <tr>
					    <td height="530" valign="top">
					       
					       <!--Comienza Agregar Documentos-->
					    	<div id="agregarDocs">
					    	
					    		<table border="0">
					    			<tr>
					    				<td align="right"><span class="au av ra rc ta" ><strong>Ruta su documento digital:</strong></span></td>
								        <td>
								        	<div id="divAdjuntarDoc" class="au av ra rc ta">
									        	<form id="anexarDocumentoForm" name="anexarDocumentoForm" 
									        	action="<%= request.getContextPath() %>/registrarSolicitudPJATP.do" method="post" enctype="multipart/form-data" >
													<input type="file" name="archivoAdjunto" > 
													<input type="hidden" name="documentoId"/>
												</form>
											</div>
								        </td>
					    			</tr>
					    			<tr>
					    				<td></td>
					    				<td>
					    					<input id="anexarDoc" type="button" value="Anexar" onclick="anexarDocumento();" class="btn_Generico"/>
					    					<input id="limpiarAnexarDoc" type="button" value="Limpiar" class="btn_Generico"/>
					    				</td>
					    			</tr>
					    		</table>
					    		
					       	</div>
					       	<!--Comienza Agregar Documentos-->
					       	
					    </td>
					  </tr>
					</table>
										
				</div>
				<!--Termina div para las actuaciones-->
				
			</div>
		</td>
	</tr>
</table>
</body>
</html>