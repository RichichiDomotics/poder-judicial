<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script>

	var datosAbc = {
		id:"", nombre:"", descripcion:"", Puntos:""
	};
	
	var abcAgregados = [];

	$(document).ready(
			function() {
				
				jQuery("#tabsPeriodosAcumulacionPuntos").tabs();
				
				var dates =	$("#fechaInicio, #fechaTermino").datepicker(
						{
							//defaultDate: "+1w",
							changeMonth: true,
							numberOfMonths: 2,
							onSelect: function( selectedDate ) {
								var option = this.id == "fechaInicio" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not( this ).datepicker( "option", option, date );
													
							},
							showOn: "button",
							buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
							buttonImageOnly: true			
						}
					);
				
				jQuery("#dialog:ui-dialog" ).dialog( "destroy" );
				
				jQuery("#dialog-abc-invalido, #dialogAcumulacionAbc, #dialogDetallesABC, #dialogValidaAcumulacionAbc, #dialog-acumulacionExito").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Ok: function() {
							$( this ).dialog( "close" );
						}
					},
					resizable: false
				});
				
				jQuery("#dialogConfirmAcumulacionAbc").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Acumular: function(){
							var idsConcatenados = asociarIdsAbc();
							jQuery('#idsAbcConcatenados').val(idsConcatenados);
							document.getElementById("acumularABCForm").submit();
						},
						Cancelar: function() {
							$( this ).dialog( "close" );
						}
					},
					resizable: false
				});
				
				jQuery( "#btnAgregarAbc" ).button().click(
						function() {
							var grid = jQuery("#gridAbcAcumular");
							agregarDatoAGrid(grid, datosAbc);
							eliminarDatoGrid(jQuery("#gridAbcDisponibles"), datosAbc.id);
							abcAgregados.push(datosAbc.id);
							jQuery( "#btnAgregarAbc" ).button( "option", "disabled", true );
							sumarPuntos(grid);
						}
				);
				
				jQuery( "#btnAcumularAbc" ).button().click(
						function() {
							if (validarDatosForma()){
								var idsConcatenados = asociarIdsAbc();
								if (idsConcatenados == ""){
									jQuery("#dialogAcumulacionAbc").dialog("open");
								}else{
									jQuery("#dialogConfirmAcumulacionAbc").dialog("open");
								}	
							}else{
								jQuery("#dialogValidaAcumulacionAbc").dialog("open");
							}
						}
				);
				
				jQuery( "#btnEliminarAbc" ).button().click(
						function() {
							var grid = jQuery("#gridAbcAcumular");
							if(datosAbc.id == ""){
								jQuery( "#confirmText" ).html("Para poder eliminar un acto de buena conducta, es necesario seleccionarlo de la lista.");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar acto de buena conducta",
									buttons: {
										"Aceptar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarAbc" ).button("refresh");
										jQuery( "#btnEliminaAbc" ).removeClass('ui-state-focus');
									}
								});
							}else{
								jQuery( "#confirmText" ).html("El acto de buena conducta seleccionado ser&aacute; eliminado de la acumulaci&oacute;n,<br /> ¿Desea continuar?");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar acto de buena conducta",
									buttons: {
										"Eliminar": function() {
											if (validarAbcAgregado(datosAbc.id)){
												eliminarDatoGrid(grid, datosAbc.id);
												agregarDatoAGrid(jQuery("#gridAbcDisponibles"), datosAbc);
												eliminarAbcAgregado(datosAbc.id);
												jQuery( "#btnEliminarAbc" ).button( "option", "disabled", true );
												sumarPuntos(grid);
												jQuery( this ).dialog( "close" );	
											}else{
												jQuery("#dialog-abc-invalido").dialog("open");
											}
										},
										"Cancelar": function() {
											jQuery( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarAbc" ).button("refresh");
										jQuery( "#btnEliminarAbc" ).removeClass('ui-state-focus');
									}
								});
							}	
							
							jQuery("#dialog-confirm").dialog("open");
						}
				);
				
				jQuery("#gridAbcAcumular").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Actos de buena conducta a acumular",
					viewrecords: true,
					onSelectRow: function(id){ 
						var abcSeleccionado = jQuery("#gridAbcAcumular").jqGrid('getRowData',id); 
						copiarDatosAbc(abcSeleccionado,id);
						jQuery( "#btnAgregarAbc" ).button( "option", "disabled", true );
						jQuery( "#btnEliminarAbc" ).button( "option", "disabled", false );
					},
					ondblClickRow: function(id) {
						llenarForma(id);
					},
					gridComplete: function(id){
						sumarPuntos(jQuery(this));
					},
					sortorder: "asc",		           		
					autowidth:true,
        			shrinkToFit:true,
					colNames:['Acto de buena conducta','Descripci&oacute;n','Puntos obtenidos'], 
					colModel:[ 	{name:'nombre',index:'1' }, 
								{name:'descripcion',index:'2'}, 
								{name:'puntos', index:'3', formatter:'integer', summaryType:'sum'}
							],
					footerrow : true, 
					userDataOnFooter : true, 
					altRows : true,
					pager: jQuery('#pagerAbcAcumulados')
				}).navGrid('#pagerAbcAcumulados',{edit:false,add:false,del:false});
			
				jQuery("#gridAbcDisponibles").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Actos de buena conducta disponibles",
					viewrecords: true,
					onSelectRow: function(id){
						var abcSeleccionado = jQuery("#gridAbcDisponibles").jqGrid('getRowData',id); 
						copiarDatosAbc(abcSeleccionado,id);
						jQuery( "#btnEliminarAbc" ).button( "option", "disabled", true );
						jQuery( "#btnAgregarAbc" ).button( "option", "disabled", false );
					},
					ondblClickRow: function(id) {
						llenarForma(id);
					},
					sortorder: "asc",		           		
					autowidth:true,
	    			shrinkToFit:true,
					url:'<%=request.getContextPath()%>/llenarGridAcumulacionPuntos.do?gridID=abcDisponibles',
					colNames:['Acto de buena conducta','Descripci&oacute;n','Puntos por acumular'], 
					colModel:[ 	{name:'nombre',index:'1' }, 
								{name:'descripcion',index:'2'}, 
								{name:'puntos',index:'3'}
							],
					pager: jQuery('#pagerAbcDisponibles')
				}).navGrid('#pagerAbcDisponibles',{edit:false,add:false,del:false});
				
				jQuery("#gridPeriodosAcumulados").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Puntos acumulados por per&iacute;odo",
					viewrecords: true,
					gridComplete: function(id){
						sumarPuntosPeriodos(jQuery(this));
					},
					sortorder: "asc",		           		
					autowidth:true,
        			shrinkToFit:true,
        			url:'<%=request.getContextPath()%>/llenarGridPeriodosAcumulacion.do',
					colNames:['Per&iacute;odo','Fecha de inicio','Fecha de t&eacute;rmino','Puntos'], 
					colModel:[ 	{name:'periodo',index:'1' }, 
								{name:'fechaInicio',index:'2'}, 
								{name:'fechaTermino', index:'3'},
								{name:'puntos', index:'4', formatter:'integer', summaryType:'sum'}
							],
					footerrow : true, 
					userDataOnFooter : true, 
					altRows : true,
					pager: jQuery('#pagerPeriodosAcumulados')
				}).navGrid('#pagerPeriodosAcumulados',{edit:false,add:false,del:false});
				
				jQuery("#btnEliminarAbc").button( "option", "disabled", true );
				jQuery("#btnAgregarAbc").button( "option", "disabled", true );
				
				<% if (request.getParameter("acumulacionExitosa") != null){%>
					jQuery("#dialog-acumulacionExito" ).dialog("open");
				<%}%>
				
			}
	);
	
	function copiarDatosAbc(abcSeleccionado, id){
		datosAbc.id = id;
		datosAbc.nombre = abcSeleccionado.nombre;
		datosAbc.descripcion = abcSeleccionado.descripcion;
		datosAbc.puntos = abcSeleccionado.puntos;
	}
	
	function agregarDatoAGrid(grid, data){
		grid.jqGrid('addRowData',data.id, data);
	}	
	
	function eliminarDatoGrid(grid,id){
		grid.delRowData(id);
	}
	
	function eliminarAbcAgregado(id){
		var nuevoArreglo = [];
		for (var i=0; i<abcAgregados.length;i++){
			if (abcAgregados[i]!= id){
				nuevoArreglo.push(abcAgregados[i]);
			}
		}
		abcAgregados = nuevoArreglo;
	}
	
	function validarAbcAgregado(id){
		for (var i=0; i<abcAgregados.length;i++){
			if (abcAgregados[i] == id){
				return true;
			}
		}
		return false;
	}
	
	function asociarIdsAbc(){
		var idsConcatenados="";
		for (var i=0;i<abcAgregados.length;i++){
			if(i==(abcAgregados.length-1)){
				idsConcatenados+=abcAgregados[i];
			}else{
				idsConcatenados+=abcAgregados[i]+',';
			}
		}
		return idsConcatenados;
	}
	
	function copiarDatosDetallesAbc(abcSeleccionado, id){
		jQuery('#nombreABC').val(abcSeleccionado.nombre);
		jQuery('#descABC').val(abcSeleccionado.descripcion);
		jQuery('#puntosABC').val(abcSeleccionado.puntos);
		jQuery('#acumuladoABC').val("No");
	}
	
	function validarDatosForma(){
		if (jQuery('#fechaInicio').val() == "" ||
				jQuery('#fechaTermino').val() == "" ){
			return false;
		}else{
			return true;
		}
	}
	
	function llenarForma(id){
		GetAJAXValues(
				id,
			function(objRemoteData){
				llenandoForma(
						objRemoteData.object
				);
				jQuery("#dialogDetallesABC").dialog("open");
			}
		);		
	}
	
	function GetAJAXValues( id, fnCallback ){
		if (GetAJAXValues.Xhr){
			GetAJAXValues.Xhr.abort();
		}

		GetAJAXValues.Xhr = $.ajax({
			type: "post",
			url:'<%=request.getContextPath()%>/obtenerDetallesActoBuenaConductaPorId.do',
			data: {
				id:id
			},
			dataType: "json",

			success: function( objData ){
				fnCallback({object: objData});
			},

			error: function(){
				customAlert("No se pueden mostrar los detalles.<br /> Intente de nuevo por favor ");
			},

			complete: function(){
				GetAJAXValues.Xhr = null;
			}
		});
	}
	
	function llenandoForma(object){
			jQuery('#nombreABC').val(object.nombreActoBuenaConducta);
			jQuery('#descABC').val(object.descripcionActoBuenaConducta);
			jQuery('#puntosABC').val(object.puntosActoBuenaConducta);
			jQuery('#acumuladoABC').val(object.acumulado);
	}
	
	function sumarPuntos(grid){
		var sum = grid.jqGrid('getCol','puntos',false,'sum');
		grid.jqGrid('footerData','set',{descripcion:'Total:',puntos:sum});
		return sum;
	}
	
	function sumarPuntosPeriodos(grid){
		var sum = grid.jqGrid('getCol','puntos',false,'sum');
		grid.jqGrid('footerData','set',{fechaTermino:'Total:',puntos:sum});
		return sum;
	}
</script>
<div id="tabsPeriodosAcumulacionPuntos">
	<ul>
		<li><a href="#AcumularPuntos">Acumulaci&oacute;n de Puntos</a></li>
		<li><a href="#PeriodosAcumulados">Per&iacute;odos Acumulados</a></li>
	</ul>
	<div id="AcumularPuntos">
		<br />
		<html:form action="/acumularABC.do" styleId="acumularABCForm">
			<fieldset>
				<legend>Datos del per&iacute;odo de acumulaci&oacute;n</legend>
				<table border="0" align="center">
					<tr>
						<td colspan="2">
							<table border="0">
								<tr>
									<td align="right">Nombre del per&iacute;odo:</td>
									<td align="left">
										<html:text name="AdministrarAcumulacionPuntosForm" property="nombrePeriodo" styleId="nombreSentenciado" styleClass="texto" />
									</td>
									<td align="right">Fecha de inicio:</td>
									<td align="left">
										<html:text name="AdministrarAcumulacionPuntosForm" property="fechaInicio" styleId="fechaInicio" styleClass="texto" />
									</td>
									<td align="right">Fecha de t&eacute;rmino:</td>
									<td align="left">
										<html:text name="AdministrarAcumulacionPuntosForm" property="fechaTermino" styleId="fechaTermino" styleClass="texto" />
									</td>
								</tr>
								<tr/>
								<tr/>
								<tr>
									<td colspan="6" align="center">
										<div id="catProg2">
											<table id="gridAbcAcumular"></table>
											<div id="pagerAbcAcumulados" style="vertical-align: top;"></div>
										</div>
									</td>
									<td align="left">
										<button id="btnEliminarAbc" type="button" class="btn_buscar">Eliminar</button>
									</td>
								</tr>
								<tr>
								</tr>
								<tr/>
								<tr>
									<td colspan="6" align="center">
										<div id="catProg1">
											<table id="gridAbcDisponibles"></table>
											<div id="pagerAbcDisponibles" style="vertical-align: top;"></div>
										</div>
									</td>
									<td align="left">
										<button id="btnAgregarAbc" type="button" class="btn_buscar">Agregar</button>
									</td>
								</tr>
								<tr/>
								<tr/>
								<tr>
									<td colspan="7" align="center">
										<button id="btnAcumularAbc" type="button" class="btn_buscar">Acumular</button>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:hidden name="AdministrarAcumulacionPuntosForm" property="idsAbcConcatenados" styleId="idsAbcConcatenados" />
			</fieldset>
			<div id="dialogDetallesABC" title="Detalles del acto de buena conducta">
				<table>	
					<tr>
						<td align="right">Nombre:</td>
						<td align="left">
							<html:text name="AdministrarAcumulacionPuntosForm" property="nombreABC" styleId="nombreABC" styleClass="texto" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right">Descripci&oacute;n:</td>
						<td align="left">
							<html:textarea name="AdministrarAcumulacionPuntosForm" property="descABC" styleId="descABC" styleClass="texto" style="width: 99%;" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">Puntos otorgados:</td>
						<td align="left">
							<html:text name="AdministrarAcumulacionPuntosForm" property="puntosABC" styleId="puntosABC" styleClass="texto" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right">Acumulado:</td>
						<td align="left">
							<html:text name="AdministrarAcumulacionPuntosForm" property="acumuladoABC" styleId="acumuladoABC" styleClass="texto" readonly="true" />
						</td>
					</tr>
				</table>
			</div>
			<div id="dialog-abc-invalido" title="Acto de buena conducta inv&aacute;lido">
				<p>
					El Acto de buena conducta no se puede eliminar debido a que se hab&iacute;a
					acumulado de manera previa. <br /> 
					S&oacute;lo se permiten eliminar los actos de buena conducta que hayan sido 
					asociados en esta ejecuci&oacute;n.
				</p>
			</div>
			<div id="dialogAcumulacionAbc" title="Error de validaci&oacute;n">
				<p>
					Para acumular los actos de buena conducta es necesario seleccionar al menos un acto de buena conducta.<br/>
					Por favor agregue un acto de la lista de actos de buena conducta disponibles.
				</p>
			</div>
			<div id="dialogValidaAcumulacionAbc" title="Error de validaci&oacute;n">
				<p>
					Para acumular los actos de buena conducta es necesario seleccionar la fecha de inicio <br/>
					y la fecha de t&eacute;rmino del per&iacute;odo de acumulaci&oacute;n. <br/>
					Por favor asegurese de haber seleccionado ambas fechas.
				</p>
			</div>
			<div id="dialogConfirmAcumulacionAbc" title="Confirmar acumulaci&oacute;n">
				<p>
					Se van a acumular los puntos otorgados de los actos de buena conducta seleccionados, <br/>
					lo cual actualizar&aacute; la cantidad de puntos obtenidos para la sentencia. <br/>
					¿Desea acumular los puntos?
				</p>
			</div>
			<div id="dialog-confirm" title="">
				<p>
					<span id="confirmText"></span>
				</p>
			</div>
			<div id="dialog-acumulacionExito" title="Acumulaci&oacute;n exitosa">
				<p>Los puntos otorgados por actos de buena conducta fueron acumulados con &eacute;xito.</p>
			</div>
		</html:form>
	</div>
	<div id="PeriodosAcumulados">
		<fieldset>
			<legend>Per&iacute;odos de acumulaci&oacute;n</legend>
			<table border="0" align="center">
				<tr>
					<td colspan="6">
						<div id="catProg3">
							<table id="gridPeriodosAcumulados"></table>
							<div id="pagerPeriodosAcumulados" style="vertical-align: top;"></div>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</div>