<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />

<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>


<script type="text/javascript"><!--
		$(document).ready(function(){			
			cargaComboDelito();
			$("#enviar").click(seleccionDel);
		       
		});
	 function cargaComboDelito() {
         $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/CargarDelito.do',
	    	  data: '',
	    	  async: false,
	    	  dataType: 'xml',
	    	  success: function(xml){
		    	  $('#cmbSeleccionarDelito').empty();
		    	  $(xml).find('catTipoDelito').each(function(){
					$('#cmbSeleccionarDelito').append('<option value="' + $(this).find('valor').text() + '">' + $(this).find('valor').text() + '</option>');

					   });
	    	  }
	    	});
		}
	 
	 $(function(){
			var el = $("#cmbSeleccionarDelito2").multiselect(),
				selected = $('#selected'),
				newItem = $('#newItem');
			
			$("#agregarDelito").click(function(){
				var v = newItem.val(), opt = $('<option />', {
					value: v,
					text: v
				});
				
				opt.attr('selected','selected');
				opt.appendTo( el );
				el.multiselect('refresh');
				$("#newItem").val('');
			});
		});

	 	function seleccionDel() {

			  $("#newItem").val($("#cmbSeleccionarDelito option:selected").val());		
			
		}
	 
				    		
	</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<body>
<fieldset>
  <table width="88%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%" height="53" align="center"><span class="seccion">Tipo de Responsable : </span></td>
                <td width="14%" height="53" align="center" valign="middle">
                <!--<img src="resources/images/fingerPrint.JPG" width="100" height="100" />-->
                <select name="cbxProbResponsableTipoResp" id="cbxProbResponsableTipoResp">
                  <option value="1">F&iacute;sica</option>
                  <option value="0">Moral</option>
                </select></td>
                <td width="68%" height="53" align="center" valign="top"><table width="89%" border="0" cellspacing="0" cellpadding="0" class="celda2">
                        <tr align="center">
                                <td height="29" colspan="10" class="seccion">Condición:</td> 
                            </tr>
                            <tr>
                                <td width="8%" align="left">Vivo</td>
                                <td width="5%">
                                    <input type="radio" name="radio" id="btnPResponsableEsVivo" value="1" checked="checked"/>
                                </td>
                                <td width="11%">Muerto</td>
                                <td width="6%"><input type="radio" name="radio" id="btnPResponsableEsMuerto" value="0"/></td>
                                <td width="16%" align="left">Desconocido</td>
                                <td width="5%"><input type="checkbox" name="btnPResponsableDesconocido " id="btnPResponsableDesconocido" value="D"/></td>
                                 <td width="22%">Menor de edad</td>
                                <td width="6%"><input type="checkbox" id="chkPResponsableMenor" /></td>
                                <td width="14%" align="left">Detenido</td>
                                <td width="7%"><input type="checkbox" id="chkPResponsableDetenido"/></td>
                            </tr>
                          
                  </table>
                </td>
              </tr>
                      </table>
                   <table align="center"> 
                   <tr>
                   <td> 
                  <jsp:include page="seleccionarDelitoView.jsp"></jsp:include> 
                  
                  </td>
                  </tr>   
                      </table> 
                      <table width="634" border="0" align="center" >
  <tr>
    <td width="173">&nbsp;</td>
    <td width="24">Si</td>
    <td width="26">No</td>
     <td width="71"></td>
    <td width="318"></td>
  </tr>
  <tr>
    <td>Cedula Identificaci&oacute;n</td>
    <td>
          <input type="radio" name="RadioGroup1" value="radio" id="RadioGroup1_0" /></td>
    <td><input type="radio" name="RadioGroup1" value="radio" id="RadioGroup1_1" /></td>
    <td>Fecha:</td>
    <td>
      <input type="text" name="textfield" id="textfield" />
    </td>
  </tr>
  <tr>
    <td>Lectura de Derechos</td>
    <td><input type="radio" name="RadioGroup2" value="radio" id="RadioGroup1_2" /></td>
    <td><input type="radio" name="RadioGroup2" value="radio" id="RadioGroup1_3" /></td>
    <td>Hora:</td>
    <td><input type="text" name="textfield2" id="textfield2" /></td>
  </tr>
  <tr>
    <td>Asignacion de Abogados</td>
    <td><input type="radio" name="RadioGroup3" value="radio" id="RadioGroup1_4" /></td>
    <td><input type="radio" name="RadioGroup3" value="radio" id="RadioGroup1_5" /></td>
    <td colspan="2" align="center">Observaciones</td>
    </tr>
  <tr valign="top">
    <td>Judicializacion</td>
    <td><input type="radio" name="RadioGroup4" value="radio" id="RadioGroup1_4" /></td>
    <td><input type="radio" name="RadioGroup4" value="radio" id="RadioGroup1_5" /></td>
    <td colspan="2" align="center">
     
      <textarea name="textarea" id="textarea" cols="45" rows="3" a></textarea>
   </td>
    </tr>
</table>
</fieldset>
</body>
</html>