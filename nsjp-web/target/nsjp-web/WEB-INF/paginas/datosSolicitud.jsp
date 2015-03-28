			<!--<ul class="toolbar">
					<div id="menu_config">
						<li id="btnGuardarDatos" value="Guardar" class="first"><span></span>Guardar</li>
						
					</div>
				</ul>-->
	
<form id="profileForm" class="actionForm"  method="get">
  <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
  </div>
<table border="0" width="70%" align="center">

	<tr>
	<td width="26%" align="right">&nbsp;</td>
		<td width="50%" class="field">&nbsp;</td>
		
		<td width="16%" align="right" class="tdAliasCmp"> <span style="display: none;">Alias:</span></td>
		<td width="8%" class="tdAliasCmp">
		
		</td>
	</tr>
	<tr>
	  <td align="right">Nombre(s):</td>
	  <td class="field"><input type="text" tabindex="1" class="" size="45" maxlength="45" id="nombreDEATT" name="nombreDEATT"  value="" onKeyDown="espejoDatos();" onKeyPress="espejoDatos();" onKeyUp="espejoDatos();"/></td>
	  <td align="right" class="tdAliasCmp">Ocupaci&oacute;n:</td>
	  <td class="tdAliasCmp"><select id="ocupacionDEATT"  size="3" name="ocupacionDEATT"  multiple="multiple">
      </select></td>
    </tr>
	<tr>
	
	<td align="right">Apellido Paterno:</td>
		<td>
			<input type="text" tabindex="2" size="45" maxlength="45" class="" id="aPaternoDEATT" name="aPaternoDEATT" onKeyDown="espejoDatos();" onKeyPress="espejoDatos();" onKeyUp="espejoDatos();"/>
		</td>
		
		<td  align="right">&nbsp;</td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td align="right">Apellido Materno:</td>
		<td>
			<input type="text" tabindex="3"  size="45" maxlength="45" class="" id="aMaternoDEATT"  name="aMaternoDEATT" onKeyDown="espejoDatos();" onKeyPress="espejoDatos();" onKeyUp="espejoDatos();"/>
		</td>
        <td align="right">Nacionalidad:</td>
        <td><select id="nacionalidadDEATT" size="3"  name="nacionalidadDEATT" multiple="multiple">
        </select></td>
	</tr>
	<tr>
		<td align="right">CURP:</td>
		<td>
			<input type="text" tabindex="4"  size="45" maxlength="18" class="" id="curpDEATT"  name="curpDEATT"/>
		</td>
		<td rowspan="2" align="right" >&nbsp;</td>
		<td rowspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td align="right">RFC:</td>
		<td>
			<input type="text" tabindex="5"  class="" size="45" maxlength="17" id="rfcDEATT" name="rfcDEATT" /> <!-- onBlur="RFC(this.value)" -->
		</td>
	</tr>
	
	<tr id="trAliasTxt">
		<td align="right"></td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td align="right">Idioma/
	    <div id="idiomaNacional">Lengua:</div><div id="idiomaExtranjero" style="display: none">Dialecto:</div></td>
		<td>
			<select id="idiomaDEATT" name="idiomaDEATT" >
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
        <td></td>
        <td></td>
	</tr>
	<tr>
		<td align="right"><span style="display: none;">Religion:</span> </td>
		<td>
			
		</td>
	</tr>
	<tr>
		<td align="right">Escolaridad:</td>
		<td>
			<select id="escoralidadDEATT" name="escoralidadDEATT" >
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
        <td></td>
        <td></td>
	</tr>
	<tr>
		<td align="right">Estado Civil:</td>
		<td>
			<select id="estadoCivilDEATT" name="estadoCivilDEATT"   >
				<option value="-1">- Selecciona -</option>
			</select>
		</td>
        
    </tr>
	<tr>
		<td align="right">Sexo:</td>
		<td>
			Masculino <input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" checked="checked" >
			&nbsp;
			&nbsp;
			&nbsp;
			Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F">
			<div class="formError"></div>
		</td>
        <td></td>
        <td></td>
	</tr>
</table>

<table width="37%" border="0" align="center" style="display: none;">
  <tr>
    <td width="203" align="right">Tipo de Solicitud:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  
    <td width="203">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="tipoExpediente" id="tipoExpediente" onChange="selectTipoExpediente()" >
      <option value="-1">- Selecciona -</option>
      <!-- <option value="adscrito">Adscrito</option> -->
      <!-- <option value="tecnico">T&eacute;cnico</option> -->
      <option value="Normal">Normal</option>
    <option value="restaurativa">Restaurativa</option>
    </select></td>
  </tr>
  
   <tr >
    <td width="203"  colspan="2">
    <center>
    <input type="button" value="Guardar" id="iBtnGuardar" class="btn_Generico"/></td>
  </center>
    
  </tr>
</table>

<input class="formButton" type="submit" value="Next"  tabindex="14" id="botonvalida" style="display: none;"/>
</form>