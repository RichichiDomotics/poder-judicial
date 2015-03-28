<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
	
		//Llena el grid con los resultados de la busqueda, pasa como parametros el No de expediente
		  function llenaGridExpediente(){
			  var noExpediente = $("#noExpediente").val();				//Numero de Expediente
			  
						if (validaExpediente==true){
							if (reloadGridExpediente) {
								  jQuery("#tablaBuscarPorNumeroDeExpediente").jqGrid('setGridParam', {postData:{noExpediente: noExpediente}});
								  $("#tablaBuscarPorNumeroDeExpediente").trigger("reloadGrid"); 
							  } else {
								  reloadGridExpediente = true;
								  jQuery("#tablaBuscarPorNumeroDeExpediente").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarExpedientePorNumeroDeExpediente.do', 						
												data: 'noExpediente='+noExpediente,
												datatype: "xml", 
												mtype: 'POST',						
												postData: {
												noExpediente: function()     { return noExpediente; }
																										
												},
												colNames:['No. Caso','No. Expediente'], 
												colModel:[ {name:'caso', index:'caso',width:50, sortable:false},
															{name:'expediente', index:'expediente',width:50, sortable:false}], 
													autowidth: true, 
													pager: jQuery('#pagerNoExpediente'), 
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
															var ret = jQuery("#tablaBuscarPorNumeroDeExpediente").jqGrid('getRowData',id);
															var numExp = ret.expediente;
															var idExp = id;
															parent.cerrarEtapa(idExp,numExp);															
														}else if(tipoOrigen > 2){
															detExp(id);
														}	
														
													},
													caption:"Resultado de la B&uacute;squeda" 
											});
							 			 }
							}			  
					   
				  }
	  

function detExp(id){
$.newWindow({id:"iframewindowDetalleExp", statusBar: true, posx:200,posy:50,width:806,height:500,title:"Detalle de Caso", type:"iframe"});
$.updateWindowContent("iframewindowDetalleExp",'<iframe src="<%= request.getContextPath() %>/generarDetalleDeBusquedaDeExpediente.do?idRow='+ id + '"  width="806" height="500" />');		

} 
		
		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposExpediente(){

			if ($('#noExpediente').val()==''){
				if($('#noExpediente').val()==''){
					customAlert("Favor de ingresar un Numero de Expediente");
					validaExpediente=false;
				}
					}else {
				validaExpediente=true;
					}						
												
		}		

		// Estas funciones hay que echarles ojo
		
</script>


<table width="650" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="306" >
    </td>
  </tr>
  <tr>
    <td height="10" colspan="2">&nbsp;
      
    </td>
    </tr>
  <tr>
    <td height="12" colspan="2" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="13" colspan="2" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="2" align="center"><span id="etiquetaNumExp">Número Expediente</span>:
		<input type="text" name="noExpediente" id="noExpediente" size="50" maxlength="50" />&nbsp;&nbsp;</td>
    </tr>
  
  <tr id="espacios" >
    <td height="25" colspan="2" align="center">* Sólo se permiten n&uacute;meros, letras y los caracteres especiales: "-","/" y "Y"</td>
    </tr>

  <tr >
    <td height="25" colspan="2" align="center">&nbsp;</td>
    </tr>

	<tr >
    <td height="25" colspan="2" align="center"><input type="button" name="buscarExpediente" value="Buscar" id="buscarExpediente" class="btn_Generico"/>
    </tr>
    <tr>
     <td align="center">
       <table align="center" id="tablaBuscarPorNumeroDeExpediente"></table><div id="pagerNoExpediente"></div>
	 </td>

</table>

