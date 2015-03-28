<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Registro de Detención</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.zweatherfeed.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.zweatherfeed.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>	

	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	</style>
    
<script type="text/javascript">

	var expedienteId = '<%=request.getParameter("expedienteId")%>';
	var numeroExpediente = '<%=request.getParameter("numeroExpediente")%>';

	//Variable que controla si el grid se carga por primera vez
	var firstGridProbableResponsable = true;
	var firstGridPertenencias = true;

	var idWindowGenerarDetencion = 1;
	var idWindowIngresarPertenencias = 1;
	var idWindowMostrarReincidencias = 1;

	var gridLength = 0;

	var detencionTxtNombreSP = '';
	var detencionTxtCargo = '';
	var detencionTxtAreaAdm = '';
	var detencionTxtFechaElaboracion = '';
	var detencionTxtCoordinadorAMP = '';
	var detencionTxtCoordinadorDP = '';
	
	$(document).ready(function() {
		$('#liDom').hide();
		$('#liDom').addClass("tabEstilo");
		
		$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
			.removeClass( "ui-corner-all ui-corner-top" )
			.addClass( "ui-corner-bottom" );

		$("#fechaInicioLapso").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});

		//Se crean las tabs principales
		$("#tabsconsultaprincipal" ).tabs();

		//Para seleccionar tab por default
		$('#tabsconsultaprincipal').tabs({ selected: 1 });

		$('#btnGenerarDetencion').click(generarDetencion);
		$('#btnAgregarProbableResponsable').click(agregarProbableResponsable);
		$('#btnEliminarProbableResponsable').click(eliminarProbableResponsable);
		$("#estaDetenido").click(habilitaFechaHoraDetencion);		
		$('#btnAgregarPertenencia').click(agregarPertenencia);
		$('#btnCancelarPertenencia').click(eliminarPertenencia);
		$('#btnEnviarUnidadCaptura').click(enviarAUnidadDeCaptura);
		$('#btnMostrarIPH').click(mostrarIPH);

		consultarRegistroDeDetencionPorExpedienteId();
		cargaGridProbableResponsable();		
		cargaGridPertenencias();
		consultarCatalogoTipoPertenencia();
		consultarCatalogoCondicionFisica();

		$('#btnMostrarIPH').hide();
	});

	/**
	** Llena de manera dinámica los subtipos de eventos asociados al tipo de evento seleccionado.
	**/
	function buscaSubTipoEvento(){
		var selected = $('#detencionCbxTipoEvento option:selected').val();
		$('#detencionCbxSubtipoEvento').attr('selectedIndex',0);
		$('#detencionCbxSubtipoEvento').empty();
		$('#detencionCbxSubtipoEvento').append('<option value="-1">-Seleccione-</option>');
		
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarSubtipoEvento.do?tipoEvento='+selected+'',
			dataType: 'xml',
			success: function(xml){
				if(selected == '1'){
					$(xml).find('delito').each(function(){
						$('#detencionCbxSubtipoEvento').append('<option value="' + $(this).find('claveDelito').text() + '">' + $(this).find('nombre').text() + '</option>');
					});
				}else if(selected == '2'){
					$(xml).find('falta').each(function(){
						$('#detencionCbxSubtipoEvento').append('<option value="' + $(this).find('catFaltaAdministrativaId').text() + '">' + $(this).find('nombreFalta').text() + '</option>');
					});
				}
			}
		});
	}

	/**
	**Funcion que carga el grid con los probables responsables
	**/
	function cargaGridProbableResponsable(){

		if(firstGridProbableResponsable == true){
			
			jQuery("#gridProbableResponsable").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarProbableResponsablePorNumeroExpediente.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',
				datatype: "xml", 
				colNames:['Nombre','Apellido Paterno','Apellido Materno','¿Detenido?','Fecha de Detención','¿Menor de Edad?'], 
				colModel:[ 	{name:'Nombre',index:'1', width:160},
				           	{name:'ApellidoPaterno',index:'2', width:120},
				           	{name:'ApellidoMaterno',index:'3', width:120},
				           	{name:'Detencion',index:'4', width:90},
				           	{name:'FechaDetencion',index:'5', width:150},
				           	{name:'MenorDeEdad',index:'6', width:120}				           	
						],
				pager: jQuery('#pagerGridProbableResponsable'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
				//autoheight:true,
				width:700,
				height:115,
				sortname: 'nombre',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridProbableResponsable',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridProbableResponsable=false;
		}
		else{
			jQuery("#gridProbableResponsable").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarProbableResponsablePorNumeroExpediente.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',datatype: 'xml'});
			$("#gridProbableResponsable").trigger("reloadGrid");			
		}
		
		setTimeout('armaComboProbableResponsable()',3000);
	}

    function generarDetencion(){
    	var arrayIds = jQuery("#gridProbableResponsable").getDataIDs();
        if($('#detencionCbxTipoEvento option:selected').val() == -1 || $('#detencionCbxSubtipoEvento option:selected').val() == -1 || arrayIds.length < 1){
            alertDinamico('Debe seleccionar Tipo de Evento, Subtipo de Evento y debe agregar al menos un Probable Responsable.');
        }else{
	   		idWindowGenerarDetencion++;
	   		$.newWindow({id:"iframewindowGenerarDetencion" + idWindowGenerarDetencion, statusBar: true, posx:30,posy:10,width:950,height:500,title:"Generar Detención", type:"iframe"});
	   		$.updateWindowContent("iframewindowGenerarDetencion" + idWindowGenerarDetencion,'<iframe src="<%= request.getContextPath() %>/mostrarGenerarDetencion.do?numeroExpediente='+numeroExpediente+'" width="950" height="500" />');
        }	
    }

	/**
	**Funcion que carga el grid con las pertenencias de los probables responsables
	**/
	function cargaGridPertenencias(){

		if(firstGridPertenencias == true){
		
			jQuery("#gridPertenencias").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarPertenenciasPorDetencionId.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'', 
				datatype: "xml", 
				colNames:['Probable Responsable','Cantidad','Tipo','Condición Física','Descripción' ], 
				colModel:[ 	{name:'ProbableResponsable',index:'probableResponsable', width:200},
				           	{name:'Cantidad',index:'cantidad', width:60},
				           	{name:'Tipo', index:'tipo', width:150},
				           	{name:'CondicionFisica', index:'condicionFisica', width:150},
				           	{name:'Descripcion', index:'descripcion', width:200}
						],
				pager: jQuery('#pagerGridPertenencias'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
				//autoheight:true,
				width:780,
				height:220,
				sortname: 'nombre',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridPertenencias',{edit:false,add:false,del:false});
			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridPertenencias=false;
		}
		else{
			jQuery("#gridPertenencias").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarPertenenciasPorDetencionId.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',datatype: "xml" });
			$("#gridPertenencias").trigger("reloadGrid");			
		}	
	}

    function customRange(input) {
  	  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
  		$('#idHoraDateLapsoInicio').timeEntry('getTime') : null),
  		maxTime: (input.id == 'idHoraDateLapsoInicio' ?
  		$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
      }
      $(function(){
        $('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
      });

      function agregarProbableResponsable(){
  		if( ( !$("#esDesconocido").is(':checked') 
  		  		&& ($('#nombre').val() == '' || $('#apellidoPaterno').val() == '' || $('#apellidoMaterno').val() == '' ) )
  	  		 || ( $("#estaDetenido").is(':checked') 
  	    	  	&& ($('#fechaInicioLapso').val() == '' || $('#horaInicioLapso').val() == '') ) 
			 || ( $('#detencionCbxTipoEvento option:selected').val() == -1 || $('#detencionCbxSubtipoEvento option:selected').val() == -1 ) ){
				alertDinamico('Debe seleccionar todos los datos del probable responsable.');
		}else{	          
			  var nombre = $('#nombre').val();
			  var apellidoPaterno = $('#apellidoPaterno').val(); 
			  var apellidoMaterno = $('#apellidoMaterno').val();
			  var detencion = $("#estaDetenido").is(':checked') ? 'Si' : 'No';
			  var fechaDetencion = $('#fechaInicioLapso').val();
			  var horarioDetencion = $('#horaInicioLapso').val();
			  var menorDeEdad = $("#esMenorDeEdad").is(':checked') ? 'Si' : 'No';
			  var desconocido = 'No';
			  
			  if($("#esDesconocido").is(':checked')){
				  desconocido = 'Si';
				  nombre = 'Desconocido';
				  apellidoPaterno = '';
				  apellidoMaterno = '';
			  }

			  if(!$("#estaDetenido").is(':checked')){
				  fechaDetencion = '';
			  }

			  var params = '&nombre=' + nombre + '&apellidoPaterno=' + apellidoPaterno + '&apellidoMaterno=' + apellidoMaterno
			  			 + '&detencion=' + detencion + '&fechaDetencion=' + fechaDetencion + '&horarioDetencion=' + horarioDetencion
			  			 + '&desconocido=' + desconocido;

	          $('#nombre').val('');
			  $('#apellidoPaterno').val('');
			  $('#apellidoMaterno').val('');
			  $('#horaInicioLapso').val('07:00 AM');
			  $("#estaDetenido").attr('checked','');
			  $("#chkMayorEdad").attr('checked','');
			  $("#esDesconocido").attr('checked','');
			  deshabilitaFechaHoraDetencion();

			  $.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/agregarProbableResponsable.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',
					data: params,
					dataType: 'xml',
					success: function(xml){
						alertDinamico('El probable responsable se agregó de manera correcta.');
						//jQuery("#gridProbableResponsable").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarProbableResponsablePorExpedienteId.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',datatype: "xml" });
						//$("#gridProbableResponsable").trigger("reloadGrid");
						cargaGridProbableResponsable();
					}
			  });

			  //gridLength++;
						   
		}  
      }

      function eliminarProbableResponsable(){
    	  var rowid = jQuery("#gridProbableResponsable").jqGrid('getGridParam','selrow');		

  		  if (rowid) { 
  			 $.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/eliminarProbableResponsable.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'&involucradoId='+rowid+'',
				dataType: 'xml',
				success: function(xml){
					alertDinamico('El probable responsable se eliminó de manera correcta.');
					//jQuery("#gridProbableResponsable").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarProbableResponsable.do?',datatype: "xml" });
					//$("#gridProbableResponsable").trigger("reloadGrid");
					cargaGridProbableResponsable();
				}
		  	 });

  			//gridLength--;
  		  }else{
  			 alertDinamico("Por favor seleccione un probable responsable");
  		  } 	
      }

      function habilitaFechaHoraDetencion(){
    	if($("#estaDetenido").is(':checked')){
  			var today = new Date();
  			var fechaActual = (today.getDate() < 10 ? '0' + today.getDate() : today.getDate()) + '/' 
  		  				   + (today.getMonth() < 9 ? '0' + (today.getMonth() + 1) : (today.getMonth() + 1)) + '/' 
  		  				   + today.getFullYear();
  			$("#fechaInicioLapso").val(fechaActual);
  			$("#horaInicioLapso").attr('disabled','');
  			$("#horaInicioLapso").val('07:00 AM');
  		}else{
  			deshabilitaFechaHoraDetencion();
  		}
  	}

  	function deshabilitaFechaHoraDetencion(){
  		$("#fechaInicioLapso").val('');
  		$("#horaInicioLapso").attr('disabled','disabled');
  		$("#horaInicioLapso").val('');
  	}

	function agregarPertenencia(){
		if($('#detencionCbxProbableResponsable option:selected').val() == -1 
				|| $('#detencionCbxTipoPertenencia option:selected').val() == -1
				|| $('#detencionCbxCondicionFisica option:selected').val() == -1
				|| $('#detencionTxtCantidad').val() == ''
				|| $('#detencionTxtDescripcion').val() == ''){
				alertDinamico('Debe seleccionar todos los parámetros de pertenencia.');
		}else{
		  	  //var arrayIds = jQuery("#gridPertenencias").getDataIDs();
			  //var id = arrayIds.length + 1;

			  var probableResponsableId = $('#detencionCbxProbableResponsable option:selected').val();
			  var probableResponsable = $('#detencionCbxProbableResponsable option:selected').text();
			  var cantidad = $('#detencionTxtCantidad').val(); 
			  var tipoId = $('#detencionCbxTipoPertenencia option:selected').val();			  
			  var tipo = $('#detencionCbxTipoPertenencia option:selected').text();
			  var condicionFisicaId = $('#detencionCbxCondicionFisica option:selected').val();
			  var condicionFisica = $('#detencionCbxCondicionFisica option:selected').text();
			  var descripcion = $('#detencionTxtDescripcion').val();
			  
			  var params = '&probableResponsableId=' + probableResponsableId + '&cantidad=' + cantidad + '&tipoId=' + tipoId 
			  			 + '&condicionFisicaId=' + condicionFisicaId + '&descripcion=' + descripcion;
				  										  
		      $('#detencionCbxProbableResponsable').val('-1');
			  $('#detencionCbxTipoPertenencia').val('-1');
			  $('#detencionCbxCondicionFisica').val('-1');
			  $('#detencionTxtCantidad').val('');
			  $('#detencionTxtDescripcion').val('');

			  $.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/agregarPertenencia.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'',
					data: params,
					dataType: 'xml',
					success: function(xml){
						alertDinamico('La pertenencia se agregó de manera correcta');
						//jQuery("#gridPertenencia").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarPertenencia.do?',datatype: "xml" });
						//$("#gridPertenencia").trigger("reloadGrid");
						cargaGridPertenencias();
					}
			  });
			  
		}
	}

	function eliminarPertenencia(){
		var rowid = jQuery("#gridPertenencias").jqGrid('getGridParam','selrow');		

	    if (rowid) { 
			//jQuery("#gridPertenencias").jqGrid('delRowData',row);
			//var rowCount = $(".gridPertenencias tr").length;
 			 $.ajax({
 				async: false,
 				type: 'POST',
 				url: '<%= request.getContextPath()%>/eliminarPertenencia.do?expedienteId='+expedienteId+'&numeroExpediente='+numeroExpediente+'&pertenenciaId='+rowid+'',
 				dataType: 'xml',
 				success: function(xml){
 					alertDinamico('La pertenencia se eliminó de manera correcta.');
 					//jQuery("#gridProbableResponsable").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarProbableResponsable.do?',datatype: "xml" });
 					//$("#gridProbableResponsable").trigger("reloadGrid");
 					cargaGridPertenencias();
 				}
 		  	 });			
		}else{
			alertDinamico("Por favor seleccione una pertenencia del probable responsable");
		}	
	}

	/**Funcion que consulta el catalogo de tipos de pertenencia*/
	function consultarCatalogoTipoPertenencia(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarTiposDePertenencia.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catTipoDePertenencia').each(function(){
					$('#detencionCbxTipoPertenencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
	}
	
	/**Funcion que consulta el catalogo de condición física del objeto*/
	function consultarCatalogoCondicionFisica(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCondicion.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catCondicion').each(function(){
					$('#detencionCbxCondicionFisica').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
	}

	function consultarRegistroDeDetencionPorExpedienteId(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerAvisoPorIdExpediente.do?expedienteId='+expedienteId+'',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				if($(xml).find('tipoPertenencia').text() != null && $(xml).find('tipoPertenencia').text() != ''){
					$("#detencionCbxTipoPertenencia").val($(xml).find('tipoPertenencia').text()); //combo
				}
				buscaSubTipoEvento();
				if($(xml).find('condicionFisica').text() != null && $(xml).find('condicionFisica').text() != ''){
					$("#detencionCbxCondicionFisica").val($(xml).find('condicionFisica').text()); //combo
				}

				if($(xml).find('avisoHechoDelictivoDTO').text() != null && $(xml).find('avisoHechoDelictivoDTO').text() != ''){
					detencionTxtNombreSP =
						$(xml).find('usuario').find('funcionario').find('nombreFuncionario').text() + ' ' 
						+ $(xml).find('usuario').find('funcionario').find('apellidoPaternoFuncionario').text() + ' '
						+ $(xml).find('usuario').find('funcionario').find('apellidoMaternoFuncionario').text(); 

					detencionTxtCargo = $(xml).find('usuario').find('funcionario').find('puesto').find('valor').text();
					detencionTxtAreaAdm = $(xml).find('usuario').find('areaActual').find('nombre').text();
					detencionTxtFechaElaboracion = $(xml).find('fechaCreacion').text();
					$("#detencionTxtCoordinadorAMP").val($(xml).find('coordinadorAMP').text());
					$("#detencionTxtCoordinadorDP").val($(xml).find('coordinadorDP').text());
				}
										
			}
		});		
	}

	function armaComboProbableResponsable(){
		$('#detencionCbxProbableResponsable').attr('selectedIndex',0);
		$('#detencionCbxProbableResponsable').empty();
		$('#detencionCbxProbableResponsable').append('<option value="-1">-Seleccione-</option>');
		
		var arrayIds = jQuery("#gridProbableResponsable").getDataIDs();
		for(var i=0; i<arrayIds.length; i++){
			var ret = jQuery("#gridProbableResponsable").jqGrid('getRowData',arrayIds[i]);
			if(ret.Detencion == 'Si'){
				$('#detencionCbxProbableResponsable').append('<option value="' + arrayIds[i] + '">' + (ret.Nombre + ' ' + ret.ApellidoPaterno + ' ' + ret.ApellidoMaterno) + '</option>');
			}			
		}
	}

	function cambiaNombreDesconocido(){
		if($("#esDesconocido").is(':checked')){
			$('#nombre').val('Desconocido');
			$('#apellidoPaterno').val('');
			$('#apellidoMaterno').val('');
			$('#nombre').attr('disabled','disabled');
			$('#apellidoPaterno').attr('disabled','disabled');
			$('#apellidoMaterno').attr('disabled','disabled');			
		}else{
			$('#nombre').val('');
			$('#nombre').attr('disabled','');
			$('#apellidoPaterno').attr('disabled','');
			$('#apellidoMaterno').attr('disabled','');
		}
	}

	function enviarAUnidadDeCaptura(){
		/*$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/enviarAUnidadDeCaptura.do?numeroExpediente='+numeroExpediente+'',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				alertDinamico("Informe Policial Homologado generado de manera correcta");
			}
		});*/
		$('#btnEnviarUnidadCaptura').hide();
		$('#btnMostrarIPH').show();				
	}

	function mostrarIPH(){
	}


</script>

</head>

<body>
<div id="dialog-Alert" style="display: none">
<table>
<tr>
<td>
<span id="divAlertTexto"></span>
</td>
</tr>
</table>	
</div>
	<div id="tabsconsultaprincipal">
		<ul>
			<li id="tabRegistroPertenencias"><a href="#tabsconsultaprincipal-1">Registro de Pertenencias</a></li>
			<li id="tabDetencion"><a href="#tabsconsultaprincipal-2">Detenci&oacute;n</a></li>
		</ul>
			
		<!--COMIENZA TAB DE REGISTRO DE PERTENENCIAS-->
		<div id="tabsconsultaprincipal-1" class="tabRegistroPertenencias">
			<fieldset style="width: 800px;">
				<legend>Pertenencias</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td align="right"><bean:message key="probableResponsable"/>:</td>
						<td align="left">						
							<select id="detencionCbxProbableResponsable" style="width: 180px;" tabindex="1">
								<option value="-1">- Seleccione -</option>
							</select>
						</td>
						<td align="right">Descripci&oacute;n:</td>
						<td style="width: 200px;"><input type="text" style="width: 180px;" maxlength="30" id="detencionTxtDescripcion" tabindex="4"/></td>						
					</tr>
					<tr>
						<td align="right" >Tipo de Pertenencia:</td>
						<td style="width: 200px;">
							<select id="detencionCbxTipoPertenencia" style="width: 180px;" tabindex="2">
								<option value="-1">- Seleccione -</option>
							</select>
						</td>					
						<td align="right">Cantidad:</td>
						<td style="width: 200px;"><input type="text" style="width: 50px;" maxlength="5" id="detencionTxtCantidad" tabindex="5"/></td>
					</tr>
					<tr>
						<td align="right">Condici&oacute;n F&iacute;sica:</td>
						<td style="width: 200px;">
							<select id="detencionCbxCondicionFisica" style="width: 180px;" tabindex="3">
								<option value="-1">- Seleccione -</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" value="Agregar Pertenencia" id="btnAgregarPertenencia" class="back_button" tabindex="6"/>
							&nbsp;
							<!--<input type="button" value="Cancelar Pertenencia" id="btnCancelarPertenencia" class="back_button" tabindex="7"/>-->
						</td>					
					</tr>		
					<tr>
						<td colspan="4"><br></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
		            		<table id="gridPertenencias"></table>
		            		<div id="pagerGridPertenencias"></div>
		            	</td>
					</tr>
				</table>
			</fieldset>
		</div>
		<!--TERMINA TAB DE REGISTRO DE PERTENENCIAS-->
		
		<!--COMIENZA TAB DE DETENCION-->
		<!--<form id="profileForm" class="actionForm" method="get">-->
		<div id="tabsconsultaprincipal-2" class="tabDetencion">
			<fieldset style="width: 800px;">
				<legend>Descripci&oacute;n</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td align="right" style="width: 200px;">Tipo de Evento:</td>
						<td>
							<select id="detencionCbxTipoEvento" onchange="buscaSubTipoEvento();" tabindex="1">
								<option value="-1">- Seleccione -</option>
								<option value="1">Delito</option>
								<option value="2">Falta Administrativa</option>
							</select>
						</td>
						<td align="right">Subtipo de Evento:</td>
						<td>
							<select id="detencionCbxSubtipoEvento" style="width: 180px;" tabindex="2">
								<option value="-1">- Seleccione -</option>
							</select>
						</td>							
					</tr>
				</table>
			</fieldset>
			<br>
			<fieldset style="width: 800px;">
				<legend>Probables Responsables Detenidos y No Detenidos</legend>
				<table width="100%" border="0" height="90%">
					<tr>
						<td align="right">Nombre(s):</td>
						<td><input type="text" style="width: 120px;" maxlength="30" id="nombre" tabindex="3"/></td>
						<td align="right">¿Es detenido?:</td>
						<td><input type="checkbox" id="estaDetenido" tabindex="6"/></td>
						<td align="right">¿Es menor de edad?:</td>
						<td><input type="checkbox" id="esMenorDeEdad" tabindex="9"/></td>						
					</tr>
					<tr>
						<td align="right">Apellido Paterno:</td>
						<td><input type="text" style="width: 120px;" maxlength="30" id="apellidoPaterno" tabindex="4"/></td>					
						<td align="right">Fecha de detenci&oacute;n:</td>
						<td><input type="text" style="width: 70px;" maxlength="8" id="fechaInicioLapso" class="dateRange" disabled="disabled" tabindex="7"/></td>
						<td align="right">¿No proporcion&oacute; nombre?:</td>
						<td><input type="checkbox" id="esDesconocido" tabindex="10" onclick="cambiaNombreDesconocido();"/></td>						
					</tr>
					<tr>
						<td align="right">Apellido Materno:</td>
						<td><input type="text" style="width: 120px;" maxlength="30" id="apellidoMaterno" tabindex="5"/></td>
						<td align="right">Hora de detenci&oacute;n:</td>
						<td><input type="text" style="width: 70px;" maxlength="8" id="horaInicioLapso" class="timeRange" disabled="disabled" tabindex="8"/></td>						
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="right" colspan="2">
							<input type="button" value='<bean:message key="ingProbaleResponsable"/>' id="btnAgregarProbableResponsable" class="back_button" tabindex="11"/>
						</td>
						<td align="left" colspan="2">
							<input type="button" value='<bean:message key="elimProbaleResponsable"/>' id="btnEliminarProbableResponsable" class="back_button" tabindex="12"/>
						</td>
						<td>&nbsp;</td>						
					</tr>
					<tr>
						<td colspan="6"><br></td>
					</tr>
					<tr>
						<td colspan="6" align="center">
		            		<table id="gridProbableResponsable"></table>
		            		<div id="pagerGridProbableResponsable"></div>
		            	</td>
					</tr>
				</table>
			</fieldset>

			<table border="0" height="90%" style="width: 800px;">
				<tr>
					<td align="right" style="width: 200px;"><input type="button" value="Generar Detención" id="btnGenerarDetencion" class="back_button" tabindex="13"/></td>
					<td align="left" style="width: 200px;"><input type="button" value="Enviar a Unidad de Captura" id="btnEnviarUnidadCaptura" class="back_button" tabindex="14"/></td>					
					<td align="left" style="width: 200px;"><input type="button" value="Mostrar IPH" id="btnMostrarIPH" class="back_button" tabindex="14"/></td>
				</tr>
			</table>
		</div>
		<!--</form>-->
		<!--TERMINA TAB DE DETENCION-->		
				
	</div>
</body>
</html>
