<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
            $.jgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;

    	//var contDelitosGravesDos=0;

    	/***** Ejecutamos funciones para setear la pagina*****/
		$(document).ready(function(){
						
			$( "#dialogTres:ui-dialog" ).dialog( "destroy" );
			
			$("#btnRelacionarRDPD").hide();
			$("#btnAnulaRelacionDelDel").hide();
			
		});
    	
		function validaSeleccion(){			
			if($("#cbxDelitosRDPD option:selected").val()!=-1){    		
    			$("#btnRelacionarRDPD").show();    			
    		}
    		else{
    			$("#btnRelacionarRDPD").hide();
    			$("#btnAnulaRelacionDelDel").hide();
    		}
			consultaDelitosPRRDPD();
    	}


		/*Funcion para lanzar el popup que relacionara 
		*un delito por delito a un probable responsable
		*/
		function abrePopupRelacionarDelitosPRRDPD()
		{
			$( "#dialogTres:ui-dialog" ).dialog();
			$( "#dialogTres:ui-dialog" ).dialog( "destroy" );
			
			//cargamos el combo con los probables responsables
			cargaComboPRExpedientesRDPD();
			
			//RE USO - cargamos el combo con las formas de participacion del involucrado
			cargaComboFormaParticipacionRDPD();
			//RE USO - cargamos el combo de victimas del expediente
			cargaComboVictimasRDPD();
			
			//cargamos el combo de clasificacion delito
			cargaComboClasificacionDelitoRDPD();
			//cargamos el combo del lugar de delito
			cargaComboLugarDelitoRDPD();
			//cargamos el combo de modalidad delito
			cargaComboModalidadDelitoRDPD();
			//cargamos el combo de modus delito
			cargaComboModusDelitoRDPD();
			//cargamos el combo de causa delito
			cargaComboCausaDelitoRDPD();
			
			//generamos el Dialogo
			$( "#dialogTres-confirm" ).dialog({
				resizable: false,
				height:325,
				width:820,
				modal: true,
				buttons: {
					"Guardar": function() {
						$( this ).dialog( "close" );
						$( "#dialogTres:ui-dialog" ).dialog( "destroy" );
						RelacionarDelitoRDPD();
					},
					"Cancelar": function() {
						$( this ).dialog( "close" );
						$( "#dialogTres:ui-dialog" ).dialog( "destroy" );
					}
				}
			});
			$( ".ui-icon-closethick,.ui-dialog-titlebar-close" ).hide();
		}
		
		//cargamos el combo de victimas del expediente
		function cargaComboVictimasRDPD()
		{
			$("#cbxVictimasExpRDPD").empty();
			$('#cbxVictimasExpRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=VICTIMA',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('involucradoDTO').each(function(){
						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
						nombreCompleto+=$(this).find('nombresDemograficoDTO').find('apellidoPaterno').text()+" ";
						nombreCompleto+=$(this).find('nombresDemograficoDTO').find('apellidoMaterno').text()+" ";
						if(nombreCompleto==null || nombreCompleto=="null" || nombreCompleto=="" || nombreCompleto=="   "){
							nombreCompleto="An�nimo";
						}
						$('#cbxVictimasExpRDPD').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
					});
				}
			});		
		}
		
		//cargamos el combo con las formas de participacion del involucrado
		function cargaComboFormaParticipacionRDPD()
		{
			$("#cbxFormasParticipacionRDPD").empty();
			$('#cbxFormasParticipacionRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModoParticipacionDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxFormasParticipacionRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}
		
		/*
		*Funcion para cargar el combo con los probables responsables del Expediente
		*/
		function cargaComboPRExpedientesRDPD()
		{
			$('#cbxProbableResponsableExpRDPD').empty();
			$('#cbxProbableResponsableExpRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp+'&calidadInvolucrado=PROBABLE_RESPONSABLE',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('involucradoDTO').each(function(){
						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
						nombreCompleto+=$(this).find('apellidoPaterno').text()+" ";
						nombreCompleto+=$(this).find('apellidoMaterno').text()+" ";
						$('#cbxProbableResponsableExpRDPD').append('<option value="' + $(this).find('elementoId').text() + '">' + nombreCompleto+ '</option>');
					});
				}
			});		
		}
		
		//cargamos el combo con los delitos del expediente
		function cargaComboDelitosAAsociarRDPD()
		{			
			$("#btnRelacionarRDPD").hide();
			$("#btnAnulaRelacionDelDel").hide();
			

			$('#cbxDelitosRDPD').empty();
			//seteamos el listener para el boton de relacionar los delitos
			$("#btnRelacionarRDPD").click(abrePopupRelacionarDelitosPRRDPD);
			$("#trGridDelitosPRRDPD").hide();
			
			$( "#dialogTres:ui-dialog" ).dialog( "destroy" );			
			$('#cbxDelitosRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultaDelitoPorExpediente.do',
				data: 'idNumeroExpediente='+idNumeroExpedienteOp,
				dataType: 'xml',
				async: false,
				success: function(xml){
					var contaDelitos=0;
					$(xml).find('DelitoDTO').each(function(){
						$('#cbxDelitosRDPD').append('<option value="' + $(this).find('delitoId').text() + '">' + $(this).find('nombreDelito').text() + '</option>');
						contaDelitos++;
					});
					if(contaDelitos==0)
					{
						alertDinamico("Necesita delitos para poderlos relacionar.");
					}
				}
			});	
		}
		var banderaRDPD=0;
		//funcion para mandar consultar los delitos de un probable responsable 
        function consultaDelitosPRRDPD()
        {
        	var idDelito=$("#cbxDelitosRDPD option:selected").val();
        	//revisamos que se haya seleccionado un probable responsable
        	if(idDelito!=-1)
        	{
        		$("#trGridDelitosPRRDPD").show();
        		if(banderaRDPD==0)
        		{ 	        		
	        		//var params="idPR="+idPR+"&idExpediente="+idExpedienteop;
	        		//cargamos el grid con los delitos del PR
		        	jQuery("#gridCatDelitosRDPD").jqGrid({ 
						url:'<%= request.getContextPath()%>/CargarProbRespsAsociadosAlDelito.do?idDelito='+idDelito +'&idExpediente='+idExpedienteop +'', 
						datatype: "xml",
						colNames:['Probable Responsable','Forma de Participaci�n','V�ctima','Ids'], 
						colModel:[ 	{name:'ProbableResp',index:'probableresp', width:250}, 
									{name:'FormaParticipacion',index:'formaParticipacion',width:300},
									{name:'Victima',index:'victima',width:250},
									{name:'DelitoPersonaId',index:'idPersonaDelito',width:250,hidden: true}
								],
						//pager: jQuery('#pagerADPD'),
						rowNum:0,
						rowList:[0,0,0],
						autowidth: true,
						caption:"LISTA DE RELACIONES",
						sortname: 'Clave',
						multiselect: true,
						viewrecords: true,
						id: 'divgridIzq',
						sortorder: "desc",
						loadComplete: function(){		        		

							var longitudTabla = jQuery("#gridCatDelitosRDPD").getDataIDs();
							var elementosListaDelitos = longitudTabla.length;							
							
				       		if(elementosListaDelitos>0){
								$("#btnAnulaRelacionDelDel").show();
							}
							else{
				    			$("#btnAnulaRelacionDelDel").hide();
							}			       		
						}
					}).navGrid('#pagerADPD',{edit:false,add:false,del:false});
		        	$("#gridCatDelitosRDPD").trigger("reloadGrid");
		        	banderaRDPD=1;
        		}
        		else
        		{
        			jQuery("#gridCatDelitosRDPD").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/CargarProbRespsAsociadosAlDelito.do?idDelito='+idDelito +'&idExpediente='+idExpedienteop +'',datatype: "xml" });
    				$("#gridCatDelitosRDPD").trigger("reloadGrid");
        		}
        	}
        	else
        	{
        		//ocultamos el grid cuando no se selecciono un PR
        		$("#trGridDelitosPRRDPD").hide();
        	}
        }
		
        /*
		*Funcion que hara el llamado a BD para guardar el nuevo delito relacionado
		*por persona a BD y posteriormente agregara un renglon al grid
		*/
		function RelacionarDelitoRDPD()
		{
			var idPR=$("#cbxProbableResponsableExpRDPD option:selected").val();
			var idDelito=$("#cbxDelitosRDPD option:selected").val();
			var idVictima=$("#cbxVictimasExpRDPD option:selected").val();
			var idFormaP=$("#cbxFormasParticipacionRDPD option:selected").val();
			
			var idClasificacion=$("#cbxClasificacionRDPD option:selected").val();
			var idLugar=$("#cbxLugarRDPD option:selected").val();
			var idModalidad=$("#cbxModalidadRDPD option:selected").val();
			var idModus=$("#cbxModusRDPD option:selected").val();
			var idCausa=$("#cbxCausaRDPD option:selected").val();
			
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
								async: false,
								success: function(xml){
									if(parseInt($(xml).find('code').text())==0)
						    		{
										$(xml).find('Asociacion').each(function(){
											alertDinamico("Se asocio exitosamente el Probable Responsable al Delito.");
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
				alertDinamico("Debe seleccionar un probable responsable, un modo de participaci�n y una v�ctima.");
			}
		}
        
		//-----------------------------------------------
		//cargamos el combo de clasificacion del delito
		function cargaComboClasificacionDelitoRDPD()
		{
			$("#cbxClasificacionRDPD").empty();
			$('#cbxClasificacionRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoClasificacionDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxClasificacionRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de lugar del delito
		function cargaComboLugarDelitoRDPD()
		{
			$("#cbxLugarRDPD").empty();
			$('#cbxLugarRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoLugarDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxLugarRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Modalidad del delito
		function cargaComboModalidadDelitoRDPD()
		{
			$("#cbxModalidadRDPD").empty();
			$('#cbxModalidadRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModalidadDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxModalidadRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Modus del delito
		function cargaComboModusDelitoRDPD()
		{
			$("#cbxModusRDPD").empty();
			$('#cbxModusRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoModusDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxModusRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}

		//cargamos el combo de Causa del delito
		function cargaComboCausaDelitoRDPD()
		{
			$("#cbxCausaRDPD").empty();
			$('#cbxCausaRDPD').append('<option value="-1" selected="selected">-Seleccione-</option>');
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoCausaDelito.do',
				dataType: 'xml',
				async: false,
				success: function(xml){
					$(xml).find('ModoParticipacionDelito').each(function(){
						$('#cbxCausaRDPD').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
					});
				}
			});
		}
        
		function anularRelacionDelitoPersona()
        {
			var idsRelacionesSeleccionados = jQuery("#gridCatDelitosRDPD").jqGrid('getGridParam','selarrrow');
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
							consultaDelitosPRRDPD();
							consultaDelitosPRRDPPV();
						}				    		
			    		else
			    			alertDinamico("No se anularon con �xito la(s) relaci\u00F3n(es)")
					}
				});
			}
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

	<table border="0"  width="1050px" id="tblRelacionarDelitoPorDelito">
		<tr>
			<td height="20" colspan="4" align="left" >
				Seleccione el Delito : 				
				<select id="cbxDelitosRDPD" onchange="validaSeleccion();">
					<option value="-1" selected="selected">-Seleccione-</option>
				</select>
			</td>
		</tr>
		<tr id="trGridDelitosPRRDPD">
			<td colspan="3">
				<table id="gridCatDelitosRDPD"></table>
				<div id="pagerADPD"></div>
				<br/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>			
			<td align="left" valign="top">
				<input type="button" value="Relacionar" id="btnRelacionarRDPD" class="btn_modificar"/>
			</td>
			<td align="left" valign="top">
				<input type="button" id="btnAnulaRelacionDelDel" value="Anular relaci�n Delito - Persona" onclick="anularRelacionDelitoPersona();" class="btn_grande"/>
			</td>
		</tr>
	</table>
	

