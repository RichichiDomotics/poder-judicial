<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
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
			  		customAlert ("Debes seleccionar una de las dos opciones para buscar el Alias");
		  }else{	
			 
			   if (reloadGridAlias) {
				   				   				   
					  jQuery("#tablaBuscarExpedientePorAlias").jqGrid('setGridParam', {postData:{alias: alias, tipo: tipo}});
					  $("#tablaBuscarExpedientePorAlias").trigger("reloadGrid"); 
				  } else {
					  
					  reloadGridAlias = true;
					  jQuery("#tablaBuscarExpedientePorAlias").jqGrid(
								{ url:'<%= request.getContextPath() %>/buscarExpedientePorAlias.do', 
														
									datatype: "xml", 
									mtype: 'POST',						
									postData: {alias: function()     {return alias;},
											   tipo: function()     { return tipo; }
									},
									colNames:['Expediente','Nombre','A. Paterno','A. Materno', 'Calidad'], 
									colModel:[ {name:'expediente',index:'expediente', width:150, sortable:false}, 
												{name:'nombre',index:'nombre', width:150, sortable:false}, 
												{name:'apaterno',index:'apaterno', width:150, sortable:false}, 
												{name:'amaterno',index:'amaterno', width:150, sortable:false},
												{name:'calidad',index:'calidad', width:150, sortable:false}												 
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
												detEvi(id);
												},
										ondblClickRow: function(id) {
											if(tipoOrigen == 1){
												defensorId=id;
												var data = jQuery("#tablaBuscarExpedientePorAlias").jqGrid('getRowData',id);
												parent.cerrarEtapa(id, data.expediente);
											}	
										},
										caption:"Resultado de la B&uacute;squeda" 
								});
				  }		 		
		  }
	}

</script>

<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
      <td width="650"></td>
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
       <font id="textoContiene">Contiene </font> <br /></td>
	</tr>
	<tr>
	   <td align="center"><input type="radio" name="radio" id="esIgual" value="0" />
       <font id="textoEsIgual">Es igual a </font></td>
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
	  <td align="center">
	  	<table id="tablaBuscarExpedientePorAlias"></table>
			</td>
 	</tr>
</table>

</body>

