<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
<style type="text/css">
			DD P {
				LINE-HEIGHT: 120%
			}
			#iProbResponsablePane {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 6px;
				WIDTH: 1000px;
				PADDING-RIGHT: 0px;
				HEIGHT: 462px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#iProbResponsablePane DL {
				WIDTH: 1000px; HEIGHT: 400px
			}
			/*acordeon editar*/
			#iProbResponsablePane DT {
				TEXT-ALIGN: right;
				PADDING-BOTTOM: 16px;
				PADDING-TOP: 2px;
				PADDING-LEFT: 0px;
				LINE-HEIGHT: 35px;
				TEXT-TRANSFORM: none;	
				/*acomodo texto*/PADDING-RIGHT: 40px;
				FONT-FAMILY: Arial, Helvetica, sans-serif;
				LETTER-SPACING: 1px;
				/*distancia persianas*/HEIGHT: 25px;
				COLOR: #f5f5f5;
				FONT-SIZE: 12px;
				FONT-WEIGHT: normal;	
				background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
				background-repeat: no-repeat;
				background-position: 28px;
			}
			#iProbResponsablePane DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#iProbResponsablePane DT.hover {
				COLOR: #f5f5f5
			}
			#iProbResponsablePane DT.hover.active {
				COLOR: #f5f5f5
			}
			#iProbResponsablePane DD {
				BORDER-BOTTOM: #dbe9ea 0px solid; 
				BORDER-LEFT: 0px; 
				PADDING-BOTTOM: 1px; 
				PADDING-LEFT: 1px; 
				PADDING-RIGHT: 1px; 
				/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/ 
				BORDER-TOP: #dbe9ea 0px solid; 
				MARGIN-RIGHT: 1px; 
				BORDER-RIGHT: #dbe9ea 0px solid; 
				PADDING-TOP: 1px
			}
			/*distancia y color de numero*/
			#iProbResponsablePane .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#iProbResponsablePane .active .slide-number {
				COLOR: #fff
			}
			#iProbResponsablePane A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#iProbResponsablePane DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#iProbResponsablePane H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#iProbResponsablePane .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
		</style>

	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<!--    Hoja de estilo para easyaccordion-->
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
   
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	 <!--Scripts necesarios para la ejecucion del editor-->
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<style>
	.transpa {
	background-color: transparent;
	border: 0;
	}
	</style>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type="text/javascript">
	var idindi;
	var etapaTit;
	//variable para determinar el tipo de detalle
	var tipo= '<%=request.getParameter("tipo")!=null?request.getParameter("tipo"):""%>';
	var numEXP = '<%=request.getParameter("stNumEx")%>';
	var numSOL = '<%=request.getParameter("idAviso")%>';
	var numEXPID = '<%=request.getParameter("numExpId")%>';
	var reloadGrid=false;
	var esDetenido;
	var idDefensor="";
	var apDefensor = "";
	var amDefensor = "";
	var defensorNombre = "";
	var especialidadesDefensor = "";
	
	var pasatipoDefensoria = "<%= TipoDefensoria.INTEGRACION.getValorId() %>";

	var id;
	
	$(document).ready(function() {

		//ocultamos el domicilio de notificaciones
		killDomicilioNotificaciones();
		//se genera el acordeon
		$('#iProbResponsablePane').easyAccordion({ 
		  autoStart: false, 
		  slideInterval: 3000
		});
		
		//arranca el editor de texto
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
				
				height:'250px',
				width:'900px'
			};			
			$('.jquery_ckeditor').ckeditor(config);
		
		$("#tabsprincipalconsulta").tabs();		
		$("#camponombreDefensor").val(defensorNombre);
		$("#campoapDefensor").val(apDefensor);
		$("#campoamDefensor").val(amDefensor);
		$("#campoEspecialidadDefensor").val(especialidadesDefensor);
		//llama a consultar el defensor por default
		grid2();
		tablaDefensor();

		switch (parseInt(tipo)) {
		case 1:
			//"entra a avisos de personas detenidas para designar defensor"
			$("#avisosPersonasDetenidas").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();
			consultaDetalleExp(1);
			
			
			break;

		case 2:
			//"entra a poder judicial para designar defensor"
			$("#poderJudicial").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();
			consultaDetalleExp(2);
					
			break;				

		case 3:
			//"entra a atencion temprana para designar defensor";
			$("#atencionTemprana").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();
			
			consultaDetalleExp(3);
					
			break;

		case 4:
			//"entra a designaciones de atencion temprana sin defensor asignado"
			$("#atencionTemprana").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();			
			consultaDetalleExp(3);
															
			break;

		case 5:
			//"entra a designaciones de poder judicial sin defensor asignado");
			$("#poderJudicial").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();
			consultaDetalleExp(2);
															
			break;

		case 6:
			$("#atencionTemprana").show();
			consultaDetalleExp(3);
															
			break;

		case 7:
			$("#poderJudicial").show();
			consultaDetalleExp(2);
															
			break;

		case 8:
			$("#detalleAsesoria").show();
			$("#datosInteresado").show();
			$("#motivoAsesoria").show();
			$("#tabDocumentos").show();
			$("#tabAsignarDefensor").show();	
			consultaDetalleExp(4);
															
			break;
		

		}
	});

	function grid2(){
		var  tipo=pasatipoDefensoria;
		if (reloadGrid==true) {
			  jQuery("#tabgrid").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/SolicitudesDefensorGrid.do?tipo='+tipo,datatype: "xml" });
			  $("#tabgrid").trigger("reloadGrid"); 
		} else{
			jQuery("#tabgrid").jqGrid({ 
				url:'<%= request.getContextPath()%>/SolicitudesDefensorGrid.do?tipo='+tipo, 
				data:"tipo="+tipo,
				datatype: "xml", 
				colNames:['Carga','Nombre','Propuesta'], 
				colModel:[ {name:'Carga',index:'carga', width:100, resizable:true},
						   {name:'Nombre',index:'nombre', width:200, resizable:true}, 				           	
				           {name:'Marca',index:'marca', width:100, resizable:true}
														
						],
				resizable:true,
				pager: jQuery('#tabgrid'),
				rowNum:10,
				rowList:[10,20,30],
				width: 'auto',
				height: 'auto',
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				caption:""+etapaTit,
				onresize_end: function () { $("#tabgrid").setGridWidth($("#mainContent").width() - 5, true);},
				onSelectRow: function(rowID) {dblClickRowBandejaDefensor(rowID);},
				ondblClickRow: function(rowID) {dblClickRowBandejaDefensor(rowID);}
			}).navGrid('#tabgrid',{edit:false,add:false,del:false});	
			$(this).find('table').width('100%');
		  }
	}

	function dblClickRowBandejaDefensor(rowID){
		idDefensor=rowID;
		tablaDefensor();
	}

	function tablaDefensor(){
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/SolicitudesDefensor.do',
			data: 'idDefensor='+idDefensor,
			dataType: 'xml',
			async: false,
			success: function(xml){
					$(xml).find('funcionarioDTO').each(function(){
						if(pasatipoDefensoria==1){   
							$("#tipoDefensa").val('Integración');
						}
						if(pasatipoDefensoria==2){   
							$("#tipoDefensa").val('Tecnica');
						}
						if(pasatipoDefensoria==3){   
							$("#tipoDefensa").val('Restaurativa');
						}
						if(pasatipoDefensoria==4){   
							$("#tipoDefensa").val('Externa');
						}
						defensorNombre =$(xml).find('nombreFuncionario').first().text();
						apDefensor =$(xml).find('apellidoPaternoFuncionario').first().text();
						amDefensor =$(xml).find('apellidoMaternoFuncionario').first().text();
						$(xml).find('funcionarioDTO').find('idCampo').each(function(){
							especialidadesDefensor=$(xml).find('valor').text();
						});
					});
			}
		});
		$("#camponombreDefensor").val(defensorNombre);
		$("#campoapDefensor").val(apDefensor);
		$("#campoamDefensor").val(amDefensor);
	}


	function pasavalor (id){  
		if(id=="integracion"){
			pasatipoDefensoria = "<%=TipoDefensoria.INTEGRACION.getValorId()%>";
			reloadGrid=true;
		}
		if(id=="tecnica"){
			pasatipoDefensoria = "<%=TipoDefensoria.TECNICA.getValorId()%>";
			reloadGrid=true;	
		}
		if(id=="restaurativa"){
			pasatipoDefensoria = "<%=TipoDefensoria.RESTAURATIVA.getValorId()%>";
			reloadGrid=true;
		}
		if(id=="externa"){
			pasatipoDefensoria = "<%=TipoDefensoria.EXTERNA.getValorId()%>";
			reloadGrid=true;
		}	
		grid2();
	}



	function designarAbogadoDefensorCoordinador(){
		var param = '';
		param += 'numSolicitud='+ numSOL;
		param += '&idDefensor='+ idDefensor
		$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/DesignarAbogadoDefensorExpediente.do',
				data: param,
				dataType: 'xml',
				async: true,
				success: function(xml){	}
			});

		parent.cerrarVentana();
	}

	function solicitarAbogadoDefensorExterno(){
		var forma=<%= Formas.SOLICITUD_DEFENSOR_EXTERNO.getValorId()%>;
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitar abogado externo", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?esconderArbol=0&formaId="+forma+"&numeroUnicoExpediente="+numEXP+"' width='1140' height='400' />");
	}

	function consultaDetalleExp(tipo) {
			$.ajax({type : 'POST',
					url : '<%= request.getContextPath()%>/consultaDetalleExpedienteDefensoria.do',
					data : 'numeroExpedienteId=' + numEXPID,
					async : false,
					dataType : 'xml',
					success : function(xml) {

						var etapa = $(xml).find('expediente').find('etapa').find('idCampo').first().text();

						if(etapa == <%= EtapasExpediente.INTEGRACION.getValorId()%>){
							pasavalor("integracion");	
							etapaTit = 	"integracion";							
						}
						if(etapa == <%= EtapasExpediente.CONCILIACION_Y_MEDIACION.getValorId()%>){
							pasavalor("restaurativa");
							etapaTit = 	"restaurativa";
						}
						if(etapa == <%= EtapasExpediente.EJECUCION.getValorId()%>){
							pasavalor("externa");
							etapaTit = 	"externa";
						}
						if(etapa == <%= EtapasExpediente.TECNICA.getValorId()%>){
							pasavalor("tecnica");
							etapaTit = 	"tecnica";
						}													
						if(etapa == <%= EtapasExpediente.ASESORIA.getValorId()%>){
							tipo = 4;						
						}						
					
						switch (parseInt(tipo)) {
						case 1:							
							llenaDetalleAD(xml);
							break;
						case 2: 
							llenaDetallePJ(xml);					
							break;
						case 3:
							llenaDetalleAT(xml);	
							break;
						case 4:
							llenaDetalleA(xml);	
							break;
						}
					}
				});
	}

	function llenaDetallePJ(xml){

		var expediente = $(xml).find('expediente').first();
		$('#casoPJ').val($(expediente).find('casoDTO').find('numeroGeneralCaso').first().text());
		$('#causaPJ').val();
		$('#tipoAudienciaPJ').val();
		$('#salaPJ').val();
		$('#expedientePJ').val($(expediente).find('numeroExpediente').first().text());
		$('#fechaHoraAudienciaPJ').val();						
		$('#etapaExpPJ').val($(expediente).find('etapa').find('valor').first().text());
		
		var nombre = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('nombre').first().text();
		var aPaterno = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
		var aMaterno = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('apellidoMaterno').first().text();

		$('#nombreImputadoPJ').val(nombre+" "+aPaterno+" "+aMaterno);
		$('#lugarDetencionPJ').val();
		$('#delitosPJ').val();
		$('#fechaHoraDetencionPJ').val();
		$('#fechaHoraSolicitudPJ').val();		

		}	

	function llenaDetalleAT(xml){		

		var expediente = $(xml).find('expediente').first();
		$('#casoAT').val($(expediente).find('casoDTO').find('numeroGeneralCaso').first().text());
		$('#detenidoAT').val();
		$('#expedienteAT').val($(expediente).find('numeroExpediente').first().text());
		$('#fechaHoraDetencionAT').val();						
		$('#etapaExpAT').val($(expediente).find('etapa').find('valor').first().text());
		
		$(xml).find('expediente').find('involucradosDTO').find('involucradoDTO').each(function(){
						
			if($(this).find('calidadDTO').find('valorIdCalidad').find('idCampo').text()== <%=Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()%>){
				
				var nombre = $(this).find('nombresDemograficoDTO').find('nombre').first().text();
				var aPaterno = $(this).find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
				var aMaterno = $(this).find('nombresDemograficoDTO').find('apellidoMaterno').first().text();
				$('#quienSolicitaAT').val(nombre+" "+aPaterno+" "+aMaterno);

				esDetenido = $(xml).find('esDetenido').first().text();
			}

		});

		if(esDetenido == "true"){		
			$("#detenidoAT").val("Si");
			
			//da formato a fecha
			var fecha = $(xml).find('expediente').find('involucradosDTO').find('involucradoDTO').find('detenciones').find('detencionDTO').find('strFechaFinDetencion').first().text();
	        var hora = fecha.split(" ")[1];
			hora = hora.substring(0,5);
			fecha = fecha.split(" ")[0];						
		    $("#fechaHoraDetencionAT").val(fecha.split("-")[2]+"/"+ fecha.split("-")[1]+"/"+fecha.split("-")[0]+"-"+hora );
			$("#lugarDetencionAT").val($(xml).find('expediente').find('involucradosDTO').find('involucradoDTO').find('detenciones').find('detencionDTO').find('lugarDetencionProvicional').first().text());
			$("#delitosAT").val($(xml).find('expediente').find('delitos').find('delitoDTO').find('delitoId').first().text());

			}else{								
			$("#detenidoAT").val("No");
			$("#lugarDetencionAT").val("----");
			$("#fechaHoraDetencionAT").val("----");
			$("#delitosAT").val("----");
			}					
		
		}	

	function llenaDetalleAD(xml){

		var expediente = $(xml).find('expediente').first();
		$('#casoAD').val($(expediente).find('casoDTO').find('numeroGeneralCaso').first().text());
		$('#expedienteAD').val($(expediente).find('numeroExpediente').first().text());
		$('#etapaExpAD').val($(expediente).find('etapa').find('valor').first().text());
		
		var nombre = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('nombre').first().text();
		var aPaterno = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
		var aMaterno = $(expediente).find('involucradosDTO').find('nombresDemograficoDTO').find('apellidoMaterno').first().text();

		$('#nombreImputadoAD').val(nombre+" "+aPaterno+" "+aMaterno);
		$('#delitosAD').val();			
		$('#lugarDetencionAD').val();
		$('#fechaHoraDetencionAD').val();
		$('#fechaHoraMensajeAD').val();		
		
		}	

	function llenaDetalleA(xml){		

		$("#detalleAsesoria").show();
		$("#datosInteresado").show();
		$("#motivoAsesoria").show();
		$("#atencionTemprana").hide();
			
		var expediente = $(xml).find('expediente').first();

		$("#expedienteA").val($(expediente).find('numeroExpediente').first().text());
		$("#etapaA").val($(expediente).find('etapa').find('valor').first().text());
		$("#fechaHoraSolicitudA").val($(xml).find('strFechaCreacion').first().text()+"-"+ $(xml).find('strHoraCreacion').first().text());			

		$(xml).find('expediente').find('involucradosDTO').find('involucradoDTO').each(function(){
			
			if($(this).find('calidadDTO').find('valorIdCalidad').find('idCampo').text()== <%=Calidades.SOLICITANTE.getValorId()%>){
				
				var nombre = $(this).find('nombresDemograficoDTO').find('nombre').first().text();
				var aPaterno = $(this).find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
				var aMaterno = $(this).find('nombresDemograficoDTO').find('apellidoMaterno').first().text();
				$('#interesadoA').val(nombre+" "+aPaterno+" "+aMaterno);

				id = $(this).find('elementoId').first().text();				
			}
		});

		consultaDetalle();
					
		}	

	//consulta el detalle del involucrado
	function consultaDetalle(){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
	    	  data: 'idInvolucrado='+id,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  datosXML=xml;
	    		  pintaDatosGenerales(xml);
	    		  pintaDatosDomicilio(xml);
	    		  pintaDatosContacto(xml);
	    		  idindi=id;
	    		  mediosContactoCorreoActualiza();
	    		  mediosContactoTelefonoActualiza();
	    					    	     
			  }
	    });

		desavilitarDatosGenerales();
		desavilitarDatosDomicilio();
		
		 $('.jquery_ckeditor').val($(this).find('descNarrativa').text());
   	 	 CKEDITOR.on("instanceReady", function (ev){
 	        var bodyelement = ev.editor.document.$.body;
 	        bodyelement.setAttribute("contenteditable", false);
 	    });
 	   // CKEDITOR.replace('editor1');
		
	}

	//se ocupa para consultar los documentos asociados
	function documentos(){
			
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDocumentosDefensoria.do?tipo=2&idExpedienteop='+numEXPID, 
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pager1'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: false,
				width:800,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					consultaPDF(id);
					},
				sortorder: "desc"
			});
				
		}
	
</script>

<title>Asignar abogado</title>
</head>
<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li id="tabResumen"><a href="#tabsconsultaprincipal-1">Resumen</a></li>
			<li id="tabAsignarDefensor"  style="display: none" ><a href="#tabsconsultaprincipal-2">Asignar defensor</a></li>
			<li id="datosInteresado" style="display: none"><a href="#tabsconsultaprincipal-3">Datos del interesado</a></li>
			<li id="motivoAsesoria"  style="display: none"><a href="#tabsconsultaprincipal-4">Motivo de asesoría</a></li>
			<li id="tabDocumentos"  style="display: none"><a href="#tabsconsultaprincipal-5" onclick="documentos()">Documentos</a></li>
		</ul>

		<!--Comienza tabprincipal 1-->
		<div id="tabsconsultaprincipal-1">		
			<div id="atencionTemprana" style="display: none">
			
				<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="5">
				<tr>
					<td width="22%" align="right">&nbsp;</td>
					<td width="20%">&nbsp;</td>
					<td align="right" id="etiquetaDetenido">&nbsp;</td>
					<td id="campoDetenido">&nbsp;</td>
				</tr>
				<tr>
					<td width="22%" align="right"><strong>Número de
							caso:</strong>
					</td>
					<td width="20%"><input type="text" id="casoAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaDetenido"><strong>Detenido:</strong></td>
					<td id="campoDetenido"><strong>
					  <input type="text"
							id="detenidoAT" style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
					  </strong></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaNumeroExpediente"><strong>Numero de expediente: </strong></td>
				  <td id="campoNumeroExpediente"><input type="text" id="expedienteAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaLugarDetencion"><strong>Lugar
				    donde se encuentra detenido:</strong></td>
				  <td id="campoLugarDetencion"><input type="text"
						id="lugarDetencionAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaEtapaExpediente"><strong>Etapa del expediente:</strong></td>
				  <td id="campoEtapaExpediente"><input type="text" id="etapaExpAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaFechaHoraDetencion"><strong>Fecha y hora de la
				    detenci&oacute;n:</strong></td>
				  <td  id="campoFechaHoraDetencion"><strong>
				    <input type="text"
							id="fechaHoraDetencionAT"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
				    </strong></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaQuienSolicita"><strong>Para
				    quien se soilcita:</strong></td>
				  <td id="campoQuienSolicita"><input type="text"
						id="quienSolicitaAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaFechaHoraDetencion">&nbsp;</td>
					<td  id="campoFechaHoraDetencion">&nbsp;</td>
				</tr>
				<tr>
				  <td align="right"><strong>Delito(s):</strong></td>
				  <td><input type="text" id="delitosAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaFechaHoraAtencion"><strong>Fecha
				    y hora  limite de atenci&oacute;n:</strong></td>
				  <td id="campoFechaHoraAtencion"><input type="text"
						id="fechaHoraAtencionAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaQuienSolicita">&nbsp;</td>
				  <td id="campoQuienSolicita">&nbsp;</td>
				  <td align="right" id="etiquetaFechaHoraSolicitud"><strong>Fecha
				    y hora de solicitud:</strong></td>
				  <td id="campoFechaHoraSolicitud"><input type="text"
						id="fechaHoraSolicitudAT"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
			</table>		
				
			</div>
		
			<div id="poderJudicial" style="display: none">
					<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="5">
				<tr>
					<td width="22%" align="right">&nbsp;</td>
					<td width="20%">&nbsp;</td>
				  <td width="29%">&nbsp;</td>
				  <td width="29%">&nbsp;</td>
				</tr>
				<tr>
					<td width="22%" align="right"><strong>Número de
							caso:</strong>
					</td>
					<td width="20%"><input type="text" id="casoPJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				  <td align="right" id="etiquetaTipoAudiencia"><strong>Tipo
				  de Audiencia:</strong></td>
			  <td id="campoTipoAudiencia"><strong>
					  <input
							type="text" id="tipoAudienciaPJ"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
					  </strong></td>
				</tr>
				<tr>
					<td align="right" id="etiquetaCausa"><strong>Número
							de causa:</strong>
					</td>
					<td id="campoCausa"><input type="text" id="causaPJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaSalaDesignada"><strong>Sala
					  designada:</strong></td>
					<td id="campoSalaDesignada"><strong>
					  <input
							type="text" id="salaPJ"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
					  </strong></td>
				</tr>
				<tr>
					<td align="right" id="etiquetaNumeroExpediente"><strong>Numero de expediente: </strong>
					</td>
					<td id="campoNumeroExpediente"><input type="text" id="expedientePJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaFechaHoraAudiencia"><strong>Fecha
					  y hora de audiencia:</strong></td>
				  <td id="campoFechaHoraAudiencia"><strong>
					  <input
							type="text" id="fechaHoraAudienciaPJ"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
				  </strong></td>
				</tr>
				<tr>
					<td align="right" id="etiquetaEtapaExpediente"><strong>Etapa del expediente:</strong>
					</td>
					<td id="campoEtapaExpediente"><input type="text" id="etapaExpPJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaLugarDetencion">&nbsp;</td>
				  <td id="campoLugarDetencion">&nbsp;</td>
				</tr>
				<tr>
					<td align="right" id="etiquetaNombreImputado"><strong>Nombre del imputado:</strong>
					</td>
					<td id="campoNombreImputado"><input type="text" id="nombreImputadoPJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				  <td align="right" id="etiquetaLugarDetencion"><strong>Lugar
				  donde se encuentra detenido:</strong></td>
				  <td id="campoLugarDetencion"><input type="text"
						id="lugarDetencionPJ"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right"><strong>Delito(s):</strong>
					</td>
					<td><input type="text" id="delitosPJ"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaFechaHoraDetencion"><strong>Fecha y hora de la
					  detenci&oacute;n:</strong></td>
				  <td  id="campoFechaHoraDetencion"><strong>
					  <input type="text"
							id="fechaHoraDetencionPJ"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
				  </strong></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaQuienSolicita">&nbsp;</td>
				  <td id="campoQuienSolicita">&nbsp;</td>
				  <td align="right" id="etiquetaFechaHoraSolicitud"><strong>Fecha
				    y hora de solicitud:</strong></td>
				  <td id="campoFechaHoraSolicitud"><input type="text"
						id="fechaHoraSolicitudPJ"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
			    </tr>
			</table>					
			</div>
			<div id="avisosPersonasDetenidas" style="display: none">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="5">
				<tr>
					<td width="22%" align="right">&nbsp;</td>
					<td width="20%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
				</tr>
				<tr>
					<td width="22%" align="right"><strong>Número de
							caso:</strong>
					</td>
					<td width="20%"><input type="text" id="casoAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaLugarDetencion"><strong>Lugar
					donde se encuentra detenido:</strong></td>
					<td id="campoLugarDetencion"><input type="text"
						id="lugarDetencionAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaNumeroExpediente"><strong>Numero de expediente: </strong></td>
				  <td id="campoNumeroExpediente"><input type="text" id="expedienteAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaFechaHoraDetencion"><strong>Fecha y hora de la
				    detenci&oacute;n:</strong></td>
				  <td  id="campoFechaHoraDetencion"><strong>
				    <input type="text"
							id="fechaHoraDetencionAD"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
				    </strong></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaEtapaExpediente"><strong>Etapa del expediente:</strong></td>
				  <td id="campoEtapaExpediente"><input type="text" id="etapaExpAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				  <td align="right" id="etiquetaFechaHoraMensaje"><strong>Fecha
				    y hora de envio del aviso:</strong></td>
				  <td id="campoFechaHoraMensaje"><strong>
				    <input
							type="text" id="fechaHoraMensajeAD"
							style="width: 150px; border: 0; background: #DDD;"
							readonly="readonly" />
				    </strong></td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaNombreImputado"><strong>Nombre del imputado:</strong></td>
				  <td id="campoNombreImputado"><input type="text" id="nombreImputadoAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right" id="etiquetaFechaHoraDetencion">&nbsp;</td>
					<td  id="campoFechaHoraDetencion">&nbsp;</td>
				</tr>
				<tr>
				  <td align="right"><strong>Delito(s):</strong></td>
				  <td><input type="text" id="delitosAD"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right" id="etiquetaNombreDefensor">&nbsp;</td>
					<td id="campoNombreDefensor">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right" id="etiquetaFechaHoraMensaje">&nbsp;</td>
					<td id="campoFechaHoraMensaje">&nbsp;</td>
				</tr>
			</table>		
			</div>
			<!-- Inicia div del detalle de asesoria -->
			<div id="detalleAsesoria" style="display: none">
					<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="5">
				<tr>
					<td width="22%" align="right">&nbsp;</td>
					<td width="20%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right" id="etiquetaNumeroExpediente"><strong>Numero de expediente: </strong>
					</td>
					<td ><input type="text" id="expedienteA"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaFechaHoraSolicitud"><strong>Fecha
					  y hora de solicitud:</strong></td>
					<td ><input type="text"
						id="fechaHoraSolicitudA"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right" id="etiquetaEtapaExpediente"><strong>Etapa del expediente:</strong>
					</td>
					<td id="campoEtapaExpediente"><input type="text" id="etapaA"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td align="right" id="etiquetaNombreDefensor">&nbsp;</td>
					<td id="campoNombreDefensor">&nbsp;</td>
				</tr>
				<tr>
				  <td align="right" id="etiquetaInteresado"><strong>Interesado:</strong></td>
				  <td id="campoFechaHoraSolicitud"><input type="text"
						id="interesadoA"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" /></td>
					<td align="right" id="etiquetaFechaHoraMensaje">&nbsp;</td>
					<td id="campoFechaHoraMensaje">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right" id="etiquetaDetenido">&nbsp;</td>
					<td id="campoDetenido">&nbsp;</td>
				</tr>
			</table>
			</div>
			<!-- Termina div del detalle de asesoria -->	
		</div>
		<!--Termina tabprincipal 1-->
		
		<!--Comienza tabprincipal 2-->
		<div id="tabsconsultaprincipal-2" >

			<table width="80%" align="center"
				style="border-left: 1; border-right: 1; border-bottom: 1; border-top: 1;">
				<tr align="center"  class="toolbar fondoFuerteAP">
					<td width="10%" align="center">Tipo Defensa</td>
					<td width="70%" align="center">Disponibilidad</td>
					<td width="20%" align="center">Información de Defensor</td>
				</tr>
				<tr>
					<td><table height="" width="">
							<tr>
								<td><input type="button" id="integracion"
									onclick="pasavalor(this.id)" id="integracion"
									value="Integración" style="cursor: pointer;" class="btn_Generico">
								</td>
							</tr>
							<tr>
								<td><input type="button" onclick="pasavalor(this.id)"
									id="tecnica" style="cursor: pointer;" value="Técnica" class="btn_Generico">
								</td>
							</tr>
							<tr>
								<td><input type="button" onclick="pasavalor(this.id)"
									id="restaurativa" style="cursor: pointer;"
									value="Conciliación y mediación" class="btn_Generico">
								</td>
							</tr>
							<tr>
								<td><input type="button" onclick="pasavalor(this.id)"
									id="externa" style="cursor: pointer;" value="Externa" class="btn_Generico">
								</td>
							</tr>
						</table></td>
					<td valign="top"><table width="100%">

							<tr>
								<td width="100%" height="100%"><div id="nabtabgrid">
										<center>
											<table id="tabgrid" align="center" disabled="disabled"></table>
										</center>
									</div></td>
							</tr>
						</table></td>
					<td><table>

							<tr style="display: none">
								<td>Tipo de defensa:</td>
								<td><input type="text" class="transpa" disabled="disabled"
									id="tipoDefensa">
								</td>
							</tr>
							<tr>
								<td>Nombre</td>
								<td><input id="camponombreDefensor" type="text" border="0"
									class="transpa" disabled="disabled"></td>
							</tr>
							<tr>
								<td>Apellido Paterno</td>
								<td><input id="campoapDefensor" type="text" border="0"
									class="transpa" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td>Apellido Materno</td>
								<td><input id="campoamDefensor" type="text" border="0"
									class="transpa" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td>Correo Electrónico</td>
								<td><input id="" type="text" border="0" class="transpa"
									disabled="disabled">
								</td>
							</tr>
							<tr>
								<td>Numero de Teléfono</td>
								<td><input id="" type="text" border="0" class="transpa"
									disabled="disabled">
								</td>
							</tr>
							<tr>
								<td>Especialidad(es)</td>
								<td><textarea name="" cols="18" rows="2"
										id="campoEspecialidadDefensor" class="transpa" onclick=""></textarea>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
			<center>
				<input type="button" value="Solicitar Abogado Externo"
					id="solicitarAbogadoExterno"
					onclick="solicitarAbogadoDefensorExterno()" /> <input type="button"
					value="Seleccionar Abogado Para Defensa"
					onclick="designarAbogadoDefensorCoordinador()">
			</center>


		</div>
		<!--Termina tabprincipal 2-->
		<!--Comienza Div Principal 3-->
		<div id="tabsconsultaprincipal-3">
			  <!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
                <div id="iProbResponsablePane">
                  <dl>
                    <dt id="cejaDatosGenerales">Datos Generales</dt>
                      <dd>	
                        <jsp:include page="datosGeneralesView.jsp"/>
                      </dd>
                    <dt id="cejaDomicilio">Domicilio</dt>
                      <dd>
                        <jsp:include page="ingresarDomicilioView.jsp"/>
                      </dd>
                      <dt id="cejaMediosContacto">Medios de Contacto</dt>
                        <dd>
                          <jsp:include page="ingresarMediosContactoView.jsp"/>
                        </dd>
                      </dl>
                    </div>	
		</div>
		<!--Termina Div Principal 3-->
		<!--Comienza Div Principal 4-->
		<div id="tabsconsultaprincipal-4" >
			<textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea>
		</div>
		<!--Termina Div Principal 4-->
		
		<!--Comienza tabprincipal 5-->
		<div id="tabsconsultaprincipal-5" >
			<table id="gridDetalleFrmPrincipal"></table>
				<div id="pager1"></div>
				<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
						<input type="hidden" name="documentoId" />
					</form>
				<form name="frmDoc2" action="<%= request.getContextPath() %>/GenerarDocumentoDirecto.do" method="post">
						<input type="hidden" name="documentoId" />
						<input type="hidden" name="numeroUnicoExpediente" />
				</form>
		</div>
		<!--Termina tabprincipal 3-->
	</div>

</body>
</html>