
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
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

	$(document).ready(function() {

		$('#horaDiligencia').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
        
		$("#fechaDiligencia").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		$('#acuerdoNo').click(habilitaJudializacion);
		$('#acuerdoSi').click(habilitaLibro);
		$('#judializacionNo').click(habilitaElementos);
		$('#judializacionSi').click(mostrarElementoJudializacion);
		$('#guardarLibroGobierno').click(guardaLibro);

		cargaComboTipoDiligencia();
	});	


	function habilitaElementos(){

		$("#divFechaJudializacion").hide();		
		
		}

	function mostrarElementoJudializacion(){

		$('#divFechaJudializacion').show();		
				
	}

	function enviaDatosSolicitud (){
		
	        	document.solicitudRecurso.submit();

			}

	function habilitaJudializacion(){

		$('#divLibroGobierno').hide();
		$('#divJudializacion').show();
		

		}	

	function habilitaLibro(){

		$('#divLibroGobierno').show();
		$('#divJudializacion').hide();

		}	

	function guardaLibro(){

		$('#generarLibro').removeAttr('disabled');

		}

	//Funcion que carga los combos de tipo de diligencia
	function cargaComboTipoDiligencia() {
     $.ajax({
    	  type: 'POST',
    	  url: '<%= request.getContextPath()%>/consultarCatalogoTipoDiligencia.do',
    	  data: '',
    	  async: false,
    	  dataType: 'xml',
    	  success: function(xml){
	    	  $('#tipoDiligencia').empty();
	    	  $('#tipoDiligencia').append('<option value="-1">- Seleccione -</option>');
	    	  $(xml).find('tipoDiligencia').each(function(){
				$('#tipoDiligencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				
				   });
    	  }
    	});
	}
	
	</script>	

<body>
<table width="700px" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="3%">&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3" align="center"><strong>Tipo de diligencia:
        <select id="tipoDiligencia">
        
        </select>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="left"><strong>Fecha de Diligencia:
      <input type="text" id="fechaDiligencia" />
      <br>      
    <br>
    </strong></td>
    <td width="46%" align="left"><strong> Hora de Diligencia: 
      <input type="text" id="horaDiligencia" />
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
			<td width="23%" valign="top"><strong>Notas de la Diligencia:</strong></td>
			<td colspan="2" valign="top"><strong>
			  <textarea name="textarea" cols="40" rows="3"></textarea>
			</strong></td>
			<td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3" align="center"><input type="button" value="Registrar" id="registrar" class="btn_Generico"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
