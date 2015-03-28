<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Vincular Expediente a Caso</title>
		<script type="text/javascript">
			var reloadGridAlias = false;
			jQuery().ready(			
				
				
		</script>
	</head>
<body>
	<table border="0" width="100%">
		<tr valign="top">
			<td>
				<table border="0" width="95%" height="50%">
					<tr>
						<td width="40%">Ingresar Numero de Caso</td>
						<td width="40%"><input type="text" title="Numero caso" size="50" id="iNumeroCaso"/></td>
						<td width="20%"> <button id="iBuscarCaso" class="btn_Generico">Buscar Caso</button></td>
					</tr>
					<tr>
						<td width="40%"><button id="iBuscarCaso" class="btn_Generico">Relacionar expediente a caso y salir</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<table width="95%">
				<tr>
					<td> alojamiento para el caso de uso buscar caso
<!--					<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>-->
					</td>
				</tr>
			</table>
		</tr>
	</table>


</body>
</html>