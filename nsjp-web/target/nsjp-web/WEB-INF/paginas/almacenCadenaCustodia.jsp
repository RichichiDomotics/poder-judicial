<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript">
		

		$(document).ready(function() {
			 $( "#tabs" ).tabs();
			 $( "#tabschild" ).tabs();
			 $( "#tabschild2" ).tabs();
			 $( "#tabschild3" ).tabs();
			 $( "#tabschild4" ).tabs();
			 $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			
			$('a[name*="padre"]').click(function(event){
				var elem = $(this).next();
				if(elem.is('ul')){
					event.preventDefault();
					$('#menu ul:visible').not(elem).slideUp();
					elem.slideToggle();
					}
				});
		});
		

	</script>
	
	<!--ARCHIVOS PARA MENU DE ARBOL-->        
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/simpletreemenu.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/simpletree.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/menu.css"  type="text/css">
	<style>
	#tabs { height: 400px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 300px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	
	<script type="text/javascript">
			$(function(){
				// Tabs
				$('#tabs').tabs();
				
				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				
			});
	</script>
	<!--TERMINA MENU ARBOL-->
	
</head>

<body >
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Cadena de Custodia</a></li>
			<li><a href="#tabs-2">Ubincaci&oacute;n de Almac&eacute;n</a></li>
			<li><a href="#tabs-3">Destino Legal de la Evidencia</a></li>
		</ul>
		<div id="tabs-1">
			<div id="tabschild" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild-1">Entrada de Evidencia</a></li>
					<li><a href="#tabschild-2">Salida de evidencia</a></li>
					<li><a href="#tabschild-3">Bajas de evidencia</a></li>
				</ul>
				<div id="tabschild-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>Lsta de Entradas de Evidencias</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild-2">
					<table width="25%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="consultaVictima">Lista de Salidas de Evidencias</a></td>
						</tr>
					</table>
				</div>
				<div id="tabschild-3">
					<table width="25%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="noSub">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="crearDenunciante">Lista de Bajas de Evidencias</a></td>
							<td align="right"> <input type="button" value="Eliminar Expediente" class="btn_Generico"/></td>
							
						</tr>
						<tr>
							<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right"><input type="button" value="Terminar Cadena de Custodia" class="btn_Generico"/> </td>
							
						</tr>
						<tr>
							<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right"> <input type="button" value="Seguimiento Cadena de Custodia" class="btn_Generico"/></td>
							
						</tr>
					</table>
				</div>
				
				
			</div>
		</div>
		<div id="tabs-2">
			<div id="tabschild2" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild2-1"></a></li>				
				</ul>
				<div id="tabschild2-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
					</table>
				</div>
			</div>		
		</div>
		<div id="tabs-3">
			<div id="tabschild3" class="tabs-bottom">
				<ul>
					<li><a href="#tabschild3-1"></a></li>				
				</ul>
				<div id="tabschild3-1">
					<table width="25%" cellpadding="0" cellspacing="0">
						
					</table>
				</div>
			</div>		
		</div>
	</div>
</body>
</html>