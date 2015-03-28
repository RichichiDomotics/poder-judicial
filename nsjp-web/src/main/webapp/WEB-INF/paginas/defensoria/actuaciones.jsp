<center>
<table width="90%" cellspacing="0" cellpadding="0">
	<tr>
		<td width="1%" >&nbsp;</td>
		<td width="15%" >&nbsp;</td>
		<td width="6%" >&nbsp;</td>
		<td width="78%">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td valign="top" align="right"><strong>Actuaciones:</strong></td>
		<td ><select name="acciones" id="cbxAcciones" size="10" style="width:470px">
			<!-- <option>- Seleccione</option> -->				      				     
		  	</select></td>
		<td>
			<table>
				<tr>
					<td>
						<input type="button" value="Cambiar etapa del expediente" id="cambiarEtapaDelExpediente" onClick="cambiarEtapaExpediente();" class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Teoría del caso/Escritos de prueba" id="teoriaDelCaso" onClick="abreTeoria();" class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Relacionar expediente a caso" id="relacionarExpediente" onClick="relacionarExpedienteACaso();" class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Actualizar Situaci&oacute;n Jur&iacute;dica" id="btnActualizarSituacion" onClick="cambiarSituacionDefendido();"class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Adjuntar documento" id="btnAdjuntarDocumento" onClick="actuacionAdjuntarDocumento('1');" class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Adjuntar oficio para cierre de expediente" id="btnAdjuntarOficio" onClick="actuacionAdjuntarOficio('1');" class="btn_grande"/>					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Medidas Cautelares" id="btnMedidasCautelares" onClick="mostrarVentanaInvolucradosCausa(jQuery('#expediente').val(), 'true');" class="btn_grande"/>					
					</td>
				</tr>				
			</table>
		
		
		
		
		</td>
	</tr>
	<!-- <tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="button" value="Teoría del caso/Escritos de prueba" id="teoriaDelCaso" onClick="abreTeoria();" class="btn_grande"/></td>
	</tr>
	<tr id="btnRelacionExpedienteCaso">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="button" value="Relacionar expediente a caso" id="relacionarExpediente" onClick="relacionarExpedienteACaso();" class="btn_grande"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="button" value="Actualizar Situaci&oacute;n Jur&iacute;dica" id="btnActualizarSituacion" onClick="cambiarSituacionDefendido();"class="btn_grande"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="button" value="Adjuntar documento" id="btnAdjuntarDocumento" onClick="actuacionAdjuntarDocumento('1');" class="btn_grande"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="button" value="Adjuntar oficio para cierre de expediente" id="btnAdjuntarOficio" onClick="actuacionAdjuntarOficio('1');" class="btn_grande"/></td>
	</tr>
	-->
</table>

<div id="divCambiarEtapa" style="display: none">
	<table align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center"><strong>La etapa actual del expediente es:
		        <input type="text" id="etapaActual" disabled size="40" style="text-align: center;"/>
		    </strong></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td valign="top">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center"><strong>Nueva etapa:
		      <select name="cmbEtapaExpediente" id="cmbEtapaExpediente">
		      </select>
		    </strong></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
	</table>
</div>
<div id="divCambiarSituacionDefendido" style="display: none">
	<table align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center"><strong>La situaci&oacute;n actual del defendido es:
		        <input type="text" id="situacionActual" disabled size="40" style="text-align: center;"/>
		    </strong></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td valign="top">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center"><strong>Seleccione la nueva situaci&oacute;n del defendido:
		      <select id="cmbSituacionDefendido">
		      </select>
		    </strong></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td align="center">&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
	</table>
</div>
</center>