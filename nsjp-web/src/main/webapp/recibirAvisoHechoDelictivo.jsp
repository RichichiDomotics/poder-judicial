<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />				
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">
	var idAviso=0;
	var numeroExpediente="";
	var estatus="";
	$(document).ready(function() {
		//revisamos el id del aviso
		idAviso='<%= request.getParameter("idAviso")%>';
		estatus='<%= request.getParameter("estatus")%>';
		
		var tipo='<%= request.getParameter("tipo")%>';
		var fecha='<%= request.getParameter("fecha")%>';
		var fechaSep=fecha.split(' ');
		
		
		$("#tabs").tabs();
		//ocultamos el renglon de coordenadas gps
		killCoordenadasGeograficas();
		cambiaNombreTabIngDomicilio('Domicilio');
		
		if(estatus=="NO_ATENDIDA")
		{
			$("#btnDarSeguimiento").click(actualizarEstatusAviso);
			$("#btnDarSeguimiento").show();
		}
		else
		{
			$("#btnDarSeguimiento").hide();
		}
		//ocultamos la pestaña del domicilio de notificaicones
		killDomicilioNotificaciones();
		if(idAviso!=null && idAviso>0)
		{
			//configuramos la paginara para la consulta del aviso
			consultaAvisoPorId();
			desavilitarDatosDomicilio();
			$("#txtTipoDelitoRecAvisoPosHechoDel").val(tipo);
			$("#txtFechaEnvioRecAvisoPosHechoDel").val(fechaSep[0]);
			$("#txtHraEnvioRecAvisoPosHechoDel").val(fechaSep[1]);
			$("#txtTipoDelitoRecAvisoPosHechoDel,#txtFechaEnvioRecAvisoPosHechoDel,#txtHraEnvioRecAvisoPosHechoDel").attr("disabled","disabled");
		}
	});
	
	function actualizarEstatusAviso()
	{	
		//mandamos a cambiar el estatus del aviso
		$.ajax({
			
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/cambiarEstatusAvisoPosHechoDlctv.do?idAviso='+idAviso +'&estatus=NO_ATENDIDA',
			dataType: 'xml',
			success: function(xml){
				if(parseInt($(xml).find('code').text())==0)
	    		{
					$(xml).find('notificacionDTO').each(function(){
						if($(this).find('documentoId').text()!='0')
						{
							//deshabilitamos el boton de dar seguimiento
							$("#btnDarSeguimiento").attr("disabled","disabled");
							//mandamos a actualizar el grid de los nuevo avisos en el padre
							window.parent.recargaGridAvisosNoAtendidos();
						}
					});
	    		}
			}
		});
	}

	/*
	*Funcion para llamar a la consulta de un aviso por ID
	*/
	function consultaAvisoPorId()
	{
		var xml="";
		//hacemos la llamada al AJAX para consultar la informacion del aviso
		$.ajax({
		     type: 'POST',
		     url: '<%= request.getContextPath()%>/consultaPosibleHechoDelictivoPorId.do',
			 data: 'idAviso='+idAviso,
			 dataType: 'xml',
			 success: function(xml){
				 if(parseInt($(xml).find('code').text())==0)
	    		{
					$(xml).find('domicilioDTO').each(function(){
						//seteamos la informacion
						seteaDatosDomicilio($(this));
					});
	    		}
			  }
			});
		 
	}
	
	/*
	*Funcion para setear los valores de los campos de domicilio, se consume la funcion
	* propia del ingreso de domicilio
	*/
	function seteaDatosDomicilio(xml)
	{
		//consumimos la funcion de seteo de la jsp de domicilio
		pintaDatosDomicilioAvisoPosHechoDel(xml);
	}
	</script>	
</head>

<body>
	<br/>
	<table>
		<tr>
			<td>
				Tipo de Delito : <input type="text" id="txtTipoDelitoRecAvisoPosHechoDel"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Fecha del envio: <input type="text" id="txtFechaEnvioRecAvisoPosHechoDel"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				Hora del envio: <input type="text" id="txtHraEnvioRecAvisoPosHechoDel"/>
			</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="/WEB-INF/paginas/ingresarDomicilioView.jsp"/>
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="button" value="Dar Seguimiento" id="btnDarSeguimiento" class="btn_Generico"/>
			</td>
		</tr>
	</table>
</body>
</html>
