<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Informe Policial Homologado</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/css/jquery/jquery-ui-1.8.10.custom.css" rel="stylesheet" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/css/jquery/jqgrid/ui.jqgrid.css" />

<!-- CSS INTERNA PARA EL ELEMENTO TITULO Y BODY -->

	<style type="text/css">
.Titulo {
	font-size: 18px;
	font-family: Arial, Helvetica, sans-serif;
	color: #FFF;
	background: #666;
}

.body {
	margin-top: 0px;
	margin-bottom: 0px;
	background-color: #EEE;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}
</style>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script type="text/javascript"><!--
/*
	 *Nombre del Programa : registrarInformePolicialHomologado.jsp                                    
	 *Autor               : Cuauhtemoc Paredes Serrano                                              
	 *Compania            : Ultrasist                                                
	 *Proyecto            : NSJP                    Fecha: 01/03/2010 
	 *Marca de cambio     : N/A                                                     
	 *Descripcion General : Mostrar campos a ser llenados por el usuario                      
	 *Programa Dependiente :N/A                                                      
	 *Programa Subsecuente :N/A                                                      
	 *Cond. de ejecucion   :N/A                                                      
	 *Dias de ejecucion    :N/A                     Horario: N/A       
	 */	

	//Enlaza botones a funciones
		$(document).ready(function(){	
			nuevoExp();		//Funcion que carga el combo de el tipo de evidencia
			
			});

		
		
		//Funcion que carga los combos de tipo de evidencia
		function nuevoExp() {
			
		  $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/asignarNuevoexpediente.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  success: function(xml){
	    		 $('#noExpNuevo').val($(xml).find('nuevoExpediente').text());
									  
	    	  }
	    	});
		}

	
</script>
</head>

	<body>
	<table bgcolor="#CCCCCC" width="900" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="4" align="center" bgcolor="#000000" class="Titulo">Registrar Informe Policial Homologado</td>
				</tr>
				<tr>
				  <td width="200" >No. Expediente:
				    <input type="text" size="11" id="noExpNuevo"></td>
				  <td>Servidor Publico:
			      <input type="text" size="90"></td>
      </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				<tr>
				  <td width="200" >Oficio:<input type="text" size="21"></td>
				  <td >Asunto:<input type="text" size="100"></td>
				  
                  
      </tr>
				<tr>
				  <td colspan="3">Lugar y Fecha de elaboraci&oacute;n:<input type="text" size="111"></td>
	  </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				<tr>
				  <td align="center" colspan="3">A T E N T A M E N T E</td>
	  </tr>
				<tr>
				  <td align="center" colspan="3">SUFRAGIO EFECTIVO. NO REELECCIÓN</td>
	  </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				<tr>
				  <td colspan="3">Cargo:</td>
	  </tr>
				<tr>
				  <td colspan="3">Area Administrativa:</td>
	  </tr>
				<tr>
				  <td colspan="3">Nombre del Servidor Publico:</td>
	  </tr>
				<tr>
				  <td colspan="3">&nbsp;</td>
	  </tr>
				
				
				
			</table>
	</body>
	
</html>