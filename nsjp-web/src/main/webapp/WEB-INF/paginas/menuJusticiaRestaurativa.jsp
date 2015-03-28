<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
		
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
	
		var idWindowIngresarVictima = 1;	
		var idWindowIngresarTraductor = 1;	
		var idWindowIngresarRepresentanteLegal = 1;
		var idWindowIngresarTestigo = 1;
		var idWindowIngresarTutor = 1;
		var idWindowIngresarProbResponsable = 1;
		var idWindowConsultarProbResponsable = 1;
		var idWindowIngresarContactoDeUnaOrganizacion=1;
		var idWindowConsultarContactoDeUnaOrganizacion=1;
		var idWindowIngresarSentenciadoReinsertar=1;
		var idWindowModificarDenunciante=1;
		var idWindowModificarRepresentanteLegal=1;
		var idWindowModificarTraductor=1;
		var idWindowIngresarHechos=1;
		

		//Objetos
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
		

		
		var idInvolucrado = 100;

		$(document).ready(function() {
			 $( "#tabs" ).tabs();
			 $( "#tabschild" ).tabs();
			 $( "#tabschild2" ).tabs();
			 $( "#tabschild3" ).tabs();
			 $( "#tabschild4" ).tabs();
			 $( "#tabschild5" ).tabs();
			 $( "#tabschild6" ).tabs();
			 $( "#tabschild7" ).tabs();
			 $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			cargaOcupacion();
			
			$("#nuevaVictima").click(creaNuevaVictima);
			$("#nuevoTraductor").click(creaNuevoTraductor);
			$("#nuevoRepresentanteLegal").click(creaNuevoRepresentanteLegal);
			$("#nuevoProbResponsable").click(creaNuevoProbResponsable);
			$("#consultarProbResponsableUno").click(consultarProbResponsable);
			$("#crearDenunciante").click(crearDenunciante);
			$("#nuevoTestigo").click(creaNuevoTestigo);
			$("#nuevoContactoDeUnaOrganizacion").click(creaNuevoContactoDeUnaOrganizacion);
			$("#consultarContactoDeUnaOrganizacionUno").click(consultarContactoDeUnaOrganizacion);
			$("#ingresarSentenciadoReinsertar").click(crearSentenciadoReinsertar);
			$("#consultarDenuncianteUno").click(modificarDenunciante);
			$("#consultarRepresentanteLegalUno").click(modificarRepresentanteLegal);
			$("#consultarTraductorUno").click(modificarTraductor);
			
			$("#ingresarHechos").click(ingresarHechos);
						
			$("#consultaVictimaUno").click(ConsultarVictimaUno);
			$("#consultaVictimaDos").click(ConsultarVictimaDos);
			
			$("#nuevoTutor").click(creaNuevoTutor);

			//objetos
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
			$("#entrevista").click(generaCapturaEntrevista);
			

						
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
						
		});
		
		function creaNuevaVictima() {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindow" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do" width="1050" height="600" />');		
		}
		
		function creaNuevoProbResponsable() {
			
			idWindowIngresarProbResponsable++;
			$.newWindow({id:"iframewindow" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindow" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do" width="1050" height="620" />');		
		}

		function consultarProbResponsable(){
			idWindowConsultarProbResponsable++;
			$.newWindow({id:"iframewindow" + idWindowConsultarProbResponsable, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Probable Responsable", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1000&idCalidad=PROBABLE_RESPONSABLE" width="1050" height="620" />');		
		}

		function crearDenunciante(){
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindow" + idWindowIngresarVictima, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do" width="1040" height="570" />');		
		}

		function modificarDenunciante(){
			
			idWindowModificarDenunciante++;
			$.newWindow({id:"iframewindow" + idWindowModificarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Modificar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowModificarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=DENUNCIANTE" width="1040" height="620" />');		
		}
				
		function creaNuevoTestigo() {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindow" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do" width="1050" height="600" />');		
		}
		
		function consultarTestigo() {
				idWindowIngresarTestigo++;
				$.newWindow({id:"iframewindow" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Testigo", type:"iframe"});
			    $.updateWindowContent("iframewindow" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TESTIGO" width="1050" height="600" />');		
			}

		function creaNuevoContactoDeUnaOrganizacion() {
			
			idWindowIngresarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindow" + idWindowIngresarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindow" + idWindowIngresarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do" width="1050" height="620" />');		
		}
		
		function consultarContactoDeUnaOrganizacion() {
			
			idWindowConsultarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindow" + idWindowConsultarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Consultar contacto de una organización", type:"iframe"});
			$.updateWindowContent("iframewindow" + idWindowConsultarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1001&idCalidad=CONTACTO_ORGANIZACION" width="1050" height="620" />');		
		}


		function creaNuevoTutor() {
			idWindowIngresarTutor++;
			$.newWindow({id:"iframewindow" + idWindowIngresarTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Datos Generales", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
		}

		function creaNuevoTraductor() {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Traductor", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do" width="1050" height="600" />');		
		}	

		function modificarTraductor(){
			
			idWindowModificarTraductor++;
			$.newWindow({id:"iframewindow" + idWindowModificarTraductor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Traductor", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowModificarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TRADUCTOR" width="1040" height="570" />');		
		}
		
		function creaNuevoRepresentanteLegal() {
			idWindowIngresarRepresentanteLegal++;
		$.newWindow({id:"iframewindow" + idWindowIngresarRepresentanteLegal, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Representante Legal", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do" width="1050" height="600" />');		
		}	
		
		function modificarRepresentanteLegal(){
			
			idWindowModificarRepresentanteLegal++;
			$.newWindow({id:"iframewindow" + idWindowModificarRepresentanteLegal, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Representante Legal", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowModificarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');		
		}
	
		function crearSentenciadoReinsertar() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindow" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar nuevo sentenciado a reinsertar", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/IngresarSentenciadoReinsertar.do" width="1050" height="600" />');		
		}
		
		function ConsultarVictimaUno() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindow" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=VICTIMA" width="1050" height="600" />');		
		}
		function ConsultarVictimaDos() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindow" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Consultar Victima", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1300&idCalidad=VICTIMA" width="1050" height="600" />');		
		}

		//Objetos
		 function creaNuevoEquipoDeComputo(){
			idWindowIngresarEquipoDeComputo++;
			$.newWindow({id:"iframewindow" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Ingresar equipo de c&oacute;mputo", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do" width="830" height="340" />');
		}

		 function creaNuevoEquipoTelefonico(){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindow" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do" width="870" height="340" />');
		}

		 
		 function creaNuevaArma(){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindow" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do" width="800" height="380" />');
		}
			 
		 function creaNuevoExplosivo(){
			 idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindow" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do" width="880" height="330" />');
		}

		 function creaNuevaSustancia(){
			 idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindow" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do" width="900" height="330" />');
		}
		 
		 function creaNuevoAnimal(){
			 idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindow" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do" width="900" height="330" />');
		}

		 function creaNuevoVehiculo(){
			 idWindowIngresarVehiculo++;
			$.newWindow({id:"iframewindow" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570,height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do" width="570" height="600" />');
		}
		 
		 function creaNuevaAeronave(){
			 idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindow" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Ingresar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do" width="600" height="530" />');
		}

		 function creaNuevaEmbarcacion(){
			 idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindow" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do" width="600" height="530" />');
		}
		 
		 function creaNuevoInmueble(){
			 idWindowIngresarInmueble++;
			$.newWindow({id:"iframewindow" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar inmueble", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do" width="1050" height="600" />');
		}
		 
		 function creaNuevoNumerario(){
			 idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindow" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do" width="800" height="350" />');
		}
		 
		 function creaNuevoVegetal(){
			 idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindow" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do" width="800" height="350" />');
		}
		 
		 function creaNuevoDocumentoOficial(){
			 idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindow" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do" width="800" height="350" />');
		}
		 
		 function creaNuevaJoya(){
			 idWindowIngresarJoya++;
			$.newWindow({id:"iframewindow" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do" width="850" height="380" />');
		}
		 
		 function creaNuevaObraDeArte(){
			 idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindow" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do" width="850" height="380" />');
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
		
		function generaCapturaEntrevista() {
			$.newWindow({id:"iframewindowCapturaEntrevista", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Captura Entrevista", type:"iframe"});
		    $.updateWindowContent("iframewindowCapturaEntrevista",'<iframe src="<%= request.getContextPath() %>/CapturaEntrevista.do" width="1140" height="400" />');
		    		
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
		  
	</script>
	
	<!--ARCHIVOS PARA MENU DE ARBOL-->        
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/simpletreemenu.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/simpletree.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/menu.css"  type="text/css">
	<style>
	#tabs { height: 400px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 300px; overflow: auto; } 
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
	<div id="tabs">
		<ul>
			        <li><a href="#tabs-1">Involucrado</a></li>
					<li><a href="#tabs-2">Carta de servicio</a></li>
					<li><a href="#tabs-3">Generar citatorio</a></li>
					<li><a href="#tabs-4">Convenio de conformidad</a></li>
					<li><a href="#tabs-5" >Captura entrevista</a></li>
					<li><a href="#tabs-6">Administrar datos de individuo</a></li>
					<li><a href="#tabs-7">Constancia de falta de interes</a></li>
		</ul>
		<div id="tabs-1">
			<div id="tabschild" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild-2">Víctima</a></li>
					
				</ul>
				<div id="tabschild-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		<div id="tabs-2">
			<div id="tabschild2" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild2-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild2-2">Víctima</a></li>
					
				</ul>
				<div id="tabschild2-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild2-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild2-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		<div id="tabs-3">
			<div id="tabschild3" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild3-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild3-2">Victima</a></li>
					
				</ul>
				<div id="tabschild3-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild3-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild3-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		<div id="tabs-4">
			<div id="tabschild4" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild4-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild4-2">Victima</a></li>
					
				</ul>
				<div id="tabschild4-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild4-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild4-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>	
		</div>
		
		<div id="tabs-5">
			<div id="tabschild5" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild5-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild5-2">Victima</a></li>
					
				</ul>
				<div id="tabschild5-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="entrevista">Captura de entrevista</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a></a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild5-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild5-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		
		<div id="tabs-6">
			<div id="tabschild6" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild6-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild6-2">Victima</a></li>
					
				</ul>
				<div id="tabschild6-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild6-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild6-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		
		<div id="tabs-7">
			<div id="tabschild7" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild7-1"><bean:message key="probableResponsable"/></a></li>
					<li><a href="#tabschild7-2">Victima</a></li>
					
				</ul>
				<div id="tabschild7-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Pedro</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild7-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaUno">Victima uno</a></td>
						</tr>
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima dos</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild7-op" >
					<table width="25%"  cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="asociarIndividuo">Asociar Individuo</a></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		
	</div>
</body>
</html>