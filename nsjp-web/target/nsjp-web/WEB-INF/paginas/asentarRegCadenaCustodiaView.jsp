<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <%@ page import="mx.gob.segob.nsjp.comun.enums.objeto.Objetos" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ingresar Persona</title>   
    
<!--	Hoja de estilo para los gadgets-->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<!--    Hoja de estilo para easyaccordion-->
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
     
      <!--Hoka de estilo para el texto dentro de los acordeones-->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
      <!--Hoja de estilo para los popups-->
      <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
      
      <!--Scripts necesarios para el funcionamiento de la JSP-->

      <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
        
      <!--Scrip para el idioma del calendario-->
      <script type="text/javascript"  src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
      
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
      
      <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
      <script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
   	  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
      <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>   
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
      
	  <!--  Estilo para mostrar de una manera mas grande el titulo del Folio de la Custodia -->
	  <style type="text/css">
    	.tituloFolio {
    		font-size: 16px;
		}
		textarea {
			resize: none;
		}
      </style>
		
		
      <script type="text/javascript">
		var numeroExpediente="";
		var idWindowObEvidencia=1;
		var cadenaCustodia="";
		var consultaCadena="";
		var folioCadenaCustodia="";
		
		var actual=1;
		var nuevoArray= new Array();
		var cantidad=0;
		
		//variables para setear las fechas y horas maximas
		var fechaServidor="";
		var fechaMax="";
		var timeMax="";
		
		//variable para almacenar el id de la evidencia que se quiere anular
		var idEvdAElmnr=0;
		var idObjAElmnr=0;
		var tipoObjAElmnr=0;
		var contextoPagina = "${pageContext.request.contextPath}";
		
		//variable para el numero de documentos que tiene el expediente
		var num= window.parent.num;
		
		//varibale para saber si venimos desde IPH
		var fromIPH=0;
		
		//Comienza funcion on ready del documento
		$(document).ready(function() {
			if(num==null || num==""){
				num=0;
			}
			$( "#tabsprincipalconsulta" ).tabs();
			numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
			consultaCadena='<%= request.getParameter("consultaCadena")%>';
			//seteamos el evento del boton eliminar evidencia de cadena de custodia
			$("#idEliminarEvidencia").click(anularEvidenciaCadCus);
			//operacion, variable con valor 1 o 2, 1 para alta 2 para consulta
			var operacion='<%= request.getParameter("operacion")%>';
			//bandera para ver si venimos de IPH para filtrar el catalogo de tipos de objetos para evidencias
			fromIPH='<%= request.getParameter("IPH")%>';
			if(operacion!=null && operacion!='null')
			{
				if(parseInt(operacion)==1)
				{
					$("#spanCadCusIngCon").html("Ingresar Cadena de Custodia");
					$("#spanEvCadCusIngCon").html("Ingresar Evidencia(s) de Cadena de Custodia");
					$("#spanEslCadCusIngCon").html("Ingresar Eslabón de Cadena de Custodia");
				}
				else if(parseInt(operacion)==2)
				{
					$("#spanCadCusIngCon").html("Consultar Cadena de Custodia");
					$("#spanEvCadCusIngCon").html("Consultar Evidencia(s) de Cadena de Custodia");
					$("#spanEslCadCusIngCon").html("Consultar Eslabón de Cadena de Custodia");
				}
			}
			
			$("#idAgregarEvidencia").click(abrePopupTipoEvidencia);
			
			//vamos por la fecha actual al servidor
			fechaServidor= consultaFechaHoraMaximaServer();
			fechaMax=getFechaMaximaServerHechos(fechaServidor);
			timeMax=getHoraMaximaServer(fechaServidor);
			
			//preseleccionamos las fechas
			//$("#txtCadCusFechaEntrega,#txtCadCusFechaRecepcion").val(fechaMax);
			
			$(function ()  { 
				$("#txtCadCusFechaEntrega").datepicker({
					dateFormat: 'dd/mm/yy',
					yearRange: '1900:2100',
					changeMonth: true,
					changeYear: true,
					showOn: "button",
					buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
					buttonImageOnly: true,	
					maxDate: fechaMax,
				    onSelect: function(date) {
						//setter fecha minima al segunda calendario
						$( "#txtCadCusFechaRecepcion" ).datepicker( "option", "minDate", date );
					}	
				});
			});
				
			
			$("#txtCadCusFechaRecepcion").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				minDate: fechaMax,
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#txtAddCadCusFechaLev").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				maxDate: fechaMax,
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			$("#txtAddCadCusFechaLevReg").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			$("#txtFechaRecepcionEslbn").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				minDate: fechaMax,
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true
			});
			
			$("#txtFechaEntregaEslbn").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true,
				maxDate: fechaMax,
			    onSelect: function(date) {
					//setter fecha minima al segunda calendario
					$( "#txtFechaRecepcionEslbn" ).datepicker( "option", "minDate", date );
				}
			});
			
			//Calendarios para el lapso de prestamos en la cadena de custodia
			$("#txtFechaPrestamoEslbn").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true,
				maxDate: fechaMax,
			    onSelect: function(date) {
					//setter fecha minima al segunda calendario
					$( "#txtFechaPrestamoRecEslbn" ).datepicker( "option", "minDate", date );
				}	
			});
			
			$("#txtFechaPrestamoRecEslbn").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				minDate: fechaMax,
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			
			$(".rdbCadCusRespEmb").bind("click",clickRdbResponsableEmbalaje);
			$(".rdbCadCusRespTras").bind("click",clickRdbResponsableTraslado);
			$(".rdbCadCusPerEnt").bind("click",clickRdbEntregaEvidencia);
			$(".rdbCadCusPerRec").bind("click",clickRdbRecibeEvidencia);
			$("#cbxAusentePresente").change(cambiaAusentePresente);
			$("#divPresente").hide();
			$("#divAusente").hide();
			$("#btnCadCusRespEmb,#btnCadCusRespTras,#btnCadCusPerEntrg").hide();
			
			$("btnSeleccionaEvidenciaCadCus").hide();
			$("#btnGuardaCadCustodia").click(asentarRegCadenaDeCustodia);
			
			$("#btnCapturarEvidenciaCadCus").click(muestraTRCapturaEvidencia);
			$("#btnSeleccionaEvidenciaCadCus").click(muestraTRSeleccionaEvidencia);
			
			$("#idAgregarEvidenciaReg").click(registrarEvidenciasSeleccionadas);
			
			jQuery("#gridCadCustodiaEvdncs").jqGrid({ 
				url:'local',
				datatype: "xml",
				colNames:['No.','NoHidden','Información', 'Fecha de levantamiento','Hora de levantamiento','Origen','Tipo de Objeto','IdObjeto','IdEvidencia'], 
				colModel:[ 	{name:'Numero',index:'numero', width:60},
				           	{name:'NumeroHidden',index:'numeroHidden', width:60,hidden:true}, 
							{name:'Informacion',index:'informacion', width:160}, 
							{name:'Fechal',index:'fechal',width:160},
							{name:'Horal',index:'horal',width:160},
							{name:'Origen',index:'origen',width:200},
							{name:'TipoObjeto',index:'tipoObjeto',hidden:true,width:100},
							{name:'IdObjeto',index:'idObjeto',hidden:true,width:100},
							{name:'IdEvidencia',index:'idEvidencia',hidden:true,width:100},
						],
				pager: jQuery('#pagerGridCadCustodiaEvdncs'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"EVIDENCIAS DE LA CADENA DE CUSTODIA",
				sortname: 'Evidencias',
				viewrecords: true,
				id: 'gridCadCustodiaEvdncs',
				ondblClickRow: function(){
						//obtenemos el tipo de objeto
						var rowd = jQuery("#gridCadCustodiaEvdncs").jqGrid('getGridParam','selrow');
						var retd = jQuery("#gridCadCustodiaEvdncs").jqGrid('getRowData',rowd);
						var posIni= retd.TipoObjeto.indexOf(">", 0);
						var posFin= retd.TipoObjeto.indexOf("<", posIni);
						var tipoObjeto=retd.TipoObjeto.substring(posIni+1,posFin);
						//ahora obtenemos el id del objeto
						posIni= retd.IdObjeto.indexOf(">", 0);
						posFin= retd.IdObjeto.indexOf("<", posIni);
						var idObjeto=retd.IdObjeto.substring(posIni+1,posFin);
						//ahora abriremos la pantalla que corresponde
						abreVentanaConsultaObjeto(tipoObjeto,idObjeto);
					},
				onSelectRow: function(){
						//obtenemos el ID del a evidencia y del objeto
						var row = jQuery("#gridCadCustodiaEvdncs").jqGrid('getGridParam','selrow');
						if (row) 
						{ 
							//obtenemos el ID de la evidencia
							var ret = jQuery("#gridCadCustodiaEvdncs").jqGrid('getRowData',row);
							var posIni=ret.IdEvidencia.indexOf(">");
							var posFin=ret.IdEvidencia.substring(1).indexOf("<");
							idEvdAElmnr=ret.IdEvidencia.substring(posIni+1,posFin);
							//obtenemos el ID del objeto
							ret = jQuery("#gridCadCustodiaEvdncs").jqGrid('getRowData',row);
							posIni=ret.IdObjeto.indexOf(">");
							posFin=ret.IdObjeto.substring(1).indexOf("<");
							idObjAElmnr=ret.IdObjeto.substring(posIni+1,posFin);
							//obtenemos el tipo del objeto
							ret = jQuery("#gridCadCustodiaEvdncs").jqGrid('getRowData',row);
							posIni=ret.TipoObjeto.indexOf(">");
							posFin=ret.TipoObjeto.substring(1).indexOf("<");
							tipoObjAElmnr=ret.TipoObjeto.substring(posIni+1,posFin);
						}
					},
				sortorder: "desc"
			});
			$("#gridCadCustodiaEvdncs").trigger("reloadGrid");
			
			jQuery("#gridCadCustodiaEvdncsRegs").jqGrid({ 
				url:'local',
				datatype: "xml",
				colNames:['Tipo','Información', ''], 
				colModel:[ 	{name:'Tipo',index:'tipo', width:160}, 
							{name:'Informacion',index:'informacion', width:160}, 
							{name:'Combo',index:'combo',width:30,align:'center'},
						],
				//pager: jQuery('#pagerGridCadCustodiaEvdncsRegs'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"OBJETOS",
				sortname: 'Objetos',
				viewrecords: true,
				id: 'gridCadCustodiaEvdncsRegs',
				onSelectRow: function(){
					//detEvi(id);
					},
				sortorder: "desc"
			});
			$("#gridCadCustodiaEvdncsRegs").trigger("reloadGrid");
			
			
			
			jQuery("#gridCadCustodiaEvdncsEslabon").jqGrid({ 
				url:'local',
				datatype: "xml",
				colNames:['','Evidencia No.','Evidenica No.H','Información', 'Fecha del levantamiento','Origen','Último Eslabón','No. Eslabón','Tipo Eslabón',''], 
				colModel:[ 	{name:'Check',index:'check', width:20,align:'center'},
				           	{name:'Numero',index:'numero', width:60},
				           	{name:'Tipo',index:'tipo', width:60,align:'center',hidden:true}, 
							{name:'Informacion',index:'informacion', width:160}, 
							{name:'Fecha',index:'fecha', width:160},
							{name:'Origen',index:'origen', width:160},
							{name:'UltimoEslabon',index:'ultimoEslabon', width:160},
							{name:'NoEslabon',index:'noEslabon', width:75},
							{name:'TipoEslabon',index:'tipoEslabon', width:100},
							{name:'Combo',index:'combo',width:30,align:'center'},
						],
				//pager: jQuery('#pagerGridCadCustodiaEvdncsEslabon'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"EVIDENCIAS DE LA CADENA",
				sortname: 'Evidencias',
				viewrecords: true,
				id: 'gridCadCustodiaEvdncsEslabon',
				onSelectRow: function(id){
					recupera(id);
					},
				sortorder: "desc"
			});
			$("#gridCadCustodiaEvdncsEslabon").trigger("reloadGrid");
			
			//Definimos el grid de las cadenas de custodia
			jQuery("#gridCadCustodiaDelExpediente").jqGrid({ 
				url:'local',
				datatype: "xml",
				colNames:['ID','Folio de cadena de custodia'], 
				colModel:[ {name:'ID',index:'id', width:100,align:'center',hidden:true},
				           {name:'CadenaCustodia',index:'cadenaCustodia', width:100,align:'center'}
						 ],
				pager: jQuery('#pagerGridCadCustodiaDelExpediente'),
				rowNum:10,
				rowList:[10,20,30],
				autowidth: true,
				caption:"CADENAS DE CUSTODIA",
				sortname: 'Cadenas',
				viewrecords: true,
				id: 'gridCadCustodiaDelExpediente',
				onSelectRow: function(id){
					cadenaCustodia=id;
					//lanzamos la consulta de la cadena de custodia
					consultaCadenaDeCustodia(cadenaCustodia);
				},
				sortorder: "desc"
			});
			$("#gridCadCustodiaDelExpediente").trigger("reloadGrid");
			
			$(".trCapturaEvidenciaCadCus").hide();
			$(".trSeleccionaEvidenciaCadCus").hide();
			//llenamos el catalogo de los tipos de eslabon
			cargaCatalogoTipoEslabon();
			
			$("#btnRegEslbnCadCus").click(registrarEslabonCadenaCustodia);
			$("#btnAddEslbnCadCus").click(habilitaRegistroEslabonCadCus);			
			
			//REVISAMOS si se trata de una consulta
			if(parseInt(consultaCadena)==1)
			{
				$(".tablaInsercion").hide();
				$(".tablaConsulta").show();
				
				consultaEvidenciasCadenaDeCustodia();
				$(".tablaInsercionEsp").show();
				$("#btnCapturarEvidenciaCadCus").hide();
				$("#btnSeleccionaEvidenciaCadCus").hide();
				$("#idAgregarEvidencia").hide();
				$("#btnRegEslbnCadCus").hide();
				$('#liPestanaUno').hide();
				$('#liPestanaDos').hide();
				$('#liPestanaTres').hide();
				$("#tabalaInsercionEslabones").hide();
				$("#btnRegEslbnCadCus").show();
				//mostranos la seccion del eslabon 
				$("#tablaEslabonesCadCustodia").hide();
				$("#btnAddEslbnCadCus").show();
				$("#btnRegEslbnCadCus").hide();
				//oculatmos las observaciones
				$("#tablaObsGeneralesEslbnCadCus").hide();
				
				//abrimos el popup con las cadenas de custodia del expediente
				dialigoCadenasCustodiaDelExpediente();
				//mostramos el boton de anular tanto en insercion como en consulta
				$("#idEliminarEvidencia").show();
				$("#trAnulaAgregaEvdncs").show();
				$("#idAgregarEvidencia").hide();
			}
			else
			{
				$(".tablaInsercion").show();
				$(".tablaConsulta").hide();
				$('#liEslabones').hide();
				$('#tabsconsultaprincipal-0').hide();
				$(".trCapturaEvidenciaCadCus").hide();
				$(".trSeleccionaEvidenciaCadCus").hide();
				$("#btnAddEslbnCadCus").hide();
				$("#tablaObsGeneralesEslbnCadCus").show();
			}
			//llenamos los campos de quien recibe
			llenaCamposQuienRecibe();
			$("btnSeleccionaEvidenciaCadCus").hide();
			$("btnSeleccionaEvidenciaCadCus").hide();
			
			cargaTiposObjetos();
		});
		
		/*
		* Funcion para habilitar la inserción de eslabones a una cadena de custodia consultada
		*/
		function habilitaRegistroEslabonCadCus()
		{
			$("#tabalaInsercionEslabones").show();
			$("#tablaEslabonesCadCustodia").hide();
			$("#btnRegEslbnCadCus").show();
			$("#btnAddEslbnCadCus").hide();
			$("#tablaObsGeneralesEslbnCadCus").show();
		}


		function llenaCamposQuienRecibe()
		{
			//hacemos la consulta del usuario que esta firmado
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultaFuncionarioFirmado.do',
				dataType: 'xml',
				success: function(xml){
						//revisamos la respuesta enviada
						if(parseInt($(xml).find('code').text())==0)
						{
							var bandera=$(xml).find('exito').find('bandera').text();
							if(bandera=='')
							{
								//removemos el funcionario en jefe
								var xmlEdit = $(xml);
								xmlEdit.find('funcionarioDTO').find('funcionarioJefe').remove();
								
								//si entra es xq todo fue exitoso
								$("#txtCadCusPerRecNombre,#txtCadCusPerRecApPat,#txtCadCusPerRecApMat").empty();
								$("#txtPersonaRecibeEslbn,#txtPersonaApPatRecibeEslbn,#txtPersonaApMatRecibeEslbn").empty();
								$("#txtCadCusPerRecNombre").val($(xml).find('nombreFuncionario').text());
								$("#txtCadCusPerRecApPat").val($(xml).find('apellidoPaternoFuncionario').text());
								$("#txtCadCusPerRecApMat").val($(xml).find('apellidoMaternoFuncionario').text());
								
								$("#txtPersonaRecibeEslbn").val($(xmlEdit).find('nombreFuncionario').text());
								$("#txtPersonaApPatRecibeEslbn").val($(xmlEdit).find('apellidoPaternoFuncionario').text());
								$("#txtPersonaApMatRecibeEslbn").val($(xmlEdit).find('apellidoMaternoFuncionario').text());
							}
						}
						else
						{
							alertDinamico("Ocurrió un error al recuperar su información");
						}
				}
			});
		}
		
	
		/*
		*Funcion para guardar objetos existentes en el expediente de la cadena actual,
		*seleccionandolas previamente en el grid de la consulta de los objetos que nose
		*encuentran relacionados a ninguna cadena del expediente
		*Extrae primero los IDs seleccionados y posteriormente se llama al action 
		*correspondiente para realizar el guardado en la BD
		*/
		function registrarEvidenciasSeleccionadas()
		{
			var fecha=$.trim($("#txtAddCadCusFechaLevReg").val());
			var hora=$.trim($("#txtAddCadCusHoraLevReg").val());
			var origen=$.trim($("#txtAddCadCusOrigenReg").val());

			if(fecha.length!=0 && hora.length!=0 && origen.length!=0)
			{
				//obtenemos los ID's de los renglones del Grid de las evidencias
				var arrayIDs = new Array() ;
				var idsChkSelected="";
				//obtenemos los checkbox del grif
				$('.chkObjNoAsentado').each(function(){
			        // identificamos los que esta seleccionados
			        if($(this).is(':checked'))
			        {//construimos la cadena con los IDs de los objetos
			        	if(idsChkSelected.length>0)
						{
			        		idsChkSelected=idsChkSelected+","+$(this).attr('id').split('_')[1];//arrayIDs[i];
						}
						else
						{
							idsChkSelected=""+$(this).attr('id').split('_')[1];//arrayIDs[i];
						}	
			        }
			    });
				//Revisamos que se seleccionaron objetos para ser guardados como evidencias en la cadena
				if(idsChkSelected.length>0)
				{
					var fechaLevCadCusReg=$("#txtAddCadCusFechaLevReg").val() + "-" +$("#txtAddCadCusHoraLevReg").val();
					var origenEvdCadCusReg=$("#txtAddCadCusOrigenReg").val();
					
					//mandaremos a guardar los nuevos objetos a la cadena de custodia actual
					var params="";
					//obtenemos ls datos de la vista
					params="ids="+idsChkSelected;
					params+="&idCadenacustodia="+cadenaCustodia;
					params+="&fecha="+fechaLevCadCusReg;
					params+="&origen="+origenEvdCadCusReg;
					params+="&numeroExpediente="+numeroExpediente;
					
					//mandamos a llamar al action que guardara la cadena de custodia
					$.ajax({
						async: false,
						type: 'POST',
						url: '<%= request.getContextPath()%>/guardarObjetoNoAsentadoEnCadCus.do',
						data: params,
						dataType: 'xml',
						success: function(xml){
								//revisamos la respuesta enviada
								if(parseInt($(xml).find('code').text())==0)
								{
									var bandera=$(xml).find('exito').find('bandera').text();
									if(parseInt(bandera)==0)
									{
										alertDinamico("Ocurrió un error al asentar las evidencias en la cadena de custodia");
									}
									else
									{
										alertDinamico("Se asentaron correctamente las evidencias en la cadena de custodia");
										consultaEvidenciasNoAsentadasEnCadena();
										consultaEvidenciasXCadenaDeCustodia();
									}
									
								}
								else
								{
									alertDinamico("Ocurrió un error al asentar las evidencias en la cadena de custodia");
								}
						}
					});
				}
				else
				{
					alertDinamico("Seleccione un objeto para guardarlo en la cadena de custodia");
				}
			}
			else
			{
				//falta algun dato de las evidencias
				alertDinamico("Los 3 campos son obligatorios");
			}
		}
		
		/*
		*Funcion que muestra las secciones para agregar nuevas evidencias
		*y darlas de alta a la cadena de custodia
		*/
		function muestraTRCapturaEvidencia()
		{
			$(".trCapturaEvidenciaCadCus").show();
			$(".trSeleccionaEvidenciaCadCus").hide();
		}
		
		/*
		*Funcion que muestra las secciones para seleccionar evidencias
		*para darlas de alta a la cadena de custodia
		*/
		function muestraTRSeleccionaEvidencia()
		{
			$(".trCapturaEvidenciaCadCus").hide();
			$(".trSeleccionaEvidenciaCadCus").show();
			consultaEvidenciasNoAsentadasEnCadena();
		}
		
		/*
		*Funcion para recargar el grid de las evidencias que corresponden a la cadena de custodia
		*para el tab del eslabon de la cadena
		*/
		function consultaEvidenciasXCadenaDeCustodia()
		{
			if(cadenaCustodia!=null && cadenaCustodia!="")
			{
				jQuery("#gridCadCustodiaEvdncsEslabon").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/ConsultaEvidenciasXCadenaCustodia.do?folioCadCustodia='+cadenaCustodia+'',datatype: "xml" });
				$("#gridCadCustodiaEvdncsEslabon").trigger("reloadGrid");
			}
		}
		
		/*
		*Funcion para consultar las evidencias recien ingresadas durante la creación de la 
		*cadena de custodia
		*/
		function consultaEvidenciasCadenaDeCustodia()
		{
			if(cadenaCustodia!=null && cadenaCustodia!="")
			{
				jQuery("#gridCadCustodiaEvdncs").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/ConsultaEvidenciasCadenaCustodiaPorExpediente.do?numeroExpediente='+numeroExpediente+'&folioCadCustodia='+cadenaCustodia+'',datatype: "xml" });
				$("#gridCadCustodiaEvdncs").trigger("reloadGrid");
			}
		}
		
		/*
		*Funcion para consutlar los obejtos que pertencen al expediente de la actual cadena
		* de custodia pero que no estan asiganadas a ninguna cade al expediente
		*/
		function consultaEvidenciasNoAsentadasEnCadena()
		{
			if(cadenaCustodia!=null && cadenaCustodia!="")
			{
				jQuery("#gridCadCustodiaEvdncsRegs").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/ConsultaEvidenciasCadenaCustodiaPorExpedienteNoAsentadas.do?numeroExpediente='+numeroExpediente+'',datatype: "xml" });
				$("#gridCadCustodiaEvdncsRegs").trigger("reloadGrid");
			}
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una arma como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaArma(id,tipoArma)
		{
			window.parent.cargaArma(id,tipoArma);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un equipo telefonico como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaEquipoTelefonico(id,tipoTelefono)
		{
			window.parent.cargaEquipoTelefonico(id,tipoTelefono);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un vehiculo como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaVehiculo(id,tipoVehiculo,placas)
		{
			//pintamos el nuevo obejto en el visor principal
			window.parent.cargaVehiculo(id,tipoVehiculo,placas);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un explosivo como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaExplosivo(id,tipoExplosivo)
		{
			//pintamos el nuevo obejto en el visor principal
			window.parent.cargaExplosivo(id,tipoExplosivo);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una sustancia como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaSustancia(id,tipoSustancia)
		{
			window.parent.cargaSustancia(id,tipoSustancia);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un animal como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaAnimal(id,tipoAnimal)
		{
			window.parent.cargaAnimal(id,tipoAnimal);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una obra de arte como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaObraDeArte(id,tipoObraArte)
		{
			window.parent.cargaObraDeArte(id,tipoObraArte);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una joya como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaJoya(id,tipoJoya)
		{
			window.parent.cargaJoya(id,tipoJoya);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un documento oficial como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaDocOfic(id,tipoDocOfic)
		{
			window.parent.cargaDocOfic(id,tipoDocOfic);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un vegetal como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaVegetal(id,tipoVegetal)
		{
			window.parent.cargaVegetal(id,tipoVegetal);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un numerario como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaNumerario(id,tipoNumerario)
		{
			window.parent.cargaNumerario(id,tipoNumerario);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una aeronave como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaAeronave(id,tipoAeronave)
		{
			window.parent.cargaAeronave(id,tipoAeronave);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una Embarcacion como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaEmbarcacion(id,tipoEmbarcacion)
		{
			window.parent.cargaEmbarcacion(id,tipoEmbarcacion);
			cargaObjetoGenerico();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta una Embarcacion como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaOtros(id,nombre)
		{
			window.parent.cargaOtros(id,nombre);
			cargaObjetoGenerico();
		}
		
		function cargaObjetoGenerico()
		{
			//habilitamos de nuevo los campos para dar de alta una nueva evidencia
			$("#idAgregarEvidencia").attr("disabled","");
			$("#txtAddCadCusFechaLev,#txtAddCadCusOrigen").val("");
			$('#cbxTipoEvidencia').find("option[value='-1']").attr("selected","selected");
			var pantalla ="iframewindowIngresarEvidencia";
			pantalla += idWindowObEvidencia;
			$.closeWindow(pantalla);
			
			//mandamos a hacer la consulta de las evidencias para agregar al grid la nueva evidencia
			consultaEvidenciasCadenaDeCustodia();
			consultaEvidenciasXCadenaDeCustodia();
		}
		
		/*
		*Funcion que se ejecuta despues de dar de alta un equipo de computo como 
		* evidencia para la cadena de custodia actual
		*/
		function cargaEquipoComputo(id,tipoEquipo)
		{
			window.parent.cargaEquipoComputo(id,tipoEquipo);
			cargaObjetoGenerico();
		}		
		
		
		function registrarEslabonCadenaCustodia()
		{
			if(validaCamposRegEslabonDeCadena())
			{
				var parametros=recuperaParametrosRegEslabonCadCus();
				
				//mandamos a llamar al action que guardara el eslabon de la cadena de custodia
				$.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/guardarEslabonCadenaDeCustodia.do',
					data: parametros,
					dataType: 'xml',
					success: function(xml){
							
							if(parseInt($(xml).find('code').text())==0)
							{
								//$("#btnGuardaCadCustodia").attr("disabled","disabled")
								//cadenaCustodia=$(xml).find('custodiaDTO').find('cadenaDeCustodiaId').text();
								//$("#spanFolioCadCus").html($(xml).find('custodiaDTO').find('folio').text());
								
								var bandera=$(xml).find('exito').find('bandera').text();
								if(parseInt(bandera)==0)
								{
									alertDinamico("Ocurrió un error al guardar el eslabón de la cadena de custodia");
								}
								else
								{
									limpiaCamposEslabonCadCus();
									if(parseInt(consultaCadena)==1)
									{
										$("#btnAddEslbnCadCus").show();
										$("#btnRegEslbnCadCus").hide();
										$("#tabalaInsercionEslabones").hide();
										$("#tablaEslabonesCadCustodia").hide();
										$("#tablaObsGeneralesEslbnCadCus").hide();
									}
									alertDinamico("Se guardó correctamente el eslabón de la cadena de custodia");
								}
								
							}
							else
							{
								cadenaCustodia="";
								alertDinamico("Ocurrió un error al guardar el eslabón de la cadena de custodia");
							}
					}
				});
			}
		}
		
		
		/*
		*Funcion para validar los datos obligatorios para ingresar una cadena de custodia
		*/
		function validaCamposCadenaDeCustodia()
		{
			
			if($.trim($("#txtCadCusPerEntNombre").val()).length==0)
			{
				alertDinamico("Debe proporcionar el nombre de la persona que entrega");
				return false;
			}
			if($.trim($("#txtCadCusPerEntApPat").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido paterno de la persona que entrega");
				return false;
			}
			if($.trim($("#txtCadCusPerEntApMat").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido materno de la persona que entrega");
				return false;
			}
			if($.trim($("#txtCadCusPerRecNombre").val()).length==0)
			{
				alertDinamico("Debe proporcionar el nombre de la persona que recibe");
				return false;
			}
			if($.trim($("#txtCadCusPerRecApPat").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido paterno de la persona que recibe");
				return false;
			}
			if($.trim($("#txtCadCusPerRecApMat").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido materno de la persona que recibe");
				return false;
			}
			
			if($.trim($("#txtCadCusInsTras").val()).length==0)
			{
				alertDinamico("Debe proporcionar la institución del persona que traslada");
				return false;
			}
			if($.trim($("#txtCadCusEmbTras").val()).length==0)
			{
				alertDinamico("Debe proporcionar la institución del persona que embala");
				return false;
			}
			return true;
		}
		
		
		function validaCamposRegEslabonDeCadena()
		{
			var i=0;
			//obtenemos los checkbox del grif
			$('.chkObjEvdXCadCus').each(function(){
		        // identificamos los que esta seleccionados
		        if($(this).is(':checked'))
		        {//construimos la cadena con los IDs de los objetos
		        	i++;
		        }
		    });
			if(i==0)
			{
				alertDinamico("Debe seleccionar al menos una evidencia para guardar un eslabón");
				return false;
			}
			//abriremos el popup del tipo de objeto seleccionado 
			var tipoEslabon= $('#cbxTipoEslabon option:selected').val();
			//revisamos la opcion seleccionada
			if(parseInt(tipoEslabon)==-1)
			{
				alertDinamico("Debe seleccionar el tipo de eslabón de entrega para poder continuar");
				return false;
			}
			//revisamos el nombre de la persona que entrega
			if($.trim($("#txtPersonaEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el nombre de la persona que entrega");
				return false;
			}						
			
			/* //revismaos el apellido paterno de quien recibe
			if($.trim($("#txtPersonaApPatEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido paterno de la persona que entrega");
				return false;
			}
			
        	//revismaos el apellido materno de quien recibe
			if($.trim($("#txtPersonaApMatEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido materno de la persona que entrega");
				return false;
			} */
			
			//revisamos la fecha de entrega
			if($.trim($("#txtFechaEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la fecha de la entrega");
				return false;
			}
			//revismaos la hora de entrega
			if($.trim($("#txtHoraEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la hora de la entrega");
				return false;
			}
			//revisamos la institucon de entrega
			if($.trim($("#txtInstitucionEslbnEntrega").val()).length==0)
        	{
				alertDinamico("Debe proporcionar la institución de entrega");
				return false;
        	}
			//revismaos el nombre de quien recibe
			if($.trim($("#txtPersonaRecibeEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el nombre de la persona que recibe");
				return false;
			}
        	
        	/* //revismaos el apellido paterno de quien recibe
			if($.trim($("#txtPersonaApPatRecibeEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido paterno de la persona que recibe");
				return false;
			}
			
        	//revismaos el apellido materno de quien recibe
			if($.trim($("#txtPersonaApMatRecibeEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar el apellido materno de la persona que recibe");
				return false;
			} */
        	
			//revismaos la fecha de recepcion
			if($.trim($("#txtFechaRecepcionEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la fecha de la recepción");
				return false;
			}
			//revisamos la hora de recpcion
			if($.trim($("#txtHoraRecepcionEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la hora de la recepción");
				return false;
			}
			//revisamos la institucon de recepcion
			if($.trim($("#txtInstitucionEslbnRecepcion").val()).length==0)
        	{
				alertDinamico("Debe proporcionar la institución de recepción");
				return false;
        	}
			
			//Validaciones para los campos de fecha de prestamo
			//revisamos la fecha de inicio del periodo de prestamo
			if($.trim($("#txtFechaPrestamoEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la fecha de inicio del periodo de préstamo");
				return false;
			}
			//revisamos la hora de inicio del periodo de prestamo
			if($.trim($("#txtHoraEntregaEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la hora de inicio del periodo de préstamo");
				return false;
			}
			//revisamos la fecha de fin del periodo de prestamo
			if($.trim($("#txtFechaPrestamoRecEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la fecha final del periodo de préstamo");
				return false;
			}
			//revisamos la hora de fin del periodo de prestamo
			if($.trim($("#txtHoraPrestamoRepEslbn").val()).length==0)
			{
				alertDinamico("Debe proporcionar la hora del final del periodo de préstamo");
				return false;
			}
			return true;
		}
		/*
		*Funcion para consumir las pantallas de insercion de objetos
		*para pdoer dar de alta una evidencia nueva en la cadena de custodia actual
		*/
		function abrePopupTipoEvidencia()
		{
			if(cadenaCustodia=="")
			{
				alertDinamico("Debe guardar una cadena de custodia antes de poder ingresar evidencias");
				return;
			}
			
			//abriremos el popup del tipo de objeto seleccionado 
			var tipoObjeto= $('#cbxTipoEvidencia option:selected').val();
			
			if(parseInt(tipoObjeto)==-1)
			{
				alertDinamico("Debe seleccionar el tipo de evidencia");
				return;
			}
			if($.trim($("#txtAddCadCusFechaLev").val()).length==0)
			{
				alertDinamico("Debe proporcionar la fecha de levantamiento de la evidencia");
				return;
			}
			if($.trim($("#txtAddCadCusOrigen").val()).length==0)
			{
				alertDinamico("Debe proporcionar el origen de la evidencia");
				return;
			}
			
			
			//obtenemos los datos que se almacenaran para la evidencia
			var fechaLevCadCus=$("#txtAddCadCusFechaLev").val() + "-" +$("#txtAddCadCusHoraLev").val();
			var origenEvdCadCus=$("#txtAddCadCusOrigen").val();
			idWindowObEvidencia++;
			//$("#idAgregarEvidencia").attr("disabled","disabled");
			//Revisamos el tipo de obejto seleccionado en el combo y lanzamos su pantalla de captura
			if(parseInt(tipoObjeto)=='<%= Objetos.VEHICULO.getValorId() %>')//Vehiculo
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Ingresar veh&iacute;culo", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=VEHICULO&idVehiculo=0&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="570" height="600" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>')//Equipo de Cómputo
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Ingresar equipo de c&oacute;mputo", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente +'&idEquipoComputo=0&tipoObjeto=EQUIPO_COMPUTO&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+'" width="830" height="340" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.EQUIPO_TELEFONICO.getValorId() %>')//Equipo Telefónico
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Ingresar equipo telef&oacute;nico", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico=0&tipoObjeto=EQUIPO_TELEFONICO&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="870" height="340" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.ARMA.getValorId() %>')//Arma
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Ingresar arma", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma=0&tipoObjeto=ARMA&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="800" height="380" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.EXPLOSIVO.getValorId() %>')//Explosivo
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Ingresar explosivo", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente+'&idExplosivo=0&tipoObjeto=EXPLOSIVO&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="880" height="330" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.SUSTANCIA.getValorId() %>')//Sustancia
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar sustancia", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia=0&tipoObjeto=SUSTANCIA&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="900" height="330" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.ANIMAL.getValorId() %>')//Animal
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Ingresar animal", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'&idAnimal=0&tipoObjeto=ANIMAL&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="900" height="330" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.AERONAVE.getValorId() %>')//Aeronave
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar aeronave", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=AERONAVE&idAeronave=0&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="600" height="530" />');			
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.EMBARCACION.getValorId() %>')//Embarcación
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Ingresar embarcaci&oacute;n", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=EMBARCACION&idEmbarcacion=0&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="600" height="530" />');			
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.NUMERARIO.getValorId() %>')//Numerario
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar numerario", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente+'&idNumerario=0&tipoObjeto=NUMERARIO&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="800" height="350" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.VEGETAL.getValorId() %>')//Vegetal
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar vegetal", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente+'&idVegetal=0&tipoObjeto=VEGETAL&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="800" height="350" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>')//Documento Oficial
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Ingresar documento oficial", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente+'&idDocOfic=0&tipoObjeto=DOCUMENTO_OFICIAL&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="800" height="350" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.JOYA.getValorId() %>')//Joya
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar joya", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente+'&idJoya=0&tipoObjeto=OBRA_DE_ARTE&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="850" height="380" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.OBRA_DE_ARTE.getValorId() %>')//Obra de Arte
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Ingresar obra de arte", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'&idObraArte=0&tipoObjeto=OBRA_DE_ARTE&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="850" height="380" />');
			}
			else if(parseInt(tipoObjeto)=='<%= Objetos.OTRO.getValorId() %>')//Otros
			{
				$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:450,title:"Ingresar otros", type:"iframe",modal: true});
			    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarOtros.do?numeroExpediente='+numeroExpediente +'&idOtros=0&tipoObjeto=OTRO&cadenaCustodia='+cadenaCustodia+'&fechaLevCadCus='+fechaLevCadCus+'&origenEvdCadCus='+origenEvdCadCus+' " width="800" height="450" />');
			}
		}
		
		/*
		*Action para mandar guardar a la BD una nueva cade de custodia
		*/
		function asentarRegCadenaDeCustodia()
		{
			var params="";
			//obtenemos ls datos de la vista
			params=extraeDatosCadenaDeCustodia();
			params+= "&numeroExpediente="+numeroExpediente;
			if(validaCamposCadenaDeCustodia())
			{
				//mandamos a llamar al action que guardara la cadena de custodia
				$.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/guardarRegCadenaDeCustodia.do',
					data: params,
					dataType: 'xml',
					success: function(xml){
							
							if(parseInt($(xml).find('code').text())==0)
							{
								$("#btnGuardaCadCustodia").attr("disabled","disabled");
								cadenaCustodia=$(xml).find('custodiaDTO').find('cadenaDeCustodiaId').text();
								$("#spanFolioCadCus").html($(xml).find('custodiaDTO').find('folio').text());
								folioCadenaCustodia=$(xml).find('custodiaDTO').find('folio').text();
								alertDinamico("Se guardó correctamente la cadena de custodia");
							}
							else
							{
								cadenaCustodia="";
								alertDinamico("Ocurrió un error al guardar la cadena de custodia");
							}
					}
				});
			}
		}
		
		/*
		*Funcion que extrae los datos necesaris para poder dar de alta un nueca cadena
		*de custodia
		*/
		function extraeDatosCadenaDeCustodia()
		{
			var parametros="";
			parametros += "quienEmbala=";
			parametros += $("#txtCadCusRespEmbNombre").val()+" "+$("#txtCadCusRespEmbApPat").val() +" " +$("#txtCadCusRespEmbApMat").val();
			parametros += "&fechaIntercambio="+$("#txtCadCusFechaRecepcion").val();
			parametros += "&horaIntercambio="+$("#txtCadCusHoraRecepcion").val();
			parametros += "&quienEntrega=";
			parametros += $("#txtCadCusPerEntNombre").val()+" "+$("#txtCadCusPerEntApPat").val() +" " +$("#txtCadCusPerEntApMat").val();
			parametros += "&quienRecibe=";
			parametros += $("#txtCadCusPerRecNombre").val()+" "+$("#txtCadCusPerRecApPat").val() +" " +$("#txtCadCusPerRecApMat").val();
			parametros += "&quienTransporta=";
			parametros += $("#txtCadCusRespTrasNombre").val()+" "+$("#txtCadCusRespTrasApPat").val() +" " +$("#txtCadCusRespTrasApMat").val();
			parametros += "&fechaLevantamiento="+$("#txtCadCusFechaEntrega").val();
			parametros += "&horaLevantamiento="+$("#txtCadCusHoraEntrega").val();
			parametros += "&institutionTraslada="+$("#txtCadCusInsTras").val();
			parametros += "&institutionEmbala="+$("#txtCadCusEmbTras").val();
			
			return parametros;
		}
		
		function cambiaAusentePresente()
		{
			var opcion=$("#cbxAusentePresente option:selected").val();
			
			if(parseInt(opcion)==-1)
			{
				$("#divPresente").hide();
				$("#divAusente").hide();
			}
			else if(parseInt(opcion)==0)
			{
				$("#divPresente").show();
				$("#divAusente").hide();
			}
			else if(parseInt(opcion)==1)
			{
				$("#divPresente").hide();
				$("#divAusente").show();
			}
		}
		
		function clickRdbResponsableEmbalaje()
		{
			var tipoRespEmbalaje= $(':radio[name=rdbCadCusRespEmb]:checked').val();
			if(parseInt(tipoRespEmbalaje)==0)
			{//interno
				habilitadeshabilitaCampos(1,0);	
			}
			else
			{//externo
				habilitadeshabilitaCampos(1,1);	
			}
		}
		
		function clickRdbResponsableTraslado()
		{
			var tipoRespTraslado= $(':radio[name=rdbCadCusRespTras]:checked').val();
			if(parseInt(tipoRespTraslado)==0)
			{//interno
				habilitadeshabilitaCampos(2,0);	
			}
			else
			{//externo
				habilitadeshabilitaCampos(2,1);	
			}
		}
		
		function clickRdbEntregaEvidencia()
		{
			var tipoRespEntEvd= $(':radio[name=rdbCadCusPerEnt]:checked').val();
			if(parseInt(tipoRespEntEvd)==0)
			{//interno
				habilitadeshabilitaCampos(3,0);	
			}
			else
			{//externo
				habilitadeshabilitaCampos(3,1);	
			}
		}
		
		function clickRdbRecibeEvidencia()
		{
			var tipoRespRecEnt= $(':radio[name=rdbCadCusPerRec]:checked').val();
			if(parseInt(tipoRespRecEnt)==0)
			{//interno
				habilitadeshabilitaCampos(4,0);	
			}
			else
			{//externo
				habilitadeshabilitaCampos(4,1);	
			}
		}
		
		function habilitadeshabilitaCampos(idDivResponsables,bandera)
		{
			if(parseInt(idDivResponsables)==1)
			{
				$("#txtCadCusRespEmbNombre,#txtCadCusRespEmbApPat,#txtCadCusRespEmbApMat,#txtCadCusEmbTras").val('');
				if(parseInt(bandera)==0)
				{//deshabilita los campos de responsable del responsable de embalaje
					$("#txtCadCusRespEmbNombre,#txtCadCusRespEmbApPat,#txtCadCusRespEmbApMat,#txtCadCusEmbTras").attr('disabled','disabled');
					$("#btnCadCusRespEmb").show();
				}
				else
				{
					//habilita los campos de responsable del responsable de embalaje
					$("#txtCadCusRespEmbNombre,#txtCadCusRespEmbApPat,#txtCadCusRespEmbApMat,#txtCadCusEmbTras").attr('disabled','');
					$("#btnCadCusRespEmb").hide();
				}
			}
			else if(parseInt(idDivResponsables)==2)
			{
				$("#txtCadCusRespTrasNombre,#txtCadCusRespTrasApPat,#txtCadCusRespTrasApMat,#txtCadCusInsTras").val('');	
				if(parseInt(bandera)==0)
				{//deshabilita los campos de responsable del traslado
					$("#txtCadCusRespTrasNombre,#txtCadCusRespTrasApPat,#txtCadCusRespTrasApMat,#txtCadCusInsTras").attr('disabled','disabled');
					$("#btnCadCusRespTras").show();
				}
				else
				{
					//habilita los campos de responsable del traslado
					$("#txtCadCusRespTrasNombre,#txtCadCusRespTrasApPat,#txtCadCusRespTrasApMat,#txtCadCusInsTras").attr('disabled','');
					$("#btnCadCusRespTras").hide();
				}
			}
			else if(parseInt(idDivResponsables)==3)
			{
				$("#txtCadCusPerEntNombre,#txtCadCusPerEntApPat,#txtCadCusPerEntApMat").val('');	
				if(parseInt(bandera)==0)
				{//deshabilita los campos de quien entrega la evidencia
					$("#txtCadCusPerEntNombre,#txtCadCusPerEntApPat,#txtCadCusPerEntApMat").attr('disabled','disabled');
					$("#btnCadCusPerEntrg").show();
				}
				else
				{
					//habilita los campos de quien entrega la evidencia
					$("#txtCadCusPerEntNombre,#txtCadCusPerEntApPat,#txtCadCusPerEntApMat").attr('disabled','');
					$("#btnCadCusPerEntrg").hide();
				}
			}
		}
		
	    function customRange(input) {
	  	  return {minTime: (input.id == 'txtCadCusHoraRecepcion' ?
	  		$('#idHoraDateLapsoInicio').timeEntry('getTime') : null),
	  		maxTime: (input.id == 'idHoraDateLapsoInicio' ?
	  		$('#txtCadCusHoraRecepcion').timeEntry('getTime') : null)};
	      }
	      $(function(){
	        $('.timeRange').timeEntry({beforeShow: '',timeSteps:[1,1,0],ampmPrefix: ' '});
	      });
	      
	      function abrePopupSystemUser(idSeccion)
	      {
	    	//generamos el Dialogo
				$( "#dialog-confirm" ).dialog({
					resizable: false,
					height:220,
					width:300,
					modal: true,
					buttons: {
						"Aceptar": function() {
							$( this ).dialog( "close" );
							$( "#dialog:ui-dialog" ).dialog( "destroy" );
							if($("#cbxAusentePresente option:selected").val()!=-1)
							{
								if($("#cbxAusentePresente option:selected").val()==0)
								{
									recuperaUsuarioInterno("presente",idSeccion);
								}
								else if($("#cbxAusentePresente option:selected").val()==1)
								{
									recuperaUsuarioInterno("ausente",idSeccion);
								}
								inicializaCamposPopupUsers();
							}
							else
							{
								alertDinamico("Debe seleccionar un tipo de responsable");
								inicializaCamposPopupUsers();
							}
						},
						"Cancelar": function() {
							inicializaCamposPopupUsers();
							$( this ).dialog( "close" );
							$( "#dialog:ui-dialog" ).dialog( "destroy" );
						}
					}
				});
				$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
	      }
	      
	      function recuperaUsuarioInterno(opcion,idSeccion)
	      {
	    	  if(opcion=='presente')
	    	  {
	    		  var usuarioP=$("#txtUsuario").val();
	    		  var pwdP=$("#txtPwdUsuario").val();
	    		  //llamamo el do que recupera el usuario interno presente
	    		  $.ajax({
	  				async: false,
	  				type: 'POST',
	  				url: '<%= request.getContextPath()%>/consultarCadCusUsuarioPresente.do?usuario='+usuarioP+'&pwd='+pwdP+'',
	  				dataType: 'xml',
	  				success: function(xml){
	  					if($(xml).find('bandera').text()=="1")
	  					{
	  							seteaDatosUsusarioInt($(xml),idSeccion);
	  					}
	  					else
	  					{
	  						alertDinamico("No se encontró el usuario");
	  					}
	  				}
	  			});	  
	    	  }
	    	  else
	    	  {
	    		//llamamo el do que recupera el usuario interno ausente
	    		  var noEmp=$("#txtNumUsuario").val();
	    		  $.ajax({
	  				async: false,
	  				type: 'POST',
	  				url: '<%= request.getContextPath()%>/consultarCadCusUsuarioNoPresente.do?numeroEmpleado='+noEmp+'',
	  				dataType: 'xml',
	  				success: function(xml){
	  					if($(xml).find('bandera').text()=="1")
	  					{
	  						seteaDatosUsusarioInt($(xml),idSeccion);	
	  					}
	  					else
	  					{
	  						alertDinamico("No se encontró el usuario");
	  					}
	  				}
	  			});
	    	  }
	      }
	      
	     /*
	     *Funcion para inicializar los campos del popup de seleccion de usuario interno:
	     * Resente o NO presente
	     */
	     function inicializaCamposPopupUsers()
	     {
	    	 $("#txtUsuario,#txtPwdUsuario,#txtNumUsuario").val("");
	    	 $('#cbxAusentePresente').find("option[value='-1']").attr("selected","selected");
	    	 $("#divAusente,#divPresente").hide();
	     }
	      
	     	     
	     /*
	     * Función para pintar los datos extraidos de la consulta de los usuarios:
	     *Presentes o NO presentes
	     */
	     function seteaDatosUsusarioInt(xml,idSeccion)
	     {
	 		
			//removemos el funcionario en jefe
			var xmlEdit = $(xml);
			xmlEdit.find('funcionarioJefe').remove();
			xml=$(xmlEdit);
					
			 var nombre=$(xml).find('nombreFuncionario').text();
	    	 var apPAterno=$(xml).find('apellidoPaternoFuncionario').text();
	    	 var apMaterno=$(xml).find('apellidoMaternoFuncionario').text();
	    	 var institucion=$(xml).find('institucion').find('nombre').text();
	    	 if(institucion==null || institucion=='')
	    	 {
	    		 institucion=$(xml).find('institucion').find('nombreInst').text();
	    	 }
	    	 
	    	 if(idSeccion==1)
	    	 {//Responsable del embalaje
		    	 $("#txtCadCusRespEmbNombre").val(nombre);
		    	 $("#txtCadCusRespEmbApPat").val(apPAterno);
		    	 $("#txtCadCusRespEmbApMat").val(apMaterno);
		    	 $("#txtCadCusEmbTras").val(institucion);
	    	 }
	    	 else if(idSeccion==2)
	     	 {//responsable del traslado
	    		 $("#txtCadCusRespTrasNombre").val(nombre);
	    		 $("#txtCadCusRespTrasApPat").val(apPAterno);
	    		 $("#txtCadCusRespTrasApMat").val(apMaterno);
	    		 $("#txtCadCusInsTras").val(institucion);
	     	 }
	    	 else
	     	 {//responsable de la entrega
	    		 $("#txtCadCusPerEntNombre").val(nombre);
	    		 $("#txtCadCusPerEntApPat").val(apPAterno);
	    		 $("#txtCadCusPerEntApMat").val(apMaterno);
	     	 }
	     }
	     
	     function cargaCatalogoTipoEslabon()
	     {
	    	$('#cbxTipoEslabon').empty();
	    	$('#cbxTipoEslabon').append('<option value="-1" selected="selected">-Seleccione-</option>');
	    	$('#cbxTipoEslabonR').empty();
	    	$('#cbxTipoEslabonR').append('<option value="-1" selected="selected">-Seleccione-</option>');
	 		$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarCatalogoTiposEslabon.do',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('catEslabon').each(function(){
						$('#cbxTipoEslabon').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						$('#cbxTipoEslabonR').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						});
				}
			});
	     }
	     
			function recuperaParametrosRegEslabonCadCus()
			{
				var params="";
				//datos de entrega
				params+="tipoEslabon="+$('#cbxTipoEslabon option:selected').val();
				
				
				var idsChkSelected="";
				//obtenemos los checkbox del grif
				$('.chkObjEvdXCadCus').each(function(){
			        // identificamos los que esta seleccionados
			        if($(this).is(':checked'))
			        {//construimos la cadena con los IDs de los objetos
			        	if(idsChkSelected.length>0)
						{
			        		idsChkSelected=idsChkSelected+","+$(this).attr('id').split('_')[1];//arrayIDs[i];
						}
						else
						{
							idsChkSelected=""+$(this).attr('id').split('_')[1];//arrayIDs[i];
						}	
			        }
			    });
				params+="&idsEvidencias="+idsChkSelected;
				params+="&nombrePersonaEntrega="+$("#txtPersonaEntregaEslbn").val()+"- "+ $("#txtPersonaApPatEntregaEslbn").val() +"- "+ $("#txtPersonaApMatEntregaEslbn").val();
				params+="&fechaEntrega="+$("#txtFechaEntregaEslbn").val();
				params+="&horaEntrega="+$("#txtHoraEntregaEslbn").val();
				if($.trim($("#txtObsEntregaEslbn").val()).length>0)
				{
					params+="&obsEntrega="+$("#txtObsEntregaEslbn").val();
				}
				
				if($.trim($("#txtInstitucionEslbnEntrega").val()).length>0)
	        	{
					params+="&institucionEnt="+$("#txtInstitucionEslbnEntrega").val();
	        	}
				//datos de recepcion 
				params+="&tipoEslabonRec="+$('#cbxTipoEslabon option:selected').val();
				params+="&nombrePersonaRecepcion="+$("#txtPersonaRecibeEslbn").val()+"-"+ $("#txtPersonaApPatRecibeEslbn").val() +"-"+ $("#txtPersonaApMatRecibeEslbn").val();
				params+="&fechaRecepcion="+$("#txtFechaRecepcionEslbn").val();
				params+="&horaRecepcion="+$("#txtHoraRecepcionEslbn").val();
				if($.trim($("#txtObsRecepcionEslbn").val()).length>0)
				{
					params+="&obsRecepcion="+$("#txtObsRecepcionEslbn").val();
				}
				if($.trim($("#txtInstitucionEslbnRecepcion").val()).length>0)
	        	{
					params+="&institucionRec="+$("#txtInstitucionEslbnRecepcion").val();
	        	}
				
				//params para el lapso de prestamo
				params+="&fechaIniPres="+$("#txtFechaPrestamoEslbn").val();
				params+="&horaIniPres="+$("#txtHoraPrestamoEslbn").val();
				params+="&fechaFinPres="+$("#txtFechaPrestamoRecEslbn").val();
				params+="&horaFinPres="+$("#txtHoraPrestamoRepEslbn").val();
								
				return params;
			}
			
			function dialigoCadenasCustodiaDelExpediente(){
				//llenamos el grid con las cadenas de custodia
				jQuery("#gridCadCustodiaDelExpediente").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarCadenasCustodiaDelExp.do?numeroExpediente='+numeroExpediente+'',datatype: "xml" });
				$("#gridCadCustodiaDelExpediente").trigger("reloadGrid");
			}
			
			function consultaCadenaDeCustodia(idCadenaCustodia)
			{
				//método Ajax para consultar la cadena de custodia
				params="idCadena="+idCadenaCustodia;
				//mandamos a llamar al action que guardara la cadena de custodia
				$.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/consultarCadenaDeCustodiaPorId.do',
					data: params,
					dataType: 'xml',
					success: function(xml){
							
						if(parseInt($(xml).find('code').text())==0)
						{
							seteaDatosConsultaCadenaDeCustodia(xml);
							$('#liPestanaUno').show();
							$('#liPestanaDos').show();
							$('#liPestanaTres').show();
						}
						else
						{
							alertDinamico("Ocurrió un error al consultar la cadena de custodia");
						}
					}
				});
				//consultamos la cadena de custodia si el folio es distinto de null
				if($("#spanFolioCadCus").html()!="")
				{
					consultaEvidenciasCadenaDeCustodia();//consulta las evidencias que se dan de alta en la cadena
					consultaEvidenciasXCadenaDeCustodia();//consultas las evidencias de la cadena para los eslabones
				}
			}
			
			/*
			*Funcion para pintar los datos de la consulta de una cadena de custodia
			*/
			function seteaDatosConsultaCadenaDeCustodia(xml)
			{
				$("#txtCadCusRespEmbNombreC").val($(xml).find('quienEmbala').text());
				$("#txtCadCusEmbTrasC").val($(xml).find('insQuienEmbala').text());
				
				var fechaOL=$(xml).find('fechaLevantamiento').text().split(" ");
				var fechaL=fechaOL[0];
				var horaL=fechaOL[1];
				var digitosFL=fechaL.split("-");
								
				$("#txtCadCusFechaEntregaC").val(digitosFL[2]+"/"+digitosFL[1]+"/"+digitosFL[0]);
				$("#txtCadCusHoraEntregaC").val(horaL.substring(0,5));
				
				$("#txtCadCusRespTrasNombreC").val($(xml).find('quienTransporta').text());		
				$("#txtCadCusInsTrasC").val($(xml).find('insQuienTras').text());
				$("#txtCadCusPerEntNombreC").val($(xml).find('quienEntrega').first().text());
				
				$("#txtCadCusPerRecNombreC").val($(xml).find('quienRecibe').first().text());
				$("#spanFolioCadCus").html($(xml).find('folio').text());
				
				$("#txtCadCusEmbTrasC").val($(xml).find('institucionEmbalaje').text());
				$("#txtCadCusInsTrasC").val($(xml).find('institucionTraslado').text());
								
				
				var fechaOR=$(xml).find('fechaIntercambio').text().split(" ");
				var fechaR=fechaOR[0];
				var horaR=fechaOR[1];
				var digitosFR=fechaR.split("-");
				$("#txtCadCusFechaRecepcionC").val(digitosFR[2]+"/"+digitosFR[1]+"/"+digitosFR[0]);
				$("#txtCadCusHoraRecepcionC").val(horaR.substring(0,5));
			}


			function  insertOtherDate(value){	
				$("#txtCadCusFechaRecepcion").val($("#txtCadCusFechaEntrega").val());	
				}
			
			/*
			* Funcion para abrir la ventana de los objetos desde la cadena de custodia y poder ver la informacion de detalle
			*/
			function abreVentanaConsultaObjeto(tipoObjeto,idObjeto)
			{
				idObjeto=$.trim(idObjeto);
				idWindowObEvidencia++;
				//lanzamos la pantalla que depende del objeto
				if(parseInt(tipoObjeto)=='<%= Objetos.VEHICULO.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:5,width:570, height:600,title:"Consultar veh&iacute;culo", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarVehiculo.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=VEHICULO&idVehiculo='+idObjeto+'&anularConsultaCadCus=1" width="570" height="600" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:830,height:340,title:"Consultar equipo de c&oacute;mputo", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoDeComputo.do?numeroExpediente='+numeroExpediente +'&idEquipoComputo='+idObjeto+'&tipoObjeto=EQUIPO_COMPUTO&anularConsultaCadCus=1" width="830" height="340" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.EQUIPO_TELEFONICO.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:870,height:340,title:"Consultar equipo telef&oacute;nico", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEquipoTelefonico.do?numeroExpediente='+numeroExpediente+'&idEquipoTelefonico='+idObjeto+'&tipoObjeto=EQUIPO_TELEFONICO&anularConsultaCadCus=1" width="870" height="340" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.ARMA.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:380,title:"Consultar arma", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarArma.do?numeroExpediente='+numeroExpediente+'&idArma='+idObjeto+'&tipoObjeto=ARMA&anularConsultaCadCus=1" width="800" height="380" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.EXPLOSIVO.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:880,height:330,title:"Consultar explosivo", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarExplosivo.do?numeroExpediente='+numeroExpediente+'&idExplosivo='+idObjeto+'&tipoObjeto=EXPLOSIVO&anularConsultaCadCus=1" width="880" height="330" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.SUSTANCIA.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar sustancia", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarSustancia.do?numeroExpediente='+numeroExpediente+'&idSustancia='+idObjeto+'&tipoObjeto=SUSTANCIA&anularConsultaCadCus=1" width="900" height="330" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.ANIMAL.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:900,height:330,title:"Consultar animal", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarAnimal.do?numeroExpediente='+numeroExpediente+'&idAnimal='+idObjeto+'&tipoObjeto=ANIMAL&anularConsultaCadCus=1" width="900" height="330" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.AERONAVE.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Consultar aeronave", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarAeronave.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=AERONAVE&idAeronave='+idObjeto+'&anularConsultaCadCus=1" width="600" height="530" />');			
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.EMBARCACION.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:10,width:600, height:530,title:"Consultar embarcaci&oacute;n", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarEmbarcacion.do?numeroExpediente='+numeroExpediente +'&tipoObjeto=EMBARCACION&idEmbarcacion='+idObjeto+'&anularConsultaCadCus=1" width="600" height="530" />');			
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.NUMERARIO.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar numerario", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarNumerario.do?numeroExpediente='+numeroExpediente+'&idNumerario='+idObjeto+'&tipoObjeto=NUMERARIO&anularConsultaCadCus=1" width="800" height="350" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.VEGETAL.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar vegetal", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarVegetal.do?numeroExpediente='+numeroExpediente+'&idVegetal='+idObjeto+'&tipoObjeto=VEGETAL&anularConsultaCadCus=1" width="800" height="350" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:800,height:350,title:"Consultar documento oficial", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarDocumentoOficial.do?numeroExpediente='+numeroExpediente+'&idDocOfic='+idObjeto+'&tipoObjeto=DOCUMENTO_OFICIAL&anularConsultaCadCus=1" width="800" height="350" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.JOYA.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar joya", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarJoya.do?numeroExpediente='+numeroExpediente+'&idJoya='+idObjeto+'&tipoObjeto=JOYA&anularConsultaCadCus=1" width="850" height="380" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.OBRA_DE_ARTE.getValorId() %>')
				{
					$.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:850, height:380,title:"Consultar obra de arte", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarObraDeArte.do?numeroExpediente='+numeroExpediente+'&idObraArte='+idObjeto+'&tipoObjeto=OBRA_DE_ARTE&anularConsultaCadCus=1" width="850" height="380" />');
				}
				else if(parseInt(tipoObjeto)=='<%= Objetos.OTRO.getValorId() %>')
				{
				    $.newWindow({id:"iframewindowIngresarEvidencia" + idWindowObEvidencia, statusBar: true, posx:200,posy:50,width:850,height:380,title:"Consultar otro", type:"iframe",modal: true});
				    $.updateWindowContent("iframewindowIngresarEvidencia" + idWindowObEvidencia,'<iframe src="<%= request.getContextPath() %>/IngresarOtros.do?numeroExpediente='+numeroExpediente +'&idOtros='+idObjeto+'&tipoObjeto=OTRO&anularConsultaCadCus=1" width="850" height="380" />');
				}
			}
			
			/*
			*Funcion para limpiar los campos de captura de un eslabon de cadena de custodia
			*Los campos se limpian despues de agregar un eslabon a la cadena
			*/
			function limpiaCamposEslabonCadCus()
			{
				//Campos de entrega del eslabon
				$('#cbxTipoEslabon').find("option[value='-1']").attr("selected","selected");
				$("#txtPersonaEntregaEslbn").val("");
				$("#txtPersonaApPatEntregaEslbn").val("");
				$("#txtPersonaApMatEntregaEslbn").val("");
				$("#txtInstitucionEslbnEntrega").val("");
				$("#txtObsEntregaEslbn").val("");
				$("#txtFechaEntregaEslbn").val("");
				$("#txtHoraEntregaEslbn").timeEntry('destroy');
				$("#txtHoraEntregaEslbn").timeEntry({show24Hours: false,defaultTime: "7:00 AM",maxTime: null});
								
				//Campos de recepcion del eslabon
				$("#txtInstitucionEslbnRecepcion").val("");
				$("#txtObsRecepcionEslbn").val("");
				$("#txtFechaRecepcionEslbn").val("");
				$("#txtHoraRecepcionEslbn").timeEntry('destroy');
				$("#txtHoraRecepcionEslbn").timeEntry({show24Hours: false,defaultTime: "7:00 AM",maxTime: null});
				
				$("#txtFechaPrestamoEslbn").val("");
				$("#txtHoraPrestamoEslbn").timeEntry('destroy');
				$("#txtHoraPrestamoEslbn").timeEntry({show24Hours: false,defaultTime: "7:00 AM",maxTime: null});
				
				$("#txtHoraPrestamoRepEslbn").timeEntry('destroy');
				$("#txtHoraPrestamoRepEslbn").timeEntry({show24Hours: false,defaultTime: "7:00 AM",maxTime: null});
				
				//quitamos la seleccion de los combobox
				$(".chkObjEvdXCadCus").attr('checked', false);
			}
			
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

			//funcion que llama al action para eliminar la evidencia de la cadena de custodia
			function anulaEvidencia()
			{
				var params="idEvidenica="+idEvdAElmnr+"&idObjeto="+idObjAElmnr;
				//mandamos a llamar al action que guardara la cadena de custodia
				$.ajax({
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/eliminarEvdncDeCdnCstd.do',
					data: params,
					dataType: 'xml',
					success: function(xml){
						if(parseInt($(xml).find('bandera').text())==1)
						{
							//se anulo exitosamente la evidencia de la cadena de custodia, actualizamos el grid
							//consultamos la cadena de custodia si el folio es distinto de null
							if($("#spanFolioCadCus").html()!="")
							{
								consultaEvidenciasCadenaDeCustodia();//consulta las evidencias que se dan de alta en la cadena
								consultaEvidenciasXCadenaDeCustodia();//consultas las evidencias de la cadena para los eslabones
								//recargamos el grid apropiado
								if(tipoObjAElmnr==<%= Objetos.ARMA.getValorId() %>){
									window.parent.cargaArma("","");	
								}
								else if(tipoObjAElmnr==<%= Objetos.EQUIPO_TELEFONICO.getValorId() %>){
									window.parent.cargaEquipoTelefonico("","");	
								}
								else if(tipoObjAElmnr==<%= Objetos.VEHICULO.getValorId() %>){
									window.parent.cargaVehiculo("","");	
								}
								else if(tipoObjAElmnr==<%= Objetos.EXPLOSIVO.getValorId() %>){
									window.parent.cargaExplosivo("","");	
								}
								else if(tipoObjAElmnr==<%= Objetos.SUSTANCIA.getValorId() %>){
									window.parent.cargaSustancia("","");	
								}
								else if(tipoObjAElmnr==<%= Objetos.ANIMAL.getValorId() %>){
									window.parent.cargaAnimal("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.OBRA_DE_ARTE.getValorId() %>){
									window.parent.cargaObraDeArte("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.JOYA.getValorId() %>){
									window.parent.cargaJoya("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>){
									window.parent.cargaDocOfic("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.VEGETAL.getValorId() %>){
									window.parent.cargaVegetal("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.NUMERARIO.getValorId() %>){
									window.parent.cargaNumerario("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.AERONAVE.getValorId() %>){
									window.parent.cargaAeronave("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.EMBARCACION.getValorId() %>){
									window.parent.cargaEmbarcacion("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %>){
									window.parent.cargaEquipoComputo("","");
								}
								else if(tipoObjAElmnr==<%= Objetos.OTRO.getValorId() %>){
									window.parent.cargaOtros("","");
								}
							}
						}
						customAlert($(xml).find('mensajeOp').text());
						//hacemos el reset de las variables con los IDs
						idEvdAElmnr=0;
						idObjAElmnr=0;
						tipoObjAElmnr=0;
					}
				});
			}
			
			/*
			Funcion para mandar a eliminar una evidencia de la cadena de custodia
			se usa el Id del objeto seleccionado en ell grid de evidencias de la cadena
			*/
			function anularEvidenciaCadCus()
			{
				if((idEvdAElmnr!=null && idEvdAElmnr>0) &&(idObjAElmnr!=null && idObjAElmnr>0))
				{
					if(parseInt(num)>0){
						customAlert("No podrá eliminar la evidencia seleccionada dado <br/> que existen documentos asociados a la denuncia");
					}
					else
					{
						//procederemos a tratar de eliminar la evidencia
						customConfirm ("¿Está seguro que desea anular la evidencia seleccionada?", "", anulaEvidencia);
					}
				}
				else
				{
					idEvdAElmnr=0;
					idObjAElmnr=0;
					tipoObjAElmnr=0;
					customAlert("Debe seleccionar una evidencia de la lista");
				}
			}
			
			/*
			*Funcion que realiza la carga del combo de tipos de objeto
			*/
			function cargaTiposObjetos() 
			{
				$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarTiposObjeto.do',
					data: '',
					dataType: 'xml',
					async: false,
					success: function(xml)
					{
						$('#cbxTipoEvidencia').empty();
						$('#cbxTipoEvidencia').append('<option value="-1" selected="selected">-Seleccione-</option>');
						$(xml).find('catTipoObjetos').each(function(){
							if(fromIPH!='null')
							{
								//validamos que el tipo de objeto no sea de los que no pueda usar el iph
								if($(this).find('clave').text()!=<%= Objetos.EQUIPO_DE_COMPUTO.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.EQUIPO_TELEFONICO.getValorId() %> &&	
								   $(this).find('clave').text()!=<%= Objetos.OBRA_DE_ARTE.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.JOYA.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.ANIMAL.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.VEGETAL.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.OTRO.getValorId() %> &&
								   $(this).find('clave').text()!=<%= Objetos.DOCUMENTO_OFICIAL.getValorId() %>
								)
								{
									$('#cbxTipoEvidencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
								}
							}
							else
							{
								$('#cbxTipoEvidencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							}
						});
					}
				});
			}
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
  <table width="100%" >
		<tr>
			<td class="tituloFolio">
				<b>Folio : <span id="spanFolioCadCus"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			</td>
			<td align="right">
					 
			</td>
		</tr>
	</table>
        <div id="tabsprincipalconsulta">
		<ul>
			<li id="liEslabones"><a href="#tabsconsultaprincipal-0">Cadenas de Custodia</a>
			<li id="liPestanaUno"><a href="#tabsconsultaprincipal-1"><span id="spanCadCusIngCon">Consultar Cadena de Custodia</span></a>
			</li>
			<li id="liPestanaDos"><a href="#tabsconsultaprincipal-2"><span id="spanEvCadCusIngCon">Consultar Evidencia(s) de Cadena de Custodia</span></a>
			</li>
			<li id="liPestanaTres"><a href="#tabsconsultaprincipal-3"><span id="spanEslCadCusIngCon">Consultar Eslab&oacute;n de Cadena de Custodia</span></a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-0">
			<table>
				<tr>
					<td>
						Seleccione la Cadena de Custodia a Consultar :<br/>
					</td>
				</tr>
				<tr>
					<td width="300px">
						<table id="gridCadCustodiaDelExpediente" style="width:300px;"></table>
						<div id="pagerGridCadCustodiaDelExpediente"></div>		
					</td>
				</tr>
			</table>
		</div>
		<div id="tabsconsultaprincipal-1">
			<!-- TABLA para la insercion de la cadena -->
        	<table width="100%" cellpadding="2" class="tablaInsercion">
        		<tr>
        			<td align="left" width="50%"><b>Responsable del Embalaje : </b>
        				<input type="radio" value="0" id="rdbCadCusRespEmbInt" name="rdbCadCusRespEmb" class="rdbCadCusRespEmb"/>Interno &nbsp;&nbsp;&nbsp;
        				<input type="radio" value="1" id="rdbCadCusRespEmbExt" name="rdbCadCusRespEmb" checked="checked" class="rdbCadCusRespEmb"/>Externo
        			</td>
        			<td width="50%" align="right">
        				<input type="button" value="Guardar" id="btnGuardaCadCustodia" class="btn_Generico"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="txtCadCusRespEmbNombre" style="width:60%;"/>
        						<input type="button" value="<<" onclick="abrePopupSystemUser(1);" id="btnCadCusRespEmb" class="btn_Generico"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Paterno: &nbsp;<input type="text" id="txtCadCusRespEmbApPat" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Materno: <input type="text" id="txtCadCusRespEmbApMat" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr>
	        					<td>Corporaci&oacute;n o : &nbsp;&nbsp;&nbsp;<textarea rows="1" cols="45" id="txtCadCusEmbTras" maxlength="150"></textarea><br/>Instituci&oacute;n
	        					</td>
        					</tr>
        				</table>
        			</td>
        			<td>Fecha de Entrada en Almacén: <input type="text" id="txtCadCusFechaEntrega" disabled="disabled"/>
        			</td>
        		</tr>
        		
        		<tr align="left">
        			<td><b>Responsable del Traslado de la evidencia :</b>
        				<input type="radio" value="0" id="rdbCadCusRespTrasInt" name="rdbCadCusRespTras" class="rdbCadCusRespTras"/>Interno &nbsp;&nbsp;&nbsp;
        				<input type="radio" value="1" id="rdbCadCusRespTrasExt" name="rdbCadCusRespTras" checked="checked" class="rdbCadCusRespTras"/>Externo
        			</td>
        			<td align="left">Hora de entrega: <input type="text" id="txtCadCusHoraEntrega" class="timeRange" value="7:00 AM" size="10"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtCadCusRespTrasNombre" style="width:60%;"/>
        							<input type="button" value="<<" onclick="abrePopupSystemUser(2);" id="btnCadCusRespTras" class="btn_Generico"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Paterno: <input type="text" id="txtCadCusRespTrasApPat" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Materno: <input type="text" id="txtCadCusRespTrasApMat" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Corporaci&oacute;n o : &nbsp;&nbsp;&nbsp;<textarea rows="1" cols="45" id="txtCadCusInsTras" maxlength="150"></textarea><br/>Instituci&oacute;n 
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>
        			</td>
        		</tr>
        		
        		<tr>
        			<td align="left"><b>Persona que entrega la(s) evidencia(s) : </b>
        				<input type="radio" value="0" id="rdbCadCusPerEntInt" name="rdbCadCusPerEnt" class="rdbCadCusPerEnt"/>Interno &nbsp;&nbsp;&nbsp;
        				<input type="radio" value="1" id="rdbCadCusPerEntExt" name="rdbCadCusPerEnt" checked="checked" class="rdbCadCusPerEnt"/>Externo
        			</td>
        			<td>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtCadCusPerEntNombre" style="width:60%;"/>
        							<input type="button" value="<<" onclick="abrePopupSystemUser(3);" id="btnCadCusPerEntrg" class="btn_Generico"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Paterno: <input type="text" id="txtCadCusPerEntApPat" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Materno: <input type="text" id="txtCadCusPerEntApMat" style="width:60%;"/>
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>
        			</td>
        		</tr>
        		
        		<tr>
        			<td align="center"><b>Persona que recibe la(s) evidencia(s) : </b>
        			</td>
        			<td>Fecha de recepci&oacute;n: <input type="text" id="txtCadCusFechaRecepcion" disabled="disabled"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="txtCadCusPerRecNombre" style="width:60%;" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Paterno: <input type="text" id="txtCadCusPerRecApPat" style="width:60%;" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>Apellido Materno: <input type="text" id="txtCadCusPerRecApMat" style="width:60%;" disabled="disabled"/>
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>Hora de recepci&oacute;n: <input type="text" id="txtCadCusHoraRecepcion" class="timeRange" value="7:00 AM" size="10"/>
        			</td>
        		</tr>
        	</table>        
        	<!-- FIN TABLA para la insercion de la cadena -->
        	<!-- TABLA para la consutla de la cadena -->
        	<table width="100%" cellpadding="2" class="tablaConsulta">
        		<tr>
        			<td align="left" width="50%"><b>Responsable del Embalaje : </b>
        			</td>
        			<td width="50%" align="right">
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
							<tr>
        						<td>Nombre(s):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" disabled="disabled" id="txtCadCusRespEmbNombreC" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr valign="top">
	        					<td>Corporaci&oacute;n : &nbsp;<textarea rows="1" cols="50" id="txtCadCusEmbTrasC" maxlength="150" readonly="readonly"></textarea><br/>
	        					</td>
        					</tr>
        				</table>
        			</td>
        			<td>Fecha de entrega: <input type="text" id="txtCadCusFechaEntregaC" disabled="disabled"/>
        			</td>
        		</tr>
        		
        		<tr align="left">
        			<td><b>Responsable del Traslado de la evidencia :</b>
        			</td>
        			<td align="left">Hora de entrega: <input type="text" disabled="disabled" id="txtCadCusHoraEntregaC" class="timeRange" value="7:00 AM" size="10"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" disabled="disabled" id="txtCadCusRespTrasNombreC" style="width:60%;"/>
        						</td>
        					</tr>
        					<tr valign="top">
        						<td>Corporaci&oacute;n : &nbsp;<textarea rows="1" cols="50" id="txtCadCusInsTrasC" maxlength="150" readonly="readonly"></textarea><br/> 
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>
        			</td>
        		</tr>
        		
        		<tr>
        			<td align="left"><b>Persona que entrega la(s) evidencia(s) : </b>
        				</td>
        			<td>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" disabled="disabled" id="txtCadCusPerEntNombreC" style="width:60%;"/>
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>
        			</td>
        		</tr>
        		<tr>
        			<td align="left"><b>Persona que recibe la(s) evidencia(s) : </b>
        			</td>
        			<td>Fecha de recepci&oacute;n: <input type="text" id="txtCadCusFechaRecepcionC" disabled="disabled"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="100%">
        					<tr>
        						<td>Nombre(s): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" disabled="disabled" id="txtCadCusPerRecNombreC" style="width:60%;" disabled="disabled"/>
        						</td>
        					</tr>
        				</table>
        			</td>
        			<td>Hora de recepci&oacute;n: <input type="text" id="txtCadCusHoraRecepcionC" disabled="disabled" class="timeRange" value="7:00 AM" size="10"/>
        			</td>
        		</tr>
        	</table> 
        	<!-- FIN TABLA para la consulta de la cadena -->
        </div>
		<div id="tabsconsultaprincipal-2">
        	<table>
        		<tr>
        			<td>
        				<input type="button" id="btnCapturarEvidenciaCadCus" onclick="consultaEvidenciasCadenaDeCustodia();" value="Capturar evidencia" style="width: 250px;" class="btn_Generico"/>
        				<input type="button" id="btnSeleccionaEvidenciaCadCus" value="Seleccionar evidencia ya capturada" style="width: 250px; display: none" class="btn_Generico"/>
        			</td>
        		</tr>
        	</table>
        	<br/>
        	<table width="850px">
        		<tr class="trCapturaEvidenciaCadCus tablaInsercion">
        			<td><b>Información de la evidencia :</b></td>
        		</tr>
        		<tr class="trCapturaEvidenciaCadCus tablaInsercion">
        			<td>
        				Tipo de Evidencia :  
							 <select id="cbxTipoEvidencia">
							 	<option value="-1" selected="selected">-Seleccione-</option>
							 </select>
						Fecha de Levantamiento : <input type="text" id="txtAddCadCusFechaLev" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Hora de Levantamiento:  <input type="text" id="txtAddCadCusHoraLev" class="timeRange" value="7:00 AM" size="10"/><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Origen de Evidencia :  <input type="text" id="txtAddCadCusOrigen" />
        			</td>
        		</tr>
        		<tr class="trSeleccionaEvidenciaCadCus tablaInsercion">
        			<td><br/>
						Fecha de Levantamiento : <input type="text" id="txtAddCadCusFechaLevReg" disabled="disabled"/>&nbsp;&nbsp;&nbsp;
						Hora de Levantamiento:  <input type="text" id="txtAddCadCusHoraLevReg" class="timeRange" value="7:00 AM" size="10"/>
						&nbsp;&nbsp;
						Origen de Evidencia :  <input type="text" id="txtAddCadCusOrigenReg" />
        			</td>
        		</tr>
        		<tr class="trCapturaEvidenciaCadCus" style="width: 800px;" id="trAnulaAgregaEvdncs">
        			<td align="center">
	        			<br/>
	        			<input type="button" id="idEliminarEvidencia" value="Anular Evidencia" class="btn_Generico"/>&nbsp;&nbsp;&nbsp;&nbsp;
	        			<input type="button" id="idAgregarEvidencia" value="Agregar Evidencia a Cadena de Custodia" class="btn_Generico"/>
	        		</td>
        		</tr>
        		<tr class="trSeleccionaEvidenciaCadCus">
	        		<td align="right">
	        			<br/>
	        			<input type="button" id="idAgregarEvidenciaReg" value="Agregar Evidencias Seleccionadas a Cadena de Custodia"/>
	        		</td>
        		</tr>
        		<tr class="trCapturaEvidenciaCadCus tablaInsercionEsp">
        			<td>
        				<table id="gridCadCustodiaEvdncs"></table>
						<div id="pagerGridCadCustodiaEvdncs"></div>
        			</td>
        		</tr>
        		<tr class="trSeleccionaEvidenciaCadCus">
        			<td align="center">
        				<table id="gridCadCustodiaEvdncsRegs"></table>
						<div id="pagerGridCadCustodiaEvdncsRegs"></div>
        			</td>
        		</tr>
        	</table>
        </div>
		<div id="tabsconsultaprincipal-3">
        	<table>
        		<tr>
        			<td colspan="2" align="right">
        			    <input type="button" value="Adicionar Eslabones a la Cadena" id="btnAddEslbnCadCus" class="btn_Generico"/>&nbsp;&nbsp;&nbsp;
        				<input type="button" value="Registrar Eslab&oacute;n de Cadena" id="btnRegEslbnCadCus" class="btn_Generico"/>
        			</td>
        		</tr>
        		<tr>
        			<td align="center">
        				<table id="gridCadCustodiaEvdncsEslabon"></table>
						<div id="pagerGridCadCustodiaEvdncsEslabon"></div>
        			</td>
        		</tr>
        	</table>
        	<table width="900px" class="tablaInsercion" id="tabalaInsercionEslabones">
        		<tr>
        			<td>
        				Tipo de Eslab&oacute;n : 
        				<select id="cbxTipoEslabon">
        					<option selected="selected" value="-1">-Seleccione-</option>
        				</select> 
        			</td>
        			<td style="visibility: hidden;">
        				Tipo de Eslab&oacute;n : 
        				<select id="cbxTipoEslabonR">
        					<option selected="selected" value="-1">-Seleccione-</option>
        				</select> 
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<table width="50%">
        					<tr>
        						<td colspan="2" align="center">
        							<b>DATOS DE ENTREGA</b>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Nombre: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaEntregaEslbn" maxlength="20" style="width: 150px;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Paterno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApPatEntregaEslbn" maxlength="20" style="width: 150px;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Materno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApMatEntregaEslbn" maxlength="20" style="width: 150px;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Fecha: 
        						</td>
        						<td>
        							<input type="text" id="txtFechaEntregaEslbn" maxlength="10" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Hora: 
        						</td>
        						<td>
        							<input type="text" id="txtHoraEntregaEslbn" class="timeRange" value="7:00 AM" size="10"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Instituci&oacute;n: 
        						</td>
        						<td>
        							<textarea rows="3" cols="45" id="txtInstitucionEslbnEntrega" maxlength="99"></textarea>
        						</td>
        					</tr>
        					
        					<tr>
        						<td colspan="2" align="left">
        							<b>Periodo de pr&eacute;stamo</b> 
        						</td>
        						<td>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Fecha incial: 
        						</td>
        						<td>
        							<input type="text" id="txtFechaPrestamoEslbn" maxlength="10" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Hora: 
        						</td>
        						<td>
        							<input type="text" id="txtHoraPrestamoEslbn" class="timeRange" value="7:00 AM" size="10"/>
        						</td>
        					</tr>
        					
        					<!-- <tr>
        						<td>
        							Observaciones: 
        						</td>
        						<td>
        							<textarea rows="4" cols="45" id="txtObsEntregaEslbn"></textarea>
        						</td>
        					</tr>-->
        				</table>
        			</td>
        			<td>
        				<table width="50%">
        					<tr>
        						<td colspan="2" align="center">
        							<b>DATOS DE RECEPCI&Oacute;N</b>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Nombre: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaRecibeEslbn" maxlength="20" style="width: 150px;" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Paterno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApPatRecibeEslbn" maxlength="20" style="width: 150px;" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Materno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApMatRecibeEslbn" maxlength="20" style="width: 150px;" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Fecha: 
        						</td>
        						<td>
        							<input type="text" id="txtFechaRecepcionEslbn" maxlength="10" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Hora: 
        						</td>
        						<td>
        							<input type="text" id="txtHoraRecepcionEslbn" class="timeRange" value="7:00 AM" size="10"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Instituci&oacute;n: 
        						</td>
        						<td>
        							<textarea rows="3" cols="45" id="txtInstitucionEslbnRecepcion" maxlength="99"></textarea>        							
        						</td>
        					</tr>
        					
        					
        					<tr>
        						<td>
        							 &nbsp;&nbsp;&nbsp;
        						</td>
        						<td>
        							&nbsp;&nbsp;&nbsp;
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Fecha final: 
        						</td>
        						<td>
        							<input type="text" id="txtFechaPrestamoRecEslbn" maxlength="10" disabled="disabled"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Hora: 
        						</td>
        						<td>
        							<input type="text" id="txtHoraPrestamoRepEslbn" class="timeRange" value="7:00 AM" size="10"/>
        						</td>
        					</tr>
        					
        					
        					<!-- <tr>
        						<td style="visibility: hidden;">
        							Observaciones: 
        						</td>
        						<td style="visibility: hidden;">
        							<textarea rows="4" cols="45" id="txtObsRecepcionEslbn"></textarea>
        						</td>
        					</tr>-->
        				</table>
        			</td>
        		</tr>
        	</table>
       	<table id="tablaObsGeneralesEslbnCadCus">
       		<tr>
       			<td>
							Observaciones: 
				</td>
       		</tr>
       		<tr>
	       		<td>
	       			<textarea rows="4" cols="150" id="txtObsEntregaEslbn" maxlength="299"></textarea>
	       		</td>
       		</tr>
       	</table>
        
        <table border="0" width="950px" class="tablaConsulta" id="tablaEslabonesCadCustodia">
			<tr>
				<td >&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td id="contador" class="txt_gral_victima">n De N</td>
				<td> <INPUT type=button class="btn_guardar" id=anterior value=- onclick="menosDatos()"></td>
				<td> <INPUT type=button class="btn_guardar" id=siguiente value=+ onclick="masDatos()"></td>
			</tr>
			<tr>
				<td colspan="3" class="txt_gral_victima"> &nbsp;</td>
				<td colspan="3" class="txt_gral_victima"> &nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" class="txt_gral_victima">
					<table>
						<tr>
							<td class="txt_gral_victima">Tipo de Eslab&oacute;n :</td>
							<td id="tipoEslabonConsulta"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" class="txt_gral_victima"> <b>Datos de la Entrega</b></td>
				<td colspan="3" class="txt_gral_victima"> <b>Datos de la Recepci&oacute;n</b></td>
			</tr>
			<tr>
				<!-- Tabla para los datos de entrega -->
				<td colspan="3">
					<table width="50%">
						<tr>
							<td class="txt_gral_victima">Nombre:</td>
							<td id="nombreDatEntrega"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima" width="50%">Instituci&oacute;n:</td>
							<td id="institucionEntregaEslbnConsulta" width="50%"></td>
						</tr>
						<tr>
							<td >&nbsp;</td>
							<td >&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima">Fecha y hora:</td>
							<td id="fechaDatEntrega"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima" width="50%">Observaciones:</td>
							<td id="ObservacionesDatEntrega" width="50%"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima">&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima"><b>Periodo del pr&eacute;stamo</b></td>
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima">Fecha inicial: </td>
							<td id="fIniCadCusPres"></td>
						</tr>
					</table>				
				</td>
				<!-- Tabla para los datos de recepcion -->
				<td colspan="3">
					<table width="50%">
						<tr>
							<td class="txt_gral_victima">Nombre:</td>
							<td id="nombreDatRecep"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima">Instituci&oacute;n:</td>
							<td id="institucionRecepcionEslbnConsulta"></td>
						</tr>
						<tr>
							<td >&nbsp;</td>
							<td >&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima">Fecha y hora:</td>
							<td id="fechaDatRecep"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima" style="visibility: hidden;">Observaciones:</td>
							<td id="observacionesDatRecep" style="visibility: hidden;"></td>
						</tr>
						<tr>
							<td class="txt_gral_victima">&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima">&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="txt_gral_victima">Fecha final: </td>
							<td id="fFinCadCusPres"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
  	<!-- DIV para obtener los responsables internos -->
		<div id="dialog-confirm" title="Seleccionar Responsable" >
		<p align="center">
			<select id="cbxAusentePresente">
				<option value="-1" selected="selected">-Seleccione-</option>
				<option value="0" >Presente</option>
				<option value="1" >Ausente</option>
			</select>
			<div id="divPresente" >
				<center>
					Usuario &nbsp;: <input type="text" id="txtUsuario" maxlength="30"/><br/><br/>
					Password : <input type="password" id="txtPwdUsuario" maxlength="20"/><br/><br/>
				</center>
			</div>
			<div id="divAusente" >
				<center>
					No. Empleado : <input type="text" id="txtNumUsuario"/><br/><br/>
				</center>
			</div>
		</p>
	</div>
	<!-- FIN DIV para obtener los responsables internos -->
  </body>
<script type="text/javascript">
$( "#dialog-confirm" ).dialog();
$( "#dialog-confirm" ).dialog( "destroy" );

//Funcion para obtener los Datos generales de eslabon
function recupera(id){
	$.ajax({
		type: 'POST',
		url: '<%= request.getContextPath()%>/consultarEslabonesEvidenciaPorId.do',
		data: 'idEvidencia='+id,
		dataType: 'xml',
		async: false,
		success: function(xml){
			cantidad=0;
			nuevoArray=new Array();
			$(xml).find('eslabonDTO').each(function(){
				//var nom=$(this).find("funcionariEntrega").find("nombreFuncionario").text();
				var nom=$(this).find("quienEntrega").text();
				var fecha=$(this).find("strFechaInicioMov").text()+ " "+$(this).find("strHoraInicioMov").text();
				var prestamo=$(this).find("tipoEslabon").find("valor").text();
				var observaciones="";
				if($(this).find("observacion"))
				{
					observaciones=$(this).find("observacion").text();
				}
				//var nomRep=$(this).find("funcionariRecibe").find("nombreFuncionario").text();
				var nomRep=$(this).find("quienRecibe").first().text();
				var fechaRep=$(this).find("strFechaFinMov").text()+ " "+$(this).find("strHoraFinMov").text();
				var institucion=$(this).find("institucionQueEntrega").text();
				var institucionR=$(this).find("institucionQueRecibe").text();
				var eslabon=$(this).find("tipoEslabon").find("valor").text();
				var eslabonR="";//$(this).find("tipoEslabonDeRecepcion").find("valor").text();
				var fechaInicioPres=$(this).find("strFechaInicioPrestamo").text();
				var fechaFinPres=$(this).find("strFechaFinPrestamo").text();
				var nuevoDatosRecepcion = new datosRecepcion(nom,fecha, prestamo, observaciones,nomRep,fechaRep,institucion,institucionR,eslabon,eslabonR,fechaInicioPres,fechaFinPres);
				nuevoArray[cantidad]=nuevoDatosRecepcion;
				cantidad++;
			});
			if(parseInt(consultaCadena)==1)
			{
				//mostranos la seccion del eslabon 
				$("#tablaEslabonesCadCustodia").show();
				$("#tabalaInsercionEslabones").hide();
				$("#btnRegEslbnCadCus").hide();
				$("#btnAddEslbnCadCus").show();
				$("#tablaObsGeneralesEslbnCadCus").hide();
			}
			if(cantidad>0)
			{
				$("#tablaEslabonesCadCustodia").show();
				actual=1;
				$('#tipoEslabonConsulta').html(""+nuevoArray[actual-1].eslabon);
				$('#contador').html(actual+" de "+nuevoArray.length);
				$('#nombreDatEntrega').html(""+nuevoArray[actual-1].nombre);
				$('#fechaDatEntrega').html(""+nuevoArray[actual-1].fecha);
				$('#institucionEntregaEslbnConsulta').html(""+nuevoArray[actual-1].institucion);
				$('#institucionRecepcionEslbnConsulta').html(""+nuevoArray[actual-1].institucionR);
				$('#ObservacionesDatEntrega').html(""+nuevoArray[actual-1].observaciones);
				$('#nombreDatRecep').html(""+nuevoArray[actual-1].nombreRep);
				$('#fechaDatRecep').html(""+nuevoArray[actual-1].fechaRep);
				$('#observacionesDatRecep').html(""+nuevoArray[actual-1].observaciones);
				$('#fFinCadCusPres').html(""+nuevoArray[actual-1].fechaFinPres);
				$('#fIniCadCusPres').html(""+nuevoArray[actual-1].fechaInicioPres);
			}
			else
			{
				$("#tablaEslabonesCadCustodia").hide();
			}
		}
	});
}

//Se define la clase para un usuario Externo
function datosRecepcion(nombre,fecha,prestamo,observaciones,nombreRep,fechaRep,institucion,institucionR,eslabon,eslabonR,fechaInicioPres,fechaFinPres){
	this.nombre = nombre
    this.fecha= fecha
    this.prestamo= prestamo
    this.observaciones= observaciones
    this.nombreRep = nombreRep
    this.fechaRep= fechaRep
    this.institucion=institucion;
    this.institucionR = institucionR
    this.eslabon=eslabon
    this.eslabonR=eslabonR
    this.fechaInicioPres=fechaInicioPres
    this.fechaFinPres=fechaFinPres
} 

function masDatos(){
	actual++;
	if(actual<=nuevoArray.length){
		$('#contador').html(actual+" de "+nuevoArray.length);
		$('#tipoEslabonConsulta').html(""+nuevoArray[actual-1].eslabon);
		$('#nombreDatEntrega').html(""+nuevoArray[actual-1].nombre);
		$('#fechaDatEntrega').html(""+nuevoArray[actual-1].fecha);
		$('#institucionEntregaEslbnConsulta').html(""+nuevoArray[actual-1].institucion);
		$('#institucionRecepcionEslbnConsulta').html(""+nuevoArray[actual-1].institucionR);
		$('#ObservacionesDatEntrega').html(""+nuevoArray[actual-1].observaciones);
		$('#nombreDatRecep').html(""+nuevoArray[actual-1].nombreRep);
		$('#fechaDatRecep').html(""+nuevoArray[actual-1].fechaRep);
		$('#observacionesDatRecep').html(""+nuevoArray[actual-1].observaciones);
		$('#fFinCadCusPres').html(""+nuevoArray[actual-1].fechaFinPres);
		$('#fIniCadCusPres').html(""+nuevoArray[actual-1].fechaInicioPres);
	}else{
		actual--;
	}
}

function menosDatos(){
	actual--;
	if(actual>=1){
		$('#contador').html(actual+" de "+nuevoArray.length);
		$('#tipoEslabonConsulta').html(""+nuevoArray[actual-1].eslabon);
		$('#nombreDatEntrega').html(""+nuevoArray[actual-1].nombre);
		$('#fechaDatEntrega').html(""+nuevoArray[actual-1].fecha);
		$('#institucionEntregaEslbnConsulta').html(""+nuevoArray[actual-1].institucion);
		$('#institucionRecepcionEslbnConsulta').html(""+nuevoArray[actual-1].institucionR);
		$('#ObservacionesDatEntrega').html(""+nuevoArray[actual-1].observaciones);
		$('#nombreDatRecep').html(""+nuevoArray[actual-1].nombreRep);
		$('#fechaDatRecep').html(""+nuevoArray[actual-1].fechaRep);
		$('#observacionesDatRecep').html(""+nuevoArray[actual-1].observaciones);
		$('#fFinCadCusPres').html(""+nuevoArray[actual-1].fechaFinPres);
		$('#fIniCadCusPres').html(""+nuevoArray[actual-1].fechaInicioPres);
	}else{
		actual++;
	}
}		


</script>
</html>