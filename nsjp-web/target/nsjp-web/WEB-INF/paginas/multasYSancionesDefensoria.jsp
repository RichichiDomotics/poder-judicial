<%@page import="mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusMultaSancion"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>

<style type="text/css">

	.texto{
		width: 175px; 
		border: 0; 
		background: #DDD;
	}
	.transpa {
		background-color: transparent;
		border: 0;
	}
</style>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
	
	var idWindowGenerarNotas=1;
	var defensorId=""; 
	var multaId=0;
	$(document).ready(function() {
		var nombreDefensor = '<%=request.getParameter("nombreDef")%>';
		defensorId = '<%=request.getParameter("idDefensor")%>';
		
		cargaGridMultas();
		llenaDatos(nombreDefensor);
		$("#botonSaldar").attr("disabled","disabled");
	});	

	function llenaDatos(nombreDefensor){
		$("#defensor").val(nombreDefensor);
	}
	
	function cargaGridMultas() {
		jQuery("#gridMultas").jqGrid({
					url : '<%= request.getContextPath()%>/consultarMultasPorDefensor.do?defensorId='+defensorId+'', 
					datatype: "xml", 						
					colNames:['Folio de la Multa/Sanción','Motivo de la Multa/Sanción','Fecha - Hora Registro','Descripción de la Multa/Sanción','Estatus'], 
					colModel:[{name:'folio',	index:'1',  width:300, align:"center"},
					          {name:'motivo',	index:'2', 	width:350, align:"center"},
					          {name:'fecha',	index:'3', 	width:250, align:"center"},
					          {name:'descripcion', index:'4',  width:400, align:"center"},
					          {name:'estatus',	index:'5', 	width:250, align:"center"}
							],				
					pager: jQuery('#pagerAudiencia'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					sortname: '1',
					viewrecords: true,
					sortorder: "desc",
					ondblClickRow: function(rowid) {
						consultarMulta(rowid);				
					}
		}).navGrid('#pagerGridMultas',{edit:false,add:false,del:false});
			jQuery("#gridMultas").trigger('reloadGrid');
	}

	function guardaMulta(){
  		var	param="motivoMulta="+$('#motivoMulta').val();
  		param+="&descripcionMulta="+$("#descripcionMulta").val();
  		param+="&defensorId="+defensorId;
  		param+="&multaId="+multaId;

    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/registrarMultaDefensor.do',
	    	  data:param,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		var respuestaMulta=  $(xml).find('long').text();
	    		if(respuestaMulta!=""){
					alert("Multa Registrada con éxito");
					window.parent.cerrarVentanaMultas();
		    		}else{
		    			alert("No se logró registrar la multa");
			    		}
			  }
	    });
   	}

    function consultarMulta(rowid){
    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarMultaDefensor.do?multaId='+rowid+'',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  multaId = $(xml).find('multaSancionDTO').find('multaSancionId').text();
    			  $("#botonSaldar").attr("disabled","");
		    		  
	    		  $("#motivoMulta").val($(xml).find('multaSancionDTO').find('motivo').text());
	    		  $("#motivoMulta").attr("disabled","disabled");
	    		  $("#descripcionMulta").val($(xml).find('multaSancionDTO').find('descripcion').text());
	    		  if($(xml).find('multaSancionDTO').find('estatus').find('idCampo').text() == <%=EstatusMultaSancion.SALDADA.getValorId()%>){
		    		  $("#descripcionMulta").attr("disabled","disabled");
	    		  }else{
		    		  $("#descripcionMulta").attr("disabled","");
	    		  }
			  }
	    });
    }   

    function saldarMulta(){
    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/saldarMultaDefensor.do?multaId='+multaId+'',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
				alert("Multa saldada correctamente.");
	    	  }
	    });
    }   
    
    </script>	

<body>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right"><input class="back_button" type="button" id="botonSaldar" onclick="saldarMulta()" value="Saldar Multa">
						  <input class="back_button" type="button" onclick="guardaMulta()" value="Guardar"> 
	  	</td>
	</tr>
    <tr>
    	<td>
		<table width="100%">
		<tr>
			<td width="30%" align="right">
				<strong>
					Defensor:
				</strong>
			</td>
			<td width="70%" align="left">
				<input class="texto" type="text" readonly="readonly" id="defensor" style="width:300px;"/>

			</td>
		</tr>
		<tr>
			<td width="30%" align="right">
				<strong>
					Motivo de la multa/sanci&oacute;n:
				</strong>				
			</td>
			<td  width="70%" align="left">
				<input type="text" id="motivoMulta" style="width:600px;"/>
			</td>		
		</tr>
		<tr>
			<td width="30%" align="right">
				<strong>
					Descripci&oacute;n de la multa/sanci&oacute;n:
				</strong>				
			</td>
			<td  width="70%" align="left">
				<textarea rows="10" id="descripcionMulta" style="width:600px;"> 
				</textarea>
			</td>		
		</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<div id="divMultas">
				<table id="gridMultas"></table>
				<div id="pagerGridMultas"></div>
			</div>
		</td>
	</tr>
    </table>
</body>
