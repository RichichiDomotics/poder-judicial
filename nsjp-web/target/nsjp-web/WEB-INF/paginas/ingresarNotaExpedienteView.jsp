<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar narrativa</title>
	   
			<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	
	<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script> -->
	
   <!--Scripts necesarios para la ejecucion del editor-->
   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		
		<!--Scripts necesarios para la ejecucion del editor-->
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	      
	   <script type="text/javascript">
	   		var idNota;
	   		var idNumeroExpedienteOp;
	   		
	   		$(document).ready(function() {
	   			//revisamos si el id de la nota es 0, si lo es sera una insercion en caso contrario es una consulta
	   			idNota='<%= request.getParameter("idNota")%>';
	   			idNumeroExpedienteOp = '<%= request.getParameter("idNumeroExpedienteOp")%>';
	   			$("#guardarNarraTiva").click(guardarNotas);

	   			if(idNota!= null && parseInt(idNota)>0)
	   			{
	   				//consultamos la nota del expediente
	   				consultarNotaXId(idNota);	
	   			}
	   		});
	   		//termina funcion ready
	   		
	   		/*
	   		*Funcion para mandar guardar una nota del expediente
	   		*/
	   		function guardarNotas(){
				if(idNota!=null && idNota!="")
				{
					var notas=escape($('.jquery_ckeditor').val());
					$.ajax({
						type: 'POST',
						url: '<%= request.getContextPath()%>/registrarNotasExpediente.do',
						data: 'notas='+notas+'&idNota='+idNota+'&idNumeroExpediente='+idNumeroExpedienteOp,
						dataType: 'xml',
						async: false,
						success: function(xml){
							alertDinamico("Los datos se guardaron exitosamente");
							$(xml).find('NotaExpedienteDTO').each(function(){
	   							//$('#editor1').val($(this).find('descripcion').text());
	   							idNota=$(this).find('idNota').text();
	   							consultarNotaXId(idNota);
	   							window.parent.cargaNota($(this).find('idNota').text());
	   						});
						}
					});
				}
			}
	   		
	   		/*
	   		*Consultar una nota del expediente por Id
	   		*/
	   		function consultarNotaXId(idNota){
	   			var notas=$('#editor1').val();
	   			$.ajax({
	   				type: 'POST',
	   				url: '<%= request.getContextPath()%>/consultarNotaXId.do?idNota='+idNota,
	   				data: '',
	   				dataType: 'xml',
	   				async: false,
	   				success: function(xml){
	   					$('#editor1').val($(xml).find('notaExpedienteDTO').find('descripcion').text());
	   					//$(xml).find('notaExpedienteDTO').each(function(){
	   						//$('#editor1').val($(this).find('descripcion').text());
	   					//});
	   				}
	   			});
	   		}
	   			   	
		</script>
		
		<!-- Estilo para el cuerpo del documento-->
		<style>
		   body{
		       font-size: 10px;
		   }
		</style>
</head>
<body>
<!-- div para el alert dinamico -->
<div id="dialog-Alert" style="display: none">
	<table align="center">
		<tr>
        	<td align="center">
            	<span id="divAlertTexto"></span>
            </td>
        </tr>
     </table>              
</div>  
	<table width="655px" height="100%">
		<tr>
			<td align="right"">
				<input type="button" id="guardarNarraTiva" name="guardar" value="Guardar" class="btn_Generico"/>
			</td>
		</tr>
		<tr>
			<td>
				<textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10" ></textarea>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	var config = {					
		toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		],
		height:'200px',
		width:'650px'
	};		
	$('.jquery_ckeditor').ckeditor(config);
</script>
</html>

