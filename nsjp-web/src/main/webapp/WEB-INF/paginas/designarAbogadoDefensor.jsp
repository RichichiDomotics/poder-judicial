
	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />

<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript">

	function uno(){
		if($("#eventoAgenda").is("display", "none")){
			$("#eventoAgenda").css("display", "block");
		}		
	}
			
</script>

<table width="700" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">
    	<div id="listaAbogados" ><jsp:include page="listaAbogado.jsp"></jsp:include></div>
    	<div id="eventoAgenda" style="display: none;"><jsp:include page="consultarEventosAgenda.jsp"></jsp:include></div>
 		<div id="notifica" style="display: none;"><jsp:include page="asignarExpedienteDefensor.jsp"></jsp:include></div>  
   </td>
  </tr>
   <tr>
    <td width="100%" align="center">
    	
   </td>
  </tr>
</table>

<script type="text/javascript">
llenaGridAlias();
//$("#1").bind('newline',function (){
	//uno();
	//});
$("#1").mousedown(uno);
</script>
