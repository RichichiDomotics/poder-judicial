<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>		
 <script type="text/javascript">
 
//funcion para limitar las horas de los textbox de horas
 function customRange(input) {
 	if($("#idFechaDateLapso").val()==$("#idFechaDateLapso2").val())
 	{
		  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
			$('#idHoraDateLapsoInicio').timeEntry('getTime') : null),
			maxTime: (input.id == 'idHoraDateLapsoInicio' ?
			$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
 	}
 	else
 	{
 		return {minTime: (input.id == 'idHoraDateLapsoInicio' ?
 				null : null),
 				maxTime: (input.id == 'idHoraDateLapsoInicio' ?
 				null : null)};
 	}
 }
  
 $(function(){
   $('#idHoraDateLapsoInicio,#idHoraDateLapsoFin').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
 });

    /*
     *Funcion para recuperar los datos de tiempo lapso
     */
     function recuperaDatosTiempoLapso(idCalidad)
     {
  	   var lsDatosTiempoLapso="";
  	 		lsDatosTiempoLapso="fechaInicioLapso="+$("#idFechaDateLapso").val();
  			lsDatosTiempoLapso+="&horaInicioLapso="+$("#idHoraDateLapsoInicio").val();
  			lsDatosTiempoLapso+="&fechaFinLapso="+$("#idFechaDateLapso2").val();
  			lsDatosTiempoLapso+="&horaFinLapso="+$("#idHoraDateLapsoFin").val();
  	   return lsDatosTiempoLapso;
     }

     /*
      *Funcion para pintar los datos mediante la recuperacion del xml de tiempo lapso
      */
     function pintaDatosTiempoLapso(xml){
    	 $('#idFechaDateLapso').val($(xml).find('expedienteDTO').find('strFechaApertura').text());
    	 $('#idHoraDateLapsoInicio').val($(xml).find('expedienteDTO').find('strHoraApertura').text());
    	 $('#idFechaDateLapso2').val($(xml).find('expedienteDTO').find('strFechaCierre').text());
    	 $('#idHoraDateLapsoFin').val($(xml).find('expedienteDTO').find('strHoraCierre').text());
     }
  
     function seteaDatosTiempoLapso(xml)
     {
    	 var datos=$(xml).find('tiempo').find('fechaInicio').text().split(' ');
    	 var datos2=$(xml).find('tiempo').find('fechaFin').text().split(' ');
    	 var fechaBien=datos[0].split('-');
    	 var fechaBien2=datos2[0].split('-');
    	 $("#idFechaDateLapso").val(fechaBien[2]+"/"+fechaBien[1]+"/"+fechaBien[0]);
    	 $("#idHoraDateLapsoInicio").val(datos[1].substring(0,5));
    	 //$("#idHoraDateLapsoInicio").timeEntry('setTime',datos[1].substring(0,5));
    	 $("#idFechaDateLapso2").val(fechaBien2[2]+"/"+fechaBien2[1]+"/"+fechaBien2[0]);
    	 $("#idHoraDateLapsoFin").val(datos2[1].substring(0,5));
    	 //$("#idHoraDateLapsoFin").timeEntry('setTime', datos2[1].substring(0,5));
    	 $("#idHoraDateLapsoInicio").click();
    	 $("#idHoraDateLapsoFin").click();
     }
     
     function seteaDatosTiempoLapsoDetencion(xml)
     {
    	 var datos=$(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('strFechaInicioDetencion').text().split(' ');
    	 var datos2=$(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('strFechaFinDetencion').text().split(' ');
    	 var fechaBien=datos[0].split('-');
    	 var fechaBien2=datos2[0].split('-');
    	 $("#idFechaDateLapso").val(fechaBien[2]+"/"+fechaBien[1]+"/"+fechaBien[0]);
    	 $("#idHoraDateLapsoInicio").val(datos[1].substring(0,5));
    	 //$("#idHoraDateLapsoInicio").timeEntry('setTime',datos[1].substring(0,5));
    	 $("#idFechaDateLapso2").val(fechaBien2[2]+"/"+fechaBien2[1]+"/"+fechaBien2[0]);
    	 $("#idHoraDateLapsoFin").val(datos2[1].substring(0,5)+' ');
    	 //$("#idHoraDateLapsoFin").timeEntry('setTime', datos2[1].substring(0,5));
    	 $("#idHoraDateLapsoInicio").click();
    	 $("#idHoraDateLapsoFin").click();
     }
     
     
     function bloqueaCamposTiempoLapso(bandera)
     {
    	if(parseInt(bandera)==0)
    	{
		   	 $("#idFechaDateLapso").attr('disabled','disabled');
		   	 $("#idHoraDateLapsoInicio").attr('disabled','disabled');
		   	 $("#idFechaDateLapso2").attr('disabled','disabled');
		   	 $("#idHoraDateLapsoFin").attr('disabled','disabled');
		   	 $("#idHoraDateLapsoInicio").attr('disabled','disabled');
		   	 $("#idHoraDateLapsoFin").attr('disabled','disabled');
    	}
    	else
    	{
    		 $("#idFechaDateLapso").attr('disabled','');
	       	 $("#idHoraDateLapsoInicio").attr('disabled','');
	       	 $("#idFechaDateLapso2").attr('disabled','');
	       	 $("#idHoraDateLapsoFin").attr('disabled','');
	       	 $("#idHoraDateLapsoInicio").attr('disabled','');
	       	 $("#idHoraDateLapsoFin").attr('disabled','');
    	}    	
     }

 	//Funcion que valida si los campos estan llenos al enviar 
 	function validaCamposFecha() {

		if ($('#idFechaDateLapso').val() == '' || $('#idFechaDateLapso2').val() == '') {
			customAlert("Debes ingresar tanto la fecha de inicio como la de fin");
			validaFecha = false;
		} else {

			var fechaIniVal = $('#idFechaDateLapso').val();
			var fechaFinVal = $('#idFechaDateLapso2').val();

 			var inicio = fechaIniVal.split("/");
 			var fin = fechaFinVal.split("/");

 			if(fin[2]>inicio[2]){
 				validaFecha=true;
 			}
 			else{
 				if(fin[2]<inicio[2]){
 					validaFecha=false;
 				}
 				else{
 					if(fin[1]>inicio[1]){
 						validaFecha=true;
 					}	
 					else{
 						if(fin[1]<inicio[1]){
 							validaFecha=false;
 						}
 						else{
 							if(fin[0]>=inicio[0]){
 								validaFecha=true;
 							}
 							else{
 								validaFecha=false;
 							}
 						}
 					}
 				}
 			}
 			if(validaFecha==false){	
 				customAlert("La fecha final debe de ser mayor o igual a la fecha inicial");
 			}
 		}	
 	}   
 	
   function revisaLongitudFechas()
   {
		if($("#idFechaDateLapso").val().length==0)
		{
			$("#idHoraDateLapsoInicio").attr("disabled","disabled");
			$("#idHoraDateLapsoFin").attr("disabled","disabled");
			$("#idHoraDateLapsoInicio").val("");
			$("#idHoraDateLapsoFin").val("");
		}
		else
		{
			$("#idHoraDateLapsoInicio").attr("disabled","");
			if($("#idFechaDateLapso2").val().length==0)
			{
				$("#idHoraDateLapsoFin").attr("disabled","disabled");	
			}
			else
			{
				$("#idHoraDateLapsoFin").attr("disabled","");
				//si la fecha de fin seleccioanda es el dia de hoy seteamos la hora maxima 
				if($("#idFechaDateLapso2").val()==fechaMax)
				{
					$("#idHoraDateLapsoFin").timeEntry('destroy');
					$("#idHoraDateLapsoFin").timeEntry({show24Hours: false,defaultTime: null,maxTime: timeMax});
				}
				else
				{
					$("#idHoraDateLapsoFin").timeEntry('destroy');
					$("#idHoraDateLapsoFin").timeEntry({show24Hours: false,defaultTime: null,maxTime: null});
				}
			}
		}
   }
   
   function habilitaTextDetenido() {
	   	$('#lbTextInicio').hide();
	   	$('#horaTextInicio').hide();
	   	$('#lbTextFin').hide();
	   	$('#horaTextFin').hide();
	   	
		$('#lbTextInicioDet').show()
		$('#horaTextInicioDet').show();
		$('#lbTextFinDet').show()
		$('#horaTextFinDet').show();
   }
   
   function deshabilitaTextDetenido() {
	   $('#lbTextInicio').show();
      	$('#horaTextInicio').show();
      	$('#lbTextFin').show();
      	$('#horaTextFin').show();
      	
   		$('#lbTextInicioDet').hide()
   		$('#horaTextInicioDet').hide();
   		$('#lbTextFinDet').hide()
   		$('#horaTextFinDet').hide();
   }
  
</script>


<table width="200px" height="100px" border="0">
	<tr>
		<td id="lbTextInicio">
			<label>Fecha Inicio:</label>
		</td>
		<td id="lbTextInicioDet">
			<label>Fecha Detencion:</label>
		</td>
		<td><div id="idFechaDateDivLapso">
		<input type="text" id="idFechaDateLapso" onchange="revisaLongitudFechas()" size="10" style="width: 70px;">
		</div></td>
	</tr>
	<tr>
		<td id="horaTextInicio">Hora Inicio:</td>
		<td id="horaTextInicioDet">Hora Detenciòn:</td>
		<td><div id="idHoraLapso">
		<input size="10" class="timeRange" type="text" id="idHoraDateLapsoInicio" value="7:00 AM">
		</div></td>
	</tr>
	<tr>
		<td id="lbTextFin">Fecha Fin:</td>
		<td id="lbTextFinDet">Fecha Disponibilidad:</td>
		<td><div id="idFechaDateDivLapso2">
		<input type="text" id="idFechaDateLapso2" onchange="revisaLongitudFechas()" size="10" style="width: 70px;">
		</div></td>
	</tr>
	<tr>
		<td id="horaTextFin">Hora Fin:</td>
		<td id="horaTextFinDet">Hora Disponibilidad:</td>
		<td><div id="idHoraLapso2">
		<input type="text" id="idHoraDateLapsoFin" size="10" class="timeRange" value="8:00 AM">
		</div></td>
	</tr>
</table>
