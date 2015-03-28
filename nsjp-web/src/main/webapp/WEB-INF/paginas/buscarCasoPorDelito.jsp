<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
	
		//Llena el grid con los resultados de la busqueda, pasa como parametros la evidencia y una a 5 palabras clave
		  function llenaGridDelito(){

			   	var delito = $("#catDelito option:selected").html();				//delito			   	
						if (validaDelito==true){
							if (reloadGridDelito) {
								  jQuery("#tablaBuscarCasoPorDelito").jqGrid('setGridParam', {postData: {delito: delito}});
								  $("#tablaBuscarCasoPorDelito").trigger("reloadGrid"); 
							  } else {
								  reloadGridDelito = true;
								  jQuery("#tablaBuscarCasoPorDelito").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarCasoPorDelito.do', 						
												datatype: "xml", 
												mtype: 'POST',						
												postData: {delito: function()     {return delito;}
													
												},
												colNames:['Caso'], 
												colModel:[ {name:'caso',index:'caso', width:500, sortable:false}

														], 
													autowidth: false, 
													pager: jQuery('#pagerBuscarCasoPorDelito'), 
								                    rowNum:10,
								                    rowList:[10,20,30],
													viewrecords: true, 
													height: "60%",
													onSelectRow: function(id){
														//uno(id);
														},
													caption:"Resultado de la B&uacute;squeda" 
							                }).navGrid('#paginadorDetalle',{edit:false,add:false,del:false});
							 			 }
							}			  
					   
				  }

		  function uno(tr){
				$.newWindow({id:"iframewindowDetalleCasoDelito", statusBar: true, posx:200,posy:50,width:806,height:500,title:"Detalle de Caso", type:"iframe"});
				$.updateWindowContent("iframewindowDetalleCasoDelito",'<iframe src="<%= request.getContextPath() %>/generarDetalleDeBusquedaDeCaso.do?idRow='+ tr + '"  width="806" height="500" />');		
				
			} 	
		//Funcion que valida si los campos estan llenos al enviar 
			function validaCamposDelito(){

				if ($('#catDelito').val()==''){
						alertDinamico("Se debe seleccionar un delito");
						validaDelito=false;
					}else {
					validaDelito=true;
						}						
													
			}		
					
		//Funcion que carga los combos de tipo de delito
		function cargaComboDelito() {
	         $.ajax({
	 			type: 'POST',
	 			url: '<%= request.getContextPath()%>/consultarCatalogoDelitos.do',
	 			data: '',
	 			dataType: 'xml',
	 			async: false,
	 			success: function(xml){
	 				$(xml).find('delitos').each(function(){
	 					$('#catDelito').append('<option value="' + $(this).find('catDelitoId').text() + '">'+ $(this).find('nombre').last().text() + '</option>');
	 				});
	 			}
	 		});
		}
		
		/*
		* Funcion para llamar la funcion de habilitar los elementos de la pantalla
		*/
		function onSelectChangeTipoDel() {
		  	var selectedDel = $("#catDelito option:selected");		
			enableControlsDelito(selectedDel.val());
		}
		    
		/*
		*Funcion que habilita o deshabilita los elementos de la pagina
		*/
		function enableControlsDelito(tipo) 
		{
	//		var  MIN = 250;
	//		var  MAX = 421;
			 
	//		if ( MIN<=  tipo  && tipo <= MAX) 
			if(parseInt(tipo)==-1){
			   $('#buscarDelito').attr('disabled', 'disabled');
			    			    			    
			}
			else
			{
			    			     
			    $('#buscarDelito').attr('disabled', '');
			   
			}
		}
		
</script>
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
<table width="650" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td >
    	
    </td>
  </tr>
  <tr>
    <td height="10" colspan="6">&nbsp;
      
    </td>
    </tr>
  <tr>
    <td height="12" colspan="6" align="center">Seleccione el Delito: 
      <select name="catDelito" id="catDelito"></select></td>
  </tr>
  <tr>
    <td height="13" colspan="6" align="center">&nbsp;</td>
  </tr>
  
  <tr id="palabra2" >
    <td height="25" colspan="6" align="center">&nbsp;    </td>
    </tr>

</table>

<table width="650" border="0" cellspacing="0" cellpadding="0">
<tr>
    	<td width="650" height="25" align="center">&nbsp;</td>
  	</tr>
</table>

   <table width="650" border="0" cellspacing="0" cellpadding="0">
   <tr>
   		<td align="center">
			<input type="button" name="buscarDelito" value="Buscar" id="buscarDelito"  class="btn_Generico"/>	
			
		</td>
   </tr>
   <tr>
     <td><p></td>
   </tr>
   <tr>
     <td valign="middle" align="center">
	     <table id="tablaBuscarCasoPorDelito"></table>
	     <div id="pagerBuscarCasoPorDelito"></div>
	 </td>
   </tr>
   </table>