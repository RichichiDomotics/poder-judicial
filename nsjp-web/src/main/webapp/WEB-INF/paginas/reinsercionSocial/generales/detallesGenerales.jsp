
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
		<fieldset style="height: 100%; min-height: 173px;">
			<table border="0">
				<tr>
					<td colspan="2">
						<table border="0">
							<tr>
								<td align="right"><strong>Caso:</strong></td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="caso" readonly="true" styleId="caso" styleClass="texto" />
								</td>
							</tr>
							<tr>
								<td align="right"><strong>Causa:</strong></td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="causa" readonly="true" styleId="causa" styleClass="texto" />
								</td>
							</tr>
							<tr>
								<td align="right"><strong>Carpeta:</strong></td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="carpeta" readonly="true" styleId="carpeta" styleClass="texto" />
								</td>
							</tr>
						</table>
					</td>
					<td colspan="1">
						<table>
							<tr>
								<td align="right">Nombre(s):</td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="nombre" readonly="true" size="30"/>
								</td>
							</tr>
							<tr>
								<td align="right">Apellido Paterno:</td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="apellidoPaterno" readonly="true" size="30"/>
								</td>
							</tr>
							<tr>
								<td align="right">Apellido Materno:</td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="apellidoMaterno" readonly="true" size="30" />
								</td>
							</tr>
						</table>
					</td>
					<td colspan="2">
						<table>
							<tr>
								<td align="right">Número Único de Sentenciado (NUS):</td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="nus" readonly="true" styleId="carpeta" styleClass="texto"/>
								</td>
							</tr>
							<tr>
								<td align="right">Edad biológica:</td>
								<td align="left">
									<html:text name="DatosGeneralesReinsercionForm" property="edad" readonly="true" size="10" />
								</td>
							</tr>
							<tr>
								<td align="right">¿Presentó lesiones?</td>
								<td align="left">
									Sí <html:radio name="DatosGeneralesReinsercionForm" property="lesionado" title="Sí" value="1" disabled="true" /> 
									No <html:radio name="DatosGeneralesReinsercionForm" property="lesionado" title="No" value="0" disabled="true" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<hr/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Sentencia </legend>
							<table border="0">
								<tr>
									<td align="right">Tipo de sentencia:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="tipoSentencia" readonly="true" size="15"/>
									</td>
								</tr>
								<tr>
									<td align="right">Inicio de pena:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="fechaInicioPenaSTR" readonly="true" size="15" />
									</td>
								</tr>
								<tr>
									<td align="right">Fin de pena:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="fechaFinPenaSTR" readonly="true" size="15" />
									</td>
								</tr>
								<tr>
									<td align="right">¿Se encuentra físicamente en el CERESO?</td>
									<td align="left">
										Sí <html:radio name="DatosGeneralesReinsercionForm" property="encarcelado" title="Sí" value="1" disabled="true" /> 
										No <html:radio name="DatosGeneralesReinsercionForm" property="encarcelado" title="No" value="0" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right">Motivo:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="motivo" readonly="true" size="30"/>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
					<td colspan="1">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Medidas Alternativas </legend>
							<div id="scrollProgramas" style="height: 100%; min-height: 173px; overflow-y:auto;">
								<table border="0">
									<logic:iterate name="DatosGeneralesReinsercionForm" property="lstMedidasAlternativas" id="medidaAlternativa">
										<tr>
											<td>
												<bean:write name="medidaAlternativa"/>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</fieldset>
					</td>
					<td colspan="2">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Remisiones </legend>
							<table border="0">
								<tr>
									<td align="right">¿Aplica para preliberación?</td>
									<td align="left">
										Sí <html:radio name="DatosGeneralesReinsercionForm" property="preliberacion" title="Sí" value="1" disabled="true" /> 
										No <html:radio name="DatosGeneralesReinsercionForm" property="preliberacion" title="No" value="0" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right">Daño reparado:</td>
									<td align="left">
										Sí <html:radio name="DatosGeneralesReinsercionForm" property="reparacionDanio" title="Sí" value="1" disabled="true" /> 
										No <html:radio name="DatosGeneralesReinsercionForm" property="reparacionDanio" title="No" value="0" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right">Monto de daño: $</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="montoDanioReparado" readonly="true" size="10" maxlength="10"/>
									</td>
								</tr>
								<tr>
									<td align="right">¿Aplica para remisión parcial de la pena?:</td>
									<td align="left">
										Sí <html:radio name="DatosGeneralesReinsercionForm" property="remisionParcial" title="Sí" value="1" disabled="true" /> 
										No <html:radio name="DatosGeneralesReinsercionForm" property="remisionParcial" title="No" value="0" disabled="true" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<hr/>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Programas </legend>
							<div id="scrollProgramas" style="height: 100%; min-height: 173px; overflow-y:auto;">
								<table>
									<logic:iterate name="DatosGeneralesReinsercionForm" property="lstProgramas" id="programa">
										<tr>
											<td>
												<bean:write name="programa"/>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</fieldset>
					</td>
					<td colspan="2">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Puntos Obtenidos </legend>
							<table>
								<tr>
									<td align="right">Puntos acumulados:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="puntosAcumulados" readonly="true" size="10"/>
									</td>
								</tr>
								<tr>
									<td align="right">Puntos totales:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="puntosTotales" readonly="true" size="10"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<strong><bean:write name="DatosGeneralesReinsercionForm" property="porcentajeCumplido" /></strong>
									</td>
									<td align="left"><strong>% Cumplido</strong></td>
								</tr>
							</table>
						</fieldset>
					</td>
					<td colspan="2">
						<fieldset style="height: 100%; min-height: 173px;">
							<legend> Cómputo de pena: </legend>
							<table border="0">
								<tr>
									<td align="right">Fecha de salida:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="fechaFinPenaSTR" readonly="true" size="12" />
									</td>
								</tr>
								<tr>
									<td align="right">Porcentaje cubierto de pena:</td>
									<td align="left">
										<html:text name="DatosGeneralesReinsercionForm" property="porcentajeCubiertoPena" readonly="true" size="12"  />
									</td>
								</tr>
								<tr>
									<td align="right">Candidato a beneficio:</td>
									<td align="left">
										Sí <html:radio name="DatosGeneralesReinsercionForm" property="candidatoBeneficio" title="Sí" value="1" disabled="true" /> 
										No <html:radio name="DatosGeneralesReinsercionForm" property="candidatoBeneficio" title="No" value="0" disabled="true" />
									</td>
								</tr>
								<tr>
									<td align="right">Beneficio:</td>
									<td align="left">
										Liberación con condicional <html:radio name="DatosGeneralesReinsercionForm" property="beneficio" 
													title="Liberación con condicional" value="1" disabled="true" /><br/>
										Liberación con definitiva  <html:radio name="DatosGeneralesReinsercionForm" property="beneficio" 
													title="Liberación con definitiva" value="2>" disabled="true" />
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>		
			</table>
		</fieldset>
		
<html:hidden name="DatosGeneralesReinsercionForm" property="expedienteId" styleId="expedienteId" />
<html:hidden name="DatosGeneralesReinsercionForm" property="numeroExpedienteId" styleId="numeroExpedienteId" />
<html:hidden name="DatosGeneralesReinsercionForm" property="sentenciaId" styleId="sentenciaId" />