<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud</title>

<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--script de jquery UI-->
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script> -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prettify.js"></script>

<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script> -->

<!--Hojas de estilos para los componentes UI de Jquery-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--Hoja de estilos para el grid-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />

<!--scripts de java script-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>-->



<!--Hoka de estilo para el texto dentro de los acordeones-->
<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<!--Hoja de estilo para los popups-->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--Scripts necesarios para el funcionamiento de la JSP-->

<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script> -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<!--Scrip para el idioma del calendario-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<!--Scripts necesarios para la ejecucion del editor-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>

<!--scripts del gird-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<script type="text/javascript">
	
	//variables globales
 	var idExpedienteop="";
	var numeroCaso="";
 	var idRelacionDelito="";
 	var idTipoSolicitud=0;
 	var numeroUnicoExpediente="";
 	
	//Comienza funcion on ready del documento
	$(document).ready(function() {
		//seteamos las variables globales
		idExpedienteop='<%= request.getParameter("idExpedienteop")%>';	
		numeroCaso='<%= request.getParameter("numeroCaso")%>';
		numeroUnicoExpediente ='<%= request.getParameter("numeroUnicoExpediente")%>';
		sistemaTradicional='<%= request.getParameter("sistemaTradicional")%>';		
		ocultaCaso(sistemaTradicional);
		//llenamos el catalogo de los tipos de ayuda
		cargaCatalogoTipoAyuda();
		$("#btnEnviarSolictud").click(abrirPantallaEnvioSolicitud);
		
		//cargamos todas las relaciones delito-persona del expediente
		cargaGridConsultaDelitosTodos();
	});
	//Termina funcion on ready del documento
	
	/*
	*Funcion que oculta el renglon de caso, usado solo para sistema
	*tradicional , usuario: agenteSistTrad, ya que los expedientes son 
	*generados sin número de caso
	*/
	function ocultaCaso(sistemaTradicional){

		if(sistemaTradicional == "1"){
			$("#numCasoTd").hide();
			$("#txtNoCaso").hide();

		}else{
			//seteamos el valor del numero de caso
			$("#txtNoCaso").val(numeroCaso);
		}
	}
	
	/*
	*Procedimiento para carga los tipos de ayuda
	*/
	function cargaCatalogoTipoAyuda()
	{
		$('#cbxTipoDeAyuda').empty();
		$('#cbxTipoDeAyuda').append('<option value="-1" selected="selected">-Seleccione-</option>');
		
		//lanzamos la consulta del tipo de solicitudes
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultaTiposSolsXArea.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('ValorDTO').each(function(){
					$('#cbxTipoDeAyuda').append('<option value="' + $(this).find('idCampo').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	/*
	*Funcion para abrir la ventan del envio de la solicitud
	*/
	function abrirPantallaEnvioSolicitud()
	{
		if(idRelacionDelito=="" || idRelacionDelito.length==0)
		{
			alert("Seleccione una relación en la tabla.");
		}
		else if($("#cbxTipoDeAyuda option:selected").val()==-1)
		{
			alert("Seleccione un tipo de ayuda.");
		}
		else
		{
			//pasaron las validaciones y procederemos a abrir el editor de formas
			idTipoSolicitud=$("#cbxTipoDeAyuda option:selected").val();
			elaboraNotificacionAuditoria();
		}
	}
	
    function elaboraNotificacionAuditoria()
    {     
    	//alert("elaboraNotificacionAuditoria - function");
    	//alert(idnumexp);
    	var numeroEX="";
    	numeroEX=idExpedienteop;
    	//alert(numeroEX);
          var actividad=0;
          var formaID=4;
          var titulo="op";
          var usaeditor="";
          var estatusId="";
          var idConf=$("#cbxTipoDeAyuda option:selected").val();
          //consultamos la forma para la notificacion de auditoria
          $.ajax({
                type: 'POST',
                url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+idConf+'',
                data: '',
                dataType: 'xml',
                async: false,
                success: function(xml){
                      actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
                      formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
                      formaID=31;
                      titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
                      usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
                      estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
                }
          });
          $.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Notificación de Ayuda", type:"iframe"});
    	  $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarNotificacionAyudaPsicologicaUAVD.do?formaId='+formaID+'&numeroExpedienteId='+idExpedienteop+'&numeroUnicoExpediente='+numeroUnicoExpediente+'&idTipoSolicitud='+idTipoSolicitud+'&idRelacionDelito='+idRelacionDelito+'"    width="1140" height="550" />');
    }
</script>
</head>
<body>
<table width="80%">
	<tr>
		<td id="numCasoTd">&nbsp;&nbsp;&nbsp;&nbsp;N&uacute;mero de caso: </td>
		<td align="left"><input type="text" id="txtNoCaso" disabled="disabled" style="width: 200px;"/></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><input type="button" id="btnEnviarSolictud" value="Enviar Solicitud" class="btn_mediano"></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;Tipo de ayuda : </td>
		<td align="left">
			<select id="cbxTipoDeAyuda">
				<option value="-1" selected="selected">-Seleccione-</option>
			</select>
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4">
			<jsp:include page="relacionaDelitoPorTodosView.jsp"></jsp:include>
		</td>
	</tr>
</table>
<table width="80%">
	<tr>
		<td>
			
		</td>
	</tr>
</table>
</body>
</html>