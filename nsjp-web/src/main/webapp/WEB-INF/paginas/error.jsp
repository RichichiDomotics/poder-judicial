
<html>
	<head>
	
	
	<link rel="stylesheet" type="text/css" href="/nsjp-web/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="/nsjp-web/resources/css/layout_complex.css" media="screen" />
	<script type="tex	t/javascript" src="/nsjp-web/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="/nsjp-web/resources/js/jquery-ui-1.8.10.custom.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>		
		
	<script type="text/javascript">
	jQuery().ready(function () {
		var error='null';
		if(error==0){
			customAlert("Usuario y/o Contraseña inválidos, favor de verificar");
			$('#errorLogin').val('Credenciales Invalidas');
			//errorlog(error);					
		}else if(error==2){
			customAlert("Cuenta Bloqueada, favor de verificar con el administrador del sistema");
			$('#errorLogin').val('Credenciales Invalidas');
		}else{
			$('#errorLogin').val('');
			
		}				
	});
	

	
	</script>

		<title>Nuevo Sistema de Justicia Penal</title>
	</head>
	<body class="marcadeagua">
	  <center>
		<form method="post" action="/nsjp-web/Login.do">
	   	<table width="100%" height="100%" border="0" cellspacing="3" cellpadding="0"  align="center">
	      <tr height="108px">
	        <td colspan="3">

	        	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" >
	                <tr>
	                    
					<td width="150" align="center"><img src="../../../imagenes/imagen/sistema/logo_nsjph.jpg" width="75%" alt="Logo sistema de justicia" /></td>     
	                    <td width="150" align="center"></td>
	                    <td width="150" align="center"></td>
	                    <td width="296" align="center">&nbsp;</td>
	                    <td width="150" align="center"></td>
	                    <td width="150" align="center">
							<img src="../../../imagenes/imagen/sistema/ejecutivo.png" width="50%" alt="Logo Hidalgo" />
	                        <!--table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
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
	                        </table-->            
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
	       
	        <table width="537" height="228" border="0" cellspacing="0" cellpadding="0" background="/nsjp-web/resources/images/back_login.png" align="center" >
	        
	                <tr>
	                    <td align="center" style="border-left:#FFFFFF; border-top:#FFFFFF; border-right-width:3; border-bottom-width:3;">
	                    	<label style="color:#4A5C68">¡ERROR!</label>	
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
										<label style="color:#4A5C68">Algo pasó</label>
									</td>
	                            </tr>
	                            <tr>
										<td>&nbsp;</td>
	                            </tr>
	                            <tr>
	                                <td align="center" colspan="1"><label style="color:#4A5C68">Estamos trabajando en solucionarlo, comun&iacute;quese con su administrador</label></td>			
	                            </tr>
								<tr>
									<td align="center">
										<input value="Reintentar" name="cancelar" id="cancelar" type="submit"  class="btn_login"/>
									</td>
								</tr>
	                        </table>
	                    </td>
	                    <td valign="top">

	                    <td valign="top" width="150" align="left""><img src="/nsjp-web/resources/images/back_huella.png"  /></td>
	                    </td>
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