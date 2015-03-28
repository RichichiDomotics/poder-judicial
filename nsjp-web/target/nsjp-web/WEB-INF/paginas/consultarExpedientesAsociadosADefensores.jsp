
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	<script type="text/javascript">
	
	$(document).ready(function() {
		$("#btnReasignarDefensor").click(reasignarDefensor); 
		Subordinados();
		
	});	

	 function Subordinados(){
			jQuery("#gridSubordinados").jqGrid({
				 url: '<%= request.getContextPath()%>/consultarDefensoresActivos.do?index=1',
				datatype: "xml", 
				colNames:['Tipo de Defensa','Nombre','Especialidad'], 
				colModel:[ {name:'TipoDefensa',index:'tipoDefensa', width:100, align:"center"},
						   {name:'nombre',index:'nombre', width:150, align:"center"},
						   {name:'especialidad',index:'especialidad', width:150, align:"center"}			     
						  ],
				pager: jQuery('#pagGridSubordinados'),
				rowNum:10,
				rowList:[10,20,30],
				width: 800,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc",
				onresize_end:			function () { $("#pagGridSubordinados").setGridWidth($("#mainContent").width() - 5, true); 
				},
				onSelectRow: function(rowid){
					gridExpedientesDeEtapas(rowid);
				}
			}).navGrid('#pagGridSubordinados',{edit:false,add:false,del:false});
			gridExpedientesDeEtapas(0);
			$("#gridSubordinados").trigger("reloadGrid"); 
	    }

	 gridExpedientesEtapaCargado = false;
	 function gridExpedientesDeEtapas(rowid){	
		var param ='expTodos=true&idFuncionario='+rowid;
		if(!gridExpedientesEtapaCargado){
			jQuery("#gridExpedientesSubordinados").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultarExpedientesPorUsuarioYEtapa.do', 
				datatype: "xml",
				postData:param,					
				colNames:['Caso','Expediente','Etapa','Defendido','Delito(s)'], 
				colModel:[ 	{name:'Caso',index:'Caso', width:200},
				           	{name:'Expediente',index:'Expediente', width:200},
				           	{name:'Etapa',index:'Etapa', width:200},
				           	{name:'Defendido',index:'Imputados', width:200},
					        {name:'delit',index:'delito', width:200, hidden:true}
						  ],
				pager: jQuery('#pagGridExpedientesSubordinados'),
				rowNum:10,
				rowList:[10,20,30],
				width: 800,
				autoheight:true,
				sortname: 'numeroExpediente',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagGridSubordinados',{edit:false,add:false,del:false});
			gridExpedientesEtapaCargado = true;
		}else{
			jQuery("#gridExpedientesSubordinados").jqGrid('setGridParam',
				{url:'<%= request.getContextPath()%>/ConsultarExpedientesPorUsuarioYEtapa.do?'+param});
			$("#gridExpedientesSubordinados").trigger("reloadGrid");
		}
	}
	 
	 function reasignarDefensor(){
		 var selrow = jQuery("#gridExpedientesSubordinados").jqGrid('getGridParam','selrow');
		 if(selrow != undefined && selrow != null){
			 var confir = confirm("Desea reasignar defensor?");
			 var param = "idExpediente="+selrow;	
			 if(confir){
			   	$.ajax({
			   		type: 'POST', data: param, dataType: 'xml',
			   		url: '<%= request.getContextPath()%>/cambiarDefensorAsignado.do',
			   		async: false,
			   		success: function(xml){
			   			varSol = $(xml).find('documentoId').first().text();
			   			varexp = $(xml).find('expediente').first().find('numeroExpediente').first().text();
					 	asignaAtencionAbogado(1, varexp, varsol);
			   		}
			   	});
			 }
		 }
	 }
    
		function asignaAtencionAbogado(tipo, exp, sol) {
			var params = "?tipo="+tipo;
			params += "&stNumEx="+exp;
			params += "&idAviso="+sol;
			$.newWindow({id:"iframewindowAsignaAtencionAbogado", statusBar: true, posx:200,posy:50,width:1000,height:400,title:" Exp:"+exp, type:"iframe"});
		    $.updateWindowContent("iframewindowAsignaAtencionAbogado",'<iframe src="<%= request.getContextPath() %>/asignaAtencionAbogado.do'+params+'" width="1000" height="400" />');
		    		
		}		

	</script>	

<body>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
    	<td>
      		<div id="divGridSubordinados">
				<table id="gridSubordinados"></table>
				<div id="pagGridSubordinados"></div>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div id="divGridExpedientesSubordinados">
				<table id="gridExpedientesSubordinados"></table>
				<div id="pagGridExpedientesSubordinados"></div>
			</div>
		</td>
	</tr>
	<tr>
		<td><input id="btnReasignarDefensor" name="btnReasignarDefensor" type="button" value="Reasignar Defensor" class="btn_Generico"></td>
	</tr>
    </table>
</body>
