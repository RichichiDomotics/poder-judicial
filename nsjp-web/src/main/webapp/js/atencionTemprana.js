
		$.ajaxSetup({ cache: false });
	   /*
		* Identificadores de los frames ingresar calidades
		*/
		//Permite mostrar en pantalla Probable Responsable o Probable participe en base al Rol del usuario
		var etiquetaProbable = '<bean:message key="probableResponsable"/>';
		var cambiarEtiquetaAProbableParticipe = false;
		var contextoPagina = "${pageContext.request.contextPath}";
		var idWindowIngresarVictima = 1;	
		var idWindowIngresarTraductor = 1;	
		var idWindowIngresarQuienDetuvo = 1;	
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
		var idWindowSolicitudTranscripcion =1;
		//var idWindowModificarRepresentanteLegal=1;
		var idWindowIngresarHechos = 1;
		var tipoExpediente = 0;
		
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
		var idWindowIngresarOtros = 1;
		var idWindowAsociarIndividuo = 1;
		var iframewindowAbrirteoria = 1;
		
		//Variable para los grids
		var reloadGridDelito=false;
		var validaDelito=false;

		var idExpediente;
		var idInvolucrado = 100;
		//manda 0 por default al ingresar un probable responsable si viene de atpenal 
		//cambia a 1 si se ingresa desde coordinador AMP
		var muestraDetenido=0;
		var numeroExpediente="";
		var idNumeroExpedienteOp="";
		var idNumeroExpedienteConsul="";
		var idExpedienteop="";
		var isDelitoSaved=false;
		var pantallaSolicitada=0;
		var datosXML;
		var numerogeneralCaso= parent.numerocaso;
		var ingresoDenuncia="false";
		//id para las ventanas relacionadas a cadena de custodia
		var idWindowAsntarRegCadCus=1;

		var criterioOportinidadOp=0;
		var actuacion=0;
		
		var idWindowGenerarNotas=1;
		var idDefensor="";
	    var nombreDefensor="";
	    var num="";
	    var idWindowVisorMedidasCautelaresPJENC=1;
	    
	    var idWindowGenConvenio=1;
		var deshabilitarCampos = false;

		//bandera para deshabilitar los campos en la consutla cuando el usuario es policia ministerial
		var deshabilitarCamposPM=false;
		var idExpedienteDeli="";
		//variable para la carga de documentos de visitaduria
		var primeraVezGridDocumentosDigitales = true;
		
		var numeroCaso="";
		var idRelacionDelito="";
		var existeDelitoGraveEnExpediente = "";
		var flagIndexProcView=0;
		
		var banderaFac=false;
		var idIframe = 0;
		
		//Comienza funcion on ready del documento
		$(document).ready(function() {
			
			idIframe = '<%= request.getParameter("idIframe")%>';
			var auxiliar='<%= request.getParameter("flagIndexProcView")%>';
			
			if(auxiliar!=null){
				flagIndexProcView=auxiliar;
			}
			
			numeroExpediente='<%= request.getAttribute("numeroExpediente")%>';

			if(numeroExpediente == 'null'){
				numeroExpediente='<%= request.getSession().getAttribute("numExpConsul")%>';
			}
			if(numeroExpediente == 'null'){
				numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			}
			pantallaSolicitada='<%= request.getParameter("pantallaSolicitada")%>';
			if(pantallaSolicitada == 'null'){
				pantallaSolicitada='<%= request.getSession().getAttribute("pantallaSolicitada")%>';
			}
			ingresoDenuncia='<%= request.getParameter("ingresoDenuncia")%>';
			
			//ATP, CoordinadorJAR y Facilitaador
			if(pantallaSolicitada == 1 || pantallaSolicitada == 2 || pantallaSolicitada == 5){
				cambiarEtiquetaAProbableParticipe = true;
				etiquetaProbable = '<bean:message key="probableParticipe"/>';
			}
			
			//Configura texto de tabs y botones
			$("#tapProbableResposable").html(etiquetaProbable);
			$("#btnNuevoProbResponsable").val(etiquetaProbable);
			

			//var numExp = '<%= request.getSession().getAttribute("numExpConsul")%>';
			idNumeroExpedienteOp='<%= request.getParameter("idNumeroExpedienteop")%>';
			idExpedienteop='<%= request.getParameter("idExpedienteop")%>';
			if(idExpedienteop=='null')
			{
				idExpedienteop='<%= request.getSession().getAttribute("idExpedienteConsulop")%>';
			}
			idExpedienteDeli=idExpedienteop;
			if(idNumeroExpedienteOp == 'null'){
				idNumeroExpedienteOp='<%= request.getSession().getAttribute("idExpedienteConsul")%>';
			}
			
			idNumeroExpedienteConsul='<%= request.getParameter("idNumeroExpedienteop")%>';
			if(idNumeroExpedienteConsul == 'null'){
				idNumeroExpedienteConsul='<%= request.getParameter("idNumeroExpediente")%>';
			
			}
					
			cargarInvolucradosExpediente(idNumeroExpedienteConsul);
			cargarHechoExpediente(idNumeroExpedienteConsul);
			
			$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul, 
				datatype: "xml",
				colNames:['\u00c1rea del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento', 'Documento', 'Es Parcial'],
				colModel:[ 	{name:'area',index:'da', width:200},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170},
				           	{name:'Documento',index:'documento', width:170},
				           	{name:'EsParcial',index:'esParcial', hidden:true}
							],
				pager: jQuery('#pager1Documentos'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:1100,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					var retd = jQuery("#gridDetalleFrmPrincipal").jqGrid('getRowData',id);
					var titulo = retd.Nombre;
					if(retd.EsParcial){
						noEsParcial = retd.EsParcial.indexOf('false');
						if(noEsParcial > 0){//"No es parcial"
							consultaPDF(id);
						}
						else{//"Es parcial"
			     			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
			    		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId='+id+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
						}
					 }
					
					},
				sortorder: "desc"
			}).navGrid('#pager1Documentos',{edit:false,add:false,del:false});
			$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '500px');


			jQuery("#gridDetalleFrmPrincipalAudiencias").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarAudiencias.do?numeroExpediente='+numeroExpediente, 
				datatype: "xml",
				colNames:['Folio','Estatus','Fecha Creaci�n','Fecha Limite','Instituci�n','Destinatario'],
				colModel:[ 	{name:'folio',index:'folio', width:200},
				           	{name:'estatus',index:'estatus', width:200},
							{name:'fecha',index:'fecha', width:170},							
							{name:'limite',index:'limite', width:400},
				           	{name:'institucion',index:'institucion', width:155}, 
							{name:'destinatario',index:'destinatario', width:255}
				           ],
				pager: jQuery('#pagerAudiencias'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:1100,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					//consultaPDF(id);
					},
				sortorder: "desc"
			}).navGrid('#pagerAudiencias',{edit:false,add:false,del:false});
			$("#gview_gridDetalleFrmPrincipalAudiencias .ui-jqgrid-bdiv").css('height', '500px');
			
			//grid de las alertas
			jQuery("#gridDetalleAlertas").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarAlertas.do?idExpedienteop='+idNumeroExpedienteConsul, 
				datatype: "xml", 
				colNames:['Fecha y Hora','Descripci�n'], 
				colModel:[ 	{name:'FechaHora',index:'fechaHora', width:160}, 
				           	{name:'Descripcion',index:'descripcion', width:450}
						],
				pager: jQuery('#pagerGridDetalleAlertas'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				autowidth: false,
				width:800,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					//consultaPDF(id);
					},
				sortorder: "desc"
			}).navGrid('#pagerGridDetalleAlertas',{edit:false,add:false,del:false});
			
			
			//cargarObjetosExpediente('<%= request.getParameter("idNumeroExpediente")%>');
			ocultaMuestraTabVisor("tabTabsCriterio",0);

			 $( "#tabs" ).tabs();
			 $( "#tabschild" ).tabs();
			 $( "#tabschild2" ).tabs();
			 $( "#tabschild3" ).tabs();
			 $( "#tabschild4" ).tabs();
			 $( "#tabschild5" ).tabs();
			 $( "#tabschild7" ).tabs();
			 $( "#tabschild9" ).tabs();
			 $( "#tabschild16" ).tabs();
			 $('#idTeoriaCaso').hide();
			 $('#idbotoncarpeta').hide();
			 
			 $('#tdCbxAjentesCoorUI').hide();
			 $('#tdCbxAjentesCoorUI1').hide();
			 
			 $('#tdCbxAjentesCoorJAR').hide();
			 $('#tdCbxAjentesCoorJAR1').hide();
			 
			 $('#idAsignarAgenteMp').hide();
			 $("#idAsignarFacilitador").hide();
			 $("#idReasignarFacilitador").hide();
			 $("#idRadiosBUt").hide();
			 ocultaMuestraTabVisor("tabTabsVisitaduria",0);
			 			 
			// opcion uno es para la pantalla de Atencion temprana penal
			if(pantallaSolicitada==1){
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsQuienDetuvo",0);
				
				ocultaMuestraTabVisor("tabTabsCriterio",0);
				ocultaMuestraTabVisor("tabTabsAlertas",0);
				ocultaMuestraTabVisor("tabTabsAmparos",0);
				
				$('#btnAccAudioVideo').hide();
				$('#btnAccAudiencia').hide();
				$('#btnAccTranscripcionAudiencia').hide();
				$('#btnAccServiciosPericiales').hide();
				$('#btnAccInvestigacionMinisterial').hide();
				$('#btnAccApoyoPericial').hide();
				$('#btnAccGenerarConvenio').hide();
				cargaActuaciones("1");
				
				ocultaSeccionDeDelitos();
				
			}else if(pantallaSolicitada==2){//coordinador JAR
				
				ocultaMuestraTabVisor("tabTabsCriterio",0);
				ocultaMuestraTabVisor("tabTabsAlertas",0);
				ocultaMuestraTabVisor("tabTabsAmparos",0);
				
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				cargaActuaciones("2");
				$('#tdCbxAjentesCoorJAR').show();
				$('#tdCbxAjentesCoorJAR1').show();
				$("#idAsignarFacilitador").show();
				banderaFac='<%= request.getSession().getAttribute("banderaFac")%>';
				if(banderaFac == 'true'){				
					$("#idAsignarFacilitador").hide();
					$("#idReasignarFacilitador").show();
					<%session.setAttribute("banderaFac", false);%>					
				}
				else{
					$("#idAsignarFacilitador").show();	
					$("#idReasignarFacilitador").hide();
				}
				cargaAgenteJAR();
			}else if(pantallaSolicitada==3){//agentemp
				muestraDetenido=1;				
				ocultaMuestraTabVisor("tabTabsPeri",1);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",1);
				ocultaMuestraTabVisor("tabTabsCriterio",1);
				cargaActuaciones("3");
				$("#tabs").tabs("option", "selected", 2);
				 $('#tdCbxAjentesCoorUI').show();
				 $('#tdCbxAjentesCoorUI1').show();
				 $('#idAsignarAgenteMp').show();
				 $('#btnAudManJud').hide();
				 $('#btnCadCusRegEslabones').hide();
				 $('#btnCadCusRepEvidencias').hide();
				 $('#btnCadCusElabOficio').hide();
				 $('#btnCadCusAdmDestino').hide();
				 cargaAgenteMp();
				 cargaActuacionesPolMinisterial();
				 
			}else if(pantallaSolicitada==4){//coordinador
				muestraDetenido=1;
				ocultaMuestraTabVisor("tabTabsPeri",1);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				ocultaMuestraTabVisor("tabTabsAudiencias",1);
				ocultaMuestraTabVisor("tabTabsCriterio",1);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				cargaActuaciones("3");
				//cargaAgenteMp();
				$("#tabs").tabs("option", "selected", 2);
				 $('#idTeoriaCaso').show();
				 $('#idbotoncarpeta').show();
				 $('#btnAudManJud').hide();
				 $('#btnCadCusRegEslabones').hide();
				 $('#btnCadCusRepEvidencias').hide();
				 $('#btnCadCusElabOficio').hide();
				 $('#btnCadCusAdmDestino').hide();
				//cargamos las actuaciones para la tab de policia ministerial
				 cargaActuacionesPolMinisterial();
			}
			else if(pantallaSolicitada==5){//facilitador
				ocultaMuestraTabVisor("tabTabsCriterio",0);
				ocultaMuestraTabVisor("tabTabsAlertas",0);
				ocultaMuestraTabVisor("tabTabsAmparos",0);
				
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",0);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				// $("#idRadiosBUt").show();
				cargaActuaciones("2");
			}
			else if(pantallaSolicitada==6){//policia ministerial
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				ocultaMuestraTabVisor("tabTabsPeri",0);
				ocultaMuestraTabVisor("tabTabsPolMin",1);
				ocultaMuestraTabVisor("tabTabsAudiencias",0);
				ocultaMuestraTabVisor("tabTabsCadCus",0);
				ocultaMuestraTabVisor("tabTabsDocs",1);
				//ocultaMuestraTabVisor("tabschild9-2",0);
				$(".btn_modificar").hide();
				$(".btn_grande").hide();
				$("#cbxAccionesTab9").attr("disabled","");
				$("#idInvestiga").attr("disabled","");
				cargaActuaciones("2");
				 cargaActuacionesPolMinisterial();
			}else if(pantallaSolicitada==7){//Coordinador Visitador
				
				var Area='<%= request.getParameter("idArea")%>';
				
				if(Area=="55" || Area=="44" || Area=="11"){
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",0);
					ocultaMuestraTabVisor("tabTabsPolMin",0);
					ocultaMuestraTabVisor("tabTabsAudiencias",0);
					ocultaMuestraTabVisor("tabTabsCadCus",0);
					ocultaMuestraTabVisor("tabTabsDocs",0);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",1);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
				}else{
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",1);
					ocultaMuestraTabVisor("tabTabsPolMin",1);
					ocultaMuestraTabVisor("tabTabsAudiencias",1);
					ocultaMuestraTabVisor("tabTabsCadCus",1);
					ocultaMuestraTabVisor("tabTabsDocs",1);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
					//ocultaMuestraTabVisor("tabschild9-2",0);
				}
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				cargaActuaciones("2");
				cargaInformacionDeResumenVisitaduira();
			}else if(pantallaSolicitada==8){//Visitador
				//Carga los documentos adjuntos que se adjuntaran a la solicitud de periciales
				cargaGridDocumentosDigitales();
				var Area='<%= request.getParameter("idArea")%>';
				var estatusVisitador='<%= EstatusExpediente.AUDITANDO.getValorId() %>';
				regsitraEstatus(estatusVisitador);
				if(Area=="55" || Area=="44" || Area=="11"){
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",0);
					ocultaMuestraTabVisor("tabTabsPolMin",0);
					ocultaMuestraTabVisor("tabTabsAudiencias",0);
					ocultaMuestraTabVisor("tabTabsCadCus",0);
					ocultaMuestraTabVisor("tabTabsDocs",0);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",1);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
				}else{
					deshabilitarCamposPM=true;
					deshabilitarCampos=true;
					ocultaMuestraTabVisor("tabTabsPeri",1);
					ocultaMuestraTabVisor("tabTabsPolMin",1);
					ocultaMuestraTabVisor("tabTabsAudiencias",1);
					ocultaMuestraTabVisor("tabTabsCadCus",1);
					ocultaMuestraTabVisor("tabTabsDocs",1);
					 ocultaMuestraTabVisor("tabTabsVisitaduria",1);
					 ocultaMuestraTabVisor("tabTabsCriterio",0);
					$(".btn_modificar").hide();
					$(".btn_grande").hide();
					//ocultaMuestraTabVisor("tabschild9-2",0);
				}
				deshabilitarCamposPM=true;
				deshabilitarCampos=true;
				cargaActuaciones("2");
				cargaInformacionDeResumenVisitaduira();
				//Carga los documentos adjuntos que se adjuntaran a la solicitud de periciales
				cargaGridDocumentosDigitales();
				
			}
			if(ingresoDenuncia=='true'){
				//consulta
				//$('#btnAccAudioVideo').hide();
				consultarNotas();
				var titulo='<%= request.getSession().getAttribute("numExpConsul")%>';
				if(titulo == undefined || titulo == null || titulo == "null"){
					titulo=numeroExpediente;
				}
				numeroCaso='<%= request.getSession().getAttribute("numeroCasoConsul")%>';
				titulo=titulo+" No. Caso: "+numeroCaso;
				window.parent.tituloVentana(titulo);
			}
			else
			{
				//insercion
				numeroCaso='<%= request.getParameter("numeroGeneralCaso")%>';
			}
			//cargarMenu();
			//cargarMenuVictima();
			//cargarMenuDenunciante();
			//cargarMenuTestigo();
			//cargarMenuTraductor();
			//cargarMenuQuienDetuvo();
						
			 $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			cargaOcupacion();

			//Se agrega el evento click para crear nuevos involucrados
			$("#nuevaVictima").click(creaNuevaVictima);
			$("#nuevoTraductor").click(creaNuevoTraductor);
			$("#quienDetuvo").click(creaQuienDetuvo);
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
			$("#nuevoOtros").click(creaNuevoOtros);
			$("#asociarIndividuo").click(asociarIndividuo);
			$("#btnAdminMediCaute").click(mostrarVentanaInvolucradosCausa);
			
			$("#btnTranscripcionAudiencia").click(muestraSolicitudTranscripcion);
			
		
			//deshabilitamos el boton de Denuncia del tab Acciones
			$("#btnAccDenuncia").hide();//.attr("disabled", "disabled");
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
				$('#tdCbxAjentesCoorUI1').hide();
				$('#tdCbxAjentesCoorUI').hide();
				$('#idAsignarAgenteMp').hide();
				
				
				
			}else{
				$('#tabschild-op').hide();
			}
			
//			$("#cbxEstatusExpediente").multiselect({ 
//				multiple: false, 
//				header: "Seleccione", 
//				position: { 
//					my: 'top', 
//					at: 'top' 
//				},
//				selectedList: 1 
//			});
			
//			$("#cbxAccionesTab").multiselect({ 
//				multiple: false, 
//				header: "Seleccione", 
//				position: { 
//					my: 'top', 
//					at: 'top' 
//				},
//				selectedList: 1,
//				close: function (event,ui){
//					seleccionaActuacion();} 
//			});

$("#cbxAccionesTab").change(function(e){
	seleccionaActuacion();
}); 
			//cargamos el combo para las actuaciones de policia ministerial
			$("#cbxAccionesTab9").change(function(e){
				seleccionaActuacionPolMin();
			}); 
			

			//$("#tdCbxAccionesTab").hide();
			//llenamos los combos de UI e IE de la pesta�a de Acciones
			cargaInstitucionesExternas();
			cargaUnidadesInvestigacion();
			mostraDivGenerarOficioCanalizacion(0);
			$("#btnGenerarAcciones").click(muestraDivInformativoCanalizacion);
			$("#btnCanalizaAJR").click(muestraDivInformativoCanalizacion);
			$("#spanGralJAR,#spanGralUI,#spanGralIE,#btnGenerarAcciones").hide();

			var abrePenal='<%= request.getAttribute("respuestaPenal")%>';
			//prueba, 
			var abrePenal="";
			if(abrePenal!=null){
				//muestraMenuIntermedio(abrePenal);					
			}
			$("#btnGardadoDefini").click(validaGuardadoDefinitivo);
			$("#btnAccQuerella").hide();
			//seteamos los listener de los radios para la relacion de Delitos por Person o por Delito
			$("#rdbMenuInterRelDelXPersona").bind("click",ocultaMuestraTblsRelacionarDelitos);
			$("#rdbMenuInterRelDelXDelito").bind("click",ocultaMuestraTblsRelacionarDelitos);
			$("#rdbMenuInterRelDelXTodos").bind("click",ocultaMuestraTblsRelacionarDelitos);
			datosGenerales();
			
			//Seteo listener cadena de custodia
			$("#btnCadCusNuevaCadCus").click(asentarRegCadenaCustodia);
			$("#btnCadCusConsultaCadCus").click(consultarRegCadenaCustodia);
			//FIN Seteo listener cadena de custodia
			//consultamos las notas del expediente
			if(idNumeroExpedienteOp != '')
			{
				consultarNotas();
			}
			/*SECCION PARA DECLARAR LOS GRIDS DE LOS OBJETOS EN EL VISOR INTERMEDIO*/
			jQuery("#gridObjsVehiculo").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>', 
				datatype: "xml", 
				colNames:['Veh�culo','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Vehiculo',index:'vehiculon', width:100}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:false}, 
						],
				pager: jQuery('#pagerGridObjsVehiculo'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"VEH�CULOS",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsVehiculo',
				ondblClickRow: function(id){
					consultarVehiculo(id);
				},
				sortorder: "desc"
			});
			//$("#gridObjsVehiculo").trigger("reloadGrid");
			
			//grid de arma
			jQuery("#gridObjsArma").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Arma','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Arma',index:'arman', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsArma'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"ARMA",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsArma',
				ondblClickRow: function(id){
					consultarArma(id);
					},
				sortorder: "desc"
			});
			
			//Grid de equipo de computo
			jQuery("#gridObjsEquipoComputo").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Equipo de C�mputo','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'EquipoComputo',index:'equipoComputon', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsEquipoComputo'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"EQUIPO DE COMPUTO",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsVehiculo',
				ondblClickRow: function(id){
					consultarEquipoComputo(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Equipo Telefonico
			jQuery("#gridObjsEquipoTelefonico").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Equipo Telefonico','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'EquipoTelefonico',index:'equipoTelefonicon', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsEquipoTelefonico'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"EQUIPO TELEFONICO",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsEquipoTelefonico',
				ondblClickRow: function(id){
					consultarEquipoTelefonico(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Explosivo
			jQuery("#gridObjsExplosivo").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Explosivo','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Explosivo',index:'explosivon', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsExplosivo'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"EXPLOSIVO",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsExplosivo',
				ondblClickRow: function(id){
					consultarExplosivo(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Sustancia
			jQuery("#gridObjsSustancia").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Sustancia','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Sustancia',index:'sustancian', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsSustancia'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"SUSTANCIA",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsSustancia',
				ondblClickRow: function(id){
					consultarSustancia(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Animal
			jQuery("#gridObjsAnimal").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Animal','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Animal',index:'animaln', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsAnimal'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"ANIMAL",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsAnimal',
				ondblClickRow: function(id){
					consultarAnimal(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Aeronave
			jQuery("#gridObjsAeronave").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Aeronave','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Aeronave',index:'aeronaven', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsAeronave'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"AERONAVE",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsAeronave',
				ondblClickRow: function(id){
					consultarAeronave(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Embarcacion
			jQuery("#gridObjsEmbarcacion").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Embarcacion','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Embarcacion',index:'embarcacionn', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsEmbarcacion'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"EMBARCACION",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsEmbarcacion',
				ondblClickRow: function(id){
					consultarEmbarcacion(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Inmueble
 			jQuery("#gridObjsInmueble").jqGrid({ 
 				url:'local', 
 				datatype: "xml", 
 				colNames:['Inmueble','Folio de cadena de custodia', 'No. de casos asociados'], 
 				colModel:[ 	{name:'Inmueble',index:'inmueblen', width:150}, 
 							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
 							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
 						],
 				pager: jQuery('#pagerGridObjsInmueble'),
 				rowNum:10,
 				rowList:[10,20,30,40,50,60],
 				width:600,
 				caption:"INMBUEBLE",
 				sortname: 'Clave',
 				viewrecords: true,
 				id: 'gridObjsInmueble',
 				onSelectRow: function(id){
 					consultarInmueble(id);
 					},
 				sortorder: "desc"
 			});
			
			//Grid de Numerario
			jQuery("#gridObjsNumerario").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Numerario','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Numerario',index:'numerarion', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsNumerario'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"NUMERARIO",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsNumerario',
				ondblClickRow: function(id){
					consultarNumerario(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Vegetal
			jQuery("#gridObjsVegetal").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Vegetal','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Vegetal',index:'vegetaln', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsVegetal'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"VEGETAL",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsVegetal',
				onSelectRow: function(id){
					consultarVegetal(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Documento Oficial
			jQuery("#gridObjsDocumentoOficial").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Documento oficial','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'DocumentoOficial',index:'documentoOficialn', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsDocumentoOficial'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"DOCUMENTO OFICIAL",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsDocumentoOficial',
				ondblClickRow: function(id){
					consultarDocumentoOficial(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Joya
			jQuery("#gridObjsJoya").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Joya','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Joya',index:'joyan', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsJoya'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"JOYA",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsJoya',
				ondblClickRow: function(id){
					consultarJoya(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Obra de Arte
			jQuery("#gridObjsObraDeArte").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Obra de arte','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'ObraDeArte',index:'obraDeArten', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsObraDeArte'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"OBRA DE ARTE",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsObraDeArte',
				ondblClickRow: function(id){
					consultarObraDeArte(id);
					},
				sortorder: "desc"
			});
			
			//Grid de Otros
			jQuery("#gridObjsOtros").jqGrid({ 
				url:'local', 
				datatype: "xml", 
				colNames:['Otros','Folio de cadena de custodia', 'No. de casos asociados'], 
				colModel:[ 	{name:'Otros',index:'otrosn', width:150}, 
							{name:'FolioCadCus',index:'folioCadCus', width:150}, 
							{name:'NoExpedientes',index:'noExpedientes', width:200,hidden:true}, 
						],
				pager: jQuery('#pagerGridObjsOtros'),
				rowNum:10,
				rowList:[10,20,30,40,50,60],
				width:600,
				caption:"OTROS",
				sortname: 'Clave',
				viewrecords: true,
				id: 'gridObjsOtros',
				ondblClickRow: function(id){
					consultarOtros(id);
					},
				sortorder: "desc"
			});
			
			/*FIN - SECCION PARA DECLARAR LOS GRIDS DE LOS OBJETOS EN EL VISOR INTERMEDIO*/
						
			//Instruccion pensada solo para el caso de policia ministerial
			if(deshabilitarCampos == true){
				$("#rdbMenuInterRelDelXTodos").attr("disabled","");
				$("#rdbMenuInterRelDelXPersona").attr("disabled","");
				$("#rdbMenuInterRelDelXDelito").attr("disabled","");
				$(":enabled").attr('disabled','disabled');
				$("#cbxAccionesTab").attr('disabled','');
				$("#botonGuardarNotas").show();
				$("#botonGuardarNotas").attr('disabled','');
				$("#idRadiosBUt").hide();
				$('#cbxAccionesTab').empty();
				$('#cbxAccionesTab').append('<option value="-1">-Seleccione-</option>');
				cargaActuaciones("2");
			}
			$("#btnTemporal").attr("disabled","");
			
			if(pantallaSolicitada==6){//policia ministerial
				$("#cbxAccionesTab9").attr("disabled","");
				$("#idInvestiga").attr("disabled","");
				cargaActuacionesPolMinisterial();
			}
			
			
			tipoExpediente = consultaTipoExpediente();
			
			if(tipoExpediente == '<%=OrigenExpediente.REPORTE.getValorId()%>')
				cambiaTextoVisor(2);
			else
				cambiaTextoVisor(1);	
			
			

		});
		//Termina funcion on ready del documento
		
		function cerrarVentanaDocumento(){
			var pantalla ="iframewindowGenerarDocumento";
			$.closeWindow(pantalla);
		}
		
		//SECCION para llenar los grids de los objetos en el visor intermedio
		function consultaGridVehiculosVisor()
		{	
			jQuery("#gridObjsVehiculo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEHICULO.getValorId() %>',datatype: "xml" });
			$("#gridObjsVehiculo").trigger("reloadGrid");
		}
		
		function consultaGridArmasVisor()
		{	
			jQuery("#gridObjsArma").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.ARMA.getValorId() %>',datatype: "xml" });
			$("#gridObjsArma").trigger("reloadGrid");
		}
		
		function consultaGridEquiposComputoVisor()
		{	
			jQuery("#gridObjsEquipoComputo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>',datatype: "xml" });
			$("#gridObjsEquipoComputo").trigger("reloadGrid");
		}
		
		function consultaGridEquipoTelefonicoVisor()
		{	
			jQuery("#gridObjsEquipoTelefonico").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EQUIPO_TELEFONICO.getValorId() %>',datatype: "xml" });
			$("#gridObjsEquipoTelefonico").trigger("reloadGrid");
		}
		
		function consultaGridExplosivoVisor()
		{	
			jQuery("#gridObjsExplosivo").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EXPLOSIVO.getValorId() %>',datatype: "xml" });
			$("#gridObjsExplosivo").trigger("reloadGrid");
		}
		
		function consultaGridSustanciaVisor()
		{	
			jQuery("#gridObjsSustancia").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.SUSTANCIA.getValorId() %>',datatype: "xml" });
			$("#gridObjsSustancia").trigger("reloadGrid");
		}
		
		function consultaGridAnimalVisor()
		{	
			jQuery("#gridObjsAnimal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.ANIMAL.getValorId() %>',datatype: "xml" });
			$("#gridObjsAnimal").trigger("reloadGrid");
		}
		
		function consultaGridAeronaveVisor()
		{	
			jQuery("#gridObjsAeronave").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.AERONAVE.getValorId() %>',datatype: "xml" });
			$("#gridObjsAeronave").trigger("reloadGrid");
		}
		
		function consultaGridEmbarcacionVisor()
		{	
			jQuery("#gridObjsEmbarcacion").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.EMBARCACION.getValorId() %>',datatype: "xml" });
			$("#gridObjsEmbarcacion").trigger("reloadGrid");
		}
		
		function consultaGridInmuebleVisor()
		{	
			jQuery("#gridObjsInmueble").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.INMUEBLE.getValorId() %>',datatype: "xml" });
			$("#gridObjsInmueble").trigger("reloadGrid");
		}
		
		function consultaGridNumerarioVisor()
		{	
			jQuery("#gridObjsNumerario").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.NUMERARIO.getValorId() %>',datatype: "xml" });
			$("#gridObjsNumerario").trigger("reloadGrid");
		}
		
		function consultaGridVegetalVisor()
		{	
			jQuery("#gridObjsVegetal").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.VEGETAL.getValorId() %>',datatype: "xml" });
			$("#gridObjsVegetal").trigger("reloadGrid");
		}
		
		function consultaGridDocumentoOficialVisor()
		{	
			jQuery("#gridObjsDocumentoOficial").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>',datatype: "xml" });
			$("#gridObjsDocumentoOficial").trigger("reloadGrid");
		}
		
		function consultaGridJoyaVisor()
		{	
			jQuery("#gridObjsJoya").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.JOYA.getValorId() %>',datatype: "xml" });
			$("#gridObjsJoya").trigger("reloadGrid");
		}
		
		function consultaGridObraDeArteVisor()
		{	
			jQuery("#gridObjsObraDeArte").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.OBRA_DE_ARTE.getValorId() %>',datatype: "xml" });
			$("#gridObjsObraDeArte").trigger("reloadGrid");
		}
		
		function consultaGridOtrosVisor()
		{	
			jQuery("#gridObjsOtros").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaObjetosGridVisorXTipo.do?numeroExpediente='+numeroExpediente+'&tipoObjeto=<%= Objetos.OTRO.getValorId() %>',datatype: "xml" });
			$("#gridObjsOtros").trigger("reloadGrid");
		}
		//FIN SECCION para llenar los grids de los objetos en el visor intermedio
		
		/*
		*Funcion para madar consultar la informacion de resumen de visitaduria
		*/
		function cargaInformacionDeResumenVisitaduira()
		{
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarInfoResumenVisitaduria.do',
	    		data: 'idExpediente='+idNumeroExpedienteConsul,
	    		dataType: 'xml',
	    		success: function(xml){
	    			$(xml).find('AuditoriaDTO').each(function(){
	    				$("#spanNombrDuenoExpAud").text($(this).find('nombreCompleto').text());
	    				$("#spanNumExpAud").text($(this).find('numeroExpediente').find('numeroExpediente').text());
	    				$("#spanAreaExpAud").text($(this).find('area').find('nombre').text());
	    				$("#spanFechaExpAud").text($(this).find('fechaAperturaNumeroExp').text());
	    				$("#spanEstatusExpAud").text($(this).find('estatus').find('valor').text());
	    			});
	    		}
	    	});
		}
		
		/*
		*Funcion que dispara el Action para consultar el los agentes mp
		*/		
	    function cargaAgenteMp(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarAgentesUI.do',
	    		data: '',
	    		dataType: 'xml',
	    		success: function(xml){
	    			var option;
	    			$(xml).find('funcionarioDTO').each(function(){
	    				$('#cbxAjentesCoorUI').append('<option value="' + $(this).find('claveFuncionario').text() + '">'+ 
	    	    				                        $(this).find('nombreFuncionario').text() +" "+
	    	    				                        $(this).find('apellidoPaternoFuncionario').text() +" "+
	    	    				                        $(this).find('apellidoMaternoFuncionario').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
	    /*
		*Funcion que dispara el Action para consultar el los agentes mp
		*/		
	    function cargaAgenteJAR(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarAgentesJAR.do',
	    		data: '',
	    		dataType: 'xml',
	    		success: function(xml){
	    			var option;
	    			$(xml).find('funcionarioDTO').each(function(){
	    				$('#cbxAjentesCoorJAR').append('<option value="' + $(this).find('claveFuncionario').text() + '">'+ $(this).find('nombreFuncionario').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
		/****** listener cadena de Custodia  ****/
		function asentarRegCadenaCustodia()
		{
			idWindowAsntarRegCadCus++;
			$.newWindow({id:"iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Asentar registro de cadena de custodia", type:"iframe"});
		    $.updateWindowContent("iframewindowAsntrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=0&numeroExpediente='+numeroExpediente +'&operacion=1 " width="1000" height="600" />');
		}
		
		function consultarRegCadenaCustodia()
		{
			idWindowAsntarRegCadCus++;
			$.newWindow({id:"iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus, statusBar: true, posx:200,posy:50,width:1000, height:600,title:"Cadena de custodia", type:"iframe"});
		    $.updateWindowContent("iframewindowCnsltrRegCadCus" + idWindowAsntarRegCadCus,'<iframe src="<%= request.getContextPath() %>/AsentarRegCadCustodia.do?consultaCadena=1&numeroExpediente='+numeroExpediente +'&operacion=2 " width="1000" height="600" />');
		}
		/****** FIN listener cadena de Custodia  ****/
		
	function notaExpediente(idNota)
	{
		idWindowGenerarNotas++;
		$.newWindow({id:"iframewindowGenerarNotas" + idWindowGenerarNotas, statusBar: true, posx:200,posy:50,width:700, height:450,title:"Nota de Expediente", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarNotas" + idWindowGenerarNotas,'<iframe src="<%= request.getContextPath() %>/capturarNotaExpediente.do?idNumeroExpedienteOp='+idNumeroExpedienteOp +'&idNota='+idNota+' " width="700" height="450" />');
	}
	
	function cerrarVentanaNota(){
		var pantalla ="iframewindowGenerarNotas";
		pantalla += idWindowGenerarNotas;
		$.closeWindow(pantalla);
	}
	
	function cargaNota(id){
		var row=$('#rowNota_'+id);
		$(row).remove();
		$('#tablaNotasExpediente').append('<tr id="rowNota_'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarNota_'+id+'" onclick="notaExpediente('+id+')">Nota</a></td></tr>');
		//cerrarVentanaNota();
	} 

	/*Funcion que abre el visor de medidas cautelares 
	*/
	function mostrarVentanaInvolucradosCausa(){

		idWindowVisorMedidasCautelaresPJENC++;
		$.newWindow({id:"iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC, statusBar: true, posx:200,posy:50,width:970,height:480,title:"Visor de Medidas Cautelares", type:"iframe"});
    	$.updateWindowContent("iframewindowVisorMedidasCautelares"+idWindowVisorMedidasCautelaresPJENC,'<iframe src="<%=request.getContextPath()%>/visorMedidaCautelar.do?numeroCausa=' + numeroExpediente +'&op=1" width="970" height="480" />'); 
	}
	
	/*
	*Consultar las notas del expediente
	*POR EL MOMENTO SOLO SE CONSULTA UNA NOTA
	*/
	function consultarNotas(){
		var notas=$('#editor1').val();
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarNotasExpediente.do?idNumeroExpediente='+idNumeroExpedienteOp,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('notaExpedienteDTO').each(function(){
					cargaNota($(this).find('idNota').text());
				});
			}
		});
	}
		
					
	/*
	*Funcion que realiza la carga del combo de Actuaciones
	*/
	function cargaActuaciones(id) {
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?id='+id+'&numeroExpediente='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAccionesTab').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});            
			}
		});
	}
	
	/*
	*Funcion que realiza la carga del combo de Actuaciones de policia ministerial
	*/
	function cargaActuacionesPolMinisterial() {
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?idCategoria=<%= CategoriasActividad.POLICIAL.getValorId() %>&numeroExpediente='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAccionesTab9').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
		
		
	}

	function registrarActividadExpediente(actuacionID,estatusId,banderaMensaje){
		//Cambia la actividad del expediente
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registraActividadExpediente.do?idExpediente='+idExpedienteop+'&idNumeroExpediente='+idNumeroExpedienteOp+'&actuacion='+actuacionID+'&estatus='+estatusId+'&numExpe='+numeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				if(parseInt(banderaMensaje)==1)
				{
					alertDinamico("Actividad nueva registrada");	
				}
				//$('#idNotasExpediente').val($(xml).find('notaExpedienteDTO').find('descripcion').text());
				//$(xml).find('notaExpedienteDTO').each(function(){
					//$('#idNotasExpediente').val($(this).find('descripcion').text());
				//});
			}
		});
	}

	function asignarAgenteMP(){
		//Sele colocara la funcion para signar agente ke aun no esta realizada
		var funcio=$('#cbxAjentesCoorUI option:selected').val();
		if(funcio != "-1"){
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/registrafuncionarioNumeroExpediente.do?funcionario='+funcio+'&idNumeroExpediente='+idNumeroExpedienteOp,
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					
				}
			});
			registrarActividadExpediente(151,1712,0);
			alertDinamico("Se asign� correctamente la carpeta de investigaci�n");	
		}else{
			alertDinamico("Debe seleccionar un agente para realizar la asignaci�n");	
		}
	}

	function asignarFacilitador()
	{
		var funcio=$('#cbxAjentesCoorJAR option:selected').val();
		if(funcio != "-1"){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registrafuncionarioNumeroExpediente.do?funcionario='+funcio+'&idNumeroExpediente='+idNumeroExpedienteOp,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){				
				if($(xml).find('respuesta').find('bandera').text() == '1'){
					//Sele colocara la funcion para signar agente ke aun no esta realizada
					registrarActividadExpediente(<%= Actividades.ATENDER_CANALIZACION_JAR.getValorId() %>,2542,0);
					$("#idAsignarFacilitador").hide();
					$("#idReasignarFacilitador").show();
					alertDinamico("Se asign� correctamente el expediente");
				}
				if($(xml).find('respuesta').find('bandera').text() == '0'){
					$("#idAsignarFacilitador").show();
					$("#idReasignarFacilitador").hide();
					alertDinamico("No se logr� asignar el expediente");
				}
			}
		});
		}else{
			alertDinamico("Debe seleccionar un facilitador para realizar la asignaci�n");	
		}
	}
	
	function seleccionaActuacion(){
		var selected = $("#cbxAccionesTab option:selected");
		var confActividadId=selected.val();
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		var habilitarTurno='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getHabilitarTurno() %>';
		var validaDelitoGrave='<%=((ConfiguracionDTO) request.getSession().getAttribute(GenericAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getValidaDelitoGrave() %>';
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+confActividadId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
		actuacion=actividad;
		if( actividad=='<%= Actividades.GENERAR_QUERELLA.getValorId() %>' || actividad=='<%= Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId() %>'){
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/enviarReplicaCaso.do?idExpediente='+idExpedienteop,
				data: '',
				dataType: 'xml',
				async: true,
				success: function(xml){
				}
			});
		}
		if(usaeditor== "true"){			
			
			if (actividad == '<%= Actividades.SOLICITAR_AUDIENCIA.getValorId() %>') {
				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
                $.newWindow({id:"iframewindowSolicitarAudiencia", statusBar: true, posx:20,posy:20,width:1030,height:570,title:"Solicitar Ayuda", type:"iframe"});
                $.updateWindowContent("iframewindowSolicitarAudiencia",'<iframe src="<%= request.getContextPath() %>/solicitarAudiencia.jsp?idNumeroExpediente=' + idNumeroExpedienteOp + '&idExpedienteSoli='+ idExpedienteop+'&numeroExpediente='+numeroExpediente+'"    width="1040" height="570" />');
			}
			else if(actividad=='<%= Actividades.SOLICITAR_ATENCION_PSICOLOGICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS.getValorId() %>')
			{
				//verificamos si se tienen relaciones de delito-persona o delito-delito
				if(consultaTotalRelacionesDelitoPorTodos()>0)
				{
					registrarActividadExpediente(actividad,estatusId,0);
					$.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:940,height:350,title:"Solicitud de Ayuda a la Unidad de Atenci�n a V�ctimas", type:"iframe"});
		            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitarAyudaPsicologicaUAVD.do?formaId='+formaID+'&idExpedienteop='+idExpedienteop+'&numeroCaso='+numeroCaso+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
				}
				else
				{
					var texto = "Debe tener registrada la relaci�n de la v�ctima \n con el " +
								etiquetaProbable +
								" y el delito"
					alertDinamico(texto);
				}
			}
			else if(actividad=='<%=Actividades.SOLICITAR_ATENCION_MEDICA_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS
					.getValorId()%>' || actividad=='<%=Actividades.SOLICITAR_ORIENTACION_LEGAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS
					.getValorId()%>' || actividad=='<%=Actividades.SOLICITAR_SEGURIDAD_POLICIAL_A_LA_DIRECCION_DE_ATENCION_A_VICTIMAS
					.getValorId()%>')
			{
				registrarActividadExpediente(actividad,estatusId,0);
				$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
                $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'"    width="1140" height="550" />');
			}
			else if(actividad=='<%=Actividades.SOLICITAR_DILIGENCIAS_MINISTERIALES
					.getValorId()%>'){
				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
	            $.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar Diligencia", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitudDiligenciaJSP.do"    width="1140" height="550" />');
	     	}
			else if(actividad=='<%= Actividades.SOLICITAR_SERVICIO_PERICIAL.getValorId() %>'){
				 // Variable para definir el �rea de donde proviene la solicitud.
				 // Para Procuraduria el valor es 1
				 // Para Defensoria el valor es 2
				 //codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
				var area = 1;			
	            $.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar servicio pericial", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitarServicioPericial.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
	     	}else if(actividad=='<%=Actividades.ELABORAR_OFICIO_INVESTIGACION_POLICIACA
					.getValorId()%>'){
				 var area = 1;			
				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
	            $.newWindow({id:"iframewindowSolicitudPolicia", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar servicio policial", type:"iframe"});
	            $.updateWindowContent("iframewindowSolicitudPolicia",'<iframe src="<%= request.getContextPath() %>/solicitarServicioPolocial.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
	     	}
			else if(actividad=='<%=Actividades.GENERAR_CONVENIO_DE_CONCILIACION_MEDIACION
					.getValorId()%>')
			{
				idWindowGenConvenio++;
				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
				$.newWindow({id:"iframewindowGenConvenio"+idWindowGenConvenio, statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Generar Convenio", type:"iframe"});
	            $.updateWindowContent("iframewindowGenConvenio"+idWindowGenConvenio,'<iframe src="<%= request.getContextPath() %>/generarConvenio.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
			}
			else if(actividad=='<%=Actividades.GENERAR_CONSTANCIA_DE_SEGUIMIENTO_A_CONVENIO
					.getValorId()%>')
 			{
 				idWindowGenConvenio++;
 				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
 				$.newWindow({id:"iframewindowGenConvenio"+idWindowGenConvenio, statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Seguimiento Convenio", type:"iframe"});
 	            $.updateWindowContent("iframewindowGenConvenio"+idWindowGenConvenio,'<iframe src="<%= request.getContextPath() %>/generarSeguimientoConvenio.do?numeroExpediente='+numeroExpediente+'&numeroExpedienteId='+idNumeroExpedienteOp+'&area='+area+'"    width="1140" height="550" />');
 			}
			else{
				//incluir mi validacion
				if(actividad=='<%=Actividades.DIRIGIR_A_LA_UNIDAD_DE_SOLUCION_DE_CONTROVERSIAS
					.getValorId()%>'){	
					
				//VALIDA EL TIPO DE EXPEDIENTE SEA REPORTE
				tipoExpediente = consultaTipoExpediente();   
				if(tipoExpediente == '<%=OrigenExpediente.REPORTE.getValorId()%>'){
					existeDelitoGrave();					
					if(existeDelitoGraveEnExpediente != ""){
						//Valida Delito Grave deshabilitado
						if(validaDelitoGrave !=null && (validaDelitoGrave == "0" || validaDelitoGrave==0)) {
							if( existeDelitoGraveEnExpediente == "false"){
								 registrarActividadExpediente(actividad,estatusId,0);
					     			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					    		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
							}
						}
						else{
							if( existeDelitoGraveEnExpediente == "true"){
								customConfirm ("Existe delito grave. �Desea enviar a la unidad de controversias?", "Aviso", 
										function(){
											canalizarControversiaExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente );
										},
										recargarActuaciones()
										);
							}
							else{
								canalizarControversiaExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente );
							}
						}
					}else
						alertDinamico("No existen delito(s) registrado(s) en el expediente");
				}else{
					alertDinamico("S�lo los tipo de expediente 'Reporte' se pueden canalizar a JAR");
				}
					
					
					
					
				}else{
					if(actividad =='<%=Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES
					.getValorId()%>'){
						
						//VALIDA EL TIPO DE EXPEDIENTE, DENUNCIA O QUERELLA
						tipoExpediente = consultaTipoExpediente();   
						if(tipoExpediente == '<%=OrigenExpediente.DENUNCIA.getValorId()%>' || tipoExpediente == '<%=OrigenExpediente.QUERELLA.getValorId()%>'){
							existeDelitoGrave();	
							if(existeDelitoGraveEnExpediente != ""){
								//Valida Delito Grave deshabilitado 
								 if(validaDelitoGrave !=null && (validaDelitoGrave == "0" || validaDelitoGrave==0)) {
									//Mostrar ventana de Canalizaci�n a la Unidad de Fiscales Investigadores
										registrarActividadExpediente(actividad,estatusId,0);
						     			$.newWindow({id:"iframewindowUFI", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"", type:"iframe"});
						    		    $.updateWindowContent("iframewindowUFI",'<iframe src="<%=request.getContextPath()%>/canalizarAUnidadFiscalesInv.do?formaId='+formaID+'&numeroExpedienteId='+numeroExpedienteId+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
								 }
								 else{
									 if( existeDelitoGraveEnExpediente == "false"){
										 customConfirm ("No existe delito grave. �Desea enviar a la unidad de fiscales investigadores?", "Aviso", 
													function(){
														canalizarInvestigadoresNoExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente );
													},
													recargarActuaciones()
													);
									 }
									 else{
										 canalizarInvestigadoresNoExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente );
									 }
									 
								 }
							}
							else
								alertDinamico("No existen delito(s) registrado(s) en el expediente");
						}else{
							alertDinamico("Un expediente de tipo 'Reporte' no puede ser enviado a UI");
						}
						
						
										

					}else{
						//codigo para cambiar el estatus del expediente
						registrarActividadExpediente(actividad,estatusId,0);
		     			$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
		    		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');						
					}
				}
             }
		}else{
			if (actividad == '<%= Actividades.REGISTRAR_AMPARO.getValorId() %>') {
                $.newWindow({id:"iframewindowRegistrarAmparo", statusBar: true, posx:20,posy:20,width:450,height:500,title:"Registrar Amparo", type:"iframe"});
                $.updateWindowContent("iframewindowRegistrarAmparo",'<iframe src="<%= request.getContextPath() %>/registrarAmparo.jsp?idNumeroExpediente=' + idNumeroExpedienteOp + '&idExpedienteSoli='+ idExpedienteop+'&numeroExpediente='+numeroExpediente+'"    width="450" height="500" />');
			}else{
				if(actividad !='<%=Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES
					.getValorId()%>' && actividad !='<%=Actividades.DIRIGIR_A_LA_UNIDAD_DE_FISCALES_INVESTIGADORES
					.getValorId()%>'){
				//codigo para cambiar el estatus del expediente
				registrarActividadExpediente(actividad,estatusId,0);
				document.frmDoc2.numeroUnicoExpediente.value = numeroExpediente;
				document.frmDoc2.formaId.value = formaID;
				document.frmDoc2.submit();	
				}
			}
		}
		$('#cbxAccionesTab').empty();
		//F1.29 Se agranda el combobox de actuaciones
		//$('#cbxAccionesTab').append('<option value="-1">-Seleccione-</option>');
		if(pantallaSolicitada=="4"){
			cargaActuaciones("3");
		}else if(pantallaSolicitada=="5"){
			cargaActuaciones("2");
		}else{
			cargaActuaciones(pantallaSolicitada);
		}
	}

	function recargarActuaciones(){
		 $('#cbxAccionesTab').empty();
			//F1.29 Se agranda el combobox de actuaciones
			//$('#cbxAccionesTab').append('<option value="-1">-Seleccione-</option>');
			if(pantallaSolicitada=="4"){
				cargaActuaciones("3");
			}else if(pantallaSolicitada=="5"){
				cargaActuaciones("2");
			}else{
				cargaActuaciones(pantallaSolicitada);
			}
	} 
	
	function canalizarControversiaExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente ) {
		if(existeProbableResponsableReincidente() == "true"){
						
			var texto = "Existe " +
			etiquetaProbable +
			"reincidente. �Desea enviar a la unidad de controversias?"
			
			customConfirm (texto, "Aviso", 
					function(){
						canalizarControversiaPRReincidente(actividad,estatusId,titulo, formaID, numeroExpediente);
					},
					recargarActuaciones()
					);
		}
		else{
			canalizarControversiaPRReincidente(actividad,estatusId,titulo, formaID, numeroExpediente);
		}
	}
	
	function canalizarControversiaPRReincidente(actividad,estatusId,titulo, formaID, numeroExpediente){
		var excede = excedeMediaAritmeticaDelitos(); 
		if( excede == "null"){
			alertDinamico("Existe un problema con la media aritm�tica de los delitos");
		}else{
			if(excede == "true"){
				customConfirm ("La media aritm�tica de los delitos excede lo permitido. �Desea enviar a la unidad de controversias?", "Aviso", 
						function(){
							canalizarControversiaMediaAritmetica(actividad,estatusId,titulo, formaID, numeroExpediente);
						},
						recargarActuaciones()
						);
			}else if(excede == "false"){
				canalizarControversiaMediaAritmetica(actividad,estatusId,titulo, formaID, numeroExpediente);
			}
		}			
	}
	
	function canalizarControversiaMediaAritmetica(actividad,estatusId,titulo, formaID, numeroExpediente){
		registrarActividadExpediente(actividad,estatusId,0);
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
	    
	    customAlert ("Solicitud enviada a la unidad de controversias.", "Aviso", recargarActuaciones);
    }
	
	function canalizarInvestigadoresNoExisteDelitoGrave(actividad,estatusId,titulo, formaID, numeroExpediente ){
		//verificamos si se tienen relaciones de delito-persona o delito-delito
		if(consultaTotalRelacionesDelitoPorTodos() <= 0)
		{
			var texto = "No se tienen registradas relaciones de los delitos con el " +
			etiquetaProbable +
			" �Desea enviar a la unidad de fiscales investigadores?"
			
			customConfirm (texto, "Aviso", 
					function(){
						canalizarInvestigadoresSinRelaciones(actividad,estatusId,titulo, formaID, numeroExpediente);
					},
					recargarActuaciones()
					);
		}
		else{
			canalizarInvestigadoresSinRelaciones(actividad,estatusId,titulo, formaID, numeroExpediente);
		}
			
	}
	
	function canalizarInvestigadoresSinRelaciones(actividad,estatusId,titulo, formaID, numeroExpediente){
		//Mostrar ventana de Canalizaci�n a la Unidad de Fiscales Investigadores
			registrarActividadExpediente(actividad,estatusId,0);
 			$.newWindow({id:"iframewindowUFI", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"", type:"iframe"});
		    $.updateWindowContent("iframewindowUFI",'<iframe src="<%=request.getContextPath()%>/canalizarAUnidadFiscalesInv.do?formaId='+formaID+'&numeroExpedienteId='+numeroExpedienteId+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
		    
		    customAlert ("Solicitud enviada a la unidad de fiscales investigadores.", "Aviso", recargarActuaciones);
	}
	
	/*
	*Cerrar ventana de Canalizacion a unidade de investigadores
	*/
	function cerrarVentanaUFI(){
		$.closeWindow("iframewindowUFI");
	}

	/** selecciona actuacion para policia ministerial*/
	function seleccionaActuacionPolMin(){
		var selected = $("#cbxAccionesTab9 option:selected");
		var confActividadId=selected.val();
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+confActividadId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
		actuacion=actividad;
		
		if(actividad=='<%=Actividades.ELABORAR_OFICIO_INVESTIGACION_POLICIAL
					.getValorId()%>'){
			 registrarActividadExpediente(actividad,estatusId,0);
			 var tipoSolicitud=<%= TiposSolicitudes.POLICIA_MINISTERIAL.getValorId() %>;
			 var area = 1;
			 $.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Elaborar Solicitud", type:"iframe"});
             $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarSolicitud.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpediente+'&idTipoSolicitud='+tipoSolicitud+' "    width="1140" height="550" />');
		}

	}
	/**FIN seleccion de actuacion de policia ministerial**/
	
	
		function muestraMenuQuienDetuvo(){
			ocultaMuestraTabVisor("tabTabsQuienDetuvo",1);
		}

		function documentoGenerado(){
			//funcion para incluir alguna accion despues de haber sido generado el archivo digital (guardado definitivo)
		}
		
		/*
		*Seteamos la bandera cuando el usuario seleccione el tipo de denuncia o querella
		* en la pesta�a de Generales
		*/
		function seteaBanderaTipoSelected()
		{
			isTipoSelected=true;
		}
		//Carga el menu de probable responsable con la consulta y un boton para agregar 
		function cargarMenu(){
			$('#tblProbableResponsable').empty();
			//$('#tblProbableResponsable').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoProbResponsable">Ingreso nuevo</a></td></tr>');
			$('#tblProbableResponsable').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoProbResponsable" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblProbableResponsable').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarProbResponsableUno">Prob. Responsable Uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>' + '/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=0',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarProbableResponsable(' + $(this).find('involucradoId').text() + ');">';
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

		function cargaProbableResponsable(nombre,id){
			var row=$('#'+id);
			$(row).remove();
			nombre=nombre+"- ";
			$('#tblProbableResponsable').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarProbableResponsable" onclick="modificaProbableResponsable('+id+')">'+nombre+'</a></td></tr>');
			
		} 
		
		//Funcion para quitar la victima del visor de elementos
		function eliminarProbableResponsable(id)
		{
			var row=$('#'+id);
			$(row).remove();
		}
		
		/*
		*Funcion que elimina el TR en la tabla de PRs despues de anular un PR en su ventana correspondiente
		* ACT - 20120109
		*/
		function eliminaProbableResponsableDeMenuIntermedio(id){
			var row =$('#tblProbableResponsable tr:#'+id);
			$(row).remove();
			
			var texto = "Se anul� exitosamente el " + etiquetaProbable;
			
			alertDinamico(texto);
		}
		
		/*
		*Funcion para cerrar la venta de modificacion de un PR despues de anular el invlucrado
		* ACT - 20120109
		*/
		function cerrarVentanaProbableResponsable(){
		    var pantalla ="iframewindowModificarProbResponsable";
			pantalla += idWindowIngresarProbResponsable;
			$.closeWindow(pantalla);
		}

		function modificaProbableResponsable(id){
			modificarProbResponsable(id);
		}

		//Carga el menu de victima con la consulta y un boton para agregar 
		function cargarMenuVictima(){
			$('#tblVictima').empty();
			//$('#tblVictima').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevaVictima">Ingreso nuevo</a></td></tr>');
			$('#tblVictima').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevaVictima" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblVictima').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultaVictimaDos">Victima uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=2',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarVictima(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblVictima').append(liga);
					  });
		    	  }
		    });
		}

		function cargaVictima(nombre,id){
			var row=$('#'+id);
			$(row).remove();
			$('#tblVictima').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaVictima('+id+')">'+nombre+'</a></td></tr>');
			//$('#nuevaVictima').hide();
		} 
		function cargaVictimaDenunciante(nombre,id){
			var row=$('#v'+id);
			$(row).remove();
			$('#tblVictima').append('<tr id="v'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarVictima" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
			//$('#nuevaVictima').hide();  modificaDenunciante
		} 

		function modificaVictima(id){
			modificarVictima(id);
		}
		
		function cerrarVentanaVictima(){
		    var pantalla ="iframewindowIngresarVictima";
			pantalla += idWindowIngresarVictima;
			$.closeWindow(pantalla);
		}
		
		//Funcion para quitar la victima del visor de elementos
		function eliminarVictima(id)
		{
			var row=$('#'+id);
			$(row).remove();
		}
		
		function regsitraEstatus(estatus){
			//Cambia la actividad del expediente
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/registraStatusExpediente.do?idExpediente='+idExpedienteop+'&idNumeroExpediente='+idNumeroExpedienteOp+'&estatus='+estatus,
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
						
				}
			});
		}

		
		//Carga el menu de denunciante con la consulta y un boton para agregar 
//		function cargarMenuDenunciante(){
//			$('#tblDenunciante').empty();
//			//$('#tblDenunciante').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante">Ingreso nuevo</a></td></tr>');
//			$('#tblDenunciante').append('<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="crearDenunciante" value="Ingreso nuevo"/></td></tr>');
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
		
		function cargarInvolucradosExpediente(idNumeroExpediente){
			
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaInvolucradosExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    	      $(xml).find('involucradoViewDTO').each(function(){
		    	      if($(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE_ORGANIZACION.getValorId() %>'){
							var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
		    	      	  if($(this).find('nombre').text()=='null'){
							  liga += 'An�nimo';
						  }else if($(this).find('nombre').text()==''){
							  liga += 'An�nimo';
						  }else if($(this).find('nombre').text()=='   '){							  
							  liga += 'An�nimo';			
						  }	else{
							  liga += $(this).find('nombre').text();
							}	
						  liga += '</a></td></tr>';
		    	    	  $('#tblDenunciante').append(liga);
		    	    	  $('#crearDenunciante').css('display','none'); //.attr("disabled", "disabled");
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.VICTIMA_PERSONA.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.DENUNCIANTE.getValorId() %>'){
		    	    	  if($(this).find('esVictima').text()=="true"){
			    	    	  	var liga = '<tr id="v' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
								if($(this).find('nombre').text()=='null'){
									  liga += 'Desconocido';
							}else if($(this).find('nombre').text()=='' || $(this).find('nombre').text()=='   '){
									  liga += 'An�nimo';
		    	    	    }
   		    	    	    else{
									  liga += $(this).find('nombre').text();
								  }
								  liga += '</a></td></tr>';
				    	    	  $('#tblVictima').append(liga);
						}else if($(this).find('calidad').text() == '<%= Calidades.VICTIMA_PERSONA.getValorId() %>'){
							  var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaVictima(' + $(this).find('involucradoId').text() + ');">';
				    	      if($(this).find('nombre').text()=='null'){
								  liga += 'Desconocido';
				    	      }else{
								  liga += $(this).find('nombre').text();
							  }
							  liga += '</a></td></tr>';
			    	    	  $('#tblVictima').append(liga);
						}
				    	  
		    	      }
		    	      
		    	      if($(this).find('calidad').text() == '<%= Calidades.DEFENSOR_PUBLICO.getValorId() %>' ||	$(this).find('calidad').text() == '<%= Calidades.DEFENSOR_PRIVADO.getValorId() %>'){
		    	    	  idDefensor=$(this).find('involucradoId').text();
			    	    	  nombreDefensor=$(this).find('nombre').text();			    	    
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId() %>' || $(this).find('calidad').text() == '<%= Calidades.PROBABLE_RESPONSABLE_ORGANIZACION.getValorId() %>'){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaProbableResponsable(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblProbableResponsable').append(liga);
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.TESTIGO.getValorId() %>'){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaTestigo(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblTestigo').append(liga);
		    	      }
		    	      if($(this).find('calidad').text() == '<%= Calidades.TRADUCTOR.getValorId() %>'){
		    	    	  var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaTraductor(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblTraductor').append(liga);
		    	      }		    
		    	      if($(this).find('calidad').text() == '<%= Calidades.QUIEN_DETUVO.getValorId() %>'){
		    	    	  var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaQuienDetuvo(' + $(this).find('involucradoId').text() + ');">';
			    	      if($(this).find('nombre').text()=='null'){
							  liga += 'Desconocido';
						  }else{
							  liga += $(this).find('nombre').text();
						  }
						  liga += '</a></td></tr>';
		    	    	  $('#tblQuienDetuvo').append(liga);
		    	      }
		    	      
	    	      });
	    		}	
	    	});
		}	
		
		/*
		*Funcion para pintar los objetos expediente en su tab correspondiente
		*/
		<%-- function cargarObjetosExpediente(idNumeroExpediente){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaObjetosExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		cache: false,
	    		success: function(xml){
	    			if(parseInt($(xml).find('code').text())==0)
		    		{
	    					//$('#tblVehiculo').empty();
	    					//$('#tblEquipoComputo').empty();
	    					//$('#tblEquipoTelefonico').empty();
	    					//$('#tblArma').empty();
			    	    
	    				  //$(xml).find('VehiculoDTO').each(function(){
			    	      //	  cargaVehiculo($(this).find('elementoId').text(),$(this).find('valorByTipoVehiculo').find('valor').text(),$(this).find('placa').text());
			    	      //});
			    	      
			    	      //$(xml).find('EquipoComputoDTO').each(function(){
			    	      //	  cargaEquipoComputo($(this).find('elementoId').text(),$(this).find('tipoEquipo').find('valor').text());
			    	      //});
			    	      
			    	      /* $(xml).find('TelefoniaDTO').each(function(){
			    	    	  cargaEquipoTelefonico($(this).find('elementoId').text(),$(this).find('tipoTelefono').find('valor').text());
			    	      }); */
			    	      
			    	      //$(xml).find('ArmaDTO').each(function(){
			    	      //	  cargaArma($(this).find('elementoId').text(),$(this).find('tipoArma').find('valor').text());
			    	      //});
			    	      
			    	      $(xml).find('ExplosivoDTO').each(function(){
			    	    	  cargaExplosivo($(this).find('elementoId').text(),$(this).find('tipoExplosivo').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('SustanciaDTO').each(function(){
			    	    	  cargaSustancia($(this).find('elementoId').text(),$(this).find('tipoSustancia').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('AnimalDTO').each(function(){
			    	    	  cargaAnimal($(this).find('elementoId').text(),$(this).find('tipoAnimal').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('AeronaveDTO').each(function(){
			    	    	  cargaAeronave($(this).find('elementoId').text(),$(this).find('tipoAeroNave').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('EmbarcacionDTO').each(function(){
			    	    	  cargaEmbarcacion($(this).find('elementoId').text(),$(this).find('tipoEmbarcacion').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('NumerarioDTO').each(function(){
			    	    	  cargaNumerario($(this).find('elementoId').text(),$(this).find('moneda').text());
			    	      });
			    	      
			    	      $(xml).find('VegetalDTO').each(function(){
			    	    	  cargaVegetal($(this).find('elementoId').text(),$(this).find('tipoVegetal').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('DocumentoOficialDTO').each(function(){
			    	    	  cargaDocOfic($(this).find('elementoId').text(),$(this).find('tipoDocumento').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('JoyaDTO').each(function(){
			    	    	  cargaJoya($(this).find('elementoId').text(),$(this).find('tipoJoya').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('ObraArteDTO').each(function(){
			    	    	  cargaObraDeArte($(this).find('elementoId').text(),$(this).find('tipoObraArte').find('valor').text());
			    	      });
			    	      
			    	      $(xml).find('OtroDTO').each(function(){
			    	    	  cargaOtros($(this).find('elementoId').text(),$(this).find('nombreObjeto').text());
			    	      });
			    	      
			    	    //deshabilitamos los botones de guardado
			    	    
			    	    /*if(ingresoDenuncia=='true')
			    	    {
			    	      $('#nuevoEquipoDeComputo').hide();
		    	    	  $('#nuevoEquipoTelefonico').hide();
		    	    	  $('#nuevaArma').hide();
		    	    	  $('#nuevoExplosivo').hide();
		    	    	  $('#nuevaSustancia').hide();
		    	    	  $('#nuevoAnimal').hide();
		    	    	  $('#nuevoVehiculo').hide();
		    	    	  $('#nuevaAeronave').hide();
		    	    	  $('#nuevaEmbarcacion').hide();
		    	    	  $('#nuevoNumerario').hide();
		    	    	  $('#nuevoVegetal').hide();
		    	    	  $('#nuevoDocumentoOficial').hide();
		    	    	  $('#nuevaJoya').hide();
		    	    	  $('#nuevaObraDeArte').hide();
			    	    }
			    	    else
			    	    {
			    	    	$('#nuevoEquipoDeComputo').show();
			    	    	  $('#nuevoEquipoTelefonico').show();
			    	    	  $('#nuevaArma').show();
			    	    	  $('#nuevoExplosivo').show();
			    	    	  $('#nuevaSustancia').show();
			    	    	  $('#nuevoAnimal').show();
			    	    	  $('#nuevoVehiculo').show();
			    	    	  $('#nuevaAeronave').show();
			    	    	  $('#nuevaEmbarcacion').show();
			    	    	  $('#nuevoNumerario').show();
			    	    	  $('#nuevoVegetal').show();
			    	    	  $('#nuevoDocumentoOficial').show();
			    	    	  $('#nuevaJoya').show();
			    	    	  $('#nuevaObraDeArte').show();
			    	    }*/
		    		}  
	    		}	
	    	});
		} --%>
		
		/*
		*Funcion para pintar el hecho del expediente en su tab correspondiente
		*/
		function cargarHechoExpediente(idNumeroExpediente){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpediente,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			if(parseInt($(xml).find('code').text())==0)
		    		{
			    	      $(xml).find('hechoDTO').each(function(){
			    	    	  var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarHecho(' + $(this).find('hechoId').text() + ','+idNumeroExpediente+');">';
							  liga += "Hecho";
							  liga += '</a></td></tr>';
			    	    	  $('#tableHecho').append(liga);
			    	    	//deshabilitamos el boton de guardado
			    	    	  $('#ingresarHechos').css('display','none');
			    	      });
		    		}  
	    		}	
	    	});
		}		

		//Carga el menu de denunciante con la consulta y un boton para agregar 
		function cargarMenuDenunciante(){
			$('#tblDenunciante').empty();
			$('#tblDenunciante').append('<tr><td ><input type="button" id="crearDenunciante" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=4',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var idIn=$(this).find('involucradoId').text();
			    	      var liga = '<tr id="' + idIn + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="modificaDenunciante(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblDenunciante').append(liga);
					  });
		    	  }
		    });
		}

		function cargaDenunciante(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblDenunciante').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarDenunciante" onclick="modificaDenunciante('+id+')">'+nombre+'</a></td></tr>');
			$('#crearDenunciante').hide();
		} 
		
		/*
		*Funcion que elimina el TR en la tabla de Denunciantes despues de anular un denunciante en su ventana correspondiente
		* ACT - 20120109
		*/
		function eliminaDenuncianteDeMenuIntermedio(id){
			var row =$('#tblDenunciante tr:#'+id);
			$(row).remove();
			$('#crearDenunciante').show();
			alertDinamico("Se anul� exitosamente el denunciante");
		}
		
		/*
		*Funcion para cerrar la venta de modificacion de un PR despues de anular el invlucrado
		* ACT - 20120109
		*/
		function cerrarVentanaDenunciante(){
		    var pantalla ="iframewindowIngresarDenunciante";
			pantalla += idWindowIngresarDenunciante;
			$.closeWindow(pantalla);
		}

		function modificaDenunciante(id){
			modificarDenuncianteDatos(id);
		}
		
		//Funcion para quitar la victima del visor de elementos
		function eliminarDenunciante(id)
		{
			var row=$('#tblDenunciante tr:#'+id);
			$(row).remove();
			$('#crearDenunciante').show();
		}

		//Carga el menu de testigo con la consulta y un boton para agregar 
		function cargarMenuTestigo(){
			$('#tblTestigo').empty();
			//$('#tblTestigo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTestigo">Ingreso nuevo</a></td></tr>');
			$('#tblTestigo').append('<tr><td>&nbsp;&nbsp;<input type="button" id="nuevoTestigo" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTestigo').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultaTestigo">Testigo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=5',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr id="' + $(this).find('involucradoId').text() + '"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarTestigo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTestigo').append(liga);
					  });
		    	  }
		    });
		}

		function cargaTestigo(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblTestigo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTestigo" onclick="modificaTestigo('+id+')">'+nombre+'</a></td></tr>');
			
		} 

		function modificaTestigo(id){
			modificarTestigo(id);
		}
		
		//Funcion para quitar la victima del visor de elementos
		function eliminarTestigo(id)
		{
			var row=$('#tblTestigo tr:#'+id);
			$(row).remove();
			$('#crearDenunciante').show();
		}
		
		function cerrarVentanaTestigo(){
		    var pantalla ="iframewindowIngresarTestigo";
			pantalla += idWindowIngresarTestigo;
			$.closeWindow(pantalla);
		}
		
		//Carga el menu de traductor con la consulta y un boton para agregar 
		function cargarMenuTraductor(){
			$('#tblTraductor').empty();
			//$('#tblTraductor').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="nuevoTraductor">Ingreso nuevo</a></td></tr>');
			$('#tblTraductor').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblTraductor').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTraductorUno">Traductor uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=7',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarTraductor(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblTraductor').append(liga);
					  });
		    	  }
		    });
		}

		function cargaTraductor(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblTraductor').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarTraductor" onclick="modificaTraductor('+id+')">'+nombre+'</a></td></tr>');
			
		} 

		function modificaTraductor(id){
			modificarTraductor(id);
		}
		

		//Carga el menu de Quien detuvo con la consulta y un boton para agregar 
		function cargarMenuQuienDetuvo(){
			$('#tblQuienDetuvo').empty();
			//$('#tblQuienDetuvo').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Ingreso nuevo</a></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td>&nbsp;&nbsp;&nbsp;<input type="button" id="nuevoTraductor" value="Ingreso nuevo" class="btn_Generico"/></td></tr>');
			$('#tblQuienDetuvo').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a>Quien detuvo uno</a></td></tr>');
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%= request.getContextPath()%>/ObtenerListaIndividuos.do',
		    	  data: 'calidadDelIndividuo=8',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    	      $(xml).find('involucradoViewDTO').each(function(){
			    	      var liga = '<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a onclick="consultarQuienDetuvo(' + $(this).find('involucradoId').text() + ');">';
						  liga += $(this).find('nombre').text() + ' ' + $(this).find('apellidoPaterno').text() + ' ' + $(this).find('apellidoMaterno').text();
						  liga += '</a></td></tr>';
		    	    	  $('#tblQuienDetuvo').append(liga);
					  });
		    	  }
		    });
		}

		function cargaQuienDetuvo(nombre,id){
			var row=$('#'+id);
			$(row).remove(); 
			$('#tblQuienDetuvo').append('<tr id="'+id+'"><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="consultarQuienDetuvo" onclick="modificaQuienDetuvo('+id+')">'+nombre+'</a></td></tr>');
			cerrarVentanaQuienDetuvo();
		}		
			
		function modificaQuienDetuvo(id) {			
			idWindowIngresarQuienDetuvo++;
			$.newWindow({id:"iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Qui�n Detuvo", type:"iframe"});
		    $.updateWindowContent("iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo,'<iframe src="<%= request.getContextPath() %>/IngresarQuienDetuvo.do?elemento='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');
		}
		
		function cargaIngresoHecho(nombre,id){
			$("#ingresarHechos").hide();
			$('#tableHecho').append('<tr><td class="noSub" style="cursor:pointer;">&nbsp;&nbsp;&nbsp;<a id="hecho_'+id+'" onclick="consultarHecho('+id+','+idNumeroExpedienteOp+');">'+nombre+'</a></td></tr>');
			cerrarVentanaHecho();
		} 

		function cerrarVentanaHecho(){
			var pantalla ="iframewindowHecho";
			pantalla += idWindowIngresarHechos;
			$.closeWindow(pantalla);
		}
		
		function cerrarVentanaQuienDetuvo(){
		    var pantalla ="iframewindowQuienDetuvo";
			pantalla += idWindowIngresarQuienDetuvo;
			$.closeWindow(pantalla);
		}
		
		//Abre una nueva ventana de consulta probable responsable  
		function consultarProbResponsable(){
			idWindowConsultarProbResponsable++;
			var titulo = "Consultar " + etiquetaProbable;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1000&idCalidad=PROBABLE_RESPONSABLE" width="1100" height="530" />');		
		}


		//Abre una ventana de problable responsable
		function consultarProbableResponsable(idInvolucrado){
			idWindowConsultarProbResponsable++;
			var titulo = "Consultar " + etiquetaProbable;
			$.newWindow({id:"iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
			$.updateWindowContent("iframewindowConsultarProbResponsable" + idWindowConsultarProbResponsable,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=PROBABLE_RESPONSABLE" width="1100" height="530" />');
		}

		//Abre una nueva ventana para consultar una v�ctima		
		function consultarVictima(idInvolucrado){
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Victima", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=VICTIMA" width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de denunciante		
		function consultarDenunciante(idInvolucrado){
			idWindowConsultarDenunciante++;
			$.newWindow({id:"iframewindowConsultarDenunciante" + idWindowConsultarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Denunciante", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarDenunciante" + idWindowConsultarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=DENUNCIANTE" width="1100" height="530"/>');
		}

		//Abre una nueva ventana de consulta de testigo		
		function consultarTestigo() {
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TESTIGO" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de consulta de testigo
		function consultarTestigo(idInvolucrado){
			idWindowConsultarTestigo++;
			$.newWindow({id:"iframewindowConsultarTestigo" + idWindowConsultarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Testigo", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTestigo" + idWindowConsultarTestigo,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TESTIGO" width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de traductor
		function consultarTraductor(idInvolucrado){
			idWindowConsultarTraductor++;
			$.newWindow({id:"iframewindowConsultarTraductor" + idWindowConsultarTraductor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Traductor", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarTraductor" + idWindowConsultarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=' +idInvolucrado +'&idCalidad=TRADUCTOR"width="1100" height="530" />');
		}

		//Abre una nueva ventana de consulta de organizacion		
		function consultarContactoDeUnaOrganizacion() {
			
			idWindowConsultarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Consultar contacto de una organizaci�n", type:"iframe"});
			$.updateWindowContent("iframewindowConsultarContactoDeUnaOrganizacion" + idWindowConsultarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1001&idCalidad=CONTACTO_ORGANIZACION" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de crear una nuev victima
		function creaNuevaVictima() {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar V�ctima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de crear una nuev victima
		function modificarVictima(id) {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar V�ctima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?idVictima='+id +'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}
		
		function eliminaVictimaDeMenuIntermedio(id){
			var row =$('#tblVictima tr:#'+id);
			$(row).remove();
			alertDinamico("Se anul� exitosamente la v�ctima");
		}

		//Abre una nueva ventana de probable responsable
		function creaNuevoProbResponsable() {
			//numeroExpediente='<%= request.getAttribute("numeroExpediente")%>';
			var sistemaTrad = false;
			idWindowIngresarProbResponsable++;
			var titulo = "Ingresar " + etiquetaProbable;
			
			if(cambiarEtiquetaAProbableParticipe == true){
				$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
				$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?cambiarEtiquetaAProbableParticipe=true&numeroExpediente='+numeroExpediente +'&calidadInv=PROBABLE_RESPONSABLE&idDefensor='+idDefensor+'&detenido='+muestraDetenido+'&sistemaTrad='+sistemaTrad+'" width="1100" height="530" />');
			}else{
				$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
				$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?cambiarEtiquetaAProbableParticipe=false&numeroExpediente='+numeroExpediente +'&calidadInv=PROBABLE_RESPONSABLE&idDefensor='+idDefensor+'&detenido='+muestraDetenido+'&sistemaTrad='+sistemaTrad+'" width="1100" height="530" />');
			}
			
		
		}

		//Abre una nueva ventana de probable responsable
		function modificarProbResponsable(id) {
			idWindowIngresarProbResponsable++;
			var sistemaTrad = false;
			var titulo = "Modificar " + etiquetaProbable;
			
			if(cambiarEtiquetaAProbableParticipe == true){
				$.newWindow({id:"iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
				$.updateWindowContent("iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?cambiarEtiquetaAProbableParticipe=true&idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpediente +'&detenido='+muestraDetenido+'&sistemaTrad='+sistemaTrad+'" width="1100" height="530" />');	
			}else{
				$.newWindow({id:"iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:75,posy:30,width:1100,height:530,title:titulo, type:"iframe"});
				$.updateWindowContent("iframewindowModificarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?cambiarEtiquetaAProbableParticipe=false&idProbableResponsable='+id +'&calidadInv=PROBABLE_RESPONSABLE&numeroExpediente='+numeroExpediente +'&detenido='+muestraDetenido+'&sistemaTrad='+sistemaTrad+'" width="1100" height="530" />');				
			}
				
			
						
		}
		
		//Abre una nueva ventana de Denunciante
		function crearDenunciante(){
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroExpediente +'&calidadInv=DENUNCIANTE" width="1100" height="530" />');		
		}

		//Abre una nueva ventana de Denunciante
		function modificarDenuncianteDatos(id){
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?idDenunciante='+id +'&numeroExpediente='+numeroExpediente +'&calidadInv=DENUNCIANTE" width="1100" height="530" />');		
		}
			

		//Crea una nueva ventana de testigo
		function creaNuevoTestigo() {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}

		//Crea una nueva ventana de testigo
		function modificarTestigo(id) {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?idTestigo='+id+'&numeroExpediente='+numeroExpediente +'" width="1100" height="530" />');		
		}
		
		function eliminaTestigoDeMenuIntermedio(id){
			var row =$('#tblTestigo tr:#'+id);
			$(row).remove();
			alertDinamico("Se anul� exitosamente el testigo");
		}
		
		//Crea una ventana de un nuevo contacti de una organizacion		
		function creaNuevoContactoDeUnaOrganizacion() {
			
			idWindowIngresarContactoDeUnaOrganizacion++;
			$.newWindow({id:"iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Contacto de una organizaci�n", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarContactoDeUnaOrganizacion" + idWindowIngresarContactoDeUnaOrganizacion,'<iframe src="<%= request.getContextPath() %>/IngresarContactoDeUnaOrganizacion.do" width="1100" height="530"  />');		
		}

		//crea una nueva ventana de ingresar tutor
		function creaNuevoTutor() {
			idWindowIngresarTutor++;
			$.newWindow({id:"iframewindowTutor" + idWindowIngresarTutor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Datos Generales", type:"iframe"});
		    $.updateWindowContent("iframewindowTutor" + idWindowIngresarTutor,'<iframe src="<%= request.getContextPath() %>/IngresarTutor.do" width="1050" height="600" />');		
		}

		//Abre una nueva ventana de ingresar traductor
		function creaNuevoTraductor() {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Traductor", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');		
		}	

		//Abre una nueva ventana de ingresar traductor
		function modificarTraductor(id) {
			idWindowIngresarTraductor++;
		$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:75,posy:30,width:1050,height:530,title:"Modificar Traductor", type:"iframe"});
	    $.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?idTraductor='+id+'&numeroExpediente='+numeroExpediente +'" width="1050" height="600" />');		
		}
		
		//Elimina un registro del traductor 
		function eliminaTraductorDeMenuIntermedio(id){
			var row =$('#tblTraductor tr:#'+id);
			$(row).remove();
		}
		
		//Cierra la ventana traductor
		function cerrarVentanaTraductor(){
		    var pantalla ="iframewindow";
			pantalla += idWindowIngresarTraductor++;
			$.closeWindow(pantalla);
		}
		
		//Abre una nueva ventana de ingresar quien detuvo
		function creaQuienDetuvo() {
			idWindowIngresarQuienDetuvo++;
		$.newWindow({id:"iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar Qui�n detuvo", type:"iframe"});
	    $.updateWindowContent("iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo,'<iframe src="<%= request.getContextPath() %>/IngresarQuienDetuvo.do?elemento='+0+'&numeroExpediente='+numeroExpediente+'" width="1050" height="600" />');
		}	

		//Abre una nueva ventana de modificar denunciante		
		function modificarDenunciante(){
			
			idWindowModificarDenunciante++;
			$.newWindow({id:"iframewindowModificarDenunciante" + idWindowModificarDenunciante, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Modificar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarDenunciante" + idWindowModificarDenunciante,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=DENUNCIANTE" width="1040" height="620" />');		
		}

		//Abre una nueva ventana de modificar traductor
		//function modificarTraductor(){
		//	idWindowModificarTraductor++;
		//	$.newWindow({id:"iframewindowModificarTraductor" + idWindowModificarTraductor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Consultar Traductor", type:"iframe"});
		//    $.updateWindowContent("iframewindowModificarTraductor" + idWindowModificarTraductor,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=TRADUCTOR" width="1040" height="570" />');		
		//}

		
		function creaNuevoRepresentanteLegal() {
			idWindowIngresarRepresentanteLegal++;
		$.newWindow({id:"iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal, statusBar: true,posx:75,posy:30,width:1100,height:530,title:"Ingresar Representante Legal", type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarRepresentanteLegal" + idWindowIngresarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/IngresarRepresentanteLegal.do" width="1050" height="600" />');		
		}	

		
		function modificarRepresentanteLegal(){
			
			idWindowModificarRepresentanteLegal++;
			$.newWindow({id:"iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Consultar Representante Legal", type:"iframe"});
		    $.updateWindowContent("iframewindowModificarRepresentanteLegal" + idWindowModificarRepresentanteLegal,'<iframe src="<%= request.getContextPath() %>/ConsultarIndividuo.do?idInvolucrado=1200&idCalidad=REPRESENTANTE_LEGAL" width="1040" height="570" />');		
		}

		
		function crearSentenciadoReinsertar() {
			idWindowIngresarSentenciadoReinsertar++;
			$.newWindow({id:"iframewindowIngresarSentenciadoReinsertar" + idWindowIngresarSentenciadoReinsertar, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Ingresar nuevo sentenciado a reinsertar", type:"iframe"});
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
		    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente +'&idEquipoComputo=0&tipoObjeto=EQUIPO_COMPUTO" width="830" height="340" />');
		}
		
		function consultarEquipoComputo(idEquipoComputo){
			idWindowIngresarEquipoDeComputo++;
			$.newWindow({id:"iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Consultar equipo de c&oacute;mputo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoDeComputo" + idWindowIngresarEquipoDeComputo,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente+'&idEquipoComputo='+idEquipoComputo+'&tipoObjeto=EQUIPO_COMPUTO " width="830" height="340" />');
		}
		
		function cargaEquipoComputo(id,tipo){
			consultaGridEquiposComputoVisor();
			cerrarVentanaEquipoComputo();
		} 
		
		function cerrarVentanaEquipoComputo(){
			var pantalla ="iframewindowIngresarEquipoDeComputo";
			pantalla += idWindowIngresarEquipoDeComputo;
			$.closeWindow(pantalla);
		}
			
		function creaNuevoEquipoTelefonico(){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico=0&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
		}
		
		function consultarEquipoTelefonico(idEquipoTelefonico){
			 idWindowIngresarEquipoTelefonico++;
			$.newWindow({id:"iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Consultar equipo telef&oacute;nico", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEquipoTelefonico" + idWindowIngresarEquipoTelefonico,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico='+idEquipoTelefonico+'&tipoObjeto=EQUIPO_TELEFONICO " width="870" height="340" />');
		}

		function cargaEquipoTelefonico(id,modelo){
			consultaGridEquipoTelefonicoVisor();
			cerrarVentanaEquipoTelefonico();
		} 
		
		function cerrarVentanaEquipoTelefonico(){
			var pantalla ="iframewindowIngresarEquipoTelefonico";
			pantalla += idWindowIngresarEquipoTelefonico;
			$.closeWindow(pantalla);
		}
		 
		function creaNuevaArma(){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma=0&tipoObjeto=ARMA " width="800" height="380" />');
		}

		function consultarArma(idArma){
			 idWindowIngresarArma++;
			$.newWindow({id:"iframewindowIngresarArma" + idWindowIngresarArma, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Consultar arma", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarArma" + idWindowIngresarArma,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma='+idArma+'&tipoObjeto=ARMA" width="800" height="380" />');
		}
		
		function cargaArma(id,tipo){
			consultaGridArmasVisor();
			cerrarVentanaArma();
		} 
		
		function cerrarVentanaArma(){
			var pantalla ="iframewindowIngresarArma";
			pantalla += idWindowIngresarArma;
			$.closeWindow(pantalla);
		}
		
		function creaNuevoExplosivo(){
			 idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=EXPLOSIVO&idExplosivo=0" width="880" height="330" />');
		}
		
		function consultarExplosivo(idExplosivo){
			idWindowIngresarExplosivo++;
			$.newWindow({id:"iframewindowIngresarExplosivo" + idWindowIngresarExplosivo, statusBar: true, posx:200,posy:50,width:880, height:330,title:"Consultar explosivo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarExplosivo" + idWindowIngresarExplosivo,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente +'&idExplosivo='+idExplosivo+'&tipoObjeto=EXPLOSIVO " width="880" height="330" />');
		}

		function cargaExplosivo(id,tipo){
			 consultaGridExplosivoVisor();
			 cerrarVentanaExplosivo();
		} 
		
		function cerrarVentanaExplosivo(){
			var pantalla ="iframewindowIngresarExplosivo";
			pantalla += idWindowIngresarExplosivo;
			$.closeWindow(pantalla);
		}

		
		function creaNuevaSustancia(){
			 idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente +'&idSustancia=0&tipoObjeto=SUSTANCIA" width="900" height="330" />');
		}
		
		function consultarSustancia(idSustancia){
			idWindowIngresarSustancia++;
			$.newWindow({id:"iframewindowIngresarSustancia" + idWindowIngresarSustancia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar sustancia", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarSustancia" + idWindowIngresarSustancia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia='+idSustancia+'&tipoObjeto=SUSTANCIA" width="900" height="330" />');
		}
		
		function cargaSustancia(id,tipo){
			consultaGridSustanciaVisor();
			cerrarVentanaSustancia();
		} 
		
		function cerrarVentanaSustancia(){
			var pantalla ="iframewindowIngresarSustancia";
			pantalla += idWindowIngresarSustancia;
			$.closeWindow(pantalla);
		}

		
		function creaNuevoAnimal(){
			 idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente +'&idAnimal=0&tipoObjeto=ANIMAL" width="900" height="330" />');
		}

		function consultarAnimal(idAnimal){
			idWindowIngresarAnimal++;
			$.newWindow({id:"iframewindowIngresarAnimal" + idWindowIngresarAnimal, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar animal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAnimal" + idWindowIngresarAnimal,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'&idAnimal='+idAnimal+'&tipoObjeto=ANIMAL" width="900" height="330" />');
		}
		
		function cargaAnimal(id,tipo){
			 consultaGridAnimalVisor();
			 cerrarVentanaAnimal();
		} 
		
		function cerrarVentanaAnimal(){
			var pantalla ="iframewindowIngresarAnimal";
			pantalla += idWindowIngresarAnimal;
			$.closeWindow(pantalla);
		}
		
		
		
		function creaNuevoVehiculo(){
			 idWindowIngresarVehiculo++;
			 var sistemaTrad = false;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=VEHICULO&idVehiculo=0&sistemaTrad='+sistemaTrad+'" width="570" height="600" />');
		}
		
		function consultarVehiculo(idVehiculo){
			 idWindowIngresarVehiculo++;
			 var sistemaTrad = false;
			$.newWindow({id:"iframewindowIngresarVehiculo" + idWindowIngresarVehiculo, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVehiculo" + idWindowIngresarVehiculo,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&idVehiculo='+idVehiculo+'&tipoObjeto=VEHICULO&sistemaTrad='+sistemaTrad+'" width="570" height="600" />');
		}

		function cargaVehiculo(id,tipo,placas){
			consultaGridVehiculosVisor();
			cerrarVentanaVehiculo();
		} 
		
		function cerrarVentanaVehiculo(){
			var pantalla ="iframewindowIngresarVehiculo";
			pantalla += idWindowIngresarVehiculo;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevaAeronave(){
			 idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'&idAeronave=0&tipoObjeto=AERONAVE" width="600" height="530" />');
		}
		function consultarAeronave(idAeronave){
			idWindowIngresarAeronave++;
			$.newWindow({id:"iframewindowIngresarAeronave" + idWindowIngresarAeronave, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar aeronave", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarAeronave" + idWindowIngresarAeronave,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente+'&idAeronave='+idAeronave+'&tipoObjeto=AERONAVE" width="600" height="530" />');
		}
		
		function cargaAeronave(id,tipo){
			 consultaGridAeronaveVisor();
			 cerrarVentanaAeronave();
		} 
		
		function cerrarVentanaAeronave(){
			var pantalla ="iframewindowIngresarAeronave";
			pantalla += idWindowIngresarAeronave;
			$.closeWindow(pantalla);
		}

		
		function creaNuevaEmbarcacion(){
			 idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'&idEmbarcacion=0&tipoObjeto=EMBARCACION" width="600" height="530" />');
		}
		function consultarEmbarcacion(idEmbarcacion){
			idWindowIngresarEmbarcacion++;
			$.newWindow({id:"iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion, statusBar: true, posx:200,posy:10,width:600,height:530,title:"Consultar embarcaci&oacute;n", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarEmbarcacion" + idWindowIngresarEmbarcacion,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente+'&idEmbarcacion='+idEmbarcacion+'&tipoObjeto=EMBARCACION" width="600" height="530" />');
		}
		
		function cargaEmbarcacion(id,tipo){
			 consultaGridEmbarcacionVisor();
			 cerrarVentanaEmbarcacion();
		} 
		
		function cerrarVentanaEmbarcacion(){
			var pantalla ="iframewindowIngresarEmbarcacion";
			pantalla += idWindowIngresarEmbarcacion;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevoInmueble(){
			 idWindowIngresarInmueble++;
			$.newWindow({id:"iframewindowIngresarInmueble" + idWindowIngresarInmueble, statusBar: true, posx:200,posy:0,width:768,height:540,title:"Ingresar inmueble", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarInmueble" + idWindowIngresarInmueble,'<iframe src="<%= request.getContextPath() %>/IngresarInmueble.do?numeroExpediente='+numeroExpediente +'" width="1050" height="600" />');
		}
		 

		function creaNuevoNumerario(){
			 idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Ingresar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario=0&tipoObjeto=NUMERARIO" width="800" height="350" />');
		}
		function consultarNumerario(idNumerario){
			idWindowIngresarNumerario++;
			$.newWindow({id:"iframewindowIngresarNumerario" + idWindowIngresarNumerario, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar numerario", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarNumerario" + idWindowIngresarNumerario,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente +'&idNumerario='+idNumerario+'&tipoObjeto=NUMERARIO " width="800" height="350" />');
		}
		function cargaNumerario(id,tipo){
			 consultaGridNumerarioVisor();
			 cerrarVentanaNumerario();
		} 
		
		function cerrarVentanaNumerario(){
			var pantalla ="iframewindowIngresarNumerario";
			pantalla += idWindowIngresarNumerario;
			$.closeWindow(pantalla);
		}

		 
		function creaNuevoVegetal(){
			 idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente +'&idVegetal=0&tipoObjeto=VEGETAL" width="800" height="350" />');
		}
		function consultarVegetal(idVegetal){
			idWindowIngresarVegetal++;
			$.newWindow({id:"iframewindowIngresarVegetal" + idWindowIngresarVegetal, statusBar: true, posx:200,posy:50,width:800, height:350,title:"Consultar vegetal", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVegetal" + idWindowIngresarVegetal,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente+'&idVegetal='+idVegetal+'&tipoObjeto=VEGETAL" width="800" height="350" />');
		}
		
		function cargaVegetal(id,tipo){
			 consultaGridVegetalVisor();
			 cerrarVentanaVegetal();
		} 
		
		function cerrarVentanaVegetal(){
			var pantalla ="iframewindowIngresarVegetal";
			pantalla += idWindowIngresarVegetal;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevoDocumentoOficial(){
			 idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente +'&idDocOfic=0&tipoObjeto=DOCUMENTO_OFICIAL" width="800" height="350" />');
		}
		function consultarDocumentoOficial(idDocumentoOficial){
			idWindowIngresarDocumentoOficial++;
			$.newWindow({id:"iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar documento oficial", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDocumentoOficial" + idWindowIngresarDocumentoOficial,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente+'&idDocOfic='+idDocumentoOficial+'&tipoObjeto=DOCUMENTO_OFICIAL" width="800" height="350" />');
		}
		
		function cargaDocOfic(id,tipo){
			 consultaGridDocumentoOficialVisor();
			 cerrarVentanaDocumentoOficial();
		} 
		
		function cerrarVentanaDocumentoOficial(){
			var pantalla ="iframewindowIngresarDocumentoOficial";
			pantalla += idWindowIngresarDocumentoOficial;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevaJoya(){
			 idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente +'&idJoya=0&tipoObjeto=JOYA" width="850" height="380" />');
		}
		function consultarJoya(idJoya){
			idWindowIngresarJoya++;
			$.newWindow({id:"iframewindowIngresarJoya" + idWindowIngresarJoya, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar joya", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarJoya" + idWindowIngresarJoya,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente+'&idJoya='+idJoya+'&tipoObjeto=JOYA" width="850" height="380" />');
		}
		
		function cargaJoya(id,tipo){
			 consultaGridJoyaVisor();
			 cerrarVentanaJoya();
		} 
		
		function cerrarVentanaJoya(){
			var pantalla ="iframewindowIngresarJoya";
			pantalla += idWindowIngresarJoya;
			$.closeWindow(pantalla);
		}
		 

		function creaNuevaObraDeArte(){
			 idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente +'&idObraArte=0&tipoObjeto=OBRA_DE_ARTE" width="850" height="380" />');
		}
		
		
		function consultarObraDeArte(idObraDeArte){
			idWindowIngresarObraDeArte++;
			$.newWindow({id:"iframewindowObraDeArte" + idWindowIngresarObraDeArte, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar obra de arte", type:"iframe"});
		    $.updateWindowContent("iframewindowObraDeArte" + idWindowIngresarObraDeArte,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'&idObraArte='+idObraDeArte+'&tipoObjeto=OBRA_DE_ARTE" width="850" height="380" />');
		}
		
		function cargaObraDeArte(id,tipo){
			 consultaGridObraDeArteVisor();
			 cerrarVentanaObraDeArte();
		} 
		
		function cerrarVentanaObraDeArte(){
			var pantalla ="iframewindowObraDeArte";
			pantalla += idWindowIngresarObraDeArte;
			$.closeWindow(pantalla);
		}
					
		/*** Cargar otros ****/
		function creaNuevoOtros(){
			 idWindowIngresarOtros++;
			$.newWindow({id:"iframewindowOtros" + idWindowIngresarOtros, statusBar: true, posx:200,posy:50,width:800,height:450,title:"Ingresar Otros", type:"iframe"});
		    $.updateWindowContent("iframewindowOtros" + idWindowIngresarOtros,'<iframe src="<%= request.getContextPath() %>/IngresarOtros.do?numeroExpediente='+numeroExpediente +'&idOtros=0&tipoObjeto=OTRO" width="800" height="450" />');
		}
		
		
		function consultarOtros(idOtros){
			idWindowIngresarOtros++;
			$.newWindow({id:"iframewindowOtros" + idWindowIngresarOtros, statusBar: true, posx:200,posy:50,width:800,height:450,title:"Consultar otros", type:"iframe"});
		    $.updateWindowContent("iframewindowOtros" + idWindowIngresarOtros,'<iframe src="<%= request.getContextPath() %>/IngresarOtros.do?numeroExpediente='+numeroExpediente+'&idOtros='+idOtros+'&tipoObjeto=OTRO" width="800" height="450" />');
		}
		
		function cargaOtros(id,nombre){
			 consultaGridOtrosVisor();
			 cerrarVentanaOtros();
		} 
		
		function cerrarVentanaOtros(){
			var pantalla ="iframewindowOtros";
			pantalla += idWindowIngresarOtros;
			$.closeWindow(pantalla);
		}
		/***** FIN cargar otros *****/
		
		function ingresarHechos() {
			idWindowIngresarHechos++;
			$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?numeroExpediente='+numeroExpediente +'&idNumeroExpedienteOp='+idNumeroExpedienteOp+'&idCalidad=DENUNCIANTE&idHecho=0&menuIntermedio=1" width="1100" height="530" />');		
		}
		
		function consultarHecho(idHecho,idNumeroExpediente) {
			idWindowIngresarHechos++;
			$.newWindow({id:"iframewindowHecho" + idWindowIngresarHechos, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindowHecho" + idWindowIngresarHechos,'<iframe src="<%= request.getContextPath() %>/IngresarHechos.do?numeroExpediente='+numeroExpediente +'&idNumeroExpedienteOp='+idNumeroExpedienteOp+'&idCalidad=DENUNCIANTE&idHecho='+idHecho +'&menuIntermedio=1" width="1100" height="530" />');		
		}

		
		function asociarIndividuo() {
			idWindowAsociarIndividuo++;
			$.newWindow({id:"iframewindow" + idWindowAsociarIndividuo, statusBar: true, posx:75,posy:30,width:1100,height:530,title:"Hechos", type:"iframe"});
		    $.updateWindowContent("iframewindow" + idWindowAsociarIndividuo,'<iframe src="<%= request.getContextPath() %>/AsociarIndividuo.do" width="1100" height="530" />');		
		}
	function muestraMenuIntermedio(abrePenal){
		
if(abrePenal=="abrPenal"){
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
		    	url: '<%= request.getContextPath()%>/consultarUnidadesEspecializadas.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAUI').empty();
		    		$('#cbxCanalizaAUI').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('departamentos').each(function(){
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
		    	url: '<%= request.getContextPath()%>/consultarInstitucionesExternas.do',
		    	data: '',
		    	dataType: 'xml',
		    	async: false,
		    	success: function(xml){
		    		$('#cbxCanalizaAIE').empty();
		    		$('#cbxCanalizaAIE').append('<option value="-1" selected="selected">-Seleccione-</option>');
		    		$(xml).find('departamentos').each(function(){
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
		
		
		function revisaEsDelitoGraveUno(idRadio)
		{
			//recuperamos el valor de la columna gravedad del delito
			var ret = jQuery("#gridCatDelitos").jqGrid('getRowData',idRadio);
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRadio);
			var isGrave="No";
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//mostramos los botones correspondientes dependiento del tipo de delito
			if(ret.Gravedad!=null)
			{
				isGrave=ret.Gravedad;
			}
			else
			{
				isGrave=retDos.Gravedad;
			}
			if(isGrave=="No")
			{
				//revisamos que no exista un delito grave NO seleccionado
				if(existeDelitoGraveEnGrid())
				{
					//se le indica al usuario que seleccione un delito grave como principal
					alertDinamico("El delito principal debe ser un delito grave,\n por favor revise su selecci�n");
				}
			}
		}
		
		function revisaEsDelitoGrave(idRadio)
		{
			//recuperamos el valor de la columna gravedad del delito
			var ret = jQuery("#gridCatDelitos").jqGrid('getRowData',idRadio);
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRadio);
			var isGrave="No";
			
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//mostramos los botones correspondientes dependiento del tipo de delito
			if(ret.Gravedad!=null)
			{
				isGrave=ret.Gravedad;
			}
			else
			{
				isGrave=retDos.Gravedad;
			}
			if(isGrave=="No")
			{
				//revisamos que no exista un delito grave NO seleccionado
				if(existeDelitoGraveEnGrid())
				{
					//se le indica al usuario que seleccione un delito grave como principal
					alertDinamico("El delito principal debe ser un delito grave,\n por favor revise su selecci�n");
				}
				else
				{
					mostraDivGenerarOficioCanalizacion(1);	
				}
			}
			else{
				//barro el pseudo-XML de delitos	
				delitosXML.find('catDelitoDTO').each(function(){
					if($(this).find('claveDelito').text()==idRadio)
					{
						if($(this).find('departamento').text()!="")
						{
							//seteamos el combo de la pesta�a de Acciones dependiendo del departamento
							$('#cbxCanalizaAUI').val(parseInt($(this).find('departamento').find('departamentoId').text()));
							$('#cbxCanalizaAUI').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(2);
						}
						else
						{
							//seteamos el combo de la pesta�a de Acciones dependiendo de la institucion
							$('#cbxCanalizaAIE').val(parseInt($(this).find('institucion').find('institucionId').text()));
							$('#cbxCanalizaAIE').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(3);
						}
					}
				});
			}
		}
		
		/*
		* Funcion que recorre el grid de delitos agraviados para revisar si existe 
		*un delito grave que no fue seleccionado como principal, de existir regresa true en 
		*caso contrario regresa false
		*/
		function existeDelitoGraveEnGrid()
		{
			var bandera=false;
			//obtenemos los ID's de los renglones del Grid
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//barremos el grid para revisar si hay por lo menos un delito marcado como grave
			for (i=0;i<arrayIDs.length;i++)
			{
				//revisamos el checkbox del renglon i-esimo para ver si es grave
				var isGrave=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
				if(isGrave.Gravedad=="Yes")
				{
					bandera=true;
				}
			} 
			return bandera;
		}
		
		
		/*
		*Funcion para saber si se selecciono un delito como principal
		*/
		function existeUnDelitoPrincipalGraveSeleccionado()
		{
			var bandera=0;
			//obtengo el ID del rdb del delito seleccionado
			var idRdb="";
			idRdb=$('input[name=gridDelitos]:checked').attr('id');
			if(idRdb!=null)
			{
				idRdb=idRdb.split('_')[1];
				if(idRdb!="")
				{
					//reviso si el delito seleccionado es grave o no
					/*var IDS=jQuery("#gridDelitosAgraviados").getDataIDs();
					for(j=0;j<IDS.length;j++)
					{
					}
					IDS=jQuery("#gridCatDelitos").getDataIDs();
					for(j=0;j<IDS.length;j++)
					{
					}*/
					var resp=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRdb);
					if(resp.Gravedad=="Yes")
					{
						//prendemos la badnera al encontrar un radio seleccionado
						bandera=1;	
					}
				}	
			}
			else
			{
				alertDinamico("Debe seleccionar un delito principal para poder guardar");	
				bandera=2;
			}
			return bandera;
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
		
		function muestraDIVSCanalizacion()
		{
			//obtenemos los ID's de los renglones del Grid de Agraviados
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//validamos que haya delitos en el grid de agraviados
			if(arrayIDs.length<1)
			{
				alertDinamico("Debe agregar al menos un delito agraviado");
				return;
			}
			//revisamos que si hay un delito grave se haya seleccionado
			if(existeDelitoGraveEnGrid())
			{
				if(parseInt(existeUnDelitoPrincipalGraveSeleccionado())==0)
				{
					alertDinamico("Debe seleccionar un delito grave como principal");
					return;
				}	
			}
			//obtenemos el ID del delito principal
			var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
			//recuperamos el valor de la columna gravedad del delito
			var retDos = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal);
			var isGrave="No";
			var delitos=<%=(String)request.getSession().getAttribute("delitosXML")%>;
			var delitosXML=$(''+delitos);
			//seteamos la gravedad
			isGrave=retDos.Gravedad;
			if(isGrave=="No")
			{
				mostraDivGenerarOficioCanalizacion(1);	
			}
			else
			{
				//barro el pseudo-XML de delitos
				delitosXML.find('catDelitoDTO').each(function()
				{
					if($(this).find('claveDelito').text()==idDelPrincipal)
					{
						if($(this).find('departamento').text()!="")
						{
							//seteamos el combo de la pesta�a de Acciones dependiendo del departamento
							$('#cbxCanalizaAUI').val(parseInt($(this).find('departamento').find('departamentoId').text()));
							$('#cbxCanalizaAUI').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(2);
						}
						else
						{
							//seteamos el combo de la pesta�a de Acciones dependiendo de la institucion
							$('#cbxCanalizaAIE').val(parseInt($(this).find('institucion').find('institucionId').text()));
							$('#cbxCanalizaAIE').multiselect('refresh');
							mostraDivGenerarOficioCanalizacion(3);
						}
					}
				});
			}
		}
		
		/*
		* Funcion que revisa que exista un delito grave en el grid de delitos denunciados 
		*/		
		function existeUnDelitoPrincipalEnGrid()
		{			
			var idRdb="";
			var bandera1=0;
			var bandera2=1;
			
			idRdb=$('input[name=gridDelitos]:checked').attr('id');
			if(idRdb!=null)
			{
				idRdb=idRdb.split('_')[1];
				if(idRdb!="")
				{
					var resp=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idRdb);
					if(resp.Gravedad=="No")
					{
						//prendemos la bandera al encontrar un radio seleccionado
						bandera1=1;	
					}
				}	
			}
			else
			{				
				bandera1=0;
			}
			
			var arrayIDs = jQuery("#gridDelitosAgraviados").getDataIDs();
			
			for (i=0;i<arrayIDs.length;i++)
			{								
				var row = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
			
				if(row.GravedadFormateada=="Si") bandera2=0; 
			} 					
			
			if(bandera2==1 && bandera1==0)
			return 0;
			else return 1;
		}

		
		/*
		*Funcion para asociar los delitos del grid de agraviados con el 
		*expediente
		*/
		function guardarDelitosAgraviadosExp()
		{
			//obtenemos los ID's de los renglones del Grid de Agraviados
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();			
			
			//validamos que haya delitos en el grid de agraviados
			if(arrayIDs.length<1)
			{
				alertDinamico("Debe agregar al menos un delito agraviado");
				return;
			}
			
			//validamos si no hay un delito grave, que al menos exista un delito principal
			if(parseInt(existeUnDelitoPrincipalEnGrid())==0){
				alertDinamico("Debe seleccionar un delito principal");	
				return;
			}
			
			//revisamos que si hay un delito grave se haya seleccionado
			if(existeDelitoGraveEnGrid())
			{
				if(parseInt(existeUnDelitoPrincipalGraveSeleccionado())==0)
				{
					alertDinamico("Debe seleccionar un delito grave como principal");
					return;
				}	
			}
			//obtenemos el ID del delito principal
			var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
			var nombreDelPrincipal=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal).Tipo;
			var delitosNormales="";			
			//barremos el grid para generar la cadena de IDs de los delitos normales
			for (i=0;i<arrayIDs.length;i++)
			{
				if(arrayIDs[i]!=idDelPrincipal)
				{
					var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
					if(delitosNormales.length>0)
					{
						delitosNormales=delitosNormales+","+retd.Tipo;//arrayIDs[i];
					}
					else
					{
						delitosNormales=""+retd.Tipo;//arrayIDs[i];
					}
				}
			} 
			//ahora mandamos los delitos al back-end
			var params="delitos="+nombreDelPrincipal+"-"+delitosNormales+"&numExp="+numeroExpediente;
			$.ajax({
	       	  url: '<%= request.getContextPath()%>/guardarDelitosAgraviadosATP.do',
	    	  dataType: 'xml',
	    	  Type: 'POST',
	    	  data:params,
	    	  async: false,
	    	  success: function(xml){
	    		  if(parseInt($(xml).find('code').text())==0)
	    		  {
	    			  isDelitoSaved=true;
	    			//mostramos las leyendas de canalizacion debajo del grid
	    			  if(existeDelitoGraveEnGrid())
	    			  {
	    				  $("#leyendaUnDelitoGrave").show();
	    				  $("#leyendaNingunDelitoGrave").hide();
	    			  }
	    			  else
	    			  {
	    				  $("#leyendaUnDelitoGrave").hide();
	    				  $("#leyendaNingunDelitoGrave").show();
	    			  }
	    			  //lanzamos la consulta de actividades que depende de los delitos almacenados
	    			  	$.ajax({
				       	  url: '<%= request.getContextPath()%>/consultarActividadesPorDelitosDelExpediente.do',
				    	  dataType: 'xml',
				    	  Type: 'POST',
				    	  data:params,
				    	  async: false,
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('code').text())==0)
				    		  {
				    			  $("#actividadesXDelitosDelExpediente").empty();
				    			  var actividades="";
				    			  //barremos las activiades y generamos el html para ser pintado
				    			  //debajo del anuncio de canalizacion
				    			  $(xml).find('ValorDTO').each(function(){
				    				  actividades = actividades + $(this).find('valor').text()+"<br/>";
					    	      });
				    			  $("#actividadesXDelitosDelExpediente").html(actividades);
				    		  }  			    		
						  }
	    				});
	    			  //fin de la consulta de actividades que depende de los delitos almacenados
	    			  alertDinamico("Se guardaron exitosamente los delitos seleccionados");
	    		  }	 
	    		  else
	    		  {
	    			  isDelitoSaved=false;
	    			  alertDinamico("Ocurri� un error al tratar de guardar los delitos agraviados");
	    		  }   			    		
			  }
	    	});
		}
		
		
		/*
		*Funcion para mostrar las actividades sugeridas dependiendo de los delitos en el expediente 
		*cuando se consulta un expediente
		*/
		function muestraActividadesSugeridasEnConsultaExpediente()
		{
			//obtenemos los ID's de los renglones del Grid de Agraviados
			var arrayIDs = new Array() ;
			var arrayIDs=jQuery("#gridDelitosAgraviados").getDataIDs();
			//validamos que haya delitos en el grid de agraviados
			if(arrayIDs.length<1)
			{
				return;
			}
			//obtenemos el ID del delito principal			
			var idDelPrincipal=$('input[name=gridDelitos]:checked').attr('id').split('_')[1];
			if(idDelPrincipal==null || idDelPrincipal=='null')
			{
				return;	
			}
			var nombreDelPrincipal=jQuery("#gridDelitosAgraviados").jqGrid('getRowData',idDelPrincipal).Tipo;
			var delitosNormales="";			
			//barremos el grid para generar la cadena de IDs de los delitos normales
			for (i=0;i<arrayIDs.length;i++)
			{
				if(arrayIDs[i]!=idDelPrincipal)
				{
					var retd = jQuery("#gridDelitosAgraviados").jqGrid('getRowData',arrayIDs[i]);
					if(delitosNormales.length>0)
					{
						delitosNormales=delitosNormales+","+retd.Tipo;//arrayIDs[i];
					}
					else
					{
						delitosNormales=""+retd.Tipo;//arrayIDs[i];
					}
				}
			} 
			//ahora mandamos los delitos al back-end
			var params="delitos="+nombreDelPrincipal+"-"+delitosNormales+"&numExp="+numeroExpediente;
		
			//mostramos las leyendas de canalizacion debajo del grid
  			  if(existeDelitoGraveEnGrid())
  			  {
  				  $("#leyendaUnDelitoGrave").show();
  				  $("#leyendaNingunDelitoGrave").hide();
  			  }
  			  else
  			  {
  				  $("#leyendaUnDelitoGrave").hide();
  				  $("#leyendaNingunDelitoGrave").show();
  			  }
  			  //lanzamos la consulta de actividades que depende de los delitos almacenados
  			  	$.ajax({
	       	  url: '<%= request.getContextPath()%>/consultarActividadesPorDelitosDelExpediente.do',
	    	  dataType: 'xml',
	    	  Type: 'POST',
	    	  data:params,
	    	  async: false,
	    	  success: function(xml){
	    		  if(parseInt($(xml).find('code').text())==0)
	    		  {
	    			  $("#actividadesXDelitosDelExpediente").empty();
	    			  var actividades="";
	    			  //barremos las activiades y generamos el html para ser pintado
	    			  //debajo del anuncio de canalizacion
	    			  $(xml).find('ValorDTO').each(function(){
	    				  actividades = actividades + $(this).find('valor').text()+"<br/>";
		    	      });
	    			  $("#actividadesXDelitosDelExpediente").html(actividades);
	    		  }  			    		
			  }
  				});
  			  //fin de la consulta de actividades que depende de los delitos almacenados
		}
		
		
		
		
		
		
		function validaGuardadoDefinitivo()
		{
			//revisamos que selecciono el tipo: Denuncia o Querella en generales
			//obtengo el ID del rdb del delito seleccionado
			var idRdbTipo="";
			var banderaTipo=false;
			idRdbTipo=$('input[name=generales]:checked').attr('id');
			if(idRdbTipo!=null)
			{
				if(idRdbTipo!="")
				{
					//reviso si el delito seleccionado es grave o no
					if(idRdbTipo=="rbtnDenuncia")//Denuncia
					{
						$("#btnAccDenuncia").show();
						$("#tdCbxAccionesTab").show();
						$("#btnAccQuerella").hide();
						//revisamos si ya guardo el delito
						if(isDelitoSaved)
						{
							//$("#btnAccDenuncia").attr("disabled", "");
						}
						else
						{
							alertDinamico("Debe de seleccionar guardar en la pesta�a Delito");
							return;
						}
					}
					else//rbtnQuerella querella
					{
						$("#btnAccQuerella").show();
						$("#btnAccDenuncia").hide();
						$("#tdCbxAccionesTab").hide();
					}
					banderaTipo=true;
				}
			}
			if(!banderaTipo)
			{
				alertDinamico("Debe seleccionar el tipo en la pesta�a Generales");
				return;
			}
		}
		
		
		
		function gridRelacionarDelitosTabDelito(){
			cargaComboProbableResponsableRDPPV();
			cargaComboDelitosAAsociarRDPD();
			jQuery("#gridDetalleTabDelitoRelDel").jqGrid({ 
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
				pager: jQuery('#pagerGridDetalleTabDelitoRelDel'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc"
					
			}).navGrid('#pagerGridDetalleTabDelitoRelDel',{edit:false,add:false,del:false});

			// $("#gview_gridDetalleFrmPrincipalTres .ui-jqgrid-bdiv").css('height', '200px');	

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

		function abrirDenuncia() {
			$.newWindow({id:"iframewindowAbrirDenuncia", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Denuncia", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirDenuncia",'<iframe src="<%= request.getContextPath() %>/resources/images/Denuncia en Atenci�n Temprana _JAS.pdf" width="1140" height="400" />');
		   		
		}
		
		function abreTeoria() {
			$.newWindow({id:"iframewindowAbrirteoria", statusBar: true, posx:20,posy:20,width:1390,height:600,title:"Teoria del caso", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirteoria",'<iframe src="<%= request.getContextPath() %>/teoriaDelCasoView.jsp?idExpediente='+idExpedienteop+'&numeroExpediente='+numeroExpediente+'" width="1390" height="600" />');
		      		
		}

		
		function lanzaCarpetaInvestigacionDefensoria() {
			$.newWindow({id:"iframewindowAbrirteoria", statusBar: true, posx:20,posy:20,width:310,height:231,title:"Enviar Carpeta Investigaci�n", type:"iframe"});
		    $.updateWindowContent("iframewindowAbrirteoria",'<iframe src="<%= request.getContextPath() %>/enviarCarpetaInvestigacionDefensoria.do" width="310" height="231" />');
		      		
		}


		function datosGenerales(){
			 $.ajax({
			      type: 'POST',
		    	  url: '<%= request.getContextPath()%>/cargarDatosGenerales.do?idNumeroExpedienteOp='+idNumeroExpedienteOp,
		    	  data: '',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
						$('#Vehiculos').html($(xml).find('totalVehiculos').text()+': '+$(xml).find('vehiculos').text());
						if($(xml).find('ve').text()!="1"){
							$('#imgVehiculo').hide();
						}
						$('#EquiposDeComputo').html($(xml).find('totalEquiposComputo').text()+': '+$(xml).find('equiposComputo').text());
						if($(xml).find('equiCom').text()!="1"){
							$('#imgEquiposDeComputo').hide();
						}
						$('#EquiposTelefonicos').html($(xml).find('totalEquiposTelefonicos').text()+': '+$(xml).find('equiposTelefonicos').text());
						if($(xml).find('equiTel').text()!="1"){
							$('#imgEquiposTelefonicos').hide();
						}
						$('#Armas').html($(xml).find('totalArmas').text()+': '+$(xml).find('armas').text());
						if($(xml).find('arm').text()!="1"){
							$('#imgArmas').hide();
						}
						$('#Explosivos').html($(xml).find('totalExplosivos').text()+': '+$(xml).find('explosivos').text());
						if($(xml).find('expl').text()!="1"){
							$('#imgExplosivos').hide();
						}
						$('#Sustancias').html($(xml).find('totalSustancias').text()+': '+$(xml).find('sustancias').text());
						if($(xml).find('sus').text()!="1"){
							$('#imgSustancias').hide();
						}
						$('#Animales').html($(xml).find('totalAnimales').text()+': '+$(xml).find('animales').text());
						if($(xml).find('anim').text()!="1"){
							$('#imgAnimales').hide();
						}
						$('#Aeronaves').html($(xml).find('totalAeronaves').text()+': '+$(xml).find('aeronaves').text());
						if($(xml).find('aero').text()!="1"){
							$('#imgAeronaves').hide();
						}
						$('#Embarcacion').html($(xml).find('totalEmbarcaciones').text()+': '+$(xml).find('embarcaciones').text());
						if($(xml).find('embar').text()!="1"){
							$('#imgEmbarcacion').hide();
						}
						$('#Inmueble').html($(xml).find('totalInmuelbes').text()+': '+$(xml).find('inmuelbes').text());
						if($(xml).find('inmu').text()!="1"){
							$('#imgInmueble').hide();
						}
						$('#Numerario').html($(xml).find('totalNumerarios').text()+': '+$(xml).find('numerarios').text());
						if($(xml).find('nume').text()!="1"){
							$('#imgNumerario').hide();
						}
						$('#Vegetal').html($(xml).find('totalVegetales').text()+': '+$(xml).find('vegetales').text());
						if($(xml).find('vege').text()!="1"){
							$('#imgVegetal').hide();
						}
						$('#DocumentoOficial').html($(xml).find('totalDocumentosOficiales').text()+': '+$(xml).find('documentosOficiales').text());
						if($(xml).find('docuOfi').text()!="1"){
							$('#imgDocumentoOficial').hide();
						}
						$('#Joya').html($(xml).find('totalJoyas').text()+': '+$(xml).find('joyas').text());
						if($(xml).find('joy').text()!="1"){
							$('#imgJoya').hide();
						}
						$('#ObraDeArte').html($(xml).find('totalObrasDeArte').text()+': '+$(xml).find('obrasDeArte').text());
						if($(xml).find('obrArte').text()!="1"){
							$('#imgObraDeArte').hide();
						}
						$('#Otros').html($(xml).find('totalOtrosObjestos').text()+': '+$(xml).find('otrosObjestos').text());
						if($(xml).find('otrObj').text()!="1"){
							$('#imgOtros').hide();
						}
						$('#Denunciantes').html($(xml).find('totalDenunciantes').text()+': '+$(xml).find('denunciantes').text());
						if($(xml).find('denun').text()!="1"){
							$('#imgDenunciantes').hide();
						}
						$('#Victimas').html($(xml).find('totalVictimas').text()+': '+$(xml).find('victimas').text());
						if($(xml).find('vic').text()!="1"){
							$('#imgVictimas').hide();
						}
						$('#ProbablesResponsables').html($(xml).find('totalProbablesResposables').text()+': '+$(xml).find('probablesResposables').text());
						if($(xml).find('proba').text()!="1"){
							$('#imgProbablesResponsables').hide();
						}
						$('#Testigos').html($(xml).find('totalTestigos').text()+': '+$(xml).find('testigos').text());
						if($(xml).find('test').text()!="1"){
							$('#imgTestigos').hide();
						}
						$('#Traductores').html($(xml).find('totalTraductores').text()+': '+$(xml).find('traductores').text());
						if($(xml).find('tradu').text()!="1"){
							$('#imgTraductores').hide();
						}
						$('#QuienDetuvo').html($(xml).find('quienDetuvo').text()+': '+$(xml).find('quienDetuvoNombre').text());
						if($(xml).find('quienDetu').text()!="1"){
							$('#imgQuienDetuvo').hide();
						}
						$('#estatusExpe').html($(xml).find('estatusNumeroExpediente').text());
						$('#origenExpe').html($(xml).find('origenExpediente').text());	
						$('#anonimoDenun').html($(xml).find('esDesconocido').text());
						num=$(xml).find('totalDocumentosDelExpediente').text();
						$('#idTotalDocumentos').html($(xml).find('totalDocumentosDelExpediente').text());
						$('#fehcaApertura').html("Fecha Apertura:"+$(xml).find('fechaApertura').text());
						$('#origenNumeroExpediente').html($(xml).find('origenNumeroExpediente').text());
						
		    	  }
		    	});
		}


		function documentos(){
//			 $.ajax({
//			      type: 'POST',
//		    	  url: '<%= request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul,
//		    	  data: '',
//		    	  dataType: 'xml',
//		    	  async: false,
//		    	  success: function(xml){
//			    	  $('#tablePestanaDocumentos').empty();
//			    	  $(xml).find('documentoDTO').each(function(){
//			    		  var nombres=$(this).find('nombreDocumento').text();
//			    		  $('#tablePestanaDocumentos').append('<tr><td class="noSub">&nbsp;&nbsp;&nbsp;<a onclick="abrirDenuncia()" id="Documento">'+nombres+'</a></td></tr>');
//				     });
//		       	  }
//		    	});

			 jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
						{url:'<%= request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul, 
						datatype: "xml" ,
						success: function(xml){
							alertDinamico('<%=request.getSession().getAttribute(
					"totalRegistrosDocumentos")%>');	 
						 } 
						});
					 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
					
					 datosGenerales();
					
		}
		
		
		function alertas(){
			 jQuery("#gridDetalleAlertas").jqGrid('setGridParam',  
						{url:'<%= request.getContextPath()%>/consultarAlertas.do?idExpedienteop='+idNumeroExpedienteConsul, 
						datatype: "xml" });
					 $("#gridDetalleAlertas").trigger("reloadGrid"); 
		}
		
		/************ FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		function ocultaMuestraTabVisor(claseTab,bandera)
		{
			if(parseInt(bandera)==0)//oculta
			{
				$("."+claseTab).hide();
			}
			else///muestra
			{
				$("."+claseTab).show();
			}
		}

		function consultaPDF(id){
			document.frmDoc.documentoId.value = id;
			document.frmDoc.submit();
		}
		
		function cerrarVentanaProableResponsable(){		
			$.closeWindow('iframewindowIngresarProbResponsable');	
		}
		
		
		/************ FIN FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		
		
		function ocultaMuestraTblsRelacionarDelitos()
		{
			var relacionDelitoPorPErsonaDelito = $(':radio[name=relacionaDelitos]:checked').val();
			if(parseInt(relacionDelitoPorPErsonaDelito)==0)
			{
				//Relacion por persona
				$("#tblRelacionaDelXPersona").show();
				$("#tblRelacionaDelXDelito").hide();
				$("#tblRelacionaDelXTodos").hide();
			}
			else if(parseInt(relacionDelitoPorPErsonaDelito)==1)
			{
				//Relacion por delito
				$("#tblRelacionaDelXDelito").show();
				$("#tblRelacionaDelXPersona").hide();
				$("#tblRelacionaDelXTodos").hide();
			}
			else
			{
				//Relacion por todos
				$("#tblRelacionaDelXDelito").hide();
				$("#tblRelacionaDelXPersona").hide();
				$("#tblRelacionaDelXTodos").show();
				cargaGridConsultaDelitosTodos();
			}	
		}

		function cerrarVentanaEnvio(){
			$windowCloseButton.click();
				
			}
		
		function abreLineasInvestiga(){		
			$.newWindow({id:"iframewindowLineaInvestigacion", statusBar: true, posx:20,posy:20,width:1300,height:550,title:"Investigaci�n", type:"iframe"});
	        $.updateWindowContent("iframewindowLineaInvestigacion",'<iframe src="<%= request.getContextPath() %>/lineasInvestigacion.do?numeroUnicoExpediente='+numeroExpediente+'&idNumeroUnicoExpediente='+idNumeroExpedienteConsul+'&pantalla='+pantallaSolicitada+'"    width="1300" height="550" />');
		}
		
		/*
		*Funcion que carga el grid con los nombre de los documentos digitales asociados 
		*al id de la solicitud de visitaduria
		*/
		function cargaGridDocumentosDigitales(){
			if(primeraVezGridDocumentosDigitales == true){
				jQuery("#gridDocumentosDigitales").jqGrid({
					url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul+'',
					datatype: "xml", 
					colNames:['�rea del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
					colModel:[ 	{name:'area',index:'area', width:200},
								{name:'fechaActividad',index:'fechaActividad', width:170},							
								{name:'nombreActividad',index:'nombreActividad', width:400},
					           	{name:'tipo',index:'tipo', width:155}, 
								{name:'nombre',index:'nombre', width:255},
					           	{name:'fecha',index:'fecha', width:170}
								],
					pager: jQuery('#pagerGridDocumentosDigitales'),
					rowNum:20,
					rowList:[10,20,30],
					width:250,
					sortname: 'nombreDocumento',
					viewrecords: true,
					sortorder: "desc",
					multiselect:true,
					ondblClickRow: function(rowid) {
						if (rowid) {
							abrirDocsDigAsociadosASol(rowid);							
						}
					},
					loadComplete: function(){
						jQuery("#gridDocumentosDigitales").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
					}
				}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
				$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
				primeraVezGridDocumentosDigitales= false;
			}
			else{
				jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numeroExpedienteId+'',datatype: "xml" });
				$("#gridDocumentosDigitales").trigger("reloadGrid");
			}
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '430px');
		}
		
		/*
		*Funcion que abre el PDF para ver los documentos asociados al numero de causa
		*/
		function abrirDocsDigAsociadosASol(documentoId){
			if(documentoId != null && documentoId != ""){
				$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoId+"&inFrame=true");
			}
			else{
				alertDinamico("El documento no puede ser mostrado");
			}
		}
		
		function elaboraNotificacionAuditoria()
		{	
			var actividad=0;
			var formaID=4;
			var titulo="op";
			var usaeditor="";
			var estatusId="";
			//consultamos la forma para la notificacion de auditoria
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf=135',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
					formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
					titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
					usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
					estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
				}
			});
			$.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Notificaci�n Auditor�a", type:"iframe"});
            $.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarNotificacionAuditoria.do?formaId='+formaID+'&numeroUnicoExpediente='+idNumeroExpedienteOp+'"    width="1140" height="550" />');
		}
		/*
		*Funcion que carga el grid con los nombre de los documentos digitales asociados 
		*al id de la solicitud de visitaduria
		*/
		function cargaGridDocumentosDigitales(){
			if(primeraVezGridDocumentosDigitales == true){
				jQuery("#gridDocumentosDigitales").jqGrid({
					url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idNumeroExpedienteConsul+'',
					datatype: "xml", 
					colNames:['�rea del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
					colModel:[ 	{name:'area',index:'area', width:200},
								{name:'fechaActividad',index:'fechaActividad', width:170},							
								{name:'nombreActividad',index:'nombreActividad', width:400},
					           	{name:'tipo',index:'tipo', width:155}, 
								{name:'nombre',index:'nombre', width:255},
					           	{name:'fecha',index:'fecha', width:170}
								],
					pager: jQuery('#pagerGridDocumentosDigitales'),
					rowNum:20,
					rowList:[10,20,30],
					width:250,
					sortname: 'nombreDocumento',
					viewrecords: true,
					sortorder: "desc",
					multiselect:true,
					ondblClickRow: function(rowid) {
						if (rowid) {
							abrirDocsDigAsociadosASol(rowid);							
						}
					},
					loadComplete: function(){
						jQuery("#gridDocumentosDigitales").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
					}
				}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
				$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
				primeraVezGridDocumentosDigitales= false;
			}
			else{
				jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numeroExpedienteId+'',datatype: "xml" });
				$("#gridDocumentosDigitales").trigger("reloadGrid");
			}
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '430px');
		}
		
		/*
		*Funcion que abre el PDF para ver los documentos asociados al numero de causa
		*/
		function abrirDocsDigAsociadosASol(documentoId){
			if(documentoId != null && documentoId != ""){
				$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoId+"&inFrame=true");
			}
			else{
				alertDinamico("El documento no puede ser mostrado");
			}
		}
		
		/*
		*Funcion para regresar el numero de relacionde delito-persona o delito-delito con 
		*las que cuenta el expediente
		*/
		function consultaTotalRelacionesDelitoPorTodos()
		{
			var numeroRelaciones = 0;
			//consultamos la forma para la notificacion de auditoria
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarTamanoDelitosAsociadosPorTodos.do?idExpediente='+idExpedienteop+'',
				data: '',
				dataType: 'xml',
				async: false,
				success: function(xml){
					numeroRelaciones=$(xml).find('relacionTodosLosDelitos').find('tamanoLista').text();
				}
			});
			return numeroRelaciones;
		}

		/*
		*Funcion que lanza la ventana par asoliciutar una transcripcion de audiencia y de audio y video
		*/
		function muestraSolicitudTranscripcion()
		{
			idWindowSolicitudTranscripcion++;
			$.newWindow({id:"iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion, statusBar: true, posx:253,posy:100,width:812,height:454,title:"Solicitud de Transcripci�n", type:"iframe"});
	    	$.updateWindowContent("iframewindowSolicitudTranscripcion"+idWindowSolicitudTranscripcion,'<iframe src="<%=request.getContextPath()%>/solicitarTranscripcionEnPG.do" width="812" height="454" />');

	    	}

		
		
		
		/*
		*Funcion que permite saber si un expedietn tiene un delito grave
		*/
		function existeDelitoGrave()
		{			
	    	$.ajax({
	    		type: 'POST',
	    		url:'<%= request.getContextPath()%>/ExisteDelitoGravePorIdExpediente.do?idNumeroExpediente='+idExpedienteop+'&numeroExpedienteId='+numeroExpedienteId+'',
	    		data: '',
	    		async: false,
	    		dataType: 'xml',
	    		success: function(xml){
	    			existeDelitoGraveEnExpediente = $(xml).find('boolean').text();
	    		}
	    	});
	    	return existeDelitoGraveEnExpediente;
		}
		
		/*
		*Funcion que permite saber si un expediente tiene un probable respnsable reincidente
		*/
		function existeProbableResponsableReincidente()
		{
			var existeProbRespReincidente = "";

			$.ajax({
   				type: 'POST',
   				url: '<%=request.getContextPath()%>/existeReincidenciaDeProbablesResponsables.do',
   				data: 'numeroExpediente='+numeroExpediente+'',
   				dataType: 'xml',
   				async: false,
   				success: function(xml){
   					existeProbRespReincidente = $(xml).find('boolean').text();
   				}
            });

			//se regresa la bandera que indica que hubo o no un PR reincidente
			return existeProbRespReincidente;
		}

		/*
		*Funcion que permite saber la Media aritm�tica de los delitos NO exceda de 4 a�os (definido en tabla Parametro)
		*/
		function excedeMediaAritmeticaDelitos()
		{
			var resultado = "";

			$.ajax({
   				type: 'POST',
   				url: '<%=request.getContextPath()%>/excedeMediaAritmeticaDelitos.do',
   				data: 'numeroExpedienteId='+numeroExpedienteId+'',
   				dataType: 'xml',
   				async: false,
   				success: function(xml){
   					resultado = $(xml).find('boolean').text();
   				}
            });
            
			//se regresa la bandera que indica que hubo o no un PR reincidente
			return resultado;
		}
		
		function cierraVentanaRegistrarAmparo(){
			var pantalla ="iframewindowRegistrarAmparo";
			//pantalla += idWindowIngresarProbResponsable;
			$.closeWindow(pantalla);
		}		
		
		
		function consultaTipoExpediente()
		{
			//alert("El numero de expediente es: " + idNumeroExpedienteOp)
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultaHechoExpediente.do',
	    		data: 'idNumeroExpediente='+idNumeroExpedienteOp,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			if(parseInt($(xml).find('code').text())==0){
	    				tipoExpediente=$(xml).find('hechoDTO').find('expediente').find('origen').find('idCampo').text();	    				
	    				tipoExpediente = tipoExpediente == "" ? 0: tipoExpediente;
	    			}  
	    		}	
	    	});
			return tipoExpediente;
		}
		
		
		function cambiaTextoVisor(idTipoExpediente){
			//Unicamente para atpenal
			if(pantallaSolicitada == "1"){
				var tituloOriginal =window.parent.recuperaTituloVisor(idIframe)
				var nuevoTitulo = "";
				if(tituloOriginal.toLowerCase().substring(0, 10) == "expediente"){
					nuevoTitulo =  (idTipoExpediente == 2 ? "Reporte" : "Expediente")  + tituloOriginal.substring(10, tituloOriginal.length);
					window.parent.cambiarTituloVisor(idIframe,nuevoTitulo)
				}else{
					if(tituloOriginal.toLowerCase().substring(0, 7) == "reporte"){
						nuevoTitulo =  (idTipoExpediente == 2 ? "Reporte" : "Expediente")  + tituloOriginal.substring(7, tituloOriginal.length);
						window.parent.cambiarTituloVisor(idIframe,nuevoTitulo)
					}					
				}	
			}			
		}
		
		function ocultaSeccionDeDelitos(){
			var idParametro = '<%=Parametros.ENTIDAD_FEDERATIVA_DESPLIEGUE.ordinal()%>';
	
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/consultarParametro.do',
				data: 'idParametro='+ idParametro, 
				async: false,
				dataType: 'xml',
				success: function(xml){					
					var mensaje = $(xml).find('body').find('respuesta').text();
					if(mensaje == "32"){//Zacatecas
						//Se ocultan propiendades de Delito Persona
						$('#tblDelitoPersonaVariantes').hide();
					}
				}
			});
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
		
		function recargaGridAgenteMP(){
			if(flagIndexProcView==1){
				window.parent.regresaGrid();
			}
		}
		
