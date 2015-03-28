<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="ingProbaleResponsableTitulo"/></title>

<!--	Hoja de estilo para los gadgets-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<!--    Hoja de estilo para easyaccordion-->
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<!--    Estilo para el acordeon de ingresar probable responsable-->

<style type="text/css">
DD P {
	LINE-HEIGHT: 120%
}

#iProbResponsablePane {
	PADDING-BOTTOM: 0px;
	PADDING-LEFT: 6px;
	WIDTH: 1000px;
	PADDING-RIGHT: 0px;
	HEIGHT: 362px;
	PADDING-TOP: 10px;
	background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
	background-repeat: no-repeat;
	border: 0px solid #000;
}

#iProbResponsablePane DL {
	WIDTH: 1000px;
	HEIGHT: 355px
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

<!--Hoka de estilo para el texto dentro de los acordeones-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<!--Hoja de estilo para los popups-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--Scripts necesarios para el funcionamiento de la JSP-->

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<!--Scrip para el idioma del calendario-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" />
	
<script type="text/javascript">

        var idDetencion=0;
        var idConDefensor=0;
	  	var idWindowIngresarNarrativa = 1;
	  	var idWindowIngresarDefensor=1;

	  	var verAlias=0;
		
		//CAMBIO-ACT var idWindowIngContOrganizacion = 1;
		
		var idWindowIngTutor = 1;
		
		var varNombre;		
		
		var banderaDenuncia=0;
		
		var banderaDenuncianteQuerellante=0;

		var banderaNarrativa=0;

		var idindi=0;
		
		var numeroExpediente="";
		
		var idOrganizacion=0;
		var idExpedienteop;
		var elemntoNuevo="no";
		
		var isUavd = '<%= request.getParameter("isUavd")%>';
		
		var idElemento = '<%=request.getParameter("idProbableResponsable")%>';
		// muestra el campo de detenido si el valor es 1
		var muestraDetenido = '<%=request.getParameter("detenido")%>';
		
		if (muestraDetenido == null || muestraDetenido == "null"){
			muestraDetenido = '<%=request.getAttribute("detenido")%>';
			}
		
		var muestraDetenidoModificar=0;

		var isDetenidoExist = '<%=request.getParameter("isDetenidoExist")%>' == '' ? false : <%=request.getParameter("isDetenidoExist")%>;
		var deshabilitarCampos = window.parent.deshabilitarCamposPM;
		var modificaGrid=true;
		jQuery().ready(function () {		

			ocultaElementosPJENS();
				$("#cbxProbResponsableTipoResp").change(onSelectChangeTipoPersonaMoral);
				
				
				if (idElemento != null && idElemento != '0' && <%=request.getParameter("idProbableResponsable")%> != null){
					//esta linea se comento porque al entrar cuando aun no hay foto oculta la imagen
					$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDelElemento.do?elementoID=<%=request.getParameter("idProbableResponsable")%>');
					//$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/foto.png");
				}else{
					$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/foto.png");
				}
				
			numeroExpediente='<%=request.getParameter("numeroExpediente")%>';
			calidadInv='<%=request.getParameter("calidadInv")%>';
			idExpedienteop='<%=request.getParameter("idExpedienteop")%>';
			elemntoNuevo='<%=request.getParameter("elemento")%>';
			var idDefensorA=parent.idDefensor;
			var nombreDefensor=parent.nombreDefensor;
			//agregamos el listener del evento onchange del combo de tipo de persona
			//$("#cbxProbResponsableTipoResp").change(onSelectChangeTipoPersonaMoral);
			//Se muestra o se oculta la sección de datos para ingresa organzacion 
			$('#iIngresarOrgWorkSheet').hide();
			$('#iVictimaBtnModificarDatos').hide();	
			var num=parent.num;
			if(num!=null && num!="0"){
				$("#anularInvolucrado").hide();
				$("#anularInv").hide();
			}
			//creamos las tabs
			$("#tabs").tabs();
			$("#tabs2").tabs();
			$("#idFechaDate").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%=request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#idFechaDateLapso").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%=request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#idFechaDateLapso2").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%=request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			//$("#ingresarTiempoLapsoPResponsable").click(cambiaLapso);
			$("#ingresarTiempoLapsoPResponsable").click(enviaAvisoDetencion);
			 $('#ingresarTiempoLapsoPResponsable').attr("disabled","disabled");
			$("#ingresarTiempoEspecificamentePResponsable").click(cambiaEspecifico);
			$("#ingresarTiempoOtroPResponsable").click(creaNuevaNarrativa);
			$("#ingresarTiempoEspecificamentePResponsable").click(deshabilitaBotonDosTres);
			//$("#ingresarTiempoLapsoPResponsable").click(deshabilitaBotonUnoTres);
			$("#ingresarTiempoOtroPResponsable").click(deshabilitaBotonUnoDos);
			$("#btnPResponsableDesconocido").click(deshabilitaDatosGeneralesDomicilio);										
			$("#iVictimaBtnModificarDatos").click(avilitaDatos);										
			//$("#btnPResponsableEsVivo").click(deshabilitaDatosGeneralesDomicilio);										
		  
			$("#chkPResponsableDetenido").click(habilitaDetenido);
			$("#chkDefensor").click(muestraDefensor);				
			//Se crea el acordeon para menor de edad
			$("#iProbResponsableAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
			  modal: true, 
			  title: 'Menor de Edad', 
			  dialogClass: 'alert', 
			  width: 500 ,
			  maxWidth: 600,
			  buttons: {"Aceptar":function(){
				  $(this).dialog("close");
				}
			  }
			
		});

			$('#iProbResponsablePane').easyAccordion({ 
		  autoStart: false, 
		  slideInterval: 3000
		});
		
		//reviso si isUavd existe, ocultaremos algunos campos en la vista
		
		if(isUavd>=0)
		
		{
			$("#tableUAVDOpsHide,#btnBuscarReincidencia,#btnConsultarExpedientes,#uploadArchivo").hide();					
		}
			
		$("#chkPResponsableMenor").click(creaNuevoTutor);
		//cargaTipoOrganizacion();
		//CAMBIO-ACT $("#iIngOrgBtnIngresarContacto").click(creaNuevoContactoOrganizacion);
		cargaOrganizacion();
		//Simulamos el contexto para el denunciante
		banderaDenunciaQuerella=1;//1 es denuncia,0 es querella
		// 1 es denunciante o querellante,depende de la bandera anterior,0 es probable responsable
		banderaDenuncianteQuerellante=0;
		//$("#iIngOrgBtnGuardar").click(btnGuardarOrganizacionCU);
		//Codigo para obtener los datos de la pantalla
		$("#iVictimaBtnGuardar").click(guardarProbResponsable);
		//Codigo para obtener los datos de la pantalla
		$("#iIngOrgBtnGuardar").click(guardarProbResponsable);
		ocultaDomicilioNotificaciones();
		//codigo para anular el probable responsable
		$('#anularInvolucrado').hide();
		$("#anularInvolucrado").click(eliminarProbableResponsable);

	   /*
		*Funcion que se utiliza para llamar al CU Consultar probable responsable
		*si el id del prob responsable ,es null, no se ejecuta la consulta
		*/			
		var id=idElemento;
		if(id==null){
			id='<%=request.getAttribute("idIndividuoProp")%>';
			if(id!=null){
				var estaDetenido =$('#chkPResponsableDetenido').is(':checked');
				if(estaDetenido){	
					// $('#ingresarTiempoLapsoPResponsable').attr("disabled","");
					 consulta(id);
					 //$('#chkDefensor').attr("disabled","");
					 
				}
			}
		}
		 $('#chkDefensor').attr("disabled","disabled");
		if(id!=null){
			muestraDatosProbResponsable(id);
		}
		cargaTamanoBoca();
		cargaTipoCara();
		cargaFormaMenton();
		cargaTipoMenton();
		cargaTez();
		cargaInclinacionMenton();

		cargaColorCabello();
		cargaFormaCabello();
		cargaCalvieTipo();
		cargaCabelloImplantacion();
		cargaCantidadCabello();
		cargaOreja();
		cargaTamanoOreja();
		cargaLobuloParticularidad();
		cargaLobuloDimension();
		cargaLobuloAdherencia();
		cargaHelixAnterior();
		cargaHelixPosterior();
		cargaHelixContorno();
		cargaHelixAdherencia();
		cargaFormaOreja();

//		$("#cmpOreja,#cmpTamanoOreja,#cmpLobuloParticularidad,#cmpLobuloDimension,#cmpLobuloAdherencia,#cmpHelixAnterior,#cmpHelixPosterior,#cmpHelixContorno,#cmpHelixAdherencia,#cmpFormaOreja").multiselect({ 
//			   multiple: false, 
//			   header: "Seleccione", 
//			   position: { 
//				      my: 'center', 
//				      at: 'center' 
//				   } ,
//			   selectedList: 1 
//			});
		
		cargaColorOjos();
		cargaTamanoOjos();
		cargaFormaOjos();
                // Jacob
                cargaAlturaNasoLabial();
                cargaComisuras();
                cargaEspesorLabioInferior();
                cargaEspesorLabioSuperior();
                cargaProminencia();
                //ancho_nariz // catalogo = ConsultarCatalogoInclinacionFrente.do
                cargaCatalogoPonEn("AnchoNariz", "ancho_nariz");
                cargaCatalogoPonEn("AlturaNariz", "altura_nariz");
                cargaCatalogoPonEn("BaseNariz", "base_nariz");
                cargaCatalogoPonEn("RaizNariz", "raiz_nariz");
                cargaCatalogoPonEn("FrenteAltura", "frente_altura");
                //  
                cargaCatalogoPonEn("FrenteAncho", "frente_ancho");
                cargaCatalogoPonEn("InclinacionFrente", "inclinacion_frente");
                // fin jacob

//		$("#cmpFormaOjos,#cmpTamanoOjos,#cmpColorOjos").multiselect({ 
//			   multiple: false, 
//			   header: "Seleccione", 
//			   position: { 
//				      my: 'center', 
//				      at: 'center' 
//				   } ,
//			   selectedList: 1 
//			});

		cargaImplantacionCeja();
		cargaFormaCejas();
		cargaTamanoCeja();
		cargaTipoSangre();
		cargaComplexion();
		cargaDireccionCeja();
		cargaHelixOriginal();
		cargaOrejaLobContorno();
		
//		$("#cmpImplantacionCejas,#cmpFormaCejas,#cmpTamanoCejas,#cmpTipoSangre,#cmpFactorRH").multiselect({ 
//			   multiple: false, 
//			   header: "Seleccione", 
//			   position: { 
//				      my: 'center', 
//				      at: 'center' 
//				   } ,
//			   selectedList: 1 
//			});
		var idProbableResponsable='<%=request.getParameter("idProbableResponsable")%>';
		if(idProbableResponsable != "null"){
			consulta(idProbableResponsable);
			//Aki colocar la consulta del probable responsable
			// $('#chkDefensor').attr("disabled","");
		}   
		if(idDefensorA!=null){
			//cargaDefensor(nombreDefensor,idDefensorA);
		}

		if(isDetenidoExist){
			$('#chkPResponsableDetenido').attr('checked',true);
			habilitaDetenido();
		}
		

		//Instruccion pensada solo para el caso de policia ministerial
		if(deshabilitarCampos == true){
			$(":enabled").attr('disabled','disabled');
			$("#btnBuscarReincidencia").removeAttr('disabled');
			$("#btnConsultarExpedientes").removeAttr('disabled');	
		}

		//Si viene de CoordinadorAMP se muestra el campo de detenido
		//o si es de agenteMP
		  if(parseInt(muestraDetenido) == 1){
				$("#etiquetaDetenido").show();
				$("#chkPResponsableDetenido").show();

				muestraDetenidoModificar = 1;
				
			  }  
  });//FIN funcion onready


  		function ocultaElementosPJENS(){

  			
  			$("#cbxProbResponsableTipoResp").attr('disabled','disabled');
  			$("#btnBuscarReincidencia").hide();
  			$("#btnConsultarExpedientes").hide();
  			$("#chkDefensor").hide();
  			$("#tdConDefensor").hide();
  			
  	  	}


		function consulta(id){
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%=request.getContextPath()%>/consultarInvolucrado.do',
		    	  data: 'idInvolucrado='+id,
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    		  datosXML=xml;
		    		  muestraDatosCondicion(xml);
		    		  if($(xml).find('desconocido').text() == "true"){
		  				$('#btnPResponsableDesconocido').attr('checked','checked');
		  				deshabilitaDatosGeneralesDomicilio();
		  				}
		    		  pintaDatosGenerales(xml);
		    		  pintaDatosDomicilio(xml);
		    		  pintaDatosDomicilioNotif(xml);
						
						if($(xml).find('tipoPersona').text() == "0"){
							pintaDatosDomicilioPersonaMoral(xml);
						}

		    		  pintaDatosMediaFiliacion(xml);
		    		  pintaDatosTipoDocIdentificacion(xml);
		    		  pintaDatosTiempoLapso(xml);

		    		  if($(xml).find('involucradoDTO').find('esDetenido').text()){
			    		 // falta quitar la imagen del calendario para no poder utilizarlo
		    			  bloqueaCamposTiempoLapso(0);
		    			  $('#chkPResponsableDetenido').attr('checked','checked');
		    			  $('#chkPResponsableDetenido').attr('disabled','disabled');
			    		  habilitaDetenido();
			    		  idDetencion = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('detencionId').first().text();
			    		  }
		    		  
	    				//var contenido = $(xml).find('contenido').text();
		    			//if(contenido != null && contenido == ""){
	    					//$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/foto.png");
				  }
		    });
			idindi=id;
			desavilitarDatosGenerales();
			desavilitarDatosDomicilio();
			deshabilitaDatosMediaFiliacion();
			desavilitarDatosIdentificacion();
			bloqueaCamposTablaUAVDOpsHide(0);
			mediosContactoCorreoActualiza();
			mediosContactoTelefonoActualiza();
			$('#iVictimaBtnModificarDatos').show();
			$('#iVictimaBtnGuardar').hide();
			$.ajax({
		    	  type: 'POST',
		    	  url:  '<%=request.getContextPath()%>/consultarRelacionDefensorInvolucrado.do',
		    	  data: 'idProbableResponsable='+idindi,
		    	  dataType: 'xml',
		    	  async: false,
		    	  success: function(xml){
		    		  var nombre=$(xml).find('nombresDemograficoDTO').find('nombre').text();
		    		  nombre=nombre+" "+$(xml).find('nombresDemograficoDTO').find('apellidoPaterno').text();
		    		  nombre=nombre+" "+ $(xml).find('nombresDemograficoDTO').find('apellidoMaterno').text();
					idConDefensor=$(xml).find('involucradoIdDefensor').text();
					if($(xml).find('involucradoIdDefensor').text()!=""){
		    		 	cargaDefensor(nombre,$(xml).find('involucradoIdDefensor').text());
		    		 	$('#chkDefensor').attr("checked","checked");
		    		 	$('#chkDefensor').attr("disabled","disabled");		    		 	
					}
					else{
						$('#chkDefensor').attr("disabled","");						
					}
				  }
		    });
		}
		
  	  /*
  	  	Funcion para habilitar y deshabilirar las opciones de la tabla UAVD
  	  */
	  function bloqueaCamposTablaUAVDOpsHide(bandera)
	  {
		  if(parseInt(bandera)==0)
	  	{
			   	 $("#btnPResponsableEsVivo").attr('disabled','disabled');
			   	 $("#btnPResponsableEsMuerto").attr('disabled','disabled');
			   	 $("#btnPResponsableDesconocido").attr('disabled','disabled');
			   	 $("#chkPResponsableDetenido").attr('disabled','disabled');
			   	 //$("#chkDefensor").attr('disabled','disabled');
	  	}
	  	else
	  	{
	  		 	 $("#btnPResponsableEsVivo").attr('disabled','');
		       	 $("#btnPResponsableEsMuerto").attr('disabled','');
		       	 $("#btnPResponsableDesconocido").attr('disabled','');
		       	 $("#chkPResponsableDetenido").attr('disabled','');
		       	 $("#chkDefensor").attr('disabled','');
	  	}
	  }

/*
 *COMIENZAN FUNCIONES PARA EL CU CONSULTAR PROBABLE RESPONSABLE
 */  
 function avilitaDatos(){
		avilitarDatosGenerales();
		avilitarDatosDomicilio();
		habilitaDatosMediaFiliacion();
		avilitarDatosIdentificacion();
		bloqueaCamposTiempoLapso(1);
		bloqueaCamposTablaUAVDOpsHide(1);
		//$('#anularInvolucrado').show();
		$('#iVictimaBtnGuardar').show();
		$('#iVictimaBtnModificarDatos').hide();
		modificaGrid=true;
	}
 
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

			if($(xml).find('esVivo').text() == "true"){
				 $("#btnPResponsableEsVivo").attr('checked','checked');
			}
			else if($(xml).find('esVivo').text() == "false"){
				$("#btnPResponsableEsMuerto").attr('checked','checked');
			}
			if($(xml).find('desconocido').text() == "true"){	
				$("#btnPResponsableDesconocido").attr('checked','checked');
				deshabilitaDatosGeneralesDomicilio();
				//$("#btnPResponsableDesconocido").click();
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
	   *Funcion que muestra los nombres del probable responsable, cuando es persona fisica
	   */	
		//function muestraNombres(xml){
			
		//	$(xml).find('nombresDemograficoDTO').find('nombreDemograficoDTO').each(function(){
		//		$('#nombProResponsable').val($(this).find('nombre').text());
		//		$('#apPatProbResponsable').val($(this).find('apellidoPaterno').text());
		//		$('#apMatProbResponsable').val($(this).find('apellidoMaterno').text());
		//	});
		//}

	  /*
	   *Funcion que verifica si el prob responsable es una persona física o moral
	   *y oculta o muestra los datos dependiendo de ello
	   */	
		function muestraDatosPersona(xml){

			var idTipoPersona = $(xml).find('tipoPersona').text();
			//var idTipoPersona = 0;
			//desconocido
			if($(xml).find('desconocido').text() == "true"){
				$('#btnPResponsableDesconocido').attr('checked','checked');
				deshabilitaDatosGeneralesDomicilio();
			}
 						
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
			
		/*CAMBIO-ACT
		*Funcion para pintar los datos provenientes de la consulta de una organizacion
		*/
		//function seteaDatosPersonaMoralConsOrg(xml)
		//{
		//	//seteo los valores en los campos
		//	$("#txtIngOrgNombreOrg").val($(xml).find('organizacionDTO').find('nombreOrganizacion').text());
		//	$("#txtIngOrgNombreCortoOrg").val($(xml).find('organizacionDTO').find('nombreCorto').text());
		//	$("#txtIngOrgDirInternet").val($(xml).find('organizacionDTO').find('direccionInternet').text());
		//	$('#cbxIngOrgTiposOrganizacion').find("option[value='"+$(xml).find('organizacionDTO').find('valorByTipoOrganizacionVal').find('idCampo').text()+"']").attr("selected","selected");
		//	//deshabilitamos los campos
		//	//CAMBIO-ACT habilitaDeshabilitaCamposIngOrganizacion(0);
		//}

		/*CAMBIO-ACT
		*Metodo para habilitar o deshabilitar los campos del maestro de Organizacion
		*bandera = 0 para deshabilitar, bandera=1 para habilitar los campos
		*/
		//function habilitaDeshabilitaCamposIngOrganizacion(bandera)
		//{
		//	if(parseInt(bandera)==0)
		//	{
		//		//bloqueo los campos de solo lectura
		//		$("#txtIngOrgNombreOrg").attr("disabled","disabled");
		//		$("#txtIngOrgNombreCortoOrg").attr("disabled","disabled");
		//		$("#txtIngOrgDirInternet").attr("disabled","disabled");
		//		$("#cbxIngOrgTiposOrganizacion").attr("disabled","disabled");
		//	}
		//	else
		//	{
		//		//habilito los campos 
		//		$("#txtIngOrgNombreOrg").attr("disabled","");
		//		$("#txtIngOrgNombreCortoOrg").attr("disabled","");
		//		$("#txtIngOrgDirInternet").attr("disabled","");
		//		$("#cbxIngOrgTiposOrganizacion").attr("disabled","");
		//	}
		//}
/*
 *TERMINAN FUNCIONES PARA EL CU CONSULTAR PROBABLE RESPONSABLE
 */

		function habilitaDetenido() {
		  if ($("#chkPResponsableDetenido").is(':checked')) {
		  
			$("#datosDetenido").toggle();		
		  }
		  else{
			
			$("#datosDetenido").toggle();
			$("#ingresarTiempoEspecificamentePResponsable").attr('disabled', 'disabled');															
			$("#ingresarTiempoLapsoPResponsable").attr('disabled', '');
			$("#ingresarTiempoOtroPResponsable").attr('disabled', 'disabled');		
			limpiaFechas();
		
		  } 
		  $('#ingresarTiempoEspecificamentePResponsable').hide();
		  $('#ingresarTiempoOtroPResponsable').hide(); 
		  cambiaLapso(); 
		}
		
		function anularDefensor(){
			var datosPestania;
			var params = '';
			params += 'idIndividuo='+idConDefensor;
			params += '&idPropParaDefensor='+idindi;
			params += '&calidadDelIndividuo=9';
			datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
			params += datosPestania;
			datosPestania = obtenerParametrosDatosGenerales();//Frame de datos generales
			params += datosPestania;
			datosPestania = obtenerParametrosDatosNacimiento();
			params += datosPestania;	
			datosPestania = obtenerParametrosDomicilio();
			params += datosPestania;
			datosPestania = obtenerMedios();
			params += datosPestania;
			datosPestania=recuperaDatosTipoDocIdentificacion("Denunciante");
			params += '&';
			params += datosPestania;
			params += '&anular='+true;
						
			$.ajax({								
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/guardarIndividuo.do?numeroExpediente='+numeroExpediente +'',
		    	  data: params,				
		    	  dataType: 'xml',
		    	  success: function(xml){
		    		alertDinamico('Defensor Anulado');		    		  
		    		var row=$('#'+idConDefensor);					
					$(row).remove();
					$("#etiquetaDefensor").remove();		    		  
		    	  }
		    });
		}		

		function muestraDefensor() {
			if ($("#chkDefensor").is(':checked')) {
				idWindowIngresarDefensor++;
				$.newWindow({id:"iframewindowIngresarDefensor" + idWindowIngresarDefensor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Defensor", type:"iframe"});
			    $.updateWindowContent("iframewindowIngresarDefensor" + idWindowIngresarDefensor,'<iframe src="<%=request.getContextPath()%>/ingresarDefensorView.do?numeroExpediente='+numeroExpediente +'&idPropPapa='+idindi+'" width="1040" height="570" />');		
			}
			else{
				if(idConDefensor!=null && idConDefensor!="null" && idConDefensor!=0){
	                if (confirm("¿Desea anular el defensor asociado?")){                      
	                	anularDefensor();
	                }
					else{
						$('#chkDefensor').attr("checked","checked");
					}
				}
			}
		}
		
			
		function limpiaFechas(){

		  $("#idFechaDate").val("");
		  $("#idFechaDateLapso").val("");
		  $("#idFechaDateLapso2").val("");
		  $("#textNarrativa").val("");							
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
			idWindowIngresarNarrativa++;
			if (idWindowIngresarNarrativa>=3){
				alertDinamico("Ya se encuentra esta funcionalidad en uso");
			}
			else{
				$.newWindow({id:"iframeWindowNarrativa" + idWindowIngresarNarrativa, statusBar: true, posx:410,posy:240,width:370,height:470,title:"Ingresar Narrativa", type:"iframe"});
				$.updateWindowContent("iframeWindowNarrativa" + idWindowIngresarNarrativa,'<iframe src="<%=request.getContextPath()%>/IngresarNarrativaPro.do" width="1050" height="600" />');		
			}
		}

			
		function cambiaEspecifico(){
		  if(!$("#divEspecifico").is(':visible')){
			$("#divLapso").css("display", "none");
			$("#divEspecifico").css("display", "block");  
			$("#divOtro").css("display", "none"); 
		  }
		}
		
			
		function cambiaLapso(){
		  if(!$("#divLapso").is(':visible')){
			$("#divLapso").css("display", "block");
			$("#divEspecifico").css("display", "none");
			$("#divOtro").css("display", "none"); 
		  }
		}
		
				
		function cambiaOtro(){
		  if(!$("#divLapso").is(':visible')||!("#divEspecifico").is(':visible')){
			$("#divLapso").css("display", "none");
			$("#divEspecifico").css("display", "none"); 
			$("#divOtro").css("display", "block");
		  }
		}

			
		/*
		*Funcion para ocultar los datos generales y el domicilio
		*en caso de que el probable responsable sea desconocido
		*/
		function deshabilitaDatosGeneralesDomicilio(){
			
		  if ($("#btnPResponsableDesconocido").is(':checked')) {
			habilitaDeshabilitaTabAcordeon("cejaDatosGenerales","cejaMediaFiliacion",0);
			habilitaDeshabilitaTabAcordeon("cejaDomicilio","cejaMediaFiliacion",0);
			habilitaDeshabilitaTabAcordeon("cejaDocumentosIdentificacion","cejaMediaFiliacion",0);
			habilitaDeshabilitaTabAcordeon("cejaMediosContacto","cejaMediaFiliacion",0);					
		  }
		  else{
			habilitaDeshabilitaTabAcordeon("cejaDatosGenerales","cejaDatosGenerales",1);
			habilitaDeshabilitaTabAcordeon("cejaDomicilio","cejaDatosGenerales",1);
			habilitaDeshabilitaTabAcordeon("cejaDocumentosIdentificacion","cejaDatosGenerales",1);
			habilitaDeshabilitaTabAcordeon("cejaMediosContacto","cejaDatosGenerales",1);
		  }
		}
			
				
		/*
		 *Funcion para atender el evento onchange del combo de tipo de persona
		 */
		function onSelectChangeTipoPersonaMoral() {
		
		var selected = $("#cbxProbResponsableTipoResp option:selected");
		  
		  if (parseInt(selected.val()) == 1 ){
			//Persona Fisica
			$('#iProbResponsableWorkSheet').show();
			$('#iIngresarOrgWorkSheet').hide();
			habilitaDeshabilitaTabAcordeon("cejaDatosGenerales","cejaDomicilio",1);
			habilitaDeshabilitaTabAcordeon("cejaMediaFiliacion","cejaDomicilio",1);
			habilitaDeshabilitaTabAcordeon("cejaDocumentosIdentificacion","cejaDomicilio",1);
			limpiaCejaDatosGenerales();
			liveDomicilioNotificaciones();
		  }
		  else if (parseInt(selected.val()) == 0 ){
			
			//En caso de que previamente se haya seleccionado que el
			//probable responsable esta detenido, hay que habilitar las cejas
			$('#btnPResponsableDesconocido').attr('checked', false);	
			deshabilitaDatosGeneralesDomicilio();	
			//Persona Moral
			$('#iProbResponsableWorkSheet').hide();
			$('#iIngresarOrgWorkSheet').show();
			habilitaDeshabilitaTabAcordeon("cejaDatosGenerales","cejaDomicilio",0);
			habilitaDeshabilitaTabAcordeon("cejaMediaFiliacion","cejaDomicilio",0);
			habilitaDeshabilitaTabAcordeon("cejaDocumentosIdentificacion","cejaDomicilio",0);		
			killDomicilioNotificaciones();
		  }
		}

				
		/*
		 * Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT a
		 * deshabilitar y el id del elemento dt, para activar su pestaña correspondiente
		 * y un 0 para deshabilitar o un 1 para habilitar
		 */
		function habilitaDeshabilitaTabAcordeon(idTabAcordeon,idTabAcordeonActive,bandera)
		{
		  if(parseInt(bandera)==0){	//Deshabilita el tab del acordeon
			  $("#"+idTabAcordeonActive).click();
			  $("#"+idTabAcordeon).unbind('click');
			  $("#"+idTabAcordeon).removeClass('active').addClass('inactive');
		  }
		  else{						//habilita los tabs del acordeon
			  $("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
			  $("#"+idTabAcordeon).click(function(){		
				  jQuery($("#"+idTabAcordeon)).activateSlide();
			  });
		  }
		}
			
			
		/*
		 *Imprime los datos que vienen de la funcion espejoDatos de datos generales, 
		 *en la pantalla ingresar probable responsable
		 */
		function imprimeDatosPadre(nombre, apPat, apMat){
		  document.getElementById('nombProResponsable').innerHTML=nombre;
		  document.getElementById('apPatProbResponsable').innerHTML=apPat;
		  document.getElementById('apMatProbResponsable').innerHTML=apMat;
		}
		
		
		/*
		 *Imprime los datos que vienen de la funcion espejoDatosNarrativa de texto narrativa
		 *en la pantalla ingresar probable responsable
		 */
		function imprimeDatosPadreNarrativa(textoNarrativa){
		  $("#textNarrativa").val(textoNarrativa);
		}
			
			
		//Oculta botones Lapso y Otro
		function deshabilitaBotonDosTres(){
		  $("#ingresarTiempoLapsoPResponsable").attr('disabled', 'disabled');
		  $("#ingresarTiempoOtroPResponsable").attr('disabled', 'disabled');
		}


		//Oculta botones Especificamente y Otro
		function deshabilitaBotonUnoTres(){		
		  $("#ingresarTiempoEspecificamentePResponsable").attr('disabled', 'disabled');
		  $("#ingresarTiempoOtroPResponsable").attr('disabled', 'disabled');
		}

		//Oculta botones Especificamente y Lapso
		function deshabilitaBotonUnoDos(){
			$("#ingresarTiempoEspecificamentePResponsable").attr('disabled', 'disabled');
			$("#ingresarTiempoLapsoPResponsable").attr('disabled', 'disabled');
		}


		/*
		*Limpia los datos de la ceja datos generales 
		*/
		function limpiaCejaDatosGenerales(){
		  //El padre invoca una función del hijo
		  cleanDatosGenerales();  
		}			
			
			
       /*CAMBIO-ACT
		*Funcion que dispara el Action para consultar el Tipo de Organizacion
		*/		
		//function cargaTipoOrganizacion(){ 
		//	$.ajax({
		//		type: 'POST',
		
		//		data: '',
		//		dataType: 'xml',
		//		success: function(xml){
		//			var option;
		//			$('#cbxIngOrgTiposOrganizacion').append('<option value="-1">- Seleccione -</option>');
		//			$(xml).find('catTipoOrganizacion').each(function(){
		//				$('#cbxIngOrgTiposOrganizacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		//			});
		//		}
		//	});
		//}
			
			
	   /*CAMBIO-ACT
	  	*Funcion que crea una nueva ventana de un contacto de una organizacion
		*/	
		//function creaNuevoContactoOrganizacion() {
		//  idWindowIngContOrganizacion++;
		//  $.newWindow({id:"iframewindow" + idWindowIngContOrganizacion, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Contacto De Una Organizacion", type:"iframe"});
		//  $.updateWindowContent("iframewindow" + idWindowIngContOrganizacion,'<iframe src="<%=request.getContextPath()%>/IngresarContactoDeUnaOrganizacion.do" width="1050" height="600" />');		
		//}

		/*
	  	*Funcion que crea una nueva ventana de un nuevo tutor
		*/	
		function creaNuevoTutor() {
		  idWindowIngTutor++;
		  if ($("#chkPResponsableMenor").is(':checked')) {
			  $.newWindow({id:"iframewindow" + idWindowIngTutor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Tutor", type:"iframe"});
			  $.updateWindowContent("iframewindow" + idWindowIngTutor,'<iframe src="<%=request.getContextPath()%>/IngresarTutor.do" width="1050" height="600" />');
		  }
		}
			
		/*CAMBIO-ACT
		*Funcion para validar los datos generales de una organizacion
		*/
		//function validaDatosGeneralesOrganizacion(){
		//  var bandera=false;
		//  var idTipoOrganizacion=-1;
		//  idTipoOrganizacion=	$("#cbxIngOrgTiposOrganizacion option:selected").val();
		//  if(idTipoOrganizacion!=-1){
		//	  if(banderaDenuncianteQuerellante==0){
		//		  //es probable responsable como Denunciante
		//		  if(banderaDenunciaQuerella==1){
		//			  //es denuncia
		//			  
		//		  }
		//		  else{
		//			  //es querella
		//		  }
		//	  }
		//	  else{
		//		//revisamos la obligatoriedad de nombre de la organizacion
		//		if(txtIngOrgNombreOrg.html().length>0){
		//			bandera=true;
		//		}	
		//	  }
		// }
		//  return bandera;
		//}
		 
		   
		function btnGuardarOrganizacionCU(){
			validaDatosGeneralesOrganizacion();
		}

		function deshabilitacheckboxdefensor(){
		 	$('#chkDefensor').attr("disabled","disabled");		    
		}

		/**
		* Función que guarda los datos de la pantalla
		*/
		function guardarProbResponsable(){
			var nombreGeneralOP=$('#datosGeneralesCmpNombres').val();
			var desconocidoOp=$('#btnPResponsableDesconocido').is(':checked');
			var nombreGeneralMoralOP =$("#txtIngOrgNombreOrg").val();
			var validaRFC_CURP;
			validaRFC_CURP=camposGeneralesValidos();
			if(validaRFC_CURP==1){
			if(nombreGeneralOP!="" && desconocidoOp!="true" ){	
			
				var params = '';
				params += 'idIndividuo='+idindi;
				params += '&calidadDelIndividuo=0';
				params += '&numeroExpediente='+numeroExpediente;				
				var tipoResponsable = $("#cbxProbResponsableTipoResp option:selected").val();
				if(tipoResponsable == 1){//Tipo de responsable fisico
					params+='&esPersonaMoral=false';
					params+='&idCondicion=' + $(':radio[name=radio]:checked').val();
					params+='&esVivo=' + $('#btnPResponsableEsVivo').is(':checked');
					params+='&esDesconocido=' + $('#btnPResponsableDesconocido').is(':checked');
					params+='&esMenorDeEdad=' + $('#chkPResponsableMenor').is(':checked');
					
					var estaDetenido =$('#chkPResponsableDetenido').is(':checked');
					if(estaDetenido){
						 $('#ingresarTiempoLapsoPResponsable').attr("disabled","");
					}
					params+='&estaDetenido=' + estaDetenido;
					if(estaDetenido){//Cuando el check detenido es palomeado 
					//Datos de detencion
					var datosPestania =	recuperaDatosTiempoLapso();
					params += datosPestania;	
					params += '&idDetencion='+ idDetencion;
					}		
					//Datos generales, media filiacion, medios de contacto, documentos de identificacion
					datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
					params += datosPestania;
	
					//Datos nacimiento
					datosPestania = obtenerParametrosDatosNacimiento();
					params += datosPestania;
					
					//Domicilio
					datosPestania = obtenerParametrosDomicilio();
					params += datosPestania;
	
					//Media Filiacion
					datosPestania = obtenerParametrosMediaFiliacion();
					params += datosPestania;
	
					//Medios de contacto
					datosPestania = obtenerMedios();
					params += datosPestania;
	
					//Documento de identificacion
					datosPestania = '&';
					datosPestania += recuperaDatosTipoDocIdentificacion();
					params += datosPestania;
	
					var op=0;
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/guardarIndividuo.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){

				    		if(elemntoNuevo=="si"){
				    			//window.parent.refresca();
						    }else if(elemntoNuevo=="no"){
							   window.parent.recargaGridImputados();
						    }
						    
							if($(xml).find('IngresarIndividuoForm').find('estaDetenido').text() == 'true'){
								//window.parent.muestraMenuQuienDetuvo();
															
							}	
							idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
							idElemento = idindi;
							op=1;
							//$('#chkDefensor').attr("disabled","");
							alertDinamico('Probable responsable guardado');
							
							enviaImagenDeElemento(idElemento,idindi);
							
							
				    	  }
				    	});
				}else{//Tipo de responsable moral
					params += '&esPersonaMoral=true';
					params += '&idUsuario=0';
					params += '&calidadPersonaMoral='+calidadInv;
					params += '&'+extraeDatosPersonaMoralIngOrg();//datos generales de organizacion
					params += obtenerParametrosDomicilio();//datos del domicilio de la organizacion
					params += obtenerMedios();//datos de los medios de contacto de la organizacion					
					//llamamos al ajax que guardara la informacion de la organizacion
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/guardarOrganizacion.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('code').text())==0)
				    		  {
				    			//el guardado fue exitoso
				    			idOrganizacion=$(xml).find('organizacionId').text();//seteamos el id de la nueva organizacion
				    			//revisamos si se elimino el objeto durante la actualizacion  
								if($('#anularInvolucrado').is(':checked')=='false' || $('#anularInvolucrado').is(':checked')==false)
								{
					    			  var nombre =$(xml).find('organizacionDTO').find('nombreOrganizacion').text();
					    			  //window.parent.cargaProbableResponsable(nombre,idOrganizacion);
					    			  //habilitamos el boton para poder guardar el representante legal y el contacto organizacional
						    		  //$("#iVictimaBtnGuardar").attr("disabled","disabled");
					    			  $("#iIngOrgBtnGuardar").attr("disabled","disabled");
					    			  $("#iIngOrgBtnIngresarContacto,#btnIngOrgFormalDatosRep").attr("disabled","");
						    		  alertDinamico("La Organización se guardó exitosamente");
								}
								else
								{
									//mandamos eliminar visualmente el elemento anulado logicamente y luego cerraremos la ventana
									//window.parent.eliminaProbableResponsableDeMenuIntermedio(idOrganizacion);
									//window.parent.cerrarVentanaProbableResponsable();
								}
					    		op=1;
				    		  }
				    		  else
				    		  {
				    			  idOrganizacion=0;
				    			  alertDinamico("Ocurrió un error al guardar la organización");
				    			  op=1;
				    		  }
				    	  }
					
				    	});
				}
				
			}else if( nombreGeneralMoralOP!="" && desconocidoOp!="true" || desconocidoOp==true){
				var params = '';
				params += 'idIndividuo='+idindi;
				params += '&calidadDelIndividuo=0';
				params += '&numeroExpediente='+numeroExpediente;				
				var tipoResponsable = $("#cbxProbResponsableTipoResp option:selected").val();
				if(tipoResponsable == 1){//Tipo de responsable fisico
					params+='&esPersonaMoral=false';
					params+='&idCondicion=' + $(':radio[name=radio]:checked').val();
					params+='&esDesconocido=' + $('#btnPResponsableDesconocido').is(':checked');
					params+='&esMenorDeEdad=' + $('#chkPResponsableMenor').is(':checked');
					
					var estaDetenido =$('#chkPResponsableDetenido').is(':checked');
					if(estaDetenido){
						 $('#ingresarTiempoLapsoPResponsable').attr("disabled","");
					}
					params+='&estaDetenido=' + estaDetenido;
					if(estaDetenido){//Cuando el check detenido es palomeado 
					//Datos de detencion
					var datosPestania =	recuperaDatosTiempoLapso();
					params += datosPestania;	
					params += '&idDetencion='+ idDetencion;
					}		
					//Datos generales, media filiacion, medios de contacto, documentos de identificacion
					datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
					params += datosPestania;
	
					//Datos nacimiento
					datosPestania = obtenerParametrosDatosNacimiento();
					params += datosPestania;
					
					//Domicilio
					datosPestania = obtenerParametrosDomicilio();
					params += datosPestania;
	
					//Media Filiacion
					datosPestania = obtenerParametrosMediaFiliacion();
					params += datosPestania;
	
					//Medios de contacto
					datosPestania = obtenerMedios();
					params += datosPestania;
	
					//Documento de identificacion
					datosPestania = '&';
					datosPestania += recuperaDatosTipoDocIdentificacion();
					params += datosPestania;
	
					var op=0;
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/guardarIndividuo.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		if(elemntoNuevo=="si"){
				    			//window.parent.refresca();
						    }else{
							   //window.parent.cargaProbableResponsable($(xml).find('IngresarIndividuoForm').find('nombre').text(),$(xml).find('IngresarIndividuoForm').find('idIndividuo').text());
						    }
							if($(xml).find('IngresarIndividuoForm').find('estaDetenido').text() == 'true'){
								//window.parent.muestraMenuQuienDetuvo();
															
							}	
							idindi=$(xml).find('IngresarIndividuoForm').find('idIndividuo').text();
							idElemento = idindi;
							op=1;
							$('#chkDefensor').attr("disabled","");
							alertDinamico('Probable responsable guardado');
							enviaImagenDeElemento(idElemento,idindi);
				    	  }
				    	});
				}else{//Tipo de responsable moral
					params += '&esPersonaMoral=true';
					params += '&idUsuario=0';
					params += '&calidadPersonaMoral='+calidadInv;
					params += '&'+extraeDatosPersonaMoralIngOrg();//datos generales de organizacion
					params += obtenerParametrosDomicilio();//datos del domicilio de la organizacion
					params += obtenerMedios();//datos de los medios de contacto de la organizacion
					
					//llamamos al ajax que guardara la informacion de la organizacion
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/guardarOrganizacion.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		  if(parseInt($(xml).find('code').text())==0)
				    		  {
				    			  //el guardado fue exitoso
				    			  idOrganizacion=$(xml).find('organizacionId').text();//seteamos el id de la nueva organizacion
					    		//revisamos si se elimino el objeto durante la actualizacion  
									if($('#anularInvolucrado').is(':checked')=='false' || $('#anularInvolucrado').is(':checked')==false)
									{
						    			  var nombre =$(xml).find('organizacionDTO').find('nombreOrganizacion').text();
						    			  //window.parent.cargaProbableResponsable(nombre,idOrganizacion);
						    			  //habilitamos el boton para poder guardar el representante legal y el contacto organizacional
							    		  //$("#iVictimaBtnGuardar").attr("disabled","disabled");
						    			  $("#iIngOrgBtnGuardar").attr("disabled","disabled");
						    			  $("#iIngOrgBtnIngresarContacto,#btnIngOrgFormalDatosRep").attr("disabled","");
							    		  alertDinamico("La Organización se guardó exitosamente");
									}
									else
									{
										//mandamos eliminar visualmente el elemento anulado logicamente y luego cerraremos la ventana
										//window.parent.eliminaProbableResponsableDeMenuIntermedio(idOrganizacion);
										//window.parent.cerrarVentanaProbableResponsable();
									}
									op=1;
				    		  }
				    		  else
				    		  {
				    			  idOrganizacion=0;
				    			  alertDinamico("Ocurrió un error al guardar la organización");
				    			  op=1;
				    		  }
				    	  }
					
				    	});
				}
				
			}else{
				alertDinamico('Favor de capturar el nombre del involucrado');
			}
		}
		else if(validaRFC_CURP==0){
			alertDinamico("Favor de verificar que el CURP ingresado sea correcto");
		}
		else{
			alertDinamico("Favor de verificar que el RFC ingresado sea correcto");
		}
	}				

		
		function eliminarProbableResponsable(){					
			guardarProbableResponsableEliminar();
			//window.parent.eliminaProbableResponsableDeMenuIntermedio(idindi);
			//window.parent.cerrarVentanaProbableResponsable();					
		}
		
		function guardarProbableResponsableEliminar(){
			var params = '';
			params += 'idIndividuo='+idindi;
			$.ajax({								
			    type: 'POST',
			    url: '<%=request.getContextPath()%>/anularElemento.do',
			    data: params,				
			    dataType: 'xml',
			    success: function(xml){
			    		
				}
			});
		}
		

	//Funcionalidad agregada a peticion de lupita (esta pendiente de aceptacion) 16/05/2011
		function buscarExpediente() {
			$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
		   	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%=request.getContextPath()%>/buscarExpediente.do" width="653" height="400" />');		
		}

		
		function buscarCaso() {
			$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
	    	$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%=request.getContextPath()%>/buscarCaso.do" width="653" height="400" />');		
		}

		/*
		*Funciones para consultar los Catalogos de Media filiacion
		*/		
	    function cargaTamanoBoca(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoBoca.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTamanoBoca').each(function(){
	            			$('#cmpTamanoBoca').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
	    function cargaTipoCara(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTipoCara.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTipoCara').each(function(){
	            			$('#cmpTipoCara').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaFormaMenton(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaMenton.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catFormaMenton').each(function(){
	            			$('#cmpFormaMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaTipoMenton(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTipoMenton.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTipoMenton').each(function(){
	            			$('#cmpTipoMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaTez(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTez.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTez').each(function(){
	            			$('#cmpTez').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaInclinacionMenton(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoInclinacionMenton.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catInclinacionMenton').each(function(){
	            			$('#cmpInclinacionMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaColorCabello(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoColorCabello.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catColorCabello').each(function(){
	            			$('#cmpColorCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaFormaCabello(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaCabello.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catFormaCabello').each(function(){
	            			$('#cmpFormaCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
		
	    function cargaCalvieTipo(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCalvieTipo.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catCalvieTipo').each(function(){
	            			$('#cmpCalvieTipo').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaCabelloImplantacion(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCabelloImplantacion.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catCabelloImplantacion').each(function(){
	            			$('#cmpCabelloImplantacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaCantidadCabello(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCantidadCabello.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catCantidadCabello').each(function(){
	            			$('#cmpCantidadCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
	    
	    function cargaOreja(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoOreja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catOreja').each(function(){
	            			$('#cmpOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaTamanoOreja(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoOreja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTamanoOreja').each(function(){
	            			$('#cmpTamanoOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
	    
	    function cargaLobuloParticularidad(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloParticularidad.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catLobuloParticularidad').each(function(){
	            			$('#cmpLobuloParticularidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaLobuloDimension(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloDimension.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catLobuloDimension').each(function(){
	            			$('#cmpLobuloDimension').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaLobuloAdherencia(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloAdherencia.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catLobuloAdherencia').each(function(){
	            			$('#cmpLobuloAdherencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaHelixAnterior(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixAnterior.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catHelixAnterior').each(function(){
	            			$('#cmpHelixAnterior').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaHelixPosterior(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixPosterior.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catHelixPosterior').each(function(){
	            			$('#cmpHelixPosterior').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaHelixContorno(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixContorno.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catHelixContorno').each(function(){
	            			$('#cmpHelixContorno').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaHelixAdherencia(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixAdherencia.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catHelixAdherencia').each(function(){
	            			$('#cmpHelixAdherencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

	    function cargaFormaOreja(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaOreja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catFormaOreja').each(function(){
	            			$('#cmpFormaOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
	    
	    function cargaColorOjos(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoColorOjos.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catColorOjos').each(function(){
	            			$('#cmpColorOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
	    
	    function cargaTamanoOjos(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoOjos.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTamanoOjos').each(function(){
	            			$('#cmpTamanoOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }
	    
	    function cargaFormaOjos(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaOjos.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catFormaOjos').each(function(){
	            			$('#cmpFormaOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaAlturaNasoLabial(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoAlturaNasoLabial.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
//	    			var option;
	        			$(xml).find('catAlturaNasoLabial').each(function(){
	            			$('#altura_nasal').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaComisuras(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoComisuras.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
//	    			var option;
	        			$(xml).find('catCatalogoComisuras').each(function(){
	            			$('#comisuras').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaEspesorLabioInferior(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoEspesorLabioInferior.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	        			$(xml).find('catEspesorLabioInferior').each(function(){
	            			$('#espesor_labio_inf').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaEspesorLabioSuperior(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoEspesorLabioSuperior.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	        			$(xml).find('catEspesorLabioSuperior').each(function(){
	            			$('#espesor_labio_sup').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaProminencia(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogoProminencia.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	        			$(xml).find('catProminencia').each(function(){
	            			$('#prominencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaCatalogoPonEn(catalogo, idObjetivo){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarCatalogo' + catalogo + '.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	        			$(xml).find('cat' + catalogo).each(function(){
	            			$('#' + idObjetivo).append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			});
	    		}
	    	});
	    }

            function cargaImplantacionCeja(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatalogoImplantacionCeja.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		        			$(xml).find('catImplantacionCeja').each(function(){
		            			$('#cmpImplantacionCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		    			});
		    		}
		    	});
		    }
		    function cargaFormaCejas(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatalogoFormaCeja.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		        			$(xml).find('catFormaCeja').each(function(){
		            			$('#cmpFormaCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		    			});
		    		}
		    	});
		    }
		    function cargaTamanoCeja(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatalogoTamanoCeja.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		        			$(xml).find('catTamanoCeja').each(function(){
		            			$('#cmpTamanoCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		    			});
		    		}
		    	});
		    }

		    function cargaTipoSangre(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatalogoTipoSangre.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		        			$(xml).find('catTipoSangre').each(function(){
		            			$('#cmpTipoSangre').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
		    			});
		    		}
		    	});
		    }

		    function cargaComplexion(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarComplexion.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		    			$(xml).find('catComplexion').each(function(){
	            			$('#cmpComplexion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			
		        			});
		    		}
		    	});
		    }
		   
		    function cargaOrejaLobContorno(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarOrejaLobContorno.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		    			$(xml).find('catOrejaLobContorno').each(function(){
	            			$('#cmplobuloContorno').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	    			
		        			});
		    		}
		    	});
		    }
		    
		    function cargaHelixOriginal(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarHelixOriginal.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		    			$(xml).find('cathelixOriginal').each(function(){
	            			$('#cmpHelixOriginal').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			
		        			});
		    		}
		    	});
		    }
		     
		    
		    function cargaDireccionCeja(){
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarDireccionCeja.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			var option;
		    			$(xml).find('catdireccionCeja').each(function(){
	            			$('#cmpDireccionCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			
		        			});
		    		}
		    	});
		    }

		 function obtenerParametrosMediaFiliacion()
		   {
		        var parametros = '&tamanoBoca=' + $('#cmpTamanoBoca option:selected').val();
		        parametros += '&tipoBoca=' + $('#cmpTipoCara option:selected').val();
		        parametros += '&formaMenton=' + $('#cmpFormaMenton option:selected').val();
		        parametros += '&tipoMenton=' + $('#cmpTipoMenton option:selected').val();
		        parametros += '&tez=' + $('#cmpTez option:selected').val();
		        parametros += '&inclinacionMenton=' + $('#cmpInclinacionMenton option:selected').val();

		        parametros += '&colorCabello=' + $('#cmpColorCabello option:selected').val();
		        parametros += '&formaCabello=' + $('#cmpFormaCabello option:selected').val();
		        parametros += '&calvieTipo=' + $('#cmpCalvieTipo option:selected').val();
		        parametros += '&cabelloImplantacion=' + $('#cmpCabelloImplantacion option:selected').val();
		        parametros += '&cantidadCabello=' + $('#cmpCantidadCabello option:selected').val();

		        parametros += '&tamanoOreja=' + $('#cmpTamanoOreja option:selected').val();
		        parametros += '&lobuloParticularidad=' + $('#cmpLobuloParticularidad option:selected').val();
		        parametros += '&lobuloDimension=' + $('#cmpLobuloDimension option:selected').val();
		        parametros += '&lobuloAdherencia=' + $('#cmpLobuloAdherencia option:selected').val();
		        parametros += '&helixAnterior=' + $('#cmpHelixAnterior option:selected').val();
		        parametros += '&helixPosterior=' + $('#cmpHelixPosterior option:selected').val();
		        parametros += '&helixContorno=' + $('#cmpHelixContorno option:selected').val();
		        parametros += '&helixAdherencia=' + $('#cmpHelixAdherencia option:selected').val();
		        parametros += '&formaOreja=' + $('#cmpFormaOreja option:selected').val();

		        parametros += '&tamanoOjos=' + $('#cmpTamanoOjos option:selected').val();
		        parametros += '&colorOjos=' + $('#cmpColorOjos option:selected').val();
		        parametros += '&formaOjos=' + $('#cmpFormaOjos option:selected').val();

		        parametros += '&implantacionCejas=' + $('#cmpImplantacionCejas option:selected').val();
		        parametros += '&formaCejas=' + $('#cmpFormaCejas option:selected').val();
		        parametros += '&tamanoCejas=' + $('#cmpTamanoCejas option:selected').val();

				if($("#Cicatrices1").is(':checked')){
					 parametros += '&cicatricesSenas=' + "1";
				}else{
					 parametros += '&cicatricesSenas=' + "0";
				}
				 parametros += '&cicatricesSenasText=' +$('#Cicatricestext').val();
				 if($("#Protesis1").is(':checked')){
					 parametros += '&protesisSenas=' + "1";
				}else{
					 parametros += '&protesisSenas=' + "0";
				}
				 parametros += '&protesisSenasText=' +$('#Protesistext').val();

				 if($("#Tatuajes1").is(':checked')){
					 parametros += '&tatuajeSenas=' + "1";
				}else{
					 parametros += '&tatuajeSenas=' + "0";
				}
				 parametros += '&tatuajeSenasText=' +$('#Tatuajestext').val();
				 parametros += '&otraSenasText=' +$('#Otrastext').val();
				 if($("#lunares1").is(':checked')){
					 parametros += '&lunaresSenas=' + "1";
				}else{
					 parametros += '&lunaresSenas=' + "0";
				}
				 parametros += '&lunaresSenasText=' +$('#Lunarestext').val();
				 if($("#Defectos1").is(':checked')){
					 parametros += '&defectosSenas=' + "1";
				}else{
					 parametros += '&defectosSenas=' + "0";
				}
				 parametros += '&defectosSenasText=' +$('#Defectostext').val();
				 
				 
				 parametros += '&factorrhOtros=' + $('#cmpFactorRH option:selected').val();
			    
					if($("#lentes1").is(':checked')){
						 parametros += '&lentesOtros=' + "1";
					}else{
						 parametros += '&lentesOtros=' + "0";
					}
					parametros += '&tipoSangreOtros=' + $('#cmpTipoSangre option:selected').val();
						
						parametros += '&complexion=' + $('#cmpComplexion option:selected').val();
								  
								parametros += '&direccionCeja=' + $('#cmpDireccionCejas option:selected').val();

								parametros += '&helixOriginal=' + $('#cmpHelixOriginal option:selected').val();

								parametros += '&orejaLobContorno=' + $('#cmplobuloContorno option:selected').val();
								
					 if($("#barba1").is(':checked')){
						 parametros += '&barbaOtros=' + "1";
					}else{
						 parametros += '&barbaOtros=' + "0";
					}
					 parametros += '&pesoOtros=' +$('#peso').val();

					 if($("#bigote1").is(':checked')){
						 parametros += '&bigoteOtros=' + "1";
					}else{
						 parametros += '&bigoteOtros=' + "0";
					}
					 parametros += '&estauraOtros=' +$('#estatura').val();						
		       
                        //Jacob
		        parametros += '&alturaNasoLabial=' + $('#altura_nasal option:selected').val();
		        parametros += '&comisuras=' + $('#comisuras option:selected').val();
		        parametros += '&espesorLabioInferior=' + $('#espesor_labio_inf option:selected').val();
		        parametros += '&espesorLabioSuperior=' + $('#espesor_labio_sup option:selected').val();
		        parametros += '&prominencia=' + $('#prominencia option:selected').val();
		        parametros += '&anchoNariz=' + $('#ancho_nariz option:selected').val();
		        parametros += '&alturaNariz=' + $('#altura_nariz option:selected').val();
		        parametros += '&baseNariz=' + $('#base_nariz option:selected').val();
		        parametros += '&raizNariz=' + $('#raiz_nariz option:selected').val();
		        parametros += '&frenteAltura=' + $('#frente_altura option:selected').val();
		        parametros += '&frenteAncho=' + $('#frente_ancho option:selected').val();
		        parametros += '&inclinacionFrente=' + $('#inclinacion_frente option:selected').val();
                        // Fin Jacob
		        
		        return parametros;
		   }
	    
		    function pintaDatosMediaFiliacion(xml){
				 //Media Filiacion Cara
				 var tamanoBoca=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioBoca').find('idCampo').text();
				 $('#cmpTamanoBoca').find("option[value='"+tamanoBoca+"']").attr("selected","selected");
				 var tipoCara=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoCara').find('idCampo').text();
				 $('#cmpTipoCara').find("option[value='"+tipoCara+"']").attr("selected","selected");
				 var formaMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaMenton').find('idCampo').text();
				 $('#cmpFormaMenton').find("option[value='"+formaMenton+"']").attr("selected","selected");
				 var tipoMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoMenton').find('idCampo').text();
				 $('#cmpTipoMenton').find("option[value='"+tipoMenton+"']").attr("selected","selected");
				 var tez=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tez').find('idCampo').text();
				 $('#cmpTez').find("option[value='"+tez+"']").attr("selected","selected");
				 var inclinacionMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('inclinacionMenton').find('idCampo').text();
				 $('#cmpInclinacionMenton').find("option[value='"+inclinacionMenton+"']").attr("selected","selected");

				 //Media Filiacion Cabello
				 var colorCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('colorCabello').find('idCampo').text();
				 $('#cmpColorCabello').find("option[value='"+colorCabello+"']").attr("selected","selected");
				 var formaCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaCabello').find('idCampo').text();
				 $('#cmpFormaCabello').find("option[value='"+formaCabello+"']").attr("selected","selected");
				 var calvieTipo=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('calvicieTipo').find('idCampo').text();
				 $('#cmpCalvieTipo').find("option[value='"+calvieTipo+"']").attr("selected","selected");
				 var cabelloImplantacion=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('cabelloImplantacion').find('idCampo').text();
				 $('#cmpCabelloImplantacion').find("option[value='"+cabelloImplantacion+"']").attr("selected","selected");
				 var cantidadCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('cabelloCantidad').find('idCampo').text();
				 $('#cmpCantidadCabello').find("option[value='"+cantidadCabello+"']").attr("selected","selected");
				 
				 //Media Filiacion Oreja
				 var tamanoOreja=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaTamanio').find('idCampo').text();
				 $('#cmpTamanoOreja').find("option[value='"+tamanoOreja+"']").attr("selected","selected");
				 var lobuloParticularidad=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobParticularidad').find('idCampo').text();
				 $('#cmpLobuloParticularidad').find("option[value='"+lobuloParticularidad+"']").attr("selected","selected");
				 var lobuloDimension=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobDimension').find('idCampo').text();
				 $('#cmpLobuloDimension').find("option[value='"+lobuloDimension+"']").attr("selected","selected");
				 var lobuloAdherencia=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobAdherencia').find('idCampo').text();
				 $('#cmpLobuloAdherencia').find("option[value='"+lobuloAdherencia+"']").attr("selected","selected");
				 var helixAnterior=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixAnterior').find('idCampo').find('idCampo').text();
				 $('#cmpHelixAnterior').find("option[value='"+helixAnterior+"']").attr("selected","selected");
				 var helixPosterior=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixPosterior').find('idCampo').text();
				 $('#cmpHelixPosterior').find("option[value='"+helixPosterior+"']").attr("selected","selected");
				 var helixContorno=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixContorno').find('idCampo').text();
				 $('#cmpHelixContorno').find("option[value='"+helixContorno+"']").attr("selected","selected");
				 var helixAdherencia=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixAdherencia').find('idCampo').text();
				 $('#cmpHelixAdherencia').find("option[value='"+helixAdherencia+"']").attr("selected","selected");
				 var formaOreja=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaOreja').find('idCampo').text();
				 $('#cmpFormaOreja').find("option[value='"+formaOreja+"']").attr("selected","selected");
				 
				 //Media Filiacion Ojos
				 var tamanoOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioOjo').find('idCampo').text();
				 $('#cmpTamanoOjos').find("option[value='"+tamanoOjos+"']").attr("selected","selected");
				 var colorOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('colorOjos').find('idCampo').text();
				 $('#cmpColorOjos').find("option[value='"+colorOjos+"']").attr("selected","selected");
				 var formaOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaOjos').find('idCampo').text();
				 $('#cmpFormaOjos').find("option[value='"+formaOjos+"']").attr("selected","selected");
				//Media filiacion Labios
				 var labioNasal=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('alturaNasoLabial').find('idCampo').text();
				 $('#altura_nasal').find("option[value='"+labioNasal+"']").attr("selected","selected");
				 var comisurasLabios=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('labioComisuras').find('idCampo').text();
				 $('#comisuras').find("option[value='"+comisurasLabios+"']").attr("selected","selected");
				 var prominenciaLabios=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('labiosProminencia').find('idCampo').text();
				 $('#prominencia').find("option[value='"+prominenciaLabios+"']").attr("selected","selected");
				 var espesorLabiosSup=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('espesorLabioSup').find('idCampo').text();
				 $('#espesor_labio_sup').find("option[value='"+espesorLabiosSup+"']").attr("selected","selected");
				 var espesorLabiosInfe=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('espesorLabioInf').find('idCampo').text();
				 $('#espesor_labio_inf').find("option[value='"+espesorLabiosInfe+"']").attr("selected","selected");

				 var anchonariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('anchoNariz').find('idCampo').text();
				 $('#ancho_nariz').find("option[value='"+anchonariz+"']").attr("selected","selected");
				 var alturaNariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('alturaNariz').find('idCampo').text();
				 $('#altura_nariz').find("option[value='"+alturaNariz+"']").attr("selected","selected");
				 var raiznariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('raizNariz').find('idCampo').text();
				 $('#raiz_nariz').find("option[value='"+raiznariz+"']").attr("selected","selected");
				 var baseNariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('baseNariz').find('idCampo').text();
				 $('#base_nariz').find("option[value='"+baseNariz+"']").attr("selected","selected");
				 
				 var alturaFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteAltura').find('idCampo').text();
				 $('#frente_altura').find("option[value='"+alturaFrente+"']").attr("selected","selected");
				 var anchoFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteAncho').find('idCampo').text();
				 $('#frente_ancho').find("option[value='"+anchoFrente+"']").attr("selected","selected");
				 var inclinacionFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteInclinacion').find('idCampo').text();
				 $('#inclinacion_frente').find("option[value='"+inclinacionFrente+"']").attr("selected","selected");
				var implantacionCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('implantacionCeja').find('idCampo').text();
				 $('#cmpImplantacionCejas').find("option[value='"+implantacionCejas+"']").attr("selected","selected");
				 var formaCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaCeja').find('idCampo').text();
				 $('#cmpFormaCejas').find("option[value='"+formaCejas+"']").attr("selected","selected");
				 var tamanoCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioCeja').find('idCampo').text();
				 $('#cmpTamanoCejas').find("option[value='"+tamanoCejas+"']").attr("selected","selected");
				 var cicatricesParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneCicatrices').text();
                if(cicatricesParti=="true"){
                	$('#Cicatrices1').attr("checked","checked");
                }else{
                	$('#Cicatrices2').attr("checked","checked");
                }
                var cicatricesPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionCicatrices').text();
                $('#Cicatricestext').val(cicatricesPartiText);
                var tatuParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneTatuajes').text();
                if(tatuParti=="true"){
                	$('#Tatuajes1').attr("checked","checked");
                }else{
                	$('#Tatuajes2').attr("checked","checked");
                }
                var tatuPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionTatuajes').text();
                $('#Tatuajestext').val(tatuPartiText);
                var lunaresParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneLunares').text();
                if(lunaresParti=="true"){
                	$('#Lunares1').attr("checked","checked");
                }else{
                	$('#Lunares2').attr("checked","checked");
                }
                var lunaresPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionLunares').text();
                $('#Lunarestext').val(lunaresPartiText);
                var defectosParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneDefectosFisicos').text();
                if(defectosParti=="true"){
                	$('#Defectos1').attr("checked","checked");
                }else{
                	$('#Defectos2').attr("checked","checked");
                }	
                var defectosPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionDefectosFisicos').text();
                $('#Defectostext').val(defectosPartiText);
                var protesisParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneProtesis').text();
                if(protesisParti=="true"){
                	$('#Protesis1').attr("checked","checked");
                }else{
                	$('#Protesis2').attr("checked","checked");
                }
                var protesisPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionProtesis').text();
                $('#Protesistext').val(protesisPartiText);
                var otraPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionOtraSenia').text();
                $('#Otrastext').val(otraPartiText);

                var factorRH=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('factorRH').text();
				if(factorRH=="Negativo"){
					$('#cmpFactorRH').find("option[value=0]").attr("selected","selected");
				}else if(factorRH==""){
					$('#cmpFactorRH').find("option[value=-1]").attr("selected","selected");
				}
				else{
					$('#cmpFactorRH').find("option[value=1]").attr("selected","selected");
				}
				var lentes=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('usaLentes').text();
                if(lentes=="true"){
                	$('#lentes1').attr("checked","checked");
                }else{
                	$('#lentes2').attr("checked","checked");
                }
                var tipoSangre=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoSangre').find('idCampo').text();
				 $('#cmpTipoSangre').find("option[value='"+tipoSangre+"']").attr("selected","selected");
				 var complexion=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('complexion').find('idCampo').text();
				 $('#cmpComplexion').find("option[value='"+complexion+"']").attr("selected","selected");

				   
				 var lobuloContorno=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobContorno').find('idCampo').text();
				 $('#cmplobuloContorno').find("option[value='"+lobuloContorno+"']").attr("selected","selected");

				 var helixOriginal=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixOriginal').find('idCampo').text();
				 $('#cmpHelixOriginal').find("option[value='"+helixOriginal+"']").attr("selected","selected");

				 var direccionCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('direccionCeja').find('idCampo').text();
				 $('#cmpDireccionCejas').find("option[value='"+direccionCejas+"']").attr("selected","selected");
				 
				
				 var barba=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tieneBarba').text();
                    if(barba=="true"){
                    	$('#barba1').attr("checked","checked");
                    }else{
                    	$('#barba2').attr("checked","checked");
                    }
                    var peso=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('peso').text();
                    $('#peso').val(peso);
                    var estatura=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('estatura').text();
                    $('#estatura').val(estatura);
                    var bigote=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tieneBigote').text();
                    if(bigote=="true"){
                    	$('#bigote1').attr("checked","checked");
                    }else{
                    	$('#bigote2').attr("checked","checked");
                    }
			}

			function deshabilitaDatosMediaFiliacion(){
				 $('#cmpTamanoBoca').attr("disabled","disabled");
				 $('#cmpTipoCara').attr("disabled","disabled");
				 $('#cmpFormaMenton').attr("disabled","disabled");
				 $('#cmpTipoMenton').attr("disabled","disabled");
				 $('#cmpTez').attr("disabled","disabled");
				 $('#cmpInclinacionMenton').attr("disabled","disabled");

				 $('#cmpColorCabello').attr("disabled","disabled");
				 $('#cmpFormaCabello').attr("disabled","disabled");
				 $('#cmpCalvieTipo').attr("disabled","disabled");
				 $('#cmpCabelloImplantacion').attr("disabled","disabled");
				 $('#cmpCantidadCabello').attr("disabled","disabled");
				 
				 $('#cmpTamanoOreja').attr("disabled","disabled");
				 $('#cmpLobuloParticularidad').attr("disabled","disabled");
				 $('#cmpLobuloDimension').attr("disabled","disabled");
				 $('#cmpLobuloAdherencia').attr("disabled","disabled");
				 $('#cmpHelixAnterior').attr("disabled","disabled");
				 $('#cmpHelixPosterior').attr("disabled","disabled");
				 $('#cmpHelixContorno').attr("disabled","disabled");
				 $('#cmpHelixAdherencia').attr("disabled","disabled");
				 $('#cmpFormcmpFormaOrejaaMenton').attr("disabled","disabled");

				 $('#cmpTamanoOjos').attr("disabled","disabled");
				 $('#cmpColorOjos').attr("disabled","disabled");
				 $('#cmpFormaOjos').attr("disabled","disabled");
				 $('#altura_nasal').attr("disabled","disabled");
				 $('#comisuras').attr("disabled","disabled");
				 $('#prominencia').attr("disabled","disabled");
				 $('#espesor_labio_sup').attr("disabled","disabled");
				 $('#espesor_labio_inf').attr("disabled","disabled");
				 $('#ancho_nariz').attr("disabled","disabled");
				 $('#altura_nariz').attr("disabled","disabled");
				 $('#raiz_nariz').attr("disabled","disabled");
				 $('#base_nariz').attr("disabled","disabled");
				 $('#frente_altura').attr("disabled","disabled");
				 $('#frente_ancho').attr("disabled","disabled");
				 $('#inclinacion_frente').attr("disabled","disabled");
				 $('#cmpImplantacionCejas').attr("disabled","disabled");
				 $('#cmpFormaCejas').attr("disabled","disabled");
				 $('#cmpTamanoCejas').attr("disabled","disabled");

				 $('#Cicatrices1').attr("disabled","disabled");
				 $('#Cicatrices2').attr("disabled","disabled");
				 $('#Cicatricestext').attr("disabled","disabled");
				 $('#Tatuajes1').attr("disabled","disabled");
				 $('#Tatuajes2').attr("disabled","disabled");
				 $('#Tatuajestext').attr("disabled","disabled");
				 $('#Lunares1').attr("disabled","disabled");
				 $('#Lunares2').attr("disabled","disabled");
				 $('#Lunarestext').attr("disabled","disabled");
				 $('#Defectos1').attr("disabled","disabled");
				 $('#Defectos2').attr("disabled","disabled");
				 $('#Defectostext').attr("disabled","disabled");
				 $('#Protesis1').attr("disabled","disabled");
				 $('#Protesis2').attr("disabled","disabled");
				 $('#Protesistext').attr("disabled","disabled");
				 $('#Otrastext').attr("disabled","disabled");
				 $('#cmpFactorRH').attr("disabled","disabled");
				 $('#lentes1').attr("disabled","disabled");
				 $('#lentes2').attr("disabled","disabled");
				 $('#cmpTipoSangre').attr("disabled","disabled");
				 $('#barba1').attr("disabled","disabled");
				 $('#barba2').attr("disabled","disabled");
				 $('#peso').attr("disabled","disabled");
				 $('#estatura').attr("disabled","disabled");
				 $('#bigote1').attr("disabled","disabled");
				 $('#bigote2').attr("disabled","disabled");
				 $('#cmpComplexion').attr("disabled","disabled");
				  $('#cmplobuloContorno').attr("disabled","disabled");
				  $('#cmpHelixOriginal').attr("disabled","disabled");
				  $('#cmpDireccionCejas').attr("disabled","disabled");
				  $('#cmpFormaOreja').attr("disabled","disabled");
				  
			}

			function enviaAvisoDetencion(){
				var params = '';
				params += 'idIndividuo='+idindi;
				params += '&calidadDelIndividuo=0';
				params += '&numeroExpediente='+numeroExpediente;
					params+='&esPersonaMoral=false';
					params+='&idCondicion=' + $(':radio[name=radio]:checked').val();
					params+='&esDesconocido=' + $('#btnPResponsableDesconocido').is(':checked');
					params+='&esMenorDeEdad=' + $('#chkPResponsableMenor').is(':checked');
					
					var estaDetenido =$('#chkPResponsableDetenido').is(':checked');
					if(estaDetenido){
						 $('#ingresarTiempoLapsoPResponsable').attr("disabled","");
					}
					params+='&estaDetenido=' + estaDetenido;
					if(estaDetenido){//Cuando el check detenido es palomeado 
						
					}
					//Datos generales, media filiacion, medios de contacto, documentos de identificacion
					var datosPestania = obtenerParametrosDatosGenerales();//Include de datos generales
					params += datosPestania;
					//DAtos fechas
					datosPestania = recuperaDatosTiempoLapso();
					params += datosPestania;
					var op=0;
					$.ajax({								
				    	  type: 'POST',
				    	  url: '<%=request.getContextPath()%>/enviarAvisosDetencion.do',
				    	  data: params,				
				    	  dataType: 'xml',
				    	  success: function(xml){
				    		alertDinamico("Defensor solicitado");
				    		bloqueaCamposTiempoLapso(0);
				    	  }
				    	});
					$('#ingresarTiempoLapsoPResponsable').attr("disabled","disabled");
					$('#ingresarTiempoLapsoPResponsable').hide();
			}

			function habilitaDatosMediaFiliacion(){
			//	var $widget = $("select").multiselect();
			//	var	state = true; 
			 //  state = !state; 
			  // $widget.multiselect(state ? 'disable' : 'enable'); 
				
				 $('#cmpTamanoBoca').attr("disabled","");
				 $('#cmpTipoCara').attr("disabled","");
				 $('#cmpFormaMenton').attr("disabled","");
				 $('#cmpTipoMenton').attr("disabled","");
				 $('#cmpTez').attr("disabled","");
				 $('#cmpInclinacionMenton').attr("disabled","");

				 $('#cmpColorCabello').attr("disabled","");
				 $('#cmpFormaCabello').attr("disabled","");
				 $('#cmpCalvieTipo').attr("disabled","");
				 $('#cmpCabelloImplantacion').attr("disabled","");
				 $('#cmpCantidadCabello').attr("disabled","");
				 
				 $('#cmpTamanoOreja').attr("disabled","");
				 $('#cmpLobuloParticularidad').attr("disabled","");
				 $('#cmpLobuloDimension').attr("disabled","");
				 $('#cmpLobuloAdherencia').attr("disabled","");
				 $('#cmpHelixAnterior').attr("disabled","");
				 $('#cmpHelixPosterior').attr("disabled","");
				 $('#cmpHelixContorno').attr("disabled","");
				 $('#cmpHelixAdherencia').attr("disabled","");
				 $('#cmpFormcmpFormaOrejaaMenton').attr("disabled","");

				 $('#cmpTamanoOjos').attr("disabled","");
				 $('#cmpColorOjos').attr("disabled","");
				 $('#cmpFormaOjos').attr("disabled","");
				 $('#altura_nasal').attr("disabled","");
				 $('#comisuras').attr("disabled","");
				 $('#prominencia').attr("disabled","");
				 $('#espesor_labio_sup').attr("disabled","");
				 $('#espesor_labio_inf').attr("disabled","");
				 $('#ancho_nariz').attr("disabled","");
				 $('#altura_nariz').attr("disabled","");
				 $('#raiz_nariz').attr("disabled","");
				 $('#base_nariz').attr("disabled","");
				 $('#frente_altura').attr("disabled","");
				 $('#frente_ancho').attr("disabled","");
				 $('#inclinacion_frente').attr("disabled","");

				 $('#cmpImplantacionCejas').attr("disabled","");
				 $('#cmpFormaCejas').attr("disabled","");
				 $('#cmpTamanoCejas').attr("disabled","");

				 $('#Cicatrices1').attr("disabled","");
				 $('#Cicatrices2').attr("disabled","");
				 $('#Cicatricestext').attr("disabled","");
				 $('#Tatuajes1').attr("disabled","");
				 $('#Tatuajes2').attr("disabled","");
				 $('#Tatuajestext').attr("disabled","");
				 $('#Lunares1').attr("disabled","");
				 $('#Lunares2').attr("disabled","");
				 $('#Lunarestext').attr("disabled","");
				 $('#Defectos1').attr("disabled","");
				 $('#Defectos2').attr("disabled","");
				 $('#Defectostext').attr("disabled","");
				 $('#Protesis1').attr("disabled","");
				 $('#Protesis2').attr("disabled","");
				 $('#Protesistext').attr("disabled","");
				 $('#Otrastext').attr("disabled","");
				 $('#cmpFactorRH').attr("disabled","");
				 $('#lentes1').attr("disabled","");
				 $('#lentes2').attr("disabled","");
				 $('#cmpTipoSangre').attr("disabled","");
				 $('#barba1').attr("disabled","");
				 $('#barba2').attr("disabled","");
				 $('#peso').attr("disabled","");
				 $('#estatura').attr("disabled","");
				 $('#bigote1').attr("disabled","");
				 $('#bigote2').attr("disabled","");
				 $('#cmpComplexion').attr("disabled","");
				  $('#cmplobuloContorno').attr("disabled","");
				  $('#cmpHelixOriginal').attr("disabled","");
				  $('#cmpDireccionCejas').attr("disabled","");
				  $('#cmpFormaOreja').attr("disabled","");
			}
			
			//Llena el grid con los resultados de la busqueda
			var banderaCargarORecargar=0;
			function cargaGridReincidenciaElemento() {
				var valorCampoNombre = document.getElementById('datosGeneralesCmpNombres').value;
				var valorCampoApPat  = document.getElementById('datosGeneralesCmpApaterno').value;
				var valorCampoApMat = document.getElementById('datosGeneralesCmpMaterno').value;
				
				if(valorCampoNombre == "")
					valorCampoNombre = null
				if(valorCampoApPat == "")
					valorCampoApPat = null
				if(valorCampoApMat == "")
					valorCampoApMat = null
					
				if(valorCampoNombre == null || valorCampoApPat == null ||  valorCampoApMat == null)	
					alertDinamico("Es necesario escribir el nombre completo para poder consultar la reincidencia");
				else{
					muestraPopupReincidenciaElemento();
					//Inicia grid
					if(banderaCargarORecargar==0){
						jQuery("#gridReincidenciaElemento").jqGrid({
										url:'<%=request.getContextPath()%>/buscarReincidenciaDePersona.do?nombre='+valorCampoNombre +'&paterno='+valorCampoApPat+'&materno='+valorCampoApMat+ '&numeroExpediente='+ numeroExpediente+'', 
										datatype: "xml",
										colNames:['Numero General del caso','Fecha de Apertura','Estatus'],
										colModel:[ 	{name:'NumeroGeneralCaso',index:'numeroGeneralCaso', width:200},
													{name:'FechaApertura',index:'fechaApertura', width:120},							
													{name:'Estatus',index:'estatus', width:100}
													],
							pager: jQuery('#paginadorReincidenciaElemento'),
							rowNum:5,
							rowList:[10,20,30],
							autowidth: false,
							autoheight:false,
							sortname: 'FechaApertura',
							viewrecords: true,
							multiselect: true,
							sortorder: "desc"
						}).navGrid('#paginadorReincidenciaElemento',{edit:false,add:false,del:false});
			        	banderaCargarORecargar=1;
					}
					else{
				    	jQuery("#gridReincidenciaElemento").jqGrid('setGridParam',{
				    		url:'<%=request.getContextPath()%>/buscarReincidenciaDePersona.do?nombre='+valorCampoNombre +'&paterno='+valorCampoApPat+'&materno='+valorCampoApMat+
				    		'&numeroExpediente='+ numeroExpediente+
							'',datatype: "xml" });
						$("#gridReincidenciaElemento").trigger("reloadGrid");
				    }//Fin grid
				}
			}
			
			//Inician cambios de RGG
			function muestraPopupReincidenciaElemento()
			{	
					$( "#dialog-Reincidencia").dialog({
						resizable: true,
						width:450,
						height:270,					
						modal: true,
						buttons: {
							"Relacionar": function() {
								
								if(deshabilitarCampos == false){
									if(jQuery("#gridReincidenciaElemento").jqGrid('getGridParam','selarrrow') != "")
										registrarReincidenciasXElemento();
									else
										alertDinamico("Debe de seleccionar al menos un registro");
								}
							},

							"Cerrar": function() {
								$( "#dialog:ui-dialog" ).hide();
								$( this ).dialog( "close" );
								$( "#dialog:ui-dialog" ).dialog( "destroy" );
							}
						}
					});
					$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();	
			}
			
			
			//Llena el grid con los resultados de la busqueda de las relaciones entre un elemento y los casos registrados en BD
			var banderaCargarORecargarGrid2=0;
			function cargaGridReincidenciasXElemento() {
					muestraPopupReincidenciaElementoCaso();
					//Inicia grid
					if(banderaCargarORecargarGrid2==0){
						jQuery("#gridReincidenciaElementoCaso").jqGrid({
							url:'<%=request.getContextPath()%>/consultarReincidenciaXElemento.do?idElemento='+idElemento +'',
										datatype: "xml",
										colNames:['Numero General del caso','Fecha de Apertura','Estatus'],
										colModel:[ 	{name:'NumeroGeneralCaso',index:'numeroGeneralCaso', width:200},
													{name:'FechaApertura',index:'fechaApertura', width:120},							
													{name:'Estatus',index:'estatus', width:100}
													],
							pager: jQuery('#paginadorReincidenciaElementoCaso'),
							rowNum:5,
							rowList:[10,20,30],
							autowidth: false,
							autoheight:false,
							sortname: 'FechaApertura',
							viewrecords: true,
							multiselect: false,
							sortorder: "desc"
						}).navGrid('#paginadorReincidenciaElementoCaso',{edit:false,add:false,del:false});
						banderaCargarORecargarGrid2=1;
					}
					else{
				    	jQuery("#gridReincidenciaElementoCaso").jqGrid('setGridParam',{
				    		url:'<%=request.getContextPath()%>/consultarReincidenciaXElemento.do?idElemento='+idElemento + 
							'',datatype: "xml" });
						$("#gridReincidenciaElementoCaso").trigger("reloadGrid");
				    }//Fin grid		
			}

			
			//Inician cambios de RGG
			function muestraPopupReincidenciaElementoCaso()
			{	
					$( "#dialog-ReincidenciaCaso").dialog({
						resizable: true,
						width:500,
						height:270,					
						modal: true,
						buttons: {
							"Cerrar": function() {
								$( "#dialog:ui-dialog" ).hide();
								$( this ).dialog( "close" );
								$( "#dialog:ui-dialog" ).dialog( "destroy" );
							}
						}
					});
					$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();	
			}	


			function registrarReincidenciasXElemento(){
				var params = "";
			    params = '&idFuncionario=' + 1;
			    params += '&idElemento=' + idElemento;
			    params += '&idCasos=' + jQuery("#gridReincidenciaElemento").jqGrid('getGridParam','selarrrow');
			    if(idElemento != "null" && idElemento != 0){
			    	$.ajax({
						type: 'POST',
						url: '<%=request.getContextPath()%>/registrarReincidenciasXElemento.do',
						data: params, 
						async: false,
						dataType: 'xml',
						success: function(xml){	
								alertDinamico("La(s) relaci\u00F3ne(s) se registraron de forma correcta");
						}
					});
			    }
			    else
			    	alertDinamico("Debe de guardar primero el elemento");
			    	
		}

		/*Funcion que permite guardar una imagen y la asocia a un elemento*/
		function enviaImagenDeElemento(idElemento,idIndividuo){
				document.frmImagenElemento.muestraDetenidoModificar.value = muestraDetenidoModificar;
				document.frmImagenElemento.elementoID.value = idElemento;
				document.frmImagenElemento.idIndividuo.value = idIndividuo;
				document.frmImagenElemento.numeroExpediente.value = numeroExpediente;
		    	document.frmImagenElemento.submit();
		}

		function cargaDefensor(nombre,id){
			var row=$('#'+id);
			idConDefensor=id;
			$(row).remove();
			$("#etiquetaDefensor").remove();
			$('#idDefensor').append('<tr id="etiquetaDefensor"><td>&nbsp;&nbsp;&nbsp;&nbsp;Defensor:</td></tr><tr id="'+id+'"><td class="noSub"><a id="consultarDefensor" onclick="modificaDefensor('+id+')">&nbsp;&nbsp;&nbsp;&nbsp;'+nombre+'</a></td></tr>');
			
		} 
		function modificaDefensor(id) {
				idWindowIngresarDefensor++;
				$.newWindow({id:"iframewindowIngresarDefensor" + idWindowIngresarDefensor, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Defensor", type:"iframe"});
			    $.updateWindowContent("iframewindowIngresarDefensor" + idWindowIngresarDefensor,'<iframe src="<%=request.getContextPath()%>/ingresarDefensorView.do?numeroExpediente='+numeroExpediente+ '&idDenunciante='+ id+ '&idPropPapa='+id+'" width="1040" height="570" />');
		}

    </script>
  </head>
  <body>
  <div id="dialog-Alert" style="display: none">
	<table align="center">
	<tr>
	<td align="center">
	<span id="divAlertTexto"></span>
	</td>
	</tr>
	</table>	
	</div>
    <table border="0">
      <tr valign="top">
		<td>
		<table id="iProbResponsableHeader" width="100%" border="0">
			<tr>
				<td width="10%" class="seccion">DENUNCIA</td>
				<td width="70%">&nbsp;</td>
				<td width="30%" align="right" class="seccion">EXPEDIENTE</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="seccion">Tipo de Responsable :</td>
		<td><select id="cbxProbResponsableTipoResp">
			<option value="1">F&iacute;sica</option>
			<option value="0">Moral</option>
		</select>
		<td>
		<td><input type="button" class="btn_Generico" id="anularInvolucrado" value="Anular Involucrado"></td>
	</tr>
</table>
<table>
	<tr valign="top">
		<td width="1060"><!-- FIN tabla Probable Responsable -->



		<table id="iProbResponsableWorkSheet" class="back_victima"
			width="1060" height="365" border="0">
			<tr valign="top">
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12%" height="130" align="center"><img
							src="<%=request.getContextPath()%>/resources/images/img_denunciante.png"
							alt="" width="105" height="105" id="imgConFoto" /></td>
						<td width="12%" height="130" align="center" valign="middle">
						<img src="resources/images/fingerPrint.JPG" width="100"
							height="100" /></td>
						<td width="12%" height="130" align="center" valign="top">
						<table width="12%" border="0" cellspacing="0" cellpadding="0"
							class="celda2" id="tableUAVDOpsHide">
							<tr>
								<td height="29" class="seccion">Condición:</td>
							</tr>
							<tr>
								<td align="left">Vivo</td>
								<td width="3%"><input type="radio" name="radio"
									id="btnPResponsableEsVivo" value="1" checked="checked" /></td>
							</tr>
							<tr>
								<td width="10%" align="left">Muerto</td>
								<td width="3%"><input type="radio" name="radio"
									id="btnPResponsableEsMuerto" value="0" /></td>
							</tr>
							<tr>
								<td align="left">Desconocido</td>
								<td><input type="checkbox"
									name="btnPResponsableDesconocido "
									id="btnPResponsableDesconocido" value="D" /></td>
							</tr>
							<!-- tr>
                                <td align="left">Menor de edad</td>
                                <td>
                                    <input type="checkbox" id="chkPResponsableMenor" />
                                </td>                 
                            </tr-->
							<tr>
								<td align="left" id="etiquetaDetenido" style="display: none">Detenido</td>
								<td><input type="checkbox" id="chkPResponsableDetenido"
									style="display: none" /></td>
							</tr>
							<tr>
								<td align="left"><span id="tdConDefensor">Con Defensor</span></td>
								<td><input type="checkbox" id="chkDefensor" /></td>
							</tr>
						</table>
						</td>

						<td width="30%" align="center">
						<table id="datosDetenido" width="100%" height="150" border="0"
							cellspacing="0" cellpadding="0" style="display: none;">
							<tr>
								<td colspan="2" height="20" valign="middle" class="seccion">INGRESAR
								DATOS DE DETENCI&Oacute;N</td>
							</tr>
							<tr>
								<td align="left"><input type="submit"
									id="ingresarTiempoEspecificamentePResponsable"
									value="Espec&iacute;ficamente" /></td>
								<td width="75%" rowspan="3">
								<div id="divEspecifico" style="display: block;"><jsp:include
									page="/WEB-INF/paginas/ingresarTiempoEspecificamente.jsp"
									flush="true"></jsp:include></div>
								<div id="divLapso" style="display: none;"><jsp:include
									page="/WEB-INF/paginas/ingresarTiempoLapsoDetencion.jsp"
									flush="true"></jsp:include></div>
								<div id="divOtro" style="display: none;"><textarea
									rows="5" cols="20" id="textNarrativa" readonly="readonly"></textarea>
								</div>
								</td>
							</tr>
							<tr>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="left"><input style="width: 112px" type="submit"
									id="ingresarTiempoOtroPResponsable" value="Otro" /></td>
							</tr>
						</table>
						</td>

						<td width="58%" height="130" align="center" valign="top">
						
						<table width="100%" height="143" cellpadding="0" cellspacing="0"
							class="celda2">
							<tr>
								<td width="22%" height="30" align="right">Nombre:</td>
								<td width="29%"><div id="nombProResponsable" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div></td>
							</tr>
							<tr>
								<td width="22%" height="28" align="right">Apellido Paterno:</td>
								<td width="29%" height="28"><div id="apPatProbResponsable" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div></td>
							</tr>
							<tr>
								<td width="22%" height="35" align="right">Apellido Materno:</td>
								<td height="35"><div id="apMatProbResponsable" style="border: 0; background: #DDD;width: 210px;">&nbsp;</div></td>
							</tr>
						</table>
						
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr valign="top">
				<td colspan="3">
				<table width="100%" border="0">
					<tr valign="top">
						<td width="20%" align="left" id="idDefensorTdDad">
						<table width="100%" cellpadding="0" cellspacing="0"
							id="idDefensor"></table>
						</td>
						<td align="left" width="80%" valign="top">
						<form id="frmImagenElemento" name="frmImagenElemento"
							action="<%=request.getContextPath()%>/ingresarImagenDelElementoPResponsablePJENS.do"
							method="post" enctype="multipart/form-data">
							
							<input type="hidden" name="muestraDetenidoModificar" /> 
							<input type="hidden" name="elementoID" /> 
							<input type="hidden" name="idIndividuo" />
							<input type="file" name="archivo" id="uploadArchivo" style="display: "> 
							<input type="text" name="numeroExpediente" id="numeroExpediente" style="display: none">
							
						</form>

						<input type="button" class="btn_modificar" value="Modificar Datos"
							id="iVictimaBtnModificarDatos" /> <input type="button"
							class="btn_guardar" value="Guardar" id="iVictimaBtnGuardar"/> <input
							type="button" class="btn_mediano" id="btnBuscarReincidencia"
							value="Buscar Reincidencia"
							onclick="cargaGridReincidenciaElemento()" /> <input type="button"
							class="btn_mediano" id="btnConsultarExpedientes"
							value="Consultar expedientes relacionados "
							onclick="cargaGridReincidenciasXElemento()" /></td>
					</tr>
				</table>
				</td>
			</tr>


		</table>
		<!-- FIN tabla Probable Responsable --> <!-- Tabla INGRESAR ORGANIZACION -->
		<jsp:include page="ingresarOrganizacion.jsp" /> <!-- FIN Tabla INGRESAR ORGANIZACION -->
		</td>
	</tr>
	<tr valign="top" width="1065">
		<td>
		<table width="1065" border="0">
			<tr valign="top">
				<td width="100%" valign="top"><!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
				<div id="iProbResponsablePane">
				<dl>
					<dt id="cejaDatosGenerales">Datos Generales</dt>
					<dd><jsp:include page="datosGeneralesView.jsp" /></dd>
					<dt id="cejaDomicilio">Domicilio</dt>
					<dd><jsp:include page="ingresarDomicilioView.jsp" /></dd>
					<dt id="cejaMediaFiliacion">Media Filiaci&oacute;n (Opcional)</dt>
					<dd><jsp:include page="mediaFiliacionView.jsp" /></dd>
					<dt id="cejaMediosContacto">Medios de Contacto</dt>
					<dd><jsp:include page="ingresarMediosContactoView.jsp" /></dd>
					<dt id="cejaDocumentosIdentificacion">Documentos de
					Identificación</dt>
					<dd><jsp:include
						page="ingresarDocumentoIdentificacionView.jsp" /></dd>
				</dl>
				</div>
				<!-- FIN ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<div id="iProbResponsableAccordionDialogoMenorEdad"
	title="Menor de edad">
<table>
	<tr>
		<td>Denuncia</td>
		<td>&nbsp;</td>
		<td>Expediente</td>
	</tr>
	<tr>
		<td>Calidad:</td>
		<td><input type="text" value='<bean:message key="probableResponsable"/>' size="50"
			maxlength="40" id="iPResponsableDialogoCmpCalidad" /></td>
	</tr>
	<tr>
		<td>Nombre:</td>
		<td><input type="text" value="Nombre ApPaterno ApMaterno"
			size="50" maxlength="40" id="iPResponsableDialogoCmpNombre" /></td>
	</tr>
</table>
</div>


<!-- ETIQUETAS PARA LA REINCIDENCIA DE UN OBJETO -->
<div id="dialog-Reincidencia"
	title="Reincidencia(s) asociada(s) al elemento">
<table id="gridReincidenciaElemento"></table>
<div id="paginadorReincidenciaElemento"></div>
</div>

<div id="dialog-ReincidenciaCaso"
	title="Reincidencia(s) registrada(s) al elemento">
<table id="gridReincidenciaElementoCaso"></table>
<div id="paginadorReincidenciaElementoCaso"></div>
</div>

</body>
</html>