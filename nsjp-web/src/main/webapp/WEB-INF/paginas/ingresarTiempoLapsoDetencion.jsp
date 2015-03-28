<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<!--script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script-->
 <script type="text/javascript">

    function customRange(input) {
	  return {minTime: (input.id == 'idHoraDateLapsoFin' ?
		$('#idHoraDateLapsoInicio').timeEntry('getTime') : null),
		maxTime: (input.id == 'idHoraDateLapsoInicio' ?
		$('#idHoraDateLapsoFin').timeEntry('getTime') : null)};
    }
    $(function(){
      $('.timeRange').timeEntry({beforeShow: customRange,timeSteps:[1,1,0],ampmPrefix: ' '});
    });

    /*
     *Funcion para recuperar los datos de tiempo lapso
     */
     function recuperaDatosTiempoLapso(idCalidad)
     {
  	   var lsDatosTiempoLapso="";
  	   		lsDatosTiempoLapso="&fechaInicioLapso="+$("#idFechaDateLapso").val();
  			lsDatosTiempoLapso+="&horaInicioLapso="+$("#idHoraDateLapsoInicio").val();
  			lsDatosTiempoLapso+="&fechaFinLapso="+$("#idFechaDateLapso2").val();
  			lsDatosTiempoLapso+="&horaFinLapso="+$("#idHoraDateLapsoFin").val();
  	   return lsDatosTiempoLapso;
     }

     /*
      *Funcion para pintar los datos mediante la recuperacion del xml de tiempo lapso
      */
     function pintaDatosTiempoLapso(xml){
    	 var fecha = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('strFechaInicioDetencion').text();
		 var strfecha = obtenerFecha(fecha);   
		 var strhora = obtenerHora(fecha); 	 
    	 $('#idFechaDateLapso').val(strfecha);
    	 $('#idHoraDateLapsoInicio').val(strhora);

    	 var fechaRecepcion = $(xml).find('involucradoDTO').find('detenciones').find('DetencionDTO').find('strFechaFinDetencion').text();
    		 strfecha = obtenerFecha(fechaRecepcion);
    		 strhora = obtenerHora(fechaRecepcion);   
    	 $('#idFechaDateLapso2').val(strfecha);
    	 $('#idHoraDateLapsoFin').val(strhora);
     }
		
	//da formato a fecha
  	function obtenerFecha(fecha){
		var fh = fecha.split(" ");
		return fh[0].split("-")[2]+"/"+fh[0].split("-")[1]+"/"+fh[0].split("-")[0];	
	}
	//da formato a hora
	function obtenerHora(fecha){
		var fh = fecha.split(" ");
		return fh[1].substring(0,5);
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
		   	$("#idFechaDateLapso").datepicker('destroy');
			$("#idFechaDateLapso2").datepicker('destroy');
		   	 
    	}
    	else
    	{
    		 $("#idFechaDateLapso").attr('disabled','');
	       	 $("#idHoraDateLapsoInicio").attr('disabled','');
	       	 $("#idFechaDateLapso2").attr('disabled','');
	       	 $("#idHoraDateLapsoFin").attr('disabled','');
	       	 $("#idHoraDateLapsoInicio").attr('disabled','');
	       	 $("#idHoraDateLapsoFin").attr('disabled','');
	       	$("#idFechaDateLapso").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#idFechaDateLapso2").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
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
 				customAlert("La fecha final debe de ser mayor a la fecha inicial");
 			}
 			
 		}	
 	}  

</script>


<table width="250px" height="100px" border="0">
	<tr>
		<td>Fecha Detenci�n:</td>
		<td><div id="idFechaDateDivLapso">
		<input type="text" id="idFechaDateLapso" size="15">
		</div></td>
	</tr>
	<tr>
		<td>Hora Detenci�n:</td>
		<td><div id="idHoraLapso">
		<input size="10" class="timeRange" type="text" id="idHoraDateLapsoInicio" value="7:00 AM">
		</div></td>
	</tr>
	<tr>
		<td>Fecha Recepci�n:</td>
		<td><div id="idFechaDateDivLapso2">
		<input type="text" id="idFechaDateLapso2" size="15">
		</div></td>
	</tr>
	<tr>
		<td>Hora Recepci�n:</td>
		<td><div id="idHoraLapso2">
		<input type="text" id="idHoraDateLapsoFin" size="10" class="timeRange" value="8:00 AM">
		</div></td>
	</tr>
	<tr id="fechaSolicitud" style="display: none">
		<td>Fecha de solicitud de defensor:</td>
		<td>
		<input type="text" id="fechaSolicitudDefensor" size="15" disabled="disabled">
		</td>
	</tr>
	<tr id="horaSolicitud" style="display: none"> 
		<td>Hora de solicitud de defensor:</td>
		<td>
		<input type="text" id="horaSolicitudDefensor" size="10" class="timeRange" value="8:00 AM" disabled="disabled">
		</td>
	</tr>
</table>
