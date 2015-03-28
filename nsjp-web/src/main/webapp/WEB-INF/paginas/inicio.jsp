<%@page language="java" contentType="text/html;charset=ISO-8859-1"%>

<!-- Column 1 start -->
<div id="head_colCentro">
<div id="buscar_head_colCentro">
<form action="" method="get" name="form_head_colCentro"><input
	name="Buscar" type="text" value="Buscar" /><input name="Buscar"
	type="image" src="<%=request.getContextPath()%>/images/ico_buscar_head_colCentro.jpg"
	class="ico_buscar_head_colCentro" /></form>
</div>
<div id="botones_head_colCentro"><a href="#"><img
	src="images/ico_previa_head_colCentro.jpg" width="19" height="19"
	alt="Activar / Desactivar panel de vista previa"
	title="Activar / Desactivar panel de vista previa" /></a></div>
<div class="limpiador"></div>
</div>
<div id="colCentro_tabla">

	<table width="100%" border="0" cellspacing="3" cellpadding="3">
		<tr>
			<th scope="col">&nbsp;</th>
			<th scope="col">Estado</th>
			<th scope="col">Fecha</th>
			<th scope="col">Nombre</th>
			<th scope="col">Resumen</th>
		</tr>
		<tr class="roja">
			<td bgcolor="#990000">&nbsp;</td>
			<td>Rojo</td>
			<td>03-Feb-11</td>
			<td>Carmona</td>
			<td>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Suspendisse feugiat neque ac mauris...</td>
		</tr>
		<tr class="verde">
			<td bgcolor="#006600">&nbsp;</td>
			<td>Verde</td>
			<td>03-Feb-11</td>
			<td>Aguilar</td>
			<td>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Suspendisse feugiat neque ac mauris...</td>
		</tr>
		<tr class="amarilla">
			<td bgcolor="#FFCC00">&nbsp;</td>
			<td>Amarilo</td>
			<td>03-Feb-11</td>
			<td>Aplataco</td>
			<td>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Suspendisse feugiat neque ac mauris...</td>
		</tr>
	</table>

</div>

	<table width="50%" border="0" cellspacing="2" cellpadding="2" align="center">
	<tr>
		<td><img src="<%=request.getContextPath()%>/images/ico_primera.png" width="16" height="16"
			alt="Ultima" /></td>
		<td><img src="<%=request.getContextPath()%>/images/ico_anterior.png" width="16" height="16"
			alt="Ultima" /></td>
		<td>Mostrando 5 de 10</td>
		<td><a href="#"><img src="<%=request.getContextPath()%>/images/ico_siguiente.png"
			width="16" height="16" alt="Ultima" /></a></td>
		<td><a href="#"><img src="<%=request.getContextPath()%>/images/ico_ultima.png" width="16"
			height="16" alt="Ultima" /></a></td>
	</tr>
	</table>


<div id="colCentro_vistaPrevia">
<div id="vistaPrevia_resumen">Resumen</div>
<div id="vistaPrevia_acordeon">
<div class="acordeon_solapa">
<div id="solapa_personas_titulo">Título personas</div>
<div id="solapa_personas_contenido">Contenido personas</div>
</div>
</div>
</div>
<!-- Column 1 end -->
