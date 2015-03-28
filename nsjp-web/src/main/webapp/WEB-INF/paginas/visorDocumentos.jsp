<!-- 
Visor atencion a solicitudes periciales
Funcionalidad para:
	-Ver los datos de la solicitud
	-Ver los documentos enviados por el AMP
	-Realizar actuaciones
	-Ver los documentos propios
 -->
<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
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
	var solicitudId;
	
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
	*Variables para la ceja Documentos Propios
	*/
	var primeraVezGridDocumentosDigitalesPropios = true;
	
	var tipoCalidad;
	/*
	*On ready del documento
	*/	
	$(document).ready(function() {

		//Obtenemos parametros
		documentoId = <%=request.getParameter("documentoId")%>;
		solicitudId = <%=request.getParameter("solicitudId")%>;
		numExpedienteId = <%=request.getParameter("numExpedienteId")%>;
		tipoCalidad = <%=Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId()%>;
		//crear tabs		
		$("#tabsPrincipal").tabs();
		/*
		*LLamadas para la ceja documentos propios
		*/
		cargaGridDocumentosDigitalesPropios();
		pintaDetalle();
	});//FIN ONREADY

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
			$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId+"&inFrame=true");
		}
		else{
			alertDinamico("El documento no puede ser mostrado");
		}
	}
	
/*******************************************************COMIENZA FUNCIONALIDAD PARA ACTUACIONES PERICIALES ***************************************************/


	

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
	
	
	/*******************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS PROPIOS**********************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitalesPropios(){ 

		if(primeraVezGridDocumentosDigitalesPropios == true){
			jQuery("#gridDocumentosDigitalesPropios").jqGrid({
				url:'<%=request.getContextPath()%>/ConsultaExpedientesDocumento.do?numeroExpedienteId='+numExpedienteId+'',
				datatype: "xml", 
				colNames:['Nombre de Documento'],
				colModel:[	{name:'nombre',index:'nombre', width:255},
				       
							],
				pager: jQuery('#pagerGridDocumentosDigitalesPropios'),
				rowNum:20,
				rowList:[10,20,30],
				width:250,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				//multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						//abrirDocsDigAsociadosASolPropios(rowid);	
						abrirDocsDigAsociadosASol(rowid);						
					}
				},
				loadComplete: function(){
					jQuery("#gridDocumentosDigitalesPropios").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
				}
			}).navGrid('#pagerGridDocumentosDigitalesPropios',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitalesPropios .ui-jqgrid-bdiv").css('height', '455px');
			primeraVezGridDocumentosDigitalesPropios= false;
		}
		else{
			jQuery("#gridDocumentosDigitalesPropios").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentosPropiosAsociadosASolicitudPericial.do?numeroExpedienteId='+numExpedienteId+'',datatype: "xml" });
			$("#gridDocumentosDigitalesPropios").trigger("reloadGrid");
		}
	}	

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASolPropios(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
		$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId+"&inFrame=true");
			
		}
		else{
			alertDinamico("El documento no puede ser mostrado");
		}
	}

	function pintaDetalle(){
		var param =parent.numCausa;
		 $.ajax({
		      type: 'POST',
	    	  url: '<%= request.getContextPath()%>/visorDocumentoDetalle.do',
	    	  data: 'numExpediente='+param,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		 //        
		    	$('#numCausaDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());

		    	//$('#MPDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());

		    	$('#numCasoDetalle').val($(xml).find('expedienteDto').find('casoDTO').find('numeroGeneralCaso').text());

		    	//$('#defensorDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());

		    	$('#fCreacionDetalle').val($(xml).find('expedienteDto').find('casoDTO').find('fechaApertura').text());

		    	//$('#JuezDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());

		    	$(xml).find('expedienteDto').find('delitos').find('delitoDto').each(function(){  
    				$('#delitosDetalle').append('<option>'+ $(this).find('catDelitoDTO').find('nombre').text() + '</option>');
    			});

		    	$(xml).find('expedienteDto').find('involucradosDTO').find('involucradoDTO').find('nombresDemograficoDTO').find('nombreDemograficoDTO').each(function(){ 
					
						
						
						$('#imputadoDetalle').append('<option>'+ $(this).find('nombre').text() +" "+ $(this).find('apellidoPaterno').text() +" "+ $(this).find('apellidoMaterno').text() + '</option>');
						

    				
    			});
	    	  }
	    	});
	}
</script>

</head>

<body>

			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-2">Detalle</a></li>
					
					<li><a href="#tabsconsultaprincipal-1">Documentos</a></li>
					
				</ul>
				
		<!--Comienza div para ver los documentos propios del Documento-->
				<div id="tabsconsultaprincipal-1">
					
					<table width="1150"  height="530" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="250" align="center" valign="top">
	                        <table id="gridDocumentosDigitalesPropios"></table>
	                        <div id="pagerGridDocumentosDigitalesPropios"></div>
		                </td>
		                <td width="900" align="center" valign="top">
		               
		                
		               	  <iframe id='visorDocsPropiosFrame' width="900" height="500" src="" scrolling="auto" style="display: ;">		               	  
		               	  </iframe>  
		                </td>
		              </tr>
		            </table>
					
				</div>
				
				<div id="tabsconsultaprincipal-2">
		
		
					<table width="98%" border="0">
  <tr>
    <td width="16%" align="right">Numero de Causa:</td>
    <td width="19%">
      <input type="text" name="textfield" id="numCausaDetalle" disabled="disabled" size="30"/> 
    </td>
    <td width="13%" align="right" >Numero de Caso:</td>
    <td width="18%"><input type="text" name="textfield2" id="numCasoDetalle" disabled="disabled" size="30"/></td>
    <td width="15%" align="right">Fecha de Creación:</td>
    <td width="19%"><input type="text" name="textfield3" id="fCreacionDetalle"  disabled="disabled" size="30"/></td>
  </tr>
 
  <tr>
    <td align="right">Imputados:</td>
    <td>
      <select name="select" id="imputadoDetalle" multiple="multiple" disabled="disabled" size="5">
      </select>
   </td>
    <td align="right">Delitos:</td>
    <td><select name="select2" id="delitosDetalle" multiple="multiple" disabled="disabled" size="5">
    </select></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
				</div>		
				</div>	
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center"><span id="divAlertTexto"></span></td>
			</tr>
		</table>
	</div>
</body>
</html>