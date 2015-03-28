<%@page import="mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia"%>
<%@page import="mx.gob.segob.nsjp.comun.constants.ConstantesGenerales"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atención a Sollicitudes</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
	
	<!--css para el estilo del combo box-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	
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
	
	<!--script para el combo box multiselect-->
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
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
	var primeraConsultaJueces= true;
	//Datos que vienen de asignarSalaTemporalPJENA.jsp
	var datosSalaTemporal="";
	//numero de salas
	var salaTemporal="false";
	//id de la audiencia
	var idAudiencia;
	var tipoAudiencia;
	var multiselect=0;
	var fechaReal="";
	//variables para la consulta de jueces
	var horaSeleccionada="";
	var minutoSeleccionado="";
	var duracionEstimadaAudiencia="";
	var idSalaSeleccionada="";
	//variable que almacena el nombre de las salas
	var nombreSalas=new Array();

	//Variable para controlar los ids de las salas
	var identiSala=new Array();
	
	$(document).ready(function() {

		//Se crean las tabs principales
		$("#tabsprincipalconsulta" ).tabs();
				
		idAudiencia=<%= request.getAttribute("idEvento")%>;
	
		if(idAudiencia == null){
			idAudiencia=<%= request.getSession().getAttribute("idEvento")%>;
			$("#tabsprincipalconsulta" ).tabs('select', 1);
		}		
		//Consultamos el detalle de la solicitud de la audiencia
		consultarDetalleSolicitudDeAudiencia(idAudiencia);

		//obtenemos la fecha actual
		mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
		anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';
		var mesSeleccionado = meses[mesActual];		

		//escrbimos en pantalla la fecha actual
		$('#mes').val(mesSeleccionado);
		$('#anio').val(anioActual);

		//Le da el estilo al combo box de duracion estimada
		$("#duracionEstimadaProgramarAudiencia").multiselect({ 
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
		

		//Se llena el gird de audiencia
		jQuery("#gridSolicitudDeAudienciaImputadosPJENA").jqGrid({
			url:'<%= request.getContextPath()%>/consultarDetalleSolicitudDeAudiencia.do?tipoDeRespuesta=0&idAudiencia='+idAudiencia+'', 
			datatype: "xml", 
			colNames:['Interviniente','Calidad','Delito','Detenido'], 
			colModel:[ 	{name:'Imputado',index:'imputado', width:200, align:"center"},
			           	{name:'Calidad',index:'calidad', width:200, align:"center"},  
						{name:'Delito',index:'delito', width:230, align:"center"},
						{name:'Detenido',index:'detenido', width:180, align:"center"}
						
					],
			pager: jQuery('#pagerGridImputados'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: false,
			width:545,
			sortname: 'imputado',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerGridImputados',{edit:false,add:false,del:false});

		
		/**
		*Funcion que llena el grid de testigos
		*/		
		jQuery("#gridTestigosPJA").jqGrid({ 

			url:'<%=request.getContextPath()%>/consultarTestigosParaAudiencia.do?idAudiencia='+idAudiencia+'', 
			datatype: "xml", 
			colNames:['Lista de Intervinientes para Audiencia'], 
			colModel:[ 	{name:'listaTestigos',index:'listaTestigos', width:200,align:'center'}
												
					],
			pager: jQuery('#pagerGridTestigos'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerGridTestigos',{edit:false,add:false,del:false});


		/**
		*Funcion que llena el grid de traslado
		*/
		jQuery("#gridTrasladoPJA").jqGrid({
		    url:'<%= request.getContextPath()%>/consultarDetalleSolicitudDeAudiencia.do?tipoDeRespuesta=2&idAudiencia='+idAudiencia+'', 
			datatype: "xml", 
			colNames:['Imputado','Delito','Centro de Detención'], 
			colModel:[ 	{name:'imputado',index:'imputado', width:200,align:'center'}, 
						{name:'Delito',index:'delito', width:100,align:'center'},
						{name:'centroDetencion',index:'centroDetencion', width:200,align:'center'}
						
					],
			pager: jQuery('#pager2'),
			rowNum:10,
			rowList:[25,50,100],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pager2',{edit:false,add:false,del:false});

		deshabilitarHabilitarComponentes("inicio");
		$('#txtSalaSeleccionada').val("");
		$('#txtHoraInicioSeleccionada').val("");
	
	});
	//FIN ON READY

	
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

	
	var numeroDeSalas=0;
	/**
	*Funcion que controla la agenda de las salas
	*/
	function controlSalas(fechaReal){
	
		if(primeraConsulta=="true"){
			
			numeroDeSalas=obtenerNumeroDeSalas();
			insertaTablas(numeroDeSalas);
			consultaDisponibilidadSalasPorDia(fechaReal);
			gridHorarios();			
			dibujaGrids(numeroDeSalas,fechaReal);			
			primeraConsulta="false";
			
		}
		else{
			consultaDisponibilidadSalasPorDia(fechaReal);
			
			for (i=0;i<numeroDeSalas;i++){
				salaId = identiSala[i];
				jQuery("#gridSalasPJENA"+salaId).jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDisponibilidadDeUnaSalaPorDia.do?posSala='+salaId+'',datatype: "xml" });
				$("#gridSalasPJENA"+salaId).trigger("reloadGrid");
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
					identiSala[numeroDeSalas] = $(this).find('salaAudienciaId').first().text();
					nombreSalas[numeroDeSalas] = $(this).find('nombreSala').first().text();
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
		for (indice=0;indice<numeroDeSalas;indice++){
			salaId = identiSala[indice];
			$('#gridsTd').append('<td><table width="100%" id="gridSalasPJENA'+salaId+'" border="0"></table></td>');
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
			rowNum:31,
			rowList:[10,20,30],
			autowidth: false,
			height:700,
			scrollOffset:0,
			sortname: 'hora'
		}).navGrid('#pagerHora',{edit:false,add:false,del:false});
		
		var mydata = [ 
						{hora:"00:00-00:30"},
						{hora:"00:30-01:00"},
						{hora:"01:00-02:30"},
						{hora:"02:30-03:00"},
						{hora:"03:00-04:30"},
						{hora:"04:30-05:00"},
						{hora:"05:00-06:30"},
						{hora:"06:30-07:00"},
						{hora:"07:00-08:30"},
						{hora:"08:30-09:00"},
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
						{hora:"15:30-16:00"},
						{hora:"16:30-17:00"},
						{hora:"17:30-18:00"},
						{hora:"18:30-19:00"},
						{hora:"19:30-20:00"},
						{hora:"20:30-21:00"},
						{hora:"21:30-22:00"},
						{hora:"22:30-23:00"},
						{hora:"23:30-00:00"},
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

		var numSala=0;

		//Dibujamos tantos grids como numero de salas
		for (numSala=0;numSala<numeroDeSalas;numSala++){
			
			salaId = identiSala[numSala];
			
			jQuery("#gridSalasPJENA"+salaId).jqGrid({
				url:'<%=request.getContextPath()%>/consultarDisponibilidadDeUnaSalaPorDia.do?posSala='+salaId+'',
				datatype: "xml",
				colNames:[nombreSalas[numSala]], 
				colModel:[
							{name:'sala',index:'sala', width:100, align:"right"},
						],   
				pager: jQuery('#pager'),
				rowNum:31,
				rowList:[10,20,30],
				autowidth: false,
				height:700,
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
			$("#gbox_gridSalasPJENA"+salaId).css('width','87px');
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
				if(	validaHoraAudienciaHoraActual( horaSeleccionada, minutoSeleccionado)){
					if((duracionEstimadaAudiencia/30) <= espacioDisponible){
						muestraHoraSalaSeleccionada();		
						deshabilitarHabilitarComponentes("horarioSala");
					}
					else{
						alertDinamico("La duración estimada es mayor que el tiempo disponible para esta sala.\rIntente con otra sala");	
					}
				}
				else{
					alertDinamico("Esta seleccionando una hora no disponible");
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
		juezSustituto =  $("#juezSustituto").is(':checked');
		
		var multiseleccion=true;
		
		if(automatico==true){
			multiseleccion=false;
		}														
		
		if(duracionEstimadaAudiencia == "" || duracionEstimadaAudiencia <= 0){
			alertDinamico("Seleccione una duración estimada para la audiencia");
		}
		else{	
			if(primeraConsultaJueces==true){	 	
				//Se llena el grid de jueces
				jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid({
					url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudiencia+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimadaAudiencia+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia + '&tipoDeAudienciaSolicitada=' + $("#tipoAudienciaProgramarAudiencia").val(), 
					datatype: "xml", 
					colNames:['Nombre','Carga de trabajo','Paridad',], 
					colModel:[ 	{name:'nombre',index:'nombre', width:200, align:"left"},
				    	       	{name:'carga',index:'carga', width:150, align:"center"},  
								{name:'paridad',index:'paridad', width:50, align:"center"},							
					],
					pager: jQuery('#pagerGridJueces'),
					rowNum:10,
					rowList:[25,50,100],
					autowidth: false,
					sortname: 'carga',
					viewrecords: true,
					multiselect: multiseleccion,
					sortorder: "desc"
				}).navGrid('#gridSolicitudDeAudienciaJuecesPJENA',{edit:false,add:false,del:false});

				primeraConsultaJueces=false;
			}
			else{
				jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudiencia+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimadaAudiencia+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia + '&tipoDeAudienciaSolicitada=' + $("#tipoAudienciaProgramarAudiencia").val(), datatype: "xml" });
				$("#gridSolicitudDeAudienciaJuecesPJENA").trigger("reloadGrid");
			}
			
			deshabilitarHabilitarComponentes("juez");
			verificarTipoAudiencia(automatico);
		}
	}
	
/////////////////////TERMINAN FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA //////////////////////////////////////////////////

	function verificarTipoAudiencia(automatico){

		//Si pulso automáticamente
		if(automatico == true){
			$("#gridSolicitudDeAudienciaJuecesPJENA").unbind('click');
			multiselect=0;
		}
		//Si pulso manualmente
		else{
	
			if(tipoAudiencia=="<%=TipoAudiencia.JUICIO_ORAL.getValorId()%>"){
			 	if($("#juezSustituto").is(':checked')){
			 		alertDinamico("Por favor seleccione 4 jueces del listado");
					multiselect=4;
				}else{
					alertDinamico("Por favor seleccione 3 jueces del listado");
				 	multiselect=3;
				}
			}else{
				alertDinamico("Por favor seleccione un juez del listado");
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
			$('#duracionEstimadaProgramarAudiencia').multiselect('disable');
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
			$('#duracionEstimadaProgramarAudiencia').multiselect('enable');
		}

		if(accion == "designarSala"){
			$('#btnDesignar').attr('disabled',true);
			$('#btnAsignarJuezManual').attr('disabled',false);
			$('#btnAsignarJuezAuto').attr('disabled',false);	
			for (i=0;i<numeroDeSalas;i++){
				salaId = identiSala[i];
				$('#gridSalasPJENA'+salaId).unbind('click');
			}
			$('#duracionEstimadaProgramarAudiencia').multiselect('disable');
		}
		if(accion == "juez"){
			$('#btnAsignarJuezManual').attr('disabled',true);
			$('#btnAsignarJuezAuto').attr('disabled',true);
			$('#btnDesignar').attr('disabled',true);
			$("#gridAgendaPJENA").unbind('click');
			$('#duracionEstimadaProgramarAudiencia').multiselect('disable');
			$('#btnAtrasMes').attr('disabled',true);
			$('#btnAdelanteMes').attr('disabled',true);
			$('#juezSustituto').attr('disabled',true);
			for (i=0;i<numeroDeSalas;i++){
				salaId = identiSala[i];
				$('#gridSalasPJENA'+salaId).unbind('click');
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
		$('#duracionEstimadaProgramarAudiencia').multiselect('enable');
		
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
	function consultarDetalleSolicitudDeAudiencia(idAudiencia){
		if(idAudiencia != null){
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarDetalleSolicitudDeAudiencia.do?tipoDeRespuesta=1',
				data: 'idAudiencia='+ idAudiencia, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					var errorCode;
					errorCode=$(xml).find('response').find('code').text();
					if(parseInt(errorCode)==0){
		  
	    				//limpiaDatosDetalleEvento();
	    				//Tab Detalle de Solicitud			
	    				$("#numCasoSolicitudAudienciaDetalle").val($(xml).find('numeroGeneralCaso').first().text());
	    				$("#numExpedienteSolicitudAudienciaDetalle").val($(xml).find('numeroExpediente').first().text());
	    				$("#caracterSolicitudAudienciaDetalle").val($(xml).find('caracter').first().text());
	    				$("#fechaSolicitudAudienciaDetalle").val($(xml).find('strFechaCreacion').first().text());
	    				$("#fechaSolicitudAudienciaDetalle").val($(xml).find('strFechaCreacion').first().text());
	    				$("#horaSolicitudAudienciaDetalle").val($(xml).find('strHoraCreacion').first().text());
	    				$("#fechaLimiteSolicitudAudienciaDetalle").val($(xml).find('strFechaLimite').first().text());
	    				$("#horaLimiteSolicitudAudienciaDetalle").val($(xml).find('strHoraLimite').first().text());
	    				$("#audienciaSolicitudAudienciaDetalle").val($(xml).find('tipoAudiencia').find('valor').first().text());
	    				$("#institucionSolicitudAudienciaDetalle").val($(xml).find('solicitud').find('nombreInstitucionSolicitante').first().text());
	    				$("#solicitanteSolicitudAudienciaDetalle").val($(xml).find('solicitud').find('nombreSolicitanteExternoInterno').first().text());
						
	    				//Tab Programar Audiencia
	    				$("#tipoAudienciaProgramarAudiencia").val($(xml).find('tipoAudiencia').find('valor').first().text());
	    				
	    				tipoAudiencia = $(xml).find('tipoAudiencia').find('idCampo').first().text();
	    				
	    				$("#numCasoProgramarAudiencia").val($(xml).find('numeroGeneralCaso').first().text());
	    				$("#numExpedienteProgramarAudiencia").val($(xml).find('numeroExpediente').first().text());
	    				$("#fechaLimiteProgramarAudiencia").val($(xml).find('strFechaLimite').first().text());
	    				

	    				//Tab Testigos
	    				$("#tipoAudienciaTestigos").val($(xml).find('tipoAudiencia').find('valor').first().text());
	    				$("#numCasoTestigos").val($(xml).find('numeroGeneralCaso').first().text());
	    				$("#numExpedienteTestigos").val($(xml).find('numeroExpediente').first().text());

	    				//Tab Traslados
	    				$("#tipoAudienciaTralados").val($(xml).find('tipoAudiencia').find('valor').first().text());
	    				$("#numCasoTraslados").val($(xml).find('numeroGeneralCaso').first().text());
	    				$("#numExpedienteTraslados").val($(xml).find('numeroExpediente').first().text());

	    					    				
	    			}
				}
			});		
		}
	}

	var tieneMasSolicitudes ="";
	var solRepetidas =0;
	function guardarAudiencia(){
		var valor = jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('getGridParam','selarrrow').length;
		
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
			parametrosGuardado += '&tipoDeAudienciaSeleccionada=' + tipoAudiencia;
			parametrosGuardado += '&numCasoSolicitudAudienciaDetalle=' + escape(jQuery("#numCasoSolicitudAudienciaDetalle").val());
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
					
					if(mensaje == "fail") {
						alertDinamico("La audiencia ya fue programada anteriormente por algún otro usuario");
						/*
						*recargamos el grid de audiencias para que la solicitud de audiencia que se abrío ya no 
						*aparesca en la bandeja
						*/
						parent.recargarGridAudienciaPJENA();
						
					} else if(mensaje == "salaOcupada") {
						alertDinamico("Algún otro usuario acaba de programar una audiencia en esta sala y horario");
						try{
							parent.recargarGridAudienciaPJENA();
							setTimeout("recargaVisor()",3000);
						}catch(e){}
					} else {
						/*
						*Guardamos la programación de la audiencia en las agendas de los Jueces
						*/
						guardarEventoAudiencia();
						/*
						*recargamos el grid de audiencias para que la audiencia que se acaba de
						*programar, no se muestre mas 
						*/
						parent.recargarGridAudienciaPJENA();
						//deshabilitamos el boton de guardado
						$('#btnGuardarAudiencia').attr('disabled',true);
						//Refrescamos el grid de las salas
						controlSalas(fechaReal);
						tieneMasSolicitudes = mensaje.substring(0,19);
						solRepetidas = 	mensaje.substring(19, mensaje.length);
						
						var idEvento = mensaje;
						
						if(idEvento=="<%=ConstantesGenerales.NO_ES_JAVS%>"){
							customAlert(
									"La programación de la audiencia se realizó de manera correcta", 
									"Programación de Audiencias", 
									cancelarSolicitudesRepetidas									
							);
						// Con agendar audiencia en JAVS							
						}else{
							var S_Mensaje;
							S_Mensaje=mensajeEstadoJAVS(idEvento);
							if(S_Mensaje!=""){
								alertDinamicoCerrar(S_Mensaje,1);	
							}
							else{
								alertDinamicoCerrar(S_Mensaje,2);
							}
					    	
						 }
					}	
				}
			});			
		}else{

			if(multiselect==1){
				alertDinamico('Seleccione exactamente un juez del listado');
			}
			else{
				alertDinamico('Seleccione exactamente '+multiselect+' jueces del listado');
			}
			
		}

	}		

	//Función para alertDinamicoCerrar
	function alertDinamicoCerrar(textoAlert, accion){			
		if(accion==2){
			textoAlert="Error al agendar audiencia en servidor JAVS, no hay conexión con el WS.</br>No se agendó en el Sistema.";
		}
		$("#divAlertTextoCerrar").html(textoAlert);
	    $( "#dialog-AlertCerrar" ).dialog({
			autoOpen: true,
			resizable: false,
			modal: true,
			buttons: {								
				"Aceptar": function() {
					if(accion==1){
						cancelarSolicitudesRepetidas();
					}
					$( this ).dialog( "close" );
					$("#divAlertTextoCerrar").html("");					
				}				
			}
		});    
	 }		

	function mensajeEstadoJAVS(idEvento){
		var S_Mensaje="";
		switch (idEvento){
            case "<%=ConstantesGenerales.ERROR_AGENDAR_JAVS%>":
                S_Mensaje = "Error al agendar audiencia en el Sistema y el Servidor JAVS";
                break;
            case "<%=ConstantesGenerales.EXITO_AGENDAR_JAVS%>":
                S_Mensaje = "Audiencia agendada correctamente en el Sistema y el Servidor JAVS";
                break;
            case "<%=ConstantesGenerales.EXITO_AGENDAR_JAVS_SIN_ARCH_OUT%>":
            	S_Mensaje = "Audiencia agendada correctamente en el Sistema y el Servidor JAVS";
                //S_Mensaje = "Audiencia agendada correctamente en servidor JAVS, error al crear archivo de salida";
                break;
            case "<%=ConstantesGenerales.EXITO_AGENDAR_JAVS_SIN_ARCH_OUT%>":
            	S_Mensaje = "Audiencia agendada correctamente en el Sistema y el Servidor JAVS";
                //S_Mensaje = "Audiencia agendada correctamente en servidor JAVS, error al crear archivo de recepción";
                break;
            case "<%=ConstantesGenerales.EXITO_AGENDAR_JAVS_SIN_ARCH_INOUT%>":
            	S_Mensaje = "Audiencia agendada correctamente en el Sistema y el Servidor JAVS";
                //S_Mensaje = "Audiencia agendada correctamente en servidor JAVS, error al crear archivos de verificación";
                break;
            case "<%=ConstantesGenerales.ERROR_PARAMETROS_CONEXION%>":
                S_Mensaje = "Error al agendar audiencia en servidor JAVS, parámetros de conexión incorrectos.</br>No se agendó en el Sistema.";
                break;
            case "<%=ConstantesGenerales.ERROR_ELEMENTOS_INSUFICIENTES%>":
                S_Mensaje = "Error al agendar audiencia en servidor JAVS, no hay elementos suficientes en la audiencia.</br>No se agendó en el Sistema.";
                break;
            case "<%=ConstantesGenerales.ERROR_CREDENCIALES%>":
                S_Mensaje = "Error al agendar audiencia en servidor JAVS, credenciales de conexión incorrectas.</br>No se agendó en el Sistema.";
                break;
            case "<%=ConstantesGenerales.FALLO_CONEXION_WEB_SERVICE_JAVS%>":
                S_Mensaje = "Error al agendar audiencia en servidor JAVS, no hay conexión con el WS.</br>No se agendó en el Sistema.";
                break;
        }		
        return S_Mensaje;
	}
	
	function cancelarSolicitudesRepetidas(){
		if (tieneMasSolicitudes == "TieneMasSolicitudes"){
							
			var mensaje =  " El caso <strong>" + jQuery("#numCasoSolicitudAudienciaDetalle").val() + "</strong> <br /> ";
				mensaje += " tiene <strong>" + solRepetidas + "</strong> solicitudes de audiencias del tipo <strong>" + jQuery("#tipoAudienciaProgramarAudiencia").val() + "</strong><br />"; 
				mensaje += " ¿Desea cancelarlas?";
				customConfirm(
					mensaje, 
					"Solicitudes De Audiencia Repetidas",
					function (){				
						var	parametrosGuardado = 'tipoDeAudienciaSeleccionada=' + tipoAudiencia;
						parametrosGuardado += '&numCasoSolicitudAudienciaDetalle=' + escape(jQuery("#numCasoSolicitudAudienciaDetalle").val());
						
						$.ajax({
							type: 'POST',
							url: '<%= request.getContextPath()%>/cancelarSolicitudesDeAudiencia.do',
							data: parametrosGuardado,
							dataType: 'xml',
							async: false,
							success: function(xml){
								var mensaje = $(xml).find('body').find('fechaActual').text();
								
								if(mensaje == "fail") {
									customAlert("No se ha podido cancencelar las solicitudes<br /> por favor intente más tarde", "Error");
								} else {
								
									parent.recargarGridAudienciaPJENA();
									customAlert(
											"Se han cancelado las solicitudes.", 
											"Programación de Audiencias"
									);
								}	
							}
						});				
					}
				);
		}
	}	


	/*Funcion que permite refrescar la ventana y redirigirla de nuevo*/
	function recargaVisor(){
			document.frmRecargaVisor.idAudiencia.value = idAudiencia;
			document.frmRecargaVisor.idVisor.value = 1;
			document.frmRecargaVisor.submit();
	}


	/*
	*Funcion que valida, que la hora y minuto seleccionados sean mayores o iguales
	*a la hora del servidor, para programar la audiencia, regresa true, en caso
	*verdadero, false caso contrario.
	*/
    function validaHoraAudienciaHoraActual(horaSelec, minutoSelec){

		var fecha = "";
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/regresaFechaYHoraDelServidor.do',
			dataType : 'xml',
			async : false,
			success : function(xml) {
				fecha = $(xml).find('fecha').text();
			}
		});
		
		//mes y anio seleccionados
		var mesSelec = $('#mes').val();
		var anioSelec = $('#anio').val();
		
		//hora, minuto, dia, mes y anio actuales
		var horaC = fecha.substring(11, 13);
		var minutoC = fecha.substring(14,16);
		
		var dia = fecha.substring(8, 10);
		var mes = meses[parseInt(fecha.substring(5, 7)) - 1];
		var anio = fecha.substring(0, 4);
		
		if (parseInt(fechaReal) == parseInt(dia) && mesSelec == mes
				&& parseInt(anioSelec) == parseInt(anio)) {
			if (parseInt(horaSelec) > parseInt(horaC)) {
				return true;
			}
			else{
				if(parseInt(horaSelec) == parseInt(horaC)){
					if(parseInt(minutoSelec)  >= parseInt(minutoC)){
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
		} 
		else {
			return true;
		}
	}
	
	/*
	* Funcion que guarda el evento en la agenda de los funcionarios Jueces
	*/
	function guardarEventoAudiencia(){
		var parametros='';

		//Si la asignacion de jueces fue automatica
		if(multiselect == 0){
			parametros += '&idJueces=' + jQuery("#gridSolicitudDeAudienciaJuecesPJENA").getDataIDs();
		}
		else{
			parametros += '&idJueces=' + jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('getGridParam','selarrrow');	
		}
		
	    parametros += '&diaEvento=' + $('#fechaSeleccionadaAudiencia').val().split("/")[0];
		parametros += '&mesEvento=' + $('#fechaSeleccionadaAudiencia').val().split("/")[1];
		parametros += '&anioEvento=' + $('#fechaSeleccionadaAudiencia').val().split("/")[2];
		parametros += '&duracionEstimada=' + duracionEstimadaAudiencia;
		parametros += '&horaEvento=' + $('#txtHoraInicioSeleccionada').val();
		parametros += '&idSala=' + $('#txtSalaSeleccionada').val();
		parametros += '&tipoDeAudienciaSolicitada=' + $("#tipoAudienciaProgramarAudiencia").val();

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/guardarEventoAudiencia.do',
			data: parametros,
			dataType: 'xml',
			async: false,
			success: function(xml){
				var mensaje = $(xml).find('body').find('fechaActual').text();
				
				if(mensaje == "fallo"){
					alertDinamico("No se logró guardar el evento en la agenda del juez");
				}	
			}
		});	
	}
	
	</script>
</head>


<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Detalle de Solicitud</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Programar Audiencia</a>
			</li>
			<li><a href="#tabsconsultaprincipal-3">Intervinientes</a>
			</li>
			<li><a href="#tabsconsultaprincipal-4">Traslados</a>
			</li>

		</ul>
		
		<div id="tabsconsultaprincipal-1" align="left">
		
		<!--<table width="1060px" height="367px" border="0" class="back_obj" cellpadding="0" cellspacing="5">-->
			<table width="1060px" height="367px" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr> 
					<td width="16%" align="right"><strong>Número de Caso:</strong>
					</td>
					<td width="24%"><input type="text"
						id="numCasoSolicitudAudienciaDetalle"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td width="7%"><strong>Audiencia Solicitada:</strong>
					</td>
					<td width="29%"><input type="text"
						id="audienciaSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Número de Causa:</strong>
					</td>
					<td><input type="text" id="numExpedienteSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td colspan="2" rowspan="8" align="left"><table
							id="gridSolicitudDeAudienciaImputadosPJENA"></table>
						<div id="pagerGridImputados" style="width: 300"></div>
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Carácter: </strong>
					</td>
					<td><input type="text" id="caracterSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Fecha de Solicitud:</strong>
					</td>
					<td><input type="text" id="fechaSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Hora de Solicitud:</strong>
					</td>
					<td><input type="text" id="horaSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Institución Solicitante:</strong>
					</td>
					<td><input type="text" id="institucionSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Solicitante:</strong>
					</td>
					<td><input type="text" id="solicitanteSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Fecha Limite de Audiencia:</strong>
					</td>
					<td><input type="text" id="fechaLimiteSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Hora Limite de Audiencia:</strong>
					</td>
					<td><input type="text" id="horaLimiteSolicitudAudienciaDetalle"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>



		</div>
		
		<div id="tabsconsultaprincipal-2" align="left">
		
			<table width="1180" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="258">&nbsp;</td>
			    <td width="178">&nbsp;</td>
			   
			    <td colspan="3" align="center" valign="bottom"><input type="button" value="<<" id="btnAtrasMes" onClick="atrasAdelanteMes('atras');" class="btn_Generico"/>
			      <input type="text" id="mes" disabled="disabled" style="width: 70px"/>
			      <input type="text" id="anio" disabled="disabled" style="width: 70px" />
			    <input type="button" value=">>" id="btnAdelanteMes" onClick="atrasAdelanteMes('adelante');" class="btn_Generico"/></td>
			    <td width="100" nowrap="nowrap"><strong>Designar Juez:</strong> 
			    	
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
			    <td align="right" valign="bottom"><strong>Tipo de audiencia solicitada:</strong></td>
			    <td><input type="text" id="tipoAudienciaProgramarAudiencia" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" /></td>
			    <td colspan="3" rowspan="7" align="center" valign="bottom"><table id="gridAgendaPJENA"></table></td>
			    <td colspan="3" rowspan="7" ><table id="gridSolicitudDeAudienciaJuecesPJENA"></table></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Número de caso:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="numCasoProgramarAudiencia"
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Número de
			    Causa:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="numExpedienteProgramarAudiencia"
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Fecha l&iacute;mite de audiencia:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="fechaLimiteProgramarAudiencia"
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Fecha Seleccionada:</strong></td>
			    <td align="left" valign="bottom"><input type="text"
			                        id="fechaSeleccionadaAudiencia"
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td align="right" valign="bottom"><strong>Duración estimada de audiencia:</strong></td>
			    <td align="left" valign="bottom">
			     <select id="duracionEstimadaProgramarAudiencia" style="width:200px;">
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
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td height="30" align="right"><strong>Hora de inicio:</strong></td>
			    <td><input type="text"
			                        id="txtHoraInicioSeleccionada"
			                        style="width: 200px; border: 0; background: #DDD;"
			                        readonly="readonly" /></td>
			    <td width="102">
			    	<strong><input type="text" size="4" style="width: 20px; border: 0; background: #669933;" readonly />
			    	</strong>Disponible </td>
			    <td width="123">
			    	<strong><input type="text" size="4" style="width: 20px; border: 0; background: red;" readonly />
			   		</strong>No Disponible
			    </td>
			    <td width="85">
			    	<input type="text" size="4" style="width: 20px; border: 0; background: #CCCCCC;" readonly />Inhábil </td>
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
			    <td colspan="3" align="center"><input type="button" class="btn_guardar" id="btnGuardarAudiencia" value="Guardar"  onclick="guardarAudiencia();" class="btn_Generico"/></td>
			    <td colspan="3" align="left"><input type="button" class="btn_mediano" id="btnDesignar" onclick="mostrarAsignarSalaTemporalPJENA();" value="Designar Sala Temporal"  class="btn_Generico"/></td>
			  </tr>
			</table>
			
														
		</div>
		
		
		<div id="tabsconsultaprincipal-3" align="left">


			<table width="700" height="355" class="back_bienvenido" border="0" cellpadding="0"
				cellspacing="5">
				<tr>
					<td colspan="4" align="center"><strong>Tipo de Audiencia
							Solicitada:</strong> <input type="text"
						id="tipoAudienciaTestigos"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">&nbsp;</td>
				</tr>
				<tr>
					<td width="25%" align="right"><strong>Número de Caso:</strong>
					</td>
					<td width="27%"><input type="text"
						id="numCasoTestigos"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td width="30%" align="right"><strong>Número de
							Causa:</strong>
					</td>
					<td width="25%"><input type="text"
						id="numExpedienteTestigos"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="right"><table id="gridTestigosPJA"></table>
						<div id="pagerGridTestigos" style="width: 300"></div>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">&nbsp;</td>
				</tr>
			</table>


		</div>
	
		<div id="tabsconsultaprincipal-4" align="left">

			<table width="720" border="0" cellpadding="0" cellspacing="5">
				
				
				<tr>
					<td colspan="4" align="center"><strong>Audiencia
							Solicitada:</strong> <input type="text"
						id="tipoAudienciaTralados"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">&nbsp;</td>
				</tr>
				<tr>
					<td width="18%" align="right"><strong>Número de Caso:</strong>
					</td>
					<td width="27%"><input type="text"
						id="numCasoTraslados"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td width="30%" align="right"><strong>Número de
							Causa:</strong>
					</td>
					<td width="25%"><input type="text"
						id="numExpedienteTraslados"
						style="width: 180px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="right"><table id="gridTrasladoPJA"></table>
						<div id="pager2" style="width=300" ></div>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="right">&nbsp;</td>
				</tr>
				<tr>
				  <td colspan="2" align="center"><input type="button"
						value="Enviar" id="trasladoImputado"  class="btn_Generico"/> 
				  </td>
                    <td colspan="2" align="center"><input type="button"
						value="Cancelar" id="trasladoImputado" class="btn_Generico" /> 
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right">&nbsp;</td>
				</tr>
</table>
            <div id="poponTraslados" style="display: none;">
            <table width="745" border="0">
            
             <tr>
    <td colspan="2" align="right">Nombre de la Persona a Trasladar:</td>
    <td colspan="2">&nbsp;&nbsp; <input name="textfield7" type="text" disabled="disabled" id="textfield7" /></td>
   
  </tr>
  <tr align="right">
    <td width="187">Lugar de Origen:</td>
    <td width="176"><label>
      <input name="textfield" type="text" disabled="disabled" id="textfield" />
    </label></td>
    <td width="183">Lugar Destino:</td>
    <td width="181"><input name="textfield2" type="text" disabled="disabled" id="textfield2" /></td>
  </tr>
  <tr align="right">
    <td>Funcionario que Autoriza:</td>
    <td><input name="textfield3" type="text" disabled="disabled" id="textfield3" /></td>
    <td>Fecha de Presentacion:</td>
    <td><input name="textfield4" type="text" disabled="disabled" id="textfield4" /></td>
  </tr>
  <tr align="right">
    <td>Hora de Presentacion:</td>
    <td><input name="textfield5" type="text" disabled="disabled" id="textfield5" /></td>
    <td>Duracion de Presentacion:</td>
    <td><input name="textfield6" type="text" disabled="disabled" id="textfield6" /></td>
  </tr>
 
</table>

            </div>
			
		</div>

	</div>
	
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
	
	<!-- div para el alert dinamico antes de cerrar ventana -->
	<div id="dialog-AlertCerrar" style="display: none">
		<table align="center">
			<tr>
	        	<td align="center">
    	        	<span id="divAlertTextoCerrar"></span>
            	</td>
        	</tr>
     	</table>              
	</div>    	
	
	<form id="frmRecargaVisor" 
		  name="frmRecargaVisor" action="<%=request.getContextPath()%>/recargaVisor.do"
		  method="post" enctype="multipart/form-data">
		  
		<input type="hidden" name="idAudiencia" /> 
		<input type="hidden" name="idVisor" />
	</form>
	
</body>
</html>