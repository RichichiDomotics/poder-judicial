<%@page import="java.util.Calendar"%>

<script type="text/javascript">

	var diasSemana =["Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"];
	var meses =["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	var diaActual='<%=(java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK))%>';	

	/*
	*Funcion que imprime la fecha actual en el calendario
	*/
	function cargaFechaActual(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
        	           	    
        	    var fechaActual = $(xml).find('fechaActual').text()
        	    var dia = fechaActual.split("/")[0];
        	    var mes = fechaActual.split("/")[1];
        	    var anio = fechaActual.split("/")[2];
        	    
        	    calcularDia();
    			$('#labelFecha').html(dia);
    			var mesActual =  meses[mes-1];
    			$('#labelMes').html(mesActual);    		
    		}
		});
    }

	/*
	*Funcion para calcular el día de la semana
	*/
    function calcularDia(){

        var diaNormal = parseInt(diaActual)-2;       
        var diaDeLaSemana = diasSemana[diaNormal];
       	$('#labelDia').html(diaDeLaSemana);    
    }

    /*
	*Funcion que imprime las actividades del usuario
	*/
    function cargaActividadesDelUsuarioPorFechaActual(){
          
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/consultarActividadesUsuarioPorFechaActual.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    	    	$(xml).find('actividad').each(function(){

        	    	var evento =  $(this).find('nombreEvento').first().text()
        	    	
   	    			if(evento.length > parseInt(20)){
   	    				var eventoRecortado = evento.substring(0,20);
   	    				$('#actividadesAgenda').append('<tr><td>' +$(this).find('horaInicioEvento').first().text()+" "+eventoRecortado+"..."+'</td></tr>');
       	    		}
   	    			else{
   	    				$('#actividadesAgenda').append('<tr><td>' +$(this).find('horaInicioEvento').first().text()+" "+evento+'</td></tr>');
       	    		}
				});
    	    	
    		}
		});
    }

    //Limpia la tabla para que no se repliquen los eventos asociados
    function limpiaTabla(){
    	
    	$('#actividadesAgenda tr').each(function() {
    		      $(this).remove();
    		});
    }


     
</script>

<div>

	<table style="cursor: pointer;" id="controlAgenda" width="150px"  height="150px" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/resources/images/calendarUsuario.png">
      <tr>
        <td align="center">
        	<span id="labelDia" style="font-size:15px;">
            </span>
        </td>
      </tr>
      <tr>
        <td align="center">
        	<span id="labelFecha" style="font-size:65px;">
            </span>
        </td>
      </tr>
      <tr>
        <td align="center">
        	<span id="labelMes" style="width:150px; height:150px; font-size:20px;">
            </span>
        </td>
      </tr>
    </table>
    
    <table id="actividadesAgenda" width="250px"  height="100%" border="0" cellspacing="0" cellpadding="0" style="cursor: pointer;">
    
    </table>
	    
	    
</div>


<script type="text/javascript">

	$("#actividadesAgenda").click(creaAgenda);
	cargaFechaActual();
	cargaActividadesDelUsuarioPorFechaActual();
	
</script>