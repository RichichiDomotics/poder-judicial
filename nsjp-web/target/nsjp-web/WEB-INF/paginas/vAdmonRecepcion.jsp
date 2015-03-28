<script type="text/javascript"> 

function turno(){
	
if($('#checpenal').is(':checked')){

	$("#tiem").val('');
$("#textemer").css("display", "block");


}else{
	$('#checemer').attr('checked', false); 

	$("#tiem").val('');
	$("#textemer").css("display", "none");
	
}
}

function cargaTurno(){
	//alert($('#checemer').is(':checked'));
	//
	var params ='tipoTurno=' + $(':radio[name=kevinTipoTurno]:checked').val();
	params+='&esUrgente=' + $('#checemer').is(':checked');
	
	//var tipoTurno  = $(':radio[name=RadioGroup1]:checked').val();	
	//var esUrgente = true;
	//var rAdmin = $(':radio[name=checadmin]:checked').val();	//nombre
	 
    $.ajax({
	      
    	  url: '<%= request.getContextPath()%>/GenerarTurno.do',
    	  dataType: 'xml',
    	  Type: 'POST',
    	  data:params,
    	  async: false,
    	  success: function(xml){
    		  //llamar(xml);
    		  $('#xml').val(xml);
    		  	    		  	
    		  $('#tiem').val($(xml).find('turnoDTO').find('numeroTurno').text());
    		  $('#tiem2').val($(xml).find('turnoDTO').find('numeroTurno').text());
		    		
    			    		
		  }
    	});
      
	}
</script> 

 
<br />
<table width="500"  border="1"   align="center"  >
  <tr align="center">
    <td colspan="2" >Bienvenido a atención temprana.</td> 
  </tr>
  <tr align="center">
    <td >Generar Turno.</td><td width="80" align="right" >N&uacute;mero de Turno: <input type="text" id="tiem" style="border: 0;" maxlength="30" > </td>
  </tr>
 
  <tr align="center" onclick="turno()">
    <td width="118" > 
      <input type="radio" name="kevinTipoTurno" value="0" id="checpenal" /> Atención Penal
      </td> 
      <td width="173"><input type="radio" name="kevinTipoTurno" value="1" id="checadmin"  />Atención Administrativa
      </td>
  </tr>
 
   <tr align="center"><td  colspan="2"><table width="200" border="0" align="center">
  <tr>
    <td id="emer"><span style="display:none;" id="textemer"><input name="checemer" type="checkbox"  id="checemer" />Urgente </span></td>
    <td  ><input name="Generar Turno de atención penal" type="button"  id="botpenal" value="Generar Turno" class="btn_Generico"/></td></tr>
</table>
 
</td> 
  </tr>
</table>