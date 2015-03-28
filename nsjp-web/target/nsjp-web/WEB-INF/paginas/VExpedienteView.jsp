<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Narrativa</title>
<script src="<%= request.getContextPath()%>/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript">

   $(document).ready(function(){	
		    consultaNarrativa();
		});
		
		
		function consultaNarrativa() {
          $('#txtNarrativa').empty();
	      $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/ConsultarNarrativa.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  success: function(xml){
		    	  //var option;
	    		  $(xml).find('narrativa').each(function(){
	    		    $('#txtNarrativa').val($(this).find('gcDescNarrativa').text());
				   });
	    	  }
	    	});
		}
	</script>
</head>
<body>
<table width="650" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="650" height="550">
		<table width="650" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="650" height="20" class="celda"
					style="background: #000; text-align: center; font-weight: bold; color: #FFF;">Consultar
				Narrativa</td>
			</tr>
		</table>
		<table width="650" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="130">
				<table width="500" border="0" align="center" cellpadding="0"
					cellspacing="0">

					<tr>

						<td align="center"  width="600">
						    <hr ></hr>Descripción Narrativa : <textarea rows="6" cols="50" id="txtNarrativa" name="descNarrativa"></textarea> 
						    <br></br>
						</td>


					</tr>

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>