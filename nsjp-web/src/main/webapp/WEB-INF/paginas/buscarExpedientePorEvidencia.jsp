<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
	
		//Llena el grid con los resultados de la busqueda, pasa como parametros la evidencia y una a 5 palabras clave
		  function llenaGridEvidencia(){
			 	var evidencia = $("#catEvidencia").val();				//evidencia
			 	var palabraUno = $("#palabraClave").val();				//palabra clave uno
			 	var palabraDos = $("#palabraClave2").val();				//palabra clave dos
			 	var palabraTres = $("#palabraClave3").val();			//palabra clave tres
			 	var palabraCuatro = $("#palabraClave4").val();			//palabra clave cuatro
			 	var palabraCinco = $("#palabraClave5").val();			//palabra clave cinco

						if (validaEvidencia==true){
							if (reloadGridEvidencia) {
								  jQuery("#tablaBuscarExpedientePorEvidencia").jqGrid('setGridParam', {postData: {evidencia: evidencia,palabraUno: palabraUno, palabraDos: palabraDos,palabraTres: palabraTres,palabraCuatro: palabraCuatro, palabraCinco: palabraCinco}});
								  $("#tablaBuscarExpedientePorEvidencia").trigger("reloadGrid"); 
							  } else {
								  reloadGridEvidencia = true;
								  jQuery("#tablaBuscarExpedientePorEvidencia").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarExpedientePorEvidencia.do', 						
												data: 'catEvidencia='+evidencia&'palabraClave='+palabraUno,
												datatype: "xml", 
												mtype: 'POST',						
												postData: {evidencia: function()     {return evidencia;},
													palabraUno: function()     { return palabraUno; },
													palabraDos: function()     { return palabraDos; },
													palabraTres: function()     { return palabraTres; },
													palabraCuatro: function()     { return palabraCuatro; },
													palabraCinco: function()     { return palabraCinco; }
												},
												colNames:['No. Caso','Expediente'], 
												colModel:[ {name:'caso', index:'caso',width:50, sortable:false},
															{name:'expediente',index:'expediente', width:150, sortable:false}], 
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
															var data = jQuery("#tablaBuscarExpedientePorEvidencia").jqGrid('getRowData',id);
															parent.cerrarEtapa(id, data.expediente);
														}else{
														
														}	
													},
													caption:"Resultado de la B&uacute;squeda" 
											});
							 			 }
							}			  
					   
				  }

		//		$.updateWindowContent("iframewindowDetalleExpEvidencia",'<iframe src="<%= request.getContextPath() %>/generarDetalleDeBusquedaDeExpediente.do?idRow='+ tr + '"  width="806" height="500" />');		

		function validaCamposEvidencia(){

				if ($('#palabraClave').val()==''&& $('#palabraClave2').val()==''&& $('#palabraClave3').val()==''&& $('#palabraClave4').val()==''&& $('#palabraClave5').val()==''){
						customAlert("Se debe capturar al menos una palabra clave");
						validaEvidencia=false;
					}else {
					validaEvidencia=true;
						}						
												
			}		
					
		//Funcion que carga los combos de tipo de evidencia
		function cargaCatEvidencia() {
		  $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/cargarExpedientePorEvidencia.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  success: function(xml){
	    		  $('#catEvidencia').empty();
		    	  $('#catEvidencia').append('<option value="-1">- Seleccione -</option>');
	    		  $(xml).find('catEvidencia').each(function(){
					$('#catEvidencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#catEvidencia').multiselect('refresh');
				   });
	    	  }
	    	});
		}
		
		/*
		* Funcion para llamar la funcion de habilitar los elementos de la pantalla
		*/
		function onSelectChangeTipoEv() {
		  	var selectedEv = $("#catEvidencia option:selected");		
			enableControlsEvidencia(selectedEv.val());
		}
		    
		/*
		*Funcion que habilita o deshabilita los elementos de la pagina
		*/
		function enableControlsEvidencia(tipo){
						 
			if (parseInt(tipo)==-1) 
			{
				$('#palabraClave').attr('disabled', 'disabled');
			    $('#palabraClave2').attr('disabled', 'disabled');
			    $('#palabraClave3').attr('disabled', 'disabled');
			    $('#palabraClave4').attr('disabled', 'disabled');
			    $('#palabraClave5').attr('disabled', 'disabled');			     
			    $('#buscarEvidencia').attr('disabled', 'disabled');			    
			    			    
			}
			else
			{
				 $('#palabraClave').attr('disabled', '');
				    $('#palabraClave2').attr('disabled', '');
				    $('#palabraClave3').attr('disabled', '');
				    $('#palabraClave4').attr('disabled', '');
				    $('#palabraClave5').attr('disabled', '');
				    
				    $('#buscarEvidencia').attr('disabled', '');
			    
			   
			}
		}
		
</script>


<table width="650" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td >
    	
    </td>
  </tr>
  <tr>
    <td height="10" colspan="6">&nbsp;
      
    </td>
    </tr>
  <tr>
    <td height="12" colspan="6" align="center">Seleccione la Evidencia: 
      <select name="catEvidencia" id="catEvidencia" style="text-align:left;"></select></td>
  </tr>
  <tr>
    <td height="13" colspan="6" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="6" align="center">Palabra(s) Clave</td>
    </tr>
  <tr id="palabra">
    <td width="125" height="25" align="center"><input type="text" name="palabraClave" id="palabraClave" size="15" maxlength="12" /></td>
    <td width="125" height="25" colspan="2" align="center"><input type="text" name="palabraClave2" id="palabraClave2" size="15" maxlength="12" /></td>
    <td width="125" height="25" align="center"><input type="text" name="palabraClave3" id="palabraClave3" size="15" maxlength="12" /></td>
    <td width="125" height="25" align="center"><input type="text" name="palabraClave4" id="palabraClave4" size="15" maxlength="12" /></td>
    <td width="125" align="center"><input type="text" name="palabraClave5" id="palabraClave5" size="15" maxlength="12" /></td>
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
			<input type="button" name="buscarEvidencia" value="Buscar" id="buscarEvidencia" class="btn_Generico"/>	
			<!--<input type="button" name="limpiar" value="limpiar campos" id="limpiar" class="btn_Generico"/>		-->
		</td>
   </tr>
   <tr>
     <td></td>
   </tr>
   <tr>
     <td valign="middle" align="center"><table id="tablaBuscarExpedientePorEvidencia"></table>
	 </td>
   </tr>
   </table>