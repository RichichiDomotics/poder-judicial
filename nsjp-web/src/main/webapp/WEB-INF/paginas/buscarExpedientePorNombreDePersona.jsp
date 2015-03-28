<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
    
		//Llena el grid con los resultados de la busqueda, pasa como parametros el nombre y el apellido
		  function llenaGridNombre(){
			  
			  var nombre = $("#nombre").val();				//nombre
			  var apellido = $("#apellido").val();				//apellido
			  var apellidoMat = $("#apellidoMat").val();        		//apellidoMat
			  
			  
						if (validaNombre==true){
							if (reloadGridNombre) {																
								  jQuery("#tablaBuscarExpedientePorNombreDePersona").jqGrid('setGridParam', {postData:{nombre: nombre,apellido: apellido, apellidoMat: apellidoMat}});
								  $("#tablaBuscarExpedientePorNombreDePersona").trigger("reloadGrid"); 
							  } else {								 
								  reloadGridNombre = true;
								  jQuery("#tablaBuscarExpedientePorNombreDePersona").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarExpedientePorNombreDePersona.do', 						
												datatype: "xml", 
												mtype: 'POST',						
												postData: {
													nombre: function()     { return nombre; },
													apellido: function()     { return apellido; },													
													apellidoMat: function() 	{ return apellidoMat; }
												},
												colNames:['No. Caso','Expediente','Nombre','A. Paterno','A. Materno','Calidad'], 
												colModel:[ {name:'caso', index:'caso',width:125, sortable:false},
															{name:'expediente',index:'expediente', width:90, sortable:false}, 
															{name:'nombre',index:'nombre', width:40, sortable:false}, 
															{name:'apaterno',index:'apaterno', width:45, sortable:false}, 
															{name:'amaterno',index:'amaterno', width:45, sortable:false},
															{name:'calidad',index:'calidad', width:50, sortable:false} 
															], 
													autowidth: true, 
													pager: jQuery('#pagerBuscarExpedientePorNombreDePersona'), 
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
															var data = jQuery("#tablaBuscarExpedientePorEvidencia").jqGrid('getRowData',id);
															parent.cerrarEtapa(id, data.expediente);
														}
													},
													caption:"Resultado de la B&uacute;squeda" 
											});
							 			 }
							}			  
					   
				  }
		
		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposNombre(){

			if ($('#nombre').val()=='' && $('#apellido').val()=='' && $('#apellidoMat').val()==''){
					customAlert("Favor de ingresar un Nombre o un Apellido");
					validaNombre=false;
				}
			else { 

				validaNombre=true;
				
				}
	}
			
</script>

<table width="650" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td >
    </td>
  </tr>
  <tr>
    <td height="10">&nbsp;
      
    </td>
    </tr>
  <tr>
    <td height="12" align="center">Nombre(s):</td>
    </tr>
  <tr>
    <td height="13" align="center"><input type="text" name="nombre" id="nombre" size="40" maxlength="50" /></td>
    </tr>
  <tr>
    <td height="25" align="center">Apellido Paterno:</td>
    </tr>
  <tr id="nombreApellido">
    <td height="25" align="center">&nbsp;
      <input type="text" name="apellido" id="apellido" size="40" maxlength="50" />      &nbsp;</td>
    </tr>
  <tr>
    <td height="25" align="center">Apellido Materno:</td>
    </tr>
  <tr id="nombreApellidoMat">
    <td height="25" align="center">&nbsp;
      <input type="text" name="apellidoMat" id="apellidoMat" size="40" maxlength="50" />      &nbsp;</td>
    </tr>

  <tr id="espacios" >
    <td height="25" align="center">* Sólo se permiten letras y espacios</td>
    </tr>

  <tr >
    <td height="25" align="center">&nbsp;</td>
    </tr>

	<tr >
    <td height="25" align="center"><input type="button" name="buscarNombre" value="Buscar" id="buscarNombre" class="btn_Generico"/></td>
    </tr>
	<tr>
     <td valign="middle" align="center">
     	<table id="tablaBuscarExpedientePorNombreDePersona"></table>
     	<div id="pagerBuscarExpedientePorNombreDePersona"></div>
	 </td>
   </tr>
</table>

