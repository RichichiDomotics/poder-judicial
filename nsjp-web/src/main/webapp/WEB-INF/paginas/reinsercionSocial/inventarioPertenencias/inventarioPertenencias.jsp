<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				jQuery("#tabsInventarioPertenencias").tabs();
				
				jQuery("#dialog:ui-dialog" ).dialog( "destroy" );
				
				jQuery("#dialogDetallesPertenencia").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Cancelar: function() {
							$( this ).dialog( "close" );
						}
					},
					resizable: false
				});
				
				jQuery("#dialog-cambioExito, #dialogValidarPertenencia").dialog({
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
				
				jQuery("#dialogConfirmacionEliminarPertenencia").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Eliminar: function() {
							enviarForma('3');
						},
						Cancelar: function() {
							$( this ).dialog( "close" );
						}
					},
					resizable: false
				});
				
				jQuery("#dialogConfirmDefinitivo").dialog({
					autoOpen: false,
					height: 'auto',
					width:'auto',
					modal: true,
					buttons: {
						Generar: function() {
							enviarForma('4');
						},
						Cancelar: function() {
							$( this ).dialog( "close" );
						}
					},
					resizable: false
				});
				
				jQuery("#gridPertenencias").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Pertenencias",
					viewrecords: true,
					onSelectRow: function(id){ 
						if(jQuery('#definitivo').val() == "false"){
							jQuery("#btnModificarPertenencia").button( "option", "disabled", false );
							jQuery("#btnEliminarPertenencia").button( "option", "disabled", false );							
						}
					},
					sortorder: "asc",		           		
					autowidth:true,
        			shrinkToFit:true,
					url:'<%=request.getContextPath()%>/llenarGridInventarioPertenencias.do',
					colNames:['Tipo','Descripci&oacute;n','Cantidad', 'Unidad', 'Condici&oacute;n'], 
					colModel:[ 	{name:'tipo',index:'1' }, 
								{name:'descripcion',index:'2'}, 
								{name:'cantidad',index:'3'}, 
								{name:'unidad',index:'4'},
								{name:'condicion',index:'5'}
							],
					pager: jQuery('#pagerPertenencias')
				}).navGrid('#pagerPertenencias',{edit:false,add:false,del:false});
				
				jQuery( "#btnNuevaPertenencia" ).button().click(
						function() {
							limpiarForma();
							jQuery("#btnRegistrarCambioPertenencia").button( "option", "disabled", true );
							jQuery("#btnRegistrarNuevaPertenencia").button( "option", "disabled", false );
							jQuery("#dialogDetallesPertenencia").dialog("open");
						}
				);
				
				jQuery( "#btnModificarPertenencia" ).button().click(
						function() {
							var pertenenciaId = jQuery("#gridPertenencias").jqGrid('getGridParam','selrow');
							jQuery("#btnRegistrarCambioPertenencia").button( "option", "disabled", false );
							jQuery("#btnRegistrarNuevaPertenencia").button( "option", "disabled", true );
							llenarForma(pertenenciaId, false);	
						}
				);
				
				jQuery( "#btnEliminarPertenencia" ).button().click(
						function() {
							var pertenenciaId = jQuery("#gridPertenencias").jqGrid('getGridParam','selrow');
							jQuery("#btnRegistrarCambioPertenencia").button( "option", "disabled", false );
							jQuery("#btnRegistrarNuevaPertenencia").button( "option", "disabled", true );
							llenarForma(pertenenciaId, true);	
						}
				);
				
				jQuery( "#btnImprimirRecibo" ).button().click(
						function() {
							jQuery("#dialogConfirmDefinitivo").dialog("open");
						}
				);
				
				jQuery( "#btnRegistrarNuevaPertenencia" ).button().click(
						function() {
							enviarForma('1');
						}
				);
				
				jQuery( "#btnRegistrarCambioPertenencia" ).button().click(
						function() {
							enviarForma('2');
						}
				);
				
				jQuery("#btnModificarPertenencia").button( "option", "disabled", true );
				jQuery("#btnEliminarPertenencia").button( "option", "disabled", true );
				
				if(jQuery('#definitivo').val() == true){
					
				}
				
				deshabilitarBotones();
				
				<% if (request.getParameter("cambioExitoso") != null){%>
					jQuery("#dialog-cambioExito" ).dialog("open");
				<%}%>
			
			});
	
	function limpiarForma(){
		jQuery('#pertenenciaId').val("");
		jQuery('#tipoPertId').val("-1");
		jQuery('#descPert').val("");
		jQuery('#cantidadPert').val("0");
		jQuery('#unidadPertId').val("-1");
		jQuery('#condicionPertId').val("-1");
	}
	
	function llenarForma(id, banderaEliminacion){
		GetAJAXValues(
				id,
			function(objRemoteData){
				llenandoForma(
						objRemoteData.object
				);
				if (banderaEliminacion){
					jQuery("#dialogConfirmacionEliminarPertenencia").dialog("open");
				}else{
					jQuery("#dialogDetallesPertenencia").dialog("open");
				}
			}
		);		
	}
	
	function GetAJAXValues( id, fnCallback ){
		if (GetAJAXValues.Xhr){
			GetAJAXValues.Xhr.abort();
		}

		GetAJAXValues.Xhr = $.ajax({
			type: "post",
			url:'<%=request.getContextPath()%>/obtenerDetallesPertenenciaPorId.do',
			data: {
				id:id
			},
			dataType: "json",

			success: function( objData ){
				fnCallback({object: objData});
			},

			error: function(){
				customAlert("No se pueden mostrar los detalles.<br /> Intente de nuevo por favor. ");
			},

			complete: function(){
				GetAJAXValues.Xhr = null;
			}
		});
	}
	
	function llenandoForma(object){
		jQuery('#pertenenciaId').val(object.idPertenencia);
		jQuery('#tipoPertId').val(object.tipoPertenenciaId);
		jQuery('#descPert').val(object.descripcionPertenencia);
		jQuery('#cantidadPert').val(object.cantidadPertenencia);
		jQuery('#unidadPertId').val(object.unidadPertenenciaId);
		jQuery('#condicionPertId').val(object.condicionPertenenciaId);
	}
	
	function enviarForma(toPath){
		if (toPath == '4'){
			var action= '<%=request.getContextPath()%>';
			action += '/generarReciboInventarioPertenencias.do';
			document.getElementById("registrarPertenenciaForm").action=action;
			document.getElementById("registrarPertenenciaForm").submit();
		}else{	
			var enviar = validarDatosForma();
			if(enviar){
				var action= '<%=request.getContextPath()%>';
				switch(toPath){
					case '1':
		  				action += '/guardarPertenenciaInventario.do';
		  				break;
					case '2':
		  				action += '/actualizarPertenenciaInventario.do';
		  				break;
					case '3':
		  				action += '/eliminarPertenenciaInventario.do';
		  				break;
					default:
		  				return false;
					}
				document.getElementById("registrarPertenenciaForm").action=action;
				document.getElementById("registrarPertenenciaForm").submit();
			}else{
				jQuery("#dialogValidarPertenencia").dialog("open");
			}
		}
	}
	
	function validarDatosForma(){
		if (jQuery('#tipoPertId').val() == -1
				|| jQuery('#descPert').val() == ""
				|| jQuery('#cantidadPert').val() == ""
				|| isNaN(jQuery('#cantidadPert').val())
				|| jQuery('#cantidadPert').val() < 0
				|| jQuery('#cantidadPert').val() == 0
				|| jQuery('#condicionPertId').val() == -1 ){
			return false;
		}else{
			return true;
		}
	}
	
	function deshabilitarBotones(){
		if(jQuery('#definitivo').val() == "true"){
			jQuery("#btnNuevaPertenencia").button( "option", "disabled", true );
			jQuery("#btnImprimirRecibo").button( "option", "disabled", true );
		}
	}
	
</script>
<div id="tabsInventarioPertenencias">
	<ul> 
		<li><a href="#InventarioPertenenciasRS">Administrar inventario de pertenencias</a></li>
	</ul>
	<div id="InventarioPertenenciasRS">
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
										name="InventarioPertenenciasForm"
										property="nombreSentenciado" readonly="true"
										styleId="nombreSentenciado" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">NUS:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm" property="nus"
										readonly="true" styleId="nus" styleClass="texto" /></td>
							</tr>
						</table></td>
					<td colspan="2">
						<table>
							<tr>
								<td align="right">Tipo de pena:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm"
										property="tipoSentencia" readonly="true" size="30"
										styleId="tipoSentencia" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">CERESO:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm" property="nombreCereso"
										readonly="true" size="30" styleId="nombreCereso"
										styleClass="texto" /></td>
							</tr>
						</table></td>
					<td colspan="2">
						<table>
							<tr>
								<td align="right">Fecha de inicio de pena:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm"
										property="fechaInicioPena" readonly="true"
										styleId="fechaInicioPena" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">Fecha de fin de pena:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm" property="fechaFinPena"
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
										name="InventarioPertenenciasForm"
										property="puntosPorAcumular" /></td>
							</tr>
						</table></td>
					<td colspan="3" align="center">
						<table border="0">
							<tr>
								<td align="right">Puntos acumulados:</td>
								<td align="left"><html:text
										name="InventarioPertenenciasForm"
										property="puntosAcumulados" readonly="true"
										styleId="puntosAcumulados" styleClass="texto" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<div id="catProg2">
							<table id="gridPertenencias"></table>
							<div id="pagerPertenencias" style="vertical-align: top;"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button id="btnNuevaPertenencia" type="button" class="btn_buscar">Agregar</button>
						<button id="btnModificarPertenencia" type="button" class="btn_buscar">Modificar</button>
						<button id="btnEliminarPertenencia" type="button" class="btn_buscar">Eliminar</button>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button id="btnImprimirRecibo" type="button" class="btn_mediano">Imprimir recibo</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div id="dialogDetallesPertenencia" title="Detalles de la pertenencia">
		<html:form action="/guardarPertenenciaInventario.do" styleId="registrarPertenenciaForm">
			<fieldset>
				<legend>Datos de la pertenencia</legend>
				<table>	
					<tr>
						<td align="right">Tipo :</td>
						<td align="left">
							<html:select name="InventarioPertenenciasForm" property="tipoPertId" styleId="tipoPertId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="InventarioPertenenciasForm" property="lstCatTipoPertDTO" label="valor" value="clave" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">Descripci&oacute;n :</td>
						<td align="left">
							<html:textarea name="InventarioPertenenciasForm" property="descPert" styleId="descPert" styleClass="texto" style="width: 99%;"/>
						</td>
					</tr>
					<tr>
						<td align="right">Cantidad :</td>
						<td align="left">
							<html:text name="InventarioPertenenciasForm" property="cantidadPert" styleId="cantidadPert" styleClass="texto" />
						</td>
					</tr>
					<tr>
						<td align="right">Unidad de medida :</td>
						<td align="left">
							<html:select name="InventarioPertenenciasForm" property="unidadPertId" styleId="unidadPertId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="InventarioPertenenciasForm" property="lstCatUnidadPertDTO" label="valor" value="clave" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">Condición :</td>
						<td align="left">
							<html:select name="InventarioPertenenciasForm" property="condicionPertId" styleId="condicionPertId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="InventarioPertenenciasForm" property="lstCatCondicionPertDTO" label="valor" value="clave" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button id="btnRegistrarNuevaPertenencia" type="button" class="btn_buscar" >Registrar</button>
							<button id="btnRegistrarCambioPertenencia" type="button" class="btn_buscar" >Modificar</button>
						</td>
					</tr>
				</table>
				<html:hidden name="InventarioPertenenciasForm" property="pertenenciaId" styleId="pertenenciaId" />
				<html:hidden name="InventarioPertenenciasForm" property="definitivo" styleId="definitivo" />
			</fieldset>
		</html:form>
	</div>
	<div id="dialogConfirmacionEliminarPertenencia" title="Confirmaci&oacute;n de eliminaci&oacute;n">
		<p>La pertenencia seleccionada ser&aacute; eliminada de manera definitiva.</p>
		<p>¿Desea eliminar la pertenencia?</p>
	</div>
	<div id="dialogValidarPertenencia" title="Error de validaci&oacute;n">
		<p>Los siguientes datos de la pertenencia son requeridos:</p>
		<ul>
			<li>Tipo</li>
			<li>Descripci&oacute;n</li>
			<li>Cantidad</li>
			<li>Condici&oacute;n</li>
		</ul>	 
		<p>Favor de verificar los datos anteriores</p>
	</div>
	<div id="dialogConfirmDefinitivo" title="Generar recibo de inventario de pertenencias">
		<p>El inventario de pertenencias ser&aacute; guardado de manera definitiva.<br/>
		Lo cual implica que no se podr&aacute;n modificar las pertenencias asociadas al inventario.<br/>
		Asimismo, se generar&aacute; el acuse de recibo de las pertenencias.<br/>
		¿Desea guardar el inventario de pertenencias de manera definitiva y generar el recibo?</p>
	</div>
	<div id="dialog-cambioExito" title="Modificaci&oacute;n exitosa">
		<p>La informaci&oacute;n se actualiz&oacute; con &eacute;xito</p>
	</div>
</div>