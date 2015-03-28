<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo perito</title>
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		creaMultiselect();
		$("#btnGuardar").click(cerrarVentana);
	});

	function cerrarVentana(){
		parent.cerrarVentanaNvoPerito();
	}

	function creaMultiselect(){
		$('#cbxInstitucion,#cbxEspecialidad').multiselect({ 
			multiple: false,
			header: "Seleccione",
			position: {
				my: 'center',
				at: 'center'
			},
			height:"auto",
			selectedList: 1 
		});

		
	}
	</script>
</head>
<body>
	<div style="background-color: #EEEEEE;">
	<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="400" border="0" cellspacing="5" cellpadding="0">
				      <tr>
				      	<td width="30%" >Nombre(s): </td>
				        <td width="70%"><input type="text" id="txtNombre" style="width: 100%;" /></td>
				      </tr>
				      <tr>
				      	<td width="30%" >Apellido Paterno: </td>
				        <td width="70%"><input type="text" id="txtApPaterno" style="width: 100%;"/></td>
				      </tr>
				      <tr>
				      	<td width="30%" >Apellido Materno: </td>
				        <td width="70%"><input type="text" id="txtApMaterno" style="width: 100%;"/></td>
				      </tr>
				       <tr>
				      	<td width="30%" >Correo Electr&oacute;nico: </td>
				        <td width="70%"><input type="text" id="txtEmail" style="width: 100%;"/></td>
				      </tr>
				       <tr>
				      	<td width="30%" >Tel&eacute;fono: </td>
				        <td width="70%"><input type="text" id="txtTelefono" style="width: 100%;"/></td>
				      </tr>
				      <tr>
				        <td width="30%" >Instituci&oacute;n: </td>
				        <td width="70%">
				        	<select id="cbxInstitucion" style="width: 100px;">
								<option value="-1">-Seleccione-</option>
								<option>Poder Judicial</option>
								<option>Defensor&iacute;a</option>
							</select>
				        </td>
				      </tr>
				      <tr>
				        <td width="30%" >Especialidad: </td>
				        <td width="70%">
				        	<select id="cbxEspecialidad" style="width: 100px;">
								<option value="-1">-Seleccione-</option>
								<option>Discapacidad Auditiva</option>
								<option>Impacto Ambiental</option>
							</select>
				        </td>
				      </tr>
				      <tr>
				      	<td>&nbsp;</td>
				      	<td align="center"><input id="btnGuardar" type="button" value="Guardar" class="btn_Generico"/></td>
				      </tr>
					</table>	
				</td>
			</tr>
		</table>
	</div>
</body>
</html>