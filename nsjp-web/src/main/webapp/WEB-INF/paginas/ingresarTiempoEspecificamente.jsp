
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/comun.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
 <script type="text/javascript">
    
	 function customRangeDef(input) {
	 	if($("#idFechaDate").val()==fechaMax)
	 	{
			  return {minTime: (input.id == 'idHoraDate' ?
						null : null),
					maxTime: (input.id == 'idHoraDate' ?
							timeMax : null)};
	 	}
	 	else
	 	{
	 		return {minTime: (input.id == 'idHoraDate' ?
	 				null : null),
	 				maxTime: (input.id == 'idHoraDate' ?
	 	    				null : null)};
	 	}
	 }
    
    $(function(){
      $('#idHoraDate').timeEntry({beforeShow: customRangeDef,timeSteps:[1,1,0],ampmPrefix: ' '});
    });

    /*
     *Funcion para recuperar los datos de tiempo especifico
     */
     function recuperaDatosTiempoEspecifico(idCalidad)
     {
  	   var lsDatosTiempo="";
  	 		lsDatosTiempo="fecha="+$("#idFechaDate").val();
  			lsDatosTiempo+="&hora="+$("#idHoraDate").val();
  	   return lsDatosTiempo;
     }

     /*
      *Funcion para pintar los datos mediante la recuperacion del xml de tiempo especifico
      */
     function pintaDatosTiempoEspecifico(xml){
  	   $('#idFechaDate').val($(xml).find('expedienteDTO').find('strFechaApertura').text());
  	   $('#idHoraDate').val($(xml).find('expedienteDTO').find('strHoraApertura').text());
  	   }
     
     function seteaDatosTiempoEspecifico(xml)
     {
    	 var datos=$(xml).find('tiempo').find('fechaInicio').text().split(' ');
    	 var fechaBien=datos[0].split('-');
    	 var hora=datos[1].split(".");
    	 var horaBien=hora[0].split(":");
    	 $("#idFechaDate").val(fechaBien[2]+"/"+fechaBien[1]+"/"+fechaBien[0]);
    	 $("#idHoraDate").timeEntry('destroy')
    	 //$("#idHoraDate").val(datos[1].substring(0,5));
    	 $('#idHoraDate').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
    	 $('#idHoraDate').timeEntry('setTime', formateaHoraTimeEntryTextBox(datos[1].substring(0,5)));
    	 
    	 //$("#idHoraDate").timeEntry('enable');
    	 //$("#idHoraDate").timeEntry('setTime',new Date());
    	 //$("#idHoraDate").click();
     }
     
     function bloqueaCamposTiempoEspecifico(bandera)
     {
    	if(parseInt(bandera)==0)
    	{
    	 $('#idFechaDate').attr('disabled','disabled');
    	 $('#idHoraDate').attr('disabled','disabled');
    	}
    	else
    	{
    		$('#idFechaDate').attr('disabled','');
       	 	$('#idHoraDate').attr('disabled','');
    	}
     }
  
     
     function revisaLongitudFechasEspecifica()
     {
			//si la fecha de fin seleccioanda es el dia de hoy seteamos la hora maxima 
			if($("#idFechaDate").val()==fechaMax)
			{
				$("#idHora").timeEntry('destroy');
				$("#idHora").timeEntry({show24Hours: false,defaultTime: timeMax,maxTime: timeMax});
			}
			else
			{
				$("#idHora").val(timeMax);
				$("#idHora").timeEntry('destroy');
				$("#idHora").timeEntry({show24Hours: false,defaultTime: timeMax,maxTime: null});
			}
     }
</script>

<table width="200px" height="100px" border="0">
	<tr>
		<td>Fecha:</td>
		<td><div id="idFechaDateDiv">
		<input type="text" id="idFechaDate" width="50px"> 
		</div></td>
	</tr>
	<tr>
		<td>Hora:</td>
		<td><div id="idHora">
		<input type="text" id="idHoraDate" size="10" class="timeRange" value="8:00 AM">
		</div></td>
	</tr>
</table>
