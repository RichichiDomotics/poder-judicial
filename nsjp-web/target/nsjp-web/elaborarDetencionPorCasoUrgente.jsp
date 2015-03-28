<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elaborar Detención Por Caso Urgente</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estiloBoton.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">

	var funcionarioId=null;

		$(document).ready(function(){

			$('#elaborarOrdenDetencion').click(elaborarOrdenDetencion);
			gridFuncionario();
												
			});	

		function elaborarOrdenDetencion(){

	   	var nombre = $('#nombre').val();
		var aPaterno = $('#aPaterno').val();
		var aMaterno = $('#aMaterno').val();
		var alias = $('#alias').val();
		var delitos = $('#delitos').val();
		var motivoDetencion = $('#motivoDetencion').val();
		var nombreQuienAutoriza = $('#nombreQuienAutoriza').val();
		var puestoQuienAutoriza = $('#puestoQuienAutoriza').val();
		 				
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Resolutivo", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/enviaResolutivo.do?esconderArbol=1&formaId=18&nombre='+nombre+'&aPaterno='+aPaterno+'&aMaterno='+aMaterno+'&alias='+alias+'&delitos='+motivoDetencion+'&nombreQuienAutoriza='+nombreQuienAutoriza+'&puestoQuienAutoriza='+puestoQuienAutoriza+'&funcionarioId='+funcionarioId+'' width='1140' height='400' />");

		}

		function habilitaBoton() {

			if ($("#nombre").val().length < 1
					|| $("#aPaterno").val().length < 1
					|| $("#aMaterno").val().length < 1
					|| $("#delitos").val().length < 1
					|| $("#motivoDetencion").val().length < 1
					|| $("#nombreQuienAutoriza").val().length < 1
					|| $("#puestoQuienAutoriza").val().length < 1
					|| funcionarioId == "" || funcionarioId == null) {

			} else {

				$('#elaborarOrdenDetencion').removeAttr('disabled');

			}

		}

		function gridFuncionario() {

			jQuery("#gridComandantePoliciaMinisterial")
					.jqGrid(
							{
								url : '
	<%=request.getContextPath()%>/consultarFuncionarioRolComandantePoliciaMinisterial.do', 
					datatype: "xml", 
					colNames:['Nombre','A. Paterno','A. Materno','Cargo','Institucion'], 				 				   
					colModel:[ 	{name:'nombre',index:'nombre', width:40, align:"center"}, 
								{name:'aPaterno',index:'aPaterno', width:45, align:"center"}, 
								{name:'aMaterno',index:'aMaterno', width:30, align:"center"}, 
								{name:'puesto',index:'puesto', width:35, align:"center"},
								{name:'institucion',index:'institucion', width:50, align:"center"}
								
							],
					pager: jQuery('#paginadorComandanteMinisterial'),
					rowNum:20,
					rowList:[10,20,30],
					width:924,
					autowidth: false,
					sortname: 'detalle',
					viewrecords: true,
					sortorder: "desc",
					onSelectRow: function(rowid) {

						funcionarioId=rowid;

						habilitaBoton();
						
							
					}
				}).navGrid('#paginadorComandanteMinisterial',{edit:false,add:false,del:false}); 

			}
					
</script>
</head>

	<body>
             
                        
                        	<table width="800" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="51" rowspan="3">&nbsp;</td>
    <td colspan="4">&nbsp;</td>
    <td width="48" rowspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="154" align="right"><strong>Nombre(s):</strong></td>
    <td width="197"><input type="text" id="nombre" onkeyup="habilitaBoton()" /></td>
    <td width="169" align="right"><strong>Alias:</strong></td>
    <td><input type="text" id="alias" /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Apellido Paterno: </strong></td>
    <td align="left"><input type="text" id="aPaterno" onkeyup="habilitaBoton()" /></td>
    <td align="right"><strong>Delito(s):</strong></td>
    <td width="179" align="left"><input type="text" id="delitos" onkeyup="habilitaBoton()"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4" align="left">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Apellido Materno:</strong></td>
    <td><input type="text" id="aMaterno" onkeyup="habilitaBoton()" /></td>
    <td align="right"><strong>Motivo de la detención:</strong></td>
    <td><input type="text" id="motivoDetencion" onkeyup="habilitaBoton()"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4" align="center"><strong>Nombre completo de quien autoriza:
      
      </strong><strong>
      <input type="text" id="nombreQuienAutoriza" size="30" onkeyup="habilitaBoton()"/>
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4" align="center"><strong>Cargo de quien Autoriza la detención:
      
      </strong><strong>
      <input type="text" id="puestoQuienAutoriza" size="30" onkeyup="habilitaBoton()" />
    </strong></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>  
  <tr>
    <td rowspan="4">&nbsp;</td>
    <td colspan="4" align="center">	<table id="gridComandantePoliciaMinisterial"></table>
						<div id="paginadorComandanteMinisterial"></div></td>
    <td rowspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center"></td>
  </tr>
  <tr>
    <td rowspan="5">&nbsp;</td>
    <td colspan="4" align="center"><input type="button" value="Enviar Orden de Detención" id="elaborarOrdenDetencion" disabled="disabled" class="btn_Generico"/></td>
    <td rowspan="5">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center">&nbsp;</td>
    
  </tr>
</table>
	
</body>
	
</html>