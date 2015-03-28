<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<html>
	<head>
	
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
		
		
		
	<script type="text/javascript">
	jQuery().ready(function () {
		var error='<%= request.getAttribute("error")%>';
		if(error==0){
			alert("Usuario y/o Contraseña Inválidos, favor de verificar");
			$('#errorLogin').val('Credenciales Invalidas');
			//errorlog(error);					
		}else if(error==2){
			alert("Cuenta Bloqueada, favor de verificar con el administrador del sistema");
			$('#errorLogin').val('Credenciales Invalidas');
		}else{
			$('#errorLogin').val('');
			
		}				
	});
	

	
	</script>
		<title>Nuevo Sistema de Justicia Penal</title>
	</head>
	<body>
	  <center>
		<form method="post" action="<%= request.getContextPath() %>/cargarLogin.do">
	   	<table width="100%" height="100%" border="0" cellspacing="3" cellpadding="0" background="<%=request.getContextPath()%>/resources/images/back_grallogin.jpg" align="center">
	      <tr height="108px">
	        <td colspan="3">
	        	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/resources/images/top_gral.jpg">
	                <tr>
	                    
					<td width="150" align="center"><img src="<%=request.getContextPath()%>/resources/images/logo_login.png" alt="Logo sistema de justicia" /></td>     
	                    <td width="150" align="center"></td>
	                    <td width="150" align="center"></td>
	                    <td width="296" align="center">&nbsp;</td>
	                    <td width="150" align="center"></td>
	                    <td width="180" >
	                        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	                            <tr>
	                                <td width="168" height="40" colspan="2" align="center">
	                                    <table align="center" cellpadding="2" cellspacing="2">
	                                        <tr>
	                                            <td>&nbsp;</td>
	                                            <td>&nbsp;</td>
	                                            <td>&nbsp;</td>
	                                        </tr>
	                                    </table>
	                                </td>
	                            </tr>
	                            <tr valign="top">
	                                <td  width="128" align="center">&nbsp;</td>
	                                <td width="40">&nbsp;</td>
	                            </tr>
	                        </table>            
	                    </td>
	                </tr>
	                <tr>
	                <td colspan="6"> 
			                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbar">
						    <tr>
						    <td height="15">&nbsp;</td>
						  </tr>
						</table>
					</td>
	                </tr>
	            </table>
	            
	        </td>
	      </tr>
	    
	      <tr>
	        <td>&nbsp;</td>
	        <td valign="top">
	       
	        <table width="537" height="228" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/resources/images/back_login.png" align="center" >
	        
	            <tr>
								 <td>&nbsp;</td>
								</tr>
	                <tr>
	                    <td align="center" style="border-left:#FFFFFF; border-top:#FFFFFF; border-right-width:3; border-bottom-width:3;">
	                    	<label style="color:#4A5C68">Dirección IP Bloqueada</label>	
	                    </td>
	                </tr>
	                <tr>
	                    <td valign="top" align="center" style="border-left:#FFFFFF; border-top:#FFFFFF; border-right-width:3; border-bottom-width:3;">
	                            <table  border="0" align="center">
	                            <tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
	                            	<td align="center" colspan="1">
	                            		<label style="color:#4A5C68">La dirección IP <%= request.getAttribute("ip") %></label>
	                            	</td>
						        </tr>
	                            <tr>
	                            	<td>&nbsp;</td>
	                            </tr>
	                            <tr>
	                                <td align="center" colspan="1"><label style="color:#4A5C68">Ha sido bloqueada por exceder el número de intentos permitidos para iniciar sesión, intente más tarde</label></td>			
	                            </tr>
								<tr>
									<td align="center">
										<input value="Reintentar" name="cancelar" id="cancelar" type="submit"  class="btn_login"/>
									</td>
									
								</tr>
	                        </table>
	                    </td>
	                    <td valign="top">
	                    <td valign="top" width="150" align="left""><img src="<%=request.getContextPath()%>/resources/images/back_huella.png"  /></td>
	                    
	                </tr>
	                
	        </table>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td colspan="3">
	        	
	        	<div>
	        	
	                <div id="footer" style="width: 100%;  padding: 5px;" >
	                
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pleca_bottom">
						    <tr>
						    <td height="15">&nbsp;</td>
						  </tr>
						</table>
	                </div> 
	                <div align="center">
	                    Direcci&oacute;n de la Instituci&oacute;n
	                </div> 
	            </div>
	        </td>
	      </tr>
	    </table>	
		</form>
	  </center>
	</body>
</html>