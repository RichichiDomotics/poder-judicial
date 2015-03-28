<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Avisos de Auxilio</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.weekcalendar.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/date.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.weekcalendar.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>	
		

	<!--css para ventanas-->

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>


<script type="text/javascript">
	var idindi;
	var unidadInvestigacion='';
	var numeroExpediente = "";
	var avisoId;
	var estatus='';

	$(document).ready(function() {
		avisoId ='<%= request.getParameter("avisoId")%>';
		estatus ='<%= request.getParameter("estatus")%>'; 

		//Se crean las tabs principales
		$( "#tabs" ).tabs();
		$("#tabsprincipalconsulta" ).tabs();
		$("#divRechazoAviso").hide();
		$('#divProcesaAviso').hide();
		$('#divEstatusAviso').hide();

		$("#llamadaProsperaSi").bind("click",muestraCausas);
		$("#llamadaProsperaNo").bind("click",ocultaCausas);
		
		$('input:text').attr("disabled", true);

		consultaAviso(avisoId);
		cargaMotivoRechazoAviso();
		validaEstatusAviso(estatus);	
		deshabilitaFuncionalidadDomicilio();
	
	});

	function validaEstatusAviso(estatus){
		if(estatus == '<%=EstatusNotificacion.NO_ATENDIDA.getValorId()%>'){
			$('#divEstatusAviso').hide();
			$('#divChkAviso').show();
		}else if(estatus == '<%=EstatusNotificacion.ATENDIDA.getValorId()%>'){
			$('#divEstatusAviso').show();
			$('#divChkAviso').hide();
		}
	}
	
	function ocultaCausas(){
		$('#divProcesaAviso').hide();
		$('#divRechazoAviso').show();
	}

	function muestraCausas(){
		$('#divProcesaAviso').show();
		$('#divRechazoAviso').hide();
	}

	function  consultaAviso(avisoId){

		  $.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/consultarAvisoAuxilioXIdSSPPolicia.do?avisoId='+avisoId+'',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$('#datosGeneralesCmpCalidad').val($(xml).find('avisoHechoDTO').find('calidadImplicado').find('valor').text());			   
				$('#datosGeneralesCmpDelito').val($(xml).find('avisoHechoDTO').find('catDelito').find('nombre').text());			   
				$('#datosGeneralesCmpUnidad').val($(xml).find('avisoHechoDTO').find('catDelito').find('departamento').find('nombreDepto').text());			   

				$('#solicitanteNombre').val($(xml).find('avisoHechoDTO').find('nombreImplicado').text());			   
				$('#solicitanteAPaterno').val($(xml).find('avisoHechoDTO').find('apellidoPatImplicado').text());			   
				$('#solicitanteAMaterno').val($(xml).find('avisoHechoDTO').find('apellidoMatImplicado').text());		

				if($(xml).find('avisoHechoDTO').find('motivoRechazo').text()){
					$('#estatusAviso').val("Rechazado por: "+$(xml).find('avisoHechoDTO').find('motivoRechazo').find('valor').text());	   
				}else{
					$('#estatusAviso').val("Aviso Procesado");	   
				}
				var idImplicado=$(xml).find('avisoHechoDTO').find('idImplicado').text();
				idindi=idImplicado;
				pintaDatosDomicilio(xml);
			    desavilitarDatosDomicilio();
			    consultaGridsMediosDeContactoImplicado(idImplicado);
		    }
	    });
	}

	/*
	*Funcion que dispara el Action para consultar el tipo de rechazo al aviso
	*/		
	function cargaMotivoRechazoAviso(){
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoRechazoAviso.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				var option;
				$(xml).find('catRechazoAviso').each(function(){
					$('#catRechazoAviso').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	
	/*
	*Funcion que dispara ajusta funcionalidad de domicilio para este caso
	*/	
	function deshabilitaFuncionalidadDomicilio(){
		killDomicilioNotificaciones();
		killCoordenadasGeograficas();
	}

	function cerrarAviso(){
		var param="avisoId="+avisoId;
		
	    param+="&rechazoAviso="+$("#catRechazoAviso option:selected").val();
		
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/asignarMotivoRechazoAvisoHDelictivo.do',
			data:param,
			dataType: 'xml',
			async: false,
			success: function(xml){
				alertDinamicoCerrar("El aviso fue cerrado correctamente",1);	
			}
		});
	}

	function procesarAviso(){
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/procesarAvisoHDelictivo.do?avisoId='+avisoId+'',
			dataType: 'xml',
			async: false,
			success: function(xml){
				alertDinamicoCerrar("El aviso fue procesado correctamente",2);
			}
		});
	}

	function enviarAviso(){
		$.ajax({
			type: 'POST',
			url:  '<%= request.getContextPath()%>/enviarAvisoHDelictivo.do?avisoId='+avisoId+'',
			dataType: 'xml',
			async: false,
			success: function(xml){
				alertDinamicoCerrar("El aviso se envió correctamente",3);	
			}
		});
	}

	//Función para alertDinamicoCerrar
	function alertDinamicoCerrar(textoAlert,identificador){						
		$("#divAlertTextoCerrar").html(textoAlert);
	    $( "#dialog-AlertCerrar" ).dialog({
			autoOpen: true,
			resizable: false,
			modal: true,
			buttons: {							
				"Aceptar": function() {					
					if((identificador==1) || (identificador==3)){
						window.parent.cargaGridAvisosAuxilio(estatus);
						window.parent.cerrarVentanaAviso();								
					}
					else if(identificador==2){
						enviarAviso();	
					}
					$( this ).dialog( "close" );
					$("#divAlertTextoCerrar").html("");					
				}				
			}
		});    
	 }
	
</script>

</head>

<body>
<!-- div para el alert dinamico antes de cerrar ventana -->
<div id="dialog-AlertCerrar" style="display: none">
	<table align="center">
		<tr>
        	<td align="center">
            	<span id="divAlertTextoCerrar"></span>
            </td>
        </tr>
     </table>              
</div>    
    <div class="error1" style="display:none;">
      <img src="<%= request.getContextPath()%>/resources/images/warning.gif" alt="Warning!" width="24" height="24" style="float:left; margin: -5px 10px 0px 0px; " />

      <span></span>.<br clear="all"/>
    </div>



	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Reporte</a>
			</li>
			<li><a href="#tabsconsultaprincipal-2">Ingresar Lugar de los Hechos</a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-1">
			<fieldset>
		    <legend>Solicitante</legend>
			<table width="100%">
				<tr align="center">
					<td align="center" colspan="2">
					</td>
                    <td width="100%" rowspan="10">                           
						<jsp:include page="ingresarMediosContactoView.jsp"/>
                    </td>
				</tr>
			    <tr>
				    <td align="right">Calidad del Solicitante:</td>
				    <td align="left">
						<input type="text" size="50" maxlength="30" id="datosGeneralesCmpCalidad"/>
			        </td>
			    </tr>
				<tr>
					<td align="right">Delito:</td>
					<td align="left">
						<input type="text" size="50" maxlength="30" id="datosGeneralesCmpDelito"/>
	  			    </td>
				</tr>
				<tr>
					<td  align="right">Unidad de investigaci&oacute;n:</td>
					<td>
						<input type="text" size="50" maxlength="30" id="datosGeneralesCmpUnidad"/>
					</td>
				</tr>
				<tr>
					<td align="right"> 
						Nombre (s):
					</td>
					<td>
						<input type="text" size="50" maxlength="50" id="solicitanteNombre"  />
					</td>
                         </tr>
				<tr>
					<td align="right">
						Apellido Paterno:
					</td>
					<td>
						<input type="text" class="" size="50" maxlength="50" id="solicitanteAPaterno"  />
					</td>
                         </tr>
				<tr>
					<td align="right">
						Apellido Materno:
					</td>
					<td>
						<input type="text" class="" size="50" maxlength="50" id="solicitanteAMaterno"  />
					</td>
                </tr>
			</table>
			<div id="divChkAviso">
				<table>
			        <tr>
			          <td  height="25" colspan="3" align="left">&iquest;La llamada de auxilio prospera? 
			            <input type="radio" name="rbtLlamada" id="llamadaProsperaSi" value="1"/>
			            <label for="llamadaProsperaSi2">Si</label>
			            <input type="radio" name="rbtLlamada" id="llamadaProsperaNo" value="0"/>
			            <label for="llamadaProsperaNo2">No</label>
			          </td>
			        </tr>
				</table>
			</div>
		    <div id="divEstatusAviso">
				<table>
					<tr>
						<td width="22.5%" align="right">
							Estatus:
						</td>
						<td>
							<input type="text" size="50" maxlength="50" id="estatusAviso"  />
						</td>
					</tr>
				</table>
			</div>
			</fieldset>
		   <div id="divRechazoAviso">
				<table width="100%" border="0">
					<tr>
						<td align="right" width="35%">
							Motivo de Rechazo:
						</td>
						<td align="left" width="25%">
							<select id="catRechazoAviso" style="width: 180px;">
							<option value="-1">- Seleccione -</option>
							</select>
						</td>
						<td width="40%" align="left">
							<input name="" type="button" id="btnCerrarAviso" value="Cerrar Aviso" onclick="cerrarAviso();" class="btn_Generico"/>
						</td>
					</tr>
				</table>
			</div>
		   <div id="divProcesaAviso">
				<table width="100%" border="0">
					<tr>
						<td width="100%" align="center">
							<input name="" type="button" id="btnProcesaAviso" value="Procesar Aviso" onclick="procesarAviso();" class="btn_Generico"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div id="tabsconsultaprincipal-2">
			<table width="762px" height="313px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td>
						<jsp:include page="ingresarDomicilioView.jsp"/>
					</td>
				</tr>
			</table>
		</div>

	</div>

</body>
</html>
