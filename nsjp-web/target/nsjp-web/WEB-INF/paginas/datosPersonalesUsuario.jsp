<%@page import="mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />

<div>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<!--tr>
			<td width="100" height="100" align="center">			
				<img width="100" height="100" id="imgConFoto" src="<%= request.getContextPath()%>/resources/images/mono.png" />
			</td>
	    </tr-->
	    <tr>
			<td height="10%" align="center">
				<strong>Usuario:</strong>
			</td>
	    </tr>
	    <tr>
			<td height="10%" align="center">
				<input align="middle" id="txtNombreUsuario" type="text" class="transpa" style="width:100%; border:0;  text-align:center" readonly="readonly"/>
			</td>
	    </tr>
	    <tr>
			<td height="10%" align="center">
				<strong>Area:</strong>
			</td>
	    </tr>
	    <tr>
			<td height="10%">
				<input align="middle" id="txtAreaUsuario" type="text" class="transpa" style="width:100%; border: 0;  text-align:center" readonly="readonly"/>
			</td>
	    </tr>
	     <tr id="trTituloAgencia">
			<td id="tdTituloAgencia" height="10%" align="center">
				<strong>Agencia:</strong>
			</td>
	    </tr>
	    <tr id="trContenidoAgencia">
			<td height="10%">
				<input align="middle" id="txtAgenciaUsuario" type="text" class="transpa" style="width:100%; border: 0;  text-align:center" readonly="readonly"/>
			</td>
	    </tr>
	    <tr>
	    	<td height="10%" align="center">
				<a href="javascript:abreVentanaModificarContrasena();">Cambiar Contraseña</a>
			</td>
	    </tr>
	</table>
</div>

<script type="text/javascript">
	<%
	UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("KEY_SESSION_USUARIO_FIRMADO");
	FuncionarioDTO funcionario = usuario.getFuncionario();
	%>
	
	var contextPath = '<%=request.getContextPath()%>';
	var claveFuncionario = "<%= funcionario.getClaveFuncionario()%>";
	var nombreFuncionario = "<%= funcionario.getNombreFuncionario()%>";
	var apaterFuncionario = "<%= funcionario.getApellidoPaternoFuncionario()%>";
	var amaterFuncionario = "<%= funcionario.getApellidoMaternoFuncionario()%>";
	var areaFuncionario = "<%= funcionario.getJerarquiaOrganizacional().getNombre()%>";
	 
	
	imprimeDatosFuncionario();
	/**
	*Imprime los datos del funcionario en pantalla
	*/
	function imprimeDatosFuncionario()
	{

		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/tieneImagenFuncionario.do?idFuncionario='+claveFuncionario+'',						
			dataType: 'xml',
			async: false,
			success: function(xml){
				var respuesta=$(xml).find('respuesta').text();						
				if(respuesta=='1' || respuesta==1){
					$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeFuncionario.do?idFuncionario='+claveFuncionario+'');
				}
				else{
					$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/mono.png");
				}
			}
		});
		$('#txtNombreUsuario').val(nombreFuncionario+" "+apaterFuncionario+" "+amaterFuncionario);
		$('#txtAreaUsuario').val(areaFuncionario);
		$.ajax({
		     type: 'POST',
		     url: '<%=request.getContextPath()%>/obtenerNombreAgencia.do',
			 dataType: 'xml',
			 async: false,
			 success: function(xml){
				 if(parseInt($(xml).find('idInstitucion').text())>0)
				 {
					 
					 if(parseInt($(xml).find('idInstitucion').text())==1 || parseInt($(xml).find('idInstitucion').text())==3)
					 {
					    $('#txtAgenciaUsuario').val($(xml).find('nombreAgencia').text());
					 }
					 else
					 {
						 $('#trTituloAgencia').hide();
						 $('#trContenidoAgencia').hide();
						 $('#tdTituloAgencia').hide();
					 }
				 }
				 else
				 {
					 $("#trTituloAgencia,#trContenidoAgencia").hide();
				 }
			  }
		});
	}
	
	function abreVentanaModificarContrasena(){
		//window.parent.abreVentanaModificarContrasena();
		$.newWindow({id:"iframewindowModificarPwdUsuario", statusBar: true, posx:400,posy:90,width:350,height:280,title:"Modificar Contraseña", type:"iframe"});
	    $.updateWindowContent("iframewindowModificarPwdUsuario",'<iframe src="<%=request.getContextPath()%>/cambiarContrasena.do?" width="350" height="280" />');
	}
	function cerrarVentanaModificarContrasena(){
		$.closeWindow('iframewindowModificarPwdUsuario');
	}
	
	
</script>
