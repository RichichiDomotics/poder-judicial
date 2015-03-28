<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<style type="text/css">
			dd p{line-height:120%}
			#iRepLegalAccordionPane{width:1000px;height:450px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iRepLegalAccordionPane dl{width:1000px;height:450px}	
			#iRepLegalAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iRepLegalAccordionPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iRepLegalAccordionPane dt.hover{color:#E78F08;}
			#iRepLegalAccordionPane dt.active.hover{color:#1C94C4}
			#iRepLegalAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iRepLegalAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iRepLegalAccordionPane .active .slide-number{color:#fff;}
			#iRepLegalAccordionPane a{color:#68889b}
			#iRepLegalAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iRepLegalAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iRepLegalAccordionPane .more{padding-top:10px;display:block}
		</style>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript">
	var outerLayout, innerLayout;
	
	$(document).ready(function() {
		

		$( "#tabs" ).tabs();	
		$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
		modal: true, 
		title: 'Menor de Edad', 
		dialogClass: 'alert', 
		width: 400 ,
		maxWidth: 400,
		buttons: {"Aceptar":function() {
							$(this).dialog("close");
						}
					} 
	});		

												
	$('#iRepLegalAccordionPane').easyAccordion({ 
		autoStart: false, 
		slideInterval: 3000
	});

	$("#cmbInstitucion, #cmbLocalizador").multiselect({ 
		multiple: false, 
		header: "Seleccione", 
		position: { 
			my: 'top', 
			at: 'top' 
		},
		selectedList: 1 
	});
	$("#cmbEncargadoIns").multiselect({ 
		multiple: true, 
		header: "Seleccione", 
		minWidth: 300,
		position: { 
			my: 'top', 
			at: 'top' 
		},
		selectedList: 3 
		
	});
	
		});	

	</script>	

<body>
<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iRepLegalAccordionPane" style="width: 100%" >
					            <dl>
					                 <dt>Internar a centro de salud</dt>
					                <dd>
					                <table><tr><td>Nombre del Centro de Salud:<input type="text" size="20" /></td></tr></table>                       
					                </dd>
					                <dt>Suspender derechos</dt>
					                <dd></dd>
					                <dt>Localizadores electrónicos</dt>
					                <dd>
					                	  <table><tr><td>Localizador Electronico:<select id="cmbLocalizador"><option>Radio</option></select></td></tr></table>                       
					                </dd>
					                <dt>Presentaciones periódicas</dt>
					                <dd>
					                	<table width="530px"><tr><td colspan="2">Presentarse en:<select id="cmbInstitucion"><option>Defensoria</option></select></td></tr>
					                			<tr><td colspan="2">Presentarse con:<select id="cmbEncargadoIns"><option>Juan Hernandez - Coordinador</option></select></td></tr>
					                			<tr><td colspan="2">Día(s):<input type="text" size="20" /></td></tr>
					                    		<tr><td colspan="2">Hora:<input type="text" size="20" /></td></tr>
					                    		<tr><td width="81">&nbsp;</td>
					                    		  <td width="437">&nbsp;</td>
					                    		</tr>
					                    		<tr>
					                    		  <td valign="top" >Descripci&oacute;n:</td>
					                    		  <td valign="top" ><textarea name="textarea" cols="15" rows="1"></textarea></td>
                                                </tr>        	
					                	</table>

					                </dd>
					                <dt>Someter a vigilancia</dt>
					                <dd>
					                
					                
					                </dd>
					                <dt>Arresto domiciliario</dt>
					                <dd>
					                </dd>
					                <dt>Separación de domicilio</dt>
					                <dd></dd>
					                <dt>Prohibir salida territorial</dt>
					                <dd>
							<table width="530px">
								<tr>
									<td width="40">Territorio:</td>
									<td width="40"><select name="cmbProhibicion2"
										id="cmbProhibicion2">
											<option>Estado</option>
											<option>Delegación ó Municipio</option>
											<option>Otro</option>
									</select>
									</td>
									<td width="282" align="right">Delegaci&oacute;n &oacute;
										Municipio:</td>
									<td><select name="cmbProhibicion" id="cmbProhibicion">
											<option>Iztacalco</option>
											<option>Coyoacan</option>
									</select>
									</td>
								</tr>
								<tr>
									<td>Estado:</td>
									<td><select name="cmbProhibicion3" id="cmbProhibicion3">
											<option>México</option>
											<option>Chiapas</option>
											<option>Puebla</option>
									</select>
									</td>
									<td align="right" valign="top">Otro:</td>
									<td width="150"><textarea name="textarea" cols="30"
											rows="2"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2" valign="top">&nbsp;</td>
									<td valign="top">&nbsp;</td>
									<td valign="top">&nbsp;</td>
								</tr>


							</table>


						</dd>
					                <dt>Prohibir convivencia</dt>
					                <dd>
							<table width="530px">
								<tr>
									<td width="82">Víctimas:</td>
									<td width="282" align="right">Tipo de Prohibici&oacute;n:
									</td>
									<td><select name="cmbProhibicion" id="cmbProhibicion">
											<option>Defensoria</option>
									</select>
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;<input type="checkbox" />Uno</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;<input type="checkbox" />Dos</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;<input type="checkbox" />Tres</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
									<td width="150">&nbsp;</td>
								</tr>
								<tr>
									<td valign="top">Descripci&oacute;n:</td>
									<td valign="top"><textarea name="textarea" cols="30"
											rows="2"></textarea>
									</td>
									<td valign="top">&nbsp;</td>
								</tr>


							</table>

						</dd>
					                <dt>Prisión preventiva</dt>
					                <dd>
					                	<table><tr><td colspan="2">Reclusorios:<select id="cmbReclu">
					                	<option>Norte</option>
					                	<option>Sur</option>
					                	</select></td></tr>
					                	<tr><td width="81">&nbsp;</td>
					                    		  <td width="437">&nbsp;</td>
					                    		</tr>
					                    		<tr>
					                    		  <td valign="top" >Descripci&oacute;n:</td>
					                    		  <td valign="top" ><textarea name="textarea" cols="15" rows="1"></textarea></td>
                                                </tr>  
					              </table>
					                </dd>
					            </dl>
							</div>
						</td>
					</tr>
				</table>

</body>
</html>