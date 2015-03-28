<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Detención</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">
       	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	    
				
<script type="text/javascript">
var idDetenido=null;
var verAlias=1;
var expedienteId;
var idDetenido;
var tipoResolutivo;
var formaId;

	$(document).ready(function() {
		$("#tabsPrincipal").tabs();
		$("#tabschild").tabs();			
		$(".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" ).removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
		$("#tipoDetenido").click(cambioSelect);
		$("#cmbTipoBusqueda").val(5);
		$("#cmbTipoBusqueda").multiselect('refresh');
		killDomicilioNotificaciones();
		$("#registroDetenido").hide();
		$("#liDom").hide();
		$("#motivoDetencion").click(habilitarRegistro);
		$("#tabIngresarPertenencias").hide();
		$("#elaborarHojaIngreso").hide();
		$("#registroDetenido").click(registarDetenido);
		$("#elaborarHojaIngreso").click(generaDoc);
		$("#fechaDetencion,#fechaRecepcion").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		$('#horaDetencion,#horaRecepcion').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
				
	});	
		

	 /*
	  * Funcion para llamar la funcion de habilitar los elementos de la pantalla
	  */
	  function cambioSelect() {
	    	var selected = $("#tipoDetenido option:selected");		
	  	habilitaControles(selected.val());
	  }

	  /*
	  *Funcion que habilita o deshabilita los elementos de la pagina dependiento del tipo 
	  * de detencion
	  */
	  function habilitaControles(tipo){

		  $("#divDetenido").hide();
		  $("#divAprehendido").hide();
	  		
	  		if (parseInt(tipo) == 1){
	  			limpiaCampos();
	  			$("#divDetenido").show();
	  			
	  			}
	  		else{if (parseInt(tipo) == 2){
	  					limpiaCampos();
	  					$("#divAprehendido").show();
	  					enableControls(5);	  		  			
	  					}
	  				}
	  			}

	  function habilitarRegistro(){

		  $("#registroDetenido").show();

		  }

	  function  registarDetenido(){		

	        //Datos Generales Recepcion
		var parametros  = 'recibeDetenido=' + $('#recibeDetenido').val();
		    parametros += '&trasladaDetenido=' + $('#trasladaDetenido').val();
		    parametros += '&fechaDetencion=' + $('#fechaDetencion').val();
		    parametros += '&horaDetencion=' + $('#horaDetencion').val();
		    parametros += '&fechaRecepcion=' + $('#fechaRecepcion').val();
		    parametros += '&horaRecepcion=' + $('#horaRecepcion').val();
		    parametros += '&motivoDetencion=' + $('#motivoDetencion').val();
		    parametros += '&observaciones=' + $('#observaciones').val();			 

			//Datos Domicilio
		    parametros += obtenerParametrosDomicilio();

		    if(idDetenido==null || idDetenido==""){
        	//Datos Generales
			parametros += obtenerParametrosDatosGenerales();
		    }
		    
		    {	
		   	parametros += '&idDetenido=' + idDetenido;
		    }
		    			    		  
	        //Datos Nacimiento
	        parametros += obtenerParametrosDatosNacimiento();

	        $.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/registrarDetenido.do',
    	    data: parametros,
    	    dataType: 'xml',
    	    success: function(xml){	
    	    	expedienteId = $(xml).find('audienciaDTO').find('expediente').find('casoDTO').find('numeroGeneralCaso').first().text();
				idDetenido = $(xml).find('numeroExpediente').first().text();
    		}		
		});	
		 	
		  $("#registroDetenido").attr('disabled','disabled');
		  $("#tabIngresarPertenencias").show();
		  $("#elaborarHojaIngreso").show();

		  }

	  function generaDoc() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Resolutivo", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/enviaResolutivo.do?formaId=15&esconderArbol=1' width='1140' height='400' />");	   		
	  }
 	  
		
	</script>
		<!--ESTILOS PARA LAS TABS-->
	<style>
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 100%; overflow: auto; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
	</style>
</head>
<body>
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Recibir Detenido</a></li>
					
				</ul>
				<div id="tabsconsultaprincipal-1">					
					<div id="tabschild" class="tabs-bottom">
						<ul>
							<li><a href="#tabschild-1">Detenido o Aprehendido</a></li>
							<li><a href="#tabschild-2">Ingresar datos generales de la detención</a></li>
							<li><a href="#tabschild-3">Ingresar lugar de la detención</a></li>
							<li id="tabIngresarPertenencias" ><a href="#tabschild-5">Ingresar Pertenencias</a></li>
							
						</ul>
						
						<div id="tabschild-1" >
						<table width="100%" border="0" height=150px >
						<tr>
							<td >
								
							</td>							
						</tr>	
                        <tr>
							<td align="center" valign="top"><select id="tipoDetenido"><option value="0">Seleccione</option><option value="1">Detenido</option><option value="2">Aprehendido</option></select>
								<div id="divAprehendido" style="display: none">
								<table width="900px" align="center" height="550px"  ><tr><td align="center" valign="top"><jsp:include page="buscarCaso.jsp"></jsp:include></td></tr></table>
							</div>
							</td>							
						</tr>	
                        
                        <tr>
							<td >
							
							<div id="divDetenido" style="display: none">
							<table width="900px" align="center" height="500px"><tr><td><jsp:include page="datosGeneralesView.jsp"></jsp:include></td></tr></table>
									
							</div>
							
								
							</td>							
						</tr>						
					</table>
						</div>
						
						<div id="tabschild-2">

								<table align="center" width="100%" cellspacing="0"
									cellpadding="0" height="300px">
									<tr>
										<td width="60">&nbsp;</td>
										<td colspan="4">&nbsp;</td>
										<td width="71">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td width="263" align="right"><strong>Nombre de
												quien recibe al detenido:</strong>
										</td>
										<td width="217"><select id="recibeDetenido">
												<option value="1">Alfonso</option> 
												<option value="2">Jaime</option>
										</select>
										</td>
										<td width="140" align="right"><strong>Fecha de
												Detención:</strong>
										</td>
										<td width="153"><strong> <input type="text"
												id="fechaDetencion" /> </strong>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="4">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="right"><strong>Nombre de quien
												traslada al detenido:</strong>
										</td>
										<td><select id="trasladaDetenido">
												<option value="2">Alfonso</option> 

												<option value="1">Jaime</option>
										</select>
										</td>
										<td align="right"><strong>Hora de Detención:</strong>
										</td>
										<td><input type="text" id="horaDetencion" name="horaDetencion"/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="4">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="right"><strong>Motivo de la
												detención:</strong>
										</td>
										<td align="left"><input type="text" id="motivoDetencion"
											size="30" />
										</td>
										<td align="right"><strong>Fecha de Recepción: </strong>
										</td>
										<td align="left"><strong> <input type="text"
												id="fechaRecepcion" /> </strong>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="4">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="right"><strong>Observaciones</strong>:</td>
										<td width="217" rowspan="2"><textarea id="observaciones"></textarea>
										</td>
										<td align="right"><strong>Hora de Recepción:</strong>
										</td>
										<td><input type="text" id="horaRecepcion" name="horaRecepcion" />
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td width="263" align="right">&nbsp;</td>
										<td width="140" align="right">&nbsp;</td>
										<td width="153">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="4">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							
							</div>
						
						<div id="tabschild-3">

								<table width="900px" align="center" height="500px">
									<tr>
										<td align="center" valign="top"><jsp:include
												page="ingresarLugarView.jsp"></jsp:include></td>
									</tr>
								</table>

							</div>
												
						<div id="tabschild-5">
						
						<table width="900px" align="center" height="500px">
									<tr>
										<td align="center" valign="top"><jsp:include
												page="ingresarInventarioPertenencias.jsp"></jsp:include></td>
									</tr>
								</table>			
						
						
						</div>
																		
					</div>
					
						<table width="100%" align="center">
							<tr>
								<td width="50%" >&nbsp;</td>
								<td width="50%" >&nbsp;</td>
							</tr>
							<tr>
								<td align="right">
									<input type="button" value="Registrar" id="registroDetenido" class="btn_Generico"/>
								</td>
								<td>
									<input type="button" value="Elaborar hoja de ingreso" id="elaborarHojaIngreso" class="btn_Generico"/>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td >&nbsp;</td>
							</tr>
						</table>
				</div>
				
				
			</div>
		</td>
	</tr>
</table>
</body>
</html>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	