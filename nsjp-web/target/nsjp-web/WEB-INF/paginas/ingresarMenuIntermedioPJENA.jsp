<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--Hojas de estilos de los combos multiselect-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--script de los combos multiselect-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">

	   /*
		* Identificadores de los frames ingresar calidades
		*/
		var idWindowIngresarVictima = 1;	
		var idWindowIngresarTraductor = 1;	
		var idWindowIngresarRepresentanteLegal = 1;
		var idWindowIngresarTestigo = 1;
		var idWindowIngresarTutor = 1;
		var idWindowIngresarProbResponsable = 1;
		var idWindowIngresarContactoDeUnaOrganizacion = 1;
		var idWindowIngresarSentenciadoReinsertar = 1;
		var idWindowIngresarDenunciante = 1;
		var idWindowConsultarProbResponsable = 1;
		var idWindowConsultarDenunciante = 1;
		var idWindowConsultarTestigo = 1;
		var idWindowConsultarTraductor = 1;		
		var idWindowConsultarContactoDeUnaOrganizacion = 1;
		var idWindowModificarDenunciante = 1;
		//var idWindowModificarRepresentanteLegal=1;
		var idWindowIngresarHechos = 1;
		
	   /*
		* Identificadores de los frames ingresar Objetos y evidencias
		*/
		var idWindowIngresarEquipoDeComputo = 1;
		var idWindowIngresarEquipoTelefonico = 1;
		var idWindowIngresarArma = 1;
		var idWindowIngresarExplosivo = 1;
		var idWindowIngresarSustancia = 1;
		var idWindowIngresarAnimal = 1;
		var idWindowIngresarVehiculo = 1;
		var idWindowIngresarAeronave = 1;
		var idWindowIngresarEmbarcacion = 1;
		var idWindowIngresarInmueble = 1;
		var idWindowIngresarNumerario = 1;
		var idWindowIngresarVegetal = 1;
		var idWindowIngresarDocumentoOficial = 1;
		var idWindowIngresarJoya = 1;
		var idWindowIngresarObraDeArte = 1;
		var idWindowAsociarIndividuo = 1;

		//Variable para los grids
		var reloadGridDelito=false;
		var validaDelito=false;
		
		var idInvolucrado = 100;
		var abrePenal='<%= request.getAttribute("respuestaPenal")%>';
		
		if(abrePenal!=null){
			muestraMenuIntermedio(abrePenal);					
		}
		
		//Comienza funcion on ready del documento
		$(document).ready(function() {

			cargarMenu();
			cargarMenuVictima();
			cargarMenuDenunciante();
			cargarMenuTestigo();
			cargarMenuTraductor();
			cargarMenuQuienDetuvo();
			 $( "#tabs" ).tabs();
			 $( "#tabschild" ).tabs();
			 $( "#tabschild2" ).tabs();
			 $( "#tabschild3" ).tabs();
			 $( "#tabschild4" ).tabs();
			 $( "#tabschild5" ).tabs();
			 $( "#tabschild7" ).tabs();
			
			 $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			cargaOcupacion();

			//Se agrega el evento click para crear nuevos involucrados
			$("#nuevaVictima").click(creaNuevaVictima);
			$("#nuevoTraductor").click(creaNuevoTraductor);
			$("#nuevoRepresentanteLegal").click(creaNuevoRepresentanteLegal);
			$("#nuevoProbResponsable").click(creaNuevoProbResponsable);
			$("#crearDenunciante").click(crearDenunciante);
			$("#nuevoTestigo").click(creaNuevoTestigo);
			$("#nuevoContactoDeUnaOrganizacion").click(creaNuevoContactoDeUnaOrganizacion);
			$("#ingresarSentenciadoReinsertar").click(crearSentenciadoReinsertar);
			$("#nuevoTutor").click(creaNuevoTutor);

			//Se agrega el evento click para consultar involucrados		
			$("#consultarDenuncianteUno").click(modificarDenunciante);
			$("#consultarRepresentanteLegalUno").click(modificarRepresentanteLegal);
			$("#consultarTraductorUno").click(modificarTraductor);
			$("#consultarProbResponsableUno").click(consultarProbResponsable);
			$("#consultarContactoDeUnaOrganizacionUno").click(consultarContactoDeUnaOrganizacion);
			$("#consultaVictimaUno").click(ConsultarVictimaUno);
			$("#consultaVictimaDos").click(ConsultarVictimaDos);


			//se agrega el evento click para ingresar hechos
			$("#ingresarHechos").click(ingresarHechos);

			//Se agrega el evento click para crear nuevos objetos
			$("#nuevoEquipoDeComputo").click(creaNuevoEquipoDeComputo);
			$("#nuevoEquipoTelefonico").click(creaNuevoEquipoTelefonico);
			$("#nuevaArma").click(creaNuevaArma);
			$("#nuevoExplosivo").click(creaNuevoExplosivo);
			$("#nuevaSustancia").click(creaNuevaSustancia);
			$("#nuevoAnimal").click(creaNuevoAnimal);
			$("#nuevoVehiculo").click(creaNuevoVehiculo);
			$("#nuevaAeronave").click(creaNuevaAeronave);
			$("#nuevaEmbarcacion").click(creaNuevaEmbarcacion);
			$("#nuevoInmueble").click(creaNuevoInmueble);
			$("#nuevoNumerario").click(creaNuevoNumerario);
			$("#nuevoVegetal").click(creaNuevoVegetal);
			$("#nuevoDocumentoOficial").click(creaNuevoDocumentoOficial);
			$("#nuevaJoya").click(creaNuevaJoya);
			$("#nuevaObraDeArte").click(creaNuevaObraDeArte);
			$("#asociarIndividuo").click(asociarIndividuo);

			$('a[name*="padre"]').click(function(event){
				var elem = $(this).next();

				if(elem.is('ul')){
					event.preventDefault();
					$('#menu ul:visible').not(elem).slideUp();
					elem.slideToggle();
					}
			});
			
			$("#consultaTestigo").click(consultarTestigo);

			var opNuevaDenuncia=<%=request.getAttribute("idNuevaDenuncia")%>;

			if(opNuevaDenuncia==1){
				$('#tabschild-op').show();
			}else{
				$('#tabschild-op').hide();
			}
			
			$("#cbxEstatusExpediente").multiselect({ 
				multiple: false, 
				header: "Seleccione", 
				position: { 
					my: 'top', 
					at: 'top' 
				},
				selectedList: 1 
			});
			//llenamos los combos de UI e IE de la pestaña de Acciones
			cargaInstitucionesExternas();
			cargaUnidadesInvestigacion();
			mostraDivGenerarOficioCanalizacion(0);
			$("#btnGenerarAcciones").click(muestraDivInformativoCanalizacion);
			$("#btnCanalizaAJR").click(muestraDivInformativoCanalizacion);
			$("#spanGralJAR,#spanGralUI,#spanGralIE,#btnGenerarAcciones").hide();
		});
		//Termina funcion on ready del documento
		
		//Carga el menu de probable responsable con la consulta y un boton para agregar 
		function cargarMenu(){
			$('#tblProbableResponsable').empty();
			//$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable">Ingreso nuevo</a></td></tr>');
			$('#tblProbableResponsable').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoProbResponsable" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>' + '/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=0',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarProbableResponsable(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblProbableResponsable').append(liga);
					  });
		    	  }
		    });
			/*for(var i=0;i<10;i++){
				$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno" onclick="consultarProbableResponsable();">Probable responsable '+ i +'</a></td></tr>');
			}*/
		}


		//Carga el menu de victima con la consulta y un boton para agregar 
		function cargarMenuVictima(){
			$('#tblVictima').empty();
			//$('#tblVictima').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima">Ingreso nuevo</a></td></tr>');
			$('#tblVictima').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaVictima" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblVictima').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=2',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarVictima(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblVictima').append(liga);
					  });
		    	  }
		    });
		}

		
		//Carga el menu de denunciante con la consulta y un boton para agregar 
//		function cargarMenuDenunciante(){
//			$('#tblDenunciante').empty();
//			//$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante">Ingreso nuevo</a></td></tr>');
//			$('#tblDenunciante').append('<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="crearDenunciante" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
//			$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarDenuncianteUno">Denunciante uno</a></td></tr>');
//			$.ajax({
//		    	  type: 'POST',
//		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
//		    	  data: 'calidadDelIndividuo=4',
//		    	  dataType: 'xml',
//		    	  async: false,
//		    	  success: function(xml){
//		    	      $(xml).find('involucradoViewDTO').each(function(){
//			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="consultarDenunciante(' + $(this).find('involucradoId').text() + ');">';
//						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
//						  liga += '</a></td></tr>';
//		    	    	  $('#tblDenunciante').append(liga);
//					  });
//		    	  }
//		    });
//		}
		
		
				//Carga el menu de denunciante con la consulta y un boton para agregar 
		function cargarMenuDenunciante(){
			$('#tblDenunciante').empty();
			$('#tblDenunciante').append('<tr><td ><input type="button" id="crearDenunciante" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarDenuncianteUno">Denunciante uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=4',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarDenunciante(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblDenunciante').append(liga);
					  });
		    	  }
		    });
		}
		

		//Carga el menu de testigo con la consulta y un boton para agregar 
		function cargarMenuTestigo(){
			$('#tblTestigo').empty();
			//$('#tblTestigo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo">Ingreso nuevo</a></td></tr>');
			$('#tblTestigo').append('<tr><td>&nbsp;&nbsp;<input type="button" id="nuevoTestigo" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTestigo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultaTestigo">Testigo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=5',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarTestigo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTestigo').append(liga);
					  });
		    	  }
		    });
		}

		
		//Carga el menu de traductor con la consulta y un boton para agregar 
		function cargarMenuTraductor(){
			$('#tblTraductor').empty();
			//$('#tblTraductor').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor">Ingreso nuevo</a></td></tr>');
			$('#tblTraductor').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTraductor').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a id="consultarTraductorUno">Traductor uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=7',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarTraductor(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTraductor').append(liga);
					  });
		    	  }
		    });
		}

		//Carga el menu de Quien detuvo con la consulta y un boton para agregar 
		function cargarMenuQuienDetuvo(){
			$('#tblQuienDetuvo').empty();
			//$('#tblQuienDetuvo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Ingreso nuevo</a></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a>Quien detuvo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=8',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="consultarQuienDetuvo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblQuienDetuvo').append(liga);
					  });
		    	  }
		    });
		}


		//Abre una nueva ventana de consulta probable responsable  
		function consultarProbResponsable(){
			idWindowConsultarProbResponsable++;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Probable Responsable", type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1000&idCalidad=PROBABLE_RESPONSABLE" width="1050" height="620" />');		
		}


		//Abre una ventana de problable responsable
		function consultarProbableResponsable(idInvolucrado){
			idWindowConsultarProbResponsable++;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=PROBABLE_RESPONSABLE" width="1050" height="620" />');
		}

		//Abre una nueva ventana para consultar una víctima		
		function consultarVictima(idInvolucrado){
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=VICTIMA" width="1050" height="620" />');
		}

		//Abre una nueva ventana de consulta de denunciante		
		function consultarDenunciante(idInvolucrado){
			idWindowConsultarDenunciante++;
			$.newWindow({id:"iframewindowConsultarDenunciante" + idWindowConsultarDenunciante, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Denunciante", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarDenunciante" + idWindowConsultarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=DENUNCIANTE" width="1050" height="620" />');
		}

		//Abre una nueva ventana de consulta de testigo		
		function consultarTestigo() {
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TESTIGO" width="1050" height="600" />');		
		}

		//Abre una nueva ventana de consulta de testigo
		function consultarTestigo(idInvolucrado){
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Testigo", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TESTIGO" width="1050" height="620" />');
		}

		//Abre una nueva ventana de consulta de traductor
		function consultarTraductor(idInvolucrado){
			idWindowConsultarTraductor++;
			$.newWindow({id:"iframewindowConsultarTraductor" + idWindowConsultarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Traductor", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTraductor" + idWindowConsultarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TRADUCTOR" width="1050" height="620" />');
		}

		//Abre una nueva ventana de consulta de organizacion		
		function consultarContactoDeUnaOrganizacion() {
			
			idWindowConsultarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Consultar contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1001&idCalidad=CONTACTO_ORGANIZACION" width="1050" height="620" />');		
		}

		//No existe la pantalla para consulta de quien detuvo
		function consultarQuienDetuvo(idInvolucrado){
			customAlert('aun no hay pantalla para el involucrado QUIEN DETUVO ' + idInvolucrado);
		}

		//Abre una nueva ventana de crear una nuev victima
		function creaNuevaVictima() {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do" width="1050" height="600" />');		
		}

		//Abre una nueva ventana de probable responsable
		function creaNuevoProbResponsable() {
			
			idWindowIngresarProbResponsable++;
			$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do" width="1050" height="620" />');		
		}

		//Abre una nueva ventana de Denunciante
		function crearDenunciante(){
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do" width="1040" height="570" />');		
		}

		//Crea una nueva ventana de testigo
		function creaNuevoTestigo() {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do" width="1050" height="600" />');		
		}

		//Crea una ventana de un nuevo contacti de una organizacion		
		function creaNuevoContactoDeUnaOrganizacion() {
			
			idWindowIngresarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do" width="1050" height="620" />');		
		}

		//crea una nueva ventana de ingresar tutor
		function creaNuevoTutor() {
			idWindowIngresarTutor++;
			$.newWindow({id:"iframewindowTutor" + idWindowIngresarTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Datos Generales", type:"iframe"});
		    $.updateWindowContent("iframewindowTutor" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
		}

		//Abre una nueva ventana de ingresar traductor
		function creaNuevoTraductor() {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Traductor", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do" width="1050" height="600" />');		
		}	


		//Abre una nueva ventana de modificar denunciante		
		function modificarDenunciante(){
			
			idWindowModificarDenunciante++;
			$.newWindow({id:"iframewindowModificarDenunciante" + idWindowModificarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Modificar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarDenunciante" + idWindowModificarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=DENUNCIANTE" width="1040" height="620" />');		
		}

		//Abre una nueva ventana de modificar traductor
		function modificarTraductor(){
			idWindowModificarTraductor++;
			$.newWindow({id:"iframewindowModificarTraductor" + idWindowModificarTraductor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Traductor", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarTraductor" + idWindowModificarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TRADUCTOR" width="1040" height="570" />');		
		}

		
		function creaNuevoRepresentanteLegal() {
			idWindowIngresarRepresentanteLegal++;
		$.newWindow({id:"iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Representante Legal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do" width="1050" height="600" />');		
		}	

		
		function modificarRepresentanteLegal(){
			
			idWindowModificarRepresentanteLegal++;
			$.newWindow({id:"iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Representante Legal", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');		
		}

		
		function crearSentenciadoReinsertar() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar nuevo sentenciado a reinsertar", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/IngresarSentenciadoReinsertar.do" width="1050" height="600" />');		
		}
		

		function ConsultarVictimaUno() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=VICTIMA_PERSONA" width="1050" height="600" />');		
		}

		
		function ConsultarVictimaDos() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindowSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1300&idCalidad=VICTIMA" width="1050" height="600" />');		
		}
			//  Inician funciones para crear ventanas de Objetos
			
		function creaNuevoEquipoDeComputo(){
			idWindowIngresarEquipoDeComputo++;
			$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Ingresar equipo de c&oacute;mputo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do" width="830" height="340" />');
		}

			
		function creaNuevoEquipoTelefonico(){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do" width="870" height="340" />');
		}

		 
		function creaNuevaArma(){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do" width="800" height="380" />');
		}

		
		function creaNuevoExplosivo(){
			 idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do" width="880" height="330" />');
		}

		
		function creaNuevaSustancia(){
			 idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do" width="900" height="330" />');
		}

		
		function creaNuevoAnimal(){
			 idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do" width="900" height="330" />');
		}

		
		function creaNuevoVehiculo(){
			 idWindowIngresarVehiculo++;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do" width="570" height="600" />');
		}

		 
		function creaNuevaAeronave(){
			 idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do" width="600" height="530" />');
		}

		
		function creaNuevaEmbarcacion(){
			 idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do" width="600" height="530" />');
		}

		 
		function creaNuevoInmueble(){
			 idWindowIngresarInmueble++;
			$.newWindow({id:"iframewindowIngresarInmueble" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:0,width:768,height:540,title:"Ingresar inmueble", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarInmueble" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do" width="1050" height="600" />');
		}
		 

		function creaNuevoNumerario(){
			 idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do" width="800" height="350" />');
		}

		 
		function creaNuevoVegetal(){
			 idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do" width="800" height="350" />');
		}
		 

		function creaNuevoDocumentoOficial(){
			 idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do" width="800" height="350" />');
		}
		 

		function creaNuevaJoya(){
			 idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do" width="850" height="380" />');
		}
		 

		function creaNuevaObraDeArte(){
			 idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do" width="850" height="380" />');
		}
			
			
		function ingresarHechos() {
			idWindowIngresarHechos++;
			$.newWindow({id:"iframewindow" + idWindowIngresarHechos, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do" width="1050" height="600" />');		
		}

		
		function asociarIndividuo() {
			idWindowAsociarIndividuo++;
			$.newWindow({id:"iframewindow" + idWindowAsociarIndividuo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowAsociarIndividuo,'<iframe src="<%= request.getContextPath() %>/AsociarIndividuo.do" width="1050" height="600" />');		
		}
	function muestraMenuIntermedio(abrePenal){
		
if(abrePenal=="abrPenal"){""
	$("#tablaAcusePenal").css("display", "block");
	$("#tablaAcuseNoPenal").css("display", "none");

}
		
			}
		
		function cargaOcupacion(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		var option;
		    		
		    		$(xml).find('ocupacion').each(function(){
		    			$('#consultaVictima').append('<li value="' + $(this).find('gcNombre').text() +  '" title="'+ $(this).find('gcDescripcion').text() + '"  style="background:#99C"  >'+ $(this).find('gcDescripcion').text() + '</li>');
		    		});
		    	}
		    		
		    	});
		}

		function generarDocumentoSinCaso() {
			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Denuncia", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do" width="1140" height="400" />');
		   		
		}
		
		function cargaUnidadesInvestigacion(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%= request.getContextPath()%>/cargarPaises.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAUI').empty();
		    		$('#cbxCanalizaAUI').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('catPaises').each(function(){
						$('#cbxCanalizaAUI').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
		    		
					$("#cbxCanalizaAUI").multiselect({ 
						multiple: false, 
						header: "Seleccione", 
						position: { 
							my: 'top', 
							at: 'top' 
						},
						selectedList: 1 
					});
					
		    	}
		    	});
		}
		
		function cargaInstitucionesExternas(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%= request.getContextPath()%>/cargarPaises.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAIE').empty();
		    		$('#cbxCanalizaAIE').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('catPaises').each(function(){
						$('#cbxCanalizaAIE').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
		    		
					$("#cbxCanalizaAIE").multiselect({ 
						multiple: false, 
						header: "Seleccione", 
						position: { 
							my: 'top', 
							at: 'top' 
						},
						selectedList: 1 
					});
		    	}	
		    	});
		}
		
		function revisaEsDelitoGrave(idRadio)
		{
			//recuperamos el valor de la columna gravedad del delito
			var ret = jQuery("#gridCatDelitos").jqGrid('getRowData',idRadio);
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRadio);
			var isGrave="No";
			if(ret.Gravedad!=null)
				isGrave=ret.Gravedad;
			else
				isGrave=retDos.Gravedad;
			if(isGrave=="No")
			{
				mostraDivGenerarOficioCanalizacion(1);
			}
			else{
				mostraDivGenerarOficioCanalizacion(2);
			}
		}
		
		function mostraDivGenerarOficioCanalizacion(idDiv)
		{
			$("#divCanalizaAUI,#divCanalizaAIE,#btnCanalizaAJR").hide();
			if(parseInt(idDiv)==1)
			{
				$("#btnCanalizaAJR").show();
				$("#btnGenerarAcciones").hide();
			}
			else if(parseInt(idDiv)==2)
			{
				$("#divCanalizaAUI").show();
				$("#btnGenerarAcciones").show();
			}
			else if(parseInt(idDiv)==3)
			{
				$("#divCanalizaAIE").show();
				$("#btnGenerarAcciones").show();
			}
		}
		
		function muestraDivInformativoCanalizacion()
		{
			$("#spanGralUI,#spanGralIE,#spanGralJAR,#spanInfoGralUI,#spanInfoGralIE").hide();
			if($("#divCanalizaAUI").is(':visible'))
			{
				$("#spanInfoGralUI").html($("#cbxCanalizaAUI option:selected").text());
				$("#spanInfoGralUI").show();
				$("#spanGralUI").show();
			}
			else if($("#divCanalizaAIE").is(':visible'))
			{
				$("#spanInfoGralIE").html($("#cbxCanalizaAIE option:selected").text());
				$("#spanInfoGralIE").show();
				$("#spanGralIE").show();
			}
			else if($("#btnCanalizaAJR").is(':visible'))
			{
				$("#spanGralJAR").show();
			}
		}
		

		function gridMinisterial(){

			jQuery("#gridDetalleFrmPrincipalTres").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pagerTres1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerTres12',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}
		
		function gridCustodia(){

			jQuery("#gridDetalleFrmPrincipalUno").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pageruno'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerpageruno',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}
		
		function gridPericiales(){

			jQuery("#gridDetalleFrmPrincipalDos").jqGrid({ 
				url:'<%= request.getContextPath()%>', 
				datatype: "xml", 
				colNames:['','', '','', '','','', '','','' ], 
				colModel:[ 	{name:'caso',index:'caso', width:20}, 
							{name:'expediente',index:'expediente', width:20}, 
							{name:'defensor',index:'defensor', width:20}, 
							{name:'detenido',index:'detenido', width:20},
							{name:'delito',index:'delito', width:20},
							{name:'institucion',index:'institucion', width:20},
							{name:'fecha',index:'fecha', width:20},
							{name:'hora',index:'hora', width:20},
							{name:'abogado',index:'abogado', width:20},
							{name:'enterado',index:'enterado', width:20}
						],
				pager: jQuery('#pagerDos'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerDos1',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

			}
	</script>
	
	<!--ARCHIVOS PARA MENU DE ARBOL-->        
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/simpletreemenu.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/simpletree.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/menu.css"  type="text/css">
	
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
		$(function(){
			// Tabs
			$('#tabs').tabs();
				
			//hover states on the static widgets
			$('#dialog_link, ul#icons li').hover(
				function() { $(this).addClass('ui-state-hover'); }, 
				function() { $(this).removeClass('ui-state-hover'); }
			);
				
		});
	</script>
	<!--TERMINA MENU ARBOL-->
	
</head>

<body >

	<div class="ui-layout-north">
	
		<!--BARRA DE HERRAMIENTAS-->
		
		<!--	<ul class="toolbar">-->
		<!--		<div id="menu_head">-->
		<!--			<li id="tbarBtnNuevo" class="first"><span></span>Estatus</li>-->
		<!--			<li id="tbarBtnLectura"><span></span>Canalizar</li>-->
		<!--			<li id="tbarBtnImpresoras"><span></span>Archivo Fisico</li>-->
		<!--			<li id="tbarBtnImpresoras"><span></span>Denuncia o Querella</li>-->
		<!--			-->
		<!--			-->
		<!--		</div>-->
		<!--		<div id="menu_config">-->
		<!--			<li><a href="#">config01</a></li>-->
		<!--			<li><a href="#">config02</a></li>-->
		<!--			<li><a href="#">config03</a></li>-->
		<!--			<li class="last"><a href="#">config04</a></li>-->
		<!--		</div>-->
		<!--	</ul>-->
	
	</div>

	<!--COMIENZAN TABS SUPERIORES (PRINCIPALES)-->
	<div id="tabs">
	
		<ul>
			<li><a href="#tabs-6">Generales</a></li>
			<li><a href="#tabs-1">Involucrado</a></li>
			<li><a href="#tabs-2">Delito</a></li>
			<li><a href="#tabs-3">Hechos</a></li>
			<li><a href="#tabs-4">Objetos y evidencias</a></li>
			<li><a href="#tabs-7">Acciones</a></li>
			<li><a href="#tabs-8" onclick="gridPericiales()">Periciales</a></li>
			<li><a href="#tabs-9" onclick="gridMinisterial()">Policía Ministerial</a></li>
			<li><a href="#tabs-10" onclick="gridCustodia()">Cadena de Custodia</a></li>
			<li><a href="#tabs-5">Notas</a></li>
		</ul>
		
		<!--COMIENZAN TABS INFERIORES DE INDIVIDUO-->
		<div id="tabs-1">		
			<div id="tabschild" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild-1">Denunciante</a></li>
					<li><a href="#tabschild-2">Victima</a></li>
					<li><a href="#tabschild-3"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild-4">Testigo</a></li>
					<li><a href="#tabschild-5">Traductor-Interprete</a></li>
					<li><a href="#tabschild-6">Quien detuvo</a></li>
				</ul>
				
				<div id="tabschild-1">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblDenunciante">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante">Ingresar Denunciante</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarDenuncianteUno">Denunciante uno</a></td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild-2">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblVictima">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima">Ingreso nuevo</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild-3">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblProbableResponsable">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable">Ingreso nuevo</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				
				</div>
				
				<div id="tabschild-4">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblTestigo">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo">Ingresar testigo</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaTestigo">Vicente</a></td> 
						</tr>
					</table>
				</div>
				
				<div id="tabschild-5">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblTraductor">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor">Ingresar Traductor</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarTraductorUno">Traductor Uno</a></td>
						</tr>
					</table>
				</div>
				
				<div id="tabschild-6">
					<table width="25%" cellpadding="0" cellspacing="0" id="tblQuienDetuvo">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Quien detuvo</a></td>
						</tr>
					</table>
				</div>		
			</div>
			
		</div>
		<!--TERMINAN TABS INFERIORES DE INDIVIDUO-->
		
		<!--COMIENZAN TABS INFERIORES DE DELITO-->
		<div id="tabs-2">
		
			<div id="tabschild2" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild2-1">Sentenciado</a></li>				
				</ul>
				
				<div id="tabschild2-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaSentenciado">
								<jsp:include page="seleccionarDelitoView.jsp"></jsp:include>
							</a></td>
						</tr>
					</table>
				</div>
				
			</div>
					
		</div>
		<!--TERMINAN TABS INFERIORES DE DELITO-->
		
		<!--COMIENZAN TAB HECHOS-->
		<div id="tabs-3">
		
			<div id="tabschild3" class="tabs-bottom">
				
				<ul>
					<li><a href="#tabschild3-1">Hechos</a></li>				
				</ul>
				
				<div id="tabschild3-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="ingresarHechos" value="Ingreso Hecho" class="btn_Generico"/></td>
						</tr>
					</table>
				</div>
				
			</div>
					
		</div>
		<!--TERMINA TAB HECHOS-->
		
		<!--COMIENZA TABS OBJETOS Y EVIDENCIAS-->
		<div id="tabs-4">
		
			<div id="tabschild4" class="tabs-bottom">
				
				<ul>
					<li><a href="#tabschild4-7">Veh&iacute;culo</a></li>
					<li><a href="#tabschild4-1">Equipo de c&oacute;mputo</a></li>
					<li><a href="#tabschild4-2">Equipo telef&oacute;nico</a></li>
					<li><a href="#tabschild4-3">Arma</a></li>
					<li><a href="#tabschild4-4">Explosivo</a></li>
					<li><a href="#tabschild4-5">Sustancia</a></li>
					<li><a href="#tabschild4-6">Animal</a></li>					
					<li><a href="#tabschild4-8">Aeronave</a></li>
					<li><a href="#tabschild4-9">Embarcaci&oacute;n</a></li>
					<li><a href="#tabschild4-10">Inmueble</a></li>
					<li><a href="#tabschild4-11">Numerario</a></li>
					<li><a href="#tabschild4-12">Vegetal</a></li>
					<li><a href="#tabschild4-13">Documento oficial</a></li>
					<li><a href="#tabschild4-14">Joya</a></li>
					<li><a href="#tabschild4-15">Obra de arte</a></li>	
					<li><a href="#tabschild4-16">Otros</a></li>		
				</ul>
				
				<!--EQUIPO DE COMPUTO-->
				<div id="tabschild4-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoDeComputo" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Equipo C&oacute;mputo 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Equipo C&oacute;mputo 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--EQUIPO TELEFONICO-->
				<div id="tabschild4-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoEquipoTelefonico" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Equipo telef&oacute;nico 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Equipo telef&oacute;nico 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--ARMA-->
				<div id="tabschild4-3">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaArma" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Arma 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Arma 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--EXPLOSIVO-->
				<div id="tabschild4-4">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoExplosivo" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Explosivos 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Explosivos 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--SUSTANCIA-->
				<div id="tabschild4-5">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaSustancia" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Sustancia 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Sustancia 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--ANIMAL-->
				<div id="tabschild4-6">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoAnimal" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Animal 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Animal 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--VEHICULO-->
				<div id="tabschild4-7">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVehiculo" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Veh&iacute;culo 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Veh&iacute;culo 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--AERONAVE-->
				<div id="tabschild4-8">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaAeronave" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Aeronave 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Aeronave 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--EMBARCACION-->
				<div id="tabschild4-9">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaEmbarcacion" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Embarcaci&oacute;n 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;<a>Embarcaci&oacute;n 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--INMUEBLE-->
				<div id="tabschild4-10">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoInmueble" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Inmueble 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--NUMERARIO-->
				<div id="tabschild4-11">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoNumerario" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Numerario 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Numerario 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--VEGETAL-->
				<div id="tabschild4-12">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoVegetal" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Vegetal 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Vegetal 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--DOCUMENTO OFICIAL-->
				<div id="tabschild4-13">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoDocumentoOficial" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Documento oficial 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Documento oficial 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--JOYA-->
				<div id="tabschild4-14">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaJoya" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Joya 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Joya 2</a></td>
						</tr>
					</table>
				</div>
				
				<!--OBRA DE ARTE-->
				<div id="tabschild4-15">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaObraDeArte" value="Ingreso nuevo" class="btn_Generico"/></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Obra de arte 1</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;<a>Obra de arte 2</a></td>
						</tr>
					</table>
				</div>
						
			</div>
					
		</div>
		<!--TERMINAN TABS OBJETOS Y EVIDENCIAS-->
		
		
		<!--COMIENZA TAB NOTAS-->
		<div id="tabs-5">
		
			<div id="tabschild5" class="tabs-bottom">
			
				<ul>
					<li><a href="#tabschild-5">Notas</a></li>
				</ul>
				
				
				<div id="tabschild5-1">
					<table width="25%" height="520" cellpadding="0" cellspacing="0">
						<tr>
							<td><label>Notas:</label>						
							</td>
						</tr>
						<tr>
							<td><a><textarea name="comentarios" rows="20" cols="90"></textarea></a>						
							</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
					</table>
				</div>
				
			</div>
					
		</div>
		<!--TERMINA TAB NOTAS-->
		
		<!--COMIENZAN TAB GENERALES-->
		<div id="tabs-6">
		
			<table width="650"  height="200px" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="177" style="font-size:14px; background-color:#91B8E8"><strong>Estatus del Expediente:</strong></td>
			    <td width="20" style="font-size:14px; background-color:#91B8E8" >&nbsp;</td>
			    <td width="198" align="right" style="font-size:14px; background-color:#91B8E8"><strong>Resumen de objetos:</strong></td>
			    <td width="38" style="font-size:14px; background-color:#91B8E8">&nbsp;</td>
			    <td width="169" align="right" style="font-size:14px; background-color:#91B8E8"><strong>Resumen de involucrados<em>:</em></strong></td>	
			    <td width="48" style="font-size:14px; background-color:#91B8E8">&nbsp;</td>
			  </tr>
			  <tr>
			    <td><select name="cbxEstatusExpediente" id="cbxEstatusExpediente" style="width:150px">
			      <option>-Seleccione-</option>
			      <option>Temporal</option>
			      <option>Definitivo</option>
			    </select></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Vehículos:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Denunciantes:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Tipo:</strong></span></td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Equipos de cómputo:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Victimas:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right">Denuncia</td>
			    <td><input type="radio" name="generales" id="rbtnDenuncia" value="d" /></td>
			    <td align="right" style="background-color:#FFC">Equipos Telefónicos:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Probables Responsables:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right">Querella</td>
			    <td><input type="radio" name="generales" id="rbtnQuerella" value="q" /></td>
			    <td align="right" style="background-color:#FFC">Armas:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Testigos:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Explosivos:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Traductores/Intérpretes:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right" style="font-size:14px; background-color:#91B8E8"><strong>Canalizado a:</strong></td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Sustancias:</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Quien detuvo:</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralJAR">Justicia Alternativa Restaurativa</span></td>
			    <td><!-- <input type="radio" name="radio" id="rbtnRestaurativa" value="R" />--></td>
			    <td align="right" style="background-color:#FFC">Animales:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralUI">Unidad de Investigación: </span><span id="spanInfoGralUI"></span></td>
			    <td><!-- <input type="radio" name="radio" id="rbtnUnidadDeInvestigacion" value="rbtnUnidadDeInvestigacion" />--></td>
			    <td align="right" style="background-color:#FFC">Aeronaves:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="right"><span id="spanGralIE">Instituci&oacute;n Externa: </span><span id="spanInfoGralIE"></span></td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Embarcación:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Inmueble:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Numerario:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Vegetal:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Documento oficial:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Joya:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Obra de arte:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td align="right" style="background-color:#FFC">Otros:</td>
			    <td>&nbsp;</td>
			    <td align="right">&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
									
		</div>
		<!--TERMINA TAB GENERALES-->
		
		<div id="tabs-7">
			<!--<table width="600" border="0" cellspacing="0" cellpadding="0" id="tablaAcusePenal" style="display: none;">
			  <tr>
			    <td width="250">&nbsp;</td>
			    <td width="350">&nbsp;</td>
			  </tr>
			  <tr>
			    <td  style="background-color:#91B8E8"align="right">
			    	<span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8">
			    		<strong>Generar Documento:</strong>
			    	</span>
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="left">
			    	<input type="button" name="btnAccDenuncia" id="btnAccDenuncia" value="Denuncia" onclick="generarDocumentoSinCaso();" class="btn_Generico"/>
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="left">
					<input type="button" name="btnAccCapturarEntrevista" id="CapturarEntrevista" value="CapturarEntrevista" class="btn_Generico"/>
			   	</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Solicitud:</strong></span></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Oficio de Canalizaci&oacute;n:</strong></span></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td><input type="button" value="Canalizar a Justicia &#10; restaurativa" id="btnCanalizaAJR" class="btn_Generico"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>
			    	<table>
			    		<tr>
			    			<td>
			    				<div id="divCanalizaAUI">
			    					Canalizar a Unidad de Investigaci&oacute;n<br/>
			    					<select id="cbxCanalizaAUI" name="cbxCanalizaAUI">
			    						<option selected="selected" style="height: 20px;">-Seleccione-</option>
			    					</select>
			    				</div>
			    				<div id="divCanalizaAIE">
			    					Canalizar a Instituci&oacute;n Externa:<br/>
			    					<select id="cbxCanalizaAIE">
			    						<option selected="selected"  style="height: 20px;">-Seleccione-</option>
			    					</select>
			    				</div>
			    			</td>
			    		</tr>
			    		<tr>
				    		<td colspan="2"><input type="button" id="btnGenerarAcciones" value="Generar" class="btn_Generico"/></td>
			    		</tr>
			    	</table>
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
		
		--><div id="tabschild7" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild7-1">Documentos</a></li>
					<li><a href="#tabschild7-2">Solicitudes</a></li>
					
				</ul>
				
				<div id="tabschild7-1">
				
					<table width="600" border="0" cellspacing="0" cellpadding="0" id="tablaAcuseNoPenal">
			  <tr>
			    <td width="250">&nbsp;</td>
			    <td width="350">&nbsp;</td>
			  </tr>
			  <tr>
			    <td  style="background-color:#91B8E8"align="right">
			    	<span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8">
			    		<strong>Generar Documento:</strong>
			    	</span>
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="left">
			    	<input type="button" name="btnAccDenuncia" id="btnAccDenuncia" value="Denuncia" onclick="generarDocumentoSinCaso();" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			   <tr>
			    <td align="left">
			    	<input type="button" name="btnAccDenuncia" id="btnAccDenuncia" value="Oficio de Cumplimiento de Acuerdo" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			   <tr>
			    <td align="left">
			    	<input type="button" name="btnAccDenuncia" id="btnAccDenuncia" value="Convenio de Justicia Alternativa" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			  <tr>
			    <td align="left">
			    	</br><input type="button" name="btnAccDenuncia" id="btnTestigo" value="Testimonio" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			  
			  <tr>
			    <td align="left">
			    	</br> <input type="button" name="btnAccDenuncia" id="btnDeclaraciones" value="Declaraciones" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			   <tr>
			    <td align="left">
			    	</br><input type="button" name="btnAccDenuncia" id="btnRecurso" value="Recursos" class="btn_Generico"/>
			    </td>
			    <td></td>
			  </tr>
			  <tr>
			    <td align="left">
					<!--<input type="button" name="btnAccCapturarEntrevista" id="CapturarEntrevista" value="CapturarEntrevista" class="btn_Generico"/>-->
			   	</td>
			    <td>&nbsp;</td>
			  </tr><!--
			  <tr>
			    <td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Solicitud:</strong></span></td>
			    <td>&nbsp;</td>
			  </tr>
			  --><tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			   <tr>
			    <td  style="background-color:#91B8E8"align="right"><span style="border-left:#000000; border-top:#000000 border-bottom-width:4; font-size:14px; background-color:#91B8E8"><strong>Generar Oficio de Canalizaci&oacute;n:</strong></span></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td><input type="button" value="Canalizar a Justicia &#10; restaurativa" id="btnCanalizaAJR" class="btn_Generico"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>
			    	<table>
			    		<tr>
			    			<td>
			    				<div id="divCanalizaAUI">
			    					Canalizar a Unidad de Investigaci&oacute;n<br/>
			    					<select id="cbxCanalizaAUI" name="cbxCanalizaAUI">
			    						<option selected="selected" style="height: 20px;">-Seleccione-</option>
			    					</select>
			    				</div>
			    				<div id="divCanalizaAIE">
			    					Canalizar a Instituci&oacute;n Externa:<br/>
			    					<select id="cbxCanalizaAIE">
			    						<option selected="selected"  style="height: 20px;">-Seleccione-</option>
			    					</select>
			    				</div>
			    			</td>
			    		</tr>
			    		<tr>
				    		<td colspan="2"><input type="button" id="btnGenerarAcciones" value="Generar" class="btn_Generico"/></td>
			    		</tr>
			    	</table>
			    </td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
					
					
				</div>
				
				<div id="tabschild7-2">
					<table width="600" border="0" cellspacing="0" cellpadding="0" id="tablaAcuseNoPenal">
			 
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td><input type="button" name="btnAccDenuncia" id="btnAcc" value="Audio y Video" class="btn_Generico"/></td>
			  </tr>
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br><input type="button" name="btnAccDenuncia" id="btnTestigo" value="Audiencia" class="btn_Generico"/></td>
			  </tr>
			  
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br> <input type="button" name="btnAccDenuncia" id="btnDeclaraciones" value="Ayuda a Unidad de Atenci&oacute;n a V&iacute;ctimas del Delito " class="btn_Generico"/></td>
			  </tr>
			   <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br><input type="button" name="btnAccDenuncia" id="btnRecurso" value="Transcripcion de Audiencia" class="btn_Generico"/></td>
			  </tr>
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br><input type="button" name="btnAccDenuncia" id="btnRecurso" value="Servicios Periciales" class="btn_Generico"/></td>
			  </tr>
			 
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br><input type="button" name="btnAccDenuncia" id="btnRecurso" value="Investigaci&oacute;n Ministerial " class="btn_Generico"/></td>
			  </tr>
			 
			  <tr>
			    <td align="left">
			    	
			    </td>
			    <td></br><input type="button" name="btnAccDenuncia" id="btnRecurso" value="Apoyo Pericial" class="btn_Generico"/></td>
			  </tr>
			</table>
					
				</div>
				
			</div>
		
			
			
		</div>	
		
		<div id="tabs-8">
		</br>
		</br>
		<center>
					<table id="gridDetalleFrmPrincipalDos" align="center"></table>
			<div id="pagerDos"></div>
			</center>
				</div>
		<div id="tabs-9">
		</br>
		</br><center>
					<table id="gridDetalleFrmPrincipalUno" align="center"></table>
			<div id="pagerUno"></div>
			</center>
				</div>
				<div id="tabs-10">
		</br>
		</br>
		<center>
					<table id="gridDetalleFrmPrincipalTres" align="center"></table>
			<div id="pagerTres1"></div>
					</center>
				</div>
	</div>
	<!--TERMINAN TABS SUPERIORES (PRINCIPALES)-->
	
</body>
</html>