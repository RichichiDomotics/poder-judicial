<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>

	<script type="text/javascript">
            $.jgrid.no_legacy_api = true;
           // $.jgrid.useJSON = true;
        
		//cargamos el combo con los delitos del expediente
		function cargaGridConsultaDelitosTodos()
		{
			//cargamos el grid con los delitos del PR
	    	jQuery("#gridCatDelitosRDPTodos").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultarRelacionDelitosPorTodos.do?idExpediente='+idExpedienteop+'', 
				datatype: "xml",
				colNames:['Probable responsable','Delito','Forma de Participaci�n','V�ctima'], 
				colModel:[ 	{name:'Probable',index:'probable', width:250},
				           	{name:'Delito',index:'delito', width:250}, 
							{name:'FormaParticipacion',index:'formaParticipacion',width:250},
							{name:'Victima',index:'victima',width:250},
						],
				//pager: jQuery('#pager1'),
				rowNum:0,
				rowList:[0,0,0],
				autowidth: true,
				caption:"LISTA DE RELACIONES",
				sortname: 'Clave',
				viewrecords: true,
				id: 'divgridIzq',
				onSelectRow: function(id){
					//alert(idRelacionDelito);
					idRelacionDelito=id;
					},
				sortorder: "desc"
			}).navGrid('#pagerGridCatDelitosRDPTodos',{edit:false,add:false,del:false});
	    	$("#gridCatDelitosRDPTodos").trigger("reloadGrid");	
		}
        </script>
</head>
<body>
	<table border="0"  width="900px" id="tblRelacionarDelitoPorTodos">
		<tr>
			<td>
				<table id="gridCatDelitosRDPTodos" width="400px"></table>
				<div id="pagerGridCatDelitosRDPTodos"></div>
			</td>
		</tr>
	</table>
	

