<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">

	function deshabilitaOpciones(){
		 $('#contiene').attr('disabled', 'disabled');
		 $('#buscarAlias').attr('disabled', 'disabled');
		 $('#esIgual').attr('disabled', 'disabled');
		 $('#textoContiene').attr('disabled', 'disabled');
		 $('#textoEsIgual').attr('disabled', 'disabled');
	} 
	function habilitaOpciones(){
		 $('#contiene').attr('disabled', '');
		 $('#buscarAlias').attr('disabled', '');
		 $('#esIgual').attr('disabled', '');
		 $('#textoContiene').attr('disabled', '');
		 $('#textoEsIgual').attr('disabled', '');
	} 	

	function uno(tr){
		
		//$("#eventoAgenda").css("display", "none");
		//if($('#eventoAgenda').is (':visible')){
			// es esta linea tocallo
		//$("#eventoAgenda").css("display", "block");
		
		//}
		
	} 	
	//Llena el grid con los resultados de la busqueda, pasa como parametros el
	//alias ingresado
	 function llenaGridAlias(){
		 var reloadGridAlias = false;
		  var alias = "chompi";
		  var tipo = "1";
		  		 		 		  		
		  if (alias==''){
			customAlert ("Existen datos invalidos");	
		  }else{	
			 
			   if (reloadGridAlias) {
				   				   				   
					  jQuery("#tablaBuscarCasoPorAlias").jqGrid('setGridParam', {postData:{alias: alias, tipo: tipo}});
					  $("#tablaBuscarCasoPorAlias").trigger("reloadGrid"); 
				  } else {
					  
					  reloadGridAlias = true;
					  jQuery("#tablaBuscarCasoPorAlias").jqGrid(
								{ url:'<%= request.getContextPath() %>/buscarCasoPorAlias.do', 
														
									datatype: "xml", 
									mtype: 'POST',						
									postData: {alias: function()     {return alias;},
											   tipo: function()     { return tipo; }
									},
									colNames:['Caso','Nombre','A. Paterno','A. Materno'], 
									colModel:[ {name:'caso',index:'caso', width:150, sortable:false}, 
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
										onSelectRow: function(id){
											uno(id);
											},
										//onSelectRow: function(id){
											//uno($("#"+id));
											//},

										//ondblClickRow: uno,
										caption:"Resultado de la B&uacute;squeda" 
								});
				  }		 		
		  }
	}
	  
</script>

<table width="650" cellpadding="0" cellspacing="0">
	<tr>
	  <td valign="middle" align="center">
	  	<table id="tablaBuscarCasoPorAlias"></table>
	</td>
 	</tr>
</table>
