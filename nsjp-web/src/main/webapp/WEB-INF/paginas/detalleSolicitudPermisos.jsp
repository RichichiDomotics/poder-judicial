<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Instituciones"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Solicitar Permisos</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
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
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

	<script type="text/javascript">
	var reloadGridExpediente=false;
	var tipoOrigen=2;//variable que se usa en la busqueda del expediente en nueva auditoria por numero de expediente
	var idWindowNuevaDenuncia=1;
	var idWindowNuevaDenunciaDos=1;
	var idWindowDetalleAuditoria=1;
	
		$(document).ready(function() {
			//Permite consultar las areas de la institucion
			var idInstitucion = <%=Instituciones.PGJ.getValorId()%>;
			var idAreaVisitaduria = <%=Areas.VISITADURIA.ordinal()%>;
//			cargaComboDeDepartamentos();
			consultarDistritos();
			consultarTipoVisita();
			cargaComboVistadores(idInstitucion, idAreaVisitaduria);
			//$('#cbxAreas').change(function(){cargaCompoFuncionarios()});
			$('#cbxNombresAMP').change(consultarExpedientes);
			$('#tableVisitador').hide();
			$('#boton').click(asignarAuditor);

			//ocultamos la pantalla con el cuerpo de los filtros de busqueda
			$("#divBusquedaPorNombre").show();
			$("#porExpediente").hide();
			//Asginamos el listener a los radios
			$("#rdbFiltroNombreAMP,#rdbFiltroExpediente").bind("click",seleccionaFiltroBusqueda);
			
			$("#buscarExpediente").bind("click",validaCamposExpediente);
			$("#buscarExpediente").bind("click",llenaGridExpediente);
			
			llenaCombosFiltroPorNombre();
			consultarExpedientes();
		});
		//termina funcion onready
		function asignarAuditor(){
			var s;
			s = jQuery("#gridDetalleFrmPrincipal").jqGrid('getGridParam','selarrrow');
			var idFuncionario=$("#cbxNombresAMPVis option:selected").val();
			var tipoVisita=$("#cbxTipoVisita option:selected").val();

			if(idFuncionario == 0){
				alertDinamico("Seleccione un visitador");
			}else if(tipoVisita == 0){
				alertDinamico("Seleccione un tipo de visita");
			}else{

				idWindowDetalleAuditoria++;
				$.newWindow({id:"iframewindowDetalleAuditoria"+idWindowDetalleAuditoria, statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Carpetas de Investigación", type:"iframe"});
				$.maximizeWindow("iframewindowDetalleAuditoria"+idWindowDetalleAuditoria);
				$.updateWindowContent("iframewindowDetalleAuditoria"+idWindowDetalleAuditoria,'<iframe src="<%= request.getContextPath() %>/expedientesAuditados.do?idExpedientes='+s+'&idFuncionario='+idFuncionario+'&tipoVisita='+tipoVisita+'" width="100%" height="100%" />');
				
				//$.newWindow({id:"iframewindowDetalleAuditoria", statusBar: true, posx:20,posy:20,width:1180,height:550,title:"Nuevas Auditor&iacute;a", type:"iframe"});
				//$.updateWindowContent("iframewindowDetalleAuditoria",'<iframe src="<%= request.getContextPath() %>/expedientesAuditados.do?idExpedientes='+s+'&idFuncionario='+idFuncionario+'" width="850" height="450" />');
			}		
		}
		
		
		
		function seleccionaFiltroBusqueda()
		{	
			var opcionFiltro = $(':radio[name=rdbFiltroBusqueda]:checked').val();
			if(parseInt(opcionFiltro)==1)
			{
				//busqueda por nombre del AMP
				$("#divBusquedaPorNombre").show();
				$("#porExpediente").hide();
			}
			else
			{
				//busqueda por el numero del expediente
				$("#divBusquedaPorNombre").hide();
				$("#porExpediente").show();
			}
		}

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
			
		function abreDetalleExpediente(id)
		{
			var pantallaSolicitada=1;
			idWindowNuevaDenuncia++;
			var ingresoDenuncia = false;
			$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: ", type:"iframe"});
			$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenuncia,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'" width="1430" height="670" />');
		}
		
		function llenaCombosFiltroPorNombre()
		{
			//obtenemos datos para la consulta de la informacion
			var idInstitucion;
			var idArea;
			//hacemos la consulta del usuario que esta firmado
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultaFuncionarioFirmado.do',
				dataType: 'xml',
				success: function(xml){
						//revisamos la respuesta enviada
						if(parseInt($(xml).find('code').text())==0)
						{
							var bandera=$(xml).find('exito').find('bandera').text();
							if(bandera=='')
							{
								//si entra es xq todo fue exitoso
								idArea=$(xml).find('area').text();
								idInstitucion=$(xml).find('institucion').text();
								//Cargamos las areas
								 $.ajax({
							    	  type: 'POST',
							    	  url: '<%= request.getContextPath()%>/consultarCatalogoAreas.do',
							    	  data: 'glCatInstitucionId=' + selected.val(),	//Parametro para hacer la consulta de Areas por Id de la Institucion
							    	  async: false,
							    	  dataType: 'xml',
							    	  success: function(xml){
							    			$('#cbxAreas').empty();
							    			$('#cbxAreas').append( '<option value="1">-Seleccione-</option>');		    			
								    	 	$(xml).find('areas').each(function(){
											$('#cbxAreas').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
											   });
							    	  }
							    	});
							}
						}
				}
			});
		}
		
		/*
		* Permite llenar el combo de Areas(Departamentos en nuestro caso)
		* Permite cargar las Areas por Id de la Institucion
		*/
		function cargaComboDeDepartamentos() {
	         $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarCatalogoDeDepartamentosXFiltro.do',
		    	  data: '',
		    	  async: false,
		    	  dataType: 'xml',
		    	  success: function(xml){
		    			$('#cbxAreas').empty();
		    			$('#cbxAreas').append( '<option value="nop">-Seleccione-</option>');		    			
			    	 	$(xml).find('departamentos').each(function(){
						$('#cbxAreas').append('<option value="' + $(this).find('jerarquiaOrganizacionalId').text() + '">' + $(this).find('nombre').text() + '</option>');
						   });
		    	  }
		    	});				
		}
		
		//permite llenar el combo de funcionarios
		function cargaCompoFuncionarios() {
			var institucion = "";
			var area = "";
			var departamento =  $("#cbxAreas option:selected").val();
			var discriminante =  $("#cbxAgencia option:selected").val();

			if(departamento != "nop" && discriminante != "0"){
				$.ajax({
					async: false,
					type: 'POST',
					url:'<%=request.getContextPath()%>/consultarFuncionarioPorDepartamento.do?idDepartamento='+ departamento +'', 
					dataType: 'xml',
					success: function(xml){
		    			$('#cbxNombresAMP').empty();
		    			$('#cbxNombresAMP').append( '<option value="nop">-Seleccione-</option>');
						$(xml).find('funcionario').each(function(){
							if( $(this).find('catDiscriminanteId').first().text() == discriminante){
							$('#cbxNombresAMP').append('<option value="' + $(this).find('claveFuncionario').text() + '">' 
									+ $(this).find('nombreFuncionario').text() + " "
									+ $(this).find('apellidoPaternoFuncionario').text() + " "
									+ $(this).find('apellidoMaternoFuncionario').text()
									+ '</option>');
							}							
						});
					}
				});
				$("#gridDetalleFrmPrincipal").show();
			}else{
				$('#cbxNombresAMP').empty();
    			$('#cbxNombresAMP').append( '<option value="nop">-Seleccione-</option>');
    			jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/limpiarGrid.do?numeroColumnas=0',datatype: "xml" });
				$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
			}
		}  
		
	   //Llena el grid con los resultados de la busqueda, pasa como parametros el idFuncionario, idArea
	   var banderaCargarORecargar=0;
	   	function consultarExpedientes(){
		   	
			  var idFuncionario = $("#cbxNombresAMP option:selected").val();

			  
					//Inicia grid
					if(banderaCargarORecargar==0){
						jQuery("#gridDetalleFrmPrincipal").jqGrid({
							url:'<%=request.getContextPath()%>/consultarExpedientesPorFuncionarioAreaEstatus.do?idFuncionario='+ idFuncionario + 			
									'', 
							datatype: "xml",  							
							colNames:['Número de Expediente','Número de caso','Delito Principal'], 
							colModel:[  {name:'Expediente',index:'expediente',width:250,align:'center'},
							            {name:'Caso',index:'caso', width:250,align:'center'},
										{name:'Delito',index:'delito',width:300,align:'center'}
									],
									pager: jQuery('#pager1'), 
									rowNum:10,
									rowList:[10,20,30],
				                    autowidth: false,
				                    //autoheight:false,
				                    height:250,
									sortname: 'Expediente', 
									viewrecords: true,
				                    sortorder: "desc",
				    				multiselect: true,
				    				caption: "Expedientes",
				    				ondblClickRow: function(id) {
				    					detEvi(id);
				    					},
				    				onSelectRow: function(id){
										validaExistenExpedientesSeleccionados();
									}      							 												
									});
			        	$("#gridDetalleFrmPrincipal").trigger("reloadGrid");
			        	banderaCargarORecargar=1;
			    }else{
			    	jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarExpedientesPorFuncionarioAreaEstatus.do?idFuncionario='+ idFuncionario +			
						'',datatype: "xml"});
					$("#gridDetalleFrmPrincipal").trigger("reloadGrid");		
			    }	            	
				//Fin grid
		  
		}//Cierra consultarExpedientes

		function tituloVentana(num){
			$("#iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos+" div.window-titleBar-content").html("Expediente: "+num);
		}
		
		function detEvi(id) 
		{
			idWindowNuevaDenunciaDos++;
			var ingresoDenuncia = true;
			var pantallaSolicitada=7;

			
			$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos, statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Expediente: ", type:"iframe"});
			$.maximizeWindow("iframewindowCarpInvNuevaDenuncia");
			$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos,'<iframe src="<%= request.getContextPath() %>/BusquedaExpedienteSistTrad.do?abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'&pantallaSolicitada='+pantallaSolicitada+'" width="100%" height="100%"/>');
			
			//$.newWindow({id:"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos, statusBar: true, posx:0,posy:0,width:1430,height:670,title:"Expediente: ", type:"iframe"});
			//$.updateWindowContent("iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos,'<iframe src="<%= request.getContextPath() %>/BusquedaExpediente.do?pantallaSolicitada=7&abreenPenal=abrPenal&ingresoDenuncia='+ingresoDenuncia +'&idNumeroExpediente='+id+'" width="1430" height="670" />');
			//$("#" +"iframewindowCarpInvNuevaDenuncia"+idWindowNuevaDenunciaDos+ " .window-maximizeButton").click();
		}

		
		
		function validaExistenExpedientesSeleccionados(){

			var s;
			s = jQuery("#gridDetalleFrmPrincipal").jqGrid('getGridParam','selarrrow');
			if(s.length > 0)
				$('#tableVisitador').show();
			else
				$('#tableVisitador').hide();			
		}
	
		/**
		* Permite llenar el combo de los visitadores, carga el combo con el nombre 
		* de los visitadores asociados ala area de visitaduria dentro de la institucion de PGJ
		*/
		
		function cargaComboVistadores(idInstitucion, idAreaVisitaduria){
			var departamento = <%=Areas.VISITADURIA.ordinal()%>;
			idAreaVisitaduria=<%=Areas.VISITADURIA.ordinal()%>;
			$.ajax({
				async: false,
				type: 'POST',
				url:'<%=request.getContextPath()%>/consultarPersonalOperativoAction.do?institucion='+ idInstitucion +'&area='+ idAreaVisitaduria +'&departamento='+ departamento +'', 
				dataType: 'xml',
				success: function(xml){
	    			$('#cbxNombresAMPVis').empty();
	    			$('#cbxNombresAMPVis').append( '<option value="0">-Seleccione-</option>');
					$(xml).find('row').each(function(){
						$('#cbxNombresAMPVis').append('<option value="' + $(this).attr('id') + '">' + $(this).find('nombre').text() + '</option>');
					});
				}
			});
		}
		
		 /*
		*Funcion que consulta Distritos
		*/
		function consultarDistritos(){		
			$.ajax({
				type: 'POST',
			    url: '<%=request.getContextPath()%>/consultarDistritos.do',
			    data: '',
			    dataType: 'xml',
			    async: false,
			    success: function(xml){
				    	$(xml).find('listaCatalogo').find('catDistritoDTO').each(function(){
							$('#cbxDistrito').append('<option value="' + $(this).find('catDistritoId').text() + '">' + $(this).find('claveDistrito').text()+"-"+ $(this).find('nombreDist').text() + '</option>');
						});					
				}
			});
		}
		 
		function actualizaComboAgencias(){
			distritoId = parseInt($("#cbxDistrito option:selected").val());
			if(distritoId > 0)
				consultarAgenciasXDistrito(distritoId);
			else{
				$('#cbxAgencia').empty();
				$('#cbxAgencia').append('<option value="0">-Seleccione-</option>');
				$('#cbxAreas').empty();
				$('#cbxAreas').append('<option value="nop">-Seleccione-</option>');
			}
		}
		
		function actualizaComboAreas(){
			agenciaId = parseInt($("#cbxAgencia option:selected").val());
			if(agenciaId > 0)
				cargaComboDeDepartamentos();
			else{
				$('#cbxAreas').empty();
				$('#cbxAreas').append('<option value="nop">-Seleccione-</option>');
				$('#cbxNombresAMP').empty();
				$('#cbxNombresAMP').append('<option value="nop">-Seleccione-</option>');
			}
		}
		
		function actualizaComboFuncionarios(){
			areaId = $("#cbxAreas option:selected").val();
			if(areaId != "nop")
				cargaCompoFuncionarios();
			else{
				$('#cbxNombresAMP').empty();
				$('#cbxNombresAMP').append('<option value="nop">-Seleccione-</option>');
			}
		}
		
		function consultarAgenciasXDistrito(distritoId){
			$('#cbxAgencia').empty();
			$('#cbxAgencia').append('<option value="0">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
			    url: '<%=request.getContextPath()%>/consultarDiscriminantesXDistrito.do?distritoId='+distritoId+'',
			    data: '',
			    dataType: 'xml',
			    async: false,
			    success: function(xml){
				    	var contAgencias=0;
				    	$(xml).find('CatDiscriminanteDTO').find('catDiscriminanteDTO').each(function(){
							$('#cbxAgencia').append('<option value="' + $(this).find('catDiscriminanteId').text() + '">' + $(this).find('clave').text()+"-"+ $(this).find('nombre').text() + '</option>');
							contAgencias++;
						});
						if(contAgencias == 0){
							alertDinamico("No existen agencias asociadas a este distrito");
						}
				}
			});
		}
		
		 /*
		*Funcion que consulta los tipos de Visitas
		*/
		function consultarTipoVisita(){		
			$.ajax({
				type: 'POST',
			    url: '<%=request.getContextPath()%>/consultarVisitas.do',
			    data: '',
			    dataType: 'xml',
			    async: false,
			    success: function(xml){
				    	$(xml).find('listaCatalogo').find('catTipoVisita').each(function(){
							$('#cbxTipoVisita').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						});					
				}
			});
		}
		
	</script>

</head>
<body bgcolor="#CCCCCC">   
	<br>
	<table border="0" width="50%" id="tableFiltroBusqueda">
		<tr>
			<td width="28%">&nbsp;Por nombre de AMP :</td>
			<td width="23%" align="left">
				<input type="radio" id="rdbFiltroNombreAMP" name="rdbFiltroBusqueda" value="1" checked="checked" style="display: none;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="32%"><span style="display: none;">Por número de expediente : </span></td>
			<td width="17%" align="left">
				<input type="radio" id="rdbFiltroExpediente" name="rdbFiltroBusqueda" value="2" style="display: none;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
    <hr>
   

	<div id="divBusquedaPorNombre" align="center">
		 <table width="100%" border="0">
	      <tr>
	        <td>Distrito: </td>
	        <td><select id="cbxDistrito"  name="cbxDistrito" style="width: 182px;" onchange="actualizaComboAgencias()">
	          <option value="">- Seleccione -</option>
	        </select></td>
	        <td>Agencia: </td>
	        <td><select id="cbxAgencia"  name="cbxAgencia" style="width: 182px;" onchange="actualizaComboAreas()">
	          <option value="">- Seleccione -</option>
	        </select></td>
	      </tr>
	      <tr>
	        <td>&Aacute;rea del AMP al que se auditar&aacute;: </td>
	        <td><select id="cbxAreas"  name="nombresAMP2" style="width: 182px;" onchange="actualizaComboFuncionarios()">
	          <option value="">- Seleccione -</option>
	        </select></td>
	        <td>Nombre del AMP al que se auditar&aacute;: </td>
	        <td><select id="cbxNombresAMP"  name="nombresAMP" style="width: 182px;">
	          <option value="">- Seleccione -</option>
	        </select></td>
	      </tr>
	    </table>
	    
		<table id="gridDetalleFrmPrincipal"></table>
		<div id="pager1"></div>
		
		<table width="100%" border="0" id="tableVisitador">
			  <tr>
			    <td width="25%">&nbsp;</td>
			    <td width="28%">Tipo de visita :
			      <select id="cbxTipoVisita"  name="" style="width: 182px;">
			        <option value="">- Seleccione -</option>
	            </select></td>
			    <td width="28%">Visitador:
			      <select id="cbxNombresAMPVis"  name="unidadInvestigacion" style="width: 182px;">
			        <option value="">- Seleccione -</option>
	            </select></td>
			    <td width="22%"><input type="button"  class="btn_Generico" value="Asignar Auditor&iacute;a"  id="boton" /></td>
		      </tr>
		</table>				
	</div>		
				
	<div id="porExpediente" style="display: none;" align="center"><jsp:include page="buscarExpedientePorNumeroDeExpediente.jsp"></jsp:include></div>
	
</body>
</html>