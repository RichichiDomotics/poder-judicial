<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">

  
   
		//Llena el grid con los resultados de la busqueda, pasa como parametros el No de caso
		  function llenaGridFecha(){
			  var fechaInicio = $("#idFechaDateLapso").val();
			  var fechaFin = $("#idFechaDateLapso2").val();
			  
			  
						if (validaFecha==true){
							if (reloadGridFecha) {
								  jQuery("#tablaBuscarPorFecha").jqGrid('setGridParam', {postData:{fechaInicio: fechaInicio, fechaFin: fechaFin}});
								  $("#tablaBuscarPorFecha").trigger("reloadGrid"); 
							  } else {
								  
								  reloadGridFecha = true;
								  jQuery("#tablaBuscarPorFecha").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarCasoPorFecha.do', 						
												datatype: "xml", 
												mtype: 'POST',						
												postData: {fechaInicio: function()  {return fechaInicio;},
												           fechaFin: function()		{return fechaFin;}																										
												},
												colNames:['No. Caso'], 
												colModel:[ {name:'caso', index:'caso', width:100, sortable:false}], 
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
		  
		
		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposFecha(){

			if ($('#idFechaDateLapso').val()==''|| $('#idFechaDateLapso2').val()==''){
				alertDinamico("Debes ingresar la fecha de inicio y fin");
				validaFecha=false;
			}else {
			validaFecha=true;
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
		<td colspan="2" align="right"></td>
	  </tr>
    <tr>
      <td colspan="2" align="center">&nbsp;</td>
      </tr>
    <tr>
      <td colspan="2" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" align="center">Fecha Inicio:
      <input type="text" id="idFechaDateLapso" size="15" maxlength="10" readonly="readonly" ></td>
    </tr>
    <tr>
      <td colspan="2" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" align="center">Fecha Fin:    
        <input type="text" id="idFechaDateLapso2" size="15" maxlength="10" readonly="readonly" ></td>
    </tr>
    <tr>
		<td colspan="2" align="center">&nbsp;</td>
	  </tr>
	<tr >
    <td height="25" colspan="2" align="center">&nbsp;</td>
    </tr>

	<tr >
    <td height="25" colspan="2" align="center"><input type="button" name="buscarFecha" id="buscarFecha" value="Buscar"  class="btn_Generico"/>
    </tr>
    <tr>
     <td align="center">
       <table align="center" id="tablaBuscarPorFecha"></table>
	 </td>

</table>

