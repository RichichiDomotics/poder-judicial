	
		
	<script type="text/javascript"><!--
			
	//Llena el grid con los resultados de la busqueda, pasa como parametros la evidencia y una a 5 palabras clave
	  function llenaGridDocDeExpediente(){		 			 	

		 		 var departamento = $("#departamentos").val();				//departamento
		  
							  jQuery("#tablaGridDocDeExpediente").jqGrid(
										{ url:'<%= request.getContextPath() %>/buscarExpedientePorEvidencia.do', 						
											datatype: "xml", 
											mtype: 'POST',						
											postData: {departamento: function()     {return departamento;}													
											},
											colNames:['Folio','Tipo','Nombre','Origen','Fecha','Responsable'], 
											colModel:[{name:'folio',index:'folio', width:100, sortable:false},
											          {name:'tipo',index:'tipo', width:100, sortable:false},
											          {name:'nombre',index:'nombre', width:150, sortable:false},
											          {name:'origen',index:'origen', width:150, sortable:false},
											          {name:'fecha',index:'fecha', width:100, sortable:false},
											          {name:'responsable',index:'responsable', width:150, sortable:false}

											], 
												autowidth: true, 
												pager: jQuery('#pager'), 
												sortname: 'id', 
												rownumbers: true,
												gridview: true, 
												viewrecords: true, 
												sortorder: "desc", 
												height: "60%",
												caption:"Lista de Documentos Relacionados al Expediente" 
										});
						 			 }
								

				
					    		
	</script>
	<table width="750px" cellspacing="0" cellpadding="0" id="tablaGridDocDeExpediente">
  <tr>
    <td width="24">&nbsp;</td>
    <td width="700">&nbsp;</td>
    <td width="24">&nbsp;</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td><table width="700%" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
    </table>
	