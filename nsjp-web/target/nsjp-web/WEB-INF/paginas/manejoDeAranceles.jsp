
	<%@page import="org.apache.commons.lang.BooleanUtils"%>
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
	
	$(document).ready(function() {
<%
boolean mostrarCaptura = BooleanUtils.toBoolean(request.getParameter("mostrarCaptura"), "true", null);
if(mostrarCaptura){
%>
		
		$("#fechaInicio").datepicker({
			changeMonth: true,
			changeYear: true
		});			

		$("#fechaFinal").datepicker({
			changeMonth: true,
			changeYear: true
		});			
		
		$("#expediente").val("<%=request.getParameter("numeroExpedienteSt")%>");
		$("#etapa").val("<%=request.getParameter("etapaExpediente")%>");
		$("#defensor").val("<%=request.getParameter("nombreDef")%>");

		$("#registrarArancel").click(registrarArancel); 

		cargarCatalogoClaseDefensa();
<%
}
%>		
		arancelesExpediente();
	});	
	
	function cargarCatalogoClaseDefensa(){
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/cargarCatalogoNivelSocioeconomico.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('nivel').each(function(){
    			var clave = $(this).find('clave').first().text();
    			var valor = $(this).find('valor').first().text();
    			var cuota = $(this).find('cuota').first().find('valor').text();
    			$('#claseDefensa').append('<option value="'+clave+'-'+cuota+'">'+ valor + '</option>');
    			});
    		}
    	});
	}
	
	function arancelesExpediente(){
		var params = "?numeroExpedienteId=<%=request.getParameter("numeroExpedienteId")%>";
		jQuery("#gridAranceles").jqGrid({
			url: '<%= request.getContextPath()%>/consultarArancelesExpediente.do'+params,
			datatype: "xml", 
			colNames:['Fecha del Arancel','Defensor','Periodo','Costo','Horas','Costo Total','Fecha de Pago'], 
			colModel:[ {name:'fecha',	index:'2031', width:100, align:"center"},
			           {name:'defensor',index:'2006', width:150, align:"center"},
					   {name:'periodo',	index:'2032', width:150, align:"center"},
					   {name:'costo',	index:'2033', width:75, align:"center"},
					   {name:'horas',	index:'2034', width:75, align:"center"},
					   {name:'ctotal',	index:'2035', width:100, align:"center"},
					   {name:'fPago',	index:'2036', width:100, align:"center"}
					  ],
			pager: jQuery('#pagAranceles'),
			rowNum:5, rowList:[5,10,15],
			autoheight:true, width: 800,
			viewrecords: true,
			onresize_end:function(){ $("#pagAranceles").setGridWidth($("#mainContent").width() - 5, true);},
			onSelectRow: function(rowid){
				var arancel = jQuery("#gridAranceles").jqGrid('getRowData',rowid);
				if(arancel.fPago == "sin pago"){
					pagarArancel(rowid)
				}else{
					customAlert("El arancel ya ha sigo pagado");
				}
			}
		}).navGrid('#pagAranceles',{edit:false,add:false,del:false});
		$("#gridAranceles").trigger("reloadGrid"); 
	}
	
	 
	 function pagarArancel(idArancel){
		 var confir = confirm("¿Desea guardar el pago del arancel con la fecha de hoy?");
		 if(confir){
			 var param ="";
			 param += "arancel_id="+idArancel;
			 param += "&expedienteDTO.numeroExpedienteId="+"<%=request.getParameter("numeroExpedienteId")%>";
			 $.ajax({
		   		type: 'POST', data: param, dataType: 'xml',
		   		url: '<%= request.getContextPath()%>/pagarArancelExpediente.do',
		   		async: false,
		   		success: function(xml){
				}
		   	});
		 }
		$("#gridAranceles").trigger("reloadGrid");	
	 }
	 
	 function registrarArancel(){
		 var horas = parseInt($("#horas").val());
		 var valor = $("#claseDefensa").val().split("-");
		 var monto = (horas * parseInt(valor[1]));
		 var param = "";
		 param += "horas="+horas;
		 param += "&monto="+monto;
		 param += "&fechaInicial="+$("#fechaInicio").val();
		 param += "&fechaFinal="+$("#fechaFinal").val();
		 param += "&clase_valDTO.idCampo="+valor[0];
		 param += "&expedienteDTO.numeroExpedienteId="+"<%=request.getParameter("numeroExpedienteId")%>";
		 param += "&funcionarioDTO.claveFuncionario=<%=request.getParameter("idDefensor")%>";
		 $.ajax({
	   		type: 'POST', data: param, dataType: 'xml',
	   		url: '<%= request.getContextPath()%>/registrarArancelExpediente.do',
	   		async: false,
	   		success: function(xml){
	   		}
	   	});
		$("#gridAranceles").trigger("reloadGrid");
	 }
	</script>	

<body>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
<%
if(mostrarCaptura){
%>
    <tr>
    	<td>
		<table>
		<tr>
			<td>
				<strong>
					Defensor:
				</strong>
			</td>
			<td>
				<input class="texto" type="text" readonly="readonly" id="defensor"/>

			</td>
			<td>
				<strong>
					Expediente:
				</strong>				
			</td>
			<td>
				<input class="texto" type="text" readonly="readonly" id="expediente"/>
			</td>
			<td>
				<strong>
					Etapa:
				</strong>				
			</td>
			<td>
				<input class="texto" type="text" readonly="readonly" id="etapa"/>
			</td>
		</tr>
		<tr>
			<td>
				<strong>
					Horas trabajadas:
				</strong>				
			</td>
			<td>
				<input class="texto" type="text" id="horas"/>
			</td>		
			<td>
				<strong>
					Clase de defensa:
				</strong>				
			</td>
			<td>
				<select class="texto" id="claseDefensa">
				<option value="0">Seleccione un opción</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<strong>
					Fecha inicio:
				</strong>				
			</td>
			<td>
				<input class="texto" id="fechaInicio" type="text" />
			</td>
			<td>
				<strong>
					Fecha final:
				</strong>				
			</td>
			<td>
				<input class="texto" id="fechaFinal" type="text" />
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<input id="registrarArancel" type="button" value="Registrar" class="btn_Generico"/>
			</td>
		</tr>
		</table>
		</td>
	</tr>
<%
}
%>
	<tr>
		<td>
			<div id="divAranceles">
				<table id="gridAranceles"></table>
				<div id="pagAranceles"></div>
			</div>
		</td>
	</tr>
    </table>
</body>
