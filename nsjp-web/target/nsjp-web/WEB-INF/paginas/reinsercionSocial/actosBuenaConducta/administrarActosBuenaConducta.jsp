<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				jQuery("#tabsActosBuenaConductaRS").tabs();
				
				jQuery("#dialogDetallesABC").dialog({
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
				
				jQuery("#dialog-cambioExito").dialog({
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
				
				jQuery("#dialogValidarABC").dialog({
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
				
				jQuery("#dialogConfirmacionEliminarABC").dialog({
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
				
				jQuery("#gridActosBuenaConducta").jqGrid({
					datatype: "xml", 
					rowNum:10,
					rowList:[10,20,30,40,50,60,70,80,90,100],
					autowidth: true,
					height:'auto',
					minWidth: 500, 
					minHeight: 200,						
					caption:"Actos de buena conducta",
					viewrecords: true,
					onSelectRow: function(id){ 
						jQuery("#btnModificarABC").button( "option", "disabled", false );
						jQuery("#btnEliminarABC").button( "option", "disabled", false );
					},
					ondblClickRow: function(id) {
						
					},
					sortorder: "asc",		           		
					autowidth:true,
        			shrinkToFit:true,
					url:'<%=request.getContextPath()%>/llenarGridActosBuenaConducta.do',
					colNames:['Nombre','Descripcion','Puntos otorgados', 'Acumulado'], 
					colModel:[ 	{name:'nombre',index:'1' }, 
								{name:'descripcion',index:'2'}, 
								{name:'puntos',index:'3'}, 
								{name:'acumulado',index:'4'}
							],
					pager: jQuery('#pagerActosBuenaConducta')
				}).navGrid('#pagerActosBuenaConducta',{edit:false,add:false,del:false});
				
				jQuery( "#btnNuevoABC" ).button().click(
						function() {
							limpiarForma();
							jQuery("#btnRegistrarCambioABC").button( "option", "disabled", true );
							jQuery("#btnRegistrarNuevoABC").button( "option", "disabled", false );
							jQuery("#dialogDetallesABC").dialog("open");
						}
				);
				
				jQuery( "#btnModificarABC" ).button().click(
						function() {
							var actoId = jQuery("#gridActosBuenaConducta").jqGrid('getGridParam','selrow');
							jQuery("#btnRegistrarCambioABC").button( "option", "disabled", false );
							jQuery("#btnRegistrarNuevoABC").button( "option", "disabled", true );
							llenarForma(actoId, false);	
						}
				);
				
				jQuery( "#btnEliminarABC" ).button().click(
						function() {
							var actoId = jQuery("#gridActosBuenaConducta").jqGrid('getGridParam','selrow');
							jQuery("#btnRegistrarCambioABC").button( "option", "disabled", false );
							jQuery("#btnRegistrarNuevoABC").button( "option", "disabled", true );
							llenarForma(actoId, true);	
						}
				);
				
				jQuery( "#btnRegistrarNuevoABC" ).button().click(
						function() {
							enviarForma('1');
						}
				);
				
				jQuery( "#btnRegistrarCambioABC" ).button().click(
						function() {
							enviarForma('2');
						}
				);
				
				jQuery("#btnModificarABC").button( "option", "disabled", true );
				jQuery("#btnEliminarABC").button( "option", "disabled", true );
				
				<% if (request.getParameter("cambioExitoso") != null){%>
					jQuery("#dialog-cambioExito" ).dialog("open");
				<%}%>
				
			}
	);
	
	function llenarForma(id, banderaEliminacion){
		GetAJAXValues(
				id,
			function(objRemoteData){
				llenandoForma(
						objRemoteData.object
				);
				if (banderaEliminacion){
					jQuery("#dialogConfirmacionEliminarABC").dialog("open");
				}else{
					jQuery("#dialogDetallesABC").dialog("open");
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
			jQuery('#actoBuenaConductaId').val(object.idActoBuenaConducta);
	}
	
	function limpiarForma(){
		jQuery('#nombreABC').val("");
		jQuery('#descABC').val("");
		jQuery('#puntosABC').val("");
		jQuery('#acumuladoABC').val("No");
		jQuery('#actoBuenaConductaId').val("");
	}
	
	function validarEntero(string)
	{
		var expresionEntero = /^[0-9]+$/;
		if(string.match(expresionEntero)) {
			return true;
		} else {
			return false;
		}
	}
	
	function validarDatosForma(){
		var puntos = jQuery('#puntosABC').val();
		if (jQuery('#nombreABC').val() == "" ||
				puntos == "" || !validarEntero(puntos) || puntos <1 ){
			return false;
		}else{
			return true;
		}
	}
	
	function enviarForma(toPath){
		var enviar = validarDatosForma();
		if(enviar){
			var action= '<%=request.getContextPath()%>';
			switch(toPath){
				case '1':
	  				action += '/guardarActoBuenaConducta.do';
	  				break;
				case '2':
	  				action += '/actualizarActoBuenaConducta.do';
	  				break;
				case '3':
	  				action += '/eliminarActoBuenaConducta.do';
	  				break;
				default:
	  				return false;
				}
			document.getElementById("guardarActoBuenaConductaForm").action=action;
			document.getElementById("guardarActoBuenaConductaForm").submit();
		}else{
			jQuery("#dialogValidarABC").dialog("open");
		}
	}
	
</script>
<div id="tabsActosBuenaConductaRS">
	<ul>
		<li><a href="#AdministrarActosBuenaConductaRS">Administrar actos de buena conducta</a></li>
	</ul>
	<div id="AdministrarActosBuenaConductaRS">
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
										name="AdministrarActosBuenaConductaForm"
										property="nombreSentenciado" readonly="true"
										styleId="nombreSentenciado" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">NUS:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm" property="nus"
										readonly="true" styleId="nus" styleClass="texto" /></td>
							</tr>
						</table></td>
					<td colspan="2">
						<table>
							<tr>
								<td align="right">Tipo de pena:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm"
										property="tipoSentencia" readonly="true" size="30"
										styleId="tipoSentencia" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">CERESO:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm" property="nombreCereso"
										readonly="true" size="30" styleId="nombreCereso"
										styleClass="texto" /></td>
							</tr>
						</table></td>
					<td colspan="2">
						<table>
							<tr>
								<td align="right">Fecha de inicio de pena:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm"
										property="fechaInicioPena" readonly="true"
										styleId="fechaInicioPena" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right">Fecha de fin de pena:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm" property="fechaFinPena"
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
										name="AdministrarActosBuenaConductaForm"
										property="puntosPorAcumular" /></td>
							</tr>
						</table></td>
					<td colspan="3" align="center">
						<table border="0">
							<tr>
								<td align="right">Puntos acumulados:</td>
								<td align="left"><html:text
										name="AdministrarActosBuenaConductaForm"
										property="puntosAcumulados" readonly="true"
										styleId="puntosAcumulados" styleClass="texto" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<div id="catProg2">
							<table id="gridActosBuenaConducta"></table>
							<div id="pagerActosBuenaConducta" style="vertical-align: top;"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button id="btnNuevoABC" type="button" class="btn_buscar">Nuevo</button>
						<button id="btnModificarABC" type="button" class="btn_buscar">Modificar</button>
						<button id="btnEliminarABC" type="button" class="btn_buscar">Eliminar</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div id="dialogDetallesABC" title="Detalles del acto de buena conducta">
		<html:form action="/guardarActoBuenaConducta.do" styleId="guardarActoBuenaConductaForm">
			<table>	
				<tr>
					<td align="right">Nombre:</td>
					<td align="left">
						<html:text name="AdministrarActosBuenaConductaForm" property="nombreABC" styleId="nombreABC" styleClass="texto" />
					</td>
				</tr>
				<tr>
					<td align="right">Descripci&oacute;n:</td>
					<td align="left">
						<html:textarea name="AdministrarActosBuenaConductaForm" property="descABC" styleId="descABC" styleClass="texto" style="width: 99%;"/>
					</td>
				</tr>
				<tr>
					<td align="right">Puntos otorgados:</td>
					<td align="left">
						<html:text name="AdministrarActosBuenaConductaForm" property="puntosABC" styleId="puntosABC" styleClass="texto" />
					</td>
				</tr>
				<tr>
					<td align="right">Acumulado:</td>
					<td align="left">
						<html:text name="AdministrarActosBuenaConductaForm" property="acumuladoABC" styleId="acumuladoABC" styleClass="texto" readonly="true" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="btnRegistrarNuevoABC" type="button" class="btn_buscar" >Registrar</button>
						<button id="btnRegistrarCambioABC" type="button" class="btn_buscar" >Modificar</button>
					</td>
				</tr>
			</table>
			<html:hidden name="AdministrarActosBuenaConductaForm" property="actoBuenaConductaId" styleId="actoBuenaConductaId" />
		</html:form>
	</div>
	<div id="dialog-cambioExito" title="Modificaci&oacute;n exitosa">
		<p>La informaci&oacute;n se actualiz&oacute; con &eacute;xito</p>
	</div>
	<div id="dialogValidarABC" title="Error de validaci&oacute;n">
		<p>El nombre y los puntos otorgados del acto de buena conducta son requeridos.<br/> 
		Los puntos deben de ser n&uacute;meros enteros mayores que cero</p>
	</div>
	<div id="dialogConfirmacionEliminarABC" title="Confirmaci&oacute;n de eliminaci&oacute;n">
		<p>El acto de buena conducta seleccionado ser&aacute; eliminado de manera definitiva.<br/>
		Si el acto ya ha sido acumulado en alg&uacute;n per&iacute;odo, los puntos otorgados <br/>
		por el acto anterior, dejar&aacute;n de ser parte de la acumulaci&oacute;n 
		</p>
		<p>¿Desea eliminar el acto de buena conducta?</p>
	</div>
</div>