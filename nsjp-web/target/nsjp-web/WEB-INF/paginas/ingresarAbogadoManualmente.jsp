<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar Abogado Manualmente</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#tabsprincipalconsulta" ).tabs();
	});

	function bloqueaSeleccion(controlID){
		$('#' + controlID).attr('checked','checked');
	}

	function cambiaListaAbogados(){
		var idTipoAbogado = $("#lstEtapas option:selected").val();
		$('#lstAbogados').empty();
		for(i=0;i<10;i++){
			if(idTipoAbogado==0){
				$('#lstAbogados').append('<option value="Abogado Int ' + i +'">Abogado Int '+ i + '</option>');
			}else if(idTipoAbogado==1){
				$('#lstAbogados').append('<option value="Abogado Legal ' + i +'">Abogado Legal '+ i + '</option>');
			}
			else if(idTipoAbogado==2){
				$('#lstAbogados').append('<option value="Abogado Defensa ' + i +'">Abogado Defensa '+ i + '</option>');
			}
			else if(idTipoAbogado==3){
				$('#lstAbogados').append('<option value="Abogado Alternativa ' + i +'">Abogado Alternativa '+ i + '</option>');
			}
			else if(idTipoAbogado==4){
				$('#lstAbogados').append('<option value="Abogado Externo ' + i +'">Abogado Externo '+ i + '</option>');
			}
		}
	}

	function muestraNombreAbogado(){
		$("#iVictimaCmpNombre").val($("#lstAbogados option:selected").val());
	}
	</script>
</head>
<body>
	<table width="100%" height="100%" border="0">
		<tr height="70%">
			<td>
				<table width="100%" border="0" height="100%" cellspacing="1">
					<tr>
						<td>&nbsp;</td>
					</tr>
			      	<tr>
			      		<td align="center">
			      			Etapa
			      		</td>
			      		<td align="center">
			      			Abogados Disponibles
			      		</td>
			      		<td align="center">
			      			Datos del Abogado
			      		</td>
			      	</tr>
			      	<tr>
						<td>&nbsp;</td>
					</tr>
			      	<tr>
			      		<td rowspan="2" valign="top" align="center">
			      			<select id="lstEtapas" style="width: 180px;" onchange="cambiaListaAbogados();">
								<option value="0">Integración</option>
								<option value="1">Asesoría Legal</option>
								<option value="2">Defensa Técnica</option>
								<option value="3">Alternativa - Restaurativa</option>
								<option value="4">Abogado Externo</option>
							</select>
			      			<!-- input type="button" value="Integración" id="txtIntegracion" readonly="readonly" style="border: 0;" onclick="cambiaListaAbogados(0);"/-->
			      		</td>
			      		<td valign="top" align="center">
			      			<table width="100%">
						      	<tr>
						      		<td align="center">
						      			<select id="lstAbogados" name="datosGeneralesCmpIdioma" style="width: 180px;" size="4" onchange="muestraNombreAbogado();">
											<option value="Abogado Int 0">Abogado Int 0</option>
											<option value="Abogado Int 1">Abogado Int 1</option>
											<option value="Abogado Int 2">Abogado Int 2</option>
											<option value="Abogado Int 3">Abogado Int 3</option>
											<option value="Abogado Int 4">Abogado Int 4</option>
											<option value="Abogado Int 5">Abogado Int 5</option>
											<option value="Abogado Int 6">Abogado Int 6</option>
											<option value="Abogado Int 7">Abogado Int 7</option>
											<option value="Abogado Int 8">Abogado Int 8</option>
											<option value="Abogado Int 9">Abogado Int 9</option>
										</select>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td align="center">
						      			<input type="button" value="Solicitar Abogado Externo" class="btn_Generico"/>
						      		</td>
						      	</tr>
						     </table>
			      			
			     		</td>
			     		<td rowspan="2" align="center">
			     			<table width="100%" style="border: 0; background:#DDD;">
						      	<tr>
						      		<td align="right">
						      			Nombre:
						      		</td>
						      		<td>
						      			<input type="text" value="" size="50" maxlength="40" id="iVictimaCmpNombre"  readonly="readonly" style="background:#DDD;border: 0;" class="required"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td align="right">
						      			Teléfono:
						      		</td>
						      		<td>
						      			<input type="text" value="" size="50" maxlength="40" id="iVictimaCmpNombre"  readonly="readonly" style="background:#DDD;border: 0;" class="required"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td align="right">
						      			Correo:
						      		</td>
						      		<td>
						      			<input type="text" value="" size="50" maxlength="40" id="iVictimaCmpNombre"  readonly="readonly" style="background:#DDD;border: 0;" class="required"/>
						      		</td>
						      	</tr>
						      	<tr>
						      		<td colspan="2" align="center">
						      			<input type="button" value="Consultar Agenda" class="btn_Generico"/>
						      		</td>
						     	</tr>
						     </table>
			     		</td>
			     	</tr>
			     </table>
			 </td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr height="30%">
			<td>
				<table width="100%" height="100%" border="0">
			     	<tr align="center">
			     		<td align="center"">
			     			<input type="button" value="Enviar Notificación" class="btn_Generico"/>
			     		</td>
			     	</tr>
			     </table>
		     </td>
		</tr>
	</table>
	
     

</body>
</html>