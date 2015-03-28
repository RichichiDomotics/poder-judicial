
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">

	var numExpediente;
	var idExpediente;
	var idFuncionario;
	var documentoId;
	
	$(document).ready(function() {
		//se agrega al evento para buscar el expediente
		$("#buscarExpediente").click(buscarExpediente);
		//se agrega el evento para cmabiar el defensor
		$("#cambiarDefensor").click(cambiarDefensor);
	});	
	
	//Crea una nueva ventana para buscar expedientes
	function buscarExpediente() {
		$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
    	$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do?tipo=1" width="653" height="400" />');		
	}

	function cerrarEtapa(idExp,numExp){
		idExpediente=idExp;	
		numExpediente=numExp;	
		$.closeWindow('iframewindowBuscarExpediente');
		jQuery("#gridAbogadoDefensor").jqGrid({ 
		    url: '<%= request.getContextPath()%>/consultarDefensorAsignado.do?idExpediente='+idExpediente+'',
			datatype: "xml", 
			colNames:['Nombre','A. Paterno','A. Materno'], 
			colModel:[ {name:'nombre',index:'nombre', width:300, align:"center"},
					   {name:'aPaterno',index:'aPaterno', width:200, align:"center"},
					   {name:'aMaterno',index:'aMaterno', width:200, align:"center"}
					 ],
		     rowNum:10, rowList:[10,20,30],
		     autowidth: true, width:"100%", height:440,
		     pager: '#pagerAbogadoDefensor',
		     sortname: 'nombre', viewrecords: true, gridview: true, 
		     caption: "Número de expediente del defensor asociado  " +numExpediente, 
		     sortorder: "desc"	,
		     onSelectRow: function (rowid){
		    	 idFuncionario = rowid;
		    	 $("#cambiarDefensor").removeAttr('disabled');
		     },
		     loadComplete: function(xml){
		    	var noAsignado = $(xml).find('string').text();
			     if(noAsignado == "El expediente aún no tiene defensor designado, favor de verificar"){
			    	 alert(noAsignado);
				 }
			 }   
		  });
		  $('#gridAbogadoDefensor').trigger('reloadGrid');
	}	


	function cambiarDefensor(){		
		alert(idFuncionario);
		alert(idExpediente);
		var conf = confirm("El expediente se quedara sin defensor asociado ¿desea continuar?");
		if (conf == true) {
		 	$.ajax({
		   		type: 'POST',
		   		url: '<%= request.getContextPath()%>/cambiarDefensorAsignado.do?idfuncionario='+idFuncionario+'&idExpediente='+ idExpediente +'',
		   		data: '',
		   		dataType: 'xml',
		   		async: false,
		   		success: function(xml){
		   		  documentoId = $(xml).find('solicitudDefensorDTO').find('documentoId').text();
		   		  if(documentoId != 0){
		   				parent.cerrarEtapa(documentoId,numExpediente);
		   		  }		   			
		   		}
		   	});			
		}
	
	</script>	

<body>
<table width="700px" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="3%">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><strong>
      <input type="button" value="Buscar Expediente" id="buscarExpediente" class="btn_Generico"/>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><table id="gridAbogadoDefensor"></table><div id="pagerAbogadoDefensor" ></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><strong>
      <input type="button" value="Cambiar defensor designado" id="cambiarDefensor" disabled="disabled" class="btn_Generico"/>
      
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
