<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administrar carpeta de ejecuci&oacute;n</title>

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

	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">
	
	//mes que se muestra en la caja de texto
	var mesActual;
	//anio que se muestra en la caja de texto
	var anioActual;
	//Arreglo usado como carrusel para obtener los meses
	var meses =["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	//bandera de cambio para recorrer el carrusel de meses
	var banderaCambio=0;
	//primera consulta muestra los grids de salas
	var primeraConsulta= "true";
	//primera consulta jueces muestra los grids de jueces
	var primeraConsultaJueces= "true";
	//Datos que vienen de asignarSalaTemporalPJENA.jsp
	var datosSalaTemporal="";
	//variable que indica si es una sala temporal
	var salaTemporal="false";
	//tipo de audiencia solicitada	
	var tipoAudiencia;
	//fecha actual
	var fechaReal="";
	//hora seleccionada en el grid
	var horaSeleccionada="";
	//Minuto seleccionado en el grid
	var minutoSeleccionado="";
	//Duracion estimada de la audiencia
	var duracionEstimadaAudiencia="";
	//identificacion de la sala selccionada
	var idSalaSeleccionada="";
	//id de la audiencia
	var idAudiencia=parent.audienciaID;
	//id del expediente
	var numeroExpedienteId=parent.expedienteID;
	var tipoAudiencia;
	//variable que almacena el nombre de las salas
	var nombreSalas=new Array();
	//numero de causa
	var numeroCausa;
	//id del involucrad
	var idInvolucrado;
	var documentoIdPACP;
	var Involucradoid;
	var numeroCarpetaE;
	var documentoID=parent.documentoID;

	var varparaEncargadoSala=parent.varparaEncargadoSala;
	$(document).ready(function() {
		if(varparaEncargadoSala==true){
   $("#idpestaniaProgramarAudiencia").css("display", "none");
		}
		cargaCondicion();
		//parent.actualizaGridsPadre();
		//obtenemos el id de la audiencia
		//idAudiencia=1;
		//idAudiencia=<%= request.getParameter("idAudiencia")%>;
		
		//obtenemos el id del expediente
		//numeroExpedienteId=<%= request.getParameter("numeroExpedienteId")%>;
		
		//obtenemos la fecha actual
		mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
		anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';

		//Consulta los datos generales de la carpeta
		consultarDatosGeneralesCarpeta();
		//Consultamos el detalle de la solicitud de la audiencia
		//consultarDetalleSolicitudDeAudiencia(idAudiencia);
		cargaComboTipoAudiencia(tipoAudiencia);
		//Carga grid sentencias
		cargaGridSentencias();
		//Carga grid documentos relacionados
		cargaGridDocumentosRelacionados();
		//Carga grid de solicitud de beneficios relacionados
		
		cargaGridSolicitudesDeBeneficios2();

		//Carga el grid con las medidas caultelares
//		cargaGridInvolucradosCausaPJENC(numeroCausa);
		
		
		var mesSeleccionado = meses[mesActual];		

		//escrbimos en pantalla la fecha actual
		$('#mes').val(mesSeleccionado);
		$('#anio').val(anioActual);

		/**
		*Funcion que recarga el grid con la funcionalidad de la agenda
		*/
		jQuery("#gridAgendaPJENA").jqGrid({ 
			url:'<%=request.getContextPath()%>/calendarioActual.do', 
			datatype: "xml", 
			colNames:['Lu','Ma','Mi','Ju','Vi','Sa','Do'], 
			colModel:[  {name:'lunes',index:'lunes', width:40, align:'center'},						
			           	{name:'martes',index:'martes', width:40, align:'center'}, 
			           	{name:'miercoles',index:'miercoles', width:40,align:'center'}, 
			           	{name:'jueves',index:'jueves', width:40, align:'center'}, 
			           	{name:'viernes',index:'viernes', width:40, align:'center'}, 
			           	{name:'sabado',index:'sabado', width:40, align:'center'}, 
			           	{name:'domingo',index:'domingo', width:40, align:'center'}, 
										
					],
			rowNum:10,
			//toolbar: [true,"top"],
			rowList:[10,20,30],
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			cellEdit: true,
			scrollOffset:0,
			onSelectCell: function(idRow,dia,fecha) {
				seleccionCelda(idRow,dia,fecha);
				deshabilitarHabilitarComponentes("seleccionFecha");
					},	
			loadComplete: function(){
				//Cambia la bandera para avanzar de mes
				banderaCambio=0;
			} 
		});
		

		//Se crean las tabs principales
		$("#tabsprincipalconsulta" ).tabs();

		//Si el id de la audiencia es null, dehabilita la ceja
		//programar audiencia
		ocultaTabProgramarAudiencia();

		deshabilitarHabilitarComponentes("inicio");
		$('#txtSalaSeleccionada').val("");
		$('#txtHoraInicioSeleccionada').val("");
		
		$('#solicitarEstudioPJOAE').click(solicitarEstudio);
		
		
	
	});
	//FIN ON READY
	

	//Si el id de la audiencia es null, dehabilita la ceja
	//programar audiencia
	function ocultaTabProgramarAudiencia(){
		if(idAudiencia == null){
			$("#tabs").tabs("option", "disabled", [3]);
			$('#progAudienciaLi').hide();	
		}
	}

	
	function solicitarEstudio(){
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registrarSolicitudEstudioCarpetaEjecucion.do',
			data: 'numeroExpedienteId='+ numeroExpedienteId, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				
				if(parseInt(errorCode)==0){

					
					
					documentoId = $(xml).find('documentoId').text();
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitud de Estudio", type:"iframe"});
			        $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+documentoId+"&esconderArbol=1' width='1140' height='400' />");
			      
    				 				
    			}
				else{
					//Mostrar mensaje de error
				}
			}
		});		
		
	}

	//////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA DATOS GENERALES////////////////////////////////////////////////////////////////
	/**
	*Funcion que carga el grid de sentencias ******FALTA AGREGAR EL ACTION QUE LLENA EL GRID
	*/
	function cargaGridSentencias(){

		jQuery("#gridSentenciasPJOAE").jqGrid({ 
			url:'local', 
			datatype: "xml", 
			colNames:['Tipo de Sentencia','Duración','Fecha-Hora de la Sentencia'], 
			colModel:[  {name:'tipoSentencia',index:'tipoSentencia', width:80, align:'center'},						
			           	{name:'duracion',index:'duracion', width:80, align:'center'}, 
			           	{name:'fechHoraSentencia',index:'fechHoraSentencia', width:120, align:'center'},
					],
			pager: jQuery('#pagerGridSentenciasPJOAE'),
			rowNum:10,
			autoWidth:false,
			width:400,
			rowList:[10,20,30],
			sortname: 'duracion',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#gridSentenciasPJOAE',{edit:false,add:false,del:false});

		$("#gview_gridSentenciasPJOAE .ui-jqgrid-bdiv").css('height', '150px');
			
	}

	/**
	*Funcion que consulta los datos generales de la carpeta de ejecucion
	*/
	function consultarDatosGeneralesCarpeta(){
			var param='numeroExpedienteId='+numeroExpedienteId;
			param+='&documentoID='+documentoID;
			param+='&idAudiencia='+idAudiencia;
			
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarSolicitudBeneficiosPreliberacionCE.do',
				data: param, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					
	    				//limpiaDatosDetalleEvento();
	    				//Los datos de muestran en la Tab Datos generales
	    				$("#numCausaCarpetaEjecucionPJOAE").val($(xml).find('expedienteDTO').find('numeroExpediente').first().text());
	    				$("#nombreSentenciadoCarpetaEjecucionPJOAE").val($(xml).find('inputado').find('nombresDemograficoDTO').find('nombre').first().text()+" "+$(xml).find('inputado').find('nombresDemograficoDTO').find('apellidoPaterno').first().text()+" "+$(xml).find('inputado').find('nombresDemograficoDTO').find('apellidoMaterno').first().text());
	    				$("#numeroDeCarpetaEjecucionPJOAE").val($(xml).find('numeroExpediente').first().text());
						Involucradoid=$(xml).find('inputado').find('elementoId').first().text();
						numeroCarpetaE=$(xml).find('numeroExpediente').first().text();
	    				//asigna el valor a la variable numeroCausa para obtener las medidas cautelares
	    				numeroCausa = $(xml).find('expedienteDTO').find('causaPadre').find('numeroExpediente').first().text();

	    				//Falta obtener el id del involucrado
	    				//idInvolucrado =  				
	    			}
					
				
			});		
	
	}	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	//////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA ACCIONES///////////////////////////////////////////////////////////////////////
	
	/**
	*Aqui va la funcionalidad del boton Solicitar Estudio
	*/

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA DOCUMENTOS///////////////////////////////////////////////////////////////////////
	/**
	*Funcion que carga el grid de documentos relacionados
	*/
	function cargaGridDocumentosRelacionados(){
		
		jQuery("#gridDocumentosRelPJOAE").jqGrid({ 
			url:'<%=request.getContextPath()%>/documentosCarpetaPreliberacion.do?numeroExpedienteId='+numeroExpedienteId, 
			datatype: "xml", 
			colNames:['Fecha de Elaboracion','Nombre'], 
			colModel:[ 					
			           	{name:'fechaElab',index:'fechaElab', width:120, align:'center'}, 
			           	{name:'Nombre',index:'Nombre', width:120, align:'center'}, 
					],
			pager: jQuery('#pagerGridDocumentosRelPJOAE'),
			rowNum:10,
			autoWidth:false,
			width:400,
			rowList:[25,50,100],
			sortname: 'duracion',
			viewrecords: true,
			sortorder: "desc",
			ondblClickRow: function(rowid) {
				dblClickRowBandejaDocumentos(rowid);
			} 
		}).navGrid('#gridDocumentosRelPJOAE',{edit:false,add:false,del:false});

		$("#gview_gridDocumentosRelPJOAE .ui-jqgrid-bdiv").css('height', '150px');
			
	}
	
	function dblClickRowBandejaDocumentos(documentoId){
		document.frmDocumento.documentoId.value = documentoId;
		document.frmDocumento.submit();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA MEDIDAS ALTERNATIVAS///////////////////////////////////////////////////////////////////////
	/**
	*Funcion que consulta los involucrados por causa
	*/
	function cargaGridMedidasAlternativas(){

		jQuery("#gridInvolucradosCausaPJENC").jqGrid(
				{ url:'<%= request.getContextPath() %>/programarAudienciasCarpetaPreliberacion.do?numeroExpedienteId='+numeroExpedienteId,						
					datatype: "xml",
					mtype: 'POST',						
					colNames:['Nombre','Medida Alternativas', 'Descripcion','Periodo de aplicacion','Periodicidad','Encargado Seguimiento'], 
					colModel:[ 	{name:'nombre',index:'nombre', width:200}, 
								{name:'medidaCautelar',index:'medidaCautelar', width:100}, 
								{name:'descripcionMedida',index:'descripcionMedida', width:150},
								{name:'periodoAplicacion',index:'periodoAplicacion', width:150}, 
								{name:'periodicidad',index:'periodicidad', width:150},
								{name:'encargadoSeguimiento',index:'encargadoSeguimiento', width:150},
							],
						autowidth: false,
						width:924,
						height: 395,
						pager: jQuery('#paginadorInvolucradosCausa'), 
						sortname: 'nombre', 
						rownumbers: false,
						gridview: false, 
						viewrecords: true, 
						sortorder: "desc",
						ondblClickRow: function(rowid) {
							documentoIdPACP=rowid;
							mostrarVentanaMedidasAlternativas();
							
						} 
				}).navGrid('#paginadorInvolucradosCausa',{edit:false,add:false,del:false});
		$('#divGridInvolucradosCausaPJENC').show();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA SOLICITUDES DE BENEFICIOS///////////////////////////////////////////////////////////////////////
	
	/**
	*Funcion que carga el grid de solicitudes de beneficios
	*/
	function cargaGridSolicitudesDeBeneficios(){

		jQuery("#gridSolDeBeneficiosRelPJOAE").jqGrid({ 
			url:'<%=request.getContextPath()%>/gridSolicitaCarpetas.do?numeroExpedienteId='+numeroExpedienteId,
			datatype: "xml", 
			colNames:['Fecha - Hora Solicitud','Estado', 'Tipo de Solicitud'], 
			colModel:[ 					
			           	{name:'fechaHoraSolicitud',index:'fechaElab', width:100, align:'center'}, 
			           	{name:'estado',index:'estado', width:80, align:'center'},
			           	{name:'tipoSolicitud',index:'tipoSolicitud', width:80, align:'center'}
					],
			pager: jQuery('#pagerGridSolDeBeneficiosRelPJOAE'),
			rowNum:10,
			autoWidth:false,
			width:400,
			rowList:[25,50,100],
			sortname: 'duracion',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#gridSolDeBeneficiosRelPJOAE',{edit:false,add:false,del:false});

		$("#gview_gridSolDeBeneficiosRelPJOAE .ui-jqgrid-bdiv").css('height', '150px');
			
	}

	
	
	
	
	function cargaGridSolicitudesDeBeneficios2(){

		jQuery("#gridSolDeBeneficiosRelPJOAE2").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarSolicitudesDeBeneficiosDePreliberacionEstudios.do?numeroExpedienteId='+numeroExpedienteId,
			datatype: "xml", 
			colNames:['Fecha - Hora Solicitud','Estado', 'Tipo de Solicitud'], 
			colModel:[ 					
			           	{name:'fechaHoraSolicitud',index:'fechaElab', width:100, align:'center'}, 
			           	{name:'estado',index:'estado', width:80, align:'center'},
			           	{name:'tipoSolicitud',index:'tipoSolicitud', width:80, align:'center'}
					],
			pager: jQuery('#pagerGridSolDeBeneficiosRelPJOAE2'),
			rowNum:10,
			autoWidth:false,
			width:400,
			rowList:[25,50,100],
			sortname: 'duracion',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#gridSolDeBeneficiosRelPJOAE2',{edit:false,add:false,del:false});

		$("#gview_gridSolDeBeneficiosRelPJOAE2 .ui-jqgrid-bdiv").css('height', '150px');
			
	}
	
	/**
	*Funcion que carga el grid de solicitudes de beneficios
	*/
	function cargaGridSolicitudesDeBeneficios(){

		jQuery("#gridSolDeBeneficiosRelPJOAE").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarSolicitudesDeBeneficiosDePreliberacion.do?numeroExpedienteId='+numeroExpedienteId+'',
			datatype: "xml", 
			colNames:['Fecha - Hora Solicitud','Estado', 'Tipo de Solicitud'], 
			colModel:[ 					
			           	{name:'fechaHoraSolicitud',index:'fechaElab', width:100, align:'center'}, 
			           	{name:'estado',index:'estado', width:80, align:'center'},
			           	{name:'tipoSolicitud',index:'tipoSolicitud', width:80, align:'center'}
					],
			pager: jQuery('#pagerGridSolDeBeneficiosRelPJOAE'),
			rowNum:10,
			autoWidth:false,
			width:400,
			rowList:[25,50,100],
			sortname: 'duracion',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#gridSolDeBeneficiosRelPJOAE',{edit:false,add:false,del:false});

		$("#gview_gridSolDeBeneficiosRelPJOAE .ui-jqgrid-bdiv").css('height', '150px');
			
	}
	
	/*
	*Funcion que abre la ventana de medidas cautelares
	* 
	*/
	function mostrarVentanaMedidasAlternativas(){
		$.newWindow({id:"iframewindowMedidasCautelares", statusBar: true, posx:70,posy:20,width:740,height:300,title:"Ingresar/Modificar Medidas Alternativa", type:"iframe"});
    	$.updateWindowContent("iframewindowMedidasCautelares",'<iframe src="<%=request.getContextPath()%>/ingresarMedidasAlternativasPJENC.do" width="740" height="300" />'); 
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	*Funcion que obtiene el valor de la celda seleccionada en la agenda
	*/
	function seleccionCelda(idRow,dia,fecha){
		
		//verifica si la sub string inhabil se encuentra en la cadena fecha,
		//de NO ser asi devuelve -1
		if(fecha.indexOf("inhabil")==-1){
			var fechaPos1=fecha.indexOf(">", 0);
			var fechaPos2=fecha.indexOf("<", fechaPos1);
			fechaReal="";
			fechaReal=fecha.substring(fechaPos1+1, fechaPos2);
			muestraFechaSeleccionada(fechaReal);
			controlSalas(fechaReal);
			//$("#gridAgendaPJENA").jqGrid('setCell',"1","lunes","",{'font-weight': 'bold',color: 'white','text-align':'center','background-color':'red'});			
		}
		else{
			alert("Seleccione un día hábil");
		}
	}

	
	/**
	*funcion que muestra en el txtField la fecha seleccionada en el calendario
	*/
	function muestraFechaSeleccionada(){
		
		var mesDisponible = $('#mes').val();
		var anioDisponible = $('#anio').val(); 

		$('#fechaSeleccionadaAudiencia').val(fechaReal+"/"+mesDisponible+"/"+anioDisponible);
	}

	
	var numeroDeSalas=0;
	/**
	*Funcion que controla la agenda de las salas
	*/
	function controlSalas(fechaReal){
			
		
		
		if(primeraConsulta=="true"){
			
			numeroDeSalas=obtenerNumeroDeSalas();
			//numeroDeSalas=10;
			insertaTablas(numeroDeSalas);
			consultaDisponibilidadSalasPorDia(fechaReal);
			gridHorarios();			
			dibujaGrids(numeroDeSalas,fechaReal);			
			primeraConsulta="false";
			
		}
		else{
			consultaDisponibilidadSalasPorDia(fechaReal);
			
			for (i=1;i<=numeroDeSalas;i++){
				jQuery("#gridSalasPJENA"+i).jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDisponibilidadDeUnaSalaPorDia.do?posSala='+i+'',datatype: "xml" });
				$("#gridSalasPJENA"+i).trigger("reloadGrid");
			}
		}
	}

	
	/**
	*Funcion para obtener el numero de salas disponibles
	*/
	function obtenerNumeroDeSalas(){

		//var numeroDeSalas=0;
	
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarNumeroDeSalas.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('ListaSalas').find('Sala').each(function(){
					nombreSalas[numeroDeSalas] = $(this).text();
					numeroDeSalas++;
				});
			}
		});
		return numeroDeSalas;
	}

	
	/**
	*Funcion que inserta las tablas en HTML,donde
	*se dibujaran los grids de las salas
	*/
	function insertaTablas(numeroDeSalas){

		var indice;
		
		$('#gridsTd').append('<tr>');
		$('#gridsTd').append('<td><table width="100%" id="gridHorarios" border="0"></table></td>');
		for (indice=1;indice<=numeroDeSalas;indice++){
			$('#gridsTd').append('<td><table width="100%" id="gridSalasPJENA'+indice+'" border="0"></table></td>');
		}
		$('#gridsTd').append('</tr>');
	}


	/**
	*Funcion que consulta la disponibilidad de las salas por dia
	*/
	function consultaDisponibilidadSalasPorDia(fechaReal){

		var mesDisponible = $('#mes').val();
		var anioDisponible = $('#anio').val();
		
		//Recuperamos la informacion de las salas
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarDisponibilidadDeSalasPorDia.do?diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
			}
		});	
	}

	
	/**
	*Funcion que genera el grid con la lista de horarios
	*/
	function gridHorarios(){
		
		jQuery("#gridHorarios").jqGrid({
				
			url:'local',
			datatype: "xml",
			colNames:['Horario'], 
			colModel:[{name:'hora',index:'hora', width:100, align:"center"},
					],   
			pager: jQuery('#pagerHora'),
			rowNum:12,
			rowList:[10,20,30],
			autowidth: false,
			height:280,
			scrollOffset:0,
			sortname: 'hora'
		}).navGrid('#pagerHora',{edit:false,add:false,del:false});
		
		var mydata = [ 
						{hora:"09:00-09:30"},
						{hora:"09:30-10:00"},
						{hora:"10:00-10:30"},
						{hora:"10:30-11:00"},
						{hora:"11:00-11:30"},
						{hora:"11:30-12:00"},
						{hora:"12:00-12:30"},
						{hora:"12:30-13:00"},
						{hora:"13:00-13:30"},
						{hora:"13:30-14:00"},
						{hora:"14:00-14:30"},
						{hora:"14:30-15:00"},
						]; 
		for(var i=0;i<=mydata.length;i++)
			jQuery("#gridHorarios").jqGrid('addRowData',i+1,mydata[i]);

		//Quita el espacio entre grids
		$("#gbox_gridHorarios").css('width','87px');

	}


	/**
	*Funcion que dibuja los grids dentro de las tablas, por cada grid, se ejecuta una peticion
	*,para llenar cada grid se obtienen los valores de la consulta de disponibilidaDeSalasPorDia
	* y la peticion:/consultarDisponibilidadDeUnaSalaPorDia.do?posSala='+numSala+'' obtiene le 
	* informacion para cada sala en especifico
	*/	
	function dibujaGrids(numeroDeSalas,fechaReal){

		var numSala=1;

		
		//Dibujamos tantos grids como numero de salas
		for (numSala=1;numSala<=numeroDeSalas;numSala++){
			
			jQuery("#gridSalasPJENA"+numSala).jqGrid({
				 				
				url:'<%=request.getContextPath()%>/consultarDisponibilidadDeUnaSalaPorDia.do?posSala='+numSala+'',
				datatype: "xml",
				colNames:[nombreSalas[numSala-1]], 
				colModel:[
							{name:'sala',index:'sala', width:100, align:"right"},
						],   
				pager: jQuery('#pager'),
				rowNum:12,
				rowList:[10,20,30],
				autowidth: false,
				height:280,
				sortname: 'sala',
				scrollOffset:0,
				cellEdit: true,
				onSelectCell: function(idRow) {
							programaHorarioSala(idRow,fechaReal);
						},	
				loadComplete: function(){
								
					$("#ocupado1").find("td").css('border','1px solid black');
					$("#ocupado1").find("td").css('padding','0 0 0 0');
					$("#ocupado1").find("td").css('background-color','#09F');
					
				
					
					$("#ocupadoInicio").find("td").css('border-bottom','1px solid #09F');
					$("#ocupadoInicio").find("td").css('border-left','1px solid black');
					$("#ocupadoInicio").find("td").css('border-right','1px solid black');
					$("#ocupadoInicio").find("td").css('border-top','1px solid black');
					$("#ocupadoInicio").find("td").css('padding','0 0 0 0');
					$("#ocupadoInicio").find("td").css('background-color','#09F');
				
					
					$("#ocupadoIntermedio").find("td").css('border-bottom','1px solid #09F');
					$("#ocupadoIntermedio").find("td").css('border-left','1px solid black');
					$("#ocupadoIntermedio").find("td").css('border-right','1px solid black');
					$("#ocupadoIntermedio").find("td").css('border-top','1px solid #09F');
					$("#ocupadoIntermedio").find("td").css('padding','0 0 0 0');
					$("#ocupadoIntermedio").find("td").css('background-color','#09F');
					
					
					$("#ocupadoUltimo").find("td").css('border-bottom','1px solid black');
					$("#ocupadoUltimo").find("td").css('border-left','1px solid black');
					$("#ocupadoUltimo").find("td").css('border-right','1px solid black');
					$("#ocupadoUltimo").find("td").css('border-top','1px solid #09F');
					$("#ocupadoUltimo").find("td").css('padding','0 0 0 0');
					$("#ocupadoUltimo").find("td").css('background-color','#09F');				
				}				
			}).navGrid('#pager',{edit:false,add:false,del:false});

			//Quita el espacio entre grids
			$("#gbox_gridSalasPJENA"+numSala).css('width','87px');
		}
	}
/////////////////////TERMINA FUNCIONALIDAD PARA LOS GIRDS DINAMICOS DE SALAS //////////////////////////////////////////////////


/////////////////////COMIENZA FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA //////////////////////////////////////////////////
	/**
	*Funcion que controla el horario y la sala seleccionada, descompone la string
	*para obtener los parametros del id de la sala, la hora de inicio de cada espacio
	*y el numero de medias hrs. disponibles para ese espacio libre
	*/
	
	function programaHorarioSala(idRow,fechaReal){

		
		//verifica si la sub string ocupado se encuentra en la cadena fecha
		//de NO ser asi, devulve -1
		if(idRow.indexOf("ocupado")==-1){
			
			//id de la sala
			var idSalaPos1=idRow.indexOf("+", 0);
			idSalaSeleccionada=idRow.substring(0,idSalaPos1);		

			//hora de inicio del espacio
			var horaInicioPos1=idRow.indexOf("+",0);
			var horaInicioPos2=idRow.indexOf("/",horaInicioPos1);
			var horaInicio=idRow.substring(horaInicioPos1+1,horaInicioPos2);
			

			//tamaño en medias hrs. del espacio disponible
			var espacioPos1=idRow.indexOf("/",0);
			var espacioPos2=idRow.indexOf("*",espacioPos2);
			var espacioDisponible=idRow.substring(espacioPos1+1,espacioPos2);
			

			//hora de inicio de la celda seleccionada
			var horaSeleccionadaPos1=idRow.indexOf("*",0);
			var horaSeleccionadaPos2=idRow.indexOf(":",horaSeleccionadaPos1);
			horaSeleccionada=idRow.substring(horaSeleccionadaPos1+1,horaSeleccionadaPos2);
			

			//Minuto de inicio de la celda seleccionada
			var minutoSeleccionadoPos1=idRow.indexOf(":",0);
			var minutoSeleccionadoPos2=idRow.indexOf("#",minutoSeleccionadoPos1);
			minutoSeleccionado=idRow.substring(minutoSeleccionadoPos1+1,minutoSeleccionadoPos2);
			
			
			var disponibilidad=parseInt("espacioDisponible");
			duracionEstimadaAudiencia = $("#duracionEstimadaProgramarAudiencia option:selected").val();
			
			
			if(duracionEstimadaAudiencia > 0 ){
				if((duracionEstimadaAudiencia/30) <= espacioDisponible){
					muestraHoraSalaSeleccionada();		
					deshabilitarHabilitarComponentes("horarioSala");
				}
				else{
					alert("La duración estimada es mayor que el tiempo disponible para esta sala.\rIntente con otra sala");	
				}	
			}
			else{
				
				alert("Seleccione una duración estimada para la audiencia");
			}
				
		}
		else{
			alert("La sala se encuentra reservada en ese horario");
		}
	}


	/**
	*funcion que muestra en el txtField correspondiente el id de la sala seleccionada
	*y la hora de inicio seleccionada
	*/
	function muestraHoraSalaSeleccionada(){

		$('#txtSalaSeleccionada').val(idSalaSeleccionada);
		$('#txtHoraInicioSeleccionada').val(horaSeleccionada+":"+minutoSeleccionado);
	}

	
	/**
	*Funcion que sonsulta la lista de jueces disponibles en base a la 
	*hora y la fecha de la audiencia
	*/
	function controlJueces(automatico){

		var mesDisponible = $('#mes').val();
		var anioDisponible = $('#anio').val();
		duracionEstimadaAudiencia = $("#duracionEstimadaProgramarAudiencia option:selected").val();
		juezSustituto =  $("#juezSustituto").val();
		if(duracionEstimadaAudiencia == "" || duracionEstimadaAudiencia <= 0){
			alert("Seleccione una duración estimada para la audiencia");
			return false;
		}
				
		
		if(primeraConsultaJueces=="true"){
			
			//Se llena el gird de jueces
			jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid({
				url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudiencia+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimadaAudiencia+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia, 
				datatype: "xml", 
				colNames:['Nombre','Carga de trabajo','Paridad',], 
				colModel:[ 	{name:'nombre',index:'nombre', width:100, align:"center"},
				           	{name:'carga',index:'carga', width:150, align:"center"},  
							{name:'paridad',index:'paridad', width:50, align:"center"},
							
						],
				pager: jQuery('#pagerGridJueces'),
				rowNum:10,
				loadComplete: function(){
					if(automatico== true)
						//Deshabilita el multi select
						jQuery("#gridSolicitudDeAudienciaJuecesPJENA").setGridParam({multiselect:false}).hideCol('cb');
					}, 				
				rowList:[25,50,100],
				autowidth: false,
				sortname: 'carga',
				viewrecords: true,
				sortorder: "desc",
				multiselect: true
			}).navGrid('#gridSolicitudDeAudienciaJuecesPJENA',{edit:false,add:false,del:false});

			primeraConsultaJueces="false";
			deshabilitarHabilitarComponentes("juez");
			verificarTipoAudiencia(automatico);
		}
		else{
			jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudiencia+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimada+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia,datatype: "xml" });
			$("#gridSolicitudDeAudienciaJuecesPJENA").trigger("reloadGrid");
			deshabilitarHabilitarComponentes("juez");
			verificarTipoAudiencia(automatico);
		}	
	}
/////////////////////TERMINAN FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA //////////////////////////////////////////////////

	function verificarTipoAudiencia(automatico){

			//Si pulso automáticamente
			if(automatico == true){
				//jQuery("#gridSolicitudDeAudienciaJuecesPJENA").setGridParam({multiselect:false}).hideCol('cb');
				$("#gridSolicitudDeAudienciaJuecesPJENA").unbind('click');
				multiselect=0;
			}
			//Si pulso manualmente
			else{
		
				if(tipoAudiencia=="Juicio Oral" || tipoAudiencia=="jucio oral" || tipoAudiencia == "Juicio oral" || tipoAudiencia == "juicio Oral"){
				 	if($("#juezSustituto").is(':checked')){
						alert("Por favor seleccione 4 jueces del listado");
						multiselect=4;
					}else{
						alert("Por favor seleccione 3 jueces del listado");
					 	multiselect=3;
					}
				}else{
					alert("Por favor seleccione un juez del listado");
					multiselect=1;
				}
			}
			
		}
///////////////////////////////////////CONTROLA LOS EVENTOS HABILITA O INHABILITA COMPONENETES//////////////////////////////////////////////////////////
	/**
	*Funcion para deshabilitar los botones de designar 
	*Juez manualmente y automáticamente, Guardar y Designar Sala Temporal
	*/
	function deshabilitarHabilitarComponentes(accion){

		if(accion == "inicio"){
				
			$('#btnAsignarJuezManual').attr('disabled',true);
			$('#btnAsignarJuezAuto').attr('disabled',true);
			$('#btnGuardarAudiencia').attr('disabled',true);
			$('#btnDesignar').attr('disabled',true);	
			$('#duracionEstimadaProgramarAudiencia').attr('disabled', 'disabled');
		}
		if(accion == "horarioSala"){
			$('#btnAsignarJuezManual').attr('disabled',false);
			$('#btnAsignarJuezAuto').attr('disabled',false);
			//$('txtSalaSeleccionada').attr('disabled',false);
		}
		
		if(accion == "seleccionFecha"){
			$('#btnDesignar').attr('disabled',false);
			$('#btnAsignarJuezManual').attr('disabled',true);
			$('#btnAsignarJuezAuto').attr('disabled',true);
			$('#btnGuardarAudiencia').attr('disabled',true);
			$('#duracionEstimadaProgramarAudiencia').removeAttr('disabled');
		}

		if(accion == "designarSala"){
			$('#btnDesignar').attr('disabled',true);
			$('#btnAsignarJuezManual').attr('disabled',false);
			$('#btnAsignarJuezAuto').attr('disabled',false);	
			for (i=1;i<=numeroDeSalas;i++){
				$('#gridSalasPJENA'+i).unbind('click');
			}
			$('#duracionEstimadaProgramarAudiencia').attr('disabled', 'disabled');
		}
		if(accion == "juez"){
			$('#btnAsignarJuezManual').attr('disabled',true);
			$('#btnAsignarJuezAuto').attr('disabled',true);
			$('#btnDesignar').attr('disabled',true);
			$("#gridAgendaPJENA").unbind('click');
			$('#duracionEstimadaProgramarAudiencia').attr('disabled', 'disabled');
			$('#btnAtrasMes').attr('disabled',true);
			$('#btnAdelanteMes').attr('disabled',true);
			$('#juezSustituto').attr('disabled',true);
			for (i=1;i<=numeroDeSalas;i++){
				$('#gridSalasPJENA'+i).unbind('click');
			}
			$('#btnGuardarAudiencia').attr('disabled',false);	
		}
	}
////////////////////////////////////////////////////FIN CONTROL HABILITA DESHABILITA//////////////////////////////////////////////////


///////////////////////////////////////////////COMIENZA FUNCIONALIDAD SALA TEMPORAL //////////////////////////////////////////////////
	function asignarSalaTemporal(recuperaDatosAsignacionTemporal){
	
		datosSalaTemporal = recuperaDatosAsignacionTemporal;
		salaTemporal = "true";
		
		hrSelecTemp = datosSalaTemporal.split("=")[4];
		var hrSeleccionadaPos1=hrSelecTemp.indexOf("&", 0);
		hora=hrSelecTemp.substring(0,hrSeleccionadaPos1);		
		minuto = datosSalaTemporal.split("=")[5];

		horaSeleccionada="";
		horaSeleccionada= hora;
		minutoSeleccionado="";
		minutoSeleccionado= minuto;
		cerrarVentanaAsignacionTemporal();
		
		$('#txtSalaSeleccionada').val(datosSalaTemporal.split("=")[2].split("&")[0]);
		$('#txtHoraInicioSeleccionada').val(horaSeleccionada+":"+minutoSeleccionado);
		
		
		deshabilitarHabilitarComponentes("designarSala");
		$('#duracionEstimadaProgramarAudiencia').removeAttr('disabled');
		
	}
	
	function mostrarAsignarSalaTemporalPJENA() {
		$.newWindow({id:"iframewindowAsignarSalaTemporalAudiencia" , statusBar: true, posx:250,posy:40,width:650,height:200,title:"Designaci&oacute;n de Sala Temporal de Audiencia", type:"iframe"});
	    $.updateWindowContent("iframewindowAsignarSalaTemporalAudiencia" ,'<iframe src="<%= request.getContextPath() %>/asignarSalaTemporalPJENA.do?idEvento=idAudiencia" width="650" height="200" />');			
	}
	function cerrarVentanaAsignacionTemporal(){
		$.closeWindow('iframewindowAsignarSalaTemporalAudiencia');
	}
///////////////////////////////////////////////TERMINA FUNCIONALIDAD SALA TEMPORAL //////////////////////////////////////////////////



///////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////	
	/**
	*Funcion que obtiene el valor del desplazamiento para los meses "atras" o "adelante",
	*pasa el valor al action para que este ejecute el movimiento y recarga el grid de 
	*agenda 
	*/
	function atrasAdelanteMes(movimiento){
		recorridoMeses(movimiento);
		jQuery("#gridAgendaPJENA").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/calendarioActual.do?mov='+ movimiento +'',datatype: "xml" });
		$("#gridAgendaPJENA").trigger("reloadGrid");
	}

	/**
	*Funcion que recorre el carrusel de los mese y anio si la bandera de cambio se encuentra encendida
	*los meses no se recorran, hasta que el grid sea refrescado y baje la bandera 
	*/
	function recorridoMeses(movimiento){
		
		if(banderaCambio==0){
			if(movimiento=="atras"){
				
				if(mesActual == 0){
					mesActual=11;
					if(anioActual == 0)
						anioActual=3000;
					else
						anioActual--;
				}
					
				else
					mesActual--;
			}
			else{
				if(mesActual == 11){
					mesActual=0;
					anioActual++;
				}
				else
					mesActual++;
			}
			var mesSeleccionado = meses[mesActual];

			//setea los valor de mes y anio a la caja de texto
			$('#mes').val(mesSeleccionado);
			$('#anio').val(anioActual);
			//levanta la bandera de cambio
			banderaCambio=1;
		}	
	}
///////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////

	/**
	*Funcion que consulta el detalle de la solicitud de audiencia
	*/
	function cargaComboTipoAudiencia(tipoAudiencia) {

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTipoAudiencia.do',
			data: '',
			async: false,
			dataType: 'xml',
			success: function(xml){
				$('#tipoAudienciaProgramarAudiencia').empty();
				$('#tipoAudienciaProgramarAudiencia').append('<option value="0">- Seleccione -</option>');
				var tipoAud = "";
				$(xml).find('institucion').each(function(){
					var tipoAud = $(this).find('clave').text();
					if(tipoAud != tipoAudiencia){
						$('#tipoAudienciaProgramarAudiencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					}
				});
			}
		});
	}

	/**
	*Funcion para el guardado de la audiencia
	*/
	function guardarAudiencia(){
		
		var valor = jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('getGridParam','selarrrow').length;

		tipoDeAudienciaSelecc=2017;

		//Se verifica que se seleccione un tipo de audiencia
		if(tipoDeAudienciaSelecc != "0"){
			if(valor == multiselect){
				var parametrosGuardado='';
				  
				parametrosGuardado += 'idAudiencia='+ idAudiencia;
				parametrosGuardado += '&anioEvento=' + $('#anio').val();
				parametrosGuardado += '&mesEvento=' + $('#mes').val();
				parametrosGuardado += '&diaEvento=' + fechaReal;
				parametrosGuardado += '&horaEvento=' + horaSeleccionada;
				parametrosGuardado += '&minutoEvento=' + minutoSeleccionado;
				parametrosGuardado += '&duracionEstimada=' + duracionEstimadaAudiencia;
				parametrosGuardado += '&idSala=' + idSalaSeleccionada;
				parametrosGuardado += '&salaTemporal=' + salaTemporal;
				parametrosGuardado += '&tipoDeAudienciaSeleccionada='+tipoDeAudienciaSelecc;
				//Si la asignacion de jueces fue automatica
				if(multiselect == 0){
					parametrosGuardado += '&idJueces=' + jQuery("#gridSolicitudDeAudienciaJuecesPJENA").getDataIDs();
				}
				else{
					parametrosGuardado += '&idJueces=' + jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('getGridParam','selarrrow');	
				}
				parametrosGuardado += '&'+datosSalaTemporal;
				
				$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/guardarAudiencia.do',
					data: parametrosGuardado,
					dataType: 'xml',
					async: false,
					success: function(xml){
						var mensaje = $(xml).find('body').find('fechaActual').text();					

						if(mensaje == "fail"){
							alertDinamico("La audiencia ya fue programada anteriormente por algún usuario");						
						} else if(mensaje == "salaOcupada"){
							alertDinamico("Otro usuario acaba de programar una audiencia en esta sala y horario");
						} else {
							alertDinamico("La programación de la audiencia se realizó de manera correcta");
							/*
							*recargamos el grid de audiencias para que la audiencia que se acaba de
							*programar, no se muestre mas 
							*/
								//parent.recargarGridAudienciaPJENA();
							//deshabilitamos el boton de guardado
							$('#btnGuardarAudiencia').attr('disabled',true);
							//Refrescamos el grid de las salas
							controlSalas(fechaReal);
						}
					}
				});			
			}else{
				if(multiselect==1){
					alert('Seleccione únicamente un juez del listado');
				}
				else{
					alert('Seleccione únicamente '+multiselect+' jueces del listado');
				}
			}
		}
		else{
			alert('Seleccione un tipo de audiencia a programar');
		}

		parent.actualizaGridsPadre();
		cambiaEstatus();
	}	
function cambiaEstatus(){
	var param="documentoID="+documentoID;
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/actualizaEstatusSolicitudAudiencia.do',
		data: param,
		async: false,
		dataType: 'xml',
		success: function(xml){
			
			
			
		}
	});
}

/**
*Funcion que carga el grid Objetos
*/
function cargaGridObjetos(){

	jQuery("#gridSolObjetos").jqGrid({ 
		url:'<%=request.getContextPath()%>/gridConsultaObjetos.do?audienciaId='+idAudiencia,
		datatype: "xml", 
		colNames:['Otorgante','No. Prueba', 'Cadena de Custodia', 'Descripción Objeto', 'Aceptado'], 
		colModel:[ 					
		           	{name:'Otorgante',index:'Otorgante', width:100, align:'center'}, 
		           	{name:'Prueba',index:'Prueba', width:80, align:'center'},
		           	{name:'Cadena',index:'Cadena', width:80, align:'center'},
		        	{name:'Objeto',index:'Objeto', width:100, align:'center'}, 
		           	{name:'Aceptado',index:'Aceptado', width:80, align:'center'},
				],
		pager: jQuery('#pagerGridSolObjetos'),
		rowNum:10,
		autoWidth:false,
		width:400,
		rowList:[25,50,100],
		sortname: 'duracion',
		viewrecords: true,
		sortorder: "desc"
	}).navGrid('#gridSolObjetos',{edit:false,add:false,del:false});

	$("#gview_gridSolObjetos .ui-jqgrid-bdiv").css('height', '150px');
		
}

function guardaObjeto(){
	 
	   
	
	var param="idAudiencia="+idAudiencia;
	param+="&campoObjeto="+$("#campoObjeto").val();
	param+="&campoPrueba="+$("#campoPrueba").val();
	param+="&campoCadena="+$("#campoCadena").val();
	param+="&campoDescripciona="+$("#campoDescripciona").val();
	param+="&campoEstado="+$("#campoEstado option:selected").val();
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/guardaObjetosCarpetaEjecucion.do',
		data: param,
		async: false,
		dataType: 'xml',
		success: function(xml){
			
			
			
		}
	});
	$("#gridSolObjetos").trigger("reloadGrid");
}


function cargaCondicion() {
	$.ajax({
		async: false,
		type: 'POST',
		url: '<%= request.getContextPath()%>/consultarCondicion.do',
		data: '',
		dataType: 'xml',
		success: function(xml){
			$(xml).find('catCondicion').each(function(){
				$('#campoEstado').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
			});
		}
	});
}

	</script>
</head>


<body>
<form name="frmDocumento" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="documentoId" />
</form>
	<div id="tabsprincipalconsulta">
		<ul>

			<li><a href="#tabsconsultaprincipal-1">Detalle</a>
			</li>
			<li style="display: none;"><a href="#tabsconsultaprincipal-3" onclick="cargaGridDocumentosRelacionados()">Documentos</a>
			</li>
			<li><a href="#tabsconsultaprincipal-5" onclick="cargaGridMedidasAlternativas()">Medidas Alternativas</a>
			</li>
			<li><a href="#tabsconsultaprincipal-6" onclick="cargaGridSolicitudesDeBeneficios()">Solicitudes Beneficios de Preliberación</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2" onclick="cargaGridSolicitudesDeBeneficios2()">Actuaciones</a>
			</li>			
			
			<!-- id="progAudienciaLi" -->
			<li id="idpestaniaProgramarAudiencia"><a href="#tabsconsultaprincipal-4">Programar Audiencia</a>
			<li><a href="#tabsconsultaprincipal-5" onclick="cargaGridMedidasAlternativas()">Medidas Alternativas</a>
			</li>
			<li><a href="#tabsconsultaprincipal-7" onclick="cargaGridObjetos()">Pruebas</a>
			</li>
		</ul>
		
		<!--Comienza tab datos generales-->
		<div id="tabsconsultaprincipal-1" align="left">
		
			<!--
<table width="1005" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td width="26%" align="right" ><strong>Número de causa:</strong>
					</td>
					<td width="24%"><input type="text"
						id="numCausaCarpetaEjecucionPJOAE"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td width="21%"><strong>Sentencias:</strong>
					</td>
					<td width="29%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>Nombre completo:</strong>
					</td>
					<td><input type="text" id="nombreSentenciadoCarpetaEjecucionPJOAE"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td colspan="2" rowspan="8">
                    	<table id="gridSentenciasPJOAE"></table>
						<div id="pagerGridSentenciasPJOAE" style="width: 300"></div>
					</td>
				</tr>
				<tr>
					<td align="right"><strong>N&uacute;mero de carpeta de ejecuci&oacute;n:</strong></td>
					<td><input type="text" id="numeroDeCarpetaEjecucionPJOAE"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>-->
            <table width="60%" border="0">
              <tr>
                <td width="25%" height="25" align="right" ><strong>Número de Causa:</strong></td>
                <td width="26%"><input type="text"
						id="numCausaCarpetaEjecucionPJOAE2"
						style="width: 170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
                <td width="28%" align="right"><strong>Tipo de Sentencia:</strong></td>
                <td width="21%"><input type="text" id="tipoSentenciaCEPJOAE3"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
              </tr>
              <tr>
                <td align="right" ><strong>Número de Caso:</strong></td>
                <td><input type="text"
						id="numCausaCarpetaEjecucionPJOAE2"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
                <td align="right"><strong>Duracion de Fecha Inicio:</strong></td>
                <td><input type="text" id="fechaInicioCEPJOAE"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
              </tr>
              <tr>
                <td align="right"><strong>N&uacute;mero de Carpeta de Ejecuci&oacute;n:</strong></td>
                <td><input type="text" id="numeroDeCarpetaEjecucionPJOAE2"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
                <td align="right"><strong>Duracion de Sentencia:</strong></td>
                <td><input type="text" id="duracionSentenciaCEPJOAE2"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
              </tr>
  <tr>
    <td align="right"><strong>Sentencia</strong></td>
    <td><input type="text" id="sentenciaCAPJOAE3"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
    <td align="right"><strong>Nombre Completo:</strong></td>
    <td><input type="text" id="nombreSentenciadoCarpetaEjecucionPJOAE2"
						style="width:  170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
    </tr>
</table>
				
		</div>
		<!--Termina tab datos generales-->
		
		<!--Comienza tab acciones-->
		<div id="tabsconsultaprincipal-2" align="left">
			<table width="1000px" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td width="26%" align="left">
                    	<input type="submit" id="solicitarEstudioPJOAE" value="Solicitar Estudio"/></td>
					<td width="24%">&nbsp;</td>
					<td width="21%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
	  			</tr>
				<tr>
					<td align="left"><input type="submit" id="solicitarDocumentoCasoPJOAE" value="Solicitar Documento del Caso"/></td></td>
					<td>&nbsp;</td>
					<td colspan="2" rowspan="8"></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			
			</table>
		 	<table id="gridSolDeBeneficiosRelPJOAE2"></table>
					<div id="pagerGridSolDeBeneficiosRelPJOAE2" style="width: 300"></div>
			
																
		</div>
		<!--Termina tab acciones-->
		
		<!--Comienza tab documentos-->
		<div id="tabsconsultaprincipal-3" align="left">
			
			<table width="1000" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td colspan="4">&nbsp;</td>
			  </tr>
			  <tr>
			    <td width="196">&nbsp;</td>
			    <td width="174">&nbsp;</td>
			    <td width="285">&nbsp;</td>
			    <td width="335">&nbsp;</td>
			  </tr>
			  <tr>
			    <td colspan="2" rowspan="8">
			    	<table id="gridDocumentosRelPJOAE"></table>
					<div id="pagerGridDocumentosRelPJOAE" style="width: 300"></div>
			    </td>
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
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			
		</div>
		<!--Termina tab documento-->
	
		<!--Comienza tab programar audiencia-->
		<div id="tabsconsultaprincipal-4" align="left">

					<table width="1100" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="258">&nbsp;</td>
			    <td width="178">&nbsp;</td>
			   
			    <td colspan="3" align="center" valign="bottom"><input type="button" value="<<" id="btnAtrasMes" onClick="atrasAdelanteMes('atras');" class="btn_Generico"/>
			      <input type="text" id="mes" disabled="disabled" style="width: 70px"/>
			      <input type="text" id="anio" disabled="disabled" style="width: 70px" />
			    <input type="button" value=">>" id="btnAdelanteMes" onClick="atrasAdelanteMes('adelante'); class="btn_Generico""/></td>
			    <td width="100" nowrap="nowrap"><strong>Asignar Juez:</strong> 
			    	
			    </td>
			    <td colspan="2">
			    	
			    	<table width="100%" cellpadding="1" cellspacing="1">
			    		<tr>
			    			<td colspan="2">
			    				<input type="checkbox" value="true" name="juezSustituto" id="juezSustituto"/> Juez sustituto
			    			</td> 
			    		</tr>
			    		<tr>
			    			<td width="111"><input type="button" value="Manualmente" id="btnAsignarJuezManual" onclick="controlJueces(false);" class="btn_Generico"/></td>
			    			<td width="125"><input type="button" value="Autom&aacute;ticamente" id="btnAsignarJuezAuto" onclick="controlJueces(true);" class="btn_Generico"/></td>
			    		</tr>
			    	</table>
			    
			    </td>
			    
			  </tr>
			  <tr>
			    <td align="right" valign="bottom" ><strong></strong></td>
			    <td>
					<!--<input type="text" id="tipoAudienciaProgramarAudiencia" style="width: 170px; border: 0; background: #DDD;" readonly="readonly" />-->
					
			    </td>
			    <td colspan="3" rowspan="7" align="center" valign="bottom"><table id="gridAgendaPJENA"></table></td>
			    <td colspan="3" rowspan="7"><table id="gridSolicitudDeAudienciaJuecesPJENA"></table></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong></strong></td>
			    <td align="left" valign="bottom"></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Tipo de Audiencia</strong></td>
			    <td align="left" valign="bottom">Ejecución</td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Fecha l&iacute;mite de audiencia:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="fechaLimiteProgramarAudiencia"
			                        style="width: 170px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Fecha Seleccionada:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="fechaSeleccionadaAudiencia"
			                        style="width: 170px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Duración estimada de
			    audiencia:</strong></td>
			    <td align="left" valign="bottom">
			     <select id="duracionEstimadaProgramarAudiencia" style="width:170px;">
                	<option value="0" >-Seleccione-</option>
                	<option value="30">30 min.</option>
                	<option value="60">60 min.</option>
                	<option value="90">90 min.</option>
                	<option value="120">120 min.</option>
                	<option value="150">150 min.</option>
                	<option value="180">180 min.</option>
                	<option value="210">210 min.</option>
                	<option value="240">240 min.</option>
                	<option value="270">270 min.</option>
                	<option value="300">300 min.</option>
                	<option value="330">330 min.</option>
                	<option value="360">360 min.</option>
            	</select>
			    <!--<input type="text" id="duracionEstimadaProgramarAudiencia" style="width: 170px; border: 0; background: #DDD;" readonly="readonly" />-->
			     </td>
			  </tr>
			  <tr>
			    <td align="right"><strong>Sala seleccionada:</strong></td>
			    <td><input type="text"
			                        id="txtSalaSeleccionada"
			                        style="width: 170px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td height="30" align="right"><strong>Hora de inicio:</strong></td>
			    <td><input type="text"
			                        id="txtHoraInicioSeleccionada"
			                        style="width: 170px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			    <td width="102">
			    	<strong><input type="text" size="4" style="width: 20px; border: 0; background: #669933;" readonly />
			    	</strong>Disponible </td>
			    <td width="123">
			    	<strong><input type="text" size="4" style="width: 20px; border: 0; background: red;" readonly />
			   		</strong>No Disponible
			    </td>
			    <td width="85">
			    	<input type="text" size="4" style="width: 20px; border: 0; background: #CCCCCC;" readonly />
			    	Inhabil </td>
			    <td colspan="3">&nbsp;</td>
			  </tr>
			  <tr>
			    <td colspan="8"><table id="gridsTd" width="100px"></table></td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td colspan="3">&nbsp;</td>
			    <td colspan="3">&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td colspan="3" align="center">
					<input type="button" id="btnGuardarAudiencia" value="Guardar"  onclick="guardarAudiencia();" class="btn_Generico"/>
			    </td>
			    <td colspan="3" align="left"><input type="button" id="btnDesignar" onclick="mostrarAsignarSalaTemporalPJENA();" value="Designar Sala Temporal" style="display: none;" class="btn_Generico"/></td>
			  </tr>
			</table>
			</br>
				</br>
				
		</div>
		
		<!--Termina tab programar audiencia-->

		<!--Comienza tab medidas alternativas		-->
		<div id="tabsconsultaprincipal-5" align="left">
			<table>
				<tr>
					<td>
						<div id="divGridInvolucradosCausaPJENC">
							<table id="gridInvolucradosCausaPJENC"></table>
							<div id="paginadorInvolucradosCausa"></div>
							<input type="button" value="Generar Medida Alterna" onclick="mostrarVentanaMedidasAlternativas()" class="btn_Generico"/>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!--Termina tab medidas alternativas-->
		
		<!--Comienza tab solicitud de beneficios de preliberacion-->
		<div id="tabsconsultaprincipal-6" align="left">

			<table width="1000" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td colspan="4">&nbsp;</td>
			  </tr>
			  <tr>
			    <td width="196">&nbsp;</td>
			    <td width="174">&nbsp;</td>
			    <td width="285">&nbsp;</td>
			    <td width="335">&nbsp;</td>
			  </tr>
			  <tr>
			    <td colspan="2" rowspan="8">
			    	<table id="gridSolDeBeneficiosRelPJOAE"></table>
					<div id="pagerGridSolDeBeneficiosRelPJOAE" style="width: 300"></div>
			    </td>
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
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			
		</div>
		
		<div id="tabsconsultaprincipal-7">
		<table><tr><td><table width="200" border="0">
  <tr>
    <td>Prueba</td>
    <td><input name="" type="text" id="campoObjeto"/></td>
  </tr>
  <tr>
    <td>No. Prueba</td>
    <td><input name="" type="text" id="campoPrueba"/></td>
  </tr>
  <tr>
    <td>No. Cadena de Custodia</td>
    <td><input name="" type="text" id="campoCadena"/></td>
  </tr>
  <tr>
    <td>Descripcion Objeto</td>
    <td><input name="" type="text" id="campoDescripciona"/></td>
  </tr>
  
  <tr>
    <td>Estado</td>
    <td><select id="campoEstado"> </select> </td>
  </tr>
  
</table>
<input name="" type="button" value="Guardar Objeto" onclick="guardaObjeto()" class="btn_Generico"/></td><td><table id="gridSolObjetos"></table>
					<div id="pagerGridSolObjetos" style="width: 300"></div></td></tr> </table>
		
		</div>
		<!--Termina tab solicitud de beneficios de preliberacion-->
		

	</div>
	<!--Termina tabs principales-->
	
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
	
</body>
</html>