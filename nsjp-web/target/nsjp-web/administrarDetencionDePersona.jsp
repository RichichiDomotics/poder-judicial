<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>



      <!--Hoja de estilo para los popups-->
      <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
      

      <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
      <!--Scrip para el idioma del calendario-->
      <script type="text/javascript"  src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
      
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
      
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Detención</title>
		<!--ESTILOS PARA LAS TABS-->
	<style>
		#tabs { height: 670px; } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	<script type="text/javascript">
	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
	if(sesionActiva=="false"){
		//alert(sesionActiva);
		document.location.href="<%= request.getContextPath()%>/Logout.do";
    	}
	</script>
</head>
<body>
<table width="100%">
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="center">
			<strong>Selecciona el tipo de detención:</strong>
			<select id="tipoDetencion">
				<option value="0">-Seleccione-</option>
				<option value="1">Flagrancia</option>
				<option value="2">Falta Administrativa</option>
				<option value="3">Detención de Menor</option>
			</select>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Solicitante</a></li>
					<!--<li><a href="#tabsconsultaprincipal-2">IPH</a></li>-->
					<li id="tabEvidencias" ><a href="#tabsconsultaprincipal-3">Evidencias</a></li>
					<li id="tabAvisarFuncionario"><a href="#tabsconsultaprincipal-4">Avisar a funcionario</a></li>
					<li id="tabBuscarSujeto"><a href="#tabsconsultaprincipal-5">Buscar Sujeto</a></li>
					<li id="tabPertenencias"><a href="#tabsconsultaprincipal-6">Pertenencias</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre Servidor Público:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solDeSSPNombre" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Cargo:
							</td>
							<td>
								<input type="text" size="50" maxlength="50"	id="solDeSSPPuesto"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Área Administrativa:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solDeSSPAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha Elaboración:
							</td>
							<td>
								<input type="text" size="50" maxlength="13"	id="solDeSSPFecha" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</div>
<!--				<div id="tabsconsultaprincipal-2">-->
<!--					<table width="100%" border="0" height="90%">-->
<!--						<tr>-->
<!--							<td width="20%">-->
<!--		        				<td width="31%" align="right" valign="bottom"><input type=button class="btn_buscar" id=btnBuscaFolioControl value="Buscar"></td>-->
<!--						</tr>-->
<!--						<tr>-->
<!--							<td align="right">-->
<!--								Nombre(s):-->
<!--							</td>-->
<!--							<td>-->
<!--								<input type="text" class="" size="50" maxlength="50" id="nombrePRespSSP" />-->
<!--							</td>-->
<!--						</tr>-->
<!--						<tr>-->
<!--							<td align="right">Fecha inicio:-->
<!--							</td>-->
<!--		        			<td > <input type="text" id="txtFechaInicioBuscarFolio" disabled="disabled"/>-->
<!--		        			</td>		        			-->
<!--						</tr>-->
<!--						<tr>-->
<!--							<td align="right">Fecha fin:-->
<!--							</td>-->
<!--		        			<td > <input type="text" id="txtFechaFinBuscarFolio" disabled="disabled"/>-->
<!--		        			</td>		        			-->
<!--						</tr>-->
<!--					</table>-->
<!--					-->
<!--					<table width="650" border="0" cellspacing="0" cellpadding="0">-->
<!--						<tr>-->
<!--					     <td valign="middle" align="center"><table id="tablaBuscarFolioControlPorNombre"></table>-->
<!--						 </td>-->
<!--					   </tr>-->
<!--					</table>			-->
<!--					-->
<!--				</div>-->
				
				<div id="tabsconsultaprincipal-3">
				
				 <input type="button" id="btnCadCusNuevaCadCus" style="width: 250px;" value="Crear nueva cadena de custodia" class="btn_Generico"/><br/><br/>  
  				 <input type="button" id="btnCadCusRegEslabones" style="width: 250px;" value="Registrar eslabones" class="btn_Generico"/> <br/><br/>
  				 <input type="button" id="btnCadCusRepEvidencias" style="width: 250px;" style="width: 250px;" value="Reporte de evidencias" class="btn_Generico"/> <br/><br/>
  				 <input type="button" id="btnCadCusElabOficio" style="width: 250px;" value="Elaborar oficio para fijación y preservación" class="btn_Generico"/><br/><br/>  
   				 <input type="button" id="btnCadCusAdmDestino" style="width: 250px;" value="Administrar destino legal de evidencia" class="btn_Generico"/>
   				 
				</div>
				<div id="tabsconsultaprincipal-4">
					<table width="100%" border="0" height="90%">
						<tr>
							<td width="50%" align="right">
								Coordinador Agente del Ministerio Publico: 
							</td>
							<td width="50%"><input type="text" id="agenteMinisterioPublico" disabled size="30"/>
							</td>
							<td width="50%" align="right">
		        				<td width="31%" align="right" valign="bottom"><input type=button class="btn_registro" id=btnRegistroDetencion value="Registrar"></td>
						</tr>
						</tr>
						<tr>
							<td align="right">
							Coordinador Defensoria Publica: 
						    </td>
							<td>
								<input type="text" id="agenteMinisterioPublico2" disabled size="30"/>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center"><input type="button" id="elaborarOficioLocalizacionPadres" value="Elaborar oficio para localizacion de padres" class="btn_Generico"/>
								
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-5" style="height: 400px">
					<table width="100%" border="0">
						<tr>
							<td><jsp:include page="WEB-INF/paginas/buscarCaso.jsp"></jsp:include></td>
						</tr>
						
					</table>
				</div>
				
				<div id="tabsconsultaprincipal-6">
					<table width="100%" border="0" height="90%">
					    <tr>
					        <td align="right">
					            Categor&iacute;a de la pertenencia:
					        </td>
					        <td>
					            <input type="text" class="" size="50" maxlength="50" id="solDeSSPCategoPertenencia"/>
					        </td>
					    </tr>
					    <tr>
					        <td align="right">
					            Descripci&oacute;n de la pertenencia:
					        </td>
					        <td>
					            <input type="text" class="" size="50" maxlength="50" id="solDeSSPDescPertenencia"/>
					        </td>
					    </tr>
					    <tr>
					        <td align="right">
					            Condici&oacute;n F&iacute;sica de la pertenencia:
					        </td>
					        <td>
					            <input type="text" size="50" maxlength="50"	id="solDeSSPCondicionPertenencia"/>
					        </td>
					    </tr>
					    <tr>
					        <td>&nbsp;
					            
					      </td>
					        <td>
					             Pertenencias Registradas
					        </td>
					    </tr>
					    <tr>
					        <td align="right" valign="top" >
					            <input type="button" id="btnIngresarPertenencias" value="Ingresar Pertenencia" class="btn_Generico"/>
					        </td>
					        <td>
					            <textarea rows="4" cols="30"></textarea>
					        </td>
					    </tr>
					    <tr>
					        <td colspan="2" align="center">
					            <input type="button" id="btnGuardar" value="Guardar" class="btn_Generico"/>
					        </td>
					    </tr>
					</table>
				</div>
			</div>
		</td>
	</tr>
	
	
</table>
<div id="dialog-confirm" title="Confirmación: ">
		<p align="right"><span style="font-size: 25px;"> </span><br/><span style="font-size: 115px;" ></span></p>
	</div>
</body>
</html>
	
<script type="text/javascript">

	//* TODO temporalmente se inicializa con un dato*/
	var numeroExpediente="NSJYUCPG2011013333E";
	var idWindowAsntarRegCadCus=1;

	//funciones para el popup interno del turno
	$( "#dialog:ui-dialog" ).dialog( "destroy" );

	//Variable para almacenar el numero de folio del IPH
	var folioIPH;
	
	$(document).ready(function() {

		//obtenemos el folio del IPH
		folioIPH='<%= request.getParameter("folioIPH")%>';
		$("#tabsPrincipal").tabs();
		$( "#tabschild" ).tabs();		
		$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" ).removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
		$("#tabsPrincipal").hide();	
		$('#btnRegistro').hide();
		$('#elaborarOficioLocalizacionPadres').hide();
		$('#elaborarOficioLocalizacionPadres').click(generarDocumento);
		$('#tipoDetencion').change(cambioSelect);
		
    	
    	$("#tabEvidencias").hide();
    	
		//$("#tabEvidencias").hide();
		//$("#guardarNarraTiva").css("display", "none");
		//$("#btnEnviarSolicitud").click(cerrarVentana);
		cargaFechaCaptura();
		

		$("#btnBuscaFolioControl").bind("click",validaCamposNombre);
		$("#btnBuscaFolioControl").bind("click",muestraGridIPH);
		
		$("#btnRegistroDetencion").bind("click",registraDetencion);
	
		$("#txtFechaInicioBuscarFolio").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		$("#txtFechaFinBuscarFolio").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		//Setea el valor -seleccione al combo cuando se refresca la pagina
		$("#tipoDetencion").val(0);				
	});

	//Consulta el IPH con base a su numero de folio
	function consultarIPHporNumeroDeFolio(){

		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarIPHPorNumeroDeFolio.do?folioIPH='+folioIPH+'',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text();					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
	}
	
	
	
	
	/*Funcion para consultar los datos del funcionario superior*/
    function consultaFuncionarioSuperior(){
    	
    	//$('#solDeSSPAreaAdmin').val('');
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDatosFuncionario.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$('#agenteMinisterioPublico').val("Alin Pina Garibay " );
					$('#agenteMinisterioPublico2').val( "Agalia Feliciano Amaya" );
					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
    }
	
	/*Funcion para consultar los datos del funcionario firmado*/
    function consultaDatosFuncionario(){
    	
    	//$('#solDeSSPAreaAdmin').val('');
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDatosFuncionario.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){	
					/* TODO  temporalmente se harcodea el área y el puesto del funcionario se quitará cuando se resuelva la jerarquía organizacional*/
					//$('#solDeSSPAreaAdmin').val( $(xml).find('usuarioDTO').find('area').find('nombre').first().text()+ " Seguridad Pública" );
					$('#solDeSSPAreaAdmin').val("Seguridad Pública Estatal" );
					$('#solDeSSPNombre').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text());
					//$('#solDeSSPPuesto').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					$('#solDeSSPPuesto').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() + "Comandante de Sector" );
					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
    }
	
	
	function cerrarVentana(){
		parent.cerrarVentanaEvidencia();
	}
	
	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solDeSSPFecha').val($(xml).find('fechaActual').text());
    		}
		});
    }
	
	 /*
	  * Funcion para llamar la funcion de habilitar los elementos de la pantalla
	  */
	  function cambioSelect() {
	    	var selected = $("#tipoDetencion option:selected");		
	    	consultaDatosFuncionario();
	    	consultaFuncionarioSuperior();

	    	$("#tabEvidencias").hide();
	  	habilitaControles(selected.val());
	  }

	  /*
	  *Funcion que habilita o deshabilita los elementos de la pagina dependiento del tipo 
	  * de detencion
	  */
	  function habilitaControles(tipo){

		  $("#tabsPrincipal").hide();
	  		
	  		if (parseInt(tipo) == 1){
	  			limpiar_IPH_RegistroCadenaCust();
	  			$("#tabsPrincipal").show();
	  			$('#tabBuscarSujeto').hide();
	  			$('#tabsconsultaprincipal-5').hide();
	  			$('#tabAvisarFuncionario').show();
		  		$('#tabsconsultaprincipal-4').show();
		  		$('#elaborarOficioLocalizacionPadres').hide();
		  		$('#tabPertenencias').hide();		  			  				  						
	  		}
	  		else{if (parseInt(tipo) == 2){
	  			limpiar_IPH_RegistroCadenaCust();
	  					$("#tabsPrincipal").show();
	  		  			$('#tabAvisarFuncionario').hide();
	  		  			$('#tabsconsultaprincipal-4').hide();
	  		  			$('#tabBuscarSujeto').show();
		  				$('#tabsconsultaprincipal-5').show();
		  				$('#elaborarOficioLocalizacionPadres').hide();
		  				$('#tabPertenencias').show();
	  						  					
	  					}else{if (parseInt(tipo) == 3){
	  						limpiar_IPH_RegistroCadenaCust();
	  						
	  			  			$("#tabsPrincipal").show();
	  			  			$('#tabBuscarSujeto').hide();
	  			  			$('#tabsconsultaprincipal-5').hide();
	  			  			$('#tabAvisarFuncionario').show();
	  				  		$('#tabsconsultaprincipal-4').show();
	  				  		$('#elaborarOficioLocalizacionPadres').show();
	  				  		$('#tabPertenencias').hide();	  					
		  				}
	  				}
	  			}
	  		}

		function limpiar_IPH_RegistroCadenaCust(){
			
			/*IPH */
			$("#nombrePRespSSP").val("");
			$("#txtFechaInicioBuscarFolio").val("");
			$("#txtFechaFinBuscarFolio").val("");
			
		}

		function generarDocumento() {
						
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Resolutivo", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/enviaResolutivo.do?formaId=15&esconderArbol=1' width='1140' height='400' />");

		    $('#btnRegistro').show();
	   		
		}
		
		
		/* funcion de busqueda*/
		
		
	//Llena el grid con los resultados de la busqueda, pasa como parametros el nombre y el apellido
		  function muestraGridIPH(){
			  var nombre = $("#nombrePRespSSP").val();				//nombre
			  var fechaInicial = $("#txtFechaInicioBuscarFolio").val();				//Fecha inicial
			  var fechaFinal = $("#txtFechaFinBuscarFolio").val();				//Fecha final
			  
						if (validaNombre==true){
														
							if (reloadGridNombre) {
								  jQuery("#tablaBuscarFolioControlPorNombre").jqGrid('setGridParam', {postData:{nombre: nombre, fechaInicial:  fechaInicial, fechaFinal: fechaFinal}});
								  $("#tablaBuscarFolioControlPorNombre").trigger("reloadGrid"); 
							  } else {
								  reloadGridNombre = true;
								  jQuery("#tablaBuscarFolioControlPorNombre").jqGrid(
											{  						
												url:'<%=request.getContextPath()%>/consultarIPH.do',
												datatype: "xml", 
												mtype: 'POST',						
												postData: {
													nombre: function()     { return nombre; },
													fechaInicial: function()     { return fechaInicial; },
													fechaFinal: function()     { return fechaFinal; }													
													
												},
												colNames:['Folio de Control de IPH','Tipo de Evento','Delito','Subtipo de Evento','Fecha del Informe', 'Hora del Informe'], 
												colModel:[ {name:'caso',index:'caso', width:200, sortable:false}, 
															{name:'nombre',index:'nombre', width:150, sortable:false}, 
															{name:'apaterno',index:'apaterno', width:150, sortable:false}, 
															{name:'amaterno',index:'amaterno', width:150, sortable:false},
															{name:'calidad',index:'calidad', width:200, sortable:false},
															{name:'hora',index:'hora', width:200, sortable:false} 
															], 
													autowidth: true, 
													pager: jQuery('#pager'), 
													sortname: 'id', 
													rownumbers: true,
													gridview: true, 
													viewrecords: true, 
													sortorder: "desc", 
													height: "60%",
													id: 'pager',
													onSelectRow: function(id){
														confirmacionIPH()
														},
													caption:"Resultado de la B&uacute;squeda" 
											});
							 			 }
							}			  
					   
				  }
		

			function confirmacionIPH(){
					$( "#dialog-confirm" ).dialog({
						resizable: false,
						height:100,
						width:200,
						modal: true,
						buttons: {
							"Confirmar": function() {
								$( this ).dialog( "close" );
								$( "#dialog:ui-dialog" ).dialog( "destroy" );
								//asociarFolioExpediente();
								mostrarTab();
							},
							"Cancelar": function() {
								$( this ).dialog( "close" );
								$( "#dialog:ui-dialog" ).dialog( "destroy" );
							}
						}
					});
					$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();

				}
			

		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposNombre(){

			//if ($('#nombrePRespSSP').val()==''&& $('#apPPRespSSP').val()==''){
				if ($('#nombrePRespSSP').val()==''&& ( $('#txtFechaInicioBuscarFolio').val()=='' || $('#txtFechaFinBuscarFolio').val()=='') ){
					alert("Favor de ingresar un Nombre y/o fechas");
					validaNombre=false;
				}else {
				validaNombre=true;
					}						
												
		}
		
		//Funcion que valida si los campos estan llenos al enviar 
		function registraDetencion(){
			$.ajax({								
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/enviarAvisosDetencionIPH.do?folioIPH='+folioIPH+'',
		    	  data: '',				
		    	  dataType: 'xml',
		    	  success: function(xml){
		  			alert("Registro realizado correctamente");
		    	  }
		    	});
		}
		
		/* funcionalidad de Asentar registro de cadena de custodia*/
		$("#btnCadCusNuevaCadCus").click(asentarRegCadenaCustodia);
		/****** función de Asentar registro de cadena de custodia
		requiere un expediente para 
		****/
		function asentarRegCadenaCustodia()
		{
			idWindowAsntarRegCadCus++;
			$.newWindow({id:"iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Asentar registro de cadena de custodia", type:"iframe"});
		    $.updateWindowContent("iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?numeroExpediente='+numeroExpediente +' " width="1000" height="600" />');
		}
		
		/************ FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		function mostrarTab()
		{

	    	$("#tabEvidencias").show();
		}
		
	</script>
	