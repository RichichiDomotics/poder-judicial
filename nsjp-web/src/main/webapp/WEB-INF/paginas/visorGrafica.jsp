<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Visor Graficas</title>

	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>

	<!--para controlar las ventanas-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript">

	var ejecutaDODinamico;
	
	$(document).ready(function() {

		cargaListaGraficos();
		$("#idTiempoInicioGrafica,#idTiempoFinGrafica").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true
			
		});

		$('').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
		
	});


    /*
	*Funcion que dispara el Action para consultar el reportes
	*/		
    function cargaListaGraficos(){
    	selectTipoGrafica = $("#selectTipoGrafica option:selected").val();
    	//alert(selectTipoGrafica);
    	var TI= $("#idTiempoInicioGrafica").val();
    	var TF=  $("#idTiempoFinGrafica").val();
    	if(TI!=""&&TF!=""){
    	var ejecutaDODinamico="";
    	if(selectTipoGrafica==0){
        	ejecutaDODinamico="<%= request.getContextPath()%>/graficaDenunciaVSTipoDelito.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

    	
    	if(selectTipoGrafica==1){
    	ejecutaDODinamico="<%= request.getContextPath()%>/graficaDeterminacionPorDenuncia.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
    	}

    	if(selectTipoGrafica==2){
        	ejecutaDODinamico="<%= request.getContextPath()%>/graficaDenunciasProbablesResponsablesDetenidos.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

    	if(selectTipoGrafica==3){
        	ejecutaDODinamico="<%= request.getContextPath()%>/graficaPricipalesDelitos.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
    	}

    	if(selectTipoGrafica==4){
        	ejecutaDODinamico="<%= request.getContextPath()%>/graficaDenunciasTiempo.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

        if(selectTipoGrafica==5){
            ejecutaDODinamico="<%= request.getContextPath()%>/graficaDetencionesPorTipo.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
            }

       	if(selectTipoGrafica==6){
            ejecutaDODinamico="<%= request.getContextPath()%>/graficaInformesElaborados.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

       	if(selectTipoGrafica==7){
            ejecutaDODinamico="<%= request.getContextPath()%>/graficacCasosRemitidos.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

       	if(selectTipoGrafica==8){
            ejecutaDODinamico="<%= request.getContextPath()%>/graficaSeguimientoMedidasCautelares.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}

       	if(selectTipoGrafica==9){
            ejecutaDODinamico="<%= request.getContextPath()%>/graficaSeguimientoMedidasAlternas.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val();
        	}
       	
    	//alert(ejecutaDODinamico);   
       $("#imagenGrafica").attr("src",ejecutaDODinamico);
    	}else{

        	alert("Selecciona un Rango de Fechas");
        	}
    }

    function cargaListaReportes(){
       
    	var ejecutaDODinamico="";
    //	var selectTipoReporte = $("#selectTipoReporte option:selected").val();
    	//alert(selectTipoGrafica);
    	var TI= $("#idTiempoInicioGrafica").val();
    	var TF=  $("#idTiempoFinGrafica").val();
    	
    	
        //    var param ='tiempoI='+TI;
    		///	param+='&tiempoF='+TF;
    			//param+='&tipoReporte='+selectTipoReporte;
    		
    			if(TI!=""&&TF!=""){
        			
    				ejecutaDODinamico="<%= request.getContextPath()%>/visorReportes.do?tiempoI="+$("#idTiempoInicioGrafica").val()+"&tiempoF="+$("#idTiempoFinGrafica").val()+"&tipoReporte="+$("#selectTipoReporte option:selected").val();
    		
    	 	//alert(ejecutaDODinamico);
    	
    	$("#imagenReporte").attr("src",ejecutaDODinamico);
    	}else{

        	alert("Selecciona un Rango de Fechas");
        	}
    }

    function botonesGraficaReportes(queTipo){
       // alert(queTipo);
		if(queTipo=="2"){
			 
			 $("#selectTipoReporte").css("display","none");
			 $("#selectTipoGrafica").css("display","block");
			 $("#imagenReporteDiv").css("display","none");
			 $("#imagenGrafica").css("display","block");
			 
			}

		
		if(queTipo=="1"){
		$("#selectTipoReporte").css("display","block");
		$("#selectTipoGrafica").css("display","none");
		 $("#imagenReporteDiv").css("display","block");
		 $("#imagenGrafica").css("display","none");
		 
		}
        }
	</script>
</head>
<body>

<table width="829" height="416" border="0">
  <tr>
    <td width="272" height="240"><table width="272" height="402" border="0">
  <tr>
    <td colspan="2" align="center">Periodo</td>
    </tr>
  <tr>
    <td width="134">De: 
     
          <input type="text" name="idTiempoInicioGrafica" id="idTiempoInicioGrafica" width="100" />
       </td>
    <td width="128">A:
      <input type="text" name="idTiempoFinGrafica" id="idTiempoFinGrafica" width="100"/></td>
  </tr>
  <tr align="center">
    <td><input type="button" name="idbotonReporte" id="idbotonReporte" value="Reportes" class="back_button" onclick="botonesGraficaReportes('1')"/></td>
    <td><input type="button" name="idbotonGrafica" id="idbotonGrafica" value="Gráficas" class="back_button" onclick="botonesGraficaReportes('2')"/></td>
  </tr>
  <tr>
    <td height="275" colspan="2" align="center"> 
        <select name="select" size="15" id="selectTipoGrafica" onchange="cargaListaGraficos()" >
          <option value="0">- Gráfica Denuncia VS Tipo Delito -</option>
          <option value="1">- Gráfica Determinacion VS Denuncia -</option>
          <option value="2">- Gráfica Denuncias VS Probables Responsables Detenidos -</option>
          <option value="3">- Gráfica Pricipales Delitos -</option>
          <option value="4">- Gráfica Denuncias Tiempo -</option>
          <option value="5">- Gráfica Detenciones Por Tipo -</option>
          <option value="6">- Gráfica Informes Elaborados -</option>
          <option value="7">- Gráfica Casos Remitidos -</option>
          <option value="8">- Gráfica Seguimiento Medidas Cautelares -</option>
          <option value="9">- Gráfica Seguimiento Medidas Alternas -</option>
        </select>
        <select name="select" size="15" id="selectTipoReporte" onchange="cargaListaReportes()" style="display: none;">
          <option value="0">- Reporte Denuncia VS Tipo Delito -</option>
          <option value="1">- Reporte Determinacion VS Denuncia -</option>
          <option value="2">- Reporte Denuncias VS Probables Responsables Detenidos -</option>
          <option value="3">- Reporte Pricipales Delitos -</option>
          <option value="4">- Reporte Denuncias Tiempo -</option>
          <option value="5">- Reporte Detenciones Por Tipo -</option>
          <option value="6">- Reporte Informes Elaborados -</option>
          <option value="7">- Reporte Casos Remitidos -</option>
          <option value="8">- Reporte Seguimiento Medidas Cautelares -</option>
          <option value="9">- Reporte Seguimiento Medidas Alternas -</option>
        </select>
     </td>
    </tr>
</table>
</td>
    <td width="547"><img id="imagenGrafica" src=""/> <div id="imagenReporteDiv"><iframe id="imagenReporte" src="" style="display: none;" width="100%" height="100%" scrolling="auto">&nbsp;lalalala</iframe></div></td>
  </tr>
</table>
	
</body>
</html>