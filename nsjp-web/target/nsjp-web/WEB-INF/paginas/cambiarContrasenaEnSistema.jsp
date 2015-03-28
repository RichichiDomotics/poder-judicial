<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>

<html>

<head>
<!--Hojas de estilos-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />



<!--Scripts-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>


<style type="text/css">
body {
	background-color: #E4F2E5;
	width:350px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() 
	{
		imprimeDatos();
		//Habilitamos los botones
		//$("#btnCancelaModifcarPassword").click(cancelarUpdatePwd);
	});	

	
	//valida campos para hacer el registro del usuario del sistema
	
	// Se renombra función por conflictos al realizar el include, ya existe
	// una método con este nombre, truena al hacer referencia en el include,
	//favor de checar darDeAltaUsuarioEnSistema
	function validaCamposUpdatePwd()
	{
		var pswant=$('#oldPassword').val();
		var psw =$('#newPassword').val();
		
		if (isContraseniaUpdate(psw) && checkLastPwd(pswant))
		{
			if($("#newPassword").val()!= $("#reNewPassword").val()) 
			{
				alertDinamico("La contraseña debe ser igual en los dos campos, favor de verificar");  
		    }
			else
			{
				if($("#newPassword").val().length < 5) 
				{  
				   alertDinamico("La contraseña debe tener como mínimo 5 caracteres");  
			    }
				else
				{
					if($("#newPassword").val()!= $("#reNewPassword").val()) 
					{  
				  	   alertDinamico("La contraseña debe ser igual");  
			        }
					else
					{
			 			var params = 'username=' +$('#txtNombreUsuario').val();
			 			params += '&oldPassword=' + escape($('#oldPassword').val());
		 			   	params += '&newPassword=' + escape($('#newPassword').val());
		 			    
			 			$.ajax({
							type: 'POST',
							url: '<%=request.getContextPath()%>/cambiaUsuario.do',
							async: false,
							data: params,
							dataType: 'xml',
							success: function(xml)
							{
								//revisamos la respuesta enviada
								if(parseInt($(xml).find('code').text())==0)
								{
									var error=$(xml).find('error').find('bandera').text();
									var exito=$(xml).find('exito').find('bandera').text();
									if(parseInt(error)==0)
									{
										if(parseInt(exito)==1)
										{
											alertDinamico("Se modificó correctamente la contraseña");
											cancelarUpdatePwd();
										}
										else{
											alertDinamico("La contraseña original es incorrecta, favor de verificar");
										}
											
									}else{
										if(parseInt(error)==1)
										{
			 								alertDinamico("Ocurrió un error al modificar la contraseña");
			 							}else{
			 								alertDinamico("Favor de registrar todos los campos solicitados, gracias");
			 							}
			 								
										
									}
								}					    			
							}
							});
					 }
				 }	
			}
		 }
	}
	
//funcion validadora de contraseña. Regresa verdadero si cumple con requisitos o falso si no cumple
function isContraseniaUpdate(theElement){
	var s = theElement;
	var resp = false;
	var mPsw1=/^[A-Za-z0-9!@#$%^&*()_]{5,20}$/;
	//Valida que tenga letras
	var mPsw2=/[A-Za-z]/;
	//Valida que tenga números
	var mPsw3=/[0-9]/;
	
	if (mPsw1.test(s))
	{
		if (mPsw2.test(s))
		{
			if (mPsw3.test(s))
			{
				resp=true
			}
			else
			{
				alertDinamico("La contraseña debe tener al menos un número, favor de verificar");
			}
		}
		else
		{
			alertDinamico("La contraseña debe tener al menos una letra, favor de verificar");
		}
	}
	else
	{
		alertDinamico("La contraseña debe tener como mínimo 5 y máximo 20 caracteres, favor de verificar");
	}
	
	return resp;
}
	
	/*
	*Funcion que sirve para revisar si el pwd anterior es el correcto regresa true o flase
	*/
	function checkLastPwd(password)
	{
		var bandera=false;
		var params = 'contrasena=' + password;				 				
		$.ajax({
		type: 'POST',
		url: '<%=request.getContextPath()%>/cambiaUsuario.do',
			async : false,
			data : params,
			dataType : 'xml',
			success : function(xml) {
				if (parseInt($(xml).find('code').text()) == 0) {
					if (parseInt($(xml).find('isPwdEqual').text()) == 1) {
						bandera = true;
					}
				}
			}
		});
		//return bandera;
		bandera=true;
		if (bandera==false){
			alertDinamico('Contraseña incorrecta, favor de verificar');
		}
		return bandera;
	}
	function imprimeDatos() {
		//$('#txtNombreUsuario').val("Hola me llamo luis Oscar");
		$('#txtNombreUsuario').val(idUsuario);
		$('#txtAreaUsuario').val(areaFuncionario);
		$('#txtIdUsuario').val(idUsuario);
		

	}
	function cerrarVentanaModificarContrasena()
	{
		var pantalla ="iframewindowModificarPwdUsuario";
		$.closeWindow(pantalla);
	}
	function cancelarUpdatePwd() {
		//var pantalla ="iframewindowModificarPwdUsuario";
		parent.cerrarVentanaModificarContrasena();

		//window.parent.cerrarVentanaModificarContrasena();
	}
	
	
	
</script>
</head>
<body style="width: 320px;">
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
<table border=0 align="left" width="300px" height="250px">
	<tr>
		<td height="10%" align="center"><strong>Usuario:</strong></td>
		<td height="10%" align="center"><input align="middle"
			id="txtNombreUsuario" type="text" class="transpa"
			style="width: 100%; border: 0; text-align: center"
			readonly="readonly" /></td>
	</tr>
	<tr>
		<td align="center" width="50%">Contraseña anterior :</td>
		<td align="center" width="50%"><input type="password"
			name="oldPassword" width="90%" maxlength="20" id="oldPassword" /></td>
	</tr>
	<tr>
		<td align="center" width="50%">Nueva contraseña :</td>
		<td align="center" width="50%"><input type="password"
			name="newPassword" width="90%" maxlength="20" id="newPassword" /></td>
	</tr>
	<tr>
		<td align="center" width="50%">Repetir contraseña :</td>
		<td align="center" width="50%"><input type="password"
			name="reNewPassword" width="90%" maxlength="20" id="reNewPassword" /></td>
	</tr>
	<tr>
		<td width="50%" align="center"><input type="button"
			value="Modificar" id="btnModifcarPassword" class="btn_Generico"
			onclick="validaCamposUpdatePwd()" /></td>
		<td width="50%" align="center"><input type="button"
			value="Cancelar" id="btnCancelaModifcarPassword" class="btn_Generico"
			onclick="cancelarUpdatePwd()" /></td>
	</tr>
</table>
</body>
<script type="text/javascript">
<%UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute("KEY_SESSION_USUARIO_FIRMADO");
			FuncionarioDTO funcionario = usuario.getFuncionario();%>

var contextPath = '<%=request.getContextPath()%>';
var nombreFuncionario = "<%=funcionario.getNombreFuncionario()%>";
var apaterFuncionario = "<%=funcionario.getApellidoPaternoFuncionario()%>";
var amaterFuncionario = "<%=funcionario.getApellidoMaternoFuncionario()%>";
var areaFuncionario = "<%=funcionario.getJerarquiaOrganizacional().getNombre()%>";
var idUsuario = "<%=usuario.getClaveUsuario()%>";

	//imprimeDatosFuncionario();
</script>

</html>