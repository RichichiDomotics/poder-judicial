<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" media="screen" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>

       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" rel="stylesheet">
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<style>
.transpa {
background-color: transparent;
border: 0;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	var reloadGridcarga=false;
	var reloadGrid=false;
	var idDefensor="";
	var carga = "";
	var idCarga = "";
	 var ditable=0;
	 var cargaMinutos=0;
	
	
	$(document).ready(function() {
		grid2();
		cargaActividades();

		$("#divFecha").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true
			
		});

		$('#divHora').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
	});

	function grid2(){
		
		if (reloadGrid==true) {
			  jQuery("#tabgrid").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/ConsultaDefensor.do'});
			  $("#tabgrid").trigger("reloadGrid"); 
		} else{
			jQuery("#tabgrid").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultaDefensor.do',
				data:"",
				datatype: "xml", 
				colNames:['Nombre'], 
				colModel:[ 	{name:'Nombre',index:'Nombre', width:200, resizable:true} 
				           	
							
							
						],
				resizable:true,
				pager: jQuery('#tabgrid'),
				rowNum:10,
				rowList:[10,20,30],
				width: 600,
				height: 400,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				onresize_end: function () { $("#tabgrid").setGridWidth($("#mainContent").width() - 5, true);},
				onSelectRow: function(rowID) {
					idDefensor=rowID;
					
					}
			}).navGrid('#tabgrid',{edit:false,add:false,del:false});	
			$(this).find('table').width('100%');
			//reloadGrid=true;
		  }
		
	}

	

	function cargaActividades(){
			jQuery("#tabgridActividad").jqGrid({ 
				url:'<%= request.getContextPath()%>/ConsultarCatalogoActividades.do',
				data:"",
				datatype: "xml", 
				colNames:['Actividad','Carga'], 
				colModel:[ 	{name:'Actividad',index:'Actividad', width:200, resizable:true }, 
				        	{name:'CargaTrabajo',index:'CargaTrabajo', width:200, resizable:true, editable:true} 						
							
						],
				resizable:true,
				pager: jQuery('#tabgridActividad'),
				rowNum:10,
				rowList:[10,20,30],
				width: 600,
				height: 400,
				sortname: 'detalle',
				viewrecords: true,
				sortorder: "desc",
				onresize_end: function () { $("#tabgridActividad").setGridWidth($("#mainContent").width() - 5, true);},
				
				ondblClickRow: function(rowID) {
					
					jQuery("#tabgridActividad").jqGrid('editRow',rowID);
					
					this.disabled = 'true';
					ditable=1;

					
					},
				onSelectRow: function(rowID) {
					idCarga=rowID;
					//jQuery("#tabgridActividad").jqGrid('saveRow',idCarga);
					}
			}).navGrid('#tabgrid',{edit:false,add:false,del:false});	
			$(this).find('table').width('100%');
			//reloadGridcarga=true;
		 // }
		
	}

	function asignarCargaTrabajo() {
		
		   
	    
		    var id2 = jQuery("#tabgridActividad").jqGrid('getGridParam','selrow');
		   
			var id = jQuery("#tabgrid").jqGrid('getGridParam','selrow');
			if (id&&id2)	{
                var ret2 = jQuery("#tabgridActividad").jqGrid('getRowData',id2);
                if(ditable==1){
                	
                    cargaMinutos= $('#'+id2+'_CargaTrabajo').val();
                    cargaMinutos= cargaMinutos*60;
                    }else{
				cargaMinutos = ret2.CargaTrabajo*60;
                    }
				var ret = jQuery("#tabgrid").jqGrid('getRowData',id);
				var param ="";
				param += 'carga='+cargaMinutos+ '&idCarga='+idCarga;
				
				param += '&id='+idDefensor+ '&nombreDef='+ret2.Actividad;

				param += '&fecha='+$("#divFecha").val();
				param += '&hora='+$("#divHora").val();

			} else { alertDinamico("Seleccione un defensor");}
		
		   $.ajax({
		     type: 'POST',
		     url: '<%= request.getContextPath()%>/AsignarCargaTrabajo.do',
			 data: param,
			 dataType: 'xml',
			 success: function(xml){
			  }
			});
		 }
	
	
	
</script>

<title>Insert title here</title>
</head>
<body>
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
<table width="100%" align="center" style="border-left: 1;border-right: 1;border-bottom: 1;border-top: 1;">
<tr align="center" class="fondoFuerteAP">
<td colspan="3" align="center">Lista de Defensores</td>
</tr>
<tr>
 
    <td width="25%" ><div id="nabtabgrid"> <table id="tabgrid" align="center"></table></div>
</td>
<td width="25%"><table>
<tr> <td>Fecha</td> </tr>
 <tr><td><input type="text" id="divFecha" name="divFecha"></td></tr>
 <tr><td>Hora</td></tr>
 <tr> <td> <input type="text" id="divHora"></td>  </tr>
 <tr> <td> <input type="button" value="Asignar" onclick="asignarCargaTrabajo()" class="btn_Generico"></td>  </tr>
 </table> </td>
<td width="50%"><div id="nabtabgrid2"> <table id="tabgridActividad" align="center"></table></div></td>
  </tr>
</table>

<!--<center>
	
	<input type="button" value="Seleccionar Abogado Para Defensa" onclick="designarAbogadoDefensorCoordinador()" class="btn_Generico">
</center>
--><script type="text/javascript">

</script>
</body>
</html>