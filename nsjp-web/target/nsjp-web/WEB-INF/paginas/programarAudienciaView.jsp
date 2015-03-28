<%@page import="mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia"%>
<script type="text/javascript">
//**********************************************************FUNCIONALIDAD DE LA CEJA PROGRAMAR AUDIENCIA**********************************************************\ 	

//FUNCIONALIDAD COMUN A LAS 3 SUB CEJAS

/*
*Funcionalidad para obtener el id de la audiencia siguiente
*/
function recuperaIdSiguienteAudiencia(){
	
	$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/recuperaIdSiguienteAudiencia.do',
		data: 'idAudiencia='+ idAudiencia,
		async: false,
		dataType: 'xml',
		success: function(xml){
			var errorCode;
			errorCode=$(xml).find('response').find('code').text();
			//if(parseInt(errorCode)==0){todo bien}
		}
	});
}


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
		for (i=1;i<=numeroDeSalas;i++){
			$('#gridSalasPJENA'+i).unbind('click');
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
		for (i=1;i<=numeroDeSalas;i++){
			$('#gridSalasPJENA'+i).unbind('click');
		}
		$('#btnGuardarAudiencia').attr('disabled',false);	
	}
}




//////////////////////////////////DEPRECIADOS////////////////////////////////////
/*
*Funcion que oculta las cejas de involucrados y objetos
*de la ceja programar audiencia
*/
function ocultaSubCejasProgAudiencia(){
	$("#tabschild").tabs("option", "disabled", [1,2]);					
  }

/*
*Habilita el tab de Involucrados y el teb de objetos
*/	
function muestraSubCejasProgAudiencia(){
	$('#tabschild').tabs('enable', 1);
	$('#tabschild').tabs('enable', 2);
}
//////////////////////////////////DEPRECIADOS////////////////////////////////////

/**
*Funcion que recarga el grid con la funcionalidad de la agenda
*/
function cargaGridAgenda(){
	
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
}

/*
*Funcion que consulta el detalle del evento y llena 
*los campos de la TAB Detalle evento
*/
function formateaFechaHora(fechaHora){
	
	var fechaFrac = fechaHora.split(" ")[0];
	var horaFrac = fechaHora.split(" ")[1];

	horaFracPos = horaFrac.indexOf(":", 0);
	hora=horaFrac.substring(0,horaFracPos+3);
	    				
	$("#fechaAudienciaPJENS").val(fechaFrac);
	$("#horaAudienciaPJENS").val(hora);
}

/*
*Funcion que carga los combos de tipo de solicitudes de audiencia que se pueden realizar
*excepto el tipo de audiencia actual
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


//COMIENZA FUNCIONALIDAD DEL CALENDARIO

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

/*
*Verifica el tipo de audiencia y en el caso de ser de juicio oral
*ordena se capturen el numero de jueces correspondientes al tipo
*/
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


//COMIENZA FUNCIONALIDAD DEL CALENDARIO //


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
		alert("Seleccione un dia hábil");
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
//TERMINA FUNCIONALIDAD PARA LOS GIRDS DINAMICOS DE SALAS


//COMIENZA FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA
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
		jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarDisponibilidadJueces.do?audienciaId='+idAudienciaSiguiente+'&diaDisp='+fechaReal+'&mesDisp='+mesDisponible+'&anioDisp='+anioDisponible+'&horaSeleccionada='+horaSeleccionada+'&minutoSeleccionado='+minutoSeleccionado+'&duracionEstimada='+duracionEstimada+'&automatico='+automatico+'&juezSustituto='+juezSustituto+'&tipoAudiencia='+tipoAudiencia,datatype: "xml" });
		$("#gridSolicitudDeAudienciaJuecesPJENA").trigger("reloadGrid");
		deshabilitarHabilitarComponentes("juez");
		verificarTipoAudiencia(automatico);
	}
	
}
//TERMINAN FUNCIONES PARA ASIGNAR AUDIENCIA A UNA SALA


///COMIENZA FUNCIONALIDAD SALA TEMPORAL
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
    $.updateWindowContent("iframewindowAsignarSalaTemporalAudiencia" ,'<iframe src="<%= request.getContextPath() %>/asignarSalaTemporalPJENA.do?idEvento=idAudienciaSiguiente" width="650" height="200" />');			
}
	
function cerrarVentanaAsignacionTemporal(){
	$.closeWindow('iframewindowAsignarSalaTemporalAudiencia');
}
//TERMINA FUNCIONALIDAD SALA TEMPORAL


/**
*Funcion para el guardado de la audiencia
*/
function guardarAudiencia(){
	
	var valor = jQuery("#gridSolicitudDeAudienciaJuecesPJENA").jqGrid('getGridParam','selarrrow').length;

	//tipoDeAudienciaSelecc=$("#tipoAudienciaProgramarAudiencia option:selected").val();
	tipoDeAudienciaSelecc=$(':radio[name=radioProgramarAudienciaPJENS]:checked').val();

	//Se verifica que se seleccione un tipo de audiencia
	if(tipoDeAudienciaSelecc != "0"){
		if(valor == multiselect){
			var parametrosGuardado='';
			
			parametrosGuardado += 'numCarpetaEjecucion='+ $("#numExpPJENS").val();
			//parametrosGuardado += 'folioSolicitud='+ $("#numExpPJENS").val();
			parametrosGuardado += '&idAudiencia='+ idAudienciaSiguiente;
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
				type:'POST',
				url:'<%= request.getContextPath()%>/guardarAudienciaProgramaNueva.do',
				data:parametrosGuardado,
				async:false,
				dataType:'xml',
				success:function(xml){					
						alert("La programación de la audiencia se realizó de manera correcta");
						/*
						*recargamos el grid de audiencias para que la audiencia que se acaba de
						*programar, no se muestre mas 
						*/
						//Falta esta funcion en el padre
						//parent.recargarGridAudienciaPJENA();
						//deshabilitamos el boton de guardado
						$('#btnGuardarAudiencia').attr('disabled',true);
						//Refrescamos el grid de las salas
						controlSalas(fechaReal);
						//Mostramos las cejas para capturar 
						muestraSubCejasProgAudiencia();
				}
			});			
		}
		else{
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
}
//TERMINA FUNCIONALIDAD DEL CALENDARIO



</script>

<table width="100%" border="0">
	<tr>
		<td width="25%">
		<table width="200" border="0">
			<tr>
				<td>
				<fieldset><legend>Tipo Audiencia</legend>
				<table width="100%" border="0">
					<tr>
						<td width="23"><input type="radio"
							name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.CONTROL.getValorId() %>" /></td>
						<td width="167">Control</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaImputacionPJENS"
							value="<%= TipoAudiencia.IMPUTACION.getValorId() %>" /></td>
						<td>Imputación</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaVinculacionPJENS"
							value="<%= TipoAudiencia.VINCULACION.getValorId()%>" /></td>
						<td>Vinculación</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.INTERMEDIA.getValorId()%>" /></td>
						<td>Intermedia</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.JUICIO_ORAL.getValorId()%>" /></td>
						<td>Juicio Oral</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.INDIVIDUALIZACION_DE_SANCION.getValorId()%>" />
						</td>
						<td>Individualización de Sanción</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.LECTURA.getValorId()%>" /></td>
						<td>Lectura</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.SSP.getValorId()%>" /></td>
						<td>SSP</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.VERIFICACION_SSP.getValorId()%>" /></td>
						<td>Verificación SSP</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.MASC.getValorId()%>" /></td>
						<td>MASC</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.VERIFICACION_MASC.getValorId()%>" /></td>
						<td>Verificación MASC</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.EJECUCION.getValorId()%>" /></td>
						<td>Ejecución</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.CATEO.getValorId()%>" /></td>
						<td>Cateo</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.APREHENSION.getValorId()%>" /></td>
						<td>Aprehensión</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr />
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="radioProgramarAudienciaPJENS"
							id="tipoAudienciaControlPJENS"
							value="<%= TipoAudiencia.ABREVIADO.getValorId()%>" /></td>
						<td>Abreviado</td>
					</tr>
				</table>
				</fieldset>
				</td>
			</tr>
			<tr>
				<td>
				<fieldset><legend>Audiencia Previa</legend>
				<table width="100%" border="0">
					<tr>
						<td>Identificador</td>
						<td><input type="text" name="textfield" id="textfield" /></td>
					</tr>
					<tr>
						<td>Tipo</td>
						<td><input type="text" name="textfield2" id="textfield2" />
						</td>
					</tr>
				</table>
				</fieldset>
				</td>
			</tr>

		</table>
		</td>
		<td width="75%">
		<table width="100%" border="0" height="100%">
			<tr>
				<td width="49%">
				<fieldset><legend>Inputados</legend>
				<table width="100%" border="0">
					<tr>
						<td width="54%">
							<select name="select" id="select"
							multiple="multiple" size="5" style="width: 100">
							</select>
						</td>
					</tr>
				</table>


				</fieldset>
				</td>
				<td width="51%">
				<table width="100%" border="0">
					<tr>
						<td>
						<fieldset><legend>Juez</legend>
						<table width="100%" cellpadding="1" cellspacing="1">
							<tr>
								<td colspan="2"><input type="checkbox" value="true"
									name="juezSustituto" id="juezSustituto" /> Sustituto</td>
							</tr>
							<tr>
								<td width="111"><input type="button" class="btn_modificar"
									value="Manualmente" id="btnAsignarJuezManual"
									onclick="controlJueces(false);" /></td>
								<td width="125"><input type="button" class="btn_mediano"
									value="Autom&aacute;ticamente" id="btnAsignarJuezAuto"
									onclick="controlJueces(true);" /></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td>
								<table id="gridSolicitudDeAudienciaJuecesPJENA"></table>
								</td>
							</tr>
						</table>
						</fieldset>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" align="center">
					<tr align="center">
						<td><input type="button" class="btn_atras" value=""
							id="btnAtrasMes" onclick="atrasAdelanteMes('atras');" /> <input
							type="text" id="mes" disabled="disabled" style="width: 70px" /> <input
							type="text" id="anio" disabled="disabled" style="width: 70px" />
						<input type="button" class="btn_adelante" value=""
							id="btnAdelanteMes" onclick="atrasAdelanteMes('adelante');" /></td>
					</tr>
					<tr>
						<td>
						<table id="gridAgendaPJENA"></table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<tr align="center">
								<td align="right" valign="bottom"><strong>Duración:</strong>
								</td>
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
								</select> <!--<input type="text" id="duracionEstimadaProgramarAudiencia" style="width: 170px; border: 0; background: #DDD;" readonly="readonly" />-->
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				</td>
				<td>
				<table id="gridsTd" width="100px"></table>
				</td>
			</tr>
			<tr align="center">
				<td><input type="button" id="btnGuardarAudiencia"
					class="btn_guardar" value="Guardar" onclick="guardarAudiencia();" /></td>
				<td><input type="button" class="btn_mediano" id="btnDesignar"
					onclick="mostrarAsignarSalaTemporalPJENA();"
					value="Designar Sala Temporal" /></td>
			</tr>
		</table>
	</tr>
</table>
<table width="200" border="0">
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>

</table>
