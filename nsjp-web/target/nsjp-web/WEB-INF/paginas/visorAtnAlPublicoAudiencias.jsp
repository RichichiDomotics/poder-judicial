<%@page import="mx.gob.segob.nsjp.comun.constants.ConstantesGenerales"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atención de Audiencias</title>	
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	
	
	var idEvento = 0;
	var idEstatusAudiencia = 0;
	var valorEstatusAudiencia = "";
	
	$(document).ready(function() {

		idEvento=<%= request.getAttribute("idEvento")%>;
		
		$("#numeroAudienciaConsulta").val(idEvento);
		
		consultaDetalleEvento(idEvento);		
		//alert("El evento recibido es: " + idEvento)
		jQuery("#gridDetalleAtencionAudiencias").jqGrid({ 
			url: '<%= request.getContextPath()%>/detalleAudienciasDelDia.do?tipoDeRespuesta=2&idEvento='+idEvento+'',
			datatype: "xml", 
			colNames:['Involucrado','Calidad','Delito','Detenido'], 
			colModel:[ 	{name:'involucrado',index:'involucrado', width:200, align:'left'}, 
			        	{name:'calidad',index:'calidad', width:200, align:'left'}, 
						{name:'Delito',index:'delito', width:100, align:'center',hidden:true },
						{name:'Detenido',index:'detenido', width:100, align:'center'}
					],
			pager: jQuery('#pagerDos2'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerDos2',{edit:false,add:false,del:false});

		/*
		*Funcion que carga el grid de las pruebas asociadas
		*/		
		jQuery("#gridDetalleAtencionPruebas").jqGrid({ 
			url: '<%= request.getContextPath()%>/detalleAudienciasDelDia.do?tipoDeRespuesta=0&idEvento='+idEvento+'',
			datatype: "xml", 
			colNames:['Prueba','Recibida','Fecha de Recepción'], 
			colModel:[ 	{name:'prueba',index:'prueba', width:100,align:"center"}, 
						{name:'recibida',index:'recibida', width:100, align:"center"},
						{name:'fechaRecepcion',index:'fechaRecepcion', width:100, align:"center"}
					],
			pager: jQuery('#pagerUno'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerUno',{edit:false,add:false,del:false});
		
		
		$( "#tabsDetalleAudiencias" ).tabs();
		$('#generarAcuse').click(generarAcuseDeRecibo);

		//Llena el campo con los jueces
		consultarJuecesDeAudiencia(idEvento);
	});

	/*
	*Funcion que limpia las cajas de texto
	*antes de llenarlas con los datos de la
	*nueva consulta
	*/	
	function limpiaDatosDetalleEvento(){
		
		$("#tipoAudienciaPJATP").val("");
		$("#numCasoPJATP").val("");
		$("#numExpPJATP").val("");
		$("#audienciaPJATP").val("");
		$("#caracterPJATP").val("");
		$("#fechaAudienciaPJATP").val("");
		$("#horaAudienciaPJATP").val("");
		$("#salaPJATP").val("");
		$("#direccionSalaPJATP").val("");
		$("#ubicacionPJATP").val("");
		$("#juezPJATP").val("");
		$("#tipoAudienciaPruebasPJATP").val("");
		$("#numCasoPruebasPJATP").val("");
		$("#numExpPruebasPJATP").val("");
	}


	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultaDetalleEvento(idEvento){

		//alert("consultaDetalleEvento");
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/detalleAudienciasDelDia.do?tipoDeRespuesta=1',
			data: 'idEvento='+ idEvento, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
    				limpiaDatosDetalleEvento();
    				idAudiencia = $(xml).find('audienciaDTO').find('id').first().text();
    				$("#tipoAudienciaPJATP").val($(xml).find('tipoAudiencia').find('valor').first().text());
    				$("#numCasoPJATP").val($(xml).find('numeroGeneralCaso').first().text());
    				$("#numExpPJATP").val($(xml).find('numeroExpediente').first().text());
    				$("#audienciaPJATP").val($(xml).find('id').first().text());
    				$("#caracterPJATP").val($(xml).find('caracter').first().text());


					//Fecha
    				var fechaAud = $(xml).find('fechaEvento').first().text();
    				var fechaAudPos1=fechaAud.indexOf(":",0);
    				$("#fechaAudienciaPJATP").val(fechaAud.substring(0,fechaAudPos1-2));

    				//Hora
    				var horaAud = $(xml).find('fechaEvento').first().text();
    				var horaAudPos1=horaAud.indexOf(":",0);
    				$("#horaAudienciaPJATP").val(horaAud.substring(horaAudPos1-2,horaAudPos1+3));
    				$("#salaPJATP").val($(xml).find('sala').find('nombreSala').first().text());
    				$("#direccionSalaPJATP").val($(xml).find('sala').find('domicilioSala').first().text());
    				$("#ubicacionPJATP").val($(xml).find('sala').find('ubicacionSala').first().text());
    				$("#juezPJATP").val($(xml).find('juez').first().text());
    				$("#tipoAudienciaPruebasPJATP").val($(xml).find('tipoAudiencia').find('valor').first().text());
    				$("#numCasoPruebasPJATP").val($(xml).find('numeroGeneralCaso').first().text());
    				$("#numExpPruebasPJATP").val($(xml).find('numeroExpediente').first().text());
    				
    				
    				idEstatusAudiencia = $(xml).find('estatusAudiencia').find('idCampo').first().text();
    				valorEstatusAudiencia = $(xml).find('estatusAudiencia').find('valor').first().text();
    				$("#estatus").val(valorEstatusAudiencia);
    			}
				else{
					//Mostrar mensaje de error
				}
			}
		});
	}

	function generarAcuseDeRecibo() {
		$.newWindow({id:"iframewindowGenerarAcuseRecibo", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Acuse de Recibo", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarAcuseRecibo",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId=<%=Formas.ACUSE_RECIBO.getValorId()%>&esconderArbol=1" width="1140" height="400" />');
	   		
	}

	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultarJuecesDeAudiencia(idEvento){
		
		$('#juezPJATP').empty();
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarJuecesDeAudiencia.do',
			data: 'audienciaId='+ idEvento, 
			//data: 'audienciaId='+3,
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$(xml).find('listaJueces').find('juez').each(function(){
						//Se pone first en el campo de nombre para formar correctamente el nombre del juez
						$('#juezPJATP').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').first().text()+" "+$(this).find('apellidoPaterno').first().text()+" "+$(this).find('apellidoMaterno').first().text() + '</option>');
					});
				}
			}
		});
	}
	
	
	function cancelarAudiencia(){
		var idAudiencia = idEvento;
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/cancelarAudiencia.do',
			data: 'idAudiencia='+ idAudiencia, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				
				var mensaje = $(xml).find('body').find('respuesta').text();
				if(mensaje == "fail_audiencia_cancelada"){
					customAlert("La audiencia ya fue cancelada anteriormente, o por algún otro usuario");
				}
				else{
					if(mensaje == "fail_estatus_audiencia"){
						customAlert("La audiencia no se puede cancelar dado que esta en proceso o fue finalizada");
						
					}else{
						var eliminacion = $(xml).find('body').find('long').text();
						
						if(eliminacion=="<%=ConstantesGenerales.NO_ES_JAVS%>"){
							customAlert("La cancelaci\u00F3n de la audiencia se realiz\u00F3 de manera correcta");
						}
						else{
							var tomaDecision=mensajeEstadoJAVS(eliminacion);
							if(tomaDecision==""){
								customAlert("La audiencia esta programada en Sala JAVS, fallo general al conectar con el servidor JAVS</br>Vuelva a intentarlo más tarde"); 	
							}
							else{
								customAlert(tomaDecision);
							}
						}
						consultaDetalleEvento(idAudiencia);
						recargarGridDeAudienciasProgramadas();
					}					
				}							
			}
		});		
	}	

	function mensajeEstadoJAVS(idEvento){
		var S_Mensaje="";
		switch (idEvento){
            case "<%=ConstantesGenerales.FALLO_GENERAL%>":
                S_Mensaje = "La audiencia esta programada en Sala JAVS, fallo al conectar con el servidor JAVS.</br>Vuelva a intentarlo más tarde";
                break;
            case "<%=ConstantesGenerales.FALLO_GENERAL_JAVS%>":
                S_Mensaje = "La audiencia esta programada en Sala JAVS, fallo al conectar con el servidor JAVS.</br>Vuelva a intentarlo más tarde";
                break;
            case "<%=ConstantesGenerales.ERROR_CREDENCIALES_CONSULTA%>":
            	S_Mensaje = "La audiencia esta programada en Sala JAVS, fallo al conectar con el servidor JAVS.</br>Error en credenciales, vuelva a intentarlo más tarde";
                break;
            case "<%=ConstantesGenerales.AUDIENCIA_PROCESO%>":
            	S_Mensaje = "La audiencia no fue cancelada, ya que esta programada en Sala JAVS y ya se esta llevando a cabo.</br>Se han actualizado los datos de la audiencia";
                break;
            case "<%=ConstantesGenerales.AUDIENCIA_TERMINO%>":
            	S_Mensaje = "La audiencia no fue cancelada, ya que esta programada en Sala JAVS y ya se llevo a cabo.</br>Se han actualizado los datos de la audiencia";
                break;
            case "<%=ConstantesGenerales.ERROR_ELIMINACION%>":
                S_Mensaje = "La audiencia no fue cancelada. Ya que esta programada en Sala JAVS y no se logró eliminar en el servidor JAVS";
                break;
            case "<%=ConstantesGenerales.EXITO_ELIMINACION%>":
                S_Mensaje = "La audiencia esta agendada en Sala JAVS. Se canceló correctamente en el Sistema y en el servidor JAVS";
                break;
            case "<%=ConstantesGenerales.NO_HAY_AUDIENCIAS%>":
                S_Mensaje = "La audiencia no esta agendada en Sala JAVS. Se canceló correctamente en el Sistema";
                break;        
            case "<%=ConstantesGenerales.ERROR_SERVICIO_ELIMINACION%>":
                S_Mensaje = "La audiencia esta programada en Sala JAVS, fallo al conectar con el servidor JAVS.</br>Vuelva a intentarlo más tarde";
                break;              
        }		
        return S_Mensaje;
	}
	
	function reprogramarAudiencia(){
		if(idEstatusAudiencia == <%=EstatusAudiencia.SOLICITADA.getValorId()%> || idEstatusAudiencia == <%=EstatusAudiencia.PROGRAMADA.getValorId()%>
		|| idEstatusAudiencia == <%=EstatusAudiencia.REPROGRAMADA.getValorId()%>){
			//Abre la ventana para poder reprogramar una audiencia
			$.newWindow({id:"iframewindowReprogramarAudiencia", statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Reprogramación de audiencia", type:"iframe"});
			$.maximizeWindow("iframewindowReprogramarAudiencia");
		    $.updateWindowContent("iframewindowReprogramarAudiencia",
		    		'<iframe src="<%= request.getContextPath() %>/ReprogramarAudiencia.do?idEvento='+idEvento+'" width="100%" height="100%" />');
		}else
			customAlert("No es posible reprogramar una audiencia con estatus: " + valorEstatusAudiencia);
	}
	
	
	function recargarGridDeAudienciasProgramadas(){
		parent.recargarGridAudienciaPJENA();
	}
	
	
	
	

	</script>
</head>
<body>

<div id="tabsDetalleAudiencias">
		<ul>
			<li><a href="#tabsDetalleAudiencias-1">Datos de Audiencia</a></li>
			<li id="pestañaPruebas"><a href="#tabsDetalleAudiencias-2" style="display: none;">Pruebas</a></li>
			<li><a href="#tabsDetalleAudiencias-3">Intervinientes</a></li>
		</ul>
		
	<div id="tabsDetalleAudiencias-1">
		<table width="900" border="0" align="center" cellpadding="0" cellspacing="5">
	        <tr>
	            <td colspan="2" align="center"><strong>Tipo de Audiencia:
	              <input type="text" id="tipoAudienciaPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/>
	            </strong></td>
				<td colspan="2" align="center"><strong>Número de Audiencia:
	              <input type="text" id="numeroAudienciaConsulta" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/>
	            </strong></td>	            
	        </tr>
	        <tr>
	          <td colspan="4" align="center">&nbsp;</td>
	        </tr>
	        <tr>
	            <td width="21%" align="right"><strong>Número de Caso:</strong></td>
	            <td width="37%"><input type="text" id="numCasoPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
	            <td colspan="2" rowspan="6">
	                <!--					<table id="gridDetalleAtencionAudiencias"></table>-->
	                <!--					<div id="pagerDos2" width="300"></div>-->
	            </td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Número de Causa:</strong></td>
	            <td><input type="text" id="numExpPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Carácter:</strong></td>
	            <td><input type="text" id="caracterPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
	        </tr>
	        <tr>
	          <td align="right"><strong>Fecha:</strong></td>
	          <td><input type="text" id="fechaAudienciaPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Hora:</strong></td>
	            <td><input type="text" id="horaAudienciaPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Sala:</strong></td>
	            <td><input type="text" id="salaPJATP" style="width:320px; border: 0; background:#DDD;" readonly="readonly"/></td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Domicilio de Sala:</strong></td>
	            <td><input type="text" id="direccionSalaPJATP" style="width:320px; border: 0; background:#DDD;" readonly="readonly"/></td>
	            <td width="16%">&nbsp;</td>
	            <td width="26%">&nbsp;</td>
	        </tr>
	        <tr>
	            <td align="right"><strong>Ubicación:</strong></td>
	            <td><input type="text" id="ubicacionPJATP" style="width:320px; border: 0; background:#DDD;" readonly="readonly"/></td>
	            <td align="right"></td>
	            <td></td>
	        </tr>
			<tr>
			  <td align="right"><strong>Estatus Audiencia:</strong></td>
			  <td><input type="text" id="estatus" style="width:320px; border: 0; background:#DDD;" readonly="readonly"/></td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
		  </tr>
			<tr>
			  <td align="right">&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
		  </tr>
			<tr>
	            <td align="right">&nbsp;</td>
	            <td><input type="button" name="button" id="button" value="Cancelar audiencia" onclick="cancelarAudiencia()" class="btn_mediano"></td>
	            <td><input type="button" name="button" id="button" value="Reprogramar audiencia" onclick="reprogramarAudiencia()" class="btn_mediano"></td>
	            <td>&nbsp;</td>
	  		</tr>
		</table> 
	</div>
		
	<div id="tabsDetalleAudiencias-2">
		
		<table width="900" border="0" align="center" cellpadding="0" cellspacing="5">
			<tr>
				<td colspan="5" align="center"><strong>Tipo de Audiencia:
				  <input type="text" id="tipoAudienciaPruebasPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/>
				</strong></td>
			</tr>
			<tr>
			  <td colspan="5" align="center">&nbsp;</td>
	  		</tr>
			<tr>
				<td width="18%" align="right"><strong>Número de Caso:</strong></td>
				<td colspan="2"><input type="text" id="numCasoPruebasPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
				<td width="24%"><strong>Número de Expediente:</strong></td>
				<td width="29%"><input type="text" id="numExpPruebasPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="5" align="right">&nbsp;</td>
			</tr>
			<tr>
			  <td colspan="5" align="right"><table id="gridDetalleAtencionPruebas"></table>
							<div id="pagerUno" width="300"></div></td>
	  		</tr>
			<tr>
			  <td colspan="5" align="right">&nbsp;</td>
	  		</tr>
			<tr>
			  <td colspan="5" align="center"><input type="button" value="Generar Acuse de Recibo" id="generarAcuse" class="btn_Generico"/></td>
		  	</tr>
			<tr>
			  <td colspan="5" align="right">&nbsp;</td>
	  		</tr>
		</table>
		
	</div>
		
	<!--Comienza tab involucrado	-->
	<div id="tabsDetalleAudiencias-3">
		<table width="900" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="50">&nbsp;</td>
		    <td width="800">&nbsp;</td>
		    <td width="50">&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td rowspan="6">
		    	<table id="gridDetalleAtencionAudiencias"></table>
				<div id="pagerDos2"></div>
		    </td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center"><strong>Juez(ces):</strong>
				<!--<input type="text" id="juezPJATP" style="width:200px; border: 0; background:#DDD;" readonly="readonly"/>-->
		    	<select id="juezPJATP" style="width: 200px; border: 0; background:#DDD;"></select>
		    </td>
		    <td></td>
		  </tr>
		</table>	
		
	</div>
	
	<!--Fin tab de Involucrados-->
</div>
</body>
</html>