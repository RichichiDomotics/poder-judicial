<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configurar Cargas de Trabajo</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#tabsprincipalconsulta").tabs();

	});
</script>

<style>
#tabs {
	
}

.tabs-bottom {
	height: 360px;
	position: relative;
}

.tabs-bottom .ui-tabs-panel {
	height: 330px;
	overflow: auto;
}

.tabs-bottom .ui-tabs-nav {
	position: absolute !important;
	left: 0;
	bottom: 0;
	right: 0;
	padding: 0 0.2em 0.2em 0;
}

.tabs-bottom .ui-tabs-nav li {
	margin-top: -2px !important;
	margin-bottom: 1px !important;
	border-top: none;
	border-bottom-width: 1px;
}

.ui-tabs-selected {
	margin-top: -3px !important;
}
</style>

</head>

<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Puntos por Tipo de
					Audiencia</a>
			</li>
		</ul>
		<div id="tabsconsultaprincipal-1" style="height: 400">

			<table width="530px">

				<tr>
					<td width="53">&nbsp;</td>
					<td width="148">&nbsp;</td>
					<td width="213" align="right">&nbsp;</td>
					<td width="96">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tipo de Audiencia:</td>
					<td width="213">Puntos</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">Imputación:</td>
					<td valign="top"><select name="select">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
					</select>
					</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">Vinculación:</td>
					<td valign="top"><select name="select2">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
					</select>
					</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">Control:</td>
					<td valign="top"><select name="select3">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
					</select>
					</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">Juicio Oral:</td>
					<td valign="top"><select name="select4">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
					</select>
					</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top"><input type="button" value="Guardar" class="btn_Generico"/>
					</td>
					<td valign="top"><input type="button" value="Cancelar" class="btn_Generico"/>
					</td>
					<td valign="top">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
				</tr>


			</table>



		</div>
	</div>
</body>
</html>