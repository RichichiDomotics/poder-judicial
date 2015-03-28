<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadena de custodia evidencia</title>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>-->
<!--	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>-->
		
</head>
 
<body>
	<table width="400" height="200" border="0" cellspacing="0" cellpadding="0" >
		<tr>
        	<td height="10">Cadena de custodia</td>
        </tr>
        <tr>	
        	<td height="10">Evidencia:</td>
        </tr>
        <tr>
        	<td align="center" width="380" height="200">
            	<textarea rows="10" cols="40"></textarea>
			</td>
        </tr>		
	</table>
</body>
</html>