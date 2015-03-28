<%@page import="mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusInspeccion"%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>			
	<script type="text/javascript">
	
	var idWindowGenerarNotas=1;
	var defensorId=""; 
	var multaAsociadaId="";
	var nombreDefensor="";
	var numeroExpediente="";
	var numeroExpedienteId="";
	var inspeccionId=0;
	
	$(document).ready(function() {
		nombreDefensor = '<%=request.getParameter("nombreDef")%>';
		defensorId = '<%=request.getParameter("idDefensor")%>';
		numeroExpediente= '<%=request.getParameter("numeroExpediente")%>';
		numeroExpedienteId= '<%=request.getParameter("numeroExpedienteId")%>';
		
		cargaGridInspecciones();
		llenaDatos();

		$("#botonCerrar").attr("disabled","disabled");
		
	});	

	function llenaDatos(){
		$("#defensor").val(nombreDefensor);
		$("#cmpExpediente").val(numeroExpediente);
	}
	
	function cargaGridInspecciones() {
		jQuery("#gridInspecciones").jqGrid({
					url : '<%= request.getContextPath()%>/consultarInspeccionesPorExpediente.do?defensorId='+defensorId+'&numeroExpedienteId='+numeroExpedienteId+'', 
					datatype: "xml", 						
					colNames:['Folio','Motivo Inspección','Inspección registrada por:','Fecha - Hora Registro','Descripción de la Inspección','Multa Asociada','Estatus'], 
					colModel:[{name:'folio',	index:'1',  width:150, align:"center"},
					          {name:'motivo',	index:'2', 	width:250, align:"center"},
					          {name:'registradaPor',	index:'3', 	width:300, align:"center"},
					          {name:'fecha',	index:'4', 	width:250, align:"center"},
					          {name:'descripcion', index:'5',  width:500, align:"center"},
					          {name:'multaAsociada',	index:'6', 	width:150, align:"center"},
					          {name:'estatus',	index:'7', 	width:150, align:"center"}
							],				
					pager: jQuery('#pagerGridInspecciones'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					autoheight:true,
					sortname: '1',
					viewrecords: true,
					sortorder: "desc",
					ondblClickRow: function(rowid) {
						consultarInspeccion(rowid);				
					}
		}).navGrid('#pagerGridInspecciones',{edit:false,add:false,del:false});
			jQuery("#gridInspecciones").trigger('reloadGrid');
	}

	function guardaInspeccion(){
  		var	param="motivoInspeccion="+$('#motivoInspeccion').val();
  		param+="&descripcionInspeccion="+$("#descripcionInspeccion").val();
  		param+="&defensorId="+defensorId;
  		param+="&multaAsociadaId="+multaAsociadaId;
  		param+="&numeroExpedienteId="+numeroExpedienteId;
  		param+="&inspeccionId="+inspeccionId;

    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/registrarInspeccionExpediente.do',
	    	  data:param,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		var respuestaMulta=  $(xml).find('long').text();
	    		if(respuestaMulta!=""){
					customAlert("Inspección registrada de manera correcta");
					window.parent.cerrarVentanaInspeccion();
		    		}else{
		    			customAlert("No se logró registrar la inspección");
		    		}
			  }
	    });
   	}

    function visorMultas(){
        cargaGridMultas();
		$( "#dialog-multas-defensor" ).dialog({
			autoOpen: true,
			//resizable: false,
			height:300,
			width:450,
			modal: true,
			buttons: {
				"Asociar": function() {
					 var multa = jQuery("#gridMultas").jqGrid('getGridParam','selrow');
					 if(multa != undefined && multa!= null){
						 customAlert("Se asocia la multa a la inspección");
						 multaAsociadaId = multa;
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
					 }else{
						$( this ).dialog( "close" );
						$( "#dialog:ui-dialog" ).dialog( "destroy" );
					 }
				},
				"Cancelar": function() {
					$( this ).dialog( "close" );
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					//dlgDetenidoInforme(false);
				}
			}
		});									
    }

	function cargaGridMultas() {
		jQuery("#gridMultas").jqGrid({
					url : '<%= request.getContextPath()%>/consultarMultasPorDefensor.do?defensorId='+defensorId+'', 
					datatype: "xml", 						
					colNames:['Folio de la Multa/Sanción','Motivo de la Multa/Sanción','Fecha - Hora Registro'], 
					colModel:[{name:'folio',	index:'1',  width:100, align:"center"},
					          {name:'motivo',	index:'2', 	width:200, align:"center"},
					          {name:'fecha',	index:'3', 	width:100, align:"center"}
							],				
					pager: jQuery('#pagerAudiencia'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: false,
					autoheight:false,
					sortname: '1',
					viewrecords: true,
					sortorder: "desc"
		}).navGrid('#pagerGridMultas',{edit:false,add:false,del:false});
			jQuery("#gridMultas").trigger('reloadGrid');
	}

    function consultarInspeccion(rowid){
    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarInspeccionExpediente.do?inspeccionId='+rowid+'',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  inspeccionId = $(xml).find('inspeccionDTO').find('inspeccionId').text();
    			  $("#botonCerrar").attr("disabled","");

    			  $("#motivoInspeccion").val($(xml).find('inspeccionDTO').find('motivo').text());
	    		  $("#motivoInspeccion").attr("disabled","disabled");
	    		  $("#descripcionInspeccion").val($(xml).find('inspeccionDTO').find('descripcion').text());

	    		  if($(xml).find('inspeccionDTO').find('estatus').find('idCampo').first().text() == <%=EstatusInspeccion.CONCLUIDA.getValorId()%>){
		    		  $("#descripcionInspeccion").attr("disabled","disabled");
	    		  }else{
		    		  $("#descripcionInspeccion").attr("disabled","");
	    		  }
	    	  }
	    });
    }   

    function cerrarInspeccion(){
    	$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/cerrarInspeccionExpediente.do?inspeccionId='+inspeccionId+'',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
				customAlert("Inspección concluida correctamente");
	    	  }
	    });
    }   
    
    </script>	

<body>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right""><input class="back_button" type="button" id="botonCerrar" onclick="cerrarInspeccion()" value="Concluir Inspección">
							<input class="back_button" type="button" onclick="guardaInspeccion()" value="Guardar"> 
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
					Expediente:
				</strong>
			</td>
			<td width="70%" align="left">
				<input class="texto" type="text" readonly="readonly" id="cmpExpediente" style="width:300px;"/>

			</td>
		</tr>
		<tr>
			<td width="30%" align="right">
				<strong>
					Motivo de la Inspecci&oacute;n:
				</strong>				
			</td>
			<td  width="70%" align="left">
				<input type="text" id="motivoInspeccion" style="width:600px;"/>
			</td>		
		</tr>
		<tr>
			<td width="30%" align="right">
				<strong>
					Descripci&oacute;n de la Inspecci&oacute;n:
				</strong>				
			</td>
			<td  width="70%" align="left">
				<textarea rows="10" id="descripcionInspeccion" style="width:600px;"> 
				</textarea>
			</td>		
		</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center""><input class="back_button" type="button" onclick="visorMultas()" value="Asociar Multa/Sanci&oacute;n a Inspecci&oacute;"> </td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<div id="divInspeccion">
				<table id="gridInspecciones"></table>
				<div id="pagerGridInspecciones"></div>
			</div>
		</td>
	</tr>
    </table>
   	<div id="dialog-multas-defensor" title="Multas y Sanciones">
		<table id="gridMultas"></table>
		<div id="pagerGridMultas"></div>
	</div>
    
</body>
