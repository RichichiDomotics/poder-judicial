<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	 <%@ page import="mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia" %>

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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>		
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Defensa en Integración</title>
	
	<script type="text/javascript">
      
		//variables globales
		var idEvidencia = parent.idParaConsultaEvidencia;
		var idAlmacen = parent.idAlmacenParaGestionarAlmacen;
		var numeroGeneralCaso = "";
		var cadenaDeCustodiaId = 0;
		var estatusEvidencia = 0;
	$(document).ready(function() {

	$("#tabsprincipalconsulta").tabs();
	$("#tabsPrincipal").tabs();

		//vamos por la fecha actual al servidor
		fechaServidor= consultaFechaHoraMaximaServer();
		fechaMax=getFechaMaximaServerHechos(fechaServidor);
		timeMax=getHoraMaximaServer(fechaServidor);

		seteaInfoDeEvidencia();		
		//idEvidencia='<%= request.getParameter("idEvidencia")%>';
		cargaDatosEvidencia(idEvidencia);	
		
		//Inician Funciones para el registro de eslabon
		cargaCatalogoTipoEslabon();		
		$("#btnRegEslbnCadCus").click(registrarEslabonCadenaCustodia);
		$("#txtFechaRecepcionEslbn").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true,
			minDate: fechaMax
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
		//Finalizan Funciones para el registro de eslabon
	});
	
    function cargaCatalogoTipoEslabon()
    {
   	$('#cbxTipoEslabon').empty();
   	$('#cbxTipoEslabon').append('<option value="-1" selected="selected">-Seleccione-</option>');
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoTiposEslabon.do',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catEslabon').each(function(){
					$('#cbxTipoEslabon').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
			}
		});
    }
    

	function generaEstatusEvidencia(esAlta)
	{
		if(esAlta == 0)
			estatusEvidencia = '<%= EstatusEvidencia.BAJA.getValorId() %>';
		else
			estatusEvidencia = '<%= EstatusEvidencia.EN_ALMACEN.getValorId() %>';
		//Permite actualizar el estatus de una Evidencia
		actualizaEstatusDeEvidencia(estatusEvidencia);
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
							var bandera=$(xml).find('exito').find('bandera').text();
							if(parseInt(bandera)==0 || $(xml).find('body').text() == "null")
							{
								customAlert("Ocurrió un error al guardar el eslabón de la cadena de custodia","");
							}
							else
							{
								customAlert("Se guardó exitosamente el eslabón de la cadena de custodia","");
							}
							
						}
						else
						{
							cadenaCustodia="";
							customAlert("Ocurrió un error al guardar el eslabón de la cadena de custodia","");
						}
				}
			});
		}
	}
	
	function validaCamposRegEslabonDeCadena()
	{
		//abriremos el popup del tipo de objeto seleccionado 
		var tipoEslabon= $('#cbxTipoEslabon option:selected').val();
		//revisamos la opcion seleccionada
		if(parseInt(tipoEslabon)==-1)
		{
			customAlert("debe seleccionar el tipo de eslabón poder continuar");
			return false;
		}
		//revisamos el nombre de la persona que entrega
		if($.trim($("#txtPersonaEntregaEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el nombre de la persona que entrega");
			return false;
		}
		
		//revismaos el apellido paterno de quien recibe
		if($.trim($("#txtPersonaApPatEntregaEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el apellido paterno de la persona que entrega");
			return false;
		}
		
    	//revismaos el apellido materno de quien recibe
		if($.trim($("#txtPersonaApMatEntregaEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el apellido materno de la persona que entrega");
			return false;
		}
		
		//revisamos la fecha de entrega
		if($.trim($("#txtFechaEntregaEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar la fecha de la entrega");
			return false;
		}
		//revismaos la hora de entrega
		if($.trim($("#txtHoraEntregaEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar la hora de la entrega");
			return false;
		}
		//revisamos el tiempo de entrega
		/*if(trim($("#txtTiempoEntregaEslbn").val()).length==0)
    	{
			return false;
    	}*/	
		//revismaos el nombre de quien recibe
		if($.trim($("#txtPersonaRecibeEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el nombre de la persona que recibe");
			return false;
		}
    	
    	//revismaos el apellido paterno de quien recibe
		if($.trim($("#txtPersonaApPatRecibeEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el apellido paterno de la persona que recibe");
			return false;
		}
		
    	//revismaos el apellido materno de quien recibe
		if($.trim($("#txtPersonaApMatRecibeEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar el apellido materno de la persona que recibe");
			return false;
		}
    	
		//revismaos la fecha de recepcion
		if($.trim($("#txtFechaRecepcionEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar la fecha de la recepción");
			return false;
		}
		//revisamos la hora de recpcion
		if($.trim($("#txtHoraRecepcionEslbn").val()).length==0)
		{
			customAlert("Debe proporcionar la hora de la recepción");
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

	
	function recuperaParametrosRegEslabonCadCus()
	{
		var params="";
		//datos de entrega
		params+="tipoEslabon="+$('#cbxTipoEslabon option:selected').val();
		params+="&idsEvidencias="+idEvidencia;
		params+="&nombrePersonaEntrega="+$("#txtPersonaEntregaEslbn").val()+"-"+ $("#txtPersonaApPatEntregaEslbn").val() +"-"+ $("#txtPersonaApMatEntregaEslbn").val();
		params+="&fechaEntrega="+$("#txtFechaEntregaEslbn").val();
		params+="&horaEntrega="+$("#txtHoraEntregaEslbn").val();
		
		params+="&posicionEvidencia="+$("#txtPosicion").val();
		params+="&ubicacionFisica="+$("#texUbicacion").val();
		
		if($.trim($("#txtObsEntregaEslbn").val()).length>0)
		{
			params+="&obsEntrega="+$("#txtObsEntregaEslbn").val();
		}
		
		if($.trim($("#txtTiempoEntregaEslbn").val()).length>0)
    	{
			params+="&tiempoPrestamo="+$("#txtTiempoEntregaEslbn").val();
    	}
		//datos de recepcion 
		params+="&nombrePersonaRecepcion="+$("#txtPersonaRecibeEslbn").val()+"-"+ $("#txtPersonaApPatRecibeEslbn").val() +"-"+ $("#txtPersonaApMatRecibeEslbn").val();
		params+="&fechaRecepcion="+$("#txtFechaRecepcionEslbn").val();
		params+="&horaRecepcion="+$("#txtHoraRecepcionEslbn").val();
		if($.trim($("#txtObsRecepcionEslbn").val()).length>0)
		{
			params+="&obsRecepcion="+$("#txtObsRecepcionEslbn").val();
		}
		
		if($.trim($("#txtInstitucionEslbnEntrega").val()).length>0)
    	{
			params+="&institucionEnt="+$("#txtInstitucionEslbnEntrega").val();
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

	
	function seteaInfoDeEvidencia(){
		$("#txtNumEvi").val(idEvidencia);		
	}
		

	
   	//Llena el grid con los resultados de la busqueda, pasa como parametros el No general del caso
	  var banderaCargarORecargar=0;

   	function consultaEvidencias(){
			//Inicia grid
			if(banderaCargarORecargar==0){
				jQuery("#tablaInventarioDeEvidenciasPorCaso").jqGrid({
					url:'<%=request.getContextPath()%>/generaInventarioDeAlmacen.do?numeroGeneralCaso='+numeroGeneralCaso+'&idAlmacen='+idAlmacen, 
					datatype: "xml",  							
					colNames:['#Caso','#Cadena','#Evidencia','Nombre','Codigo','Cantidad','Estatus','Fecha de devolución'], 
					colModel:[  {name:'Caso',index:'caso', width:100},
					            {name:'Cadena',index:'cadena', width:100},
								{name:'Evidencia',index:'evidencia', width:100},
								{name:'NombreEvi',index:'nombreEvi', width:80}, 
								{name:'Codigo',index:'codigo', width:80}, 
								{name:'Cantidad',index:'cantidad', width:80},
								{name:'Estatus',index:'estatus', width:100},
								{name:'Fecha',index:'fecha', width:100}
							],
							pager: jQuery('#pager1'), 
							rowNum:10,
							rowList:[10,20,30],
		                    autowidth: true,
		                    autoheight:true,
		                  	height:600,
							sortname: 'Cadena', 
							viewrecords: true,
		                    sortorder: "desc"});
	        	$("#tablaInventarioDeEvidenciasPorCaso").trigger("reloadGrid");
	        	banderaCargarORecargar=1;
	    }else{
	    	jQuery("#tablaInventarioDeEvidenciasPorCaso").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/generaInventarioDeAlmacen.do?numeroGeneralCaso='+numeroGeneralCaso+'&idAlmacen='+idAlmacen,datatype: "xml" });
			$("#tablaInventarioDeEvidenciasPorCaso").trigger("reloadGrid");		
	    }	            	
		//Fin grid
		  
	}//Cierra consultaEvidencias

			 
	function gridDocumento() {
	  	  jQuery("#gridDocumentos").jqGrid({
						url:'<%=request.getContextPath()%>/consultarDocumentosXIdEvidencia.do?idEvidencia='+idEvidencia, 
						datatype: "xml", 						
						colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
						colModel:[ 	{name:'area',index:'area', width:200, hidden:true},
									{name:'FechaActividad',index:'fechaActividad', width:170, hidden:true},							
									{name:'NombreActividad',index:'nombreActividad', width:400, hidden:true},
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


	
	function consultaPDF(id){					
		document.frmDoc.documentoId.value = id;
		document.frmDoc.submit();
	}
	
	function cargaDatosEvidencia(evidenciaId){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarEvidenciaPorId.do',
	    	  data: 'evidenciaId='+evidenciaId,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  pintaDatosEvidencia(xml);
			  }
	    });
    }
	
    function pintaDatosEvidencia(xml){
		 //Numero de caso
    	if($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso') != null){
 		   $('#txtNumCaso').val($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text());
 		  numeroGeneralCaso = $(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text();
        }
    	//Numero de expediente
        if($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('expedienteDTO').find('numeroExpediente') != null){
    		   $('#txtNumExpediente').val($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('expedienteDTO').find('numeroExpediente').text());
           }
    	
      //Numero de cadena
        if($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('folio') != null){
    		   $('#txtNumCadena').val($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('folio').text());
           }
        //Cadena de custodia
        if($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('cadenaDeCustodiaId') != null){
    		   $('#txtNombreCadena').val($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('cadenaDeCustodiaId').text());
    		   cadenaDeCustodiaId = $(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('cadenaDeCustodiaId').text();
           }
      
        
		//Numero de evidencia
        if($(xml).find('evidenciaDTO').find('evidenciaId') != null){
 		   $('#txtNumEvi').val($(xml).find('evidenciaDTO').find('evidenciaId').text());
        }
		//Nombre de la evidencia  
        if($(xml).find('evidenciaDTO').find('tipoObjeto')!= null){
 		   $('#txtNombreEvi').val($(xml).find('evidenciaDTO').find('tipoObjeto').text());
        }
		
      //Nombre de la evidencia  
        if($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('quienRecibe')!= null){
 		   $('#txtCodigo').val($(xml).find('evidenciaDTO').find('cadenaDeCustodia').find('quienRecibe').text());
        }
        
        //Codigo de la evidencia
        if($(xml).find('evidenciaDTO').find('codigoBarras') != null){
 		   $('#txtCodigo').val($(xml).find('evidenciaDTO').find('codigoBarras').text());
        }
            
      //Nombre del servidor publico
        if($(xml).find('evidenciaDTO').find('funcionario').find('nombreFuncionario')!= null){
 		   $('#solDePericialNombre').val($(xml).find('evidenciaDTO').find('funcionario').find('nombreFuncionario').text() + " " +
 				  $(xml).find('evidenciaDTO').find('funcionario').find('apellidoPaternoFuncionario').text() + " " +
 				 $(xml).find('evidenciaDTO').find('funcionario').find('apellidoMaternoFuncionario').text());
        }
        
        //Area del responsable 
        if($(xml).find('evidenciaDTO').find('funcionario').find('area').find('nombre')!= null){
 		   $('#solDePericialAreaAdmin').val($(xml).find('evidenciaDTO').find('funcionario').find('area').find('nombre').text());
        }
        
      //Puesto del responsable 
        if($(xml).find('evidenciaDTO').find('funcionario').find('puesto').find('valor')!= null){
 		   $('#solDePericialPuesto').val($(xml).find('evidenciaDTO').find('funcionario').find('puesto').find('valor').text());
        }
		
        

    }

	/* Funcion que permite acutalizar el estatus de una Evidencia
	*  En Almacen o Baja
	*/
	function actualizaEstatusDeEvidencia(estatusId)
	{
			var parametros="";
			parametros+="&estatusId="+estatusId;
			parametros+="&evidenciaId="+idEvidencia;
			
			//mandamos a llamar al action que actualiza el estatus de una solicitud
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/actualizarEstatusEvidencia.do',
				data: parametros,
				dataType: 'xml',
				success: function(xml){						
						if(parseInt($(xml).find('code').text())==0)
						{	
							var bandera=$(xml).find('boolean').text();
							if(bandera=="true")
							{
								customAlert("Se actualizó exitosamente el estatus de la evidencia");
							}
							else
							{
								customAlert("Ocurrió un error al actualizar el estatus de la evidencia");
							}							
						}
						else
						{
							cadenaCustodia="";
							customAlert("Ocurrió un error al actualizar el estatus de la evidencia");
						}
				}
			});
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
	
	      $(function(){
	        $('.timeRange').timeEntry({beforeShow: '',timeSteps:[1,1,0],ampmPrefix: ' '});
	      });
					
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
		<li id="tabAltas" ><a href="#tabsconsultaprincipal-1">Altas</a></li>
		<li id="tabPrestamos" ><a href="#tabsconsultaprincipal-2">Prestamos</a></li>
		<li id="tabES" ><a href="#tabsconsultaprincipal-3" onclick="gridDocumento()">Entradas y salidas</a></li>
		<li id="tabEslabon" ><a href="#tabsconsultaprincipal-4">Eslabón de cadenas</a></li>		
	</ul>

		<div id="tabsconsultaprincipal-1">
		
			<table width="571" border="0">
				  <tr>
				    <td width="169">Numero de caso:</td>
				    <td width="386"><input type="text" name="txtNumCaso" id="txtNumCaso" size="30" disabled="disabled"/></td>
			      </tr>
			      <tr>
				    <td width="169">Numero de expediente:</td>
				    <td width="386"><input type="text" name="txtNumExpediente" id="txtNumExpediente" size="30" disabled="disabled" /></td>
			      </tr>
			      <tr>
				    <td width="169">Numero de cadena:</td>
				    <td width="386"><input type="text" name="txtNumCadena" id="txtNumCadena" size="30"  disabled="disabled"/></td>
			      </tr>			      
			      <tr>
				    <td width="169">Cadena de custodia:</td>
				    <td width="386"><input type="text" name="txtNombreCadena" id="txtNombreCadena" size="30"  disabled="disabled"/></td>
			      </tr>
			    
				  <tr>
				    <td>Numero de evidencia:</td>
				    <td><input type="text" name="txtNumEvi" id="txtNumEvi" size="30" disabled="disabled"/></td>
			      </tr>
				  <tr>
				    <td>Nombre de evidencia:</td>
				    <td><input type="text" name="txtNombreEvi" id="txtNombreEvi" size="30" disabled="disabled"/></td>
			      </tr>
				  <tr>
				    <td>Código:</td>
				    <td><input type="text" name="txtCodigo" id="txtCodigo" size="30" disabled="disabled"/></td>
			      </tr>	
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
			      </tr>			  
				</table>
				<br>
				<input type="button" name="button" id="button" value="Generar Reporte" onclick="consultaEvidencias()" class="btn_modificar"/>
				<br/>
				<br>
				<div id="divRerporteGeneral">
                        	<table align="center" id="tablaInventarioDeEvidenciasPorCaso" width="800px"></table>
							<div id="pager1"></div>
				</div>
				
		
		</div>

			<div id="tabsconsultaprincipal-2">		
				<table width="100%">
					<tr>
						<td>
							<div id="tabsPrincipal">
								<ul>
									<li><a href="#tabsconsultaprincipal-1">Solicitud</a></li>
									<li><a href="#tabsconsultaprincipal-2">Detalle Solicitud</a></li>
									<li><a href="#tabsconsultaprincipal-3" style="display: none">Autorizar Evidencia</a></li>
								</ul>
								<div id="tabsconsultaprincipal-1">
									<fieldset style="width: 700px;">
									<legend>Responsable</legend>
									<table width="100%" border="0" height="90%">
										<tr>
											<td>
												Nombre Servidor P&uacute;blico:
											</td>
											<td>
												<input type="text" size="50" maxlength="50" id="solDePericialNombre" disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<td>
												Cargo:
											</td>
											<td>
												<input type="text" size="50" maxlength="50"	id="solDePericialPuesto"  disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<td>
												Área Administrativa:
											</td>
											<td>
												<input type="text" size="50" maxlength="50" id="solDePericialAreaAdmin" disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<td>
												Fecha Elaboración:
											</td>
											<td>
												<input type="text" size="50" maxlength="13"	id="solDePericialFecha" disabled="disabled"/>
											</td>
										</tr>
									</table>
									</fieldset>
									<fieldset style="width: 700px;">
									<legend>Datos de la Solicitud</legend>
									<table width="100%" border="0" height="90%">
										<tr>
											<td>
												Número de Expediente:
											</td>
											<td>
												<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente"  disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<td>
												Fecha inicio de préstamo:
											</td>
											<td>
												<input type="text" id="solServPericialFechaInicioPrestamo" width="50px"  disabled="disabled"/>
											</td>
										</tr>
										<tr>
											<td>
												Fecha fin de préstamo:
											</td>
											<td>
												<input type="text" id="solServPericialFechaFinPrestamo" width="50px"  disabled="disabled"/>
											</td>
										</tr>
									</table>
									</fieldset>
								</div>
								<div id="tabsconsultaprincipal-2">
									<fieldset style="width: 700px;">
									<table width="100%" border="0" height="90%">
										<tr>
											<td align="right">
												Persona Autorizada:
											</td>
											<td align="left">
												<input type="text" size="50" maxlength="50"  id="solServPericialPersonaAutorizadas" disabled="disabled"/>
											</td>
										</tr>
										<tr>
								          <td>Recomendaciones:</td>
								          <td>
								            <textarea id="areaDescripcion" cols="45" rows="5" style="width: 500px; height:200px;" disabled="disabled"></textarea>
							              </td>
								        </tr>
									</table>
									<table>
										<tr>
											<td>
												<jsp:include page="ingresarDocumentoIdentificacionView.jsp"/>
											</td>
										</tr>
									</table>
									</fieldset>
								</div>
								<div id="tabsconsultaprincipal-3">
								  	<table width="600">
								  		<tr>
								  			<td>
								  			<td>&nbsp;</td>
								                Nombre Funcionario:
								                <input type="text" class="" size="50" maxlength="50" id="solServPericialNombreFuncionario" disabled="disabled"/>
								            </td>
								  		</tr>
								  		<tr>
											<td  colspan="2" align="center">
												<input type="button" id="btnEnviarSolicitud" value="Enviar Autorizacion">
											</td>
										</tr>
								  	</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<!-- autorizarEvidencia.jsp  -->					
			</div>
			
			<div id="tabsconsultaprincipal-3">		
				<!-- Inicia seccion de documento -->				
				<div id="divGridDocumentos">
					<table id="gridDocumentos"></table>
					<div id="paginadorDocumentos"></div>
				</div>
				<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
				<input type="hidden" name="documentoId" />
				</form>
				<!-- Fin seccion de documento -->
				<br>
					<input type="button" name="button" id="button" value="Entrada" onclick="generaEstatusEvidencia(1)" class="btn_modificar" />
      				<input type="button" name="button" id="button" value="Salida" onclick="generaEstatusEvidencia(0)" class="btn_modificar"/>				
				<br>
				
				<p>&nbsp;</p>
				<fieldset>
					<legend>Informaci&oacute;n de la evidencia</legend>
					<table width="518" border="0">
					  <tr>
					    <td width="186">Ubicación Física:</td>
					    <td width="316"><textarea name="texUbicacion" id="texUbicacion" cols="45" rows="5"></textarea></td>
					  </tr>
					  <tr>
					    <td>Posición:</td>
					    <td><input type="text" name="txtPosicion" id="txtPosicion" size="48" /></td>
					  </tr>
					</table>
				</fieldset>			
				
			</div>
			<div id="tabsconsultaprincipal-4">	
			
			<div id="divRegistrarEslabon">
				  <table>
				    <tr>
				      <td colspan="2" align="right"><input type="button" value="Registrar Eslab&oacute;n de Cadena" id="btnRegEslbnCadCus" class="btn_modificar"/></td>
				    </tr>
				  </table>
					<!--  Tabla insercion eslabon de cadena de custodia -->
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
        							<input type="text" id="txtPersonaRecibeEslbn" maxlength="20" style="width: 150px;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Paterno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApPatRecibeEslbn" maxlength="20" style="width: 150px;"/>
        						</td>
        					</tr>
        					<tr>
        						<td>
        							Ap. Materno: 
        						</td>
        						<td>
        							<input type="text" id="txtPersonaApMatRecibeEslbn" maxlength="20" style="width: 150px;"/>
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
					<!-- FIN tabla insercion eslabon de cadena de custodia -->
				</div>
					
			</div>
			
			
		</div>


	
</body>
</html>