<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" >
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" >
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" >
   	<link rel="stylesheet" type="text/css" href="/nsjp-web/resources/css/jquery.timeentry.css"/>
    
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>	
	<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="/nsjp-web/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>		
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Defensa en Integración</title>
	
	<script type="text/javascript">
      
		//variables globales

	 	var idInvolucrado=parent.involucradoID;
       	var idWindowIngresarNarrativa = 1;
		var idWindowIngTutor = 1;
		var varNombre;		
		var banderaDenuncia=0;
		var banderaDenuncianteQuerellante=0;
		var banderaNarrativa=0;
		var idindi=0;		
		var idOrganizacion=0;
		var idExpediente = "";
		var idWindowIngresarNarrativa = 1;
		var idUsuario="";
		var tipoTiempoHecho=1;//1 para definido,2 para lapso y 3 para descripcion hecho en el tiempo
		var calidad="";
		var idHecho="";
		var banderaConsultaHecho=0;
		var numEtapaMenu=parent.numEtapa;
		var valorEtapaMenu=parent.valorEtapa;
		var forma = "";
		var idAudiencia="";
	 	var cadenaNumeroExpediente ="";
	 	var idNumeroExp = "";
		var actuacion=0;
		
	$(document).ready(function() {
		cargaDatosSolicitud();
		
		var asNombreVictima= '<%= request.getParameter("nombreVictima")%>';		
		var asDelitos ='<%= request.getParameter("delitos")%>';

		$('#delitos').val(asDelitos);
		$('#victima').val(asNombreVictima);
		
		
		$('#areaSolicitante').attr("disabled", "disabled");
		$('#FechaSolicitante').attr("disabled", "disabled");
		$('#numCaso').attr("disabled", "disabled");
		$('#numExpediente').attr("disabled", "disabled");
		$('#delitos').attr("disabled", "disabled");
		$('#victima').attr("disabled", "disabled");
		$('#estatusSolicitud').attr("disabled", "disabled");
		$('#HoraSolicitante').attr("disabled", "disabled");
		
		$("#tabsprincipalconsulta").tabs();				
						$("#FechaSolicitante").datepicker({
							dateFormat: 'dd/mm/yy',
							yearRange: '1900:2100',
							changeMonth: true,
							changeYear: true
							
						});
					
	});//Termina document Ready
	
		function gridDocumento() {
					jQuery("#gridDocumentos").jqGrid({
								url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idExpediente, 
								datatype: "xml", 
								
								colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
								colModel:[ 	{name:'area',index:'area', width:200},
											{name:'FechaActividad',index:'fechaActividad', width:170},							
											{name:'NombreActividad',index:'nombreActividad', width:400},
								           	{name:'Tipo',index:'tipo', width:155}, 
											{name:'Nombre',index:'nombre', width:255},
								           	{name:'Fecha',index:'fecha', width:170}
											],
					pager: jQuery('#paginadorDocumentos'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					sortname: 'Fecha',
					viewrecords: true,
					sortorder: "desc",
					onSelectRow: function(id) {
							consultaPDF(id);
						}
				}).navGrid('#paginadorDocumentos',{edit:false,add:false,del:false});
				 
			}

			 
			 function cargaDatosSolicitud(){				 
				  solicitudId='<%= request.getParameter("idSolicitud")%>';
					$.ajax({
				    	  type: 'POST',
				    	  url:  '<%= request.getContextPath()%>/consultaDetalleDeSolicitudPorId.do',				    	  
				    	  data: 'solicitudId='+solicitudId,
				    	  dataType: 'xml',
				    	  async: false,
				    	  success: function(xml){
						      cadenaNumeroExpediente = $(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text();	  			
				  			  idNumeroExp = $(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpedienteId').text();		  			
				  			  idExpediente = $(xml).find('solicitudDTO').find('expedienteDTO').find('expedienteId').text();
				    		  pintaDatosSolicitud(xml);
						  }
				    });
			    }

			 function pintaDatosSolicitud(xml){
		  			
				 
			        if($(xml).find('solicitudDTO').find('nombreInstitucionSolicitante') != null){
			 		   $('#areaSolicitante').val($(xml).find('solicitudDTO').find('nombreInstitucionSolicitante').text());
			        }else{
			        	 $('#areaSolicitante').val("-");
			        }

			        if($(xml).find('solicitudDTO').find('strFechaCreacion') != null){
			  		   $('#FechaSolicitante').val($(xml).find('solicitudDTO').find('strFechaCreacion').text());
			         }else{
			        	 $('#FechaSolicitante').val("-");
				     }			        

			        if($(xml).find('solicitudDTO').find('strHoraCreacion') != null){
			   		   $('#HoraSolicitante').val($(xml).find('solicitudDTO').find('strHoraCreacion').text());
			          }else{
				        	 $('#HoraSolicitante').val("-");
					     }

			        if($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente') != null){
			    		   $('#numExpediente').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());				  			

			           }else{
				        	 $('#numExpediente').val("-");
					     }


			        if($(xml).find('solicitudDTO').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso') != null){
			    		   $('#numCaso').val($(xml).find('solicitudDTO').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text());
			           }
			        else{
			        	 $('#numCaso').val("-");
				     }

			        if($(xml).find('solicitudDTO').find('delitos') != null){
			    		   $('#delitos').val($(xml).find('delitos').text());
			           }
			        else{
			        	 $('#delitos').val("-");
				     }
			        
			        if($(xml).find('solicitudDTO').find('nombreSolicitanteExternoInterno') != null){
			    		   $('#victima').val($(xml).find('nombreSolicitanteExternoInterno').text());
			           }
			        else{
			        	 $('#victima').val("-");
				     }
			        
			        if($(xml).find('solicitudDTO').find('estatus').find('valor') != null){
			    		   $('#estatusSolicitud').val($(xml).find('estatus').find('valor').text());
			           }
			        else{
			        	 $('#estatusSolicitud').val("-");
				     }

			    }
	
				function consultaPDF(id){					
					document.frmDoc.documentoId.value = id;
					document.frmDoc.submit();
				}
			 
				
				function seleccionaActuacion(){
					var selected = $("#cbxAcciones option:selected");
					actuacion=selected.val()
					var titulo="";
					var formaID=1;

					if(selected.val()=="9"){
						titulo="Adjuntar archivo";
						formaID=2;
					}else if(selected.val()=="16"){
						titulo="Canalizar a dependencia externa";
						formaID=3;
					}else if(selected.val()=="1"){
						titulo="Generar documento de avance";
						formaID=5;
					}else if(selected.val()=="2"){
						titulo="Generar documento de t&eacute;rmino de atenci&oacute;n";
						formaID=6;						
					}
					
					actuacion = "";
						
					if(selected.val()=="1" || selected.val()=="2"){	
			        		    
		        		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:""+titulo, type:"iframe"});
	    		        $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%= request.getContextPath() %>/generarDocumentoSinCaso.do?formaId='+formaID+'&numeroUnicoExpediente='+idExpediente+'" width="1140" height="400" />');
               		    
					}
				}
				
	</script>
	
	<!--ESTILOS PARA LAS TABS-->
	
	<style>
	#tabs { height: 100% } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 550px; overflow: visible; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	</style>
	
</head>
<body>
<div id="tabsprincipalconsulta">
	<ul>
		<li id="tabCedulaIdentificacion" ><a href="#tabsconsultaprincipal-1">Detalle</a></li>
		<li id="tabAcuerdoDefensa" ><a href="#tabsconsultaprincipal-2">Actuaciones</a></li>
		<li id="tabBitacora" ><a href="#tabsconsultaprincipal-3" onclick="gridDocumento()">Documentos</a></li>
		
	</ul>

		<div id="tabsconsultaprincipal-1">
					<table>
					<tr>
					<td>
					Área Solicitante:
					</td>
					<td>
					<input type="text" id="areaSolicitante">
					</td>
					</tr>
					
					<tr>
					<td>
					Fecha y hora de la Solicitud:
					</td>
					<td>
					<input type="text" id="FechaSolicitante">-<input type="text" id="HoraSolicitante">
					</td>
					</tr>
					
					<tr>
					<td>
					Número de caso:
					</td>
					<td>
					<input type="text" id="numCaso" size="28">
					</td>
					</tr>
					
					<tr>
					<td>
					Número de expediente:
					</td>
					<td>
					<input type="text" id="numExpediente" size="28">
					</td>
					</tr>
					
					<tr>
					<td>
					Delito(s):
					</td>
					<td>
					<input type="text" id="delitos" size="46">
					</td>
					</tr>
					
					<tr>
					<td>
					Víctima:
					</td>
					<td>
					<input type="text" id="victima">
					</td>
					</tr>
					
					<tr>
					<td>
					Estatus de la Solicitud:
					</td>
					<td>
					<input type="text" id="estatusSolicitud">
					</td>
					</tr>
					</table>		
			</div>

			<div id="tabsconsultaprincipal-2">
					<table>
					<tr>
					<td>
					Actuaciones
					</td>
					
					</tr>
					<tr>
					<td>
					<select id="cbxAcciones" onchange="seleccionaActuacion()">
					  <option value="-1" selected>- Seleccione -</option>
					  <option value="9">Adjuntar archivo</option>
					  <option value="16">Canalizar a dependencia externa</option>
					  <option value="1">Generar documento de avance</option>
					  <option value="2">Generar documento de t&eacute;rmino de atenci&oacute;n</option>
                    </select>
					</td>
					
					</tr>
					</table>		
            </div>
			<div id="tabsconsultaprincipal-3">		
				
				<div id="divGridDocumentos">
					<table id="gridDocumentos"></table>
					<div id="paginadorDocumentos"></div>
				</div>
				<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
				<input type="hidden" name="documentoId" />
				</form>
			</div>
			
			
			
		</div>


	
</body>
</html>