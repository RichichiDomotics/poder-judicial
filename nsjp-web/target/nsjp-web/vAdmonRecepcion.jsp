<script type="text/javascript"> 
 
$(document).ready(function() {

	//cambia los elementos de la pantalla dependiendo si viene de defensoria 
	tipoAtencion(defensoria);
});


function turno(){
	
if($('#checpenal').is(':checked')){
	$("#tiem").val('');
	$("#textemer").css("display", "");
	$("#checemer").attr('disabled', "");

} else{
	$('#checemer').attr('checked', false); 
	$("#tiem").val('');
	$("#textemer").css("display", "none");
	$("#checemer").attr('disabled', "disabled");
}
}

//genera turno 
function cargaTurno(){
	
	//alert("cargaTurno()");
	var params;
	if(defensoria==1)
	{
		if($(':radio[name=kevinTipoTurno]:checked').val()==1)
		{
			//asesoria legal
			params = 'tipoTurno='+  3;
		}
		else
		{
				//solicitud ciudadana
				params = 'tipoTurno='+  4;
		}
	}
	else
	{
		params ='tipoTurno=' + $(':radio[name=kevinTipoTurno]:checked').val();
	}
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
        	      		  
    		  $('#xml').val(xml);
    		  $('#tiem').val($(xml).find('turnoDTO').find('numeroTurno').text());
    		  $('#tiem2').val($(xml).find('turnoDTO').find('numeroTurno').text());
    		 	
    			    		
		  }
    	});

    document.frmDoc.submit();
      
	}
	
function muestradatospersona(){
	$("#divDatos").css("display", "block");
	$("#divTurno").css("display", "none");
	
	
}

function tipoAtencion(defensoria){

	if(parseInt(defensoria)== 1){

		$("#atencionPenal").empty();
		$("#atencionPenal").append('Solicitud ciudadana de defensor');
		$("#atencionNoPenal").empty();
		$("#atencionNoPenal").append('Asesoria Legal');
		$("#checemer").css("display", "none");
		$("#textemer").empty();
		$('#tablaFondo').removeClass("back_pantalla_bienvenido");
		$('#tablaFondo').addClass("back_pantalla_bienvenido_def");
		

		}else{


			}
}
</script> 

 <br/>
 <br/>
 <br/>
 <br/>
 <br/>
 
 <br/>
 <br/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><table width="537" border="0" align="center" cellpadding="0" cellspacing="0" class="back_pantalla_bienvenido" id="tablaFondo">
      <tr>
        <td height="277" align="center" valign="top"><table width="490" border="0" cellspacing="0" cellpadding="0">
          <tr>
				    <td height="31" align="center" valign="bottom" class="txt_cuad_bienvenido">Bienvenido a atención temprana.</td> 
          </tr>
        </table>
          <TABLE width=490 border=0 align=center>
          <TBODY>
            <TR align=middle>
              <TD height="35" colSpan=3 align="center" valign="middle">&nbsp;</TD>
            </TR>
            <TR align=middle>
				    <td width="245" align="left" ><input type="radio" name="kevinTipoTurno" value="1" id="checadmin"  onclick="turno()"/>
				    <span id="atencionNoPenal" class="txt_cuad_bienvenido">Atención no Penal</span></td><TD width=235 rowSpan=2 align="left" valign="top"><span class="txt_cuad_bienvenido">N&uacute;mero de Turno:</span>
				     <input type="text" id="tiem" maxLength=30 style="BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; font-size: 1cm; " size="3"  > </td>
			</tr>				 
			 <TR align=middle>
              <TD align="left">
				      <input type="radio" name="kevinTipoTurno" value="0" id="checpenal" checked="checked" onClick="turno()"/> 
				      <span id="atencionPenal" class="txt_cuad_bienvenido">Atención Penal</span>
				      <br/> 
				      <dir>
					  <input id="checemer" name="checemer" type="checkbox"   />&nbsp;
					  <span id="textemer" class="txt_cuad_bienvenido" style="display:;" >Urgente</span> 
					  </dir>
			</tr>

            <TR align=middle>
              <TD height="40" colSpan=3>&nbsp;</td>
            </tr>
        </table>
          <TABLE border=0 width="50%" align=center>				 
            <TBODY>
              <TR valign="top">
                <TD width="45%" align="right" valign="top" class="txt_cuad_bienvenido">Generar Turno</td>
				<TD width="11%" align="left" valign="top"><img src="<%=request.getContextPath() %>/resources/images/icn_turno.png" id="botpenal" width="16" height="14" onclick="cargaTurno();" style="cursor: pointer;"></TD>
                <TD width="22%" class="txt_cuad_bienvenido">&nbsp;</td>
             </TR>
            </TBODY>
           <table width="490" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="25">&nbsp;</td>
            </tr>
        	</table></td>
      	</tr>
    </table>

				    <form name="frmDoc" action="<%= request.getContextPath() %>/ImprimirTurno.do" method="post">
					<!-- input type="hidden" name="documentoId" /-->
					</form>

				 
			
<script type="text/javascript">
//$("#botpenal").click(cargaTurno);
</script>
