
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">

	var archivoDigitalId;
	var cedula;
	var acuerdo;
	
	$(document).ready(function() {

		$('#judializacionNo').click(habilitaElementos);
		$('#judializacionSi').click(mostrarElementoJudializacion);
		$('#liberacionSi').click(mostrarElementoLiberacion);
		$('#liberacionNo').click(mostrarElementoLiberacionNo);
		
		
		$('#horaJudalizacion,#horaLiberacion').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
        
		$("#fechaJudalizacion,#fechaLiberacion").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
	});	
	
	//funcion que abre los archivos digitales
	function abrirPDF(tipo){

		if(tipo=="1"){

			archivoDigitalId=cedula;

			}else{

				archivoDigitalId=acuerdo;
				
				}

		document.frmDoc.archivoDigitalId.value = archivoDigitalId;
		document.frmDoc.submit();
		
	}

	function habilitaElementos(){

		$('#divJudializacion').hide();
		$('#divLiberacion').show();
		$('input[name=liberacion]:eq(0)').attr('checked', 'checked');
		$('input[name=liberacion]:eq(1)').attr('disabled', 'disabled');
		//$('#judializacionNo').attr('checked');
		
		}

	function mostrarElementoJudializacion(){

		$('#divJudializacion').show();
		$('#divLiberacion').hide();
		$('input[name=liberacion]:eq(1)').attr('checked', 'checked');
		$('input[name=liberacion]:eq(1)').removeAttr('disabled', 'disabled');
		
				
	}

	function mostrarElementoLiberacion(){
		//$('#divJudializacion').hide();
		$('#divLiberacion').show();
		$('#guardarBitacora').removeAttr('disabled');
		//$('input[name=judializacion]:eq(1)').attr('checked', 'checked');

		}

	function mostrarElementoLiberacionNo(){

		$('#divLiberacion').hide();
		$('#guardarBitacora').removeAttr('disabled');
		

		}

	
	</script>	

<body>
<table width="600px" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="7%">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="left"><strong>C&eacute;dula de Identificaci&oacute;n:
      
        <input type="text" id="cedulaIdentificacion2"
				style=" border: 0; background: #DDD;"
				readonly="readonly"  size="50" />
    </strong><strong>
      <input type="button" value="Ver" id="verDocumento" onclick="abrirPDF(1)" class="btn_Generico"/>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="left"><strong>Acuerdos de Defensa:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <input type="text" id="acuerdosDefensa"
				style=" border: 0; background: #DDD;"
				readonly="readonly" size="50" />
    </strong><strong>
      <input type="button" value="Ver" id="verDocumento2"  onclick="abrirPDF(1)" class="btn_Generico"/>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="39%" align="left"><strong> Se acepta la designaci&oacute;n del defensor:
        
    </strong></td>
    <td width="53%" align="left"><strong>Si
      <input type="radio" id="acuerdoSi"  name="acuerdos" />
      No
<input type="radio" id="acuerdoNo"  name="acuerdos"/>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><strong>Judicializaci&oacute;n:</strong></td>
    <td><strong>Si
        <input type="radio" id="judializacionSi"  name="judializacion" />
    No
        <input type="radio" id="judializacionNo"  name="judializacion" />
    </strong></td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2"><div id="divJudializacion" style="display: none">

<strong>Fecha de Judicialización:</strong>
<input type="text" id="fechaJudalizacion" />
<strong>Hora:</strong><input type="text" id="horaJudalizacion" /></div></td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><strong>Liberaci&oacute;n:</strong></td>
    <td><strong>Si
      <input type="radio" id="liberacionSi"  name="liberacion" />
    No
      <input type="radio" id="liberacionNo"  name="liberacion" />
    </strong></td>
    <td>&nbsp;</td>
  </tr>
    <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2"><div id="divLiberacion" style="display: none">

<strong>Fecha de Liberación:</strong>
<input type="text" id="fechaLiberacion" />
<strong>Hora:</strong><input type="text" id="horaLiberacion" /></div></td>
    <td>&nbsp;</td>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center"><input type="button" value="Guardar" id="guardarBitacora" disabled="disabled" class="btn_Generico"/></td>
    <td>&nbsp;</td>
  </tr>
</table>

<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
					<input type="hidden" name="archivoDigitalId" value=""/>
					
										
</form>	

</body>
