<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<title><bean:message key="ingProbaleResponsableTitulo"/></title>
</head>
  <body>
<table width="500" height="595" border="1" cellspacing="0" cellpadding="0" >
      <tr>
        <td height="243" colspan="3">
        
        	<table width="100%" height="242" border="0" cellpadding="0" cellspacing="0">
              
            </table>
        </td>
      </tr>
      <tr>
        <td width="20%">
        	Condici&oacute;n del objeto:
        </td>
        <td width="60%">
        	<select name="cbxCondicionEquipoComputo" id="cbxCondicionEquipoComputo" style="width:200px">
        	</select>
        </td>
        <td width="20%">
        	<input name="btnModificarEquipoComputo" type="button" value="Modificar" class="btn_Generico"s>
        </td>
      </tr>
      <tr>
        <td colspan="3">Descripci&oacute;n del objeto:</td>
      </tr>
      <tr>
        <td colspan="3" align="center" valign="middle"><textarea name="txtBoxDescEquipoComputo" id="txtBoxDescEquipoComputo" cols="50" rows="13"></textarea></td>
  </tr>
    </table>
  </body>
</html>
