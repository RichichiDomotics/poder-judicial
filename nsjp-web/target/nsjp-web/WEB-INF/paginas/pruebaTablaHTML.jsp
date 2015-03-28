<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>prueba Tabla HTML</title>
<script src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarTutor.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/css/jquery/jquery-ui-1.8.10.custom.css" rel="stylesheet" />

<script type="text/javascript">
   $(document).ready(function(){
	  generaTabla('<%= request.getContextPath()%>');	
	  });
   
   function agregaCodigoDiv(idDiv)
   {
	   $(idDiv).html('<jsp:include page="VAdmonIndividuo.jsp" flush="true"/>');
   }
</script>
</head>
<body>

	<div class="encabezadot1 floatL clear">
                            <div class="p12 negrita floatL marginTop4"> <span id="t8">Detalle del movimiento</span></div>
                            <div class="floatR" id="entpag"><div id="paginacion"></div></div>
                        </div>
                        <!-- <div class="cuadro1 floatL clear"> -->
                            <table class="tabla_info sortable paginated" id="tablaDetalle" border="1" bordercolor="blue">
                            <thead>
                                <tr>
                                  <td width="7%" class="celdatope"><span class="p11 floatL paddingTop2" id="t8">#</span> 
                                  </td>
                                  <td width="7%" class="celdatope"><span class="p11 floatL paddingTop2" id="t9">Nombre</span> 
                                  </td>
                                  <td width="12%" class="celdatope"><span class="p11 floatL paddingTop2" id="t10">Ap. Paterno</span> 
                                  </td>
                                  <td width="16%" class="celdatope"><span class="p11 floatL paddingTop2" id="t11">Ap. Materno</span> 
                                  </td>
                                  
                              </tr>
                              </thead>
                              <tbody id="tbodyDetalle">
                              
                              </tbody>        
                      	  </table>
                      	  
                      	  <p class="p_18"><span id="spanNoMovsShow"> </span> <span id="t19">&nbsp;movimientos mostrados de </span><span id="spanNoMovsTotal"></span></p>
                          <div class="clear overflow">
                              <p class="floatL"><span id="t20">P&aacute;gina </span><span id="spanNoPagShow"></span> <span id="t21">&nbsp;de &nbsp;</span><span id="spanNoPagsTotal"></span> <span id="t22">&nbsp;/ mostrar p&aacute;gina</span></p>
                              <p class="floatL paddingLeft5"><input type="text" id="irpag" onKeyPress="return solonumeros(event);" /> </p>
                              <div class="btnIr floatL marginLeft5 marginTop1"><span onclick="irAPaginaX();" id="t23">Ir</span></div>
                          </div>
  <div id="divsDinamicos">
	
</div>
		
</body>
</html>