<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>

<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
	jQuery().ready(function () {
		var error='<%= request.getAttribute("error")%>';
		var captcha='<%= request.getAttribute("captcha")%>'
		if(error==0){
			alert("Usuario y/o Contrase\u00d1a Inv\u00e1lidos, favor de verificar");
			$('#errorLogin').val('Credenciales Invalidas');
			//errorlog(error);					
		}else if(error==2){
			alert("Cuenta Bloqueada, favor de verificar con el administrador del sistema");
			$('#errorLogin').val('Credenciales Invalidas');
		}else if(error==3){
			alert("Código Captcha err\u00f3neo, favor de verificar");
			$('#errorLogin').val('Credenciales Invalidas');
		}else{
			$('#errorLogin').val('');
			
		}
		
		if(captcha==0){
			$("#captchaJPG").hide();
			$("#captchaTXT").hide();
			

		}else if (captcha==1){
			alert("Existe una sesi\u00f3on iniciada en sistema, favor de volver a ingresar su informaci\u00f3n de usuario");
			$('#errorLogin').val('Sesión Duplicada');
			$("#captchaJPG").show();
			$("#captchaTXT").show();
		}
	});
		

	
	</script>
		<title>Nuevo Sistema de Justicia Penal</title>
	</head>
	<body class="marcadeagua">
	  <center>
		<form method="post" action="<%= request.getContextPath() %>/Login.do">
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
	       
	        <table width="537" height="228" border="0" cellspacing="0" cellpadding="0" background="../../../imagenes/imagen/sistema/back_login.png" align="center" >
	        
	                <tr>
	                    <td align="center" style="border-left:#FFFFFF; border-top:#FFFFFF; border-right-width:3; border-bottom-width:3;">
	                    	<label style="color:#4A5C68">Escriba su usuario y contrase&ntilde;a para iniciar.</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td valign="top" align="center" style="border-left:#FFFFFF; border-top:#FFFFFF; border-right-width:3; border-bottom-width:3;">
	                        <table align="center" border="0">
	                            <tr>
	                                <td colspan="2" align="right">
	                                <div id="errorLogin"   style=" color:FF0000 ;" >
	                                
	                                </div>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td align="right"><label style="color:#4A5C68">Usuario:</label></td><td><input type="text" name="username" id="username" value="" maxlength="30" size="20"></td>
	                            </tr>
	                            <tr>
	                                <td align="right"><label style="color:#4A5C68">Contrase&ntilde;a:</label></td><td><input type="password" name="password" value="" maxlength="20" size="20"></td>
	                            </tr>
	                            <tr id="captchaJPG" style="display:none;">
	                                <td align="right">
	                                	<label style="color:#4A5C68">Captcha:</label>
	                                </td>
	                                <td>
	                                  	<img src="<%=request.getContextPath()%>/kaptcha.jpg">
	                                </td>
	                               
	                            </tr>
	                             <tr id="captchaTXT" style="display:none;">
	                                <td align="right">
	                                	<label style="color:#4A5C68">Captcha:</label>
	                                </td>
	                                <td>
	                                	<input type="text" name="scaptcha" value="" maxlength="15" size="20">
	                                	<input type="hidden" name="captcha" value='<%= request.getAttribute("captcha")%>'>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td align="right">
	                                    &nbsp;
	                                </td>			
	                                <td align="left" colspan="2">
	                                    <input value="Entrar" name="login" id="login" type="submit" class="btn_login"/> <input value="Cancelar" name="cancelar" id="cancelar" type="reset"  class="btn_login"/>
	                                </td>			
	                            </tr>
	                        </table>
	                    </td>
	                    <td valign="top">
	                    <td valign="top" width="150" align="left""><img width="103" height="120" src="../../../imagenes/imagen/sistema/rombo_domotics.png"  /></td>
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
	                
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbar">
						    <tr>
						    <td height="15">&nbsp;</td>
						  </tr>
						</table>
	                </div>
					<!--div align="left">Version HGO-2.0</div-->
	                <div>
						<div align="left">Version HGO-2.0</div>
	                    <div align="center">
						<h3>TRIBUNAL SUPERIOR DE JUSTICIA</h3>
						<p>
	                    Carretera M&eacutexico-Pachuca Km 84.5
						Centro C&iacutevico primer piso, Pachuca de Soto Hidalgo
						</p>
						<p>
						C.P. 42038 Tel.01771 19000
						</p>
	                </div>
	                </div> 
	            </div>
	        </td>
	      </tr>
	    </table>	
		</form>
	  </center>
	</body>
</html>