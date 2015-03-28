
<table border="0" width="800px" align="center" >
<tr><td colspan="4">
	
	
	</td>
  </tr>
  <tr>
	<td colspan="4">
	
	</td>
  </tr>
	<tr>
	<td align="right">Nombre(s):</td>
		<td class="field">
			<input type="text" tabindex="1" class="" size="50" maxlength="50" id="nombreDEATT1" name="nombreDEATT1"  value="" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();" />
		</td>
		
	<td align="right" class="tdAliasCmp">Alias:</td>
		<td class="tdAliasCmp"><select id="aliasDEATT"
			multiple="multiple" name="aliasDEATT" ></select></td>
	</tr>
	
	<tr>
	
	<td align="right">Apellido Paterno:</td>
		<td>
			<input type="text" tabindex="2" size="50" maxlength="50" class="" id="aPaternoDEATT1" name="aPaternoDEATT1" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
		</td>
		
		<td   align="right">Ocupaci&oacute;n:</td>
		<td >
			<select id="ocupacionDEATT1"  name="ocupacionDEATT1"  multiple="multiple"></select>
		</td>
	</tr>
	<tr>
		<td align="right">Apellido Materno:</td>
		<td>
			<input type="text" tabindex="3" size="50" maxlength="50" class="" id="aMaternoDEATT1"  name="aMaternoDEATT1" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
		</td>
	</tr>
	<tr>
		<td align="right">CURP:</td>
		<td>
			<input type="text" tabindex="4" size="50" maxlength="18" class="" id="curpDEATT1"  name="curpDEATT"/>
		</td>
		<td  align="right" >Nacionalidad:</td>
		<td>
			<select id="nacionalidadDEATT1" size="3" name="nacionalidadDEATT1" multiple="multiple"></select>
		</td>
	</tr>
	<tr>
		<td align="right">RFC:</td>
		<td>
			<input type="text" tabindex="5" class="" size="50" maxlength="13" id="rfcDEATT1" name="rfcDEATT1"/>
		</td>
	</tr>
	
	<tr id="trAliasTxt">
		<td align="right">Alias:</td>
		<td><input type="text" tabindex="6" size="20" maxlength="50" id="newItem"
			name="newItem" /> <input type="button" id="add" value="Agrega Alias" class="btn_Generico"/>
		</td>
	</tr>
	<tr>
		<td align="right">Idioma o Lengua:</td>
		<td>

			<select id="idiomaDEATT1" name="idiomaDEATT1">
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right"><span style="display: none;">Religion:</span> </td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td align="right">Escolaridad:</td>
		<td>
			<select id="escoralidadDEATT1" name="escoralidadDEATT1">
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">Estado Civil:</td>
		<td>
			<select id="estadoCivilDEATT1" name="estadoCivilDEATT" >
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
		<td colspan="2" id="etiquetaMismaPersona">Misma persona para quien solicita?
        <input type="checkbox" name="mismaPersona" id="mismaPersona" /></td>
	</tr>
	<tr>
		<td align="right">Sexo:</td>
		<td>
			Masculino <input type="radio" id="datosGeneralesCmpSexoM1" name="rbtSexoDatosGenerales1" value="M" checked="checked">
			&nbsp;
			&nbsp;
			&nbsp;
			Femenino <input type="radio" id="datosGeneralesCmpSexoF1" name="rbtSexoDatosGenerales1" value="F">
			<div class="formError"></div>
		</td>
	</tr>
</table>


<table width="37%" border="0" align="center" style="display: none;">
  <tr>
    <td width="203" align="right">Tipo de Solicitud:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  
    <td width="203">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="tipoExpediente" id="tipoExpediente" onchange="selectTipoExpediente()" >
      <option value="-1" >- Selecciona -</option>
      <!-- <option value="adscrito">Adscrito</option> -->
      <!-- <option value="tecnico">T&eacute;cnico</option> -->
      <option value="Normal">Normal</option>
    <option value="restaurativa">Restaurativa</option>
    </select></td>
  </tr>
  
   <tr >
    <td width="203"  colspan="2">
    <center>
    <input type="button" value="Guardar" id="iBtnGuardar" class="btn_Generico"/></center></td>
  
    
  </tr>
</table>

