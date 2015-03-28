<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Hechos</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />	
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />			
		<style type="text/css">
			dd p{line-height:120%}
			#iHechosAccordionPane {width:1000px;height:385px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iHechosAccordionPane dl{width:1000px;height:385px}	
			#iHechosAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iHechosAccordionPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#68889b}
			#iHechosAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iHechosAccordionPane dt.hover{color:#E78F08;}
			#iHechosAccordionPane dt.active.hover{color:#1C94C4}
			#iHechosAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iHechosAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iHechosAccordionPane .active .slide-number{color:#fff;}
			#iHechosAccordionPane a{color:#68889b}
			#iHechosAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iHechosAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iHechosAccordionPane .more{padding-top:10px;display:block}
		</style>
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		
									<!--Scripts necesarios para la ejecucion del editor-->
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	   
		<script type="text/javascript">
			var idWindowIngresarNarrativa = 1;
			var numeroExpediente="";
			var numeroExpedienteId="";
			var idNumeroExpedienteOp="";
			var idUsuario="";
			var tipoTiempoHecho=1;//1 para definido,2 para lapso y 3 para descripcion hecho en el tiempo
			var calidad="";
			var idHecho=";"
			var banderaConsultaHecho=0;
			var deshabilitarCampos = window.parent.deshabilitarCamposPM;

			//variables para la modificacion de tiempo y lugar
			var idTiempo=0;
			var idLugar=0;
			
			//variables para setear las fechas y horas maximas
			var fechaServidor="";
			var fechaMax="";
			var timeMax="";
			var isIPH=true;
			
			var narrativaConsultada="";
			
			var flagMenuIntermedio=0;
			
			jQuery().ready(			
				function () {
					
					var auxiliar='<%= request.getParameter("menuIntermedio")%>';
					
					if(auxiliar!=null){
						flagMenuIntermedio=auxiliar;
					}
					
					$("#tabsHechos").tabs();
					numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
					numeroExpedienteId='<%= request.getParameter("numeroExpedienteId")%>';
					idNumeroExpedienteOp='<%= request.getParameter("idNumeroExpedienteOp")%>';
					calidad='<%= request.getParameter("idCalidad")%>';
					idHecho='<%= request.getParameter("idHecho")%>';
					isIPH = '<%=request.getParameter("iphFuncionalidadHidden")%>';
					idUsuario="0000";
					fechaServidor= consultaFechaHoraMaximaServer();
					fechaMax=getFechaMaximaServerHechos(fechaServidor);
					timeMax=getHoraMaximaServer(fechaServidor);
					$("#idFechaDateLapso").attr("disabled","disabled");
					$("#idFechaDateLapso2").attr("disabled","disabled");
					//$("#idHoraDateLapsoInicio").attr("disabled","disabled");
					$("#idHoraDateLapsoFin").attr("disabled","disabled");
					//$("#idHoraDate").timeEntry({maxTime: timeMax}); 
					//$("#idHoraDateLapsoFin").timeEntry({maxTime: timeMax}); 
					$("#idFechaDate").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '-111:+0',
						maxDate: fechaMax,
						onSelect: function(date) {
							$("#idHora").val(timeMax);
							revisaLongitudFechasEspecifica();
						}, 
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#idFechaDateLapso").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '-111:+0',
						maxDate: fechaMax,
						onSelect: function(date) {
							//setter fecha minima al segunda calendario
							$( "#idFechaDateLapso2" ).datepicker( "option", "minDate", date );
						},
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#idFechaDateLapso2").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '-111:+0',
						maxDate: fechaMax, 
						minDate: fechaMax,
						onSelect: function(date) {
							//sette la hora maxima del inicio
							var fechaTemp=$("#idHoraDateLapsoFin").val();
							//$("#idHoraDateLapsoInicio").timeEntry('destroy');
							$("#idHoraDateLapsoInicio").val(fechaTemp.replace(" ",""));
							//$("#idHoraDateLapsoFin").timeEntry({show24Hours: false,defaultTime: null,maxTime: timeMax});
							revisaLongitudFechas();
						},
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#idFechaArribo").datepicker({
						dateFormat: 'dd/mm/yy',
						yearRange: '-111:+0',
						maxDate: fechaMax,
						onSelect: function(date) {
							$("#idHora").val(timeMax);
							revisaLongitudFechasEspecifica();
						}, 
						changeMonth: true,
						changeYear: true,
						showOn: "button",
						buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
						buttonImageOnly: true			
					});
					$("#btnGuardarHechos").click(guardaHecho);
					$("#btnModificarHechos").click(modificarHecho);
					$("#btnGuardarHechos").show();
					$("#btnModificarHechos").hide();
					//se crean los tabs y el acordeon
					/*$('#iHechosAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});*/
					//asginamos los listener de los botones de fecha y hora del hecho
					$("#ingresarTiempoLapsoPResponsable").click(cambiaLapso);
					$("#ingresarTiempoEspecificamentePResponsable").click(cambiaEspecifico);
					$("#ingresarTiempoOtroPResponsable").click(creaNuevaNarrativa);
					
					killDomicilioNotificaciones();
					
					
					if(idHecho!=null && parseInt(idHecho)!=0)
					{
						$("#btnGuardarHechos").hide();
						$("#btnModificarHechos").show();
						//consultamos el Hecho
						consultaHecho();
					}

					$('#idFechaDateLapso').change(validaCamposFecha);
					$('#idFechaDateLapso2').change(validaCamposFecha);
					
					if (isIPH == true || isIPH == "true") {
						$("#IPHFuncionalidadOcultaTipoExpediente").hide();					
					}
					
					//Instruccion pensada solo para el caso de policia ministerial
					if(deshabilitarCampos == true){
						$(":enabled").attr('disabled','disabled');
					}
				});
			
			$(function(){
 	    	   $('#idHoraArribo').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
 	    	 });
			
			/*
			*Funcion para traer la fecha y hora del servidor en el formato : YYYY-MM-DD HH:MI:SS
			*/
			function consultaFechaHoraMaximaServer()
			{
				var fecha="";
				   $.ajax({
					     type: 'POST',
					     url: '<%=request.getContextPath()%>/regresaFechaYHoraDelServidor.do',
						 dataType: 'xml',
						 async: false,
						 success: function(xml){
							fecha= $(xml).find('fecha').text();
						  }
						});
				return fecha;
			}
			
			/*
			 * Funcion para regresar la fecha maxima obtenida desde el servidor
			 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
			 * regresa una cadena con la fecha en el formato : DD/MM/YYYY
			 */
			function getFechaMaximaServerHechos(fechaCompleta)
			{
				var arrFechaHora=fechaCompleta.split(" ");
				var digitosFecha=arrFechaHora[0].split("-");
				return digitosFecha[2]+'/'+digitosFecha[1]+'/'+digitosFecha[0];
			}

			/*
			 * Funcion para regresar la hora maxima obtenida desde el servidor
			 * fechaCompleta - cadena con el siguiente formato : YYYY-MM-DD HH:MI:SS
			 * regresa una cadena con la hora en el formato : HH:MMAM o HH:MMPM
			 */
			function getHoraMaximaServer(fechaCompleta)
			{
				var arrFechaHora=fechaCompleta;
				var horaC=arrFechaHora[1].substring(0,5);
				var horaD=horaC.substring(0,2);
				var horaCorrecta="";
				if(parseInt(horaD)<13)
				{	
					horaCorrecta=horaC+'AM';
				}
				else
				{
					horaCorrecta=horaC+'PM';
				}
				return horaCorrecta;
			}
			
			function consultaHecho()
			{
				var numExpedienteConsulta=0;
				if (isIPH == true || isIPH == "true") {
					numExpedienteConsulta=numeroExpedienteId;
				}
				else{
					numExpedienteConsulta=idNumeroExpedienteOp;
				}	
				$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
		    		data: 'idNumeroExpediente='+numExpedienteConsulta,//numeroExpediente,
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			if(parseInt($(xml).find('code').text())==0)
			    		{	
				    	      fechaArribo=$(xml).find('strfechaDeArribo').first().text();
				    	      horaArribo=$(xml).find('strHoraDeArribo').first().text();
				    	      
				    	      $("#idFechaArribo").val(fechaArribo);
				    	      $("#idHoraArribo").val(horaArribo);
				  			  bloqueaCamposHoraArribo(0);
				  			
		    				//seteamos la informacion del hecho
		    				 $(xml).find('hechoDTO').find('tiempo').find('fechaInicio').each(function(){
				    	    	  $("#ingresarTiempoEspecificamentePResponsable").click();
				    	  	    	seteaDatosTiempoEspecifico(xml);
				    	  	    	bloqueaCamposTiempoEspecifico(0);
				    	  	    	tipoTiempoHecho=1;
				    	      });
		    				  
				    	      $(xml).find('hechoDTO').find('tiempo').find('fechaFin').each(function(){
				    	    	  $("#ingresarTiempoLapsoPResponsable").click();
			    	  	    	  seteaDatosTiempoLapso(xml);
			    	  	    	  bloqueaCamposTiempoLapso(0);
			    	  	    	  tipoTiempoHecho=2;
				    	      });
				    	      
				    	      
				    	      var tipoExpediente=$(xml).find('hechoDTO').find('expediente').find('origen').find('idCampo').text();
				    	      
				    	      if(tipoExpediente=='<%=OrigenExpediente.QUERELLA.getValorId()%>'){
				    	    	  $('#rdbTipoQuerella').attr('checked','checked');				    	    	  
				    	      }
				    	      
				    	      if(tipoExpediente=='<%=OrigenExpediente.REPORTE.getValorId()%>'){
				    	    	  $('#rdbTipoReporte').attr('checked','checked');				    	    	  
				    	      }
				    	      
				    	      $(xml).find('hechoDTO').find('tiempo').find('descripcion').each(function(){
				    	    	  $("#ingresarTiempoOtroPResponsable").click();
				    	    	  $("#textNarrativa").val($(xml).find('hechoDTO').find('tiempo').find('descripcion').text());
				    	    	  bloqueaCamposTiempoOtro(0);
			    	  	    	  tipoTiempoHecho=3;
				    	      });
				    	      
				    	      //seteamos el campo de fecha y hora de arribo
				    	      $("#idFechaArribo").val($(xml).find('hechoDTO').find('strfechaDeArribo').text());
				    	      $("#idHoraArribo").val($(xml).find('hechoDTO').find('strHoraDeArribo').text());
				    	      
				    	      habilitaDeshabilitaBotonesTiempo(0);
				    	      
				    	      //seteamos los id de tiempo y lugar
				    	      idTiempo=$(xml).find('tiempo').find('tiempoId').text();
				    	      idLugar=$(xml).find('lugar').find('elementoId').first().text();
				    	      
				    	      $(xml).find('hechoDTO').each(function(){
				    	    	  //seteamos la informacion del lugar
						    	      seteaLugar(xml);
					    	      $("#btnGuardarHechos").hide();
					    	      $("#btnModificarHechos").show();
				    	    	  //seteamos el valor de la descripcion del hecho
				    	    	  $('.jquery_ckeditor').val($(this).find('descNarrativa').text());
				    	    	  narrativaConsultada=$(this).find('descNarrativa').text();
				    	    	  CKEDITOR.on("instanceReady", function (ev) {
				    	  	        var bodyelement = ev.editor.document.$.body;
				    	  	        bodyelement.setAttribute("contenteditable", false);
				    	  	    });
				    	  	    CKEDITOR.replace('editor1');
				    	    	  banderaConsultaHecho=1;
				    	      });
			    		}
		    		}	
		    	});
			}
			
			function bloqueaCamposTiempoOtro(bandera)
		     {
		    	if(parseInt(bandera)==0)
		    	{
		    		$("#textNarrativa").attr("readonly","readonly");
		    	}
		    	else
		    	{
		    		$("#textNarrativa").attr("readonly","");
		    	}
		     }
			
			function habilitaDeshabilitaBotonesTiempo(bandera)
			{
				if(parseInt(bandera)==1)
				{
					$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","");
					$("#ingresarTiempoLapsoPResponsable").attr("disabled","");
					$("#ingresarTiempoOtroPResponsable").attr("disabled","");
				}
				else
				{
					$("#ingresarTiempoEspecificamentePResponsable").attr("disabled","disabled");
					$("#ingresarTiempoLapsoPResponsable").attr("disabled","disabled");
					$("#ingresarTiempoOtroPResponsable").attr("disabled","disabled");
				}
			}
			
			function cambiaEspecifico(){
				  if(!$("#divEspecifico").is(':visible')){
					$("#divLapso").css("display", "none");
					$("#divEspecifico").css("display", "block");  
					$("#divOtro").css("display", "none"); 
					tipoTiempoHecho=1;
				  }
				}
				
					
				function cambiaLapso(){
				  if(!$("#divLapso").is(':visible')){
					$("#divLapso").css("display", "block");
					$("#divEspecifico").css("display", "none");
					$("#divOtro").css("display", "none"); 
					deshabilitaTextDetenido();
					tipoTiempoHecho=2;
				  }
				}
				
						
				function cambiaOtro(){
				  if(!$("#divLapso").is(':visible')||!("#divEspecifico").is(':visible')){
					$("#divLapso").css("display", "none");
					$("#divEspecifico").css("display", "none"); 
					$("#divOtro").css("display", "block");
					tipoTiempoHecho=3;
				  }
				}
				
				
				function creaNuevaNarrativa() {
					if(!$("#divEspecifico").is(':visible')){
						$("#divEspecifico").css("display", "none"); 
						$("#divLapso").css("display", "none");  	 
					}
					if(!$("#divLapso").is(':visible')){
						$("#divLapso").css("display", "none");
						$("#divEspecifico").css("display", "none");  
					}
					cambiaOtro();
				}
			/*
			*Funcion para mandar guardar el nuevo hecho en la BD
			*/
			function guardaHecho()
			{
				if(validaDatosIngresoHecho())
				{
					//guardare el hecho
					var parametrosHechos=extraeDatosIngresarHechos();//obtengo la información a almacenar
					//llamamos al action que guardara el nuevo Hecho
					$.ajax({
						async: false,
						type: 'POST',
						url: '<%= request.getContextPath()%>/ingresarHecho.do',
						data: parametrosHechos+"&idTiempo="+idTiempo+"&idLugar="+idLugar,
						dataType: 'xml',
						success: function(xml){
							if(parseInt(idHecho)==0)
							{
								//Venismo de una insercion
								if(parseInt($(xml).find('code').text())==0)
								{
									$("#btnGuardarHechos").attr("disabled","disabled");	
									alertDinamicoCerrar("Se guardó exitosamente la información",1,xml);
									if (typeof window.parent.cambiaTextoVisor == 'function') {
										window.parent.cambiaTextoVisor($(':radio[name=rdTipoExpediente]:checked').val());
								    }
						    	}
								else
								{
									alertDinamico("Debe capturar la información solicitada en la pestaña: Fecha y hora de los hechos");
								}
							}
							else
							{
								if(parseInt($(xml).find('code').text())==0)
								{
									//venimos de un update y para setear la pantalla mandaremos llamar a la consulta del hecho
									//consultaHecho();
									//$(":enabled").attr('disabled','disabled');
									bloqueaAllCamposLugaresHecho(0);
									$("#btnGuardarHechos").hide();
									$("#btnModificarHechos").attr('disabled','');
									
									if (typeof window.parent.cambiaTextoVisor == 'function') {
										window.parent.cambiaTextoVisor($(':radio[name=rdTipoExpediente]:checked').val());
								    }
									
									alertDinamicoCerrar("Se actualizó exitosamente la información",2,"");
								}
								else
								{
									alertDinamico("No se actualizó la información del hecho");
								}
							}
						}
					});
				}
				else
				{
					alertDinamico("Favor de registrar la descripción de los hechos");
				}
			}
			
			function modificarHecho()
			{
				//habilitamos los campos de tiempo especifico 
				bloqueaCamposTiempoEspecifico(1);
				//habilitamos los campos de tiempo por lapso
				bloqueaCamposTiempoLapso(1);
				//habilita los campos de tiempo Otro
				bloqueaCamposTiempoOtro(1);
				//habilitamos los botones para seleccionar el tipo de tiempo
				habilitaDeshabilitaBotonesTiempo(1);
				//habilitamos todos los campos de lugar
				bloqueaAllCamposLugaresHecho(1);
				//habilitamos el boton de guardar
				if(idHecho!=null && parseInt(idHecho)!=0)
				{
					$("#btnGuardarHechos").show();
					$("#btnGuardarHechos").attr("disabled","");
				}
				$("#areaReferencias").attr("disabled","");
				//habilita Narrativa
				bloqueaCamposHoraArribo(1);
				habilitaNarrativa();
			}
			
			function bloqueaCamposHoraArribo(bandera)
		     {
		    	if(parseInt(bandera)==0)
		    	{
		    	 $('#idFechaArribo').attr('disabled','disabled');
		    	 $('#idHoraArribo').attr('disabled','disabled');
		    	}
		    	else
		    	{
		    		$('#idFechaArribo').attr('disabled','');
		       	 	$('#idHoraArribo').attr('disabled','');
		    	}
		     }
			
			
			/*
			*Funcion que validara los datos del nuevo hecho para poder ingresar el hecho a la BD
			*/
			function validaDatosIngresoHecho()
			{
				var banderaVal=true;
				//lamaremos a cada uno de los metodos que validan las secciones de la vista
				if(escape($('.jquery_ckeditor').val()).length==0)
				{
					banderaVal=false;
				}
				return banderaVal;	
			}
				
			/*
			*
			*/
			function extraeDatosIngresarHechos()
			{
				var parametros="";
				//llmaremos a cada uno de los metodos que estraen la informacion de cada una de 
				//las secciones de la vista para poder ingresar un nuevo Hecho
				
				//agregamos el Id del hecho
				parametros="idHecho="+idHecho;
				//extraemos la descripcion del hecho
				parametros +="&gcDescripcionHecho="+escape($('.jquery_ckeditor').val());
				//recuperamos los datos de lugar, ya trae el & para la union
				parametros += obtenerDatos();
				if(parseInt(tipoTiempoHecho)>0)
				{
					//seteamos el tiempo seleccionado
					parametros += "&tipoTiempoHecho="+tipoTiempoHecho;
				}
				//recuperamos los datos de fecha y tiempo
				if(parseInt(tipoTiempoHecho)==1)//especifico
				{
					parametros += "&"+recuperaDatosTiempoEspecifico(calidad);	
				}
				else if(parseInt(tipoTiempoHecho)==2)//lapso
				{
					parametros += "&"+recuperaDatosTiempoLapso(calidad);	
				}
				else if(parseInt(tipoTiempoHecho)==3)//hecho en el tiempo
				{
					parametros += "&gsNarrativa="+$("#textNarrativa").val();	
				}
				
				//regresamos la cadena con los datos del Hecho
				parametros += "&idUsuario=" + idUsuario;
				parametros += "&origenExpediente=" + $(':radio[name=rdTipoExpediente]:checked').val();
				var numExpedienteConsulta=0;
				if (isIPH == true || isIPH == "true") {					
					parametros += "&numExpediente=" + numeroExpediente;				
					parametros += "&numeroExpedienteId="+numeroExpedienteId;
				}
				else{									
					parametros += "&numExpediente=" + numeroExpediente;				
					parametros += "&numeroExpedienteId="+idNumeroExpedienteOp;
				}							
				parametros += "&fechaArribo="+$("#idFechaArribo").val();
				parametros += "&horaArribo="+$("#idHoraArribo").val();
				return parametros;
			}
			
			/*
			 *Imprime los datos que vienen de la funcion espejoDatosNarrativa de texto narrativa
			 *en la pantalla ingresar probable responsable
			 */
			function imprimeDatosPadreNarrativa(textoNarrativa){
			  $("#textNarrativa").val(textoNarrativa);
			}
			
			function habilitaNarrativa()
			{
				//narrativaConsultada=$(this).find('descNarrativa').text();
				$('.jquery_ckeditor').val(narrativaConsultada);
  	    	  	CKEDITOR.on("instanceReady", function (ev) {
  	  	        	var bodyelement = ev.editor.document.$.body;
  	  	        	bodyelement.setAttribute("contenteditable", true);
  	  	    	});
  	  	    	CKEDITOR.replace('editor1');
			}
			
			/**
			* Funcion que permite parsear la fecha de una cadena, por ejemplo 2012-02-08 08:00:00.0
			* obteniendo la feha en el siguiente formato dd/mm/aaaa, asignando el resultado al idElemento que
			* recibe como parametro.
			* 
			**/
			function formateaFecha(idElemento, fechaHora){
				var fechaFrac = "";
				if(fechaHora != ""){
					fechaFrac = fechaHora.split(" ")[0];

					var dia = fechaFrac.split("-")[2];
					var mes = fechaFrac.split("-")[1];
					var anio = fechaFrac.split("-")[0];
					
					$(idElemento).val(dia + "/" + mes + "/" + anio);					
				}
					
			}
			
			/**
			* Funcion que permite parsear la hora de una cadena, por ejemplo 2012-02-08 08:00:00.0
			* obteniendo la hora hh:mm en el siguiente formato asignando el resultado al idElemento que
			* recibe como parametro.
			**/
			function formateaHora(idElemento, fechaHora){		
				var fechaFrac = "";
				if(fechaHora != ""){
					var horaFrac = fechaHora.split(" ")[1];
					horaFracPos = horaFrac.indexOf(":", 0);
					hora=horaFrac.substring(0,horaFracPos+3);
					$(idElemento).val(hora);	
				}
			}

			//Función para alertDinamicoCerrar
			function alertDinamicoCerrar(textoAlert,estado,xml){
				$("#divAlertTextoCerrar").html(textoAlert);
			    $( "#dialog-AlertCerrar" ).dialog({
					autoOpen: true,
					resizable: false,
					modal: true,
					buttons: {											
						"Aceptar": function() {
							$( this ).dialog( "close" );
							$("#divAlertTextoCerrar").html("");		    		  		
							if(estado==1){
								window.parent.cargaIngresoHecho("Hecho",''+$(xml).find('hechoDTO').find('hechoId').text());
							}
							else if(estado==2){
								window.parent.cerrarVentanaHecho();
							}
							if(flagMenuIntermedio==1){
									window.parent.recargaGridAgenteMP();
							}
						}					    
					}
				});    
			 }
			
		</script>
	</head>
<body>
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

	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div>
	<table width="100%" class="back_pleca_h">
		<tr>
			<td>&nbsp;
				<span id="IPHFuncionalidadOcultaTipoExpediente">
					Tipo de Expediente: 
					<input type="radio" id="rdbTipoDenuncia" value="0" name="rdTipoExpediente" checked="checked"/> Denuncia
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="rdbTipoQuerella" value="1" name="rdTipoExpediente"/> Querella
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="rdbTipoReporte" value="2" name="rdTipoExpediente"/> Reporte
				</span>
			</td>
			<td align="right">
					 <input type="button" value="Modificar" id="btnModificarHechos"  class="btn_Generico"/>
					 <input type="button" value="Guardar" id="btnGuardarHechos" class="btn_Generico"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<div id="tabsHechos"> 
					    <ul>
					        <li id="hechosIngHechosTab"><a href="#tabsHechos-1">Hechos</a></li>
					        <li id="lugarTab"><a href="#tabsHechos-2">Lugar donde ocurrieron los Hechos</a></li>
					        <li id="fechaHoraTab"><a href="#tabsHechos-3">Fecha y hora de los hechos</a></li>
					    </ul>
					    <div id="tabsHechos-1">
					    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
					    		<tr>
						    		<td align="center"><textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea></td>
						    	</tr>
						    </table>
					    </div>
					    <div id="tabsHechos-2">
					    	<table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" id="tableHecho" class="back_hechos">
			    				<tr valign="top">
			    					<td width="20px">&nbsp;</td>
			    					<td><br><jsp:include page="/WEB-INF/paginas/ingresarLugarView.jsp" flush="true"/></td>			    					
			    				</tr>
			    			</table>
					    </div>
					    <div id="tabsHechos-3">
					    <table width="1042px"  height="490px" border="0" cellspacing="0" cellpadding="0" class="back_hechos">
					     <tr>
    						<td>
    						    <table id="datosDetenido" width="1042px"  height="300px" border="0" cellspacing="0" cellpadding="0" id="tableHecho">
			                        <tr valign="top">
			                          <td width="20px">&nbsp;</td>
			                          <td colspan="2" height="20" valign="middle" class="seccion"><br>Fecha y hora de los hechos</td>
			                        </tr>
			                        <tr valign="top">
			                          <td width="20px">&nbsp;</td>
			                          <td align="left" height="18px">
			                              <input type="submit" id="ingresarTiempoEspecificamentePResponsable" value="Espec&iacute;ficamente" class="btn_Generico"/>
			                          </td>
			                          <td width="55%" rowspan="3">
			                            <div id="divEspecifico" style="display: block;">
			                                <jsp:include page="/WEB-INF/paginas/ingresarTiempoEspecificamente.jsp" flush="true"></jsp:include>
			                            </div>
			                            <div id="divLapso" style="display: none;">
			                                <jsp:include page="/WEB-INF/paginas/ingresarTiempoLapso.jsp" flush="true"></jsp:include>
			                            </div>
			                            <div id="divOtro" style="display: none;">
			                            	Evento en el tiempo: <br/>
			                                &nbsp;&nbsp;&nbsp;<textarea rows="7" cols="70" id="textNarrativa" ></textarea>
			                            </div>						                		
			                          </td>
			                        </tr>
			                        <tr valign="top">
			                        <td width="20px">&nbsp;</td>
			                          <td align="left" height="18px">
			                              <input style="width:112px" type="submit" id="ingresarTiempoLapsoPResponsable" value="Lapso" class="btn_Generico"/>
			                          </td>
			                        </tr>
			                        <tr valign="top">
			                        <td width="20px">&nbsp;</td>
			                          <td align="left" height="18px">
			                              <input style="width:112px" type="submit" id="ingresarTiempoOtroPResponsable" value="Otro" class="btn_Generico"/>
			                          </td>
			                        </tr>
	
			                        <tr valign="top">
			                          <td width="20px">&nbsp;</td>
			                          <td colspan="2" height="20" valign="middle" class="seccion"><br>Fecha y hora de arribo</td>
			                        </tr>
			                        <tr valign="top">
			                        <td width="20px">&nbsp;</td>
				                        <td colspan="2">                      	
				                          	<table border="0" summary="Tabla para la fecha y hora de arribo">
				                             	  <tr>
				                             	    <td>Fecha: </td>
				                             	    <td><input type="text" id="idFechaArribo" width="50px"> </td>
				                      	      </tr>
				                             	  <tr>
				                             	    <td>Hora:</td>
				                             	    <td><input type="text" id="idHoraArribo" size="10" class="timeRange" value="8:00 AM"></td>
				                      	      </tr>
				                      	    </table>
				                        </td>
			                        </tr>
			                      </table>
    						</td>
  						</tr>
						</table>
					    </div>
					 </div>
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
		height:'300px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);
</script>
</html>