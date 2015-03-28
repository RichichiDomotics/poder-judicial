<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.tipoatencion.TiposAtencion"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades"%>
<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Persona</title>

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
	WIDTH: 1000px;
	HEIGHT: 400px
}

/*acordeon editar*/
#iProbResponsablePane DT {
	TEXT-ALIGN: right;
	PADDING-BOTTOM: 16px;
	PADDING-TOP: 2px;
	PADDING-LEFT: 0px;
	LINE-HEIGHT: 35px;
	TEXT-TRANSFORM: none;
	/*acomodo texto*/
	PADDING-RIGHT: 40px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	LETTER-SPACING: 1px;
	/*distancia persianas*/
	HEIGHT: 25px;
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
	COLOR: #68889b;
	FONT-WEIGHT: bold;
	LEFT: 30px
}

#iProbResponsablePane .active .slide-number {
	COLOR: #fff
}

#iProbResponsablePane A {
	COLOR: #58595b;
	font-family: Arial, Helvetica, sans-serif;
}

#iProbResponsablePane DD IMG {
	MARGIN: 0px;
	FLOAT: right
}

#iProbResponsablePane H2 {
	MARGIN-TOP: 10px;
	FONT-SIZE: 2.5em
}

#iProbResponsablePane .more {
	DISPLAY: block;
	PADDING-TOP: 10px
}
</style>

<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />

<!--    Hoja de estilo para easyaccordion-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--script de jquery UI-->
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script> -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prettify.js"></script>

<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script> -->

<!--Hojas de estilos para los componentes UI de Jquery-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" /> -->

<!--Hoja de estilos para el grid-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />

<!--scripts de java script-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>-->



<!--Hoka de estilo para el texto dentro de los acordeones-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<!--Hoja de estilo para los popups-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--Scripts necesarios para el funcionamiento de la JSP-->

<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script> -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<!--Scrip para el idioma del calendario-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<!--Scripts necesarios para la ejecucion del editor-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>

<!--scripts del gird-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<script type="text/javascript">

	  	var idWindowIngTutor = 1;
		var varNombre;		
		var banderaDenuncia=0;
		var banderaDenuncianteQuerellante=0;
		var banderaNarrativa=0;
		var idindi=0;
		var numeroExpedienteTempAdmin="";
		var idExpedienteTempAdmin="";
		var idNumeroExpedienteConsul="";
		var idOrganizacion=0;
		var verAlias;
		
		var idActaCircunstancial=0;
		var idHecho=0;
		var idDomicilio=0;
		
		var narrativaConsultada="";
		
		var tipo= '<%=request.getParameter("tipo") != null ? request.getParameter("tipo") : ""%>';
			
		jQuery().ready(	function () {

			//consulta los tipos de Atencion no penal
			consultarTipoAtencionAtencionNoPenal();

				if(tipo!= null && tipo!=0 && tipo!=""){
					//alert("entra  a detalle");
					consultaDetalle(tipo);			
					$('#btnGuardarHechos').hide();	
					$('#botonGenerarActa').hide();
				}
	
				idExpedienteTempAdmin='<%=request.getParameter("idExpedienteTempAdmin")%>';
				numeroExpedienteTempAdmin='<%=request.getParameter("numeroExpedienteTempAdmin")%>';
				
				//*******comienza funcionailidad actuaciones***********/
				cargaActuaciones();

				//Para escuchar los eventos de psicologico
				$("#cbxTipoDeAtencion").change(seleccionaActuacion);
				//*******termina funcionailidad actuaciones***********/
				
				//revisamos la operacion a realizar
				var op='<%=request.getParameter("operacion")%>';
								
				//se generan las tabs del domicilio
				$("#tabs" ).tabs();
				//Se crean las tabs principales
				$("#tabsprincipalconsulta" ).tabs();
				//ocultamos el domicilio de notificaciones
				killDomicilioNotificaciones();
				//se genera las pestañas de domicilio
				$('#iProbResponsablePane').easyAccordion({ 
			  autoStart: false, 
			  slideInterval: 3000
			});
			
			$("#btnGuardarHechos").click(guardaDenuncianteTempAdmin);
			$("#btnModificar").click(habilitaCampos);
			
			
			//Simulamos el contexto para el denunciante
			banderaDenunciaQuerella=1;//1 es denuncia,0 es querella
			// 1 es denunciante o querellante,depende de la bandera anterior,0 es probable responsable
			banderaDenuncianteQuerellante=0;
			
			//Lanzamos la consulta de los documentos
			jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idExpedienteTempAdmin, 
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200,hidden:true},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pager1'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: false,
				width:800,
				height:250,
				sortname: 'turno',
				viewrecords: true,
				id: 'divgrid',
				onSelectRow: function(id){
					consultaPDF(id);
					},
				sortorder: "desc"
			});//.navGrid('#pager1',{edit:false,add:false,del:false});
			
			//revisamos si es una consulta o una insercion
			if(op!='null')
			{
				//Consulta
				consultaActaCircunstanciada(idExpedienteTempAdmin,numeroExpedienteTempAdmin);
				documentos();
				$("#btnGuardarHechos").hide();
				$("#btnModificar").show();
				$("#btnModificar").attr("disabled","");
				$("#botonGenerarActa").show();
				if(jQuery("#gridDetalleFrmPrincipal").getDataIDs().length==0)
				{
					$("#botonGenerarActa").attr("disabled","");
				}
				else
				{
					$("#botonGenerarActa").attr("disabled","");//aqui se deshabilitaba
				}
				consultaTiposAtencionSeleccionados();
			}
			else
			{
				//insercion
				$("#btnModificar").hide();
				$("#btnGuardarHechos").show();
				$("#btnGuardarHechos").attr();
				$("#botonGenerarActa").show();
				$("#botonGenerarActa").attr("disabled","disabled");
			}
  		});
		//TERMINA function On Ready
		
		function consultaPDF(id){
			//alert(id);
			document.frmDoc.documentoId.value = id;
			document.frmDoc.submit();
		}
		/*
		*Funcion para consultar el acta circunstanciada
		*/
		function consultaActaCircunstanciada(idExp,numExp)
		{
			//mandamos consultar a BD
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/consultarActaCircunstanciada.do',
				data: 'idExpAtAdmin='+idExp+'&numExpAtAdmin='+numExp,
				dataType: 'xml',
				async: false,
				success: function(xml){
					if(parseInt($(xml).find('code').text())==0)
		    		{
						$('.jquery_ckeditor').val($(xml).find('hechoDTO').find('descNarrativa').text());
						narrativaConsultada=$(xml).find('hechoDTO').find('descNarrativa').text();
						idActaCircunstancial=$(xml).find('expedienteDTO').find('expedienteId').text();
						//alert("idActa:: "+idActaCircunstancial);
						idDomicilio=$(xml).find('involucradoDTO').find('domicilio').find('elementoId').text();
						//alert("idDomicilio:: "+idDomicilio);
						idHecho=$(xml).find('hechoDTO').find('hechoId').text();
						//alert("idHecho:: "+idHecho);
						if(idHecho!=0)
						{
							$("#btnModificar").show();
						}
						pintaDatosGeneralesActaCirc(xml);
						desavilitarDatosGenerales();
						pintaDatosDomicilio(xml);
						pintaDatosContacto(xml);
						pintaDatosTipoDocIdentificacion(xml);
						disparaConsultaGridsMediosDeContacto($(xml).find('actaDTO').find('involucradoDTO').find('elementoId').first().text());
						desavilitarDatosDomicilio();
						$("#codigoPostalButton").attr("disabled","disabled");
						CKEDITOR.on("instanceReady", function (ev) {
		    	  	        var bodyelement = ev.editor.document.$.body;
		    	  	        bodyelement.setAttribute("contenteditable", false);
		    	  	    });
		    	  	    CKEDITOR.replace('editor1');
		    		}
				}
			});	
		}
		
		/*
		*Funcion que validará los datos obligatorios:
		* Nombre del solicitante
		* Motivo de la solicitud
		*/
		function validaCamposInsercion()
		{
			var motivo=escape($('.jquery_ckeditor').val());
			if(trim(motivo).length==0)
			{
				return false;
			}
			if(trim($('#datosGeneralesCmpNombres').val()).length==0)
			{
				return false;
			}
			if(trim($('#datosGeneralesCmpApaterno').val()).length==0)
			{
				return false;
			}
			if(trim($('#datosGeneralesCmpMaterno').val()).length==0)
			{
				return false;
			}
			return true;
		}
		
		function guardaDenuncianteTempAdmin(){
			
			if(validaCamposInsercion()){
				
				//var tipoAtten  = validaTiposDeAtencion();
				
				//if(tipoAtten != false){
				
					var parametros="";
					parametros += 'calidadDelIndividuo=0';
					parametros += '&numExpediente='+numeroExpedienteTempAdmin;
					
					//extraemos la descripcion del hecho
					parametros +="&motivoComparecencia="+escape($('.jquery_ckeditor').val());
					
					//Datos generales, media filiacion, medios de contacto, documentos de identificacion
					var datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
					parametros += datosPestania;
		
					//Datos nacimiento
					datosPestania = obtenerParametrosDatosNacimiento();
					parametros += datosPestania;
					
					//recuperamos los datos de lugar, ya trae el & para la union
					parametros += obtenerParametrosDomicilio();
					
					//Medios de contacto
					datosPestania = obtenerMedios();
					parametros += datosPestania;
					
					//Documento de identificación
					datosPestania = '&';
					datosPestania = recuperaDatosTipoDocIdentificacion();					
					parametros += datosPestania;
								
					//regresamos la cadena con los datos del Hecho
					parametros += "&idUsuario=1";
					
					parametros += "&idActaCircunstancial="+idActaCircunstancial;
					parametros += "&idHecho="+idHecho;
					parametros += "&idDomicilio="+idDomicilio;
					//parametros += "&idsTiposSolicitud=" +tipoAtten;
									
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/ingresarActaCircunstanciada.do',
				    	  data: parametros,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('expedienteDTO').find('expedienteId').text())>0){  
								  //guardamos la referencia a los IDs necesarios para la modificacion
								  documentos();
								  idActaCircunstancial=$(xml).find('expedienteDTO').find('expedienteId').text();
								  idDomicilio=$(xml).find('involucradoDTO').find('domicilio').find('elementoId').text();
								  idHecho=$(xml).find('hechoDTO').find('hechoId').text();  
								  narrativaConsultada=$(xml).find('hechoDTO').find('descNarrativa').text();
								  //configuramos los botones
								  $("#botonGenerarActa").show();
								  $("#btnModificar").show();
								  $("#btnModificar").attr('disabled','');
								  $("#btnGuardarHechos").hide();
								  $("#btnGuardarHechos").attr('disabled','');
								  alert('Se guardó la información exitosamente.\nFavor de generar el Acta Circunstanciada.');
								  if(jQuery("#gridDetalleFrmPrincipal").getDataIDs().length==0)
								  {
										$("#botonGenerarActa").attr("disabled","");
								  }
								  else
								  {
									  $("#botonGenerarActa").attr("disabled","");//aqui se deshabilitaba
								  }
								  deshabilitaCampos();
								  //$('.jquery_ckeditor').val(narrativaConsultada);
							  }
				    	  }
				    	});
				//}
			}
			else{
				alert("La información obligatoria es el nombre del solicitante y el motivo de la solicitud.");
			}
		}
		
		function generaOficioActa(){
			$("#botonGenerarActa").attr("disabled","disabled");
			            
            $.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Elaborar Acta Circunstanciada", type:"iframe"});
		    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId=2&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'" width="1140" height="400" />');
		    
            //document.formaDocDirecto.numeroUnicoExpediente.value = numeroExpedienteTempAdmin;
			//document.formaDocDirecto.submit();
		}

/*
 *COMIENZAN FUNCIONES PARA EL CU CONSULTAR PROBABLE RESPONSABLE
 */  


 
	 	/*
	  	 *Funcion que realiza la consulta de los datos del probable responsable
	     */
	  	function muestraDatosProbResponsable(id) {
		  
		   $.ajax({
		     type: 'POST',
		     url: '<%=request.getContextPath()%>/ConsultarIndividuoDatos.do',
			 data: 'idInvolucrado='+id,
			 dataType: 'xml',
			 success: function(xml){
				muestraDatosPersona(xml);
			  }
			});
		 }

	  /*
	   *Funcion que muestra los datos del involucrado de acuerdo a su condicion, es
	   *decir Vivo, Muerto, Desconocido, simpre y cuando, sea persona física
	   */
		function muestraDatosCondicion(xml){

			if($(xml).find('esVivo').text() == "1"){
				 $("#btnPResponsableEsVivo").attr('checked','checked');
			}
			else if($(xml).find('esVivo').text() == "0"){
				$("#btnPResponsableEsMuerto").attr('checked','checked');
			}
			else if($(xml).find('esDesconocido').text() == "true"){	
				$("#btnPResponsableDesconocido").attr('checked','checked');
				$("#btnPResponsableDesconocido").click();
			}
		}

	  /*
	   *Funcion que muestra los datos de la detencion, cuando el prob responsable es
	   *persona fisica
	   */		
		function muestraDatosDetenido(xml){
			
			if($(xml).find('esDetenido').text() == "true"){
				 $("#chkPResponsableDetenido").click();
			}
			$('#textNarrativa').val($(xml).find('expedienteDTO').find('narrativa').text());
			cambiaOtro();
			banderaNarrativa=1;
		}

	  /*
	   *Funcion que verifica si el prob responsable es una persona física o moral
	   *y oculta o muestra los datos dependiendo de ello
	   */	
		function muestraDatosPersona(xml){

			var idTipoPersona = $(xml).find('tipoPersona').text();
			//var idTipoPersona = 0;
			
			$('#cbxProbResponsableTipoResp').find("option[value='"+idTipoPersona+"']").attr("selected","selected");
			//$('#cbxProbResponsableTipoResp').find("option[value='"+0+"']").attr("selected","selected");
			
			if($(xml).find('tipoPersona').text() == "1"){
			//if( idTipoPersona == 1){
				muestraDatosCondicion(xml);
				muestraDatosDetenido(xml);
				pintaDatosGenerales(xml);///////////////////////////////
				pintaDatosMediaFiliacion();
		    	espejoDatos();//////////////////////////////
				//setea los tipos de documento de identificacion
				pintaDatosTipoDocIdentificacion(xml);
			}
			else if($(xml).find('tipoPersona').text() == "0"){
			//else if(idTipoPersona == 0){
				seteaDatosPersonaMoralConsOrg(xml);
				onSelectChangeTipoPersonaMoral();
				
			}
		}

	/*
	 *TERMINAN FUNCIONES PARA EL CU CONSULTAR PROBABLE RESPONSABLE
	 */
		/*
		 *Imprime los datos que vienen de la funcion espejoDatos de datos generales, 
		 *en la pantalla ingresar probable responsable
		 */
		function imprimeDatosPadre(nombre, apPat, apMat){
		  document.getElementById('nombProResponsable').value=nombre;
		  document.getElementById('apPatProbResponsable').value=apPat;
		  document.getElementById('apMatProbResponsable').value=apMat;
		}
		
		
		/*
		 *Imprime los datos que vienen de la funcion espejoDatosNarrativa de texto narrativa
		 *en la pantalla ingresar probable responsable
		 */
		function imprimeDatosPadreNarrativa(textoNarrativa){
		  $("#textNarrativa").val(textoNarrativa);
		}

		/*
		*Limpia los datos de la ceja datos generales 
		*/
		function limpiaCejaDatosGenerales(){
		  //El padre invoca una función del hijo
		  cleanDatosGenerales();  
		}			
			
			
		function btnGuardarOrganizacionCU(){
			validaDatosGeneralesOrganizacion();
		}

		/**
		* Función que guarda los datos de la pantalla
		*/
		function guardarProbResponsable()
		{
			var params = '';
			params += 'gcDescripcionHecho='+idindi;
			params += '&calidadDelIndividuo=0';
			params += '&numeroExpediente='+numeroExpediente;
			
			params += '&motivoComparecencia='+$("#editor1").val();

			//Datos generales, media filiacion, medios de contacto, documentos de identificacion
			var datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
			params += datosPestania;

			//Datos nacimiento
			datosPestania = obtenerParametrosDatosNacimiento();
			params += datosPestania;
			
			//Domicilio
			datosPestania = obtenerParametrosDomicilio();
			params += datosPestania;

			//Medios de contacto
			datosPestania = obtenerMedios();
			params += datosPestania;

			//alert(params);
			
			//alert('antes de la funcion ajax');
			$.ajax({								
		    	  type: 'POST',
		    	  url: '<%=request.getContextPath()%>/ingresarActaCircunstanciada.do',
		    	  data: params,				
		    	  dataType: 'xml',
		    	  success: function(xml){
		    		  window.parent.cargaProbableResponsable($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
					  if($(xml).find('IngresarIndividuoForm').find('estaDetenido').text() == 'true'){
			    		  window.parent.muestraMenuQuienDetuvo();
					  }	
		    		  idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
		    		  alert('Probable Responsable Guardado');
		    	  }
		    	});
		}

			function consultaDetalle(id){
				$.ajax({
			    	  type: 'POST',
			    	  url:  '<%=request.getContextPath()%>/consultarInvolucrado.do',
			    	  data: 'idInvolucrado='+id,
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
			    		  datosXML=xml;
			    		  //alert($(xml).find('nombresDemograficoDTO').find('nombre').text());
			    		  pintaDatosGenerales(xml);
			    		  pintaDatosDomicilio(xml);
			    		 // pintaDatosMediaFiliacion(xml);
			    		  pintaDatosTipoDocIdentificacion(xml);
					  }
			    });
				//idindi=id;
				desavilitarDatosGenerales();
				desavilitarDatosDomicilio();
				//deshabilitaDatosMediaFiliacion();
				//desavilitarDatosIdentificacion();
				//$('#iVictimaBtnModificarDatos').show();
				//$('#iVictimaBtnGuardar').hide();
			}
			
			function documentos(){


				 jQuery("#gridDetalleFrmPrincipal").jqGrid('setGridParam',  
							{url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idExpedienteTempAdmin, 
							datatype: "xml" });
						 $("#gridDetalleFrmPrincipal").trigger("reloadGrid"); 
						
			}
			
		function deshabilitaCampos()
		{
			desavilitarDatosGenerales();
			desavilitarDatosDomicilio();
			$("#codigoPostalButton").attr("disabled","disabled");
			bloqueaCamposMediosDeContactoGrid();
			CKEDITOR.instances.editor1.destroy();
			$('.jquery_ckeditor').val(narrativaConsultada);
			$('.jquery_ckeditor').ckeditor(config);
			//$('.jquery_ckeditor').val(narrativaConsultada);
			CKEDITOR.on("instanceReady", function (ev) {
  	             var bodyelement = ev.editor.document.$.body;
	  	        bodyelement.setAttribute("contenteditable", false);
	  	    });
			//CKEDITOR.replace('editor1');
		}
		
		function habilitaCampos()
		{
			avilitarDatosGenerales();
			avilitarDatosDomicilio();
			$("#codigoPostalButton").attr("disabled","");
			desbloqueaCamposMediosDeContactoGrid();
			$("#btnModificar").hide();
	  	  	$("#btnGuardarHechos").show();
			$('.jquery_ckeditor').val(narrativaConsultada);
			CKEDITOR.on("instanceReady", function (ev) {
	  	        var bodyelement = ev.editor.document.$.body;
	  	        bodyelement.setAttribute("contenteditable", true);
	  	    });
	  	    CKEDITOR.replace('editor1');
		}

		/*
		*Funcion que consulta el tipo de atencion para atPenal
		*/
		function consultarTipoAtencionAtencionNoPenal(){

			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%=request.getContextPath()%>/consultarCatalogoTipoAtencionAtNoPenal.do',
		    	  data: '',
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    		  dibujaTiposAtencionNoPenal(xml);
				  }
		    });
		}


		//Variable para almacenar los ids de los tipos de atencion
		var idsCheckbox = new Array();

		/*
		*Funcion que dibuja el nombre de los tipos de atencion
		*/
		function dibujaTiposAtencionNoPenal(xml){
			var contIds = 0;
			$(xml).find('catTipoAtencion').each(function(){
				
				trTabla = '<td><input type="checkbox" disabled="disabled" value="'+$(this).find("clave").text()+'" name="tiposSolicitud" id="check_'+$(this).find("clave").text()+'"><span>'+$(this).find("valor").text()+'</span></td>';
				$('#tblTiposSol').append(trTabla);
				idsCheckbox[contIds] = $(this).find("clave").text();
				contIds++;
			});
		}

		/*
		*Funcion que "checa" los check box de tipo de atencion seleccionada
		*desde el generarDocumentoSinCaso.jsp, es llamada a traves del parametro "pintaTiposAtencion"
		*que corresponde con la enum de Tipo de Atencion seleccionada por el usuario, a traves de la 
		*actuacion
		*/
		function pintaCheckTipoAtencion(pintaTiposAtencion){
			$('#check_'+pintaTiposAtencion).attr('checked',true);
		}
		
		/*
		*Funcion que recopila los ids de tipos de atencion solicitados
		*/
		function guardaChecks(){

			var tiposAttnSelected = new Array();
			var contSelected = 0;
			for(i=0;i<idsCheckbox.length;i ++){
				if($('#check_'+idsCheckbox[i]).is(':checked')){
					tiposAttnSelected[contSelected] = $('#check_'+idsCheckbox[i]).val();
					contSelected++;
				}
			}
			return(tiposAttnSelected);
		}

		/*
		*Funcion que valida que se haya seleccionado almenos un tipo de atención
		*/
		//function validaTiposDeAtencion(){
		//	var tipoAtten = guardaChecks();
		//	if(tipoAtten.length <=0){
		//		alert("Seleccione almenos un tipo de atención");
		//		return false;
		//	}
		//	else{
		//		return tipoAtten;
		//	}
		//}

	//*********************************************Funcionalidad para le ceja de solicitar atecion********************************************/
	
	/*
	*Funcion que realiza la carga del combo de Actuaciones
	*/
	function cargaActuaciones() {

		//alert("idExpedienteTempAdmin="+idExpedienteTempAdmin+"   "+"numeroExpedienteTempAdmin="+numeroExpedienteTempAdmin);
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?id='+id+'&numeroExpediente='+numeroExpedienteTempAdmin,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catActuaciones').each(function(){
					$('#cbxTipoDeAtencion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});            
			}
		});
	}

	/*
	*Funcion que selecciona la actuacion y manda a abrir el editor 
	*coorespondiente a la actuacion.
	*/
	function seleccionaActuacion(){

		var selected = $("#cbxTipoDeAtencion option:selected");
		var pintaCheckBox=0;

		if(selected.val() != "nop"){

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

			//alert("actividad="+actividad);
			//alert("formaID="+formaID);
			//alert("titulo="+titulo);
			//alert("usaeditor="+usaeditor);
			//alert("estatusId="+estatusId);

			//registrarActividadExpediente(actividad,estatusId,0);
			if(usaeditor== "true"){				
				if(actividad=='<%=Actividades.SOLICITAR_ATENCION_PSICOLOGICA.getValorId() %>')
				{
					pintaCheckBox='<%=TiposAtencion.ATENCION_PSICOLOGICA.getValorId()%>';
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					$.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'&pintaTiposAtencion='+pintaCheckBox+'" width="1140" height="400" />');
				}
				else if(actividad=='<%=Actividades.SOLICITAR_ANTENCION_MEDICA.getValorId() %>')
				{
					pintaCheckBox='<%=TiposAtencion.ATENCION_MEDICA.getValorId()%>';
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					$.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'&pintaTiposAtencion='+pintaCheckBox+'" width="1140" height="400" />');	
				}
				else if(actividad=='<%= Actividades.SOLICITAR_ATENCION_DE_TRABAJO_SOCIAL.getValorId() %>')
				{
					pintaCheckBox='<%=TiposAtencion.ATENCION_DE_TRABAJO_SOCIAL.getValorId()%>';
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					$.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'&pintaTiposAtencion='+pintaCheckBox+'" width="1140" height="400" />');
				}
				
				else if(actividad=='<%= Actividades.GENERAR_SOLICITUD_DE_DICTAMEN_MEDICO_DE_LESIONES.getValorId()%>')
				{
					
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					$.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'" width="1140" height="400" />');
				}
				else if(actividad=='<%= Actividades.DIRIGIR_A_INSTANCIA_EXTERNA.getValorId()%>')
				{
					
					$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
					$.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpedienteTempAdmin+'" width="1140" height="400" />');
				}
				
			}else{
				
				if(actividad=='<%= Actividades.SOLICITAR_SERVICIO_PERICIAL.getValorId() %>'){
						/*
						*Variable para definir el área de donde proviene la solicitud.
						*Para Procuraduria el valor es 1
						*Para Defensoria el valor es 2
						*codigo para cambiar el estatus del expediente
						*/
					//registrarActividadExpediente(actividad,estatusId,0);
					var area = 1;
					var subArea = '<%=Areas.ATENCION_TEMPRANA_PG_NO_PENAL.parseLong()%>';		
			        $.newWindow({id:"iframewindowSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Solicitar servicio pericial", type:"iframe"});
			        $.updateWindowContent("iframewindowSolicitud",'<iframe src="<%= request.getContextPath() %>/solicitarServicioPericial.do?numeroExpediente='+numeroExpedienteTempAdmin+'&numeroExpedienteId='+idExpedienteTempAdmin+'&area='+area+'&subArea='+subArea+'"    width="1140" height="550" />');
		     	}
			}
		}		
	}

	//*****************************************Termina Funcionalidad para le ceja de solicitar atecion********************************************/
	
	/*
	*Funcion que consulta los tipos de atención seleccionadas y dibuja
	*los tipos de atencion
	*/	
	function consultaTiposAtencionSeleccionados(){

		var numeroOcurr = 0;
		
		for(i=0;i<idsCheckbox.length;i++){
			numeroOcurr = consultaNumeroDeOcurrencias(idsCheckbox[i]);
			if(numeroOcurr > 0){
				$('#check_'+idsCheckbox[i]).attr('checked', true);
			}
		}		
	}

	/*
	*Consulta el numero de registros de actividades, que corresponden con el tipo de actuacion seleccionada
	*/
	function consultaNumeroDeOcurrencias(tipoDeAtencion){

		var numeroOcurrencias = 0;
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultaTiposAtencionSeleccionadas.do?numeroExpediente='+numeroExpedienteTempAdmin+'&expedienteId='+idExpedienteTempAdmin+'&tipoAtencion='+tipoDeAtencion+'',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				numeroOcurrencias=$(xml).find('body').find('long').text();
			}
		});
		return numeroOcurrencias; 
	}
	
    </script>
</head>
<body>
<table width="100%" border="0">
	<tr>
		<td>
			<fieldset>
				<legend align= "left">Tipos de atención solicitados:</legend>
				<table id="tblTiposSol" border="0">
					<tr>
					</tr>
				</table>
			</fieldset>
		</td>
		<td align="right">
			<input type="button" value="Modificar"
			id="btnModificarHechos" style="display: none;" class="btn_modificar"/> <input
			type="button" value="Generar Acta" id="botonGenerarActa"
			onclick="generaOficioActa();" class="btn_modificar"/>&nbsp;&nbsp;&nbsp; <input
			type="button" class="btn_modificar" value="Guardar"
			id="btnGuardarHechos" />&nbsp;&nbsp;&nbsp;<input
			type="button" class="btn_modificar" value="Modificar"
			id="btnModificar" />
		</td>
	</tr>
</table>
<div id="tabsprincipalconsulta">
<ul>
	<li><a href="#tabsconsultaprincipal-1">Datos del compareciente</a>
	</li>
	<li><a href="#tabsconsultaprincipal-2">Motivo de la solicitud
	de atenci&oacute;n</a></li>
	<li class="tabTabsDocs"><a href="#tabsconsultaprincipal-3"
		onclick="documentos()">Documentos</a></li>
	<li><a href="#tabsconsultaprincipal-4">Solicitar Atención</a></li>
</ul>
<div id="tabsconsultaprincipal-1"><!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
	<div id="iProbResponsablePane">
		<dl>
			<dt id="cejaDatosGenerales">Datos Generales</dt>
			<dd><jsp:include page="datosGeneralesView.jsp"  /></dd>
			<dt id="cejaDomicilio">Domicilio</dt>
			<dd><jsp:include page="ingresarDomicilioView.jsp" /></dd>
			<dt id="cejaMediosContacto">Medios de Contacto</dt>
			<dd><jsp:include page="ingresarMediosContactoView.jsp" /></dd>
			<dt>Documentos de identificación</dt>
			<dd><jsp:include page="ingresarDocumentoIdentificacionView.jsp"/></dd>					               
		</dl>
	</div>
</div>

<div id="tabsconsultaprincipal-2">
	<div style="width: 1042px; height: 490px;" class="back_hechos">
		<table  border="0">
  			<tr>
    			<td colspan="2" height="45">&nbsp;</td>
  			</tr>
  			<tr>
    			<td width="5%">&nbsp;</td>
    			<td width="95%"><textarea
				class="jquery_ckeditor" cols="150" id="editor1" name="editor1"
				rows="10"></textarea></td>
  			</tr>
		</table>
	</div>
</div>

<div id="tabsconsultaprincipal-3" class="tabTabsDocs">

	<table  border="0">
  		<tr>
    		<td colspan="2" height="45">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="5%">&nbsp;</td>
    		<td width="95%">
    			<table id="gridDetalleFrmPrincipal" width="100%"></table>
				<div id="pager1"></div>
			</td>
  		</tr>
	</table>

	<form name="frmDoc"
	action="<%=request.getContextPath()%>/ConsultarContenidoArchivoDigital.do"
	method="post"><input type="hidden" name="documentoId" /></form>
	
	<form name="frmDoc2"
	action="<%=request.getContextPath()%>/GenerarDocumentoDirecto.do"
	method="post"><input type="hidden" name="documentoId" /> <input
	type="hidden" name="numeroUnicoExpediente" /></form>
	
</div>

<div id="tabsconsultaprincipal-4">
	<div style="width: 1042px; height: 490px;" class="back_hechos">
		<table  border="0" width="100%">
			<tr>
  				<td width="20%" height="50px" ></td>
    			<td width="40%"></td>
    			<td width="40%"></td>
  			</tr>
  			<tr>
    			<td align="right"><span>Solicitar tipo de atención:</span></td>
    			<td>
					<select id="cbxTipoDeAtencion" style="width:250px">
						<option value="nop">-Seleccione-</option>
					</select>
				</td>
    			<td >&nbsp;</td>
  			</tr>
  			<tr>
  				<td>
  				</td>
    			<td></td>
    			<td></td>
  			</tr>
		</table>
	</div>
</div>

</div>

<form name="formaDocDirecto"
	action="<%=request.getContextPath()%>/GenerarDocumentoDirecto.do"
	method="post"><input type="hidden" name="formaId" value="2"%>
<input type="hidden" name="numeroUnicoExpediente"%></form>

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
		
		height:'250px',
		width:'900px'
	};			
	$('.jquery_ckeditor').ckeditor(config);

</script>
</html>