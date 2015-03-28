<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Generar Devoluci&oacute;n De Evidencia</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
		
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	 <!--Scrip para el idioma del calSolicitarPericialServiceImplendario-->
    <script type="text/javascript"  src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
      
    <!--Scripts necesarios para la ejecucion del editor-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>

	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
	
			//obtenemos el id de la audiencia
			//numeroDeExpedienteId=<%= request.getParameter("numeroDeExpedienteId")%>;
			//obtenemos el numero de folio de la cadena de custodia
			folioCadenaCustodia=<%= request.getParameter("folioCadenaCustodia")%>;
			
			//Se crean las tabs principales
			$("#tabsDevolucionEvidencia" ).tabs();
			//Carga el grid de evidencias 
			cargaGridCadenaDeCustodiaEvidencias(folioCadenaCustodia);
			//Carga los datos del usuario
			cargaDatosDelUsuario();
			//Consulta los tipo de documentos de documento de
			//identificacion del la persona autorizada
			cargaTiposIdentificacionGenerarDevolucion();
		});
		//FIN ON READY

/////////////////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL LA CEJA REMITENTE//////////////////////////////////////////////////

	

	
	/*Funcion que obtiene todos los datos y envia la solicitud de devolucion de evidencia*/
	function enviarNotificacionDevolucionEvidencia(){

		var idsEvidencias = validaEvidencias();
		
		if(idsEvidencias != "error"){
			
			var motivoDevolucion = validaMotivo();

			if(motivoDevolucion != "error"){

				var nombre = validaNombrePersonaAutorizada();
				
				if( nombre != "error"){

					var apPat = validaApPatPersonaAutorizada();

					if(apPat != "error"){

						var apMat = validaApMatPersonaAutorizada();

						if( apMat != "error" ){

							var tipoDocIdent = documentoIdentificacion();

							if(tipoDocIdent != "error"){
						
								var paramDevolucionEvidencia = "";
								 
								paramDevolucionEvidencia =  'idsEvidencias='+idsEvidencias; 
								paramDevolucionEvidencia += '&motivoDevolucion='+ motivoDevolucion;
								paramDevolucionEvidencia += '&nombre='+ nombre;
								paramDevolucionEvidencia += '&apPat='+ apPat;
								paramDevolucionEvidencia += '&apMat='+ apMat;
								paramDevolucionEvidencia += '&tipoDocIdent='+ tipoDocIdent;

							}
						}
					}
				}
			}
		}
	}
	
	/*Funcion que dispara el Action para consultar los datos de usuario*/
    function cargaDatosDelUsuario(){
 	
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDatosUsuario.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	

					//Nombre del funcionario
					$('#nombreFuncionarioGenerarDevolucion').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text()
						);
					//Puesto del funcionario
					$('#puestoFuncionarioGenerarDevolucion').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					//Area administrativa
					$('#areaFuncionarioGenerarDevolucion').val( $(xml).find('usuarioDTO').find('area').find('nombre').first().text()); 		
					//Fecha de la solicitud
					diaActual='<%=(java.util.Calendar.getInstance().get(Calendar.DATE))%>';
					mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
					anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';
					if (mesActual.length == 1){
						$('#fechaSolicitudGenerarDevolucion').val(diaActual+"/0"+mesActual+"/"+anioActual);
					}
					else
						$('#fechaSolicitudGenerarDevolucion').val(diaActual+"/"+mesActual+"/"+anioActual);
					
					
    			}
    		}
    	});
    }

/////////////////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL LA CEJA REMITENTE////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL LA CEJA CADENA DE CUSTODIA//////////////////////////////////////////////////
	/*
	*Funcion que valida que se haya selccionado al menos un registro del grid de evidencias
	*devuelve los ids de las evidencias seleccionadas; muestra mensaje de error y devuelve el 
	*codigo error en caso contrario
	*/
	function validaEvidencias(){
		
		idsEvidencias = jQuery("#gridEvidenciasADevolver").jqGrid('getGridParam','selarrrow');
		if(idsEvidencias != ""){
			return idsEvidencias;
		}
		else{
			alert("Seleccione al menos una evidencia");
			return "error";
		}
		
	}
	
	/*
	*Funcion que carga el grid con evidencias a devolver
	*/
	function cargaGridCadenaDeCustodiaEvidencias(folioCadenaCustodia){
			
			jQuery("#gridEvidenciasADevolver").jqGrid({ 
				
				url:'<%= request.getContextPath()%>/consultarEvidenciasADevolver.do?folioCadenaCustodia='+folioCadenaCustodia+'', 
				datatype: "xml", 
				colNames:['Numero de Evidencia','Tipo de Objeto','Objeto','Descripci&oacute;n','Código de Barras' ], 
				colModel:[ 	
							{name:'NumeroDeEvidencia',index:'numeroDeEvidencia', width:250},
							{name:'TipoObjeto',index:'tipoObjeto', width:250},
				           	{name:'Objeto',index:'objeto', width:200},
				           	{name:'Descripcion',index:'descripcion', width:300},
				           	{name:'CodigoBarras',index:'codigoBarras', width:300},
						],
				pager: jQuery('#pagerGridEvidenciasADevolver'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
				//autoheight:true,
				width:450,
				height:240,
				sortname: 'tipoObjeto',
				viewrecords: true,
				sortorder: "desc",
				multiselect:true
			}).navGrid('#pagerGridEvidenciasADevolver',{edit:false,add:false,del:false});
	}
/////////////////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL LA CEJA CADENA DE CUSTODIA//////////////////////////////////////////////////

/////////////////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL LA MOTIVO//////////////////////////////////////////////////
	/*
	*Funcion que valida que se haya ingresado un motivo de por lo menos
	*/
	function validaMotivo(){

		var motivoDevolucion = $('#motivoGenerarDevolucion').val();

		if(motivoDevolucion != ""){
			return escape(motivoDevolucion);
		}
		else{
			alert("Ingrese un motivo de devolucion");
			return "error";
		}
	}
/////////////////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL LA CEJA MOTIVO//////////////////////////////////////////////////
		
/////////////////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL LA CEJA PERSONA AUTORIZADA//////////////////////////////////////////////////
	
	/*
	*Funcion que valida que se haya ingresado un nombre de la 
	*persona autorizada para llevar la evidencia
	*/	
	function validaNombrePersonaAutorizada(){

	var nombrePersona = $('#nombrePersonaAutorizadaGenerarDevolucion').val();

		if(nombrePersona != ""){
			return nombrePersona;
		}
		else{
			alert("Ingrese el nombre de la persona autorizada");
			return "error";
		}
	}

	/*
	*Funcion que valida que se haya ingresado un ap paterno de la 
	*persona autorizada para llevar la evidencia
	*/	
	function validaApPatPersonaAutorizada(){

	var apPatPersona = $('#apPatPersonaAutorizadaGenerarDevolucion').val();

		if(apPatPersona != ""){
			return apPatPersona;
		}
		else{
			alert("Ingrese el apellido paterno de la persona autorizada");
			return "error";
		}
	}

	/*
	*Funcion que valida que se haya ingresado un ap materno de la 
	*persona autorizada para llevar la evidencia
	*/	
	function validaApMatPersonaAutorizada(){

	var apMatPersona = $('#apMatPersonaAutorizadaGenerarDevolucion').val();

		if(apMatPersona != ""){
			return apMatPersona;
		}
		else{
			alert("Ingrese el apellido materno de la persona autorizada");
			return "error";
		}
	}

	/*
	*Funcion que valida que se haya seleccionado un tipo de documento de identificacion
	*/	
	function documentoIdentificacion(){

		var tipoDoc = $("#cbxTipoIdentificacionPersonaAutorizadaGenerarDevolucion option:selected").val();

		if(tipoDoc != "notSelected"){
			return tipoDoc;
		} 
		else{
			alert("Ingrese el tipo de documento de idetificaion \r de la persona autorizada");
			return "error";
		}
	} 


	/*
	*Funcion que dispara el Action para consultar el tipo de documentos de identificacion
	*/
	function cargaTiposIdentificacionGenerarDevolucion() {
		
		//$('#cbxTipoIdentificacionPersonaAutorizadaGenerarDevolucion').empty();
		     $.ajax({
		      type: 'POST',
		   	  url: '<%= request.getContextPath()%>/ingresarTipoDeDocumentoDeIdentificacion.do',
		   	  data: '',
		   	  dataType: 'xml',
		   	  async: false,
		   	  success: function(xml){
					$(xml).find('catTipoIdentificacion').each(function(){
					$('#cbxTipoIdentificacionPersonaAutorizadaGenerarDevolucion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				   });
		   	  }
		});
	}
/////////////////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL LA CEJA PERSONA AUTORIZADA//////////////////////////////////////////////////	
	</script>
</head>


<body>
	<!--Comienzan tabs principales-->
	<div id="tabsDevolucionEvidencia">
		<ul>
			<li><a href="#tabsDevolucionEvidencia-1">Remitente</a>
			</li>
			<li><a href="#tabsDevolucionEvidencia-2">Cadena de Custodia</a>
			</li>
			<li><a href="#tabsDevolucionEvidencia-3">Motivo</a>
			</li>
			<li><a href="#tabsDevolucionEvidencia-4">Persona Autorizada</a>
			</li>
		</ul>
		
		<!--Comienza tab Remitente-->
		<div id="tabsDevolucionEvidencia-1" align="left">
		
			<table width="900" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="250">&nbsp;</td>
			    <td width="200">&nbsp;</td>
			    <td width="450"><input type="button" value="Enviar Notificacion" onclick="enviarNotificacionDevolucionEvidencia();" class="btn_Generico"/></td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Nombre del Servidor Público:</strong></td>
			    <td align="left"><input type="text" id="nombreFuncionarioGenerarDevolucion" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
			    <td></td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Cargo:</strong></td>
			    <td align="left"><input type="text" id="puestoFuncionarioGenerarDevolucion" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Área Administrativa:</strong></td>
			    <td align="left"><input type="text" id="areaFuncionarioGenerarDevolucion" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Fecha de Solicitud:</strong></td>
			    <td align="left"><input type="text" id="fechaSolicitudGenerarDevolucion" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			
		</div>
		<!--Termina tab Remitente-->
		
		<!--Comienza tab Cadena de Custodia-->
		<div id="tabsDevolucionEvidencia-2" align="left">
			
			<table width="800" border="0" cellspacing="0" cellpadding="0">		       
				<tr>
					<td>		   
			           <table id="gridEvidenciasADevolver"></table>
			           <div id="pagerGridEvidenciasADevolver"></div>
		           </td>
		       </tr>
			</table>	
						
		</div>
		<!--Termina tab Cadena de Custodia-->
		
		<!--Comienza tab Motivo-->
		<div id="tabsDevolucionEvidencia-3" align="left">
			<textarea class="jquery_ckeditor" cols="150" id="motivoGenerarDevolucion" rows="10"></textarea>										
		</div>
		<!--Termina tab Motivo-->
						
		<!--Comienza tab Persona Autorizada-->
		<div id="tabsDevolucionEvidencia-4" align="left">
			
			<table width="900" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="250">&nbsp;</td>
			    <td width="200">&nbsp;</td>
			    <td width="450">&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Nombre:</strong></td>
			    <td align="left"><input type="text" id="nombrePersonaAutorizadaGenerarDevolucion" style="width:250px;"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Apellido Paterno:</strong></td>
			    <td align="left"><input type="text" id="apPatPersonaAutorizadaGenerarDevolucion" style="width:250px;"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Apellido Materno:</strong></td>
			    <td align="left"><input type="text" id="apMatPersonaAutorizadaGenerarDevolucion" style="width:250px;"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Tipo de identificaci&oacute;n:</strong></td>
			    <td align="left">
			    	<select id="cbxTipoIdentificacionPersonaAutorizadaGenerarDevolucion" style="width: 250px">
		                <option value="notSelected">-Seleccione-</option>
		            </select></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
									
		</div>
		<!--Termina tab Persona Autorizada-->

	</div>
	<!--Terminan tabs principales-->
</body>

<script type="text/javascript">
	var config = {					
		toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Table','HorizontalRule'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		],
		height:'250px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);
</script>
</html>