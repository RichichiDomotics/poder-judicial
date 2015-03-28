<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script type="text/javascript">

	var datosPrograma = {
		id:"", Tipo:"", Nombre:"", FechaInicio:"", FechaTermino:"", Puntos:""
	};
	
	var programasAgregados = [];

	$(document).ready(
			function() {
				
				jQuery("#tabsAsignacionRS").tabs();
				
				jQuery("#gridCatProgramas").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Programas Disponibles",
					viewrecords: true,
					onSelectRow: function(id){ 
						var programaSeleccionado = jQuery("#gridCatProgramas").jqGrid('getRowData',id); 
						copiarDatosPrograma(programaSeleccionado,id);
						jQuery( "#btnEliminarPrograma" ).button( "option", "disabled", true );
						jQuery( "#btnAsociarPrograma" ).button( "option", "disabled", false );
					},
					ondblClickRow: function(id) {
						llenarForma("programasDisponibles",id);
					},
					sortorder: "asc",		           		
					autowidth:true,
        			shrinkToFit:true,
					url:'<%=request.getContextPath()%>/llenarGridAsignacionPrograma.do?gridID=programasDisponibles',
					colNames:['Tipo','Nombre','Fecha Inicio','Fecha Termino', 'Puntos'], 
					colModel:[ 	{name:'Tipo',index:'1' }, 
								{name:'Nombre',index:'2'}, 
								{name:'FechaInicio',index:'3'}, 
								{name:'FechaTermino',index:'4'},
								{name:'Puntos',index:'5'}
							],
					pager: jQuery('#pagerCatProgramas')
				}).navGrid('#pagerCatProgramas',{edit:false,add:false,del:false});
			
				jQuery("#gridProgramas").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Programas Asignados",
					viewrecords: true,
					onSelectRow: function(id){
						var programaSeleccionado = jQuery("#gridProgramas").jqGrid('getRowData',id); 
						copiarDatosPrograma(programaSeleccionado, id);
						jQuery( "#btnEliminarPrograma" ).button( "option", "disabled", false );
						jQuery( "#btnAsociarPrograma" ).button( "option", "disabled", true );
					},
					ondblClickRow: function(id) {
						llenarForma("programasAsignados",id);
					},
					sortorder: "asc",		           		
					autowidth:true,
	    			shrinkToFit:true,
					url:'<%=request.getContextPath()%>/llenarGridAsignacionPrograma.do?gridID=programasAsignados',
					colNames:['Tipo','Nombre','Fecha Inicio','Fecha Termino', 'Puntos'], 
					colModel:[ 	{name:'Tipo',index:'1' }, 
								{name:'Nombre',index:'2'}, 
								{name:'FechaInicio',index:'3'}, 
								{name:'FechaTermino',index:'4'},
								{name:'Puntos',index:'5'}
							],
					pager: jQuery('#pagerProgramas')
				}).navGrid('#pagerProgramas',{edit:false,add:false,del:false});
				
				jQuery("#dialog:ui-dialog" ).dialog( "destroy" );
				
				jQuery("#dialog-detalles-programa").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Ok: function() {
							$( this ).dialog( "close" );
							jQuery('.detalleCursos').empty();
							jQuery('.detalleTrabajos').empty();
						}
					},
					resizable: false
				});
				
				jQuery("#dialog-cambios-programa").dialog({
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
				
				jQuery("#dialog-programa-invalido").dialog({
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
				
				jQuery("#dialog-asociarCursoExito").dialog({
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
				
				jQuery( "#btnAsociarPrograma" ).button().click(
						function() {
							var grid = jQuery("#gridProgramas");
							agregarDatoAGrid(grid, datosPrograma);
							eliminarDatoGrid(jQuery("#gridCatProgramas"), datosPrograma.id);
							programasAgregados.push(datosPrograma.id);
							jQuery( "#btnAsociarPrograma" ).button( "option", "disabled", true );
						}
				);
				
				jQuery( "#btnRegistrarProgramas" ).button().click(
						function() {
							var idsConcatenados = asociarIdsProgramas();
							if (idsConcatenados == ""){
								jQuery("#dialog-cambios-programa").dialog("open");
							}else{
								jQuery('#idsProgramasConcatenados').val(idsConcatenados);
								document.getElementById("guardarAsocProgForm").submit();
							}
						}
				);
				
				jQuery( "#btnEliminarPrograma" ).button().click(
						function() {
							var grid = jQuery("#gridProgramas");
							if(datosPrograma.id == ""){
								jQuery( "#confirmText" ).html("Para poder eliminar un programa, es necesario seleccionarlo de la lista.");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Programa",
									buttons: {
										"Aceptar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarPrograma" ).button("refresh");
										jQuery( "#btnEliminarPrograma" ).removeClass('ui-state-focus');
									}
								});
							}else{
								jQuery( "#confirmText" ).html("El programa seleccionado ser&aacute; eliminado de la asignaci&oacute;n,<br /> ¿Desea continuar?");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Programa",
									buttons: {
										"Eliminar": function() {
											if (validarProgramaAgregado(datosPrograma.id)){
												eliminarDatoGrid(grid, datosPrograma.id);
												agregarDatoAGrid(jQuery("#gridCatProgramas"), datosPrograma);
												eliminarProgramaAgregado(datosPrograma.id);
												jQuery( "#btnEliminarPrograma" ).button( "option", "disabled", true );
												jQuery( this ).dialog( "close" );	
											}else{
												jQuery("#dialog-programa-invalido").dialog("open");
											}
										},
										"Cancelar": function() {
											jQuery( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarPrograma" ).button("refresh");
										jQuery( "#btnEliminarPrograma" ).removeClass('ui-state-focus');
									}
								});
							}	
							
							jQuery("#dialog-confirm").dialog("open");
						}
				);
				
				jQuery( "#btnEliminarPrograma" ).button( "option", "disabled", true );
				jQuery( "#btnAsociarPrograma" ).button( "option", "disabled", true );
				
				<% if (request.getParameter("asociacionExitosa") != null){%>
					jQuery("#dialog-asociarCursoExito" ).dialog("open");
				<%}%>	
			}
	);
	
	function llenarForma(catalogo, id){
		GetAJAXValues(
				catalogo, id,
			function(objRemoteData){
				llenandoForma(
						objRemoteData.catalogo,
						objRemoteData.object
				);
				
				jQuery("#dialog-detalles-programa").dialog("open");
			}
		);		
	}
	
	function GetAJAXValues( catalogo, id, fnCallback ){
		if (GetAJAXValues.Xhr){
			GetAJAXValues.Xhr.abort();
		}

		GetAJAXValues.Xhr = $.ajax({
			type: "post",
			url:'<%=request.getContextPath()%>/obtenerDetallesAsignacionProgramaPorId.do',
			data: {
				catalogo: catalogo, id:id
			},
			dataType: "json",

			success: function( objData ){
				fnCallback({ catalogo: catalogo, object: objData});
			},

			error: function(){
				customAlert("No se pueden mostrar los detalles.<br /> Intente de nuevo por favor ");
			},

			complete: function(){
				GetAJAXValues.Xhr = null;
			}
		});
	}
	
	function llenandoForma(catalogo, object){
			jQuery('#nombreProgDisponible').val(object.CatProgramaNombre);
			jQuery('#tipoProgDisponible').val(object.CatTipoProgramaId);
			jQuery('#descProgDisponible').val(object.CatProgramaDescripcion);
			jQuery('#fchIniProgDisp').val(object.CatProgramaFechaInicio);
			jQuery('#fchFinProgDisp').val(object.CatProgramaFechaInicio);
			jQuery('#totalPuntosDisp').val(object.CatProgramaPuntos);
			for (key in object.CatProgramaCursos){
				jQuery('.detalleCursos').append('<p> '+object.CatProgramaCursos[key].CatCursoNombre+'</p>');
			}
			for (key in object.CatProgramaTrabajos){
				jQuery('.detalleTrabajos').append('<p> '+object.CatProgramaTrabajos[key].CatTrabajoNombre+'</p>');
			}
	}
	
	function copiarDatosPrograma(datosSeleccionados, id){
		datosPrograma.id = id;
		datosPrograma.Tipo = datosSeleccionados.Tipo;
		datosPrograma.Nombre = datosSeleccionados.Nombre;
		datosPrograma.FechaInicio = datosSeleccionados.FechaInicio;
		datosPrograma.FechaTermino = datosSeleccionados.FechaTermino;
		datosPrograma.Puntos = 0;
	}
	
	function agregarDatoAGrid(grid, data){
		grid.jqGrid('addRowData',data.id, data);
	}	
	
	function eliminarDatoGrid(grid,id){
		grid.delRowData(id);
	}
	
	function eliminarProgramaAgregado(id){
		var nuevoArreglo = [];
		for (var i=0; i<programasAgregados.length;i++){
			if (programasAgregados[i]!= id){
				nuevoArreglo.push(programasAgregados[i]);
			}
		}
		programasAgregados = nuevoArreglo;
	}
	
	function validarProgramaAgregado(id){
		for (var i=0; i<programasAgregados.length;i++){
			if (programasAgregados[i] == id){
				return true;
			}
		}
		return false;
	}
	
	function asociarIdsProgramas(){
		var idsConcatenados="";
		for (var i=0;i<programasAgregados.length;i++){
			if(i==(programasAgregados.length-1)){
				idsConcatenados+=programasAgregados[i];
			}else{
				idsConcatenados+=programasAgregados[i]+',';
			}
		}
		return idsConcatenados;
	}
	
</script>
<div id="tabsAsignacionRS">
	<ul>
		<li><a href="#AsignarProgramaRS">Asignar Programa</a></li>
	</ul>
	<div id="AsignarProgramaRS">
		<html:form action="/guardarAsocProg.do" styleId="guardarAsocProgForm">
			<br />
			<fieldset>
				<legend>Datos de la sentencia</legend>
				<table border="0" align="center">
					<tr>
						<td colspan="2">
							<table border="0">
								<tr>
									<td align="right">Sentenciado:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm"
											property="nombreSentenciado" readonly="true"
											styleId="nombreSentenciado" styleClass="texto" /></td>
								</tr>
								<tr>
									<td align="right">NUS:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm" property="nus"
											readonly="true" styleId="nus" styleClass="texto" /></td>
								</tr>
							</table></td>
						<td colspan="2">
							<table>
								<tr>
									<td align="right">Tipo de pena:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm"
											property="tipoSentencia" readonly="true" size="30"
											styleId="tipoSentencia" styleClass="texto" /></td>
								</tr>
								<tr>
									<td align="right">CERESO:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm" property="nombreCereso"
											readonly="true" size="30" styleId="nombreCereso"
											styleClass="texto" /></td>
								</tr>
							</table></td>
						<td colspan="2">
							<table>
								<tr>
									<td align="right">Fecha de inicio de pena:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm"
											property="fechaInicioPena" readonly="true"
											styleId="fechaInicioPena" styleClass="texto" /></td>
								</tr>
								<tr>
									<td align="right">Fecha de fin de pena:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm" property="fechaFinPena"
											readonly="true" styleId="fechaFinPena" styleClass="texto" />
									</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<table border="0">
								<tr>
									<td align="right">Puntos por cumplir:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm"
											property="puntosPorAcumular" /></td>
								</tr>
							</table></td>
						<td colspan="3" align="center">
							<table border="0">
								<tr>
									<td align="right">Puntos acumulados:</td>
									<td align="left"><html:text
											name="AsignarProgramaReinsercionForm"
											property="puntosAcumulados" readonly="true"
											styleId="puntosAcumulados" styleClass="texto" /></td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td colspan="9">
							<hr />
						</td>
					</tr>
					<tr />
					<tr>
						<td colspan="6" align="center">
							<div id="catProg2">
								<table id="gridProgramas"></table>
								<div id="pagerProgramas" style="vertical-align: top;"></div>
							</div></td>
						<td align="left">
							<button id="btnEliminarPrograma" type="button" class="btn_buscar">Eliminar</button>
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<hr /></td>
					</tr>
					<tr />
					<tr>
						<td colspan="6" align="center">
							<div id="catProg1">
								<table id="gridCatProgramas"></table>
								<div id="pagerCatProgramas" style="vertical-align: top;"></div>
							</div></td>
						<td align="left">
							<button id="btnAsociarPrograma" type="button" class="btn_buscar">Asociar</button>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<button id="btnRegistrarProgramas" type="button"
								class="btn_buscar">Guardar</button></td>
					</tr>
				</table>
				<html:hidden name="AsignarProgramaReinsercionForm"
					property="idsProgramasConcatenados"
					styleId="idsProgramasConcatenados" />
				<div id="dialog-detalles-programa" title="Detalles del programa">
					<fieldset>
						<table border="0">
							<tr>
								<td align="right">Nombre:</td>
								<td align="left" colspan="3"><html:text
										name="AsignarProgramaReinsercionForm"
										property="nombreProgDisponible" maxlength="200"
										styleId="nombreProgDisponible" styleClass="texto"
										readonly="true" /></td>
							</tr>
							<tr>
								<td align="right">Tipo de Programa:</td>
								<td align="left" colspan="3"><html:text
										name="AsignarProgramaReinsercionForm"
										property="tipoProgDisponible" maxlength="200"
										styleId="tipoProgDisponible" styleClass="texto"
										readonly="true" /></td>
							</tr>
							<tr>
								<td align="right">Descripci&oacute;n:</td>
								<td align="left" colspan="3"><html:textarea
										name="AsignarProgramaReinsercionForm"
										property="descProgDisponible" onkeypress=""
										styleId="descProgDisponible" styleClass="texto"
										style="width: 99%;" readonly="true">
									</html:textarea> <script type="text/javascript">
									$('#descProgDisponible').attr("maxlength", 200);
							</script></td>
							</tr>
							<tr>
								<td align="right">Total De Puntos:</td>
								<td align="left" colspan="3"><input type="text"
									id="totalPuntosDisp" class="texto" readonly="readonly" /></td>
							</tr>
							<tr>
								<td align="right">Fecha de Inicio:</td>
								<td align="left"><html:text
										name="AsignarProgramaReinsercionForm"
										property="fchIniProgDisp" styleId="fchIniProgDisp"
										styleClass="texto" readonly="true" /></td>
								<td align="right">Fecha de Termino:</td>
								<td align="left"><html:text
										name="AsignarProgramaReinsercionForm"
										property="fchFinProgDisp" styleId="fchFinProgDisp"
										styleClass="texto" readonly="true" /></td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend>Cursos</legend>
						<div class="detalleCursos"></div>
					</fieldset>
					<fieldset>
						<legend>Trabajos</legend>
						<div class="detalleTrabajos"></div>
					</fieldset>
				</div>

				<div id="dialog-confirm" title="">
					<p>
						<span id="confirmText"></span>
					</p>
				</div>

				<div id="dialog-programa-invalido" title="Programa inv&aacute;lido">
					<p>
						El programa no se puede eliminar debido a que se hab&iacute;a
						asociado al sentenciado de manera previa. <br /> S&oacute;lo se
						permiten eliminar los programas que hayan sido asociados en esta
						ejecuci&oacute;n.
					</p>
				</div>

				<div id="dialog-cambios-programa"
					title="No se han efectuado cambios">
					<p>
						No se puede llevar a cabo la asociaci&oacute;n de programas debido
						a que no se han encontrado cambios.<br /> Es necesario agregar por
						lo menos un programa de la lista de programas disponibles para
						poder asociarlo.
					</p>
				</div>

				<div id="dialog-asociarCursoExito" title="Asociaci&oacute;n exitosa">
					<p>Los cursos han sido asociados con &eacute;xito.</p>
				</div>
			</fieldset>
		</html:form>
	</div>
</div>