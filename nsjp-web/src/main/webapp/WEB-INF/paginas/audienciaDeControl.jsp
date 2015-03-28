<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Audiencia de Control</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />	
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />			
		<style type="text/css">
			dd p{line-height:120%}
			#iAudienciaDeControlPane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
			#iAudienciaDeControlPane dl{width:1000px;height:355px}	
			#iAudienciaDeControlPane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
			#iAudienciaDeControlPane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#68889b}
			#iAudienciaDeControlPane dt.active{cursor:pointer;color:#E78F08;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
			#iAudienciaDeControlPane dt.hover{color:#E78F08;}
			#iAudienciaDeControlPane dt.active.hover{color:#1C94C4}
			#iAudienciaDeControlPane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
			#iAudienciaDeControlPane .slide-number{color:#68889b;left:10px;font-weight:bold}
			#iAudienciaDeControlPane .active .slide-number{color:#fff;}
			#iAudienciaDeControlPane a{color:#68889b}
			#iAudienciaDeControlPane dd img{float:right;margin:0 0 0 0px;}
			#iAudienciaDeControlPane h2{font-size:2.5em;margin-top:10px}
			#iAudienciaDeControlPane .more{padding-top:10px;display:block}
		</style>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
		<script type="text/javascript">
			var idWindowIngresarTutor = 1;
			jQuery().ready(			
				function () {
					var id=<%= request.getAttribute("idInvolucrado")%>;
					//se crean los tabs y el acordeon
					//$("#tabsTestigo").tabs();
					$("#tabs").tabs();
					$('#iAudienciaDeControlPane').easyAccordion({ 
						autoStart: false, 
						slideInterval: 3000
					});
					//deshabilito los tabs
					habilitaDeshabilitaTabAcordeon("audienciaImputacionTab",0);
					$("#chkAudienciaControlCalificaDetencion").click(habilitaChkAudienciaImputacion);
					$("#chkAudienciaControlAudienciaImp").click(llevaAcaboAudienciaImputacion);
					//deshabilitamos los campos de nombre y apellidos
					$("#chkAudienciaControlAudienciaImp").attr("disabled","disabled");
					
					//agregamos el listener del evento click para el boton guardar
					//$("#iTestigoBtnGuardar").click(guardarTestigo);
					//Revisamos si es una consulta para llenar los campos
					//if(id!=null){
					//	seteaDatosConsultaTestigo(id);
					//}

					//ocultaDomicilioNotificaciones();
					$("#idDesignarAbogadoDefensorAudControl").click(DesignaraAbogadoDefensorAudControl);	
				});
				
			/************  SECCION INGRESA TESTIGO **********/

				/*
				* Funcion para deshabilitar el tab de un acordeon, se pasa el id del elemento DT
				* y un 0 para deshabilitar o un 1 para habilitar
				*/
				function habilitaDeshabilitaTabAcordeon(idTabAcordeon,bandera)
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
								
				/*
				*Metodo para habilitar el chk de audiencia de imputacion
				*/
				function habilitaChkAudienciaImputacion()
				{
					if ($("#chkAudienciaControlCalificaDetencion").is(':checked')) {
						$("#chkAudienciaControlAudienciaImp").attr("disabled","");
					}else{
						$("#chkAudienciaControlAudienciaImp").attr("disabled","disabled");
						$("#chkAudienciaControlAudienciaImp").attr("checked","");
					}
					llevaAcaboAudienciaImputacion();
				}
				
				//asociamos la funcion que atiende el evento click del check de servidor publico
				function llevaAcaboAudienciaImputacion() {
					if ($("#chkAudienciaControlAudienciaImp").is(':checked')) {
						habilitaDeshabilitaTabAcordeon1("audienciaImputacionTab",1);
					}else{
						habilitaDeshabilitaTabAcordeon1("audienciaImputacionTab",0);
					}				
				}
				
				function DesignaraAbogadoDefensorAudControl(){
					$.newWindow({id:"iframewindowDesignarAbogado", statusBar: true, posx:200,posy:50,width:705,height:200,title:"Designar Abogado Defensor", type:"iframe"});
				    $.updateWindowContent("iframewindowDesignarAbogado",'<iframe src="<%= request.getContextPath() %>/designarAbogadoDefensorDefensoria.do" width="705" height="200" />');
				}
				/************ FIN  SECCION INGRESA TESTIGO **********/
				
		</script>
	</head>
<body>
	
	<table border="0">
		<tr valign="top">
			<td>
				<table id="iTestigoViewHeader" width="100%" border="0">
					<tr>
						<td width="10%">DENUNCIA</td>
						<td width="70%">&nbsp;</td>
						<td width="30%" align="right">CALIDAD: Testigo</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table id="iTestigoWorkSheet" width="100%"  border="0">
					<tr valign="top">
						<td width="100%">
							<table width="100%">
								<tr>
									<td align="left">
										<input type="button" value="Designar Abogado Defensor" id="idDesignarAbogadoDefensorAudControl" class="btn_Generico"/>
									</td>
								</tr>
								<tr>
									<td align="left">
										<input type="checkbox"  id="chkAudienciaControlCalificaDetencion"> Calificar la detenci&oacute;n como legal. 
									</td>
								</tr>
								<tr>
									<td align="left">
										<input type="checkbox"  id="chkAudienciaControlAudienciaImp"> Llevar a cabo audiencia de imputaci&oacute;n.
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<!-- <tr valign="top">
						<td colspan="3">
							<table width="100%" >
								<tr valign="top">
									<td align="center">
										<input type="button" value="Modificar Datos" id="iTestigoBtnModificarDatos" class="btn_Generico"/>
										<input type="button" value="Guardar" id="iTestigoBtnGuardar" class="btn_Generico"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>-->
				</table>
			</td>
		</tr>
		<tr valign="top">
			<td>
				<table width="100%" border="0">
					<tr valign="top">
						<td width="100%" valign="top">
							<div id="iAudienciaDeControlPane" style="width: 100%" >
					            <dl>
					                <dt id="adminPreAudienciaControlTab">Administrar preliminares de audiencia</dt>
					                <dd>
					                	
									</dd>
					                <dt>Inicio de una audiencia</dt>
					                <dd>
					                	
                                    </dd>
					                <dt id="audienciaImputacionTab">Administrar Audiencia de imputaci&oacute;n</dt>
					                <dd>
					                	
					                </dd>
					                <dt>Administrar Final de Audiencia</dt>
					                <dd>
					                	
					                </dd>
					            </dl>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>