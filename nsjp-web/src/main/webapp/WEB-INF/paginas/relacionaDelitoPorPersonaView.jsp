<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
            $.jgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;

    	var contDelitosGraves=0;
    	var idProbResAviso;
    	var idDetencion;
        var idNumeroExpedienteOp="";


    	/***** Ejecutamos funciones para setear la pagina*****/
		$(document).ready(function(){		
			
			$("#dialogDos:ui-dialog").dialog( "destroy" );
			
			$("#btnRelacionarRDPPV").hide();
			$("#btnAnulaRelacionDelPer").hide();
		});
    	
	
    	function validaSeleccion1(){
    		    		
    		if($("#cbxProbableResponsableRDPPV option:selected").val()!=-1){
    			$("#trGridDelitosPRRDPPV").show();
    			$("#btnRelacionarRDPPV").show();    			
    			consultaDelitosPRRDPPV();
    		}
    		else{    			
    			$("#trGridDelitosPRRDPPV").hide();
    			$("#btnRelacionarRDPPV").hide();
    			$("#btnAnulaRelacionDelPer").hide();
    			$("#muestraDatosDetencion1").hide();
    			$("#muestraDatosDetencion2").hide();
    		}
    	}

		/*Funcion para lanzar el popup que relacionara 
		*un delito al probable responsable seleccionado
		*/
		function abrePopupRelacionarDelitosPRRDPPV()
		{
			//cargamos el combo con los delitos del expediente
			cargaComboDelitosExpedientesRDPPV();
			//cargamos el combo con las formas de participacion del involucrado
			cargaComboFormaParticipacionRDPPV();
			//cargamos el combo de victimas del expediente
			cargaComboVictimasRDPPV();
			
			//cargamos el combo de clasificacion delito
			cargaComboClasificacionDelitoRDPPV();
			//cargamos el combo del lugar de delito
			cargaComboLugarDelitoRDPPV();
			//cargamos el combo de modalidad delito
			cargaComboModalidadDelitoRDPPV();
			//cargamos el combo de modus delito
			cargaComboModusDelitoRDPPV();
			//cargamos el combo de causa delito
			cargaComboCausaDelitoRDPPV();
			
			//generamos el Dialogo
			$( "#dialogDos-confirm" ).dialog({
				resizable: false,
				height:325,
				width:820,
				modal: true,
				buttons: {
					"Guardar": function() {
						$( this ).dialog( "close" );
						$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
						RelacionarDelitoRDPPV();
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialogDos:ui-dialog" ).dialog( "destroy" );
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}
		
		/*
		*Funcion que hara el llamado a BD para guardar el nuevo delito relacionado
		*por persona a BD y posteriormente agregara un renglon al grid
		*/
		function RelacionarDelitoRDPPV()
		{
			var idPR=$("#cbxProbableResponsableRDPPV option:selected").val();
			var idDelito=$("#cbxDelitosExpRDPPV option:selected").val();
			var idVictima=$("#cbxVictimasExpRDPPV option:selected").val();
			var idFormaP=$("#cbxFormasParticipacionRDPPV option:selected").val();
			
			var idClasificacion=$("#cbxClasificacionRDPPV option:selected").val();
			var idLugar=$("#cbxLugarRDPPV option:selected").val();
			var idModalidad=$("#cbxModalidadRDPPV option:selected").val();
			var idModus=$("#cbxModusRDPPV option:selected").val();
			var idCausa=$("#cbxCausaRDPPV option:selected").val();
			
			if(parseInt(idPR)!=-1 && parseInt(idDelito)!=-1 && parseInt(idVictima)!=-1 && parseInt(idFormaP)!=-1  )
			{
				
				//mandamos guardar a BD
				$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/ExisteRelacionProbableResponsableVictimaDelito.do',//verifica si existe la relacion en BD
					data: 'idPR='+idPR+'&idDelito='+idDelito+'&idVictima='+idVictima+'&idFormaP='+idFormaP,
					dataType: 'xml',
					async: true,
					success: function(xml){
						if(parseInt($(xml).find('bandera').text())==0)
			    		{
							//mandamos guardar a BD
							$.ajax({
								type: 'POST',
								url: '<%= request.getContextPath()%>/AsociarDelitoProbableResponsable.do',//guardar a BD la nueva relacion
								data: 'idPR='+idPR+'&idDelito='+idDelito+'&idVictima='+idVictima+'&idFormaP='+idFormaP+'&idAsociacion=0'+'&idClasificacion='+idClasificacion+'&idLugar='+idLugar+'&idModalidad='+idModalidad+'&idModus='+idModus+'&idCausa='+idCausa,
								dataType: 'xml',
								async: true,
								success: function(xml){
									if(parseInt($(xml).find('code').text())==0)
						    		{
										$(xml).find('Asociacion').each(function(){
											alertDinamico("Se asocio exitosamente el Delito al Probable Responsable.");
											//$('#cbxDelitosExpRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
											
										});
										consultaDelitosPRRDPPV();
										consultaDelitosPRRDPD();
						    		}
								}
							});	
			    		}
						else {
							alertDinamico("Ya existe esta relaci�n. Favor de verificar.");
						}
					}
				});
				
			}
			else
			{
				alertDinamico("Debe seleccionar un delito, un modo de participaci�n y una v�ctima.");
			}
		}
		
		
		//cargamos el combo con los delitos del expediente
		function cargaComboProbableResponsableRDPPV()
		{
			//idNumeroExpedienteOp
			$('#cbxProbableResponsableRDPPV').empty();
			//seteamos el listener para el combo de PR en relacion de delitos por persona
	       // $("#cbxProbableResponsableRDPPV").change(consultaDelitosPRRDPPV);
	        //seteamos el listener para el boton de relacionar los delitos
			$("#btnRelacionarRDPPV").click(abrePopupRelacionarDelitosPRRDPPV);
			$("#trGridDelitosPRRDPPV").hide();
			$("#muestraDatosDetencion1").hide();
			$("#muestraDatosDetencion2").hide();
            idNumeroExpedienteOp = '<%= request.getParameter("idNumeroExpedienteop") %>';
			
			$( "#dialogDos:ui-dialog" ).dialog( "destroy" );			
			$('#cbxProbableResponsableRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=PROBABLE_RESPONSABLE',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaProbResps=0;
					$(xml).find('involucradoDTO').each(function(){
						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').first().text()+" ";
						nombreCompleto+=$(this).find('apellidoPaterno').first().text()+" ";
						nombreCompleto+=$(this).find('apellidoMaterno').first().text()+" ";
						$('#cbxProbableResponsableRDPPV').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
						contaProbResps++;
												
					});
					if(contaProbResps==0)
					{
						alertDinamico("Necesita un probable responsable para poder relacionar un delito.");
					}
				}
			});		
		}

		//cargamos el combo con los delitos del expediente
		function cargaComboProbableResponsableRDPPVDelito(){
			//oculta jsp que muestra los datos de detencion
			$('#muestraDatosDetencion1').hide();
			$('#muestraDatosDetencion2').hide();
			//idNumeroExpedienteOp
			$('#cbxProbableResponsableRDPPV').empty();
			
	        //seteamos el listener para el boton de relacionar los delitos
			$("#btnRelacionarRDPPV").click(abrePopupRelacionarDelitosPRRDPPV);
			$("#trGridDelitosPRRDPPV").hide();
			$("#muestraDatosDetencion1").hide();
			$("#muestraDatosDetencion2").hide();
			
			$( "#dialogDos:ui-dialog" ).dialog( "destroy" );			
			$('#cbxProbableResponsableRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=PROBABLE_RESPONSABLE',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaProbResps=0;
					$(xml).find('involucradoDTO').each(function(){
						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
						nombreCompleto+=$(this).find('apellidoPaterno').text()+" ";
						nombreCompleto+=$(this).find('apellidoMaterno').text()+" ";
						$('#cbxProbableResponsableRDPPV').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
						contaProbResps++;
					});
				}
			});		
		}
		
		//cargamos el combo con los delitos del expediente
		function cargaComboDelitosExpedientesRDPPV()
		{
			$("#cbxDelitosExpRDPPV").empty();
			$('#cbxDelitosExpRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaDelitoPorExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp,
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('DelitoDTO').each(function(){
						$('#cbxDelitosExpRDPPV').append('<option value="' + $(this).find('delitoId').text() + '">' + $(this).find('nombreDelito').text() + '</option>');
					});
				}
			});
		}
		//cargamos el combo con las formas de participacion del involucrado
		function cargaComboFormaParticipacionRDPPV()
		{
			$("#cbxFormasParticipacionRDPPV").empty();
			$('#cbxFormasParticipacionRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModoParticipacionDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxFormasParticipacionRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}
		
		//cargamos el combo de victimas del expediente
		function cargaComboVictimasRDPPV()
		{
			$("#cbxVictimasExpRDPPV").empty();
			$('#cbxVictimasExpRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=VICTIMA',
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaProbResps=0;
					$(xml).find('involucradoDTO').each(function(){
						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
						nombreCompleto+=$(this).find('nombresDemograficoDTO').find('apellidoPaterno').text()+" ";
						nombreCompleto+=$(this).find('nombresDemograficoDTO').find('apellidoMaterno').text()+" ";
						if(nombreCompleto==null || nombreCompleto=="null" || nombreCompleto=="" || nombreCompleto=="   "){
							nombreCompleto="An�nimo";
						}
						$('#cbxVictimasExpRDPPV').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
						contaProbResps++;
					});
					if(contaProbResps==0)
					{
						alertDinamico("Necesita una v�ctima para poder relacionar un delito.");
					}
				}
			});		
		}
		
		//-----------------------------------------------
		//cargamos el combo de clasificacion del delito
		function cargaComboClasificacionDelitoRDPPV()
		{
			$("#cbxClasificacionRDPPV").empty();
			$('#cbxClasificacionRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoClasificacionDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxClasificacionRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de lugar del delito
		function cargaComboLugarDelitoRDPPV()
		{
			$("#cbxLugarRDPPV").empty();
			$('#cbxLugarRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoLugarDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxLugarRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Modalidad del delito
		function cargaComboModalidadDelitoRDPPV()
		{
			$("#cbxModalidadRDPPV").empty();
			$('#cbxModalidadRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModalidadDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxModalidadRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Modus del delito
		function cargaComboModusDelitoRDPPV()
		{
			$("#cbxModusRDPPV").empty();
			$('#cbxModusRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModusDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxModusRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Causa del delito
		function cargaComboCausaDelitoRDPPV()
		{
			$("#cbxCausaRDPPV").empty();
			$('#cbxCausaRDPPV').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoCausaDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxCausaRDPPV').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}
		
		var banderaPV=0;
        //funcion para mandar consultar los delitos de un probable responsable 
        function consultaDelitosPRRDPPV(){
        	var idPRval=$("#cbxProbableResponsableRDPPV option:selected").val();        
        	//revisamos que se haya seleccionado un probable responsable
        	if(parseInt(idPRval)!=-1)
        	{
        		if(banderaPV==0)
        		{
        		$("#trGridDelitosPRRDPPV").show();
        		//var params="idPR="+idPR+"&idExpediente="+idExpedienteop;
        		//cargamos el grid con los delitos del PR
	        	jQuery("#gridCatDelitosRDPPV").jqGrid({ 
					url:'<%= request.getContextPath()%>/CargarDelitoAsociadosInvolucrado.do?idPR='+idPRval +'&idExpediente='+idExpedienteop+'', 
					datatype: "xml",
					colNames:['Delito','Forma de Participaci�n','V�ctima'], 
					colModel:[ 	{name:'Delito',index:'delito', width:250}, 
								{name:'FormaParticipacion',index:'formaParticipacion',width:550},
								{name:'Victima',index:'victima',width:250},							
								
							],
					//pager: jQuery('#pager1'),
					rowNum:0,
					rowList:[0,0,0],
					autowidth: true,
					caption:"LISTA DE DELITOS",
					sortname: 'Clave',
					multiselect: true,
					viewrecords: true,
					id: 'divgridIzq',
					sortorder: "desc",
					loadComplete: function(){		        		

						var longitudTabla = jQuery("#gridCatDelitosRDPPV").getDataIDs();
						var elementosListaDelitos = longitudTabla.length;

			       		if(elementosListaDelitos>0){
							$("#btnAnulaRelacionDelPer").show();
						}
						else{
							$("#btnAnulaRelacionDelPer").hide();
						}			       		
					}
				}).navGrid('#pager1',{edit:false,add:false,del:false});
	        	$("#gridCatDelitosRDPPV").trigger("reloadGrid");
	        	banderaPV=1;
        		}
        		else
        		{
        			jQuery("#gridCatDelitosRDPPV").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/CargarDelitoAsociadosInvolucrado.do?idPR='+idPRval +'&idExpediente='+idExpedienteop+'',datatype: "xml" });
    				$("#gridCatDelitosRDPPV").trigger("reloadGrid");
    				$("#trGridDelitosPRRDPPV").show();
        		}
        	}
        	else
        	{
        		//ocultamos el grid cuando no se selecciono un PR
        		$("#trGridDelitosPRRDPPV").hide();
        		$('#muestraDatosDetencion1').hide();
        		$('#muestraDatosDetencion2').hide();
        	}
        	consultaDetencion(idPRval);
        }

        function consultaDetencion(idPRval){
        	$('#muestraDatosDetencion1').hide();
        	$('#muestraDatosDetencion2').hide();
            	 idProbResAviso = idPRval;
	   			$.ajax({
    		    	  type: 'POST',
    		    	  url:  '<%= request.getContextPath()%>/consultarInvolucrado.do',
    		    	  data: 'idInvolucrado='+idProbResAviso,
    		    	  dataType: 'xml',
    		    	  async: false,
    		    	  success: function(xml){

			   			var arrayIds = jQuery("#gridCatDelitosRDPPV").getDataIDs();
						if(arrayIds.length>0){
							 $('#btnSolicitarDefensor').show();  							 							 
						}

						idDetencion = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('detencionId').first().text();
						
    		    		var avisoDetencion = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('avisoDetencion').find('documentoId').first().text()!= "";
    		    		  //$(xml).find('involucradoDTO').find('nombresDemograficoDTO').find('').
        		    	      			    		
    		    		  if($(xml).find('involucradoDTO').find('esDetenido').first().text()){
    		    			  $('#btnSolicitarDefensor').attr("disabled","");
    		    		 	  pintaDatosTiempoLapso(xml);
    		    			  bloqueaCamposTiempoLapso(1);
    		    			  bloqueaCamposTiempoLapso(0);
    		    			  $('#muestraDatosDetencion1').show();  
    		    			  $('#muestraDatosDetencion2').show();

    		    			  if(avisoDetencion){
        		    			   $('#btnSolicitarDefensor').attr("disabled","disabled");
        		    			   var fecha = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('avisoDetencion').find('fechaCreacion').text();
        		    			   var strfecha = obtenerFecha(fecha);   
        		    			   var strhora = obtenerHora(fecha); 	
        		    			   $('#fechaSolicitud').show();
        		    		       $('#horaSolicitud').show(); 
        		    		       $('#fechaSolicitudDefensor').val(strfecha);
        		    		       $('#horaSolicitudDefensor').val(strhora);
         		    			  }  		    			    			    		  
    			    		  }
    		    		  }
    		    });


        }
    			
        
        function anularRelacionDelitoPersonaPersona()
        {
			var idsRelacionesSeleccionados = jQuery("#gridCatDelitosRDPPV").jqGrid('getGridParam','selarrrow');
			if(idsRelacionesSeleccionados.length>0)
			{
			//Guardamos la relacion
				$.ajax({
					type: 'POST',		
		    		url: '<%= request.getContextPath()%>/anularRelacionDelitoPersona.do?idsRelacionesSeleccionados=' + idsRelacionesSeleccionados + '',
					data: '',
					dataType: 'xml',
					async: false,
					success: function(xml){
						if(parseInt($(xml).find('code').text())==0 && parseInt($(xml).find('bandera').text())==1){
							alertDinamico("Se anularon con �xito la(s) relaci\u00F3n(es)");
							consultaDelitosPRRDPPV();
							consultaDelitosPRRDPD();
						}				    		
			    		else
			    			alertDinamico("No se anularon con �xito la(s) relaci\u00F3n(es)");
					}
				});
			}
        }

        function enviaAvisoDetencion(){
			var params = '';
			params += 'idIndividuo='+idProbResAviso;
			params += '&numeroExpediente='+numeroExpediente;
			params += '&idDetencion='+ idDetencion;
						
			//Datos fechas
			datosPestania = recuperaDatosTiempoLapso();
			params += datosPestania;
			
			$.ajax({								
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/enviarAvisosDetencion.do',
		    	  data: params,				
		    	  dataType: 'xml',
		    	  success: function(xml){
		    		  alertDinamico("Defensor Solicitado");
					$('#btnSolicitarDefensor').attr("disabled","disabled");
		    		
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

	<table border="0"  width="1050px" id="tblRelacionarDelitoPorPersona">
		<tr>
			<td height="20" colspan="5" align="left" >
				<!-- <input type="button" id="btnGuardarDelitosAg" value="Guardar" onclick="guardarDelitosAgraviadosExp();"/> -->				
					<bean:message key="selProbableResponsable"/>: 
				<select id="cbxProbableResponsableRDPPV" onchange="validaSeleccion1();">
					<option value="-1" selected="selected">-Seleccione-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td id="trGridDelitosPRRDPPV" colspan="3">
				<table id="gridCatDelitosRDPPV"></table>
				<div id="pager1"></div>
				<br/>				
			</td>
			<!-- <td id="muestraDatosDetencion" style="display: none"> -->
		  		<td id="muestraDatosDetencion1" align="left" ><jsp:include page="ingresarTiempoLapsoDetencion.jsp"></jsp:include></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>	
			<td align="left" valign="top">
				<input type="button" value="Relacionar" id="btnRelacionarRDPPV" class="btn_modificar"/>
			</td>
			<td align="left" valign="top">
				<input type="button" id="btnAnulaRelacionDelPer" value="Anular relaci�n Delito - Persona" onclick="anularRelacionDelitoPersonaPersona();" class="btn_grande"/>
			</td>
			<td>&nbsp;</td>
			<td id="muestraDatosDetencion2" align="center" >
				<input type="button" id="btnSolicitarDefensor" value="Solicitar Defensor" style="display: none" class="btn_mediano"/>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
	 $('#btnSolicitarDefensor').click(enviaAvisoDetencion); 	 
	</script>

