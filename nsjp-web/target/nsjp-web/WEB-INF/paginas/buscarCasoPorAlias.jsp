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
	
	//Llena el grid con los resultados de la busqueda, pasa como parametros el
	//alias ingresado
	 function llenaGridAlias(){

		  var alias = $('#alias').val();
		  var tipo = $(':radio[name=radio]:checked').val();
		  		 		 		  		
		  if (alias==''){
			customAlert ("Existen datos invalidos");	
		  }else  if(!($('#contiene').is(':checked')) && !($('#esIgual').is(':checked'))){
			  		customAlert ("Debes seleccionar una de las dos opciones para buscar el alias");
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
										caption:"Resultado de la B&uacute;squeda" 
								});
				  }		 		
		  }
	}
	  
</script>

<table width="650" cellpadding="0" cellspacing="0">
	<tr>
      <td></td>
    </tr>
	<tr>
	  <td align="center">&nbsp;</td>
	</tr>
	<tr>
	  <td align="center">&nbsp;</td>
	</tr>
  	<tr>
		<td align="center" width="600">
    		Alias: <input name="alias" id="alias" type="text" size="30" maxlength="100" />
		</td>
	</tr>
	<tr>
	  <td align="center">&nbsp;</td>
	</tr>
	<tr>
	  <td align="center"><input type="radio" name="radio" id="contiene" value="1" />
       <font id="textoContiene">Contiene: </font> <br /></td>
	</tr>
	<tr>
	   <td align="center"><input type="radio" name="radio" id="esIgual" value="0" />
       <font id="textoEsIgual">Es igual a: </font></td>
	</tr>
	<tr>
	  <td align="center">&nbsp;</td>
	</tr>
	<tr>
	  <td align="center">* Se aceptan letras, números y caracteres especiales </td>
	</tr>
	<tr>
	  <td align="center">(No distingue entre mayúsculas y minúsculas)</td>
	</tr>
	<tr>
	  <td align="center">&nbsp;</td>
	</tr>
	<tr>
	  <td align="center"><input name="buscarAlias" id="buscarAlias" type="button" value="Buscar" size="30" class="btn_Generico"/></td>
	</tr>
	<tr>
	  <td valign="middle" align="center">
	  	<table id="tablaBuscarCasoPorAlias"></table>
	</td>
 	</tr>
</table>
