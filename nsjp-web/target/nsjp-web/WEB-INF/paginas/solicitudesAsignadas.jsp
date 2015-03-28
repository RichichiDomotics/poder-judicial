<script type="text/javascript">
			var reloadGridAlias = false;
			//Llena el grid con los resultados de la busqueda, pasa como parametros el
			//alias ingresado
			  function llenaGridAlias(){
				  var alias = 'hola';//Alias	
				  var con = true;
				  var esIg = true;
				  		
				  if (alias==''){
					alert ("Existen datos invalidos");	
				  }else  if((con==false) && (esIg==false)){
					  		alert ("Debes seleccionar una de las dos opciones para buscar el Alias");
				  }else{
					 
					   if (reloadGridAlias) {
							  jQuery("#detalleIndividuos").jqGrid('setGridParam', {postData:{alias: alias}});
							  $("#detalleIndividuos").trigger("reloadGrid"); 
						  } else {
							  reloadGridAlias = true;
							  jQuery("#detalleIndividuos").jqGrid(
										{ url:'<%= request.getContextPath() %>/buscarExpedientePorAlias.do', 
											data: 'alias='+alias,						
											datatype: "xml", 
											mtype: 'POST',						
											postData: {
												alias: function()     { return alias; }
											},
											colNames:['Expediente','Nombre','A. Paterno', 'A. Materno'], 
											colModel:[ {name:'expediente',index:'expediente', width:100, sortable:false},
												{name:'nombre',index:'nombre', width:150, sortable:false}, 
												{name:'apaterno',index:'apaterno', width:150, sortable:false}, 
												{name:'amaterno',index:'amaterno', width:150, sortable:false} 
												], 
												autowidth: true, 
												pager: jQuery('#pager'), 
												sortname: 'id', 
												rownumbers: true,
												gridview: true, 
												viewrecords: true, 
												sortorder: "desc", 
												height: "60%",
												caption:"Resultado de la B&uacute;squeda" 
										});
						  }		 		
				  }
			}

			  

				
		</script>
	</head>
<body>
	<table border="0" width="100%">
		<tr valign="top">
			<td>
				<table border="0" width="85%" height="33%">
					<tr>
						<td>Solicitudes de No. Asignadas</td>
						
					</tr>
					<tr>
						<table id="detalleIndividuos" width="85%"></table>
					</tr>	
				</table>
			</td>
			
		</tr>
	</table>
