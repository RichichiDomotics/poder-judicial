<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

<script type="text/javascript">
var reloadGridCaso = false;
		//Llena el grid con los resultados de la busqueda, pasa como parametros el No de expediente
		  function llenaGridCaso(){
			  var noCaso = $("#noCaso").val();				//Numero de Caso
			  
						if (validaCaso==true){
							if (reloadGridCaso) {
								  jQuery("#tablaBuscarPorNumeroDeCaso").jqGrid('setGridParam', {postData:{noCaso: noCaso}});
								  $("#tablaBuscarPorNumeroDeCaso").trigger("reloadGrid"); 
							  } else {
								  reloadGridCaso = true;
								  jQuery("#tablaBuscarPorNumeroDeCaso").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarExpedientePorNumeroDeCasoGeneral.do', 						
												//data: 'noCaso='+noCaso,
												datatype: "xml", 
												mtype: 'POST',						
												postData: {
												noCaso: function()     { return noCaso; }
																										
												},
												colNames:['No. Caso','No. Expediente',], 
												colModel:[ {name:'caso', index:'caso', width:100, sortable:false},
												{name:'exp',index:'exp', width:150, sortable:false}, 
												
												], 
													autowidth: true, 
													rowNum:10,
													rowList:[25,50,100],
													pager: jQuery('#pager11'), 
													sortname: 'id', 
													rownumbers: true,
													gridview: true, 
													viewrecords: true, 
													sortorder: "desc", 
													height: "50%",
													onSelectRow: function(id){
														seleccionarNumeroExpediente(id.split(",")[0],id.split(",")[1]);
														},
													caption:"Resultado de la B&uacute;squeda" 
													
											});
							 			 }
							}			  
					   
				  }
		  
		
		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposCaso(){

			if ($('#noCaso').val()==''){
				if($('#noCaso').val()==''){
					customAlert("Favor de ingresar un número de caso");
					validaCaso=false;
				}
					}else {
				validaCaso=true;
					}						
												
		}		

		function sacarIdDetenido(id){
			idDetenido=id;
		}

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
    <td height="25" colspan="2" align="center">Número de Caso:
		<input type="text" name="noCaso" id="noCaso" size="50" maxlength="50" />&nbsp;&nbsp;</td>
    </tr>
 

  <tr id="espacios" >
    <td height="25" colspan="2" align="center">* Sólo se permiten numeros, letras y los caracteres especiales: "-","/" y "Y"</td>
    </tr>

  <tr >
    <td height="25" colspan="2" align="center">&nbsp;</td>
    </tr>

	<tr >
    <td height="25" colspan="2" align="center"><input type="button" name="buscarCaso" value="Buscar" id="buscarCaso" class="btn_Generico"/>
    </tr>
    <tr>
     <td align="center">
       <table align="center" id="tablaBuscarPorNumeroDeCaso"></table><div id="pager11"></div>
	 </td>

</table>
<script type="text/javascript">
$("#buscarCaso").bind("click",validaCamposCaso);
$("#buscarCaso").bind("click",llenaGridCaso);

</script>
