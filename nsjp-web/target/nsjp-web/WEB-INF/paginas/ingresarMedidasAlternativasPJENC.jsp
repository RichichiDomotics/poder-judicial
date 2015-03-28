<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Medidas Alternativas</title>


	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
					
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
			
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/js/comun.js"></script>	

		<!--Hoja de estilo para los popups-->
      	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
		<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

		<script type="text/javascript">
		var numexpedienteid=parent.numeroExpedienteId;
		
		var idInvolucrado = parent.Involucradoid;
		var documentoIdPACP=parent.documentoIdPACP;
		
		rowid = documentoIdPACP;
		var archivoDigitalId;
		var idDocumentoGlobal;
		var numeroCarpetaE=parent.numeroCarpetaE;
		var medidaAlternaId;
		//var numeroCarpetaGrid=parent.numeroCarpetaGrid;
		//var numexpedienteid = "<%=request.getParameter("numeroExpedienteId")%>";
		jQuery().ready(function () {
			if(documentoIdPACP==undefined){
				$("#botguardarMedida").css("display","block");
				$("#guardarMedida").css("display","none");
			}else{$("#botguardarMedida").css("display","none");
			$("#guardarMedida").css("display","block");}

			
			//rowid = '<%= request.getParameter("rowid")%>';
			$( "#tabsprincipalconsulta" ).tabs();
			$("#medidaCautelarCmpFechaInicio").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#medidaCautelarCmpFechaFin").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			cargarMedidasAlternativas();
			cargarEncargadoSeguimiento();
			
			obtenerCatalogoPeriodicidad();
			
			
			//Codigo para obtener los datos de la pantalla
			
	
			$("#consultaMedida").click(consultaDocumento);

			$('#iMedidaCautelarWorkSheet').show();
			$('#iMedidaCautelarConsulta').hide();
			
			obtenerDatosMedidaCautelar(rowid);
			cargaGridDocumentosRelacionados();
		});

		/*
		* Función que carga el catálogo de periodicidad en el combo-box correspondiente cbxPeriodicidad
		*
		*/
		function obtenerCatalogoPeriodicidad(){
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarCatalogoPeriodicidad.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('catPeriodicidad').each(function(){
						$('#cbxPeriodicidad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}
		/*
		*Funcion que realiza la carga del combo de Medidas Alternativas
		*/
		function cargarMedidasAlternativas() {
		  
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoMedidasAlternativa.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('medidasAlternativas').each(function(){
						$('#cbxMedidaCautelar').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}
		/*
		*Funcion que realiza la carga del combo de Encargados de seguimiento
		*/
		function cargarEncargadoSeguimiento() {
		  
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoEncargadosSeguimiento.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('encargadoSeguimiento').each(function(){
						$('#cbxEncargado').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}

		function obtenerDatosMedidaCautelar(rowid){
	    	
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarMedidasCautelaresInvolucradoPJENC.do',
	    		data: 'rowid='+rowid,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){

	    			//idInvolucrado=$(xml).find('medidaCautelar').find('involucrado').find('elementoId').text();

					var nombre=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('nombre').first().text();
					document.getElementById('iMCNombre').value=nombre;
					var apellidoPaterno=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
					document.getElementById('iMCApellidoPaterno').value=apellidoPaterno;
					var apellidoMaterno=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('apellidoMaterno').first().text();
					document.getElementById('iMCApellidoMaterno').value=apellidoMaterno;

					var medidaCautelar = $(xml).find('medidaCautelar').find('valorTipoMedida').find('idCampo').text();
					tipoPeriodicidad = $(xml).find('medidaCautelar').find('valorPeriodicidad').find('idCampo').text();
					
					$('#cbxPeriodicidad').find("option[value='"+tipoPeriodicidad+"']").attr("selected","selected");
					if(medidaCautelar != ''  && medidaCautelar != null){
						$('#cbxMedidaCautelar').find("option[value='"+medidaCautelar+"']").attr("selected","selected");
						
						var guardaDef = $(xml).find('medidaCautelar').find('guardadoDefinitivo').text();
						deshabilitaCampos();
						if(guardaDef == 'true'){
							$('#iMedidaCautelarWorkSheet').hide();
							$('#iMedidaCautelarConsulta').show();
						}
					}
					
					var seguimiento=$(xml).find('medidaCautelar').find('seguimiento').text();
					if(seguimiento != '' && seguimiento != null){
						//$('#cbxEncargado').find("option[value='"+seguimiento+"']").attr("selected","selected");
						$('#cmpSeguimiento').val(seguimiento);
					}

					var fechaInicio=$(xml).find('medidaCautelar').find('strFechaInicio').text();
					if(fechaInicio != '' && fechaInicio != null){
						//var fechaTmp =fechaInicio.indexOf(":",0);
						//$("#medidaCautelarCmpFechaInicio").val(fechaInicio.substring(0,fechaTmp-3));
						$("#medidaCautelarCmpFechaInicio").val(fechaInicio);
					}	

					var fechaFin=$(xml).find('medidaCautelar').find('strFechaFin').text();
					if(fechaFin != '' && fechaFin != null){
						//var fechaTmp = fechaFin.indexOf(":",0); 
						//$("#medidaCautelarCmpFechaFin").val(fechaFin.substring(0,fechaTmp-3));
						$("#medidaCautelarCmpFechaFin").val(fechaFin);
					}
	    		}	
	    	});
		}
		medidaCautelarId  = "";
		
		function guardarMedidaCautelar(){
			var params = '';
			params += recuperoDatosMedidaCautelar();
			params +="&numeroUnicoExpediente="+ numexpedienteid;
			params +="&numeroCarpetaE="+numeroCarpetaE;
			var formaId = '<%=Formas.MEDIDA_ALTERNA.getValorId()%>'; 
			//var numeroUnicoExpediente =numeroUnicoExpediente= numexpedienteid;			
			$.ajax({								
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/registrarMedidaAlternativa.do?idInvolucrado='+idInvolucrado+'',
		    	  data: params,				
		    	  dataType: 'xml',
		    	  success: function(xml){
		    		  //si no tenía medida cautelar
		    		 //idAlterna= $(xml).find('Long').text();
		    		 
		    		 // if(rowid.split(",")[1] == ""){
		    			 
		    			  medidaAlternaId=$(xml).find('medidaCautelarForm').find('medidaCautelarId').text();
		    			  
							//$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitud de Medida Alternativas", type:"iframe"});
					        //$.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+medidaCautelarId+"&formaId="+formaId+"&esconderArbol=1&numeroUnicoExpediente="+numexpedienteid+"' width='1140' height='400' />");

					        if(medidaAlternaId!=""){customAlert("Medida Cautelar Guardada");
				    		 $("#botguardarMedida").css("display","none");
				 			$("#guardarMedida").css("display","block");
				    		 }
		    		  	//}
		    	 }
		    	});
			
		}

		function recuperoDatosMedidaCautelar(){
	        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
	        var parametros = '&medidaCautelar=' + $('#cbxMedidaCautelar option:selected').val();
	        parametros += '&seguimiento=' + $('#cmpSeguimiento').val();
	        parametros += '&fechaInicio=' + $('#medidaCautelarCmpFechaInicio').val();        
	        parametros += '&fechaFin=' + $('#medidaCautelarCmpFechaFin').val(); 

	        var activo = $(':radio[name=rbtMCDesactivar]:checked').val();
	        parametros += '&activo=' + activo;
	        parametros += '&descripcionMedidaCautelar='+  $('#descripcionMedidaCautelar').val();
	        parametros += '&periodicidad=' + $('#cbxPeriodicidad option:selected').val();
	        parametros += '&rowid='+rowid;
	        parametros += '&numeroExpediente='+numexpedienteid;
			return parametros;
		}

		function habilitaCampos(){
	        $('#cbxMedidaCautelar').attr("disabled","");
	        $('#cmpSeguimiento').attr("disabled","");
	        $('#medidaCautelarCmpFechaInicio').attr("disabled","");        
	        $('#medidaCautelarCmpFechaFin').attr("disabled",""); 
	        $('#cbxPeriodicidad').attr("disabled",""); 
	        $('#descripcionMedidaCautelar').attr("disabled",""); 
		}

		function deshabilitaCampos(){
	        $('#cbxMedidaCautelar').attr("disabled","disabled");
	        $('#cmpSeguimiento').attr("disabled","disabled");
	        $('#medidaCautelarCmpFechaInicio').attr("disabled","disabled");        
	        $('#medidaCautelarCmpFechaFin').attr("disabled","disabled"); 
	        $('#cbxPeriodicidad').attr("disabled","disabled"); 
	        $('#descripcionMedidaCautelar').attr("disabled","disabled"); 
	        
	        
		}

		function consultaDocumento(){
			var idCausa = '<%= request.getSession().getAttribute("idCausa")%>';
			//request.getSession().getAttribute("idCausa")
			document.frmDocumento.documentoId.value = idCausa;
			document.frmDocumento.submit();
		}


		/**
		*Funcion que carga el grid de documentos relacionados
		*/
		function cargaGridDocumentosRelacionados(){
			//
			jQuery("#gridDocumentos").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultaGridDocumentosMedidasCautelares.do?numexpedienteid='+numexpedienteid, 
				datatype: "xml", 
				colNames:['Fecha de Elaboracion','Nombre'], 
				colModel:[ 					
				           	{name:'fechaElab',index:'fechaElab', width:120, align:'center'}, 
				           	{name:'Nombre',index:'Nombre', width:120, align:'center'}, 
						],
				pager: jQuery('#pagerGridDocumentos'),
				rowNum:10,
				autoWidth:false,
				width:400,
				rowList:[10,20,30],
				sortname: 'fechaElab',
				viewrecords: true,
				sortorder: "fechaElab",
				ondblClickRow: function(rowid) {
					archivoDigitalId=rowid;
					abrirPDF();
				} 
			}).navGrid('#pagerGridDocumentos',{edit:false,add:false,del:false});

			$("#gview_pagerGridDocumentos .ui-jqgrid-bdiv").css('height', '150px');
				
		}

		/*
		*Funcion que consulta el detalle del evento y llena 
		*los campos de la TAB Detalle evento
		*/
		function consultaDetalleEvento(){

			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/detalleSolicitudRecurso.do',
				data: 'idDocumento='+ idDocumentoGlobal, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					  
	    				
	    				//$("#documento").val($(xml).find('nombreArchivo').first().text());

	    				archivoDigitalId=$(xml).find('archivoDigitalId').first().text();
	    				//expedienteId=$(xml).find('solicitudDTO').find('expedienteDTO').find('expedienteId').first().text();
	    				
				}
			});
		}
		
		function abrirPDF(){
			document.frmDoc.archivoDigitalId.value = archivoDigitalId;
			document.frmDoc.submit();
		}
	

		function generarMedidaAlterna(){	
			var formaId = '<%=Formas.MEDIDA_ALTERNA.getValorId()%>'; 		
			$.newWindow({id:"iframewindowGenerarDocumento", 
				statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Medida Alterna", 
				type:"iframe"});
	        $.updateWindowContent("iframewindowGenerarDocumento",
	        		"<iframe src='<%=request.getContextPath()%>/generarDocumentoSincrono.do?documentoId="+medidaAlternaId+"&numeroUnicoExpediente="+numeroCarpetaE+"&formaId="+formaId+"&expedienteId="+numexpedienteid+"' width='1140' height='400' />");	      						
		}
		
		function documentoGeneradoSincrono(documentoGeneradoId){		
			
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/enviarMedidaAlterna.do?medidaAlternaId='+documentoGeneradoId,
				data: '', 
				async: false,
				dataType: 'xml',
				success: function(xml){
					  
					document.frmDocumento.documentoId.value = documentoGeneradoId;
					document.frmDocumento.submit();
	    				
					$.closeWindow("iframewindowGenerarDocumento");
							    				
				}
			});
		}

		</script>
	</head>
<body>

<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Medidas Alternativas</a></li>
			<li><a href="#tabsconsultaprincipal-2">Documentos</a></li>
		</ul>
		
		<div id="tabsconsultaprincipal-1">
	<input type="hidden" name="xml" id="xml" />
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iMedidaCautelarViewHeader" width="100%" border="0">
					<tr>
						<td>
							<table>
								<tr valign="top">
									<td width="30%">
									</td>
									
									<td width="40%">
										<table style="border: 0; background:#DDD;" width="100%" height="143" cellpadding="0"  cellspacing="0" class="celda2">
					                        <tr>
					                          <td width="60%" height="30" align="right">Nombre:</td>
					                          <td width="29%"><input type="text" value="" readonly="readonly" title="Escribir nombre" size="50" maxlength="40" id="iMCNombre" style="background:#DDD;border: 0;" readonly="readonly"/></td>
					                        </tr>
					                        <tr>
					                          <td width="60%" height="28" align="right">Apellido Paterno:</td>
					                          <td width="29%" height="28"><input type="text" value="" readonly="readonly" readonly="readonly" size="50" maxlength="40" id="iMCApellidoPaterno" style="background:#DDD;border: 0;" readonly="readonly"/></td>
					                        </tr>
					                        <tr>
					                          <td width="60%"  height="35" align="right">Apellido Materno:</td>
					                        <td height="35"><input type="text" value="" readonly="readonly"  readonly="readonly" size="50" maxlength="40" id="iMCApellidoMaterno" style="background:#DDD;border: 0;" readonly="readonly"/></td>
					                        </tr>
					                    </table>
									</td>
								</tr>
							</table>
						</td>
				</table>
					</td>
					<td>
						<table>
							<tr>
								<td width="10%">Medida Alternativa</td>
								<td width="20%">
						          <select id="cbxMedidaCautelar">
									<option value="-1">-Seleccione-</option>
						          </select>
						        </td>
							</tr>
							<tr>
								<td width="10%">Encargado de Seguimiento</td>
								<td width="20%">
								<input type="text" id="cmpSeguimiento" size="40"/>					
								<!--
						          <select id="cbxEncargado">
									<option value="-1">-Seleccione-</option>
						          </select>
						        -->
						        </td>
						    </tr>
							<tr>
								<td align="right">Fecha de Inicio:</td>
								<td><input type="text" id="medidaCautelarCmpFechaInicio" style="width: 180px;" /></td>
							</tr>
							<tr>
								<td align="right">Fecha Fin:</td>
								<td><input type="text" id="medidaCautelarCmpFechaFin" style="width: 180px;" /></td>
							</tr>
							<tr>
								<td align="right">Periodicidad:</td>
								<td><select id="cbxPeriodicidad">
									<option value="-1">-Seleccione-</option>
						          </select></td>
							</tr>
							<tr>
								<td align="right">Descripci&oacute;n:</td>
								<td><textarea style="width: 200px;" id="descripcionMedidaCautelar"></textarea></td>
							</tr>
							
						</table>
					</td>
				</tr>
				<!--  <tr>	
		          <td id="cmpMCDesactivar" height="25" colspan="2" align="center"><strong>&iquest;Cancelar Medida Alterna?: 
		            </strong>
					<input type="radio" name="rbtMCDesactivar" id="rbtMcDesactivarNo" value="0" checked="checked"/> 
					<label for="rbtMcDesactivarNo"><strong>No</strong></label>
		            <input type="radio" name="rbtMCDesactivar" id="rbtMcDesactivarSi" value="1" />
					<label for="rbtMcDesactivarSi"><strong>Si</strong></label>
		          </td>
				</tr>-->
		<tr valign="top">
			<td>
			  <table id="iMedidaCautelarWorkSheet" width="100%"  border="0">
				<tr valign="top">
					<td align="center">
						<input type="button" value="Continuar Medida Alterna" id="guardarMedida" onclick="generarMedidaAlterna()" class="btn_Generico"/><td align="center">
						<input type="button" value="Guardar" id="botguardarMedida" onclick="guardarMedidaCautelar()" class="btn_Generico"/>
					</td>
					</td>
				</tr>
			  </table>
			</td>
			<td>
			  <table id="iMedidaCautelarConsulta" width="100%"  border="0">
				<tr valign="top">
					<td align="center">
						<input type="button" value="Consultar Documento Digital" id="consultaMedida" class="btn_Generico"/>
					</td>
				</tr>
			  </table>
			</td>
		</tr>
	</table>
<form name="frmDocumento" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="documentoId" />
	
</form>
</div>

<div id="tabsconsultaprincipal-2">
<div >
<table id="gridDocumentos" ></table>
					<div id="pagerGridDocumentos" style="width: 300"></div>
					</div>
</div>

</div>
<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" name="archivoDigitalId" value=""/>
</body>
</html>