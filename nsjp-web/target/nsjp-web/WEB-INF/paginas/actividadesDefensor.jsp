<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Actividades</title>
		
	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />

<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 770px; } 
	.tabs-bottom { position: relative; } 
	
	.tabs-bottom .ui-tabs-panel { height: auto; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	
	<script type="text/javascript">

	/**
	* Variables globales para controlar el calendario de actividades
	*/
	
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
	//id de la audiencia
	var idAudiencia;
	//guarda el tipo de audiencia
	var tipoAudiencia;
	var fechaReal="";
	//variables para la consulta de jueces
	var horaSeleccionada="";
	var minutoSeleccionado="";
	var duracionEstimadaAudiencia="";
	var idSalaSeleccionada="";
	
	var idAudienciaSiguiente=0;
	/**
	* Variables globales para controlar los datos 
	*/
	var lstResolutivos = new Array();
	var idResolutivo = 0;
	var idEvento = 0;
	var fechaGlob;
	var tipoAudGlob;
	var ubicacionGlob;
	var direccionGlob;

	//variable que almacena el nombre de las salas
	var nombreSalas=new Array();

	var primeraFiscal=true;

	var numExpediente = parent.numExpediente;
	
	//inicia onready
	$(document).ready(function() {

		//obtenemos la fecha actual
		mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
		anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';
		var mesSeleccionado = meses[mesActual];		
			
		//escrbimos en pantalla la fecha actual
		$('#mes').val(mesSeleccionado);
		$('#anio').val(anioActual);
		
		//Le da el estilo a los combo box de duracion estimada y actividades
		$("#duracionEstimadaProgramarAudiencia, #actividadesDefensor").multiselect({ 
			multiple: false, 
			header: "Seleccione", 
			position: { 
				my: 'top', 
				at: 'top' 
			},
			selectedList: 1 
		});

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
			           	{name:'domingo',index:'domingo', width:40, align:'center'} 
										
					],
			rowNum:10,
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
		
		//tabs Principales	
		$("#tabsPrincipales").tabs();

		//inhabilita componentes al inicio de pagina
		deshabilitarHabilitarComponentes("inicio");

	//Fin Onready							
	});
		
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

			//numeroDeSalas=0;
			
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
						alertDinamico(("La duración estimada es mayor que el tiempo disponible para esta sala.\rIntente con otra sala");	
					}	
				}
				else{
					
					alertDinamico("Seleccione una duración estimada para la audiencia");
				}
					
			}
			else{
				alertDinamico("La sala se encuentra reservada en ese horario");
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
				alertDinamico("Seleccione una duración estimada para la audiencia");
				return false;
			}
					
			
			if(primeraConsultaJueces=="true"){
				
				//Se llena el gird de jueces
				jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid({
					url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudienciaSiguiente+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimadaAudiencia+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia, 
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
					rowList:[10,20,30],
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
				jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudienciaSiguiente+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimada+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia,datatype: "xml" });
				$("#gridSolicitudDeAudienciaJuecesPJENA").trigger("reloadGrid");
				deshabilitarHabilitarComponentes("juez");
				verificarTipoAudiencia(automatico);
			}
			
		}
	/////////////////////TERMINAN FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA //////////////////////////////////////////////////

	/////////////////////COMIENZAN FUNCIONES COMUNES //////////////////////////////////////////////////
	
		/**
		*Funcion para deshabilitar o habilitar los componentes de la pagina
		*/
		function deshabilitarHabilitarComponentes(accion){

			if(accion == "inicio"){
				
				$('#btnRegistrarActividad').attr('disabled',true);
				$('#duracionEstimadaProgramarAudiencia').multiselect('disable');
				$('#actividadesDefensor').multiselect('disable');
			}
						
			if(accion == "seleccionFecha"){
				$('#btnRegistrarActividad').attr('disabled',false);
				$('#duracionEstimadaProgramarAudiencia').multiselect('enable');
				$('#actividadesDefensor').multiselect('enable');
			}
			
			if(accion == "juez"){
				
				$("#gridAgendaPJENA").unbind('click');
				$('#duracionEstimadaProgramarAudiencia').multiselect('disable');
				$('#btnAtrasMes').attr('disabled',true);
				$('#btnAdelanteMes').attr('disabled',true);
				
				for (i=1;i<=numeroDeSalas;i++){
					$('#gridSalasPJENA'+i).unbind('click');
				}
					
			}
		}

	/////////////////////TEMRINAN FUNCIONES COMUNES //////////////////////////////////////////////////
		
	
	/////////////////////COMIENZA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////	
		
		/**
		*funcion que muestra en el txtField la fecha seleccionada en el calendario
		*/
		function muestraFechaSeleccionada(){

			var contadorMeses = 0;
			var mesDisponible = $('#mes').val();
			var anioDisponible = $('#anio').val(); 
					
			while(mesDisponible != meses[contadorMeses]){
				contadorMeses++;	
			}

			var diaDisponible;
			if(fechaReal < 10){
				diaDisponible= "0"+fechaReal;  
			}
			else{
				diaDisponible=fechaReal;
			}
			contadorMeses++;
			if((contadorMeses) < 10){
				//$('#fechaSeleccionadaAudiencia').val(fechaReal+"/"+"0"+contadorMeses+"/"+anioDisponible);
				contadorMeses = "0"+contadorMeses;
			}
			$('#fechaSeleccionadaAudiencia').val(diaDisponible+"/"+contadorMeses+"/"+anioDisponible);
		}
		
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
		*Funcion que recorre el carrusel de los meses y anio si la bandera de cambio se encuentra encendida
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
				}
			else{
				alertDinamico("Seleccione un día hábil");
			}
		}

	
	/////////////////////TERMINA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////	
		
				
</script>
</head>
<body>
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
<div id="tabsPrincipales">
		<ul>					
			<li id=calendario><a href="#tabsPrincipales-1">Actividades del defensor</a></li>
		</ul>

		<div id="tabsPrincipales-1">
		<table width="700" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" rowspan="10">&nbsp;				    </td>
					<td colspan="3" align="center" valign="bottom"><input
						type="button" value="<<" id="
						btnAtrasMes" onClick="atrasAdelanteMes('atras');" /> <input
						type="text" id="mes" disabled="disabled" style="width: 70px" /> <input
						type="text" id="anio" disabled="disabled" style="width: 70px" />
						<input type="button" value=">>" id="btnAdelanteMes"
						onClick="atrasAdelanteMes('adelante');" class="btn_Generico"/>
					</td>
					<td width="181" nowrap="nowrap">&nbsp;</td>
					<td width="199">&nbsp;</td>

				</tr>
				<tr>
					<td colspan="3" rowspan="7" align="center" valign="bottom"><table
							id="gridAgendaPJENA"></table>
					</td>
					<td align="right">
					  <strong>Fecha
					Seleccionada :</strong></td>
					<td><input type="text"
						id="fechaSeleccionadaAudiencia"
						style="width: 170px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"><strong>Actividad :</strong></td>
					<td><select name="actividadesDefensor"
						id="actividadesDefensor" style="width: 170px;">
					  <option value="0">-Seleccione-</option>
	
				    </select></td>
				</tr>
				<tr>
					<td align="right" valign="bottom"><strong>Duración
					  estimada :</strong></td>
					<td align="left" valign="bottom"><select
						id="duracionEstimadaProgramarAudiencia" style="width: 170px;">
					  <option value="0">-Seleccione-</option>
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
					  <!--<input type="text" id="duracionEstimadaProgramarAudiencia" style="width: 170px; border: 0; background: #DDD;" readonly="readonly" />--></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						id="btnRegistrarActividad" 
						value="Registrar actividad" class="btn_Generico"/></td>
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
					<td width="102" height="30"><strong><input type="text" size="4"
							style="width: 20px; border: 0; background: #669933;" readonly />
					</strong>Disponible</td>
					<td width="123"><strong><input type="text" size="4"
							style="width: 20px; border: 0; background: red;" readonly /> </strong>No
						Disponible</td>
					<td width="85"><input type="text" size="4"
						style="width: 20px; border: 0; background: #CCCCCC;" readonly />
						Inhabil</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5"><table id="gridsTd" width="100px"></table></td>
				</tr>
				</table>
		</div>
	</div>
	
</body>
</html>