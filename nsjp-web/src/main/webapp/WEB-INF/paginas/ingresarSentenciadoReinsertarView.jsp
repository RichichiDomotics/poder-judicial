<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Víctima</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
		<style type="text/css">
			dd p{line-height:120%}
			#iVictimaAccordionPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iVictimaAccordionPane dl{width:1000px;height:355px}	
			#iVictimaAccordionPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#26526c}
			#iVictimaAccordionPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#68889b}
			#iVictimaAccordionPane dt.active{cursor:pointer;color:#fff;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iVictimaAccordionPane dt.hover{color:#68889b;}
			#iVictimaAccordionPane dt.active.hover{color:#fff}
			#iVictimaAccordionPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iVictimaAccordionPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iVictimaAccordionPane .active .slide-number{color:#fff;}
			#iVictimaAccordionPane a{color:#68889b}
			#iVictimaAccordionPane dd img{float:right;margin:0 0 0 0px;}
			#iVictimaAccordionPane h2{font-size:2.5em;margin-top:10px}
			#iVictimaAccordionPane .more{padding-top:10px;display:block}
		</style>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
		<script type="text/javascript">
			jQuery().ready(			
				function () {
					$( "#tabs" ).tabs();
					
					$("#iVictimaAccordionDialogoMenorEdad").dialog({ autoOpen: false, 
						modal: true, 
						title: 'Menor de Edad', 
						dialogClass: 'alert', 
						width: 500 ,
						maxWidth: 600,
						buttons: {"Aceptar":function() {
											$(this).dialog("close");
										}
									} 
					});
					$('#iVictimaAccordionPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});
					$("#admMedCautelares").click(cautelares);
					$("#asignarPersona").click(asignarPersona);
					$("#digitalizarDocumento").click(digiDocu);
					habilitaDeshabilitaTabAcordeon1("mediosCautelaPesta",0);
					habilitaDeshabilitaTabAcordeon1("asignarPerso",0);
					habilitaDeshabilitaTabAcordeon1("digitalizarDocument",0);
				});
			
				function cautelares() {
					if ($("#admMedCautelares").is(':checked')) {
						habilitaDeshabilitaTabAcordeon("mediosCautelaPesta","datosGenerales",1);
						
					}else{
						habilitaDeshabilitaTabAcordeon("mediosCautelaPesta","datosGenerales",0);			
					}		
				}
				function asignarPersona() {
					if ($("#asignarPersona").is(':checked')) {
						habilitaDeshabilitaTabAcordeon("asignarPerso","datosGenerales",1);
					}else{
						habilitaDeshabilitaTabAcordeon("asignarPerso","datosGenerales",0);
					}
				}
				
				function digiDocu() {
					if ($("#digitalizarDocumento").is(':checked')) {
						habilitaDeshabilitaTabAcordeon("digitalizarDocument","datosGenerales",1);
					}else{
						habilitaDeshabilitaTabAcordeon("digitalizarDocument","datosGenerales",0);
					}
				}

				/*
				* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
				* y un 0 para deshabilitar o un 1 para habilitar
				*/
				function habilitaDeshabilitaTabAcordeon(idTabAcordeon,idTabAcordeonActive,bandera)
				{
					if(parseInt(bandera)==0)//Deshabilita el tab del acordeon
					{
						$("#"+idTabAcordeon).unbind('click');
						$("#"+idTabAcordeonActive).click();
						if($("#"+idTabAcordeon).hasClass('active'))
						{
							$("#"+idTabAcordeon).removeClass('active').addClass('inactive');
						}
						else
						{
							$("#"+idTabAcordeon).removeClass('no-more-active').addClass('inactive');
						}
					}
					else//habilita los tabs del acordeon
					{
						$("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
						$("#"+idTabAcordeon).click(function(){		
							jQuery($("#"+idTabAcordeon)).activateSlide();
							//clearTimeout(timerInstance.value);
						});
					}
				}

				/*
				* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
				* y un 0 para deshabilitar o un 1 para habilitar
				*/
				function habilitaDeshabilitaTabAcordeon1(idTabAcordeon,bandera)
				{
					if(parseInt(bandera)==0)//Deshabilita el tab del acordeon
					{
						$("#"+idTabAcordeon).unbind('click');
						if($("#"+idTabAcordeon).hasClass('active'))
						{
							$("#"+idTabAcordeon).removeClass('active').addClass('inactive');
							$("#"+idTabAcordeon).parent().find('dt.no-more-active:first').click();
						}
						else
						{
							$("#"+idTabAcordeon).removeClass('no-more-active').addClass('inactive');
						}
					}
					else//habilita los tabs del acordeon
					{
						$("#"+idTabAcordeon).removeClass('inactive').addClass('no-more-active');
						$("#"+idTabAcordeon).click(function(){		
							jQuery($("#"+idTabAcordeon)).activateSlide();
							//clearTimeout(timerInstance.value);
						});
					}
				}
				
		</script>
	</head>
<body style="font-size:70.5%;">
	
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iVictimaViewHeader" width="100%" border="0">
					<tr>
						<td width="10%">Denuncia</td>
						<td width="70%">&nbsp;</td>
						<td width="30%" align="right">Expediente: XXXXXXXXX</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iVictimaWorkSheet" width="100%"  border="0">
					<tr valign="top">
						<td>
							<table>
								<tr>
									<td>
										<img alt="foto" src="<%= request.getContextPath() %>/resources/images/foto.png" id="iVictimaCmpFoto">
									</td>
<!--									<td>-->
<!--										<img alt="huella" src="<%= request.getContextPath() %>/resources/images/fingerPrint.JPG" width="105px" height="105px" id="iVictimaCmpHuella">-->
<!--									</td>-->
								</tr>
								
							</table>
						</td>
						<td>
							<table width="100%" border="0">
								<tr>
									<td align="left">
										<input type="checkbox" value="false" id="admMedCautelares"/> Adm. Med. Cautelares
									</td>
								</tr>
								<tr>
									<td  align="left">
										<input type="checkbox" value="false" id="asignarPersona"/> Asignar Persona
									</td>
								</tr>
								<tr>
									<td  align="left">
										<input type="checkbox" value="false" id="digitalizarDocumento"/> Digitalizar Documento	
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr valign="top">
						<td colspan="3">
							<table width="100%" >
								<tr valign="top">
									<td align="center">
										<input type="button" value="Abrir Expediente" id="abrirExpediente" class="btn_Generico"/>
										<input type="button" value="Elaborar Oficio" id="oficio" class="btn_Generico"/>
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iVictimaAccordionPane" style="width=100%" >
					            <dl>
					                <dt id="datosGenerales">Datos Generales</dt>
					                <dd>
					                	<iframe src="<%= request.getContextPath() %>/DatosGenerales.do" width="100%" height="100%" scrolling="no" frameborder="0">
					                	</iframe>
									</dd>
					                <dt id="mediosCautelaPesta">Admin. Medios de Cautela</dt>
					                <dd><p>Aqui va el CU medio de cautela</p></dd>
					               
					                <dt id="asignarPerso">Asignar Persona</dt>
					                <dd><p> Aqui va el CU Asignar Persona</p></dd>
					               
					                <dt id="digitalizarDocument">Digitalizar documento</dt>
					                <dd><p> Aqui va el CU Digitalizar documento</p></dd>
					              </dl>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<div id="iVictimaAccordionDialogoMenorEdad" title="Menor de edad">
	<table>
		<tr>
			<td>Denuncia</td>
			<td>&nbsp;</td>
			<td>Expediente</td>
		</tr>
		<tr>
			<td>Calidad:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iVictimaDialogoCmpCalidad"/></td>
		</tr>
		<tr>
			<td>Nombre Tutor:</td>
			<td><input type="text" value="A" size="50" maxlength="40" id="iVictimaDialogoCmpNombre"/></td>
		</tr>
	</table>
</div>

</body>
</html>